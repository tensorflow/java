package org.tensorflow.framework.metrics.impl;

import org.tensorflow.Operand;
import org.tensorflow.framework.initializers.Zeros;
import org.tensorflow.framework.metrics.Metric;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Abstract base class for computing sensitivity and specificity.
 *
 * @param <T> The data type for the metric result
 */
public abstract class SensitivitySpecificityBase<T extends TNumber> extends Metric<T> {

  public static final int DEFAULT_NUM_THRESHOLDS = 200;

  public static final String TRUE_POSITIVES = "TRUE_POSITIVES";
  public static final String FALSE_POSITIVES = "FALSE_POSITIVES";
  public static final String TRUE_NEGATIVES = "TRUE_NEGATIVES";
  public static final String FALSE_NEGATIVES = "FALSE_NEGATIVES";
  protected final int numThresholds;
  protected final float value;
  protected final float[] thresholds;
  private final String truePositivesName;
  private final String falsePositivesName;
  private final String trueNegativesName;
  private final String falseNegativesName;
  private final Class<T> type;
  protected Variable<T> truePositives;
  protected Variable<T> falsePositives;
  protected Variable<T> trueNegatives;
  protected Variable<T> falseNegatives;

  private Assign<T> truePositivesInitializer;
  private Assign<T> falsePositivesInitializer;
  private Assign<T> trueNegativesInitializer;
  private Assign<T> falseNegativesInitializer;

  /**
   * Creates a SensitivitySpecificityBase Metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the metric instance, if null then {@link Class#getSimpleName()} is used
   * @param value A scalar value in range `[0, 1]`
   * @param numThresholds The number of thresholds to use for matching the given recall.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0.
   */
  protected SensitivitySpecificityBase(
      Ops tf, String name, float value, int numThresholds, long seed, Class<T> type) {
    super(tf, name, seed);
    if (numThresholds <= 0) throw new IllegalArgumentException("numThresholds must be > 0.");
    this.type = type;
    this.truePositivesName = this.getVariableName(TRUE_POSITIVES);
    this.falsePositivesName = this.getVariableName(FALSE_POSITIVES);
    this.trueNegativesName = this.getVariableName(TRUE_NEGATIVES);
    this.falseNegativesName = this.getVariableName(FALSE_NEGATIVES);

    this.value = value;
    this.numThresholds = numThresholds;

    if (this.numThresholds == 1) {
      this.thresholds = new float[] {0.5f};
    } else {
      this.thresholds = new float[numThresholds];
      for (int i = 0; i < numThresholds - 2; i++) {
        this.thresholds[i + 1] = (i + 1f) / (float) (numThresholds - 1);
      }
      this.thresholds[numThresholds - 1] = 1f;
    }
    init();
  }

  /** Initializes the Variables */
  private void init() {
    Ops tf = getTF();
    Zeros<T> zeros = new Zeros<>(tf);
    Shape varShape = Shape.of(numThresholds);
    Operand<T> zero = zeros.call(tf.constant(varShape), type);

    if (this.getTruePositives() == null) {

      truePositives = tf.withName(truePositivesName).variable(zero);
      truePositivesInitializer = tf.assign(truePositives, zero);
    }
    if (this.getFalsePositives() == null) {

      falsePositives = tf.withName(falsePositivesName).variable(zero);
      falsePositivesInitializer = tf.assign(falsePositives, zero);
    }
    if (this.getTrueNegatives() == null) {

      trueNegatives = tf.withName(trueNegativesName).variable(zero);
      trueNegativesInitializer = tf.assign(trueNegatives, zero);
    }
    if (this.getFalseNegatives() == null) {

      falseNegatives = tf.withName(falseNegativesName).variable(zero);
      falseNegativesInitializer = tf.assign(falseNegatives, zero);
    }
  }

