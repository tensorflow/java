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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * The DTensorAllScatter operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = DTensorAllScatter.OP_NAME,
    inputsClass = DTensorAllScatter.Inputs.class
)
@Operator
public final class DTensorAllScatter<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DTensorAllScatter";

  private Output<T> output;

  public DTensorAllScatter(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DTensorAllScatter operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param inputLayout The value of the inputLayout attribute
   * @param outputLayout The value of the outputLayout attribute
   * @param <T> data type for {@code DTensorAllScatter} output and operands
   * @return a new instance of DTensorAllScatter
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> DTensorAllScatter<T> create(Scope scope, Operand<T> input,
      String inputLayout, String outputLayout) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DTensorAllScatter");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("input_layout", inputLayout);
    opBuilder.setAttr("output_layout", outputLayout);
    return new DTensorAllScatter<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = DTensorAllScatter.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<DTensorAllScatter<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The inputLayout attribute
     */
    public final String inputLayout;

    /**
     * The outputLayout attribute
     */
    public final String outputLayout;

    public Inputs(GraphOperation op) {
      super(new DTensorAllScatter<>(op), op, Arrays.asList("T", "input_layout", "output_layout"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      inputLayout = op.attributes().getAttrString("input_layout");
      outputLayout = op.attributes().getAttrString("output_layout");
    }
  }
}
