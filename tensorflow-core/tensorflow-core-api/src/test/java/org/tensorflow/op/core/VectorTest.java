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

import static org.junit.Assert.assertArrayEquals;
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
    int[] data = new int[values.length];
    op.data().read(data);
    assertArrayEquals(values, data);
  }

  @Test
  public void createLongVector() {
    long[] values = {1L, 2L, 3L, 4L};
    Vector<TInt64> op = tf.vector(values);
    long[] data = new long[values.length];
    op.data().read(data);
    assertArrayEquals(values, data);
  }

  @Test
  public void createFloatVector() {
    float[] values = {1.5f, 2.5f, 3.5f, 4.5f};
    Vector<TFloat32> op = tf.vector(values);
    float[] data = new float[values.length];
    op.data().read(data);
    assertArrayEquals(values, data, 0);
  }

  @Test
  public void createDoubleVector() {
    double[] values = {1.5, 2.5, 3.5, 4.5};
    Vector<TFloat64> op = tf.vector(values);
    double[] data = new double[values.length];
    op.data().read(data);
    assertArrayEquals(values, data, 0);
  }

  @Test
  public void createBooleanVector() {
    boolean[] values = {true, true, false, true};
    Vector<TBool> op = tf.vector(values);
    boolean[] data = new boolean[values.length];
    op.data().read(data);
    assertArrayEquals(values, data);
  }

  @Test
  public void createByteVector() {
    byte[] values = {1, 2, 3, 4};
    Vector<TUint8> op = tf.vector(values);
    byte[] data = new byte[values.length];
    op.data().read(data);
    assertArrayEquals(values, data);
  }

  @Test
  public void createStringVector() {
    String[] values = {"Cincinnati", "Coaticook", "Chibougamau"};
    Vector<TString> op = tf.vector(values);
    String[] data = new String[values.length];
    op.data().read(data);
    assertArrayEquals(values, data);
  }

  @Test
  public void createStringVectorUsingCharset() {
    String[] values = {"Cincinnati", "Coaticook", "Chibougamau"};
    Charset charset = StandardCharsets.UTF_16LE;
    Vector<TString> op = tf.vector(charset, values);
    String[] data = new String[values.length];
    op.data().using(charset).read(data);
    assertArrayEquals(values, data);
  }

  @Test
  public void createShapeVector() {
    Shape shape = Shape.of(2, 3, 2);
    Vector<TInt64> op = tf.vector(shape);
    long[] data = new long[shape.numDimensions()];
    op.data().read(data);
    assertArrayEquals(data, shape.asArray());
  }

  @Test
  public void createScalarShapeVector() {
    Shape shape = Shape.scalar();
    Vector<TInt64> op = tf.vector(shape);
    long[] data = new long[shape.numDimensions()];
    op.data().read(data);
    assertArrayEquals(data, shape.asArray());
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
