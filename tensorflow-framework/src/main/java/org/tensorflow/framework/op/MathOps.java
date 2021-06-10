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
package org.tensorflow.framework.op;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.core.AssertThat;
import org.tensorflow.op.core.Concat;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Gather;
import org.tensorflow.op.core.Identity;
import org.tensorflow.op.core.OnesLike;
import org.tensorflow.op.core.Range;
import org.tensorflow.op.core.Rank;
import org.tensorflow.op.core.ReduceAll;
import org.tensorflow.op.core.ReduceMax;
import org.tensorflow.op.core.ReduceProd;
import org.tensorflow.op.core.ReduceSum;
import org.tensorflow.op.core.Reshape;
import org.tensorflow.op.core.ScatterNd;
import org.tensorflow.op.core.Select;
import org.tensorflow.op.core.SetDiff1d;
import org.tensorflow.op.core.Slice;
import org.tensorflow.op.core.Squeeze;
import org.tensorflow.op.core.Stack;
import org.tensorflow.op.core.StopGradient;
import org.tensorflow.op.core.ZerosLike;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.linalg.Transpose;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Exp;
import org.tensorflow.op.math.GreaterEqual;
import org.tensorflow.op.math.IsFinite;
import org.tensorflow.op.math.Less;
import org.tensorflow.op.math.Log;
import org.tensorflow.op.math.Maximum;
import org.tensorflow.op.math.Mul;
import org.tensorflow.op.math.Rsqrt;
import org.tensorflow.op.math.Square;
import org.tensorflow.op.math.Sub;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

public class MathOps {
  private final Scope scope;

  private final FrameworkOps frameworkOps;

  /**
   * Creates Framework {@code nn} Operations
   *
   * @param frameworkOps the TensorFLow framework Ops
   */
  MathOps(FrameworkOps frameworkOps) {
    this.scope = frameworkOps.scope();
    this.frameworkOps = frameworkOps;
  }

  /**
   * Normalizes along dimension axis using an L2 norm.
   *
   * @param x the input
   * @param axis Dimension along which to normalize.
   * @param <T> the data type for the input and the result
   * @return the normalized values based on L2 norm
   */
  public <T extends TNumber> Operand<T> l2Normalize(Operand<T> x, int[] axis) {
    Operand<T> squareSum =
        ReduceSum.create(
            scope,
            Square.create(scope, x),
            Constant.vectorOf(scope, axis),
            ReduceSum.keepDims(Boolean.TRUE));
    Operand<T> invNorm =
        Rsqrt.create(
            scope,
            Maximum.create(
                scope, squareSum, Cast.create(scope, Constant.scalarOf(scope, 1e-12F), x.type())));
    return Mul.create(scope, x, invNorm);
  }

  /**
   * Computes the confusion matrix from predictions and labels.
   *
   * <p>The matrix columns represent the prediction labels and the rows represent the real labels.
   * The confusion matrix is always a 2-D array of shape {@code [n,n]}, where {@code n} is the
   * number of valid labels for a given classification task. Both prediction and labels must be 1-D
   * arrays of the same shape in order for this function to work.
   *
   * <p>If {@code numClasses} is null, then {@code numClasses} will be set to one plus the maximum
   * value in either predictions or labels. Class labels are expected to start at 0. For example, if
   * {@code numClasses} is 3, then the possible labels would be {@code [0, 1, 2]}.
   *
   * <p>If {@code weights} is not null, then each prediction contributes its corresponding weight to
   * the total value of the confusion matrix cell.
   *
   * <p>For example:
   *
   * <pre>{@code
   * fops.math.confusion_matrix(tf.constant(new int[] {1, 2, 4}), tf.constant(new int[] {2, 2, 4})) ==>
   *     [[0 0 0 0 0]
   *      [0 0 1 0 0]
   *      [0 0 1 0 0]
   *      [0 0 0 0 0]
   *      [0 0 0 0 1]]
   * }</pre>
   *
   * <p>Note that the possible labels are assumed to be {@code [0, 1, 2, 3, 4]}, resulting in a 5x5
   * confusion matrix.
   *
   * @param labels 1-D Operand of real labels for the classification task.
   * @param predictions 1-D Operand of predictions for a given classification.
   * @param <T> Data type of the confusion matrix.
   * @return An Operand of type {@code type} with shape {@code [n, n]} representing the confusion
   *     matrix, where {@code n} is the number of possible labels in the classification task.
   * @throws IllegalArgumentException If both predictions and labels are not 1-D vectors and have
   *     mismatched shapes, or if {@code weights} is not null and its shape doesn't match {@code
   *     predictions}.
   */
  public <T extends TNumber> Operand<T> confusionMatrix(Operand<T> labels, Operand<T> predictions) {
    return confusionMatrix(labels, predictions, null, null);
  }

  /**
   * Computes the confusion matrix from predictions and labels.
   *
   * <p>The matrix columns represent the prediction labels and the rows represent the real labels.
   * The confusion matrix is always a 2-D array of shape {@code [n,n]}, where {@code n} is the
   * number of valid labels for a given classification task. Both prediction and labels must be 1-D
   * arrays of the same shape in order for this function to work.
   *
   * <p>If {@code numClasses} is null, then {@code numClasses} will be set to one plus the maximum
   * value in either predictions or labels. Class labels are expected to start at 0. For example, if
   * {@code numClasses} is 3, then the possible labels would be {@code [0, 1, 2]}.
   *
   * <p>If {@code weights} is not null, then each prediction contributes its corresponding weight to
   * the total value of the confusion matrix cell.
   *
   * <p>For example:
   *
   * <pre>{@code
   * fops.math.confusion_matrix(tf.constant(new int[] {1, 2, 4}), tf.constant(new int[] {2, 2, 4})) ==>
   *     [[0 0 0 0 0]
   *      [0 0 1 0 0]
   *      [0 0 1 0 0]
   *      [0 0 0 0 0]
   *      [0 0 0 0 1]]
   * }</pre>
   *
   * <p>Note that the possible labels are assumed to be {@code [0, 1, 2, 3, 4]}, resulting in a 5x5
   * confusion matrix.
   *
   * @param labels 1-D Operand of real labels for the classification task.
   * @param predictions 1-D Operand of predictions for a given classification.
   * @param weights An optional Operand whose shape matches {@code predictions}.
   * @param <T> Data type of the confusion matrix.
   * @return An Operand of type {@code type} with shape {@code [n, n]} representing the confusion
   *     matrix, where {@code n} is the number of possible labels in the classification task.
   * @throws IllegalArgumentException If both predictions and labels are not 1-D vectors and have
   *     mismatched shapes, or if {@code weights} is not null and its shape doesn't match {@code
   *     predictions}.
   */
  public <T extends TNumber> Operand<T> confusionMatrix(
      Operand<T> labels, Operand<T> predictions, Operand<T> weights) {
    return confusionMatrix(labels, predictions, weights, null);
  }

