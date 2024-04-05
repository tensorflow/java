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
import org.tensorflow.op.random.experimental.StatelessShuffle;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code random.experimental} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class RandomExperimentalOps {
  private final Scope scope;

  private final Ops ops;

  RandomExperimentalOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Randomly and deterministically shuffles a tensor along its first dimension.
   *  The tensor is shuffled along dimension 0, such that each {@code value[j]} is mapped
   *  to one and only one {@code output[i]}. For example, a mapping that might occur for a
   *  3x2 tensor is:
   *  <pre>
   *  [[1, 2],       [[5, 6],
   *   [3, 4],  ==&gt;   [1, 2],
   *   [5, 6]]        [3, 4]]
   *  </pre>
   *  <p>The outputs are a deterministic function of {@code value}, {@code key}, {@code counter} and {@code alg}.
   *
   * @param <T> data type for {@code output} output
   * @param value The tensor to be shuffled.
   * @param key Key for the counter-based RNG algorithm (shape uint64[1]).
   * @param counter Initial counter for the counter-based RNG algorithm (shape uint64[2] or uint64[1] depending on the algorithm). If a larger vector is given, only the needed portion on the left (i.e. [:N]) will be used.
   * @param alg The RNG algorithm (shape int32[]).
   * @param <T> data type for {@code StatelessShuffle} output and operands
   * @return a new instance of StatelessShuffle
   */
  public <T extends TType> StatelessShuffle<T> statelessShuffle(Operand<T> value,
      Operand<? extends TType> key, Operand<? extends TType> counter, Operand<TInt32> alg) {
    return StatelessShuffle.create(scope, value, key, counter, alg);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
