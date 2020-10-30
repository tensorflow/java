package org.tensorflow.internal.types;

import org.tensorflow.AbstractTypeFactory;
import org.tensorflow.TensorHandle;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayouts;
import org.tensorflow.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.types.TFloat16;

/**
 * Factory of {@link TFloat16} tensor instances
 */
public class TFloat16Factory extends AbstractTypeFactory<TFloat16> {

  @Override
  public TFloat16 createDense(TensorHandle tensorHandle, Shape shape) {
    FloatDataBuffer buffer = DataLayouts.FLOAT16.applyTo(TensorBuffers.toShorts(getNative(tensorHandle)));
    return new TFloat16Impl(tensorHandle, buffer, shape);
  }

  private static final class TFloat16Impl extends FloatDenseNdArray implements TFloat16 {

    @Override
    public Class<TFloat16> type() {
      return TFloat16.class;
    }

    @Override
    public TensorHandle handle() {
      return tensorHandle;
    }

    TFloat16Impl(TensorHandle tensorHandle, FloatDataBuffer buffer, Shape shape) {
      super(buffer, shape);
      this.tensorHandle = tensorHandle;
    }

    private final TensorHandle tensorHandle;
  }
}
