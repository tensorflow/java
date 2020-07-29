package org.tensorflow;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_Dim;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_NumDims;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorType;

import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.family.TType;

final class Tensors {

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
    DataType<? extends Tensor> dataType = DataTypes.fromNativeCode(dtype(handle));
    Shape shape = Shape.of(shape(handle));
    try (PointerScope scope = new PointerScope()) {
      scope.attach(handle);
      return dataType.instantiateTensor(handle, shape);
    }
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
