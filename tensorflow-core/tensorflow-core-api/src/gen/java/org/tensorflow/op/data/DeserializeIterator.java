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

package org.tensorflow.op.data;

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
import org.tensorflow.types.family.TType;

/**
 * Converts the given variant tensor to an iterator and stores it in the given resource.
 */
@OpMetadata(
    opType = DeserializeIterator.OP_NAME,
    inputsClass = DeserializeIterator.Inputs.class
)
@Operator(
    group = "data"
)
public final class DeserializeIterator extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DeserializeIterator";

  public DeserializeIterator(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new DeserializeIterator operation.
   *
   * @param scope current scope
   * @param resourceHandle A handle to an iterator resource.
   * @param serialized A variant tensor storing the state of the iterator contained in the
   * resource.
   * @return a new instance of DeserializeIterator
   */
  @Endpoint(
      describeByClass = true
  )
  public static DeserializeIterator create(Scope scope, Operand<? extends TType> resourceHandle,
      Operand<? extends TType> serialized) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DeserializeIterator");
    opBuilder.addInput(resourceHandle.asOutput());
    opBuilder.addInput(serialized.asOutput());
    return new DeserializeIterator(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = DeserializeIterator.class
  )
  public static class Inputs extends RawOpInputs<DeserializeIterator> {
    /**
     * A handle to an iterator resource.
     */
    public final Operand<? extends TType> resourceHandle;

    /**
     * A variant tensor storing the state of the iterator contained in the
     * resource.
     */
    public final Operand<? extends TType> serialized;

    public Inputs(GraphOperation op) {
      super(new DeserializeIterator(op), op, Arrays.asList());
      int inputIndex = 0;
      resourceHandle = (Operand<? extends TType>) op.input(inputIndex++);
      serialized = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
