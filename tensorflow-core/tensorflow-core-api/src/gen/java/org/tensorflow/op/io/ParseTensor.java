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

package org.tensorflow.op.io;

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
 * Transforms a serialized tensorflow.TensorProto proto into a Tensor.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = ParseTensor.OP_NAME,
    inputsClass = ParseTensor.Inputs.class
)
@Operator(
    group = "io"
)
public final class ParseTensor<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ParseTensor";

  private Output<T> output;

  public ParseTensor(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ParseTensor operation.
   *
   * @param scope current scope
   * @param serialized A scalar string containing a serialized TensorProto proto.
   * @param outType The type of the serialized tensor.  The provided type must match the
   * type of the serialized tensor and no implicit conversion will take place.
   * @param <T> data type for {@code ParseTensor} output and operands
   * @return a new instance of ParseTensor
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ParseTensor<T> create(Scope scope, Operand<TString> serialized,
      Class<T> outType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ParseTensor");
    opBuilder.addInput(serialized.asOutput());
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new ParseTensor<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A Tensor of type {@code out_type}.
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
      outputsClass = ParseTensor.class
  )
  public static class Inputs extends RawOpInputs<ParseTensor<?>> {
    /**
     * A scalar string containing a serialized TensorProto proto.
     */
    public final Operand<TString> serialized;

    /**
     * The type of the serialized tensor.  The provided type must match the
     * type of the serialized tensor and no implicit conversion will take place.
     */
    public final DataType outType;

    public Inputs(GraphOperation op) {
      super(new ParseTensor<>(op), op, Arrays.asList("out_type"));
      int inputIndex = 0;
      serialized = (Operand<TString>) op.input(inputIndex++);
      outType = op.attributes().getAttrType("out_type");
    }
  }
}
