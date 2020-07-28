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
package org.tensorflow.keras.backend;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.tensorflow.DataType;
import org.tensorflow.EagerSession;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.keras.backend.tf.ConfusionMatrix;
import org.tensorflow.keras.backend.tf.NN;
import org.tensorflow.keras.backend.tf.Tuple;
import org.tensorflow.ndarray.NdArraySequence;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.ReduceSum;
import org.tensorflow.op.core.Squeeze;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.math.Mean;
import org.tensorflow.op.nn.SoftmaxCrossEntropyWithLogits;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/** Keras backend methods */
public class K {

  public static final double Epsilon = 1e-7;
  public static final float EpsilonF = 1e-7F;

  public static final double epsilon() {
    return Epsilon;
  }

  public static final Operand epsilonConstant(Ops tf) {
    return tf.constant(Epsilon);
  }

  public static final Operand epsilonConstant(Ops tf, DataType dtype) {
    return tf.dtypes.cast(tf.constant(Epsilon), dtype);
  }

  public static final Operand one(Ops tf) {
    return tf.constant(1);
  }

  public static final Operand one(Ops tf, DataType dtype) {
    return tf.dtypes.cast(tf.constant(1), dtype);
  }

  public static final Operand minusOne(Ops tf) {
    return tf.constant(-1);
  }

  public static final Operand minusOne(Ops tf, DataType dtype) {
    return tf.dtypes.cast(tf.constant(-1), dtype);
  }

  public static final Operand zero(Ops tf) {
    return tf.constant(0);
  }

  public static final Operand zero(Ops tf, DataType dtype) {
    return tf.dtypes.cast(tf.constant(0), dtype);
  }

  public static final Operand constant(Ops tf, double number, DataType dtype) {
    return tf.dtypes.cast(tf.constant(number), dtype);
  }

  public static Operand clip(Ops tf, Operand x, double minValue, double maxValue) {
    assert x != null : "Operand x must not be null";
    DataType dtype = x.asOutput().dataType();
    if (maxValue < minValue) {
      double tmp = maxValue;
      maxValue = minValue;
      minValue = tmp;
    }
    Operand minValueConstant = tf.dtypes.cast(tf.constant(minValue), dtype);
    Operand maxValueConstant = tf.dtypes.cast(tf.constant(maxValue), dtype);
    return tf.clipByValue(x, minValueConstant, maxValueConstant);
  }

  public static Operand mean(Ops tf, Operand x) {
    return mean(tf, x, null, false);
  }

  public static Operand mean(Ops tf, Operand x, Operand axis) {
    return mean(tf, x, axis, false);
  }

  public static Operand mean(Ops tf, Operand x, boolean keepDims) {
    return mean(tf, x, null, keepDims);
  }

  public static Operand mean(Ops tf, Operand x, Operand axis, boolean keepDims) {
    if (x.asOutput().dataType() == TBool.DTYPE) {
      x = tf.dtypes.cast(x, TFloat32.DTYPE);
    }
    if (axis == null) {
      axis = allAxis(tf, x);
    }
    return tf.math.mean(x, axis, Mean.keepDims(keepDims));
  }

  // alias for mean
  public static Operand reduceMean(Ops tf, Operand x, Operand axis, boolean keepDims) {
    return mean(tf, x, axis, keepDims);
  }

  public static Operand maximum(Ops tf, Operand x, Operand y) {
    y = tf.dtypes.cast(y, x.asOutput().dataType());
    return tf.math.maximum(x, y);
  }

  public static <T extends TType> Operand<T> sqrt(Ops tf, Operand<T> x) {
    DataType dType = x.asOutput().dataType();
    Operand<T> zero = tf.dtypes.cast(tf.constant(0), dType);
    Operand<T> inf = tf.dtypes.cast(tf.constant(Float.POSITIVE_INFINITY), dType);
    x = tf.clipByValue(x, zero, inf);
    return tf.math.sqrt(x);
  }

