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
import org.geotools.api.metadata.citation.OnLineFunction;
import org.geotools.api.metadata.citation.OnLineResource;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class OnlineResourceToGT extends WrapperToGT implements OnLineResource {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.metadata.citation.OnlineResource impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private OnlineResourceToGT(final org.opengis.metadata.citation.OnlineResource impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static OnLineResource wrap(final org.opengis.metadata.citation.OnlineResource impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof OnLineResource) {
            var c = (OnLineResource) impl;
            return c;
        }
        if (impl instanceof OnlineResourceFromGT) {
            var c = (OnlineResourceFromGT) impl;
            return c.impl;
        }
        return new OnlineResourceToGT(impl);
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
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
        return InternationalStringToGT.wrap(impl.getDescription());
    }

    @Override
    public OnLineFunction getFunction() {
        return wrap(impl.getFunction(), OnLineFunction::valueOf);
    }
}
