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

import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class CoordinateSystemFromGT<S extends org.geotools.api.referencing.cs.CoordinateSystem>
        extends IdentifiedObjectFromGT<S> implements CoordinateSystem
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    CoordinateSystemFromGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CoordinateSystem wrap(final org.geotools.api.referencing.cs.CoordinateSystem impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof CoordinateSystem) {
            var c = (CoordinateSystem) impl;
            return c;
        }
        if (impl instanceof CoordinateSystemToGT<?>) {
            var c = (CoordinateSystemToGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.geotools.api.referencing.cs.EllipsoidalCS) {
            var c = (org.geotools.api.referencing.cs.EllipsoidalCS) impl;
            return new EllipsoidalCSFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.cs.SphericalCS) {
            var c = (org.geotools.api.referencing.cs.SphericalCS) impl;
            return new SphericalCSFromGT  (c);
        }
        if (impl instanceof org.geotools.api.referencing.cs.CartesianCS) {
            var c = (org.geotools.api.referencing.cs.CartesianCS) impl;
            return new CartesianCSFromGT  (c);
        }
        if (impl instanceof org.geotools.api.referencing.cs.AffineCS) {
            var c = (org.geotools.api.referencing.cs.AffineCS) impl;
            return new AffineCSFromGT<>   (c);
        }
        if (impl instanceof org.geotools.api.referencing.cs.CylindricalCS) {
            var c = (org.geotools.api.referencing.cs.CylindricalCS) impl;
            return new CylindricalCSFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.cs.PolarCS) {
            var c = (org.geotools.api.referencing.cs.PolarCS) impl;
            return new PolarCSFromGT      (c);
        }
        if (impl instanceof org.geotools.api.referencing.cs.VerticalCS) {
            var c = (org.geotools.api.referencing.cs.VerticalCS) impl;
            return new VerticalCSFromGT   (c);
        }
        if (impl instanceof org.geotools.api.referencing.cs.LinearCS) {
            var c = (org.geotools.api.referencing.cs.LinearCS) impl;
            return new LinearCSFromGT     (c);
        }
        if (impl instanceof org.geotools.api.referencing.cs.TimeCS) {
            var c = (org.geotools.api.referencing.cs.TimeCS) impl;
            return new TimeCSFromGT       (c);
        }
        if (impl instanceof org.geotools.api.referencing.cs.UserDefinedCS) {
            var c = (org.geotools.api.referencing.cs.UserDefinedCS) impl;
            return new UserDefinedCSFromGT(c);
        }
        return new CoordinateSystemFromGT<>(impl);
    }

    @Override
    public int getDimension() {
        return impl.getDimension();
    }

    @Override
    public CoordinateSystemAxis getAxis(int i) throws IndexOutOfBoundsException {
        return CoordinateSystemAxisFromGT.wrap(impl.getAxis(i));
    }
}
