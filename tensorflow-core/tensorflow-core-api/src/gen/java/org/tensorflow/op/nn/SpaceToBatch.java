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
 * SpaceToBatch for 4-D tensors of type T.
 * <p>
 * This is a legacy version of the more general SpaceToBatchND.
 * <p>
 * Zero-pads and then rearranges (permutes) blocks of spatial data into batch.
 * More specifically, this op outputs a copy of the input tensor where values from
 * the `height` and `width` dimensions are moved to the `batch` dimension. After
 * the zero-padding, both `height` and `width` of the input must be divisible by the
 * block size.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "nn")
public final class SpaceToBatch<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SpaceToBatch operation.
   * 
   * @param scope current scope
   * @param input 4-D with shape `[batch, height, width, depth]`.
   * @param paddings 2-D tensor of non-negative integers with shape `[2, 2]`. It specifies
   *   the padding of the input with zeros across the spatial dimensions as follows:
   * <p>
   *       paddings = [[pad_top, pad_bottom], [pad_left, pad_right]]
   * <p>
   *   The effective spatial dimensions of the zero-padded input tensor will be:
   * <p>
   *       height_pad = pad_top + height + pad_bottom
   *       width_pad = pad_left + width + pad_right
   * <p>
   * The attr `block_size` must be greater than one. It indicates the block size.
   * <p>
   *   * Non-overlapping blocks of size `block_size x block size` in the height and
   *     width dimensions are rearranged into the batch dimension at each location.
   *   * The batch of the output tensor is `batch * block_size * block_size`.
   *   * Both height_pad and width_pad must be divisible by block_size.
   * <p>
   * The shape of the output will be:
   * <p>
   *     [batch<i>block_size</i>block_size, height_pad/block_size, width_pad/block_size,
   *      depth]
   * <p>
   * Some examples:
   * <p>
   * (1) For the following input of shape `[1, 2, 2, 1]` and block_size of 2:
   * <pre>{@code
   * x = [[[[1], [2]], [[3], [4]]]]
   * }</pre>
   * The output tensor has shape `[4, 1, 1, 1]` and value:
   * <pre>{@code
   * [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
   * }</pre>
   * (2) For the following input of shape `[1, 2, 2, 3]` and block_size of 2:
   * <pre>{@code
   * x = [[[[1, 2, 3], [4, 5, 6]],
   *       [[7, 8, 9], [10, 11, 12]]]]
   * }</pre>
   * The output tensor has shape `[4, 1, 1, 3]` and value:
   * <pre>{@code
   * [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
   * }</pre>
   * (3) For the following input of shape `[1, 4, 4, 1]` and block_size of 2:
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
   * (4) For the following input of shape `[2, 2, 4, 1]` and block_size of 2:
   * <pre>{@code
   * x = [[[[1],   [2],  [3],  [4]],
   *       [[5],   [6],  [7],  [8]]],
   *      [[[9],  [10], [11],  [12]],
   *       [[13], [14], [15],  [16]]]]
   * }</pre>
   * The output tensor has shape `[8, 1, 2, 1]` and value:
   * <pre>{@code
   * x = [[[[1], [3]]], [[[9], [11]]], [[[2], [4]]], [[[10], [12]]],
   *      [[[5], [7]]], [[[13], [15]]], [[[6], [8]]], [[[14], [16]]]]
   * }</pre>
   * Among others, this operation is useful for reducing atrous convolution into
   * regular convolution.
   * @param blockSize 
   * @return a new instance of SpaceToBatch
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> SpaceToBatch<T> create(Scope scope, Operand<T> input, Operand<U> paddings, Long blockSize) {
    OperationBuilder opBuilder = scope.env().opBuilder("SpaceToBatch", scope.makeOpName("SpaceToBatch"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(paddings.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("block_size", blockSize);
    return new SpaceToBatch<T>(opBuilder.build());
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
  public static final String OP_NAME = "SpaceToBatch";
  
  private Output<T> output;
  
  private SpaceToBatch(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
