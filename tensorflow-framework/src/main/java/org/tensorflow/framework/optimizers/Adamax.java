package org.tensorflow.framework.optimizers;

import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.train.ApplyAdaMax;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

import java.util.List;
import java.util.Optional;

/**
 * Optimizer that implements the Adamax algorithm.
 *
 * <p>It is a variant of Adam based on the infinity norm. Default parameters follow those provided
 * in the paper. Adamax is sometimes superior to adam, specially in models with embeddings.
 *
 * @see <a href="https://arxiv.org/abs/1412.6980">Kingma et al., 2014</a>
 */
public class Adamax extends Optimizer {

  public static final String FIRST_MOMENT = "m";
  public static final String SECOND_MOMENT = "v";

  public static final float LEARNING_RATE_DEFAULT = 0.001f;
  public static final float EPSILON_DEFAULT = 1e-07f;
  public static final float BETA_ONE_DEFAULT = 0.9f;
  public static final float BETA_TWO_DEFAULT = 0.999f;

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
   * Creates an Optimizer that implements the Adamax algorithm.
   *
   * @param graph the TensorFlow graph
   */
  public Adamax(Graph graph) {
    this(graph, LEARNING_RATE_DEFAULT, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates an Optimizer that implements the Adamax algorithm.
   *
   * @param graph the TensorFlow graph
   * @param name name for the operations Created when applying gradients. Defaults to "Adamax".
   */
  public Adamax(Graph graph, String name) {
    this(graph, name, LEARNING_RATE_DEFAULT, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates an Optimizer that implements the Adamax algorithm.
   *
   * @param graph the TensorFlow graph
   * @param learningRate The learning rate.
   */
  public Adamax(Graph graph, float learningRate) {
    this(graph, learningRate, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates an Optimizer that implements the Adamax algorithm.
   *
   * @param graph the TensorFlow graph
   * @param name name for the operations Created when applying gradients. Defaults to "Adamax".
   * @param learningRate The learning rate.
   */
  public Adamax(Graph graph, String name, float learningRate) {
    this(graph, name, learningRate, BETA_ONE_DEFAULT, BETA_TWO_DEFAULT, EPSILON_DEFAULT);
  }

  /**
   * Creates an Optimizer that implements the Adamax algorithm.
   *
   * @param graph the TensorFlow graph
   * @param learningRate The learning rate.
   * @param betaOne The exponential decay rate for the 1st moment estimates.
   * @param betaTwo The exponential decay rate for the exponentially weighted infinity norm.
   * @param epsilon A small constant for numerical stability.
   */
  public Adamax(Graph graph, float learningRate, float betaOne, float betaTwo, float epsilon) {
    super(graph);
    this.learningRate = learningRate;
    this.betaOne = betaOne;
    this.betaTwo = betaTwo;
    this.epsilon = epsilon;
  }

  /**
   * Creates an Optimizer that implements the Adamax algorithm.
   *
   * @param graph the TensorFlow graph
   * @param name name for the operations Created when applying gradients. Defaults to "Adamax".
   * @param learningRate The learning rate.
   * @param betaOne The exponential decay rate for the 1st moment estimates.
   * @param betaTwo The exponential decay rate for the exponentially weighted infinity norm.
   * @param epsilon A small constant for numerical stability.
   */
  public Adamax(
      Graph graph, String name, float learningRate, float betaOne, float betaTwo, float epsilon) {
    super(graph, name);
    this.learningRate = learningRate;
    this.betaOne = betaOne;
    this.betaTwo = betaTwo;
    this.epsilon = epsilon;
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
    betaOnePower = tf.withName("beta1_power").variable(Shape.scalar(), TFloat32.class);
    Assign<TFloat32> betaOnePowerInit = tf.assign(betaOnePower, tf.constant(betaOne));
    ((Graph) tf.scope().env()).addInitializer(betaOnePowerInit);
  }

  /**
   * Creates the first and second moment slots
   *
   * @param v the variable
   * @param <T> the datatype of the variable
   */
  private <T extends TType> void createAdamaxSlot(Output<T> v) {
    Operand<T> firstMomentInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.type()));
    createSlot(v.asOutput(), FIRST_MOMENT, firstMomentInitializer);
    Operand<T> secondMomentInitializer =
        tf.fill(tf.shape(v), tf.dtypes.cast(tf.constant(0.0f), v.type()));
    createSlot(v.asOutput(), SECOND_MOMENT, secondMomentInitializer);
  }

  /** {@inheritDoc} */
  @Override
  protected <T extends TType> Op applyDense(Output<T> gradient, Output<T> variable) {
    Variable<T> firstMomentSlot = getSlot(variable, FIRST_MOMENT).get();
    Variable<T> secondMomentSlot = getSlot(variable, SECOND_MOMENT).get();
    return ApplyAdaMax.create(
        this.tf.scope(),
        variable,
        firstMomentSlot,
        secondMomentSlot,
        tf.dtypes.cast(betaOnePower, gradient.type()),
        tf.dtypes.cast(learningRateConst, gradient.type()),
        tf.dtypes.cast(betaOneConst, gradient.type()),
        tf.dtypes.cast(betaTwoConst, gradient.type()),
        tf.dtypes.cast(epsilonConst, gradient.type()),
        gradient);
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
}
