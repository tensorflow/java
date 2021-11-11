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
import java.util.List;
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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Generate a randomly distorted bounding box for an image deterministically.
 * Bounding box annotations are often supplied in addition to ground-truth labels
 * in image recognition or object localization tasks. A common technique for
 * training such a system is to randomly distort an image while preserving its
 * content, i.e. <em>data augmentation</em>. This Op, given the same {@code seed},
 * deterministically outputs a randomly distorted localization of an object, i.e.
 * bounding box, given an {@code image_size}, {@code bounding_boxes} and a series of
 * constraints.
 * <p>The output of this Op is a single bounding box that may be used to crop the
 * original image. The output is returned as 3 tensors: {@code begin}, {@code size} and
 * {@code bboxes}. The first 2 tensors can be fed directly into {@code tf.slice} to crop the
 * image. The latter may be supplied to {@code tf.image.draw_bounding_boxes} to visualize
 * what the bounding box looks like.
 * <p>Bounding boxes are supplied and returned as {@code [y_min, x_min, y_max, x_max]}. The
 * bounding box coordinates are floats in {@code [0.0, 1.0]} relative to the width and
 * the height of the underlying image.
 * <p>The output of this Op is guaranteed to be the same given the same {@code seed} and is
 * independent of how many times the function is called, and independent of global
 * seed settings (e.g. {@code tf.random.set_seed}).
 * <p>Example usage:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>image = np.array([[[1], [2], [3]], [[4], [5], [6]], [[7], [8], [9]]])
 * bbox = tf.constant(
 * ...   [0.0, 0.0, 1.0, 1.0], dtype=tf.float32, shape=[1, 1, 4])
 * seed = (1, 2)
 * <strong>Generate a single distorted bounding box.</strong><br>
 * <p>bbox_begin, bbox_size, bbox_draw = (
 * ...   tf.image.stateless_sample_distorted_bounding_box(
 * ...     tf.shape(image), bounding_boxes=bbox, seed=seed))
 * <strong>Employ the bounding box to distort the image.</strong><br>
 * <p>tf.slice(image, bbox_begin, bbox_size)
 * &lt;tf.Tensor: shape=(2, 2, 1), dtype=int64, numpy=
 * array([[[1],
 * [2]],
 * [[4],
 * [5]]])&gt;
 * <strong>Draw the bounding box in an image summary.</strong><br>
 * <p>colors = np.array([[1.0, 0.0, 0.0], [0.0, 0.0, 1.0]])
 * tf.image.draw_bounding_boxes(
 * ...   tf.expand_dims(tf.cast(image, tf.float32),0), bbox_draw, colors)
 * &lt;tf.Tensor: shape=(1, 3, 3, 1), dtype=float32, numpy=
 * array([[[[1.],
 * [1.],
 * [3.]],
 * [[1.],
 * [1.],
 * [6.]],
 * [[7.],
 * [8.],
 * [9.]]]], dtype=float32)&gt;
 * </blockquote>
 * </blockquote>
 * </blockquote>
 * <p>Note that if no bounding box information is available, setting
 * {@code use_image_if_no_bounding_boxes = true} will assume there is a single implicit
 * bounding box covering the whole image. If {@code use_image_if_no_bounding_boxes} is
 * false and no bounding boxes are supplied, an error is raised.
 *
 * @param <T> data type for {@code begin} output
 */
