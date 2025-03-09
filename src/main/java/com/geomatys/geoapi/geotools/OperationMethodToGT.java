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

import org.geotools.api.parameter.ParameterDescriptorGroup;
import org.geotools.api.referencing.operation.OperationMethod;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class OperationMethodToGT extends IdentifiedObjectToGT<org.opengis.referencing.operation.OperationMethod>
        implements OperationMethod
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    OperationMethodToGT(final org.opengis.referencing.operation.OperationMethod impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static OperationMethod wrap(final org.opengis.referencing.operation.OperationMethod impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof OperationMethod) {
            var c = (OperationMethod) impl;
            return c;
        }
        if (impl instanceof OperationMethodFromGT) {
            var c = (OperationMethodFromGT) impl;
            return c.impl;
        }
        return new OperationMethodToGT(impl);
    }

    @Override
    public InternationalString getFormula() {
        var formula = impl.getFormula();
        return (formula == null) ? null : InternationalStringToGT.wrap(formula.getFormula());
    }

    /**
     * Replaces null values by an arbitrary number of dimensions.
     * The returned value may be wrong, but we have no better value to return.
     * Note that the number of dimensions has been removed from {@code OperationMethod} in ISO 19111:2019.
     */
    private static int unbox(Integer value) {
        return (value != null) ? value : 2;
    }

    /**
     * @deprecated The number of dimensions has been removed from {@code OperationMethod} in ISO 19111:2019.
     */
    @Override
    @Deprecated(since = "GeoAPI 3.1")
    public int getSourceDimensions() {
        return unbox(impl.getSourceDimensions());
    }

    /**
     * @deprecated The number of dimensions has been removed from {@code OperationMethod} in ISO 19111:2019.
     */
    @Override
    @Deprecated(since = "GeoAPI 3.1")
    public int getTargetDimensions() {
        return unbox(impl.getTargetDimensions());
    }

    @Override
    public ParameterDescriptorGroup getParameters() {
        return ParameterDescriptorGroupToGT.wrap(impl.getParameters());
    }
}
