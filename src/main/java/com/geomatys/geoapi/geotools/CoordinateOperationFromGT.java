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
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.quality.PositionalAccuracy;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.util.InternationalString;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class CoordinateOperationFromGT<S extends org.geotools.api.referencing.operation.CoordinateOperation>
        extends IdentifiedObjectFromGT<S> implements CoordinateOperation
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    CoordinateOperationFromGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CoordinateOperation wrap(final org.geotools.api.referencing.operation.CoordinateOperation impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof CoordinateOperation) {
            var c = (CoordinateOperation) impl;
            return c;
        }
        if (impl instanceof CoordinateOperationToGT<?>) {
            var c = (CoordinateOperationToGT<?>) impl;
            return c.impl;
        }
            // The following case intentionally excludes the GeoTools `SingleOperation` interface.
        if (impl instanceof org.geotools.api.referencing.operation.Operation) {
            var c = (org.geotools.api.referencing.operation.Operation) impl;
            return SingleOperationFromGT.wrap(c);
        }
        if (impl instanceof org.geotools.api.referencing.operation.ConcatenatedOperation) {
            var c = (org.geotools.api.referencing.operation.ConcatenatedOperation) impl;
            return new ConcatenatedOperationFromGT(c);
        }
        return new CoordinateOperationFromGT<>(impl);
    }

    @Override
    public CoordinateReferenceSystem getSourceCRS() {
        return CoordinateReferenceSystemFromGT.wrap(impl.getSourceCRS());
    }

    @Override
    public CoordinateReferenceSystem getTargetCRS() {
        return CoordinateReferenceSystemFromGT.wrap(impl.getTargetCRS());
    }

    @Override
    public String getOperationVersion() {
        return impl.getOperationVersion();
    }

    @Override
    public Collection<PositionalAccuracy> getCoordinateOperationAccuracy() {
        return wrap(impl.getCoordinateOperationAccuracy(), PositionalAccuracyFromGT::wrap);
    }

    @Override
    public Extent getDomainOfValidity() {
        return ExtentFromGT.wrap(impl.getDomainOfValidity());
    }

    @Override
    public InternationalString getScope() {
        return InternationalStringFromGT.wrap(impl.getScope());
    }

    @Override
    public MathTransform getMathTransform() {
        return MathTransformFromGT.wrap(impl.getMathTransform());
    }
}
