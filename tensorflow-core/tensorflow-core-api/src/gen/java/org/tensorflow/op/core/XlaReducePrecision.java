/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
 * Wraps the XLA ReducePrecision operator
 * documented at https://www.tensorflow.org/xla/operation_semantics#reduceprecision.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = XlaReducePrecision.OP_NAME,
    inputsClass = XlaReducePrecision.Inputs.class
)
@Operator
public final class XlaReducePrecision<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaReducePrecision";

  private Output<T> output;

  public XlaReducePrecision(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaReducePrecision operation.
   *
   * @param scope current scope
   * @param operand array of floating-point type.
   * @param exponentBits number of exponent bits in lower-precision format
   * @param mantissaBits number of mantissa bits in lower-precision format
   * @param <T> data type for {@code XlaReducePrecision} output and operands
   * @return a new instance of XlaReducePrecision
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> XlaReducePrecision<T> create(Scope scope, Operand<T> operand,
      Long exponentBits, Long mantissaBits) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaReducePrecision");
    opBuilder.addInput(operand.asOutput());
    opBuilder.setAttr("exponent_bits", exponentBits);
    opBuilder.setAttr("mantissa_bits", mantissaBits);
    return new XlaReducePrecision<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
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
      outputsClass = XlaReducePrecision.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<XlaReducePrecision<T>> {
    /**
     * array of floating-point type.
     */
    public final Operand<T> operand;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * number of exponent bits in lower-precision format
     */
    public final long exponentBits;

    /**
     * number of mantissa bits in lower-precision format
     */
    public final long mantissaBits;

    public Inputs(GraphOperation op) {
      super(new XlaReducePrecision<>(op), op, Arrays.asList("T", "exponent_bits", "mantissa_bits"));
      int inputIndex = 0;
      operand = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      exponentBits = op.attributes().getAttrInt("exponent_bits");
      mantissaBits = op.attributes().getAttrInt("mantissa_bits");
    }
  }
}
