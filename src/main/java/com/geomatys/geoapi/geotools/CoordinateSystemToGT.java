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
        switch (impl) {
            case null: return null;
            case CoordinateSystem c: return c;
            case CoordinateSystemFromGT<?> c: return c.impl;
            case org.opengis.referencing.cs.EllipsoidalCS c: return new EllipsoidalCSToGT(c);
            case org.opengis.referencing.cs.SphericalCS   c: return new SphericalCSToGT  (c);
            case org.opengis.referencing.cs.CartesianCS   c: return new CartesianCSToGT  (c);
            case org.opengis.referencing.cs.AffineCS      c: return new AffineCSToGT<>   (c);
            case org.opengis.referencing.cs.CylindricalCS c: return new CylindricalCSToGT(c);
            case org.opengis.referencing.cs.PolarCS       c: return new PolarCSToGT      (c);
            case org.opengis.referencing.cs.VerticalCS    c: return new VerticalCSToGT   (c);
            case org.opengis.referencing.cs.LinearCS      c: return new LinearCSToGT     (c);
            case org.opengis.referencing.cs.TimeCS        c: return new TimeCSToGT       (c);
            case org.opengis.referencing.cs.UserDefinedCS c: return new UserDefinedCSToGT(c);
            default: return new CoordinateSystemToGT<>(impl);
        }
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
