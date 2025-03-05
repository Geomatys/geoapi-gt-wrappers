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
class GeneralParameterValueToGT<S extends org.opengis.parameter.GeneralParameterValue>
        extends WrapperToGT implements GeneralParameterValue
{
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final S impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    GeneralParameterValueToGT(final S impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static GeneralParameterValue wrap(final org.opengis.parameter.GeneralParameterValue impl) {
        switch (impl) {
            case null: return null;
            case GeneralParameterValue c: return c;
            case GeneralParameterValueFromGT<?> c: return c.impl;
            case org.opengis.parameter.ParameterValue<?> c: return new ParameterValueToGT<>(c);
            case org.opengis.parameter.ParameterValueGroup c: return new ParameterValueGroupToGT(c);
            default: return new GeneralParameterValueToGT<>(impl);
        }
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public GeneralParameterDescriptor getDescriptor() {
        return GeneralParameterDescriptorToGT.wrap(impl.getDescriptor());
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public GeneralParameterValue clone() {
        return wrap(impl.clone());
    }
}
