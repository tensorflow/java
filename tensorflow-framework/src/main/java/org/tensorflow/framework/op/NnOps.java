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

import org.tensorflow.Operand;
import org.tensorflow.framework.op.nn.GELU;
import org.tensorflow.framework.op.nn.SigmoidCrossEntropyWithLogits;
import org.tensorflow.framework.op.nn.SoftmaxCrossEntropyWithLogits;
import org.tensorflow.framework.op.nn.SparseSoftmaxCrossEntropyWithLogits;
import org.tensorflow.op.Scope;
import org.tensorflow.types.family.TNumber;

/**
 * Creates Framework nerual network Operations
 *
 * <p>These are higher level ops that may invoke core ops. Higher level Ops may perform the
 * operation solely in the TensorFlow framework or do preprocessing of the Operands before invoking
 * a core level Op.
 *
 * <p>{@link FrameworkOps}
 */
public class NnOps {
  private final Scope scope;

  private final FrameworkOps frameworkOps;

  /**
   * Creates Framework {@code nn} Operations
   *
   * @param frameworkOps the TensorFLow framework Ops
   */
  NnOps(FrameworkOps frameworkOps) {
    this.scope = frameworkOps.scope();
    this.frameworkOps = frameworkOps;
  }

  /**
   * Computes sigmoid cross entropy given {@code logits}.
   *
   * <p>Measures the probability error in discrete classification tasks in which each class is
   * independent and not mutually exclusive. For instance, one could perform multilabel
   * classification where a picture can contain both an elephant and a dog at the same time.
   *
   * <p>For brevity, let {@code x = logits}, {@code z = labels}. The logistic loss in pseudo-code is
   *
   * <pre>
   *  z * -log(sigmoid(x)) + (1 - z) * -log(1 - sigmoid(x))
   *   = z * -log(1 / (1 + exp(-x))) + (1 - z) * -log(exp(-x) / (1 + exp(-x)))
   *   = z * log(1 + exp(-x)) + (1 - z) * (-log(exp(-x)) + log(1 + exp(-x)))
   *   = z * log(1 + exp(-x)) + (1 - z) * (x + log(1 + exp(-x))
   *   = (1 - z) * x + log(1 + exp(-x))
   *   = x - x * z + log(1 + exp(-x))
   *  </pre>
   *
   * <p>For {@code x < 0}, to avoid overflow in {@code exp(-x)}, we reformulate the above
   *
   * <pre>
   *  x - x * z + log(1 + exp(-x))
   *   = log(exp(x)) - x * z + log(1 + exp(-x))
   *   = - x * z + log(1 + exp(x))
   *  </pre>
   *
   * <p>Hence, to ensure stability and avoid overflow, the implementation uses this equivalent
   * formulation
   *
   * <pre>
   *    max(x, 0) - x * z + log(1 + exp(-abs(x)))
   *  </pre>
   *
   * <p>{@code logits} and {@code labels} must have the same type and shape.
   *
   * <p>
   *
   * @param labels the labels
   * @param logits the logits of type float32 or float64
   * @param <T> the type of labels and logits
   * @return the component-wise logistic losses.
   * @throws IllegalArgumentException if logits and labels do not have the same shape
   */
  public <T extends TNumber> Operand<T> sigmoidCrossEntropyWithLogits(
      Operand<T> labels, Operand<T> logits) {
    return SigmoidCrossEntropyWithLogits.sigmoidCrossEntropyWithLogits(scope, labels, logits);
  }

