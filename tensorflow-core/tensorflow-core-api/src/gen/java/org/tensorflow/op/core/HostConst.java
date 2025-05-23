/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.Tensor;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TType;

/**
 * Returns a constant tensor on the host. Only for writing C++ tests.
 */
@OpMetadata(
    opType = HostConst.OP_NAME,
    inputsClass = HostConst.Inputs.class
)
@Operator
public final class HostConst<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "HostConst";

  private Output<T> output;

  public HostConst(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new HostConst operation.
   *
   * @param scope current scope
   * @param value Attr {@code value} is the tensor to return.
   * @param dtype The value of the dtype attribute
   * @param <T> data type for {@code HostConst} output and operands
   * @return a new instance of HostConst
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> HostConst<T> create(Scope scope, Tensor value, Class<T> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "HostConst");
    opBuilder.setAttr("value", value);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new HostConst<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
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
      outputsClass = HostConst.class
  )
  public static class Inputs extends RawOpInputs<HostConst<?>> {
    /**
     * Attr {@code value} is the tensor to return.
     */
    public final Tensor value;

    /**
     * The dtype attribute
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new HostConst<>(op), op, Arrays.asList("value", "dtype"));
      int inputIndex = 0;
      value = op.attributes().getAttrTensor("value");
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
