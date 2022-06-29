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

package org.tensorflow.op.risc;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * The RiscShape operation
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = RiscShape.OP_NAME,
    inputsClass = RiscShape.Inputs.class
)
public final class RiscShape<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscShape";

  private Output<U> output;

  public RiscShape(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscShape operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param outType The value of the outType attribute
   * @param <U> data type for {@code RiscShape} output and operands
   * @return a new instance of RiscShape
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> RiscShape<U> create(Scope scope,
      Operand<? extends TNumber> input, Class<U> outType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscShape");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new RiscShape<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new RiscShape operation, with the default output types.
   *
   * @param scope current scope
   * @param input The input value
   * @return a new instance of RiscShape, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static RiscShape<TInt32> create(Scope scope, Operand<? extends TNumber> input) {
    return create(scope, input, TInt32.class);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = RiscShape.class
  )
  public static class Inputs extends RawOpInputs<RiscShape<?>> {
    /**
     * The input input
     */
    public final Operand<? extends TNumber> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The outType attribute
     */
    public final DataType outType;

    public Inputs(GraphOperation op) {
      super(new RiscShape<>(op), op, Arrays.asList("T", "out_type"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      outType = op.attributes().getAttrType("out_type");
    }
  }
}
