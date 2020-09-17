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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt64;
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
 *              initializer.call(tf.constant(Shape.of(2,2)), TFloat32.DTYPE);
 * </pre>
 *
 * @param <T> The Type for the call operation
 */
public class Constant<T extends TType> extends BaseInitializer<T> {

  private final Number numberValue;
  private final Boolean booleanValue;

  /**
   * Creates an Initializer that generates tensors with a constant value.
   *
   * @param tf the TensorFlow Ops
   * @param value a number value
   */
  public Constant(Ops tf, Number value) {
    super(tf);
    this.numberValue = value;
    this.booleanValue = null;
  }

  /**
   * Creates an Initializer that generates tensors with a constant value.
   *
   * @param tf the TensorFlow Ops
   * @param value a boolean value
   */
  public Constant(Ops tf, Boolean value) {
    super(tf);
    this.numberValue = null;
    this.booleanValue = value;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Operand<TInt64> dims, DataType<T> dtype) {
    if (!(dtype.isNumeric() || dtype.isBoolean())) {
      throw new IllegalArgumentException("DataType must be numeric or boolean: " + dtype.name());
    }
    if (this.numberValue != null) {
      return tf.fill(dims, tf.dtypes.cast(tf.constant(numberValue.doubleValue()), dtype));
    } else if (this.booleanValue != null) {
      return tf.fill(dims, tf.dtypes.cast(tf.constant(this.booleanValue), dtype));
    } else { // should not happen, but throw here just in case
      throw new IllegalArgumentException(
          "Value must be either be a Number or Boolean, none supplied.");
    }
  }
}
