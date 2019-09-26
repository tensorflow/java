package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.Shape;
import org.tensorflow.op.sparse.AddManySparseToTensorsMap;
import org.tensorflow.op.sparse.AddSparseToTensorsMap;
import org.tensorflow.op.sparse.DenseToDenseSetOperation;
import org.tensorflow.op.sparse.DenseToSparseSetOperation;
import org.tensorflow.op.sparse.DeserializeSparse;
import org.tensorflow.op.sparse.SparseAccumulatorApplyGradient;
import org.tensorflow.op.sparse.SparseAccumulatorTakeGradient;
import org.tensorflow.op.sparse.SparseAdd;
import org.tensorflow.op.sparse.SparseAddGrad;
import org.tensorflow.op.sparse.SparseConcat;
import org.tensorflow.op.sparse.SparseConditionalAccumulator;
import org.tensorflow.op.sparse.SparseCross;
import org.tensorflow.op.sparse.SparseDenseCwiseAdd;
import org.tensorflow.op.sparse.SparseDenseCwiseDiv;
import org.tensorflow.op.sparse.SparseDenseCwiseMul;
import org.tensorflow.op.sparse.SparseFillEmptyRows;
import org.tensorflow.op.sparse.SparseFillEmptyRowsGrad;
import org.tensorflow.op.sparse.SparseMatMul;
import org.tensorflow.op.sparse.SparseReduceMax;
import org.tensorflow.op.sparse.SparseReduceMaxSparse;
import org.tensorflow.op.sparse.SparseReduceSum;
import org.tensorflow.op.sparse.SparseReduceSumSparse;
import org.tensorflow.op.sparse.SparseReorder;
import org.tensorflow.op.sparse.SparseReshape;
import org.tensorflow.op.sparse.SparseSegmentMean;
import org.tensorflow.op.sparse.SparseSegmentMeanGrad;
import org.tensorflow.op.sparse.SparseSegmentMeanWithNumSegments;
import org.tensorflow.op.sparse.SparseSegmentSqrtN;
import org.tensorflow.op.sparse.SparseSegmentSqrtNGrad;
import org.tensorflow.op.sparse.SparseSegmentSqrtNWithNumSegments;
import org.tensorflow.op.sparse.SparseSegmentSum;
import org.tensorflow.op.sparse.SparseSegmentSumWithNumSegments;
import org.tensorflow.op.sparse.SparseSlice;
import org.tensorflow.op.sparse.SparseSliceGrad;
import org.tensorflow.op.sparse.SparseSoftmax;
import org.tensorflow.op.sparse.SparseSparseMaximum;
import org.tensorflow.op.sparse.SparseSparseMinimum;
import org.tensorflow.op.sparse.SparseSplit;
import org.tensorflow.op.sparse.SparseTensorDenseAdd;
import org.tensorflow.op.sparse.SparseTensorDenseMatMul;
import org.tensorflow.op.sparse.SparseToDense;
import org.tensorflow.op.sparse.SparseToSparseSetOperation;
import org.tensorflow.op.sparse.TakeManySparseFromTensorsMap;

