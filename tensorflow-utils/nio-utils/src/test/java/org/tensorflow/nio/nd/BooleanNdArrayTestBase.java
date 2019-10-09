/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */
package org.tensorflow.nio.nd;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.tensorflow.nio.nd.NdArrays.vector;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import org.junit.Test;

public abstract class BooleanNdArrayTestBase extends NdArrayTestBase<Boolean> {

    @Override
    protected abstract BooleanNdArray allocate(Shape shape);

    @Override
    protected Boolean valueOf(Long val) {
        return val > 0;
    }

    @Test
    public void iteratePrimitiveElements() {
        BooleanNdArray matrix3d = allocate(Shape.make(5, 4, 5));

        matrix3d.scalars().forEachIndexed((coords, scalar) -> {
            scalar.setBoolean(coords[2] > 0);
        });

        assertFalse(matrix3d.getBoolean(0, 0, 0));
        assertTrue(matrix3d.getBoolean(0, 0, 1));
        assertTrue(matrix3d.getBoolean(0, 0, 4));
        assertTrue(matrix3d.getBoolean(0, 1, 2));

        matrix3d.elements(1).forEach(vector -> {
            vector.set(vector(true, false, true, false, true));
        });

        assertTrue(matrix3d.getBoolean(0, 0, 0));
        assertFalse(matrix3d.getBoolean(0, 0, 1));
        assertTrue(matrix3d.getBoolean(0, 0, 4));
        assertTrue(matrix3d.getBoolean(0, 1, 2));
    }

    @Test
    public void writeAndReadWithPrimitiveArrays() {
        boolean[] values = new boolean[] { true, true, false, false, true, true, false, true, false, false, true, false, true, false, true, true };

        BooleanNdArray matrix = allocate(Shape.make(3, 4));
        matrix.write(values);
        assertTrue(matrix.getBoolean(0, 0));
        assertFalse(matrix.getBoolean(0, 3));
        assertTrue(matrix.getBoolean(1, 0));
        assertFalse(matrix.getBoolean(2, 3));

        matrix.write(values, 4);
        assertTrue(matrix.getBoolean(0, 0));
        assertTrue(matrix.getBoolean(0, 3));
        assertFalse(matrix.getBoolean(1, 0));
        assertTrue(matrix.getBoolean(2, 3));

        matrix.setBoolean(true, 1, 0);
        matrix.read(values, 2);
        assertTrue(values[2]);
        assertTrue(values[5]);

        matrix.read(values);
        assertTrue(values[0]);
        assertTrue(values[3]);

        try {
            matrix.write(new boolean[] { true, true, true, true });
            fail();
        } catch (BufferUnderflowException e) {
            // as expected
        }
        try {
            matrix.write(values, values.length);
            fail();
        } catch (BufferUnderflowException e) {
            // as expected
        }
        try {
            matrix.write(values, -1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // as expected
        }
        try {
            matrix.write(values, values.length + 1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // as expected
        }
        try {
            matrix.read(new boolean[4]);
            fail();
        } catch (BufferOverflowException e) {
            // as expected
        }
        try {
            matrix.read(values, values.length);
            fail();
        } catch (BufferOverflowException e) {
            // as expected
        }
        try {
            matrix.read(values, -1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // as expected
        }
        try {
            matrix.read(values, values.length + 1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // as expected
        }
    }
}