  /**
   * Gets a control dependency Op to initialize all the variables
   *
   * @return a control dependency Op to initialize all the variables
   */
  public Op initializeVariables() {
    List<Op> varInitializers = new ArrayList<>();

    if (truePositivesInitializer != null) {
      varInitializers.add(truePositivesInitializer);
    }
    if (falsePositivesInitializer != null) {
      varInitializers.add(falsePositivesInitializer);
    }
    if (trueNegativesInitializer != null) {
      varInitializers.add(trueNegativesInitializer);
    }
    if (falseNegativesInitializer != null) {
      varInitializers.add(falseNegativesInitializer);
    }

    return getTF().withControlDependencies(varInitializers).noOp();
  }

  /**
   * Accumulates confusion matrix statistics.
   *
   * @param labels The ground truth values.
   * @param predictions the predictions
   * @param sampleWeights Optional weighting of each example. Defaults to 1. Rank is either 0, or
   *     the same rank as labels, and must be broadcastable to labels.
   * @return a List of Operations to update the metric state.
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<Op> updateStateList(
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    Ops tf = getTF();
    Operand<T> tLabels = cast(tf, labels, type);
    Operand<T> tPredictions = cast(tf, predictions, type);
    Operand<T> tSampleWeights = sampleWeights != null ? cast(tf, sampleWeights, type) : null;

    Map<ConfusionMatrixEnum, Variable<T>> confusionMatrix = new HashMap<>();
    confusionMatrix.put(ConfusionMatrixEnum.TRUE_POSITIVES, getTruePositives());
    confusionMatrix.put(ConfusionMatrixEnum.FALSE_POSITIVES, getFalsePositives());
    confusionMatrix.put(ConfusionMatrixEnum.TRUE_NEGATIVES, getTrueNegatives());
    confusionMatrix.put(ConfusionMatrixEnum.FALSE_NEGATIVES, getFalseNegatives());

    return MetricsHelper.updateConfusionMatrixVariables(
        tf,
        confusionMatrix,
        Collections.EMPTY_MAP,
        tLabels,
        tPredictions,
        tf.constant(thresholds),
        null,
        null,
        tSampleWeights,
        false,
        null);
  }

  /** {@inheritDoc} */
  @Override
  public Op resetStates() {
    return initializeVariables();
  }

  /**
   * Gets the truePositives variable
   *
   * @return the truePositives
   */
  public Variable<T> getTruePositives() {
    return truePositives;
  }

  /**
   * Gets the falsePositives variable
   *
   * @return the falsePositives truePositives
   */
  public Variable<T> getFalsePositives() {
    return falsePositives;
  }

  /**
   * Gets the trueNegatives variable
   *
   * @return the trueNegatives truePositives
   */
  public Variable<T> getTrueNegatives() {
    return trueNegatives;
  }

  /**
   * Gets the falseNegatives variable
   *
   * @return the falseNegatives truePositives
   */
  public Variable<T> getFalseNegatives() {
    return falseNegatives;
  }

  /**
   * Gets the numThresholds
   *
   * @return the numThresholds
   */
  public int getNumThresholds() {
    return numThresholds;
  }

  /**
   * Gets the value
   *
   * @return the value
   */
  public float getValue() {
    return value;
  }

  /**
   * Gets the thresholds
   *
   * @return the thresholds
   */
  public float[] getThresholds() {
    return thresholds;
  }

  /**
   * Gets the truePositives variable name
   *
   * @return the truePositivesName
   */
  public String getTruePositivesName() {
    return truePositivesName;
  }

  /**
   * Gets the falsePositives variable name
   *
   * @return the falsePositivesName
   */
  public String getFalsePositivesName() {
    return falsePositivesName;
  }

  /**
   * Gets the trueNegatives variable name
   *
   * @return the trueNegativesName
   */
  public String getTrueNegativesName() {
    return trueNegativesName;
  }

  /**
   * Gets the falseNegatives variable name
   *
   * @return the falseNegativesName
   */
  public String getFalseNegativesName() {
    return falseNegativesName;
  }

  /**
   * Gets the type
   *
   * @return the type
   */
  public Class<T> getType() {
    return type;
  }
}
