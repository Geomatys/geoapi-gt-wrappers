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
import org.opengis.referencing.cs.CSAuthorityFactory;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.cs.CylindricalCS;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.PolarCS;
import org.opengis.referencing.cs.SphericalCS;
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.cs.VerticalCS;
import org.opengis.util.FactoryException;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CSAuthorityFactoryFromGT extends AuthorityFactoryFromGT implements CSAuthorityFactory {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.referencing.cs.CSAuthorityFactory impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private CSAuthorityFactoryFromGT(final org.geotools.api.referencing.cs.CSAuthorityFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CSAuthorityFactory wrap(final org.geotools.api.referencing.cs.CSAuthorityFactory impl) {
        switch (impl) {
            case null: return null;
            case CSAuthorityFactory c: return c;
            case CSAuthorityFactoryToGT c: return c.impl;
            default: return new CSAuthorityFactoryFromGT(impl);
        }
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.geotools.api.referencing.AuthorityFactory implementation() {
        return impl;
    }

    @Override
    public Unit<?> createUnit(String code) throws FactoryException {
        try {
            return impl.createUnit(code);
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CoordinateSystemAxis createCoordinateSystemAxis(String code) throws FactoryException {
        try {
            return CoordinateSystemAxisFromGT.wrap(impl.createCoordinateSystemAxis(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CoordinateSystem createCoordinateSystem(String code) throws FactoryException {
        try {
            return CoordinateSystemFromGT.wrap(impl.createCoordinateSystem(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CartesianCS createCartesianCS(String code) throws FactoryException {
        try {
            return CartesianCSFromGT.wrap(impl.createCartesianCS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public SphericalCS createSphericalCS(String code) throws FactoryException {
        try {
            return SphericalCSFromGT.wrap(impl.createSphericalCS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public EllipsoidalCS createEllipsoidalCS(String code) throws FactoryException {
        try {
            return EllipsoidalCSFromGT.wrap(impl.createEllipsoidalCS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public VerticalCS createVerticalCS(String code) throws FactoryException {
        try {
            return VerticalCSFromGT.wrap(impl.createVerticalCS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public TimeCS createTimeCS(String code) throws FactoryException {
        try {
            return TimeCSFromGT.wrap(impl.createTimeCS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public PolarCS createPolarCS(String code) throws FactoryException {
        try {
            return PolarCSFromGT.wrap(impl.createPolarCS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CylindricalCS createCylindricalCS(String code) throws FactoryException {
        try {
            return CylindricalCSFromGT.wrap(impl.createCylindricalCS(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }
}
