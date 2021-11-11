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

package org.tensorflow.op.train;

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
 * An identity op that triggers an error if a gradient is requested.
 * When executed in a graph, this op outputs its input tensor as-is.
 * <p>When building ops to compute gradients, the TensorFlow gradient system
 * will return an error when trying to lookup the gradient of this op,
 * because no gradient must ever be registered for this function.  This
 * op exists to prevent subtle bugs from silently returning unimplemented
 * gradients in some corner cases.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = PreventGradient.OP_NAME,
    inputsClass = PreventGradient.Inputs.class
)
@Operator(
    group = "train"
)
public final class PreventGradient<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "PreventGradient";

  private Output<T> output;

  public PreventGradient(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new PreventGradient operation.
   *
   * @param scope current scope
   * @param input any tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code PreventGradient} output and operands
   * @return a new instance of PreventGradient
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> PreventGradient<T> create(Scope scope, Operand<T> input,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "PreventGradient");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.message != null) {
          opBuilder.setAttr("message", opts.message);
        }
      }
    }
    return new PreventGradient<>(opBuilder.build());
  }

  /**
   * Sets the message option.
   *
   * @param message Will be printed in the error when anyone tries to differentiate
   * this operation.
   * @return this Options instance.
   */
  public static Options message(String message) {
    return new Options().message(message);
  }

  /**
   * Gets output.
   * the same input tensor.
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
   * Optional attributes for {@link org.tensorflow.op.train.PreventGradient}
   */
  public static class Options {
    private String message;

    private Options() {
    }

    /**
     * Sets the message option.
     *
     * @param message Will be printed in the error when anyone tries to differentiate
     * this operation.
     * @return this Options instance.
     */
    public Options message(String message) {
      this.message = message;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = PreventGradient.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<PreventGradient<T>> {
    /**
     * any tensor.
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Will be printed in the error when anyone tries to differentiate
     * this operation.
     */
    public final String message;

    public Inputs(GraphOperation op) {
      super(new PreventGradient<>(op), op, Arrays.asList("T", "message"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      message = op.attributes().getAttrString("message");
    }
  }
}
