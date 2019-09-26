package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.op.summary.AudioSummary;
import org.tensorflow.op.summary.HistogramSummary;
import org.tensorflow.op.summary.ImageSummary;
import org.tensorflow.op.summary.MergeSummary;
import org.tensorflow.op.summary.ScalarSummary;
import org.tensorflow.op.summary.TensorSummary;

/**
 * An API for building {@code summary} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class SummaryOps {
  private final Scope scope;

  SummaryOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link TensorSummary} operation
   *
   * @param tag A string attached to this summary. Used for organization in TensorBoard.
   * @param tensor A tensor to serialize.
   * @param serializedSummaryMetadata A serialized SummaryMetadata proto. Contains plugin
   * @return a new instance of TensorSummary
   * @see org.tensorflow.op.summary.TensorSummary
   */
  public <T> TensorSummary tensorSummary(Operand<String> tag, Operand<T> tensor,
      Operand<String> serializedSummaryMetadata) {
    return TensorSummary.create(scope, tag, tensor, serializedSummaryMetadata);
  }

  /**
   * Builds an {@link ImageSummary} operation
   *
   * @param tag Scalar. Used to build the `tag` attribute of the summary values.
   * @param tensor 4-D of shape `[batch_size, height, width, channels]` where
   * @param options carries optional attributes values
   * @return a new instance of ImageSummary
   * @see org.tensorflow.op.summary.ImageSummary
   */
  public <T extends Number> ImageSummary imageSummary(Operand<String> tag, Operand<T> tensor,
      ImageSummary.Options... options) {
    return ImageSummary.create(scope, tag, tensor, options);
  }

  /**
   * Builds an {@link ScalarSummary} operation
   *
   * @param tags Tags for the summary.
   * @param values Same shape as `tags.  Values for the summary.
   * @return a new instance of ScalarSummary
   * @see org.tensorflow.op.summary.ScalarSummary
   */
  public <T extends Number> ScalarSummary scalarSummary(Operand<String> tags, Operand<T> values) {
    return ScalarSummary.create(scope, tags, values);
  }

  /**
   * Builds an {@link MergeSummary} operation
   *
   * @param inputs Can be of any shape.  Each must contain serialized `Summary` protocol
   * @return a new instance of MergeSummary
   * @see org.tensorflow.op.summary.MergeSummary
   */
  public MergeSummary mergeSummary(Iterable<Operand<String>> inputs) {
    return MergeSummary.create(scope, inputs);
  }

  /**
   * Builds an {@link HistogramSummary} operation
   *
   * @param tag Scalar.  Tag to use for the `Summary.Value`.
   * @param values Any shape. Values to use to build the histogram.
   * @return a new instance of HistogramSummary
   * @see org.tensorflow.op.summary.HistogramSummary
   */
  public <T extends Number> HistogramSummary histogramSummary(Operand<String> tag,
      Operand<T> values) {
    return HistogramSummary.create(scope, tag, values);
  }

  /**
   * Builds an {@link AudioSummary} operation
   *
   * @param tag Scalar. Used to build the `tag` attribute of the summary values.
   * @param tensor 2-D of shape `[batch_size, frames]`.
   * @param sampleRate The sample rate of the signal in hertz.
   * @param options carries optional attributes values
   * @return a new instance of AudioSummary
   * @see org.tensorflow.op.summary.AudioSummary
   */
  public AudioSummary audioSummary(Operand<String> tag, Operand<Float> tensor,
      Operand<Float> sampleRate, AudioSummary.Options... options) {
    return AudioSummary.create(scope, tag, tensor, sampleRate, options);
  }
}
