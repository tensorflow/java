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

import java.util.HashMap;
import java.util.Map;
import static org.tensorflow.keras.optimizers.OptimizerInterface.assertGraph;
import org.tensorflow.op.Ops;

/**
 * AdaGrad Optimizer that implements the AdaGrad algorithm. Adagrad is an optimizer with
 * parameter-specific learning rates, which are adapted relative to how frequently a parameter gets
 * updated during training. The more updates a parameter receives, the smaller the updates.
 */
public class AdaGrad extends org.tensorflow.framework.optimizers.AdaGrad
    implements OptimizerInterface {

  public static final String LEARNING_RATE_KEY = "learning_rate";
  public static final String INITIAL_ACCUM_KEY = "accumulator";

  public static final float LEARNING_RATE_DEFAULT = 0.001F;
  public static final float INITIAL_ACCUM__DEFAULT = 0.1f;

  private Map<String, Object> config = new HashMap<>();
  private float learningRate;

  /**
   * Create an AdaGrad Optimizer with name="Adagrad", learningRate=0.001F, and initial
   * accumulator=0.1
   *
   * @param tf the tensorflow Ops
   */
  public AdaGrad(Ops tf) {
    this(tf, LEARNING_RATE_DEFAULT, INITIAL_ACCUM__DEFAULT);
  }

  /**
   * Create an AdaGrad Optimizer with learningRate=0.001F, and initial accumulator=0.1
   *
   * @param tf the tensorflow Ops
   * @param name the name of the Optimizer, defaults to "Adagrad"
   */
  public AdaGrad(Ops tf, String name) {
    this(tf, name, LEARNING_RATE_DEFAULT, INITIAL_ACCUM__DEFAULT);
  }

  /**
   * Create an AdaGrad Optimizer with initial accumulator=0.1
   *
   * @param tf the tensorflow Ops
   * @param learningRate The learning rate. Defaults to 0.001.
   */
  public AdaGrad(Ops tf, float learningRate) {
    this(tf, learningRate, INITIAL_ACCUM__DEFAULT);
  }

  /**
   * Create an AdaGrad Optimizer
   *
   * @param tf the tensorflow Ops
   * @param name the name of the Optimizer, defaults to "Adagrad"
   * @param learningRate The learning rate. Defaults to 0.01.
   */
  public AdaGrad(Ops tf, String name, float learningRate) {
    this(tf, name, learningRate, INITIAL_ACCUM__DEFAULT);
  }

  /**
   * Create an AdaGrad Optimizer
   *
   * @param tf the tensorflow Ops
   * @param learningRate The learning rate
   * @param initialAccumulatorValue initial accumulator value
   */
  public AdaGrad(Ops tf, float learningRate, float initialAccumulatorValue) {
    super(assertGraph(tf), learningRate, initialAccumulatorValue);
    initConfig(learningRate, initialAccumulatorValue);
  }

  /**
   * Create an AdaGrad Optimizer
   *
   * @param tf the tensorflow Ops
   * @param name the name of the Optimizer, defaults to "Adagrad"
   * @param learningRate The learning rate
   * @param initialAccumulatorValue initial accumulator value, must be >= 0.
   */
  public AdaGrad(Ops tf, String name, float learningRate, float initialAccumulatorValue) {
    super(assertGraph(tf), name, learningRate, initialAccumulatorValue);
    assert initialAccumulatorValue >= 0.0F
        : "initial_accumulator_value must be non-negative: " + initialAccumulatorValue;
    initConfig(learningRate, initialAccumulatorValue);
  }

  /**
   * Create an AdaGrad Optimizer from a config object
   *
   * @param graph the tensorflow graph
   * @param config a config object to initialize, , the config object has keys for "name",
   *     "learning_rate" and "accumulator". If a key is missing the default value is used.
   */
  public static AdaGrad fromConfig(Ops tf, Map<String, Object> config) {
    return create(tf, config);
  }

  /**
   * Create an AdaGrad Optimizer from a config object
   *
   * @param graph the tensorflow graph
   * @param config a config object to initialize, the config object has keys for "name",
   *     "learning_rate" and "accumulator". If a key is missing the default value is used.
   */
  public static AdaGrad create(Ops tf, Map<String, Object> config) {
    String name = (String) config.get(NAME_KEY);
    float learningRate = (float) config.getOrDefault(LEARNING_RATE_KEY, LEARNING_RATE_DEFAULT);
    float initialAccumulatorValue =
        (float) config.getOrDefault(INITIAL_ACCUM_KEY, INITIAL_ACCUM__DEFAULT);
    if (name != null) {
      return new AdaGrad(tf, name, learningRate, initialAccumulatorValue);
    } else {
      return new AdaGrad(tf, learningRate, initialAccumulatorValue);
    }
  }

  /**
   * Initialize the configuration
   *
   * @param learningRate
   * @param initialAccumulatorValue
   */
  private void initConfig(float learningRate, float initialAccumulatorValue) {
    this.learningRate = learningRate;
    config.put(NAME_KEY, this.getOptimizerName());
    config.put(LEARNING_RATE_KEY, learningRate);
    config.put(INITIAL_ACCUM_KEY, initialAccumulatorValue);
  }

  /** {@inheritDoc} */
  @Override
  public Map<String, Object> getConfig() {
    return config;
  }

  /** {@inheritDoc} */
  @Override
  public float getLearningRate() {
    return this.learningRate;
  }

  /** {@inheritDoc} */
  @Override
  public void setLearningRate(float learningRate) {
    this.learningRate = learningRate;
  }
}
