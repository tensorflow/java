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
 * BatchToSpace for 4-D tensors of type T.
 * <p>
 * This is a legacy version of the more general BatchToSpaceND.
 * <p>
 * Rearranges (permutes) data from batch into blocks of spatial data, followed by
 * cropping. This is the reverse transformation of SpaceToBatch. More specifically,
 * this op outputs a copy of the input tensor where values from the `batch`
 * dimension are moved in spatial blocks to the `height` and `width` dimensions,
 * followed by cropping along the `height` and `width` dimensions.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class BatchToSpace<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new BatchToSpace operation.
   * 
   * @param scope current scope
   * @param input 4-D tensor with shape
   * `[batch<i>block_size</i>block_size, height_pad/block_size, width_pad/block_size,
   *   depth]`. Note that the batch size of the input tensor must be divisible by
   * `block_size * block_size`.
   * @param crops 2-D tensor of non-negative integers with shape `[2, 2]`. It specifies
   * how many elements to crop from the intermediate result across the spatial
   * dimensions as follows:
   * <p>
   *     crops = [[crop_top, crop_bottom], [crop_left, crop_right]]
   * @param blockSize 
   * @return a new instance of BatchToSpace
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> BatchToSpace<T> create(Scope scope, Operand<T> input, Operand<U> crops, Long blockSize) {
    OperationBuilder opBuilder = scope.env().opBuilder("BatchToSpace", scope.makeOpName("BatchToSpace"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(crops.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("block_size", blockSize);
    return new BatchToSpace<T>(opBuilder.build());
  }
  
  /**
   * 4-D with shape `[batch, height, width, depth]`, where:
   * <p>
   *       height = height_pad - crop_top - crop_bottom
   *       width = width_pad - crop_left - crop_right
   * <p>
   * The attr `block_size` must be greater than one. It indicates the block size.
   * <p>
   * Some examples:
   * <p>
   * (1) For the following input of shape `[4, 1, 1, 1]` and block_size of 2:
   * <pre>{@code
   * [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
   * }</pre>
   * The output tensor has shape `[1, 2, 2, 1]` and value:
   * <pre>{@code
   * x = [[[[1], [2]], [[3], [4]]]]
   * }</pre>
   * (2) For the following input of shape `[4, 1, 1, 3]` and block_size of 2:
   * <pre>{@code
   * [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
   * }</pre>
   * The output tensor has shape `[1, 2, 2, 3]` and value:
   * <pre>{@code
   * x = [[[[1, 2, 3], [4, 5, 6]],
   *       [[7, 8, 9], [10, 11, 12]]]]
   * }</pre>
   * (3) For the following input of shape `[4, 2, 2, 1]` and block_size of 2:
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
   * (4) For the following input of shape `[8, 1, 2, 1]` and block_size of 2:
   * <pre>{@code
   * x = [[[[1], [3]]], [[[9], [11]]], [[[2], [4]]], [[[10], [12]]],
   *      [[[5], [7]]], [[[13], [15]]], [[[6], [8]]], [[[14], [16]]]]
   * }</pre>
   * The output tensor has shape `[2, 2, 4, 1]` and value:
   * <pre>{@code
   * x = [[[[1], [3]], [[5], [7]]],
   *      [[[2], [4]], [[10], [12]]],
   *      [[[5], [7]], [[13], [15]]],
   *      [[[6], [8]], [[14], [16]]]]
   * }</pre>
   * 
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BatchToSpace";
  
  private Output<T> output;
  
  private BatchToSpace(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
