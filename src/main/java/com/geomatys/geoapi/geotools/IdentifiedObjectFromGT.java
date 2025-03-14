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
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class IdentifiedObjectFromGT<S extends org.geotools.api.referencing.IdentifiedObject>
        extends WrapperFromGT implements IdentifiedObject
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
    IdentifiedObjectFromGT(final S impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static IdentifiedObject wrap(final org.geotools.api.referencing.IdentifiedObject impl) {
        switch (impl) {
            case null: return null;
            case IdentifiedObject c: return c;
            case IdentifiedObjectToGT<?> c: return c.impl;
            case org.geotools.api.referencing.operation.CoordinateOperation c: return CoordinateOperationFromGT.wrap(c);
            case org.geotools.api.referencing.crs.CoordinateReferenceSystem c: return CoordinateReferenceSystemFromGT.wrap(c);
            case org.geotools.api.referencing.ReferenceSystem c: return new ReferenceSystemFromGT<>(c);
            case org.geotools.api.referencing.cs.CoordinateSystem c: return CoordinateSystemFromGT.wrap(c);
            case org.geotools.api.referencing.cs.CoordinateSystemAxis c: return new CoordinateSystemAxisFromGT(c);
            case org.geotools.api.referencing.datum.Datum c: return DatumFromGT.wrap(c);
            case org.geotools.api.referencing.datum.Ellipsoid c: return new EllipsoidFromGT(c);
            case org.geotools.api.referencing.datum.PrimeMeridian c: return new PrimeMeridianFromGT(c);
            case org.geotools.api.referencing.operation.OperationMethod c: return new OperationMethodFromGT(c);
            case org.geotools.api.parameter.GeneralParameterDescriptor c: return GeneralParameterDescriptorFromGT.wrap(c);
            default: return new IdentifiedObjectFromGT<>(impl);
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
    public ReferenceIdentifier getName() {
        return ReferenceIdentifierFromGT.wrap(impl.getName());
    }

    @Override
    public Collection<GenericName> getAlias() {
        return wrap(impl.getAlias(), GenericNameFromGT::wrap);
    }

    @Override
    public Set<ReferenceIdentifier> getIdentifiers() {
        return wrap(impl.getIdentifiers(), ReferenceIdentifierFromGT::wrap);
    }

    @Override
    public InternationalString getRemarks() {
        return InternationalStringFromGT.wrap(impl.getRemarks());
    }

    @Override
    public String toWKT() throws UnsupportedOperationException {
        return impl.toWKT();
    }
}
