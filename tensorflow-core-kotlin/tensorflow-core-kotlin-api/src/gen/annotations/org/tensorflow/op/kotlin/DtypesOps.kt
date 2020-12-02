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
import org.tensorflow.op.dtypes.AsString
import org.tensorflow.op.dtypes.Cast
import org.tensorflow.op.dtypes.Complex
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building {@code dtypes} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class DtypesOps(
	/**
	 * Get the parent {@link KotlinOps} object.
	 */
	public val ops: KotlinOps
) {
	public val java: org.tensorflow.op.DtypesOps = ops.java.dtypes

	/**
	 * Returns the current {@link Scope scope} of this API
	 */
	public val scope: Scope = ops.scope

	public fun <T : TType> asString(
		input: Operand<T>,
		precision: Long? = null,
		scientific: Boolean? = null,
		shortest: Boolean? = null,
		width: Long? = null,
		fill: String? = null
	): AsString = java.asString<T>(	
		input,
		*listOfNotNull(
			precision?.let{ org.tensorflow.op.dtypes.AsString.precision(it) },
			scientific?.let{ org.tensorflow.op.dtypes.AsString.scientific(it) },
			shortest?.let{ org.tensorflow.op.dtypes.AsString.shortest(it) },
			width?.let{ org.tensorflow.op.dtypes.AsString.width(it) },
			fill?.let{ org.tensorflow.op.dtypes.AsString.fill(it) }
		).toTypedArray()
		)

	public fun <U : TType, T : TType> cast(
		x: Operand<T>,
		DstT: DataType<U>,
		Truncate: Boolean? = null
	): Cast<U> = java.cast<U, T>(	
		x,
		DstT,
		*listOfNotNull(
			Truncate?.let{ org.tensorflow.op.dtypes.Cast.Truncate(it) }
		).toTypedArray()
		)

	public fun <U : TType, T : TNumber> complex(
		real: Operand<T>,
		imag: Operand<T>,
		Tout: DataType<U>
	): Complex<U> = java.complex<U, T>(	
		real,
		imag,
		Tout
		)
}
