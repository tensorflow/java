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

package org.tensorflow.ndarray.impl.buffer.misc;

import java.util.BitSet;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;

/**
 * Factory of miscellaneous data buffers
 */
public class MiscDataBufferFactory {

  public static BooleanDataBuffer create(BitSet bitSet, long numBits, boolean readOnly) {
    return new BitSetDataBuffer(bitSet, numBits, readOnly);
  }

  public static BooleanDataBuffer create(boolean[] array, boolean readOnly) {
    return new BooleanArrayDataBuffer(array, readOnly);
  }

  public static <T>  DataBuffer<T> create(T[] array, boolean readOnly) {
    return new ArrayDataBuffer<>(array, readOnly);
  }
}
