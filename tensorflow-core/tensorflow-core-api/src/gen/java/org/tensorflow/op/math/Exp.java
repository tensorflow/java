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
 * Computes exponential of x element-wise.  \(y = e^x\).
 * This function computes the exponential of every element in the input tensor.
 * i.e. {@code exp(x)} or {@code e^(x)}, where {@code x} is the input tensor.
 * {@code e} denotes Euler's number and is approximately equal to 2.718281.
 * Output is positive for any real input.
 * <pre>
 * x = tf.constant(2.0)
 * tf.math.exp(x) ==&gt; 7.389056
 *
 * x = tf.constant([2.0, 8.0])
 * tf.math.exp(x) ==&gt; array([7.389056, 2980.958], dtype=float32)
 * </pre>
 * <p>For complex numbers, the exponential value is calculated as follows:
 * <pre>
 * e^(x+iy) = e^x * e^iy = e^x * (cos y + i sin y)
 * </pre>
 * <p>Let's consider complex number 1+1j as an example.
 * e^1 * (cos 1 + i sin 1) = 2.7182818284590 * (0.54030230586+0.8414709848j)
 * <pre>
 * x = tf.constant(1 + 1j)
 * tf.math.exp(x) ==&gt; 1.4686939399158851+2.2873552871788423j
 * </pre>
 *
 * @param <T> data type for {@code y} output
 */
@OpMetadata(
    opType = Exp.OP_NAME,
    inputsClass = Exp.Inputs.class
)
@Operator(
    group = "math"
)
public final class Exp<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Exp";

  private Output<T> y;

  public Exp(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Exp operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param <T> data type for {@code Exp} output and operands
   * @return a new instance of Exp
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Exp<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Exp");
    opBuilder.addInput(x.asOutput());
    return new Exp<>(opBuilder.build());
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
      outputsClass = Exp.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Exp<T>> {
    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Exp<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
