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
package org.tensorflow.keras.utils;

import org.tensorflow.*;
import org.tensorflow.Session.Runner;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.*;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/** A Graph Mode Test Session */
public class GraphTestSession extends TestSession {

  private final Graph graph;
  private final Session session;
  private final Ops tf;

  /** Create a Graph Test Session */
  public GraphTestSession() {
    graph = new Graph();
    session = new Session(graph);
    tf = Ops.create(graph).withName("test");
  }

  /** {@inheritDoc} */
  @Override
  public Ops getTF() {
    return tf;
  }

  /**
   * Get the Graph instance for this test Session
   *
   * @return
   */
  public Graph getGraph() {
    return graph;
  }

  /**
   * Get the Graph session instance for this test Session
   *
   * @return
   */
  public Session getSession() {
    return session;
  }

  /** {@inheritDoc} */
  @Override
  public void close() {
    session.close();
    graph.close();
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEager() {
    return false;
  }

  /** {@inheritDoc} */
  @Override
  public Session getGraphSession() {
    return this.session;
  }

  /** {@inheritDoc} */
  @Override
  public EagerSession getEagerSession() {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public void initialize() {
    graph
        .initializers()
        .forEach(
            initializer -> {
              session.runner().addTarget(initializer).run();
            });
  }

  /** {@inheritDoc} */
  @Override
  public void run(Op op) {
    session.run(op);
  }

  /** {@inheritDoc} */
  @Override
  public void run(Op op, Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    createRunner(op, feedDict).run();
  }

  /**
   * Create a runner for the Operation
   *
   * @param op the operation
   * @return the runner
   */
  public Runner createRunner(Op op) {
    return createRunner(op, null);
  }

  /**
   * Create a runner for the Operation
   *
   * @param op the operation
   * @param feedDict the dictionary of values to use for the runner's feed operations. Required when
   *     placeholders are used.
   * @return the runner
   */
  public Runner createRunner(
      Op op, Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    Runner runner = session.runner();
    runner.addTarget(op.op());
    if (feedDict != null && !feedDict.isEmpty()) {
      feedDict.forEach((name, tensor) -> runner.feed(name, tensor));
    }
    return runner;
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TNumber> void evaluate(
      double expected,
      Operand<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    DataType dtype = input.asOutput().dataType();
    if (dtype == TFloat32.DTYPE) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (Tensor<TFloat32> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat());
                  });
        }
      }
      index.set(0);
      try (Tensor<TFloat32> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals((float) expected, f.getFloat(), epsilon);
                });
      }
    } else if (dtype == TFloat64.DTYPE) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (Tensor<TFloat64> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble());
                  });
        }
      }
      index.set(0);
      try (Tensor<TFloat64> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals(expected, f.getDouble(), epsilon);
                });
      }
    } else if (dtype == TInt32.DTYPE) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (Tensor<TInt32> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt());
                  });
        }
      }
      index.set(0);
      try (Tensor<TInt32> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals((int) expected, f.getInt());
                });
      }
    } else if (dtype == TInt64.DTYPE) {
      Output<TInt64> o = (Output<TInt64>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (Tensor<TInt64> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong());
                  });
        }
      }
      index.set(0);
      try (Tensor<TInt64> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals((long) expected, f.getLong());
                });
      }
    } else {
      fail("Unexpected DataType: " + dtype);
    }
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TNumber> void evaluate(
      Number[] expected,
      Output<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    long size = input.shape().size() == 0 ? 1 : input.shape().size();
    if (size != Shape.UNKNOWN_SIZE) {
      assertEquals(
          expected.length,
          size,
          () ->
              String.format("expected length (%d) != to input length (%d)", expected.length, size));
    }
    DataType dtype = input.asOutput().dataType();
    if (dtype == TFloat32.DTYPE) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (Tensor<TFloat32> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat());
                  });
        }
      }
      index.set(0);
      try (Tensor<TFloat32> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals(
                      expected[index.getAndIncrement()].floatValue(), f.getFloat(), epsilon);
                });
      }
    } else if (dtype == TFloat64.DTYPE) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (Tensor<TFloat64> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble());
                  });
        }
      }
      index.set(0);
      try (Tensor<TFloat64> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals(
                      expected[index.getAndIncrement()].doubleValue(), f.getDouble(), epsilon);
                });
      }
    } else if (dtype == TInt32.DTYPE) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (Tensor<TInt32> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt());
                  });
        }
      }
      index.set(0);
      try (Tensor<TInt32> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals(expected[index.getAndIncrement()].intValue(), f.getInt());
                });
      }
    } else if (dtype == TInt64.DTYPE) {
      Output<TInt64> o = (Output<TInt64>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (Tensor<TInt64> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong());
                  });
        }
      }
      index.set(0);
      try (Tensor<TInt64> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals(expected[index.getAndIncrement()].longValue(), f.getLong());
                });
      }
    } else {
      fail("Unexpected DataType: " + dtype);
    }
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TNumber> void evaluate(
      FloatNdArray expected,
      Output<U> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    DataType dtype = input.asOutput().dataType();
    if (dtype == TFloat32.DTYPE) {
      AtomicLong index = new AtomicLong();
      if (debug) {
        try (Tensor<TFloat32> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat());
                  });
        }
      }
      index.set(0);
      try (Tensor<TFloat32> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals(expected.getFloat(index.getAndIncrement()), f.getFloat(), epsilon);
                });
      }
    } else if (dtype == TFloat64.DTYPE) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (Tensor<TFloat64> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble());
                  });
        }
      }
      index.set(0);
      try (Tensor<TFloat64> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals(
                      (double) expected.getFloat(index.getAndIncrement()), f.getDouble(), epsilon);
                });
      }
    } else if (dtype == TInt32.DTYPE) {
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (Tensor<TInt32> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt());
                  });
        }
      }
      index.set(0);
      try (Tensor<TInt32> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals((int) expected.getFloat(index.getAndIncrement()), f.getInt());
                });
      }
    } else if (dtype == TInt64.DTYPE) {
      Output<TInt64> o = (Output<TInt64>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        try (Tensor<TInt64> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE)) {
          result
              .data()
              .scalars()
              .forEach(
                  f -> {
                    System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong());
                  });
        }
      }
      index.set(0);
      try (Tensor<TInt64> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  assertEquals((long) expected.getFloat(index.getAndIncrement()), f.getLong());
                });
      }
    } else {
      fail("Unexpected DataType: " + dtype);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void evaluate(
      String[] expected,
      Output<TString> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    int size = input.shape().size() == 0 ? 1 : (int) input.shape().size();
    assertEquals(
        expected.length,
        size,
        () -> String.format("expected length (%d) != to input length (%d)", expected.length, size));
    AtomicInteger index = new AtomicInteger();
    if (debug) {
      try (Tensor<TString> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TString.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  System.out.printf("%d). %s\n", index.getAndIncrement(), f.getObject());
                });
      }
    }
    index.set(0);
    try (Tensor<TString> result =
        createRunner(input, feedDict).fetch(input).run().get(0).expect(TString.DTYPE)) {
      result
          .data()
          .scalars()
          .forEach(
              f -> {
                assertEquals(expected[index.getAndIncrement()], f.getObject());
              });
    }
  }

  /** {@inheritDoc} */
  @Override
  public void evaluate(
      Boolean[] expected,
      Output<TBool> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    int size = input.shape().size() == 0 ? 1 : (int) input.shape().size();
    assertEquals(
        expected.length,
        size,
        () -> String.format("expected length (%d) != to input length (%d)", expected.length, size));
    AtomicInteger index = new AtomicInteger();
    if (debug) {
      try (Tensor<TBool> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TBool.DTYPE)) {
        result
            .data()
            .scalars()
            .forEach(
                f -> {
                  System.out.printf("%d). %b\n", index.getAndIncrement(), f.getObject());
                });
      }
    }
    index.set(0);
    try (Tensor<TBool> result =
        createRunner(input, feedDict).fetch(input).run().get(0).expect(TBool.DTYPE)) {
      result
          .data()
          .scalars()
          .forEach(
              f -> {
                assertEquals(expected[index.getAndIncrement()], f.getObject());
              });
    }
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void evaluate(
      Output<T> expected,
      Output<T> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    assert input.shape().equals(expected.shape())
        : String.format(
            "expected shape (%s) != to input shape (%s)",
            expected.shape().toString(), input.shape().toString());
    AtomicInteger index = new AtomicInteger();
    DataType dtype = input.asOutput().dataType();
    boolean isScalar = input.shape().equals(Shape.scalar());
    if (dtype == TFloat32.DTYPE) {
      final Output<TFloat32> finalExpected = (Output<TFloat32>) expected;
      if (debug) {
        try (Tensor<TFloat32> result =
                createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE);
            Tensor<TFloat32> expectedResult =
                createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE)) {
          if (isScalar) {
            System.out.printf(
                "0). %f <==> %f\n", expectedResult.data().getFloat(), result.data().getFloat());
          } else {
            result
                .data()
                .scalars()
                .forEachIndexed(
                    (idx, f) -> {
                      System.out.printf(
                          "%d). %f <==> %f\n",
                          index.getAndIncrement(),
                          finalExpected.data().getFloat(idx),
                          f.getFloat());
                    });
          }
        }
      }
      index.set(0);
      try (Tensor<TFloat32> result =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE);
          Tensor<TFloat32> expectedResult =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE)) {
        if (isScalar) {
          assertEquals(expectedResult.data().getFloat(), result.data().getFloat(), epsilon);
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    assertEquals(expectedResult.data().getFloat(idx), f.getFloat(), epsilon);
                  });
        }
      }
    } else if (dtype == TFloat64.DTYPE) {
      final Output<TFloat64> finalExpected = (Output<TFloat64>) expected;
      if (debug) {
        try (Tensor<TFloat64> result =
                createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE);
            Tensor<TFloat64> expectedResult =
                createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE)) {
          if (isScalar) {
            System.out.printf(
                "0). %f <==> %f\n", expectedResult.data().getDouble(), result.data().getDouble());
          } else {
            result
                .data()
                .scalars()
                .forEachIndexed(
                    (idx, f) -> {
                      System.out.printf(
                          "%d). %f <==> %f\n",
                          index.getAndIncrement(),
                          finalExpected.data().getDouble(idx),
                          f.getDouble());
                    });
          }
        }
      }
      index.set(0);
      try (Tensor<TFloat64> result =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE);
          Tensor<TFloat64> expectedResult =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE)) {
        if (isScalar) {
          assertEquals(expectedResult.data().getDouble(), result.data().getDouble(), epsilon);
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    assertEquals(expectedResult.data().getDouble(idx), f.getDouble(), epsilon);
                  });
        }
      }
    } else if (dtype == TInt32.DTYPE) {
      final Output<TInt32> finalExpected = (Output<TInt32>) expected;
      if (debug) {
        try (Tensor<TInt32> result =
                createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE);
            Tensor<TInt32> expectedResult =
                createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE)) {
          if (isScalar) {
            System.out.printf(
                "0). %d <==> %d\n", expectedResult.data().getInt(), result.data().getInt());
          } else {
            result
                .data()
                .scalars()
                .forEachIndexed(
                    (idx, f) -> {
                      System.out.printf(
                          "%d). %d <==> %d\n",
                          index.getAndIncrement(), finalExpected.data().getInt(idx), f.getInt());
                    });
          }
        }
      }
      index.set(0);
      try (Tensor<TInt32> result =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE);
          Tensor<TInt32> expectedResult =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE)) {
        if (isScalar) {
          assertEquals(expectedResult.data().getInt(), result.data().getInt(), epsilon);
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    assertEquals(expectedResult.data().getInt(idx), f.getInt(), epsilon);
                  });
        }
      }
    } else if (dtype == TInt64.DTYPE) {
      final Output<TInt64> finalExpected = (Output<TInt64>) expected;
      if (debug) {
        try (Tensor<TInt64> result =
                createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE);
            Tensor<TInt64> expectedResult =
                createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE)) {
          if (isScalar) {
            System.out.printf(
                "0). %d <==> %d\n", expectedResult.data().getLong(), result.data().getLong());
          } else {
            result
                .data()
                .scalars()
                .forEachIndexed(
                    (idx, f) -> {
                      System.out.printf(
                          "%d). %d <==> %d\n",
                          index.getAndIncrement(), finalExpected.data().getLong(idx), f.getLong());
                    });
          }
        }
      }
      index.set(0);
      try (Tensor<TInt64> result =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE);
          Tensor<TInt64> expectedResult =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE)) {
        if (isScalar) {
          assertEquals(expectedResult.data().getLong(), result.data().getLong(), epsilon);
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    assertEquals(expectedResult.data().getLong(idx), f.getLong(), epsilon);
                  });
        }
      }
    } else if (dtype == TBool.DTYPE) {
      final Output<TBool> finalExpected = (Output<TBool>) expected;
      if (debug) {
        try (Tensor<TBool> result =
                createRunner(input, feedDict).fetch(input).run().get(0).expect(TBool.DTYPE);
            Tensor<TBool> expectedResult =
                createRunner(input, feedDict).fetch(input).run().get(0).expect(TBool.DTYPE)) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %b\n", expectedResult.data().getBoolean(), result.data().getBoolean());
          } else {
            result
                .data()
                .scalars()
                .forEachIndexed(
                    (idx, f) -> {
                      System.out.printf(
                          "%d). %b <==> %b\n",
                          index.getAndIncrement(),
                          finalExpected.data().getBoolean(idx),
                          f.getBoolean());
                    });
          }
        }
      }
      index.set(0);
      try (Tensor<TBool> result =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TBool.DTYPE);
          Tensor<TBool> expectedResult =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TBool.DTYPE)) {
        if (isScalar) {
          assertEquals(expectedResult.data().getBoolean(), result.data().getBoolean());
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    assertEquals(expectedResult.data().getBoolean(idx), f.getBoolean());
                  });
        }
      }
    } else if (dtype == TString.DTYPE) {
      final Output<TString> finalExpected = (Output<TString>) expected;
      if (debug) {
        try (Tensor<TString> result =
                createRunner(input, feedDict).fetch(input).run().get(0).expect(TString.DTYPE);
            Tensor<TString> expectedResult =
                createRunner(input, feedDict).fetch(input).run().get(0).expect(TString.DTYPE)) {
          if (isScalar) {
            System.out.printf(
                "0). %s <==> %s\n", expectedResult.data().getObject(), result.data().getObject());
          } else {
            result
                .data()
                .scalars()
                .forEachIndexed(
                    (idx, f) -> {
                      System.out.printf(
                          "%d). %s <==> %s\n",
                          index.getAndIncrement(),
                          finalExpected.data().getObject(idx),
                          f.getObject());
                    });
          }
        }
      }
      index.set(0);
      try (Tensor<TString> result =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TString.DTYPE);
          Tensor<TString> expectedResult =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TString.DTYPE)) {
        if (isScalar) {
          assertEquals(expectedResult.data().getObject(), result.data().getObject());
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    assertEquals(expectedResult.data().getObject(idx), f.getObject());
                  });
        }
      }
    } else {
      fail("Unexpected DataType: " + dtype);
    }
  }

  /** {@inheritDoc} */
  public <U extends TNumber> void evaluate(
      Output<U> input,
      Predicate<Number> predicate,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    AtomicInteger index = new AtomicInteger();
    DataType dtype = input.asOutput().dataType();
    boolean isScalar = input.shape().equals(Shape.scalar());
    if (dtype == TFloat32.DTYPE) {
      if (debug) {
        try (Tensor<TFloat32> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE)) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %f\n",
                predicate.test(result.data().getFloat()), result.data().getFloat());
          } else {
            result
                .data()
                .scalars()
                .forEachIndexed(
                    (idx, f) -> {
                      System.out.printf(
                          "%d). %b <==> %f\n",
                          index.getAndIncrement(), predicate.test(f.getFloat()), f.getFloat());
                    });
          }
        }
      }
      index.set(0);
      try (Tensor<TFloat32> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE)) {
        if (isScalar) {
          assertTrue(predicate.test(result.data().getFloat()));
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    assertTrue(predicate.test(result.data().getFloat()));
                  });
        }
      }
    } else if (dtype == TFloat64.DTYPE) {
      if (debug) {
        try (Tensor<TFloat64> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE)) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %f\n",
                predicate.test(result.data().getDouble()), result.data().getDouble());
          } else {
            result
                .data()
                .scalars()
                .forEachIndexed(
                    (idx, f) -> {
                      System.out.printf(
                          "%d). %b <==> %f\n",
                          index.getAndIncrement(), predicate.test(f.getDouble()), f.getDouble());
                    });
          }
        }
      }
      index.set(0);
      try (Tensor<TFloat64> result =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE);
          Tensor<TFloat64> expectedResult =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE)) {
        if (isScalar) {
          assertTrue(predicate.test(result.data().getDouble()));
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    assertTrue(predicate.test(result.data().getDouble()));
                  });
        }
      }
    } else if (dtype == TInt32.DTYPE) {
      if (debug) {
        try (Tensor<TInt32> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE)) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %d\n", predicate.test(result.data().getInt()), result.data().getInt());
          } else {
            result
                .data()
                .scalars()
                .forEachIndexed(
                    (idx, f) -> {
                      System.out.printf(
                          "%d). %b <==> %d\n",
                          index.getAndIncrement(), predicate.test(f.getInt()), f.getInt());
                    });
          }
        }
      }
      index.set(0);
      try (Tensor<TInt32> result =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE);
          Tensor<TInt32> expectedResult =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE)) {
        if (isScalar) {
          assertTrue(predicate.test(result.data().getInt()));
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    assertTrue(predicate.test(result.data().getInt()));
                  });
        }
      }
    } else if (dtype == TInt64.DTYPE) {
      if (debug) {
        try (Tensor<TInt64> result =
            createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE)) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %d\n",
                predicate.test(result.data().getLong()), result.data().getLong());
          } else {
            result
                .data()
                .scalars()
                .forEachIndexed(
                    (idx, f) -> {
                      System.out.printf(
                          "%d). %b <==> %d\n",
                          index.getAndIncrement(), predicate.test(f.getLong()), f.getLong());
                    });
          }
        }
      }
      index.set(0);
      try (Tensor<TInt64> result =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE);
          Tensor<TInt64> expectedResult =
              createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE)) {
        if (isScalar) {
          assertTrue(predicate.test(result.data().getLong()));
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    assertTrue(predicate.test(result.data().getLong()));
                  });
        }
      }
    } else {
      fail("Unexpected DataType: " + dtype);
    }
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void print(
      PrintWriter writer,
      Output<T> input,
      Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict) {
    boolean isScalar = input.asOutput().shape().size() == 1;

    DataType dtype = input.dataType();
    if (dtype == TFloat32.DTYPE) {
      AtomicInteger index = new AtomicInteger();
      try (Tensor<TFloat32> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat32.DTYPE)) {
        if (isScalar) {
          writer.printf("%d). %f\n", index.getAndIncrement(), result.data().getFloat());
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    writer.printf("%d). %f\n", index.getAndIncrement(), f.getFloat());
                  });
        }
      }
    } else if (dtype == TFloat64.DTYPE) {
      AtomicInteger index = new AtomicInteger();

      try (Tensor<TFloat64> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TFloat64.DTYPE)) {
        if (isScalar) {
          writer.printf(
              "%d). %f\n", index.getAndIncrement(), ((Output<TFloat64>) input).data().getDouble());
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    writer.printf("%d). %f\n", index.getAndIncrement(), f.getDouble());
                  });
        }
      }
    } else if (dtype == TInt32.DTYPE) {
      AtomicInteger index = new AtomicInteger();

      try (Tensor<TInt32> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt32.DTYPE)) {
        if (isScalar) {
          writer.printf(
              "%d). %f\n", index.getAndIncrement(), ((Output<TInt32>) input).data().getInt());
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    writer.printf("%d). %d\n", index.getAndIncrement(), f.getInt());
                  });
        }
      }
    } else if (dtype == TInt64.DTYPE) {
      AtomicInteger index = new AtomicInteger();

      try (Tensor<TInt64> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TInt64.DTYPE)) {
        if (isScalar) {
          writer.printf(
              "%d). %f\n", index.getAndIncrement(), ((Output<TInt64>) input).data().getLong());
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    writer.printf("%d). %d\n", index.getAndIncrement(), f.getLong());
                  });
        }
      }
    } else if (dtype == TBool.DTYPE) {
      AtomicInteger index = new AtomicInteger();

      try (Tensor<TBool> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TBool.DTYPE)) {
        if (isScalar) {
          writer.printf(
              "%d). %b\n", index.getAndIncrement(), ((Output<TBool>) input).data().getBoolean());
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    writer.printf("%d). %b\n", index.getAndIncrement(), f.getBoolean());
                  });
        }
      }
    } else if (dtype == TString.DTYPE) {
      AtomicInteger index = new AtomicInteger();

      try (Tensor<TString> result =
          createRunner(input, feedDict).fetch(input).run().get(0).expect(TString.DTYPE)) {
        if (isScalar) {
          writer.printf(
              "%d). %s\n", index.getAndIncrement(), ((Output<TString>) input).data().getObject());
        } else {
          result
              .data()
              .scalars()
              .forEachIndexed(
                  (idx, f) -> {
                    writer.printf("%d). %s\n", index.getAndIncrement(), f.getObject());
                  });
        }
      }
    } else {
      writer.println("Unexpected DataType: " + dtype);
    }
    writer.flush();
  }
}
