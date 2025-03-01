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
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.PrimeMeridian;
import org.opengis.referencing.operation.Conversion;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.util.FactoryException;
import org.geotools.referencing.CRS;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Tests fetching a <abbr>CRS</abbr> from the authority factory.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CRSAuthorityFactoryTest {
    /**
     * The factory to test.
     */
    private final CRSAuthorityFactory factory;

    /**
     * Creates a new test case.
     */
    public CRSAuthorityFactoryTest() {
        factory = CRSAuthorityFactoryFromGT.wrap(CRS.getAuthorityFactory(false));
    }

    /**
     * Tests {@code EPSG:4326} (a geographic <abbr>CRS</abbr>).
     *
     * @throws FactoryException if an error occurred.
     */
    @Test
    public void testEPSG_4326() throws FactoryException {
        GeographicCRS crs = factory.createGeographicCRS("EPSG:4326");
        assertWGS84(crs.getDatum());
    }

    /**
     * Verifies a few properties expected for the <abbr>WGS84</abbr> datum.
     */
    private static void assertWGS84(final GeodeticDatum datum) {
        PrimeMeridian pm = datum.getPrimeMeridian();
        assertEquals(0, pm.getGreenwichLongitude());
        Ellipsoid ellipsoid = datum.getEllipsoid();
        assertEquals(6378137, ellipsoid.getSemiMajorAxis());
    }

    /**
     * Tests {@code EPSG:3395} (a projected <abbr>CRS</abbr>).
     *
     * @throws FactoryException if an error occurred.
     */
    @Test
    public void testEPSG_3395() throws FactoryException {
        ProjectedCRS crs = factory.createProjectedCRS("EPSG:3395");
        assertWGS84(crs.getDatum());
        Conversion fromBase = crs.getConversionFromBase();
        ParameterValueGroup params = fromBase.getParameterValues();
        assertEquals(0, params.parameter("central_meridian").doubleValue());
        assertEquals(1, params.parameter("scale_factor").doubleValue());
    }
}
