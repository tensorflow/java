package org.tensorflow.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Operator(group = "nn")
public class SparseSoftmaxCrossEntropyWithLogits {

  /**
   * Computes sparse softmax cross entropy between <code>logits</code> and <code>labels</code>.
   *
   * <p>Measures the probability error in discrete classification tasks in which the classes are
   * mutually exclusive (each entry is in exactly one class). For example, each CIFAR-10 image is
   * labeled with one and only one label: an image can be a dog or a truck, but not both.
   *
   * <p><b>NOTE:</b>
   *
   * <p>For this operation, the probability of a given label is considered exclusive. That is, soft
   * classes are not allowed, and the <code>labels</code> vector must provide a single specific
   * index for the true class for each row of <code>logits</code> (each minibatch entry). For soft
   * softmax classification with a probability distribution for each entry, {@link
   * org.tensorflow.op.NnOps#softmaxCrossEntropyWithLogits}.
   *
   * <p><b>WARNING:</b>
   *
   * <p>This op expects unscaled logits, since it performs a <code>softmax</code> on <code>logits
   * </code> internally for efficiency. Do not call this op with the output of <code>softmax</code>,
   * as it will produce incorrect results.
   *
   * <p>A common use case is to have logits of shape <code>[batchSize, numClasses]</code> and have
   * labels of shape <code>[batchSize]</code>, but higher dimensions are supported, in which case
   * the <code>dim</code>-th dimension is assumed to be of size <code>numClasses</code>. <code>
   * logits</code> must have the <cod>dataType</cod> of <code>TFloat16</code>, <code>TFloat32</code>
   * , or <code>TFloat64</code>, and <code>labels</code> must have the dtype of <code>TInt32</code>
   * or <code>TInt64</code>.
   *
   * @param scope current scope
   * @param labels <code>Tensor</code> of shape <code>[d_0, d_1, ..., d_{r-1}]</code> (where <code>r
   *     </code> is rank of <code>labels</code> and result) and the dataType is <code>TInt32</code>
   *     or <code>TInt64</code>. Each entry in <code>labels</code> must be an index in <code>[0,
   *     numClasses)</code>. Other values will raise an exception when this op is run on CPU, and
   *     return <code>NaN</code> for corresponding loss and gradient rows on GPU.
   * @param logits Per-label activations (typically a linear output) of shape <code>[d_0, d_1, ...,
   *     d_{r-1}, numClasses]</code> and dataType of <code>TFloat16</code>, <code>TFloat32</code>,
   *     or <code>TFloat64</code>. These activation energies are interpreted as unnormalized log
   *     probabilities.
   * @return A <code>Tensor</code> of the same shape as <code>labels</code> and of the same type as
   *     <code>logits</code> with the softmax cross entropy loss.
   * @throws IllegalArgumentException If logits are scalars (need to have rank >= 1) or if the rank
   *     of the labels is not equal to the rank of the logits minus one.
   */
  @Endpoint(name = "sparseSoftmaxCrossEntropyWithLogits")
  public static <T extends TNumber, U extends TNumber> Operand sparseSoftmaxCrossEntropyWithLogits(
      Scope scope, Operand<T> labels, Operand<U> logits) {
    scope = scope.withSubScope("SparseSoftmaxCrossEntropyWithLogits");
    /** cannot use generics on preciseLogits as it may be recast later */
    Operand preciseLogits = logits;
    if (logits.asOutput().type() == TFloat16.class || logits.asOutput().type() == TBfloat16.class) {
      preciseLogits = Cast.create(scope, logits, TFloat32.class);
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
      org.tensorflow.op.nn.raw.SparseSoftmaxCrossEntropyWithLogits smax =
          org.tensorflow.op.nn.raw.SparseSoftmaxCrossEntropyWithLogits.create(
              scope, preciseLogits, labels);
      Operand loss = smax.loss();
      if (logits.asOutput().type() == TFloat16.class) {
        loss = Cast.create(scope, loss, TFloat16.class);
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

    // Reshape logits to 2 dims, labels to 1 dim.
    long numClassses = logitsShape.size(-1);

    preciseLogits = Reshape.create(scope, preciseLogits, Constant.arrayOf(scope, -1L, numClassses));
    labels = Reshape.create(scope, labels, Constant.scalarOf(scope, -1));
    scope.withControlDependencies(shapeChecks);
    org.tensorflow.op.nn.raw.SparseSoftmaxCrossEntropyWithLogits smax =
        org.tensorflow.op.nn.raw.SparseSoftmaxCrossEntropyWithLogits.create(
            scope, preciseLogits, labels);
    Operand cost = smax.loss();
    cost = Reshape.create(scope, cost, labelsShape);
    if (logits.asOutput().type() == TFloat16.class) {
      cost = Cast.create(scope, cost, TFloat16.class);
    }
    return cost;
  }
}
