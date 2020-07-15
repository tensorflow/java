/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow.framework.optimizers;

import java.util.List;
import java.util.Optional;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Variable;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Optimizer that implements the Adagrad Dual-Averaging algorithm.
 * <p>
 * See the <a href="http://www.jmlr.org/papers/volume12/duchi11a/duchi11a.pdf">paper</a>.
 */
public class AdaGradDA extends Optimizer {

  public static final String ACCUMULATOR = "gradient_accumulator";
  public static final String SQUARED_ACCUMULATOR = "gradient_squared_accumulator";
  private final float learningRate;
  private final float initialAccumulatorValue;
  private final float l1Strength;
  private final float l2Strength;
  private Variable<TInt64> globalStep;

  public AdaGradDA(Graph graph, float learningRate) {
    this(graph, learningRate, 0.1f, 0.0f, 0.0f);
  }

  public AdaGradDA(Graph graph, float learningRate, float initialAccumulatorValue, float l1Strength,
      float l2Strength) {
    super(graph);
    this.learningRate = learningRate;
    this.initialAccumulatorValue = initialAccumulatorValue;
    this.l1Strength = l1Strength;
    this.l2Strength = l2Strength;
  }

  public AdaGradDA(Graph graph, String name, float learningRate) {
    this(graph, name, learningRate, 0.1f, 0.0f, 0.0f);
  }

  public AdaGradDA(Graph graph, String name, float learningRate, float initialAccumulatorValue, float l1Strength,
      float l2Strength) {
    super(graph, name);
    this.learningRate = learningRate;
    this.initialAccumulatorValue = initialAccumulatorValue;
    this.l1Strength = l1Strength;
    this.l2Strength = l2Strength;
  }

  @Override
  protected Optional<Op> prepare(String name) {
    return Optional.of(tf.assignAdd(globalStep, tf.constant(1L)));
  }

  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createAdaGradDASlot(v);
    }
    globalStep = tf.withName("adagrad-da-global-step").variable(Shape.scalar(), TInt64.DTYPE);
    Assign<TInt64> globalStepInitializer = tf.assign(globalStep, tf.constant(0L));
    graph.addInitializer(globalStepInitializer);
  }

  private <T extends TType> void createAdaGradDASlot(Output<T> v) {
    Operand<T> initializer = tf
        .fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), ACCUMULATOR, initializer);
    Operand<T> sqInitializer = tf.fill(tf.shape(v),
        tf.dtypes.cast(tf.constant(initialAccumulatorValue), v.dataType()));
    createSlot(v.asOutput(), SQUARED_ACCUMULATOR, sqInitializer);
  }

  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> gradSlot = getSlot(variable, ACCUMULATOR).get();
    Variable<T> gradSquaredSlot = getSlot(variable, SQUARED_ACCUMULATOR).get();
    return tf.train.applyAdagradDa(variable, gradSlot, gradSquaredSlot, gradient,
        tf.dtypes.cast(tf.constant(learningRate), gradient.dataType()),
        tf.dtypes.cast(tf.constant(l1Strength), gradient.dataType()),
        tf.dtypes.cast(tf.constant(l2Strength), gradient.dataType()),
        globalStep);
  }

  /**
   * Gathers up the update operations into a single op that can be used as a run target.
   * <p>
   * Adds the global step update to the end of the updates list.
   *
   * @param updateOperations The update operations.
   * @param name             The name of the run target.
   * @return A NoOp with a control dependency on each update operation.
   */
  @Override
  protected Op finish(List<Op> updateOperations, String name) {
    updateOperations.add(tf.assignAdd(globalStep, tf.constant(1L)));
    return super.finish(updateOperations, name);
  }

  @Override
  public String toString() {
    return "AdaGradDA{" +
        "globalStep=" + globalStep +
        ", learningRate=" + learningRate +
        ", initialAccumulatorValue=" + initialAccumulatorValue +
        ", l1Strength=" + l1Strength +
        ", l2Strength=" + l2Strength +
        '}';
  }

  @Override
  public String getOptimizerName() {
    return "adagrad-da";
  }
}
