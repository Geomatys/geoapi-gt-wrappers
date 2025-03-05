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
import org.geotools.api.metadata.quality.Element;
import org.geotools.api.metadata.quality.EvaluationMethodType;
import org.geotools.api.metadata.quality.Result;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class QualityElementToGT extends WrapperToGT implements Element {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.metadata.quality.Element impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    QualityElementToGT(final org.opengis.metadata.quality.Element impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Element wrap(final org.opengis.metadata.quality.Element impl) {
        switch (impl) {
            case null: return null;
            case Element c: return c;
            case QualityElementFromGT c: return c.impl;
            case org.opengis.metadata.quality.PositionalAccuracy c: return PositionalAccuracyToGT.wrap(c);
            default: return new QualityElementToGT(impl);
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
    public Collection<InternationalString> getNamesOfMeasure() {
        return wrap(impl.getNamesOfMeasure(), InternationalStringToGT::wrap);
    }

    @Override
    public Identifier getMeasureIdentification() {
        return IdentifierToGT.wrap(impl.getMeasureIdentification());
    }

    @Override
    public InternationalString getMeasureDescription() {
        return InternationalStringToGT.wrap(impl.getMeasureDescription());
    }

    @Override
    public EvaluationMethodType getEvaluationMethodType() {
        return wrap(impl.getEvaluationMethodType(), EvaluationMethodType::valueOf);
    }

    @Override
    public InternationalString getEvaluationMethodDescription() {
        return InternationalStringToGT.wrap(impl.getEvaluationMethodDescription());
    }

    @Override
    public Citation getEvaluationProcedure() {
        return CitationToGT.wrap(impl.getEvaluationProcedure());
    }

    @Override
    public Collection<? extends Date> getDates() {
        return impl.getDates();
    }

    @Override
    public Collection<Result> getResults() {
        return wrap(impl.getResults(), QualityResultToGT::wrap);
    }
}
