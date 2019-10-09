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

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import org.junit.Test;

public abstract class LongNdArrayTestBase extends NdArrayTestBase<Long> {

    @Override
    protected abstract LongNdArray allocate(Shape shape);

    @Override
    protected Long valueOf(Long val) {
        return val;
    }

    @Test
    public void writeAndReadWithPrimitiveArrays() {
        long[] values = new long[] { 0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L };

        LongNdArray matrix = allocate(Shape.make(3, 4));
        matrix.write(values);
        assertEquals(0L, matrix.getLong(0, 0));
        assertEquals(3L, matrix.getLong(0, 3));
        assertEquals(4L, matrix.getLong(1, 0));
        assertEquals(11L, matrix.getLong(2, 3));

        matrix.write(values, 4);
        assertEquals(4L, matrix.getLong(0, 0));
        assertEquals(7L, matrix.getLong(0, 3));
        assertEquals(8L, matrix.getLong(1, 0));
        assertEquals(15L, matrix.getLong(2, 3));

        matrix.setLong(100L, 1, 0);
        matrix.read(values, 2);
        assertEquals(4L, values[2]);
        assertEquals(7L, values[5]);
        assertEquals(100L, values[6]);
        assertEquals(15L, values[13]);
        assertEquals(15L, values[15]);

        matrix.read(values);
        assertEquals(4L, values[0]);
        assertEquals(7L, values[3]);
        assertEquals(100L, values[4]);
        assertEquals(15L, values[11]);
        assertEquals(15L, values[13]);
        assertEquals(15L, values[15]);

        try {
            matrix.write(new long[] { 1, 2, 3, 4 });
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
            matrix.read(new long[4]);
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
