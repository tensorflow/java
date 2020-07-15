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

package org.tensorflow.ndarray.impl.buffer.adapter;

import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.buffer.layout.BooleanDataLayout;
import org.tensorflow.ndarray.buffer.layout.ByteDataLayout;
import org.tensorflow.ndarray.buffer.layout.DataLayout;
import org.tensorflow.ndarray.buffer.layout.DoubleDataLayout;
import org.tensorflow.ndarray.buffer.layout.FloatDataLayout;
import org.tensorflow.ndarray.buffer.layout.IntDataLayout;
import org.tensorflow.ndarray.buffer.layout.LongDataLayout;
import org.tensorflow.ndarray.buffer.layout.ShortDataLayout;

/**
 * Factory of data buffer adapters.
 *
 * <p>Data buffer adapters are used to apply a {@link DataLayout} to a buffer. Conceptually, they act
 * as a proxy that intercept each I/O call and perform the required type conversions after/before
 * delegating the task to the underlying buffer.
 */
public class DataBufferAdapterFactory {

  /**
   * Creates an adapter that applies a byte data layout to the given buffer.
   *
   * @param buffer the delegate buffer
   * @param layout layout to apply
   * @return buffer adapter
   */
  public static <S extends DataBuffer<?>> ByteDataBuffer create(S buffer, ByteDataLayout<S> layout) {
    return new ByteDataBufferAdapter<>(buffer, layout);
  }

  /**
   * Creates an adapter that applies a boolean data layout to the given buffer.
   *
   * @param buffer the delegate buffer
   * @param layout layout to apply
   * @return buffer adapter
   */
  public static <S extends DataBuffer<?>> BooleanDataBuffer create(S buffer, BooleanDataLayout<S> layout) {
    return new BooleanDataBufferAdapter<>(buffer, layout);
  }

  /**
   * Creates an adapter that applies a double data layout to the given buffer.
   *
   * @param buffer the delegate buffer
   * @param layout layout to apply
   * @return buffer adapter
   */
  public static <S extends DataBuffer<?>> DoubleDataBuffer create(S buffer, DoubleDataLayout<S> layout) {
    return new DoubleDataBufferAdapter<>(buffer, layout);
  }

  /**
   * Creates an adapter that applies a float data layout to the given buffer.
   *
   * @param buffer the delegate buffer
   * @param layout layout to apply
   * @return buffer adapter
   */
  public static <S extends DataBuffer<?>> FloatDataBuffer create(S buffer, FloatDataLayout<S> layout) {
    return new FloatDataBufferAdapter<>(buffer, layout);
  }

  /**
   * Creates an adapter that applies a integer data layout to the given buffer.
   *
   * @param buffer the delegate buffer
   * @param layout layout to apply
   * @return buffer adapter
   */
  public static <S extends DataBuffer<?>> IntDataBuffer create(S buffer, IntDataLayout<S> layout) {
    return new IntDataBufferAdapter<>(buffer, layout);
  }

  /**
   * Creates an adapter that applies a long data layout to the given buffer.
   *
   * @param buffer the delegate buffer
   * @param layout layout to apply
   * @return buffer adapter
   */
  public static <S extends DataBuffer<?>> LongDataBuffer create(S buffer, LongDataLayout<S> layout) {
    return new LongDataBufferAdapter<>(buffer, layout);
  }

  /**
   * Creates an adapter that applies a short data layout to the given buffer.
   *
   * @param buffer the delegate buffer
   * @param layout layout to apply
   * @return buffer adapter
   */
  public static <S extends DataBuffer<?>> ShortDataBuffer create(S buffer, ShortDataLayout<S> layout) {
    return new ShortDataBufferAdapter<>(buffer, layout);
  }

  /**
   * Creates an adapter that applies a data layout to the given buffer.
   *
   * @param buffer the delegate buffer
   * @param layout layout to apply
   * @return buffer adapter
   */
  public static <S extends DataBuffer<?>, T> DataBuffer<T> create(S buffer, DataLayout<S, T> layout) {
    return new DataBufferAdapter<>(buffer, layout);
  }
}
