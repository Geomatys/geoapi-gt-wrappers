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

import java.util.Collection;
import java.util.Map;
import org.geotools.api.util.LocalName;
import org.geotools.api.util.RecordSchema;
import org.geotools.api.util.RecordType;
import org.geotools.api.util.TypeName;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class RecordSchemaToGT extends WrapperToGT implements RecordSchema {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    private final org.opengis.util.RecordSchema impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private RecordSchemaToGT(final org.opengis.util.RecordSchema impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static RecordSchema wrap(final org.opengis.util.RecordSchema impl) {
        switch (impl) {
            case null: return null;
            case RecordSchema c: return c;
            default: return new RecordSchemaToGT(impl);
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
    public LocalName getSchemaName() {
        return LocalNameToGT.wrap(impl.getSchemaName());
    }

    @Override
    public Map<TypeName, RecordType> getDescription() {
        return wrap(impl.getDescription(), TypeNameToGT::wrap, RecordTypeToGT::wrap);
    }

    @Override
    public Collection<RecordType> getElements() {
        return getDescription().values();
    }

    @Override
    public RecordType locate(final TypeName name) {
        return RecordTypeToGT.wrap(impl.locate(TypeNameToGT.unwrap(name)));
    }
}
