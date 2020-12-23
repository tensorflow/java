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
import org.tensorflow.types.family.TIntegral;

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
  public static <T extends TIntegral> Shape toShape(Scope scope, Operand<T> dims) {
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
  public static <T extends TIntegral> long[] getLongArray(Scope scope, Operand<T> dims) {
    if (scope.env().isEager()) {
      return getLongArray(dims.asTensor());
    }
    try (Session session = new Session((Graph)scope.env());
        TIntegral tensor = (TIntegral)session.runner().fetch(dims).run().get(0)) {
      return getLongArray(tensor);
    }
  }

  /**
   * Converts a TInt32 or TInt64 to a java long array
   *
   * @param dims the dimension tensor
   * @return the long array
   * @throws java.lang.IllegalArgumentException if the dims type is not an integer
   */
  public static <T extends TIntegral> long[] getLongArray(T dims) {
    List<Long> result = new ArrayList<>();
    if (dims instanceof TInt32) {
      ((TInt32)dims).scalars().forEach(s -> result.add((long) s.getInt()));
    } else if (dims instanceof TInt64) {
      ((TInt64)dims).scalars().forEach(s -> result.add(s.getLong()));
    } else if (dims instanceof TUint8) {
      ((TUint8)dims).scalars().forEach(s -> result.add(s.getObject().longValue()));
      } else { // shouldn't happen
        throw new IllegalArgumentException("the data type must be an integer type");
      }
    return result.stream().mapToLong(i -> i).toArray();
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
}
