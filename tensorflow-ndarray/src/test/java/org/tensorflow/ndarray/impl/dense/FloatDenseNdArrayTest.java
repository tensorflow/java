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

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.FloatNdArrayTestBase;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.index.Indices;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloatDenseNdArrayTest extends FloatNdArrayTestBase {

  @Override
  protected FloatNdArray allocate(Shape shape) {
    return NdArrays.ofFloats(shape);
  }

  @Override
  protected DataBuffer<Float> allocateBuffer(long size) {
    return DataBuffers.ofFloats(size);
  }

  @Test
  public void testSlice() {
    Shape shape = Shape.of(3, 4);
    Float[] values = {
      1f, 0f, 0f, 0f,
      0f, 0f, 2f, 0f,
      0f, 0f, 0f, 0f
    };

    float[] expected = {0, 0, 2, 0, 0, 0};

    FloatDataBuffer buffer = (FloatDataBuffer) allocateBuffer(shape.size());
    buffer.write(values);
    FloatNdArray instance = FloatDenseNdArray.create(buffer, shape);

    FloatNdArray sliceInstance = instance.slice(Indices.all(), Indices.sliceFrom(2));
    // check the values of the slice against the  original  array
    AtomicInteger i = new AtomicInteger();
    sliceInstance
        .scalars()
        .forEachIndexed((idx, f) -> assertEquals(expected[i.getAndIncrement()], f.getFloat()));

    // check values from elements(0) of a slice against the  original  array
    i.set(0);
    sliceInstance
        .elements(0)
        .forEachIndexed(
            (idx, l) ->
                l.scalars()
                    .forEachIndexed(
                        (lidx, f) -> assertEquals(expected[i.getAndIncrement()], f.getFloat())));
  }
}
