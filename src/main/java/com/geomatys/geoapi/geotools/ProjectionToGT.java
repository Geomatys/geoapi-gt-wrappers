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

import org.geotools.api.referencing.operation.Projection;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class ProjectionToGT extends ConversionToGT<org.opengis.referencing.operation.Projection>
        implements Projection
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    ProjectionToGT(final org.opengis.referencing.operation.Projection impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Projection wrap(final org.opengis.referencing.operation.Projection impl) {
        switch (impl) {
            case null: return null;
            case Projection c: return c;
            case ProjectionFromGT c: return c.impl;
            case org.opengis.referencing.operation.ConicProjection c:       return new ConicProjectionToGT(c);
            case org.opengis.referencing.operation.PlanarProjection c:      return new PlanarProjectionToGT(c);
            case org.opengis.referencing.operation.CylindricalProjection c: return new CylindricalProjectionToGT(c);
            default: return new ProjectionToGT(impl);
        }
    }
}
