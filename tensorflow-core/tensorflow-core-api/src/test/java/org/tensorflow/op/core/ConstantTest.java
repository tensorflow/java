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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

@RunWith(JUnit4.class)
public class ConstantTest {
  private static final float EPSILON = 1e-7f;
  
  @Test
  public void createInt() {
    int value = 1;
    
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TInt32> op = Constant.create(scope, value);
      try (Tensor<TInt32> result = sess.runner().fetch(op).run().get(0).expect(TInt32.DTYPE)) {
        assertEquals(value, result.intValue());
      }
    }
  }

  @Test
  public void createIntBuffer() {
    int[] ints = {1, 2, 3, 4};
    long[] shape = {4};

    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TInt32> op = Constant.create(scope, shape, IntBuffer.wrap(ints));
      try (Tensor<?> result = sess.runner().fetch(op).run().get(0)) {
        int[] actual = new int[ints.length];
        assertArrayEquals(ints, result.expect(TInt32.DTYPE).copyTo(actual));
      }
    }
  }

  @Test
  public void createTFloat() {
    float value = 1;
    
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TFloat32> op = Constant.create(scope, value);
      try (Tensor<?> result = sess.runner().fetch(op).run().get(0)) {
        assertEquals(value, result.expect(TFloat32.DTYPE).floatValue(), 0.0f);
      }
    }
  }

  @Test
  public void createTFloatBuffer() {
    float[] floats = {1, 2, 3, 4};
    long[] shape = {4};

    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TFloat32> op = Constant.create(scope, shape, FloatBuffer.wrap(floats));
      try (Tensor<?> result = sess.runner().fetch(op).run().get(0)) {
        float[] actual = new float[floats.length];
        assertArrayEquals(floats, result.expect(TFloat32.DTYPE).copyTo(actual), EPSILON);
      }
    }
  }

  @Test
  public void createTDouble() {
    double value = 1;
    
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TFloat64> op = Constant.create(scope, value);
      try (Tensor<?> result = sess.runner().fetch(op).run().get(0)) {
        assertEquals(value, result.expect(TFloat64.DTYPE).doubleValue(), 0.0);
      }
    }
  }

  @Test
  public void createTDoubleBuffer() {
    double[] doubles = {1, 2, 3, 4};
    long[] shape = {4};

    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TFloat64> op = Constant.create(scope, shape, DoubleBuffer.wrap(doubles));
      try (Tensor<?> result = sess.runner().fetch(op).run().get(0)) {
        double[] actual = new double[doubles.length];
        assertArrayEquals(doubles, result.expect(TFloat64.DTYPE).copyTo(actual), EPSILON);
      }
    }
  }

  @Test
  public void createLong() {
    long value = 1;
    
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TInt64> op = Constant.create(scope, value);
      try (Tensor<?> result = sess.runner().fetch(op).run().get(0)) {
        assertEquals(value, result.expect(TInt64.DTYPE).longValue());
      }
    }
  }

  @Test
  public void createLongBuffer() {
    long[] longs = {1, 2, 3, 4};
    long[] shape = {4};

    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TInt64> op = Constant.create(scope, shape, LongBuffer.wrap(longs));
      try (Tensor<?> result = sess.runner().fetch(op).run().get(0)) {
        long[] actual = new long[longs.length];
        assertArrayEquals(longs, result.expect(TInt64.DTYPE).copyTo(actual));
      }
    }
  }

  @Test
  public void createBoolean() {
    boolean value = true;
    
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TBool> op = Constant.create(scope, value);
      try (Tensor<?> result = sess.runner().fetch(op).run().get(0)) {
        assertEquals(value, result.expect(TBool.DTYPE).booleanValue());
      }
    }
  }

  @Test
  public void createStringBuffer() throws IOException {
    byte[] data = {(byte) 1, (byte) 2, (byte) 3, (byte) 4};
    long[] shape = {};

    // byte arrays (DataType.STRING in Tensorflow) are encoded as an offset in the data buffer,
    // followed by a varint encoded size, followed by the data.
    ByteArrayOutputStream baout = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(baout);
    // Offset in array.
    out.writeLong(0L);
    // Varint encoded length of buffer.
    // For any number < 0x80, the varint encoding is simply the number itself.
    // https://developers.google.com/protocol-buffers/docs/encoding#varints
    assertTrue(data.length < 0x80);
    out.write(data.length);
    out.write(data);
    out.close();
    byte[] content = baout.toByteArray();

    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TString> op = Constant.create(scope, TString.DTYPE, shape, ByteBuffer.wrap(content));
      try (Tensor<?> result = sess.runner().fetch(op).run().get(0)) {
        assertArrayEquals(data, result.expect(TString.DTYPE).bytesValue());
      }
    }
  }
}
