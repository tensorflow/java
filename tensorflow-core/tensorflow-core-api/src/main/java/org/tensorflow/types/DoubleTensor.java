package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.impl.dense.DoubleDenseNdArray;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.types.family.TType;

public interface DoubleTensor<T extends DoubleTensor & TType> extends DoubleNdArray, Tensor<T> {

  @Override
  T setDouble(double value, long... coordinates);

  @Override
  T set(NdArray<Double> src, long... coordinates);

  @Override
  T setObject(Double value, long... coordinates);

  @Override
  T copyTo(NdArray<Double> dst);

  @Override
  T read(DataBuffer<Double> dst);

  @Override
  T read(DoubleDataBuffer dst);

  @Override
  T write(DataBuffer<Double> src);

  @Override
  T write(DoubleDataBuffer src);
}

class DoubleTensorImpl<T extends DoubleTensor & TType> extends DoubleDenseNdArray implements DoubleTensor<T> {

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
  public T setDouble(double value, long... coordinates) {
    return (T)super.setDouble(value, coordinates);
  }

  @Override
  public T setObject(Double value, long... coordinates) {
    return (T)super.setObject(value, coordinates);
  }

  @Override
  public T set(NdArray<Double> src, long... coordinates) {
    return (T)super.set(src, coordinates);
  }

  @Override
  public T copyTo(NdArray<Double> dst) {
    return (T)super.copyTo(dst);
  }

  @Override
  public T read(DataBuffer<Double> dst) {
    return (T)super.read(dst);
  }

  @Override
  public T read(DoubleDataBuffer dst) {
    return (T)super.read(dst);
  }

  @Override
  public T write(DataBuffer<Double> src) {
    return (T)super.write(src);
  }

  @Override
  public T write(DoubleDataBuffer src) {
    return (T)super.write(src);
  }

  DoubleTensorImpl(TF_Tensor nativeHandle, DataType<T> dataType, Shape shape, DoubleDataBuffer buffer) {
    super(buffer, shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
  }

  private final RawTensor rawTensor;
}
