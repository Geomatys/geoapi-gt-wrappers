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
import java.util.function.Consumer;


/**
 * Generates {@code *ToGT} classes as copies of {@code *FromGT} class with source and target <abbr>API</abbr> interchanged.
 * This generator is incomplete: some generated classes will have compilation errors and will need to be hand-edited.
 * When this method is invoked for refreshing the existing class, the generated classes should be checked for changes
 * to revert.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
public final class ReverseWrapperGenerator implements Consumer<Path> {
    /**
     * The first and second <abbr>API</abbr> to interchange.
     * Will become (after swap) the source and target respectively.
     */
    private static final String SOURCE_API = "opengis", TARGET_API = "geotools.api";

    /**
     * A temporary value to use during the interchange.
     */
    private static final String PLACEHOLDER = "#PLACEHOLDER#";

    /**
     * The file suffix before and after generation.
     */
    private static final String SOURCE_SUFFIX = "FromGT", TARGET_SUFFIX = "ToGT";

    /**
     * Replacements to perform after the API swapping.
     * Values at even index are the string to search, and values at odd index are the replacements.
     */
    private final String[] replacements;

    /**
     * Creates a new generator.
     */
    private ReverseWrapperGenerator() {
        replacements = new String[] {
            "org.opengis.geometry.Position",             "org.opengis.geometry.DirectPosition",
            "org.geotools.api.geometry.DirectPosition",  "org.geotools.api.geometry.Position",
            "org.opengis.referencing.FactoryException",  "org.opengis.util.FactoryException",
            "org.geotools.api.util.FactoryException",    "org.geotools.api.referencing.FactoryException",

            // Documentation
            "object from the GeoAPI API", "implementation of the GeoAPI interface",
            "GeoAPI API", "GeoAPI"
        };
    }

    /**
     * Generates the classes.
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
     * Writes {@code ToGT} file from the given {@code FromGT} file.
     *
     * @param file source source file to parse.
     */
    @Override
    public void accept(final Path file) {
        String name = file.getFileName().toString();
        name = name.substring(0, name.lastIndexOf('.'));
        if (!name.startsWith("Wrapper") && name.endsWith(SOURCE_SUFFIX)) try {
            name = name.substring(0, name.length() - SOURCE_SUFFIX.length());
            final var output = new ArrayList<String>(100);
            for (String line : Files.readAllLines(file)) {
                if (!line.startsWith("package ")) {
                    line = swap(line, SOURCE_SUFFIX, TARGET_SUFFIX);
                    line = swap(line, SOURCE_API, TARGET_API);
                    line = swap(line, "OnLineResource", "OnlineResource");
                    line = swap(line, "GeoAPI", "GeoTools");
                    for (int i=0; i < replacements.length;) {
                        line = line.replace(replacements[i++], replacements[i++]);
                    }
                }
                output.add(line);
            }
            Files.write(file.resolveSibling(name + TARGET_SUFFIX + ".java"), output);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Swaps the source and target in the given line.
     */
    private static String swap(String line, String source, String target) {
        line = line.replace(source, PLACEHOLDER);
        line = line.replace(target, source);
        line = line.replace(PLACEHOLDER, target);
        return line;
    }

    /**
     * Generates the {@code *ToGT} classes.
     *
     * @param args ignored.
     * @throws IOException if an I/O error occurred.
     */
    public static void main(String[] args) throws IOException {
        var generator = new ReverseWrapperGenerator();
        generator.run(Path.of("src/main/java/com/geomatys/geoapi/geotools"));
    }
}
