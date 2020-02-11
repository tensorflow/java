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

import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DataStorageVisitor;
import org.tensorflow.tools.buffer.impl.AbstractDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;
import org.tensorflow.tools.buffer.layout.DataLayout;

@SuppressWarnings("unchecked")
abstract class AbstractDataBufferAdapter<S extends DataBuffer<?>, T, U extends DataBuffer<T>> extends AbstractDataBuffer<T> {

  @Override
  public long size() {
    return buffer.size() / layout.scale();
  }

  @Override
  public boolean isReadOnly() {
    return buffer.isReadOnly();
  }

  @Override
  public T getObject(long index) {
    Validator.getArgs(this, index);
    return layout.readObject(buffer, index * layout.scale());
  }

  @Override
  public U setObject(T value, long index) {
    Validator.setArgs(this, index);
    layout.writeObject(buffer, value, index * layout.scale());
    return (U)this;
  }

  @Override
  public <R> R accept(DataStorageVisitor<R> visitor) {
    return buffer().accept(visitor);
  }

  AbstractDataBufferAdapter(S buffer, DataLayout<S, T> layout) {
    this.buffer = buffer;
    this.layout = layout;
  }

  DataLayout<S, T> layout() {
    return layout;
  }

  S buffer() {
    return buffer;
  }

  private final S buffer;
  private final DataLayout<S, T> layout;
}
