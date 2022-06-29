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

package org.tensorflow.op.xla;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
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
 * Wraps the XLA DotGeneral operator, documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#dotgeneral
 * .
 *
 * @param <V> data type for {@code output} output
 */
@OpMetadata(
    opType = Dot.OP_NAME,
    inputsClass = Dot.Inputs.class
)
@Operator(
    group = "xla"
)
public final class Dot<V extends TType> extends RawOp implements Operand<V> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaDotV2";

  private Output<V> output;

  public Dot(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaDotV2 operation.
   *
   * @param scope current scope
   * @param lhs the LHS tensor
   * @param rhs the RHS tensor
   * @param dimensionNumbers a serialized xla::DotDimensionNumbers proto.
   * @param precisionConfig a serialized xla::PrecisionConfig proto.
   * @param preferredElementType The type of the tensor.
   * @param <V> data type for {@code XlaDotV2} output and operands
   * @return a new instance of Dot
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TType> Dot<V> create(Scope scope, Operand<? extends TType> lhs,
      Operand<? extends TType> rhs, String dimensionNumbers, String precisionConfig,
      Class<V> preferredElementType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Dot");
    opBuilder.addInput(lhs.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder.setAttr("dimension_numbers", dimensionNumbers);
    opBuilder.setAttr("precision_config", precisionConfig);
    opBuilder.setAttr("preferred_element_type", Operands.toDataType(preferredElementType));
    return new Dot<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<V> output() {
    return output;
  }

  @Override
  public Output<V> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = Dot.class
  )
  public static class Inputs extends RawOpInputs<Dot<?>> {
    /**
     * the LHS tensor
     */
    public final Operand<? extends TType> lhs;

    /**
     * the RHS tensor
     */
    public final Operand<? extends TType> rhs;

    /**
     * The LhsT attribute
     */
    public final DataType LhsT;

    /**
     * The RhsT attribute
     */
    public final DataType RhsT;

    /**
     * a serialized xla::DotDimensionNumbers proto.
     */
    public final String dimensionNumbers;

    /**
     * a serialized xla::PrecisionConfig proto.
     */
    public final String precisionConfig;

    /**
     * The type of the tensor.
     */
    public final DataType preferredElementType;

    public Inputs(GraphOperation op) {
      super(new Dot<>(op), op, Arrays.asList("LhsT", "RhsT", "dimension_numbers", "precision_config", "preferred_element_type"));
      int inputIndex = 0;
      lhs = (Operand<? extends TType>) op.input(inputIndex++);
      rhs = (Operand<? extends TType>) op.input(inputIndex++);
      LhsT = op.attributes().getAttrType("LhsT");
      RhsT = op.attributes().getAttrType("RhsT");
      dimensionNumbers = op.attributes().getAttrString("dimension_numbers");
      precisionConfig = op.attributes().getAttrString("precision_config");
      preferredElementType = op.attributes().getAttrType("preferred_element_type");
    }
  }
}
