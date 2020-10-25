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

package org.tensorflow;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TType;

/**
 * Utility class for working with {@link DataType} objects.
 */
final class TensorTypes {

  /**
   * Find a type registration from the code returned by the native layer (C API).
   *
   * @param nativeCode native type code
   * @return type registered to this code
   * @throws IllegalArgumentException if the code matches no registered data type
   */
  static TensorType find(int nativeCode) {
    TensorType entry = TYPES_BY_CODE.get(nativeCode);
    if (entry == null) {
      throw new IllegalArgumentException(
          "DataType " + nativeCode + " is not recognized in Java (version " + TensorFlow.version() + ")");
    }
    return entry;
  }

  /**
   * Find a type registration.
   *
   * @param typeClass class implementing {@link TType}
   * @return type registration
   * @throws IllegalArgumentException if the code matches no registered data type
   */
  static TensorType find(Class<? extends TType> typeClass) {
    TensorType entry = TYPES_BY_CLASS.get(typeClass);
    if (entry == null) {
      throw new IllegalArgumentException("Class \"" + typeClass.getName() + "\" is not a valid datatype class");
    }
    return entry;
  }

  static int numberOf(Class<? extends TType> typeClass) {
    return find(typeClass).dataType().number;
  }

  private static final Map<Integer, TensorType> TYPES_BY_CODE = new HashMap<>();
  private static final Map<Class<? extends TType>, TensorType> TYPES_BY_CLASS = new HashMap<>();

  private static void register(Class<? extends TType> typeClass) {
    org.tensorflow.types.annotation.TensorType typeAnnot =
        typeClass.getDeclaredAnnotation(org.tensorflow.types.annotation.TensorType.class);
    if (typeAnnot == null) {
      throw new IllegalArgumentException("Class \"" + typeClass.getName() + "\" must be annotated "
          + "with @TensorType to be registered as a tensor type");
    }
    Constructor<?> implConstructor;
    try {
      implConstructor = typeAnnot.impl().getConstructor(TF_Tensor.class, Shape.class);
    } catch (NoSuchMethodException e) {
      throw new IllegalArgumentException("Class \"" + typeClass.getName() + "\" must have a constructor "
          + "accepting a native `TF_Tensor` handle and a `Shape` to be implement as a tensor type");
    }
    TensorType type = new TensorType(typeClass, typeAnnot.dataType(), implConstructor);
    TYPES_BY_CLASS.put(typeClass, type);

    // If more than one tensor type is mapped to a given native code, the last registered will
    // have priority. This way, we can allow user to register their own classes to map tensors
    // of a given data type.
    TYPES_BY_CODE.put(type.dataType().number, type);
    TYPES_BY_CODE.put(type.dataType().number + 100, type);
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