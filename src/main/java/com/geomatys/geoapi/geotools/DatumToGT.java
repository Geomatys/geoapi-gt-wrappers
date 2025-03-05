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
import org.geotools.api.metadata.extent.Extent;
import org.geotools.api.referencing.datum.Datum;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class DatumToGT<S extends org.opengis.referencing.datum.Datum>
        extends IdentifiedObjectToGT<S> implements Datum
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    DatumToGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Datum wrap(final org.opengis.referencing.datum.Datum impl) {
        switch (impl) {
            case null: return null;
            case Datum c: return c;
            case org.opengis.referencing.datum.GeodeticDatum    c: return new GeodeticDatumToGT(c);
            case org.opengis.referencing.datum.VerticalDatum    c: return new VerticalDatumToGT(c);
            case org.opengis.referencing.datum.TemporalDatum    c: return new TemporalDatumToGT(c);
            case org.opengis.referencing.datum.EngineeringDatum c: return new EngineeringDatumToGT(c);
            case org.opengis.referencing.datum.ImageDatum       c: return new ImageDatumToGT(c);
            default: return new DatumToGT<>(impl);
        }
    }

    @Override
    public InternationalString getAnchorPoint() {
        return InternationalStringToGT.wrap(impl.getAnchorPoint());
    }

    @Override
    public Date getRealizationEpoch() {
        return impl.getRealizationEpoch();
    }

    @Override
    public Extent getDomainOfValidity() {
        return ExtentToGT.wrap(impl.getDomainOfValidity());
    }

    @Override
    public InternationalString getScope() {
        return InternationalStringToGT.wrap(impl.getScope());
    }
}
