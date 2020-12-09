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
import org.tensorflow.op.Index;
import org.tensorflow.op.Index.Singular;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Helper endpoint methods for Python like indexing.
 *
 * @see Index
 */
@Operator
public class Indexing {

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
        idx = Index.all();
      }

      begin[i] = idx.getBegin();
      end[i] = idx.getEnd();
      strides[i] = idx.getStride();

      if (idx.isBeginMask()) {
        beginMask |= 1L << i;
      }

      if (idx.isEndMask()) {
        endMask |= 1L << i;
      }

      if (idx.isEllipsisMask()) {
        if(ellipsisMask != 0)
          throw new IllegalArgumentException("Can not have two ellipsis in a slice");
        ellipsisMask |= 1L << i;
      }

      if (idx.isNewAxisMask()) {
        newAxisMask |= 1L << i;
      }

      if (idx.isShrinkAxisMask()) {
        shrinkAxisMask |= 1L << i;
      }
    }

    return new StridedSliceArgs(begin, end, strides, beginMask, endMask, ellipsisMask, newAxisMask, shrinkAxisMask);
  }
  /**
   * Return a strided slice from `input`.
   *  <p>
   *  The goal of this op is to produce a new tensor with a subset of
   *  the elements from the `n` dimensional `input` tensor. The subset is chosen using
   *  a sequence of `m` sparse range specifications encoded into the arguments
   *  of this function. Note, in some cases
   *  `m` could be equal to `n`, but this need not be the case. Each
   *  range specification entry can be one of the following:
   *  <p>
   *  - An ellipsis (...) using {@link Index#ellipses()}. Ellipses are used to imply zero or more
   *    dimensions of full-dimension selection and are produced using
   *    `ellipsis_mask`. For example, `foo[...]` is the identity slice.
   *  <p>
   *  - A new axis using {@link Index#newAxis()}. This is used to insert a new shape=1 dimension and is
   *    produced using `new_axis_mask`. For example, `foo[:, ...]` where
   *    `foo` is shape `(3, 4)` produces a `(1, 3, 4)` tensor.
   *  <p>
   *  - A range `begin:end:stride` using {@link Index#slice(Singular, Singular, int) Index.slice()} or {@link Index#all()}. This is used to specify how much to choose from
   *    a given dimension. `stride` can be any integer but 0.  `begin` is an integer
   *    which represents the index of the first value to select while `end` represents
   *    the index of the last value to select. The number of values selected in each
   *    dimension is `end - begin` if `stride > 0` and `begin - end` if `stride < 0`.
   *    `begin` and `end` can be negative where `-1` is the last element, `-2` is
   *    the second to last. `begin_mask` controls whether to replace the explicitly
   *    given `begin` with an implicit effective value of `0` if `stride > 0` and
   *    `-1` if `stride < 0`. `end_mask` is analogous but produces the number
   *    required to create the largest open interval. For example, given a shape
   *    `(3,)` tensor `foo[:]`, the effective `begin` and `end` are `0` and `3`. Do
   *    not assume this is equivalent to `foo[0:-1]` which has an effective `begin`
   *    and `end` of `0` and `2`. Another example is `foo[-2::-1]` which reverses the
   *    first dimension of a tensor while dropping the last two (in the original
   *    order elements). For example `foo = [1,2,3,4]; foo[-2::-1]` is `[4,3]`.
   *  <p>
   *  - A single index using {@link Index#point(int)}. This is used to keep only elements that have a given
   *    index. For example (`foo[2, :]` on a shape `(5,6)` tensor produces a
   *    shape `(6,)` tensor. This is encoded in `begin` and `end` and
   *    `shrink_axis_mask`.
   *  <p>
   *
   *  <i>Requirements</i>:
   *    `0 != strides[i] for i in [0, m)`
   *    Only one ellipsis.
   *
   * @param scope current scope
   * @param <T> data type for {@code output()} output
   * @param input
   * @param indices The indices to slice.  See {@link Index}.
   * @return a new instance of StridedSlice
   * @see Index
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
   *  <p>
   *  The values of `value` are assigned to the positions in the variable
   *  `ref` that are selected by the slice parameters. The slice parameters
   *  `begin`, `end`, `strides`, etc. work exactly as in `StridedSlice`.
   *  <p>
   *  NOTE this op currently does not support broadcasting and so `value`'s
   *  shape must be exactly the shape produced by the slice of `ref`.
   *
   * @param <T> data type for {@code outputRef()} output
   * @param scope current scope
   * @param ref the tensor to assign to.
   * @param value the value to assign.
   * @param indices The indices to slice.  See {@link Index}.
   * @return a new instance of StridedSliceAssign
   * @see org.tensorflow.op.Ops#stridedSlice(Operand, Index...)
   */
  @Endpoint(name = "stridedSliceAssign")
  public static <T extends TType> StridedSliceAssign<T> stridedSliceAssign(Scope scope, Operand<T> ref, Operand<T> value, Index... indices) {
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
