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
import org.tensorflow.types.family.TNumber;

/**
 * Greedily selects a subset of bounding boxes in descending order of score,
 * pruning away boxes that have high intersection-over-union (IOU) overlap
 * with previously selected boxes.  Bounding boxes with score less than
 * {@code score_threshold} are removed.  Bounding boxes are supplied as
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
 * using the {@code tf.gather operation}.  For example:
 * selected_indices = tf.image.non_max_suppression_v2(
 * boxes, scores, max_output_size, iou_threshold, score_threshold)
 * selected_boxes = tf.gather(boxes, selected_indices)
 * This op also supports a Soft-NMS (with Gaussian weighting) mode (c.f.
 * Bodla et al, https://arxiv.org/abs/1704.04503) where boxes reduce the score
 * of other overlapping boxes instead of directly causing them to be pruned.
 * To enable this Soft-NMS mode, set the {@code soft_nms_sigma} parameter to be
 * larger than 0.
 *
 * @param <T> data type for {@code selected_scores} output
 */
@OpMetadata(
    opType = NonMaxSuppression.OP_NAME,
    inputsClass = NonMaxSuppression.Inputs.class
)
@Operator(
    group = "image"
)
public final class NonMaxSuppression<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "NonMaxSuppressionV5";

  private Output<TInt32> selectedIndices;

  private Output<T> selectedScores;

  private Output<TInt32> validOutputs;

  public NonMaxSuppression(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    selectedIndices = operation.output(outputIdx++);
    selectedScores = operation.output(outputIdx++);
    validOutputs = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new NonMaxSuppressionV5 operation.
   *
   * @param scope current scope
   * @param boxes A 2-D float tensor of shape {@code [num_boxes, 4]}.
   * @param scores A 1-D float tensor of shape {@code [num_boxes]} representing a single
   * score corresponding to each box (each row of boxes).
   * @param maxOutputSize A scalar integer tensor representing the maximum number of
   * boxes to be selected by non max suppression.
   * @param iouThreshold A 0-D float tensor representing the threshold for deciding whether
   * boxes overlap too much with respect to IOU.
   * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to remove
   * boxes based on score.
   * @param softNmsSigma A 0-D float tensor representing the sigma parameter for Soft NMS; see Bodla et
   * al (c.f. https://arxiv.org/abs/1704.04503).  When {@code soft_nms_sigma=0.0} (which
   * is default), we fall back to standard (hard) NMS.
   * @param options carries optional attribute values
   * @param <T> data type for {@code NonMaxSuppressionV5} output and operands
   * @return a new instance of NonMaxSuppression
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> NonMaxSuppression<T> create(Scope scope, Operand<T> boxes,
      Operand<T> scores, Operand<TInt32> maxOutputSize, Operand<T> iouThreshold,
      Operand<T> scoreThreshold, Operand<T> softNmsSigma, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "NonMaxSuppression");
    opBuilder.addInput(boxes.asOutput());
    opBuilder.addInput(scores.asOutput());
    opBuilder.addInput(maxOutputSize.asOutput());
    opBuilder.addInput(iouThreshold.asOutput());
    opBuilder.addInput(scoreThreshold.asOutput());
    opBuilder.addInput(softNmsSigma.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.padToMaxOutputSize != null) {
          opBuilder.setAttr("pad_to_max_output_size", opts.padToMaxOutputSize);
        }
      }
    }
    return new NonMaxSuppression<>(opBuilder.build());
  }

  /**
   * Sets the padToMaxOutputSize option.
   *
   * @param padToMaxOutputSize If true, the output {@code selected_indices} is padded to be of length
   * {@code max_output_size}. Defaults to false.
   * @return this Options instance.
   */
  public static Options padToMaxOutputSize(Boolean padToMaxOutputSize) {
    return new Options().padToMaxOutputSize(padToMaxOutputSize);
  }

  /**
   * Gets selectedIndices.
   * A 1-D integer tensor of shape {@code [M]} representing the selected
   * indices from the boxes tensor, where {@code M <= max_output_size}.
   * @return selectedIndices.
   */
  public Output<TInt32> selectedIndices() {
    return selectedIndices;
  }

  /**
   * Gets selectedScores.
   * A 1-D float tensor of shape {@code [M]} representing the corresponding
   * scores for each selected box, where {@code M <= max_output_size}.  Scores only differ
   * from corresponding input scores when using Soft NMS (i.e. when
   * {@code soft_nms_sigma>0})
   * @return selectedScores.
   */
  public Output<T> selectedScores() {
    return selectedScores;
  }

  /**
   * Gets validOutputs.
   * A 0-D integer tensor representing the number of valid elements in
   * {@code selected_indices}, with the valid elements appearing first.
   * @return validOutputs.
   */
  public Output<TInt32> validOutputs() {
    return validOutputs;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.NonMaxSuppression}
   */
  public static class Options {
    private Boolean padToMaxOutputSize;

    private Options() {
    }

    /**
     * Sets the padToMaxOutputSize option.
     *
     * @param padToMaxOutputSize If true, the output {@code selected_indices} is padded to be of length
     * {@code max_output_size}. Defaults to false.
     * @return this Options instance.
     */
    public Options padToMaxOutputSize(Boolean padToMaxOutputSize) {
      this.padToMaxOutputSize = padToMaxOutputSize;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = NonMaxSuppression.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<NonMaxSuppression<T>> {
    /**
     * A 2-D float tensor of shape {@code [num_boxes, 4]}.
     */
    public final Operand<T> boxes;

    /**
     * A 1-D float tensor of shape {@code [num_boxes]} representing a single
     * score corresponding to each box (each row of boxes).
     */
    public final Operand<T> scores;

    /**
     * A scalar integer tensor representing the maximum number of
     * boxes to be selected by non max suppression.
     */
    public final Operand<TInt32> maxOutputSize;

    /**
     * A 0-D float tensor representing the threshold for deciding whether
     * boxes overlap too much with respect to IOU.
     */
    public final Operand<T> iouThreshold;

    /**
     * A 0-D float tensor representing the threshold for deciding when to remove
     * boxes based on score.
     */
    public final Operand<T> scoreThreshold;

    /**
     * A 0-D float tensor representing the sigma parameter for Soft NMS; see Bodla et
     * al (c.f. https://arxiv.org/abs/1704.04503).  When {@code soft_nms_sigma=0.0} (which
     * is default), we fall back to standard (hard) NMS.
     */
    public final Operand<T> softNmsSigma;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * If true, the output `selected_indices` is padded to be of length
     * `max_output_size`. Defaults to false.
     */
    public final boolean padToMaxOutputSize;

    public Inputs(GraphOperation op) {
      super(new NonMaxSuppression<>(op), op, Arrays.asList("T", "pad_to_max_output_size"));
      int inputIndex = 0;
      boxes = (Operand<T>) op.input(inputIndex++);
      scores = (Operand<T>) op.input(inputIndex++);
      maxOutputSize = (Operand<TInt32>) op.input(inputIndex++);
      iouThreshold = (Operand<T>) op.input(inputIndex++);
      scoreThreshold = (Operand<T>) op.input(inputIndex++);
      softNmsSigma = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      padToMaxOutputSize = op.attributes().getAttrBool("pad_to_max_output_size");
    }
  }
}
