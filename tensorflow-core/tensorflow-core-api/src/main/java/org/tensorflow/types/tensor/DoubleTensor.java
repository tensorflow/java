package org.tensorflow.types.tensor;

import org.tensorflow.Tensor;
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;

/**
 * A {@link Tensor} of doubles.
 */
public interface DoubleTensor extends DoubleNdArray, Tensor<Double> {

  @Override
  DoubleTensor setDouble(double value, long... coordinates);

  @Override
  DoubleTensor set(NdArray<Double> src, long... coordinates);

  @Override
  DoubleTensor setObject(Double value, long... coordinates);

  @Override
  DoubleTensor copyTo(NdArray<Double> dst);

  @Override
  DoubleTensor read(DataBuffer<Double> dst);

  @Override
  DoubleTensor read(DoubleDataBuffer dst);

  @Override
  DoubleTensor write(DataBuffer<Double> src);

  @Override
  DoubleTensor write(DoubleDataBuffer src);
}
