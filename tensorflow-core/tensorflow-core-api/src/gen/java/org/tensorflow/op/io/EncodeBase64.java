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

package org.tensorflow.op.io;

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
import org.tensorflow.types.TString;

/**
 * Encode strings into web-safe base64 format.
 * Refer to  <a href="https://en.wikipedia.org/wiki/Base64">this article</a>  for more information on
 * base64 format. Base64 strings may have padding with '=' at the
 * end so that the encoded has length multiple of 4. See Padding section of the
 * link above.
 * <p>Web-safe means that the encoder uses - and _ instead of + and /.
 */
@OpMetadata(
    opType = EncodeBase64.OP_NAME,
    inputsClass = EncodeBase64.Inputs.class
)
@Operator(
    group = "io"
)
public final class EncodeBase64 extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EncodeBase64";

  private Output<TString> output;

  public EncodeBase64(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new EncodeBase64 operation.
   *
   * @param scope current scope
   * @param input Strings to be encoded.
   * @param options carries optional attribute values
   * @return a new instance of EncodeBase64
   */
  @Endpoint(
      describeByClass = true
  )
  public static EncodeBase64 create(Scope scope, Operand<TString> input, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "EncodeBase64");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.pad != null) {
          opBuilder.setAttr("pad", opts.pad);
        }
      }
    }
    return new EncodeBase64(opBuilder.build());
  }

  /**
   * Sets the pad option.
   *
   * @param pad Bool whether padding is applied at the ends.
   * @return this Options instance.
   */
  public static Options pad(Boolean pad) {
    return new Options().pad(pad);
  }

  /**
   * Gets output.
   * Input strings encoded in base64.
   * @return output.
   */
  public Output<TString> output() {
    return output;
  }

  @Override
  public Output<TString> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.io.EncodeBase64}
   */
  public static class Options {
    private Boolean pad;

    private Options() {
    }

    /**
     * Sets the pad option.
     *
     * @param pad Bool whether padding is applied at the ends.
     * @return this Options instance.
     */
    public Options pad(Boolean pad) {
      this.pad = pad;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = EncodeBase64.class
  )
  public static class Inputs extends RawOpInputs<EncodeBase64> {
    /**
     * Strings to be encoded.
     */
    public final Operand<TString> input;

    /**
     * Bool whether padding is applied at the ends.
     */
    public final boolean pad;

    public Inputs(GraphOperation op) {
      super(new EncodeBase64(op), op, Arrays.asList("pad"));
      int inputIndex = 0;
      input = (Operand<TString>) op.input(inputIndex++);
      pad = op.attributes().getAttrBool("pad");
    }
  }
}
