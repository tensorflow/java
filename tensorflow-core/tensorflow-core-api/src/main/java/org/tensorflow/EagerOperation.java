/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

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

import static org.tensorflow.internal.c_api.global.tensorflow.TFE_DeleteOp;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_DeleteTensorHandle;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpGetInputLength;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_OpGetOutputLength;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_TensorHandleDataType;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_TensorHandleDim;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_TensorHandleNumDims;
import static org.tensorflow.internal.c_api.global.tensorflow.TFE_TensorHandleResolve;

import java.util.concurrent.atomic.AtomicReferenceArray;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.c_api.TFE_Op;
import org.tensorflow.internal.c_api.TFE_TensorHandle;
import org.tensorflow.internal.c_api.TF_Status;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.tools.Shape;

/**
 * Implementation of an {@link Operation} executed eagerly.
 *
 * <p>EagerOperation instances are valid only as long as the {@link EagerSession} they are a part of
 * is valid. Thus, if {@link EagerSession#close()} has been invoked, then methods on the
 * EagerOperation instance may fail with an {@code IllegalStateException}.
 *
 * <p>EagerOperation instances are thread-safe.
 */
class EagerOperation extends AbstractOperation {

  EagerOperation(
      EagerSession session,
      TFE_Op opNativeHandle,
      TFE_TensorHandle[] outputNativeHandles,
      String type,
      String name) {
    this.session = session;
    this.type = type;
    this.name = name;
    this.nativeRef = new NativeReference(session, this, opNativeHandle, outputNativeHandles);
    this.outputTensors = new AtomicReferenceArray<>(outputNativeHandles.length);
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public String type() {
    return type;
  }

  @Override
  public int numOutputs() {
    return nativeRef.outputHandles.length;
  }

  @Override
  public int outputListLength(final String name) {
    return outputListLength(nativeRef.opHandle, name);
  }

  @Override
  public int inputListLength(final String name) {
    return inputListLength(nativeRef.opHandle, name);
  }

  @Override
  public TFE_TensorHandle getUnsafeNativeHandle(int outputIndex) {
    return nativeRef.outputHandles[outputIndex];
  }

  @Override
  public Shape shape(int outputIndex) {
    // If the tensor of this output has already been resolved, return its shape.
    // Otherwise, retrieve the tensor shape from the native library.
    Tensor<?> tensor = outputTensors.get(outputIndex);
    if (tensor != null) {
      return tensor.shape();
    }
    TFE_TensorHandle outputNativeHandle = getUnsafeNativeHandle(outputIndex);
    long[] shape = new long[numDims(outputNativeHandle)];
    for (int i = 0; i < shape.length; ++i) {
      shape[i] = dim(outputNativeHandle, i);
    }
    return Shape.of(shape);
  }

  @Override
  public DataType<?> dtype(int outputIndex) {
    // If the tensor of this output has already been resolved, return its datatype.
    // Otherwise, retrieve the tensor datatype from the native library.
    Tensor<?> tensor = outputTensors.get(outputIndex);
    if (tensor != null) {
      return tensor.dataType();
    }
    TFE_TensorHandle outputNativeHandle = getUnsafeNativeHandle(outputIndex);
    return DataTypes.fromNativeCode(dataType(outputNativeHandle));
  }

  @Override
  public Tensor<?> tensor(int outputIndex) {
    Tensor<?> tensor = outputTensors.get(outputIndex);
    if (tensor == null) {
      tensor = resolveTensor(outputIndex);
    }
    return tensor;
  }

  private final EagerSession session;
  private final NativeReference nativeRef;
  private final String type;
  private final String name;
  private final AtomicReferenceArray<Tensor<?>> outputTensors;

  private Tensor<?> resolveTensor(int outputIndex) {
    // Take an optimistic approach, where we attempt to resolve the output tensor without locking.
    // If another thread has resolved it meanwhile, release our copy and reuse the existing one
    // instead.
    TF_Tensor tensorNativeHandle = resolveTensorHandle(getUnsafeNativeHandle(outputIndex));
    Tensor<?> tensor = Tensor.fromHandle(tensorNativeHandle, session);
    if (!outputTensors.compareAndSet(outputIndex, null, tensor)) {
      tensor.close();
      tensor = outputTensors.get(outputIndex);
    }
    return tensor;
  }

  private static class NativeReference extends EagerSession.NativeReference {

    NativeReference(
        EagerSession session, EagerOperation operation, TFE_Op opHandle, TFE_TensorHandle[] outputHandles) {
      super(session, operation);
      this.opHandle = opHandle;
      this.outputHandles = outputHandles;
    }

    @Override
    void delete() {
      if (opHandle != null && !opHandle.isNull()) {
        for (int i = 0; i < outputHandles.length; ++i) {
          if (outputHandles[i] != null && !outputHandles[i].isNull()) {
            EagerOperation.deleteTensorHandle(outputHandles[i]);
            outputHandles[i] = null;
          }
        }
        EagerOperation.delete(opHandle);
        opHandle = null;
      }
    }

    private TFE_Op opHandle;
    private final TFE_TensorHandle[] outputHandles;
  }

  private static void requireOp(TFE_Op handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("Eager session has been closed");
    }
  }

  private static void requireTensorHandle(TFE_TensorHandle handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("Eager session has been closed");
    }
  }

  private static void delete(TFE_Op handle) {
    if (handle == null || handle.isNull()) return;
    TFE_DeleteOp(handle);
  }

  private static void deleteTensorHandle(TFE_TensorHandle handle) {
    if (handle == null || handle.isNull()) return;
    TFE_DeleteTensorHandle(handle);
  }

  private static TF_Tensor resolveTensorHandle(TFE_TensorHandle handle) {
    requireTensorHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      TF_Tensor tensor = TFE_TensorHandleResolve(handle, status);
      status.throwExceptionIfNotOK();
      return tensor;
    }
  }

  private static int outputListLength(TFE_Op handle, String name) {
    requireOp(handle);
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      int length = TFE_OpGetOutputLength(handle, name, status);
      status.throwExceptionIfNotOK();
      return length;
    }
  }

  private static int inputListLength(TFE_Op handle, String name) {
    requireOp(handle);
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      int length = TFE_OpGetInputLength(handle, name, status);
      status.throwExceptionIfNotOK();
      return length;
    }
  }

  private static int dataType(TFE_TensorHandle handle) {
    requireTensorHandle(handle);
    return TFE_TensorHandleDataType(handle);
  }

  private static int numDims(TFE_TensorHandle handle) {
    requireTensorHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      int numDims = TFE_TensorHandleNumDims(handle, status);
      status.throwExceptionIfNotOK();
      return numDims;
    }
  }

  private static long dim(TFE_TensorHandle handle, int index) {
    requireTensorHandle(handle);
    try (PointerScope scope = new PointerScope()) {
      TF_Status status = TF_Status.newStatus();
      long dim = TFE_TensorHandleDim(handle, index, status);
      status.throwExceptionIfNotOK();
      return dim;
    }
  }
}