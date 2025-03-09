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

import java.util.Date;
import java.util.Map;
import javax.measure.Unit;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import org.opengis.referencing.datum.*;
import org.opengis.util.FactoryException;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class DatumFactoryFromGT extends ObjectFactoryFromGT implements DatumFactory {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.referencing.datum.DatumFactory impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private DatumFactoryFromGT(final org.geotools.api.referencing.datum.DatumFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static DatumFactory wrap(final org.geotools.api.referencing.datum.DatumFactory impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof DatumFactory) {
            var c = (DatumFactory) impl;
            return c;
        }
        if (impl instanceof DatumFactoryToGT) {
            var c = (DatumFactoryToGT) impl;
            return c.impl;
        }
        return new DatumFactoryFromGT(impl);
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.geotools.api.referencing.ObjectFactory implementation() {
        return impl;
    }

    @Override
    public PrimeMeridian createPrimeMeridian(Map<String, ?> properties, double longitude, Unit<Angle> unit) throws FactoryException {
        try {
            return PrimeMeridianFromGT.wrap(impl.createPrimeMeridian(properties, longitude, unit));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public Ellipsoid createEllipsoid(Map<String, ?> properties, double semiMajorAxis, double semiMinorAxis, Unit<Length> unit) throws FactoryException {
        try {
            return EllipsoidFromGT.wrap(impl.createEllipsoid(properties, semiMajorAxis, semiMinorAxis, unit));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public Ellipsoid createFlattenedSphere(Map<String, ?> properties, double semiMajorAxis, double inverseFlattening, Unit<Length> unit) throws FactoryException {
        try {
            return EllipsoidFromGT.wrap(impl.createFlattenedSphere(properties, semiMajorAxis, inverseFlattening, unit));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public GeodeticDatum createGeodeticDatum(Map<String, ?> properties, Ellipsoid ellipsoid, PrimeMeridian pm) throws FactoryException {
        try {
            return GeodeticDatumFromGT.wrap(
                    impl.createGeodeticDatum(properties,
                            EllipsoidToGT.wrap(ellipsoid),
                            PrimeMeridianToGT.wrap(pm)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public VerticalDatum createVerticalDatum(Map<String, ?> properties, VerticalDatumType type) throws FactoryException {
        try {
            return VerticalDatumFromGT.wrap(impl.createVerticalDatum(properties,
                    WrapperToGT.wrap(type, org.geotools.api.referencing.datum.VerticalDatumType::valueOf)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public TemporalDatum createTemporalDatum(Map<String, ?> properties, Date origin) throws FactoryException {
        try {
            return TemporalDatumFromGT.wrap(impl.createTemporalDatum(properties, origin));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public EngineeringDatum createEngineeringDatum(Map<String, ?> properties) throws FactoryException {
        try {
            return EngineeringDatumFromGT.wrap(impl.createEngineeringDatum(properties));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public ImageDatum createImageDatum(Map<String, ?> properties, PixelInCell pixelInCell) throws FactoryException {
        try {
            return ImageDatumFromGT.wrap(impl.createImageDatum(properties,
                    WrapperToGT.wrap(pixelInCell, org.geotools.api.referencing.datum.PixelInCell::valueOf)));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }
}
