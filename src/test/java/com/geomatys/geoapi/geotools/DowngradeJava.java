/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership. You may not use this
 * file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.geomatys.geoapi.geotools;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Downgrade Java code to Java 11. It should be done on the release branch only.
 * This processor is incomplete: some modified classes will have compilation errors and will need to be hand-edited.
 * When this method is invoked for refreshing the existing class, the modified classes should be checked for changes
 * to edit or revert.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
public final class DowngradeJava implements Consumer<Path> {
    /**
     * A matcher for the {@code if (x instanceof y c)} pattern.
     */
    private final Matcher instanceOf;

    /**
     * A matcher for the {@code switch (impl)} pattern.
     */
    private final Matcher switchStmt;

    /**
     * A matcher for the cases in the {@code switch (impl)} statement.
     */
    private final Matcher switchCase;

    /**
     * A matcher for {@code case null:}.
     */
    private final Matcher nullCase;

    /**
     * A matcher for {@code default:}.
     */
    private final Matcher defaultCase;

    /**
     * Where to write the result of downgrading a file.
     */
    private final List<String> result;

    /**
     * Creates a new downgrade.
     */
    private DowngradeJava() {
        instanceOf  = Pattern.compile("(\\s*)if\\s*\\(\\s*([\\w\\.]+)\\s+instanceof\\s+([\\w<\\?>\\.]+)\\s+(\\w+).+").matcher("");
        switchStmt  = Pattern.compile("(\\s*)switch\\s*\\(([\\w\\.]+)\\).+").matcher("");
        switchCase  = Pattern.compile("\\s*case\\s*([\\w<\\?>\\.]+)\\s*(\\w+)\\s*:\\s*(.*)").matcher("");
        nullCase    = Pattern.compile("\\s*case\\s*null\\s*:\\s*(.*)").matcher("");
        defaultCase = Pattern.compile("\\s*default\\s*:\\s*(.*)").matcher("");
        result      = new ArrayList<>();
    }

    /**
     * Downgrades all classes.
     *
     * @param directory the directory of source files.
     * @throws IOException if an I/O error occurred.
     */
    private void run(final Path directory) throws IOException {
        try {
            Files.list(directory).filter((file) -> file.toString().endsWith(".java")).forEach(this);
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }

    /**
     * Downgrades the specified class.
     *
     * @param file source source file to parse.
     */
    @Override
    public void accept(final Path file) {
        result.clear();
        String switchOnVariable = null;     // Used as a sentinel value telling that we are inside a switch block.
        String switchCaseIndent = null;
        String endOfSwitchBlock = null;
        try {
            for (String line : Files.readAllLines(file)) {
                if (instanceOf.reset(line).matches()) {
                    String ident  = instanceOf.group(1);
                    String source = instanceOf.group(2);
                    String type   = instanceOf.group(3);
                    String target = instanceOf.group(4);
                    result.add(line.substring(0, instanceOf.end(3)) + line.substring(instanceOf.end(4)));
                    result.add(ident + "    var " + target + " = (" + type + ") " + source + ';');
                    continue;
                }
                if (switchStmt.reset(line).matches()) {
                    switchOnVariable = switchStmt.group(2);
                    switchCaseIndent = switchStmt.group(1);
                    endOfSwitchBlock = switchCaseIndent + '}';
                    continue;
                }
                if (switchOnVariable != null) {
                    if (line.equals(endOfSwitchBlock)) {
                        switchOnVariable = null;
                        continue;
                    }
                    if (defaultCase.reset(line).matches()) {
                        String stmt = defaultCase.group(1);
                        result.add(switchCaseIndent + stmt);
                        continue;
                    }
                    if (nullCase.reset(line).matches()) {
                        String stmt = nullCase.group(1);
                        result.add(switchCaseIndent + "if (" + switchOnVariable + " == null) {");
                        result.add(switchCaseIndent + "    " + stmt);
                        result.add(switchCaseIndent + '}');
                        continue;
                    }
                    if (switchCase.reset(line).matches()) {
                        String type = switchCase.group(1);
                        String name = switchCase.group(2);
                        String stmt = switchCase.group(3);
                        result.add(switchCaseIndent + "if (" + switchOnVariable + " instanceof " + type + ") {");
                        result.add(switchCaseIndent + "    var " + name + " = (" + type + ") " + switchOnVariable + ';');
                        result.add(switchCaseIndent + "    " + stmt);
                        result.add(switchCaseIndent + '}');
                        continue;
                    }
                }
                result.add(line);
            }
            Files.write(file, result);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Downgrades the classes.
     *
     * @param args ignored.
     * @throws IOException if an I/O error occurred.
     */
    public static void main(String[] args) throws IOException {
        var d = new DowngradeJava();
        d.run(Path.of("src/main/java/com/geomatys/geoapi/geotools"));
    }
}
