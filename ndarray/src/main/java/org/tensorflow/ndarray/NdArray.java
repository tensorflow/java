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

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.index.Index;

/**
 * A data structure of N-dimensions.
 *
 * <p>The `NdArray` interface creates an abstraction between the physical storage of a data record,
 * which can be linear or segmented, and its logical representation. In general, they achieve
 * better performances than standard multi-dimensional arrays in Java by mapping directly linear
 * data segments in memory.
 *
 * <p>Like {@link DataBuffer}, {@code NdArray} instances support 64-bits indexing so they can be
 * used to map very large data records. They also support special coordinates that allow traversing
 * their values in any direction or to select only a subset of them.
 *
 * <p>Example of usage:
 * <pre>{@code
 *    // Creates a 2x3x2 matrix (of rank 3)
 *    FloatNdArray matrix3d = NdArrays.ofFloats(shape(2, 3, 2));
 *
 *    // Initialize sub-matrices data with vectors
 *    matrix.set(NdArrays.vectorOf(1.0f, 2.0f), 0, 0)
 *          .set(NdArrays.vectorOf(3.0f, 4.0f), 0, 1)
 *          .set(NdArrays.vectorOf(5.0f, 6.0f), 0, 2)
 *          .set(NdArrays.vectorOf(7.0f, 8.0f), 1, 0)
 *          .set(NdArrays.vectorOf(9.0f, 10.0f), 1, 1)
 *          .set(NdArrays.vectorOf(11.0f, 12.0f), 1, 2);
 *
 *    // Access the second 3x2 matrix (of rank 2)
 *    FloatNdArray matrix = matrix3d.get(1);
 *
 *    // Access directly the float value at (1, 0) from the second matrix
 *    assertEquals(9.0f, matrix.getFloat(1, 0));
 * }</pre>
 *
 * @param <T> the type of values to be mapped
 */
public interface NdArray<T> {

  /**
   * @return the shape of this N-dimensional array
   */
  Shape shape();

  /**
   * @return the rank of this N-dimensional array
   */
  default int rank() {
    return shape().numDimensions();
  }

  /**
   * Computes and returns the total size of this N-dimensional array, in number of values.
   *
   * <p>For example, given a 3x3x2 matrix, the return value will be 18.
   * @return total size of this nd array
   */
  default long size() {
    return shape().size();
  }

  /**
   * Returns a sequence of all elements at a given dimension.
   *
   * <p>Logically, the N-dimensional array can be flatten in a single vector, where the scalars of
   * the {@code (n - 1)}th element precedes those of the {@code (n)}th element, for a total of
   * {@link #size()} values.
   *
   * <p>For example, given a {@code n x m} matrix on the {@code [x, y]} axes, elements are iterated in
   * the following order:
   * <pre>
   * x<sub>0</sub>y<sub>0</sub>, x<sub>0</sub>y<sub>1</sub>, ..., x<sub>0</sub>y<sub>m-1</sub>, x<sub>1</sub>y<sub>0</sub>, x<sub>1</sub>y<sub>1</sub>, ..., x<sub>n-1</sub>y<sub>m-1</sub>
   * </pre>
   *
   * <p>The returned sequence can then be iterated to visit each elements, either by calling
   * {@link NdArraySequence#forEach(Consumer)} or {@link NdArraySequence#forEachIndexed(BiConsumer)}.
   * <pre>{@code
   *    // Iterate matrix for initializing each of its vectors
   *    matrixOfFloats.elements(0).forEach(v -> {
   *      v.set(vector(1.0f, 2.0f, 3.0f));
   *    });
   *
   *    // Iterate a vector for reading each of its scalar
   *    vectorOfFloats.scalars().forEachIdx((coords, s) -> {
   *      System.out.println("Value " + s.getFloat() + " found at " + coords);
   *    });
   * }</pre>
   *
   * @params dimensionIdx index of the dimension
   * @return an {@code NdArray} sequence
   * @throws IllegalArgumentException if {@code dimensionIdx} is greater or equal to the total
   *                                  number of dimensions of this array
   */
  NdArraySequence<? extends NdArray<T>> elements(int dimensionIdx);

