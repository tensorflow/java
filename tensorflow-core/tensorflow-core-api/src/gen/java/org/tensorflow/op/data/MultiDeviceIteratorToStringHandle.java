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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Produces a string handle for the given MultiDeviceIterator.
 */
@OpMetadata(
    opType = MultiDeviceIteratorToStringHandle.OP_NAME,
    inputsClass = MultiDeviceIteratorToStringHandle.Inputs.class
)
public final class MultiDeviceIteratorToStringHandle extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MultiDeviceIteratorToStringHandle";

  private Output<TString> stringHandle;

  public MultiDeviceIteratorToStringHandle(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    stringHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MultiDeviceIteratorToStringHandle operation.
   *
   * @param scope current scope
   * @param multiDeviceIterator A MultiDeviceIterator resource.
   * @return a new instance of MultiDeviceIteratorToStringHandle
   */
  @Endpoint(
      describeByClass = true
  )
  public static MultiDeviceIteratorToStringHandle create(Scope scope,
      Operand<? extends TType> multiDeviceIterator) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MultiDeviceIteratorToStringHandle");
    opBuilder.addInput(multiDeviceIterator.asOutput());
    return new MultiDeviceIteratorToStringHandle(opBuilder.build());
  }

  /**
   * Gets stringHandle.
   * A string representing the resource.
   * @return stringHandle.
   */
  public Output<TString> stringHandle() {
    return stringHandle;
  }

  @Override
  public Output<TString> asOutput() {
    return stringHandle;
  }

  @OpInputsMetadata(
      outputsClass = MultiDeviceIteratorToStringHandle.class
  )
  public static class Inputs extends RawOpInputs<MultiDeviceIteratorToStringHandle> {
    /**
     * A MultiDeviceIterator resource.
     */
    public final Operand<? extends TType> multiDeviceIterator;

    public Inputs(GraphOperation op) {
      super(new MultiDeviceIteratorToStringHandle(op), op, Arrays.asList());
      int inputIndex = 0;
      multiDeviceIterator = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
