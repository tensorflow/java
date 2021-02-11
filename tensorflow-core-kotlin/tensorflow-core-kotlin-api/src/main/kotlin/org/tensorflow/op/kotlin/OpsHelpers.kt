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

import org.tensorflow.DeviceSpec
import org.tensorflow.ExecutionEnvironment
import org.tensorflow.op.JavaOps
import org.tensorflow.op.Op
import org.tensorflow.op.core.Constant
import org.tensorflow.types.TBool
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TFloat64
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.TUint8
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Get the kotlin KotlinOps class for this scope.
 */
public val JavaOps.kotlin: KotlinOps get() = KotlinOps(this)

/**
 * Returns a child [KotlinOps] builder that builds operations with the provided name prefix.
 *
 * @see org.tensorflow.op.Scope.withSubScope
 */
public fun KotlinOps.withSubScope(childScopeName: String): KotlinOps = KotlinOps(java.withSubScope(childScopeName))

/**
 * Runs [block] on a child [KotlinOps] builder that builds operations with the provided name prefix.
 *
 * @see org.tensorflow.op.Scope.withSubScope
 */
public inline fun <R> KotlinOps.withSubScope(childScopeName: String, block: KotlinOps.() -> R): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return withSubScope(childScopeName).run(block)
}

/**
 * Returns a child [KotlinOps] builder that uses the provided name for an op.
 *
 * @see org.tensorflow.op.Scope.withName
 */
public fun KotlinOps.withName(opName: String): KotlinOps = java.withName(opName).kotlin

/**
 * Returns a child [KotlinOps] builder that adds operations to the graph with the provided control dependencies.
 *
 * @see org.tensorflow.op.Scope.withControlDependencies
 */
public fun KotlinOps.withControlDependencies(controls: Iterable<Op>): KotlinOps =
    java.withControlDependencies(controls).kotlin

/**
 * Returns a child [KotlinOps] builder that adds operations to the graph with the provided control dependencies.
 *
 * @see org.tensorflow.op.Scope.withControlDependencies
 */
public fun KotlinOps.withControlDependencies(vararg controls: Op): KotlinOps =
    withControlDependencies(controls.toList())

/**
 * Runs [block] on a child [KotlinOps] builder that adds operations to the graph with the provided control dependencies.
 *
 * @see org.tensorflow.op.Scope.withControlDependencies
 */
public inline fun <R> KotlinOps.withControlDependencies(controls: Iterable<Op>, block: KotlinOps.() -> R): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return withControlDependencies(controls).run(block)
}

/**
 * Runs [block] on a child [KotlinOps] builder that adds operations to the graph with the provided control dependencies.
 *
 * @see org.tensorflow.op.Scope.withControlDependencies
 */
public inline fun <R> KotlinOps.withControlDependencies(vararg controls: Op, block: KotlinOps.() -> R): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return withControlDependencies(*controls).run(block)
}

/**
 * Returns a child [KotlinOps] builder that uses the provided device for created ops.
 *
 * @see org.tensorflow.op.Scope.withDevice
 */
public fun KotlinOps.withDevice(device: DeviceSpec): KotlinOps = java.withDevice(device).kotlin

/**
 * Runs [block] on a child [KotlinOps] builder that uses the provided device for created ops.
 *
 * @see org.tensorflow.op.Scope.withDevice
 */
public inline fun <R> KotlinOps.withDevice(device: DeviceSpec, block: KotlinOps.() -> R): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return withDevice(device).run(block)
}

/**
 * Returns a child [KotlinOps] builder, combining [withSubScope], [withControlDependencies], and [withDevice].
 * Null arguments are ignored.
 *
 * @see org.tensorflow.op.Scope.withSubScope
 * @see org.tensorflow.op.Scope.withControlDependencies
 * @see org.tensorflow.op.Scope.withDevice
 */
public fun KotlinOps.with(
    childScopeName: String? = null,
    controlDependencies: Iterable<Op>? = null,
    device: DeviceSpec? = null
): KotlinOps {
    var ops = this
    childScopeName?.let { ops = ops.withSubScope(it) }
    controlDependencies?.let { ops = ops.withControlDependencies(it) }
    device?.let { ops = ops.withDevice(it) }
    return ops
}

/**
 * Runs [block] on a child [KotlinOps] builder, combining [withSubScope], [withControlDependencies], and [withDevice].
 * Null arguments are ignored.
 *
 * @see org.tensorflow.op.Scope.withSubScope
 * @see org.tensorflow.op.Scope.withControlDependencies
 * @see org.tensorflow.op.Scope.withDevice
 */
public inline fun <R> KotlinOps.with(
    childScopeName: String? = null,
    controlDependencies: Iterable<Op>? = null,
    device: DeviceSpec? = null,
    block: KotlinOps.() -> R
): R {
    return with(childScopeName, controlDependencies, device).run(block)
}

/**
 * Creates a [KotlinOps] builder for building operations in the provided execution environment.
 */
public val ExecutionEnvironment.tf: KotlinOps get() = JavaOps.create(this).kotlin

/**
 * Creates a [KotlinOps] builder for building operations in the provided execution environment with the provided device.
 */
public fun ExecutionEnvironment.tf(device: DeviceSpec): KotlinOps = tf.withDevice(device)

// TODO we could have tf that gets itself from ExecutionEnvironment.default().  I think this will be too error prone to be worth doing

/**
 * Creates a 1D constant from [array].
 *
 * @see KotlinOps.constant
 */
@JvmName("constantDoubles")
public fun KotlinOps.constant(array: Collection<Double>): Constant<TFloat64> = constant(array.toDoubleArray())

/**
 * @see KotlinOps.constant
 */
@JvmName("constantFloats")
public fun KotlinOps.constant(array: Collection<Float>): Constant<TFloat32> = constant(array.toFloatArray())

/**
 * Creates a 1D constant from [array].
 *
 * @see KotlinOps.constant
 */
@JvmName("constantInts")
public fun KotlinOps.constant(array: Collection<Int>): Constant<TInt32> = constant(array.toIntArray())

/**
 * Creates a 1D constant from [array].
 *
 * @see KotlinOps.constant
 */
@JvmName("constantLongs")
public fun KotlinOps.constant(array: Collection<Long>): Constant<TInt64> = constant(array.toLongArray())

/**
 * Creates a 1D constant from [array].
 *
 * @see KotlinOps.constant
 */
@JvmName("constantBytes")
public fun KotlinOps.constant(array: Collection<Byte>): Constant<TUint8> = constant(array.toByteArray())

/**
 * Creates a 1D constant from [array].
 *
 * @see KotlinOps.constant
 */
@JvmName("constantBooleans")
public fun KotlinOps.constant(array: Collection<Boolean>): Constant<TBool> = constant(array.toBooleanArray())
