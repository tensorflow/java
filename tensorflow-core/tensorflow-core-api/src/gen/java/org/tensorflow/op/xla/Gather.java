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
 * Wraps the XLA Gather operator documented at
 * <p>
 *   https://www.tensorflow.org/xla/operation_semantics#gather
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "xla")
public final class Gather<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Gather operation.
   * 
   * @param scope current scope
   * @param operand The array we're gathering from.
   * @param startIndices Array containing the starting indices of the slices we gather.
   * @param sliceSizes slice_sizes[i] is the bounds for the slice on dimension i.
   * @param dimensionNumbers A serialized xla::GatherDimensionNumbers proto.
   * @param indicesAreSorted Boolean indicating if the indices are sorted.
   * @return a new instance of Gather
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> Gather<T> create(Scope scope, Operand<T> operand, Operand<U> startIndices, Operand<U> sliceSizes, String dimensionNumbers, Boolean indicesAreSorted) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaGather", scope.makeOpName("Gather"));
    opBuilder.addInput(operand.asOutput());
    opBuilder.addInput(startIndices.asOutput());
    opBuilder.addInput(sliceSizes.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dimension_numbers", dimensionNumbers);
    opBuilder.setAttr("indices_are_sorted", indicesAreSorted);
    return new Gather<T>(opBuilder.build());
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
  public static final String OP_NAME = "XlaGather";
  
  private Output<T> output;
  
  private Gather(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
