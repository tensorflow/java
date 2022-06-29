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

package org.tensorflow.op.core;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Scatter the data from the input value into specific TensorArray elements.
 * {@code indices} must be a vector, its length must match the first dim of {@code value}.
 */
@OpMetadata(
    opType = TensorArrayScatter.OP_NAME,
    inputsClass = TensorArrayScatter.Inputs.class
)
@Operator
public final class TensorArrayScatter extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorArrayScatterV3";

  private Output<TFloat32> flowOut;

  public TensorArrayScatter(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    flowOut = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorArrayScatterV3 operation.
   *
   * @param scope current scope
   * @param handle The handle to a TensorArray.
   * @param indices The locations at which to write the tensor elements.
   * @param value The concatenated tensor to write to the TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @return a new instance of TensorArrayScatter
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorArrayScatter create(Scope scope, Operand<? extends TType> handle,
      Operand<TInt32> indices, Operand<? extends TType> value, Operand<TFloat32> flowIn) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorArrayScatter");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(value.asOutput());
    opBuilder.addInput(flowIn.asOutput());
    return new TensorArrayScatter(opBuilder.build());
  }

  /**
   * Gets flowOut.
   * A float scalar that enforces proper chaining of operations.
   * @return flowOut.
   */
  public Output<TFloat32> flowOut() {
    return flowOut;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return flowOut;
  }

  @OpInputsMetadata(
      outputsClass = TensorArrayScatter.class
  )
  public static class Inputs extends RawOpInputs<TensorArrayScatter> {
    /**
     * The handle to a TensorArray.
     */
    public final Operand<? extends TType> handle;

    /**
     * The locations at which to write the tensor elements.
     */
    public final Operand<TInt32> indices;

    /**
     * The concatenated tensor to write to the TensorArray.
     */
    public final Operand<? extends TType> value;

    /**
     * A float scalar that enforces proper chaining of operations.
     */
    public final Operand<TFloat32> flowIn;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new TensorArrayScatter(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
      indices = (Operand<TInt32>) op.input(inputIndex++);
      value = (Operand<? extends TType>) op.input(inputIndex++);
      flowIn = (Operand<TFloat32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
