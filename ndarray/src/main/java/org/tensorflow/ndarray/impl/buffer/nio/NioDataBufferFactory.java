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

package org.tensorflow.ndarray.impl.buffer.nio;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;

/**
 * Factory of JDK NIO-based data buffers
 */
public class NioDataBufferFactory {

  public static ByteDataBuffer create(ByteBuffer buffer) {
    return new ByteNioDataBuffer(buffer);
  }

  public static DoubleDataBuffer create(DoubleBuffer buffer) {
    return new DoubleNioDataBuffer(buffer);
  }

  public static FloatDataBuffer create(FloatBuffer buffer) {
    return new FloatNioDataBuffer(buffer);
  }

  public static IntDataBuffer create(IntBuffer buffer) {
    return new IntNioDataBuffer(buffer);
  }

  public static LongDataBuffer create(LongBuffer buffer) {
    return new LongNioDataBuffer(buffer);
  }

  public static ShortDataBuffer create(ShortBuffer buffer) {
    return new ShortNioDataBuffer(buffer);
  }
}
