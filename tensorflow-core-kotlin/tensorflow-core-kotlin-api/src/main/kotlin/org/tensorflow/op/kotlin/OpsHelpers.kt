package org.tensorflow.op.kotlin

import org.tensorflow.DataType
import org.tensorflow.ExecutionEnvironment
import org.tensorflow.ndarray.Shape
import org.tensorflow.op.JavaOps
import org.tensorflow.op.Op
import org.tensorflow.op.Ops
import org.tensorflow.op.core.Placeholder
import org.tensorflow.types.family.TType
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Get the kotlin KotlinOps class for this scope.
 */
public val JavaOps.kotlin: KotlinOps get() = KotlinOps(this)

public fun KotlinOps.withSubScope(childScopeName: String): KotlinOps = KotlinOps(java.withSubScope(childScopeName))

/**
 * Returns an API that builds operations with the provided name prefix.
 *
 * @see {@link Scope#withSubScope(String)}
 */
public inline fun <R> KotlinOps.withSubScope(childScopeName: String, block: KotlinOps.() -> R): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return withSubScope(childScopeName).run(block)
}

/**
 * Returns an API that uses the provided name for an op.
 *
 * @see {@link Scope#withName(String)}
 */
public fun KotlinOps.withName(opName: String): KotlinOps = java.withName(opName).kotlin

/**
 * Returns an API that adds operations to the graph with the provided control dependencies.
 *
 * @see {@link Scope#withControlDependencies(Iterable<Op<?>>)}
 */
public fun KotlinOps.withControlDependencies(controls: Iterable<Op>): KotlinOps = java.withControlDependencies(controls).kotlin

/**
 * Creates an API for building operations in the provided execution environment
 */
public val ExecutionEnvironment.tf: KotlinOps get() = JavaOps.create(this).kotlin

//TODO we could have tf that gets itself from ExecutionEnvironment.default().  I think this will be too error prone to be worth doing

//public fun <T: TType> Ops.placeholder(dtype: DataType<T>, vararg shape: Long): Placeholder<T> = placeholder(dtype, Shape.of(*shape))