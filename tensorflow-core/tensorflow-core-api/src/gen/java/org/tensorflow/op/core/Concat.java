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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Concatenates tensors along one dimension.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class Concat<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Concat operation.
   * 
   * @param scope current scope
   * @param values List of `N` Tensors to concatenate. Their ranks and types must match,
   * and their sizes must match in all dimensions except `concat_dim`.
   * @param axis 0-D.  The dimension along which to concatenate.  Must be in the
   * range [-rank(values), rank(values)).
   * @return a new instance of Concat
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> Concat<T> create(Scope scope, Iterable<Operand<T>> values, Operand<U> axis) {
    OperationBuilder opBuilder = scope.env().opBuilder("ConcatV2", scope.makeOpName("Concat"));
    opBuilder.addInputList(Operands.asOutputs(values));
    opBuilder.addInput(axis.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Concat<T>(opBuilder.build());
  }
  
  /**
   * A `Tensor` with the concatenation of values stacked along the
   * `concat_dim` dimension.  This tensor's shape matches that of `values` except
   * in `concat_dim` where it has the sum of the sizes.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ConcatV2";
  
  private Output<T> output;
  
  private Concat(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
