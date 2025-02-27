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

import org.opengis.metadata.citation.Contact;
import org.opengis.metadata.citation.Role;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.util.InternationalString;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ResponsiblePartyFromGT extends WrapperFromGT implements ResponsibleParty {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    private final org.geotools.api.metadata.citation.ResponsibleParty impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private ResponsiblePartyFromGT(final org.geotools.api.metadata.citation.ResponsibleParty impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static ResponsibleParty wrap(final org.geotools.api.metadata.citation.ResponsibleParty impl) {
        return (impl == null) ? null : new ResponsiblePartyFromGT(impl);
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public String getIndividualName() {
        return impl.getIndividualName();
    }

    @Override
    public InternationalString getOrganisationName() {
        return InternationalStringFromGT.wrap(impl.getOrganisationName());
    }

    @Override
    public InternationalString getPositionName() {
        return InternationalStringFromGT.wrap(impl.getPositionName());
    }

    @Override
    public Contact getContactInfo() {
        return ContactFromGT.wrap(impl.getContactInfo());
    }

    @Override
    public Role getRole() {
        return wrap(impl.getRole(), Role::valueOf);
    }
}
