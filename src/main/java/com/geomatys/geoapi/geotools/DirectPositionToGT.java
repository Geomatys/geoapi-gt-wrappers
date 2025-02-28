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

import org.geotools.api.geometry.Position;
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;



/**
 * Adapter from GeoAPI to GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class DirectPositionToGT extends WrapperToGT implements Position {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.geometry.DirectPosition impl;

    /**
     * Creates a new wrapper for the given GeoAPI object.
     *
     * @param impl the GeoAPI object on which to delegate all methods
     */
    private DirectPositionToGT(final org.opengis.geometry.DirectPosition impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given object is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI object on which to delegate all methods
     * @return wrapper for the given instance
     */
    static Position wrap(final org.opengis.geometry.DirectPosition impl) {
        switch (impl) {
            case null: return null;
            case Position c: return c;
            case DirectPositionFromGT c: return c.impl;
            default: return new DirectPositionToGT(impl);
        }
    }

    /**
     * {@return the GeoAPI object on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return CoordinateReferenceSystemFromGT.unwrap(impl.getCoordinateReferenceSystem());
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
    public Position getDirectPosition() {
        return this;
    }
}
