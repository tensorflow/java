package org.tensorflow.types;

import org.tensorflow.Tensor;
import org.tensorflow.nio.nd.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;

public class TInt32Test extends NumericTypesTestBase<TInt32, Integer> {

  @Override
  Tensor<TInt32> allocateTensor(Shape shape) {
    return TInt32.tensor(shape);
  }

  @Override
  Integer valueOf(int value) {
    return value;
  }
}
