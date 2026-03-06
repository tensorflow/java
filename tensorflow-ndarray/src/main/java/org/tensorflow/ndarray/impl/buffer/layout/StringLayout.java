/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
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

package org.tensorflow.ndarray.impl.buffer.layout;

import java.nio.charset.Charset;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayout;

/**
 * Data layout that converts a String to/from a sequence of bytes applying a given charset.
 */
public final class StringLayout implements DataLayout<DataBuffer<byte[]>, String> {

  public static StringLayout of(Charset charset) {
    return new StringLayout(charset);
  }

  @Override
  public void writeObject(DataBuffer<byte[]> buffer, String value, long index) {
    buffer.setObject(value.getBytes(charset), index);
  }

  @Override
  public String readObject(DataBuffer<byte[]> buffer, long index) {
    return new String(buffer.getObject(index), charset);
  }

  private StringLayout(Charset charset) {
    this.charset = charset;
  }

  private final Charset charset;
}
