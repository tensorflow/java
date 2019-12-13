package org.tensorflow.tools.buffer.impl.raw;

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
        // Validate that this Unsafe instance exposes all methods we need
        clazz.getDeclaredMethod("getByte", Object.class, long.class);
        clazz.getDeclaredMethod("putByte", Object.class, long.class, byte.class);
        clazz.getDeclaredMethod("getShort", Object.class, long.class);
        clazz.getDeclaredMethod("putShort", Object.class, long.class, short.class);
        clazz.getDeclaredMethod("getInt", Object.class, long.class);
        clazz.getDeclaredMethod("putInt", Object.class, long.class, int.class);
        clazz.getDeclaredMethod("getLong", Object.class, long.class);
        clazz.getDeclaredMethod("putLong", Object.class, long.class, long.class);
        clazz.getDeclaredMethod("getFloat", Object.class, long.class);
        clazz.getDeclaredMethod("putFloat", Object.class, long.class, float.class);
        clazz.getDeclaredMethod("getDouble", Object.class, long.class);
        clazz.getDeclaredMethod("putDouble", Object.class, long.class, double.class);
        clazz.getDeclaredMethod("getBoolean", Object.class, long.class);
        clazz.getDeclaredMethod("putBoolean", Object.class, long.class, boolean.class);
        clazz.getDeclaredMethod("copyMemory", Object.class, long.class, Object.class, long.class, long.class);
        clazz.getDeclaredMethod("arrayBaseOffset", Class.class);
        clazz.getDeclaredMethod("arrayIndexScale", Class.class);
        unsafe = (Unsafe) instance;
      }
    } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | SecurityException | IllegalAccessException | ClassCastException ex) {
      // Do nothing, keep unsafe as null
    }
    UNSAFE = unsafe;
  }
}
