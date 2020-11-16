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
import org.tensorflow.op.ragged.RaggedBincount;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * An API for building {@code ragged} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class RaggedOps {
  private final Scope scope;

  private final Ops ops;

  RaggedOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
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
   * @param splits 1D int64 `Tensor`.
   * @param values 2D int `Tensor`.
   * @param size non-negative int scalar `Tensor`.
   * @param weights is an int32, int64, float32, or float64 `Tensor` with the same
   *  shape as `input`, or a length-0 `Tensor`, in which case it acts as all weights
   *  equal to 1.
   * @param options carries optional attributes values
   * @return a new instance of RaggedBincount
   */
  public <U extends TNumber, T extends TNumber> RaggedBincount<U> raggedBincount(
      Operand<TInt64> splits, Operand<T> values, Operand<T> size, Operand<U> weights,
      RaggedBincount.Options... options) {
    return RaggedBincount.create(scope, splits, values, size, weights, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
