/*
 Copyright 2019-2023 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.ndarray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public abstract class LongNdArrayTestBase extends NdArrayTestBase<Long> {

    @Override
    protected abstract LongNdArray allocate(Shape shape);

    @Override
    protected Long valueOf(Long val) {
        return val;
    }

    @Test
    public void iteratePrimitiveElements() {
        LongNdArray matrix3d = allocate(Shape.of(5, 4, 5));

        matrix3d.scalars().forEachIndexed((coords, scalar) ->
            scalar.setLong(coords[2])
        );

        assertEquals(0, matrix3d.getLong(0, 0, 0));
        assertEquals(1, matrix3d.getLong(0, 0, 1));
        assertEquals(4, matrix3d.getLong(0, 0, 4));
        assertEquals(2, matrix3d.getLong(0, 1, 2));

        matrix3d.elements(1).forEach(vector ->
            vector.set(NdArrays.vectorOf(5L, 6L, 7L, 8L, 9L))
        );

        assertEquals(5, matrix3d.getLong(0, 0, 0));
        assertEquals(6, matrix3d.getLong(0, 0, 1));
        assertEquals(9, matrix3d.getLong(0, 0, 4));
        assertEquals(7, matrix3d.getLong(0, 1, 2));
    }

    @Test
    public void streamingLongs() {
        LongNdArray scalar = allocate(Shape.scalar());
        scalar.setLong(1L);
        var values = scalar.streamOfLongs().toArray();
        assertArrayEquals(new long[]{1L}, values);

        LongNdArray vector = allocate(Shape.of(5));
        vector.setLong(1L, 0);
        vector.setLong(2L, 1);
        vector.setLong(3L, 2);
        vector.setLong(4L, 3);
        vector.setLong(5L, 4);
        values = vector.streamOfLongs().toArray();
        assertArrayEquals(new long[]{1L, 2L, 3L, 4L, 5L}, values);

        LongNdArray matrix = allocate(Shape.of(2, 2));
        matrix.setLong(1L, 0, 0);
        matrix.setLong(2L, 0, 1);
        matrix.setLong(3L, 1, 0);
        matrix.setLong(4L, 1, 1);
        values = matrix.streamOfLongs().toArray();
        assertArrayEquals(new long[]{1L, 2L, 3L, 4L}, values);
    }
}
