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

import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CompoundCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.DerivedCRS;
import org.opengis.referencing.crs.EngineeringCRS;
import org.opengis.referencing.crs.GeocentricCRS;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.ImageCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.util.FactoryException;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CRSAuthorityFactoryFromGT extends AuthorityFactoryFromGT implements CRSAuthorityFactory {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.referencing.crs.CRSAuthorityFactory impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private CRSAuthorityFactoryFromGT(final org.geotools.api.referencing.crs.CRSAuthorityFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CRSAuthorityFactory wrap(final org.geotools.api.referencing.crs.CRSAuthorityFactory impl) {
        switch (impl) {
            case null: return null;
            case CRSAuthorityFactory c: return c;
            case CRSAuthorityFactoryToGT c: return c.impl;
            default: return new CRSAuthorityFactoryFromGT(impl);
        }
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.geotools.api.referencing.AuthorityFactory implementation() {
        return impl;
    }

    @Override
    public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
        try {
            return CoordinateReferenceSystemFromGT.wrap(impl.createCoordinateReferenceSystem(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public GeographicCRS createGeographicCRS(String code) throws FactoryException {
        try {
            return GeographicCRSFromGT.wrap(impl.createGeographicCRS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public GeocentricCRS createGeocentricCRS(String code) throws FactoryException {
        try {
            return GeocentricCRSFromGT.wrap(impl.createGeocentricCRS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public ProjectedCRS createProjectedCRS(String code) throws FactoryException {
        try {
            return ProjectedCRSFromGT.wrap(impl.createProjectedCRS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public VerticalCRS createVerticalCRS(String code) throws FactoryException {
        try {
            return VerticalCRSFromGT.wrap(impl.createVerticalCRS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public TemporalCRS createTemporalCRS(String code) throws FactoryException {
        try {
            return TemporalCRSFromGT.wrap(impl.createTemporalCRS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public EngineeringCRS createEngineeringCRS(String code) throws FactoryException {
        try {
            return EngineeringCRSFromGT.wrap(impl.createEngineeringCRS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public ImageCRS createImageCRS(String code) throws FactoryException {
        try {
            return ImageCRSFromGT.wrap(impl.createImageCRS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public DerivedCRS createDerivedCRS(String code) throws FactoryException {
        try {
            return DerivedCRSFromGT.wrap(impl.createDerivedCRS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CompoundCRS createCompoundCRS(String code) throws FactoryException {
        try {
            return CompoundCRSFromGT.wrap(impl.createCompoundCRS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }
}
