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

import org.geotools.api.referencing.crs.DerivedCRS;
import org.geotools.api.referencing.crs.EngineeringCRS;
import org.geotools.api.referencing.crs.GeodeticCRS;
import org.geotools.api.referencing.crs.GeographicCRS;
import org.geotools.api.referencing.crs.TemporalCRS;
import org.geotools.api.referencing.crs.VerticalCRS;
import org.geotools.api.referencing.cs.EllipsoidalCS;
import org.geotools.api.referencing.cs.TimeCS;
import org.geotools.api.referencing.cs.VerticalCS;
import org.geotools.api.referencing.datum.EngineeringDatum;
import org.geotools.api.referencing.datum.GeodeticDatum;
import org.geotools.api.referencing.datum.TemporalDatum;
import org.geotools.api.referencing.datum.VerticalDatum;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class DerivedCRSToGT extends GeneralDerivedCRSToGT<org.opengis.referencing.crs.DerivedCRS>
        implements DerivedCRS
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    DerivedCRSToGT(final org.opengis.referencing.crs.DerivedCRS impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    @SuppressWarnings("unused")
    static DerivedCRS wrap(final org.opengis.referencing.crs.DerivedCRS impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof DerivedCRS) {
            var c = (DerivedCRS) impl;
            return c;
        }
        if (impl instanceof DerivedCRSFromGT) {
            var c = (DerivedCRSFromGT) impl;
            return c.impl;
        }
        if (impl instanceof org.opengis.referencing.crs.GeographicCRS) {
            var c = (org.opengis.referencing.crs.GeographicCRS) impl;
            return new Geographic (impl);
        }
        if (impl instanceof org.opengis.referencing.crs.GeodeticCRS) {
            var c = (org.opengis.referencing.crs.GeodeticCRS) impl;
            return new Geodetic   (impl);
        }
        if (impl instanceof org.opengis.referencing.crs.VerticalCRS) {
            var c = (org.opengis.referencing.crs.VerticalCRS) impl;
            return new Vertical   (impl);
        }
        if (impl instanceof org.opengis.referencing.crs.TemporalCRS) {
            var c = (org.opengis.referencing.crs.TemporalCRS) impl;
            return new Temporal   (impl);
        }
        if (impl instanceof org.opengis.referencing.crs.EngineeringCRS) {
            var c = (org.opengis.referencing.crs.EngineeringCRS) impl;
            return new Engineering(impl);
        }
        return new DerivedCRSToGT(impl);
    }

    /**
     * The derived geodetic <abbr>CRS</abbr> case.
     */
    private static class Geodetic extends DerivedCRSToGT implements GeodeticCRS {
        Geodetic(final org.opengis.referencing.crs.DerivedCRS impl) {
            super(impl);
        }

        @Override
        public GeodeticDatum getDatum() {
            return GeodeticDatumToGT.wrap(((org.opengis.referencing.crs.GeographicCRS) impl).getDatum());
        }
    }

    /**
     * The derived geographic <abbr>CRS</abbr> case.
     */
    private static final class Geographic extends Geodetic implements GeographicCRS {
        Geographic(final org.opengis.referencing.crs.DerivedCRS impl) {
            super(impl);
        }

        @Override
        public EllipsoidalCS getCoordinateSystem() {
            return EllipsoidalCSToGT.wrap(((org.opengis.referencing.crs.GeographicCRS) impl).getCoordinateSystem());
        }
    }

    /**
     * The derived vertical <abbr>CRS</abbr> case.
     */
    private static final class Vertical extends DerivedCRSToGT implements VerticalCRS {
        Vertical(final org.opengis.referencing.crs.DerivedCRS impl) {
            super(impl);
        }

        @Override
        public VerticalCS getCoordinateSystem() {
            return VerticalCSToGT.wrap(((org.opengis.referencing.crs.VerticalCRS) impl).getCoordinateSystem());
        }

        @Override
        public VerticalDatum getDatum() {
            return VerticalDatumToGT.wrap(((org.opengis.referencing.crs.VerticalCRS) impl).getDatum());
        }
    }

    /**
     * The derived temporal <abbr>CRS</abbr> case.
     */
    private static final class Temporal extends DerivedCRSToGT implements TemporalCRS {
        Temporal(final org.opengis.referencing.crs.DerivedCRS impl) {
            super(impl);
        }

        @Override
        public TimeCS getCoordinateSystem() {
            return TimeCSToGT.wrap(((org.opengis.referencing.crs.TemporalCRS) impl).getCoordinateSystem());
        }

        @Override
        public TemporalDatum getDatum() {
            return TemporalDatumToGT.wrap(((org.opengis.referencing.crs.TemporalCRS) impl).getDatum());
        }
    }

    /**
     * The derived engineering <abbr>CRS</abbr> case.
     */
    private static final class Engineering extends DerivedCRSToGT implements EngineeringCRS {
        Engineering(final org.opengis.referencing.crs.DerivedCRS impl) {
            super(impl);
        }

        @Override
        public EngineeringDatum getDatum() {
            return EngineeringDatumToGT.wrap(((org.opengis.referencing.crs.EngineeringCRS) impl).getDatum());
        }
    }
}
