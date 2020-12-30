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
import org.tensorflow.op.Ops;
import org.tensorflow.ndarray.Shape;

import java.util.List;
import java.util.stream.Collectors;
import org.tensorflow.types.family.TType;

public class TensorSliceDataset extends Dataset {

  public TensorSliceDataset(Ops tf, List<Operand<?>> components, List<Class<? extends TType>> outputTypes) {
    super(tf, makeVariant(tf, components, outputTypes), outputTypes, outputShapes(components));
  }

  private static List<Shape> outputShapes(List<Operand<?>> components) {
    return components.stream().map(c -> c.shape().tail()).collect(Collectors.toList());
  }

  private static Operand<?> makeVariant(
      Ops tf, List<Operand<?>> components, List<Class<? extends TType>> outputTypes) {
    if (!(components.size() == outputTypes.size())) {
      throw new IllegalArgumentException(
          "Lists `tensors` and `dtypes` must have the same number of elements.");
    }

    return tf.data.tensorSliceDataset(components, outputShapes(components));
  }
}
