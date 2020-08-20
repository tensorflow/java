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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import static org.tensorflow.keras.optimizers.OptimizerInterface.NAME_KEY;
import static org.tensorflow.keras.optimizers.OptimizerInterface.assertGraph;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.train.ApplyAdaMax;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/** Adamax Optimizer that implements the Adamax algorithm. */
public class Adamax extends org.tensorflow.framework.optimizers.Optimizer
    implements OptimizerInterface {

  public static final String FIRST_MOMENT = "m";
  public static final String SECOND_MOMENT = "v";

  public static final String LEARNING_RATE_KEY = "learning_rate";
  public static final String EPSILON_KEY = "epsilon";
  public static final String BETA_ONE_KEY = "beta_1";
  public static final String BETA_TWO_KEY = "beta_2";

  public static final float LEARNING_RATE_DEFAULT = 0.001F;
  public static final float EPSILON_DEFAULT = 1e-07F;
  public static final float BETA_ONE_DEFAULT = 0.9F;
  public static final float BETA_TWO_DEFAULT = 0.999F;

  private Scope scope;
  private Map<String, Object> config = new HashMap<>();

  private float learningRate;
  private final float betaOne;
  private final float betaTwo;
  private final float epsilon;

  private Constant<TFloat32> learningRateConst;
  private Constant<TFloat32> epsilonConst;
  private Constant<TFloat32> betaOneConst;
  private Constant<TFloat32> betaTwoConst;
  private Variable<TFloat32> betaOnePower;

  /**
   * Create an Optimizer that implements the Adamax algorithm.
   *
   * @param tf the TensoFlow Ops
   */
  public Adamax(Ops tf) {
    this(tf, LEARNING_RATE_DEFAULT, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Create an Optimizer that implements the Adamax algorithm.
   *
   * @param tf the TensoFlow Ops
   * @param name name for the operations Created when applying gradients. Defaults to "Adamax".
   */
  public Adamax(Ops tf, String name) {
    this(tf, name, LEARNING_RATE_DEFAULT, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Create an Optimizer that implements the Adamax algorithm.
   *
   * @param tf the TensoFlow Ops
   * @param learningRate The learning rate.
   */
  public Adamax(Ops tf, float learningRate) {
    this(tf, learningRate, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Create an Optimizer that implements the Adamax algorithm.
   *
   * @param tf the TensoFlow Ops
   * @param name name for the operations Created when applying gradients. Defaults to "Adamax".
   * @param learningRate The learning rate.
   */
  public Adamax(Ops tf, String name, float learningRate) {
    this(tf, name, learningRate, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Create an Optimizer that implements the Adamax algorithm.
   *
   * @param tf the TensoFlow Ops
   * @param learningRate The learning rate.
   * @param betaOne The exponential decay rate for the 1st moment estimates.
   * @param betaTwo The exponential decay rate for the exponentially weighted infinity norm.
   * @param epsilon A small constant for numerical stability.
   */
  public Adamax(Ops tf, float learningRate, float betaOne, float betaTwo, float epsilon) {
    super(assertGraph(tf));
    this.learningRate = learningRate;
    this.betaOne = betaOne;
    this.betaTwo = betaTwo;
    this.epsilon = epsilon;
    this.scope = tf.scope();
    initConfig(learningRate, betaOne, betaTwo, epsilon);
  }

  /**
   * Create an Optimizer that implements the Adamax algorithm.
   *
   * @param tf the TensoFlow Ops
   * @param name name for the operations Created when applying gradients. Defaults to "Adamax".
   * @param learningRate The learning rate.
   * @param betaOne The exponential decay rate for the 1st moment estimates.
   * @param betaTwo The exponential decay rate for the exponentially weighted infinity norm.
   * @param epsilon A small constant for numerical stability.
   */
  public Adamax(
      Ops tf, String name, float learningRate, float betaOne, float betaTwo, float epsilon) {
    super(assertGraph(tf), name);
    this.learningRate = learningRate;
    this.betaOne = betaOne;
    this.betaTwo = betaTwo;
    this.epsilon = epsilon;
    this.scope = tf.scope();

    initConfig(learningRate, betaOne, betaTwo, epsilon);
  }

  /**
   * Create an Optimizer that implements the Adamax algorithm from a config object
   *
   * @param tf the TensoFlow Ops
   * @param config a config object to initialize, the config object has keys for "name",
   *     "learning_rate", "epsilon", "beta_1", "beta_2". If a key is missing the default value is
   *     used.
   */
  public static Adamax fromConfig(Ops tf, Map<String, Object> config) {
    return create(tf, config);
  }

  /**
   * Create an Optimizer that implements the Adamax algorithm from a config object
   *
   * @param tf the TensoFlow Ops
   * @param config a config object to initialize
   */
  public static Adamax create(Ops tf, Map<String, Object> config) {
    String name = (String) config.get(NAME_KEY);
    float learningRate = (float) config.getOrDefault(LEARNING_RATE_KEY, LEARNING_RATE_DEFAULT);
    float epsilon = (float) config.getOrDefault(EPSILON_KEY, EPSILON_DEFAULT);
    float betaOne = (float) config.getOrDefault(BETA_ONE_KEY, BETA_ONE_DEFAULT);
    float betaTwo = (float) config.getOrDefault(BETA_TWO_KEY, BETA_TWO_DEFAULT);
    if (name == null) {
      return new Adamax(tf, learningRate, betaOne, betaTwo, epsilon);
    } else {
      return new Adamax(tf, name, learningRate, betaOne, betaTwo, epsilon);
    }
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

  /** {@inheritDoc} */
  @Override
  protected Optional<Op> prepare(String scopeName) {
    betaOneConst = tf.constant(betaOne);
    betaTwoConst = tf.constant(betaTwo);
    learningRateConst = tf.constant(learningRate);
    epsilonConst = tf.constant(epsilon);

    return Optional.empty();
  }

  /** {@inheritDoc} */
  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createAdamaxSlot(v.asOutput());
    }
    betaOnePower = tf.withName("beta1_power").variable(Shape.scalar(), TFloat32.DTYPE);
    Assign<TFloat32> betaOnePowerInit = tf.assign(betaOnePower, tf.constant(betaOne));
    ((Graph) tf.scope().env()).addInitializer(betaOnePowerInit);
  }

  /**
   * Create the first and second moment slots
   *
   * @param v the variable
   * @param <T> the datatype of the variable
   */
  private <T extends TType> void createAdamaxSlot(Output<T> v) {
    Operand<T> firstMomentInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), FIRST_MOMENT, firstMomentInitializer);
    Operand<T> secondMomentInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), SECOND_MOMENT, secondMomentInitializer);
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> firstMomentSlot = getSlot(variable, FIRST_MOMENT).get();
    Variable<T> secondMomentSlot = getSlot(variable, SECOND_MOMENT).get();
    return ApplyAdaMax.create(
        scope,
        (Operand) variable,
        (Operand) firstMomentSlot,
        (Operand) secondMomentSlot,
        (Operand) tf.dtypes.cast(betaOnePower, gradient.dataType()),
        (Operand) tf.dtypes.cast(learningRateConst, gradient.dataType()),
        (Operand) tf.dtypes.cast(betaOneConst, gradient.dataType()),
        (Operand) tf.dtypes.cast(betaTwoConst, gradient.dataType()),
        (Operand) tf.dtypes.cast(epsilonConst, gradient.dataType()),
        (Operand) gradient);
  }

  /** {@inheritDoc} */
  @Override
  protected Op finish(List<Op> updateOperations, String name) {
    updateOperations.add(tf.assign(betaOnePower, tf.math.mul(betaOnePower, betaOneConst)));
    return super.finish(updateOperations, name);
  }

  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return "Adamax";
  }

  /**
   * Initialize the Optimizer from a config object based on which constructor is called.
   *
   * @param learningRate The learning rate. Defaults to 0.001.
   * @param betaOne The exponential decay rate for the 1st moment estimates. Defaults to 0.9.
   * @param betaTwo The exponential decay rate for the 2nd moment estimates. Defaults to 0.999.
   * @param epsilon A small constant for numerical stability. This epsilon is "epsilon hat" in the
   *     Kingma and Ba paper (in the formula just before Section 2.1), not the epsilon in Algorithm
   *     1 of the paper. Defaults to 1e-7.
   */
  protected void initConfig(float learningRate, float betaOne, float betaTwo, float epsilon) {
    config.put(NAME_KEY, this.getOptimizerName());
    config.put(LEARNING_RATE_KEY, learningRate);
    config.put(EPSILON_KEY, epsilon);
    config.put(BETA_ONE_KEY, betaOne);
    config.put(BETA_TWO_KEY, betaTwo);
  }
}
