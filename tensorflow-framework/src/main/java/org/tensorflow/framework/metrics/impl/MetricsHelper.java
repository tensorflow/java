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

import org.tensorflow.Operand;
import org.tensorflow.framework.metrics.exceptions.NotBroadcastableException;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.math.Mean;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TIntegral;
import org.tensorflow.types.family.TNumber;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.tensorflow.framework.losses.impl.LossesHelper.allAxes;
import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * These are helper methods for Metrics and will be module private when Java modularity is applied
 * to TensorFlow Java. These methods should not be used outside of the metrics packages.
 */
public class MetricsHelper {
  public static final float NEG_INF = -1e10f;
  private static final String ASSERT_BROADCAST_ERROR_PREFIX =
      "weights can not be broadcast to values.";

  /**
   * Asserts that the <code>sampleWeights</code> can be broadcast to the same shape as <code>values
   * </code>
   *
   * <p>In losses and metrics, limited weight broadcasting is supported. Weights must be either
   * scalar, or the same rank as the target values, with each dimension either 1, or the same as the
   * corresponding values dimension.
   *
   * @param tf the TensorFlow Ops
   * @param sampleWeights the sample weights.
   * @param values the values to which weights are applied.
   * @return <code>Operation</code> with control dependencies to ensure <code>sampleWeight</code>
   *     can be broadcast to <code>values</code>
   * @param <T> the type of Operand
   * @throws NotBroadcastableException If static checks determine <code>sampleWeights</code> has an
   *     incorrect shape that prohibit broadcasting to <code>values</code>
   */
  @SuppressWarnings("unchecked")
  public static <T extends TNumber> Op assertBroadcastable(
      Ops tf, Operand<T> sampleWeights, Operand<T> values) {

    // try static check for exact match

    Shape weightsShapeStatic = sampleWeights.shape();
    int weightsRankStatic = weightsShapeStatic.numDimensions();

    Shape valuesShapeStatic = values.shape();
    int valuesRankStatic = valuesShapeStatic.numDimensions();

    // if (weightsRankStatic != Shape.UNKNOWN_SIZE && valuesRankStatic != Shape.UNKNOWN_SIZE) {
    if (!weightsShapeStatic.isUnknown()
        && !valuesShapeStatic.isUnknown()
        && !weightsShapeStatic.hasUnknownDimension()
        && !valuesShapeStatic.hasUnknownDimension()) {
      if (weightsRankStatic == 0) {
        return tf.withSubScope("staticScalarCheckSuccess")
            .withControlDependencies(Collections.EMPTY_LIST)
            .noOp();
      }
      if (weightsRankStatic != valuesRankStatic) {
        throw new NotBroadcastableException(
            String.format(
                "%s values.rank=%d. weights.rank=%d.  values.shape=%s. weights.shape=%s.",
                ASSERT_BROADCAST_ERROR_PREFIX,
                valuesRankStatic,
                weightsRankStatic,
                valuesShapeStatic.toString(),
                weightsShapeStatic.toString()));
      }

      for (int i = 0; i < valuesRankStatic; i++) {
        if (valuesShapeStatic.size(i) != weightsShapeStatic.size(i)
            && weightsShapeStatic.size(i) != 1) {
          throw new NotBroadcastableException(
              String.format(
                  "%s Mismatch at dim %d. values.shape=%s weights.shape=%s.",
                  ASSERT_BROADCAST_ERROR_PREFIX,
                  i,
                  valuesShapeStatic.toString(),
                  weightsShapeStatic.toString()));
        }
      }
      return tf.withSubScope("staticDimsCheckSuccess")
          .withControlDependencies(Collections.EMPTY_LIST)
          .noOp();
    }
    // Dynamic checks.
    Operand<TInt32> weightsShape = tf.shape(sampleWeights);
    Operand<TInt32> weightsRank = tf.rank(sampleWeights);
    Operand<TInt32> valuesShape = tf.shape(values);
    Operand<TInt32> valuesRank = tf.rank(values);

    Operand<TBool> isScalar = tf.math.equal(weightsRank, tf.constant(0));
    List<Operand<?>> data =
        Arrays.asList(
            tf.constant(ASSERT_BROADCAST_ERROR_PREFIX),
            tf.constant("weights.shape="),
            weightsShape,
            tf.constant("values.shape="),
            valuesShape,
            tf.constant("isScalar="),
            isScalar);

    // hack to work around the non-lazy select for isValidShape, otherwise validNonscalar fails on a
    // scalar weight. If select was lazy, that branch wouldn't get executed when iScalar is true.
    Operand<T> reshapedWeights =
        tf.select(isScalar, tf.math.mul(sampleWeights, tf.onesLike(values)), sampleWeights);
    weightsShape = tf.shape(reshapedWeights);
    weightsRank = tf.rank(reshapedWeights);

    Operand<TBool> validNonscalar =
        canBroadcastNonscalarShapes(tf, weightsRank, weightsShape, valuesRank, valuesShape);

    Operand<TBool> isValidShape = tf.select(isScalar, isScalar, validNonscalar);

    return tf.withSubScope("broadcastWeights-dynamic").assertThat(isValidShape, data);
  }

