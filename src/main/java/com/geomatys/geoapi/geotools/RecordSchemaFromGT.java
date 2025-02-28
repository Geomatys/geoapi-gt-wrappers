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

import java.util.LinkedHashMap;
import java.util.Map;
import org.opengis.util.LocalName;
import org.opengis.util.RecordSchema;
import org.opengis.util.RecordType;
import org.opengis.util.TypeName;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class RecordSchemaFromGT extends WrapperFromGT implements RecordSchema {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    private final org.geotools.api.util.RecordSchema impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private RecordSchemaFromGT(final org.geotools.api.util.RecordSchema impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static RecordSchema wrap(final org.geotools.api.util.RecordSchema impl) {
        switch (impl) {
            case null: return null;
            case RecordSchema c: return c;
            default: return new RecordSchemaFromGT(impl);
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
    public LocalName getSchemaName() {
        return LocalNameFromGT.wrap(impl.getSchemaName());
    }

    @Override
    public Map<TypeName, RecordType> getDescription() {
        final var map = new LinkedHashMap<TypeName, RecordType>();
        for (final var entry : impl.getDescription().entrySet()) {
            map.put(TypeNameFromGT.wrap(entry.getKey()), RecordTypeFromGT.wrap(entry.getValue()));
        }
        return map;
    }

    @Override
    public RecordType locate(final TypeName name) {
        return RecordTypeFromGT.wrap(impl.locate(TypeNameFromGT.unwrap(name)));
    }
}
