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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Generate a randomly distorted bounding box for an image deterministically.
 * <p>
 * Bounding box annotations are often supplied in addition to ground-truth labels
 * in image recognition or object localization tasks. A common technique for
 * training such a system is to randomly distort an image while preserving its
 * content, i.e. <i>data augmentation</i>. This Op, given the same `seed`,
 * deterministically outputs a randomly distorted localization of an object, i.e.
 * bounding box, given an `image_size`, `bounding_boxes` and a series of
 * constraints.
 * <p>
 * The output of this Op is a single bounding box that may be used to crop the
 * original image. The output is returned as 3 tensors: `begin`, `size` and
 * `bboxes`. The first 2 tensors can be fed directly into `tf.slice` to crop the
 * image. The latter may be supplied to `tf.image.draw_bounding_boxes` to visualize
 * what the bounding box looks like.
 * <p>
 * Bounding boxes are supplied and returned as `[y_min, x_min, y_max, x_max]`. The
 * bounding box coordinates are floats in `[0.0, 1.0]` relative to the width and
 * the height of the underlying image.
 * <p>
 * The output of this Op is guaranteed to be the same given the same `seed` and is
 * independent of how many times the function is called, and independent of global
 * seed settings (e.g. `tf.random.set_seed`).
 * <p>
 * Example usage:
 * <p>
 * >>> image = np.array([[[1], [2], [3]], [[4], [5], [6]], [[7], [8], [9]]])
 * >>> bbox = tf.constant(
 * ...   [0.0, 0.0, 1.0, 1.0], dtype=tf.float32, shape=[1, 1, 4])
 * >>> seed = (1, 2)
 * >>> # Generate a single distorted bounding box.
 * >>> bbox_begin, bbox_size, bbox_draw = (
 * ...   tf.image.stateless_sample_distorted_bounding_box(
 * ...     tf.shape(image), bounding_boxes=bbox, seed=seed))
 * >>> # Employ the bounding box to distort the image.
 * >>> tf.slice(image, bbox_begin, bbox_size)
 * <tf.Tensor: shape=(2, 2, 1), dtype=int64, numpy=
 * array([[[1],
 *         [2]],
 *        [[4],
 *         [5]]])>
 * >>> # Draw the bounding box in an image summary.
 * >>> colors = np.array([[1.0, 0.0, 0.0], [0.0, 0.0, 1.0]])
 * >>> tf.image.draw_bounding_boxes(
 * ...   tf.expand_dims(tf.cast(image, tf.float32),0), bbox_draw, colors)
 * <tf.Tensor: shape=(1, 3, 3, 1), dtype=float32, numpy=
 * array([[[[1.],
 *          [1.],
 *          [3.]],
 *         [[1.],
 *          [1.],
 *          [6.]],
 *         [[7.],
 *          [8.],
 *          [9.]]]], dtype=float32)>
 * <p>
 * Note that if no bounding box information is available, setting
 * `use_image_if_no_bounding_boxes = true` will assume there is a single implicit
 * bounding box covering the whole image. If `use_image_if_no_bounding_boxes` is
 * false and no bounding boxes are supplied, an error is raised.
 * 
 * @param <T> data type for {@code begin()} output
 */
