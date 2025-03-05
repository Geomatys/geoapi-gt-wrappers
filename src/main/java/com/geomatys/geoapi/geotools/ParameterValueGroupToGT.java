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
import org.geotools.api.parameter.GeneralParameterValue;
import org.geotools.api.parameter.ParameterDescriptorGroup;
import org.geotools.api.parameter.ParameterNotFoundException;
import org.geotools.api.parameter.ParameterValue;
import org.geotools.api.parameter.ParameterValueGroup;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ParameterValueGroupToGT extends GeneralParameterValueToGT<org.opengis.parameter.ParameterValueGroup>
        implements ParameterValueGroup
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    ParameterValueGroupToGT(final org.opengis.parameter.ParameterValueGroup impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static <V> ParameterValueGroup wrap(final org.opengis.parameter.ParameterValueGroup impl) {
        switch (impl) {
            case null: return null;
            case ParameterValueGroupFromGT c: return c.impl;
            default: return new ParameterValueGroupToGT(impl);
        }
    }

    @Override
    public ParameterDescriptorGroup getDescriptor() {
        return ParameterDescriptorGroupToGT.wrap(impl.getDescriptor());
    }

    @Override
    public List<GeneralParameterValue> values() {
        return wrap(impl.values(), GeneralParameterValueToGT::wrap);
    }

    @Override
    public ParameterValue<?> parameter(String name) throws ParameterNotFoundException {
        try {
            return ParameterValueToGT.wrap(impl.parameter(name));
        } catch (org.opengis.parameter.ParameterNotFoundException e) {
            throw wrap(e);
        }
    }

    @Override
    public List<ParameterValueGroup> groups(String name) throws ParameterNotFoundException {
        try {
            return wrap(impl.groups(name), ParameterValueGroupToGT::wrap);
        } catch (org.opengis.parameter.ParameterNotFoundException e) {
            throw wrap(e);
        }
    }

    @Override
    public ParameterValueGroup addGroup(String name) throws ParameterNotFoundException {
        try {
            return wrap(impl.addGroup(name));
        } catch (org.opengis.parameter.ParameterNotFoundException e) {
            throw wrap(e);
        }
    }

    /**
     * Wraps a GeoAPI exception into a GeoTools exception.
     *
     * @param e the GeoAPI exception.
     * @return the GeoTools exception.
     */
    static ParameterNotFoundException wrap(org.opengis.parameter.ParameterNotFoundException e) {
        return (ParameterNotFoundException) new ParameterNotFoundException(
                e.getMessage(), e.getParameterName()).initCause(e);
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public ParameterValueGroup clone() {
        return wrap(impl.clone());
    }
}
