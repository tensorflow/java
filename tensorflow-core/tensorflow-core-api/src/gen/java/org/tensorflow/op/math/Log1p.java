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
 * Computes natural logarithm of (1 + x) element-wise.
 * I.e., \(y = \log_e (1 + x)\).
 * <p>Example:
 * <pre>
 * x = tf.constant([0, 0.5, 1, 5])
 * tf.math.log1p(x) ==&gt; [0., 0.4054651, 0.6931472, 1.7917595]
 * </pre>
 *
 * @param <T> data type for {@code y} output
 */
@OpMetadata(
    opType = Log1p.OP_NAME,
    inputsClass = Log1p.Inputs.class
)
@Operator(
    group = "math"
)
public final class Log1p<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Log1p";

  private Output<T> y;

  public Log1p(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Log1p operation.
   *
   * @param scope current scope
   * @param x The x value
   * @param <T> data type for {@code Log1p} output and operands
   * @return a new instance of Log1p
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Log1p<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Log1p");
    opBuilder.addInput(x.asOutput());
    return new Log1p<>(opBuilder.build());
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
      outputsClass = Log1p.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Log1p<T>> {
    /**
     * The x input
     */
    public final Operand<T> x;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Log1p<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
