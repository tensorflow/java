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
package org.tensorflow.internal.types;

import java.util.function.Consumer;
import java.util.function.Function;
import org.tensorflow.internal.buffer.ByteSequenceTensorBuffer;
import org.tensorflow.internal.buffer.ByteSequenceProvider;
import org.tensorflow.internal.types.TStringMapper.TStringInternal;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.types.TString;

/**
 * Helper class for initializing a {@link TString} tensor.
 *
 * @param <T> source of bytes ({@code byte[]} or {@code String})
 */
public final class TStringInitializer<T> implements Consumer<TString> {

  public TStringInitializer(NdArray<T> source, Function<T, byte[]> byteExtractor) {
    this.byteSequenceProvider = new ByteSequenceProvider<>(source, byteExtractor);
  }

  /**
   * Compute the minimum size for a tensor to hold all the data provided by the source.
   *
   * @return minimum tensor size, in bytes
   * @see ByteSequenceTensorBuffer#computeSize(ByteSequenceProvider)
   */
  public long computeRequiredSize() {
    return ByteSequenceTensorBuffer.computeSize(byteSequenceProvider);
  }

  @Override
  public void accept(TString tensor) {
    ((TStringInternal)tensor).init(byteSequenceProvider);
  }

  private final ByteSequenceProvider<T> byteSequenceProvider;
}
