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
package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.op.core.Shape;
import org.tensorflow.op.core.Shapes;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code shape} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class ShapeOps {
  private final Scope scope;

  private final Ops ops;

  ShapeOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Creates a 1-dimensional operand containing the dimensions of a shape followed by the last
   *  dimension.
   *
   * @param shape the TensorFlow shape
   * @param lastDimension the dimension(s) to append
   * @return a 1-dimensional operand containing the dimensions of a shape followed by the last
   *      dimension
   */
  public Operand<TInt64> append(Shape<TInt64> shape, long lastDimension) {
    return Shapes.append(scope, shape, lastDimension);
  }

  /**
   * Creates a 1-dimensional operand containing the dimensions of a shape followed by the last
   *  dimension.
   *
   * @param shape the TensorFlow shape
   * @param lastDimension the dimension(s) to append
   * @return a 1-dimensional operand containing the dimensions of a shape followed by the last
   *      dimension
   */
  public Operand<TInt32> append(Shape<TInt32> shape, int lastDimension) {
    return Shapes.append(scope, shape, lastDimension);
  }

  /**
   * Creates a 1-dimensional operand that represents a new shape containing the dimensions of the
   *  operand representing a shape, followed by the dimensions of an operand representing a shape to
   *  append.
   *
   * @param shape the TensorFlow shape
   * @param shapeToAppend the other shape to append
   * @return a 1-dimensional operand that represents a new shape containing the dimensions of the
   *      operand representing a shape, followed by the dimensions of an operand representing a shape
   *      to append
   */
  public <T extends TNumber> Operand<T> append(Operand<T> shape, Operand<T> shapeToAppend) {
    return Shapes.append(scope, shape, shapeToAppend);
  }

  /**
   * Flatten the operand to 1 dimension.
   *
   * @param <T> the type of operand
   * @param operand the operand to flatten
   * @return the reshaped operand
   */
  public <T extends TType> Operand<T> flatten(Operand<T> operand) {
    return Shapes.flatten(scope, operand);
  }

  /**
   * Flatten the shape to 1 dimension.
   *
   * @param shape the TensorFlow shape
   * @return the flattened shape
   */
  public Operand<TInt32> flatten(Shape<TInt32> shape) {
    return Shapes.flatten(scope, shape);
  }

  /**
   * Flatten the operand to 1 dimension
   *
   * @param <T> the type of operand
   * @param <U> the shape datatype
   * @param operand the operand to flatten
   * @param type the shape datatype
   * @return the reshaped operand
   */
  public <T extends TType, U extends TNumber> Operand<T> flatten(Operand<T> operand,
      Class<U> type) {
    return Shapes.flatten(scope, operand, type);
  }

  /**
   * Flatten the shape to 1 dimension.
   *
   * @param <U> the shape datatype
   * @param shape the TensorFlow shape
   * @param type the shape datatype
   * @return the flattened shape
   */
  public <U extends TNumber> Operand<U> flatten(Shape<U> shape, Class<U> type) {
    return Shapes.flatten(scope, shape, type);
  }

  /**
   * Creates a 1-dimensional Operand containing the Shape's first dimension.
   *
   * @param shape the TensorFlow shape
   * @return a 1-dimensional Operand containing the Shape's first dimension
   */
  public Operand<TInt32> head(Shape<TInt32> shape) {
    return Shapes.head(scope, shape);
  }

  /**
   * Creates a 1-dimensional Operand containing the Shape's first dimension.
   *
   * @param shape the TensorFlow shape
   * @param type the shape datatype.
   * @param <U> the shape datatype.
   * @return a 1-dimensional Operand containing the Shape's first dimension
   */
  public <U extends TNumber> Operand<U> head(Shape<U> shape, Class<U> type) {
    return Shapes.head(scope, shape, type);
  }

  /**
   * Get the number of dimensions of the shape object.
   *
   * @param shape the shape
   * @return the number of dimensions
   */
  public Operand<TInt32> numDimensions(Shape<TInt32> shape) {
    return Shapes.numDimensions(scope, shape);
  }

  /**
   * Get the number of dimensions of the shape object.
   *
   * @param <U> the shape datatype
   * @param shape the shape
   * @param type the shape datatype
   * @return the number of dimensions
   */
  public <U extends TNumber> Operand<U> numDimensions(Shape<U> shape, Class<U> type) {
    return Shapes.numDimensions(scope, shape, type);
  }

  /**
   * Creates a 1-dimensional operand containing the first dimension followed by the dimensions of
   *  the shape.
   *
   * @param shape the TensorFlow shape
   * @param firstDimension the dimension to prepend
   * @return a 1-dimensional operand containing the first dimension followed by the dimensions of
   *      the shape
   */
  public Operand<TInt64> prepend(Shape<TInt64> shape, long firstDimension) {
    return Shapes.prepend(scope, shape, firstDimension);
  }

  /**
   * Creates a 1-dimensional operand containing the first dimension followed by the dimensions of
   *  the shape.
   *
   * @param shape the TensorFlow shape
   * @param firstDimension the dimension to prepend
   * @return a 1-dimensional operand containing the first dimension followed by the dimensions of
   *      the shape
   */
  public Operand<TInt32> prepend(Shape<TInt32> shape, int firstDimension) {
    return Shapes.prepend(scope, shape, firstDimension);
  }

  /**
   * Creates a 1-dimensional operand that represents a new shape containing the dimensions of an
   *  operand representing the shape to prepend, followed by the dimensions of an operand
   *  representing a shape.
   *
   * @param shape an operand containing the dimensions of a shape
   * @param shapeToPrepend an operand containing the dimensions of the shape to prepend
   * @return a 1-dimensional operand that represents a new shape containing the dimensions of an
   *      operand representing the shape to prepend, followed by the dimensions of an operand
   *      representing the shape
   */
  public <T extends TNumber> Operand<T> prepend(Operand<T> shape, Operand<T> shapeToPrepend) {
    return Shapes.prepend(scope, shape, shapeToPrepend);
  }

  /**
   * Reshapes the operand by reducing the shape to the specified axis.
   *
   * @param <T> the type of Operand
   * @param operand the operand
   * @param axis the axis
   * @return the reshaped operand
   */
  public <T extends TType> Operand<T> reduceDims(Operand<T> operand, Operand<TInt32> axis) {
    return Shapes.reduceDims(scope, operand, axis);
  }

  /**
   * Reduces the shape to the specified axis.
   *
   * @param shape the TensorFlow shape
   * @param axis the axis
   * @return an operand containing the dimensions for the reduced shape
   */
  public Operand<TInt32> reduceDims(Shape<TInt32> shape, Operand<TInt32> axis) {
    return Shapes.reduceDims(scope, shape, axis);
  }

  /**
   * Reshapes the operand by reducing the shape to the specified axis.
   *
   * @param <T> the type of Operand
   * @param <U> the shape datatype
   * @param operand the operand
   * @param axis the axis
   * @param type the shape datatype
   * @return the reshaped operand
   */
  public <T extends TType, U extends TNumber> Operand<T> reduceDims(Operand<T> operand,
      Operand<U> axis, Class<U> type) {
    return Shapes.reduceDims(scope, operand, axis, type);
  }

  /**
   * Reduces the shape to the specified axis.
   *
   * @param <U> the shape datatype
   * @param shape the TensorFlow shape
   * @param axis the axis
   * @param type the shape datatype
   * @return the reduced shape
   */
  public <U extends TNumber> Operand<U> reduceDims(Shape<U> shape, Operand<U> axis, Class<U> type) {
    return Shapes.reduceDims(scope, shape, axis, type);
  }

  /**
   * Get the size represented by the TensorFlow shape.
   *
   * @param shape the TensorFlow shape
   * @return the size
   */
  public Operand<TInt32> size(Shape<TInt32> shape) {
    return Shapes.size(scope, shape);
  }

  /**
   * Get the size of the specified dimension for the shape of the tensor.
   *
   * @param input the operand
   * @param dim the dimension
   * @return the size of the specified dimension
   */
  public <T extends TType> Operand<TInt32> size(Operand<T> input, Operand<TInt32> dim) {
    return Shapes.size(scope, input, dim);
  }

  /**
   * Get the size of the specified dimension in the shape.
   *
   * @param shape the TensorFlow shape
   * @param dim the dimension
   * @return the size of the specified dimension
   */
  public Operand<TInt32> size(Shape<TInt32> shape, Operand<TInt32> dim) {
    return Shapes.size(scope, shape, dim);
  }

  /**
   * Get the size represented by the TensorFlow shape.
   *
   * @param <U> the type of the shape
   * @param shape the TensorFlow shape
   * @param type the shape datatype
   * @return the size
   */
  public <U extends TNumber> Operand<U> size(Shape<U> shape, Class<U> type) {
    return Shapes.size(scope, shape, type);
  }

  /**
   * Get the size of the specified dimension for the shape of the tensor.
   *
   * @param <U> the shape datatype
   * @param input the operand
   * @param dim the dimension
   * @param type the shape datatype
   * @return the size of the specified dimension
   */
  public <T extends TType, U extends TNumber> Operand<U> size(Operand<T> input, Operand<U> dim,
      Class<U> type) {
    return Shapes.size(scope, input, dim, type);
  }

  /**
   * Get the size of the specified dimension in the shape.
   *
   * @param <U> the shape datatype
   * @param shape the TensorFlow shape
   * @param dim the dimension
   * @param type the shape datatype
   * @return the size of the specified dimension
   */
  public <U extends TNumber> Operand<U> size(Shape<U> shape, Operand<U> dim, Class<U> type) {
    return Shapes.size(scope, shape, dim, type);
  }

  /**
   * Removes dimensions of size 1 from the shape.
   *
   * @param shape the TensorFlow shape
   * @return the squeezed shape
   */
  public Operand<TInt32> squeeze(Shape<TInt32> shape) {
    return Shapes.squeeze(scope, shape);
  }

  /**
   * Removes dimensions of size 1 from the shape.
   *
   * @param <U> the shape datatype.
   * @param shape the TensorFlow shape
   * @param type the shape datatype.
   * @return the squeezed shape
   */
  public <U extends TNumber> Operand<U> squeeze(Shape<U> shape, Class<U> type) {
    return Shapes.squeeze(scope, shape, type);
  }

  /**
   * Creates a 1-dimensional Operand that contains the dimension matching the last dimension of the
   *  Shape.
   *
   * @param shape the TensorFlow shape
   * @return a 1-dimensional Operand that contains the dimension matching the last dimension of the
   *      Shape
   */
  public Operand<TInt32> tail(Shape<TInt32> shape) {
    return Shapes.tail(scope, shape);
  }

  /**
   * Creates a 1-dimensional Operand that contains the dimension matching the last dimension of *
   *  the Shape.
   *
   * @param shape the TensorFlow shape
   * @param type the shape datatype.
   * @param <U> the shape datatype.
   * @return a 1-dimensional Operand that contains the dimension matching the last dimension of the
   *      Shape
   */
  public <U extends TNumber> Operand<U> tail(Shape<U> shape, Class<U> type) {
    return Shapes.tail(scope, shape, type);
  }

  /**
   * Creates a 1-dimensional operand with the dimensions matching the first n dimensions of the
   *  shape.
   *
   * @param shape the TensorFlow shape
   * @param n the number of leading dimensions to get, must be <= than the shape's numDimensions()
   * @return a 1-dimensional operand with the dimensions matching the first n dimensions of the
   *      shape
   */
  public Operand<TInt32> take(Shape<TInt32> shape, Operand<TInt32> n) {
    return Shapes.take(scope, shape, n);
  }

  /**
   * Creates a 1-dimensional operand containin the dimensions matching the first n dimensions of the
   *  shape.
   *
   * @param shape the TensorFlow shape
   * @param n the number of leading dimensions to get, must be <= than the shape's numDimensions()
   * @param type the shape datatype.
   * @param <U> the shape datatype.
   * @return a 1-dimensional operand with the dimensions matching * the first n dimensions of the
   *      shape
   */
  public <U extends TNumber> Operand<U> take(Shape<U> shape, Operand<U> n, Class<U> type) {
    return Shapes.take(scope, shape, n, type);
  }

  /**
   * Creates a 1-dimensional operand containing the dimensions matching the last n dimensions of the
   *  shape.
   *
   * @param shape the TensorFlow shape
   * @param n the number of leading dimensions to get, must be <= than the shape's numDimensions()
   * @return a 1-dimensional operand containing the dimensions matching the last n dimensions of the
   *      shape
   */
  public <U extends TNumber> Operand<TInt32> takeLast(Shape<TInt32> shape, Operand<TInt32> n) {
    return Shapes.takeLast(scope, shape, n);
  }

  /**
   * Creates a 1-dimensional operand containing the dimensions matching the last n dimensions of the
   *  shape.
   *
   * @param shape the TensorFlow shape
   * @param n the number of leading dimensions to get, must be <= than the shape's numDimensions()
   * @param type the shape datatype.
   * @param <U> the shape datatype.
   * @return a 1-dimensional operand containing the dimensions matching the last n dimensions of the
   *      shape
   */
  public <U extends TNumber> Operand<U> takeLast(Shape<U> shape, Operand<U> n, Class<U> type) {
    return Shapes.takeLast(scope, shape, n, type);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
