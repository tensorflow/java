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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.tensorflow.exceptions.TFFailedPreconditionException;
import org.tensorflow.exceptions.TFInvalidArgumentException;
import org.tensorflow.op.Ops;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * Unit tests for {@link EagerOperation} class.
 */
public class EagerOperationTest {

  @Test
  public void failToCreateIfSessionIsClosed() {
    EagerSession session = EagerSession.create();
    session.close();
    try {
      new EagerOperation(session, null, null, "Add", "add");
      fail();
    } catch (IllegalStateException e) {
      // expected
    }
  }

  @Test
  public void outputDataTypeAndShape() {
    try (EagerSession session = EagerSession.create();
         TInt32 t = TInt32.tensorOf(Shape.of(2, 3))) {
      EagerOperation op =
          opBuilder(session, "Const", "OutputAttrs")
              .setAttr("dtype", t.dataType())
              .setAttr("value", t)
              .build();
      assertEquals(DataType.DT_INT32, op.dtype(0));
      assertEquals(2, op.shape(0).size(0));
      assertEquals(3, op.shape(0).size(1));
    }
  }

  @Test
  public void outputTensor() {
    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);
      EagerOperation add =
          opBuilder(session, "Add", "CompareResult")
              .addInput(tf.constant(2).asOutput())
              .addInput(tf.constant(4).asOutput())
              .build();
      assertEquals(6, ((TInt32)add.tensor(0)).getInt());

      // Validate that we retrieve the right shape and datatype from the tensor
      // that has been resolved
      assertEquals(0, add.shape(0).numDimensions());
      assertEquals(DataType.DT_INT32, add.dtype(0));
    }
  }

  @Test
  public void inputAndOutputListLengths() {
    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);
      Output<TFloat32> c1 = tf.constant(new float[]{1f, 2f}).asOutput();
      Output<TFloat32> c2 = tf.constant(new float[]{3f, 4f}).asOutput();

      EagerOperation acc =
          opBuilder(session, "AddN", "InputListLength")
              .addInputList(new Output<?>[]{c1, c2})
              .build();
      assertEquals(2, acc.inputListLength("inputs"));
      assertEquals(1, acc.outputListLength("sum"));

      EagerOperation split =
          opBuilder(session, "Split", "OutputListLength")
              .addInput(tf.constant(0).asOutput())
              .addInput(c1)
              .setAttr("num_split", 2)
              .build();
      assertEquals(1, split.inputListLength("split_dim"));
      assertEquals(2, split.outputListLength("output"));

      try {
        split.inputListLength("no_such_input");
        fail();
      } catch (TFInvalidArgumentException e) {
        // expected
      }

      try {
        split.outputListLength("no_such_output");
        fail();
      } catch (TFInvalidArgumentException e) {
        // expected
      }
    }
  }

  @Test
  public void numOutputs() {
    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);
      EagerOperation op =
          opBuilder(session, "UniqueWithCountsV2", "unq")
              .addInput(tf.constant(new int[]{1, 2, 1}).asOutput())
              .addInput(tf.constant(new int[]{0}).asOutput())
              .setAttr("out_idx", DataType.DT_INT32)
              .build();
      assertEquals(3, op.numOutputs());
    }
  }

  @Test
  public void opNotAccessibleIfSessionIsClosed() {
    EagerSession session = EagerSession.create();
    Ops tf = Ops.create(session);
    EagerOperation add =
        opBuilder(session, "Add", "SessionClosed")
            .addInput(tf.constant(2).asOutput())
            .addInput(tf.constant(4).asOutput())
            .build();
    assertEquals(1, add.outputListLength("z"));
    session.close();
    try {
      add.outputListLength("z");
      fail();
    } catch (IllegalStateException e) {
      // expected
    }
  }

  @Test
  public void outputIndexOutOfBounds() {
    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);
      EagerOperation add =
          opBuilder(session, "Add", "OutOfRange")
              .addInput(tf.constant(2).asOutput())
              .addInput(tf.constant(4).asOutput())
              .build();
      try {
        add.getUnsafeNativeHandle(1);
        fail();
      } catch (IndexOutOfBoundsException e) {
        // expected
      }
      try {
        add.shape(1);
        fail();
      } catch (IndexOutOfBoundsException e) {
        // expected
      }
      try {
        add.dtype(1);
        fail();
      } catch (IndexOutOfBoundsException e) {
        // expected
      }
      try {
        add.tensor(1);
        fail();
      } catch (IndexOutOfBoundsException e) {
        // expected
      }
    }
  }

  private static EagerOperationBuilder opBuilder(EagerSession session, String type, String name) {
    return new EagerOperationBuilder(session, type, name);
  }
}
