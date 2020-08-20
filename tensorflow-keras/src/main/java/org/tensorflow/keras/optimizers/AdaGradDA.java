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

/** Optimizer that implements the Adagrad Dual-Averaging algorithm. */
public class AdaGradDA extends org.tensorflow.framework.optimizers.AdaGradDA
    implements OptimizerInterface {

  public static final String LEARNING_RATE_KEY = "learning_rate";
  public static final String INITIAL_ACCUM_KEY = "accumulator";
  public static final String L1STRENGTH_KEY = "l1Strength";
  public static final String L2STRENGTH_KEY = "l2Strength";

  public static final float LEARNING_RATE_DEFAULT = 0.001F; // arbitray number
  public static final float INITIAL_ACCUM__DEFAULT = 0.1f;
  public static final float L1STRENGTH_DEFAULT = 0.0F;
  public static final float L2STRENGTH_DEFAULT = 0.0F;

  private Map<String, Object> config = new HashMap<>();
  private float learningRate;

  /**
   * Create an AdagradDA Optimizer with default values name="adagrad-da". learning_rate=.001,
   * initial accumulator= 0.1, l1Strength=0.0, l2Strength=0.0;
   *
   * @param tf the tensorflow tf
   */
  public AdaGradDA(Ops tf) {
    this(tf, LEARNING_RATE_DEFAULT, INITIAL_ACCUM__DEFAULT, L1STRENGTH_DEFAULT, L2STRENGTH_DEFAULT);
  }

  /**
   * Create an AdagradDA Optimizer with default values initial accumulator= 0.1, l1Strength=0.0,
   * l2Strength=0.0;
   *
   * @param tf the tensorflow tf
   * @param learningRate The learning rate.
   */
  public AdaGradDA(Ops tf, float learningRate) {
    this(tf, learningRate, INITIAL_ACCUM__DEFAULT, L1STRENGTH_DEFAULT, L2STRENGTH_DEFAULT);
  }

  /**
   * Create an AdagradDA Optimizer with default values initial accumulator= 0.1, l1Strength=0.0,
   * l2Strength=0.0;
   *
   * @param tf the tensorflow tf
   * @param name the name of the Optimizer, defaults to "adagrad-da"
   * @param learningRate The learning rate.
   */
  public AdaGradDA(Ops tf, String name, float learningRate) {
    this(tf, name, learningRate, INITIAL_ACCUM__DEFAULT, L1STRENGTH_DEFAULT, L2STRENGTH_DEFAULT);
  }

  /**
   * Create an AdagradDA Optimizer
   *
   * @param tf the tensorflow tf
   * @param learningRate the learning rate, default is 0.001
   * @param initialAccumulatorValue Starting value for the accumulators, must be >= 0.0.
   * @param l1Strength L1 Regularization Strength
   * @param l2Strength L2 Regularization Strength
   */
  public AdaGradDA(
      Ops tf,
      float learningRate,
      float initialAccumulatorValue,
      float l1Strength,
      float l2Strength) {
    super(assertGraph(tf), learningRate, initialAccumulatorValue, l1Strength, l2Strength);
    if( initialAccumulatorValue < 0.0F)
        throw new IllegalArgumentException("initial_accumulator_value must be non-negative: " + initialAccumulatorValue);
    if(l1Strength < 0)
      throw new IllegalArgumentException("l1Strength must be non-negative: " + l1Strength);
    if(l2Strength < 0)
      throw new IllegalArgumentException("l2Strength must be non-negative: " + l2Strength);
    initConfig(learningRate, initialAccumulatorValue, l1Strength, l2Strength);
  }

  /**
   * Create an AdagradDA Optimizer
   *
   * @param tf the tensorflow tf
   * @param name the name of the Optimizer, defaults to "adagrad-da"
   * @param learningRate the learning rate, default is 0.001
   * @param initialAccumulatorValue Starting value for the accumulators, must be positive.
   * @param l1Strength L1 Regularization Strength
   * @param l2Strength L2 Regularization Strength
   */
  public AdaGradDA(
      Ops tf,
      String name,
      float learningRate,
      float initialAccumulatorValue,
      float l1Strength,
      float l2Strength) {
    super(assertGraph(tf), name, learningRate, initialAccumulatorValue, l1Strength, l2Strength);
    if( initialAccumulatorValue < 0.0F)
      throw new IllegalArgumentException("initial_accumulator_value must be non-negative: " + initialAccumulatorValue);
    if(l1Strength < 0)
      throw new IllegalArgumentException("l1Strength must be non-negative: " + l1Strength);
    if(l2Strength < 0)
      throw new IllegalArgumentException("l2Strength must be non-negative: " + l2Strength);
    initConfig(learningRate, initialAccumulatorValue, l1Strength, l2Strength);
    initConfig(learningRate, initialAccumulatorValue, l1Strength, l2Strength);
  }

  /**
   * Create an AdaGrad Optimizer from a config object
   *
   * @param tf the tensorflow tf
   * @param config a config object to initialize, , the config object has keys for "name",
   *     "learning_rate", "accumulator", "l1Strength" and "l2Strength". If a key is missing the
   *     default value is used.
   * @return the new AdaGradDA Optimizer
   */
  public static AdaGradDA fromConfig(Ops tf, Map<String, Object> config) {
    return create(tf, config);
  }

  /**
   * Create an AdaGradDA Optimizer from a config object
   *
   * @param tf the tensorflow tf
   * @param config a config object to initialize, the config object has keys for "name",
   *     "learning_rate", "accumulator", "l1Strength" and "l2Strength". If a key is missing the
   *     default value is used.
   * @return the new AdaGradDA Optimizer
   */
  public static AdaGradDA create(Ops tf, Map<String, Object> config) {
    String name = (String) config.get(NAME_KEY);
    float learningRate = (float) config.getOrDefault(LEARNING_RATE_KEY, LEARNING_RATE_DEFAULT);
    float initialAccumulatorValue =
        (float) config.getOrDefault(INITIAL_ACCUM_KEY, INITIAL_ACCUM__DEFAULT);
    float l1Strength = (float) config.getOrDefault(L1STRENGTH_KEY, L2STRENGTH_DEFAULT);
    float l2Strength = (float) config.getOrDefault(L2STRENGTH_KEY, L2STRENGTH_DEFAULT);
    if (name != null) {
      return new AdaGradDA(tf, name, learningRate, initialAccumulatorValue, l1Strength, l2Strength);
    } else {
      return new AdaGradDA(tf, learningRate, initialAccumulatorValue, l1Strength, l2Strength);
    }
  }

  /**
   * Initialize the Optimizer from a config object based on which constructor is called.
   *
   * @param learningRate the learning rate, default is 0.001
   * @param initialAccumulatorValue Starting value for the accumulators, must be >= 0.0.
   * @param l1Strength L1 Regularization Strength
   * @param l2Strength L2 Regularization Strength
   */
  private void initConfig(
      float learningRate, float initialAccumulatorValue, float l1Strength, float l2Strength) {
    this.learningRate = learningRate;
    config.put(NAME_KEY, this.getOptimizerName());
    config.put(LEARNING_RATE_KEY, learningRate);
    config.put(INITIAL_ACCUM_KEY, initialAccumulatorValue);
    config.put(L1STRENGTH_KEY, l1Strength);
    config.put(L2STRENGTH_KEY, l2Strength);
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
