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

import org.geotools.api.metadata.extent.GeographicBoundingBox;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class GeographicBoundingBoxToGT extends GeographicExtentToGT<org.opengis.metadata.extent.GeographicBoundingBox>
        implements GeographicBoundingBox
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    GeographicBoundingBoxToGT(final org.opengis.metadata.extent.GeographicBoundingBox impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static GeographicBoundingBox wrap(final org.opengis.metadata.extent.GeographicBoundingBox impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof GeographicBoundingBox) {
            var c = (GeographicBoundingBox) impl;
            return c;
        }
        if (impl instanceof GeographicBoundingBoxFromGT) {
            var c = (GeographicBoundingBoxFromGT) impl;
            return c.impl;
        }
        return new GeographicBoundingBoxToGT(impl);
    }

    @Override
    public double getWestBoundLongitude() {
        return impl.getWestBoundLongitude();
    }

    @Override
    public double getEastBoundLongitude() {
        return impl.getEastBoundLongitude();
    }

    @Override
    public double getSouthBoundLatitude() {
        return impl.getSouthBoundLatitude();
    }

    @Override
    public double getNorthBoundLatitude() {
        return impl.getNorthBoundLatitude();
    }
}
