package org.tensorflow.ndarray

/**
 * Convert the [Shape] to a List.
 */
public fun Shape.toList(): List<Long> = asArray().toList()

/**
 * Get the size at [index].
 */
public operator fun Shape.get(index: Int): Long = this.size(index)