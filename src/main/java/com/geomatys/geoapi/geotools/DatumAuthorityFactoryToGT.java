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

import org.geotools.api.referencing.datum.DatumAuthorityFactory;
import org.geotools.api.referencing.datum.Datum;
import org.geotools.api.referencing.datum.Ellipsoid;
import org.geotools.api.referencing.datum.EngineeringDatum;
import org.geotools.api.referencing.datum.GeodeticDatum;
import org.geotools.api.referencing.datum.ImageDatum;
import org.geotools.api.referencing.datum.PrimeMeridian;
import org.geotools.api.referencing.datum.TemporalDatum;
import org.geotools.api.referencing.datum.VerticalDatum;
import org.geotools.api.referencing.FactoryException;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class DatumAuthorityFactoryToGT extends AuthorityFactoryToGT implements DatumAuthorityFactory {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    private final org.opengis.referencing.datum.DatumAuthorityFactory impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private DatumAuthorityFactoryToGT(final org.opengis.referencing.datum.DatumAuthorityFactory impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static DatumAuthorityFactory wrap(final org.opengis.referencing.datum.DatumAuthorityFactory impl) {
        switch (impl) {
            case null: return null;
            case DatumAuthorityFactory c: return c;
            default: return new DatumAuthorityFactoryToGT(impl);
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
    public Datum createDatum(String code) throws FactoryException {
        try {
            return DatumToGT.wrap(impl.createDatum(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public Ellipsoid createEllipsoid(String code) throws FactoryException {
        try {
            return EllipsoidToGT.wrap(impl.createEllipsoid(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public PrimeMeridian createPrimeMeridian(String code) throws FactoryException {
        try {
            return PrimeMeridianToGT.wrap(impl.createPrimeMeridian(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public GeodeticDatum createGeodeticDatum(String code) throws FactoryException {
        try {
            return GeodeticDatumToGT.wrap(impl.createGeodeticDatum(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public VerticalDatum createVerticalDatum(String code) throws FactoryException {
        try {
            return VerticalDatumToGT.wrap(impl.createVerticalDatum(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public TemporalDatum createTemporalDatum(String code) throws FactoryException {
        try {
            return TemporalDatumToGT.wrap(impl.createTemporalDatum(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public EngineeringDatum createEngineeringDatum(String code) throws FactoryException {
        try {
            return EngineeringDatumToGT.wrap(impl.createEngineeringDatum(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }

    @Override
    public ImageDatum createImageDatum(String code) throws FactoryException {
        try {
            return ImageDatumToGT.wrap(impl.createImageDatum(code));
        } catch (org.opengis.util.FactoryException e) {
            throw wrap(e);
        }
    }
}
