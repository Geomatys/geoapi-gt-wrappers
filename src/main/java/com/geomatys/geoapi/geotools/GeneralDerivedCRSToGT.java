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

import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.api.referencing.crs.GeneralDerivedCRS;
import org.geotools.api.referencing.operation.Conversion;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class GeneralDerivedCRSToGT<S extends org.opengis.referencing.crs.GeneralDerivedCRS>
        extends SingleCRSToGT<S> implements GeneralDerivedCRS
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    GeneralDerivedCRSToGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static GeneralDerivedCRS wrap(final org.opengis.referencing.crs.GeneralDerivedCRS impl) {
        switch (impl) {
            case null: return null;
            case GeneralDerivedCRS c: return c;
            case GeneralDerivedCRSFromGT<?> c: return c.impl;
            case org.opengis.referencing.crs.DerivedCRS c: return DerivedCRSToGT.wrap(c);
            default: return new GeneralDerivedCRSToGT<>(impl);
        }
    }

    @Override
    public CoordinateReferenceSystem getBaseCRS() {
        return CoordinateReferenceSystemToGT.wrap(impl.getBaseCRS());
    }

    @Override
    public Conversion getConversionFromBase() {
        return ConversionToGT.wrap(impl.getConversionFromBase());
    }
}
