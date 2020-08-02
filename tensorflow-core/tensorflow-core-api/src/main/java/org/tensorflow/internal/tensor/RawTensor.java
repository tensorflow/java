package org.tensorflow.internal.tensor;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorByteSize;

import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.DataType;
import org.tensorflow.internal.tensor.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;

class RawTensor<T> {

  public TF_Tensor nativeHandle() {
    if (nativeHandle.isNull()) {
      throw new IllegalStateException("Tensor has been already released");
    }
    return nativeHandle;
  }

  public DataType<?> dataType() {
    return dtype;
  }

  public Shape shape() {
    return shape;
  }

  public long numBytes() {
    return TF_TensorByteSize(nativeHandle);
  }

  public ByteDataBuffer rawData() {
    return TensorBuffers.toBytes(nativeHandle, true);
  }

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
  private final DataType<?> dtype;
  private final Shape shape;

  RawTensor(TF_Tensor nativeHandle, DataType<?> dtype, Shape shape) {
    this.nativeHandle = nativeHandle;
    this.dtype = dtype;
    this.shape = shape;
    this.tensorScope.attach(nativeHandle);
  }
}
