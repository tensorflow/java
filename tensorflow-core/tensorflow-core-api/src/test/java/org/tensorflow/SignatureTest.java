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
==============================================================================*/
package org.tensorflow;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.tensorflow.Signature.TensorDescription;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt32;

public class SignatureTest {

  @Test
  public void cannotUseEmptyKey() {
    Signature.Builder builder = Signature.builder();
    assertThrows(IllegalArgumentException.class, () -> builder.key(null));
    assertThrows(IllegalArgumentException.class, () -> builder.key(""));
    builder.key("valid key");
  }

  @Test
  public void cannotDuplicateInputOutputNames() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Signature.Builder builder =
          Signature.builder()
              .input("x", tf.constant(10.0f))
              .output("x", tf.constant(10.0f)) // can add an output with the same name as an input
              .output("y", tf.constant(20.0f));
      assertThrows(IllegalArgumentException.class, () -> builder.input("x", tf.constant(10)));
      assertThrows(IllegalArgumentException.class, () -> builder.output("y", tf.constant(20.0f)));
    }
  }

  @Test
  public void getInputsAndOutputs() {
    Ops tf = Ops.create();
    Signature builder =
        Signature.builder()
            .input("x", tf.constant(10.0f))
            .output("y", tf.constant(new float[][] {{10.0f, 30.0f}}))
            .output("z", tf.constant(20.0f))
            .build();

    Map<String, TensorDescription> inputs = builder.getInputs();
    assertEquals(inputs.size(), 1);

    Map<String, TensorDescription> outputs = builder.getOutputs();
    assertEquals(outputs.size(), 2);

    assertEquals(outputs.get("y").dataType, DataType.DT_FLOAT);
    assertEquals(outputs.get("z").dataType, DataType.DT_FLOAT);
    assertArrayEquals(outputs.get("y").shape.asArray(), new long[] {1, 2});
    assertArrayEquals(outputs.get("z").shape.asArray(), new long[] {});

    Signature emptySignature = Signature.builder().build();
    assertEquals(emptySignature.getInputs().size(), 0);
  }

  @Test
  public void emptyMethodNameConvertedToNull() {
    Signature signature = Signature.builder().key("f").build();
    assertNull(signature.methodName());
    signature = Signature.builder().key("f").methodName("").build();
    assertNull(signature.methodName());
    signature = Signature.builder().key("f").methodName(null).build();
    assertNull(signature.methodName());
  }

  @Test
  public void createTensorInfoFromOperandWithUnknownShape() {
    try (Graph g = new Graph()) {
      var tf = Ops.create(g);
      var placeholder = tf.placeholder(TInt32.class);
      var tensorInfo = Signature.Builder.toTensorInfo(placeholder.asOutput());
      assertTrue(tensorInfo.getTensorShape().getUnknownRank());
      assertEquals(0, tensorInfo.getTensorShape().getDimCount());
    }
  }

  @Test
  public void createTensorInfoFromOperandWithPartiallyUnknownShape() {
    try (Graph g = new Graph()) {
      var tf = Ops.create(g);
      var shape = Shape.of(Shape.UNKNOWN_SIZE, 10);
      var placeholder = tf.placeholder(TInt32.class, Placeholder.shape(shape));
      var tensorInfo = Signature.Builder.toTensorInfo(placeholder.asOutput());
      assertFalse(tensorInfo.getTensorShape().getUnknownRank());
      assertEquals(2, tensorInfo.getTensorShape().getDimCount());
      assertEquals(-1, tensorInfo.getTensorShape().getDim(0).getSize());
      assertEquals(10, tensorInfo.getTensorShape().getDim(1).getSize());
    }
  }
}
