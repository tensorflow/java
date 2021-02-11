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
package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.index.Indices;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Helper endpoint methods for Python like indexing.
 *
 * @see org.tensorflow.ndarray.index.Indices
 */
@Operator
public abstract class StridedSliceHelper {

  static class StridedSliceArgs {

    final int[] begin;
    final int[] end;
    final int[] strides;
    final long beginMask;
    final long endMask;
    final long ellipsisMask;
    final long newAxisMask;
    final long shrinkAxisMask;

    private StridedSliceArgs(int[] begin, int[] end, int[] strides, long beginMask, long endMask, long ellipsisMask,
        long newAxisMask, long shrinkAxisMask) {
      this.begin = begin;
      this.end = end;
      this.strides = strides;
      this.beginMask = beginMask;
      this.endMask = endMask;
      this.ellipsisMask = ellipsisMask;
      this.newAxisMask = newAxisMask;
      this.shrinkAxisMask = shrinkAxisMask;
    }
  }

  static StridedSliceArgs mergeIndexes(Index[] indices) {
    int[] begin = new int[indices.length];
    int[] end = new int[indices.length];
    int[] strides = new int[indices.length];
    long beginMask = 0;
    long endMask = 0;
    long ellipsisMask = 0;
    long newAxisMask = 0;
    long shrinkAxisMask = 0;

    for (int i = 0; i < indices.length; i++) {
      Index idx = indices[i];

      if (idx == null) {
        idx = Indices.all();
      }

      if (!idx.isStridedSlicingCompliant()) {
        throw new UnsupportedOperationException("Index " + idx + " is not supported for Tensors");
      }

      begin[i] = (int) idx.begin();
      if (begin[i] != idx.begin()) {
        throw new IllegalArgumentException(
            "Can't convert long begin value to int for index " + idx + ": Out of bounds");
      }

      end[i] = (int) idx.end();
      if (end[i] != idx.end()) {
        throw new IllegalArgumentException("Can't convert long end value to int for index " + idx + ": Out of bounds");
      }

      strides[i] = (int) idx.stride();
      if (strides[i] != idx.stride()) {
        throw new IllegalArgumentException(
            "Can't convert long stride value to int for index " + idx + ": Out of bounds");
      }

      if (idx.beginMask()) {
        beginMask |= 1L << i;
      }

      if (idx.endMask()) {
        endMask |= 1L << i;
      }

      if (idx.isEllipsis()) {
        if (ellipsisMask != 0) {
          throw new IllegalArgumentException("Can not have two ellipsis in a slice");
        }
        ellipsisMask |= 1L << i;
      }

      if (idx.isNewAxis()) {
        newAxisMask |= 1L << i;
      }

      if (idx.isPoint()) {
        shrinkAxisMask |= 1L << i;
      }
    }

    return new StridedSliceArgs(begin, end, strides, beginMask, endMask, ellipsisMask, newAxisMask, shrinkAxisMask);
  }

