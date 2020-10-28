package org.tensorflow.types;

import org.tensorflow.TensorHandle;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.family.TType;

public interface TypeFactory<T extends TType> {

  T createDense(TensorHandle tensorHandle, Shape shape);
}
