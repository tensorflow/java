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
package org.tensorflow.tools.buffer.impl;

import org.tensorflow.tools.buffer.DataBuffer;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {

  @Override
  public DataBuffer<T> read(T[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0; i < length; ++i) {
      dst[i + offset] = getObject(i);
    }
    return this;
  }

  @Override
  public DataBuffer<T> write(T[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0; i < length; ++i) {
      setObject(src[i + offset], i);
    }
    return this;
  }

  protected void slowCopyTo(DataBuffer<T> dst, long size) {
    for (long idx = 0; idx < size; ++idx) {
      dst.setObject(getObject(idx), idx);
    }
  }
}
