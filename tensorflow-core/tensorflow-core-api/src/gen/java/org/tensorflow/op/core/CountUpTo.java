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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Increments 'ref' until it reaches 'limit'.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = CountUpTo.OP_NAME,
    inputsClass = CountUpTo.Inputs.class
)
@Operator
public final class CountUpTo<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CountUpTo";

  private Output<T> output;

  public CountUpTo(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CountUpTo operation.
   *
   * @param scope current scope
   * @param ref Should be from a scalar {@code Variable} node.
   * @param limit If incrementing ref would bring it above limit, instead generates an
   * 'OutOfRange' error.
   * @param <T> data type for {@code CountUpTo} output and operands
   * @return a new instance of CountUpTo
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> CountUpTo<T> create(Scope scope, Operand<T> ref, Long limit) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CountUpTo");
    opBuilder.addInput(ref.asOutput());
    opBuilder.setAttr("limit", limit);
    return new CountUpTo<>(opBuilder.build());
  }

  /**
   * Gets output.
   * A copy of the input before increment. If nothing else modifies the
   * input, the values produced will all be distinct.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = CountUpTo.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<CountUpTo<T>> {
    /**
     * Should be from a scalar {@code Variable} node.
     */
    public final Operand<T> ref;

    /**
     * If incrementing ref would bring it above limit, instead generates an
     * 'OutOfRange' error.
     */
    public final long limit;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new CountUpTo<>(op), op, Arrays.asList("limit", "T"));
      int inputIndex = 0;
      ref = (Operand<T>) op.input(inputIndex++);
      limit = op.attributes().getAttrInt("limit");
      T = op.attributes().getAttrType("T");
    }
  }
}
