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

package org.tensorflow.op.linalg;

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
 * Copy a tensor setting everything outside a central band in each innermost matrix to zero.
 * The {@code band} part is computed as follows:
 * Assume {@code input} has {@code k} dimensions {@code [I, J, K, ..., M, N]}, then the output is a
 * tensor with the same shape where
 * <p>{@code band[i, j, k, ..., m, n] = in_band(m, n) * input[i, j, k, ..., m, n]}.
 * <p>The indicator function
 * <p>{@code in_band(m, n) = (num_lower < 0 || (m-n) <= num_lower)) && (num_upper < 0 || (n-m) <= num_upper)}.
 * <p>For example:
 * <pre>
 * # if 'input' is [[ 0,  1,  2, 3]
 * #                [-1,  0,  1, 2]
 * #                [-2, -1,  0, 1]
 * #                [-3, -2, -1, 0]],
 *
 * tf.linalg.band_part(input, 1, -1) ==&gt; [[ 0,  1,  2, 3]
 *                                        [-1,  0,  1, 2]
 *                                        [ 0, -1,  0, 1]
 *                                        [ 0,  0, -1, 0]],
 *
 * tf.linalg.band_part(input, 2, 1) ==&gt; [[ 0,  1,  0, 0]
 *                                       [-1,  0,  1, 0]
 *                                       [-2, -1,  0, 1]
 *                                       [ 0, -2, -1, 0]]
 * </pre>
 * <p>Useful special cases:
 * <pre>
 *  tf.linalg.band_part(input, 0, -1) ==&gt; Upper triangular part.
 *  tf.linalg.band_part(input, -1, 0) ==&gt; Lower triangular part.
 *  tf.linalg.band_part(input, 0, 0) ==&gt; Diagonal.
 * </pre>
 *
 * @param <T> data type for {@code band} output
 */
@OpMetadata(
    opType = BandPart.OP_NAME,
    inputsClass = BandPart.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class BandPart<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MatrixBandPart";

  private Output<T> band;

  public BandPart(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    band = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MatrixBandPart operation.
   *
   * @param scope current scope
   * @param input Rank {@code k} tensor.
   * @param numLower 0-D tensor. Number of subdiagonals to keep. If negative, keep entire
   * lower triangle.
   * @param numUpper 0-D tensor. Number of superdiagonals to keep. If negative, keep
   * entire upper triangle.
   * @param <T> data type for {@code MatrixBandPart} output and operands
   * @param <U> data type for {@code MatrixBandPart} output and operands
   * @return a new instance of BandPart
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TNumber> BandPart<T> create(Scope scope,
      Operand<T> input, Operand<U> numLower, Operand<U> numUpper) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BandPart");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(numLower.asOutput());
    opBuilder.addInput(numUpper.asOutput());
    return new BandPart<>(opBuilder.build());
  }

  /**
   * Gets band.
   * Rank {@code k} tensor of the same shape as input. The extracted banded tensor.
   * @return band.
   */
  public Output<T> band() {
    return band;
  }

  @Override
  public Output<T> asOutput() {
    return band;
  }

  @OpInputsMetadata(
      outputsClass = BandPart.class
  )
  public static class Inputs<T extends TType, U extends TNumber> extends RawOpInputs<BandPart<T>> {
    /**
     * Rank {@code k} tensor.
     */
    public final Operand<T> input;

    /**
     * 0-D tensor. Number of subdiagonals to keep. If negative, keep entire
     * lower triangle.
     */
    public final Operand<U> numLower;

    /**
     * 0-D tensor. Number of superdiagonals to keep. If negative, keep
     * entire upper triangle.
     */
    public final Operand<U> numUpper;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindex attribute
     */
    public final DataType Tindex;

    public Inputs(GraphOperation op) {
      super(new BandPart<>(op), op, Arrays.asList("T", "Tindex"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      numLower = (Operand<U>) op.input(inputIndex++);
      numUpper = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindex = op.attributes().getAttrType("Tindex");
    }
  }
}