  /**
   * Computes the confusion matrix from predictions and labels.
   *
   * <p>The matrix columns represent the prediction labels and the rows represent the real labels.
   * The confusion matrix is always a 2-D array of shape {@code [n,n]}, where {@code n} is the
   * number of valid labels for a given classification task. Both prediction and labels must be 1-D
   * arrays of the same shape in order for this function to work.
   *
   * <p>If {@code numClasses} is null, then {@code numClasses} will be set to one plus the maximum
   * value in either predictions or labels. Class labels are expected to start at 0. For example, if
   * {@code numClasses} is 3, then the possible labels would be {@code [0, 1, 2]}.
   *
   * <p>If {@code weights} is not null, then each prediction contributes its corresponding weight to
   * the total value of the confusion matrix cell.
   *
   * <p>For example:
   *
   * <pre>{@code
   * fops.math.confusion_matrix(tf.constant(new int[] {1, 2, 4}), tf.constant(new int[] {2, 2, 4})) ==>
   *     [[0 0 0 0 0]
   *      [0 0 1 0 0]
   *      [0 0 1 0 0]
   *      [0 0 0 0 0]
   *      [0 0 0 0 1]]
   * }</pre>
   *
   * <p>Note that the possible labels are assumed to be {@code [0, 1, 2, 3, 4]}, resulting in a 5x5
   * confusion matrix.
   *
   * @param labels 1-D Operand of real labels for the classification task.
   * @param predictions 1-D Operand of predictions for a given classification.
   * @param weights An optional Operand whose shape matches {@code predictions}.
   * @param numClasses The possible number of labels the classification task can have. If this value
   *     is null, it will be calculated using both predictions and labels.
   * @param <T> Data type of the confusion matrix.
   * @return An Operand of type {@code type} with shape {@code [n, n]} representing the confusion
   *     matrix, where {@code n} is the number of possible labels in the classification task.
   * @throws IllegalArgumentException If both predictions and labels are not 1-D vectors and have
   *     mismatched shapes, or if {@code weights} is not null and its shape doesn't match {@code
   *     predictions}.
   */
  public <T extends TNumber> Operand<T> confusionMatrix(
      Operand<T> labels, Operand<T> predictions, Operand<T> weights, Operand<TInt64> numClasses) {
    Scope lScope = scope.withSubScope("confusionMatrix");
    LossTuple<T> tuple = removeSqueezableDimensions(labels, predictions, 0);
    Operand<TInt64> lLabels = Cast.create(lScope, tuple.getLabels(), TInt64.class);
    Operand<TInt64> lPredictions = Cast.create(lScope, tuple.getTarget(), TInt64.class);

    Operand<TInt64> zero = Constant.scalarOf(lScope, 0L);
    Operand<TInt64> one = Constant.scalarOf(lScope, 1L);

    AssertThat labelsNonNegative =
        AssertThat.create(
            lScope,
            ReduceAll.create(lScope, GreaterEqual.create(lScope, lLabels, zero), allAxes(lLabels)),
            Collections.singletonList(
                Constant.scalarOf(lScope, "labels contains negative values")));
    lLabels =
        Identity.create(
            lScope.withControlDependencies(Collections.singletonList(labelsNonNegative)), lLabels);

    AssertThat predictionsNonNegative =
        AssertThat.create(
            lScope,
            ReduceAll.create(
                lScope, GreaterEqual.create(lScope, lPredictions, zero), allAxes(lPredictions)),
            Collections.singletonList(
                Constant.scalarOf(lScope, "predictions contains negative values")));
    lPredictions =
        Identity.create(
            lScope.withControlDependencies(Collections.singletonList(predictionsNonNegative)),
            lPredictions);

    Operand<TInt64> lNumClasses;
    if (numClasses == null) {
      lNumClasses =
          Add.create(
              lScope,
              Maximum.create(
                  lScope,
                  ReduceMax.create(lScope, lPredictions, zero),
                  ReduceMax.create(lScope, lLabels, zero)),
              one);
    } else {
      lNumClasses = Cast.create(lScope, numClasses, TInt64.class);
      Operand<TBool> less = Less.create(lScope, lLabels, lNumClasses);
      AssertThat labelsLess =
          AssertThat.create(
              lScope,
              ReduceAll.create(scope, less, allAxes(less), ReduceAll.keepDims(false)),
              Collections.singletonList(Constant.scalarOf(lScope, "labels out of bounds")));
      lLabels =
          Identity.create(
              lScope.withControlDependencies(Collections.singletonList(labelsLess)), lLabels);

      less = Less.create(lScope, lPredictions, lNumClasses);
      AssertThat predictionsLess =
          AssertThat.create(
              lScope,
              ReduceAll.create(scope, less, allAxes(less), ReduceAll.keepDims(false)),
              Collections.singletonList(Constant.scalarOf(lScope, "predictions  out of bounds")));
      lPredictions =
          Identity.create(
              lScope.withControlDependencies(Collections.singletonList(predictionsLess)),
              lPredictions);
    }

    if (weights != null) {
      if (!predictions.shape().isCompatibleWith(weights.shape())) {
        throw new IllegalArgumentException(
            String.format(
                "predictions.shape() [%s], is not compatible with weights.shape() [ %s].",
                predictions.shape(), weights.shape()));
      }
    }

    Operand<TInt64> shape = Stack.create(lScope, Arrays.asList(lNumClasses, lNumClasses));
    Operand<TInt64> indices =
        Stack.create(lScope, Arrays.asList(lLabels, lPredictions), Stack.axis(1L));
    Operand<T> values = weights == null ? OnesLike.create(lScope, predictions) : weights;
    /// Operand<T> zeroMatrix = Zeros.create(lScope, Cast.create(lScope, shape, TInt32.class),
    // type);

    return ScatterNd.create(lScope, indices, values, shape);
  }

