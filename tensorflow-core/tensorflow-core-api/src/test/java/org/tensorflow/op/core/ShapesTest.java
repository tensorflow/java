/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache /p[rintLicense, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/
package org.tensorflow.op.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.tensorflow.EagerSession;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.op.JavaScope;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

public class ShapesTest {

  /**
   * Test of flatten method, of class Shapes.
   */
  @Test
  public void testFlatten_Operand() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Shape<TInt64> expResult = Shape.create(scope, operand, TInt64.class);
      Operand<TFloat32> reshaped =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{4, 2, 1}));
      Operand<TFloat32> actual = Shapes.flatten(scope, reshaped);
      Shape<TInt64> tfshape = Shape.create(scope, actual, TInt64.class);

      AtomicInteger index = new AtomicInteger();
      try (TInt64 result1 = (TInt64) session.runner().fetch(tfshape.asOutput()).run().get(0);
          TInt64 result2 = (TInt64) session.runner().fetch(expResult.asOutput()).run().get(0)) {
        result1
            .scalars()
            .forEach(
                s -> assertEquals(result2.getLong(index.getAndIncrement()), s.getLong()));
      }
    }
  }

  /**
   * Test of flatten method, of class Shapes.
   */
  @Test
  public void testFlatten_Shape() {
    try (EagerSession session = EagerSession.create()) {
      Scope scope = new JavaScope(session);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Shape<TInt64> expShape = Shape.create(scope, operand, TInt64.class);
      Operand<TFloat32> actual =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{4, 2, 1}));
      Shape<TInt64> tfshape = Shape.create(scope, actual, TInt64.class);
      Operand<TInt64> flattened = Shapes.flatten(scope, tfshape, TInt64.class);

      AtomicInteger index = new AtomicInteger();
      flattened
          .asTensor()
          .scalars()
          .forEach(
              s ->
                  assertEquals(
                      expShape.asTensor().getLong(index.getAndIncrement()), s.getLong()));
    }
  }

  /**
   * Test of size method, of class Shapes.
   */
  @Test
  public void testSize_Shape() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{4, 2, 1}));
      Shape<TInt64> tfshape = Shape.create(scope, actual, TInt64.class);
      Operand<TInt64> size = Shapes.size(scope, tfshape, TInt64.class);

      AtomicInteger index = new AtomicInteger();
      try (TInt64 result1 = (TInt64) session.runner().fetch(size.asOutput()).run().get(0)) {
        result1.scalars().forEach(s -> assertEquals(8, s.getLong()));
      }
    }
  }

  /**
   * Test of size method, of class Shapes.
   */
  @Test
  public void testSize_Shape_Operand() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{4, 2, 1}));
      Shape<TInt32> tfshape = Shape.create(scope, actual);

      Operand<TInt32> size = Shapes.size(scope, tfshape, Constant.scalarOf(scope, 0));
      try (TInt32 result = (TInt32) session.runner().fetch(size.asOutput()).run().get(0)) {
        result.scalars().forEach(s -> assertEquals(4, s.getInt()));
      }

      size = Shapes.size(scope, tfshape, Constant.scalarOf(scope, 1));
      try (TInt32 result = (TInt32) session.runner().fetch(size.asOutput()).run().get(0)) {
        result.scalars().forEach(s -> assertEquals(2, s.getInt()));
      }

      size = Shapes.size(scope, tfshape, Constant.scalarOf(scope, 2));
      try (TInt32 result = (TInt32) session.runner().fetch(size.asOutput()).run().get(0)) {
        result.scalars().forEach(s -> assertEquals(1, s.getInt()));
      }
    }
  }

  /**
   * Test of size method, of class Shapes.
   */
  @Test
  public void testSize_Operand_Operand() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{4, 2, 1}));

      Operand<TInt32> size = Shapes.size(scope, actual, Constant.scalarOf(scope, 0));
      try (TInt32 result = (TInt32) session.runner().fetch(size.asOutput()).run().get(0)) {
        result.scalars().forEach(s -> assertEquals(4, s.getInt()));
      }

      size = Shapes.size(scope, actual, Constant.scalarOf(scope, 1));
      try (TInt32 result = (TInt32) session.runner().fetch(size.asOutput()).run().get(0)) {
        result.scalars().forEach(s -> assertEquals(2, s.getInt()));
      }

      size = Shapes.size(scope, actual, Constant.scalarOf(scope, 2));
      try (TInt32 result = (TInt32) session.runner().fetch(size.asOutput()).run().get(0)) {
        result.scalars().forEach(s -> assertEquals(1, s.getInt()));
      }
    }
  }

  /**
   * Test of numDimensions method, of class Shapes.
   */
  @Test
  public void testNumDimensions() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{4, 2, 1}));
      Shape<TInt32> tfshape = Shape.create(scope, actual);

      Operand<TInt32> nDims = Shapes.numDimensions(scope, tfshape);
      try (TInt32 result = (TInt32) session.runner().fetch(nDims.asOutput()).run().get(0)) {
        result.scalars().forEach(s -> assertEquals(3, s.getInt()));
      }
    }
  }

  /**
   * Test of reduceDims method, of class Shapes.
   */
  @Test
  public void testReduceDims_Operand_Operand() {
    try (EagerSession session = EagerSession.create()) {
      Scope scope = new JavaScope(session);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{2, 2, 2}));
      Shape<TInt32> tfshape = Shape.create(scope, actual);

      Operand<TFloat32> reduced = Shapes.reduceDims(scope, actual, Constant.scalarOf(scope, 0));
      Shape<TInt32> reducedShape = Shape.create(scope, reduced);
      AtomicInteger index = new AtomicInteger();
      int[] expected = {8};
      reducedShape
          .asTensor()
          .scalars()
          .forEach(
              s -> {
                assertEquals(expected[index.getAndIncrement()], s.getInt());
              });
      assertEquals(expected.length, index.get());
    }
  }

  /**
   * Test of reduceDims method, of class Shapes.
   */
  @Test
  public void testReduceDims_Shape_Operand() {
    try (EagerSession session = EagerSession.create()) {
      Scope scope = new JavaScope(session);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{2, 2, 2}));
      Shape<TInt32> tfshape = Shape.create(scope, actual);

      Operand<TFloat32> reduced = Shapes.reduceDims(scope, actual, Constant.scalarOf(scope, 0));
      Shape<TInt32> reducedShape = Shape.create(scope, reduced);
      AtomicInteger index = new AtomicInteger();
      int[] expected1 = {8};
      reducedShape
          .asTensor()
          .scalars()
          .forEach(
              s -> {
                assertEquals(expected1[index.getAndIncrement()], s.getInt());
              });
      assertEquals(expected1.length, index.get());

      reduced = Shapes.reduceDims(scope, actual, Constant.scalarOf(scope, 1));
      reducedShape = Shape.create(scope, reduced);
      index.set(0);
      int[] expected2 = {2, 4};
      reducedShape
          .asTensor()
          .scalars()
          .forEach(
              s -> {
                assertEquals(expected2[index.getAndIncrement()], s.getInt());
              });
      assertEquals(expected2.length, index.get());

      reduced = Shapes.reduceDims(scope, actual, Constant.scalarOf(scope, -1));
      reducedShape = Shape.create(scope, reduced);
      index.set(0);
      int[] expected3 = {2, 2, 2};
      reducedShape
          .asTensor()
          .scalars()
          .forEach(
              s -> {
                assertEquals(expected3[index.getAndIncrement()], s.getInt());
              });
      assertEquals(expected3.length, index.get());
    }
  }

  /**
   * Test of squeeze method, of class Shapes.
   */
  @Test
  public void testSqueeze() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{4, 1, 2, 1}));
      Shape<TInt32> tfshape = Shape.create(scope, actual);

      Operand<TInt32> squeezed = Shapes.squeeze(scope, tfshape);
      AtomicInteger index = new AtomicInteger();
      int[] expected = {4, 2};
      try (TInt32 result = (TInt32) session.runner().fetch(squeezed.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getInt());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }

  @Test
  public void testHead() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{4, 1, 2, 1}));
      Shape<TInt32> tfshape = Shape.create(scope, actual);

      Operand<TInt32> head = Shapes.head(scope, tfshape);
      AtomicInteger index = new AtomicInteger();
      int[] expected = {4};
      try (TInt32 result = (TInt32) session.runner().fetch(head.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getInt());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }

  @Test
  public void testTake() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{4, 1, 2, 1}));
      Shape<TInt32> tfshape = Shape.create(scope, actual);

      Operand<TInt32> take = Shapes.take(scope, tfshape, Constant.scalarOf(scope, 2));
      AtomicInteger index = new AtomicInteger();
      int[] expected = {4, 1};
      try (TInt32 result = (TInt32) session.runner().fetch(take.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getInt());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }

  @Test
  public void testTail() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{4, 1, 2, 1}));
      Shape<TInt32> tfshape = Shape.create(scope, actual);

      Operand<TInt32> tail = Shapes.tail(scope, tfshape);
      AtomicInteger index = new AtomicInteger();
      int[] expected = {1};
      try (TInt32 result = (TInt32) session.runner().fetch(tail.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getInt());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }

  @Test
  public void testTakeLast() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual =
          Reshape.create(scope, operand, Constant.vectorOf(scope, new long[]{4, 1, 2, 1}));
      Shape<TInt32> tfshape = Shape.create(scope, actual);

      Operand<TInt32> takeLast = Shapes.takeLast(scope, tfshape, Constant.scalarOf(scope, 3));
      AtomicInteger index = new AtomicInteger();
      int[] expected = {1, 2, 1};
      try (TInt32 result = (TInt32) session.runner().fetch(takeLast.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getInt());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }

  @Test
  public void testPrependInt() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual = Reshape
          .create(scope, operand, Constant.vectorOf(scope, new long[]{4, 2}));
      Shape<TInt32> tfshape = Shape.create(scope, actual);

      Operand<TInt32> prepend = Shapes.prepend(scope, tfshape, 3);
      AtomicInteger index = new AtomicInteger();
      int[] expected = {3, 4, 2};
      try (TInt32 result = (TInt32) session.runner().fetch(prepend.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getInt());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }

  @Test
  public void testPrependLong() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual = Reshape
          .create(scope, operand, Constant.vectorOf(scope, new long[]{4, 2}));
      Shape<TInt64> tfshape = Shape.create(scope, actual, TInt64.class);

      Operand<TInt64> prepend = Shapes.prepend(scope, tfshape, 1L);
      AtomicInteger index = new AtomicInteger();
      long[] expected = {1, 4, 2};
      try (TInt64 result = (TInt64) session.runner().fetch(prepend.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getLong());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }

  @Test
  public void testPrependShapeTInt32() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand1 = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual1 =
          Reshape.create(scope, operand1, Constant.vectorOf(scope, new long[]{4, 2}));
      Operand<TFloat32> operand2 = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual2 =
          Reshape.create(scope, operand2, Constant.vectorOf(scope, new long[]{2, 4}));
      Shape<TInt32> tfshape1 = Shape.create(scope, actual1);
      Shape<TInt32> tfshape2 = Shape.create(scope, actual2);

      Operand<TInt32> prepend = Shapes.prepend(scope, tfshape1, tfshape2);
      AtomicInteger index = new AtomicInteger();
      int[] expected = {2, 4, 4, 2};
      try (TInt32 result = (TInt32) session.runner().fetch(prepend.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getInt());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }

  @Test
  public void testPrependShapeTInt64() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand1 = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual1 =
          Reshape.create(scope, operand1, Constant.vectorOf(scope, new long[]{4, 2}));
      Operand<TFloat32> operand2 = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual2 =
          Reshape.create(scope, operand2, Constant.vectorOf(scope, new long[]{2, 4}));
      Shape<TInt64> tfshape1 = Shape.create(scope, actual1, TInt64.class);
      Shape<TInt64> tfshape2 = Shape.create(scope, actual2, TInt64.class);

      Operand<TInt64> prepend = Shapes.prepend(scope, tfshape1, tfshape2);
      AtomicInteger index = new AtomicInteger();
      long[] expected = {2, 4, 4, 2};
      try (TInt64 result = (TInt64) session.runner().fetch(prepend.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getLong());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }

  @Test
  public void testAppendLong() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual = Reshape
          .create(scope, operand, Constant.vectorOf(scope, new long[]{4, 2}));
      Shape<TInt64> tfshape = Shape.create(scope, actual, TInt64.class);

      Operand<TInt64> append = Shapes.append(scope, tfshape, 2L);
      AtomicInteger index = new AtomicInteger();
      long[] expected = {4L, 2L, 2L};
      try (TInt64 result = (TInt64) session.runner().fetch(append.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getLong());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }

  @Test
  public void testAppendInt() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual = Reshape
          .create(scope, operand, Constant.vectorOf(scope, new long[]{4, 2}));
      Shape<TInt32> tfshape = Shape.create(scope, actual);

      Operand<TInt32> append = Shapes.append(scope, tfshape, 2);
      AtomicInteger index = new AtomicInteger();
      int[] expected = {4, 2, 2};
      try (TInt32 result = (TInt32) session.runner().fetch(append.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getInt());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }

  @Test
  public void testAppendShapeTInt32() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand1 = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual1 =
          Reshape.create(scope, operand1, Constant.vectorOf(scope, new long[]{4, 2}));
      Operand<TFloat32> operand2 = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual2 =
          Reshape.create(scope, operand2, Constant.vectorOf(scope, new long[]{2, 4}));
      Shape<TInt32> tfshape1 = Shape.create(scope, actual1);
      Shape<TInt32> tfshape2 = Shape.create(scope, actual2);

      Operand<TInt32> append = Shapes.append(scope, tfshape1, tfshape2);
      AtomicInteger index = new AtomicInteger();
      int[] expected = {4, 2, 2, 4};
      try (TInt32 result = (TInt32) session.runner().fetch(append.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getInt());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }

  @Test
  public void testAppendShapeTInt64() {
    try (Graph g = new Graph();
        Session session = new Session(g)) {
      Scope scope = new JavaScope(g);
      Operand<TFloat32> operand1 = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual1 =
          Reshape.create(scope, operand1, Constant.vectorOf(scope, new long[]{4, 2}));
      Operand<TFloat32> operand2 = Constant.arrayOf(scope, new float[]{1, 2, 3, 4, 5, 6, 7, 8});
      Operand<TFloat32> actual2 =
          Reshape.create(scope, operand2, Constant.vectorOf(scope, new long[]{2, 4}));
      Shape<TInt64> tfshape1 = Shape.create(scope, actual1, TInt64.class);
      Shape<TInt64> tfshape2 = Shape.create(scope, actual2, TInt64.class);

      Operand<TInt64> append = Shapes.append(scope, tfshape1, tfshape2);
      AtomicInteger index = new AtomicInteger();
      long[] expected = {4, 2, 2, 4};
      try (TInt64 result = (TInt64) session.runner().fetch(append.asOutput()).run().get(0)) {
        result
            .scalars()
            .forEach(
                s -> {
                  assertEquals(expected[index.getAndIncrement()], s.getLong());
                });
      }
      assertEquals(expected.length, index.get());
    }
  }
}
