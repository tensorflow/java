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
import org.tensorflow.internal.tensor.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;

/**
 * A statically typed multi-dimensional array whose elements are of a type described by T.
 *
 * <p>Instances of a Tensor are <b>not</b> thread-safe.
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
 */
public interface Tensor<T> extends NdArray<T>, AutoCloseable {

  /**
   * Allocates a tensor of a given datatype and shape.
   *
   * <p>The amount of memory to allocate is derived from the datatype and the shape of the tensor.
   * Memory is left uninitialized after this method returns, so it is the responsibility of the
   * caller to initialize the tensor data before it is used, via the {@link #data()} accessor.
   * For example:
   *
   * <pre>{@code
   * FloatNdArray data = ...
   * try (Tensor<TFloat32> t = Tensor.of(TFloat32.DTYPE, Shape.of(2, 2))) {
   *   data.copyTo(t.data());
   *   ...
   * }
   * }</pre>
   *
   * @param <T> the tensor element type
   * @param dtype datatype of the tensor
   * @param shape shape of the tensor
   * @return an allocated but uninitialized tensor
   * @throws IllegalStateException if tensor failed to be allocated
   */
  static <T extends Tensor> T of(DataType<T> dtype, Shape shape) {
    return of(dtype, shape, shape.size() * dtype.byteSize());
  }

  /**
   * Allocates a tensor of a given datatype, shape and size.
   *
   * <p>This method is identical to {@link #of(DataType, Shape)}, except that the final size of the
   * tensor is explicitly set instead of computing it from the datatype and shape.
   *
   * <p>This could be useful for tensor types that stores data but also metadata in the tensor memory,
   * like {@link org.tensorflow.types.TString TString}.
   *
   * @param <T> the tensor element type
   * @param dtype datatype of the tensor
   * @param shape shape of the tensor
   * @param size size, in bytes, of the tensor
   * @return an allocated but uninitialized tensor
   * @see #of(DataType, Shape)
   * @throws IllegalArgumentException if {@code size} is smaller than the minimum space required to
   *                                  store the tensor data
   * @throws IllegalStateException if tensor failed to be allocated
   */
  static <T extends Tensor> T of(DataType<T> dtype, Shape shape, long size) {
    return Tensors.allocate(dtype, shape, size);
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
   * try (Tensor<TFloat32> t = Tensor.of(TFloat32.DTYPE, Shape.of(2, 2), data::copyTo)) {
   *   ...
   * }
   * }</pre>
   *
   * <p>If {@code dataInitializer} fails and throws an exception, the allocated tensor will be
   * automatically released before rethrowing the same exception.
   *
   * @param <T> the tensor element type
   * @param dtype datatype of the tensor
   * @param shape shape of the tensor
   * @param dataInitializer method receiving accessor to the allocated tensor data for initialization
   * @return an allocated and initialized tensor
   * @throws IllegalStateException if tensor failed to be allocated
   */
  static <T extends Tensor> T of(DataType<T> dtype, Shape shape, Consumer<T> dataInitializer) {
    return of(dtype, shape, shape.size() * dtype.byteSize(), dataInitializer);
  }

