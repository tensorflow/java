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
package org.tensorflow

import org.tensorflow.op.Op
import org.tensorflow.proto.framework.RunOptions
import org.tensorflow.types.family.TType
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.reflect.KProperty

internal sealed class FetchSpec {
    data class OperationFetch(val operation: String, val index: Int?) : FetchSpec()
    data class OperandFetch(val operand: Operand<*>) : FetchSpec()
    data class OutputFetch(val output: Output<*>) : FetchSpec()

    companion object {
        operator fun invoke(operation: String) = OperationFetch(operation, null)
        operator fun invoke(operation: String, index: Int) = OperationFetch(operation, index)
        operator fun invoke(operand: Operand<*>) = OperandFetch(operand)
        operator fun invoke(output: Output<*>) = OutputFetch(output)
    }
}

public fun Session.kotlinRunner(options: RunOptions? = null): KotlinRunner = KotlinRunner(this, options)

public inline fun <R> Session.kotlinRunner(options: RunOptions? = null, block: KotlinRunner.() -> R): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return kotlinRunner(options).run(block)
}

public fun Session.kotlinRunner(
    feeds: Map<String, Tensor<*>>,
    fetches: List<String> = emptyList(),
    options: RunOptions? = null
): KotlinRunner = kotlinRunner(options).apply {
    feed(feeds)
    fetch(fetches)
}

@JvmName("kotlinRunnerOutput")
public fun Session.kotlinRunner(
    feeds: Map<Output<*>, Tensor<*>>,
    fetches: List<Output<*>> = emptyList(),
    options: RunOptions? = null
): KotlinRunner = kotlinRunner(options).apply {
    feed(feeds)
    fetch(fetches)
}

@JvmName("kotlinRunnerOperand")
public fun Session.kotlinRunner(
    feeds: Map<Operand<*>, Tensor<*>>,
    fetches: List<Operand<*>> = emptyList(),
    options: RunOptions? = null
): KotlinRunner = kotlinRunner(options).apply {
    feed(feeds)
    fetch(fetches)
}

public inline fun <R> Session.kotlinRunner(
    feeds: Map<String, Tensor<*>>,
    fetches: List<String> = emptyList(),
    options: RunOptions? = null,
    block: KotlinRunner.() -> R
): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return kotlinRunner(feeds, fetches, options).run(block)
}

@JvmName("kotlinRunnerOutput")
public inline fun <R> Session.kotlinRunner(
    feeds: Map<Output<*>, Tensor<*>>,
    fetches: List<Output<*>> = emptyList(),
    options: RunOptions? = null,
    block: KotlinRunner.() -> R
): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return kotlinRunner(feeds, fetches, options).run(block)
}

@JvmName("kotlinRunnerOperand")
public inline fun <R> Session.kotlinRunner(
    feeds: Map<Operand<*>, Tensor<*>>,
    fetches: List<Operand<*>> = emptyList(),
    options: RunOptions? = null,
    block: KotlinRunner.() -> R
): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return kotlinRunner(feeds, fetches, options).run(block)
}

// TODO return Map or KotlinRun?
public fun Session.run(
    feeds: Map<String, Tensor<*>>,
    fetches: List<String>,
    options: RunOptions? = null
): KotlinRunner.Run = kotlinRunner(feeds, fetches, options).run()

@JvmName("runOutput")
public fun Session.run(
    feeds: Map<Output<*>, Tensor<*>>,
    fetches: List<Output<*>>,
    options: RunOptions? = null
): KotlinRunner.Run = kotlinRunner(feeds, fetches, options).run()

@JvmName("runOperand")
public fun Session.run(
    feeds: Map<Operand<*>, Tensor<*>>,
    fetches: List<Operand<*>>,
    options: RunOptions? = null
): KotlinRunner.Run = kotlinRunner(feeds, fetches, options).run()

public class KotlinRunner internal constructor(private val session: Session, options: RunOptions?) {
    private val runner = session.runner().let {
        if (options != null)
            it.setOptions(options)
        else
            it
    }

    // feeding

    public fun feed(operation: String, t: Tensor<*>) {
        runner.feed(operation, t)
    }

    public fun feed(operation: String, index: Int, t: Tensor<*>) {
        runner.feed(operation, index, t)
    }

    public fun <T : TType> feed(operand: Operand<in T>, t: Tensor<in T>) {
        runner.feed(operand, t)
    }

    public fun <T : TType> feed(output: Output<in T>, t: Tensor<in T>) {
        runner.feed(output, t)
    }

    public fun feed(vararg operations: Pair<String, Tensor<*>>): Unit = operations.forEach { feed(it.first, it.second) }

    @JvmName("feedOperands")
    public fun feed(vararg operands: Pair<Operand<*>, Tensor<*>>): Unit = operands.forEach { feed(it.first, it.second) }

    @JvmName("feedOutputs")
    public fun feed(vararg operands: Pair<Output<*>, Tensor<*>>): Unit = operands.forEach { feed(it.first, it.second) }

    public fun feed(operations: Map<String, Tensor<*>>): Unit = operations.forEach { feed(it.key, it.value) }

    @JvmName("feedOperands")
    public fun feed(operands: Map<Operand<*>, Tensor<*>>): Unit = operands.forEach { feed(it.key, it.value) }

    @JvmName("feedOutputs")
    public fun feed(operands: Map<Output<*>, Tensor<*>>): Unit = operands.forEach { feed(it.key, it.value) }

