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
import org.tensorflow.op.ragged.RaggedBincount
import org.tensorflow.types.TInt64
import org.tensorflow.types.family.TNumber

/**
 * An API for building {@code ragged} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class RaggedOps(
  /**
   * Get the parent {@link KotlinOps} object.
   */
  public val ops: KotlinOps
) {
  public val java: org.tensorflow.op.RaggedOps = ops.java.ragged

  /**
   * Returns the current {@link Scope scope} of this API
   */
  public val scope: Scope = ops.scope

  public fun <U : TNumber, T : TNumber> raggedBincount(
    splits: Operand<TInt64>,
    values: Operand<T>,
    size: Operand<T>,
    weights: Operand<U>,
    vararg options: RaggedBincount.Options
  ): RaggedBincount<U> = java.raggedBincount<U, T>(splits, values, size, weights, *options)
}
