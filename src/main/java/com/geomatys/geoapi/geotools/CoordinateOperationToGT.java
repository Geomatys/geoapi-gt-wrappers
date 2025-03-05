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

import java.util.Collection;
import org.geotools.api.metadata.extent.Extent;
import org.geotools.api.metadata.quality.PositionalAccuracy;
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.api.referencing.operation.CoordinateOperation;
import org.geotools.api.referencing.operation.MathTransform;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class CoordinateOperationToGT<S extends org.opengis.referencing.operation.CoordinateOperation>
        extends IdentifiedObjectToGT<S> implements CoordinateOperation
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    CoordinateOperationToGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CoordinateOperation wrap(final org.opengis.referencing.operation.CoordinateOperation impl) {
        switch (impl) {
            case null: return null;
            case CoordinateOperation c: return c;
            case CoordinateOperationFromGT<?> c: return c.impl;
            case org.opengis.referencing.operation.SingleOperation c: return SingleOperationToGT.wrap(c);
            case org.opengis.referencing.operation.ConcatenatedOperation c: return new ConcatenatedOperationToGT(c);
            default: return new CoordinateOperationToGT<>(impl);
        }
    }

    @Override
    public CoordinateReferenceSystem getSourceCRS() {
        return CoordinateReferenceSystemToGT.wrap(impl.getSourceCRS());
    }

    @Override
    public CoordinateReferenceSystem getTargetCRS() {
        return CoordinateReferenceSystemToGT.wrap(impl.getTargetCRS());
    }

    @Override
    public String getOperationVersion() {
        return impl.getOperationVersion();
    }

    @Override
    public Collection<PositionalAccuracy> getCoordinateOperationAccuracy() {
        return wrap(impl.getCoordinateOperationAccuracy(), PositionalAccuracyToGT::wrap);
    }

    @Override
    public Extent getDomainOfValidity() {
        return ExtentToGT.wrap(impl.getDomainOfValidity());
    }

    @Override
    public InternationalString getScope() {
        return InternationalStringToGT.wrap(impl.getScope());
    }

    @Override
    public MathTransform getMathTransform() {
        return MathTransformToGT.wrap(impl.getMathTransform());
    }
}
