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
package org.tensorflow.framework.metrics;

import static org.tensorflow.framework.losses.impl.LossesHelper.allAxes;
import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.tensorflow.Operand;
import org.tensorflow.framework.initializers.Zeros;
import org.tensorflow.framework.metrics.impl.ConfusionMatrixEnum;
import org.tensorflow.framework.metrics.impl.MetricsHelper;
import org.tensorflow.framework.metrics.impl.SymbolicShape;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;

/**
 * Metric that computes the approximate AUC (Area under the curve) via a Riemann sum.
 *
 * <p>This metric creates four local variables, {@code truePositives}, {@code trueNegatives },
 * {@code falsePositives} and {@code falseNegatives} that are used to compute the AUC. To discretize
 * the AUC curve, a linearly spaced set of thresholds is used to compute pairs of recall and
 * precision values. The area under the ROC-curve is therefore computed using the height of the
 * recall values by the false positive rate, while the area under the PR-curve is the computed using
 * the height of the precision values by the recall.
 *
 * <p>This value is ultimately returned as {@code auc}, an idempotent operation that computes the
 * area under a discretized curve of precision versus recall values (computed using the
 * aforementioned variables). The {@code numThresholds} variable controls the degree of
 * discretization with larger numbers of thresholds more closely approximating the true AUC. The
 * quality of the approximation may vary dramatically depending on {@code numThresholds}. The {@code
 * thresholds} parameter can be used to manually specify thresholds which split the predictions more
 * evenly.
 *
 * <p>For best results, {@code predictions} should be distributed approximately uniformly in the
 * range [0, 1] and not peaked around 0 or 1. The quality of the AUC approximation may be poor if
 * this is not the case. Setting {@code summationMethod} to {@code minoring} or {@code majoring} can
 * help quantify the error in the approximation by providing lower or upper bound estimate of the
 * AUC.
 *
 * <p>Usage: <br>
 *
 * <pre>
 * AUC m = new  org.tensorflow.framework.metrics.AUC( tf, 3);
 * m.updateState( tf.constant(new float[] {0, 0, 1,1}),
 *          tf.constant(new float[] {0f, 0.5f, 0.3f, 0.9f}));
 *
 * // threshold values are [0 - 1e-7, 0.5, 1 + 1e-7]
 * // tp = [2, 1, 0], fp = [2, 0, 0], fn = [0, 1, 2], tn = [0, 2, 2]
 * // recall = [1, 0.5, 0], fpRate = [1, 0, 0]
 * // auc = ((((1+0.5)/2)*(1-0))+ (((0.5+0)/2)*(0-0))) = 0.75
 * Operand&lt;TFloat32&gt; result = m.result();
 * System.out.println(result.data().getFloat());
 * 0.75
 * </pre>
 *
 * <pre>
 * m.resetStates()
 * m.updateState( tf.constant(new float[] {0, 0, 1, 1}),
 *                 tf.constant(new float[] {0f, 0.5f, 0.3f, 0.9f}, ),
 *                 tf.constant(new float[] {1, 0, 0, 1}));
 * result = m.result();
 * System.out.println(result.data().getFloat());
 * 1.0
 * </pre>
 *
 * @param <T> The data type for the metric result
 */
public class AUC<T extends TNumber> extends BaseMetric {

  /** Default Fuzz factor. */
  public static final float EPSILON = 1e-7f;

  public static final String TRUE_POSITIVES = "TRUE_POSITIVES";
  public static final String FALSE_POSITIVES = "FALSE_POSITIVES";
  public static final String TRUE_NEGATIVES = "TRUE_NEGATIVES";
  public static final String FALSE_NEGATIVES = "FALSE_NEGATIVES";
  public static final int DEFAULT_NUM_THRESHOLDS = 200;
  public static final String DEFAULT_NAME = "auc";

  private final int numThresholds;
  private final AUCCurve curve;
  private final AUCSummationMethod summationMethod;
  private final float[] thresholds;
  private final boolean multiLabel;
  private final String truePositivesName;
  private final String falsePositivesName;
  private final String trueNegativesName;
  private final String falseNegativesName;
  private final Class<T> type;
  private final Zeros<T> zeros = new Zeros<>();
  /** The size of the label dimension. */
  private Integer numLabels;

