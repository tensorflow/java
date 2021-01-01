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
import org.tensorflow.framework.utils.CastHelper;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * These are helper methods for Metrics and will be module private when Java modularity is applied
 * to TensorFlow Java. These methods should not be used outside of the metrics packages.
 */
public class MetricsHelper {
  private static final String ASSERT_BROADCASTABLE_ERROR_PREFIX =
      "weights can not be broadcast to values.";

  /**
   * Asserts that the <code>sampleWeight</code> can be broadcast to <code>values</code>
   *
   * @param tf the TensorFlow Ops
   * @param sampleWeights the sample weights.
   * @param values the values to which weights are applied.
   * @return <code>Operation</code> raising <code>InvalidArgumentError</code> if <code>sampleWeight
   *     </code> has incorrect shape. <code>no_op</code> if static checks determine <code>
   *     sampleWeight</code> has correct shape.
   * @param <U> the type of Operand
   * @throws IllegalArgumentException If static checks determine `weights` has incorrect shape.
   */
  @SuppressWarnings("unchecked")
  public static <U extends TNumber> Op broadcastWeights(
      Ops tf, Operand<U> sampleWeights, Operand<U> values) {

    Operand<TInt32> weightsShape = tf.shape(sampleWeights);
    Operand<TInt32> weightsRank = tf.rank(sampleWeights);
    Shape weightsShapeStatic = sampleWeights.asOutput().shape();
    int weightsRankStatic = weightsShapeStatic.numDimensions();

    Operand<TInt32> valuesShape = tf.shape(values);
    Operand<TInt32> valuesRank = tf.rank(values);
    Shape valuesShapeStatic = values.asOutput().shape();
    int valuesRankStatic = valuesShapeStatic.numDimensions();

    if (weightsRankStatic != -1 && valuesRankStatic != -1) {
      if (weightsRankStatic == 0) {
        return tf.withSubScope("static_scalar_check_success")
            .withControlDependencies(Collections.EMPTY_LIST)
            .noOp();
      }
      if (weightsRankStatic != valuesRankStatic) {
        throw new IllegalArgumentException(
            String.format(
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
                  "%s Mismatch at dim %d. values.shape=%s weights.shape=%s.",
                  ASSERT_BROADCASTABLE_ERROR_PREFIX,
                  i,
                  valuesShapeStatic.toString(),
                  weightsShapeStatic.toString()));
        }
      }
      return tf.withSubScope("static_dims_check_success")
          .withControlDependencies(Collections.EMPTY_LIST)
          .noOp();
    }
    // Dynamic checks.
    Operand<TBool> is_scalar = tf.math.equal(weightsRank, tf.constant(0));
    List<Operand<?>> data =
        Arrays.asList(
            tf.constant(ASSERT_BROADCASTABLE_ERROR_PREFIX),
            tf.constant("weights.shape="),
            weightsShape,
            tf.constant("values.shape="),
            valuesShape,
            tf.constant("is_scalar="),
            is_scalar);

    Operand<TBool> isValidShape =
        tf.select(
            is_scalar,
            is_scalar,
            hasValidNonscalarShape(tf, weightsRank, weightsShape, valuesRank, valuesShape));

    return tf.assertThat(isValidShape, data);
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

  /**
   * Gets an operand that tests if the shapes have valid dimensions or not.
   *
   * @param tf the TensorFlow Ops
   * @param weightsShape the operand for the shape of the sample weights
   * @param valuesShape the operand for the shape of the sample weights
   * @param <T> the data type for the operands
   * @return a boolean operand to determine if the shapes have valid dimensions or not.
   */
  private static <T extends TNumber> Operand<TBool> hasValidDims(
      Ops tf, Operand<T> weightsShape, Operand<T> valuesShape) {
    tf = tf.withSubScope("has_invalid_dims");
    Operand<T> diff = tf.reduceSum(tf.math.sub(weightsShape, valuesShape), tf.constant(0));
    return tf.math.equal(CastHelper.cast(tf, tf.constant(0), diff.type()), diff);
  }
}
