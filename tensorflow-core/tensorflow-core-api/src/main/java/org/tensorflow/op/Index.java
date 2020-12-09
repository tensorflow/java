/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/
package org.tensorflow.op;

import org.tensorflow.op.annotation.Endpoint;

/**
 * Numpy-like indexing.  Supports slices, stride slices, open slices, all, newaxis, and ellipsis.
 * <p>
 * Examples:
 * <pre>{@code
 * x[1:-1, :, tf.newaxis, ...]
 * // becomes
 * stridedSlice(x, Index.slice(1, -1), Index.all(), Index.newAxis(), Index.ellipsis())
 *
 *
 * x[2, 10:, :-10, 2:-2:2]
 * // becomes
 * stridedSlice(x, Index.point(2), Index.slice(10, null), Index.slice(null, -10), Index.slice(2, -2, 2))
 * }</pre>
 *
 */
public abstract class Index {

  private final int begin;
  private final int end;
  private final int stride;
  private final boolean beginMask;
  private final boolean endMask;
  private final boolean ellipsisMask;
  private final boolean newAxisMask;
  private final boolean shrinkAxisMask;

  private Index(int begin, int end, int stride, boolean beginMask, boolean endMask, boolean ellipsisMask,
      boolean newAxisMask, boolean shrinkAxisMask) {
    this.begin = begin;
    this.end = end;
    this.stride = stride;
    this.beginMask = beginMask;
    this.endMask = endMask;
    this.ellipsisMask = ellipsisMask;
    this.newAxisMask = newAxisMask;
    this.shrinkAxisMask = shrinkAxisMask;
  }

  /**
   * @return the beginning index of the slice.
   */
  public int getBegin() {
    return begin;
  }

  /**
   * @return the end (exclusive) index of the slice.
   */
  public int getEnd() {
    return end;
  }

  /**
   * @return the stride of the slice.
   */
  public int getStride() {
    return stride;
  }

  /**
   * @return whether to begin at the beginning.
   */
  public boolean isBeginMask() {
    return beginMask;
  }

  /**
   * @return whether to end at the end.
   */
  public boolean isEndMask() {
    return endMask;
  }

  /**
   * @return is this index an {@link Ellipses}
   */
  public boolean isEllipsisMask() {
    return ellipsisMask;
  }

  /**
   * @return should this index add a new dimension.
   */
  public boolean isNewAxisMask() {
    return newAxisMask;
  }

  /**
   * @return should this index shrink its dimension.
   */
  public boolean isShrinkAxisMask() {
    return shrinkAxisMask;
  }

  /**
   * An index that can be used as the start or end of a slice.
   */
  public static abstract class Singular extends Index {

    private Singular(int begin, int end, int stride, boolean beginMask, boolean endMask, boolean ellipsisMask,
        boolean newAxisMask, boolean shrinkAxisMask) {
      super(begin, end, stride, beginMask, endMask, ellipsisMask, newAxisMask, shrinkAxisMask);
    }
  }

  /**
   * An index that gets the entire dimension.
   */
  public static class All extends Singular {

    private All() {
      super(0, 0, 1, true, true, false, false, false);
    }
  }

  /**
   * An index that gets a single point from its dimension, collapsing it by default.
   */
  public static class Point extends Singular {

    private final int index;

    private Point(int index, boolean keepDim) {
      super(index, index + 1, 1, false, false, false, false, !keepDim);
      this.index = index;
    }

    public int getIndex() {
      return index;
    }
  }

  /**
   * An index that adds a new dimension of size 1 where it is used.
   */
  public static class NewAxis extends Index {

    private NewAxis() {
      super(0, 0, 1, false, false, false, true, false);
    }
  }

  /**
   * An index that expands to get all possible dimensions.
   */
  public static class Ellipses extends Index {

    private Ellipses() {
      super(0, 0, 1, false, false, true, false, false);
    }
  }

  /**
   * An index to get a slice of its dimension, with an optional stride.
   */
  public static class Slice extends Index {

    private Slice(Singular start, Singular end, int stride) {
      super(start instanceof Point ? ((Point) start).index : 0,
          end instanceof Point ? ((Point) end).index : 0,
          stride,
          start instanceof All,
          end instanceof All,
          false,
          false,
          false);

      if(stride != 0){
        throw new IllegalArgumentException("Can not have a stride of 0");
      }
    }
  }

  /**
   * An index that gets the entire dimension.
   * <p>
   * Equivalent to Python's {@code :}.
   */
  public static All all(){
    return new All();
  }

