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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * This op produces Region of Interests from given bounding boxes(bbox_deltas) encoded wrt anchors according to eq.2 in arXiv:1506.01497
 * <pre>
 *   The op selects top `pre_nms_topn` scoring boxes, decodes them with respect to anchors,
 *   applies non-maximal suppression on overlapping boxes with higher than
 *   `nms_threshold` intersection-over-union (iou) value, discarding boxes where shorter
 *   side is less than `min_size`.
 *   Inputs:
 *   `scores`: A 4D tensor of shape [Batch, Height, Width, Num Anchors] containing the scores per anchor at given position
 *   `bbox_deltas`: is a tensor of shape [Batch, Height, Width, 4 x Num Anchors] boxes encoded to each anchor
 *   `anchors`: A 1D tensor of shape [4 x Num Anchors], representing the anchors.
 *   Outputs:
 *   `rois`: output RoIs, a 3D tensor of shape [Batch, post_nms_topn, 4], padded by 0 if less than post_nms_topn candidates found.
 *   `roi_probabilities`: probability scores of each roi in 'rois', a 2D tensor of shape [Batch,post_nms_topn], padded with 0 if needed, sorted by scores.
 * </pre>
 */
@OpMetadata(
    opType = GenerateBoundingBoxProposals.OP_NAME,
    inputsClass = GenerateBoundingBoxProposals.Inputs.class
)
public final class GenerateBoundingBoxProposals extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "GenerateBoundingBoxProposals";

  private Output<TFloat32> rois;

  private Output<TFloat32> roiProbabilities;

  public GenerateBoundingBoxProposals(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    rois = operation.output(outputIdx++);
    roiProbabilities = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new GenerateBoundingBoxProposals operation.
   *
   * @param scope current scope
   * @param scores A 4-D float tensor of shape {@code [num_images, height, width, num_achors]} containing scores of the boxes for given anchors, can be unsorted.
   * @param bboxDeltas A 4-D float tensor of shape {@code [num_images, height, width, 4 x num_anchors]}. encoding boxes with respec to each anchor.
   * Coordinates are given in the form [dy, dx, dh, dw].
   * @param imageInfo A 2-D float tensor of shape {@code [num_images, 5]} containing image information Height, Width, Scale.
   * @param anchors A 2-D float tensor of shape {@code [num_anchors, 4]} describing the anchor boxes. Boxes are formatted in the form [y1, x1, y2, x2].
   * @param nmsThreshold A scalar float tensor for non-maximal-suppression threshold.
   * @param preNmsTopn A scalar int tensor for the number of top scoring boxes to be used as input.
   * @param minSize A scalar float tensor. Any box that has a smaller size than min_size will be discarded.
   * @param options carries optional attribute values
   * @return a new instance of GenerateBoundingBoxProposals
   */
  @Endpoint(
      describeByClass = true
  )
  public static GenerateBoundingBoxProposals create(Scope scope, Operand<TFloat32> scores,
      Operand<TFloat32> bboxDeltas, Operand<TFloat32> imageInfo, Operand<TFloat32> anchors,
      Operand<TFloat32> nmsThreshold, Operand<TInt32> preNmsTopn, Operand<TFloat32> minSize,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "GenerateBoundingBoxProposals");
    opBuilder.addInput(scores.asOutput());
    opBuilder.addInput(bboxDeltas.asOutput());
    opBuilder.addInput(imageInfo.asOutput());
    opBuilder.addInput(anchors.asOutput());
    opBuilder.addInput(nmsThreshold.asOutput());
    opBuilder.addInput(preNmsTopn.asOutput());
    opBuilder.addInput(minSize.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.postNmsTopn != null) {
          opBuilder.setAttr("post_nms_topn", opts.postNmsTopn);
        }
      }
    }
    return new GenerateBoundingBoxProposals(opBuilder.build());
  }

  /**
   * Sets the postNmsTopn option.
   *
   * @param postNmsTopn An integer. Maximum number of rois in the output.
   * @return this Options instance.
   */
  public static Options postNmsTopn(Long postNmsTopn) {
    return new Options().postNmsTopn(postNmsTopn);
  }

  /**
   * Gets rois.
   * A 3-D float tensor of shape {@code [num_images,post_nms_topn,4]} representing the selected
   * region of interest boxes. Sorted in descending order in scores.
   * @return rois.
   */
  public Output<TFloat32> rois() {
    return rois;
  }

  /**
   * Gets roiProbabilities.
   * A 2-D float tensor of shape {@code [num_images, post_nms_topn]} representing the score of the
   * region of interest box in {@code rois} tensor at the same index.
   * @return roiProbabilities.
   */
  public Output<TFloat32> roiProbabilities() {
    return roiProbabilities;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.GenerateBoundingBoxProposals}
   */
  public static class Options {
    private Long postNmsTopn;

    private Options() {
    }

    /**
     * Sets the postNmsTopn option.
     *
     * @param postNmsTopn An integer. Maximum number of rois in the output.
     * @return this Options instance.
     */
    public Options postNmsTopn(Long postNmsTopn) {
      this.postNmsTopn = postNmsTopn;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = GenerateBoundingBoxProposals.class
  )
  public static class Inputs extends RawOpInputs<GenerateBoundingBoxProposals> {
    /**
     * A 4-D float tensor of shape {@code [num_images, height, width, num_achors]} containing scores of the boxes for given anchors, can be unsorted.
     */
    public final Operand<TFloat32> scores;

    /**
     * A 4-D float tensor of shape {@code [num_images, height, width, 4 x num_anchors]}. encoding boxes with respec to each anchor.
     * Coordinates are given in the form [dy, dx, dh, dw].
     */
    public final Operand<TFloat32> bboxDeltas;

    /**
     * A 2-D float tensor of shape {@code [num_images, 5]} containing image information Height, Width, Scale.
     */
    public final Operand<TFloat32> imageInfo;

    /**
     * A 2-D float tensor of shape {@code [num_anchors, 4]} describing the anchor boxes. Boxes are formatted in the form [y1, x1, y2, x2].
     */
    public final Operand<TFloat32> anchors;

    /**
     * A scalar float tensor for non-maximal-suppression threshold.
     */
    public final Operand<TFloat32> nmsThreshold;

    /**
     * A scalar int tensor for the number of top scoring boxes to be used as input.
     */
    public final Operand<TInt32> preNmsTopn;

    /**
     * A scalar float tensor. Any box that has a smaller size than min_size will be discarded.
     */
    public final Operand<TFloat32> minSize;

    /**
     * An integer. Maximum number of rois in the output.
     */
    public final long postNmsTopn;

    public Inputs(GraphOperation op) {
      super(new GenerateBoundingBoxProposals(op), op, Arrays.asList("post_nms_topn"));
      int inputIndex = 0;
      scores = (Operand<TFloat32>) op.input(inputIndex++);
      bboxDeltas = (Operand<TFloat32>) op.input(inputIndex++);
      imageInfo = (Operand<TFloat32>) op.input(inputIndex++);
      anchors = (Operand<TFloat32>) op.input(inputIndex++);
      nmsThreshold = (Operand<TFloat32>) op.input(inputIndex++);
      preNmsTopn = (Operand<TInt32>) op.input(inputIndex++);
      minSize = (Operand<TFloat32>) op.input(inputIndex++);
      postNmsTopn = op.attributes().getAttrInt("post_nms_topn");
    }
  }
}
