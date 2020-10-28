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
 * Marker interface for numeric tensor types.
 *
 * <p>Operations that only accepts numeric values as some of their operands enforce that the tensor
 * types for these operands to be bound to this interface. For example:
 *
 * <pre>{@code
 * TFloat32 tensor1 = TFloat32.vectorOf(1, 2, 3);
 * TBool tensor2 = TBool.vectorOf(true, false, true);
 *
 * Ops tf = Ops.create();
 * tf.nn.softmax(tf.constant(tensor1));  // OK
 * tf.nn.softmax(tf.constant(tensor2));  // Compilation failure
 * }</pre>
 */
public interface TNumber<T> extends TType<T> {}
