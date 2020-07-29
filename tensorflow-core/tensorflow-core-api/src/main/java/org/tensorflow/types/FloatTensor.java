package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.types.family.TType;

public interface FloatTensor<T extends FloatTensor & TType> extends FloatNdArray, Tensor<T> {

  @Override
  T setFloat(float value, long... coordinates);

  @Override
  T set(NdArray<Float> src, long... coordinates);

  @Override
  T setObject(Float value, long... coordinates);

  @Override
  T copyTo(NdArray<Float> dst);

  @Override
  T read(DataBuffer<Float> dst);

  @Override
  T read(FloatDataBuffer dst);

  @Override
  T write(DataBuffer<Float> src);

  @Override
  T write(FloatDataBuffer src);
}

class FloatTensorImpl<T extends FloatTensor & TType> extends FloatDenseNdArray implements FloatTensor<T> {

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
  public T setFloat(float value, long... coordinates) {
    return (T)super.setFloat(value, coordinates);
  }

  @Override
  public T setObject(Float value, long... coordinates) {
    return (T)super.setObject(value, coordinates);
  }

  @Override
  public T set(NdArray<Float> src, long... coordinates) {
    return (T)super.set(src, coordinates);
  }

  @Override
  public T copyTo(NdArray<Float> dst) {
    return (T)super.copyTo(dst);
  }

  @Override
  public T read(DataBuffer<Float> dst) {
    return (T)super.read(dst);
  }

  @Override
  public T read(FloatDataBuffer dst) {
    return (T)super.read(dst);
  }

  @Override
  public T write(DataBuffer<Float> src) {
    return (T)super.write(src);
  }

  @Override
  public T write(FloatDataBuffer src) {
    return (T)super.write(src);
  }

  FloatTensorImpl(TF_Tensor nativeHandle, DataType<T> dataType, Shape shape, FloatDataBuffer buffer) {
    super(buffer, shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
  }

  private final RawTensor rawTensor;
}
