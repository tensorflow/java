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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.framework.op.linalg.MatMul;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.AssertThat;
import org.tensorflow.op.core.Concat;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Gather;
import org.tensorflow.op.core.Range;
import org.tensorflow.op.core.Rank;
import org.tensorflow.op.core.ReduceProd;
import org.tensorflow.op.core.Reshape;
import org.tensorflow.op.core.Select;
import org.tensorflow.op.core.SetDiff1d;
import org.tensorflow.op.core.Slice;
import org.tensorflow.op.core.Stack;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.linalg.Transpose;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.GreaterEqual;
import org.tensorflow.op.math.Less;
import org.tensorflow.op.math.Sub;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;

/** tensor contraction Operations */
public class TensorDot {

  /**
   * Transpose and reshape the input for contraction op.
   *
   * <p>This method is helpful in reducing {@code math.tensordot} to {@code math_ops.matmul} using
   * {@code array_ops.transpose} and {@code array_ops.reshape}. The method takes a tensor and
   * performs the correct transpose and reshape operation for a given set of indices. It returns the
   * reshaped tensor as well as a list of indices necessary to reshape the tensor again after matrix
   * multiplication.
   *
   * @param <T> the type of Operand
   * @param a the Tensor
   * @param axis unique indices specifying valid axes of {@code a}.
   * @param flipped whether to flip the dimensions or not
   * @return A tuple (reshapedA, freeDims, freeDimsStatic) where reshapedA is a reshaped to allow
   *     contraction via matmul, freeDims is a TInt32 Operand, depending on whether the shape of a
   *     is fully specified, and freeDimsStatic is either a list of integers and null values, or
   *     None, representing the inferred shape of the free dimensions
   */
  private static <T extends TNumber> Object[] tensordotReshape(
      Scope scope, Operand<T> a, Operand<TInt32> axis, boolean flipped) {
    Shape aShape = a.shape();

    if (!aShape.hasUnknownDimension()) { // calculate using values
      long[] aShapeDims = aShape.asArray();
      if (aShapeDims == null) aShapeDims = new long[0];
      long[] aDimsIndex = new long[aShapeDims.length];
      for (int i = 0; i < aDimsIndex.length; i++) aDimsIndex[i] = i;

      // get int array from axis Operand
      int[] iAxes = getIntArray(scope, axis);
      // Convert negative axes to positive
      for (int i = 0; i < iAxes.length; i++)
        iAxes[i] = iAxes[i] >= 0 ? iAxes[i] : Math.floorMod(iAxes[i], iAxes.length);

      // convert integer axis to long axis
      long[] lAxes = Arrays.stream(iAxes).mapToLong(i -> i).toArray();

      // create list of the axes, dims, and free axes
      List<Long> axesList = Arrays.stream(lAxes).boxed().collect(Collectors.toList());
      List<Long> freeList = Arrays.stream(aDimsIndex).boxed().collect(Collectors.toList());
      freeList.removeAll(axesList);

      // create array of free dims
      long[] free = freeList.stream().mapToLong(i -> i).toArray();
      long[] freeDims = new long[free.length];
      for (int i = 0; i < free.length; i++) freeDims[i] = aShapeDims[(int) free[i]];

      // Calculate the free dim by doing a reduce prod
      long prodFree = 1;
      for (long i : freeDims) {
        prodFree *= i;
      }

      // calculate the used dims by doing a reduce prod
      long prodAxis = 1;
      for (long i : lAxes) {
        prodAxis *= aShapeDims[(int) i];
      }

      // setup the permutations array for the transpose
      long[] perm = new long[freeDims.length + lAxes.length];
      Shape newShape;
      if (flipped) {
        System.arraycopy(lAxes, 0, perm, 0, lAxes.length);
        System.arraycopy(free, 0, perm, lAxes.length, free.length);
        newShape = Shape.of(prodAxis, prodFree);
      } else {
        System.arraycopy(free, 0, perm, 0, free.length);
        System.arraycopy(lAxes, 0, perm, freeDims.length, lAxes.length);
        newShape = Shape.of(prodFree, prodAxis);
      }

      Operand<T> aTrans;
      long[] arrange = new long[lAxes.length];
      for (int i = 0; i < arrange.length; i++) arrange[i] = i;

      // if the permutations is not equals to the natural order of the dims, then do a transpose
      if (!Arrays.equals(perm, arrange)) {
        aTrans = Transpose.create(scope, a, Constant.vectorOf(scope, perm));
      } else {
        aTrans = a;
      }

      // reshape the final result to the new Shape, if necessary
      Operand<T> aReshaped =
          aTrans.asOutput().shape().equals(newShape)
              ? aTrans
              : Reshape.create(scope, aTrans, Constant.vectorOf(scope, newShape.asArray()));
      // return a tuple for the reshaped Operand, and Operand for the free dimensions, and a long
      // array for the free dimensions
      return new Object[] {aReshaped, Constant.vectorOf(scope, freeDims), freeDims};

    } else { // calculate dynamically

      long[] freeDimsStatic = null;
      Operand<TInt32> one = Constant.scalarOf(scope, 1);
      Operand<TInt32> minusOne = Constant.scalarOf(scope, -1);
      Operand<TInt32> zero = Constant.scalarOf(scope, 0);
      org.tensorflow.op.core.Shape<TInt32> tShape = org.tensorflow.op.core.Shape.create(scope, a);
      Operand<TInt32> axesT;
      Operand<TInt32> freeT;
      if (aShape.numDimensions()
          != Shape.UNKNOWN_SIZE) { // we know the rank, but there are unknown dimensions
        long[] aShapeDims = aShape.asArray();
        if (aShapeDims == null) aShapeDims = new long[0];

        // get int array from axis Operand
        int[] iAxes = getIntArray(scope, axis);
        // Convert negative axes to positive
        for (int i = 0; i < iAxes.length; i++)
          iAxes[i] = iAxes[i] >= 0 ? iAxes[i] : Math.floorMod(iAxes[i], iAxes.length);

        // convert integer axis to long axis
        long[] lAxes = Arrays.stream(iAxes).mapToLong(i -> i).toArray();

        // create list of the axes, dims, and free axes
        List<Long> axesList = Arrays.stream(lAxes).boxed().collect(Collectors.toList());
        List<Long> dimsList = Arrays.stream(aShapeDims).boxed().collect(Collectors.toList());
        List<Long> freeList = new ArrayList<>(axesList);
        freeList.removeAll(dimsList);

        // create array of free dims
        long[] freeDims = freeList.stream().mapToLong(i -> i).toArray();
        freeDimsStatic = freeDims;

        axesT = Constant.vectorOf(scope, iAxes);
        freeT = Cast.create(scope, Constant.vectorOf(scope, freeDims), TInt32.class);

      } else { // we don't know the rank yet
        Rank rank = Rank.create(scope, a);

        // convert axis to positive
        axesT =
            Select.create(
                scope,
                GreaterEqual.create(scope, axis, Constant.scalarOf(scope, 0)),
                axis,
                Add.create(scope, axis, rank));

        SetDiff1d<TInt32, TInt32> diff =
            SetDiff1d.create(
                scope, Range.create(scope, Constant.scalarOf(scope, 0), rank, one), axesT);
        freeT = diff.out();
      }
      Operand<TInt32> freeDims = Gather.create(scope, tShape, freeT, zero);
      Operand<TInt32> axesDims = Gather.create(scope, tShape, axesT, zero);
      Operand<TInt32> prodFreeDims = ReduceProd.create(scope, freeDims, minusOne);
      Operand<TInt32> prodAxesDims = ReduceProd.create(scope, axesDims, minusOne);
      Operand<TInt32> perm;
      Operand<TInt32> newShape;
      if (flipped) {
        perm = Concat.create(scope, Arrays.asList(axesT, freeT), zero);
        newShape = Stack.create(scope, Arrays.asList(prodAxesDims, prodFreeDims));
      } else {
        perm = Concat.create(scope, Arrays.asList(freeT, axesT), zero);
        newShape = Stack.create(scope, Arrays.asList(prodFreeDims, prodAxesDims));
      }
      Operand<T> aReshaped = Reshape.create(scope, Transpose.create(scope, a, perm), newShape);
      return new Object[] {aReshaped, freeDims, freeDimsStatic};
    }
  }

