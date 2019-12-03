package org.tensorflow.types;

import org.tensorflow.Tensor;
import org.tensorflow.util.ndarray.Shape;

public class TInt64Test extends NumericTypesTestBase<TInt64, Long> {

  @Override
  Tensor<TInt64> allocateTensor(Shape shape) {
    return TInt64.ofShape(shape);
  }

  @Override
  Long valueOf(Integer value) {
    return value.longValue();
  }
}
