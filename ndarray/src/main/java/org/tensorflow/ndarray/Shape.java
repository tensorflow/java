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

package org.tensorflow.ndarray;

import java.util.Arrays;

/**
 * The shape of a Tensor or {@link NdArray}.
 *
 * <p>A {@code Shape} defines sizes along its axes. It may contain an unknown size for one of the
 * axes or may be totally unknown, in which case not even the number of axes is known. If the size
 * of an axis is unknown, {@link Shape#UNKNOWN_SIZE} should be used as its size.
 */
public final class Shape {

  /** The size of an unknown axis or the total unknown size for an unknown Shape. */
  public static long UNKNOWN_SIZE = -1L;

  /**
   * Creates a Shape representing an unknown number of dimensions.
   *
   * @return A Shape for which {@link Shape#isUnknown()} is true, never null.
   */
  public static Shape unknown() {
    return new Shape(null);
  }

  /**
   * Creates a Shape representing a scalar value.
   *
   * @return A Shape without dimensions for which {@link Shape#isScalar()} is true, never null.
   */
  public static Shape scalar() {
    return new Shape(new long[0]);
  }

  /**
   * Create a Shape representing a scalar or an N-dimensional value.
   *
   * <p>Creates a Shape representing a scalar or an N-dimensional value (N being at least 1),
   * with the provided size for each dimension. A -1 indicates that the size of the corresponding
   * dimension is unknown. If no sizes are provided, a Shape representing a scalar is created.
   * For example:
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
   *
   * // A scalar. For readability, you should prefer calling Shape.scalar()
   * Shape scalar = Shape.of()
   * }</pre>
   */
  public static Shape of(long... dimensionSizes) {
    if (dimensionSizes == null || dimensionSizes.length == 0) {
      return scalar();
    }
    return new Shape(dimensionSizes);
  }

  /**
   * Returns the total number of elements a Tensor with this Shape would have.
   *
   * <p>If {@link Shape#isUnknown()} is true or {@link Shape#hasUnknownDimension()} is true,
   * {@link Shape#UNKNOWN_SIZE} is returned.
   *
   * @return The total number of elements a Tensor with this shape would have if it can be
   * calculated, else {@link Shape#UNKNOWN_SIZE}.
   */
  public long size() {
    if (size == null) {
      size = computeSize(dimensionSizes);
    }
    return size;
  }

  /**
   * The size of the dimension with the given index.
   *
   * <p>If {@link Shape#isUnknown()} is true or the size of the dimension with the given index has
   * an unknown size, {@link Shape#UNKNOWN_SIZE} is returned.
   *
   * @param i the index of the dimension to get the size for. If this Shape has a known number of
   *          dimensions, it must be < {@link Shape#numDimensions()}. The index may be negative,
   *          in which case the position is counted from the end of the shape. E.g.:
   *          {@code size(-1)} returns the size of the last dimension, {@code size(-2)} the size of
   *          the second to last dimension etc.
   * @return The size of the dimension with the given index if known, {@link Shape#UNKNOWN_SIZE}
   *         otherwise.
   */
  public long size(int i) {
    if (dimensionSizes == null) {
      return UNKNOWN_SIZE;
    } else if (i >= 0) {
      return dimensionSizes[i];
    } else {
      return dimensionSizes[dimensionSizes.length + i];
    }
  }

  /**
   * Returns the number of dimensions of this Shape. -1 if unknown, 0 for a scalar, 1 for a vector,
   * 2 for a matrix etc.
   */
  public int numDimensions() {
    return dimensionSizes != null ? dimensionSizes.length : -1;
  }

  /** Returns whether one or more dimensions of this Shape have an unknown size. */
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

  /** Returns whether this Shape represents a scalar. */
  public boolean isScalar() {
    return dimensionSizes != null && dimensionSizes.length == 0;
  }

  /** Returns whether this Shape is the shape of a vector. */
  public boolean isVector() {
    return dimensionSizes != null && dimensionSizes.length == 1;
  }

  /** Returns whether this Shape is the shape of a matrix */
  public boolean isMatrix() {
    return dimensionSizes != null && dimensionSizes.length == 2;
  }

  /** Returns whether the number of dimensions of this Shape is unknown. */
  public boolean isUnknown() {
    return dimensionSizes == null;
  }

  /**
   * Returns a defensive copy of the this Shape's axes. Changes to the returned array to not
   * change this Shape's state. Returns null if {@link Shape#isUnknown()} is true.
   */
  public long[] asArray() {
    if (this.dimensionSizes == null) {
      return null;
    } else {
      return Arrays.copyOf(dimensionSizes, dimensionSizes.length);
    }
  }

  @Override
  public int hashCode() {
    return dimensionSizes != null ? Arrays.hashCode(dimensionSizes) : super.hashCode();
  }

  /**
   * Equals implementation for Shapes. Two Shapes are considered equal iff:
   *
   * <p><ul>
   * <li>the number of dimensions is defined and equal for both
   * <li>the size of each dimension is defined and equal for both
   * </ul>
   *
   * <p>If either Shape has unknown dimensions (even if they are the same in both) or if either
   * shape has an unknown number of dimensions (even if both return {@code true} for
   * {@link Shape#isUnknown()}), they are not considered equal! However, a shape will always
   * equal itself, even if it is unknown or contains unknown dimensions.
   */
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

