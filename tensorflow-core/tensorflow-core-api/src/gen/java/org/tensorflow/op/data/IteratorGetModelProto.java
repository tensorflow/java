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
 * Returns the serialized model proto of an iterator resource.
 * Returns the serialized model proto of an iterator resource.
 */
@OpMetadata(
    opType = IteratorGetModelProto.OP_NAME,
    inputsClass = IteratorGetModelProto.Inputs.class
)
public final class IteratorGetModelProto extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IteratorGetModelProto";

  private Output<TString> modelProto;

  public IteratorGetModelProto(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    modelProto = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IteratorGetModelProto operation.
   *
   * @param scope current scope
   * @param iterator An resource from an dataset iterator.
   * @return a new instance of IteratorGetModelProto
   */
  @Endpoint(
      describeByClass = true
  )
  public static IteratorGetModelProto create(Scope scope, Operand<? extends TType> iterator) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IteratorGetModelProto");
    opBuilder.addInput(iterator.asOutput());
    return new IteratorGetModelProto(opBuilder.build());
  }

  /**
   * Gets modelProto.
   * A serialized model proto.
   * @return modelProto.
   */
  public Output<TString> modelProto() {
    return modelProto;
  }

  @Override
  public Output<TString> asOutput() {
    return modelProto;
  }

  @OpInputsMetadata(
      outputsClass = IteratorGetModelProto.class
  )
  public static class Inputs extends RawOpInputs<IteratorGetModelProto> {
    /**
     * An resource from an dataset iterator.
     */
    public final Operand<? extends TType> iterator;

    public Inputs(GraphOperation op) {
      super(new IteratorGetModelProto(op), op, Arrays.asList());
      int inputIndex = 0;
      iterator = (Operand<? extends TType>) op.input(inputIndex++);
    }
  }
}