  /**
   * Gets an int array from an Operand&lt;TInt32&gt operand.
   *
   * @param axes the Operand to fetch the values
   * @return the int array from an Operand&lt;TInt32&gt;
   */
  private static int[] getIntArray(Scope scope, Operand<TInt32> axes) {
    List<Integer> result = new ArrayList<>();
    if (scope.env().isEager()) {
      axes.asTensor().scalars().forEach(s -> result.add(s.getInt()));
    } else {
      try (Session session = new Session((Graph) scope.env());
          TInt32 tensor = (TInt32) session.runner().fetch(axes).run().get(0)) {
        tensor.scalars().forEach(s -> result.add(s.getInt()));
      }
    }
    return result.stream().mapToInt(i -> i).toArray();
  }

  /**
   * Generates two sets of contraction axes for the two tensor arguments.
   *
   * @param a the Operand to analyze
   * @param axis the axes
   * @param <T> the data type for the Operand
   * @return the contraction axes
   */
  @SuppressWarnings("unchecked")
  private static <T extends TNumber> Operand<TInt32>[] tensordotAxes(
      Scope scope, Operand<T> a, int axis) {
    Shape aShape = a.asOutput().shape();
    if (axis < 0) {
      throw new IllegalArgumentException("'axis' must be at least 0.");
    }
    int rank = aShape.numDimensions();
    Operand<TInt32>[] result = new Operand[2];
    if (rank != Shape.UNKNOWN_SIZE) {
      if (axis > rank) {
        throw new IllegalArgumentException(
            String.format(
                "'axis' must not be larger than the number of dimensions of tensor %s.", rank));
      }
      int min = rank - axis;
      int postRange = rank - min;
      int[] postAxis = new int[postRange];
      for (int i = 0; i < postRange; i++) postAxis[i] = i + min;

      int[] preAxis = new int[axis];
      for (int i = 0; i < axis; i++) preAxis[i] = i;

      result[0] = Constant.vectorOf(scope, postAxis);
      result[1] = Constant.vectorOf(scope, preAxis);
    } else {
      Rank rankT = Rank.create(scope, a);
      Constant<TInt32> axisT = Constant.scalarOf(scope, axis);
      Constant<TInt32> one = Constant.scalarOf(scope, 1);
      Constant<TInt32> zero = Constant.scalarOf(scope, 0);
      AssertThat assertion =
          AssertThat.create(
              scope,
              Less.create(scope, axisT, rankT),
              Arrays.asList(
                  Constant.scalarOf(
                      scope, "'axes' must not be larger than the number of dimensions of tensor "),
                  rankT));
      Scope scope1 = scope.withControlDependencies(Collections.singletonList(assertion));
      result[0] = Range.create(scope1, Sub.create(scope, rankT, axisT), rankT, one);
      result[1] = Range.create(scope1, zero, axisT, one);
    }
    return result;
  }

