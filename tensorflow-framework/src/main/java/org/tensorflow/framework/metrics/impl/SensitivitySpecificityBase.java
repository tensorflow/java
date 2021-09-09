package org.tensorflow.framework.metrics.impl;

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.tensorflow.Operand;
import org.tensorflow.framework.initializers.Zeros;
import org.tensorflow.framework.metrics.BaseMetric;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TNumber;

/**
 * Abstract base class for computing sensitivity and specificity.
 *
 * @param <T> The data internalType for the metric result
 */
public abstract class SensitivitySpecificityBase<T extends TNumber> extends BaseMetric {

  public static final int DEFAULT_NUM_THRESHOLDS = 200;

  public static final String TRUE_POSITIVES = "TRUE_POSITIVES";
  public static final String FALSE_POSITIVES = "FALSE_POSITIVES";
  public static final String TRUE_NEGATIVES = "TRUE_NEGATIVES";
  public static final String FALSE_NEGATIVES = "FALSE_NEGATIVES";
  protected final int numThresholds;

  protected final float[] thresholds;
  private final String truePositivesName;
  private final String falsePositivesName;
  private final String trueNegativesName;
  private final String falseNegativesName;
  private final Zeros<T> zeros = new Zeros<>();
  private final Class<T> internalType;
  protected Variable<T> truePositives;
  protected Variable<T> falsePositives;
  protected Variable<T> trueNegatives;
  protected Variable<T> falseNegatives;

  /**
   * Creates a SensitivitySpecificityBase Metric
   *
   * @param name the name of the metric instance, if null then {@link Class#getSimpleName()} is used
   * @param numThresholds The number of thresholds to use for matching the given recall.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data internalType.
   * @param internalType the data internalType for the variables
   * @throws IllegalArgumentException if numThresholds &lt;= 0.
   */
  protected SensitivitySpecificityBase(
      String name, int numThresholds, long seed, Class<T> internalType) {
    super(name, seed);
    if (numThresholds <= 0) {
      throw new IllegalArgumentException("numThresholds must be > 0.");
    }
    this.internalType = internalType;
    this.truePositivesName = this.getVariableName(TRUE_POSITIVES);
    this.falsePositivesName = this.getVariableName(FALSE_POSITIVES);
    this.trueNegativesName = this.getVariableName(TRUE_NEGATIVES);
    this.falseNegativesName = this.getVariableName(FALSE_NEGATIVES);

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
  }

  /** {@inheritDoc} */
  @Override
  protected void init(Ops tf) {
    checkIsGraph(tf);
    if (!isInitialized()) {
      setTF(tf);
      Shape varShape = Shape.of(numThresholds);
      Operand<T> zero = zeros.call(tf, tf.constant(varShape), internalType);

      if (this.getTruePositives() == null) {

        truePositives = tf.withName(truePositivesName).withInitScope().variable(zero);
      }
      if (this.getFalsePositives() == null) {

        falsePositives = tf.withName(falsePositivesName).withInitScope().variable(zero);
      }
      if (this.getTrueNegatives() == null) {

        trueNegatives = tf.withInitScope().withName(trueNegativesName).variable(zero);
      }
      if (this.getFalseNegatives() == null) {

        falseNegatives = tf.withInitScope().withName(falseNegativesName).variable(zero);
      }
      setInitialized(true);
    }
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
  public List<Op> updateStateList(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    init(tf);
    Operand<T> tLabels = cast(tf, labels, internalType);
    Operand<T> tPredictions = cast(tf, predictions, internalType);
    Operand<T> tSampleWeights =
        sampleWeights != null ? cast(tf, sampleWeights, internalType) : null;

    Map<ConfusionMatrixEnum, Variable<T>> confusionMatrix = new HashMap<>();
    confusionMatrix.put(ConfusionMatrixEnum.TRUE_POSITIVES, getTruePositives());
    confusionMatrix.put(ConfusionMatrixEnum.FALSE_POSITIVES, getFalsePositives());
    confusionMatrix.put(ConfusionMatrixEnum.TRUE_NEGATIVES, getTrueNegatives());
    confusionMatrix.put(ConfusionMatrixEnum.FALSE_NEGATIVES, getFalseNegatives());

    return MetricsHelper.updateConfusionMatrixVariables(
        tf,
        confusionMatrix,
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
  public Op resetStates(Ops tf) {

    Shape varShape = Shape.of(numThresholds);
    Operand<T> zero = zeros.call(tf, tf.constant(varShape), internalType);

    List<Op> controlList = new ArrayList<>();
    if (this.getTruePositives() != null) {
      controlList.add(tf.withName(truePositivesName).assign(this.getTruePositives(), zero));
    }
    if (this.getFalsePositives() != null) {
      controlList.add(tf.withName(falsePositivesName).assign(this.getFalsePositives(), zero));
    }
    if (this.getTrueNegatives() != null) {
      controlList.add(tf.withName(trueNegativesName).assign(this.getTrueNegatives(), zero));
    }
    if (this.getFalseNegatives() != null) {
      controlList.add(tf.withName(falseNegativesName).assign(this.getFalseNegatives(), zero));
    }
    if (controlList.size() == 1) {
      return controlList.get(0);
    } else {
      return tf.withControlDependencies(controlList).noOp();
    }
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
   * Gets the internalType
   *
   * @return the internalType
   */
  public Class<T> getType() {
    return internalType;
  }

  public Class<T> getInternalType() {
    return internalType;
  }
}
