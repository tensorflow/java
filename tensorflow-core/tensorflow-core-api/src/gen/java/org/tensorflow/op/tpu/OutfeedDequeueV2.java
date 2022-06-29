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
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.tpu;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Retrieves a single tensor from the computation outfeed. Device ordinal is a
 * tensor allowing dynamic outfeed.
 * This operation will block indefinitely until data is available.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = OutfeedDequeueV2.OP_NAME,
    inputsClass = OutfeedDequeueV2.Inputs.class
)
public final class OutfeedDequeueV2<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "OutfeedDequeueV2";

  private Output<T> output;

  public OutfeedDequeueV2(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new OutfeedDequeueV2 operation.
   *
   * @param scope current scope
   * @param deviceOrdinal An int scalar tensor, representing the TPU device to use. This should be -1 when
   * the Op is running on a TPU device, and &gt;= 0 when the Op is running on the CPU
   * device.
   * @param dtype The type of elements in the tensor.
   * @param shape The shape of the tensor.
   * @param <T> data type for {@code OutfeedDequeueV2} output and operands
   * @return a new instance of OutfeedDequeueV2
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> OutfeedDequeueV2<T> create(Scope scope,
      Operand<TInt32> deviceOrdinal, Class<T> dtype, Shape shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "OutfeedDequeueV2");
    opBuilder.addInput(deviceOrdinal.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    opBuilder.setAttr("shape", shape);
    return new OutfeedDequeueV2<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A tensor that will be read from the device outfeed.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = OutfeedDequeueV2.class
  )
  public static class Inputs extends RawOpInputs<OutfeedDequeueV2<?>> {
    /**
     * An int scalar tensor, representing the TPU device to use. This should be -1 when
     * the Op is running on a TPU device, and &gt;= 0 when the Op is running on the CPU
     * device.
     */
    public final Operand<TInt32> deviceOrdinal;

    /**
     * The type of elements in the tensor.
     */
    public final DataType dtype;

    /**
     * The shape of the tensor.
     */
    public final Shape shape;

    public Inputs(GraphOperation op) {
      super(new OutfeedDequeueV2<>(op), op, Arrays.asList("dtype", "shape"));
      int inputIndex = 0;
      deviceOrdinal = (Operand<TInt32>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      shape = op.attributes().getAttrShape("shape");
    }
  }
}
