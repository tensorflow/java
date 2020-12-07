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
package org.tensorflow.op.kotlin

import org.tensorflow.DataType
import org.tensorflow.Operand
import org.tensorflow.ndarray.Shape
import org.tensorflow.op.Scope
import org.tensorflow.op.math.Abs
import org.tensorflow.op.math.AccumulateN
import org.tensorflow.op.math.Acos
import org.tensorflow.op.math.Acosh
import org.tensorflow.op.math.Add
import org.tensorflow.op.math.AddN
import org.tensorflow.op.math.Angle
import org.tensorflow.op.math.ApproximateEqual
import org.tensorflow.op.math.ArgMax
import org.tensorflow.op.math.ArgMin
import org.tensorflow.op.math.Asin
import org.tensorflow.op.math.Asinh
import org.tensorflow.op.math.Atan
import org.tensorflow.op.math.Atan2
import org.tensorflow.op.math.Atanh
import org.tensorflow.op.math.Betainc
import org.tensorflow.op.math.Bincount
import org.tensorflow.op.math.Ceil
import org.tensorflow.op.math.CompareAndBitpack
import org.tensorflow.op.math.ComplexAbs
import org.tensorflow.op.math.Conj
import org.tensorflow.op.math.Cos
import org.tensorflow.op.math.Cosh
import org.tensorflow.op.math.Cumprod
import org.tensorflow.op.math.Cumsum
import org.tensorflow.op.math.DenseBincount
import org.tensorflow.op.math.Digamma
import org.tensorflow.op.math.Div
import org.tensorflow.op.math.DivNoNan
import org.tensorflow.op.math.Equal
import org.tensorflow.op.math.Erf
import org.tensorflow.op.math.Erfc
import org.tensorflow.op.math.Exp
import org.tensorflow.op.math.Expm1
import org.tensorflow.op.math.Fact
import org.tensorflow.op.math.Floor
import org.tensorflow.op.math.FloorDiv
import org.tensorflow.op.math.FloorMod
import org.tensorflow.op.math.Greater
import org.tensorflow.op.math.GreaterEqual
import org.tensorflow.op.math.Igamma
import org.tensorflow.op.math.Igammac
import org.tensorflow.op.math.Imag
import org.tensorflow.op.math.InvertPermutation
import org.tensorflow.op.math.IsFinite
import org.tensorflow.op.math.IsInf
import org.tensorflow.op.math.IsNan
import org.tensorflow.op.math.Less
import org.tensorflow.op.math.LessEqual
import org.tensorflow.op.math.Lgamma
import org.tensorflow.op.math.Log
import org.tensorflow.op.math.Log1p
import org.tensorflow.op.math.LogicalAnd
import org.tensorflow.op.math.LogicalNot
import org.tensorflow.op.math.LogicalOr
import org.tensorflow.op.math.Maximum
import org.tensorflow.op.math.Mean
import org.tensorflow.op.math.Minimum
import org.tensorflow.op.math.Mod
import org.tensorflow.op.math.Mul
import org.tensorflow.op.math.MulNoNan
import org.tensorflow.op.math.Ndtri
import org.tensorflow.op.math.Neg
import org.tensorflow.op.math.NextAfter
import org.tensorflow.op.math.NotEqual
import org.tensorflow.op.math.Polygamma
import org.tensorflow.op.math.PopulationCount
import org.tensorflow.op.math.Pow
import org.tensorflow.op.math.QuantizedAdd
import org.tensorflow.op.math.QuantizedMul
import org.tensorflow.op.math.Real
import org.tensorflow.op.math.RealDiv
import org.tensorflow.op.math.Reciprocal
import org.tensorflow.op.math.Rint
import org.tensorflow.op.math.Round
import org.tensorflow.op.math.Rsqrt
import org.tensorflow.op.math.SegmentMax
import org.tensorflow.op.math.SegmentMean
import org.tensorflow.op.math.SegmentMin
import org.tensorflow.op.math.SegmentProd
import org.tensorflow.op.math.SegmentSum
import org.tensorflow.op.math.Sigmoid
import org.tensorflow.op.math.Sign
import org.tensorflow.op.math.Sin
import org.tensorflow.op.math.Sinh
import org.tensorflow.op.math.Softplus
import org.tensorflow.op.math.Sqrt
import org.tensorflow.op.math.Square
import org.tensorflow.op.math.SquaredDifference
import org.tensorflow.op.math.Sub
import org.tensorflow.op.math.Tan
import org.tensorflow.op.math.Tanh
import org.tensorflow.op.math.TruncateDiv
import org.tensorflow.op.math.TruncateMod
import org.tensorflow.op.math.UnsortedSegmentMax
import org.tensorflow.op.math.UnsortedSegmentMin
import org.tensorflow.op.math.UnsortedSegmentProd
import org.tensorflow.op.math.UnsortedSegmentSum
import org.tensorflow.op.math.Xdivy
import org.tensorflow.op.math.Xlog1py
import org.tensorflow.op.math.Xlogy
import org.tensorflow.op.math.Zeta
import org.tensorflow.op.math.erfinv
import org.tensorflow.types.TBool
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building `math` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class MathOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.MathOps = ops.java.math

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Computes the absolute value of a tensor.
     *
     *  Given a tensor `x`, this operation returns a tensor containing the absolute
     *  value of each element in `x`. For example, if x is an input element and y is
     *  an output element, this operation computes \\(y = |x|\\).
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Abs
     * @see org.tensorflow.op.MathOps.abs
     */
    public fun <T : TNumber> abs(x: Operand<T>): Abs<T> = java.abs<T>(
        x
    )

    /**
     * Returns the element-wise sum of a list of tensors.
     *
     *  `tf.accumulate_n_v2` performs the same operation as `tf.add_n`, but does not
     *  wait for all of its inputs to be ready before beginning to sum. This can
     *  save memory if inputs are ready at different times, since minimum temporary
     *  storage is proportional to the output size rather than the inputs size.
     *
     *  Unlike the original `accumulate_n`, `accumulate_n_v2` is differentiable.
     *
     *  Returns a `Tensor` of same shape and type as the elements of `inputs`.
     *
     * @param T data type for ` sum()` output
     * @param inputs A list of `Tensor` objects, each with same shape and type.
     * @param shape Shape of elements of `inputs`.
     * @return a new instance of AccumulateN
     * @see org.tensorflow.op.MathOps.accumulateN
     */
    public fun <T : TType> accumulateN(inputs: Iterable<Operand<T>>, shape: Shape): AccumulateN<T> =
        java.accumulateN<T>(
            inputs,
            shape
        )

    /**
     * Computes acos of x element-wise.
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Acos
     * @see org.tensorflow.op.MathOps.acos
     */
    public fun <T : TType> acos(x: Operand<T>): Acos<T> = java.acos<T>(
        x
    )

    /**
     * Computes inverse hyperbolic cosine of x element-wise.
     *
     *  Given an input tensor, the function computes inverse hyperbolic cosine of every element.
     *  Input range is `&#91;1, inf]`. It returns `nan` if the input lies outside the range.
     *  ```
     *  x = tf.constant([-2, -0.5, 1, 1.2, 200, 10000, float("inf")])
     *  tf.math.acosh(x) ==> [nan nan 0. 0.62236255 5.9914584 9.903487 inf]
     *  ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Acosh
     * @see org.tensorflow.op.MathOps.acosh
     */
    public fun <T : TType> acosh(x: Operand<T>): Acosh<T> = java.acosh<T>(
        x
    )

    /**
     * Returns x + y element-wise.
     *
     *  <i>NOTE</i>: `math.Add` supports broadcasting. `AddN` does not. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of Add
     * @see org.tensorflow.op.MathOps.add
     */
    public fun <T : TType> add(x: Operand<T>, y: Operand<T>): Add<T> = java.add<T>(
        x,
        y
    )

    /**
     * Add all input tensors element wise.
     *
     *    Inputs must be of same size and shape.
     *
     *    ```
     *    x = [9, 7, 10]
     *    tf.math.add_n(x) ==> 26
     *    ```
     *
     *
     * @param T data type for ` sum()` output
     * @param inputs
     * @return a new instance of AddN
     * @see org.tensorflow.op.MathOps.addN
     */
    public fun <T : TType> addN(inputs: Iterable<Operand<T>>): AddN<T> = java.addN<T>(
        inputs
    )

    /**
     * Returns the argument of a complex number.
     *
     *  Given a tensor `input` of complex numbers, this operation returns a tensor of
     *  type `float` that is the argument of each element in `input`. All elements in
     *  `input` must be complex numbers of the form \\(a + bj\\), where <i>a</i>
     *  is the real part and <i>b</i> is the imaginary part.
     *
     *  The argument returned by this operation is of the form \\(atan2(b, a)\\).
     *
     *  For example:
     *  ```
     *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
     *  tf.angle(input) ==> [2.0132, 1.056]
     *  ```
     *
     *
     * @compatibility(numpy) Equivalent to np.angle.
     * @end_compatibility
     * @param U data type for ` output()` output
     * @param input
     * @return a new instance of Angle
     * @see org.tensorflow.op.MathOps.angle
     */
    public fun <T : TType> angle(input: Operand<T>): Angle<TFloat32> = java.angle<T>(
        input
    )

    /**
     * Returns the argument of a complex number.
     *
     *  Given a tensor `input` of complex numbers, this operation returns a tensor of
     *  type `float` that is the argument of each element in `input`. All elements in
     *  `input` must be complex numbers of the form \\(a + bj\\), where <i>a</i>
     *  is the real part and <i>b</i> is the imaginary part.
     *
     *  The argument returned by this operation is of the form \\(atan2(b, a)\\).
     *
     *  For example:
     *  ```
     *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
     *  tf.angle(input) ==> [2.0132, 1.056]
     *  ```
     *
     *
     * @compatibility(numpy) Equivalent to np.angle.
     * @end_compatibility
     * @param U data type for ` output()` output
     * @param input
     * @param Tout
     * @return a new instance of Angle
     * @see org.tensorflow.op.MathOps.angle
     */
    public fun <U : TNumber, T : TType> angle(input: Operand<T>, Tout: DataType<U>): Angle<U> =
        java.angle<U, T>(
            input,
            Tout
        )

    /**
     * Returns the truth value of abs(x-y) < tolerance element-wise.
     *
     * @param x
     * @param y
     * @param options carries optional attributes values
     * @return a new instance of ApproximateEqual
     * @see org.tensorflow.op.MathOps.approximateEqual
     * @param tolerance @param tolerance
     */
    public fun <T : TType> approximateEqual(
        x: Operand<T>,
        y: Operand<T>,
        tolerance: Float? = null
    ): ApproximateEqual = java.approximateEqual<T>(
        x,
        y,
        *listOfNotNull(
            tolerance?.let { org.tensorflow.op.math.ApproximateEqual.tolerance(it) }
        ).toTypedArray()
    )

    /**
     * Returns the index with the largest value across dimensions of a tensor.
     *
     *  Note that in case of ties the identity of the return value is not guaranteed.
     *
     *  Usage:
     *    ```
     *    import tensorflow as tf
     *    a = [1, 10, 26.9, 2.8, 166.32, 62.3]
     *    b = tf.math.argmax(input = a)
     *    c = tf.keras.backend.eval(b)
     *    # c = 4
     *    # here a[4] = 166.32 which is the largest element of a across axis 0
     *    ```
     *
     *
     * @param V data type for ` output()` output
     * @param input
     * @param dimension int32 or int64, must be in the range `&#91;-rank(input), rank(input))`.
     *  Describes which dimension of the input Tensor to reduce across. For vectors,
     *  use dimension = 0.
     * @return a new instance of ArgMax
     * @see org.tensorflow.op.MathOps.argMax
     */
    public fun <T : TType, U : TNumber> argMax(input: Operand<T>, dimension: Operand<U>):
        ArgMax<TInt64> = java.argMax<T, U>(
            input,
            dimension
        )

    /**
     * Returns the index with the largest value across dimensions of a tensor.
     *
     *  Note that in case of ties the identity of the return value is not guaranteed.
     *
     *  Usage:
     *    ```
     *    import tensorflow as tf
     *    a = [1, 10, 26.9, 2.8, 166.32, 62.3]
     *    b = tf.math.argmax(input = a)
     *    c = tf.keras.backend.eval(b)
     *    # c = 4
     *    # here a[4] = 166.32 which is the largest element of a across axis 0
     *    ```
     *
     *
     * @param V data type for ` output()` output
     * @param input
     * @param dimension int32 or int64, must be in the range `&#91;-rank(input), rank(input))`.
     *  Describes which dimension of the input Tensor to reduce across. For vectors,
     *  use dimension = 0.
     * @param outputType
     * @return a new instance of ArgMax
     * @see org.tensorflow.op.MathOps.argMax
     */
    public fun <V : TNumber, T : TType, U : TNumber> argMax(
        input: Operand<T>,
        dimension: Operand<U>,
        outputType: DataType<V>
    ): ArgMax<V> = java.argMax<V, T, U>(
        input,
        dimension,
        outputType
    )

    /**
     * Returns the index with the smallest value across dimensions of a tensor.
     *
     *  Note that in case of ties the identity of the return value is not guaranteed.
     *
     *  Usage:
     *    ```
     *    import tensorflow as tf
     *    a = [1, 10, 26.9, 2.8, 166.32, 62.3]
     *    b = tf.math.argmin(input = a)
     *    c = tf.keras.backend.eval(b)
     *    # c = 0
     *    # here a[0] = 1 which is the smallest element of a across axis 0
     *    ```
     *
     *
     * @param V data type for ` output()` output
     * @param input
     * @param dimension int32 or int64, must be in the range `&#91;-rank(input), rank(input))`.
     *  Describes which dimension of the input Tensor to reduce across. For vectors,
     *  use dimension = 0.
     * @return a new instance of ArgMin
     * @see org.tensorflow.op.MathOps.argMin
     */
    public fun <T : TType, U : TNumber> argMin(input: Operand<T>, dimension: Operand<U>):
        ArgMin<TInt64> = java.argMin<T, U>(
            input,
            dimension
        )

    /**
     * Returns the index with the smallest value across dimensions of a tensor.
     *
     *  Note that in case of ties the identity of the return value is not guaranteed.
     *
     *  Usage:
     *    ```
     *    import tensorflow as tf
     *    a = [1, 10, 26.9, 2.8, 166.32, 62.3]
     *    b = tf.math.argmin(input = a)
     *    c = tf.keras.backend.eval(b)
     *    # c = 0
     *    # here a[0] = 1 which is the smallest element of a across axis 0
     *    ```
     *
     *
     * @param V data type for ` output()` output
     * @param input
     * @param dimension int32 or int64, must be in the range `&#91;-rank(input), rank(input))`.
     *  Describes which dimension of the input Tensor to reduce across. For vectors,
     *  use dimension = 0.
     * @param outputType
     * @return a new instance of ArgMin
     * @see org.tensorflow.op.MathOps.argMin
     */
    public fun <V : TNumber, T : TType, U : TNumber> argMin(
        input: Operand<T>,
        dimension: Operand<U>,
        outputType: DataType<V>
    ): ArgMin<V> = java.argMin<V, T, U>(
        input,
        dimension,
        outputType
    )

    /**
     * Computes the trignometric inverse sine of x element-wise.
     *
     *  The `tf.math.asin` operation returns the inverse of `tf.math.sin`, such that
     *  if `y = tf.math.sin(x)` then, `x = tf.math.asin(y)`.
     *
     *  <b>Note</b>: The output of `tf.math.asin` will lie within the invertible range
     *  of sine, i.e &#91;-pi/2, pi/2].
     *
     *  For example:
     *  ```
     *  # Note: [1.047, 0.785] ~= [(pi/3), (pi/4)]
     *  x = tf.constant([1.047, 0.785])
     *  y = tf.math.sin(x) # [0.8659266, 0.7068252]
     *
     *  tf.math.asin(y) # [1.047, 0.785] = x
     *  ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Asin
     * @see org.tensorflow.op.MathOps.asin
     */
    public fun <T : TType> asin(x: Operand<T>): Asin<T> = java.asin<T>(
        x
    )

    /**
     * Computes inverse hyperbolic sine of x element-wise.
     *
     *    Given an input tensor, this function computes inverse hyperbolic sine
     *    for every element in the tensor. Both input and output has a range of
     *    `&#91;-inf, inf]`.
     *
     *    ```
     *    x = tf.constant([-float("inf"), -2, -0.5, 1, 1.2, 200, 10000, float("inf")])
     *    tf.math.asinh(x) ==> [-inf -1.4436355 -0.4812118 0.8813736 1.0159732 5.991471 9.903487
     * inf]
     *    ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Asinh
     * @see org.tensorflow.op.MathOps.asinh
     */
    public fun <T : TType> asinh(x: Operand<T>): Asinh<T> = java.asinh<T>(
        x
    )

    /**
     * Computes the trignometric inverse tangent of x element-wise.
     *
     *  The `tf.math.atan` operation returns the inverse of `tf.math.tan`, such that
     *  if `y = tf.math.tan(x)` then, `x = tf.math.atan(y)`.
     *
     *  <b>Note</b>: The output of `tf.math.atan` will lie within the invertible range
     *  of tan, i.e (-pi/2, pi/2).
     *
     *  For example:
     *  ```
     *  # Note: [1.047, 0.785] ~= [(pi/3), (pi/4)]
     *  x = tf.constant([1.047, 0.785])
     *  y = tf.math.tan(x) # [1.731261, 0.99920404]
     *
     *  tf.math.atan(y) # [1.047, 0.785] = x
     *  ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Atan
     * @see org.tensorflow.op.MathOps.atan
     */
    public fun <T : TType> atan(x: Operand<T>): Atan<T> = java.atan<T>(
        x
    )

    /**
     * Computes arctangent of `y/x` element-wise, respecting signs of the arguments.
     *
     *  This is the angle \( \theta \in &#91;-\pi, \pi] \) such that
     *  \&#91; x = r \cos(\theta) \]
     *  and
     *  \&#91; y = r \sin(\theta) \]
     *  where \(r = \sqrt(x^2 + y^2) \).
     *
     * @param T data type for ` z()` output
     * @param y
     * @param x
     * @return a new instance of Atan2
     * @see org.tensorflow.op.MathOps.atan2
     */
    public fun <T : TNumber> atan2(y: Operand<T>, x: Operand<T>): Atan2<T> = java.atan2<T>(
        y,
        x
    )

    /**
     * Computes inverse hyperbolic tangent of x element-wise.
     *
     *    Given an input tensor, this function computes inverse hyperbolic tangent
     *    for every element in the tensor. Input range is `&#91;-1,1]` and output range is
     *    `&#91;-inf, inf]`. If input is `-1`, output will be `-inf` and if the
     *    input is `1`, output will be `inf`. Values outside the range will have
     *    `nan` as output.
     *
     *    ```
     *    x = tf.constant([-float("inf"), -1, -0.5, 1, 0, 0.5, 10, float("inf")])
     *    tf.math.atanh(x) ==> [nan -inf -0.54930615 inf  0. 0.54930615 nan nan]
     *    ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Atanh
     * @see org.tensorflow.op.MathOps.atanh
     */
    public fun <T : TType> atanh(x: Operand<T>): Atanh<T> = java.atanh<T>(
        x
    )

    /**
     * Compute the regularized incomplete beta integral \\(I_x(a, b)\\).
     *
     *  The regularized incomplete beta integral is defined as:
     *
     *  \\(I_x(a, b) = \frac{B(x; a, b)}{B(a, b)}\\)
     *
     *  where
     *
     *  \\(B(x; a, b) = \int_0^x t^{a-1} (1 - t)^{b-1} dt\\)
     *
     *  is the incomplete beta function and \\(B(a, b)\\) is the <i>complete</i>
     *  beta function.
     *
     * @param T data type for ` z()` output
     * @param a
     * @param b
     * @param x
     * @return a new instance of Betainc
     * @see org.tensorflow.op.MathOps.betainc
     */
    public fun <T : TNumber> betainc(
        a: Operand<T>,
        b: Operand<T>,
        x: Operand<T>
    ): Betainc<T> = java.betainc<T>(
        a,
        b,
        x
    )

    /**
     * Counts the number of occurrences of each value in an integer array.
     *
     *  Outputs a vector with length `size` and the same dtype as `weights`. If
     *  `weights` are empty, then index `i` stores the number of times the value `i` is
     *  counted in `arr`. If `weights` are non-empty, then index `i` stores the sum of
     *  the value in `weights` at each index where the corresponding value in `arr` is
     *  `i`.
     *
     *  Values in `arr` outside of the range &#91;0, size) are ignored.
     *
     * @param T data type for ` bins()` output
     * @param arr int32 `Tensor`.
     * @param size non-negative int32 scalar `Tensor`.
     * @param weights is an int32, int64, float32, or float64 `Tensor` with the same
     *  shape as `arr`, or a length-0 `Tensor`, in which case it acts as all weights
     *  equal to 1.
     * @return a new instance of Bincount
     * @see org.tensorflow.op.MathOps.bincount
     */
    public fun <T : TNumber> bincount(
        arr: Operand<TInt32>,
        size: Operand<TInt32>,
        weights: Operand<T>
    ): Bincount<T> = java.bincount<T>(
        arr,
        size,
        weights
    )

    /**
     * Returns element-wise smallest integer not less than x.
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Ceil
     * @see org.tensorflow.op.MathOps.ceil
     */
    public fun <T : TNumber> ceil(x: Operand<T>): Ceil<T> = java.ceil<T>(
        x
    )

    /**
     * Compare values of `input` to `threshold` and pack resulting bits into a `uint8`.
     *
     *  Each comparison returns a boolean `true` (if `input_value > threshold`)
     *  or and `false` otherwise.
     *
     *  This operation is useful for Locality-Sensitive-Hashing (LSH) and other
     *  algorithms that use hashing approximations of cosine and `L2` distances;
     *  codes can be generated from an input via:
     *  ```
     *  codebook_size = 50
     *  codebook_bits = codebook_size * 32
     *  codebook = tf.get_variable('codebook', [x.shape[-1].value, codebook_bits],
     *                             dtype=x.dtype,
     *                             initializer=tf.orthogonal_initializer())
     *  codes = compare_and_threshold(tf.matmul(x, codebook), threshold=0.)
     *  codes = tf.bitcast(codes, tf.int32)  # go from uint8 to int32
     *  # now codes has shape x.shape[:-1] + [codebook_size]
     *  ```
     *
     *  <b>NOTE</b>: Currently, the innermost dimension of the tensor must be divisible
     *  by 8.
     *
     *  Given an `input` shaped `&#91;s0, s1, ..., s_n]`, the output is
     *  a `uint8` tensor shaped `&#91;s0, s1, ..., s_n / 8]`.
     *
     * @param input Values to compare against `threshold` and bitpack.
     * @param threshold Threshold to compare against.
     * @return a new instance of CompareAndBitpack
     * @see org.tensorflow.op.MathOps.compareAndBitpack
     */
    public fun <T : TType> compareAndBitpack(input: Operand<T>, threshold: Operand<T>):
        CompareAndBitpack = java.compareAndBitpack<T>(
            input,
            threshold
        )

    /**
     * Computes the complex absolute value of a tensor.
     *
     *  Given a tensor `x` of complex numbers, this operation returns a tensor of type
     *  `float` or `double` that is the absolute value of each element in `x`. All
     *  elements in `x` must be complex numbers of the form \\(a + bj\\). The absolute
     *  value is computed as \\( \sqrt{a^2 + b^2}\\).
     *
     * @param U data type for ` y()` output
     * @param x
     * @return a new instance of ComplexAbs
     * @see org.tensorflow.op.MathOps.complexAbs
     */
    public fun <T : TType> complexAbs(x: Operand<T>): ComplexAbs<TFloat32> = java.complexAbs<T>(
        x
    )

    /**
     * Computes the complex absolute value of a tensor.
     *
     *  Given a tensor `x` of complex numbers, this operation returns a tensor of type
     *  `float` or `double` that is the absolute value of each element in `x`. All
     *  elements in `x` must be complex numbers of the form \\(a + bj\\). The absolute
     *  value is computed as \\( \sqrt{a^2 + b^2}\\).
     *
     * @param U data type for ` y()` output
     * @param x
     * @param Tout
     * @return a new instance of ComplexAbs
     * @see org.tensorflow.op.MathOps.complexAbs
     */
    public fun <U : TNumber, T : TType> complexAbs(x: Operand<T>, Tout: DataType<U>): ComplexAbs<U> =
        java.complexAbs<U, T>(
            x,
            Tout
        )

    /**
     * Returns the complex conjugate of a complex number.
     *
     *  Given a tensor `input` of complex numbers, this operation returns a tensor of
     *  complex numbers that are the complex conjugate of each element in `input`. The
     *  complex numbers in `input` must be of the form \\(a + bj\\), where <i>a</i> is the
     *  real part and <i>b</i> is the imaginary part.
     *
     *  The complex conjugate returned by this operation is of the form \\(a - bj\\).
     *
     *  For example:
     *  ```
     *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
     *  tf.conj(input) ==> [-2.25 - 4.75j, 3.25 - 5.75j]
     *  ```
     *
     *
     * @param T data type for ` output()` output
     * @param input
     * @return a new instance of Conj
     * @see org.tensorflow.op.MathOps.conj
     */
    public fun <T : TType> conj(input: Operand<T>): Conj<T> = java.conj<T>(
        input
    )

    /**
     * Computes cos of x element-wise.
     *
     *    Given an input tensor, this function computes cosine of every
     *    element in the tensor. Input range is `(-inf, inf)` and
     *    output range is `&#91;-1,1]`. If input lies outside the boundary, `nan`
     *    is returned.
     *
     *    ```
     *    x = tf.constant([-float("inf"), -9, -0.5, 1, 1.2, 200, 10000, float("inf")])
     *    tf.math.cos(x) ==> [nan -0.91113025 0.87758255 0.5403023 0.36235774 0.48718765 -0.95215535
     * nan]
     *    ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Cos
     * @see org.tensorflow.op.MathOps.cos
     */
    public fun <T : TType> cos(x: Operand<T>): Cos<T> = java.cos<T>(
        x
    )

    /**
     * Computes hyperbolic cosine of x element-wise.
     *
     *    Given an input tensor, this function computes hyperbolic cosine of every
     *    element in the tensor. Input range is `&#91;-inf, inf]` and output range
     *    is `&#91;1, inf]`.
     *
     *    ```
     *    x = tf.constant([-float("inf"), -9, -0.5, 1, 1.2, 2, 10, float("inf")])
     *    tf.math.cosh(x) ==> [inf 4.0515420e+03 1.1276259e+00 1.5430807e+00 1.8106556e+00
     * 3.7621956e+00 1.1013233e+04 inf]
     *    ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Cosh
     * @see org.tensorflow.op.MathOps.cosh
     */
    public fun <T : TType> cosh(x: Operand<T>): Cosh<T> = java.cosh<T>(
        x
    )

    /**
     * Compute the cumulative product of the tensor `x` along `axis`.
     *
     *  By default, this op performs an inclusive cumprod, which means that the first
     *  element of the input is identical to the first element of the output:
     *  ```
     *  tf.cumprod([a, b, c])  # => [a, a * b, a * b * c]
     *  ```
     *
     *  By setting the `exclusive` kwarg to `True`, an exclusive cumprod is
     *  performed instead:
     *  ```
     *  tf.cumprod([a, b, c], exclusive=True)  # => [1, a, a * b]
     *  ```
     *
     *  By setting the `reverse` kwarg to `True`, the cumprod is performed in the
     *  opposite direction:
     *  ```
     *  tf.cumprod([a, b, c], reverse=True)  # => [a * b * c, b * c, c]
     *  ```
     *
     *  This is more efficient than using separate `tf.reverse` ops.
     *
     *  The `reverse` and `exclusive` kwargs can also be combined:
     *  ```
     *  tf.cumprod([a, b, c], exclusive=True, reverse=True)  # => [b * c, c, 1]
     *  ```
     *
     *
     * @param T data type for ` out()` output
     * @param x A `Tensor`. Must be one of the following types: `float32`, `float64`,
     *  `int64`, `int32`, `uint8`, `uint16`, `int16`, `int8`, `complex64`,
     *  `complex128`, `qint8`, `quint8`, `qint32`, `half`.
     * @param axis A `Tensor` of type `int32` (default: 0). Must be in the range
     *  `&#91;-rank(x), rank(x))`.
     * @param options carries optional attributes values
     * @return a new instance of Cumprod
     * @see org.tensorflow.op.MathOps.cumprod
     * @param exclusive If `True`, perform exclusive cumprod.
     * @param reverse A `bool` (default: False).
     */
    public fun <T : TType, U : TNumber> cumprod(
        x: Operand<T>,
        axis: Operand<U>,
        exclusive: Boolean? = null,
        reverse: Boolean? = null
    ): Cumprod<T> = java.cumprod<T, U>(
        x,
        axis,
        *listOfNotNull(
            exclusive?.let { org.tensorflow.op.math.Cumprod.exclusive(it) },
            reverse?.let { org.tensorflow.op.math.Cumprod.reverse(it) }
        ).toTypedArray()
    )

    /**
     * Compute the cumulative sum of the tensor `x` along `axis`.
     *
     *  By default, this op performs an inclusive cumsum, which means that the first
     *  element of the input is identical to the first element of the output:
     *  ```
     *  tf.cumsum([a, b, c])  # => [a, a + b, a + b + c]
     *  ```
     *
     *  By setting the `exclusive` kwarg to `True`, an exclusive cumsum is
     *  performed instead:
     *  ```
     *  tf.cumsum([a, b, c], exclusive=True)  # => [0, a, a + b]
     *  ```
     *
     *  By setting the `reverse` kwarg to `True`, the cumsum is performed in the
     *  opposite direction:
     *  ```
     *  tf.cumsum([a, b, c], reverse=True)  # => [a + b + c, b + c, c]
     *  ```
     *
     *  This is more efficient than using separate `tf.reverse` ops.
     *
     *  The `reverse` and `exclusive` kwargs can also be combined:
     *  ```
     *  tf.cumsum([a, b, c], exclusive=True, reverse=True)  # => [b + c, c, 0]
     *  ```
     *
     *
     * @param T data type for ` out()` output
     * @param x A `Tensor`. Must be one of the following types: `float32`, `float64`,
     *  `int64`, `int32`, `uint8`, `uint16`, `int16`, `int8`, `complex64`,
     *  `complex128`, `qint8`, `quint8`, `qint32`, `half`.
     * @param axis A `Tensor` of type `int32` (default: 0). Must be in the range
     *  `&#91;-rank(x), rank(x))`.
     * @param options carries optional attributes values
     * @return a new instance of Cumsum
     * @see org.tensorflow.op.MathOps.cumsum
     * @param exclusive If `True`, perform exclusive cumsum.
     * @param reverse A `bool` (default: False).
     */
    public fun <T : TType, U : TNumber> cumsum(
        x: Operand<T>,
        axis: Operand<U>,
        exclusive: Boolean? = null,
        reverse: Boolean? = null
    ): Cumsum<T> = java.cumsum<T, U>(
        x,
        axis,
        *listOfNotNull(
            exclusive?.let { org.tensorflow.op.math.Cumsum.exclusive(it) },
            reverse?.let { org.tensorflow.op.math.Cumsum.reverse(it) }
        ).toTypedArray()
    )

    /**
     * Counts the number of occurrences of each value in an integer array.
     *
     *  Outputs a vector with length `size` and the same dtype as `weights`. If
     *  `weights` are empty, then index `i` stores the number of times the value `i` is
     *  counted in `arr`. If `weights` are non-empty, then index `i` stores the sum of
     *  the value in `weights` at each index where the corresponding value in `arr` is
     *  `i`.
     *
     *  Values in `arr` outside of the range &#91;0, size) are ignored.
     *
     * @param U data type for ` output()` output
     * @param input 1D or 2D int `Tensor`.
     * @param size non-negative int scalar `Tensor`.
     * @param weights is an int32, int64, float32, or float64 `Tensor` with the same
     *  shape as `arr`, or a length-0 `Tensor`, in which case it acts as all weights
     *  equal to 1.
     * @param options carries optional attributes values
     * @return a new instance of DenseBincount
     * @see org.tensorflow.op.MathOps.denseBincount
     * @param binaryOutput bool; Whether the kernel should count the appearance or number of
     * occurrences.
     */
    public fun <U : TNumber, T : TNumber> denseBincount(
        input: Operand<T>,
        size: Operand<T>,
        weights: Operand<U>,
        binaryOutput: Boolean? = null
    ): DenseBincount<U> = java.denseBincount<U, T>(
        input,
        size,
        weights,
        *listOfNotNull(
            binaryOutput?.let { org.tensorflow.op.math.DenseBincount.binaryOutput(it) }
        ).toTypedArray()
    )

    /**
     * Computes Psi, the derivative of Lgamma (the log of the absolute value of
     *
     *  `Gamma(x)`), element-wise.
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Digamma
     * @see org.tensorflow.op.MathOps.digamma
     */
    public fun <T : TNumber> digamma(x: Operand<T>): Digamma<T> = java.digamma<T>(
        x
    )

    /**
     * Returns x / y element-wise.
     *
     *  <i>NOTE</i>: `math.Div` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of Div
     * @see org.tensorflow.op.MathOps.div
     */
    public fun <T : TType> div(x: Operand<T>, y: Operand<T>): Div<T> = java.div<T>(
        x,
        y
    )

    /**
     * Returns 0 if the denominator is zero.
     *
     *
     *  <i>NOTE</i>: `math.DivNoNan` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of DivNoNan
     * @see org.tensorflow.op.MathOps.divNoNan
     */
    public fun <T : TType> divNoNan(x: Operand<T>, y: Operand<T>): DivNoNan<T> = java.divNoNan<T>(
        x,
        y
    )

    /**
     * Returns the truth value of (x == y) element-wise.
     *
     *  <i>NOTE</i>: `math.Equal` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *  ```
     *  x = tf.constant([2, 4])
     *  y = tf.constant(2)
     *  tf.math.equal(x, y) ==> array([True, False])
     *
     *  x = tf.constant([2, 4])
     *  y = tf.constant([2, 4])
     *  tf.math.equal(x, y) ==> array([True,  True])
     *  ```
     *
     *
     * @param x
     * @param y
     * @param options carries optional attributes values
     * @return a new instance of Equal
     * @see org.tensorflow.op.MathOps.equal
     * @param incompatibleShapeError @param incompatibleShapeError
     */
    public fun <T : TType> equal(
        x: Operand<T>,
        y: Operand<T>,
        incompatibleShapeError: Boolean? = null
    ): Equal = java.equal<T>(
        x,
        y,
        *listOfNotNull(
            incompatibleShapeError?.let { org.tensorflow.op.math.Equal.incompatibleShapeError(it) }
        ).toTypedArray()
    )

    /**
     * Computes the Gauss error function of `x` element-wise.
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Erf
     * @see org.tensorflow.op.MathOps.erf
     */
    public fun <T : TNumber> erf(x: Operand<T>): Erf<T> = java.erf<T>(
        x
    )

    /**
     * Computes the complementary error function of `x` element-wise.
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Erfc
     * @see org.tensorflow.op.MathOps.erfc
     */
    public fun <T : TNumber> erfc(x: Operand<T>): Erfc<T> = java.erfc<T>(
        x
    )

    /**
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of erfinv
     * @see org.tensorflow.op.MathOps.erfinv
     */
    public fun <T : TNumber> erfinv(x: Operand<T>): erfinv<T> = java.erfinv<T>(
        x
    )

    /**
     * Computes exponential of x element-wise.  \\(y = e^x\\).
     *
     *    This function computes the exponential of every element in the input tensor.
     *    i.e. `exp(x)` or `e^(x)`, where `x` is the input tensor.
     *    `e` denotes Euler's number and is approximately equal to 2.718281.
     *    Output is positive for any real input.
     *
     *    ```
     *    x = tf.constant(2.0)
     *    tf.math.exp(x) ==> 7.389056
     *
     *    x = tf.constant([2.0, 8.0])
     *    tf.math.exp(x) ==> array([7.389056, 2980.958], dtype=float32)
     *    ```
     *
     *  For complex numbers, the exponential value is calculated as follows:
     *
     *    ```
     *    e^(x+iy) = e^x * e^iy = e^x * (cos y + i sin y)
     *    ```
     *
     *  Let's consider complex number 1+1j as an example.
     *    e^1 * (cos 1 + i sin 1) = 2.7182818284590 * (0.54030230586+0.8414709848j)
     *
     *    ```
     *    x = tf.constant(1 + 1j)
     *    tf.math.exp(x) ==> 1.4686939399158851+2.2873552871788423j
     *    ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Exp
     * @see org.tensorflow.op.MathOps.exp
     */
    public fun <T : TType> exp(x: Operand<T>): Exp<T> = java.exp<T>(
        x
    )

    /**
     * Computes `exp(x) - 1` element-wise.
     *
     *    i.e. `exp(x) - 1` or `e^(x) - 1`, where `x` is the input tensor.
     *    `e` denotes Euler's number and is approximately equal to 2.718281.
     *
     *    ```
     *    x = tf.constant(2.0)
     *    tf.math.expm1(x) ==> 6.389056
     *
     *    x = tf.constant([2.0, 8.0])
     *    tf.math.expm1(x) ==> array([6.389056, 2979.958], dtype=float32)
     *
     *    x = tf.constant(1 + 1j)
     *    tf.math.expm1(x) ==> (0.46869393991588515+2.2873552871788423j)
     *    ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Expm1
     * @see org.tensorflow.op.MathOps.expm1
     */
    public fun <T : TType> expm1(x: Operand<T>): Expm1<T> = java.expm1<T>(
        x
    )

    /**
     * Output a fact about factorials.
     *
     * @return a new instance of Fact
     * @see org.tensorflow.op.MathOps.fact
     */
    public fun fact(): Fact = java.fact()

    /**
     * Returns element-wise largest integer not greater than x.
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Floor
     * @see org.tensorflow.op.MathOps.floor
     */
    public fun <T : TNumber> floor(x: Operand<T>): Floor<T> = java.floor<T>(
        x
    )

    /**
     * Returns x // y element-wise.
     *
     *  <i>NOTE</i>: `math.FloorDiv` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of FloorDiv
     * @see org.tensorflow.op.MathOps.floorDiv
     */
    public fun <T : TType> floorDiv(x: Operand<T>, y: Operand<T>): FloorDiv<T> = java.floorDiv<T>(
        x,
        y
    )

    /**
     * Returns element-wise remainder of division. When `x < 0` xor `y < 0` is
     *
     *  true, this follows Python semantics in that the result here is consistent
     *  with a flooring divide. E.g. `floor(x / y) * y + mod(x, y) = x`.
     *
     *  <i>NOTE</i>: `math.FloorMod` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of FloorMod
     * @see org.tensorflow.op.MathOps.floorMod
     */
    public fun <T : TNumber> floorMod(x: Operand<T>, y: Operand<T>): FloorMod<T> =
        java.floorMod<T>(
            x,
            y
        )

    /**
     * Returns the truth value of (x > y) element-wise.
     *
     *  <i>NOTE</i>: `math.Greater` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Example:
     *  ```
     *  x = tf.constant([5, 4, 6])
     *  y = tf.constant([5, 2, 5])
     *  tf.math.greater(x, y) ==> [False, True, True]
     *
     *  x = tf.constant([5, 4, 6])
     *  y = tf.constant([5])
     *  tf.math.greater(x, y) ==> [False, False, True]
     *  ```
     *
     *
     * @param x
     * @param y
     * @return a new instance of Greater
     * @see org.tensorflow.op.MathOps.greater
     */
    public fun <T : TNumber> greater(x: Operand<T>, y: Operand<T>): Greater = java.greater<T>(
        x,
        y
    )

    /**
     * Returns the truth value of (x >= y) element-wise.
     *
     *  <i>NOTE</i>: `math.GreaterEqual` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Example:
     *  ```
     *  x = tf.constant([5, 4, 6, 7])
     *  y = tf.constant([5, 2, 5, 10])
     *  tf.math.greater_equal(x, y) ==> [True, True, True, False]
     *
     *  x = tf.constant([5, 4, 6, 7])
     *  y = tf.constant([5])
     *  tf.math.greater_equal(x, y) ==> [True, False, True, True]
     *  ```
     *
     *
     * @param x
     * @param y
     * @return a new instance of GreaterEqual
     * @see org.tensorflow.op.MathOps.greaterEqual
     */
    public fun <T : TNumber> greaterEqual(x: Operand<T>, y: Operand<T>): GreaterEqual =
        java.greaterEqual<T>(
            x,
            y
        )

    /**
     * Compute the lower regularized incomplete Gamma function `P(a, x)`.
     *
     *  The lower regularized incomplete Gamma function is defined as:
     *
     *  \\(P(a, x) = gamma(a, x) / Gamma(a) = 1 - Q(a, x)\\)
     *
     *  where
     *
     *  \\(gamma(a, x) = \\int_{0}^{x} t^{a-1} exp(-t) dt\\)
     *
     *  is the lower incomplete Gamma function.
     *
     *  Note, above `Q(a, x)` (`Igammac`) is the upper regularized complete
     *  Gamma function.
     *
     * @param T data type for ` z()` output
     * @param a
     * @param x
     * @return a new instance of Igamma
     * @see org.tensorflow.op.MathOps.igamma
     */
    public fun <T : TNumber> igamma(a: Operand<T>, x: Operand<T>): Igamma<T> = java.igamma<T>(
        a,
        x
    )

    /**
     * Compute the upper regularized incomplete Gamma function `Q(a, x)`.
     *
     *  The upper regularized incomplete Gamma function is defined as:
     *
     *  \\(Q(a, x) = Gamma(a, x) / Gamma(a) = 1 - P(a, x)\\)
     *
     *  where
     *
     *  \\(Gamma(a, x) = int_{x}^{\infty} t^{a-1} exp(-t) dt\\)
     *
     *  is the upper incomplete Gama function.
     *
     *  Note, above `P(a, x)` (`Igamma`) is the lower regularized complete
     *  Gamma function.
     *
     * @param T data type for ` z()` output
     * @param a
     * @param x
     * @return a new instance of Igammac
     * @see org.tensorflow.op.MathOps.igammac
     */
    public fun <T : TNumber> igammac(a: Operand<T>, x: Operand<T>): Igammac<T> = java.igammac<T>(
        a,
        x
    )

    /**
     * Returns the imaginary part of a complex number.
     *
     *  Given a tensor `input` of complex numbers, this operation returns a tensor of
     *  type `float` that is the imaginary part of each element in `input`. All
     *  elements in `input` must be complex numbers of the form \\(a + bj\\), where <i>a</i>
     *  is the real part and <i>b</i> is the imaginary part returned by this operation.
     *
     *  For example:
     *  ```
     *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
     *  tf.imag(input) ==> [4.75, 5.75]
     *  ```
     *
     *
     * @param U data type for ` output()` output
     * @param input
     * @return a new instance of Imag
     * @see org.tensorflow.op.MathOps.imag
     */
    public fun <T : TType> imag(input: Operand<T>): Imag<TFloat32> = java.imag<T>(
        input
    )

    /**
     * Returns the imaginary part of a complex number.
     *
     *  Given a tensor `input` of complex numbers, this operation returns a tensor of
     *  type `float` that is the imaginary part of each element in `input`. All
     *  elements in `input` must be complex numbers of the form \\(a + bj\\), where <i>a</i>
     *  is the real part and <i>b</i> is the imaginary part returned by this operation.
     *
     *  For example:
     *  ```
     *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
     *  tf.imag(input) ==> [4.75, 5.75]
     *  ```
     *
     *
     * @param U data type for ` output()` output
     * @param input
     * @param Tout
     * @return a new instance of Imag
     * @see org.tensorflow.op.MathOps.imag
     */
    public fun <U : TNumber, T : TType> imag(input: Operand<T>, Tout: DataType<U>): Imag<U> =
        java.imag<U, T>(
            input,
            Tout
        )

    /**
     * Computes the inverse permutation of a tensor.
     *
     *  This operation computes the inverse of an index permutation. It takes a 1-D
     *  integer tensor `x`, which represents the indices of a zero-based array, and
     *  swaps each value with its index position. In other words, for an output tensor
     *  `y` and an input tensor `x`, this operation computes the following:
     *
     *  `y&#91;x&#91;i]] = i for i in &#91;0, 1, ..., len(x) - 1]`
     *
     *  The values must include 0. There can be no duplicate values or negative values.
     *
     *  For example:
     *  ```
     *  # tensor `x` is [3, 4, 0, 2, 1]
     *  invert_permutation(x) ==> [2, 4, 3, 0, 1]
     *  ```
     *
     *
     * @param T data type for ` y()` output
     * @param x 1-D.
     * @return a new instance of InvertPermutation
     * @see org.tensorflow.op.MathOps.invertPermutation
     */
    public fun <T : TNumber> invertPermutation(x: Operand<T>): InvertPermutation<T> =
        java.invertPermutation<T>(
            x
        )

    /**
     * Returns which elements of x are finite.
     *
     *
     * @compatibility(numpy) Equivalent to np.isfinite
     * @end_compatibility
     *  Example:
     *  ```
     *  x = tf.constant([5.0, 4.8, 6.8, np.inf, np.nan])
     *  tf.math.is_finite(x) ==> [True, True, True, False, False]
     *  ```
     *
     * @param x
     * @return a new instance of IsFinite
     * @see org.tensorflow.op.MathOps.isFinite
     */
    public fun <T : TNumber> isFinite(x: Operand<T>): IsFinite = java.isFinite<T>(
        x
    )

    /**
     * Returns which elements of x are Inf.
     *
     *
     * @compatibility(numpy) Equivalent to np.isinf
     * @end_compatibility
     *  Example:
     *  ```
     *  x = tf.constant([5.0, np.inf, 6.8, np.inf])
     *  tf.math.is_inf(x) ==> [False, True, False, True]
     *  ```
     *
     * @param x
     * @return a new instance of IsInf
     * @see org.tensorflow.op.MathOps.isInf
     */
    public fun <T : TNumber> isInf(x: Operand<T>): IsInf = java.isInf<T>(
        x
    )

    /**
     * Returns which elements of x are NaN.
     *
     *
     * @compatibility(numpy) Equivalent to np.isnan
     * @end_compatibility
     *  Example:
     *  ```
     *  x = tf.constant([5.0, np.nan, 6.8, np.nan, np.inf])
     *  tf.math.is_nan(x) ==> [False, True, False, True, False]
     *  ```
     *
     * @param x
     * @return a new instance of IsNan
     * @see org.tensorflow.op.MathOps.isNan
     */
    public fun <T : TNumber> isNan(x: Operand<T>): IsNan = java.isNan<T>(
        x
    )

    /**
     * Returns the truth value of (x < y) element-wise.
     *
     *  <i>NOTE</i>: `math.Less` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Example:
     *  ```
     *  x = tf.constant([5, 4, 6])
     *  y = tf.constant([5])
     *  tf.math.less(x, y) ==> [False, True, False]
     *
     *  x = tf.constant([5, 4, 6])
     *  y = tf.constant([5, 6, 7])
     *  tf.math.less(x, y) ==> [False, True, True]
     *  ```
     *
     *
     * @param x
     * @param y
     * @return a new instance of Less
     * @see org.tensorflow.op.MathOps.less
     */
    public fun <T : TNumber> less(x: Operand<T>, y: Operand<T>): Less = java.less<T>(
        x,
        y
    )

    /**
     * Returns the truth value of (x <= y) element-wise.
     *
     *  <i>NOTE</i>: `math.LessEqual` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Example:
     *  ```
     *  x = tf.constant([5, 4, 6])
     *  y = tf.constant([5])
     *  tf.math.less_equal(x, y) ==> [True, True, False]
     *
     *  x = tf.constant([5, 4, 6])
     *  y = tf.constant([5, 6, 6])
     *  tf.math.less_equal(x, y) ==> [True, True, True]
     *  ```
     *
     *
     * @param x
     * @param y
     * @return a new instance of LessEqual
     * @see org.tensorflow.op.MathOps.lessEqual
     */
    public fun <T : TNumber> lessEqual(x: Operand<T>, y: Operand<T>): LessEqual =
        java.lessEqual<T>(
            x,
            y
        )

    /**
     * Computes the log of the absolute value of `Gamma(x)` element-wise.
     *
     *    For positive numbers, this function computes log((input - 1)!) for every element in the
     * tensor.
     *    `lgamma(5) = log((5-1)!) = log(4!) = log(24) = 3.1780539`
     *
     *  Example:
     *  ```
     *  x = tf.constant([0, 0.5, 1, 4.5, -4, -5.6])
     *  tf.math.lgamma(x) ==> [inf, 0.5723649, 0., 2.4537368, inf, -4.6477685]
     *  ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Lgamma
     * @see org.tensorflow.op.MathOps.lgamma
     */
    public fun <T : TNumber> lgamma(x: Operand<T>): Lgamma<T> = java.lgamma<T>(
        x
    )

    /**
     * Computes natural logarithm of x element-wise.
     *
     *  I.e., \\(y = \log_e x\\).
     *
     *  Example:
     *  ```
     *  x = tf.constant([0, 0.5, 1, 5])
     *  tf.math.log(x) ==> [-inf, -0.6931472,  0. ,  1.609438]
     *  ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Log
     * @see org.tensorflow.op.MathOps.log
     */
    public fun <T : TType> log(x: Operand<T>): Log<T> = java.log<T>(
        x
    )

    /**
     * Computes natural logarithm of (1 + x) element-wise.
     *
     *  I.e., \\(y = \log_e (1 + x)\\).
     *
     *  Example:
     *  ```
     *  x = tf.constant([0, 0.5, 1, 5])
     *  tf.math.log1p(x) ==> [0., 0.4054651, 0.6931472, 1.7917595]
     *  ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Log1p
     * @see org.tensorflow.op.MathOps.log1p
     */
    public fun <T : TType> log1p(x: Operand<T>): Log1p<T> = java.log1p<T>(
        x
    )

    /**
     * Returns the truth value of x AND y element-wise.
     *
     *  <i>NOTE</i>: `math.LogicalAnd` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param x
     * @param y
     * @return a new instance of LogicalAnd
     * @see org.tensorflow.op.MathOps.logicalAnd
     */
    public fun logicalAnd(x: Operand<TBool>, y: Operand<TBool>): LogicalAnd = java.logicalAnd(
        x,
        y
    )

    /**
     * Returns the truth value of `NOT x` element-wise.
     *
     * @param x A `Tensor` of type `bool`.
     * @return a new instance of LogicalNot
     * @see org.tensorflow.op.MathOps.logicalNot
     */
    public fun logicalNot(x: Operand<TBool>): LogicalNot = java.logicalNot(
        x
    )

    /**
     * Returns the truth value of x OR y element-wise.
     *
     *  <i>NOTE</i>: `math.LogicalOr` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param x
     * @param y
     * @return a new instance of LogicalOr
     * @see org.tensorflow.op.MathOps.logicalOr
     */
    public fun logicalOr(x: Operand<TBool>, y: Operand<TBool>): LogicalOr = java.logicalOr(
        x,
        y
    )

    /**
     * Returns the max of x and y (i.e. x > y ? x : y) element-wise.
     *
     *  <i>NOTE</i>: `math.Maximum` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of Maximum
     * @see org.tensorflow.op.MathOps.maximum
     */
    public fun <T : TNumber> maximum(x: Operand<T>, y: Operand<T>): Maximum<T> = java.maximum<T>(
        x,
        y
    )

    /**
     * Computes the mean of elements across dimensions of a tensor.
     *
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param T data type for ` output()` output
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `&#91;-rank(input), rank(input))`.
     * @param options carries optional attributes values
     * @return a new instance of Mean
     * @see org.tensorflow.op.MathOps.mean
     * @param keepDims If true, retain reduced dimensions with length 1.
     */
    public fun <T : TType, U : TNumber> mean(
        input: Operand<T>,
        axis: Operand<U>,
        keepDims: Boolean? = null
    ): Mean<T> = java.mean<T, U>(
        input,
        axis,
        *listOfNotNull(
            keepDims?.let { org.tensorflow.op.math.Mean.keepDims(it) }
        ).toTypedArray()
    )

    /**
     * Returns the min of x and y (i.e. x < y ? x : y) element-wise.
     *
     *  <i>NOTE</i>: `math.Minimum` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of Minimum
     * @see org.tensorflow.op.MathOps.minimum
     */
    public fun <T : TNumber> minimum(x: Operand<T>, y: Operand<T>): Minimum<T> = java.minimum<T>(
        x,
        y
    )

    /**
     * Returns element-wise remainder of division. This emulates C semantics in that
     *
     *  the result here is consistent with a truncating divide. E.g.
     *  `tf.truncatediv(x, y) * y + truncate_mod(x, y) = x`.
     *
     *  <i>NOTE</i>: `math.Mod` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of Mod
     * @see org.tensorflow.op.MathOps.mod
     */
    public fun <T : TNumber> mod(x: Operand<T>, y: Operand<T>): Mod<T> = java.mod<T>(
        x,
        y
    )

    /**
     * Returns x * y element-wise.
     *
     *  <i>NOTE</i>: `math.Mul` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of Mul
     * @see org.tensorflow.op.MathOps.mul
     */
    public fun <T : TType> mul(x: Operand<T>, y: Operand<T>): Mul<T> = java.mul<T>(
        x,
        y
    )

    /**
     * Returns x * y element-wise. Returns zero if y is zero, even if x if infinite or NaN.
     *
     *  <i>NOTE</i>: `math.MulNoNan` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of MulNoNan
     * @see org.tensorflow.op.MathOps.mulNoNan
     */
    public fun <T : TType> mulNoNan(x: Operand<T>, y: Operand<T>): MulNoNan<T> = java.mulNoNan<T>(
        x,
        y
    )

    /**
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Ndtri
     * @see org.tensorflow.op.MathOps.ndtri
     */
    public fun <T : TNumber> ndtri(x: Operand<T>): Ndtri<T> = java.ndtri<T>(
        x
    )

    /**
     * Computes numerical negative value element-wise.
     *
     *  I.e., \\(y = -x\\).
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Neg
     * @see org.tensorflow.op.MathOps.neg
     */
    public fun <T : TType> neg(x: Operand<T>): Neg<T> = java.neg<T>(
        x
    )

    /**
     * Returns the next representable value of `x1` in the direction of `x2`, element-wise.
     *
     *  This operation returns the same result as the C++ std::nextafter function.
     *
     *  It can also return a subnormal number.
     *
     *
     * @compatibility(cpp) Equivalent to C++ std::nextafter function.
     * @end_compatibility
     * @param T data type for ` output()` output
     * @param x1
     * @param x2
     * @return a new instance of NextAfter
     * @see org.tensorflow.op.MathOps.nextAfter
     */
    public fun <T : TNumber> nextAfter(x1: Operand<T>, x2: Operand<T>): NextAfter<T> =
        java.nextAfter<T>(
            x1,
            x2
        )

    /**
     * Returns the truth value of (x != y) element-wise.
     *
     *  <i>NOTE</i>: `math.NotEqual` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param x
     * @param y
     * @param options carries optional attributes values
     * @return a new instance of NotEqual
     * @see org.tensorflow.op.MathOps.notEqual
     * @param incompatibleShapeError @param incompatibleShapeError
     */
    public fun <T : TType> notEqual(
        x: Operand<T>,
        y: Operand<T>,
        incompatibleShapeError: Boolean? = null
    ): NotEqual = java.notEqual<T>(
        x,
        y,
        *listOfNotNull(
            incompatibleShapeError?.let { org.tensorflow.op.math.NotEqual.incompatibleShapeError(it) }
        ).toTypedArray()
    )

    /**
     * Compute the polygamma function \\(\psi^{(n)}(x)\\).
     *
     *  The polygamma function is defined as:
     *
     *  \\(\psi^{(a)}(x) = \frac{d^a}{dx^a} \psi(x)\\)
     *
     *  where \\(\psi(x)\\) is the digamma function.
     *  The polygamma function is defined only for non-negative integer orders \\a\\.
     *
     * @param T data type for ` z()` output
     * @param a
     * @param x
     * @return a new instance of Polygamma
     * @see org.tensorflow.op.MathOps.polygamma
     */
    public fun <T : TNumber> polygamma(a: Operand<T>, x: Operand<T>): Polygamma<T> =
        java.polygamma<T>(
            a,
            x
        )

    /**
     * Computes element-wise population count (a.k.a. popcount, bitsum, bitcount).
     *
     *  For each entry in `x`, calculates the number of `1` (on) bits in the binary
     *  representation of that entry.
     *
     *  <b>NOTE</b>: It is more efficient to first `tf.bitcast` your tensors into
     *  `int32` or `int64` and perform the bitcount on the result, than to feed in
     *  8- or 16-bit inputs and then aggregate the resulting counts.
     *
     * @param x
     * @return a new instance of PopulationCount
     * @see org.tensorflow.op.MathOps.populationCount
     */
    public fun <T : TNumber> populationCount(x: Operand<T>): PopulationCount =
        java.populationCount<T>(
            x
        )

    /**
     * Computes the power of one value to another.
     *
     *  Given a tensor `x` and a tensor `y`, this operation computes \\(x^y\\) for
     *  corresponding elements in `x` and `y`. For example:
     *  ```
     *  # tensor 'x' is [[2, 2]], [3, 3]]
     *  # tensor 'y' is [[8, 16], [2, 3]]
     *  tf.pow(x, y) ==> [[256, 65536], [9, 27]]
     *  ```
     *
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of Pow
     * @see org.tensorflow.op.MathOps.pow
     */
    public fun <T : TType> pow(x: Operand<T>, y: Operand<T>): Pow<T> = java.pow<T>(
        x,
        y
    )

    /**
     * Returns x + y element-wise, working on quantized buffers.
     *
     * @param V data type for ` z()` output
     * @param x
     * @param y
     * @param minX The float value that the lowest quantized `x` value represents.
     * @param maxX The float value that the highest quantized `x` value represents.
     * @param minY The float value that the lowest quantized `y` value represents.
     * @param maxY The float value that the highest quantized `y` value represents.
     * @param Toutput
     * @return a new instance of QuantizedAdd
     * @see org.tensorflow.op.MathOps.quantizedAdd
     */
    public fun <V : TType, T : TType, U : TType> quantizedAdd(
        x: Operand<T>,
        y: Operand<U>,
        minX: Operand<TFloat32>,
        maxX: Operand<TFloat32>,
        minY: Operand<TFloat32>,
        maxY: Operand<TFloat32>,
        Toutput: DataType<V>
    ): QuantizedAdd<V> = java.quantizedAdd<V, T, U>(
        x,
        y,
        minX,
        maxX,
        minY,
        maxY,
        Toutput
    )

    /**
     * Returns x * y element-wise, working on quantized buffers.
     *
     * @param V data type for ` z()` output
     * @param x
     * @param y
     * @param minX The float value that the lowest quantized `x` value represents.
     * @param maxX The float value that the highest quantized `x` value represents.
     * @param minY The float value that the lowest quantized `y` value represents.
     * @param maxY The float value that the highest quantized `y` value represents.
     * @param Toutput
     * @return a new instance of QuantizedMul
     * @see org.tensorflow.op.MathOps.quantizedMul
     */
    public fun <V : TType, T : TType, U : TType> quantizedMul(
        x: Operand<T>,
        y: Operand<U>,
        minX: Operand<TFloat32>,
        maxX: Operand<TFloat32>,
        minY: Operand<TFloat32>,
        maxY: Operand<TFloat32>,
        Toutput: DataType<V>
    ): QuantizedMul<V> = java.quantizedMul<V, T, U>(
        x,
        y,
        minX,
        maxX,
        minY,
        maxY,
        Toutput
    )

    /**
     * Returns the real part of a complex number.
     *
     *  Given a tensor `input` of complex numbers, this operation returns a tensor of
     *  type `float` that is the real part of each element in `input`. All elements in
     *  `input` must be complex numbers of the form \\(a + bj\\), where <i>a</i> is the real
     *   part returned by this operation and <i>b</i> is the imaginary part.
     *
     *  For example:
     *  ```
     *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
     *  tf.real(input) ==> [-2.25, 3.25]
     *  ```
     *
     *
     * @param U data type for ` output()` output
     * @param input
     * @return a new instance of Real
     * @see org.tensorflow.op.MathOps.real
     */
    public fun <T : TType> real(input: Operand<T>): Real<TFloat32> = java.real<T>(
        input
    )

    /**
     * Returns the real part of a complex number.
     *
     *  Given a tensor `input` of complex numbers, this operation returns a tensor of
     *  type `float` that is the real part of each element in `input`. All elements in
     *  `input` must be complex numbers of the form \\(a + bj\\), where <i>a</i> is the real
     *   part returned by this operation and <i>b</i> is the imaginary part.
     *
     *  For example:
     *  ```
     *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
     *  tf.real(input) ==> [-2.25, 3.25]
     *  ```
     *
     *
     * @param U data type for ` output()` output
     * @param input
     * @param Tout
     * @return a new instance of Real
     * @see org.tensorflow.op.MathOps.real
     */
    public fun <U : TNumber, T : TType> real(input: Operand<T>, Tout: DataType<U>): Real<U> =
        java.real<U, T>(
            input,
            Tout
        )

    /**
     * Returns x / y element-wise for real types.
     *
     *  If `x` and `y` are reals, this will return the floating-point division.
     *
     *  <i>NOTE</i>: `Div` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of RealDiv
     * @see org.tensorflow.op.MathOps.realDiv
     */
    public fun <T : TType> realDiv(x: Operand<T>, y: Operand<T>): RealDiv<T> = java.realDiv<T>(
        x,
        y
    )

    /**
     * Computes the reciprocal of x element-wise.
     *
     *  I.e., \\(y = 1 / x\\).
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Reciprocal
     * @see org.tensorflow.op.MathOps.reciprocal
     */
    public fun <T : TType> reciprocal(x: Operand<T>): Reciprocal<T> = java.reciprocal<T>(
        x
    )

    /**
     * Returns element-wise integer closest to x.
     *
     *  If the result is midway between two representable values,
     *  the even representable is chosen.
     *  For example:
     *  ```
     *  rint(-1.5) ==> -2.0
     *  rint(0.5000001) ==> 1.0
     *  rint([-1.7, -1.5, -0.2, 0.2, 1.5, 1.7, 2.0]) ==> [-2., -2., -0., 0., 2., 2., 2.]
     *  ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Rint
     * @see org.tensorflow.op.MathOps.rint
     */
    public fun <T : TNumber> rint(x: Operand<T>): Rint<T> = java.rint<T>(
        x
    )

    /**
     * Rounds the values of a tensor to the nearest integer, element-wise.
     *
     *  Rounds half to even.  Also known as bankers rounding. If you want to round
     *  according to the current system rounding mode use std::cint.
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Round
     * @see org.tensorflow.op.MathOps.round
     */
    public fun <T : TType> round(x: Operand<T>): Round<T> = java.round<T>(
        x
    )

    /**
     * Computes reciprocal of square root of x element-wise.
     *
     *  I.e., \\(y = 1 / \sqrt{x}\\).
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Rsqrt
     * @see org.tensorflow.op.MathOps.rsqrt
     */
    public fun <T : TType> rsqrt(x: Operand<T>): Rsqrt<T> = java.rsqrt<T>(
        x
    )

    /**
     * Computes the maximum along segments of a tensor.
     *
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
     *  for an explanation of segments.
     *
     *  Computes a tensor such that
     *  \\(output_i = \max_j(data_j)\\) where `max` is over `j` such
     *  that `segment_ids&#91;j] == i`.
     *
     *  If the max is empty for a given segment ID `i`, `output&#91;i] = 0`.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentMax.png" alt>
     *  </div>
     *
     *  For example:
     *  ```
     *  c = tf.constant([[1,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
     *  tf.segment_max(c, tf.constant([0, 0, 1]))
     *  # ==> [[4, 3, 3, 4],
     *  #      [5, 6, 7, 8]]
     *  ```
     *
     *
     * @param T data type for ` output()` output
     * @param data
     * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
     *  first dimension.  Values should be sorted and can be repeated.
     * @return a new instance of SegmentMax
     * @see org.tensorflow.op.MathOps.segmentMax
     */
    public fun <T : TNumber, U : TNumber> segmentMax(`data`: Operand<T>, segmentIds: Operand<U>):
        SegmentMax<T> = java.segmentMax<T, U>(
            data,
            segmentIds
        )

    /**
     * Computes the mean along segments of a tensor.
     *
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
     *  for an explanation of segments.
     *
     *  Computes a tensor such that
     *  \\(output_i = \frac{\sum_j data_j}{N}\\) where `mean` is
     *  over `j` such that `segment_ids&#91;j] == i` and `N` is the total number of
     *  values summed.
     *
     *  If the mean is empty for a given segment ID `i`, `output&#91;i] = 0`.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentMean.png" alt>
     *  </div>
     *
     *  For example:
     *  ```
     *  c = tf.constant([[1.0,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
     *  tf.segment_mean(c, tf.constant([0, 0, 1]))
     *  # ==> [[2.5, 2.5, 2.5, 2.5],
     *  #      [5, 6, 7, 8]]
     *  ```
     *
     *
     * @param T data type for ` output()` output
     * @param data
     * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
     *  first dimension.  Values should be sorted and can be repeated.
     * @return a new instance of SegmentMean
     * @see org.tensorflow.op.MathOps.segmentMean
     */
    public fun <T : TType, U : TNumber> segmentMean(`data`: Operand<T>, segmentIds: Operand<U>):
        SegmentMean<T> = java.segmentMean<T, U>(
            data,
            segmentIds
        )

    /**
     * Computes the minimum along segments of a tensor.
     *
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
     *  for an explanation of segments.
     *
     *  Computes a tensor such that
     *  \\(output_i = \min_j(data_j)\\) where `min` is over `j` such
     *  that `segment_ids&#91;j] == i`.
     *
     *  If the min is empty for a given segment ID `i`, `output&#91;i] = 0`.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentMin.png" alt>
     *  </div>
     *
     *  For example:
     *  ```
     *  c = tf.constant([[1,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
     *  tf.segment_min(c, tf.constant([0, 0, 1]))
     *  # ==> [[1, 2, 2, 1],
     *  #      [5, 6, 7, 8]]
     *  ```
     *
     *
     * @param T data type for ` output()` output
     * @param data
     * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
     *  first dimension.  Values should be sorted and can be repeated.
     * @return a new instance of SegmentMin
     * @see org.tensorflow.op.MathOps.segmentMin
     */
    public fun <T : TNumber, U : TNumber> segmentMin(`data`: Operand<T>, segmentIds: Operand<U>):
        SegmentMin<T> = java.segmentMin<T, U>(
            data,
            segmentIds
        )

    /**
     * Computes the product along segments of a tensor.
     *
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
     *  for an explanation of segments.
     *
     *  Computes a tensor such that
     *  \\(output_i = \prod_j data_j\\) where the product is over `j` such
     *  that `segment_ids&#91;j] == i`.
     *
     *  If the product is empty for a given segment ID `i`, `output&#91;i] = 1`.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentProd.png" alt>
     *  </div>
     *
     *  For example:
     *  ```
     *  c = tf.constant([[1,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
     *  tf.segment_prod(c, tf.constant([0, 0, 1]))
     *  # ==> [[4, 6, 6, 4],
     *  #      [5, 6, 7, 8]]
     *  ```
     *
     *
     * @param T data type for ` output()` output
     * @param data
     * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
     *  first dimension.  Values should be sorted and can be repeated.
     * @return a new instance of SegmentProd
     * @see org.tensorflow.op.MathOps.segmentProd
     */
    public fun <T : TType, U : TNumber> segmentProd(`data`: Operand<T>, segmentIds: Operand<U>):
        SegmentProd<T> = java.segmentProd<T, U>(
            data,
            segmentIds
        )

    /**
     * Computes the sum along segments of a tensor.
     *
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
     *  for an explanation of segments.
     *
     *  Computes a tensor such that
     *  \\(output_i = \sum_j data_j\\) where sum is over `j` such
     *  that `segment_ids&#91;j] == i`.
     *
     *  If the sum is empty for a given segment ID `i`, `output&#91;i] = 0`.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentSum.png" alt>
     *  </div>
     *
     *  For example:
     *  ```
     *  c = tf.constant([[1,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
     *  tf.segment_sum(c, tf.constant([0, 0, 1]))
     *  # ==> [[5, 5, 5, 5],
     *  #      [5, 6, 7, 8]]
     *  ```
     *
     *
     * @param T data type for ` output()` output
     * @param data
     * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
     *  first dimension.  Values should be sorted and can be repeated.
     * @return a new instance of SegmentSum
     * @see org.tensorflow.op.MathOps.segmentSum
     */
    public fun <T : TType, U : TNumber> segmentSum(`data`: Operand<T>, segmentIds: Operand<U>):
        SegmentSum<T> = java.segmentSum<T, U>(
            data,
            segmentIds
        )

    /**
     * Computes sigmoid of `x` element-wise.
     *
     *  Specifically, `y = 1 / (1 + exp(-x))`.
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Sigmoid
     * @see org.tensorflow.op.MathOps.sigmoid
     */
    public fun <T : TType> sigmoid(x: Operand<T>): Sigmoid<T> = java.sigmoid<T>(
        x
    )

    /**
     * Returns an element-wise indication of the sign of a number.
     *
     *  `y = sign(x) = -1` if `x < 0`; 0 if `x == 0`; 1 if `x > 0`.
     *
     *  For complex numbers, `y = sign(x) = x / |x|` if `x != 0`, otherwise `y = 0`.
     *
     *  Example usage:
     *  >>> tf.math.sign(&#91;0., 2., -3.])
     *  <tf.Tensor: shape=(3,), dtype=float32, numpy=array(&#91; 0.,  1., -1.], dtype=float32)>
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Sign
     * @see org.tensorflow.op.MathOps.sign
     */
    public fun <T : TType> sign(x: Operand<T>): Sign<T> = java.sign<T>(
        x
    )

    /**
     * Computes sine of x element-wise.
     *
     *    Given an input tensor, this function computes sine of every
     *    element in the tensor. Input range is `(-inf, inf)` and
     *    output range is `&#91;-1,1]`.
     *
     *    ```
     *    x = tf.constant([-float("inf"), -9, -0.5, 1, 1.2, 200, 10, float("inf")])
     *    tf.math.sin(x) ==> [nan -0.4121185 -0.47942555 0.84147096
     * 0.9320391 -0.87329733 -0.54402107 nan]
     *    ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Sin
     * @see org.tensorflow.op.MathOps.sin
     */
    public fun <T : TType> sin(x: Operand<T>): Sin<T> = java.sin<T>(
        x
    )

    /**
     * Computes hyperbolic sine of x element-wise.
     *
     *    Given an input tensor, this function computes hyperbolic sine of every
     *    element in the tensor. Input range is `&#91;-inf,inf]` and output range
     *    is `&#91;-inf,inf]`.
     *
     *    ```
     *    x = tf.constant([-float("inf"), -9, -0.5, 1, 1.2, 2, 10, float("inf")])
     *    tf.math.sinh(x) ==> [-inf -4.0515420e+03 -5.2109528e-01 1.1752012e+00 1.5094614e+00
     * 3.6268604e+00 1.1013232e+04 inf]
     *    ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Sinh
     * @see org.tensorflow.op.MathOps.sinh
     */
    public fun <T : TType> sinh(x: Operand<T>): Sinh<T> = java.sinh<T>(
        x
    )

    /**
     * Computes softplus: `log(exp(features) + 1)`.
     *
     * @param T data type for ` activations()` output
     * @param features
     * @return a new instance of Softplus
     * @see org.tensorflow.op.MathOps.softplus
     */
    public fun <T : TNumber> softplus(features: Operand<T>): Softplus<T> = java.softplus<T>(
        features
    )

    /**
     * Computes square root of x element-wise.
     *
     *  I.e., \\(y = \sqrt{x} = x^{1/2}\\).
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Sqrt
     * @see org.tensorflow.op.MathOps.sqrt
     */
    public fun <T : TType> sqrt(x: Operand<T>): Sqrt<T> = java.sqrt<T>(
        x
    )

    /**
     * Computes square of x element-wise.
     *
     *  I.e., \\(y = x * x = x^2\\).
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Square
     * @see org.tensorflow.op.MathOps.square
     */
    public fun <T : TType> square(x: Operand<T>): Square<T> = java.square<T>(
        x
    )

    /**
     * Returns (x - y)(x - y) element-wise.
     *
     *  <i>NOTE</i>: `math.SquaredDifference` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of SquaredDifference
     * @see org.tensorflow.op.MathOps.squaredDifference
     */
    public fun <T : TType> squaredDifference(x: Operand<T>, y: Operand<T>): SquaredDifference<T> =
        java.squaredDifference<T>(
            x,
            y
        )

    /**
     * Returns x - y element-wise.
     *
     *  <i>NOTE</i>: `math.Sub` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of Sub
     * @see org.tensorflow.op.MathOps.sub
     */
    public fun <T : TType> sub(x: Operand<T>, y: Operand<T>): Sub<T> = java.sub<T>(
        x,
        y
    )

    /**
     * Computes tan of x element-wise.
     *
     *    Given an input tensor, this function computes tangent of every
     *    element in the tensor. Input range is `(-inf, inf)` and
     *    output range is `(-inf, inf)`. If input lies outside the boundary, `nan`
     *    is returned.
     *
     *    ```
     *    x = tf.constant([-float("inf"), -9, -0.5, 1, 1.2, 200, 10000, float("inf")])
     *    tf.math.tan(x) ==> [nan 0.45231566 -0.5463025 1.5574077 2.572152 -1.7925274 0.32097113
     * nan]
     *    ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Tan
     * @see org.tensorflow.op.MathOps.tan
     */
    public fun <T : TType> tan(x: Operand<T>): Tan<T> = java.tan<T>(
        x
    )

    /**
     * Computes hyperbolic tangent of `x` element-wise.
     *
     *    Given an input tensor, this function computes hyperbolic tangent of every
     *    element in the tensor. Input range is `&#91;-inf, inf]` and
     *    output range is `&#91;-1,1]`.
     *
     *    ```
     *    x = tf.constant([-float("inf"), -5, -0.5, 1, 1.2, 2, 3, float("inf")])
     *    tf.math.tanh(x) ==> [-1. -0.99990916 -0.46211717 0.7615942 0.8336547 0.9640276 0.9950547
     * 1.]
     *    ```
     *
     *
     * @param T data type for ` y()` output
     * @param x
     * @return a new instance of Tanh
     * @see org.tensorflow.op.MathOps.tanh
     */
    public fun <T : TType> tanh(x: Operand<T>): Tanh<T> = java.tanh<T>(
        x
    )

    /**
     * Returns x / y element-wise for integer types.
     *
     *  Truncation designates that negative numbers will round fractional quantities
     *  toward zero. I.e. -7 / 5 = -1. This matches C semantics but it is different
     *  than Python semantics. See `FloorDiv` for a division function that matches
     *  Python Semantics.
     *
     *  <i>NOTE</i>: `math.TruncateDiv` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of TruncateDiv
     * @see org.tensorflow.op.MathOps.truncateDiv
     */
    public fun <T : TType> truncateDiv(x: Operand<T>, y: Operand<T>): TruncateDiv<T> =
        java.truncateDiv<T>(
            x,
            y
        )

    /**
     * Returns element-wise remainder of division. This emulates C semantics in that
     *
     *  the result here is consistent with a truncating divide. E.g. `truncate(x / y) *
     *  y + truncate_mod(x, y) = x`.
     *
     *  <i>NOTE</i>: `math.TruncateMod` supports broadcasting. More about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of TruncateMod
     * @see org.tensorflow.op.MathOps.truncateMod
     */
    public fun <T : TNumber> truncateMod(x: Operand<T>, y: Operand<T>): TruncateMod<T> =
        java.truncateMod<T>(
            x,
            y
        )

    /**
     * Computes the maximum along segments of a tensor.
     *
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
     *  for an explanation of segments.
     *
     *  This operator is similar to the unsorted segment sum operator found
     *  &#91;(here)](../../../api_docs/python/math_ops.md#UnsortedSegmentSum).
     *  Instead of computing the sum over segments, it computes the maximum such that:
     *
     *  \\(output_i = \max_{j...} data&#91;j...]\\) where max is over tuples `j...` such
     *  that `segment_ids&#91;j...] == i`.
     *
     *  If the maximum is empty for a given segment ID `i`, it outputs the smallest
     *  possible value for the specific numeric type,
     *  `output&#91;i] = numeric_limits<T>::lowest()`.
     *
     *  If the given segment ID `i` is negative, then the corresponding value is
     *  dropped, and will not be included in the result.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/UnsortedSegmentMax.png" alt>
     *  </div>
     *
     *  For example:
     *  ```
     *  c = tf.constant([[1,2,3,4], [5,6,7,8], [4,3,2,1]])
     *  tf.unsorted_segment_max(c, tf.constant([0, 1, 0]), num_segments=2)
     *  # ==> [[ 4,  3, 3, 4],
     *  #       [5,  6, 7, 8]]
     *  ```
     *
     *
     * @param T data type for ` output()` output
     * @param data
     * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
     * @param numSegments
     * @return a new instance of UnsortedSegmentMax
     * @see org.tensorflow.op.MathOps.unsortedSegmentMax
     */
    public fun <T : TNumber, U : TNumber, V : TNumber> unsortedSegmentMax(
        `data`: Operand<T>,
        segmentIds: Operand<U>,
        numSegments: Operand<V>
    ): UnsortedSegmentMax<T> = java.unsortedSegmentMax<T, U, V>(
        data,
        segmentIds,
        numSegments
    )

    /**
     * Computes the minimum along segments of a tensor.
     *
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
     *  for an explanation of segments.
     *
     *  This operator is similar to the unsorted segment sum operator found
     *  &#91;(here)](../../../api_docs/python/math_ops.md#UnsortedSegmentSum).
     *  Instead of computing the sum over segments, it computes the minimum such that:
     *
     *  \\(output_i = \min_{j...} data_&#91;j...]\\) where min is over tuples `j...` such
     *  that `segment_ids&#91;j...] == i`.
     *
     *  If the minimum is empty for a given segment ID `i`, it outputs the largest
     *  possible value for the specific numeric type,
     *  `output&#91;i] = numeric_limits<T>::max()`.
     *
     *  For example:
     *  ```
     *  c = tf.constant([[1,2,3,4], [5,6,7,8], [4,3,2,1]])
     *  tf.unsorted_segment_min(c, tf.constant([0, 1, 0]), num_segments=2)
     *  # ==> [[ 1,  2, 2, 1],
     *  #       [5,  6, 7, 8]]
     *  ```
     *
     *  If the given segment ID `i` is negative, then the corresponding value is
     *  dropped, and will not be included in the result.
     *
     * @param T data type for ` output()` output
     * @param data
     * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
     * @param numSegments
     * @return a new instance of UnsortedSegmentMin
     * @see org.tensorflow.op.MathOps.unsortedSegmentMin
     */
    public fun <T : TNumber, U : TNumber, V : TNumber> unsortedSegmentMin(
        `data`: Operand<T>,
        segmentIds: Operand<U>,
        numSegments: Operand<V>
    ): UnsortedSegmentMin<T> = java.unsortedSegmentMin<T, U, V>(
        data,
        segmentIds,
        numSegments
    )

    /**
     * Computes the product along segments of a tensor.
     *
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
     *  for an explanation of segments.
     *
     *  This operator is similar to the unsorted segment sum operator found
     *  &#91;(here)](../../../api_docs/python/math_ops.md#UnsortedSegmentSum).
     *  Instead of computing the sum over segments, it computes the product of all
     *  entries belonging to a segment such that:
     *
     *  \\(output_i = \prod_{j...} data&#91;j...]\\) where the product is over tuples
     *  `j...` such that `segment_ids&#91;j...] == i`.
     *
     *  For example:
     *  ```
     *  c = tf.constant([[1,2,3,4], [5,6,7,8], [4,3,2,1]])
     *  tf.unsorted_segment_prod(c, tf.constant([0, 1, 0]), num_segments=2)
     *  # ==> [[ 4,  6, 6, 4],
     *  #       [5,  6, 7, 8]]
     *  ```
     *
     *  If there is no entry for a given segment ID `i`, it outputs 1.
     *
     *  If the given segment ID `i` is negative, then the corresponding value is
     *  dropped, and will not be included in the result.
     *
     * @param T data type for ` output()` output
     * @param data
     * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
     * @param numSegments
     * @return a new instance of UnsortedSegmentProd
     * @see org.tensorflow.op.MathOps.unsortedSegmentProd
     */
    public fun <T : TType, U : TNumber, V : TNumber> unsortedSegmentProd(
        `data`: Operand<T>,
        segmentIds: Operand<U>,
        numSegments: Operand<V>
    ): UnsortedSegmentProd<T> = java.unsortedSegmentProd<T, U, V>(
        data,
        segmentIds,
        numSegments
    )

    /**
     * Computes the sum along segments of a tensor.
     *
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
     *  for an explanation of segments.
     *
     *  Computes a tensor such that
     *  \\(output&#91;i] = \sum_{j...} data&#91;j...]\\) where the sum is over tuples `j...` such
     *  that `segment_ids&#91;j...] == i`.  Unlike `SegmentSum`, `segment_ids`
     *  need not be sorted and need not cover all values in the full
     *  range of valid values.
     *
     *  If the sum is empty for a given segment ID `i`, `output&#91;i] = 0`.
     *  If the given segment ID `i` is negative, the value is dropped and will not be
     *  added to the sum of the segment.
     *
     *  `num_segments` should equal the number of distinct segment IDs.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/UnsortedSegmentSum.png" alt>
     *  </div>
     *  ```
     *  c = tf.constant([[1,2,3,4], [5,6,7,8], [4,3,2,1]])
     *  tf.unsorted_segment_sum(c, tf.constant([0, 1, 0]), num_segments=2)
     *  # ==> [[ 5,  5, 5, 5],
     *  #       [5,  6, 7, 8]]
     *  ```
     *
     *
     * @param T data type for ` output()` output
     * @param data
     * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
     * @param numSegments
     * @return a new instance of UnsortedSegmentSum
     * @see org.tensorflow.op.MathOps.unsortedSegmentSum
     */
    public fun <T : TType, U : TNumber, V : TNumber> unsortedSegmentSum(
        `data`: Operand<T>,
        segmentIds: Operand<U>,
        numSegments: Operand<V>
    ): UnsortedSegmentSum<T> = java.unsortedSegmentSum<T, U, V>(
        data,
        segmentIds,
        numSegments
    )

    /**
     * Returns 0 if x == 0, and x / y otherwise, elementwise.
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of Xdivy
     * @see org.tensorflow.op.MathOps.xdivy
     */
    public fun <T : TType> xdivy(x: Operand<T>, y: Operand<T>): Xdivy<T> = java.xdivy<T>(
        x,
        y
    )

    /**
     * Returns 0 if x == 0, and x * log1p(y) otherwise, elementwise.
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of Xlog1py
     * @see org.tensorflow.op.MathOps.xlog1py
     */
    public fun <T : TType> xlog1py(x: Operand<T>, y: Operand<T>): Xlog1py<T> = java.xlog1py<T>(
        x,
        y
    )

    /**
     * Returns 0 if x == 0, and x * log(y) otherwise, elementwise.
     *
     * @param T data type for ` z()` output
     * @param x
     * @param y
     * @return a new instance of Xlogy
     * @see org.tensorflow.op.MathOps.xlogy
     */
    public fun <T : TType> xlogy(x: Operand<T>, y: Operand<T>): Xlogy<T> = java.xlogy<T>(
        x,
        y
    )

    /**
     * Compute the Hurwitz zeta function \\(\zeta(x, q)\\).
     *
     *  The Hurwitz zeta function is defined as:
     *
     *  \\(\zeta(x, q) = \sum_{n=0}^{\infty} (q + n)^{-x}\\)
     *
     * @param T data type for ` z()` output
     * @param x
     * @param q
     * @return a new instance of Zeta
     * @see org.tensorflow.op.MathOps.zeta
     */
    public fun <T : TNumber> zeta(x: Operand<T>, q: Operand<T>): Zeta<T> = java.zeta<T>(
        x,
        q
    )
}
