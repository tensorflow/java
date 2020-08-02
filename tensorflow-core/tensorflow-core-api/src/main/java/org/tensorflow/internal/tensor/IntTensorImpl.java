package org.tensorflow.internal.tensor;

import org.tensorflow.DataType;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.impl.dense.IntDenseNdArray;
import org.tensorflow.tensor.IntTensor;

public class IntTensorImpl extends IntDenseNdArray implements IntTensor {

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
  public IntTensor setInt(int value, long... coordinates) {
    return (IntTensor)super.setInt(value, coordinates);
  }

  @Override
  public IntTensor setObject(Integer value, long... coordinates) {
    return (IntTensor)super.setObject(value, coordinates);
  }

  @Override
  public IntTensor set(NdArray<Integer> src, long... coordinates) {
    return (IntTensor)super.set(src, coordinates);
  }

  @Override
  public IntTensor copyTo(NdArray<Integer> dst) {
    return (IntTensor)super.copyTo(dst);
  }

  @Override
  public IntTensor read(DataBuffer<Integer> dst) {
    return (IntTensor)super.read(dst);
  }

  @Override
  public IntTensor read(IntDataBuffer dst) {
    return (IntTensor)super.read(dst);
  }

  @Override
  public IntTensor write(DataBuffer<Integer> src) {
    return (IntTensor)super.write(src);
  }

  @Override
  public IntTensor write(IntDataBuffer src) {
    return (IntTensor)super.write(src);
  }

  public IntTensorImpl(TF_Tensor nativeHandle, DataType<?> dataType, Shape shape, IntDataBuffer buffer) {
    super(buffer, shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
  }

  private final RawTensor rawTensor;
}
