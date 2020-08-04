package org.tensorflow.internal.tensor;

import org.tensorflow.AbstractTensor;
import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArraySequence;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.tensor.IntTensor;

public class IntTensorImpl extends AbstractTensor<Integer> implements IntTensor {

  private final IntNdArray data;

  public IntTensorImpl(TF_Tensor nativeHandle, DataType<?> dataType, Shape shape,
      IntDataBuffer buffer) {
    super(nativeHandle, dataType);
    data = NdArrays.wrap(shape, buffer);
  }

  @Override
  public int getInt(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getInt(coordinates);
  }

  @Override
  public IntTensor setInt(int value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setInt(value, coordinates);
    return this;
  }

  @Override
  public IntNdArray slice(Index... coordinates) {
    requireHandle(nativeHandle);
    return data.slice(coordinates);
  }

  @Override
  public IntTensor get(long... coordinates) {
    requireHandle(nativeHandle);
    data.get(coordinates);
    return this;
  }

  @Override
  public IntTensor set(NdArray<Integer> src, long... coordinates) {
    requireHandle(nativeHandle);
    data.set(src, coordinates);
    return this;
  }

  @Override
  public Integer getObject(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getObject(coordinates);
  }

  @Override
  public IntTensor setObject(Integer value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setObject(value, coordinates);
    return this;
  }

  @Override
  public NdArraySequence<IntNdArray> elements(int dimensionIdx) {
    requireHandle(nativeHandle);
    return data.elements(dimensionIdx);
  }

  @Override
  public NdArraySequence<IntNdArray> scalars() {
    requireHandle(nativeHandle);
    return data.scalars();
  }

  @Override
  public IntTensor copyTo(NdArray<Integer> dst) {
    requireHandle(nativeHandle);
    data.copyTo(dst);
    return this;
  }

  @Override
  public IntTensor read(DataBuffer<Integer> dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public IntTensor read(IntDataBuffer dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public IntTensor write(DataBuffer<Integer> src) {
    requireHandle(nativeHandle);
    data.write(src);
    return this;
  }

  @Override
  public IntTensor write(IntDataBuffer src) {
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
