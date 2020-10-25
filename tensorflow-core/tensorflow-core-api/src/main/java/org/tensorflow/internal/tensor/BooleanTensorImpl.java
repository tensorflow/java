package org.tensorflow.internal.tensor;

import org.tensorflow.AbstractTensor;
import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.BooleanNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArraySequence;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.types.tensor.BooleanTensor;

public class BooleanTensorImpl extends AbstractTensor<Boolean> implements BooleanTensor {

  private final BooleanNdArray data;

  public BooleanTensorImpl(TF_Tensor nativeHandle, Shape shape, BooleanDataBuffer buffer) {
    super(nativeHandle);
    data = NdArrays.wrap(shape, buffer);
  }

  @Override
  public boolean getBoolean(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getBoolean(coordinates);
  }

  @Override
  public BooleanTensor setBoolean(boolean value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setBoolean(value, coordinates);
    return this;
  }

  @Override
  public BooleanNdArray slice(Index... coordinates) {
    requireHandle(nativeHandle);
    return data.slice(coordinates);
  }

  @Override
  public BooleanTensor get(long... coordinates) {
    requireHandle(nativeHandle);
    data.get(coordinates);
    return this;
  }

  @Override
  public BooleanTensor set(NdArray<Boolean> src, long... coordinates) {
    requireHandle(nativeHandle);
    data.set(src, coordinates);
    return this;
  }

  @Override
  public Boolean getObject(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getObject(coordinates);
  }

  @Override
  public BooleanTensor setObject(Boolean value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setObject(value, coordinates);
    return this;
  }

  @Override
  public NdArraySequence<BooleanNdArray> elements(int dimensionIdx) {
    requireHandle(nativeHandle);
    return data.elements(dimensionIdx);
  }

  @Override
  public NdArraySequence<BooleanNdArray> scalars() {
    requireHandle(nativeHandle);
    return data.scalars();
  }

  @Override
  public BooleanTensor copyTo(NdArray<Boolean> dst) {
    requireHandle(nativeHandle);
    data.copyTo(dst);
    return this;
  }

  @Override
  public BooleanTensor read(DataBuffer<Boolean> dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public BooleanTensor read(BooleanDataBuffer dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public BooleanTensor write(DataBuffer<Boolean> src) {
    requireHandle(nativeHandle);
    data.write(src);
    return this;
  }

  @Override
  public BooleanTensor write(BooleanDataBuffer src) {
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
