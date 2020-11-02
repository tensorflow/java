/*
 *  Copyright 2019 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

package org.tensorflow.types;

import java.util.HashMap;
import java.util.Map;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.annotation.TensorType;
import org.tensorflow.types.family.TType;

/**
 * Utility class for working with {@link DataType} objects.
 */
public final class TypeRegistry {

  /**
   * Find a type registration from a data type
   *
   * @param dataType data type
   * @return type registered to this code
   * @throws IllegalArgumentException if the code matches no registered data type
   */
  public static <T extends TType> Type<T> find(DataType dataType) {
    Type<?> entry = TYPES_BY_CODE.get(dataType.getNumber());
    if (entry == null) {
      throw new IllegalArgumentException("No type has been registered for datatype " + dataType);
    }
    return (Type<T>)entry;
  }

  /**
   * Find a type registration from a type class
   *
   * @param typeClass class implementing {@link Tensor}
   * @return type registration
   * @throws IllegalArgumentException if the code matches no registered data type
   */
  public static <T extends TType> Type<T> find(Class<T> typeClass) {
    Type<?> entry = TYPES_BY_CLASS.get(typeClass);
    if (entry == null) {
      throw new IllegalArgumentException("Class \"" + typeClass.getName() + "\" is not a valid datatype class");
    }
    return (Type<T>)entry;
  }

  private static final Map<Integer, Type<?>> TYPES_BY_CODE = new HashMap<>();
  private static final Map<Class<? extends TType>, Type<?>> TYPES_BY_CLASS = new HashMap<>();

  private static <T extends TType> void register(Class<T> typeClass) {
    TensorType typeAnnot = typeClass.getDeclaredAnnotation(TensorType.class);
    if (typeAnnot == null) {
      throw new IllegalArgumentException("Class \"" + typeClass.getName() + "\" must be annotated "
          + "with @TensorType to be registered as a tensor type");
    }
    TypeFactory<T> factory;
    try {
      factory = typeAnnot.factory().newInstance();
    } catch (ReflectiveOperationException e) {
      throw new IllegalArgumentException("Class \"" + typeClass.getName() + "\" must have a public "
          + "parameter-less constructor to be used as a tensor type factory");
    }
    Type<T> type = new Type<T>(typeClass, typeAnnot.dataType(), typeAnnot.byteSize(), factory);
    TYPES_BY_CLASS.put(typeClass, type);

    // If more than one tensor type is mapped to a given native code, the last registered will
    // have priority. This way, we can allow user to register their own classes to map tensors
    // of a given data type.
    TYPES_BY_CODE.put(type.dataType().getNumber(), type);
    TYPES_BY_CODE.put(type.dataType().getNumber() + 100, type);
  }

  static {
    register(TBool.class);
    register(TFloat64.class);
    register(TFloat32.class);
    register(TFloat16.class);
    register(TInt32.class);
    register(TInt64.class);
    register(TString.class);
    register(TUint8.class);
    register(TBfloat16.class);
  }
}