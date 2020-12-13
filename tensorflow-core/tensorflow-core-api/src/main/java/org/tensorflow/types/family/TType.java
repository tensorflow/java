/*
 *  Copyright 2019 The TensorFlow Authors. All Rights Reserved.
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

package org.tensorflow.types.family;

import org.tensorflow.Tensor;

/**
 * Common interface for all typed tensors.
 *
 * <p>Typed tensors wrap a {@link org.tensorflow.RawTensor RawTensor} by mapping their native memory
 * to a n-dimensional data space allowing direct I/O access from the JVM.</p>
 *
 * <p>Subinterfaces of {@code TType} are propagated as a generic parameter to various entities of
 * TensorFlow to identify the type of the tensor they carry. For example, a
 * {@link org.tensorflow.Operand Operand<TFloat32>} is an operand which outputs a 32-bit floating
 * point tensor. This parameter ensure type-compatibility between operands of a computation at
 * compile-time. For example:
 *
 * <pre>{@code
 * Ops tf = Ops.create();
 *
 * Constant<TFloat32> c1 = tf.array(2.0f, 3.0f, 2.0f);
 * Constant<TFloat32> c2 = tf.array(1.0f, 2.0f, 3.0f);
 * Constant<TInt32> c3 = tf.array(2, 3, 2);
 *
 * tf.math.add(c1, c2);  // OK
 * tf.math.add(c1, c3);  // Compilation failure
 * }</pre>
 *
 * <p>Even if all typed tensors implements somehow {@link org.tensorflow.ndarray.NdArray NdArray}
 * to provide access to their data, {@code TType} deliberately does not extend directly from this
 * interface, for the following reasons:
 * <ul>
 *   <li>Implementing {@code NdArray} at this level could only expose boxed-type accessors, which
 *   are less performant than their primitive equivalent, only exposed by subinterfaces of
 *   {@code NdArray} (e.g. {@code FloatNdArray}).
 *   </li>
 *   <li>{@code TType} would need to carry a new generic parameter for typing the {@code NdArray},
 *   which will increase the verbosity in the signature of any method accepting or returning
 *   an instance of this interface, which is very common.
 *   </li>
 * </ul>
 * Therefore, enforcing the user to cast a reference of {@code TType} in a concrete tensor type before
 * accessing its data guarantees better performance and improves readability.
 */
public interface TType extends Tensor {

  @Override
  default long numBytes() {
    return asRawTensor().numBytes();
  }

  @Override
  default void close() {
    asRawTensor().close();
  }
}
