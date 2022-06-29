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
 * BatchToSpace for 4-D tensors of type T.
 * This is a legacy version of the more general BatchToSpaceND.
 * <p>Rearranges (permutes) data from batch into blocks of spatial data, followed by
 * cropping. This is the reverse transformation of SpaceToBatch. More specifically,
 * this op outputs a copy of the input tensor where values from the {@code batch}
 * dimension are moved in spatial blocks to the {@code height} and {@code width} dimensions,
 * followed by cropping along the {@code height} and {@code width} dimensions.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = BatchToSpace.OP_NAME,
    inputsClass = BatchToSpace.Inputs.class
)
@Operator
public final class BatchToSpace<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BatchToSpace";

  private Output<T> output;

  public BatchToSpace(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BatchToSpace operation.
   *
   * @param scope current scope
   * @param input 4-D tensor with shape
   * {@code [batch*block_size*block_size, height_pad/block_size, width_pad/block_size, depth]}. Note that the batch size of the input tensor must be divisible by
   * {@code block_size * block_size}.
   * @param crops 2-D tensor of non-negative integers with shape {@code [2, 2]}. It specifies
   * how many elements to crop from the intermediate result across the spatial
   * dimensions as follows:
   * <pre>
   * crops = [[crop_top, crop_bottom], [crop_left, crop_right]]
   * </pre>
   * @param blockSize The value of the blockSize attribute
   * @param <T> data type for {@code BatchToSpace} output and operands
   * @return a new instance of BatchToSpace
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> BatchToSpace<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> crops, Long blockSize) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BatchToSpace");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(crops.asOutput());
    opBuilder.setAttr("block_size", blockSize);
    return new BatchToSpace<>(opBuilder.build());
  }

  /**
   * Gets output.
   * 4-D with shape {@code [batch, height, width, depth]}, where:
   * <pre>
   *   height = height_pad - crop_top - crop_bottom
   *   width = width_pad - crop_left - crop_right
   * </pre>
   * <p>The attr {@code block_size} must be greater than one. It indicates the block size.
   * <p>Some examples:
   * <p>(1) For the following input of shape {@code [4, 1, 1, 1]} and block_size of 2:
   * <pre>
   * [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
   * </pre>
   * <p>The output tensor has shape {@code [1, 2, 2, 1]} and value:
   * <pre>
   * x = [[[[1], [2]], [[3], [4]]]]
   * </pre>
   * <p>(2) For the following input of shape {@code [4, 1, 1, 3]} and block_size of 2:
   * <pre>
   * [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
   * </pre>
   * <p>The output tensor has shape {@code [1, 2, 2, 3]} and value:
   * <pre>
   * x = [[[[1, 2, 3], [4, 5, 6]],
   *       [[7, 8, 9], [10, 11, 12]]]]
   * </pre>
   * <p>(3) For the following input of shape {@code [4, 2, 2, 1]} and block_size of 2:
   * <pre>
   * x = [[[[1], [3]], [[9], [11]]],
   *      [[[2], [4]], [[10], [12]]],
   *      [[[5], [7]], [[13], [15]]],
   *      [[[6], [8]], [[14], [16]]]]
   * </pre>
   * <p>The output tensor has shape {@code [1, 4, 4, 1]} and value:
   * <pre>
   * x = [[[[1],   [2],  [3],  [4]],
   *      [[5],   [6],  [7],  [8]],
   *      [[9],  [10], [11],  [12]],
   *      [[13], [14], [15],  [16]]]]
   * </pre>
   * <p>(4) For the following input of shape {@code [8, 1, 2, 1]} and block_size of 2:
   * <pre>
   * x = [[[[1], [3]]], [[[9], [11]]], [[[2], [4]]], [[[10], [12]]],
   *      [[[5], [7]]], [[[13], [15]]], [[[6], [8]]], [[[14], [16]]]]
   * </pre>
   * <p>The output tensor has shape {@code [2, 2, 4, 1]} and value:
   * <pre>
   * x = [[[[1], [3]], [[5], [7]]],
   *      [[[2], [4]], [[10], [12]]],
   *      [[[5], [7]], [[13], [15]]],
   *      [[[6], [8]], [[14], [16]]]]
   * </pre>
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
      outputsClass = BatchToSpace.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<BatchToSpace<T>> {
    /**
     * 4-D tensor with shape
     * {@code [batch*block_size*block_size, height_pad/block_size, width_pad/block_size, depth]}. Note that the batch size of the input tensor must be divisible by
     * {@code block_size * block_size}.
     */
    public final Operand<T> input;

    /**
     * 2-D tensor of non-negative integers with shape {@code [2, 2]}. It specifies
     * how many elements to crop from the intermediate result across the spatial
     * dimensions as follows:
     * <pre>
     * crops = [[crop_top, crop_bottom], [crop_left, crop_right]]
     * </pre>
     */
    public final Operand<? extends TNumber> crops;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The blockSize attribute
     */
    public final long blockSize;

    /**
     * The Tidx attribute
     */
    public final DataType Tidx;

    public Inputs(GraphOperation op) {
      super(new BatchToSpace<>(op), op, Arrays.asList("T", "block_size", "Tidx"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      crops = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      blockSize = op.attributes().getAttrInt("block_size");
      Tidx = op.attributes().getAttrType("Tidx");
    }
  }
}
