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

import java.util.Map;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.operation.*;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.util.FactoryException;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CoordinateOperationFactoryFromGT extends ObjectFactoryFromGT implements CoordinateOperationFactory {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.referencing.operation.CoordinateOperationFactory impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private CoordinateOperationFactoryFromGT(final org.geotools.api.referencing.operation.CoordinateOperationFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CoordinateOperationFactory wrap(final org.geotools.api.referencing.operation.CoordinateOperationFactory impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof CoordinateOperationFactory) {
            var c = (CoordinateOperationFactory) impl;
            return c;
        }
        if (impl instanceof CoordinateOperationFactoryToGT) {
            var c = (CoordinateOperationFactoryToGT) impl;
            return c.impl;
        }
        return new CoordinateOperationFactoryFromGT(impl);
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.geotools.api.referencing.ObjectFactory implementation() {
        return impl;
    }

    @Override
    public CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS,
                                               CoordinateReferenceSystem targetCRS)
            throws FactoryException
    {
        try {
            return CoordinateOperationFromGT.wrap(
                    impl.createOperation(
                            CoordinateReferenceSystemToGT.wrap(sourceCRS),
                            CoordinateReferenceSystemToGT.wrap(targetCRS)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS,
                                               CoordinateReferenceSystem targetCRS,
                                               OperationMethod method)
            throws FactoryException
    {
        try {
            return CoordinateOperationFromGT.wrap(
                    impl.createOperation(
                            CoordinateReferenceSystemToGT.wrap(sourceCRS),
                            CoordinateReferenceSystemToGT.wrap(targetCRS),
                            OperationMethodToGT.wrap(method)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CoordinateOperation createConcatenatedOperation(Map<String, ?> properties, CoordinateOperation... operations)
            throws FactoryException
    {
        final var g = new org.geotools.api.referencing.operation.CoordinateOperation[operations.length];
        for (int i=0; i<operations.length; i++) {
            g[i] = CoordinateOperationToGT.wrap(operations[i]);
        }
        try {
            return CoordinateOperationFromGT.wrap(impl.createConcatenatedOperation(properties, g));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public Conversion createDefiningConversion(Map<String, ?> properties, OperationMethod method, ParameterValueGroup parameters)
            throws FactoryException
    {
        try {
            return ConversionFromGT.wrap(
                    impl.createDefiningConversion(properties,
                            OperationMethodToGT.wrap(method),
                            ParameterValueGroupToGT.wrap(parameters)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }
}
