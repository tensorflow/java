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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Returns locations of nonzero / true values in a tensor.
 * <p>
 * This operation returns the coordinates of true elements in `condition`. The
 * coordinates are returned in a 2-D tensor where the first dimension (rows)
 * represents the number of true elements, and the second dimension (columns)
 * represents the coordinates of the true elements. Keep in mind, the shape of
 * the output tensor can vary depending on how many true values there are in
 * `condition`. Indices are output in row-major order.
 * <p>
 * For example:
 * <pre>{@code
 * # 'input' tensor is [[True, False]
 * #                    [True, False]]
 * # 'input' has two true values, so output has two coordinates.
 * # 'input' has rank of 2, so coordinates have two indices.
 * where(input) ==> [[0, 0],
 *                   [1, 0]]
 * 
 * # `condition` tensor is [[[True, False]
 * #                     [True, False]]
 * #                    [[False, True]
 * #                     [False, True]]
 * #                    [[False, False]
 * #                     [False, True]]]
 * # 'input' has 5 true values, so output has 5 coordinates.
 * # 'input' has rank of 3, so coordinates have three indices.
 * where(input) ==> [[0, 0, 0],
 *                   [0, 1, 0],
 *                   [1, 0, 1],
 *                   [1, 1, 1],
 *                   [2, 1, 1]]
 * 
 * # `condition` tensor is [[[1.5,  0.0]
 * #                     [-0.5, 0.0]]
 * #                    [[0.0,  0.25]
 * #                     [0.0,  0.75]]
 * #                    [[0.0,  0.0]
 * #                     [0.0,  0.01]]]
 * # 'input' has 5 nonzero values, so output has 5 coordinates.
 * # 'input' has rank of 3, so coordinates have three indices.
 * where(input) ==> [[0, 0, 0],
 *                   [0, 1, 0],
 *                   [1, 0, 1],
 *                   [1, 1, 1],
 *                   [2, 1, 1]]
 * 
 * # `condition` tensor is [[[1.5 + 0.0j, 0.0  + 0.0j]
 * #                     [0.0 + 0.5j, 0.0  + 0.0j]]
 * #                    [[0.0 + 0.0j, 0.25 + 1.5j]
 * #                     [0.0 + 0.0j, 0.75 + 0.0j]]
 * #                    [[0.0 + 0.0j, 0.0  + 0.0j]
 * #                     [0.0 + 0.0j, 0.01 + 0.0j]]]
 * # 'input' has 5 nonzero magnitude values, so output has 5 coordinates.
 * # 'input' has rank of 3, so coordinates have three indices.
 * where(input) ==> [[0, 0, 0],
 *                   [0, 1, 0],
 *                   [1, 0, 1],
 *                   [1, 1, 1],
 *                   [2, 1, 1]]
 * }</pre>
 * 
 */
@Operator
public final class Where extends RawOp implements Operand<TInt64> {
  
  /**
   * Factory method to create a class wrapping a new Where operation.
   * 
   * @param scope current scope
   * @param condition 
   * @return a new instance of Where
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Where create(Scope scope, Operand<T> condition) {
    OperationBuilder opBuilder = scope.env().opBuilder("Where", scope.makeOpName("Where"));
    opBuilder.addInput(condition.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Where(opBuilder.build());
  }
  
  /**
   */
  public Output<TInt64> index() {
    return index;
  }
  
  @Override
  public Output<TInt64> asOutput() {
    return index;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Where";
  
  private Output<TInt64> index;
  
  private Where(Operation operation) {
    super(operation);
    int outputIdx = 0;
    index = operation.output(outputIdx++);
  }
}
