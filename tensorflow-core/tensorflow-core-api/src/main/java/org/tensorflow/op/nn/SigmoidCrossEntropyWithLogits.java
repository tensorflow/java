package org.tensorflow.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.core.Select;
import org.tensorflow.op.core.ZerosLike;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.math.*;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;

@Operator(group = "nn")
public class SigmoidCrossEntropyWithLogits {

  /**
   * Computes sigmoid cross entropy given <code>logits</code>.
   *
   * <p>Measures the probability error in discrete classification tasks in which each class is
   * independent and not mutually exclusive. For instance, one could perform multilabel
   * classification where a picture can contain both an elephant and a dog at the same time.
   *
   * <p>For brevity, let <code>x = logits</code>, <code>z = labels</code>. The logistic loss in
   * pseudo-code is
   *
   * <pre>
   * z * -log(sigmoid(x)) + (1 - z) * -log(1 - sigmoid(x))
   *  = z * -log(1 / (1 + exp(-x))) + (1 - z) * -log(exp(-x) / (1 + exp(-x)))
   *  = z * log(1 + exp(-x)) + (1 - z) * (-log(exp(-x)) + log(1 + exp(-x)))
   *  = z * log(1 + exp(-x)) + (1 - z) * (x + log(1 + exp(-x))
   *  = (1 - z) * x + log(1 + exp(-x))
   *  = x - x * z + log(1 + exp(-x))
   * </pre>
   *
   * <p>For <code>x < 0</code>, to avoid overflow in <code>exp(-x)</code>, we reformulate the above
   *
   * <pre>
   * x - x * z + log(1 + exp(-x))
   *  = log(exp(x)) - x * z + log(1 + exp(-x))
   *  = - x * z + log(1 + exp(x))
   * </pre>
   *
   * <p>Hence, to ensure stability and avoid overflow, the implementation uses this equivalent
   * formulation
   *
   * <pre>
   *   max(x, 0) - x * z + log(1 + exp(-abs(x)))
   * </pre>
   *
   * <p></ode>logits</code> and <code>labels</code> must have the same type and shape.
   *
   * <p>
   *
   * @param scope The TensorFlow scope
   * @param labels the labels
   * @param logits the logits of type float32 or float64
   * @param <T> the type of labels and logits
   * @return the component-wise logistic losses.
   * @throws IllegalArgumentException if logits' and labels' do not have the same shape
   */
  @Endpoint(name = "sigmoidCrossEntropyWithLogits")
  public static <T extends TNumber> Operand<T> sigmoidCrossEntropyWithLogits(
      Scope scope, Operand<T> labels, Operand<T> logits) {
    if (!isCompatible(labels.shape(), logits.shape())) {
      throw new IllegalArgumentException(
          String.format(
              "logits and labels must have the same shape (%s vs %s)",
              labels.shape(), logits.shape()));
    }
    scope = scope.withSubScope("SigmoidCrossEntropyWithLogits");

    Operand<T> zeros =
        Cast.create(scope, ZerosLike.create(scope, logits), logits.asOutput().type());
    Operand<TBool> cond = GreaterEqual.create(scope, logits, zeros);

    Operand<T> reluLogits = Select.create(scope, cond, logits, zeros);
    Operand<T> negAbsLogits = Select.create(scope, cond, Neg.create(scope, logits), logits);
    return Add.create(
        scope,
        Sub.create(scope, reluLogits, Mul.create(scope, logits, labels)),
        Log1p.create(scope, Exp.create(scope, negAbsLogits)));
  }
  /**
   * Determine if 2 shapes are compatible
   *
   * <p>2 shapes are compatible if they have the same number of dimensions, and if the corresponding
   * dimensions are equal, or at least one of the corresponding dimensions is unknown.
   *
   * @param shape the first shape
   * @param other the second shape
   * @return true, if the shapes are compatible.
   */
  private static boolean isCompatible(Shape shape, Shape other) {
    if (shape.numDimensions() != other.numDimensions()) return false;
    for (int i = 0; i < shape.numDimensions(); i++) {
      long aShapeDim = shape.get(i);
      long bShapeDim = other.get(i);
      if (aShapeDim == bShapeDim
          || (aShapeDim == Shape.UNKNOWN_SIZE || bShapeDim == Shape.UNKNOWN_SIZE)) {
        continue;
      }
      return false;
    }
    return true;
  }
}
