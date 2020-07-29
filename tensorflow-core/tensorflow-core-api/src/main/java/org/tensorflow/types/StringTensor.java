package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.ByteSequenceTensorBuffer;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayout;
import org.tensorflow.ndarray.impl.dense.DenseNdArray;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.types.family.TType;

public interface StringTensor<T extends StringTensor & TType> extends NdArray<String>, Tensor<T> {

  /**
   * @return the tensor data as a n-dimensional array of raw byte sequences.
   */
  NdArray<byte[]> asBytes();

  @Override
  T set(NdArray<String> src, long... coordinates);

  @Override
  T setObject(String value, long... coordinates);

  @Override
  T copyTo(NdArray<String> dst);

  @Override
  T read(DataBuffer<String> dst);

  @Override
  T write(DataBuffer<String> src);
}

class StringTensorImpl<T extends StringTensor & TType> extends DenseNdArray<String> implements StringTensor<T> {

  @Override
  public NdArray<byte[]> asBytes() {
    return NdArrays.wrap(shape(), rawBuffer);
  }

  @Override
  public TF_Tensor nativeHandle() {
    return rawTensor.nativeHandle();
  }

  @Override
  public DataType<T> dataType() {
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
  public T setObject(String value, long... coordinates) {
    return (T)super.setObject(value, coordinates);
  }

  @Override
  public T set(NdArray<String> src, long... coordinates) {
    return (T)super.set(src, coordinates);
  }

  @Override
  public T copyTo(NdArray<String> dst) {
    return (T)super.copyTo(dst);
  }

  @Override
  public T read(DataBuffer<String> dst) {
    return (T)super.read(dst);
  }

  @Override
  public T write(DataBuffer<String> src) {
    return (T)super.write(src);
  }

  protected ByteSequenceTensorBuffer rawBuffer() {
    return rawBuffer;
  }

  StringTensorImpl(
      TF_Tensor nativeHandle,
      DataType<T> dataType,
      Shape shape,
      DataLayout<DataBuffer<byte[]>, String> layout,
      ByteSequenceTensorBuffer buffer
  ) {
    super(layout.applyTo(buffer), shape);
    this.rawTensor = new RawTensor(nativeHandle, dataType, shape);
    this.rawBuffer = buffer;
  }

  private final RawTensor rawTensor;
  private final ByteSequenceTensorBuffer rawBuffer;
}
