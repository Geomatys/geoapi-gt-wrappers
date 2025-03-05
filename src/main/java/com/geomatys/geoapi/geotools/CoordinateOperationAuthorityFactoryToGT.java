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
import org.geotools.api.referencing.operation.CoordinateOperationAuthorityFactory;
import org.geotools.api.referencing.operation.CoordinateOperation;
import org.geotools.api.referencing.FactoryException;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CoordinateOperationAuthorityFactoryToGT extends AuthorityFactoryToGT implements CoordinateOperationAuthorityFactory {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.referencing.operation.CoordinateOperationAuthorityFactory impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private CoordinateOperationAuthorityFactoryToGT(final org.opengis.referencing.operation.CoordinateOperationAuthorityFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CoordinateOperationAuthorityFactory wrap(final org.opengis.referencing.operation.CoordinateOperationAuthorityFactory impl) {
        switch (impl) {
            case null: return null;
            case CoordinateOperationAuthorityFactory c: return c;
            case CoordinateOperationAuthorityFactoryFromGT c: return c.impl;
            default: return new CoordinateOperationAuthorityFactoryToGT(impl);
        }
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.opengis.referencing.AuthorityFactory implementation() {
        return impl;
    }

    @Override
    public CoordinateOperation createCoordinateOperation(String code) throws FactoryException {
        try {
            return CoordinateOperationToGT.wrap(impl.createCoordinateOperation(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String source, String target) throws FactoryException {
        try {
            return wrap(impl.createFromCoordinateReferenceSystemCodes(source, target), CoordinateOperationToGT::wrap);
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }
}
