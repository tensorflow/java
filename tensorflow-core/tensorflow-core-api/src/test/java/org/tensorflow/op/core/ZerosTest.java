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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;

public class ZerosTest {

  @Test
  public void createIntZeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TInt32> op = Zeros.create(scope, Constant.vectorOf(scope, shape), TInt32.DTYPE);
      try (Tensor<TInt32> result = sess.runner().fetch(op).run().get(0).expect(TInt32.DTYPE)) {
        result.data().scalars().forEach(s -> assertEquals(0, s.getInt()));
      }
    }
  }

  @Test
  public void createFloatZeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TFloat32> op = Zeros.create(scope, Constant.vectorOf(scope, shape), TFloat32.DTYPE);
      try (Tensor<TFloat32> result = sess.runner().fetch(op.asOutput()).run().get(0).expect(TFloat32.DTYPE)) {
        result.data().scalars().forEach(s -> assertEquals(0.0f, s.getFloat(), 0));
      }
    }
  }

  @Test
  public void createDoubleZeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TFloat64> op = Zeros.create(scope, Constant.vectorOf(scope, shape), TFloat64.DTYPE);
      try (Tensor<TFloat64> result = sess.runner().fetch(op.asOutput()).run().get(0).expect(TFloat64.DTYPE)) {
        result.data().scalars().forEach(s -> assertEquals(0.0f, s.getDouble(), 0));
      }
    }
  }

  @Test
  public void createLongZeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TInt64> op = Zeros.create(scope, Constant.vectorOf(scope, shape), TInt64.DTYPE);
      try (Tensor<TInt64> result = sess.runner().fetch(op.asOutput()).run().get(0).expect(TInt64.DTYPE)) {
        result.data().scalars().forEach(s -> assertEquals(0L, s.getLong()));
      }
    }
  }

  @Test
  public void createBooleanZeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TBool> op = Zeros.create(scope, Constant.vectorOf(scope, shape), TBool.DTYPE);
      try (Tensor<TBool> result = sess.runner().fetch(op.asOutput()).run().get(0).expect(TBool.DTYPE)) {
        result.data().scalars().forEach(s -> assertFalse(s.getBoolean()));
      }
   }
  }

  @Test
  public void createUint8Zeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TUint8> op = Zeros.create(scope, Constant.vectorOf(scope, shape), TUint8.DTYPE);
      try (Tensor<TUint8> result = sess.runner().fetch(op.asOutput()).run().get(0).expect(TUint8.DTYPE)) {
        result.data().scalars().forEach(s -> assertEquals(0, s.getByte()));
      }
    }
  }

  @Test
  public void createStringZeros() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TString> op = Zeros.create(scope, Constant.vectorOf(scope, shape), TString.DTYPE);
      try (Tensor<TString> result = sess.runner().fetch(op.asOutput()).run().get(0).expect(TString.DTYPE)) {
        result.data().scalars().forEach(s -> assertTrue(s.getObject().isEmpty()));
      }
    }
  }

  @Test
  public void operationsComposingZerosAreCorrectlyNamed() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);
      long[] shape = {2, 2};
      Zeros<TFloat32> zeros = Zeros.create(scope.withSubScope("test"), Constant.vectorOf(scope, shape), TFloat32.DTYPE);
      List<Tensor<?>> results = sess.runner().addTarget("test/Zeros/Zero").addTarget("test/Zeros/Fill").run();
    }
  }
}