  /**
   * Generates two sets of contraction axes for the two tensor arguments.
   *
   * @param a the Operand to analyze
   * @param axes the axes
   * @param <T> the data type for the Operand
   * @return the contraction axes
   */
  @SuppressWarnings({"unchecked", "unused"})
  private static <T extends TNumber> Operand<TInt32>[] tensordotAxes(
      Scope scope, Operand<T> a, int[] axes) {
    if (axes.length != 2)
      throw new IllegalArgumentException(
          "'axes' must have length 1 or 2, provided with " + axes.length);
    int[] aAxis = new int[] {axes[0]};
    int[] bAxis = new int[] {axes[1]};
    Operand<TInt32>[] result = new Operand[2];
    result[0] = Constant.vectorOf(scope, aAxis);
    result[1] = Constant.vectorOf(scope, bAxis);

    return result;
  }

  /**
   * Generates two sets of contraction axes for the two tensor arguments.
   *
   * @param a the Operand to analyze
   * @param axes the axes
   * @param <T> the data type for the Operand
   * @return the contraction axes
   */
  @SuppressWarnings({"unchecked", "unused"})
  private static <T extends TNumber> Operand<TInt32>[] tensordotAxes(
      Scope scope, Operand<T> a, int[][] axes) {
    if (axes.length != 2)
      throw new IllegalArgumentException(
          "'axes' must have length 1 or 2, provided with " + axes.length);
    int[] aAxis = axes[0];
    int[] bAxis = axes[1];
    if (aAxis.length != bAxis.length)
      throw new IllegalArgumentException(
          String.format(
              "Different number of contraction axes 'a' and 'b', %d != %d",
              aAxis.length, bAxis.length));
    Operand<TInt32>[] result = new Operand[2];
    result[0] = Constant.vectorOf(scope, aAxis);
    result[1] = Constant.vectorOf(scope, bAxis);
    return result;
  }

