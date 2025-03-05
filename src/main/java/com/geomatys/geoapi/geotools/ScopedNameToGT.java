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

import org.geotools.api.util.GenericName;
import org.geotools.api.util.ScopedName;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ScopedNameToGT extends GenericNameToGT<org.opengis.util.ScopedName> implements ScopedName {
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    ScopedNameToGT(final org.opengis.util.ScopedName impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static ScopedName wrap(final org.opengis.util.ScopedName impl) {
        return (impl == null) ? null : new ScopedNameToGT(impl);
    }

    @Override
    public GenericName tail() {
        return GenericNameToGT.wrap(impl.tail());
    }

    @Override
    public GenericName path() {
        return GenericNameToGT.wrap(impl.path());
    }
}
