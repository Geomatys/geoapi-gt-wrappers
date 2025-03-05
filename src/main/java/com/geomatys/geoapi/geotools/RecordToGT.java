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

import java.util.Map;
import java.util.function.Function;
import org.geotools.api.util.MemberName;
import org.geotools.api.util.Record;
import org.geotools.api.util.RecordType;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class RecordToGT extends WrapperToGT implements Record {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    private final org.opengis.util.Record impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private RecordToGT(final org.opengis.util.Record impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Record wrap(final org.opengis.util.Record impl) {
        switch (impl) {
            case null: return null;
            case Record c: return c;
            default: return new RecordToGT(impl);
        }
    }

    /**
     * {@return the GeoAPI implementation behind the given wrapper}.
     *
     * @param wrapper the wrapper from which to get the GeoAPI implementation.
     * @throws ClassCastException if the given value is not a wrapper for GeoAPI.
     */
    static org.opengis.util.Record unwrap(final Record wrapper) {
        switch (wrapper) {
            case null: return null;
            default: return ((RecordToGT) wrapper).impl;
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
    public RecordType getRecordType() {
        return RecordTypeToGT.wrap(impl.getRecordType());
    }

    @Override
    public Map<MemberName, Object> getAttributes() {
        return wrap(impl.getAttributes(), MemberNameToGT::wrap, Function.identity());
    }

    @Override
    public Object locate(MemberName name) {
        return impl.locate(MemberNameToGT.unwrap(name));
    }

    @Override
    public void set(MemberName name, Object value) {
        impl.set(MemberNameToGT.unwrap(name), value);
    }
}