  /**
   * Generates two sets of contraction axes for the two tensor arguments.
   *
   * @param a the Operand to analyze
   * @param axes the axes
   * @param <T> the data type for the Operand
   * @return the contraction axes
   */
  @SuppressWarnings({"unchecked", "unused"})
  private static <T extends TNumber> Operand<TInt32>[] tensordotAxes(
      Scope scope, Operand<T> a, Operand<TInt32> axes) {

    Constant<TInt32> one = Constant.scalarOf(scope, 1);
    Constant<TInt32> zero = Constant.scalarOf(scope, 0);
    Operand<TInt32>[] result = new Operand[2];
    result[0] =
        Slice.create(
            scope,
            axes,
            Cast.create(scope, zero, TInt32.class),
            Cast.create(scope, one, TInt32.class));
    result[1] =
        Slice.create(
            scope,
            axes,
            Cast.create(scope, one, TInt32.class),
            Cast.create(scope, one, TInt32.class));
    return result;
  }

  /**
   * Tensor contraction of a and b along specified axes and outer product.
   *
   * <p>Tensordot (also known as tensor contraction) sums the product of elements from {@code a} and
   * {@code b} over the indices specified by {@code a_axes} and {@code b_axes}. The lists {@code
   * a_axes} and {@code b_axes} specify those pairs of axes along which to contract the tensors. The
   * axis {@code a_axes[i]} of {@code a} must have the same dimension as axis {@code b_axes[i]} of
   * {@code b} for all {@code i} in {@code range(0, len(a_axes))}. The lists {@code a_axes} and
   * {@code b_axes} must have identical length and consist of unique integers that specify valid
   * axes for each of the tensors. Additionally outer product is supported by passing {@code
   * axes=0}.
   *
   * <p>This operation corresponds to {@code numpy.tensordot(a, b, axes)}.
   *
   * <p>Example 1: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = 1} is
   * equivalent to matrix multiplication.
   *
   * <p>Example 2: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = [[1],
   * [0]]} is equivalent to matrix multiplication.
   *
   * <p>Example 3: When {@code a} and {@code b} are matrices (order 2), the case {@code axes=0}
   * gives the outer product, a tensor of order 4.
   *
   * <p>Example 4: Suppose that <i>a<sub>ijk</sub></i> and <i>b<sub>lmn</sub></i> represent two
   * tensors of order 3. Then, {@code contract(a, b, [[0], [2]])} is the order 4 tensor
   * <i>c<sub>jklm</sub></i> whose entry corresponding to the indices <i>(j,k,l,m)</i> is given by:
   * <i> c<sub>jklm</sub> = &Sigma;<sub>i</sub> a<sub>ijk</sub> b<sub>lmi</sub> </i>.
   *
   * <p>In general, {@code order(c) = order(a) + order(b) - 2*len(axes[0])}.
   *
   * @param scope current scope
   * @param a {@code Operand} of type {@code TFloat32} or {@code TFloat64}.
   * @param b {@code Operand} with the same type as {@code a}.
   * @param axis sum over the last N axes of a and the first N axes of b in order. If {@code
   *     axis=0}, computes the outer product between {@code a} and {@code b}.
   * @param <T> the datatype of the Operands, must be either TFloat32 or TFloat64
   * @return A {@code Operand} with the same type as {@code a}.
   * @throws IllegalArgumentException if a is not a float32 or float64 data type and if a and b are
   *     not the same data type
   */
  public static <T extends TFloating> Operand<T> tensordot(
      Scope scope, Operand<T> a, Operand<T> b, int axis) {

    Operand<TInt32>[] abAxis = tensordotAxes(scope, a, axis);
    Operand<TInt32> aAxis = abAxis[0];
    Operand<TInt32> bAxis = abAxis[1];
    return tensordot(scope, a, b, aAxis, bAxis);
  }

