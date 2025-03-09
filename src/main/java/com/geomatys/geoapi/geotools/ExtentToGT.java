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
import org.geotools.api.metadata.extent.Extent;
import org.geotools.api.metadata.extent.GeographicExtent;
import org.geotools.api.metadata.extent.TemporalExtent;
import org.geotools.api.metadata.extent.VerticalExtent;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ExtentToGT extends WrapperToGT implements Extent {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.metadata.extent.Extent impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private ExtentToGT(final org.opengis.metadata.extent.Extent impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Extent wrap(final org.opengis.metadata.extent.Extent impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof Extent) {
            var c = (Extent) impl;
            return c;
        }
        if (impl instanceof ExtentFromGT) {
            var c = (ExtentFromGT) impl;
            return c.impl;
        }
        return new ExtentToGT(impl);
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public InternationalString getDescription() {
        return InternationalStringToGT.wrap(impl.getDescription());
    }

    @Override
    public Collection<GeographicExtent> getGeographicElements() {
        return wrap(impl.getGeographicElements(), GeographicExtentToGT::wrap);
    }

    @Override
    public Collection<TemporalExtent> getTemporalElements() {
        return wrap(impl.getTemporalElements(), TemporalExtentToGT::wrap);
    }

    @Override
    public Collection<VerticalExtent> getVerticalElements() {
        return wrap(impl.getVerticalElements(), VerticalExtentToGT::wrap);
    }
}
