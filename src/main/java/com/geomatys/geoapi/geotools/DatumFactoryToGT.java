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
import org.geotools.api.referencing.datum.*;
import org.geotools.api.referencing.FactoryException;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class DatumFactoryToGT extends ObjectFactoryToGT implements DatumFactory {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.referencing.datum.DatumFactory impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private DatumFactoryToGT(final org.opengis.referencing.datum.DatumFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static DatumFactory wrap(final org.opengis.referencing.datum.DatumFactory impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof DatumFactory) {
            var c = (DatumFactory) impl;
            return c;
        }
        if (impl instanceof DatumFactoryFromGT) {
            var c = (DatumFactoryFromGT) impl;
            return c.impl;
        }
        return new DatumFactoryToGT(impl);
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
     */
    @Override
    final org.opengis.referencing.ObjectFactory implementation() {
        return impl;
    }

    @Override
    public PrimeMeridian createPrimeMeridian(Map<String, ?> properties, double longitude, Unit<Angle> unit) throws FactoryException {
        try {
            return PrimeMeridianToGT.wrap(impl.createPrimeMeridian(properties, longitude, unit));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public Ellipsoid createEllipsoid(Map<String, ?> properties, double semiMajorAxis, double semiMinorAxis, Unit<Length> unit) throws FactoryException {
        try {
            return EllipsoidToGT.wrap(impl.createEllipsoid(properties, semiMajorAxis, semiMinorAxis, unit));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public Ellipsoid createFlattenedSphere(Map<String, ?> properties, double semiMajorAxis, double inverseFlattening, Unit<Length> unit) throws FactoryException {
        try {
            return EllipsoidToGT.wrap(impl.createFlattenedSphere(properties, semiMajorAxis, inverseFlattening, unit));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public GeodeticDatum createGeodeticDatum(Map<String, ?> properties, Ellipsoid ellipsoid, PrimeMeridian pm) throws FactoryException {
        try {
            return GeodeticDatumToGT.wrap(
                    impl.createGeodeticDatum(properties,
                            EllipsoidFromGT.wrap(ellipsoid),
                            PrimeMeridianFromGT.wrap(pm)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public VerticalDatum createVerticalDatum(Map<String, ?> properties, VerticalDatumType type) throws FactoryException {
        try {
            return VerticalDatumToGT.wrap(impl.createVerticalDatum(properties,
                    WrapperFromGT.wrap(type, org.opengis.referencing.datum.VerticalDatumType::valueOf)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public TemporalDatum createTemporalDatum(Map<String, ?> properties, Date origin) throws FactoryException {
        try {
            return TemporalDatumToGT.wrap(impl.createTemporalDatum(properties, origin));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public EngineeringDatum createEngineeringDatum(Map<String, ?> properties) throws FactoryException {
        try {
            return EngineeringDatumToGT.wrap(impl.createEngineeringDatum(properties));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public ImageDatum createImageDatum(Map<String, ?> properties, PixelInCell pixelInCell) throws FactoryException {
        try {
            return ImageDatumToGT.wrap(impl.createImageDatum(properties,
                    WrapperFromGT.wrap(pixelInCell, org.opengis.referencing.datum.PixelInCell::valueOf)));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }
}
