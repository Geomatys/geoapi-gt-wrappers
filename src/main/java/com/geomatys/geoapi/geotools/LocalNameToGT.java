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

import org.geotools.api.util.LocalName;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class LocalNameToGT<S extends org.opengis.util.LocalName>
        extends GenericNameToGT<S> implements LocalName
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    LocalNameToGT(final S impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static LocalName wrap(final org.opengis.util.LocalName impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof LocalNameFromGT<?>) {
            var c = (LocalNameFromGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.opengis.util.TypeName) {
            var c = (org.opengis.util.TypeName) impl;
            return new TypeNameToGT(c);
        }
        if (impl instanceof org.opengis.util.MemberName) {
            var c = (org.opengis.util.MemberName) impl;
            return new MemberNameToGT(c);
        }
        return new LocalNameToGT<>(impl);
    }
}
