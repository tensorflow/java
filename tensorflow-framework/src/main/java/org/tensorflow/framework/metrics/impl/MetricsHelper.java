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
package org.tensorflow.framework.metrics.impl;

import static org.tensorflow.framework.losses.impl.LossesHelper.allAxes;
import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.framework.metrics.exceptions.NotBroadcastableException;
import org.tensorflow.framework.utils.SparseTensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.OneHot;
import org.tensorflow.op.core.Rank;
import org.tensorflow.op.core.Squeeze;
import org.tensorflow.op.core.Stack;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.math.Mean;
import org.tensorflow.op.nn.TopK;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TIntegral;
import org.tensorflow.types.family.TNumber;

/**
 * These are helper methods for Metrics and will be module private when Java modularity is applied
 * to TensorFlow Java. These methods should not be used outside of the metrics packages.
 */
public class MetricsHelper {
  public static final float NEG_INF = -1e10f;
  private static final String ASSERT_BROADCAST_ERROR_PREFIX =
      "weights can not be broadcast to values.";

  /**
   * Asserts that the {@code sampleWeights} can be broadcast to the same shape as {@code values }
   *
   * <p>In losses and metrics, limited weight broadcasting is supported. Weights must be either
   * scalar, or the same rank as the target values, with each dimension either 1, or the same as the
   * corresponding values dimension.
   *
   * @param tf the TensorFlow Ops
   * @param sampleWeights the sample weights.
   * @param values the values to which weights are applied.
   * @return {@code Operation} with control dependencies to ensure {@code sampleWeight} can be
   *     broadcast to {@code values}
   * @param <T> the type of Operand
   * @throws NotBroadcastableException If static checks determine {@code sampleWeights} has an
   *     incorrect shape that prohibit broadcasting to {@code values}
   */
  @SuppressWarnings("unchecked")
  public static <T extends TNumber> Op assertBroadcastable(
      Ops tf, Operand<T> sampleWeights, Operand<T> values) {

    // try static check for exact match

    Shape weightsShapeStatic = sampleWeights.shape();
    int weightsRankStatic = weightsShapeStatic.numDimensions();

    Shape valuesShapeStatic = values.shape();
    int valuesRankStatic = valuesShapeStatic.numDimensions();

    // if (weightsRankStatic != Shape.UNKNOWN_SIZE && valuesRankStatic != Shape.UNKNOWN_SIZE) {
    if (!weightsShapeStatic.isUnknown()
        && !valuesShapeStatic.isUnknown()
        && !weightsShapeStatic.hasUnknownDimension()
        && !valuesShapeStatic.hasUnknownDimension()) {
      if (weightsRankStatic == 0) {
        return tf.withSubScope("staticScalarCheckSuccess")
            .withControlDependencies(java.util.Collections.EMPTY_LIST)
            .noOp();
      }
      if (weightsRankStatic != valuesRankStatic) {
        throw new NotBroadcastableException(
            String.format(
                "%s values.rank=%d. weights.rank=%d.  values.shape=%s. weights.shape=%s.",
                ASSERT_BROADCAST_ERROR_PREFIX,
                valuesRankStatic,
                weightsRankStatic,
                valuesShapeStatic,
                weightsShapeStatic));
      }

      for (int i = 0; i < valuesRankStatic; i++) {
        if (valuesShapeStatic.get(i) != weightsShapeStatic.get(i)
            && weightsShapeStatic.get(i) != 1) {
          throw new NotBroadcastableException(
              String.format(
                  "%s Mismatch at dim %d. values.shape=%s weights.shape=%s.",
                  ASSERT_BROADCAST_ERROR_PREFIX, i, valuesShapeStatic, weightsShapeStatic));
        }
      }
      return tf.withSubScope("staticDimsCheckSuccess")
          .withControlDependencies(Collections.EMPTY_LIST)
          .noOp();
    }
    // Dynamic checks.
    Operand<TInt32> weightsShape = tf.shape(sampleWeights);
    Operand<TInt32> weightsRank = tf.rank(sampleWeights);
    Operand<TInt32> valuesShape = tf.shape(values);
    Operand<TInt32> valuesRank = tf.rank(values);

    Operand<TBool> isScalar = tf.math.equal(weightsRank, tf.constant(0));
    List<Operand<?>> data =
        Arrays.asList(
            tf.constant(ASSERT_BROADCAST_ERROR_PREFIX),
            tf.constant("weights.shape="),
            weightsShape,
            tf.constant("values.shape="),
            valuesShape,
            tf.constant("isScalar="),
            isScalar);

    // hack to work around the non-lazy select for isValidShape, otherwise validNonscalar fails on a
    // scalar weight. If select was lazy, that branch wouldn't get executed when iScalar is true.
    Operand<T> reshapedWeights =
        tf.select(isScalar, tf.math.mul(sampleWeights, tf.onesLike(values)), sampleWeights);
    weightsShape = tf.shape(reshapedWeights);
    weightsRank = tf.rank(reshapedWeights);

    Operand<TBool> validNonscalar =
        canBroadcastNonscalarShapes(tf, weightsRank, weightsShape, valuesRank, valuesShape);

    Operand<TBool> isValidShape = tf.select(isScalar, isScalar, validNonscalar);

    return tf.withSubScope("broadcastWeights-dynamic").assertThat(isValidShape, data);
  }

