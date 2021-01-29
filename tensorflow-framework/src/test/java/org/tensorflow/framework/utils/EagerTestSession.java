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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import org.tensorflow.EagerSession;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.TensorScope;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Eager Mode Test Session
 */
public class EagerTestSession extends TestSession {

  private final EagerSession session;
  private final Ops tf;

  /**
   * Create an Eager mode test session.
   */
  public EagerTestSession() {
    this.session = EagerSession.create();
    this.tf = Ops.create(session).withName("test");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Ops getTF() {
    return tf;
  }

  /**
   * Get the TensorFlow EagerSession instance
   *
   * @return the TensorFlow EagerSession instance
   */
  public EagerSession getSession() {
    return session;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void close() {
    session.close();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEager() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Session getGraphSession() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EagerSession getEagerSession() {
    return this.session;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends TNumber> void evaluate(double expected, Operand<T> input) {
    try (TensorScope scope = new TensorScope()) {
      Class<T> inputType = input.type();
      if (inputType == TFloat32.class) {
        Operand<TFloat32> o = (Operand<TFloat32>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat()));
        }
        index.set(0);
        o.asTensor(scope).scalars().forEach(f -> assertEquals(expected, f.getFloat(), epsilon));
      } else if (inputType == TFloat64.class) {
        Operand<TFloat64> o = (Operand<TFloat64>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble()));
        }
        index.set(0);
        o.asTensor(scope).scalars().forEach(f -> assertEquals(expected, f.getDouble(), epsilon));
      } else if (inputType == TInt32.class) {
        Operand<TInt32> o = (Operand<TInt32>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt()));
        }
        index.set(0);
        o.asTensor(scope).scalars().forEach(f -> assertEquals((int) expected, f.getInt()));
      } else if (inputType == TInt64.class) {
        Operand<TInt64> o = (Operand<TInt64>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong()));
        }
        index.set(0);
        o.asTensor(scope).scalars().forEach(f -> assertEquals((long) expected, f.getLong()));
      } else if (inputType == TUint8.class) {
        Operand<TUint8> o = (Operand<TUint8>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %x\n", index.getAndIncrement(), f.getByte()));
        }
        index.set(0);
        o.asTensor(scope).scalars().forEach(f -> assertEquals((long) expected, f.getByte()));
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends TNumber> void evaluate(Number[] expected, Output<T> input) {
    try (TensorScope scope = new TensorScope()) {
      int size = input.shape().size() == 0 ? 1 : (int) input.shape().size();
      assertEquals(
          expected.length,
          size,
          () -> String.format("expected length (%d) != to input length (%d)", expected.length, size));
      Class<T> inputType = input.type();
      if (inputType == TFloat32.class) {
        Output<TFloat32> o = (Output<TFloat32>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat()));
        }
        index.set(0);
        o.asTensor(scope)
            .scalars()
            .forEach(
                f ->
                    assertEquals(
                        expected[index.getAndIncrement()].floatValue(), f.getFloat(), epsilon));
      } else if (inputType == TFloat64.class) {
        Output<TFloat64> o = (Output<TFloat64>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble()));
        }
        index.set(0);
        o.asTensor(scope)
            .scalars()
            .forEach(
                f ->
                    assertEquals(
                        expected[index.getAndIncrement()].doubleValue(), f.getDouble(), epsilon));
      } else if (inputType == TInt32.class) {
        Output<TInt32> o = (Output<TInt32>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt()));
        }
        index.set(0);
        o.asTensor(scope)
            .scalars()
            .forEach(f -> assertEquals(expected[index.getAndIncrement()].intValue(), f.getInt()));
      } else if (inputType == TInt64.class) {
        Output<TInt64> o = (Output<TInt64>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong()));
        }
        index.set(0);
        o.asTensor(scope)
            .scalars()
            .forEach(f -> assertEquals(expected[index.getAndIncrement()].longValue(), f.getLong()));
      } else if (inputType == TUint8.class) {
        Output<TUint8> o = (Output<TUint8>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%x). %d\n", index.getAndIncrement(), f.getByte()));
        }
        index.set(0);
        o.asTensor(scope)
            .scalars()
            .forEach(f -> assertEquals(expected[index.getAndIncrement()].byteValue(), f.getByte()));
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends TType> void evaluate(FloatNdArray expected, Output<T> input) {
    try (TensorScope scope = new TensorScope()) {
      Class<T> inputType = input.type();
      if (inputType == TFloat32.class) {
        Output<TFloat32> o = (Output<TFloat32>) input;
        AtomicLong index = new AtomicLong();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat()));
        }
        index.set(0);
        o.asTensor(scope)
            .scalars()
            .forEach(
                f -> assertEquals(expected.getFloat(index.getAndIncrement()), f.getFloat(), epsilon));
      } else if (inputType == TFloat64.class) {
        Output<TFloat64> o = (Output<TFloat64>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble()));
        }
        index.set(0);
        o.asTensor(scope)
            .scalars()
            .forEach(
                f ->
                    assertEquals(expected.getFloat(index.getAndIncrement()), f.getDouble(), epsilon));
      } else if (inputType == TInt32.class) {
        Output<TInt32> o = (Output<TInt32>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt()));
        }
        index.set(0);
        for (IntNdArray f : o.asTensor(scope).scalars()) {
          assertEquals((int) expected.getFloat(index.getAndIncrement()), f.getInt());
        }
      } else if (inputType == TInt64.class) {
        Output<TInt64> o = (Output<TInt64>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong()));
        }
        index.set(0);
        o.asTensor(scope)
            .scalars()
            .forEach(
                f -> assertEquals((long) expected.getFloat(index.getAndIncrement()), f.getLong()));
      } else if (inputType == TUint8.class) {
        Output<TUint8> o = (Output<TUint8>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          o.asTensor(scope)
              .scalars()
              .forEach(f -> System.out.printf("%d). %x\n", index.getAndIncrement(), f.getByte()));
        }
        index.set(0);
        o.asTensor(scope)
            .scalars()
            .forEach(
                f -> assertEquals((long) expected.getFloat(index.getAndIncrement()), f.getByte()));
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void evaluateString(Output<TString> input, Predicate<String> predicate) {
    try (TensorScope scope = new TensorScope()) {
      AtomicInteger index = new AtomicInteger();
      boolean isScalar = input.shape().equals(Shape.scalar());
      if (debug) {
        if (isScalar) {
          System.out.printf(
              "0). %b <==> %s\n", predicate.test(input.asTensor(scope).getObject()), input.asTensor(scope).getObject());
        } else {
          input
              .asTensor(scope)
              .scalars()
              .forEachIndexed(
                  (idx, s) ->
                      System.out.printf(
                          "%d). %b <==> %s\n",
                          index.getAndIncrement(), predicate.test(s.getObject()), s.getObject()));
        }
      }
      index.set(0);
      if (isScalar) {
        assertTrue(predicate.test(input.asTensor(scope).getObject()));
      } else {
        input.asTensor(scope).scalars().forEachIndexed((idx, s) -> assertTrue(predicate.test(s.getObject())));
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends TType> void evaluate(Output<T> input, Predicate<Number> predicate) {
    try (TensorScope scope = new TensorScope()) {
      AtomicInteger index = new AtomicInteger();
      Class<T> inputType = input.type();
      boolean isScalar = input.shape().equals(Shape.scalar());
      if (inputType == TFloat32.class) {
        Output<TFloat32> o = (Output<TFloat32>) input;
        if (debug) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %f\n", predicate.test(o.asTensor(scope).getFloat()), o.asTensor(scope).getFloat());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b <==> %f\n",
                            index.getAndIncrement(), predicate.test(f.getFloat()), f.getFloat()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertTrue(predicate.test(o.asTensor(scope).getFloat()));
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed((idx, f) -> assertTrue(predicate.test(o.asTensor(scope).getFloat())));
        }
      } else if (inputType == TFloat64.class) {
        Output<TFloat64> o = (Output<TFloat64>) input;
        if (debug) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %f\n", predicate.test(o.asTensor(scope).getDouble()), o.asTensor(scope).getDouble());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b <==> %f\n",
                            index.getAndIncrement(), predicate.test(f.getDouble()), f.getDouble()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertTrue(predicate.test(o.asTensor(scope).getDouble()));
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed((idx, f) -> assertTrue(predicate.test(o.asTensor(scope).getDouble())));
        }
      } else if (inputType == TFloat16.class) {
        Output<TFloat16> o = (Output<TFloat16>) input;
        if (debug) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %f\n", predicate.test(o.asTensor(scope).getFloat()), o.asTensor(scope).getFloat());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b <==> %f\n",
                            index.getAndIncrement(), predicate.test(f.getFloat()), f.getFloat()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertTrue(predicate.test(o.asTensor(scope).getFloat()));
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed((idx, f) -> assertTrue(predicate.test(o.asTensor(scope).getFloat())));
        }
      } else if (inputType == TInt32.class) {
        Output<TInt32> o = (Output<TInt32>) input;
        if (debug) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %d\n", predicate.test(o.asTensor(scope).getInt()), o.asTensor(scope).getInt());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b <==> %d\n",
                            index.getAndIncrement(), predicate.test(f.getInt()), f.getInt()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertTrue(predicate.test(o.asTensor(scope).getInt()));
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed((idx, f) -> assertTrue(predicate.test(o.asTensor(scope).getInt())));
        }
      } else if (inputType == TInt64.class) {
        Output<TInt64> o = (Output<TInt64>) input;
        if (debug) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %d\n", predicate.test(o.asTensor(scope).getLong()), o.asTensor(scope).getLong());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b <==> %d\n",
                            index.getAndIncrement(), predicate.test(f.getLong()), f.getLong()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertTrue(predicate.test(o.asTensor(scope).getLong()));
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed((idx, f) -> assertTrue(predicate.test(o.asTensor(scope).getLong())));
        }
      } else if (inputType == TUint8.class) {
        Output<TUint8> o = (Output<TUint8>) input;
        if (debug) {
          if (isScalar) {
            System.out.printf(
                "0). %b <==> %x\n", predicate.test(o.asTensor(scope).getByte()), o.asTensor(scope).getByte());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b <==> %x\n",
                            index.getAndIncrement(), predicate.test(f.getByte()), f.getByte()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertTrue(predicate.test(o.asTensor(scope).getByte()));
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed((idx, f) -> assertTrue(predicate.test(o.asTensor(scope).getByte())));
        }
      } else {
        fail("Unexpected Class: " + inputType);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void evaluate(String[] expected, Output<TString> input) {
    try (TensorScope scope = new TensorScope()) {
      int size = input.shape().size() == 0 ? 1 : (int) input.shape().size();
      assertEquals(
          expected.length,
          size,
          () -> String.format("expected length (%d) != to input length (%d)", expected.length, size));
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        input
            .asTensor(scope)
            .scalars()
            .forEach(f -> System.out.printf("%d). %s\n", index.getAndIncrement(), f.getObject()));
      }
      index.set(0);
      input
          .asTensor(scope)
          .scalars()
          .forEach(f -> assertEquals(expected[index.getAndIncrement()], f.getObject()));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void evaluate(Boolean[] expected, Output<TBool> input) {
    try (TensorScope scope = new TensorScope()) {
      int size = input.shape().size() == 0 ? 1 : (int) input.shape().size();
      assertEquals(
          expected.length,
          size,
          () -> String.format("expected size (%d) != to input length (%d)", expected.length, size));
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        input
            .asTensor(scope)
            .scalars()
            .forEach(f -> System.out.printf("%d). %b\n", index.getAndIncrement(), f.getBoolean()));
      }
      index.set(0);
      input
          .asTensor(scope)
          .scalars()
          .forEach(f -> assertEquals(expected[index.getAndIncrement()], f.getBoolean()));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends TType> void evaluate(Output<T> expected, Output<T> input) {
    try (TensorScope scope = new TensorScope()) {
      assert input.shape().equals(expected.shape())
          : String.format(
          "expected shape (%s) != to input shape (%s)",
          expected.shape().toString(), input.shape().toString());
      Class<T> inputType = input.asOutput().type();
      boolean isScalar = input.shape().equals(Shape.scalar());
      if (inputType == TFloat32.class) {
        Output<TFloat32> x = (Output<TFloat32>) expected;
        Output<TFloat32> o = (Output<TFloat32>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          if (isScalar) {
            System.out.printf("0). %f <==> %f\n", x.asTensor(scope).getFloat(), o.asTensor(scope).getFloat());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %f <==> %f\n",
                            index.getAndIncrement(), x.asTensor(scope).getFloat(idx), f.getFloat()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertEquals(x.asTensor(scope).getFloat(), o.asTensor(scope).getFloat(), epsilon);
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed(
                  (idx, f) -> assertEquals(x.asTensor(scope).getFloat(idx), f.getFloat(), epsilon));
        }
      } else if (inputType == TFloat64.class) {
        Output<TFloat64> x = (Output<TFloat64>) expected;
        Output<TFloat64> o = (Output<TFloat64>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          if (isScalar) {
            System.out.printf("0). %f <==> %f\n", x.asTensor(scope).getDouble(), o.asTensor(scope).getDouble());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %f <==> %f\n",
                            index.getAndIncrement(), x.asTensor(scope).getDouble(idx), f.getDouble()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertEquals(x.asTensor(scope).getDouble(), o.asTensor(scope).getDouble(), epsilon);
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed(
                  (idx, f) -> assertEquals(x.asTensor(scope).getDouble(idx), f.getDouble(), epsilon));
        }
      } else if (inputType == TInt32.class) {
        Output<TInt32> x = (Output<TInt32>) expected;
        Output<TInt32> o = (Output<TInt32>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          if (isScalar) {
            System.out.printf("0). %d <==> %d\n", x.asTensor(scope).getInt(), o.asTensor(scope).getInt());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %d <==> %d\n",
                            index.getAndIncrement(), x.asTensor(scope).getInt(idx), f.getInt()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertEquals(x.asTensor(scope).getInt(), o.asTensor(scope).getInt());
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed((idx, f) -> assertEquals(x.asTensor(scope).getInt(idx), f.getInt()));
        }
      } else if (inputType == TInt64.class) {
        Output<TInt64> x = (Output<TInt64>) expected;
        Output<TInt64> o = (Output<TInt64>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          if (isScalar) {
            System.out.printf("0). %d  <==> %d\n", x.asTensor(scope).getLong(), o.asTensor(scope).getLong());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %d  <==> %d\n",
                            index.getAndIncrement(), x.asTensor(scope).getLong(idx), f.getLong()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertEquals(x.asTensor(scope).getLong(), o.asTensor(scope).getLong());
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed((idx, f) -> assertEquals(x.asTensor(scope).getLong(idx), f.getLong()));
        }
      } else if (inputType == TUint8.class) {
        Output<TUint8> x = (Output<TUint8>) expected;
        Output<TUint8> o = (Output<TUint8>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          if (isScalar) {
            System.out.printf("0). %x  <==> %x\n", x.asTensor(scope).getByte(), o.asTensor(scope).getByte());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %x  <==> %x\n",
                            index.getAndIncrement(), x.asTensor(scope).getByte(idx), f.getByte()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertEquals(x.asTensor(scope).getByte(), o.asTensor(scope).getByte());
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed((idx, f) -> assertEquals(x.asTensor(scope).getByte(idx), f.getByte()));
        }
      } else if (inputType == TString.class) {
        Output<TString> x = (Output<TString>) expected;
        Output<TString> o = (Output<TString>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          if (isScalar) {
            System.out.printf("0). %s  <==> %s\n", x.asTensor(scope).getObject(), o.asTensor(scope).getObject());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %s  <==> %s\n",
                            index.getAndIncrement(), x.asTensor(scope).getObject(idx), f.getObject()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertEquals(x.asTensor(scope).getObject(), o.asTensor(scope).getObject());
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed((idx, f) -> assertEquals(x.asTensor(scope).getObject(idx), f.getObject()));
        }
      } else if (inputType == TBool.class) {
        Output<TBool> x = (Output<TBool>) expected;
        Output<TBool> o = (Output<TBool>) input;
        AtomicInteger index = new AtomicInteger();
        if (debug) {
          if (isScalar) {
            System.out.printf("0). %b  <==> %b\n", x.asTensor(scope).getBoolean(), o.asTensor(scope).getBoolean());
          } else {
            o.asTensor(scope)
                .scalars()
                .forEachIndexed(
                    (idx, f) ->
                        System.out.printf(
                            "%d). %b  <==> %b\n",
                            index.getAndIncrement(), x.asTensor(scope).getBoolean(idx), f.getBoolean()));
          }
        }
        index.set(0);
        if (isScalar) {
          assertEquals(x.asTensor(scope).getBoolean(), o.asTensor(scope).getBoolean());
        } else {
          o.asTensor(scope)
              .scalars()
              .forEachIndexed((idx, f) -> assertEquals(x.asTensor(scope).getBoolean(idx), f.getBoolean()));
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends TType> void print(PrintWriter writer, Output<T> input) {
    try (TensorScope scope = new TensorScope()) {
      Class<T> inputType = input.asOutput().type();
      if (inputType == TFloat32.class) {
        Output<TFloat32> o = (Output<TFloat32>) input;
        AtomicInteger index = new AtomicInteger();
        o.asTensor(scope)
            .scalars()
            .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat()));
      } else if (inputType == TFloat64.class) {
        Output<TFloat64> o = (Output<TFloat64>) input;
        AtomicInteger index = new AtomicInteger();
        o.asTensor(scope)
            .scalars()
            .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble()));
      } else if (inputType == TInt32.class) {
        Output<TInt32> o = (Output<TInt32>) input;
        AtomicInteger index = new AtomicInteger();
        o.asTensor(scope)
            .scalars()
            .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt()));
      } else if (inputType == TInt64.class) {
        Output<TInt64> o = (Output<TInt64>) input;
        AtomicInteger index = new AtomicInteger();
        o.asTensor(scope)
            .scalars()
            .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong()));
      } else if (inputType == TUint8.class) {
        Output<TUint8> o = (Output<TUint8>) input;
        AtomicInteger index = new AtomicInteger();
        o.asTensor(scope)
            .scalars()
            .forEach(f -> System.out.printf("%d). %x\n", index.getAndIncrement(), f.getByte()));
      } else if (inputType == TString.class) {
        Output<TString> o = (Output<TString>) input;
        AtomicInteger index = new AtomicInteger();
        o.asTensor(scope)
            .scalars()
            .forEach(f -> System.out.printf("%d). %s\n", index.getAndIncrement(), f.getObject()));
      } else if (inputType == TBool.class) {
        Output<TBool> o = (Output<TBool>) input;
        AtomicInteger index = new AtomicInteger();
        o.asTensor(scope)
            .scalars()
            .forEach(f -> System.out.printf("%d). %b\n", index.getAndIncrement(), f.getBoolean()));
      } else {
        writer.println("Unexpected Class: " + inputType);
      }
      writer.flush();
    }
  }
}
