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


/**
 * Base class of all wrappers from GeoAPI to GeoTools.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
abstract class WrapperToGT {
    /**
     * Creates a new wrapper.
     */
    WrapperToGT() {
    }

    /**
     * Returns the {@code org.opengis} instances on which to delegate the operations.
     *
     * @return the backing {@code org.opengisi} instance
     */
    abstract Object implementation();

    /**
     * {@return whether this wrapper is equal to the given object}.
     * Two wrappers are considered equal if they are of the same class and the wrapped GeoAPI instances are equal.
     *
     * @param obj the object to compare, or {@code null}
     */
    @Override
    public final boolean equals(final Object obj) {
        return (obj != null) && obj.getClass() == getClass() && implementation().equals(((WrapperToGT) obj).implementation());
    }

    /**
     * {@return a hash code value for this wrapper}.
     */
    @Override
    public final int hashCode() {
        return implementation().hashCode() ^ getClass().hashCode();
    }

    /**
     * {@return the string representation of the GeoAPI instance}.
     */
    @Override
    public final String toString() {
        return implementation().toString();
    }
}