  /**
   * Gets an operand that tests if the shapes have the same rank and valid dimensions.
   *
   * @param tf the TensorFlow Ops
   * @param weightsRank the operand for the rank of the sample weights
   * @param weightsShape the operand for the shape of the sample weights
   * @param valuesRank the operand for the rank of the sample weights
   * @param valuesShape the operand for the shape of the sample weights
   * @param <T> the data type for the operands
   * @return a boolean operand to determine if the Shape is scalar or not.
   */
  private static <T extends TNumber> Operand<TBool> canBroadcastNonscalarShapes(
      Ops tf,
      Operand<T> weightsRank,
      Operand<T> weightsShape,
      Operand<T> valuesRank,
      Operand<T> valuesShape) {
    tf = tf.withSubScope("canBroadcastNonscalarShapes");
    Operand<TBool> isSameRank = tf.math.equal(valuesRank, weightsRank);
    return tf.select(isSameRank, canBroadcastDims(tf, weightsShape, valuesShape), isSameRank);
  }

  /**
   * Gets an operand that tests if the shapes have valid dimensions or not.
   *
   * @param tf the TensorFlow Ops
   * @param weightsShape the operand for the shape of the sample weights
   * @param valuesShape the operand for the shape of the values
   * @param <T> the data type for the operands
   * @return a boolean operand to determine if the shapes have valid dimensions or not.
   */
  private static <T extends TNumber> Operand<TBool> canBroadcastDims(
      Ops tf, Operand<T> weightsShape, Operand<T> valuesShape) {
    tf = tf.withSubScope("canBroadcastDims");
    Operand<T> valuesShape2d = tf.expandDims(valuesShape, tf.constant(-1));
    Operand<T> validDims =
        tf.concat(Arrays.asList(valuesShape2d, tf.onesLike(valuesShape2d)), tf.constant(1));
    Operand<T> weightsShape2D = tf.expandDims(weightsShape, tf.constant(-1));

    Operand<T> diffResult = SetsOps.difference(tf, weightsShape2D, validDims);
    Operand<TInt32> numInvalidDims = tf.size(diffResult);
    return tf.math.equal(tf.constant(0), numInvalidDims);
  }

  /**
   * Broadcast {@code weights} to the same shape as {@code values}.
   *
   * @param tf the TensorFlow ops
   * @param weights Operand whose shape is broadcastable to {@code values}.
   * @param values Operand of any shape
   * @param <T> the type of Operands
   * @return {@code weights} broadcast to {@code values} shape
   */
  public static <T extends TNumber> Operand<T> broadcastWeights(
      Ops tf, Operand<T> weights, Operand<T> values) {

    Shape weightsShape = weights.shape();
    Shape valuesShape = values.shape();

    if (!weightsShape.hasUnknownDimension()
        && !valuesShape.hasUnknownDimension()
        && weightsShape.isCompatibleWith(valuesShape)) {
      return weights;
    }

    Ops ctf =
        tf.withSubScope("broadcastWeights")
            .withControlDependencies(
                Collections.singletonList(assertBroadcastable(tf, weights, tf.onesLike(values))));
    return ctf.math.mul(weights, tf.onesLike(values));
  }

  /**
   * Checks that all the Symbolic Shapes are consistent.
   *
   * @param tf the TensorFlow Ops
   * @param symbols the list of Symbolic Shapes
   * @param message the error message if the shapes are not consistent.
   * @return a list of Operands to check the consistency of the symbolic shapes ready to add to a
   *     control dependency.
   */
  public static List<Op> assertShapes(
      Ops tf, List<SymbolicShape<? extends TNumber>> symbols, String message) {
    List<Op> updateOperations = new ArrayList<>();
    // check that the symbolic shape rank matches the operands rank.
    symbols.forEach(
        symbol -> {
          Operand<? extends TNumber> operand = symbol.getOperand();
          int rank = symbol.rank();
          Rank tfRank = tf.rank(operand);
          Op assertion =
              tf.withSubScope("assertShapes-1")
                  .assertThat(
                      tf.math.equal(tfRank, tf.constant(rank)),
                      Collections.singletonList(tf.constant(message)));
          updateOperations.add(assertion);
        });

    Map<String, Operand<TInt64>> dict = new HashMap<>();

    // check that each operand's dimension size equals the corresponding symbolic shape's dimensions
    // size
    symbols.forEach(
        symbol -> {
          AtomicLong ll = new AtomicLong();
          symbol
              .getSymbols()
              .forEach(
                  s -> {
                    Operand<TInt64> size = dict.get(s);
                    if (size == null) {
                      // save size for later checks
                      size =
                          tf.shape.size(symbol.getOperand(), tf.constant(ll.get()), TInt64.class);
                      dict.put(s, size);
                    }
                    Op assertion =
                        tf.withSubScope("assertShapes-2")
                            .assertThat(
                                tf.math.equal(
                                    tf.shape.size(
                                        symbol.getOperand(),
                                        tf.constant(ll.getAndIncrement()),
                                        TInt64.class),
                                    size),
                                Collections.singletonList(tf.constant(message)));
                    updateOperations.add(assertion);
                  });
        });

    return updateOperations;
  }

