package org.tensorflow.internal.types;

import org.tensorflow.TensorHandle;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.impl.dense.DoubleDenseNdArray;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TypeFactory;

/**
 * Factory of {@link TFloat64} tensor instances
 */
public class TFloat64Factory implements TypeFactory<TFloat64> {

  @Override
  public TFloat64 createDense(TensorHandle tensorHandle, Shape shape) {
    DoubleDataBuffer buffer = TensorBuffers.toDoubles(tensorHandle.nativeHandle());
    return new TFloat64Impl(tensorHandle, buffer, shape);
  }

  private static final class TFloat64Impl extends DoubleDenseNdArray implements TFloat64 {

    @Override
    public Class<TFloat64> type() {
      return TFloat64.class;
    }

    @Override
    public TensorHandle tensorHandle() {
      return tensorHandle;
    }

    TFloat64Impl(TensorHandle tensorHandle, DoubleDataBuffer buffer, Shape shape) {
      super(buffer, shape);
      this.tensorHandle = tensorHandle;
    }

    private final TensorHandle tensorHandle;
  }
}
