/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.op.core.Concat;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.ExpandDims;
import org.tensorflow.op.core.Range;
import org.tensorflow.op.core.Rank;
import org.tensorflow.op.core.Reshape;
import org.tensorflow.op.linalg.Transpose;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Sub;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;

import java.util.Arrays;
import java.util.function.BiFunction;

/** package private Helper class for nn functions */
public class NNhelper {
  /**
   * Helper function for ops that accept and return 2d inputs of same shape.
   *
   * <p>It reshapes and transposes the inputs into a 2-D Tensor and then invokes the given function.
   * The output would be transposed and reshaped back.
   *
   * @param scope the TensorFlow Scope
   * @param input the input
   * @param computeOp  The function to wrap. Must accept the scope as the first argument, and the input as the second  argument.
   *                   (e.g. {@code org.tensorflow.op.nn.Softmax::create}
   * @param axis The axisension the operation should operate on. {@code -1} indicates the last
   *     axisension.
   * @param <T> the data type from the input and the result.
   * @return the result of the operation, the same shape as the input
   * @throws IllegalArgumentException if axis is not in the range [-rank - rank], exclusive
   */
  public static <T extends TFloating> Operand<T> wrap2DFunction(
      Scope scope, Operand<T> input, BiFunction<Scope, Operand<T>, Operand<T>> computeOp, int axis) {
    Shape shape = input.shape();
    boolean isLastDim = axis == -1 || axis == shape.numDimensions() - 1;
    if (isLastDim) {
      return computeOp.apply(scope, input);
    }

    // validate axis
    if (!(-shape.numDimensions() <= axis && axis < shape.numDimensions())) {
      throw new IllegalArgumentException(
          String.format(
              "Axis (%d) must be in the range [%d, %d] where %d is the number of axisensions in the input.",
              axis, -shape.numDimensions(), shape.numDimensions(), shape.numDimensions()));
    }

    // If axis is not the last axisension, we have to do a transpose so that we can
    // still perform the op on its  last axisension.

    // In case axis is negative (and is not last axisension -1), convert to positive
    int lAxis = Math.floorMod(axis, shape.numDimensions());
    Operand<TInt32> inputRank = Rank.create(scope, input);
    Operand<TInt32> axisOp = Constant.scalarOf(scope, lAxis);
    Operand<TInt32> one = Constant.scalarOf(scope, 1);
    Operand<TInt32> lastIndex = Sub.create(scope, inputRank, one);
    Operand<T> swappedInputs = swapAxis(scope, input, axisOp, lastIndex);
    Operand<T> output = computeOp.apply(scope, swappedInputs);
    return fixOutput(scope, output, shape, axisOp, lastIndex);
  }

  /**
   * Restores the specified axis, then reshapes the input to the provided shape.
   *
   * @param scope The TensorFlow scope
   * @param output the output
   * @param shape the desired shape
   * @param axis the axisension to move
   * @return the restored output based on the axisension and shape.
   */
  private static <T extends TFloating> Operand<T> fixOutput(
      Scope scope, Operand<T> output, Shape shape, Operand<TInt32> axis, Operand<TInt32> lastIndex) {
    Operand<T> result = swapAxis(scope, output, axis, lastIndex);
    return Reshape.create(scope, result, Constant.tensorOf(scope, shape));
  }

  /**
   * Moves the specified Axis to the last axis
   *
   * @param input the input
   * @param axis the axisension to move
   * @param lastIndex the last axisension
   * @return input with the axisension swapped to the last axisension
   */
  private static <T extends TFloating> Operand<T> swapAxis(
      Scope scope, Operand<T> input, Operand<TInt32> axis, Operand<TInt32> lastIndex) {

    Operand<TInt32> zero = Constant.scalarOf(scope, 0);
    Operand<TInt32> one = Constant.scalarOf(scope, 1);
    Operand<TInt32> minus1 = Constant.scalarOf(scope, -1);
    Operand<TInt32> range1 = Range.create(scope, zero, axis, one);
    Operand<TInt32> range2 = Range.create(scope, Add.create(scope, axis, one), lastIndex, one);
    Operand<TInt32> xDim = ExpandDims.create(scope, axis, minus1);
    Operand<TInt32> xLastIndex = ExpandDims.create(scope, lastIndex, minus1);

    return Transpose.create(
        scope, input, Concat.create(scope, Arrays.asList(range1, xLastIndex, range2, xDim), zero));
  }
}
