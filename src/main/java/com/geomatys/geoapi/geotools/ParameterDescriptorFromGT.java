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

import java.util.Set;
import javax.measure.Unit;
import org.opengis.parameter.ParameterDescriptor;
import org.opengis.parameter.ParameterValue;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <V> the type of value stored in the parameter.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ParameterDescriptorFromGT<V> extends GeneralParameterDescriptorFromGT<org.geotools.api.parameter.ParameterDescriptor<V>>
        implements ParameterDescriptor<V>
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    ParameterDescriptorFromGT(final org.geotools.api.parameter.ParameterDescriptor<V> impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param <V> the type of value stored in the parameter.
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static <V> ParameterDescriptor<V> wrap(final org.geotools.api.parameter.ParameterDescriptor<V> impl) {
        switch (impl) {
            case null: return null;
            default: return new ParameterDescriptorFromGT<>(impl);
        }
    }

    @Override
    public ParameterValue<V> createValue() {
        return ParameterValueFromGT.wrap(impl.createValue());
    }

    @Override
    public Class<V> getValueClass() {
        return impl.getValueClass();
    }

    @Override
    public Set<V> getValidValues() {
        return impl.getValidValues();
    }

    @Override
    public V getDefaultValue() {
        return impl.getDefaultValue();
    }

    @Override
    public Comparable<V> getMinimumValue() {
        return impl.getMinimumValue();
    }

    @Override
    public Comparable<V> getMaximumValue() {
        return impl.getMaximumValue();
    }

    @Override
    public Unit<?> getUnit() {
        return impl.getUnit();
    }
}
