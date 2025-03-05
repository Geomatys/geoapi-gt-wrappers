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

import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.api.referencing.cs.CoordinateSystem;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class CoordinateReferenceSystemToGT<S extends org.opengis.referencing.crs.CoordinateReferenceSystem>
        extends ReferenceSystemToGT<S> implements CoordinateReferenceSystem
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    CoordinateReferenceSystemToGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CoordinateReferenceSystem wrap(final org.opengis.referencing.crs.CoordinateReferenceSystem impl) {
        switch (impl) {
            case null: return null;
            case CoordinateReferenceSystem c: return c;
            case org.opengis.referencing.crs.SingleCRS c: return SingleCRSToGT.wrap(c);
            case org.opengis.referencing.crs.CompoundCRS c: return new CompoundCRSToGT(c);
            default: return new CoordinateReferenceSystemToGT<>(impl);
        }
    }

    /**
     * {@return the GeoAPI implementation behind the given wrapper}.
     *
     * @param wrapper the wrapper from which to get the GeoAPI implementation.
     * @throws ClassCastException if the given value is not a wrapper for GeoAPI.
     */
    static org.opengis.referencing.crs.CoordinateReferenceSystem unwrap(CoordinateReferenceSystem wrapper) {
        switch (wrapper) {
            case null: return null;
            default: return ((CoordinateReferenceSystemToGT<?>) wrapper).impl;
        }
    }

    @Override
    public CoordinateSystem getCoordinateSystem() {
        return CoordinateSystemToGT.wrap(impl.getCoordinateSystem());
    }
}
