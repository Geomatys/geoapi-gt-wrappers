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
import org.opengis.parameter.GeneralParameterValue;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.parameter.ParameterNotFoundException;
import org.opengis.parameter.ParameterValue;
import org.opengis.parameter.ParameterValueGroup;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ParameterValueGroupFromGT extends GeneralParameterValueFromGT<org.geotools.api.parameter.ParameterValueGroup>
        implements ParameterValueGroup
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    ParameterValueGroupFromGT(final org.geotools.api.parameter.ParameterValueGroup impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static <V> ParameterValueGroup wrap(final org.geotools.api.parameter.ParameterValueGroup impl) {
        switch (impl) {
            case null: return null;
            default: return new ParameterValueGroupFromGT(impl);
        }
    }

    @Override
    public ParameterDescriptorGroup getDescriptor() {
        return ParameterDescriptorGroupFromGT.wrap(impl.getDescriptor());
    }

    @Override
    public List<GeneralParameterValue> values() {
        return wrap(impl.values(), GeneralParameterValueFromGT::wrap);
    }

    @Override
    public ParameterValue<?> parameter(String name) throws ParameterNotFoundException {
        try {
            return ParameterValueFromGT.wrap(impl.parameter(name));
        } catch (org.geotools.api.parameter.ParameterNotFoundException e) {
            throw wrap(e);
        }
    }

    @Override
    public List<ParameterValueGroup> groups(String name) throws ParameterNotFoundException {
        try {
            return wrap(impl.groups(name), ParameterValueGroupFromGT::wrap);
        } catch (org.geotools.api.parameter.ParameterNotFoundException e) {
            throw wrap(e);
        }
    }

    @Override
    public ParameterValueGroup addGroup(String name) throws ParameterNotFoundException {
        try {
            return wrap(impl.addGroup(name));
        } catch (org.geotools.api.parameter.ParameterNotFoundException e) {
            throw wrap(e);
        }
    }

    /**
     * Wraps a GeoTools exception into a GeoAPI exception.
     *
     * @param e the GeoTools exception.
     * @return the GeoAPI exception.
     */
    static ParameterNotFoundException wrap(org.geotools.api.parameter.ParameterNotFoundException e) {
        return (ParameterNotFoundException) new ParameterNotFoundException(
                e.getMessage(), e.getParameterName()).initCause(e);
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public ParameterValueGroup clone() {
        return wrap(impl.clone());
    }
}
