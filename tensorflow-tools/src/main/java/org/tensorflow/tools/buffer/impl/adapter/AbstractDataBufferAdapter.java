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

package org.tensorflow.tools.buffer.impl.adapter;

import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.impl.AbstractDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;
import org.tensorflow.tools.buffer.layout.DataLayout;

@SuppressWarnings("unchecked")
abstract class AbstractDataBufferAdapter<T, B extends DataBuffer<T>> extends AbstractDataBuffer<T> {

  @Override
  public long size() {
    return buffer.size() / layout.sizeInBytes();
  }

  @Override
  public boolean isReadOnly() {
    return buffer.isReadOnly();
  }

  @Override
  public T getObject(long index) {
    Validator.getArgs(this, index);
    return layout.readValue(buffer, index * layout.sizeInBytes());
  }

  @Override
  public B setObject(T value, long index) {
    Validator.setArgs(this, index);
    layout.writeValue(buffer, value, index * layout.sizeInBytes());
    return (B)this;
  }

  @Override
  public B copyTo(DataBuffer<T> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    slowCopyTo(dst, size); // FIXME anyway to speed up this?
    return (B)this;
  }

  AbstractDataBufferAdapter(ByteDataBuffer buffer, DataLayout<T> layout) {
    this.buffer = buffer;
    this.layout = layout;
  }

  DataLayout<T> layout() {
    return layout;
  }

  ByteDataBuffer buffer() {
    return buffer;
  }

  private final ByteDataBuffer buffer;
  private final DataLayout<T> layout;
}
