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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Encodes an {@code ExtensionType} value into a {@code variant} scalar Tensor.
 * Returns a scalar variant tensor containing a single {@code CompositeTensorVariant}
 * with the specified Tensor components and TypeSpec.
 */
@OpMetadata(
    opType = CompositeTensorVariantFromComponents.OP_NAME,
    inputsClass = CompositeTensorVariantFromComponents.Inputs.class
)
public final class CompositeTensorVariantFromComponents extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CompositeTensorVariantFromComponents";

  private Output<? extends TType> encoded;

  @SuppressWarnings("unchecked")
  public CompositeTensorVariantFromComponents(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    encoded = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CompositeTensorVariantFromComponents operation.
   *
   * @param scope current scope
   * @param components The component tensors for the extension type value.
   * @param metadata String serialization for the TypeSpec.  (Note: the encoding for the TypeSpec
   * may change in future versions of TensorFlow.)
   * @return a new instance of CompositeTensorVariantFromComponents
   */
  @Endpoint(
      describeByClass = true
  )
  public static CompositeTensorVariantFromComponents create(Scope scope,
      Iterable<Operand<?>> components, String metadata) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CompositeTensorVariantFromComponents");
    opBuilder.addInputList(Operands.asOutputs(components));
    opBuilder.setAttr("metadata", metadata);
    return new CompositeTensorVariantFromComponents(opBuilder.build());
  }

  /**
   * Gets encoded.
   * A {@code variant} Tensor that containing the encoded value.
   * @return encoded.
   */
  public Output<? extends TType> encoded() {
    return encoded;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) encoded;
  }

  @OpInputsMetadata(
      outputsClass = CompositeTensorVariantFromComponents.class
  )
  public static class Inputs extends RawOpInputs<CompositeTensorVariantFromComponents> {
    /**
     * The component tensors for the extension type value.
     */
    public final Iterable<Operand<?>> components;

    /**
     * String serialization for the TypeSpec.  (Note: the encoding for the TypeSpec
     * may change in future versions of TensorFlow.)
     */
    public final String metadata;

    /**
     * The Tcomponents attribute
     */
    public final DataType[] Tcomponents;

    public Inputs(GraphOperation op) {
      super(new CompositeTensorVariantFromComponents(op), op, Arrays.asList("metadata", "Tcomponents"));
      int inputIndex = 0;
      int componentsLength = op.inputListLength("components");
      components = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, componentsLength));
      inputIndex += componentsLength;
      metadata = op.attributes().getAttrString("metadata");
      Tcomponents = op.attributes().getAttrTypeList("Tcomponents");
    }
  }
}
