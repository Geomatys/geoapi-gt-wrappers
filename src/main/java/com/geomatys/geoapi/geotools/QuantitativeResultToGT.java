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
import javax.measure.Unit;
import org.geotools.api.metadata.quality.QuantitativeResult;
import org.geotools.api.util.InternationalString;
import org.geotools.api.util.Record;
import org.geotools.api.util.RecordType;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class QuantitativeResultToGT extends QualityResultToGT<org.opengis.metadata.quality.QuantitativeResult>
        implements QuantitativeResult
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    QuantitativeResultToGT(final org.opengis.metadata.quality.QuantitativeResult impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static QuantitativeResult wrap(final org.opengis.metadata.quality.QuantitativeResult impl) {
        switch (impl) {
            case null: return null;
            case QuantitativeResult c: return c;
            case QuantitativeResultFromGT c: return c.impl;
            default: return new QuantitativeResultToGT(impl);
        }
    }

    @Override
    public Collection<Record> getValues() {
        return wrap(impl.getValues(), RecordToGT::wrap);
    }

    @Override
    public RecordType getValueType() {
        return RecordTypeToGT.wrap(impl.getValueType());
    }

    @Override
    public Unit<?> getValueUnit() {
        return impl.getValueUnit();
    }

    @Override
    public InternationalString getErrorStatistic() {
        return InternationalStringToGT.wrap(impl.getErrorStatistic());
    }
}
