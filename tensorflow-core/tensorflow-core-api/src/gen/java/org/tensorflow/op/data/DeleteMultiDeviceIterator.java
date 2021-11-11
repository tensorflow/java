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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.family.TType;

/**
 * A container for an iterator resource.
 */
@OpMetadata(
    opType = DeleteMultiDeviceIterator.OP_NAME,
    inputsClass = DeleteMultiDeviceIterator.Inputs.class
)
public final class DeleteMultiDeviceIterator extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DeleteMultiDeviceIterator";

  public DeleteMultiDeviceIterator(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new DeleteMultiDeviceIterator operation.
   *
   * @param scope current scope
   * @param multiDeviceIterator A handle to the multi device iterator to delete.
   * @param iterators A list of iterator handles (unused). This is added so that automatic control dependencies get added during function tracing that ensure this op runs after all the dependent iterators are deleted.
   * @param deleter A variant deleter.
   * @return a new instance of DeleteMultiDeviceIterator
   */
  @Endpoint(
      describeByClass = true
  )
  public static DeleteMultiDeviceIterator create(Scope scope,
      Operand<? extends TType> multiDeviceIterator, Iterable<Operand<? extends TType>> iterators,
      Operand<? extends TType> deleter) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DeleteMultiDeviceIterator");
    opBuilder.addInput(multiDeviceIterator.asOutput());
    opBuilder.addInputList(Operands.asOutputs(iterators));
    opBuilder.addInput(deleter.asOutput());
    return new DeleteMultiDeviceIterator(opBuilder.build());
  }

  @OpInputsMetadata(
      outputsClass = DeleteMultiDeviceIterator.class
  )
  public static class Inputs extends RawOpInputs<DeleteMultiDeviceIterator> {
    /**
     * A handle to the multi device iterator to delete.
     */
    public final Operand<? extends TType> multiDeviceIterator;

    /**
     * A list of iterator handles (unused). This is added so that automatic control dependencies get added during function tracing that ensure this op runs after all the dependent iterators are deleted.
     */
    public final Iterable<Operand<? extends TType>> iterators;

    /**
     * A variant deleter.
     */
    public final Operand<? extends TType> deleter;

    public Inputs(GraphOperation op) {
      super(new DeleteMultiDeviceIterator(op), op, Arrays.asList());
      int inputIndex = 0;
      multiDeviceIterator = (Operand<? extends TType>) op.input(inputIndex++);
      int iteratorsLength = op.inputListLength("iterators");
      iterators = Arrays.asList((Operand<? extends TType>[]) op.inputList(inputIndex, iteratorsLength));
      inputIndex += iteratorsLength;
      deleter = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
