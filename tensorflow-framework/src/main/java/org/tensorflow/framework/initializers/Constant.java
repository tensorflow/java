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
 * Initializer that generates tensors with a constant value.
 *
 * <p>Examples:
 *
 * <pre>
 *      Constant&lt;TFloat32&gt; initializer =
 *              new org.tensorflow.framework.initializers.Constant&lt;&gt;(tf, 3f);
 *      Operand&lt;TFloat32&gt; values =
 *              initializer.call(Ops tf, tf.constant(Shape.of(2,2)), TFloat32.class);
 * </pre>
 *
 * @param <T> The Type for the call operation
 */
public class Constant<T extends TType> extends BaseInitializer<T> {

  private final double doubleValue;
  private final long longValue;
  private final boolean booleanValue;
  private final ValueType valueType;

  /**
   * Creates an Initializer that generates tensors with a constant value.
   *
   * @param value a long value used for the constant.
   */
  public Constant(long value) {
    super();
    longValue = value;
    doubleValue = 0;
    booleanValue = false;
    valueType = ValueType.LONG;
  }

  /**
   * Creates an Initializer that generates tensors with a constant value.
   *
   * @param value a double value used for the constant.
   */
  public Constant(double value) {
    super();
    doubleValue = value;
    longValue = 0;
    booleanValue = false;
    valueType = ValueType.DOUBLE;
  }

  /**
   * Creates an Initializer that generates tensors with a constant value.
   *
   * @param value a boolean value used for the constant.
   */
  public Constant(boolean value) {
    super();
    booleanValue = value;
    doubleValue = 0;
    longValue = 0;
    valueType = ValueType.BOOLEAN;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Ops tf, Operand<TInt64> dims, Class<T> type) {

    if (!TNumber.class.isAssignableFrom(type) && type != TBool.class) {
      throw new IllegalArgumentException(
          "Tensor type must be numeric or boolean: " + type.getSimpleName());
    }
    switch (valueType) {
      case LONG:
        return tf.fill(dims, cast(tf, tf.constant(longValue), type));
      case DOUBLE:
        return tf.fill(dims, cast(tf, tf.constant(doubleValue), type));
      default:
        return tf.fill(dims, cast(tf, tf.constant(booleanValue), type));
    }
  }

  private enum ValueType {
    LONG,
    DOUBLE,
    BOOLEAN
  }
}
