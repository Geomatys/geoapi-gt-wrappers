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

import java.util.List;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.util.LocalName;
import org.opengis.util.NameSpace;
import org.opengis.util.ScopedName;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class GenericNameFromGT<S extends org.geotools.api.util.GenericName>
        extends WrapperFromGT implements GenericName
{
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final S impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    GenericNameFromGT(final S impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static GenericName wrap(final org.geotools.api.util.GenericName impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof GenericNameToGT<?>) {
            var c = (GenericNameToGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.geotools.api.util.LocalName) {
            var c = (org.geotools.api.util.LocalName) impl;
            return LocalNameFromGT.wrap(c);
        }
        if (impl instanceof org.geotools.api.util.ScopedName) {
            var c = (org.geotools.api.util.ScopedName) impl;
            return new ScopedNameFromGT(c);
        }
        return new GenericNameFromGT<>(impl);
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public NameSpace scope() {
        return NameSpaceFromGT.wrap(impl.scope());
    }

    @Override
    public int depth() {
        return impl.depth();
    }

    @Override
    public List<LocalName> getParsedNames() {
        return wrap(impl.getParsedNames(), LocalNameFromGT::wrap);
    }

    @Override
    public LocalName head() {
        return LocalNameFromGT.wrap(impl.head());
    }

    @Override
    public LocalName tip() {
        return LocalNameFromGT.wrap(impl.tip());
    }

    @Override
    public GenericName toFullyQualifiedName() {
        return wrap(impl.toFullyQualifiedName());
    }

    @Override
    public ScopedName push(final GenericName scope) {
        return ScopedNameFromGT.wrap(impl.push(GenericNameToGT.wrap(scope)));
    }

    @Override
    public InternationalString toInternationalString() {
        return InternationalStringFromGT.wrap(impl.toInternationalString());
    }

    @Override
    public int compareTo(final GenericName other) {
        return impl.compareTo(GenericNameToGT.wrap(other));
    }
}
