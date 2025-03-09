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
import java.util.Set;
import org.geotools.api.referencing.crs.*;
import org.geotools.api.referencing.operation.*;
import org.geotools.api.parameter.ParameterValueGroup;
import org.geotools.api.referencing.FactoryException;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CoordinateOperationFactoryToGT extends ObjectFactoryToGT implements CoordinateOperationFactory {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.referencing.operation.CoordinateOperationFactory impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private CoordinateOperationFactoryToGT(final org.opengis.referencing.operation.CoordinateOperationFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CoordinateOperationFactory wrap(final org.opengis.referencing.operation.CoordinateOperationFactory impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof CoordinateOperationFactory) {
            var c = (CoordinateOperationFactory) impl;
            return c;
        }
        if (impl instanceof CoordinateOperationFactoryFromGT) {
            var c = (CoordinateOperationFactoryFromGT) impl;
            return c.impl;
        }
        return new CoordinateOperationFactoryToGT(impl);
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.opengis.referencing.ObjectFactory implementation() {
        return impl;
    }

    @Override
    public Set<CoordinateOperation> findOperations(CoordinateReferenceSystem sourceCRS,
                                                   CoordinateReferenceSystem targetCRS)
            throws FactoryException
    {
        try {
            return Set.of(createOperation(sourceCRS, targetCRS));
        } catch (OperationNotFoundException e) {
            return Set.of();
        }
    }

    @Override
    public CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS,
                                               CoordinateReferenceSystem targetCRS)
            throws FactoryException
    {
        try {
            return CoordinateOperationToGT.wrap(
                    impl.createOperation(
                            CoordinateReferenceSystemFromGT.wrap(sourceCRS),
                            CoordinateReferenceSystemFromGT.wrap(targetCRS)));
        } catch (org.opengis.util.FactoryException e) {
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
            return CoordinateOperationToGT.wrap(
                    impl.createOperation(
                            CoordinateReferenceSystemFromGT.wrap(sourceCRS),
                            CoordinateReferenceSystemFromGT.wrap(targetCRS),
                            OperationMethodFromGT.wrap(method)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CoordinateOperation createConcatenatedOperation(Map<String, ?> properties, CoordinateOperation... operations)
            throws FactoryException
    {
        final var g = new org.opengis.referencing.operation.CoordinateOperation[operations.length];
        for (int i=0; i<operations.length; i++) {
            g[i] = CoordinateOperationFromGT.wrap(operations[i]);
        }
        try {
            return CoordinateOperationToGT.wrap(impl.createConcatenatedOperation(properties, g));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public Conversion createDefiningConversion(Map<String, ?> properties, OperationMethod method, ParameterValueGroup parameters)
            throws FactoryException
    {
        try {
            return ConversionToGT.wrap(
                    impl.createDefiningConversion(properties,
                            OperationMethodFromGT.wrap(method),
                            ParameterValueGroupFromGT.wrap(parameters)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }
}
