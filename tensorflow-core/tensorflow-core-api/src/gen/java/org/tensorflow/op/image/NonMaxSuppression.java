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

package org.tensorflow.op.image;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Greedily selects a subset of bounding boxes in descending order of score,
 * <p>
 * pruning away boxes that have high intersection-over-union (IOU) overlap
 * with previously selected boxes.  Bounding boxes with score less than
 * `score_threshold` are removed.  Bounding boxes are supplied as
 * [y1, x1, y2, x2], where (y1, x1) and (y2, x2) are the coordinates of any
 * diagonal pair of box corners and the coordinates can be provided as normalized
 * (i.e., lying in the interval [0, 1]) or absolute.  Note that this algorithm
 * is agnostic to where the origin is in the coordinate system and more
 * generally is invariant to orthogonal transformations and translations
 * of the coordinate system; thus translating or reflections of the coordinate
 * system result in the same boxes being selected by the algorithm.
 * The output of this operation is a set of integers indexing into the input
 * collection of bounding boxes representing the selected boxes.  The bounding
 * box coordinates corresponding to the selected indices can then be obtained
 * using the `tf.gather operation`.  For example:
 *   selected_indices = tf.image.non_max_suppression_v2(
 *       boxes, scores, max_output_size, iou_threshold, score_threshold)
 *   selected_boxes = tf.gather(boxes, selected_indices)
 * This op also supports a Soft-NMS (with Gaussian weighting) mode (c.f.
 * Bodla et al, https://arxiv.org/abs/1704.04503) where boxes reduce the score
 * of other overlapping boxes instead of directly causing them to be pruned.
 * To enable this Soft-NMS mode, set the `soft_nms_sigma` parameter to be
 * larger than 0.
 * 
 * @param <T> data type for {@code selectedScores()} output
 */
@Operator(group = "image")
public final class NonMaxSuppression<T extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.NonMaxSuppression}
   */
  public static class Options {
    
    /**
     * @param padToMaxOutputSize If true, the output `selected_indices` is padded to be of length
     * `max_output_size`. Defaults to false.
     */
    public Options padToMaxOutputSize(Boolean padToMaxOutputSize) {
      this.padToMaxOutputSize = padToMaxOutputSize;
      return this;
    }
    
    private Boolean padToMaxOutputSize;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new NonMaxSuppression operation.
   * 
   * @param scope current scope
   * @param boxes A 2-D float tensor of shape `[num_boxes, 4]`.
   * @param scores A 1-D float tensor of shape `[num_boxes]` representing a single
   * score corresponding to each box (each row of boxes).
   * @param maxOutputSize A scalar integer tensor representing the maximum number of
   * boxes to be selected by non max suppression.
   * @param iouThreshold A 0-D float tensor representing the threshold for deciding whether
   * boxes overlap too much with respect to IOU.
   * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to remove
   * boxes based on score.
   * @param softNmsSigma A 0-D float tensor representing the sigma parameter for Soft NMS; see Bodla et
   * al (c.f. https://arxiv.org/abs/1704.04503).  When `soft_nms_sigma=0.0` (which
   * is default), we fall back to standard (hard) NMS.
   * @param options carries optional attributes values
   * @return a new instance of NonMaxSuppression
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> NonMaxSuppression<T> create(Scope scope, Operand<T> boxes, Operand<T> scores, Operand<TInt32> maxOutputSize, Operand<T> iouThreshold, Operand<T> scoreThreshold, Operand<T> softNmsSigma, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("NonMaxSuppressionV5", scope.makeOpName("NonMaxSuppression"));
    opBuilder.addInput(boxes.asOutput());
    opBuilder.addInput(scores.asOutput());
    opBuilder.addInput(maxOutputSize.asOutput());
    opBuilder.addInput(iouThreshold.asOutput());
    opBuilder.addInput(scoreThreshold.asOutput());
    opBuilder.addInput(softNmsSigma.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.padToMaxOutputSize != null) {
          opBuilder.setAttr("pad_to_max_output_size", opts.padToMaxOutputSize);
        }
      }
    }
    return new NonMaxSuppression<T>(opBuilder.build());
  }
  
  /**
   * @param padToMaxOutputSize If true, the output `selected_indices` is padded to be of length
   * `max_output_size`. Defaults to false.
   */
  public static Options padToMaxOutputSize(Boolean padToMaxOutputSize) {
    return new Options().padToMaxOutputSize(padToMaxOutputSize);
  }
  
  /**
   * A 1-D integer tensor of shape `[M]` representing the selected
   * indices from the boxes tensor, where `M <= max_output_size`.
   */
  public Output<TInt32> selectedIndices() {
    return selectedIndices;
  }
  
  /**
   * A 1-D float tensor of shape `[M]` representing the corresponding
   * scores for each selected box, where `M <= max_output_size`.  Scores only differ
   * from corresponding input scores when using Soft NMS (i.e. when
   * `soft_nms_sigma>0`)
   */
  public Output<T> selectedScores() {
    return selectedScores;
  }
  
  /**
   * A 0-D integer tensor representing the number of valid elements in
   * `selected_indices`, with the valid elements appearing first.
   */
  public Output<TInt32> validOutputs() {
    return validOutputs;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "NonMaxSuppressionV5";
  
  private Output<TInt32> selectedIndices;
  private Output<T> selectedScores;
  private Output<TInt32> validOutputs;
  
  private NonMaxSuppression(Operation operation) {
    super(operation);
    int outputIdx = 0;
    selectedIndices = operation.output(outputIdx++);
    selectedScores = operation.output(outputIdx++);
    validOutputs = operation.output(outputIdx++);
  }
}
