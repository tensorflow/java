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
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;

@RunWith(JUnit4.class)
public class ScalarTest {

  private final Ops tf = Ops.create();

  @Test
  public void createInt() {
    int value = 1;
    Scalar<TInt32> op = tf.scalar(value);
    assertEquals(value, op.data().getInt());
  }

  @Test
  public void createFloat() {
    float value = 1;
    Scalar<TFloat32> op = tf.scalar(value);
    assertEquals(value, op.data().getFloat(), 0.0f);
  }

  @Test
  public void createDouble() {
    double value = 1;
    Scalar<TFloat64> op = tf.scalar(value);
    assertEquals(value, op.data().getDouble(), 0.0);
  }

  @Test
  public void createLong() {
    long value = 1;
    Scalar<TInt64> op = tf.scalar(value);
    assertEquals(value, op.data().getLong());
  }

  @Test
  public void createBoolean() {
    boolean value = true;
    Scalar<TBool> op = tf.scalar(value);
    assertTrue(op.data().getBoolean());
  }

  @Test
  public void createByte() {
    byte value = 1;
    Scalar<TUint8> op = tf.scalar(value);
    assertEquals(value, op.data().getByte());
  }

  @Test
  public void createString() {
    String value = "Cincinnati";
    Scalar<TString> op = tf.scalar(value);
    assertEquals(value, op.data().getObject());
  }

  @Test
  public void createStringUsingCharset() {
    String value = "Cincinnati";
    Charset charset = StandardCharsets.UTF_16;
    Scalar<TString> op = tf.scalar(charset, value);
    assertEquals(value, op.data().using(charset).getObject());
  }
}
