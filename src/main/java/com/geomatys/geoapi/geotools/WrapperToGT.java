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

import java.util.Collection;
import java.util.function.Function;
import org.geotools.api.util.CodeList;


/**
 * Base class of all wrappers from GeoAPI to GeoTools.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
abstract class WrapperToGT extends Wrapper {
    /**
     * Creates a new wrapper.
     */
    WrapperToGT() {
    }

    /**
     * Returns the GeoTools code list value of the same name than the given GeoAPI code list value.
     * If the given code list value is {@code null}, then this method returns {@code null}.
     *
     * @param <T>     the GeoTools code list type.
     * @param impl    the GeoAPI code list value.
     * @param wrapper the {@code valueOf(…)} function to invoke for creating a GeoTools code list value from a name.
     * @return the GeoTools code list value.
     */
    static <T extends CodeList<T>> T wrap(
            final org.opengis.util.CodeList<?> impl,
            final Function<String,T> wrapper)
    {
        return (impl == null) ? null : wrapper.apply(impl.name());
    }

    /**
     * Returns the GeoTools code list values of the same names than the given GeoAPI code list values.
     * If the given collection is {@code null}, then this method returns {@code null}.
     *
     * @param <T>     the GeoTools code list type.
     * @param impl    the GeoAPI code list value.
     * @param wrapper the {@code valueOf(…)} function to invoke for creating a GeoTools code list value from a name.
     * @return the GeoTools code list values.
     */
    static <T extends CodeList<T>> Collection<T> wrapAll(
            final Collection<? extends org.opengis.util.CodeList<?>> impl,
            final Function<String,T> wrapper)
    {
        return wrap(impl, wrapper.compose(org.opengis.util.CodeList::name));
    }
}
