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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.tensorflow.nio.nd.NdArrays.vector;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import org.junit.Test;

public abstract class IntNdArrayTestBase extends NdArrayTestBase<Integer> {

    @Override
    protected abstract IntNdArray allocate(Shape shape);

    @Override
    protected Integer valueOf(Long val) {
        return val.intValue();
    }

    @Test
    public void iteratePrimitiveElements() {
        IntNdArray matrix3d = allocate(Shape.make(5, 4, 5));

        matrix3d.scalars().forEachIndexed((coords, scalar) -> {
            scalar.setInt((int)coords[2]);
        });

        assertEquals(0, matrix3d.getInt(0, 0, 0));
        assertEquals(1, matrix3d.getInt(0, 0, 1));
        assertEquals(4, matrix3d.getInt(0, 0, 4));
        assertEquals(2, matrix3d.getInt(0, 1, 2));

        matrix3d.elements(1).forEach(vector -> {
            vector.set(vector(5, 6, 7, 8, 9));
        });

        assertEquals(5, matrix3d.getInt(0, 0, 0));
        assertEquals(6, matrix3d.getInt(0, 0, 1));
        assertEquals(9, matrix3d.getInt(0, 0, 4));
        assertEquals(7, matrix3d.getInt(0, 1, 2));
    }

    @Test
    public void writeAndReadWithPrimitiveArrays() {
        int[] values = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };

        IntNdArray matrix = allocate(Shape.make(3, 4));
        matrix.write(values);
        assertEquals(0, matrix.getInt(0, 0));
        assertEquals(3, matrix.getInt(0, 3));
        assertEquals(4, matrix.getInt(1, 0));
        assertEquals(11, matrix.getInt(2, 3));

        matrix.write(values, 4);
        assertEquals(4, matrix.getInt(0, 0));
        assertEquals(7, matrix.getInt(0, 3));
        assertEquals(8, matrix.getInt(1, 0));
        assertEquals(15, matrix.getInt(2, 3));

        matrix.setInt(100, 1, 0);
        matrix.read(values, 2);
        assertEquals(4, values[2]);
        assertEquals(7, values[5]);
        assertEquals(100, values[6]);
        assertEquals(15, values[13]);
        assertEquals(15, values[15]);

        matrix.read(values);
        assertEquals(4, values[0]);
        assertEquals(7, values[3]);
        assertEquals(100, values[4]);
        assertEquals(15, values[11]);
        assertEquals(15, values[13]);
        assertEquals(15, values[15]);

        try {
            matrix.write(new int[] { 1, 2, 3, 4 });
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
            matrix.read(new int[4]);
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
