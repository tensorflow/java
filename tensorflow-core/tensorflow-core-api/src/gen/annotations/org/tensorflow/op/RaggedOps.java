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

import org.tensorflow.Operand;
import org.tensorflow.op.ragged.RaggedBincount;
import org.tensorflow.op.ragged.RaggedFillEmptyRows;
import org.tensorflow.op.ragged.RaggedFillEmptyRowsGrad;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

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
   *  Outputs a vector with length {@code size} and the same dtype as {@code weights}. If
   *  {@code weights} are empty, then index {@code i} stores the number of times the value {@code i} is
   *  counted in {@code arr}. If {@code weights} are non-empty, then index {@code i} stores the sum of
   *  the value in {@code weights} at each index where the corresponding value in {@code arr} is
   *  {@code i}.
   *  <p>Values in {@code arr} outside of the range [0, size) are ignored.
   *
   * @param <U> data type for {@code output} output
   * @param splits 1D int64 {@code Tensor}.
   * @param values 2D int {@code Tensor}.
   * @param sizeOutput non-negative int scalar {@code Tensor}.
   * @param weights is an int32, int64, float32, or float64 {@code Tensor} with the same
   *  shape as {@code input}, or a length-0 {@code Tensor}, in which case it acts as all weights
   *  equal to 1.
   * @param options carries optional attribute values
   * @param <U> data type for {@code RaggedBincount} output and operands
   * @param <T> data type for {@code RaggedBincount} output and operands
   * @return a new instance of RaggedBincount
   */
  public <U extends TNumber, T extends TNumber> RaggedBincount<U> raggedBincount(
      Operand<TInt64> splits, Operand<T> values, Operand<T> sizeOutput, Operand<U> weights,
      RaggedBincount.Options... options) {
    return RaggedBincount.create(scope, splits, values, sizeOutput, weights, options);
  }

  /**
   * The RaggedFillEmptyRows operation
   *
   * @param <T> data type for {@code output_values} output
   * @param valueRowids The valueRowids value
   * @param values The values value
   * @param nrows The nrows value
   * @param defaultValue The defaultValue value
   * @param <T> data type for {@code RaggedFillEmptyRows} output and operands
   * @return a new instance of RaggedFillEmptyRows
   */
  public <T extends TType> RaggedFillEmptyRows<T> raggedFillEmptyRows(Operand<TInt64> valueRowids,
      Operand<T> values, Operand<TInt64> nrows, Operand<T> defaultValue) {
    return RaggedFillEmptyRows.create(scope, valueRowids, values, nrows, defaultValue);
  }

  /**
   * The RaggedFillEmptyRowsGrad operation
   *
   * @param <T> data type for {@code d_values} output
   * @param reverseIndexMap The reverseIndexMap value
   * @param gradValues The gradValues value
   * @param <T> data type for {@code RaggedFillEmptyRowsGrad} output and operands
   * @return a new instance of RaggedFillEmptyRowsGrad
   */
  public <T extends TType> RaggedFillEmptyRowsGrad<T> raggedFillEmptyRowsGrad(
      Operand<TInt64> reverseIndexMap, Operand<T> gradValues) {
    return RaggedFillEmptyRowsGrad.create(scope, reverseIndexMap, gradValues);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
