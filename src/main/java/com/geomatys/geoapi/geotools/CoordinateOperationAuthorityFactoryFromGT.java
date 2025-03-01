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
import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.OperationMethod;
import org.opengis.util.FactoryException;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CoordinateOperationAuthorityFactoryFromGT extends AuthorityFactoryFromGT implements CoordinateOperationAuthorityFactory {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    private final org.geotools.api.referencing.operation.CoordinateOperationAuthorityFactory impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private CoordinateOperationAuthorityFactoryFromGT(final org.geotools.api.referencing.operation.CoordinateOperationAuthorityFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CoordinateOperationAuthorityFactory wrap(final org.geotools.api.referencing.operation.CoordinateOperationAuthorityFactory impl) {
        switch (impl) {
            case null: return null;
            case CoordinateOperationAuthorityFactory c: return c;
            default: return new CoordinateOperationAuthorityFactoryFromGT(impl);
        }
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.geotools.api.referencing.AuthorityFactory implementation() {
        return impl;
    }

    @Override
    public CoordinateOperation createCoordinateOperation(String code) throws FactoryException {
        try {
            return CoordinateOperationFromGT.wrap(impl.createCoordinateOperation(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public OperationMethod createOperationMethod(String code) throws FactoryException {
        throw new FactoryException("Not supported.");
    }

    @Override
    public Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String source, String target) throws FactoryException {
        try {
            return wrap(impl.createFromCoordinateReferenceSystemCodes(source, target), CoordinateOperationFromGT::wrap);
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }
}