  /**
   * Gets an operand that tests if the shapes have the same rank and valid dimensions.
   *
   * @param tf the TensorFlow Ops
   * @param weightsRank the operand for the rank of the sample weights
   * @param weightsShape the operand for the shape of the sample weights
   * @param valuesRank the operand for the rank of the sample weights
   * @param valuesShape the operand for the shape of the sample weights
   * @param <T> the data type for the operands
   * @return a boolean operand to determine if the Shape is scalar or not.
   */
  private static <T extends TNumber> Operand<TBool> canBroadcastNonscalarShapes(
      Ops tf,
      Operand<T> weightsRank,
      Operand<T> weightsShape,
      Operand<T> valuesRank,
      Operand<T> valuesShape) {
    tf = tf.withSubScope("canBroadcastNonscalarShapes");
    Operand<TBool> isSameRank = tf.math.equal(valuesRank, weightsRank);
    return tf.select(isSameRank, canBroadcastDims(tf, weightsShape, valuesShape), isSameRank);
  }

  /**
   * Gets an operand that tests if the shapes have valid dimensions or not.
   *
   * @param tf the TensorFlow Ops
   * @param weightsShape the operand for the shape of the sample weights
   * @param valuesShape the operand for the shape of the values
   * @param <T> the data type for the operands
   * @return a boolean operand to determine if the shapes have valid dimensions or not.
   */
  private static <T extends TNumber> Operand<TBool> canBroadcastDims(
      Ops tf, Operand<T> weightsShape, Operand<T> valuesShape) {
    tf = tf.withSubScope("canBroadcastDims");
    Operand<T> valuesShape2d = tf.expandDims(valuesShape, tf.constant(-1));
    Operand<T> validDims =
        tf.concat(Arrays.asList(valuesShape2d, tf.onesLike(valuesShape2d)), tf.constant(1));
    Operand<T> weightsShape2D = tf.expandDims(weightsShape, tf.constant(-1));

    Operand<T> diffResult = SetsOps.difference(tf, weightsShape2D, validDims);
    Operand<TInt32> numInvalidDims = tf.size(diffResult);
    return tf.math.equal(tf.constant(0), numInvalidDims);
  }

  /**
   * Broadcast <code>weights</code> to the same shape as <code>values</code>.
   *
   * @param tf the TensorFlow ops
   * @param weights Operand whose shape is broadcastable to <code>values</code>.
   * @param values Operand of any shape
   * @param <T> the type of Operands
   * @return <code>weights</code> broadcast to <code>values</code> shape
   */
  public static <T extends TNumber> Operand<T> broadcastWeights(
      Ops tf, Operand<T> weights, Operand<T> values) {

    Shape weightsShape = weights.shape();
    Shape valuesShape = values.shape();

    if (!weightsShape.hasUnknownDimension()
        && !valuesShape.hasUnknownDimension()
        && weightsShape.isCompatibleWith(valuesShape)) {
      return weights;
    }

    Ops ctf =
        tf.withSubScope("broadcastWeights")
            .withControlDependencies(
                Collections.singletonList(assertBroadcastable(tf, weights, tf.onesLike(values))));
    return ctf.math.mul(weights, tf.onesLike(values));
  }

  // aliases for mean

  /**
   * Calculate the mean of the operand, along all axes and <code>keepDims</code> is <code>false
   * </code>
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand used to calculate the mean
   * @param <T> the type of the Operand.
   * @return the mean of the operand
   */
  public static <T extends TNumber> Operand<T> mean(Ops tf, Operand<T> x) {
    return mean(tf, x, null, false);
  }

