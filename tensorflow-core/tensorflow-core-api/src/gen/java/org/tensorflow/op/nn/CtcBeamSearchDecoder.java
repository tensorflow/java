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
import java.util.List;
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
 * Performs beam search decoding on the logits given in input.
 * <p>
 * A note about the attribute merge_repeated: For the beam search decoder,
 * this means that if consecutive entries in a beam are the same, only
 * the first of these is emitted.  That is, when the top path is "A B B B B",
 * "A B" is returned if merge_repeated = True but "A B B B B" is
 * returned if merge_repeated = False.
 * 
 * @param <T> data type for {@code logProbability()} output
 */
@Operator(group = "nn")
public final class CtcBeamSearchDecoder<T extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.CtcBeamSearchDecoder}
   */
  public static class Options {
    
    /**
     * @param mergeRepeated If true, merge repeated classes in output.
     */
    public Options mergeRepeated(Boolean mergeRepeated) {
      this.mergeRepeated = mergeRepeated;
      return this;
    }
    
    private Boolean mergeRepeated;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new CtcBeamSearchDecoder operation.
   * 
   * @param scope current scope
   * @param inputs 3-D, shape: `(max_time x batch_size x num_classes)`, the logits.
   * @param sequenceLength A vector containing sequence lengths, size `(batch)`.
   * @param beamWidth A scalar >= 0 (beam search beam width).
   * @param topPaths A scalar >= 0, <= beam_width (controls output size).
   * @param options carries optional attributes values
   * @return a new instance of CtcBeamSearchDecoder
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> CtcBeamSearchDecoder<T> create(Scope scope, Operand<T> inputs, Operand<TInt32> sequenceLength, Long beamWidth, Long topPaths, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("CTCBeamSearchDecoder", scope.makeOpName("CtcBeamSearchDecoder"));
    opBuilder.addInput(inputs.asOutput());
    opBuilder.addInput(sequenceLength.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("beam_width", beamWidth);
    opBuilder.setAttr("top_paths", topPaths);
    if (options != null) {
      for (Options opts : options) {
        if (opts.mergeRepeated != null) {
          opBuilder.setAttr("merge_repeated", opts.mergeRepeated);
        }
      }
    }
    return new CtcBeamSearchDecoder<T>(opBuilder.build());
  }
  
  /**
   * @param mergeRepeated If true, merge repeated classes in output.
   */
  public static Options mergeRepeated(Boolean mergeRepeated) {
    return new Options().mergeRepeated(mergeRepeated);
  }
  
  /**
   * A list (length: top_paths) of indices matrices.  Matrix j,
   * size `(total_decoded_outputs[j] x 2)`, has indices of a
   * `SparseTensor<int64, 2>`.  The rows store: [batch, time].
   */
  public List<Output<TInt64>> decodedIndices() {
    return decodedIndices;
  }
  
  /**
   * A list (length: top_paths) of values vectors.  Vector j,
   * size `(length total_decoded_outputs[j])`, has the values of a
   * `SparseTensor<int64, 2>`.  The vector stores the decoded classes for beam j.
   */
  public List<Output<TInt64>> decodedValues() {
    return decodedValues;
  }
  
  /**
   * A list (length: top_paths) of shape vector.  Vector j,
   * size `(2)`, stores the shape of the decoded `SparseTensor[j]`.
   * Its values are: `[batch_size, max_decoded_length[j]]`.
   */
  public List<Output<TInt64>> decodedShape() {
    return decodedShape;
  }
  
  /**
   * A matrix, shaped: `(batch_size x top_paths)`.  The
   * sequence log-probabilities.
   */
  public Output<T> logProbability() {
    return logProbability;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "CTCBeamSearchDecoder";
  
  private List<Output<TInt64>> decodedIndices;
  private List<Output<TInt64>> decodedValues;
  private List<Output<TInt64>> decodedShape;
  private Output<T> logProbability;
  
  @SuppressWarnings("unchecked")
  private CtcBeamSearchDecoder(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int decodedIndicesLength = operation.outputListLength("decoded_indices");
    decodedIndices = Arrays.asList((Output<TInt64>[])operation.outputList(outputIdx, decodedIndicesLength));
    outputIdx += decodedIndicesLength;
    int decodedValuesLength = operation.outputListLength("decoded_values");
    decodedValues = Arrays.asList((Output<TInt64>[])operation.outputList(outputIdx, decodedValuesLength));
    outputIdx += decodedValuesLength;
    int decodedShapeLength = operation.outputListLength("decoded_shape");
    decodedShape = Arrays.asList((Output<TInt64>[])operation.outputList(outputIdx, decodedShapeLength));
    outputIdx += decodedShapeLength;
    logProbability = operation.output(outputIdx++);
  }
}
