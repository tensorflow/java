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
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.framework.metrics.Metric;
import org.tensorflow.framework.metrics.MetricReduction;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TNumber;

import java.util.ArrayList;
import java.util.List;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Encapsulates metrics that perform a reduce operation on the metric values.
 *
 * @param <T> The data type for the metric result
 */
public abstract class Reduce<T extends TNumber> extends Metric<T> {
  /** the default name for the total variable */
  public static final String TOTAL = "total";
  /** the default name for the count variable */
  public static final String COUNT = "count";
  /** The metric reduction to use */
  protected final MetricReduction reduction;

  private final String totalName;
  private final String countName;

  private final Class<T> resultType;
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
   * @param resultType the type for the variables and result
   */
  protected Reduce(String name, long seed, Class<T> resultType) {
    this(name, MetricReduction.SUM, seed, resultType);
  }

  /**
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param reduction The type of metric reduction to apply
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param resultType the type for the variables and result
   */
  protected Reduce(String name, MetricReduction reduction, long seed, Class<T> resultType) {
    super(name, seed);
    this.reduction = reduction;
    this.totalName = this.getVariableName(TOTAL);
    this.countName = this.getVariableName(COUNT);
    this.resultType = resultType;
  }

  /**
   * Creates a Reducible Metric with a metric reductions of {@link MetricReduction#SUM}
   *
   * @param tf the TensorFlow Ops
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param resultType the type for the variables and result
   */
  protected Reduce(Ops tf, String name, long seed, Class<T> resultType) {
    this(tf, name, MetricReduction.SUM, seed, resultType);
  }

  /**
   * @param tf The TensorFlow Ops
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param reduction The type of metric reduction to apply
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param resultType the type for the variables and result
   */
  protected Reduce(Ops tf, String name, MetricReduction reduction, long seed, Class<T> resultType) {
    this(name, reduction, seed, resultType);
    init(tf);
  }
  /** {@inheritDoc} */
  @Override
  public Ops init(Ops tf) {
    super.init(tf);
    if (total == null) {
      total = getTF().withName(totalName).variable(Shape.scalar(), resultType);
    }
    if (reduction == MetricReduction.SUM_OVER_BATCH_SIZE
        || reduction == MetricReduction.WEIGHTED_MEAN) {
      if (count == null) {
        count = getTF().withName(countName).variable(Shape.scalar(), resultType);
      }
    }
    return getTF();
  }

  /** {@inheritDoc} */
  public Op resetStates() {
    List<Op> controls = new ArrayList<>();
    if (total != null) {
      controls.add(checkTF().assign(total, cast(getTF(), getTF().constant(0), total.type())));
    }
    if (count != null) {
      controls.add(checkTF().assign(count, cast(getTF(), getTF().constant(0), count.type())));
    }
    return checkTF().withControlDependencies(controls).noOp();
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
      Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights) {

    if (values == null) {
      throw new IllegalArgumentException("values is required.");
    }
    Ops tf = checkTF();
    List<Op> updateOperations = new ArrayList<>();
    // cast everything to match the variables
    Operand<T> tSampleWeights = null;
    Operand<T> tValues = cast(tf, values, getResultType());

    if (sampleWeights != null) {
      tSampleWeights = cast(getTF(), sampleWeights, getResultType());
      // Update dimensions of weights to match with values if possible.
      LossTuple<T> tuple =
          LossesHelper.squeezeOrExpandDimensions(getTF(), null, tValues, tSampleWeights);
      tValues = tuple.getTarget();
      tSampleWeights = tuple.getSampleWeights();
      try {
        // Broadcast weights if possible
        tSampleWeights = MetricsHelper.broadcastWeights(getTF(), tSampleWeights, tValues);
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
            tValues = getTF().reduceSum(tValues, getTF().constant(axes));
          } else {
            tValues = getTF().math.mean(tValues, getTF().constant(axes));
          }
        }
      }
      tValues = getTF().math.mul(tValues, tSampleWeights);
    }

    Operand<? extends TNumber> weightedValueSum =
        getTF().reduceSum(tValues, LossesHelper.allAxes(getTF(), tValues));
    Operand<T> totalUpdate =
        getTF().assignAdd(total, cast(getTF(), weightedValueSum, total.type()));
    updateOperations.add(totalUpdate);
    Operand<T> numValues;
    // Exit early if the reduction doesn't have a denominator.
    if (reduction != MetricReduction.SUM) {
      // Update `count` for reductions that require a denominator.
      switch (reduction) {
        case SUM_OVER_BATCH_SIZE:
          numValues = cast(getTF(), getTF().constant(tValues.shape().size()), resultType);
          break;
        case WEIGHTED_MEAN:
          if (tSampleWeights == null) {
            numValues = cast(getTF(), getTF().constant(tValues.shape().size()), resultType);
          } else {
            numValues =
                cast(
                    getTF(),
                    getTF()
                        .reduceSum(tSampleWeights, LossesHelper.allAxes(getTF(), tSampleWeights)),
                    resultType);
          }
          break;
        default:
          throw new UnsupportedOperationException(
              String.format("reduction [%s] not implemented", reduction));
      }

      Operand<T> totalCount = getTF().assignAdd(this.count, numValues);

      updateOperations.add(totalCount);
    }

    return updateOperations;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> result() {
    Operand<T> fResult;

    switch (this.reduction) {
      case SUM:
        fResult = checkTF().identity(total);
        break;
      case WEIGHTED_MEAN:
      case SUM_OVER_BATCH_SIZE:
        fResult = checkTF().math.divNoNan(total, cast(getTF(), count, resultType));
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
  public Class<T> getResultType() {
    return resultType;
  }
}
