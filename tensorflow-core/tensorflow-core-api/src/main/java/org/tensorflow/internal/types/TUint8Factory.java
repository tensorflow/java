package org.tensorflow.internal.types;

import org.tensorflow.AbstractTypeFactory;
import org.tensorflow.TensorHandle;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.impl.dense.ByteDenseNdArray;
import org.tensorflow.types.TUint8;

/**
 * Factory of {@link TUint8} tensor instances
 */
public class TUint8Factory extends AbstractTypeFactory<TUint8> {

  @Override
  public TUint8 createDense(TensorHandle tensorHandle, Shape shape) {
    ByteDataBuffer buffer = TensorBuffers.toBytes(getNative(tensorHandle));
    return new TUint8Impl(tensorHandle, buffer, shape);
  }

  private static final class TUint8Impl extends ByteDenseNdArray implements TUint8 {

    @Override
    public Class<TUint8> type() {
      return TUint8.class;
    }

    @Override
    public TensorHandle handle() {
      return tensorHandle;
    }

    TUint8Impl(TensorHandle tensorHandle, ByteDataBuffer buffer, Shape shape) {
      super(buffer, shape);
      this.tensorHandle = tensorHandle;
    }

    private final TensorHandle tensorHandle;
  }
}
