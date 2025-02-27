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

import java.util.Set;
import java.util.Collection;
import java.util.function.Function;
import org.opengis.util.CodeList;


/**
 * Base class of all wrappers defined in this package.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
abstract class Wrapper {
    /**
     * Creates a new wrapper.
     */
    Wrapper() {
    }

    /**
     * Returns the {@code org.geotools.api} instances used an the implementation.
     *
     * @return the backing {@code org.geotools.api} instance
     */
    abstract Object implementation();

    /**
     * Returns a collection with all elements of the given collection wrapped behind GeoAPI interface.
     * If the given collection is {@code null}, then this method returns {@code null}.
     *
     * @param <S>     the GeoTools type.
     * @param <T>     the GeoAPI interface.
     * @param impl    the collection of GeoTools objects.
     * @param wrapper the {@code wrap(…)} function to invoke for wrapping each element of the given list.
     * @return a list of wrappers around the GeoTools object.
     */
    static <S,T> Collection<T> wrap(
            final Collection<S> impl,
            final Function<S,T> wrapper)
    {
        return (impl == null) ? null : impl.stream().map(wrapper).toList();
    }

    /**
     * Returns the GeoAPI code list value of the same name than the given GeoTools code list value.
     * If the given code list value is {@code null}, then this method returns {@code null}.
     *
     * @param <T>     the GeoAPI code list type.
     * @param impl    the GeoTools code list value.
     * @param wrapper the {@code valueOf(…)} function to invoke for creating a GeoAPI code list value from a name.
     * @return the GeoAPI code list value.
     */
    static <T extends CodeList<T>> T wrap(
            final org.geotools.api.util.CodeList<?> impl,
            final Function<String,T> wrapper)
    {
        return (impl == null) ? null : wrapper.apply(impl.name());
    }

    /**
     * Returns the GeoAPI code list values of the same names than the given GeoTools code list values.
     * If the given collection is {@code null}, then this method returns {@code null}.
     *
     * @param <T>     the GeoAPI code list type.
     * @param impl    the GeoTools code list value.
     * @param wrapper the {@code valueOf(…)} function to invoke for creating a GeoAPI code list value from a name.
     * @return the GeoAPI code list values.
     */
    static <T extends CodeList<T>> Collection<T> wrapAll(
            final Collection<? extends org.geotools.api.util.CodeList<?>> impl,
            final Function<String,T> wrapper)
    {
        return wrap(impl, wrapper.compose(org.geotools.api.util.CodeList::name));
    }

    /**
     * Converts a list to a set.
     *
     * @param <T> type of elements in the collections.
     * @param elements the elements of the list to copy.
     * @return the given list as a set.
     */
    static <T> Set<T> toSet(final Collection<T> elements) {
        return (elements == null) ? null : Set.copyOf(elements);
    }

    /**
     * {@return whether this wrapper is equal to the given object}.
     * Two wrappers are considered equal if they are of the same class and wrap the equal implementations.
     *
     * @param obj the object to compare, or {@code null}
     */
    @Override
    public final boolean equals(final Object obj) {
        return (obj != null) && obj.getClass() == getClass() && implementation().equals(((Wrapper) obj).implementation());
    }

    /**
     * {@return a hash code value for this wrapper}.
     */
    @Override
    public final int hashCode() {
        return implementation().hashCode() ^ getClass().hashCode();
    }

    /**
     * {@return the string representation of the implementation}.
     */
    @Override
    public final String toString() {
        return implementation().toString();
    }
}
