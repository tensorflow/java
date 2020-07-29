package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.ByteNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.impl.dense.ByteDenseNdArray;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.types.family.TType;

public interface ByteTensor<T extends ByteTensor & TType> extends ByteNdArray, Tensor<T> {

  @Override
  T setByte(byte value, long... coordinates);

  @Override
  T set(NdArray<Byte> src, long... coordinates);

  @Override
  T setObject(Byte value, long... coordinates);

  @Override
  T copyTo(NdArray<Byte> dst);

  @Override
  T read(DataBuffer<Byte> dst);

  @Override
  T read(ByteDataBuffer dst);

  @Override
  T write(DataBuffer<Byte> src);

  @Override
  T write(ByteDataBuffer src);
}

class ByteTensorImpl<T extends ByteTensor & TType> extends ByteDenseNdArray implements ByteTensor<T> {

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
  public T setByte(byte value, long... coordinates) {
    return (T)super.setByte(value, coordinates);
  }

  @Override
  public T setObject(Byte value, long... coordinates) {
    return (T)super.setObject(value, coordinates);
  }

  @Override
  public T set(NdArray<Byte> src, long... coordinates) {
    return (T)super.set(src, coordinates);
  }

  @Override
  public T copyTo(NdArray<Byte> dst) {
    return (T)super.copyTo(dst);
  }

  @Override
  public T read(DataBuffer<Byte> dst) {
    return (T)super.read(dst);
  }

  @Override
  public T read(ByteDataBuffer dst) {
    return (T)super.read(dst);
  }

  @Override
  public T write(DataBuffer<Byte> src) {
    return (T)super.write(src);
  }

  @Override
  public T write(ByteDataBuffer src) {
    return (T)super.write(src);
  }

  ByteTensorImpl(TF_Tensor nativeHandle, DataType<T> dataType, Shape shape, ByteDataBuffer buffer) {
    super(buffer, shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
  }

  private final RawTensor rawTensor;
}
