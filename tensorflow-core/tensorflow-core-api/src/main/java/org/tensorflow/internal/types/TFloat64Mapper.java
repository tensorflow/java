package org.tensorflow.internal.types;

import org.tensorflow.DataType;
import org.tensorflow.RawTensor;
import org.tensorflow.TensorMapper;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.impl.dense.DoubleDenseNdArray;
import org.tensorflow.types.TFloat64;

/**
 * Maps memory of {@link org.tensorflow.proto.framework.DataType#DT_DOUBLE} tensors
 * to a n-dimensional data space.
 */
public final class TFloat64Mapper extends TensorMapper<TFloat64> {

  @Override
  protected TFloat64 mapDense(RawTensor tensor) {
    DoubleDataBuffer buffer = TensorBuffers.toDoubles(nativeHandle(tensor));
    return new DenseTFloat64(tensor, buffer);
  }

  private static final class DenseTFloat64 extends DoubleDenseNdArray implements TFloat64 {

    @Override
    public DataType<?> dataType() {
      return TFloat64.DTYPE;
    }

    @Override
    public RawTensor asRawTensor() {
      return rawTensor;
    }

    final RawTensor rawTensor;

    DenseTFloat64(RawTensor rawTensor, DoubleDataBuffer buffer) {
      super(buffer, rawTensor.shape());
      this.rawTensor = rawTensor;
    }
  }
}