@Operator(group = "image")
public final class StatelessSampleDistortedBoundingBox<T extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.StatelessSampleDistortedBoundingBox}
   */
  public static class Options {
    
    /**
     * @param aspectRatioRange The cropped area of the image must have an aspect ratio =
     * width / height within this range.
     */
    public Options aspectRatioRange(List<Float> aspectRatioRange) {
      this.aspectRatioRange = aspectRatioRange;
      return this;
    }
    
    /**
     * @param areaRange The cropped area of the image must contain a fraction of the
     * supplied image within this range.
     */
    public Options areaRange(List<Float> areaRange) {
      this.areaRange = areaRange;
      return this;
    }
    
    /**
     * @param maxAttempts Number of attempts at generating a cropped region of the image
     * of the specified constraints. After `max_attempts` failures, return the entire
     * image.
     */
    public Options maxAttempts(Long maxAttempts) {
      this.maxAttempts = maxAttempts;
      return this;
    }
    
    /**
     * @param useImageIfNoBoundingBoxes Controls behavior if no bounding boxes supplied.
     * If true, assume an implicit bounding box covering the whole input. If false,
     * raise an error.
     */
    public Options useImageIfNoBoundingBoxes(Boolean useImageIfNoBoundingBoxes) {
      this.useImageIfNoBoundingBoxes = useImageIfNoBoundingBoxes;
      return this;
    }
    
    private List<Float> aspectRatioRange;
    private List<Float> areaRange;
    private Long maxAttempts;
    private Boolean useImageIfNoBoundingBoxes;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new StatelessSampleDistortedBoundingBox operation.
   * 
   * @param scope current scope
   * @param imageSize 1-D, containing `[height, width, channels]`.
   * @param boundingBoxes 3-D with shape `[batch, N, 4]` describing the N bounding boxes
   * associated with the image.
   * @param minObjectCovered The cropped area of the image must contain at least this
   * fraction of any bounding box supplied. The value of this parameter should be
   * non-negative. In the case of 0, the cropped area does not need to overlap
   * any of the bounding boxes supplied.
   * @param seed 1-D with shape `[2]`. The seed to the random number generator. Must have dtype
   * `int32` or `int64`. (When using XLA, only `int32` is allowed.)
   * @param options carries optional attributes values
   * @return a new instance of StatelessSampleDistortedBoundingBox
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> StatelessSampleDistortedBoundingBox<T> create(Scope scope, Operand<T> imageSize, Operand<TFloat32> boundingBoxes, Operand<TFloat32> minObjectCovered, Operand<? extends TNumber> seed, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("StatelessSampleDistortedBoundingBox", scope.makeOpName("StatelessSampleDistortedBoundingBox"));
    opBuilder.addInput(imageSize.asOutput());
    opBuilder.addInput(boundingBoxes.asOutput());
    opBuilder.addInput(minObjectCovered.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.aspectRatioRange != null) {
          float[] aspectRatioRangeArray = new float[opts.aspectRatioRange.size()];
          for (int i = 0; i < aspectRatioRangeArray.length; ++i) {
            aspectRatioRangeArray[i] = opts.aspectRatioRange.get(i);
          }
          opBuilder.setAttr("aspect_ratio_range", aspectRatioRangeArray);
        }
        if (opts.areaRange != null) {
          float[] areaRangeArray = new float[opts.areaRange.size()];
          for (int i = 0; i < areaRangeArray.length; ++i) {
            areaRangeArray[i] = opts.areaRange.get(i);
          }
          opBuilder.setAttr("area_range", areaRangeArray);
        }
        if (opts.maxAttempts != null) {
          opBuilder.setAttr("max_attempts", opts.maxAttempts);
        }
        if (opts.useImageIfNoBoundingBoxes != null) {
          opBuilder.setAttr("use_image_if_no_bounding_boxes", opts.useImageIfNoBoundingBoxes);
        }
      }
    }
    return new StatelessSampleDistortedBoundingBox<T>(opBuilder.build());
  }
  
  /**
   * @param aspectRatioRange The cropped area of the image must have an aspect ratio =
   * width / height within this range.
   */
  public static Options aspectRatioRange(List<Float> aspectRatioRange) {
    return new Options().aspectRatioRange(aspectRatioRange);
  }
  
  /**
   * @param areaRange The cropped area of the image must contain a fraction of the
   * supplied image within this range.
   */
  public static Options areaRange(List<Float> areaRange) {
    return new Options().areaRange(areaRange);
  }
  
  /**
   * @param maxAttempts Number of attempts at generating a cropped region of the image
   * of the specified constraints. After `max_attempts` failures, return the entire
   * image.
   */
  public static Options maxAttempts(Long maxAttempts) {
    return new Options().maxAttempts(maxAttempts);
  }
  
  /**
   * @param useImageIfNoBoundingBoxes Controls behavior if no bounding boxes supplied.
   * If true, assume an implicit bounding box covering the whole input. If false,
   * raise an error.
   */
  public static Options useImageIfNoBoundingBoxes(Boolean useImageIfNoBoundingBoxes) {
    return new Options().useImageIfNoBoundingBoxes(useImageIfNoBoundingBoxes);
  }
  
  /**
   * 1-D, containing `[offset_height, offset_width, 0]`. Provide as input to
   * `tf.slice`.
   */
  public Output<T> begin() {
    return begin;
  }
  
  /**
   * 1-D, containing `[target_height, target_width, -1]`. Provide as input to
   * `tf.slice`.
   */
  public Output<T> size() {
    return size;
  }
  
  /**
   * 3-D with shape `[1, 1, 4]` containing the distorted bounding box.
   * Provide as input to `tf.image.draw_bounding_boxes`.
   */
  public Output<TFloat32> bboxes() {
    return bboxes;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StatelessSampleDistortedBoundingBox";
  
  private Output<T> begin;
  private Output<T> size;
  private Output<TFloat32> bboxes;
  
  private StatelessSampleDistortedBoundingBox(Operation operation) {
    super(operation);
    int outputIdx = 0;
    begin = operation.output(outputIdx++);
    size = operation.output(outputIdx++);
    bboxes = operation.output(outputIdx++);
  }
}
