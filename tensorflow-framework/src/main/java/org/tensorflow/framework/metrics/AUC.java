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

import org.tensorflow.Operand;
import org.tensorflow.framework.initializers.Zeros;
import org.tensorflow.framework.metrics.impl.ConfusionMatrixEnum;
import org.tensorflow.framework.metrics.impl.MetricsHelper;
import org.tensorflow.framework.metrics.impl.SymbolicShape;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.family.TNumber;

import java.util.*;

import static org.tensorflow.framework.losses.impl.LossesHelper.allAxes;
import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Metric that computes the approximate AUC (Area under the curve) via a Riemann sum.
 *
 * <p>This metric creates four local variables, <code>truePositives</code>, <code>trueNegatives
 * </code>, <code>falsePositives</code> and <code>falseNegatives</code> that are used to compute the
 * AUC. To discretize the AUC curve, a linearly spaced set of thresholds is used to compute pairs of
 * recall and precision values. The area under the ROC-curve is therefore computed using the height
 * of the recall values by the false positive rate, while the area under the PR-curve is the
 * computed using the height of the precision values by the recall.
 *
 * <p>This value is ultimately returned as <code>auc</code>, an idempotent operation that computes
 * the area under a discretized curve of precision versus recall values (computed using the
 * aforementioned variables). The <code>numThresholds</code> variable controls the degree of
 * discretization with larger numbers of thresholds more closely approximating the true AUC. The
 * quality of the approximation may vary dramatically depending on <code>numThresholds</code>. The
 * <code>thresholds</code> parameter can be used to manually specify thresholds which split the
 * predictions more evenly.
 *
 * <p>For best results, <code>predictions</code> should be distributed approximately uniformly in
 * the range [0, 1] and not peaked around 0 or 1. The quality of the AUC approximation may be poor
 * if this is not the case. Setting <code>summationMethod</code> to <code>minoring</code> or <code>
 * majoring</code> can help quantify the error in the approximation by providing lower or upper
 * bound estimate of the AUC.
 *
 * <p>Usage: <br>
 *
 * <pre>
 * AUC m = new  org.tensorflow.framework.metrcis.AUC( tf, 3);
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
public class AUC<T extends TNumber> extends Metric<T> {

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
  private final Map<ConfusionMatrixEnum, Assign<T>> initializers = new HashMap<>();
  private final Class<T> type;

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

  private boolean initialized;

