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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.RawTensor;
import org.tensorflow.SparseTensor;
import org.tensorflow.TensorMapper;
import org.tensorflow.internal.buffer.ByteSequenceProvider;
import org.tensorflow.internal.buffer.ByteSequenceTensorBuffer;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayout;
import org.tensorflow.ndarray.buffer.layout.DataLayouts;
import org.tensorflow.ndarray.impl.dense.DenseNdArray;
import org.tensorflow.ndarray.impl.sparse.SparseNdArray;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Maps memory of {@link org.tensorflow.proto.framework.DataType#DT_STRING} tensors to a
 * n-dimensional data space.
 */
public final class TStringMapper extends TensorMapper<TString> {

  private static final DataLayout<DataBuffer<byte[]>, String> UTF_8_LAYOUT =
      DataLayouts.ofStrings(StandardCharsets.UTF_8);

  @Override
  protected TString mapDense(RawTensor tensor) {
    ByteSequenceTensorBuffer buffer =
        TensorBuffers.toStrings(nativeHandle(tensor), tensor.shape().size());
    return new DenseTString(tensor, buffer, UTF_8_LAYOUT);
  }

  @Override
  protected SparseTensor<TString> mapSparse(
      TInt64 indices, TString values, TInt64 denseShape, PointerScope tensorScope) {
    return new SparseTString(indices, values, denseShape, tensorScope);
  }

  /** Adds package-private methods to all instances of {@code TString} */
  interface TStringInternal extends TString {

    /**
     * Initialize the buffer of this string tensor using the provided byte sequencer.
     *
     * @param byteSequenceProvider produces sequences of bytes to use as the tensor data
     * @param <T> source of bytes ({@code byte[]} or {@code String})
     */
    <T> void init(ByteSequenceProvider<T> byteSequenceProvider);
  }

  private static final class DenseTString extends DenseNdArray<String> implements TStringInternal {

    @Override
    public <T> void init(ByteSequenceProvider<T> byteSequenceProvider) {
      buffer.init(byteSequenceProvider);
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
    public Class<TString> type() {
      return TString.class;
    }

    @Override
    public DataType dataType() {
      return asRawTensor().dataType();
    }

    @Override
    public long numBytes() {
      return asRawTensor().numBytes();
    }

    @Override
    public void close() {
      asRawTensor().close();
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
        DataLayout<DataBuffer<byte[]>, String> layout) {
      super(layout.applyTo(buffer), rawTensor.shape());
      this.rawTensor = rawTensor;
      this.buffer = buffer;
    }
  }

  private static final class SparseTString extends SparseNdArray<String, TString>
      implements TString, SparseTensor<TString> {

    @Override
    public Class<TString> type() {
      return TString.class;
    }

    @Override
    public DataType dataType() {
      return values().dataType();
    }

    @Override
    public long numBytes() {
      return SparseHelpers.numBytes(this);
    }

    @Override
    public void close() {
      tensorScope.close();
    }

    @Override
    public boolean isSparse() {
      return true;
    }

    @Override
    public TInt64 indices() {
      return (TInt64) getIndices();
    }

    @Override
    public TString values() {
      return (TString) getValues();
    }

    @Override
    public TInt64 denseShape() {
      return denseShape;
    }

    @Override
    public TString using(Charset charset) {
      return new SparseTString(
          indices(), values().using(charset), denseShape(), tensorScope, false);
    }

    @Override
    public NdArray<byte[]> asBytes() {
      return SparseNdArray.create(byte[].class, indices(), values().asBytes(), dimensions());
    }

    SparseTString(TInt64 indices, TString values, TInt64 denseShape, PointerScope tensorScope) {
      this(indices, values, denseShape, tensorScope, true);
    }

    private SparseTString(
        TInt64 indices,
        TString values,
        TInt64 denseShape,
        PointerScope tensorScope,
        boolean extendScope) {
      super(String.class, indices, values, "", SparseHelpers.toDimensionalSpace(denseShape));
      this.denseShape = denseShape;
      this.tensorScope = extendScope ? tensorScope.extend() : tensorScope;
    }

    private final TInt64 denseShape;
    private final PointerScope tensorScope;
  }
}
