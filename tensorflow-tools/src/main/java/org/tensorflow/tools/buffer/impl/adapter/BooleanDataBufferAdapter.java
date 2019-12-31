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

import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;
import org.tensorflow.tools.buffer.layout.BooleanDataLayout;

class BooleanDataBufferAdapter extends AbstractDataBufferAdapter<Boolean, BooleanDataBuffer>
    implements BooleanDataBuffer {

  @Override
  public boolean getBoolean(long index) {
    Validator.getArgs(this, index);
    return layout.readBoolean(buffer(), index * layout.sizeInBytes());
  }

  @Override
  public BooleanDataBuffer setBoolean(boolean value, long index) {
    Validator.setArgs(this, index);
    layout.writeBoolean(buffer(), value, index * layout.sizeInBytes());
    return this;
  }

  @Override
  public BooleanDataBuffer read(boolean[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = layout.readBoolean(buffer(), i * layout.sizeInBytes());
    }
    return this;
  }

  @Override
  public BooleanDataBuffer write(boolean[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      layout.writeBoolean(buffer(), src[j], i * layout.sizeInBytes());
    }
    return this;
  }

  @Override
  public BooleanDataBuffer offset(long index) {
    return new BooleanDataBufferAdapter(buffer().offset(index * layout.sizeInBytes()), layout);
  }

  @Override
  public BooleanDataBuffer narrow(long size) {
    return new BooleanDataBufferAdapter(buffer().narrow(size * layout.sizeInBytes()), layout);
  }

  BooleanDataBufferAdapter(ByteDataBuffer physicalBuffer, BooleanDataLayout layout) {
    super(physicalBuffer, layout);
    this.layout = layout;
  }

  private BooleanDataLayout layout;
}
