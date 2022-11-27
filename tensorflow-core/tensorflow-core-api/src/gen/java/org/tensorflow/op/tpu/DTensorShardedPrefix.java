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
import org.tensorflow.types.TString;

/**
 * The DTensorShardedPrefix operation
 */
@OpMetadata(
    opType = DTensorShardedPrefix.OP_NAME,
    inputsClass = DTensorShardedPrefix.Inputs.class
)
@Operator(
    group = "tpu"
)
public final class DTensorShardedPrefix extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DTensorShardedPrefix";

  private Output<TString> output;

  public DTensorShardedPrefix(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DTensorShardedPrefix operation.
   *
   * @param scope current scope
   * @param prefix The prefix value
   * @param tensorNames The tensorNames value
   * @param shapeAndSlices The shapeAndSlices value
   * @param mesh The mesh value
   * @param layouts The layouts value
   * @param tensors The tensors value
   * @return a new instance of DTensorShardedPrefix
   */
  @Endpoint(
      describeByClass = true
  )
  public static DTensorShardedPrefix create(Scope scope, Operand<TString> prefix,
      Operand<TString> tensorNames, Operand<TString> shapeAndSlices, Operand<TString> mesh,
      Operand<TString> layouts, Iterable<Operand<?>> tensors) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DTensorShardedPrefix");
    opBuilder.addInput(prefix.asOutput());
    opBuilder.addInput(tensorNames.asOutput());
    opBuilder.addInput(shapeAndSlices.asOutput());
    opBuilder.addInput(mesh.asOutput());
    opBuilder.addInput(layouts.asOutput());
    opBuilder.addInputList(Operands.asOutputs(tensors));
    return new DTensorShardedPrefix(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<TString> output() {
    return output;
  }

  @Override
  public Output<TString> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = DTensorShardedPrefix.class
  )
  public static class Inputs extends RawOpInputs<DTensorShardedPrefix> {
    /**
     * The prefix input
     */
    public final Operand<TString> prefix;

    /**
     * The tensorNames input
     */
    public final Operand<TString> tensorNames;

    /**
     * The shapeAndSlices input
     */
    public final Operand<TString> shapeAndSlices;

    /**
     * The mesh input
     */
    public final Operand<TString> mesh;

    /**
     * The layouts input
     */
    public final Operand<TString> layouts;

    /**
     * The tensors input
     */
    public final Iterable<Operand<?>> tensors;

    /**
     * The dtypes attribute
     */
    public final DataType[] dtypes;

    public Inputs(GraphOperation op) {
      super(new DTensorShardedPrefix(op), op, Arrays.asList("dtypes"));
      int inputIndex = 0;
      prefix = (Operand<TString>) op.input(inputIndex++);
      tensorNames = (Operand<TString>) op.input(inputIndex++);
      shapeAndSlices = (Operand<TString>) op.input(inputIndex++);
      mesh = (Operand<TString>) op.input(inputIndex++);
      layouts = (Operand<TString>) op.input(inputIndex++);
      int tensorsLength = op.inputListLength("tensors");
      tensors = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, tensorsLength));
      inputIndex += tensorsLength;
      dtypes = op.attributes().getAttrTypeList("dtypes");
    }
  }
}
