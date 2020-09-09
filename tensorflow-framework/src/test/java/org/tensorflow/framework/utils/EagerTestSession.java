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
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.*;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/** Eager Mode Test Session */
public class EagerTestSession extends TestSession {

  private final EagerSession session;
  private final Ops tf;

  /** Create an Eager mode test session. */
  public EagerTestSession() {
    this.session = EagerSession.create();
    this.tf = Ops.create(session).withName("test");
  }

  /** {@inheritDoc} */
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

  /** {@inheritDoc} */
  @Override
  public void close() {
    session.close();
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEager() {
    return true;
  }

  /** {@inheritDoc} */
  @Override
  public Session getGraphSession() {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public EagerSession getEagerSession() {
    return this.session;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> void evaluate(double expected, Operand<T> input) {
    DataType<T> dtype = input.asOutput().dataType();
    if (dtype == TFloat32.DTYPE) {
      Operand<TFloat32> o = (Operand<TFloat32>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        o.data()
            .scalars()
            .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat()));
      }
      index.set(0);
      o.data().scalars().forEach(f -> assertEquals(expected, f.getFloat(), epsilon));
    } else if (dtype == TFloat64.DTYPE) {
      Operand<TFloat64> o = (Operand<TFloat64>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        o.data()
            .scalars()
            .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble()));
      }
      index.set(0);
      o.data().scalars().forEach(f -> assertEquals(expected, f.getDouble(), epsilon));
    } else if (dtype == TInt32.DTYPE) {
      Operand<TInt32> o = (Operand<TInt32>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        o.data()
            .scalars()
            .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt()));
      }
      index.set(0);
      o.data().scalars().forEach(f -> assertEquals((int) expected, f.getInt()));
    } else if (dtype == TInt64.DTYPE) {
      Operand<TInt64> o = (Operand<TInt64>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        o.data()
            .scalars()
            .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong()));
      }
      index.set(0);
      o.data().scalars().forEach(f -> assertEquals((long) expected, f.getLong()));
    }
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> void evaluate(Number[] expected, Output<T> input) {
    int size = input.shape().size() == 0 ? 1 : (int) input.shape().size();
    assertEquals(
        expected.length,
        size,
        () -> String.format("expected length (%d) != to input length (%d)", expected.length, size));
    DataType<T> dtype = input.dataType();
    if (dtype == TFloat32.DTYPE) {
      Output<TFloat32> o = (Output<TFloat32>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        o.data()
            .scalars()
            .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat()));
      }
      index.set(0);
      o.data()
          .scalars()
          .forEach(
              f ->
                  assertEquals(
                      expected[index.getAndIncrement()].floatValue(), f.getFloat(), epsilon));
    } else if (dtype == TFloat64.DTYPE) {
      Output<TFloat64> o = (Output<TFloat64>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        o.data()
            .scalars()
            .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble()));
      }
      index.set(0);
      o.data()
          .scalars()
          .forEach(
              f ->
                  assertEquals(
                      expected[index.getAndIncrement()].doubleValue(), f.getDouble(), epsilon));
    } else if (dtype == TInt32.DTYPE) {
      Output<TInt32> o = (Output<TInt32>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        o.data()
            .scalars()
            .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt()));
      }
      index.set(0);
      o.data()
          .scalars()
          .forEach(f -> assertEquals(expected[index.getAndIncrement()].intValue(), f.getInt()));
    } else if (dtype == TInt64.DTYPE) {
      Output<TInt64> o = (Output<TInt64>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        o.data()
            .scalars()
            .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong()));
      }
      index.set(0);
      o.data()
          .scalars()
          .forEach(f -> assertEquals(expected[index.getAndIncrement()].longValue(), f.getLong()));
    }
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void evaluate(FloatNdArray expected, Output<T> input) {
    DataType<T> dtype = input.dataType();
    if (dtype == TFloat32.DTYPE) {
      Output<TFloat32> o = (Output<TFloat32>) input;
      AtomicLong index = new AtomicLong();
      if (debug) {
        o.data()
            .scalars()
            .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat()));
      }
      index.set(0);
      o.data()
          .scalars()
          .forEach(
              f -> assertEquals(expected.getFloat(index.getAndIncrement()), f.getFloat(), epsilon));
    } else if (dtype == TFloat64.DTYPE) {
      Output<TFloat64> o = (Output<TFloat64>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        o.data()
            .scalars()
            .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble()));
      }
      index.set(0);
      o.data()
          .scalars()
          .forEach(
              f ->
                  assertEquals(expected.getFloat(index.getAndIncrement()), f.getDouble(), epsilon));
    } else if (dtype == TInt32.DTYPE) {
      Output<TInt32> o = (Output<TInt32>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        o.data()
            .scalars()
            .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt()));
      }
      index.set(0);
      for (IntNdArray f : o.data().scalars()) {
        assertEquals((int) expected.getFloat(index.getAndIncrement()), f.getInt());
      }
    } else if (dtype == TInt64.DTYPE) {
      Output<TInt64> o = (Output<TInt64>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        o.data()
            .scalars()
            .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong()));
      }
      index.set(0);
      o.data()
          .scalars()
          .forEach(
              f -> assertEquals((long) expected.getFloat(index.getAndIncrement()), f.getLong()));
    }
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void evaluate(Output<T> input, Predicate<Number> predicate) {
    AtomicInteger index = new AtomicInteger();
    DataType<T> dtype = input.asOutput().dataType();
    boolean isScalar = input.shape().equals(Shape.scalar());
    if (dtype == TFloat32.DTYPE) {
      Output<TFloat32> o = (Output<TFloat32>) input;
      if (debug) {
        if (isScalar) {
          System.out.printf(
              "0). %b <==> %f\n", predicate.test(o.data().getFloat()), o.data().getFloat());
        } else {
          o.data()
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
        assertTrue(predicate.test(o.data().getFloat()));
      } else {
        o.data()
            .scalars()
            .forEachIndexed((idx, f) -> assertTrue(predicate.test(o.data().getFloat())));
      }
    } else if (dtype == TFloat64.DTYPE) {
      Output<TFloat64> o = (Output<TFloat64>) input;
      if (debug) {
        if (isScalar) {
          System.out.printf(
              "0). %b <==> %f\n", predicate.test(o.data().getDouble()), o.data().getDouble());
        } else {
          o.data()
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
        assertTrue(predicate.test(o.data().getDouble()));
      } else {
        o.data()
            .scalars()
            .forEachIndexed((idx, f) -> assertTrue(predicate.test(o.data().getDouble())));
      }
    } else if (dtype == TInt32.DTYPE) {
      Output<TInt32> o = (Output<TInt32>) input;
      if (debug) {
        if (isScalar) {
          System.out.printf(
              "0). %b <==> %d\n", predicate.test(o.data().getInt()), o.data().getInt());
        } else {
          o.data()
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
        assertTrue(predicate.test(o.data().getInt()));
      } else {
        o.data()
            .scalars()
            .forEachIndexed((idx, f) -> assertTrue(predicate.test(o.data().getInt())));
      }
    } else if (dtype == TInt64.DTYPE) {
      Output<TInt64> o = (Output<TInt64>) input;
      if (debug) {
        if (isScalar) {
          System.out.printf(
              "0). %b <==> %d\n", predicate.test(o.data().getLong()), o.data().getLong());
        } else {
          o.data()
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
        assertTrue(predicate.test(o.data().getLong()));
      } else {
        o.data()
            .scalars()
            .forEachIndexed((idx, f) -> assertTrue(predicate.test(o.data().getLong())));
      }
    } else {
      fail("Unexpected DataType: " + dtype);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void evaluate(String[] expected, Output<TString> input) {
    int size = input.shape().size() == 0 ? 1 : (int) input.shape().size();
    assertEquals(
        expected.length,
        size,
        () -> String.format("expected length (%d) != to input length (%d)", expected.length, size));
    AtomicInteger index = new AtomicInteger();
    if (debug) {
      input
          .data()
          .scalars()
          .forEach(f -> System.out.printf("%d). %s\n", index.getAndIncrement(), f.getObject()));
    }
    index.set(0);
    input
        .data()
        .scalars()
        .forEach(f -> assertEquals(expected[index.getAndIncrement()], f.getObject()));
  }

  /** {@inheritDoc} */
  @Override
  public void evaluate(Boolean[] expected, Output<TBool> input) {
    int size = input.shape().size() == 0 ? 1 : (int) input.shape().size();
    assertEquals(
        expected.length,
        size,
        () -> String.format("expected size (%d) != to input length (%d)", expected.length, size));
    AtomicInteger index = new AtomicInteger();
    if (debug) {
      input
          .data()
          .scalars()
          .forEach(f -> System.out.printf("%d). %b\n", index.getAndIncrement(), f.getBoolean()));
    }
    index.set(0);
    input
        .data()
        .scalars()
        .forEach(f -> assertEquals(expected[index.getAndIncrement()], f.getBoolean()));
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void evaluate(Output<T> expected, Output<T> input) {
    assert input.shape().equals(expected.shape())
        : String.format(
            "expected shape (%s) != to input shape (%s)",
            expected.shape().toString(), input.shape().toString());
    DataType<T> dtype = input.asOutput().dataType();
    boolean isScalar = input.shape().equals(Shape.scalar());
    if (dtype == TFloat32.DTYPE) {
      Output<TFloat32> x = (Output<TFloat32>) expected;
      Output<TFloat32> o = (Output<TFloat32>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        if (isScalar) {
          System.out.printf("0). %f <==> %f\n", x.data().getFloat(), o.data().getFloat());
        } else {
          o.data()
              .scalars()
              .forEachIndexed(
                  (idx, f) ->
                      System.out.printf(
                          "%d). %f <==> %f\n",
                          index.getAndIncrement(), x.data().getFloat(idx), f.getFloat()));
        }
      }
      index.set(0);
      if (isScalar) {
        assertEquals(x.data().getFloat(), o.data().getFloat(), epsilon);
      } else {
        o.data()
            .scalars()
            .forEachIndexed(
                (idx, f) -> assertEquals(x.data().getFloat(idx), f.getFloat(), epsilon));
      }
    } else if (dtype == TFloat64.DTYPE) {
      Output<TFloat64> x = (Output<TFloat64>) expected;
      Output<TFloat64> o = (Output<TFloat64>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        if (isScalar) {
          System.out.printf("0). %f <==> %f\n", x.data().getDouble(), o.data().getDouble());
        } else {
          o.data()
              .scalars()
              .forEachIndexed(
                  (idx, f) ->
                      System.out.printf(
                          "%d). %f <==> %f\n",
                          index.getAndIncrement(), x.data().getDouble(idx), f.getDouble()));
        }
      }
      index.set(0);
      if (isScalar) {
        assertEquals(x.data().getDouble(), o.data().getDouble(), epsilon);
      } else {
        o.data()
            .scalars()
            .forEachIndexed(
                (idx, f) -> assertEquals(x.data().getDouble(idx), f.getDouble(), epsilon));
      }
    } else if (dtype == TInt32.DTYPE) {
      Output<TInt32> x = (Output<TInt32>) expected;
      Output<TInt32> o = (Output<TInt32>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        if (isScalar) {
          System.out.printf("0). %d <==> %d\n", x.data().getInt(), o.data().getInt());
        } else {
          o.data()
              .scalars()
              .forEachIndexed(
                  (idx, f) ->
                      System.out.printf(
                          "%d). %d <==> %d\n",
                          index.getAndIncrement(), x.data().getInt(idx), f.getInt()));
        }
      }
      index.set(0);
      if (isScalar) {
        assertEquals(x.data().getInt(), o.data().getInt());
      } else {
        o.data()
            .scalars()
            .forEachIndexed((idx, f) -> assertEquals(x.data().getInt(idx), f.getInt()));
      }
    } else if (dtype == TInt64.DTYPE) {
      Output<TInt64> x = (Output<TInt64>) expected;
      Output<TInt64> o = (Output<TInt64>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        if (isScalar) {
          System.out.printf("0). %d  <==> %d\n", x.data().getLong(), o.data().getLong());
        } else {
          o.data()
              .scalars()
              .forEachIndexed(
                  (idx, f) ->
                      System.out.printf(
                          "%d). %d  <==> %d\n",
                          index.getAndIncrement(), x.data().getLong(idx), f.getLong()));
        }
      }
      index.set(0);
      if (isScalar) {
        assertEquals(x.data().getLong(), o.data().getLong());
      } else {
        o.data()
            .scalars()
            .forEachIndexed((idx, f) -> assertEquals(x.data().getLong(idx), f.getLong()));
      }
    } else if (dtype == TString.DTYPE) {
      Output<TString> x = (Output<TString>) expected;
      Output<TString> o = (Output<TString>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        if (isScalar) {
          System.out.printf("0). %s  <==> %s\n", x.data().getObject(), o.data().getObject());
        } else {
          o.data()
              .scalars()
              .forEachIndexed(
                  (idx, f) ->
                      System.out.printf(
                          "%d). %s  <==> %s\n",
                          index.getAndIncrement(), x.data().getObject(idx), f.getObject()));
        }
      }
      index.set(0);
      if (isScalar) {
        assertEquals(x.data().getObject(), o.data().getObject());
      } else {
        o.data()
            .scalars()
            .forEachIndexed((idx, f) -> assertEquals(x.data().getObject(idx), f.getObject()));
      }
    } else if (dtype == TBool.DTYPE) {
      Output<TBool> x = (Output<TBool>) expected;
      Output<TBool> o = (Output<TBool>) input;
      AtomicInteger index = new AtomicInteger();
      if (debug) {
        if (isScalar) {
          System.out.printf("0). %b  <==> %b\n", x.data().getBoolean(), o.data().getBoolean());
        } else {
          o.data()
              .scalars()
              .forEachIndexed(
                  (idx, f) ->
                      System.out.printf(
                          "%d). %b  <==> %b\n",
                          index.getAndIncrement(), x.data().getBoolean(idx), f.getBoolean()));
        }
      }
      index.set(0);
      if (isScalar) {
        assertEquals(x.data().getBoolean(), o.data().getBoolean());
      } else {
        o.data()
            .scalars()
            .forEachIndexed((idx, f) -> assertEquals(x.data().getBoolean(idx), f.getBoolean()));
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TType> void print(PrintWriter writer, Output<T> input) {
    DataType<T> dtype = input.asOutput().dataType();
    if (dtype == TFloat32.DTYPE) {
      Output<TFloat32> o = (Output<TFloat32>) input;
      AtomicInteger index = new AtomicInteger();
      o.data()
          .scalars()
          .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getFloat()));
    } else if (dtype == TFloat64.DTYPE) {
      Output<TFloat64> o = (Output<TFloat64>) input;
      AtomicInteger index = new AtomicInteger();
      o.data()
          .scalars()
          .forEach(f -> System.out.printf("%d). %f\n", index.getAndIncrement(), f.getDouble()));
    } else if (dtype == TInt32.DTYPE) {
      Output<TInt32> o = (Output<TInt32>) input;
      AtomicInteger index = new AtomicInteger();
      o.data()
          .scalars()
          .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getInt()));
    } else if (dtype == TInt64.DTYPE) {
      Output<TInt64> o = (Output<TInt64>) input;
      AtomicInteger index = new AtomicInteger();
      o.data()
          .scalars()
          .forEach(f -> System.out.printf("%d). %d\n", index.getAndIncrement(), f.getLong()));
    } else if (dtype == TString.DTYPE) {
      Output<TString> o = (Output<TString>) input;
      AtomicInteger index = new AtomicInteger();
      o.data()
          .scalars()
          .forEach(f -> System.out.printf("%d). %s\n", index.getAndIncrement(), f.getObject()));
    } else if (dtype == TBool.DTYPE) {
      Output<TBool> o = (Output<TBool>) input;
      AtomicInteger index = new AtomicInteger();
      o.data()
          .scalars()
          .forEach(f -> System.out.printf("%d). %b\n", index.getAndIncrement(), f.getBoolean()));
    } else {
      writer.println("Unexpected DataType: " + dtype);
    }
    writer.flush();
  }
}
