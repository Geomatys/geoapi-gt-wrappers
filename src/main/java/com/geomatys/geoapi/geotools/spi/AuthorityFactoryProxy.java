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
package com.geomatys.geoapi.geotools.spi;

import java.util.Set;
import org.opengis.util.FactoryException;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.referencing.IdentifiedObject;
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
import com.geomatys.geoapi.geotools.Services;


/**
 * <abbr>CRS</abbr> authority factory that delegates its work to GeoTools.
 * This is a temporary class for compatibility with applications that put their dependencies on the class-path.
 * This class may be removed in a future version if only module-path is supported (in that latter case,
 * {@link java.util.ServiceLoader} would invoke {@link Services#provider()} directly).
 *
 * @author Martin Desruisseaux (Geomatys)
 */
public class AuthorityFactoryProxy implements CRSAuthorityFactory {
    /**
     * The factory where to delegate all method calls.
     */
    protected final CRSAuthorityFactory factory;

    /**
     * Creates a new authority factory.
     *
     * @throws FactoryException if the GeoTools {@link org.geotools.referencing.CRS} class has not been found.
     */
    public AuthorityFactoryProxy() throws FactoryException {
        factory = Services.provider();
    }

    @Override
    public Citation getVendor() {
        return factory.getVendor();
    }

    @Override
    public Citation getAuthority() {
        return factory.getAuthority();
    }

    @Override
    public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
        return factory.getAuthorityCodes(type);
    }

    @Override
    public InternationalString getDescriptionText(String string) throws FactoryException {
        return factory.getDescriptionText(string);
    }

    @Override
    public IdentifiedObject createObject(String string) throws FactoryException {
        return factory.createObject(string);
    }

    @Override
    public CoordinateReferenceSystem createCoordinateReferenceSystem(String string) throws FactoryException {
        return factory.createCoordinateReferenceSystem(string);
    }

    @Override
    public CompoundCRS createCompoundCRS(String string) throws FactoryException {
        return factory.createCompoundCRS(string);
    }

    @Override
    public DerivedCRS createDerivedCRS(String string) throws FactoryException {
        return factory.createDerivedCRS(string);
    }

    @Override
    public EngineeringCRS createEngineeringCRS(String string) throws FactoryException {
        return factory.createEngineeringCRS(string);
    }

    @Override
    public GeographicCRS createGeographicCRS(String string) throws FactoryException {
        return factory.createGeographicCRS(string);
    }

    @Override
    public GeocentricCRS createGeocentricCRS(String string) throws FactoryException {
        return factory.createGeocentricCRS(string);
    }

    @Override
    public ImageCRS createImageCRS(String string) throws FactoryException {
        return factory.createImageCRS(string);
    }

    @Override
    public ProjectedCRS createProjectedCRS(String string) throws FactoryException {
        return factory.createProjectedCRS(string);
    }

    @Override
    public TemporalCRS createTemporalCRS(String string) throws FactoryException {
        return factory.createTemporalCRS(string);
    }

    @Override
    public VerticalCRS createVerticalCRS(String string) throws FactoryException {
        return factory.createVerticalCRS(string);
    }

    @Override
    public String toString() {
        return factory.toString();
    }
}
