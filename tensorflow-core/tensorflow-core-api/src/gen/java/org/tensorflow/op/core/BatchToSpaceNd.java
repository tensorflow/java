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
 * BatchToSpace for N-D tensors of type T.
 * <p>
 * This operation reshapes the "batch" dimension 0 into `M + 1` dimensions of shape
 * `block_shape + [batch]`, interleaves these blocks back into the grid defined by
 * the spatial dimensions `[1, ..., M]`, to obtain a result with the same rank as
 * the input.  The spatial dimensions of this intermediate result are then
 * optionally cropped according to `crops` to produce the output.  This is the
 * reverse of SpaceToBatch.  See below for a precise description.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class BatchToSpaceNd<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new BatchToSpaceNd operation.
   * 
   * @param scope current scope
   * @param input N-D with shape `input_shape = [batch] + spatial_shape + remaining_shape`,
   * where spatial_shape has M dimensions.
   * @param blockShape 1-D with shape `[M]`, all values must be >= 1.
   * @param crops 2-D with shape `[M, 2]`, all values must be >= 0.
   *   `crops[i] = [crop_start, crop_end]` specifies the amount to crop from input
   *   dimension `i + 1`, which corresponds to spatial dimension `i`.  It is
   *   required that
   *   `crop_start[i] + crop_end[i] <= block_shape[i] * input_shape[i + 1]`.
   * <p>
   * This operation is equivalent to the following steps:
   * <p>
   * 1. Reshape `input` to `reshaped` of shape:
   *      [block_shape[0], ..., block_shape[M-1],
   *       batch / prod(block_shape),
   *       input_shape[1], ..., input_shape[N-1]]
   * <p>
   * 2. Permute dimensions of `reshaped` to produce `permuted` of shape
   *      [batch / prod(block_shape),
   * <p>
   *       input_shape[1], block_shape[0],
   *       ...,
   *       input_shape[M], block_shape[M-1],
   * <p>
   *       input_shape[M+1], ..., input_shape[N-1]]
   * <p>
   * 3. Reshape `permuted` to produce `reshaped_permuted` of shape
   *      [batch / prod(block_shape),
   * <p>
   *       input_shape[1] * block_shape[0],
   *       ...,
   *       input_shape[M] * block_shape[M-1],
   * <p>
   *       input_shape[M+1],
   *       ...,
   *       input_shape[N-1]]
   * <p>
   * 4. Crop the start and end of dimensions `[1, ..., M]` of
   *    `reshaped_permuted` according to `crops` to produce the output of shape:
   *      [batch / prod(block_shape),
   * <p>
   *       input_shape[1] * block_shape[0] - crops[0,0] - crops[0,1],
   *       ...,
   *       input_shape[M] * block_shape[M-1] - crops[M-1,0] - crops[M-1,1],
   * <p>
   *       input_shape[M+1], ..., input_shape[N-1]]
   * <p>
   * Some examples:
   * <p>
   * (1) For the following input of shape `[4, 1, 1, 1]`, `block_shape = [2, 2]`, and
   *     `crops = [[0, 0], [0, 0]]`:
   * <pre>{@code
   * [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
   * }</pre>
   * The output tensor has shape `[1, 2, 2, 1]` and value:
   * <pre>{@code
   * x = [[[[1], [2]], [[3], [4]]]]
   * }</pre>
   * (2) For the following input of shape `[4, 1, 1, 3]`, `block_shape = [2, 2]`, and
   *     `crops = [[0, 0], [0, 0]]`:
   * <pre>{@code
   * [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
   * }</pre>
   * The output tensor has shape `[1, 2, 2, 3]` and value:
   * <pre>{@code
   * x = [[[[1, 2, 3], [4, 5, 6]],
   *       [[7, 8, 9], [10, 11, 12]]]]
   * }</pre>
   * (3) For the following input of shape `[4, 2, 2, 1]`, `block_shape = [2, 2]`, and
   *     `crops = [[0, 0], [0, 0]]`:
   * <pre>{@code
   * x = [[[[1], [3]], [[9], [11]]],
   *      [[[2], [4]], [[10], [12]]],
   *      [[[5], [7]], [[13], [15]]],
   *      [[[6], [8]], [[14], [16]]]]
   * }</pre>
   * The output tensor has shape `[1, 4, 4, 1]` and value:
   * <pre>{@code
   * x = [[[[1],   [2],  [3],  [4]],
   *      [[5],   [6],  [7],  [8]],
   *      [[9],  [10], [11],  [12]],
   *      [[13], [14], [15],  [16]]]]
   * }</pre>
   * (4) For the following input of shape `[8, 1, 3, 1]`, `block_shape = [2, 2]`, and
   *     `crops = [[0, 0], [2, 0]]`:
   * <pre>{@code
   * x = [[[[0], [1], [3]]], [[[0], [9], [11]]],
   *      [[[0], [2], [4]]], [[[0], [10], [12]]],
   *      [[[0], [5], [7]]], [[[0], [13], [15]]],
   *      [[[0], [6], [8]]], [[[0], [14], [16]]]]
   * }</pre>
   * The output tensor has shape `[2, 2, 4, 1]` and value:
   * <pre>{@code
   * x = [[[[1],   [2],  [3],  [4]],
   *       [[5],   [6],  [7],  [8]]],
   *      [[[9],  [10], [11],  [12]],
   *       [[13], [14], [15],  [16]]]]
   * }</pre>
   * 
   * @return a new instance of BatchToSpaceNd
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber, V extends TNumber> BatchToSpaceNd<T> create(Scope scope, Operand<T> input, Operand<U> blockShape, Operand<V> crops) {
    OperationBuilder opBuilder = scope.env().opBuilder("BatchToSpaceND", scope.makeOpName("BatchToSpaceNd"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(blockShape.asOutput());
    opBuilder.addInput(crops.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new BatchToSpaceNd<T>(opBuilder.build());
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
  public static final String OP_NAME = "BatchToSpaceND";
  
  private Output<T> output;
  
  private BatchToSpaceNd(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
