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

import org.geotools.api.referencing.crs.SingleCRS;
import org.geotools.api.referencing.datum.Datum;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class SingleCRSToGT<S extends org.opengis.referencing.crs.SingleCRS>
        extends CoordinateReferenceSystemToGT<S> implements SingleCRS
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    SingleCRSToGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static SingleCRS wrap(final org.opengis.referencing.crs.SingleCRS impl) {
        switch (impl) {
            case null: return null;
            case SingleCRS c: return c;
            case SingleCRSFromGT<?> c: return c.impl;
            case org.opengis.referencing.crs.ProjectedCRS      c: return new ProjectedCRSToGT(c);
            case org.opengis.referencing.crs.GeneralDerivedCRS c: return GeneralDerivedCRSToGT.wrap(c);
            case org.opengis.referencing.crs.GeodeticCRS       c: return GeodeticCRSToGT.wrap(c);
            case org.opengis.referencing.crs.VerticalCRS       c: return new VerticalCRSToGT(c);
            case org.opengis.referencing.crs.TemporalCRS       c: return new TemporalCRSToGT(c);
            case org.opengis.referencing.crs.EngineeringCRS    c: return new EngineeringCRSToGT(c);
            case org.opengis.referencing.crs.ImageCRS          c: return new ImageCRSToGT(c);
            default: return new SingleCRSToGT<>(impl);
        }
    }

    @Override
    public Datum getDatum() {
        return DatumToGT.wrap(impl.getDatum());
    }
}
