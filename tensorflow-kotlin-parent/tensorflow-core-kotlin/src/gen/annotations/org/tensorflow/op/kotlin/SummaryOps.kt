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

import org.tensorflow.Operand
import org.tensorflow.Tensor
import org.tensorflow.op.Scope
import org.tensorflow.op.summary.AudioSummary
import org.tensorflow.op.summary.HistogramSummary
import org.tensorflow.op.summary.ImageSummary
import org.tensorflow.op.summary.MergeSummary
import org.tensorflow.op.summary.ScalarSummary
import org.tensorflow.op.summary.TensorSummary
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TString
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building `summary` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class SummaryOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps,
) {
    public val java: org.tensorflow.op.SummaryOps = ops.java.summary

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Outputs a `Summary` protocol buffer with audio.
     *
     *  The summary has up to `max_outputs` summary values containing audio. The
     *  audio is built from `tensor` which must be 3-D with shape `&#91;batch_size,
     *  frames, channels]` or 2-D with shape `&#91;batch_size, frames]`. The values are
     *  assumed to be in the range of `&#91;-1.0, 1.0]` with a sample rate of `sample_rate`.
     *
     *  The `tag` argument is a scalar `Tensor` of type `string`.  It is used to
     *  build the `tag` of the summary values:
     *  <ul>
     *  <li>
     *  If `max_outputs` is 1, the summary value tag is '<i>tag</i>/audio'.
     *  </li>
     *  <li>
     *  If `max_outputs` is greater than 1, the summary value tags are
     *     generated sequentially as '<i>tag</i>/audio/0', '<i>tag</i>/audio/1', etc.
     *
     * @param tag Scalar. Used to build the `tag` attribute of the summary values.
     * @param tensor 2-D of shape `&#91;batch_size, frames]`.
     * @param sampleRate The sample rate of the signal in hertz.
     * @param options carries optional attributes values
     * @return a new instance of AudioSummary
     * @see org.tensorflow.op.SummaryOps.audioSummary
     * @param maxOutputs Max number of batch elements to generate audio for.
     */
    public fun audioSummary(
        tag: Operand<TString>,
        tensor: Operand<TFloat32>,
        sampleRate: Operand<TFloat32>,
        maxOutputs: Long? = null,
    ): AudioSummary = java.audioSummary(
        tag,
        tensor,
        sampleRate,
        *listOfNotNull(
            maxOutputs?.let { org.tensorflow.op.summary.AudioSummary.maxOutputs(it) }
        ).toTypedArray()
    )

    /**
     * Outputs a `Summary` protocol buffer with a histogram.
     *
     *  The generated
     *  &#91;`Summary`](https://www.tensorflow.org/code/tensorflow/core/framework/summary.proto)
     *  has one summary value containing a histogram for `values`.
     *
     *  This op reports an `InvalidArgument` error if any value is not finite.
     *
     * @param tag Scalar.  Tag to use for the `Summary.Value`.
     * @param values Any shape. Values to use to build the histogram.
     * @return a new instance of HistogramSummary
     * @see org.tensorflow.op.SummaryOps.histogramSummary
     */
    public fun histogramSummary(tag: Operand<TString>, values: Operand<out TNumber>):
        HistogramSummary = java.histogramSummary(
        tag,
        values
    )

    /**
     * Outputs a `Summary` protocol buffer with images.
     *
     *  The summary has up to `max_images` summary values containing images. The
     *  images are built from `tensor` which must be 4-D with shape `&#91;batch_size,
     *  height, width, channels]` and where `channels` can be:
     *  <ul>
     *  <li>
     *  1: `tensor` is interpreted as Grayscale.
     *  </li>
     *  <li>
     *  3: `tensor` is interpreted as RGB.
     *  </li>
     *  <li>
     *  4: `tensor` is interpreted as RGBA.
     *  </li>
     *  </ul>
     *  The images have the same number of channels as the input tensor. For float
     *  input, the values are normalized one image at a time to fit in the range
     *  `&#91;0, 255]`.  `uint8` values are unchanged.  The op uses two different
     *  normalization algorithms:
     *  <ul>
     *  <li>
     *  If the input values are all positive, they are rescaled so the largest one
     *     is 255.
     *  </li>
     *  <li>
     *  If any input value is negative, the values are shifted so input value 0.0
     *     is at 127.  They are then rescaled so that either the smallest value is 0,
     *     or the largest one is 255.
     *  </li>
     *  </ul>
     *  The `tag` argument is a scalar `Tensor` of type `string`.  It is used to
     *  build the `tag` of the summary values:
     *  <ul>
     *  <li>
     *  If `max_images` is 1, the summary value tag is '<i>tag</i>/image'.
     *  </li>
     *  <li>
     *  If `max_images` is greater than 1, the summary value tags are
     *     generated sequentially as '<i>tag</i>/image/0', '<i>tag</i>/image/1', etc.
     *  </li>
     *  </ul>
     *  The `bad_color` argument is the color to use in the generated images for
     *  non-finite input values.  It is a `uint8` 1-D tensor of length `channels`.
     *  Each element must be in the range `&#91;0, 255]` (It represents the value of a
     *  pixel in the output image).  Non-finite values in the input tensor are
     *  replaced by this tensor in the output image.  The default value is the color
     *  red.
     *
     * @param tag Scalar. Used to build the `tag` attribute of the summary values.
     * @param tensor 4-D of shape `&#91;batch_size, height, width, channels]` where
     *  `channels` is 1, 3, or 4.
     * @param options carries optional attributes values
     * @return a new instance of ImageSummary
     * @see org.tensorflow.op.SummaryOps.imageSummary
     * @param maxImages Max number of batch elements to generate images for.
     * @param badColor Color to use for pixels with non-finite values.
     */
    public fun imageSummary(
        tag: Operand<TString>,
        tensor: Operand<out TNumber>,
        maxImages: Long? = null,
        badColor: Tensor? = null,
    ): ImageSummary = java.imageSummary(
        tag,
        tensor,
        *listOfNotNull(
            maxImages?.let { org.tensorflow.op.summary.ImageSummary.maxImages(it) },
            badColor?.let { org.tensorflow.op.summary.ImageSummary.badColor(it) }
        ).toTypedArray()
    )

    /**
     * Merges summaries.
     *
     *  This op creates a
     *  &#91;`Summary`](https://www.tensorflow.org/code/tensorflow/core/framework/summary.proto)
     *  protocol buffer that contains the union of all the values in the input
     *  summaries.
     *
     *  When the Op is run, it reports an `InvalidArgument` error if multiple values
     *  in the summaries to merge use the same tag.
     *
     * @param inputs Can be of any shape.  Each must contain serialized `Summary` protocol
     *  buffers.
     * @return a new instance of MergeSummary
     * @see org.tensorflow.op.SummaryOps.mergeSummary
     */
    public fun mergeSummary(inputs: Iterable<Operand<TString>>): MergeSummary = java.mergeSummary(
        inputs
    )

    /**
     * Outputs a `Summary` protocol buffer with scalar values.
     *
     *  The input `tags` and `values` must have the same shape.  The generated summary
     *  has a summary value for each tag-value pair in `tags` and `values`.
     *
     * @param tags Tags for the summary.
     * @param values Same shape as `tags.  Values for the summary.
     * @return a new instance of ScalarSummary
     * @see org.tensorflow.op.SummaryOps.scalarSummary
     */
    public fun scalarSummary(tags: Operand<TString>, values: Operand<out TNumber>): ScalarSummary =
        java.scalarSummary(
            tags,
            values
        )

    /**
     * Outputs a `Summary` protocol buffer with a tensor and per-plugin data.
     *
     * @param tag A string attached to this summary. Used for organization in TensorBoard.
     * @param tensor A tensor to serialize.
     * @param serializedSummaryMetadata A serialized SummaryMetadata proto. Contains plugin
     *  data.
     * @return a new instance of TensorSummary
     * @see org.tensorflow.op.SummaryOps.tensorSummary
     */
    public fun tensorSummary(
        tag: Operand<TString>,
        tensor: Operand<out TType>,
        serializedSummaryMetadata: Operand<TString>,
    ): TensorSummary = java.tensorSummary(
        tag,
        tensor,
        serializedSummaryMetadata
    )
}
