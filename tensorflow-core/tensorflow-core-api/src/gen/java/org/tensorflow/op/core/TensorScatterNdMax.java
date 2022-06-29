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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Apply a sparse update to a tensor taking the element-wise maximum.
 * Returns a new tensor copied from {@code tensor} whose values are element-wise maximum between
 * tensor and updates according to the indices.
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>tensor = [0, 0, 0, 0, 0, 0, 0, 0]
 * indices = [[1], [4], [5]]
 * updates = [1, -1, 1]
 * tf.tensor_scatter_nd_max(tensor, indices, updates).numpy()
 * array([0, 1, 0, 0, 0, 1, 0, 0], dtype=int32)
 * </blockquote>
 * </blockquote>
 * </blockquote>
 * <p>Refer to {@code tf.tensor_scatter_nd_update} for more details.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = TensorScatterNdMax.OP_NAME,
    inputsClass = TensorScatterNdMax.Inputs.class
)
@Operator
public final class TensorScatterNdMax<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorScatterMax";

  private Output<T> output;

  public TensorScatterNdMax(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorScatterMax operation.
   *
   * @param scope current scope
   * @param tensor Tensor to update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @param <T> data type for {@code TensorScatterMax} output and operands
   * @return a new instance of TensorScatterNdMax
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TensorScatterNdMax<T> create(Scope scope, Operand<T> tensor,
      Operand<? extends TNumber> indices, Operand<T> updates) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorScatterNdMax");
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(updates.asOutput());
    return new TensorScatterNdMax<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A new tensor copied from tensor whose values are element-wise maximum between tensor and updates according to the indices.
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
      outputsClass = TensorScatterNdMax.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<TensorScatterNdMax<T>> {
    /**
     * Tensor to update.
     */
    public final Operand<T> tensor;

    /**
     * Index tensor.
     */
    public final Operand<? extends TNumber> indices;

    /**
     * Updates to scatter into output.
     */
    public final Operand<T> updates;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    public Inputs(GraphOperation op) {
      super(new TensorScatterNdMax<>(op), op, Arrays.asList("T", "Tindices"));
      int inputIndex = 0;
      tensor = (Operand<T>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      updates = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
