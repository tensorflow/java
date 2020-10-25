package org.tensorflow.types.tensor;

import org.tensorflow.Tensor;
import org.tensorflow.ndarray.ByteNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;

/**
 * A {@link Tensor} of bytes.
 */
public interface ByteTensor extends ByteNdArray, Tensor<Byte> {

  @Override
  ByteTensor setByte(byte value, long... coordinates);

  @Override
  ByteTensor set(NdArray<Byte> src, long... coordinates);

  @Override
  ByteTensor setObject(Byte value, long... coordinates);

  @Override
  ByteTensor copyTo(NdArray<Byte> dst);

  @Override
  ByteTensor read(DataBuffer<Byte> dst);

  @Override
  ByteTensor read(ByteDataBuffer dst);

  @Override
  ByteTensor write(DataBuffer<Byte> src);

  @Override
  ByteTensor write(ByteDataBuffer src);
}
