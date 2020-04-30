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

import org.tensorflow.framework.data.Dataset;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;

import java.util.List;
import java.util.stream.Collectors;

public class TensorSliceDataset extends Dataset {
  private org.tensorflow.op.data.TensorSliceDataset dataset;

  public TensorSliceDataset(Ops tf, List<Operand<?>> components, List<DataType<?>> outputTypes) {
    super(
        tf,
        outputTypes,
        components.stream().map(c -> c.asOutput().shape().tail()).collect(Collectors.toList()));

    if (!(components.size() == outputTypes.size())) {
      throw new IllegalArgumentException(
          "Lists `tensors` and `dtypes` must have the same number of elements.");
    }

    this.dataset = tf.data.tensorSliceDataset(components, this.getOutputShapes());
  }

  @Override
  public Operand<?> getVariant() {
    return dataset;
  }
}
