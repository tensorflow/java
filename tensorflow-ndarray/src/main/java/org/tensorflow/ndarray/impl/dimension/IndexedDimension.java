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
package org.tensorflow.ndarray.impl.dimension;

import org.tensorflow.ndarray.index.Index;

final class IndexedDimension extends AbstractDimension {

  @Override
  public long numElements() {
    return numElements;
  }
  
  @Override
  public long positionOf(long coord) {
    if (coord >= numElements()) {
      throw new IndexOutOfBoundsException();
    }
    return originalDimension.positionOf(index.mapCoordinate(coord, originalDimension));
  }
  
  @Override
  public boolean isSegmented() {
    // TODO (karllessard) for now we consider all indexed dimensions as segmented but might depend
    //      on the actual index
    return true;
  }

  @Override
  public long elementSize() {
    return originalDimension.elementSize();  // indices do not change the size of an inner element
  }

  @Override
  public long physicalSize() {
    // TODO (karllessard) we consider this dimension takes the same amount of memory that the
    //      original one but might depend on the actual index
    return originalDimension.physicalSize();
  }

  @Override
  public String toString() {
    return String.valueOf(numElements());
  }

  IndexedDimension(Index index, Dimension originalDimension) {
    this.index = index;
    this.originalDimension = originalDimension;
    this.numElements = index.numElements(originalDimension);
  }

  private final Index index;
  private final Dimension originalDimension;
  private final long numElements;
}