  public static Shape merge(Shape a, Shape b) {
    assert a.numDimensions() == b.numDimensions()
        : String.format("Shapes %s and %s are incompatible", a, b);
    long[] array = new long[a.numDimensions()];
    for (int i = 0; i < a.numDimensions(); i++) {
      if (a.size(i) != Shape.UNKNOWN_SIZE) {
        if (b.size(i) != Shape.UNKNOWN_SIZE) {
          assert a.size(i) == b.size(i) : String.format("Shapes %s and %s are incompatible", a, b);
        }
        array[i] = a.size(i);
      } else {
        array[i] = b.size(i);
      }
    }
    return Shape.of(array);
  }

  // this is from nn in Python, I could not find it in the Java frameworks.
  public static Operand sigmoidCrossEntropyWithLogits(Ops tf, Operand labels, Operand logits) {
    Shape lablesShape = labels.asOutput().shape();
    Shape logitsShape = logits.asOutput().shape();
    Shape newShape = merge(lablesShape, logitsShape);

    Operand zeros = tf.dtypes.cast(tf.zerosLike(logits), logits.asOutput().dataType());
    Operand cond = tf.math.greaterEqual(logits, zeros);

    Operand relu_logits = tf.select(cond, logits, zeros);
    Operand neg_abs_logits = tf.select(cond, tf.math.neg(logits), logits);
    return tf.math.add(
        tf.math.sub(relu_logits, tf.math.mul(logits, labels)),
        tf.math.log1p(tf.math.exp(neg_abs_logits)));
  }

  // TODO need to walk back identity until it hits something else
  // not sure how to get the input nodes for the Operand.
  private static Operand backtrackIdentity(Operand output) {
    // while(!output.op().type().equals("Identity"))
    //    output = output.op().output(0);
    return output;
  }

  public static Operand binary_crossentropy(
      Ops tf, Operand target, Operand output, boolean fromLogits) {
    if (fromLogits) {
      return sigmoidCrossEntropyWithLogits(tf, target, output);
    }

    if (!(output instanceof Variable) && (!tf.scope().env().isEager())) {
      // output = backtrackIdentity(output); // TODO - this does not work, goes infinite loop
      if (output.op().type().equals("Sigmoid")) {
        assert output.op().numOutputs() == 1;
        output = output.op().output(0);
        return sigmoidCrossEntropyWithLogits(tf, target, output);
      }
    }
    DataType dtype = output.asOutput().dataType();
    Operand one = one(tf, dtype);
    Operand epsilonConst = K.epsilonConstant(tf, dtype);
    Operand oneMinusEpsilonConst = tf.math.sub(one, epsilonConst);
    output = tf.clipByValue(output, epsilonConst, oneMinusEpsilonConst);

    // Compute cross entropy from probabilities.
    Operand bce = tf.math.mul(target, tf.math.log(tf.math.add(output, epsilonConst)));
    bce =
        tf.math.add(
            bce,
            tf.math.mul(
                tf.math.sub(one, target),
                tf.math.log(tf.math.add(tf.math.sub(one, output), epsilonConst))));
    Operand result = tf.math.neg(bce);
    return result;
  }

  public static Operand categorical_crossentropy(
      Ops tf, Operand target, Operand output, boolean fromLogits) {
    return categorical_crossentropy(tf, target, output, fromLogits, -1);
  }

