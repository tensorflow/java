/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

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

import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorByteSize;

import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.internal.tensor.buffer.TensorBuffers;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.types.family.TType;

/**
 * A basic implementation of a dense tensor.
 *
 * <p>Dense tensors are stored as a continuous segment of native memory, mapped in a n-dimensional
 * space, normally by delegating all I/O operations to an {@link NdArray} instance.
 *
 * <p>Usage of this class is meant to be kept internal.
 */
public abstract class AbstractTensor<T> implements Tensor<T> {

  @Override
  public void close() {
    nativeHandle().close();
  }

  @Override
  public long numBytes() {
    return TF_TensorByteSize(nativeHandle());
  }

  @Override
  public ByteDataBuffer rawData() {
    return TensorBuffers.toBytes(nativeHandle(), true);
  }

  @Override
  public String toString() {
    return String.format("%s tensor with shape %s", TensorTypes.find(type()).dataType(), shape());
  }

  protected AbstractTensor(TF_Tensor nativeHandle) {
    this.nativeHandle = nativeHandle;
    nativeHandle.retainReference();
  }

  /**
   * @return native handle to this tensor
   * @throws IllegalStateException if tensor has been closed
   */
  protected TF_Tensor nativeHandle() {
    return requireHandle(nativeHandle);
  }

  void attachTo(EagerSession session) {
    session.attach(nativeHandle());
    nativeHandle.releaseReference();
  }

  protected static TF_Tensor requireHandle(TF_Tensor handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("close() was called on the Tensor");
    }
    return handle;
  }

  protected final TF_Tensor nativeHandle;
}
