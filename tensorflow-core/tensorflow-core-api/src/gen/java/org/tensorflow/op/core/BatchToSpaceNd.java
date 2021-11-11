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
 * BatchToSpace for N-D tensors of type T.
 * This operation reshapes the &quot;batch&quot; dimension 0 into {@code M + 1} dimensions of shape
 * {@code block_shape + [batch]}, interleaves these blocks back into the grid defined by
 * the spatial dimensions {@code [1, ..., M]}, to obtain a result with the same rank as
 * the input.  The spatial dimensions of this intermediate result are then
 * optionally cropped according to {@code crops} to produce the output.  This is the
 * reverse of SpaceToBatch.  See below for a precise description.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = BatchToSpaceNd.OP_NAME,
    inputsClass = BatchToSpaceNd.Inputs.class
)
@Operator
public final class BatchToSpaceNd<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BatchToSpaceND";

  private Output<T> output;

  public BatchToSpaceNd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BatchToSpaceND operation.
   *
   * @param scope current scope
   * @param input N-D with shape {@code input_shape = [batch] + spatial_shape + remaining_shape},
   * where spatial_shape has M dimensions.
   * @param blockShape 1-D with shape {@code [M]}, all values must be &gt;= 1.
   * @param crops 2-D with shape {@code [M, 2]}, all values must be &gt;= 0.
   * {@code crops[i] = [crop_start, crop_end]} specifies the amount to crop from input
   * dimension {@code i + 1}, which corresponds to spatial dimension {@code i}.  It is
   * required that
   * {@code crop_start[i] + crop_end[i] <= block_shape[i] * input_shape[i + 1]}.
   * <p>This operation is equivalent to the following steps:
   * <ol>
   * <li>
   * <p>Reshape {@code input} to {@code reshaped} of shape:
   * [block_shape[0], ..., block_shape[M-1],
   * batch / prod(block_shape),
   * input_shape[1], ..., input_shape[N-1]]
   * </li>
   * <li>
   * <p>Permute dimensions of {@code reshaped} to produce {@code permuted} of shape
   * [batch / prod(block_shape),
   * <p>input_shape[1], block_shape[0],
   * ...,
   * input_shape[M], block_shape[M-1],
   * <p>input_shape[M+1], ..., input_shape[N-1]]
   * </li>
   * <li>
   * <p>Reshape {@code permuted} to produce {@code reshaped_permuted} of shape
   * [batch / prod(block_shape),
   * <p>input_shape[1] * block_shape[0],
   * ...,
   * input_shape[M] * block_shape[M-1],
   * <p>input_shape[M+1],
   * ...,
   * input_shape[N-1]]
   * </li>
   * <li>
   * <p>Crop the start and end of dimensions {@code [1, ..., M]} of
   * {@code reshaped_permuted} according to {@code crops} to produce the output of shape:
   * [batch / prod(block_shape),
   * <p>input_shape[1] * block_shape[0] - crops[0,0] - crops[0,1],
   * ...,
   * input_shape[M] * block_shape[M-1] - crops[M-1,0] - crops[M-1,1],
   * <p>input_shape[M+1], ..., input_shape[N-1]]
   * </li>
   * </ol>
   * <p>Some examples:
   * <p>(1) For the following input of shape {@code [4, 1, 1, 1]}, {@code block_shape = [2, 2]}, and
   * {@code crops = [[0, 0], [0, 0]]}:
   * <pre>
   * [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
   * </pre>
   * <p>The output tensor has shape {@code [1, 2, 2, 1]} and value:
   * <pre>
   * x = [[[[1], [2]], [[3], [4]]]]
   * </pre>
   * <p>(2) For the following input of shape {@code [4, 1, 1, 3]}, {@code block_shape = [2, 2]}, and
   * {@code crops = [[0, 0], [0, 0]]}:
   * <pre>
   * [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
   * </pre>
   * <p>The output tensor has shape {@code [1, 2, 2, 3]} and value:
   * <pre>
   * x = [[[[1, 2, 3], [4, 5, 6]],
   *       [[7, 8, 9], [10, 11, 12]]]]
   * </pre>
   * <p>(3) For the following input of shape {@code [4, 2, 2, 1]}, {@code block_shape = [2, 2]}, and
   * {@code crops = [[0, 0], [0, 0]]}:
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
   * <p>(4) For the following input of shape {@code [8, 1, 3, 1]}, {@code block_shape = [2, 2]}, and
   * {@code crops = [[0, 0], [2, 0]]}:
   * <pre>
   * x = [[[[0], [1], [3]]], [[[0], [9], [11]]],
   *      [[[0], [2], [4]]], [[[0], [10], [12]]],
   *      [[[0], [5], [7]]], [[[0], [13], [15]]],
   *      [[[0], [6], [8]]], [[[0], [14], [16]]]]
   * </pre>
   * <p>The output tensor has shape {@code [2, 2, 4, 1]} and value:
   * <pre>
   * x = [[[[1],   [2],  [3],  [4]],
   *       [[5],   [6],  [7],  [8]]],
   *      [[[9],  [10], [11],  [12]],
   *       [[13], [14], [15],  [16]]]]
   * </pre>
   * @param <T> data type for {@code BatchToSpaceND} output and operands
   * @return a new instance of BatchToSpaceNd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> BatchToSpaceNd<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> blockShape, Operand<? extends TNumber> crops) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BatchToSpaceNd");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(blockShape.asOutput());
    opBuilder.addInput(crops.asOutput());
    return new BatchToSpaceNd<>(opBuilder.build());
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
      outputsClass = BatchToSpaceNd.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<BatchToSpaceNd<T>> {
    /**
     * N-D with shape {@code input_shape = [batch] + spatial_shape + remaining_shape},
     * where spatial_shape has M dimensions.
     */
    public final Operand<T> input;

    /**
     * 1-D with shape {@code [M]}, all values must be &gt;= 1.
     */
    public final Operand<? extends TNumber> blockShape;

    /**
     * 2-D with shape {@code [M, 2]}, all values must be &gt;= 0.
     * {@code crops[i] = [crop_start, crop_end]} specifies the amount to crop from input
     * dimension {@code i + 1}, which corresponds to spatial dimension {@code i}.  It is
     * required that
     * {@code crop_start[i] + crop_end[i] <= block_shape[i] * input_shape[i + 1]}.
     * <p>This operation is equivalent to the following steps:
     * <ol>
     * <li>
     * <p>Reshape {@code input} to {@code reshaped} of shape:
     * [block_shape[0], ..., block_shape[M-1],
     * batch / prod(block_shape),
     * input_shape[1], ..., input_shape[N-1]]
     * </li>
     * <li>
     * <p>Permute dimensions of {@code reshaped} to produce {@code permuted} of shape
     * [batch / prod(block_shape),
     * <p>input_shape[1], block_shape[0],
     * ...,
     * input_shape[M], block_shape[M-1],
     * <p>input_shape[M+1], ..., input_shape[N-1]]
     * </li>
     * <li>
     * <p>Reshape {@code permuted} to produce {@code reshaped_permuted} of shape
     * [batch / prod(block_shape),
     * <p>input_shape[1] * block_shape[0],
     * ...,
     * input_shape[M] * block_shape[M-1],
     * <p>input_shape[M+1],
     * ...,
     * input_shape[N-1]]
     * </li>
     * <li>
     * <p>Crop the start and end of dimensions {@code [1, ..., M]} of
     * {@code reshaped_permuted} according to {@code crops} to produce the output of shape:
     * [batch / prod(block_shape),
     * <p>input_shape[1] * block_shape[0] - crops[0,0] - crops[0,1],
     * ...,
     * input_shape[M] * block_shape[M-1] - crops[M-1,0] - crops[M-1,1],
     * <p>input_shape[M+1], ..., input_shape[N-1]]
     * </li>
     * </ol>
     * <p>Some examples:
     * <p>(1) For the following input of shape {@code [4, 1, 1, 1]}, {@code block_shape = [2, 2]}, and
     * {@code crops = [[0, 0], [0, 0]]}:
     * <pre>
     * [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
     * </pre>
     * <p>The output tensor has shape {@code [1, 2, 2, 1]} and value:
     * <pre>
     * x = [[[[1], [2]], [[3], [4]]]]
     * </pre>
     * <p>(2) For the following input of shape {@code [4, 1, 1, 3]}, {@code block_shape = [2, 2]}, and
     * {@code crops = [[0, 0], [0, 0]]}:
     * <pre>
     * [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
     * </pre>
     * <p>The output tensor has shape {@code [1, 2, 2, 3]} and value:
     * <pre>
     * x = [[[[1, 2, 3], [4, 5, 6]],
     *       [[7, 8, 9], [10, 11, 12]]]]
     * </pre>
     * <p>(3) For the following input of shape {@code [4, 2, 2, 1]}, {@code block_shape = [2, 2]}, and
     * {@code crops = [[0, 0], [0, 0]]}:
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
     * <p>(4) For the following input of shape {@code [8, 1, 3, 1]}, {@code block_shape = [2, 2]}, and
     * {@code crops = [[0, 0], [2, 0]]}:
     * <pre>
     * x = [[[[0], [1], [3]]], [[[0], [9], [11]]],
     *      [[[0], [2], [4]]], [[[0], [10], [12]]],
     *      [[[0], [5], [7]]], [[[0], [13], [15]]],
     *      [[[0], [6], [8]]], [[[0], [14], [16]]]]
     * </pre>
     * <p>The output tensor has shape {@code [2, 2, 4, 1]} and value:
     * <pre>
     * x = [[[[1],   [2],  [3],  [4]],
     *       [[5],   [6],  [7],  [8]]],
     *      [[[9],  [10], [11],  [12]],
     *       [[13], [14], [15],  [16]]]]
     * </pre>
     */
    public final Operand<? extends TNumber> crops;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The TblockShape attribute
     */
    public final DataType TblockShape;

    /**
     * The Tcrops attribute
     */
    public final DataType Tcrops;

    public Inputs(GraphOperation op) {
      super(new BatchToSpaceNd<>(op), op, Arrays.asList("T", "Tblock_shape", "Tcrops"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      blockShape = (Operand<? extends TNumber>) op.input(inputIndex++);
      crops = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      TblockShape = op.attributes().getAttrType("Tblock_shape");
      Tcrops = op.attributes().getAttrType("Tcrops");
    }
  }
}
