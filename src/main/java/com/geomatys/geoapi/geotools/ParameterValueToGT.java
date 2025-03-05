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

import java.net.URI;
import javax.measure.Unit;
import org.geotools.api.parameter.InvalidParameterValueException;
import org.geotools.api.parameter.ParameterDescriptor;
import org.geotools.api.parameter.ParameterValue;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <V> the type of value stored in the parameter.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ParameterValueToGT<V> extends GeneralParameterValueToGT<org.opengis.parameter.ParameterValue<V>>
        implements ParameterValue<V>
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    ParameterValueToGT(final org.opengis.parameter.ParameterValue<V> impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param <V> the type of value stored in the parameter.
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static <V> ParameterValue<V> wrap(final org.opengis.parameter.ParameterValue<V> impl) {
        switch (impl) {
            case null: return null;
            default: return new ParameterValueToGT<>(impl);
        }
    }

    @Override
    public ParameterDescriptor<V> getDescriptor() {
        return ParameterDescriptorToGT.wrap(impl.getDescriptor());
    }

    @Override
    public Unit<?> getUnit() {
        return impl.getUnit();
    }

    @Override
    public double doubleValue(Unit<?> unit) {
        return impl.doubleValue(unit);
    }

    @Override
    public double doubleValue() {
        return impl.doubleValue();
    }

    @Override
    public int intValue() {
        return impl.intValue();
    }

    @Override
    public boolean booleanValue() {
        return impl.booleanValue();
    }

    @Override
    public String stringValue() {
        return impl.stringValue();
    }

    @Override
    public double[] doubleValueList(Unit<?> unit) {
        return impl.doubleValueList(unit);
    }

    @Override
    public double[] doubleValueList() {
        return impl.doubleValueList();
    }

    @Override
    public int[] intValueList() {
        return impl.intValueList();
    }

    @Override
    public URI valueFile() {
        return impl.valueFile();
    }

    @Override
    public V getValue() {
        return impl.getValue();
    }

    @Override
    public void setValue(double[] values, Unit<?> unit) throws InvalidParameterValueException {
        try {
            impl.setValue(values, unit);
        } catch (org.opengis.parameter.InvalidParameterValueException e) {
            throw wrap(e);
        }
    }

    @Override
    public void setValue(double value, Unit<?> unit) throws InvalidParameterValueException {
        try {
            impl.setValue(value, unit);
        } catch (org.opengis.parameter.InvalidParameterValueException e) {
            throw wrap(e);
        }
    }

    @Override
    public void setValue(double value) throws InvalidParameterValueException {
        try {
            impl.setValue(value);
        } catch (org.opengis.parameter.InvalidParameterValueException e) {
            throw wrap(e);
        }
    }

    @Override
    public void setValue(int value) throws InvalidParameterValueException {
        try {
            impl.setValue(value);
        } catch (org.opengis.parameter.InvalidParameterValueException e) {
            throw wrap(e);
        }
    }

    @Override
    public void setValue(boolean value) throws InvalidParameterValueException {
        try {
            impl.setValue(value);
        } catch (org.opengis.parameter.InvalidParameterValueException e) {
            throw wrap(e);
        }
    }

    @Override
    public void setValue(Object value) throws InvalidParameterValueException {
        try {
            impl.setValue(value);
        } catch (org.opengis.parameter.InvalidParameterValueException e) {
            throw wrap(e);
        }
    }

    /**
     * Wraps a GeoAPI exception into a GeoTools exception.
     *
     * @param e the GeoAPI exception.
     * @return the GeoTools exception.
     */
    private static InvalidParameterValueException wrap(org.opengis.parameter.InvalidParameterValueException e) {
        return (InvalidParameterValueException) new InvalidParameterValueException(
                e.getMessage(), e.getParameterName(), e.getValue()).initCause(e);
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public ParameterValue<V> clone() {
        return wrap(impl.clone());
    }
}