  /**
   * Returns an op to update the given confusion matrix variables.
   *
   * <p>For every pair of values in {@code labels} and {@code predictions}:
   *
   * <pre>
   * TRUE_POSITIVES:  {@code labels} == true and {@code predictions} &gt; thresholds
   * FALSE_POSITIVES: {@code labels} == true and {@code predictions} &lt;= thresholds
   * TRUE_NEGATIVES:  {@code labels} == false and {@code predictions} &lt;= thresholds
   * FALSE_NEGATIVE:  {@code labels} == false and {@code predictions} &gt; thresholds
   * </pre>
   *
   * <p>The results will be weighted and added together. When multiple thresholds are provided, we
   * will repeat the same for every threshold.
   *
   * <p>For estimation of these metrics over a stream of data, the function creates an `update_op`
   * operation that updates the given variables.
   *
   * <p>{@code labels}, {@code predictions}, and {@code sampleWeight} tensors are aligned by {@link
   * LossesHelper#removeSqueezableDimensions(Ops, Operand, Operand)}. {@code sampleWeight} is then
   * broadcast to the shape of {@code predictions}.
   *
   * @param tf the TensorFlow Ops
   * @param variablesToUpdate map with {@link ConfusionMatrixEnum} values as valid keys and
   *     corresponding variables to update as values. If {@code multiLabel}, then the variable
   *     shapes are (T, D), where T is the number of thresholds and D is the number of classes
   *     (after slicing by {@code classIndex}, if provided). If {@code multiLabels}, then the
   *     variable shapes are (T).
   * @param varInitializers map with {@link ConfusionMatrixEnum} values as valid keys and
   *     corresponding initializer Operands to for {@code variablesToUpdate}.
   * @param labels the labels. Will be cast to {@link TBool}. Shape (N, Cx, L1?), where N is the
   *     number of examples, Cx is zero or more class dimensions, and L1 is a potential extra
   *     dimension of size 1 that would be squeezed.
   * @param predictions the predictions shape (N, Cx, P1?)
   * @param thresholds thresholds in the range {@code [0, 1]}, or {@link #NEG_INF} is used when topK
   *     is set
   * @param topK optional, indicates that only the top k predictions should be considered. Applied
   *     before possibly slicing by {@code classIndex}.
   * @param classIndex optional, limits the prediction and labels to the specified class. This is an
   *     integer index into the first dimension of Cx.
   * @param sampleWeight optional {@code Tensor} that is aligned with labels and predictions as
   *     explained above. Use weights of 0 to mask values.
   * @param multiLabel indicates whether multidimensional prediction/labels should be treated as
   *     multilabel responses, or flattened into a single label. When true, the values of {@code
   *     variablesToUpdate} must have a second dimension equal to the number of labels and
   *     predictions per example, and those tensors must not be RaggedTensors.
   * @param labelWeights tensor of non-negative weights for multilabel data. The weights are applied
   *     when calculating TRUE_POSITIVES, FALSE_POSITIVES, TRUE_NEGATIVES, and FALSE_NEGATIVES
   *     without explicit multilabel handling (i.e. when the data is to be flattened). Must have
   *     shape (Dx), which is the same as (Cx) referenced above, except that if {@code classIndex }
   *     is provided, then the final dimension of Dx is 1. These weights will be broadcast across
   *     the 0th dimension (the examples dimension) of {@code predictions}. May be null. Must be
   *     null if {@code multiLabel}.
   * @param <T> the data type for the variables
   * @throws IllegalArgumentException If {@code predictions} and {@code labels} have mismatched
   *     shapes, or if {@code sampleWeight} is not null and its shape doesn't match {@code
   *     predictions}, or if {@code multiLabel && labelWeights != null}..
   * @return an op to update the given confusion matrix variables.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <T extends TNumber> List<Op> updateConfusionMatrixVariables(
      Ops tf,
      Map<ConfusionMatrixEnum, Variable<T>> variablesToUpdate,
      Map<ConfusionMatrixEnum, Assign<T>> varInitializers,
      Operand<T> labels,
      Operand<T> predictions,
      Operand<TFloat32> thresholds,
      Integer topK,
      Integer classIndex,
      Operand<T> sampleWeight,
      boolean multiLabel,
      Operand<T> labelWeights) {
    if (multiLabel && labelWeights != null)
      throw new IllegalArgumentException(
          "labelWeights for multilabel data should be handled outside of updateConfusionMatrixVariables when multiLabel is true.");

    if (variablesToUpdate == null || variablesToUpdate.isEmpty()) {
      return Collections.EMPTY_LIST;
    }

    Operand<T> tLabels = labels;
    Operand<T> tPredictions = predictions;
    Operand<T> tSampleWeight = sampleWeight;

    // We will tile data for threshold comparisons. We want a cross product of thresholds and
    // predictions/labels:
    //   In the multilabel case, we want a data shape of (T, N, D).
    //                                              else (T, ND).
    //   where
    //     T is numThresholds (the size of the 0th dimension of thresholds)
    //     N is the number of examples (the 0th dimension of labels and predictions)
    //     Dx == Cx except that if classIndex != null,
    //                          then the last dimension of Dx is size 1
    //     D is the product of all Dx
    //     ND is N * D

    // size of the 0th dimension of thresholds
    // reshape to scalar for operations later.
    Operand<TInt32> numThresholds =
        tf.reshape(tf.shape.size(thresholds, tf.constant(0)), tf.constant(Shape.scalar()));

    // if multilabel, then (rank(thresholds) == 1)
    //                else true
    Operand<TBool> oneThresh;
    if (multiLabel) {
      oneThresh = tf.math.equal(tf.constant(1), tf.rank(thresholds));
    } else {
      // TODO handle Ragged Tensors????
      // [y_pred,
      //    y_true], _ = ragged_assert_compatible_and_get_flat_values([y_pred, y_true],
      //                                                   sampleWeights)
      oneThresh = tf.constant(true);
    }

    List<Op> controlOps = new ArrayList<>();
    Operand<TInt32> axes = allAxes(tf, tPredictions);
    controlOps.add(
        tf.withSubScope("updateConfusionMatrixVariables-1")
            .assertThat(
                tf.reduceAll(
                    tf.math.greaterEqual(
                        tPredictions, cast(tf, tf.constant(0), tPredictions.type())),
                    axes),
                Collections.singletonList(tf.constant("predictions must be >= 0"))));
    controlOps.add(
        tf.withSubScope("updateConfusionMatrixVariables-2")
            .assertThat(
                tf.reduceAll(
                    tf.math.lessEqual(tPredictions, cast(tf, tf.constant(1), tPredictions.type())),
                    axes),
                Collections.singletonList(tf.constant("predictions must be <= 1"))));

    LossTuple<T> result =
        LossesHelper.squeezeOrExpandDimensions(tf, tLabels, tPredictions, tSampleWeight);
    tPredictions = result.getTarget(); // shape (N, Cx)
    tLabels = result.getLabels(); // shape (N, Cx)
    tSampleWeight = result.getSampleWeights(); // broadcastable to (N, Dx)

    if (!tPredictions.shape().isCompatibleWith(tLabels.shape()))
      throw new IllegalArgumentException(
          String.format(
              "Shapes %s and %s are incompatible)",
              tPredictions.shape().toString(), tLabels.shape().toString()));

    if (topK != null) {
      tPredictions = filterTopK(tf, tPredictions, topK);
    }

    if (classIndex != null) {
      // Slice to new shapes (N, Dx)
      tLabels =
          tf.squeeze(
              tf.gather(tLabels, tf.constant(new int[] {classIndex}), tf.constant(-1)),
              Squeeze.axis(Collections.singletonList(1L)));
      tPredictions =
          tf.squeeze(
              tf.gather(tPredictions, tf.constant(new int[] {classIndex}), tf.constant(-1)),
              Squeeze.axis(Collections.singletonList(1L)));
    }
    org.tensorflow.op.core.Shape<TInt32> predShape = tf.shape(tPredictions);

    Operand<TInt32> numExamples =
        tf.reshape(tf.shape.size(tPredictions, tf.constant(0)), tf.constant(Shape.scalar()));

    // number of labels (and predictions) per example (after possibly slicing by classIndex)
    // In the notation we are using for comments, this is D.
    Operand<TInt32> numLabels =
        tf.select(
            tf.math.equal(tf.shape.numDimensions(predShape), tf.constant(1)),
            tf.constant(1),
            tf.reduceProd(
                // take all but the first dimension
                tf.shape.takeLast(
                    predShape, tf.math.sub(tf.shape.numDimensions(predShape), tf.constant(1))),
                tf.constant(0)));

    // threshLabelTile == numLabels except in one case:
    //    if multilabel and rank(thresholds) != 1, then threshLabelTile is 1
    Operand<TInt32> threshLabelTile = tf.select(oneThresh, numLabels, tf.constant(1));

    // if multilabel, then shape (1, N, Dx)
    // else shape (1, ND),
    Operand<T> predictionsExtraDim;
    Operand<TBool> labelsExtraDim;

    if (multiLabel) {
      predictionsExtraDim = tf.expandDims(tPredictions, tf.constant(0));
      labelsExtraDim = tf.expandDims(cast(tf, tLabels, TBool.class), tf.constant(0));
    } else {
      predictionsExtraDim = tf.reshape(tPredictions, tf.constant(Shape.of(1, -1)));
      labelsExtraDim = tf.reshape(cast(tf, tLabels, TBool.class), tf.constant(Shape.of(1, -1)));
    }

    // the shape of each thresholds tile
    // if multilabel, then [T, 1, -1]
    //                else [T, -1]
    List<Operand<TInt32>> threshPretileShape;

    // the tiling multiples for thresholds
    // We want to repeat the thresholds for each data position.
    // if multilabel, then [1, N, threshLabelTile]. (threshLabelTile is typically numLabels)
    //                else [1, ND]
    List<Operand<TInt32>> threshTiles;

    // tiling multiples for predictionsExtraDim and labelsExtraDim
    // We want to repeat the predictions and labels for each threshold.
    // If multilabel, then [T, 1, 1]
    //                else [T, 1]
    List<Operand<TInt32>> dataTiles;

    if (multiLabel) {
      threshPretileShape = Arrays.asList(numThresholds, tf.constant(1), tf.constant(-1));
      threshTiles = Arrays.asList(tf.constant(1), numExamples, threshLabelTile);
      dataTiles = Arrays.asList(numThresholds, tf.constant(1), tf.constant(1));
    } else {
      threshPretileShape =
          Arrays.asList(tf.reshape(numThresholds, tf.constant(Shape.scalar())), tf.constant(-1));
      Operand<TInt32> mul = tf.math.mul(numExamples, numLabels);
      threshTiles = Arrays.asList(tf.constant(1), mul);
      dataTiles = Arrays.asList(numThresholds, tf.constant(1));
    }

    // if multilabel, then shape (T, 1, T*)
    //                else shape (T, T*)
    // where T* is the product of all threshold dimension sizes beyond 0
    Operand<T> thresholdsReshaped =
        tf.reshape(cast(tf, thresholds, predictions.type()), tf.stack(threshPretileShape));

    Operand<TInt32> threshTilesShape = tf.stack(threshTiles);

    // if multilabel, then
    //     if thresholds has rank > 1, then shape (T, N, T*)
    //                                 else shape (T, N, D)
    // else shape (T, ND)
    Operand<T> threshTiled = tf.tile(thresholdsReshaped, threshTilesShape);

    Operand<TInt32> dataTilesShape = tf.stack(dataTiles);

    // if multilabel, then shape (T, N, D)
    //                      else (T, ND)
    Operand<T> predsTiled = tf.tile(predictionsExtraDim, dataTilesShape);

    // Compare predictions and threshold.
    Operand<TBool> predIsPos = tf.math.greater(predsTiled, threshTiled);
    // Tile labels by number of thresholds
    Operand<TBool> labelIsPos = tf.tile(labelsExtraDim, tf.stack(dataTiles));
    Operand<T> weightsTiled;
    if (tSampleWeight != null) {
      tSampleWeight = tf.broadcastTo(tSampleWeight, tf.shape(tPredictions));
      // if multilabel, then
      //     reshape tSampleWeight to (1, N, threshLabelTile)
      //     tile the result into shape (T, N, threshLabelTile)
      //     where threshLabelTile is typically D
      // else
      //     reshape tSampleWeight to (1, ND)
      //     tile the result into shape (T, ND)
      weightsTiled = tf.tile(tf.reshape(tSampleWeight, threshTilesShape), dataTilesShape);
    } else {
      weightsTiled = null;
    }

    if (labelWeights != null) {
      // Change shape to (1, Dx).
      Operand<T> lLabelWeights = tf.expandDims(tf.identity(labelWeights), tf.constant(0));

      // Broadcast to shape (N, Dx).
      lLabelWeights = tf.broadcastTo(lLabelWeights, tPredictions);

      // If multilabel: shape (T, N, D)
      //          else: shape (T, ND)
      Operand<T> labelWeightsTiled =
          tf.tile(tf.reshape(lLabelWeights, tf.stack(threshTiles)), tf.stack(dataTiles));

      if (weightsTiled == null) {
        weightsTiled = labelWeightsTiled;
      } else {
        weightsTiled = tf.math.mul(weightsTiled, labelWeightsTiled);
      }
    }

    Map<ConfusionMatrixEnum, Operand[]> loopVars = new HashMap<>();
    loopVars.put(ConfusionMatrixEnum.TRUE_POSITIVES, new Operand[] {labelIsPos, predIsPos});
    Variable<T> updateTN = variablesToUpdate.get(ConfusionMatrixEnum.TRUE_NEGATIVES);
    Variable<T> updateFP = variablesToUpdate.get(ConfusionMatrixEnum.FALSE_POSITIVES);
    Variable<T> updateFN = variablesToUpdate.get(ConfusionMatrixEnum.FALSE_NEGATIVES);

    Operand<TBool> predIsNeg = null;
    Operand<TBool> labelIsNeg;
    if (updateFN != null || updateTN != null) {
      predIsNeg = tf.math.logicalNot(predIsPos);
      loopVars.put(ConfusionMatrixEnum.FALSE_NEGATIVES, new Operand[] {labelIsPos, predIsNeg});
    }

    if (updateFP != null || updateTN != null) {
      labelIsNeg = tf.math.logicalNot(labelIsPos);
      loopVars.put(ConfusionMatrixEnum.FALSE_POSITIVES, new Operand[] {labelIsNeg, predIsPos});
      if (updateTN != null) {
        loopVars.put(ConfusionMatrixEnum.TRUE_NEGATIVES, new Operand[] {labelIsNeg, predIsNeg});
      }
    }

    final Operand<T> weightsTiledF = weightsTiled;
    loopVars
        .keySet()
        .forEach(
            (c) -> {
              if (variablesToUpdate.containsKey(c)) {
                Operand[] op = loopVars.get(c);
                // op[0] = label, op[1] == prediction
                controlOps.add(
                    weightedAssignAdd(
                        tf,
                        op[0],
                        op[1],
                        weightsTiledF,
                        variablesToUpdate.get(c),
                        varInitializers.get(c)));
              }
            });

    return controlOps;
  }

  /**
   * Creates an Operand that adds the values by taking the logical and of labels and predictions to
   * the specified confusion matrix variable.
   *
   * @param tf The TensorFlow Ops
   * @param labels the labels
   * @param predictions the predictions
   * @param weights the weights applied to the logical and result, may be null
   * @param variable the variable to update
   * @param initializer the variable initializer to be applied to the variable, may be null.
   * @param <T> the data type for the variable.
   * @return an Operand that updates the variable.
   */
  private static <T extends TNumber> Operand<T> weightedAssignAdd(
      Ops tf,
      Operand<TBool> labels,
      Operand<TBool> predictions,
      Operand<T> weights,
      Variable<T> variable,
      Assign<T> initializer) {
    Class<T> type = variable.type();
    Operand<T> labelAndPred = cast(tf, tf.math.logicalAnd(labels, predictions), type);

    if (weights != null) {
      labelAndPred = tf.math.mul(labelAndPred, weights);
    }
    // if multilabel:
    //   sum across examples, leaving shape (T, D)
    // else:
    //   sum across ND, leaving shape (T)
    Operand<T> valueSum = tf.reduceSum(labelAndPred, tf.constant(1));
    Operand<T> assignAdd;
    if (initializer != null) {
      Ops tfc =
          tf.withSubScope("weightedAssignAdd")
              .withControlDependencies(Collections.singletonList(initializer));
      assignAdd = tfc.assignAdd(variable, valueSum);
    } else {
      assignAdd = tf.assignAdd(variable, valueSum);
    }
    return assignAdd;
  }

