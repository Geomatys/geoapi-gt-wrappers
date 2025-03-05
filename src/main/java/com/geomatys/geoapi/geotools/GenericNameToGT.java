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
import org.geotools.api.util.GenericName;
import org.geotools.api.util.InternationalString;
import org.geotools.api.util.LocalName;
import org.geotools.api.util.NameSpace;
import org.geotools.api.util.ScopedName;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class GenericNameToGT<S extends org.opengis.util.GenericName>
        extends WrapperToGT implements GenericName
{
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final S impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    GenericNameToGT(final S impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static GenericName wrap(final org.opengis.util.GenericName impl) {
        switch (impl) {
            case null: return null;
            case GenericNameFromGT<?> c: return c.impl;
            case org.opengis.util.LocalName  c: return LocalNameToGT.wrap(c);
            case org.opengis.util.ScopedName c: return new ScopedNameToGT(c);
            default: return new GenericNameToGT<>(impl);
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
    public NameSpace scope() {
        return NameSpaceToGT.wrap(impl.scope());
    }

    @Override
    public int depth() {
        return impl.depth();
    }

    @Override
    public List<LocalName> getParsedNames() {
        return wrap(impl.getParsedNames(), LocalNameToGT::wrap);
    }

    @Override
    public LocalName head() {
        return LocalNameToGT.wrap(impl.head());
    }

    @Override
    public LocalName tip() {
        return LocalNameToGT.wrap(impl.tip());
    }

    @Override
    public GenericName toFullyQualifiedName() {
        return wrap(impl.toFullyQualifiedName());
    }

    @Override
    public ScopedName push(final GenericName scope) {
        return ScopedNameToGT.wrap(impl.push(GenericNameFromGT.wrap(scope)));
    }

    @Override
    public InternationalString toInternationalString() {
        return InternationalStringToGT.wrap(impl.toInternationalString());
    }

    @Override
    public int compareTo(final GenericName other) {
        return impl.compareTo(GenericNameFromGT.wrap(other));
    }
}