  /**
   * Squeeze last dim if ranks differ from expected by exactly 1.
   *
   * @param labels Label values, a {@code Operand} whose dimensions match {@code predictions }.
   * @param predictions Predicted values, a {@code Tensor} of arbitrary dimensions.
   * @param expectedRankDiff Expected result of {@code rank(predictions) - rank(labels)}.
   * @param <T> the data type for the labels, predictions and result
   * @return {@code labels} and {@code predictions}, possibly with last dim squeezed.
   */
  public <T extends TNumber> LossTuple<T> removeSqueezableDimensions(
      Operand<T> labels, Operand<T> predictions, int expectedRankDiff) {
    Scope lScope = scope.withSubScope("removeSqueezableDimensions");
    Shape predictionsShape = predictions.shape();
    int predictionsRank = predictionsShape.numDimensions();
    Shape labelsShape = labels.shape();
    int labelsRank = labelsShape.numDimensions();

    if (predictionsRank != Shape.UNKNOWN_SIZE || labelsRank != Shape.UNKNOWN_SIZE) {
      // Use rank.
      int rankDiff = predictionsRank - labelsRank;
      if (rankDiff == expectedRankDiff + 1 && Shape.isCompatible(predictionsShape.size(-1), 1)) {
        predictions = Squeeze.create(lScope, predictions);
      } else if (rankDiff == expectedRankDiff - 1 && Shape.isCompatible(labelsShape.size(-1), 1)) {
        labels = Squeeze.create(lScope, labels);
      }
      return new LossTuple<>(labels, predictions);
    }
    // Use dynamic rank.

    // TODO: hold for lazy select feature,
    //  Operand<TInt32> rankDiff = tf.math.sub(tf.rank(predictions), tf.rank(labels));
    if (predictionsRank == Shape.UNKNOWN_SIZE && Shape.isCompatible(predictionsShape.size(-1), 1)) {
      /*
       * TODO, if we ever get a select that does lazy evaluation, but for now do the tf.squeeze
       * predictions = tf.select( tf.math.equal(tf.constant(expectedRankDiff+1),rankDiff ),
       * tf.squeeze(predictions, Squeeze.axis(Arrays.asList(-1L))), predictions ); *
       */
      predictions =
          Squeeze.create(lScope, predictions, Squeeze.axis(Collections.singletonList(-1L)));
    }
    if (labelsRank == Shape.UNKNOWN_SIZE && Shape.isCompatible(labelsShape.size(-1), 1)) {
      /*
       * TODO, if we ever get a select that does lazy evaluation labels = tf.select(
       * tf.math.equal(tf.constant(expectedRankDiff+1),rankDiff ), tf.squeeze(labels,
       * Squeeze.axis(Arrays.asList(-1L))), predictions ); *
       */
      labels = Squeeze.create(lScope, labels, Squeeze.axis(Collections.singletonList(-1L)));
    }
    return new LossTuple<>(labels, predictions);
  }

  /**
   * Creates an Operand that has all axes contained in the Operand's shape.
   *
   * @param op the Operand
   * @return an Operand that has all axes contained in the Operand's shape..
   */
  public Operand<TInt32> allAxes(Operand<? extends TType> op) {
    int rank = op.shape().numDimensions();
    if (rank != Shape.UNKNOWN_SIZE) {
      int[] axes = new int[rank];
      for (int i = 0; i < rank; i++) {
        axes[i] = i;
      }
      return Constant.vectorOf(scope, axes);
    } else {
      return Range.create(
          scope, Constant.scalarOf(scope, 0), Rank.create(scope, op), Constant.scalarOf(scope, 1));
    }
  }