  /**
   * Return a strided slice from `input`.
   * <p>
   * The goal of this op is to produce a new tensor with a subset of the elements from the `n` dimensional `input`
   * tensor. The subset is chosen using a sequence of `m` sparse range specifications encoded into the arguments of this
   * function. Note, in some cases `m` could be equal to `n`, but this need not be the case. Each range specification
   * entry can be one of the following:
   * <p>
   * - An ellipsis (...) using {@link Indices#ellipsis()}. Ellipses are used to imply zero or more dimensions of
   * full-dimension selection. For example, {@code stridedSlice(foo, Indices.ellipsis()} is the identity slice.
   * <p>
   * - A new axis using {@link Indices#newAxis()}. This is used to insert a new shape=1 dimension.
   * For example, `{@code stridedSlice(foo, Indices.newAxis())} where {@code foo} is shape {@code (3, 4)} 
   * produces a {@code (1, 3, 4)} tensor.
   * <p>
   * - A range {@code begin:end:stride} using {@link Indices#slice(Long, Long, long)}  Index.slice()} or {@link Indices#all()}. This is used to specify
   * how much to choose from a given dimension. {@code stride} can be any integer but 0.  {@code begin} is an integer which
   * represents the index of the first value to select while {@code end} represents the index of the last value to select 
   * (exclusive). Begin and end can be null, in which case the index begins or ends at the beginning or end of the dimension,
   * respectively (reversed if stride is negative).  When both are null, {@code slice()} is the same as {@code all()}.
   * The number of values selected in each dimension is {@code end - begin} if {@code stride > 0} and {@code begin - end}
   * if {@code stride < 0}. {@code begin} and {@code end} can be negative where {@code -1} is the last element, {@code -2}
   * is the second to last. For example, given a shape {@code (3,)} tensor {@code stridedSlice(foo, Indices.all())}, the
   * effective {@code begin} and {@code end} are {@code 0} and {@code 3}. Do not assume this is equivalent to
   * {@code stridedSlice(foo, Indices.slice(0, -1))} which has an effective {@code begin} and {@code end} of {@code 0} and
   * {@code 2}. Another example is {@code stridedSlice(foo, Indices.slice(-2, null, -1))} which reverses the first dimension
   * of a tensor while dropping the last two (in the original order elements). For example {@code foo = [1,2,3,4];
   * stridedSlice(foo, Indices.slice(-2, null, -1)} is {@code [4,3]}.
   * <p>
   * - A single index using {@link Indices#at(long)}. This is used to keep only elements that have a given index. For
   * example ({@code stridedSlice(foo, Indices.at(2))} on a shape {@code (5,6)} tensor produces a shape {@code (6,)} tensor.
   * The dimension can be kept with size one using {@link Indices#at(long, boolean)}.
   * <p>
   * These semantics generally follow NumPy's indexing semantics, which can be found here:
   * <a href="https://numpy.org/doc/stable/reference/arrays.indexing.html">https://numpy.org/doc/stable/reference/arrays.indexing.html</a>
   * <p>
   *
   * <i>Requirements</i>:
   * `0 != strides[i] for i in [0, m)` Only one ellipsis.
   *
   * @param scope current scope
   * @param <T> data type for {@code output()} output
   * @param indices The indices to slice.  See {@link Indices}.
   * @return a new instance of StridedSlice
   * @see Indices
   */
  @Endpoint(name = "stridedSlice")
  public static <T extends TType> StridedSlice<T> stridedSlice(Scope scope, Operand<T> input, Index... indices) {
    StridedSliceArgs args = mergeIndexes(indices);
    return StridedSlice.create(
        scope,
        input,
        Constant.vectorOf(scope, args.begin),
        Constant.vectorOf(scope, args.end),
        Constant.vectorOf(scope, args.strides),
        StridedSlice.beginMask(args.beginMask),
        StridedSlice.endMask(args.endMask),
        StridedSlice.ellipsisMask(args.ellipsisMask),
        StridedSlice.newAxisMask(args.newAxisMask),
        StridedSlice.shrinkAxisMask(args.shrinkAxisMask)
    );
  }

  /**
   * Assign `value` to the sliced l-value reference of `ref`.
   * <p>
   * The values of `value` are assigned to the positions in the variable `ref` that are selected by the slice
   * parameters. The slice parameters `begin`, `end`, `strides`, etc. work exactly as in `StridedSlice`.
   * <p>
   * NOTE this op currently does not support broadcasting and so `value`'s shape must be exactly the shape produced by
   * the slice of `ref`.
   *
   * @param <T> data type for {@code outputRef()} output
   * @param scope current scope
   * @param ref the tensor to assign to.
   * @param value the value to assign.
   * @param indices The indices to slice.  See {@link Indices}.
   * @return a new instance of StridedSliceAssign
   * @see org.tensorflow.op.Ops#stridedSlice(Operand, Index...)
   */
  @Endpoint(name = "stridedSliceAssign")
  public static <T extends TType> StridedSliceAssign<T> stridedSliceAssign(Scope scope, Operand<T> ref,
      Operand<T> value, Index... indices) {
    StridedSliceArgs args = mergeIndexes(indices);
    return StridedSliceAssign.create(
        scope,
        ref,
        Constant.vectorOf(scope, args.begin),
        Constant.vectorOf(scope, args.end),
        Constant.vectorOf(scope, args.strides),
        value,
        StridedSliceAssign.beginMask(args.beginMask),
        StridedSliceAssign.endMask(args.endMask),
        StridedSliceAssign.ellipsisMask(args.ellipsisMask),
        StridedSliceAssign.newAxisMask(args.newAxisMask),
        StridedSliceAssign.shrinkAxisMask(args.shrinkAxisMask)
    );
  }

}
