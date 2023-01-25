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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class IntNdArrayTestBase extends NdArrayTestBase<Integer> {

    @Override
    protected abstract IntNdArray allocate(Shape shape);

    @Override
    protected Integer valueOf(Long val) {
        return val.intValue();
    }

    @Test
    public void iteratePrimitiveElements() {
        IntNdArray matrix3d = allocate(Shape.of(5, 4, 5));

        matrix3d.scalars().forEachIndexed((coords, scalar) ->
            scalar.setInt((int)coords[2])
        );

        assertEquals(0, matrix3d.getInt(0, 0, 0));
        assertEquals(1, matrix3d.getInt(0, 0, 1));
        assertEquals(4, matrix3d.getInt(0, 0, 4));
        assertEquals(2, matrix3d.getInt(0, 1, 2));

        matrix3d.elements(1).forEach(vector ->
            vector.set(NdArrays.vectorOf(5, 6, 7, 8, 9))
        );

        assertEquals(5, matrix3d.getInt(0, 0, 0));
        assertEquals(6, matrix3d.getInt(0, 0, 1));
        assertEquals(9, matrix3d.getInt(0, 0, 4));
        assertEquals(7, matrix3d.getInt(0, 1, 2));
    }

    @Test
    public void streamingInts() {
        IntNdArray scalar = allocate(Shape.scalar());
        scalar.setInt(1);
        var values = scalar.streamOfInts().toArray();
        assertArrayEquals(new int[]{1}, values);

        IntNdArray vector = allocate(Shape.of(5));
        vector.setInt(1, 0);
        vector.setInt(2, 1);
        vector.setInt(3, 2);
        vector.setInt(4, 3);
        vector.setInt(5, 4);
        values = vector.streamOfInts().toArray();
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, values);

        IntNdArray matrix = allocate(Shape.of(2, 2));
        matrix.setInt(1, 0, 0);
        matrix.setInt(2, 0, 1);
        matrix.setInt(3, 1, 0);
        matrix.setInt(4, 1, 1);
        values = matrix.streamOfInts().toArray();
        assertArrayEquals(new int[]{1, 2, 3, 4}, values);
    }
}
