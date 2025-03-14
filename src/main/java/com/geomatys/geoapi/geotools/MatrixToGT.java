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

import org.geotools.api.referencing.operation.Matrix;


/**
 * GeoTools wrapper for an implementation of the GeoAPI interface.
 *
 * @author Martin Desruisseaux (Geomatys)
 */
final class MatrixToGT extends WrapperToGT implements Matrix {
    /**
     * The GeoAPI implementation on which to delegate all methods.
     */
    final org.opengis.referencing.operation.Matrix impl;

    /**
     * Creates a new wrapper for the given GeoAPI implementation.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     */
    private MatrixToGT(final org.opengis.referencing.operation.Matrix impl) {
        this.impl = impl;
    }

    /**
     * Creates a new wrapper of the most appropriate type for the given instance.
     * If the given implementation is {@code null}, then this method returns {@code null}.
     *
     * @param impl the GeoAPI implementation on which to delegate all methods
     * @return wrapper for the given implementation
     */
    static Matrix wrap(final org.opengis.referencing.operation.Matrix impl) {
        switch (impl) {
            case null: return null;
            case Matrix c: return c;
            case MatrixFromGT c: return c.impl;
            default: return new MatrixToGT(impl);
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
    public int getNumRow() {
        return impl.getNumRow();
    }

    @Override
    public int getNumCol() {
        return impl.getNumCol();
    }

    @Override
    public double getElement(int row, int column) {
        return impl.getElement(row, column);
    }

    @Override
    public void setElement(int row, int column, double value) {
        impl.setElement(row, column, value);
    }

    @Override
    public boolean isIdentity() {
        return impl.isIdentity();
    }

    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public Matrix clone() {
        return wrap(impl.clone());
    }
}
