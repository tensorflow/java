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
package org.tensorflow.nio.nd.impl.dense;

import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.buffer.IntDataBuffer;
import org.tensorflow.nio.nd.IntNdArray;
import org.tensorflow.nio.nd.NdArray;
import org.tensorflow.nio.nd.Shape;
import org.tensorflow.nio.nd.impl.dimension.DimensionalSpace;

public class IntDenseNdArray extends AbstractDenseNdArray<Integer, IntNdArray>
    implements IntNdArray {

  public static IntNdArray create(IntDataBuffer buffer, Shape shape) {
    Validator.denseShape(buffer, shape);
    return new IntDenseNdArray(buffer, shape);
  }

  @Override
  public int getInt(long... indices) {
    return buffer().getInt(positionOf(indices, true));
  }

  @Override
  public IntNdArray setInt(int value, long... indices) {
    buffer().setInt(value, positionOf(indices, true));
    return this;
  }

  @Override
  public IntNdArray read(int[] dst, int offset) {
    Validator.getArrayArgs(this, dst.length, offset);
    return read(DataBuffers.wrap(dst, false).offset(offset));
  }

  @Override
  public IntNdArray write(int[] src, int offset) {
    Validator.putArrayArgs(this, src.length, offset);
    return write(DataBuffers.wrap(src, true).offset(offset));
  }

  @Override
  public IntNdArray copyTo(NdArray<Integer> dst) {
    Validator.copyToNdArrayArgs(this, dst);
    if (dst instanceof IntDenseNdArray) {
      IntDenseNdArray intDst = (IntDenseNdArray)dst;
      DataTransfer.execute(buffer, dimensions(), intDst.buffer, intDst.dimensions(), DataTransfer::ofInt);
    } else {
      slowCopyTo(dst);
    }
    return this;
  }

  @Override
  public IntNdArray read(IntDataBuffer dst) {
    Validator.readToBufferArgs(this, dst);
    DataTransfer.execute(buffer, dimensions(), dst, DataTransfer::ofInt);
    return this;
  }

  @Override
  public IntNdArray write(IntDataBuffer src) {
    Validator.writeFromBufferArgs(this, src);
    DataTransfer.execute(src, buffer, dimensions(), DataTransfer::ofInt);
    return this;
  }

  protected IntDenseNdArray(IntDataBuffer buffer, Shape shape) {
    this(buffer, DimensionalSpace.create(shape));
  }

  @Override
  IntDenseNdArray instantiate(DataBuffer<Integer> buffer, DimensionalSpace dimensions) {
    return new IntDenseNdArray((IntDataBuffer)buffer, dimensions);
  }

  @Override
  protected IntDataBuffer buffer() {
    return buffer;
  }

  private final IntDataBuffer buffer;

  private IntDenseNdArray(IntDataBuffer buffer, DimensionalSpace dimensions) {
    super(dimensions);
    this.buffer = buffer;
  }
}