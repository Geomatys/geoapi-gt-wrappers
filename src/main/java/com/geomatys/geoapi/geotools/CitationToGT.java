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
import org.geotools.api.metadata.Identifier;
import org.geotools.api.metadata.citation.Citation;
import org.geotools.api.metadata.citation.PresentationForm;
import org.geotools.api.metadata.citation.ResponsibleParty;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CitationToGT extends WrapperToGT implements Citation {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.metadata.citation.Citation impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private CitationToGT(final org.opengis.metadata.citation.Citation impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Citation wrap(final org.opengis.metadata.citation.Citation impl) {
        switch (impl) {
            case null: return null;
            case Citation c: return c;
            case CitationFromGT c: return c.impl;
            default: return new CitationToGT(impl);
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
    public InternationalString getTitle() {
        return InternationalStringToGT.wrap(impl.getTitle());
    }

    @Override
    public Collection<InternationalString> getAlternateTitles() {
        return wrap(impl.getAlternateTitles(), InternationalStringToGT::wrap);
    }

    @Override
    public InternationalString getEdition() {
        return InternationalStringToGT.wrap(impl.getEdition());
    }

    @Override
    public Date getEditionDate() {
        return impl.getEditionDate();
    }

    @Override
    public Collection<Identifier> getIdentifiers() {
        return wrap(impl.getIdentifiers(), IdentifierToGT::wrap);
    }

    @Override
    public Collection<ResponsibleParty> getCitedResponsibleParties() {
        return wrap(impl.getCitedResponsibleParties(), ResponsiblePartyToGT::wrap);
    }

    @Override
    public Collection<PresentationForm> getPresentationForm() {
        return wrapAll(impl.getPresentationForms(), PresentationForm::valueOf);
    }

    @Override
    public InternationalString getOtherCitationDetails() {
        return InternationalStringToGT.wrap(impl.getOtherCitationDetails());
    }

    @Override
    public InternationalString getCollectiveTitle() {
        return InternationalStringToGT.wrap(impl.getCollectiveTitle());
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
