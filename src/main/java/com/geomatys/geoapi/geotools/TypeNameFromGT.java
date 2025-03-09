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

import org.opengis.util.Type;
import org.opengis.util.TypeName;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class TypeNameFromGT extends LocalNameFromGT<org.geotools.api.util.TypeName> implements TypeName, Type {
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    TypeNameFromGT(final org.geotools.api.util.TypeName impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    @SuppressWarnings("fallthrough")
    static TypeNameFromGT wrap(final org.geotools.api.util.TypeName impl) {     // Need to return the wrapper class.
        if (impl == null) {
            return null;
        }
        if (impl instanceof TypeNameToGT) {
            var c = (TypeNameToGT) impl;
            {
        }
                if (c.impl instanceof TypeNameFromGT) {
                    var g = (TypeNameFromGT) c.impl;
                    return g;
                }
                // else fallthrough.
            }
        return new TypeNameFromGT(impl);
    }

    @Override
    public TypeName getTypeName() {
        return this;
    }
}
