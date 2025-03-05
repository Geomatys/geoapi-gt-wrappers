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

import org.geotools.api.referencing.crs.CRSAuthorityFactory;
import org.geotools.api.referencing.crs.CompoundCRS;
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.api.referencing.crs.DerivedCRS;
import org.geotools.api.referencing.crs.EngineeringCRS;
import org.geotools.api.referencing.crs.GeocentricCRS;
import org.geotools.api.referencing.crs.GeographicCRS;
import org.geotools.api.referencing.crs.ImageCRS;
import org.geotools.api.referencing.crs.ProjectedCRS;
import org.geotools.api.referencing.crs.TemporalCRS;
import org.geotools.api.referencing.crs.VerticalCRS;
import org.geotools.api.referencing.FactoryException;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CRSAuthorityFactoryToGT extends AuthorityFactoryToGT implements CRSAuthorityFactory {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    private final org.opengis.referencing.crs.CRSAuthorityFactory impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private CRSAuthorityFactoryToGT(final org.opengis.referencing.crs.CRSAuthorityFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CRSAuthorityFactory wrap(final org.opengis.referencing.crs.CRSAuthorityFactory impl) {
        switch (impl) {
            case null: return null;
            case CRSAuthorityFactory c: return c;
            default: return new CRSAuthorityFactoryToGT(impl);
        }
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.opengis.referencing.AuthorityFactory implementation() {
        return impl;
    }

    @Override
    public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
        try {
            return CoordinateReferenceSystemToGT.wrap(impl.createCoordinateReferenceSystem(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public GeographicCRS createGeographicCRS(String code) throws FactoryException {
        try {
            return GeographicCRSToGT.wrap(impl.createGeographicCRS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public GeocentricCRS createGeocentricCRS(String code) throws FactoryException {
        try {
            return GeocentricCRSToGT.wrap(impl.createGeocentricCRS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public ProjectedCRS createProjectedCRS(String code) throws FactoryException {
        try {
            return ProjectedCRSToGT.wrap(impl.createProjectedCRS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public VerticalCRS createVerticalCRS(String code) throws FactoryException {
        try {
            return VerticalCRSToGT.wrap(impl.createVerticalCRS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public TemporalCRS createTemporalCRS(String code) throws FactoryException {
        try {
            return TemporalCRSToGT.wrap(impl.createTemporalCRS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public EngineeringCRS createEngineeringCRS(String code) throws FactoryException {
        try {
            return EngineeringCRSToGT.wrap(impl.createEngineeringCRS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public ImageCRS createImageCRS(String code) throws FactoryException {
        try {
            return ImageCRSToGT.wrap(impl.createImageCRS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public DerivedCRS createDerivedCRS(String code) throws FactoryException {
        try {
            return DerivedCRSToGT.wrap(impl.createDerivedCRS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CompoundCRS createCompoundCRS(String code) throws FactoryException {
        try {
            return CompoundCRSToGT.wrap(impl.createCompoundCRS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }
}
