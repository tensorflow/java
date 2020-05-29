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

package org.tensorflow.tools;

import java.util.Arrays;

public final class Shape {

  public static long UNKNOWN_SIZE = -1L;

  /** Create a Shape representing an unknown number of dimensions. */
  public static Shape unknown() {
    return new Shape(null);
  }

  /** Create a Shape representing a scalar value. */
  public static Shape scalar() {
    return new Shape(new long[0]);
  }

  /**
   * Create a Shape representing an N-dimensional value.
   *
   * <p>Creates a Shape representing an N-dimensional value (N being at least 1), with the provided
   * size for each dimension. A -1 indicates that the size of the corresponding dimension is
   * unknown. For example:
   *
   * <pre>{@code
   * // A 2-element vector.
   * Shape vector = Shape.of(2);
   *
   * // A 2x3 matrix.
   * Shape matrix = Shape.of(2, 3);
   *
   * // A matrix with 4 columns but an unknown number of rows.
   * // This is typically used to indicate the shape of tensors that represent
   * // a variable-sized batch of values. The Shape below might represent a
   * // variable-sized batch of 4-element vectors.
   * Shape batch = Shape.of(-1, 4);
   * }</pre>
   */
  public static Shape of(long... dimensionSizes) {
    if (dimensionSizes == null || dimensionSizes.length == 0) {
      return scalar();
    }
    return new Shape(dimensionSizes);
  }

  public long size() {
    if (size == null) {
      size = computeSize(dimensionSizes);
    }
    return size;
  }

  public long size(int i) {
    return dimensionSizes != null ? dimensionSizes[i] : UNKNOWN_SIZE;
  }

  public int numDimensions() {
    return dimensionSizes != null ? dimensionSizes.length : -1;
  }

  public boolean hasUnknownDimension() {
    if (dimensionSizes == null) {
      return true;
    }
    for (long dimSize : dimensionSizes) {
      if (dimSize == UNKNOWN_SIZE) {
        return true;
      }
    }
    return false;
  }

  public long[] asArray() {
    return dimensionSizes;
  }

  @Override
  public int hashCode() {
    return dimensionSizes != null ? Arrays.hashCode(dimensionSizes) : super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    // Shapes are equivalent if all of their dimensions are equals
    if (obj instanceof Shape) {
      Shape otherShape = (Shape) obj;
      if (otherShape.hasUnknownDimension()) {
        return false;
      }
      return Arrays.equals(dimensionSizes, otherShape.dimensionSizes);
    }
    return false;
  }

  /** Succinct description of the shape meant for debugging. */
  @Override
  public String toString() {
    return Arrays.toString(dimensionSizes);
  }

  private Shape(long[] dimensionSizes) {
    this.dimensionSizes = dimensionSizes;
  }

  private final long[] dimensionSizes;
  private Long size;

  /**
   * Returns a 1-dimension shape with first dimension matching the first dimensions of this shape.
   */
  public Shape head() {
    return Shape.of(size(0));
  }

  /** Returns a new shape, with this shape's first dimension removed. */
  public Shape tail() {
    if (dimensionSizes.length < 2) return Shape.of();
    return Shape.of(Arrays.copyOfRange(dimensionSizes, 1, dimensionSizes.length));
  }

  /** Returns a new shape, with a new first dimension added */
  public Shape prepend(long firstDimension) {
    long[] newDimensions = new long[dimensionSizes.length + 1];
    newDimensions[0] = firstDimension;
    System.arraycopy(dimensionSizes, 0, newDimensions, 1, dimensionSizes.length);

    return Shape.of(newDimensions);
  }

  public boolean isKnown(int i) {
    return dimensionSizes[i] != -1;
  }

  public void assertKnown(int i) {
    if (!isKnown(i)) {
      throw new IllegalStateException("Dimension " + i + " in shape needs to be known.");
    }
  }

  public Shape replaceFirst(long dim) {
    return replace(0, dim);
  }

  public Shape replaceLast(long dim) {
    return replace(dimensionSizes.length - 1, dim);
  }

  public Shape replace(int i, long dim) {
    Shape newShape = new Shape(Arrays.copyOf(dimensionSizes, dimensionSizes.length));
    newShape.dimensionSizes[i] = dim;
    return newShape;
  }

  private static long computeSize(long[] dimensionSizes) {
    if (dimensionSizes == null) {
      return UNKNOWN_SIZE;
    }
    long computedSize = 1L;
    for (long dimensionSize : dimensionSizes) {
      if (dimensionSize == UNKNOWN_SIZE) {
        return UNKNOWN_SIZE;
      }
      computedSize *= dimensionSize;
    }
    return computedSize;
  }
}
