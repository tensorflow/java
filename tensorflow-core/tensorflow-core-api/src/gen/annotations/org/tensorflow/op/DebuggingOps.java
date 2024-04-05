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
import org.tensorflow.op.debugging.CheckNumerics;
import org.tensorflow.types.family.TNumber;

/**
 * An API for building {@code debugging} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class DebuggingOps {
  private final Scope scope;

  private final Ops ops;

  DebuggingOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Checks a tensor for NaN, -Inf and +Inf values.
   *  When run, reports an {@code InvalidArgument} error if {@code tensor} has any values
   *  that are not a number (NaN) or infinity (Inf). Otherwise, returns the input
   *  tensor. Unlike CheckNumerics (V1), CheckNumericsV2 distinguishes -Inf and +Inf
   *  in the errors it throws.
   *
   * @param <T> data type for {@code output} output
   * @param tensor The tensor value
   * @param message Prefix of the error message.
   * @param <T> data type for {@code CheckNumericsV2} output and operands
   * @return a new instance of CheckNumerics
   */
  public <T extends TNumber> CheckNumerics<T> checkNumerics(Operand<T> tensor, String message) {
    return CheckNumerics.create(scope, tensor, message);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
