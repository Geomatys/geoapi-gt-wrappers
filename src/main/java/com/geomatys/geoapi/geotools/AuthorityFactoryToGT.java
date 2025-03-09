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
import org.geotools.api.metadata.citation.Citation;
import org.geotools.api.referencing.AuthorityFactory;
import org.geotools.api.referencing.IdentifiedObject;
import org.geotools.api.referencing.NoSuchAuthorityCodeException;
import org.geotools.api.referencing.FactoryException;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
abstract class AuthorityFactoryToGT extends WrapperToGT implements AuthorityFactory {
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     */
    AuthorityFactoryToGT() {
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    abstract org.opengis.referencing.AuthorityFactory implementation();

    @Override
    public Citation getAuthority() {
        return CitationToGT.wrap(implementation().getAuthority());
    }

    @Override
    public Citation getVendor() {
        return CitationToGT.wrap(implementation().getVendor());
    }

    @Override
    public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
        Class<? extends org.opengis.referencing.IdentifiedObject> gt = null;
        if (type != null) {
            final var interfaces = AuthorityFactoryFromGT.GEOTOOLS_INTERFACES;
            for (int i = interfaces.length; --i >= 0;) {
                if (interfaces[i].isAssignableFrom(type)) {
                    gt = AuthorityFactoryFromGT.GEOAPI_INTERFACES[i];
                    break;
                }
            }
            // Should never be null at this point.
        }
        try {
            return implementation().getAuthorityCodes(gt);
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public InternationalString getDescriptionText(String code) throws FactoryException {
        try {
            return InternationalStringToGT.wrap(implementation().getDescriptionText(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public IdentifiedObject createObject(String code) throws FactoryException {
        try {
            return IdentifiedObjectToGT.wrap(implementation().createObject(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    /**
     * Wraps a GeoAPI exception into a GeoTools exception.
     *
     * @param e the GeoAPI exception.
     * @return the GeoTools exception.
     */
    static FactoryException wrap(org.opengis.util.FactoryException e) {
        if (e instanceof org.opengis.referencing.NoSuchAuthorityCodeException) {
            var c = (org.opengis.referencing.NoSuchAuthorityCodeException) e;
            return (NoSuchAuthorityCodeException) new NoSuchAuthorityCodeException(
                    c.getMessage(), c.getAuthority(), c.getAuthorityCode()).initCause(c);
        }
        return new FactoryException(e.getMessage(), e);
    }
}
