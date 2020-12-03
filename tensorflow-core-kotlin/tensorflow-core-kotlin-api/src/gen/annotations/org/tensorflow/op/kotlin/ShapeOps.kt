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
import org.tensorflow.op.core.Shape
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType
import kotlin.Int
import kotlin.Long

/**
 * An API for building {@code shape} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class ShapeOps(
    /**
     * Get the parent {@link KotlinOps} object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.ShapeOps = ops.java.shape

    /**
     * Returns the current {@link Scope scope} of this API
     */
    public val scope: Scope = ops.scope

    public fun append(shape: Shape<TInt64>, lastDimension: Long): Operand<TInt64> = java.append(
        shape,
        lastDimension
    )

    public fun append(shape: Shape<TInt32>, lastDimension: Int): Operand<TInt32> = java.append(
        shape,
        lastDimension
    )

    public fun <T : TNumber> append(shape: Operand<T>, shapeToAppend: Operand<T>): Operand<T> =
        java.append<T>(
            shape,
            shapeToAppend
        )

    public fun <T : TType> flatten(operand: Operand<T>): Operand<T> = java.flatten<T>(
        operand
    )

    public fun flatten(shape: Shape<TInt32>): Operand<TInt32> = java.flatten(
        shape
    )

    public fun <T : TType, U : TNumber> flatten(operand: Operand<T>, dType: DataType<U>): Operand<T> =
        java.flatten<T, U>(
            operand,
            dType
        )

    public fun <U : TNumber> flatten(shape: Shape<U>, dType: DataType<U>): Operand<U> =
        java.flatten<U>(
            shape,
            dType
        )

    public fun head(shape: Shape<TInt32>): Operand<TInt32> = java.head(
        shape
    )

    public fun <U : TNumber> head(shape: Shape<U>, dType: DataType<U>): Operand<U> = java.head<U>(
        shape,
        dType
    )

    public fun numDimensions(shape: Shape<TInt32>): Operand<TInt32> = java.numDimensions(
        shape
    )

    public fun <U : TNumber> numDimensions(shape: Shape<U>, dType: DataType<U>): Operand<U> =
        java.numDimensions<U>(
            shape,
            dType
        )

    public fun prepend(shape: Shape<TInt64>, firstDimension: Long): Operand<TInt64> = java.prepend(
        shape,
        firstDimension
    )

    public fun prepend(shape: Shape<TInt32>, firstDimension: Int): Operand<TInt32> = java.prepend(
        shape,
        firstDimension
    )

    public fun <T : TNumber> prepend(shape: Operand<T>, shapeToPrepend: Operand<T>): Operand<T> =
        java.prepend<T>(
            shape,
            shapeToPrepend
        )

    public fun <T : TType> reduceDims(operand: Operand<T>, axis: Operand<TInt32>): Operand<T> =
        java.reduceDims<T>(
            operand,
            axis
        )

    public fun reduceDims(shape: Shape<TInt32>, axis: Operand<TInt32>): Operand<TInt32> =
        java.reduceDims(
            shape,
            axis
        )

    public fun <T : TType, U : TNumber> reduceDims(
        operand: Operand<T>,
        axis: Operand<U>,
        dType: DataType<U>
    ): Operand<T> = java.reduceDims<T, U>(
        operand,
        axis,
        dType
    )

    public fun <U : TNumber> reduceDims(
        shape: Shape<U>,
        axis: Operand<U>,
        dType: DataType<U>
    ): Operand<U> = java.reduceDims<U>(
        shape,
        axis,
        dType
    )

    public fun size(shape: Shape<TInt32>): Operand<TInt32> = java.size(
        shape
    )

    public fun <T : TType> size(input: Operand<T>, dim: Operand<TInt32>): Operand<TInt32> =
        java.size<T>(
            input,
            dim
        )

    public fun <U : TNumber> size(shape: Shape<U>, dType: DataType<U>): Operand<U> = java.size<U>(
        shape,
        dType
    )

    public fun size(shape: Shape<TInt32>, dim: Operand<TInt32>): Operand<TInt32> = java.size(
        shape,
        dim
    )

    public fun <T : TType, U : TNumber> size(
        input: Operand<T>,
        dim: Operand<U>,
        dType: DataType<U>
    ): Operand<U> = java.size<T, U>(
        input,
        dim,
        dType
    )

    public fun <U : TNumber> size(
        shape: Shape<U>,
        dim: Operand<U>,
        dType: DataType<U>
    ): Operand<U> = java.size<U>(
        shape,
        dim,
        dType
    )

    public fun squeeze(shape: Shape<TInt32>): Operand<TInt32> = java.squeeze(
        shape
    )

    public fun <U : TNumber> squeeze(shape: Shape<U>, dType: DataType<U>): Operand<U> =
        java.squeeze<U>(
            shape,
            dType
        )

    public fun tail(shape: Shape<TInt32>): Operand<TInt32> = java.tail(
        shape
    )

    public fun <U : TNumber> tail(shape: Shape<U>, dType: DataType<U>): Operand<U> = java.tail<U>(
        shape,
        dType
    )

    public fun take(shape: Shape<TInt32>, n: Operand<TInt32>): Operand<TInt32> = java.take(
        shape,
        n
    )

    public fun <U : TNumber> take(
        shape: Shape<U>,
        n: Operand<U>,
        dType: DataType<U>
    ): Operand<U> = java.take<U>(
        shape,
        n,
        dType
    )

    public fun <U : TNumber> takeLast(shape: Shape<TInt32>, n: Operand<TInt32>): Operand<TInt32> =
        java.takeLast<U>(
            shape,
            n
        )

    public fun <U : TNumber> takeLast(
        shape: Shape<U>,
        n: Operand<U>,
        dType: DataType<U>
    ): Operand<U> = java.takeLast<U>(
        shape,
        n,
        dType
    )
}
