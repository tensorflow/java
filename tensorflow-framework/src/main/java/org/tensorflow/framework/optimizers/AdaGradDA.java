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

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

import java.util.List;
import java.util.Optional;

/**
 * Optimizer that implements the Adagrad Dual-Averaging algorithm.
 *
 * <p>This optimizer takes care of regularization of unseen features in a mini batch by updating
 * them when they are seen with a closed form update rule that is equivalent to having updated them
 * on every mini-batch.
 *
 * <p>AdagradDA is typically used when there is a need for large sparsity in the trained model. This
 * optimizer only guarantees sparsity for linear models. Be careful when using AdagradDA for deep
 * networks as it will require careful initialization of the gradient accumulators for it to train.
 *
 * @see <a href="http://www.jmlr.org/papers/volume12/duchi11a/duchi11a.pdf">Duchi, J, et al., 2011,
 *    Adaptive Subgradient Methods for Online Learning and Stochastic Optimization</a>.
 */
public class AdaGradDA extends Optimizer {

  public static final String ACCUMULATOR = "gradient_accumulator";
  public static final String SQUARED_ACCUMULATOR = "gradient_squared_accumulator";
  public static final float LEARNING_RATE_DEFAULT = 0.001F;
  public static final float INITIAL_ACCUMULATOR_DEFAULT = 0.1f;
  public static final float L1_STRENGTH_DEFAULT = 0.0F;
  public static final float L2_STRENGTH_DEFAULT = 0.0F;

  private final float learningRate;
  private final float initialAccumulatorValue;
  private final float l1Strength;
  private final float l2Strength;
  private Variable<TInt64> globalStep;

  /**
   * Creates an AdaGradDA Optimizer
   *
   * @param graph the TensorFlow Graph
   */
  public AdaGradDA(Graph graph) {
    this(
        graph,
        LEARNING_RATE_DEFAULT,
        INITIAL_ACCUMULATOR_DEFAULT,
        L1_STRENGTH_DEFAULT,
        L2_STRENGTH_DEFAULT);
  }

  /**
   * Creates an AdaGradDA Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param learningRate the learning rate
   */
  public AdaGradDA(Graph graph, float learningRate) {
    this(
        graph, learningRate, INITIAL_ACCUMULATOR_DEFAULT, L1_STRENGTH_DEFAULT, L2_STRENGTH_DEFAULT);
  }

  /**
   * Creates an AdaGradDA Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param learningRate the learning rate
   * @param initialAccumulatorValue Starting value for the accumulators, must be greater than zero.
   * @param l1Strength l1 regularization strength, must be greater than or equal to zero.
   * @param l2Strength l2 regularization strength, must be greater than or equal to zero.
   * @throws java.lang.IllegalArgumentException if initialAccumulatorValue is not greater than zero,
   *     or l1Strength or l2Strength is less than zero
   */
  public AdaGradDA(
      Graph graph,
      float learningRate,
      float initialAccumulatorValue,
      float l1Strength,
      float l2Strength) {
    super(graph);
    if (initialAccumulatorValue <= 0F) {
      throw new IllegalArgumentException(
          String.format(
              "initialAccumulatorValue must be greater than zero: %f", initialAccumulatorValue));
    }
    if (l1Strength < 0F) {
      throw new IllegalArgumentException(
          String.format("l1Strength must not be negative: %f", l1Strength));
    }
    if (l2Strength < 0F) {
      throw new IllegalArgumentException(
          String.format("l2Strength must not be negative: %f", l2Strength));
    }
    this.learningRate = learningRate;
    this.initialAccumulatorValue = initialAccumulatorValue;
    this.l1Strength = l1Strength;
    this.l2Strength = l2Strength;
  }

  /**
   * Creates an AdaGradDA Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param name the name for this Optimizer (defaults to 'adagrad-da')
   * @param learningRate the learning rate
   */
  public AdaGradDA(Graph graph, String name, float learningRate) {
    this(
        graph,
        name,
        learningRate,
        INITIAL_ACCUMULATOR_DEFAULT,
        L1_STRENGTH_DEFAULT,
        L2_STRENGTH_DEFAULT);
  }

