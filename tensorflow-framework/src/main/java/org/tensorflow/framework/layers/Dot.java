/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.framework.layers;

import org.tensorflow.Operand;
import org.tensorflow.framework.layers.impl.Merge;
import org.tensorflow.framework.op.FrameworkOps;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Squeeze;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Layer that computes a dot product between samples in two tensors.
 *
 * <p>E.g. if applied to a list of two tensors <code>a</code> and <code>b</code> of shape <code>
 * (batch_size, n)</code>, the output will be a tensor of shape <code>(batch_size, 1)</code> where
 * each entry <code>i</code> will be the dot product between `a[i]` and `b[i]`.
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class Dot<T extends TFloating> extends Merge<T> {
  private final int[] axes;
  private final boolean normalize;

  /**
   * Creates a Layer that computes a dot product between samples in two tensors, using {@link
   * Class#getSimpleName()} as the layer name, and no L2 Normalization.
   *
   * @param tf the TensorFlow Ops
   * @param axes axes along which to take the dot product.
   * @param type the data type for the weights and computation
   */
  public Dot(Ops tf, int axes, Class<T> type) {
    this(tf, null, new int[] {axes}, false, type, null);
  }

  /**
   * Creates a Layer that computes a dot product between samples in two tensors, using {@link
   * Class#getSimpleName()} as the layer name, and no L2 Normalization.
   *
   * @param tf the TensorFlow Ops
   * @param axes axes along which to take the dot product.
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Dot(Ops tf, int axes, Class<T> type, Options options) {
    this(tf, null, new int[] {axes}, false, type, options);
  }

  /**
   * Creates a Layer that computes a dot product between samples in two tensors, using {@link
   * Class#getSimpleName()} as the layer name and no L2 Normalization.
   *
   * @param tf the TensorFlow Ops
   * @param axes axes along which to take the dot product. Should be one or two integers
   *     corresponding to the desired axis from the first input and the desired axis from the second
   *     input, respectively. Note that the size of the two selected axes must match.
   * @param type the data type for the weights and computation
   */
  public Dot(Ops tf, int[] axes, Class<T> type) {
    this(tf, null, axes, false, type, null);
  }

  /**
   * Creates a Layer that computes a dot product between samples in two tensors, using {@link
   * Class#getSimpleName()} as the layer name and no L2 Normalization.
   *
   * @param tf the TensorFlow Ops
   * @param axes axes along which to take the dot product. Should be one or two integers
   *     corresponding to the desired axis from the first input and the desired axis from the second
   *     input, respectively. Note that the size of the two selected axes must match.
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Dot(Ops tf, int[] axes, Class<T> type, Options options) {
    this(tf, null, axes, false, type, options);
  }

  /**
   * Creates a Layer that computes a dot product between samples in two tensors with no L2
   * Normalization.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param axes axes along which to take the dot product.
   * @param type the data type for the weights and computation
   */
  public Dot(Ops tf, String name, int axes, Class<T> type) {
    this(tf, name, new int[] {axes}, false, type, null);
  }

  /**
   * Creates a Layer that computes a dot product between samples in two tensors with no L2
   * Normalization.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param axes axes along which to take the dot product.
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Dot(Ops tf, String name, int axes, Class<T> type, Options options) {
    this(tf, name, new int[] {axes}, false, type, options);
  }

  /**
   * Creates a Layer that computes a dot product between samples in two tensors with no L2
   * Normalization.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param axes axes along which to take the dot product. Should be one or two integers
   *     corresponding to the desired axis from the first input and the desired axis from the second
   *     input, respectively. Note that the size of the two selected axes must match.
   * @param type the data type for the weights and computation
   */
  public Dot(Ops tf, String name, int[] axes, Class<T> type) {
    this(tf, name, axes, false, type, null);
  }

  /**
   * Creates a Layer that computes a dot product between samples in two tensors with no L2
   * Normalization.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param axes axes along which to take the dot product. Should be one or two integers
   *     corresponding to the desired axis from the first input and the desired axis from the second
   *     input, respectively. Note that the size of the two selected axes must match.
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Dot(Ops tf, String name, int[] axes, Class<T> type, Options options) {
    this(tf, name, axes, false, type, options);
  }

  /**
   * Creates a Layer that computes a dot product between samples in two tensors.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param axes axes along which to take the dot product.
   * @param normalize Whether to L2-normalize samples along the dot product axis before taking the
   *     dot product. If set to True, then the output of the dot product is the cosine proximity
   *     between the two samples.
   * @param type the data type for the weights and computation
   */
  public Dot(Ops tf, String name, int axes, boolean normalize, Class<T> type) {
    this(tf, name, new int[] {axes}, normalize, type, null);
  }

  /**
   * Creates a Layer that computes a dot product between samples in two tensors.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param axes axes along which to take the dot product.
   * @param normalize Whether to L2-normalize samples along the dot product axis before taking the
   *     dot product. If set to True, then the output of the dot product is the cosine proximity
   *     between the two samples.
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Dot(Ops tf, String name, int axes, boolean normalize, Class<T> type, Options options) {
    this(tf, name, new int[] {axes}, normalize, type, options);
  }

  /**
   * Creates a Layer that computes a dot product between samples in two tensors.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param axes axes along which to take the dot product. Should be one or two integers
   *     corresponding to the desired axis from the first input and the desired axis from the second
   *     input, respectively. Note that the size of the two selected axes must match.
   * @param normalize Whether to L2-normalize samples along the dot product axis before taking the
   *     dot product. If set to True, then the output of the dot product is the cosine proximity
   *     between the two samples.
   * @param type the data type for the weights and computation
   */
  public Dot(Ops tf, String name, int[] axes, boolean normalize, Class<T> type) {
    this(tf, name, axes, normalize, type, null);
  }

  /**
   * Creates a Layer that computes a dot product between samples in two tensors.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param axes axes along which to take the dot product. Should be one or two integers
   *     corresponding to the desired axis from the first input and the desired axis from the second
   *     input, respectively. Note that the size of the two selected axes must match.
   * @param normalize Whether to L2-normalize samples along the dot product axis before taking the
   *     dot product. If set to True, then the output of the dot product is the cosine proximity
   *     between the two samples.
   * @param type the data type for the weights and computation
   * @param options the layer's options
   */
  public Dot(Ops tf, String name, int[] axes, boolean normalize, Class<T> type, Options options) {
    super(tf, name, type, options);
    if (axes.length < 1 || axes.length > 2) {
      throw new IllegalArgumentException(
          "Invalid format for axes - must only contain one or two elements.");
    }
    this.axes = axes;
    this.normalize = normalize;
    this.setSupportsMasking(true);
  }

  /** {@inheritDoc} */
  @Override
  protected void build(List<Shape> inputShapes) {
    // Used purely for shape validation.
    if (inputShapes.size() != 2) {
      throw new IllegalArgumentException("A Dot layer should be called on exactly 2 inputs");
    }
    Shape shape1 = inputShapes.get(0);
    Shape shape2 = inputShapes.get(1);
    if (shape1.isUnknown() || shape2.isUnknown()) {
      return;
    }
    int[] newAxes;
    if (axes.length == 1) {
      newAxes = new int[2];
      // covert negative axes
      if (axes[0] < 0) {
        newAxes[0] = Math.floorMod(axes[0], shape1.numDimensions());
        newAxes[1] = Math.floorMod(axes[0], shape2.numDimensions());
      } else {
        newAxes[0] = axes[0];
        newAxes[1] = axes[0];
      }
    } else {
      newAxes = axes;
    }
    if (shape1.size(axes[0]) != shape2.size(axes[1])) {
      throw new IllegalArgumentException(
          String.format(
              "Dimension incompatibility %s != %s. Layer shapes: %s, %s. Chosen axes: %s",
              shape1.size(axes[0]),
              shape2.size(axes[1]),
              shape1,
              shape2,
              Arrays.toString(newAxes)));
    }
  }

  /** {@inheritDoc} */
  @Override
  protected Operand<T> mergeFunction(List<Operand<? extends TNumber>> inputs) {
    Ops tf = getTF();
    if (inputs.size() != 2) {
      throw new IllegalArgumentException("A Dot layer should be called on exactly 2 inputs");
    }
    Operand<? extends TNumber> input1 = inputs.get(0);
    Operand<? extends TNumber> input2 = inputs.get(1);
    int[] newAxes = new int[2];
    if (axes.length == 1) {
      if (axes[0] < 0) {
        newAxes[0] = Math.floorMod(axes[0], input1.shape().numDimensions());
        newAxes[1] = Math.floorMod(axes[0], input2.shape().numDimensions());
      } else {
        newAxes[0] = axes[0];
        newAxes[1] = axes[0];
      }
    } else {
      for (int i = 0; i < axes.length; i++) {
        if (axes[i] < 0) {
          newAxes[i] = Math.floorMod(axes[0], inputs.get(i).shape().numDimensions());
        } else {
          newAxes[i] = axes[i];
        }
      }
    }
    if (normalize) {
      FrameworkOps fops = FrameworkOps.create(tf);
      input1 = fops.math.l2Normalize(input1, new int[] {axes[0]});
      input2 = fops.math.l2Normalize(input2, new int[] {axes[1]});
    }
    return batchDot(input1, input2, newAxes);
  }

  /** {@inheritDoc} */
  @Override
  public List<Operand<TBool>> computeMask(
      List<Operand<? extends TType>> inputs, List<Operand<? extends TType>> masks) {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public List<Shape> computeOutputShape(List<Shape> inputShapes) {
    if (inputShapes.size() != 2) {
      throw new IllegalArgumentException("A Dot layer should be called on a list of 2 inputs.");
    }
    Shape shape1 = inputShapes.get(0);
    Shape shape2 = inputShapes.get(1);
    int[] lAxes;
    if (axes.length == 1) {
      lAxes = new int[2];
      lAxes[0] = Math.floorMod(axes[0], shape1.numDimensions());
      lAxes[1] = Math.floorMod(axes[0], shape2.numDimensions());
    } else {
      lAxes = axes;
      for (int i = 0; i < lAxes.length; i++) {
        lAxes[i] = Math.floorMod(axes[i], shape1.numDimensions());
      }
    }

    // pop(axes[0])
    shape1 = shape1.take(lAxes[0]);
    long remainder = shape1.numDimensions() - (lAxes[0] + 1);
    if (remainder > 0) {
      shape1 = shape1.append(shape1.takeLast((int) remainder));
    }

    // pop(axes[1])
    shape2 = shape2.take(lAxes[1]);
    remainder = shape2.numDimensions() - (lAxes[1] + 1);
    if (remainder > 0) {
      shape2 = shape2.append(shape2.takeLast((int) remainder));
    }
    if (shape2.numDimensions() > 0) {
      // pop(0)
      shape2 = shape2.takeLast(shape2.numDimensions() - 1);
    }
    Shape outputShape = shape1.append(shape2);

    if (outputShape.numDimensions() == 1) {
      outputShape = outputShape.append(1);
    }
    return Collections.singletonList(outputShape);
  }

  /**
   * Computes the batch-wise dot product.
   *
   * <p><code>batchDot</code> is used to compute dot product of <code>x</code> and <code>y</code>
   * when <code>x</code> and <code>y</code> are data in batch, i.e. in a shape of <code>
   * (batch_size, :)</code>. <code>batchDot</code> results in aan Operand with less dimensions than
   * the input. If the number of dimensions is reduced to 1, we use <code>expandDims
   * </code> to make sure that the number of dimensions is at least 2.
   *
   * @param x Operand with <code>numdimensions >= 2</code>.
   * @param y Operand with <code>numdimensions >= 2</code>.
   * @param axes the axes to peform the Dot Product.
   * @return A operand with shape equal to the concatenation of <code>x</code>'s shape (less the
   *     dimension that was summed over) and <code>y</code>'s shape (less the batch dimension and
   *     the dimension that was summed over). If the final rank is 1, the result is reshaped to
   *     <code>(batch_size, 1)</code>.
   */
  private Operand<T> batchDot(
      Operand<? extends TNumber> x, Operand<? extends TNumber> y, int[] axes) {
    Ops tf = getTF();
    FrameworkOps fops = FrameworkOps.create(tf);
    // make local copy for changes later
    int[] dotAxes = axes;
    Operand<T> tX = cast(tf, x, getType());
    Operand<T> tY = cast(tf, y, getType());

    Shape xShape = tX.shape();
    Shape yShape = tY.shape();

    int xRank = xShape.numDimensions();
    int yRank = yShape.numDimensions();

    if (xRank < 2 || yRank < 2) {
      throw new IllegalArgumentException(
          String.format(
              "Cannot do batch_dot on inputs with rank < 2. Received inputs with shapes  %s and %s.",
              xShape, yShape));
    }

    int xBatchSize = (int) xShape.size(0);
    int yBatchSize = (int) yShape.size(0);
    if (xBatchSize != Shape.UNKNOWN_SIZE && yBatchSize != Shape.UNKNOWN_SIZE) {
      if (xBatchSize != yBatchSize) {
        throw new IllegalArgumentException(
            String.format(
                "Cannot do batchDot on inputs with different batch sizes. Received inputs with shapes  %s and %s.",
                xShape, yShape));
      }
    }

    if (dotAxes == null) {
      dotAxes = new int[2];
      dotAxes[0] = xRank - 1;
      if (yRank == 2) {
        dotAxes[1] = yRank - 1;
      } else {
        dotAxes[1] = yRank - 2;
      }
    } else if (dotAxes.length == 1) {
      dotAxes = new int[] {dotAxes[0], dotAxes[0]};
    }

    if (dotAxes[0] < 0) {
      dotAxes[0] = Math.floorMod(dotAxes[0], xRank);
    }
    if (dotAxes[1] < 0) {
      dotAxes[1] = Math.floorMod(dotAxes[1], yRank);
    }
    if (dotAxes[0] == 0 || dotAxes[1] == 0) {
      throw new IllegalArgumentException(
          "Cannot perform batch_dot over axis 0. If your inputs are not batched, add a dummy batch dimension to your inputs using tf.expandDims(x, 0)");
    }

    int a0 = dotAxes[0];
    int a1 = dotAxes[1];
    int d1 = (int) xShape.size(a0);
    int d2 = (int) yShape.size(a1);

    if (d1 != Shape.UNKNOWN_SIZE && d2 != Shape.UNKNOWN_SIZE && d1 != d2) {
      throw new IllegalArgumentException(
          String.format(
              "Cannot do batch_dot on inputs with shapes %s and %s with axes %s. x.shape[%d] != %d, y.shape[%d] != %d",
              xShape, yShape, Arrays.toString(dotAxes), a0, d1, a1, d2));
    }

    // backup rank. Need them rank.
    int origXRank = xRank;
    int origYRank = yRank;
    if (xRank == 2) {
      tX = tf.expandDims(tX, tf.constant(1));
      xRank++;
      a0++;
    }
    if (yRank == 2) {
      tY = tf.expandDims(tY, tf.constant(2));
      yRank += 1;
    }

    // move x's dimension to be reduced to last axis.
    if (a0 != xRank - 1) {
      int[] pattern = new int[xRank];
      // move a0 to last
      for (int i = 0; i < a0; i++) {
        pattern[i] = i;
      }
      for (int i = a0; i < xRank - 1; i++) {
        pattern[i] = i + 1;
      }
      pattern[xRank - 1] = a0;
      tX = tf.linalg.transpose(tX, tf.constant(pattern));
    }
    // move y's dimension to be reduced to axis 1.
    if (a1 != 1) {
      int[] pattern = new int[yRank];
      pattern[0] = 0;
      // skip slot 1
      for (int i = 1; i < a1; i++) {
        pattern[i + 1] = i;
      }
      for (int i = a1; i < pattern.length - 1; i++) {
        pattern[i + 1] = i + 1;
      }
      pattern[1] = a1;
      //noinspection SuspiciousNameCombination
      tY = tf.linalg.transpose(tY, tf.constant(pattern));
    }

    // normalize both inputs to rank 3.
    boolean xSquashed = false;
    Operand<TInt64> xMidShape = null;
    if (xRank > 3) {
      org.tensorflow.op.core.Shape<TInt64> tmpShape = tf.shape(tX, TInt64.class);
      xMidShape = tf.shape.takeLast(tmpShape, tf.constant((long) (xRank - 1)), TInt64.class);

      Operand<TInt64> squashedShape =
          tf.stack(
              Arrays.asList(
                  tf.shape.size(tmpShape, tf.constant(0L), TInt64.class),
                  tf.constant(Shape.UNKNOWN_SIZE),
                  tf.shape.size(tmpShape, tf.constant((long) (xRank - 1)), TInt64.class)));
      tX = tf.reshape(tX, squashedShape);
      xSquashed = true;
    }

    boolean ySquashed = false;
    Operand<TInt64> yTrailDims = null;
    if (yRank > 3) {
      yTrailDims =
          tf.shape.takeLast(
              tf.shape(tY, TInt64.class), tf.constant((long) (yRank - 2)), TInt64.class);

      Operand<TInt64> squashedShape =
          tf.stack(
              Arrays.asList(
                  tf.shape.size(y, tf.constant(0L), TInt64.class),
                  tf.shape.size(y, tf.constant(1L), TInt64.class),
                  tf.constant(-1L)));
      tY = tf.reshape(tY, squashedShape);
      ySquashed = true;
    }

    Operand<T> result = fops.linalg.matmul(tX, tY);
    boolean doReshape = false;
    Operand<TInt64> outputShape = tf.shape(result, TInt64.class);

    if (xSquashed) {
      outputShape =
          tf.concat(
              Arrays.asList(
                  tf.shape.size(outputShape, tf.constant(0L), TInt64.class),
                  xMidShape,
                  tf.shape.size(outputShape, tf.constant(-1L), TInt64.class)),
              tf.constant(0));
      doReshape = true;
    }

    if (ySquashed) {

      outputShape =
          tf.concat(
              Arrays.asList(
                  tf.slice(outputShape, tf.constant(0), tf.constant(outputShape.rank() - 1)),
                  yTrailDims),
              tf.constant(0));
      doReshape = true;
    }

    if (doReshape) {
      result = tf.reshape(result, outputShape);
    }

    if (origXRank == 2) {
      result = tf.squeeze(result, Squeeze.axis(Collections.singletonList(1L)));
    } else if (origYRank == 2) {
      result = tf.squeeze(result, Squeeze.axis(Collections.singletonList(-1L)));
    }
    return result;
  }
}
