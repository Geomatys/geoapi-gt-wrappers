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

import java.util.Date;
import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.datum.Datum;
import org.opengis.util.InternationalString;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class DatumFromGT<S extends org.geotools.api.referencing.datum.Datum>
        extends IdentifiedObjectFromGT<S> implements Datum
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    DatumFromGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Datum wrap(final org.geotools.api.referencing.datum.Datum impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof Datum) {
            var c = (Datum) impl;
            return c;
        }
        if (impl instanceof DatumToGT<?>) {
            var c = (DatumToGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.geotools.api.referencing.datum.GeodeticDatum) {
            var c = (org.geotools.api.referencing.datum.GeodeticDatum) impl;
            return new GeodeticDatumFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.datum.VerticalDatum) {
            var c = (org.geotools.api.referencing.datum.VerticalDatum) impl;
            return new VerticalDatumFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.datum.TemporalDatum) {
            var c = (org.geotools.api.referencing.datum.TemporalDatum) impl;
            return new TemporalDatumFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.datum.EngineeringDatum) {
            var c = (org.geotools.api.referencing.datum.EngineeringDatum) impl;
            return new EngineeringDatumFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.datum.ImageDatum) {
            var c = (org.geotools.api.referencing.datum.ImageDatum) impl;
            return new ImageDatumFromGT(c);
        }
        return new DatumFromGT<>(impl);
    }

    @Override
    public InternationalString getAnchorPoint() {
        return InternationalStringFromGT.wrap(impl.getAnchorPoint());
    }

    @Override
    public Date getRealizationEpoch() {
        return impl.getRealizationEpoch();
    }

    @Override
    public Extent getDomainOfValidity() {
        return ExtentFromGT.wrap(impl.getDomainOfValidity());
    }

    @Override
    public InternationalString getScope() {
        return InternationalStringFromGT.wrap(impl.getScope());
    }
}
