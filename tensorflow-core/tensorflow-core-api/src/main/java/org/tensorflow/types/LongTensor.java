package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.impl.dense.LongDenseNdArray;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.types.family.TType;

public interface LongTensor<T extends LongTensor & TType> extends LongNdArray, Tensor<T> {

  @Override
  T setLong(long value, long... coordinates);

  @Override
  T set(NdArray<Long> src, long... coordinates);

  @Override
  T setObject(Long value, long... coordinates);

  @Override
  T copyTo(NdArray<Long> dst);

  @Override
  T read(DataBuffer<Long> dst);

  @Override
  T read(LongDataBuffer dst);

  @Override
  T write(DataBuffer<Long> src);

  @Override
  T write(LongDataBuffer src);
}

class LongTensorImpl<T extends LongTensor & TType> extends LongDenseNdArray implements LongTensor<T> {

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
  public T setLong(long value, long... coordinates) {
    return (T)super.setLong(value, coordinates);
  }

  @Override
  public T setObject(Long value, long... coordinates) {
    return (T)super.setObject(value, coordinates);
  }

  @Override
  public T set(NdArray<Long> src, long... coordinates) {
    return (T)super.set(src, coordinates);
  }

  @Override
  public T copyTo(NdArray<Long> dst) {
    return (T)super.copyTo(dst);
  }

  @Override
  public T read(DataBuffer<Long> dst) {
    return (T)super.read(dst);
  }

  @Override
  public T read(LongDataBuffer dst) {
    return (T)super.read(dst);
  }

  @Override
  public T write(DataBuffer<Long> src) {
    return (T)super.write(src);
  }

  @Override
  public T write(LongDataBuffer src) {
    return (T)super.write(src);
  }

  LongTensorImpl(TF_Tensor nativeHandle, DataType<T> dataType, Shape shape, LongDataBuffer buffer) {
    super(buffer, shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
  }

  private final RawTensor rawTensor;
}
