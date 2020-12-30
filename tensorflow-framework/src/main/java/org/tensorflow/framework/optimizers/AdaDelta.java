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
 * Optimizer that implements the Adadelta algorithm.
 *
 * <p>Adadelta optimization is a stochastic gradient descent method that is based on adaptive
 * learning rate per dimension to address two drawbacks:
 *
 * <ul>
 * <li>the continual decay of learning rates throughout training
 * <li>the need for a manually selected global learning rate
 * </ul>
 *
 *     <p>Adadelta is a more robust extension of Adagrad that adapts learning rates based on a
 *     moving window of gradient updates, instead of accumulating all past gradients. This way,
 *     Adadelta continues learning even when many updates have been done. Compared to Adagrad, in
 *     the original version of Adadelta you don't have to set an initial learning rate. In this
 *     version, initial learning rate can be set, as in most other optimizers.
 *
 *     <p>According to section 4.3 ("Effective Learning rates"), near the end of training step sizes
 *     converge to 1 which is effectively a high learning rate which would cause divergence. This
 *     occurs only near the end of the training as gradients and step sizes are small, and the
 *     epsilon constant in the numerator and denominator dominate past gradients and parameter
 *     updates which converge the learning rate to 1.
 *
 *     <p>According to section 4.4("Speech Data"),where a large neural network with 4 hidden layers
 *     was trained on a corpus of US English data, ADADELTA was used with 100 network replicas.The
 *     epsilon used is 1e-6 with rho=0.95 which converged faster than ADAGRAD, by the following
 *     construction: <code> new AdaDelta(graph, 1.0f, 0.95f, 1e-6f); </code>
 *
 * @see <a href="http://arxiv.org/abs/1212.5701">Zeiler, M., 2012 ADADELTA: An Adaptive Learning
 *    Rate Method</a>.
 */
public class AdaDelta extends Optimizer {

  public static final String ACCUMULATOR = "accum";
  public static final String ACCUMULATOR_UPDATE = "accum_update";
  public static final float LEARNING_RATE_DEFAULT = 0.001f;
  public static final float RHO_DEFAULT = 0.95f;
  public static final float EPSILON_DEFAULT = 1e-7f;

  private final float learningRate;

  private final float rho;

  private final float epsilon;

  public AdaDelta(Graph graph) {
    this(graph, LEARNING_RATE_DEFAULT, RHO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates an AdaDelta Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param learningRate the learning rate
   */
  public AdaDelta(Graph graph, float learningRate) {
    this(graph, learningRate, RHO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates an AdaDelta Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param learningRate the learning rate
   * @param rho The decay factor
   * @param epsilon A constant epsilon used to better conditioning the grad update
   */
  public AdaDelta(Graph graph, float learningRate, float rho, float epsilon) {
    super(graph);
    this.learningRate = learningRate;
    this.rho = rho;
    this.epsilon = epsilon;
  }

  /**
   * Creates an AdaDelta Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param name the name for this Optimizer (defaults to 'Adadelta')
   * @param learningRate the learning rate
   */
  public AdaDelta(Graph graph, String name, float learningRate) {
    this(graph, name, learningRate, 0.95f, 1e-8f);
  }

  /**
   * Creates an AdaDelta Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param name the name for this Optimizer (defaults to 'Adadelta')
   * @param learningRate the learning rate
   * @param rho The decay factor
   * @param epsilon A constant epsilon used to better conditioning the grad update
   */
  public AdaDelta(Graph graph, String name, float learningRate, float rho, float epsilon) {
    super(graph, name);
    this.learningRate = learningRate;
    this.rho = rho;
    this.epsilon = epsilon;
  }

  /** {@inheritDoc} */
  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createAdaDeltaSlot(v);
    }
  }

  /**
   * Creates an AdaDelta Slot
   *
   * @param v the variable to install in the slot
   * @param <T> the datatype of the variable.
   */
  private <T extends TType> void createAdaDeltaSlot(Output<T> v) {
    Operand<T> accumulatorInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.type()));
    createSlot(v.asOutput(), ACCUMULATOR, accumulatorInitializer);
    Operand<T> updateInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.type()));
    createSlot(v.asOutput(), ACCUMULATOR_UPDATE, updateInitializer);
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> accumSlot = getSlot(variable, ACCUMULATOR).get();
    Variable<T> accumUpdateSlot = getSlot(variable, ACCUMULATOR_UPDATE).get();
    return tf.train.applyAdadelta(
        variable,
        accumSlot,
        accumUpdateSlot,
        tf.dtypes.cast(tf.constant(learningRate), gradient.type()),
        tf.dtypes.cast(tf.constant(rho), gradient.type()),
        tf.dtypes.cast(tf.constant(epsilon), gradient.type()),
        gradient);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "AdaDelta{"
        + "learningRate="
        + learningRate
        + ", rho="
        + rho
        + ", epsilon="
        + epsilon
        + '}';
  }

  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return "Adadelta";
  }
}
