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
import org.geotools.api.util.MemberName;
import org.geotools.api.util.Record;
import org.geotools.api.util.RecordSchema;
import org.geotools.api.util.RecordType;
import org.geotools.api.util.TypeName;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class RecordTypeToGT extends WrapperToGT implements RecordType {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.util.RecordType impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private RecordTypeToGT(final org.opengis.util.RecordType impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static RecordType wrap(final org.opengis.util.RecordType impl) {
        switch (impl) {
            case null: return null;
            case RecordType c: return c;
            case RecordTypeFromGT c: return c.impl;
            default: return new RecordTypeToGT(impl);
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
    public TypeName getTypeName() {
        return TypeNameToGT.wrap(impl.getTypeName());
    }

    @Override
    public RecordSchema getContainer() {
        return RecordSchemaToGT.wrap(impl.getContainer());
    }

    @Override
    public Map<MemberName, TypeName> getAttributeTypes() {
        return wrap(impl.getMemberTypes(), MemberNameToGT::wrap, TypeNameToGT::wrap);
    }

    @Override
    public Set<MemberName> getMembers() {
        return wrap(impl.getMembers(), MemberNameToGT::wrap);
    }

    @Override
    public TypeName locate(MemberName name) {
        return TypeNameToGT.wrap(impl.locate(MemberNameFromGT.wrap(name)));
    }

    @Override
    public boolean isInstance(Record record) {
        return impl.isInstance(RecordFromGT.wrap(record));
    }
}
