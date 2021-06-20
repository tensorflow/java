/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/
package org.tensorflow.op.kotlin

import org.tensorflow.op.WithOps
import org.tensorflow.op.core.Constant
import org.tensorflow.types.TBool
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TFloat64
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.TUint8

/**
 * Get the Kotlin ops builder.
 */
public val WithOps.tf: KotlinOps get() = if(this is KotlinOps) this else KotlinOps(tf())

/**
 * Get the Kotlin ops builder.
 */
public val KotlinOps.tf: KotlinOps get() = this

// TODO we could have tf that gets itself from ExecutionEnvironment.default().  I think this will be
// too error prone to be worth doing

/**
 * Creates a 1D constant from [array].
 *
 * @see KotlinOps.constant
 */
@JvmName("constantDoubles")
public fun KotlinOps.constant(array: Collection<Double>): Constant<TFloat64> =
    constant(array.toDoubleArray())

/** @see KotlinOps.constant */
@JvmName("constantFloats")
public fun KotlinOps.constant(array: Collection<Float>): Constant<TFloat32> =
    constant(array.toFloatArray())

/**
 * Creates a 1D constant from [array].
 *
 * @see KotlinOps.constant
 */
@JvmName("constantInts")
public fun KotlinOps.constant(array: Collection<Int>): Constant<TInt32> =
    constant(array.toIntArray())

/**
 * Creates a 1D constant from [array].
 *
 * @see KotlinOps.constant
 */
@JvmName("constantLongs")
public fun KotlinOps.constant(array: Collection<Long>): Constant<TInt64> =
    constant(array.toLongArray())

/**
 * Creates a 1D constant from [array].
 *
 * @see KotlinOps.constant
 */
@JvmName("constantBytes")
public fun KotlinOps.constant(array: Collection<Byte>): Constant<TUint8> =
    constant(array.toByteArray())

/**
 * Creates a 1D constant from [array].
 *
 * @see KotlinOps.constant
 */
@JvmName("constantBooleans")
public fun KotlinOps.constant(array: Collection<Boolean>): Constant<TBool> =
    constant(array.toBooleanArray())
