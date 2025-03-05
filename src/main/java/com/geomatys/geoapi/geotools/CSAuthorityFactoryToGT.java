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

import javax.measure.Unit;
import org.geotools.api.referencing.cs.CSAuthorityFactory;
import org.geotools.api.referencing.cs.CartesianCS;
import org.geotools.api.referencing.cs.CoordinateSystem;
import org.geotools.api.referencing.cs.CoordinateSystemAxis;
import org.geotools.api.referencing.cs.CylindricalCS;
import org.geotools.api.referencing.cs.EllipsoidalCS;
import org.geotools.api.referencing.cs.PolarCS;
import org.geotools.api.referencing.cs.SphericalCS;
import org.geotools.api.referencing.cs.TimeCS;
import org.geotools.api.referencing.cs.VerticalCS;
import org.geotools.api.referencing.FactoryException;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CSAuthorityFactoryToGT extends AuthorityFactoryToGT implements CSAuthorityFactory {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.referencing.cs.CSAuthorityFactory impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private CSAuthorityFactoryToGT(final org.opengis.referencing.cs.CSAuthorityFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CSAuthorityFactory wrap(final org.opengis.referencing.cs.CSAuthorityFactory impl) {
        switch (impl) {
            case null: return null;
            case CSAuthorityFactory c: return c;
            case CSAuthorityFactoryFromGT c: return c.impl;
            default: return new CSAuthorityFactoryToGT(impl);
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
    public Unit<?> createUnit(String code) throws FactoryException {
        try {
            return impl.createUnit(code);
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CoordinateSystemAxis createCoordinateSystemAxis(String code) throws FactoryException {
        try {
            return CoordinateSystemAxisToGT.wrap(impl.createCoordinateSystemAxis(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CoordinateSystem createCoordinateSystem(String code) throws FactoryException {
        try {
            return CoordinateSystemToGT.wrap(impl.createCoordinateSystem(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CartesianCS createCartesianCS(String code) throws FactoryException {
        try {
            return CartesianCSToGT.wrap(impl.createCartesianCS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public SphericalCS createSphericalCS(String code) throws FactoryException {
        try {
            return SphericalCSToGT.wrap(impl.createSphericalCS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public EllipsoidalCS createEllipsoidalCS(String code) throws FactoryException {
        try {
            return EllipsoidalCSToGT.wrap(impl.createEllipsoidalCS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public VerticalCS createVerticalCS(String code) throws FactoryException {
        try {
            return VerticalCSToGT.wrap(impl.createVerticalCS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public TimeCS createTimeCS(String code) throws FactoryException {
        try {
            return TimeCSToGT.wrap(impl.createTimeCS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public PolarCS createPolarCS(String code) throws FactoryException {
        try {
            return PolarCSToGT.wrap(impl.createPolarCS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CylindricalCS createCylindricalCS(String code) throws FactoryException {
        try {
            return CylindricalCSToGT.wrap(impl.createCylindricalCS(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }
}
