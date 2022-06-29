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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Read an element from the TensorArray into output {@code value}.
 *
 * @param <T> data type for {@code value} output
 */
@OpMetadata(
    opType = TensorArrayRead.OP_NAME,
    inputsClass = TensorArrayRead.Inputs.class
)
@Operator
public final class TensorArrayRead<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorArrayReadV3";

  private Output<T> value;

  public TensorArrayRead(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    value = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorArrayReadV3 operation.
   *
   * @param scope current scope
   * @param handle The handle to a TensorArray.
   * @param index The index value
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param dtype The type of the elem that is returned.
   * @param <T> data type for {@code TensorArrayReadV3} output and operands
   * @return a new instance of TensorArrayRead
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TensorArrayRead<T> create(Scope scope,
      Operand<? extends TType> handle, Operand<TInt32> index, Operand<TFloat32> flowIn,
      Class<T> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorArrayRead");
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(index.asOutput());
    opBuilder.addInput(flowIn.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new TensorArrayRead<>(opBuilder.build());
  }

  /**
   * Gets value.
   * The tensor that is read from the TensorArray.
   * @return value.
   */
  public Output<T> value() {
    return value;
  }

  @Override
  public Output<T> asOutput() {
    return value;
  }

  @OpInputsMetadata(
      outputsClass = TensorArrayRead.class
  )
  public static class Inputs extends RawOpInputs<TensorArrayRead<?>> {
    /**
     * The handle to a TensorArray.
     */
    public final Operand<? extends TType> handle;

    /**
     * The index input
     */
    public final Operand<TInt32> index;

    /**
     * A float scalar that enforces proper chaining of operations.
     */
    public final Operand<TFloat32> flowIn;

    /**
     * The type of the elem that is returned.
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new TensorArrayRead<>(op), op, Arrays.asList("dtype"));
      int inputIndex = 0;
      handle = (Operand<? extends TType>) op.input(inputIndex++);
      index = (Operand<TInt32>) op.input(inputIndex++);
      flowIn = (Operand<TFloat32>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
