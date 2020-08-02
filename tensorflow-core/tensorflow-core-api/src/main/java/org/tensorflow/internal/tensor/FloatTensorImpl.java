package org.tensorflow.internal.tensor;

import org.tensorflow.DataType;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.tensor.FloatTensor;

public class FloatTensorImpl extends FloatDenseNdArray implements FloatTensor {

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
  public FloatTensor setFloat(float value, long... coordinates) {
    return (FloatTensor)super.setFloat(value, coordinates);
  }

  @Override
  public FloatTensor setObject(Float value, long... coordinates) {
    return (FloatTensor)super.setObject(value, coordinates);
  }

  @Override
  public FloatTensor set(NdArray<Float> src, long... coordinates) {
    return (FloatTensor)super.set(src, coordinates);
  }

  @Override
  public FloatTensor copyTo(NdArray<Float> dst) {
    return (FloatTensor)super.copyTo(dst);
  }

  @Override
  public FloatTensor read(DataBuffer<Float> dst) {
    return (FloatTensor)super.read(dst);
  }

  @Override
  public FloatTensor read(FloatDataBuffer dst) {
    return (FloatTensor)super.read(dst);
  }

  @Override
  public FloatTensor write(DataBuffer<Float> src) {
    return (FloatTensor)super.write(src);
  }

  @Override
  public FloatTensor write(FloatDataBuffer src) {
    return (FloatTensor)super.write(src);
  }

  public FloatTensorImpl(TF_Tensor nativeHandle, DataType<?> dataType, Shape shape, FloatDataBuffer buffer) {
    super(buffer, shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
  }

  private final RawTensor rawTensor;
}
