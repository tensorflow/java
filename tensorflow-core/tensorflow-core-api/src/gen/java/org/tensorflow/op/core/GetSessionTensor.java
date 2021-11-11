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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Get the value of the tensor specified by its handle.
 *
 * @param <T> data type for {@code value} output
 */
@OpMetadata(
    opType = GetSessionTensor.OP_NAME,
    inputsClass = GetSessionTensor.Inputs.class
)
@Operator
public final class GetSessionTensor<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GetSessionTensor";

  private Output<T> value;

  public GetSessionTensor(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    value = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GetSessionTensor operation.
   *
   * @param scope current scope
   * @param handle The handle for a tensor stored in the session state.
   * @param dtype The type of the output value.
   * @param <T> data type for {@code GetSessionTensor} output and operands
   * @return a new instance of GetSessionTensor
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> GetSessionTensor<T> create(Scope scope, Operand<TString> handle,
      Class<T> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GetSessionTensor");
    opBuilder.addInput(handle.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new GetSessionTensor<>(opBuilder.build());
  }

  /**
   * Gets value.
   * The tensor for the given handle.
   * @return value.
   */
  public Output<T> value() {
    return value;
  }

  @Override
  public Output<T> asOutput() {
    return value;
  }

  @OpInputsMetadata(
      outputsClass = GetSessionTensor.class
  )
  public static class Inputs extends RawOpInputs<GetSessionTensor<?>> {
    /**
     * The handle for a tensor stored in the session state.
     */
    public final Operand<TString> handle;

    /**
     * The type of the output value.
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new GetSessionTensor<>(op), op, Arrays.asList("dtype"));
      int inputIndex = 0;
      handle = (Operand<TString>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
