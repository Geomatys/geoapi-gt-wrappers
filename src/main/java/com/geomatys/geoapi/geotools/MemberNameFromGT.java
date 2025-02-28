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

import org.opengis.util.MemberName;
import org.opengis.util.TypeName;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class MemberNameFromGT extends LocalNameFromGT<org.geotools.api.util.MemberName> implements MemberName {
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    MemberNameFromGT(final org.geotools.api.util.MemberName impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static MemberName wrap(final org.geotools.api.util.MemberName impl) {
        return (impl == null) ? null : new MemberNameFromGT(impl);
    }

    /**
     * {@return the GeoTools implementation behind the given wrapper}.
     *
     * @param wrapper the wrapper from which to get the GeoTools implementation.
     * @throws ClassCastException if the given value is not a wrapper for GeoTools.
     */
    static org.geotools.api.util.MemberName unwrap(final MemberName wrapper) {
        switch (wrapper) {
            case null: return null;
            default: return ((MemberNameFromGT) wrapper).impl;
        }
    }

    @Override
    public TypeName getAttributeType() {
        return TypeNameFromGT.wrap(impl.getAttributeType());
    }
}
