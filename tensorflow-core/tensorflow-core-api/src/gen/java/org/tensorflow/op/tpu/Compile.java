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

package org.tensorflow.op.tpu;

import java.util.Arrays;
import java.util.List;
import org.tensorflow.ConcreteFunction;
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
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * Compiles a computations for execution on one or more TPU devices.
 * For the internal use of the distributed TPU compiler.
 * <p>'num_computations' is the number of computations to be compiled.
 * 'function' is a function containing the computation to compile.
 * 'dynamic_shapes' contains dynamic shapes of arguments whose shapes were not
 * known statically at TPUReplication rewrite time.
 * 'guaranteed_constants' is a list of tensors which have been guaranteed to not
 * change their values during the session lifetime. These contain tensors marked as
 * constant using the GuaranteeConstOp.
 * 'metadata' is a serialized TPUCompileMetadataProto describing
 * the shapes and types of the inputs to the computation, as well as a mapping onto
 * the TPU pod topology.
 * Each 'program' output is a string key that is passed to the _TPUExecute op and
 * used to look up the program in the compilation cache.
 * 'may_modify_variables' indicates whether variables may be modified.
 */
@OpMetadata(
    opType = Compile.OP_NAME,
    inputsClass = Compile.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class Compile extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TPUCompile";

  private Output<TString> compilationStatus;

  private List<Output<TString>> program;

  private List<Output<TBool>> mayModifyVariables;

  @SuppressWarnings("unchecked")
  public Compile(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    compilationStatus = operation.output(outputIdx++);
    int programLength = operation.outputListLength("program");
    program = Arrays.asList((Output<TString>[]) operation.outputList(outputIdx, programLength));
    outputIdx += programLength;
    int mayModifyVariablesLength = operation.outputListLength("may_modify_variables");
    mayModifyVariables = Arrays.asList((Output<TBool>[]) operation.outputList(outputIdx, mayModifyVariablesLength));
    outputIdx += mayModifyVariablesLength;
  }

  /**
   * Factory method to create a class wrapping a new TPUCompile operation.
   *
   * @param scope current scope
   * @param dynamicShapes The dynamicShapes value
   * @param guaranteedConstants The guaranteedConstants value
   * @param numComputations The value of the numComputations attribute
   * @param function The value of the function attribute
   * @param metadata The value of the metadata attribute
   * @return a new instance of Compile
   */
  @Endpoint(
      describeByClass = true
  )
  public static Compile create(Scope scope, Iterable<Operand<TInt64>> dynamicShapes,
      Iterable<Operand<?>> guaranteedConstants, Long numComputations, ConcreteFunction function,
      String metadata) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Compile");
    opBuilder.addInputList(Operands.asOutputs(dynamicShapes));
    opBuilder.addInputList(Operands.asOutputs(guaranteedConstants));
    opBuilder.setAttr("num_computations", numComputations);
    opBuilder.setAttr("function", function);
    opBuilder.setAttr("metadata", metadata);
    return new Compile(opBuilder.build());
  }

  /**
   * Gets compilationStatus.
   *
   * @return compilationStatus.
   */
  public Output<TString> compilationStatus() {
    return compilationStatus;
  }

  /**
   * Gets program.
   *
   * @return program.
   */
  public List<Output<TString>> program() {
    return program;
  }

  /**
   * Gets mayModifyVariables.
   *
   * @return mayModifyVariables.
   */
  public List<Output<TBool>> mayModifyVariables() {
    return mayModifyVariables;
  }

  @OpInputsMetadata(
      outputsClass = Compile.class
  )
  public static class Inputs extends RawOpInputs<Compile> {
    /**
     * The dynamicShapes input
     */
    public final Iterable<Operand<TInt64>> dynamicShapes;

    /**
     * The guaranteedConstants input
     */
    public final Iterable<Operand<?>> guaranteedConstants;

    /**
     * The metadata attribute
     */
    public final String metadata;

    /**
     * The TguaranteedConstants attribute
     */
    public final DataType[] TguaranteedConstants;

    public Inputs(GraphOperation op) {
      super(new Compile(op), op, Arrays.asList("metadata", "Tguaranteed_constants"));
      int inputIndex = 0;
      int dynamicShapesLength = op.inputListLength("dynamic_shapes");
      dynamicShapes = Arrays.asList((Operand<TInt64>[]) op.inputList(inputIndex, dynamicShapesLength));
      inputIndex += dynamicShapesLength;
      int guaranteedConstantsLength = op.inputListLength("guaranteed_constants");
      guaranteedConstants = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, guaranteedConstantsLength));
      inputIndex += guaranteedConstantsLength;
      metadata = op.attributes().getAttrString("metadata");
      TguaranteedConstants = op.attributes().getAttrTypeList("Tguaranteed_constants");
    }
  }
}
