package org.tensorflow.internal.tensor;

import org.tensorflow.DataType;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.impl.dense.DoubleDenseNdArray;
import org.tensorflow.tensor.DoubleTensor;

public class DoubleTensorImpl extends DoubleDenseNdArray implements DoubleTensor {

  @Override
  public TF_Tensor nativeHandle() {
    return rawTensor.nativeHandle();
  }

  @Override
  public DataType<?> dataType() {
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
  public DoubleTensor setDouble(double value, long... coordinates) {
    return (DoubleTensor)super.setDouble(value, coordinates);
  }

  @Override
  public DoubleTensor setObject(Double value, long... coordinates) {
    return (DoubleTensor)super.setObject(value, coordinates);
  }

  @Override
  public DoubleTensor set(NdArray<Double> src, long... coordinates) {
    return (DoubleTensor)super.set(src, coordinates);
  }

  @Override
  public DoubleTensor copyTo(NdArray<Double> dst) {
    return (DoubleTensor)super.copyTo(dst);
  }

  @Override
  public DoubleTensor read(DataBuffer<Double> dst) {
    return (DoubleTensor)super.read(dst);
  }

  @Override
  public DoubleTensor read(DoubleDataBuffer dst) {
    return (DoubleTensor)super.read(dst);
  }

  @Override
  public DoubleTensor write(DataBuffer<Double> src) {
    return (DoubleTensor)super.write(src);
  }

  @Override
  public DoubleTensor write(DoubleDataBuffer src) {
    return (DoubleTensor)super.write(src);
  }

  public DoubleTensorImpl(TF_Tensor nativeHandle, DataType<?> dataType, Shape shape, DoubleDataBuffer buffer) {
    super(buffer, shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
  }

  private final RawTensor rawTensor;
}
