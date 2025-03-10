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

import org.opengis.referencing.datum.DatumAuthorityFactory;
import org.opengis.referencing.datum.Datum;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.referencing.datum.EngineeringDatum;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.ImageDatum;
import org.opengis.referencing.datum.PrimeMeridian;
import org.opengis.referencing.datum.TemporalDatum;
import org.opengis.referencing.datum.VerticalDatum;
import org.opengis.util.FactoryException;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class DatumAuthorityFactoryFromGT extends AuthorityFactoryFromGT implements DatumAuthorityFactory {
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final org.geotools.api.referencing.datum.DatumAuthorityFactory impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    private DatumAuthorityFactoryFromGT(final org.geotools.api.referencing.datum.DatumAuthorityFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static DatumAuthorityFactory wrap(final org.geotools.api.referencing.datum.DatumAuthorityFactory impl) {
        switch (impl) {
            case null: return null;
            case DatumAuthorityFactory c: return c;
            case DatumAuthorityFactoryToGT c: return c.impl;
            default: return new DatumAuthorityFactoryFromGT(impl);
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
    public Datum createDatum(String code) throws FactoryException {
        try {
            return DatumFromGT.wrap(impl.createDatum(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public Ellipsoid createEllipsoid(String code) throws FactoryException {
        try {
            return EllipsoidFromGT.wrap(impl.createEllipsoid(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public PrimeMeridian createPrimeMeridian(String code) throws FactoryException {
        try {
            return PrimeMeridianFromGT.wrap(impl.createPrimeMeridian(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public GeodeticDatum createGeodeticDatum(String code) throws FactoryException {
        try {
            return GeodeticDatumFromGT.wrap(impl.createGeodeticDatum(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public VerticalDatum createVerticalDatum(String code) throws FactoryException {
        try {
            return VerticalDatumFromGT.wrap(impl.createVerticalDatum(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public TemporalDatum createTemporalDatum(String code) throws FactoryException {
        try {
            return TemporalDatumFromGT.wrap(impl.createTemporalDatum(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public EngineeringDatum createEngineeringDatum(String code) throws FactoryException {
        try {
            return EngineeringDatumFromGT.wrap(impl.createEngineeringDatum(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public ImageDatum createImageDatum(String code) throws FactoryException {
        try {
            return ImageDatumFromGT.wrap(impl.createImageDatum(code));
        } catch (org.geotools.api.referencing.FactoryException e) {
            throw wrap(e);
        }
    }
}
