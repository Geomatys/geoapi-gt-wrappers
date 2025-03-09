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

import org.geotools.api.metadata.citation.Address;
import org.geotools.api.metadata.citation.Contact;
import org.geotools.api.metadata.citation.OnLineResource;
import org.geotools.api.metadata.citation.Telephone;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ContactToGT extends WrapperToGT implements Contact {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.metadata.citation.Contact impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private ContactToGT(final org.opengis.metadata.citation.Contact impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Contact wrap(final org.opengis.metadata.citation.Contact impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof Contact) {
            var c = (Contact) impl;
            return c;
        }
        if (impl instanceof ContactFromGT) {
            var c = (ContactFromGT) impl;
            return c.impl;
        }
        return new ContactToGT(impl);
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public Telephone getPhone() {
        return TelephoneToGT.wrap(impl.getPhone());
    }

    @Override
    public Address getAddress() {
        return AddressToGT.wrap(impl.getAddress());
    }

    @Override
    public OnLineResource getOnLineResource() {
        return OnlineResourceToGT.wrap(impl.getOnlineResource());
    }

    @Override
    public InternationalString getHoursOfService() {
        return InternationalStringToGT.wrap(impl.getHoursOfService());
    }

    @Override
    public InternationalString getContactInstructions() {
        return InternationalStringToGT.wrap(impl.getContactInstructions());
    }
}
