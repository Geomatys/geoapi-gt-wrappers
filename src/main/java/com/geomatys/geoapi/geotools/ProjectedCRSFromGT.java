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

import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.operation.Projection;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ProjectedCRSFromGT extends GeneralDerivedCRSFromGT<org.geotools.api.referencing.crs.ProjectedCRS>
        implements ProjectedCRS
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    ProjectedCRSFromGT(final org.geotools.api.referencing.crs.ProjectedCRS impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static ProjectedCRS wrap(final org.geotools.api.referencing.crs.ProjectedCRS impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof ProjectedCRS) {
            var c = (ProjectedCRS) impl;
            return c;
        }
        if (impl instanceof ProjectedCRSToGT) {
            var c = (ProjectedCRSToGT) impl;
            return c.impl;
        }
        return new ProjectedCRSFromGT(impl);
    }

    @Override
    public GeographicCRS getBaseCRS() {
        return GeographicCRSFromGT.wrap(impl.getBaseCRS());
    }

    @Override
    public Projection getConversionFromBase() {
        return ProjectionFromGT.wrap(impl.getConversionFromBase());
    }

    @Override
    public CartesianCS getCoordinateSystem() {
        return CartesianCSFromGT.wrap(impl.getCoordinateSystem());
    }

    @Override
    public GeodeticDatum getDatum() {
        return GeodeticDatumFromGT.wrap(impl.getDatum());
    }
}
