package org.tensorflow.internal.types;

import org.tensorflow.DataType;
import org.tensorflow.RawTensor;
import org.tensorflow.TensorMapper;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.impl.dense.LongDenseNdArray;
import org.tensorflow.types.TInt64;

/**
 * Maps memory of {@link org.tensorflow.proto.framework.DataType#DT_INT64} tensors
 * to a n-dimensional data space.
 */
public final class TInt64Mapper extends TensorMapper<TInt64> {

  @Override
  protected TInt64 mapDense(RawTensor tensor) {
    LongDataBuffer buffer = TensorBuffers.toLongs(nativeHandle(tensor));
    return new DenseTInt64(tensor, buffer);
  }

  private static final class DenseTInt64 extends LongDenseNdArray implements TInt64 {

    @Override
    public DataType<?> dataType() {
      return TInt64.DTYPE;
    }

    @Override
    public RawTensor asRawTensor() {
      return rawTensor;
    }

    final RawTensor rawTensor;

    DenseTInt64(RawTensor rawTensor, LongDataBuffer buffer) {
      super(buffer, rawTensor.shape());
      this.rawTensor = rawTensor;
    }
  }
}
