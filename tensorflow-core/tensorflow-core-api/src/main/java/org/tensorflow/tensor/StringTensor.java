package org.tensorflow.tensor;

import org.tensorflow.Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.buffer.DataBuffer;

public interface StringTensor extends Tensor<String> {

  /**
   * @return the tensor data as a n-dimensional array of raw byte sequences.
   */
  NdArray<byte[]> asBytes();

  @Override
  Tensor<String> set(NdArray<String> src, long... coordinates);

  @Override
  Tensor<String> setObject(String value, long... coordinates);

  @Override
  Tensor<String> copyTo(NdArray<String> dst);

  @Override
  Tensor<String> read(DataBuffer<String> dst);

  @Override
  Tensor<String> write(DataBuffer<String> src);
}

