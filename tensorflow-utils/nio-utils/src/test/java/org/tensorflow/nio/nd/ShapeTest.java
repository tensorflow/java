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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ShapeTest {

    @Test
    public void allKnownDimensions() {
        Shape shape = Shape.make(5, 4, 5);
        assertEquals(3, shape.numDimensions());
        assertEquals(5, shape.size(0));
        assertEquals(4, shape.size(1));
        assertEquals(5, shape.size(2));
        assertEquals(100, shape.size());
        assertArrayEquals(new long[] {5, 4, 5}, shape.asArray());
        try {
            shape.size(3);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // as expected
        }
        try {
            shape.size(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // as expected
        }
    }

    @Test
    public void hashCodeEquals() {
        Shape shape1 = Shape.make(5, 4, 5);
        Shape shape2 = Shape.make(5, 4, 5);
        Shape shape3 = Shape.make(5, 4, 5, 6);
        Shape shape4 = Shape.make(5, 4, 1);

        assertEquals(shape1, shape2);
        assertEquals(shape1.hashCode(), shape2.hashCode());
        assertNotEquals(shape1, shape3);
        assertNotEquals(shape1.hashCode(), shape3.hashCode());
        assertNotEquals(shape1, shape4);
        assertNotEquals(shape1.hashCode(), shape4.hashCode());

        Shape scalar1 = Shape.make();
        Shape scalar2 = Shape.make();
        assertEquals(scalar1, scalar2);
        assertNotEquals(scalar1, shape1);

        Shape unknown1 = Shape.make(-1, 4, 5);
        Shape unknown2 = Shape.make(-1, 4, 5);
        assertNotEquals(unknown1, unknown2);
        assertNotEquals(unknown1, shape1);
    }
}
