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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Converts an array of flat indices into a tuple of coordinate arrays.
 * <p>
 * 
 * Example:
 * <pre>{@code
 * y = tf.unravel_index(indices=[2, 5, 7], dims=[3, 3])
 * # 'dims' represent a hypothetical (3, 3) tensor of indices:
 * # [[0, 1, *2*],
 * #  [3, 4, *5*],
 * #  [6, *7*, 8]]
 * # For each entry from 'indices', this operation returns
 * # its coordinates (marked with '*'), such as
 * # 2 ==> (0, 2)
 * # 5 ==> (1, 2)
 * # 7 ==> (2, 1)
 * y ==> [[0, 1, 2], [2, 2, 1]]
 * }</pre>
 * @compatibility(numpy)
 * Equivalent to np.unravel_index
 * @end_compatibility
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class UnravelIndex<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new UnravelIndex operation.
   * 
   * @param scope current scope
   * @param indices An 0-D or 1-D `int` Tensor whose elements are indices into the
   * flattened version of an array of dimensions dims.
   * @param dims An 1-D `int` Tensor. The shape of the array to use for unraveling
   * indices.
   * @return a new instance of UnravelIndex
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> UnravelIndex<T> create(Scope scope, Operand<T> indices, Operand<T> dims) {
    OperationBuilder opBuilder = scope.env().opBuilder("UnravelIndex", scope.makeOpName("UnravelIndex"));
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(dims.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new UnravelIndex<T>(opBuilder.build());
  }
  
  /**
   * An 2-D (or 1-D if indices is 0-D) tensor where each row has the
   * same shape as the indices array.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "UnravelIndex";
  
  private Output<T> output;
  
  private UnravelIndex(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
