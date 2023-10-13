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
import java.util.Iterator;
import java.util.List;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
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
 * Emits an HLO {@code CustomCall} operation with multiple outputs.
 * As opposed to {@code XlaCustomCall}, this operation supports multiple outputs.
 * <p>See {@code CustomCall} specification at
 * https://tensorflow.org/xla/operation_semantics#customcall,
 * and {@code mhlo.custom_call} specification at
 * https://tensorflow.org/mlir/hlo_ops#mhlocustom_call_mlirmhlocustomcallop.
 */
@OpMetadata(
    opType = XlaCustomCallV2.OP_NAME,
    inputsClass = XlaCustomCallV2.Inputs.class
)
@Operator
public final class XlaCustomCallV2 extends RawOp implements Iterable<Operand<TType>> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaCustomCallV2";

  private List<Output<?>> results;

  @SuppressWarnings("unchecked")
  public XlaCustomCallV2(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    int resultsLength = operation.outputListLength("results");
    results = Arrays.asList(operation.outputList(outputIdx, resultsLength));
    outputIdx += resultsLength;
  }

  /**
   * Factory method to create a class wrapping a new XlaCustomCallV2 operation.
   *
   * @param scope current scope
   * @param operands A sequence of tensors with possibly different types.
   * @param callTargetName Name of the user function. The function signature must conform
   * to version 3 of the API, see {@code API_VERSION_STATUS_RETURNING_UNIFIED}. All
   * operands and results assumed to be in the default layout.
   * @param backendConfig A string that encodes a metadata for the backend.
   * @param hasSideEffect Indicates whether the custom call has side effects.
   * @param resultDtypes Types of all results.
   * @param resultShapes Shapes of all results.
   * @return a new instance of XlaCustomCallV2
   */
  @Endpoint(
      describeByClass = true
  )
  public static XlaCustomCallV2 create(Scope scope, Iterable<Operand<?>> operands,
      String callTargetName, String backendConfig, Boolean hasSideEffect,
      List<Class<? extends TType>> resultDtypes, List<Shape> resultShapes) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaCustomCallV2");
    opBuilder.addInputList(Operands.asOutputs(operands));
    opBuilder.setAttr("call_target_name", callTargetName);
    opBuilder.setAttr("backend_config", backendConfig);
    opBuilder.setAttr("has_side_effect", hasSideEffect);
    opBuilder.setAttr("result_dtypes", Operands.toDataTypes(resultDtypes));
    Shape[] resultShapesArray = new Shape[resultShapes.size()];
    for (int i = 0 ; i < resultShapesArray.length ; i++) {
      resultShapesArray[i] = resultShapes.get(i);
    }
    opBuilder.setAttr("result_shapes", resultShapesArray);
    return new XlaCustomCallV2(opBuilder.build());
  }

  /**
   * Gets results.
   *
   * @return results.
   */
  public List<Output<?>> results() {
    return results;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Iterator<Operand<TType>> iterator() {
    return (Iterator) results.iterator();
  }

  @OpInputsMetadata(
      outputsClass = XlaCustomCallV2.class
  )
  public static class Inputs extends RawOpInputs<XlaCustomCallV2> {
    /**
     * A sequence of tensors with possibly different types.
     */
    public final Iterable<Operand<?>> operands;

    /**
     * Name of the user function. The function signature must conform
     * to version 3 of the API, see `API_VERSION_STATUS_RETURNING_UNIFIED`. All
     * operands and results assumed to be in the default layout.
     */
    public final String callTargetName;

    /**
     * A string that encodes a metadata for the backend.
     */
    public final String backendConfig;

    /**
     * Indicates whether the custom call has side effects.
     */
    public final boolean hasSideEffect;

    /**
     * The operandDtypes attribute
     */
    public final DataType[] operandDtypes;

    /**
     * Types of all results.
     */
    public final DataType[] resultDtypes;

    /**
     * Shapes of all results.
     */
    public final Shape[] resultShapes;

    public Inputs(GraphOperation op) {
      super(new XlaCustomCallV2(op), op, Arrays.asList("call_target_name", "backend_config", "has_side_effect", "operand_dtypes", "result_dtypes", "result_shapes"));
      int inputIndex = 0;
      int operandsLength = op.inputListLength("operands");
      operands = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, operandsLength));
      inputIndex += operandsLength;
      callTargetName = op.attributes().getAttrString("call_target_name");
      backendConfig = op.attributes().getAttrString("backend_config");
      hasSideEffect = op.attributes().getAttrBool("has_side_effect");
      operandDtypes = op.attributes().getAttrTypeList("operand_dtypes");
      resultDtypes = op.attributes().getAttrTypeList("result_dtypes");
      resultShapes = op.attributes().getAttrShapeList("result_shapes");
    }
  }
}
