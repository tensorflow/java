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
package org.tensorflow.op.core;

import java.util.Arrays;
import org.tensorflow.Operand;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.math.FloorMod;
import org.tensorflow.op.math.NotEqual;
import org.tensorflow.op.math.Sub;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An operator providing methods on org.tensorflow.op.core.Shape tensors and 1d operands that
 * represent the dimensions of a shape.
 *
 * <pre>{@code
 * Ops tf = Ops.create();
 * Operand<TFloat32> yPred = tf.constant(new float[][][] {{{0f, 0.5f}, {0.3f, 0.9f}}});
 * Shape<TInt32> predShape = tf.shape(yPred);
 * Operand<TInt32> numLabels =
 *     tf.reduceProd(tf.shape.takeLast(predShape, // take all but the first dimension
 *         tf.math.sub(  // by subtract 1 from the total dimensions represent by  predShape
 *             tf.shape.numDimensions(predShape),
 *             tf.constant(1))),
 *         tf.constant(0));
 *
 * Operand<TInt32> numPred = tf.shape.size(predShape, tf.constant(0));
 * Operand<TFloat32> predFlat = tf.shape.flatten(yPred);
 *
 * Shape<TInt64> predShape64 = tf.shape(yPred, TInt64.class);
 * Operand<TInt64> predSqueezed = tf.shape.squeeze(predShape64, TInt64.class);
 * }</pre>
 */
@Operator(group = "shape")
public abstract class Shapes {

  /**
   * Flatten the operand to 1 dimension.
   *
   * @param <T> the type of operand
   * @param scope current scope
   * @param operand the operand to flatten
   * @return the reshaped operand
   */
  @Endpoint(name = "flatten")
  public static <T extends TType> Operand<T> flatten(Scope scope, Operand<T> operand) {
    return flatten(scope, operand, TInt32.class);
  }

  /**
   * Flatten the operand to 1 dimension
   *
   * @param <T> the type of operand
   * @param <U> the shape datatype
   * @param scope current scope
   * @param operand the operand to flatten
   * @param dType the shape datatype
   * @return the reshaped operand
   */
  @Endpoint(name = "flatten")
  public static <T extends TType, U extends TNumber> Operand<T> flatten(
      Scope scope, Operand<T> operand, Class<U> dType) {
    Operand<U> flatShape = flatten(scope, Shape.create(scope, operand, dType), dType);
    return Reshape.create(scope, operand, flatShape);
  }

  /**
   * Flatten the shape to 1 dimension.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @return the flattened shape
   */
  @Endpoint(name = "flatten")
  public static Operand<TInt32> flatten(Scope scope, Shape<TInt32> shape) {
    return flatten(scope, shape, TInt32.class);
  }

  /**
   * Flatten the shape to 1 dimension.
   *
   * @param <U> the shape datatype
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param dType the shape datatype
   * @return the flattened shape
   */
  @Endpoint(name = "flatten")
  public static <U extends TNumber> Operand<U> flatten(
      Scope scope, Shape<U> shape, Class<U> dType) {
    return ExpandDims.create(
        scope,
        size(scope, shape, dType),
        Cast.create(scope, Constant.scalarOf(scope, -1), TInt32.class));
  }

  /**
   * Get the size represented by the TensorFlow shape.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @return the size
   */
  @Endpoint(name = "size")
  public static Operand<TInt32> size(Scope scope, Shape<TInt32> shape) {
    return size(scope, shape, TInt32.class);
  }

  /**
   * Get the size represented by the TensorFlow shape.
   *
   * @param <U> the type of the shape
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param dType the shape datatype
   * @return the size
   */
  @Endpoint(name = "size")
  public static <U extends TNumber> Operand<U> size(
      Scope scope, Shape<U> shape, Class<U> dType) {
    Slice<U> dims =
        Slice.create(
            scope,
            shape,
            Cast.create(scope, Constant.arrayOf(scope, 0), dType),
            ExpandDims.create(
                scope,
                Cast.create(scope, Constant.scalarOf(scope, -1), dType),
                Constant.scalarOf(scope, -1)));
    return ReduceProd.create(scope, dims, Constant.scalarOf(scope, 0));
  }

  /**
   * Get the size of the specified dimension in the shape.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param dim the dimension
   * @return the size of the specified dimension
   */
  @Endpoint(name = "size")
  public static Operand<TInt32> size(Scope scope, Shape<TInt32> shape, Operand<TInt32> dim) {
    return size(scope, shape, dim, TInt32.class);
  }

