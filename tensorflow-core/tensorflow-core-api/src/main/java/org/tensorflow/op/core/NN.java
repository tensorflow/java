package org.tensorflow.op.core;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.math.*;
import org.tensorflow.op.nn.raw.SoftmaxCrossEntropyWithLogits;
import org.tensorflow.op.nn.raw.SparseSoftmaxCrossEntropyWithLogits;
import org.tensorflow.types.*;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.types.family.TType;
import org.tensorflow.op.linalg.Transpose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Operator(group = "nn")
public abstract class NN {

  /**
   * Computes softmax cross entropy between `logits` and `labels`.
   *
   * <p>Measures the probability error in discrete classification tasks in which the classes are
   * mutually exclusive (each entry is in exactly one class). For example, each CIFAR-10 image is
   * labeled with one and only one label: an image can be a dog or a truck, but not both.
   *
   * <p>**NOTE:** While the classes are mutually exclusive, their probabilities need not be. All
   * that is required is that each row of `labels` is a valid probability distribution. If they are
   * not, the computation of the gradient will be incorrect.
   *
   * <p>If using exclusive `labels` (wherein one and only one class is true at a time), see
   * `sparse_softmax_cross_entropy_with_logits`.
   *
   * <p>Usage:
   *
   * <pre>
   *   >>> logits = [[4.0, 2.0, 1.0], [0.0, 5.0, 1.0]]
   *   >>> labels = [[1.0, 0.0, 0.0], [0.0, 0.8, 0.2]]
   *   >>> tf.nn.softmax_cross_entropy_with_logits(labels=labels, logits=logits)
   *   <tf.Tensor: shape=(2,), dtype=float32,
   *   numpy=array([0.16984604, 0.82474494], dtype=float32)>
   * </pre>
   *
   * <p>Backpropagation will happen into both `logits` and `labels`. To disallow backpropagation
   * into `labels`, pass label tensors through `tf.stop_gradient` before feeding it to this
   * function.
   *
   * @param scope current scope
   * @param labels Each vector along the class dimension should hold a valid probability
   *     distribution e.g. for the case in which labels are of shape `[batch_size, num_classes]`,
   *     each row of `labels[i]` must be a valid probability distribution.
   * @param logits Per-label activations, typically a linear output. These activation energies are
   *     interpreted as unnormalized log probabilities.
   * @param axis The class dimension. -1 is the last dimension.
   * @param <U> the data type of the logits
   * @param <T> the number type of the operands
   * @return the softmax cross entropy loss. Its type is the same as `logits` and its shape is the
   *     same as `labels` except that it does not have the last dimension of `labels`.
   */
  @Endpoint(name = "softmaxCrossEntropyWithLogits")
  public static <U extends TType, T extends TNumber> Operand<T> softmaxCrossEntropyWithLogits(
      Scope scope, Operand<T> labels, Operand<U> logits, int axis) {
    axis = axis % logits.asOutput().shape().numDimensions();
    if (axis < 0) {
      axis += logits.asOutput().shape().numDimensions();
    }

    Operand precise_logits =
        logits; // cannot use generics cause logits of bool gets cast to TFloat32

    boolean convertToFloat32 =
        logits.asOutput().dataType() == TFloat16.DTYPE
            || logits.asOutput().dataType() == TBfloat16.DTYPE;
    if (convertToFloat32) {
      precise_logits = Cast.create(scope, logits, TFloat32.DTYPE);
    }
    /* cannot use generics on DataType because precis_logits may have been cast. */
    DataType dtype = precise_logits.asOutput().dataType();
    labels = Cast.create(scope, labels, dtype);
    Operand<TInt64> inputRank =
        Cast.create(scope, Rank.create(scope, precise_logits), TInt64.DTYPE);
    Shape shape = logits.asOutput().shape();

    // Move the dim to the end if dim is not the last dimension.
    if (axis != -1 && axis != precise_logits.asOutput().shape().numDimensions() - 1) {
      precise_logits = moveDimToEnd(scope, precise_logits, axis, inputRank);
      labels = moveDimToEnd(scope, labels, axis, inputRank);
    }

    Shape inputShape = precise_logits.asOutput().shape();
    precise_logits = flattenOuterDims(scope, precise_logits);
    labels = flattenOuterDims(scope, labels);
    SoftmaxCrossEntropyWithLogits<T> smax =
        SoftmaxCrossEntropyWithLogits.create(scope, precise_logits, labels);
    /* cannot use generic on cost, because cost may be recast later. */
    Operand cost = smax.loss();
    Operand<TInt64> outputShape =
        Slice.create(
            scope,
            Constant.vectorOf(scope, inputShape.asArray()),
            Constant.vectorOf(scope, new long[] {0}),
            Constant.vectorOf(scope, new long[] {inputShape.numDimensions() - 1}));
    cost = Reshape.create(scope, cost, outputShape);
    if (scope.env().isGraph() && !shape.hasUnknownDimension()) {
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
      cost = Reshape.create(scope, cost, Constant.vectorOf(scope, newShape.asArray()));
    }

    if (convertToFloat32) {
      cost = Cast.create(scope, cost, logits.asOutput().dataType());
    }
    return cost;
  }