  /**
   * Tensor contraction of a and b along specified axes and outer product.
   *
   * <p>Tensordot (also known as tensor contraction) sums the product of elements from {@code a} and
   * {@code b} over the indices specified by {@code a_axes} and {@code b_axes}. The lists {@code
   * a_axes} and {@code b_axes} specify those pairs of axes along which to contract the tensors. The
   * axis {@code a_axes[i]} of {@code a} must have the same dimension as axis {@code b_axes[i]} of
   * {@code b} for all {@code i} in {@code range(0, len(a_axes))}. The lists {@code a_axes} and
   * {@code b_axes} must have identical length and consist of unique integers that specify valid
   * axes for each of the tensors. Additionally outer product is supported by passing {@code
   * axes=0}.
   *
   * <p>This operation corresponds to {@code numpy.tensordot(a, b, axes)}.
   *
   * <p>Example 1: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = 1} is
   * equivalent to matrix multiplication.
   *
   * <p>Example 2: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = [[1],
   * [0]]} is equivalent to matrix multiplication.
   *
   * <p>Example 3: When {@code a} and {@code b} are matrices (order 2), the case {@code axes=0}
   * gives the outer product, a tensor of order 4.
   *
   * <p>Example 4: Suppose that <i>a<sub>ijk</sub></i> and <i>b<sub>lmn</sub></i> represent two
   * tensors of order 3. Then, {@code contract(a, b, [[0], [2]])} is the order 4 tensor
   * <i>c<sub>jklm</sub></i> whose entry corresponding to the indices <i>(j,k,l,m)</i> is given by:
   *
   * <p><i> c<sub>jklm</sub> = &Sigma;<sub>i</sub> a<sub>ijk</sub> b<sub>lmi</sub> </i>.
   *
   * <p>In general, {@code order(c) = order(a) + order(b) - 2*len(axes[0])}.
   *
   * <p>
   *
   * @param scope current scope
   * @param a {@code Operand} of type {@code TFloat32} or {@code TFloat64}.
   * @param b {@code Operand} with the same type as {@code a}.
   * @param axes If axes is a scalar, sum over the last N axes of a and the first N axes of b in
   *     order. If axes is a list, the first and second row contain the set of unique integers
   *     specifying axes along which the contraction is computed, for {@code a} and {@code b},
   *     respectively. The number of axes for {@code a} and {@code b} must be equal. If {@code
   *     axis=0}, computes the outer product between {@code a} and {@code b}.
   * @param <T> the datatype of the Operands, must be either TFloat32 or TFloat64
   * @return A {@code Operand} with the same type as {@code a}.
   * @throws IllegalArgumentException if a is not a float32 or float64 data type and if a and b are
   *     not the same data type
   */
  public static <T extends TFloating> Operand<T> tensordot(
      Scope scope, Operand<T> a, Operand<T> b, Operand<TInt32> axes) {

    Operand<TInt32>[] abAxis = tensordotAxes(scope, a, axes);
    Operand<TInt32> aAxis = abAxis[0];
    Operand<TInt32> bAxis = abAxis[1];

    return tensordot(scope, a, b, aAxis, bAxis);
  }

