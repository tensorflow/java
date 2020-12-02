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
 * An API for building {@code math} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class MathOps(
  /**
   * Get the parent {@link KotlinOps} object.
   */
  public val ops: KotlinOps
) {
  public val java: org.tensorflow.op.MathOps = ops.java.math

  /**
   * Returns the current {@link Scope scope} of this API
   */
  public val scope: Scope = ops.scope

  public fun <T : TNumber> abs(x: Operand<T>): Abs<T> = java.abs<T>(x)

  public fun <T : TType> accumulateN(inputs: Iterable<Operand<T>>, shape: Shape): AccumulateN<T> =
      java.accumulateN<T>(inputs, shape)

  public fun <T : TType> acos(x: Operand<T>): Acos<T> = java.acos<T>(x)

  public fun <T : TType> acosh(x: Operand<T>): Acosh<T> = java.acosh<T>(x)

  public fun <T : TType> add(x: Operand<T>, y: Operand<T>): Add<T> = java.add<T>(x, y)

  public fun <T : TType> addN(inputs: Iterable<Operand<T>>): AddN<T> = java.addN<T>(inputs)

  public fun <T : TType> angle(input: Operand<T>): Angle<TFloat32> = java.angle<T>(input)

  public fun <U : TNumber, T : TType> angle(input: Operand<T>, Tout: DataType<U>): Angle<U> =
      java.angle<U, T>(input, Tout)

  public fun <T : TType> approximateEqual(
    x: Operand<T>,
    y: Operand<T>,
    vararg options: ApproximateEqual.Options
  ): ApproximateEqual = java.approximateEqual<T>(x, y, *options)

  public fun <T : TType, U : TNumber> argMax(input: Operand<T>, dimension: Operand<U>):
      ArgMax<TInt64> = java.argMax<T, U>(input, dimension)

  public fun <V : TNumber, T : TType, U : TNumber> argMax(
    input: Operand<T>,
    dimension: Operand<U>,
    outputType: DataType<V>
  ): ArgMax<V> = java.argMax<V, T, U>(input, dimension, outputType)

  public fun <T : TType, U : TNumber> argMin(input: Operand<T>, dimension: Operand<U>):
      ArgMin<TInt64> = java.argMin<T, U>(input, dimension)

  public fun <V : TNumber, T : TType, U : TNumber> argMin(
    input: Operand<T>,
    dimension: Operand<U>,
    outputType: DataType<V>
  ): ArgMin<V> = java.argMin<V, T, U>(input, dimension, outputType)

  public fun <T : TType> asin(x: Operand<T>): Asin<T> = java.asin<T>(x)

  public fun <T : TType> asinh(x: Operand<T>): Asinh<T> = java.asinh<T>(x)

  public fun <T : TType> atan(x: Operand<T>): Atan<T> = java.atan<T>(x)

  public fun <T : TNumber> atan2(y: Operand<T>, x: Operand<T>): Atan2<T> = java.atan2<T>(y, x)

  public fun <T : TType> atanh(x: Operand<T>): Atanh<T> = java.atanh<T>(x)

  public fun <T : TNumber> betainc(
    a: Operand<T>,
    b: Operand<T>,
    x: Operand<T>
  ): Betainc<T> = java.betainc<T>(a, b, x)

  public fun <T : TNumber> bincount(
    arr: Operand<TInt32>,
    size: Operand<TInt32>,
    weights: Operand<T>
  ): Bincount<T> = java.bincount<T>(arr, size, weights)

  public fun <T : TNumber> ceil(x: Operand<T>): Ceil<T> = java.ceil<T>(x)

  public fun <T : TType> compareAndBitpack(input: Operand<T>, threshold: Operand<T>):
      CompareAndBitpack = java.compareAndBitpack<T>(input, threshold)

  public fun <T : TType> complexAbs(x: Operand<T>): ComplexAbs<TFloat32> = java.complexAbs<T>(x)

  public fun <U : TNumber, T : TType> complexAbs(x: Operand<T>, Tout: DataType<U>): ComplexAbs<U> =
      java.complexAbs<U, T>(x, Tout)

  public fun <T : TType> conj(input: Operand<T>): Conj<T> = java.conj<T>(input)

  public fun <T : TType> cos(x: Operand<T>): Cos<T> = java.cos<T>(x)

  public fun <T : TType> cosh(x: Operand<T>): Cosh<T> = java.cosh<T>(x)

  public fun <T : TType, U : TNumber> cumprod(
    x: Operand<T>,
    axis: Operand<U>,
    vararg options: Cumprod.Options
  ): Cumprod<T> = java.cumprod<T, U>(x, axis, *options)

  public fun <T : TType, U : TNumber> cumsum(
    x: Operand<T>,
    axis: Operand<U>,
    vararg options: Cumsum.Options
  ): Cumsum<T> = java.cumsum<T, U>(x, axis, *options)

  public fun <U : TNumber, T : TNumber> denseBincount(
    input: Operand<T>,
    size: Operand<T>,
    weights: Operand<U>,
    vararg options: DenseBincount.Options
  ): DenseBincount<U> = java.denseBincount<U, T>(input, size, weights, *options)

  public fun <T : TNumber> digamma(x: Operand<T>): Digamma<T> = java.digamma<T>(x)

  public fun <T : TType> div(x: Operand<T>, y: Operand<T>): Div<T> = java.div<T>(x, y)

  public fun <T : TType> divNoNan(x: Operand<T>, y: Operand<T>): DivNoNan<T> = java.divNoNan<T>(x,
      y)

  public fun <T : TType> equal(
    x: Operand<T>,
    y: Operand<T>,
    vararg options: Equal.Options
  ): Equal = java.equal<T>(x, y, *options)

  public fun <T : TNumber> erf(x: Operand<T>): Erf<T> = java.erf<T>(x)

  public fun <T : TNumber> erfc(x: Operand<T>): Erfc<T> = java.erfc<T>(x)

  public fun <T : TNumber> erfinv(x: Operand<T>): erfinv<T> = java.erfinv<T>(x)

  public fun <T : TType> exp(x: Operand<T>): Exp<T> = java.exp<T>(x)

  public fun <T : TType> expm1(x: Operand<T>): Expm1<T> = java.expm1<T>(x)

  public fun fact(): Fact = java.fact()

  public fun <T : TNumber> floor(x: Operand<T>): Floor<T> = java.floor<T>(x)

  public fun <T : TType> floorDiv(x: Operand<T>, y: Operand<T>): FloorDiv<T> = java.floorDiv<T>(x,
      y)

  public fun <T : TNumber> floorMod(x: Operand<T>, y: Operand<T>): FloorMod<T> = java.floorMod<T>(x,
      y)

  public fun <T : TNumber> greater(x: Operand<T>, y: Operand<T>): Greater = java.greater<T>(x, y)

  public fun <T : TNumber> greaterEqual(x: Operand<T>, y: Operand<T>): GreaterEqual =
      java.greaterEqual<T>(x, y)

  public fun <T : TNumber> igamma(a: Operand<T>, x: Operand<T>): Igamma<T> = java.igamma<T>(a, x)

  public fun <T : TNumber> igammac(a: Operand<T>, x: Operand<T>): Igammac<T> = java.igammac<T>(a, x)

  public fun <T : TType> imag(input: Operand<T>): Imag<TFloat32> = java.imag<T>(input)

  public fun <U : TNumber, T : TType> imag(input: Operand<T>, Tout: DataType<U>): Imag<U> =
      java.imag<U, T>(input, Tout)

  public fun <T : TNumber> invertPermutation(x: Operand<T>): InvertPermutation<T> =
      java.invertPermutation<T>(x)

  public fun <T : TNumber> isFinite(x: Operand<T>): IsFinite = java.isFinite<T>(x)

  public fun <T : TNumber> isInf(x: Operand<T>): IsInf = java.isInf<T>(x)

  public fun <T : TNumber> isNan(x: Operand<T>): IsNan = java.isNan<T>(x)

  public fun <T : TNumber> less(x: Operand<T>, y: Operand<T>): Less = java.less<T>(x, y)

  public fun <T : TNumber> lessEqual(x: Operand<T>, y: Operand<T>): LessEqual = java.lessEqual<T>(x,
      y)

  public fun <T : TNumber> lgamma(x: Operand<T>): Lgamma<T> = java.lgamma<T>(x)

  public fun <T : TType> log(x: Operand<T>): Log<T> = java.log<T>(x)

  public fun <T : TType> log1p(x: Operand<T>): Log1p<T> = java.log1p<T>(x)

  public fun logicalAnd(x: Operand<TBool>, y: Operand<TBool>): LogicalAnd = java.logicalAnd(x, y)

  public fun logicalNot(x: Operand<TBool>): LogicalNot = java.logicalNot(x)

  public fun logicalOr(x: Operand<TBool>, y: Operand<TBool>): LogicalOr = java.logicalOr(x, y)

  public fun <T : TNumber> maximum(x: Operand<T>, y: Operand<T>): Maximum<T> = java.maximum<T>(x, y)

  public fun <T : TType, U : TNumber> mean(
    input: Operand<T>,
    axis: Operand<U>,
    vararg options: Mean.Options
  ): Mean<T> = java.mean<T, U>(input, axis, *options)

  public fun <T : TNumber> minimum(x: Operand<T>, y: Operand<T>): Minimum<T> = java.minimum<T>(x, y)

  public fun <T : TNumber> mod(x: Operand<T>, y: Operand<T>): Mod<T> = java.mod<T>(x, y)

  public fun <T : TType> mul(x: Operand<T>, y: Operand<T>): Mul<T> = java.mul<T>(x, y)

  public fun <T : TType> mulNoNan(x: Operand<T>, y: Operand<T>): MulNoNan<T> = java.mulNoNan<T>(x,
      y)

  public fun <T : TNumber> ndtri(x: Operand<T>): Ndtri<T> = java.ndtri<T>(x)

  public fun <T : TType> neg(x: Operand<T>): Neg<T> = java.neg<T>(x)

  public fun <T : TNumber> nextAfter(x1: Operand<T>, x2: Operand<T>): NextAfter<T> =
      java.nextAfter<T>(x1, x2)

  public fun <T : TType> notEqual(
    x: Operand<T>,
    y: Operand<T>,
    vararg options: NotEqual.Options
  ): NotEqual = java.notEqual<T>(x, y, *options)

  public fun <T : TNumber> polygamma(a: Operand<T>, x: Operand<T>): Polygamma<T> =
      java.polygamma<T>(a, x)

  public fun <T : TNumber> populationCount(x: Operand<T>): PopulationCount =
      java.populationCount<T>(x)

  public fun <T : TType> pow(x: Operand<T>, y: Operand<T>): Pow<T> = java.pow<T>(x, y)

  public fun <V : TType, T : TType, U : TType> quantizedAdd(
    x: Operand<T>,
    y: Operand<U>,
    minX: Operand<TFloat32>,
    maxX: Operand<TFloat32>,
    minY: Operand<TFloat32>,
    maxY: Operand<TFloat32>,
    Toutput: DataType<V>
  ): QuantizedAdd<V> = java.quantizedAdd<V, T, U>(x, y, minX, maxX, minY, maxY, Toutput)

  public fun <V : TType, T : TType, U : TType> quantizedMul(
    x: Operand<T>,
    y: Operand<U>,
    minX: Operand<TFloat32>,
    maxX: Operand<TFloat32>,
    minY: Operand<TFloat32>,
    maxY: Operand<TFloat32>,
    Toutput: DataType<V>
  ): QuantizedMul<V> = java.quantizedMul<V, T, U>(x, y, minX, maxX, minY, maxY, Toutput)

  public fun <T : TType> real(input: Operand<T>): Real<TFloat32> = java.real<T>(input)

  public fun <U : TNumber, T : TType> real(input: Operand<T>, Tout: DataType<U>): Real<U> =
      java.real<U, T>(input, Tout)

  public fun <T : TType> realDiv(x: Operand<T>, y: Operand<T>): RealDiv<T> = java.realDiv<T>(x, y)

  public fun <T : TType> reciprocal(x: Operand<T>): Reciprocal<T> = java.reciprocal<T>(x)

  public fun <T : TNumber> rint(x: Operand<T>): Rint<T> = java.rint<T>(x)

  public fun <T : TType> round(x: Operand<T>): Round<T> = java.round<T>(x)

  public fun <T : TType> rsqrt(x: Operand<T>): Rsqrt<T> = java.rsqrt<T>(x)

  public fun <T : TNumber, U : TNumber> segmentMax(`data`: Operand<T>, segmentIds: Operand<U>):
      SegmentMax<T> = java.segmentMax<T, U>(data, segmentIds)

  public fun <T : TType, U : TNumber> segmentMean(`data`: Operand<T>, segmentIds: Operand<U>):
      SegmentMean<T> = java.segmentMean<T, U>(data, segmentIds)

  public fun <T : TNumber, U : TNumber> segmentMin(`data`: Operand<T>, segmentIds: Operand<U>):
      SegmentMin<T> = java.segmentMin<T, U>(data, segmentIds)

  public fun <T : TType, U : TNumber> segmentProd(`data`: Operand<T>, segmentIds: Operand<U>):
      SegmentProd<T> = java.segmentProd<T, U>(data, segmentIds)

  public fun <T : TType, U : TNumber> segmentSum(`data`: Operand<T>, segmentIds: Operand<U>):
      SegmentSum<T> = java.segmentSum<T, U>(data, segmentIds)

  public fun <T : TType> sigmoid(x: Operand<T>): Sigmoid<T> = java.sigmoid<T>(x)

  public fun <T : TType> sign(x: Operand<T>): Sign<T> = java.sign<T>(x)

  public fun <T : TType> sin(x: Operand<T>): Sin<T> = java.sin<T>(x)

  public fun <T : TType> sinh(x: Operand<T>): Sinh<T> = java.sinh<T>(x)

  public fun <T : TNumber> softplus(features: Operand<T>): Softplus<T> = java.softplus<T>(features)

  public fun <T : TType> sqrt(x: Operand<T>): Sqrt<T> = java.sqrt<T>(x)

  public fun <T : TType> square(x: Operand<T>): Square<T> = java.square<T>(x)

  public fun <T : TType> squaredDifference(x: Operand<T>, y: Operand<T>): SquaredDifference<T> =
      java.squaredDifference<T>(x, y)

  public fun <T : TType> sub(x: Operand<T>, y: Operand<T>): Sub<T> = java.sub<T>(x, y)

  public fun <T : TType> tan(x: Operand<T>): Tan<T> = java.tan<T>(x)

  public fun <T : TType> tanh(x: Operand<T>): Tanh<T> = java.tanh<T>(x)

  public fun <T : TType> truncateDiv(x: Operand<T>, y: Operand<T>): TruncateDiv<T> =
      java.truncateDiv<T>(x, y)

  public fun <T : TNumber> truncateMod(x: Operand<T>, y: Operand<T>): TruncateMod<T> =
      java.truncateMod<T>(x, y)

  public fun <T : TNumber, U : TNumber, V : TNumber> unsortedSegmentMax(
    `data`: Operand<T>,
    segmentIds: Operand<U>,
    numSegments: Operand<V>
  ): UnsortedSegmentMax<T> = java.unsortedSegmentMax<T, U, V>(data, segmentIds, numSegments)

  public fun <T : TNumber, U : TNumber, V : TNumber> unsortedSegmentMin(
    `data`: Operand<T>,
    segmentIds: Operand<U>,
    numSegments: Operand<V>
  ): UnsortedSegmentMin<T> = java.unsortedSegmentMin<T, U, V>(data, segmentIds, numSegments)

  public fun <T : TType, U : TNumber, V : TNumber> unsortedSegmentProd(
    `data`: Operand<T>,
    segmentIds: Operand<U>,
    numSegments: Operand<V>
  ): UnsortedSegmentProd<T> = java.unsortedSegmentProd<T, U, V>(data, segmentIds, numSegments)

  public fun <T : TType, U : TNumber, V : TNumber> unsortedSegmentSum(
    `data`: Operand<T>,
    segmentIds: Operand<U>,
    numSegments: Operand<V>
  ): UnsortedSegmentSum<T> = java.unsortedSegmentSum<T, U, V>(data, segmentIds, numSegments)

  public fun <T : TType> xdivy(x: Operand<T>, y: Operand<T>): Xdivy<T> = java.xdivy<T>(x, y)

  public fun <T : TType> xlog1py(x: Operand<T>, y: Operand<T>): Xlog1py<T> = java.xlog1py<T>(x, y)

  public fun <T : TType> xlogy(x: Operand<T>, y: Operand<T>): Xlogy<T> = java.xlogy<T>(x, y)

  public fun <T : TNumber> zeta(x: Operand<T>, q: Operand<T>): Zeta<T> = java.zeta<T>(x, q)
}
