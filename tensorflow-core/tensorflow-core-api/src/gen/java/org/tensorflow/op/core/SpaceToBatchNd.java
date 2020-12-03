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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * SpaceToBatch for N-D tensors of type T.
 * <p>
 * This operation divides "spatial" dimensions `[1, ..., M]` of the input into a
 * grid of blocks of shape `block_shape`, and interleaves these blocks with the
 * "batch" dimension (0) such that in the output, the spatial dimensions
 * `[1, ..., M]` correspond to the position within the grid, and the batch
 * dimension combines both the position within a spatial block and the original
 * batch position.  Prior to division into blocks, the spatial dimensions of the
 * input are optionally zero padded according to `paddings`.  See below for a
 * precise description.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class SpaceToBatchNd<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SpaceToBatchNd operation.
   * 
   * @param scope current scope
   * @param input N-D with shape `input_shape = [batch] + spatial_shape + remaining_shape`,
   * where spatial_shape has `M` dimensions.
   * @param blockShape 1-D with shape `[M]`, all values must be >= 1.
   * @param paddings 2-D with shape `[M, 2]`, all values must be >= 0.
   *   `paddings[i] = [pad_start, pad_end]` specifies the padding for input dimension
   *   `i + 1`, which corresponds to spatial dimension `i`.  It is required that
   *   `block_shape[i]` divides `input_shape[i + 1] + pad_start + pad_end`.
   * <p>
   * This operation is equivalent to the following steps:
   * <p>
   * 1. Zero-pad the start and end of dimensions `[1, ..., M]` of the
   *    input according to `paddings` to produce `padded` of shape `padded_shape`.
   * <p>
   * 2. Reshape `padded` to `reshaped_padded` of shape:
   * <p>
   *      [batch] +
   *      [padded_shape[1] / block_shape[0],
   *        block_shape[0],
   *       ...,
   *       padded_shape[M] / block_shape[M-1],
   *       block_shape[M-1]] +
   *      remaining_shape
   * <p>
   * 3. Permute dimensions of `reshaped_padded` to produce
   *    `permuted_reshaped_padded` of shape:
   * <p>
   *      block_shape +
   *      [batch] +
   *      [padded_shape[1] / block_shape[0],
   *       ...,
   *       padded_shape[M] / block_shape[M-1]] +
   *      remaining_shape
   * <p>
   * 4. Reshape `permuted_reshaped_padded` to flatten `block_shape` into the batch
   *    dimension, producing an output tensor of shape:
   * <p>
   *      [batch * prod(block_shape)] +
   *      [padded_shape[1] / block_shape[0],
   *       ...,
   *       padded_shape[M] / block_shape[M-1]] +
   *      remaining_shape
   * <p>
   * Some examples:
   * <p>
   * (1) For the following input of shape `[1, 2, 2, 1]`, `block_shape = [2, 2]`, and
   *     `paddings = [[0, 0], [0, 0]]`:
   * <pre>{@code
   * x = [[[[1], [2]], [[3], [4]]]]
   * }</pre>
   * The output tensor has shape `[4, 1, 1, 1]` and value:
   * <pre>{@code
   * [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
   * }</pre>
   * (2) For the following input of shape `[1, 2, 2, 3]`, `block_shape = [2, 2]`, and
   *     `paddings = [[0, 0], [0, 0]]`:
   * <pre>{@code
   * x = [[[[1, 2, 3], [4, 5, 6]],
   *       [[7, 8, 9], [10, 11, 12]]]]
   * }</pre>
   * The output tensor has shape `[4, 1, 1, 3]` and value:
   * <pre>{@code
   * [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
   * }</pre>
   * (3) For the following input of shape `[1, 4, 4, 1]`, `block_shape = [2, 2]`, and
   *     `paddings = [[0, 0], [0, 0]]`:
   * <pre>{@code
   * x = [[[[1],   [2],  [3],  [4]],
   *       [[5],   [6],  [7],  [8]],
   *       [[9],  [10], [11],  [12]],
   *       [[13], [14], [15],  [16]]]]
   * }</pre>
   * The output tensor has shape `[4, 2, 2, 1]` and value:
   * <pre>{@code
   * x = [[[[1], [3]], [[9], [11]]],
   *      [[[2], [4]], [[10], [12]]],
   *      [[[5], [7]], [[13], [15]]],
   *      [[[6], [8]], [[14], [16]]]]
   * }</pre>
   * (4) For the following input of shape `[2, 2, 4, 1]`, block_shape = `[2, 2]`, and
   *     paddings = `[[0, 0], [2, 0]]`:
   * <pre>{@code
   * x = [[[[1],   [2],  [3],  [4]],
   *       [[5],   [6],  [7],  [8]]],
   *      [[[9],  [10], [11],  [12]],
   *       [[13], [14], [15],  [16]]]]
   * }</pre>
   * The output tensor has shape `[8, 1, 3, 1]` and value:
   * <pre>{@code
   * x = [[[[0], [1], [3]]], [[[0], [9], [11]]],
   *      [[[0], [2], [4]]], [[[0], [10], [12]]],
   *      [[[0], [5], [7]]], [[[0], [13], [15]]],
   *      [[[0], [6], [8]]], [[[0], [14], [16]]]]
   * }</pre>
   * Among others, this operation is useful for reducing atrous convolution into
   * regular convolution.
   * @return a new instance of SpaceToBatchNd
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber, V extends TNumber> SpaceToBatchNd<T> create(Scope scope, Operand<T> input, Operand<U> blockShape, Operand<V> paddings) {
    OperationBuilder opBuilder = scope.env().opBuilder("SpaceToBatchND", scope.makeOpName("SpaceToBatchNd"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(blockShape.asOutput());
    opBuilder.addInput(paddings.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SpaceToBatchNd<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SpaceToBatchND";
  
  private Output<T> output;
  
  private SpaceToBatchNd(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
