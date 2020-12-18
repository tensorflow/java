package org.tensorflow.internal.types;

import java.util.function.Consumer;
import java.util.function.Function;
import org.tensorflow.internal.buffer.ByteSequenceTensorBuffer;
import org.tensorflow.internal.buffer.ByteSequencer;
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
    this.byteSequencer = new ByteSequencer<>(source, byteExtractor);
  }

  /**
   * Compute the minimum size for a tensor to hold all the data provided by the source.
   *
   * @return minimum tensor size, in bytes
   * @see ByteSequenceTensorBuffer#computeSize(ByteSequencer)
   */
  public long computeRequiredSize() {
    return ByteSequenceTensorBuffer.computeSize(byteSequencer);
  }

  @Override
  public void accept(TString tensor) {
    ((TStringInternal)tensor).init(byteSequencer);
  }

  private final ByteSequencer<T> byteSequencer;
}
