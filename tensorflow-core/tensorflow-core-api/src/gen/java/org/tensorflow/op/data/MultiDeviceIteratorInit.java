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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Initializes the multi device iterator with the given dataset.
 */
@OpMetadata(
    opType = MultiDeviceIteratorInit.OP_NAME,
    inputsClass = MultiDeviceIteratorInit.Inputs.class
)
public final class MultiDeviceIteratorInit extends RawOp implements Operand<TInt64> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MultiDeviceIteratorInit";

  private Output<TInt64> incarnationId;

  public MultiDeviceIteratorInit(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    incarnationId = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MultiDeviceIteratorInit operation.
   *
   * @param scope current scope
   * @param dataset Dataset to be iterated upon.
   * @param multiDeviceIterator A MultiDeviceIteratorResource.
   * @param maxBufferSize The maximum size of the host side per device buffer to keep.
   * @return a new instance of MultiDeviceIteratorInit
   */
  @Endpoint(
      describeByClass = true
  )
  public static MultiDeviceIteratorInit create(Scope scope, Operand<? extends TType> dataset,
      Operand<? extends TType> multiDeviceIterator, Operand<TInt64> maxBufferSize) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MultiDeviceIteratorInit");
    opBuilder.addInput(dataset.asOutput());
    opBuilder.addInput(multiDeviceIterator.asOutput());
    opBuilder.addInput(maxBufferSize.asOutput());
    return new MultiDeviceIteratorInit(opBuilder.build());
  }

  /**
   * Gets incarnationId.
   * An int64 indicating which incarnation of the MultiDeviceIterator
   * is running.
   * @return incarnationId.
   */
  public Output<TInt64> incarnationId() {
    return incarnationId;
  }

  @Override
  public Output<TInt64> asOutput() {
    return incarnationId;
  }

  @OpInputsMetadata(
      outputsClass = MultiDeviceIteratorInit.class
  )
  public static class Inputs extends RawOpInputs<MultiDeviceIteratorInit> {
    /**
     * Dataset to be iterated upon.
     */
    public final Operand<? extends TType> dataset;

    /**
     * A MultiDeviceIteratorResource.
     */
    public final Operand<? extends TType> multiDeviceIterator;

    /**
     * The maximum size of the host side per device buffer to keep.
     */
    public final Operand<TInt64> maxBufferSize;

    public Inputs(GraphOperation op) {
      super(new MultiDeviceIteratorInit(op), op, Arrays.asList());
      int inputIndex = 0;
      dataset = (Operand<? extends TType>) op.input(inputIndex++);
      multiDeviceIterator = (Operand<? extends TType>) op.input(inputIndex++);
      maxBufferSize = (Operand<TInt64>) op.input(inputIndex++);
    }
  }
}
