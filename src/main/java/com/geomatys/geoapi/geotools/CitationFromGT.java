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
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.PresentationForm;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.citation.Series;
import org.opengis.util.InternationalString;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CitationFromGT extends WrapperFromGT implements Citation {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.metadata.citation.Citation impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private CitationFromGT(final org.geotools.api.metadata.citation.Citation impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Citation wrap(final org.geotools.api.metadata.citation.Citation impl) {
        switch (impl) {
            case null: return null;
            case Citation c: return c;
            case CitationToGT c: return c.impl;
            default: return new CitationFromGT(impl);
        }
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public InternationalString getTitle() {
        return InternationalStringFromGT.wrap(impl.getTitle());
    }

    @Override
    public Collection<InternationalString> getAlternateTitles() {
        return wrap(impl.getAlternateTitles(), InternationalStringFromGT::wrap);
    }

    @Override
    public Collection<CitationDate> getDates() {
        return List.of();
    }

    @Override
    public InternationalString getEdition() {
        return InternationalStringFromGT.wrap(impl.getEdition());
    }

    @Override
    public Date getEditionDate() {
        return impl.getEditionDate();
    }

    @Override
    public Collection<Identifier> getIdentifiers() {
        return wrap(impl.getIdentifiers(), IdentifierFromGT::wrap);
    }

    @Override
    public Collection<ResponsibleParty> getCitedResponsibleParties() {
        return wrap(impl.getCitedResponsibleParties(), ResponsiblePartyFromGT::wrap);
    }

    @Override
    public Collection<PresentationForm> getPresentationForms() {
        return wrapAll(impl.getPresentationForm(), PresentationForm::valueOf);
    }

    @Override
    public Series getSeries() {
        return null;
    }

    @Override
    public InternationalString getOtherCitationDetails() {
        return InternationalStringFromGT.wrap(impl.getOtherCitationDetails());
    }

    @Override
    public InternationalString getCollectiveTitle() {
        return InternationalStringFromGT.wrap(impl.getCollectiveTitle());
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
