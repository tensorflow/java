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

import static org.tensorflow.framework.utils.CastHelper.cast;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Initializer that generates tensors initialized to 1.
 *
 * <p>Examples:
 *
 * <pre>
 *      Ones&lt;TFloat32&gt; initializer =
 *              new org.tensorflow.framework.initializers.Ones&lt;&gt;(tf);
 *      Operand&lt;TFloat32&gt; values =
 *              initializer.call(Ops tf, tf.constant(Shape.of(2,2)), TFloat32.class);
 * </pre>
 *
 * @param <T> The TType for the call operation
 */
public class Ones<T extends TType> extends BaseInitializer<T> {

  /**
   * Creates an Initializer that sets all values to one.
   *
   * <p>Examples:
   *
   * <pre>
   *      Ones&lt;TFloat32&gt; initializer =
   *              new org.tensorflow.framework.initializers.Ones&lt;&gt;(tf);
   *      Operand&lt;TFloat32&gt; values =
   *              initializer.call(Ops tf, tf.constant(Shape.of(2,2)), TFloat32.class);
   * </pre>
   */
  public Ones() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Ops tf, Operand<TInt64> dims, Class<T> type) {

    if (!TNumber.class.isAssignableFrom(type) && type != TBool.class) {
      throw new IllegalArgumentException(
          "Tensor type must be numeric or boolean: " + type.getSimpleName());
    }
    return tf.fill(dims, cast(tf, tf.constant(1), type));
  }
}
