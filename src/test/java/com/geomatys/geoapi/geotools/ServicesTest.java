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
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.operation.CoordinateOperationFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Tests {@link Services}. This class merely verifies that at least one service is found.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
public class ServicesTest {
    /**
     * Creates a new test case.
     */
    public ServicesTest() {
    }

    /**
     * Tests {@link Services#getAuthorityFactory(boolean)}.
     *
     * @throws FactoryException if the GeoTools {@link org.geotools.referencing.CRS} class has not been found.
     */
    @Test
    public void testGetAuthorityFactory() throws FactoryException {
        assertNotNull(Services.getAuthorityFactory(false));
    }

    /**
     * Tests {@link Services#getCoordinateOperationFactory(boolean)}.
     *
     * @throws FactoryException if the GeoTools {@link org.geotools.referencing.CRS} class has not been found.
     */
    @Test
    public void testGetCoordinateOperationFactory() throws FactoryException {
        assertNotNull(Services.getCoordinateOperationFactory(false));
    }

    /**
     * Verifies that {@link Services#loadCRSAuthorityFactories()} finds at least one factory.
     */
    @Test
    public void testCRSAuthorityFactories() {
        assertTrue(Services.loadCRSAuthorityFactories().iterator().hasNext());
    }

    /**
     * Verifies that {@link Services#loadCSAuthorityFactories()} finds at least one factory.
     */
    @Test
    public void testCSAuthorityFactories() {
        assertTrue(Services.loadCSAuthorityFactories().iterator().hasNext());
    }

    /**
     * Verifies that {@link Services#loadDatumAuthorityFactories()} finds at least one factory.
     */
    @Test
    public void testDatumAuthorityFactories() {
        assertTrue(Services.loadDatumAuthorityFactories().iterator().hasNext());
    }

    /**
     * Verifies that {@link Services#loadCoordinateOperationAuthorityFactories()} finds at least one factory.
     */
    @Test
    public void testCoordinateOperationAuthorityFactories() {
        assertTrue(Services.loadCoordinateOperationAuthorityFactories().iterator().hasNext());
    }

    /**
     * Verifies that {@link Services#loadCRSFactories()} finds at least one factory.
     */
    @Test
    public void testCRSFactories() {
        assertTrue(Services.loadCRSFactories().iterator().hasNext());
    }

    /**
     * Verifies that {@link Services#loadCSFactories()} finds at least one factory.
     */
    @Test
    public void testCSFactories() {
        assertTrue(Services.loadCSFactories().iterator().hasNext());
    }

    /**
     * Verifies that {@link Services#loadDatumFactories()} finds at least one factory.
     */
    @Test
    public void testDatumFactories() {
        assertTrue(Services.loadDatumFactories().iterator().hasNext());
    }

    /**
     * Verifies that {@link Services#loadCoordinateOperationFactories()} finds at least one factory.
     */
    @Test
    public void testCoordinateOperationFactories() {
        assertTrue(Services.loadCoordinateOperationFactories().iterator().hasNext());
    }

    /**
     * Tests {@link Services#getAuthorityFactory(boolean)} fetched through {@link ServiceLoader}.
     *
     * @throws FactoryException if an error occurred while testing the creation of a <abbr>CRS</abbr>.
     */
    @Test
    public void testAuthorityFactoryProxy() throws FactoryException {
        var it = ServiceLoader.load(CRSAuthorityFactory.class).iterator();
        assertTrue(it.hasNext());
        CRSAuthorityFactory factory = it.next();
        assertFalse(it.hasNext());

        assertNotNull(factory.createGeographicCRS("EPSG:4326"));
    }

    /**
     * Tests {@link Services#getCoordinateOperationFactory(boolean)} fetched through {@link ServiceLoader}.
     *
     * @throws FactoryException if an error occurred while testing the creation of an operation.
     */
    @Test
    public void testOperationFactoryProxy() throws FactoryException {
        var it = ServiceLoader.load(CoordinateOperationFactory.class).iterator();
        assertTrue(it.hasNext());
        CoordinateOperationFactory factory = it.next();
        assertFalse(it.hasNext());

        var crs = Services.getAuthorityFactory(false).createGeographicCRS("EPSG:4326");
        assertTrue(factory.createOperation(crs, crs).getMathTransform().isIdentity());
    }
}
