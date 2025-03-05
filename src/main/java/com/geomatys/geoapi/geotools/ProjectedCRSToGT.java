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

import org.geotools.api.referencing.crs.GeographicCRS;
import org.geotools.api.referencing.crs.ProjectedCRS;
import org.geotools.api.referencing.cs.CartesianCS;
import org.geotools.api.referencing.datum.GeodeticDatum;
import org.geotools.api.referencing.operation.Projection;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ProjectedCRSToGT extends GeneralDerivedCRSToGT<org.opengis.referencing.crs.ProjectedCRS>
        implements ProjectedCRS
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    ProjectedCRSToGT(final org.opengis.referencing.crs.ProjectedCRS impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static ProjectedCRS wrap(final org.opengis.referencing.crs.ProjectedCRS impl) {
        switch (impl) {
            case null: return null;
            case ProjectedCRS c: return c;
            default: return new ProjectedCRSToGT(impl);
        }
    }

    @Override
    public GeographicCRS getBaseCRS() {
        return GeographicCRSToGT.wrap(impl.getBaseCRS());
    }

    @Override
    public Projection getConversionFromBase() {
        return ProjectionToGT.wrap(impl.getConversionFromBase());
    }

    @Override
    public CartesianCS getCoordinateSystem() {
        return CartesianCSToGT.wrap(impl.getCoordinateSystem());
    }

    @Override
    public GeodeticDatum getDatum() {
        return GeodeticDatumToGT.wrap(impl.getDatum());
    }
}
