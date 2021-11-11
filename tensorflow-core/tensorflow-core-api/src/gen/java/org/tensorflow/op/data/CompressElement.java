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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Compresses a dataset element.
 */
@OpMetadata(
    opType = CompressElement.OP_NAME,
    inputsClass = CompressElement.Inputs.class
)
public final class CompressElement extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CompressElement";

  private Output<? extends TType> compressed;

  @SuppressWarnings("unchecked")
  public CompressElement(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    compressed = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CompressElement operation.
   *
   * @param scope current scope
   * @param components The components value
   * @return a new instance of CompressElement
   */
  @Endpoint(
      describeByClass = true
  )
  public static CompressElement create(Scope scope, Iterable<Operand<?>> components) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CompressElement");
    opBuilder.addInputList(Operands.asOutputs(components));
    return new CompressElement(opBuilder.build());
  }

  /**
   * Gets compressed.
   *
   * @return compressed.
   */
  public Output<? extends TType> compressed() {
    return compressed;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) compressed;
  }

  @OpInputsMetadata(
      outputsClass = CompressElement.class
  )
  public static class Inputs extends RawOpInputs<CompressElement> {
    /**
     * The components input
     */
    public final Iterable<Operand<?>> components;

    /**
     * The inputTypes attribute
     */
    public final DataType[] inputTypes;

    public Inputs(GraphOperation op) {
      super(new CompressElement(op), op, Arrays.asList("input_types"));
      int inputIndex = 0;
      int componentsLength = op.inputListLength("components");
      components = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, componentsLength));
      inputIndex += componentsLength;
      inputTypes = op.attributes().getAttrTypeList("input_types");
    }
  }
}
