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

import org.geotools.api.referencing.crs.TemporalCRS;
import org.geotools.api.referencing.cs.TimeCS;
import org.geotools.api.referencing.datum.TemporalDatum;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class TemporalCRSToGT extends CoordinateReferenceSystemToGT<org.opengis.referencing.crs.TemporalCRS>
        implements TemporalCRS
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    TemporalCRSToGT(final org.opengis.referencing.crs.TemporalCRS impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static TemporalCRS wrap(final org.opengis.referencing.crs.TemporalCRS impl) {
        switch (impl) {
            case null: return null;
            case TemporalCRS c: return c;
            case TemporalCRSFromGT c: return c.impl;
            default: return new TemporalCRSToGT(impl);
        }
    }

    @Override
    public TimeCS getCoordinateSystem() {
        return TimeCSToGT.wrap(impl.getCoordinateSystem());
    }

    @Override
    public TemporalDatum getDatum() {
        return TemporalDatumToGT.wrap(impl.getDatum());
    }
}
