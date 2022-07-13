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

package org.tensorflow.op.xla;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Wraps the XLA CustomCall operator
 * documented at https://www.tensorflow.org/xla/operation_semantics#customcall.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = CustomCall.OP_NAME,
    inputsClass = CustomCall.Inputs.class
)
@Operator(
    group = "xla"
)
public final class CustomCall<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaCustomCall";

  private Output<T> output;

  public CustomCall(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaCustomCall operation.
   *
   * @param scope current scope
   * @param args A list of {@code Tensor} with possibly different types.
   * @param targetName Name of the function. A call instruction will be emitted which
   * targets this symbol name.
   * @param backendConfig String, used to encode serialized metadata to the backend.
   * @param dtype Output tensor data type.
   * @param shape Output tensor shape.
   * @param <T> data type for {@code XlaCustomCall} output and operands
   * @return a new instance of CustomCall
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> CustomCall<T> create(Scope scope, Iterable<Operand<?>> args,
      String targetName, String backendConfig, Class<T> dtype, Shape shape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CustomCall");
    opBuilder.addInputList(Operands.asOutputs(args));
    opBuilder.setAttr("target_name", targetName);
    opBuilder.setAttr("backend_config", backendConfig);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    opBuilder.setAttr("shape", shape);
    return new CustomCall<>(opBuilder.build());
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
      outputsClass = CustomCall.class
  )
  public static class Inputs extends RawOpInputs<CustomCall<?>> {
    /**
     * A list of {@code Tensor} with possibly different types.
     */
    public final Iterable<Operand<?>> args;

    /**
     * Name of the function. A call instruction will be emitted which
     * targets this symbol name.
     */
    public final String targetName;

    /**
     * String, used to encode serialized metadata to the backend.
     */
    public final String backendConfig;

    /**
     * The T attribute
     */
    public final DataType[] T;

    /**
     * Output tensor data type.
     */
    public final DataType dtype;

    /**
     * Output tensor shape.
     */
    public final Shape shape;

    public Inputs(GraphOperation op) {
      super(new CustomCall<>(op), op, Arrays.asList("target_name", "backend_config", "T", "dtype", "shape"));
      int inputIndex = 0;
      int argsLength = op.inputListLength("args");
      args = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, argsLength));
      inputIndex += argsLength;
      targetName = op.attributes().getAttrString("target_name");
      backendConfig = op.attributes().getAttrString("backend_config");
      T = op.attributes().getAttrTypeList("T");
      dtype = op.attributes().getAttrType("dtype");
      shape = op.attributes().getAttrShape("shape");
    }
  }
}
