// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op.kotlin

import org.tensorflow.DataType
import org.tensorflow.Operand
import org.tensorflow.ndarray.Shape
import org.tensorflow.op.Scope
import org.tensorflow.op.sparse.AddManySparseToTensorsMap
import org.tensorflow.op.sparse.AddSparseToTensorsMap
import org.tensorflow.op.sparse.DenseToDenseSetOperation
import org.tensorflow.op.sparse.DenseToSparseSetOperation
import org.tensorflow.op.sparse.DeserializeSparse
import org.tensorflow.op.sparse.SparseAccumulatorApplyGradient
import org.tensorflow.op.sparse.SparseAccumulatorTakeGradient
import org.tensorflow.op.sparse.SparseAdd
import org.tensorflow.op.sparse.SparseAddGrad
import org.tensorflow.op.sparse.SparseBincount
import org.tensorflow.op.sparse.SparseConcat
import org.tensorflow.op.sparse.SparseConditionalAccumulator
import org.tensorflow.op.sparse.SparseCross
import org.tensorflow.op.sparse.SparseCrossHashed
import org.tensorflow.op.sparse.SparseDenseCwiseAdd
import org.tensorflow.op.sparse.SparseDenseCwiseDiv
import org.tensorflow.op.sparse.SparseDenseCwiseMul
import org.tensorflow.op.sparse.SparseFillEmptyRows
import org.tensorflow.op.sparse.SparseFillEmptyRowsGrad
import org.tensorflow.op.sparse.SparseMatMul
import org.tensorflow.op.sparse.SparseReduceMax
import org.tensorflow.op.sparse.SparseReduceMaxSparse
import org.tensorflow.op.sparse.SparseReduceSum
import org.tensorflow.op.sparse.SparseReduceSumSparse
import org.tensorflow.op.sparse.SparseReorder
import org.tensorflow.op.sparse.SparseReshape
import org.tensorflow.op.sparse.SparseSegmentMean
import org.tensorflow.op.sparse.SparseSegmentMeanGrad
import org.tensorflow.op.sparse.SparseSegmentMeanWithNumSegments
import org.tensorflow.op.sparse.SparseSegmentSqrtN
import org.tensorflow.op.sparse.SparseSegmentSqrtNGrad
import org.tensorflow.op.sparse.SparseSegmentSqrtNWithNumSegments
import org.tensorflow.op.sparse.SparseSegmentSum
import org.tensorflow.op.sparse.SparseSegmentSumWithNumSegments
import org.tensorflow.op.sparse.SparseSlice
import org.tensorflow.op.sparse.SparseSliceGrad
import org.tensorflow.op.sparse.SparseSoftmax
import org.tensorflow.op.sparse.SparseSparseMaximum
import org.tensorflow.op.sparse.SparseSparseMinimum
import org.tensorflow.op.sparse.SparseSplit
import org.tensorflow.op.sparse.SparseTensorDenseAdd
import org.tensorflow.op.sparse.SparseTensorDenseMatMul
import org.tensorflow.op.sparse.SparseToDense
import org.tensorflow.op.sparse.SparseToSparseSetOperation
import org.tensorflow.op.sparse.TakeManySparseFromTensorsMap
import org.tensorflow.types.TBool
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.TString
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building {@code sparse} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class SparseOps(
  /**
   * Get the parent {@link KotlinOps} object.
   */
  public val ops: KotlinOps
) {
  public val java: org.tensorflow.op.SparseOps = ops.java.sparse

  /**
   * Returns the current {@link Scope scope} of this API
   */
  public val scope: Scope = ops.scope

  public fun <T : TType> addManySparseToTensorsMap(
    sparseIndices: Operand<TInt64>,
    sparseValues: Operand<T>,
    sparseShape: Operand<TInt64>,
    vararg options: AddManySparseToTensorsMap.Options
  ): AddManySparseToTensorsMap = java.addManySparseToTensorsMap<T>(sparseIndices, sparseValues,
      sparseShape, *options)

  public fun <T : TType> addSparseToTensorsMap(
    sparseIndices: Operand<TInt64>,
    sparseValues: Operand<T>,
    sparseShape: Operand<TInt64>,
    vararg options: AddSparseToTensorsMap.Options
  ): AddSparseToTensorsMap = java.addSparseToTensorsMap<T>(sparseIndices, sparseValues, sparseShape,
      *options)

  public fun <T : TType> denseToDenseSetOperation(
    set1: Operand<T>,
    set2: Operand<T>,
    setOperation: String,
    vararg options: DenseToDenseSetOperation.Options
  ): DenseToDenseSetOperation<T> = java.denseToDenseSetOperation<T>(set1, set2, setOperation,
      *options)

  public fun <T : TType> denseToSparseSetOperation(
    set1: Operand<T>,
    set2Indices: Operand<TInt64>,
    set2Values: Operand<T>,
    set2Shape: Operand<TInt64>,
    setOperation: String,
    vararg options: DenseToSparseSetOperation.Options
  ): DenseToSparseSetOperation<T> = java.denseToSparseSetOperation<T>(set1, set2Indices, set2Values,
      set2Shape, setOperation, *options)

  public fun <U : TType, T : TType> deserializeSparse(serializedSparse: Operand<T>,
      dtype: DataType<U>): DeserializeSparse<U> = java.deserializeSparse<U, T>(serializedSparse,
      dtype)

  public fun <T : TType> sparseAccumulatorApplyGradient(
    handle: Operand<TString>,
    localStep: Operand<TInt64>,
    gradientIndices: Operand<TInt64>,
    gradientValues: Operand<T>,
    gradientShape: Operand<TInt64>,
    hasKnownShape: Boolean
  ): SparseAccumulatorApplyGradient = java.sparseAccumulatorApplyGradient<T>(handle, localStep,
      gradientIndices, gradientValues, gradientShape, hasKnownShape)

  public fun <T : TType> sparseAccumulatorTakeGradient(
    handle: Operand<TString>,
    numRequired: Operand<TInt32>,
    dtype: DataType<T>
  ): SparseAccumulatorTakeGradient<T> = java.sparseAccumulatorTakeGradient<T>(handle, numRequired,
      dtype)

  public fun <T : TType, U : TNumber> sparseAdd(
    aIndices: Operand<TInt64>,
    aValues: Operand<T>,
    aShape: Operand<TInt64>,
    bIndices: Operand<TInt64>,
    bValues: Operand<T>,
    bShape: Operand<TInt64>,
    thresh: Operand<U>
  ): SparseAdd<T> = java.sparseAdd<T, U>(aIndices, aValues, aShape, bIndices, bValues, bShape,
      thresh)

  public fun <T : TType> sparseAddGrad(
    backpropValGrad: Operand<T>,
    aIndices: Operand<TInt64>,
    bIndices: Operand<TInt64>,
    sumIndices: Operand<TInt64>
  ): SparseAddGrad<T> = java.sparseAddGrad<T>(backpropValGrad, aIndices, bIndices, sumIndices)

  public fun <U : TNumber, T : TNumber> sparseBincount(
    indices: Operand<TInt64>,
    values: Operand<T>,
    denseShape: Operand<TInt64>,
    size: Operand<T>,
    weights: Operand<U>,
    vararg options: SparseBincount.Options
  ): SparseBincount<U> = java.sparseBincount<U, T>(indices, values, denseShape, size, weights,
      *options)

  public fun <T : TType> sparseConcat(
    indices: Iterable<Operand<TInt64>>,
    values: Iterable<Operand<T>>,
    shapes: Iterable<Operand<TInt64>>,
    concatDim: Long
  ): SparseConcat<T> = java.sparseConcat<T>(indices, values, shapes, concatDim)

  public fun <T : TType> sparseConditionalAccumulator(
    dtype: DataType<T>,
    shape: Shape,
    vararg options: SparseConditionalAccumulator.Options
  ): SparseConditionalAccumulator = java.sparseConditionalAccumulator<T>(dtype, shape, *options)

  public fun sparseCross(
    indices: Iterable<Operand<TInt64>>,
    values: Iterable<Operand<*>>,
    shapes: Iterable<Operand<TInt64>>,
    denseInputs: Iterable<Operand<*>>,
    sep: Operand<TString>
  ): SparseCross = java.sparseCross(indices, values, shapes, denseInputs, sep)

  public fun sparseCrossHashed(
    indices: Iterable<Operand<TInt64>>,
    values: Iterable<Operand<*>>,
    shapes: Iterable<Operand<TInt64>>,
    denseInputs: Iterable<Operand<*>>,
    numBuckets: Operand<TInt64>,
    strongHash: Operand<TBool>,
    salt: Operand<TInt64>
  ): SparseCrossHashed = java.sparseCrossHashed(indices, values, shapes, denseInputs, numBuckets,
      strongHash, salt)

  public fun <T : TType> sparseDenseCwiseAdd(
    spIndices: Operand<TInt64>,
    spValues: Operand<T>,
    spShape: Operand<TInt64>,
    dense: Operand<T>
  ): SparseDenseCwiseAdd<T> = java.sparseDenseCwiseAdd<T>(spIndices, spValues, spShape, dense)

  public fun <T : TType> sparseDenseCwiseDiv(
    spIndices: Operand<TInt64>,
    spValues: Operand<T>,
    spShape: Operand<TInt64>,
    dense: Operand<T>
  ): SparseDenseCwiseDiv<T> = java.sparseDenseCwiseDiv<T>(spIndices, spValues, spShape, dense)

  public fun <T : TType> sparseDenseCwiseMul(
    spIndices: Operand<TInt64>,
    spValues: Operand<T>,
    spShape: Operand<TInt64>,
    dense: Operand<T>
  ): SparseDenseCwiseMul<T> = java.sparseDenseCwiseMul<T>(spIndices, spValues, spShape, dense)

  public fun <T : TType> sparseFillEmptyRows(
    indices: Operand<TInt64>,
    values: Operand<T>,
    denseShape: Operand<TInt64>,
    defaultValue: Operand<T>
  ): SparseFillEmptyRows<T> = java.sparseFillEmptyRows<T>(indices, values, denseShape, defaultValue)

  public fun <T : TType> sparseFillEmptyRowsGrad(reverseIndexMap: Operand<TInt64>,
      gradValues: Operand<T>): SparseFillEmptyRowsGrad<T> =
      java.sparseFillEmptyRowsGrad<T>(reverseIndexMap, gradValues)

  public fun <T : TNumber, U : TNumber> sparseMatMul(
    a: Operand<T>,
    b: Operand<U>,
    vararg options: SparseMatMul.Options
  ): SparseMatMul = java.sparseMatMul<T, U>(a, b, *options)

  public fun <T : TNumber> sparseReduceMax(
    inputIndices: Operand<TInt64>,
    inputValues: Operand<T>,
    inputShape: Operand<TInt64>,
    reductionAxes: Operand<TInt32>,
    vararg options: SparseReduceMax.Options
  ): SparseReduceMax<T> = java.sparseReduceMax<T>(inputIndices, inputValues, inputShape,
      reductionAxes, *options)

  public fun <T : TNumber> sparseReduceMaxSparse(
    inputIndices: Operand<TInt64>,
    inputValues: Operand<T>,
    inputShape: Operand<TInt64>,
    reductionAxes: Operand<TInt32>,
    vararg options: SparseReduceMaxSparse.Options
  ): SparseReduceMaxSparse<T> = java.sparseReduceMaxSparse<T>(inputIndices, inputValues, inputShape,
      reductionAxes, *options)

  public fun <T : TType> sparseReduceSum(
    inputIndices: Operand<TInt64>,
    inputValues: Operand<T>,
    inputShape: Operand<TInt64>,
    reductionAxes: Operand<TInt32>,
    vararg options: SparseReduceSum.Options
  ): SparseReduceSum<T> = java.sparseReduceSum<T>(inputIndices, inputValues, inputShape,
      reductionAxes, *options)

  public fun <T : TType> sparseReduceSumSparse(
    inputIndices: Operand<TInt64>,
    inputValues: Operand<T>,
    inputShape: Operand<TInt64>,
    reductionAxes: Operand<TInt32>,
    vararg options: SparseReduceSumSparse.Options
  ): SparseReduceSumSparse<T> = java.sparseReduceSumSparse<T>(inputIndices, inputValues, inputShape,
      reductionAxes, *options)

  public fun <T : TType> sparseReorder(
    inputIndices: Operand<TInt64>,
    inputValues: Operand<T>,
    inputShape: Operand<TInt64>
  ): SparseReorder<T> = java.sparseReorder<T>(inputIndices, inputValues, inputShape)

  public fun sparseReshape(
    inputIndices: Operand<TInt64>,
    inputShape: Operand<TInt64>,
    newShape: Operand<TInt64>
  ): SparseReshape = java.sparseReshape(inputIndices, inputShape, newShape)

  public fun <T : TNumber, U : TNumber, V : TNumber> sparseSegmentMean(
    `data`: Operand<T>,
    indices: Operand<U>,
    segmentIds: Operand<V>
  ): SparseSegmentMean<T> = java.sparseSegmentMean<T, U, V>(data, indices, segmentIds)

  public fun <T : TNumber, U : TNumber, V : TNumber> sparseSegmentMeanGrad(
    grad: Operand<T>,
    indices: Operand<U>,
    segmentIds: Operand<V>,
    outputDim0: Operand<TInt32>
  ): SparseSegmentMeanGrad<T> = java.sparseSegmentMeanGrad<T, U, V>(grad, indices, segmentIds,
      outputDim0)

  public fun <T : TNumber, U : TNumber, V : TNumber, W : TNumber> sparseSegmentMeanWithNumSegments(
    `data`: Operand<T>,
    indices: Operand<U>,
    segmentIds: Operand<V>,
    numSegments: Operand<W>
  ): SparseSegmentMeanWithNumSegments<T> = java.sparseSegmentMeanWithNumSegments<T, U, V, W>(data,
      indices, segmentIds, numSegments)

  public fun <T : TNumber, U : TNumber, V : TNumber> sparseSegmentSqrtN(
    `data`: Operand<T>,
    indices: Operand<U>,
    segmentIds: Operand<V>
  ): SparseSegmentSqrtN<T> = java.sparseSegmentSqrtN<T, U, V>(data, indices, segmentIds)

  public fun <T : TNumber, U : TNumber, V : TNumber> sparseSegmentSqrtNGrad(
    grad: Operand<T>,
    indices: Operand<U>,
    segmentIds: Operand<V>,
    outputDim0: Operand<TInt32>
  ): SparseSegmentSqrtNGrad<T> = java.sparseSegmentSqrtNGrad<T, U, V>(grad, indices, segmentIds,
      outputDim0)

  public fun <T : TNumber, U : TNumber, V : TNumber, W : TNumber> sparseSegmentSqrtNWithNumSegments(
    `data`: Operand<T>,
    indices: Operand<U>,
    segmentIds: Operand<V>,
    numSegments: Operand<W>
  ): SparseSegmentSqrtNWithNumSegments<T> = java.sparseSegmentSqrtNWithNumSegments<T, U, V, W>(data,
      indices, segmentIds, numSegments)

  public fun <T : TNumber, U : TNumber, V : TNumber> sparseSegmentSum(
    `data`: Operand<T>,
    indices: Operand<U>,
    segmentIds: Operand<V>
  ): SparseSegmentSum<T> = java.sparseSegmentSum<T, U, V>(data, indices, segmentIds)

  public fun <T : TNumber, U : TNumber, V : TNumber, W : TNumber> sparseSegmentSumWithNumSegments(
    `data`: Operand<T>,
    indices: Operand<U>,
    segmentIds: Operand<V>,
    numSegments: Operand<W>
  ): SparseSegmentSumWithNumSegments<T> = java.sparseSegmentSumWithNumSegments<T, U, V, W>(data,
      indices, segmentIds, numSegments)

  public fun <T : TType> sparseSlice(
    indices: Operand<TInt64>,
    values: Operand<T>,
    shape: Operand<TInt64>,
    start: Operand<TInt64>,
    size: Operand<TInt64>
  ): SparseSlice<T> = java.sparseSlice<T>(indices, values, shape, start, size)

  public fun <T : TType> sparseSliceGrad(
    backpropValGrad: Operand<T>,
    inputIndices: Operand<TInt64>,
    inputStart: Operand<TInt64>,
    outputIndices: Operand<TInt64>
  ): SparseSliceGrad<T> = java.sparseSliceGrad<T>(backpropValGrad, inputIndices, inputStart,
      outputIndices)

  public fun <T : TNumber> sparseSoftmax(
    spIndices: Operand<TInt64>,
    spValues: Operand<T>,
    spShape: Operand<TInt64>
  ): SparseSoftmax<T> = java.sparseSoftmax<T>(spIndices, spValues, spShape)

  public fun <T : TNumber> sparseSparseMaximum(
    aIndices: Operand<TInt64>,
    aValues: Operand<T>,
    aShape: Operand<TInt64>,
    bIndices: Operand<TInt64>,
    bValues: Operand<T>,
    bShape: Operand<TInt64>
  ): SparseSparseMaximum<T> = java.sparseSparseMaximum<T>(aIndices, aValues, aShape, bIndices,
      bValues, bShape)

  public fun <T : TType> sparseSparseMinimum(
    aIndices: Operand<TInt64>,
    aValues: Operand<T>,
    aShape: Operand<TInt64>,
    bIndices: Operand<TInt64>,
    bValues: Operand<T>,
    bShape: Operand<TInt64>
  ): SparseSparseMinimum<T> = java.sparseSparseMinimum<T>(aIndices, aValues, aShape, bIndices,
      bValues, bShape)

  public fun <T : TType> sparseSplit(
    splitDim: Operand<TInt64>,
    indices: Operand<TInt64>,
    values: Operand<T>,
    shape: Operand<TInt64>,
    numSplit: Long
  ): SparseSplit<T> = java.sparseSplit<T>(splitDim, indices, values, shape, numSplit)

  public fun <U : TType, T : TNumber> sparseTensorDenseAdd(
    aIndices: Operand<T>,
    aValues: Operand<U>,
    aShape: Operand<T>,
    b: Operand<U>
  ): SparseTensorDenseAdd<U> = java.sparseTensorDenseAdd<U, T>(aIndices, aValues, aShape, b)

  public fun <U : TType, T : TNumber> sparseTensorDenseMatMul(
    aIndices: Operand<T>,
    aValues: Operand<U>,
    aShape: Operand<TInt64>,
    b: Operand<U>,
    vararg options: SparseTensorDenseMatMul.Options
  ): SparseTensorDenseMatMul<U> = java.sparseTensorDenseMatMul<U, T>(aIndices, aValues, aShape, b,
      *options)

  public fun <U : TType, T : TNumber> sparseToDense(
    sparseIndices: Operand<T>,
    outputShape: Operand<T>,
    sparseValues: Operand<U>,
    defaultValue: Operand<U>,
    vararg options: SparseToDense.Options
  ): SparseToDense<U> = java.sparseToDense<U, T>(sparseIndices, outputShape, sparseValues,
      defaultValue, *options)

  public fun <T : TType> sparseToSparseSetOperation(
    set1Indices: Operand<TInt64>,
    set1Values: Operand<T>,
    set1Shape: Operand<TInt64>,
    set2Indices: Operand<TInt64>,
    set2Values: Operand<T>,
    set2Shape: Operand<TInt64>,
    setOperation: String,
    vararg options: SparseToSparseSetOperation.Options
  ): SparseToSparseSetOperation<T> = java.sparseToSparseSetOperation<T>(set1Indices, set1Values,
      set1Shape, set2Indices, set2Values, set2Shape, setOperation, *options)

  public fun <T : TType> takeManySparseFromTensorsMap(
    sparseHandles: Operand<TInt64>,
    dtype: DataType<T>,
    vararg options: TakeManySparseFromTensorsMap.Options
  ): TakeManySparseFromTensorsMap<T> = java.takeManySparseFromTensorsMap<T>(sparseHandles, dtype,
      *options)
}