  private Operand<T> labelWeights;
  /**
   * If not {@link #multiLabel}, shape (T) where T is the number of thresholds.
   *
   * <p>If {@link #multiLabel}, shape (T, C0) where T is the number of thresholds and C0 is a single
   * class dimension within each example.
   */
  private Variable<T> truePositives;
  /**
   * If not {@link #multiLabel}, shape (T) where T is the number of thresholds.
   *
   * <p>If {@link #multiLabel}, shape (T, C0) where T is the number of thresholds and C0 is a single
   * class dimension within each example.
   */
  private Variable<T> falsePositives;
  /**
   * If not {@link #multiLabel}, shape (T) where T is the number of thresholds.
   *
   * <p>If {@link #multiLabel}, shape (T, C0) where T is the number of thresholds and C0 is a single
   * class dimension within each example.
   */
  private Variable<T> trueNegatives;
  /**
   * If not {@link #multiLabel}, shape (T) where T is the number of thresholds.
   *
   * <p>If {@link #multiLabel}, shape (T, C0) where T is the number of thresholds and C0 is a single
   * class dimension within each example.
   */
  private Variable<T> falseNegatives;

  private Shape variableShape;
  private Shape shape;

  /**
   * Creates an AUC (Area under the curve) metric using {@link #DEFAULT_NAME} for the metric name,
   * {@link #DEFAULT_NUM_THRESHOLDS} for the numThresholds, {@link AUCCurve#ROC} for the curve type,
   * {@link AUCSummationMethod#INTERPOLATION} for the summation method, {@code null} for thresholds,
   * {@code false} for multiLabel, and {@code null} for labelWeights.
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(long seed, Class<T> type) {
    this(
        null,
        DEFAULT_NUM_THRESHOLDS,
        AUCCurve.ROC,
        AUCSummationMethod.INTERPOLATION,
        null,
        false,
        null,
        seed,
        type);
  }

  /**
   * Creates an AUC (Area under the curve) metric using {@link #DEFAULT_NUM_THRESHOLDS} for the
   * numThresholds, {@link AUCCurve#ROC} for the curve type, {@link
   * AUCSummationMethod#INTERPOLATION} for the summation method, {@code null} for thresholds, {@code
   * false} for multiLabel, and {@code null} for labelWeights.
   *
   * @param name the name of the metric, if {@code null} defaults to {@link #DEFAULT_NAME}
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(String name, long seed, Class<T> type) {
    this(
        name,
        DEFAULT_NUM_THRESHOLDS,
        AUCCurve.ROC,
        AUCSummationMethod.INTERPOLATION,
        null,
        false,
        null,
        seed,
        type);
  }

  /**
   * Creates an AUC (Area under the curve) metric using {@link #DEFAULT_NAME} for the metric name,
   * {@link AUCCurve#ROC} for the curve type, {@link AUCSummationMethod#INTERPOLATION} for the
   * summation method, {@code null} for thresholds, {@code false} for multiLabel, and {@code null}
   * for labelWeights.
   *
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. Values
   *     must be &gt; 1.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(int numThresholds, long seed, Class<T> type) {
    this(
        null,
        numThresholds,
        AUCCurve.ROC,
        AUCSummationMethod.INTERPOLATION,
        null,
        false,
        null,
        seed,
        type);
  }

  /**
   * Creates an AUC (Area under the curve) metric using {@link #DEFAULT_NAME} for the metric name,
   * {@link AUCCurve#ROC} for the curve type, {@link AUCSummationMethod#INTERPOLATION} for the
   * summation method, {@code null} for numThresholds, {@code false} for multiLabel, and {@code
   * null} for labelWeights.
   *
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1].
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(float[] thresholds, long seed, Class<T> type) {
    this(
        null,
        DEFAULT_NUM_THRESHOLDS,
        AUCCurve.ROC,
        AUCSummationMethod.INTERPOLATION,
        thresholds,
        false,
        null,
        seed,
        type);
  }

  /**
   * Creates an AUC (Area under the curve) metric. using {@link AUCCurve#ROC} for the curve type,
   * {@link AUCSummationMethod#INTERPOLATION} for the summation method, {@code null} for thresholds,
   * {@code false} for multiLabel, and {@code null} for labelWeights.
   *
   * @param name the name of the metric, if {@code null} defaults to {@link #DEFAULT_NAME}
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. Values
   *     must be &gt; 1.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(String name, int numThresholds, long seed, Class<T> type) {
    this(
        name,
        numThresholds,
        AUCCurve.ROC,
        AUCSummationMethod.INTERPOLATION,
        null,
        false,
        null,
        seed,
        type);
  }

  /**
   * Creates an AUC (Area under the curve) metric using {@code null} for numThresholds, {@link
   * AUCCurve#ROC} for the curve type, {@link AUCSummationMethod#INTERPOLATION} for the summation
   * method, {@link #DEFAULT_NUM_THRESHOLDS} num thresholds, {@code false} for multiLabel, and
   * {@code null} for labelWeights.
   *
   * @param name the name of the metric, if {@code null} defaults to {@link #DEFAULT_NAME}
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1].
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(String name, float[] thresholds, long seed, Class<T> type) {
    this(
        name,
        DEFAULT_NUM_THRESHOLDS,
        AUCCurve.ROC,
        AUCSummationMethod.INTERPOLATION,
        thresholds,
        false,
        null,
        seed,
        type);
  }

  /**
   * Creates an AUC (Area under the curve) metric using {@link AUCSummationMethod#INTERPOLATION} for
   * the summation method, {@code null} for thresholds, {@code false} for multiLabel, and {@code
   * null} for labelWeights.
   *
   * @param name the name of the metric, if {@code null} defaults to {@link #DEFAULT_NAME}
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. Values
   *     must be &gt; 1.
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(String name, int numThresholds, AUCCurve curve, long seed, Class<T> type) {
    this(
        name,
        numThresholds,
        curve,
        AUCSummationMethod.INTERPOLATION,
        null,
        false,
        null,
        seed,
        type);
  }

  /**
   * Creates an AUC (Area under the curve) metric using {@code null} for numThresholds, {@link
   * AUCSummationMethod#INTERPOLATION} for the summation method, {@link #DEFAULT_NUM_THRESHOLDS} num
   * thresholds, {@code false} for multiLabel, and {@code null} for labelWeights.
   *
   * @param name the name of the metric, if {@code null} defaults to {@link #DEFAULT_NAME}
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1].
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(String name, float[] thresholds, AUCCurve curve, long seed, Class<T> type) {
    this(
        name,
        DEFAULT_NUM_THRESHOLDS,
        curve,
        AUCSummationMethod.INTERPOLATION,
        thresholds,
        false,
        null,
        seed,
        type);
  }

  /**
   * Creates an AUC (Area under the curve) metric using {@link #DEFAULT_NAME} for the metric name,
   * {@link AUCSummationMethod#INTERPOLATION} for the summation method, {@code null} for thresholds,
   * {@code false} for multiLabel, and {@code null} for labelWeights.
   *
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. Values
   *     must be &gt; 1.
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(int numThresholds, AUCCurve curve, long seed, Class<T> type) {
    this(
        null,
        numThresholds,
        curve,
        AUCSummationMethod.INTERPOLATION,
        null,
        false,
        null,
        seed,
        type);
  }

  /**
   * Creates an AUC (Area under the curve) metric using {@code null} for numThresholds, {@link
   * AUCSummationMethod#INTERPOLATION} for the summation method, {@code false} for multiLabel, and
   * {@code null} for labelWeights.
   *
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1].
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(float[] thresholds, AUCCurve curve, long seed, Class<T> type) {
    this(
        null,
        DEFAULT_NUM_THRESHOLDS,
        curve,
        AUCSummationMethod.INTERPOLATION,
        thresholds,
        false,
        null,
        seed,
        type);
  }

  /**
   * Creates an AUC (Area under the curve) metric. using {@link #DEFAULT_NAME} for the metric name,,
   * {@code null} for thresholds, {@code false} for multiLabel, and {@code null} for labelWeights.
   *
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. Values
   *     must be &gt; 1.
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param summationMethod Specifies the Riemann summation method used
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(
      int numThresholds,
      AUCCurve curve,
      AUCSummationMethod summationMethod,
      long seed,
      Class<T> type) {
    this(null, numThresholds, curve, summationMethod, null, false, null, seed, type);
  }

  /**
   * Creates an AUC (Area under the curve) metric using {@link #DEFAULT_NAME} for the metric name,
   * {@code null} for numThresholds, {@code false} for multiLabel, and {@code null} for
   * labelWeights.
   *
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1].
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param summationMethod Specifies the Riemann summation method used
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(
      float[] thresholds,
      AUCCurve curve,
      AUCSummationMethod summationMethod,
      long seed,
      Class<T> type) {
    this(null, DEFAULT_NUM_THRESHOLDS, curve, summationMethod, thresholds, false, null, seed, type);
  }

  /**
   * Creates an AUC (Area under the curve) metric. using {@code null} for thresholds, {@code false}
   * for multiLabel, and {@code null} for labelWeights.
   *
   * @param name the name of the metric, if {@code null} defaults to {@link #DEFAULT_NAME}
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. Values
   *     must be &gt; 1.
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param summationMethod Specifies the Riemann summation method used,
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(
      String name,
      int numThresholds,
      AUCCurve curve,
      AUCSummationMethod summationMethod,
      long seed,
      Class<T> type) {
    this(name, numThresholds, curve, summationMethod, null, false, null, seed, type);
  }

  /**
   * Creates an AUC (Area under the curve) metric. using {@code null} for the numThresholds, {@code
   * false} for multiLabel, and {@code null} for labelWeights.
   *
   * @param name the name of the metric, if {@code null} defaults to {@link #DEFAULT_NAME}
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1].
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param summationMethod Specifies the Riemann summation method used.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(
      String name,
      float[] thresholds,
      AUCCurve curve,
      AUCSummationMethod summationMethod,
      long seed,
      Class<T> type) {
    this(name, DEFAULT_NUM_THRESHOLDS, curve, summationMethod, thresholds, false, null, seed, type);
  }

  /**
   * Creates an AUC (Area under the curve) metric.
   *
   * @param name the name of the metric, if name is null then use {@link #DEFAULT_NAME}.
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. This
   *     includes the bracketing 0 and 1 thresholds, so the value must be &ge; 2.
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param summationMethod Specifies the Riemann summation method used
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1]. This method
   *     automatically brackets the provided {@code thresholds} with a (-{@link #EPSILON}) below and
   *     a (1 + {@link #EPSILON}) above.
   * @param multiLabel boolean indicating whether multilabel data should be treated as such, wherein
   *     AUC is computed separately for each label and then averaged across labels, or (when false)
   *     if the data should be flattened into a single label before AUC computation. In the latter
   *     case, when multilabel data is passed to AUC, each label-prediction pair is treated as an
   *     individual data point. Should be set to {@code false} for multi-class data.
   * @param labelWeights non-negative weights used to compute AUCs for multilabel data. When {@code
   *     multiLabel} is true, the weights are applied to the individual label AUCs when they are
   *     averaged to produce the multi-label AUC. When it's false, they are used to weight the
   *     individual label predictions in computing the confusion matrix on the flattened data.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   * @throws IllegalArgumentException if numThresholds is less than 2 and thresholds is null, or if
   *     a threshold value is less than 0 or greater than 1.
   */
  public AUC(
      String name,
      int numThresholds,
      AUCCurve curve,
      AUCSummationMethod summationMethod,
      float[] thresholds,
      boolean multiLabel,
      Operand<T> labelWeights,
      long seed,
      Class<T> type) {
    super(name == null ? DEFAULT_NAME : name, seed);
    truePositivesName = getVariableName(TRUE_POSITIVES);
    falsePositivesName = getVariableName(FALSE_POSITIVES);
    trueNegativesName = getVariableName(TRUE_NEGATIVES);
    falseNegativesName = getVariableName(FALSE_NEGATIVES);
    this.curve = curve;
    this.summationMethod = summationMethod;
    this.type = type;

    this.multiLabel = multiLabel;

    if (thresholds != null) { // ignore numThresholds
      for (float t : thresholds) {
        if (t < 0.0f || t > 1.0f) {
          throw new IllegalArgumentException(
              String.format(
                  "Threshold values must be in range [0, 1], inclusive. Invalid values: %s",
                  Arrays.toString(thresholds)));
        }
      }
      this.numThresholds = thresholds.length + 2;
      Arrays.sort(thresholds);
    } else {

      if (numThresholds <= 1) {
        throw new IllegalArgumentException("numThresholds must be > 1.");
      }
      this.numThresholds = numThresholds;
      thresholds = new float[this.numThresholds - 2];
      // linearly interpolate (numThresholds - 2) thresholds between endpoints
      for (int i = 0; i < thresholds.length; i++) {
        thresholds[i] = (i + 1) * 1.0f / (this.numThresholds - 1);
      }
    }

    // Add an endpoint "threshold" below zero and above one for either
    // threshold method to account for floating point imprecisions.
    this.thresholds = new float[this.numThresholds];
    this.thresholds[0] = -EPSILON;
    System.arraycopy(thresholds, 0, this.thresholds, 1, thresholds.length);
    this.thresholds[this.numThresholds - 1] = 1 + EPSILON;

    // Handle multilabel arguments.

    this.labelWeights = labelWeights;

    if (multiLabel) {
      numLabels = null;
    }
  }