  /**
   * Filters top-k values in the last dim of x and set the rest to NEG_INF.
   *
   * <p>Used for computing top-k prediction values in dense labels (which has the same shape as
   * predictions) for recall and precision top-k metrics.
   *
   * @param tf The TensorFlow Ops
   * @param x the tensor with any dimensions to filter
   * @param topK the number of values to keep.
   * @param <T> the data type for x and the return value.
   * @return the topK prediction values.
   */
  private static <T extends TNumber> Operand<T> filterTopK(Ops tf, Operand<T> x, int topK) {
    Class<T> type = x.type();
    Shape xShape = x.shape();
    // top has the same rank as x; the last dimension becomes indices of the topK features.
    TopK<T> top = tf.nn.topK(x, tf.constant(topK), TopK.sorted(false));
    // oneHot has an additional dimension: the one-hot representation of each topK index.
    OneHot<TInt32> oneHot =
        tf.oneHot(
            top.indices(),
            cast(tf, tf.constant(xShape.size(xShape.numDimensions() - 1)), TInt32.class),
            tf.constant(1),
            tf.constant(0),
            OneHot.axis(-1L));
    // Sum the one-hot representations along the last dimension of x.
    Operand<T> topKMask = cast(tf, tf.reduceSum(oneHot, tf.constant(-2)), type);

    // x * top_k_mask + NEG_INF * (1 - top_k_mask)
    Operand<T> add1 = tf.math.mul(x, topKMask);
    Operand<T> add2 =
        tf.math.mul(
            cast(tf, tf.constant(NEG_INF), type),
            tf.math.sub(cast(tf, tf.constant(1), type), topKMask));
    return tf.math.add(add1, add2);
  }

