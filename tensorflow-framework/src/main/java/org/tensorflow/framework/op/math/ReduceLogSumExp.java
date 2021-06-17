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
package org.tensorflow.framework.op.math;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Range;
import org.tensorflow.op.core.Rank;
import org.tensorflow.op.core.ReduceMax;
import org.tensorflow.op.core.ReduceSum;
import org.tensorflow.op.core.Reshape;
import org.tensorflow.op.core.Select;
import org.tensorflow.op.core.StopGradient;
import org.tensorflow.op.core.ZerosLike;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Exp;
import org.tensorflow.op.math.IsFinite;
import org.tensorflow.op.math.Log;
import org.tensorflow.op.math.Sub;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TFloating;

/** Reduce Log Sum Exp Operations */
public class ReduceLogSumExp {

  /**
   * Computes log(sum(exp(elements across dimensions of a tensor))). Reduces {@code input_tensor}
   * along the dimensions given in {@code axes}.
   *
   * <p>Reduces {@code input} along the dimensions given in {@code axes}. Unless {@code keepdims} is
   * true, the rank of the tensor is reduced by 1 for each of the entries in {@code axes}, which
   * must be unique. If {@code keepdims} is true, the reduced dimensions are retained with length 1.
   * If {@code axes} has no entries, all dimensions are reduced, and a tensor with a single element
   * is returned. This function is more numerically stable than {@code log(sum(exp(input)))}. It
   * avoids overflows caused by taking the exp of large inputs and underflows caused by taking the
   * log of small inputs.
   *
   * @param scope The TensorFlow scope
   * @param input The tensor to reduce.
   * @param axes The dimensions to reduce. If null, reduces all dimensions. Must be in the range
   *     {@code [-rank(input_tensor), rank(input_tensor)]}.
   * @param keepDims If true, retains reduced dimensions with length 1.
   * @param <T> the data type for the input and the result
   * @return The reduced tensor.
   */
  public static <T extends TFloating> Operand<T> reduceLogSumExp(
      Scope scope, Operand<T> input, int[] axes, boolean keepDims) {
    Operand<TInt32> reduceDims = reductionDims(scope, input, axes);
    Operand<T> rawMax = reduceMaxWithDims(scope, input, axes, keepDims, reduceDims);
    Operand<T> myMax =
        StopGradient.create(
            scope,
            Select.create(
                scope, IsFinite.create(scope, rawMax), rawMax, ZerosLike.create(scope, rawMax)));

    Operand<T> result =
        Log.create(
            scope,
            reduceSumWithDims(
                scope,
                Exp.create(scope, Sub.create(scope, input, myMax)),
                axes,
                keepDims,
                reduceDims));

    if (!keepDims) {
      myMax = Reshape.create(scope, myMax, org.tensorflow.op.core.Shape.create(scope, result));
    }
    result = Add.create(scope, result, myMax);
    return mayReduceToScalar(scope, keepDims, axes, result);
  }

  /**
   * @param scope The TensorFlow scope
   * @param input The tensor to reduce.
   * @param axes The dimensions to reduce. If null, reduces all dimensions. Must be in the range
   *     {@code [-rank(input_tensor), rank(input_tensor)]}.
   * @param keepDims If true, retains reduced dimensions with length 1.
   * @param dims the reduction dimensions
   * @param <T> the data type for the input and the result
   * @return the reduced sum
   */
  private static <T extends TFloating> Operand<T> reduceSumWithDims(
      Scope scope, Operand<T> input, int[] axes, boolean keepDims, Operand<TInt32> dims) {
    return mayReduceToScalar(
        scope, keepDims, axes, ReduceSum.create(scope, input, dims, ReduceSum.keepDims(keepDims)));
  }

  /**
   * @param scope The TensorFlow scope
   * @param input The tensor to reduce.
   * @param axes The dimensions to reduce. If null, reduces all dimensions. Must be in the range
   *     {@code [-rank(input_tensor), rank(input_tensor)]}.
   * @param keepDims If true, retains reduced dimensions with length 1.
   * @param dims the reduction dimensions
   * @param <T> the data type for the input and the result
   * @return the reduced maximum input value
   */
  private static <T extends TFloating> Operand<T> reduceMaxWithDims(
      Scope scope, Operand<T> input, int[] axes, boolean keepDims, Operand<TInt32> dims) {
    return mayReduceToScalar(
        scope, keepDims, axes, ReduceMax.create(scope, input, dims, ReduceMax.keepDims(keepDims)));
  }

  /**
   * Sets a reduction's output shape to be a scalar if possible.
   *
   * @param scope The TensorFlow scope
   * @param keepDims If true, retains reduced dimensions with length 1.
   * @param axes The dimensions to reduce. If null, reduces all dimensions. Must be in the range
   *     {@code [-rank(input_tensor), rank(input_tensor)]}.
   * @param output the output, possibly reduced to a scalar
   * @param <T> the datat type of the Operands.
   * @return the operand, possibly reduced to a scalar.
   */
  private static <T extends TFloating> Operand<T> mayReduceToScalar(
      Scope scope, boolean keepDims, int[] axes, Operand<T> output) {

    if ((output.shape().numDimensions() == Shape.UNKNOWN_SIZE
            || output.shape().hasUnknownDimension())
        && !keepDims
        && axes == null) {
      return Reshape.create(scope, output, Constant.tensorOf(scope, Shape.scalar()));
    } else {
      return output;
    }
  }

  /**
   * Reduce dimensions based on axis
   *
   * @param input the input
   * @param axes he dimensions to reduce, may be null
   * @return the dimensions to be reduced.
   */
  private static <T extends TFloating> Operand<TInt32> reductionDims(
      Scope scope, Operand<T> input, int[] axes) {
    if (axes != null) {
      return Constant.vectorOf(scope, axes);
    }
    long rank = input.shape().numDimensions();
    if (rank != Shape.UNKNOWN_SIZE) {
      int[] dims = new int[(int) rank];
      for (int i = 0; i < rank; i++) {
        dims[i] = i;
      }
      return Constant.vectorOf(scope, dims);

    } else {
      return Range.create(
          scope,
          Constant.scalarOf(scope, 0),
          Rank.create(scope, input),
          Constant.scalarOf(scope, 1));
    }
  }
}
