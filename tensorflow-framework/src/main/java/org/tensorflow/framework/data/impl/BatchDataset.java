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
import org.tensorflow.op.core.Constant;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

import java.util.List;

/** Combines consecutive elements of a dataset into batches. */
public class BatchDataset extends Dataset {

  /**
   * Creates a batched dataset
   *
   * @param tf The TensorFlow Ops.
   * @param variant the Operand that represents the dataset.
   * @param batchSize The number of desired elements per batch
   * @param dropRemainder Whether to leave out the final batch if it has fewer than `batchSize` *
   *     elements.
   * @param outputTypes A list of classes corresponding to the tensor type of each component of a
   *     dataset element.
   * @param outputShapes A list of `Shape` objects corresponding to the shapes of each component of
   *     a dataset element.
   */
  public BatchDataset(
      Ops tf,
      Operand<?> variant,
      Constant<TInt64> batchSize,
      Constant<TBool> dropRemainder,
      List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    super(
        tf,
        tf.data.batchDataset(variant, batchSize, dropRemainder, outputTypes, outputShapes),
        outputTypes,
        outputShapes);
  }
}
