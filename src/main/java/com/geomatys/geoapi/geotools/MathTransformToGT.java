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

import org.geotools.api.geometry.Position;
import org.geotools.api.geometry.MismatchedDimensionException;
import org.geotools.api.referencing.operation.MathTransform;
import org.geotools.api.referencing.operation.Matrix;
import org.geotools.api.referencing.operation.NoninvertibleTransformException;
import org.geotools.api.referencing.operation.TransformException;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @param <S> the interface from the GeoAPI of the wrapped implementation.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
class MathTransformToGT<S extends org.opengis.referencing.operation.MathTransform>
        extends WrapperToGT implements MathTransform
{
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final S impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    MathTransformToGT(final S impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static MathTransform wrap(final org.opengis.referencing.operation.MathTransform impl) {
        switch (impl) {
            case null: return null;
            case MathTransform c: return c;
            case MathTransformFromGT<?> c: return c.impl;
            case org.opengis.referencing.operation.MathTransform1D c: return new MathTransform1DToGT(c);
            case org.opengis.referencing.operation.MathTransform2D c: return new MathTransform2DToGT(c);
            default: return new MathTransformToGT<>(impl);
        }
    }

    /**
     * {@return the GeoAPI implementation on which this wrapper delegates all operations}.
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
    public Position transform(final Position ptSrc, final Position ptDst)
            throws MismatchedDimensionException, TransformException
    {
        try {
            return DirectPositionToGT.wrap(impl.transform(DirectPositionFromGT.wrap(ptSrc), DirectPositionFromGT.wrap(ptDst)));
        } catch (org.opengis.geometry.MismatchedDimensionException e) {
            throw new MismatchedDimensionException(e.getMessage(), e);
        } catch (org.opengis.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public void transform(double[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
        try {
            impl.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        } catch (org.opengis.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public void transform(float[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) throws TransformException {
        try {
            impl.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        } catch (org.opengis.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public void transform(float[] srcPts, int srcOff, double[] dstPts, int dstOff, int numPts) throws TransformException {
        try {
            impl.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        } catch (org.opengis.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public void transform(double[] srcPts, int srcOff, float[] dstPts, int dstOff, int numPts) throws TransformException {
        try {
            impl.transform(srcPts, srcOff, dstPts, dstOff, numPts);
        } catch (org.opengis.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public Matrix derivative(final Position point) throws MismatchedDimensionException, TransformException {
        try {
            return MatrixToGT.wrap(impl.derivative(DirectPositionFromGT.wrap(point)));
        } catch (org.opengis.geometry.MismatchedDimensionException e) {
            throw new MismatchedDimensionException(e.getMessage(), e);
        } catch (org.opengis.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public MathTransform inverse() throws NoninvertibleTransformException {
        try {
            return wrap(impl.inverse());
        } catch (org.opengis.referencing.operation.NoninvertibleTransformException e) {
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
