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

  AudioOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link DecodeWav} operation
   *
   * @param contents The WAV-encoded audio, usually from a file.
   * @param options carries optional attributes values
   * @return a new instance of DecodeWav
   * @see org.tensorflow.op.audio.DecodeWav
   */
  public DecodeWav decodeWav(Operand<TString> contents, DecodeWav.Options... options) {
    return DecodeWav.create(scope, contents, options);
  }

  /**
   * Builds an {@link EncodeWav} operation
   *
   * @param audio 2-D with shape `[length, channels]`.
   * @param sampleRate Scalar containing the sample frequency.
   * @return a new instance of EncodeWav
   * @see org.tensorflow.op.audio.EncodeWav
   */
  public EncodeWav encodeWav(Operand<TFloat32> audio, Operand<TInt32> sampleRate) {
    return EncodeWav.create(scope, audio, sampleRate);
  }

  /**
   * Builds an {@link Mfcc} operation
   *
   * @param spectrogram Typically produced by the Spectrogram op, with magnitude_squared
   * @param sampleRate How many samples per second the source audio used.
   * @param options carries optional attributes values
   * @return a new instance of Mfcc
   * @see org.tensorflow.op.audio.Mfcc
   */
  public Mfcc mfcc(Operand<TFloat32> spectrogram, Operand<TInt32> sampleRate,
      Mfcc.Options... options) {
    return Mfcc.create(scope, spectrogram, sampleRate, options);
  }

  /**
   * Builds an {@link AudioSpectrogram} operation
   *
   * @param input Float representation of audio data.
   * @param windowSize How wide the input window is in samples. For the highest efficiency
   * @param stride How widely apart the center of adjacent sample windows should be.
   * @param options carries optional attributes values
   * @return a new instance of AudioSpectrogram
   * @see org.tensorflow.op.audio.AudioSpectrogram
   */
  public AudioSpectrogram audioSpectrogram(Operand<TFloat32> input, Long windowSize, Long stride,
      AudioSpectrogram.Options... options) {
    return AudioSpectrogram.create(scope, input, windowSize, stride, options);
  }
}
