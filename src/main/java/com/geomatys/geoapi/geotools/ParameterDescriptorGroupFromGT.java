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

import java.util.List;
import org.opengis.parameter.GeneralParameterDescriptor;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.parameter.ParameterNotFoundException;
import org.opengis.parameter.ParameterValueGroup;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ParameterDescriptorGroupFromGT extends GeneralParameterDescriptorFromGT<org.geotools.api.parameter.ParameterDescriptorGroup>
        implements ParameterDescriptorGroup
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    ParameterDescriptorGroupFromGT(final org.geotools.api.parameter.ParameterDescriptorGroup impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static <V> ParameterDescriptorGroup wrap(final org.geotools.api.parameter.ParameterDescriptorGroup impl) {
        switch (impl) {
            case null: return null;
            case ParameterDescriptorGroupToGT c: return c.impl;
            default: return new ParameterDescriptorGroupFromGT(impl);
        }
    }

    @Override
    public ParameterValueGroup createValue() {
        return ParameterValueGroupFromGT.wrap(impl.createValue());
    }

    @Override
    public List<GeneralParameterDescriptor> descriptors() {
        return wrap(impl.descriptors(), GeneralParameterDescriptorFromGT::wrap);
    }

    @Override
    public GeneralParameterDescriptor descriptor(String name) throws ParameterNotFoundException {
        try {
            return wrap(impl.descriptor(name));
        } catch (org.geotools.api.parameter.ParameterNotFoundException e) {
            throw ParameterValueGroupFromGT.wrap(e);
        }
    }
}