  // alias for mean

  /**
   * Calculate the mean of the operand, along all axes and {@code keepDims} is {@code false }
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand used to calculate the mean
   * @param <T> the type of the Operand.
   * @return the mean of the operand
   */
  public static <T extends TNumber> Operand<T> mean(Ops tf, Operand<T> x) {
    return mean(tf, x, null, false);
  }

  /**
   * Calculate the mean of the operand, alongside the specified axis with {@code keepDims} is {@code
   * false}
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand used to calculate the mean
   * @param axes Axes to compute the mean.
   * @param <T> the type of the Operand.
   * @return the mean of the operand, along the specified axes.
   */
  public static <T extends TNumber> Operand<T> mean(
      Ops tf, Operand<T> x, Operand<? extends TIntegral> axes) {
    return mean(tf, x, axes, false);
  }

  /**
   * Calculates the mean of the operand, along all axes.
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand used to calculate the mean
   * @param keepDims Indicates whether to keep the dimensions or not. If {@code keepdims} is {@code
   *     false}, the rank of the tensor is reduced by 1 for each entry in {@code axes }. If {@code
   *     keepdims} is {@code true}, the reduced dimensions are retained with length 1.
   * @param <T> the type of the operand
   * @return the mean of elements of {@code x}.
   */
  public static <T extends TNumber> Operand<T> mean(Ops tf, Operand<T> x, boolean keepDims) {
    return mean(tf, x, null, keepDims);
  }

