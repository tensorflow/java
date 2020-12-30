/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Return a strided slice from `input`.
 * <p>
 * Note, most python users will want to use the Python `Tensor.__getitem__`
 * or `Variable.__getitem__` rather than this op directly.
 * <p>
 * The goal of this op is to produce a new tensor with a subset of
 * the elements from the `n` dimensional `input` tensor. The subset is chosen using
 * a sequence of `m` sparse range specifications encoded into the arguments
 * of this function. Note, in some cases
 * `m` could be equal to `n`, but this need not be the case. Each
 * range specification entry can be one of the following:
 * <p>
 * - An ellipsis (...). Ellipses are used to imply zero or more
 *   dimensions of full-dimension selection and are produced using
 *   `ellipsis_mask`. For example, `foo[...]` is the identity slice.
 * <p>
 * - A new axis. This is used to insert a new shape=1 dimension and is
 *   produced using `new_axis_mask`. For example, `foo[:, ...]` where
 *   `foo` is shape `(3, 4)` produces a `(1, 3, 4)` tensor.
 * <p>
 * - A range `begin:end:stride`. This is used to specify how much to choose from
 *   a given dimension. `stride` can be any integer but 0.  `begin` is an integer
 *   which represents the index of the first value to select while `end` represents
 *   the index of the last value to select. The number of values selected in each
 *   dimension is `end - begin` if `stride > 0` and `begin - end` if `stride < 0`.
 *   `begin` and `end` can be negative where `-1` is the last element, `-2` is
 *   the second to last. `begin_mask` controls whether to replace the explicitly
 *   given `begin` with an implicit effective value of `0` if `stride > 0` and
 *   `-1` if `stride < 0`. `end_mask` is analogous but produces the number
 *   required to create the largest open interval. For example, given a shape
 *   `(3,)` tensor `foo[:]`, the effective `begin` and `end` are `0` and `3`. Do
 *   not assume this is equivalent to `foo[0:-1]` which has an effective `begin`
 *   and `end` of `0` and `2`. Another example is `foo[-2::-1]` which reverses the
 *   first dimension of a tensor while dropping the last two (in the original
 *   order elements). For example `foo = [1,2,3,4]; foo[-2::-1]` is `[4,3]`.
 * <p>
 * - A single index. This is used to keep only elements that have a given
 *   index. For example (`foo[2, :]` on a shape `(5,6)` tensor produces a
 *   shape `(6,)` tensor. This is encoded in `begin` and `end` and
 *   `shrink_axis_mask`.
 * <p>
 * Each conceptual range specification is encoded in the op's argument. This
 * encoding is best understand by considering a non-trivial example. In
 * particular,
 * `foo[1, 2:4, None, ..., :-3:-1, :]` will be encoded as
 * <pre>{@code
 * begin = [1, 2, x, x, 0, x] # x denotes don't care (usually 0)
 * end = [2, 4, x, x, -3, x]
 * strides = [1, 1, x, x, -1, 1]
 * begin_mask = 1<<4 | 1<<5 = 48
 * end_mask = 1<<5 = 32
 * ellipsis_mask = 1<<3 = 8
 * new_axis_mask = 1<<2 = 4
 * shrink_axis_mask = 1<<0 = 1
 * }</pre>
 * In this case if `foo.shape` is (5, 5, 5, 5, 5, 5) the final shape of
 * the slice becomes (2, 1, 5, 5, 2, 5).
 * Let us walk step by step through each argument specification.
 * <p>
 * 1.  The first argument in the example slice is turned into `begin = 1` and
 * `end = begin + 1 = 2`. To disambiguate from the original spec `2:4` we
 * also set the appropriate bit in `shrink_axis_mask`.
 * <p>
 * 2. `2:4` is contributes 2, 4, 1 to begin, end, and stride. All masks have
 * zero bits contributed.
 * <p>
 * 3. None is a synonym for `tf.newaxis`. This means insert a dimension of size 1
 * dimension in the final shape. Dummy values are contributed to begin,
 * end and stride, while the new_axis_mask bit is set.
 * <p>
 * 4. `...` grab the full ranges from as many dimensions as needed to
 * fully specify a slice for every dimension of the input shape.
 * <p>
 * 5. `:-3:-1` shows the use of negative indices. A negative index `i` associated
 * with a dimension that has shape `s` is converted to a positive index
 * `s + i`. So `-1` becomes `s-1` (i.e. the last element). This conversion
 * is done internally so begin, end and strides receive x, -3, and -1.
 * The appropriate begin_mask bit is set to indicate the start range is the
 * full range (ignoring the x).
 * <p>
 * 6. `:` indicates that the entire contents of the corresponding dimension
 * is selected. This is equivalent to `::` or `0::1`. begin, end, and strides
 * receive 0, 0, and 1, respectively. The appropriate bits in `begin_mask` and
 * `end_mask` are also set.
 * <p>
 * <i>Requirements</i>:
 *   `0 != strides[i] for i in [0, m)`
 *   `ellipsis_mask must be a power of two (only one ellipsis)`
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class StridedSlice<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.StridedSlice}
   */
  public static class Options {
    
    /**
     * @param beginMask a bitmask where a bit i being 1 means to ignore the begin
     * value and instead use the largest interval possible. At runtime
     * begin[i] will be replaced with `[0, n-1)` if `stride[i] > 0` or
     * `[-1, n-1]` if `stride[i] < 0`
     */
    public Options beginMask(Long beginMask) {
      this.beginMask = beginMask;
      return this;
    }
    
    /**
     * @param endMask analogous to `begin_mask`
     */
    public Options endMask(Long endMask) {
      this.endMask = endMask;
      return this;
    }
    
    /**
     * @param ellipsisMask a bitmask where bit `i` being 1 means the `i`th
     * position is actually an ellipsis. One bit at most can be 1.
     * If `ellipsis_mask == 0`, then an implicit ellipsis mask of `1 << (m+1)`
     * is provided. This means that `foo[3:5] == foo[3:5, ...]`. An ellipsis
     * implicitly creates as many range specifications as necessary to fully
     * specify the sliced range for every dimension. For example for a 4-dimensional
     * tensor `foo` the slice `foo[2, ..., 5:8]` implies `foo[2, :, :, 5:8]`.
     */
    public Options ellipsisMask(Long ellipsisMask) {
      this.ellipsisMask = ellipsisMask;
      return this;
    }
    
    /**
     * @param newAxisMask a bitmask where bit `i` being 1 means the `i`th
     * specification creates a new shape 1 dimension. For example
     * `foo[:4, tf.newaxis, :2]` would produce a shape `(4, 1, 2)` tensor.
     */
    public Options newAxisMask(Long newAxisMask) {
      this.newAxisMask = newAxisMask;
      return this;
    }
    
    /**
     * @param shrinkAxisMask a bitmask where bit `i` implies that the `i`th
     * specification should shrink the dimensionality. begin and end
     * must imply a slice of size 1 in the dimension. For example in
     * python one might do `foo[:, 3, :]` which would result in
     * `shrink_axis_mask` being 2.
     */
    public Options shrinkAxisMask(Long shrinkAxisMask) {
      this.shrinkAxisMask = shrinkAxisMask;
      return this;
    }
    
    private Long beginMask;
    private Long endMask;
    private Long ellipsisMask;
    private Long newAxisMask;
    private Long shrinkAxisMask;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new StridedSlice operation.
   * 
   * @param scope current scope
   * @param input 
   * @param begin `begin[k]` specifies the offset into the `k`th range specification.
   * The exact dimension this corresponds to will be determined by context.
   * Out-of-bounds values will be silently clamped. If the `k`th bit of
   * `begin_mask` then `begin[k]` is ignored and the full range of the
   * appropriate dimension is used instead. Negative values causes indexing
   * to start from the highest element e.g. If `foo==[1,2,3]` then `foo[-1]==3`.
   * @param end `end[i]` is like `begin` with the exception that `end_mask` is
   * used to determine full ranges.
   * @param strides `strides[i]` specifies the increment in the `i`th specification
   * after extracting a given element. Negative indices will reverse
   * the original order. Out or range values are
   * clamped to `[0,dim[i]) if slice[i]>0` or `[-1,dim[i]-1] if slice[i] < 0`
   * @param options carries optional attributes values
   * @return a new instance of StridedSlice
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> StridedSlice<T> create(Scope scope, Operand<T> input, Operand<U> begin, Operand<U> end, Operand<U> strides, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("StridedSlice", scope.makeOpName("StridedSlice"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(begin.asOutput());
    opBuilder.addInput(end.asOutput());
    opBuilder.addInput(strides.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.beginMask != null) {
          opBuilder.setAttr("begin_mask", opts.beginMask);
        }
        if (opts.endMask != null) {
          opBuilder.setAttr("end_mask", opts.endMask);
        }
        if (opts.ellipsisMask != null) {
          opBuilder.setAttr("ellipsis_mask", opts.ellipsisMask);
        }
        if (opts.newAxisMask != null) {
          opBuilder.setAttr("new_axis_mask", opts.newAxisMask);
        }
        if (opts.shrinkAxisMask != null) {
          opBuilder.setAttr("shrink_axis_mask", opts.shrinkAxisMask);
        }
      }
    }
    return new StridedSlice<T>(opBuilder.build());
  }
  
  /**
   * @param beginMask a bitmask where a bit i being 1 means to ignore the begin
   * value and instead use the largest interval possible. At runtime
   * begin[i] will be replaced with `[0, n-1)` if `stride[i] > 0` or
   * `[-1, n-1]` if `stride[i] < 0`
   */
  public static Options beginMask(Long beginMask) {
    return new Options().beginMask(beginMask);
  }
  
  /**
   * @param endMask analogous to `begin_mask`
   */
  public static Options endMask(Long endMask) {
    return new Options().endMask(endMask);
  }
  
  /**
   * @param ellipsisMask a bitmask where bit `i` being 1 means the `i`th
   * position is actually an ellipsis. One bit at most can be 1.
   * If `ellipsis_mask == 0`, then an implicit ellipsis mask of `1 << (m+1)`
   * is provided. This means that `foo[3:5] == foo[3:5, ...]`. An ellipsis
   * implicitly creates as many range specifications as necessary to fully
   * specify the sliced range for every dimension. For example for a 4-dimensional
   * tensor `foo` the slice `foo[2, ..., 5:8]` implies `foo[2, :, :, 5:8]`.
   */
  public static Options ellipsisMask(Long ellipsisMask) {
    return new Options().ellipsisMask(ellipsisMask);
  }
  
  /**
   * @param newAxisMask a bitmask where bit `i` being 1 means the `i`th
   * specification creates a new shape 1 dimension. For example
   * `foo[:4, tf.newaxis, :2]` would produce a shape `(4, 1, 2)` tensor.
   */
  public static Options newAxisMask(Long newAxisMask) {
    return new Options().newAxisMask(newAxisMask);
  }
  
  /**
   * @param shrinkAxisMask a bitmask where bit `i` implies that the `i`th
   * specification should shrink the dimensionality. begin and end
   * must imply a slice of size 1 in the dimension. For example in
   * python one might do `foo[:, 3, :]` which would result in
   * `shrink_axis_mask` being 2.
   */
  public static Options shrinkAxisMask(Long shrinkAxisMask) {
    return new Options().shrinkAxisMask(shrinkAxisMask);
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StridedSlice";
  
  private Output<T> output;
  
  private StridedSlice(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