@OpMetadata(
    opType = StatelessSampleDistortedBoundingBox.OP_NAME,
    inputsClass = StatelessSampleDistortedBoundingBox.Inputs.class
)
@Operator(
    group = "image"
)
public final class StatelessSampleDistortedBoundingBox<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessSampleDistortedBoundingBox";

  private Output<T> begin;

  private Output<T> sizeOutput;

  private Output<TFloat32> bboxes;

  public StatelessSampleDistortedBoundingBox(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    begin = operation.output(outputIdx++);
    sizeOutput = operation.output(outputIdx++);
    bboxes = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatelessSampleDistortedBoundingBox operation.
   *
   * @param scope current scope
   * @param imageSize 1-D, containing {@code [height, width, channels]}.
   * @param boundingBoxes 3-D with shape {@code [batch, N, 4]} describing the N bounding boxes
   * associated with the image.
   * @param minObjectCovered The cropped area of the image must contain at least this
   * fraction of any bounding box supplied. The value of this parameter should be
   * non-negative. In the case of 0, the cropped area does not need to overlap
   * any of the bounding boxes supplied.
   * @param seed 1-D with shape {@code [2]}. The seed to the random number generator. Must have dtype
   * {@code int32} or {@code int64}. (When using XLA, only {@code int32} is allowed.)
   * @param options carries optional attribute values
   * @param <T> data type for {@code StatelessSampleDistortedBoundingBox} output and operands
   * @return a new instance of StatelessSampleDistortedBoundingBox
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> StatelessSampleDistortedBoundingBox<T> create(Scope scope,
      Operand<T> imageSize, Operand<TFloat32> boundingBoxes, Operand<TFloat32> minObjectCovered,
      Operand<? extends TNumber> seed, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessSampleDistortedBoundingBox");
    opBuilder.addInput(imageSize.asOutput());
    opBuilder.addInput(boundingBoxes.asOutput());
    opBuilder.addInput(minObjectCovered.asOutput());
    opBuilder.addInput(seed.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.aspectRatioRange != null) {
          float[] aspectRatioRangeArray = new float[opts.aspectRatioRange.size()];
          for (int i = 0 ; i < aspectRatioRangeArray.length ; i++) {
            aspectRatioRangeArray[i] = opts.aspectRatioRange.get(i);
          }
          opBuilder.setAttr("aspect_ratio_range", aspectRatioRangeArray);
        }
        if (opts.areaRange != null) {
          float[] areaRangeArray = new float[opts.areaRange.size()];
          for (int i = 0 ; i < areaRangeArray.length ; i++) {
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
    return new StatelessSampleDistortedBoundingBox<>(opBuilder.build());
  }

  /**
   * Sets the aspectRatioRange option.
   *
   * @param aspectRatioRange The cropped area of the image must have an aspect ratio =
   * width / height within this range.
   * @return this Options instance.
   */
  public static Options aspectRatioRange(List<Float> aspectRatioRange) {
    return new Options().aspectRatioRange(aspectRatioRange);
  }

  /**
   * Sets the aspectRatioRange option.
   *
   * @param aspectRatioRange The cropped area of the image must have an aspect ratio =
   * width / height within this range.
   * @return this Options instance.
   */
  public static Options aspectRatioRange(Float... aspectRatioRange) {
    return new Options().aspectRatioRange(aspectRatioRange);
  }

  /**
   * Sets the areaRange option.
   *
   * @param areaRange The cropped area of the image must contain a fraction of the
   * supplied image within this range.
   * @return this Options instance.
   */
  public static Options areaRange(List<Float> areaRange) {
    return new Options().areaRange(areaRange);
  }

  /**
   * Sets the areaRange option.
   *
   * @param areaRange The cropped area of the image must contain a fraction of the
   * supplied image within this range.
   * @return this Options instance.
   */
  public static Options areaRange(Float... areaRange) {
    return new Options().areaRange(areaRange);
  }

  /**
   * Sets the maxAttempts option.
   *
   * @param maxAttempts Number of attempts at generating a cropped region of the image
   * of the specified constraints. After {@code max_attempts} failures, return the entire
   * image.
   * @return this Options instance.
   */
  public static Options maxAttempts(Long maxAttempts) {
    return new Options().maxAttempts(maxAttempts);
  }

  /**
   * Sets the useImageIfNoBoundingBoxes option.
   *
   * @param useImageIfNoBoundingBoxes Controls behavior if no bounding boxes supplied.
   * If true, assume an implicit bounding box covering the whole input. If false,
   * raise an error.
   * @return this Options instance.
   */
  public static Options useImageIfNoBoundingBoxes(Boolean useImageIfNoBoundingBoxes) {
    return new Options().useImageIfNoBoundingBoxes(useImageIfNoBoundingBoxes);
  }

  /**
   * Gets begin.
   * 1-D, containing {@code [offset_height, offset_width, 0]}. Provide as input to
   * {@code tf.slice}.
   * @return begin.
   */
  public Output<T> begin() {
    return begin;
  }

  /**
   * Gets sizeOutput.
   * 1-D, containing {@code [target_height, target_width, -1]}. Provide as input to
   * {@code tf.slice}.
   * @return sizeOutput.
   */
  public Output<T> sizeOutput() {
    return sizeOutput;
  }

  /**
   * Gets bboxes.
   * 3-D with shape {@code [1, 1, 4]} containing the distorted bounding box.
   * Provide as input to {@code tf.image.draw_bounding_boxes}.
   * @return bboxes.
   */
  public Output<TFloat32> bboxes() {
    return bboxes;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.StatelessSampleDistortedBoundingBox}
   */
  public static class Options {
    private List<Float> aspectRatioRange;

    private List<Float> areaRange;

    private Long maxAttempts;

    private Boolean useImageIfNoBoundingBoxes;

    private Options() {
    }

    /**
     * Sets the aspectRatioRange option.
     *
     * @param aspectRatioRange The cropped area of the image must have an aspect ratio =
     * width / height within this range.
     * @return this Options instance.
     */
    public Options aspectRatioRange(List<Float> aspectRatioRange) {
      this.aspectRatioRange = aspectRatioRange;
      return this;
    }

    /**
     * Sets the aspectRatioRange option.
     *
     * @param aspectRatioRange The cropped area of the image must have an aspect ratio =
     * width / height within this range.
     * @return this Options instance.
     */
    public Options aspectRatioRange(Float... aspectRatioRange) {
      this.aspectRatioRange = Arrays.asList(aspectRatioRange);
      return this;
    }

    /**
     * Sets the areaRange option.
     *
     * @param areaRange The cropped area of the image must contain a fraction of the
     * supplied image within this range.
     * @return this Options instance.
     */
    public Options areaRange(List<Float> areaRange) {
      this.areaRange = areaRange;
      return this;
    }

    /**
     * Sets the areaRange option.
     *
     * @param areaRange The cropped area of the image must contain a fraction of the
     * supplied image within this range.
     * @return this Options instance.
     */
    public Options areaRange(Float... areaRange) {
      this.areaRange = Arrays.asList(areaRange);
      return this;
    }

    /**
     * Sets the maxAttempts option.
     *
     * @param maxAttempts Number of attempts at generating a cropped region of the image
     * of the specified constraints. After {@code max_attempts} failures, return the entire
     * image.
     * @return this Options instance.
     */
    public Options maxAttempts(Long maxAttempts) {
      this.maxAttempts = maxAttempts;
      return this;
    }

    /**
     * Sets the useImageIfNoBoundingBoxes option.
     *
     * @param useImageIfNoBoundingBoxes Controls behavior if no bounding boxes supplied.
     * If true, assume an implicit bounding box covering the whole input. If false,
     * raise an error.
     * @return this Options instance.
     */
    public Options useImageIfNoBoundingBoxes(Boolean useImageIfNoBoundingBoxes) {
      this.useImageIfNoBoundingBoxes = useImageIfNoBoundingBoxes;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = StatelessSampleDistortedBoundingBox.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<StatelessSampleDistortedBoundingBox<T>> {
    /**
     * 1-D, containing {@code [height, width, channels]}.
     */
    public final Operand<T> imageSize;

    /**
     * 3-D with shape {@code [batch, N, 4]} describing the N bounding boxes
     * associated with the image.
     */
    public final Operand<TFloat32> boundingBoxes;

    /**
     * The cropped area of the image must contain at least this
     * fraction of any bounding box supplied. The value of this parameter should be
     * non-negative. In the case of 0, the cropped area does not need to overlap
     * any of the bounding boxes supplied.
     */
    public final Operand<TFloat32> minObjectCovered;

    /**
     * 1-D with shape {@code [2]}. The seed to the random number generator. Must have dtype
     * {@code int32} or {@code int64}. (When using XLA, only {@code int32} is allowed.)
     */
    public final Operand<? extends TNumber> seed;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tseed attribute
     */
    public final DataType Tseed;

    /**
     * The cropped area of the image must have an aspect ratio =
     * width / height within this range.
     */
    public final float[] aspectRatioRange;

    /**
     * The cropped area of the image must contain a fraction of the
     * supplied image within this range.
     */
    public final float[] areaRange;

    /**
     * Number of attempts at generating a cropped region of the image
     * of the specified constraints. After `max_attempts` failures, return the entire
     * image.
     */
    public final long maxAttempts;

    /**
     * Controls behavior if no bounding boxes supplied.
     * If true, assume an implicit bounding box covering the whole input. If false,
     * raise an error.
     */
    public final boolean useImageIfNoBoundingBoxes;

    public Inputs(GraphOperation op) {
      super(new StatelessSampleDistortedBoundingBox<>(op), op, Arrays.asList("T", "Tseed", "aspect_ratio_range", "area_range", "max_attempts", "use_image_if_no_bounding_boxes"));
      int inputIndex = 0;
      imageSize = (Operand<T>) op.input(inputIndex++);
      boundingBoxes = (Operand<TFloat32>) op.input(inputIndex++);
      minObjectCovered = (Operand<TFloat32>) op.input(inputIndex++);
      seed = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tseed = op.attributes().getAttrType("Tseed");
      aspectRatioRange = op.attributes().getAttrFloatList("aspect_ratio_range");
      areaRange = op.attributes().getAttrFloatList("area_range");
      maxAttempts = op.attributes().getAttrInt("max_attempts");
      useImageIfNoBoundingBoxes = op.attributes().getAttrBool("use_image_if_no_bounding_boxes");
    }
  }
}