  /**
   * Returns a sequence of all scalars in this array.
   *
   * <p>This is equivalent to call {@code elements(shape().numDimensions() - 1)}
   *
   * @return an {@code NdArray} sequence
   */
  NdArraySequence<? extends NdArray<T>> scalars();

  /**
   * Creates a multi-dimensional view (or slice) of this array by mapping one or more dimensions
   * to the given index selectors.
   *
   * <p>Slices allow to traverse an N-dimensional array in any of its axis and/or to filter only
   * elements of interest. For example, for a given matrix on the {@code [x, y]} axes, it is
   * possible to iterate elements at {@code y=0} for all {@code x}.
   *
   * <p>Any changes applied to the returned slice affect the data of this array as well, as there
   * is no copy involved.
   *
   * <p>Example of usage:
   * <pre>{@code
   *    FloatNdArray matrix3d = NdArrays.ofFloats(shape(3, 2, 4));  // with [x, y, z] axes
   *
   *    // Iterates elements on the x axis by preserving only the 3rd value on the z axis,
   *    // (i.e. [x, y, 2])
   *    matrix3d.slice(all(), all(), at(2)).elements(0).forEach(m -> {
   *      assertEquals(shape(2), m); // y=2, z=0 (scalar)
   *    });
   *
   *    // Creates a slice that contains only the last element of the y axis and elements with an
   *    // odd `z` coordinate.
   *    FloatNdArray slice = matrix3d.slice(all(), at(1), odd());
   *    assertEquals(shape(3, 2), slice.shape());  // x=3, y=0 (scalar), z=2 (odd coordinates)
   *
   *    // Iterates backward the elements on the x axis
   *    matrix3d.slice(flip()).elements(0).forEach(m -> {
   *      assertEquals(shape(2, 4), m);  // y=2, z=4
   *    });
   * }</pre>
   *
   * @param indices index selectors per dimensions, starting from dimension 0 of this array.
   * @return the element resulting of the index selection
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their
   * respective dimension
   */
  NdArray<T> slice(Index... indices);

  /**
   * Returns the N-dimensional element of this array at the given coordinates.
   *
   * <p>Elements of any of the dimensions of this array can be retrieved. For example, if the number
   * of coordinates is equal to the number of dimensions of this array, then a rank-0 (scalar) array
   * is returned, which value can then be obtained by calling `array.getObject()`.
   *
   * <p>Any changes applied to the returned elements affect the data of this array as well, as there
   * is no copy involved.
   *
   * <p>Note that invoking this method is an equivalent and more efficient way to slice this array
   * on single scalar, i.e. {@code array.get(x, y, z)} is equal to
   * {@code array.slice(at(x), at(y), at(z))}
   *
   * @param coordinates coordinates of the element to access, none will return this array
   * @return the element at this index
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their
   * respective dimension
   */
  NdArray<T> get(long... coordinates);

  /**
   * Assigns the value of the N-dimensional element found at the given coordinates.
   *
   * <p>The number of coordinates provided can be anywhere between 0 and rank - 1. For example:
   * <pre>{@code
   *  FloatNdArray matrix = NdArrays.ofFloats(shape(2, 2));  // matrix rank = 2
   *  matrix.set(vector(10.0f, 20.0f), 0);  // success
   *  matrix.set(scalar(10.0f), 1, 0); // success
   * }</pre>
   *
   * @param coordinates coordinates of the element to assign
   * @return this array
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their
   * respective dimension
   */
  NdArray<T> set(NdArray<T> src, long... coordinates);

  /**
   * Returns the value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  FloatNdArray matrix = NdArrays.ofFloats(shape(2, 2));  // matrix rank = 2
   *  matrix.getObject(0, 1);  // succeeds, returns 0.0f
   *  matrix.getObject(0);  // throws IllegalRankException
   *
   *  FloatNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.getObject();  // succeeds, returns 0.0f
   * }</pre>
   *
   * Note: if this array stores values of a primitive type, prefer the usage of the specialized
   * method in the subclass for that type. For example, {@code floatArray.getFloat(0); }.
   *
   * @param coordinates coordinates of the scalar to resolve
   * @return value of that scalar
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their
   * respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar
   * element
   */
  T getObject(long... coordinates);

