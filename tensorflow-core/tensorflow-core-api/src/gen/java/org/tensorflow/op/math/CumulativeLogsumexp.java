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

package org.tensorflow.op.math;

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
 * Compute the cumulative product of the tensor `x` along `axis`.
 * <p>
 * By default, this op performs an inclusive cumulative log-sum-exp,
 * which means that the first
 * element of the input is identical to the first element of the output:
 * <pre>{@code
 * tf.math.cumulative_logsumexp([a, b, c])  # => [a, log(exp(a) + exp(b)), log(exp(a) + exp(b) + exp(c))]
 * }</pre>
 * By setting the `exclusive` kwarg to `True`, an exclusive cumulative log-sum-exp is
 * performed instead:
 * <pre>{@code
 * tf.cumulative_logsumexp([a, b, c], exclusive=True)  # => [-inf, a, log(exp(a) * exp(b))]
 * }</pre>
 * Note that the neutral element of the log-sum-exp operation is `-inf`,
 * however, for performance reasons, the minimal value representable by the
 * floating point type is used instead.
 * <p>
 * By setting the `reverse` kwarg to `True`, the cumulative log-sum-exp is performed in the
 * opposite direction.
 * 
 * @param <T> data type for {@code out()} output
 */
public final class CumulativeLogsumexp<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.math.CumulativeLogsumexp}
   */
  public static class Options {
    
    /**
     * @param exclusive If `True`, perform exclusive cumulative log-sum-exp.
     */
    public Options exclusive(Boolean exclusive) {
      this.exclusive = exclusive;
      return this;
    }
    
    /**
     * @param reverse A `bool` (default: False).
     */
    public Options reverse(Boolean reverse) {
      this.reverse = reverse;
      return this;
    }
    
    private Boolean exclusive;
    private Boolean reverse;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new CumulativeLogsumexp operation.
   * 
   * @param scope current scope
   * @param x A `Tensor`. Must be one of the following types: `float16`, `float32`, `float64`.
   * @param axis A `Tensor` of type `int32` (default: 0). Must be in the range
   * `[-rank(x), rank(x))`.
   * @param options carries optional attributes values
   * @return a new instance of CumulativeLogsumexp
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber> CumulativeLogsumexp<T> create(Scope scope, Operand<T> x, Operand<U> axis, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("CumulativeLogsumexp", scope.makeOpName("CumulativeLogsumexp"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(axis.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.exclusive != null) {
          opBuilder.setAttr("exclusive", opts.exclusive);
        }
        if (opts.reverse != null) {
          opBuilder.setAttr("reverse", opts.reverse);
        }
      }
    }
    return new CumulativeLogsumexp<T>(opBuilder.build());
  }
  
  /**
   * @param exclusive If `True`, perform exclusive cumulative log-sum-exp.
   */
  public static Options exclusive(Boolean exclusive) {
    return new Options().exclusive(exclusive);
  }
  
  /**
   * @param reverse A `bool` (default: False).
   */
  public static Options reverse(Boolean reverse) {
    return new Options().reverse(reverse);
  }
  
  /**
   */
  public Output<T> out() {
    return out;
  }
  
  @Override
  public Output<T> asOutput() {
    return out;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "CumulativeLogsumexp";
  
  private Output<T> out;
  
  private CumulativeLogsumexp(Operation operation) {
    super(operation);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }
}
