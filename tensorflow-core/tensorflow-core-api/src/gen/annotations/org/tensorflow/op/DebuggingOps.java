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
import org.tensorflow.op.debugging.CheckNumerics;
import org.tensorflow.types.family.TNumber;

/**
 * An API for building {@code debugging} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class DebuggingOps {
  private final Scope scope;

  DebuggingOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Checks a tensor for NaN and Inf values.
   *  <p>
   *  When run, reports an `InvalidArgument` error if `tensor` has any values
   *  that are not a number (NaN) or infinity (Inf). Otherwise, passes `tensor` as-is.
   *
   * @param <T> data type for {@code output()} output
   * @param tensor
   * @param message Prefix of the error message.
   * @return a new instance of CheckNumerics
   */
  public <T extends TNumber> CheckNumerics<T> checkNumerics(Operand<T> tensor, String message) {
    return CheckNumerics.create(scope, tensor, message);
  }
}
