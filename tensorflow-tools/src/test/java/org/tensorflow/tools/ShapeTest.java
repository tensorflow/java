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
package org.tensorflow.tools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeTest {

  @Test
  public void allKnownDimensions() {
    Shape shape = Shape.of(5, 4, 5);
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
    assertEquals(5, shape.size(-1));
    assertEquals(4, shape.size(-2));
    assertEquals(5, shape.size(-3));
    try {
      shape.size(-4);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
    assertFalse(shape.isUnknown());
    assertFalse(shape.hasUnknownDimension());
    assertFalse(shape.isScalar());
  }

  @Test
  public void hashCodeEquals() {
    Shape shape1 = Shape.of(5, 4, 5);
    Shape shape2 = Shape.of(5, 4, 5);
    Shape shape3 = Shape.of(5, 4, 5, 6);
    Shape shape4 = Shape.of(5, 4, 1);

    assertEquals(shape1, shape2);
    assertEquals(shape1.hashCode(), shape2.hashCode());
    assertNotEquals(shape1, shape3);
    assertNotEquals(shape1.hashCode(), shape3.hashCode());
    assertNotEquals(shape1, shape4);
    assertNotEquals(shape1.hashCode(), shape4.hashCode());

    Shape scalar1 = Shape.of();
    Shape scalar2 = Shape.of();
    assertEquals(scalar1, scalar2);
    assertNotEquals(scalar1, shape1);

    Shape unknown1 = Shape.of(-1, 4, 5);
    Shape unknown2 = Shape.of(-1, 4, 5);
    assertNotEquals(unknown1, unknown2);
    assertNotEquals(unknown1, shape1);
    assertEquals(unknown1, unknown1);

    Shape sizeUnknown1 = Shape.unknown();
    Shape sizeUnknown2 = Shape.unknown();
    assertNotEquals(sizeUnknown1, sizeUnknown2);
    assertEquals(sizeUnknown1, sizeUnknown1);
  }

  @Test
  public void testShapeModification() {
    Shape one = Shape.of(2, 4, 6, 8);
    assertEquals(one.head(), Shape.of(2));
    assertEquals(one.tail(), Shape.of(4, 6, 8));

    Shape two = Shape.of(5);
    assertEquals(two.head(), two);
    assertEquals(two.tail(), Shape.of());

    try {
      Shape.of().head();
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }

    assertEquals(Shape.of().tail(), Shape.of());

    Shape three = Shape.of(2, 4, 6);
    assertEquals(three.prepend(5), Shape.of(5, 2, 4, 6));

    assertEquals(Shape.of(5, 2, 4, 6), two.append(three));
    assertEquals(Shape.of(2, 4, 6, 5), two.prepend(three));
    assertEquals(Shape.of(1, 2, 3, 4), Shape.of(1, 2).append(Shape.of(3, 4)));
    assertEquals(Shape.of(1, 2, 3, 4), Shape.of(1, 2, 3).append(4));
    assertEquals(Shape.of(1, 2, 3, 4), Shape.of(1, 2, 3, 4).append(Shape.scalar()));
    assertEquals(Shape.of(3, 4, 1, 2), Shape.of(1, 2).prepend(Shape.of(3, 4)));
    assertEquals(Shape.of(4, 6), three.takeLast(2));
    assertEquals(Shape.scalar(), three.takeLast(0));
    assertEquals(Shape.of(2, 4), three.take(2));
    assertEquals(Shape.scalar(), three.take(0));

    try {
      Shape.unknown().append(Shape.of(1, 2));
      fail();
    } catch (NullPointerException e) {
      // as expected
    }

    try {
      Shape.unknown().prepend(Shape.of(1, 2));
      fail();
    } catch (NullPointerException e) {
      // as expected
    }

    // changing the values of the array returned by asArray should not mutate the shape
    long[] internalShape = one.asArray();
    assertNotNull(internalShape);
    internalShape[0] = 42L;
    assertEquals(2L, one.size(0));
  }
}
