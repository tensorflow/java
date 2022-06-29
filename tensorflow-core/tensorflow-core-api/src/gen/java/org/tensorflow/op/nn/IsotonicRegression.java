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

package org.tensorflow.op.nn;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Solves a batch of isotonic regression problems.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = IsotonicRegression.OP_NAME,
    inputsClass = IsotonicRegression.Inputs.class
)
public final class IsotonicRegression<U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IsotonicRegression";

  private Output<U> output;

  private Output<TInt32> segments;

  public IsotonicRegression(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    segments = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IsotonicRegression operation.
   *
   * @param scope current scope
   * @param input A (batch_size, dim)-tensor holding a batch of inputs.
   * @param outputDtype Dtype of output.
   * @param <U> data type for {@code IsotonicRegression} output and operands
   * @return a new instance of IsotonicRegression
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> IsotonicRegression<U> create(Scope scope,
      Operand<? extends TNumber> input, Class<U> outputDtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IsotonicRegression");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("output_dtype", Operands.toDataType(outputDtype));
    return new IsotonicRegression<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new IsotonicRegression operation, with the default output types.
   *
   * @param scope current scope
   * @param input A (batch_size, dim)-tensor holding a batch of inputs.
   * @return a new instance of IsotonicRegression, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static IsotonicRegression<TFloat32> create(Scope scope, Operand<? extends TNumber> input) {
    return create(scope, input, TFloat32.class);
  }

  /**
   * Gets output.
   * A (batch_size, dim)-tensor holding the per-batch element solutions.
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  /**
   * Gets segments.
   * An int32 (batch_size, dim)-tensor with the segments.
   * @return segments.
   */
  public Output<TInt32> segments() {
    return segments;
  }

  @OpInputsMetadata(
      outputsClass = IsotonicRegression.class
  )
  public static class Inputs extends RawOpInputs<IsotonicRegression<?>> {
    /**
     * A (batch_size, dim)-tensor holding a batch of inputs.
     */
    public final Operand<? extends TNumber> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Dtype of output.
     */
    public final DataType outputDtype;

    public Inputs(GraphOperation op) {
      super(new IsotonicRegression<>(op), op, Arrays.asList("T", "output_dtype"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      outputDtype = op.attributes().getAttrType("output_dtype");
    }
  }
}
