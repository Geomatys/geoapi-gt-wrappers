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
        if (impl == null) {
            return null;
        }
        if (impl instanceof IdentifiedObject) {
            var c = (IdentifiedObject) impl;
            return c;
        }
        if (impl instanceof IdentifiedObjectFromGT<?>) {
            var c = (IdentifiedObjectFromGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.opengis.referencing.operation.CoordinateOperation) {
            var c = (org.opengis.referencing.operation.CoordinateOperation) impl;
            return CoordinateOperationToGT.wrap(c);
        }
        if (impl instanceof org.opengis.referencing.crs.CoordinateReferenceSystem) {
            var c = (org.opengis.referencing.crs.CoordinateReferenceSystem) impl;
            return CoordinateReferenceSystemToGT.wrap(c);
        }
        if (impl instanceof org.opengis.referencing.ReferenceSystem) {
            var c = (org.opengis.referencing.ReferenceSystem) impl;
            return new ReferenceSystemToGT<>(c);
        }
        if (impl instanceof org.opengis.referencing.cs.CoordinateSystem) {
            var c = (org.opengis.referencing.cs.CoordinateSystem) impl;
            return CoordinateSystemToGT.wrap(c);
        }
        if (impl instanceof org.opengis.referencing.cs.CoordinateSystemAxis) {
            var c = (org.opengis.referencing.cs.CoordinateSystemAxis) impl;
            return new CoordinateSystemAxisToGT(c);
        }
        if (impl instanceof org.opengis.referencing.datum.Datum) {
            var c = (org.opengis.referencing.datum.Datum) impl;
            return DatumToGT.wrap(c);
        }
        if (impl instanceof org.opengis.referencing.datum.Ellipsoid) {
            var c = (org.opengis.referencing.datum.Ellipsoid) impl;
            return new EllipsoidToGT(c);
        }
        if (impl instanceof org.opengis.referencing.datum.PrimeMeridian) {
            var c = (org.opengis.referencing.datum.PrimeMeridian) impl;
            return new PrimeMeridianToGT(c);
        }
        if (impl instanceof org.opengis.referencing.operation.OperationMethod) {
            var c = (org.opengis.referencing.operation.OperationMethod) impl;
            return new OperationMethodToGT(c);
        }
        if (impl instanceof org.opengis.parameter.GeneralParameterDescriptor) {
            var c = (org.opengis.parameter.GeneralParameterDescriptor) impl;
            return GeneralParameterDescriptorToGT.wrap(c);
        }
        return new IdentifiedObjectToGT<>(impl);
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
