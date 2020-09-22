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
=======================================================================*/
package org.tensorflow.framework.utils;

import org.tensorflow.*;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Various methods for processing with Shapes and Operands */
public class ShapeUtils {

  /**
   * Converts a shape operand to a Shape object
   *
   * @param dims the Operand containing the shape values
   * @return a new Shape based on an Operand that contains dimensions
   */
  public static <T extends TNumber> Shape toShape(Scope scope, Operand<T> dims) {
    long[] longDims = getLongArray(scope, dims);
    return Shape.of(longDims);
  }

  /**
   * Converts a TInt32 type Operand to a Java int array
   *
   * @param scope the TensorFlow scope
   * @param dims the TInt32 Operand
   * @return the int array
   */
  public static int[] getIntArray(Scope scope, Operand<TInt32> dims) {
    long[] longDims = getLongArray(scope, dims);
    return Arrays.stream(longDims).mapToInt(i -> (int) i).toArray();
  }

  /**
   * Converts a TInt32 or TInt64 Operand to a java long array
   *
   * @param scope the TensorFlow scope
   * @param dims the Operand
   * @param <T> the type of the dimensions, must either be TInt32 or TInt64 type
   * @return the long array
   * @throws java.lang.IllegalArgumentException if the dims type is not an integer
   */
  public static <T extends TNumber> long[] getLongArray(Scope scope, Operand<T> dims) {
    DataType<T> dType = dims.asOutput().dataType();
    if (!dType.isInteger()) {
      throw new IllegalArgumentException("the data type must be an integer type");
    }
    List<Long> result = new ArrayList<>();

    if (scope.env().isEager()) {
      if (dType.equals(TInt32.DTYPE)) {
        @SuppressWarnings("unchecked")
        Operand<TInt32> idims = (Operand<TInt32>) dims;

        idims.asOutput().data().scalars().forEach(s -> result.add((long) s.getInt()));
      } else if (dType.equals(TInt64.DTYPE)) {
        @SuppressWarnings("unchecked")
        Operand<TInt64> ldims = (Operand<TInt64>) dims;
        ldims.asOutput().data().scalars().forEach(s -> result.add(s.getLong()));
      } else if (dType.equals(TUint8.DTYPE)) {
        @SuppressWarnings("unchecked")
        Operand<TUint8> udims = (Operand<TUint8>) dims;
        udims.asOutput().data().scalars().forEach(s -> result.add(s.getObject().longValue()));
      } else { // shouldn't happen
        throw new IllegalArgumentException("the data type must be an integer type");
      }

    } else {
      try (Session session = new Session((Graph) scope.env())) {
        if (dType.equals(TInt32.DTYPE)) {
          try (Tensor<TInt32> tensorResult =
              session.runner().fetch(dims).run().get(0).expect(TInt32.DTYPE)) {
            tensorResult.data().scalars().forEach(s -> result.add((long) s.getInt()));
          }
        } else if (dType.equals(TInt64.DTYPE)) {
          try (Tensor<TInt64> tensorResult =
              session.runner().fetch(dims).run().get(0).expect(TInt64.DTYPE)) {
            tensorResult.data().scalars().forEach(s -> result.add(s.getLong()));
          }
        } else if (dType.equals(TUint8.DTYPE)) {
          try (Tensor<TUint8> tensorResult =
              session.runner().fetch(dims).run().get(0).expect(TUint8.DTYPE)) {
            tensorResult.data().scalars().forEach(s -> result.add(s.getObject().longValue()));
          }
        } else { // shouldn't happen
          throw new IllegalArgumentException("the data type must be an integer type");
        }
      }
    }
    return result.stream().mapToLong(i -> i).toArray();
  }

  /**
   * Gets the shape for the data within a Tensor
   *
   * @param tensor the tensor
   * @return the Shape of the tensor's data;
   */
  public static <T extends TNumber> Shape getShape(Tensor<T> tensor) {
    NdArray<?> data = (NdArray<?>) tensor.data();
    return data.shape();
  }

