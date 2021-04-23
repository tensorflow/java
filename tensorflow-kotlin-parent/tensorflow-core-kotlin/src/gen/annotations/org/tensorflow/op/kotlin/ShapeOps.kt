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

import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.core.Shape
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building `shape` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class ShapeOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.ShapeOps = ops.java.shape

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Creates a 1-dimensional operand containing the dimensions of a shape followed by the last
     *  dimension.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param lastDimension the dimension(s) to append
     * @return a 1-dimensional operand containing the dimensions of a shape followed by the last
     *      dimension
     * @see org.tensorflow.op.ShapeOps.append
     */
    public fun append(shape: Shape<TInt64>, lastDimension: Long): Operand<TInt64> = java.append(
        shape,
        lastDimension
    )

    /**
     * Creates a 1-dimensional operand containing the dimensions of a shape followed by the last
     *  dimension.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param lastDimension the dimension(s) to append
     * @return a 1-dimensional operand containing the dimensions of a shape followed by the last
     *      dimension
     * @see org.tensorflow.op.ShapeOps.append
     */
    public fun append(shape: Shape<TInt32>, lastDimension: Int): Operand<TInt32> = java.append(
        shape,
        lastDimension
    )

    /**
     * Creates a 1-dimensional operand that represents a new shape containing the dimensions of the
     *  operand representing a shape, followed by the dimensions of an operand representing a shape
     * to
     *  append.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param shapeToAppend the other shape to append
     * @return a 1-dimensional operand that represents a new shape containing the dimensions of the
     *      operand representing a shape, followed by the dimensions of an operand representing a
     * shape
     *      to append
     * @see org.tensorflow.op.ShapeOps.append
     */
    public fun <T : TNumber> append(shape: Operand<T>, shapeToAppend: Operand<T>): Operand<T> =
        java.append<T>(
            shape,
            shapeToAppend
        )

    /**
     * Flatten the operand to 1 dimension.
     *
     * @param T the type of operand
     * @param scope current scope
     * @param operand the operand to flatten
     * @return the reshaped operand
     * @see org.tensorflow.op.ShapeOps.flatten
     */
    public fun <T : TType> flatten(operand: Operand<T>): Operand<T> = java.flatten<T>(
        operand
    )

    /**
     * Flatten the shape to 1 dimension.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @return the flattened shape
     * @see org.tensorflow.op.ShapeOps.flatten
     */
    public fun flatten(shape: Shape<TInt32>): Operand<TInt32> = java.flatten(
        shape
    )

    /**
     * Flatten the operand to 1 dimension
     *
     * @param T the type of operand
     * @param U the shape datatype
     * @param scope current scope
     * @param operand the operand to flatten
     * @param type the shape datatype
     * @return the reshaped operand
     * @see org.tensorflow.op.ShapeOps.flatten
     */
    public fun <T : TType, U : TNumber> flatten(operand: Operand<T>, type: Class<U>): Operand<T> =
        java.flatten<T, U>(
            operand,
            type
        )

    /**
     * Flatten the shape to 1 dimension.
     *
     * @param U the shape datatype
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param type the shape datatype
     * @return the flattened shape
     * @see org.tensorflow.op.ShapeOps.flatten
     */
    public fun <U : TNumber> flatten(shape: Shape<U>, type: Class<U>): Operand<U> =
        java.flatten<U>(
            shape,
            type
        )

    /**
     * Creates a 1-dimensional Operand containing the Shape's first dimension.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @return a 1-dimensional Operand containing the Shape's first dimension
     * @see org.tensorflow.op.ShapeOps.head
     */
    public fun head(shape: Shape<TInt32>): Operand<TInt32> = java.head(
        shape
    )

    /**
     * Creates a 1-dimensional Operand containing the Shape's first dimension.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param type the shape datatype.
     * @param U the shape datatype.
     * @return a 1-dimensional Operand containing the Shape's first dimension
     * @see org.tensorflow.op.ShapeOps.head
     */
    public fun <U : TNumber> head(shape: Shape<U>, type: Class<U>): Operand<U> = java.head<U>(
        shape,
        type
    )

    /**
     * Get the number of dimensions of the shape object.
     *
     * @param scope current scope
     * @param shape the shape
     * @return the number of dimensions
     * @see org.tensorflow.op.ShapeOps.numDimensions
     */
    public fun numDimensions(shape: Shape<TInt32>): Operand<TInt32> = java.numDimensions(
        shape
    )

    /**
     * Get the number of dimensions of the shape object.
     *
     * @param U the shape datatype
     * @param scope the curren scope
     * @param shape the shape
     * @param type the shape datatype
     * @return the number of dimensions
     * @see org.tensorflow.op.ShapeOps.numDimensions
     */
    public fun <U : TNumber> numDimensions(shape: Shape<U>, type: Class<U>): Operand<U> =
        java.numDimensions<U>(
            shape,
            type
        )

    /**
     * Creates a 1-dimensional operand containing the first dimension followed by the dimensions of
     *  the shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param firstDimension the dimension to prepend
     * @return a 1-dimensional operand containing the first dimension followed by the dimensions of
     *      the shape
     * @see org.tensorflow.op.ShapeOps.prepend
     */
    public fun prepend(shape: Shape<TInt64>, firstDimension: Long): Operand<TInt64> = java.prepend(
        shape,
        firstDimension
    )

    /**
     * Creates a 1-dimensional operand containing the first dimension followed by the dimensions of
     *  the shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param firstDimension the dimension to prepend
     * @return a 1-dimensional operand containing the first dimension followed by the dimensions of
     *      the shape
     * @see org.tensorflow.op.ShapeOps.prepend
     */
    public fun prepend(shape: Shape<TInt32>, firstDimension: Int): Operand<TInt32> = java.prepend(
        shape,
        firstDimension
    )

    /**
     * Creates a 1-dimensional operand that represents a new shape containing the dimensions of an
     *  operand representing the shape to prepend, followed by the dimensions of an operand
     *  representing a shape.
     *
     * @param scope current scope
     * @param shape an operand containing the dimensions of a shape
     * @param shapeToPrepend an operand containing the dimensions of the shape to prepend
     * @return a 1-dimensional operand that represents a new shape containing the dimensions of an
     *      operand representing the shape to prepend, followed by the dimensions of an operand
     *      representing the shape
     * @see org.tensorflow.op.ShapeOps.prepend
     */
    public fun <T : TNumber> prepend(shape: Operand<T>, shapeToPrepend: Operand<T>): Operand<T> =
        java.prepend<T>(
            shape,
            shapeToPrepend
        )

    /**
     * Reshapes the operand by reducing the shape to the specified axis.
     *
     * @param T the type of Operand
     * @param scope current scope
     * @param operand the operand
     * @param axis the axis
     * @return the reshaped operand
     * @see org.tensorflow.op.ShapeOps.reduceDims
     */
    public fun <T : TType> reduceDims(operand: Operand<T>, axis: Operand<TInt32>): Operand<T> =
        java.reduceDims<T>(
            operand,
            axis
        )

    /**
     * Reduces the shape to the specified axis.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param axis the axis
     * @return an operand containing the dimensions for the reduced shape
     * @see org.tensorflow.op.ShapeOps.reduceDims
     */
    public fun reduceDims(shape: Shape<TInt32>, axis: Operand<TInt32>): Operand<TInt32> =
        java.reduceDims(
            shape,
            axis
        )

    /**
     * Reshapes the operand by reducing the shape to the specified axis.
     *
     * @param T the type of Operand
     * @param U the shape datatype
     * @param scope current scope
     * @param operand the operand
     * @param axis the axis
     * @param type the shape datatype
     * @return the reshaped operand
     * @see org.tensorflow.op.ShapeOps.reduceDims
     */
    public fun <T : TType, U : TNumber> reduceDims(
        operand: Operand<T>,
        axis: Operand<U>,
        type: Class<U>
    ): Operand<T> = java.reduceDims<T, U>(
        operand,
        axis,
        type
    )

    /**
     * Reduces the shape to the specified axis.
     *
     * @param U the shape datatype
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param axis the axis
     * @param type the shape datatype
     * @return the reduced shape
     * @see org.tensorflow.op.ShapeOps.reduceDims
     */
    public fun <U : TNumber> reduceDims(
        shape: Shape<U>,
        axis: Operand<U>,
        type: Class<U>
    ): Operand<U> = java.reduceDims<U>(
        shape,
        axis,
        type
    )

    /**
     * Get the size represented by the TensorFlow shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @return the size
     * @see org.tensorflow.op.ShapeOps.size
     */
    public fun size(shape: Shape<TInt32>): Operand<TInt32> = java.size(
        shape
    )

    /**
     * Get the size of the specified dimension for the shape of the tensor.
     *
     * @param scope current scope
     * @param input the operand
     * @param dim the dimension
     * @return the size of the specified dimension
     * @see org.tensorflow.op.ShapeOps.size
     */
    public fun <T : TType> size(input: Operand<T>, dim: Operand<TInt32>): Operand<TInt32> =
        java.size<T>(
            input,
            dim
        )

    /**
     * Get the size of the specified dimension in the shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param dim the dimension
     * @return the size of the specified dimension
     * @see org.tensorflow.op.ShapeOps.size
     */
    public fun size(shape: Shape<TInt32>, dim: Operand<TInt32>): Operand<TInt32> = java.size(
        shape,
        dim
    )

    /**
     * Get the size represented by the TensorFlow shape.
     *
     * @param U the type of the shape
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param type the shape datatype
     * @return the size
     * @see org.tensorflow.op.ShapeOps.size
     */
    public fun <U : TNumber> size(shape: Shape<U>, type: Class<U>): Operand<U> = java.size<U>(
        shape,
        type
    )

    /**
     * Get the size of the specified dimension for the shape of the tensor.
     *
     * @param U the shape datatype
     * @param scope current scope
     * @param input the operand
     * @param dim the dimension
     * @param type the shape datatype
     * @return the size of the specified dimension
     * @see org.tensorflow.op.ShapeOps.size
     */
    public fun <T : TType, U : TNumber> size(
        input: Operand<T>,
        dim: Operand<U>,
        type: Class<U>
    ): Operand<U> = java.size<T, U>(
        input,
        dim,
        type
    )

    /**
     * Get the size of the specified dimension in the shape.
     *
     * @param U the shape datatype
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param dim the dimension
     * @param type the shape datatype
     * @return the size of the specified dimension
     * @see org.tensorflow.op.ShapeOps.size
     */
    public fun <U : TNumber> size(
        shape: Shape<U>,
        dim: Operand<U>,
        type: Class<U>
    ): Operand<U> = java.size<U>(
        shape,
        dim,
        type
    )

    /**
     * Removes dimensions of size 1 from the shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @return the squeezed shape
     * @see org.tensorflow.op.ShapeOps.squeeze
     */
    public fun squeeze(shape: Shape<TInt32>): Operand<TInt32> = java.squeeze(
        shape
    )

    /**
     * Removes dimensions of size 1 from the shape.
     *
     * @param U the shape datatype.
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param type the shape datatype.
     * @return the squeezed shape
     * @see org.tensorflow.op.ShapeOps.squeeze
     */
    public fun <U : TNumber> squeeze(shape: Shape<U>, type: Class<U>): Operand<U> =
        java.squeeze<U>(
            shape,
            type
        )

    /**
     * Creates a 1-dimensional Operand that contains the dimension matching the last dimension of
     * the
     *  Shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @return a 1-dimensional Operand that contains the dimension matching the last dimension of
     * the
     *      Shape
     * @see org.tensorflow.op.ShapeOps.tail
     */
    public fun tail(shape: Shape<TInt32>): Operand<TInt32> = java.tail(
        shape
    )

    /**
     * Creates a 1-dimensional Operand that contains the dimension matching the last dimension of *
     *  the Shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param type the shape datatype.
     * @param U the shape datatype.
     * @return a 1-dimensional Operand that contains the dimension matching the last dimension of
     * the
     *      Shape
     * @see org.tensorflow.op.ShapeOps.tail
     */
    public fun <U : TNumber> tail(shape: Shape<U>, type: Class<U>): Operand<U> = java.tail<U>(
        shape,
        type
    )

    /**
     * Creates a 1-dimensional operand with the dimensions matching the first n dimensions of the
     *  shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param n the number of leading dimensions to get, must be <= than the shape's
     * numDimensions()
     * @return a 1-dimensional operand with the dimensions matching the first n dimensions of the
     *      shape
     * @see org.tensorflow.op.ShapeOps.take
     */
    public fun take(shape: Shape<TInt32>, n: Operand<TInt32>): Operand<TInt32> = java.take(
        shape,
        n
    )

    /**
     * Creates a 1-dimensional operand containin the dimensions matching the first n dimensions of
     * the
     *  shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param n the number of leading dimensions to get, must be <= than the shape's
     * numDimensions()
     * @param type the shape datatype.
     * @param U the shape datatype.
     * @return a 1-dimensional operand with the dimensions matching * the first n dimensions of the
     *      shape
     * @see org.tensorflow.op.ShapeOps.take
     */
    public fun <U : TNumber> take(
        shape: Shape<U>,
        n: Operand<U>,
        type: Class<U>
    ): Operand<U> = java.take<U>(
        shape,
        n,
        type
    )

    /**
     * Creates a 1-dimensional operand containing the dimensions matching the last n dimensions of
     * the
     *  shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param n the number of leading dimensions to get, must be <= than the shape's
     * numDimensions()
     * @return a 1-dimensional operand containing the dimensions matching the last n dimensions of
     * the
     *      shape
     * @see org.tensorflow.op.ShapeOps.takeLast
     */
    public fun <U : TNumber> takeLast(shape: Shape<TInt32>, n: Operand<TInt32>): Operand<TInt32> =
        java.takeLast<U>(
            shape,
            n
        )

    /**
     * Creates a 1-dimensional operand containing the dimensions matching the last n dimensions of
     * the
     *  shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param n the number of leading dimensions to get, must be <= than the shape's
     * numDimensions()
     * @param type the shape datatype.
     * @param U the shape datatype.
     * @return a 1-dimensional operand containing the dimensions matching the last n dimensions of
     * the
     *      shape
     * @see org.tensorflow.op.ShapeOps.takeLast
     */
    public fun <U : TNumber> takeLast(
        shape: Shape<U>,
        n: Operand<U>,
        type: Class<U>
    ): Operand<U> = java.takeLast<U>(
        shape,
        n,
        type
    )

    /**
     * Flatten the operand to 1 dimension
     *
     * @param T the type of operand
     * @param U the shape datatype
     * @param scope current scope
     * @param operand the operand to flatten
     * @param type the shape datatype
     * @return the reshaped operand
     * @see org.tensorflow.op.ShapeOps.flatten
     */
    @JvmName("flattenReified")
    public inline fun <T : TType, reified U : TNumber> flattenTyped(operand: Operand<T>): Operand<T> =
        flatten<T, U>(operand, U::class.java)

    /**
     * Flatten the shape to 1 dimension.
     *
     * @param U the shape datatype
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param type the shape datatype
     * @return the flattened shape
     * @see org.tensorflow.op.ShapeOps.flatten
     */
    @JvmName("flattenReified")
    public inline fun <reified U : TNumber> flatten(shape: Shape<U>): Operand<U> = flatten<U>(
        shape,
        U::class.java
    )

    /**
     * Creates a 1-dimensional Operand containing the Shape's first dimension.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param type the shape datatype.
     * @param U the shape datatype.
     * @return a 1-dimensional Operand containing the Shape's first dimension
     * @see org.tensorflow.op.ShapeOps.head
     */
    @JvmName("headReified")
    public inline fun <reified U : TNumber> head(shape: Shape<U>): Operand<U> = head<U>(
        shape,
        U::class.java
    )

    /**
     * Get the number of dimensions of the shape object.
     *
     * @param U the shape datatype
     * @param scope the curren scope
     * @param shape the shape
     * @param type the shape datatype
     * @return the number of dimensions
     * @see org.tensorflow.op.ShapeOps.numDimensions
     */
    @JvmName("numDimensionsReified")
    public inline fun <reified U : TNumber> numDimensions(shape: Shape<U>): Operand<U> =
        numDimensions<U>(shape, U::class.java)

    /**
     * Reshapes the operand by reducing the shape to the specified axis.
     *
     * @param T the type of Operand
     * @param U the shape datatype
     * @param scope current scope
     * @param operand the operand
     * @param axis the axis
     * @param type the shape datatype
     * @return the reshaped operand
     * @see org.tensorflow.op.ShapeOps.reduceDims
     */
    @JvmName("reduceDimsReified")
    public inline fun <T : TType, reified U : TNumber> reduceDims(
        operand: Operand<T>,
        axis: Operand<U>
    ): Operand<T> = reduceDims<T, U>(operand, axis, U::class.java)

    /**
     * Reduces the shape to the specified axis.
     *
     * @param U the shape datatype
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param axis the axis
     * @param type the shape datatype
     * @return the reduced shape
     * @see org.tensorflow.op.ShapeOps.reduceDims
     */
    @JvmName("reduceDimsReified")
    public inline fun <reified U : TNumber> reduceDims(shape: Shape<U>, axis: Operand<U>):
        Operand<U> = reduceDims<U>(shape, axis, U::class.java)

    /**
     * Get the size represented by the TensorFlow shape.
     *
     * @param U the type of the shape
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param type the shape datatype
     * @return the size
     * @see org.tensorflow.op.ShapeOps.size
     */
    @JvmName("sizeReified")
    public inline fun <reified U : TNumber> size(shape: Shape<U>): Operand<U> = size<U>(
        shape,
        U::class.java
    )

    /**
     * Get the size of the specified dimension for the shape of the tensor.
     *
     * @param U the shape datatype
     * @param scope current scope
     * @param input the operand
     * @param dim the dimension
     * @param type the shape datatype
     * @return the size of the specified dimension
     * @see org.tensorflow.op.ShapeOps.size
     */
    @JvmName("sizeReified")
    public inline fun <T : TType, reified U : TNumber> size(input: Operand<T>, dim: Operand<U>):
        Operand<U> = size<T, U>(input, dim, U::class.java)

    /**
     * Get the size of the specified dimension in the shape.
     *
     * @param U the shape datatype
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param dim the dimension
     * @param type the shape datatype
     * @return the size of the specified dimension
     * @see org.tensorflow.op.ShapeOps.size
     */
    @JvmName("sizeReified")
    public inline fun <reified U : TNumber> size(shape: Shape<U>, dim: Operand<U>): Operand<U> =
        size<U>(shape, dim, U::class.java)

    /**
     * Removes dimensions of size 1 from the shape.
     *
     * @param U the shape datatype.
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param type the shape datatype.
     * @return the squeezed shape
     * @see org.tensorflow.op.ShapeOps.squeeze
     */
    @JvmName("squeezeReified")
    public inline fun <reified U : TNumber> squeeze(shape: Shape<U>): Operand<U> = squeeze<U>(
        shape,
        U::class.java
    )

    /**
     * Creates a 1-dimensional Operand that contains the dimension matching the last dimension of *
     *  the Shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param type the shape datatype.
     * @param U the shape datatype.
     * @return a 1-dimensional Operand that contains the dimension matching the last dimension of
     * the
     *      Shape
     * @see org.tensorflow.op.ShapeOps.tail
     */
    @JvmName("tailReified")
    public inline fun <reified U : TNumber> tail(shape: Shape<U>): Operand<U> = tail<U>(
        shape,
        U::class.java
    )

    /**
     * Creates a 1-dimensional operand containin the dimensions matching the first n dimensions of
     * the
     *  shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param n the number of leading dimensions to get, must be <= than the shape's
     * numDimensions()
     * @param type the shape datatype.
     * @param U the shape datatype.
     * @return a 1-dimensional operand with the dimensions matching * the first n dimensions of the
     *      shape
     * @see org.tensorflow.op.ShapeOps.take
     */
    @JvmName("takeReified")
    public inline fun <reified U : TNumber> take(shape: Shape<U>, n: Operand<U>): Operand<U> =
        take<U>(shape, n, U::class.java)

    /**
     * Creates a 1-dimensional operand containing the dimensions matching the last n dimensions of
     * the
     *  shape.
     *
     * @param scope current scope
     * @param shape the TensorFlow shape
     * @param n the number of leading dimensions to get, must be <= than the shape's
     * numDimensions()
     * @param type the shape datatype.
     * @param U the shape datatype.
     * @return a 1-dimensional operand containing the dimensions matching the last n dimensions of
     * the
     *      shape
     * @see org.tensorflow.op.ShapeOps.takeLast
     */
    @JvmName("takeLastReified")
    public inline fun <reified U : TNumber> takeLast(shape: Shape<U>, n: Operand<U>): Operand<U> =
        takeLast<U>(shape, n, U::class.java)
}
