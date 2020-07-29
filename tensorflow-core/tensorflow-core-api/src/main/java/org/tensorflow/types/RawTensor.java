package org.tensorflow.types;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorByteSize;

import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.types.family.TType;

class RawTensor<T extends TType> implements Tensor<T> {

  @Override
  public TF_Tensor nativeHandle() {
    if (nativeHandle.isNull()) {
      throw new IllegalStateException("Tensor has been already released");
    }
    return nativeHandle;
  }

  @Override
  public DataType<T> dataType() {
    return dtype;
  }

  @Override
  public Shape shape() {
    return shape;
  }

  @Override
  public long numBytes() {
    return TF_TensorByteSize(nativeHandle);
  }

  @Override
  public ByteDataBuffer rawData() {
    return TensorBuffers.toBytes(nativeHandle, true);
  }

  @Override
  public void close() {
    tensorScope.close();
  }

  /** Returns a string describing the type and shape of the Tensor. */
  @Override
  public String toString() {
    return String.format("%s tensor with shape %s", dataType().toString(), shape());
  }

  private final PointerScope tensorScope = new PointerScope();
  private final TF_Tensor nativeHandle;
  private final DataType<T> dtype;
  private final Shape shape;

  RawTensor(TF_Tensor nativeHandle, DataType<T> dtype, Shape shape) {
    this.nativeHandle = nativeHandle;
    this.dtype = dtype;
    this.shape = shape;
    this.tensorScope.attach(nativeHandle);
  }
}
