// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
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
import org.tensorflow.op.summary.AudioSummary;
import org.tensorflow.op.summary.CloseSummaryWriter;
import org.tensorflow.op.summary.CreateSummaryDbWriter;
import org.tensorflow.op.summary.CreateSummaryFileWriter;
import org.tensorflow.op.summary.FlushSummaryWriter;
import org.tensorflow.op.summary.HistogramSummary;
import org.tensorflow.op.summary.ImageSummary;
import org.tensorflow.op.summary.ImportEvent;
import org.tensorflow.op.summary.MergeSummary;
import org.tensorflow.op.summary.ScalarSummary;
import org.tensorflow.op.summary.StatsAggregatorSummary;
import org.tensorflow.op.summary.SummaryWriter;
import org.tensorflow.op.summary.TensorSummary;
import org.tensorflow.op.summary.WriteAudioSummary;
import org.tensorflow.op.summary.WriteGraphSummary;
import org.tensorflow.op.summary.WriteHistogramSummary;
import org.tensorflow.op.summary.WriteImageSummary;
import org.tensorflow.op.summary.WriteRawProtoSummary;
import org.tensorflow.op.summary.WriteScalarSummary;
import org.tensorflow.op.summary.WriteSummary;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code summary} operations as {@link Op Op}s
 *
 * @see Ops
 */
public final class SummaryOps {
  private final Scope scope;

  private final Ops ops;

  SummaryOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Outputs a {@code Summary} protocol buffer with audio.
   *  The summary has up to {@code max_outputs} summary values containing audio. The
   *  audio is built from {@code tensor} which must be 3-D with shape {@code [batch_size, frames, channels]} or 2-D with shape {@code [batch_size, frames]}. The values are
   *  assumed to be in the range of {@code [-1.0, 1.0]} with a sample rate of {@code sample_rate}.
   *  <p>The {@code tag} argument is a scalar {@code Tensor} of type {@code string}.  It is used to
   *  build the {@code tag} of the summary values:
   *  <ul>
   *  <li>If {@code max_outputs} is 1, the summary value tag is '<em>tag</em>/audio'.</li>
   *  <li>If {@code max_outputs} is greater than 1, the summary value tags are
   *  generated sequentially as '<em>tag</em>/audio/0', '<em>tag</em>/audio/1', etc.</li>
   *  </ul>
   *
   * @param tag Scalar. Used to build the {@code tag} attribute of the summary values.
   * @param tensor 2-D of shape {@code [batch_size, frames]}.
   * @param sampleRate The sample rate of the signal in hertz.
   * @param options carries optional attribute values
   * @return a new instance of AudioSummary
   */
  public AudioSummary audioSummary(Operand<TString> tag, Operand<TFloat32> tensor,
      Operand<TFloat32> sampleRate, AudioSummary.Options... options) {
    return AudioSummary.create(scope, tag, tensor, sampleRate, options);
  }

  /**
   * The CloseSummaryWriter operation
   *
   * @param writer The writer value
   * @return a new instance of CloseSummaryWriter
   */
  public CloseSummaryWriter closeSummaryWriter(Operand<? extends TType> writer) {
    return CloseSummaryWriter.create(scope, writer);
  }

  /**
   * The CreateSummaryDbWriter operation
   *
   * @param writer The writer value
   * @param dbUri The dbUri value
   * @param experimentName The experimentName value
   * @param runName The runName value
   * @param userName The userName value
   * @return a new instance of CreateSummaryDbWriter
   */
  public CreateSummaryDbWriter createSummaryDbWriter(Operand<? extends TType> writer,
      Operand<TString> dbUri, Operand<TString> experimentName, Operand<TString> runName,
      Operand<TString> userName) {
    return CreateSummaryDbWriter.create(scope, writer, dbUri, experimentName, runName, userName);
  }

  /**
   * The CreateSummaryFileWriter operation
   *
   * @param writer The writer value
   * @param logdir The logdir value
   * @param maxQueue The maxQueue value
   * @param flushMillis The flushMillis value
   * @param filenameSuffix The filenameSuffix value
   * @return a new instance of CreateSummaryFileWriter
   */
  public CreateSummaryFileWriter createSummaryFileWriter(Operand<? extends TType> writer,
      Operand<TString> logdir, Operand<TInt32> maxQueue, Operand<TInt32> flushMillis,
      Operand<TString> filenameSuffix) {
    return CreateSummaryFileWriter.create(scope, writer, logdir, maxQueue, flushMillis, filenameSuffix);
  }

  /**
   * The FlushSummaryWriter operation
   *
   * @param writer The writer value
   * @return a new instance of FlushSummaryWriter
   */
  public FlushSummaryWriter flushSummaryWriter(Operand<? extends TType> writer) {
    return FlushSummaryWriter.create(scope, writer);
  }

