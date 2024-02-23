/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
import java.util.Iterator;
import java.util.List;
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
 * Decodes a {@code variant} scalar Tensor into an {@code ExtensionType} value.
 * Returns the Tensor components encoded in a {@code CompositeTensorVariant}.
 * <p>Raises an error if {@code type_spec_proto} doesn't match the TypeSpec
 * in {@code encoded}.
 */
@OpMetadata(
    opType = CompositeTensorVariantToComponents.OP_NAME,
    inputsClass = CompositeTensorVariantToComponents.Inputs.class
)
public final class CompositeTensorVariantToComponents extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CompositeTensorVariantToComponents";

  private List<Output<?>> components;

  @SuppressWarnings("unchecked")
  public CompositeTensorVariantToComponents(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int componentsLength = operation.outputListLength("components");
    components = Arrays.asList(operation.outputList(outputIdx, componentsLength));
    outputIdx += componentsLength;
  }

  /**
   * Factory method to create a class wrapping a new CompositeTensorVariantToComponents operation.
   *
   * @param scope current scope
   * @param encoded A scalar {@code variant} Tensor containing an encoded ExtensionType value.
   * @param metadata String serialization for the TypeSpec.  Must be compatible with the
   * {@code TypeSpec} contained in {@code encoded}.  (Note: the encoding for the TypeSpec
   * may change in future versions of TensorFlow.)
   * @param Tcomponents Expected dtypes for components.
   * @return a new instance of CompositeTensorVariantToComponents
   */
  @Endpoint(
      describeByClass = true
  )
  public static CompositeTensorVariantToComponents create(Scope scope,
      Operand<? extends TType> encoded, String metadata, List<Class<? extends TType>> Tcomponents) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CompositeTensorVariantToComponents");
    opBuilder.addInput(encoded.asOutput());
    opBuilder.setAttr("metadata", metadata);
    opBuilder.setAttr("Tcomponents", Operands.toDataTypes(Tcomponents));
    return new CompositeTensorVariantToComponents(opBuilder.build());
  }

  /**
   * Gets components.
   * The component tensors for the ExtensionType value in {@code encoded}.
   * @return components.
   */
  public List<Output<?>> components() {
    return components;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) components.iterator();
  }

  @OpInputsMetadata(
      outputsClass = CompositeTensorVariantToComponents.class
  )
  public static class Inputs extends RawOpInputs<CompositeTensorVariantToComponents> {
    /**
     * A scalar {@code variant} Tensor containing an encoded ExtensionType value.
     */
    public final Operand<? extends TType> encoded;

    /**
     * String serialization for the TypeSpec.  Must be compatible with the
     * `TypeSpec` contained in `encoded`.  (Note: the encoding for the TypeSpec
     * may change in future versions of TensorFlow.)
     */
    public final String metadata;

    /**
     * Expected dtypes for components.
     */
    public final DataType[] Tcomponents;

    public Inputs(GraphOperation op) {
      super(new CompositeTensorVariantToComponents(op), op, Arrays.asList("metadata", "Tcomponents"));
      int inputIndex = 0;
      encoded = (Operand<? extends TType>) op.input(inputIndex++);
      metadata = op.attributes().getAttrString("metadata");
      Tcomponents = op.attributes().getAttrTypeList("Tcomponents");
    }
  }
}
