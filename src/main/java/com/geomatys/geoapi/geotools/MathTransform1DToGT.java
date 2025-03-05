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

import org.geotools.api.referencing.operation.MathTransform1D;
import org.geotools.api.referencing.operation.NoninvertibleTransformException;
import org.geotools.api.referencing.operation.TransformException;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class MathTransform1DToGT extends MathTransformToGT<org.opengis.referencing.operation.MathTransform1D>
        implements MathTransform1D
{
    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    MathTransform1DToGT(final org.opengis.referencing.operation.MathTransform1D impl) {
        super(impl);
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static MathTransform1D wrap(final org.opengis.referencing.operation.MathTransform1D impl) {
        switch (impl) {
            case null: return null;
            case MathTransform1D c: return c;
            case MathTransform1DFromGT c: return c.impl;
            default: return new MathTransform1DToGT(impl);
        }
    }

    @Override
    public double transform(double value) throws TransformException {
        try {
            return impl.transform(value);
        } catch (org.opengis.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public double derivative(double value) throws TransformException {
        try {
            return impl.derivative(value);
        } catch (org.opengis.referencing.operation.TransformException e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    @Override
    public MathTransform1D inverse() throws NoninvertibleTransformException {
        try {
            return wrap(impl.inverse());
        } catch (org.opengis.referencing.operation.NoninvertibleTransformException e) {
            throw new NoninvertibleTransformException(e.getMessage(), e);
        }
    }
}