  /**
   * Creates an AUC (Area under the curve) metric using {@link #DEFAULT_NAME} for the metric name,
   * {@link #DEFAULT_NUM_THRESHOLDS} for the numThresholds, {@link AUCCurve#ROC} for the curve type,
   * {@link AUCSummationMethod#INTERPOLATION} for the summation method, <code>null</code> for
   * thresholds, <code>false</code> for multiLabel, and <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(Ops tf, long seed, Class<T> type) {
    this(
        tf,
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
   * AUCSummationMethod#INTERPOLATION} for the summation method, <code>null</code> for thresholds,
   * <code>false</code> for multiLabel, and <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if <code>null</code> defaults to {@link #DEFAULT_NAME}
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(Ops tf, String name, long seed, Class<T> type) {
    this(
        tf,
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
   * summation method, <code>null</code> for thresholds, <code>false</code> for multiLabel, and
   * <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. Values
   *     must be &gt; 1.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(Ops tf, int numThresholds, long seed, Class<T> type) {
    this(
        tf,
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
   * summation method, <code>null</code> for numThresholds, <code>false</code> for multiLabel, and
   * <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1].
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(Ops tf, float[] thresholds, long seed, Class<T> type) {
    this(
        tf,
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
   * {@link AUCSummationMethod#INTERPOLATION} for the summation method, <code>null</code> for
   * thresholds, <code>false</code> for multiLabel, and <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if <code>null</code> defaults to {@link #DEFAULT_NAME}
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. Values
   *     must be &gt; 1.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(Ops tf, String name, int numThresholds, long seed, Class<T> type) {
    this(
        tf,
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
   * Creates an AUC (Area under the curve) metric using <code>null</code> for numThresholds, {@link
   * AUCCurve#ROC} for the curve type, {@link AUCSummationMethod#INTERPOLATION} for the summation
   * method, {@link #DEFAULT_NUM_THRESHOLDS} num thresholds, <code>false</code> for multiLabel, and
   * <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if <code>null</code> defaults to {@link #DEFAULT_NAME}
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1].
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(Ops tf, String name, float[] thresholds, long seed, Class<T> type) {
    this(
        tf,
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
   * the summation method, <code>null</code> for thresholds, <code>false</code> for multiLabel, and
   * <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if <code>null</code> defaults to {@link #DEFAULT_NAME}
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. Values
   *     must be &gt; 1.
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(Ops tf, String name, int numThresholds, AUCCurve curve, long seed, Class<T> type) {
    this(
        tf,
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
   * Creates an AUC (Area under the curve) metric using <code>null</code> for numThresholds, {@link
   * AUCSummationMethod#INTERPOLATION} for the summation method, {@link #DEFAULT_NUM_THRESHOLDS} num
   * thresholds, <code>false</code> for multiLabel, and <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if <code>null</code> defaults to {@link #DEFAULT_NAME}
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1].
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(Ops tf, String name, float[] thresholds, AUCCurve curve, long seed, Class<T> type) {
    this(
        tf,
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
   * {@link AUCSummationMethod#INTERPOLATION} for the summation method, <code>null</code> for
   * thresholds, <code>false</code> for multiLabel, and <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. Values
   *     must be &gt; 1.
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(Ops tf, int numThresholds, AUCCurve curve, long seed, Class<T> type) {
    this(
        tf,
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
   * Creates an AUC (Area under the curve) metric using <code>null</code> for numThresholds, {@link
   * AUCSummationMethod#INTERPOLATION} for the summation method, <code>false</code> for multiLabel,
   * and <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1].
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   */
  public AUC(Ops tf, float[] thresholds, AUCCurve curve, long seed, Class<T> type) {
    this(
        tf,
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
   * <code>null</code> for thresholds, <code>false</code> for multiLabel, and <code>null</code> for
   * labelWeights.
   *
   * @param tf The TensorFlow Ops
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
      Ops tf,
      int numThresholds,
      AUCCurve curve,
      AUCSummationMethod summationMethod,
      long seed,
      Class<T> type) {
    this(tf, null, numThresholds, curve, summationMethod, null, false, null, seed, type);
  }

  /**
   * Creates an AUC (Area under the curve) metric using {@link #DEFAULT_NAME} for the metric name,
   * <code>null</code> for numThresholds, <code>false</code> for multiLabel, and <code>null</code>
   * for labelWeights.
   *
   * @param tf The TensorFlow Ops
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
      Ops tf,
      float[] thresholds,
      AUCCurve curve,
      AUCSummationMethod summationMethod,
      long seed,
      Class<T> type) {
    this(
        tf,
        null,
        DEFAULT_NUM_THRESHOLDS,
        curve,
        summationMethod,
        thresholds,
        false,
        null,
        seed,
        type);
  }

  /**
   * Creates an AUC (Area under the curve) metric. using <code>null</code> for thresholds, <code>
   * false</code> for multiLabel, and <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if <code>null</code> defaults to {@link #DEFAULT_NAME}
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
      Ops tf,
      String name,
      int numThresholds,
      AUCCurve curve,
      AUCSummationMethod summationMethod,
      long seed,
      Class<T> type) {
    this(tf, name, numThresholds, curve, summationMethod, null, false, null, seed, type);
  }

  /**
   * Creates an AUC (Area under the curve) metric. using <code>null</code> for the numThresholds,
   * <code>false</code> for multiLabel, and <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if <code>null</code> defaults to {@link #DEFAULT_NAME}
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
      Ops tf,
      String name,
      float[] thresholds,
      AUCCurve curve,
      AUCSummationMethod summationMethod,
      long seed,
      Class<T> type) {
    this(
        tf,
        name,
        DEFAULT_NUM_THRESHOLDS,
        curve,
        summationMethod,
        thresholds,
        false,
        null,
        seed,
        type);
  }

  /**
   * Creates an AUC (Area under the curve) metric.
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if name is null then use {@link #DEFAULT_NAME}.
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. This
   *     includes the bracketing 0 and 1 thresholds, so the value must be &GE; 2.
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param summationMethod Specifies the Riemann summation method used
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1]. This method
   *     automatically brackets the provided <code>thresholds</code> with a (-{@link #EPSILON})
   *     below and a (1 + {@link #EPSILON}) above.
   * @param multiLabel boolean indicating whether multilabel data should be treated as such, wherein
   *     AUC is computed separately for each label and then averaged across labels, or (when false)
   *     if the data should be flattened into a single label before AUC computation. In the latter
   *     case, when multilabel data is passed to AUC, each label-prediction pair is treated as an
   *     individual data point. Should be set to <code>false</code> for multi-class data.
   * @param labelWeights non-negative weights used to compute AUCs for multilabel data. When <code>
   *     multiLabel</code> is true, the weights are applied to the individual label AUCs when they
   *     are averaged to produce the multi-label AUC. When it's false, they are used to weight the
   *     individual label predictions in computing the confusion matrix on the flattened data.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the confusion matrix variables.
   * @throws IllegalArgumentException if numThresholds is less than 2 and thresholds is null, or if
   *     a threshold value is less than 0 or greater than 1.
   */
  public AUC(
      Ops tf,
      String name,
      int numThresholds,
      AUCCurve curve,
      AUCSummationMethod summationMethod,
      float[] thresholds,
      boolean multiLabel,
      Operand<T> labelWeights,
      long seed,
      Class<T> type) {
    super(tf, name == null ? DEFAULT_NAME : name, seed);
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
                  "Threshold values must be in [0, 1]. Invalid values: %s",
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
    // threshold method to account for floating point imprecision.
    if (thresholds.length != this.numThresholds - 2) {
      throw new IllegalArgumentException(
          "Thresholds length must contain numThresholds - 2 entries");
    }
    // Add an endpoint "threshold" below zero and above one for either
    // threshold method to account for floating point imprecisions.
    this.thresholds = new float[this.numThresholds];
    this.thresholds[0] = -EPSILON;
    System.arraycopy(thresholds, 0, this.thresholds, 1, thresholds.length);
    this.thresholds[this.numThresholds - 1] = 1 + EPSILON;

    // Handle multilabel arguments.

    if (labelWeights != null) {
      // assert that labelWeights are non-negative.

      this.labelWeights = labelWeights;
      Op checks =
          tf.withSubScope("AUC")
              .assertThat(
                  tf.math.greaterEqual(labelWeights, cast(tf, tf.constant(0), labelWeights.type())),
                  Collections.singletonList(
                      tf.constant("All values of labelWeights must be non-negative.")));

      Ops ltf =
          tf.withSubScope("updateState").withControlDependencies(Collections.singletonList(checks));

      this.labelWeights = ltf.identity(this.labelWeights);
    }

    if (multiLabel) {
      numLabels = null;
    }
  }

  /**
   * Initialize truePositives, falsePositives, trueNegatives, and falseNegatives variables, given
   * the shape of the data.
   *
   * @param shape the prediction shape if called from updateState, otherwise null
   */
  @SuppressWarnings("unchecked")
  private Map<ConfusionMatrixEnum, Assign<T>> build(Shape shape) {
    Shape variableShape;
    if (initialized) {
      return Collections.EMPTY_MAP;
    }
    Ops tf = getTF();

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
    Zeros<T> zeros = new Zeros<>(tf);
    Operand<T> zero = zeros.call(tf.constant(variableShape), type);
    if (truePositives == null) {
      truePositives = tf.withName(getTruePositivesName()).variable(zero);
      initializers.put(ConfusionMatrixEnum.TRUE_POSITIVES, tf.assign(truePositives, zero));
    }

    if (falsePositives == null) {
      falsePositives = tf.withName(getFalsePositivesName()).variable(zero);
      initializers.put(ConfusionMatrixEnum.FALSE_POSITIVES, tf.assign(falsePositives, zero));
    }

    if (trueNegatives == null) {
      trueNegatives = tf.withName(getTrueNegativesName()).variable(zero);
      initializers.put(ConfusionMatrixEnum.TRUE_NEGATIVES, tf.assign(trueNegatives, zero));
    }

    if (falseNegatives == null) {
      falseNegatives = tf.withName(getFalseNegativesName()).variable(zero);
      initializers.put(ConfusionMatrixEnum.FALSE_NEGATIVES, tf.assign(falseNegatives, zero));
    }

    initialized = true;
    return initializers;
  }

  /**
   * Creates a List of Operations to update the metric state based on labels and predictions.
   *
   * @param labels shape (N, Cx, L1?) where N is the number of examples, Cx is zero or more class
   *     dimensions, and L1 is a potential extra dimension of size 1 that would be squeezed. Will be
   *     cast to <code>T</code>. If {@link #multiLabel} or if {@link #labelWeights} <code>!= null
   *     </code>, then Cx must be a single dimension.
   * @param predictions the predictions shape (N, Cx, P1?). Will be cast to <code>T</code>.
   * @param sampleWeights sample weights to be applied to values, may be null. Will be cast to
   *     <code>T</code>.
   * @return a List of Operations to update the metric state
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
    List<Op> updateOperations = new ArrayList<>();
    Map<ConfusionMatrixEnum, Assign<T>> varInitializers = Collections.EMPTY_MAP;
    if (!initialized) {
      varInitializers = build(tPredictions.shape());
    }
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
        symbols.add(new SymbolicShape<>(getLabelWeights(), "L", ""));
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
            varInitializers,
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
   * Interpolation formula inspired by section 4 of Davis & Goadrich 2006.
   *
   * @return an approximation of the area under the P-R curve.
   */
  private Operand<T> interpolatePRAuc() {
    // truePositives[:self.numThresholds - 1]
    Ops tf = getTF();
    Operand<T> tp0 =
        tf.slice(
            truePositives,
            tf.constant(new int[] {0}),
            tf.constant(new int[] {getNumThresholds() - 1}));
    // truePositives[1:]
    Operand<T> tp1 =
        tf.slice(truePositives, tf.constant(new int[] {1}), tf.constant(new int[] {-1}));

    Operand<T> dTP = tf.math.sub(tp0, tp1);

    Operand<T> p = tf.math.add(truePositives, falsePositives);

    Operand<T> dP =
        tf.math.sub(
            tf.slice(
                p, tf.constant(new int[] {0}), tf.constant(new int[] {getNumThresholds() - 1})),
            tf.slice(p, tf.constant(new int[] {1}), tf.constant(new int[] {-1})));

    Operand<T> precisionSlope =
        tf.math.divNoNan(dTP, tf.math.maximum(dP, tf.dtypes.cast(tf.constant(0), dP.type())));

    Operand<T> intercept =
        tf.math.sub(
            tf.slice(truePositives, tf.constant(new int[] {1}), tf.constant(new int[] {-1})),
            tf.math.mul(
                precisionSlope,
                tf.slice(p, tf.constant(new int[] {1}), tf.constant(new int[] {-1}))));

    Operand<T> safePRatio =
        tf.select(
            tf.math.logicalAnd(
                tf.math.greater(
                    tf.slice(
                        p,
                        tf.constant(new int[] {0}),
                        tf.constant(new int[] {getNumThresholds() - 1})),
                    tf.dtypes.cast(tf.constant(0), p.type())),
                tf.math.greater(
                    tf.slice(p, tf.constant(new int[] {1}), tf.constant(new int[] {-1})),
                    tf.dtypes.cast(tf.constant(0), p.type()))),
            tf.math.divNoNan(
                tf.slice(
                    p, tf.constant(new int[] {0}), tf.constant(new int[] {getNumThresholds() - 1})),
                tf.math.maximum(
                    tf.slice(p, tf.constant(new int[] {1}), tf.constant(new int[] {-1})),
                    tf.dtypes.cast(tf.constant(0), p.type()))),
            tf.onesLike(tf.slice(p, tf.constant(new int[] {1}), tf.constant(new int[] {-1}))));

    Operand<T> fn1 =
        tf.slice(falseNegatives, tf.constant(new int[] {1}), tf.constant(new int[] {-1}));

    Operand<T> aucTotalPos =
        tf.math.mul(
            precisionSlope, tf.math.add(dTP, tf.math.mul(intercept, tf.math.log(safePRatio))));

    Operand<T> prAucIncrement =
        tf.math.divNoNan(
            aucTotalPos,
            tf.math.maximum(
                tf.math.add(tp1, fn1), tf.dtypes.cast(tf.constant(0), truePositives.type())));

    if (isMultiLabel()) {
      Operand<T> byLabelAuc = tf.reduceSum(prAucIncrement, tf.constant(0));
      if (getLabelWeights() == null) {
        return MetricsHelper.mean(tf, byLabelAuc);
      } else {
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
  public Operand<T> result() {

    if (getCurve() == AUCCurve.PR && getSummationMethod() == AUCSummationMethod.INTERPOLATION) {
      // This use case is different and is handled separately.
      return interpolatePRAuc();
    }
    Ops tf = getTF();
    Operand<T> x;
    Operand<T> y;
    Operand<T> recall = tf.math.divNoNan(truePositives, tf.math.add(truePositives, falseNegatives));

    if (getCurve() == AUCCurve.ROC) {
      x = tf.math.divNoNan(falsePositives, tf.math.add(falsePositives, trueNegatives));
      y = recall;
    } else { // AUCCurve.PR
      y = tf.math.divNoNan(truePositives, tf.math.add(truePositives, falsePositives));
      x = recall;
    }

    // Find the rectangle heights based on `summationMethod`.
    // y[:self.numThresholds - 1]
    Operand<T> ySlice1 =
        tf.slice(y, tf.constant(new int[] {0}), tf.constant(new int[] {getNumThresholds() - 1}));
    // y[1:]
    Operand<T> ySlice2 = tf.slice(y, tf.constant(new int[] {1}), tf.constant(new int[] {-1}));

    Operand<T> heights = null;
    switch (getSummationMethod()) {
      case INTERPOLATION:
        heights = tf.math.div(tf.math.add(ySlice1, ySlice2), cast(tf, tf.constant(2), y.type()));
        break;
      case MINORING:
        heights = tf.math.minimum(ySlice1, ySlice2);
        break;
      case MAJORING:
        heights = tf.math.maximum(ySlice1, ySlice2);
        break;
    }

    if (isMultiLabel()) {
      Operand<T> riemannTerms =
          tf.math.mul(
              tf.math.sub(
                  tf.slice(
                      x,
                      tf.constant(new int[] {0}),
                      tf.constant(new int[] {getNumThresholds() - 1})),
                  tf.slice(x, tf.constant(new int[] {1}), tf.constant(new int[] {-1}))),
              heights);
      Operand<T> byLabelAuc = tf.reduceSum(riemannTerms, tf.constant(0));

      if (getLabelWeights() == null) {
        return MetricsHelper.mean(tf, byLabelAuc);
      } else {
        // Weighted average of the label AUCs.
        return tf.math.divNoNan(
            tf.reduceSum(
                tf.math.mul(byLabelAuc, getLabelWeights()), allAxes(tf, getLabelWeights())),
            tf.reduceSum(getLabelWeights(), allAxes(tf, getLabelWeights())));
      }

    } else {
      Operand<T> slice1 =
          tf.slice(x, tf.constant(new int[] {0}), tf.constant(new int[] {getNumThresholds() - 1}));
      Operand<T> slice2 = tf.slice(x, tf.constant(new int[] {1}), tf.constant(new int[] {-1}));
      Operand<T> sub = tf.math.sub(slice1, slice2);
      Operand<T> operand = tf.math.mul(sub, heights);
      return tf.reduceSum(operand, allAxes(tf, operand));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Op resetStates() {
    List<Op> updateOperations = new ArrayList<>(initializers.values());
    return getTF().withSubScope("resetStates").withControlDependencies(updateOperations).noOp();
  }

  /** @return the numThresholds */
  public int getNumThresholds() {
    return numThresholds;
  }

  /** @return the curve */
  public AUCCurve getCurve() {
    return curve;
  }

  /** @return the summationMethod */
  public AUCSummationMethod getSummationMethod() {
    return summationMethod;
  }

  /** @return the thresholds */
  public float[] getThresholds() {
    return thresholds;
  }

  /** @return the multiLabel */
  public boolean isMultiLabel() {
    return multiLabel;
  }

  /** @return the numLabels */
  public Integer getNumLabels() {
    return numLabels;
  }

  /** @param numLabels the numLabels to set */
  public void setNumLabels(Integer numLabels) {
    this.numLabels = numLabels;
  }

  /** @return the labelWeights */
  public Operand<T> getLabelWeights() {
    return labelWeights;
  }

  /** @return the truePositives */
  public Variable<T> getTruePositives() {
    return truePositives;
  }

  /** @return the falsePositives */
  public Variable<T> getFalsePositives() {
    return falsePositives;
  }

  /** @return the trueNegatives */
  public Variable<T> getTrueNegatives() {
    return trueNegatives;
  }

  /** @return the falseNegatives */
  public Variable<T> getFalseNegatives() {
    return falseNegatives;
  }

  /** @return the truePositivesName */
  public String getTruePositivesName() {
    return truePositivesName;
  }

  /** @return the falsePositivesName */
  public String getFalsePositivesName() {
    return falsePositivesName;
  }

  /** @return the trueNegativesName */
  public String getTrueNegativesName() {
    return trueNegativesName;
  }

  /** @return the falseNegativesName */
  public String getFalseNegativesName() {
    return falseNegativesName;
  }
}
