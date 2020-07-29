package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.BooleanNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.impl.dense.BooleanDenseNdArray;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.types.family.TType;

public interface BooleanTensor<T extends BooleanTensor & TType> extends BooleanNdArray, Tensor<T> {

  @Override
  T setBoolean(boolean value, long... coordinates);

  @Override
  T set(NdArray<Boolean> src, long... coordinates);

  @Override
  T setObject(Boolean value, long... coordinates);

  @Override
  T copyTo(NdArray<Boolean> dst);

  @Override
  T read(DataBuffer<Boolean> dst);

  @Override
  T read(BooleanDataBuffer dst);

  @Override
  T write(DataBuffer<Boolean> src);

  @Override
  T write(BooleanDataBuffer src);
}

class BooleanTensorImpl<T extends BooleanTensor & TType> extends BooleanDenseNdArray implements BooleanTensor<T> {

  @Override
  public TF_Tensor nativeHandle() {
    return rawTensor.nativeHandle();
  }

  @Override
  public DataType<T> dataType() {
    return rawTensor.dataType();
  }

  @Override
  public Shape shape() {
    return rawTensor.shape();
  }

  @Override
  public long numBytes() {
    return rawTensor.numBytes();
  }

  @Override
  public ByteDataBuffer rawData() {
    return rawTensor.rawData();
  }

  @Override
  public void close() {
    rawTensor.close();
  }

  @Override
  public String toString() {
    return rawTensor.toString();
  }

  @Override
  public T setBoolean(boolean value, long... coordinates) {
    return (T)super.setBoolean(value, coordinates);
  }

  @Override
  public T setObject(Boolean value, long... coordinates) {
    return (T)super.setObject(value, coordinates);
  }

  @Override
  public T set(NdArray<Boolean> src, long... coordinates) {
    return (T)super.set(src, coordinates);
  }

  @Override
  public T copyTo(NdArray<Boolean> dst) {
    return (T)super.copyTo(dst);
  }

  @Override
  public T read(DataBuffer<Boolean> dst) {
    return (T)super.read(dst);
  }

  @Override
  public T read(BooleanDataBuffer dst) {
    return (T)super.read(dst);
  }

  @Override
  public T write(DataBuffer<Boolean> src) {
    return (T)super.write(src);
  }

  @Override
  public T write(BooleanDataBuffer src) {
    return (T)super.write(src);
  }

  BooleanTensorImpl(TF_Tensor nativeHandle, DataType<T> dataType, Shape shape, BooleanDataBuffer buffer) {
    super(buffer, shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
  }

  private final RawTensor rawTensor;
}
