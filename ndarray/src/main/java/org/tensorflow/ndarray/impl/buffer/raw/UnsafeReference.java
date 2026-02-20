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

package org.tensorflow.ndarray.impl.buffer.raw;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

final class UnsafeReference {

  static boolean isAvailable() {
    return UNSAFE != null;
  }

  static final Unsafe UNSAFE;

  static {
    Unsafe unsafe = null;
    try {
      Class<?> clazz = Class.forName("sun.misc.Unsafe");
      Field theUnsafe = clazz.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      Object instance = theUnsafe.get(null);
      if (instance.getClass() == clazz) {
        checkMethod(clazz, "getByte", Object.class, long.class);
        checkMethod(clazz, "putByte", Object.class, long.class, byte.class);
        checkMethod(clazz, "getShort", Object.class, long.class);
        checkMethod(clazz, "putShort", Object.class, long.class, short.class);
        checkMethod(clazz, "getInt", Object.class, long.class);
        checkMethod(clazz, "putInt", Object.class, long.class, int.class);
        checkMethod(clazz, "getLong", Object.class, long.class);
        checkMethod(clazz, "putLong", Object.class, long.class, long.class);
        checkMethod(clazz, "getFloat", Object.class, long.class);
        checkMethod(clazz, "putFloat", Object.class, long.class, float.class);
        checkMethod(clazz, "getDouble", Object.class, long.class);
        checkMethod(clazz, "putDouble", Object.class, long.class, double.class);
        checkMethod(clazz, "getBoolean", Object.class, long.class);
        checkMethod(clazz, "putBoolean", Object.class, long.class, boolean.class);
        checkMethod(clazz, "copyMemory", Object.class, long.class, Object.class, long.class, long.class);
        checkMethod(clazz, "arrayBaseOffset", Class.class);
        checkMethod(clazz, "arrayIndexScale", Class.class);

        unsafe = (Unsafe) instance;
      }
    } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | SecurityException | IllegalAccessException | ClassCastException ex) {
      // Do nothing, keep unsafe as null
    }
    UNSAFE = unsafe;
  }

  /**
   * Validate that this Unsafe instance exposes this method
   *
   * ErrorProne does not like that we do nothing with the returned method... but there is nothing to do with it, so disable the check
   */
  @SuppressWarnings("ReturnValueIgnored")
  private static void checkMethod(Class<?> unsafeClass, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
    unsafeClass.getDeclaredMethod(methodName, parameterTypes);
  }
}
