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

import org.opengis.metadata.citation.Citation;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.referencing.operation.Formula;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.util.InternationalString;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class OperationMethodFromGT extends IdentifiedObjectFromGT<org.geotools.api.referencing.operation.OperationMethod>
        implements OperationMethod
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    OperationMethodFromGT(final org.geotools.api.referencing.operation.OperationMethod impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static OperationMethod wrap(final org.geotools.api.referencing.operation.OperationMethod impl) {
        switch (impl) {
            case null: return null;
            case OperationMethod c: return c;
            case OperationMethodToGT c: return c.impl;
            default: return new OperationMethodFromGT(impl);
        }
    }

    @Override
    public Formula getFormula() {
        return new Formula() {
            @Override
            public InternationalString getFormula() {
                return InternationalStringFromGT.wrap(impl.getFormula());
            }

            @Override
            public Citation getCitation() {
                return null;
            }
        };
    }

    @Override
    public Integer getSourceDimensions() {
        return impl.getSourceDimensions();
    }

    @Override
    public Integer getTargetDimensions() {
        return impl.getTargetDimensions();
    }

    @Override
    public ParameterDescriptorGroup getParameters() {
        return ParameterDescriptorGroupFromGT.wrap(impl.getParameters());
    }
}