  /**
   * Transpose and reshape the input for contraction op.
   *
   * <p>This method is helpful in reducing {@code math.tensordot} to {@code math_ops.matmul} using
   * {@code array_ops.transpose} and {@code array_ops.reshape}. The method takes a tensor and
   * performs the correct transpose and reshape operation for a given set of indices. It returns the
   * reshaped tensor as well as a list of indices necessary to reshape the tensor again after matrix
   * multiplication.
   *
   * @param <T> the type of Operand
   * @param a the Tensor
   * @param axis unique indices specifying valid axes of {@code a}.
   * @param flipped whether to flip the dimensions or not
   * @return A tuple (reshapedA, freeDims, freeDimsStatic) where reshapedA is a reshaped to allow
   *     contraction via matmul, freeDims is a TInt32 Operand, depending on whether the shape of a
   *     is fully specified, and freeDimsStatic is either a list of integers and null values, or
   *     None, representing the inferred shape of the free dimensions
   */
  private <T extends TNumber> Object[] tensordotReshape(
      Operand<T> a, Operand<TInt32> axis, boolean flipped) {
    Shape aShape = a.shape();

    if (!aShape.hasUnknownDimension()) { // calculate using values
      long[] aShapeDims = aShape.asArray();
      if (aShapeDims == null) aShapeDims = new long[0];
      long[] aDimsIndex = new long[aShapeDims.length];
      for (int i = 0; i < aDimsIndex.length; i++) aDimsIndex[i] = i;

      // get int array from axis Operand
      int[] iAxes = getIntArray(axis);
      // Convert negative axes to positive
      for (int i = 0; i < iAxes.length; i++)
        iAxes[i] = iAxes[i] >= 0 ? iAxes[i] : Math.floorMod(iAxes[i], iAxes.length);

      // convert integer axis to long axis
      long[] lAxes = Arrays.stream(iAxes).mapToLong(i -> i).toArray();

      // create list of the axes, dims, and free axes
      List<Long> axesList = Arrays.stream(lAxes).boxed().collect(Collectors.toList());
      List<Long> freeList = Arrays.stream(aDimsIndex).boxed().collect(Collectors.toList());
      freeList.removeAll(axesList);

      // create array of free dims
      long[] free = freeList.stream().mapToLong(i -> i).toArray();
      long[] freeDims = new long[free.length];
      for (int i = 0; i < free.length; i++) freeDims[i] = aShapeDims[(int) free[i]];

      // Calculate the free dim by doing a reduce prod
      long prodFree = 1;
      for (long i : freeDims) {
        prodFree *= i;
      }

      // calculate the used dims by doing a reduce prod
      long prodAxis = 1;
      for (long i : lAxes) {
        prodAxis *= aShapeDims[(int) i];
      }

      // setup the permutations array for the transpose
      long[] perm = new long[freeDims.length + lAxes.length];
      Shape newShape;
      if (flipped) {
        System.arraycopy(lAxes, 0, perm, 0, lAxes.length);
        System.arraycopy(free, 0, perm, lAxes.length, free.length);
        newShape = Shape.of(prodAxis, prodFree);
      } else {
        System.arraycopy(free, 0, perm, 0, free.length);
        System.arraycopy(lAxes, 0, perm, freeDims.length, lAxes.length);
        newShape = Shape.of(prodFree, prodAxis);
      }

      Operand<T> aTrans;
      long[] arrange = new long[lAxes.length];
      for (int i = 0; i < arrange.length; i++) arrange[i] = i;

      // if the permutations is not equals to the natural order of the dims, then do a transpose
      if (!Arrays.equals(perm, arrange)) {
        aTrans = Transpose.create(scope, a, Constant.vectorOf(scope, perm));
      } else {
        aTrans = a;
      }

      // reshape the final result to the new Shape, if necessary
      Operand<T> aReshaped =
          aTrans.asOutput().shape().equals(newShape)
              ? aTrans
              : Reshape.create(scope, aTrans, Constant.vectorOf(scope, newShape.asArray()));
      // return a tuple for the reshaped Operand, and Operand for the free dimensions, and a long
      // array for the free dimensions
      return new Object[] {aReshaped, Constant.vectorOf(scope, freeDims), freeDims};

    } else { // calculate dynamically

      long[] freeDimsStatic = null;
      Operand<TInt32> one = Constant.scalarOf(scope, 1);
      Operand<TInt32> minusOne = Constant.scalarOf(scope, -1);
      Operand<TInt32> zero = Constant.scalarOf(scope, 0);
      org.tensorflow.op.core.Shape<TInt32> tShape = org.tensorflow.op.core.Shape.create(scope, a);
      Operand<TInt32> axesT;
      Operand<TInt32> freeT;
      if (aShape.numDimensions()
          != Shape.UNKNOWN_SIZE) { // we know the rank, but there are unknown dimensions
        long[] aShapeDims = aShape.asArray();
        if (aShapeDims == null) aShapeDims = new long[0];

        // get int array from axis Operand
        int[] iAxes = getIntArray(axis);
        // Convert negative axes to positive
        for (int i = 0; i < iAxes.length; i++)
          iAxes[i] = iAxes[i] >= 0 ? iAxes[i] : Math.floorMod(iAxes[i], iAxes.length);

        // convert integer axis to long axis
        long[] lAxes = Arrays.stream(iAxes).mapToLong(i -> i).toArray();

        // create list of the axes, dims, and free axes
        List<Long> axesList = Arrays.stream(lAxes).boxed().collect(Collectors.toList());
        List<Long> dimsList = Arrays.stream(aShapeDims).boxed().collect(Collectors.toList());
        List<Long> freeList = new ArrayList<>(axesList);
        freeList.removeAll(dimsList);

        // create array of free dims
        long[] freeDims = freeList.stream().mapToLong(i -> i).toArray();
        freeDimsStatic = freeDims;

        axesT = Constant.vectorOf(scope, iAxes);
        freeT = Cast.create(scope, Constant.vectorOf(scope, freeDims), TInt32.class);

      } else { // we don't know the rank yet
        Rank rank = Rank.create(scope, a);

        // convert axis to positive
        axesT =
            Select.create(
                scope,
                GreaterEqual.create(scope, axis, Constant.scalarOf(scope, 0)),
                axis,
                Add.create(scope, axis, rank));

        SetDiff1d<TInt32, TInt32> diff =
            SetDiff1d.create(
                scope, Range.create(scope, Constant.scalarOf(scope, 0), rank, one), axesT);
        freeT = diff.out();
      }
      Operand<TInt32> freeDims = Gather.create(scope, tShape, freeT, zero);
      Operand<TInt32> axesDims = Gather.create(scope, tShape, axesT, zero);
      Operand<TInt32> prodFreeDims = ReduceProd.create(scope, freeDims, minusOne);
      Operand<TInt32> prodAxesDims = ReduceProd.create(scope, axesDims, minusOne);
      Operand<TInt32> perm;
      Operand<TInt32> newShape;
      if (flipped) {
        perm = Concat.create(scope, Arrays.asList(axesT, freeT), zero);
        newShape = Stack.create(scope, Arrays.asList(prodAxesDims, prodFreeDims));
      } else {
        perm = Concat.create(scope, Arrays.asList(freeT, axesT), zero);
        newShape = Stack.create(scope, Arrays.asList(prodFreeDims, prodAxesDims));
      }
      Operand<T> aReshaped = Reshape.create(scope, Transpose.create(scope, a, perm), newShape);
      return new Object[] {aReshaped, freeDims, freeDimsStatic};
    }
  }

  /**
   * Gets an int array from an Operand&lt;TInt32&gt operand.
   *
   * @param axes the Operand to fetch the values
   * @return the int array from an Operand&lt;TInt32&gt;
   */
  private int[] getIntArray(Operand<TInt32> axes) {
    List<Integer> result = new ArrayList<>();
    if (scope.env().isEager()) {
      axes.asTensor().scalars().forEach(s -> result.add(s.getInt()));
    } else {
      try (Session session = new Session((Graph) scope.env());
          TInt32 tensor = (TInt32) session.runner().fetch(axes).run().get(0)) {
        tensor.scalars().forEach(s -> result.add(s.getInt()));
      }
    }
    return result.stream().mapToInt(i -> i).toArray();
  }

