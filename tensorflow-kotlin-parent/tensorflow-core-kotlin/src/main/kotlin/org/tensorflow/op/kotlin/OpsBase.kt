/*
 Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================
*/
package org.tensorflow.op.kotlin

import org.tensorflow.Operand
import org.tensorflow.ndarray.Shape
import org.tensorflow.ndarray.index.Index
import org.tensorflow.op.core.Constant
import org.tensorflow.op.core.StopGradient
import org.tensorflow.op.core.StridedSlice
import org.tensorflow.op.dtypes.Cast
import org.tensorflow.op.linalg.MatMul
import org.tensorflow.op.math.Add
import org.tensorflow.op.math.Div
import org.tensorflow.op.math.Equal
import org.tensorflow.op.math.Greater
import org.tensorflow.op.math.GreaterEqual
import org.tensorflow.op.math.Less
import org.tensorflow.op.math.LessEqual
import org.tensorflow.op.math.LogicalAnd
import org.tensorflow.op.math.LogicalNot
import org.tensorflow.op.math.LogicalOr
import org.tensorflow.op.math.Mod
import org.tensorflow.op.math.Mul
import org.tensorflow.op.math.Neg
import org.tensorflow.op.math.NotEqual
import org.tensorflow.op.math.Pow
import org.tensorflow.op.math.Sub
import org.tensorflow.types.TBool
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TFloat64
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.TUint8
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * Interface extended by [KotlinOps], used for now to declare extensions on Operand
 *
 * FIXME: Should be replaced by multiple receivers when available
 */
public abstract class OpsBase {
  public abstract val tf: KotlinOps

  /** @see LinalgOps.matMul */
  public fun <T : TType> Operand<T>.matMul(
      b: Operand<T>,
      transposeA: Boolean? = null,
      transposeB: Boolean? = null,
  ): MatMul<T> = tf.linalg.matMul(this, b, transposeA, transposeB)

  /** @see LinalgOps.matMul */
  public infix fun <T : TType> Operand<T>.matMul(b: Operand<T>): MatMul<T> =
      matMul(b, transposeB = null)

  /** @see MathOps.add */
  public operator fun <T : TType> Operand<T>.plus(b: Operand<T>): Add<T> = tf.math.add(this, b)

  /** @see MathOps.sub */
  public operator fun <T : TType> Operand<T>.minus(b: Operand<T>): Sub<T> = tf.math.sub(this, b)

  /** @see MathOps.mul */
  public operator fun <T : TType> Operand<T>.times(b: Operand<T>): Mul<T> = tf.math.mul(this, b)

  /** @see MathOps.div */
  public operator fun <T : TType> Operand<T>.div(b: Operand<T>): Div<T> = tf.math.div(this, b)

  /** @see MathOps.mod */
  public operator fun <T : TNumber> Operand<T>.rem(b: Operand<T>): Mod<T> = tf.math.mod(this, b)

  /** @see MathOps.pow */
  public infix fun <T : TType> Operand<T>.pow(b: Operand<T>): Pow<T> = tf.math.pow(this, b)

  /** @see MathOps.add */
  public operator fun <T : TNumber> Operand<T>.plus(scalar: Number): Add<T> =
      this + tf.constantOfSameType(this, scalar)

  /** @see MathOps.sub */
  public operator fun <T : TNumber> Operand<T>.minus(scalar: Number): Sub<T> =
      this - tf.constantOfSameType(this, scalar)

  /** @see MathOps.mul */
  public operator fun <T : TNumber> Operand<T>.times(scalar: Number): Mul<T> =
      this * tf.constantOfSameType(this, scalar)

  /** @see MathOps.div */
  public operator fun <T : TNumber> Operand<T>.div(scalar: Number): Div<T> =
      this / tf.constantOfSameType(this, scalar)

  /** @see MathOps.mod */
  public operator fun <T : TNumber> Operand<T>.rem(scalar: Number): Mod<T> =
      this % tf.constantOfSameType(this, scalar)

  /** @see MathOps.pow */
  public infix fun <T : TNumber> Operand<T>.pow(scalar: Number): Pow<T> =
      this pow tf.constantOfSameType(this, scalar)

  /** @see MathOps.neg */
  public operator fun <T : TType> Operand<T>.unaryMinus(): Neg<T> = tf.math.neg(this)

  /** @see MathOps.logicalNot */
  public operator fun Operand<TBool>.not(): LogicalNot = tf.math.logicalNot(this)

  /** @see MathOps.logicalAnd */
  public infix fun Operand<TBool>.and(b: Operand<TBool>): LogicalAnd = tf.math.logicalAnd(this, b)

  /** @see MathOps.logicalOr */
  public infix fun Operand<TBool>.or(b: Operand<TBool>): LogicalOr = tf.math.logicalOr(this, b)

