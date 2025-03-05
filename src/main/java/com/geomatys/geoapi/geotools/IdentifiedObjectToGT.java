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
import org.geotools.api.referencing.IdentifiedObject;
import org.geotools.api.referencing.ReferenceIdentifier;
import org.geotools.api.util.GenericName;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class IdentifiedObjectToGT<S extends org.opengis.referencing.IdentifiedObject>
        extends WrapperToGT implements IdentifiedObject
{
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final S impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    IdentifiedObjectToGT(final S impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static IdentifiedObject wrap(final org.opengis.referencing.IdentifiedObject impl) {
        switch (impl) {
            case null: return null;
            case IdentifiedObject c: return c;
            case IdentifiedObjectFromGT<?> c: return c.impl;
            case org.opengis.referencing.operation.CoordinateOperation c: return CoordinateOperationToGT.wrap(c);
            case org.opengis.referencing.crs.CoordinateReferenceSystem c: return CoordinateReferenceSystemToGT.wrap(c);
            case org.opengis.referencing.ReferenceSystem c: return new ReferenceSystemToGT<>(c);
            case org.opengis.referencing.cs.CoordinateSystem c: return CoordinateSystemToGT.wrap(c);
            case org.opengis.referencing.cs.CoordinateSystemAxis c: return new CoordinateSystemAxisToGT(c);
            case org.opengis.referencing.datum.Datum c: return DatumToGT.wrap(c);
            case org.opengis.referencing.datum.Ellipsoid c: return new EllipsoidToGT(c);
            case org.opengis.referencing.datum.PrimeMeridian c: return new PrimeMeridianToGT(c);
            case org.opengis.referencing.operation.OperationMethod c: return new OperationMethodToGT(c);
            case org.opengis.parameter.GeneralParameterDescriptor c: return GeneralParameterDescriptorToGT.wrap(c);
            default: return new IdentifiedObjectToGT<>(impl);
        }
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public ReferenceIdentifier getName() {
        return ReferenceIdentifierToGT.wrap(impl.getName());
    }

    @Override
    public Collection<GenericName> getAlias() {
        return wrap(impl.getAlias(), GenericNameToGT::wrap);
    }

    @Override
    public Set<ReferenceIdentifier> getIdentifiers() {
        return wrap(impl.getIdentifiers(), ReferenceIdentifierToGT::wrap);
    }

    @Override
    public InternationalString getRemarks() {
        return InternationalStringToGT.wrap(impl.getRemarks());
    }

    @Override
    public String toWKT() throws UnsupportedOperationException {
        return impl.toWKT();
    }
}
