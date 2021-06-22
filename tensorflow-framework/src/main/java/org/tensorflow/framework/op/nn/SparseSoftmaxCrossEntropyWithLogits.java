package org.tensorflow.framework.op.nn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.AssertThat;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Reshape;
import org.tensorflow.op.core.Shapes;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.math.Equal;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

public class SparseSoftmaxCrossEntropyWithLogits {

  /**
   * Computes sparse softmax cross entropy between {@code logits} and {@code labels}.
   *
   * <p>Measures the probability error in discrete classification tasks in which the classes are
   * mutually exclusive (each entry is in exactly one class). For example, each CIFAR-10 image is
   * labeled with one and only one label: an image can be a dog or a truck, but not both.
   *
   * <p><b>NOTE:</b>
   *
   * <p>For this operation, the probability of a given label is considered exclusive. That is, soft
   * classes are not allowed, and the {@code labels} vector must provide a single specific index for
   * the true class for each row of {@code logits} (each minibatch entry). For soft softmax
   * classification with a probability distribution for each entry, {@link
   * org.tensorflow.op.NnOps#softmaxCrossEntropyWithLogits}.
   *
   * <p><b>WARNING:</b>
   *
   * <p>This op expects unscaled logits, since it performs a {@code softmax} on {@code logits }
   * internally for efficiency. Do not call this op with the output of {@code softmax}, as it will
   * produce incorrect results.
   *
   * <p>A common use case is to have logits of shape {@code [batchSize, numClasses]} and have labels
   * of shape {@code [batchSize]}, but higher dimensions are supported, in which case the {@code
   * dim}-th dimension is assumed to be of size {@code numClasses}. {@code logits} must have the
   * {@code dataType} of {@code TFloat16}, {@code TFloat32} , or {@code TFloat64}, and {@code
   * labels} must have the dtype of {@code TInt32} or {@code TInt64}.
   *
   * @param scope current scope
   * @param labels {@code Tensor} of shape {@code [d_0, d_1, ..., d_{r-1}]} (where {@code r } is
   *     rank of {@code labels} and result) and the dataType is {@code TInt32} or {@code TInt64}.
   *     Each entry in {@code labels} must be an index in {@code [0, numClasses)}. Other values will
   *     raise an exception when this op is run on CPU, and return {@code NaN} for corresponding
   *     loss and gradient rows on GPU.
   * @param logits Per-label activations (typically a linear output) of shape {@code [d_0, d_1, ...,
   *     d_{r-1}, numClasses]} and dataType of {@code TFloat16}, {@code TFloat32}, or {@code
   *     TFloat64}. These activation energies are interpreted as unnormalized log probabilities.
   * @param <U> the data type for the labels
   * @param <T> the data tyoe for the loss and logits.
   * @return the loss
   * @throws IllegalArgumentException If logits are scalars (need to have {@code rank >= 1}) or if
   *     the rank of the labels is not equal to the rank of the logits minus one.
   */
  @SuppressWarnings("unchecked")
  public static <T extends TNumber, U extends TNumber>
      Operand<T> sparseSoftmaxCrossEntropyWithLogits(
          Scope scope, Operand<U> labels, Operand<T> logits) {
    scope = scope.withSubScope("SparseSoftmaxCrossEntropyWithLogits");
    Operand<? extends TNumber> preciseLogits;
    if (logits.asOutput().type() == TFloat16.class || logits.asOutput().type() == TBfloat16.class) {
      preciseLogits = Cast.create(scope, logits, TFloat32.class);
    } else {
      preciseLogits = logits;
    }
    Shape labelsStaticShape = labels.shape();
    org.tensorflow.op.core.Shape<TInt32> labelsShape =
        org.tensorflow.op.core.Shape.create(scope, labels);
    Shape logitsShape = logits.shape();
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
              labelsStaticShape, logitsShape));
    }

    if (staticShapesFullyDefined && !labelsStaticShape.equals(logitsShortened)) {
      throw new IllegalArgumentException(
          String.format(
              "Shape mismatch: The shape of labels (received %s) "
                  + "should equal the shape of logits except for the last "
                  + "dimension (received %s).",
              labelsStaticShape, logitsShape));
    }
    // Check if no reshapes are required.
    if (logitsShape.numDimensions() == 2) {
      org.tensorflow.op.nn.SparseSoftmaxCrossEntropyWithLogits<? extends TNumber> smax =
          org.tensorflow.op.nn.SparseSoftmaxCrossEntropyWithLogits.create(
              scope, preciseLogits, labels);
      Operand<? extends TNumber> cost = smax.loss();
      if (cost.type() != logits.type()) {
        return Cast.create(scope, cost, logits.type());
      } else {
        // Unchecked cast already checked with previous if
        return (Operand<T>) cost;
      }
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

    // Reshape logits to 2 dims, labels to 1 dim.
    long numClassses = logitsShape.size(-1);

    preciseLogits = Reshape.create(scope, preciseLogits, Constant.arrayOf(scope, -1L, numClassses));
    labels = Reshape.create(scope, labels, Constant.scalarOf(scope, -1));
    scope.withControlDependencies(shapeChecks);
    // call raw op
    org.tensorflow.op.nn.SparseSoftmaxCrossEntropyWithLogits<? extends TNumber> smax =
        org.tensorflow.op.nn.SparseSoftmaxCrossEntropyWithLogits.create(
            scope, preciseLogits, labels);
    Operand<? extends TNumber> cost = smax.loss();
    cost = Reshape.create(scope, cost, labelsShape);
    if (cost.type() != logits.type()) {
      return Cast.create(scope, cost, logits.type());
    } else {
      // Unchecked cast already checked with previous if
      return (Operand<T>) cost;
    }
  }
}