  /**
   * Generates two sets of contraction axes for the two tensor arguments.
   *
   * @param a the Operand to analyze
   * @param axis the axes
   * @param <T> the data type for the Operand
   * @return the contraction axes
   */
  @SuppressWarnings("unchecked")
  private <T extends TNumber> Operand<TInt32>[] tensordotAxes(Operand<T> a, int axis) {
    Shape aShape = a.asOutput().shape();
    if (axis < 0) {
      throw new IllegalArgumentException("'axis' must be at least 0.");
    }
    int rank = aShape.numDimensions();
    Operand<TInt32>[] result = new Operand[2];
    if (rank != Shape.UNKNOWN_SIZE) {
      if (axis > rank) {
        throw new IllegalArgumentException(
            String.format(
                "'axis' must not be larger than the number of dimensions of tensor %s.", rank));
      }
      int min = rank - axis;
      int postRange = rank - min;
      int[] postAxis = new int[postRange];
      for (int i = 0; i < postRange; i++) postAxis[i] = i + min;

      int[] preAxis = new int[axis];
      for (int i = 0; i < axis; i++) preAxis[i] = i;

      result[0] = Constant.vectorOf(scope, postAxis);
      result[1] = Constant.vectorOf(scope, preAxis);
    } else {
      Rank rankT = Rank.create(scope, a);
      Constant<TInt32> axisT = Constant.scalarOf(scope, axis);
      Constant<TInt32> one = Constant.scalarOf(scope, 1);
      Constant<TInt32> zero = Constant.scalarOf(scope, 0);
      AssertThat assertion =
          AssertThat.create(
              scope,
              Less.create(scope, axisT, rankT),
              Arrays.asList(
                  Constant.scalarOf(
                      scope, "'axes' must not be larger than the number of dimensions of tensor "),
                  rankT));
      Scope scope1 = scope.withControlDependencies(Collections.singletonList(assertion));
      result[0] = Range.create(scope1, Sub.create(scope, rankT, axisT), rankT, one);
      result[1] = Range.create(scope1, zero, axisT, one);
    }
    return result;
  }

  /**
   * Generates two sets of contraction axes for the two tensor arguments.
   *
   * @param a the Operand to analyze
   * @param axes the axes
   * @param <T> the data type for the Operand
   * @return the contraction axes
   */
  @SuppressWarnings({"unchecked", "unused"})
  private <T extends TNumber> Operand<TInt32>[] tensordotAxes(Operand<T> a, int[] axes) {
    if (axes.length != 2)
      throw new IllegalArgumentException(
          "'axes' must have length 1 or 2, provided with " + axes.length);
    int[] aAxis = new int[] {axes[0]};
    int[] bAxis = new int[] {axes[1]};
    Operand<TInt32>[] result = new Operand[2];
    result[0] = Constant.vectorOf(scope, aAxis);
    result[1] = Constant.vectorOf(scope, bAxis);

    return result;
  }

  /**
   * Generates two sets of contraction axes for the two tensor arguments.
   *
   * @param a the Operand to analyze
   * @param axes the axes
   * @param <T> the data type for the Operand
   * @return the contraction axes
   */
  @SuppressWarnings({"unchecked", "unused"})
  private <T extends TNumber> Operand<TInt32>[] tensordotAxes(Operand<T> a, int[][] axes) {
    if (axes.length != 2)
      throw new IllegalArgumentException(
          "'axes' must have length 1 or 2, provided with " + axes.length);
    int[] aAxis = axes[0];
    int[] bAxis = axes[1];
    if (aAxis.length != bAxis.length)
      throw new IllegalArgumentException(
          String.format(
              "Different number of contraction axes 'a' and 'b', %d != %d",
              aAxis.length, bAxis.length));
    Operand<TInt32>[] result = new Operand[2];
    result[0] = Constant.vectorOf(scope, aAxis);
    result[1] = Constant.vectorOf(scope, bAxis);
    return result;
  }

  /**
   * Generates two sets of contraction axes for the two tensor arguments.
   *
   * @param a the Operand to analyze
   * @param axes the axes
   * @param <T> the data type for the Operand
   * @return the contraction axes
   */
  @SuppressWarnings({"unchecked", "unused"})
  private <T extends TNumber> Operand<TInt32>[] tensordotAxes(Operand<T> a, Operand<TInt32> axes) {

    Constant<TInt32> one = Constant.scalarOf(scope, 1);
    Constant<TInt32> zero = Constant.scalarOf(scope, 0);
    Operand<TInt32>[] result = new Operand[2];
    result[0] =
        Slice.create(
            scope,
            axes,
            Cast.create(scope, zero, TInt32.class),
            Cast.create(scope, one, TInt32.class));
    result[1] =
        Slice.create(
            scope,
            axes,
            Cast.create(scope, one, TInt32.class),
            Cast.create(scope, one, TInt32.class));
    return result;
  }

  /**
   * Tensor contraction of a and b along specified axes and outer product.
   *
   * <p>Tensordot (also known as tensor contraction) sums the product of elements from {@code a} and
   * {@code b} over the indices specified by {@code a_axes} and {@code b_axes}. The lists {@code
   * a_axes} and {@code b_axes} specify those pairs of axes along which to contract the tensors. The
   * axis {@code a_axes[i]} of {@code a} must have the same dimension as axis {@code b_axes[i]} of
   * {@code b} for all {@code i} in {@code range(0, len(a_axes))}. The lists {@code a_axes} and
   * {@code b_axes} must have identical length and consist of unique integers that specify valid
   * axes for each of the tensors. Additionally outer product is supported by passing {@code
   * axes=0}.
   *
   * <p>This operation corresponds to {@code numpy.tensordot(a, b, axes)}.
   *
   * <p>Example 1: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = 1} is
   * equivalent to matrix multiplication.
   *
   * <p>Example 2: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = [[1],
   * [0]]} is equivalent to matrix multiplication.
   *
   * <p>Example 3: When {@code a} and {@code b} are matrices (order 2), the case {@code axes=0}
   * gives the outer product, a tensor of order 4.
   *
   * <p>Example 4: Suppose that <i>a<sub>ijk</sub></i> and <i>b<sub>lmn</sub></i> represent two
   * tensors of order 3. Then, {@code contract(a, b, [[0], [2]])} is the order 4 tensor
   * <i>c<sub>jklm</sub></i> whose entry corresponding to the indices <i>(j,k,l,m)</i> is given by:
   * <i> c<sub>jklm</sub> = &Sigma;<sub>i</sub> a<sub>ijk</sub> b<sub>lmi</sub> </i>.
   *
   * <p>In general, {@code order(c) = order(a) + order(b) - 2*len(axes[0])}.
   *
   * @param a {@code Operand} of type {@code TFloat32} or {@code TFloat64}.
   * @param b {@code Operand} with the same type as {@code a}.
   * @param axis sum over the last N axes of a and the first N axes of b in order. If {@code
   *     axis=0}, computes the outer product between {@code a} and {@code b}.
   * @param <T> the datatype of the Operands, must be either TFloat32 or TFloat64
   * @return A {@code Operand} with the same type as {@code a}.
   * @throws IllegalArgumentException if a is not a float32 or float64 data type and if a and b are
   *     not the same data type
   */
  @Endpoint(name = "tensordot")
  public <T extends TFloating> Operand<T> tensordot(Operand<T> a, Operand<T> b, int axis) {

    Operand<TInt32>[] abAxis = tensordotAxes(a, axis);
    Operand<TInt32> aAxis = abAxis[0];
    Operand<TInt32> bAxis = abAxis[1];
    return tensordot(a, b, aAxis, bAxis);
  }

