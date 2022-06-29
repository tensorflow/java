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
 * Returns a Tensor stack of all keys in a tensor map.
 * input_handle: the input map
 * keys: the returned Tensor of all keys in the map
 *
 * @param <T> data type for {@code keys} output
 */
@OpMetadata(
    opType = TensorMapStackKeys.OP_NAME,
    inputsClass = TensorMapStackKeys.Inputs.class
)
@Operator
public final class TensorMapStackKeys<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorMapStackKeys";

  private Output<T> keys;

  public TensorMapStackKeys(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    keys = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorMapStackKeys operation.
   *
   * @param scope current scope
   * @param inputHandle The inputHandle value
   * @param keyDtype The value of the keyDtype attribute
   * @param <T> data type for {@code TensorMapStackKeys} output and operands
   * @return a new instance of TensorMapStackKeys
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TensorMapStackKeys<T> create(Scope scope,
      Operand<? extends TType> inputHandle, Class<T> keyDtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorMapStackKeys");
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.setAttr("key_dtype", Operands.toDataType(keyDtype));
    return new TensorMapStackKeys<>(opBuilder.build());
  }

  /**
   * Gets keys.
   *
   * @return keys.
   */
  public Output<T> keys() {
    return keys;
  }

  @Override
  public Output<T> asOutput() {
    return keys;
  }

  @OpInputsMetadata(
      outputsClass = TensorMapStackKeys.class
  )
  public static class Inputs extends RawOpInputs<TensorMapStackKeys<?>> {
    /**
     * The inputHandle input
     */
    public final Operand<? extends TType> inputHandle;

    /**
     * The keyDtype attribute
     */
    public final DataType keyDtype;

    public Inputs(GraphOperation op) {
      super(new TensorMapStackKeys<>(op), op, Arrays.asList("key_dtype"));
      int inputIndex = 0;
      inputHandle = (Operand<? extends TType>) op.input(inputIndex++);
      keyDtype = op.attributes().getAttrType("key_dtype");
    }
  }
}
