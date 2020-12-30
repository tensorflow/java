/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
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

/**
 * Defines classes that represent TensorFlow tensor types. For each possible data type that can be
 * used in a tensor, there is a corresponding interface that is used to represent it and its hidden
 * implementation. For example, the TensorFlow int32 type is represented by the tensor type interface
 * {@link org.tensorflow.types.TInt32 TInt32}, where the {@code T} prefix stands for "Tensor of".
 *
 * <p>To support compile-time checking of tensor element types, each interface in this package must be
 * bound to one of the marker interface found in {@link org.tensorflow.types.family}, according
 * to the nature of the data.
 *
 * <p>Each tensor type must be annotated with {@link org.tensorflow.types.annotation.TensorType} to
 * provide type metadata that should be used for allocating or mapping tensors of this type.
 *
 * <p>Instances of tensor types must also implement the {@link org.tensorflow.ndarray.NdArray NdArray}
 * interface so a user can access directly the tensor data in a n-dimensional space.
 *
 * <p>Note that while it is always possible to allocate a tensor using the
 * {@link org.tensorflow.Tensor#of(Class, Shape) Tensor.of(...)}
 * method, most tensor types expose factory methods that simplify the creation process, like
 * {@code scalarOf(...)}, {@code vectorOf(...)}, {@code tensorOf(...)}, etc.
 */
package org.tensorflow.types;

import org.tensorflow.ndarray.Shape;