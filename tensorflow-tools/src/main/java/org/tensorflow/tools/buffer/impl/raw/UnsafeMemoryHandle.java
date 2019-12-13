package org.tensorflow.tools.buffer.impl.raw;

final class UnsafeMemoryHandle {

  static UnsafeMemoryHandle fromArray(Object array, int length) {
    long byteOffset = UnsafeReference.UNSAFE.arrayBaseOffset(array.getClass());
    long scale = UnsafeReference.UNSAFE.arrayIndexScale(array.getClass());
    return new UnsafeMemoryHandle(array, byteOffset, length * scale, scale);
  }

  static UnsafeMemoryHandle fromAddress(long address, long byteSize, long scale) {
    return new UnsafeMemoryHandle(null, address, byteSize, scale);
  }

  long size() {
    return byteSize / scale;
  }

  byte getByte(long index) {
    return UnsafeReference.UNSAFE.getByte(object, align(index));
  }

  void setByte(byte value, long index) {
    UnsafeReference.UNSAFE.putByte(object, align(index), value);
  }

  boolean getBoolean(long index) {
    return UnsafeReference.UNSAFE.getBoolean(object, align(index));
  }

  void setBoolean(boolean value, long index) {
    UnsafeReference.UNSAFE.putBoolean(object, align(index), value);
  }

  short getShort(long index) {
    return UnsafeReference.UNSAFE.getShort(object, align(index));
  }

  void setShort(short value, long index) {
    UnsafeReference.UNSAFE.putShort(object, align(index), value);
  }

  int getInt(long index) {
    return UnsafeReference.UNSAFE.getInt(object, align(index));
  }

  void setInt(int value, long index) {
    UnsafeReference.UNSAFE.putInt(object, align(index), value);
  }

  float getFloat(long index) {
    return UnsafeReference.UNSAFE.getFloat(object, align(index));
  }

  void setFloat(float value, long index) {
    UnsafeReference.UNSAFE.putFloat(object, align(index), value);
  }

  double getDouble(long index) {
    return UnsafeReference.UNSAFE.getDouble(object, align(index));
  }

  void setDouble(double value, long index) {
    UnsafeReference.UNSAFE.putDouble(object, align(index), value);
  }

  long getLong(long index) {
    return UnsafeReference.UNSAFE.getLong(object, align(index));
  }

  void setLong(long value, long index) {
    UnsafeReference.UNSAFE.putLong(object, align(index), value);
  }

  void copyTo(UnsafeMemoryHandle memory, long length) {
    UnsafeReference.UNSAFE.copyMemory(object, byteOffset, memory.object, memory.byteOffset, length * scale);
  }

  UnsafeMemoryHandle offset(long index) {
    long offset = scale(index);
    return new UnsafeMemoryHandle(object, this.byteOffset + offset, byteSize - offset, scale);
  }

  UnsafeMemoryHandle narrow(long size) {
    return new UnsafeMemoryHandle(object, byteOffset, scale(size), scale);
  }

  private final Object object;
  private final long byteOffset;
  private final long byteSize;
  private final long scale;

  private UnsafeMemoryHandle(Object object, long byteOffset, long byteSize, long scale) {
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
