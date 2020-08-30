/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the );
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an  BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.keras.optimizers;

import org.tensorflow.op.Ops;

import java.util.HashMap;
import java.util.Map;

import static org.tensorflow.keras.optimizers.OptimizerInterface.assertGraph;

/** Stochastic Gradient Descent and momentum optimizer. */
public class SGD extends org.tensorflow.framework.optimizers.Momentum
    implements OptimizerInterface {

  public static final String LEARNING_RATE_KEY = "learning_rate";
  public static final String MOMENTUM_KEY = "momentum";
  public static final String NESTEROV_KEY = "nesterov";

  public static final float LEARNING_RATE_DEFAULT = 0.01F;
  public static final float MOMENTUM_DEFAULT = 0.0F;
  public static final boolean NESTEROV_DEFAULT = false;

  private Map<String, Object> config = new HashMap<>();

  /**
   * Create a Stochastic Gradient Descent optimizer using defaults: name="SGD", learning_rate=0.01,
   * momentum=0.0, and nesterov=false
   *
   * @param tf the TensorFlow Ops
   */
  public SGD(Ops tf) {
    this(tf, LEARNING_RATE_DEFAULT, MOMENTUM_DEFAULT, NESTEROV_DEFAULT);
  }

  /**
   * Create a Stochastic gradient descent optimizer using defaults: name="SGD", momentum=0.0, and
   * nesterov=false
   *
   * @param tf the TensorFlow Ops
   * @param learningRate The learning rate. Defaults to 0.01.
   */
  public SGD(Ops tf, float learningRate) {
    this(tf, learningRate, MOMENTUM_DEFAULT, NESTEROV_DEFAULT);
  }

  /**
   * Create a Stochastic gradient descent optimizer using defaults: name="SGD", and nesterov=false
   *
   * @param tf the TensorFlow Ops
   * @param learningRate The learning rate. Defaults to 0.01.
   * @param momentum hyperparameter that accelerates SGD in the relevant direction and dampens
   *     oscillations. Must be between [0, 1].
   */
  public SGD(Ops tf, float learningRate, float momentum) {
    this(tf, learningRate, momentum, NESTEROV_DEFAULT);
  }

  /**
   * Create a Stochastic gradient descent optimizer using defaults: momentum=0.0, and nesterov=false
   *
   * @param tf the TensorFlow Ops
   * @param name prefix for the operations created when applying gradients
   * @param learningRate The learning rate. Defaults to 0.01.
   */
  public SGD(Ops tf, String name, float learningRate) {
    this(tf, name, learningRate, MOMENTUM_DEFAULT, NESTEROV_DEFAULT);
  }

  /**
   * create a Stochastic gradient descent optimizer using defaults: momentum=0.0, and nesterov=false
   *
   * @param tf the TensorFlow Ops
   * @param name prefix for the operations created when applying gradients
   * @param learningRate The learning rate. Defaults to 0.01.
   * @param momentum hyperparameter that accelerates SGD in the relevant direction and dampens
   *     oscillations. Must be between [0, 1].
   */
  public SGD(Ops tf, String name, float learningRate, float momentum) {
    this(tf, name, learningRate, momentum, NESTEROV_DEFAULT);
  }

  /**
   * Create a Stochastic gradient descent optimizer
   *
   * @param tf the TensorFlow Ops
   * @param learningRate The learning rate. Defaults to 0.01.
   * @param momentum hyperparameter that accelerates SGD in the relevant direction and dampens
   *     oscillations. Must be between [0, 1].
   * @param useNesterov Whether to apply Nesterov momentum. Defaults to `false`.
   */
  public SGD(Ops tf, float learningRate, float momentum, boolean useNesterov) {
    super(assertGraph(tf), learningRate, momentum, useNesterov);
    if (momentum < 0 || momentum > 1)
      throw new IllegalArgumentException("\"momentum\" must be between [0, 1].");
    initConfig(learningRate, momentum, useNesterov);
  }

  /**
   * Create a Stochastic gradient descent optimizer
   *
   * @param tf the TensorFlow Ops
   * @param name prefix for the operations created when applying gradients
   * @param learningRate The learning rate. Defaults to 0.01.
   * @param momentum hyperparameter that accelerates SGD in the relevant direction and dampens
   *     oscillations. Must be between [0, 1].
   * @param useNesterov Whether to apply Nesterov momentum. Defaults to `false`.
   */
  public SGD(Ops tf, String name, float learningRate, float momentum, boolean useNesterov) {
    super(assertGraph(tf), name, learningRate, momentum, useNesterov);
    if (momentum < 0 || momentum > 1)
      throw new IllegalArgumentException("\"momentum\" must be between [0, 1].");
    initConfig(learningRate, momentum, useNesterov);
  }

  /**
   * Create a Stochastic gradient descent optimizer
   *
   * @param tf the TensorFlow Ops
   * @param config a config object to initialize, the config object has keys for "name",
   *     "learning_rate", "momentum", and "nesterov". If a key is missing the default value is used.
   * @return the Stochastic gradient descent optimizer
   */
  public static SGD fromConfig(Ops tf, Map<String, Object> config) {
    return create(tf, config);
  }

  /**
   * Create a Stochastic gradient descent optimizer
   *
   * @param tf the TensorFlow Ops
   * @param config a config object to initialize, the config object has keys for "name",
   *     "learning_rate", "momentum", and "nesterov". If a key is missing the default value is used.
   * @return the Stochastic gradient descent optimizer
   */
  public static SGD create(Ops tf, Map<String, Object> config) {

    String name = (String) config.get(NAME_KEY);
    float learningRate = (float) config.getOrDefault(LEARNING_RATE_KEY, LEARNING_RATE_DEFAULT);
    float momentum = (float) config.getOrDefault(MOMENTUM_KEY, MOMENTUM_DEFAULT);
    boolean nesterov = (boolean) config.getOrDefault(NESTEROV_KEY, NESTEROV_DEFAULT);
    if (name == null) {
      return new SGD(tf, learningRate, momentum, nesterov);
    } else {
      return new SGD(tf, name, learningRate, momentum, nesterov);
    }
  }

  /**
   * Initialize the configuration ased on which constructor is called.
   *
   * @param learningRate learningRate The learning rate. Defaults to 0.01.
   * @param momentum hyperparameter that accelerates SGD in the relevant direction and dampens
   *     oscillations. Must be between [0, 1].
   * @param useNesterov Whether to apply Nesterov momentum. Defaults to `false`.
   */
  private void initConfig(float learningRate, float momentum, boolean useNesterov) {
    config.put(NAME_KEY, this.getOptimizerName());
    config.put(LEARNING_RATE_KEY, learningRate);
    config.put(MOMENTUM_KEY, momentum);
    config.put(NESTEROV_KEY, useNesterov);
  }

  /** { @inheritDoc } */
  @Override
  public Map<String, Object> getConfig() {
    return config;
  }

  // overide the momentum name to return "SGD"
  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return "SGD";
  }
}
