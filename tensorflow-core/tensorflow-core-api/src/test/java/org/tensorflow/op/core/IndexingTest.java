/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/
package org.tensorflow.op.core;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.ndarray.index.Indices;
import org.tensorflow.op.JavaScope;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TFloat32;

public class IndexingTest {

  // [2, 1:2, :, tf.newaxis, ..., :4, 4::2]
  private static final Index[] slice = new Index[]{
      Indices.at(2),
      Indices.at(1, true),
      Indices.all(),
      Indices.newAxis(),
      Indices.ellipsis(),
      Indices.sliceTo(4),
      Indices.sliceFrom(4, 2)
  };

  @Test
  public void testIndexMerge() {
    StridedSliceHelper.StridedSliceArgs args = StridedSliceHelper.mergeIndexes(slice);

    assertArrayEquals(new int[]{2, 1, 0, 0, 0, 0, 4}, args.begin);
    assertArrayEquals(new int[]{3, 2, 0, 0, 0, 4, 0}, args.end);
    assertArrayEquals(new int[]{1, 1, 1, 1, 1, 1, 2}, args.strides);
    assertEquals(0b0100100, args.beginMask);
    assertEquals(0b1000100, args.endMask);
    assertEquals(0b0010000, args.ellipsisMask);
    assertEquals(0b0001000, args.newAxisMask);
    assertEquals(0b0000001, args.shrinkAxisMask);

  }

  @Test
  public void testStridedSliceIndex() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new JavaScope(g);
      long[] shape = {10, 10, 10, 10, 10, 10, 10, 10};
      Zeros<TFloat32> op = Zeros.create(scope, Constant.vectorOf(scope, shape), TFloat32.class);
      StridedSlice<TFloat32> output = StridedSliceHelper.stridedSlice(scope, op, slice);
      try (TFloat32 result = (TFloat32) sess.runner().fetch(output.asOutput()).run().get(0)) {
        // expected shape from Python tensorflow
        assertEquals(Shape.of(1, 10, 1, 10, 10, 10, 4, 3), result.shape(),
            "Slice index didn't match expected (Python)");
      }
    }
  }

}
