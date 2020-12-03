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

package org.tensorflow.op.tpu;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * An Op to permute tensors across replicated TPU instances.
 * <p>
 * Each instance supplies its own input.
 * <p>
 * For example, suppose there are 4 TPU instances: `[A, B, C, D]`. Passing
 * source_target_pairs=`[[0,1],[1,2],[2,3],[3,0]]` gets the outputs:
 * `[D, A, B, C]`.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class CollectivePermute<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new CollectivePermute operation.
   * 
   * @param scope current scope
   * @param input The local input to be permuted. Currently only supports float and
   * bfloat16.
   * @param sourceTargetPairs A tensor with shape [num_pairs, 2].
   * @return a new instance of CollectivePermute
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> CollectivePermute<T> create(Scope scope, Operand<T> input, Operand<TInt32> sourceTargetPairs) {
    OperationBuilder opBuilder = scope.env().opBuilder("CollectivePermute", scope.makeOpName("CollectivePermute"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(sourceTargetPairs.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new CollectivePermute<T>(opBuilder.build());
  }
  
  /**
   * The permuted input.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "CollectivePermute";
  
  private Output<T> output;
  
  private CollectivePermute(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
