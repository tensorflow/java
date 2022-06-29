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
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the &quot;logical or&quot; of elements across dimensions of a tensor.
 * Reduces {@code input} along the dimensions given in {@code axis}. Unless
 * {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
 * {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
 * retained with length 1.
 */
@OpMetadata(
    opType = ReduceAny.OP_NAME,
    inputsClass = ReduceAny.Inputs.class
)
@Operator
public final class ReduceAny extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Any";

  private Output<TBool> output;

  public ReduceAny(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Any operation.
   *
   * @param scope current scope
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @return a new instance of ReduceAny
   */
  @Endpoint(
      describeByClass = true
  )
  public static ReduceAny create(Scope scope, Operand<TBool> input, Operand<? extends TNumber> axis,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ReduceAny");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(axis.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.keepDims != null) {
          opBuilder.setAttr("keep_dims", opts.keepDims);
        }
      }
    }
    return new ReduceAny(opBuilder.build());
  }

  /**
   * Sets the keepDims option.
   *
   * @param keepDims If true, retain reduced dimensions with length 1.
   * @return this Options instance.
   */
  public static Options keepDims(Boolean keepDims) {
    return new Options().keepDims(keepDims);
  }

  /**
   * Gets output.
   * The reduced tensor.
   * @return output.
   */
  public Output<TBool> output() {
    return output;
  }

  @Override
  public Output<TBool> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.ReduceAny}
   */
  public static class Options {
    private Boolean keepDims;

    private Options() {
    }

    /**
     * Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public Options keepDims(Boolean keepDims) {
      this.keepDims = keepDims;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ReduceAny.class
  )
  public static class Inputs extends RawOpInputs<ReduceAny> {
    /**
     * The tensor to reduce.
     */
    public final Operand<TBool> input;

    /**
     * The dimensions to reduce. Must be in the range
     * {@code [-rank(input), rank(input))}.
     */
    public final Operand<? extends TNumber> axis;

    /**
     * If true, retain reduced dimensions with length 1.
     */
    public final boolean keepDims;

    /**
     * The Tidx attribute
     */
    public final DataType Tidx;

    public Inputs(GraphOperation op) {
      super(new ReduceAny(op), op, Arrays.asList("keep_dims", "Tidx"));
      int inputIndex = 0;
      input = (Operand<TBool>) op.input(inputIndex++);
      axis = (Operand<? extends TNumber>) op.input(inputIndex++);
      keepDims = op.attributes().getAttrBool("keep_dims");
      Tidx = op.attributes().getAttrType("Tidx");
    }
  }
}
