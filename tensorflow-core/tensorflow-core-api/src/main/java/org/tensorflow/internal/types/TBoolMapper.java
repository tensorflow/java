package org.tensorflow.internal.types;

import org.tensorflow.DataType;
import org.tensorflow.RawTensor;
import org.tensorflow.TensorMapper;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.impl.dense.BooleanDenseNdArray;
import org.tensorflow.types.TBool;

/**
 * Maps memory of {@link org.tensorflow.proto.framework.DataType#DT_BOOL} tensors
 * to a n-dimensional data space.
 */
public final class TBoolMapper extends TensorMapper<TBool> {

  @Override
  protected TBool mapDense(RawTensor tensor) {
    BooleanDataBuffer buffer = TensorBuffers.toBooleans(nativeHandle(tensor));
    return new DenseTBool(tensor, buffer);
  }

  private static final class DenseTBool extends BooleanDenseNdArray implements TBool {

    @Override
    public DataType<TBool> dataType() {
      return TBool.DTYPE;
    }

    @Override
    public RawTensor asRawTensor() {
      return rawTensor;
    }

    final RawTensor rawTensor;

    DenseTBool(RawTensor rawTensor, BooleanDataBuffer buffer) {
      super(buffer, rawTensor.shape());
      this.rawTensor = rawTensor;
    }
  }
}
