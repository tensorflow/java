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
 * @param <T> data type for {@code e()} output
 */
@Operator(group = "linalg")
public final class BatchSelfAdjointEig<T extends TNumber> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.BatchSelfAdjointEig}
   */
  public static class Options {
    
    /**
     * @param computeV 
     */
    public Options computeV(Boolean computeV) {
      this.computeV = computeV;
      return this;
    }
    
    private Boolean computeV;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new BatchSelfAdjointEig operation.
   * 
   * @param scope current scope
   * @param input 
   * @param options carries optional attributes values
   * @return a new instance of BatchSelfAdjointEig
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> BatchSelfAdjointEig<T> create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("BatchSelfAdjointEigV2", scope.makeOpName("BatchSelfAdjointEig"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.computeV != null) {
          opBuilder.setAttr("compute_v", opts.computeV);
        }
      }
    }
    return new BatchSelfAdjointEig<T>(opBuilder.build());
  }
  
  /**
   * @param computeV 
   */
  public static Options computeV(Boolean computeV) {
    return new Options().computeV(computeV);
  }
  
  /**
   */
  public Output<T> e() {
    return e;
  }
  
  /**
   */
  public Output<T> v() {
    return v;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BatchSelfAdjointEigV2";
  
  private Output<T> e;
  private Output<T> v;
  
  private BatchSelfAdjointEig(Operation operation) {
    super(operation);
    int outputIdx = 0;
    e = operation.output(outputIdx++);
    v = operation.output(outputIdx++);
  }
}
