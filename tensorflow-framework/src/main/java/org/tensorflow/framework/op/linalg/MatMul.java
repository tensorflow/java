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
package org.tensorflow.framework.op.linalg;

import org.tensorflow.Operand;
import org.tensorflow.framework.utils.SparseTensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.math.Conj;
import org.tensorflow.op.sparse.SparseMatMul;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;

/** Multiplication matrix operations */
public class MatMul {

  /**
   * Multiplies matrix {@code a} by matrix {@code b}, producing {@code a} * {@code b }.
   *
   * <p>The inputs must, following any transpositions, be tensors of {@code rank >= 2} where the
   * inner 2 dimensions specify valid matrix multiplication dimensions, and any further outer
   * dimensions specify matching batch size.
   *
   * <p>Both matrices must be of the same type. The supported types are: {@code TFloat16}, {@code
   * TFloat32}, {@code TFloat64}, {@code TInt32}.
   *
   * <p>Either matrix can be transposed or adjointed (conjugated and transposed) on the fly by
   * setting one of the corresponding flag to true. These are false by default.
   *
   * <p>A simple 2-D tensor matrix multiplication:
   *
   * <pre>{@code
   * Operand<TFloat64> a = tf.constant(new double[][] {
   *         {-8.944851},
   *         {4.1711287},
   *         {-0.22380222}
   *     });
   * Operand<TFloat64> b = tf.constant( new double[][] {
   *         {-14.276086, -12.433481, -2.2447076, -1.5775859, 1.8588694}
   *     });
   * Operand<TFloat64> result = fops.linalg.matmul(a, b);
   * // result = {
   * //     {127.69746,  111.21564,  20.078575,  14.111271,  -16.62731},
   * //     {-59.547394, -51.861652, -9.362965,  -6.580314,    7.753584},
   * //     {  3.1950197,  2.7826407, 0.50237054, 0.35306725, -0.4160191}
   * //  }
   *
   * }</pre>
   *
   * <p>Note: This is matrix product, not element-wise product.
   *
   * @param scope The TensorFlow scope
   * @param a an Operand of of type {@code TFloat16}, {@code TFloat32}, {@code TFloat64 }, {@code
   *     TInt32}. with a {@code rank > 1}
   * @param b an Operand with same type and rank as {@code a}.
   * @param <T> the data type of the Operands
   * @return A Operand of the same type as {@code a} and {@code b} where each inner-most matrix is
   *     the product of the corresponding matrices in {@code a} and {@code b}. This is the matrix
   *     product not an element-wise product.
   * @throws java.lang.IllegalArgumentException If {@code transposeA} and {@code adjointA} , or
   *     {@code transposeB} and {@code adjointB} are both set to `true`.
   */
  public static <T extends TNumber> Operand<T> matmul(Scope scope, Operand<T> a, Operand<T> b) {
    return matmul(scope, a, b, false, false, false, false, false, false);
  }

