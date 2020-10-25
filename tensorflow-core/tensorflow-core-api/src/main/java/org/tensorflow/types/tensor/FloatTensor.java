package org.tensorflow.types.tensor;

import org.tensorflow.Tensor;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;

/**
 * A {@link Tensor} of floats.
 */
public interface FloatTensor extends FloatNdArray, Tensor<Float> {

  @Override
  FloatTensor setFloat(float value, long... coordinates);

  @Override
  FloatTensor set(NdArray<Float> src, long... coordinates);

  @Override
  FloatTensor setObject(Float value, long... coordinates);

  @Override
  FloatTensor copyTo(NdArray<Float> dst);

  @Override
  FloatTensor read(DataBuffer<Float> dst);

  @Override
  FloatTensor read(FloatDataBuffer dst);

  @Override
  FloatTensor write(DataBuffer<Float> src);

  @Override
  FloatTensor write(FloatDataBuffer src);
}
