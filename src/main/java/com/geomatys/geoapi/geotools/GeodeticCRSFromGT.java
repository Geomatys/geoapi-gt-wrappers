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

import org.opengis.referencing.crs.GeodeticCRS;
import org.opengis.referencing.datum.GeodeticDatum;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class GeodeticCRSFromGT<S extends org.geotools.api.referencing.crs.GeodeticCRS>
        extends SingleCRSFromGT<S> implements GeodeticCRS
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    GeodeticCRSFromGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static GeodeticCRS wrap(final org.geotools.api.referencing.crs.GeodeticCRS impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof GeodeticCRS) {
            var c = (GeodeticCRS) impl;
            return c;
        }
        if (impl instanceof GeodeticCRSToGT<?>) {
            var c = (GeodeticCRSToGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.geotools.api.referencing.crs.GeographicCRS) {
            var c = (org.geotools.api.referencing.crs.GeographicCRS) impl;
            return new GeographicCRSFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.crs.GeocentricCRS) {
            var c = (org.geotools.api.referencing.crs.GeocentricCRS) impl;
            return new GeocentricCRSFromGT(c);
        }
        return new GeodeticCRSFromGT<>(impl);
    }

    @Override
    public GeodeticDatum getDatum() {
        return GeodeticDatumFromGT.wrap(impl.getDatum());
    }
}
