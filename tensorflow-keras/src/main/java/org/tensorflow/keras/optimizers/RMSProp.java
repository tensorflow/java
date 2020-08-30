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

/** RMSProp Optimizer that implements the RMSProp algorithm. */
public class RMSProp extends org.tensorflow.framework.optimizers.RMSProp
    implements OptimizerInterface {

  public static final String LEARNING_RATE_KEY = "learning_rate";
  public static final String DECAY_KEY = "decay";
  public static final String MOMENTUM_KEY = "momentum";
  public static final String EPSILON_KEY = "epsilon";
  public static final String CENTERED_KEY = "centered";

  public static final float LEARNING_RATE_DEFAULT = 0.001F;
  public static final float DECAY_DEFAULT = 0.9F;
  public static final float MOMENTUM_DEFAULT = 0.0F;
  public static final float EPSILON_DEFAULT = 1e-07F;
  public static final boolean CENTERED_DEFAULT = false;

  private Map<String, Object> config = new HashMap<>();

  /**
   * Create an RMSProp Optimizer with the following defaults, name="RMSProp", learning_rate=0.001,
   * decay=0.9, momentum=0.0, epsilon=1e-07, centered=false
   *
   * @param tf the TensorFlow Ops
   */
  public RMSProp(Ops tf) {
    this(
        tf,
        LEARNING_RATE_DEFAULT,
        DECAY_DEFAULT,
        MOMENTUM_DEFAULT,
        EPSILON_DEFAULT,
        CENTERED_DEFAULT);
  }

  /**
   * Create an RMSProp Optimizer with the following defaults, name="RMSProp", decay=0.9,
   * momentum=0.0, epsilon=1e-07, centered=false
   *
   * @param tf the TensorFlow Ops
   * @param learningRate The learning rate.
   */
  public RMSProp(Ops tf, float learningRate) {
    this(tf, learningRate, DECAY_DEFAULT, MOMENTUM_DEFAULT, EPSILON_DEFAULT, CENTERED_DEFAULT);
  }

  /**
   * Create an RMSProp Optimizer with the following defaults, decay=0.9, momentum=0.0,
   * epsilon=1e-07, centered=false
   *
   * @param tf the TensorFlow Ops
   * @param name prefix for the operations created when applying gradients. Defaults to "RMSProp"
   * @param learningRate The learning rate.
   */
  public RMSProp(Ops tf, String name, float learningRate) {
    this(
        tf, name, learningRate, DECAY_DEFAULT, MOMENTUM_DEFAULT, EPSILON_DEFAULT, CENTERED_DEFAULT);
  }

  /**
   * Create an RMSProp Optimizer
   *
   * @param tf the TensorFlow Ops
   * @param learningRate The learning rate. Defaults to 0.001.
   * @param decay Discounting factor for the history/coming gradient. Defaults to 0.9.
   * @param momentum hyperparameter that accelerates descent in the relevant direction and dampens
   *     oscillations. Must be between [0, 1].
   * @param epsilon A small constant for numerical stability.
   * @param centered If True, gradients are normalized by the estimated variance of the gradient; if
   *     False, by the uncentered second moment.
   */
  public RMSProp(
      Ops tf, float learningRate, float decay, float momentum, float epsilon, boolean centered) {
    super(assertGraph(tf), learningRate, decay, momentum, epsilon, centered);
    initConfig(learningRate, decay, momentum, epsilon, centered);
  }

  /**
   * Create an RMSProp Optimizer
   *
   * @param tf the TensorFlow Ops
   * @param name prefix for the operations created when applying gradients. Defaults to "RMSProp"
   * @param learningRate The learning rate. Defaults to 0.001.
   * @param decay Discounting factor for the history/coming gradient. Defaults to 0.9.
   * @param momentum hyperparameter that accelerates descent in the relevant direction and dampens
   *     oscillations. Must be between [0, 1].
   * @param epsilon A small constant for numerical stability.
   * @param centered If True, gradients are normalized by the estimated variance of the gradient; if
   *     False, by the uncentered second moment.
   */
  public RMSProp(
      Ops tf,
      String name,
      float learningRate,
      float decay,
      float momentum,
      float epsilon,
      boolean centered) {
    super(assertGraph(tf), name, learningRate, decay, momentum, epsilon, centered);
    initConfig(learningRate, decay, momentum, epsilon, centered);
  }

  /**
   * Create a RMSProp Optimizer using a configuration
   *
   * @param tf the TensorFlow Ops
   * @param config a config object to initialize the Optimizer, the config object has keys for
   *     "name", "learning_rate", "decay", "momentum", "epsilon" and "centered". If a key is missing
   *     the default value is used.
   * @return the RMSProp optimizer
   */
  public static RMSProp fromConfig(Ops tf, Map<String, Object> config) {
    return create(tf, config);
  }

  /**
   * Create a RMSProp Optimizer using a configuration
   *
   * @param tf the TensorFlow Ops
   * @param config a config object to initialize the Optimizer, the config object has keys for
   *     "name", "learning_rate", "decay", "momentum", "epsilon" and "centered". If a key is missing
   *     the default value is used.
   * @return the RMSProp optimizer
   */
  public static RMSProp create(Ops tf, Map<String, Object> config) {

    String name = (String) config.get(NAME_KEY);
    float learningRate = (float) config.getOrDefault(LEARNING_RATE_KEY, LEARNING_RATE_DEFAULT);
    float decay = (float) config.getOrDefault(DECAY_KEY, DECAY_DEFAULT);
    float momentum = (float) config.getOrDefault(MOMENTUM_KEY, MOMENTUM_DEFAULT);
    float epsilon = (float) config.getOrDefault(EPSILON_KEY, EPSILON_DEFAULT);
    boolean centered = (boolean) config.getOrDefault(CENTERED_KEY, CENTERED_DEFAULT);
    if (name == null) {
      return new RMSProp(tf, learningRate, decay, momentum, epsilon, centered);
    } else {
      return new RMSProp(tf, name, learningRate, decay, momentum, epsilon, centered);
    }
  }

  /**
   * Initialize the configuration based on which constructor is called.
   *
   * @param learningRate The learning rate. Defaults to 0.001.
   * @param decay Discounting factor for the history/coming gradient. Defaults to 0.9.
   * @param momentum hyperparameter that accelerates descent in the relevant direction and dampens
   *     oscillations. Must be between [0, 1].
   * @param epsilon A small constant for numerical stability.
   * @param centered If True, gradients are normalized by the estimated variance of the gradient; if
   *     False, by the uncentered second moment.
   */
  private void initConfig(
      float learningRate, float decay, float momentum, float epsilon, boolean centered) {
    config.put(NAME_KEY, this.getOptimizerName());
    config.put(LEARNING_RATE_KEY, learningRate);
    config.put(DECAY_KEY, decay);
    config.put(MOMENTUM_KEY, momentum);
    config.put(EPSILON_KEY, epsilon);
    config.put(CENTERED_KEY, centered);
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> getConfig() {
    return config;
  }
}
