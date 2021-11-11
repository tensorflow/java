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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;

/**
 * Prints a string scalar.
 * Prints a string scalar to the desired output_stream.
 */
@OpMetadata(
    opType = Print.OP_NAME,
    inputsClass = Print.Inputs.class
)
@Operator
public final class Print extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "PrintV2";

  public Print(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new PrintV2 operation.
   *
   * @param scope current scope
   * @param input The string scalar to print.
   * @param options carries optional attribute values
   * @return a new instance of Print
   */
  @Endpoint(
      describeByClass = true
  )
  public static Print create(Scope scope, Operand<TString> input, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Print");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.outputStream != null) {
          opBuilder.setAttr("output_stream", opts.outputStream);
        }
        if (opts.end != null) {
          opBuilder.setAttr("end", opts.end);
        }
      }
    }
    return new Print(opBuilder.build());
  }

  /**
   * Sets the outputStream option.
   *
   * @param outputStream A string specifying the output stream or logging level to print to.
   * @return this Options instance.
   */
  public static Options outputStream(String outputStream) {
    return new Options().outputStream(outputStream);
  }

  /**
   * Sets the end option.
   *
   * @param end the end option
   * @return this Options instance.
   */
  public static Options end(String end) {
    return new Options().end(end);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.Print}
   */
  public static class Options {
    private String outputStream;

    private String end;

    private Options() {
    }

    /**
     * Sets the outputStream option.
     *
     * @param outputStream A string specifying the output stream or logging level to print to.
     * @return this Options instance.
     */
    public Options outputStream(String outputStream) {
      this.outputStream = outputStream;
      return this;
    }

    /**
     * Sets the end option.
     *
     * @param end the end option
     * @return this Options instance.
     */
    public Options end(String end) {
      this.end = end;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Print.class
  )
  public static class Inputs extends RawOpInputs<Print> {
    /**
     * The string scalar to print.
     */
    public final Operand<TString> input;

    /**
     * A string specifying the output stream or logging level to print to.
     */
    public final String outputStream;

    /**
     * The end attribute
     */
    public final String end;

    public Inputs(GraphOperation op) {
      super(new Print(op), op, Arrays.asList("output_stream", "end"));
      int inputIndex = 0;
      input = (Operand<TString>) op.input(inputIndex++);
      outputStream = op.attributes().getAttrString("output_stream");
      end = op.attributes().getAttrString("end");
    }
  }
}
