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
 * An API for building {@code summary} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class SummaryOps(
    /**
     * Get the parent {@link KotlinOps} object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.SummaryOps = ops.java.summary

    /**
     * Returns the current {@link Scope scope} of this API
     */
    public val scope: Scope = ops.scope

    public fun audioSummary(
        tag: Operand<TString>,
        tensor: Operand<TFloat32>,
        sampleRate: Operand<TFloat32>,
        maxOutputs: Long? = null
    ): AudioSummary = java.audioSummary(
        tag,
        tensor,
        sampleRate,
        *listOfNotNull(
            maxOutputs?.let { org.tensorflow.op.summary.AudioSummary.maxOutputs(it) }
        ).toTypedArray()
    )

    public fun <T : TNumber> histogramSummary(tag: Operand<TString>, values: Operand<T>):
        HistogramSummary = java.histogramSummary<T>(
            tag,
            values
        )

    public fun <T : TNumber> imageSummary(
        tag: Operand<TString>,
        tensor: Operand<T>,
        maxImages: Long? = null,
        badColor: Tensor<*>? = null
    ): ImageSummary = java.imageSummary<T>(
        tag,
        tensor,
        *listOfNotNull(
            maxImages?.let { org.tensorflow.op.summary.ImageSummary.maxImages(it) },
            badColor?.let { org.tensorflow.op.summary.ImageSummary.badColor(it) }
        ).toTypedArray()
    )

    public fun mergeSummary(inputs: Iterable<Operand<TString>>): MergeSummary = java.mergeSummary(
        inputs
    )

    public fun <T : TNumber> scalarSummary(tags: Operand<TString>, values: Operand<T>):
        ScalarSummary = java.scalarSummary<T>(
            tags,
            values
        )

    public fun <T : TType> tensorSummary(
        tag: Operand<TString>,
        tensor: Operand<T>,
        serializedSummaryMetadata: Operand<TString>
    ): TensorSummary = java.tensorSummary<T>(
        tag,
        tensor,
        serializedSummaryMetadata
    )
}
