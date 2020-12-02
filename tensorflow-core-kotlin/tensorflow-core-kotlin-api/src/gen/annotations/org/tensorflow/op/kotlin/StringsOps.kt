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

	public fun join(inputs: Iterable<Operand<TString>>, separator: String? = null): Join = java.join(	
		inputs,
		*listOfNotNull(
			separator?.let{ org.tensorflow.op.strings.Join.separator(it) }
		).toTypedArray()
		)

	public fun lower(input: Operand<TString>, encoding: String? = null): Lower = java.lower(	
		input,
		*listOfNotNull(
			encoding?.let{ org.tensorflow.op.strings.Lower.encoding(it) }
		).toTypedArray()
		)

	public fun reduceJoin(
		inputs: Operand<TString>,
		reductionIndices: Operand<TInt32>,
		keepDims: Boolean? = null,
		separator: String? = null
	): ReduceJoin = java.reduceJoin(	
		inputs,
		reductionIndices,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.strings.ReduceJoin.keepDims(it) },
			separator?.let{ org.tensorflow.op.strings.ReduceJoin.separator(it) }
		).toTypedArray()
		)

	public fun regexFullMatch(input: Operand<TString>, pattern: Operand<TString>): RegexFullMatch =
			java.regexFullMatch(	
		input,
		pattern
		)

	public fun regexReplace(
		input: Operand<TString>,
		pattern: Operand<TString>,
		rewrite: Operand<TString>,
		replaceGlobal: Boolean? = null
	): RegexReplace = java.regexReplace(	
		input,
		pattern,
		rewrite,
		*listOfNotNull(
			replaceGlobal?.let{ org.tensorflow.op.strings.RegexReplace.replaceGlobal(it) }
		).toTypedArray()
		)

	public fun stringFormat(
		inputs: Iterable<Operand<*>>,
		template: String? = null,
		placeholder: String? = null,
		summarize: Long? = null
	): StringFormat = java.stringFormat(	
		inputs,
		*listOfNotNull(
			template?.let{ org.tensorflow.op.strings.StringFormat.template(it) },
			placeholder?.let{ org.tensorflow.op.strings.StringFormat.placeholder(it) },
			summarize?.let{ org.tensorflow.op.strings.StringFormat.summarize(it) }
		).toTypedArray()
		)

	public fun stringLength(input: Operand<TString>, unit: String? = null): StringLength =
			java.stringLength(	
		input,
		*listOfNotNull(
			unit?.let{ org.tensorflow.op.strings.StringLength.unit(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> stringNGrams(
		`data`: Operand<TString>,
		dataSplits: Operand<T>,
		separator: String,
		ngramWidths: List<Long>,
		leftPad: String,
		rightPad: String,
		padWidth: Long,
		preserveShortSequences: Boolean
	): StringNGrams<T> = java.stringNGrams<T>(	
		data,
		dataSplits,
		separator,
		ngramWidths,
		leftPad,
		rightPad,
		padWidth,
		preserveShortSequences
		)

	public fun stringSplit(
		input: Operand<TString>,
		sep: Operand<TString>,
		maxsplit: Long? = null
	): StringSplit = java.stringSplit(	
		input,
		sep,
		*listOfNotNull(
			maxsplit?.let{ org.tensorflow.op.strings.StringSplit.maxsplit(it) }
		).toTypedArray()
		)

	public fun strip(input: Operand<TString>): Strip = java.strip(	
		input
		)

	public fun <T : TNumber> substr(
		input: Operand<TString>,
		pos: Operand<T>,
		len: Operand<T>,
		unit: String? = null
	): Substr = java.substr<T>(	
		input,
		pos,
		len,
		*listOfNotNull(
			unit?.let{ org.tensorflow.op.strings.Substr.unit(it) }
		).toTypedArray()
		)

	public fun toHashBucket(stringTensor: Operand<TString>, numBuckets: Long): ToHashBucket =
			java.toHashBucket(	
		stringTensor,
		numBuckets
		)

	public fun toHashBucketFast(input: Operand<TString>, numBuckets: Long): ToHashBucketFast =
			java.toHashBucketFast(	
		input,
		numBuckets
		)

	public fun toHashBucketStrong(
		input: Operand<TString>,
		numBuckets: Long,
		key: List<Long>
	): ToHashBucketStrong = java.toHashBucketStrong(	
		input,
		numBuckets,
		key
		)

	public fun toNumber(stringTensor: Operand<TString>): ToNumber<TFloat32> = java.toNumber(	
		stringTensor
		)

	public fun <T : TNumber> toNumber(stringTensor: Operand<TString>, outType: DataType<T>):
			ToNumber<T> = java.toNumber<T>(	
		stringTensor,
		outType
		)

	public fun unicodeScript(input: Operand<TInt32>): UnicodeScript = java.unicodeScript(	
		input
		)

	public fun unicodeTranscode(
		input: Operand<TString>,
		inputEncoding: String,
		outputEncoding: String,
		errors: String? = null,
		replacementChar: Long? = null,
		replaceControlCharacters: Boolean? = null
	): UnicodeTranscode = java.unicodeTranscode(	
		input,
		inputEncoding,
		outputEncoding,
		*listOfNotNull(
			errors?.let{ org.tensorflow.op.strings.UnicodeTranscode.errors(it) },
			replacementChar?.let{ org.tensorflow.op.strings.UnicodeTranscode.replacementChar(it) },
			replaceControlCharacters?.let{
			org.tensorflow.op.strings.UnicodeTranscode.replaceControlCharacters(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TNumber> unsortedSegmentJoin(
		inputs: Operand<TString>,
		segmentIds: Operand<T>,
		numSegments: Operand<U>,
		separator: String? = null
	): UnsortedSegmentJoin = java.unsortedSegmentJoin<T, U>(	
		inputs,
		segmentIds,
		numSegments,
		*listOfNotNull(
			separator?.let{ org.tensorflow.op.strings.UnsortedSegmentJoin.separator(it) }
		).toTypedArray()
		)

	public fun upper(input: Operand<TString>, encoding: String? = null): Upper = java.upper(	
		input,
		*listOfNotNull(
			encoding?.let{ org.tensorflow.op.strings.Upper.encoding(it) }
		).toTypedArray()
		)
}
