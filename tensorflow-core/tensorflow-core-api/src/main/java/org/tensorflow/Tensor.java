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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.Shaped;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * A statically typed multi-dimensional array.
 *
 * <p>There are two categories of tensors in TensorFlow Java: {@link TType typed tensors} and {@link
 * RawTensor raw tensors}. The former maps the tensor native memory to an n-dimensional typed data
 * space, allowing direct I/O operations from the JVM, while the latter is only a reference to a
 * native tensor allowing basic operations and flat data access.
 *
 * <p><b>WARNING:</b> Resources consumed by the Tensor object <b>must</b> be explicitly freed by
 * invoking the {@link #close()} method when the object is no longer needed. For example, using a
 * try-with-resources block:
 *
 * <pre>{@code
 * try (Tensor t = Tensor.of(...)) {
 *   doSomethingWith(t);
 * }
 * }</pre>
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
   * @param type the tensor type class
   * @param shape shape of the tensor
   * @return an allocated but uninitialized tensor
   * @throws IllegalArgumentException if elements of the given {@code type} are of variable length
   *     (e.g. strings)
   * @throws IllegalArgumentException if {@code shape} is totally or partially {@link
   *     Shape#hasUnknownDimension() unknown}
   * @throws IllegalStateException if tensor failed to be allocated
   */
  static <T extends TType> T of(Class<T> type, Shape shape) {
    return of(type, shape, -1);
  }

  /**
   * Allocates a tensor of a given datatype, shape and size.
   *
   * <p>This method is identical to {@link #of(Class, Shape)}, except that the final size of the
   * tensor can be explicitly set instead of computing it from the datatype and shape, which could
   * be larger than the actual space required to store the data but not smaller.
   *
   * @param <T> the tensor type
   * @param type the tensor type class
   * @param shape shape of the tensor
   * @param size size in bytes of the tensor or -1 to compute the size from the shape
   * @return an allocated but uninitialized tensor
   * @see #of(Class, Shape)
   * @throws IllegalArgumentException if {@code size} is smaller than the minimum space required to
   *     store the tensor data
   * @throws IllegalArgumentException if {@code size} is set to -1 but elements of the given {@code
   *     type} are of variable length (e.g. strings)
   * @throws IllegalArgumentException if {@code shape} is totally or partially {@link
   *     Shape#hasUnknownDimension() unknown}
   * @throws IllegalStateException if tensor failed to be allocated
   */
  static <T extends TType> T of(Class<T> type, Shape shape, long size) {
    RawTensor tensor = RawTensor.allocate(type, shape, size);
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
   * Tensor data is initialized by calling the {@code dataInitializer}, which receives in argument
   * the value returned by {@link #data()} on the allocated tensor. For example:
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
   * @param type the tensor type class
   * @param shape shape of the tensor
   * @param dataInitializer method receiving accessor to the allocated tensor data for
   *     initialization
   * @return an allocated and initialized tensor
   * @throws IllegalArgumentException if elements of the given {@code type} are of variable length
   *     (e.g. strings)
   * @throws IllegalArgumentException if {@code shape} is totally or partially {@link
   *     Shape#hasUnknownDimension() unknown}
   * @throws IllegalStateException if tensor failed to be allocated
   */
  static <T extends TType> T of(Class<T> type, Shape shape, Consumer<T> dataInitializer) {
    return of(type, shape, -1, dataInitializer);
  }

  /**
   * Allocates a tensor of a given datatype, shape and size.
   *
   * <p>This method is identical to {@link #of(Class, Shape, Consumer)}, except that the final size
   * for the tensor can be explicitly set instead of being computed from the datatype and shape.
   *
   * <p>This could be useful for tensor types that stores data but also metadata in the tensor
   * memory, such as the lookup table in a tensor of strings.
   *
   * @param <T> the tensor type
   * @param type the tensor type class
   * @param shape shape of the tensor
   * @param size size in bytes of the tensor or -1 to compute the size from the shape
   * @param dataInitializer method receiving accessor to the allocated tensor data for
   *     initialization
   * @return an allocated and initialized tensor
   * @see #of(Class, Shape, long, Consumer)
   * @throws IllegalArgumentException if {@code size} is smaller than the minimum space required to
   *     store the tensor data
   * @throws IllegalArgumentException if {@code size} is set to -1 but elements of the given {@code
   *     type} are of variable length (e.g. strings)
   * @throws IllegalArgumentException if {@code shape} is totally or partially {@link
   *     Shape#hasUnknownDimension() unknown}
   * @throws IllegalStateException if tensor failed to be allocated
   */
  static <T extends TType> T of(
      Class<T> type, Shape shape, long size, Consumer<T> dataInitializer) {
    T tensor = of(type, shape, size);
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
   * @param type the tensor type class
   * @param shape the tensor shape.
   * @param rawData a buffer containing the tensor raw data.
   * @throws IllegalArgumentException if {@code rawData} is not large enough to contain the tensor
   *     data
   * @throws IllegalArgumentException if {@code shape} is totally or partially {@link
   *     Shape#hasUnknownDimension() unknown}
   * @throws IllegalStateException if tensor failed to be allocated with the given parameters
   */
  static <T extends TType> T of(Class<T> type, Shape shape, ByteDataBuffer rawData) {
    return of(
        type, shape, rawData.size(), t -> rawData.copyTo(t.asRawTensor().data(), rawData.size()));
  }

  /** Returns the {@link DataType} of elements stored in the tensor. */
  DataType dataType();

  /** Returns the size, in bytes, of the tensor data. */
  long numBytes();

  /** Returns the shape of the tensor. */
  @Override
  Shape shape();

  /**
   * Returns a raw (untyped) representation of this tensor
   *
   * @throws UnsupportedOperationException if this tensor is composed of other tensors, such as
   *     {@link SparseTensor sparse tensors}.
   */
  RawTensor asRawTensor();

  /**
   * Check if this tensor is sparse or not.
   *
   * <p>When this methods retuns {@code true}, the tensor could be cast to a {@link SparseTensor
   * SparseTensor<T>} to access its <i>indices</i>, <i>values</i> and <i>denseShape</i> tensors.
   *
   * @return true if this tensor is a sparse
   */
  default boolean isSparse() {
    return false;
  }

  /**
   * Release resources associated with the Tensor.
   *
   * <p><b>WARNING:</b>This must be invoked for all tensors that were not been produced by an eager
   * operation or memory will be leaked.
   *
   * <p>The Tensor object is no longer usable after {@code close} returns.
   */
  @Override
  void close();
}
