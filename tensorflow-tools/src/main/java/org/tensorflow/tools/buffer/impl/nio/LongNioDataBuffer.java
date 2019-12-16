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

import java.nio.LongBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

/**
 * A buffer of bytes using a JDK {@link LongBuffer} for storage.
 */
final class LongNioDataBuffer extends AbstractNioDataBuffer<Long>
    implements LongDataBuffer {

  @Override
  public long getLong(long index) {
    return buf.get((int)index);
  }

  @Override
  public LongDataBuffer setLong(long value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public LongDataBuffer read(long[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public LongDataBuffer write(long[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public LongDataBuffer copyTo(DataBuffer<Long> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof LongNioDataBuffer) {
      LongBuffer dstBuf = ((LongNioDataBuffer)dst).buf.duplicate();
      LongBuffer srcBuf = (LongBuffer)buf.duplicate().limit((int)size);
      dstBuf.put(srcBuf);
    } else {
      slowCopyTo(dst, size);
    }
    return this;
  }

  @Override
  public LongDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new LongNioDataBuffer(((LongBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public LongDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new LongNioDataBuffer(((LongBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  LongBuffer buf() {
    return buf;
  }

  LongNioDataBuffer(LongBuffer buf) {
    this.buf = buf;
  }

  private LongBuffer buf;
}
