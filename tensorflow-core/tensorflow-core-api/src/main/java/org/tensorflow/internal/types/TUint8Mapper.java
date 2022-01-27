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

import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.RawTensor;
import org.tensorflow.SparseTensor;
import org.tensorflow.TensorMapper;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.impl.dense.ByteDenseNdArray;
import org.tensorflow.ndarray.impl.sparse.ByteSparseNdArray;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TUint8;

/**
 * Maps memory of {@link org.tensorflow.proto.framework.DataType#DT_UINT8} tensors to a
 * n-dimensional data space.
 */
public final class TUint8Mapper extends TensorMapper<TUint8> {

  @Override
  protected TUint8 mapDense(RawTensor tensor) {
    ByteDataBuffer buffer = TensorBuffers.toBytes(nativeHandle(tensor));
    return new DenseTUint8(tensor, buffer);
  }

  @Override
  protected SparseTensor<TUint8> mapSparse(
      TInt64 indices, TUint8 values, TInt64 denseShape, PointerScope tensorScope) {
    return new TUint8Mapper.SparseTUint8(indices, values, denseShape, tensorScope);
  }

  private static final class DenseTUint8 extends ByteDenseNdArray implements TUint8 {

    @Override
    public Class<TUint8> type() {
      return TUint8.class;
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

    DenseTUint8(RawTensor rawTensor, ByteDataBuffer buffer) {
      super(buffer, rawTensor.shape());
      this.rawTensor = rawTensor;
    }
  }

  private static final class SparseTUint8 extends ByteSparseNdArray
      implements TUint8, SparseTensor<TUint8> {

    @Override
    public Class<TUint8> type() {
      return TUint8.class;
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
    public TUint8 values() {
      return (TUint8) getValues();
    }

    @Override
    public TInt64 denseShape() {
      return denseShape;
    }

    SparseTUint8(TInt64 indices, TUint8 values, TInt64 denseShape, PointerScope tensorScope) {
      super(indices, values, (byte) 0, SparseHelpers.toDimensionalSpace(denseShape));
      this.denseShape = denseShape;
      this.tensorScope = tensorScope.extend();
    }

    private final TInt64 denseShape;
    private final PointerScope tensorScope;
  }
}
