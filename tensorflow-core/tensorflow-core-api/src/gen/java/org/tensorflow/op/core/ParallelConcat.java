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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Concatenates a list of `N` tensors along the first dimension.
 * <p>
 * The input tensors are all required to have size 1 in the first dimension.
 * <p>
 * For example:
 * <pre>{@code
 * # 'x' is [[1, 4]]
 * # 'y' is [[2, 5]]
 * # 'z' is [[3, 6]]
 * parallel_concat([x, y, z]) => [[1, 4], [2, 5], [3, 6]]  # Pack along first dim.
 * }</pre>
 * The difference between concat and parallel_concat is that concat requires all
 * of the inputs be computed before the operation will begin but doesn't require
 * that the input shapes be known during graph construction.  Parallel concat
 * will copy pieces of the input into the output as they become available, in
 * some situations this can provide a performance benefit.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class ParallelConcat<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new ParallelConcat operation.
   * 
   * @param scope current scope
   * @param values Tensors to be concatenated. All must have size 1 in the first dimension
   * and same shape.
   * @param shape the final shape of the result; should be equal to the shapes of any input
   * but with the number of input values in the first dimension.
   * @return a new instance of ParallelConcat
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> ParallelConcat<T> create(Scope scope, Iterable<Operand<T>> values, Shape shape) {
    OperationBuilder opBuilder = scope.env().opBuilder("ParallelConcat", scope.makeOpName("ParallelConcat"));
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("shape", shape);
    return new ParallelConcat<T>(opBuilder.build());
  }
  
  /**
   * The concatenated tensor.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ParallelConcat";
  
  private Output<T> output;
  
  private ParallelConcat(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
