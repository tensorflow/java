package org.tensorflow;

import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.types.TypeFactory;
import org.tensorflow.types.family.TType;

public abstract class AbstractTypeFactory<T extends TType> implements TypeFactory<T> {

  protected static TF_Tensor getNative(TensorHandle tensorHandle) {
    return tensorHandle.get();
  }
}
