package org.tensorflow.internal.tensor;

import org.tensorflow.AbstractTensor;
import org.tensorflow.DataType;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArraySequence;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.tensor.FloatTensor;

public class FloatTensorImpl extends AbstractTensor<Float> implements FloatTensor {

  private final FloatNdArray data;

  public FloatTensorImpl(TF_Tensor nativeHandle, DataType<?> dataType, Shape shape,
      FloatDataBuffer buffer) {
    super(nativeHandle, dataType);
    data = NdArrays.wrap(shape, buffer);
  }

  @Override
  public float getFloat(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getFloat(coordinates);
  }

  @Override
  public FloatTensor setFloat(float value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setFloat(value, coordinates);
    return this;
  }

  @Override
  public FloatNdArray slice(Index... coordinates) {
    requireHandle(nativeHandle);
    return data.slice(coordinates);
  }

  @Override
  public FloatTensor get(long... coordinates) {
    requireHandle(nativeHandle);
    data.get(coordinates);
    return this;
  }

  @Override
  public FloatTensor set(NdArray<Float> src, long... coordinates) {
    requireHandle(nativeHandle);
    data.set(src, coordinates);
    return this;
  }

  @Override
  public Float getObject(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getObject(coordinates);
  }

  @Override
  public FloatTensor setObject(Float value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setObject(value, coordinates);
    return this;
  }

  @Override
  public NdArraySequence<FloatNdArray> elements(int dimensionIdx) {
    requireHandle(nativeHandle);
    return data.elements(dimensionIdx);
  }

  @Override
  public NdArraySequence<FloatNdArray> scalars() {
    requireHandle(nativeHandle);
    return data.scalars();
  }

  @Override
  public FloatTensor copyTo(NdArray<Float> dst) {
    requireHandle(nativeHandle);
    data.copyTo(dst);
    return this;
  }

  @Override
  public FloatTensor read(DataBuffer<Float> dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public FloatTensor read(FloatDataBuffer dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public FloatTensor write(DataBuffer<Float> src) {
    requireHandle(nativeHandle);
    data.write(src);
    return this;
  }

  @Override
  public FloatTensor write(FloatDataBuffer src) {
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