  /**
   * Tensor contraction of a and b along specified axes and outer product.
   *
   * <p>Tensordot (also known as tensor contraction) sums the product of elements from {@code a} and
   * {@code b} over the indices specified by {@code a_axes} and {@code b_axes}. The lists {@code
   * a_axes} and {@code b_axes} specify those pairs of axes along which to contract the tensors. The
   * axis {@code a_axes[i]} of {@code a} must have the same dimension as axis {@code b_axes[i]} of
   * {@code b} for all {@code i} in {@code range(0, len(a_axes))}. The lists {@code a_axes} and
   * {@code b_axes} must have identical length and consist of unique integers that specify valid
   * axes for each of the tensors. Additionally outer product is supported by passing {@code
   * axes=0}.
   *
   * <p>This operation corresponds to {@code numpy.tensordot(a, b, axes)}.
   *
   * <p>Example 1: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = 1} is
   * equivalent to matrix multiplication.
   *
   * <p>Example 2: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = [[1],
   * [0]]} is equivalent to matrix multiplication.
   *
   * <p>Example 3: When {@code a} and {@code b} are matrices (order 2), the case {@code axes=0}
   * gives the outer product, a tensor of order 4.
   *
   * <p>Example 4: Suppose that <i>a<sub>ijk</sub></i> and <i>b<sub>lmn</sub></i> represent two
   * tensors of order 3. Then, {@code contract(a, b, [[0], [2]])} is the order 4 tensor
   * <i>c<sub>jklm</sub></i> whose entry corresponding to the indices <i>(j,k,l,m)</i> is given by:
   *
   * <p><i> c<sub>jklm</sub> = &Sigma;<sub>i</sub> a<sub>ijk</sub> b<sub>lmi</sub> </i>.
   *
   * <p>In general, {@code order(c) = order(a) + order(b) - 2*len(axes[0])}.
   *
   * <p>
   *
   * @param a {@code Operand} of type {@code TFloat32} or {@code TFloat64}.
   * @param b {@code Operand} with the same type as {@code a}.
   * @param axes If axes is a scalar, sum over the last N axes of a and the first N axes of b in
   *     order. If axes is a list, the first and second row contain the set of unique integers
   *     specifying axes along which the contraction is computed, for {@code a} and {@code b},
   *     respectively. The number of axes for {@code a} and {@code b} must be equal. If {@code
   *     axis=0}, computes the outer product between {@code a} and {@code b}.
   * @param <T> the datatype of the Operands, must be either TFloat32 or TFloat64
   * @return A {@code Operand} with the same type as {@code a}.
   * @throws IllegalArgumentException if a is not a float32 or float64 data type and if a and b are
   *     not the same data type
   */
  @Endpoint(name = "tensordot")
  public <T extends TFloating> Operand<T> tensordot(
      Operand<T> a, Operand<T> b, Operand<TInt32> axes) {

    Operand<TInt32>[] abAxis = tensordotAxes(a, axes);
    Operand<TInt32> aAxis = abAxis[0];
    Operand<TInt32> bAxis = abAxis[1];

    return tensordot(a, b, aAxis, bAxis);
  }

  /**
   * Tensor contraction of a and b along specified axes and outer product.
   *
   * <p>Tensordot (also known as tensor contraction) sums the product of elements from {@code a} and
   * {@code b} over the indices specified by {@code a_axes} and {@code b_axes}. The lists {@code
   * a_axes} and {@code b_axes} specify those pairs of axes along which to contract the tensors. The
   * axis {@code a_axes[i]} of {@code a} must have the same dimension as axis {@code b_axes[i]} of
   * {@code b} for all {@code i} in {@code range(0, len(a_axes))}. The lists {@code a_axes} and
   * {@code b_axes} must have identical length and consist of unique integers that specify valid
   * axes for each of the tensors. Additionally outer product is supported by passing {@code
   * axes=0}.
   *
   * <p>This operation corresponds to {@code numpy.tensordot(a, b, axes)}.
   *
   * <p>Example 1: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = 1} is
   * equivalent to matrix multiplication.
   *
   * <p>Example 2: When {@code a} and{@code b} are matrices (order 2), the case {@code axes = [[1],
   * [0]]} is equivalent to matrix multiplication.
   *
   * <p>Example 3: When {@code a} and {@code b} are matrices (order 2), the case {@code axes=0}
   * gives the outer product, a tensor of order 4.
   *
   * <p>Example 4: Suppose that <i>a<sub>ijk</sub></i> and <i>b<sub>lmn</sub></i> represent two
   * tensors of order 3. Then, {@code contract(a, b, [[0], [2]])} is the order 4 tensor
   * <i>c<sub>jklm</sub></i> whose entry corresponding to the indices <i>(j,k,l,m)</i> is given by:
   *
   * <p><i> c<sub>jklm</sub> = &Sigma;<sub>i</sub> a<sub>ijk</sub> b<sub>lmi</sub> </i>.
   *
   * <p>In general, {@code order(c) = order(a) + order(b) - 2*len(axes[0])}.
   *
   * <p>
   *
   * @param a {@code Operand} of type {@code TFloat32} or {@code TFloat64}.
   * @param b {@code Operand} with the same type as {@code a}.
   * @param axes the first and second row contain the set of unique integers specifying axes along
   *     which the contraction is computed, for {@code a} and {@code b}, respectively. The number of
   *     axes for {@code a} and {@code b} must be equal. I
   * @param <T> the datatype of the Operands, must be either TFloat32 or TFloat64
   * @return A {@code Operand} with the same type as {@code a}.
   * @throws IllegalArgumentException if a is not a float32 or float64 data type and if a and b are
   *     not the same data type
   */
  @Endpoint(name = "tensordot")
  public <T extends TFloating> Operand<T> tensordot(Operand<T> a, Operand<T> b, int[] axes) {

    Operand<TInt32>[] abAxis = tensordotAxes(a, axes);
    Operand<TInt32> aAxis = abAxis[0];
    Operand<TInt32> bAxis = abAxis[1];

    return tensordot(a, b, aAxis, bAxis);
  }

