package org.tensorflow.internal.types;

import org.tensorflow.TensorHandle;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.impl.dense.ByteDenseNdArray;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.TypeFactory;

/**
 * Factory of {@link TUint8} tensor instances
 */
public class TUint8Factory implements TypeFactory<TUint8> {

  @Override
  public TUint8 createDense(TensorHandle tensorHandle, Shape shape) {
    ByteDataBuffer buffer = TensorBuffers.toBytes(tensorHandle.nativeHandle());
    return new TUint8Impl(tensorHandle, buffer, shape);
  }

  private static final class TUint8Impl extends ByteDenseNdArray implements TUint8 {

    @Override
    public Class<TUint8> type() {
      return TUint8.class;
    }

    @Override
    public TensorHandle tensorHandle() {
      return tensorHandle;
    }

    TUint8Impl(TensorHandle tensorHandle, ByteDataBuffer buffer, Shape shape) {
      super(buffer, shape);
      this.tensorHandle = tensorHandle;
    }

    private final TensorHandle tensorHandle;
  }
}
