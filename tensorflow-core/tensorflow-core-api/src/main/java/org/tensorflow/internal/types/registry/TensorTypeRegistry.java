/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
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
package org.tensorflow.internal.types.registry;

import java.util.HashMap;
import java.util.Map;
import org.tensorflow.TensorMapper;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.annotation.TensorType;
import org.tensorflow.types.family.TType;

/**
 * Repository of all registered tensor types.
 */
public final class TensorTypeRegistry {

  /**
   * Find registered information about a tensor type from its equivalent data type
   *
   * @param dataType data type
   * @return type registered information
   * @throws IllegalArgumentException if no tensor type for this data type has been registered
   */
  public static <T extends TType> TensorTypeInfo<T> find(DataType dataType) {
    TensorTypeInfo<?> typeInfo = TYPES_BY_CODE.get(dataType.getNumber());
    if (typeInfo == null) {
      throw new IllegalArgumentException("No tensor type has been registered for data type " + dataType);
    }
    return (TensorTypeInfo<T>)typeInfo;
  }

  /**
   * Find registered information about a tensor type from its class
   *
   * @param type class implementing {@link TType}
   * @return type registered information
   * @throws IllegalArgumentException if the provided class has not been registered as a tensor type
   */
  public static <T extends TType> TensorTypeInfo<T> find(Class<T> type) {
    TensorTypeInfo<?> typeInfo = TYPES_BY_CLASS.get(type);
    if (typeInfo == null) {
      throw new IllegalArgumentException("Class \"" + type.getName() + "\" is not registered as a tensor type");
    }
    return (TensorTypeInfo<T>)typeInfo;
  }

  private static final Map<Integer, TensorTypeInfo<?>> TYPES_BY_CODE = new HashMap<>();
  private static final Map<Class<? extends TType>, TensorTypeInfo<?>> TYPES_BY_CLASS = new HashMap<>();

  private static <T extends TType> void register(Class<T> type) {
    TensorType typeAnnot = type.getDeclaredAnnotation(TensorType.class);
    if (typeAnnot == null) {
      throw new IllegalArgumentException("Class \"" + type.getName() + "\" must be annotated "
          + "with @TensorType to be registered as a tensor type");
    }
    TensorMapper<T> mapper;
    try {
      mapper = (TensorMapper<T>)typeAnnot.mapperClass().newInstance();
    } catch (ReflectiveOperationException e) {
      throw new IllegalArgumentException("Class \"" + type.getName() + "\" must have a public "
          + "parameter-less constructor to be used as a tensor mapper");
    }
    TensorTypeInfo<T> typeInfo = new TensorTypeInfo<>(type, typeAnnot.dataType(), typeAnnot.byteSize(), mapper);
    TYPES_BY_CLASS.put(type, typeInfo);

    // If more than one tensor type is mapped to a given native code, the last registered will
    // have priority. This way, we can allow user to register their own classes to map tensors
    // of a given data type.
    TYPES_BY_CODE.put(typeInfo.dataType().getNumber(), typeInfo);
    TYPES_BY_CODE.put(typeInfo.dataType().getNumber() + 100, typeInfo);
  }

  static {
    // TODO (karllessard) scan and registered automatically all annotated tensors types
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
