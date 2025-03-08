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
package com.geomatys.geoapi.geotools.spi;

import java.util.Map;
import org.opengis.util.FactoryException;
import org.opengis.metadata.citation.Citation;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationFactory;
import org.opengis.referencing.operation.OperationMethod;
import com.geomatys.geoapi.geotools.Services;


/**
 * Coordinate operation factory that delegates its work to GeoTools.
 * This is a temporary class for compatibility with applications that put their dependencies on the class-path.
 * This class may be removed in a future version if only module-path is supported (in that latter case,
 * {@link java.util.ServiceLoader} would invoke {@link #provider()} directly).
 *
 * @author Martin Desruisseaux (Geomatys)
 */
public class OperationFactoryProxy implements CoordinateOperationFactory {
    /**
     * The factory where to delegate all method calls.
     */
    protected final CoordinateOperationFactory factory;

    /**
     * Creates a new authority factory.
     * This is for compatibility with applications that put their dependencies on the class-path.
     *
     * @throws FactoryException if the GeoTools {@code org.geotools.referencing.CRS} class has not been found.
     */
    public OperationFactoryProxy() throws FactoryException {
        factory = provider();
    }

    /**
     * {@return the operation factory to use}.
     * In a modular project, {@code ServiceLoadeer} invokes this method instead of the constructor.
     *
     * @throws FactoryException if the GeoTools {@code org.geotools.referencing.CRS} class has not been found.
     */
    public static CoordinateOperationFactory provider() throws FactoryException {
        return Services.getCoordinateOperationFactory(false);
    }

    @Override
    public Citation getVendor() {
        return factory.getVendor();
    }

    @Override
    public CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS)
            throws FactoryException
    {
        return factory.createOperation(sourceCRS, targetCRS);
    }

    @Override
    public CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, OperationMethod method)
            throws FactoryException
    {
        return factory.createOperation(sourceCRS, targetCRS, method);
    }

    @Override
    public CoordinateOperation createConcatenatedOperation(Map<String, ?> properties, CoordinateOperation... operations)
            throws FactoryException
    {
        return factory.createConcatenatedOperation(properties, operations);
    }

    @Override
    public Conversion createDefiningConversion(Map<String, ?> properties, OperationMethod method, ParameterValueGroup parameters)
            throws FactoryException
    {
        return factory.createDefiningConversion(properties, method, parameters);
    }

    @Override
    public String toString() {
        return factory.toString();
    }
}
