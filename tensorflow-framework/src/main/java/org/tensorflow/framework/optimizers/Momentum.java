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
import org.tensorflow.op.train.ApplyMomentum;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

import java.util.List;

/**
 * Stochastic gradient descent plus momentum, either nesterov or traditional.
 *
 * <p>See the <a href="http://jmlr.org/proceedings/papers/v28/sutskever13.pdf">paper</a> for details
 * of nesterov momentum.
 */
public class Momentum extends Optimizer {

  public static final String DEFAULT_NAME = "Momentum";
  public static final float LEARNING_RATE_DEFAULT = 0.01F;
  public static final float MOMENTUM_DEFAULT = 0.0F;
  public static final boolean NESTEROV_DEFAULT = false;

  public static final String MOMENTUM = "momentum";

  private final float momentum;

  private final boolean useNesterov;

  /**
   * Creates a Momentum Optimizer using {@link #DEFAULT_NAME} for the Optimizer name, {@link
   * #LEARNING_RATE_DEFAULT} for the learning rate, {@link #MOMENTUM_DEFAULT} for the momentum, and
   * {@link #NESTEROV_DEFAULT} for the Nesterov flag.
   *
   * @param graph the TensorFlow graph
   */
  public Momentum(Graph graph) {
    this(graph, LEARNING_RATE_DEFAULT, MOMENTUM_DEFAULT, NESTEROV_DEFAULT);
  }

  /**
   * Creates a Momentum Optimizer using {@link #DEFAULT_NAME} for the Optimizer name, {@link
   * #MOMENTUM_DEFAULT} for the momentum, and {@link #NESTEROV_DEFAULT} for the Nesterov flag.
   *
   * @param graph the TensorFlow graph
   * @param learningRate the learning rate
   */
  public Momentum(Graph graph, float learningRate) {
    this(graph, learningRate, MOMENTUM_DEFAULT, NESTEROV_DEFAULT);
  }

  /**
   * Creates a Momentum Optimizer using {@link #DEFAULT_NAME} for the Optimizer name, {@link
   * #MOMENTUM_DEFAULT} for the momentum, and {@link #NESTEROV_DEFAULT} for the Nesterov flag.
   *
   * @param graph the TensorFlow graph
   * @param learningRateOperand the learning rate Operand, this is used to calculate the learning
   *     rate.
   */
  public Momentum(Graph graph, Operand<TFloat32> learningRateOperand) {
    this(graph, learningRateOperand, MOMENTUM_DEFAULT, NESTEROV_DEFAULT);
  }

  /**
   * Creates a Momentum Optimizer using {@link #DEFAULT_NAME} for the Optimizer name and {@link
   * #NESTEROV_DEFAULT} for the Nesterov flag.
   *
   * @param graph the TensorFlow graph
   * @param learningRate the learning rate
   * @param momentum hyperparameter that accelerates gradient descent in the relevant direction and
   *     dampens oscillations, Must be greater than or equal to zero.
   * @throws java.lang.IllegalArgumentException if momentum is less than zero.
   */
  public Momentum(Graph graph, float learningRate, float momentum) {
    this(graph, learningRate, momentum, NESTEROV_DEFAULT);
  }

  /**
   * Creates a Momentum Optimizer using {@link #DEFAULT_NAME} for the Optimizer name and {@link
   * #NESTEROV_DEFAULT} for the Nesterov flag.
   *
   * @param graph the TensorFlow graph
   * @param learningRateOperand the learning rate Operand, this is used to calculate the learning
   *     rate.
   * @param momentum hyperparameter that accelerates gradient descent in the relevant direction and
   *     dampens oscillations, Must be greater than or equal to zero.
   * @throws java.lang.IllegalArgumentException if momentum is less than zero.
   */
  public Momentum(Graph graph, Operand<TFloat32> learningRateOperand, float momentum) {
    this(graph, learningRateOperand, momentum, NESTEROV_DEFAULT);
  }

