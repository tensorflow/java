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
import org.tensorflow.framework.initializers.Zeros;
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.framework.metrics.Metric;
import org.tensorflow.framework.metrics.MetricReduction;
import org.tensorflow.framework.utils.CastHelper;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Encapsulates metrics that perform a reduce operation on the metric values.
 *
 * @param <U> The data type for the metric values
 * @param <T> The data type for the metric result
 */
public abstract class Reduce<U extends TNumber, T extends TNumber> extends Metric<U, T> {
  public static final String TOTAL = "total";
  public static final String COUNT = "count";
  protected final MetricReduction reduction;
  private final String totalName;
  private final String countName;

  private final Class<T> type;
  /** the variable that holds the total of the metric values */
  protected Variable<T> total;
  /** the variable that holds the count of the metric values */
  protected Variable<T> count;

  protected boolean initialized;

  /**
   * Creates a Reducible Metric with a metric reductions of {@link MetricReduction#SUM}
   *
   * @param tf the TensorFlow Ops
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected Reduce(Ops tf, String name, long seed, Class<T> type) {
    this(tf, name, MetricReduction.SUM, seed, type);
  }

  /**
   * @param tf The TensorFlow Ops
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param reduction The type of metric reduction to apply
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  protected Reduce(Ops tf, String name, MetricReduction reduction, long seed, Class<T> type) {
    super(tf, name, seed);
    this.reduction = reduction;
    this.totalName = this.getVariableName(TOTAL);
    this.countName = this.getVariableName(COUNT);
    this.type = type;
    setupVars();
  }
  /** initialize the Variables */
  @SuppressWarnings("unchecked")
  private void setupVars() {
    Zeros<T> fZeros = new Zeros<>(getTF());
    total = (Variable<T>) getVariable(totalName);
    if (total == null) {
      total = getTF().withSubScope(totalName).variable(Shape.scalar(), type);
      addVariable(totalName, total, fZeros);
    }
    if (reduction == MetricReduction.SUM_OVER_BATCH_SIZE
        || reduction == MetricReduction.WEIGHTED_MEAN) {
      count = (Variable<T>) getVariable(countName);
      if (count == null) {
        count = getTF().withSubScope(countName).variable(Shape.scalar(), type);
        addVariable(countName, count, fZeros);
      }
    }
  }

  /**
   * Updates the metric variables based on the inputs. At least one input arg required for <code>
   * values</code>, an optional additional input for the <code>sampleWeights</code>
   *
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return the result with a control dependency on update state Operands
   * @throws IllegalArgumentException if values is null
   */
  @Override
  public  List<Op> updateStateList(Operand<U> values, Operand<T> sampleWeights) {

    if (values == null) {
      throw new IllegalArgumentException("values is required.");
    }
    List<Op> updateOperations = new ArrayList<>();
    // cast everything to match the variables
    Operand<U> lSampleWeights = null;
    Operand<U> lValues = values;

    if (sampleWeights != null) {
      lSampleWeights = CastHelper.cast(getTF(), sampleWeights, lValues.type());
      LossTuple<U> tuple =
          LossesHelper.squeezeOrExpandDimensions(getTF(), null, lValues, lSampleWeights);
      lValues = tuple.getTarget();
      lSampleWeights = tuple.getSampleWeights();
      try {

        Op broadcastWeightsCheck = MetricsHelper.broadcastWeights(getTF(), lSampleWeights, lValues);
        lValues =
            getTF()
                .withSubScope("broadcastWeightsCheck")
                .withControlDependencies(Collections.singletonList(broadcastWeightsCheck))
                .math
                .mul(lValues, lSampleWeights);
      } catch (IllegalArgumentException ex) {
        System.out.println("Reduce: Fall back from broadcast");
        // reduce the values down to the rank of the samples
        int nDim = lValues.shape().numDimensions();
        int wDim = lSampleWeights.shape().numDimensions();
        int numAxes = nDim - wDim;
        int[] axes = new int[numAxes];
        for (int i = 0; i < numAxes; i++) axes[i] = i + wDim;
        if (reduction == MetricReduction.SUM) {
          lValues = getTF().reduceSum(lValues, getTF().constant(axes));
        } else {
          lValues = getTF().math.mean(lValues, getTF().constant(axes));
        }
        lValues = getTF().math.mul(lValues, lSampleWeights);
      }
    }

    Operand<U> valueSum = getTF().reduceSum(lValues, LossesHelper.allAxes(getTF(), lValues));
    Operand<T> totalUpdate =
        getTF().assignAdd(total, CastHelper.cast(getTF(), valueSum, total.type()));
    updateOperations.add(totalUpdate);
    Operand<T> numValues;
    if (reduction != MetricReduction.SUM) {
      switch (reduction) {
        case SUM_OVER_BATCH_SIZE:
          numValues = CastHelper.cast(getTF(), getTF().constant(lValues.shape().size()), type);
          break;
        case WEIGHTED_MEAN:
          if (lSampleWeights == null) {
            numValues = CastHelper.cast(getTF(), getTF().constant(lValues.shape().size()), type);
          } else {
            numValues =
                CastHelper.cast(
                    getTF(),
                    getTF()
                        .reduceSum(lSampleWeights, LossesHelper.allAxes(getTF(), lSampleWeights)),
                    type);
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
  public Operand<T> result(Ops rtf) {
    Operand<T> fResult;

    switch (this.reduction) {
      case SUM:
        fResult = rtf.identity(total);
        break;
      case WEIGHTED_MEAN:
      case SUM_OVER_BATCH_SIZE:
        fResult = rtf.math.divNoNan(total, CastHelper.cast(rtf, count, type));
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

  /** Gets the type for the variables
   * @return  the type for the variables
   */
  public Class<T> getType() {
    return type;
  }
}
