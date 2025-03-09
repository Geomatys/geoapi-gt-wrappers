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
import org.opengis.metadata.quality.QuantitativeResult;
import org.opengis.util.InternationalString;
import org.opengis.util.Record;
import org.opengis.util.RecordType;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class QuantitativeResultFromGT extends QualityResultFromGT<org.geotools.api.metadata.quality.QuantitativeResult>
        implements QuantitativeResult
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    QuantitativeResultFromGT(final org.geotools.api.metadata.quality.QuantitativeResult impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static QuantitativeResult wrap(final org.geotools.api.metadata.quality.QuantitativeResult impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof QuantitativeResult) {
            var c = (QuantitativeResult) impl;
            return c;
        }
        if (impl instanceof QuantitativeResultToGT) {
            var c = (QuantitativeResultToGT) impl;
            return c.impl;
        }
        return new QuantitativeResultFromGT(impl);
    }

    @Override
    public Collection<Record> getValues() {
        return wrap(impl.getValues(), RecordFromGT::wrap);
    }

    @Override
    public RecordType getValueType() {
        return RecordTypeFromGT.wrap(impl.getValueType());
    }

    @Override
    public Unit<?> getValueUnit() {
        return impl.getValueUnit();
    }

    @Override
    public InternationalString getErrorStatistic() {
        return InternationalStringFromGT.wrap(impl.getErrorStatistic());
    }
}
