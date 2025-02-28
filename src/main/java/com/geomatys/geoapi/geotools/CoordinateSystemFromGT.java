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
 * @author Martin Desruisseaux (Geomatys)
 */
class CoordinateSystemFromGT extends IdentifiedObjectFromGT<org.geotools.api.referencing.cs.CoordinateSystem>
        implements CoordinateSystem
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    CoordinateSystemFromGT(final org.geotools.api.referencing.cs.CoordinateSystem impl) {
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
        switch (impl) {
            case null: return null;
            case CoordinateSystem c: return c;
            case org.geotools.api.referencing.cs.EllipsoidalCS c: return new EllipsoidalCSFromGT(c);
            case org.geotools.api.referencing.cs.SphericalCS   c: return new SphericalCSFromGT  (c);
            case org.geotools.api.referencing.cs.CartesianCS   c: return new CartesianCSFromGT  (c);
            case org.geotools.api.referencing.cs.AffineCS      c: return new AffineCSFromGT     (c);
            case org.geotools.api.referencing.cs.CylindricalCS c: return new CylindricalCSFromGT(c);
            case org.geotools.api.referencing.cs.PolarCS       c: return new PolarCSFromGT      (c);
            case org.geotools.api.referencing.cs.VerticalCS    c: return new VerticalCSFromGT   (c);
            case org.geotools.api.referencing.cs.LinearCS      c: return new LinearCSFromGT     (c);
            case org.geotools.api.referencing.cs.TimeCS        c: return new TimeCSFromGT       (c);
            case org.geotools.api.referencing.cs.UserDefinedCS c: return new UserDefinedCSFromGT(c);
            default: return new CoordinateSystemFromGT(impl);
        }
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
