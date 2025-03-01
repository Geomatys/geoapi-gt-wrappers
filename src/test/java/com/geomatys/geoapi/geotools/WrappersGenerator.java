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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Generates the main body of the {@link Wrappers} class.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
public final class WrappersGenerator implements Consumer<Path> {
    /**
     * The import statements to write, omitting the prefix and the trailing semi-colon.
     */
    private static final String[] IMPORTS = {
        "util.*",
        "util.Record",
        "temporal.*",
        "referencing.*",
        "referencing.cs.*",
        "referencing.crs.*",
        "referencing.datum.*",
        "referencing.operation.*",
        "parameter.*",
        "metadata.citation.*",
        "metadata.extent.*",
        "metadata.quality.*",
        "metadata.Identifier",
        "geometry.DirectPosition"
    };

    /**
     * Mapping from GeoAPI interface name to the wrapper name when those names differ.
     */
    private static final Map<String,String> GEOAPI_TO_WRAPPER = Map.of(
            "Element", "QualityElement",
            "Result",  "QualityResult");

    /**
     * The suffix of the implementation to parse.
     */
    private final String suffix;

    /**
     * Name of the class to generate.
     */
    private final String outputClassName;

    /**
     * The pattern for finding {@code wrap(…)} methods.
     */
    private final Pattern wrapMethodPattern;

    /**
     * All wrap methods found in the source files.
     * Keys are the GeoAPI interface name and values are the method signature of the implementation.
     */
    private final SortedMap<String,String> wrapMethods;

    /**
     * The classes identified as final.
     */
    private final Set<String> finalClasses;

    /**
     * Creates a new generator.
     */
    private WrappersGenerator() {
        suffix = "FromGT";
        outputClassName = "Wrappers";
        wrapMethods = new TreeMap<>();
        finalClasses = new HashSet<>();
        wrapMethodPattern = Pattern.compile(".*static\\s+(\\w+)\\s+wrap\\s*\\(.+");
    }

    /**
     * Generates the class.
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
        try (final var out = Files.newBufferedWriter(directory.resolve(outputClassName + ".java"))) {
            out.write("package com.geomatys.geoapi.geotools;");
            out.newLine();
            out.newLine();
            for (String line : IMPORTS) {
                out.append("import org.opengis.").append(line).append(';');
                out.newLine();
            }
            out.newLine();
            out.append("public final class ").append(outputClassName).append(" {");
            out.newLine();
            for (final Map.Entry<String,String> entry : wrapMethods.entrySet()) {
                appendWrapMethod(out, entry.getKey(), entry.getValue());
            }
            out.write('}');
            out.newLine();
        }
    }

    /**
     * Loads the given source file. This method is public as an implementation side-effect.
     * This method only collects information without writing anything to the file.
     *
     * @param file source source file to parse.
     */
    @Override
    public void accept(final Path file) {
        String name = file.getFileName().toString();
        name = name.substring(0, name.lastIndexOf('.'));
        if (!name.startsWith("Wrapper") && name.endsWith(suffix)) try {
            name = name.substring(0, name.length() - suffix.length());
            final Matcher matcher = wrapMethodPattern.matcher("");
            for (String line : Files.readAllLines(file)) {
                if (line.startsWith("final class ")) {
                    finalClasses.add(name);
                }
                if (matcher.reset(line).matches()) {
                    String type = matcher.group(1);
                    if (type.endsWith("Exception")) {
                        continue;
                    }
                    if (type.endsWith(suffix)) {
                        line = line.replace(type, type = type.substring(0, type.length() - suffix.length()));
                    }
                    if (wrapMethods.put(type, line) != null) {
                        throw new RuntimeException("Collision for type " + type);
                    }
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Writes the {@code wrap} method for the given GeoAPI interface.
     *
     * @param out   where to write.
     * @param type  the GeoAPI interface.
     * @param line  the method signature, without the {@code public} keyword.
     * @throws IOException if an error occurred while writing the method.
     */
    private void appendWrapMethod(final BufferedWriter out, String type, String line) throws IOException {
        line = line.replace(" wrap(final ", " geoapi(")     // Change method name.
                   .replace(" wrap(",       " geoapi(")     // In case a parameter did not have the `final` keyword.
                   .replace(" impl)", " geotools)");        // Change parameter name.
        out.newLine(); out.append("    /**");
        out.newLine(); out.append("     * Views the given GeoTools object as a GeoAPI {@code ").append(type).append("}.");
        out.newLine(); out.append("     * This method returns the first of the following choices which is applicable:");
        out.newLine(); out.append("     * <ol>");
        out.newLine(); out.append("     *   <li>If the given object is null, returns {@code null}.</li>");
        out.newLine(); out.append("     *   <li>If the given object already implements the GeoAPI {@code ").append(type).append('}');
        out.newLine(); out.append("     *       interface, returns that {@code impl} instance directly.</li>");
        if (!finalClasses.contains(type)) {
            out.newLine(); out.append("     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}");
            out.newLine(); out.append("     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>");
        }
        out.newLine(); out.append("     *   <li>Otherwise, wraps {@code impl} in a {@code ").append(type).append('}');
        out.newLine(); out.append("     *       which will forward all methods to {@code impl}.</li>");
        out.newLine(); out.append("     * </ol>");
        out.newLine(); out.append("     *");
        out.newLine(); out.append("     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.");
        out.newLine(); out.append("     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.");
        out.newLine(); out.append("     */");
        out.newLine(); out.append("    public ").append(line.strip());
        out.newLine(); out.append("        return ").append(GEOAPI_TO_WRAPPER.getOrDefault(type, type)).append(suffix).append(".wrap(geotools);");
        out.newLine(); out.append("    }");
        out.newLine();
    }

    /**
     * Generates the {@link Wrappers} class.
     *
     * @param args ignored.
     * @throws IOException if an I/O error occurred.
     */
    public static void main(String[] args) throws IOException {
        var generator = new WrappersGenerator();
        generator.run(Path.of("src/main/java/com/geomatys/geoapi/geotools"));
    }
}