  /**
   * Tensor contraction of a and b along specified axes and outer product.
   *
   * <p>Tensordot (also known as tensor contraction) sums the product of elements from {@code a} and
   * {@code b} over the indices specified by {@code a_axes} and {@code b_axes}. The lists {@code
   * a_axes} and {@code b_axes} specify those pairs of axes along which to contract the tensors. The
   * axis {@code a_axes[i]} of {@code a} must have the same dimension as axis {@code b_axes[i]} of
   * {@code b} for all {@code i} in {@code range(0, len(a_axes))}. The lists {@code a_axes} and
   * {@code b_axes} must have identical length and consist of unique integers that specify valid
   * axes for each of the tensors. Additionally outer product is supported by passing {@code
   * axes=0}.
   *
   * <p>This operation corresponds to {@code numpy.tensordot(a, b, axes)}.
   *
   * <p>Example 1: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = 1} is
   * equivalent to matrix multiplication.
   *
   * <p>Example 2: When {@code a} and{@code b} are matrices (order 2), the case {@code axes = [[1],
   * [0]]} is equivalent to matrix multiplication.
   *
   * <p>Example 3: When {@code a} and {@code b} are matrices (order 2), the case {@code axes=0}
   * gives the outer product, a tensor of order 4.
   *
   * <p>Example 4: Suppose that <i>a<sub>ijk</sub></i> and <i>b<sub>lmn</sub></i> represent two
   * tensors of order 3. Then, {@code contract(a, b, [[0], [2]])} is the order 4 tensor
   * <i>c<sub>jklm</sub></i> whose entry corresponding to the indices <i>(j,k,l,m)</i> is given by:
   *
   * <p><i> c<sub>jklm</sub> = &Sigma;<sub>i</sub> a<sub>ijk</sub> b<sub>lmi</sub> </i>.
   *
   * <p>In general, {@code order(c) = order(a) + order(b) - 2*len(axes[0])}.
   *
   * <p>
   *
   * @param scope current scope
   * @param a {@code Operand} of type {@code TFloat32} or {@code TFloat64}.
   * @param b {@code Operand} with the same type as {@code a}.
   * @param axes the first and second row contain the set of unique integers specifying axes along
   *     which the contraction is computed, for {@code a} and {@code b}, respectively. The number of
   *     axes for {@code a} and {@code b} must be equal. I
   * @param <T> the datatype of the Operands, must be either TFloat32 or TFloat64
   * @return A {@code Operand} with the same type as {@code a}.
   * @throws IllegalArgumentException if a is not a float32 or float64 data type and if a and b are
   *     not the same data type
   */
  public static <T extends TFloating> Operand<T> tensordot(
      Scope scope, Operand<T> a, Operand<T> b, int[] axes) {

    Operand<TInt32>[] abAxis = tensordotAxes(scope, a, axes);
    Operand<TInt32> aAxis = abAxis[0];
    Operand<TInt32> bAxis = abAxis[1];

    return tensordot(scope, a, b, aAxis, bAxis);
  }

