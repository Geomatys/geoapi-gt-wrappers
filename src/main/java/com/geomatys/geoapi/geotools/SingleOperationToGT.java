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

import org.geotools.api.parameter.ParameterValueGroup;
import org.geotools.api.referencing.operation.Operation;
import org.geotools.api.referencing.operation.OperationMethod;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class SingleOperationToGT<S extends org.opengis.referencing.operation.SingleOperation>
        extends CoordinateOperationToGT<S> implements Operation
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     * This given object <em>should</em> implement {@link Operation}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    SingleOperationToGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * <h4>GeoAPI particularity</h4>
     * While this method accepts {@code SingleOperation} for compatibility with
     * {@code ConcatenatedOperation.getOperations()}, the given object <em>should</em>
     * be restricted to instances of the {@link Operation} subtype when possible.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Operation wrap(final org.opengis.referencing.operation.SingleOperation impl) {
        if (impl instanceof SingleOperationFromGT<?> c) {
            if (c.impl instanceof Operation op) {
                return op;
            }
        }
        switch (impl) {
            case null: return null;
            case Operation c: return c;
            case org.opengis.referencing.operation.Transformation c: return new TransformationToGT(c);
            case org.opengis.referencing.operation.Conversion c: return ConversionToGT.wrap(c);
            default: return new SingleOperationToGT<>(impl);
        }
    }

    /**
     * If the GeoAPI object implements {@link Operation}, returns a wrapper for the operation method.
     * Otherwise returns {@code null}. The latter case is illegal in <abbr>ISO</abbr> 19111 because this
     * property is mandatory. However, GeoAPI introduces the concept of {@code SingleOperation}
     * without parameters, which does not exist in <abbr>ISO</abbr> 19111.
     */
    @Override
    public OperationMethod getMethod() {
        return OperationMethodToGT.wrap(impl.getMethod());
    }

    @Override
    public ParameterValueGroup getParameterValues() {
        return ParameterValueGroupToGT.wrap(impl.getParameterValues());
    }
}
