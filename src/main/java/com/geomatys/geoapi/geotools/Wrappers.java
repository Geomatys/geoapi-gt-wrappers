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
 * Views of GeoTools objects as GeoAPI objects, and conversely.
 * All {@code geoapi(…)} methods take a GeoTools object in argument and returns an implementation
 * of the corresponding GeoAPI interface which delegates all operations to the given GeoTools object.
 * That {@code geoapi(…)} method is overloaded with all types supported by wrappers in this package.
 * In most cases, users can blindly invoke {@code Wrappers.geoapi(…)} and let the compiler determines
 * the most appropriate method.
 *
 * <p>All {@code geoapi(…)} methods perform the same work as {@code geoapi(…)} methods,
 * but in the reverse direction: they view GeoAPI objects as GeoTools objects.</p>
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
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code AbsoluteExternalPositionalAccuracy}.
     * This method performs the same choice as {@code geoapi(AbsoluteExternalPositionalAccuracy)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.quality.AbsoluteExternalPositionalAccuracy geotools(AbsoluteExternalPositionalAccuracy geoapi) {
        return AbsoluteExternalPositionalAccuracyToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Address}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Address}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Address}.
     * This method performs the same choice as {@code geoapi(Address)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.citation.Address geotools(Address geoapi) {
        return AddressToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code AffineCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code AffineCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code AffineCS}.
     * This method performs the same choice as {@code geoapi(AffineCS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.AffineCS geotools(AffineCS geoapi) {
        return AffineCSToGT.wrap(geoapi);
    }

    /**
     * Returns the given GeoTools code list value as a GeoAPI {@code AxisDirection}.
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public AxisDirection geoapi(org.geotools.api.referencing.cs.AxisDirection geotools) {
        return WrapperFromGT.wrap(geotools, AxisDirection::valueOf);
    }

    /**
     * Returns the given GeoAPI code list value as a GeoTools {@code AxisDirection}.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public org.geotools.api.referencing.cs.AxisDirection geotools(AxisDirection geoapi) {
        return WrapperToGT.wrap(geoapi, org.geotools.api.referencing.cs.AxisDirection::valueOf);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CRSAuthorityFactory}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CRSAuthorityFactory}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code CRSAuthorityFactory}.
     * This method performs the same choice as {@code geoapi(CRSAuthorityFactory)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.CRSAuthorityFactory geotools(CRSAuthorityFactory geoapi) {
        return CRSAuthorityFactoryToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CRSFactory}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CRSFactory}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CRSFactory}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CRSFactory geoapi(org.geotools.api.referencing.crs.CRSFactory geotools) {
        return CRSFactoryFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoAPI object as a GeoTools {@code CRSFactory}.
     * This method performs the same choice as {@code geoapi(CRSFactory)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.CRSFactory geotools(CRSFactory geoapi) {
        return CRSFactoryToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CSAuthorityFactory}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CSAuthorityFactory}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code CSAuthorityFactory}.
     * This method performs the same choice as {@code geoapi(CSAuthorityFactory)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.CSAuthorityFactory geotools(CSAuthorityFactory geoapi) {
        return CSAuthorityFactoryToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CSFactory}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CSFactory}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CSFactory}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CSFactory geoapi(org.geotools.api.referencing.cs.CSFactory geotools) {
        return CSFactoryFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoAPI object as a GeoTools {@code CSFactory}.
     * This method performs the same choice as {@code geoapi(CSFactory)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.CSFactory geotools(CSFactory geoapi) {
        return CSFactoryToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CartesianCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CartesianCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code CartesianCS}.
     * This method performs the same choice as {@code geoapi(CartesianCS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.CartesianCS geotools(CartesianCS geoapi) {
        return CartesianCSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Citation}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Citation}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Citation}.
     * This method performs the same choice as {@code geoapi(Citation)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.citation.Citation geotools(Citation geoapi) {
        return CitationToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CompoundCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CompoundCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code CompoundCRS}.
     * This method performs the same choice as {@code geoapi(CompoundCRS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.CompoundCRS geotools(CompoundCRS geoapi) {
        return CompoundCRSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ConcatenatedOperation}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ConcatenatedOperation}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code ConcatenatedOperation}.
     * This method performs the same choice as {@code geoapi(ConcatenatedOperation)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.ConcatenatedOperation geotools(ConcatenatedOperation geoapi) {
        return ConcatenatedOperationToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ConformanceResult}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ConformanceResult}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code ConformanceResult}.
     * This method performs the same choice as {@code geoapi(ConformanceResult)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.quality.ConformanceResult geotools(ConformanceResult geoapi) {
        return ConformanceResultToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Contact}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Contact}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Contact}.
     * This method performs the same choice as {@code geoapi(Contact)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.citation.Contact geotools(Contact geoapi) {
        return ContactToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Conversion}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Conversion}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Conversion}.
     * This method performs the same choice as {@code geoapi(Conversion)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.Conversion geotools(Conversion geoapi) {
        return ConversionToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CoordinateOperation}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CoordinateOperation}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code CoordinateOperation}.
     * This method performs the same choice as {@code geoapi(CoordinateOperation)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.CoordinateOperation geotools(CoordinateOperation geoapi) {
        return CoordinateOperationToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CoordinateOperationAuthorityFactory}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CoordinateOperationAuthorityFactory}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code CoordinateOperationAuthorityFactory}.
     * This method performs the same choice as {@code geoapi(CoordinateOperationAuthorityFactory)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.CoordinateOperationAuthorityFactory geotools(CoordinateOperationAuthorityFactory geoapi) {
        return CoordinateOperationAuthorityFactoryToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CoordinateOperationFactory}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CoordinateOperationFactory}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code CoordinateOperationFactory}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static CoordinateOperationFactory geoapi(org.geotools.api.referencing.operation.CoordinateOperationFactory geotools) {
        return CoordinateOperationFactoryFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoAPI object as a GeoTools {@code CoordinateOperationFactory}.
     * This method performs the same choice as {@code geoapi(CoordinateOperationFactory)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.CoordinateOperationFactory geotools(CoordinateOperationFactory geoapi) {
        return CoordinateOperationFactoryToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CoordinateReferenceSystem}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CoordinateReferenceSystem}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code CoordinateReferenceSystem}.
     * This method performs the same choice as {@code geoapi(CoordinateReferenceSystem)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.CoordinateReferenceSystem geotools(CoordinateReferenceSystem geoapi) {
        return CoordinateReferenceSystemToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CoordinateSystem}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CoordinateSystem}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code CoordinateSystem}.
     * This method performs the same choice as {@code geoapi(CoordinateSystem)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.CoordinateSystem geotools(CoordinateSystem geoapi) {
        return CoordinateSystemToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CoordinateSystemAxis}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CoordinateSystemAxis}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code CoordinateSystemAxis}.
     * This method performs the same choice as {@code geoapi(CoordinateSystemAxis)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.CoordinateSystemAxis geotools(CoordinateSystemAxis geoapi) {
        return CoordinateSystemAxisToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code CylindricalCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code CylindricalCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code CylindricalCS}.
     * This method performs the same choice as {@code geoapi(CylindricalCS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.CylindricalCS geotools(CylindricalCS geoapi) {
        return CylindricalCSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Datum}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Datum}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Datum}.
     * This method performs the same choice as {@code geoapi(Datum)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.datum.Datum geotools(Datum geoapi) {
        return DatumToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code DatumAuthorityFactory}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code DatumAuthorityFactory}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code DatumAuthorityFactory}.
     * This method performs the same choice as {@code geoapi(DatumAuthorityFactory)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.datum.DatumAuthorityFactory geotools(DatumAuthorityFactory geoapi) {
        return DatumAuthorityFactoryToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code DatumFactory}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code DatumFactory}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
     *   <li>Otherwise, wraps {@code impl} in a {@code DatumFactory}
     *       which will forward all methods to {@code impl}.</li>
     * </ol>
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public static DatumFactory geoapi(org.geotools.api.referencing.datum.DatumFactory geotools) {
        return DatumFactoryFromGT.wrap(geotools);
    }

    /**
     * Views the given GeoAPI object as a GeoTools {@code DatumFactory}.
     * This method performs the same choice as {@code geoapi(DatumFactory)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.datum.DatumFactory geotools(DatumFactory geoapi) {
        return DatumFactoryToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code DerivedCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code DerivedCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code DerivedCRS}.
     * This method performs the same choice as {@code geoapi(DerivedCRS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.DerivedCRS geotools(DerivedCRS geoapi) {
        return DerivedCRSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code DirectPosition}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code DirectPosition}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code DirectPosition}.
     * This method performs the same choice as {@code geoapi(DirectPosition)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.geometry.Position geotools(DirectPosition geoapi) {
        return DirectPositionToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Element}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Element}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Element}.
     * This method performs the same choice as {@code geoapi(Element)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.quality.Element geotools(Element geoapi) {
        return QualityElementToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Ellipsoid}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Ellipsoid}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Ellipsoid}.
     * This method performs the same choice as {@code geoapi(Ellipsoid)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.datum.Ellipsoid geotools(Ellipsoid geoapi) {
        return EllipsoidToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code EllipsoidalCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code EllipsoidalCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code EllipsoidalCS}.
     * This method performs the same choice as {@code geoapi(EllipsoidalCS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.EllipsoidalCS geotools(EllipsoidalCS geoapi) {
        return EllipsoidalCSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code EngineeringCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code EngineeringCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code EngineeringCRS}.
     * This method performs the same choice as {@code geoapi(EngineeringCRS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.EngineeringCRS geotools(EngineeringCRS geoapi) {
        return EngineeringCRSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code EngineeringDatum}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code EngineeringDatum}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code EngineeringDatum}.
     * This method performs the same choice as {@code geoapi(EngineeringDatum)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.datum.EngineeringDatum geotools(EngineeringDatum geoapi) {
        return EngineeringDatumToGT.wrap(geoapi);
    }

    /**
     * Returns the given GeoTools code list value as a GeoAPI {@code EvaluationMethodType}.
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public EvaluationMethodType geoapi(org.geotools.api.metadata.quality.EvaluationMethodType geotools) {
        return WrapperFromGT.wrap(geotools, EvaluationMethodType::valueOf);
    }

    /**
     * Returns the given GeoAPI code list value as a GeoTools {@code EvaluationMethodType}.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public org.geotools.api.metadata.quality.EvaluationMethodType geotools(EvaluationMethodType geoapi) {
        return WrapperToGT.wrap(geoapi, org.geotools.api.metadata.quality.EvaluationMethodType::valueOf);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Extent}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Extent}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Extent}.
     * This method performs the same choice as {@code geoapi(Extent)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.extent.Extent geotools(Extent geoapi) {
        return ExtentToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeneralDerivedCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeneralDerivedCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code GeneralDerivedCRS}.
     * This method performs the same choice as {@code geoapi(GeneralDerivedCRS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.GeneralDerivedCRS geotools(GeneralDerivedCRS geoapi) {
        return GeneralDerivedCRSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeneralParameterDescriptor}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeneralParameterDescriptor}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code GeneralParameterDescriptor}.
     * This method performs the same choice as {@code geoapi(GeneralParameterDescriptor)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.parameter.GeneralParameterDescriptor geotools(GeneralParameterDescriptor geoapi) {
        return GeneralParameterDescriptorToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeneralParameterValue}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeneralParameterValue}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code GeneralParameterValue}.
     * This method performs the same choice as {@code geoapi(GeneralParameterValue)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.parameter.GeneralParameterValue geotools(GeneralParameterValue geoapi) {
        return GeneralParameterValueToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GenericName}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GenericName}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code GenericName}.
     * This method performs the same choice as {@code geoapi(GenericName)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.util.GenericName geotools(GenericName geoapi) {
        return GenericNameToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeocentricCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeocentricCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code GeocentricCRS}.
     * This method performs the same choice as {@code geoapi(GeocentricCRS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.GeocentricCRS geotools(GeocentricCRS geoapi) {
        return GeocentricCRSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeodeticCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeodeticCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code GeodeticCRS}.
     * This method performs the same choice as {@code geoapi(GeodeticCRS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.GeodeticCRS geotools(GeodeticCRS geoapi) {
        return GeodeticCRSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeodeticDatum}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeodeticDatum}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code GeodeticDatum}.
     * This method performs the same choice as {@code geoapi(GeodeticDatum)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.datum.GeodeticDatum geotools(GeodeticDatum geoapi) {
        return GeodeticDatumToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeographicBoundingBox}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeographicBoundingBox}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code GeographicBoundingBox}.
     * This method performs the same choice as {@code geoapi(GeographicBoundingBox)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.extent.GeographicBoundingBox geotools(GeographicBoundingBox geoapi) {
        return GeographicBoundingBoxToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeographicCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeographicCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code GeographicCRS}.
     * This method performs the same choice as {@code geoapi(GeographicCRS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.GeographicCRS geotools(GeographicCRS geoapi) {
        return GeographicCRSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code GeographicExtent}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code GeographicExtent}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code GeographicExtent}.
     * This method performs the same choice as {@code geoapi(GeographicExtent)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.extent.GeographicExtent geotools(GeographicExtent geoapi) {
        return GeographicExtentToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code IdentifiedObject}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code IdentifiedObject}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code IdentifiedObject}.
     * This method performs the same choice as {@code geoapi(IdentifiedObject)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.IdentifiedObject geotools(IdentifiedObject geoapi) {
        return IdentifiedObjectToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Identifier}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Identifier}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Identifier}.
     * This method performs the same choice as {@code geoapi(Identifier)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.Identifier geotools(Identifier geoapi) {
        return IdentifierToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ImageCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ImageCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code ImageCRS}.
     * This method performs the same choice as {@code geoapi(ImageCRS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.ImageCRS geotools(ImageCRS geoapi) {
        return ImageCRSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ImageDatum}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ImageDatum}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code ImageDatum}.
     * This method performs the same choice as {@code geoapi(ImageDatum)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.datum.ImageDatum geotools(ImageDatum geoapi) {
        return ImageDatumToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code InternationalString}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code InternationalString}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code InternationalString}.
     * This method performs the same choice as {@code geoapi(InternationalString)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.util.InternationalString geotools(InternationalString geoapi) {
        return InternationalStringToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code LinearCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code LinearCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code LinearCS}.
     * This method performs the same choice as {@code geoapi(LinearCS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.LinearCS geotools(LinearCS geoapi) {
        return LinearCSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code LocalName}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code LocalName}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code LocalName}.
     * This method performs the same choice as {@code geoapi(LocalName)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.util.LocalName geotools(LocalName geoapi) {
        return LocalNameToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code MathTransform}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code MathTransform}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code MathTransform}.
     * This method performs the same choice as {@code geoapi(MathTransform)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.MathTransform geotools(MathTransform geoapi) {
        return MathTransformToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code MathTransform1D}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code MathTransform1D}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code MathTransform1D}.
     * This method performs the same choice as {@code geoapi(MathTransform1D)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.MathTransform1D geotools(MathTransform1D geoapi) {
        return MathTransform1DToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code MathTransform2D}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code MathTransform2D}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code MathTransform2D}.
     * This method performs the same choice as {@code geoapi(MathTransform2D)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.MathTransform2D geotools(MathTransform2D geoapi) {
        return MathTransform2DToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Matrix}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Matrix}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Matrix}.
     * This method performs the same choice as {@code geoapi(Matrix)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.Matrix geotools(Matrix geoapi) {
        return MatrixToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code MemberName}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code MemberName}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code MemberName}.
     * This method performs the same choice as {@code geoapi(MemberName)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.util.MemberName geotools(MemberName geoapi) {
        return MemberNameToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code NameSpace}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code NameSpace}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code NameSpace}.
     * This method performs the same choice as {@code geoapi(NameSpace)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.util.NameSpace geotools(NameSpace geoapi) {
        return NameSpaceToGT.wrap(geoapi);
    }

    /**
     * Returns the given GeoTools code list value as a GeoAPI {@code OnLineFunction}.
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public OnLineFunction geoapi(org.geotools.api.metadata.citation.OnLineFunction geotools) {
        return WrapperFromGT.wrap(geotools, OnLineFunction::valueOf);
    }

    /**
     * Returns the given GeoAPI code list value as a GeoTools {@code OnLineFunction}.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public org.geotools.api.metadata.citation.OnLineFunction geotools(OnLineFunction geoapi) {
        return WrapperToGT.wrap(geoapi, org.geotools.api.metadata.citation.OnLineFunction::valueOf);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code OnlineResource}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code OnlineResource}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code OnlineResource}.
     * This method performs the same choice as {@code geoapi(OnlineResource)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.citation.OnLineResource geotools(OnlineResource geoapi) {
        return OnlineResourceToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code OperationMethod}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code OperationMethod}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code OperationMethod}.
     * This method performs the same choice as {@code geoapi(OperationMethod)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.OperationMethod geotools(OperationMethod geoapi) {
        return OperationMethodToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code PeriodDuration}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code PeriodDuration}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code PeriodDuration}.
     * This method performs the same choice as {@code geoapi(PeriodDuration)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.temporal.PeriodDuration geotools(PeriodDuration geoapi) {
        return PeriodDurationToGT.wrap(geoapi);
    }

    /**
     * Returns the given GeoTools code list value as a GeoAPI {@code PixelInCell}.
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public PixelInCell geoapi(org.geotools.api.referencing.datum.PixelInCell geotools) {
        return WrapperFromGT.wrap(geotools, PixelInCell::valueOf);
    }

    /**
     * Returns the given GeoAPI code list value as a GeoTools {@code PixelInCell}.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public org.geotools.api.referencing.datum.PixelInCell geotools(PixelInCell geoapi) {
        return WrapperToGT.wrap(geoapi, org.geotools.api.referencing.datum.PixelInCell::valueOf);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code PolarCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code PolarCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code PolarCS}.
     * This method performs the same choice as {@code geoapi(PolarCS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.PolarCS geotools(PolarCS geoapi) {
        return PolarCSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code PositionalAccuracy}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code PositionalAccuracy}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code PositionalAccuracy}.
     * This method performs the same choice as {@code geoapi(PositionalAccuracy)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.quality.PositionalAccuracy geotools(PositionalAccuracy geoapi) {
        return PositionalAccuracyToGT.wrap(geoapi);
    }

    /**
     * Returns the given GeoTools code list value as a GeoAPI {@code PresentationForm}.
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public PresentationForm geoapi(org.geotools.api.metadata.citation.PresentationForm geotools) {
        return WrapperFromGT.wrap(geotools, PresentationForm::valueOf);
    }

    /**
     * Returns the given GeoAPI code list value as a GeoTools {@code PresentationForm}.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public org.geotools.api.metadata.citation.PresentationForm geotools(PresentationForm geoapi) {
        return WrapperToGT.wrap(geoapi, org.geotools.api.metadata.citation.PresentationForm::valueOf);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code PrimeMeridian}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code PrimeMeridian}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code PrimeMeridian}.
     * This method performs the same choice as {@code geoapi(PrimeMeridian)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.datum.PrimeMeridian geotools(PrimeMeridian geoapi) {
        return PrimeMeridianToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ProjectedCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ProjectedCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code ProjectedCRS}.
     * This method performs the same choice as {@code geoapi(ProjectedCRS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.ProjectedCRS geotools(ProjectedCRS geoapi) {
        return ProjectedCRSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Projection}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Projection}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Projection}.
     * This method performs the same choice as {@code geoapi(Projection)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.Projection geotools(Projection geoapi) {
        return ProjectionToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code QuantitativeResult}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code QuantitativeResult}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code QuantitativeResult}.
     * This method performs the same choice as {@code geoapi(QuantitativeResult)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.quality.QuantitativeResult geotools(QuantitativeResult geoapi) {
        return QuantitativeResultToGT.wrap(geoapi);
    }

    /**
     * Returns the given GeoTools code list value as a GeoAPI {@code RangeMeaning}.
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public RangeMeaning geoapi(org.geotools.api.referencing.cs.RangeMeaning geotools) {
        return WrapperFromGT.wrap(geotools, RangeMeaning::valueOf);
    }

    /**
     * Returns the given GeoAPI code list value as a GeoTools {@code RangeMeaning}.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public org.geotools.api.referencing.cs.RangeMeaning geotools(RangeMeaning geoapi) {
        return WrapperToGT.wrap(geoapi, org.geotools.api.referencing.cs.RangeMeaning::valueOf);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Record}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Record}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Record}.
     * This method performs the same choice as {@code geoapi(Record)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.util.Record geotools(Record geoapi) {
        return RecordToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code RecordSchema}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code RecordSchema}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code RecordSchema}.
     * This method performs the same choice as {@code geoapi(RecordSchema)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.util.RecordSchema geotools(RecordSchema geoapi) {
        return RecordSchemaToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code RecordType}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code RecordType}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code RecordType}.
     * This method performs the same choice as {@code geoapi(RecordType)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.util.RecordType geotools(RecordType geoapi) {
        return RecordTypeToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ReferenceIdentifier}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ReferenceIdentifier}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code ReferenceIdentifier}.
     * This method performs the same choice as {@code geoapi(ReferenceIdentifier)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.ReferenceIdentifier geotools(ReferenceIdentifier geoapi) {
        return ReferenceIdentifierToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ReferenceSystem}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ReferenceSystem}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code ReferenceSystem}.
     * This method performs the same choice as {@code geoapi(ReferenceSystem)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.ReferenceSystem geotools(ReferenceSystem geoapi) {
        return ReferenceSystemToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ResponsibleParty}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ResponsibleParty}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code ResponsibleParty}.
     * This method performs the same choice as {@code geoapi(ResponsibleParty)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.citation.ResponsibleParty geotools(ResponsibleParty geoapi) {
        return ResponsiblePartyToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Result}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Result}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Result}.
     * This method performs the same choice as {@code geoapi(Result)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.quality.Result geotools(Result geoapi) {
        return QualityResultToGT.wrap(geoapi);
    }

    /**
     * Returns the given GeoTools code list value as a GeoAPI {@code Role}.
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public Role geoapi(org.geotools.api.metadata.citation.Role geotools) {
        return WrapperFromGT.wrap(geotools, Role::valueOf);
    }

    /**
     * Returns the given GeoAPI code list value as a GeoTools {@code Role}.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public org.geotools.api.metadata.citation.Role geotools(Role geoapi) {
        return WrapperToGT.wrap(geoapi, org.geotools.api.metadata.citation.Role::valueOf);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code ScopedName}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code ScopedName}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code ScopedName}.
     * This method performs the same choice as {@code geoapi(ScopedName)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.util.ScopedName geotools(ScopedName geoapi) {
        return ScopedNameToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code SingleCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code SingleCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code SingleCRS}.
     * This method performs the same choice as {@code geoapi(SingleCRS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.SingleCRS geotools(SingleCRS geoapi) {
        return SingleCRSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code SingleOperation}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code SingleOperation}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code SingleOperation}.
     * This method performs the same choice as {@code geoapi(SingleOperation)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.SingleOperation geotools(SingleOperation geoapi) {
        return SingleOperationToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code SphericalCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code SphericalCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code SphericalCS}.
     * This method performs the same choice as {@code geoapi(SphericalCS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.SphericalCS geotools(SphericalCS geoapi) {
        return SphericalCSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Telephone}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Telephone}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Telephone}.
     * This method performs the same choice as {@code geoapi(Telephone)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.citation.Telephone geotools(Telephone geoapi) {
        return TelephoneToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code TemporalCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code TemporalCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code TemporalCRS}.
     * This method performs the same choice as {@code geoapi(TemporalCRS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.TemporalCRS geotools(TemporalCRS geoapi) {
        return TemporalCRSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code TemporalDatum}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code TemporalDatum}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code TemporalDatum}.
     * This method performs the same choice as {@code geoapi(TemporalDatum)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.datum.TemporalDatum geotools(TemporalDatum geoapi) {
        return TemporalDatumToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code TemporalExtent}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code TemporalExtent}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code TemporalExtent}.
     * This method performs the same choice as {@code geoapi(TemporalExtent)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.extent.TemporalExtent geotools(TemporalExtent geoapi) {
        return TemporalExtentToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code TemporalPrimitive}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code TemporalPrimitive}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code TemporalPrimitive}.
     * This method performs the same choice as {@code geoapi(TemporalPrimitive)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.temporal.TemporalPrimitive geotools(TemporalPrimitive geoapi) {
        return TemporalPrimitiveToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code TimeCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code TimeCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code TimeCS}.
     * This method performs the same choice as {@code geoapi(TimeCS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.TimeCS geotools(TimeCS geoapi) {
        return TimeCSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code Transformation}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code Transformation}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code Transformation}.
     * This method performs the same choice as {@code geoapi(Transformation)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.operation.Transformation geotools(Transformation geoapi) {
        return TransformationToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code TypeName}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code TypeName}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code TypeName}.
     * This method performs the same choice as {@code geoapi(TypeName)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.util.TypeName geotools(TypeName geoapi) {     // Need to return the wrapper class.
        return TypeNameToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code UserDefinedCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code UserDefinedCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code UserDefinedCS}.
     * This method performs the same choice as {@code geoapi(UserDefinedCS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.UserDefinedCS geotools(UserDefinedCS geoapi) {
        return UserDefinedCSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code VerticalCRS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code VerticalCRS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code VerticalCRS}.
     * This method performs the same choice as {@code geoapi(VerticalCRS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.crs.VerticalCRS geotools(VerticalCRS geoapi) {
        return VerticalCRSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code VerticalCS}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code VerticalCS}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code VerticalCS}.
     * This method performs the same choice as {@code geoapi(VerticalCS)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.cs.VerticalCS geotools(VerticalCS geoapi) {
        return VerticalCSToGT.wrap(geoapi);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code VerticalDatum}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code VerticalDatum}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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
     * Views the given GeoAPI object as a GeoTools {@code VerticalDatum}.
     * This method performs the same choice as {@code geoapi(VerticalDatum)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.referencing.datum.VerticalDatum geotools(VerticalDatum geoapi) {
        return VerticalDatumToGT.wrap(geoapi);
    }

    /**
     * Returns the given GeoTools code list value as a GeoAPI {@code VerticalDatumType}.
     *
     * @param geotools the GeoTools object to view as a GeoAPI object, or {@code null}.
     * @return the given implementation viewed as a GeoAPI object, or {@code null} if the given object was null.
     */
    public VerticalDatumType geoapi(org.geotools.api.referencing.datum.VerticalDatumType geotools) {
        return WrapperFromGT.wrap(geotools, VerticalDatumType::valueOf);
    }

    /**
     * Returns the given GeoAPI code list value as a GeoTools {@code VerticalDatumType}.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public org.geotools.api.referencing.datum.VerticalDatumType geotools(VerticalDatumType geoapi) {
        return WrapperToGT.wrap(geoapi, org.geotools.api.referencing.datum.VerticalDatumType::valueOf);
    }

    /**
     * Views the given GeoTools object as a GeoAPI {@code VerticalExtent}.
     * This method returns the first of the following choices which is applicable:
     * <ol>
     *   <li>If the given object is null, returns {@code null}.</li>
     *   <li>If the given object already implements the GeoAPI {@code VerticalExtent}
     *       interface, returns that {@code impl} instance directly.</li>
     *   <li>If the given object was created by a {@code geotools(…)} method,
     *       unwraps and returns the backing implementation.</li>
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

    /**
     * Views the given GeoAPI object as a GeoTools {@code VerticalExtent}.
     * This method performs the same choice as {@code geoapi(VerticalExtent)},
     * but in the reverse direction.
     *
     * @param geoapi the GeoAPI object to view as a GeoTools object, or {@code null}.
     * @return the given implementation viewed as a GeoTools object, or {@code null} if the given object was null.
     */
    public static org.geotools.api.metadata.extent.VerticalExtent geotools(VerticalExtent geoapi) {
        return VerticalExtentToGT.wrap(geoapi);
    }
}