  /**
   * Multiplies matrix {@code a} by matrix {@code b}, producing {@code a} * {@code b }.
   *
   * <p>The inputs must, following any transpositions, be tensors of {@code rank >= 2} where the
   * inner 2 dimensions specify valid matrix multiplication dimensions, and any further outer
   * dimensions specify matching batch size.
   *
   * <p>Both matrices must be of the same type. The supported types are: {@code TFloat16}, {@code
   * TFloat32}, {@code TFloat64}, {@code TInt32}.
   *
   * <p>Either matrix can be transposed or adjointed (conjugated and transposed) on the fly by
   * setting one of the corresponding flag to true. These are false by default.
   *
   * <p>Note: This is matrix product, not element-wise product.
   *
   * <p>A simple 2-D tensor matrix multiplication:
   *
   * <pre>{@code
   * Operand<TFloat64> a = tf.constant(new double[][] {
   *         {-8.944851},
   *         {4.1711287},
   *         {-0.22380222}
   *     });
   * Operand<TFloat64> b = tf.constant( new double[][] {
   *         {-14.276086, -12.433481, -2.2447076, -1.5775859, 1.8588694}
   *     });
   * Operand<TFloat64> result = fops.linalg.matmul(a, b);
   * // result = {
   * //     {127.69746,  111.21564,  20.078575,  14.111271,  -16.62731},
   * //     {-59.547394, -51.861652, -9.362965,  -6.580314,    7.753584},
   * //     {  3.1950197,  2.7826407, 0.50237054, 0.35306725, -0.4160191}
   * //  }
   *
   * }</pre>
   *
   * @param scope The TensorFlow scope
   * @param a an Operand of of type {@code TFloat16}, {@code TFloat32}, {@code TFloat64 }, {@code
   *     TInt32}. with a {@code rank > 1}
   * @param b an Operand with same type and rank as {@code a}.
   * @param transposeA If true, {@code a} is transposed before multiplication.
   * @param transposeB If true, {@code b} is transposed before multiplication
   * @param <T> the data type of the Operands
   * @return A Operand of the same type as {@code a} and {@code b} where each inner-most matrix is
   *     the product of the corresponding matrices in {@code a} and {@code b}. This is the matrix
   *     product not an element-wise product.
   * @throws java.lang.IllegalArgumentException If {@code transposeA} and {@code adjointA} , or
   *     {@code transposeB} and {@code adjointB} are both set to `true`.
   */
  public static <T extends TNumber> Operand<T> matmul(
      Scope scope, Operand<T> a, Operand<T> b, boolean transposeA, boolean transposeB) {
    return matmul(scope, a, b, transposeA, transposeB, false, false, false, false);
  }

