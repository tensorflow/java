/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.utils;

import org.tensorflow.*;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.*;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Graph Mode Test Session
 */
public class GraphTestSession extends TestSession {

  private final Graph graph;
  private final Session session;
  private final Ops tf;

  /**
   * Create a Graph mode test session.
   */
  public GraphTestSession() {
    graph = new Graph();
    session = new Session(graph);
    tf = Ops.create(graph).withName("test");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Ops getTF() {
    return tf;
  }

  /**
   * Get the Graph object that is represented by this Test Session
   */
  public Graph getGraph() {
    return graph;
  }

  /**
   * Get the TensorFlow Session instance
   *
   * @return the TensorFlow Session instance
   */
  public Session getSession() {
    return session;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void close() {
    session.close();
    graph.close();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEager() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Session getGraphSession() {
    return this.session;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EagerSession getEagerSession() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initialize() {
    graph.initializers().forEach(initializer -> session.runner().addTarget(initializer).run());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(Op op) {
    session.run(op);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends TNumber> void evaluate(double expected, Operand<T> input) {
    Class<T> inputType = input.type();
    if (inputType == TFloat32.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TFloat32 result =
            (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat()));
        }
      }
      index.set(0);
      try (TFloat32 result =
          (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result.scalars().forEach(f -> assertEquals((float) expected, f.getFloat(), epsilon));
      }
    } else if (inputType == TFloat64.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TFloat64 result =
            (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble()));
        }
      }
      index.set(0);
      try (TFloat64 result =
          (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result.scalars().forEach(f -> assertEquals(expected, f.getDouble(), epsilon));
      }
    } else if (inputType == TInt32.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TInt32 result =
            (TInt32)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt()));
        }
      }
      index.set(0);
      try (TInt32 result =
          (TInt32)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result.scalars().forEach(f -> assertEquals((int) expected, f.getInt()));
      }
    } else if (inputType == TInt64.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TInt64 result =
            (TInt64)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong()));
        }
      }
      index.set(0);
      try (TInt64 result =
          (TInt64)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result.scalars().forEach(f -> assertEquals((long) expected, f.getLong()));
      }
    } else if (inputType == TUint8.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TUint8 result =
            (TUint8)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getByte()));
        }
      }
      index.set(0);
      try (TUint8 result =
          (TUint8)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result.scalars().forEach(f -> assertEquals((long) expected, f.getByte()));
      }
    } else {
      fail("Unexpected type class: " + inputType);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends TNumber> void evaluate(Number[] expected, Output<T> input) {
    int size = input.shape().size() == 0 ? 1 : (int) input.shape().size();
    assertEquals(
        expected.length,
        size,
        () -> String.format("expected length (%d) != to input length (%d)", expected.length, size));
    Class<T> inputType = input.type();
    if (inputType == TFloat32.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TFloat32 result =
            (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat()));
        }
      }
      index.set(0);
      try (TFloat32 result =
          (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result
            .scalars()
            .forEach(
                f ->
                    assertEquals(
                        expected[index.getAndIncrement()].floatValue(), f.getFloat(), epsilon));
      }
    } else if (inputType == TFloat64.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TFloat64 result =
            (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble()));
        }
      }
      index.set(0);
      try (TFloat64 result =
          (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result
            .scalars()
            .forEach(
                f ->
                    assertEquals(
                        expected[index.getAndIncrement()].doubleValue(), f.getDouble(), epsilon));
      }
    } else if (inputType == TInt32.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TInt32 result =
            (TInt32)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt()));
        }
      }
      index.set(0);
      try (TInt32 result =
          (TInt32)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result
            .scalars()
            .forEach(f -> assertEquals(expected[index.getAndIncrement()].intValue(), f.getInt()));
      }
    } else if (inputType == TInt64.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TInt64 result =
            (TInt64)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong()));
        }
      }
      index.set(0);
      try (TInt64 result =
          (TInt64)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result
            .scalars()
            .forEach(f -> assertEquals(expected[index.getAndIncrement()].longValue(), f.getLong()));
      }
    } else if (inputType == TUint8.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TUint8 result =
            (TUint8)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getByte()));
        }
      }
      index.set(0);
      try (TUint8 result =
          (TUint8)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result
            .scalars()
            .forEach(f -> assertEquals(expected[index.getAndIncrement()].longValue(), f.getByte()));
      }
    } else {
      fail("Unexpected type class: " + inputType);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends TType> void evaluate(FloatNdArray expected, Output<T> input) {
    Class<T> inputType = input.type();
    if (inputType == TFloat32.class) {
      AtomicLong index = new AtomicLong();
      if (debug) {
        try (TFloat32 result =
            (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat()));
        }
      }
      index.set(0);
      try (TFloat32 result =
          (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result
            .scalars()
            .forEach(
                f ->
                    assertEquals(
                        expected.getFloat(index.getAndIncrement()), f.getFloat(), epsilon));
      }
    } else if (inputType == TFloat64.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TFloat64 result =
            (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble()));
        }
      }
      index.set(0);
      try (TFloat64 result =
          (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result
            .scalars()
            .forEach(
                f ->
                    assertEquals(
                        expected.getFloat(index.getAndIncrement()), f.getDouble(), epsilon));
      }
    } else if (inputType == TInt32.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TInt32 result =
            (TInt32)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt()));
        }
      }
      index.set(0);
      try (TInt32 result =
          (TInt32)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result
            .scalars()
            .forEach(
                f -> assertEquals((int) expected.getFloat(index.getAndIncrement()), f.getInt()));
      }
    } else if (inputType == TInt64.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TInt64 result =
            (TInt64)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong()));
        }
      }
      index.set(0);
      try (TInt64 result =
          (TInt64)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result
            .scalars()
            .forEach(
                f -> assertEquals((long) expected.getFloat(index.getAndIncrement()), f.getLong()));
      }
    } else if (inputType == TUint8.class) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (TUint8 result =
            (TUint8)this.getGraphSession().runner().fetch(input).run().get(0)) {
          result
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getByte()));
        }
      }
      index.set(0);
      try (TUint8 result =
          (TUint8)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result
            .scalars()
            .forEach(
                f -> assertEquals((long) expected.getFloat(index.getAndIncrement()), f.getByte()));
      }
    } else {
      fail("Unexpected type class: " + inputType);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void evaluate(String[] expected, Output<TString> input) {
    int size = input.shape().size() == 0 ? 1 : (int) input.shape().size();
    assertEquals(
        expected.length,
        size,
        () -> String.format("expected length (%d) != to input length (%d)", expected.length, size));
    AtomicInteger index = new AtomicInteger();
    if (debug) {
      try (TString result =
          (TString)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result
            .scalars()
            .forEach(f -> System.out.printf("%d). %s\n", index.getAndIncrement(), f.getObject()));
      }
    }
    index.set(0);
    try (TString result =
        (TString)this.getGraphSession().runner().fetch(input).run().get(0)) {
      result
          .scalars()
          .forEach(f -> assertEquals(expected[index.getAndIncrement()], f.getObject()));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void evaluate(Boolean[] expected, Output<TBool> input) {
    int size = input.shape().size() == 0 ? 1 : (int) input.shape().size();
    assertEquals(
        expected.length,
        size,
        () -> String.format("expected length (%d) != to input length (%d)", expected.length, size));
    AtomicInteger index = new AtomicInteger();
    if (debug) {
      try (TBool result =
          (TBool)this.getGraphSession().runner().fetch(input).run().get(0)) {
        result
            .scalars()
            .forEach(f -> System.out.printf("%d). %b\n", index.getAndIncrement(), f.getObject()));
      }
    }
    index.set(0);
    try (TBool result =
        (TBool)this.getGraphSession().runner().fetch(input).run().get(0)) {
      result
          .scalars()
          .forEach(f -> assertEquals(expected[index.getAndIncrement()], f.getObject()));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends TType> void evaluate(Output<T> expected, Output<T> input) {
    assert input.shape().equals(expected.shape())
        : String.format(
        "expected shape (%s) != to input shape (%s)",
        expected.shape().toString(), input.shape().toString());
    AtomicInteger index = new AtomicInteger();
    Class<T> inputType = input.type();
    if (!inputType.equals(expected.type())) {
      throw new IllegalArgumentException(
          String.format(
              "Both data type must be equal, inout = %s, expected = %s",
              inputType, expected.dataType()));
    }
    boolean isScalar = input.shape().equals(Shape.scalar());
    if (inputType == TFloat32.class) {
      final Output<TFloat32> finalExpected = (Output<TFloat32>) expected;
      if (debug) {
        try (TFloat32 result =
            (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0);
            TFloat32 expectedResult =
                (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %f <==> %f\n", expectedResult.getFloat(), result.getFloat());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %f <==> %f\n",
                            index.getAndIncrement(),
                            finalExpected.asTensor().getFloat(idx),
                            f.getFloat()));
          }
        }
      }
      index.set(0);
      try (TFloat32 result =
          (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0);
          TFloat32 expectedResult =
              (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertEquals(expectedResult.getFloat(), result.getFloat(), epsilon);
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) ->
                      assertEquals(expectedResult.getFloat(idx), f.getFloat(), epsilon));
        }
      }
    } else if (inputType == TFloat64.class) {
      final Output<TFloat64> finalExpected = (Output<TFloat64>) expected;
      if (debug) {
        try (TFloat64 result =
            (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0);
            TFloat64 expectedResult =
                (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %f <==> %f\n", expectedResult.getDouble(), result.getDouble());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %f <==> %f\n",
                            index.getAndIncrement(),
                            finalExpected.asTensor().getDouble(idx),
                            f.getDouble()));
          }
        }
      }
      index.set(0);
      try (TFloat64 result =
          (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0);
          TFloat64 expectedResult =
              (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertEquals(expectedResult.getDouble(), result.getDouble(), epsilon);
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) ->
                      assertEquals(expectedResult.getDouble(idx), f.getDouble(), epsilon));
        }
      }
    } else if (inputType == TFloat16.class) {
      final Output<TFloat16> finalExpected = (Output<TFloat16>) expected;
      if (debug) {
        try (TFloat16 result =
            (TFloat16)this.getGraphSession().runner().fetch(input).run().get(0);
            TFloat16 expectedResult =
                (TFloat16)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %f <==> %f\n", expectedResult.getFloat(), result.getFloat());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %f <==> %f\n",
                            index.getAndIncrement(),
                            finalExpected.asTensor().getFloat(idx),
                            f.getFloat()));
          }
        }
      }
      index.set(0);
      try (TFloat16 result =
          (TFloat16)this.getGraphSession().runner().fetch(input).run().get(0);
          TFloat16 expectedResult =
              (TFloat16)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertEquals(expectedResult.getFloat(), result.getFloat(), epsilon);
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) ->
                      assertEquals(expectedResult.getFloat(idx), f.getFloat(), epsilon));
        }
      }
    } else if (inputType == TInt32.class) {
      final Output<TInt32> finalExpected = (Output<TInt32>) expected;
      if (debug) {
        try (TInt32 result =
            (TInt32)this.getGraphSession().runner().fetch(input).run().get(0);
            TInt32 expectedResult =
                (TInt32)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %d <==> %d\n", expectedResult.getInt(), result.getInt());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %d <==> %d\n",
                            index.getAndIncrement(), finalExpected.asTensor().getInt(idx), f.getInt()));
          }
        }
      }
      index.set(0);
      try (TInt32 result =
          (TInt32)this.getGraphSession().runner().fetch(input).run().get(0);
          TInt32 expectedResult =
              (TInt32)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertEquals(expectedResult.getInt(), result.getInt(), epsilon);
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) -> assertEquals(expectedResult.getInt(idx), f.getInt(), epsilon));
        }
      }
    } else if (inputType == TInt64.class) {
      final Output<TInt64> finalExpected = (Output<TInt64>) expected;
      if (debug) {
        try (TInt64 result =
            (TInt64)this.getGraphSession().runner().fetch(input).run().get(0);
            TInt64 expectedResult =
                (TInt64)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %d <==> %d\n", expectedResult.getLong(), result.getLong());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %d <==> %d\n",
                            index.getAndIncrement(),
                            finalExpected.asTensor().getLong(idx),
                            f.getLong()));
          }
        }
      }
      index.set(0);
      try (TInt64 result =
          (TInt64)this.getGraphSession().runner().fetch(input).run().get(0);
          TInt64 expectedResult =
              (TInt64)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertEquals(expectedResult.getLong(), result.getLong(), epsilon);
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) ->
                      assertEquals(expectedResult.getLong(idx), f.getLong(), epsilon));
        }
      }
    } else if (inputType == TUint8.class) {
      final Output<TUint8> finalExpected = (Output<TUint8>) expected;
      if (debug) {
        try (TUint8 result =
            (TUint8)this.getGraphSession().runner().fetch(input).run().get(0);
            TUint8 expectedResult =
                (TUint8)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %d <==> %d\n", expectedResult.getByte(), result.getByte());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %d <==> %d\n",
                            index.getAndIncrement(),
                            finalExpected.asTensor().getByte(idx),
                            f.getByte()));
          }
        }
      }
      index.set(0);
      try (TUint8 result =
          (TUint8)this.getGraphSession().runner().fetch(input).run().get(0);
          TUint8 expectedResult =
              (TUint8)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertEquals(expectedResult.getByte(), result.getByte(), epsilon);
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) ->
                      assertEquals(expectedResult.getByte(idx), f.getByte(), epsilon));
        }
      }
    } else if (inputType == TBool.class) {
      final Output<TBool> finalExpected = (Output<TBool>) expected;
      if (debug) {
        try (TBool result =
            (TBool)this.getGraphSession().runner().fetch(input).run().get(0);
            TBool expectedResult =
                (TBool)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %b\n", expectedResult.getBoolean(), result.getBoolean());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b <==> %b\n",
                            index.getAndIncrement(),
                            finalExpected.asTensor().getBoolean(idx),
                            f.getBoolean()));
          }
        }
      }
      index.set(0);
      try (TBool result =
          (TBool)this.getGraphSession().runner().fetch(input).run().get(0);
          TBool expectedResult =
              (TBool)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertEquals(expectedResult.getBoolean(), result.getBoolean());
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) -> assertEquals(expectedResult.getBoolean(idx), f.getBoolean()));
        }
      }
    } else if (inputType == TString.class) {
      final Output<TString> finalExpected = (Output<TString>) expected;
      if (debug) {
        try (TString result =
            (TString)this.getGraphSession().runner().fetch(input).run().get(0);
            TString expectedResult =
                (TString)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %s <==> %s\n", expectedResult.getObject(), result.getObject());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %s <==> %s\n",
                            index.getAndIncrement(),
                            finalExpected.asTensor().getObject(idx),
                            f.getObject()));
          }
        }
      }
      index.set(0);
      try (TString result =
          (TString)this.getGraphSession().runner().fetch(input).run().get(0);
          TString expectedResult =
              (TString)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertEquals(expectedResult.getObject(), result.getObject());
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) -> assertEquals(expectedResult.getObject(idx), f.getObject()));
        }
      }
    } else {
      fail("Unexpected type class: " + inputType);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void evaluateString(Output<TString> input, Predicate<String> predicate) {
    boolean isScalar = input.shape().equals(Shape.scalar());
    AtomicInteger index = new AtomicInteger();
    if (debug) {
      try (TString result =
          (TString)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          System.out.printf(
              "0). %b <==> %s\n",
              predicate.test(result.getObject()), result.getObject());
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) ->
                      System.out.printf(
                          "%d). %b <==> %s\n",
                          index.getAndIncrement(), predicate.test(f.getObject()), f.getObject()));
        }
      }
    }
    index.set(0);
    try (TString result =
        (TString)this.getGraphSession().runner().fetch(input).run().get(0)) {
      if (isScalar) {
        assertTrue(predicate.test(result.getObject()));
      } else {
        result
            .scalars()
            .forEachIndexed((idx, s) -> assertTrue(predicate.test(s.getObject())));
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends TType> void evaluate(Output<T> input, Predicate<Number> predicate) {
    AtomicInteger index = new AtomicInteger();
    Class<T> inputType = input.type();
    boolean isScalar = input.shape().equals(Shape.scalar());
    if (inputType == TFloat32.class) {
      if (debug) {
        try (TFloat32 result =
            (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %f\n",
                predicate.test(result.getFloat()), result.getFloat());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b <==> %f\n",
                            index.getAndIncrement(), predicate.test(f.getFloat()), f.getFloat()));
          }
        }
      }
      index.set(0);
      try (TFloat32 result =
          (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertTrue(predicate.test(result.getFloat()));
        } else {
          result
              .scalars()
              .forEachIndexed((idx, f) -> assertTrue(predicate.test(result.getFloat())));
        }
      }
    } else if (inputType == TFloat64.class) {
      if (debug) {
        try (TFloat64 result =
            (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %f\n",
                predicate.test(result.getDouble()), result.getDouble());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b <==> %f\n",
                            index.getAndIncrement(), predicate.test(f.getDouble()), f.getDouble()));
          }
        }
      }
      index.set(0);
      try (TFloat64 result =
          (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertTrue(predicate.test(result.getDouble()));
        } else {
          result
              .scalars()
              .forEachIndexed((idx, f) -> assertTrue(predicate.test(result.getDouble())));
        }
      }
    } else if (inputType == TInt32.class) {
      if (debug) {
        try (TInt32 result =
            (TInt32)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %d\n", predicate.test(result.getInt()), result.getInt());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b <==> %d\n",
                            index.getAndIncrement(), predicate.test(f.getInt()), f.getInt()));
          }
        }
      }
      index.set(0);
      try (TInt32 result =
          (TInt32)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertTrue(predicate.test(result.getInt()));
        } else {
          result
              .scalars()
              .forEachIndexed((idx, f) -> assertTrue(predicate.test(result.getInt())));
        }
      }
    } else if (inputType == TInt64.class) {
      if (debug) {
        try (TInt64 result =
            (TInt64)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %d\n",
                predicate.test(result.getLong()), result.getLong());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b <==> %d\n",
                            index.getAndIncrement(), predicate.test(f.getLong()), f.getLong()));
          }
        }
      }
      index.set(0);
      try (TInt64 result =
          (TInt64)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertTrue(predicate.test(result.getLong()));
        } else {
          result
              .scalars()
              .forEachIndexed((idx, f) -> assertTrue(predicate.test(result.getLong())));
        }
      }
    } else if (inputType == TUint8.class) {
      if (debug) {
        try (TUint8 result =
            (TUint8)this.getGraphSession().runner().fetch(input).run().get(0)) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %d\n",
                predicate.test(result.getByte()), result.getByte());
          } else {
            result
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b <==> %d\n",
                            index.getAndIncrement(), predicate.test(f.getByte()), f.getByte()));
          }
        }
      }
      index.set(0);
      try (TUint8 result =
          (TUint8)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          assertTrue(predicate.test(result.getByte()));
        } else {
          result
              .scalars()
              .forEachIndexed((idx, f) -> assertTrue(predicate.test(result.getByte())));
        }
      }
    } else {
      fail("Unexpected type class: " + inputType);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends TType> void print(PrintWriter writer, Output<T> input) {
    boolean isScalar = input.shape().size() == 1;

    Class<T> inputType = input.type();
    if (inputType == TFloat32.class) {
      AtomicInteger index = new AtomicInteger();
      try (TFloat32 result =
          (TFloat32)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          writer.printf("%d). %f\n", index.getAndIncrement(), result.getFloat());
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) -> writer.printf("%d). %f\n", index.getAndIncrement(), f.getFloat()));
        }
      }
    } else if (inputType == TFloat64.class) {
      AtomicInteger index = new AtomicInteger();

      try (TFloat64 result =
          (TFloat64)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          writer.printf(
              "%d). %f\n", index.getAndIncrement(), ((Output<TFloat64>) input).asTensor().getDouble());
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) -> writer.printf("%d). %f\n", index.getAndIncrement(), f.getDouble()));
        }
      }
    } else if (inputType == TInt32.class) {
      AtomicInteger index = new AtomicInteger();

      try (TInt32 result =
          (TInt32)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          writer.printf(
              "%d). %d\n", index.getAndIncrement(), ((Output<TInt32>) input).asTensor().getInt());
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) -> writer.printf("%d). %d\n", index.getAndIncrement(), f.getInt()));
        }
      }
    } else if (inputType == TInt64.class) {
      AtomicInteger index = new AtomicInteger();

      try (TInt64 result =
          (TInt64)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          writer.printf(
              "%d). %d\n", index.getAndIncrement(), ((Output<TInt64>) input).asTensor().getLong());
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) -> writer.printf("%d). %d\n", index.getAndIncrement(), f.getLong()));
        }
      }
    } else if (inputType == TUint8.class) {
      AtomicInteger index = new AtomicInteger();

      try (TUint8 result =
          (TUint8)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          writer.printf(
              "%d). %x\n", index.getAndIncrement(), ((Output<TUint8>) input).asTensor().getByte());
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) -> writer.printf("%d). %x\n", index.getAndIncrement(), f.getByte()));
        }
      }
    } else if (inputType == TBool.class) {
      AtomicInteger index = new AtomicInteger();

      try (TBool result =
          (TBool)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          writer.printf(
              "%d). %b\n", index.getAndIncrement(), ((Output<TBool>) input).asTensor().getBoolean());
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) -> writer.printf("%d). %b\n", index.getAndIncrement(), f.getBoolean()));
        }
      }
    } else if (inputType == TString.class) {
      AtomicInteger index = new AtomicInteger();

      try (TString result =
          (TString)this.getGraphSession().runner().fetch(input).run().get(0)) {
        if (isScalar) {
          writer.printf(
              "%d). %s\n", index.getAndIncrement(), ((Output<TString>) input).asTensor().getObject());
        } else {
          result
              .scalars()
              .forEachIndexed(
                  (idx, f) -> writer.printf("%d). %s\n", index.getAndIncrement(), f.getObject()));
        }
      }
    } else {
      writer.println("Unexpected type class: " + inputType);
    }
    writer.flush();
  }
}
