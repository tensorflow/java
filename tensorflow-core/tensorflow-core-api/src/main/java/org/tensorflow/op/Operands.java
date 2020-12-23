/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.op;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.internal.types.registry.TensorTypeRegistry;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/** Utilities for manipulating operand related types and lists. */
public final class Operands {

  /**
   * Converts a list of {@link Operand} into an array of {@link Output}.
   *
   * <p>Operation wrappers need to convert back a list of inputs into an array of outputs in order
   * to build an operation, see {@link OperationBuilder#addInputList(Output[])}.
   *
   * @param inputs an iteration of input operands
   * @return an array of outputs
   */
  public static Output<?>[] asOutputs(Iterable<? extends Operand<?>> inputs) {
    List<Output<?>> outputList = new ArrayList<>();
    for (Operand<?> input : inputs) {
      outputList.add(input.asOutput());
    }
    return outputList.toArray(new Output<?>[outputList.size()]);
  }

  /**
   * Converts a tensor type class to a {@link DataType} attribute.
   *
   * @param type tensor type class
   * @return data type
   */
  public static <T extends TType> DataType toDataType(Class<? extends T> type) {
    return TensorTypeRegistry.find(type).dataType();
  }

  /**
   * Converts a list of tensor type classes to an array of {@link DataType} attributes.
   *
   * @param types tensor type classes
   * @return an array of data types
   */
  public static <T extends TType> DataType[] toDataTypes(Collection<Class<? extends T>> types) {
    DataType[] dataTypes = new DataType[types.size()];
    int i = 0;
    for (Class<? extends TType> type : types) {
      dataTypes[i++] = toDataType(type);
    }
    return dataTypes;
  }

  // Disabled constructor
  private Operands() {}
}
