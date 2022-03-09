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
package org.tensorflow.framework.metrics;

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.ArrayList;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.framework.initializers.Zeros;
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.framework.metrics.impl.WeightsBroadcastOps;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TNumber;

/**
 * Metric that computes the element-wise (weighted) mean of the given tensors.
 *
 * @param <T> The data type for the metric result
 */
public class MeanTensor<T extends TNumber> extends BaseMetric {
  public static final String TOTAL = "total";
  public static final String COUNT = "count";
  private final String totalName;
  private final String countName;
  private final Class<T> type;
  private Shape shape;
  private Variable<T> total;
  private Variable<T> count;
  private Assign<T> totalInitializer;
  private Assign<T> countInitializer;

  /**
   * Creates a MeanTensor metric, using {@link Class#getSimpleName()} as the name
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public MeanTensor(long seed, Class<T> type) {
    this(null, seed, type);
  }
  /**
   * Creates a MeanTensor metric
   *
   * @param name the name of this metric, if null then {@link Class#getSimpleName()} is used
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public MeanTensor(String name, long seed, Class<T> type) {
    super(name, seed);
    this.type = type;
    this.totalName = this.getVariableName(TOTAL);
    this.countName = this.getVariableName(COUNT);
  }

  /** {@inheritDoc} */
  @Override
  protected void init(Ops tf) {
    checkIsGraph(tf);
    if (!isInitialized() && shape != null) {
      setTF(tf);
      Zeros<T> zeros = new Zeros<>();
      Operand<T> zero = zeros.call(tf, tf.constant(shape), type);

      if (total == null) {
        total = tf.withName(totalName).withInitScope().variable(zero);
        totalInitializer = tf.assign(total, zero);
      }
      if (count == null) {
        count = tf.withName(countName).withInitScope().variable(zero);
        countInitializer = tf.assign(count, zero);
      }
      setInitialized(true);
    }
  }

  /**
   * Accumulates statistics for computing the element-wise mean.
   *
   * @param values Per-example value. Input values must always have the same shape for all
   *     invocations of updateStateList.
   * @param sampleWeights Optional weighting of each example. Defaults to 1 if null.
   * @throws IllegalArgumentException if the shape of {@code values} in each subsequent call is not
   *     the same shape as {@code values} set during the first call
   */
  @Override
  public List<Op> updateStateList(
      Ops tf, Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights) {
    if (shape == null) {
      shape = values.shape();
    }
    init(tf);
    Operand<T> tValues = cast(tf, values, type);
    Operand<T> tSampleWeights = sampleWeights == null ? null : cast(tf, sampleWeights, type);

    if (!shape.equals(values.shape())) {
      throw new IllegalArgumentException(
          String.format(
              "MeanTensor input values must always have the same shape. Expected shape (set during the first call): %s. Got %s",
              shape.toString(), values.shape().toString()));
    }

    Operand<T> numValues = tf.onesLike(tValues);
    if (tSampleWeights != null) {
      // Update dimensions of weights to match with values if possible.
      LossTuple<T> tuple =
          LossesHelper.squeezeOrExpandDimensions(tf, null, tValues, tSampleWeights);
      tValues = tuple.getTarget();
      tSampleWeights = tuple.getSampleWeights();
      try {
        // Broadcast weights if possible.
        tSampleWeights = WeightsBroadcastOps.broadcastWeights(tf, tSampleWeights, tValues);
      } catch (IllegalArgumentException ex) {
        // sampleWeights cannot be broadcast to values
        //  Reduce values to same ndim as weight array
        int ndim = values.shape().numDimensions();
        int weightNdim = tSampleWeights.asOutput().shape().numDimensions();
        int[] range = new int[ndim - weightNdim];
        for (int i = weightNdim; i < ndim; i++) {
          range[i] = i;
        }
        tValues = tf.math.mean(tValues, tf.constant(range));
      }
      numValues = tf.math.mul(numValues, tSampleWeights);
      tValues = tf.math.mul(tValues, tSampleWeights);
    }

    Ops tf1 = tf.withSubScope("MeanTensor.variables");

    List<Op> controlOps = new ArrayList<>();
    controlOps.add(tf1.assignAdd(this.count, numValues));
    controlOps.add(tf1.assignAdd(this.total, tValues));
    return controlOps;
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TNumber> Operand<U> result(Ops tf, Class<U> resultType) {
    init(tf);
    if (!isInitialized()) {
      return cast(tf, tf.constant(0), resultType);
    } else {
      return cast(tf, tf.math.divNoNan(total, count), resultType);
    }
  }

  /**
   * @return the total
   */
  public Variable<T> getTotal() {
    return total;
  }

  /**
   * @return the count
   */
  public Variable<T> getCount() {
    return count;
  }

  /** {@inheritDoc} */
  @Override
  public Op resetStates(Ops tf) {
    init(tf);
    if (!isInitialized()) {
      return tf.withSubScope("resetStates").noOp();
    } else {
      List<Op> controlOpsPre = new ArrayList<>();
      controlOpsPre.add(countInitializer);
      controlOpsPre.add(totalInitializer);
      return tf.withSubScope("resetStates").withControlDependencies(controlOpsPre).noOp();
    }
  }
}
