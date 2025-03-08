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

import java.util.ServiceLoader;
import org.opengis.util.FactoryException;
import org.opengis.referencing.cs.CSFactory;
import org.opengis.referencing.cs.CSAuthorityFactory;
import org.opengis.referencing.crs.CRSFactory;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.datum.DatumFactory;
import org.opengis.referencing.datum.DatumAuthorityFactory;
import org.opengis.referencing.operation.CoordinateOperationFactory;
import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;


/**
 * Views overs GeoTools factories.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
public final class Services {
    /**
     * Do not allow instantiations of this class.
     */
    private Services() {
    }

    /**
     * {@return the default factory as provided by the GeoTools <code>CRS</code> class}.
     *
     * @todo {@code CoordinateOperationFactory} is not yet available.
     *
     * @param  method    {@code "getAuthorityFactory"} or {@code "getCoordinateOperationFactory"}.
     * @param  argument  the {@code longitudeFirst} or {@code lenient} argument, for CRS and operation factory respectively.
     * @throws FactoryException if the GeoTools {@link org.geotools.referencing.CRS} class has not been found.
     */
    private static Object provider(final String method, final boolean argument) throws FactoryException {
        try {
            return Class.forName("org.geotools.referencing.CRS").getMethod(method, Boolean.TYPE).invoke(null, argument);
        } catch (ReflectiveOperationException e) {
            throw new FactoryException(e);
        }
    }

    /**
     * {@return the default <abbr>CRS</abbr> authority factory}. This method delegates to the
     * method of the same name in {@code org.geotools.referencing.CRS} and wraps the result.
     *
     * @param  longitudeFirst  whether to force (<var>longitude</var>, <var>latitude</var>) axis order.
     * @throws FactoryException if the GeoTools {@link org.geotools.referencing.CRS} class has not been found.
     */
    public static CRSAuthorityFactory getAuthorityFactory(boolean longitudeFirst) throws FactoryException {
        final Object factory = provider("getAuthorityFactory", longitudeFirst);
        return CRSAuthorityFactoryFromGT.wrap((org.geotools.api.referencing.crs.CRSAuthorityFactory) factory);
    }

    /**
     * {@return the default coordinate operation factory}. This method delegates to the method
     * of the same name in {@code org.geotools.referencing.CRS} and wraps the result.
     *
     * @param  lenient  whether to be lenient about datum shifts without transformation parameters.
     * @throws FactoryException if the GeoTools {@link org.geotools.referencing.CRS} class has not been found.
     */
    public static CoordinateOperationFactory getCoordinateOperationFactory(boolean lenient) throws FactoryException {
        final Object factory = provider("getCoordinateOperationFactory", lenient);
        return CoordinateOperationFactoryFromGT.wrap((org.geotools.api.referencing.operation.CoordinateOperationFactory) factory);
    }

    /**
     * {@return all <abbr>CRS</abbr> authority factories registered by GeoTools}.
     * Note that GeoTools provides many <abbr>EPSG</abbr> factories with different axis order policy.
     * It provides also factories for other authorities and for different code syntax (e.g. <abbr>URN</abbr>).
     * This method returns these factories in no particular order.
     */
    public static Iterable<CRSAuthorityFactory> loadCRSAuthorityFactories() {
        return Wrapper.wrap(ServiceLoader.load(org.geotools.api.referencing.crs.CRSAuthorityFactory.class),
                CRSAuthorityFactoryFromGT::wrap);
    }

    /**
     * {@return all <abbr>CS</abbr> authority factories registered by GeoTools}.
     * Note that GeoTools provides many <abbr>EPSG</abbr> factories with different axis order policy.
     * This method returns these factories in no particular order.
     */
    public static Iterable<CSAuthorityFactory> loadCSAuthorityFactories() {
        return Wrapper.wrap(ServiceLoader.load(org.geotools.api.referencing.cs.CSAuthorityFactory.class),
                CSAuthorityFactoryFromGT::wrap);
    }

    /**
     * {@return all datum authority factories registered by GeoTools}.
     * Note that GeoTools provides many factories for different authorities and code syntax (e.g. <abbr>URN</abbr>).
     * This method returns these factories in no particular order.
     */
    public static Iterable<DatumAuthorityFactory> loadDatumAuthorityFactories() {
        return Wrapper.wrap(ServiceLoader.load(org.geotools.api.referencing.datum.DatumAuthorityFactory.class),
                DatumAuthorityFactoryFromGT::wrap);
    }

    /**
     * {@return all coordinate operation authority factories registered by GeoTools}.
     * Note that GeoTools provides many factories for different code syntax (e.g. <abbr>URN</abbr>).
     * This method returns these factories in no particular order.
     */
    public static Iterable<CoordinateOperationAuthorityFactory> loadCoordinateOperationAuthorityFactories() {
        return Wrapper.wrap(ServiceLoader.load(org.geotools.api.referencing.operation.CoordinateOperationAuthorityFactory.class),
                CoordinateOperationAuthorityFactoryFromGT::wrap);
    }

    /**
     * {@return all <abbr>CRS</abbr> object factories registered by GeoTools}.
     * This method returns these factories in no particular order.
     */
    public static Iterable<CRSFactory> loadCRSFactories() {
        return Wrapper.wrap(ServiceLoader.load(org.geotools.api.referencing.crs.CRSFactory.class),
                CRSFactoryFromGT::wrap);
    }

    /**
     * {@return all <abbr>CS</abbr> object factories registered by GeoTools}.
     * This method returns these factories in no particular order.
     */
    public static Iterable<CSFactory> loadCSFactories() {
        return Wrapper.wrap(ServiceLoader.load(org.geotools.api.referencing.cs.CSFactory.class),
                CSFactoryFromGT::wrap);
    }

    /**
     * {@return all datum object factories registered by GeoTools}.
     * This method returns these factories in no particular order.
     */
    public static Iterable<DatumFactory> loadDatumFactories() {
        return Wrapper.wrap(ServiceLoader.load(org.geotools.api.referencing.datum.DatumFactory.class),
                DatumFactoryFromGT::wrap);
    }

    /**
     * {@return all coordinate operation object factories registered by GeoTools}.
     * This method returns these factories in no particular order.
     */
    public static Iterable<CoordinateOperationFactory> loadCoordinateOperationFactories() {
        return Wrapper.wrap(ServiceLoader.load(org.geotools.api.referencing.operation.CoordinateOperationFactory.class),
                CoordinateOperationFactoryFromGT::wrap);
    }
}
