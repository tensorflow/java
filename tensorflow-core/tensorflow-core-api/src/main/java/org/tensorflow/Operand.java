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

import org.tensorflow.types.family.TType;

/**
 * Interface implemented by operands of a TensorFlow operation.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * // The "decodeJpeg" operation can be used as an operand to the "cast" operation
 * Operand<TUint8> decodeJpeg = ops.image.decodeJpeg(...);
 * ops.dtypes.cast(decodeJpeg, TFloat32.DTYPE);
 *
 * // The output "y" of the "unique" operation can be used as an operand to the "cast" operation
 * Output<TInt32> y = ops.unique(...).y();
 * ops.dtypes.cast(y, TFloat32.DTYPE);
 *
 * // The "split" operation can be used as operand list to the "concat" operation
 * Iterable<? extends Operand<TFloat32>> split = ops.split(...);
 * ops.concat(split, ops.constant(0));
 * }</pre>
 */
public interface Operand<T extends TType> {

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
   * Returns the data of the tensor.
   *
   * <i>This only works when running in an eager execution</i>
   *
   * @throws IllegalStateException if this is an operand of a graph
   */
  default T data() {
    return asOutput().tensor().data();
  }
}