  /**
   * Creates a Momentum Optimizer using {@link #DEFAULT_NAME} for the Optimizer name.
   *
   * @param graph the TensorFlow graph
   * @param learningRate the learning rate
   * @param momentum hyperparameter that accelerates gradient descent in the relevant direction and
   *     dampens oscillations, Must be greater than or equal to zero.
   * @param useNesterov Whether to apply Nesterov momentum.
   * @throws java.lang.IllegalArgumentException if momentum is less than zero.
   */
  public Momentum(Graph graph, float learningRate, float momentum, boolean useNesterov) {
    this(graph, null, learningRate, momentum, useNesterov);
  }

  /**
   * Creates a Momentum Optimizer using {@link #DEFAULT_NAME} for the Optimizer name.
   *
   * @param graph the TensorFlow graph
   * @param learningRateOperand the learning rate Operand, this is used to calculate the learning
   *     rate.
   * @param momentum hyperparameter that accelerates gradient descent in the relevant direction and
   *     dampens oscillations, Must be greater than or equal to zero.
   * @param useNesterov Whether to apply Nesterov momentum.
   * @throws java.lang.IllegalArgumentException if momentum is less than zero.
   */
  public Momentum(
      Graph graph, Operand<TFloat32> learningRateOperand, float momentum, boolean useNesterov) {
    this(graph, null, learningRateOperand, momentum, useNesterov);
  }

  /**
   * Creates a Momentum Optimizer
   *
   * @param graph the TensorFlow graph
   * @param name the name for this Optimizer
   * @param learningRate the learning rate
   * @param momentum hyperparameter that accelerates gradient descent in the relevant direction and
   *     dampens oscillations, Must be greater than or equal to zero.
   * @param useNesterov Whether to apply Nesterov momentum.
   * @throws java.lang.IllegalArgumentException if momentum is less than zero.
   */
  public Momentum(
      Graph graph, String name, float learningRate, float momentum, boolean useNesterov) {
    super(graph, name, learningRate);
    if (momentum < 0)
      throw new IllegalArgumentException("momentum must be greater than or equal to zero.");
    this.momentum = momentum;
    this.useNesterov = useNesterov;
  }

  /**
   * Creates a Momentum Optimizer
   *
   * @param graph the TensorFlow graph
   * @param name the name for this Optimizer
   * @param learningRateOperand the learning rate Operand, this is used to calculate the learning
   *     rate.
   * @param momentum hyperparameter that accelerates gradient descent in the relevant direction and
   *     dampens oscillations, Must be greater than or equal to zero.
   * @param useNesterov Whether to apply Nesterov momentum.
   * @throws java.lang.IllegalArgumentException if momentum is less than zero.
   */
  public Momentum(
      Graph graph,
      String name,
      Operand<TFloat32> learningRateOperand,
      float momentum,
      boolean useNesterov) {
    super(graph, name, learningRateOperand);
    if (momentum < 0)
      throw new IllegalArgumentException("momentum must be greater than or equal to zero.");
    this.momentum = momentum;
    this.useNesterov = useNesterov;
  }

  /** {@inheritDoc} */
  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createMomentumSlot(v);
    }
  }

  /**
   * Creates a slot for the momentum variable
   *
   * @param v the variable
   * @param <T> the data type of the variable
   */
  private <T extends TType> void createMomentumSlot(Output<T> v) {
    Operand<T> initializer = tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), MOMENTUM, initializer);
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> slot = getSlot(variable, MOMENTUM).get();
    return tf.train.applyMomentum(
        variable,
        slot,
        tf.dtypes.cast(getLearningRateOperand(), gradient.dataType()),
        gradient,
        tf.dtypes.cast(tf.constant(momentum), gradient.dataType()),
        ApplyMomentum.useNesterov(useNesterov));
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "Momentum{"
        + "learningRate="
        + learningRate
        + ", momentum="
        + momentum
        + ", useNesterov="
        + useNesterov
        + '}';
  }

  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return DEFAULT_NAME;
  }
}