  /**
   * Calculates the mean of the operand, alongside the specified axes.
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand used to calculate the mean
   * @param axes Axes to compute the mean.
   * @param keepDims Indicates whether to keep the dimensions or not. If {@code keepdims} is {@code
   *     false}, the rank of the tensor is reduced by 1 for each entry in {@code axes }. If {@code
   *     keepdims} is {@code true}, the reduced dimensions are retained with length 1.
   * @param <T> the data type of the Operand
   * @return the mean of elements of {@code x}.
   */
  public static <T extends TNumber> Operand<T> mean(
      Ops tf, Operand<T> x, Operand<? extends TIntegral> axes, boolean keepDims) {
    if (axes == null) {
      axes = allAxes(tf, x);
    }
    return tf.math.mean(x, axes, Mean.keepDims(keepDims));
  }

  public static <T extends TNumber, V extends TNumber>
      LossTuple<T> raggedAssertCompatibleAndGetFlatValues(
          Ops tf, Operand<V> labels, Operand<T> predictions) {
    // TODO handle ragged Tensors
    Operand<T> tLabels = cast(tf, labels, predictions.type());
    return new LossTuple<>(tLabels, predictions);
  }

  /**
   * Computes the confusion matrix from predictions and labels.
   *
   * <p>The matrix columns represent the prediction labels and the rows represent the real labels.
   * The confusion matrix is always a 2-D array of shape {@code [n, n]}, where {@code n} is the
   * number of valid labels for a given classification task. Both prediction and labels must be 1-D
   * arrays of the same shape in order for this function to work.
   *
   * <p>If {@code numClasses} is null, then {@code numClasses} will be set to one plus the maximum
   * value in either predictions or labels. Class labels are expected to start at 0. For example, if
   * {@code numClasses}` is 3, then the possible labels would be {@code [0, 1, 2]}.
   *
   * <p>If {@code weights} is not null, then each prediction contributes its corresponding weight to
   * the total value of the confusion matrix cell.
   *
   * <p>For example:
   *
   * <pre>{@code
   * confusion_matrix([1, 2, 4], [2, 2, 4]) ==>
   *      [[0 0 0 0 0]
   *       [0 0 1 0 0]
   *       [0 0 1 0 0]
   *       [0 0 0 0 0]
   *       [0 0 0 0 1]]
   * }</pre>
   *
   * Note that the possible labels are assumed to be {@code [0, 1, 2, 3,4]}, resulting in a 5x5
   * confusion matrix.
   *
   * @param tf the TensorFlow Ops
   * @param labels 1-D {@code Operand} of real labels for the classification task.
   * @param predictions 1-D {@code Operand} of predictions for a given classification.
   * @param numClasses The possible number of labels the classification task can have. If this value
   *     is not provided, it will be calculated using both predictions and labels array.
   * @param weights optional weights to be applied to the confusion matrix
   * @param type Data type of the confusion matrix.
   * @param <T> the type of Operands
   * @return A {@code Operand} of type {@code type} with shape {@code [n, n]} representing the
   *     confusion matrix, where {@code n} is the number of possible labels in the classification
   *     task.
   * @throws IllegalArgumentException If both {@code predictions} and {@code labels} do not have
   *     compatible shapes, or if {@code weights} is not{@code null} and its shape is not compatible
   *     with {@code predictions}.
   */
  // TODO should this be moved to FramnworkOps under math.
  public static <T extends TNumber> Operand<T> confusionMatrix(
      Ops tf,
      Operand<T> labels,
      Operand<T> predictions,
      Operand<TInt64> numClasses,
      Operand<T> weights,
      Class<T> type) {
    if (!predictions.shape().isCompatibleWith(labels.shape()))
      throw new IllegalArgumentException(
          String.format(
              "Prediction shape %s is not compatible with labels shape %s",
              predictions.shape().toString(), labels.shape().toString()));
    tf = tf.withSubScope("confusionMatrix");
    LossTuple<T> ops = LossesHelper.squeezeOrExpandDimensions(tf, predictions, labels, null);
    Operand<TInt64> tPredictions = cast(tf, ops.getTarget(), TInt64.class);
    Operand<TInt64> tLabels = cast(tf, ops.getLabels(), TInt64.class);

    List<Op> labelControls = new ArrayList<>();
    List<Op> predictionControls = new ArrayList<>();

    labelControls.add(
        tf.assertThat(
            tf.reduceAny(tf.math.greaterEqual(tLabels, tf.constant(0L)), allAxes(tf, tLabels)),
            Collections.singletonList(tf.constant("`labels` contains negative values"))));

    predictionControls.add(
        tf.assertThat(
            tf.reduceAny(
                tf.math.greaterEqual(tPredictions, tf.constant(0L)), allAxes(tf, tPredictions)),
            Collections.singletonList(tf.constant("`predictions` contains negative values"))));
    if (numClasses == null) {
      numClasses =
          tf.math.maximum(
              tf.reduceMax(tPredictions, allAxes(tf, tPredictions)),
              tf.reduceMax(tLabels, allAxes(tf, tLabels)));
    } else {
      labelControls.add(
          tf.assertThat(
              tf.reduceAny(tf.math.less(tLabels, numClasses), allAxes(tf, tLabels)),
              Collections.singletonList(tf.constant("``labels` out of bounds"))));
      predictionControls.add(
          tf.assertThat(
              tf.reduceAny(tf.math.less(tPredictions, numClasses), allAxes(tf, tPredictions)),
              Collections.singletonList(tf.constant("``predictions` out of bounds"))));
    }

    if (weights != null) {
      if (!tPredictions.shape().isCompatibleWith(weights.shape())) {
        throw new IllegalArgumentException(
            String.format(
                "Prediction shape %s is not compatible with weights shape %s",
                tPredictions.shape().toString(), weights.shape().toString()));
      }
    }

    Ops tfc = tf.withSubScope("confusionMatrixLabels").withControlDependencies(labelControls);
    tLabels = tfc.identity(tLabels);

    tfc = tf.withSubScope("confusionMatrixPredictions").withControlDependencies(predictionControls);
    tPredictions = tfc.identity(tPredictions);

    Operand<TInt64> shape = tf.stack(Arrays.asList(numClasses, numClasses));
    Operand<TInt64> indices = tf.stack(Arrays.asList(tLabels, tPredictions), Stack.axis(1L));
    Operand<T> values =
        weights == null ? cast(tf, tf.onesLike(tPredictions), type) : cast(tf, weights, type);
    SparseTensor<T> cmSparse = new SparseTensor<>(indices, values, shape);
    Operand<T> zeroMatrix = tf.zeros(shape, type);

    return tf.sparse.sparseTensorDenseAdd(
        cmSparse.getIndices(), cmSparse.getValues(), cmSparse.getDenseShape(), zeroMatrix);
  }

