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
import org.geotools.api.referencing.cs.*;
import org.geotools.api.referencing.FactoryException;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class CSFactoryToGT extends ObjectFactoryToGT implements CSFactory {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.referencing.cs.CSFactory impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private CSFactoryToGT(final org.opengis.referencing.cs.CSFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static CSFactory wrap(final org.opengis.referencing.cs.CSFactory impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof CSFactory) {
            var c = (CSFactory) impl;
            return c;
        }
        if (impl instanceof CSFactoryFromGT) {
            var c = (CSFactoryFromGT) impl;
            return c.impl;
        }
        return new CSFactoryToGT(impl);
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.opengis.referencing.ObjectFactory implementation() {
        return impl;
    }

    @Override
    public CoordinateSystemAxis createCoordinateSystemAxis(Map<String, ?> properties,
            String abbreviation, AxisDirection direction, Unit<?> unit) throws FactoryException
    {
        try {
            return CoordinateSystemAxisToGT.wrap(impl.createCoordinateSystemAxis(properties, abbreviation,
                    WrapperFromGT.wrap(direction, org.opengis.referencing.cs.AxisDirection::valueOf), unit));
        } catch (org.opengis.util.FactoryException e) {
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
            return CartesianCSToGT.wrap(
                    impl.createCartesianCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0),
                            CoordinateSystemAxisFromGT.wrap(axis1)));
        } catch (org.opengis.util.FactoryException e) {
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
            return CartesianCSToGT.wrap(
                    impl.createCartesianCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0),
                            CoordinateSystemAxisFromGT.wrap(axis1),
                            CoordinateSystemAxisFromGT.wrap(axis2)));
        } catch (org.opengis.util.FactoryException e) {
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
            return AffineCSToGT.wrap(
                    impl.createAffineCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0),
                            CoordinateSystemAxisFromGT.wrap(axis1)));
        } catch (org.opengis.util.FactoryException e) {
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
            return AffineCSToGT.wrap(
                    impl.createAffineCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0),
                            CoordinateSystemAxisFromGT.wrap(axis1),
                            CoordinateSystemAxisFromGT.wrap(axis2)));
        } catch (org.opengis.util.FactoryException e) {
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
            return PolarCSToGT.wrap(
                    impl.createPolarCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0),
                            CoordinateSystemAxisFromGT.wrap(axis1)));
        } catch (org.opengis.util.FactoryException e) {
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
            return CylindricalCSToGT.wrap(
                    impl.createCylindricalCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0),
                            CoordinateSystemAxisFromGT.wrap(axis1),
                            CoordinateSystemAxisFromGT.wrap(axis2)));
        } catch (org.opengis.util.FactoryException e) {
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
            return SphericalCSToGT.wrap(
                    impl.createSphericalCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0),
                            CoordinateSystemAxisFromGT.wrap(axis1),
                            CoordinateSystemAxisFromGT.wrap(axis2)));
        } catch (org.opengis.util.FactoryException e) {
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
            return EllipsoidalCSToGT.wrap(
                    impl.createEllipsoidalCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0),
                            CoordinateSystemAxisFromGT.wrap(axis1)));
        } catch (org.opengis.util.FactoryException e) {
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
            return EllipsoidalCSToGT.wrap(
                    impl.createEllipsoidalCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0),
                            CoordinateSystemAxisFromGT.wrap(axis1),
                            CoordinateSystemAxisFromGT.wrap(axis2)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public VerticalCS createVerticalCS(Map<String, ?> properties,
                                       CoordinateSystemAxis axis0)
            throws FactoryException
    {
        try {
            return VerticalCSToGT.wrap(
                    impl.createVerticalCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public TimeCS createTimeCS(Map<String, ?> properties,
                               CoordinateSystemAxis axis0)
            throws FactoryException
    {
        try {
            return TimeCSToGT.wrap(
                    impl.createTimeCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public LinearCS createLinearCS(Map<String, ?> properties,
                                   CoordinateSystemAxis axis0)
            throws FactoryException
    {
        try {
            return LinearCSToGT.wrap(
                    impl.createLinearCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0)));
        } catch (org.opengis.util.FactoryException e) {
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
            return UserDefinedCSToGT.wrap(
                    impl.createUserDefinedCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0),
                            CoordinateSystemAxisFromGT.wrap(axis1)));
        } catch (org.opengis.util.FactoryException e) {
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
            return UserDefinedCSToGT.wrap(
                    impl.createUserDefinedCS(properties,
                            CoordinateSystemAxisFromGT.wrap(axis0),
                            CoordinateSystemAxisFromGT.wrap(axis1),
                            CoordinateSystemAxisFromGT.wrap(axis2)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }
}
