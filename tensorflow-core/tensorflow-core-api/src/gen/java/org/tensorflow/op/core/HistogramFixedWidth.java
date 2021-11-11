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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Return histogram of values.
 * Given the tensor {@code values}, this operation returns a rank 1 histogram counting
 * the number of entries in {@code values} that fall into every bin.  The bins are
 * equal width and determined by the arguments {@code value_range} and {@code nbins}.
 * <pre>
 * # Bins will be:  (-inf, 1), [1, 2), [2, 3), [3, 4), [4, inf)
 * nbins = 5
 * value_range = [0.0, 5.0]
 * new_values = [-1.0, 0.0, 1.5, 2.0, 5.0, 15]
 *
 * with tf.get_default_session() as sess:
 *   hist = tf.histogram_fixed_width(new_values, value_range, nbins=5)
 *   variables.global_variables_initializer().run()
 *   sess.run(hist) =&gt; [2, 1, 1, 0, 2]
 * </pre>
 *
 * @param <U> data type for {@code out} output
 */
@OpMetadata(
    opType = HistogramFixedWidth.OP_NAME,
    inputsClass = HistogramFixedWidth.Inputs.class
)
@Operator
public final class HistogramFixedWidth<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "HistogramFixedWidth";

  private Output<U> out;

  public HistogramFixedWidth(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new HistogramFixedWidth operation.
   *
   * @param scope current scope
   * @param values Numeric {@code Tensor}.
   * @param valueRange Shape [2] {@code Tensor} of same {@code dtype} as {@code values}.
   * values &lt;= value_range[0] will be mapped to hist[0],
   * values &gt;= value_range[1] will be mapped to hist[-1].
   * @param nbins Scalar {@code int32 Tensor}.  Number of histogram bins.
   * @param dtype The value of the dtype attribute
   * @param <U> data type for {@code HistogramFixedWidth} output and operands
   * @param <T> data type for {@code HistogramFixedWidth} output and operands
   * @return a new instance of HistogramFixedWidth
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber, T extends TNumber> HistogramFixedWidth<U> create(Scope scope,
      Operand<T> values, Operand<T> valueRange, Operand<TInt32> nbins, Class<U> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "HistogramFixedWidth");
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(valueRange.asOutput());
    opBuilder.addInput(nbins.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new HistogramFixedWidth<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new HistogramFixedWidth operation, with the default output types.
   *
   * @param scope current scope
   * @param values Numeric {@code Tensor}.
   * @param valueRange Shape [2] {@code Tensor} of same {@code dtype} as {@code values}.
   * values &lt;= value_range[0] will be mapped to hist[0],
   * values &gt;= value_range[1] will be mapped to hist[-1].
   * @param nbins Scalar {@code int32 Tensor}.  Number of histogram bins.
   * @param <T> data type for {@code HistogramFixedWidth} output and operands
   * @return a new instance of HistogramFixedWidth, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> HistogramFixedWidth<TInt32> create(Scope scope,
      Operand<T> values, Operand<T> valueRange, Operand<TInt32> nbins) {
    return create(scope, values, valueRange, nbins, TInt32.class);
  }

  /**
   * Gets out.
   * A 1-D {@code Tensor} holding histogram of values.
   * @return out.
   */
  public Output<U> out() {
    return out;
  }

  @Override
  public Output<U> asOutput() {
    return out;
  }

  @OpInputsMetadata(
      outputsClass = HistogramFixedWidth.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<HistogramFixedWidth<?>> {
    /**
     * Numeric {@code Tensor}.
     */
    public final Operand<T> values;

    /**
     * Shape [2] {@code Tensor} of same {@code dtype} as {@code values}.
     * values &lt;= value_range[0] will be mapped to hist[0],
     * values &gt;= value_range[1] will be mapped to hist[-1].
     */
    public final Operand<T> valueRange;

    /**
     * Scalar {@code int32 Tensor}.  Number of histogram bins.
     */
    public final Operand<TInt32> nbins;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The dtype attribute
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new HistogramFixedWidth<>(op), op, Arrays.asList("T", "dtype"));
      int inputIndex = 0;
      values = (Operand<T>) op.input(inputIndex++);
      valueRange = (Operand<T>) op.input(inputIndex++);
      nbins = (Operand<TInt32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
