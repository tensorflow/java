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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Return histogram of values.
 * <p>
 * Given the tensor `values`, this operation returns a rank 1 histogram counting
 * the number of entries in `values` that fall into every bin.  The bins are
 * equal width and determined by the arguments `value_range` and `nbins`.
 * <pre>{@code
 * # Bins will be:  (-inf, 1), [1, 2), [2, 3), [3, 4), [4, inf)
 * nbins = 5
 * value_range = [0.0, 5.0]
 * new_values = [-1.0, 0.0, 1.5, 2.0, 5.0, 15]
 * 
 * with tf.get_default_session() as sess:
 *   hist = tf.histogram_fixed_width(new_values, value_range, nbins=5)
 *   variables.global_variables_initializer().run()
 *   sess.run(hist) => [2, 1, 1, 0, 2]
 * }</pre>
 * 
 * 
 * @param <U> data type for {@code out()} output
 */
@Operator
public final class HistogramFixedWidth<U extends TNumber> extends RawOp implements Operand<U> {
  
  /**
   * Factory method to create a class wrapping a new HistogramFixedWidth operation.
   * 
   * @param scope current scope
   * @param values Numeric `Tensor`.
   * @param valueRange Shape [2] `Tensor` of same `dtype` as `values`.
   * values <= value_range[0] will be mapped to hist[0],
   * values >= value_range[1] will be mapped to hist[-1].
   * @param nbins Scalar `int32 Tensor`.  Number of histogram bins.
   * @param dtype 
   * @return a new instance of HistogramFixedWidth
   */
  @Endpoint(describeByClass = true)
  public static <U extends TNumber, T extends TNumber> HistogramFixedWidth<U> create(Scope scope, Operand<T> values, Operand<T> valueRange, Operand<TInt32> nbins, DataType<U> dtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("HistogramFixedWidth", scope.makeOpName("HistogramFixedWidth"));
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(valueRange.asOutput());
    opBuilder.addInput(nbins.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", dtype);
    return new HistogramFixedWidth<U>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new HistogramFixedWidth operation using default output types.
   * 
   * @param scope current scope
   * @param values Numeric `Tensor`.
   * @param valueRange Shape [2] `Tensor` of same `dtype` as `values`.
   * values <= value_range[0] will be mapped to hist[0],
   * values >= value_range[1] will be mapped to hist[-1].
   * @param nbins Scalar `int32 Tensor`.  Number of histogram bins.
   * @return a new instance of HistogramFixedWidth
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> HistogramFixedWidth<TInt32> create(Scope scope, Operand<T> values, Operand<T> valueRange, Operand<TInt32> nbins) {
    return create(scope, values, valueRange, nbins, TInt32.DTYPE);
  }
  
  /**
   * A 1-D `Tensor` holding histogram of values.
   */
  public Output<U> out() {
    return out;
  }
  
  @Override
  public Output<U> asOutput() {
    return out;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "HistogramFixedWidth";
  
  private Output<U> out;
  
  private HistogramFixedWidth(Operation operation) {
    super(operation);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }
}
