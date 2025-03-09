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
import org.opengis.referencing.crs.*;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.Conversion;
import org.opengis.util.FactoryException;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CRSFactoryFromGT extends ObjectFactoryFromGT implements CRSFactory {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.referencing.crs.CRSFactory impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private CRSFactoryFromGT(final org.geotools.api.referencing.crs.CRSFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CRSFactory wrap(final org.geotools.api.referencing.crs.CRSFactory impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof CRSFactory) {
            var c = (CRSFactory) impl;
            return c;
        }
        if (impl instanceof CRSFactoryToGT) {
            var c = (CRSFactoryToGT) impl;
            return c.impl;
        }
        return new CRSFactoryFromGT(impl);
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.geotools.api.referencing.ObjectFactory implementation() {
        return impl;
    }

    @Override
    public GeocentricCRS createGeocentricCRS(Map<String, ?> properties, GeodeticDatum datum, CartesianCS cs) throws FactoryException {
        try {
            return GeocentricCRSFromGT.wrap(
                    impl.createGeocentricCRS(properties,
                            GeodeticDatumToGT.wrap(datum),
                            CartesianCSToGT.wrap(cs)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public GeocentricCRS createGeocentricCRS(Map<String, ?> properties, GeodeticDatum datum, SphericalCS cs) throws FactoryException {
        try {
            return GeocentricCRSFromGT.wrap(
                    impl.createGeocentricCRS(properties,
                            GeodeticDatumToGT.wrap(datum),
                            SphericalCSToGT.wrap(cs)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public GeographicCRS createGeographicCRS(Map<String, ?> properties, GeodeticDatum datum, EllipsoidalCS cs) throws FactoryException {
        try {
            return GeographicCRSFromGT.wrap(
                    impl.createGeographicCRS(properties,
                            GeodeticDatumToGT.wrap(datum),
                            EllipsoidalCSToGT.wrap(cs)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public ProjectedCRS createProjectedCRS(Map<String, ?> properties, GeographicCRS baseCRS,
            Conversion fromBase, CartesianCS cs) throws FactoryException
    {
        try {
            return ProjectedCRSFromGT.wrap(
                    impl.createProjectedCRS(properties,
                            GeographicCRSToGT.wrap(baseCRS),
                            ConversionToGT.wrap(fromBase),
                            CartesianCSToGT.wrap(cs)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public DerivedCRS createDerivedCRS(Map<String, ?> properties, CoordinateReferenceSystem baseCRS,
            Conversion fromBase, CoordinateSystem cs) throws FactoryException
    {
        try {
            return DerivedCRSFromGT.wrap(
                    impl.createDerivedCRS(properties,
                            CoordinateReferenceSystemToGT.wrap(baseCRS),
                            ConversionToGT.wrap(fromBase),
                            CoordinateSystemToGT.wrap(cs)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public VerticalCRS createVerticalCRS(Map<String, ?> properties, VerticalDatum datum, VerticalCS cs) throws FactoryException {
        try {
            return VerticalCRSFromGT.wrap(
                    impl.createVerticalCRS(properties,
                            VerticalDatumToGT.wrap(datum),
                            VerticalCSToGT.wrap(cs)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public TemporalCRS createTemporalCRS(Map<String, ?> properties, TemporalDatum datum, TimeCS cs) throws FactoryException {
        try {
            return TemporalCRSFromGT.wrap(
                    impl.createTemporalCRS(properties,
                            TemporalDatumToGT.wrap(datum),
                            TimeCSToGT.wrap(cs)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public EngineeringCRS createEngineeringCRS(Map<String, ?> properties, EngineeringDatum datum, CoordinateSystem cs) throws FactoryException {
        try {
            return EngineeringCRSFromGT.wrap(
                    impl.createEngineeringCRS(properties,
                            EngineeringDatumToGT.wrap(datum),
                            CoordinateSystemToGT.wrap(cs)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public ImageCRS createImageCRS(Map<String, ?> properties, ImageDatum datum, AffineCS cs) throws FactoryException {
        try {
            return ImageCRSFromGT.wrap(
                    impl.createImageCRS(properties,
                            ImageDatumToGT.wrap(datum),
                            AffineCSToGT.wrap(cs)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CompoundCRS createCompoundCRS(Map<String, ?> properties, CoordinateReferenceSystem... components) throws FactoryException {
        final var g = new org.geotools.api.referencing.crs.CoordinateReferenceSystem[components.length];
        for (int i=0; i<components.length; i++) {
            g[i] = CoordinateReferenceSystemToGT.wrap(components[i]);
        }
        try {
            return CompoundCRSFromGT.wrap(impl.createCompoundCRS(properties, g));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CoordinateReferenceSystem createFromXML(String xml) throws FactoryException {
        try {
            return CoordinateReferenceSystemFromGT.wrap(impl.createFromXML(xml));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CoordinateReferenceSystem createFromWKT(String wkt) throws FactoryException {
        try {
            return CoordinateReferenceSystemFromGT.wrap(impl.createFromWKT(wkt));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }
}
