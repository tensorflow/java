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
package org.tensorflow.op.kotlin

import kotlin.Boolean
import kotlin.Float
import kotlin.Long
import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.audio.AudioSpectrogram
import org.tensorflow.op.audio.DecodeWav
import org.tensorflow.op.audio.EncodeWav
import org.tensorflow.op.audio.Mfcc
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32
import org.tensorflow.types.TString

/**
 * An API for building `audio` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class AudioOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.AudioOps = ops.java.audio

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Produces a visualization of audio data over time.
     *  Spectrograms are a standard way of representing audio information as a series of
     *  slices of frequency information, one slice for each window of time. By joining
     *  these together into a sequence, they form a distinctive fingerprint of the sound
     *  over time.
     *  
     * This op expects to receive audio data as an input, stored as floats in the range
     *  -1 to 1, together with a window width in samples, and a stride specifying how
     *  far to move the window between slices. From this it generates a three
     *  dimensional output. The first dimension is for the channels in the input, so a
     *  stereo audio input would have two here for example. The second dimension is time,
     *  with successive frequency slices. The third dimension has an amplitude value for
     *  each frequency during that time slice.
     *  
     * This means the layout when converted and saved as an image is rotated 90 degrees
     *  clockwise from a typical spectrogram. Time is descending down the Y axis, and
     *  the frequency decreases from left to right.
     *  
     * Each value in the result represents the square root of the sum of the real and
     *  imaginary parts of an FFT on the current window of samples. In this way, the
     *  lowest dimension represents the power of each frequency in the current window,
     *  and adjacent windows are concatenated in the next dimension.
     *  
     * To get a more intuitive and visual look at what this operation does, you can run
     *  tensorflow/examples/wav_to_spectrogram to read in an audio file and save out the
     *  resulting spectrogram as a PNG image.
     *
     * @param input Float representation of audio data.
     * @param windowSize How wide the input window is in samples. For the highest efficiency
     *  this should be a power of two, but other values are accepted.
     * @param stride How widely apart the center of adjacent sample windows should be.
     * @param options carries optional attribute values
     * @return a new instance of AudioSpectrogram
     * @see org.tensorflow.op.AudioOps.audioSpectrogram
     * @param magnitudeSquared Sets the magnitudeSquared option.
     *
     * @param magnitudeSquared Whether to return the squared magnitude or just the
     *  magnitude. Using squared magnitude can avoid extra calculations.
     * @return this Options instance.
     */
    public fun audioSpectrogram(
        input: Operand<TFloat32>,
        windowSize: Long,
        stride: Long,
        magnitudeSquared: Boolean? = null
    ): AudioSpectrogram = java.audioSpectrogram(    
        input,
        windowSize,
        stride,
        *listOfNotNull(
            magnitudeSquared?.let{ org.tensorflow.op.audio.AudioSpectrogram.magnitudeSquared(it) }
        ).toTypedArray()
        )

    /**
     * Decode a 16-bit PCM WAV file to a float tensor.
     *  The -32768 to 32767 signed 16-bit values will be scaled to -1.0 to 1.0 in float.
     *  
     * When desired_channels is set, if the input contains fewer channels than this
     *  then the last channel will be duplicated to give the requested number, else if
     *  the input has more channels than requested then the additional channels will be
     *  ignored.
     *  
     * If desired_samples is set, then the audio will be cropped or padded with zeroes
     *  to the requested length.
     *  
     * The first output contains a Tensor with the content of the audio samples. The
     *  lowest dimension will be the number of channels, and the second will be the
     *  number of samples. For example, a ten-sample-long stereo WAV file should give an
     *  output shape of &#91;10, 2&#93;.
     *
     * @param contents The WAV-encoded audio, usually from a file.
     * @param options carries optional attribute values
     * @return a new instance of DecodeWav
     * @see org.tensorflow.op.AudioOps.decodeWav
     * @param desiredChannels Sets the desiredChannels option.
     *
     * @param desiredChannels Number of sample channels wanted.
     * @return this Options instance.
     * @param desiredSamples Sets the desiredSamples option.
     *
     * @param desiredSamples Length of audio requested.
     * @return this Options instance.
     */
    public fun decodeWav(
        contents: Operand<TString>,
        desiredChannels: Long? = null,
        desiredSamples: Long? = null
    ): DecodeWav = java.decodeWav(    
        contents,
        *listOfNotNull(
            desiredChannels?.let{ org.tensorflow.op.audio.DecodeWav.desiredChannels(it) },
            desiredSamples?.let{ org.tensorflow.op.audio.DecodeWav.desiredSamples(it) }
        ).toTypedArray()
        )

    /**
     * Encode audio data using the WAV file format.
     *  This operation will generate a string suitable to be saved out to create a .wav
     *  audio file. It will be encoded in the 16-bit PCM format. It takes in float
     *  values in the range -1.0f to 1.0f, and any outside that value will be clamped to
     *  that range.
     *  
     * `audio` is a 2-D float Tensor of shape `&#91;length, channels&#93;`.
     *  `sample_rate` is a scalar Tensor holding the rate to use (e.g. 44100).
     *
     * @param audio 2-D with shape `&#91;length, channels&#93;`.
     * @param sampleRate Scalar containing the sample frequency.
     * @return a new instance of EncodeWav
     * @see org.tensorflow.op.AudioOps.encodeWav
     */
    public fun encodeWav(audio: Operand<TFloat32>, sampleRate: Operand<TInt32>): EncodeWav =
            java.encodeWav(    
        audio,
        sampleRate
        )

    /**
     * Transforms a spectrogram into a form that's useful for speech recognition.
     *  Mel Frequency Cepstral Coefficients are a way of representing audio data that's
     *  been effective as an input feature for machine learning. They are created by
     *  taking the spectrum of a spectrogram (a 'cepstrum'), and discarding some of the
     *  higher frequencies that are less significant to the human ear. They have a long
     *  history in the speech recognition world, and
     * https://en.wikipedia.org/wiki/Mel-frequency_cepstrum
     *  is a good resource to learn more.
     *
     * @param spectrogram Typically produced by the Spectrogram op, with magnitude_squared
     *  set to true.
     * @param sampleRate How many samples per second the source audio used.
     * @param options carries optional attribute values
     * @return a new instance of Mfcc
     * @see org.tensorflow.op.AudioOps.mfcc
     * @param upperFrequencyLimit Sets the upperFrequencyLimit option.
     *
     * @param upperFrequencyLimit The highest frequency to use when calculating the
     *  ceptstrum.
     * @return this Options instance.
     * @param lowerFrequencyLimit Sets the lowerFrequencyLimit option.
     *
     * @param lowerFrequencyLimit The lowest frequency to use when calculating the
     *  ceptstrum.
     * @return this Options instance.
     * @param filterbankChannelCount Sets the filterbankChannelCount option.
     *
     * @param filterbankChannelCount Resolution of the Mel bank used internally.
     * @return this Options instance.
     * @param dctCoefficientCount Sets the dctCoefficientCount option.
     *
     * @param dctCoefficientCount How many output channels to produce per time slice.
     * @return this Options instance.
     */
    public fun mfcc(
        spectrogram: Operand<TFloat32>,
        sampleRate: Operand<TInt32>,
        upperFrequencyLimit: Float? = null,
        lowerFrequencyLimit: Float? = null,
        filterbankChannelCount: Long? = null,
        dctCoefficientCount: Long? = null
    ): Mfcc = java.mfcc(    
        spectrogram,
        sampleRate,
        *listOfNotNull(
            upperFrequencyLimit?.let{ org.tensorflow.op.audio.Mfcc.upperFrequencyLimit(it) },
            lowerFrequencyLimit?.let{ org.tensorflow.op.audio.Mfcc.lowerFrequencyLimit(it) },
            filterbankChannelCount?.let{ org.tensorflow.op.audio.Mfcc.filterbankChannelCount(it) },
            dctCoefficientCount?.let{ org.tensorflow.op.audio.Mfcc.dctCoefficientCount(it) }
        ).toTypedArray()
        )
}
