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
 * Decompress strings.
 * This op decompresses each element of the {@code bytes} input {@code Tensor}, which
 * is assumed to be compressed using the given {@code compression_type}.
 * <p>The {@code output} is a string {@code Tensor} of the same shape as {@code bytes},
 * each element containing the decompressed data from the corresponding
 * element in {@code bytes}.
 */
@OpMetadata(
    opType = DecodeCompressed.OP_NAME,
    inputsClass = DecodeCompressed.Inputs.class
)
@Operator(
    group = "io"
)
public final class DecodeCompressed extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DecodeCompressed";

  private Output<TString> output;

  public DecodeCompressed(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DecodeCompressed operation.
   *
   * @param scope current scope
   * @param bytes A Tensor of string which is compressed.
   * @param options carries optional attribute values
   * @return a new instance of DecodeCompressed
   */
  @Endpoint(
      describeByClass = true
  )
  public static DecodeCompressed create(Scope scope, Operand<TString> bytes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DecodeCompressed");
    opBuilder.addInput(bytes.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.compressionType != null) {
          opBuilder.setAttr("compression_type", opts.compressionType);
        }
      }
    }
    return new DecodeCompressed(opBuilder.build());
  }

  /**
   * Sets the compressionType option.
   *
   * @param compressionType A scalar containing either (i) the empty string (no
   * compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
   * @return this Options instance.
   */
  public static Options compressionType(String compressionType) {
    return new Options().compressionType(compressionType);
  }

  /**
   * Gets output.
   * A Tensor with the same shape as input {@code bytes}, uncompressed
   * from bytes.
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
   * Optional attributes for {@link org.tensorflow.op.io.DecodeCompressed}
   */
  public static class Options {
    private String compressionType;

    private Options() {
    }

    /**
     * Sets the compressionType option.
     *
     * @param compressionType A scalar containing either (i) the empty string (no
     * compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
     * @return this Options instance.
     */
    public Options compressionType(String compressionType) {
      this.compressionType = compressionType;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DecodeCompressed.class
  )
  public static class Inputs extends RawOpInputs<DecodeCompressed> {
    /**
     * A Tensor of string which is compressed.
     */
    public final Operand<TString> bytes;

    /**
     * A scalar containing either (i) the empty string (no
     * compression), (ii) "ZLIB", or (iii) "GZIP".
     */
    public final String compressionType;

    public Inputs(GraphOperation op) {
      super(new DecodeCompressed(op), op, Arrays.asList("compression_type"));
      int inputIndex = 0;
      bytes = (Operand<TString>) op.input(inputIndex++);
      compressionType = op.attributes().getAttrString("compression_type");
    }
  }
}
