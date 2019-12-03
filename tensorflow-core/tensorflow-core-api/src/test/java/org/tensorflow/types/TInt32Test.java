package org.tensorflow.types;

import org.tensorflow.Tensor;
import org.tensorflow.util.ndarray.Shape;

public class TInt32Test extends NumericTypesTestBase<TInt32, Integer> {

  @Override
  Tensor<TInt32> allocateTensor(Shape shape) {
    return TInt32.ofShape(shape);
  }

  @Override
  Integer valueOf(Integer value) {
    return value;
  }
}
