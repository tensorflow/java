package org.tensorflow.op;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
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
import org.tensorflow.op.math.BesselI0e;
import org.tensorflow.op.math.BesselI1e;
import org.tensorflow.op.math.Betainc;
import org.tensorflow.op.math.Bincount;
import org.tensorflow.op.math.Ceil;
import org.tensorflow.op.math.CheckNumerics;
import org.tensorflow.op.math.CompareAndBitpack;
import org.tensorflow.op.math.ComplexAbs;
import org.tensorflow.op.math.Conj;
import org.tensorflow.op.math.Cos;
import org.tensorflow.op.math.Cosh;
import org.tensorflow.op.math.Cumprod;
import org.tensorflow.op.math.Cumsum;
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
import org.tensorflow.op.math.Xlogy;
import org.tensorflow.op.math.Zeta;
import org.tensorflow.op.math.erfinv;
import org.tensorflow.tools.Shape;
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

  MathOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link MulNoNan} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of MulNoNan
   * @see org.tensorflow.op.math.MulNoNan
   */
  public <T extends TType> MulNoNan<T> mulNoNan(Operand<T> x, Operand<T> y) {
    return MulNoNan.create(scope, x, y);
  }

  /**
   * Builds an {@link Bincount} operation
   *
   * @param arr int32 `Tensor`.
   * @param size non-negative int32 scalar `Tensor`.
   * @param weights is an int32, int64, float32, or float64 `Tensor` with the same
   * @return a new instance of Bincount
   * @see org.tensorflow.op.math.Bincount
   */
  public <T extends TNumber> Bincount<T> bincount(Operand<TInt32> arr, Operand<TInt32> size,
      Operand<T> weights) {
    return Bincount.create(scope, arr, size, weights);
  }

  /**
   * Builds an {@link Polygamma} operation
   *
   * @param a 
   * @param x 
   * @return a new instance of Polygamma
   * @see org.tensorflow.op.math.Polygamma
   */
  public <T extends TNumber> Polygamma<T> polygamma(Operand<T> a, Operand<T> x) {
    return Polygamma.create(scope, a, x);
  }

  /**
   * Builds an {@link BesselI0e} operation
   *
   * @param x 
   * @return a new instance of BesselI0e
   * @see org.tensorflow.op.math.BesselI0e
   */
  public <T extends TNumber> BesselI0e<T> besselI0e(Operand<T> x) {
    return BesselI0e.create(scope, x);
  }

  /**
   * Builds an {@link Reciprocal} operation
   *
   * @param x 
   * @return a new instance of Reciprocal
   * @see org.tensorflow.op.math.Reciprocal
   */
  public <T extends TType> Reciprocal<T> reciprocal(Operand<T> x) {
    return Reciprocal.create(scope, x);
  }

  /**
   * Builds an {@link Rint} operation
   *
   * @param x 
   * @return a new instance of Rint
   * @see org.tensorflow.op.math.Rint
   */
  public <T extends TNumber> Rint<T> rint(Operand<T> x) {
    return Rint.create(scope, x);
  }

  /**
   * Builds an {@link Igamma} operation
   *
   * @param a 
   * @param x 
   * @return a new instance of Igamma
   * @see org.tensorflow.op.math.Igamma
   */
  public <T extends TNumber> Igamma<T> igamma(Operand<T> a, Operand<T> x) {
    return Igamma.create(scope, a, x);
  }

  /**
   * Builds an {@link Pow} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of Pow
   * @see org.tensorflow.op.math.Pow
   */
  public <T extends TType> Pow<T> pow(Operand<T> x, Operand<T> y) {
    return Pow.create(scope, x, y);
  }

  /**
   * Builds an {@link Cosh} operation
   *
   * @param x 
   * @return a new instance of Cosh
   * @see org.tensorflow.op.math.Cosh
   */
  public <T extends TType> Cosh<T> cosh(Operand<T> x) {
    return Cosh.create(scope, x);
  }

  /**
   * Builds an {@link Round} operation
   *
   * @param x 
   * @return a new instance of Round
   * @see org.tensorflow.op.math.Round
   */
  public <T extends TType> Round<T> round(Operand<T> x) {
    return Round.create(scope, x);
  }

  /**
   * Builds an {@link IsInf} operation
   *
   * @param x 
   * @return a new instance of IsInf
   * @see org.tensorflow.op.math.IsInf
   */
  public <T extends TNumber> IsInf isInf(Operand<T> x) {
    return IsInf.create(scope, x);
  }

  /**
   * Builds an {@link LessEqual} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of LessEqual
   * @see org.tensorflow.op.math.LessEqual
   */
  public <T extends TNumber> LessEqual lessEqual(Operand<T> x, Operand<T> y) {
    return LessEqual.create(scope, x, y);
  }

  /**
   * Builds an {@link Real} operation
   *
   * @param input 
   * @return a new instance of Real
   * @see org.tensorflow.op.math.Real
   */
  public <T extends TType> Real<TFloat32> real(Operand<T> input) {
    return Real.create(scope, input);
  }

  /**
   * Builds an {@link BesselI1e} operation
   *
   * @param x 
   * @return a new instance of BesselI1e
   * @see org.tensorflow.op.math.BesselI1e
   */
  public <T extends TNumber> BesselI1e<T> besselI1e(Operand<T> x) {
    return BesselI1e.create(scope, x);
  }

  /**
   * Builds an {@link Atanh} operation
   *
   * @param x 
   * @return a new instance of Atanh
   * @see org.tensorflow.op.math.Atanh
   */
  public <T extends TType> Atanh<T> atanh(Operand<T> x) {
    return Atanh.create(scope, x);
  }

  /**
   * Builds an {@link Ceil} operation
   *
   * @param x 
   * @return a new instance of Ceil
   * @see org.tensorflow.op.math.Ceil
   */
  public <T extends TNumber> Ceil<T> ceil(Operand<T> x) {
    return Ceil.create(scope, x);
  }

  /**
   * Builds an {@link erfinv} operation
   *
   * @param x 
   * @return a new instance of erfinv
   * @see org.tensorflow.op.math.erfinv
   */
  public <T extends TNumber> erfinv<T> erfinv(Operand<T> x) {
    return erfinv.create(scope, x);
  }

  /**
   * Builds an {@link Atan2} operation
   *
   * @param y 
   * @param x 
   * @return a new instance of Atan2
   * @see org.tensorflow.op.math.Atan2
   */
  public <T extends TNumber> Atan2<T> atan2(Operand<T> y, Operand<T> x) {
    return Atan2.create(scope, y, x);
  }

  /**
   * Builds an {@link ArgMin} operation
   *
   * @param input 
   * @param dimension int32 or int64, must be in the range `[-rank(input), rank(input))`.
   * @param outputType 
   * @return a new instance of ArgMin
   * @see org.tensorflow.op.math.ArgMin
   */
  public <V extends TNumber, T extends TType, U extends TNumber> ArgMin<V> argMin(Operand<T> input,
      Operand<U> dimension, DataType<V> outputType) {
    return ArgMin.create(scope, input, dimension, outputType);
  }

  /**
   * Builds an {@link Sign} operation
   *
   * @param x 
   * @return a new instance of Sign
   * @see org.tensorflow.op.math.Sign
   */
  public <T extends TType> Sign<T> sign(Operand<T> x) {
    return Sign.create(scope, x);
  }

  /**
   * Builds an {@link Exp} operation
   *
   * @param x 
   * @return a new instance of Exp
   * @see org.tensorflow.op.math.Exp
   */
  public <T extends TType> Exp<T> exp(Operand<T> x) {
    return Exp.create(scope, x);
  }

  /**
   * Builds an {@link Angle} operation
   *
   * @param input 
   * @param Tout 
   * @return a new instance of Angle
   * @see org.tensorflow.op.math.Angle
   */
  public <U extends TNumber, T extends TType> Angle<U> angle(Operand<T> input, DataType<U> Tout) {
    return Angle.create(scope, input, Tout);
  }

  /**
   * Builds an {@link Add} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of Add
   * @see org.tensorflow.op.math.Add
   */
  public <T extends TType> Add<T> add(Operand<T> x, Operand<T> y) {
    return Add.create(scope, x, y);
  }

  /**
   * Builds an {@link Log} operation
   *
   * @param x 
   * @return a new instance of Log
   * @see org.tensorflow.op.math.Log
   */
  public <T extends TType> Log<T> log(Operand<T> x) {
    return Log.create(scope, x);
  }

  /**
   * Builds an {@link UnsortedSegmentMin} operation
   *
   * @param data 
   * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
   * @param numSegments 
   * @return a new instance of UnsortedSegmentMin
   * @see org.tensorflow.op.math.UnsortedSegmentMin
   */
  public <T extends TNumber, U extends TNumber, V extends TNumber> UnsortedSegmentMin<T> unsortedSegmentMin(
      Operand<T> data, Operand<U> segmentIds, Operand<V> numSegments) {
    return UnsortedSegmentMin.create(scope, data, segmentIds, numSegments);
  }

  /**
   * Builds an {@link SquaredDifference} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of SquaredDifference
   * @see org.tensorflow.op.math.SquaredDifference
   */
  public <T extends TType> SquaredDifference<T> squaredDifference(Operand<T> x, Operand<T> y) {
    return SquaredDifference.create(scope, x, y);
  }

  /**
   * Builds an {@link NotEqual} operation
   *
   * @param x 
   * @param y 
   * @param options carries optional attributes values
   * @return a new instance of NotEqual
   * @see org.tensorflow.op.math.NotEqual
   */
  public <T extends TType> NotEqual notEqual(Operand<T> x, Operand<T> y,
      NotEqual.Options... options) {
    return NotEqual.create(scope, x, y, options);
  }

  /**
   * Builds an {@link Cumprod} operation
   *
   * @param x A `Tensor`. Must be one of the following types: `float32`, `float64`,
   * @param axis A `Tensor` of type `int32` (default: 0). Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of Cumprod
   * @see org.tensorflow.op.math.Cumprod
   */
  public <T extends TType, U extends TNumber> Cumprod<T> cumprod(Operand<T> x, Operand<U> axis,
      Cumprod.Options... options) {
    return Cumprod.create(scope, x, axis, options);
  }

  /**
   * Builds an {@link DivNoNan} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of DivNoNan
   * @see org.tensorflow.op.math.DivNoNan
   */
  public <T extends TType> DivNoNan<T> divNoNan(Operand<T> x, Operand<T> y) {
    return DivNoNan.create(scope, x, y);
  }

  /**
   * Builds an {@link Atan} operation
   *
   * @param x 
   * @return a new instance of Atan
   * @see org.tensorflow.op.math.Atan
   */
  public <T extends TType> Atan<T> atan(Operand<T> x) {
    return Atan.create(scope, x);
  }

  /**
   * Builds an {@link ArgMax} operation
   *
   * @param input 
   * @param dimension int32 or int64, must be in the range `[-rank(input), rank(input))`.
   * @return a new instance of ArgMax
   * @see org.tensorflow.op.math.ArgMax
   */
  public <T extends TType, U extends TNumber> ArgMax<TInt64> argMax(Operand<T> input,
      Operand<U> dimension) {
    return ArgMax.create(scope, input, dimension);
  }

  /**
   * Builds an {@link Maximum} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of Maximum
   * @see org.tensorflow.op.math.Maximum
   */
  public <T extends TNumber> Maximum<T> maximum(Operand<T> x, Operand<T> y) {
    return Maximum.create(scope, x, y);
  }

  /**
   * Builds an {@link ArgMax} operation
   *
   * @param input 
   * @param dimension int32 or int64, must be in the range `[-rank(input), rank(input))`.
   * @param outputType 
   * @return a new instance of ArgMax
   * @see org.tensorflow.op.math.ArgMax
   */
  public <V extends TNumber, T extends TType, U extends TNumber> ArgMax<V> argMax(Operand<T> input,
      Operand<U> dimension, DataType<V> outputType) {
    return ArgMax.create(scope, input, dimension, outputType);
  }

  /**
   * Builds an {@link UnsortedSegmentProd} operation
   *
   * @param data 
   * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
   * @param numSegments 
   * @return a new instance of UnsortedSegmentProd
   * @see org.tensorflow.op.math.UnsortedSegmentProd
   */
  public <T extends TType, U extends TNumber, V extends TNumber> UnsortedSegmentProd<T> unsortedSegmentProd(
      Operand<T> data, Operand<U> segmentIds, Operand<V> numSegments) {
    return UnsortedSegmentProd.create(scope, data, segmentIds, numSegments);
  }

  /**
   * Builds an {@link LogicalNot} operation
   *
   * @param x 
   * @return a new instance of LogicalNot
   * @see org.tensorflow.op.math.LogicalNot
   */
  public LogicalNot logicalNot(Operand<TBool> x) {
    return LogicalNot.create(scope, x);
  }

  /**
   * Builds an {@link Cos} operation
   *
   * @param x 
   * @return a new instance of Cos
   * @see org.tensorflow.op.math.Cos
   */
  public <T extends TType> Cos<T> cos(Operand<T> x) {
    return Cos.create(scope, x);
  }

  /**
   * Builds an {@link Ndtri} operation
   *
   * @param x 
   * @return a new instance of Ndtri
   * @see org.tensorflow.op.math.Ndtri
   */
  public <T extends TNumber> Ndtri<T> ndtri(Operand<T> x) {
    return Ndtri.create(scope, x);
  }

  /**
   * Builds an {@link Imag} operation
   *
   * @param input 
   * @return a new instance of Imag
   * @see org.tensorflow.op.math.Imag
   */
  public <T extends TType> Imag<TFloat32> imag(Operand<T> input) {
    return Imag.create(scope, input);
  }

  /**
   * Builds an {@link Greater} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of Greater
   * @see org.tensorflow.op.math.Greater
   */
  public <T extends TNumber> Greater greater(Operand<T> x, Operand<T> y) {
    return Greater.create(scope, x, y);
  }

  /**
   * Builds an {@link TruncateDiv} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of TruncateDiv
   * @see org.tensorflow.op.math.TruncateDiv
   */
  public <T extends TType> TruncateDiv<T> truncateDiv(Operand<T> x, Operand<T> y) {
    return TruncateDiv.create(scope, x, y);
  }

  /**
   * Builds an {@link CheckNumerics} operation
   *
   * @param tensor 
   * @param message Prefix of the error message.
   * @return a new instance of CheckNumerics
   * @see org.tensorflow.op.math.CheckNumerics
   */
  public <T extends TNumber> CheckNumerics<T> checkNumerics(Operand<T> tensor, String message) {
    return CheckNumerics.create(scope, tensor, message);
  }

  /**
   * Builds an {@link Minimum} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of Minimum
   * @see org.tensorflow.op.math.Minimum
   */
  public <T extends TNumber> Minimum<T> minimum(Operand<T> x, Operand<T> y) {
    return Minimum.create(scope, x, y);
  }

  /**
   * Builds an {@link Div} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of Div
   * @see org.tensorflow.op.math.Div
   */
  public <T extends TType> Div<T> div(Operand<T> x, Operand<T> y) {
    return Div.create(scope, x, y);
  }

  /**
   * Builds an {@link QuantizedAdd} operation
   *
   * @param x 
   * @param y 
   * @param minX The float value that the lowest quantized `x` value represents.
   * @param maxX The float value that the highest quantized `x` value represents.
   * @param minY The float value that the lowest quantized `y` value represents.
   * @param maxY The float value that the highest quantized `y` value represents.
   * @param Toutput 
   * @return a new instance of QuantizedAdd
   * @see org.tensorflow.op.math.QuantizedAdd
   */
  public <V extends TType, T extends TType, U extends TType> QuantizedAdd<V> quantizedAdd(
      Operand<T> x, Operand<U> y, Operand<TFloat32> minX, Operand<TFloat32> maxX,
      Operand<TFloat32> minY, Operand<TFloat32> maxY, DataType<V> Toutput) {
    return QuantizedAdd.create(scope, x, y, minX, maxX, minY, maxY, Toutput);
  }

  /**
   * Builds an {@link Digamma} operation
   *
   * @param x 
   * @return a new instance of Digamma
   * @see org.tensorflow.op.math.Digamma
   */
  public <T extends TNumber> Digamma<T> digamma(Operand<T> x) {
    return Digamma.create(scope, x);
  }

  /**
   * Builds an {@link Mean} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of Mean
   * @see org.tensorflow.op.math.Mean
   */
  public <T extends TType, U extends TNumber> Mean<T> mean(Operand<T> input, Operand<U> axis,
      Mean.Options... options) {
    return Mean.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link Betainc} operation
   *
   * @param a 
   * @param b 
   * @param x 
   * @return a new instance of Betainc
   * @see org.tensorflow.op.math.Betainc
   */
  public <T extends TNumber> Betainc<T> betainc(Operand<T> a, Operand<T> b, Operand<T> x) {
    return Betainc.create(scope, a, b, x);
  }

  /**
   * Builds an {@link Real} operation
   *
   * @param input 
   * @param Tout 
   * @return a new instance of Real
   * @see org.tensorflow.op.math.Real
   */
  public <U extends TNumber, T extends TType> Real<U> real(Operand<T> input, DataType<U> Tout) {
    return Real.create(scope, input, Tout);
  }

  /**
   * Builds an {@link Asin} operation
   *
   * @param x 
   * @return a new instance of Asin
   * @see org.tensorflow.op.math.Asin
   */
  public <T extends TType> Asin<T> asin(Operand<T> x) {
    return Asin.create(scope, x);
  }

  /**
   * Builds an {@link Log1p} operation
   *
   * @param x 
   * @return a new instance of Log1p
   * @see org.tensorflow.op.math.Log1p
   */
  public <T extends TType> Log1p<T> log1p(Operand<T> x) {
    return Log1p.create(scope, x);
  }

  /**
   * Builds an {@link Conj} operation
   *
   * @param input 
   * @return a new instance of Conj
   * @see org.tensorflow.op.math.Conj
   */
  public <T extends TType> Conj<T> conj(Operand<T> input) {
    return Conj.create(scope, input);
  }

  /**
   * Builds an {@link Mul} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of Mul
   * @see org.tensorflow.op.math.Mul
   */
  public <T extends TType> Mul<T> mul(Operand<T> x, Operand<T> y) {
    return Mul.create(scope, x, y);
  }

  /**
   * Builds an {@link Rsqrt} operation
   *
   * @param x 
   * @return a new instance of Rsqrt
   * @see org.tensorflow.op.math.Rsqrt
   */
  public <T extends TType> Rsqrt<T> rsqrt(Operand<T> x) {
    return Rsqrt.create(scope, x);
  }

  /**
   * Builds an {@link Tanh} operation
   *
   * @param x 
   * @return a new instance of Tanh
   * @see org.tensorflow.op.math.Tanh
   */
  public <T extends TType> Tanh<T> tanh(Operand<T> x) {
    return Tanh.create(scope, x);
  }

  /**
   * Builds an {@link Cumsum} operation
   *
   * @param x A `Tensor`. Must be one of the following types: `float32`, `float64`,
   * @param axis A `Tensor` of type `int32` (default: 0). Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of Cumsum
   * @see org.tensorflow.op.math.Cumsum
   */
  public <T extends TType, U extends TNumber> Cumsum<T> cumsum(Operand<T> x, Operand<U> axis,
      Cumsum.Options... options) {
    return Cumsum.create(scope, x, axis, options);
  }

  /**
   * Builds an {@link Xlogy} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of Xlogy
   * @see org.tensorflow.op.math.Xlogy
   */
  public <T extends TType> Xlogy<T> xlogy(Operand<T> x, Operand<T> y) {
    return Xlogy.create(scope, x, y);
  }

  /**
   * Builds an {@link Mod} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of Mod
   * @see org.tensorflow.op.math.Mod
   */
  public <T extends TNumber> Mod<T> mod(Operand<T> x, Operand<T> y) {
    return Mod.create(scope, x, y);
  }

  /**
   * Builds an {@link Expm1} operation
   *
   * @param x 
   * @return a new instance of Expm1
   * @see org.tensorflow.op.math.Expm1
   */
  public <T extends TType> Expm1<T> expm1(Operand<T> x) {
    return Expm1.create(scope, x);
  }

  /**
   * Builds an {@link UnsortedSegmentSum} operation
   *
   * @param data 
   * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
   * @param numSegments 
   * @return a new instance of UnsortedSegmentSum
   * @see org.tensorflow.op.math.UnsortedSegmentSum
   */
  public <T extends TType, U extends TNumber, V extends TNumber> UnsortedSegmentSum<T> unsortedSegmentSum(
      Operand<T> data, Operand<U> segmentIds, Operand<V> numSegments) {
    return UnsortedSegmentSum.create(scope, data, segmentIds, numSegments);
  }

  /**
   * Builds an {@link SegmentMax} operation
   *
   * @param data 
   * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
   * @return a new instance of SegmentMax
   * @see org.tensorflow.op.math.SegmentMax
   */
  public <T extends TNumber, U extends TNumber> SegmentMax<T> segmentMax(Operand<T> data,
      Operand<U> segmentIds) {
    return SegmentMax.create(scope, data, segmentIds);
  }

  /**
   * Builds an {@link Less} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of Less
   * @see org.tensorflow.op.math.Less
   */
  public <T extends TNumber> Less less(Operand<T> x, Operand<T> y) {
    return Less.create(scope, x, y);
  }

  /**
   * Builds an {@link FloorDiv} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of FloorDiv
   * @see org.tensorflow.op.math.FloorDiv
   */
  public <T extends TType> FloorDiv<T> floorDiv(Operand<T> x, Operand<T> y) {
    return FloorDiv.create(scope, x, y);
  }

  /**
   * Builds an {@link LogicalOr} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of LogicalOr
   * @see org.tensorflow.op.math.LogicalOr
   */
  public LogicalOr logicalOr(Operand<TBool> x, Operand<TBool> y) {
    return LogicalOr.create(scope, x, y);
  }

  /**
   * Builds an {@link Neg} operation
   *
   * @param x 
   * @return a new instance of Neg
   * @see org.tensorflow.op.math.Neg
   */
  public <T extends TType> Neg<T> neg(Operand<T> x) {
    return Neg.create(scope, x);
  }

  /**
   * Builds an {@link FloorMod} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of FloorMod
   * @see org.tensorflow.op.math.FloorMod
   */
  public <T extends TNumber> FloorMod<T> floorMod(Operand<T> x, Operand<T> y) {
    return FloorMod.create(scope, x, y);
  }

  /**
   * Builds an {@link Acos} operation
   *
   * @param x 
   * @return a new instance of Acos
   * @see org.tensorflow.op.math.Acos
   */
  public <T extends TType> Acos<T> acos(Operand<T> x) {
    return Acos.create(scope, x);
  }

  /**
   * Builds an {@link NextAfter} operation
   *
   * @param x1 
   * @param x2 
   * @return a new instance of NextAfter
   * @see org.tensorflow.op.math.NextAfter
   */
  public <T extends TNumber> NextAfter<T> nextAfter(Operand<T> x1, Operand<T> x2) {
    return NextAfter.create(scope, x1, x2);
  }

  /**
   * Builds an {@link Igammac} operation
   *
   * @param a 
   * @param x 
   * @return a new instance of Igammac
   * @see org.tensorflow.op.math.Igammac
   */
  public <T extends TNumber> Igammac<T> igammac(Operand<T> a, Operand<T> x) {
    return Igammac.create(scope, a, x);
  }

  /**
   * Builds an {@link ApproximateEqual} operation
   *
   * @param x 
   * @param y 
   * @param options carries optional attributes values
   * @return a new instance of ApproximateEqual
   * @see org.tensorflow.op.math.ApproximateEqual
   */
  public <T extends TType> ApproximateEqual approximateEqual(Operand<T> x, Operand<T> y,
      ApproximateEqual.Options... options) {
    return ApproximateEqual.create(scope, x, y, options);
  }

  /**
   * Builds an {@link SegmentMean} operation
   *
   * @param data 
   * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
   * @return a new instance of SegmentMean
   * @see org.tensorflow.op.math.SegmentMean
   */
  public <T extends TType, U extends TNumber> SegmentMean<T> segmentMean(Operand<T> data,
      Operand<U> segmentIds) {
    return SegmentMean.create(scope, data, segmentIds);
  }

  /**
   * Builds an {@link Lgamma} operation
   *
   * @param x 
   * @return a new instance of Lgamma
   * @see org.tensorflow.op.math.Lgamma
   */
  public <T extends TNumber> Lgamma<T> lgamma(Operand<T> x) {
    return Lgamma.create(scope, x);
  }

  /**
   * Builds an {@link Sqrt} operation
   *
   * @param x 
   * @return a new instance of Sqrt
   * @see org.tensorflow.op.math.Sqrt
   */
  public <T extends TType> Sqrt<T> sqrt(Operand<T> x) {
    return Sqrt.create(scope, x);
  }

  /**
   * Builds an {@link IsNan} operation
   *
   * @param x 
   * @return a new instance of IsNan
   * @see org.tensorflow.op.math.IsNan
   */
  public <T extends TNumber> IsNan isNan(Operand<T> x) {
    return IsNan.create(scope, x);
  }

  /**
   * Builds an {@link Equal} operation
   *
   * @param x 
   * @param y 
   * @param options carries optional attributes values
   * @return a new instance of Equal
   * @see org.tensorflow.op.math.Equal
   */
  public <T extends TType> Equal equal(Operand<T> x, Operand<T> y, Equal.Options... options) {
    return Equal.create(scope, x, y, options);
  }

  /**
   * Builds an {@link Erf} operation
   *
   * @param x 
   * @return a new instance of Erf
   * @see org.tensorflow.op.math.Erf
   */
  public <T extends TNumber> Erf<T> erf(Operand<T> x) {
    return Erf.create(scope, x);
  }

  /**
   * Builds an {@link LogicalAnd} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of LogicalAnd
   * @see org.tensorflow.op.math.LogicalAnd
   */
  public LogicalAnd logicalAnd(Operand<TBool> x, Operand<TBool> y) {
    return LogicalAnd.create(scope, x, y);
  }

  /**
   * Builds an {@link Square} operation
   *
   * @param x 
   * @return a new instance of Square
   * @see org.tensorflow.op.math.Square
   */
  public <T extends TType> Square<T> square(Operand<T> x) {
    return Square.create(scope, x);
  }

  /**
   * Builds an {@link Sub} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of Sub
   * @see org.tensorflow.op.math.Sub
   */
  public <T extends TType> Sub<T> sub(Operand<T> x, Operand<T> y) {
    return Sub.create(scope, x, y);
  }

  /**
   * Builds an {@link AccumulateN} operation
   *
   * @param inputs A list of `Tensor` objects, each with same shape and type.
   * @param shape Shape of elements of `inputs`.
   * @return a new instance of AccumulateN
   * @see org.tensorflow.op.math.AccumulateN
   */
  public <T extends TType> AccumulateN<T> accumulateN(Iterable<Operand<T>> inputs, Shape shape) {
    return AccumulateN.create(scope, inputs, shape);
  }

  /**
   * Builds an {@link UnsortedSegmentMax} operation
   *
   * @param data 
   * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
   * @param numSegments 
   * @return a new instance of UnsortedSegmentMax
   * @see org.tensorflow.op.math.UnsortedSegmentMax
   */
  public <T extends TNumber, U extends TNumber, V extends TNumber> UnsortedSegmentMax<T> unsortedSegmentMax(
      Operand<T> data, Operand<U> segmentIds, Operand<V> numSegments) {
    return UnsortedSegmentMax.create(scope, data, segmentIds, numSegments);
  }

  /**
   * Builds an {@link InvertPermutation} operation
   *
   * @param x 1-D.
   * @return a new instance of InvertPermutation
   * @see org.tensorflow.op.math.InvertPermutation
   */
  public <T extends TNumber> InvertPermutation<T> invertPermutation(Operand<T> x) {
    return InvertPermutation.create(scope, x);
  }

  /**
   * Builds an {@link IsFinite} operation
   *
   * @param x 
   * @return a new instance of IsFinite
   * @see org.tensorflow.op.math.IsFinite
   */
  public <T extends TNumber> IsFinite isFinite(Operand<T> x) {
    return IsFinite.create(scope, x);
  }

  /**
   * Builds an {@link Abs} operation
   *
   * @param x 
   * @return a new instance of Abs
   * @see org.tensorflow.op.math.Abs
   */
  public <T extends TNumber> Abs<T> abs(Operand<T> x) {
    return Abs.create(scope, x);
  }

  /**
   * Builds an {@link TruncateMod} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of TruncateMod
   * @see org.tensorflow.op.math.TruncateMod
   */
  public <T extends TNumber> TruncateMod<T> truncateMod(Operand<T> x, Operand<T> y) {
    return TruncateMod.create(scope, x, y);
  }

  /**
   * Builds an {@link SegmentProd} operation
   *
   * @param data 
   * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
   * @return a new instance of SegmentProd
   * @see org.tensorflow.op.math.SegmentProd
   */
  public <T extends TType, U extends TNumber> SegmentProd<T> segmentProd(Operand<T> data,
      Operand<U> segmentIds) {
    return SegmentProd.create(scope, data, segmentIds);
  }

  /**
   * Builds an {@link Angle} operation
   *
   * @param input 
   * @return a new instance of Angle
   * @see org.tensorflow.op.math.Angle
   */
  public <T extends TType> Angle<TFloat32> angle(Operand<T> input) {
    return Angle.create(scope, input);
  }

  /**
   * Builds an {@link AddN} operation
   *
   * @param inputs 
   * @return a new instance of AddN
   * @see org.tensorflow.op.math.AddN
   */
  public <T extends TType> AddN<T> addN(Iterable<Operand<T>> inputs) {
    return AddN.create(scope, inputs);
  }

  /**
   * Builds an {@link RealDiv} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of RealDiv
   * @see org.tensorflow.op.math.RealDiv
   */
  public <T extends TType> RealDiv<T> realDiv(Operand<T> x, Operand<T> y) {
    return RealDiv.create(scope, x, y);
  }

  /**
   * Builds an {@link Sinh} operation
   *
   * @param x 
   * @return a new instance of Sinh
   * @see org.tensorflow.op.math.Sinh
   */
  public <T extends TType> Sinh<T> sinh(Operand<T> x) {
    return Sinh.create(scope, x);
  }

  /**
   * Builds an {@link ComplexAbs} operation
   *
   * @param x 
   * @return a new instance of ComplexAbs
   * @see org.tensorflow.op.math.ComplexAbs
   */
  public <T extends TType> ComplexAbs<TFloat32> complexAbs(Operand<T> x) {
    return ComplexAbs.create(scope, x);
  }

  /**
   * Builds an {@link Acosh} operation
   *
   * @param x 
   * @return a new instance of Acosh
   * @see org.tensorflow.op.math.Acosh
   */
  public <T extends TType> Acosh<T> acosh(Operand<T> x) {
    return Acosh.create(scope, x);
  }

  /**
   * Builds an {@link Floor} operation
   *
   * @param x 
   * @return a new instance of Floor
   * @see org.tensorflow.op.math.Floor
   */
  public <T extends TNumber> Floor<T> floor(Operand<T> x) {
    return Floor.create(scope, x);
  }

  /**
   * Builds an {@link SegmentMin} operation
   *
   * @param data 
   * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
   * @return a new instance of SegmentMin
   * @see org.tensorflow.op.math.SegmentMin
   */
  public <T extends TNumber, U extends TNumber> SegmentMin<T> segmentMin(Operand<T> data,
      Operand<U> segmentIds) {
    return SegmentMin.create(scope, data, segmentIds);
  }

  /**
   * Builds an {@link PopulationCount} operation
   *
   * @param x 
   * @return a new instance of PopulationCount
   * @see org.tensorflow.op.math.PopulationCount
   */
  public <T extends TNumber> PopulationCount populationCount(Operand<T> x) {
    return PopulationCount.create(scope, x);
  }

  /**
   * Builds an {@link Softplus} operation
   *
   * @param features 
   * @return a new instance of Softplus
   * @see org.tensorflow.op.math.Softplus
   */
  public <T extends TNumber> Softplus<T> softplus(Operand<T> features) {
    return Softplus.create(scope, features);
  }

  /**
   * Builds an {@link Asinh} operation
   *
   * @param x 
   * @return a new instance of Asinh
   * @see org.tensorflow.op.math.Asinh
   */
  public <T extends TType> Asinh<T> asinh(Operand<T> x) {
    return Asinh.create(scope, x);
  }

  /**
   * Builds an {@link Sin} operation
   *
   * @param x 
   * @return a new instance of Sin
   * @see org.tensorflow.op.math.Sin
   */
  public <T extends TType> Sin<T> sin(Operand<T> x) {
    return Sin.create(scope, x);
  }

  /**
   * Builds an {@link QuantizedMul} operation
   *
   * @param x 
   * @param y 
   * @param minX The float value that the lowest quantized `x` value represents.
   * @param maxX The float value that the highest quantized `x` value represents.
   * @param minY The float value that the lowest quantized `y` value represents.
   * @param maxY The float value that the highest quantized `y` value represents.
   * @param Toutput 
   * @return a new instance of QuantizedMul
   * @see org.tensorflow.op.math.QuantizedMul
   */
  public <V extends TType, T extends TType, U extends TType> QuantizedMul<V> quantizedMul(
      Operand<T> x, Operand<U> y, Operand<TFloat32> minX, Operand<TFloat32> maxX,
      Operand<TFloat32> minY, Operand<TFloat32> maxY, DataType<V> Toutput) {
    return QuantizedMul.create(scope, x, y, minX, maxX, minY, maxY, Toutput);
  }

  /**
   * Builds an {@link Erfc} operation
   *
   * @param x 
   * @return a new instance of Erfc
   * @see org.tensorflow.op.math.Erfc
   */
  public <T extends TNumber> Erfc<T> erfc(Operand<T> x) {
    return Erfc.create(scope, x);
  }

  /**
   * Builds an {@link Fact} operation
   *
   * @return a new instance of Fact
   * @see org.tensorflow.op.math.Fact
   */
  public Fact fact() {
    return Fact.create(scope);
  }

  /**
   * Builds an {@link SegmentSum} operation
   *
   * @param data 
   * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
   * @return a new instance of SegmentSum
   * @see org.tensorflow.op.math.SegmentSum
   */
  public <T extends TType, U extends TNumber> SegmentSum<T> segmentSum(Operand<T> data,
      Operand<U> segmentIds) {
    return SegmentSum.create(scope, data, segmentIds);
  }

  /**
   * Builds an {@link Sigmoid} operation
   *
   * @param x 
   * @return a new instance of Sigmoid
   * @see org.tensorflow.op.math.Sigmoid
   */
  public <T extends TType> Sigmoid<T> sigmoid(Operand<T> x) {
    return Sigmoid.create(scope, x);
  }

  /**
   * Builds an {@link Xdivy} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of Xdivy
   * @see org.tensorflow.op.math.Xdivy
   */
  public <T extends TType> Xdivy<T> xdivy(Operand<T> x, Operand<T> y) {
    return Xdivy.create(scope, x, y);
  }

  /**
   * Builds an {@link Tan} operation
   *
   * @param x 
   * @return a new instance of Tan
   * @see org.tensorflow.op.math.Tan
   */
  public <T extends TType> Tan<T> tan(Operand<T> x) {
    return Tan.create(scope, x);
  }

  /**
   * Builds an {@link ArgMin} operation
   *
   * @param input 
   * @param dimension int32 or int64, must be in the range `[-rank(input), rank(input))`.
   * @return a new instance of ArgMin
   * @see org.tensorflow.op.math.ArgMin
   */
  public <T extends TType, U extends TNumber> ArgMin<TInt64> argMin(Operand<T> input,
      Operand<U> dimension) {
    return ArgMin.create(scope, input, dimension);
  }

  /**
   * Builds an {@link CompareAndBitpack} operation
   *
   * @param input Values to compare against `threshold` and bitpack.
   * @param threshold Threshold to compare against.
   * @return a new instance of CompareAndBitpack
   * @see org.tensorflow.op.math.CompareAndBitpack
   */
  public <T extends TType> CompareAndBitpack compareAndBitpack(Operand<T> input,
      Operand<T> threshold) {
    return CompareAndBitpack.create(scope, input, threshold);
  }

  /**
   * Builds an {@link GreaterEqual} operation
   *
   * @param x 
   * @param y 
   * @return a new instance of GreaterEqual
   * @see org.tensorflow.op.math.GreaterEqual
   */
  public <T extends TNumber> GreaterEqual greaterEqual(Operand<T> x, Operand<T> y) {
    return GreaterEqual.create(scope, x, y);
  }

  /**
   * Builds an {@link Zeta} operation
   *
   * @param x 
   * @param q 
   * @return a new instance of Zeta
   * @see org.tensorflow.op.math.Zeta
   */
  public <T extends TNumber> Zeta<T> zeta(Operand<T> x, Operand<T> q) {
    return Zeta.create(scope, x, q);
  }

  /**
   * Builds an {@link ComplexAbs} operation
   *
   * @param x 
   * @param Tout 
   * @return a new instance of ComplexAbs
   * @see org.tensorflow.op.math.ComplexAbs
   */
  public <U extends TNumber, T extends TType> ComplexAbs<U> complexAbs(Operand<T> x,
      DataType<U> Tout) {
    return ComplexAbs.create(scope, x, Tout);
  }

  /**
   * Builds an {@link Imag} operation
   *
   * @param input 
   * @param Tout 
   * @return a new instance of Imag
   * @see org.tensorflow.op.math.Imag
   */
  public <U extends TNumber, T extends TType> Imag<U> imag(Operand<T> input, DataType<U> Tout) {
    return Imag.create(scope, input, Tout);
  }
}
