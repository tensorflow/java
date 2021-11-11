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
import java.util.List;
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
import org.tensorflow.types.family.TType;

/**
 * The RiscSqueeze operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RiscSqueeze.OP_NAME,
    inputsClass = RiscSqueeze.Inputs.class
)
public final class RiscSqueeze<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RiscSqueeze";

  private Output<T> output;

  public RiscSqueeze(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RiscSqueeze operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param options carries optional attribute values
   * @param <T> data type for {@code RiscSqueeze} output and operands
   * @return a new instance of RiscSqueeze
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> RiscSqueeze<T> create(Scope scope, Operand<T> input,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RiscSqueeze");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.squeezeDims != null) {
          long[] squeezeDimsArray = new long[opts.squeezeDims.size()];
          for (int i = 0 ; i < squeezeDimsArray.length ; i++) {
            squeezeDimsArray[i] = opts.squeezeDims.get(i);
          }
          opBuilder.setAttr("squeeze_dims", squeezeDimsArray);
        }
      }
    }
    return new RiscSqueeze<>(opBuilder.build());
  }

  /**
   * Sets the squeezeDims option.
   *
   * @param squeezeDims the squeezeDims option
   * @return this Options instance.
   */
  public static Options squeezeDims(List<Long> squeezeDims) {
    return new Options().squeezeDims(squeezeDims);
  }

  /**
   * Sets the squeezeDims option.
   *
   * @param squeezeDims the squeezeDims option
   * @return this Options instance.
   */
  public static Options squeezeDims(Long... squeezeDims) {
    return new Options().squeezeDims(squeezeDims);
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
   * Optional attributes for {@link org.tensorflow.op.risc.RiscSqueeze}
   */
  public static class Options {
    private List<Long> squeezeDims;

    private Options() {
    }

    /**
     * Sets the squeezeDims option.
     *
     * @param squeezeDims the squeezeDims option
     * @return this Options instance.
     */
    public Options squeezeDims(List<Long> squeezeDims) {
      this.squeezeDims = squeezeDims;
      return this;
    }

    /**
     * Sets the squeezeDims option.
     *
     * @param squeezeDims the squeezeDims option
     * @return this Options instance.
     */
    public Options squeezeDims(Long... squeezeDims) {
      this.squeezeDims = Arrays.asList(squeezeDims);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RiscSqueeze.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<RiscSqueeze<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The squeezeDims attribute
     */
    public final long[] squeezeDims;

    public Inputs(GraphOperation op) {
      super(new RiscSqueeze<>(op), op, Arrays.asList("T", "squeeze_dims"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      squeezeDims = op.attributes().getAttrIntList("squeeze_dims");
    }
  }
}
