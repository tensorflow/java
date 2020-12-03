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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "linalg")
public final class BatchMatrixInverse<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.BatchMatrixInverse}
   */
  public static class Options {
    
    /**
     * @param adjoint 
     */
    public Options adjoint(Boolean adjoint) {
      this.adjoint = adjoint;
      return this;
    }
    
    private Boolean adjoint;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new BatchMatrixInverse operation.
   * 
   * @param scope current scope
   * @param input 
   * @param options carries optional attributes values
   * @return a new instance of BatchMatrixInverse
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> BatchMatrixInverse<T> create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("BatchMatrixInverse", scope.makeOpName("BatchMatrixInverse"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.adjoint != null) {
          opBuilder.setAttr("adjoint", opts.adjoint);
        }
      }
    }
    return new BatchMatrixInverse<T>(opBuilder.build());
  }
  
  /**
   * @param adjoint 
   */
  public static Options adjoint(Boolean adjoint) {
    return new Options().adjoint(adjoint);
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
  public static final String OP_NAME = "BatchMatrixInverse";
  
  private Output<T> output;
  
  private BatchMatrixInverse(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
