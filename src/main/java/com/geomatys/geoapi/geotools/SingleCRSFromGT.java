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

import org.opengis.referencing.crs.SingleCRS;
import org.opengis.referencing.datum.Datum;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class SingleCRSFromGT<S extends org.geotools.api.referencing.crs.SingleCRS>
        extends CoordinateReferenceSystemFromGT<S> implements SingleCRS
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    SingleCRSFromGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static SingleCRS wrap(final org.geotools.api.referencing.crs.SingleCRS impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof SingleCRS) {
            var c = (SingleCRS) impl;
            return c;
        }
        if (impl instanceof SingleCRSToGT<?>) {
            var c = (SingleCRSToGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.geotools.api.referencing.crs.ProjectedCRS) {
            var c = (org.geotools.api.referencing.crs.ProjectedCRS) impl;
            return new ProjectedCRSFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.crs.GeneralDerivedCRS) {
            var c = (org.geotools.api.referencing.crs.GeneralDerivedCRS) impl;
            return GeneralDerivedCRSFromGT.wrap(c);
        }
        if (impl instanceof org.geotools.api.referencing.crs.GeodeticCRS) {
            var c = (org.geotools.api.referencing.crs.GeodeticCRS) impl;
            return GeodeticCRSFromGT.wrap(c);
        }
        if (impl instanceof org.geotools.api.referencing.crs.VerticalCRS) {
            var c = (org.geotools.api.referencing.crs.VerticalCRS) impl;
            return new VerticalCRSFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.crs.TemporalCRS) {
            var c = (org.geotools.api.referencing.crs.TemporalCRS) impl;
            return new TemporalCRSFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.crs.EngineeringCRS) {
            var c = (org.geotools.api.referencing.crs.EngineeringCRS) impl;
            return new EngineeringCRSFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.crs.ImageCRS) {
            var c = (org.geotools.api.referencing.crs.ImageCRS) impl;
            return new ImageCRSFromGT(c);
        }
        return new SingleCRSFromGT<>(impl);
    }

    @Override
    public Datum getDatum() {
        return DatumFromGT.wrap(impl.getDatum());
    }
}