  public static Operand categorical_crossentropy(
      Ops tf, Operand target, Operand output, boolean fromLogits, int axis) {

    if (fromLogits) {
      return softmax_cross_entropy_with_logits(tf, target, output);
    }
    if (!(output instanceof Variable) && (!tf.scope().env().isEager())) {
      // TODO output = backtrackIdentity(output); doesn't seem to work with Java version.
      if (output.op().type().equals("Softmax")) {
        assert output.op().numOutputs() == 1;
        output = output.op().output(0);
        Operand op = softmax_cross_entropy_with_logits(tf, target, output);
        return op;
      }
    }
    DataType dtype = output.asOutput().dataType();
    Operand one = one(tf, dtype);
    Operand epsilonConst = K.epsilonConstant(tf, dtype);
    Operand oneMinusepsilonConst = tf.math.sub(one, epsilonConst);
    output =
        tf.math.div(
            output, tf.reduceSum(output, tf.constant(axis), ReduceSum.keepDims(Boolean.TRUE)));
    output = tf.clipByValue(output, epsilonConst, oneMinusepsilonConst);

    // Compute cross entropy from probabilities.
    Operand cce =
        tf.reduceSum(
            tf.math.mul(target, tf.math.log(output)),
            tf.constant(axis),
            ReduceSum.keepDims(Boolean.FALSE));
    return tf.math.neg(cce);
  }

  public static Operand flatten(Ops tf, Operand t) {
    Shape shape = Shape.of(1L);
    return tf.reshape(t, tf.constant(shape));
  }

  public static Operand sparse_categorical_crossentropy(
      Ops tf, Operand target, Operand output, boolean fromLogits, int axis) {
    DataType dType = output.asOutput().dataType();
    if (!(output instanceof Variable) && (!tf.scope().env().isEager())) {
      // TODO output = backtrackIdentity(output); doesn't seem to work with Java version.
      if (output.op().type().equals("Softmax")) {
        // assert output.op().numOutputs() == 1;
        // When softmax activation function is used for output operation, we
        // use logits from the softmax function directly to compute loss in order
        // to prevent collapsing zero when training.
        // TODO assert len(output.op.inputs) == 1
        // TODO output = output.op.inputs[0]
        fromLogits = true;
      }
    }
    if (!fromLogits) {
      Operand epsilonConst = epsilonConstant(tf, dType);
      Operand one = one(tf, dType);
      Operand oneMinusEpsilonConst = tf.math.sub(one, epsilonConst);
      output = tf.clipByValue(output, epsilonConst, oneMinusEpsilonConst);
      output = tf.math.log(output);
    }
    Shape outputShape = output.asOutput().shape();
    int outputRank = outputShape.numDimensions();
    axis %= outputRank;
    if (axis < 0) {
      axis += outputRank;
    }
    if (axis != outputRank - 1) {
      int[] axisNew = moveAxisToEnd(axis, outputRank);
      output = tf.linalg.transpose(output, tf.constant(axisNew));
    }

    target = tf.dtypes.cast(target, TInt64.DTYPE);
    // TODO Try to adjust the shape so that rank of labels = rank of logits - 1.
    outputShape = output.asOutput().shape();
    Shape targetShape = target.asOutput().shape();
    int targetRank = targetShape.numDimensions();

    boolean updateShape = targetRank != outputRank - 1;
    if (updateShape) { // TODO check to see if this is right
      target = tf.reshape(target, tf.constant(-1L)); // flatten
      output =
          tf.reshape(
              output,
              tf.constant(new long[] {-1L, outputShape.size(outputShape.numDimensions() - 1)}));
    }

    // call nn.nn.sparse_softmax_cross_entropy_with_logits_v2
    Operand loss = NN.sparse_softmax_cross_entropy_with_logits(tf, target, output);
    if (updateShape && outputRank >= 3) {
      long[] dims = outputShape.asArray();
      long[] newDims = new long[dims.length - 1];
      System.arraycopy(dims, 0, newDims, 0, newDims.length);
      loss = tf.reshape(loss, tf.constant(newDims));
    }
    return loss;
  }

  private static int[] allAxis(Operand op) {
    int rank = op.asOutput().shape().numDimensions();
    int[] ranks = new int[rank];
    for (int i = 0; i < rank; i++) {
      ranks[i] = i;
    }
    return ranks;
  }

  public static Operand allAxis(Ops tf, Operand op) {
    int[] ranks = allAxis(op);
    return tf.constant(ranks);
  }

