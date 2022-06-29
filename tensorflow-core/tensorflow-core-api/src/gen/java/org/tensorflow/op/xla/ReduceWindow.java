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
import org.tensorflow.ConcreteFunction;
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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Wraps the XLA ReduceWindow operator, documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#reducewindow .
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = ReduceWindow.OP_NAME,
    inputsClass = ReduceWindow.Inputs.class
)
@Operator(
    group = "xla"
)
public final class ReduceWindow<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaReduceWindow";

  private Output<T> output;

  public ReduceWindow(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaReduceWindow operation.
   *
   * @param scope current scope
   * @param input the input tensor
   * @param initValue a scalar representing the initial value for the reduction
   * @param windowDimensions the shape of the window
   * @param windowStrides the inter-window strides
   * @param baseDilations The baseDilations value
   * @param windowDilations The windowDilations value
   * @param padding the padding to apply at the start and end of each input dimensions
   * @param computation a reducer function to apply
   * @param <T> data type for {@code XlaReduceWindow} output and operands
   * @param <U> data type for {@code XlaReduceWindow} output and operands
   * @return a new instance of ReduceWindow
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TNumber> ReduceWindow<T> create(Scope scope,
      Operand<T> input, Operand<T> initValue, Operand<U> windowDimensions, Operand<U> windowStrides,
      Operand<U> baseDilations, Operand<U> windowDilations, Operand<U> padding,
      ConcreteFunction computation) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReduceWindow");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(initValue.asOutput());
    opBuilder.addInput(windowDimensions.asOutput());
    opBuilder.addInput(windowStrides.asOutput());
    opBuilder.addInput(baseDilations.asOutput());
    opBuilder.addInput(windowDilations.asOutput());
    opBuilder.addInput(padding.asOutput());
    opBuilder.setAttr("computation", computation);
    return new ReduceWindow<>(opBuilder.build());
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
      outputsClass = ReduceWindow.class
  )
  public static class Inputs<T extends TType, U extends TNumber> extends RawOpInputs<ReduceWindow<T>> {
    /**
     * the input tensor
     */
    public final Operand<T> input;

    /**
     * a scalar representing the initial value for the reduction
     */
    public final Operand<T> initValue;

    /**
     * the shape of the window
     */
    public final Operand<U> windowDimensions;

    /**
     * the inter-window strides
     */
    public final Operand<U> windowStrides;

    /**
     * The baseDilations input
     */
    public final Operand<U> baseDilations;

    /**
     * The windowDilations input
     */
    public final Operand<U> windowDilations;

    /**
     * the padding to apply at the start and end of each input dimensions
     */
    public final Operand<U> padding;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    public Inputs(GraphOperation op) {
      super(new ReduceWindow<>(op), op, Arrays.asList("T", "Tindices"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      initValue = (Operand<T>) op.input(inputIndex++);
      windowDimensions = (Operand<U>) op.input(inputIndex++);
      windowStrides = (Operand<U>) op.input(inputIndex++);
      baseDilations = (Operand<U>) op.input(inputIndex++);
      windowDilations = (Operand<U>) op.input(inputIndex++);
      padding = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