    @JvmName("operandFeed")
    public fun <T : TType> Operand<T>.feed(t: Tensor<T>): Unit = feed(this, t)

    @JvmName("outputFeed")
    public fun <T : TType> Output<T>.feed(t: Tensor<T>): Unit = feed(this, t)

    public operator fun set(operation: String, t: Tensor<*>): Unit = feed(operation, t)

    public operator fun set(operation: String, index: Int, t: Tensor<*>): Unit = feed(operation, index, t)

    public operator fun <T : TType> set(operand: Operand<T>, t: Tensor<T>): Unit = feed(operand, t)

    public operator fun <T : TType> set(output: Output<T>, t: Tensor<T>): Unit = feed(output, t)

    // targeting

    public fun addTarget(operation: String) {
        runner.addTarget(operation)
    }

    public fun addTarget(operation: Operation) {
        runner.addTarget(operation)
    }

    public fun addTarget(op: Op) {
        runner.addTarget(op)
    }

    // fetching

    public inner class FetchKey<T : TType> internal constructor(public val index: Int)

    private var currentKey = 0
    private val fetchMap = mutableMapOf<FetchSpec, FetchKey<*>>()

    private fun <T : TType> newKey(spec: FetchSpec): FetchKey<T> {
        if (spec in fetchMap)
            return fetchMap[spec] as FetchKey<T>

        return FetchKey<T>(currentKey++).also { fetchMap[spec] = it }
    }

    public fun findKey(operation: String): FetchKey<*> =
        fetchMap[FetchSpec(operation)] ?: error("Operation $operation was not fetched")

    public fun findKey(operation: String, index: Int): FetchKey<*> =
        fetchMap[FetchSpec(operation, index)] ?: error("Index $index of Operation $operation was not fetched")

    public fun <T : TType> findKey(operand: Operand<T>): FetchKey<T> =
        fetchMap[FetchSpec(operand)] as? FetchKey<T>? ?: error("Operand $operand was not fetched")

    public fun <T : TType> findKey(output: Output<T>): FetchKey<T> =
        fetchMap[FetchSpec(output)] as? FetchKey<T>? ?: error("Output $output was not fetched")

    public fun fetch(operation: String): FetchKey<*> =
        newKey<TType>(FetchSpec(operation)).also { runner.fetch(operation) }

    public fun fetch(operation: String, index: Int): FetchKey<*> =
        newKey<TType>(FetchSpec(operation, index)).also { runner.fetch(operation, index) }

    public fun <T : TType> fetch(output: Output<T>): FetchKey<*> =
        newKey<TType>(FetchSpec(output)).also { runner.fetch(output) }

    public fun <T : TType> fetch(operand: Operand<T>): FetchKey<*> =
        newKey<TType>(FetchSpec(operand)).also { runner.fetch(operand) }

    public fun fetch(vararg operations: String): List<FetchKey<*>> = operations.map { fetch(it) }

    public fun fetch(vararg outputs: Output<*>): List<FetchKey<*>> = outputs.map { fetch(it) }

    public fun fetch(vararg operands: Operand<*>): List<FetchKey<*>> = operands.map { fetch(it) }

    @JvmName("fetchStrings")
    public fun fetch(operations: List<String>): List<FetchKey<*>> = operations.map { fetch(it) }

    @JvmName("fetchOutputs")
    public fun fetch(outputs: List<Output<*>>): List<FetchKey<*>> = outputs.map { fetch(it) }

    @JvmName("fetchOperands")
    public fun fetch(operands: List<Operand<*>>): List<FetchKey<*>> = operands.map { fetch(it) }

    // running

    public inner class Run internal constructor(public val output: List<Tensor<*>>) : AutoCloseable {
        public operator fun <T : TType> get(key: FetchKey<T>): Tensor<T> {
            if (key.index < 0 || key.index > output.lastIndex)
                error("Invalid key: key's index is ${key.index}, but there are only ${output.size} outputs.")
            return output[key.index] as Tensor<T>
        }

        public operator fun get(operation: String): Tensor<*> = this[findKey(operation)]
        public operator fun get(operation: String, index: Int): Tensor<*> = this[findKey(operation, index)]
        public operator fun <T : TType> get(output: Output<T>): Tensor<T> = this[findKey(output)]
        public operator fun <T : TType> get(operand: Operand<T>): Tensor<T> = this[findKey(operand)]

        @JvmName("keyGet")
        public fun <T : TType> FetchKey<T>.get(): Tensor<T> = this@Run[this]

        @JvmName("operandGet")
        public fun <T : TType> Operand<T>.get(): Tensor<T> = this@Run[this]

        @JvmName("outputGet")
        public fun <T : TType> Output<T>.get(): Tensor<T> = this@Run[this]

        public operator fun <T : TType> FetchKey<T>.getValue(thisRef: Any?, property: KProperty<*>): Tensor<T> =
            this.get()

        override fun close() {
            output.forEach { it.close() }
        }
    }

    private var latestRun: Run? = null

    public fun run(): Run = Run(runner.run()).also {
        latestRun = it
    }

    public fun <R> run(freeTensors: Boolean = true, block: Run.() -> R): R {
        contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
        return if (freeTensors) run().use(block) else run().run(block)
    }

    // TODO Unsure if the nicer API is worth the weird run call requirements
    public operator fun <T : TType> FetchKey<T>.getValue(thisRef: Any?, property: KProperty<*>): Tensor<T> =
        latestRun?.get(this) ?: error("Runner has not yet been ran, can not get fetched value.")
}
