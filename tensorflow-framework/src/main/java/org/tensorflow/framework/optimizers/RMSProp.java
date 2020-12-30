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
 * Optimizer that implements the RMSProp algorithm.
 *
 * <p>The gist of RMSprop is to: <ul>
 * <li>Maintain a moving (discounted) average of the square of gradients
 * <li>Divide the gradient by the root of this average </ul>
 *
 *     <p>This implementation of RMSprop uses plain momentum, not Nesterov momentum.
 *
 *     <p>The centered version additionally maintains a moving average of the gradients, and uses
 *     that average to estimate the variance.
 *
 * @see <a href="http://www.cs.toronto.edu/~tijmen/csc321/slides/lecture_slides_lec6.pdf">Hinton G,
 *    et al. 2012, lecture notes</a> that is inexplicably the canonical reference.
 */
public class RMSProp extends Optimizer {

  public static final float LEARNING_RATE_DEFAULT = 0.001f;
  public static final float DECAY_DEFAULT = 0.9f;
  public static final float MOMENTUM_DEFAULT = 0.0f;
  public static final float EPSILON_DEFAULT = 1e-10f;
  public static final boolean CENTERED_DEFAULT = false;
  public static final String RMS = "rms";
  public static final String MG = "mg"; // mean gradient?
  public static final String MOMENTUM = "momentum";

  private final float learningRate;
  private final float decay;
  private final float momentum;
  private final float epsilon;
  private final boolean centered;

  /**
   * Creates an RMSPRrop Optimizer
   *
   * @param graph the TensorFlow Graph
   */
  public RMSProp(Graph graph) {
    this(
        graph,
        LEARNING_RATE_DEFAULT,
        DECAY_DEFAULT,
        MOMENTUM_DEFAULT,
        EPSILON_DEFAULT,
        CENTERED_DEFAULT);
  }

  /**
   * Creates an RMSPRrop Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param learningRate the learning rate
   */
  public RMSProp(Graph graph, float learningRate) {
    this(graph, learningRate, DECAY_DEFAULT, MOMENTUM_DEFAULT, EPSILON_DEFAULT, CENTERED_DEFAULT);
  }

  /**
   * Creates an RMSPRrop Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param learningRate the learning rate
   * @param decay Discounting factor for the history/coming gradient. Defaults to 0.9.
   * @param momentum the acceleration factor, default is 0.
   * @param epsilon A small constant for numerical stability
   * @param centered If <code>true</code>, gradients are normalized by the estimated variance of the
   *     gradient; if <code>false</code>, by the uncentered second moment. Setting this to <code>
   *     true</code> may help with training, but is slightly more expensive in terms of computation
   *     and memory. Defaults to <code>false</code>.
   */
  public RMSProp(
      Graph graph,
      float learningRate,
      float decay,
      float momentum,
      float epsilon,
      boolean centered) {
    super(graph);
    this.learningRate = learningRate;
    this.decay = decay;
    this.momentum = momentum;
    this.epsilon = epsilon;
    this.centered = centered;
  }

  /**
   * Creates an RMSPRrop Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param name the name of this Optimizer. Defaults to "RMSProp".
   * @param learningRate the learning rate
   */
  public RMSProp(Graph graph, String name, float learningRate) {
    this(
        graph,
        name,
        learningRate,
        DECAY_DEFAULT,
        MOMENTUM_DEFAULT,
        EPSILON_DEFAULT,
        CENTERED_DEFAULT);
  }

  /**
   * Creates an RMSPRrop Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param name the name of this Optimizer. Defaults to "RMSProp".
   * @param learningRate the learning rate
   * @param decay Discounting factor for the history/coming gradient. Defaults to 0.9.
   * @param momentum The acceleration factor, default is 0.
   * @param epsilon A small constant for numerical stability
   * @param centered If <code>true</code>, gradients are normalized by the estimated variance of the
   *     gradient; if <code>false</code>, by the uncentered second moment. Setting this to <code>
   *     true</code> may help with training, but is slightly more expensive in terms of computation
   *     and memory. Defaults to <code>false</code>.
   */
  public RMSProp(
      Graph graph,
      String name,
      float learningRate,
      float decay,
      float momentum,
      float epsilon,
      boolean centered) {
    super(graph, name);
    this.learningRate = learningRate;
    this.decay = decay;
    this.momentum = momentum;
    this.epsilon = epsilon;
    this.centered = centered;
  }

  /** {@inheritDoc} */
  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createRMSPropSlot(v);
    }
  }


  /**
   * Creates the RMSProp Slots for Root Mean Squared (RMS),
   * MOMENTUM, and Mean Gradient (MG)
   *
   * @param v the variable to install in the slot
   * @param <T> the datatype of the variable.
   */
  private <T extends TType> void createRMSPropSlot(Output<T> v) {
    Operand<T> rmsInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(1.0f), v.type()));
    createSlot(v.asOutput(), RMS, rmsInitializer);
    Operand<T> momentumInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.type()));
    createSlot(v.asOutput(), MOMENTUM, momentumInitializer);
    if (centered) {
      Operand<T> mgInitializer =
          tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.type()));
      createSlot(v.asOutput(), MG, mgInitializer);
    }
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> rmsSlot = getSlot(variable, RMS).get();
    Variable<T> momentumSlot = getSlot(variable, MOMENTUM).get();
    if (centered) {
      Variable<T> mgSlot = getSlot(variable, MG).get();
      return tf.train.applyCenteredRmsProp(
          variable,
          mgSlot,
          rmsSlot,
          momentumSlot,
          tf.dtypes.cast(tf.constant(learningRate), gradient.type()),
          tf.dtypes.cast(tf.constant(decay), gradient.type()),
          tf.dtypes.cast(tf.constant(momentum), gradient.type()),
          tf.dtypes.cast(tf.constant(epsilon), gradient.type()),
          gradient);
    }
    return tf.train.applyRmsProp(
        variable,
        rmsSlot,
        momentumSlot,
        tf.dtypes.cast(tf.constant(learningRate), gradient.type()),
        tf.dtypes.cast(tf.constant(decay), gradient.type()),
        tf.dtypes.cast(tf.constant(momentum), gradient.type()),
        tf.dtypes.cast(tf.constant(epsilon), gradient.type()),
        gradient);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "RMSProp{"
        + "learningRate="
        + learningRate
        + ", decay="
        + decay
        + ", momentum="
        + momentum
        + ", epsilon="
        + epsilon
        + ", centered="
        + centered
        + '}';
  }

  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return "RMSProp";
  }
}