  /** Succinct description of the Shape meant for debugging. */
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
   * Returns a 1-dimensional Shape with first dimension matching the first dimension of this Shape.
   */
  public Shape head() {
    return take(1);
  }

  /**
   * Returns an n-dimensional Shape with the dimensions matching the first n dimensions
   * of this shape
   *
   * @param n the number of leading dimensions to get, must be <= than {@link Shape#numDimensions()}
   * @return an n-dimensional Shape with the first n dimensions matching the first n dimensions
   * of this Shape
   */
  public Shape take(int n) {
    if (n > numDimensions()) {
      throw new ArrayIndexOutOfBoundsException("Cannot take " + n +
              " dimensions, shape has only " + numDimensions() + ".");
    }
    long[] newDimensions = new long[n];
    System.arraycopy(dimensionSizes, 0, newDimensions, 0, n);
    return Shape.of(newDimensions);
  }

  /** Returns a new Shape, with this Shape's first dimension removed. */
  public Shape tail() {
    if (dimensionSizes.length < 2) return Shape.of();
    return Shape.of(Arrays.copyOfRange(dimensionSizes, 1, dimensionSizes.length));
  }

  /**
   * Returns an n-dimensional Shape with the dimensions matching the last n dimensions
   * of this Shape.
   *
   * @param n the number of trailing dimensions to get, must be <= than
   *          {@link Shape#numDimensions()}
   * @return an n-dimensional shape with the dimensions matching the last n dimensions of this
   *         Shape, never null
   */
  public Shape takeLast(int n) {
    if (n > numDimensions()) {
      throw new ArrayIndexOutOfBoundsException("Cannot take last " + n +
              " dimensions, shape has only " + numDimensions() + ".");
    }
    long[] newDimensions = new long[n];
    System.arraycopy(dimensionSizes, numDimensions() - n, newDimensions, 0, n);
    return Shape.of(newDimensions);
  }

  /**
   * Returns a new Shape, with a new first dimension added. In order for this call to succeed,
   * {@link Shape#isUnknown()} must be {@code false}.
   *
   * @param firstDimension the dimension to prepend
   * @return a new shape with the given dimension first, followed by this Shape's dimensions,
   *         never null
   */
  public Shape prepend(long firstDimension) {
    long[] newDimensions = new long[dimensionSizes.length + 1];
    newDimensions[0] = firstDimension;
    System.arraycopy(dimensionSizes, 0, newDimensions, 1, dimensionSizes.length);

    return Shape.of(newDimensions);
  }

  /**
   * Returns a new Shape, with a new last dimension added. In order for this call to succeed,
   * {@link Shape#isUnknown()} must be {@code false}.
   *
   * @param lastDimension the dimension to append
   * @return a new Shape with this Shape's dimensions followed by the given dimension, never null
   */
  public Shape append(long lastDimension) {
    long[] newDimensions = new long[dimensionSizes.length + 1];
    newDimensions[newDimensions.length - 1] = lastDimension;
    System.arraycopy(dimensionSizes, 0, newDimensions, 0, dimensionSizes.length);

    return Shape.of(newDimensions);
  }

  /**
   * Returns a new Shape, with another Shape's dimensions prepended.
   * For both this Shape and the other Shape, {@link Shape#isUnknown()} must return false.
   * E.g. {@code Shape.of(3,4).prepend(Shape.of(1,2)) => Shape.of(1,2,3,4) }
   *
   * @param other another Shape, must not be {@code null}, must not be unknown
   * @return A new Shape consisting of the given Shapes's dimensions followed by this Shape's
   *         dimensions, never null
   */
  public Shape prepend(Shape other) {
    long[] newDimensions = new long[other.dimensionSizes.length + dimensionSizes.length];
    System.arraycopy(other.dimensionSizes, 0,
                     newDimensions, 0, other.dimensionSizes.length);
    System.arraycopy(dimensionSizes, 0,
                     newDimensions, other.dimensionSizes.length, dimensionSizes.length);
    return Shape.of(newDimensions);
  }

  /**
   * Returns a new Shape, with another Shapes' dimensions appended.
   * For both this Shape and the other Shape, {@link Shape#isUnknown()} must return false.
   * E.g. @code Shape.of(3,4).append(Shape.of(1,2)) => Shape.of(3,4,1,2) }
   *
   * @param other another Shape, must not be {@code null}, must not be unknown
   * @return A new Shape consisting of this Shapes's dimensions followed by the given Shape's
   *         dimensions
   */
  public Shape append(Shape other) {
    long[] newDimensions = new long[dimensionSizes.length + other.dimensionSizes.length];
    System.arraycopy(dimensionSizes, 0,
            newDimensions, 0, dimensionSizes.length);
    System.arraycopy(other.dimensionSizes, 0,
            newDimensions, dimensionSizes.length, other.dimensionSizes.length);
    return Shape.of(newDimensions);
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
