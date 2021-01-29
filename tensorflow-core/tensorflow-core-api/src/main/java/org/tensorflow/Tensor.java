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

import java.util.function.Consumer;
import org.bytedeco.javacpp.Pointer;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.Shaped;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * A statically typed multi-dimensional array.
 *
 * <p>There are two categories of tensors in TensorFlow Java: {@link TType typed tensors} and
 * {@link RawTensor raw tensors}. The former maps the tensor native memory to an n-dimensional typed data space,
 * allowing direct I/O operations from the JVM, while the latter is only a reference to a native tensor allowing basic
 * operations and flat data access.</p>
 *
 * <p><b>WARNING:</b> Resources consumed by the Tensor object should be explicitly freed by
 * invoking the {@link #close()} method on the tensor, or using {@link TensorScope}. For example, using a
 * try-with-resources block:
 *
 * <pre>{@code
 * try (TensorScope scope = new TensorScope()) {
 *   Tensor t = Tensor.of(scope, ...);
 *   doSomethingWith(t);
 * }
 * }</pre>
 *
 * Dropped tensors will be closed when GC'd, but relying on the garbage collector for cleanup is inefficient.
 *
 *
 * <p>JavaCPP properties are used to manage garbage collection, see {@link Pointer}.  Specifically
 * {@link Pointer#maxBytes} and {@link Pointer#maxPhysicalBytes}.
 *
 * <p>Instances of a Tensor are <b>not</b> thread-safe.
 */
public interface Tensor extends Shaped, AutoCloseable {

  /**
   * Allocates a tensor of a given datatype and shape.
   *
   * <p>The amount of memory to allocate is derived from the datatype and the shape of the tensor,
   * and is left uninitialized.
   *
   * @param <T> the tensor type
   * @param scope the {@link TensorScope} to create the tensor in
   * @param type the tensor type class
   * @param shape shape of the tensor
   * @return an allocated but uninitialized tensor
   * @throws IllegalArgumentException if elements of the given {@code type} are of variable length (e.g. strings)
   * @throws IllegalArgumentException if {@code shape} is totally or partially {@link Shape#hasUnknownDimension()
   * unknown}
   * @throws IllegalStateException if tensor failed to be allocated
   */
  static <T extends TType> T of(TensorScope scope, Class<T> type, Shape shape) {
    return of(scope, type, shape, -1);
  }

  /**
   * Allocates a tensor of a given datatype, shape and size.
   *
   * <p>This method is identical to {@link #of(TensorScope, Class, Shape)}, except that the final size of the
   * tensor can be explicitly set instead of computing it from the datatype and shape, which could be larger than the
   * actual space required to store the data but not smaller.
   *
   * @param <T> the tensor type
   * @param scope the {@link TensorScope} to create the tensor in
   * @param type the tensor type class
   * @param shape shape of the tensor
   * @param size size in bytes of the tensor or -1 to compute the size from the shape
   * @return an allocated but uninitialized tensor
   * @throws IllegalArgumentException if {@code size} is smaller than the minimum space required to store the tensor
   * data
   * @throws IllegalArgumentException if {@code size} is set to -1 but elements of the given {@code type} are of
   * variable length (e.g. strings)
   * @throws IllegalArgumentException if {@code shape} is totally or partially {@link Shape#hasUnknownDimension()
   * unknown}
   * @throws IllegalStateException if tensor failed to be allocated
   * @see #of(TensorScope, Class, Shape)
   */
  static <T extends TType> T of(TensorScope scope, Class<T> type, Shape shape, long size) {
    RawTensor tensor = RawTensor.allocate(scope, type, shape, size);
    try {
      return (T) tensor.asTypedTensor();
    } catch (Exception e) {
      tensor.close();
      throw e;
    }
  }

  /**
   * Allocates and initialize a tensor of a given datatype and shape.
   *
   * <p>The amount of memory to allocate is derived from the datatype and the shape of the tensor.
   * Tensor data is initialized by calling the {@code dataInitializer}, which receives in argument the value returned by
   * {@link #data()} on the allocated tensor. For example:
   *
   * <pre>{@code
   * FloatNdArray data = ...
   * try (TFloat32 t = Tensor.of(TFloat32.class, Shape.of(2, 2), data::copyTo)) {
   *   ...
   * }
   * }</pre>
   *
   * <p>If {@code dataInitializer} fails and throws an exception, the allocated tensor will be
   * automatically released before rethrowing the same exception.
   *
   * @param <T> the tensor type
   * @param scope the {@link TensorScope} to create the tensor in
   * @param type the tensor type class
   * @param shape shape of the tensor
   * @param dataInitializer method receiving accessor to the allocated tensor data for initialization
   * @return an allocated and initialized tensor
   * @throws IllegalArgumentException if elements of the given {@code type} are of variable length (e.g. strings)
   * @throws IllegalArgumentException if {@code shape} is totally or partially {@link Shape#hasUnknownDimension()
   * unknown}
   * @throws IllegalStateException if tensor failed to be allocated
   */
  static <T extends TType> T of(TensorScope scope, Class<T> type, Shape shape, Consumer<T> dataInitializer) {
    return of(scope, type, shape, -1, dataInitializer);
  }

  /**
   * Allocates a tensor of a given datatype, shape and size.
   *
   * <p>This method is identical to {@link #of(TensorScope, Class, Shape, Consumer)}, except that the final
   * size for the tensor can be explicitly set instead of being computed from the datatype and shape.
   *
   * <p>This could be useful for tensor types that stores data but also metadata in the tensor memory,
   * such as the lookup table in a tensor of strings.
   *
   * @param <T> the tensor type
   * @param scope the {@link TensorScope} to create the tensor in
   * @param type the tensor type class
   * @param shape shape of the tensor
   * @param size size in bytes of the tensor or -1 to compute the size from the shape
   * @param dataInitializer method receiving accessor to the allocated tensor data for initialization
   * @return an allocated and initialized tensor
   * @throws IllegalArgumentException if {@code size} is smaller than the minimum space required to store the tensor
   * data
   * @throws IllegalArgumentException if {@code size} is set to -1 but elements of the given {@code type} are of
   * variable length (e.g. strings)
   * @throws IllegalArgumentException if {@code shape} is totally or partially {@link Shape#hasUnknownDimension()
   * unknown}
   * @throws IllegalStateException if tensor failed to be allocated
   * @see #of(TensorScope, Class, Shape, long, Consumer)
   */
  static <T extends TType> T of(TensorScope scope, Class<T> type, Shape shape, long size, Consumer<T> dataInitializer) {
    T tensor = of(scope, type, shape, size);
    try {
      dataInitializer.accept(tensor);
      return tensor;
    } catch (Exception t) {
      tensor.close();
      throw t;
    }
  }

  /**
   * Creates a Tensor of any type from the raw data provided by the given buffer.
   *
   * <p>Data must have been encoded into {@code data} as per the specification of the TensorFlow <a
   * href="https://www.tensorflow.org/code/tensorflow/c/c_api.h">C API</a>.
   *
   * @param <T> the tensor type
   * @param scope the {@link TensorScope} to create the tensor in
   * @param type the tensor type class
   * @param shape the tensor shape.
   * @param rawData a buffer containing the tensor raw data.
   * @throws IllegalArgumentException if {@code rawData} is not large enough to contain the tensor data
   * @throws IllegalArgumentException if {@code shape} is totally or partially {@link Shape#hasUnknownDimension()
   * unknown}
   * @throws IllegalStateException if tensor failed to be allocated with the given parameters
   */
  static <T extends TType> T of(TensorScope scope, Class<T> type, Shape shape, ByteDataBuffer rawData) {
    return of(scope, type, shape, rawData.size(), t -> rawData.copyTo(t.asRawTensor().data(), rawData.size()));
  }

  /**
   * Returns the {@link DataType} of elements stored in the tensor.
   */
  DataType dataType();

  /**
   * Returns the size, in bytes, of the tensor data.
   */
  long numBytes();

  /**
   * Returns the shape of the tensor.
   */
  @Override
  Shape shape();

  /**
   * Returns a raw (untyped) representation of this tensor
   */
  RawTensor asRawTensor();

  /**
   * Release resources associated with the Tensor.
   * <p>All tensors should be closed using this method or {@link TensorScope}.
   * Memory will not leak if they aren't, but relying on the garbage collector for cleanup is not efficient.
   *
   * <p>JavaCPP properties are used to manage garbage collection, see {@link Pointer}.  Specifically
   * {@link Pointer#maxBytes} and {@link Pointer#maxPhysicalBytes}.
   *
   * <p>The Tensor object is no longer usable after {@code close} returns.
   */
  @Override
  void close();

  /**
   * Get whether this tensor has been closed.
   */
  boolean isClosed();

  /**
   * Detach this tensor from any scopes managing it.  It must be manually closed or attached to another scope.
   */
  default void detach() {
    TensorScope.detach(this);
  }

  /**
   * Returns true if this tensor is attached to a {@link TensorScope}.
   */
  boolean isAttached();
}