  /**
   * Allocates a tensor of a given datatype, shape and size.
   *
   * <p>This method is identical to {@link #of(DataType, Shape, Consumer)}, except that the final
   * size for the tensor is explicitly set instead of being computed from the datatype and shape.
   *
   * <p>This could be useful for tensor types that stores data but also metadata in the tensor memory,
   * such as {@link org.tensorflow.types.TString TString}.
   *
   * @param <T> the tensor element type
   * @param dtype datatype of the tensor
   * @param shape shape of the tensor
   * @param size size, in bytes, of the tensor
   * @param tensorInit method receiving accessor to the allocated tensor data for initialization
   * @return an allocated and initialized tensor
   * @see #of(DataType, Shape, long, Consumer)
   * @throws IllegalArgumentException if {@code size} is smaller than the minimum space required to
   *                                  store the tensor data
   * @throws IllegalStateException if tensor failed to be allocated
   */
  static <T extends Tensor> T of(DataType<T> dtype, Shape shape, long size, Consumer<T> tensorInit) {
    T tensor = of(dtype, shape, size);
    try {
      tensorInit.accept(tensor);
      return tensor;
    } catch (Throwable t) {
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
   * @param <T> the tensor element type
   * @param dtype the tensor element data type
   * @param shape the tensor shape.
   * @param rawData a buffer containing the tensor raw data.
   * @throws IllegalArgumentException if {@code rawData} is not large enough to contain the tensor data
   * @throws IllegalStateException if tensor failed to be allocated with the given parameters
   */
  static <T extends Tensor> T of(DataType<T> dtype, Shape shape, ByteDataBuffer rawData) {
    T tensor = of(dtype, shape, rawData.size());
    rawData.copyTo(TensorBuffers.toBytes(tensor.nativeHandle()), rawData.size());
    return tensor;
  }

  /**
   * Returns this Tensor object with the type {@code Tensor<U>}. This method is useful when given a
   * value of type {@code Tensor<?>}.
   *
   * @param dt any supported tensor data type
   * @param <U> a tensor type
   * @return a tensor of the requested data type
   * @throws IllegalArgumentException if the actual data type of this object does not match the type
   *     {@code U}.
   */
  @SuppressWarnings("unchecked")
  default <U extends Tensor> U expect(DataType<U> dt) {
    if (!dt.equals(dataType())) {
      throw new IllegalArgumentException(
          "Cannot cast from tensor of " + dataType() + " to tensor of " + dt);
    }
    return (U)this;
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

  /** Returns the {@link DataType} of elements stored in the Tensor. */
  DataType<?> dataType();

  /** Returns the size, in bytes, of the tensor data. */
  long numBytes();

  /**
   * Returns the <a href="https://www.tensorflow.org/resources/dims_types.html#shape">shape</a> of
   * the Tensor, i.e., the sizes of each dimension.
   *
   * @return shape of this tensor
   */
  Shape shape();

  /**
   * Returns the data of this tensor.
   *
   * <p>This method returns an accessor to the tensor data as an instance of {@code T}, which
   * commonly maps this data to an {@link NdArray NdArray}. Input and
   * output operations performed on the returned n-dimensional array are applied directly to the
   * tensor native memory. For example:
   *
   * <pre>{@code
   * Ops tf = Ops.create();
   * try (Tensor<TFloat32> t = TFloat32.tensorOf(Shape.of(2, 2))) {
   *   TFloat32 data = t.data();
   *
   *   StdArrays.copyTo(data, new float[][] {
   *     {1.0f, 2.0f},
   *     {3.0f, 4.0f}
   *   });
   *   assertEquals(NdArrays.vectorOf(3.0f, 4.0f), data.getFloat(1));
   *
   *   Constant<TFloat32> c = tf.constant(t);
   *   assertEquals(4.0f, c.data().getFloat(1, 1));
   * }
   * }</pre>
   *
   * <p>Please refer to the documentation of the {@link NdArray NdArray}
   * classes for more information on the various techniques to read or write data in an
   * n-dimensional space using this data structure.
   *
   * @return the tensor data mapped to an n-dimensional space
   * @throws IllegalStateException if the tensor has been closed
   * @see NdArray
   * @deprecated since tensor implements {@code NdArray}
   */
  @Deprecated
  default Tensor<T> data() {
    nativeHandle(); // make sure native handle is still valid
    return this;
  }

  /**
   * Returns the raw data of this tensor as a buffer of bytes.
   *
   * <p>Use this method to obtain a read-only serializable view of the tensor raw data and must be
   * used with care since there is no guard on the element boundaries. For regular input or output
   * operations, use {@link #data()}.
   *
   * @return the tensor raw data mapped to a read-only byte buffer
   * @throws IllegalStateException if the tensor has been closed
   */
  ByteDataBuffer rawData();

  /**
   * @return native handle to this tensor
   * @throws IllegalStateException if tensor has been closed
   */
  TF_Tensor nativeHandle();
}



