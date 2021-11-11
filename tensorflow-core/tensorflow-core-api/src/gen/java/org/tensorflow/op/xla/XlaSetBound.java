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
import org.tensorflow.types.TInt32;

/**
 * Set a bound for the given input value as a hint to Xla compiler,
 * <pre>
 *     returns the same value.
 * </pre>
 */
@OpMetadata(
    opType = XlaSetBound.OP_NAME,
    inputsClass = XlaSetBound.Inputs.class
)
@Operator(
    group = "xla"
)
public final class XlaSetBound extends RawOp implements Operand<TInt32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSetBound";

  private Output<TInt32> output;

  public XlaSetBound(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSetBound operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param bound The bound value
   * @return a new instance of XlaSetBound
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaSetBound create(Scope scope, Operand<TInt32> input, Operand<TInt32> bound) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSetBound");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(bound.asOutput());
    return new XlaSetBound(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TInt32> output() {
    return output;
  }

  @Override
  public Output<TInt32> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = XlaSetBound.class
  )
  public static class Inputs extends RawOpInputs<XlaSetBound> {
    /**
     * The input input
     */
    public final Operand<TInt32> input;

    /**
     * The bound input
     */
    public final Operand<TInt32> bound;

    public Inputs(GraphOperation op) {
      super(new XlaSetBound(op), op, Arrays.asList());
      int inputIndex = 0;
      input = (Operand<TInt32>) op.input(inputIndex++);
      bound = (Operand<TInt32>) op.input(inputIndex++);
    }
  }
}