  /**
   * Determines whether two shapes are compatible.
   *
   * <p>
   *
   * <p>Two possibly-partially-defined shapes are compatible if there exists a fully-defined shape
   * that both shapes can represent. Thus, compatibility allows the shape inference code to reason
   * about partially-defined shapes. For example:
   *
   * <ul>
   *   <li><code>Shape.unknown()</code> is compatible with all shapes.
   *   <li><code>Shape(UNKNOWN_SIZE, UNKNOWN_SIZE)</code> is compatible with all two-dimensional
   *       shapes, such as <code>Shape(32, 784)</code>, and also <code>Shape.unknown()</code>. It is
   *       not compatible with, for example, <code>Shape(UNKNOWN_SIZE)</code> or <code>
   *       Shape(UNKNOWN_SIZE, UNKNOWN_SIZE, UNKNOWN_SIZE)</code>.
   *   <li><code>Shape(32, UNKNOWN_SIZE)</code> is compatible with all two-dimensional shapes with
   *       size 32 in the 0th dimension, and also <code>Shape(UNKNOWN_SIZE, UNKNOWN_SIZE)</code> and
   *       <code>Shape.unknown()</code>. It is not compatible with, for example, <code>Shape(32)
   *       </code>, <code>Shape(32, UNKNOWN_SIZE, 1)</code> or <code>Shape(64, UNKNOWN_SIZE)</code>.
   *   <li><code>Shape(32, 784)</code> is compatible with itself, and also <code>
   *       Shape(32, UNKNOWN_SIZE)</code>, <code>Shape(UNKNOWN_SIZE, 784)</code>, <code>
   *       Shape(UNKNOWN_SIZE, UNKNOWN_SIZE)</code> and <code>Shape.unknown()</code>. It is not
   *       compatible with, for example, <code>Shape(32, 1, 784)</code> or <code>Shape(UNKNOWN_SIZE)
   *       </code>.
   * </ul>
   *
   * <p>The compatibility relation is reflexive and symmetric, but not transitive. For example,
   * <code>Shape(32, 784)</code> is compatible with <code>Shape.unknown()</code>, and <code>
   * Shape.unknown()</code> is compatible with <code>Shape(4, 4)</code>, but <code>Shape(32, 784)
   * </code> is not compatible with <code>Shape(4, 4)</code>.
   *
   * <p>Compatibility is not the same as broadcasting. Compatible shapes must have the same number
   * of dimensions and for each dimension pair, one dimension has to equal the other dimensions or
   * at least one of the dimensions in the pair has to be UNKNOWN_SIZE.
   *
   * <p>Broadcasting allows different dimensions, but paired dimensions have to either be equal, or
   * one dimension must be 1. If one shape has less dimensions than another shape, the smaller shape
   * is "stretched" with dimensions of 1. See {@link org.tensorflow.op.Ops#broadcastTo}.
   *
   * @param a The first shape
   * @param b The second shape
   * @return true, if the two shapes are compatible.
   */
  public static boolean isCompatibleWith(Shape a, Shape b) {
    if (isUnknownShape(a) && isUnknownShape(b)) {
      if (a.numDimensions() != b.numDimensions()) {
        return false;
      }
      for (int i = 0; i < a.numDimensions(); i++) {
        if (!isCompatible(a.size(i), b.size(i))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Determines if a shape is an unknown shape as provided in <code>Shape.unknown()</code>.
   *
   * @param a the shape to test.
   * @return true if the shape is an unknown shape
   */
  public static boolean isUnknownShape(Shape a) {
    return a.equals(Shape.unknown());
  }

  /**
   * Reduces the shape by eliminating trailing Dimensions.
   *
   * <p>The last dimension, specified by axis, will be a product of all remaining dimensions
   *
   * @param shape the shape to squeeze
   * @param axis the axis to squeeze
   * @return the new shape
   */
  public static Shape reduce(Shape shape, int axis) {
    axis %= shape.numDimensions();
    if (axis < 0) {
      axis = shape.numDimensions() + axis;
    }
    long[] array = shape.asArray();
    if (array == null) return Shape.unknown();
    long[] newArray = new long[axis];
    System.arraycopy(array, 0, newArray, 0, axis - 1);
    long prod = array[axis - 1];
    for (int i = axis; i < array.length; i++) {
      if (array[i] != Shape.UNKNOWN_SIZE) prod *= array[i];
    }
    newArray[axis - 1] = prod;
    return Shape.of(newArray);
  }

  /**
   * Test to see if two shape dimensions are compatible.
   *
   * <p>The dimensions are compatible if either dimension is <code>Shape.UNKNOWN_SIZE</code> or both
   * dimensions are equal
   *
   * @param dim the first dimension
   * @param otherDim the second dimension
   * @return true, if both dimensions are compatible
   */
  public static boolean isCompatible(long dim, long otherDim) {
    return dim == Shape.UNKNOWN_SIZE || otherDim == Shape.UNKNOWN_SIZE || dim == otherDim;
  }
}