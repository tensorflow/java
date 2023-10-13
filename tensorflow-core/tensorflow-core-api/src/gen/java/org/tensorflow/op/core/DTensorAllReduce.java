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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * The DTensorAllReduce operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = DTensorAllReduce.OP_NAME,
    inputsClass = DTensorAllReduce.Inputs.class
)
@Operator
public final class DTensorAllReduce<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DTensorAllReduce";

  private Output<T> output;

  public DTensorAllReduce(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DTensorAllReduce operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param groupAssignment The groupAssignment value
   * @param reduceOp The value of the reduceOp attribute
   * @param deviceType The value of the deviceType attribute
   * @param <T> data type for {@code DTensorAllReduce} output and operands
   * @return a new instance of DTensorAllReduce
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> DTensorAllReduce<T> create(Scope scope, Operand<T> input,
      Operand<TInt32> groupAssignment, String reduceOp, String deviceType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DTensorAllReduce");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(groupAssignment.asOutput());
    opBuilder.setAttr("reduce_op", reduceOp);
    opBuilder.setAttr("device_type", deviceType);
    return new DTensorAllReduce<>(opBuilder.build());
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
      outputsClass = DTensorAllReduce.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<DTensorAllReduce<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The groupAssignment input
     */
    public final Operand<TInt32> groupAssignment;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The reduceOp attribute
     */
    public final String reduceOp;

    /**
     * The deviceType attribute
     */
    public final String deviceType;

    public Inputs(GraphOperation op) {
      super(new DTensorAllReduce<>(op), op, Arrays.asList("T", "reduce_op", "device_type"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      groupAssignment = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      reduceOp = op.attributes().getAttrString("reduce_op");
      deviceType = op.attributes().getAttrString("device_type");
    }
  }
}