  // TODO shouldn't these be in tensorflow itself under nn?
  private static <T extends TType, U extends TNumber> Operand moveDimToEnd(
      Ops tf, Operand tensor, int dim_index, Operand rank) {
    Operand one = one(tf, TInt32.DTYPE);
    List<Operand<T>> concatList =
        Arrays.asList(
            tf.range(tf.constant(dim_index), one, one),
            tf.range(tf.constant(dim_index + 1), rank, one));
    return tf.linalg.transpose(
        tensor,
        (Operand<U>) tf.concat((Iterable<Operand<T>>) concatList, (Operand<U>) tf.constant(0)));
  }

  private static <T extends TType, U extends TNumber> Operand flattenOuterDims(
      Ops tf, Operand logits) {
    Operand zero = zero(tf, TInt64.DTYPE);
    Operand one = one(tf, TInt64.DTYPE);
    Operand minusOne = tf.constant(-1);

    // Shape logitsShape = logits.asOutput().shape();
    // long lastDimSize = logitsShape.size(logitsShape.numDimensions()-1);
    // if(!tf.scope().env().isEager()) {
    Shape shape = logits.asOutput().shape();
    int ndims = shape.numDimensions();
    if (!shape.hasUnknownDimension()) {
      long product = 1L;
      boolean productValid = true;
      for (int i = ndims - 2; i >= 0; i--) {
        long d = shape.size(i);
        if (d == Shape.UNKNOWN_SIZE) {
          productValid = false;
          break;
        }
        product *= d;
      }
      if (productValid) {
        Shape outputShape = Shape.of(product, shape.size(ndims - 1));
        return tf.reshape(logits, tf.constant(outputShape.asArray()));
      }
    }
    // }

    Operand rank = tf.dtypes.cast(tf.rank(logits), TInt64.DTYPE);
    Operand rankMinusOne = tf.math.sub(rank, one);

    Operand last_dim_size = tf.slice(tf.shape(logits), rankMinusOne, tf.constant(1));
    Operand concat =
        tf.concat(Arrays.asList(tf.constant(new int[] {-1}), last_dim_size), tf.constant(0));
    return tf.reshape(zero, concat);
  }

  private static int[] moveAxisToEnd(int axis, int outputRank) {
    int[] axisNew = new int[outputRank];
    for (int i = 0; i < axis; i++) {
      axisNew[i] = i;
    }
    for (int i = axis + 1; i < outputRank; i++) {
      axisNew[i - 1] = i;
    }
    axisNew[outputRank - 1] = axis;
    return axisNew;
  }

  // TODO, maybe part of Shape ??
  private static boolean shapeIsCompatible(Shape a, Shape b) {
    if (a.numDimensions() != b.numDimensions()) {
      return false;
    }
    for (int i = 0; i < a.numDimensions(); i++) {
      long aSize = a.size(i);
      long bSize = b.size(i);
      if (aSize != Shape.UNKNOWN_SIZE && bSize != Shape.UNKNOWN_SIZE && aSize != bSize) {
        return false;
      }
    }
    return true;
  }

  // TODO these are "nn" ops
  public static Operand softmax_cross_entropy_with_logits(Ops tf, Operand labels, Operand logits) {
    return softmax_cross_entropy_with_logits(tf, labels, logits, -1);
  }