  /**
   * Tensor contraction of a and b along specified axes and outer product.
   *
   * <p>Tensordot (also known as tensor contraction) sums the product of elements from {@code a} and
   * {@code b} over the indices specified by {@code a_axes} and {@code b_axes}. The lists {@code
   * a_axes} and {@code b_axes} specify those pairs of axes along which to contract the tensors. The
   * axis {@code a_axes[i]} of {@code a} must have the same dimension as axis {@code b_axes[i]} of
   * {@code b} for all {@code i} in {@code range(0, len(a_axes))}. The lists {@code a_axes} and
   * {@code b_axes} must have identical length and consist of unique integers that specify valid
   * axes for each of the tensors. Additionally outer product is supported by passing {@code
   * axes=0}.
   *
   * <p>This operation corresponds to {@code numpy.tensordot(a, b, axes)}.
   *
   * <p>Example 1: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = 1} is
   * equivalent to matrix multiplication.
   *
   * <p>Example 2: When {@code a} and{@code b} are matrices (order 2), the case {@code axes = [[1],
   * [0]]} is equivalent to matrix multiplication.
   *
   * <p>Example 3: When {@code a} and {@code b} are matrices (order 2), the case {@code axes=0}
   * gives the outer product, a tensor of order 4.
   *
   * <p>Example 4: Suppose that <i>a<sub>ijk</sub></i> and <i>b<sub>lmn</sub></i> represent two
   * tensors of order 3. Then, {@code contract(a, b, [[0], [2]])} is the order 4 tensor
   * <i>c<sub>jklm</sub></i> whose entry corresponding to the indices <i>(j,k,l,m)</i> is given by:
   *
   * <p><i> c<sub>jklm</sub> = &Sigma;<sub>i</sub> a<sub>ijk</sub> b<sub>lmi</sub> </i>.
   *
   * <p>In general, {@code order(c) = order(a) + order(b) - 2*len(axes[0])}.
   *
   * <p>
   *
   * @param scope current scope
   * @param a {@code Operand} of type {@code TFloat32} or {@code TFloat64}.
   * @param b {@code Operand} with the same type as {@code a}.
   * @param axes the first and second row contain the set of unique integers specifying axes along
   *     which the contraction is computed, for {@code a} and {@code b}, respectively. The number of
   *     axes for {@code a} and {@code b} must be equal. I
   * @param <T> the datatype of the Operands, must be either TFloat32 or TFloat64
   * @return A {@code Operand} with the same type as {@code a}.
   * @throws IllegalArgumentException if a is not a float32 or float64 data type and if a and b are
   *     not the same data type
   */
  public static <T extends TFloating> Operand<T> tensordot(
      Scope scope, Operand<T> a, Operand<T> b, int[][] axes) {

    Operand<TInt32>[] abAxis = tensordotAxes(scope, a, axes);
    Operand<TInt32> aAxis = abAxis[0];
    Operand<TInt32> bAxis = abAxis[1];

    return tensordot(scope, a, b, aAxis, bAxis);
  }

