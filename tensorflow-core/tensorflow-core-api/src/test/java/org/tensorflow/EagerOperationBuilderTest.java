/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.tensorflow.op.Ops;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/** Unit tests for {@link EagerOperationBuilder} class. */
@RunWith(JUnit4.class)
public class EagerOperationBuilderTest {

  @Test
  public void failToCreateIfSessionIsClosed() {
    EagerSession session = EagerSession.create();
    session.close();
    try {
      new EagerOperationBuilder(session, "Add", "add");
      fail();
    } catch (IllegalStateException e) {
      // expected
    }
  }

  @Test
  public void failToBuildOpIfSessionIsClosed() {
    EagerOperationBuilder opBuilder;
    try (EagerSession session = EagerSession.create()) {
      opBuilder = new EagerOperationBuilder(session, "Empty", "empty");
    }
    try {
      opBuilder.setAttr("dtype", TFloat32.DTYPE);
      fail();
    } catch (IllegalStateException e) {
      // expected
    }
  }

  @Test
  public void addInputs() {
    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);
      Operation asrt =
          opBuilder(session, "Assert", "assert")
              .addInput(tf.val(true).asOutput())
              .addInputList(new Output<?>[] {tf.val(-1).asOutput()})
              .build();
      try {
        opBuilder(session, "Const", "var").addControlInput(asrt);
        fail();
      } catch (UnsupportedOperationException e) {
        // expected
      }
    }
  }

  @Test
  public void setDevice() {
    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);
      opBuilder(session, "Add", "SetDevice")
          .setDevice("/job:localhost/replica:0/task:0/device:CPU:0")
          .addInput(tf.val(2).asOutput())
          .addInput(tf.val(4).asOutput())
          .build();
    }
  }

  @Test
  public void setAttrs() {
    // The effect of setting an attribute may not easily be visible from the other parts of this
    // package's API. Thus, for now, the test simply executes the various setAttr variants to see
    // that there are no exceptions.
    //
    // This is a bit of an awkward test since it has to find operations with attributes of specific
    // types that aren't inferred from the input arguments.
    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);
      // dtype, tensor attributes.
      try (Tensor<TInt32> t = TInt32.scalarOf(1)) {
        opBuilder(session, "Const", "DataTypeAndTensor")
            .setAttr("dtype", TInt32.DTYPE)
            .setAttr("value", t)
            .build();
      }
      // type, int (TF "int" attributes are 64-bit signed, so a Java long).
      opBuilder(session, "RandomUniform", "DataTypeAndInt")
          .addInput(tf.array(1).asOutput())
          .setAttr("seed", 10)
          .setAttr("dtype", TFloat32.DTYPE)
          .build();
      // list(int), string
      opBuilder(session, "MaxPool", "IntListAndString")
          .addInput(tf.val(new float[2][2][2][2]).asOutput())
          .setAttr("ksize", new long[] {1, 1, 1, 1})
          .setAttr("strides", new long[] {1, 1, 1, 1})
          .setAttr("padding", "SAME")
          .build();
      // list(float), device
      opBuilder(session, "FractionalMaxPool", "FloatList")
          .addInput(tf.val(new float[2][2][2][2]).asOutput())
          .setAttr("pooling_ratio", new float[] {1.0f, 1.44f, 1.73f, 1.0f})
          .build();
      // shape
      opBuilder(session, "EnsureShape", "ShapeAttr")
          .addInput(tf.val(new int[2][2]).asOutput())
          .setAttr("shape", Shape.of(2, 2))
          .build();
      // list(shape)
      opBuilder(session, "FIFOQueue", "queue")
          .setAttr("component_types", new DataType[] {TInt32.DTYPE, TInt32.DTYPE})
          .setAttr("shapes", new Shape[] {Shape.of(2, 2), Shape.of(2, 2, 2)})
          .build();
      // bool
      opBuilder(session, "All", "Bool")
          .addInput(tf.val(new boolean[] {true, true, false}).asOutput())
          .addInput(tf.val(0).asOutput())
          .setAttr("keep_dims", false)
          .build();
      // float
      opBuilder(session, "ApproximateEqual", "Float")
          .addInput(tf.val(10.00001f).asOutput())
          .addInput(tf.val(10.00000f).asOutput())
          .setAttr("tolerance", 0.1f)
          .build();
      // Missing tests: list(string), list(byte), list(bool), list(type)
    }
  }

  private static EagerOperationBuilder opBuilder(EagerSession session, String type, String name) {
    return new EagerOperationBuilder(session, type, name);
  }
}