  /**
   * Computes softmax cross entropy between {@code logits} and {@code labels}.
   *
   * <p>Measures the probability error in discrete classification tasks in which the classes are
   * mutually exclusive (each entry is in exactly one class). For example, each CIFAR-10 image is
   * labeled with one and only one label: an image can be a dog or a truck, but not both.
   *
   * <p><b>NOTE:</b>
   *
   * <p>While the classes are mutually exclusive, their probabilities need not be. All that is
   * required is that each row of {@code labels} is a valid probability distribution. If they are
   * not, the computation of the gradient will be incorrect.
   *
   * <p>If using exclusive {@code labels} (wherein one and only one class is true at a time), see
   * {@link org.tensorflow.op.NnOps#sparseSoftmaxCrossEntropyWithLogits}
   *
   * <p>Usage:
   *
   * <pre>
   *    Operand&lt;TFloat32&gt; logits =
   *        tf.constant(new float[][] {{4.0F, 2.0F, 1.0F}, {0.0F, 5.0F, 1.0F}} );
   *    Operand&lt;TFloat32&gt; labels =
   *        tf.constant(new float[][] {{1.0F, 0.0F, 0.0F}, {0.0F, 0.8F, 0.2F}} );
   *    Operand&lt;TFloat32&gt; output =
   *        tf.nn.softmaxCrossEntropyWithLogits(labels, logits, -1);
   *    // output Shape = [2]
   *    // dataType = FLOAT (1)
   *    // values { 0.169846, 0.824745 }
   *  </pre>
   *
   * <p>Backpropagation will happen into both {@code logits} and {@code labels}. To disallow
   * backpropagation into {@code labels}, pass label tensors through {@code tf.stopGradient} before
   * feeding it to this function.
   *
   * @param labels Each vector along the class dimension should hold a valid probability
   *     distribution e.g. for the case in which labels are of shape {@code [batch_size,
   *     num_classes] }, each row of {@code labels[i]} must be a valid probability distribution.
   * @param logits Per-label activations, typically a linear output. These activation energies are
   *     interpreted as unnormalized log probabilities.
   * @param axis The class dimension. -1 is the last dimension.
   * @param <T> the number type of the operands
   * @param <U> the data type for the labels.
   * @return the softmax cross entropy loss. Its type is the same as {@code logits} and its shape is
   *     the same as {@code labels} except that it does not have the last dimension of {@code
   *     labels}.
   */
  public <T extends TNumber, U extends TNumber> Operand<T> softmaxCrossEntropyWithLogits(
      Operand<U> labels, Operand<T> logits, int axis) {
    return SoftmaxCrossEntropyWithLogits.softmaxCrossEntropyWithLogits(scope, labels, logits, axis);
  }

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
  public <T extends TNumber, U extends TNumber> Operand<T> sparseSoftmaxCrossEntropyWithLogits(
      Operand<U> labels, Operand<T> logits) {
    return SparseSoftmaxCrossEntropyWithLogits.sparseSoftmaxCrossEntropyWithLogits(
        scope, labels, logits);
  }

  /**
   * Compute the Gaussian Error Linear Unit (GELU) activation function without approximation.
   *
   * <p>Gaussian error linear unit (GELU) computes {@code x * P(X <= x)}, where {@code P(X) ~ N(0,
   * 1)}. The (GELU) nonlinearity weights inputs by their value, rather than gates inputs by their
   * sign as in ReLU.
   *
   * @param input the input
   * @param <T> the data type for the input and result
   * @return The Gaussian Error Linear Unit computation
   */
  public <T extends TNumber> Operand<T> gelu(Operand<T> input) {
    return GELU.gelu(scope, input);
  }

  /**
   * Compute the Gaussian Error Linear Unit (GELU) activation function.
   *
   * <p>Gaussian error linear unit (GELU) computes {@code x * P(X <= x)}, where {@code P(X) ~ N(0,
   * 1)}. The (GELU) nonlinearity weights inputs by their value, rather than gates inputs by their
   * sign as in ReLU.
   *
   * @param input the input
   * @param approximate Whether to enable approximation.
   * @param <T> the data type for the input and result
   * @return The Gaussian Error Linear Unit computation
   */
  public <T extends TNumber> Operand<T> gelu(Operand<T> input, boolean approximate) {
    return GELU.gelu(scope, input, approximate);
  }
}