  /**
   * Outputs a {@code Summary} protocol buffer with a histogram.
   *  The generated
   *   <a href="https://www.tensorflow.org/code/tensorflow/core/framework/summary.proto">{@code Summary}</a> 
   *  has one summary value containing a histogram for {@code values}.
   *  <p>This op reports an {@code InvalidArgument} error if any value is not finite.
   *
   * @param tag Scalar.  Tag to use for the {@code Summary.Value}.
   * @param values Any shape. Values to use to build the histogram.
   * @return a new instance of HistogramSummary
   */
  public HistogramSummary histogramSummary(Operand<TString> tag,
      Operand<? extends TNumber> values) {
    return HistogramSummary.create(scope, tag, values);
  }

  /**
   * Outputs a {@code Summary} protocol buffer with images.
   *  The summary has up to {@code max_images} summary values containing images. The
   *  images are built from {@code tensor} which must be 4-D with shape {@code [batch_size, height, width, channels]} and where {@code channels} can be:
   *  <ul>
   *  <li>1: {@code tensor} is interpreted as Grayscale.</li>
   *  <li>3: {@code tensor} is interpreted as RGB.</li>
   *  <li>4: {@code tensor} is interpreted as RGBA.</li>
   *  </ul>
   *  <p>The images have the same number of channels as the input tensor. For float
   *  input, the values are normalized one image at a time to fit in the range
   *  {@code [0, 255]}.  {@code uint8} values are unchanged.  The op uses two different
   *  normalization algorithms:
   *  <ul>
   *  <li>
   *  <p>If the input values are all positive, they are rescaled so the largest one
   *  is 255.
   *  </li>
   *  <li>
   *  <p>If any input value is negative, the values are shifted so input value 0.0
   *  is at 127.  They are then rescaled so that either the smallest value is 0,
   *  or the largest one is 255.
   *  </li>
   *  </ul>
   *  <p>The {@code tag} argument is a scalar {@code Tensor} of type {@code string}.  It is used to
   *  build the {@code tag} of the summary values:
   *  <ul>
   *  <li>If {@code max_images} is 1, the summary value tag is '<em>tag</em>/image'.</li>
   *  <li>If {@code max_images} is greater than 1, the summary value tags are
   *  generated sequentially as '<em>tag</em>/image/0', '<em>tag</em>/image/1', etc.</li>
   *  </ul>
   *  <p>The {@code bad_color} argument is the color to use in the generated images for
   *  non-finite input values.  It is a {@code uint8} 1-D tensor of length {@code channels}.
   *  Each element must be in the range {@code [0, 255]} (It represents the value of a
   *  pixel in the output image).  Non-finite values in the input tensor are
   *  replaced by this tensor in the output image.  The default value is the color
   *  red.
   *
   * @param tag Scalar. Used to build the {@code tag} attribute of the summary values.
   * @param tensor 4-D of shape {@code [batch_size, height, width, channels]} where
   *  {@code channels} is 1, 3, or 4.
   * @param options carries optional attribute values
   * @return a new instance of ImageSummary
   */
  public ImageSummary imageSummary(Operand<TString> tag, Operand<? extends TNumber> tensor,
      ImageSummary.Options... options) {
    return ImageSummary.create(scope, tag, tensor, options);
  }

  /**
   * The ImportEvent operation
   *
   * @param writer The writer value
   * @param event The event value
   * @return a new instance of ImportEvent
   */
  public ImportEvent importEvent(Operand<? extends TType> writer, Operand<TString> event) {
    return ImportEvent.create(scope, writer, event);
  }

  /**
   * Merges summaries.
   *  This op creates a
   *   <a href="https://www.tensorflow.org/code/tensorflow/core/framework/summary.proto">{@code Summary}</a> 
   *  protocol buffer that contains the union of all the values in the input
   *  summaries.
   *  <p>When the Op is run, it reports an {@code InvalidArgument} error if multiple values
   *  in the summaries to merge use the same tag.
   *
   * @param inputs Can be of any shape.  Each must contain serialized {@code Summary} protocol
   *  buffers.
   * @return a new instance of MergeSummary
   */
  public MergeSummary mergeSummary(Iterable<Operand<TString>> inputs) {
    return MergeSummary.create(scope, inputs);
  }

  /**
   * Outputs a {@code Summary} protocol buffer with scalar values.
   *  The input {@code tags} and {@code values} must have the same shape.  The generated summary
   *  has a summary value for each tag-value pair in {@code tags} and {@code values}.
   *
   * @param tags Tags for the summary.
   * @param values Same shape as `tags.  Values for the summary.
   * @return a new instance of ScalarSummary
   */
  public ScalarSummary scalarSummary(Operand<TString> tags, Operand<? extends TNumber> values) {
    return ScalarSummary.create(scope, tags, values);
  }

  /**
   * Produces a summary of any statistics recorded by the given statistics manager.
   *
   * @param iterator The iterator value
   * @return a new instance of StatsAggregatorSummary
   */
  public StatsAggregatorSummary statsAggregatorSummary(Operand<? extends TType> iterator) {
    return StatsAggregatorSummary.create(scope, iterator);
  }

  /**
   * The SummaryWriter operation
   *
   * @param options carries optional attribute values
   * @return a new instance of SummaryWriter
   */
  public SummaryWriter summaryWriter(SummaryWriter.Options... options) {
    return SummaryWriter.create(scope, options);
  }