  /** {@inheritDoc} */
  @Override
  protected void init(Ops tf) {
    checkIsGraph(tf);
    if (shape != null && !isInitialized()) {
      setTF(tf);
      if (labelWeights != null) {
        // assert that labelWeights are non-negative.

        Op checks =
            tf.withSubScope("AUC")
                .assertThat(
                    tf.math.greaterEqual(
                        labelWeights, cast(tf, tf.constant(0), labelWeights.type())),
                    Collections.singletonList(
                        tf.constant("All values of labelWeights must be non-negative.")));

        Ops ltf =
            tf.withSubScope("updateState")
                .withControlDependencies(Collections.singletonList(checks));

        this.labelWeights = ltf.identity(this.labelWeights);
      }
      if (isMultiLabel()) {
        if (shape == null) {
          throw new IllegalArgumentException("For multiLabel, a shape must be provided");
        }
        if (shape.numDimensions() != 2)
          throw new IllegalArgumentException(
              String.format(
                  "labels must have rank=2 when multiLabel is true. Found rank %d.",
                  shape.numDimensions()));
        numLabels = (int) shape.size(1);
        variableShape = Shape.of(numThresholds, numLabels);
      } else {
        variableShape = Shape.of(numThresholds);
      }

      // Create metric variables

      Operand<T> zero = zeros.call(tf, tf.constant(variableShape), type);
      if (truePositives == null) {
        truePositives = tf.withName(getTruePositivesName()).withInitScope().variable(zero);
      }

      if (falsePositives == null) {
        falsePositives = tf.withName(getFalsePositivesName()).withInitScope().variable(zero);
      }

      if (trueNegatives == null) {
        trueNegatives = tf.withName(getTrueNegativesName()).withInitScope().variable(zero);
      }

      if (falseNegatives == null) {
        falseNegatives = tf.withName(getFalseNegativesName()).withInitScope().variable(zero);
      }
      setInitialized(true);
    }
  }

