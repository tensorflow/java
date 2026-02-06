/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TType;

/**
 * The XlaSparseGradientsStack operation
 */
@OpMetadata(
    opType = XlaSparseGradientsStack.OP_NAME,
    inputsClass = XlaSparseGradientsStack.Inputs.class
)
public final class XlaSparseGradientsStack<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSparseGradientsStack";

  private Output<U> stackedGradients;

  public XlaSparseGradientsStack(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    stackedGradients = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSparseGradientsStack operation.
   *
   * @param scope current scope
   * @param unstackedGradients The unstackedGradients value
   * @param interleaved The value of the interleaved attribute
   * @param dtype The value of the dtype attribute
   * @param <U> data type for {@code XlaSparseGradientsStack} output and operands
   * @return a new instance of XlaSparseGradientsStack
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> XlaSparseGradientsStack<U> create(Scope scope,
      Iterable<Operand<? extends TType>> unstackedGradients, Boolean interleaved, Class<U> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "XlaSparseGradientsStack");
    opBuilder.addInputList(Operands.asOutputs(unstackedGradients));
    opBuilder.setAttr("interleaved", interleaved);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new XlaSparseGradientsStack<>(opBuilder.build());
  }

  /**
   * Gets stackedGradients.
   *
   * @return stackedGradients.
   */
  public Output<U> stackedGradients() {
    return stackedGradients;
  }

  @Override
  public Output<U> asOutput() {
    return stackedGradients;
  }

  @OpInputsMetadata(
      outputsClass = XlaSparseGradientsStack.class
  )
  public static class Inputs extends RawOpInputs<XlaSparseGradientsStack<?>> {
    /**
     * The unstackedGradients input
     */
    public final Iterable<Operand<? extends TType>> unstackedGradients;

    /**
     * The interleaved attribute
     */
    public final boolean interleaved;

    /**
     * The inputDtype attribute
     */
    public final DataType inputDtype;

    /**
     * The dtype attribute
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new XlaSparseGradientsStack<>(op), op, Arrays.asList("interleaved", "input_dtype", "dtype"));
      int inputIndex = 0;
      int unstackedGradientsLength = op.inputListLength("unstacked_gradients");
      unstackedGradients = Arrays.asList((Operand<? extends TType>[]) op.inputList(inputIndex, unstackedGradientsLength));
      inputIndex += unstackedGradientsLength;
      interleaved = op.attributes().getAttrBool("interleaved");
      inputDtype = op.attributes().getAttrType("input_dtype");
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
