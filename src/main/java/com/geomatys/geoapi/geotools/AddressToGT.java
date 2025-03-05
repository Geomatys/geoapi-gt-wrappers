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

import java.util.Collection;
import org.geotools.api.metadata.citation.Address;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class AddressToGT extends WrapperToGT implements Address {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    private final org.opengis.metadata.citation.Address impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private AddressToGT(final org.opengis.metadata.citation.Address impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Address wrap(final org.opengis.metadata.citation.Address impl) {
        switch (impl) {
            case null: return null;
            case Address c: return c;
            default: return new AddressToGT(impl);
        }
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public Collection<String> getDeliveryPoints() {
        return impl.getDeliveryPoints();
    }

    @Override
    public InternationalString getCity() {
        return InternationalStringToGT.wrap(impl.getCity());
    }

    @Override
    public InternationalString getAdministrativeArea() {
        return InternationalStringToGT.wrap(impl.getAdministrativeArea());
    }

    @Override
    public String getPostalCode() {
        return impl.getPostalCode();
    }

    @Override
    public InternationalString getCountry() {
        return InternationalStringToGT.wrap(impl.getCountry());
    }

    @Override
    public Collection<String> getElectronicMailAddresses() {
        return impl.getElectronicMailAddresses();
    }
}
