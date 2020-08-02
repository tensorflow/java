package org.tensorflow.internal.tensor;

import org.tensorflow.DataType;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.impl.dense.ByteDenseNdArray;
import org.tensorflow.tensor.ByteTensor;

public class ByteTensorImpl extends ByteDenseNdArray implements ByteTensor {

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
  public ByteTensor setByte(byte value, long... coordinates) {
    return (ByteTensor)super.setByte(value, coordinates);
  }

  @Override
  public ByteTensor setObject(Byte value, long... coordinates) {
    return (ByteTensor)super.setObject(value, coordinates);
  }

  @Override
  public ByteTensor set(NdArray<Byte> src, long... coordinates) {
    return (ByteTensor)super.set(src, coordinates);
  }

  @Override
  public ByteTensor copyTo(NdArray<Byte> dst) {
    return (ByteTensor)super.copyTo(dst);
  }

  @Override
  public ByteTensor read(DataBuffer<Byte> dst) {
    return (ByteTensor)super.read(dst);
  }

  @Override
  public ByteTensor read(ByteDataBuffer dst) {
    return (ByteTensor)super.read(dst);
  }

  @Override
  public ByteTensor write(DataBuffer<Byte> src) {
    return (ByteTensor)super.write(src);
  }

  @Override
  public ByteTensor write(ByteDataBuffer src) {
    return (ByteTensor)super.write(src);
  }

  public ByteTensorImpl(TF_Tensor nativeHandle, DataType<?> dataType, Shape shape, ByteDataBuffer buffer) {
    super(buffer, shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
  }

  private final RawTensor rawTensor;
}
