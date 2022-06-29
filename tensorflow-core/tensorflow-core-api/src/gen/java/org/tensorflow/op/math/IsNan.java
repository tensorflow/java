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
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;

/**
 * Returns which elements of x are NaN.
 * {@literal @}compatibility(numpy)<br>
 * Equivalent to np.isnan
 * <br>{@literal @}end_compatibility
 * <p>Example:
 * <pre>
 * x = tf.constant([5.0, np.nan, 6.8, np.nan, np.inf])
 * tf.math.is_nan(x) ==&gt; [False, True, False, True, False]
 * </pre>
 */
@OpMetadata(
    opType = IsNan.OP_NAME,
    inputsClass = IsNan.Inputs.class
)
@Operator(
    group = "math"
)
public final class IsNan extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IsNan";

  private Output<TBool> y;

  public IsNan(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IsNan operation.
   *
   * @param scope current scope
   * @param x The x value
   * @return a new instance of IsNan
   */
  @Endpoint(
      describeByClass = true
  )
  public static IsNan create(Scope scope, Operand<? extends TNumber> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IsNan");
    opBuilder.addInput(x.asOutput());
    return new IsNan(opBuilder.build());
  }

  /**
   * Gets y.
   *
   * @return y.
   */
  public Output<TBool> y() {
    return y;
  }

  @Override
  public Output<TBool> asOutput() {
    return y;
  }

  @OpInputsMetadata(
      outputsClass = IsNan.class
  )
  public static class Inputs extends RawOpInputs<IsNan> {
    /**
     * The x input
     */
    public final Operand<? extends TNumber> x;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new IsNan(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
