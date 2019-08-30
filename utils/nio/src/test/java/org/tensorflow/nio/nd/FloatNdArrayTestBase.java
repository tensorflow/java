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

public abstract class FloatNdArrayTestBase extends NdArrayTestBase<Float> {

    @Override
    protected abstract FloatNdArray allocate(Shape shape);

    @Override
    protected Float valueOf(Long val) {
        return val.floatValue();
    }

    @Test
    public void writeAndReadWithPrimitiveArrays() {
        float[] values = new float[] { 0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f, 1.1f, 1.2f, 1.3f, 1.4f, 1.5f };

        FloatNdArray matrix = allocate(Shape.create(3, 4));
        matrix.write(values);
        assertEquals(Float.valueOf(0.0f), matrix.get(0, 0));
        assertEquals(Float.valueOf(0.3f), matrix.get(0, 3));
        assertEquals(Float.valueOf(0.4f), matrix.get(1, 0));
        assertEquals(Float.valueOf(1.1f), matrix.get(2, 3));

        matrix.write(values, 4);
        assertEquals(Float.valueOf(0.4f), matrix.get(0, 0));
        assertEquals(Float.valueOf(0.7f), matrix.get(0, 3));
        assertEquals(Float.valueOf(0.8f), matrix.get(1, 0));
        assertEquals(Float.valueOf(1.5f), matrix.get(2, 3));

        matrix.set(100.5f, 1, 0);
        matrix.read(values, 2);
        assertEquals(0.4f, values[2], 0);
        assertEquals(0.7f, values[5], 0);
        assertEquals(100.5f, values[6], 0);
        assertEquals(1.5f, values[13], 0);
        assertEquals(1.5f, values[15], 0);

        matrix.read(values);
        assertEquals(0.4f, values[0], 0);
        assertEquals(0.7f, values[3], 0);
        assertEquals(100.5f, values[4], 0);
        assertEquals(1.5f, values[11], 0);
        assertEquals(1.5f, values[13], 0);
        assertEquals(1.5f, values[15], 0);

        try {
            matrix.write(new float[] { 0.1f, 0.2f, 0.3f, 0.4f });
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
        } catch (IllegalArgumentException e) {
            // as expected
        }
        try {
            matrix.write(values, values.length + 1);
            fail();
        } catch (IllegalArgumentException e) {
            // as expected
        }
        try {
            matrix.read(new float[4]);
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
        } catch (IllegalArgumentException e) {
            // as expected
        }
        try {
            matrix.read(values, values.length + 1);
            fail();
        } catch (IllegalArgumentException e) {
            // as expected
        }
    }
}
