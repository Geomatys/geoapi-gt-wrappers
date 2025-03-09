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
import org.geotools.api.referencing.crs.*;
import org.geotools.api.referencing.cs.*;
import org.geotools.api.referencing.datum.*;
import org.geotools.api.referencing.operation.Conversion;
import org.geotools.api.referencing.FactoryException;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CRSFactoryToGT extends ObjectFactoryToGT implements CRSFactory {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.referencing.crs.CRSFactory impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private CRSFactoryToGT(final org.opengis.referencing.crs.CRSFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CRSFactory wrap(final org.opengis.referencing.crs.CRSFactory impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof CRSFactory) {
            var c = (CRSFactory) impl;
            return c;
        }
        if (impl instanceof CRSFactoryFromGT) {
            var c = (CRSFactoryFromGT) impl;
            return c.impl;
        }
        return new CRSFactoryToGT(impl);
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.opengis.referencing.ObjectFactory implementation() {
        return impl;
    }

    @Override
    public GeocentricCRS createGeocentricCRS(Map<String, ?> properties, GeodeticDatum datum, CartesianCS cs) throws FactoryException {
        try {
            return GeocentricCRSToGT.wrap(
                    impl.createGeocentricCRS(properties,
                            GeodeticDatumFromGT.wrap(datum),
                            CartesianCSFromGT.wrap(cs)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public GeocentricCRS createGeocentricCRS(Map<String, ?> properties, GeodeticDatum datum, SphericalCS cs) throws FactoryException {
        try {
            return GeocentricCRSToGT.wrap(
                    impl.createGeocentricCRS(properties,
                            GeodeticDatumFromGT.wrap(datum),
                            SphericalCSFromGT.wrap(cs)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public GeographicCRS createGeographicCRS(Map<String, ?> properties, GeodeticDatum datum, EllipsoidalCS cs) throws FactoryException {
        try {
            return GeographicCRSToGT.wrap(
                    impl.createGeographicCRS(properties,
                            GeodeticDatumFromGT.wrap(datum),
                            EllipsoidalCSFromGT.wrap(cs)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public ProjectedCRS createProjectedCRS(Map<String, ?> properties, GeographicCRS baseCRS,
            Conversion fromBase, CartesianCS cs) throws FactoryException
    {
        try {
            return ProjectedCRSToGT.wrap(
                    impl.createProjectedCRS(properties,
                            GeographicCRSFromGT.wrap(baseCRS),
                            ConversionFromGT.wrap(fromBase),
                            CartesianCSFromGT.wrap(cs)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public DerivedCRS createDerivedCRS(Map<String, ?> properties, CoordinateReferenceSystem baseCRS,
            Conversion fromBase, CoordinateSystem cs) throws FactoryException
    {
        try {
            return DerivedCRSToGT.wrap(
                    impl.createDerivedCRS(properties,
                            CoordinateReferenceSystemFromGT.wrap(baseCRS),
                            ConversionFromGT.wrap(fromBase),
                            CoordinateSystemFromGT.wrap(cs)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public VerticalCRS createVerticalCRS(Map<String, ?> properties, VerticalDatum datum, VerticalCS cs) throws FactoryException {
        try {
            return VerticalCRSToGT.wrap(
                    impl.createVerticalCRS(properties,
                            VerticalDatumFromGT.wrap(datum),
                            VerticalCSFromGT.wrap(cs)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public TemporalCRS createTemporalCRS(Map<String, ?> properties, TemporalDatum datum, TimeCS cs) throws FactoryException {
        try {
            return TemporalCRSToGT.wrap(
                    impl.createTemporalCRS(properties,
                            TemporalDatumFromGT.wrap(datum),
                            TimeCSFromGT.wrap(cs)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public EngineeringCRS createEngineeringCRS(Map<String, ?> properties, EngineeringDatum datum, CoordinateSystem cs) throws FactoryException {
        try {
            return EngineeringCRSToGT.wrap(
                    impl.createEngineeringCRS(properties,
                            EngineeringDatumFromGT.wrap(datum),
                            CoordinateSystemFromGT.wrap(cs)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public ImageCRS createImageCRS(Map<String, ?> properties, ImageDatum datum, AffineCS cs) throws FactoryException {
        try {
            return ImageCRSToGT.wrap(
                    impl.createImageCRS(properties,
                            ImageDatumFromGT.wrap(datum),
                            AffineCSFromGT.wrap(cs)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CompoundCRS createCompoundCRS(Map<String, ?> properties, CoordinateReferenceSystem... components) throws FactoryException {
        final var g = new org.opengis.referencing.crs.CoordinateReferenceSystem[components.length];
        for (int i=0; i<components.length; i++) {
            g[i] = CoordinateReferenceSystemFromGT.wrap(components[i]);
        }
        try {
            return CompoundCRSToGT.wrap(impl.createCompoundCRS(properties, g));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CoordinateReferenceSystem createFromXML(String xml) throws FactoryException {
        try {
            return CoordinateReferenceSystemToGT.wrap(impl.createFromXML(xml));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CoordinateReferenceSystem createFromWKT(String wkt) throws FactoryException {
        try {
            return CoordinateReferenceSystemToGT.wrap(impl.createFromWKT(wkt));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }
}
