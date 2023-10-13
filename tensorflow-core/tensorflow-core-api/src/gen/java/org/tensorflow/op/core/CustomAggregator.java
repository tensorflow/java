/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;

/**
 * The CustomAggregator operation
 */
@OpMetadata(
    opType = CustomAggregator.OP_NAME,
    inputsClass = CustomAggregator.Inputs.class
)
@Operator
public final class CustomAggregator extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CustomAggregator";

  private Output<TFloat32> output;

  public CustomAggregator(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CustomAggregator operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param id The value of the id attribute
   * @return a new instance of CustomAggregator
   */
  @Endpoint(
      describeByClass = true
  )
  public static CustomAggregator create(Scope scope, Operand<TFloat32> input, String id) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CustomAggregator");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("id", id);
    return new CustomAggregator(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TFloat32> output() {
    return output;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = CustomAggregator.class
  )
  public static class Inputs extends RawOpInputs<CustomAggregator> {
    /**
     * The input input
     */
    public final Operand<TFloat32> input;

    /**
     * The id attribute
     */
    public final String id;

    public Inputs(GraphOperation op) {
      super(new CustomAggregator(op), op, Arrays.asList("id"));
      int inputIndex = 0;
      input = (Operand<TFloat32>) op.input(inputIndex++);
      id = op.attributes().getAttrString("id");
    }
  }
}
