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
     * Tests {@link Services#provider()}.
     *
     * @throws FactoryException if the GeoTools {@link org.geotools.referencing.CRS} class has not been found.
     */
    @Test
    public void testProvider() throws FactoryException {
        assertNotNull(Services.provider());
    }

    /**
     * Tests {@link Services#provider()} fetched through {@link ServiceLoader}.
     *
     * @throws FactoryException if an error occurred while testing the creation of a <abbr>CRS</abbr>.
     */
    @Test
    public void testServiceProvider() throws FactoryException {
        var it = ServiceLoader.load(CRSAuthorityFactory.class).iterator();
        assertTrue(it.hasNext());
        CRSAuthorityFactory factory = it.next();
        assertNotNull(factory.createGeographicCRS("EPSG:4326"));
        assertFalse(it.hasNext());
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
}
