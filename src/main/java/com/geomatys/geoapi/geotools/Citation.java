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
import java.util.Date;
import java.util.List;
import org.opengis.metadata.citation.PresentationForm;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class Citation extends Wrapper implements org.opengis.metadata.citation.Citation {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    private final org.geotools.api.metadata.citation.Citation impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private Citation(final org.geotools.api.metadata.citation.Citation impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static org.opengis.metadata.citation.Citation wrap(final org.geotools.api.metadata.citation.Citation impl) {
        return (impl == null) ? null : new Citation(impl);
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public org.opengis.util.InternationalString getTitle() {
        return InternationalString.wrap(impl.getTitle());
    }

    @Override
    public Collection<org.opengis.util.InternationalString> getAlternateTitles() {
        return wrap(impl.getAlternateTitles(), InternationalString::wrap);
    }

    @Override
    public Collection<org.opengis.metadata.citation.CitationDate> getDates() {
        return List.of();
    }

    @Override
    public org.opengis.util.InternationalString getEdition() {
        return InternationalString.wrap(impl.getEdition());
    }

    @Override
    public Date getEditionDate() {
        return impl.getEditionDate();
    }

    @Override
    public Collection<org.opengis.metadata.Identifier> getIdentifiers() {
        return wrap(impl.getIdentifiers(), Identifier::wrap);
    }

    @Override
    public Collection<? extends org.opengis.metadata.citation.ResponsibleParty> getCitedResponsibleParties() {
        return wrap(impl.getCitedResponsibleParties(), ResponsibleParty::wrap);
    }

    @Override
    public Collection<PresentationForm> getPresentationForms() {
        return wrapAll(impl.getPresentationForm(), PresentationForm::valueOf);
    }

    @Override
    public org.opengis.metadata.citation.Series getSeries() {
        return null;
    }

    @Override
    public org.opengis.util.InternationalString getOtherCitationDetails() {
        return InternationalString.wrap(impl.getOtherCitationDetails());
    }

    @Override
    public org.opengis.util.InternationalString getCollectiveTitle() {
        return InternationalString.wrap(impl.getCollectiveTitle());
    }

    @Override
    public String getISBN() {
        return impl.getISBN();
    }

    @Override
    public String getISSN() {
        return impl.getISSN();
    }
}
