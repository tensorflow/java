package org.tensorflow.util.buffer.impl.raw;

public final class UnsafeMemoryHandle {

  static UnsafeMemoryHandle of(UnsafeReference unsafe, byte[] array) {
    return fromArray(unsafe, array, array.length);
  }

  static UnsafeMemoryHandle of(UnsafeReference unsafe, boolean[] array) {
    return fromArray(unsafe, array, array.length);
  }

  static UnsafeMemoryHandle of(UnsafeReference unsafe, short[] array) {
    return fromArray(unsafe, array, array.length);
  }

  static UnsafeMemoryHandle of(UnsafeReference unsafe, int[] array) {
    return fromArray(unsafe, array, array.length);
  }

  static UnsafeMemoryHandle of(UnsafeReference unsafe, float[] array) {
    return fromArray(unsafe, array, array.length);
  }

  static UnsafeMemoryHandle of(UnsafeReference unsafe, double[] array) {
    return fromArray(unsafe, array, array.length);
  }

  static UnsafeMemoryHandle of(UnsafeReference unsafe, long[] array) {
    return fromArray(unsafe, array, array.length);
  }

  static UnsafeMemoryHandle of(UnsafeReference unsafe, long address, long byteSize, long scale) {
    return new UnsafeMemoryHandle(unsafe, null, address, byteSize, scale);
  }

  final UnsafeReference unsafe;

  long size() {
    return byteSize / scale;
  }

  byte getByte(long index) {
    return unsafe.instance.getByte(object, align(index));
  }

  void setByte(byte value, long index) {
    unsafe.instance.putByte(object, align(index), value);
  }

  boolean getBoolean(long index) {
    return unsafe.instance.getBoolean(object, align(index));
  }

  void setBoolean(boolean value, long index) {
    unsafe.instance.putBoolean(object, align(index), value);
  }

  short getShort(long index) {
    return unsafe.instance.getShort(object, align(index));
  }

  void setShort(short value, long index) {
    unsafe.instance.putShort(object, align(index), value);
  }

  int getInt(long index) {
    return unsafe.instance.getInt(object, align(index));
  }

  void setInt(int value, long index) {
    unsafe.instance.putInt(object, align(index), value);
  }

  float getFloat(long index) {
    return unsafe.instance.getFloat(object, align(index));
  }

  void setFloat(float value, long index) {
    unsafe.instance.putFloat(object, align(index), value);
  }

  double getDouble(long index) {
    return unsafe.instance.getDouble(object, align(index));
  }

  void setDouble(double value, long index) {
    unsafe.instance.putDouble(object, align(index), value);
  }

  long getLong(long index) {
    return unsafe.instance.getLong(object, align(index));
  }

  void setLong(long value, long index) {
    unsafe.instance.putLong(object, align(index), value);
  }

  void copyTo(UnsafeMemoryHandle memory, long length) {
    unsafe.instance.copyMemory(object, byteOffset, memory.object, memory.byteOffset, length * scale);
  }

  UnsafeMemoryHandle offset(long index) {
    long offset = scale(index);
    return new UnsafeMemoryHandle(unsafe, object, this.byteOffset + offset, byteSize - offset, scale);
  }

  UnsafeMemoryHandle narrow(long size) {
    return new UnsafeMemoryHandle(unsafe, object, byteOffset, scale(size), scale);
  }

  private static UnsafeMemoryHandle fromArray(UnsafeReference unsafe, Object array, int length) {
    long byteOffset = unsafe.instance.arrayBaseOffset(array.getClass());
    long scale = unsafe.instance.arrayIndexScale(array.getClass());
    return new UnsafeMemoryHandle(unsafe, array, byteOffset, length * scale, scale);
  }

  private final Object object;
  private final long byteOffset;
  private final long byteSize;
  private final long scale;

  private UnsafeMemoryHandle(UnsafeReference unsafe, Object object, long byteOffset, long byteSize, long scale) {
    this.unsafe = unsafe;
    this.object = object;
    this.byteOffset = byteOffset;
    this.byteSize = byteSize;
    this.scale = scale;
  }

  private long align(long index) {
    return byteOffset + index * scale;
  }

  private long scale(long value) {
    return value * scale;
  }
}
