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

public abstract class ShortNdArrayTestBase extends NdArrayTestBase<Short> {

    @Override
    protected abstract ShortNdArray allocate(Shape shape);

    @Override
    protected Short valueOf(Long val) {
        return val.shortValue();
    }

    @Test
    public void iteratePrimitiveElements() {
        ShortNdArray matrix3d = allocate(Shape.of(5, 4, 5));

        matrix3d.scalars().forEachIndexed((coords, scalar) ->
            scalar.setShort((short)coords[2])
        );

        assertEquals(0, matrix3d.getShort(0, 0, 0));
        assertEquals(1, matrix3d.getShort(0, 0, 1));
        assertEquals(4, matrix3d.getShort(0, 0, 4));
        assertEquals(2, matrix3d.getShort(0, 1, 2));

        matrix3d.elements(1).forEach(vector ->
            vector.set(NdArrays.vectorOf((short)5, (short)6, (short)7, (short)8, (short)9))
        );

        assertEquals(5, matrix3d.getShort(0, 0, 0));
        assertEquals(6, matrix3d.getShort(0, 0, 1));
        assertEquals(9, matrix3d.getShort(0, 0, 4));
        assertEquals(7, matrix3d.getShort(0, 1, 2));
    }
}
