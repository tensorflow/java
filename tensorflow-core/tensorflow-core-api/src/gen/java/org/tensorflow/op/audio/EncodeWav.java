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
 * Encode audio data using the WAV file format.
 * This operation will generate a string suitable to be saved out to create a .wav
 * audio file. It will be encoded in the 16-bit PCM format. It takes in float
 * values in the range -1.0f to 1.0f, and any outside that value will be clamped to
 * that range.
 * <p>{@code audio} is a 2-D float Tensor of shape {@code [length, channels]}.
 * {@code sample_rate} is a scalar Tensor holding the rate to use (e.g. 44100).
 */
@OpMetadata(
    opType = EncodeWav.OP_NAME,
    inputsClass = EncodeWav.Inputs.class
)
@Operator(
    group = "audio"
)
public final class EncodeWav extends RawOp implements Operand<TString> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "EncodeWav";

  private Output<TString> contents;

  public EncodeWav(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    contents = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new EncodeWav operation.
   *
   * @param scope current scope
   * @param audio 2-D with shape {@code [length, channels]}.
   * @param sampleRate Scalar containing the sample frequency.
   * @return a new instance of EncodeWav
   */
  @Endpoint(
      describeByClass = true
  )
  public static EncodeWav create(Scope scope, Operand<TFloat32> audio, Operand<TInt32> sampleRate) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "EncodeWav");
    opBuilder.addInput(audio.asOutput());
    opBuilder.addInput(sampleRate.asOutput());
    return new EncodeWav(opBuilder.build());
  }

  /**
   * Gets contents.
   * 0-D. WAV-encoded file contents.
   * @return contents.
   */
  public Output<TString> contents() {
    return contents;
  }

  @Override
  public Output<TString> asOutput() {
    return contents;
  }

  @OpInputsMetadata(
      outputsClass = EncodeWav.class
  )
  public static class Inputs extends RawOpInputs<EncodeWav> {
    /**
     * 2-D with shape {@code [length, channels]}.
     */
    public final Operand<TFloat32> audio;

    /**
     * Scalar containing the sample frequency.
     */
    public final Operand<TInt32> sampleRate;

    public Inputs(GraphOperation op) {
      super(new EncodeWav(op), op, Arrays.asList());
      int inputIndex = 0;
      audio = (Operand<TFloat32>) op.input(inputIndex++);
      sampleRate = (Operand<TInt32>) op.input(inputIndex++);
    }
  }
}