  /**
   * Get the size of the specified dimension in the shape.
   *
   * @param <U> the shape datatype
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param dim the dimension
   * @param dType the shape datatype
   * @return the size of the specified dimension
   */
  @Endpoint(name = "size")
  public static <U extends TNumber> Operand<U> size(
      Scope scope, Shape<U> shape, Operand<U> dim, Class<U> dType) {
    return Slice.create(
        scope,
        shape,
        ExpandDims.create(scope, dim, Cast.create(scope, Constant.scalarOf(scope, -1), dType)),
        ExpandDims.create(
            scope,
            Cast.create(scope, Constant.scalarOf(scope, 1), dType),
            Cast.create(scope, Constant.scalarOf(scope, -1), dType)));
  }

  /**
   * Get the size of the specified dimension for the shape of the tensor.
   *
   * @param scope current scope
   * @param input the operand
   * @param dim the dimension
   * @return the size of the specified dimension
   */
  @Endpoint(name = "size")
  public static <T extends TType> Operand<TInt32> size(
      Scope scope, Operand<T> input, Operand<TInt32> dim) {
    return size(scope, input, dim, TInt32.class);
  }

  /**
   * Get the size of the specified dimension for the shape of the tensor.
   *
   * @param <U> the shape datatype
   * @param scope current scope
   * @param input the operand
   * @param dim the dimension
   * @param dType the shape datatype
   * @return the size of the specified dimension
   */
  @Endpoint(name = "size")
  public static <T extends TType, U extends TNumber> Operand<U> size(
      Scope scope, Operand<T> input, Operand<U> dim, Class<U> dType) {
    return size(scope, Shape.create(scope, input, dType), dim, dType);
  }

  /**
   * Get the number of dimensions of the shape object.
   *
   * @param scope current scope
   * @param shape the shape
   * @return the number of dimensions
   */
  @Endpoint(name = "numDimensions")
  public static Operand<TInt32> numDimensions(Scope scope, Shape<TInt32> shape) {
    return Size.create(scope, shape, TInt32.class);
  }

  /**
   * Get the number of dimensions of the shape object.
   *
   * @param <U> the shape datatype
   * @param scope the curren scope
   * @param shape the shape
   * @param dType the shape datatype
   * @return the number of dimensions
   */
  @Endpoint(name = "numDimensions")
  public static <U extends TNumber> Operand<U> numDimensions(
      Scope scope, Shape<U> shape, Class<U> dType) {
    return Size.create(scope, shape, dType);
  }

  /**
   * Reshapes the operand by reducing the shape to the specified axis.
   *
   * @param <T> the type of Operand
   * @param scope current scope
   * @param operand the operand
   * @param axis the axis
   * @return the reshaped operand
   */
  @Endpoint(name = "reduceDims")
  public static <T extends TType> Operand<T> reduceDims(
      Scope scope, Operand<T> operand, Operand<TInt32> axis) {
    return reduceDims(scope, operand, axis, TInt32.class);
  }

  /**
   * Reshapes the operand by reducing the shape to the specified axis.
   *
   * @param <T> the type of Operand
   * @param <U> the shape datatype
   * @param scope current scope
   * @param operand the operand
   * @param axis the axis
   * @param dType the shape datatype
   * @return the reshaped operand
   */
  @Endpoint(name = "reduceDims")
  public static <T extends TType, U extends TNumber> Operand<T> reduceDims(
      Scope scope, Operand<T> operand, Operand<U> axis, Class<U> dType) {
    Shape<U> newShape = Shape.create(scope, operand, dType);
    return Reshape.create(scope, operand, reduceDims(scope, newShape, axis, dType));
  }

  /**
   * Reduces the shape to the specified axis.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param axis the axis
   * @return an operand containing the dimensions for the reduced shape
   */
  @Endpoint(name = "reduceDims")
  public static Operand<TInt32> reduceDims(Scope scope, Shape<TInt32> shape, Operand<TInt32> axis) {
    return reduceDims(scope, shape, axis, TInt32.class);
  }

  /**
   * Reduces the shape to the specified axis.
   *
   * @param <U> the shape datatype
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param axis the axis
   * @param dType the shape datatype
   * @return the reduced shape
   */
  @Endpoint(name = "reduceDims")
  public static <U extends TNumber> Operand<U> reduceDims(
      Scope scope, Shape<U> shape, Operand<U> axis, Class<U> dType) {
    Size<U> rank = Size.create(scope, shape, dType);
    axis = FloorMod.create(scope, axis, rank);
    Sub<U> remainder = Sub.create(scope, rank, axis);

    Operand<U> dims1 =
        Slice.create(
            scope,
            shape,
            Cast.create(scope, Constant.arrayOf(scope, 0), dType),
            ExpandDims.create(scope, axis, Constant.scalarOf(scope, -1)));

    Operand<U> dims2 =
        Slice.create(
            scope,
            shape,
            ExpandDims.create(scope, axis, Constant.scalarOf(scope, -1)),
            ExpandDims.create(
                scope,
                Cast.create(scope, Constant.scalarOf(scope, -1), dType),
                Constant.scalarOf(scope, -1)));

    Operand<U> prod =
        ReduceProd.create(
            scope, dims2, Constant.scalarOf(scope, 0), ReduceProd.keepDims(Boolean.TRUE));

    return Concat.create(scope, Arrays.asList(dims1, prod), Constant.scalarOf(scope, 0));
  }

