package org.tensorflow.types.family;

import org.tensorflow.Tensor;
import org.tensorflow.TensorHandle;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.proto.framework.DataType;

public interface TType<T> extends NdArray<T>, Tensor {

  Class<? extends TType> type();

  TensorHandle tensorHandle();

  @Override
  default void close() {
    tensorHandle().close();
  }

  @Override
  default ByteDataBuffer rawData() {
    return tensorHandle().rawData();
  }

  @Override
  default DataType dataType() {
    return tensorHandle().dataType();
  }

  @Override
  default long numBytes() {
    return tensorHandle().numBytes();
  }
}