  /**
   * Calculate the mean of the operand, alongside the specified axis with <code>keepDims</code> is
   * <code>false</code>
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand used to calculate the mean
   * @param axes Axes to compute the mean.
   * @param <T> the type of the Operand.
   * @return the mean of the operand, along the specified axes.
   */
  public static <T extends TNumber> Operand<T> mean(
      Ops tf, Operand<T> x, Operand<? extends TIntegral> axes) {
    return mean(tf, x, axes, false);
  }

  /**
   * Calculates the mean of the operand, along all axes.
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand used to calculate the mean
   * @param keepDims Indicates whether to keep the dimensions or not. If <code>keepdims</code> is
   *     <code>false</code>, the rank of the tensor is reduced by 1 for each entry in <code>axes
   *     </code>. If <code>keepdims</code> is <code>true</code>, the reduced dimensions are retained
   *     with length 1.
   * @param <T> the type of the operand
   * @return the mean of elements of <code>x</code>.
   */
  public static <T extends TNumber> Operand<T> mean(Ops tf, Operand<T> x, boolean keepDims) {
    return mean(tf, x, null, keepDims);
  }

  /**
   * Calculates the mean of the operand, alongside the specified axes.
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand used to calculate the mean
   * @param axes Axes to compute the mean.
   * @param keepDims Indicates whether to keep the dimensions or not. If <code>keepdims</code> is
   *     <code>false</code>, the rank of the tensor is reduced by 1 for each entry in <code>axes
   *     </code>. If <code>keepdims</code> is <code>true</code>, the reduced dimensions are retained
   *     with length 1.
   * @param <T> the data type of the Operand
   * @return the mean of elements of <code>x</code>.
   */
  public static <T extends TNumber> Operand<T> mean(
      Ops tf, Operand<T> x, Operand<? extends TIntegral> axes, boolean keepDims) {
    if (axes == null) {
      axes = allAxes(tf, x);
    }
    return tf.math.mean(x, axes, Mean.keepDims(keepDims));
  }

  /**
   * Calculate the mean of the operand, along all axes and <code>keepDims</code> is <code>false
   * </code>
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand used to calculate the mean
   * @return the mean of the operand containing floating point numbers
   */
  public static Operand<TFloat64> booleanMean(Ops tf, Operand<TBool> x) {
    return booleanMean(tf, x, null, false);
  }

  /**
   * Calculate the mean of the operand, alongside the specified axis with <code>keepDims</code> is
   * <code>false</code>
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand used to calculate the mean
   * @param axes Axes to compute the mean.
   * @return the mean of the operand, along the specified axes, containing floating point numbers
   */
  public static Operand<TFloat64> booleanMean(
      Ops tf, Operand<TBool> x, Operand<? extends TIntegral> axes) {
    return booleanMean(tf, x, axes, false);
  }

  /**
   * Calculates the mean of the boolean operand, alongside all axes.
   *
   * @param tf the TensorFlow Ops
   * @param x the boolean Operand used to calculate the mean
   * @param keepDims Indicates whether to keep the dimensions or not. If <code>keepdims</code> is
   *     <code>false</code>, the rank of the tensor is reduced by 1 for each entry in <code>axes
   *     </code>. If <code>keepdims</code> is <code>true</code>, the reduced dimensions are retained
   *     with length 1.
   * @return the mean of elements of <code>x</code> containing floating point numbers
   */
  public static Operand<TFloat64> booleanMean(Ops tf, Operand<TBool> x, boolean keepDims) {
    return booleanMean(tf, x, null, keepDims);
  }

  /**
   * Calculates the mean of the boolean operand, alongside the specified axes.
   *
   * @param tf the TensorFlow Ops
   * @param x the boolean Operand used to calculate the mean
   * @param axes Axes to compute the mean.
   * @param keepDims Indicates whether to keep the dimensions or not. If <code>keepdims</code> is
   *     <code>false</code>, the rank of the tensor is reduced by 1 for each entry in <code>axes
   *     </code>. If <code>keepdims</code> is <code>true</code>, the reduced dimensions are retained
   *     with length 1.
   * @return the mean of elements of <code>x</code> containing floating point numbers
   */
  public static Operand<TFloat64> booleanMean(
      Ops tf, Operand<TBool> x, Operand<? extends TIntegral> axes, boolean keepDims) {
    Operand<TFloat64> xf = cast(tf, x, TFloat64.class);
    return mean(tf, xf, axes, keepDims);
  }
}
