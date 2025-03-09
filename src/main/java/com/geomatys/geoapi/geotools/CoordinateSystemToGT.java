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

import org.geotools.api.referencing.cs.CoordinateSystem;
import org.geotools.api.referencing.cs.CoordinateSystemAxis;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class CoordinateSystemToGT<S extends org.opengis.referencing.cs.CoordinateSystem>
        extends IdentifiedObjectToGT<S> implements CoordinateSystem
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    CoordinateSystemToGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CoordinateSystem wrap(final org.opengis.referencing.cs.CoordinateSystem impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof CoordinateSystem) {
            var c = (CoordinateSystem) impl;
            return c;
        }
        if (impl instanceof CoordinateSystemFromGT<?>) {
            var c = (CoordinateSystemFromGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.opengis.referencing.cs.EllipsoidalCS) {
            var c = (org.opengis.referencing.cs.EllipsoidalCS) impl;
            return new EllipsoidalCSToGT(c);
        }
        if (impl instanceof org.opengis.referencing.cs.SphericalCS) {
            var c = (org.opengis.referencing.cs.SphericalCS) impl;
            return new SphericalCSToGT  (c);
        }
        if (impl instanceof org.opengis.referencing.cs.CartesianCS) {
            var c = (org.opengis.referencing.cs.CartesianCS) impl;
            return new CartesianCSToGT  (c);
        }
        if (impl instanceof org.opengis.referencing.cs.AffineCS) {
            var c = (org.opengis.referencing.cs.AffineCS) impl;
            return new AffineCSToGT<>   (c);
        }
        if (impl instanceof org.opengis.referencing.cs.CylindricalCS) {
            var c = (org.opengis.referencing.cs.CylindricalCS) impl;
            return new CylindricalCSToGT(c);
        }
        if (impl instanceof org.opengis.referencing.cs.PolarCS) {
            var c = (org.opengis.referencing.cs.PolarCS) impl;
            return new PolarCSToGT      (c);
        }
        if (impl instanceof org.opengis.referencing.cs.VerticalCS) {
            var c = (org.opengis.referencing.cs.VerticalCS) impl;
            return new VerticalCSToGT   (c);
        }
        if (impl instanceof org.opengis.referencing.cs.LinearCS) {
            var c = (org.opengis.referencing.cs.LinearCS) impl;
            return new LinearCSToGT     (c);
        }
        if (impl instanceof org.opengis.referencing.cs.TimeCS) {
            var c = (org.opengis.referencing.cs.TimeCS) impl;
            return new TimeCSToGT       (c);
        }
        if (impl instanceof org.opengis.referencing.cs.UserDefinedCS) {
            var c = (org.opengis.referencing.cs.UserDefinedCS) impl;
            return new UserDefinedCSToGT(c);
        }
        return new CoordinateSystemToGT<>(impl);
    }

    @Override
    public int getDimension() {
        return impl.getDimension();
    }

    @Override
    public CoordinateSystemAxis getAxis(int i) throws IndexOutOfBoundsException {
        return CoordinateSystemAxisToGT.wrap(impl.getAxis(i));
    }
}
