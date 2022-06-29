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

package org.tensorflow.op.ragged;

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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Helper used to compute the gradient for {@code RaggedTensorToVariant}.
 * Computes the gradient for the dense_values input to the RaggedTensorToVariant
 * op, given the variant-encoded ragged gradients of the outputs, along with
 * the outer row-splits and the shape of the dense-values that were provided as
 * inputs to the RaggedTensorToVariant op.
 *
 * @param <U> data type for {@code dense_values_grad} output
 */
@OpMetadata(
    opType = RaggedTensorToVariantGradient.OP_NAME,
    inputsClass = RaggedTensorToVariantGradient.Inputs.class
)
public final class RaggedTensorToVariantGradient<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RaggedTensorToVariantGradient";

  private Output<U> denseValuesGrad;

  public RaggedTensorToVariantGradient(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    denseValuesGrad = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RaggedTensorToVariantGradient operation.
   *
   * @param scope current scope
   * @param encodedRaggedGrad A {@code variant} Tensor containing encoded {@code RaggedTensor} gradients.
   * @param rowSplits Outermost row-splits that were used as input to the RaggedTensorToVariant op.
   * @param denseValuesShape Shape of the dense_values that was used as an input to the
   * RaggedTensorToVariant op.
   * @param Tvalues The value of the Tvalues attribute
   * @param <U> data type for {@code RaggedTensorToVariantGradient} output and operands
   * @return a new instance of RaggedTensorToVariantGradient
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> RaggedTensorToVariantGradient<U> create(Scope scope,
      Operand<? extends TType> encodedRaggedGrad, Operand<? extends TNumber> rowSplits,
      Operand<TInt32> denseValuesShape, Class<U> Tvalues) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RaggedTensorToVariantGradient");
    opBuilder.addInput(encodedRaggedGrad.asOutput());
    opBuilder.addInput(rowSplits.asOutput());
    opBuilder.addInput(denseValuesShape.asOutput());
    opBuilder.setAttr("Tvalues", Operands.toDataType(Tvalues));
    return new RaggedTensorToVariantGradient<>(opBuilder.build());
  }

  /**
   * Gets denseValuesGrad.
   * Gradient for the dense_values of the RaggedTensorToVariant op.
   * @return denseValuesGrad.
   */
  public Output<U> denseValuesGrad() {
    return denseValuesGrad;
  }

  @Override
  public Output<U> asOutput() {
    return denseValuesGrad;
  }

  @OpInputsMetadata(
      outputsClass = RaggedTensorToVariantGradient.class
  )
  public static class Inputs extends RawOpInputs<RaggedTensorToVariantGradient<?>> {
    /**
     * A {@code variant} Tensor containing encoded {@code RaggedTensor} gradients.
     */
    public final Operand<? extends TType> encodedRaggedGrad;

    /**
     * Outermost row-splits that were used as input to the RaggedTensorToVariant op.
     */
    public final Operand<? extends TNumber> rowSplits;

    /**
     * Shape of the dense_values that was used as an input to the
     * RaggedTensorToVariant op.
     */
    public final Operand<TInt32> denseValuesShape;

    /**
     * The Tvalues attribute
     */
    public final DataType Tvalues;

    /**
     * The Tsplits attribute
     */
    public final DataType Tsplits;

    public Inputs(GraphOperation op) {
      super(new RaggedTensorToVariantGradient<>(op), op, Arrays.asList("Tvalues", "Tsplits"));
      int inputIndex = 0;
      encodedRaggedGrad = (Operand<? extends TType>) op.input(inputIndex++);
      rowSplits = (Operand<? extends TNumber>) op.input(inputIndex++);
      denseValuesShape = (Operand<TInt32>) op.input(inputIndex++);
      Tvalues = op.attributes().getAttrType("Tvalues");
      Tsplits = op.attributes().getAttrType("Tsplits");
    }
  }
}
