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

package org.tensorflow.nio.nd;

import java.util.Arrays;

import org.tensorflow.nio.nd.impl.dimension.Dimension;
import org.tensorflow.nio.nd.impl.dimension.Dimensions;
import org.tensorflow.nio.nd.index.Index;

/** The possibly partially known shape of a tensor produced by an operation. */
public final class Shape {

  public static final long UNKNOWN_SIZE = -1L;

  /**
   * Create a Shape representing an N-dimensional value.
   *
   * <p>Creates a Shape representing an N-dimensional value (N being at least 1), with the provided
   * size for each dimension. A -1 indicates that the size of the corresponding dimension is
   * unknown. For example:
   *
   * <pre>{@code
   * // A 2-element vector.
   * Shape vector = Shape.create(2);
   *
   * // A 2x3 matrix.
   * Shape matrix = Shape.create(2, 3);
   *
   * // A matrix with 4 columns but an unknown number of rows.
   * // This is typically used to indicate the shape of tensors that represent
   * // a variable-sized batch of values. The Shape below might represent a
   * // variable-sized batch of 4-element vectors.
   * Shape batch = Shape.create(-1, 4);
   * }</pre>
   */
  public static Shape create(long... dimensionSizes) {
    if (dimensionSizes == null) {
      return new Shape(new Dimension[0]);
    }
    Dimension[] dimensions = new Dimension[dimensionSizes.length];

    // Start from the last dimension, where all elements are continuous
    for (int i = dimensionSizes.length - 1, positionStep = 1; i >= 0; --i) {
      if (dimensionSizes[i] == UNKNOWN_SIZE) {
        dimensions[i] = Dimensions.unknown();
      } else {
        dimensions[i] = Dimensions.axis(dimensionSizes[i], positionStep);
      }
      positionStep *= dimensions[i].numElements();
    }
    return new Shape(dimensions);
  }
  
  public Shape mapTo(Index[] indices) {
    if (indices.length > dimensions.length) {
      throw new ArrayIndexOutOfBoundsException();
    }
    Dimension[] mappedDimensions = Arrays.copyOf(dimensions, dimensions.length);
    for (int i = 0; i < indices.length; ++i) {
      mappedDimensions[i] = indices[i].apply(dimensions[i]);
    }
    return new Shape(mappedDimensions);
  }

  /**
   * Number of dimensions represented by this shape.
   *
   * @return -1 if the number of dimensions is unknown, 0 if the shape represents a scalar, 1 for a
   *     vector, 2 for a matrix etc.
   */
  public int numDimensions() {
    return dimensions.length;
  }
  
  public long numElements(int i) {
    return dimensions[i].numElements();
  }
  
  public Dimension dimension(int i) {
    return dimensions[i];
  }

  public boolean hasUnknownDimension() {
    return Arrays.stream(dimensions).anyMatch(d -> d.numElements() == UNKNOWN_SIZE);
  }
  
  public long size() {
    return size;
  }

  public Shape subshape(int dimensionStart) {
    return new Shape(Arrays.copyOfRange(dimensions, dimensionStart, dimensions.length));
  }

  public long[] toArray() {
    return Arrays.stream(dimensions).mapToLong(Dimension::numElements).toArray();
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(dimensions);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    // Shapes are equivalent if all of their dimensions are equals
    if (obj instanceof Shape) {
      Shape otherShape = (Shape)obj;
      return Arrays.equals(dimensions, otherShape.dimensions);
    }
    return false;
  }

  /** Succinct description of the shape meant for debugging. */
  @Override
  public String toString() {
    return Arrays.toString(dimensions);
  }

  private Shape(Dimension[] dimensions) {
    this.dimensions = dimensions;
    this.size = computeShapeSize(dimensions);
  }

  private final Dimension[] dimensions;
  private final long size;

  private static long computeShapeSize(Dimension[] dimensions) {
    long size = 1L;
    for (Dimension dimension: dimensions) {
      long dimensionSize = dimension.numElements();
      if (dimensionSize > 0) {
        size *= dimensionSize;
      } else if (dimensionSize == Shape.UNKNOWN_SIZE) {
        return UNKNOWN_SIZE;
      }
    }
    return size;
  }
}