  /**
   * Calculate the mean of the operand, along all axes and {@code keepDims} is {@code false }
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand used to calculate the mean
   * @return the mean of the operand containing floating point numbers
   */
  public static Operand<TFloat64> booleanMean(Ops tf, Operand<TBool> x) {
    return booleanMean(tf, x, null, false);
  }

  /**
   * Calculate the mean of the operand, alongside the specified axis with {@code keepDims} is {@code
   * false}
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand used to calculate the mean
   * @param axes Axes to compute the mean.
   * @return the mean of the operand, along the specified axes, containing floating point numbers
   */
  public static Operand<TFloat64> booleanMean(
      Ops tf, Operand<TBool> x, Operand<? extends TIntegral> axes) {
    return booleanMean(tf, x, axes, false);
  }

  /**
   * Calculates the mean of the boolean operand, alongside all axes.
   *
   * @param tf the TensorFlow Ops
   * @param x the boolean Operand used to calculate the mean
   * @param keepDims Indicates whether to keep the dimensions or not. If {@code keepdims} is {@code
   *     false}, the rank of the tensor is reduced by 1 for each entry in {@code axes }. If {@code
   *     keepdims} is {@code true}, the reduced dimensions are retained with length 1.
   * @return the mean of elements of {@code x} containing floating point numbers
   */
  public static Operand<TFloat64> booleanMean(Ops tf, Operand<TBool> x, boolean keepDims) {
    return booleanMean(tf, x, null, keepDims);
  }

  /**
   * Calculates the mean of the boolean operand, alongside the specified axes.
   *
   * @param tf the TensorFlow Ops
   * @param x the boolean Operand used to calculate the mean
   * @param axes Axes to compute the mean.
   * @param keepDims Indicates whether to keep the dimensions or not. If {@code keepdims} is {@code
   *     false}, the rank of the tensor is reduced by 1 for each entry in {@code axes }. If {@code
   *     keepdims} is {@code true}, the reduced dimensions are retained with length 1.
   * @return the mean of elements of {@code x} containing floating point numbers
   */
  public static Operand<TFloat64> booleanMean(
      Ops tf, Operand<TBool> x, Operand<? extends TIntegral> axes, boolean keepDims) {
    Operand<TFloat64> xf = cast(tf, x, TFloat64.class);
    return mean(tf, xf, axes, keepDims);
  }
}
