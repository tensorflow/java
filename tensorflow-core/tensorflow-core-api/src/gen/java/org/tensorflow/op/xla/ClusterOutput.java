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
import org.tensorflow.types.family.TType;

/**
 * Operator that connects the output of an XLA computation to other consumer graph nodes.
 *
 * @param <T> data type for {@code outputs} output
 */
@Operator(
    group = "xla"
)
public final class ClusterOutput<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaClusterOutput";

  private Output<T> outputs;

  private ClusterOutput(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputs = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaClusterOutput operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param <T> data type for {@code XlaClusterOutput} output and operands
   * @return a new instance of ClusterOutput
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ClusterOutput<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("ClusterOutput"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new ClusterOutput<>(opBuilder.build());
  }

  /**
   * Gets outputs.
   *
   * @return outputs.
   */
  public Output<T> outputs() {
    return outputs;
  }

  @Override
  public Output<T> asOutput() {
    return outputs;
  }
}
