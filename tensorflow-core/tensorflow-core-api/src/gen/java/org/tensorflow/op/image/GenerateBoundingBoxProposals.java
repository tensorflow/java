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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * This op produces Region of Interests from given bounding boxes(bbox_deltas) encoded wrt anchors according to eq.2 in arXiv:1506.01497
 * <p>
 *       The op selects top `pre_nms_topn` scoring boxes, decodes them with respect to anchors,
 *       applies non-maximal suppression on overlapping boxes with higher than
 *       `nms_threshold` intersection-over-union (iou) value, discarding boxes where shorter
 *       side is less than `min_size`.
 *       Inputs:
 *       `scores`: A 4D tensor of shape [Batch, Height, Width, Num Anchors] containing the scores per anchor at given position
 *       `bbox_deltas`: is a tensor of shape [Batch, Height, Width, 4 x Num Anchors] boxes encoded to each anchor
 *       `anchors`: A 1D tensor of shape [4 x Num Anchors], representing the anchors.
 *       Outputs:
 *       `rois`: output RoIs, a 3D tensor of shape [Batch, post_nms_topn, 4], padded by 0 if less than post_nms_topn candidates found.
 *       `roi_probabilities`: probability scores of each roi in 'rois', a 2D tensor of shape [Batch,post_nms_topn], padded with 0 if needed, sorted by scores.
 */
public final class GenerateBoundingBoxProposals extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.GenerateBoundingBoxProposals}
   */
  public static class Options {
    
    /**
     * @param postNmsTopn An integer. Maximum number of rois in the output.
     */
    public Options postNmsTopn(Long postNmsTopn) {
      this.postNmsTopn = postNmsTopn;
      return this;
    }
    
    private Long postNmsTopn;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new GenerateBoundingBoxProposals operation.
   * 
   * @param scope current scope
   * @param scores A 4-D float tensor of shape `[num_images, height, width, num_achors]` containing scores of the boxes for given anchors, can be unsorted.
   * @param bboxDeltas A 4-D float tensor of shape `[num_images, height, width, 4 x num_anchors]`. encoding boxes with respec to each anchor.
   * Coordinates are given in the form [dy, dx, dh, dw].
   * @param imageInfo A 2-D float tensor of shape `[num_images, 5]` containing image information Height, Width, Scale.
   * @param anchors A 2-D float tensor of shape `[num_anchors, 4]` describing the anchor boxes. Boxes are formatted in the form [y1, x1, y2, x2].
   * @param nmsThreshold A scalar float tensor for non-maximal-suppression threshold.
   * @param preNmsTopn A scalar int tensor for the number of top scoring boxes to be used as input.
   * @param minSize A scalar float tensor. Any box that has a smaller size than min_size will be discarded.
   * @param options carries optional attributes values
   * @return a new instance of GenerateBoundingBoxProposals
   */
  @Endpoint(describeByClass = true)
  public static GenerateBoundingBoxProposals create(Scope scope, Operand<TFloat32> scores, Operand<TFloat32> bboxDeltas, Operand<TFloat32> imageInfo, Operand<TFloat32> anchors, Operand<TFloat32> nmsThreshold, Operand<TInt32> preNmsTopn, Operand<TFloat32> minSize, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("GenerateBoundingBoxProposals", scope.makeOpName("GenerateBoundingBoxProposals"));
    opBuilder.addInput(scores.asOutput());
    opBuilder.addInput(bboxDeltas.asOutput());
    opBuilder.addInput(imageInfo.asOutput());
    opBuilder.addInput(anchors.asOutput());
    opBuilder.addInput(nmsThreshold.asOutput());
    opBuilder.addInput(preNmsTopn.asOutput());
    opBuilder.addInput(minSize.asOutput());
    opBuilder = scope.apply(opBuilder);
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
   * @param postNmsTopn An integer. Maximum number of rois in the output.
   */
  public static Options postNmsTopn(Long postNmsTopn) {
    return new Options().postNmsTopn(postNmsTopn);
  }
  
  /**
   * A 3-D float tensor of shape `[num_images,post_nms_topn,4]` representing the selected
   * region of interest boxes. Sorted in descending order in scores.
   */
  public Output<TFloat32> rois() {
    return rois;
  }
  
  /**
   * A 2-D float tensor of shape `[num_images, post_nms_topn]` representing the score of the
   * region of interest box in `rois` tensor at the same index.
   */
  public Output<TFloat32> roiProbabilities() {
    return roiProbabilities;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "GenerateBoundingBoxProposals";
  
  private Output<TFloat32> rois;
  private Output<TFloat32> roiProbabilities;
  
  private GenerateBoundingBoxProposals(Operation operation) {
    super(operation);
    int outputIdx = 0;
    rois = operation.output(outputIdx++);
    roiProbabilities = operation.output(outputIdx++);
  }
}
