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
package org.tensorflow.framework.op.linalg;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.math.Conj;
import org.tensorflow.op.sparse.SparseMatMul;
import org.tensorflow.op.train.BatchMatMul;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;

/**
 * Higher level operation for matMul that does logic before calling the low leve MatMul operations.
 */
/* TODO this is a higher level of abstraction from the low level matmul
it is defined as tf.matmul and tf.linalg.matmul in python.
Should this be defined here? */
@Operator(group = "linalg")
public class MatMul {

  /**
   * Multiplies matrix <code>a</code> by matrix <code>b</code>, producing <code>a</code> * <code>b
   * </code>.
   *
   * <p>The inputs must, following any transpositions, be tensors of rank >= 2 where the inner 2
   * dimensions specify valid matrix multiplication dimensions, and any further outer dimensions
   * specify matching batch size.
   *
   * <p>Both matrices must be of the same type. The supported types are: <code>TFloat16</code>,
   * <code>TFloat32</code>, <code>TFloat64</code>, <code>TInt32</code>.
   *
   * <p>Either matrix can be transposed or adjointed (conjugated and transposed) on the fly by
   * setting one of the corresponding flag to <code>true</code>. These are <code>false</code> by
   * default.
   *
   * <p>A simple 2-D tensor matrix multiplication:
   *
   * <pre>
   *  Operand<TFloat32> a = tf.constant(new float[][] {{1, 2, 3}, {4, 5, 6}});
   *  Operand<TFloat32> b = tf.constant(new float[][] {{7, 8},{ 9, 10}, {11, 12}});
   *  Operand<TFloat32> c = FMWLinalgOps.matmul(tf.scope(), a, b)
   *
   * </pre>
   *
   * <p>Note: This is matrix product, not element-wise product.
   *
   * @param scope the Tensorflow scope
   * @param a an Operand of of type <code>TFloat16</code>, <code>TFloat32</code>, <code>TFloat64
   *     </code>, <code>TInt32</code>. with a rank > 1
   * @param b an Operand with same type and rank as <code>a</code>.
   * @param <T> the data type of the Operands
   * @return A Operand of the same type as <code>a</code> and <code>b</code> where each inner-most
   *     matrix is the product of the corresponding matrices in <code>a</code> and <code>b</code>.
   *     This is the matrix product not an element-wise product.
   * @throws java.lang.IllegalArgumentException If <code>transposeA</code> and <code>adjointA</code>
   *     , or <code>transposeB</code> and <code>adjointB</code> are both set to `true`.
   */
  @Endpoint(name = "matmul")
  public static <T extends TNumber> Operand<T> matmul(Scope scope, Operand<T> a, Operand<T> b) {
    return matmul(scope, a, b, false, false, false, false, false, false);
  }

  /**
   * Multiplies matrix <code>a</code> by matrix <code>b</code>, producing <code>a</code> * <code>b</code>.
   * <p>
   * The inputs must, following any transpositions, be tensors of rank >= 2
   * where the inner 2 dimensions specify valid matrix multiplication
   * dimensions, and any further outer dimensions specify matching batch size.
   * <p>
   * Both matrices must be of the same type. The supported types are:
   * <code>TFloat16</code>, <code>TFloat32</code>, <code>TFloat64</code>, <code>TInt32</code>.
   * <p>
   * Either matrix can be transposed or adjointed (conjugated and transposed)
   * on the fly by setting one of the corresponding flag to <code>true</code>. These are
   * <code>false</code> by default.
   * <p>
   * <p>Note: This is matrix product, not element-wise product.
   * <p>
   * A simple 2-D tensor matrix multiplication:
   * <pre>
   * //TODO
   * </pred
   *
   * @param scope the Tensorflow scope
   * @param a an Operand of of type <code>TFloat16</code>, <code>TFloat32</code>, <code>TFloat64</code>, <code>TInt32</code>.
   * with a rank > 1
   * @param b an Operand with same type and rank as <code>a</code>.
   * @param transposeA If `true`, <code>a</code> is transposed before multiplication.
   * @param transposeB If `True`, <code>b</code> is transposed before multiplication
   * @param <T> the data type of the Operands
   * @return A Operand of the same type as <code>a</code> and <code>b</code> where each
   * inner-most matrix is the product of the corresponding matrices in <code>a</code> and
   * <code>b</code>. This is the
   * matrix product not an element-wise product.
   * @throws java.lang.IllegalArgumentException If <code>transposeA</code> and
   * <code>adjointA</code>, or <code>transposeB</code> and <code>adjointB</code> are both set to `true`.
   */
  @Endpoint(name = "matmul")
  public static <T extends TNumber> Operand<T> matmul(
      Scope scope, Operand<T> a, Operand<T> b, boolean transposeA, boolean transposeB) {
    return matmul(scope, a, b, transposeA, transposeB, false, false, false, false);
  }

