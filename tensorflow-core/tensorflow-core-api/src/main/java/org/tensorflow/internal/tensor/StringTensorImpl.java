package org.tensorflow.internal.tensor;

import org.tensorflow.AbstractTensor;
import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.internal.tensor.buffer.ByteSequenceTensorBuffer;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArraySequence;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayout;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.types.tensor.StringTensor;

public class StringTensorImpl extends AbstractTensor<String> implements StringTensor {

  private final ByteSequenceTensorBuffer rawBuffer;
  private final NdArray<String> data;

  public StringTensorImpl(
      TF_Tensor nativeHandle,
      Shape shape,
      DataLayout<DataBuffer<byte[]>, String> layout,
      ByteSequenceTensorBuffer buffer
  ) {
    super(nativeHandle);
    this.rawBuffer = buffer;
    data = NdArrays.wrap(shape, layout.applyTo(buffer));
  }

  @Override
  public NdArray<byte[]> asBytes() {
    requireHandle(nativeHandle);
    return NdArrays.wrap(shape(), rawBuffer);
  }

  @Override
  public NdArraySequence<? extends NdArray<String>> elements(int dimensionIdx) {
    requireHandle(nativeHandle);
    return data.elements(dimensionIdx);
  }

  @Override
  public NdArraySequence<? extends NdArray<String>> scalars() {
    requireHandle(nativeHandle);
    return data.scalars();
  }

  @Override
  public NdArray<String> slice(Index... indices) {
    requireHandle(nativeHandle);
    return data.slice(indices);
  }

  @Override
  public NdArray<String> get(long... coordinates) {
    requireHandle(nativeHandle);
    return data.get(coordinates);
  }

  @Override
  public StringTensor set(NdArray<String> src, long... coordinates) {
    requireHandle(nativeHandle);
    data.set(src, coordinates);
    return this;
  }

  @Override
  public String getObject(long... coordinates) {
    requireHandle(nativeHandle);
    return data.getObject(coordinates);
  }

  @Override
  public StringTensor setObject(String value, long... coordinates) {
    requireHandle(nativeHandle);
    data.setObject(value, coordinates);
    return this;
  }

  @Override
  public StringTensor copyTo(NdArray<String> dst) {
    requireHandle(nativeHandle);
    data.copyTo(dst);
    return this;
  }

  @Override
  public StringTensor read(DataBuffer<String> dst) {
    requireHandle(nativeHandle);
    data.read(dst);
    return this;
  }

  @Override
  public StringTensor write(DataBuffer<String> src) {
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

  protected ByteSequenceTensorBuffer rawBuffer() {
    return rawBuffer;
  }
}
