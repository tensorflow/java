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

package org.tensorflow.op.debugging;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Checks a tensor for NaN, -Inf and +Inf values.
 * When run, reports an {@code InvalidArgument} error if {@code tensor} has any values
 * that are not a number (NaN) or infinity (Inf). Otherwise, returns the input
 * tensor. Unlike CheckNumerics (V1), CheckNumericsV2 distinguishes -Inf and +Inf
 * in the errors it throws.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = CheckNumerics.OP_NAME,
    inputsClass = CheckNumerics.Inputs.class
)
public final class CheckNumerics<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CheckNumericsV2";

  private Output<T> output;

  public CheckNumerics(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CheckNumericsV2 operation.
   *
   * @param scope current scope
   * @param tensor The tensor value
   * @param message Prefix of the error message.
   * @param <T> data type for {@code CheckNumericsV2} output and operands
   * @return a new instance of CheckNumerics
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> CheckNumerics<T> create(Scope scope, Operand<T> tensor,
      String message) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CheckNumerics");
    opBuilder.addInput(tensor.asOutput());
    opBuilder.setAttr("message", message);
    return new CheckNumerics<>(opBuilder.build());
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
      outputsClass = CheckNumerics.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<CheckNumerics<T>> {
    /**
     * The tensor input
     */
    public final Operand<T> tensor;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Prefix of the error message.
     */
    public final String message;

    public Inputs(GraphOperation op) {
      super(new CheckNumerics<>(op), op, Arrays.asList("T", "message"));
      int inputIndex = 0;
      tensor = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      message = op.attributes().getAttrString("message");
    }
  }
}
