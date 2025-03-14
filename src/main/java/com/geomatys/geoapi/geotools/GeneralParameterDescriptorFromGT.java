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

import org.opengis.parameter.GeneralParameterDescriptor;
import org.opengis.parameter.GeneralParameterValue;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class GeneralParameterDescriptorFromGT<S extends org.geotools.api.parameter.GeneralParameterDescriptor>
        extends IdentifiedObjectFromGT<S> implements GeneralParameterDescriptor
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    GeneralParameterDescriptorFromGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static GeneralParameterDescriptor wrap(final org.geotools.api.parameter.GeneralParameterDescriptor impl) {
        switch (impl) {
            case null: return null;
            case GeneralParameterDescriptor c: return c;
            case GeneralParameterDescriptorToGT<?> c: return c.impl;
            case org.geotools.api.parameter.ParameterDescriptor<?> c: return new ParameterDescriptorFromGT<>(c);
            case org.geotools.api.parameter.ParameterDescriptorGroup c: return new ParameterDescriptorGroupFromGT(c);
            default: return new GeneralParameterDescriptorFromGT<>(impl);
        }
    }

    @Override
    public GeneralParameterValue createValue() {
        return GeneralParameterValueFromGT.wrap(impl.createValue());
    }

    @Override
    public int getMinimumOccurs() {
        return impl.getMinimumOccurs();
    }

    @Override
    public int getMaximumOccurs() {
        return impl.getMaximumOccurs();
    }
}
