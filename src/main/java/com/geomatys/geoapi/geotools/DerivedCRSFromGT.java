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

import org.opengis.referencing.crs.DerivedCRS;
import org.opengis.referencing.crs.EngineeringCRS;
import org.opengis.referencing.crs.GeodeticCRS;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.crs.VerticalCRS;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.cs.VerticalCS;
import org.opengis.referencing.datum.EngineeringDatum;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.datum.TemporalDatum;
import org.opengis.referencing.datum.VerticalDatum;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class DerivedCRSFromGT extends GeneralDerivedCRSFromGT<org.geotools.api.referencing.crs.DerivedCRS>
        implements DerivedCRS
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    DerivedCRSFromGT(final org.geotools.api.referencing.crs.DerivedCRS impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    @SuppressWarnings("unused")
    static DerivedCRS wrap(final org.geotools.api.referencing.crs.DerivedCRS impl) {
        switch (impl) {
            case null: return null;
            case DerivedCRS c: return c;
            case DerivedCRSToGT c: return c.impl;
            case org.geotools.api.referencing.crs.GeographicCRS  c: return new Geographic (impl);
            case org.geotools.api.referencing.crs.GeodeticCRS    c: return new Geodetic   (impl);
            case org.geotools.api.referencing.crs.VerticalCRS    c: return new Vertical   (impl);
            case org.geotools.api.referencing.crs.TemporalCRS    c: return new Temporal   (impl);
            case org.geotools.api.referencing.crs.EngineeringCRS c: return new Engineering(impl);
            default: return new DerivedCRSFromGT(impl);
        }
    }

    /**
     * The derived geodetic <abbr>CRS</abbr> case.
     */
    private static class Geodetic extends DerivedCRSFromGT implements GeodeticCRS {
        Geodetic(final org.geotools.api.referencing.crs.DerivedCRS impl) {
            super(impl);
        }

        @Override
        public GeodeticDatum getDatum() {
            return GeodeticDatumFromGT.wrap(((org.geotools.api.referencing.crs.GeographicCRS) impl).getDatum());
        }
    }

    /**
     * The derived geographic <abbr>CRS</abbr> case.
     */
    private static final class Geographic extends Geodetic implements GeographicCRS {
        Geographic(final org.geotools.api.referencing.crs.DerivedCRS impl) {
            super(impl);
        }

        @Override
        public EllipsoidalCS getCoordinateSystem() {
            return EllipsoidalCSFromGT.wrap(((org.geotools.api.referencing.crs.GeographicCRS) impl).getCoordinateSystem());
        }
    }

    /**
     * The derived vertical <abbr>CRS</abbr> case.
     */
    private static final class Vertical extends DerivedCRSFromGT implements VerticalCRS {
        Vertical(final org.geotools.api.referencing.crs.DerivedCRS impl) {
            super(impl);
        }

        @Override
        public VerticalCS getCoordinateSystem() {
            return VerticalCSFromGT.wrap(((org.geotools.api.referencing.crs.VerticalCRS) impl).getCoordinateSystem());
        }

        @Override
        public VerticalDatum getDatum() {
            return VerticalDatumFromGT.wrap(((org.geotools.api.referencing.crs.VerticalCRS) impl).getDatum());
        }
    }

    /**
     * The derived temporal <abbr>CRS</abbr> case.
     */
    private static final class Temporal extends DerivedCRSFromGT implements TemporalCRS {
        Temporal(final org.geotools.api.referencing.crs.DerivedCRS impl) {
            super(impl);
        }

        @Override
        public TimeCS getCoordinateSystem() {
            return TimeCSFromGT.wrap(((org.geotools.api.referencing.crs.TemporalCRS) impl).getCoordinateSystem());
        }

        @Override
        public TemporalDatum getDatum() {
            return TemporalDatumFromGT.wrap(((org.geotools.api.referencing.crs.TemporalCRS) impl).getDatum());
        }
    }

    /**
     * The derived engineering <abbr>CRS</abbr> case.
     */
    private static final class Engineering extends DerivedCRSFromGT implements EngineeringCRS {
        Engineering(final org.geotools.api.referencing.crs.DerivedCRS impl) {
            super(impl);
        }

        @Override
        public EngineeringDatum getDatum() {
            return EngineeringDatumFromGT.wrap(((org.geotools.api.referencing.crs.EngineeringCRS) impl).getDatum());
        }
    }
}
