package org.tensorflow.op.nn;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.core.*;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.linalg.Transpose;
import org.tensorflow.op.math.Sub;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

import java.util.Arrays;
import java.util.List;

@Operator(group = "nn")
public class SoftmaxCrossEntropyWithLogits {

  /**
   * Computes softmax cross entropy between <code>logits</code> and <code>labels</code>.
   *
   * <p>Measures the probability error in discrete classification tasks in which the classes are
   * mutually exclusive (each entry is in exactly one class). For example, each CIFAR-10 image is
   * labeled with one and only one label: an image can be a dog or a truck, but not both.
   *
   * <p><b>NOTE:</b>
   *
   * <p>While the classes are mutually exclusive, their probabilities need not be. All that is
   * required is that each row of <code>labels</code> is a valid probability distribution. If they
   * are not, the computation of the gradient will be incorrect.
   *
   * <p>If using exclusive <code>labels</code> (wherein one and only one class is true at a time),
   * see {@link org.tensorflow.op.NnOps#sparseSoftmaxCrossEntropyWithLogits}
   *
   * <p>Usage:
   *
   * <pre>
   *   Operand&lt;TFloat32&gt; logits =
   *       tf.constant(new float[][] {{4.0F, 2.0F, 1.0F}, {0.0F, 5.0F, 1.0F}} );
   *   Operand&lt;TFloat32&gt; labels =
   *       tf.constant(new float[][] {{1.0F, 0.0F, 0.0F}, {0.0F, 0.8F, 0.2F}} );
   *   Operand&lt;TFloat32&gt; output =
   *       tf.nn.softmaxCrossEntropyWithLogits(labels, logits, -1);
   *   // output Shape = [2]
   *   // dataType = FLOAT (1)
   *   // values { 0.169846, 0.824745 }
   * </pre>
   *
   * <p>Backpropagation will happen into both <code>logits</code> and <code>labels</code>. To
   * disallow backpropagation into <code>labels</code>, pass label tensors through <code>
   * tf.stopGradient</code> before feeding it to this function.
   *
   * @param scope current scope
   * @param labels Each vector along the class dimension should hold a valid probability
   *     distribution e.g. for the case in which labels are of shape <code>[batch_size, num_classes]
   *     </code>, each row of <code>labels[i]</code> must be a valid probability distribution.
   * @param logits Per-label activations, typically a linear output. These activation energies are
   *     interpreted as unnormalized log probabilities.
   * @param axis The class dimension. -1 is the last dimension.
   * @param <U> the data type of the <code>logits</code>
   * @param <T> the number type of the operands
   * @return the softmax cross entropy loss. Its type is the same as <code>logits</code> and its
   *     shape is the same as <code>labels</code> except that it does not have the last dimension of
   *     <code>labels</code>.
   */
  @Endpoint(name = "softmaxCrossEntropyWithLogits")
  public static <U extends TType, T extends TNumber> Operand<T> softmaxCrossEntropyWithLogits(
      Scope scope, Operand<T> labels, Operand<U> logits, int axis) {
    axis = axis % logits.asOutput().shape().numDimensions();
    if (axis < 0) {
      axis += logits.asOutput().shape().numDimensions();
    }

    Operand<? extends TNumber> preciseLogits =
        (Operand<? extends TNumber>)
            logits; // cannot use generics cause logits of bool gets cast to TFloat32

    boolean convertToFloat32 =
        logits.asOutput().dataType() == TFloat16.DTYPE
            || logits.asOutput().dataType() == TBfloat16.DTYPE;
    if (convertToFloat32) {
      preciseLogits = Cast.create(scope, logits, TFloat32.DTYPE);
    }
    /* cannot use generics on DataType because preciseLogits may have been cast. */
    DataType<? extends TType> dtype = preciseLogits.asOutput().dataType();
    Operand castLabels = Cast.create(scope, labels, dtype);
    Operand<TInt64> inputRank = Cast.create(scope, Rank.create(scope, preciseLogits), TInt64.DTYPE);
    Shape shape = logits.asOutput().shape();

    // Move the dim to the end if dim is not the last dimension.
    if (axis != -1 && axis != preciseLogits.asOutput().shape().numDimensions() - 1) {
      preciseLogits = moveDimToEnd(scope, preciseLogits, axis, inputRank);
      castLabels = moveDimToEnd(scope, castLabels, axis, inputRank);
    }

    Shape inputShape = preciseLogits.asOutput().shape();
    preciseLogits = flattenOuterDims(scope, preciseLogits);
    castLabels = flattenOuterDims(scope, castLabels);

    org.tensorflow.op.nn.raw.SoftmaxCrossEntropyWithLogits smax =
        org.tensorflow.op.nn.raw.SoftmaxCrossEntropyWithLogits.create(
            scope, preciseLogits, castLabels);
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
        return Reshape.create(
            scope, logits, Constant.vectorOf(scope, new long[] {product, shape.size(-1)}));
      }
    }

    Operand<TInt64> rank = Cast.create(scope, Rank.create(scope, logits), TInt64.DTYPE);
    Operand<TInt64> rankMinusOne = Sub.create(scope, rank, one);

    Operand<TInt64> lastDimSize =
        Slice.create(
            scope,
            org.tensorflow.op.core.Shape.create(scope, logits, TInt64.DTYPE),
            rankMinusOne,
            one);
    Operand<TInt64> concat =
        Concat.create(
            scope,
            Arrays.asList(Constant.arrayOf(scope, -1L), lastDimSize),
            Constant.scalarOf(scope, 0));
    return Reshape.create(scope, logits, concat);
  }

  /**
   * Move the dim to the end if dimIndex is not the last dimension.
   *
   * @param scope The TensorFlow Scope
   * @param input the input to reshape
   * @param dimIndex the index to move
   * @param rank the number of Dimensions in the tensor
   * @param <T> the data type of the tensor.
   * @param <U> the data type of the rank
   * @return the reshaped input
   */
  private static <T extends TType, U extends TNumber> Operand<T> moveDimToEnd(
      Scope scope, Operand<T> input, int dimIndex, Operand<U> rank) {
    DataType<? extends TNumber> rankDType = rank.asOutput().dataType();
    Operand one = Cast.create(scope, Constant.scalarOf(scope, 1), rankDType);
    List<Operand<U>> concatList =
        Arrays.asList(
            Range.create(
                scope, Cast.create(scope, Constant.scalarOf(scope, dimIndex), rankDType), one, one),
            Range.create(
                scope,
                Cast.create(scope, Constant.scalarOf(scope, dimIndex + 1), rankDType),
                one,
                one));
    return Transpose.create(
        scope, input, Concat.create(scope, concatList, Constant.scalarOf(scope, 0)));
  }
}
