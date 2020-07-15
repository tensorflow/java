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

package org.tensorflow.ndarray.buffer.layout;

import java.nio.charset.Charset;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.impl.buffer.layout.Bfloat16Layout;
import org.tensorflow.ndarray.impl.buffer.layout.BoolLayout;
import org.tensorflow.ndarray.impl.buffer.layout.Float16Layout;
import org.tensorflow.ndarray.impl.buffer.layout.StringLayout;

/**
 * Exposes {@link DataLayout} instances of data formats frequently used in linear algebra computation.
 *
 * <p>Example of usage:
 * <pre>{@code
 * // Storing boolean values in a ByteDataBuffer
 * BooleanDataBuffer boolBuffer = DataLayouts.BOOL.applyTo(byteDataBuffer);
 *
 * // Allocating a new buffer of 256 half floats
 * FloatDataBuffer halfBuffer = DataLayouts.FLOAT16.applyTo(DataBuffers.ofShorts(256 * DataLayouts.FLOAT16.scale());
 * }</pre>
 */
public final class DataLayouts {

  /**
   * Data layout for converting 16-bit bfloats to/from short values.
   *
   * <p>This format used to be specific to TensorFlow but has now been adopted more broadly in the
   * machine learning field. It is optimized for fast conversion with single-precision 32-bit
   * floating points by simply shifting their value and truncating the mantissa to only 7 bits.
   *
   * <p>Therefore, this is a lost of precision in the fraction part compared to the IEEE-754
   * half-precision floating point specification (see {@link #FLOAT16} but it has a larger range of
   * possible values in the whole part as it preserves the 8-bit exponent and uses the same bias,
   * (i.e. an absolute range above 0 of approximately [10<sup>-40</sup>, 3.39 × 10<sup>38</sup>]
   *
   * <p>Some CPUs support the bfloat16 format natively for better performances.
   */
  public static final FloatDataLayout<ShortDataBuffer> BFLOAT16 = new Bfloat16Layout();

  /**
   * Data layout for converting 16-bit half floats to/from short values.
   *
   * <p>Half floats are stored in memory accordingly to the IEEE-754 half-precision floating point
   * specification, and are converted to/from 32-bit floats in the user space.
   *
   * <p>There is a potential loss of precision when converting a single float (32-bit) to a half
   * float (16-bit). Absolute range of values above 0 for a half float is approximately
   * [5.96 × 10<sup>-8</sup>, 6.55 × 10<sup>4</sup>] and their decimal part is rounded up to a 10 bits mantissa.
   *
   * <p>In general, half float computation perform better on GPUs since, in general, CPUs do not
   * support this format natively.</p>
   */
  public static final FloatDataLayout<ShortDataBuffer> FLOAT16 = new Float16Layout();

  /**
   * Data layout for converting booleans to/from byte values.
   *
   * <p>Since there is no Java NIO boolean buffer, this layout is particularly useful for mapping
   * booleans values to standard byte buffers. The conversion between a boolean and a byte requires
   * explicit type casting.
   */
  public static final BooleanDataLayout<ByteDataBuffer> BOOL = new BoolLayout();

  /**
   * Creates a data layout for converting strings to/from byte sequences.
   *
   * <p>This layout requires a {@code charset} in parameter to specify how the strings must be
   * encoded/decoded as byte sequences. So a new layout instance is always returned.
   *
   * @param charset charset to use
   * @return a new string layout
   */
  public static DataLayout<DataBuffer<byte[]>, String> ofStrings(Charset charset) {
    return StringLayout.of(charset);
  }
}
