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

import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.datum.TemporalDatum;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class TemporalCRSFromGT extends CoordinateReferenceSystemFromGT<org.geotools.api.referencing.crs.TemporalCRS>
        implements TemporalCRS
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    TemporalCRSFromGT(final org.geotools.api.referencing.crs.TemporalCRS impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static TemporalCRS wrap(final org.geotools.api.referencing.crs.TemporalCRS impl) {
        switch (impl) {
            case null: return null;
            case TemporalCRS c: return c;
            case TemporalCRSToGT c: return c.impl;
            default: return new TemporalCRSFromGT(impl);
        }
    }

    @Override
    public TimeCS getCoordinateSystem() {
        return TimeCSFromGT.wrap(impl.getCoordinateSystem());
    }

    @Override
    public TemporalDatum getDatum() {
        return TemporalDatumFromGT.wrap(impl.getDatum());
    }
}