  public static Operand softmax_cross_entropy_with_logits(
      Ops tf, Operand labels, Operand logits, int axis) {
    axis = axis % logits.asOutput().shape().numDimensions();
    if (axis < 0) {
      axis += logits.asOutput().shape().numDimensions();
    }

    Operand minusOne = tf.constant(-1);
    Operand precise_logits = logits;
    Operand one = tf.constant(1L);

    boolean convertToFloat32 =
        logits.asOutput().dataType() == TFloat16.DTYPE
            || logits.asOutput().dataType() == TBfloat16.DTYPE;
    if (convertToFloat32) {
      precise_logits = tf.dtypes.cast(logits, TFloat32.DTYPE);
    }
    DataType dtype = precise_logits.asOutput().dataType();
    labels = tf.dtypes.cast(labels, dtype);
    Operand inputRank = tf.dtypes.cast(tf.rank(precise_logits), TInt64.DTYPE);
    Operand inputRankMinusOne = tf.dtypes.cast(tf.math.sub(inputRank, one), TInt64.DTYPE);
    Shape shape = logits.asOutput().shape();

    // Move the dim to the end if dim is not the last dimension.
    if (axis != -1 && axis != precise_logits.asOutput().shape().numDimensions() - 1) {
      precise_logits = moveDimToEnd(tf, precise_logits, axis, inputRank);
      labels = moveDimToEnd(tf, labels, axis, inputRank);
    }

    Shape inputShape = precise_logits.asOutput().shape();
    precise_logits = flattenOuterDims(tf, precise_logits);
    labels = flattenOuterDims(tf, labels);
    SoftmaxCrossEntropyWithLogits smax =
        tf.nn.softmaxCrossEntropyWithLogits(precise_logits, labels);
    Operand cost = smax.loss();
    Operand outputShape =
        tf.slice(
            tf.constant(inputShape.asArray()),
            tf.constant(new long[] {0}),
            tf.constant(new long[] {inputShape.numDimensions() - 1}));
    cost = tf.reshape(cost, outputShape);
    if (tf.scope().env().isGraph() && !shape.hasUnknownDimension()) {
      long[] array = shape.asArray();
      long[] newArray = new long[array.length - 1];
      if (axis < 0) {
        axis = shape.numDimensions() + axis;
      }
      for (int i = 0; i < axis; i++) {
        newArray[i] = shape.size(i);
      }
      for (int i = axis + 1; i < shape.numDimensions(); i++) {
        newArray[i - 1] = shape.size(i);
      }
      Shape newShape = Shape.of(newArray);
      cost = tf.reshape(cost, tf.constant(newShape.asArray()));
    }

    if (convertToFloat32) {
      cost = tf.dtypes.cast(cost, logits.asOutput().dataType());
    }
    return cost;
  }

  public static <T extends TType> Operand<T> map(
      Operand<T> input, Function<Operand<T>, Operand<T>> mapFunc) {
    return null;
  }

  public static long[] concatenate(long first, long... remaining) {
    long[] dims = new long[remaining.length + 1];
    System.arraycopy(remaining, 0, dims, 1, remaining.length);
    dims[0] = first;
    return dims;
  }

  private static Map<ExecutionEnvironment, Map<String, Integer>> uidMap = new HashMap<>();

  /**
   * Associates a string prefix with an integer counter in a TensorFlow graph.
   *
   * <p>Example:
   *
   * <pre>
   * get_uid('dense')
   * 1
   * get_uid('dense')
   * 2
   * </pre>
   *
   * @param tf the TensorFlow Ops
   * @param prefix String prefix to index.
   * @return Unique integer ID.
   */
  public static int getUid(Ops tf, String prefix) {
    ExecutionEnvironment env = tf.scope().env();
    Map<String, Integer> uids = uidMap.get(env);
    if (uids == null) {
      uids = new HashMap<>();
      uidMap.put(env, uids);
    }
    Integer id = uids.get(prefix);
    if (id == null) {
      id = 0;
    } else {
      id++;
    }

    uids.put(prefix, id);
    return id;
  }

  /**
   * returns the larger DataType between the two.
   *
   * @param a the first DataType to compare
   * @param b the second DataType to compare
   * @return the wider DataType
   */
  public DataType wider(DataType a, DataType b) {
    return a.byteSize() < b.byteSize() ? b : a;
  }

  /**
   * returns the smaller DataType between the two.
   *
   * @param a the first DataType to compare
   * @param b the second DataType to compare
   * @return the smaller DataType
   */
  public DataType narrower(DataType a, DataType b) {
    return a.byteSize() > b.byteSize() ? b : a;
  }

