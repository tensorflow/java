/*
 *  Copyright 2022 The TensorFlow Authors. All Rights Reserved.
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

import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.RawTensor;
import org.tensorflow.SparseTensor;
import org.tensorflow.TensorMapper;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.impl.dense.ShortDenseNdArray;
import org.tensorflow.ndarray.impl.sparse.ShortSparseNdArray;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TUint16;

/**
 * Maps memory of {@link org.tensorflow.proto.DataType#DT_UINT16} tensors to a n-dimensional data
 * space.
 */
public final class TUint16Mapper extends TensorMapper<TUint16> {

  @Override
  protected TUint16 mapDense(RawTensor tensor) {
    ShortDataBuffer buffer = TensorBuffers.toShorts(nativeHandle(tensor));
    return new DenseTUint16(tensor, buffer);
  }

  @Override
  protected SparseTensor<TUint16> mapSparse(
      TInt64 indices, TUint16 values, TInt64 denseShape, PointerScope tensorScope) {
    return new TUint16Mapper.SparseTUint16(indices, values, denseShape, tensorScope);
  }

  private static final class DenseTUint16 extends ShortDenseNdArray implements TUint16 {

    @Override
    public Class<TUint16> type() {
      return TUint16.class;
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

    DenseTUint16(RawTensor rawTensor, ShortDataBuffer buffer) {
      super(buffer, rawTensor.shape());
      this.rawTensor = rawTensor;
    }
  }

  private static final class SparseTUint16 extends ShortSparseNdArray
      implements TUint16, SparseTensor<TUint16> {

    @Override
    public Class<TUint16> type() {
      return TUint16.class;
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
    public TUint16 values() {
      return (TUint16) getValues();
    }

    @Override
    public TInt64 denseShape() {
      return denseShape;
    }

    SparseTUint16(TInt64 indices, TUint16 values, TInt64 denseShape, PointerScope tensorScope) {
      super(indices, values, (short) 0, SparseHelpers.toDimensionalSpace(denseShape));
      this.denseShape = denseShape;
      this.tensorScope = tensorScope.extend();
    }

    private final TInt64 denseShape;
    private final PointerScope tensorScope;
  }
}
