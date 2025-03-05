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
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.quality.Element;
import org.opengis.metadata.quality.EvaluationMethodType;
import org.opengis.metadata.quality.Result;
import org.opengis.util.InternationalString;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class QualityElementFromGT extends WrapperFromGT implements Element {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.metadata.quality.Element impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    QualityElementFromGT(final org.geotools.api.metadata.quality.Element impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Element wrap(final org.geotools.api.metadata.quality.Element impl) {
        switch (impl) {
            case null: return null;
            case Element c: return c;
            case QualityElementToGT c: return c.impl;
            case org.geotools.api.metadata.quality.PositionalAccuracy c: return PositionalAccuracyFromGT.wrap(c);
            default: return new QualityElementFromGT(impl);
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
    public Collection<InternationalString> getNamesOfMeasure() {
        return wrap(impl.getNamesOfMeasure(), InternationalStringFromGT::wrap);
    }

    @Override
    public Identifier getMeasureIdentification() {
        return IdentifierFromGT.wrap(impl.getMeasureIdentification());
    }

    @Override
    public InternationalString getMeasureDescription() {
        return InternationalStringFromGT.wrap(impl.getMeasureDescription());
    }

    @Override
    public EvaluationMethodType getEvaluationMethodType() {
        return wrap(impl.getEvaluationMethodType(), EvaluationMethodType::valueOf);
    }

    @Override
    public InternationalString getEvaluationMethodDescription() {
        return InternationalStringFromGT.wrap(impl.getEvaluationMethodDescription());
    }

    @Override
    public Citation getEvaluationProcedure() {
        return CitationFromGT.wrap(impl.getEvaluationProcedure());
    }

    @Override
    public Collection<? extends Date> getDates() {
        return impl.getDates();
    }

    @Override
    public Collection<Result> getResults() {
        return wrap(impl.getResults(), QualityResultFromGT::wrap);
    }
}
