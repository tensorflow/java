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

import java.util.ArrayList;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.framework.metrics.BaseMetric;
import org.tensorflow.framework.metrics.MetricReduction;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TNumber;

/** Encapsulates metrics that perform a reduce operation on the metric values. */
public abstract class Reduce<T extends TNumber> extends BaseMetric {
  public static final String TOTAL = "total";
  public static final String COUNT = "count";
  protected final MetricReduction reduction;
  private final String totalName;
  private final String countName;

  private final Class<T> internalType;
  /** the variable that holds the total of the metric values */
  protected Variable<T> total;
  /**
   * the variable that holds the count of the metric values. For {@link
   * MetricReduction#WEIGHTED_MEAN}, this count may be weighted
   */
  protected Variable<T> count;

  /**
   * Creates a Reducible Metric with a metric reductions of {@link MetricReduction#SUM}
   *
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param internalType the type for the internal variables
   */
  protected Reduce(String name, long seed, Class<T> internalType) {
    this(name, MetricReduction.SUM, seed, internalType);
  }

  /**
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param reduction The type of metric reduction to apply
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param internalType the type for the internal variables
   */
  protected Reduce(String name, MetricReduction reduction, long seed, Class<T> internalType) {
    super(name, seed);
    this.reduction = reduction;
    this.totalName = this.getVariableName(TOTAL);
    this.countName = this.getVariableName(COUNT);
    this.internalType = internalType;
  }
  /** {@inheritDoc} */
  @Override
  protected void init(Ops tf) {
    checkIsGraph(tf);

    if (!isInitialized()) {
      setTF(tf);
      Operand<T> zero = cast(tf, tf.constant(0), internalType);
      if (total == null) {
        total = tf.withInitScope().withName(totalName).variable(zero);
      }
      if (reduction == MetricReduction.SUM_OVER_BATCH_SIZE
          || reduction == MetricReduction.WEIGHTED_MEAN) {
        if (count == null) {
          count = tf.withInitScope().withName(countName).variable(zero);
        }
      }
      setInitialized(true);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Op resetStates(Ops tf) {
    if (!isInitialized()) {
      init(tf);
    }
    List<Op> operandList = new ArrayList<>();
    if (total != null) {
      operandList.add(tf.assign(total, cast(tf, tf.constant(0), total.type())));
    }
    if (count != null) {
      operandList.add(tf.assign(count, cast(tf, tf.constant(0), count.type())));
    }
    if (operandList.size() == 1) {
      return operandList.get(0);
    } else {
      return tf.withControlDependencies(operandList).noOp();
    }
  }

  /**
   * Updates the metric variables based on the inputs. At least one input arg required for {@code
   * values}, an optional additional input for the {@code sampleWeights}
   *
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, will default to 1 if null.
   * @return the result with a control dependency on update state Operands
   * @throws IllegalArgumentException if values is null
   */
  @Override
  public List<Op> updateStateList(
      Ops tf, Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights) {

    if (values == null) {
      throw new IllegalArgumentException("values is required.");
    }
    init(tf);
    List<Op> updateOperations = new ArrayList<>();
    // cast everything to match the variables
    Operand<T> tSampleWeights = null;
    Operand<T> tValues = cast(tf, values, getInternalType());

    if (sampleWeights != null) {
      tSampleWeights = cast(tf, sampleWeights, getInternalType());
      // Update dimensions of weights to match with values if possible.
      LossTuple<T> tuple =
          LossesHelper.squeezeOrExpandDimensions(tf, null, tValues, tSampleWeights);
      tValues = tuple.getTarget();
      tSampleWeights = tuple.getSampleWeights();
      try {
        // Broadcast weights if possible
        tSampleWeights = MetricsHelper.broadcastWeights(tf, tSampleWeights, tValues);
      } catch (IllegalArgumentException ex) {
        // reduce values to same ndim as weight array
        // if we get here we have static shapes with either
        // different ranks or different dimension sizes.
        // first, reduce the values down to the rank of the samples
        int valuesRank = tValues.shape().numDimensions();
        int weightsRank = tSampleWeights.shape().numDimensions();
        int numAxes = Math.min(0, valuesRank - weightsRank);
        if (numAxes
            > 0) { // values rank is greater than weights rank, reduce values to weights rank.
          int[] axes = new int[numAxes];
          for (int i = 0; i < numAxes; i++) axes[i] = i + weightsRank;
          if (reduction == MetricReduction.SUM) {
            tValues = tf.reduceSum(tValues, tf.constant(axes));
          } else {
            tValues = tf.math.mean(tValues, tf.constant(axes));
          }
        }
      }
      tValues = tf.math.mul(tValues, tSampleWeights);
    }

    Operand<? extends TNumber> weightedValueSum =
        tf.reduceSum(tValues, LossesHelper.allAxes(tf, tValues));
    Operand<T> totalUpdate = tf.assignAdd(total, cast(tf, weightedValueSum, total.type()));
    updateOperations.add(totalUpdate);
    Operand<T> numValues;
    // Exit early if the reduction doesn't have a denominator.
    if (reduction != MetricReduction.SUM) {
      // Update `count` for reductions that require a denominator.
      switch (reduction) {
        case SUM_OVER_BATCH_SIZE:
          numValues = cast(tf, tf.constant(tValues.shape().size()), internalType);
          break;
        case WEIGHTED_MEAN:
          if (tSampleWeights == null) {
            numValues = cast(tf, tf.constant(tValues.shape().size()), internalType);
          } else {
            numValues =
                cast(
                    tf,
                    tf.reduceSum(tSampleWeights, LossesHelper.allAxes(tf, tSampleWeights)),
                    internalType);
          }
          break;
        default:
          throw new UnsupportedOperationException(
              String.format("reduction [%s] not implemented", reduction));
      }

      Operand<T> totalCount = tf.assignAdd(this.count, numValues);

      updateOperations.add(totalCount);
    }

    return updateOperations;
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TNumber> Operand<U> result(Ops tf, Class<U> type) {
    Operand<U> fResult;
    init(tf);
    switch (this.reduction) {
      case SUM:
        fResult = cast(tf, tf.identity(total), type);
        break;
      case WEIGHTED_MEAN:
      case SUM_OVER_BATCH_SIZE:
        fResult = cast(tf, tf.math.divNoNan(total, count), type);
        break;
      default:
        throw new UnsupportedOperationException(
            String.format("reduction [%s] not implemented", reduction));
    }
    return fResult;
  }

  /**
   * Gets the total variable
   *
   * @return the total variable
   */
  public Variable<T> getTotal() {
    return total;
  }

  /**
   * Gets the count variable
   *
   * @return the count variable
   */
  public Variable<T> getCount() {
    return count;
  }

  /**
   * Gets the type for the variables
   *
   * @return the type for the variables
   */
  public Class<T> getInternalType() {
    return internalType;
  }
}
