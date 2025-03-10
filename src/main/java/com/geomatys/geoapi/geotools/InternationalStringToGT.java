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

import java.util.Locale;
import java.util.stream.IntStream;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class InternationalStringToGT extends WrapperToGT implements InternationalString {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.util.InternationalString impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private InternationalStringToGT(final org.opengis.util.InternationalString impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static InternationalString wrap(final org.opengis.util.InternationalString impl) {
        switch (impl) {
            case null: return null;
            case InternationalStringFromGT c: return c.impl;
            default: return new InternationalStringToGT(impl);
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
    public boolean isEmpty() {
        return impl.isEmpty();
    }

    @Override
    public int length() {
        return impl.length();
    }

    @Override
    public char charAt(int index) {
        return impl.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return impl.subSequence(start, end);
    }

    @Override
    public IntStream chars() {
        return impl.chars();
    }

    @Override
    public IntStream codePoints() {
        return impl.codePoints();
    }

    @Override
    public String toString(Locale locale) {
        return impl.toString(locale);
    }

    @Override
    public int compareTo(final InternationalString o) {
        if (o instanceof InternationalStringToGT i18n) {
            return impl.compareTo(i18n.impl);
        } else {
            return toString().compareTo(o.toString());
        }
    }
}
