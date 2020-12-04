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

package org.tensorflow.op.xla;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Wraps the XLA DynamicSlice operator, documented at
 * <p>
 *  https://www.tensorflow.org/performance/xla/operation_semantics#dynamicslice
 * .
 * <p>
 * DynamicSlice extracts a sub-array from the input array at dynamic
 * start_indices. The size of the slice in each dimension is passed in
 * size_indices, which specify the end point of exclusive slice intervals in each
 * dimension -- [start, start + size). The shape of start_indices must have rank 1,
 * with dimension size equal to the rank of operand.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "xla")
public final class DynamicSlice<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new DynamicSlice operation.
   * 
   * @param scope current scope
   * @param input A `Tensor` of type T.
   * @param startIndices List of N integers containing the slice size for each
   * dimension. Each value must be strictly greater than zero, and start + size
   * must be less than or equal to the size of the dimension to avoid
   * implementation defined behavior.
   * @param sizeIndices 
   * @return a new instance of DynamicSlice
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> DynamicSlice<T> create(Scope scope, Operand<T> input, Operand<U> startIndices, Operand<U> sizeIndices) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaDynamicSlice", scope.makeOpName("DynamicSlice"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(startIndices.asOutput());
    opBuilder.addInput(sizeIndices.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new DynamicSlice<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "XlaDynamicSlice";
  
  private Output<T> output;
  
  private DynamicSlice(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
