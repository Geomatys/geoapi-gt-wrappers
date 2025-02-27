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
        switch (impl) {
            case null: return null;
            case org.geotools.api.util.TypeName   c: return new TypeNameFromGT   (c);
            case org.geotools.api.util.MemberName c: return new MemberNameFromGT (c);
            case org.geotools.api.util.LocalName  c: return new LocalNameFromGT<>(c);
            case org.geotools.api.util.ScopedName c: return new ScopedNameFromGT (c);
            default: return new GenericNameFromGT<>(impl);
        }
    }

    /**
     * {@return the GeoTools implementation behind the given wrapper}.
     *
     * @param wrapper the wrapper from which to get the GeoTools implementation.
     * @throws ClassCastException if the given value is not a wrapper for GeoTools.
     */
    static org.geotools.api.util.GenericName unwrap(final GenericName wrapper) {
        return (wrapper == null) ? null : ((GenericNameFromGT) wrapper).impl;
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
    public ScopedName push(final GenericName gn) {
        return ScopedNameFromGT.wrap(impl.push(unwrap(gn)));
    }

    @Override
    public InternationalString toInternationalString() {
        return InternationalStringFromGT.wrap(impl.toInternationalString());
    }

    @Override
    public int compareTo(final GenericName o) {
        return impl.compareTo(unwrap(o));
    }
}
