package org.tensorflow.internal.tensor;

import org.tensorflow.AbstractTensor;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArraySequence;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.types.tensor.DoubleTensor;

public class DoubleTensorImpl extends AbstractTensor<Double> implements DoubleTensor {

  private final DoubleNdArray data;

  public DoubleTensorImpl(TF_Tensor nativeHandle, Shape shape, DoubleDataBuffer buffer) {
    super(nativeHandle);
    data = NdArrays.wrap(shape, buffer);
  }

  @Override
  public double getDouble(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getDouble(coordinates);
  }

  @Override
  public DoubleTensor setDouble(double value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setDouble(value, coordinates);
    return this;
  }

  @Override
  public DoubleNdArray slice(Index... coordinates) {
    requireHandle(nativeHandle);
    return data.slice(coordinates);
  }

  @Override
  public DoubleTensor get(long... coordinates) {
    requireHandle(nativeHandle);
    data.get(coordinates);
    return this;
  }

  @Override
  public DoubleTensor set(NdArray<Double> src, long... coordinates) {
    requireHandle(nativeHandle);
    data.set(src, coordinates);
    return this;
  }

  @Override
  public Double getObject(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getObject(coordinates);
  }

  @Override
  public DoubleTensor setObject(Double value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setObject(value, coordinates);
    return this;
  }

  @Override
  public NdArraySequence<DoubleNdArray> elements(int dimensionIdx) {
    requireHandle(nativeHandle);
    return data.elements(dimensionIdx);
  }

  @Override
  public NdArraySequence<DoubleNdArray> scalars() {
    requireHandle(nativeHandle);
    return data.scalars();
  }

  @Override
  public DoubleTensor copyTo(NdArray<Double> dst) {
    requireHandle(nativeHandle);
    data.copyTo(dst);
    return this;
  }

  @Override
  public DoubleTensor read(DataBuffer<Double> dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public DoubleTensor read(DoubleDataBuffer dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public DoubleTensor write(DataBuffer<Double> src) {
    requireHandle(nativeHandle);
    data.write(src);
    return this;
  }

  @Override
  public DoubleTensor write(DoubleDataBuffer src) {
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
