/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.ragged;

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * The RaggedFillEmptyRowsGrad operation
 */
@OpMetadata(
    opType = RaggedFillEmptyRowsGrad.OP_NAME,
    inputsClass = RaggedFillEmptyRowsGrad.Inputs.class
)
@Operator(
    group = "ragged"
)
public final class RaggedFillEmptyRowsGrad<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RaggedFillEmptyRowsGrad";

  private Output<T> dValues;

  private Output<T> dDefaultValue;

  public RaggedFillEmptyRowsGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    dValues = operation.output(outputIdx++);
    dDefaultValue = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RaggedFillEmptyRowsGrad operation.
   *
   * @param scope current scope
   * @param reverseIndexMap The reverseIndexMap value
   * @param gradValues The gradValues value
   * @param <T> data type for {@code RaggedFillEmptyRowsGrad} output and operands
   * @return a new instance of RaggedFillEmptyRowsGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> RaggedFillEmptyRowsGrad<T> create(Scope scope,
      Operand<TInt64> reverseIndexMap, Operand<T> gradValues) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RaggedFillEmptyRowsGrad");
    opBuilder.addInput(reverseIndexMap.asOutput());
    opBuilder.addInput(gradValues.asOutput());
    return new RaggedFillEmptyRowsGrad<>(opBuilder.build());
  }

  /**
   * Gets dValues.
   *
   * @return dValues.
   */
  public Output<T> dValues() {
    return dValues;
  }

  /**
   * Gets dDefaultValue.
   *
   * @return dDefaultValue.
   */
  public Output<T> dDefaultValue() {
    return dDefaultValue;
  }

  @OpInputsMetadata(
      outputsClass = RaggedFillEmptyRowsGrad.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<RaggedFillEmptyRowsGrad<T>> {
    /**
     * The reverseIndexMap input
     */
    public final Operand<TInt64> reverseIndexMap;

    /**
     * The gradValues input
     */
    public final Operand<T> gradValues;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RaggedFillEmptyRowsGrad<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      reverseIndexMap = (Operand<TInt64>) op.input(inputIndex++);
      gradValues = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