  /**
   * Tensor contraction of a and b along specified axes and outer product.
   *
   * <p>Tensordot (also known as tensor contraction) sums the product of elements from {@code a} and
   * {@code b} over the indices specified by {@code a_axes} and {@code b_axes}. The lists {@code
   * a_axes} and {@code b_axes} specify those pairs of axes along which to contract the tensors. The
   * axis {@code a_axes[i]} of {@code a} must have the same dimension as axis {@code b_axes[i]} of
   * {@code b} for all {@code i} in {@code range(0, len(a_axes))}. The lists {@code a_axes} and
   * {@code b_axes} must have identical length and consist of unique integers that specify valid
   * axes for each of the tensors. Additionally outer product is supported by passing {@code
   * axes=0}.
   *
   * <p>This operation corresponds to {@code numpy.tensordot(a, b, axes)}.
   *
   * <p>Example 1: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = 1} is
   * equivalent to matrix multiplication.
   *
   * <p>Example 2: When {@code a} and{@code b} are matrices (order 2), the case {@code axes = [[1],
   * [0]]} is equivalent to matrix multiplication.
   *
   * <p>Example 3: When {@code a} and {@code b} are matrices (order 2), the case {@code axes=0}
   * gives the outer product, a tensor of order 4.
   *
   * <p>Example 4: Suppose that <i>a<sub>ijk</sub></i> and <i>b<sub>lmn</sub></i> represent two
   * tensors of order 3. Then, {@code contract(a, b, [[0], [2]])} is the order 4 tensor
   * <i>c<sub>jklm</sub></i> whose entry corresponding to the indices <i>(j,k,l,m)</i> is given by:
   *
   * <p><i> c<sub>jklm</sub> = &Sigma;<sub>i</sub> a<sub>ijk</sub> b<sub>lmi</sub> </i>.
   *
   * <p>In general, {@code order(c) = order(a) + order(b) - 2*len(axes[0])}.
   *
   * <p>
   *
   * @param a {@code Operand} of type {@code TFloat32} or {@code TFloat64}.
   * @param b {@code Operand} with the same type as {@code a}.
   * @param axes the first and second row contain the set of unique integers specifying axes along
   *     which the contraction is computed, for {@code a} and {@code b}, respectively. The number of
   *     axes for {@code a} and {@code b} must be equal. I
   * @param <T> the datatype of the Operands, must be either TFloat32 or TFloat64
   * @return A {@code Operand} with the same type as {@code a}.
   * @throws IllegalArgumentException if a is not a float32 or float64 data type and if a and b are
   *     not the same data type
   */
  @Endpoint(name = "tensordot")
  public <T extends TFloating> Operand<T> tensordot(Operand<T> a, Operand<T> b, int[][] axes) {

    Operand<TInt32>[] abAxis = tensordotAxes(a, axes);
    Operand<TInt32> aAxis = abAxis[0];
    Operand<TInt32> bAxis = abAxis[1];

    return tensordot(a, b, aAxis, bAxis);
  }

  /**
   * Tensor contraction of a and b along specified axes and outer product.
   *
   * <p>Tensordot (also known as tensor contraction) sums the product of elements from {@code a} and
   * {@code b} over the indices specified by {@code a_axes} and {@code b_axes}. The lists {@code
   * a_axes} and {@code b_axes} specify those pairs of axes along which to contract the tensors. The
   * axis {@code a_axes[i]} of {@code a} must have the same dimension as axis {@code b_axes[i]} of
   * {@code b} for all {@code i} in {@code range(0, len(a_axes))}. The lists {@code a_axes} and
   * {@code b_axes} must have identical length and consist of unique integers that specify valid
   * axes for each of the tensors. Additionally outer product is supported by passing {@code
   * axes=0}.
   *
   * <p>This operation corresponds to {@code numpy.tensordot(a, b, axes)}.
   *
   * <p>Example 1: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = 1} is
   * equivalent to matrix multiplication.
   *
   * <p>Example 2: When {@code a} and{@code b} are matrices (order 2), the case {@code axes = [[1],
   * [0]]} is equivalent to matrix multiplication.
   *
   * <p>Example 3: When {@code a} and {@code b} are matrices (order 2), the case {@code axes=0}
   * gives the outer product, a tensor of order 4.
   *
   * <p>Example 4: Suppose that <i>a<sub>ijk</sub></i> and <i>b<sub>lmn</sub></i> represent two
   * tensors of order 3. Then, {@code contract(a, b, [[0], [2]])} is the order 4 tensor
   * <i>c<sub>jklm</sub></i> whose entry corresponding to the indices <i>(j,k,l,m)</i> is given by:
   *
   * <p><i> c<sub>jklm</sub> = &Sigma;<sub>i</sub> a<sub>ijk</sub> b<sub>lmi</sub> </i>.
   *
   * <p>In general, {@code order(c) = order(a) + order(b) - 2*len(axes[0])}.
   *
   * <p>
   *
   * @param a {@code Operand} of type {@code TFloat32} or {@code TFloat64}.
   * @param b {@code Operand} with the same type as {@code a}.
   * @param aAxis axes for the a Operand
   * @param bAxis axes for the b Operand
   * @param <T> the datatype of the Operands, must be either TFloat32 or TFloat64
   * @return A {@code Operand} with the same type as {@code a}.
   * @throws IllegalArgumentException if a is not a float32 or float64 data type and if a and b are
   *     not the same data type
   */
  @SuppressWarnings({"unchecked", "unused"})
  @Endpoint(name = "tensordot")
  public <T extends TFloating> Operand<T> tensordot(
      Operand<T> a, Operand<T> b, Operand<TInt32> aAxis, Operand<TInt32> bAxis) {

    if (a.type().equals(TBfloat16.class) || a.type().equals(TFloat16.class)) {
      throw new IllegalArgumentException(
          String.format(
              "Operand 'a' must be either TFloat32 or TFloat64 DataType, 'a' is a %s DataType",
              a.type().getSimpleName()));
    }
    if (!a.type().equals(b.type())) {
      throw new IllegalArgumentException(
          String.format(
              "Operands a and b must be the same data type, a is %s DataType, b is %s DataType",
              a.type().getSimpleName(), b.type().getSimpleName()));
    }

    // first result is Operand<T>, second result is Operand<TIn32>, third result is long[] and it is
    // ignored here.
    Object[] aResult = tensordotReshape(a, aAxis, false);
    Operand<T> reshapedA = (Operand<T>) aResult[0];
    Operand<TInt32> aFreeDims = (Operand<TInt32>) aResult[1];
    long[] aFreeDimsStatic = (long[]) aResult[2];

    // first result is Operand<T>, second result is Operand<TIn32>, third result is long[] and it is
    // ignored here.
    Object[] bResult = tensordotReshape(b, bAxis, true);
    Operand<T> reshapedB = (Operand<T>) bResult[0];
    Operand<TInt32> bFreeDims = (Operand<TInt32>) bResult[1];
    long[] bFreeDimsStatic = (long[]) bResult[2];

    Operand<T> abMatmul = frameworkOps.linalg.matmul(reshapedA, reshapedB);
    long[] abDimsStatic = new long[aFreeDimsStatic.length + bFreeDimsStatic.length];
    System.arraycopy(aFreeDimsStatic, 0, abDimsStatic, 0, aFreeDimsStatic.length);
    System.arraycopy(
        bFreeDimsStatic, 0, abDimsStatic, aFreeDimsStatic.length, bFreeDimsStatic.length);
    if (!abMatmul.shape().hasUnknownDimension()
        && abMatmul.shape().equals(Shape.of(abDimsStatic))) {
      return abMatmul;
    } else {
      return Reshape.create(scope, abMatmul, Constant.vectorOf(scope, abDimsStatic));
    }
  }

