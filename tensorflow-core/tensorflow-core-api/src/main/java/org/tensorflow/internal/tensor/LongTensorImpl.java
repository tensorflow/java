package org.tensorflow.internal.tensor;

import org.tensorflow.DataType;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.impl.dense.LongDenseNdArray;
import org.tensorflow.tensor.LongTensor;

public class LongTensorImpl extends LongDenseNdArray implements LongTensor {

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
  public LongTensor setLong(long value, long... coordinates) {
    return (LongTensor)super.setLong(value, coordinates);
  }

  @Override
  public LongTensor setObject(Long value, long... coordinates) {
    return (LongTensor)super.setObject(value, coordinates);
  }

  @Override
  public LongTensor set(NdArray<Long> src, long... coordinates) {
    return (LongTensor)super.set(src, coordinates);
  }

  @Override
  public LongTensor copyTo(NdArray<Long> dst) {
    return (LongTensor)super.copyTo(dst);
  }

  @Override
  public LongTensor read(DataBuffer<Long> dst) {
    return (LongTensor)super.read(dst);
  }

  @Override
  public LongTensor read(LongDataBuffer dst) {
    return (LongTensor)super.read(dst);
  }

  @Override
  public LongTensor write(DataBuffer<Long> src) {
    return (LongTensor)super.write(src);
  }

  @Override
  public LongTensor write(LongDataBuffer src) {
    return (LongTensor)super.write(src);
  }

  public LongTensorImpl(TF_Tensor nativeHandle, DataType<?> dataType, Shape shape, LongDataBuffer buffer) {
    super(buffer, shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
  }

  private final RawTensor rawTensor;
}
