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

import org.opengis.metadata.extent.VerticalExtent;
import org.opengis.referencing.crs.VerticalCRS;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class VerticalExtentFromGT extends WrapperFromGT implements VerticalExtent {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.metadata.extent.VerticalExtent impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private VerticalExtentFromGT(final org.geotools.api.metadata.extent.VerticalExtent impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static VerticalExtent wrap(final org.geotools.api.metadata.extent.VerticalExtent impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof VerticalExtent) {
            var c = (VerticalExtent) impl;
            return c;
        }
        if (impl instanceof VerticalExtentToGT) {
            var c = (VerticalExtentToGT) impl;
            return c.impl;
        }
        return new VerticalExtentFromGT(impl);
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public Double getMinimumValue() {
        return impl.getMinimumValue();
    }

    @Override
    public Double getMaximumValue() {
        return impl.getMaximumValue();
    }

    @Override
    public VerticalCRS getVerticalCRS() {
        return VerticalCRSFromGT.wrap(impl.getVerticalCRS());
    }
}