  /**
   * Creates a List of Operations to update the metric state based on labels and predictions.
   *
   * @param labels shape (N, Cx, L1?) where N is the number of examples, Cx is zero or more class
   *     dimensions, and L1 is a potential extra dimension of size 1 that would be squeezed. Will be
   *     cast to {@code <T>}. If {@link #multiLabel} or if {@link #labelWeights} {@code != null },
   *     then Cx must be a single dimension.
   * @param predictions the predictions shape (N, Cx, P1?). Will be cast to {@code T}.
   * @param sampleWeights sample weights to be applied to values, may be null. Will be cast to
   *     {@code <T>}.
   * @return a List of Operations to update the metric state
   */
  @Override
  public List<Op> updateStateList(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {
    if (shape == null) {
      shape = predictions.shape();
    }
    init(tf);
    Operand<T> tLabels = cast(tf, labels, type);
    Operand<T> tPredictions = cast(tf, predictions, type);
    Operand<T> tSampleWeights = sampleWeights != null ? cast(tf, sampleWeights, type) : null;
    List<Op> updateOperations = new ArrayList<>();

    if (isMultiLabel() || getLabelWeights() != null) {
      // labels should have shape (number of examples, number of labels).
      List<SymbolicShape<? extends TNumber>> symbols = new ArrayList<>();
      symbols.add(new SymbolicShape<>(tLabels, "N", "L"));
      if (isMultiLabel()) {
        // TP, TN, FP, and FN should all have shape
        // (number of thresholds, number of labels).
        symbols.add(new SymbolicShape<>(truePositives, "T", "L"));
        symbols.add(new SymbolicShape<>(falsePositives, "T", "L"));
        symbols.add(new SymbolicShape<>(trueNegatives, "T", "L"));
        symbols.add(new SymbolicShape<>(falseNegatives, "T", "L"));
      }
      if (getLabelWeights() != null) {
        symbols.add(new SymbolicShape<>(getLabelWeights(), "L"));
      }
      updateOperations.addAll(
          MetricsHelper.assertShapes(tf, symbols, "Number of labels is not consistent."));
    }

    Map<ConfusionMatrixEnum, Variable<T>> confusionMatrix = new HashMap<>();
    confusionMatrix.put(ConfusionMatrixEnum.TRUE_POSITIVES, truePositives);
    confusionMatrix.put(ConfusionMatrixEnum.FALSE_POSITIVES, falsePositives);
    confusionMatrix.put(ConfusionMatrixEnum.TRUE_NEGATIVES, trueNegatives);
    confusionMatrix.put(ConfusionMatrixEnum.FALSE_NEGATIVES, falseNegatives);

    // Only forward labelWeights to update_confusion_matrix_variables when
    // multiLabel is false. Otherwise the averaging of individual label AUCs is
    // handled in AUC.result
    updateOperations.addAll(
        MetricsHelper.updateConfusionMatrixVariables(
            tf,
            confusionMatrix,
            tLabels,
            tPredictions,
            tf.constant(thresholds),
            null,
            null,
            tSampleWeights,
            isMultiLabel(),
            isMultiLabel() ? null : getLabelWeights()));
    return updateOperations;
  }

  /**
   * Gets the input with all positive numbers. Negative numbers are set to 0.
   *
   * @param input the input
   * @return the input with all positive numbers.
   */
  private Operand<T> positive(Ops tf, Operand<T> input) {
    return tf.math.maximum(input, cast(tf, tf.constant(0), input.type()));
  }

  /**
   * Gets the truth value of whether {@code input > 0}, element-wise.
   *
   * @param input the input
   * @return the truth value of whether {@code input > 0}, element-wise.
   */
  private Operand<TBool> isPositive(Ops tf, Operand<T> input) {
    return tf.math.greater(input, cast(tf, tf.constant(0), input.type()));
  }

  /**
   * Extracts a slice from the input.
   *
   * @param input the input
   * @param begin the beginning location of the slice
   * @param size the size of the slice
   * @return the slice
   */
  private Operand<T> slice(Ops tf, Operand<T> input, int begin, int size) {
    return tf.slice(input, tf.constant(new int[] {begin}), tf.constant(new int[] {size}));
  }

  /**
   * Interpolation formula inspired by section 4 of Davis & Goadrich 2006.
   *
   * <p>Note here we derive & use a closed formula not present in the paper as follows:
   *
   * <pre>
   *     Precision = TP / (TP + FP) = TP / P
   * </pre>
   *
   * <p>Modeling all of TP (true positive), FP (false positive) and their sum P = TP + FP (predicted
   * positive) as varying linearly within each interval [A, B] between successive thresholds, we get
   *
   * <pre>
   *     Precision slope = dTP / dP
   *                     = (TP_B - TP_A) / (P_B - P_A)
   *                     = (TP - TP_A) / (P - P_A)
   *     Precision = (TP_A + slope * (P - P_A)) / P
   * </pre>
   *
   * <p>The area within the interval is (slope / total_pos_weight) times
   *
   * <pre>
   *       int_A^B{Precision.dP} = int_A^B{(TP_A + slope * (P - P_A)) * dP / P}
   *       int_A^B{Precision.dP} = int_A^B{slope * dP + intercept * dP / P}
   * </pre>
   *
   * where intercept = TP_A - slope * P_A = TP_B - slope * P_B, resulting in
   *
   * <pre>
   *       int_A^B{Precision.dP} = TP_B - TP_A + intercept * log(P_B / P_A)
   * </pre>
   *
   * Bringing back the factor (slope / total_pos_weight) we'd put aside, we get
   *
   * <pre>
   *       slope * [dTP + intercept *  log(P_B / P_A)] / total_pos_weight
   * </pre>
   *
   * where dTP == TP_B - TP_A. Note that when P_A == 0 the above calculation simplifies into
   *
   * <pre>
   *       int_A^B{Precision.dTP} = int_A^B{slope * dTP} = slope * (TP_B - TP_A)
   * </pre>
   *
   * which is really equivalent to imputing constant precision throughout the first bucket having >0
   * true positives.
   *
   * @return an approximation of the area under the P-R curve.
   * @see <a href="https://www.biostat.wisc.edu/~page/rocpr.pdf">The Relationship Between
   *     Precision-Recall and ROC Curves - Davis & Goadrich 2006</a>
   */
  private Operand<T> interpolatePRAuc(Ops tf) {
    // truePositives[:self.numThresholds - 1]
    Operand<T> tp0 = slice(tf, truePositives, 0, getNumThresholds() - 1);
    // truePositives[1:]
    Operand<T> tp1 = slice(tf, truePositives, 1, -1);

    Operand<T> dTP = tf.math.sub(tp0, tp1);

    Operand<T> p = tf.math.add(truePositives, falsePositives);
    Operand<T> p0 = slice(tf, p, 0, getNumThresholds() - 1);
    Operand<T> p1 = slice(tf, p, 1, -1);

    Operand<T> dP = tf.math.sub(p0, p1);

    Operand<T> precisionSlope = tf.math.divNoNan(dTP, positive(tf, dP));

    Operand<T> intercept = tf.math.sub(tp1, tf.math.mul(precisionSlope, p1));

    Operand<T> safePRatio =
        tf.select(
            tf.math.logicalAnd(isPositive(tf, p0), isPositive(tf, p1)),
            tf.math.divNoNan(p0, positive(tf, p1)),
            tf.onesLike(p1));

    Operand<T> fn1 = slice(tf, falseNegatives, 1, -1);

    Operand<T> aucTotalPos =
        tf.math.mul(
            precisionSlope, tf.math.add(dTP, tf.math.mul(intercept, tf.math.log(safePRatio))));

    Operand<T> prAucIncrement = tf.math.divNoNan(aucTotalPos, positive(tf, tf.math.add(tp1, fn1)));

    if (isMultiLabel()) {
      Operand<T> byLabelAuc = tf.reduceSum(prAucIncrement, tf.constant(0));
      if (getLabelWeights() == null) {
        // Evenly weighted average of the label AUCs.
        return MetricsHelper.mean(tf, byLabelAuc);
      } else {
        // Weighted average of the label AUCs.
        return tf.math.divNoNan(
            tf.reduceSum(tf.math.mul(byLabelAuc, getLabelWeights()), allAxes(tf, byLabelAuc)),
            tf.reduceSum(getLabelWeights(), allAxes(tf, getLabelWeights())));
      }
    } else {
      return tf.reduceSum(prAucIncrement, allAxes(tf, prAucIncrement));
    }
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TNumber> Operand<U> result(Ops tf, Class<U> resultType) {
    init(tf);
    if (getCurve() == AUCCurve.PR && getSummationMethod() == AUCSummationMethod.INTERPOLATION) {
      // This use case is different and is handled separately.
      return cast(tf, interpolatePRAuc(tf), resultType);
    }
    Operand<T> x;
    Operand<T> y;
    Operand<T> recall = tf.math.divNoNan(truePositives, tf.math.add(truePositives, falseNegatives));

    switch (getCurve()) {
      case ROC:
        x = tf.math.divNoNan(falsePositives, tf.math.add(falsePositives, trueNegatives));
        y = recall;
        break;
      case PR:
        y = tf.math.divNoNan(truePositives, tf.math.add(truePositives, falsePositives));
        x = recall;
        break;
      default:
        throw new IllegalArgumentException("Unexpected AUCCurve value: " + getCurve());
    }

    // Find the rectangle heights based on `summationMethod`.
    // y[:self.numThresholds - 1]
    Operand<T> ySlice1 = slice(tf, y, 0, getNumThresholds() - 1);
    // y[1:]
    Operand<T> ySlice2 = slice(tf, y, 1, -1);

    Operand<T> heights;
    switch (getSummationMethod()) {
      case INTERPOLATION:
        //noinspection SuspiciousNameCombination
        heights = tf.math.div(tf.math.add(ySlice1, ySlice2), cast(tf, tf.constant(2), y.type()));
        break;
      case MINORING:
        //noinspection SuspiciousNameCombination
        heights = tf.math.minimum(ySlice1, ySlice2);
        break;
      case MAJORING:
        //noinspection SuspiciousNameCombination
        heights = tf.math.maximum(ySlice1, ySlice2);
        break;
      default:
        throw new IllegalArgumentException(
            "Unexpected AUCSummationMethod value: " + getSummationMethod());
    }

    if (isMultiLabel()) {
      Operand<T> riemannTerms =
          tf.math.mul(
              tf.math.sub(slice(tf, x, 0, getNumThresholds() - 1), slice(tf, x, 1, -1)), heights);
      Operand<T> byLabelAuc = tf.reduceSum(riemannTerms, tf.constant(0));

      if (getLabelWeights() == null) {
        return cast(tf, MetricsHelper.mean(tf, byLabelAuc), resultType);
      } else {
        // Weighted average of the label AUCs.
        return cast(
            tf,
            tf.math.divNoNan(
                tf.reduceSum(
                    tf.math.mul(byLabelAuc, getLabelWeights()), allAxes(tf, getLabelWeights())),
                tf.reduceSum(getLabelWeights(), allAxes(tf, getLabelWeights()))),
            resultType);
      }

    } else {
      Operand<T> slice1 = slice(tf, x, 0, getNumThresholds() - 1);
      Operand<T> slice2 = slice(tf, x, 1, -1);
      Operand<T> sub = tf.math.sub(slice1, slice2);
      Operand<T> operand = tf.math.mul(sub, heights);
      return cast(tf, tf.reduceSum(operand, allAxes(tf, operand)), resultType);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Op resetStates(Ops tf) {
    init(tf);
    Operand<T> zero = zeros.call(tf, tf.constant(variableShape), type);
    List<Op> controlList = new ArrayList<>();
    if (truePositives != null) {
      controlList.add(tf.assign(truePositives, zero));
    }
    if (falsePositives != null) {
      controlList.add(tf.assign(falsePositives, zero));
    }
    if (trueNegatives != null) {
      controlList.add(tf.assign(trueNegatives, zero));
    }
    if (falseNegatives != null) {
      controlList.add(tf.assign(falseNegatives, zero));
    }
    return tf.withControlDependencies(controlList).noOp();
  }

  /**
   * @return the numThresholds
   */
  public int getNumThresholds() {
    return numThresholds;
  }

  /**
   * @return the curve
   */
  public AUCCurve getCurve() {
    return curve;
  }

  /**
   * @return the summationMethod
   */
  public AUCSummationMethod getSummationMethod() {
    return summationMethod;
  }

  /**
   * @return the thresholds
   */
  public float[] getThresholds() {
    return thresholds;
  }

  /**
   * @return the multiLabel
   */
  public boolean isMultiLabel() {
    return multiLabel;
  }

  /**
   * @return the numLabels
   */
  public Integer getNumLabels() {
    return numLabels;
  }

  /**
   * @param numLabels the numLabels to set
   */
  public void setNumLabels(Integer numLabels) {
    this.numLabels = numLabels;
  }

  /**
   * @return the labelWeights
   */
  public Operand<T> getLabelWeights() {
    return labelWeights;
  }

  /**
   * @return the truePositives
   */
  public Variable<T> getTruePositives() {
    return truePositives;
  }

  /**
   * @return the falsePositives
   */
  public Variable<T> getFalsePositives() {
    return falsePositives;
  }

  /**
   * @return the trueNegatives
   */
  public Variable<T> getTrueNegatives() {
    return trueNegatives;
  }

  /**
   * @return the falseNegatives
   */
  public Variable<T> getFalseNegatives() {
    return falseNegatives;
  }

  /**
   * @return the truePositivesName
   */
  public String getTruePositivesName() {
    return truePositivesName;
  }

  /**
   * @return the falsePositivesName
   */
  public String getFalsePositivesName() {
    return falsePositivesName;
  }

  /**
   * @return the trueNegativesName
   */
  public String getTrueNegativesName() {
    return trueNegativesName;
  }

  /**
   * @return the falseNegativesName
   */
  public String getFalseNegativesName() {
    return falseNegativesName;
  }
}
