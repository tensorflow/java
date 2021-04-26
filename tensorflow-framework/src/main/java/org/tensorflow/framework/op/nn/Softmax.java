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
package org.tensorflow.framework.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.core.*;
import org.tensorflow.op.linalg.Transpose;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Sub;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;

import java.util.Arrays;

/**
 * Higher level operation for Softmax. This class will move the desired axis to the last axis, if
 * necessary, before calling the low level tf.nn.softmax method.
 */

/* TODO this is a higher level of abstraction from the low level softmax
this method is defined as tf.nn.softmax in TF Python
Should this be defined here? */
@Operator(group = "nn")
public class Softmax {

  /**
   * Calculates a Softmax operation. If the exis is not the last dimension, then the input axis is
   * moved to the last axis berfore calling tf.nn.softmax, then restored before returning.
   *
   * @param scope The TensorFlow scope
   * @param input the input
   * @param axis the axis
   * @return the softmax of the input for the specified axis.
   * @throws IllegalArgumentException if axis is not in the range [-rank - rank], exclusive
   * @param <T> the data type for the input and result
   */
  @Endpoint(name = "softmax")
  public static <T extends TFloating> Operand<T> softmax(Scope scope, Operand<T> input, int axis) {
    Shape shape = input.shape();
    boolean isLastDim = axis == -1 || axis == shape.numDimensions() - 1;
    if (isLastDim) {
      return org.tensorflow.op.nn.Softmax.create(scope, input);
    }

    if (axis <= -shape.numDimensions() || axis >= shape.numDimensions()) {
      throw new IllegalArgumentException(
          String.format(
              "Axis (%d) must be in the range [%d, %d] where %d is the number of dimensions in the input.",
              axis, -shape.numDimensions(), shape.numDimensions(), shape.numDimensions()));
    }

    int dim = Math.floorMod(axis, shape.numDimensions());
    Operand<TInt32> rank = Rank.create(scope, input);
    Operand<TInt32> dimOp = Constant.scalarOf(scope, dim);
    Operand<TInt32> one = Constant.scalarOf(scope, 1);
    Operand<TInt32> lastIndex = Sub.create(scope, rank, one);
    Operand<T> swappedInputs = swapAxis(scope, input, dimOp, lastIndex);
    Operand<T> output = org.tensorflow.op.nn.Softmax.create(scope, swappedInputs);
    return fixOutput(scope, output, shape, dimOp, lastIndex);
  }

  /**
   * Restores the specified axis, then reshapes the input to the provided shaoe.
   *
   * @param scope The TensorFlow scope
   * @param output the output
   * @param shape the desired shape
   * @param dim the dimension to move
   * @return the restored output based on the dimension and shape.
   */
  private static <T extends TFloating> Operand<T> fixOutput(
      Scope scope, Operand<T> output, Shape shape, Operand<TInt32> dim, Operand<TInt32> lastIndex) {

    Operand<T> result = swapAxis(scope, output, dim, lastIndex);
    return Reshape.create(scope, result, Constant.tensorOf(scope, shape));
  }

  /**
   * Moves the specified Axis to the last axis
   *
   * @param input the input
   * @param dim the dimension to move
   * @param lastIndex the last dimension
   * @return input with the dimension swapped to the last dimension
   */
  private static <T extends TFloating> Operand<T> swapAxis(
      Scope scope, Operand<T> input, Operand<TInt32> dim, Operand<TInt32> lastIndex) {

    Operand<TInt32> zero = Constant.scalarOf(scope, 0);
    Operand<TInt32> one = Constant.scalarOf(scope, 1);
    return Transpose.create(
        scope,
        input,
        Concat.create(
            scope,
            Arrays.asList(
                Range.create(scope, zero, dim, one),
                Range.create(scope, Add.create(scope, dim, one), lastIndex, one),
                dim),
            zero));
  }
}
