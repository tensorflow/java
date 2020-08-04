package org.tensorflow;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_Dim;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NumDims;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorType;

import java.util.function.Consumer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.internal.tensor.buffer.TensorBuffers;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.types.family.TType;

public final class Tensors {

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
  public static <T extends TType> T of(DataType<T> dtype, Shape shape) {
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
  public static <T extends TType> T of(DataType<T> dtype, Shape shape, long size) {
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
  public static <T extends TType> T of(DataType<T> dtype, Shape shape, Consumer<T> dataInitializer) {
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
  public static <T extends TType> T of(DataType<T> dtype, Shape shape, long size, Consumer<T> tensorInit) {
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
  public static <T extends TType> T of(DataType<T> dtype, Shape shape, ByteDataBuffer rawData) {
    T tensor = of(dtype, shape, rawData.size());
    rawData.copyTo(TensorBuffers.toBytes(((AbstractTensor)tensor).nativeHandle()), rawData.size());
    return tensor;
  }

  static <T extends TType> T allocate(DataType<T> dataType, Shape shape, long size) {
    // Minimum requirements for datatypes of variable length cannot be verified in a relevant way so
    // we only validate them for fixed length datatypes
    if (!dataType.isVariableLength() && shape.size() * dataType.byteSize() > size) {
      throw new IllegalArgumentException("Tensor size is not large enough to contain all scalar values");
    }
    TF_Tensor nativeHandle = allocate(dataType.nativeCode(), shape.asArray(), size);
    try (PointerScope scope = new PointerScope()) {
      scope.attach(nativeHandle);
      return dataType.instantiateTensor(nativeHandle, shape);
    }
  }

  /**
   * Create a Tensor object from a handle to the C TF_Tensor object.
   *
   * <p>Takes ownership of the handle.
   */
  static Tensor<?> fromHandle(TF_Tensor handle) {
    DataType<?> dataType = DataTypes.fromNativeCode(dtype(handle));
    Shape shape = Shape.of(shape(handle));
    return dataType.instantiateTensor(handle, shape);
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
}