  /**
   * Outputs a {@code Summary} protocol buffer with a tensor and per-plugin data.
   *
   * @param tag A string attached to this summary. Used for organization in TensorBoard.
   * @param tensor A tensor to serialize.
   * @param serializedSummaryMetadata A serialized SummaryMetadata proto. Contains plugin
   *  data.
   * @return a new instance of TensorSummary
   */
  public TensorSummary tensorSummary(Operand<TString> tag, Operand<? extends TType> tensor,
      Operand<TString> serializedSummaryMetadata) {
    return TensorSummary.create(scope, tag, tensor, serializedSummaryMetadata);
  }

  /**
   * Writes an audio summary.
   *  Writes encoded audio summary {@code tensor} at {@code step} with {@code tag} using summary {@code writer}.
   *  {@code sample_rate} is the audio sample rate is Hz.
   *
   * @param writer The writer value
   * @param step The step value
   * @param tag The tag value
   * @param tensor The tensor value
   * @param sampleRate The sampleRate value
   * @param options carries optional attribute values
   * @return a new instance of WriteAudioSummary
   */
  public WriteAudioSummary writeAudioSummary(Operand<? extends TType> writer, Operand<TInt64> step,
      Operand<TString> tag, Operand<TFloat32> tensor, Operand<TFloat32> sampleRate,
      WriteAudioSummary.Options... options) {
    return WriteAudioSummary.create(scope, writer, step, tag, tensor, sampleRate, options);
  }

  /**
   * Writes a graph summary.
   *  Writes TensorFlow graph {@code tensor} at {@code step} using summary {@code writer}.
   *
   * @param writer The writer value
   * @param step The step value
   * @param tensor The tensor value
   * @return a new instance of WriteGraphSummary
   */
  public WriteGraphSummary writeGraphSummary(Operand<? extends TType> writer, Operand<TInt64> step,
      Operand<TString> tensor) {
    return WriteGraphSummary.create(scope, writer, step, tensor);
  }

  /**
   * Writes a histogram summary.
   *  Writes histogram {@code values} at {@code step} with {@code tag} using summary {@code writer}.
   *
   * @param writer The writer value
   * @param step The step value
   * @param tag The tag value
   * @param values The values value
   * @return a new instance of WriteHistogramSummary
   */
  public WriteHistogramSummary writeHistogramSummary(Operand<? extends TType> writer,
      Operand<TInt64> step, Operand<TString> tag, Operand<? extends TType> values) {
    return WriteHistogramSummary.create(scope, writer, step, tag, values);
  }

  /**
   * Writes an image summary.
   *  Writes image {@code tensor} at {@code step} with {@code tag} using summary {@code writer}.
   *  {@code tensor} is image with shape [height, width, channels].
   *
   * @param writer The writer value
   * @param step The step value
   * @param tag The tag value
   * @param tensor The tensor value
   * @param badColor The badColor value
   * @param options carries optional attribute values
   * @return a new instance of WriteImageSummary
   */
  public WriteImageSummary writeImageSummary(Operand<? extends TType> writer, Operand<TInt64> step,
      Operand<TString> tag, Operand<? extends TNumber> tensor, Operand<TUint8> badColor,
      WriteImageSummary.Options... options) {
    return WriteImageSummary.create(scope, writer, step, tag, tensor, badColor, options);
  }

  /**
   * Writes a serialized proto summary.
   *  Writes {@code tensor}, a serialized proto at {@code step} using summary {@code writer}.
   *
   * @param writer The writer value
   * @param step The step value
   * @param tensor The tensor value
   * @return a new instance of WriteRawProtoSummary
   */
  public WriteRawProtoSummary writeRawProtoSummary(Operand<? extends TType> writer,
      Operand<TInt64> step, Operand<TString> tensor) {
    return WriteRawProtoSummary.create(scope, writer, step, tensor);
  }

  /**
   * Writes a scalar summary.
   *  Writes scalar {@code value} at {@code step} with {@code tag} using summary {@code writer}.
   *
   * @param writer The writer value
   * @param step The step value
   * @param tag The tag value
   * @param value The value value
   * @return a new instance of WriteScalarSummary
   */
  public WriteScalarSummary writeScalarSummary(Operand<? extends TType> writer,
      Operand<TInt64> step, Operand<TString> tag, Operand<? extends TNumber> value) {
    return WriteScalarSummary.create(scope, writer, step, tag, value);
  }

  /**
   * Writes a tensor summary.
   *  Writes {@code tensor} at {@code step} with {@code tag} using summary {@code writer}.
   *
   * @param writer The writer value
   * @param step The step value
   * @param tensor The tensor value
   * @param tag The tag value
   * @param summaryMetadata The summaryMetadata value
   * @return a new instance of WriteSummary
   */
  public WriteSummary writeSummary(Operand<? extends TType> writer, Operand<TInt64> step,
      Operand<? extends TType> tensor, Operand<TString> tag, Operand<TString> summaryMetadata) {
    return WriteSummary.create(scope, writer, step, tensor, tag, summaryMetadata);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
