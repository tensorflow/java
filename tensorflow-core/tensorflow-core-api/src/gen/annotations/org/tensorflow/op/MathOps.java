// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.math.Abs;
import org.tensorflow.op.math.AccumulateN;
import org.tensorflow.op.math.Acos;
import org.tensorflow.op.math.Acosh;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.AddN;
import org.tensorflow.op.math.Angle;
import org.tensorflow.op.math.ApproximateEqual;
import org.tensorflow.op.math.ArgMax;
import org.tensorflow.op.math.ArgMin;
import org.tensorflow.op.math.Asin;
import org.tensorflow.op.math.Asinh;
import org.tensorflow.op.math.Atan;
import org.tensorflow.op.math.Atan2;
import org.tensorflow.op.math.Atanh;
import org.tensorflow.op.math.Betainc;
import org.tensorflow.op.math.Bincount;
import org.tensorflow.op.math.Ceil;
import org.tensorflow.op.math.CompareAndBitpack;
import org.tensorflow.op.math.ComplexAbs;
import org.tensorflow.op.math.Conj;
import org.tensorflow.op.math.Cos;
import org.tensorflow.op.math.Cosh;
import org.tensorflow.op.math.Cumprod;
import org.tensorflow.op.math.Cumsum;
import org.tensorflow.op.math.DenseBincount;
import org.tensorflow.op.math.Digamma;
import org.tensorflow.op.math.Div;
import org.tensorflow.op.math.DivNoNan;
import org.tensorflow.op.math.Equal;
import org.tensorflow.op.math.Erf;
import org.tensorflow.op.math.Erfc;
import org.tensorflow.op.math.Exp;
import org.tensorflow.op.math.Expm1;
import org.tensorflow.op.math.Fact;
import org.tensorflow.op.math.Floor;
import org.tensorflow.op.math.FloorDiv;
import org.tensorflow.op.math.FloorMod;
import org.tensorflow.op.math.Greater;
import org.tensorflow.op.math.GreaterEqual;
import org.tensorflow.op.math.Igamma;
import org.tensorflow.op.math.Igammac;
import org.tensorflow.op.math.Imag;
import org.tensorflow.op.math.InvertPermutation;
import org.tensorflow.op.math.IsFinite;
import org.tensorflow.op.math.IsInf;
import org.tensorflow.op.math.IsNan;
import org.tensorflow.op.math.Less;
import org.tensorflow.op.math.LessEqual;
import org.tensorflow.op.math.Lgamma;
import org.tensorflow.op.math.Log;
import org.tensorflow.op.math.Log1p;
import org.tensorflow.op.math.LogicalAnd;
import org.tensorflow.op.math.LogicalNot;
import org.tensorflow.op.math.LogicalOr;
import org.tensorflow.op.math.Maximum;
import org.tensorflow.op.math.Mean;
import org.tensorflow.op.math.Minimum;
import org.tensorflow.op.math.Mod;
import org.tensorflow.op.math.Mul;
import org.tensorflow.op.math.MulNoNan;
import org.tensorflow.op.math.Ndtri;
import org.tensorflow.op.math.Neg;
import org.tensorflow.op.math.NextAfter;
import org.tensorflow.op.math.NotEqual;
import org.tensorflow.op.math.Polygamma;
import org.tensorflow.op.math.PopulationCount;
import org.tensorflow.op.math.Pow;
import org.tensorflow.op.math.QuantizedAdd;
import org.tensorflow.op.math.QuantizedMul;
import org.tensorflow.op.math.Real;
import org.tensorflow.op.math.RealDiv;
import org.tensorflow.op.math.Reciprocal;
import org.tensorflow.op.math.Rint;
import org.tensorflow.op.math.Round;
import org.tensorflow.op.math.Rsqrt;
import org.tensorflow.op.math.SegmentMax;
import org.tensorflow.op.math.SegmentMean;
import org.tensorflow.op.math.SegmentMin;
import org.tensorflow.op.math.SegmentProd;
import org.tensorflow.op.math.SegmentSum;
import org.tensorflow.op.math.Sigmoid;
import org.tensorflow.op.math.Sign;
import org.tensorflow.op.math.Sin;
import org.tensorflow.op.math.Sinh;
import org.tensorflow.op.math.Softplus;
import org.tensorflow.op.math.Sqrt;
import org.tensorflow.op.math.Square;
import org.tensorflow.op.math.SquaredDifference;
import org.tensorflow.op.math.Sub;
import org.tensorflow.op.math.Tan;
import org.tensorflow.op.math.Tanh;
import org.tensorflow.op.math.TruncateDiv;
import org.tensorflow.op.math.TruncateMod;
import org.tensorflow.op.math.UnsortedSegmentMax;
import org.tensorflow.op.math.UnsortedSegmentMin;
import org.tensorflow.op.math.UnsortedSegmentProd;
import org.tensorflow.op.math.UnsortedSegmentSum;
import org.tensorflow.op.math.Xdivy;
import org.tensorflow.op.math.Xlog1py;
import org.tensorflow.op.math.Xlogy;
import org.tensorflow.op.math.Zeta;
import org.tensorflow.op.math.erfinv;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code math} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class MathOps {
  private final Scope scope;

  private final Ops ops;

  MathOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Computes the absolute value of a tensor.
   *  <p>
   *  Given a tensor `x`, this operation returns a tensor containing the absolute
   *  value of each element in `x`. For example, if x is an input element and y is
   *  an output element, this operation computes \\(y = |x|\\).
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Abs
   */
  public <T extends TNumber> Abs<T> abs(Operand<T> x) {
    return Abs.create(scope, x);
  }

  /**
   * Returns the element-wise sum of a list of tensors.
   *  <p>
   *  `tf.accumulate_n_v2` performs the same operation as `tf.add_n`, but does not
   *  wait for all of its inputs to be ready before beginning to sum. This can
   *  save memory if inputs are ready at different times, since minimum temporary
   *  storage is proportional to the output size rather than the inputs size.
   *  <p>
   *  Unlike the original `accumulate_n`, `accumulate_n_v2` is differentiable.
   *  <p>
   *  Returns a `Tensor` of same shape and type as the elements of `inputs`.
   *
   * @param <T> data type for {@code sum()} output
   * @param inputs A list of `Tensor` objects, each with same shape and type.
   * @param shape Shape of elements of `inputs`.
   * @return a new instance of AccumulateN
   */
  public <T extends TType> AccumulateN<T> accumulateN(Iterable<Operand<T>> inputs, Shape shape) {
    return AccumulateN.create(scope, inputs, shape);
  }

  /**
   * Computes acos of x element-wise.
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Acos
   */
  public <T extends TType> Acos<T> acos(Operand<T> x) {
    return Acos.create(scope, x);
  }

  /**
   * Computes inverse hyperbolic cosine of x element-wise.
   *  <p>
   *  Given an input tensor, the function computes inverse hyperbolic cosine of every element.
   *  Input range is `[1, inf]`. It returns `nan` if the input lies outside the range.
   *  <pre>{@code
   *  x = tf.constant([-2, -0.5, 1, 1.2, 200, 10000, float("inf")])
   *  tf.math.acosh(x) ==> [nan nan 0. 0.62236255 5.9914584 9.903487 inf]
   *  }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Acosh
   */
  public <T extends TType> Acosh<T> acosh(Operand<T> x) {
    return Acosh.create(scope, x);
  }

  /**
   * Returns x + y element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.Add` supports broadcasting. `AddN` does not. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of Add
   */
  public <T extends TType> Add<T> add(Operand<T> x, Operand<T> y) {
    return Add.create(scope, x, y);
  }

  /**
   * Add all input tensors element wise.
   *  <p>
   *    Inputs must be of same size and shape.
   *  <p>
   *    <pre>{@code
   *    x = [9, 7, 10]
   *    tf.math.add_n(x) ==> 26
   *    }</pre>
   *
   * @param <T> data type for {@code sum()} output
   * @param inputs
   * @return a new instance of AddN
   */
  public <T extends TType> AddN<T> addN(Iterable<Operand<T>> inputs) {
    return AddN.create(scope, inputs);
  }

  /**
   * Returns the argument of a complex number.
   *  <p>
   *  Given a tensor `input` of complex numbers, this operation returns a tensor of
   *  type `float` that is the argument of each element in `input`. All elements in
   *  `input` must be complex numbers of the form \\(a + bj\\), where <i>a</i>
   *  is the real part and <i>b</i> is the imaginary part.
   *  <p>
   *  The argument returned by this operation is of the form \\(atan2(b, a)\\).
   *  <p>
   *  For example:
   *  <pre>{@code
   *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
   *  tf.angle(input) ==> [2.0132, 1.056]
   *  }</pre>
   *
   * @compatibility(numpy) Equivalent to np.angle.
   * @end_compatibility
   * @param <U> data type for {@code output()} output
   * @param input
   * @return a new instance of Angle
   */
  public <T extends TType> Angle<TFloat32> angle(Operand<T> input) {
    return Angle.create(scope, input);
  }

  /**
   * Returns the argument of a complex number.
   *  <p>
   *  Given a tensor `input` of complex numbers, this operation returns a tensor of
   *  type `float` that is the argument of each element in `input`. All elements in
   *  `input` must be complex numbers of the form \\(a + bj\\), where <i>a</i>
   *  is the real part and <i>b</i> is the imaginary part.
   *  <p>
   *  The argument returned by this operation is of the form \\(atan2(b, a)\\).
   *  <p>
   *  For example:
   *  <pre>{@code
   *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
   *  tf.angle(input) ==> [2.0132, 1.056]
   *  }</pre>
   *
   * @compatibility(numpy) Equivalent to np.angle.
   * @end_compatibility
   * @param <U> data type for {@code output()} output
   * @param input
   * @param Tout
   * @return a new instance of Angle
   */
  public <U extends TNumber, T extends TType> Angle<U> angle(Operand<T> input, Class<U> Tout) {
    return Angle.create(scope, input, Tout);
  }

  /**
   * Returns the truth value of abs(x-y) < tolerance element-wise.
   *
   * @param x
   * @param y
   * @param options carries optional attributes values
   * @return a new instance of ApproximateEqual
   */
  public <T extends TType> ApproximateEqual approximateEqual(Operand<T> x, Operand<T> y,
      ApproximateEqual.Options... options) {
    return ApproximateEqual.create(scope, x, y, options);
  }

  /**
   * Returns the index with the largest value across dimensions of a tensor.
   *  <p>
   *  Note that in case of ties the identity of the return value is not guaranteed.
   *  <p>
   *  Usage:
   *    <pre>{@code
   *    import tensorflow as tf
   *    a = [1, 10, 26.9, 2.8, 166.32, 62.3]
   *    b = tf.math.argmax(input = a)
   *    c = tf.keras.backend.eval(b)
   *    # c = 4
   *    # here a[4] = 166.32 which is the largest element of a across axis 0
   *    }</pre>
   *
   * @param <V> data type for {@code output()} output
   * @param input
   * @param dimension int32 or int64, must be in the range `[-rank(input), rank(input))`.
   *  Describes which dimension of the input Tensor to reduce across. For vectors,
   *  use dimension = 0.
   * @return a new instance of ArgMax
   */
  public <T extends TType, U extends TNumber> ArgMax<TInt64> argMax(Operand<T> input,
      Operand<U> dimension) {
    return ArgMax.create(scope, input, dimension);
  }

  /**
   * Returns the index with the largest value across dimensions of a tensor.
   *  <p>
   *  Note that in case of ties the identity of the return value is not guaranteed.
   *  <p>
   *  Usage:
   *    <pre>{@code
   *    import tensorflow as tf
   *    a = [1, 10, 26.9, 2.8, 166.32, 62.3]
   *    b = tf.math.argmax(input = a)
   *    c = tf.keras.backend.eval(b)
   *    # c = 4
   *    # here a[4] = 166.32 which is the largest element of a across axis 0
   *    }</pre>
   *
   * @param <V> data type for {@code output()} output
   * @param input
   * @param dimension int32 or int64, must be in the range `[-rank(input), rank(input))`.
   *  Describes which dimension of the input Tensor to reduce across. For vectors,
   *  use dimension = 0.
   * @param outputType
   * @return a new instance of ArgMax
   */
  public <V extends TNumber, T extends TType, U extends TNumber> ArgMax<V> argMax(Operand<T> input,
      Operand<U> dimension, Class<V> outputType) {
    return ArgMax.create(scope, input, dimension, outputType);
  }

  /**
   * Returns the index with the smallest value across dimensions of a tensor.
   *  <p>
   *  Note that in case of ties the identity of the return value is not guaranteed.
   *  <p>
   *  Usage:
   *    <pre>{@code
   *    import tensorflow as tf
   *    a = [1, 10, 26.9, 2.8, 166.32, 62.3]
   *    b = tf.math.argmin(input = a)
   *    c = tf.keras.backend.eval(b)
   *    # c = 0
   *    # here a[0] = 1 which is the smallest element of a across axis 0
   *    }</pre>
   *
   * @param <V> data type for {@code output()} output
   * @param input
   * @param dimension int32 or int64, must be in the range `[-rank(input), rank(input))`.
   *  Describes which dimension of the input Tensor to reduce across. For vectors,
   *  use dimension = 0.
   * @return a new instance of ArgMin
   */
  public <T extends TType, U extends TNumber> ArgMin<TInt64> argMin(Operand<T> input,
      Operand<U> dimension) {
    return ArgMin.create(scope, input, dimension);
  }

  /**
   * Returns the index with the smallest value across dimensions of a tensor.
   *  <p>
   *  Note that in case of ties the identity of the return value is not guaranteed.
   *  <p>
   *  Usage:
   *    <pre>{@code
   *    import tensorflow as tf
   *    a = [1, 10, 26.9, 2.8, 166.32, 62.3]
   *    b = tf.math.argmin(input = a)
   *    c = tf.keras.backend.eval(b)
   *    # c = 0
   *    # here a[0] = 1 which is the smallest element of a across axis 0
   *    }</pre>
   *
   * @param <V> data type for {@code output()} output
   * @param input
   * @param dimension int32 or int64, must be in the range `[-rank(input), rank(input))`.
   *  Describes which dimension of the input Tensor to reduce across. For vectors,
   *  use dimension = 0.
   * @param outputType
   * @return a new instance of ArgMin
   */
  public <V extends TNumber, T extends TType, U extends TNumber> ArgMin<V> argMin(Operand<T> input,
      Operand<U> dimension, Class<V> outputType) {
    return ArgMin.create(scope, input, dimension, outputType);
  }

  /**
   * Computes the trignometric inverse sine of x element-wise.
   *  <p>
   *  The `tf.math.asin` operation returns the inverse of `tf.math.sin`, such that
   *  if `y = tf.math.sin(x)` then, `x = tf.math.asin(y)`.
   *  <p>
   *  <b>Note</b>: The output of `tf.math.asin` will lie within the invertible range
   *  of sine, i.e [-pi/2, pi/2].
   *  <p>
   *  For example:
   *  <pre>{@code
   *  # Note: [1.047, 0.785] ~= [(pi/3), (pi/4)]
   *  x = tf.constant([1.047, 0.785])
   *  y = tf.math.sin(x) # [0.8659266, 0.7068252]
   *
   *  tf.math.asin(y) # [1.047, 0.785] = x
   *  }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Asin
   */
  public <T extends TType> Asin<T> asin(Operand<T> x) {
    return Asin.create(scope, x);
  }

  /**
   * Computes inverse hyperbolic sine of x element-wise.
   *  <p>
   *    Given an input tensor, this function computes inverse hyperbolic sine
   *    for every element in the tensor. Both input and output has a range of
   *    `[-inf, inf]`.
   *  <p>
   *    <pre>{@code
   *    x = tf.constant([-float("inf"), -2, -0.5, 1, 1.2, 200, 10000, float("inf")])
   *    tf.math.asinh(x) ==> [-inf -1.4436355 -0.4812118 0.8813736 1.0159732 5.991471 9.903487 inf]
   *    }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Asinh
   */
  public <T extends TType> Asinh<T> asinh(Operand<T> x) {
    return Asinh.create(scope, x);
  }

  /**
   * Computes the trignometric inverse tangent of x element-wise.
   *  <p>
   *  The `tf.math.atan` operation returns the inverse of `tf.math.tan`, such that
   *  if `y = tf.math.tan(x)` then, `x = tf.math.atan(y)`.
   *  <p>
   *  <b>Note</b>: The output of `tf.math.atan` will lie within the invertible range
   *  of tan, i.e (-pi/2, pi/2).
   *  <p>
   *  For example:
   *  <pre>{@code
   *  # Note: [1.047, 0.785] ~= [(pi/3), (pi/4)]
   *  x = tf.constant([1.047, 0.785])
   *  y = tf.math.tan(x) # [1.731261, 0.99920404]
   *
   *  tf.math.atan(y) # [1.047, 0.785] = x
   *  }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Atan
   */
  public <T extends TType> Atan<T> atan(Operand<T> x) {
    return Atan.create(scope, x);
  }

  /**
   * Computes arctangent of `y/x` element-wise, respecting signs of the arguments.
   *  <p>
   *  This is the angle \( \theta \in [-\pi, \pi] \) such that
   *  \[ x = r \cos(\theta) \]
   *  and
   *  \[ y = r \sin(\theta) \]
   *  where \(r = \sqrt(x^2 + y^2) \).
   *
   * @param <T> data type for {@code z()} output
   * @param y
   * @param x
   * @return a new instance of Atan2
   */
  public <T extends TNumber> Atan2<T> atan2(Operand<T> y, Operand<T> x) {
    return Atan2.create(scope, y, x);
  }

  /**
   * Computes inverse hyperbolic tangent of x element-wise.
   *  <p>
   *    Given an input tensor, this function computes inverse hyperbolic tangent
   *    for every element in the tensor. Input range is `[-1,1]` and output range is
   *    `[-inf, inf]`. If input is `-1`, output will be `-inf` and if the
   *    input is `1`, output will be `inf`. Values outside the range will have
   *    `nan` as output.
   *  <p>
   *    <pre>{@code
   *    x = tf.constant([-float("inf"), -1, -0.5, 1, 0, 0.5, 10, float("inf")])
   *    tf.math.atanh(x) ==> [nan -inf -0.54930615 inf  0. 0.54930615 nan nan]
   *    }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Atanh
   */
  public <T extends TType> Atanh<T> atanh(Operand<T> x) {
    return Atanh.create(scope, x);
  }

  /**
   * Compute the regularized incomplete beta integral \\(I_x(a, b)\\).
   *  <p>
   *  The regularized incomplete beta integral is defined as:
   *  <p>
   *  \\(I_x(a, b) = \frac{B(x; a, b)}{B(a, b)}\\)
   *  <p>
   *  where
   *  <p>
   *  \\(B(x; a, b) = \int_0^x t^{a-1} (1 - t)^{b-1} dt\\)
   *  <p>
   *  is the incomplete beta function and \\(B(a, b)\\) is the <i>complete</i>
   *  beta function.
   *
   * @param <T> data type for {@code z()} output
   * @param a
   * @param b
   * @param x
   * @return a new instance of Betainc
   */
  public <T extends TNumber> Betainc<T> betainc(Operand<T> a, Operand<T> b, Operand<T> x) {
    return Betainc.create(scope, a, b, x);
  }

  /**
   * Counts the number of occurrences of each value in an integer array.
   *  <p>
   *  Outputs a vector with length `size` and the same dtype as `weights`. If
   *  `weights` are empty, then index `i` stores the number of times the value `i` is
   *  counted in `arr`. If `weights` are non-empty, then index `i` stores the sum of
   *  the value in `weights` at each index where the corresponding value in `arr` is
   *  `i`.
   *  <p>
   *  Values in `arr` outside of the range [0, size) are ignored.
   *
   * @param <T> data type for {@code bins()} output
   * @param arr int32 `Tensor`.
   * @param size non-negative int32 scalar `Tensor`.
   * @param weights is an int32, int64, float32, or float64 `Tensor` with the same
   *  shape as `arr`, or a length-0 `Tensor`, in which case it acts as all weights
   *  equal to 1.
   * @return a new instance of Bincount
   */
  public <T extends TNumber> Bincount<T> bincount(Operand<TInt32> arr, Operand<TInt32> size,
      Operand<T> weights) {
    return Bincount.create(scope, arr, size, weights);
  }

  /**
   * Returns element-wise smallest integer not less than x.
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Ceil
   */
  public <T extends TNumber> Ceil<T> ceil(Operand<T> x) {
    return Ceil.create(scope, x);
  }

  /**
   * Compare values of `input` to `threshold` and pack resulting bits into a `uint8`.
   *  <p>
   *  Each comparison returns a boolean `true` (if `input_value > threshold`)
   *  or and `false` otherwise.
   *  <p>
   *  This operation is useful for Locality-Sensitive-Hashing (LSH) and other
   *  algorithms that use hashing approximations of cosine and `L2` distances;
   *  codes can be generated from an input via:
   *  <pre>{@code
   *  codebook_size = 50
   *  codebook_bits = codebook_size * 32
   *  codebook = tf.get_variable('codebook', [x.shape[-1].value, codebook_bits],
   *                             dtype=x.dtype,
   *                             initializer=tf.orthogonal_initializer())
   *  codes = compare_and_threshold(tf.matmul(x, codebook), threshold=0.)
   *  codes = tf.bitcast(codes, tf.int32)  # go from uint8 to int32
   *  # now codes has shape x.shape[:-1] + [codebook_size]
   *  }</pre>
   *  <b>NOTE</b>: Currently, the innermost dimension of the tensor must be divisible
   *  by 8.
   *  <p>
   *  Given an `input` shaped `[s0, s1, ..., s_n]`, the output is
   *  a `uint8` tensor shaped `[s0, s1, ..., s_n / 8]`.
   *
   * @param input Values to compare against `threshold` and bitpack.
   * @param threshold Threshold to compare against.
   * @return a new instance of CompareAndBitpack
   */
  public <T extends TType> CompareAndBitpack compareAndBitpack(Operand<T> input,
      Operand<T> threshold) {
    return CompareAndBitpack.create(scope, input, threshold);
  }

  /**
   * Computes the complex absolute value of a tensor.
   *  <p>
   *  Given a tensor `x` of complex numbers, this operation returns a tensor of type
   *  `float` or `double` that is the absolute value of each element in `x`. All
   *  elements in `x` must be complex numbers of the form \\(a + bj\\). The absolute
   *  value is computed as \\( \sqrt{a^2 + b^2}\\).
   *
   * @param <U> data type for {@code y()} output
   * @param x
   * @return a new instance of ComplexAbs
   */
  public <T extends TType> ComplexAbs<TFloat32> complexAbs(Operand<T> x) {
    return ComplexAbs.create(scope, x);
  }

  /**
   * Computes the complex absolute value of a tensor.
   *  <p>
   *  Given a tensor `x` of complex numbers, this operation returns a tensor of type
   *  `float` or `double` that is the absolute value of each element in `x`. All
   *  elements in `x` must be complex numbers of the form \\(a + bj\\). The absolute
   *  value is computed as \\( \sqrt{a^2 + b^2}\\).
   *
   * @param <U> data type for {@code y()} output
   * @param x
   * @param Tout
   * @return a new instance of ComplexAbs
   */
  public <U extends TNumber, T extends TType> ComplexAbs<U> complexAbs(Operand<T> x,
      Class<U> Tout) {
    return ComplexAbs.create(scope, x, Tout);
  }

  /**
   * Returns the complex conjugate of a complex number.
   *  <p>
   *  Given a tensor `input` of complex numbers, this operation returns a tensor of
   *  complex numbers that are the complex conjugate of each element in `input`. The
   *  complex numbers in `input` must be of the form \\(a + bj\\), where <i>a</i> is the
   *  real part and <i>b</i> is the imaginary part.
   *  <p>
   *  The complex conjugate returned by this operation is of the form \\(a - bj\\).
   *  <p>
   *  For example:
   *  <pre>{@code
   *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
   *  tf.conj(input) ==> [-2.25 - 4.75j, 3.25 - 5.75j]
   *  }</pre>
   *
   * @param <T> data type for {@code output()} output
   * @param input
   * @return a new instance of Conj
   */
  public <T extends TType> Conj<T> conj(Operand<T> input) {
    return Conj.create(scope, input);
  }

  /**
   * Computes cos of x element-wise.
   *  <p>
   *    Given an input tensor, this function computes cosine of every
   *    element in the tensor. Input range is `(-inf, inf)` and
   *    output range is `[-1,1]`. If input lies outside the boundary, `nan`
   *    is returned.
   *  <p>
   *    <pre>{@code
   *    x = tf.constant([-float("inf"), -9, -0.5, 1, 1.2, 200, 10000, float("inf")])
   *    tf.math.cos(x) ==> [nan -0.91113025 0.87758255 0.5403023 0.36235774 0.48718765 -0.95215535 nan]
   *    }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Cos
   */
  public <T extends TType> Cos<T> cos(Operand<T> x) {
    return Cos.create(scope, x);
  }

  /**
   * Computes hyperbolic cosine of x element-wise.
   *  <p>
   *    Given an input tensor, this function computes hyperbolic cosine of every
   *    element in the tensor. Input range is `[-inf, inf]` and output range
   *    is `[1, inf]`.
   *  <p>
   *    <pre>{@code
   *    x = tf.constant([-float("inf"), -9, -0.5, 1, 1.2, 2, 10, float("inf")])
   *    tf.math.cosh(x) ==> [inf 4.0515420e+03 1.1276259e+00 1.5430807e+00 1.8106556e+00 3.7621956e+00 1.1013233e+04 inf]
   *    }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Cosh
   */
  public <T extends TType> Cosh<T> cosh(Operand<T> x) {
    return Cosh.create(scope, x);
  }

  /**
   * Compute the cumulative product of the tensor `x` along `axis`.
   *  <p>
   *  By default, this op performs an inclusive cumprod, which means that the first
   *  element of the input is identical to the first element of the output:
   *  <pre>{@code
   *  tf.cumprod([a, b, c])  # => [a, a * b, a * b * c]
   *  }</pre>
   *  By setting the `exclusive` kwarg to `True`, an exclusive cumprod is
   *  performed instead:
   *  <pre>{@code
   *  tf.cumprod([a, b, c], exclusive=True)  # => [1, a, a * b]
   *  }</pre>
   *  By setting the `reverse` kwarg to `True`, the cumprod is performed in the
   *  opposite direction:
   *  <pre>{@code
   *  tf.cumprod([a, b, c], reverse=True)  # => [a * b * c, b * c, c]
   *  }</pre>
   *  This is more efficient than using separate `tf.reverse` ops.
   *  <p>
   *  The `reverse` and `exclusive` kwargs can also be combined:
   *  <pre>{@code
   *  tf.cumprod([a, b, c], exclusive=True, reverse=True)  # => [b * c, c, 1]
   *  }</pre>
   *
   * @param <T> data type for {@code out()} output
   * @param x A `Tensor`. Must be one of the following types: `float32`, `float64`,
   *  `int64`, `int32`, `uint8`, `uint16`, `int16`, `int8`, `complex64`,
   *  `complex128`, `qint8`, `quint8`, `qint32`, `half`.
   * @param axis A `Tensor` of type `int32` (default: 0). Must be in the range
   *  `[-rank(x), rank(x))`.
   * @param options carries optional attributes values
   * @return a new instance of Cumprod
   */
  public <T extends TType, U extends TNumber> Cumprod<T> cumprod(Operand<T> x, Operand<U> axis,
      Cumprod.Options... options) {
    return Cumprod.create(scope, x, axis, options);
  }

  /**
   * Compute the cumulative sum of the tensor `x` along `axis`.
   *  <p>
   *  By default, this op performs an inclusive cumsum, which means that the first
   *  element of the input is identical to the first element of the output:
   *  <pre>{@code
   *  tf.cumsum([a, b, c])  # => [a, a + b, a + b + c]
   *  }</pre>
   *  By setting the `exclusive` kwarg to `True`, an exclusive cumsum is
   *  performed instead:
   *  <pre>{@code
   *  tf.cumsum([a, b, c], exclusive=True)  # => [0, a, a + b]
   *  }</pre>
   *  By setting the `reverse` kwarg to `True`, the cumsum is performed in the
   *  opposite direction:
   *  <pre>{@code
   *  tf.cumsum([a, b, c], reverse=True)  # => [a + b + c, b + c, c]
   *  }</pre>
   *  This is more efficient than using separate `tf.reverse` ops.
   *  <p>
   *  The `reverse` and `exclusive` kwargs can also be combined:
   *  <pre>{@code
   *  tf.cumsum([a, b, c], exclusive=True, reverse=True)  # => [b + c, c, 0]
   *  }</pre>
   *
   * @param <T> data type for {@code out()} output
   * @param x A `Tensor`. Must be one of the following types: `float32`, `float64`,
   *  `int64`, `int32`, `uint8`, `uint16`, `int16`, `int8`, `complex64`,
   *  `complex128`, `qint8`, `quint8`, `qint32`, `half`.
   * @param axis A `Tensor` of type `int32` (default: 0). Must be in the range
   *  `[-rank(x), rank(x))`.
   * @param options carries optional attributes values
   * @return a new instance of Cumsum
   */
  public <T extends TType, U extends TNumber> Cumsum<T> cumsum(Operand<T> x, Operand<U> axis,
      Cumsum.Options... options) {
    return Cumsum.create(scope, x, axis, options);
  }

  /**
   * Counts the number of occurrences of each value in an integer array.
   *  <p>
   *  Outputs a vector with length `size` and the same dtype as `weights`. If
   *  `weights` are empty, then index `i` stores the number of times the value `i` is
   *  counted in `arr`. If `weights` are non-empty, then index `i` stores the sum of
   *  the value in `weights` at each index where the corresponding value in `arr` is
   *  `i`.
   *  <p>
   *  Values in `arr` outside of the range [0, size) are ignored.
   *
   * @param <U> data type for {@code output()} output
   * @param input 1D or 2D int `Tensor`.
   * @param size non-negative int scalar `Tensor`.
   * @param weights is an int32, int64, float32, or float64 `Tensor` with the same
   *  shape as `arr`, or a length-0 `Tensor`, in which case it acts as all weights
   *  equal to 1.
   * @param options carries optional attributes values
   * @return a new instance of DenseBincount
   */
  public <U extends TNumber, T extends TNumber> DenseBincount<U> denseBincount(Operand<T> input,
      Operand<T> size, Operand<U> weights, DenseBincount.Options... options) {
    return DenseBincount.create(scope, input, size, weights, options);
  }

  /**
   * Computes Psi, the derivative of Lgamma (the log of the absolute value of
   *  <p>
   *  `Gamma(x)`), element-wise.
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Digamma
   */
  public <T extends TNumber> Digamma<T> digamma(Operand<T> x) {
    return Digamma.create(scope, x);
  }

  /**
   * Returns x / y element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.Div` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of Div
   */
  public <T extends TType> Div<T> div(Operand<T> x, Operand<T> y) {
    return Div.create(scope, x, y);
  }

  /**
   * Returns 0 if the denominator is zero.
   *  <p>
   *
   *  <i>NOTE</i>: `math.DivNoNan` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of DivNoNan
   */
  public <T extends TType> DivNoNan<T> divNoNan(Operand<T> x, Operand<T> y) {
    return DivNoNan.create(scope, x, y);
  }

  /**
   * Returns the truth value of (x == y) element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.Equal` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *  <pre>{@code
   *  x = tf.constant([2, 4])
   *  y = tf.constant(2)
   *  tf.math.equal(x, y) ==> array([True, False])
   *
   *  x = tf.constant([2, 4])
   *  y = tf.constant([2, 4])
   *  tf.math.equal(x, y) ==> array([True,  True])
   *  }</pre>
   *
   * @param x
   * @param y
   * @param options carries optional attributes values
   * @return a new instance of Equal
   */
  public <T extends TType> Equal equal(Operand<T> x, Operand<T> y, Equal.Options... options) {
    return Equal.create(scope, x, y, options);
  }

  /**
   * Computes the Gauss error function of `x` element-wise.
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Erf
   */
  public <T extends TNumber> Erf<T> erf(Operand<T> x) {
    return Erf.create(scope, x);
  }

  /**
   * Computes the complementary error function of `x` element-wise.
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Erfc
   */
  public <T extends TNumber> Erfc<T> erfc(Operand<T> x) {
    return Erfc.create(scope, x);
  }

  /**
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of erfinv
   */
  public <T extends TNumber> erfinv<T> erfinv(Operand<T> x) {
    return erfinv.create(scope, x);
  }

  /**
   * Computes exponential of x element-wise.  \\(y = e^x\\).
   *  <p>
   *    This function computes the exponential of every element in the input tensor.
   *    i.e. `exp(x)` or `e^(x)`, where `x` is the input tensor.
   *    `e` denotes Euler's number and is approximately equal to 2.718281.
   *    Output is positive for any real input.
   *  <p>
   *    <pre>{@code
   *    x = tf.constant(2.0)
   *    tf.math.exp(x) ==> 7.389056
   *
   *    x = tf.constant([2.0, 8.0])
   *    tf.math.exp(x) ==> array([7.389056, 2980.958], dtype=float32)
   *    }</pre>
   *  For complex numbers, the exponential value is calculated as follows:
   *  <p>
   *    <pre>{@code
   *    e^(x+iy) = e^x * e^iy = e^x * (cos y + i sin y)
   *    }</pre>
   *  Let's consider complex number 1+1j as an example.
   *    e^1 * (cos 1 + i sin 1) = 2.7182818284590 * (0.54030230586+0.8414709848j)
   *  <p>
   *    <pre>{@code
   *    x = tf.constant(1 + 1j)
   *    tf.math.exp(x) ==> 1.4686939399158851+2.2873552871788423j
   *    }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Exp
   */
  public <T extends TType> Exp<T> exp(Operand<T> x) {
    return Exp.create(scope, x);
  }

  /**
   * Computes `exp(x) - 1` element-wise.
   *  <p>
   *    i.e. `exp(x) - 1` or `e^(x) - 1`, where `x` is the input tensor.
   *    `e` denotes Euler's number and is approximately equal to 2.718281.
   *  <p>
   *    <pre>{@code
   *    x = tf.constant(2.0)
   *    tf.math.expm1(x) ==> 6.389056
   *
   *    x = tf.constant([2.0, 8.0])
   *    tf.math.expm1(x) ==> array([6.389056, 2979.958], dtype=float32)
   *
   *    x = tf.constant(1 + 1j)
   *    tf.math.expm1(x) ==> (0.46869393991588515+2.2873552871788423j)
   *    }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Expm1
   */
  public <T extends TType> Expm1<T> expm1(Operand<T> x) {
    return Expm1.create(scope, x);
  }

  /**
   * Output a fact about factorials.
   *
   * @return a new instance of Fact
   */
  public Fact fact() {
    return Fact.create(scope);
  }

  /**
   * Returns element-wise largest integer not greater than x.
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Floor
   */
  public <T extends TNumber> Floor<T> floor(Operand<T> x) {
    return Floor.create(scope, x);
  }

  /**
   * Returns x // y element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.FloorDiv` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of FloorDiv
   */
  public <T extends TType> FloorDiv<T> floorDiv(Operand<T> x, Operand<T> y) {
    return FloorDiv.create(scope, x, y);
  }

  /**
   * Returns element-wise remainder of division. When `x < 0` xor `y < 0` is
   *  <p>
   *  true, this follows Python semantics in that the result here is consistent
   *  with a flooring divide. E.g. `floor(x / y) * y + mod(x, y) = x`.
   *  <p>
   *  <i>NOTE</i>: `math.FloorMod` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of FloorMod
   */
  public <T extends TNumber> FloorMod<T> floorMod(Operand<T> x, Operand<T> y) {
    return FloorMod.create(scope, x, y);
  }

  /**
   * Returns the truth value of (x > y) element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.Greater` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *  <p>
   *  Example:
   *  <pre>{@code
   *  x = tf.constant([5, 4, 6])
   *  y = tf.constant([5, 2, 5])
   *  tf.math.greater(x, y) ==> [False, True, True]
   *
   *  x = tf.constant([5, 4, 6])
   *  y = tf.constant([5])
   *  tf.math.greater(x, y) ==> [False, False, True]
   *  }</pre>
   *
   * @param x
   * @param y
   * @return a new instance of Greater
   */
  public <T extends TNumber> Greater greater(Operand<T> x, Operand<T> y) {
    return Greater.create(scope, x, y);
  }

  /**
   * Returns the truth value of (x >= y) element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.GreaterEqual` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *  <p>
   *  Example:
   *  <pre>{@code
   *  x = tf.constant([5, 4, 6, 7])
   *  y = tf.constant([5, 2, 5, 10])
   *  tf.math.greater_equal(x, y) ==> [True, True, True, False]
   *
   *  x = tf.constant([5, 4, 6, 7])
   *  y = tf.constant([5])
   *  tf.math.greater_equal(x, y) ==> [True, False, True, True]
   *  }</pre>
   *
   * @param x
   * @param y
   * @return a new instance of GreaterEqual
   */
  public <T extends TNumber> GreaterEqual greaterEqual(Operand<T> x, Operand<T> y) {
    return GreaterEqual.create(scope, x, y);
  }

  /**
   * Compute the lower regularized incomplete Gamma function `P(a, x)`.
   *  <p>
   *  The lower regularized incomplete Gamma function is defined as:
   *  <p>
   *  \\(P(a, x) = gamma(a, x) / Gamma(a) = 1 - Q(a, x)\\)
   *  <p>
   *  where
   *  <p>
   *  \\(gamma(a, x) = \\int_{0}^{x} t^{a-1} exp(-t) dt\\)
   *  <p>
   *  is the lower incomplete Gamma function.
   *  <p>
   *  Note, above `Q(a, x)` (`Igammac`) is the upper regularized complete
   *  Gamma function.
   *
   * @param <T> data type for {@code z()} output
   * @param a
   * @param x
   * @return a new instance of Igamma
   */
  public <T extends TNumber> Igamma<T> igamma(Operand<T> a, Operand<T> x) {
    return Igamma.create(scope, a, x);
  }

  /**
   * Compute the upper regularized incomplete Gamma function `Q(a, x)`.
   *  <p>
   *  The upper regularized incomplete Gamma function is defined as:
   *  <p>
   *  \\(Q(a, x) = Gamma(a, x) / Gamma(a) = 1 - P(a, x)\\)
   *  <p>
   *  where
   *  <p>
   *  \\(Gamma(a, x) = int_{x}^{\infty} t^{a-1} exp(-t) dt\\)
   *  <p>
   *  is the upper incomplete Gama function.
   *  <p>
   *  Note, above `P(a, x)` (`Igamma`) is the lower regularized complete
   *  Gamma function.
   *
   * @param <T> data type for {@code z()} output
   * @param a
   * @param x
   * @return a new instance of Igammac
   */
  public <T extends TNumber> Igammac<T> igammac(Operand<T> a, Operand<T> x) {
    return Igammac.create(scope, a, x);
  }

  /**
   * Returns the imaginary part of a complex number.
   *  <p>
   *  Given a tensor `input` of complex numbers, this operation returns a tensor of
   *  type `float` that is the imaginary part of each element in `input`. All
   *  elements in `input` must be complex numbers of the form \\(a + bj\\), where <i>a</i>
   *  is the real part and <i>b</i> is the imaginary part returned by this operation.
   *  <p>
   *  For example:
   *  <pre>{@code
   *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
   *  tf.imag(input) ==> [4.75, 5.75]
   *  }</pre>
   *
   * @param <U> data type for {@code output()} output
   * @param input
   * @return a new instance of Imag
   */
  public <T extends TType> Imag<TFloat32> imag(Operand<T> input) {
    return Imag.create(scope, input);
  }

  /**
   * Returns the imaginary part of a complex number.
   *  <p>
   *  Given a tensor `input` of complex numbers, this operation returns a tensor of
   *  type `float` that is the imaginary part of each element in `input`. All
   *  elements in `input` must be complex numbers of the form \\(a + bj\\), where <i>a</i>
   *  is the real part and <i>b</i> is the imaginary part returned by this operation.
   *  <p>
   *  For example:
   *  <pre>{@code
   *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
   *  tf.imag(input) ==> [4.75, 5.75]
   *  }</pre>
   *
   * @param <U> data type for {@code output()} output
   * @param input
   * @param Tout
   * @return a new instance of Imag
   */
  public <U extends TNumber, T extends TType> Imag<U> imag(Operand<T> input, Class<U> Tout) {
    return Imag.create(scope, input, Tout);
  }

  /**
   * Computes the inverse permutation of a tensor.
   *  <p>
   *  This operation computes the inverse of an index permutation. It takes a 1-D
   *  integer tensor `x`, which represents the indices of a zero-based array, and
   *  swaps each value with its index position. In other words, for an output tensor
   *  `y` and an input tensor `x`, this operation computes the following:
   *  <p>
   *  `y[x[i]] = i for i in [0, 1, ..., len(x) - 1]`
   *  <p>
   *  The values must include 0. There can be no duplicate values or negative values.
   *  <p>
   *  For example:
   *  <pre>{@code
   *  # tensor `x` is [3, 4, 0, 2, 1]
   *  invert_permutation(x) ==> [2, 4, 3, 0, 1]
   *  }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x 1-D.
   * @return a new instance of InvertPermutation
   */
  public <T extends TNumber> InvertPermutation<T> invertPermutation(Operand<T> x) {
    return InvertPermutation.create(scope, x);
  }

  /**
   * Returns which elements of x are finite.
   *  <p>
   *
   * @compatibility(numpy) Equivalent to np.isfinite
   * @end_compatibility <p>
   *  Example:
   *  <pre>{@code
   *  x = tf.constant([5.0, 4.8, 6.8, np.inf, np.nan])
   *  tf.math.is_finite(x) ==> [True, True, True, False, False]
   *  }</pre>
   * @param x
   * @return a new instance of IsFinite
   */
  public <T extends TNumber> IsFinite isFinite(Operand<T> x) {
    return IsFinite.create(scope, x);
  }

  /**
   * Returns which elements of x are Inf.
   *  <p>
   *
   * @compatibility(numpy) Equivalent to np.isinf
   * @end_compatibility <p>
   *  Example:
   *  <pre>{@code
   *  x = tf.constant([5.0, np.inf, 6.8, np.inf])
   *  tf.math.is_inf(x) ==> [False, True, False, True]
   *  }</pre>
   * @param x
   * @return a new instance of IsInf
   */
  public <T extends TNumber> IsInf isInf(Operand<T> x) {
    return IsInf.create(scope, x);
  }

  /**
   * Returns which elements of x are NaN.
   *  <p>
   *
   * @compatibility(numpy) Equivalent to np.isnan
   * @end_compatibility <p>
   *  Example:
   *  <pre>{@code
   *  x = tf.constant([5.0, np.nan, 6.8, np.nan, np.inf])
   *  tf.math.is_nan(x) ==> [False, True, False, True, False]
   *  }</pre>
   * @param x
   * @return a new instance of IsNan
   */
  public <T extends TNumber> IsNan isNan(Operand<T> x) {
    return IsNan.create(scope, x);
  }

  /**
   * Returns the truth value of (x < y) element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.Less` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *  <p>
   *  Example:
   *  <pre>{@code
   *  x = tf.constant([5, 4, 6])
   *  y = tf.constant([5])
   *  tf.math.less(x, y) ==> [False, True, False]
   *
   *  x = tf.constant([5, 4, 6])
   *  y = tf.constant([5, 6, 7])
   *  tf.math.less(x, y) ==> [False, True, True]
   *  }</pre>
   *
   * @param x
   * @param y
   * @return a new instance of Less
   */
  public <T extends TNumber> Less less(Operand<T> x, Operand<T> y) {
    return Less.create(scope, x, y);
  }

  /**
   * Returns the truth value of (x <= y) element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.LessEqual` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *  <p>
   *  Example:
   *  <pre>{@code
   *  x = tf.constant([5, 4, 6])
   *  y = tf.constant([5])
   *  tf.math.less_equal(x, y) ==> [True, True, False]
   *
   *  x = tf.constant([5, 4, 6])
   *  y = tf.constant([5, 6, 6])
   *  tf.math.less_equal(x, y) ==> [True, True, True]
   *  }</pre>
   *
   * @param x
   * @param y
   * @return a new instance of LessEqual
   */
  public <T extends TNumber> LessEqual lessEqual(Operand<T> x, Operand<T> y) {
    return LessEqual.create(scope, x, y);
  }

  /**
   * Computes the log of the absolute value of `Gamma(x)` element-wise.
   *  <p>
   *    For positive numbers, this function computes log((input - 1)!) for every element in the tensor.
   *    `lgamma(5) = log((5-1)!) = log(4!) = log(24) = 3.1780539`
   *  <p>
   *  Example:
   *  <pre>{@code
   *  x = tf.constant([0, 0.5, 1, 4.5, -4, -5.6])
   *  tf.math.lgamma(x) ==> [inf, 0.5723649, 0., 2.4537368, inf, -4.6477685]
   *  }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Lgamma
   */
  public <T extends TNumber> Lgamma<T> lgamma(Operand<T> x) {
    return Lgamma.create(scope, x);
  }

  /**
   * Computes natural logarithm of x element-wise.
   *  <p>
   *  I.e., \\(y = \log_e x\\).
   *  <p>
   *  Example:
   *  <pre>{@code
   *  x = tf.constant([0, 0.5, 1, 5])
   *  tf.math.log(x) ==> [-inf, -0.6931472,  0. ,  1.609438]
   *  }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Log
   */
  public <T extends TType> Log<T> log(Operand<T> x) {
    return Log.create(scope, x);
  }

  /**
   * Computes natural logarithm of (1 + x) element-wise.
   *  <p>
   *  I.e., \\(y = \log_e (1 + x)\\).
   *  <p>
   *  Example:
   *  <pre>{@code
   *  x = tf.constant([0, 0.5, 1, 5])
   *  tf.math.log1p(x) ==> [0., 0.4054651, 0.6931472, 1.7917595]
   *  }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Log1p
   */
  public <T extends TType> Log1p<T> log1p(Operand<T> x) {
    return Log1p.create(scope, x);
  }

  /**
   * Returns the truth value of x AND y element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.LogicalAnd` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param x
   * @param y
   * @return a new instance of LogicalAnd
   */
  public LogicalAnd logicalAnd(Operand<TBool> x, Operand<TBool> y) {
    return LogicalAnd.create(scope, x, y);
  }

  /**
   * Returns the truth value of `NOT x` element-wise.
   *
   * @param x A `Tensor` of type `bool`.
   * @return a new instance of LogicalNot
   */
  public LogicalNot logicalNot(Operand<TBool> x) {
    return LogicalNot.create(scope, x);
  }

  /**
   * Returns the truth value of x OR y element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.LogicalOr` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param x
   * @param y
   * @return a new instance of LogicalOr
   */
  public LogicalOr logicalOr(Operand<TBool> x, Operand<TBool> y) {
    return LogicalOr.create(scope, x, y);
  }

  /**
   * Returns the max of x and y (i.e. x > y ? x : y) element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.Maximum` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of Maximum
   */
  public <T extends TNumber> Maximum<T> maximum(Operand<T> x, Operand<T> y) {
    return Maximum.create(scope, x, y);
  }

  /**
   * Computes the mean of elements across dimensions of a tensor.
   *  <p>
   *  Reduces `input` along the dimensions given in `axis`. Unless
   *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
   *  `axis`. If `keep_dims` is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param <T> data type for {@code output()} output
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  `[-rank(input), rank(input))`.
   * @param options carries optional attributes values
   * @return a new instance of Mean
   */
  public <T extends TType, U extends TNumber> Mean<T> mean(Operand<T> input, Operand<U> axis,
      Mean.Options... options) {
    return Mean.create(scope, input, axis, options);
  }

  /**
   * Returns the min of x and y (i.e. x < y ? x : y) element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.Minimum` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of Minimum
   */
  public <T extends TNumber> Minimum<T> minimum(Operand<T> x, Operand<T> y) {
    return Minimum.create(scope, x, y);
  }

  /**
   * Returns element-wise remainder of division. This emulates C semantics in that
   *  <p>
   *  the result here is consistent with a truncating divide. E.g.
   *  `tf.truncatediv(x, y) * y + truncate_mod(x, y) = x`.
   *  <p>
   *  <i>NOTE</i>: `math.Mod` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of Mod
   */
  public <T extends TNumber> Mod<T> mod(Operand<T> x, Operand<T> y) {
    return Mod.create(scope, x, y);
  }

  /**
   * Returns x * y element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.Mul` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of Mul
   */
  public <T extends TType> Mul<T> mul(Operand<T> x, Operand<T> y) {
    return Mul.create(scope, x, y);
  }

  /**
   * Returns x * y element-wise. Returns zero if y is zero, even if x if infinite or NaN.
   *  <p>
   *  <i>NOTE</i>: `math.MulNoNan` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of MulNoNan
   */
  public <T extends TType> MulNoNan<T> mulNoNan(Operand<T> x, Operand<T> y) {
    return MulNoNan.create(scope, x, y);
  }

  /**
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Ndtri
   */
  public <T extends TNumber> Ndtri<T> ndtri(Operand<T> x) {
    return Ndtri.create(scope, x);
  }

  /**
   * Computes numerical negative value element-wise.
   *  <p>
   *  I.e., \\(y = -x\\).
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Neg
   */
  public <T extends TType> Neg<T> neg(Operand<T> x) {
    return Neg.create(scope, x);
  }

  /**
   * Returns the next representable value of `x1` in the direction of `x2`, element-wise.
   *  <p>
   *  This operation returns the same result as the C++ std::nextafter function.
   *  <p>
   *  It can also return a subnormal number.
   *  <p>
   *
   * @compatibility(cpp) Equivalent to C++ std::nextafter function.
   * @end_compatibility
   * @param <T> data type for {@code output()} output
   * @param x1
   * @param x2
   * @return a new instance of NextAfter
   */
  public <T extends TNumber> NextAfter<T> nextAfter(Operand<T> x1, Operand<T> x2) {
    return NextAfter.create(scope, x1, x2);
  }

  /**
   * Returns the truth value of (x != y) element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.NotEqual` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param x
   * @param y
   * @param options carries optional attributes values
   * @return a new instance of NotEqual
   */
  public <T extends TType> NotEqual notEqual(Operand<T> x, Operand<T> y,
      NotEqual.Options... options) {
    return NotEqual.create(scope, x, y, options);
  }

  /**
   * Compute the polygamma function \\(\psi^{(n)}(x)\\).
   *  <p>
   *  The polygamma function is defined as:
   *  <p>
   *  \\(\psi^{(a)}(x) = \frac{d^a}{dx^a} \psi(x)\\)
   *  <p>
   *  where \\(\psi(x)\\) is the digamma function.
   *  The polygamma function is defined only for non-negative integer orders \\a\\.
   *
   * @param <T> data type for {@code z()} output
   * @param a
   * @param x
   * @return a new instance of Polygamma
   */
  public <T extends TNumber> Polygamma<T> polygamma(Operand<T> a, Operand<T> x) {
    return Polygamma.create(scope, a, x);
  }

  /**
   * Computes element-wise population count (a.k.a. popcount, bitsum, bitcount).
   *  <p>
   *  For each entry in `x`, calculates the number of `1` (on) bits in the binary
   *  representation of that entry.
   *  <p>
   *  <b>NOTE</b>: It is more efficient to first `tf.bitcast` your tensors into
   *  `int32` or `int64` and perform the bitcount on the result, than to feed in
   *  8- or 16-bit inputs and then aggregate the resulting counts.
   *
   * @param x
   * @return a new instance of PopulationCount
   */
  public <T extends TNumber> PopulationCount populationCount(Operand<T> x) {
    return PopulationCount.create(scope, x);
  }

  /**
   * Computes the power of one value to another.
   *  <p>
   *  Given a tensor `x` and a tensor `y`, this operation computes \\(x^y\\) for
   *  corresponding elements in `x` and `y`. For example:
   *  <pre>{@code
   *  # tensor 'x' is [[2, 2]], [3, 3]]
   *  # tensor 'y' is [[8, 16], [2, 3]]
   *  tf.pow(x, y) ==> [[256, 65536], [9, 27]]
   *  }</pre>
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of Pow
   */
  public <T extends TType> Pow<T> pow(Operand<T> x, Operand<T> y) {
    return Pow.create(scope, x, y);
  }

  /**
   * Returns x + y element-wise, working on quantized buffers.
   *
   * @param <V> data type for {@code z()} output
   * @param x
   * @param y
   * @param minX The float value that the lowest quantized `x` value represents.
   * @param maxX The float value that the highest quantized `x` value represents.
   * @param minY The float value that the lowest quantized `y` value represents.
   * @param maxY The float value that the highest quantized `y` value represents.
   * @param Toutput
   * @return a new instance of QuantizedAdd
   */
  public <V extends TType, T extends TType, U extends TType> QuantizedAdd<V> quantizedAdd(
      Operand<T> x, Operand<U> y, Operand<TFloat32> minX, Operand<TFloat32> maxX,
      Operand<TFloat32> minY, Operand<TFloat32> maxY, Class<V> Toutput) {
    return QuantizedAdd.create(scope, x, y, minX, maxX, minY, maxY, Toutput);
  }

  /**
   * Returns x * y element-wise, working on quantized buffers.
   *
   * @param <V> data type for {@code z()} output
   * @param x
   * @param y
   * @param minX The float value that the lowest quantized `x` value represents.
   * @param maxX The float value that the highest quantized `x` value represents.
   * @param minY The float value that the lowest quantized `y` value represents.
   * @param maxY The float value that the highest quantized `y` value represents.
   * @param Toutput
   * @return a new instance of QuantizedMul
   */
  public <V extends TType, T extends TType, U extends TType> QuantizedMul<V> quantizedMul(
      Operand<T> x, Operand<U> y, Operand<TFloat32> minX, Operand<TFloat32> maxX,
      Operand<TFloat32> minY, Operand<TFloat32> maxY, Class<V> Toutput) {
    return QuantizedMul.create(scope, x, y, minX, maxX, minY, maxY, Toutput);
  }

  /**
   * Returns the real part of a complex number.
   *  <p>
   *  Given a tensor `input` of complex numbers, this operation returns a tensor of
   *  type `float` that is the real part of each element in `input`. All elements in
   *  `input` must be complex numbers of the form \\(a + bj\\), where <i>a</i> is the real
   *   part returned by this operation and <i>b</i> is the imaginary part.
   *  <p>
   *  For example:
   *  <pre>{@code
   *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
   *  tf.real(input) ==> [-2.25, 3.25]
   *  }</pre>
   *
   * @param <U> data type for {@code output()} output
   * @param input
   * @return a new instance of Real
   */
  public <T extends TType> Real<TFloat32> real(Operand<T> input) {
    return Real.create(scope, input);
  }

  /**
   * Returns the real part of a complex number.
   *  <p>
   *  Given a tensor `input` of complex numbers, this operation returns a tensor of
   *  type `float` that is the real part of each element in `input`. All elements in
   *  `input` must be complex numbers of the form \\(a + bj\\), where <i>a</i> is the real
   *   part returned by this operation and <i>b</i> is the imaginary part.
   *  <p>
   *  For example:
   *  <pre>{@code
   *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
   *  tf.real(input) ==> [-2.25, 3.25]
   *  }</pre>
   *
   * @param <U> data type for {@code output()} output
   * @param input
   * @param Tout
   * @return a new instance of Real
   */
  public <U extends TNumber, T extends TType> Real<U> real(Operand<T> input, Class<U> Tout) {
    return Real.create(scope, input, Tout);
  }

  /**
   * Returns x / y element-wise for real types.
   *  <p>
   *  If `x` and `y` are reals, this will return the floating-point division.
   *  <p>
   *  <i>NOTE</i>: `Div` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of RealDiv
   */
  public <T extends TType> RealDiv<T> realDiv(Operand<T> x, Operand<T> y) {
    return RealDiv.create(scope, x, y);
  }

  /**
   * Computes the reciprocal of x element-wise.
   *  <p>
   *  I.e., \\(y = 1 / x\\).
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Reciprocal
   */
  public <T extends TType> Reciprocal<T> reciprocal(Operand<T> x) {
    return Reciprocal.create(scope, x);
  }

  /**
   * Returns element-wise integer closest to x.
   *  <p>
   *  If the result is midway between two representable values,
   *  the even representable is chosen.
   *  For example:
   *  <pre>{@code
   *  rint(-1.5) ==> -2.0
   *  rint(0.5000001) ==> 1.0
   *  rint([-1.7, -1.5, -0.2, 0.2, 1.5, 1.7, 2.0]) ==> [-2., -2., -0., 0., 2., 2., 2.]
   *  }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Rint
   */
  public <T extends TNumber> Rint<T> rint(Operand<T> x) {
    return Rint.create(scope, x);
  }

  /**
   * Rounds the values of a tensor to the nearest integer, element-wise.
   *  <p>
   *  Rounds half to even.  Also known as bankers rounding. If you want to round
   *  according to the current system rounding mode use std::cint.
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Round
   */
  public <T extends TType> Round<T> round(Operand<T> x) {
    return Round.create(scope, x);
  }

  /**
   * Computes reciprocal of square root of x element-wise.
   *  <p>
   *  I.e., \\(y = 1 / \sqrt{x}\\).
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Rsqrt
   */
  public <T extends TType> Rsqrt<T> rsqrt(Operand<T> x) {
    return Rsqrt.create(scope, x);
  }

  /**
   * Computes the maximum along segments of a tensor.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
   *  for an explanation of segments.
   *  <p>
   *  Computes a tensor such that
   *  \\(output_i = \max_j(data_j)\\) where `max` is over `j` such
   *  that `segment_ids[j] == i`.
   *  <p>
   *  If the max is empty for a given segment ID `i`, `output[i] = 0`.
   *  <p>
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentMax.png" alt>
   *  </div>
   *  <p>
   *  For example:
   *  <pre>{@code
   *  c = tf.constant([[1,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
   *  tf.segment_max(c, tf.constant([0, 0, 1]))
   *  # ==> [[4, 3, 3, 4],
   *  #      [5, 6, 7, 8]]
   *  }</pre>
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
   *  first dimension.  Values should be sorted and can be repeated.
   * @return a new instance of SegmentMax
   */
  public <T extends TNumber, U extends TNumber> SegmentMax<T> segmentMax(Operand<T> data,
      Operand<U> segmentIds) {
    return SegmentMax.create(scope, data, segmentIds);
  }

  /**
   * Computes the mean along segments of a tensor.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
   *  for an explanation of segments.
   *  <p>
   *  Computes a tensor such that
   *  \\(output_i = \frac{\sum_j data_j}{N}\\) where `mean` is
   *  over `j` such that `segment_ids[j] == i` and `N` is the total number of
   *  values summed.
   *  <p>
   *  If the mean is empty for a given segment ID `i`, `output[i] = 0`.
   *  <p>
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentMean.png" alt>
   *  </div>
   *  <p>
   *  For example:
   *  <pre>{@code
   *  c = tf.constant([[1.0,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
   *  tf.segment_mean(c, tf.constant([0, 0, 1]))
   *  # ==> [[2.5, 2.5, 2.5, 2.5],
   *  #      [5, 6, 7, 8]]
   *  }</pre>
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
   *  first dimension.  Values should be sorted and can be repeated.
   * @return a new instance of SegmentMean
   */
  public <T extends TType, U extends TNumber> SegmentMean<T> segmentMean(Operand<T> data,
      Operand<U> segmentIds) {
    return SegmentMean.create(scope, data, segmentIds);
  }

  /**
   * Computes the minimum along segments of a tensor.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
   *  for an explanation of segments.
   *  <p>
   *  Computes a tensor such that
   *  \\(output_i = \min_j(data_j)\\) where `min` is over `j` such
   *  that `segment_ids[j] == i`.
   *  <p>
   *  If the min is empty for a given segment ID `i`, `output[i] = 0`.
   *  <p>
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentMin.png" alt>
   *  </div>
   *  <p>
   *  For example:
   *  <pre>{@code
   *  c = tf.constant([[1,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
   *  tf.segment_min(c, tf.constant([0, 0, 1]))
   *  # ==> [[1, 2, 2, 1],
   *  #      [5, 6, 7, 8]]
   *  }</pre>
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
   *  first dimension.  Values should be sorted and can be repeated.
   * @return a new instance of SegmentMin
   */
  public <T extends TNumber, U extends TNumber> SegmentMin<T> segmentMin(Operand<T> data,
      Operand<U> segmentIds) {
    return SegmentMin.create(scope, data, segmentIds);
  }

  /**
   * Computes the product along segments of a tensor.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
   *  for an explanation of segments.
   *  <p>
   *  Computes a tensor such that
   *  \\(output_i = \prod_j data_j\\) where the product is over `j` such
   *  that `segment_ids[j] == i`.
   *  <p>
   *  If the product is empty for a given segment ID `i`, `output[i] = 1`.
   *  <p>
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentProd.png" alt>
   *  </div>
   *  <p>
   *  For example:
   *  <pre>{@code
   *  c = tf.constant([[1,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
   *  tf.segment_prod(c, tf.constant([0, 0, 1]))
   *  # ==> [[4, 6, 6, 4],
   *  #      [5, 6, 7, 8]]
   *  }</pre>
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
   *  first dimension.  Values should be sorted and can be repeated.
   * @return a new instance of SegmentProd
   */
  public <T extends TType, U extends TNumber> SegmentProd<T> segmentProd(Operand<T> data,
      Operand<U> segmentIds) {
    return SegmentProd.create(scope, data, segmentIds);
  }

  /**
   * Computes the sum along segments of a tensor.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
   *  for an explanation of segments.
   *  <p>
   *  Computes a tensor such that
   *  \\(output_i = \sum_j data_j\\) where sum is over `j` such
   *  that `segment_ids[j] == i`.
   *  <p>
   *  If the sum is empty for a given segment ID `i`, `output[i] = 0`.
   *  <p>
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentSum.png" alt>
   *  </div>
   *  <p>
   *  For example:
   *  <pre>{@code
   *  c = tf.constant([[1,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
   *  tf.segment_sum(c, tf.constant([0, 0, 1]))
   *  # ==> [[5, 5, 5, 5],
   *  #      [5, 6, 7, 8]]
   *  }</pre>
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
   *  first dimension.  Values should be sorted and can be repeated.
   * @return a new instance of SegmentSum
   */
  public <T extends TType, U extends TNumber> SegmentSum<T> segmentSum(Operand<T> data,
      Operand<U> segmentIds) {
    return SegmentSum.create(scope, data, segmentIds);
  }

  /**
   * Computes sigmoid of `x` element-wise.
   *  <p>
   *  Specifically, `y = 1 / (1 + exp(-x))`.
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Sigmoid
   */
  public <T extends TType> Sigmoid<T> sigmoid(Operand<T> x) {
    return Sigmoid.create(scope, x);
  }

  /**
   * Returns an element-wise indication of the sign of a number.
   *  <p>
   *  `y = sign(x) = -1` if `x < 0`; 0 if `x == 0`; 1 if `x > 0`.
   *  <p>
   *  For complex numbers, `y = sign(x) = x / |x|` if `x != 0`, otherwise `y = 0`.
   *  <p>
   *  Example usage:
   *  >>> tf.math.sign([0., 2., -3.])
   *  <tf.Tensor: shape=(3,), dtype=float32, numpy=array([ 0.,  1., -1.], dtype=float32)>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Sign
   */
  public <T extends TType> Sign<T> sign(Operand<T> x) {
    return Sign.create(scope, x);
  }

  /**
   * Computes sine of x element-wise.
   *  <p>
   *    Given an input tensor, this function computes sine of every
   *    element in the tensor. Input range is `(-inf, inf)` and
   *    output range is `[-1,1]`.
   *  <p>
   *    <pre>{@code
   *    x = tf.constant([-float("inf"), -9, -0.5, 1, 1.2, 200, 10, float("inf")])
   *    tf.math.sin(x) ==> [nan -0.4121185 -0.47942555 0.84147096 0.9320391 -0.87329733 -0.54402107 nan]
   *    }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Sin
   */
  public <T extends TType> Sin<T> sin(Operand<T> x) {
    return Sin.create(scope, x);
  }

  /**
   * Computes hyperbolic sine of x element-wise.
   *  <p>
   *    Given an input tensor, this function computes hyperbolic sine of every
   *    element in the tensor. Input range is `[-inf,inf]` and output range
   *    is `[-inf,inf]`.
   *  <p>
   *    <pre>{@code
   *    x = tf.constant([-float("inf"), -9, -0.5, 1, 1.2, 2, 10, float("inf")])
   *    tf.math.sinh(x) ==> [-inf -4.0515420e+03 -5.2109528e-01 1.1752012e+00 1.5094614e+00 3.6268604e+00 1.1013232e+04 inf]
   *    }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Sinh
   */
  public <T extends TType> Sinh<T> sinh(Operand<T> x) {
    return Sinh.create(scope, x);
  }

  /**
   * Computes softplus: `log(exp(features) + 1)`.
   *
   * @param <T> data type for {@code activations()} output
   * @param features
   * @return a new instance of Softplus
   */
  public <T extends TNumber> Softplus<T> softplus(Operand<T> features) {
    return Softplus.create(scope, features);
  }

  /**
   * Computes square root of x element-wise.
   *  <p>
   *  I.e., \\(y = \sqrt{x} = x^{1/2}\\).
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Sqrt
   */
  public <T extends TType> Sqrt<T> sqrt(Operand<T> x) {
    return Sqrt.create(scope, x);
  }

  /**
   * Computes square of x element-wise.
   *  <p>
   *  I.e., \\(y = x * x = x^2\\).
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Square
   */
  public <T extends TType> Square<T> square(Operand<T> x) {
    return Square.create(scope, x);
  }

  /**
   * Returns (x - y)(x - y) element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.SquaredDifference` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of SquaredDifference
   */
  public <T extends TType> SquaredDifference<T> squaredDifference(Operand<T> x, Operand<T> y) {
    return SquaredDifference.create(scope, x, y);
  }

  /**
   * Returns x - y element-wise.
   *  <p>
   *  <i>NOTE</i>: `math.Sub` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of Sub
   */
  public <T extends TType> Sub<T> sub(Operand<T> x, Operand<T> y) {
    return Sub.create(scope, x, y);
  }

  /**
   * Computes tan of x element-wise.
   *  <p>
   *    Given an input tensor, this function computes tangent of every
   *    element in the tensor. Input range is `(-inf, inf)` and
   *    output range is `(-inf, inf)`. If input lies outside the boundary, `nan`
   *    is returned.
   *  <p>
   *    <pre>{@code
   *    x = tf.constant([-float("inf"), -9, -0.5, 1, 1.2, 200, 10000, float("inf")])
   *    tf.math.tan(x) ==> [nan 0.45231566 -0.5463025 1.5574077 2.572152 -1.7925274 0.32097113 nan]
   *    }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Tan
   */
  public <T extends TType> Tan<T> tan(Operand<T> x) {
    return Tan.create(scope, x);
  }

  /**
   * Computes hyperbolic tangent of `x` element-wise.
   *  <p>
   *    Given an input tensor, this function computes hyperbolic tangent of every
   *    element in the tensor. Input range is `[-inf, inf]` and
   *    output range is `[-1,1]`.
   *  <p>
   *    <pre>{@code
   *    x = tf.constant([-float("inf"), -5, -0.5, 1, 1.2, 2, 3, float("inf")])
   *    tf.math.tanh(x) ==> [-1. -0.99990916 -0.46211717 0.7615942 0.8336547 0.9640276 0.9950547 1.]
   *    }</pre>
   *
   * @param <T> data type for {@code y()} output
   * @param x
   * @return a new instance of Tanh
   */
  public <T extends TType> Tanh<T> tanh(Operand<T> x) {
    return Tanh.create(scope, x);
  }

  /**
   * Returns x / y element-wise for integer types.
   *  <p>
   *  Truncation designates that negative numbers will round fractional quantities
   *  toward zero. I.e. -7 / 5 = -1. This matches C semantics but it is different
   *  than Python semantics. See `FloorDiv` for a division function that matches
   *  Python Semantics.
   *  <p>
   *  <i>NOTE</i>: `math.TruncateDiv` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of TruncateDiv
   */
  public <T extends TType> TruncateDiv<T> truncateDiv(Operand<T> x, Operand<T> y) {
    return TruncateDiv.create(scope, x, y);
  }

  /**
   * Returns element-wise remainder of division. This emulates C semantics in that
   *  <p>
   *  the result here is consistent with a truncating divide. E.g. `truncate(x / y) *
   *  y + truncate_mod(x, y) = x`.
   *  <p>
   *  <i>NOTE</i>: `math.TruncateMod` supports broadcasting. More about broadcasting
   *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of TruncateMod
   */
  public <T extends TNumber> TruncateMod<T> truncateMod(Operand<T> x, Operand<T> y) {
    return TruncateMod.create(scope, x, y);
  }

  /**
   * Computes the maximum along segments of a tensor.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
   *  for an explanation of segments.
   *  <p>
   *  This operator is similar to the unsorted segment sum operator found
   *  [(here)](../../../api_docs/python/math_ops.md#UnsortedSegmentSum).
   *  Instead of computing the sum over segments, it computes the maximum such that:
   *  <p>
   *  \\(output_i = \max_{j...} data[j...]\\) where max is over tuples `j...` such
   *  that `segment_ids[j...] == i`.
   *  <p>
   *  If the maximum is empty for a given segment ID `i`, it outputs the smallest
   *  possible value for the specific numeric type,
   *  `output[i] = numeric_limits<T>::lowest()`.
   *  <p>
   *  If the given segment ID `i` is negative, then the corresponding value is
   *  dropped, and will not be included in the result.
   *  <p>
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/UnsortedSegmentMax.png" alt>
   *  </div>
   *  <p>
   *  For example:
   *  <pre>{@code
   *  c = tf.constant([[1,2,3,4], [5,6,7,8], [4,3,2,1]])
   *  tf.unsorted_segment_max(c, tf.constant([0, 1, 0]), num_segments=2)
   *  # ==> [[ 4,  3, 3, 4],
   *  #       [5,  6, 7, 8]]
   *  }</pre>
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
   * @param numSegments
   * @return a new instance of UnsortedSegmentMax
   */
  public <T extends TNumber, U extends TNumber, V extends TNumber> UnsortedSegmentMax<T> unsortedSegmentMax(
      Operand<T> data, Operand<U> segmentIds, Operand<V> numSegments) {
    return UnsortedSegmentMax.create(scope, data, segmentIds, numSegments);
  }

  /**
   * Computes the minimum along segments of a tensor.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
   *  for an explanation of segments.
   *  <p>
   *  This operator is similar to the unsorted segment sum operator found
   *  [(here)](../../../api_docs/python/math_ops.md#UnsortedSegmentSum).
   *  Instead of computing the sum over segments, it computes the minimum such that:
   *  <p>
   *  \\(output_i = \min_{j...} data_[j...]\\) where min is over tuples `j...` such
   *  that `segment_ids[j...] == i`.
   *  <p>
   *  If the minimum is empty for a given segment ID `i`, it outputs the largest
   *  possible value for the specific numeric type,
   *  `output[i] = numeric_limits<T>::max()`.
   *  <p>
   *  For example:
   *  <pre>{@code
   *  c = tf.constant([[1,2,3,4], [5,6,7,8], [4,3,2,1]])
   *  tf.unsorted_segment_min(c, tf.constant([0, 1, 0]), num_segments=2)
   *  # ==> [[ 1,  2, 2, 1],
   *  #       [5,  6, 7, 8]]
   *  }</pre>
   *  If the given segment ID `i` is negative, then the corresponding value is
   *  dropped, and will not be included in the result.
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
   * @param numSegments
   * @return a new instance of UnsortedSegmentMin
   */
  public <T extends TNumber, U extends TNumber, V extends TNumber> UnsortedSegmentMin<T> unsortedSegmentMin(
      Operand<T> data, Operand<U> segmentIds, Operand<V> numSegments) {
    return UnsortedSegmentMin.create(scope, data, segmentIds, numSegments);
  }

  /**
   * Computes the product along segments of a tensor.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
   *  for an explanation of segments.
   *  <p>
   *  This operator is similar to the unsorted segment sum operator found
   *  [(here)](../../../api_docs/python/math_ops.md#UnsortedSegmentSum).
   *  Instead of computing the sum over segments, it computes the product of all
   *  entries belonging to a segment such that:
   *  <p>
   *  \\(output_i = \prod_{j...} data[j...]\\) where the product is over tuples
   *  `j...` such that `segment_ids[j...] == i`.
   *  <p>
   *  For example:
   *  <pre>{@code
   *  c = tf.constant([[1,2,3,4], [5,6,7,8], [4,3,2,1]])
   *  tf.unsorted_segment_prod(c, tf.constant([0, 1, 0]), num_segments=2)
   *  # ==> [[ 4,  6, 6, 4],
   *  #       [5,  6, 7, 8]]
   *  }</pre>
   *  If there is no entry for a given segment ID `i`, it outputs 1.
   *  <p>
   *  If the given segment ID `i` is negative, then the corresponding value is
   *  dropped, and will not be included in the result.
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
   * @param numSegments
   * @return a new instance of UnsortedSegmentProd
   */
  public <T extends TType, U extends TNumber, V extends TNumber> UnsortedSegmentProd<T> unsortedSegmentProd(
      Operand<T> data, Operand<U> segmentIds, Operand<V> numSegments) {
    return UnsortedSegmentProd.create(scope, data, segmentIds, numSegments);
  }

  /**
   * Computes the sum along segments of a tensor.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
   *  for an explanation of segments.
   *  <p>
   *  Computes a tensor such that
   *  \\(output[i] = \sum_{j...} data[j...]\\) where the sum is over tuples `j...` such
   *  that `segment_ids[j...] == i`.  Unlike `SegmentSum`, `segment_ids`
   *  need not be sorted and need not cover all values in the full
   *  range of valid values.
   *  <p>
   *  If the sum is empty for a given segment ID `i`, `output[i] = 0`.
   *  If the given segment ID `i` is negative, the value is dropped and will not be
   *  added to the sum of the segment.
   *  <p>
   *  `num_segments` should equal the number of distinct segment IDs.
   *  <p>
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/UnsortedSegmentSum.png" alt>
   *  </div>
   *  <pre>{@code
   *  c = tf.constant([[1,2,3,4], [5,6,7,8], [4,3,2,1]])
   *  tf.unsorted_segment_sum(c, tf.constant([0, 1, 0]), num_segments=2)
   *  # ==> [[ 5,  5, 5, 5],
   *  #       [5,  6, 7, 8]]
   *  }</pre>
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
   * @param numSegments
   * @return a new instance of UnsortedSegmentSum
   */
  public <T extends TType, U extends TNumber, V extends TNumber> UnsortedSegmentSum<T> unsortedSegmentSum(
      Operand<T> data, Operand<U> segmentIds, Operand<V> numSegments) {
    return UnsortedSegmentSum.create(scope, data, segmentIds, numSegments);
  }

  /**
   * Returns 0 if x == 0, and x / y otherwise, elementwise.
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of Xdivy
   */
  public <T extends TType> Xdivy<T> xdivy(Operand<T> x, Operand<T> y) {
    return Xdivy.create(scope, x, y);
  }

  /**
   * Returns 0 if x == 0, and x * log1p(y) otherwise, elementwise.
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of Xlog1py
   */
  public <T extends TType> Xlog1py<T> xlog1py(Operand<T> x, Operand<T> y) {
    return Xlog1py.create(scope, x, y);
  }

  /**
   * Returns 0 if x == 0, and x * log(y) otherwise, elementwise.
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param y
   * @return a new instance of Xlogy
   */
  public <T extends TType> Xlogy<T> xlogy(Operand<T> x, Operand<T> y) {
    return Xlogy.create(scope, x, y);
  }

  /**
   * Compute the Hurwitz zeta function \\(\zeta(x, q)\\).
   *  <p>
   *  The Hurwitz zeta function is defined as:
   *  <p>
   *  \\(\zeta(x, q) = \sum_{n=0}^{\infty} (q + n)^{-x}\\)
   *
   * @param <T> data type for {@code z()} output
   * @param x
   * @param q
   * @return a new instance of Zeta
   */
  public <T extends TNumber> Zeta<T> zeta(Operand<T> x, Operand<T> q) {
    return Zeta.create(scope, x, q);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
