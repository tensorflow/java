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
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Creates an Initializer that sets all values to zero.
 *
 * <p>Examples:
 *
 * <pre>
 *      Zeros&lt;TFloat32&gt; initializer =
 *              new org.tensorflow.framework.initializers.Zeros&lt;&gt;(tf);
 *      Operand&lt;TFloat32&gt; values =
 *              initializer.call(tf.constant(Shape.of(2,2)), TFloat32.class);
 * </pre>
 */
public class Zeros extends BaseInitializer<TType> {

  /**
   * Creates an Initializer that sets all values to one.
   *
   * @param tf the TensorFlow Ops
   */
  public Zeros(Ops tf) {
    super(tf);
  }

  /**
   * Generates the operation used to perform the initialization.
   *
   * @param dims the shape dimensions
   * @param type the data type of tensor
   * @param <U> The data Type for initializer operation
   * @return An operand for the initialization.
   * @throws IllegalArgumentException if the data type is not a TNumber or TBool
   */
  @Override
  public <U extends TType> Operand<U> call(Operand<TInt64> dims, Class<U> type) {
    if (!TNumber.class.isAssignableFrom(type) && type != TBool.class) {
      throw new IllegalArgumentException(
          "Tensor type must be numeric or boolean: " + type.getSimpleName());
    }
    return tf.zeros(dims, type);
  }
}
