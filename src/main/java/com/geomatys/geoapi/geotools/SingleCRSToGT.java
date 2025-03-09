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
        if (impl == null) {
            return null;
        }
        if (impl instanceof SingleCRS) {
            var c = (SingleCRS) impl;
            return c;
        }
        if (impl instanceof SingleCRSFromGT<?>) {
            var c = (SingleCRSFromGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.opengis.referencing.crs.ProjectedCRS) {
            var c = (org.opengis.referencing.crs.ProjectedCRS) impl;
            return new ProjectedCRSToGT(c);
        }
        if (impl instanceof org.opengis.referencing.crs.GeneralDerivedCRS) {
            var c = (org.opengis.referencing.crs.GeneralDerivedCRS) impl;
            return GeneralDerivedCRSToGT.wrap(c);
        }
        if (impl instanceof org.opengis.referencing.crs.GeodeticCRS) {
            var c = (org.opengis.referencing.crs.GeodeticCRS) impl;
            return GeodeticCRSToGT.wrap(c);
        }
        if (impl instanceof org.opengis.referencing.crs.VerticalCRS) {
            var c = (org.opengis.referencing.crs.VerticalCRS) impl;
            return new VerticalCRSToGT(c);
        }
        if (impl instanceof org.opengis.referencing.crs.TemporalCRS) {
            var c = (org.opengis.referencing.crs.TemporalCRS) impl;
            return new TemporalCRSToGT(c);
        }
        if (impl instanceof org.opengis.referencing.crs.EngineeringCRS) {
            var c = (org.opengis.referencing.crs.EngineeringCRS) impl;
            return new EngineeringCRSToGT(c);
        }
        if (impl instanceof org.opengis.referencing.crs.ImageCRS) {
            var c = (org.opengis.referencing.crs.ImageCRS) impl;
            return new ImageCRSToGT(c);
        }
        return new SingleCRSToGT<>(impl);
    }

    @Override
    public Datum getDatum() {
        return DatumToGT.wrap(impl.getDatum());
    }
}
