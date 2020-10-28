package org.tensorflow.internal.types;

import org.tensorflow.TensorHandle;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.impl.dense.BooleanDenseNdArray;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TypeFactory;

/**
 * Factory of {@link TBool} tensor instances
 */
public class TBoolFactory implements TypeFactory<TBool> {

  @Override
  public TBool createDense(TensorHandle tensorHandle, Shape shape) {
    BooleanDataBuffer buffer = TensorBuffers.toBooleans(tensorHandle.nativeHandle());
    return new TBoolImpl(tensorHandle, buffer, shape);
  }

  private static final class TBoolImpl extends BooleanDenseNdArray implements TBool {

    @Override
    public Class<TBool> type() {
      return TBool.class;
    }

    @Override
    public TensorHandle tensorHandle() {
      return tensorHandle;
    }

    TBoolImpl(TensorHandle tensorHandle, BooleanDataBuffer buffer, Shape shape) {
      super(buffer, shape);
      this.tensorHandle = tensorHandle;
    }

    private final TensorHandle tensorHandle;
  }
}
