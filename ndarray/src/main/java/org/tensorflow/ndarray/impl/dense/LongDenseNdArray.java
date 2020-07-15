/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */
package org.tensorflow.ndarray.impl.dense;

import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;

public class LongDenseNdArray extends AbstractDenseNdArray<Long, LongNdArray>
    implements LongNdArray {

  public static LongNdArray create(LongDataBuffer buffer, Shape shape) {
    Validator.denseShape(buffer, shape);
    return new LongDenseNdArray(buffer, shape);
  }

  @Override
  public long getLong(long... indices) {
    return buffer.getLong(positionOf(indices, true));
  }

  @Override
  public LongNdArray setLong(long value, long... indices) {
    buffer.setLong(value, positionOf(indices, true));
    return this;
  }

  @Override
  public LongNdArray copyTo(NdArray<Long> dst) {
    Validator.copyToNdArrayArgs(this, dst);
    if (dst instanceof LongDenseNdArray) {
      LongDenseNdArray longDst = (LongDenseNdArray)dst;
      DataTransfer.execute(buffer, dimensions(), longDst.buffer, longDst.dimensions(), DataTransfer::ofLong);
    } else {
      slowCopyTo(dst);
    }
    return this;
  }

  @Override
  public LongNdArray read(LongDataBuffer dst) {
    Validator.readToBufferArgs(this, dst);
    DataTransfer.execute(buffer, dimensions(), dst, DataTransfer::ofLong);
    return this;
  }

  @Override
  public LongNdArray write(LongDataBuffer src) {
    Validator.writeFromBufferArgs(this, src);
    DataTransfer.execute(src, buffer, dimensions(), DataTransfer::ofLong);
    return this;
  }

  protected LongDenseNdArray(LongDataBuffer buffer, Shape shape) {
    this(buffer, DimensionalSpace.create(shape));
  }

  @Override
  LongDenseNdArray instantiate(DataBuffer<Long> buffer, DimensionalSpace dimensions) {
    return new LongDenseNdArray((LongDataBuffer)buffer, dimensions);
  }

  @Override
  protected LongDataBuffer buffer() {
    return buffer;
  }

  private final LongDataBuffer buffer;

  private LongDenseNdArray(LongDataBuffer buffer, DimensionalSpace dimensions) {
    super(dimensions);
    this.buffer = buffer;
  }
}