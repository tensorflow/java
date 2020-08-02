package org.tensorflow.internal.tensor;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.tensor.buffer.ByteSequenceTensorBuffer;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayout;
import org.tensorflow.ndarray.impl.dense.DenseNdArray;
import org.tensorflow.tensor.StringTensor;

public class StringTensorImpl extends DenseNdArray<String> implements StringTensor {

  @Override
  public NdArray<byte[]> asBytes() {
    return NdArrays.wrap(shape(), rawBuffer);
  }

  @Override
  public TF_Tensor nativeHandle() {
    return rawTensor.nativeHandle();
  }

  @Override
  public DataType<Tensor<String>> dataType() {
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
  public Tensor<String> setObject(String value, long... coordinates) {
    return (Tensor<String>)super.setObject(value, coordinates);
  }

  @Override
  public Tensor<String> set(NdArray<String> src, long... coordinates) {
    return (Tensor<String>)super.set(src, coordinates);
  }

  @Override
  public Tensor<String> copyTo(NdArray<String> dst) {
    return (Tensor<String>)super.copyTo(dst);
  }

  @Override
  public Tensor<String> read(DataBuffer<String> dst) {
    return (Tensor<String>)super.read(dst);
  }

  @Override
  public Tensor<String> write(DataBuffer<String> src) {
    return (Tensor<String>)super.write(src);
  }

  protected ByteSequenceTensorBuffer rawBuffer() {
    return rawBuffer;
  }

  public StringTensorImpl(
      TF_Tensor nativeHandle,
      DataType<?> dataType,
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
