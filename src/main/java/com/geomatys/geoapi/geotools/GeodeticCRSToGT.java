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

import org.geotools.api.referencing.crs.GeodeticCRS;
import org.geotools.api.referencing.datum.GeodeticDatum;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class GeodeticCRSToGT<S extends org.opengis.referencing.crs.GeodeticCRS>
        extends SingleCRSToGT<S> implements GeodeticCRS
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    GeodeticCRSToGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static GeodeticCRS wrap(final org.opengis.referencing.crs.GeodeticCRS impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof GeodeticCRS) {
            var c = (GeodeticCRS) impl;
            return c;
        }
        if (impl instanceof GeodeticCRSFromGT<?>) {
            var c = (GeodeticCRSFromGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.opengis.referencing.crs.GeographicCRS) {
            var c = (org.opengis.referencing.crs.GeographicCRS) impl;
            return new GeographicCRSToGT(c);
        }
        if (impl instanceof org.opengis.referencing.crs.GeocentricCRS) {
            var c = (org.opengis.referencing.crs.GeocentricCRS) impl;
            return new GeocentricCRSToGT(c);
        }
        return new GeodeticCRSToGT<>(impl);
    }

    @Override
    public GeodeticDatum getDatum() {
        return GeodeticDatumToGT.wrap(impl.getDatum());
    }
}
