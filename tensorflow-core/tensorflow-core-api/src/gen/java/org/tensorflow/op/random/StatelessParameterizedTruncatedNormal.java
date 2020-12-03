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
 * @param <V> data type for {@code output()} output
 */
public final class StatelessParameterizedTruncatedNormal<V extends TNumber> extends RawOp implements Operand<V> {
  
  /**
   * Factory method to create a class wrapping a new StatelessParameterizedTruncatedNormal operation.
   * 
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param means The mean parameter of each batch.
   * @param stddevs The standard deviation parameter of each batch. Must be greater than 0.
   * @param minvals The minimum cutoff. May be -infinity.
   * @param maxvals The maximum cutoff. May be +infinity, and must be more than the minval
   * for each batch.
   * @return a new instance of StatelessParameterizedTruncatedNormal
   */
  @Endpoint(describeByClass = true)
  public static <V extends TNumber, T extends TNumber, U extends TNumber> StatelessParameterizedTruncatedNormal<V> create(Scope scope, Operand<T> shape, Operand<U> seed, Operand<V> means, Operand<V> stddevs, Operand<V> minvals, Operand<V> maxvals) {
    OperationBuilder opBuilder = scope.env().opBuilder("StatelessParameterizedTruncatedNormal", scope.makeOpName("StatelessParameterizedTruncatedNormal"));
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(means.asOutput());
    opBuilder.addInput(stddevs.asOutput());
    opBuilder.addInput(minvals.asOutput());
    opBuilder.addInput(maxvals.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new StatelessParameterizedTruncatedNormal<V>(opBuilder.build());
  }
  
  /**
   * The outputs are truncated normal samples and are a deterministic function of
   * `shape`, `seed`, `minvals`, `maxvals`, `means` and `stddevs`.
   */
  public Output<V> output() {
    return output;
  }
  
  @Override
  public Output<V> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StatelessParameterizedTruncatedNormal";
  
  private Output<V> output;
  
  private StatelessParameterizedTruncatedNormal(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
