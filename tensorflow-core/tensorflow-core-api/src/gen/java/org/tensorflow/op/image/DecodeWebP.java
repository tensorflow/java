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

package org.tensorflow.op.image;

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TNumber;

/**
 * Decode a WebP-encoded image to a uint8 tensor.
 * The attr {@code channels} indicates the desired number of color channels for the
 * decoded image.
 * <p>Accepted values are:
 * <ul>
 * <li>0: Use the number of channels in the WebP-encoded image.</li>
 * <li>3: output an RGB image.</li>
 * <li>4: output an RGBA image.</li>
 * </ul>
 * <p>The number of channels must currently match that of the underlying file.
 * For WebP animations, only 4-channel RGBA is supported.
 */
@OpMetadata(
    opType = DecodeWebP.OP_NAME,
    inputsClass = DecodeWebP.Inputs.class
)
@Operator(
    group = "image"
)
public final class DecodeWebP<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DecodeWebP";

  private Output<T> image;

  public DecodeWebP(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    image = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DecodeWebP operation.
   *
   * @param scope current scope
   * @param contents 0-D.  The WebP-encoded image.
   * @param dtype The value of the dtype attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code DecodeWebP} output and operands
   * @return a new instance of DecodeWebP
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> DecodeWebP<T> create(Scope scope, Operand<TString> contents,
      Class<T> dtype, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DecodeWebP");
    opBuilder.addInput(contents.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.channels != null) {
          opBuilder.setAttr("channels", opts.channels);
        }
      }
    }
    return new DecodeWebP<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new DecodeWebP operation, with the default output types.
   *
   * @param scope current scope
   * @param contents 0-D.  The WebP-encoded image.
   * @param options carries optional attribute values
   * @return a new instance of DecodeWebP, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static DecodeWebP<TUint8> create(Scope scope, Operand<TString> contents,
      Options... options) {
    return create(scope, contents, TUint8.class, options);
  }

  /**
   * Sets the channels option.
   *
   * @param channels Number of color channels for the decoded image.
   * @return this Options instance.
   */
  public static Options channels(Long channels) {
    return new Options().channels(channels);
  }

  /**
   * Gets image.
   * 4-D with shape {@code [num_frames, height, width, channels]}.
   * @return image.
   */
  public Output<T> image() {
    return image;
  }

  @Override
  public Output<T> asOutput() {
    return image;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.image.DecodeWebP}
   */
  public static class Options {
    private Long channels;

    private Options() {
    }

    /**
     * Sets the channels option.
     *
     * @param channels Number of color channels for the decoded image.
     * @return this Options instance.
     */
    public Options channels(Long channels) {
      this.channels = channels;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DecodeWebP.class
  )
  public static class Inputs extends RawOpInputs<DecodeWebP<?>> {
    /**
     * 0-D.  The WebP-encoded image.
     */
    public final Operand<TString> contents;

    /**
     * Number of color channels for the decoded image.
     */
    public final long channels;

    /**
     * The dtype attribute
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new DecodeWebP<>(op), op, Arrays.asList("channels", "dtype"));
      int inputIndex = 0;
      contents = (Operand<TString>) op.input(inputIndex++);
      channels = op.attributes().getAttrInt("channels");
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
