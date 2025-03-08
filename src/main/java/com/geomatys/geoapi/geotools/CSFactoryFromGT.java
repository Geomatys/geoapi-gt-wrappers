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

import java.util.Map;
import javax.measure.Unit;
import org.opengis.referencing.cs.*;
import org.opengis.util.FactoryException;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CSFactoryFromGT extends ObjectFactoryFromGT implements CSFactory {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.referencing.cs.CSFactory impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private CSFactoryFromGT(final org.geotools.api.referencing.cs.CSFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CSFactory wrap(final org.geotools.api.referencing.cs.CSFactory impl) {
        switch (impl) {
            case null: return null;
            case CSFactory c: return c;
            case CSFactoryToGT c: return c.impl;
            default: return new CSFactoryFromGT(impl);
        }
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.geotools.api.referencing.ObjectFactory implementation() {
        return impl;
    }

    @Override
    public CoordinateSystemAxis createCoordinateSystemAxis(Map<String, ?> properties,
            String abbreviation, AxisDirection direction, Unit<?> unit) throws FactoryException
    {
        try {
            return CoordinateSystemAxisFromGT.wrap(impl.createCoordinateSystemAxis(properties, abbreviation,
                    WrapperToGT.wrap(direction, org.geotools.api.referencing.cs.AxisDirection::valueOf), unit));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CartesianCS createCartesianCS(Map<String, ?> properties,
                                         CoordinateSystemAxis axis0,
                                         CoordinateSystemAxis axis1)
            throws FactoryException
    {
        try {
            return CartesianCSFromGT.wrap(
                    impl.createCartesianCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0),
                            CoordinateSystemAxisToGT.wrap(axis1)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CartesianCS createCartesianCS(Map<String, ?> properties,
                                         CoordinateSystemAxis axis0,
                                         CoordinateSystemAxis axis1,
                                         CoordinateSystemAxis axis2)
            throws FactoryException
    {
        try {
            return CartesianCSFromGT.wrap(
                    impl.createCartesianCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0),
                            CoordinateSystemAxisToGT.wrap(axis1),
                            CoordinateSystemAxisToGT.wrap(axis2)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public AffineCS createAffineCS(Map<String, ?> properties,
                                   CoordinateSystemAxis axis0,
                                   CoordinateSystemAxis axis1)
            throws FactoryException
    {
        try {
            return AffineCSFromGT.wrap(
                    impl.createAffineCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0),
                            CoordinateSystemAxisToGT.wrap(axis1)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public AffineCS createAffineCS(Map<String, ?> properties,
                                   CoordinateSystemAxis axis0,
                                   CoordinateSystemAxis axis1,
                                   CoordinateSystemAxis axis2)
            throws FactoryException
    {
        try {
            return AffineCSFromGT.wrap(
                    impl.createAffineCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0),
                            CoordinateSystemAxisToGT.wrap(axis1),
                            CoordinateSystemAxisToGT.wrap(axis2)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public PolarCS createPolarCS(Map<String, ?> properties,
                                 CoordinateSystemAxis axis0,
                                 CoordinateSystemAxis axis1)
            throws FactoryException
    {
        try {
            return PolarCSFromGT.wrap(
                    impl.createPolarCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0),
                            CoordinateSystemAxisToGT.wrap(axis1)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public CylindricalCS createCylindricalCS(Map<String, ?> properties,
                                             CoordinateSystemAxis axis0,
                                             CoordinateSystemAxis axis1,
                                             CoordinateSystemAxis axis2)
            throws FactoryException
    {
        try {
            return CylindricalCSFromGT.wrap(
                    impl.createCylindricalCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0),
                            CoordinateSystemAxisToGT.wrap(axis1),
                            CoordinateSystemAxisToGT.wrap(axis2)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public SphericalCS createSphericalCS(Map<String, ?> properties,
                                         CoordinateSystemAxis axis0,
                                         CoordinateSystemAxis axis1,
                                         CoordinateSystemAxis axis2)
            throws FactoryException
    {
        try {
            return SphericalCSFromGT.wrap(
                    impl.createSphericalCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0),
                            CoordinateSystemAxisToGT.wrap(axis1),
                            CoordinateSystemAxisToGT.wrap(axis2)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public EllipsoidalCS createEllipsoidalCS(Map<String, ?> properties,
                                             CoordinateSystemAxis axis0,
                                             CoordinateSystemAxis axis1)
            throws FactoryException
    {
        try {
            return EllipsoidalCSFromGT.wrap(
                    impl.createEllipsoidalCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0),
                            CoordinateSystemAxisToGT.wrap(axis1)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public EllipsoidalCS createEllipsoidalCS(Map<String, ?> properties,
                                             CoordinateSystemAxis axis0,
                                             CoordinateSystemAxis axis1,
                                             CoordinateSystemAxis axis2)
            throws FactoryException
    {
        try {
            return EllipsoidalCSFromGT.wrap(
                    impl.createEllipsoidalCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0),
                            CoordinateSystemAxisToGT.wrap(axis1),
                            CoordinateSystemAxisToGT.wrap(axis2)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public VerticalCS createVerticalCS(Map<String, ?> properties,
                                       CoordinateSystemAxis axis0)
            throws FactoryException
    {
        try {
            return VerticalCSFromGT.wrap(
                    impl.createVerticalCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public TimeCS createTimeCS(Map<String, ?> properties,
                               CoordinateSystemAxis axis0)
            throws FactoryException
    {
        try {
            return TimeCSFromGT.wrap(
                    impl.createTimeCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public LinearCS createLinearCS(Map<String, ?> properties,
                                   CoordinateSystemAxis axis0)
            throws FactoryException
    {
        try {
            return LinearCSFromGT.wrap(
                    impl.createLinearCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public UserDefinedCS createUserDefinedCS(Map<String, ?> properties,
                                             CoordinateSystemAxis axis0,
                                             CoordinateSystemAxis axis1)
            throws FactoryException
    {
        try {
            return UserDefinedCSFromGT.wrap(
                    impl.createUserDefinedCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0),
                            CoordinateSystemAxisToGT.wrap(axis1)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public UserDefinedCS createUserDefinedCS(Map<String, ?> properties,
                                             CoordinateSystemAxis axis0,
                                             CoordinateSystemAxis axis1,
                                             CoordinateSystemAxis axis2)
            throws FactoryException
    {
        try {
            return UserDefinedCSFromGT.wrap(
                    impl.createUserDefinedCS(properties,
                            CoordinateSystemAxisToGT.wrap(axis0),
                            CoordinateSystemAxisToGT.wrap(axis1),
                            CoordinateSystemAxisToGT.wrap(axis2)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }
}