  /**
   * Removes dimensions of size 1 from the shape.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @return the squeezed shape
   */
  @Endpoint(name = "squeeze")
  public static Operand<TInt32> squeeze(Scope scope, Shape<TInt32> shape) {
    return squeeze(scope, shape, TInt32.class);
  }

  /**
   * Removes dimensions of size 1 from the shape.
   *
   * @param <U> the shape datatype.
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param dType the shape datatype.
   * @return the squeezed shape
   */
  @Endpoint(name = "squeeze")
  public static <U extends TNumber> Operand<U> squeeze(
      Scope scope, Shape<U> shape, Class<U> dType) {
    Operand<TBool> mask =
        NotEqual.create(scope, shape, Cast.create(scope, OnesLike.create(scope, shape), dType));

    return Gather.create(scope, shape, Where.create(scope, mask), Constant.scalarOf(scope, 0));
  }

  /**
   * Creates a 1-dimensional Operand containing the Shape's first dimension.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @return a 1-dimensional Operand containing the Shape's first dimension
   */
  @Endpoint(name = "head")
  public static Operand<TInt32> head(Scope scope, Shape<TInt32> shape) {
    return head(scope, shape, TInt32.class);
  }

  /**
   * Creates a 1-dimensional Operand containing the Shape's first dimension.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param dType the shape datatype.
   * @param <U> the shape datatype.
   * @return a 1-dimensional Operand containing the Shape's first dimension
   */
  @Endpoint(name = "head")
  public static <U extends TNumber> Operand<U> head(
      Scope scope, Shape<U> shape, Class<U> dType) {
    return take(scope, shape, Cast.create(scope, Constant.scalarOf(scope, 1), dType), dType);
  }

  /**
   * Creates a 1-dimensional operand with the dimensions matching the first n dimensions of the
   * shape.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param n the number of leading dimensions to get, must be <= than the shape's numDimensions()
   * @return a 1-dimensional operand with the dimensions matching the first n dimensions of the
   *     shape
   */
  @Endpoint(name = "take")
  public static Operand<TInt32> take(Scope scope, Shape<TInt32> shape, Operand<TInt32> n) {
    return take(scope, shape, n, TInt32.class);
  }

  /**
   * Creates a 1-dimensional operand containin the dimensions matching the first n dimensions of the
   * shape.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param n the number of leading dimensions to get, must be <= than the shape's numDimensions()
   * @param dType the shape datatype.
   * @param <U> the shape datatype.
   * @return a 1-dimensional operand with the dimensions matching * the first n dimensions of the
   *     shape
   */
  @Endpoint(name = "take")
  public static <U extends TNumber> Operand<U> take(
      Scope scope, Shape<U> shape, Operand<U> n, Class<U> dType) {
    return Slice.create(
        scope,
        shape,
        Cast.create(scope, Constant.arrayOf(scope, 0), dType),
        ExpandDims.create(scope, n, Constant.scalarOf(scope, -1)));
  }

  /**
   * Creates a 1-dimensional Operand that contains the dimension matching the last dimension of the
   * Shape.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @return a 1-dimensional Operand that contains the dimension matching the last dimension of the
   *     Shape
   */
  @Endpoint(name = "tail")
  public static Operand<TInt32> tail(Scope scope, Shape<TInt32> shape) {
    return tail(scope, shape, TInt32.class);
  }

  /**
   * Creates a 1-dimensional Operand that contains the dimension matching the last dimension of *
   * the Shape.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param dType the shape datatype.
   * @param <U> the shape datatype.
   * @return a 1-dimensional Operand that contains the dimension matching the last dimension of the
   *     Shape
   */
  @Endpoint(name = "tail")
  public static <U extends TNumber> Operand<U> tail(
      Scope scope, Shape<U> shape, Class<U> dType) {
    return takeLast(scope, shape, Cast.create(scope, Constant.scalarOf(scope, 1), dType), dType);
  }

  /**
   * Creates a 1-dimensional operand containing the dimensions matching the last n dimensions of the
   * shape.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param n the number of leading dimensions to get, must be <= than the shape's numDimensions()
   * @return a 1-dimensional operand containing the dimensions matching the last n dimensions of the
   *     shape
   */
  @Endpoint(name = "takeLast")
  public static <U extends TNumber> Operand<TInt32> takeLast(
      Scope scope, Shape<TInt32> shape, Operand<TInt32> n) {
    return takeLast(scope, shape, n, TInt32.class);
  }

