/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.op;

import org.tensorflow.Operand;
import org.tensorflow.framework.op.sets.Sets;
import org.tensorflow.op.Scope;
import org.tensorflow.types.family.TNumber;

/** Creates Framework set Operations */
public class SetOps {

  private final Scope scope;

  private final FrameworkOps frameworkOps;

  /**
   * Creates Framework {@code nn} Operations
   *
   * @param frameworkOps the TensorFLow framework Ops
   */
  SetOps(FrameworkOps frameworkOps) {
    this.scope = frameworkOps.scope();
    this.frameworkOps = frameworkOps;
  }

  /**
   * Computes set difference of elements in last dimension of <code>a</code> and <code>b</code> with
   * <code>aMinusB</code> set to true.
   *
   * <p>All but the last dimension of <code>a</code> and <code>b</code> must match
   *
   * @param a The first operand representing set <code>a</code>
   * @param b The other operand representing set <code>b</code>
   * @param <T>the data type for the sets
   * @return An Operand with the same rank as <code>a</code> and <code>b</code>, and all but the
   *     last dimension the * same. Elements along the last dimension contain the results of the set
   *     operation.
   */
  public <T extends TNumber> Operand<T> difference(Operand<T> a, Operand<T> b) {

    return Sets.difference(scope, a, b, true);
  }

  /**
   * Computes set difference of elements in last dimension of <code>a</code> and <code>b</code>.
   *
   * <p>All but the last dimension of <code>a</code> and <code>b</code> must match
   *
   * @param a The first operand representing set <code>a</code>
   * @param b The other operand representing set <code>b</code>
   * @param aMinusB whether to subtract b from a, vs vice versa.
   * @param <T>the data type for the sets
   * @return An Operand with the same rank as <code>a</code> and <code>b</code>, and all but the
   *     last dimension the * same. Elements along the last dimension contain the results of the set
   *     operation.
   */
  public <T extends TNumber> Operand<T> difference(Operand<T> a, Operand<T> b, boolean aMinusB) {
    return Sets.difference(scope, a, b, aMinusB);
  }

  /**
   * Computes set union of elements in last dimension of <code>a</code> and <code>b</code>.
   *
   * @param a The first operand representing set <code>a</code>
   * @param b The other operand representing set <code>b</code>
   * @param <T>the data type for the sets
   * @return An Operand with the same rank as <code>a</code> and <code>b</code>, and all but the
   *     last dimension the * same. Elements along the last dimension contain the results of the set
   *     operation.
   */
  public <T extends TNumber> Operand<T> union(Operand<T> a, Operand<T> b) {
    return Sets.union(scope, a, b);
  }

  /**
   * Computes set intersection of elements in last dimension of <code>a</code> and <code>b</code>.
   *
   * @param a The first operand representing set <code>a</code>
   * @param b The other operand representing set <code>b</code>
   * @param <T>the data type for the sets
   * @return An Operand with the same rank as <code>a</code> and <code>b</code>, and all but the
   *     last dimension the * same. Elements along the last dimension contain the results of the set
   *     operation.
   */
  public <T extends TNumber> Operand<T> intersection(Operand<T> a, Operand<T> b) {
    return Sets.intersection(scope, a, b);
  }
}