  public <T extends TNumber> NdArraySequence getTensorValue(Ops tf, Operand<T> operand) {
    DataType dtype = operand.asOutput().dataType();
    if (tf.scope().env().isGraph()) {
      try (Session session = new Session((Graph) tf.scope().env())) {
        if (dtype.equals(TInt32.DTYPE)) {
          try (Tensor<TInt32> result =
              session.runner().fetch(operand).run().get(0).expect(TInt32.DTYPE)) {
            return result.data().scalars();
          }
        } else if (dtype.equals(TInt64.DTYPE)) {
          try (Tensor<TInt64> result =
              session.runner().fetch(operand).run().get(0).expect(TInt64.DTYPE)) {
            return result.data().scalars();
          }
        } else if (dtype.equals(TUint8.DTYPE)) {
          try (Tensor<TUint8> result =
              session.runner().fetch(operand).run().get(0).expect(TUint8.DTYPE)) {
            return result.data().scalars();
          }
        } else if (dtype.equals(TBfloat16.DTYPE)) {
          try (Tensor<TBfloat16> result =
              session.runner().fetch(operand).run().get(0).expect(TBfloat16.DTYPE)) {
            return result.data().scalars();
          }
        } else if (dtype.equals(TFloat16.DTYPE)) {
          try (Tensor<TFloat16> result =
              session.runner().fetch(operand).run().get(0).expect(TFloat16.DTYPE)) {
            return result.data().scalars();
          }
        } else if (dtype.equals(TFloat32.DTYPE)) {
          try (Tensor<TFloat32> result =
              session.runner().fetch(operand).run().get(0).expect(TFloat32.DTYPE)) {
            return result.data().scalars();
          }
        } else if (dtype.equals(TFloat64.DTYPE)) {
          try (Tensor<TFloat64> result =
              session.runner().fetch(operand).run().get(0).expect(TFloat64.DTYPE)) {
            return result.data().scalars();
          }
        } else {
          return null;
        }
      }
    } else {
      try (EagerSession session = EagerSession.create()) {
        if (dtype.equals(TInt32.DTYPE)) {
          return ((Operand<TInt32>) operand).data().scalars();
        } else if (dtype.equals(TInt64.DTYPE)) {
          return ((Operand<TInt64>) operand).data().scalars();
        } else if (dtype.equals(TUint8.DTYPE)) {
          return ((Operand<TUint8>) operand).data().scalars();
        } else if (dtype.equals(TBfloat16.DTYPE)) {
          return ((Operand<TBfloat16>) operand).data().scalars();
        } else if (dtype.equals(TFloat16.DTYPE)) {
          return ((Operand<TFloat16>) operand).data().scalars();
        } else if (dtype.equals(TFloat32.DTYPE)) {
          return ((Operand<TFloat32>) operand).data().scalars();
        } else if (dtype.equals(TFloat64.DTYPE)) {
          return ((Operand<TFloat64>) operand).data().scalars();
        } else {
          return null;
        }
      }
    }
  }

  /**
   * Squeeze or expand last dimension if needed. 1. Squeezes last dim of `y_pred` or `y_true` if
   * their rank differs by 1 (using `confusion_matrix.remove_squeezable_dimensions`). 2. Squeezes or
   * expands last dim of `sample_weight` if its rank differs by 1 from the new rank of `y_pred`. If
   * `sample_weight` is scalar, it is kept scalar.
   *
   * @param tf the TensorVlow Ops
   * @param yPred Predicted values, a `Tensor` of arbitrary dimensions.
   * @param yTrue Optional label `Tensor` whose dimensions match `y_pred`.
   * @return Tuple of `y_pred`, `y_true` and `sample_weight`. Each of them possibly has the last
   *     dimension squeezed, `sample_weight` could be extended by one dimension. If `sample_weight`
   *     is null, (y_pred, y_true) is returned.
   */
  public static Tuple squeezeOrExpandDimensions(Ops tf, Operand yTrue, Operand yPred) {
    return squeezeOrExpandDimensions(tf, yTrue, yPred, null);
  }

