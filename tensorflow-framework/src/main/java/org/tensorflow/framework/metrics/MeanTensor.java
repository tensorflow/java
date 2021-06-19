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
public class MeanTensor<T extends TNumber> extends Metric<T> {
  /** The name of the total variable */
  public static final String TOTAL = "total";
  /** The name of the count variable */
  public static final String COUNT = "count";

  private final String totalName;
  private final String countName;
  private Shape shape;
  private Variable<T> total;
  private Variable<T> count;
  private Assign<T> totalInitializer;
  private Assign<T> countInitializer;
  private boolean initialized;

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
    super(name, seed, type);
    this.totalName = this.getVariableName(TOTAL);
    this.countName = this.getVariableName(COUNT);
  }

  /**
   * Creates the Operations that initialize the total and count variables.
   *
   * @param shape the shape of the variables
   */
  private void init(Shape shape) {
    if (!initialized) {
      this.shape = shape;
      Zeros<T> zeros = new Zeros<>();
      Operand<T> zero = zeros.call(getTF(), getTF().constant(shape), getResultType());

      if (total == null) {
        variablesNeedAssign = true;
        total = getTF().withName(totalName).variable(zero);
        totalInitializer = getTF().assign(total, zero);
      }
      if (count == null) {
        variablesNeedAssign = true;
        count = getTF().withName(countName).variable(zero);
        countInitializer = getTF().assign(count, zero);
      }
      this.initialized = true;
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
    init(tf);
    Operand<T> tValues = cast(getTF(), values, getResultType());
    Operand<T> tSampleWeights =
        sampleWeights == null ? null : cast(getTF(), sampleWeights, getResultType());

    // update the shape if it is the first call.
    init(values.shape());

    if (!this.shape.equals(values.shape())) {
      throw new IllegalArgumentException(
          String.format(
              "MeanTensor input values must always have the same shape. Expected shape (set during the first call): %s. Got %s",
              this.shape.toString(), values.shape().toString()));
    }

    Operand<T> numValues = getTF().onesLike(tValues);
    if (tSampleWeights != null) {
      // Update dimensions of weights to match with values if possible.
      LossTuple<T> tuple =
          LossesHelper.squeezeOrExpandDimensions(getTF(), null, tValues, tSampleWeights);
      tValues = tuple.getTarget();
      tSampleWeights = tuple.getSampleWeights();
      try {
        // Broadcast weights if possible.
        tSampleWeights = WeightsBroadcastOps.broadcastWeights(getTF(), tSampleWeights, tValues);
      } catch (IllegalArgumentException ex) {
        // sampleWeights cannot be broadcast to values
        //  Reduce values to same ndim as weight array
        int ndim = values.shape().numDimensions();
        int weightNdim = tSampleWeights.asOutput().shape().numDimensions();
        int[] range = new int[ndim - weightNdim];
        for (int i = weightNdim; i < ndim; i++) {
          range[i] = i;
        }
        tValues = getTF().math.mean(tValues, getTF().constant(range));
      }
      numValues = getTF().math.mul(numValues, tSampleWeights);
      tValues = getTF().math.mul(tValues, tSampleWeights);
    }

    List<Op> controlOps = new ArrayList<>();
    controlOps.add(
        variablesNeedAssign
            ? getTF().assign(this.count, numValues)
            : getTF().assignAdd(this.count, numValues));
    controlOps.add(
        variablesNeedAssign
            ? getTF().assign(this.total, tValues)
            : getTF().assignAdd(this.total, tValues));
    variablesNeedAssign = false;
    return controlOps;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> result(Ops tf) {
    init(tf);
    if (total == null || count == null || variablesNeedAssign) {
      return getResultZero();
    } else {
      return getTF().math.divNoNan(total, count);
    }
  }

  /**
   * Gets the total variable
   *
   * @return the total
   */
  public Variable<T> getTotal() {
    return total;
  }

  /**
   * Gets the count count variable
   *
   * @return the count variable
   */
  public Variable<T> getCount() {
    return count;
  }

  /** {@inheritDoc} */
  @Override
  public Op resetStates(Ops tf) {
    init(tf);
    if (countInitializer != null && totalInitializer != null) {
      List<Op> controlOpsPre = new ArrayList<>();
      controlOpsPre.add(countInitializer);
      controlOpsPre.add(totalInitializer);
      return getTF().withSubScope("resetStates").withControlDependencies(controlOpsPre).noOp();
    } else {
      return getTF().withSubScope("resetStates").noOp();
    }
  }
}
