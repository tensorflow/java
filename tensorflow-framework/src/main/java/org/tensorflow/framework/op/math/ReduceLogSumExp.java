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
package org.tensorflow.framework.op.math;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.core.*;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.math.*;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

@Operator(group = "math")
public class ReduceLogSumExp {



  // TODO this method is defined in tf.math.reduce_logsumexp in TF Python.
  /**
   * Computes log(sum(exp(elements across dimensions of a tensor))). Reduces {@code input_tensor}
   * along the dimensions given in {@code axes}.
   *
   * <p>Reduces `{@code input} along the dimensions given in {@code axes}. Unless {@code keepdims}
   * is true, the rank of the tensor is reduced by 1 for each of the entries in {@code axes}, which
   * must be unique. If {@code keepdims} is true, the reduced dimensions are retained with length 1.
   * If {@code axes} has no entries, all dimensions are reduced, and a tensor with a single element
   * is returned. This function is more numerically stable than {@code log(sum(exp(input)))}. It
   * avoids overflows caused by taking the exp of large inputs and underflows caused by taking the
   * log of small inputs.
   *
   * @param input The tensor to reduce.
   * @param axes The dimensions to reduce. If null, reduces all dimensions. Must be in the range
   *     {@link [-rank(input_tensor), rank(input_tensor)]}.
   * @param keepDims If true, retains reduced dimensions with length 1.
   * @return The reduced tensor.
   */
  @Endpoint(name = "reduceLogSumExp")
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

  private static <T extends TFloating> Operand<T> reduceSumWithDims(
      Scope scope, Operand<T> input, int[] axes, boolean keepDims, Operand<TInt32> dims) {
    return mayReduceToScalar(
        scope, keepDims, axes, ReduceSum.create(scope, input, dims, ReduceSum.keepDims(keepDims)));
  }

  private static <T extends TFloating> Operand<T> reduceMaxWithDims(
      Scope scope, Operand<T> input, int[] axes, boolean keepDims, Operand<TInt32> dims) {
    return mayReduceToScalar(
        scope, keepDims, axes, ReduceMax.create(scope, input, dims, ReduceMax.keepDims(keepDims)));
  }

  /**
   * Sets a reduction's output shape to be a scalar if possible.
   *
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
   * @param scope the TensorFlow scope
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
