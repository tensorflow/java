// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
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

import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.op.risc.RiscAbs;
import org.tensorflow.op.risc.RiscAdd;
import org.tensorflow.op.risc.RiscBinaryArithmetic;
import org.tensorflow.op.risc.RiscBinaryComparison;
import org.tensorflow.op.risc.RiscBitcast;
import org.tensorflow.op.risc.RiscBroadcast;
import org.tensorflow.op.risc.RiscCast;
import org.tensorflow.op.risc.RiscCeil;
import org.tensorflow.op.risc.RiscCholesky;
import org.tensorflow.op.risc.RiscConcat;
import org.tensorflow.op.risc.RiscCondition;
import org.tensorflow.op.risc.RiscConv;
import org.tensorflow.op.risc.RiscCos;
import org.tensorflow.op.risc.RiscDiv;
import org.tensorflow.op.risc.RiscDot;
import org.tensorflow.op.risc.RiscExp;
import org.tensorflow.op.risc.RiscFft;
import org.tensorflow.op.risc.RiscFloor;
import org.tensorflow.op.risc.RiscGather;
import org.tensorflow.op.risc.RiscImag;
import org.tensorflow.op.risc.RiscIsFinite;
import org.tensorflow.op.risc.RiscLog;
import org.tensorflow.op.risc.RiscLogicalAnd;
import org.tensorflow.op.risc.RiscLogicalNot;
import org.tensorflow.op.risc.RiscLogicalOr;
import org.tensorflow.op.risc.RiscMax;
import org.tensorflow.op.risc.RiscMin;
import org.tensorflow.op.risc.RiscMul;
import org.tensorflow.op.risc.RiscNeg;
import org.tensorflow.op.risc.RiscPad;
import org.tensorflow.op.risc.RiscPool;
import org.tensorflow.op.risc.RiscPow;
import org.tensorflow.op.risc.RiscRandomUniform;
import org.tensorflow.op.risc.RiscReal;
import org.tensorflow.op.risc.RiscReduce;
import org.tensorflow.op.risc.RiscRem;
import org.tensorflow.op.risc.RiscReshape;
import org.tensorflow.op.risc.RiscReverse;
import org.tensorflow.op.risc.RiscScatter;
import org.tensorflow.op.risc.RiscShape;
import org.tensorflow.op.risc.RiscSign;
import org.tensorflow.op.risc.RiscSlice;
import org.tensorflow.op.risc.RiscSort;
import org.tensorflow.op.risc.RiscSqueeze;
import org.tensorflow.op.risc.RiscSub;
import org.tensorflow.op.risc.RiscTranspose;
import org.tensorflow.op.risc.RiscTriangularSolve;
import org.tensorflow.op.risc.RiscUnary;
import org.tensorflow.op.risc.RiscWhile;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code risc} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class RiscOps {
  private final Scope scope;

  private final Ops ops;

  RiscOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * The RiscAbs operation
   *
   * @param <T> data type for {@code y} output
   * @param x The x value
   * @param <T> data type for {@code RiscAbs} output and operands
   * @return a new instance of RiscAbs
   */
  public <T extends TNumber> RiscAbs<T> riscAbs(Operand<T> x) {
    return RiscAbs.create(scope, x);
  }

  /**
   * Returns x + y element-wise.
   *  <em>NOTE</em>: {@code risc.RiscAdd} does not supports broadcasting.
   *  <p>Given two input tensors, the {@code tf.risc_add} operation computes the sum for every element in the tensor.
   *  <p>Both input and output have a range {@code (-inf, inf)}.
   *
   * @param <T> data type for {@code z} output
   * @param x The x value
   * @param y The y value
   * @param <T> data type for {@code RiscAdd} output and operands
   * @return a new instance of RiscAdd
   */
  public <T extends TNumber> RiscAdd<T> riscAdd(Operand<T> x, Operand<T> y) {
    return RiscAdd.create(scope, x, y);
  }

  /**
   * The RiscBinaryArithmetic operation
   *
   * @param <T> data type for {@code z} output
   * @param x The x value
   * @param y The y value
   * @param opType The value of the opType attribute
   * @param <T> data type for {@code RiscBinaryArithmetic} output and operands
   * @return a new instance of RiscBinaryArithmetic
   */
  public <T extends TNumber> RiscBinaryArithmetic<T> riscBinaryArithmetic(Operand<T> x,
      Operand<T> y, String opType) {
    return RiscBinaryArithmetic.create(scope, x, y, opType);
  }

  /**
   * The RiscBinaryComparison operation
   *
   * @param x The x value
   * @param y The y value
   * @param opType The value of the opType attribute
   * @param <T> data type for {@code RiscBinaryComparison} output and operands
   * @return a new instance of RiscBinaryComparison
   */
  public <T extends TNumber> RiscBinaryComparison riscBinaryComparison(Operand<T> x, Operand<T> y,
      String opType) {
    return RiscBinaryComparison.create(scope, x, y, opType);
  }

  /**
   * The RiscBitcast operation
   *
   * @param <U> data type for {@code y} output
   * @param x The x value
   * @param DstT The value of the DstT attribute
   * @param <U> data type for {@code RiscBitcast} output and operands
   * @return a new instance of RiscBitcast
   */
  public <U extends TType> RiscBitcast<U> riscBitcast(Operand<? extends TType> x, Class<U> DstT) {
    return RiscBitcast.create(scope, x, DstT);
  }

  /**
   * The RiscBroadcast operation
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param shape The shape value
   * @param <T> data type for {@code RiscBroadcast} output and operands
   * @return a new instance of RiscBroadcast
   */
  public <T extends TType> RiscBroadcast<T> riscBroadcast(Operand<T> input,
      Operand<? extends TNumber> shape) {
    return RiscBroadcast.create(scope, input, shape);
  }

  /**
   * The RiscCast operation
   *
   * @param <U> data type for {@code y} output
   * @param x The x value
   * @param DstT The value of the DstT attribute
   * @param <U> data type for {@code RiscCast} output and operands
   * @return a new instance of RiscCast
   */
  public <U extends TType> RiscCast<U> riscCast(Operand<? extends TType> x, Class<U> DstT) {
    return RiscCast.create(scope, x, DstT);
  }

  /**
   * The RiscCeil operation
   *
   * @param <T> data type for {@code y} output
   * @param x The x value
   * @param <T> data type for {@code RiscCeil} output and operands
   * @return a new instance of RiscCeil
   */
  public <T extends TNumber> RiscCeil<T> riscCeil(Operand<T> x) {
    return RiscCeil.create(scope, x);
  }

  /**
   * The RiscCholesky operation
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param <T> data type for {@code RiscCholesky} output and operands
   * @return a new instance of RiscCholesky
   */
  public <T extends TNumber> RiscCholesky<T> riscCholesky(Operand<T> input) {
    return RiscCholesky.create(scope, input);
  }

  /**
   * The RiscConcat operation
   *
   * @param <T> data type for {@code output} output
   * @param values The values value
   * @param axis The axis value
   * @param <T> data type for {@code RiscConcat} output and operands
   * @return a new instance of RiscConcat
   */
  public <T extends TType> RiscConcat<T> riscConcat(Iterable<Operand<T>> values,
      Operand<? extends TNumber> axis) {
    return RiscConcat.create(scope, values, axis);
  }

  /**
   * The RiscCondition operation
   *
   * @param <U> data type for {@code output} output
   * @param pred The pred value
   * @param inputTrue The inputTrue value
   * @param inputFalse The inputFalse value
   * @param funcTrue The value of the funcTrue attribute
   * @param funcFalse The value of the funcFalse attribute
   * @param DstT The value of the DstT attribute
   * @param <U> data type for {@code RiscCondition} output and operands
   * @param <T> data type for {@code RiscCondition} output and operands
   * @return a new instance of RiscCondition
   */
  public <U extends TNumber, T extends TNumber> RiscCondition<U> riscCondition(Operand<TBool> pred,
      Operand<T> inputTrue, Operand<T> inputFalse, ConcreteFunction funcTrue,
      ConcreteFunction funcFalse, Class<U> DstT) {
    return RiscCondition.create(scope, pred, inputTrue, inputFalse, funcTrue, funcFalse, DstT);
  }

  /**
   * The RiscConv operation
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param filter The filter value
   * @param strides The value of the strides attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code RiscConv} output and operands
   * @return a new instance of RiscConv
   */
  public <T extends TNumber> RiscConv<T> riscConv(Operand<T> input, Operand<T> filter,
      List<Long> strides, RiscConv.Options... options) {
    return RiscConv.create(scope, input, filter, strides, options);
  }

  /**
   * The RiscCos operation
   *
   * @param <T> data type for {@code y} output
   * @param x The x value
   * @param <T> data type for {@code RiscCos} output and operands
   * @return a new instance of RiscCos
   */
  public <T extends TNumber> RiscCos<T> riscCos(Operand<T> x) {
    return RiscCos.create(scope, x);
  }

  /**
   * The RiscDiv operation
   *
   * @param <T> data type for {@code z} output
   * @param x The x value
   * @param y The y value
   * @param <T> data type for {@code RiscDiv} output and operands
   * @return a new instance of RiscDiv
   */
  public <T extends TNumber> RiscDiv<T> riscDiv(Operand<T> x, Operand<T> y) {
    return RiscDiv.create(scope, x, y);
  }

  /**
   * The RiscDot operation
   *
   * @param <T> data type for {@code product} output
   * @param a The a value
   * @param b The b value
   * @param options carries optional attribute values
   * @param <T> data type for {@code RiscDot} output and operands
   * @return a new instance of RiscDot
   */
  public <T extends TNumber> RiscDot<T> riscDot(Operand<T> a, Operand<T> b,
      RiscDot.Options... options) {
    return RiscDot.create(scope, a, b, options);
  }

  /**
   * The RiscExp operation
   *
   * @param <T> data type for {@code y} output
   * @param x The x value
   * @param <T> data type for {@code RiscExp} output and operands
   * @return a new instance of RiscExp
   */
  public <T extends TNumber> RiscExp<T> riscExp(Operand<T> x) {
    return RiscExp.create(scope, x);
  }

  /**
   * The RiscFft operation
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param <T> data type for {@code RiscFft} output and operands
   * @return a new instance of RiscFft
   */
  public <T extends TType> RiscFft<T> riscFft(Operand<T> input) {
    return RiscFft.create(scope, input);
  }

  /**
   * The RiscFloor operation
   *
   * @param <T> data type for {@code y} output
   * @param x The x value
   * @param <T> data type for {@code RiscFloor} output and operands
   * @return a new instance of RiscFloor
   */
  public <T extends TNumber> RiscFloor<T> riscFloor(Operand<T> x) {
    return RiscFloor.create(scope, x);
  }

  /**
   * The RiscGather operation
   *
   * @param <T> data type for {@code output} output
   * @param params The params value
   * @param indices The indices value
   * @param axis The axis value
   * @param options carries optional attribute values
   * @param <T> data type for {@code RiscGather} output and operands
   * @return a new instance of RiscGather
   */
  public <T extends TType> RiscGather<T> riscGather(Operand<T> params,
      Operand<? extends TNumber> indices, Operand<? extends TNumber> axis,
      RiscGather.Options... options) {
    return RiscGather.create(scope, params, indices, axis, options);
  }

  /**
   * The RiscImag operation
   *
   * @param <U> data type for {@code output} output
   * @param input The input value
   * @return a new instance of RiscImag, with default output types
   */
  public RiscImag<TFloat32> riscImag(Operand<? extends TType> input) {
    return RiscImag.create(scope, input);
  }

  /**
   * The RiscImag operation
   *
   * @param <U> data type for {@code output} output
   * @param input The input value
   * @param Tout The value of the Tout attribute
   * @param <U> data type for {@code RiscImag} output and operands
   * @return a new instance of RiscImag
   */
  public <U extends TNumber> RiscImag<U> riscImag(Operand<? extends TType> input, Class<U> Tout) {
    return RiscImag.create(scope, input, Tout);
  }

  /**
   * The RiscIsFinite operation
   *
   * @param x The x value
   * @return a new instance of RiscIsFinite
   */
  public RiscIsFinite riscIsFinite(Operand<? extends TNumber> x) {
    return RiscIsFinite.create(scope, x);
  }

  /**
   * The RiscLog operation
   *
   * @param <T> data type for {@code y} output
   * @param x The x value
   * @param <T> data type for {@code RiscLog} output and operands
   * @return a new instance of RiscLog
   */
  public <T extends TNumber> RiscLog<T> riscLog(Operand<T> x) {
    return RiscLog.create(scope, x);
  }

  /**
   * The RiscLogicalAnd operation
   *
   * @param x The x value
   * @param y The y value
   * @return a new instance of RiscLogicalAnd
   */
  public RiscLogicalAnd riscLogicalAnd(Operand<TBool> x, Operand<TBool> y) {
    return RiscLogicalAnd.create(scope, x, y);
  }

  /**
   * The RiscLogicalNot operation
   *
   * @param x The x value
   * @return a new instance of RiscLogicalNot
   */
  public RiscLogicalNot riscLogicalNot(Operand<TBool> x) {
    return RiscLogicalNot.create(scope, x);
  }

  /**
   * The RiscLogicalOr operation
   *
   * @param x The x value
   * @param y The y value
   * @return a new instance of RiscLogicalOr
   */
  public RiscLogicalOr riscLogicalOr(Operand<TBool> x, Operand<TBool> y) {
    return RiscLogicalOr.create(scope, x, y);
  }

  /**
   * Returns max(x, y) element-wise.
   *  <em>NOTE</em>: {@code risc.RiscMax} does not supports broadcasting.
   *  <p>Given two input tensors, the {@code tf.risc_max} operation computes the maximum for every element in the tensor.
   *
   * @param <T> data type for {@code max} output
   * @param x The x value
   * @param y The y value
   * @param <T> data type for {@code RiscMax} output and operands
   * @return a new instance of RiscMax
   */
  public <T extends TNumber> RiscMax<T> riscMax(Operand<T> x, Operand<T> y) {
    return RiscMax.create(scope, x, y);
  }

  /**
   * The RiscMin operation
   *
   * @param <T> data type for {@code z} output
   * @param x The x value
   * @param y The y value
   * @param <T> data type for {@code RiscMin} output and operands
   * @return a new instance of RiscMin
   */
  public <T extends TNumber> RiscMin<T> riscMin(Operand<T> x, Operand<T> y) {
    return RiscMin.create(scope, x, y);
  }

  /**
   * The RiscMul operation
   *
   * @param <T> data type for {@code z} output
   * @param x The x value
   * @param y The y value
   * @param <T> data type for {@code RiscMul} output and operands
   * @return a new instance of RiscMul
   */
  public <T extends TNumber> RiscMul<T> riscMul(Operand<T> x, Operand<T> y) {
    return RiscMul.create(scope, x, y);
  }

  /**
   * The RiscNeg operation
   *
   * @param <T> data type for {@code y} output
   * @param x The x value
   * @param <T> data type for {@code RiscNeg} output and operands
   * @return a new instance of RiscNeg
   */
  public <T extends TNumber> RiscNeg<T> riscNeg(Operand<T> x) {
    return RiscNeg.create(scope, x);
  }

  /**
   * The RiscPad operation
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param paddings The paddings value
   * @param constantValues The constantValues value
   * @param <T> data type for {@code RiscPad} output and operands
   * @return a new instance of RiscPad
   */
  public <T extends TNumber> RiscPad<T> riscPad(Operand<T> input,
      Operand<? extends TNumber> paddings, Operand<T> constantValues) {
    return RiscPad.create(scope, input, paddings, constantValues);
  }

  /**
   * The RiscPool operation
   *
   * @param <T> data type for {@code output} output
   * @param value The value value
   * @param ksize The value of the ksize attribute
   * @param strides The value of the strides attribute
   * @param poolingType The value of the poolingType attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code RiscPool} output and operands
   * @return a new instance of RiscPool
   */
  public <T extends TNumber> RiscPool<T> riscPool(Operand<T> value, List<Long> ksize,
      List<Long> strides, String poolingType, RiscPool.Options... options) {
    return RiscPool.create(scope, value, ksize, strides, poolingType, options);
  }

  /**
   * The RiscPow operation
   *
   * @param <T> data type for {@code z} output
   * @param x The x value
   * @param y The y value
   * @param <T> data type for {@code RiscPow} output and operands
   * @return a new instance of RiscPow
   */
  public <T extends TNumber> RiscPow<T> riscPow(Operand<T> x, Operand<T> y) {
    return RiscPow.create(scope, x, y);
  }

  /**
   * The RiscRandomUniform operation
   *
   * @param shape The shape value
   * @param options carries optional attribute values
   * @return a new instance of RiscRandomUniform
   */
  public RiscRandomUniform riscRandomUniform(Operand<? extends TNumber> shape,
      RiscRandomUniform.Options... options) {
    return RiscRandomUniform.create(scope, shape, options);
  }

  /**
   * The RiscReal operation
   *
   * @param <U> data type for {@code output} output
   * @param input The input value
   * @return a new instance of RiscReal, with default output types
   */
  public RiscReal<TFloat32> riscReal(Operand<? extends TType> input) {
    return RiscReal.create(scope, input);
  }

  /**
   * The RiscReal operation
   *
   * @param <U> data type for {@code output} output
   * @param input The input value
   * @param Tout The value of the Tout attribute
   * @param <U> data type for {@code RiscReal} output and operands
   * @return a new instance of RiscReal
   */
  public <U extends TNumber> RiscReal<U> riscReal(Operand<? extends TType> input, Class<U> Tout) {
    return RiscReal.create(scope, input, Tout);
  }

  /**
   * The RiscReduce operation
   *
   * @param <T> data type for {@code output} output
   * @param tensor The tensor value
   * @param axis The axis value
   * @param reduceType The value of the reduceType attribute
   * @param <T> data type for {@code RiscReduce} output and operands
   * @return a new instance of RiscReduce
   */
  public <T extends TNumber> RiscReduce<T> riscReduce(Operand<T> tensor,
      Operand<? extends TNumber> axis, String reduceType) {
    return RiscReduce.create(scope, tensor, axis, reduceType);
  }

  /**
   * The RiscRem operation
   *
   * @param <T> data type for {@code z} output
   * @param x The x value
   * @param y The y value
   * @param <T> data type for {@code RiscRem} output and operands
   * @return a new instance of RiscRem
   */
  public <T extends TNumber> RiscRem<T> riscRem(Operand<T> x, Operand<T> y) {
    return RiscRem.create(scope, x, y);
  }

  /**
   * The RiscReshape operation
   *
   * @param <T> data type for {@code output} output
   * @param tensor The tensor value
   * @param shape The shape value
   * @param <T> data type for {@code RiscReshape} output and operands
   * @return a new instance of RiscReshape
   */
  public <T extends TNumber> RiscReshape<T> riscReshape(Operand<T> tensor,
      Operand<? extends TNumber> shape) {
    return RiscReshape.create(scope, tensor, shape);
  }

  /**
   * The RiscReverse operation
   *
   * @param <T> data type for {@code output} output
   * @param tensor The tensor value
   * @param axis The axis value
   * @param <T> data type for {@code RiscReverse} output and operands
   * @return a new instance of RiscReverse
   */
  public <T extends TNumber> RiscReverse<T> riscReverse(Operand<T> tensor,
      Operand<? extends TNumber> axis) {
    return RiscReverse.create(scope, tensor, axis);
  }

  /**
   * The RiscScatter operation
   *
   * @param <U> data type for {@code output} output
   * @param indices The indices value
   * @param updates The updates value
   * @param shape The shape value
   * @param <U> data type for {@code RiscScatter} output and operands
   * @param <T> data type for {@code RiscScatter} output and operands
   * @return a new instance of RiscScatter
   */
  public <U extends TNumber, T extends TNumber> RiscScatter<U> riscScatter(Operand<T> indices,
      Operand<U> updates, Operand<T> shape) {
    return RiscScatter.create(scope, indices, updates, shape);
  }

  /**
   * The RiscShape operation
   *
   * @param <U> data type for {@code output} output
   * @param input The input value
   * @return a new instance of RiscShape, with default output types
   */
  public RiscShape<TInt32> riscShape(Operand<? extends TNumber> input) {
    return RiscShape.create(scope, input);
  }

  /**
   * The RiscShape operation
   *
   * @param <U> data type for {@code output} output
   * @param input The input value
   * @param outType The value of the outType attribute
   * @param <U> data type for {@code RiscShape} output and operands
   * @return a new instance of RiscShape
   */
  public <U extends TNumber> RiscShape<U> riscShape(Operand<? extends TNumber> input,
      Class<U> outType) {
    return RiscShape.create(scope, input, outType);
  }

  /**
   * The RiscSign operation
   *
   * @param <T> data type for {@code y} output
   * @param x The x value
   * @param <T> data type for {@code RiscSign} output and operands
   * @return a new instance of RiscSign
   */
  public <T extends TNumber> RiscSign<T> riscSign(Operand<T> x) {
    return RiscSign.create(scope, x);
  }

  /**
   * The RiscSlice operation
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param begin The begin value
   * @param sizeOutput The sizeOutput value
   * @param <T> data type for {@code RiscSlice} output and operands
   * @param <U> data type for {@code RiscSlice} output and operands
   * @return a new instance of RiscSlice
   */
  public <T extends TNumber, U extends TNumber> RiscSlice<T> riscSlice(Operand<T> input,
      Operand<U> begin, Operand<U> sizeOutput) {
    return RiscSlice.create(scope, input, begin, sizeOutput);
  }

  /**
   * The RiscSort operation
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param axis The axis value
   * @param direction The value of the direction attribute
   * @param <T> data type for {@code RiscSort} output and operands
   * @return a new instance of RiscSort
   */
  public <T extends TNumber> RiscSort<T> riscSort(Operand<T> input, Operand<? extends TNumber> axis,
      String direction) {
    return RiscSort.create(scope, input, axis, direction);
  }

  /**
   * The RiscSqueeze operation
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param options carries optional attribute values
   * @param <T> data type for {@code RiscSqueeze} output and operands
   * @return a new instance of RiscSqueeze
   */
  public <T extends TType> RiscSqueeze<T> riscSqueeze(Operand<T> input,
      RiscSqueeze.Options... options) {
    return RiscSqueeze.create(scope, input, options);
  }

  /**
   * The RiscSub operation
   *
   * @param <T> data type for {@code z} output
   * @param x The x value
   * @param y The y value
   * @param <T> data type for {@code RiscSub} output and operands
   * @return a new instance of RiscSub
   */
  public <T extends TNumber> RiscSub<T> riscSub(Operand<T> x, Operand<T> y) {
    return RiscSub.create(scope, x, y);
  }

  /**
   * The RiscTranspose operation
   *
   * @param <T> data type for {@code y} output
   * @param x The x value
   * @param perm The perm value
   * @param <T> data type for {@code RiscTranspose} output and operands
   * @return a new instance of RiscTranspose
   */
  public <T extends TType> RiscTranspose<T> riscTranspose(Operand<T> x,
      Operand<? extends TNumber> perm) {
    return RiscTranspose.create(scope, x, perm);
  }

  /**
   * The RiscTriangularSolve operation
   *
   * @param <T> data type for {@code output} output
   * @param matrix The matrix value
   * @param rhs The rhs value
   * @param options carries optional attribute values
   * @param <T> data type for {@code RiscTriangularSolve} output and operands
   * @return a new instance of RiscTriangularSolve
   */
  public <T extends TNumber> RiscTriangularSolve<T> riscTriangularSolve(Operand<T> matrix,
      Operand<T> rhs, RiscTriangularSolve.Options... options) {
    return RiscTriangularSolve.create(scope, matrix, rhs, options);
  }

  /**
   * The RiscUnary operation
   *
   * @param <T> data type for {@code y} output
   * @param x The x value
   * @param opType The value of the opType attribute
   * @param <T> data type for {@code RiscUnary} output and operands
   * @return a new instance of RiscUnary
   */
  public <T extends TNumber> RiscUnary<T> riscUnary(Operand<T> x, String opType) {
    return RiscUnary.create(scope, x, opType);
  }

  /**
   * The RiscWhile operation
   *
   * @param input The input value
   * @param cond The value of the cond attribute
   * @param body The value of the body attribute
   * @param options carries optional attribute values
   * @return a new instance of RiscWhile
   */
  public RiscWhile riscWhile(Iterable<Operand<?>> input, ConcreteFunction cond,
      ConcreteFunction body, RiscWhile.Options... options) {
    return RiscWhile.create(scope, input, cond, body, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
