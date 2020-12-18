package org.tensorflow.internal.types;

import org.tensorflow.DataType;
import org.tensorflow.RawTensor;
import org.tensorflow.TensorMapper;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayouts;
import org.tensorflow.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.types.TFloat16;

/**
 * Maps memory of {@link org.tensorflow.proto.framework.DataType#DT_HALF} tensors
 * to a n-dimensional data space.
 */
public final class TFloat16Mapper extends TensorMapper<TFloat16> {

  @Override
  protected TFloat16 mapDense(RawTensor tensor) {
    FloatDataBuffer buffer = DataLayouts.FLOAT16.applyTo(TensorBuffers.toShorts(nativeHandle(tensor)));
    return new DenseTFloat16(tensor, buffer);
  }

  private static final class DenseTFloat16 extends FloatDenseNdArray implements TFloat16 {

    @Override
    public DataType<?> dataType() {
      return TFloat16.DTYPE;
    }

    @Override
    public RawTensor asRawTensor() {
      return rawTensor;
    }

    final RawTensor rawTensor;

    DenseTFloat16(RawTensor rawTensor, FloatDataBuffer buffer) {
      super(buffer, rawTensor.shape());
      this.rawTensor = rawTensor;
    }
  }
}