  /**
   * Creates a 1-dimensional operand containing the dimensions matching the last n dimensions of the
   * shape.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param n the number of leading dimensions to get, must be <= than the shape's numDimensions()
   * @param dType the shape datatype.
   * @param <U> the shape datatype.
   * @return a 1-dimensional operand containing the dimensions matching the last n dimensions of the
   *     shape
   */
  @Endpoint(name = "takeLast")
  public static <U extends TNumber> Operand<U> takeLast(
      Scope scope, Shape<U> shape, Operand<U> n, Class<U> dType) {

    Size<U> rank = Size.create(scope, shape, dType);
    Sub<U> start = Sub.create(scope, rank, n);
    return Slice.create(
        scope,
        shape,
        ExpandDims.create(scope, start, Constant.scalarOf(scope, -1)),
        ExpandDims.create(
            scope,
            Cast.create(scope, Constant.scalarOf(scope, -1), dType),
            Constant.scalarOf(scope, -1)));
  }

  /**
   * Creates a 1-dimensional operand containing the first dimension followed by the dimensions of
   * the shape.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param firstDimension the dimension to prepend
   * @return a 1-dimensional operand containing the first dimension followed by the dimensions of
   *     the shape
   */
  @Endpoint(name = "prepend")
  public static Operand<TInt32> prepend(Scope scope, Shape<TInt32> shape, int firstDimension) {
    Operand<TInt32> dim = Constant.arrayOf(scope, firstDimension);
    return Concat.create(scope, Arrays.asList(dim, shape), Constant.scalarOf(scope, 0));
  }

  /**
   * Creates a 1-dimensional operand containing the first dimension followed by the dimensions of
   * the shape.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param firstDimension the dimension to prepend
   * @return a 1-dimensional operand containing the first dimension followed by the dimensions of
   *     the shape
   */
  @Endpoint(name = "prepend")
  public static Operand<TInt64> prepend(Scope scope, Shape<TInt64> shape, long firstDimension) {
    Operand<TInt64> dim = Constant.arrayOf(scope, firstDimension);
    return Concat.create(scope, Arrays.asList(dim, shape), Constant.scalarOf(scope, 0));
  }

  /**
   * Creates a 1-dimensional operand that represents a new shape containing the dimensions of an
   * operand representing the shape to prepend, followed by the dimensions of an operand
   * representing a shape.
   *
   * @param scope current scope
   * @param shape an operand containing the dimensions of a shape
   * @param shapeToPrepend an operand containing the dimensions of the shape to prepend
   * @return a 1-dimensional operand that represents a new shape containing the dimensions of an
   *     operand representing the shape to prepend, followed by the dimensions of an operand
   *     representing the shape
   */
  @Endpoint(name = "prepend")
  public static <T extends TNumber> Operand<T> prepend(
      Scope scope, Operand<T> shape, Operand<T> shapeToPrepend) {

    return Concat.create(scope, Arrays.asList(shapeToPrepend, shape), Constant.scalarOf(scope, 0));
  }

  /**
   * Creates a 1-dimensional operand containing the dimensions of a shape followed by the last
   * dimension.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param lastDimension the dimension(s) to append
   * @return a 1-dimensional operand containing the dimensions of a shape followed by the last
   *     dimension
   */
  @Endpoint(name = "append")
  public static Operand<TInt32> append(Scope scope, Shape<TInt32> shape, int lastDimension) {
    Operand<TInt32> dim = Constant.arrayOf(scope, lastDimension);
    return Concat.create(scope, Arrays.asList(shape, dim), Constant.scalarOf(scope, 0));
  }

  /**
   * Creates a 1-dimensional operand containing the dimensions of a shape followed by the last
   * dimension.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param lastDimension the dimension(s) to append
   * @return a 1-dimensional operand containing the dimensions of a shape followed by the last
   *     dimension
   */
  @Endpoint(name = "append")
  public static Operand<TInt64> append(Scope scope, Shape<TInt64> shape, long lastDimension) {

    Operand<TInt64> dim = Constant.arrayOf(scope, lastDimension);
    return Concat.create(scope, Arrays.asList(shape, dim), Constant.scalarOf(scope, 0));
  }

  /**
   * Creates a 1-dimensional operand that represents a new shape containing the dimensions of the
   * operand representing a shape, followed by the dimensions of an operand representing a shape to
   * append.
   *
   * @param scope current scope
   * @param shape the TensorFlow shape
   * @param shapeToAppend the other shape to append
   * @return a 1-dimensional operand that represents a new shape containing the dimensions of the
   *     operand representing a shape, followed by the dimensions of an operand representing a shape
   *     to append
   */
  @Endpoint(name = "append")
  public static <T extends TNumber> Operand<T> append(
      Scope scope, Operand<T> shape, Operand<T> shapeToAppend) {
    return Concat.create(scope, Arrays.asList(shape, shapeToAppend), Constant.scalarOf(scope, 0));
  }
}
