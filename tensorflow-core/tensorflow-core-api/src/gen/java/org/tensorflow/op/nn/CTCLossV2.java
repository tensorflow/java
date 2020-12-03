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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

/**
 * Calculates the CTC Loss (log probability) for each batch entry.  Also calculates
 * <p>
 * the gradient.  This class performs the softmax operation for you, so inputs
 * should be e.g. linear projections of outputs by an LSTM.
 */
public final class CTCLossV2 extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.CTCLossV2}
   */
  public static class Options {
    
    /**
     * @param preprocessCollapseRepeated Scalar, if true then repeated labels are
     * collapsed prior to the CTC calculation.
     */
    public Options preprocessCollapseRepeated(Boolean preprocessCollapseRepeated) {
      this.preprocessCollapseRepeated = preprocessCollapseRepeated;
      return this;
    }
    
    /**
     * @param ctcMergeRepeated Scalar.  If set to false, <i>during</i> CTC calculation
     * repeated non-blank labels will not be merged and are interpreted as
     * individual labels.  This is a simplified version of CTC.
     */
    public Options ctcMergeRepeated(Boolean ctcMergeRepeated) {
      this.ctcMergeRepeated = ctcMergeRepeated;
      return this;
    }
    
    /**
     * @param ignoreLongerOutputsThanInputs Scalar. If set to true, during CTC
     * calculation, items that have longer output sequences than input sequences
     * are skipped: they don't contribute to the loss term and have zero-gradient.
     */
    public Options ignoreLongerOutputsThanInputs(Boolean ignoreLongerOutputsThanInputs) {
      this.ignoreLongerOutputsThanInputs = ignoreLongerOutputsThanInputs;
      return this;
    }
    
    private Boolean preprocessCollapseRepeated;
    private Boolean ctcMergeRepeated;
    private Boolean ignoreLongerOutputsThanInputs;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new CTCLossV2 operation.
   * 
   * @param scope current scope
   * @param inputs 3-D, shape: `(max_time x batch_size x num_classes)`, the logits. Default blank
   * label is 0 rather num_classes - 1.
   * @param labelsIndices The indices of a `SparseTensor<int32, 2>`.
   * `labels_indices(i, :) == [b, t]` means `labels_values(i)` stores the id for
   * `(batch b, time t)`.
   * @param labelsValues The values (labels) associated with the given batch and time.
   * @param sequenceLength A vector containing sequence lengths (batch).
   * @param options carries optional attributes values
   * @return a new instance of CTCLossV2
   */
  @Endpoint(describeByClass = true)
  public static CTCLossV2 create(Scope scope, Operand<TFloat32> inputs, Operand<TInt64> labelsIndices, Operand<TInt32> labelsValues, Operand<TInt32> sequenceLength, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("CTCLossV2", scope.makeOpName("CTCLossV2"));
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
    return new CTCLossV2(opBuilder.build());
  }
  
  /**
   * @param preprocessCollapseRepeated Scalar, if true then repeated labels are
   * collapsed prior to the CTC calculation.
   */
  public static Options preprocessCollapseRepeated(Boolean preprocessCollapseRepeated) {
    return new Options().preprocessCollapseRepeated(preprocessCollapseRepeated);
  }
  
  /**
   * @param ctcMergeRepeated Scalar.  If set to false, <i>during</i> CTC calculation
   * repeated non-blank labels will not be merged and are interpreted as
   * individual labels.  This is a simplified version of CTC.
   */
  public static Options ctcMergeRepeated(Boolean ctcMergeRepeated) {
    return new Options().ctcMergeRepeated(ctcMergeRepeated);
  }
  
  /**
   * @param ignoreLongerOutputsThanInputs Scalar. If set to true, during CTC
   * calculation, items that have longer output sequences than input sequences
   * are skipped: they don't contribute to the loss term and have zero-gradient.
   */
  public static Options ignoreLongerOutputsThanInputs(Boolean ignoreLongerOutputsThanInputs) {
    return new Options().ignoreLongerOutputsThanInputs(ignoreLongerOutputsThanInputs);
  }
  
  /**
   * A vector (batch) containing log-probabilities.
   */
  public Output<TFloat32> loss() {
    return loss;
  }
  
  /**
   * The gradient of `loss`.  3-D, shape:
   * `(max_time x batch_size x num_classes)`.
   */
  public Output<TFloat32> gradient() {
    return gradient;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "CTCLossV2";
  
  private Output<TFloat32> loss;
  private Output<TFloat32> gradient;
  
  private CTCLossV2(Operation operation) {
    super(operation);
    int outputIdx = 0;
    loss = operation.output(outputIdx++);
    gradient = operation.output(outputIdx++);
  }
}
