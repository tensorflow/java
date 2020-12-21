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
 * Generate a single randomly distorted bounding box for an image.
 * <p>
 * Bounding box annotations are often supplied in addition to ground-truth labels
 * in image recognition or object localization tasks. A common technique for
 * training such a system is to randomly distort an image while preserving
 * its content, i.e. <i>data augmentation</i>. This Op outputs a randomly distorted
 * localization of an object, i.e. bounding box, given an `image_size`,
 * `bounding_boxes` and a series of constraints.
 * <p>
 * The output of this Op is a single bounding box that may be used to crop the
 * original image. The output is returned as 3 tensors: `begin`, `size` and
 * `bboxes`. The first 2 tensors can be fed directly into `tf.slice` to crop the
 * image. The latter may be supplied to `tf.image.draw_bounding_boxes` to visualize
 * what the bounding box looks like.
 * <p>
 * Bounding boxes are supplied and returned as `[y_min, x_min, y_max, x_max]`. The
 * bounding box coordinates are floats in `[0.0, 1.0]` relative to the width and
 * height of the underlying image.
 * <p>
 * For example,
 * <pre>{@code
 *     # Generate a single distorted bounding box.
 *     begin, size, bbox_for_draw = tf.image.sample_distorted_bounding_box(
 *         tf.shape(image),
 *         bounding_boxes=bounding_boxes)
 * 
 *     # Draw the bounding box in an image summary.
 *     image_with_box = tf.image.draw_bounding_boxes(tf.expand_dims(image, 0),
 *                                                   bbox_for_draw)
 *     tf.summary.image('images_with_box', image_with_box)
 * 
 *     # Employ the bounding box to distort the image.
 *     distorted_image = tf.slice(image, begin, size)
 * }</pre>
 * Note that if no bounding box information is available, setting
 * `use_image_if_no_bounding_boxes = true` will assume there is a single implicit
 * bounding box covering the whole image. If `use_image_if_no_bounding_boxes` is
 * false and no bounding boxes are supplied, an error is raised.
 * 
 * @param <T> data type for {@code begin()} output
 */
@Operator(group = "image")
public final class SampleDistortedBoundingBox<T extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.image.SampleDistortedBoundingBox}
   */
  public static class Options {
    
    /**
     * @param seed If either `seed` or `seed2` are set to non-zero, the random number
     * generator is seeded by the given `seed`.  Otherwise, it is seeded by a random
     * seed.
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }
    
    /**
     * @param seed2 A second seed to avoid seed collision.
     */
    public Options seed2(Long seed2) {
      this.seed2 = seed2;
      return this;
    }
    
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
    
    private Long seed;
    private Long seed2;
    private List<Float> aspectRatioRange;
    private List<Float> areaRange;
    private Long maxAttempts;
    private Boolean useImageIfNoBoundingBoxes;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SampleDistortedBoundingBox operation.
   * 
   * @param scope current scope
   * @param imageSize 1-D, containing `[height, width, channels]`.
   * @param boundingBoxes 3-D with shape `[batch, N, 4]` describing the N bounding boxes
   * associated with the image.
   * @param minObjectCovered The cropped area of the image must contain at least this
   * fraction of any bounding box supplied. The value of this parameter should be
   * non-negative. In the case of 0, the cropped area does not need to overlap
   * any of the bounding boxes supplied.
   * @param options carries optional attributes values
   * @return a new instance of SampleDistortedBoundingBox
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> SampleDistortedBoundingBox<T> create(Scope scope, Operand<T> imageSize, Operand<TFloat32> boundingBoxes, Operand<TFloat32> minObjectCovered, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SampleDistortedBoundingBoxV2", scope.makeOpName("SampleDistortedBoundingBox"));
    opBuilder.addInput(imageSize.asOutput());
    opBuilder.addInput(boundingBoxes.asOutput());
    opBuilder.addInput(minObjectCovered.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.seed != null) {
          opBuilder.setAttr("seed", opts.seed);
        }
        if (opts.seed2 != null) {
          opBuilder.setAttr("seed2", opts.seed2);
        }
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
    return new SampleDistortedBoundingBox<T>(opBuilder.build());
  }
  
  /**
   * @param seed If either `seed` or `seed2` are set to non-zero, the random number
   * generator is seeded by the given `seed`.  Otherwise, it is seeded by a random
   * seed.
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }
  
  /**
   * @param seed2 A second seed to avoid seed collision.
   */
  public static Options seed2(Long seed2) {
    return new Options().seed2(seed2);
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
  public static final String OP_NAME = "SampleDistortedBoundingBoxV2";
  
  private Output<T> begin;
  private Output<T> size;
  private Output<TFloat32> bboxes;
  
  private SampleDistortedBoundingBox(Operation operation) {
    super(operation);
    int outputIdx = 0;
    begin = operation.output(outputIdx++);
    size = operation.output(outputIdx++);
    bboxes = operation.output(outputIdx++);
  }
}
