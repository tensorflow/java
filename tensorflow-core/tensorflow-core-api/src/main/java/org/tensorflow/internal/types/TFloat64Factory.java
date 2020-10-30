package org.tensorflow.internal.types;

import org.tensorflow.AbstractTypeFactory;
import org.tensorflow.TensorHandle;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.impl.dense.DoubleDenseNdArray;
import org.tensorflow.types.TFloat64;

/**
 * Factory of {@link TFloat64} tensor instances
 */
public class TFloat64Factory extends AbstractTypeFactory<TFloat64> {

  @Override
  public TFloat64 createDense(TensorHandle tensorHandle, Shape shape) {
    DoubleDataBuffer buffer = TensorBuffers.toDoubles(getNative(tensorHandle));
    return new TFloat64Impl(tensorHandle, buffer, shape);
  }

  private static final class TFloat64Impl extends DoubleDenseNdArray implements TFloat64 {

    @Override
    public Class<TFloat64> type() {
      return TFloat64.class;
    }

    @Override
    public TensorHandle handle() {
      return tensorHandle;
    }

    TFloat64Impl(TensorHandle tensorHandle, DoubleDataBuffer buffer, Shape shape) {
      super(buffer, shape);
      this.tensorHandle = tensorHandle;
    }

    private final TensorHandle tensorHandle;
  }
}
