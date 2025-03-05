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

import org.opengis.metadata.quality.PositionalAccuracy;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class PositionalAccuracyFromGT extends QualityElementFromGT implements PositionalAccuracy {
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    PositionalAccuracyFromGT(final org.geotools.api.metadata.quality.PositionalAccuracy impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static PositionalAccuracy wrap(final org.geotools.api.metadata.quality.PositionalAccuracy impl) {
        switch (impl) {
            case null: return null;
            case PositionalAccuracy c: return c;
            case PositionalAccuracyToGT c: return (PositionalAccuracy) c.impl;
            case org.geotools.api.metadata.quality.AbsoluteExternalPositionalAccuracy c: return new AbsoluteExternalPositionalAccuracyFromGT(c);
            default: return new PositionalAccuracyFromGT(impl);
        }
    }
}
