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
package org.tensorflow;

import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Maps the native memory of a {@link RawTensor} to a n-dimensional typed data space accessible from
 * the JVM.
 *
 * <p>Usage of this class is reserved for internal purposes only.
 *
 * @param <T> tensor type mapped by this object
 * @see TType
 */
public abstract class TensorMapper<T extends TType> {

  /**
   * Maps the provided dense raw {@code tensor} as a tensor of type {@code T}.
   *
   * @param tensor the dense tensor to map, in its raw nature
   * @return an instance of {@code T}
   */
  protected abstract T mapDense(RawTensor tensor);

  /**
   * Maps the provided dense {@code tensors} as a sparse tensor of type {@code T}.
   *
   * @param indices indices of the non-default values in a dense space
   * @param values non-default values of the tensor
   * @param denseShape size of the dimensions definining the shape of the sparse tensor in a dense
   *     space.
   * @param tensorScope scope to extend to keep a reference on the sub-tensors composing this sparse
   *     tensor
   * @return an instance of {@code T}, that could also be casted to a {@link SparseTensor
   *     SparseTensor<T>}
   */
  protected abstract SparseTensor<T> mapSparse(
      TInt64 indices, T values, TInt64 denseShape, PointerScope tensorScope);

  /**
   * Helper for retrieving the native handle of a raw tensor
   *
   * @param tensor a raw tensor
   * @return the native handle of that tensor
   * @throws IllegalStateException if the tensor has been released
   */
  protected static TF_Tensor nativeHandle(RawTensor tensor) {
    return tensor.nativeHandle();
  }
}
