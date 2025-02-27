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
import java.util.Set;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class IdentifiedObject<S extends org.geotools.api.referencing.IdentifiedObject> extends Wrapper
        implements org.opengis.referencing.IdentifiedObject
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
    IdentifiedObject(final S impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static org.opengis.referencing.IdentifiedObject wrap(final org.geotools.api.referencing.IdentifiedObject impl) {
        switch (impl) {
            case null: return null;
            case org.geotools.api.referencing.cs.CoordinateSystemAxis c: return new Axis(c);
            default: return new IdentifiedObject<>(impl);
        }
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public org.opengis.referencing.ReferenceIdentifier getName() {
        return ReferenceIdentifier.wrap(impl.getName());
    }

    @Override
    public Collection<org.opengis.util.GenericName> getAlias() {
        impl.getAlias();
        return null;    // TODO
    }

    @Override
    public Set<org.opengis.referencing.ReferenceIdentifier> getIdentifiers() {
        return toSet(wrap(impl.getIdentifiers(), ReferenceIdentifier::wrap));
    }

    @Override
    public org.opengis.util.InternationalString getRemarks() {
        return InternationalString.wrap(impl.getRemarks());
    }

    @Override
    public String toWKT() throws UnsupportedOperationException {
        return impl.toWKT();
    }
}
