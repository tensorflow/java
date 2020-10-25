package org.tensorflow;

import java.lang.reflect.Constructor;
import org.tensorflow.exceptions.TensorFlowException;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.family.TType;

class TensorType {

  Class<?> typeClass() {
    return typeClass;
  }

  DataType dataType() {
    return dataType;
  }

  boolean isVariableLength() {
    return dataType.byteSize < 0;
  }

  <T extends TType> T newInstance(TF_Tensor nativeHandle, Shape shape) {
    try {
      return (T) implConstructor.newInstance(nativeHandle, shape);
    } catch (ReflectiveOperationException e) {
      throw new TensorFlowException("Fail to instantiate tensor: " + e.getMessage(), e);
    }
  }

  TensorType(Class<?> typeClass, DataType dataType, Constructor<?> implConstructor) {
    this.typeClass = typeClass;
    this.dataType = dataType;
    this.implConstructor = implConstructor;
  }

  private final Class<?> typeClass;
  private final DataType dataType;
  private final Constructor<?> implConstructor;
}
