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

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.GeneralDerivedCRS;
import org.opengis.referencing.operation.Conversion;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class GeneralDerivedCRSFromGT<S extends org.geotools.api.referencing.crs.GeneralDerivedCRS>
        extends SingleCRSFromGT<S> implements GeneralDerivedCRS
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    GeneralDerivedCRSFromGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static GeneralDerivedCRS wrap(final org.geotools.api.referencing.crs.GeneralDerivedCRS impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof GeneralDerivedCRS) {
            var c = (GeneralDerivedCRS) impl;
            return c;
        }
        if (impl instanceof GeneralDerivedCRSToGT<?>) {
            var c = (GeneralDerivedCRSToGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.geotools.api.referencing.crs.DerivedCRS) {
            var c = (org.geotools.api.referencing.crs.DerivedCRS) impl;
            return DerivedCRSFromGT.wrap(c);
        }
        return new GeneralDerivedCRSFromGT<>(impl);
    }

    @Override
    public CoordinateReferenceSystem getBaseCRS() {
        return CoordinateReferenceSystemFromGT.wrap(impl.getBaseCRS());
    }

    @Override
    public Conversion getConversionFromBase() {
        return ConversionFromGT.wrap(impl.getConversionFromBase());
    }
}
