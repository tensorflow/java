package org.tensorflow.internal.tensor;

import org.tensorflow.AbstractTensor;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArraySequence;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.types.tensor.LongTensor;

public class LongTensorImpl extends AbstractTensor<Long> implements LongTensor {

  private final LongNdArray data;

  public LongTensorImpl(TF_Tensor nativeHandle, Shape shape, LongDataBuffer buffer) {
    super(nativeHandle);
    data = NdArrays.wrap(shape, buffer);
  }

  @Override
  public long getLong(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getLong(coordinates);
  }

  @Override
  public LongTensor setLong(long value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setLong(value, coordinates);
    return this;
  }

  @Override
  public LongNdArray slice(Index... coordinates) {
    requireHandle(nativeHandle);
    return data.slice(coordinates);
  }

  @Override
  public LongTensor get(long... coordinates) {
    requireHandle(nativeHandle);
    data.get(coordinates);
    return this;
  }

  @Override
  public LongTensor set(NdArray<Long> src, long... coordinates) {
    requireHandle(nativeHandle);
    data.set(src, coordinates);
    return this;
  }

  @Override
  public Long getObject(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getObject(coordinates);
  }

  @Override
  public LongTensor setObject(Long value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setObject(value, coordinates);
    return this;
  }

  @Override
  public NdArraySequence<LongNdArray> elements(int dimensionIdx) {
    requireHandle(nativeHandle);
    return data.elements(dimensionIdx);
  }

  @Override
  public NdArraySequence<LongNdArray> scalars() {
    requireHandle(nativeHandle);
    return data.scalars();
  }

  @Override
  public LongTensor copyTo(NdArray<Long> dst) {
    requireHandle(nativeHandle);
    data.copyTo(dst);
    return this;
  }

  @Override
  public LongTensor read(DataBuffer<Long> dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public LongTensor read(LongDataBuffer dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public LongTensor write(DataBuffer<Long> src) {
    requireHandle(nativeHandle);
    data.write(src);
    return this;
  }

  @Override
  public LongTensor write(LongDataBuffer src) {
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
