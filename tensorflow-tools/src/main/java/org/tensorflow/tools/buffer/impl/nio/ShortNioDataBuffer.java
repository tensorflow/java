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

import java.nio.ShortBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.ShortDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

/**
 * A buffer of bytes using a JDK {@link ShortBuffer} for storage.
 */
final class ShortNioDataBuffer extends AbstractNioDataBuffer<Short>
    implements ShortDataBuffer {

  @Override
  public short getShort(long index) {
    return buf.get((int)index);
  }

  @Override
  public ShortDataBuffer setShort(short value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public ShortDataBuffer read(short[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public ShortDataBuffer write(short[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public ShortDataBuffer copyTo(DataBuffer<Short> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof ShortNioDataBuffer) {
      ShortBuffer dstBuf = ((ShortNioDataBuffer)dst).buf.duplicate();
      ShortBuffer srcBuf = (ShortBuffer)buf.duplicate().limit((int)size);
      dstBuf.put(srcBuf);
    } else {
      slowCopyTo(dst, size);
    }
    return this;
  }

  @Override
  public ShortDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new ShortNioDataBuffer(((ShortBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public ShortDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new ShortNioDataBuffer(((ShortBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  ShortBuffer buf() {
    return buf;
  }

  ShortNioDataBuffer(ShortBuffer buf) {
    this.buf = buf;
  }

  private ShortBuffer buf;
}