  /**
   * Tensor contraction of a and b along specified axes and outer product.
   *
   * <p>Tensordot (also known as tensor contraction) sums the product of elements from {@code a} and
   * {@code b} over the indices specified by {@code a_axes} and {@code b_axes}. The lists {@code
   * a_axes} and {@code b_axes} specify those pairs of axes along which to contract the tensors. The
   * axis {@code a_axes[i]} of {@code a} must have the same dimension as axis {@code b_axes[i]} of
   * {@code b} for all {@code i} in {@code range(0, len(a_axes))}. The lists {@code a_axes} and
   * {@code b_axes} must have identical length and consist of unique integers that specify valid
   * axes for each of the tensors. Additionally outer product is supported by passing {@code
   * axes=0}.
   *
   * <p>This operation corresponds to {@code numpy.tensordot(a, b, axes)}.
   *
   * <p>Example 1: When {@code a} and {@code b} are matrices (order 2), the case {@code axes = 1} is
   * equivalent to matrix multiplication.
   *
   * <p>Example 2: When {@code a} and{@code b} are matrices (order 2), the case {@code axes = [[1],
   * [0]]} is equivalent to matrix multiplication.
   *
   * <p>Example 3: When {@code a} and {@code b} are matrices (order 2), the case {@code axes=0}
   * gives the outer product, a tensor of order 4.
   *
   * <p>Example 4: Suppose that <i>a<sub>ijk</sub></i> and <i>b<sub>lmn</sub></i> represent two
   * tensors of order 3. Then, {@code contract(a, b, [[0], [2]])} is the order 4 tensor
   * <i>c<sub>jklm</sub></i> whose entry corresponding to the indices <i>(j,k,l,m)</i> is given by:
   *
   * <p><i> c<sub>jklm</sub> = &Sigma;<sub>i</sub> a<sub>ijk</sub> b<sub>lmi</sub> </i>.
   *
   * <p>In general, {@code order(c) = order(a) + order(b) - 2*len(axes[0])}.
   *
   * <p>
   *
   * @param scope current scope
   * @param a {@code Operand} of type {@code TFloat32} or {@code TFloat64}.
   * @param b {@code Operand} with the same type as {@code a}.
   * @param aAxis axes for the a Operand
   * @param bAxis axes for the b Operand
   * @param <T> the datatype of the Operands, must be either TFloat32 or TFloat64
   * @return A {@code Operand} with the same type as {@code a}.
   * @throws IllegalArgumentException if a is not a float32 or float64 data type and if a and b are
   *     not the same data type
   */
  @SuppressWarnings({"unchecked", "unused"})
  public static <T extends TFloating> Operand<T> tensordot(
      Scope scope, Operand<T> a, Operand<T> b, Operand<TInt32> aAxis, Operand<TInt32> bAxis) {

    if (a.type().equals(TBfloat16.class) || a.type().equals(TFloat16.class)) {
      throw new IllegalArgumentException(
          String.format(
              "Operand 'a' must be either TFloat32 or TFloat64 DataType, 'a' is a %s DataType",
              a.type().getSimpleName()));
    }
    if (!a.type().equals(b.type())) {
      throw new IllegalArgumentException(
          String.format(
              "Operands a and b must be the same data type, a is %s DataType, b is %s DataType",
              a.type().getSimpleName(), b.type().getSimpleName()));
    }

    // first result is Operand<T>, second result is Operand<TIn32>, third result is long[] and it is
    // ignored here.
    Object[] aResult = tensordotReshape(scope, a, aAxis, false);
    Operand<T> reshapedA = (Operand<T>) aResult[0];
    Operand<TInt32> aFreeDims = (Operand<TInt32>) aResult[1];
    long[] aFreeDimsStatic = (long[]) aResult[2];

    // first result is Operand<T>, second result is Operand<TIn32>, third result is long[] and it is
    // ignored here.
    Object[] bResult = tensordotReshape(scope, b, bAxis, true);
    Operand<T> reshapedB = (Operand<T>) bResult[0];
    Operand<TInt32> bFreeDims = (Operand<TInt32>) bResult[1];
    long[] bFreeDimsStatic = (long[]) bResult[2];

    Operand<T> abMatmul = MatMul.matmul(scope, reshapedA, reshapedB);
    long[] abDimsStatic = new long[aFreeDimsStatic.length + bFreeDimsStatic.length];
    System.arraycopy(aFreeDimsStatic, 0, abDimsStatic, 0, aFreeDimsStatic.length);
    System.arraycopy(
        bFreeDimsStatic, 0, abDimsStatic, aFreeDimsStatic.length, bFreeDimsStatic.length);
    if (!abMatmul.shape().hasUnknownDimension()
        && abMatmul.shape().equals(Shape.of(abDimsStatic))) {
      return abMatmul;
    } else {
      return Reshape.create(scope, abMatmul, Constant.vectorOf(scope, abDimsStatic));
    }
  }
}
