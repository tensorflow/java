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
import org.tensorflow.op.Op;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TType;

import java.util.List;

/**
 * Optimizer that implements the Adagrad algorithm.
 *
 * <p>Adagrad is an optimizer with parameter-specific learning rates, which are adapted relative to
 * how frequently a parameter gets updated during training. The more updates a parameter receives,
 * the smaller the updates.
 *
 * <p>
 *
 * @see <a href="http://www.jmlr.org/papers/volume12/duchi11a/duchi11a.pdf">Duchi, J, et al., 2011, Adaptive Subgradient Methods for Online Learning and Stochastic Optimization</a>
 * @see <a href="https://ppasupat.github.io/a9online/uploads/proximal_notes.pdf">Duchi, J, et al., 2013, Proximal and First-Order Methods for Convex Optimization, Introduction Section 1</a>.
 */
public class AdaGrad extends Optimizer {

  public static final String ACCUMULATOR = "accumulator";
  public static final float LEARNING_RATE_DEFAULT = 0.001f;
  public static final float INITIAL_ACCUMULATOR_DEFAULT = 0.01f;

  private final float learningRate;

  private final float initialAccumulatorValue;

  /**
   * Creates an AdaGrad Optimizer
   *
   * @param graph the TensorFlow Graph
   */
  public AdaGrad(Graph graph) {
    this(graph, LEARNING_RATE_DEFAULT, INITIAL_ACCUMULATOR_DEFAULT);
  }

  /**
   * Creates an AdaGrad Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param learningRate the learning rate
   */
  public AdaGrad(Graph graph, float learningRate) {
    this(graph, learningRate, INITIAL_ACCUMULATOR_DEFAULT);
  }

  /**
   * Creates an AdaGrad Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param learningRate the learning rate
   * @param initialAccumulatorValue Starting value for the accumulators, must be non-negative.
   * @throws java.lang.IllegalArgumentException if initialAccumulatorValue is negative
   */
  public AdaGrad(Graph graph, float learningRate, float initialAccumulatorValue) {
    super(graph);
    if (initialAccumulatorValue < 0F) {
      throw new IllegalArgumentException(
          String.format(
              "initialAccumulatorValue must be non-negative: %f", initialAccumulatorValue));
    }
    this.learningRate = learningRate;
    this.initialAccumulatorValue = initialAccumulatorValue;
  }

  /**
   * Creates an AdaGrad Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param name the name for this Optimizer (defaults to 'Adagrad')
   * @param learningRate the learning rate
   */
  public AdaGrad(Graph graph, String name, float learningRate) {
    this(graph, name, learningRate, 0.01f);
  }

  /**
   * Creates an AdaGrad Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param name the name for this Optimizer (defaults to 'Adagrad')
   * @param learningRate the learning rate
   * @param initialAccumulatorValue Starting value for the accumulators, must be non-negative.
   * @throws java.lang.IllegalArgumentException if initialAccumulatorValue is negative
   */
  public AdaGrad(Graph graph, String name, float learningRate, float initialAccumulatorValue) {
    super(graph, name);
    if (initialAccumulatorValue < 0F) {
      throw new IllegalArgumentException(
          String.format(
              "initialAccumulatorValue must be non-negative: %f", initialAccumulatorValue));
    }
    this.learningRate = learningRate;
    this.initialAccumulatorValue = initialAccumulatorValue;
  }

  /** {@inheritDoc} */
  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createAdaGradSlot(v);
    }
  }

  /**
   * Creates an AdaGrad Slot
   *
   * @param v the variable to install in the slot
   * @param <T> the datatype of the variable.
   */
  private <T extends TType> void createAdaGradSlot(Output<T> v) {
    Operand<T> initializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(initialAccumulatorValue), v.type()));
    createSlot(v.asOutput(), ACCUMULATOR, initializer);
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> slot = getSlot(variable, ACCUMULATOR).get();
    return tf.train.applyAdagrad(
        variable, slot, tf.dtypes.cast(tf.constant(learningRate), gradient.type()), gradient);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "AdaGrad{"
        + "learningRate="
        + learningRate
        + ", initialAccumulatorValue="
        + initialAccumulatorValue
        + '}';
  }

  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return "Adagrad";
  }
}
