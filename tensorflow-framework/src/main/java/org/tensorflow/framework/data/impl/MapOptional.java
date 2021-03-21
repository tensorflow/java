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
import org.tensorflow.framework.data.DatasetOptional;

import java.util.List;
import java.util.function.Function;

/**
 * An optional represents the result of a MapDataset getNext operation that may fail, when the end
 * of the dataset has been reached.
 */
public class MapOptional extends DatasetOptional {
  private final Function<List<Operand<?>>, List<Operand<?>>> mapper;

  /**
   * Creates a MapOptional
   *
   * @param optional The source dataset optional
   * @param mapper the mapper Operands
   */
  MapOptional(DatasetOptional optional, Function<List<Operand<?>>, List<Operand<?>>> mapper) {
    super(optional);
    this.mapper = mapper;
  }
  /** {@inheritDoc} */
  @Override
  public List<Operand<?>> getValue() {
    return mapper.apply(super.getValue());
  }
}
