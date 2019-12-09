package org.tensorflow.types;

import org.tensorflow.Tensor;
import org.tensorflow.tools.Shape;

public class TFloatTest extends NumericTypesTestBase<TFloat, Float> {

  @Override
  Tensor<TFloat> allocateTensor(Shape shape) {
    return TFloat.ofShape(shape);
  }

  @Override
  Float valueOf(Integer value) {
    return value.floatValue();
  }
}
