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

import org.opengis.util.GenericName;
import org.opengis.util.NameSpace;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class NameSpaceFromGT extends WrapperFromGT implements NameSpace {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.util.NameSpace impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private NameSpaceFromGT(final org.geotools.api.util.NameSpace impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static NameSpace wrap(final org.geotools.api.util.NameSpace impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof NameSpace) {
            var c = (NameSpace) impl;
            return c;
        }
        if (impl instanceof NameSpaceToGT) {
            var c = (NameSpaceToGT) impl;
            return c.impl;
        }
        return new NameSpaceFromGT(impl);
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public boolean isGlobal() {
        return impl.isGlobal();
    }

    @Override
    public GenericName name() {
        return GenericNameFromGT.wrap(impl.name());
    }
}
