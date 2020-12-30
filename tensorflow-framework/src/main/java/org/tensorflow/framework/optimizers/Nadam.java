package org.tensorflow.framework.optimizers;

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

import java.util.List;
import java.util.Optional;

/**
 * Nadam Optimizer that implements the NAdam algorithm.
 *
 * <p>Much like Adam is essentially RMSprop with momentum, Nadam is Adam with Nesterov momentum.
 *
 * @see <a href="http://cs229.stanford.edu/proj2015/054_report.pdf">Dozat, 2015</a>
 */
public class Nadam extends Optimizer {

  private static final float DECAY_BASE = 0.96f;
  private static final float DECAY = 0.004f;
  public static final float LEARNING_RATE_DEFAULT = 0.001f;
  public static final float EPSILON_DEFAULT = 1e-8f;
  public static final float BETA_ONE_DEFAULT = 0.9f;
  public static final float BETA_TWO_DEFAULT = 0.999f;
  public static final String FIRST_MOMENT = "m";
  public static final String SECOND_MOMENT = "v";
  public static final String MOMENTUM = "momentum";

  /** The learning rate. */
  private final float learningRate;

  /** The exponential decay rate for the 1st moment estimates. */
  private final float betaOne;

  /** The exponential decay rate for the exponentially weighted infinity norm. */
  private final float betaTwo;

  /** A small constant for numerical stability. */
  private final float epsilon;

  private Constant<TFloat32> learningRateConst;
  private Constant<TFloat32> epsilonConst;
  private Constant<TFloat32> betaOneConst;
  private Constant<TFloat32> betaTwoConst;

  private Variable<TFloat32> betaOnePower;
  private Variable<TFloat32> betaTwoPower;
  private Variable<TFloat32> momentum;

  private long iterations = 0;

  // private Operand<TFloat32> mT;
  private Operand<TFloat32> mT1;

  private Operand<TFloat32> oneMinusBeta1;
  private Operand<TFloat32> oneMinusBeta2;
  private Operand<TFloat32> oneMinusMT;
  private Operand<TFloat32> oneMinusMScheduleNew;
  private Operand<TFloat32> oneMinusMScheduleNext;
  private Operand<TFloat32> vTPrimeDenominator;

  /**
   * Creates a Nadam Optimizer
   *
   * @param graph the TensorFlow graph
   */
  public Nadam(Graph graph) {
    this(graph, LEARNING_RATE_DEFAULT, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates a Nadam Optimizer
   *
   * @param graph the TensorFlow graph
   * @param learningRate the learning rate, defaults to 0.001
   */
  public Nadam(Graph graph, float learningRate) {
    this(graph, learningRate, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates a Nadam Optimizer
   *
   * @param graph the TensorFlow graph
   * @param learningRate the learning rate, defaults to 0.001
   * @param betaOne The exponential decay rate for the 1st moment estimates. Default is 0.9.
   * @param betaTwo The exponential decay rate for the exponentially weighted infinity norm. Default
   *     is 0.999.
   * @param epsilon A small constant for numerical stability. Default is 1e-8.
   */
  public Nadam(Graph graph, float learningRate, float betaOne, float betaTwo, float epsilon) {
    super(graph);
    this.learningRate = learningRate;
    this.betaOne = betaOne;
    this.betaTwo = betaTwo;
    this.epsilon = epsilon;
  }

  /**
   * Creates a Nadam Optimizer
   *
   * @param graph the TensorFlow graph
   * @param name the name for this Optimizer, defaults to "Nadam"
   * @param learningRate the learning rate, defaults to 0.001
   */
  public Nadam(Graph graph, String name, float learningRate) {
    this(graph, name, learningRate, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates a Nadam Optimizer
   *
   * @param graph the TensorFlow graph
   * @param name the name for this Optimizer, defaults to "Nadam"
   * @param learningRate the learning rate, defaults to 0.001
   * @param betaOne The exponential decay rate for the 1st moment estimates. Default is 0.9.
   * @param betaTwo The exponential decay rate for the exponentially weighted infinity norm. Default
   *     is 0.999.
   * @param epsilon A small constant for numerical stability. Default is 1e-8.
   */
  public Nadam(
      Graph graph, String name, float learningRate, float betaOne, float betaTwo, float epsilon) {
    super(graph, name);
    this.learningRate = learningRate;
    this.betaOne = betaOne;
    this.betaTwo = betaTwo;
    this.epsilon = epsilon;
  }

  /** {@inheritDoc} */
  @Override
  protected void createSlots(List<Output<? extends TType>> variables) {
    for (Output<? extends TType> v : variables) {
      createNadamSlot(v.asOutput());
    }
    betaOnePower = tf.withName("beta1_power").variable(Shape.scalar(), TFloat32.class);
    Assign<TFloat32> betaOnePowerInit = tf.assign(betaOnePower, tf.constant(betaOne));
    ((Graph) tf.scope().env()).addInitializer(betaOnePowerInit);

    betaTwoPower = tf.withName("beta2_power").variable(Shape.scalar(), TFloat32.class);
    Assign<TFloat32> betaTwoPowerInit = tf.assign(betaTwoPower, tf.constant(betaTwo));
    ((Graph) tf.scope().env()).addInitializer(betaTwoPowerInit);

    momentum = tf.withName("momentum").variable(Shape.scalar(), TFloat32.class);
    Assign<TFloat32> momentumInit = tf.assign(momentum, tf.constant(1.0F));
    ((Graph) tf.scope().env()).addInitializer(momentumInit);
  }

  /**
   * Creates slots for first and second moments and momentum
   *
   * @param v the variable
   * @param <T> the data type or the Variable
   */
  private <T extends TType> void createNadamSlot(Output<T> v) {
    Operand<T> firstMomentInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.type()));
    createSlot(v.asOutput(), FIRST_MOMENT, firstMomentInitializer);
    Operand<T> secondMomentInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.type()));
    createSlot(v.asOutput(), SECOND_MOMENT, secondMomentInitializer);

    Operand<T> momentumInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(1.0f), v.type()));
    createSlot(v.asOutput(), MOMENTUM, momentumInitializer);
  }

