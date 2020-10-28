package org.tensorflow.internal.types;

import org.tensorflow.TensorHandle;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.impl.dense.IntDenseNdArray;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TypeFactory;

/**
 * Factory of {@link TInt32} tensor instances
 */
public class TInt32Factory implements TypeFactory<TInt32> {

  @Override
  public TInt32 createDense(TensorHandle tensorHandle, Shape shape) {
    IntDataBuffer buffer = TensorBuffers.toInts(tensorHandle.nativeHandle());
    return new TInt32Impl(tensorHandle, buffer, shape);
  }

  private static final class TInt32Impl extends IntDenseNdArray implements TInt32 {

    @Override
    public Class<TInt32> type() {
      return TInt32.class;
    }

    @Override
    public TensorHandle tensorHandle() {
      return tensorHandle;
    }

    TInt32Impl(TensorHandle tensorHandle, IntDataBuffer buffer, Shape shape) {
      super(buffer, shape);
      this.tensorHandle = tensorHandle;
    }

    private final TensorHandle tensorHandle;
  }
}
