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

import java.nio.FloatBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

/**
 * A buffer of bytes using a JDK {@link FloatBuffer} for storage.
 */
final class FloatNioDataBuffer extends AbstractNioDataBuffer<Float>
    implements FloatDataBuffer {

  @Override
  public float getFloat(long index) {
    return buf.get((int)index);
  }

  @Override
  public FloatDataBuffer setFloat(float value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public FloatDataBuffer read(float[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public FloatDataBuffer write(float[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public FloatDataBuffer copyTo(DataBuffer<Float> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof FloatNioDataBuffer) {
      FloatBuffer dstBuf = ((FloatNioDataBuffer)dst).buf.duplicate();
      FloatBuffer srcBuf = (FloatBuffer)buf.duplicate().limit((int)size);
      dstBuf.put(srcBuf);
    } else {
      slowCopyTo(dst, size);
    }
    return this;
  }

  @Override
  public FloatDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new FloatNioDataBuffer(((FloatBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public FloatDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new FloatNioDataBuffer(((FloatBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  FloatBuffer buf() {
    return buf;
  }

  FloatNioDataBuffer(FloatBuffer buf) {
    this.buf = buf;
  }

  private FloatBuffer buf;
}
