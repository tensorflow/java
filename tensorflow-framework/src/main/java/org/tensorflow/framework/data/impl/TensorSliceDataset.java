/*
 * Copyright 2020 The TensorFlow Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow.framework.data.impl;

import org.tensorflow.Operand;
import org.tensorflow.framework.data.Dataset;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A Dataset whose elements are slices of the given tensors.
 *
 * <p>The given tensors are sliced along their first dimension. This operation preserves the
 * structure of the input tensors, removing the first dimension of each tensor and using it as the
 * dataset dimension. All input tensors must have the same size in their first dimensions.
 */
public class TensorSliceDataset extends Dataset {

  /**
   * Creates a Dataset whose elements are slices of the given tensors.
   *
   * @param tf the TensorFlow Ops
   * @param components the conpoents to slice
   * @param outputTypes A list of classes corresponding to the tensor type of each component of a
   *     dataset element.
   */
  public TensorSliceDataset(
      Ops tf, List<Operand<?>> components, List<Class<? extends TType>> outputTypes) {
    super(tf, makeVariant(tf, components, outputTypes), outputTypes, outputShapes(components));
  }

  /**
   * Gets the list of Shapes for the components.
   *
   * @param components the list of components
   * @return the output shapes for the components.
   */
  private static List<Shape> outputShapes(List<Operand<?>> components) {
    return components.stream().map(c -> c.shape().tail()).collect(Collectors.toList());
  }

  /**
   * Makes the variant Operand from the components
   *
   * @param tf the TensorFlow Ops
   * @param components the list of components
   * @param outputTypes list of classes corresponding to the tensor type of each component of a *
   *     dataset element.
   * @return the variant Operand that represents this dataset.
   */
  private static Operand<?> makeVariant(
      Ops tf, List<Operand<?>> components, List<Class<? extends TType>> outputTypes) {
    if (!(components.size() == outputTypes.size())) {
      throw new IllegalArgumentException(
          "Lists `tensors` and `dtypes` must have the same number of elements.");
    }

    return tf.data.tensorSliceDataset(components, outputShapes(components));
  }
}
