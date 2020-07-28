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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/** Nadam Optimizer that implements the NAdam algorithm. */
public class Nadam extends org.tensorflow.framework.optimizers.Optimizer
    implements OptimizerInterface {

  public static final String FIRST_MOMENT = "m";
  public static final String SECOND_MOMENT = "v";
  public static final String MOMENTUM = "momentum";

  public static final String LEARNING_RATE_KEY = "learning_rate";
  public static final String EPSILON_KEY = "epsilon";
  public static final String BETA_ONE_KEY = "beta_1";
  public static final String BETA_TWO_KEY = "beta_2";

  public static final float LEARNING_RATE_DEFAULT = 0.001F;
  public static final float EPSILON_DEFAULT = 1e-07F;
  public static final float BETA_ONE_DEFAULT = 0.9F;
  public static final float BETA_TWO_DEFAULT = 0.999F;

  private Scope scope;
  private final Map<String, Object> config = new HashMap<>();

  private float learningRate;
  private final float betaOne;
  private final float betaTwo;
  private final float epsilon;
  private final float decayBase = 0.96F;
  private final float decay = 0.004F;

  private long iterations = 0;

  private Constant<TFloat32> learningRateConst;
  private Constant<TFloat32> betaOneConst;
  private Constant<TFloat32> betaTwoConst;
  private Constant<TInt64> localStepConst;
  private Constant<TInt64> nextStepConst;

  private Constant<TFloat32> decayBaseConst;
  private Constant<TFloat32> decayConst;
  private Constant<TFloat32> epsilonConst;

  private Variable<TFloat32> betaOnePower;
  private Variable<TFloat32> betaTwoPower;
  private Variable<TFloat32> momentum;

  private Operand<TFloat32> m_t;
  private Operand<TFloat32> m_t_1;
  private Operand<TFloat32> m_schedule_new;
  private Operand<TFloat32> m_schedule_next;
  private Operand<TFloat32> one_minus_beta_1;
  private Operand<TFloat32> one_minus_beta_2;
  private Operand<TFloat32> one_minus_m_t;
  private Operand<TFloat32> one_minus_m_schedule_new;
  private Operand<TFloat32> one_minus_m_schedule_next;
  private Operand<TFloat32> v_t_prime_denominator;

  /**
   * Create an Optimizer that implements the NAdam algorithm.
   *
   * @param tf the TensorFlow Ops
   */
  public Nadam(Ops tf) {
    this(tf, LEARNING_RATE_DEFAULT, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Create an Optimizer that implements the NAdam algorithm.
   *
   * @param tf the TensorFlow Ops
   * @param name name for the operations created when applying gradients. Defaults to "Nadam".
   */
  public Nadam(Ops tf, String name) {
    this(tf, name, LEARNING_RATE_DEFAULT, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Create an Optimizer that implements the NAdam algorithm.
   *
   * @param tf the TensorFlow Ops
   * @param learningRate The learning rate.
   */
  public Nadam(Ops tf, float learningRate) {
    this(tf, learningRate, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Create an Optimizer that implements the NAdam algorithm.
   *
   * @param tf the TensorFlow Ops
   * @param name name for the operations created when applying gradients. Defaults to "Adamax".
   * @param learningRate The learning rate.
   */
  public Nadam(Ops tf, String name, float learningRate) {
    this(tf, name, learningRate, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Create an Optimizer that implements the NAdam algorithm.
   *
   * @param tf the TensorFlow Ops
   * @param learningRate The learning rate.
   * @param betaOne The exponential decay rate for the 1st moment estimates.
   * @param betaTwo The exponential decay rate for the exponentially weighted infinity norm.
   * @param epsilon A small constant for numerical stability.
   */
  public Nadam(Ops tf, float learningRate, float betaOne, float betaTwo, float epsilon) {
    super(assertGraph(tf));
    this.learningRate = learningRate;
    this.betaOne = betaOne;
    this.betaTwo = betaTwo;
    this.epsilon = epsilon;
    this.scope = tf.scope();
    initConfig(learningRate, betaOne, betaTwo, epsilon);
  }

  /**
   * Create an Optimizer that implements the NAdam algorithm.
   *
   * @param tf the TensorFlow Ops
   * @param name name for the operations created when applying gradients.
   * @param learningRate The learning rate.
   * @param betaOne The exponential decay rate for the 1st moment estimates.
   * @param betaTwo The exponential decay rate for the exponentially weighted infinity norm.
   * @param epsilon A small constant for numerical stability.
   */
  public Nadam(
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
   * Create an Optimizer that implements the NAdam algorithm.
   *
   * @param tf the TensorFlow Ops
   * @param config a config object to initialize
   */
  public static Nadam create(Ops tf, Map<String, Object> config) {
    String name = (String) config.get(NAME_KEY);
    float learningRate = (float) config.getOrDefault(LEARNING_RATE_KEY, LEARNING_RATE_DEFAULT);
    float epsilon = (float) config.getOrDefault(EPSILON_KEY, EPSILON_DEFAULT);
    float betaOne = (float) config.getOrDefault(LEARNING_RATE_KEY, LEARNING_RATE_DEFAULT);
    float betaTwo = (float) config.getOrDefault(LEARNING_RATE_KEY, LEARNING_RATE_DEFAULT);
    if (name == null) {
      return new Nadam(tf, learningRate, betaOne, betaTwo, epsilon);
    } else {
      return new Nadam(tf, name, learningRate, betaOne, betaTwo, epsilon);
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
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createNadamSlot(v.asOutput());
    }
    betaOnePower = tf.withName("beta1_power").variable(Shape.scalar(), TFloat32.DTYPE);
    Assign<TFloat32> betaOnePowerInit = tf.assign(betaOnePower, tf.constant(betaOne));
    ((Graph) tf.scope().env()).addInitializer(betaOnePowerInit);

    betaTwoPower = tf.withName("beta2_power").variable(Shape.scalar(), TFloat32.DTYPE);
    Assign<TFloat32> betaTwoPowerInit = tf.assign(betaTwoPower, tf.constant(betaTwo));
    ((Graph) tf.scope().env()).addInitializer(betaTwoPowerInit);

    momentum = tf.withName("momentum").variable(Shape.scalar(), TFloat32.DTYPE);
    Assign<TFloat32> momentumInit = tf.assign(momentum, tf.constant(1.0F));
    ((Graph) tf.scope().env()).addInitializer(momentumInit);
  }

  /**
   * Create slots for first and second momements and momentum
   * @param v the variable
   * @param <T> the data type or the Variable
   */
  private <T extends TType> void createNadamSlot(Output<T> v) {
    Operand<T> firstMomentInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), FIRST_MOMENT, firstMomentInitializer);
    Operand<T> secondMomentInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.dataType()));
    createSlot(v.asOutput(), SECOND_MOMENT, secondMomentInitializer);

    Operand<T> momentumInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(1.0f), v.dataType()));
    createSlot(v.asOutput(), MOMENTUM, momentumInitializer);
  }

  /** {@inheritDoc} */
  @Override
  protected Optional<Op> prepare(String scopeName) {
    Constant one = tf.constant(1.0F);
    Constant point5 = tf.constant(0.5F);

    learningRateConst = tf.constant(learningRate);
    betaOneConst = tf.constant(betaOne);
    betaTwoConst = tf.constant(betaTwo);
    localStepConst = tf.constant(this.iterations + 1);
    nextStepConst = tf.constant(this.iterations + 2);
    decayConst = tf.constant(decay);
    decayBaseConst = tf.constant(this.decayBase);
    epsilonConst = tf.constant(this.epsilon);

    //  m_t = beta_1_t * (1. - 0.5 * ( math_ops.pow(decay_base, self._initial_decay * local_step)))
    m_t =
        tf.math.mul(
            betaOneConst,
            tf.math.sub(
                one,
                tf.math.mul(
                    point5,
                    tf.math.pow(
                        decayBaseConst,
                        tf.math.mul(decayConst, tf.dtypes.cast(localStepConst, TFloat32.DTYPE))))));
    // m_t_1 = beta_1_t * (1. - 0.5 * ( math_ops.pow(decay_base, self._initial_decay * next_step)))
    m_t_1 =
        tf.math.mul(
            betaOneConst,
            tf.math.sub(
                one,
                tf.math.mul(
                    point5,
                    tf.math.pow(
                        decayBaseConst,
                        tf.math.mul(decayConst, tf.dtypes.cast(nextStepConst, TFloat32.DTYPE))))));

    // m_schedule_new = math_ops.cast(self._m_cache_read, var_dtype) * m_t
    m_schedule_new = tf.math.mul(momentum, m_t);
    // if var_dtype is self._m_cache.dtype:
    // m_schedule_new = array_ops.identity(state_ops.assign(
    // self._m_cache, m_schedule_new, use_locking=self._use_locking))
    m_schedule_new = tf.identity(tf.assign(momentum, m_schedule_new, Assign.useLocking(true)));
    // m_schedule_next = m_schedule_new * m_t_1
    m_schedule_next = tf.math.mul(m_schedule_new, m_t_1);

    // 1 - beta_1_t
    one_minus_beta_1 = tf.math.sub(one, betaOneConst);
    // 1 - beta_2_t,
    one_minus_beta_2 = tf.math.sub(one, betaTwoConst);
    // 1. - m_t,
    one_minus_m_t = tf.math.sub(one, m_t);
    // 1. - m_schedule_new
    one_minus_m_schedule_new = tf.math.sub(one, m_schedule_new);
    // 1. - m_schedule_next
    one_minus_m_schedule_next = tf.math.sub(one, m_schedule_next);
    // 1. - math_ops.pow(beta_2_t, local_step)
    v_t_prime_denominator =
        tf.math.sub(one, tf.math.pow(betaTwoConst, tf.dtypes.cast(localStepConst, TFloat32.DTYPE)));
    return Optional.empty();
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> m = getSlot(variable, FIRST_MOMENT).get(); // first Moment
    Variable<T> v = getSlot(variable, SECOND_MOMENT).get(); // Second Moment

    //  g_prime = grad / coefficients['one_minus_m_schedule_new']
    Operand g_prime = tf.math.div((Operand) gradient, one_minus_m_schedule_new);
    // m_t = (coefficients['beta_1_t'] * m + coefficients['one_minus_beta_1_t'] * grad)
    Operand m_t =
        tf.math.add(
            tf.math.mul(betaOneConst, (Operand) m),
            tf.math.mul(one_minus_beta_1, (Operand) gradient));
    // m_t = state_ops.assign(m, m_t, use_locking=self._use_locking)
    // update m
    m_t = tf.assign(m, m_t, Assign.useLocking(true));

    // m_t_prime = m_t / coefficients['one_minus_m_schedule_next']
    Operand m_t_prime = tf.math.div(m_t, one_minus_m_schedule_next);

    // v_t = (coefficients['beta_2_t'] * v + coefficients['one_minus_beta_2_t'] *
    // math_ops.square(grad))
    Operand v_t =
        tf.math.add(
            tf.math.mul(betaTwoConst, (Operand) v),
            tf.math.mul(one_minus_beta_2, tf.math.square((Operand) gradient)));
    // v_t = state_ops.assign(v, v_t, use_locking=self._use_locking)
    // update v
    v_t = tf.assign(v, v_t, Assign.useLocking(true));

    // v_t_prime = v_t / coefficients['v_t_prime_denominator']
    Operand v_t_prime = tf.math.div(v_t, v_t_prime_denominator);

    // m_t_bar = (coefficients['one_minus_m_t'] * g_prime + coefficients['m_t_1'] * m_t_prime)
    Operand m_t_bar =
        tf.math.add(tf.math.mul(one_minus_m_t, g_prime), tf.math.mul(m_t_1, m_t_prime));
    // var_t = var - coefficients['lr_t'] * m_t_bar / (math_ops.sqrt(v_t_prime) +
    // coefficients['epsilon'])
    Operand var_t =
        tf.math.sub(
            variable,
            tf.math.div(
                tf.math.mul(learningRateConst, m_t_bar),
                tf.math.add(tf.math.sqrt(v_t_prime), epsilonConst)));
    // assign(var, var_t, use_locking=self._use_locking)
    return tf.assign(variable, var_t, Assign.useLocking(true));
  }

  /**
   * Gathers up the update operations into a single op that can be used as a run target.
   *
   * <p>Adds the betaOne, betaTwo and mu updates to the end of the updates list.
   *
   * @param updateOperations The update operations.
   * @param name The name of the run target.
   * @return A NoOp with a control dependency on each update operation.
   */
  @Override
  protected Op finish(List<Op> updateOperations, String name) {
    iterations++; // increment the step;
    updateOperations.add(tf.assign(betaOnePower, tf.math.mul(betaOnePower, betaOneConst)));
    updateOperations.add(tf.assign(betaTwoPower, tf.math.mul(betaTwoPower, betaTwoConst)));
    return super.finish(updateOperations, name);
  }

  /** {@inheritDoc} */
  @Override
  public String getOptimizerName() {
    return "Nadam";
  }

  /**
   * Sets the config object based on the current state of the Optmizer.
   *
   * @param learningRate The learning rate. Defaults to 0.001.
   * @param betaOne The exponential decay rate for the 1st moment estimates. Defaults to 0.9.
   * @param betaTwo The exponential decay rate for the 2nd moment estimates. Defaults to 0.999.
   * @param epsilon A small constant for numerical stability. This epsilon is "epsilon hat" in the
   *     Kingma and Ba paper (in the formula just before Section 2.1), not the epsilon in Algorithm
   *     1 of the paper. Defaults to 1e-7.
   */
  private void initConfig(float learningRate, float betaOne, float betaTwo, float epsilon) {
    config.put(NAME_KEY, this.getOptimizerName());
    config.put(LEARNING_RATE_KEY, learningRate);
    config.put(EPSILON_KEY, epsilon);
    config.put(BETA_ONE_KEY, betaOne);
    config.put(BETA_TWO_KEY, betaTwo);
  }

  private float calcM(int iteration) {
    return betaOne * (1 - .05F * (float) Math.pow(this.decayBase, this.decay * iteration));
  }
}
