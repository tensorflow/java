package org.tensorflow.internal.tensor;

import org.tensorflow.DataType;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.impl.dense.BooleanDenseNdArray;
import org.tensorflow.tensor.BooleanTensor;

public class BooleanTensorImpl extends BooleanDenseNdArray implements BooleanTensor {

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
  public BooleanTensor setBoolean(boolean value, long... coordinates) {
    return (BooleanTensor)super.setBoolean(value, coordinates);
  }

  @Override
  public BooleanTensor setObject(Boolean value, long... coordinates) {
    return (BooleanTensor)super.setObject(value, coordinates);
  }

  @Override
  public BooleanTensor set(NdArray<Boolean> src, long... coordinates) {
    return (BooleanTensor)super.set(src, coordinates);
  }

  @Override
  public BooleanTensor copyTo(NdArray<Boolean> dst) {
    return (BooleanTensor)super.copyTo(dst);
  }

  @Override
  public BooleanTensor read(DataBuffer<Boolean> dst) {
    return (BooleanTensor)super.read(dst);
  }

  @Override
  public BooleanTensor read(BooleanDataBuffer dst) {
    return (BooleanTensor)super.read(dst);
  }

  @Override
  public BooleanTensor write(DataBuffer<Boolean> src) {
    return (BooleanTensor)super.write(src);
  }

  @Override
  public BooleanTensor write(BooleanDataBuffer src) {
    return (BooleanTensor)super.write(src);
  }

  public BooleanTensorImpl(TF_Tensor nativeHandle, DataType<?> dataType, Shape shape, BooleanDataBuffer buffer) {
    super(buffer, shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
  }

  private final RawTensor rawTensor;
}
