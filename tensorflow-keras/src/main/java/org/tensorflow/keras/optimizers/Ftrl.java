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

import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.train.ApplyFtrl;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.tensorflow.keras.optimizers.OptimizerInterface.assertGraph;

/** Ftrl (Follow the Regularized Leader) Optimizer that implements the FTRL algorithm. */
public class Ftrl extends org.tensorflow.framework.optimizers.Optimizer
    implements OptimizerInterface, AutoCloseable {

  public static final String LEARNING_RATE_KEY = "learning_rate";
  public static final String LEARNING_RATE_POWER_KEY = "learning_rate_power";
  public static final String INITIAL_ACCUM_VALUE_KEY = "initial_accumulator_value";
  public static final String L1STRENGTH_KEY = "l1_regularization_strength";
  public static final String L2STRENGTH_KEY = "l2_regularization_strength";
  public static final String L2_SHRINKAGE_REGULARIZATION_STRENGTH_KEY =
      "l2_shrinkage_regularization_strength";

  public static final float LEARNING_RATE_DEFAULT = 0.001F;
  public static final float LEARNING_RATE_POWER_DEFAULT = -0.5F;
  public static final float INITIAL_ACCUM_VALUE_DEFAULT = 0.1F;
  public static final float L1STRENGTH_DEFAULT = 0.0F;
  public static final float L2STRENGTH_DEFAULT = 0.0F;
  public static final float L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT = 0.0F;

  public static final String ACCUMULATOR = "gradient_accumulator";
  public static final String LINEAR_ACCUMULATOR = "linear_accumulator";

  private final String name;
  private float learningRate;
  private Tensor<TFloat32> learningRateTensor;
  private final Placeholder<TFloat32> learningRatePlaceholder;
  private Map<Operand<? extends TType>, Tensor<? extends TType>> feedDict;
  private final float learningRatePower;
  private final float initialAccumulatorValue;
  private final float l1RegularizationStrength;
  private final float l2RegularizationStrength;
  private final float l2ShrinkageRegularizationStrength;

  private final Map<String, Object> config = new HashMap<>();

  private final boolean useLocking = true;

  /**
   * Create a Ftrl Optimizer
   *
   * @param tf the TensorFlow Ops
   */
  public Ftrl(Ops tf) {
    this(
        tf,
        LEARNING_RATE_DEFAULT,
        LEARNING_RATE_POWER_DEFAULT,
        INITIAL_ACCUM_VALUE_DEFAULT,
        L1STRENGTH_DEFAULT,
        L2STRENGTH_DEFAULT,
        L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT);
  }

  /**
   * Create a Ftrl Optimizer
   *
   * @param tf the TensorFlow Ops
   * @param name the Optmizer name
   */
  public Ftrl(Ops tf, String name) {
    this(
        tf,
        name,
        LEARNING_RATE_DEFAULT,
        LEARNING_RATE_POWER_DEFAULT,
        INITIAL_ACCUM_VALUE_DEFAULT,
        L1STRENGTH_DEFAULT,
        L2STRENGTH_DEFAULT,
        L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT);
  }

  /**
   * Create a Ftrl Optimizer
   *
   * @param tf the TensorFlow Ops
   * @param learningRate the learning rate
   */
  public Ftrl(Ops tf, float learningRate) {
    this(
        tf,
        learningRate,
        LEARNING_RATE_POWER_DEFAULT,
        INITIAL_ACCUM_VALUE_DEFAULT,
        L1STRENGTH_DEFAULT,
        L2STRENGTH_DEFAULT,
        L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT);
  }

  /**
   * Create a Ftrl Optimizer
   *
   * @param tf the TensorFlow Ops
   * @param name the Optmizer name
   * @param learningRate the learning rate
   */
  public Ftrl(Ops tf, String name, float learningRate) {
    this(
        tf,
        name,
        learningRate,
        LEARNING_RATE_POWER_DEFAULT,
        INITIAL_ACCUM_VALUE_DEFAULT,
        L1STRENGTH_DEFAULT,
        L2STRENGTH_DEFAULT,
        L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT);
  }

  /**
   * Create a Ftrl Optimizer
   *
   * @param tf the TensorFlow Ops
   * @param learningRate the learning rate
   * @param learningRatePower Controls how the learning rate decreases during training. Use zero for
   *     a fixed learning rate.
   * @param initialAccumulatorValue The starting value for accumulators. Only zero or positive
   *     values are allowed.
   * @param l1Strength the L1 Regularization strength, must be greater than or equal to zero.
   * @param l2Strength the L2 Regularization strength, must be greater than or equal to zero.
   * @param l2ShrinkageRegularizationStrength This differs from L2 above in that the L2 above is a
   *     stabilization penalty, whereas this L2 shrinkage is a magnitude penalty. must be greater
   *     than or equal to zero.
   */
  public Ftrl(
      Ops tf,
      float learningRate,
      float learningRatePower,
      float initialAccumulatorValue,
      float l1Strength,
      float l2Strength,
      float l2ShrinkageRegularizationStrength) {
    super(assertGraph(tf));
    this.name = getOptimizerName();
    this.learningRate = learningRate;
    this.learningRateTensor = TFloat32.scalarOf(this.learningRate);
    this.learningRatePlaceholder =
        tf.withSubScope(LEARNING_RATE)
            .placeholder(TFloat32.DTYPE, Placeholder.shape(Shape.scalar()));
    this.feedDict = Collections.singletonMap(this.learningRatePlaceholder, this.learningRateTensor);
    this.learningRatePower = learningRatePower;
    this.initialAccumulatorValue = initialAccumulatorValue;
    this.l1RegularizationStrength = l1Strength;
    this.l2RegularizationStrength = l2Strength;
    this.l2ShrinkageRegularizationStrength = l2ShrinkageRegularizationStrength;
    validateParams();
    initConfig();
  }

  /**
   * Create a Ftrl Optimizer
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this Ftrl Optimizer
   * @param learningRate the learning rate
   * @param learningRatePower Controls how the learning rate decreases during training. Use zero for
   *     a fixed learning rate.
   * @param initialAccumulatorValue The starting value for accumulators. Only zero or positive
   *     values are allowed.
   * @param l1Strength the L1 Regularization strength, must be greater than or equal to zero.
   * @param l2Strength the L2 Regularization strength, must be greater than or equal to zero.
   * @param l2ShrinkageRegularizationStrength This differs from L2 above in that the L2 above is a
   *     stabilization penalty, whereas this L2 shrinkage is a magnitude penalty. must be greater
   *     than or equal to zero.
   */
  public Ftrl(
      Ops tf,
      String name,
      float learningRate,
      float learningRatePower,
      float initialAccumulatorValue,
      float l1Strength,
      float l2Strength,
      float l2ShrinkageRegularizationStrength) {
    super(assertGraph(tf), name);
    this.name = name;
    this.learningRate = learningRate;
    this.learningRateTensor = TFloat32.scalarOf(this.learningRate);
    this.learningRatePlaceholder =
        tf.withSubScope(LEARNING_RATE)
            .placeholder(TFloat32.DTYPE, Placeholder.shape(Shape.scalar()));
    this.feedDict = Collections.singletonMap(this.learningRatePlaceholder, this.learningRateTensor);
    this.learningRatePower = learningRatePower;
    this.initialAccumulatorValue = initialAccumulatorValue;
    this.l1RegularizationStrength = l1Strength;
    this.l2RegularizationStrength = l2Strength;
    this.l2ShrinkageRegularizationStrength = l2ShrinkageRegularizationStrength;
    validateParams();
    initConfig();
  }

  /**
   * Create a Ftrl Optmizer
   *
   * @param tf the TensorFlow Ops
   * @param config a config object to initialize
   * @return a new Frtl Optimizer
   */
  public static Ftrl create(Ops tf, Map<String, Object> config) {
    String name = (String) config.get(NAME_KEY);
    float learningRate = (float) config.getOrDefault(LEARNING_RATE_KEY, LEARNING_RATE_DEFAULT);
    float learningRatePower =
        (float) config.getOrDefault(LEARNING_RATE_POWER_KEY, LEARNING_RATE_POWER_DEFAULT);
    float initialAccumulatorValue =
        (float) config.getOrDefault(INITIAL_ACCUM_VALUE_KEY, INITIAL_ACCUM_VALUE_DEFAULT);
    float l1RegularizationStrength =
        (float) config.getOrDefault(L1STRENGTH_KEY, L1STRENGTH_DEFAULT);
    float l2RegularizationStrength =
        (float) config.getOrDefault(L2STRENGTH_KEY, L2STRENGTH_DEFAULT);
    float l2ShrinkageRegularizationStrength =
        (float)
            config.getOrDefault(
                L2_SHRINKAGE_REGULARIZATION_STRENGTH_KEY,
                L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT);

    if (name == null) {
      return new Ftrl(
          tf,
          learningRate,
          learningRatePower,
          initialAccumulatorValue,
          l1RegularizationStrength,
          l2RegularizationStrength,
          l2ShrinkageRegularizationStrength);
    } else {
      return new Ftrl(
          tf,
          name,
          learningRate,
          learningRatePower,
          initialAccumulatorValue,
          l1RegularizationStrength,
          l2RegularizationStrength,
          l2ShrinkageRegularizationStrength);
    }
  }

  /** Initialize the Config object from the current settings */
  protected void initConfig() {
    config.put(NAME_KEY, this.name);
    config.put(LEARNING_RATE_KEY, learningRate);
    config.put(LEARNING_RATE_POWER_KEY, learningRatePower);
    config.put(INITIAL_ACCUM_VALUE_KEY, initialAccumulatorValue);
    config.put(L1STRENGTH_KEY, l1RegularizationStrength);
    config.put(L2STRENGTH_KEY, l2RegularizationStrength);
    config.put(L2_SHRINKAGE_REGULARIZATION_STRENGTH_KEY, l2ShrinkageRegularizationStrength);
  }

  /** Validate all the settings of the Frtl Optmizer */
  private void validateParams() {
    if (this.initialAccumulatorValue < 0.0F) {
      throw new IllegalArgumentException(
          String.format(
              "initialAccumulatorValue %f needs to be positive or zero",
              this.initialAccumulatorValue));
    }
    if (this.learningRatePower > 0.0F) {
      throw new IllegalArgumentException(
          String.format(
              "learningRatePower %f needs to be negative or zero", this.learningRatePower));
    }
    if (this.l1RegularizationStrength < 0.0F) {
      throw new IllegalArgumentException(
          String.format(
              "'l1RegularizationStrength %f needs to be positive or zero",
              this.l1RegularizationStrength));
    }
    if (this.l2RegularizationStrength < 0.0F) {
      throw new IllegalArgumentException(
          String.format(
              "'l2RegularizationStrength %f needs to be positive or zero",
              this.l2RegularizationStrength));
    }
    if (this.l2ShrinkageRegularizationStrength < 0.0F) {
      throw new IllegalArgumentException(
          String.format(
              "'l2ShrinkageRegularizationStrength %f needs to be positive or zero",
              this.l2RegularizationStrength));
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createFtrlSlot(v);
    }
  }

  /**
   * Create a slot variables for the accumulators
   *
   * @param v the variable
   * @param <T> the data type of the variable
   */
  private <T extends TType> void createFtrlSlot(Output<T> v) {
    Operand<T> initializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(initialAccumulatorValue), v.dataType()));
    createSlot(v.asOutput(), ACCUMULATOR, initializer);
    Operand<T> linearInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), LINEAR_ACCUMULATOR, linearInitializer);
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> accumSlot = getSlot(variable, ACCUMULATOR).get();
    Variable<T> linearSlot = getSlot(variable, LINEAR_ACCUMULATOR).get();
    ApplyFtrl.Options options = ApplyFtrl.useLocking(useLocking);
    return this.tf.train.applyFtrl(
        variable,
        accumSlot, // accum
        linearSlot, // linear
        gradient, // gradient
        tf.dtypes.cast(this.learningRatePlaceholder, gradient.dataType()), // lr
        tf.dtypes.cast(tf.constant(l1RegularizationStrength), gradient.dataType()), // l1
        tf.dtypes.cast(tf.constant(l2RegularizationStrength), gradient.dataType()), // l2
        tf.dtypes.cast(
            tf.constant(l2ShrinkageRegularizationStrength), gradient.dataType()), // l2Shrinkage
        tf.dtypes.cast(tf.constant(learningRatePower), gradient.dataType()), // lrPower
        options);
  }

  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return "Ftrl";
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
  public final void setLearningRate(float learningRate) {
    this.learningRate = learningRate;
    if (this.learningRateTensor != null) {
      this.learningRateTensor.close();
    }
    this.learningRateTensor = TFloat32.scalarOf(this.learningRate);
    this.feedDict = Collections.singletonMap(this.learningRatePlaceholder, this.learningRateTensor);
  }

  /** {@inheritDoc} */
  public Map<Operand<? extends TType>, Tensor<? extends TType>> getFeedDict() {
    return this.feedDict;
  }

  /** {@inheritDoc} */
  @Override
  public void close() throws Exception {
    if (this.learningRateTensor != null) {
      this.learningRateTensor.close();
      this.learningRateTensor = null;
    }
  }
}
