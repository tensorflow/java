package org.tensorflow.framework.optimizers;

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.train.ApplyFtrl;
import org.tensorflow.types.family.TType;

import java.util.List;

/**
 * Optimizer that implements the FTRL algorithm.
 *
 * @see <a href="https://www.eecs.tufts.edu/~dsculley/papers/ad-click-prediction.pdf">McMahan, et
 *    al., 2013, Algorithm 1</a>
 *    <p>This version has support for both online L2 (the L2 penalty given in the paper above) and
 *    shrinkage-type L2 (which is the addition of an L2 penalty to the loss function).
 */
public class Ftrl extends Optimizer {

  public static final String ACCUMULATOR = "gradient_accumulator";
  public static final String LINEAR_ACCUMULATOR = "linear_accumulator";

  public static final float LEARNING_RATE_DEFAULT = 0.001f;
  public static final float LEARNING_RATE_POWER_DEFAULT = -0.5f;
  public static final float INITIAL_ACCUMULATOR_VALUE_DEFAULT = 0.1f;
  public static final float L1STRENGTH_DEFAULT = 0.0f;
  public static final float L2STRENGTH_DEFAULT = 0.0f;
  public static final float L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT = 0.0f;

  private float learningRate;
  private final float learningRatePower;
  private final float initialAccumulatorValue;
  private final float l1RegularizationStrength;
  private final float l2RegularizationStrength;
  private final float l2ShrinkageRegularizationStrength;

  /**
   * Creates a Ftrl Optimizer
   *
   * @param graph the TensorFlow Graph
   */
  public Ftrl(Graph graph) {
    this(
        graph,
        LEARNING_RATE_DEFAULT,
        LEARNING_RATE_POWER_DEFAULT,
        INITIAL_ACCUMULATOR_VALUE_DEFAULT,
        L1STRENGTH_DEFAULT,
        L2STRENGTH_DEFAULT,
        L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT);
  }

  /**
   * Creates a Ftrl Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param name the name of this Optimizer
   */
  public Ftrl(Graph graph, String name) {
    this(
        graph,
        name,
        LEARNING_RATE_DEFAULT,
        LEARNING_RATE_POWER_DEFAULT,
        INITIAL_ACCUMULATOR_VALUE_DEFAULT,
        L1STRENGTH_DEFAULT,
        L2STRENGTH_DEFAULT,
        L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT);
  }

  /**
   * Creates a Ftrl Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param learningRate the learning rate
   */
  public Ftrl(Graph graph, float learningRate) {
    this(
        graph,
        learningRate,
        LEARNING_RATE_POWER_DEFAULT,
        INITIAL_ACCUMULATOR_VALUE_DEFAULT,
        L1STRENGTH_DEFAULT,
        L2STRENGTH_DEFAULT,
        L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT);
  }

  /**
   * Creates a Ftrl Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param name the name of this Optimizer
   * @param learningRate the learning rate
   */
  public Ftrl(Graph graph, String name, float learningRate) {
    this(
        graph,
        name,
        learningRate,
        LEARNING_RATE_POWER_DEFAULT,
        INITIAL_ACCUMULATOR_VALUE_DEFAULT,
        L1STRENGTH_DEFAULT,
        L2STRENGTH_DEFAULT,
        L2_SHRINKAGE_REGULARIZATION_STRENGTH_DEFAULT);
  }

  /**
   * Creates a Ftrl Optimizer
   *
   * @param graph the TensorFlow Graph
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
   * @throws java.lang.IllegalArgumentException if the initialAccumulatorValue,
   *     l1RegularizationStrength, l2RegularizationStrength, or l2ShrinkageRegularizationStrength
   *     are less than 0.0, or learningRatePower is greater than 0.0.
   */
  public Ftrl(
      Graph graph,
      float learningRate,
      float learningRatePower,
      float initialAccumulatorValue,
      float l1Strength,
      float l2Strength,
      float l2ShrinkageRegularizationStrength) {
    super(graph);
    this.learningRate = learningRate;
    this.learningRatePower = learningRatePower;
    this.initialAccumulatorValue = initialAccumulatorValue;
    this.l1RegularizationStrength = l1Strength;
    this.l2RegularizationStrength = l2Strength;
    this.l2ShrinkageRegularizationStrength = l2ShrinkageRegularizationStrength;
    validateParams();
  }

  /**
   * Creates a Ftrl Optimizer
   *
   * @param graph the TensorFlow Graph
   * @param name the name of this Optimizer
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
   * @throws java.lang.IllegalArgumentException if the initialAccumulatorValue,
   *     l1RegularizationStrength, l2RegularizationStrength, or l2ShrinkageRegularizationStrength
   *     are less than 0.0, or learningRatePower is greater than 0.0.
   */
  public Ftrl(
      Graph graph,
      String name,
      float learningRate,
      float learningRatePower,
      float initialAccumulatorValue,
      float l1Strength,
      float l2Strength,
      float l2ShrinkageRegularizationStrength) {
    super(graph, name);
    this.learningRate = learningRate;
    this.learningRatePower = learningRatePower;
    this.initialAccumulatorValue = initialAccumulatorValue;
    this.l1RegularizationStrength = l1Strength;
    this.l2RegularizationStrength = l2Strength;
    this.l2ShrinkageRegularizationStrength = l2ShrinkageRegularizationStrength;
    validateParams();
  }

  /** Validates all the settings of the Frtl Optmizer */
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
   * Creates a slot variables for the accumulators
   *
   * @param v the variable
   * @param <T> the data type of the variable
   */
  private <T extends TType> void createFtrlSlot(Output<T> v) {
    Operand<T> initializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(initialAccumulatorValue), v.type()));
    createSlot(v.asOutput(), ACCUMULATOR, initializer);
    Operand<T> linearInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.type()));
    createSlot(v.asOutput(), LINEAR_ACCUMULATOR, linearInitializer);
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> accumSlot = getSlot(variable, ACCUMULATOR).get();
    Variable<T> linearSlot = getSlot(variable, LINEAR_ACCUMULATOR).get();
    ApplyFtrl.Options options = ApplyFtrl.useLocking(true);
    return this.tf.train.applyFtrl(
        variable,
        accumSlot, // accum
        linearSlot, // linear
        gradient, // gradient
        tf.dtypes.cast(tf.constant(learningRate), gradient.type()), // lr
        tf.dtypes.cast(tf.constant(l1RegularizationStrength), gradient.type()), // l1
        tf.dtypes.cast(tf.constant(l2RegularizationStrength), gradient.type()), // l2
        tf.dtypes.cast(
            tf.constant(l2ShrinkageRegularizationStrength), gradient.type()), // l2Shrinkage
        tf.dtypes.cast(tf.constant(learningRatePower), gradient.type()), // lrPower
        options);
  }

  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return "Ftrl";
  }
}
