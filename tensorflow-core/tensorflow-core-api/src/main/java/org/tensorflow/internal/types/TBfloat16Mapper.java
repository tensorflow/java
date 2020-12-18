package org.tensorflow.internal.types;

import org.tensorflow.TensorMapper;
import org.tensorflow.DataType;
import org.tensorflow.RawTensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayouts;
import org.tensorflow.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.types.TBfloat16;

/**
 * Maps memory of {@link org.tensorflow.proto.framework.DataType#DT_BFLOAT16} tensors
 * to a n-dimensional data space.
 */
public final class TBfloat16Mapper extends TensorMapper<TBfloat16> {

  @Override
  protected TBfloat16 mapDense(RawTensor tensor) {
    FloatDataBuffer buffer = DataLayouts.BFLOAT16.applyTo(TensorBuffers.toShorts(nativeHandle(tensor)));
    return new DenseTBfloat16(tensor, buffer);
  }

  private static final class DenseTBfloat16 extends FloatDenseNdArray implements TBfloat16 {

    @Override
    public DataType<TBfloat16> dataType() {
      return TBfloat16.DTYPE;
    }

    @Override
    public RawTensor asRawTensor() {
      return rawTensor;
    }

    final RawTensor rawTensor;

    DenseTBfloat16(RawTensor rawTensor, FloatDataBuffer buffer) {
      super(buffer, rawTensor.shape());
      this.rawTensor = rawTensor;
    }
  }
}
