package org.tensorflow.util.buffer.impl.raw;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * Holds a valid reference to an instance of sun.misc.Unsafe.
 */
public final class UnsafeReference {

  /**
   * Creates a reference to {@code unsafe}
   *
   * The reference can be created only if the instance exposes the methods required by
   * this library, otherwise an exception is thrown
   *
   * @param unsafe instance of {@code Unsafe}
   * @return a valid reference to {@code unsafe}
   * @throws IllegalArgumentException if {@code unsafe} instance does not meet requirements for this library
   */
  public static UnsafeReference from(Object unsafe) {
    try {
      // Validate that this Unsafe instance exposes all methods we need
      Class<?> clazz = unsafe.getClass();
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
      return new UnsafeReference((Unsafe)unsafe);

    } catch (NoSuchMethodException | SecurityException | ClassCastException ex) {
      throw new IllegalArgumentException(ex);
    }
  }

  /**
   * @return true if a valid reference to {@code Unsafe} can be used for internal purposes
   */
  public static boolean isAvailableInternally() {
    return INTERNAL != null;
  }

  final Unsafe instance;

  /**
   * @return a valid reference to an {@code Unsafe} instance (do not expose publicly!)
   */
  static UnsafeReference get() {
    if (INTERNAL == null) {
      throw new RuntimeException("No valid reference to an instance of 'sun.misc.Unsafe'");
    }
    return INTERNAL;
  }

  // Reference to Unsafe only for internal purposes
  private static final UnsafeReference INTERNAL;

  static {
    UnsafeReference internalRef = null;
    try {
      Class<?> clazz = Class.forName("sun.misc.Unsafe");
      Field theUnsafe = clazz.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      internalRef = from(theUnsafe.get(null));
    } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
      // Do nothing, internal reference will be left as null
    }
    INTERNAL = internalRef;
  }

  private UnsafeReference(Unsafe instance) {
    this.instance = instance;
  }
}
