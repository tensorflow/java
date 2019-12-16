/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */

package org.tensorflow.tools.buffer.impl.nio;

import java.nio.IntBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

/**
 * A buffer of bytes using a JDK {@link IntBuffer} for storage.
 */
final class IntNioDataBuffer extends AbstractNioDataBuffer<Integer>
    implements IntDataBuffer {

  @Override
  public int getInt(long index) {
    return buf.get((int)index);
  }

  @Override
  public IntDataBuffer setInt(int value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public IntDataBuffer read(int[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public IntDataBuffer write(int[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public IntDataBuffer copyTo(DataBuffer<Integer> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof IntNioDataBuffer) {
      IntBuffer dstBuf = ((IntNioDataBuffer)dst).buf.duplicate();
      IntBuffer srcBuf = (IntBuffer)buf.duplicate().limit((int)size);
      dstBuf.put(srcBuf);
    } else {
      slowCopyTo(dst, size);
    }
    return this;
  }

  @Override
  public IntDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new IntNioDataBuffer(((IntBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public IntDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new IntNioDataBuffer(((IntBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  IntBuffer buf() {
    return buf;
  }

  IntNioDataBuffer(IntBuffer buf) {
    this.buf = buf;
  }

  private IntBuffer buf;
}
