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

import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.NoninvertibleTransformException;
import org.opengis.referencing.operation.TransformException;


/**
 * GeoAPI wrapper for an object from the GeoTools API.
 *
 * @param <S> the interface from the GeoTools API of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class MathTransformFromGT<S extends org.geotools.api.referencing.operation.MathTransform>
        extends WrapperFromGT implements MathTransform
{
    /**
     * The GeoTools implementation on which to delegate all methods.
     */
    final S impl;

    /**
     * Creates a new wrapper for the given GeoTools implementation.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     */
    MathTransformFromGT(final S impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoTools implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static MathTransform wrap(final org.geotools.api.referencing.operation.MathTransform impl) {
        if (impl == null) {
            return null;
        }
        if (impl instanceof MathTransform) {
            var c = (MathTransform) impl;
            return c;
        }
        if (impl instanceof MathTransformToGT<?>) {
            var c = (MathTransformToGT<?>) impl;
            return c.impl;
        }
        if (impl instanceof org.geotools.api.referencing.operation.MathTransform1D) {
            var c = (org.geotools.api.referencing.operation.MathTransform1D) impl;
            return new MathTransform1DFromGT(c);
        }
        if (impl instanceof org.geotools.api.referencing.operation.MathTransform2D) {
            var c = (org.geotools.api.referencing.operation.MathTransform2D) impl;
            return new MathTransform2DFromGT(c);
        }
        return new MathTransformFromGT<>(impl);
    }

    /**
     * {@return the GeoTools implementation on which this wrapper delegates all operations}.
     */
    @Override
    final Object implementation() {
        return impl;
    }

    @Override
    public int getSourceDimensions() {
        return impl.getSourceDimensions();
    }

    @Override
    public int getTargetDimensions() {
        return impl.getTargetDimensions();
    }

    @Override
    public DirectPosition transform(final DirectPosition ptSrc, final DirectPosition ptDst)
            throws MismatchedDimensionException, TransformException
    {
        try {
            return DirectPositionFromGT.wrap(impl.transform(DirectPositionToGT.wrap(ptSrc), DirectPositionToGT.wrap(ptDst)));
        } catch (org.geotools.api.geometry.MismatchedDimensionException e) {
            throw new MismatchedDimensionException(e.getMessage(), e);
        } catch (org.geotools.api.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
        try {
            impl.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        } catch (org.geotools.api.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) throws TransformException {
        try {
            impl.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        } catch (org.geotools.api.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public void transform(float[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
        try {
            impl.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        } catch (org.geotools.api.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public void transform(double[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) throws TransformException {
        try {
            impl.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        } catch (org.geotools.api.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public Matrix derivative(final DirectPosition point) throws MismatchedDimensionException, TransformException {
        try {
            return MatrixFromGT.wrap(impl.derivative(DirectPositionToGT.wrap(point)));
        } catch (org.geotools.api.geometry.MismatchedDimensionException e) {
            throw new MismatchedDimensionException(e.getMessage(), e);
        } catch (org.geotools.api.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public MathTransform inverse() throws NoninvertibleTransformException {
        try {
            return wrap(impl.inverse());
        } catch (org.geotools.api.referencing.operation.NoninvertibleTransformException e) {
            throw new NoninvertibleTransformException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isIdentity() {
        return impl.isIdentity();
    }

    @Override
    public String toWKT() throws UnsupportedOperationException {
        return impl.toWKT();
    }
}