  /**
   * Computes log(sum(exp(elements across dimensions of a tensor))). Reduces {@code input_tensor}
   * along the dimensions given in {@code axes}.
   *
   * <p>Reduces {@code input} along the dimensions given in {@code axes}. Unless {@code keepdims} is
   * true, the rank of the tensor is reduced by 1 for each of the entries in {@code axes}, which
   * must be unique. If {@code keepdims} is true, the reduced dimensions are retained with length 1.
   * If {@code axes} has no entries, all dimensions are reduced, and a tensor with a single element
   * is returned. This function is more numerically stable than {@code log(sum(exp(input)))}. It
   * avoids overflows caused by taking the exp of large inputs and underflows caused by taking the
   * log of small inputs.
   *
   * @param input The tensor to reduce.
   * @param axes The dimensions to reduce. If null, reduces all dimensions. Must be in the range
   *     {@code [-rank(input_tensor), rank(input_tensor)]}.
   * @param keepDims If true, retains reduced dimensions with length 1.
   * @param <T> the data type for the input and the result
   * @return The reduced tensor.
   */
  @Endpoint(name = "reduceLogSumExp")
  public <T extends TFloating> Operand<T> reduceLogSumExp(
      Operand<T> input, int[] axes, boolean keepDims) {
    Operand<TInt32> reduceDims = reductionDims(input, axes);
    Operand<T> rawMax = reduceMaxWithDims(input, axes, keepDims, reduceDims);
    Operand<T> myMax =
        StopGradient.create(
            scope,
            Select.create(
                scope, IsFinite.create(scope, rawMax), rawMax, ZerosLike.create(scope, rawMax)));

    Operand<T> result =
        Log.create(
            scope,
            reduceSumWithDims(
                Exp.create(scope, Sub.create(scope, input, myMax)), axes, keepDims, reduceDims));

    if (!keepDims) {
      myMax = Reshape.create(scope, myMax, org.tensorflow.op.core.Shape.create(scope, result));
    }
    result = Add.create(scope, result, myMax);
    return mayReduceToScalar(keepDims, axes, result);
  }

  private <T extends TFloating> Operand<T> reduceSumWithDims(
      Operand<T> input, int[] axes, boolean keepDims, Operand<TInt32> dims) {
    return mayReduceToScalar(
        keepDims, axes, ReduceSum.create(scope, input, dims, ReduceSum.keepDims(keepDims)));
  }

  private <T extends TFloating> Operand<T> reduceMaxWithDims(
      Operand<T> input, int[] axes, boolean keepDims, Operand<TInt32> dims) {
    return mayReduceToScalar(
        keepDims, axes, ReduceMax.create(scope, input, dims, ReduceMax.keepDims(keepDims)));
  }

  /**
   * Sets a reduction's output shape to be a scalar if possible.
   *
   * @return the operand, possibly reduced to a scalar.
   */
  private <T extends TFloating> Operand<T> mayReduceToScalar(
      boolean keepDims, int[] axes, Operand<T> output) {

    if ((output.shape().numDimensions() == Shape.UNKNOWN_SIZE
            || output.shape().hasUnknownDimension())
        && !keepDims
        && axes == null) {
      return Reshape.create(scope, output, Constant.tensorOf(scope, Shape.scalar()));
    } else {
      return output;
    }
  }

  /**
   * Reduce dimensions based on axis
   *
   * @param input the input
   * @param axes he dimensions to reduce, may be null
   * @return the dimensions to be reduced.
   */
  private <T extends TFloating> Operand<TInt32> reductionDims(Operand<T> input, int[] axes) {
    if (axes != null) {
      return Constant.vectorOf(scope, axes);
    }
    long rank = input.shape().numDimensions();
    if (rank != Shape.UNKNOWN_SIZE) {
      int[] dims = new int[(int) rank];
      for (int i = 0; i < rank; i++) {
        dims[i] = i;
      }
      return Constant.vectorOf(scope, dims);

    } else {
      return Range.create(
          scope,
          Constant.scalarOf(scope, 0),
          Rank.create(scope, input),
          Constant.scalarOf(scope, 1));
    }
  }
}
