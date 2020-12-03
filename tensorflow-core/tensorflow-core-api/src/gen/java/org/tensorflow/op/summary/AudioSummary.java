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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TString;

/**
 * Outputs a `Summary` protocol buffer with audio.
 * <p>
 * The summary has up to `max_outputs` summary values containing audio. The
 * audio is built from `tensor` which must be 3-D with shape `[batch_size,
 * frames, channels]` or 2-D with shape `[batch_size, frames]`. The values are
 * assumed to be in the range of `[-1.0, 1.0]` with a sample rate of `sample_rate`.
 * <p>
 * The `tag` argument is a scalar `Tensor` of type `string`.  It is used to
 * build the `tag` of the summary values:
 * <ul>
 * <li>
 * If `max_outputs` is 1, the summary value tag is '<i>tag</i>/audio'.
 * </li>
 * <li>
 * If `max_outputs` is greater than 1, the summary value tags are
 *    generated sequentially as '<i>tag</i>/audio/0', '<i>tag</i>/audio/1', etc.
 */
@Operator(group = "summary")
public final class AudioSummary extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.summary.AudioSummary}
   */
  public static class Options {
    
    /**
     * @param maxOutputs Max number of batch elements to generate audio for.
     */
    public Options maxOutputs(Long maxOutputs) {
      this.maxOutputs = maxOutputs;
      return this;
    }
    
    private Long maxOutputs;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new AudioSummary operation.
   * 
   * @param scope current scope
   * @param tag Scalar. Used to build the `tag` attribute of the summary values.
   * @param tensor 2-D of shape `[batch_size, frames]`.
   * @param sampleRate The sample rate of the signal in hertz.
   * @param options carries optional attributes values
   * @return a new instance of AudioSummary
   */
  @Endpoint(describeByClass = true)
  public static AudioSummary create(Scope scope, Operand<TString> tag, Operand<TFloat32> tensor, Operand<TFloat32> sampleRate, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("AudioSummaryV2", scope.makeOpName("AudioSummary"));
    opBuilder.addInput(tag.asOutput());
    opBuilder.addInput(tensor.asOutput());
    opBuilder.addInput(sampleRate.asOutput());
    opBuilder = scope.apply(opBuilder);
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
   * @param maxOutputs Max number of batch elements to generate audio for.
   */
  public static Options maxOutputs(Long maxOutputs) {
    return new Options().maxOutputs(maxOutputs);
  }
  
  /**
   * Scalar. Serialized `Summary` protocol buffer.
   */
  public Output<TString> summary() {
    return summary;
  }
  
  @Override
  public Output<TString> asOutput() {
    return summary;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "AudioSummaryV2";
  
  private Output<TString> summary;
  
  private AudioSummary(Operation operation) {
    super(operation);
    int outputIdx = 0;
    summary = operation.output(outputIdx++);
  }
}
