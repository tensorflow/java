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

/**
 * Produces a visualization of audio data over time.
 * Spectrograms are a standard way of representing audio information as a series of
 * slices of frequency information, one slice for each window of time. By joining
 * these together into a sequence, they form a distinctive fingerprint of the sound
 * over time.
 * <p>This op expects to receive audio data as an input, stored as floats in the range
 * -1 to 1, together with a window width in samples, and a stride specifying how
 * far to move the window between slices. From this it generates a three
 * dimensional output. The first dimension is for the channels in the input, so a
 * stereo audio input would have two here for example. The second dimension is time,
 * with successive frequency slices. The third dimension has an amplitude value for
 * each frequency during that time slice.
 * <p>This means the layout when converted and saved as an image is rotated 90 degrees
 * clockwise from a typical spectrogram. Time is descending down the Y axis, and
 * the frequency decreases from left to right.
 * <p>Each value in the result represents the square root of the sum of the real and
 * imaginary parts of an FFT on the current window of samples. In this way, the
 * lowest dimension represents the power of each frequency in the current window,
 * and adjacent windows are concatenated in the next dimension.
 * <p>To get a more intuitive and visual look at what this operation does, you can run
 * tensorflow/examples/wav_to_spectrogram to read in an audio file and save out the
 * resulting spectrogram as a PNG image.
 */
@OpMetadata(
    opType = AudioSpectrogram.OP_NAME,
    inputsClass = AudioSpectrogram.Inputs.class
)
@Operator(
    group = "audio"
)
public final class AudioSpectrogram extends RawOp implements Operand<TFloat32> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AudioSpectrogram";

  private Output<TFloat32> spectrogram;

  public AudioSpectrogram(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    spectrogram = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AudioSpectrogram operation.
   *
   * @param scope current scope
   * @param input Float representation of audio data.
   * @param windowSize How wide the input window is in samples. For the highest efficiency
   * this should be a power of two, but other values are accepted.
   * @param stride How widely apart the center of adjacent sample windows should be.
   * @param options carries optional attribute values
   * @return a new instance of AudioSpectrogram
   */
  @Endpoint(
      describeByClass = true
  )
  public static AudioSpectrogram create(Scope scope, Operand<TFloat32> input, Long windowSize,
      Long stride, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AudioSpectrogram");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("window_size", windowSize);
    opBuilder.setAttr("stride", stride);
    if (options != null) {
      for (Options opts : options) {
        if (opts.magnitudeSquared != null) {
          opBuilder.setAttr("magnitude_squared", opts.magnitudeSquared);
        }
      }
    }
    return new AudioSpectrogram(opBuilder.build());
  }

  /**
   * Sets the magnitudeSquared option.
   *
   * @param magnitudeSquared Whether to return the squared magnitude or just the
   * magnitude. Using squared magnitude can avoid extra calculations.
   * @return this Options instance.
   */
  public static Options magnitudeSquared(Boolean magnitudeSquared) {
    return new Options().magnitudeSquared(magnitudeSquared);
  }

  /**
   * Gets spectrogram.
   * 3D representation of the audio frequencies as an image.
   * @return spectrogram.
   */
  public Output<TFloat32> spectrogram() {
    return spectrogram;
  }

  @Override
  public Output<TFloat32> asOutput() {
    return spectrogram;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.audio.AudioSpectrogram}
   */
  public static class Options {
    private Boolean magnitudeSquared;

    private Options() {
    }

    /**
     * Sets the magnitudeSquared option.
     *
     * @param magnitudeSquared Whether to return the squared magnitude or just the
     * magnitude. Using squared magnitude can avoid extra calculations.
     * @return this Options instance.
     */
    public Options magnitudeSquared(Boolean magnitudeSquared) {
      this.magnitudeSquared = magnitudeSquared;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = AudioSpectrogram.class
  )
  public static class Inputs extends RawOpInputs<AudioSpectrogram> {
    /**
     * Float representation of audio data.
     */
    public final Operand<TFloat32> input;

    /**
     * How wide the input window is in samples. For the highest efficiency
     * this should be a power of two, but other values are accepted.
     */
    public final long windowSize;

    /**
     * How widely apart the center of adjacent sample windows should be.
     */
    public final long stride;

    /**
     * Whether to return the squared magnitude or just the
     * magnitude. Using squared magnitude can avoid extra calculations.
     */
    public final boolean magnitudeSquared;

    public Inputs(GraphOperation op) {
      super(new AudioSpectrogram(op), op, Arrays.asList("window_size", "stride", "magnitude_squared"));
      int inputIndex = 0;
      input = (Operand<TFloat32>) op.input(inputIndex++);
      windowSize = op.attributes().getAttrInt("window_size");
      stride = op.attributes().getAttrInt("stride");
      magnitudeSquared = op.attributes().getAttrBool("magnitude_squared");
    }
  }
}
