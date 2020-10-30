package org.tensorflow.internal.types;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import org.tensorflow.AbstractTypeFactory;
import org.tensorflow.TensorHandle;
import org.tensorflow.internal.buffer.ByteSequenceTensorBuffer;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayout;
import org.tensorflow.ndarray.buffer.layout.DataLayouts;
import org.tensorflow.ndarray.impl.dense.DenseNdArray;
import org.tensorflow.types.TString;

/**
 * Factory of {@link TString} tensor instances
 */
public class TStringFactory extends AbstractTypeFactory<TString> {

  @Override
  public TString createDense(TensorHandle tensorHandle, Shape shape) {
    ByteSequenceTensorBuffer buffer = TensorBuffers.toStrings(getNative(tensorHandle), shape.size());
    return new TStringImpl(tensorHandle, buffer, shape);
  }

  private static final DataLayout<DataBuffer<byte[]>, String> UTF_8_LAYOUT =
      DataLayouts.ofStrings(StandardCharsets.UTF_8);

  private static final class TStringImpl extends DenseNdArray<String> implements TString {

    @Override
    public NdArray<byte[]> asBytes() {
      return NdArrays.wrap(shape(), rawBuffer);
    }

    @Override
    public Class<TString> type() {
      return TString.class;
    }

    @Override
    public TensorHandle handle() {
      return tensorHandle;
    }

    @Override
    public TString using(Charset charset) {
      return new TStringImpl(tensorHandle, rawBuffer, shape(), DataLayouts.ofStrings(charset));
    }

    @Override
    public <T> void write(NdArray<T> src, Function<T, byte[]> getBytes) {
      rawBuffer.init(src, getBytes);
    }

    TStringImpl(TensorHandle tensorHandle, ByteSequenceTensorBuffer rawBuffer, Shape shape) {
      this(tensorHandle, rawBuffer, shape, UTF_8_LAYOUT);
    }

    private TStringImpl(
        TensorHandle tensorHandle,
        ByteSequenceTensorBuffer rawBuffer,
        Shape shape,
        DataLayout<DataBuffer<byte[]>, String> layout
    ) {
      super(layout.applyTo(rawBuffer), shape);
      this.rawBuffer = rawBuffer;
      this.tensorHandle = tensorHandle;
    }

    private final TensorHandle tensorHandle;
    private final ByteSequenceTensorBuffer rawBuffer;
  }
}
