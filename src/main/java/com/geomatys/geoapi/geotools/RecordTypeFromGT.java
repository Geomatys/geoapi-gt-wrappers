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
import java.util.Set;
import org.opengis.util.MemberName;
import org.opengis.util.Record;
import org.opengis.util.RecordSchema;
import org.opengis.util.RecordType;
import org.opengis.util.Type;
import org.opengis.util.TypeName;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class RecordTypeFromGT extends WrapperFromGT implements RecordType {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.util.RecordType impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private RecordTypeFromGT(final org.geotools.api.util.RecordType impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static RecordType wrap(final org.geotools.api.util.RecordType impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof RecordType) {
            var c = (RecordType) impl;
            return c;
        }
        if (impl instanceof RecordTypeToGT) {
            var c = (RecordTypeToGT) impl;
            return c.impl;
        }
        return new RecordTypeFromGT(impl);
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public TypeName getTypeName() {
        return TypeNameFromGT.wrap(impl.getTypeName());
    }

    @Override
    public RecordSchema getContainer() {
        return RecordSchemaFromGT.wrap(impl.getContainer());
    }

    @Override
    public Map<MemberName, Type> getMemberTypes() {
        return wrap(impl.getAttributeTypes(), MemberNameFromGT::wrap, TypeNameFromGT::wrap);
    }

    @Override
    public Set<MemberName> getMembers() {
        return wrap(impl.getMembers(), MemberNameFromGT::wrap);
    }

    @Override
    public TypeName locate(MemberName name) {
        return TypeNameFromGT.wrap(impl.locate(MemberNameToGT.wrap(name)));
    }

    @Override
    public boolean isInstance(Record record) {
        return impl.isInstance(RecordToGT.wrap(record));
    }
}
