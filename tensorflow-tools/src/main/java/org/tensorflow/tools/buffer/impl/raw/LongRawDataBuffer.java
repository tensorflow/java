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

import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

final class LongRawDataBuffer extends AbstractRawDataBuffer<Long, LongDataBuffer>
    implements LongDataBuffer {

  @Override
  public long getLong(long index) {
    Validator.getArgs(this, index);
    return memory.getLong(index);
  }

  @Override
  public LongDataBuffer setLong(long value, long index) {
    Validator.setArgs(this, index);
    memory.setLong(value, index);
    return this;
  }

  @Override
  public LongDataBuffer read(long[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public LongDataBuffer read(long[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public LongDataBuffer write(long[] src) {
    return write(src, src.length);
  }

  @Override
  public LongDataBuffer write(long[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  protected LongDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new LongRawDataBuffer(memory, readOnly);
  }

  LongRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
