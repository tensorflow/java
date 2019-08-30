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

import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.nd.index.Index;
import org.tensorflow.nio.nd.impl.iterator.ValueIterable;
import org.tensorflow.nio.nd.impl.iterator.ValueIterator;

/**
 * A data structure of N-dimensions.
 *
 * <p>The `NdArray` interface creates an abstraction between the physical storage of a data record,
 * which can be linear or segmented, and its logical representation. In general, they achieve
 * better performances than standard multi-dimensional arrays in Java by mapping directly the
 * data in memory.
 *
 * <p>Like {@link DataBuffer}, {@code NdArray} instances support 64-bits indexation so they can be
 * used to map very large data records. They also support special indices that to traverse their
 * values in any direction or to select only a subset of them.
 *
 * <p>Example of usage:
 * <pre>{@code
 *    import static org.tensorflow.nio.StaticApi.*;
 *
 *    // Creates a 3x2x2 matrix (of rank 3)
 *    FloatNdArray matrix3d = ndArrayOfFloats(shape(3, 2, 2));
 *
 *    // Access the second 2x2 matrix (of rank 2)
 *    FloatNdArray matrix = matrix3d.at(1);
 *
 *    // Initialize second matrix data with an array of floats
 *    matrix.write(new float[] { 1.0f, 2.0f, 3.0f, 4.0f });
 *
 *    // Access directly the float value at (1, 1, 0) from the 3D-matrix
 *    assertEquals(3.0f, matrix3d.get(1, 1, 0));
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
  long size();

  /**
   * Returns an iteration of the elements on the first dimension of this N-dimensional array.
   *
   * <p>For example, given a {@code n x m} matrix on the {@code [x, y]} axes, the return object can be used
   * to iterate all {@code y} vectors in the following order:
   * <pre>
   * x<sub>0</sub>, x<sub>1</sub>, ..., x<sub>n-1</sub>
   * </pre>
  *
   * @return an iteration of N-dimensional arrays
   * @throws IllegalRankException if this array is a scalar (rank 0)
   */
  Iterable<? extends NdArray<T>> childElements();

  /**
   * Returns an iteration of all values found under this N-dimension array.
   *
   * <p>Logically, the N-dimensional array is flatten in a vector of scalars, where scalars of the
   * {@code n - 1} dimension precedes those of the {@code n} dimension, for a total of
   * {@link #size()} values.
   *
   * <p>For example, given a {@code n x m} matrix on the {@code [x, y]} axes, values are iterated in the
   * following order:
   * <pre>
   * x<sub>0</sub>y<sub>0</sub>, x<sub>0</sub>y<sub>1</sub>, ..., x<sub>0</sub>y<sub>m-1</sub>, x<sub>1</sub>y<sub>0</sub>, x<sub>1</sub>y<sub>1</sub>, ..., x<sub>n-1</sub>y<sub>m-1</sub>
   * </pre>
   *
   * <p>The returned iterable can be used for reading, as any other iteration, or for writing
   * by keeping a direct reference to its {@link ValueIterator}
   * <pre>{@code
   *    Iterable<Float> values = arrayOfFloat.values();
   *
   *    // Iterate values for reading
   *    for (Float value: values) {
   *      System.out.println(value);
   *    }
   *
   *    // Iterate values for writing
   *    float val = 0.0f;
   *    for (ValueIterator<Float> iter = values.iterator(); iter.hasNext();) {
   *      iter.next(val++);
   *    }
   * }</pre>
   *
   * @return an iteration of values of type {@code T}
   * @throws IllegalRankException if this array is a scalar (rank 0)
   */
  ValueIterable<T> values();

  /**
   * Returns the N-dimensional element of this array at the given coordinates.
   *
   * <p>Elements of any of the dimensions of this array can be retrieved. For example, if the number
   * of indices is equal to the number of dimensions of this array, then a rank-0 (scalar) array is
   * returned, which value can then be obtained with `array.get()`.
   *
   * <p>Any changes applied to the returned elements affect the data of this array as well, as there
   * is no copy involved.
   *
   * <p>Note that invoking this method is equivalent and more efficient to slice this array at
   * on single element for each indexed dimension, i.e.
   * {@code array.at(x, y, z) == array.slice(at(x), at(y), at(z))}
   *
   * @param indices coordinates of the element to access, none will return this array
   * @return the element at this index
   * @throws IndexOutOfBoundsException if some indices are outside the limits of their respective dimension
   */
  NdArray<T> at(long... indices);

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
   *    import static org.tensorflow.nio.StaticApi.*;
   *
   *    NdArray<Float> matrix3d = ndArrayOfFloats(shape(3, 2, 4));  // with [x, y, z] axes
   *
   *    // Iterates values over the 3rd elements on the z axis, (i.e. [x, x, 2])
   *    for (Float values = matrix3d.slice(all(), all(), at(2)).values()) {
   *      ...
   *    }
   *
   *    // Creates a slice that contains only the last element of the y axis and elements with an
   *    // odd `z` coordinate.
   *    NdArray<Float> slice = matrix3d.slice(all(), at(1), odd());
   *    assertEquals(shape(3, 2), slice.shape());  // x=3, y=0 (scalar), z=2 (odd coordinates)
   *
   *    // Iterates backward the elements on the x axis
   *    for (NdArray<Float> matrix = matrix3d.slice(flip())) {
   *      assertEquals(shape(2, 4), matrix);  // y=2, z=4
   *    }
   * }</pre>
   *
   * @param indices index selectors per dimensions, starting from dimension 0 of this array.
   * @return the element resulting of the index selection
   * @throws IndexOutOfBoundsException if some indices are outside the limits of their respective dimension
   */
  NdArray<T> slice(Index... indices);

  /**
   * Returns the value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of indices provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  NdArray<Float> matrix = ndArrayOfFloat(shape(2, 2));  // matrix rank = 2
   *  matrix.get(0, 1);  // succeeds, returns 0.0f
   *  matrix.get(0);  // throws IllegalRankException
   *
   *  NdArray<Float> scalar = matrix.at(0, 1);  // scalar rank = 0
   *  scalar.get();  // succeeds, returns 0.0f
   * }</pre>
   *
   * @param indices coordinates of the scalar to resolve
   * @return value of that scalar
   * @throws IndexOutOfBoundsException if some indices are outside the limits of their respective dimension
   * @throws IllegalRankException if number of indices is not sufficient to access a scalar element
   */
  T get(long... indices);

  /**
   * Assigns the value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of indices provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  NdArray<Float> matrix = ndArrayOfFloat(shape(2, 2));  // matrix rank = 2
   *  matrix.set(10.0f, 0, 1);  // succeeds
   *  matrix.set(10.0f, 0);  // throws IllegalRankException
   *
   *  NdArray<Float> scalar = matrix.at(0, 1);  // scalar rank = 0
   *  scalar.set(10.0f);  // succeeds
   * }</pre>
   *
   * @param indices coordinates of the scalar to assign
   * @return this array
   * @throws IndexOutOfBoundsException if some indices are outside the limits of their respective dimension
   * @throws IllegalRankException if number of indices is not sufficient to access a scalar element
   */
  NdArray<T> set(T value, long... indices);

  /**
   * Copy the content of this array to the destination array.
   *
   * <p>The {@link #shape()} of the destination array must be equal to the shape of this array, or an exception is
   * thrown. After the copy, the content of both arrays can be altered independently, without affecting
   * each other.
   *
   * @param dst array to receive a copy of the content of this array
   * @return this array
   * @throws IllegalArgumentException if the shape of {@code dst} is not equal to the shape of this array
   */
  NdArray<T> copyTo(NdArray<T> dst);

  /**
   * Copy the content of the source array to this array.
   *
   * <p>The {@link #shape()} of the source array must be equal to the shape of this array, or an exception is
   * thrown. After the copy, the content of both arrays can be altered independently, without affecting
   * each other.
   *
   * @param src array from which content is copied to this array
   * @return this array
   * @throws IllegalArgumentException if the shape of {@code src} is not equal to the shape of this array
   */
  NdArray<T> copyFrom(NdArray<T> src);

  /**
   * Read the content of this N-dimensional array into the destination buffer.
   *
   * <p>The remaining space of the buffer must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the buffer and of the array can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination buffer
   * @return this array
   * @throws java.nio.BufferOverflowException if the buffer cannot hold the content of this array
   * @see DataBuffer#remaining()
   */
  NdArray<T> read(DataBuffer<T> dst);

  /**
   * Write the content of this N-dimensional array from the source buffer.
   *
   * <p>The remaining data of the buffer must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the buffer and of the array can be altered
   * independently, without affecting each other.
   *
   * @param src the source buffer
   * @return this array
   * @throws java.nio.BufferUnderflowException if the buffer has not enough remaining data to write into this array
   * @see DataBuffer#remaining()
   */
  NdArray<T> write(DataBuffer<T> src);

  /**
   * Reads the content of this N-dimensional array into the destination array.
   *
   * <p>The size of the destination array must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination array
   * @return this array
   * @throws java.nio.BufferOverflowException if the destination array cannot hold the content of this array
   */
  NdArray<T> read(T[] dst);

  /**
   * Reads the content of this N-dimensional array into the destination array.
   *
   * <p>{@code dst.length - offset} must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination array
   * @param offset the index of the first element to write in the destination array
   * @return this array
   * @throws java.nio.BufferOverflowException if the destination array cannot hold the content of this array
   * @throws IllegalArgumentException if offset is greater than dst length or is negative
   */
  NdArray<T> read(T[] dst, int offset);

  /**
   * Writes the content of this N-dimensional array from the source array.
   *
   * <p>The size of the source array must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param src the source array
   * @return this array
   * @throws java.nio.BufferUnderflowException if the size of the source array is less than the size of this array
   */
  NdArray<T> write(T[] src);

  /**
   * Writes the content of this N-dimensional array from the source array.
   *
   * <p>{@code src.length - offset} must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param src the source array
   * @param offset the index of the first byte to read from the source array
   * @return this array
   * @throws java.nio.BufferUnderflowException if the size of the source array is less than the size of this array
   * @throws IllegalArgumentException if offset is greater than src length or is negative
   */
  NdArray<T> write(T[] src, int offset);
}
