/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.framework.initializers;

import org.tensorflow.Operand;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * An interface for Initializers
 *
 * @param <T> The data Type for initializer operation
 */
public interface Initializer<T extends TType> {

  /**
   * Generates the operation used to perform the initialization.
   *
   * @param dims the shape dimensions
   * @param type the type of tensor
   * @return An operand for the initialization.
   */
  Operand<T> call(Operand<TInt64> dims, Class<T> type);
}
