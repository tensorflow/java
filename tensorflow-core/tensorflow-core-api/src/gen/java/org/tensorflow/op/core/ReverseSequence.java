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
 * Reverses variable length slices.
 * This op first slices {@code input} along the dimension {@code batch_dim}, and for each
 * slice {@code i}, reverses the first {@code seq_lengths[i]} elements along
 * the dimension {@code seq_dim}.
 * <p>The elements of {@code seq_lengths} must obey {@code seq_lengths[i] <= input.dims[seq_dim]},
 * and {@code seq_lengths} must be a vector of length {@code input.dims[batch_dim]}.
 * <p>The output slice {@code i} along dimension {@code batch_dim} is then given by input
 * slice {@code i}, with the first {@code seq_lengths[i]} slices along dimension
 * {@code seq_dim} reversed.
 * <p>For example:
 * <pre>
 * # Given this:
 * batch_dim = 0
 * seq_dim = 1
 * input.dims = (4, 8, ...)
 * seq_lengths = [7, 2, 3, 5]
 *
 * # then slices of input are reversed on seq_dim, but only up to seq_lengths:
 * output[0, 0:7, :, ...] = input[0, 7:0:-1, :, ...]
 * output[1, 0:2, :, ...] = input[1, 2:0:-1, :, ...]
 * output[2, 0:3, :, ...] = input[2, 3:0:-1, :, ...]
 * output[3, 0:5, :, ...] = input[3, 5:0:-1, :, ...]
 *
 * # while entries past seq_lens are copied through:
 * output[0, 7:, :, ...] = input[0, 7:, :, ...]
 * output[1, 2:, :, ...] = input[1, 2:, :, ...]
 * output[2, 3:, :, ...] = input[2, 3:, :, ...]
 * output[3, 2:, :, ...] = input[3, 2:, :, ...]
 * </pre>
 * <p>In contrast, if:
 * <pre>
 * # Given this:
 * batch_dim = 2
 * seq_dim = 0
 * input.dims = (8, ?, 4, ...)
 * seq_lengths = [7, 2, 3, 5]
 *
 * # then slices of input are reversed on seq_dim, but only up to seq_lengths:
 * output[0:7, :, 0, :, ...] = input[7:0:-1, :, 0, :, ...]
 * output[0:2, :, 1, :, ...] = input[2:0:-1, :, 1, :, ...]
 * output[0:3, :, 2, :, ...] = input[3:0:-1, :, 2, :, ...]
 * output[0:5, :, 3, :, ...] = input[5:0:-1, :, 3, :, ...]
 *
 * # while entries past seq_lens are copied through:
 * output[7:, :, 0, :, ...] = input[7:, :, 0, :, ...]
 * output[2:, :, 1, :, ...] = input[2:, :, 1, :, ...]
 * output[3:, :, 2, :, ...] = input[3:, :, 2, :, ...]
 * output[2:, :, 3, :, ...] = input[2:, :, 3, :, ...]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = ReverseSequence.OP_NAME,
    inputsClass = ReverseSequence.Inputs.class
)
@Operator
public final class ReverseSequence<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ReverseSequence";

  private Output<T> output;

  public ReverseSequence(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ReverseSequence operation.
   *
   * @param scope current scope
   * @param input The input to reverse.
   * @param seqLengths 1-D with length {@code input.dims(batch_dim)} and
   * {@code max(seq_lengths) <= input.dims(seq_dim)}
   * @param seqDim The dimension which is partially reversed.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ReverseSequence} output and operands
   * @return a new instance of ReverseSequence
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ReverseSequence<T> create(Scope scope, Operand<T> input,
      Operand<? extends TNumber> seqLengths, Long seqDim, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReverseSequence");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(seqLengths.asOutput());
    opBuilder.setAttr("seq_dim", seqDim);
    if (options != null) {
      for (Options opts : options) {
        if (opts.batchDim != null) {
          opBuilder.setAttr("batch_dim", opts.batchDim);
        }
      }
    }
    return new ReverseSequence<>(opBuilder.build());
  }

  /**
   * Sets the batchDim option.
   *
   * @param batchDim The dimension along which reversal is performed.
   * @return this Options instance.
   */
  public static Options batchDim(Long batchDim) {
    return new Options().batchDim(batchDim);
  }

  /**
   * Gets output.
   * The partially reversed input. It has the same shape as {@code input}.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.ReverseSequence}
   */
  public static class Options {
    private Long batchDim;

    private Options() {
    }

    /**
     * Sets the batchDim option.
     *
     * @param batchDim The dimension along which reversal is performed.
     * @return this Options instance.
     */
    public Options batchDim(Long batchDim) {
      this.batchDim = batchDim;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ReverseSequence.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ReverseSequence<T>> {
    /**
     * The input to reverse.
     */
    public final Operand<T> input;

    /**
     * 1-D with length {@code input.dims(batch_dim)} and
     * {@code max(seq_lengths) <= input.dims(seq_dim)}
     */
    public final Operand<? extends TNumber> seqLengths;

    /**
     * The dimension which is partially reversed.
     */
    public final long seqDim;

    /**
     * The dimension along which reversal is performed.
     */
    public final long batchDim;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tlen attribute
     */
    public final DataType Tlen;

    public Inputs(GraphOperation op) {
      super(new ReverseSequence<>(op), op, Arrays.asList("seq_dim", "batch_dim", "T", "Tlen"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      seqLengths = (Operand<? extends TNumber>) op.input(inputIndex++);
      seqDim = op.attributes().getAttrInt("seq_dim");
      batchDim = op.attributes().getAttrInt("batch_dim");
      T = op.attributes().getAttrType("T");
      Tlen = op.attributes().getAttrType("Tlen");
    }
  }
}
