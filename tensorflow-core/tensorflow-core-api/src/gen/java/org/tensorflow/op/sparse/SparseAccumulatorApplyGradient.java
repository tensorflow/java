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

package org.tensorflow.op.sparse;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Applies a sparse gradient to a given accumulator.
 * Does not add if local_step is smaller than the accumulator's
 * global_step.
 */
@OpMetadata(
    opType = SparseAccumulatorApplyGradient.OP_NAME,
    inputsClass = SparseAccumulatorApplyGradient.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseAccumulatorApplyGradient extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseAccumulatorApplyGradient";

  public SparseAccumulatorApplyGradient(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new SparseAccumulatorApplyGradient operation.
   *
   * @param scope current scope
   * @param handle The handle to a accumulator.
   * @param localStep The local_step value at which the sparse gradient was computed.
   * @param gradientIndices Indices of the sparse gradient to be accumulated. Must be a
   * vector.
   * @param gradientValues Values are the non-zero slices of the gradient, and must have
   * the same first dimension as indices, i.e., the nnz represented by indices and
   * values must be consistent.
   * @param gradientShape Shape of the sparse gradient to be accumulated.
   * @param hasKnownShape Boolean indicating whether gradient_shape is unknown, in which
   * case the input is ignored during validation.
   * @return a new instance of SparseAccumulatorApplyGradient
   */
  @Endpoint(
      describeByClass = true
  )
  public static SparseAccumulatorApplyGradient create(Scope scope, Operand<TString> handle,
      Operand<TInt64> localStep, Operand<TInt64> gradientIndices,
      Operand<? extends TType> gradientValues, Operand<TInt64> gradientShape,
      Boolean hasKnownShape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseAccumulatorApplyGradient");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(localStep.asOutput());
    opBuilder.addInput(gradientIndices.asOutput());
    opBuilder.addInput(gradientValues.asOutput());
    opBuilder.addInput(gradientShape.asOutput());
    opBuilder.setAttr("has_known_shape", hasKnownShape);
    return new SparseAccumulatorApplyGradient(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = SparseAccumulatorApplyGradient.class
  )
  public static class Inputs extends RawOpInputs<SparseAccumulatorApplyGradient> {
    /**
     * The handle to a accumulator.
     */
    public final Operand<TString> handle;

    /**
     * The local_step value at which the sparse gradient was computed.
     */
    public final Operand<TInt64> localStep;

    /**
     * Indices of the sparse gradient to be accumulated. Must be a
     * vector.
     */
    public final Operand<TInt64> gradientIndices;

    /**
     * Values are the non-zero slices of the gradient, and must have
     * the same first dimension as indices, i.e., the nnz represented by indices and
     * values must be consistent.
     */
    public final Operand<? extends TType> gradientValues;

    /**
     * Shape of the sparse gradient to be accumulated.
     */
    public final Operand<TInt64> gradientShape;

    /**
     * The data type of accumulated gradients. Needs to correspond to the type
     * of the accumulator.
     */
    public final DataType dtype;

    /**
     * Boolean indicating whether gradient_shape is unknown, in which
     * case the input is ignored during validation.
     */
    public final boolean hasKnownShape;

    public Inputs(GraphOperation op) {
      super(new SparseAccumulatorApplyGradient(op), op, Arrays.asList("dtype", "has_known_shape"));
      int inputIndex = 0;
      handle = (Operand<TString>) op.input(inputIndex++);
      localStep = (Operand<TInt64>) op.input(inputIndex++);
      gradientIndices = (Operand<TInt64>) op.input(inputIndex++);
      gradientValues = (Operand<? extends TType>) op.input(inputIndex++);
      gradientShape = (Operand<TInt64>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      hasKnownShape = op.attributes().getAttrBool("has_known_shape");
    }
  }
}
