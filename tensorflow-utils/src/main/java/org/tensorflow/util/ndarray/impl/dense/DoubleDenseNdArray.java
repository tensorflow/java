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
package org.tensorflow.util.ndarray.impl.dense;

import org.tensorflow.util.buffer.DataBuffer;
import org.tensorflow.util.buffer.DataBuffers;
import org.tensorflow.util.buffer.DoubleDataBuffer;
import org.tensorflow.util.ndarray.DoubleNdArray;
import org.tensorflow.util.ndarray.NdArray;
import org.tensorflow.util.Shape;
import org.tensorflow.util.ndarray.impl.dimension.DimensionalSpace;

public class DoubleDenseNdArray extends AbstractDenseNdArray<Double, DoubleNdArray>
    implements DoubleNdArray {

  public static DoubleNdArray create(DoubleDataBuffer buffer, Shape shape) {
    Validator.denseShape(buffer, shape);
    return new DoubleDenseNdArray(buffer, shape);
  }

  @Override
  public double getDouble(long... indices) {
    return buffer().getDouble(positionOf(indices, true));
  }

  @Override
  public DoubleNdArray setDouble(double value, long... indices) {
    buffer().setDouble(value, positionOf(indices, true));
    return this;
  }

  @Override
  public DoubleNdArray read(double[] dst, int offset) {
    Validator.getArrayArgs(this, dst.length, offset);
    return read(DataBuffers.wrap(dst, false).offset(offset));
  }

  @Override
  public DoubleNdArray write(double[] src, int offset) {
    Validator.putArrayArgs(this, src.length, offset);
    return write(DataBuffers.wrap(src, true).offset(offset));
  }

  @Override
  public DoubleNdArray copyTo(NdArray<Double> dst) {
    Validator.copyToNdArrayArgs(this, dst);
    if (dst instanceof DoubleDenseNdArray) {
      DoubleDenseNdArray doubleDst = (DoubleDenseNdArray)dst;
      DataTransfer.execute(buffer, dimensions(), doubleDst.buffer, doubleDst.dimensions(), DataTransfer::ofDouble);
    } else {
      slowCopyTo(dst);
    }
    return this;
  }

  @Override
  public DoubleNdArray read(DoubleDataBuffer dst) {
    Validator.readToBufferArgs(this, dst);
    DataTransfer.execute(buffer, dimensions(), dst, DataTransfer::ofDouble);
    return this;
  }

  @Override
  public DoubleNdArray write(DoubleDataBuffer src) {
    Validator.writeFromBufferArgs(this, src);
    DataTransfer.execute(src, buffer, dimensions(), DataTransfer::ofDouble);
    return this;
  }

  protected DoubleDenseNdArray(DoubleDataBuffer buffer, Shape shape) {
    this(buffer, DimensionalSpace.create(shape));
  }

  @Override
  DoubleDenseNdArray instantiate(DataBuffer<Double> buffer, DimensionalSpace dimensions) {
    return new DoubleDenseNdArray((DoubleDataBuffer)buffer, dimensions);
  }

  @Override
  protected DoubleDataBuffer buffer() {
    return buffer;
  }

  private final DoubleDataBuffer buffer;

  private DoubleDenseNdArray(DoubleDataBuffer buffer, DimensionalSpace dimensions) {
    super(dimensions);
    this.buffer = buffer;
  }
}