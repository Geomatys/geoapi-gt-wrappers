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
import org.geotools.api.referencing.cs.AxisDirection;
import org.geotools.api.referencing.cs.CoordinateSystemAxis;
import org.geotools.api.referencing.cs.RangeMeaning;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CoordinateSystemAxisToGT extends IdentifiedObjectToGT<org.opengis.referencing.cs.CoordinateSystemAxis>
        implements CoordinateSystemAxis
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    CoordinateSystemAxisToGT(final org.opengis.referencing.cs.CoordinateSystemAxis impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CoordinateSystemAxis wrap(final org.opengis.referencing.cs.CoordinateSystemAxis impl) {
        switch (impl) {
            case null: return null;
            case CoordinateSystemAxis c: return c;
            case CoordinateSystemAxisFromGT c: return c.impl;
            default: return new CoordinateSystemAxisToGT(impl);
        }
    }

    @Override
    public String getAbbreviation() {
        return impl.getAbbreviation();
    }

    @Override
    public AxisDirection getDirection() {
        return wrap(impl.getDirection(), AxisDirection::valueOf);
    }

    @Override
    public double getMinimumValue() {
        return impl.getMinimumValue();
    }

    @Override
    public double getMaximumValue() {
        return impl.getMaximumValue();
    }

    @Override
    public RangeMeaning getRangeMeaning() {
        return wrap(impl.getRangeMeaning(), RangeMeaning::valueOf);
    }

    @Override
    public Unit<?> getUnit() {
        return impl.getUnit();
    }
}
