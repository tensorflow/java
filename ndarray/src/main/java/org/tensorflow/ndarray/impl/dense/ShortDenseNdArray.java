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

import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.ShortNdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;

public class ShortDenseNdArray extends AbstractDenseNdArray<Short, ShortNdArray>
    implements ShortNdArray {

  public static ShortNdArray create(ShortDataBuffer buffer, Shape shape) {
    Validator.denseShape(buffer, shape);
    return new ShortDenseNdArray(buffer, shape);
  }

  @Override
  public short getShort(long... indices) {
    return buffer.getShort(positionOf(indices, true));
  }

  @Override
  public ShortNdArray setShort(short value, long... indices) {
    buffer.setShort(value, positionOf(indices, true));
    return this;
  }

  @Override
  public ShortNdArray copyTo(NdArray<Short> dst) {
    Validator.copyToNdArrayArgs(this, dst);
    if (dst instanceof ShortDenseNdArray) {
      ShortDenseNdArray shortDst = (ShortDenseNdArray)dst;
      DataTransfer.execute(buffer, dimensions(), shortDst.buffer, shortDst.dimensions(), DataTransfer::ofShort);
    } else {
      slowCopyTo(dst);
    }
    return this;
  }

  @Override
  public ShortNdArray read(ShortDataBuffer dst) {
    Validator.readToBufferArgs(this, dst);
    DataTransfer.execute(buffer, dimensions(), dst, DataTransfer::ofShort);
    return this;
  }

  @Override
  public ShortNdArray write(ShortDataBuffer src) {
    Validator.writeFromBufferArgs(this, src);
    DataTransfer.execute(src, buffer, dimensions(), DataTransfer::ofShort);
    return this;
  }

  protected ShortDenseNdArray(ShortDataBuffer buffer, Shape shape) {
    this(buffer, DimensionalSpace.create(shape));
  }

  @Override
  ShortDenseNdArray instantiate(DataBuffer<Short> buffer, DimensionalSpace dimensions) {
    return new ShortDenseNdArray((ShortDataBuffer)buffer, dimensions);
  }

  @Override
  protected ShortDataBuffer buffer() {
    return buffer;
  }

  private final ShortDataBuffer buffer;

  private ShortDenseNdArray(ShortDataBuffer buffer, DimensionalSpace dimensions) {
    super(dimensions);
    this.buffer = buffer;
  }
}