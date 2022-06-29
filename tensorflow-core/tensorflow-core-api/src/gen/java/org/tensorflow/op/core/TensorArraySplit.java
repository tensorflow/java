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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Split the data from the input value into TensorArray elements.
 * Assuming that {@code lengths} takes on values
 * <p>{@code (n0, n1, ..., n(T-1))}
 * <p>and that {@code value} has shape
 * <p>{@code (n0 + n1 + ... + n(T-1) x d0 x d1 x ...)},
 * <p>this splits values into a TensorArray with T tensors.
 * <p>TensorArray index t will be the subtensor of values with starting position
 * <p>{@code (n0 + n1 + ... + n(t-1), 0, 0, ...)}
 * <p>and having size
 * <p>{@code nt x d0 x d1 x ...}
 */
@OpMetadata(
    opType = TensorArraySplit.OP_NAME,
    inputsClass = TensorArraySplit.Inputs.class
)
@Operator
public final class TensorArraySplit extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorArraySplitV3";

  private Output<TFloat32> flowOut;

  public TensorArraySplit(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    flowOut = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorArraySplitV3 operation.
   *
   * @param scope current scope
   * @param handle The handle to a TensorArray.
   * @param value The concatenated tensor to write to the TensorArray.
   * @param lengths The vector of lengths, how to split the rows of value into the
   * TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @return a new instance of TensorArraySplit
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorArraySplit create(Scope scope, Operand<? extends TType> handle,
      Operand<? extends TType> value, Operand<TInt64> lengths, Operand<TFloat32> flowIn) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorArraySplit");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(value.asOutput());
    opBuilder.addInput(lengths.asOutput());
    opBuilder.addInput(flowIn.asOutput());
    return new TensorArraySplit(opBuilder.build());
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
      outputsClass = TensorArraySplit.class
  )
  public static class Inputs extends RawOpInputs<TensorArraySplit> {
    /**
     * The handle to a TensorArray.
     */
    public final Operand<? extends TType> handle;

    /**
     * The concatenated tensor to write to the TensorArray.
     */
    public final Operand<? extends TType> value;

    /**
     * The vector of lengths, how to split the rows of value into the
     * TensorArray.
     */
    public final Operand<TInt64> lengths;

    /**
     * A float scalar that enforces proper chaining of operations.
     */
    public final Operand<TFloat32> flowIn;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new TensorArraySplit(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
      value = (Operand<? extends TType>) op.input(inputIndex++);
      lengths = (Operand<TInt64>) op.input(inputIndex++);
      flowIn = (Operand<TFloat32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
