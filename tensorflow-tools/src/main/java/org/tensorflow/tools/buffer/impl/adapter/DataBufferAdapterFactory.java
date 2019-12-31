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
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.ShortDataBuffer;
import org.tensorflow.tools.buffer.layout.BooleanDataLayout;
import org.tensorflow.tools.buffer.layout.DataLayout;
import org.tensorflow.tools.buffer.layout.DoubleDataLayout;
import org.tensorflow.tools.buffer.layout.FloatDataLayout;
import org.tensorflow.tools.buffer.layout.IntDataLayout;
import org.tensorflow.tools.buffer.layout.LongDataLayout;
import org.tensorflow.tools.buffer.layout.ShortDataLayout;

public class DataBufferAdapterFactory {

  public static BooleanDataBuffer create(ByteDataBuffer buffer, BooleanDataLayout layout) {
    return new BooleanDataBufferAdapter(buffer, layout);
  }

  public static DoubleDataBuffer create(ByteDataBuffer buffer, DoubleDataLayout layout) {
    return new DoubleDataBufferAdapter(buffer, layout);
  }

  public static FloatDataBuffer create(ByteDataBuffer buffer, FloatDataLayout layout) {
    return new FloatDataBufferAdapter(buffer, layout);
  }

  public static IntDataBuffer create(ByteDataBuffer buffer, IntDataLayout layout) {
    return new IntDataBufferAdapter(buffer, layout);
  }

  public static LongDataBuffer create(ByteDataBuffer buffer, LongDataLayout layout) {
    return new LongDataBufferAdapter(buffer, layout);
  }

  public static ShortDataBuffer create(ByteDataBuffer buffer, ShortDataLayout layout) {
    return new ShortDataBufferAdapter(buffer, layout);
  }

  public static <T> DataBuffer<T> create(ByteDataBuffer buffer, DataLayout<T> layout) {
    return new DataBufferAdapter<>(buffer, layout);
  }
}
