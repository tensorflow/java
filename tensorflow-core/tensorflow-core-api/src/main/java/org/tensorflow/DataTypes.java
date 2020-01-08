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

import java.util.HashMap;
import java.util.Map;
import org.tensorflow.types.TBFloat16;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUInt8;

/**
 * Utility class for working with {@link DataType} objects.
 */
final class DataTypes {

  /**
   * Find a data type from the type code returned by the native layer (C API).
   *
   * <p>Only data types registered via {@link #register(DataType)} can be resolved.
   *
   * @param nativeCode native code
   * @return data type for this code
   * @throws IllegalArgumentException if the code matches no registered data type
   */
  static DataType<?> fromNativeCode(int nativeCode) {
    DataType<?> dataType = DATA_TYPE_REGISTRY.get(nativeCode);
    if (dataType == null) {
      throw new IllegalArgumentException(
          "DataType " + nativeCode + " is not recognized in Java (version " + TensorFlow.version() + ")");
    }
    return dataType;
  }

  private static final Map<Integer, DataType<?>> DATA_TYPE_REGISTRY = new HashMap<>();

  static {
    register(TBool.DTYPE);
    register(TFloat64.DTYPE);
    register(TFloat32.DTYPE);
    register(TFloat16.DTYPE);
    register(TInt32.DTYPE);
    register(TInt64.DTYPE);
    register(TString.DTYPE);
    register(TUInt8.DTYPE);
    register(TBFloat16.DTYPE);
  }

  // TODO (karllessard): Right now this method is private but we might want to expose it
  //      to allow user to register custom data types?
  private static void register(DataType<?> dataType) {
    DATA_TYPE_REGISTRY.put(dataType.nativeCode(), dataType);
  }
}
