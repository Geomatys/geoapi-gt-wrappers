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

import javax.measure.Unit;
import javax.measure.quantity.Length;
import org.opengis.referencing.datum.Ellipsoid;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class EllipsoidFromGT extends IdentifiedObjectFromGT<org.geotools.api.referencing.datum.Ellipsoid>
        implements Ellipsoid
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    EllipsoidFromGT(final org.geotools.api.referencing.datum.Ellipsoid impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Ellipsoid wrap(final org.geotools.api.referencing.datum.Ellipsoid impl) {
        switch (impl) {
            case null: return null;
            case Ellipsoid c: return c;
            case EllipsoidToGT c: return c.impl;
            default: return new EllipsoidFromGT(impl);
        }
    }

    @Override
    public Unit<Length> getAxisUnit() {
        return impl.getAxisUnit();
    }

    @Override
    public double getSemiMajorAxis() {
        return impl.getSemiMajorAxis();
    }

    @Override
    public double getSemiMinorAxis() {
        return impl.getSemiMinorAxis();
    }

    @Override
    public double getInverseFlattening() {
        return impl.getInverseFlattening();
    }

    @Override
    public boolean isIvfDefinitive() {
        return impl.isIvfDefinitive();
    }

    @Override
    public boolean isSphere() {
        return impl.isSphere();
    }
}
