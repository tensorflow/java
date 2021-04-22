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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Encodes a {@code RaggedTensor} into a {@code variant} Tensor.
 * Encodes the given {@code RaggedTensor} and returns a {@code variant} Tensor. If
 * {@code batched_input} is True, then input {@code RaggedTensor} is unbatched along the
 * zero-th dimension, each component {@code RaggedTensor} is encoded into a scalar
 * {@code variant} Tensor, and these are stacked to return a 1-D {@code variant} Tensor.
 * If {@code batched_input} is False, then the input {@code RaggedTensor} is encoded as is and
 * a scalar {@code variant} Tensor is returned. A {@code RaggedTensor} is encoded by first
 * creating a 1-D {@code variant} Tensor with {@code ragged_rank + 1} elements, containing the
 * splits and values Tensors of the {@code RaggedTensor}. Then the 1-D {@code variant} Tensor
 * is wrapped in a scalar {@code variant} Tensor. See {@code RaggedTensorFromVariant} for the
 * corresponding decoding logic.
 */
public final class RaggedTensorToVariant extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RaggedTensorToVariant";

  private Output<? extends TType> encodedRagged;

  @SuppressWarnings("unchecked")
  private RaggedTensorToVariant(Operation operation) {
    super(operation);
    int outputIdx = 0;
    encodedRagged = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RaggedTensorToVariant operation.
   *
   * @param scope current scope
   * @param rtNestedSplits A list of one or more Tensors representing the splits of the input
   * {@code RaggedTensor}.
   * @param rtDenseValues A Tensor representing the values of the input {@code RaggedTensor}.
   * @param batchedInput A {@code bool} denoting whether the input is a batched {@code RaggedTensor}.
   * @return a new instance of RaggedTensorToVariant
   */
  @Endpoint(
      describeByClass = true
  )
  public static RaggedTensorToVariant create(Scope scope,
      Iterable<Operand<? extends TNumber>> rtNestedSplits, Operand<? extends TType> rtDenseValues,
      Boolean batchedInput) {
    OperationBuilder opBuilder = scope.env().opBuilder("RaggedTensorToVariant", scope.makeOpName("RaggedTensorToVariant"));
    opBuilder.addInputList(Operands.asOutputs(rtNestedSplits));
    opBuilder.addInput(rtDenseValues.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("batched_input", batchedInput);
    return new RaggedTensorToVariant(opBuilder.build());
  }

  /**
   * Gets encodedRagged.
   * A {@code variant} Tensor that containing encoded {@code RaggedTensor}.
   * @return encodedRagged.
   */
  public Output<? extends TType> encodedRagged() {
    return encodedRagged;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) encodedRagged;
  }
}
