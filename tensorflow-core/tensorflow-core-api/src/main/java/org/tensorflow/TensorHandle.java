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
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorType;

import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.proto.framework.DataType;

public final class TensorHandle implements Tensor {

  @Override
  public void close() {
    nativeHandle().close();
    this.nativeHandle = null;
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
  public DataType dataType() {
    return DataType.forNumber(dtype(nativeHandle()));
  }

  /**
   * FIXME public??
   * @return native handle to this tensor
   * @throws IllegalStateException if tensor has been closed
   */
  public TF_Tensor nativeHandle() {
    return requireHandle(nativeHandle);
  }

  static TensorHandle of(TF_Tensor nativeHandle) {
    return new TensorHandle(nativeHandle);
  }

  void attachTo(EagerSession session) {
    session.attach(nativeHandle());
    nativeHandle.releaseReference();
  }

  private TensorHandle(TF_Tensor nativeHandle) {
    this.nativeHandle = nativeHandle;
    nativeHandle.retainReference();
  }

  private static TF_Tensor requireHandle(TF_Tensor handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("close() was called on the Tensor");
    }
    return handle;
  }

  private static int dtype(TF_Tensor handle) {
    requireHandle(handle);
    return TF_TensorType(handle);
  }

  private TF_Tensor nativeHandle;
}
