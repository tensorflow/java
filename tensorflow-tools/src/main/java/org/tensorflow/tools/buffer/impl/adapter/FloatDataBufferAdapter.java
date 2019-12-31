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
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;
import org.tensorflow.tools.buffer.layout.FloatDataLayout;

class FloatDataBufferAdapter extends AbstractDataBufferAdapter<Float, FloatDataBuffer>
    implements FloatDataBuffer {

  @Override
  public float getFloat(long index) {
    Validator.getArgs(this, index);
    return layout.readFloat(buffer(), index * layout.sizeInBytes());
  }

  @Override
  public FloatDataBuffer setFloat(float value, long index) {
    Validator.setArgs(this, index);
    layout.writeFloat(buffer(), value, index * layout.sizeInBytes());
    return this;
  }

  @Override
  public FloatDataBuffer read(float[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = layout.readFloat(buffer(), i * layout.sizeInBytes());
    }
    return this;
  }

  @Override
  public FloatDataBuffer write(float[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      layout.writeFloat(buffer(), src[j], i * layout.sizeInBytes());
    }
    return this;
  }

  @Override
  public FloatDataBuffer offset(long index) {
    return new FloatDataBufferAdapter(buffer().offset(index * layout.sizeInBytes()), layout);
  }

  @Override
  public FloatDataBuffer narrow(long size) {
    return new FloatDataBufferAdapter(buffer().narrow(size * layout.sizeInBytes()), layout);
  }

  FloatDataBufferAdapter(ByteDataBuffer physicalBuffer, FloatDataLayout layout) {
    super(physicalBuffer, layout);
    this.layout = layout;
  }

  private FloatDataLayout layout;
}
