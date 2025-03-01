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
import org.opengis.util.MemberName;
import org.opengis.util.Record;
import org.opengis.util.RecordType;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class RecordFromGT extends WrapperFromGT implements Record {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    private final org.geotools.api.util.Record impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private RecordFromGT(final org.geotools.api.util.Record impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Record wrap(final org.geotools.api.util.Record impl) {
        switch (impl) {
            case null: return null;
            case Record c: return c;
            default: return new RecordFromGT(impl);
        }
    }

    /**
     * {@return the GeoTools implementation behind the given wrapper}.
     *
     * @param wrapper the wrapper from which to get the GeoTools implementation.
     * @throws ClassCastException if the given value is not a wrapper for GeoTools.
     */
    static org.geotools.api.util.Record unwrap(final Record wrapper) {
        switch (wrapper) {
            case null: return null;
            default: return ((RecordFromGT) wrapper).impl;
        }
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public RecordType getRecordType() {
        return RecordTypeFromGT.wrap(impl.getRecordType());
    }

    @Override
    public Map<MemberName, Object> getAttributes() {
        return wrap(impl.getAttributes(), MemberNameFromGT::wrap, Function.identity());
    }

    @Override
    public Object locate(MemberName name) {
        return impl.locate(MemberNameFromGT.unwrap(name));
    }

    @Override
    public void set(MemberName name, Object value) {
        impl.set(MemberNameFromGT.unwrap(name), value);
    }
}
