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
 * Returns which elements of x are finite.
 * {@literal @}compatibility(numpy)<br>
 * Equivalent to np.isfinite
 * <br>{@literal @}end_compatibility
 * <p>Example:
 * <pre>
 * x = tf.constant([5.0, 4.8, 6.8, np.inf, np.nan])
 * tf.math.is_finite(x) ==&gt; [True, True, True, False, False]
 * </pre>
 */
@OpMetadata(
    opType = IsFinite.OP_NAME,
    inputsClass = IsFinite.Inputs.class
)
@Operator(
    group = "math"
)
public final class IsFinite extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "IsFinite";

  private Output<TBool> y;

  public IsFinite(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new IsFinite operation.
   *
   * @param scope current scope
   * @param x The x value
   * @return a new instance of IsFinite
   */
  @Endpoint(
      describeByClass = true
  )
  public static IsFinite create(Scope scope, Operand<? extends TNumber> x) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "IsFinite");
    opBuilder.addInput(x.asOutput());
    return new IsFinite(opBuilder.build());
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
      outputsClass = IsFinite.class
  )
  public static class Inputs extends RawOpInputs<IsFinite> {
    /**
     * The x input
     */
    public final Operand<? extends TNumber> x;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new IsFinite(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      x = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
