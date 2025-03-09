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

import java.net.URI;
import org.opengis.metadata.citation.OnLineFunction;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.util.InternationalString;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class OnlineResourceFromGT extends WrapperFromGT implements OnlineResource {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.metadata.citation.OnLineResource impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private OnlineResourceFromGT(final org.geotools.api.metadata.citation.OnLineResource impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static OnlineResource wrap(final org.geotools.api.metadata.citation.OnLineResource impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof OnlineResource) {
            var c = (OnlineResource) impl;
            return c;
        }
        if (impl instanceof OnlineResourceToGT) {
            var c = (OnlineResourceToGT) impl;
            return c.impl;
        }
        return new OnlineResourceFromGT(impl);
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public URI getLinkage() {
        return impl.getLinkage();
    }

    @Override
    public String getProtocol() {
        return impl.getProtocol();
    }

    @Override
    public String getApplicationProfile() {
        return impl.getApplicationProfile();
    }

    @Override
    public String getName() {
        return impl.getName();
    }

    @Override
    public InternationalString getDescription() {
        return InternationalStringFromGT.wrap(impl.getDescription());
    }

    @Override
    public OnLineFunction getFunction() {
        return wrap(impl.getFunction(), OnLineFunction::valueOf);
    }
}
