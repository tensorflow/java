/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.tensorflow.op.Ops;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;

@RunWith(JUnit4.class)
public class VectorTest {

  private final Ops tf = Ops.create();

  @Test
  public void createIntVector() {
    int[] values = {1, 2, 3, 4};
    Vector<TInt32> op = tf.vector(values);
    assertEquals(values[0], op.data().getInt(0));
    assertEquals(values[1], op.data().getInt(1));
    assertEquals(values[2], op.data().getInt(2));
    assertEquals(values[3], op.data().getInt(3));
  }

  @Test
  public void createLongVector() {
    long[] values = {1L, 2L, 3L, 4L};
    Vector<TInt64> op = tf.vector(values);
    assertEquals(values[0], op.data().getLong(0));
    assertEquals(values[1], op.data().getLong(1));
    assertEquals(values[2], op.data().getLong(2));
    assertEquals(values[3], op.data().getLong(3));
  }

  @Test
  public void createFloatVector() {
    float[] values = {1.5f, 2.5f, 3.5f, 4.5f};
    Vector<TFloat32> op = tf.vector(values);
    assertEquals(values[0], op.data().getFloat(0), 0);
    assertEquals(values[1], op.data().getFloat(1), 0);
    assertEquals(values[2], op.data().getFloat(2), 0);
    assertEquals(values[3], op.data().getFloat(3), 0);
  }

  @Test
  public void createDoubleVector() {
    double[] values = {1.5, 2.5, 3.5, 4.5};
    Vector<TFloat64> op = tf.vector(values);
    assertEquals(values[0], op.data().getDouble(0), 0);
    assertEquals(values[1], op.data().getDouble(1), 0);
    assertEquals(values[2], op.data().getDouble(2), 0);
    assertEquals(values[3], op.data().getDouble(3), 0);
  }

  @Test
  public void createBooleanVector() {
    boolean[] values = {true, true, false, true};
    Vector<TBool> op = tf.vector(values);
    assertEquals(values[0], op.data().getBoolean(0));
    assertEquals(values[1], op.data().getBoolean(1));
    assertEquals(values[2], op.data().getBoolean(2));
    assertEquals(values[3], op.data().getBoolean(3));
  }

  @Test
  public void createByteVector() {
    byte[] values = {1, 2, 3, 4};
    Vector<TUint8> op = tf.vector(values);
    assertEquals(values[0], op.data().getByte(0));
    assertEquals(values[1], op.data().getByte(1));
    assertEquals(values[2], op.data().getByte(2));
    assertEquals(values[3], op.data().getByte(3));
  }

  @Test
  public void createStringVector() {
    String[] values = {"Cincinnati", "Coaticook", "Chibougamau"};
    Vector<TString> op = tf.vector(values);
    assertEquals(values[0], op.data().getObject(0));
    assertEquals(values[1], op.data().getObject(1));
    assertEquals(values[2], op.data().getObject(2));
  }

  @Test
  public void createStringVectorUsingCharset() {
    String[] values = {"Cincinnati", "Coaticook", "Chibougamau"};
    Charset charset = StandardCharsets.UTF_16LE;
    Vector<TString> op = tf.vector(charset, values);
    TString data = op.data().using(charset);
    assertEquals(values[0], data.getObject(0));
    assertEquals(values[1], data.getObject(1));
    assertEquals(values[2], data.getObject(2));
  }

  @Test
  public void createShapeVector() {
    Shape shape = Shape.of(2, 3, 2);
    Vector<TInt64> op = tf.vector(shape);
    assertEquals(shape.size(0), op.data().getLong(0));
    assertEquals(shape.size(1), op.data().getLong(1));
    assertEquals(shape.size(2), op.data().getLong(2));
  }

  @Test
  public void createScalarShapeVector() {
    Shape shape = Shape.scalar();
    Vector<TInt64> op = tf.vector(shape);
    assertEquals(0, op.data().size());
  }

  @Test
  public void cannotCreateUnknownShapeVector() {
    Shape shape = Shape.unknown();
    try {
      tf.vector(shape);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
  }
}
