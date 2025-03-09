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

import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.referencing.operation.SingleOperation;
import org.geotools.api.referencing.operation.Operation;    // Specific to GeoTools API.


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class SingleOperationFromGT<S extends org.geotools.api.referencing.operation.SingleOperation>
        extends CoordinateOperationFromGT<S> implements SingleOperation
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     * This given object <em>should</em> implement {@link Operation}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    SingleOperationFromGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * <h4>GeoTools API particularity</h4>
     * While this method accepts {@code SingleOperation} for compatibility with
     * {@code ConcatenatedOperation.getOperations()}, the given object <em>should</em>
     * be restricted to instances of the {@link Operation} subtype when possible.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static SingleOperation wrap(final org.geotools.api.referencing.operation.SingleOperation impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof SingleOperation) {
            var c = (SingleOperation) impl;
            return c;
        }
        if (impl instanceof SingleOperationToGT<?>) {
            var c = (SingleOperationToGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.geotools.api.referencing.operation.Transformation) {
            var c = (org.geotools.api.referencing.operation.Transformation) impl;
            return new TransformationFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.operation.Conversion) {
            var c = (org.geotools.api.referencing.operation.Conversion) impl;
            return ConversionFromGT.wrap(c);
        }
        return new SingleOperationFromGT<>(impl);
    }

    /**
     * If the GeoTools object implements {@link Operation}, returns a wrapper for the operation method.
     * Otherwise returns {@code null}. The latter case is illegal in <abbr>ISO</abbr> 19111 because this
     * property is mandatory. However, GeoTools API introduces the concept of {@code SingleOperation}
     * without parameters, which does not exist in <abbr>ISO</abbr> 19111.
     */
    @Override
    public OperationMethod getMethod() {
        if (impl instanceof Operation) {
            var op = (Operation) impl;
            return OperationMethodFromGT.wrap(op.getMethod());
        }
        return null;
    }

    @Override
    public ParameterValueGroup getParameterValues() {
        if (impl instanceof Operation) {
            var op = (Operation) impl;
            return ParameterValueGroupFromGT.wrap(op.getParameterValues());
        }
        return null;
    }
}
