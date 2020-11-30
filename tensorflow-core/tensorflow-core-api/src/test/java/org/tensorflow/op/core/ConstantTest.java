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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.tensorflow.AutoCloseableList;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.op.Scope;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

public class ConstantTest {
  private static final float EPSILON = 1e-7f;

  @Test
  public void createInts() {
    IntDataBuffer buffer = DataBuffers.of(1, 2, 3, 4);
    Shape shape = Shape.of(4);
    IntNdArray array = NdArrays.wrap(shape, buffer);

    try (Graph g = new Graph();
         Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TInt32> op1 = Constant.tensorOf(scope, shape, buffer);
      Constant<TInt32> op2 = Constant.tensorOf(scope, array);
      try (AutoCloseableList<Tensor<?>> t =
          new AutoCloseableList<>(sess.runner().fetch(op1).fetch(op2).run())) {
        assertEquals(array, t.get(0).expect(TInt32.DTYPE).data());
        assertEquals(array, t.get(1).expect(TInt32.DTYPE).data());
      }
    }
  }

  @Test
  public void createFloats() {
    FloatDataBuffer buffer = DataBuffers.of(1.0f, 2.0f, 3.0f, 4.0f);
    Shape shape = Shape.of(4);
    FloatNdArray array = NdArrays.wrap(shape, buffer);

    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TFloat32> op1 = Constant.tensorOf(scope, shape, buffer);
      Constant<TFloat32> op2 = Constant.tensorOf(scope, array);
      try (AutoCloseableList<Tensor<?>> t =
          new AutoCloseableList<>(sess.runner().fetch(op1).fetch(op2).run())) {
        assertEquals(array, t.get(0).expect(TFloat32.DTYPE).data());
        assertEquals(array, t.get(1).expect(TFloat32.DTYPE).data());
      }
    }
  }

  @Test
  public void createDoubles() {
    DoubleDataBuffer buffer = DataBuffers.of(1.0, 2.0, 3.0, 4.0);
    Shape shape = Shape.of(4);
    DoubleNdArray array = NdArrays.wrap(shape, buffer);

    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TFloat64> op1 = Constant.tensorOf(scope, shape, buffer);
      Constant<TFloat64> op2 = Constant.tensorOf(scope, array);
      try (AutoCloseableList<Tensor<?>> t =
          new AutoCloseableList<>(sess.runner().fetch(op1).fetch(op2).run())) {
        assertEquals(array, t.get(0).expect(TFloat64.DTYPE).data());
        assertEquals(array, t.get(1).expect(TFloat64.DTYPE).data());
      }
    }
  }

  @Test
  public void createLongs() {
    LongDataBuffer buffer = DataBuffers.of(1L, 2L, 3L, 4L);
    Shape shape = Shape.of(4);
    LongNdArray array = NdArrays.wrap(shape, buffer);

    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TInt64> op1 = Constant.tensorOf(scope, shape, buffer);
      Constant<TInt64> op2 = Constant.tensorOf(scope, array);
      try (AutoCloseableList<Tensor<?>> t =
          new AutoCloseableList<>(sess.runner().fetch(op1).fetch(op2).run())) {
        assertEquals(array, t.get(0).expect(TInt64.DTYPE).data());
        assertEquals(array, t.get(1).expect(TInt64.DTYPE).data());
      }
    }
  }

  @Test
  public void createStrings() throws IOException {
    DataBuffer<String> buffer = DataBuffers.ofObjects("1", "2", "3", "4");
    Shape shape = Shape.of(4);
    NdArray<String> array = NdArrays.wrap(shape, buffer);

    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      Constant<TString> op1 = Constant.tensorOf(scope, shape, buffer);
      Constant<TString> op2 = Constant.tensorOf(scope, array);
      try (AutoCloseableList<Tensor<?>> t =
          new AutoCloseableList<>(sess.runner().fetch(op1).fetch(op2).run())) {
        assertEquals(array, t.get(0).expect(TString.DTYPE).data());
        assertEquals(array, t.get(1).expect(TString.DTYPE).data());
      }
    }
  }
}