  /**
   * Computes sparse softmax cross entropy between `logits` and `labels`.
   *
   * @param scope current scope
   * @param labels `Tensor` of shape `[d_0, d_1, ..., d_{r-1}]` (where `r` is rank of `labels` and
   *     result) and dtype `int32` or `int64`. Each entry in `labels` must be an index in `[0,
   *     num_classes)`. Other values will raise an exception when this op is run on CPU, and return
   *     `NaN` for corresponding loss and gradient rows on GPU.
   * @param logits Per-label activations (typically a linear output) of shape `[d_0, d_1, ...,
   *     d_{r-1}, num_classes]` and dtype `float16`, `float32`, or `float64`. These activation
   *     energies are interpreted as unnormalized log probabilities.
   * @return A `Tensor` of the same shape as `labels` and of the same type as `logits` with the
   *     softmax cross entropy loss.
   */
  @Endpoint(name = "sparseSoftmaxCrossEntropyWithLogits")
  public static <T extends TNumber, U extends TNumber> Operand sparseSoftmaxCrossEntropyWithLogits(
      Scope scope, Operand<T> labels, Operand<U> logits) {
    // assert shapeIsCompatible(labels.asOutput().shape(), logits.asOutput().shape()):
    //        String.format("Shapes %s and %s are incompatible",
    //                labels.asOutput().shape(), logits.asOutput().shape());
    scope = scope.withSubScope("SparseSoftmaxCrossEntropyWithLogits");
    /** cannot use generics on precise_logits as it may be recast later */
    Operand precise_logits = logits;
    boolean convertToFloat32 =
        logits.asOutput().dataType() == TFloat16.DTYPE
            || logits.asOutput().dataType() == TBfloat16.DTYPE;
    if (convertToFloat32) {
      precise_logits = Cast.create(scope, logits, TFloat32.DTYPE);
    }
    Shape labelsStaticShape = labels.asOutput().shape();
    org.tensorflow.op.core.Shape<TInt32> labelsShape =
        org.tensorflow.op.core.Shape.create(scope, labels);
    Shape logitsShape = logits.asOutput().shape();
    Shape logitsShortened = logitsShape.take(logitsShape.numDimensions() - 1);

    boolean staticShapesFullyDefined =
        !labelsStaticShape.hasUnknownDimension() && !logitsShortened.hasUnknownDimension();
    if (logitsShape.numDimensions() == 0) {
      throw new IllegalArgumentException(
          String.format("Logits cannot be scalars - received shape %s.", logitsShape));
    }
    if (!logitsShape.hasUnknownDimension()
        && !labelsStaticShape.hasUnknownDimension()
        && labelsStaticShape.numDimensions() != logitsShape.numDimensions() - 1) {
      throw new IllegalArgumentException(
          String.format(
              "Rank mismatch: Rank of labels (received %s) should equal rank of logits minus 1 (received %s).",
              labelsStaticShape.toString(), logitsShape.toString()));
    }

    if (staticShapesFullyDefined && !labelsStaticShape.equals(logitsShortened)) {
      throw new IllegalArgumentException(
          String.format(
              "Shape mismatch: The shape of labels (received %s) "
                  + "should equal the shape of logits except for the last "
                  + "dimension (received %s).",
              labelsStaticShape.toString(), logitsShape.toString()));
    }
    // Check if no reshapes are required.
    if (logitsShape.numDimensions() == 2) {
      SparseSoftmaxCrossEntropyWithLogits smax =
          SparseSoftmaxCrossEntropyWithLogits.create(scope, precise_logits, labels);
      Operand loss = smax.loss();
      if (logits.asOutput().dataType() == TFloat16.DTYPE) {
        loss = Cast.create(scope, loss, TFloat16.DTYPE);
      }
      return loss;
    }

    List<Op> shapeChecks = new ArrayList<>();

    if (!staticShapesFullyDefined) {
      shapeChecks.add(
          AssertThat.create(
              scope,
              Equal.create(
                  scope,
                  org.tensorflow.op.core.Shape.create(scope, labels),
                  Shapes.take(
                      scope,
                      org.tensorflow.op.core.Shape.create(scope, logits),
                      Constant.scalarOf(scope, -1))),
              Collections.singletonList(
                  Constant.scalarOf(
                      scope,
                      "Shape mismatch: The shape of labels  "
                          + "should equal the shape of logits except for the last "
                          + "dimension "))));
    }

    // Reshape logits to 2 dim, labels to 1 dim.
    long numClassses = logitsShape.size(logitsShape.numDimensions() - 1);

    precise_logits =
        Reshape.create(
            scope, precise_logits, Constant.vectorOf(scope, new long[] {-1, numClassses}));
    labels = Reshape.create(scope, labels, Constant.scalarOf(scope, -1));
    scope.withControlDependencies(shapeChecks);
    SparseSoftmaxCrossEntropyWithLogits smax =
        SparseSoftmaxCrossEntropyWithLogits.create(scope, precise_logits, labels);
    Operand cost = smax.loss();
    cost = Reshape.create(scope, cost, labelsShape);
    if (logits.asOutput().dataType() == TFloat16.DTYPE) {
      cost = Cast.create(scope, cost, TFloat16.DTYPE);
    }
    return cost;
  }

