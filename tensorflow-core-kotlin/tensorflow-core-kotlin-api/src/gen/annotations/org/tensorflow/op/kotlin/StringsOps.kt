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

import org.tensorflow.DataType
import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.strings.Join
import org.tensorflow.op.strings.Lower
import org.tensorflow.op.strings.ReduceJoin
import org.tensorflow.op.strings.RegexFullMatch
import org.tensorflow.op.strings.RegexReplace
import org.tensorflow.op.strings.StringFormat
import org.tensorflow.op.strings.StringLength
import org.tensorflow.op.strings.StringNGrams
import org.tensorflow.op.strings.StringSplit
import org.tensorflow.op.strings.Strip
import org.tensorflow.op.strings.Substr
import org.tensorflow.op.strings.ToHashBucket
import org.tensorflow.op.strings.ToHashBucketFast
import org.tensorflow.op.strings.ToHashBucketStrong
import org.tensorflow.op.strings.ToNumber
import org.tensorflow.op.strings.UnicodeScript
import org.tensorflow.op.strings.UnicodeTranscode
import org.tensorflow.op.strings.UnsortedSegmentJoin
import org.tensorflow.op.strings.Upper
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32
import org.tensorflow.types.TString
import org.tensorflow.types.family.TNumber

/**
 * An API for building {@code strings} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class StringsOps(
  /**
   * Get the parent {@link KotlinOps} object.
   */
  public val ops: KotlinOps
) {
  public val java: org.tensorflow.op.StringsOps = ops.java.strings

  /**
   * Returns the current {@link Scope scope} of this API
   */
  public val scope: Scope = ops.scope

  public fun join(inputs: Iterable<Operand<TString>>, vararg options: Join.Options): Join =
      java.join(inputs, *options)

  public fun lower(input: Operand<TString>, vararg options: Lower.Options): Lower =
      java.lower(input, *options)

  public fun reduceJoin(
    inputs: Operand<TString>,
    reductionIndices: Operand<TInt32>,
    vararg options: ReduceJoin.Options
  ): ReduceJoin = java.reduceJoin(inputs, reductionIndices, *options)

  public fun regexFullMatch(input: Operand<TString>, pattern: Operand<TString>): RegexFullMatch =
      java.regexFullMatch(input, pattern)

  public fun regexReplace(
    input: Operand<TString>,
    pattern: Operand<TString>,
    rewrite: Operand<TString>,
    vararg options: RegexReplace.Options
  ): RegexReplace = java.regexReplace(input, pattern, rewrite, *options)

  public fun stringFormat(inputs: Iterable<Operand<*>>, vararg options: StringFormat.Options):
      StringFormat = java.stringFormat(inputs, *options)

  public fun stringLength(input: Operand<TString>, vararg options: StringLength.Options):
      StringLength = java.stringLength(input, *options)

  public fun <T : TNumber> stringNGrams(
    `data`: Operand<TString>,
    dataSplits: Operand<T>,
    separator: String,
    ngramWidths: List<Long>,
    leftPad: String,
    rightPad: String,
    padWidth: Long,
    preserveShortSequences: Boolean
  ): StringNGrams<T> = java.stringNGrams<T>(data, dataSplits, separator, ngramWidths, leftPad,
      rightPad, padWidth, preserveShortSequences)

  public fun stringSplit(
    input: Operand<TString>,
    sep: Operand<TString>,
    vararg options: StringSplit.Options
  ): StringSplit = java.stringSplit(input, sep, *options)

  public fun strip(input: Operand<TString>): Strip = java.strip(input)

  public fun <T : TNumber> substr(
    input: Operand<TString>,
    pos: Operand<T>,
    len: Operand<T>,
    vararg options: Substr.Options
  ): Substr = java.substr<T>(input, pos, len, *options)

  public fun toHashBucket(stringTensor: Operand<TString>, numBuckets: Long): ToHashBucket =
      java.toHashBucket(stringTensor, numBuckets)

  public fun toHashBucketFast(input: Operand<TString>, numBuckets: Long): ToHashBucketFast =
      java.toHashBucketFast(input, numBuckets)

  public fun toHashBucketStrong(
    input: Operand<TString>,
    numBuckets: Long,
    key: List<Long>
  ): ToHashBucketStrong = java.toHashBucketStrong(input, numBuckets, key)

  public fun toNumber(stringTensor: Operand<TString>): ToNumber<TFloat32> =
      java.toNumber(stringTensor)

  public fun <T : TNumber> toNumber(stringTensor: Operand<TString>, outType: DataType<T>):
      ToNumber<T> = java.toNumber<T>(stringTensor, outType)

  public fun unicodeScript(input: Operand<TInt32>): UnicodeScript = java.unicodeScript(input)

  public fun unicodeTranscode(
    input: Operand<TString>,
    inputEncoding: String,
    outputEncoding: String,
    vararg options: UnicodeTranscode.Options
  ): UnicodeTranscode = java.unicodeTranscode(input, inputEncoding, outputEncoding, *options)

  public fun <T : TNumber, U : TNumber> unsortedSegmentJoin(
    inputs: Operand<TString>,
    segmentIds: Operand<T>,
    numSegments: Operand<U>,
    vararg options: UnsortedSegmentJoin.Options
  ): UnsortedSegmentJoin = java.unsortedSegmentJoin<T, U>(inputs, segmentIds, numSegments, *options)

  public fun upper(input: Operand<TString>, vararg options: Upper.Options): Upper =
      java.upper(input, *options)
}
