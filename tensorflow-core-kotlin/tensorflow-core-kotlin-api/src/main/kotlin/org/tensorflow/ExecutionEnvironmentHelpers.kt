package org.tensorflow

import org.tensorflow.EagerSession.DevicePlacementPolicy
import org.tensorflow.proto.framework.ConfigProto
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Construct a TensorFlow [Graph] and run [block] on it.
 */
public inline fun <R> Graph(block: Graph.() -> R): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Graph().use{
        it.run(block)
    }
}


/**
 * Construct a new session with the associated {@link Graph} and configuration options, and run [block] on it.
 *
 * @param g The {@link Graph} the created Session will operate on.
 * @param config Configuration parameters for the session specified as a [ConfigProto](https://www.tensorflow.org/code/tensorflow/core/protobuf/config.proto)
 *     protocol buffer.
 * @throws IllegalArgumentException if the config is not a valid serialization of the ConfigProto
 *     protocol buffer.
 */
public inline fun <R> Graph.withSession(config: ConfigProto? = null, block: (Session) -> R): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return Session(this, config).use(block)
}


/**
 * An environment for executing TensorFlow operations eagerly.
 *
 * Eager execution is an imperative programming environment that evaluates operations
 * immediately, without building graphs. Operations return concrete values instead of constructing a
 * computational graph to run later, as with {@link Graph}s and {@link Session}s.
 *
 * This makes it easy to develop with TensorFlow and debug models, as it behaves more like a
 * standard programming library.
 *
 * Instances of a {@code EagerSession} are thread-safe.
 *
 * @param options The options for this session.
 * @see EagerSession.Options
 */
public inline fun <R> EagerSession(
    options: EagerSession.Options? = null,
    block: EagerSession.() -> R
): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }

    val ses = options?.build() ?: EagerSession.create()
    return ses.use(block)
}

/**
 * An environment for executing TensorFlow operations eagerly.
 *
 * Eager execution is an imperative programming environment that evaluates operations
 * immediately, without building graphs. Operations return concrete values instead of constructing a
 * computational graph to run later, as with {@link Graph}s and {@link Session}s.
 *
 * This makes it easy to develop with TensorFlow and debug models, as it behaves more like a
 * standard programming library.
 *
 * Instances of a {@code EagerSession} are thread-safe.
 *
 * @param config The session configuration to use.  See [EagerSession.Options.config] and [ConfigProto].
 * @param async Whether to return from op methods before the outputs have been calculated.  See [EagerSession.Options.async].
 * @param devicePlacementPolicy How to handle tensors on different devices.  See [EagerSession.Options.devicePlacementPolicy].
 * @see EagerSession.Options
 */
public inline fun <R> EagerSession(
    config: ConfigProto? = null,
    async: Boolean = false,
    devicePlacementPolicy: DevicePlacementPolicy = DevicePlacementPolicy.SILENT,
    block: EagerSession.() -> R
): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }

    val options = EagerSession.options()
        .config(config)
        .async(async)
        .devicePlacementPolicy(devicePlacementPolicy)

    return EagerSession(options, block)
}

/**
 * Executed [block] in the default eager session, creating it if necessary.
 *
 * To configure the default session, use [EagerSession.initDefault].
 */
public fun <R> withDefaultEagerSession(block: EagerSession.() -> R): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return EagerSession.getDefault().use(block)
}