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

import org.geotools.api.temporal.PeriodDuration;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class PeriodDurationToGT extends WrapperToGT implements PeriodDuration {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    private final org.opengis.temporal.PeriodDuration impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private PeriodDurationToGT(final org.opengis.temporal.PeriodDuration impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static PeriodDuration wrap(final org.opengis.temporal.PeriodDuration impl) {
        switch (impl) {
            case null: return null;
            case PeriodDuration c: return c;
            default: return new PeriodDurationToGT(impl);
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
    @Deprecated(since = "GeoAPI 3.0")
    public InternationalString getDesignator() {
        return null;
    }

    @Override
    @Deprecated(since = "GeoAPI 3.0")
    public InternationalString getYears() {
        return null;
    }

    @Override
    @Deprecated(since = "GeoAPI 3.0")
    public InternationalString getMonths() {
        return null;
    }

    @Override
    @Deprecated(since = "GeoAPI 3.0")
    public InternationalString getDays() {
        return null;
    }

    @Override
    @Deprecated(since = "GeoAPI 3.0")
    public InternationalString getTimeIndicator() {
        return null;
    }

    @Override
    @Deprecated(since = "GeoAPI 3.0")
    public InternationalString getHours() {
        return null;
    }

    @Override
    @Deprecated(since = "GeoAPI 3.0")
    public InternationalString getMinutes() {
        return null;
    }

    @Override
    @Deprecated(since = "GeoAPI 3.0")
    public InternationalString getSeconds() {
        return null;
    }
}
