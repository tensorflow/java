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
 * Compute the cumulative sum of the tensor {@code x} along {@code axis}.
 * By default, this op performs an inclusive cumsum, which means that the first
 * element of the input is identical to the first element of the output:
 * <pre>
 * tf.cumsum([a, b, c])  # =&gt; [a, a + b, a + b + c]
 * </pre>
 * <p>By setting the {@code exclusive} kwarg to {@code True}, an exclusive cumsum is
 * performed instead:
 * <pre>
 * tf.cumsum([a, b, c], exclusive=True)  # =&gt; [0, a, a + b]
 * </pre>
 * <p>By setting the {@code reverse} kwarg to {@code True}, the cumsum is performed in the
 * opposite direction:
 * <pre>
 * tf.cumsum([a, b, c], reverse=True)  # =&gt; [a + b + c, b + c, c]
 * </pre>
 * <p>This is more efficient than using separate {@code tf.reverse} ops.
 * <p>The {@code reverse} and {@code exclusive} kwargs can also be combined:
 * <pre>
 * tf.cumsum([a, b, c], exclusive=True, reverse=True)  # =&gt; [b + c, c, 0]
 * </pre>
 *
 * @param <T> data type for {@code out} output
 */
@OpMetadata(
    opType = Cumsum.OP_NAME,
    inputsClass = Cumsum.Inputs.class
)
@Operator(
    group = "math"
)
public final class Cumsum<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Cumsum";

  private Output<T> out;

  public Cumsum(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Cumsum operation.
   *
   * @param scope current scope
   * @param x A {@code Tensor}. Must be one of the following types: {@code float32}, {@code float64},
   * {@code int64}, {@code int32}, {@code uint8}, {@code uint16}, {@code int16}, {@code int8}, {@code complex64},
   * {@code complex128}, {@code qint8}, {@code quint8}, {@code qint32}, {@code half}.
   * @param axis A {@code Tensor} of type {@code int32} (default: 0). Must be in the range
   * {@code [-rank(x), rank(x))}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Cumsum} output and operands
   * @return a new instance of Cumsum
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Cumsum<T> create(Scope scope, Operand<T> x,
      Operand<? extends TNumber> axis, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Cumsum");
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(axis.asOutput());
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
    return new Cumsum<>(opBuilder.build());
  }

  /**
   * Sets the exclusive option.
   *
   * @param exclusive If {@code True}, perform exclusive cumsum.
   * @return this Options instance.
   */
  public static Options exclusive(Boolean exclusive) {
    return new Options().exclusive(exclusive);
  }

  /**
   * Sets the reverse option.
   *
   * @param reverse A {@code bool} (default: False).
   * @return this Options instance.
   */
  public static Options reverse(Boolean reverse) {
    return new Options().reverse(reverse);
  }

  /**
   * Gets out.
   *
   * @return out.
   */
  public Output<T> out() {
    return out;
  }

  @Override
  public Output<T> asOutput() {
    return out;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.math.Cumsum}
   */
  public static class Options {
    private Boolean exclusive;

    private Boolean reverse;

    private Options() {
    }

    /**
     * Sets the exclusive option.
     *
     * @param exclusive If {@code True}, perform exclusive cumsum.
     * @return this Options instance.
     */
    public Options exclusive(Boolean exclusive) {
      this.exclusive = exclusive;
      return this;
    }

    /**
     * Sets the reverse option.
     *
     * @param reverse A {@code bool} (default: False).
     * @return this Options instance.
     */
    public Options reverse(Boolean reverse) {
      this.reverse = reverse;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Cumsum.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Cumsum<T>> {
    /**
     * A {@code Tensor}. Must be one of the following types: {@code float32}, {@code float64},
     * {@code int64}, {@code int32}, {@code uint8}, {@code uint16}, {@code int16}, {@code int8}, {@code complex64},
     * {@code complex128}, {@code qint8}, {@code quint8}, {@code qint32}, {@code half}.
     */
    public final Operand<T> x;

    /**
     * A {@code Tensor} of type {@code int32} (default: 0). Must be in the range
     * {@code [-rank(x), rank(x))}.
     */
    public final Operand<? extends TNumber> axis;

    /**
     * If `True`, perform exclusive cumsum.
     */
    public final boolean exclusive;

    /**
     * A `bool` (default: False).
     */
    public final boolean reverse;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tidx attribute
     */
    public final DataType Tidx;

    public Inputs(GraphOperation op) {
      super(new Cumsum<>(op), op, Arrays.asList("exclusive", "reverse", "T", "Tidx"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      axis = (Operand<? extends TNumber>) op.input(inputIndex++);
      exclusive = op.attributes().getAttrBool("exclusive");
      reverse = op.attributes().getAttrBool("reverse");
      T = op.attributes().getAttrType("T");
      Tidx = op.attributes().getAttrType("Tidx");
    }
  }
}
