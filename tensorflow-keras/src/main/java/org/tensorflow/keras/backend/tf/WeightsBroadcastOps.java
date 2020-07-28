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

package org.tensorflow.keras.backend.tf;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.keras.utils.ShapeUtils;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;


/** Weights Broadcast Ops */
public class WeightsBroadcastOps {

  private static final String ASSERT_BROADCASTABLE_ERROR_PREFIX =
      "weights can not be broadcast to values.";

  /**
   * Asserts `weights` can be broadcast to `values`
   *
   * @param <T>
   * @param tf the TensorFlow Ops
   * @param weights `Tensor` of weights.
   * @param values `Tensor` of values to which weights are applied.
   * @return `Operation` raising `InvalidArgumentError` if `weights` has incorrect shape. `no_op` if
   *     static checks determine `weights` has correct shape.
   * @param <T> the type of Operand
   * @throws IllegalArgumentException If static checks determine `weights` has incorrect shape.
   */
  public static <T extends TNumber> Op assertBroadcastable(
      Ops tf, Operand<T> weights, Operand<T> values) {
    Operand weightsShape = tf.shape(weights);
    Operand weightsRank = tf.rank(weights);
    Shape weightsShapeStatic = weights.asOutput().shape();
    int weightsRankStatic = weightsShapeStatic.numDimensions();

    Operand valuesShape = tf.shape(values);
    Operand valuesRank = tf.rank(values);
    Shape valuesShapeStatic = values.asOutput().shape();
    int valuesRankStatic = valuesShapeStatic.numDimensions();

    if (weightsRankStatic != -1 && valuesRankStatic != -1) {
      if (weightsRankStatic == 0) {
        return ControlDependencies.addControlDependencies(tf, "static_scalar_check_success");
      }
      if (weightsRankStatic != valuesRankStatic) {
        throw new IllegalArgumentException(
            String.format(
                "",
                "%s values.rank=%d. weights.rank=%d.  values.shape=%s. weights.shape=%s.",
                ASSERT_BROADCASTABLE_ERROR_PREFIX,
                valuesRankStatic,
                weightsRankStatic,
                valuesShapeStatic.toString(),
                weightsShapeStatic.toString()));
      }

      for (int i = 0; i < valuesRankStatic; i++) {
        if (valuesShapeStatic.size(i) != weightsShapeStatic.size(i)) {
          throw new IllegalArgumentException(
              String.format(
                  "",
                  "%s Mismatch at dim %s. values.shape=%s weights.shape=%s.",
                  ASSERT_BROADCASTABLE_ERROR_PREFIX,
                  i,
                  valuesShapeStatic.toString(),
                  weightsShapeStatic.toString()));
        }
      }
      return ControlDependencies.addControlDependencies(tf, "static_dims_check_success");
    }
    // Dynamic checks.
    Operand is_scalar = tf.math.equal(weightsRank, tf.constant(0));
    List<Operand<?>> data =
        Arrays.asList(
            tf.constant(ASSERT_BROADCASTABLE_ERROR_PREFIX),
            tf.constant("weights.shape="),
            weightsShape,
            tf.constant("values.shape="),
            valuesShape,
            tf.constant("is_scalar="),
            is_scalar);

    Operand isValidShape =
        tf.select(
            is_scalar,
            is_scalar,
            hasValidNonscalarShape(tf, weightsRank, weightsShape, valuesRank, valuesShape));

    return tf.assertThat(isValidShape, data);
  }

  private static <T extends TNumber> Operand<TBool> hasValidNonscalarShape(
      Ops tf,
      Operand<T> weightsRank,
      Operand<T> weightsShape,
      Operand<T> valuesRank,
      Operand<T> valuesShape) {
    tf = tf.withSubScope("has_valid_nonscalar_shape");
    Operand<TBool> isSameRank = tf.math.equal(valuesRank, weightsRank);
    return tf.select(isSameRank, hasValidDims(tf, weightsShape, valuesShape), isSameRank);
  }

  private static <T extends TType> Operand<TBool> hasValidDims(
      Ops tf, Operand<T> weightsShape, Operand valuesShape) {
    tf = tf.withSubScope("has_invalid_dims");
    Operand diff = tf.reduceSum(tf.math.sub(weightsShape, valuesShape), tf.constant(0));
    return tf.math.equal(tf.constant(0), diff);
  }

  /**
   * Broadcast `weights` to the same shape as `values`.
   *
   * @param tf the TensorFlow ops
   * @param weights `Tensor` whose shape is broadcastable to `values`
   * @param values Tensor` of any shape
   * @param <T> the type of Operand
   * @return `weights` broadcast to `values` shape
   */
  public static <T extends TNumber> Operand<T> broadcastWeights(
      Ops tf, Operand<T> weights, Operand<T> values) {
    tf = tf.withSubScope("broadcast_weights");
    values = tf.dtypes.cast(values, weights.asOutput().dataType());

    Shape weightsShape = weights.asOutput().shape();
    Shape valuesShape = values.asOutput().shape();

    if (!weightsShape.hasUnknownDimension()
        && !valuesShape.hasUnknownDimension()
        && ShapeUtils.isCompatibleWith(weightsShape, valuesShape)) {
      return weights;
    }

    weights = tf.math.mul(weights, tf.onesLike(values));
    Op dependencies = assertBroadcastable(tf, weights, values);
    final Operand weightsFinal = weights;
    return ControlDependencies.addControlDependencies(
        tf, tfc -> tfc.identity(weightsFinal), "assertBroadcastable", dependencies);
  }
}
