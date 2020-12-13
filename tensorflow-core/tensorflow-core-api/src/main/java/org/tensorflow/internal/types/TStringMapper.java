package org.tensorflow.internal.types;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import org.tensorflow.DataType;
import org.tensorflow.RawTensor;
import org.tensorflow.TensorMapper;
import org.tensorflow.internal.buffer.ByteSequenceTensorBuffer;
import org.tensorflow.internal.buffer.ByteSequencer;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayout;
import org.tensorflow.ndarray.buffer.layout.DataLayouts;
import org.tensorflow.ndarray.impl.dense.DenseNdArray;
import org.tensorflow.types.TString;

/**
 * Maps memory of {@link org.tensorflow.proto.framework.DataType#DT_STRING} tensors
 * to a n-dimensional data space.
 */
public class TStringMapper extends TensorMapper<TString> {

  private static final DataLayout<DataBuffer<byte[]>, String> UTF_8_LAYOUT =
      DataLayouts.ofStrings(StandardCharsets.UTF_8);

  @Override
  protected TString mapDense(RawTensor tensor) {
    ByteSequenceTensorBuffer buffer = TensorBuffers.toStrings(nativeHandle(tensor), tensor.shape().size());
    return new DenseTString(tensor, buffer, UTF_8_LAYOUT);
  }

  /**
   * Adds package-private methods to all instances of {@code TString}
   */
  interface TStringInternal extends TString {

    /**
     * Initialize the buffer of this string tensor using the provided byte sequencer.
     *
     * @param byteSequencer produces sequences of bytes to use as the tensor data
     * @param <T> source of bytes ({@code byte[]} or {@code String})
     */
    <T> void init(ByteSequencer<T> byteSequencer);
  }

  private static class DenseTString extends DenseNdArray<String> implements TStringInternal {

    @Override
    public <T> void init(ByteSequencer<T> byteSequencer) {
      buffer.init(byteSequencer);
    }

    @Override
    public TString using(Charset charset) {
      return new DenseTString(rawTensor, buffer, DataLayouts.ofStrings(charset));
    }

    @Override
    public NdArray<byte[]> asBytes() {
      return NdArrays.wrap(shape(), buffer);
    }

    @Override
    public DataType<?> dataType() {
      return TString.DTYPE;
    }

    @Override
    public RawTensor asRawTensor() {
      return rawTensor;
    }

    final RawTensor rawTensor;
    final ByteSequenceTensorBuffer buffer;

    DenseTString(
        RawTensor rawTensor,
        ByteSequenceTensorBuffer buffer,
        DataLayout<DataBuffer<byte[]>, String> layout
    ) {
      super(layout.applyTo(buffer), rawTensor.shape());
      this.rawTensor = rawTensor;
      this.buffer = buffer;
    }
  }
}
