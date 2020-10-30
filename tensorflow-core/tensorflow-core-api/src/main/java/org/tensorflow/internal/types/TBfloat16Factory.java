package org.tensorflow.internal.types;

import org.tensorflow.AbstractTypeFactory;
import org.tensorflow.TensorHandle;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayouts;
import org.tensorflow.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.types.TBfloat16;

/**
 * Factory of {@link TBfloat16} tensor instances
 */
public class TBfloat16Factory extends AbstractTypeFactory<TBfloat16> {

  @Override
  public TBfloat16 createDense(TensorHandle tensorHandle, Shape shape) {
    FloatDataBuffer buffer = DataLayouts.BFLOAT16.applyTo(TensorBuffers.toShorts(getNative(tensorHandle)));
    return new TBfloat16Impl(tensorHandle, buffer, shape);
  }

  private static final class TBfloat16Impl extends FloatDenseNdArray implements TBfloat16 {

    @Override
    public Class<TBfloat16> type() {
      return TBfloat16.class;
    }

    @Override
    public TensorHandle handle() {
      return tensorHandle;
    }

    TBfloat16Impl(TensorHandle tensorHandle, FloatDataBuffer buffer, Shape shape) {
      super(buffer, shape);
      this.tensorHandle = tensorHandle;
    }

    private final TensorHandle tensorHandle;
  }
}
