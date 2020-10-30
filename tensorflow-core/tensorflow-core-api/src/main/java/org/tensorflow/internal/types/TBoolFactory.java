package org.tensorflow.internal.types;

import org.tensorflow.AbstractTypeFactory;
import org.tensorflow.TensorHandle;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.impl.dense.BooleanDenseNdArray;
import org.tensorflow.types.TBool;

/**
 * Factory of {@link TBool} tensor instances
 */
public class TBoolFactory extends AbstractTypeFactory<TBool> {

  @Override
  public TBool createDense(TensorHandle tensorHandle, Shape shape) {
    BooleanDataBuffer buffer = TensorBuffers.toBooleans(getNative(tensorHandle));
    return new TBoolImpl(tensorHandle, buffer, shape);
  }

  private static final class TBoolImpl extends BooleanDenseNdArray implements TBool {

    @Override
    public Class<TBool> type() {
      return TBool.class;
    }

    @Override
    public TensorHandle handle() {
      return tensorHandle;
    }

    TBoolImpl(TensorHandle tensorHandle, BooleanDataBuffer buffer, Shape shape) {
      super(buffer, shape);
      this.tensorHandle = tensorHandle;
    }

    private final TensorHandle tensorHandle;
  }
}
