package org.tensorflow.types.family;

import org.tensorflow.Tensor;
import org.tensorflow.TensorHandle;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.proto.framework.DataType;

public interface TType extends Tensor {

  Class<? extends TType> type();

  @Override
  default void close() {
    handle().close();
  }

  @Override
  default ByteDataBuffer rawData() {
    return handle().rawData();
  }

  @Override
  default DataType dataType() {
    return handle().dataType();
  }

  @Override
  default long numBytes() {
    return handle().numBytes();
  }
}
