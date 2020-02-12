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
package org.tensorflow.tools.ndarray.impl;

import org.tensorflow.tools.Shape;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.NdArraySequence;
import org.tensorflow.tools.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.tools.ndarray.impl.sequence.ElementSequence;

@SuppressWarnings("unchecked")
public abstract class AbstractNdArray<T, U extends NdArray<T>> implements NdArray<T> {

  public abstract U slice(long position, DimensionalSpace dimensions);

  public DimensionalSpace dimensions() {
    return dimensions;
  }

  @Override
  public Shape shape() {
    return dimensions.shape();
  }

  @Override
  public NdArraySequence<U> elements(int dimensionIdx) {
    if (dimensionIdx >= shape().numDimensions()) {
      throw new IllegalArgumentException("Cannot iterate elements in dimension '" + dimensionIdx +
          "' of array with shape " + shape());
    }
    return ElementSequence.create(this, dimensionIdx);
  }

  @Override
  public NdArraySequence<U> scalars() {
    return ElementSequence.create(this, shape().numDimensions() - 1);  // negative if this array is a scalar
  }

  protected AbstractNdArray(DimensionalSpace dimensions) {
    this.dimensions = dimensions;
  }

  protected void slowCopyTo(NdArray<T> array) {
    scalars().forEachIndexed((coords, e) -> array.setObject(e.getObject(), coords));
  }

  private DimensionalSpace dimensions;
}
