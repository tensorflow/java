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

import org.junit.Test;
import org.tensorflow.op.Index;

public class IndexingTest {

  @Test
  public void testIndexMerge() {
    Indexing.StridedSliceArgs args = Indexing.mergeIndexes(new Index[]{
            Index.point(2),
            Index.point(1, true),
            Index.all(),
            Index.newAxis(),
            Index.ellipses(),
            Index.slice(Index.all(), 10),
            Index.slice(10, null, 2)
        }
    );

    assertArrayEquals(args.begin, new int[]{2, 1, 0, 0, 0, 0, 10});
    assertArrayEquals(args.end, new int[]{3, 2, 0, 0, 0, 10, 0});
    assertArrayEquals(args.strides, new int[]{1, 1, 1, 1, 1, 1, 2});
    assertEquals(args.beginMask, 0b0100100);
    assertEquals(args.endMask, 0b1000100);
    assertEquals(args.ellipsisMask, 0b0010000);
    assertEquals(args.newAxisMask, 0b0001000);
    assertEquals(args.shrinkAxisMask, 0b0000001);

  }

  @Test
  public void testStridedSliceIndex(){
    //TODO test op
  }

}
