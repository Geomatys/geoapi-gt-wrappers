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

import org.geotools.api.parameter.GeneralParameterDescriptor;
import org.geotools.api.parameter.GeneralParameterValue;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class GeneralParameterDescriptorToGT<S extends org.opengis.parameter.GeneralParameterDescriptor>
        extends IdentifiedObjectToGT<S> implements GeneralParameterDescriptor
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    GeneralParameterDescriptorToGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static GeneralParameterDescriptor wrap(final org.opengis.parameter.GeneralParameterDescriptor impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof GeneralParameterDescriptor) {
            var c = (GeneralParameterDescriptor) impl;
            return c;
        }
        if (impl instanceof GeneralParameterDescriptorFromGT<?>) {
            var c = (GeneralParameterDescriptorFromGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.opengis.parameter.ParameterDescriptor<?>) {
            var c = (org.opengis.parameter.ParameterDescriptor<?>) impl;
            return new ParameterDescriptorToGT<>(c);
        }
        if (impl instanceof org.opengis.parameter.ParameterDescriptorGroup) {
            var c = (org.opengis.parameter.ParameterDescriptorGroup) impl;
            return new ParameterDescriptorGroupToGT(c);
        }
        return new GeneralParameterDescriptorToGT<>(impl);
    }

    @Override
    public GeneralParameterValue createValue() {
        return GeneralParameterValueToGT.wrap(impl.createValue());
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
