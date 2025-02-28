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

import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class IdentifierFromGT<S extends org.geotools.api.metadata.Identifier>
        extends WrapperFromGT implements Identifier
{
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final S impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    IdentifierFromGT(final S impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Identifier wrap(final org.geotools.api.metadata.Identifier impl) {
        switch (impl) {
            case null: return null;
            case Identifier c: return c;
            case org.geotools.api.referencing.ReferenceIdentifier c: return new ReferenceIdentifierFromGT(c);
            default: return new IdentifierFromGT<>(impl);
        }
    }

    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public String getCode() {
        return impl.getCode();
    }

    @Override
    public Citation getAuthority() {
        return CitationFromGT.wrap(impl.getAuthority());
    }
}
