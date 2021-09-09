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
package org.tensorflow.framework.metrics.impl;

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.framework.op.FrameworkOps;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.NoOp;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Weight broadcasting operations.
 *
 * <p>In {@link org.tensorflow.framework.losses} and `{@link org.tensorflow.framework.metrics}, we
 * support limited weight broadcasting. This file includes operations for those broadcasting rules.
 */
public class WeightsBroadcastOps {

  private static final String ASSERT_BROADCASTABLE_ERROR_PREFIX =
      "weights can not be broadcast to values.";

  /**
   * Asserts that {@code weights} can be broadcast to {@code values}
   *
   * @param tf the TensorFlow Ops.
   * @param weights the weights Operand
   * @param values Operand of values to which weights are applied.
   * @return {@code Operation} raising a tensorflow InvalidArgumentError if {@code weights} has
   *     incorrect shape. {@link NoOp} if static checks determine {@code weights} has correct shape.
   * @param <T> the type of weights and values
   * @throws IllegalArgumentException If static checks determine {@code weights} has incorrect
   *     shape.
   */
  public static <T extends TNumber> Op assertBroadcastable(
      Ops tf, Operand<T> weights, Operand<T> values) {
    Operand<TInt32> weightsShape = tf.shape(weights);
    Operand<TInt32> weightsRank = tf.rank(weights);
    Shape weightsShapeStatic = weights.shape();
    int weightsRankStatic = weightsShapeStatic.numDimensions();

    Operand<TInt32> valuesShape = tf.shape(values);
    Operand<TInt32> valuesRank = tf.rank(values);
    Shape valuesShapeStatic = values.asOutput().shape();
    int valuesRankStatic = valuesShapeStatic.numDimensions();

    if (weightsRankStatic != -1 && valuesRankStatic != -1) {
      if (weightsRankStatic == 0) {
        return tf.withSubScope("staticScalarCheckSuccess")
            .withControlDependencies(Collections.emptyList())
            .noOp();
      }
      if (weightsRankStatic != valuesRankStatic) {
        throw new IllegalArgumentException(
            String.format(
                "%s values.rank=%d. weights.rank=%d.  values.shape=%s. weights.shape=%s.",
                ASSERT_BROADCASTABLE_ERROR_PREFIX,
                valuesRankStatic,
                weightsRankStatic,
                valuesShapeStatic,
                weightsShapeStatic));
      }

      for (int i = 0; i < valuesRankStatic; i++) {
        if (weightsShapeStatic.size(i) != 1
            && valuesShapeStatic.size(i) != weightsShapeStatic.size(i)) {
          throw new IllegalArgumentException(
              String.format(
                  "%s Mismatch at dim %s. values.shape=%s weights.shape=%s.",
                  ASSERT_BROADCASTABLE_ERROR_PREFIX, i, valuesShapeStatic, weightsShapeStatic));
        }
      }
      return tf.withSubScope("staticDimsCheckSuccess")
          .withControlDependencies(Collections.emptyList())
          .noOp();
    }
    // Dynamic checks.
    Operand<TBool> isScalar = tf.math.equal(weightsRank, tf.constant(0));
    List<Operand<?>> data =
        Arrays.asList(
            tf.constant(ASSERT_BROADCASTABLE_ERROR_PREFIX),
            tf.constant("weights.shape="),
            weightsShape,
            tf.constant("values.shape="),
            valuesShape,
            tf.constant("isScalar="),
            isScalar);

    Operand<TBool> isValidShape =
        tf.select(
            isScalar,
            isScalar,
            hasValidNonscalarShape(tf, weightsRank, weightsShape, valuesRank, valuesShape));

    return tf.assertThat(isValidShape, data);
  }

  /**
   * Check to see that weights and values have the same rank, if they do, then check each
   * corresponding dim of each.
   *
   * @param tf the TensorFlow Ops
   * @param weightsRank the rank operand for the weights
   * @param weightsShape the shape operand for the weights
   * @param valuesRank the rank operand for the values
   * @param valuesShape the shape operand for the values
   * @return a boolean Operand, true if both shapes have the same rank, and each dimension is the
   *     same
   */
  private static Operand<TBool> hasValidNonscalarShape(
      Ops tf,
      Operand<TInt32> weightsRank,
      Operand<TInt32> weightsShape,
      Operand<TInt32> valuesRank,
      Operand<TInt32> valuesShape) {
    tf = tf.withSubScope("hasValidNonscalarShape");
    Operand<TBool> isSameRank = tf.math.equal(valuesRank, weightsRank);
    return tf.select(isSameRank, hasValidDims(tf, weightsShape, valuesShape), isSameRank);
  }

  /**
   * Checks that each dimension of the two shapes are the same size, or that the weight dimension
   * size is 1.
   *
   * @param tf the TensorFlow Ops.
   * @param weightsShape the shape of the weights
   * @param valuesShape the shape of the values
   * @return a boolean Operand, true if all the dimensions of the two shapes are the same.
   */
  private static Operand<TBool> hasValidDims(
      Ops tf, Operand<TInt32> weightsShape, Operand<TInt32> valuesShape) {
    tf = tf.withSubScope("hasInvalidDims");

    Operand<TInt32> valuesShape2d = tf.expandDims(valuesShape, tf.constant(-1));
    Operand<TInt32> validDims =
        tf.concat(Arrays.asList(valuesShape2d, tf.onesLike(valuesShape2d)), tf.constant(1));
    Operand<TInt32> weightsShape2d = tf.expandDims(weightsShape, tf.constant(-1));

    FrameworkOps fops = FrameworkOps.create(tf);
    Operand<TInt32> invalidDims = fops.sets.difference(weightsShape2d, validDims);
    Operand<TInt32> numInvalidDims = tf.size(invalidDims, TInt32.class);
    return tf.math.equal(tf.constant(0), numInvalidDims);
  }

  /**
   * Broadcast {@code weights} to the same shape as {@code values}.
   *
   * <p>This returns a version of {@code weights} following the same broadcast rules as {@code
   * mul(weights, values)}, but limited to the weights shapes allowed by {@code assertBroadcastable}
   * When computing a weighted average, use this function to broadcast {@code weights} before
   * summing them; e.g., {@code reduceSum(w * v) / reduceSum(_broadcast_weights(w, v))}.
   *
   * @param tf the TensorFlow Ops
   * @param weights Operand whose shape is able to be broadcast to {@code values}
   * @param values Tensor` of any shape
   * @param <T> the type of Operand
   * @return {@code weights} broadcast to {@code values} shape
   */
  public static <T extends TNumber> Operand<T> broadcastWeights(
      Ops tf, Operand<T> weights, Operand<T> values) {
    tf = tf.withSubScope("broadcast_weights");
    Operand<T> tValues = cast(tf, values, weights.type());

    Shape weightsShape = weights.shape();
    Shape valuesShape = tValues.shape();

    if (!weightsShape.hasUnknownDimension()
        && !valuesShape.hasUnknownDimension()
        && weightsShape.isCompatibleWith(valuesShape)) {
      return weights;
    }

    Op dependencies = assertBroadcastable(tf, weights, tValues);
    Ops tf1 =
        tf.withSubScope("assertBroadcastable")
            .withControlDependencies(Collections.singletonList(dependencies));
    return tf1.math.mul(weights, tf.onesLike(tValues));
  }
}
