/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.quantization;

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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Concatenates quantized tensors along one dimension.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = QuantizedConcat.OP_NAME,
    inputsClass = QuantizedConcat.Inputs.class
)
@Operator(
    group = "quantization"
)
public final class QuantizedConcat<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedConcat";

  private Output<T> output;

  private Output<TFloat32> outputMin;

  private Output<TFloat32> outputMax;

  public QuantizedConcat(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    outputMin = operation.output(outputIdx++);
    outputMax = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedConcat operation.
   *
   * @param scope current scope
   * @param concatDim 0-D.  The dimension along which to concatenate.  Must be in the
   * range [0, rank(values)).
   * @param values The {@code N} Tensors to concatenate. Their ranks and types must match,
   * and their sizes must match in all dimensions except {@code concat_dim}.
   * @param inputMins The minimum scalar values for each of the input tensors.
   * @param inputMaxes The maximum scalar values for each of the input tensors.
   * @param <T> data type for {@code QuantizedConcat} output and operands
   * @return a new instance of QuantizedConcat
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> QuantizedConcat<T> create(Scope scope, Operand<TInt32> concatDim,
      Iterable<Operand<T>> values, Iterable<Operand<TFloat32>> inputMins,
      Iterable<Operand<TFloat32>> inputMaxes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizedConcat");
    opBuilder.addInput(concatDim.asOutput());
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder.addInputList(Operands.asOutputs(inputMins));
    opBuilder.addInputList(Operands.asOutputs(inputMaxes));
    return new QuantizedConcat<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A {@code Tensor} with the concatenation of values stacked along the
   * {@code concat_dim} dimension.  This tensor's shape matches that of {@code values} except
   * in {@code concat_dim} where it has the sum of the sizes.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  /**
   * Gets outputMin.
   * The float value that the minimum quantized output value represents.
   * @return outputMin.
   */
  public Output<TFloat32> outputMin() {
    return outputMin;
  }

  /**
   * Gets outputMax.
   * The float value that the maximum quantized output value represents.
   * @return outputMax.
   */
  public Output<TFloat32> outputMax() {
    return outputMax;
  }

  @OpInputsMetadata(
      outputsClass = QuantizedConcat.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<QuantizedConcat<T>> {
    /**
     * 0-D.  The dimension along which to concatenate.  Must be in the
     * range [0, rank(values)).
     */
    public final Operand<TInt32> concatDim;

    /**
     * The {@code N} Tensors to concatenate. Their ranks and types must match,
     * and their sizes must match in all dimensions except {@code concat_dim}.
     */
    public final Iterable<Operand<T>> values;

    /**
     * The minimum scalar values for each of the input tensors.
     */
    public final Iterable<Operand<TFloat32>> inputMins;

    /**
     * The maximum scalar values for each of the input tensors.
     */
    public final Iterable<Operand<TFloat32>> inputMaxes;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new QuantizedConcat<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      concatDim = (Operand<TInt32>) op.input(inputIndex++);
      int valuesLength = op.inputListLength("values");
      values = Arrays.asList((Operand<T>[]) op.inputList(inputIndex, valuesLength));
      inputIndex += valuesLength;
      int inputMinsLength = op.inputListLength("input_mins");
      inputMins = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, inputMinsLength));
      inputIndex += inputMinsLength;
      int inputMaxesLength = op.inputListLength("input_maxes");
      inputMaxes = Arrays.asList((Operand<TFloat32>[]) op.inputList(inputIndex, inputMaxesLength));
      inputIndex += inputMaxesLength;
      T = op.attributes().getAttrType("T");
    }
  }
}
