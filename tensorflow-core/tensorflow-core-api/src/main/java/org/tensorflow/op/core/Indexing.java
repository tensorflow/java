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
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

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

    public StridedSliceArgs(int[] begin, int[] end, int[] strides, long beginMask, long endMask, long ellipsisMask,
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
