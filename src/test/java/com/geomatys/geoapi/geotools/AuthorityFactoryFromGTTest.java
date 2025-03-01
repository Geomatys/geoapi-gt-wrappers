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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Tests {@link AuthorityFactoryFromGT}.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
public class AuthorityFactoryFromGTTest {
    /**
     * Creates a new test case.
     */
    public AuthorityFactoryFromGTTest() {
    }

    /**
     * Verifies the mapping between GeoAPI and GeoTools interface.
     */
    @Test
    public void verifyInterfaceMapping() {
        final int n = AuthorityFactoryFromGT.GEOAPI_INTERFACES.length;
        assertEquals(n, AuthorityFactoryFromGT.GEOTOOLS_INTERFACES.length);
        for (int i=0; i<n; i++) {
            Class<?> api = AuthorityFactoryFromGT.GEOAPI_INTERFACES[i];
            Class<?> gt  = AuthorityFactoryFromGT.GEOTOOLS_INTERFACES[i];
            assertTrue(org.opengis.referencing.IdentifiedObject.class.isAssignableFrom(api), api.getName());
            assertTrue(org.geotools.api.referencing.IdentifiedObject.class.isAssignableFrom(gt), gt.getName());
            assertEquals(api.getSimpleName(), gt.getSimpleName(), "Mismatched indexes.");
        }
    }
}
