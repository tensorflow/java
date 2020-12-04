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

package org.tensorflow.op.linalg;

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
 * @param <T> data type for {@code band()} output
 */
@Operator(group = "linalg")
public final class BatchMatrixBandPart<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new BatchMatrixBandPart operation.
   * 
   * @param scope current scope
   * @param input 
   * @param numLower 
   * @param numUpper 
   * @return a new instance of BatchMatrixBandPart
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> BatchMatrixBandPart<T> create(Scope scope, Operand<T> input, Operand<TInt64> numLower, Operand<TInt64> numUpper) {
    OperationBuilder opBuilder = scope.env().opBuilder("BatchMatrixBandPart", scope.makeOpName("BatchMatrixBandPart"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(numLower.asOutput());
    opBuilder.addInput(numUpper.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new BatchMatrixBandPart<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> band() {
    return band;
  }
  
  @Override
  public Output<T> asOutput() {
    return band;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BatchMatrixBandPart";
  
  private Output<T> band;
  
  private BatchMatrixBandPart(Operation operation) {
    super(operation);
    int outputIdx = 0;
    band = operation.output(outputIdx++);
  }
}
