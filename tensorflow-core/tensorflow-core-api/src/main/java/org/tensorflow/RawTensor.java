/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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

import static org.tensorflow.internal.c_api.global.tensorflow.TF_Dim;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NumDims;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorByteSize;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorType;

import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.internal.types.registry.TensorTypeInfo;
import org.tensorflow.internal.types.registry.TensorTypeRegistry;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * A tensor which memory has not been mapped to a data space directly accessible from the JVM.
 *
 * <p>A raw tensor is a minimalist representation of a tensor allocated in native memory by the
 * TensorFlow runtime library and it controls its lifetime within the current process. The data
 * is represented by a flat {@link ByteDataBuffer buffer of bytes}, until it is mapped in a
 * n-dimensional typed space by a {@link TType typed tensor}.</p>
 *
 * <p>Instances of a RawTensor are <b>not</b> thread-safe and their resource must be released
 * by calling {@link #close()} explicitly or implicitly via try-with-resources.</p>
 */
public final class RawTensor implements Tensor {

  @Override
  public DataType dataType() {
    return typeInfo.dataType();
  }

  @Override
  public long numBytes() {
    return TF_TensorByteSize(nativeHandle());
  }

  @Override
  public Shape shape() {
    return shape;
  }

  @Override
  public RawTensor asRawTensor() {
    return this;
  }

  @Override
  public void close() {
    tensorScope.close();
  }

  /**
   * Returns the raw data of this tensor as a buffer of bytes.
   *
   * @return the tensor bytes
   * @throws IllegalStateException if the tensor has been closed
   */
  public ByteDataBuffer data() {
    if (buffer == null) {
      buffer = TensorBuffers.toBytes(nativeHandle());
    }
    return buffer;
  }

  /**
   * Returns a string describing the type and shape of the tensor.
   */
  @Override
  public String toString() {
    return String.format("%s tensor with shape %s", typeInfo.dataType(), shape);
  }

  /**
   * Allocates a new tensor in native memory of the given type, shape and size.
   *
   * <p>The size of the tensor must be at least large enough to contain all scalars for the
   * given type and shape, i.e. <code>size >= type.byteSize() * shape.size()</code>. More memory
   * can be allocated to store also metadata within the tensor itself, e.g. a lookup table
   * in a string tensor.
   *
   * @param type tensor type class
   * @param shape shape of the tensor
   * @param size size in bytes of the tensor, or -1 to compute the size from the shape
   * @return allocated tensor
   */
  static RawTensor allocate(Class<? extends TType> type, Shape shape, long size) {
    TensorTypeInfo<?> typeInfo = TensorTypeRegistry.find(type);
    long effectiveSize = size;
    if (effectiveSize < 0) {
      effectiveSize = shape.size() * typeInfo.byteSize();

    } else if (!typeInfo.isVariableLength() && shape.size() * typeInfo.byteSize() > effectiveSize) {
      // Minimum requirements for datatypes of variable length cannot be verified in a relevant way so
      // we only validate them for fixed length datatypes
      throw new IllegalArgumentException("Tensor size is not large enough to contain all scalar values");
    }
    TF_Tensor nativeHandle = allocate(typeInfo.dataType().getNumber(), shape.asArray(), effectiveSize);
    try (PointerScope scope = new PointerScope()) {
      scope.attach(nativeHandle);
      RawTensor t = new RawTensor(typeInfo, shape);
      t.tensorHandle = nativeHandle;
      t.tensorScope = scope.extend();
      return t;
    }
  }

  /**
   * Create a Tensor object from a handle to the C TF_Tensor object.
   *
   * <p>Takes ownership of the handle.
   */
  static RawTensor fromHandle(TF_Tensor handle) {
    TensorTypeInfo<?> typeInfo = TensorTypeRegistry.find(DataType.forNumber(dtype(handle)));
    RawTensor t = new RawTensor(typeInfo, Shape.of(shape(handle)));
    try (PointerScope scope = new PointerScope()) {
        scope.attach(handle);
        t.tensorHandle = handle;
        t.tensorScope = scope.extend();
    }
    return t;
  }

  /**
   * Create an eager Tensor object from a handle to the C TF_Tensor object.
   *
   * <p>Takes ownership of the handle.
   */
  static RawTensor fromHandle(TF_Tensor handle, EagerSession session) {
    RawTensor t = fromHandle(handle);
    session.attach(handle);
    t.tensorScope.detach(handle);
    return t;
  }

  /**
   * Returns the native handle to this tensor
   * @throws IllegalStateException if tensor has been closed
   */
  TF_Tensor nativeHandle() {
    return requireHandle(tensorHandle);
  }

  /**
   * Returns a typed reference to this tensor
   *
   * <p>In some cases, it is more useful to keep a typed reference to a tensor rather than its raw
   * nature to prevent mapping its memory on every access (e.g. when calling {@link Operand#asTensor()}).
   *
   * @param <T> type of the tensor (must be compatible with the internal representation of this tensor,
   *            as indicated by {@link #dataType()})
   * @return typed reference to this tensor
   * @throws ClassCastException if {@code T} is not compatible type with {@link #dataType()}
   */
  <T extends TType> T asTypedTensor() {
    return (T)typeInfo.mapper().mapDense(this);
  }

  private static TF_Tensor requireHandle(TF_Tensor handle) {
    if (handle == null || handle.isNull()) {
      throw new IllegalStateException("close() was called on the Tensor");
    }
    return handle;
  }

  private static TF_Tensor allocate(int dtype, long[] shape, long byteSize) {
    TF_Tensor t = TF_Tensor.allocateTensor(dtype, shape, byteSize);
    if (t == null || t.isNull()) {
      throw new IllegalStateException("unable to allocate memory for the Tensor");
    }
    return t;
  }

  private static int dtype(TF_Tensor handle) {
    requireHandle(handle);
    return TF_TensorType(handle);
  }

  private static long[] shape(TF_Tensor handle) {
    requireHandle(handle);
    int numDims = TF_NumDims(handle);
    long[] dims = new long[numDims];
    for (int i = 0; i < numDims; ++i) {
      dims[i] = TF_Dim(handle, i);
    }
    return dims;
  }

  RawTensor(TensorTypeInfo<? extends TType> typeInfo, Shape shape) {
    this.typeInfo = typeInfo;
    this.shape = shape;
  }

  private PointerScope tensorScope;
  private TF_Tensor tensorHandle;
  private final TensorTypeInfo<? extends TType> typeInfo;
  private final Shape shape;
  private ByteDataBuffer buffer = null;

  static {
    TensorFlow.init();
  }
}
