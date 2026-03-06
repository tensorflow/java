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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public abstract class FloatNdArrayTestBase extends NdArrayTestBase<Float> {

    @Override
    protected abstract FloatNdArray allocate(Shape shape);

    @Override
    protected Float valueOf(Long val) {
        return val.floatValue();
    }

    @Test
    public void iteratePrimitiveElements() {
        FloatNdArray matrix3d = allocate(Shape.of(5, 4, 5));

        matrix3d.scalars().forEachIndexed((coords, scalar) ->
            scalar.setFloat((float)coords[2])
        );

        assertEquals(0.0f, matrix3d.getFloat(0, 0, 0), 0.0f);
        assertEquals(1.0f, matrix3d.getFloat(0, 0, 1), 0.0f);
        assertEquals(4.0f, matrix3d.getFloat(0, 0, 4), 0.0f);
        assertEquals(2.0f, matrix3d.getFloat(0, 1, 2), 0.0f);

        matrix3d.elements(1).forEach(vector ->
            vector.set(NdArrays.vectorOf(5.0f, 6.0f, 7.0f, 8.0f, 9.0f))
        );

        assertEquals(5, matrix3d.getFloat(0, 0, 0), 0.0f);
        assertEquals(6, matrix3d.getFloat(0, 0, 1), 0.0f);
        assertEquals(9, matrix3d.getFloat(0, 0, 4), 0.0f);
        assertEquals(7, matrix3d.getFloat(0, 1, 2), 0.0f);
    }
}
