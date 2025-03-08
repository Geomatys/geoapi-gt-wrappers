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

import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.ObjectFactory;
import org.opengis.referencing.operation.OperationNotFoundException;
import org.opengis.util.FactoryException;


/**
 * GeoAPI wrapper for an object factory from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
abstract class ObjectFactoryFromGT extends WrapperFromGT implements ObjectFactory {
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     */
    ObjectFactoryFromGT() {
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    abstract org.geotools.api.referencing.ObjectFactory implementation();

    @Override
    public Citation getVendor() {
        return CitationFromGT.wrap(implementation().getVendor());
    }

    /**
     * Wraps a GeoTools exception into a GeoAPI exception.
     *
     * @param e the GeoTools exception.
     * @return the GeoAPI exception.
     */
    static FactoryException wrap(org.geotools.api.referencing.FactoryException e) {
        if (e instanceof org.geotools.api.referencing.operation.OperationNotFoundException) {
            return new OperationNotFoundException(e.getMessage(), e);
        }
        return new FactoryException(e.getMessage(), e);
    }
}
