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
 * SpaceToBatch for 4-D tensors of type T.
 * This is a legacy version of the more general SpaceToBatchND.
 * <p>Zero-pads and then rearranges (permutes) blocks of spatial data into batch.
 * More specifically, this op outputs a copy of the input tensor where values from
 * the {@code height} and {@code width} dimensions are moved to the {@code batch} dimension. After
 * the zero-padding, both {@code height} and {@code width} of the input must be divisible by the
 * block size.
 * <p>The attr {@code block_size} must be greater than one. It indicates the block size.
 * <ul>
 * <li>Non-overlapping blocks of size {@code block_size x block size} in the height and
 * width dimensions are rearranged into the batch dimension at each location.</li>
 * <li>The batch of the output tensor is {@code batch * block_size * block_size}.</li>
 * <li>Both height_pad and width_pad must be divisible by block_size.</li>
 * </ul>
 * <p>The shape of the output will be:
 * <pre>
 * [batch*block_size*block_size, height_pad/block_size, width_pad/block_size,
 *  depth]
 * </pre>
 * <p>Some examples:
 * <p>(1) For the following input of shape {@code [1, 2, 2, 1]} and block_size of 2:
 * <pre>
 * x = [[[[1], [2]], [[3], [4]]]]
 * </pre>
 * <p>The output tensor has shape {@code [4, 1, 1, 1]} and value:
 * <pre>
 * [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
 * </pre>
 * <p>(2) For the following input of shape {@code [1, 2, 2, 3]} and block_size of 2:
 * <pre>
 * x = [[[[1, 2, 3], [4, 5, 6]],
 *       [[7, 8, 9], [10, 11, 12]]]]
 * </pre>
 * <p>The output tensor has shape {@code [4, 1, 1, 3]} and value:
 * <pre>
 * [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
 * </pre>
 * <p>(3) For the following input of shape {@code [1, 4, 4, 1]} and block_size of 2:
 * <pre>
 * x = [[[[1],   [2],  [3],  [4]],
 *       [[5],   [6],  [7],  [8]],
 *       [[9],  [10], [11],  [12]],
 *       [[13], [14], [15],  [16]]]]
 * </pre>
 * <p>The output tensor has shape {@code [4, 2, 2, 1]} and value:
 * <pre>
 * x = [[[[1], [3]], [[9], [11]]],
 *      [[[2], [4]], [[10], [12]]],
 *      [[[5], [7]], [[13], [15]]],
 *      [[[6], [8]], [[14], [16]]]]
 * </pre>
 * <p>(4) For the following input of shape {@code [2, 2, 4, 1]} and block_size of 2:
 * <pre>
 * x = [[[[1],   [2],  [3],  [4]],
 *       [[5],   [6],  [7],  [8]]],
 *      [[[9],  [10], [11],  [12]],
 *       [[13], [14], [15],  [16]]]]
 * </pre>
 * <p>The output tensor has shape {@code [8, 1, 2, 1]} and value:
 * <pre>
 * x = [[[[1], [3]]], [[[9], [11]]], [[[2], [4]]], [[[10], [12]]],
 *      [[[5], [7]]], [[[13], [15]]], [[[6], [8]]], [[[14], [16]]]]
 * </pre>
 * <p>Among others, this operation is useful for reducing atrous convolution into
 * regular convolution.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = SpaceToBatch.OP_NAME,
    inputsClass = SpaceToBatch.Inputs.class
)
@Operator(
    group = "nn"
)
public final class SpaceToBatch<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SpaceToBatch";

  private Output<T> output;

  public SpaceToBatch(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SpaceToBatch operation.
   *
   * @param scope current scope
   * @param input 4-D with shape {@code [batch, height, width, depth]}.
   * @param paddings 2-D tensor of non-negative integers with shape {@code [2, 2]}. It specifies
   * the padding of the input with zeros across the spatial dimensions as follows:
   * <pre>
   *   paddings = [[pad_top, pad_bottom], [pad_left, pad_right]]
   * </pre>
   * <p>The effective spatial dimensions of the zero-padded input tensor will be:
   * <pre>
   *   height_pad = pad_top + height + pad_bottom
   *   width_pad = pad_left + width + pad_right
   * </pre>
   * @param blockSize The value of the blockSize attribute
   * @param <T> data type for {@code SpaceToBatch} output and operands
   * @return a new instance of SpaceToBatch
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SpaceToBatch<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> paddings, Long blockSize) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SpaceToBatch");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(paddings.asOutput());
    opBuilder.setAttr("block_size", blockSize);
    return new SpaceToBatch<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
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
      outputsClass = SpaceToBatch.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SpaceToBatch<T>> {
    /**
     * 4-D with shape {@code [batch, height, width, depth]}.
     */
    public final Operand<T> input;

    /**
     * 2-D tensor of non-negative integers with shape {@code [2, 2]}. It specifies
     * the padding of the input with zeros across the spatial dimensions as follows:
     * <pre>
     *   paddings = [[pad_top, pad_bottom], [pad_left, pad_right]]
     * </pre>
     * <p>The effective spatial dimensions of the zero-padded input tensor will be:
     * <pre>
     *   height_pad = pad_top + height + pad_bottom
     *   width_pad = pad_left + width + pad_right
     * </pre>
     */
    public final Operand<? extends TNumber> paddings;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tpaddings attribute
     */
    public final DataType Tpaddings;

    /**
     * The blockSize attribute
     */
    public final long blockSize;

    public Inputs(GraphOperation op) {
      super(new SpaceToBatch<>(op), op, Arrays.asList("T", "Tpaddings", "block_size"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      paddings = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tpaddings = op.attributes().getAttrType("Tpaddings");
      blockSize = op.attributes().getAttrInt("block_size");
    }
  }
}
