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

package org.tensorflow.op.risc;

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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * The RiscGather operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RiscGather.OP_NAME,
    inputsClass = RiscGather.Inputs.class
)
public final class RiscGather<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscGather";

  private Output<T> output;

  public RiscGather(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscGather operation.
   *
   * @param scope current scope
   * @param params The params value
   * @param indices The indices value
   * @param axis The axis value
   * @param options carries optional attribute values
   * @param <T> data type for {@code RiscGather} output and operands
   * @return a new instance of RiscGather
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> RiscGather<T> create(Scope scope, Operand<T> params,
      Operand<? extends TNumber> indices, Operand<? extends TNumber> axis, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscGather");
    opBuilder.addInput(params.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(axis.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.batchDims != null) {
          opBuilder.setAttr("batch_dims", opts.batchDims);
        }
      }
    }
    return new RiscGather<>(opBuilder.build());
  }

  /**
   * Sets the batchDims option.
   *
   * @param batchDims the batchDims option
   * @return this Options instance.
   */
  public static Options batchDims(Long batchDims) {
    return new Options().batchDims(batchDims);
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
   * Optional attributes for {@link org.tensorflow.op.risc.RiscGather}
   */
  public static class Options {
    private Long batchDims;

    private Options() {
    }

    /**
     * Sets the batchDims option.
     *
     * @param batchDims the batchDims option
     * @return this Options instance.
     */
    public Options batchDims(Long batchDims) {
      this.batchDims = batchDims;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RiscGather.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<RiscGather<T>> {
    /**
     * The params input
     */
    public final Operand<T> params;

    /**
     * The indices input
     */
    public final Operand<? extends TNumber> indices;

    /**
     * The axis input
     */
    public final Operand<? extends TNumber> axis;

    /**
     * The batchDims attribute
     */
    public final long batchDims;

    /**
     * The Tparams attribute
     */
    public final DataType Tparams;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    /**
     * The Taxis attribute
     */
    public final DataType Taxis;

    public Inputs(GraphOperation op) {
      super(new RiscGather<>(op), op, Arrays.asList("batch_dims", "Tparams", "Tindices", "Taxis"));
      int inputIndex = 0;
      params = (Operand<T>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      axis = (Operand<? extends TNumber>) op.input(inputIndex++);
      batchDims = op.attributes().getAttrInt("batch_dims");
      Tparams = op.attributes().getAttrType("Tparams");
      Tindices = op.attributes().getAttrType("Tindices");
      Taxis = op.attributes().getAttrType("Taxis");
    }
  }
}
