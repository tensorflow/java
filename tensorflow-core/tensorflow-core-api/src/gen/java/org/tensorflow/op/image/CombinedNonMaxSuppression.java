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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * Greedily selects a subset of bounding boxes in descending order of score,
 * This operation performs non_max_suppression on the inputs per batch, across
 * all classes.
 * Prunes away boxes that have high intersection-over-union (IOU) overlap
 * with previously selected boxes.  Bounding boxes are supplied as
 * [y1, x1, y2, x2], where (y1, x1) and (y2, x2) are the coordinates of any
 * diagonal pair of box corners and the coordinates can be provided as normalized
 * (i.e., lying in the interval [0, 1]) or absolute.  Note that this algorithm
 * is agnostic to where the origin is in the coordinate system. Also note that
 * this algorithm is invariant to orthogonal transformations and translations
 * of the coordinate system; thus translating or reflections of the coordinate
 * system result in the same boxes being selected by the algorithm.
 * The output of this operation is the final boxes, scores and classes tensor
 * returned after performing non_max_suppression.
 */
@OpMetadata(
    opType = CombinedNonMaxSuppression.OP_NAME,
    inputsClass = CombinedNonMaxSuppression.Inputs.class
)
@Operator(
    group = "image"
)
public final class CombinedNonMaxSuppression extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CombinedNonMaxSuppression";

  private Output<TFloat32> nmsedBoxes;

  private Output<TFloat32> nmsedScores;

  private Output<TFloat32> nmsedClasses;

  private Output<TInt32> validDetections;

  public CombinedNonMaxSuppression(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    nmsedBoxes = operation.output(outputIdx++);
    nmsedScores = operation.output(outputIdx++);
    nmsedClasses = operation.output(outputIdx++);
    validDetections = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CombinedNonMaxSuppression operation.
   *
   * @param scope current scope
   * @param boxes A 4-D float tensor of shape {@code [batch_size, num_boxes, q, 4]}. If {@code q} is 1 then
   * same boxes are used for all classes otherwise, if {@code q} is equal to number of
   * classes, class-specific boxes are used.
   * @param scores A 3-D float tensor of shape {@code [batch_size, num_boxes, num_classes]}
   * representing a single score corresponding to each box (each row of boxes).
   * @param maxOutputSizePerClass A scalar integer tensor representing the maximum number of
   * boxes to be selected by non max suppression per class
   * @param maxTotalSize An int32 scalar representing the maximum number of boxes retained over all
   * classes. Note that setting this value to a large number may result in OOM error
   * depending on the system workload.
   * @param iouThreshold A 0-D float tensor representing the threshold for deciding whether
   * boxes overlap too much with respect to IOU.
   * @param scoreThreshold A 0-D float tensor representing the threshold for deciding when to remove
   * boxes based on score.
   * @param options carries optional attribute values
   * @return a new instance of CombinedNonMaxSuppression
   */
  @Endpoint(
      describeByClass = true
  )
  public static CombinedNonMaxSuppression create(Scope scope, Operand<TFloat32> boxes,
      Operand<TFloat32> scores, Operand<TInt32> maxOutputSizePerClass, Operand<TInt32> maxTotalSize,
      Operand<TFloat32> iouThreshold, Operand<TFloat32> scoreThreshold, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CombinedNonMaxSuppression");
    opBuilder.addInput(boxes.asOutput());
    opBuilder.addInput(scores.asOutput());
    opBuilder.addInput(maxOutputSizePerClass.asOutput());
    opBuilder.addInput(maxTotalSize.asOutput());
    opBuilder.addInput(iouThreshold.asOutput());
    opBuilder.addInput(scoreThreshold.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.padPerClass != null) {
          opBuilder.setAttr("pad_per_class", opts.padPerClass);
        }
        if (opts.clipBoxes != null) {
          opBuilder.setAttr("clip_boxes", opts.clipBoxes);
        }
      }
    }
    return new CombinedNonMaxSuppression(opBuilder.build());
  }

  /**
   * Sets the padPerClass option.
   *
   * @param padPerClass If false, the output nmsed boxes, scores and classes
   * are padded/clipped to {@code max_total_size}. If true, the
   * output nmsed boxes, scores and classes are padded to be of length
   * {@code max_size_per_class}*{@code num_classes}, unless it exceeds {@code max_total_size} in
   * which case it is clipped to {@code max_total_size}. Defaults to false.
   * @return this Options instance.
   */
  public static Options padPerClass(Boolean padPerClass) {
    return new Options().padPerClass(padPerClass);
  }

  /**
   * Sets the clipBoxes option.
   *
   * @param clipBoxes If true, assume the box coordinates are between [0, 1] and clip the output boxes
   * if they fall beyond [0, 1]. If false, do not do clipping and output the box
   * coordinates as it is.
   * @return this Options instance.
   */
  public static Options clipBoxes(Boolean clipBoxes) {
    return new Options().clipBoxes(clipBoxes);
  }

  /**
   * Gets nmsedBoxes.
   * A [batch_size, max_detections, 4] float32 tensor
   * containing the non-max suppressed boxes.
   * @return nmsedBoxes.
   */
  public Output<TFloat32> nmsedBoxes() {
    return nmsedBoxes;
  }

  /**
   * Gets nmsedScores.
   * A [batch_size, max_detections] float32 tensor
   * containing the scores for the boxes.
   * @return nmsedScores.
   */
  public Output<TFloat32> nmsedScores() {
    return nmsedScores;
  }

  /**
   * Gets nmsedClasses.
   * A [batch_size, max_detections] float32 tensor
   * containing the classes for the boxes.
   * @return nmsedClasses.
   */
  public Output<TFloat32> nmsedClasses() {
    return nmsedClasses;
  }

  /**
   * Gets validDetections.
   * A [batch_size] int32 tensor indicating the number of
   * valid detections per batch item. Only the top num_detections[i] entries in
   * nms_boxes[i], nms_scores[i] and nms_class[i] are valid. The rest of the
   * entries are zero paddings.
   * @return validDetections.
   */
  public Output<TInt32> validDetections() {
    return validDetections;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.CombinedNonMaxSuppression}
   */
  public static class Options {
    private Boolean padPerClass;

    private Boolean clipBoxes;

    private Options() {
    }

    /**
     * Sets the padPerClass option.
     *
     * @param padPerClass If false, the output nmsed boxes, scores and classes
     * are padded/clipped to {@code max_total_size}. If true, the
     * output nmsed boxes, scores and classes are padded to be of length
     * {@code max_size_per_class}*{@code num_classes}, unless it exceeds {@code max_total_size} in
     * which case it is clipped to {@code max_total_size}. Defaults to false.
     * @return this Options instance.
     */
    public Options padPerClass(Boolean padPerClass) {
      this.padPerClass = padPerClass;
      return this;
    }

    /**
     * Sets the clipBoxes option.
     *
     * @param clipBoxes If true, assume the box coordinates are between [0, 1] and clip the output boxes
     * if they fall beyond [0, 1]. If false, do not do clipping and output the box
     * coordinates as it is.
     * @return this Options instance.
     */
    public Options clipBoxes(Boolean clipBoxes) {
      this.clipBoxes = clipBoxes;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = CombinedNonMaxSuppression.class
  )
  public static class Inputs extends RawOpInputs<CombinedNonMaxSuppression> {
    /**
     * A 4-D float tensor of shape {@code [batch_size, num_boxes, q, 4]}. If {@code q} is 1 then
     * same boxes are used for all classes otherwise, if {@code q} is equal to number of
     * classes, class-specific boxes are used.
     */
    public final Operand<TFloat32> boxes;

    /**
     * A 3-D float tensor of shape {@code [batch_size, num_boxes, num_classes]}
     * representing a single score corresponding to each box (each row of boxes).
     */
    public final Operand<TFloat32> scores;

    /**
     * A scalar integer tensor representing the maximum number of
     * boxes to be selected by non max suppression per class
     */
    public final Operand<TInt32> maxOutputSizePerClass;

    /**
     * An int32 scalar representing the maximum number of boxes retained over all
     * classes. Note that setting this value to a large number may result in OOM error
     * depending on the system workload.
     */
    public final Operand<TInt32> maxTotalSize;

    /**
     * A 0-D float tensor representing the threshold for deciding whether
     * boxes overlap too much with respect to IOU.
     */
    public final Operand<TFloat32> iouThreshold;

    /**
     * A 0-D float tensor representing the threshold for deciding when to remove
     * boxes based on score.
     */
    public final Operand<TFloat32> scoreThreshold;

    /**
     * If false, the output nmsed boxes, scores and classes
     * are padded/clipped to `max_total_size`. If true, the
     * output nmsed boxes, scores and classes are padded to be of length
     * `max_size_per_class`*`num_classes`, unless it exceeds `max_total_size` in
     * which case it is clipped to `max_total_size`. Defaults to false.
     */
    public final boolean padPerClass;

    /**
     * If true, assume the box coordinates are between [0, 1] and clip the output boxes
     * if they fall beyond [0, 1]. If false, do not do clipping and output the box
     * coordinates as it is.
     */
    public final boolean clipBoxes;

    public Inputs(GraphOperation op) {
      super(new CombinedNonMaxSuppression(op), op, Arrays.asList("pad_per_class", "clip_boxes"));
      int inputIndex = 0;
      boxes = (Operand<TFloat32>) op.input(inputIndex++);
      scores = (Operand<TFloat32>) op.input(inputIndex++);
      maxOutputSizePerClass = (Operand<TInt32>) op.input(inputIndex++);
      maxTotalSize = (Operand<TInt32>) op.input(inputIndex++);
      iouThreshold = (Operand<TFloat32>) op.input(inputIndex++);
      scoreThreshold = (Operand<TFloat32>) op.input(inputIndex++);
      padPerClass = op.attributes().getAttrBool("pad_per_class");
      clipBoxes = op.attributes().getAttrBool("clip_boxes");
    }
  }
}
