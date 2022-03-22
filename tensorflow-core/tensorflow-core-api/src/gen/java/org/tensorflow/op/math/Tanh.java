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
import org.tensorflow.types.family.TType;

/**
 * Computes hyperbolic tangent of {@code x} element-wise.
 * Given an input tensor, this function computes hyperbolic tangent of every
 * element in the tensor. Input range is {@code [-inf, inf]} and
 * output range is {@code [-1,1]}.
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>x = tf.constant([-float(&quot;inf&quot;), -5, -0.5, 1, 1.2, 2, 3, float(&quot;inf&quot;)])
 * tf.math.tanh(x)
 * &lt;tf.Tensor: shape=(8,), dtype=float32, numpy=
 * array([-1.0, -0.99990916, -0.46211717,  0.7615942 ,  0.8336547 ,
 * 0.9640276 ,  0.9950547 ,  1.0], dtype=float32)&gt;
 * </blockquote>
 * </blockquote>
 * </blockquote>
 *
 * @param <T> data type for {@code y} output
 */
@OpMetadata(
    opType = Tanh.OP_NAME,
    inputsClass = Tanh.Inputs.class
)
@Operator(
    group = "math"
)
public final class Tanh<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Tanh";

  private Output<T> y;

  public Tanh(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Tanh operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param <T> data type for {@code Tanh} output and operands
   * @return a new instance of Tanh
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Tanh<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Tanh");
    opBuilder.addInput(x.asOutput());
    return new Tanh<>(opBuilder.build());
  }

  /**
   * Gets y.
   *
   * @return y.
   */
  public Output<T> y() {
    return y;
  }

  @Override
  public Output<T> asOutput() {
    return y;
  }

  @OpInputsMetadata(
      outputsClass = Tanh.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Tanh<T>> {
    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Tanh<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
