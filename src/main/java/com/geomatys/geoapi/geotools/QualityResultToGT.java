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

import org.geotools.api.metadata.quality.Result;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class QualityResultToGT<S extends org.opengis.metadata.quality.Result> extends WrapperToGT implements Result {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final S impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    QualityResultToGT(final S impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Result wrap(final org.opengis.metadata.quality.Result impl) {
        switch (impl) {
            case null: return null;
            case Result c: return c;
            case org.opengis.metadata.quality.ConformanceResult  c: return new ConformanceResultToGT (c);
            case org.opengis.metadata.quality.QuantitativeResult c: return new QuantitativeResultToGT(c);
            default: return new QualityResultToGT<>(impl);
        }
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }
}
