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
 * <p>This metric creates four local variables, <code>truePositives`, <code>trueNegatives`,
 * <code>falsePositives` and <code>falseNegatives` that are used to compute the AUC. To discretize the AUC
 * curve, a linearly spaced set of thresholds is used to compute pairs of recall and precision
 * values. The area under the ROC-curve is therefore computed using the height of the recall values
 * by the false positive rate, while the area under the PR-curve is the computed using the height of
 * the precision values by the recall.
 *
 * <p>This value is ultimately returned as <code>auc</code>, an idempotent operation that computes the area
 * under a discretized curve of precision versus recall values (computed using the aforementioned
 * variables). The <code>numThresholds</code> variable controls the degree of discretization with larger
 * numbers of thresholds more closely approximating the true AUC. The quality of the approximation
 * may vary dramatically depending on <code>numThresholds`. The <code>thresholds</code> parameter can be used to
 * manually specify thresholds which split the predictions more evenly.
 *
 * <p>For best results, <code>predictions</code> should be distributed approximately uniformly in the range [0,
 * 1] and not peaked around 0 or 1. The quality of the AUC approximation may be poor if this is not
 * the case. Setting <code>summationMethod</code> to <code>minoring</code> or <code>majoring</code> can help quantify the error in
 * the approximation by providing lower or upper bound estimate of the AUC.
 * <p>
 * <p>
 * Usage: <br>
 * <pre>
 * AUC m = new  getTF().keras.metrics.AUC( getTF(), 3);
 * m.updateState( getTF().constant(new float[] {0, 0, 1,1}),
 *          getTF().constant(new float[] {0f, 0.5f, 0.3f, 0.9f}));
 *
 * // threshold values are [0 - 1e-7, 0.5, 1 + 1e-7]
 * // tp = [2, 1, 0], fp = [2, 0, 0], fn = [0, 1, 2], tn = [0, 2, 2]
 * // recall = [1, 0.5, 0], fpRate = [1, 0, 0]
 * // auc = ((((1+0.5)/2)*(1-0))+ (((0.5+0)/2)*(0-0))) = 0.75
 * Operand&ltTFloat32&gt result = m.result();
 * System.out.println(result.data().getFloat());
 * 0.75
 * </pre>
 * <pre>
 * m.resetStates()
 * m.updateState( getTF().constant(new float[] {0, 0, 1, 1}),
 *                 getTF().constant(new float[] {0f, 0.5f, 0.3f, 0.9f}, ),
 *                 getTF().constant(new float[] {1, 0, 0, 1}));
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
  private Integer numLabels;
  private Operand<T> labelWeights;
  private Variable<T> truePositives;
  private Variable<T> falsePositives;
  private Variable<T> trueNegatives;
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
   *     must be > 1.
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
        null,
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
   *     must be > 1.
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
        null,
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
   *     must be > 1.
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
        null,
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
   *     must be > 1.
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
        null,
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
   *     must be > 1.
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
    this(tf, null, null, curve, summationMethod, thresholds, false, null, seed, type);
  }

  /**
   * Creates an AUC (Area under the curve) metric. using <code>null</code> for thresholds, <code>
   * false</code> for multiLabel, and <code>null</code> for labelWeights.
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if <code>null</code> defaults to {@link #DEFAULT_NAME}
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. Values
   *     must be > 1.
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
   * Creates an AUC (Area under the curve) metric. using <code>null</code>> for the numThresholds,
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
    this(tf, name, null, curve, summationMethod, thresholds, false, null, seed, type);
  }

  /**
   * Creates an AUC (Area under the curve) metric.
   *
   * @param tf The TensorFlow Ops
   * @param name the name of the metric, if name is null then use {@link #DEFAULT_NAME}.
   * @param numThresholds the number of thresholds to use when discretizing the roc curve. Values
   *     must be > 1. if null, the default is {@link #DEFAULT_NUM_THRESHOLDS}
   * @param curve specifies the type of the curve to be computed, {@link AUCCurve#ROC} or {@link
   *     AUCCurve#PR} for the Precision-Recall-curve.
   * @param summationMethod Specifies the Riemann summation method used
   * @param thresholds Optional values to use as the thresholds for discretizing the curve. If set,
   *     the numThresholds parameter is ignored. Values should be in [0, 1].
   * @param multiLabel boolean indicating whether multilabel data should be treated as such, wherein
   *     AUC is computed separately for each label and then averaged across labels, or (when false)
   *     if the data should be flattened into a single label before AUC computation. In the latter
   *     case, when multilabel data is passed to AUC, each label-prediction pair is treated as an
   *     individual data point. Should be set to <code>false</code> for multi-class data.
   * @param labelWeights non-negative weights used to compute AUCs for multilabel data. When
   *     multi_label is True, the weights are applied to the individual label AUCs when they are
   *     averaged to produce the multi-label AUC. When it's false, they are used to weight the
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
      Integer numThresholds,
      AUCCurve curve,
      AUCSummationMethod summationMethod,
      float[] thresholds,
      boolean multiLabel,
      Operand<T> labelWeights,
      long seed,
      Class<T> type) {
    super(tf, name == null ? DEFAULT_NAME : name, seed);
    this.truePositivesName = this.getVariableName(TRUE_POSITIVES);
    this.falsePositivesName = this.getVariableName(FALSE_POSITIVES);
    this.trueNegativesName = this.getVariableName(TRUE_NEGATIVES);
    this.falseNegativesName = this.getVariableName(FALSE_NEGATIVES);
    this.curve = curve;
    this.summationMethod = summationMethod;
    this.type = type;

    this.multiLabel = multiLabel;

    if (thresholds != null) { // ignore numThresholds
      for (float t : thresholds)
        if (t < 0.0f || t > 1.0f)
          throw new IllegalArgumentException(
              String.format(
                  "Threshold values must be in [0, 1]. Invalid values: %s",
                  Arrays.toString(thresholds)));
      this.numThresholds = thresholds.length + 2;
      Arrays.sort(thresholds);
    } else {
      if (numThresholds <= 1) throw new IllegalArgumentException("numThresholds must be > 1.");
      this.numThresholds = numThresholds;
      thresholds = new float[numThresholds - 2];
      // linearly interpolate (numThresholds - 2) thresholds between endpoints
      for (int i = 0; i < thresholds.length; i++) {
        thresholds[i] = (i + 1) * 1.0f / (this.numThresholds - 1);
      }
    }
    // Add an endpoint "threshold" below zero and above one for either
    // threshold method to account for floating point imprecision.
    if (thresholds.length != this.numThresholds - 2)
      throw new IllegalArgumentException(
          "Thresholds length must contain numThresholds - 2 entries");
    this.thresholds = new float[this.numThresholds];
    this.thresholds[0] = -EPSILON;
    System.arraycopy(thresholds, 0, this.thresholds, 1, thresholds.length);
    this.thresholds[this.numThresholds - 1] = 1 + EPSILON;

    if (labelWeights != null) {
      // assert that labelWeights are non-negative.

      this.labelWeights = labelWeights;
      Op checks =
          getTF()
              .withSubScope("AUC")
              .assertThat(
                  getTF()
                      .math
                      .greaterEqual(
                          labelWeights, cast(getTF(), getTF().constant(0), labelWeights.type())),
                  Collections.singletonList(
                      getTF().constant("All values of `labelWeights` must be non-negative.")));

      Ops ltf =
          getTF()
              .withSubScope("updateState")
              .withControlDependencies(Collections.singletonList(checks));

      this.labelWeights = ltf.identity(this.labelWeights);
    }

    if (this.multiLabel) {
      this.numLabels = null;
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

    if (this.isMultiLabel()) {
      if (shape == null) {
        throw new IllegalArgumentException("For multiLabel, a shape must be provided");
      }
      if (shape.numDimensions() != 2)
        throw new IllegalArgumentException(
            String.format(
                "labels must have rank=2 when multiLabel is true. Found rank %d.",
                shape.numDimensions()));
      this.numLabels = (int) shape.size(1);
      variableShape = Shape.of(this.numThresholds, this.numLabels);
    } else {
      variableShape = Shape.of(this.numThresholds);
    }

    Zeros<T> zeros = new Zeros<>(getTF());
    Operand<T> zero = zeros.call(getTF().constant(variableShape), type);
    if (truePositives == null) {
      truePositives = getTF().withName(getTruePositivesName()).variable(zero);
      initializers.put(ConfusionMatrixEnum.TRUE_POSITIVES, getTF().assign(truePositives, zero));
    }

    if (falsePositives == null) {
      falsePositives = getTF().withName(getFalsePositivesName()).variable(zero);
      initializers.put(ConfusionMatrixEnum.FALSE_POSITIVES, getTF().assign(falsePositives, zero));
    }

    if (trueNegatives == null) {
      trueNegatives = getTF().withName(getTrueNegativesName()).variable(zero);
      initializers.put(ConfusionMatrixEnum.TRUE_NEGATIVES, getTF().assign(trueNegatives, zero));
    }

    if (falseNegatives == null) {
      falseNegatives = getTF().withName(getFalseNegativesName()).variable(zero);
      initializers.put(ConfusionMatrixEnum.FALSE_NEGATIVES, getTF().assign(falseNegatives, zero));
    }

    this.initialized = true;
    return initializers;
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public List<Op> updateStateList(
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights) {

    Operand<T> lLabels = cast(getTF(), labels, type);
    Operand<T> lPredictions = cast(getTF(), predictions, type);
    Operand<T> tSampleWeights = sampleWeights != null ? cast(getTF(), sampleWeights, type) : null;
    List<Op> updateOperations = new ArrayList<>();
    Map<ConfusionMatrixEnum, Assign<T>> varInitializers = Collections.EMPTY_MAP;
    if (!this.initialized) {
      varInitializers = build(lPredictions.shape());
    }
    if (this.isMultiLabel() || this.getLabelWeights() != null) {
      List<SymbolicShape<? extends TNumber>> symbols = new ArrayList<>();
      symbols.add(new SymbolicShape<>(lLabels, "N", "L"));
      if (this.isMultiLabel()) {
        symbols.add(new SymbolicShape<>(this.truePositives, "T", "L"));
        symbols.add(new SymbolicShape<>(this.falsePositives, "T", "L"));
        symbols.add(new SymbolicShape<>(this.trueNegatives, "T", "L"));
        symbols.add(new SymbolicShape<>(this.falseNegatives, "T", "L"));
      }
      if (this.getLabelWeights() != null) {
        symbols.add(new SymbolicShape<>(this.getLabelWeights(), "L", ""));
      }
      updateOperations.addAll(
          MetricsHelper.assertShapes(getTF(), symbols, "Number of labels is not consistent."));
    }
    if (this.isMultiLabel()) {
      this.labelWeights = null;
    }
    Map<ConfusionMatrixEnum, Variable<T>> confusionMatrix = new HashMap<>();
    confusionMatrix.put(ConfusionMatrixEnum.TRUE_POSITIVES, this.truePositives);
    confusionMatrix.put(ConfusionMatrixEnum.FALSE_POSITIVES, this.falsePositives);
    confusionMatrix.put(ConfusionMatrixEnum.TRUE_NEGATIVES, this.trueNegatives);
    confusionMatrix.put(ConfusionMatrixEnum.FALSE_NEGATIVES, this.falseNegatives);

    updateOperations.addAll(
        MetricsHelper.updateConfusionMatrixVariables(
            getTF(),
            confusionMatrix,
            varInitializers,
            lLabels,
            lPredictions,
            this.thresholds,
            null,
            null,
            tSampleWeights,
            this.isMultiLabel(),
            this.getLabelWeights()));
    return updateOperations;
  }

  /**
   * Interpolation formula inspired by section 4 of Davis & Goadrich 2006.
   *
   * @return an approximation of the area under the P-R curve.
   */
  private Operand<T> interpolatePRAuc() {
    // truePositives[:self.numThresholds - 1]
    Operand<T> tp0 =
        getTF()
            .slice(
                truePositives,
                getTF().constant(new int[] {0}),
                getTF().constant(new int[] {this.getNumThresholds() - 1}));
    // truePositives[1:]
    Operand<T> tp1 =
        getTF()
            .slice(
                truePositives, getTF().constant(new int[] {1}), getTF().constant(new int[] {-1}));

    Operand<T> dTP = getTF().math.sub(tp0, tp1);

    Operand<T> p = getTF().math.add(truePositives, falsePositives);

    Operand<T> dP =
        getTF()
            .math
            .sub(
                getTF()
                    .slice(
                        p,
                        getTF().constant(new int[] {0}),
                        getTF().constant(new int[] {this.getNumThresholds() - 1})),
                getTF()
                    .slice(p, getTF().constant(new int[] {1}), getTF().constant(new int[] {-1})));

    Operand<T> precisionSlope =
        getTF()
            .math
            .divNoNan(
                dTP, getTF().math.maximum(dP, getTF().dtypes.cast(getTF().constant(0), dP.type())));

    Operand<T> intercept =
        getTF()
            .math
            .sub(
                getTF()
                    .slice(
                        truePositives,
                        getTF().constant(new int[] {1}),
                        getTF().constant(new int[] {-1})),
                getTF()
                    .math
                    .mul(
                        precisionSlope,
                        getTF()
                            .slice(
                                p,
                                getTF().constant(new int[] {1}),
                                getTF().constant(new int[] {-1}))));

    Operand<T> safePRatio =
        getTF()
            .select(
                getTF()
                    .math
                    .logicalAnd(
                        getTF()
                            .math
                            .greater(
                                getTF()
                                    .slice(
                                        p,
                                        getTF().constant(new int[] {0}),
                                        getTF().constant(new int[] {this.getNumThresholds() - 1})),
                                getTF().dtypes.cast(getTF().constant(0), p.type())),
                        getTF()
                            .math
                            .greater(
                                getTF()
                                    .slice(
                                        p,
                                        getTF().constant(new int[] {1}),
                                        getTF().constant(new int[] {-1})),
                                getTF().dtypes.cast(getTF().constant(0), p.type()))),
                getTF()
                    .math
                    .divNoNan(
                        getTF()
                            .slice(
                                p,
                                getTF().constant(new int[] {0}),
                                getTF().constant(new int[] {this.getNumThresholds() - 1})),
                        getTF()
                            .math
                            .maximum(
                                getTF()
                                    .slice(
                                        p,
                                        getTF().constant(new int[] {1}),
                                        getTF().constant(new int[] {-1})),
                                getTF().dtypes.cast(getTF().constant(0), p.type()))),
                getTF()
                    .onesLike(
                        getTF()
                            .slice(
                                p,
                                getTF().constant(new int[] {1}),
                                getTF().constant(new int[] {-1}))));

    Operand<T> fn1 =
        getTF()
            .slice(
                falseNegatives, getTF().constant(new int[] {1}), getTF().constant(new int[] {-1}));

    Operand<T> aucTotalPos =
        getTF()
            .math
            .mul(
                precisionSlope,
                getTF().math.add(dTP, getTF().math.mul(intercept, getTF().math.log(safePRatio))));

    Operand<T> prAucIncrement =
        getTF()
            .math
            .divNoNan(
                aucTotalPos,
                getTF()
                    .math
                    .maximum(
                        getTF().math.add(tp1, fn1),
                        getTF().dtypes.cast(getTF().constant(0), this.truePositives.type())));

    if (this.isMultiLabel()) {
      Operand<T> byLabelAuc = getTF().reduceSum(prAucIncrement, getTF().constant(0));
      if (this.getLabelWeights() == null) {
        return MetricsHelper.mean(getTF(), byLabelAuc);
      } else {
        return getTF()
            .math
            .divNoNan(
                getTF()
                    .reduceSum(
                        getTF().math.mul(byLabelAuc, this.getLabelWeights()),
                        allAxes(getTF(), byLabelAuc)),
                getTF().reduceSum(getLabelWeights(), allAxes(getTF(), getLabelWeights())));
      }
    } else {
      return getTF().reduceSum(prAucIncrement, allAxes(getTF(), prAucIncrement));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> result() {

    if (this.getCurve() == AUCCurve.PR
        && this.getSummationMethod() == AUCSummationMethod.INTERPOLATION) {
      return this.interpolatePRAuc();
    }
    Ops tf = getTF();
    Operand<T> x;
    Operand<T> y;
    Operand<T> recall =
        getTF().math.divNoNan(truePositives, tf.math.add(truePositives, falseNegatives));

    if (this.getCurve() == AUCCurve.ROC) {
      x = tf.math.divNoNan(falsePositives, tf.math.add(falsePositives, trueNegatives));
      y = recall;
    } else { // AUCCurve.PR
      y = tf.math.divNoNan(truePositives, tf.math.add(truePositives, falsePositives));
      x = recall;
    }

    // Find the rectangle heights based on `summationMethod`.
    // y[:self.numThresholds - 1]
    Operand<T> ySlice1 =
        tf.slice(
            y, tf.constant(new int[] {0}), tf.constant(new int[] {this.getNumThresholds() - 1}));
    // y[1:]
    Operand<T> ySlice2 = tf.slice(y, tf.constant(new int[] {1}), tf.constant(new int[] {-1}));

    Operand<T> heights = null;
    switch (this.getSummationMethod()) {
      case INTERPOLATION:
        heights =
            tf.math.div(tf.math.add(ySlice1, ySlice2), tf.dtypes.cast(tf.constant(2), y.type()));
        break;
      case MINORING:
        heights = tf.math.minimum(ySlice1, ySlice2);
        break;
      case MAJORING:
        heights = tf.math.maximum(ySlice1, ySlice2);
        break;
    }

    if (this.isMultiLabel()) {
      Operand<T> riemannTerms =
          tf.math.mul(
              tf.math.sub(
                  tf.slice(
                      x,
                      tf.constant(new int[] {0}),
                      tf.constant(new int[] {this.getNumThresholds() - 1})),
                  tf.slice(x, tf.constant(new int[] {1}), tf.constant(new int[] {-1}))),
              heights);
      Operand<T> byLabelAuc = tf.reduceSum(riemannTerms, tf.constant(0));

      if (this.getLabelWeights() == null) {
        return MetricsHelper.mean(tf, byLabelAuc);
      } else {
        return tf.math.divNoNan(
            tf.reduceSum(
                tf.math.mul(byLabelAuc, getLabelWeights()), allAxes(tf, getLabelWeights())),
            tf.reduceSum(getLabelWeights(), allAxes(tf, getLabelWeights())));
      }

    } else {
      Operand<T> slice1 =
          tf.slice(
              x, tf.constant(new int[] {0}), tf.constant(new int[] {this.getNumThresholds() - 1}));
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
