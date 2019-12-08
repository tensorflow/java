package org.tensorflow.types;

import org.tensorflow.Tensor;
import org.tensorflow.util.Shape;

public class TUInt8Test extends NumericTypesTestBase<TUInt8, Byte> {

  @Override
  Tensor<TUInt8> allocateTensor(Shape shape) {
    return TUInt8.ofShape(shape);
  }

  @Override
  Byte valueOf(Integer value) {
    return value.byteValue();
  }
}