  /**
   * Multiplies matrix <code>a</code> by matrix <code>b</code>, producing <code>a</code> * <code>b</code>.
   * <p>
   * The inputs must, following any transpositions, be tensors of rank >= 2
   * where the inner 2 dimensions specify valid matrix multiplication
   * dimensions, and any further outer dimensions specify matching batch size.
   * <p>
   * Both matrices must be of the same type. The supported types are:
   * <code>TFloat16</code>, <code>TFloat32</code>, <code>TFloat64</code>, <code>TInt32</code>.
   * <p>
   * Either matrix can be transposed or adjointed (conjugated and transposed)
   * on the fly by setting one of the corresponding flag to <code>true</code>. These are
   * <code>false</code> by default.
   *
   * <p>Note: This is matrix product, not element-wise product.
   * <p>
   * A simple 2-D tensor matrix multiplication:
   * <pre>
   * //TODO
   * </pred
   *
   * @param scope the Tensorflow scope
   * @param a an Operand of of type <code>TFloat16</code>, <code>TFloat32</code>, <code>TFloat64</code>, <code>TInt32</code>.
   * with a rank > 1
   * @param b an Operand with same type and rank as <code>a</code>.
   * @param transposeA If true, <code>a</code> is transposed before multiplication.
   * @param transposeB If True, <code>b</code> is transposed before multiplication
   * @param adjointA If true, <code>a</code> is conjugated and transposed before
   * multiplication.
   * @param adjointB If true, <code>b</code> is conjugated and transposed before
   * multiplication.
   * @param aIsSparse If <code>true</code>, <code>a</code> is treated as a sparse matrix. Notice, this
   *       <em>does not support <code>org.tensorflow.framework.utils.SparseTensor</code></em>, it just makes optimizations
   *       that assume most values in <code>a</code> are zero.
   * @param bIsSparse If <code>true</code>, <code>b</code> is treated as a sparse matrix. Notice, this
   *       <em>does not support <code>org.tensorflow.framework.utils.SparseTensor</code></em>, it just makes optimizations
   *       that assume most values in <code>b</code> are zero.
   * @param <T> the data type of the Operands
   * @return A Operand of the same type as <code>a</code> and <code>b</code> where each
   * inner-most matrix is the product of the corresponding matrices in <code>a</code> and
   * <code>b</code>. This is the
   * matrix product not an element-wise product.
   * @throws java.lang.IllegalArgumentException If <code>transposeA</code> and
   * <code>adjointA</code>, or <code>transposeB</code> and <code>adjointB</code> are both set to `true`.
   */
  @SuppressWarnings("unchecked")
  @Endpoint(name = "matmul")
  public static <T extends TNumber> Operand<T> matmul(
      Scope scope,
      Operand<T> a,
      Operand<? extends TNumber> b,
      boolean transposeA,
      boolean transposeB,
      boolean adjointA,
      boolean adjointB,
      boolean aIsSparse,
      boolean bIsSparse) {
    scope = scope.withSubScope("MatMul");
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
        adjointA = true;
      }
      if (transposeB) {
        b = Conj.create(scope, b);
        adjointB = true;
      }
      Operand<T> bT = a.type().equals(b.type()) ? (Operand<T>) b : Cast.create(scope, b, a.type());
      return BatchMatMul.create(
          scope, a, bT, BatchMatMul.adjX(adjointA), BatchMatMul.adjY(adjointB));
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
              scope,
              a,
              b,
              SparseMatMul.transposeA(transposeA),
              SparseMatMul.transposeB(transposeB),
              SparseMatMul.aIsSparse(aIsSparse),
              SparseMatMul.bIsSparse(bIsSparse));
      if (a.type().equals(TFloat32.class)) return (Operand<T>) result;
      else return Cast.create(scope, result, a.type());
    }

    // need to cast b to Operand<T>
    Operand<T> bT = a.type().equals(b.type()) ? (Operand<T>) b : Cast.create(scope, b, a.type());

    return org.tensorflow.op.linalg.MatMul.create(
        scope, a, bT,
            org.tensorflow.op.linalg.MatMul.transposeA(transposeA),
            org.tensorflow.op.linalg.MatMul.transposeB(transposeB));
  }
}
