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

package org.tensorflow.op.image;

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
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;

/**
 * PNG-encode an image.
 * {@code image} is a 3-D uint8 or uint16 Tensor of shape {@code [height, width, channels]}
 * where {@code channels} is:
 * <ul>
 * <li>1: for grayscale.</li>
 * <li>2: for grayscale + alpha.</li>
 * <li>3: for RGB.</li>
 * <li>4: for RGBA.</li>
 * </ul>
 * <p>The ZLIB compression level, {@code compression}, can be -1 for the PNG-encoder
 * default or a value from 0 to 9.  9 is the highest compression level, generating
 * the smallest output, but is slower.
 */
@OpMetadata(
    opType = EncodePng.OP_NAME,
    inputsClass = EncodePng.Inputs.class
)
@Operator(
    group = "image"
)
public final class EncodePng extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EncodePng";

  private Output<TString> contents;

  public EncodePng(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    contents = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new EncodePng operation.
   *
   * @param scope current scope
   * @param image 3-D with shape {@code [height, width, channels]}.
   * @param options carries optional attribute values
   * @return a new instance of EncodePng
   */
  @Endpoint(
      describeByClass = true
  )
  public static EncodePng create(Scope scope, Operand<? extends TNumber> image,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "EncodePng");
    opBuilder.addInput(image.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.compression != null) {
          opBuilder.setAttr("compression", opts.compression);
        }
      }
    }
    return new EncodePng(opBuilder.build());
  }

  /**
   * Sets the compression option.
   *
   * @param compression Compression level.
   * @return this Options instance.
   */
  public static Options compression(Long compression) {
    return new Options().compression(compression);
  }

  /**
   * Gets contents.
   * 0-D. PNG-encoded image.
   * @return contents.
   */
  public Output<TString> contents() {
    return contents;
  }

  @Override
  public Output<TString> asOutput() {
    return contents;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.EncodePng}
   */
  public static class Options {
    private Long compression;

    private Options() {
    }

    /**
     * Sets the compression option.
     *
     * @param compression Compression level.
     * @return this Options instance.
     */
    public Options compression(Long compression) {
      this.compression = compression;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = EncodePng.class
  )
  public static class Inputs extends RawOpInputs<EncodePng> {
    /**
     * 3-D with shape {@code [height, width, channels]}.
     */
    public final Operand<? extends TNumber> image;

    /**
     * Compression level.
     */
    public final long compression;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new EncodePng(op), op, Arrays.asList("compression", "T"));
      int inputIndex = 0;
      image = (Operand<? extends TNumber>) op.input(inputIndex++);
      compression = op.attributes().getAttrInt("compression");
      T = op.attributes().getAttrType("T");
    }
  }
}
