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

import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.bitwise.BitwiseAnd
import org.tensorflow.op.bitwise.BitwiseOr
import org.tensorflow.op.bitwise.BitwiseXor
import org.tensorflow.op.bitwise.Invert
import org.tensorflow.op.bitwise.LeftShift
import org.tensorflow.op.bitwise.RightShift
import org.tensorflow.types.family.TNumber

/**
 * An API for building {@code bitwise} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class BitwiseOps(
  /**
   * Get the parent {@link KotlinOps} object.
   */
  public val ops: KotlinOps
) {
  public val java: org.tensorflow.op.BitwiseOps = ops.java.bitwise

  /**
   * Returns the current {@link Scope scope} of this API
   */
  public val scope: Scope = ops.scope

  public fun <T : TNumber> bitwiseAnd(x: Operand<T>, y: Operand<T>): BitwiseAnd<T> =
      java.bitwiseAnd<T>(x, y)

  public fun <T : TNumber> bitwiseOr(x: Operand<T>, y: Operand<T>): BitwiseOr<T> =
      java.bitwiseOr<T>(x, y)

  public fun <T : TNumber> bitwiseXor(x: Operand<T>, y: Operand<T>): BitwiseXor<T> =
      java.bitwiseXor<T>(x, y)

  public fun <T : TNumber> invert(x: Operand<T>): Invert<T> = java.invert<T>(x)

  public fun <T : TNumber> leftShift(x: Operand<T>, y: Operand<T>): LeftShift<T> =
      java.leftShift<T>(x, y)

  public fun <T : TNumber> rightShift(x: Operand<T>, y: Operand<T>): RightShift<T> =
      java.rightShift<T>(x, y)
}
