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

import org.opengis.util.*;
import org.opengis.util.Record;
import org.opengis.temporal.*;
import org.opengis.referencing.*;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.operation.*;
import org.opengis.parameter.*;
import org.opengis.metadata.citation.*;
import org.opengis.metadata.extent.*;
import org.opengis.metadata.quality.*;
import org.opengis.metadata.Identifier;
import org.opengis.geometry.DirectPosition;


/**
 * Views of GeoTools objects as GeoAPI objects.
 * All {@code geoapi(…)} methods take a GeoTools object in argument and returns an implementation
 * of the corresponding GeoAPI interface which delegates all operations to the given GeoTools object.
 * That {@code geoapi(…)} method is overloaded with all types supported by wrappers in this package.
 * In most cases, users can blindly invoke {@code Wrappers.geoapi(…)} and let the compiler determines
 * the most appropriate method.
 *
 * <h2>Dynamic determination of type</h2>
 * Some types are the parent of one or more specializations. For example, when the {@code geoapi(CoordinateSystem)}
 * method is invoked, the given object could actually be a {@code CartesianCS}, or a {@code SphericalCS}, <i>etc</i>.
 * All methods in this class check for such specializations in order to return the best match.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
public final class Wrappers {
    /**
     * Do not allow instantiation of this class.
     */
    private Wrappers() {
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code AbsoluteExternalPositionalAccuracy}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code AbsoluteExternalPositionalAccuracy}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code AbsoluteExternalPositionalAccuracy}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static AbsoluteExternalPositionalAccuracy geoapi(org.geotools.api.metadata.quality.AbsoluteExternalPositionalAccuracy geotools) {
        return AbsoluteExternalPositionalAccuracyFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Address}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Address}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Address}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Address geoapi(org.geotools.api.metadata.citation.Address geotools) {
        return AddressFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code AffineCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code AffineCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code AffineCS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static AffineCS geoapi(org.geotools.api.referencing.cs.AffineCS geotools) {
        return AffineCSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CRSAuthorityFactory}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CRSAuthorityFactory}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CRSAuthorityFactory}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CRSAuthorityFactory geoapi(org.geotools.api.referencing.crs.CRSAuthorityFactory geotools) {
        return CRSAuthorityFactoryFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CSAuthorityFactory}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CSAuthorityFactory}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CSAuthorityFactory}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CSAuthorityFactory geoapi(org.geotools.api.referencing.cs.CSAuthorityFactory geotools) {
        return CSAuthorityFactoryFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CartesianCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CartesianCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CartesianCS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CartesianCS geoapi(org.geotools.api.referencing.cs.CartesianCS geotools) {
        return CartesianCSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Citation}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Citation}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Citation}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Citation geoapi(org.geotools.api.metadata.citation.Citation geotools) {
        return CitationFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CompoundCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CompoundCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CompoundCRS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CompoundCRS geoapi(org.geotools.api.referencing.crs.CompoundCRS geotools) {
        return CompoundCRSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ConcatenatedOperation}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ConcatenatedOperation}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code ConcatenatedOperation}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static ConcatenatedOperation geoapi(org.geotools.api.referencing.operation.ConcatenatedOperation geotools) {
        return ConcatenatedOperationFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ConformanceResult}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ConformanceResult}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code ConformanceResult}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static ConformanceResult geoapi(org.geotools.api.metadata.quality.ConformanceResult geotools) {
        return ConformanceResultFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Contact}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Contact}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Contact}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Contact geoapi(org.geotools.api.metadata.citation.Contact geotools) {
        return ContactFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Conversion}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Conversion}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Conversion}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Conversion geoapi(org.geotools.api.referencing.operation.Conversion geotools) {
        return ConversionFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CoordinateOperation}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CoordinateOperation}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CoordinateOperation}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CoordinateOperation geoapi(org.geotools.api.referencing.operation.CoordinateOperation geotools) {
        return CoordinateOperationFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CoordinateOperationAuthorityFactory}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CoordinateOperationAuthorityFactory}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CoordinateOperationAuthorityFactory}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CoordinateOperationAuthorityFactory geoapi(org.geotools.api.referencing.operation.CoordinateOperationAuthorityFactory geotools) {
        return CoordinateOperationAuthorityFactoryFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CoordinateReferenceSystem}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CoordinateReferenceSystem}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CoordinateReferenceSystem}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CoordinateReferenceSystem geoapi(org.geotools.api.referencing.crs.CoordinateReferenceSystem geotools) {
        return CoordinateReferenceSystemFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CoordinateSystem}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CoordinateSystem}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CoordinateSystem}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CoordinateSystem geoapi(org.geotools.api.referencing.cs.CoordinateSystem geotools) {
        return CoordinateSystemFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CoordinateSystemAxis}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CoordinateSystemAxis}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CoordinateSystemAxis}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CoordinateSystemAxis geoapi(org.geotools.api.referencing.cs.CoordinateSystemAxis geotools) {
        return CoordinateSystemAxisFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CylindricalCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CylindricalCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CylindricalCS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CylindricalCS geoapi(org.geotools.api.referencing.cs.CylindricalCS geotools) {
        return CylindricalCSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Datum}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Datum}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Datum}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Datum geoapi(org.geotools.api.referencing.datum.Datum geotools) {
        return DatumFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code DatumAuthorityFactory}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code DatumAuthorityFactory}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code DatumAuthorityFactory}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static DatumAuthorityFactory geoapi(org.geotools.api.referencing.datum.DatumAuthorityFactory geotools) {
        return DatumAuthorityFactoryFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code DerivedCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code DerivedCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code DerivedCRS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static DerivedCRS geoapi(org.geotools.api.referencing.crs.DerivedCRS geotools) {
        return DerivedCRSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code DirectPosition}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code DirectPosition}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code DirectPosition}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static DirectPosition geoapi(org.geotools.api.geometry.Position geotools) {
        return DirectPositionFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Element}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Element}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Element}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Element geoapi(org.geotools.api.metadata.quality.Element geotools) {
        return QualityElementFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Ellipsoid}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Ellipsoid}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Ellipsoid}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Ellipsoid geoapi(org.geotools.api.referencing.datum.Ellipsoid geotools) {
        return EllipsoidFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code EllipsoidalCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code EllipsoidalCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code EllipsoidalCS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static EllipsoidalCS geoapi(org.geotools.api.referencing.cs.EllipsoidalCS geotools) {
        return EllipsoidalCSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code EngineeringCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code EngineeringCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code EngineeringCRS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static EngineeringCRS geoapi(org.geotools.api.referencing.crs.EngineeringCRS geotools) {
        return EngineeringCRSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code EngineeringDatum}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code EngineeringDatum}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code EngineeringDatum}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static EngineeringDatum geoapi(org.geotools.api.referencing.datum.EngineeringDatum geotools) {
        return EngineeringDatumFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Extent}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Extent}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Extent}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Extent geoapi(org.geotools.api.metadata.extent.Extent geotools) {
        return ExtentFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeneralDerivedCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeneralDerivedCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code GeneralDerivedCRS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static GeneralDerivedCRS geoapi(org.geotools.api.referencing.crs.GeneralDerivedCRS geotools) {
        return GeneralDerivedCRSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeneralParameterDescriptor}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeneralParameterDescriptor}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code GeneralParameterDescriptor}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static GeneralParameterDescriptor geoapi(org.geotools.api.parameter.GeneralParameterDescriptor geotools) {
        return GeneralParameterDescriptorFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeneralParameterValue}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeneralParameterValue}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code GeneralParameterValue}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static GeneralParameterValue geoapi(org.geotools.api.parameter.GeneralParameterValue geotools) {
        return GeneralParameterValueFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GenericName}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GenericName}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code GenericName}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static GenericName geoapi(org.geotools.api.util.GenericName geotools) {
        return GenericNameFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeocentricCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeocentricCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code GeocentricCRS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static GeocentricCRS geoapi(org.geotools.api.referencing.crs.GeocentricCRS geotools) {
        return GeocentricCRSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeodeticCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeodeticCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code GeodeticCRS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static GeodeticCRS geoapi(org.geotools.api.referencing.crs.GeodeticCRS geotools) {
        return GeodeticCRSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeodeticDatum}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeodeticDatum}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code GeodeticDatum}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static GeodeticDatum geoapi(org.geotools.api.referencing.datum.GeodeticDatum geotools) {
        return GeodeticDatumFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeographicBoundingBox}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeographicBoundingBox}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code GeographicBoundingBox}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static GeographicBoundingBox geoapi(org.geotools.api.metadata.extent.GeographicBoundingBox geotools) {
        return GeographicBoundingBoxFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeographicCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeographicCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code GeographicCRS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static GeographicCRS geoapi(org.geotools.api.referencing.crs.GeographicCRS geotools) {
        return GeographicCRSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeographicExtent}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeographicExtent}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code GeographicExtent}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static GeographicExtent geoapi(org.geotools.api.metadata.extent.GeographicExtent geotools) {
        return GeographicExtentFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code IdentifiedObject}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code IdentifiedObject}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code IdentifiedObject}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static IdentifiedObject geoapi(org.geotools.api.referencing.IdentifiedObject geotools) {
        return IdentifiedObjectFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Identifier}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Identifier}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Identifier}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Identifier geoapi(org.geotools.api.metadata.Identifier geotools) {
        return IdentifierFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ImageCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ImageCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code ImageCRS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static ImageCRS geoapi(org.geotools.api.referencing.crs.ImageCRS geotools) {
        return ImageCRSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ImageDatum}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ImageDatum}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code ImageDatum}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static ImageDatum geoapi(org.geotools.api.referencing.datum.ImageDatum geotools) {
        return ImageDatumFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code InternationalString}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code InternationalString}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code InternationalString}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static InternationalString geoapi(org.geotools.api.util.InternationalString geotools) {
        return InternationalStringFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code LinearCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code LinearCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code LinearCS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static LinearCS geoapi(org.geotools.api.referencing.cs.LinearCS geotools) {
        return LinearCSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code LocalName}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code LocalName}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code LocalName}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static LocalName geoapi(org.geotools.api.util.LocalName geotools) {
        return LocalNameFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code MathTransform}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code MathTransform}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code MathTransform}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static MathTransform geoapi(org.geotools.api.referencing.operation.MathTransform geotools) {
        return MathTransformFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code MathTransform1D}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code MathTransform1D}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code MathTransform1D}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static MathTransform1D geoapi(org.geotools.api.referencing.operation.MathTransform1D geotools) {
        return MathTransform1DFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code MathTransform2D}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code MathTransform2D}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code MathTransform2D}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static MathTransform2D geoapi(org.geotools.api.referencing.operation.MathTransform2D geotools) {
        return MathTransform2DFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Matrix}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Matrix}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Matrix}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Matrix geoapi(org.geotools.api.referencing.operation.Matrix geotools) {
        return MatrixFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code MemberName}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code MemberName}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code MemberName}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static MemberName geoapi(org.geotools.api.util.MemberName geotools) {
        return MemberNameFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code NameSpace}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code NameSpace}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code NameSpace}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static NameSpace geoapi(org.geotools.api.util.NameSpace geotools) {
        return NameSpaceFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code OnlineResource}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code OnlineResource}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code OnlineResource}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static OnlineResource geoapi(org.geotools.api.metadata.citation.OnLineResource geotools) {
        return OnlineResourceFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code OperationMethod}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code OperationMethod}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code OperationMethod}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static OperationMethod geoapi(org.geotools.api.referencing.operation.OperationMethod geotools) {
        return OperationMethodFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code PeriodDuration}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code PeriodDuration}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code PeriodDuration}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static PeriodDuration geoapi(org.geotools.api.temporal.PeriodDuration geotools) {
        return PeriodDurationFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code PolarCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code PolarCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code PolarCS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static PolarCS geoapi(org.geotools.api.referencing.cs.PolarCS geotools) {
        return PolarCSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code PositionalAccuracy}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code PositionalAccuracy}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code PositionalAccuracy}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static PositionalAccuracy geoapi(org.geotools.api.metadata.quality.PositionalAccuracy geotools) {
        return PositionalAccuracyFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code PrimeMeridian}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code PrimeMeridian}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code PrimeMeridian}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static PrimeMeridian geoapi(org.geotools.api.referencing.datum.PrimeMeridian geotools) {
        return PrimeMeridianFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ProjectedCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ProjectedCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code ProjectedCRS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static ProjectedCRS geoapi(org.geotools.api.referencing.crs.ProjectedCRS geotools) {
        return ProjectedCRSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Projection}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Projection}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Projection}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Projection geoapi(org.geotools.api.referencing.operation.Projection geotools) {
        return ProjectionFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code QuantitativeResult}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code QuantitativeResult}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code QuantitativeResult}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static QuantitativeResult geoapi(org.geotools.api.metadata.quality.QuantitativeResult geotools) {
        return QuantitativeResultFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Record}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Record}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Record}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Record geoapi(org.geotools.api.util.Record geotools) {
        return RecordFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code RecordSchema}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code RecordSchema}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code RecordSchema}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static RecordSchema geoapi(org.geotools.api.util.RecordSchema geotools) {
        return RecordSchemaFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code RecordType}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code RecordType}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code RecordType}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static RecordType geoapi(org.geotools.api.util.RecordType geotools) {
        return RecordTypeFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ReferenceIdentifier}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ReferenceIdentifier}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code ReferenceIdentifier}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static ReferenceIdentifier geoapi(org.geotools.api.referencing.ReferenceIdentifier geotools) {
        return ReferenceIdentifierFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ReferenceSystem}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ReferenceSystem}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code ReferenceSystem}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static ReferenceSystem geoapi(org.geotools.api.referencing.ReferenceSystem geotools) {
        return ReferenceSystemFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ResponsibleParty}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ResponsibleParty}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code ResponsibleParty}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static ResponsibleParty geoapi(org.geotools.api.metadata.citation.ResponsibleParty geotools) {
        return ResponsiblePartyFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Result}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Result}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Result}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Result geoapi(org.geotools.api.metadata.quality.Result geotools) {
        return QualityResultFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ScopedName}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ScopedName}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code ScopedName}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static ScopedName geoapi(org.geotools.api.util.ScopedName geotools) {
        return ScopedNameFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code SingleCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code SingleCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code SingleCRS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static SingleCRS geoapi(org.geotools.api.referencing.crs.SingleCRS geotools) {
        return SingleCRSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code SingleOperation}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code SingleOperation}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object implements a more specific GeoTools interface, behaves as if {@code impl}
     *       was cast to the most specific supported type before to invoke {@code wrap(…)}.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code SingleOperation}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static SingleOperation geoapi(org.geotools.api.referencing.operation.SingleOperation geotools) {
        return SingleOperationFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code SphericalCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code SphericalCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code SphericalCS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static SphericalCS geoapi(org.geotools.api.referencing.cs.SphericalCS geotools) {
        return SphericalCSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Telephone}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Telephone}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Telephone}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Telephone geoapi(org.geotools.api.metadata.citation.Telephone geotools) {
        return TelephoneFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code TemporalCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code TemporalCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code TemporalCRS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static TemporalCRS geoapi(org.geotools.api.referencing.crs.TemporalCRS geotools) {
        return TemporalCRSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code TemporalDatum}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code TemporalDatum}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code TemporalDatum}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static TemporalDatum geoapi(org.geotools.api.referencing.datum.TemporalDatum geotools) {
        return TemporalDatumFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code TemporalExtent}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code TemporalExtent}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code TemporalExtent}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static TemporalExtent geoapi(org.geotools.api.metadata.extent.TemporalExtent geotools) {
        return TemporalExtentFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code TemporalPrimitive}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code TemporalPrimitive}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code TemporalPrimitive}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static TemporalPrimitive geoapi(org.geotools.api.temporal.TemporalPrimitive geotools) {
        return TemporalPrimitiveFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code TimeCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code TimeCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code TimeCS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static TimeCS geoapi(org.geotools.api.referencing.cs.TimeCS geotools) {
        return TimeCSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Transformation}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Transformation}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code Transformation}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static Transformation geoapi(org.geotools.api.referencing.operation.Transformation geotools) {
        return TransformationFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code TypeName}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code TypeName}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code TypeName}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static TypeName geoapi(org.geotools.api.util.TypeName geotools) {     // Need to return the wrapper class.
        return TypeNameFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code UserDefinedCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code UserDefinedCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code UserDefinedCS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static UserDefinedCS geoapi(org.geotools.api.referencing.cs.UserDefinedCS geotools) {
        return UserDefinedCSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code VerticalCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code VerticalCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code VerticalCRS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static VerticalCRS geoapi(org.geotools.api.referencing.crs.VerticalCRS geotools) {
        return VerticalCRSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code VerticalCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code VerticalCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code VerticalCS}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static VerticalCS geoapi(org.geotools.api.referencing.cs.VerticalCS geotools) {
        return VerticalCSFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code VerticalDatum}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code VerticalDatum}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code VerticalDatum}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static VerticalDatum geoapi(org.geotools.api.referencing.datum.VerticalDatum geotools) {
        return VerticalDatumFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code VerticalExtent}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code VerticalExtent}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code VerticalExtent}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static VerticalExtent geoapi(org.geotools.api.metadata.extent.VerticalExtent geotools) {
        return VerticalExtentFromGT.wrap(geotools);
    }
}
