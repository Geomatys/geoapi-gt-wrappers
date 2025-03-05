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
import org.geotools.api.parameter.GeneralParameterDescriptor;
import org.geotools.api.parameter.ParameterDescriptorGroup;
import org.geotools.api.parameter.ParameterNotFoundException;
import org.geotools.api.parameter.ParameterValueGroup;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ParameterDescriptorGroupToGT extends GeneralParameterDescriptorToGT<org.opengis.parameter.ParameterDescriptorGroup>
        implements ParameterDescriptorGroup
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    ParameterDescriptorGroupToGT(final org.opengis.parameter.ParameterDescriptorGroup impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static <V> ParameterDescriptorGroup wrap(final org.opengis.parameter.ParameterDescriptorGroup impl) {
        switch (impl) {
            case null: return null;
            default: return new ParameterDescriptorGroupToGT(impl);
        }
    }

    @Override
    public ParameterValueGroup createValue() {
        return ParameterValueGroupToGT.wrap(impl.createValue());
    }

    @Override
    public List<GeneralParameterDescriptor> descriptors() {
        return wrap(impl.descriptors(), GeneralParameterDescriptorToGT::wrap);
    }

    @Override
    public GeneralParameterDescriptor descriptor(String name) throws ParameterNotFoundException {
        try {
            return wrap(impl.descriptor(name));
        } catch (org.opengis.parameter.ParameterNotFoundException e) {
            throw ParameterValueGroupToGT.wrap(e);
        }
    }
}
