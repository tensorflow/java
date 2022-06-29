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
 * SpaceToBatch for N-D tensors of type T.
 * This operation divides &quot;spatial&quot; dimensions {@code [1, ..., M]} of the input into a
 * grid of blocks of shape {@code block_shape}, and interleaves these blocks with the
 * &quot;batch&quot; dimension (0) such that in the output, the spatial dimensions
 * {@code [1, ..., M]} correspond to the position within the grid, and the batch
 * dimension combines both the position within a spatial block and the original
 * batch position.  Prior to division into blocks, the spatial dimensions of the
 * input are optionally zero padded according to {@code paddings}. See below for a
 * precise description.
 * <p>This operation is equivalent to the following steps:
 * <ol>
 * <li>
 * <p>Zero-pad the start and end of dimensions {@code [1, ..., M]} of the
 * input according to {@code paddings} to produce {@code padded} of shape {@code padded_shape}.
 * </li>
 * <li>
 * <p>Reshape {@code padded} to {@code reshaped_padded} of shape:
 * <p>[batch] +
 * [padded_shape[1] / block_shape[0],
 * block_shape[0],
 * ...,
 * padded_shape[M] / block_shape[M-1],
 * block_shape[M-1]] +
 * remaining_shape
 * </li>
 * <li>
 * <p>Permute dimensions of {@code reshaped_padded} to produce
 * {@code permuted_reshaped_padded} of shape:
 * <p>block_shape +
 * [batch] +
 * [padded_shape[1] / block_shape[0],
 * ...,
 * padded_shape[M] / block_shape[M-1]] +
 * remaining_shape
 * </li>
 * <li>
 * <p>Reshape {@code permuted_reshaped_padded} to flatten {@code block_shape} into the batch
 * dimension, producing an output tensor of shape:
 * <p>[batch * prod(block_shape)] +
 * [padded_shape[1] / block_shape[0],
 * ...,
 * padded_shape[M] / block_shape[M-1]] +
 * remaining_shape
 * </li>
 * </ol>
 * <p>Some examples:
 * <p>(1) For the following input of shape {@code [1, 2, 2, 1]}, {@code block_shape = [2, 2]}, and
 * {@code paddings = [[0, 0], [0, 0]]}:
 * <pre>
 * x = [[[[1], [2]], [[3], [4]]]]
 * </pre>
 * <p>The output tensor has shape {@code [4, 1, 1, 1]} and value:
 * <pre>
 * [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
 * </pre>
 * <p>(2) For the following input of shape {@code [1, 2, 2, 3]}, {@code block_shape = [2, 2]}, and
 * {@code paddings = [[0, 0], [0, 0]]}:
 * <pre>
 * x = [[[[1, 2, 3], [4, 5, 6]],
 *       [[7, 8, 9], [10, 11, 12]]]]
 * </pre>
 * <p>The output tensor has shape {@code [4, 1, 1, 3]} and value:
 * <pre>
 * [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
 * </pre>
 * <p>(3) For the following input of shape {@code [1, 4, 4, 1]}, {@code block_shape = [2, 2]}, and
 * {@code paddings = [[0, 0], [0, 0]]}:
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
 * <p>(4) For the following input of shape {@code [2, 2, 4, 1]}, block_shape = {@code [2, 2]}, and
 * paddings = {@code [[0, 0], [2, 0]]}:
 * <pre>
 * x = [[[[1],   [2],  [3],  [4]],
 *       [[5],   [6],  [7],  [8]]],
 *      [[[9],  [10], [11],  [12]],
 *       [[13], [14], [15],  [16]]]]
 * </pre>
 * <p>The output tensor has shape {@code [8, 1, 3, 1]} and value:
 * <pre>
 * x = [[[[0], [1], [3]]], [[[0], [9], [11]]],
 *      [[[0], [2], [4]]], [[[0], [10], [12]]],
 *      [[[0], [5], [7]]], [[[0], [13], [15]]],
 *      [[[0], [6], [8]]], [[[0], [14], [16]]]]
 * </pre>
 * <p>Among others, this operation is useful for reducing atrous convolution into
 * regular convolution.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = SpaceToBatchNd.OP_NAME,
    inputsClass = SpaceToBatchNd.Inputs.class
)
@Operator
public final class SpaceToBatchNd<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SpaceToBatchND";

  private Output<T> output;

  public SpaceToBatchNd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SpaceToBatchND operation.
   *
   * @param scope current scope
   * @param input N-D with shape {@code input_shape = [batch] + spatial_shape + remaining_shape},
   * where spatial_shape has {@code M} dimensions.
   * @param blockShape 1-D with shape {@code [M]}, all values must be &gt;= 1.
   * @param paddings 2-D with shape {@code [M, 2]}, all values must be &gt;= 0.
   * {@code paddings[i] = [pad_start, pad_end]} specifies the padding for input dimension
   * {@code i + 1}, which corresponds to spatial dimension {@code i}.  It is required that
   * {@code block_shape[i]} divides {@code input_shape[i + 1] + pad_start + pad_end}.
   * @param <T> data type for {@code SpaceToBatchND} output and operands
   * @return a new instance of SpaceToBatchNd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SpaceToBatchNd<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> blockShape, Operand<? extends TNumber> paddings) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SpaceToBatchNd");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(blockShape.asOutput());
    opBuilder.addInput(paddings.asOutput());
    return new SpaceToBatchNd<>(opBuilder.build());
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
      outputsClass = SpaceToBatchNd.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SpaceToBatchNd<T>> {
    /**
     * N-D with shape {@code input_shape = [batch] + spatial_shape + remaining_shape},
     * where spatial_shape has {@code M} dimensions.
     */
    public final Operand<T> input;

    /**
     * 1-D with shape {@code [M]}, all values must be &gt;= 1.
     */
    public final Operand<? extends TNumber> blockShape;

    /**
     * 2-D with shape {@code [M, 2]}, all values must be &gt;= 0.
     * {@code paddings[i] = [pad_start, pad_end]} specifies the padding for input dimension
     * {@code i + 1}, which corresponds to spatial dimension {@code i}.  It is required that
     * {@code block_shape[i]} divides {@code input_shape[i + 1] + pad_start + pad_end}.
     */
    public final Operand<? extends TNumber> paddings;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The TblockShape attribute
     */
    public final DataType TblockShape;

    /**
     * The Tpaddings attribute
     */
    public final DataType Tpaddings;

    public Inputs(GraphOperation op) {
      super(new SpaceToBatchNd<>(op), op, Arrays.asList("T", "Tblock_shape", "Tpaddings"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      blockShape = (Operand<? extends TNumber>) op.input(inputIndex++);
      paddings = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      TblockShape = op.attributes().getAttrType("Tblock_shape");
      Tpaddings = op.attributes().getAttrType("Tpaddings");
    }
  }
}