  /**
   * An index that gets the value at the given position, and collapses the dimension (removing it).
   * <p>
   * Equivalent to Python's indexing, and supports negative values in the same way.
   *
   * @param index The position to get.
   */
  public static Point point(int index){
    return new Point(index, false);
  }


  /**
   * An index that gets the value at the given position, and collapses the dimension (removing it) if keepDim is false.
   *
   * @param index The position to get.
   * @param keepDim Whether to keep the dimension as size 1.
   */
  public static Point point(int index, boolean keepDim){
    return new Point(index, keepDim);
  }

  /**
   * An index that adds a new dimension of size 1 where it is used.
   * <p>
   * Equivalent to Python's {@code np.newaxis}, {@code tf.newaxis} or {@code None}.
   */
  public static NewAxis newAxis(){
    return new NewAxis();
  }

  /**
   * An index that expands to get all possible dimensions.
   * <p>
   * Equivalent to Python's {@code ...}.
   */
  public static Ellipses ellipses(){
    return new Ellipses();
  }

  /**
   * An index to get a slice of its dimension.
   * Start and end can be null or All to start or end at the beginning or end, respectively.
   * <p>
   * Equivalent to Python's {@code :} slicing syntax:
   * <pre>{@code
   * :
   * // becomes
   * Index.all()
   * Index.slice(null, null)
   * Index.slice(Index.all(), Index.all())
   *
   * 2:
   * // becomes
   * Index.slice(2, null)
   *
   * :2
   * // becomes
   * Index.slice(null, 2)
   *
   * 2:10
   * // becomes
   * Index.slice(2, 10)
   *
   * :2
   * // becomes
   * Index.slice(null, null, 2)
   *
   * 2:10:2
   * //becomes
   * Index.slice(2, 10, 2)
   * }</pre>
   *
   * @param start Where to start the slice.  Starts at the beginning if null or All.
   * @param end Where to end the slice (exclusive).  Ends at the end if null or All.
   * @param stride The stride.
   */
  public static Slice slice(Singular start, Singular end, int stride){
    return new Slice(start == null ? all() : start, end == null ? all() : end, stride);
  }


  /**
   * An index to get a slice of its dimension.
   * End can be null or All to end at the end.
   *
   * @param start Where to start the slice.
   * @param end Where to end the slice (exclusive).  Ends at the end if null or All.
   * @param stride The stride.
   * @see #slice(Singular, Singular, int)
   */
  public static Slice slice(int start, Singular end, int stride){
    return slice(point(start), end, stride);
  }


  /**
   * An index to get a slice of its dimension.
   * Start can be null or All to start at the beginning.
   *
   * @param start Where to start the slice.  Starts at the beginning if null or All.
   * @param end Where to end the slice (exclusive).
   * @param stride The stride.
   * @see #slice(Singular, Singular, int)
   */
  public static Slice slice(Singular start, int end, int stride){
    return slice(start, point(end), stride);
  }


  /**
   * An index to get a slice of its dimension.
   *
   * @param start Where to start the slice.
   * @param end Where to end the slice (exclusive).
   * @param stride The stride.
   * @see #slice(Singular, Singular, int)
   */
  public static Slice slice(int start, int end, int stride){
    return slice(point(start), point(end), stride);
  }

  /**
   * An index to get a slice of its dimension.
   * Start and end can be null or All to start or end at the beginning or end, respectively.
   *
   * @param start Where to start the slice.  Starts at the beginning if null or All.
   * @param end Where to end the slice (exclusive).  Ends at the end if null or All.
   * @see #slice(Singular, Singular, int)
   */
  public static Slice slice(Singular start, Singular end){
    return slice(start, end, 1);
  }

  /**
   * An index to get a slice of its dimension.
   * End can be null or All to end at the end.
   *
   * @param start Where to start the slice.
   * @param end Where to end the slice (exclusive).  Ends at the end if null or All.
   * @see #slice(Singular, Singular, int)
   */
  public static Slice slice(int start, Singular end){
    return slice(start, end, 1);
  }

  /**
   * An index to get a slice of its dimension.
   * Start can be null or All to start at the beginning.
   *
   * @param start Where to start the slice.  Starts at the beginning if null or All.
   * @param end Where to end the slice (exclusive).
   * @see #slice(Singular, Singular, int)
   */
  public static Slice slice(Singular start, int end){
    return slice(start, end, 1);
  }

  /**
   * An index to get a slice of its dimension.
   *
   * @param start Where to start the slice.
   * @param end Where to end the slice (exclusive).
   * @see #slice(Singular, Singular, int)
   */
  public static Slice slice(int start, int end){
    return slice(start, end, 1);
  }

}
