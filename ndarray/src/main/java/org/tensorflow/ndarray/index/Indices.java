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
package org.tensorflow.ndarray.index;

import org.tensorflow.ndarray.IllegalRankException;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffers;

/**
 * Helper class for instantiating {@link Index} objects.
 */
public final class Indices {

  /**
   * A coordinate that selects a specific element on a given dimension.
   *
   * <p>When this index is applied to a given dimension, the dimension is resolved as a
   * single element and therefore is excluded from the computation of the rank.
   *
   * <p>For example, given a 3D matrix on the axis [x, y, z], if
   * {@code matrix.slice(all(), at(0), at(0)}, then the rank of the returned slice is 1 and its
   * number of elements is {@code x.numElements()}
   *
   * @param coord coordinate of the element on the indexed axis
   * @return index
   */
  public static TensorIndex at(long coord) {
    return new At(coord, false);
  }

  /**
   * A coordinate that selects a specific element on a given dimension.
   *
   * <p>This is equivalent to call {@link #at(long)} but where the value of the coordinate is
   * provided by an N-dimensional array.
   *
   * @param coord scalar indicating the coordinate of the element on the indexed axis
   * @return index
   * @throws IllegalRankException if {@code coord} is not a scalar (rank 0)
   */
  public static TensorIndex at(NdArray<? extends Number> coord) {
    if (coord.rank() > 0) {
      throw new IllegalRankException("Only scalars are accepted as a value index");
    }
    return new At(coord.getObject().longValue(), false);
  }

  /**
   * A coordinate that selects a specific element on a given dimension.
   *
   * <p>When this index is applied to a given dimension, the dimension is resolved as a
   * single element and therefore, if {@code keepDim} is false, is excluded from the computation of the rank.
   * If {@code} keepDim is true, the dimension is collapsed down to one element.
   *
   * <p>For example, given a 3D matrix on the axis [x, y, z], if
   * {@code matrix.slice(all(), at(0), at(0)}, then the rank of the returned slice is 1 and its
   * number of elements is {@code x.numElements()}
   *
   * @param coord coordinate of the element on the indexed axis
   * @param keepDim whether to remove the dimension.
   * @return index
   */
  public static TensorIndex at(long coord, boolean keepDim) {
    return new At(coord, keepDim);
  }

  /**
   * A coordinate that selects a specific element on a given dimension.
   *
   * <p>This is equivalent to call {@link #at(long, boolean)} but where the value of the coordinate is
   * provided by an N-dimensional array.
   * <p>
   * If {@code} keepDim is true, the dimension is collapsed down to one element instead of being removed.
   *
   * @param coord scalar indicating the coordinate of the element on the indexed axis
   * @return index
   * @param keepDim whether to remove the dimension.
   * @throws IllegalRankException if {@code coord} is not a scalar (rank 0)
   */
  public static TensorIndex at(NdArray<? extends Number> coord, boolean keepDim) {
    if (coord.rank() > 0) {
      throw new IllegalRankException("Only scalars are accepted as a value index");
    }
    return new At(coord.getObject().longValue(), keepDim);
  }

  /**
   * An index that returns all elements of a dimension in the original order.
   *
   * <p>Applying this index to a given dimension will return the original dimension
   * directly.
   *
   * <p>For example, given a vector with {@code n} elements, {@code all()} returns
   * x<sub>0</sub>, x<sub>1</sub>, ..., x<sub>n-1</sub>
   *
   * @return index
   */
  public static TensorIndex all() {
    return All.INSTANCE;
  }

  /**
   * An index that returns only specific elements on a given dimension.
   *
   * <p>For example, given a vector with {@code n} elements on the {@code x} axis, and {@code n > 10},
   * {@code seq(8, 0, 3)} returns x<sub>8</sub>, x<sub>0</sub>, x<sub>3</sub>
   *
   * @param coords coordinates of the elements in the sequence
   * @return index
   */
  public static Index seq(long... coords) {
    if (coords == null) {
      throw new IllegalArgumentException();
    }
    return new Sequence(NdArrays.wrap(Shape.of(coords.length), DataBuffers.of(coords, true, false)));
  }

