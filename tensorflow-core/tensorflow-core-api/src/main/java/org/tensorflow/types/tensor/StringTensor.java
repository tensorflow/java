package org.tensorflow.types.tensor;

import org.tensorflow.Tensor;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.buffer.DataBuffer;

/**
 * A {@link Tensor} of strings.
 */
public interface StringTensor extends Tensor<String> {

  /**
   * @return the tensor data as a n-dimensional array of raw byte sequences.
   */
  NdArray<byte[]> asBytes();

  @Override
  StringTensor set(NdArray<String> src, long... coordinates);

  @Override
  StringTensor setObject(String value, long... coordinates);

  @Override
  StringTensor copyTo(NdArray<String> dst);

  @Override
  StringTensor read(DataBuffer<String> dst);

  @Override
  StringTensor write(DataBuffer<String> src);
}
