package org.tensorflow.types;

import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

public class Type<T extends TType> {

  public Class<T> typeClass() {
    return typeClass;
  }

  public DataType dataType() {
    return dataType;
  }

  public int byteSize() {
    return byteSize;
  }

  public boolean isVariableLength() {
    return byteSize < 0;
  }

  public TypeFactory<T> factory() {
    return factory;
  }

  Type(Class<T> typeClass, DataType dataType, int byteSize, TypeFactory<T> factory) {
    this.typeClass = typeClass;
    this.dataType = dataType;
    this.byteSize = byteSize;
    this.factory = factory;
  }

  private final Class<T> typeClass;
  private final DataType dataType;
  private final int byteSize;
  private final TypeFactory<T> factory;
}
