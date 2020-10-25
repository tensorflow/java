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

import org.tensorflow.op.Op;
import org.tensorflow.op.Scope;
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

  /**
   * Returns the symbolic handle of the tensor.
   *
   * <p>Inputs to TensorFlow operations are outputs of another TensorFlow operation. This method is
   * used to obtain a symbolic handle that represents the computation of the input.
   *
   * <p>If a valid non-null value is provided as the {@code scope} of this operand, the implementor
   * can program additional computations before returning its output. This might be required if,
   * for instance, the implementor has not yet been added as a node to the graph.</p>
   *
   * @param scope if non-null, scope that can be used by the operand to produce a new output
   * @see OperationBuilder#addInput(Output)
   */
  Output<T> asOutput(Scope scope);

  /**
   * Returns the symbolic handle of the tensor.
   *
   * <p>Inputs to TensorFlow operations are outputs of another TensorFlow operation. This method is
   * used to obtain a symbolic handle that represents the computation of the input.
   *
   * @see OperationBuilder#addInput(Output)
   */
  default Output<T> asOutput() {
    return asOutput(null);
  }

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
}
