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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Reshapes a quantized tensor as per the Reshape op.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = QuantizedReshape.OP_NAME,
    inputsClass = QuantizedReshape.Inputs.class
)
@Operator
public final class QuantizedReshape<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedReshape";

  private Output<T> output;

  private Output<TFloat32> outputMin;

  private Output<TFloat32> outputMax;

  public QuantizedReshape(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    outputMin = operation.output(outputIdx++);
    outputMax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedReshape operation.
   *
   * @param scope current scope
   * @param tensor The tensor value
   * @param shape Defines the shape of the output tensor.
   * @param inputMin The minimum value of the input.
   * @param inputMax The maximum value of the input.
   * @param <T> data type for {@code QuantizedReshape} output and operands
   * @return a new instance of QuantizedReshape
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> QuantizedReshape<T> create(Scope scope, Operand<T> tensor,
      Operand<? extends TNumber> shape, Operand<TFloat32> inputMin, Operand<TFloat32> inputMax) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizedReshape");
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(inputMin.asOutput());
    opBuilder.addInput(inputMax.asOutput());
    return new QuantizedReshape<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  /**
   * Gets outputMin.
   * This value is copied from input_min.
   * @return outputMin.
   */
  public Output<TFloat32> outputMin() {
    return outputMin;
  }

  /**
   * Gets outputMax.
   * This value is copied from input_max.
   * @return outputMax.
   */
  public Output<TFloat32> outputMax() {
    return outputMax;
  }

  @OpInputsMetadata(
      outputsClass = QuantizedReshape.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<QuantizedReshape<T>> {
    /**
     * The tensor input
     */
    public final Operand<T> tensor;

    /**
     * Defines the shape of the output tensor.
     */
    public final Operand<? extends TNumber> shape;

    /**
     * The minimum value of the input.
     */
    public final Operand<TFloat32> inputMin;

    /**
     * The maximum value of the input.
     */
    public final Operand<TFloat32> inputMax;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tshape attribute
     */
    public final DataType Tshape;

    public Inputs(GraphOperation op) {
      super(new QuantizedReshape<>(op), op, Arrays.asList("T", "Tshape"));
      int inputIndex = 0;
      tensor = (Operand<T>) op.input(inputIndex++);
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      inputMin = (Operand<TFloat32>) op.input(inputIndex++);
      inputMax = (Operand<TFloat32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tshape = op.attributes().getAttrType("Tshape");
    }
  }
}
