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

package org.tensorflow.op.core;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
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
 * Table initializer that takes two tensors for keys and values respectively.
 */
@OpMetadata(
    opType = InitializeTable.OP_NAME,
    inputsClass = InitializeTable.Inputs.class
)
@Operator
public final class InitializeTable extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "InitializeTableV2";

  public InitializeTable(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new InitializeTableV2 operation.
   *
   * @param scope current scope
   * @param tableHandle Handle to a table which will be initialized.
   * @param keys Keys of type Tkey.
   * @param values Values of type Tval.
   * @return a new instance of InitializeTable
   */
  @Endpoint(
      describeByClass = true
  )
  public static InitializeTable create(Scope scope, Operand<? extends TType> tableHandle,
      Operand<? extends TType> keys, Operand<? extends TType> values) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "InitializeTable");
    opBuilder.addInput(tableHandle.asOutput());
    opBuilder.addInput(keys.asOutput());
    opBuilder.addInput(values.asOutput());
    return new InitializeTable(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = InitializeTable.class
  )
  public static class Inputs extends RawOpInputs<InitializeTable> {
    /**
     * Handle to a table which will be initialized.
     */
    public final Operand<? extends TType> tableHandle;

    /**
     * Keys of type Tkey.
     */
    public final Operand<? extends TType> keys;

    /**
     * Values of type Tval.
     */
    public final Operand<? extends TType> values;

    /**
     * The Tkey attribute
     */
    public final DataType Tkey;

    /**
     * The Tval attribute
     */
    public final DataType Tval;

    public Inputs(GraphOperation op) {
      super(new InitializeTable(op), op, Arrays.asList("Tkey", "Tval"));
      int inputIndex = 0;
      tableHandle = (Operand<? extends TType>) op.input(inputIndex++);
      keys = (Operand<? extends TType>) op.input(inputIndex++);
      values = (Operand<? extends TType>) op.input(inputIndex++);
      Tkey = op.attributes().getAttrType("Tkey");
      Tval = op.attributes().getAttrType("Tval");
    }
  }
}
