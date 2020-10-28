package org.tensorflow.internal.types;

import org.tensorflow.TensorHandle;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TypeFactory;

/**
 * Factory of {@link TFloat32} tensor instances
 */
public class TFloat32Factory implements TypeFactory<TFloat32> {

  @Override
  public TFloat32 createDense(TensorHandle tensorHandle, Shape shape) {
    FloatDataBuffer buffer = TensorBuffers.toFloats(tensorHandle.nativeHandle());
    return new TFloat32Impl(tensorHandle, buffer, shape);
  }

  private static final class TFloat32Impl extends FloatDenseNdArray implements TFloat32 {

    @Override
    public Class<TFloat32> type() {
      return TFloat32.class;
    }

    @Override
    public TensorHandle tensorHandle() {
      return tensorHandle;
    }

    TFloat32Impl(TensorHandle tensorHandle, FloatDataBuffer buffer, Shape shape) {
      super(buffer, shape);
      this.tensorHandle = tensorHandle;
    }

    private final TensorHandle tensorHandle;
  }
}