  /**
   * Computes sigmoid cross entropy given `logits`.
   *
   * <p>Measures the probability error in discrete classification tasks in which each class is
   * independent and not mutually exclusive. For instance, one could perform multilabel
   * classification where a picture can contain both an elephant and a dog at the same time.
   *
   * <p>For brevity, let `x = logits`, `z = labels`. The logistic loss is
   *
   * <pre>
   *     z * -log(sigmoid(x)) + (1 - z) * -log(1 - sigmoid(x))
   *     = z * -log(1 / (1 + exp(-x))) + (1 - z) * -log(exp(-x) / (1 + exp(-x)))
   *     = z * log(1 + exp(-x)) + (1 - z) * (-log(exp(-x)) + log(1 + exp(-x)))
   *     = z * log(1 + exp(-x)) + (1 - z) * (x + log(1 + exp(-x))
   *     = (1 - z) * x + log(1 + exp(-x))
   *     = x - x * z + log(1 + exp(-x))
   * </pre>
   *
   * <p>For x < 0, to avoid overflow in exp(-x), we reformulate the above
   *
   * <pre>
   *      x - x * z + log(1 + exp(-x))
   *      = log(exp(x)) - x * z + log(1 + exp(-x))
   *      = - x * z + log(1 + exp(x))
   * </pre>
   *
   * <p>Hence, to ensure stability and avoid overflow, the implementation uses this equivalent
   * formulation
   *
   * <pre>
   *     max(x, 0) - x * z + log(1 + exp(-abs(x)))
   * </pre>
   *
   * <p>`logits` and `labels` must have the same type and shape.
   *
   * @param scope The TensorFlow scope
   * @param labels the labels
   * @param logits the logits of type float32 or float64
   * @param <T> the type of labels and logits
   * @return the component-wise logistic losses.
   */
  @Endpoint(name = "sigmoidCrossEntropyWithLogits")
  public static <T extends TNumber> Operand<T> sigmoidCrossEntropyWithLogits(
      Scope scope, Operand<T> labels, Operand<T> logits) {
    if (labels.asOutput().shape().numDimensions() != logits.asOutput().shape().numDimensions())
      throw new IllegalArgumentException(
          String.format(
              "logits and labels must have the same shape (%s vs %s)",
              labels.asOutput().shape().toString(), logits.asOutput().shape()));
    Operand<T> zeros =
        Cast.create(scope, ZerosLike.create(scope, logits), logits.asOutput().dataType());
    Operand<TBool> cond = GreaterEqual.create(scope, logits, zeros);

    Operand<T> relu_logits = Select.create(scope, cond, logits, zeros);
    Operand<T> neg_abs_logits = Select.create(scope, cond, Neg.create(scope, logits), logits);
    return Add.create(
        scope,
        Sub.create(scope, relu_logits, Mul.create(scope, logits, labels)),
        Log1p.create(scope, Exp.create(scope, neg_abs_logits)));
  }

