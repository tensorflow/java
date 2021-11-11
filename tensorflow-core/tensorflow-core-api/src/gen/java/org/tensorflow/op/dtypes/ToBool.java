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

package org.tensorflow.op.dtypes;

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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * Converts a tensor to a scalar predicate.
 * Converts a tensor to a scalar predicate with the following rules:
 * <ul>
 * <li>
 * <p>For 0D tensors, truthiness is determined by comparing against a &quot;zero&quot;
 * value. For numerical types it is the obvious zero. For strings it is the
 * empty string.
 * </li>
 * <li>
 * <p>For &gt;0D tensors, truthiness is determined by looking at the number of
 * elements. If has zero elements, then the result is false. Otherwise the
 * result is true.
 * </li>
 * </ul>
 * <p>This matches the behavior of If and While for determining if a tensor counts
 * as true/false for a branch condition.
 */
@OpMetadata(
    opType = ToBool.OP_NAME,
    inputsClass = ToBool.Inputs.class
)
public final class ToBool extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ToBool";

  private Output<TBool> output;

  public ToBool(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ToBool operation.
   *
   * @param scope current scope
   * @param input The input value
   * @return a new instance of ToBool
   */
  @Endpoint(
      describeByClass = true
  )
  public static ToBool create(Scope scope, Operand<? extends TType> input) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ToBool");
    opBuilder.addInput(input.asOutput());
    return new ToBool(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TBool> output() {
    return output;
  }

  @Override
  public Output<TBool> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = ToBool.class
  )
  public static class Inputs extends RawOpInputs<ToBool> {
    /**
     * The input input
     */
    public final Operand<? extends TType> input;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new ToBool(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