/**
 * An API for building {@code sparse} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class SparseOps {
  private final Scope scope;

  SparseOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link SparseReorder} operation
   *
   * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @return a new instance of SparseReorder
   * @see org.tensorflow.op.sparse.SparseReorder
   */
  public <T> SparseReorder<T> sparseReorder(Operand<Long> inputIndices, Operand<T> inputValues,
      Operand<Long> inputShape) {
    return SparseReorder.create(scope, inputIndices, inputValues, inputShape);
  }

  /**
   * Builds an {@link SparseSegmentSqrtNGrad} operation
   *
   * @param grad gradient propagated to the SparseSegmentSqrtN op.
   * @param indices indices passed to the corresponding SparseSegmentSqrtN op.
   * @param segmentIds segment_ids passed to the corresponding SparseSegmentSqrtN op.
   * @param outputDim0 dimension 0 of "data" passed to SparseSegmentSqrtN op.
   * @return a new instance of SparseSegmentSqrtNGrad
   * @see org.tensorflow.op.sparse.SparseSegmentSqrtNGrad
   */
  public <T extends Number, U extends Number> SparseSegmentSqrtNGrad<T> sparseSegmentSqrtNGrad(Operand<T> grad,
      Operand<U> indices, Operand<Integer> segmentIds, Operand<Integer> outputDim0) {
    return SparseSegmentSqrtNGrad.create(scope, grad, indices, segmentIds, outputDim0);
  }

  /**
   * Builds an {@link SparseFillEmptyRows} operation
   *
   * @param indices 2-D. the indices of the sparse tensor.
   * @param values 1-D. the values of the sparse tensor.
   * @param denseShape 1-D. the shape of the sparse tensor.
   * @param defaultValue 0-D. default value to insert into location `[row, 0, ..., 0]`
   * @return a new instance of SparseFillEmptyRows
   * @see org.tensorflow.op.sparse.SparseFillEmptyRows
   */
  public <T> SparseFillEmptyRows<T> sparseFillEmptyRows(Operand<Long> indices, Operand<T> values,
      Operand<Long> denseShape, Operand<T> defaultValue) {
    return SparseFillEmptyRows.create(scope, indices, values, denseShape, defaultValue);
  }

  /**
   * Builds an {@link SparseAccumulatorApplyGradient} operation
   *
   * @param handle The handle to a accumulator.
   * @param localStep The local_step value at which the sparse gradient was computed.
   * @param gradientIndices Indices of the sparse gradient to be accumulated. Must be a
   * @param gradientValues Values are the non-zero slices of the gradient, and must have
   * @param gradientShape Shape of the sparse gradient to be accumulated.
   * @param hasKnownShape Boolean indicating whether gradient_shape is unknown, in which
   * @return a new instance of SparseAccumulatorApplyGradient
   * @see org.tensorflow.op.sparse.SparseAccumulatorApplyGradient
   */
  public <T> SparseAccumulatorApplyGradient sparseAccumulatorApplyGradient(Operand<String> handle,
      Operand<Long> localStep, Operand<Long> gradientIndices, Operand<T> gradientValues,
      Operand<Long> gradientShape, Boolean hasKnownShape) {
    return SparseAccumulatorApplyGradient.create(scope, handle, localStep, gradientIndices, gradientValues, gradientShape, hasKnownShape);
  }

  /**
   * Builds an {@link SparseSegmentSqrtN} operation
   *
   * @param data 
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @return a new instance of SparseSegmentSqrtN
   * @see org.tensorflow.op.sparse.SparseSegmentSqrtN
   */
  public <T extends Number, U extends Number> SparseSegmentSqrtN<T> sparseSegmentSqrtN(Operand<T> data,
      Operand<U> indices, Operand<Integer> segmentIds) {
    return SparseSegmentSqrtN.create(scope, data, indices, segmentIds);
  }

  /**
   * Builds an {@link SparseTensorDenseAdd} operation
   *
   * @param aIndices 2-D.  The `indices` of the `SparseTensor`, with shape `[nnz, ndims]`.
   * @param aValues 1-D.  The `values` of the `SparseTensor`, with shape `[nnz]`.
   * @param aShape 1-D.  The `shape` of the `SparseTensor`, with shape `[ndims]`.
   * @param b `ndims`-D Tensor.  With shape `a_shape`.
   * @return a new instance of SparseTensorDenseAdd
   * @see org.tensorflow.op.sparse.SparseTensorDenseAdd
   */
  public <U, T extends Number> SparseTensorDenseAdd<U> sparseTensorDenseAdd(Operand<T> aIndices,
      Operand<U> aValues, Operand<T> aShape, Operand<U> b) {
    return SparseTensorDenseAdd.create(scope, aIndices, aValues, aShape, b);
  }

  /**
   * Builds an {@link SparseTensorDenseMatMul} operation
   *
   * @param aIndices 2-D.  The `indices` of the `SparseTensor`, size `[nnz, 2]` Matrix.
   * @param aValues 1-D.  The `values` of the `SparseTensor`, size `[nnz]` Vector.
   * @param aShape 1-D.  The `shape` of the `SparseTensor`, size `[2]` Vector.
   * @param b 2-D.  A dense Matrix.
   * @param options carries optional attributes values
   * @return a new instance of SparseTensorDenseMatMul
   * @see org.tensorflow.op.sparse.SparseTensorDenseMatMul
   */
  public <U, T extends Number> SparseTensorDenseMatMul<U> sparseTensorDenseMatMul(Operand<T> aIndices,
      Operand<U> aValues, Operand<Long> aShape, Operand<U> b,
      SparseTensorDenseMatMul.Options... options) {
    return SparseTensorDenseMatMul.create(scope, aIndices, aValues, aShape, b, options);
  }

  /**
   * Builds an {@link DeserializeSparse} operation
   *
   * @param serializedSparse The serialized `SparseTensor` objects. The last dimension
   * @param dtype The `dtype` of the serialized `SparseTensor` objects.
   * @return a new instance of DeserializeSparse
   * @see org.tensorflow.op.sparse.DeserializeSparse
   */
  public <U, T> DeserializeSparse<U> deserializeSparse(Operand<T> serializedSparse,
      Class<U> dtype) {
    return DeserializeSparse.create(scope, serializedSparse, dtype);
  }

  /**
   * Builds an {@link SparseSegmentSqrtNWithNumSegments} operation
   *
   * @param data 
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param numSegments Should equal the number of distinct segment IDs.
   * @return a new instance of SparseSegmentSqrtNWithNumSegments
   * @see org.tensorflow.op.sparse.SparseSegmentSqrtNWithNumSegments
   */
  public <T extends Number, U extends Number, V extends Number> SparseSegmentSqrtNWithNumSegments<T> sparseSegmentSqrtNWithNumSegments(Operand<T> data,
      Operand<U> indices, Operand<Integer> segmentIds, Operand<V> numSegments) {
    return SparseSegmentSqrtNWithNumSegments.create(scope, data, indices, segmentIds, numSegments);
  }

  /**
   * Builds an {@link TakeManySparseFromTensorsMap} operation
   *
   * @param sparseHandles 1-D, The `N` serialized `SparseTensor` objects.
   * @param dtype The `dtype` of the `SparseTensor` objects stored in the
   * @param options carries optional attributes values
   * @return a new instance of TakeManySparseFromTensorsMap
   * @see org.tensorflow.op.sparse.TakeManySparseFromTensorsMap
   */
  public <T> TakeManySparseFromTensorsMap<T> takeManySparseFromTensorsMap(Operand<Long> sparseHandles,
      Class<T> dtype, TakeManySparseFromTensorsMap.Options... options) {
    return TakeManySparseFromTensorsMap.create(scope, sparseHandles, dtype, options);
  }

  /**
   * Builds an {@link SparseReduceMaxSparse} operation
   *
   * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-`K` vector containing the reduction axes.
   * @param options carries optional attributes values
   * @return a new instance of SparseReduceMaxSparse
   * @see org.tensorflow.op.sparse.SparseReduceMaxSparse
   */
  public <T extends Number> SparseReduceMaxSparse<T> sparseReduceMaxSparse(Operand<Long> inputIndices,
      Operand<T> inputValues, Operand<Long> inputShape, Operand<Integer> reductionAxes,
      SparseReduceMaxSparse.Options... options) {
    return SparseReduceMaxSparse.create(scope, inputIndices, inputValues, inputShape, reductionAxes, options);
  }

  /**
   * Builds an {@link AddSparseToTensorsMap} operation
   *
   * @param sparseIndices 2-D.  The `indices` of the `SparseTensor`.
   * @param sparseValues 1-D.  The `values` of the `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the `SparseTensor`.
   * @param options carries optional attributes values
   * @return a new instance of AddSparseToTensorsMap
   * @see org.tensorflow.op.sparse.AddSparseToTensorsMap
   */
  public <T> AddSparseToTensorsMap addSparseToTensorsMap(Operand<Long> sparseIndices,
      Operand<T> sparseValues, Operand<Long> sparseShape,
      AddSparseToTensorsMap.Options... options) {
    return AddSparseToTensorsMap.create(scope, sparseIndices, sparseValues, sparseShape, options);
  }

  /**
   * Builds an {@link SparseAdd} operation
   *
   * @param aIndices 2-D.  The `indices` of the first `SparseTensor`, size `[nnz, ndims]` Matrix.
   * @param aValues 1-D.  The `values` of the first `SparseTensor`, size `[nnz]` Vector.
   * @param aShape 1-D.  The `shape` of the first `SparseTensor`, size `[ndims]` Vector.
   * @param bIndices 2-D.  The `indices` of the second `SparseTensor`, size `[nnz, ndims]` Matrix.
   * @param bValues 1-D.  The `values` of the second `SparseTensor`, size `[nnz]` Vector.
   * @param bShape 1-D.  The `shape` of the second `SparseTensor`, size `[ndims]` Vector.
   * @param thresh 0-D.  The magnitude threshold that determines if an output value/index
   * @return a new instance of SparseAdd
   * @see org.tensorflow.op.sparse.SparseAdd
   */
  public <T, U extends Number> SparseAdd<T> sparseAdd(Operand<Long> aIndices, Operand<T> aValues,
      Operand<Long> aShape, Operand<Long> bIndices, Operand<T> bValues, Operand<Long> bShape,
      Operand<U> thresh) {
    return SparseAdd.create(scope, aIndices, aValues, aShape, bIndices, bValues, bShape, thresh);
  }

  /**
   * Builds an {@link SparseSegmentMeanGrad} operation
   *
   * @param grad gradient propagated to the SparseSegmentMean op.
   * @param indices indices passed to the corresponding SparseSegmentMean op.
   * @param segmentIds segment_ids passed to the corresponding SparseSegmentMean op.
   * @param outputDim0 dimension 0 of "data" passed to SparseSegmentMean op.
   * @return a new instance of SparseSegmentMeanGrad
   * @see org.tensorflow.op.sparse.SparseSegmentMeanGrad
   */
  public <T extends Number, U extends Number> SparseSegmentMeanGrad<T> sparseSegmentMeanGrad(Operand<T> grad,
      Operand<U> indices, Operand<Integer> segmentIds, Operand<Integer> outputDim0) {
    return SparseSegmentMeanGrad.create(scope, grad, indices, segmentIds, outputDim0);
  }

  /**
   * Builds an {@link SparseMatMul} operation
   *
   * @param a 
   * @param b 
   * @param options carries optional attributes values
   * @return a new instance of SparseMatMul
   * @see org.tensorflow.op.sparse.SparseMatMul
   */
  public <T extends Number, U extends Number> SparseMatMul sparseMatMul(Operand<T> a, Operand<U> b,
      SparseMatMul.Options... options) {
    return SparseMatMul.create(scope, a, b, options);
  }

  /**
   * Builds an {@link SparseFillEmptyRowsGrad} operation
   *
   * @param reverseIndexMap 1-D.  The reverse index map from SparseFillEmptyRows.
   * @param gradValues 1-D.  The gradients from backprop.
   * @return a new instance of SparseFillEmptyRowsGrad
   * @see org.tensorflow.op.sparse.SparseFillEmptyRowsGrad
   */
  public <T> SparseFillEmptyRowsGrad<T> sparseFillEmptyRowsGrad(Operand<Long> reverseIndexMap,
      Operand<T> gradValues) {
    return SparseFillEmptyRowsGrad.create(scope, reverseIndexMap, gradValues);
  }

  /**
   * Builds an {@link SparseReduceSumSparse} operation
   *
   * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-`K` vector containing the reduction axes.
   * @param options carries optional attributes values
   * @return a new instance of SparseReduceSumSparse
   * @see org.tensorflow.op.sparse.SparseReduceSumSparse
   */
  public <T> SparseReduceSumSparse<T> sparseReduceSumSparse(Operand<Long> inputIndices,
      Operand<T> inputValues, Operand<Long> inputShape, Operand<Integer> reductionAxes,
      SparseReduceSumSparse.Options... options) {
    return SparseReduceSumSparse.create(scope, inputIndices, inputValues, inputShape, reductionAxes, options);
  }

  /**
   * Builds an {@link SparseDenseCwiseDiv} operation
   *
   * @param spIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * @param spValues 1-D.  `N` non-empty values corresponding to `sp_indices`.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param dense `R`-D.  The dense Tensor operand.
   * @return a new instance of SparseDenseCwiseDiv
   * @see org.tensorflow.op.sparse.SparseDenseCwiseDiv
   */
  public <T> SparseDenseCwiseDiv<T> sparseDenseCwiseDiv(Operand<Long> spIndices,
      Operand<T> spValues, Operand<Long> spShape, Operand<T> dense) {
    return SparseDenseCwiseDiv.create(scope, spIndices, spValues, spShape, dense);
  }

  /**
   * Builds an {@link SparseSlice} operation
   *
   * @param indices 2-D tensor represents the indices of the sparse tensor.
   * @param values 1-D tensor represents the values of the sparse tensor.
   * @param shape 1-D. tensor represents the shape of the sparse tensor.
   * @param start 1-D. tensor represents the start of the slice.
   * @param size 1-D. tensor represents the size of the slice.
   * @return a new instance of SparseSlice
   * @see org.tensorflow.op.sparse.SparseSlice
   */
  public <T> SparseSlice<T> sparseSlice(Operand<Long> indices, Operand<T> values,
      Operand<Long> shape, Operand<Long> start, Operand<Long> size) {
    return SparseSlice.create(scope, indices, values, shape, start, size);
  }

  /**
   * Builds an {@link SparseReduceSum} operation
   *
   * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-`K` vector containing the reduction axes.
   * @param options carries optional attributes values
   * @return a new instance of SparseReduceSum
   * @see org.tensorflow.op.sparse.SparseReduceSum
   */
  public <T> SparseReduceSum<T> sparseReduceSum(Operand<Long> inputIndices, Operand<T> inputValues,
      Operand<Long> inputShape, Operand<Integer> reductionAxes,
      SparseReduceSum.Options... options) {
    return SparseReduceSum.create(scope, inputIndices, inputValues, inputShape, reductionAxes, options);
  }

  /**
   * Builds an {@link SparseSegmentSumWithNumSegments} operation
   *
   * @param data 
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param numSegments Should equal the number of distinct segment IDs.
   * @return a new instance of SparseSegmentSumWithNumSegments
   * @see org.tensorflow.op.sparse.SparseSegmentSumWithNumSegments
   */
  public <T extends Number, U extends Number, V extends Number> SparseSegmentSumWithNumSegments<T> sparseSegmentSumWithNumSegments(Operand<T> data,
      Operand<U> indices, Operand<Integer> segmentIds, Operand<V> numSegments) {
    return SparseSegmentSumWithNumSegments.create(scope, data, indices, segmentIds, numSegments);
  }

  /**
   * Builds an {@link SparseSparseMinimum} operation
   *
   * @param aIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * @param aValues 1-D.  `N` non-empty values corresponding to `a_indices`.
   * @param aShape 1-D.  Shape of the input SparseTensor.
   * @param bIndices counterpart to `a_indices` for the other operand.
   * @param bValues counterpart to `a_values` for the other operand; must be of the same dtype.
   * @param bShape counterpart to `a_shape` for the other operand; the two shapes must be equal.
   * @return a new instance of SparseSparseMinimum
   * @see org.tensorflow.op.sparse.SparseSparseMinimum
   */
  public <T> SparseSparseMinimum<T> sparseSparseMinimum(Operand<Long> aIndices, Operand<T> aValues,
      Operand<Long> aShape, Operand<Long> bIndices, Operand<T> bValues, Operand<Long> bShape) {
    return SparseSparseMinimum.create(scope, aIndices, aValues, aShape, bIndices, bValues, bShape);
  }

  /**
   * Builds an {@link SparseSegmentMean} operation
   *
   * @param data 
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @return a new instance of SparseSegmentMean
   * @see org.tensorflow.op.sparse.SparseSegmentMean
   */
  public <T extends Number, U extends Number> SparseSegmentMean<T> sparseSegmentMean(Operand<T> data,
      Operand<U> indices, Operand<Integer> segmentIds) {
    return SparseSegmentMean.create(scope, data, indices, segmentIds);
  }

  /**
   * Builds an {@link AddManySparseToTensorsMap} operation
   *
   * @param sparseIndices 2-D.  The `indices` of the minibatch `SparseTensor`.
   * @param sparseValues 1-D.  The `values` of the minibatch `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the minibatch `SparseTensor`.
   * @param options carries optional attributes values
   * @return a new instance of AddManySparseToTensorsMap
   * @see org.tensorflow.op.sparse.AddManySparseToTensorsMap
   */
  public <T> AddManySparseToTensorsMap addManySparseToTensorsMap(Operand<Long> sparseIndices,
      Operand<T> sparseValues, Operand<Long> sparseShape,
      AddManySparseToTensorsMap.Options... options) {
    return AddManySparseToTensorsMap.create(scope, sparseIndices, sparseValues, sparseShape, options);
  }

  /**
   * Builds an {@link SparseToSparseSetOperation} operation
   *
   * @param set1Indices 2D `Tensor`, indices of a `SparseTensor`. Must be in row-major
   * @param set1Values 1D `Tensor`, values of a `SparseTensor`. Must be in row-major
   * @param set1Shape 1D `Tensor`, shape of a `SparseTensor`. `set1_shape[0...n-1]` must
   * @param set2Indices 2D `Tensor`, indices of a `SparseTensor`. Must be in row-major
   * @param set2Values 1D `Tensor`, values of a `SparseTensor`. Must be in row-major
   * @param set2Shape 1D `Tensor`, shape of a `SparseTensor`. `set2_shape[0...n-1]` must
   * @param setOperation 
   * @param options carries optional attributes values
   * @return a new instance of SparseToSparseSetOperation
   * @see org.tensorflow.op.sparse.SparseToSparseSetOperation
   */
  public <T> SparseToSparseSetOperation<T> sparseToSparseSetOperation(Operand<Long> set1Indices,
      Operand<T> set1Values, Operand<Long> set1Shape, Operand<Long> set2Indices,
      Operand<T> set2Values, Operand<Long> set2Shape, String setOperation,
      SparseToSparseSetOperation.Options... options) {
    return SparseToSparseSetOperation.create(scope, set1Indices, set1Values, set1Shape, set2Indices, set2Values, set2Shape, setOperation, options);
  }

  /**
   * Builds an {@link DenseToSparseSetOperation} operation
   *
   * @param set1 `Tensor` with rank `n`. 1st `n-1` dimensions must be the same as `set2`.
   * @param set2Indices 2D `Tensor`, indices of a `SparseTensor`. Must be in row-major
   * @param set2Values 1D `Tensor`, values of a `SparseTensor`. Must be in row-major
   * @param set2Shape 1D `Tensor`, shape of a `SparseTensor`. `set2_shape[0...n-1]` must
   * @param setOperation 
   * @param options carries optional attributes values
   * @return a new instance of DenseToSparseSetOperation
   * @see org.tensorflow.op.sparse.DenseToSparseSetOperation
   */
  public <T> DenseToSparseSetOperation<T> denseToSparseSetOperation(Operand<T> set1,
      Operand<Long> set2Indices, Operand<T> set2Values, Operand<Long> set2Shape,
      String setOperation, DenseToSparseSetOperation.Options... options) {
    return DenseToSparseSetOperation.create(scope, set1, set2Indices, set2Values, set2Shape, setOperation, options);
  }

  /**
   * Builds an {@link SparseSplit} operation
   *
   * @param splitDim 0-D.  The dimension along which to split.  Must be in the range
   * @param indices 2-D tensor represents the indices of the sparse tensor.
   * @param values 1-D tensor represents the values of the sparse tensor.
   * @param shape 1-D. tensor represents the shape of the sparse tensor.
   * @param numSplit The number of ways to split.
   * @return a new instance of SparseSplit
   * @see org.tensorflow.op.sparse.SparseSplit
   */
  public <T> SparseSplit<T> sparseSplit(Operand<Long> splitDim, Operand<Long> indices,
      Operand<T> values, Operand<Long> shape, Long numSplit) {
    return SparseSplit.create(scope, splitDim, indices, values, shape, numSplit);
  }

  /**
   * Builds an {@link DenseToDenseSetOperation} operation
   *
   * @param set1 `Tensor` with rank `n`. 1st `n-1` dimensions must be the same as `set2`.
   * @param set2 `Tensor` with rank `n`. 1st `n-1` dimensions must be the same as `set1`.
   * @param setOperation 
   * @param options carries optional attributes values
   * @return a new instance of DenseToDenseSetOperation
   * @see org.tensorflow.op.sparse.DenseToDenseSetOperation
   */
  public <T> DenseToDenseSetOperation<T> denseToDenseSetOperation(Operand<T> set1, Operand<T> set2,
      String setOperation, DenseToDenseSetOperation.Options... options) {
    return DenseToDenseSetOperation.create(scope, set1, set2, setOperation, options);
  }

  /**
   * Builds an {@link SparseAccumulatorTakeGradient} operation
   *
   * @param handle The handle to a SparseConditionalAccumulator.
   * @param numRequired Number of gradients required before we return an aggregate.
   * @param dtype The data type of accumulated gradients. Needs to correspond to the type
   * @return a new instance of SparseAccumulatorTakeGradient
   * @see org.tensorflow.op.sparse.SparseAccumulatorTakeGradient
   */
  public <T> SparseAccumulatorTakeGradient<T> sparseAccumulatorTakeGradient(Operand<String> handle,
      Operand<Integer> numRequired, Class<T> dtype) {
    return SparseAccumulatorTakeGradient.create(scope, handle, numRequired, dtype);
  }

  /**
   * Builds an {@link SparseDenseCwiseMul} operation
   *
   * @param spIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * @param spValues 1-D.  `N` non-empty values corresponding to `sp_indices`.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param dense `R`-D.  The dense Tensor operand.
   * @return a new instance of SparseDenseCwiseMul
   * @see org.tensorflow.op.sparse.SparseDenseCwiseMul
   */
  public <T> SparseDenseCwiseMul<T> sparseDenseCwiseMul(Operand<Long> spIndices,
      Operand<T> spValues, Operand<Long> spShape, Operand<T> dense) {
    return SparseDenseCwiseMul.create(scope, spIndices, spValues, spShape, dense);
  }

  /**
   * Builds an {@link SparseConditionalAccumulator} operation
   *
   * @param dtype The type of the value being accumulated.
   * @param shape The shape of the values.
   * @param options carries optional attributes values
   * @return a new instance of SparseConditionalAccumulator
   * @see org.tensorflow.op.sparse.SparseConditionalAccumulator
   */
  public <T> SparseConditionalAccumulator sparseConditionalAccumulator(Class<T> dtype, Shape shape,
      SparseConditionalAccumulator.Options... options) {
    return SparseConditionalAccumulator.create(scope, dtype, shape, options);
  }

  /**
   * Builds an {@link SparseReduceMax} operation
   *
   * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-`K` vector containing the reduction axes.
   * @param options carries optional attributes values
   * @return a new instance of SparseReduceMax
   * @see org.tensorflow.op.sparse.SparseReduceMax
   */
  public <T extends Number> SparseReduceMax<T> sparseReduceMax(Operand<Long> inputIndices,
      Operand<T> inputValues, Operand<Long> inputShape, Operand<Integer> reductionAxes,
      SparseReduceMax.Options... options) {
    return SparseReduceMax.create(scope, inputIndices, inputValues, inputShape, reductionAxes, options);
  }

  /**
   * Builds an {@link SparseToDense} operation
   *
   * @param sparseIndices 0-D, 1-D, or 2-D.  `sparse_indices[i]` contains the complete
   * @param outputShape 1-D.  Shape of the dense output tensor.
   * @param sparseValues 1-D.  Values corresponding to each row of `sparse_indices`,
   * @param defaultValue Scalar value to set for indices not specified in
   * @param options carries optional attributes values
   * @return a new instance of SparseToDense
   * @see org.tensorflow.op.sparse.SparseToDense
   */
  public <U, T extends Number> SparseToDense<U> sparseToDense(Operand<T> sparseIndices,
      Operand<T> outputShape, Operand<U> sparseValues, Operand<U> defaultValue,
      SparseToDense.Options... options) {
    return SparseToDense.create(scope, sparseIndices, outputShape, sparseValues, defaultValue, options);
  }

  /**
   * Builds an {@link SparseCross} operation
   *
   * @param indices 2-D.  Indices of each input `SparseTensor`.
   * @param values 1-D.   values of each `SparseTensor`.
   * @param shapes 1-D.   Shapes of each `SparseTensor`.
   * @param denseInputs 2-D.    Columns represented by dense `Tensor`.
   * @param hashedOutput If true, returns the hash of the cross instead of the string.
   * @param numBuckets It is used if hashed_output is true.
   * @param hashKey Specify the hash_key that will be used by the `FingerprintCat64`
   * @param outType 
   * @param internalType 
   * @return a new instance of SparseCross
   * @see org.tensorflow.op.sparse.SparseCross
   */
  public <T, U> SparseCross<T> sparseCross(Iterable<Operand<Long>> indices,
      Iterable<Operand<?>> values, Iterable<Operand<Long>> shapes, Iterable<Operand<?>> denseInputs,
      Boolean hashedOutput, Long numBuckets, Long hashKey, Class<T> outType,
      Class<U> internalType) {
    return SparseCross.create(scope, indices, values, shapes, denseInputs, hashedOutput, numBuckets, hashKey, outType, internalType);
  }

  /**
   * Builds an {@link SparseDenseCwiseAdd} operation
   *
   * @param spIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * @param spValues 1-D.  `N` non-empty values corresponding to `sp_indices`.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param dense `R`-D.  The dense Tensor operand.
   * @return a new instance of SparseDenseCwiseAdd
   * @see org.tensorflow.op.sparse.SparseDenseCwiseAdd
   */
  public <T> SparseDenseCwiseAdd<T> sparseDenseCwiseAdd(Operand<Long> spIndices,
      Operand<T> spValues, Operand<Long> spShape, Operand<T> dense) {
    return SparseDenseCwiseAdd.create(scope, spIndices, spValues, spShape, dense);
  }

  /**
   * Builds an {@link SparseSegmentMeanWithNumSegments} operation
   *
   * @param data 
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param numSegments Should equal the number of distinct segment IDs.
   * @return a new instance of SparseSegmentMeanWithNumSegments
   * @see org.tensorflow.op.sparse.SparseSegmentMeanWithNumSegments
   */
  public <T extends Number, U extends Number, V extends Number> SparseSegmentMeanWithNumSegments<T> sparseSegmentMeanWithNumSegments(Operand<T> data,
      Operand<U> indices, Operand<Integer> segmentIds, Operand<V> numSegments) {
    return SparseSegmentMeanWithNumSegments.create(scope, data, indices, segmentIds, numSegments);
  }

  /**
   * Builds an {@link SparseSliceGrad} operation
   *
   * @param backpropValGrad 1-D. The gradient with respect to
   * @param inputIndices 2-D.  The `indices` of the input `SparseTensor`.
   * @param inputStart 1-D. tensor represents the start of the slice.
   * @param outputIndices 2-D.  The `indices` of the sliced `SparseTensor`.
   * @return a new instance of SparseSliceGrad
   * @see org.tensorflow.op.sparse.SparseSliceGrad
   */
  public <T> SparseSliceGrad<T> sparseSliceGrad(Operand<T> backpropValGrad,
      Operand<Long> inputIndices, Operand<Long> inputStart, Operand<Long> outputIndices) {
    return SparseSliceGrad.create(scope, backpropValGrad, inputIndices, inputStart, outputIndices);
  }

  /**
   * Builds an {@link SparseConcat} operation
   *
   * @param indices 2-D.  Indices of each input `SparseTensor`.
   * @param values 1-D.  Non-empty values of each `SparseTensor`.
   * @param shapes 1-D.  Shapes of each `SparseTensor`.
   * @param concatDim Dimension to concatenate along. Must be in range [-rank, rank),
   * @return a new instance of SparseConcat
   * @see org.tensorflow.op.sparse.SparseConcat
   */
  public <T> SparseConcat<T> sparseConcat(Iterable<Operand<Long>> indices,
      Iterable<Operand<T>> values, Iterable<Operand<Long>> shapes, Long concatDim) {
    return SparseConcat.create(scope, indices, values, shapes, concatDim);
  }

  /**
   * Builds an {@link SparseAddGrad} operation
   *
   * @param backpropValGrad 1-D with shape `[nnz(sum)]`.  The gradient with respect to
   * @param aIndices 2-D.  The `indices` of the `SparseTensor` A, size `[nnz(A), ndims]`.
   * @param bIndices 2-D.  The `indices` of the `SparseTensor` B, size `[nnz(B), ndims]`.
   * @param sumIndices 2-D.  The `indices` of the sum `SparseTensor`, size
   * @return a new instance of SparseAddGrad
   * @see org.tensorflow.op.sparse.SparseAddGrad
   */
  public <T> SparseAddGrad<T> sparseAddGrad(Operand<T> backpropValGrad, Operand<Long> aIndices,
      Operand<Long> bIndices, Operand<Long> sumIndices) {
    return SparseAddGrad.create(scope, backpropValGrad, aIndices, bIndices, sumIndices);
  }

  /**
   * Builds an {@link SparseSoftmax} operation
   *
   * @param spIndices 2-D.  `NNZ x R` matrix with the indices of non-empty values in a
   * @param spValues 1-D.  `NNZ` non-empty values corresponding to `sp_indices`.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @return a new instance of SparseSoftmax
   * @see org.tensorflow.op.sparse.SparseSoftmax
   */
  public <T extends Number> SparseSoftmax<T> sparseSoftmax(Operand<Long> spIndices,
      Operand<T> spValues, Operand<Long> spShape) {
    return SparseSoftmax.create(scope, spIndices, spValues, spShape);
  }

  /**
   * Builds an {@link SparseSparseMaximum} operation
   *
   * @param aIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   * @param aValues 1-D.  `N` non-empty values corresponding to `a_indices`.
   * @param aShape 1-D.  Shape of the input SparseTensor.
   * @param bIndices counterpart to `a_indices` for the other operand.
   * @param bValues counterpart to `a_values` for the other operand; must be of the same dtype.
   * @param bShape counterpart to `a_shape` for the other operand; the two shapes must be equal.
   * @return a new instance of SparseSparseMaximum
   * @see org.tensorflow.op.sparse.SparseSparseMaximum
   */
  public <T extends Number> SparseSparseMaximum<T> sparseSparseMaximum(Operand<Long> aIndices,
      Operand<T> aValues, Operand<Long> aShape, Operand<Long> bIndices, Operand<T> bValues,
      Operand<Long> bShape) {
    return SparseSparseMaximum.create(scope, aIndices, aValues, aShape, bIndices, bValues, bShape);
  }

  /**
   * Builds an {@link SparseSegmentSum} operation
   *
   * @param data 
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @return a new instance of SparseSegmentSum
   * @see org.tensorflow.op.sparse.SparseSegmentSum
   */
  public <T extends Number, U extends Number> SparseSegmentSum<T> sparseSegmentSum(Operand<T> data,
      Operand<U> indices, Operand<Integer> segmentIds) {
    return SparseSegmentSum.create(scope, data, indices, segmentIds);
  }

  /**
   * Builds an {@link SparseReshape} operation
   *
   * @param inputIndices 2-D.  `N x R_in` matrix with the indices of non-empty values in a
   * @param inputShape 1-D.  `R_in` vector with the input SparseTensor's dense shape.
   * @param newShape 1-D.  `R_out` vector with the requested new dense shape.
   * @return a new instance of SparseReshape
   * @see org.tensorflow.op.sparse.SparseReshape
   */
  public SparseReshape sparseReshape(Operand<Long> inputIndices, Operand<Long> inputShape,
      Operand<Long> newShape) {
    return SparseReshape.create(scope, inputIndices, inputShape, newShape);
  }
}