  /**
   * Flattens logits' outer dimensions and keep its last dimension.
   *
   * @param scope the TensorFlow scope
   * @param logits the logits
   * @param <T> the type of logits
   * @return the flattened logits
   */
  private static <T extends TNumber> Operand<T> flattenOuterDims(Scope scope, Operand<T> logits) {
    Operand<TInt64> one = Constant.scalarOf(scope, 1L);

    org.tensorflow.ndarray.Shape shape = logits.asOutput().shape();
    int ndims = shape.numDimensions();
    if (!shape.hasUnknownDimension()) {
      long product = 1L;
      boolean productValid = true;
      for (int i = ndims - 2; i >= 0; i--) {
        long d = shape.size(i);
        if (d == org.tensorflow.ndarray.Shape.UNKNOWN_SIZE) {
          productValid = false;
          break;
        }
        product *= d;
      }
      if (productValid) {
        org.tensorflow.ndarray.Shape outputShape = Shape.of(product, shape.size(ndims - 1));
        return Reshape.create(scope, logits, Constant.vectorOf(scope, outputShape.asArray()));
      }
    }

    Operand<TInt64> rank = Cast.create(scope, Rank.create(scope, logits), TInt64.DTYPE);
    Operand<TInt64> rankMinusOne = Sub.create(scope, rank, one);

    Operand<TInt64> last_dim_size =
        Slice.create(
            scope,
            org.tensorflow.op.core.Shape.create(scope, logits, TInt64.DTYPE),
            rankMinusOne,
            one);
    Operand<TInt64> concat =
        Concat.create(
            scope,
            Arrays.asList(Constant.vectorOf(scope, new long[] {-1}), last_dim_size),
            Constant.scalarOf(scope, 0));
    return Reshape.create(scope, logits, concat);
  }

  /**
   * Move the dim to the end if dim is not the last dimension.
   *
   * @param scope The TensorFlow Scope
   * @param input the input to reshape
   * @param dim_index the index to move
   * @param rank the number of Dimensions in the tensor
   * @param <T> the data type of the tensor.
   * @param <U> the data type of the rank
   * @return the reshaped input
   */
  private static <T extends TNumber, U extends TNumber> Operand<T> moveDimToEnd(
      Scope scope, Operand<T> input, int dim_index, Operand<U> rank) {
    DataType rankDType = rank.asOutput().dataType();
    Operand<U> one = Cast.create(scope, Constant.scalarOf(scope, 1), rankDType);
    List<Operand<T>> concatList =
        Arrays.asList(
            Range.create(
                scope,
                Cast.create(scope, Constant.scalarOf(scope, dim_index), rankDType),
                one,
                one),
            Range.create(
                scope,
                Cast.create(scope, Constant.scalarOf(scope, (dim_index + 1)), rankDType),
                rank,
                one));
    return Transpose.create(
        scope, input, Concat.create(scope, concatList, Constant.scalarOf(scope, 0)));
  }
}
