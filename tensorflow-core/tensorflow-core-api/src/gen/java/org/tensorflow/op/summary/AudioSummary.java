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

package org.tensorflow.op.summary;

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
import org.tensorflow.types.TString;

/**
 * Outputs a {@code Summary} protocol buffer with audio.
 * The summary has up to {@code max_outputs} summary values containing audio. The
 * audio is built from {@code tensor} which must be 3-D with shape {@code [batch_size, frames, channels]} or 2-D with shape {@code [batch_size, frames]}. The values are
 * assumed to be in the range of {@code [-1.0, 1.0]} with a sample rate of {@code sample_rate}.
 * <p>The {@code tag} argument is a scalar {@code Tensor} of type {@code string}.  It is used to
 * build the {@code tag} of the summary values:
 * <ul>
 * <li>If {@code max_outputs} is 1, the summary value tag is '<em>tag</em>/audio'.</li>
 * <li>If {@code max_outputs} is greater than 1, the summary value tags are
 * generated sequentially as '<em>tag</em>/audio/0', '<em>tag</em>/audio/1', etc.</li>
 * </ul>
 */
@OpMetadata(
    opType = AudioSummary.OP_NAME,
    inputsClass = AudioSummary.Inputs.class
)
@Operator(
    group = "summary"
)
public final class AudioSummary extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AudioSummaryV2";

  private Output<TString> summary;

  public AudioSummary(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    summary = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AudioSummaryV2 operation.
   *
   * @param scope current scope
   * @param tag Scalar. Used to build the {@code tag} attribute of the summary values.
   * @param tensor 2-D of shape {@code [batch_size, frames]}.
   * @param sampleRate The sample rate of the signal in hertz.
   * @param options carries optional attribute values
   * @return a new instance of AudioSummary
   */
  @Endpoint(
      describeByClass = true
  )
  public static AudioSummary create(Scope scope, Operand<TString> tag, Operand<TFloat32> tensor,
      Operand<TFloat32> sampleRate, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AudioSummary");
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(sampleRate.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.maxOutputs != null) {
          opBuilder.setAttr("max_outputs", opts.maxOutputs);
        }
      }
    }
    return new AudioSummary(opBuilder.build());
  }

  /**
   * Sets the maxOutputs option.
   *
   * @param maxOutputs Max number of batch elements to generate audio for.
   * @return this Options instance.
   */
  public static Options maxOutputs(Long maxOutputs) {
    return new Options().maxOutputs(maxOutputs);
  }

  /**
   * Gets summary.
   * Scalar. Serialized {@code Summary} protocol buffer.
   * @return summary.
   */
  public Output<TString> summary() {
    return summary;
  }

  @Override
  public Output<TString> asOutput() {
    return summary;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.summary.AudioSummary}
   */
  public static class Options {
    private Long maxOutputs;

    private Options() {
    }

    /**
     * Sets the maxOutputs option.
     *
     * @param maxOutputs Max number of batch elements to generate audio for.
     * @return this Options instance.
     */
    public Options maxOutputs(Long maxOutputs) {
      this.maxOutputs = maxOutputs;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = AudioSummary.class
  )
  public static class Inputs extends RawOpInputs<AudioSummary> {
    /**
     * Scalar. Used to build the {@code tag} attribute of the summary values.
     */
    public final Operand<TString> tag;

    /**
     * 2-D of shape {@code [batch_size, frames]}.
     */
    public final Operand<TFloat32> tensor;

    /**
     * The sample rate of the signal in hertz.
     */
    public final Operand<TFloat32> sampleRate;

    /**
     * Max number of batch elements to generate audio for.
     */
    public final long maxOutputs;

    public Inputs(GraphOperation op) {
      super(new AudioSummary(op), op, Arrays.asList("max_outputs"));
      int inputIndex = 0;
      tag = (Operand<TString>) op.input(inputIndex++);
      tensor = (Operand<TFloat32>) op.input(inputIndex++);
      sampleRate = (Operand<TFloat32>) op.input(inputIndex++);
      maxOutputs = op.attributes().getAttrInt("max_outputs");
    }
  }
}