  /**
   * Multiplies matrix {@code a} by matrix {@code b}, producing {@code a} * {@code b }.
   *
   * <p>The inputs must, following any transpositions, be tensors of {@code rank >= 2} where the
   * inner 2 dimensions specify valid matrix multiplication dimensions, and any further outer
   * dimensions specify matching batch size.
   *
   * <p>Both matrices must be of the same type. The supported types are: {@code TFloat16}, {@code
   * TFloat32}, {@code TFloat64}, {@code TInt32}.
   *
   * <p>Either matrix can be transposed or adjointed (conjugated and transposed) on the fly by
   * setting one of the corresponding flag to true. These are false by default.
   *
   * <p>Note: This is matrix product, not element-wise product.
   *
   * <p>A simple 2-D tensor matrix multiplication:
   *
   * <pre>{@code
   * Operand<TFloat64> a = tf.constant(new double[][] {
   *         {-8.944851},
   *         {4.1711287},
   *         {-0.22380222}
   *     });
   * Operand<TFloat64> b = tf.constant( new double[][] {
   *         {-14.276086, -12.433481, -2.2447076, -1.5775859, 1.8588694}
   *     });
   * Operand<TFloat64> result = fops.linalg.matmul(a, b);
   * // result = {
   * //     {127.69746,  111.21564,  20.078575,  14.111271,  -16.62731},
   * //     {-59.547394, -51.861652, -9.362965,  -6.580314,    7.753584},
   * //     {  3.1950197,  2.7826407, 0.50237054, 0.35306725, -0.4160191}
   * //  }
   *
   * }</pre>
   *
   * @param scope The TensorFlow scope
   * @param a an Operand of of type {@code TFloat16}, {@code TFloat32}, {@code TFloat64 }, {@code
   *     TInt32}. with a {@code rank > 1}
   * @param b an Operand with same type and rank as {@code a}.
   * @param transposeA If true, {@code a} is transposed before multiplication.
   * @param transposeB If True, {@code b} is transposed before multiplication
   * @param adjointA If true, {@code a} is conjugated and transposed before multiplication.
   * @param adjointB If true, {@code b} is conjugated and transposed before multiplication.
   * @param aIsSparse If true, {@code a} is treated as a sparse matrix. Notice, this <em>does not
   *     support {@link SparseTensor}</em>, it just makes optimizations that assume most values in
   *     {@code a} are zero.
   * @param bIsSparse If true, {@code b} is treated as a sparse matrix. Notice, this <em>does not
   *     support {@link SparseTensor}</em>, it just makes optimizations that assume most values in
   *     {@code b} are zero.
   * @param <T> the data type of the Operands
   * @return A Operand of the same type as {@code a} and {@code b} where each inner-most matrix is
   *     the product of the corresponding matrices in {@code a} and {@code b}. This is the matrix
   *     product not an element-wise product.
   * @throws java.lang.IllegalArgumentException If {@code transposeA} and {@code adjointA} , or
   *     {@code transposeB} and {@code adjointB} are both set to `true`.
   */
  @SuppressWarnings("unchecked")
  public static <T extends TNumber> Operand<T> matmul(
      Scope scope,
      Operand<T> a,
      Operand<T> b,
      boolean transposeA,
      boolean transposeB,
      boolean adjointA,
      boolean adjointB,
      boolean aIsSparse,
      boolean bIsSparse) {
    Scope lscope = scope.withSubScope("MatMul");
    if (transposeA && adjointA)
      throw new IllegalArgumentException("Only one of transposeA and adjointA can be true.");
    if (transposeB && adjointB)
      throw new IllegalArgumentException("Only one of transposeB and adjointB can be true.");
    if (!(TFloating.class.isAssignableFrom(a.type()) || a.type().equals(TInt32.class)))
      throw new IllegalArgumentException(
          String.format(
              "Operand 'a' must be of type 'TBfloat16','TFloat16', 'TFloat32', 'TFloat64' or 'TInt32'. found type : %s",
              a.type().getSimpleName()));
    if (!(TFloating.class.isAssignableFrom(a.type()) || b.type().equals(TInt32.class)))
      throw new IllegalArgumentException(
          String.format(
              "Operand 'b' must be of type 'TBfloat16', 'TFloat32', 'TFloat64' or 'TInt32'. found type : %s",
              b.type().getSimpleName()));

    Shape aShape = a.shape();
    Shape bShape = b.shape();
    if (aShape.numDimensions() != bShape.numDimensions())
      throw new IllegalArgumentException(
          String.format(
              "Parameters 'a' and 'b' must the same rank: found a rank = %d, b rank = %d",
              aShape.numDimensions(), bShape.numDimensions()));
    boolean outputMayHaveNonEmptyBatchShape =
        aShape.numDimensions() == Shape.UNKNOWN_SIZE
            || aShape.numDimensions() > 2
            || bShape.numDimensions() == Shape.UNKNOWN_SIZE;

    if ((!aIsSparse && !bIsSparse) && outputMayHaveNonEmptyBatchShape) {
      // BatchMatmul does not support transpose, so we conjugate the matrix and
      // use adjoint instead. Conj() is a noop for real matrices.
      if (transposeA) {
        a = Conj.create(scope, a);
      }
      if (transposeB) {
        b = Conj.create(scope, b);
      }
      return org.tensorflow.op.linalg.MatMul.create(
          lscope,
          a,
          b,
          org.tensorflow.op.linalg.MatMul.transposeA(transposeA),
          org.tensorflow.op.linalg.MatMul.transposeB(transposeB));
    }

    // Neither matmul nor sparse_matmul support adjoint, so we conjugate
    // the matrix and use transpose instead. Conj() is a noop for real
    // matrices.
    if (adjointA) {
      a = Conj.create(scope, a);
      transposeA = true;
    }
    if (adjointB) {
      b = Conj.create(scope, b);
      transposeB = true;
    }

    boolean useSparseMatmul = false;
    if (aIsSparse || bIsSparse) {
      useSparseMatmul =
          (a.type().equals(TBfloat16.class) || a.type().equals(TFloat32.class))
              && (b.type().equals(TBfloat16.class) || b.type().equals(TFloat32.class));
    }
    if ((a.type().equals(TBfloat16.class) || b.type().equals(TBfloat16.class))
        && !a.type().equals(b.type())) useSparseMatmul = true;

    if (useSparseMatmul) {
      Operand<TFloat32> result =
          SparseMatMul.create(
              lscope,
              a,
              b,
              SparseMatMul.transposeA(transposeA),
              SparseMatMul.transposeB(transposeB),
              SparseMatMul.aIsSparse(aIsSparse),
              SparseMatMul.bIsSparse(bIsSparse));
      if (a.type().equals(TFloat32.class)) return (Operand<T>) result;
      else return Cast.create(scope, result, a.type());
    }

    return org.tensorflow.op.linalg.MatMul.create(
        lscope,
        a,
        b,
        org.tensorflow.op.linalg.MatMul.transposeA(transposeA),
        org.tensorflow.op.linalg.MatMul.transposeB(transposeB));
  }
}