  /**
   * Creates an AdaGradDA Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param name the name for this Optimizer (defaults to 'adagrad-da')
   * @param learningRate the learning rate
   * @param initialAccumulatorValue Starting value for the accumulators, must be positive
   * @param l1Strength l1 regularization strength, must be greater than or equal to zero.
   * @param l2Strength l2 regularization strength, must be greater than or equal to zero.
   * @throws java.lang.IllegalArgumentException if initialAccumulatorValue is not greater than zero,
   *     or * l1Strength or l2Strength is less than zero
   */
  public AdaGradDA(
      Graph graph,
      String name,
      float learningRate,
      float initialAccumulatorValue,
      float l1Strength,
      float l2Strength) {
    super(graph, name);
    if (initialAccumulatorValue <= 0F) {
      throw new IllegalArgumentException(
          String.format(
              "initialAccumulatorValue must be greater than zero: %f", initialAccumulatorValue));
    }
    if (l1Strength < 0F) {
      throw new IllegalArgumentException(
          String.format("l1Strength must not be negative: %f", l1Strength));
    }
    if (l2Strength < 0F) {
      throw new IllegalArgumentException(
          String.format("l2Strength must not be negative: %f", l2Strength));
    }
    this.learningRate = learningRate;
    this.initialAccumulatorValue = initialAccumulatorValue;
    this.l1Strength = l1Strength;
    this.l2Strength = l2Strength;
  }

  /** {@inheritDoc} */
  @Override
  protected Optional<Op> prepare(String name) {
    return Optional.of(tf.assignAdd(globalStep, tf.constant(1L)));
  }

  /** {@inheritDoc} */
  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createAdaGradDASlot(v);
    }
    globalStep = tf.withName("adagrad-da-global-step").variable(Shape.scalar(), TInt64.class);
    Assign<TInt64> globalStepInitializer = tf.assign(globalStep, tf.constant(0L));
    graph.addInitializer(globalStepInitializer);
  }

  /**
   * Creates an AdaGradDA Slot
   *
   * @param v the variable to install in the slot
   * @param <T> the datatype of the variable.
   */
  private <T extends TType> void createAdaGradDASlot(Output<T> v) {
    Operand<T> initializer = tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.type()));
    createSlot(v.asOutput(), ACCUMULATOR, initializer);
    Operand<T> sqInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(initialAccumulatorValue), v.type()));
    createSlot(v.asOutput(), SQUARED_ACCUMULATOR, sqInitializer);
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> gradSlot = getSlot(variable, ACCUMULATOR).get();
    Variable<T> gradSquaredSlot = getSlot(variable, SQUARED_ACCUMULATOR).get();
    return tf.train.applyAdagradDa(
        variable,
        gradSlot,
        gradSquaredSlot,
        gradient,
        tf.dtypes.cast(tf.constant(learningRate), gradient.type()),
        tf.dtypes.cast(tf.constant(l1Strength), gradient.type()),
        tf.dtypes.cast(tf.constant(l2Strength), gradient.type()),
        globalStep);
  }

  /**
   * Gathers up the update operations into a single op that can be used as a run target.
   *
   * <p>Adds the global step update to the end of the updates list.
   *
   * @param updateOperations The update operations.
   * @param name The name of the run target.
   * @return A NoOp with a control dependency on each update operation.
   */
  @Override
  protected Op finish(List<Op> updateOperations, String name) {
    updateOperations.add(tf.assignAdd(globalStep, tf.constant(1L)));
    return super.finish(updateOperations, name);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "AdaGradDA{"
        + "globalStep="
        + globalStep
        + ", learningRate="
        + learningRate
        + ", initialAccumulatorValue="
        + initialAccumulatorValue
        + ", l1Strength="
        + l1Strength
        + ", l2Strength="
        + l2Strength
        + '}';
  }

  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return "adagrad-da";
  }
}
