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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ShapeTest {

    @Test
    public void allKnownDimensions() {
        Shape shape = Shape.create(5, 4, 5);
        assertEquals(3, shape.numDimensions());
        assertEquals(5, shape.numElements(0));
        assertEquals(4, shape.numElements(1));
        assertEquals(5, shape.numElements(2));
        assertEquals(100, shape.size());
        assertArrayEquals(new long[] {5, 4, 5}, shape.toArray());
        try {
            shape.numElements(3);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // as expected
        }
        try {
            shape.numElements(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // as expected
        }
        assertNotNull(shape.dimension(0));
        assertNotNull(shape.dimension(1));
        assertNotNull(shape.dimension(2));
        try {
            shape.dimension(3);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // as expected
        }
        try {
            shape.dimension(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // as expected
        }
        Shape subshape = shape.subshape(1);
        assertEquals(2, subshape.numDimensions());
        assertEquals(4, subshape.numElements(0));
        assertEquals(5, subshape.numElements(1));
        assertArrayEquals(new long[] {4, 5}, subshape.toArray());
        assertEquals(20, subshape.size());
        try {
            subshape.numElements(2);
            fail();
        } catch (IndexOutOfBoundsException e) {
            //as expected
        }
        assertSame(shape.dimension(1), subshape.dimension(0));
        assertSame(shape.dimension(2), subshape.dimension(1));
        try {
            subshape.dimension(3);
            fail();
        } catch (IndexOutOfBoundsException e) {
            //as expected
        }
    }

    @Test
    public void hashCodeEquals() {
        Shape shape1 = Shape.create(5, 4, 5);
        Shape shape2 = Shape.create(5, 4, 5);
        Shape shape3 = Shape.create(5, 4, 5, 6);
        Shape shape4 = Shape.create(5, 4, 1);

        assertEquals(shape1, shape2);
        assertEquals(shape1.hashCode(), shape2.hashCode());
        assertNotEquals(shape1, shape3);
        assertNotEquals(shape1.hashCode(), shape3.hashCode());
        assertNotEquals(shape1, shape4);
        assertNotEquals(shape1.hashCode(), shape4.hashCode());

        Shape scalar1 = Shape.create();
        Shape scalar2 = Shape.create();
        assertEquals(scalar1, scalar2);
        assertNotEquals(scalar1, shape1);

        Shape unknown1 = Shape.create(-1, 4, 5);
        Shape unknown2 = Shape.create(-1, 4, 5);
        assertNotEquals(unknown1, unknown2);
        assertNotEquals(unknown1, shape1);
    }
}
