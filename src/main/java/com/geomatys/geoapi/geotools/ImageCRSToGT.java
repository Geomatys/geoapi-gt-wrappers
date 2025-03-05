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

import org.geotools.api.referencing.crs.ImageCRS;
import org.geotools.api.referencing.cs.AffineCS;
import org.geotools.api.referencing.datum.ImageDatum;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class ImageCRSToGT extends CoordinateReferenceSystemToGT<org.opengis.referencing.crs.ImageCRS>
        implements ImageCRS
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    ImageCRSToGT(final org.opengis.referencing.crs.ImageCRS impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static ImageCRS wrap(final org.opengis.referencing.crs.ImageCRS impl) {
        switch (impl) {
            case null: return null;
            case ImageCRS c: return c;
            default: return new ImageCRSToGT(impl);
        }
    }

    @Override
    public AffineCS getCoordinateSystem() {
        return AffineCSToGT.wrap(impl.getCoordinateSystem());
    }

    @Override
    public ImageDatum getDatum() {
        return ImageDatumToGT.wrap(impl.getDatum());
    }
}
