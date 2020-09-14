/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.optimizers.schedules;

/**
 * A LearningRateSchedule that uses a polynomial decay schedule.
 *
 * <p>
 *
 * <p>It is commonly observed that a monotonically decreasing learning rate, whose degree of change
 * is carefully chosen, results in a better performing model. This schedule applies a polynomial
 * decay function to an optimizer step, given a provided `initial_learning_rate`, to reach an
 * `end_learning_rate` in the given `decay_steps`.
 *
 * <p>
 *
 * <p>The schedule is a 1-arg callable that produces a decayed learning rate when passed the current
 * optimizer step. This can be useful for changing the learning rate value across different
 * invocations of optimizer functions. It is computed as:
 *
 * <pre>
 *     step = min(step, decay_steps)
 *     ((initialLearningRate - endLearningRate) *
 * (1 - step / decaySteps) ^ (power)
 * ) + endLearningRate
 * </pre>
 *
 * <p>
 *
 * <p>If `cycle` is True then a multiple of `decay_steps` is used, the first one that is bigger than
 * `step`.
 */
public class PolynomialDecay implements LearningRateSchedule {
  private static final float END_LEARNING_RATE_DEFAULT = 0.0001f;
  public static final float POWER_DEFAULT = 1.0f;
  public static final boolean CYCLE_DEFAULT = false;

  protected final float initialLearningRate;
  protected final float decaySteps;
  protected final float endLearningRate;
  protected final float power;
  protected final boolean cycle;

  /**
   * Create a PolynomialDecay
   *
   * @param initialLearningRate The initial learning rate.
   * @param decaySteps How often to apply decay.
   */
  public PolynomialDecay(float initialLearningRate, int decaySteps) {
    this(initialLearningRate, decaySteps, END_LEARNING_RATE_DEFAULT, POWER_DEFAULT, CYCLE_DEFAULT);
  }

  /**
   * Create a PolynomialDecay
   *
   * @param initialLearningRate The initial learning rate.
   * @param decaySteps How often to apply decay.
   * @param cycle Whether or not it should cycle beyond decay_steps. Default is false.
   */
  public PolynomialDecay(float initialLearningRate, int decaySteps, boolean cycle) {
    this(initialLearningRate, decaySteps, END_LEARNING_RATE_DEFAULT, POWER_DEFAULT, cycle);
  }

  /**
   * Create a PolynomialDecay
   *
   * @param initialLearningRate The initial learning rate.
   * @param decaySteps How often to apply decay.
   * @param endLearningRate The end learning rate. Default is 0.0001.
   */
  public PolynomialDecay(float initialLearningRate, int decaySteps, float endLearningRate) {
    this(initialLearningRate, decaySteps, endLearningRate, POWER_DEFAULT, CYCLE_DEFAULT);
  }

  /**
   * Create a PolynomialDecay
   *
   * @param initialLearningRate The initial learning rate.
   * @param decaySteps How often to apply decay.
   * @param endLearningRate The end learning rate. Default is 0.0001.
   * @param power The power of the polynomial. Defaults to linear, 1.0.
   * @param cycle Whether or not it should cycle beyond decay_steps. Default is false.
   */
  public PolynomialDecay(
      float initialLearningRate,
      int decaySteps,
      float endLearningRate,
      float power,
      boolean cycle) {
    this.initialLearningRate = initialLearningRate;
    this.decaySteps = decaySteps;
    this.endLearningRate = endLearningRate;
    this.power = power;
    this.cycle = cycle;
  }

  @Override
  public float call(int step) {

    float lDecaySteps = decaySteps;
    float lStep = step;
    if (cycle) {
      float multipler = step == 0 ? 1.0f : (float) Math.ceil(step / decaySteps);
      lDecaySteps = decaySteps * multipler;
    } else {
      lStep = Math.min(lStep, lDecaySteps);
    }

    float p = lStep / lDecaySteps;

    float f = (this.initialLearningRate - this.endLearningRate) * (float) Math.pow(1.0f - p, power);
    return f + endLearningRate;
  }
}
