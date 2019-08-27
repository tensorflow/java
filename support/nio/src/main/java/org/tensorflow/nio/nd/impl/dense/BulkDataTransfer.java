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
import org.tensorflow.nio.nd.impl.dimension.Dimension;

class BulkDataTransfer<T> {

  @FunctionalInterface
  interface BulkCopy<T> {
    void invoke(DataBuffer<T> arrayBuffer, long bulkCopySize);
  }
  
  static <T> BulkDataTransfer<T> create(AbstractDenseNdArray<T, ?> array) {
    int bulkCopyDimensionIdx = -1;
    long bulkCopySize = 1L;

    // Find what are the biggest chunk of data that we can copy in bulk by starting from the last dimension of this array and
    // iterating backward until we hit a dimension that is segmented (if any)
    for (int i = array.shape().numDimensions() - 1; i >= 0; --i) {
      Dimension dim = array.shape().dimension(i);
      if (dim.isSegmented()) {
        break;
      }
      bulkCopyDimensionIdx = i;
      bulkCopySize *= dim.numElements();
    }
    if (bulkCopyDimensionIdx < 0) {
      throw new IllegalArgumentException("This array cannot be copied by bulk, since its last dimension is segmented");
    }
    return new BulkDataTransfer<>(array, bulkCopyDimensionIdx, bulkCopySize);
  }
  
  void execute(BulkCopy<T> bulkCopy) {
    execute(bulkCopy, array, 0);
  }

  private final AbstractDenseNdArray<T, ?> array;  // The array we want to copy in bulk
  private final int bulkCopyDimensionIdx;  // The first dimension of this array that can be copied in bulk
  private final long bulkCopySize;  // The number of values that can be copied in a single bulk copy

  private BulkDataTransfer(AbstractDenseNdArray<T, ?> array, int bulkCopyDimensionIdx, long bulkCopySize) {
    this.array = array;
    this.bulkCopyDimensionIdx = bulkCopyDimensionIdx;
    this.bulkCopySize = bulkCopySize;
  }

  private void execute(BulkCopy<T> bulkCopy, AbstractDenseNdArray<T, ?> element, int dimensionIdx) {
    if (dimensionIdx == bulkCopyDimensionIdx) {
      bulkCopy.invoke(element.buffer().duplicate(), bulkCopySize);
    } else {
      element.childElements().forEach(e -> execute(bulkCopy, (AbstractDenseNdArray<T, ?>)e, dimensionIdx + 1));
    }
  }
}
