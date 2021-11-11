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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes Quantized Rectified Linear X: {@code min(max(features, 0), max_value)}
 *
 * @param <U> data type for {@code activations} output
 */
@OpMetadata(
    opType = QuantizedReluX.OP_NAME,
    inputsClass = QuantizedReluX.Inputs.class
)
@Operator(
    group = "nn"
)
public final class QuantizedReluX<U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedReluX";

  private Output<U> activations;

  private Output<TFloat32> minActivations;

  private Output<TFloat32> maxActivations;

  public QuantizedReluX(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    activations = operation.output(outputIdx++);
    minActivations = operation.output(outputIdx++);
    maxActivations = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedReluX operation.
   *
   * @param scope current scope
   * @param features The features value
   * @param maxValue The maxValue value
   * @param minFeatures The float value that the lowest quantized value represents.
   * @param maxFeatures The float value that the highest quantized value represents.
   * @param outType The value of the outType attribute
   * @param <U> data type for {@code QuantizedReluX} output and operands
   * @return a new instance of QuantizedReluX
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> QuantizedReluX<U> create(Scope scope,
      Operand<? extends TNumber> features, Operand<TFloat32> maxValue,
      Operand<TFloat32> minFeatures, Operand<TFloat32> maxFeatures, Class<U> outType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizedReluX");
    opBuilder.addInput(features.asOutput());
    opBuilder.addInput(maxValue.asOutput());
    opBuilder.addInput(minFeatures.asOutput());
    opBuilder.addInput(maxFeatures.asOutput());
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new QuantizedReluX<>(opBuilder.build());
  }

  /**
   * Gets activations.
   * Has the same output shape as &quot;features&quot;.
   * @return activations.
   */
  public Output<U> activations() {
    return activations;
  }

  /**
   * Gets minActivations.
   * The float value that the lowest quantized value represents.
   * @return minActivations.
   */
  public Output<TFloat32> minActivations() {
    return minActivations;
  }

  /**
   * Gets maxActivations.
   * The float value that the highest quantized value represents.
   * @return maxActivations.
   */
  public Output<TFloat32> maxActivations() {
    return maxActivations;
  }

  @OpInputsMetadata(
      outputsClass = QuantizedReluX.class
  )
  public static class Inputs extends RawOpInputs<QuantizedReluX<?>> {
    /**
     * The features input
     */
    public final Operand<? extends TNumber> features;

    /**
     * The maxValue input
     */
    public final Operand<TFloat32> maxValue;

    /**
     * The float value that the lowest quantized value represents.
     */
    public final Operand<TFloat32> minFeatures;

    /**
     * The float value that the highest quantized value represents.
     */
    public final Operand<TFloat32> maxFeatures;

    /**
     * The Tinput attribute
     */
    public final DataType Tinput;

    /**
     * The outType attribute
     */
    public final DataType outType;

    public Inputs(GraphOperation op) {
      super(new QuantizedReluX<>(op), op, Arrays.asList("Tinput", "out_type"));
      int inputIndex = 0;
      features = (Operand<? extends TNumber>) op.input(inputIndex++);
      maxValue = (Operand<TFloat32>) op.input(inputIndex++);
      minFeatures = (Operand<TFloat32>) op.input(inputIndex++);
      maxFeatures = (Operand<TFloat32>) op.input(inputIndex++);
      Tinput = op.attributes().getAttrType("Tinput");
      outType = op.attributes().getAttrType("out_type");
    }
  }
}
