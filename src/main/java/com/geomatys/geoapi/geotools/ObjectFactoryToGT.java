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

import org.geotools.api.metadata.citation.Citation;
import org.geotools.api.referencing.ObjectFactory;
import org.geotools.api.referencing.operation.OperationNotFoundException;
import org.geotools.api.referencing.FactoryException;


/**
 * GeoTools wrapper for an object factory from the GeoAPI.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
abstract class ObjectFactoryToGT extends WrapperToGT implements ObjectFactory {
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     */
    ObjectFactoryToGT() {
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    abstract org.opengis.referencing.ObjectFactory implementation();

    @Override
    public Citation getVendor() {
        return CitationToGT.wrap(implementation().getVendor());
    }

    /**
     * Wraps a GeoAPI exception into a GeoTools exception.
     *
     * @param e the GeoAPI exception.
     * @return the GeoTools exception.
     */
    static FactoryException wrap(org.opengis.util.FactoryException e) {
        if (e instanceof org.opengis.referencing.operation.OperationNotFoundException) {
            return new OperationNotFoundException(e.getMessage(), e);
        }
        return new FactoryException(e.getMessage(), e);
    }
}
