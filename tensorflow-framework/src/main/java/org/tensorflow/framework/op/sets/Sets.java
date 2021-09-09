/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.framework.op.sets;

import org.tensorflow.Operand;
import org.tensorflow.op.Scope;
import org.tensorflow.op.SparseOps;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.sparse.DenseToDenseSetOperation;
import org.tensorflow.op.sparse.SparseToDense;
import org.tensorflow.types.family.TNumber;

public class Sets {

  /**
   * Computes set difference of elements in last dimension of <code>a</code> and <code>b</code> with
   * <code>aMinusB</code> set to true.
   *
   * <p>All but the last dimension of <code>a</code> and <code>b</code> must match
   *
   * @param scope current scope
   * @param a The first operand representing set <code>a</code>
   * @param b The other operand representing set <code>b</code>
   * @param <T>the data type for the sets
   * @return An Operand with the same rank as <code>a</code> and <code>b</code>, and all but the
   *     last dimension the * same. Elements along the last dimension contain the results of the set
   *     operation.
   */
  public static <T extends TNumber> Operand<T> difference(Scope scope, Operand<T> a, Operand<T> b) {
    return difference(scope, a, b, true);
  }

  /**
   * Computes set difference of elements in last dimension of <code>a</code> and <code>b</code>.
   *
   * <p>All but the last dimension of <code>a</code> and <code>b</code> must match
   *
   * @param scope current scope
   * @param a The first operand representing set <code>a</code>
   * @param b The other operand representing set <code>b</code>
   * @param aMinusB whether to subtract b from a, vs vice versa.
   * @param <T>the data type for the sets
   * @return An Operand with the same rank as <code>a</code> and <code>b</code>, and all but the
   *     last dimension the * same. Elements along the last dimension contain the results of the set
   *     operation.
   */
  public static <T extends TNumber> Operand<T> difference(
      Scope scope, Operand<T> a, Operand<T> b, boolean aMinusB) {
    return setOperation(scope, a, b, aMinusB ? Operation.A_MINUS_B : Operation.B_MINUS_A);
  }

  /**
   * Computes set union of elements in last dimension of <code>a</code> and <code>b</code>.
   *
   * @param scope current scope
   * @param a The first operand representing set <code>a</code>
   * @param b The other operand representing set <code>b</code>
   * @param <T>the data type for the sets
   * @return An Operand with the same rank as <code>a</code> and <code>b</code>, and all but the
   *     last dimension the * same. Elements along the last dimension contain the results of the set
   *     operation.
   */
  public static <T extends TNumber> Operand<T> union(Scope scope, Operand<T> a, Operand<T> b) {
    return setOperation(scope, a, b, Operation.UNION);
  }

  /**
   * Computes set intersection of elements in last dimension of <code>a</code> and <code>b</code>.
   *
   * @param scope current scope
   * @param a The first operand representing set <code>a</code>
   * @param b The other operand representing set <code>b</code>
   * @param <T>the data type for the sets
   * @return An Operand with the same rank as <code>a</code> and <code>b</code>, and all but the
   *     last dimension the * same. Elements along the last dimension contain the results of the set
   *     operation.
   */
  public static <T extends TNumber> Operand<T> intersection(
      Scope scope, Operand<T> a, Operand<T> b) {
    return setOperation(scope, a, b, Operation.INTERSECTION);
  }

  /**
   * Compute set operation of elements in last dimension of <code>a</code> and <code>b</code>.
   *
   * @param scope current scope
   * @param a The first set operation operand
   * @param b The other et operation operand
   * @param setOperation The set operation to perform, {@link Operation}.
   * @param <T> the data type for the sets
   * @return An Operand with the same rank as <code>a</code> and <code>b</code>, and all but the
   *     last dimension the same. Elements along the last dimension contain the results of the set
   *     operation.
   */
  public static <T extends TNumber> Operand<T> setOperation(
      Scope scope, Operand<T> a, Operand<T> b, Operation setOperation) {

    DenseToDenseSetOperation<T> setOperationResult =
        DenseToDenseSetOperation.create(
            scope,
            a,
            b,
            setOperation.getSetOperation(),
            DenseToDenseSetOperation.validateIndices(true));

    return SparseToDense.create(
        scope,
        setOperationResult.resultIndices(),
        setOperationResult.resultShape(),
        setOperationResult.resultValues(),
        Cast.create(scope, Constant.scalarOf(scope, 0), a.type()));
  }

  /**
   * Enumeration containing the string operation values to be passed to the TensorFlow Sparse Ops
   * function {@link SparseOps#denseToDenseSetOperation}
   */
  public enum Operation {
    A_MINUS_B("a-b"),
    B_MINUS_A("b-a"),
    INTERSECTION("intersection"),
    UNION("union");

    private final String setOperation;

    Operation(String setOperation) {
      this.setOperation = setOperation;
    }

    /**
     * Gets the set operation String value used to pass as the stringOperation value to {@link
     * SparseOps#denseToDenseSetOperation}
     *
     * @return the set operation String value
     */
    public String getSetOperation() {
      return setOperation;
    }
  }
}
