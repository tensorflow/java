/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

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

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Computes the difference between two lists of numbers or strings.
 * <p>
 * Given a list `x` and a list `y`, this operation returns a list `out` that
 * represents all values that are in `x` but not in `y`. The returned list `out`
 * is sorted in the same order that the numbers appear in `x` (duplicates are
 * preserved). This operation also returns a list `idx` that represents the
 * position of each `out` element in `x`. In other words:
 * <p>
 * `out[i] = x[idx[i]] for i in [0, 1, ..., len(out) - 1]`
 * <p>
 * For example, given this input:
 * <pre>{@code
 * x = [1, 2, 3, 4, 5, 6]
 * y = [1, 3, 5]
 * }</pre>
 * This operation would return:
 * <pre>{@code
 * out ==> [2, 4, 6]
 * idx ==> [1, 3, 5]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code out()} output
 * @param <U> data type for {@code idx()} output
 */
@Operator
public final class SetDiff1d<T extends TType, U extends TNumber> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SetDiff1d operation.
   * 
   * @param scope current scope
   * @param x 1-D. Values to keep.
   * @param y 1-D. Values to remove.
   * @param outIdx 
   * @return a new instance of SetDiff1d
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> SetDiff1d<T, U> create(Scope scope, Operand<T> x, Operand<T> y, Class<U> outIdx) {
    OperationBuilder opBuilder = scope.env().opBuilder("ListDiff", scope.makeOpName("SetDiff1d"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(y.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_idx", Operands.toDataType(outIdx));
    return new SetDiff1d<T, U>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new SetDiff1d operation using default output types.
   * 
   * @param scope current scope
   * @param x 1-D. Values to keep.
   * @param y 1-D. Values to remove.
   * @return a new instance of SetDiff1d
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> SetDiff1d<T, TInt32> create(Scope scope, Operand<T> x, Operand<T> y) {
    return create(scope, x, y, TInt32.class);
  }
  
  /**
   * 1-D. Values present in `x` but not in `y`.
   */
  public Output<T> out() {
    return out;
  }
  
  /**
   * 1-D. Positions of `x` values preserved in `out`.
   */
  public Output<U> idx() {
    return idx;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ListDiff";
  
  private Output<T> out;
  private Output<U> idx;
  
  private SetDiff1d(Operation operation) {
    super(operation);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
    idx = operation.output(outputIdx++);
  }
}
