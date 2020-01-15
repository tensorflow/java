/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

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
import static org.junit.Assert.assertFalse;

import java.util.List;
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
import org.tensorflow.types.TUint8;

@RunWith(JUnit4.class)
public class ZerosTest {
  private static final float EPSILON = 1e-7f;
  
  @Test
  public void createIntZeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TInt32> op = Zeros.create(scope, Constant.create(scope, shape), TInt32.DTYPE);
      try (Tensor<?> result = sess.runner().fetch(op).run().get(0)) {
        int[][] actual = result.expect(TInt32.DTYPE).copyTo(new int[(int)shape[0]][(int)shape[1]]);
        for (int i = 0; i < actual.length; ++i) {
          for (int j = 0; j < actual[i].length; ++j) {
            assertEquals(0, actual[i][j]);
          }
        }
      }
    }
  }

  @Test
  public void createFloatZeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TFloat32> op = Zeros.create(scope, Constant.create(scope, shape), TFloat32.DTYPE);
      try (Tensor<?> result = sess.runner().fetch(op.asOutput()).run().get(0)) {
        float[][] actual = result.expect(TFloat32.DTYPE).copyTo(new float[(int)shape[0]][(int)shape[1]]);
        for (int i = 0; i < actual.length; ++i) {
          for (int j = 0; j < actual[i].length; ++j) {
            assertEquals(0.0f, actual[i][j], EPSILON);
          }
        }
      }
    }
  }

  @Test
  public void createDoubleZeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TFloat64> op = Zeros.create(scope, Constant.create(scope, shape), TFloat64.DTYPE);
      try (Tensor<?> result = sess.runner().fetch(op.asOutput()).run().get(0)) {
        double[][] actual = result.expect(TFloat64.DTYPE).copyTo(new double[(int)shape[0]][(int)shape[1]]);
        for (int i = 0; i < actual.length; ++i) {
          for (int j = 0; j < actual[i].length; ++j) {
            assertEquals(0.0, actual[i][j], EPSILON);
          }
        }
      }
    }
  }

  @Test
  public void createLongZeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TInt64> op = Zeros.create(scope, Constant.create(scope, shape), TInt64.DTYPE);
      try (Tensor<?> result = sess.runner().fetch(op.asOutput()).run().get(0)) {
        long[][] actual = result.expect(TInt64.DTYPE).copyTo(new long[(int)shape[0]][(int)shape[1]]);
        for (int i = 0; i < actual.length; ++i) {
          for (int j = 0; j < actual[i].length; ++j) {
            assertEquals(0L, actual[i][j]);
          }
        }
      }
    }
  }

  @Test
  public void createBooleanZeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TBool> op = Zeros.create(scope, Constant.create(scope, shape), TBool.DTYPE);
      try (Tensor<?> result = sess.runner().fetch(op.asOutput()).run().get(0)) {
        boolean[][] actual = result.expect(TBool.DTYPE).copyTo(new boolean[(int)shape[0]][(int)shape[1]]);
        for (int i = 0; i < actual.length; ++i) {
          for (int j = 0; j < actual[i].length; ++j) {
            assertFalse(actual[i][j]);
          }
        }
      }
    }
  }

  @Test
  public void createUint8Zeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TUint8> op = Zeros.create(scope, Constant.create(scope, shape), TUint8.DTYPE);
      try (Tensor<?> result = sess.runner().fetch(op.asOutput()).run().get(0)) {
        byte[][] actual = result.expect(TUint8.DTYPE).copyTo(new byte[(int)shape[0]][(int)shape[1]]);
        result.copyTo(actual);
        for (int i = 0; i < actual.length; ++i) {
          for (int j = 0; j < actual[i].length; ++j) {
            assertEquals(0, actual[i][j]);
          }
        }
      }
    }
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void cannotCreateStringZeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros.create(scope, Constant.create(scope, shape), TString.DTYPE);
    }
  }
  
  @Test
  public void operationsComposingZerosAreCorrectlyNamed() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TFloat32> zeros = Zeros.create(scope.withSubScope("test"), Constant.create(scope, shape), TFloat32.DTYPE);
      List<Tensor<?>> results = sess.runner().addTarget("test/Zeros/Zero").addTarget("test/Zeros/Fill").run();
    }
  }
}
