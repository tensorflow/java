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
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.types.family.TType;

/**
 * Basic Stochastic gradient descent optimizer. GradientDescent updates the current weight using the
 * current gradient ?L/?w multiplied by the learning rate.
 */
public class GradientDescent extends Optimizer {

  public static final float LEARNING_RATE_DEFAULT = 0.01f;

  private final float learningRate;

  /**
   * Creates a GradientDescent Optimizer
   *
   * @param graph the TensorFlow graph
   */
  public GradientDescent(Graph graph) {
    this(graph, LEARNING_RATE_DEFAULT);
  }

  /**
   * Creates a GradientDescent Optimizer
   *
   * @param graph the TensorFlow graph
   * @param learningRate the learning rate, defaults to 0.01
   */
  public GradientDescent(Graph graph, float learningRate) {
    super(graph);
    this.learningRate = learningRate;
  }

  /**
   * Creates a GradientDescent Optimizer
   *
   * @param graph the TensorFlow graph
   * @param name the name for this Optimizer, default is "GradientDescent"
   * @param learningRate the learning rate, defaults to 0.01
   */
  public GradientDescent(Graph graph, String name, float learningRate) {
    super(graph, name);
    this.learningRate = learningRate;
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    return tf.train.applyGradientDescent(
        variable, tf.dtypes.cast(tf.constant(learningRate), gradient.type()), gradient);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "GradientDescent{" + "learningRate=" + learningRate + '}';
  }

  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return "GradientDescent";
  }
}
