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
 * Wraps the XLA SelectAndScatter operator, documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#selectandscatter
 * .
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = SelectAndScatter.OP_NAME,
    inputsClass = SelectAndScatter.Inputs.class
)
@Operator(
    group = "xla"
)
public final class SelectAndScatter<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSelectAndScatter";

  private Output<T> output;

  public SelectAndScatter(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSelectAndScatter operation.
   *
   * @param scope current scope
   * @param operand the input tensor
   * @param windowDimensions the shape of the window
   * @param windowStrides the inter-window strides
   * @param padding the padding to apply at the start and end of each input dimensions
   * @param source a tensor of values to scatter
   * @param initValue a scalar representing the initial value for the output tensor
   * @param select a selection function to apply
   * @param scatter a scatter function to apply
   * @param <T> data type for {@code XlaSelectAndScatter} output and operands
   * @param <U> data type for {@code XlaSelectAndScatter} output and operands
   * @return a new instance of SelectAndScatter
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TNumber> SelectAndScatter<T> create(Scope scope,
      Operand<T> operand, Operand<U> windowDimensions, Operand<U> windowStrides, Operand<U> padding,
      Operand<T> source, Operand<T> initValue, ConcreteFunction select, ConcreteFunction scatter) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SelectAndScatter");
    opBuilder.addInput(operand.asOutput());
    opBuilder.addInput(windowDimensions.asOutput());
    opBuilder.addInput(windowStrides.asOutput());
    opBuilder.addInput(padding.asOutput());
    opBuilder.addInput(source.asOutput());
    opBuilder.addInput(initValue.asOutput());
    opBuilder.setAttr("select", select);
    opBuilder.setAttr("scatter", scatter);
    return new SelectAndScatter<>(opBuilder.build());
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
      outputsClass = SelectAndScatter.class
  )
  public static class Inputs<T extends TType, U extends TNumber> extends RawOpInputs<SelectAndScatter<T>> {
    /**
     * the input tensor
     */
    public final Operand<T> operand;

    /**
     * the shape of the window
     */
    public final Operand<U> windowDimensions;

    /**
     * the inter-window strides
     */
    public final Operand<U> windowStrides;

    /**
     * the padding to apply at the start and end of each input dimensions
     */
    public final Operand<U> padding;

    /**
     * a tensor of values to scatter
     */
    public final Operand<T> source;

    /**
     * a scalar representing the initial value for the output tensor
     */
    public final Operand<T> initValue;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    public Inputs(GraphOperation op) {
      super(new SelectAndScatter<>(op), op, Arrays.asList("T", "Tindices"));
      int inputIndex = 0;
      operand = (Operand<T>) op.input(inputIndex++);
      windowDimensions = (Operand<U>) op.input(inputIndex++);
      windowStrides = (Operand<U>) op.input(inputIndex++);
      padding = (Operand<U>) op.input(inputIndex++);
      source = (Operand<T>) op.input(inputIndex++);
      initValue = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
