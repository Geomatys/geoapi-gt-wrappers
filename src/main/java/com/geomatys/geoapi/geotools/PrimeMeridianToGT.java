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
import javax.measure.quantity.Angle;
import org.geotools.api.referencing.datum.PrimeMeridian;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class PrimeMeridianToGT extends IdentifiedObjectToGT<org.opengis.referencing.datum.PrimeMeridian>
        implements PrimeMeridian
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    PrimeMeridianToGT(final org.opengis.referencing.datum.PrimeMeridian impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static PrimeMeridian wrap(final org.opengis.referencing.datum.PrimeMeridian impl) {
        switch (impl) {
            case null: return null;
            case PrimeMeridian c: return c;
            default: return new PrimeMeridianToGT(impl);
        }
    }

    @Override
    public double getGreenwichLongitude() {
        return impl.getGreenwichLongitude();
    }

    @Override
    public Unit<Angle> getAngularUnit() {
        return impl.getAngularUnit();
    }
}
