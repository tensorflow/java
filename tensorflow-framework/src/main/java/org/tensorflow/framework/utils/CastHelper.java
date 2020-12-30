/*
 * Copyright (c) 2020, Oracle and/or its affiliates. All rights reserved.
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
package org.tensorflow.framework.utils;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TType;

/** A helper class for casting an Operand */
public class CastHelper {

  /**
   * Casts an operand to the desired type.
   *
   * @param tf The TensorFlow Ops
   * @param value the value to be cast
   * @param requiredType the required data type
   * @param <T> the required data type
   * @param <U> the original data type of the value
   * @return the value cast to the required data type.
   */
  @SuppressWarnings("unchecked")
  public static <T extends TType, U extends TType> Operand<T> cast(
          Ops tf, Operand<U> value, Class<T> requiredType) {
    return (value.type() == requiredType)
        ? (Operand<T>) value
        : tf.dtypes.cast(value, requiredType);
  }
}
