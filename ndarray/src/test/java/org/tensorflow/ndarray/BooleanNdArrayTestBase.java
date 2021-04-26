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
package org.tensorflow.ndarray;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.tensorflow.ndarray.NdArrays.vectorOf;

import org.junit.jupiter.api.Test;

public abstract class BooleanNdArrayTestBase extends NdArrayTestBase<Boolean> {

    @Override
    protected abstract BooleanNdArray allocate(Shape shape);

    @Override
    protected Boolean valueOf(Long val) {
        return val > 0;
    }

    @Test
    public void iteratePrimitiveElements() {
        BooleanNdArray matrix3d = allocate(Shape.of(5, 4, 5));

        matrix3d.scalars().forEachIndexed((coords, scalar) ->
            scalar.setBoolean(coords[2] > 0)
        );

        assertFalse(matrix3d.getBoolean(0, 0, 0));
        assertTrue(matrix3d.getBoolean(0, 0, 1));
        assertTrue(matrix3d.getBoolean(0, 0, 4));
        assertTrue(matrix3d.getBoolean(0, 1, 2));

        matrix3d.elements(1).forEach(vector ->
            vector.set(vectorOf(true, false, true, false, true))
        );

        assertTrue(matrix3d.getBoolean(0, 0, 0));
        assertFalse(matrix3d.getBoolean(0, 0, 1));
        assertTrue(matrix3d.getBoolean(0, 0, 4));
        assertTrue(matrix3d.getBoolean(0, 1, 2));
    }
}
