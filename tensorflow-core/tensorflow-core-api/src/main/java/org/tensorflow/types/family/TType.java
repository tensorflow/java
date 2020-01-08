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

/**
 * Marker interface for all tensor types.
 *
 * <p>Tensor types are carried as a generic parameter of the {@link org.tensorflow.Tensor Tensor}
 * class bound by the {@code TType} interface. This generic parameter ensure type-compatibility
 * between operands of a computation at compile-time. For example:
 *
 * <pre>{@code
 * Tensor<TFloat32> tensor1 = TFloat32.ofShape(2, 3, 2);
 * Tensor<TFloat32> tensor2 = TFloat32.ofShape(2, 3, 2);
 * Tensor<TInt32> tensor3 = TInt32.ofShape(2, 3, 2);
 *
 * Ops tf = Ops.create();
 * tf.math.add(tf.constant(tensor1), tf.constant(tensor2));  // OK
 * tf.math.add(tf.constant(tensor1), tf.constant(tensor3));  // Compilation failure
 * }</pre>
 */
public interface TType {}
