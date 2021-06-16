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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Calculates the CTC Loss (log probability) for each batch entry.  Also calculates
 * the gradient.  This class performs the softmax operation for you, so inputs
 * should be e.g. linear projections of outputs by an LSTM.
 *
 * @param <T> data type for {@code loss} output
 */
@Operator(
    group = "nn"
)
public final class CtcLoss<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CTCLoss";

  private Output<T> loss;

  private Output<T> gradient;

  private CtcLoss(Operation operation) {
    super(operation);
    int outputIdx = 0;
    loss = operation.output(outputIdx++);
    gradient = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CTCLoss operation.
   *
   * @param scope current scope
   * @param inputs 3-D, shape: {@code (max_time x batch_size x num_classes)}, the logits.
   * @param labelsIndices The indices of a {@code SparseTensor<int32, 2>}.
   * {@code labels_indices(i, :) == [b, t]} means {@code labels_values(i)} stores the id for
   * {@code (batch b, time t)}.
   * @param labelsValues The values (labels) associated with the given batch and time.
   * @param sequenceLength A vector containing sequence lengths (batch).
   * @param options carries optional attribute values
   * @param <T> data type for {@code CTCLoss} output and operands
   * @return a new instance of CtcLoss
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> CtcLoss<T> create(Scope scope, Operand<T> inputs,
      Operand<TInt64> labelsIndices, Operand<TInt32> labelsValues, Operand<TInt32> sequenceLength,
      Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("CtcLoss"));
    opBuilder.addInput(inputs.asOutput());
    opBuilder.addInput(labelsIndices.asOutput());
    opBuilder.addInput(labelsValues.asOutput());
    opBuilder.addInput(sequenceLength.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.preprocessCollapseRepeated != null) {
          opBuilder.setAttr("preprocess_collapse_repeated", opts.preprocessCollapseRepeated);
        }
        if (opts.ctcMergeRepeated != null) {
          opBuilder.setAttr("ctc_merge_repeated", opts.ctcMergeRepeated);
        }
        if (opts.ignoreLongerOutputsThanInputs != null) {
          opBuilder.setAttr("ignore_longer_outputs_than_inputs", opts.ignoreLongerOutputsThanInputs);
        }
      }
    }
    return new CtcLoss<>(opBuilder.build());
  }

  /**
   * Sets the preprocessCollapseRepeated option.
   *
   * @param preprocessCollapseRepeated Scalar, if true then repeated labels are
   * collapsed prior to the CTC calculation.
   * @return this Options instance.
   */
  public static Options preprocessCollapseRepeated(Boolean preprocessCollapseRepeated) {
    return new Options().preprocessCollapseRepeated(preprocessCollapseRepeated);
  }

  /**
   * Sets the ctcMergeRepeated option.
   *
   * @param ctcMergeRepeated Scalar.  If set to false, <em>during</em> CTC calculation
   * repeated non-blank labels will not be merged and are interpreted as
   * individual labels.  This is a simplified version of CTC.
   * @return this Options instance.
   */
  public static Options ctcMergeRepeated(Boolean ctcMergeRepeated) {
    return new Options().ctcMergeRepeated(ctcMergeRepeated);
  }

  /**
   * Sets the ignoreLongerOutputsThanInputs option.
   *
   * @param ignoreLongerOutputsThanInputs Scalar. If set to true, during CTC
   * calculation, items that have longer output sequences than input sequences
   * are skipped: they don't contribute to the loss term and have zero-gradient.
   * @return this Options instance.
   */
  public static Options ignoreLongerOutputsThanInputs(Boolean ignoreLongerOutputsThanInputs) {
    return new Options().ignoreLongerOutputsThanInputs(ignoreLongerOutputsThanInputs);
  }

  /**
   * Gets loss.
   * A vector (batch) containing log-probabilities.
   * @return loss.
   */
  public Output<T> loss() {
    return loss;
  }

  /**
   * Gets gradient.
   * The gradient of {@code loss}.  3-D, shape:
   * {@code (max_time x batch_size x num_classes)}.
   * @return gradient.
   */
  public Output<T> gradient() {
    return gradient;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.CtcLoss}
   */
  public static class Options {
    private Boolean preprocessCollapseRepeated;

    private Boolean ctcMergeRepeated;

    private Boolean ignoreLongerOutputsThanInputs;

    private Options() {
    }

    /**
     * Sets the preprocessCollapseRepeated option.
     *
     * @param preprocessCollapseRepeated Scalar, if true then repeated labels are
     * collapsed prior to the CTC calculation.
     * @return this Options instance.
     */
    public Options preprocessCollapseRepeated(Boolean preprocessCollapseRepeated) {
      this.preprocessCollapseRepeated = preprocessCollapseRepeated;
      return this;
    }

    /**
     * Sets the ctcMergeRepeated option.
     *
     * @param ctcMergeRepeated Scalar.  If set to false, <em>during</em> CTC calculation
     * repeated non-blank labels will not be merged and are interpreted as
     * individual labels.  This is a simplified version of CTC.
     * @return this Options instance.
     */
    public Options ctcMergeRepeated(Boolean ctcMergeRepeated) {
      this.ctcMergeRepeated = ctcMergeRepeated;
      return this;
    }

    /**
     * Sets the ignoreLongerOutputsThanInputs option.
     *
     * @param ignoreLongerOutputsThanInputs Scalar. If set to true, during CTC
     * calculation, items that have longer output sequences than input sequences
     * are skipped: they don't contribute to the loss term and have zero-gradient.
     * @return this Options instance.
     */
    public Options ignoreLongerOutputsThanInputs(Boolean ignoreLongerOutputsThanInputs) {
      this.ignoreLongerOutputsThanInputs = ignoreLongerOutputsThanInputs;
      return this;
    }
  }
}
