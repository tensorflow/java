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
package org.tensorflow.internal.buffer;

import java.util.Iterator;
import java.util.function.Function;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArraySequence;

/**
 * Produces sequence of bytes to be stored in a {@link ByteSequenceTensorBuffer}.
 *
 * @param <T> source of bytes (byte arrays or strings)
 */
public class ByteSequenceProvider<T> implements Iterable<byte[]> {

  /**
   * Constructor
   *
   * @param source source of data
   * @param byteExtractor method that converts one value of the source into a sequence of bytes
   */
  public ByteSequenceProvider(NdArray<T> source, Function<T, byte[]> byteExtractor) {
    this.source = source;
    this.byteExtractor = byteExtractor;
  }

  @Override
  public Iterator<byte[]> iterator() {
    return new Iterator<byte[]>() {

      @Override
      public boolean hasNext() {
        return scalarIterator.hasNext();
      }

      @Override
      public byte[] next() {
        return byteExtractor.apply(scalarIterator.next().getObject());
      }

      private final Iterator<? extends NdArray<T>> scalarIterator = source.scalars().iterator();
    };
  }

  /**
   * @return total number of byte sequences that can be produced by this sequencer
   */
  long numSequences() {
    return source.size();
  }

  private final NdArray<T> source;
  private final Function<T, byte[]> byteExtractor;
}
