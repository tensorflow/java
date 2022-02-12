/*
 Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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

import org.tensorflow.ndarray.Shape
import org.tensorflow.ndarray.Shaped

/**
 * The (possibly partially known) shape of the tensor referred to by the {@link Output} of this
 * operand.
 * @see Operand.shape
 */
public val Operand<*>.shape: Shape
  get() = this.shape()

public fun interface ShapeErrorLazyMessage {
  public fun message(actual: Shape, required: Shape): String
}

@PublishedApi
internal val defaultShapeErrorMessage: ShapeErrorLazyMessage =
    ShapeErrorLazyMessage { actual, required ->
  "Shape $actual is not compatible with the required shape $required"
}

/**
 * Require the [Shaped] object have a certain shape.
 *
 * @throws AssertionError if the shapes are not compatible
 */
public inline fun <T : Shaped> T.assertShape(
    requiredShape: Shape,
    exception: ShapeErrorLazyMessage = defaultShapeErrorMessage
): T = apply {
  val actual = this.shape()
  assert(actual.isCompatibleWith(requiredShape)) { exception.message(actual, requiredShape) }
}

/**
 * Require the [Shaped] object have a certain shape.
 *
 * @throws AssertionError if the shapes are not compatible
 */
public inline fun <T : Shaped> T.assertShape(
    vararg shape: Long,
    exception: ShapeErrorLazyMessage = defaultShapeErrorMessage
): T = checkShape(Shape.of(*shape), exception)

/**
 * Require the [Shaped] object have a certain shape.
 *
 * @throws IllegalArgumentException if the shapes are not compatible
 */
public inline fun <T : Shaped> T.requireShape(
    requiredShape: Shape,
    exception: ShapeErrorLazyMessage = defaultShapeErrorMessage
): T = apply {
  val actual = this.shape()
  require(actual.isCompatibleWith(requiredShape)) { exception.message(actual, requiredShape) }
}

/**
 * Require the [Shaped] object have a certain shape.
 *
 * @throws IllegalArgumentException if the shapes are not compatible
 */
public inline fun <T : Shaped> T.requireShape(
    vararg shape: Long,
    exception: ShapeErrorLazyMessage = defaultShapeErrorMessage
): T = checkShape(Shape.of(*shape), exception)

/**
 * Require the [Shaped] object have a certain shape.
 *
 * @throws IllegalStateException if the shapes are not compatible
 */
public inline fun <T : Shaped> T.checkShape(
    requiredShape: Shape,
    exception: ShapeErrorLazyMessage = defaultShapeErrorMessage
): T = apply {
  val actual = this.shape()
  check(actual.isCompatibleWith(requiredShape)) { exception.message(actual, requiredShape) }
}

/**
 * Require the [Shaped] object have a certain shape.
 *
 * @throws IllegalStateException if the shapes are not compatible
 */
public inline fun <T : Shaped> T.checkShape(
    vararg shape: Long,
    exception: ShapeErrorLazyMessage = defaultShapeErrorMessage
): T = checkShape(Shape.of(*shape), exception)
