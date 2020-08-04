package org.tensorflow.internal.tensor;

import org.tensorflow.AbstractTensor;
import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.ByteNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArraySequence;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.tensor.ByteTensor;

public class ByteTensorImpl extends AbstractTensor<Byte> implements ByteTensor {

  private final ByteNdArray data;

  public ByteTensorImpl(TF_Tensor nativeHandle, DataType<?> dataType, Shape shape,
      ByteDataBuffer buffer) {
    super(nativeHandle, dataType);
    data = NdArrays.wrap(shape, buffer);
  }

  @Override
  public byte getByte(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getByte(coordinates);
  }

  @Override
  public ByteTensor setByte(byte value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setByte(value, coordinates);
    return this;
  }

  @Override
  public ByteNdArray slice(Index... coordinates) {
    requireHandle(nativeHandle);
    return data.slice(coordinates);
  }

  @Override
  public ByteTensor get(long... coordinates) {
    requireHandle(nativeHandle);
    data.get(coordinates);
    return this;
  }

  @Override
  public ByteTensor set(NdArray<Byte> src, long... coordinates) {
    requireHandle(nativeHandle);
    data.set(src, coordinates);
    return this;
  }

  @Override
  public Byte getObject(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getObject(coordinates);
  }

  @Override
  public ByteTensor setObject(Byte value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setObject(value, coordinates);
    return this;
  }

  @Override
  public NdArraySequence<ByteNdArray> elements(int dimensionIdx) {
    requireHandle(nativeHandle);
    return data.elements(dimensionIdx);
  }

  @Override
  public NdArraySequence<ByteNdArray> scalars() {
    requireHandle(nativeHandle);
    return data.scalars();
  }

  @Override
  public ByteTensor copyTo(NdArray<Byte> dst) {
    requireHandle(nativeHandle);
    data.copyTo(dst);
    return this;
  }

  @Override
  public ByteTensor read(DataBuffer<Byte> dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public ByteTensor read(ByteDataBuffer dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public ByteTensor write(DataBuffer<Byte> src) {
    requireHandle(nativeHandle);
    data.write(src);
    return this;
  }

  @Override
  public ByteTensor write(ByteDataBuffer src) {
    requireHandle(nativeHandle);
    data.write(src);
    return this;
  }

  @Override
  public Shape shape() {
    requireHandle(nativeHandle);
    return data.shape();
  }

  @Override
  public int rank() {
    requireHandle(nativeHandle);
    return data.rank();
  }

  @Override
  public long size() {
    requireHandle(nativeHandle);
    return data.size();
  }

  @Override
  public boolean equals(Object obj) {
    requireHandle(nativeHandle);
    return data.equals(obj);
  }
}
