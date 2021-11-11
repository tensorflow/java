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

package org.tensorflow.op.audio;

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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

/**
 * Decode a 16-bit PCM WAV file to a float tensor.
 * The -32768 to 32767 signed 16-bit values will be scaled to -1.0 to 1.0 in float.
 * <p>When desired_channels is set, if the input contains fewer channels than this
 * then the last channel will be duplicated to give the requested number, else if
 * the input has more channels than requested then the additional channels will be
 * ignored.
 * <p>If desired_samples is set, then the audio will be cropped or padded with zeroes
 * to the requested length.
 * <p>The first output contains a Tensor with the content of the audio samples. The
 * lowest dimension will be the number of channels, and the second will be the
 * number of samples. For example, a ten-sample-long stereo WAV file should give an
 * output shape of [10, 2].
 */
@OpMetadata(
    opType = DecodeWav.OP_NAME,
    inputsClass = DecodeWav.Inputs.class
)
@Operator(
    group = "audio"
)
public final class DecodeWav extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DecodeWav";

  private Output<TFloat32> audio;

  private Output<TInt32> sampleRate;

  public DecodeWav(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    audio = operation.output(outputIdx++);
    sampleRate = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DecodeWav operation.
   *
   * @param scope current scope
   * @param contents The WAV-encoded audio, usually from a file.
   * @param options carries optional attribute values
   * @return a new instance of DecodeWav
   */
  @Endpoint(
      describeByClass = true
  )
  public static DecodeWav create(Scope scope, Operand<TString> contents, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DecodeWav");
    opBuilder.addInput(contents.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.desiredChannels != null) {
          opBuilder.setAttr("desired_channels", opts.desiredChannels);
        }
        if (opts.desiredSamples != null) {
          opBuilder.setAttr("desired_samples", opts.desiredSamples);
        }
      }
    }
    return new DecodeWav(opBuilder.build());
  }

  /**
   * Sets the desiredChannels option.
   *
   * @param desiredChannels Number of sample channels wanted.
   * @return this Options instance.
   */
  public static Options desiredChannels(Long desiredChannels) {
    return new Options().desiredChannels(desiredChannels);
  }

  /**
   * Sets the desiredSamples option.
   *
   * @param desiredSamples Length of audio requested.
   * @return this Options instance.
   */
  public static Options desiredSamples(Long desiredSamples) {
    return new Options().desiredSamples(desiredSamples);
  }

  /**
   * Gets audio.
   * 2-D with shape {@code [length, channels]}.
   * @return audio.
   */
  public Output<TFloat32> audio() {
    return audio;
  }

  /**
   * Gets sampleRate.
   * Scalar holding the sample rate found in the WAV header.
   * @return sampleRate.
   */
  public Output<TInt32> sampleRate() {
    return sampleRate;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.audio.DecodeWav}
   */
  public static class Options {
    private Long desiredChannels;

    private Long desiredSamples;

    private Options() {
    }

    /**
     * Sets the desiredChannels option.
     *
     * @param desiredChannels Number of sample channels wanted.
     * @return this Options instance.
     */
    public Options desiredChannels(Long desiredChannels) {
      this.desiredChannels = desiredChannels;
      return this;
    }

    /**
     * Sets the desiredSamples option.
     *
     * @param desiredSamples Length of audio requested.
     * @return this Options instance.
     */
    public Options desiredSamples(Long desiredSamples) {
      this.desiredSamples = desiredSamples;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DecodeWav.class
  )
  public static class Inputs extends RawOpInputs<DecodeWav> {
    /**
     * The WAV-encoded audio, usually from a file.
     */
    public final Operand<TString> contents;

    /**
     * Number of sample channels wanted.
     */
    public final long desiredChannels;

    /**
     * Length of audio requested.
     */
    public final long desiredSamples;

    public Inputs(GraphOperation op) {
      super(new DecodeWav(op), op, Arrays.asList("desired_channels", "desired_samples"));
      int inputIndex = 0;
      contents = (Operand<TString>) op.input(inputIndex++);
      desiredChannels = op.attributes().getAttrInt("desired_channels");
      desiredSamples = op.attributes().getAttrInt("desired_samples");
    }
  }
}
