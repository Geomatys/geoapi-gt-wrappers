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
import org.opengis.referencing.cs.CSAuthorityFactory;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.datum.DatumAuthorityFactory;
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
     * {@return the default <abbr>CRS</abbr> authority factory with axis order as specified by the authority}.
     *
     * @throws FactoryException if the GeoTools {@link org.geotools.referencing.CRS} class has not been found.
     */
    public static CRSAuthorityFactory provider() throws FactoryException {
        final Object factory = provider("getAuthorityFactory", false);
        return CRSAuthorityFactoryFromGT.wrap((org.geotools.api.referencing.crs.CRSAuthorityFactory) factory);
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
}
