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
import org.geotools.api.referencing.datum.Ellipsoid;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class EllipsoidToGT extends IdentifiedObjectToGT<org.opengis.referencing.datum.Ellipsoid>
        implements Ellipsoid
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    EllipsoidToGT(final org.opengis.referencing.datum.Ellipsoid impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Ellipsoid wrap(final org.opengis.referencing.datum.Ellipsoid impl) {
        switch (impl) {
            case null: return null;
            case Ellipsoid c: return c;
            case EllipsoidFromGT c: return c.impl;
            default: return new EllipsoidToGT(impl);
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
