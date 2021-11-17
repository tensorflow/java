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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Wraps the XLA ReduceScatter operator
 * documented at https://www.tensorflow.org/xla/operation_semantics#reducescatter.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = ReduceScatter.OP_NAME,
    inputsClass = ReduceScatter.Inputs.class
)
@Operator(
    group = "xla"
)
public final class ReduceScatter<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaReduceScatter";

  private Output<T> output;

  public ReduceScatter(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaReduceScatter operation.
   *
   * @param scope current scope
   * @param input Array or a non-empty tuple of arrays to reduce across replicas.
   * @param groupAssignment Groups between which the reductions are performed.
   * @param scatterDimension Dimension to scatter.
   * @param reduceOp Reduction computation.
   * @param <T> data type for {@code XlaReduceScatter} output and operands
   * @return a new instance of ReduceScatter
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> ReduceScatter<T> create(Scope scope, Operand<T> input,
      Operand<TInt32> groupAssignment, Operand<TInt32> scatterDimension, String reduceOp) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReduceScatter");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(groupAssignment.asOutput());
    opBuilder.addInput(scatterDimension.asOutput());
    opBuilder.setAttr("reduce_op", reduceOp);
    return new ReduceScatter<>(opBuilder.build());
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
      outputsClass = ReduceScatter.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<ReduceScatter<T>> {
    /**
     * Array or a non-empty tuple of arrays to reduce across replicas.
     */
    public final Operand<T> input;

    /**
     * Groups between which the reductions are performed.
     */
    public final Operand<TInt32> groupAssignment;

    /**
     * Dimension to scatter.
     */
    public final Operand<TInt32> scatterDimension;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Reduction computation.
     */
    public final String reduceOp;

    public Inputs(GraphOperation op) {
      super(new ReduceScatter<>(op), op, Arrays.asList("T", "reduce_op"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      groupAssignment = (Operand<TInt32>) op.input(inputIndex++);
      scatterDimension = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      reduceOp = op.attributes().getAttrString("reduce_op");
    }
  }
}
