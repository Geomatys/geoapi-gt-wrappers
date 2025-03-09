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

import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.quality.ConformanceResult;
import org.opengis.util.InternationalString;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ConformanceResultFromGT extends QualityResultFromGT<org.geotools.api.metadata.quality.ConformanceResult>
        implements ConformanceResult
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    ConformanceResultFromGT(final org.geotools.api.metadata.quality.ConformanceResult impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static ConformanceResult wrap(final org.geotools.api.metadata.quality.ConformanceResult impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof ConformanceResult) {
            var c = (ConformanceResult) impl;
            return c;
        }
        if (impl instanceof ConformanceResultToGT) {
            var c = (ConformanceResultToGT) impl;
            return c.impl;
        }
        return new ConformanceResultFromGT(impl);
    }

    @Override
    public Citation getSpecification() {
        return CitationFromGT.wrap(impl.getSpecification());
    }

    @Override
    public InternationalString getExplanation() {
        return InternationalStringFromGT.wrap(impl.getExplanation());
    }

    @Override
    public Boolean pass() {
        return impl.pass();
    }
}