  /** @see MathOps.equal */
  public infix fun <T : TType> Operand<T>.eq(b: Operand<T>): Equal = tf.math.equal(this, b)

  /** @see MathOps.notEqual */
  public infix fun <T : TType> Operand<T>.neq(b: Operand<T>): NotEqual = tf.math.notEqual(this, b)

  /** @see MathOps.less */
  public infix fun <T : TNumber> Operand<T>.lt(b: Operand<T>): Less = tf.math.less(this, b)

  /** @see MathOps.greater */
  public infix fun <T : TNumber> Operand<T>.gt(b: Operand<T>): Greater = tf.math.greater(this, b)

  /** @see MathOps.lessEqual */
  public infix fun <T : TNumber> Operand<T>.lte(b: Operand<T>): LessEqual =
      tf.math.lessEqual(this, b)

  /** @see MathOps.greaterEqual */
  public infix fun <T : TNumber> Operand<T>.gte(b: Operand<T>): GreaterEqual =
      tf.math.greaterEqual(this, b)

  /** @see KotlinOps.stopGradient */
  @JvmName("stopGradientExtension")
  public fun <T : TType> Operand<T>.stopGradient(): StopGradient<T> = tf.stopGradient(this)

  /** @see DtypesOps.cast */
  public inline fun <reified T : TType> Operand<*>.cast(truncate: Boolean? = null): Cast<T> =
      tf.dtypes.cast<T>(this, truncate)

  /** @see KotlinOps.constant */
  public fun Int.asConstant(): Constant<TInt32> = tf.constant(this)

  /** @see KotlinOps.constant */
  public fun Long.asConstant(): Constant<TInt64> = tf.constant(this)

  /** @see KotlinOps.constant */
  public fun Float.asConstant(): Constant<TFloat32> = tf.constant(this)

  /** @see KotlinOps.constant */
  public fun Double.asConstant(): Constant<TFloat64> = tf.constant(this)

  /** @see KotlinOps.constant */
  public fun Byte.asConstant(): Constant<TUint8> = tf.constant(this)

  /** @see KotlinOps.constant */
  public fun Boolean.asConstant(): Constant<TBool> = tf.constant(this)

  /** @see KotlinOps.constant */
  public fun IntArray.asConstant(): Constant<TInt32> = tf.constant(this)

  /** @see KotlinOps.constant */
  public fun LongArray.asConstant(): Constant<TInt64> = tf.constant(this)

  /** @see KotlinOps.constant */
  public fun FloatArray.asConstant(): Constant<TFloat32> = tf.constant(this)

  /** @see KotlinOps.constant */
  public fun DoubleArray.asConstant(): Constant<TFloat64> = tf.constant(this)

  /** @see KotlinOps.constant */
  public fun ByteArray.asConstant(): Constant<TUint8> = tf.constant(this)

  /** @see KotlinOps.constant */
  public fun BooleanArray.asConstant(): Constant<TBool> = tf.constant(this)

  /** @see KotlinOps.constant */
  public fun Shape.asConstant(): Constant<TInt64> = tf.constant(this)

  /**
   * Creates a 1D constant from [array].
   *
   * @see KotlinOps.constant
   */
  @JvmName("intsAsConstant")
  public fun Collection<Int>.asConstant(): Constant<TInt32> = tf.constant(this)

  /**
   * Creates a 1D constant from [array].
   *
   * @see KotlinOps.constant
   */
  @JvmName("longsAsConstant")
  public fun Collection<Long>.asConstant(): Constant<TInt64> = tf.constant(this)

  /**
   * Creates a 1D constant from [array].
   *
   * @see KotlinOps.constant
   */
  @JvmName("floatsAsConstant")
  public fun Collection<Float>.asConstant(): Constant<TFloat32> = tf.constant(this)

  /**
   * Creates a 1D constant from [array].
   *
   * @see KotlinOps.constant
   */
  @JvmName("doublesAsConstant")
  public fun Collection<Double>.asConstant(): Constant<TFloat64> = tf.constant(this)

  /**
   * Creates a 1D constant from [array].
   *
   * @see KotlinOps.constant
   */
  @JvmName("bytesAsConstant")
  public fun Collection<Byte>.asConstant(): Constant<TUint8> = tf.constant(this)

  /**
   * Creates a 1D constant from [array].
   *
   * @see KotlinOps.constant
   */
  @JvmName("booleansAsConstant")
  public fun Collection<Boolean>.asConstant(): Constant<TBool> = tf.constant(this)

  // TODO look at syntax `W[1][3..4]()`
  /** @see KotlinOps.stridedSlice */
  public operator fun <T : TType> Operand<T>.get(vararg indices: Index): StridedSlice<T> =
      tf.stridedSlice(this, *indices)
}
