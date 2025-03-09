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

import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class DirectPositionFromGT extends WrapperFromGT implements DirectPosition {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.geometry.Position impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private DirectPositionFromGT(final org.geotools.api.geometry.Position impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static DirectPosition wrap(final org.geotools.api.geometry.Position impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof DirectPosition) {
            var c = (DirectPosition) impl;
            return c;
        }
        if (impl instanceof DirectPositionToGT) {
            var c = (DirectPositionToGT) impl;
            return c.impl;
        }
        return new DirectPositionFromGT(impl);
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return CoordinateReferenceSystemFromGT.wrap(impl.getCoordinateReferenceSystem());
    }

    @Override
    public int getDimension() {
        return impl.getDimension();
    }

    @Override
    public double[] getCoordinate() {
        return impl.getCoordinate();
    }

    @Override
    public double getOrdinate(int dimension) {
        return impl.getOrdinate(dimension);
    }

    @Override
    public void setOrdinate(int dimension, double value) {
        impl.setOrdinate(dimension, value);
    }

    @Override
    public DirectPosition getDirectPosition() {
        return this;
    }
}
