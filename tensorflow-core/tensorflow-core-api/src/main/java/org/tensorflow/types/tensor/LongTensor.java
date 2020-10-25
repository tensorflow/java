package org.tensorflow.types.tensor;

import org.tensorflow.Tensor;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;

/**
 * A {@link Tensor} of longs.
 */
public interface LongTensor extends LongNdArray, Tensor<Long> {

  @Override
  LongTensor setLong(long value, long... coordinates);

  @Override
  LongTensor set(NdArray<Long> src, long... coordinates);

  @Override
  LongTensor setObject(Long value, long... coordinates);

  @Override
  LongTensor copyTo(NdArray<Long> dst);

  @Override
  LongTensor read(DataBuffer<Long> dst);

  @Override
  LongTensor read(LongDataBuffer dst);

  @Override
  LongTensor write(DataBuffer<Long> src);

  @Override
  LongTensor write(LongDataBuffer src);
}
