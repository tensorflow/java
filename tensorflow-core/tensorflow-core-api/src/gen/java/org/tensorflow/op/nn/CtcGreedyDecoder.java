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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Performs greedy decoding on the logits given in inputs.
 * A note about the attribute merge_repeated: if enabled, when
 * consecutive logits' maximum indices are the same, only the first of
 * these is emitted.  Labeling the blank '*', the sequence &quot;A B B * B B&quot;
 * becomes &quot;A B B&quot; if merge_repeated = True and &quot;A B B B B&quot; if
 * merge_repeated = False.
 * <p>Regardless of the value of merge_repeated, if the maximum index of a given
 * time and batch corresponds to the blank, index {@code (num_classes - 1)}, no new
 * element is emitted.
 *
 * @param <T> data type for {@code log_probability} output
 */
@OpMetadata(
    opType = CtcGreedyDecoder.OP_NAME,
    inputsClass = CtcGreedyDecoder.Inputs.class
)
@Operator(
    group = "nn"
)
public final class CtcGreedyDecoder<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CTCGreedyDecoder";

  private Output<TInt64> decodedIndices;

  private Output<TInt64> decodedValues;

  private Output<TInt64> decodedShape;

  private Output<T> logProbability;

  public CtcGreedyDecoder(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    decodedIndices = operation.output(outputIdx++);
    decodedValues = operation.output(outputIdx++);
    decodedShape = operation.output(outputIdx++);
    logProbability = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CTCGreedyDecoder operation.
   *
   * @param scope current scope
   * @param inputs 3-D, shape: {@code (max_time x batch_size x num_classes)}, the logits.
   * @param sequenceLength A vector containing sequence lengths, size {@code (batch_size)}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code CTCGreedyDecoder} output and operands
   * @return a new instance of CtcGreedyDecoder
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> CtcGreedyDecoder<T> create(Scope scope, Operand<T> inputs,
      Operand<TInt32> sequenceLength, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CtcGreedyDecoder");
    opBuilder.addInput(inputs.asOutput());
    opBuilder.addInput(sequenceLength.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.mergeRepeated != null) {
          opBuilder.setAttr("merge_repeated", opts.mergeRepeated);
        }
        if (opts.blankIndex != null) {
          opBuilder.setAttr("blank_index", opts.blankIndex);
        }
      }
    }
    return new CtcGreedyDecoder<>(opBuilder.build());
  }

  /**
   * Sets the mergeRepeated option.
   *
   * @param mergeRepeated If True, merge repeated classes in output.
   * @return this Options instance.
   */
  public static Options mergeRepeated(Boolean mergeRepeated) {
    return new Options().mergeRepeated(mergeRepeated);
  }

  /**
   * Sets the blankIndex option.
   *
   * @param blankIndex the blankIndex option
   * @return this Options instance.
   */
  public static Options blankIndex(Long blankIndex) {
    return new Options().blankIndex(blankIndex);
  }

  /**
   * Gets decodedIndices.
   * Indices matrix, size {@code (total_decoded_outputs x 2)},
   * of a {@code SparseTensor<int64, 2>}.  The rows store: [batch, time].
   * @return decodedIndices.
   */
  public Output<TInt64> decodedIndices() {
    return decodedIndices;
  }

  /**
   * Gets decodedValues.
   * Values vector, size: {@code (total_decoded_outputs)},
   * of a {@code SparseTensor<int64, 2>}.  The vector stores the decoded classes.
   * @return decodedValues.
   */
  public Output<TInt64> decodedValues() {
    return decodedValues;
  }

  /**
   * Gets decodedShape.
   * Shape vector, size {@code (2)}, of the decoded SparseTensor.
   * Values are: {@code [batch_size, max_decoded_length]}.
   * @return decodedShape.
   */
  public Output<TInt64> decodedShape() {
    return decodedShape;
  }

  /**
   * Gets logProbability.
   * Matrix, size {@code (batch_size x 1)}, containing sequence
   * log-probabilities.
   * @return logProbability.
   */
  public Output<T> logProbability() {
    return logProbability;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.CtcGreedyDecoder}
   */
  public static class Options {
    private Boolean mergeRepeated;

    private Long blankIndex;

    private Options() {
    }

    /**
     * Sets the mergeRepeated option.
     *
     * @param mergeRepeated If True, merge repeated classes in output.
     * @return this Options instance.
     */
    public Options mergeRepeated(Boolean mergeRepeated) {
      this.mergeRepeated = mergeRepeated;
      return this;
    }

    /**
     * Sets the blankIndex option.
     *
     * @param blankIndex the blankIndex option
     * @return this Options instance.
     */
    public Options blankIndex(Long blankIndex) {
      this.blankIndex = blankIndex;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = CtcGreedyDecoder.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<CtcGreedyDecoder<T>> {
    /**
     * 3-D, shape: {@code (max_time x batch_size x num_classes)}, the logits.
     */
    public final Operand<T> inputs;

    /**
     * A vector containing sequence lengths, size {@code (batch_size)}.
     */
    public final Operand<TInt32> sequenceLength;

    /**
     * If True, merge repeated classes in output.
     */
    public final boolean mergeRepeated;

    /**
     * The blankIndex attribute
     */
    public final long blankIndex;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new CtcGreedyDecoder<>(op), op, Arrays.asList("merge_repeated", "blank_index", "T"));
      int inputIndex = 0;
      inputs = (Operand<T>) op.input(inputIndex++);
      sequenceLength = (Operand<TInt32>) op.input(inputIndex++);
      mergeRepeated = op.attributes().getAttrBool("merge_repeated");
      blankIndex = op.attributes().getAttrInt("blank_index");
      T = op.attributes().getAttrType("T");
    }
  }
}
