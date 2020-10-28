package org.tensorflow.internal.types;

import org.tensorflow.TensorHandle;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.impl.dense.LongDenseNdArray;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TypeFactory;

/**
 * Factory of {@link TInt64} tensor instances
 */
public class TInt64Factory implements TypeFactory<TInt64> {

  @Override
  public TInt64 createDense(TensorHandle tensorHandle, Shape shape) {
    LongDataBuffer buffer = TensorBuffers.toLongs(tensorHandle.nativeHandle());
    return new Int64DenseTensor(tensorHandle, buffer, shape);
  }

  private static final class Int64DenseTensor extends LongDenseNdArray implements TInt64 {

    @Override
    public Class<TInt64> type() {
      return TInt64.class;
    }

    @Override
    public TensorHandle tensorHandle() {
      return tensorHandle;
    }

    Int64DenseTensor(TensorHandle tensorHandle, LongDataBuffer buffer, Shape shape) {
      super(buffer, shape);
      this.tensorHandle = tensorHandle;
    }

    private final TensorHandle tensorHandle;
  }
}
