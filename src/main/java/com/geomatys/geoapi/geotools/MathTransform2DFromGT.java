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

import java.awt.Shape;
import java.awt.geom.Point2D;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.NoninvertibleTransformException;
import org.opengis.referencing.operation.TransformException;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class MathTransform2DFromGT extends MathTransformFromGT<org.geotools.api.referencing.operation.MathTransform2D>
        implements MathTransform2D
{
    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    MathTransform2DFromGT(final org.geotools.api.referencing.operation.MathTransform2D impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static MathTransform2D wrap(final org.geotools.api.referencing.operation.MathTransform2D impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof MathTransform2D) {
            var c = (MathTransform2D) impl;
            return c;
        }
        if (impl instanceof MathTransform2DToGT) {
            var c = (MathTransform2DToGT) impl;
            return c.impl;
        }
        return new MathTransform2DFromGT(impl);
    }

    @Override
    public Point2D transform(Point2D ptSrc, Point2D ptDst) throws TransformException {
        try {
            return impl.transform(ptSrc, ptDst);
        } catch (org.geotools.api.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public Shape createTransformedShape(Shape shape) throws TransformException {
        try {
            return impl.createTransformedShape(shape);
        } catch (org.geotools.api.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public Matrix derivative(Point2D point) throws TransformException {
        try {
            return MatrixFromGT.wrap(impl.derivative(point));
        } catch (org.geotools.api.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public MathTransform2D inverse() throws NoninvertibleTransformException {
        try {
            return wrap(impl.inverse());
        } catch (org.geotools.api.referencing.operation.NoninvertibleTransformException e) {
            throw new NoninvertibleTransformException(e.getMessage(), e);
        }
    }
}
