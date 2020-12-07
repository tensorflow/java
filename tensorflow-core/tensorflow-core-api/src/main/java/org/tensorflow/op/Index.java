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

public abstract class Index {

  private final int begin;
  private final int end;
  private final int stride;
  private final boolean beginMask;
  private final boolean endMask;
  private final boolean ellipsisMask;
  private final boolean newAxisMask;
  private final boolean shrinkAxisMask;

  public Index(int begin, int end, int stride, boolean beginMask, boolean endMask, boolean ellipsisMask,
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

  public int getBegin() {
    return begin;
  }

  public int getEnd() {
    return end;
  }

  public int getStride() {
    return stride;
  }

  public boolean isBeginMask() {
    return beginMask;
  }

  public boolean isEndMask() {
    return endMask;
  }

  public boolean isEllipsisMask() {
    return ellipsisMask;
  }

  public boolean isNewAxisMask() {
    return newAxisMask;
  }

  public boolean isShrinkAxisMask() {
    return shrinkAxisMask;
  }


  public static abstract class Singular extends Index {

    public Singular(int begin, int end, int stride, boolean beginMask, boolean endMask, boolean ellipsisMask,
        boolean newAxisMask, boolean shrinkAxisMask) {
      super(begin, end, stride, beginMask, endMask, ellipsisMask, newAxisMask, shrinkAxisMask);
    }
  }

  public static class All extends Singular {

    public All() {
      super(0, 0, 1, true, true, false, false, false);
    }
  }

  public static All all(){
    return new All();
  }

  public static class Point extends Singular {

    private final int index;

    public Point(int index, boolean keepDim) {
      super(index, index + 1, 1, false, false, false, false, !keepDim);
      this.index = index;
    }

    public int getIndex() {
      return index;
    }
  }

  public static Point point(int index){
    return new Point(index, false);
  }

  public static Point point(int index, boolean keepDim){
    return new Point(index, keepDim);
  }

  public static class NewAxis extends Index {

    public NewAxis() {
      super(0, 0, 1, false, false, false, true, false);
    }
  }

  public static NewAxis newAxis(){
    return new NewAxis();
  }

  public static class Ellipses extends Index {

    public Ellipses() {
      super(0, 0, 1, false, false, true, false, false);
    }
  }

  public static Ellipses ellipses(){
    return new Ellipses();
  }

  public static class Slice extends Index {

    public Slice(Singular start, Singular end, int stride) {
      super(start instanceof Point ? ((Point) start).index : 0,
          end instanceof Point ? ((Point) end).index : 0,
          stride,
          start instanceof All,
          end instanceof All,
          false,
          false,
          false);

      if(stride < 1){
        throw new IllegalArgumentException("Can not have a stride < 1");
      }
    }
  }

  public static Slice slice(Singular start, Singular end, int stride){
    return new Slice(start == null ? all() : start, end == null ? all() : end, stride);
  }

  public static Slice slice(int start, Singular end, int stride){
    return slice(point(start), end, stride);
  }

  public static Slice slice(Singular start, int end, int stride){
    return slice(start, point(end), stride);
  }

  public static Slice slice(int start, int end, int stride){
    return slice(point(start), point(end), stride);
  }

  public static Slice slice(Singular start, Singular end){
    return slice(start, end, 1);
  }

  public static Slice slice(int start, Singular end){
    return slice(start, end, 1);
  }

  public static Slice slice(Singular start, int end){
    return slice(start, end, 1);
  }

  public static Slice slice(int start, int end){
    return slice(start, end, 1);
  }

}
