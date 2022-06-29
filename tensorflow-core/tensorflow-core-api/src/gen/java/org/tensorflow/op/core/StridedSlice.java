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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Return a strided slice from {@code input}.
 * Note, most python users will want to use the Python {@code Tensor.__getitem__}
 * or {@code Variable.__getitem__} rather than this op directly.
 * <p>The goal of this op is to produce a new tensor with a subset of
 * the elements from the {@code n} dimensional {@code input} tensor. The subset is chosen using
 * a sequence of {@code m} sparse range specifications encoded into the arguments
 * of this function. Note, in some cases
 * {@code m} could be equal to {@code n}, but this need not be the case. Each
 * range specification entry can be one of the following:
 * <ul>
 * <li>
 * <p>An ellipsis (...). Ellipses are used to imply zero or more
 * dimensions of full-dimension selection and are produced using
 * {@code ellipsis_mask}. For example, {@code foo[...]} is the identity slice.
 * </li>
 * <li>
 * <p>A new axis. This is used to insert a new shape=1 dimension and is
 * produced using {@code new_axis_mask}. For example, {@code foo[:, ...]} where
 * {@code foo} is shape {@code (3, 4)} produces a {@code (1, 3, 4)} tensor.
 * </li>
 * <li>
 * <p>A range {@code begin:end:stride}. This is used to specify how much to choose from
 * a given dimension. {@code stride} can be any integer but 0.  {@code begin} is an integer
 * which represents the index of the first value to select while {@code end} represents
 * the index of the last value to select. The number of values selected in each
 * dimension is {@code end - begin} if {@code stride > 0} and {@code begin - end} if {@code stride < 0}.
 * {@code begin} and {@code end} can be negative where {@code -1} is the last element, {@code -2} is
 * the second to last. {@code begin_mask} controls whether to replace the explicitly
 * given {@code begin} with an implicit effective value of {@code 0} if {@code stride > 0} and
 * {@code -1} if {@code stride < 0}. {@code end_mask} is analogous but produces the number
 * required to create the largest open interval. For example, given a shape
 * {@code (3,)} tensor {@code foo[:]}, the effective {@code begin} and {@code end} are {@code 0} and {@code 3}. Do
 * not assume this is equivalent to {@code foo[0:-1]} which has an effective {@code begin}
 * and {@code end} of {@code 0} and {@code 2}. Another example is {@code foo[-2::-1]} which reverses the
 * first dimension of a tensor while dropping the last two (in the original
 * order elements). For example {@code foo = [1,2,3,4]; foo[-2::-1]} is {@code [4,3]}.
 * </li>
 * <li>
 * <p>A single index. This is used to keep only elements that have a given
 * index. For example ({@code foo[2, :]} on a shape {@code (5,6)} tensor produces a
 * shape {@code (6,)} tensor. This is encoded in {@code begin} and {@code end} and
 * {@code shrink_axis_mask}.
 * </li>
 * </ul>
 * <p>Each conceptual range specification is encoded in the op's argument. This
 * encoding is best understand by considering a non-trivial example. In
 * particular,
 * {@code foo[1, 2:4, None, ..., :-3:-1, :]} will be encoded as
 * <pre>
 * begin = [1, 2, x, x, 0, x] # x denotes don't care (usually 0)
 * end = [2, 4, x, x, -3, x]
 * strides = [1, 1, x, x, -1, 1]
 * begin_mask = 1&lt;&lt;4 | 1&lt;&lt;5 = 48
 * end_mask = 1&lt;&lt;5 = 32
 * ellipsis_mask = 1&lt;&lt;3 = 8
 * new_axis_mask = 1&lt;&lt;2 = 4
 * shrink_axis_mask = 1&lt;&lt;0 = 1
 * </pre>
 * <p>In this case if {@code foo.shape} is (5, 5, 5, 5, 5, 5) the final shape of
 * the slice becomes (2, 1, 5, 5, 2, 5).
 * Let us walk step by step through each argument specification.
 * <ol>
 * <li>
 * <p>The first argument in the example slice is turned into {@code begin = 1} and
 * {@code end = begin + 1 = 2}. To disambiguate from the original spec {@code 2:4} we
 * also set the appropriate bit in {@code shrink_axis_mask}.
 * </li>
 * <li>
 * <p>{@code 2:4} is contributes 2, 4, 1 to begin, end, and stride. All masks have
 * zero bits contributed.
 * </li>
 * <li>
 * <p>None is a synonym for {@code tf.newaxis}. This means insert a dimension of size 1
 * dimension in the final shape. Dummy values are contributed to begin,
 * end and stride, while the new_axis_mask bit is set.
 * </li>
 * <li>
 * <p>{@code ...} grab the full ranges from as many dimensions as needed to
 * fully specify a slice for every dimension of the input shape.
 * </li>
 * <li>
 * <p>{@code :-3:-1} shows the use of negative indices. A negative index {@code i} associated
 * with a dimension that has shape {@code s} is converted to a positive index
 * {@code s + i}. So {@code -1} becomes {@code s-1} (i.e. the last element). This conversion
 * is done internally so begin, end and strides receive x, -3, and -1.
 * The appropriate begin_mask bit is set to indicate the start range is the
 * full range (ignoring the x).
 * </li>
 * <li>
 * <p>{@code :} indicates that the entire contents of the corresponding dimension
 * is selected. This is equivalent to {@code ::} or {@code 0::1}. begin, end, and strides
 * receive 0, 0, and 1, respectively. The appropriate bits in {@code begin_mask} and
 * {@code end_mask} are also set.
 * </li>
 * </ol>
 * <p><em>Requirements</em>:
 * {@code 0 != strides[i] for i in [0, m)}
 * {@code ellipsis_mask must be a power of two (only one ellipsis)}
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = StridedSlice.OP_NAME,
    inputsClass = StridedSlice.Inputs.class
)
@Operator
public final class StridedSlice<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StridedSlice";

  private Output<T> output;

  public StridedSlice(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StridedSlice operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param begin {@code begin[k]} specifies the offset into the {@code k}th range specification.
   * The exact dimension this corresponds to will be determined by context.
   * Out-of-bounds values will be silently clamped. If the {@code k}th bit of
   * {@code begin_mask} then {@code begin[k]} is ignored and the full range of the
   * appropriate dimension is used instead. Negative values causes indexing
   * to start from the highest element e.g. If {@code foo==[1,2,3]} then {@code foo[-1]==3}.
   * @param end {@code end[i]} is like {@code begin} with the exception that {@code end_mask} is
   * used to determine full ranges.
   * @param strides {@code strides[i]} specifies the increment in the {@code i}th specification
   * after extracting a given element. Negative indices will reverse
   * the original order. Out or range values are
   * clamped to {@code [0,dim[i]) if slice[i]>0} or {@code [-1,dim[i]-1] if slice[i] < 0}
   * @param options carries optional attribute values
   * @param <T> data type for {@code StridedSlice} output and operands
   * @param <U> data type for {@code StridedSlice} output and operands
   * @return a new instance of StridedSlice
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TNumber> StridedSlice<T> create(Scope scope,
      Operand<T> input, Operand<U> begin, Operand<U> end, Operand<U> strides, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StridedSlice");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(begin.asOutput());
    opBuilder.addInput(end.asOutput());
    opBuilder.addInput(strides.asOutput());
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
    return new StridedSlice<>(opBuilder.build());
  }

  /**
   * Sets the beginMask option.
   *
   * @param beginMask a bitmask where a bit i being 1 means to ignore the begin
   * value and instead use the largest interval possible. At runtime
   * begin[i] will be replaced with {@code [0, n-1)} if {@code stride[i] > 0} or
   * {@code [-1, n-1]} if {@code stride[i] < 0}
   * @return this Options instance.
   */
  public static Options beginMask(Long beginMask) {
    return new Options().beginMask(beginMask);
  }

  /**
   * Sets the endMask option.
   *
   * @param endMask analogous to {@code begin_mask}
   * @return this Options instance.
   */
  public static Options endMask(Long endMask) {
    return new Options().endMask(endMask);
  }

  /**
   * Sets the ellipsisMask option.
   *
   * @param ellipsisMask a bitmask where bit {@code i} being 1 means the {@code i}th
   * position is actually an ellipsis. One bit at most can be 1.
   * If {@code ellipsis_mask == 0}, then an implicit ellipsis mask of {@code 1 << (m+1)}
   * is provided. This means that {@code foo[3:5] == foo[3:5, ...]}. An ellipsis
   * implicitly creates as many range specifications as necessary to fully
   * specify the sliced range for every dimension. For example for a 4-dimensional
   * tensor {@code foo} the slice {@code foo[2, ..., 5:8]} implies {@code foo[2, :, :, 5:8]}.
   * @return this Options instance.
   */
  public static Options ellipsisMask(Long ellipsisMask) {
    return new Options().ellipsisMask(ellipsisMask);
  }

  /**
   * Sets the newAxisMask option.
   *
   * @param newAxisMask a bitmask where bit {@code i} being 1 means the {@code i}th
   * specification creates a new shape 1 dimension. For example
   * {@code foo[:4, tf.newaxis, :2]} would produce a shape {@code (4, 1, 2)} tensor.
   * @return this Options instance.
   */
  public static Options newAxisMask(Long newAxisMask) {
    return new Options().newAxisMask(newAxisMask);
  }

  /**
   * Sets the shrinkAxisMask option.
   *
   * @param shrinkAxisMask a bitmask where bit {@code i} implies that the {@code i}th
   * specification should shrink the dimensionality. begin and end
   * must imply a slice of size 1 in the dimension. For example in
   * python one might do {@code foo[:, 3, :]} which would result in
   * {@code shrink_axis_mask} being 2.
   * @return this Options instance.
   */
  public static Options shrinkAxisMask(Long shrinkAxisMask) {
    return new Options().shrinkAxisMask(shrinkAxisMask);
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.StridedSlice}
   */
  public static class Options {
    private Long beginMask;

    private Long endMask;

    private Long ellipsisMask;

    private Long newAxisMask;

    private Long shrinkAxisMask;

    private Options() {
    }

    /**
     * Sets the beginMask option.
     *
     * @param beginMask a bitmask where a bit i being 1 means to ignore the begin
     * value and instead use the largest interval possible. At runtime
     * begin[i] will be replaced with {@code [0, n-1)} if {@code stride[i] > 0} or
     * {@code [-1, n-1]} if {@code stride[i] < 0}
     * @return this Options instance.
     */
    public Options beginMask(Long beginMask) {
      this.beginMask = beginMask;
      return this;
    }

    /**
     * Sets the endMask option.
     *
     * @param endMask analogous to {@code begin_mask}
     * @return this Options instance.
     */
    public Options endMask(Long endMask) {
      this.endMask = endMask;
      return this;
    }

    /**
     * Sets the ellipsisMask option.
     *
     * @param ellipsisMask a bitmask where bit {@code i} being 1 means the {@code i}th
     * position is actually an ellipsis. One bit at most can be 1.
     * If {@code ellipsis_mask == 0}, then an implicit ellipsis mask of {@code 1 << (m+1)}
     * is provided. This means that {@code foo[3:5] == foo[3:5, ...]}. An ellipsis
     * implicitly creates as many range specifications as necessary to fully
     * specify the sliced range for every dimension. For example for a 4-dimensional
     * tensor {@code foo} the slice {@code foo[2, ..., 5:8]} implies {@code foo[2, :, :, 5:8]}.
     * @return this Options instance.
     */
    public Options ellipsisMask(Long ellipsisMask) {
      this.ellipsisMask = ellipsisMask;
      return this;
    }

    /**
     * Sets the newAxisMask option.
     *
     * @param newAxisMask a bitmask where bit {@code i} being 1 means the {@code i}th
     * specification creates a new shape 1 dimension. For example
     * {@code foo[:4, tf.newaxis, :2]} would produce a shape {@code (4, 1, 2)} tensor.
     * @return this Options instance.
     */
    public Options newAxisMask(Long newAxisMask) {
      this.newAxisMask = newAxisMask;
      return this;
    }

    /**
     * Sets the shrinkAxisMask option.
     *
     * @param shrinkAxisMask a bitmask where bit {@code i} implies that the {@code i}th
     * specification should shrink the dimensionality. begin and end
     * must imply a slice of size 1 in the dimension. For example in
     * python one might do {@code foo[:, 3, :]} which would result in
     * {@code shrink_axis_mask} being 2.
     * @return this Options instance.
     */
    public Options shrinkAxisMask(Long shrinkAxisMask) {
      this.shrinkAxisMask = shrinkAxisMask;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = StridedSlice.class
  )
  public static class Inputs<T extends TType, U extends TNumber> extends RawOpInputs<StridedSlice<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * {@code begin[k]} specifies the offset into the {@code k}th range specification.
     * The exact dimension this corresponds to will be determined by context.
     * Out-of-bounds values will be silently clamped. If the {@code k}th bit of
     * {@code begin_mask} then {@code begin[k]} is ignored and the full range of the
     * appropriate dimension is used instead. Negative values causes indexing
     * to start from the highest element e.g. If {@code foo==[1,2,3]} then {@code foo[-1]==3}.
     */
    public final Operand<U> begin;

    /**
     * {@code end[i]} is like {@code begin} with the exception that {@code end_mask} is
     * used to determine full ranges.
     */
    public final Operand<U> end;

    /**
     * {@code strides[i]} specifies the increment in the {@code i}th specification
     * after extracting a given element. Negative indices will reverse
     * the original order. Out or range values are
     * clamped to {@code [0,dim[i]) if slice[i]>0} or {@code [-1,dim[i]-1] if slice[i] < 0}
     */
    public final Operand<U> strides;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Index attribute
     */
    public final DataType Index;

    /**
     * a bitmask where a bit i being 1 means to ignore the begin
     * value and instead use the largest interval possible. At runtime
     * begin[i] will be replaced with `[0, n-1)` if `stride[i] > 0` or
     * `[-1, n-1]` if `stride[i] < 0`
     */
    public final long beginMask;

    /**
     * analogous to `begin_mask`
     */
    public final long endMask;

    /**
     * a bitmask where bit `i` being 1 means the `i`th
     * position is actually an ellipsis. One bit at most can be 1.
     * If `ellipsis_mask == 0`, then an implicit ellipsis mask of `1 << (m+1)`
     * is provided. This means that `foo[3:5] == foo[3:5, ...]`. An ellipsis
     * implicitly creates as many range specifications as necessary to fully
     * specify the sliced range for every dimension. For example for a 4-dimensional
     * tensor `foo` the slice `foo[2, ..., 5:8]` implies `foo[2, :, :, 5:8]`.
     */
    public final long ellipsisMask;

    /**
     * a bitmask where bit `i` being 1 means the `i`th
     * specification creates a new shape 1 dimension. For example
     * `foo[:4, tf.newaxis, :2]` would produce a shape `(4, 1, 2)` tensor.
     */
    public final long newAxisMask;

    /**
     * a bitmask where bit `i` implies that the `i`th
     * specification should shrink the dimensionality. begin and end
     * must imply a slice of size 1 in the dimension. For example in
     * python one might do `foo[:, 3, :]` which would result in
     * `shrink_axis_mask` being 2.
     */
    public final long shrinkAxisMask;

    public Inputs(GraphOperation op) {
      super(new StridedSlice<>(op), op, Arrays.asList("T", "Index", "begin_mask", "end_mask", "ellipsis_mask", "new_axis_mask", "shrink_axis_mask"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      begin = (Operand<U>) op.input(inputIndex++);
      end = (Operand<U>) op.input(inputIndex++);
      strides = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Index = op.attributes().getAttrType("Index");
      beginMask = op.attributes().getAttrInt("begin_mask");
      endMask = op.attributes().getAttrInt("end_mask");
      ellipsisMask = op.attributes().getAttrInt("ellipsis_mask");
      newAxisMask = op.attributes().getAttrInt("new_axis_mask");
      shrinkAxisMask = op.attributes().getAttrInt("shrink_axis_mask");
    }
  }
}
