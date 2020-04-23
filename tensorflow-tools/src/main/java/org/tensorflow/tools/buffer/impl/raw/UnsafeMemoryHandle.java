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

package org.tensorflow.tools.buffer.impl.raw;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

final class UnsafeMemoryHandle {

  static UnsafeMemoryHandle fromArray(Object array, int length) {
    return fromArray(array, 0, length);
  }

  static UnsafeMemoryHandle fromArray(Object array, int arrayOffset, int length) {
    long scale = UnsafeReference.UNSAFE.arrayIndexScale(array.getClass());
    int baseOffset = UnsafeReference.UNSAFE.arrayBaseOffset(array.getClass());
    return new UnsafeMemoryHandle(array, baseOffset + (arrayOffset * scale), length * scale, scale);
  }

  static UnsafeMemoryHandle fromAddress(long address, long byteSize, long scale) {
    return new UnsafeMemoryHandle(address, byteSize, scale);
  }

  long size() {
    return size;
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

  UnsafeMemoryHandle slice(long index, long size) {
    return new UnsafeMemoryHandle(object, this.byteOffset + scale(index), scale(size), scale);
  }

  UnsafeMemoryHandle rescale(long scale) {
    if (object != null) {
      throw new IllegalStateException("Raw heap memory cannot be rescaled");
    }
    return new UnsafeMemoryHandle(null, byteOffset, byteSize, scale);
  }

  void rebase(long index) {
    byteOffset = baseOffset + scale(index);
  }

  boolean isArray() {
    return object != null;
  }

  @SuppressWarnings("unchecked")
  <A> A array() {
    return (A)object;
  }

  int arrayOffset(Class<?> arrayClass) {
    return (int)((byteOffset - UnsafeReference.UNSAFE.arrayBaseOffset(arrayClass)) / scale);
  }

  ByteBuffer toArrayByteBuffer() {
    return ByteBuffer.wrap((byte[])object, (int) byteOffset - UnsafeReference.UNSAFE.arrayBaseOffset(byte[].class), (int)size);
  }

  ShortBuffer toArrayShortBuffer() {
    return ShortBuffer.wrap((short[])object, (int)((byteOffset - UnsafeReference.UNSAFE.arrayBaseOffset(short[].class)) / scale), (int)size);
  }

  IntBuffer toArrayIntBuffer() {
    return IntBuffer.wrap((int[])object, (int)((byteOffset - UnsafeReference.UNSAFE.arrayBaseOffset(int[].class)) / scale), (int)size);
  }

  LongBuffer toArrayLongBuffer() {
    return LongBuffer.wrap((long[])object, (int)((byteOffset - UnsafeReference.UNSAFE.arrayBaseOffset(long[].class)) / scale), (int)size);
  }

  FloatBuffer toArrayFloatBuffer() {
    return FloatBuffer.wrap((float[])object, (int)((byteOffset - UnsafeReference.UNSAFE.arrayBaseOffset(float[].class)) / scale), (int)size);
  }

  DoubleBuffer toArrayDoubleBuffer() {
    return DoubleBuffer.wrap((double[])object, (int)((byteOffset - UnsafeReference.UNSAFE.arrayBaseOffset(double[].class)) / scale), (int)size);
  }

  final Object object;
  final long baseOffset;
  long byteOffset;
  final long byteSize;
  final long scale;
  final long size;

  private UnsafeMemoryHandle(Object object, long baseOffset, long byteSize, long scale) {
    this.object = object;
    this.baseOffset = baseOffset;
    byteOffset = baseOffset;
    this.byteSize = byteSize;
    this.scale = scale;
    size = byteSize / scale;
  }

  private UnsafeMemoryHandle(long address, long byteSize, long scale) {
    this(null, address, byteSize, scale);
  }

  private long align(long index) {
    return byteOffset + index * scale;
  }

  private long scale(long value) {
    return value * scale;
  }
}
