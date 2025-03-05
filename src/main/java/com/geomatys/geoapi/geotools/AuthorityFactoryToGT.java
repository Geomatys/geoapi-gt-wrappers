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

import java.util.Set;
import org.geotools.api.metadata.citation.Citation;
import org.geotools.api.referencing.AuthorityFactory;
import org.geotools.api.referencing.IdentifiedObject;
import org.geotools.api.referencing.NoSuchAuthorityCodeException;
import org.geotools.api.referencing.FactoryException;
import org.geotools.api.util.InternationalString;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
abstract class AuthorityFactoryToGT extends WrapperToGT implements AuthorityFactory {
    /**
     * GeoTools interfaces recognized by the factories.
     * Most specialized interfaces (i.e., the ones having precedence when testing for a match) shall be last.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})    // Generic array creation.
    static final Class<? extends IdentifiedObject>[] GEOAPI_INTERFACES = new Class[] {
        org.geotools.api.referencing.IdentifiedObject.class,
        org.geotools.api.referencing.cs.CoordinateSystemAxis.class,
        org.geotools.api.referencing.cs.CoordinateSystem.class,
        org.geotools.api.referencing.cs.PolarCS.class,
        org.geotools.api.referencing.cs.CylindricalCS.class,
        org.geotools.api.referencing.cs.AffineCS.class,
        org.geotools.api.referencing.cs.CartesianCS.class,
        org.geotools.api.referencing.cs.SphericalCS.class,
        org.geotools.api.referencing.cs.EllipsoidalCS.class,
        org.geotools.api.referencing.cs.VerticalCS.class,
        org.geotools.api.referencing.cs.TimeCS.class,
        org.geotools.api.referencing.datum.Ellipsoid.class,
        org.geotools.api.referencing.datum.PrimeMeridian.class,
        org.geotools.api.referencing.datum.Datum.class,
        org.geotools.api.referencing.datum.EngineeringDatum.class,
        org.geotools.api.referencing.datum.ImageDatum.class,
        org.geotools.api.referencing.datum.GeodeticDatum.class,
        org.geotools.api.referencing.datum.VerticalDatum.class,
        org.geotools.api.referencing.datum.TemporalDatum.class,
        org.geotools.api.referencing.crs.CoordinateReferenceSystem.class,
        org.geotools.api.referencing.crs.CompoundCRS.class,
        org.geotools.api.referencing.crs.SingleCRS.class,
        org.geotools.api.referencing.crs.GeocentricCRS.class,
        org.geotools.api.referencing.crs.GeographicCRS.class,
        org.geotools.api.referencing.crs.VerticalCRS.class,
        org.geotools.api.referencing.crs.TemporalCRS.class,
        org.geotools.api.referencing.crs.EngineeringCRS.class,
        org.geotools.api.referencing.crs.ImageCRS.class,
        org.geotools.api.referencing.crs.DerivedCRS.class,
        org.geotools.api.referencing.crs.ProjectedCRS.class,
        org.geotools.api.referencing.operation.OperationMethod.class,
        org.geotools.api.referencing.operation.CoordinateOperation.class
    };

    /**
     * GeoAPI interfaces for each GeoTools interfaces.
     * The indexes much match with {@link #GEOAPI_INTERFACES}.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})    // Generic array creation.
    static final Class<? extends org.opengis.referencing.IdentifiedObject>[] GEOTOOLS_INTERFACES = new Class[] {
        org.opengis.referencing.IdentifiedObject.class,
        org.opengis.referencing.cs.CoordinateSystemAxis.class,
        org.opengis.referencing.cs.CoordinateSystem.class,
        org.opengis.referencing.cs.PolarCS.class,
        org.opengis.referencing.cs.CylindricalCS.class,
        org.opengis.referencing.cs.AffineCS.class,
        org.opengis.referencing.cs.CartesianCS.class,
        org.opengis.referencing.cs.SphericalCS.class,
        org.opengis.referencing.cs.EllipsoidalCS.class,
        org.opengis.referencing.cs.VerticalCS.class,
        org.opengis.referencing.cs.TimeCS.class,
        org.opengis.referencing.datum.Ellipsoid.class,
        org.opengis.referencing.datum.PrimeMeridian.class,
        org.opengis.referencing.datum.Datum.class,
        org.opengis.referencing.datum.EngineeringDatum.class,
        org.opengis.referencing.datum.ImageDatum.class,
        org.opengis.referencing.datum.GeodeticDatum.class,
        org.opengis.referencing.datum.VerticalDatum.class,
        org.opengis.referencing.datum.TemporalDatum.class,
        org.opengis.referencing.crs.CoordinateReferenceSystem.class,
        org.opengis.referencing.crs.CompoundCRS.class,
        org.opengis.referencing.crs.SingleCRS.class,
        org.opengis.referencing.crs.GeocentricCRS.class,
        org.opengis.referencing.crs.GeographicCRS.class,
        org.opengis.referencing.crs.VerticalCRS.class,
        org.opengis.referencing.crs.TemporalCRS.class,
        org.opengis.referencing.crs.EngineeringCRS.class,
        org.opengis.referencing.crs.ImageCRS.class,
        org.opengis.referencing.crs.DerivedCRS.class,
        org.opengis.referencing.crs.ProjectedCRS.class,
        org.opengis.referencing.operation.OperationMethod.class,
        org.opengis.referencing.operation.CoordinateOperation.class
    };

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     */
    AuthorityFactoryToGT() {
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    abstract org.opengis.referencing.AuthorityFactory implementation();

    @Override
    public Citation getAuthority() {
        return CitationToGT.wrap(implementation().getAuthority());
    }

    @Override
    public Citation getVendor() {
        return CitationToGT.wrap(implementation().getVendor());
    }

    @Override
    public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
        Class<? extends org.opengis.referencing.IdentifiedObject> gt = null;
        if (type != null) {
            for (int i = GEOAPI_INTERFACES.length; --i >= 0;) {
                if (GEOAPI_INTERFACES[i].isAssignableFrom(type)) {
                    gt = GEOTOOLS_INTERFACES[i];
                    break;
                }
            }
            // Should never be null at this point.
        }
        try {
            return implementation().getAuthorityCodes(gt);
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public InternationalString getDescriptionText(String code) throws FactoryException {
        try {
            return InternationalStringToGT.wrap(implementation().getDescriptionText(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public IdentifiedObject createObject(String code) throws FactoryException {
        try {
            return IdentifiedObjectToGT.wrap(implementation().createObject(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    /**
     * Wraps a GeoAPI exception into a GeoTools exception.
     *
     * @param e the GeoAPI exception.
     * @return the GeoTools exception.
     */
    static FactoryException wrap(org.opengis.util.FactoryException e) {
        if (e instanceof org.opengis.referencing.NoSuchAuthorityCodeException c) {
            return (NoSuchAuthorityCodeException) new NoSuchAuthorityCodeException(
                    c.getMessage(), c.getAuthority(), c.getAuthorityCode()).initCause(c);
        }
        return new FactoryException(e.getMessage(), e);
    }
}
