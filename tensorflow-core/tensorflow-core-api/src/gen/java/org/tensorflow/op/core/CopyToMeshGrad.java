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
import org.tensorflow.types.family.TType;

/**
 * The CopyToMeshGrad operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = CopyToMeshGrad.OP_NAME,
    inputsClass = CopyToMeshGrad.Inputs.class
)
@Operator
public final class CopyToMeshGrad<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CopyToMeshGrad";

  private Output<T> output;

  public CopyToMeshGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CopyToMeshGrad operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param forwardInput The forwardInput value
   * @param options carries optional attribute values
   * @param <T> data type for {@code CopyToMeshGrad} output and operands
   * @return a new instance of CopyToMeshGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> CopyToMeshGrad<T> create(Scope scope, Operand<T> input,
      Operand<T> forwardInput, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CopyToMeshGrad");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(forwardInput.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.referenceLayout != null) {
          opBuilder.setAttr("reference_layout", opts.referenceLayout);
        }
      }
    }
    return new CopyToMeshGrad<>(opBuilder.build());
  }

  /**
   * Sets the referenceLayout option.
   *
   * @param referenceLayout the referenceLayout option
   * @return this Options instance.
   */
  public static Options referenceLayout(String referenceLayout) {
    return new Options().referenceLayout(referenceLayout);
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

  /**
   * Optional attributes for {@link org.tensorflow.op.core.CopyToMeshGrad}
   */
  public static class Options {
    private String referenceLayout;

    private Options() {
    }

    /**
     * Sets the referenceLayout option.
     *
     * @param referenceLayout the referenceLayout option
     * @return this Options instance.
     */
    public Options referenceLayout(String referenceLayout) {
      this.referenceLayout = referenceLayout;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = CopyToMeshGrad.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<CopyToMeshGrad<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The forwardInput input
     */
    public final Operand<T> forwardInput;

    /**
     * The referenceLayout attribute
     */
    public final String referenceLayout;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new CopyToMeshGrad<>(op), op, Arrays.asList("reference_layout", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      forwardInput = (Operand<T>) op.input(inputIndex++);
      referenceLayout = op.attributes().getAttrString("reference_layout");
      T = op.attributes().getAttrType("T");
    }
  }
}