  /**
   * Squeeze or expand last dimension if needed. 1. Squeezes last dim of `y_pred` or `y_true` if
   * their rank differs by 1 (using `confusion_matrix.remove_squeezable_dimensions`). 2. Squeezes or
   * expands last dim of `sample_weight` if its rank differs by 1 from the new rank of `y_pred`. If
   * `sample_weight` is scalar, it is kept scalar.
   *
   * @param tf the TensorVlow Ops
   * @param yPred Predicted values, a `Tensor` of arbitrary dimensions.
   * @param yTrue Optional label `Tensor` whose dimensions match `y_pred`.
   * @param sampleWeight Optional weight scalar or `Tensor` whose dimensions match `y_pred`.
   * @return Tuple of `y_pred`, `y_true` and `sample_weight`. Each of them possibly has the last
   *     dimension squeezed, `sample_weight` could be extended by one dimension. If `sample_weight`
   *     is null, (y_pred, y_true) is returned.
   */
  public static Tuple squeezeOrExpandDimensions(
      Ops tf, Operand yTrue, Operand yPred, Operand sampleWeight) {
    Tuple tuple = new Tuple(yTrue, yPred);
    Shape ypredShape = yPred.asOutput().shape();
    long ypredRank = ypredShape.numDimensions();

    if (yTrue != null) {
      Shape ytrueShape = yTrue.asOutput().shape();
      long ytrueRank = ytrueShape.numDimensions();
      if (ytrueRank != Shape.UNKNOWN_SIZE && ypredRank != Shape.UNKNOWN_SIZE) {
        // Use static rank for `y_true` and `y_pred`.
        if (ypredRank - ytrueRank != 1 || ypredShape.size(-1) == 1) {
          // y_true, y_pred = confusion_matrix.remove_squeezable_dimensions(y_true, y_pred)
          tuple = ConfusionMatrix.removeSqueezableDimensions(tf, yTrue, yPred);
        }
      } else { // use dynamic rank
        tuple = ConfusionMatrix.removeSqueezableDimensions(tf, yTrue, yPred);
      }
    }
    if (sampleWeight == null) {
      return tuple;
    }
    Shape weightsShape = sampleWeight.asOutput().shape();
    long weightsRank = weightsShape.numDimensions();
    if (weightsRank == 0) { // scalar
      return new Tuple(yTrue, yPred, sampleWeight);
    }

    if (ypredRank != Shape.UNKNOWN_SIZE && weightsRank != Shape.UNKNOWN_SIZE) {

      if (weightsRank - ypredRank == 1) {
        sampleWeight = tf.squeeze(sampleWeight);
      } else if (ypredRank - weightsRank == 1) {
        sampleWeight = tf.expandDims(sampleWeight, tf.constant(-1L));
      }
      return new Tuple(yTrue, yPred, sampleWeight);
    }
    // Use dynamic rank.
    Operand weightsRankTensor = tf.rank(sampleWeight);
    Operand rankDiff = tf.math.sub(weightsRankTensor, tf.rank(yPred));
    sampleWeight =
        tf.select(
            tf.math.equal(weightsRankTensor, tf.constant(0)),
            sampleWeight,
            maybeAdjustWeights(tf, sampleWeight, rankDiff));
    return new Tuple(yTrue, yPred, sampleWeight);
  }

  private static Operand maybeAdjustWeights(Ops tf, Operand sampleWeight, Operand rankDiff) {
    return tf.select(
        tf.math.equal(rankDiff, tf.constant(1)),
        tf.squeeze(sampleWeight, Squeeze.axis(Arrays.asList(-1L))),
        maybeExpandWeights(tf, sampleWeight, rankDiff));
  }

  private static Operand maybeExpandWeights(Ops tf, Operand sampleWeight, Operand rankDiff) {
    return tf.select(
        tf.math.equal(rankDiff, tf.constant(-1)),
        tf.expandDims(sampleWeight, tf.constant(-1)),
        sampleWeight);
  }
}
