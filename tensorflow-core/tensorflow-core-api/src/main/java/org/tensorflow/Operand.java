/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow;

import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TypeRegistry;
import org.tensorflow.types.family.TType;

/**
 * Interface implemented by operands of a TensorFlow operation.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * Ops tf = Ops.create();
 *
 * // The "decodeJpeg" operation can be used as an operand to the "cast" operation
 * Operand<TUint8> decodeJpeg = tf.image.decodeJpeg(...);
 * tf.dtypes.cast(decodeJpeg, TFloat32.DTYPE);
 *
 * // The output "y" of the "unique" operation can be used as an operand to the "cast" operation
 * Output<TInt32> y = tf.unique(...).y();
 * tf.dtypes.cast(y, TFloat32.DTYPE);
 *
 * // The "split" operation can be used as operand list to the "concat" operation
 * Iterable<? extends Operand<TFloat32>> split = tf.split(...);
 * tf.concat(split, tf.constant(0));
 * }</pre>
 */
public interface Operand<T extends TType> extends Op {

  /** Returns the (possibly partially known) shape of the tensor referred to by this Output. */
  default Shape shape() {
    return asOutput().shape();
  }

  /** Returns the DataType of the tensor referred to by this Output. */
  default Class<T> type() {
    return asOutput().type();
  }


  /**
   * Returns the symbolic handle of the tensor.
   *
   * <p>Inputs to TensorFlow operations are outputs of another TensorFlow operation. This method is
   * used to obtain a symbolic handle that represents the computation of the input.
   *
   * @see OperationBuilder#addInput(Output)
   */
  Output<T> asOutput();

  /**
   * Returns this operand as a tensor.
   *
   * <i>Only works when running in an eager execution</i>
   * <p>This helper method is equivalent to {@code asOutput().tensor()}
   *
   * @return the tensor
   * @throws IllegalStateException if this is an operand of a graph
   */
  default T asTensor() {
    return asOutput().tensor();
  }

  default <U extends TType> Operand<U> expect(Class<U> type) {
    if (asOutput().type() != type) {
      throw new IllegalArgumentException(
          "Cannot cast from tensor of " + asOutput().type().getSimpleName() + " to tensor of " + type.getSimpleName());
    }
    return (Operand<U>)this;
  }
}
