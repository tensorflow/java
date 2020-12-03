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

package org.tensorflow.op.random;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * @param <V> data type for {@code output()} output
 */
@Operator(group = "random")
public final class StatefulRandomBinomial<V extends TNumber> extends RawOp implements Operand<V> {
  
  /**
   * Factory method to create a class wrapping a new StatefulRandomBinomial operation.
   * 
   * @param scope current scope
   * @param resource 
   * @param algorithm 
   * @param shape 
   * @param counts 
   * @param probs 
   * @param dtype 
   * @return a new instance of StatefulRandomBinomial
   */
  @Endpoint(describeByClass = true)
  public static <V extends TNumber, T extends TNumber, U extends TNumber> StatefulRandomBinomial<V> create(Scope scope, Operand<?> resource, Operand<TInt64> algorithm, Operand<T> shape, Operand<U> counts, Operand<U> probs, DataType<V> dtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("StatefulRandomBinomial", scope.makeOpName("StatefulRandomBinomial"));
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(algorithm.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(counts.asOutput());
    opBuilder.addInput(probs.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", dtype);
    return new StatefulRandomBinomial<V>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new StatefulRandomBinomial operation using default output types.
   * 
   * @param scope current scope
   * @param resource 
   * @param algorithm 
   * @param shape 
   * @param counts 
   * @param probs 
   * @return a new instance of StatefulRandomBinomial
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber> StatefulRandomBinomial<TInt64> create(Scope scope, Operand<?> resource, Operand<TInt64> algorithm, Operand<T> shape, Operand<U> counts, Operand<U> probs) {
    return create(scope, resource, algorithm, shape, counts, probs, TInt64.DTYPE);
  }
  
  /**
   */
  public Output<V> output() {
    return output;
  }
  
  @Override
  public Output<V> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StatefulRandomBinomial";
  
  private Output<V> output;
  
  private StatefulRandomBinomial(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
