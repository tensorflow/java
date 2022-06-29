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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * Writes an audio summary.
 * Writes encoded audio summary {@code tensor} at {@code step} with {@code tag} using summary {@code writer}.
 * {@code sample_rate} is the audio sample rate is Hz.
 */
@OpMetadata(
    opType = WriteAudioSummary.OP_NAME,
    inputsClass = WriteAudioSummary.Inputs.class
)
public final class WriteAudioSummary extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "WriteAudioSummary";

  public WriteAudioSummary(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new WriteAudioSummary operation.
   *
   * @param scope current scope
   * @param writer The writer value
   * @param step The step value
   * @param tag The tag value
   * @param tensor The tensor value
   * @param sampleRate The sampleRate value
   * @param options carries optional attribute values
   * @return a new instance of WriteAudioSummary
   */
  @Endpoint(
      describeByClass = true
  )
  public static WriteAudioSummary create(Scope scope, Operand<? extends TType> writer,
      Operand<TInt64> step, Operand<TString> tag, Operand<TFloat32> tensor,
      Operand<TFloat32> sampleRate, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "WriteAudioSummary");
    opBuilder.addInput(writer.asOutput());
    opBuilder.addInput(step.asOutput());
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
    return new WriteAudioSummary(opBuilder.build());
  }

  /**
   * Sets the maxOutputs option.
   *
   * @param maxOutputs the maxOutputs option
   * @return this Options instance.
   */
  public static Options maxOutputs(Long maxOutputs) {
    return new Options().maxOutputs(maxOutputs);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.summary.WriteAudioSummary}
   */
  public static class Options {
    private Long maxOutputs;

    private Options() {
    }

    /**
     * Sets the maxOutputs option.
     *
     * @param maxOutputs the maxOutputs option
     * @return this Options instance.
     */
    public Options maxOutputs(Long maxOutputs) {
      this.maxOutputs = maxOutputs;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = WriteAudioSummary.class
  )
  public static class Inputs extends RawOpInputs<WriteAudioSummary> {
    /**
     * The writer input
     */
    public final Operand<? extends TType> writer;

    /**
     * The step input
     */
    public final Operand<TInt64> step;

    /**
     * The tag input
     */
    public final Operand<TString> tag;

    /**
     * The tensor input
     */
    public final Operand<TFloat32> tensor;

    /**
     * The sampleRate input
     */
    public final Operand<TFloat32> sampleRate;

    /**
     * The maxOutputs attribute
     */
    public final long maxOutputs;

    public Inputs(GraphOperation op) {
      super(new WriteAudioSummary(op), op, Arrays.asList("max_outputs"));
      int inputIndex = 0;
      writer = (Operand<? extends TType>) op.input(inputIndex++);
      step = (Operand<TInt64>) op.input(inputIndex++);
      tag = (Operand<TString>) op.input(inputIndex++);
      tensor = (Operand<TFloat32>) op.input(inputIndex++);
      sampleRate = (Operand<TFloat32>) op.input(inputIndex++);
      maxOutputs = op.attributes().getAttrInt("max_outputs");
    }
  }
}
