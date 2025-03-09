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
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.util.FactoryException;
import org.opengis.util.InternationalString;


/**
 * GeoAPI wrapper for an authority factory from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
abstract class AuthorityFactoryFromGT extends WrapperFromGT implements AuthorityFactory {
    /**
     * GeoAPI interfaces recognized by the factories.
     * Most specialized interfaces (i.e., the ones having precedence when testing for a match) shall be last.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})    // Generic array creation.
    static final Class<? extends IdentifiedObject>[] GEOAPI_INTERFACES = new Class[] {
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
     * GeoTools interfaces for each GeoAPI interfaces.
     * The indexes much match with {@link #GEOAPI_INTERFACES}.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})    // Generic array creation.
    static final Class<? extends org.geotools.api.referencing.IdentifiedObject>[] GEOTOOLS_INTERFACES = new Class[] {
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
     * Creates a new wrapper for the given GeoTools implementation.
     */
    AuthorityFactoryFromGT() {
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    abstract org.geotools.api.referencing.AuthorityFactory implementation();

    @Override
    public Citation getAuthority() {
        return CitationFromGT.wrap(implementation().getAuthority());
    }

    @Override
    public Citation getVendor() {
        return CitationFromGT.wrap(implementation().getVendor());
    }

    @Override
    public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
        Class<? extends org.geotools.api.referencing.IdentifiedObject> gt = null;
        if (type != null) {
            final var interfaces = GEOAPI_INTERFACES;
            for (int i = interfaces.length; --i >= 0;) {
                if (interfaces[i].isAssignableFrom(type)) {
                    gt = GEOTOOLS_INTERFACES[i];
                    break;
                }
            }
            // Should never be null at this point.
        }
        try {
            return implementation().getAuthorityCodes(gt);
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public InternationalString getDescriptionText(String code) throws FactoryException {
        try {
            return InternationalStringFromGT.wrap(implementation().getDescriptionText(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public IdentifiedObject createObject(String code) throws FactoryException {
        try {
            return IdentifiedObjectFromGT.wrap(implementation().createObject(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    /**
     * Wraps a GeoTools exception into a GeoAPI exception.
     *
     * @param e the GeoTools exception.
     * @return the GeoAPI exception.
     */
    static FactoryException wrap(org.geotools.api.referencing.FactoryException e) {
        if (e instanceof org.geotools.api.referencing.NoSuchAuthorityCodeException) {
            var c = (org.geotools.api.referencing.NoSuchAuthorityCodeException) e;
            return (NoSuchAuthorityCodeException) new NoSuchAuthorityCodeException(
                    c.getMessage(), c.getAuthority(), c.getAuthorityCode()).initCause(c);
        }
        return new FactoryException(e.getMessage(), e);
    }
}
