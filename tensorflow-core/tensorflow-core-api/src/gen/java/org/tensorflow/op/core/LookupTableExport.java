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
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
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
 * Outputs all keys and values in the table.
 *
 * @param <T> data type for {@code keys} output
 *
 * @param <U> data type for {@code values} output
 */
@OpMetadata(
    opType = LookupTableExport.OP_NAME,
    inputsClass = LookupTableExport.Inputs.class
)
@Operator
public final class LookupTableExport<T extends TType, U extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LookupTableExportV2";

  private Output<T> keys;

  private Output<U> values;

  public LookupTableExport(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    keys = operation.output(outputIdx++);
    values = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LookupTableExportV2 operation.
   *
   * @param scope current scope
   * @param tableHandle Handle to the table.
   * @param Tkeys The value of the Tkeys attribute
   * @param Tvalues The value of the Tvalues attribute
   * @param <T> data type for {@code LookupTableExportV2} output and operands
   * @param <U> data type for {@code LookupTableExportV2} output and operands
   * @return a new instance of LookupTableExport
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TType> LookupTableExport<T, U> create(Scope scope,
      Operand<? extends TType> tableHandle, Class<T> Tkeys, Class<U> Tvalues) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LookupTableExport");
    opBuilder.addInput(tableHandle.asOutput());
    opBuilder.setAttr("Tkeys", Operands.toDataType(Tkeys));
    opBuilder.setAttr("Tvalues", Operands.toDataType(Tvalues));
    return new LookupTableExport<>(opBuilder.build());
  }

  /**
   * Gets keys.
   * Vector of all keys present in the table.
   * @return keys.
   */
  public Output<T> keys() {
    return keys;
  }

  /**
   * Gets values.
   * Tensor of all values in the table. Indexed in parallel with {@code keys}.
   * @return values.
   */
  public Output<U> values() {
    return values;
  }

  @OpInputsMetadata(
      outputsClass = LookupTableExport.class
  )
  public static class Inputs extends RawOpInputs<LookupTableExport<?, ?>> {
    /**
     * Handle to the table.
     */
    public final Operand<? extends TType> tableHandle;

    /**
     * The Tkeys attribute
     */
    public final DataType Tkeys;

    /**
     * The Tvalues attribute
     */
    public final DataType Tvalues;

    public Inputs(GraphOperation op) {
      super(new LookupTableExport<>(op), op, Arrays.asList("Tkeys", "Tvalues"));
      int inputIndex = 0;
      tableHandle = (Operand<? extends TType>) op.input(inputIndex++);
      Tkeys = op.attributes().getAttrType("Tkeys");
      Tvalues = op.attributes().getAttrType("Tvalues");
    }
  }
}
