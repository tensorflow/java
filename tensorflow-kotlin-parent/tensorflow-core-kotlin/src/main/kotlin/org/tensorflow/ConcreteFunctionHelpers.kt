/*
  Copyright 2021 The TensorFlow Authors. All Rights Reserved.
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
     http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ==============================================================================
 */
package org.tensorflow

import org.tensorflow.op.kotlin.KotlinOps
import org.tensorflow.op.kotlin.kotlin
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Create a [ConcreteFunction] by building a new graph.
 * @see ConcreteFunction.create
 */
public inline fun ConcreteFunction(crossinline function: KotlinOps.() -> Signature): ConcreteFunction {
    contract { callsInPlace(function, InvocationKind.EXACTLY_ONCE) }
    return ConcreteFunction.create { function(it.kotlin) }
}

/**
 * Call this function with the specified arguments.
 * @see ConcreteFunction.call
 */
public operator fun ConcreteFunction.invoke(arguments: Map<String, Tensor>): Map<String, Tensor> = this.call(arguments)

/**
 * Call this function with the specified arguments.
 * @see ConcreteFunction.call
 */
public operator fun ConcreteFunction.invoke(vararg arguments: Pair<String, Tensor>): Map<String, Tensor> =
    this.invoke(arguments.toMap())

/**
 * Call this function with a single argument.  Requires this function to be a single argument function.
 * @see ConcreteFunction.call
 */
public operator fun ConcreteFunction.invoke(argument: Tensor): Tensor = this.call(argument)

/**
 * Create a [Signature] for a [ConcreteFunction].
 */
public fun Signature(
    methodName: String,
    inputs: Map<String, Operand<*>>,
    outputs: Map<String, Operand<*>>,
    key: String = Signature.DEFAULT_KEY,
): Signature =
    Signature.builder().methodName(methodName).key(key).inputs(inputs).outputs(outputs).build()

/**
 * Create a [Signature] for a [ConcreteFunction].
 */
public fun Signature(
    methodName: String,
    inputs: Operand<*>,
    outputs: Map<String, Operand<*>>,
    key: String = Signature.DEFAULT_KEY,
): Signature =
    Signature.builder().methodName(methodName).key(key).input("input", inputs).outputs(outputs).build()

/**
 * Create a [Signature] for a [ConcreteFunction].
 */
public fun Signature(
    methodName: String,
    inputs: Map<String, Operand<*>>,
    outputs: Operand<*>,
    key: String = Signature.DEFAULT_KEY,
): Signature =
    Signature.builder().methodName(methodName).key(key).inputs(inputs).output("output", outputs).build()

/**
 * Create a [Signature] for a [ConcreteFunction].
 */
public fun Signature(
    methodName: String,
    inputs: Operand<*>,
    outputs: Operand<*>,
    key: String = Signature.DEFAULT_KEY,
): Signature =
    Signature.builder().methodName(methodName).key(key).input("input", inputs).output("output", outputs).build()

/**
 * Add [inputs] to the signature.
 */
public fun Signature.Builder.inputs(inputs: Map<String, Operand<*>>): Signature.Builder = apply {
    inputs.forEach {
        input(it.key, it.value)
    }
}

/**
 * Add [outputs] to the signature.
 */
public fun Signature.Builder.outputs(outputs: Map<String, Operand<*>>): Signature.Builder = apply {
    outputs.forEach {
        output(it.key, it.value)
    }
}

/**
 * Add [inputs] to the signature.
 */
public fun Signature.Builder.inputs(vararg inputs: Pair<String, Operand<*>>): Signature.Builder = inputs(inputs.toMap())

/**
 * Add [outputs] to the signature.
 */
public fun Signature.Builder.outputs(vararg outputs: Pair<String, Operand<*>>): Signature.Builder =
    outputs(outputs.toMap())
