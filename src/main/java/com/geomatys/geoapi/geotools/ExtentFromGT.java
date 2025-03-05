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
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicExtent;
import org.opengis.metadata.extent.TemporalExtent;
import org.opengis.metadata.extent.VerticalExtent;
import org.opengis.util.InternationalString;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ExtentFromGT extends WrapperFromGT implements Extent {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.metadata.extent.Extent impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private ExtentFromGT(final org.geotools.api.metadata.extent.Extent impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Extent wrap(final org.geotools.api.metadata.extent.Extent impl) {
        switch (impl) {
            case null: return null;
            case Extent c: return c;
            case ExtentToGT c: return c.impl;
            default: return new ExtentFromGT(impl);
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
    public InternationalString getDescription() {
        return InternationalStringFromGT.wrap(impl.getDescription());
    }

    @Override
    public Collection<GeographicExtent> getGeographicElements() {
        return wrap(impl.getGeographicElements(), GeographicExtentFromGT::wrap);
    }

    @Override
    public Collection<TemporalExtent> getTemporalElements() {
        return wrap(impl.getTemporalElements(), TemporalExtentFromGT::wrap);
    }

    @Override
    public Collection<VerticalExtent> getVerticalElements() {
        return wrap(impl.getVerticalElements(), VerticalExtentFromGT::wrap);
    }
}
