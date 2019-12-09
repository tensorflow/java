package org.tensorflow.types;

import org.tensorflow.Tensor;
import org.tensorflow.tools.Shape;

public class TDoubleTest extends NumericTypesTestBase<TDouble, Double> {

  @Override
  Tensor<TDouble> allocateTensor(Shape shape) {
    return TDouble.ofShape(shape);
  }

  @Override
  Double valueOf(Integer value) {
    return value.doubleValue();
  }
}