  /** {@inheritDoc} */
  @Override
  protected Optional<Op> prepare(String scopeName) {
    Constant<TFloat32> one = tf.constant(1.0F);
    Constant<TFloat32> point5 = tf.constant(0.5F);

    learningRateConst = tf.constant(learningRate);
    betaOneConst = tf.constant(betaOne);
    betaTwoConst = tf.constant(betaTwo);
    Constant<TInt64> localStepConst = tf.constant(this.iterations + 1);
    Constant<TInt64> nextStepConst = tf.constant(this.iterations + 2);
    Constant<TFloat32> decayConst = tf.constant(DECAY);
    Constant<TFloat32> decayBaseConst = tf.constant(DECAY_BASE);
    epsilonConst = tf.constant(this.epsilon);

    Operand<TFloat32> mT =
        tf.math.mul(
            betaOneConst,
            tf.math.sub(
                one,
                tf.math.mul(
                    point5,
                    tf.math.pow(
                        decayBaseConst,
                        tf.math.mul(decayConst, tf.dtypes.cast(localStepConst, TFloat32.class))))));

    mT1 =
        tf.math.mul(
            betaOneConst,
            tf.math.sub(
                one,
                tf.math.mul(
                    point5,
                    tf.math.pow(
                        decayBaseConst,
                        tf.math.mul(decayConst, tf.dtypes.cast(nextStepConst, TFloat32.class))))));

    Operand<TFloat32> mScheduleNew = tf.math.mul(momentum, mT);

    mScheduleNew = tf.assign(momentum, mScheduleNew, Assign.useLocking(true));
    Operand<TFloat32> mScheduleNext = tf.math.mul(mScheduleNew, mT1);

    oneMinusBeta1 = tf.math.sub(one, betaOneConst);
    oneMinusBeta2 = tf.math.sub(one, betaTwoConst);
    oneMinusMT = tf.math.sub(one, mT);
    oneMinusMScheduleNew = tf.math.sub(one, mScheduleNew);
    oneMinusMScheduleNext = tf.math.sub(one, mScheduleNext);
    vTPrimeDenominator =
        tf.math.sub(one, tf.math.pow(betaTwoConst, tf.dtypes.cast(localStepConst, TFloat32.class)));
    return Optional.empty();
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Class<T> type = gradient.type();
    Variable<T> m = getSlot(variable, FIRST_MOMENT).get(); // first Moment
    Variable<T> v = getSlot(variable, SECOND_MOMENT).get(); // Second Moment

    //  gPrime = grad / coefficients['oneMinusMScheduleNew']
    Operand<T> gPrime = tf.math.div(gradient, tf.dtypes.cast(oneMinusMScheduleNew, type));
    // mT = (coefficients['beta_1_t'] * m + coefficients['one_minus_beta_1_t'] * grad)
    Operand<T> mT =
        tf.math.add(
            tf.math.mul(tf.dtypes.cast(betaOneConst, type), m),
            tf.math.mul(tf.dtypes.cast(oneMinusBeta1, type), gradient));
    // mT = state_ops.assign(m, mT, use_locking=self._use_locking)
    // update m
    mT = tf.assign(m, mT, Assign.useLocking(true));

    // mTPrime = mT / coefficients['oneMinusMScheduleNext']
    Operand<T> mTPrime = tf.math.div(mT, tf.dtypes.cast(oneMinusMScheduleNext, type));

    // vT = (coefficients['beta_2_t'] * v + coefficients['one_minus_beta_2_t'] *
    // math_ops.square(grad))
    Operand<T> vT =
        tf.math.add(
            tf.math.mul(tf.dtypes.cast(betaTwoConst, type), v),
            tf.math.mul(tf.dtypes.cast(oneMinusBeta2, type), tf.math.square(gradient)));
    // vT = state_ops.assign(v, vT, use_locking=self._use_locking)
    // update v
    vT = tf.assign(v, vT, Assign.useLocking(true));

    // vTPrime = vT / coefficients['vTPrimeDenominator']
    Operand<T> vTPrime = tf.math.div(vT, tf.dtypes.cast(vTPrimeDenominator, type));

    // m_t_bar = (coefficients['oneMinusMT'] * gPrime + coefficients['mT1'] * mTPrime)
    Operand<T> m_t_bar =
        tf.math.add(
            tf.math.mul(tf.dtypes.cast(oneMinusMT, type), gPrime),
            tf.math.mul(tf.dtypes.cast(mT1, type), mTPrime));
    // varT = var - coefficients['lr_t'] * m_t_bar / (math_ops.sqrt(vTPrime) +
    // coefficients['epsilon'])
    Operand<T> varT =
        tf.math.sub(
            variable,
            tf.math.div(
                tf.math.mul(tf.dtypes.cast(learningRateConst, type), m_t_bar),
                tf.math.add(tf.math.sqrt(vTPrime), tf.dtypes.cast(epsilonConst, type))));

    return tf.assign(variable, varT, Assign.useLocking(true));
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
}
