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
import org.tensorflow.framework.op.math.Axes;
import org.tensorflow.framework.op.math.ConfusionMatrix;
import org.tensorflow.framework.op.math.L2Normalize;
import org.tensorflow.framework.op.math.ReduceLogSumExp;
import org.tensorflow.framework.op.math.TensorDot;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

public class MathOps {
  private final Scope scope;

  private final FrameworkOps frameworkOps;

  /**
   * Creates Framework math Operations
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
    return L2Normalize.l2Normalize(scope, x, axis);
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
    return ConfusionMatrix.confusionMatrix(scope, labels, predictions, null, null);
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
    return ConfusionMatrix.confusionMatrix(scope, labels, predictions, weights, null);
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
    return ConfusionMatrix.confusionMatrix(scope, labels, predictions, weights, numClasses);
  }

  /**
   * Creates an Operand that has all axes contained in the Operand's shape.
   *
   * @param op the Operand
   * @return an Operand that has all axes contained in the Operand's shape..
   */
  public Operand<TInt32> allAxes(Operand<? extends TType> op) {
    return Axes.allAxes(scope, op);
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
  public <T extends TFloating> Operand<T> tensordot(Operand<T> a, Operand<T> b, int axis) {
    return TensorDot.tensordot(scope, a, b, axis);
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
  public <T extends TFloating> Operand<T> tensordot(
      Operand<T> a, Operand<T> b, Operand<TInt32> axes) {
    return TensorDot.tensordot(scope, a, b, axes);
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
  public <T extends TFloating> Operand<T> tensordot(Operand<T> a, Operand<T> b, int[] axes) {
    return TensorDot.tensordot(scope, a, b, axes);
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
  public <T extends TFloating> Operand<T> tensordot(Operand<T> a, Operand<T> b, int[][] axes) {
    return TensorDot.tensordot(scope, a, b, axes);
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
  public <T extends TFloating> Operand<T> tensordot(
      Operand<T> a, Operand<T> b, Operand<TInt32> aAxis, Operand<TInt32> bAxis) {
    return TensorDot.tensordot(scope, a, b, aAxis, bAxis);
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
  public <T extends TFloating> Operand<T> reduceLogSumExp(
      Operand<T> input, int[] axes, boolean keepDims) {
    return ReduceLogSumExp.reduceLogSumExp(scope, input, axes, keepDims);
  }
}