  /**
   * An index that returns only specific elements on a given dimension.
   *
   * <p>This is equivalent to {@link #seq(long...)} but where the coordinates of the elements in
   * the sequence are provided by an N-dimensional array.
   *
   * @param coords vector of coordinates of the elements in the sequence
   * @return index
   * @throws IllegalRankException if {@code coords} is not a vector (rank 1)
   */
  public static Index seq(NdArray<? extends Number> coords) {
    if (coords.rank() != 1) {
      throw new IllegalRankException("Only vectors are accepted as an element index");
    }
    return new Sequence(coords);
  }

  /**
   * An index that returns only elements found at an even position in the
   * original dimension.
   *
   * <p>For example, given a vector with {@code n} elements on the {@code x} axis, and n is even,
   * {@code even()} returns x<sub>0</sub>, x<sub>2</sub>, ..., x<sub>n-2</sub>
   *
   * @return index
   */
  public static Index even() {
    return Even.INSTANCE;
  }

  /**
   * An index that returns only elements found at an odd position in the
   * original dimension.
   *
   * <p>For example, given a vector with {@code n} elements on the {@code x} axis, and n is even,
   * {@code odd()} returns x<sub>1</sub>, x<sub>3</sub>, ..., x<sub>n-1</sub>
   *
   * @return index
   */
  public static Index odd() {
    return Odd.INSTANCE;
  }

  /**
   * An index that skips a fixed amount of coordinates between each values returned.
   *
   * <p>For example, given a vector with {@code n} elements on the {@code x} axis,
   * {@code step(k)} returns x<sub>0</sub>, x<sub>k</sub>, x<sub>k*2</sub>, ...
   *
   * @param stepLength the number of elements between each steps
   * @return index
   */
  public static Index step(long stepLength) {
    return new Step(stepLength);
  }

  /**
   * An index that returns only elements on a given dimension starting at a
   * specific coordinate.
   *
   * <p>For example, given a vector with {@code n} elements on the {@code x} axis, and {@code n > k},
   * {@code from(k)} returns x<sub>k</sub>, x<sub>k+1</sub>, ..., x<sub>n-1</sub>
   *
   * @param start coordinate of the first element of the sequence
   * @return index
   */
  public static Index from(long start) {
    return new From(start);
  }

  /**
   * An index that returns only elements on a given dimension up to a
   * specific coordinate.
   *
   * <p>For example, given a vector with {@code n} elements on the {@code x} axis, and {@code n > k},
   * {@code to(k)} returns x<sub>0</sub>, x<sub>1</sub>, ..., x<sub>k</sub>
   *
   * @param end coordinate of the last element of the sequence (exclusive)
   * @return index
   */
  public static Index to(long end) {
    return new To(end);
  }

  /**
   * An index that returns only elements on a given dimension between two coordinates.
   *
   * <p>For example, given a vector with {@code n} elements on the {@code x} axis, and {@code n > k > j},
   * {@code range(j, k)} returns x<sub>j</sub>, x<sub>j+1</sub>, ..., x<sub>k</sub>
   *
   * @param start coordinate of the first element of the sequence
   * @param end coordinate of the last element of the sequence (exclusive)
   * @return index
   */
  public static Index range(long start, long end) {
    return new Range(start, end);
  }

  /**
   * An index that returns only elements on a given dimension between two coordinates.
   *
   * <p>For example, given a vector with {@code n} elements on the {@code x} axis, and {@code n > k > j},
   * {@code range(j, k)} returns x<sub>j</sub>, x<sub>j+1</sub>, ..., x<sub>k</sub>
   *
   * @return index
   */
  public static Index flip() {
    return Flip.INSTANCE;
  }
  
  /**
   * An index that returns elements according to an hyperslab defined by {@code start},
   * {@code stride}, {@code count}, {@code block}. See {@link Hyperslab}.
   * 
   * @param start Starting location for the hyperslab.
   * @param stride The number of elements to separate each element or block to be selected.
   * @param count The number of elements or blocks to select along the dimension.
   * @param block The size of the block selected from the dimension.
   * 
   * @return index
   */
  public static Index hyperslab(long start, long stride, long count, long block) {
    return new Hyperslab(start, stride, count, block);
  }

  //TODO comments, tests, remove extra classes in favor of helper methods

  public static TensorIndex newAxis(){
    return NewAxis.INSTANCE;
  }

  public static TensorIndex ellipsis(){
    return Ellipsis.INSTANCE;
  }

  public static TensorIndex expand(){
    return ellipsis();
  }

  public static TensorIndex slice(Long start, Long end, long stride){
    return new Slice(start, end, stride);
  }
}