  /**
   * Assigns the value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  FloatNdArray matrix = NdArrays.ofFloats(shape(2, 2));  // matrix rank = 2
   *  matrix.setObject(10.0f, 0, 1);  // succeeds
   *  matrix.setObject(10.0f, 0);  // throws IllegalRankException
   *
   *  FloatNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.setObject(10.0f);  // succeeds
   * }</pre>
   *
   * Note: if this array stores values of a primitive type, prefer the usage of the specialized
   * method in the subclass for that type. For example, {@code floatArray.setFloat(10.0f, 0); }
   *
   * @param coordinates coordinates of the scalar to assign
   * @return this array
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their
   * respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar
   * element
   */
  NdArray<T> setObject(T value, long... coordinates);

  /**
   * Copy the content of this array to the destination array.
   *
   * <p>The {@link #shape()} of the destination array must be equal to the shape of this array, or
   * an exception is thrown. After the copy, the content of both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst array to receive a copy of the content of this array
   * @return this array
   * @throws IllegalArgumentException if the shape of {@code dst} is not equal to the shape of this
   * array
   */
  NdArray<T> copyTo(NdArray<T> dst);

  /**
   * Read the content of this N-dimensional array into the destination buffer.
   *
   * <p>The size of the buffer must be equal or greater to the {@link #size()} of this
   * array, or an exception is thrown. After the copy, content of the buffer and of the array can be
   * altered independently, without affecting each other.
   *
   * @param dst the destination buffer
   * @return this array
   * @throws java.nio.BufferOverflowException if the buffer cannot hold the content of this array
   * @see DataBuffer#size()
   */
  NdArray<T> read(DataBuffer<T> dst);

  /**
   * Write the content of this N-dimensional array from the source buffer.
   *
   * <p>The size of the buffer must be equal or greater to the {@link #size()} of this
   * array, or an exception is thrown. After the copy, content of the buffer and of the array can be
   * altered independently, without affecting each other.
   *
   * @param src the source buffer
   * @return this array
   * @throws java.nio.BufferUnderflowException if the buffer has not enough remaining data to write
   * into this array
   * @see DataBuffer#size()
   */
  NdArray<T> write(DataBuffer<T> src);

  /**
   * Checks equality between n-dimensional arrays.
   *
   * <p>An array is equal to another object if this object is another {@link NdArray} of the
   * same shape, type and the elements are equal and in the same order. For example:
   *
   * <pre>{@code
   * IntNdArray array = NdArrays.ofInts(Shape.of(2, 2))
   *    .set(NdArrays.vectorOf(1, 2), 0)
   *    .set(NdArrays.vectorOf(3, 4), 1);
   *
   * assertEquals(array, StdArrays.ndCopyOf(new int[][] {{1, 2}, {3, 4}}));  // true
   * assertEquals(array, StdArrays.ndCopyOf(new Integer[][] {{1, 2}, {3, 4}}));  // true, as Integers are equal to ints
   * assertNotEquals(array, NdArrays.vectorOf(1, 2, 3, 4));  // false, different shapes
   * assertNotEquals(array, StdArrays.ndCopyOf(new int[][] {{3, 4}, {1, 2}}));  // false, different order
   * assertNotEquals(array, StdArrays.ndCopyOf(new long[][] {{1L, 2L}, {3L, 4L}}));  // false, different types
   * }</pre>
   *
   * <p>Note that the computation required to verify equality between two arrays can be expensive
   * in some cases and therefore, it is recommended to not use this method in a critical path
   * where performances matter.
   *
   * @param obj object to compare this array with
   * @return true if this array is equal to the provided object
   */
  @Override
  boolean equals(Object obj);
}
