// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.op.audio.AudioSpectrogram;
import org.tensorflow.op.audio.DecodeWav;
import org.tensorflow.op.audio.EncodeWav;
import org.tensorflow.op.audio.Mfcc;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

/**
 * An API for building {@code audio} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class AudioOps {
  private final Scope scope;

  private final Ops ops;

  AudioOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Produces a visualization of audio data over time.
   *  <p>
   *  Spectrograms are a standard way of representing audio information as a series of
   *  slices of frequency information, one slice for each window of time. By joining
   *  these together into a sequence, they form a distinctive fingerprint of the sound
   *  over time.
   *  <p>
   *  This op expects to receive audio data as an input, stored as floats in the range
   *  -1 to 1, together with a window width in samples, and a stride specifying how
   *  far to move the window between slices. From this it generates a three
   *  dimensional output. The first dimension is for the channels in the input, so a
   *  stereo audio input would have two here for example. The second dimension is time,
   *  with successive frequency slices. The third dimension has an amplitude value for
   *  each frequency during that time slice.
   *  <p>
   *  This means the layout when converted and saved as an image is rotated 90 degrees
   *  clockwise from a typical spectrogram. Time is descending down the Y axis, and
   *  the frequency decreases from left to right.
   *  <p>
   *  Each value in the result represents the square root of the sum of the real and
   *  imaginary parts of an FFT on the current window of samples. In this way, the
   *  lowest dimension represents the power of each frequency in the current window,
   *  and adjacent windows are concatenated in the next dimension.
   *  <p>
   *  To get a more intuitive and visual look at what this operation does, you can run
   *  tensorflow/examples/wav_to_spectrogram to read in an audio file and save out the
   *  resulting spectrogram as a PNG image.
   *
   * @param input Float representation of audio data.
   * @param windowSize How wide the input window is in samples. For the highest efficiency
   *  this should be a power of two, but other values are accepted.
   * @param stride How widely apart the center of adjacent sample windows should be.
   * @param options carries optional attributes values
   * @return a new instance of AudioSpectrogram
   */
  public AudioSpectrogram audioSpectrogram(Operand<TFloat32> input, Long windowSize, Long stride,
      AudioSpectrogram.Options... options) {
    return AudioSpectrogram.create(scope, input, windowSize, stride, options);
  }

  /**
   * Decode a 16-bit PCM WAV file to a float tensor.
   *  <p>
   *  The -32768 to 32767 signed 16-bit values will be scaled to -1.0 to 1.0 in float.
   *  <p>
   *  When desired_channels is set, if the input contains fewer channels than this
   *  then the last channel will be duplicated to give the requested number, else if
   *  the input has more channels than requested then the additional channels will be
   *  ignored.
   *  <p>
   *  If desired_samples is set, then the audio will be cropped or padded with zeroes
   *  to the requested length.
   *  <p>
   *  The first output contains a Tensor with the content of the audio samples. The
   *  lowest dimension will be the number of channels, and the second will be the
   *  number of samples. For example, a ten-sample-long stereo WAV file should give an
   *  output shape of [10, 2].
   *
   * @param contents The WAV-encoded audio, usually from a file.
   * @param options carries optional attributes values
   * @return a new instance of DecodeWav
   */
  public DecodeWav decodeWav(Operand<TString> contents, DecodeWav.Options... options) {
    return DecodeWav.create(scope, contents, options);
  }

  /**
   * Encode audio data using the WAV file format.
   *  <p>
   *  This operation will generate a string suitable to be saved out to create a .wav
   *  audio file. It will be encoded in the 16-bit PCM format. It takes in float
   *  values in the range -1.0f to 1.0f, and any outside that value will be clamped to
   *  that range.
   *  <p>
   *  `audio` is a 2-D float Tensor of shape `[length, channels]`.
   *  `sample_rate` is a scalar Tensor holding the rate to use (e.g. 44100).
   *
   * @param audio 2-D with shape `[length, channels]`.
   * @param sampleRate Scalar containing the sample frequency.
   * @return a new instance of EncodeWav
   */
  public EncodeWav encodeWav(Operand<TFloat32> audio, Operand<TInt32> sampleRate) {
    return EncodeWav.create(scope, audio, sampleRate);
  }

  /**
   * Transforms a spectrogram into a form that's useful for speech recognition.
   *  <p>
   *  Mel Frequency Cepstral Coefficients are a way of representing audio data that's
   *  been effective as an input feature for machine learning. They are created by
   *  taking the spectrum of a spectrogram (a 'cepstrum'), and discarding some of the
   *  higher frequencies that are less significant to the human ear. They have a long
   *  history in the speech recognition world, and https://en.wikipedia.org/wiki/Mel-frequency_cepstrum
   *  is a good resource to learn more.
   *
   * @param spectrogram Typically produced by the Spectrogram op, with magnitude_squared
   *  set to true.
   * @param sampleRate How many samples per second the source audio used.
   * @param options carries optional attributes values
   * @return a new instance of Mfcc
   */
  public Mfcc mfcc(Operand<TFloat32> spectrogram, Operand<TInt32> sampleRate,
      Mfcc.Options... options) {
    return Mfcc.create(scope, spectrogram, sampleRate, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
