/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */
package org.tensorflow.internal.types.registry;

import org.tensorflow.TensorMapper;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Registered information about a tensor type.
 *
 * @param <T> the tensor type
 */
public final class TensorTypeInfo<T extends TType> {

  /**
   * Returns the class of this tensor type
   */
  public Class<T> typeClass() {
    return typeClass;
  }

  /**
   * Returns the corresponding data type for this tensor type
   */
  public DataType dataType() {
    return dataType;
  }

  /**
   * Returns the number of bytes required to store one element of the corresponding data type, -1 if variable.
   */
  public int byteSize() {
    return byteSize;
  }

  /**
   * Returns true if elements of the corresponding data type are of variable length (undefined number of bytes)
   */
  public boolean isVariableLength() {
    return byteSize < 0;
  }

  /**
   * Returns an object used to map {@link org.tensorflow.RawTensor raw tensors} to a tensor of this type
   */
  public TensorMapper<T> mapper() {
    return mapper;
  }

  TensorTypeInfo(Class<T> typeClass, DataType dataType, int byteSize, TensorMapper<T> mapper) {
    this.typeClass = typeClass;
    this.dataType = dataType;
    this.byteSize = byteSize;
    this.mapper = mapper;
  }

  private final Class<T> typeClass;
  private final DataType dataType;
  private final int byteSize;
  private final TensorMapper<T> mapper;
}
