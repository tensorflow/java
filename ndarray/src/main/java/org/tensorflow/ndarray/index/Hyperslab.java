/*
 * Copyright 2020 Matteo Di Giovinazzo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow.ndarray.index;

import org.tensorflow.ndarray.impl.dimension.Dimension;

/**
 * A hyperslab is a rectangular pattern defined by four arrays.
 * 
 * The {@code start} defines the origin of the hyperslab in the original coordinates.
 * The {@code stride} is the number of elements to increment between selected elements.
 * A stride of '1' is every element, a stride of '2' is every second element, etc.
 * The default stride is 1.
 * The {@code count} is the number of elements in the hyperslab selection.
 * When the stride is 1, the selection is a hyper rectangle with a corner at {@code start}
 * and size {@code count[0]} by {@code count[1]} by ...
 * When stride is greater than one, the hyperslab bounded by start and the corners 
 * defined by {@code stride[n] * count[n]}.
 * The {@code block} is a count on the number of repetitions of the hyperslab. 
 * The default block size is '1', which is one hyperslab. A block of 2 would be 
 * two hyperslabs in that dimension, with the second starting at {@code start[n]+ (count[n] * stride[n]) + 1}.
 * 
 * @see https://portal.hdfgroup.org/display/HDF5/Reading+From+or+Writing+To+a+Subset+of+a+Dataset
 * @see https://portal.hdfgroup.org/display/HDF5/H5S_SELECT_HYPERSLAB
 * @see https://support.hdfgroup.org/HDF5/doc1.6/UG/12_Dataspaces.html
 * @author Matteo Di Giovinazzo
 */
final class Hyperslab implements Index {

  @Override
  public long numElements(Dimension dimension) {
    return count * block;
  }

  @Override
  public long mapCoordinate(long coordinate, Dimension dimension) {
    return start + stride * (coordinate / block) + (coordinate % block);
  }

  @Override
  public Dimension apply(Dimension dim) {
    return dim.withIndex(this);
  }

  @Override
  public boolean isPoint() {
    return false;
  }

  Hyperslab(long start, long stride, long count, long block) {
    this.start = start;
    this.stride = stride;
    this.count = count;
    this.block = block;
  }

  private final long start;
  private final long stride;
  private final long count;
  private final long block;
}
