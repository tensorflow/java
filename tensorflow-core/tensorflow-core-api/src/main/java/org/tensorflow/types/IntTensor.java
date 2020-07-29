package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.impl.dense.IntDenseNdArray;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.types.family.TType;

public interface IntTensor<T extends IntTensor & TType> extends IntNdArray, Tensor<T> {

  @Override
  T setInt(int value, long... coordinates);

  @Override
  T set(NdArray<Integer> src, long... coordinates);

  @Override
  T setObject(Integer value, long... coordinates);

  @Override
  T copyTo(NdArray<Integer> dst);

  @Override
  T read(DataBuffer<Integer> dst);

  @Override
  T read(IntDataBuffer dst);

  @Override
  T write(DataBuffer<Integer> src);

  @Override
  T write(IntDataBuffer src);
}

class IntTensorImpl<T extends IntTensor & TType> extends IntDenseNdArray implements IntTensor<T> {

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
  public T setInt(int value, long... coordinates) {
    return (T)super.setInt(value, coordinates);
  }

  @Override
  public T setObject(Integer value, long... coordinates) {
    return (T)super.setObject(value, coordinates);
  }

  @Override
  public T set(NdArray<Integer> src, long... coordinates) {
    return (T)super.set(src, coordinates);
  }

  @Override
  public T copyTo(NdArray<Integer> dst) {
    return (T)super.copyTo(dst);
  }

  @Override
  public T read(DataBuffer<Integer> dst) {
    return (T)super.read(dst);
  }

  @Override
  public T read(IntDataBuffer dst) {
    return (T)super.read(dst);
  }

  @Override
  public T write(DataBuffer<Integer> src) {
    return (T)super.write(src);
  }

  @Override
  public T write(IntDataBuffer src) {
    return (T)super.write(src);
  }

  IntTensorImpl(TF_Tensor nativeHandle, DataType<T> dataType, Shape shape, IntDataBuffer buffer) {
    super(buffer, shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
  }

  private final RawTensor rawTensor;
}
