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

import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;

public class FloatDenseNdArray extends AbstractDenseNdArray<Float, FloatNdArray>
    implements FloatNdArray {

  public static FloatNdArray create(FloatDataBuffer buffer, Shape shape) {
    Validator.denseShape(buffer, shape);
    return new FloatDenseNdArray(buffer, shape);
  }

  @Override
  public float getFloat(long... indices) {
    return buffer.getFloat(positionOf(indices, true));
  }

  @Override
  public FloatNdArray setFloat(float value, long... indices) {
    buffer.setFloat(value, positionOf(indices, true));
    return this;
  }

  @Override
  public FloatNdArray copyTo(NdArray<Float> dst) {
    Validator.copyToNdArrayArgs(this, dst);
    if (dst instanceof FloatDenseNdArray) {
      FloatDenseNdArray floatDst = (FloatDenseNdArray)dst;
      DataTransfer.execute(buffer, dimensions(), floatDst.buffer, floatDst.dimensions(), DataTransfer::ofFloat);
    } else {
      slowCopyTo(dst);
    }
    return this;
  }

  @Override
  public FloatNdArray read(FloatDataBuffer dst) {
    Validator.readToBufferArgs(this, dst);
    DataTransfer.execute(buffer, dimensions(), dst, DataTransfer::ofFloat);
    return this;
  }

  @Override
  public FloatNdArray write(FloatDataBuffer src) {
    Validator.writeFromBufferArgs(this, src);
    DataTransfer.execute(src, buffer, dimensions(), DataTransfer::ofFloat);
    return this;
  }

  protected FloatDenseNdArray(FloatDataBuffer buffer, Shape shape) {
    this(buffer, DimensionalSpace.create(shape));
  }

  @Override
  FloatDenseNdArray instantiate(DataBuffer<Float> buffer, DimensionalSpace dimensions) {
    return new FloatDenseNdArray((FloatDataBuffer) buffer, dimensions);
  }

  @Override
  public FloatDataBuffer buffer() {
    return buffer;
  }

  private final FloatDataBuffer buffer;

  private FloatDenseNdArray(FloatDataBuffer buffer, DimensionalSpace dimensions) {
    super(dimensions);
    this.buffer = buffer;
  }
}
