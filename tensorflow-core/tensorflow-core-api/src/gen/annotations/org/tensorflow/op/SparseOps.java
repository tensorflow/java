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
package org.tensorflow.op;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.sparse.AddManySparseToTensorsMap;
import org.tensorflow.op.sparse.AddSparseToTensorsMap;
import org.tensorflow.op.sparse.DenseToDenseSetOperation;
import org.tensorflow.op.sparse.DenseToSparseSetOperation;
import org.tensorflow.op.sparse.DeserializeSparse;
import org.tensorflow.op.sparse.SparseAccumulatorApplyGradient;
import org.tensorflow.op.sparse.SparseAccumulatorTakeGradient;
import org.tensorflow.op.sparse.SparseAdd;
import org.tensorflow.op.sparse.SparseAddGrad;
import org.tensorflow.op.sparse.SparseBincount;
import org.tensorflow.op.sparse.SparseConcat;
import org.tensorflow.op.sparse.SparseConditionalAccumulator;
import org.tensorflow.op.sparse.SparseCross;
import org.tensorflow.op.sparse.SparseCrossHashed;
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
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code sparse} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class SparseOps {
  private final Scope scope;

  private final Ops ops;

  SparseOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Add an `N`-minibatch `SparseTensor` to a `SparseTensorsMap`, return `N` handles.
   *  <p>
   *  A `SparseTensor` of rank `R` is represented by three tensors: `sparse_indices`,
   *  `sparse_values`, and `sparse_shape`, where
   *  <pre>{@code
   *  sparse_indices.shape[1] == sparse_shape.shape[0] == R}</pre>
   *  An `N`-minibatch of `SparseTensor` objects is represented as a `SparseTensor`
   *  having a first `sparse_indices` column taking values between `[0, N)`, where
   *  the minibatch size `N == sparse_shape[0]`.
   *  <p>
   *  The input `SparseTensor` must have rank `R` greater than 1, and the first
   *  dimension is treated as the minibatch dimension.  Elements of the `SparseTensor`
   *  must be sorted in increasing order of this first dimension.  The stored
   *  `SparseTensor` objects pointed to by each row of the output `sparse_handles`
   *  will have rank `R-1`.
   *  <p>
   *  The `SparseTensor` values can then be read out as part of a minibatch by passing
   *  the given keys as vector elements to `TakeManySparseFromTensorsMap`.  To ensure
   *  the correct `SparseTensorsMap` is accessed, ensure that the same
   *  `container` and `shared_name` are passed to that Op.  If no `shared_name`
   *  is provided here, instead use the <i>name</i> of the Operation created by calling
   *  `sparse.AddManySparseToTensorsMap` as the `shared_name` passed to
   *  `TakeManySparseFromTensorsMap`.  Ensure the Operations are colocated.
   *
   * @param sparseIndices 2-D.  The `indices` of the minibatch `SparseTensor`.
   *  `sparse_indices[:, 0]` must be ordered values in `[0, N)`.
   * @param sparseValues 1-D.  The `values` of the minibatch `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the minibatch `SparseTensor`.
   *  The minibatch size `N == sparse_shape[0]`.
   * @param options carries optional attributes values
   * @return a new instance of AddManySparseToTensorsMap
   */
  public <T extends TType> AddManySparseToTensorsMap addManySparseToTensorsMap(
      Operand<TInt64> sparseIndices, Operand<T> sparseValues, Operand<TInt64> sparseShape,
      AddManySparseToTensorsMap.Options... options) {
    return AddManySparseToTensorsMap.create(scope, sparseIndices, sparseValues, sparseShape, options);
  }

  /**
   * Add a `SparseTensor` to a `SparseTensorsMap` return its handle.
   *  <p>
   *  A `SparseTensor` is represented by three tensors: `sparse_indices`,
   *  `sparse_values`, and `sparse_shape`.
   *  <p>
   *  This operator takes the given `SparseTensor` and adds it to a container
   *  object (a `SparseTensorsMap`).  A unique key within this container is generated
   *  in the form of an `int64`, and this is the value that is returned.
   *  <p>
   *  The `SparseTensor` can then be read out as part of a minibatch by passing
   *  the key as a vector element to `TakeManySparseFromTensorsMap`.  To ensure
   *  the correct `SparseTensorsMap` is accessed, ensure that the same
   *  `container` and `shared_name` are passed to that Op.  If no `shared_name`
   *  is provided here, instead use the <i>name</i> of the Operation created by calling
   *  `sparse.AddSparseToTensorsMap` as the `shared_name` passed to
   *  `TakeManySparseFromTensorsMap`.  Ensure the Operations are colocated.
   *
   * @param sparseIndices 2-D.  The `indices` of the `SparseTensor`.
   * @param sparseValues 1-D.  The `values` of the `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the `SparseTensor`.
   * @param options carries optional attributes values
   * @return a new instance of AddSparseToTensorsMap
   */
  public <T extends TType> AddSparseToTensorsMap addSparseToTensorsMap(
      Operand<TInt64> sparseIndices, Operand<T> sparseValues, Operand<TInt64> sparseShape,
      AddSparseToTensorsMap.Options... options) {
    return AddSparseToTensorsMap.create(scope, sparseIndices, sparseValues, sparseShape, options);
  }

  /**
   * Applies set operation along last dimension of 2 `Tensor` inputs.
   *  <p>
   *  See SetOperationOp::SetOperationFromContext for values of `set_operation`.
   *  <p>
   *  Output `result` is a `SparseTensor` represented by `result_indices`,
   *  `result_values`, and `result_shape`. For `set1` and `set2` ranked `n`, this
   *  has rank `n` and the same 1st `n-1` dimensions as `set1` and `set2`. The `nth`
   *  dimension contains the result of `set_operation` applied to the corresponding
   *  `[0...n-1]` dimension of `set`.
   *
   * @param <T> data type for {@code resultValues()} output
   * @param set1 `Tensor` with rank `n`. 1st `n-1` dimensions must be the same as `set2`.
   *  Dimension `n` contains values in a set, duplicates are allowed but ignored.
   * @param set2 `Tensor` with rank `n`. 1st `n-1` dimensions must be the same as `set1`.
   *  Dimension `n` contains values in a set, duplicates are allowed but ignored.
   * @param setOperation
   * @param options carries optional attributes values
   * @return a new instance of DenseToDenseSetOperation
   */
  public <T extends TType> DenseToDenseSetOperation<T> denseToDenseSetOperation(Operand<T> set1,
      Operand<T> set2, String setOperation, DenseToDenseSetOperation.Options... options) {
    return DenseToDenseSetOperation.create(scope, set1, set2, setOperation, options);
  }

  /**
   * Applies set operation along last dimension of `Tensor` and `SparseTensor`.
   *  <p>
   *  See SetOperationOp::SetOperationFromContext for values of `set_operation`.
   *  <p>
   *  Input `set2` is a `SparseTensor` represented by `set2_indices`, `set2_values`,
   *  and `set2_shape`. For `set2` ranked `n`, 1st `n-1` dimensions must be the same
   *  as `set1`. Dimension `n` contains values in a set, duplicates are allowed but
   *  ignored.
   *  <p>
   *  If `validate_indices` is `True`, this op validates the order and range of `set2`
   *  indices.
   *  <p>
   *  Output `result` is a `SparseTensor` represented by `result_indices`,
   *  `result_values`, and `result_shape`. For `set1` and `set2` ranked `n`, this
   *  has rank `n` and the same 1st `n-1` dimensions as `set1` and `set2`. The `nth`
   *  dimension contains the result of `set_operation` applied to the corresponding
   *  `[0...n-1]` dimension of `set`.
   *
   * @param <T> data type for {@code resultValues()} output
   * @param set1 `Tensor` with rank `n`. 1st `n-1` dimensions must be the same as `set2`.
   *  Dimension `n` contains values in a set, duplicates are allowed but ignored.
   * @param set2Indices 2D `Tensor`, indices of a `SparseTensor`. Must be in row-major
   *  order.
   * @param set2Values 1D `Tensor`, values of a `SparseTensor`. Must be in row-major
   *  order.
   * @param set2Shape 1D `Tensor`, shape of a `SparseTensor`. `set2_shape[0...n-1]` must
   *  be the same as the 1st `n-1` dimensions of `set1`, `result_shape[n]` is the
   *  max set size across `n-1` dimensions.
   * @param setOperation
   * @param options carries optional attributes values
   * @return a new instance of DenseToSparseSetOperation
   */
  public <T extends TType> DenseToSparseSetOperation<T> denseToSparseSetOperation(Operand<T> set1,
      Operand<TInt64> set2Indices, Operand<T> set2Values, Operand<TInt64> set2Shape,
      String setOperation, DenseToSparseSetOperation.Options... options) {
    return DenseToSparseSetOperation.create(scope, set1, set2Indices, set2Values, set2Shape, setOperation, options);
  }

  /**
   * Deserialize `SparseTensor` objects.
   *  <p>
   *  The input `serialized_sparse` must have the shape `[?, ?, ..., ?, 3]` where
   *  the last dimension stores serialized `SparseTensor` objects and the other N
   *  dimensions (N >= 0) correspond to a batch. The ranks of the original
   *  `SparseTensor` objects must all match. When the final `SparseTensor` is
   *  created, its rank is the rank of the incoming `SparseTensor` objects plus N;
   *  the sparse tensors have been concatenated along new dimensions, one for each
   *  batch.
   *  <p>
   *  The output `SparseTensor` object's shape values for the original dimensions
   *  are the max across the input `SparseTensor` objects' shape values for the
   *  corresponding dimensions. The new dimensions match the size of the batch.
   *  <p>
   *  The input `SparseTensor` objects' indices are assumed ordered in
   *  standard lexicographic order.  If this is not the case, after this
   *  step run `SparseReorder` to restore index ordering.
   *  <p>
   *  For example, if the serialized input is a `[2 x 3]` matrix representing two
   *  original `SparseTensor` objects:
   *  <p>
   *      index = [ 0]
   *              [10]
   *              [20]
   *      values = [1, 2, 3]
   *      shape = [50]
   *  <p>
   *  and
   *  <p>
   *      index = [ 2]
   *              [10]
   *      values = [4, 5]
   *      shape = [30]
   *  <p>
   *  then the final deserialized `SparseTensor` will be:
   *  <p>
   *      index = [0  0]
   *              [0 10]
   *              [0 20]
   *              [1  2]
   *              [1 10]
   *      values = [1, 2, 3, 4, 5]
   *      shape = [2 50]
   *
   * @param <U> data type for {@code sparseValues()} output
   * @param serializedSparse The serialized `SparseTensor` objects. The last dimension
   *  must have 3 columns.
   * @param dtype The `dtype` of the serialized `SparseTensor` objects.
   * @return a new instance of DeserializeSparse
   */
  public <U extends TType, T extends TType> DeserializeSparse<U> deserializeSparse(
      Operand<T> serializedSparse, DataType<U> dtype) {
    return DeserializeSparse.create(scope, serializedSparse, dtype);
  }

  /**
   * Applies a sparse gradient to a given accumulator.
   *  <p>
   *  Does not add if local_step is smaller than the accumulator's
   *  global_step.
   *
   * @param handle The handle to a accumulator.
   * @param localStep The local_step value at which the sparse gradient was computed.
   * @param gradientIndices Indices of the sparse gradient to be accumulated. Must be a
   *  vector.
   * @param gradientValues Values are the non-zero slices of the gradient, and must have
   *  the same first dimension as indices, i.e., the nnz represented by indices and
   *  values must be consistent.
   * @param gradientShape Shape of the sparse gradient to be accumulated.
   * @param hasKnownShape Boolean indicating whether gradient_shape is unknown, in which
   *  case the input is ignored during validation.
   * @return a new instance of SparseAccumulatorApplyGradient
   */
  public <T extends TType> SparseAccumulatorApplyGradient sparseAccumulatorApplyGradient(
      Operand<TString> handle, Operand<TInt64> localStep, Operand<TInt64> gradientIndices,
      Operand<T> gradientValues, Operand<TInt64> gradientShape, Boolean hasKnownShape) {
    return SparseAccumulatorApplyGradient.create(scope, handle, localStep, gradientIndices, gradientValues, gradientShape, hasKnownShape);
  }

  /**
   * Extracts the average sparse gradient in a SparseConditionalAccumulator.
   *  <p>
   *  The op will blocks until sufficient (i.e., more than num_required)
   *  gradients have been accumulated. If the accumulator has already
   *  aggregated more than num_required gradients, it will return its
   *  average of the accumulated gradients.  Also automatically increments
   *  the recorded global_step in the accumulator by 1, and resets the
   *  aggregate to 0.
   *
   * @param <T> data type for {@code values()} output
   * @param handle The handle to a SparseConditionalAccumulator.
   * @param numRequired Number of gradients required before we return an aggregate.
   * @param dtype The data type of accumulated gradients. Needs to correspond to the type
   *  of the accumulator.
   * @return a new instance of SparseAccumulatorTakeGradient
   */
  public <T extends TType> SparseAccumulatorTakeGradient<T> sparseAccumulatorTakeGradient(
      Operand<TString> handle, Operand<TInt32> numRequired, DataType<T> dtype) {
    return SparseAccumulatorTakeGradient.create(scope, handle, numRequired, dtype);
  }

  /**
   * Adds two `SparseTensor` objects to produce another `SparseTensor`.
   *  <p>
   *  The input `SparseTensor` objects' indices are assumed ordered in standard
   *  lexicographic order.  If this is not the case, before this step run
   *  `SparseReorder` to restore index ordering.
   *  <p>
   *  By default, if two values sum to zero at some index, the output `SparseTensor`
   *  would still include that particular location in its index, storing a zero in the
   *  corresponding value slot.  To override this, callers can specify `thresh`,
   *  indicating that if the sum has a magnitude strictly smaller than `thresh`, its
   *  corresponding value and index would then not be included.  In particular,
   *  `thresh == 0` (default) means everything is kept and actual thresholding happens
   *  only for a positive value.
   *  <p>
   *  In the following shapes, `nnz` is the count after taking `thresh` into account.
   *
   * @param <T> data type for {@code sumValues()} output
   * @param aIndices 2-D.  The `indices` of the first `SparseTensor`, size `[nnz, ndims]` Matrix.
   * @param aValues 1-D.  The `values` of the first `SparseTensor`, size `[nnz]` Vector.
   * @param aShape 1-D.  The `shape` of the first `SparseTensor`, size `[ndims]` Vector.
   * @param bIndices 2-D.  The `indices` of the second `SparseTensor`, size `[nnz, ndims]` Matrix.
   * @param bValues 1-D.  The `values` of the second `SparseTensor`, size `[nnz]` Vector.
   * @param bShape 1-D.  The `shape` of the second `SparseTensor`, size `[ndims]` Vector.
   * @param thresh 0-D.  The magnitude threshold that determines if an output value/index
   *  pair takes space.
   * @return a new instance of SparseAdd
   */
  public <T extends TType, U extends TNumber> SparseAdd<T> sparseAdd(Operand<TInt64> aIndices,
      Operand<T> aValues, Operand<TInt64> aShape, Operand<TInt64> bIndices, Operand<T> bValues,
      Operand<TInt64> bShape, Operand<U> thresh) {
    return SparseAdd.create(scope, aIndices, aValues, aShape, bIndices, bValues, bShape, thresh);
  }

  /**
   * The gradient operator for the SparseAdd op.
   *  <p>
   *  The SparseAdd op calculates A + B, where A, B, and the sum are all represented
   *  as `SparseTensor` objects.  This op takes in the upstream gradient w.r.t.
   *  non-empty values of the sum, and outputs the gradients w.r.t. the non-empty
   *  values of A and B.
   *
   * @param <T> data type for {@code aValGrad()} output
   * @param backpropValGrad 1-D with shape `[nnz(sum)]`.  The gradient with respect to
   *  the non-empty values of the sum.
   * @param aIndices 2-D.  The `indices` of the `SparseTensor` A, size `[nnz(A), ndims]`.
   * @param bIndices 2-D.  The `indices` of the `SparseTensor` B, size `[nnz(B), ndims]`.
   * @param sumIndices 2-D.  The `indices` of the sum `SparseTensor`, size
   *  `[nnz(sum), ndims]`.
   * @return a new instance of SparseAddGrad
   */
  public <T extends TType> SparseAddGrad<T> sparseAddGrad(Operand<T> backpropValGrad,
      Operand<TInt64> aIndices, Operand<TInt64> bIndices, Operand<TInt64> sumIndices) {
    return SparseAddGrad.create(scope, backpropValGrad, aIndices, bIndices, sumIndices);
  }

  /**
   * Counts the number of occurrences of each value in an integer array.
   *  <p>
   *  Outputs a vector with length `size` and the same dtype as `weights`. If
   *  `weights` are empty, then index `i` stores the number of times the value `i` is
   *  counted in `arr`. If `weights` are non-empty, then index `i` stores the sum of
   *  the value in `weights` at each index where the corresponding value in `arr` is
   *  `i`.
   *  <p>
   *  Values in `arr` outside of the range [0, size) are ignored.
   *
   * @param <U> data type for {@code output()} output
   * @param indices 2D int64 `Tensor`.
   * @param values 1D int `Tensor`.
   * @param denseShape 1D int64 `Tensor`.
   * @param size non-negative int scalar `Tensor`.
   * @param weights is an int32, int64, float32, or float64 `Tensor` with the same
   *  shape as `input`, or a length-0 `Tensor`, in which case it acts as all weights
   *  equal to 1.
   * @param options carries optional attributes values
   * @return a new instance of SparseBincount
   */
  public <U extends TNumber, T extends TNumber> SparseBincount<U> sparseBincount(
      Operand<TInt64> indices, Operand<T> values, Operand<TInt64> denseShape, Operand<T> size,
      Operand<U> weights, SparseBincount.Options... options) {
    return SparseBincount.create(scope, indices, values, denseShape, size, weights, options);
  }

  /**
   * Concatenates a list of `SparseTensor` along the specified dimension.
   *  <p>
   *  Concatenation is with respect to the dense versions of these sparse tensors.
   *  It is assumed that each input is a `SparseTensor` whose elements are ordered
   *  along increasing dimension number.
   *  <p>
   *  All inputs' shapes must match, except for the concat dimension.  The
   *  `indices`, `values`, and `shapes` lists must have the same length.
   *  <p>
   *  The output shape is identical to the inputs', except along the concat
   *  dimension, where it is the sum of the inputs' sizes along that dimension.
   *  <p>
   *  The output elements will be resorted to preserve the sort order along
   *  increasing dimension number.
   *  <p>
   *  This op runs in `O(M log M)` time, where `M` is the total number of non-empty
   *  values across all inputs. This is due to the need for an internal sort in
   *  order to concatenate efficiently across an arbitrary dimension.
   *  <p>
   *  For example, if `concat_dim = 1` and the inputs are
   *  <p>
   *      sp_inputs[0]: shape = [2, 3]
   *      [0, 2]: "a"
   *      [1, 0]: "b"
   *      [1, 1]: "c"
   *  <p>
   *      sp_inputs[1]: shape = [2, 4]
   *      [0, 1]: "d"
   *      [0, 2]: "e"
   *  <p>
   *  then the output will be
   *  <p>
   *      shape = [2, 7]
   *      [0, 2]: "a"
   *      [0, 4]: "d"
   *      [0, 5]: "e"
   *      [1, 0]: "b"
   *      [1, 1]: "c"
   *  <p>
   *  Graphically this is equivalent to doing
   *  <p>
   *      [    a] concat [  d e  ] = [    a   d e  ]
   *      [b c  ]        [       ]   [b c          ]
   *
   * @param <T> data type for {@code outputValues()} output
   * @param indices 2-D.  Indices of each input `SparseTensor`.
   * @param values 1-D.  Non-empty values of each `SparseTensor`.
   * @param shapes 1-D.  Shapes of each `SparseTensor`.
   * @param concatDim Dimension to concatenate along. Must be in range [-rank, rank),
   *  where rank is the number of dimensions in each input `SparseTensor`.
   * @return a new instance of SparseConcat
   */
  public <T extends TType> SparseConcat<T> sparseConcat(Iterable<Operand<TInt64>> indices,
      Iterable<Operand<T>> values, Iterable<Operand<TInt64>> shapes, Long concatDim) {
    return SparseConcat.create(scope, indices, values, shapes, concatDim);
  }

  /**
   * A conditional accumulator for aggregating sparse gradients.
   *  <p>
   *  The accumulator accepts gradients marked with local_step greater or
   *  equal to the most recent global_step known to the accumulator. The
   *  average can be extracted from the accumulator, provided sufficient
   *  gradients have been accumulated. Extracting the average automatically
   *  resets the aggregate to 0, and increments the global_step recorded by
   *  the accumulator.
   *
   * @param dtype The type of the value being accumulated.
   * @param shape The shape of the values.
   * @param options carries optional attributes values
   * @return a new instance of SparseConditionalAccumulator
   */
  public <T extends TType> SparseConditionalAccumulator sparseConditionalAccumulator(
      DataType<T> dtype, Shape shape, SparseConditionalAccumulator.Options... options) {
    return SparseConditionalAccumulator.create(scope, dtype, shape, options);
  }

  /**
   * Generates sparse cross from a list of sparse and dense tensors.
   *  <p>
   *  The op takes two lists, one of 2D `SparseTensor` and one of 2D `Tensor`, each
   *  representing features of one feature column. It outputs a 2D `SparseTensor` with
   *  the batchwise crosses of these features.
   *  <p>
   *  For example, if the inputs are
   *  <p>
   *      inputs[0]: SparseTensor with shape = [2, 2]
   *      [0, 0]: "a"
   *      [1, 0]: "b"
   *      [1, 1]: "c"
   *  <p>
   *      inputs[1]: SparseTensor with shape = [2, 1]
   *      [0, 0]: "d"
   *      [1, 0]: "e"
   *  <p>
   *      inputs[2]: Tensor [["f"], ["g"]]
   *  <p>
   *  then the output will be
   *  <p>
   *      shape = [2, 2]
   *      [0, 0]: "a_X_d_X_f"
   *      [1, 0]: "b_X_e_X_g"
   *      [1, 1]: "c_X_e_X_g"
   *  <p>
   *  if hashed_output=true then the output will be
   *  <p>
   *      shape = [2, 2]
   *      [0, 0]: FingerprintCat64(
   *                  Fingerprint64("f"), FingerprintCat64(
   *                      Fingerprint64("d"), Fingerprint64("a")))
   *      [1, 0]: FingerprintCat64(
   *                  Fingerprint64("g"), FingerprintCat64(
   *                      Fingerprint64("e"), Fingerprint64("b")))
   *      [1, 1]: FingerprintCat64(
   *                  Fingerprint64("g"), FingerprintCat64(
   *                      Fingerprint64("e"), Fingerprint64("c")))
   *
   * @param indices 2-D.  Indices of each input `SparseTensor`.
   * @param values 1-D.   values of each `SparseTensor`.
   * @param shapes 1-D.   Shapes of each `SparseTensor`.
   * @param denseInputs 2-D.    Columns represented by dense `Tensor`.
   * @param sep string used when joining a list of string inputs, can be used as separator later.
   * @return a new instance of SparseCross
   */
  public SparseCross sparseCross(Iterable<Operand<TInt64>> indices, Iterable<Operand<?>> values,
      Iterable<Operand<TInt64>> shapes, Iterable<Operand<?>> denseInputs, Operand<TString> sep) {
    return SparseCross.create(scope, indices, values, shapes, denseInputs, sep);
  }

  /**
   * Generates sparse cross from a list of sparse and dense tensors.
   *  <p>
   *  The op takes two lists, one of 2D `SparseTensor` and one of 2D `Tensor`, each
   *  representing features of one feature column. It outputs a 2D `SparseTensor` with
   *  the batchwise crosses of these features.
   *  <p>
   *  For example, if the inputs are
   *  <p>
   *      inputs[0]: SparseTensor with shape = [2, 2]
   *      [0, 0]: "a"
   *      [1, 0]: "b"
   *      [1, 1]: "c"
   *  <p>
   *      inputs[1]: SparseTensor with shape = [2, 1]
   *      [0, 0]: "d"
   *      [1, 0]: "e"
   *  <p>
   *      inputs[2]: Tensor [["f"], ["g"]]
   *  <p>
   *  then the output will be
   *  <p>
   *      shape = [2, 2]
   *      [0, 0]: "a_X_d_X_f"
   *      [1, 0]: "b_X_e_X_g"
   *      [1, 1]: "c_X_e_X_g"
   *  <p>
   *  if hashed_output=true then the output will be
   *  <p>
   *      shape = [2, 2]
   *      [0, 0]: FingerprintCat64(
   *                  Fingerprint64("f"), FingerprintCat64(
   *                      Fingerprint64("d"), Fingerprint64("a")))
   *      [1, 0]: FingerprintCat64(
   *                  Fingerprint64("g"), FingerprintCat64(
   *                      Fingerprint64("e"), Fingerprint64("b")))
   *      [1, 1]: FingerprintCat64(
   *                  Fingerprint64("g"), FingerprintCat64(
   *                      Fingerprint64("e"), Fingerprint64("c")))
   *
   * @param indices 2-D.  Indices of each input `SparseTensor`.
   * @param values 1-D.   values of each `SparseTensor`.
   * @param shapes 1-D.   Shapes of each `SparseTensor`.
   * @param denseInputs 2-D.    Columns represented by dense `Tensor`.
   * @param numBuckets It is used if hashed_output is true.
   *  output = hashed_value%num_buckets if num_buckets > 0 else hashed_value.
   * @param strongHash boolean, if true, siphash with salt will be used instead of farmhash.
   * @param salt Specify the salt that will be used by the siphash function.
   * @return a new instance of SparseCrossHashed
   */
  public SparseCrossHashed sparseCrossHashed(Iterable<Operand<TInt64>> indices,
      Iterable<Operand<?>> values, Iterable<Operand<TInt64>> shapes,
      Iterable<Operand<?>> denseInputs, Operand<TInt64> numBuckets, Operand<TBool> strongHash,
      Operand<TInt64> salt) {
    return SparseCrossHashed.create(scope, indices, values, shapes, denseInputs, numBuckets, strongHash, salt);
  }

  /**
   * Adds up a SparseTensor and a dense Tensor, using these special rules:
   *  <p>
   *  (1) Broadcasts the dense side to have the same shape as the sparse side, if
   *      eligible;
   *  (2) Then, only the dense values pointed to by the indices of the SparseTensor
   *      participate in the cwise addition.
   *  <p>
   *  By these rules, the result is a logical SparseTensor with exactly the same
   *  indices and shape, but possibly with different non-zero values.  The output of
   *  this Op is the resultant non-zero values.
   *
   * @param <T> data type for {@code output()} output
   * @param spIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param spValues 1-D.  `N` non-empty values corresponding to `sp_indices`.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param dense `R`-D.  The dense Tensor operand.
   * @return a new instance of SparseDenseCwiseAdd
   */
  public <T extends TType> SparseDenseCwiseAdd<T> sparseDenseCwiseAdd(Operand<TInt64> spIndices,
      Operand<T> spValues, Operand<TInt64> spShape, Operand<T> dense) {
    return SparseDenseCwiseAdd.create(scope, spIndices, spValues, spShape, dense);
  }

  /**
   * Component-wise divides a SparseTensor by a dense Tensor.
   *  <p>
   *  <i>Limitation</i>: this Op only broadcasts the dense side to the sparse side, but not
   *  the other direction.
   *
   * @param <T> data type for {@code output()} output
   * @param spIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param spValues 1-D.  `N` non-empty values corresponding to `sp_indices`.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param dense `R`-D.  The dense Tensor operand.
   * @return a new instance of SparseDenseCwiseDiv
   */
  public <T extends TType> SparseDenseCwiseDiv<T> sparseDenseCwiseDiv(Operand<TInt64> spIndices,
      Operand<T> spValues, Operand<TInt64> spShape, Operand<T> dense) {
    return SparseDenseCwiseDiv.create(scope, spIndices, spValues, spShape, dense);
  }

  /**
   * Component-wise multiplies a SparseTensor by a dense Tensor.
   *  <p>
   *  The output locations corresponding to the implicitly zero elements in the sparse
   *  tensor will be zero (i.e., will not take up storage space), regardless of the
   *  contents of the dense tensor (even if it's +/-INF and that INF<i>0 == NaN).
   *  <p>
   *  </i>Limitation*: this Op only broadcasts the dense side to the sparse side, but not
   *  the other direction.
   *
   * @param <T> data type for {@code output()} output
   * @param spIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param spValues 1-D.  `N` non-empty values corresponding to `sp_indices`.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param dense `R`-D.  The dense Tensor operand.
   * @return a new instance of SparseDenseCwiseMul
   */
  public <T extends TType> SparseDenseCwiseMul<T> sparseDenseCwiseMul(Operand<TInt64> spIndices,
      Operand<T> spValues, Operand<TInt64> spShape, Operand<T> dense) {
    return SparseDenseCwiseMul.create(scope, spIndices, spValues, spShape, dense);
  }

  /**
   * Fills empty rows in the input 2-D `SparseTensor` with a default value.
   *  <p>
   *  The input `SparseTensor` is represented via the tuple of inputs
   *  (`indices`, `values`, `dense_shape`).  The output `SparseTensor` has the
   *  same `dense_shape` but with indices `output_indices` and values
   *  `output_values`.
   *  <p>
   *  This op inserts a single entry for every row that doesn't have any values.
   *  The index is created as `[row, 0, ..., 0]` and the inserted value
   *  is `default_value`.
   *  <p>
   *  For example, suppose `sp_input` has shape `[5, 6]` and non-empty values:
   *  <p>
   *      [0, 1]: a
   *      [0, 3]: b
   *      [2, 0]: c
   *      [3, 1]: d
   *  <p>
   *  Rows 1 and 4 are empty, so the output will be of shape `[5, 6]` with values:
   *  <p>
   *      [0, 1]: a
   *      [0, 3]: b
   *      [1, 0]: default_value
   *      [2, 0]: c
   *      [3, 1]: d
   *      [4, 0]: default_value
   *  <p>
   *  The output `SparseTensor` will be in row-major order and will have the
   *  same shape as the input.
   *  <p>
   *  This op also returns an indicator vector shaped `[dense_shape[0]]` such that
   *  <p>
   *      empty_row_indicator[i] = True iff row i was an empty row.
   *  <p>
   *  And a reverse index map vector shaped `[indices.shape[0]]` that is used during
   *  backpropagation,
   *  <p>
   *      reverse_index_map[j] = out_j s.t. indices[j, :] == output_indices[out_j, :]
   *
   * @param <T> data type for {@code outputValues()} output
   * @param indices 2-D. the indices of the sparse tensor.
   * @param values 1-D. the values of the sparse tensor.
   * @param denseShape 1-D. the shape of the sparse tensor.
   * @param defaultValue 0-D. default value to insert into location `[row, 0, ..., 0]`
   *    for rows missing from the input sparse tensor.
   *  output indices: 2-D. the indices of the filled sparse tensor.
   * @return a new instance of SparseFillEmptyRows
   */
  public <T extends TType> SparseFillEmptyRows<T> sparseFillEmptyRows(Operand<TInt64> indices,
      Operand<T> values, Operand<TInt64> denseShape, Operand<T> defaultValue) {
    return SparseFillEmptyRows.create(scope, indices, values, denseShape, defaultValue);
  }

  /**
   * The gradient of SparseFillEmptyRows.
   *  <p>
   *  Takes vectors reverse_index_map, shaped `[N]`, and grad_values,
   *  shaped `[N_full]`, where `N_full >= N` and copies data into either
   *  `d_values` or `d_default_value`.  Here `d_values` is shaped `[N]` and
   *  `d_default_value` is a scalar.
   *  <p>
   *    d_values[j] = grad_values[reverse_index_map[j]]
   *    d_default_value = sum_{k : 0 .. N_full - 1} (
   *       grad_values[k] * 1{k not in reverse_index_map})
   *
   * @param <T> data type for {@code dValues()} output
   * @param reverseIndexMap 1-D.  The reverse index map from SparseFillEmptyRows.
   * @param gradValues 1-D.  The gradients from backprop.
   * @return a new instance of SparseFillEmptyRowsGrad
   */
  public <T extends TType> SparseFillEmptyRowsGrad<T> sparseFillEmptyRowsGrad(
      Operand<TInt64> reverseIndexMap, Operand<T> gradValues) {
    return SparseFillEmptyRowsGrad.create(scope, reverseIndexMap, gradValues);
  }

  /**
   * Multiply matrix "a" by matrix "b".
   *  <p>
   *  The inputs must be two-dimensional matrices and the inner dimension of "a" must
   *  match the outer dimension of "b". Both "a" and "b" must be `Tensor`s not
   *  `SparseTensor`s.  This op is optimized for the case where at least one of "a" or
   *  "b" is sparse, in the sense that they have a large proportion of zero values.
   *  The breakeven for using this versus a dense matrix multiply on one platform was
   *  30% zero values in the sparse matrix.
   *  <p>
   *  The gradient computation of this operation will only take advantage of sparsity
   *  in the input gradient when that gradient comes from a Relu.
   *
   * @param a
   * @param b
   * @param options carries optional attributes values
   * @return a new instance of SparseMatMul
   */
  public <T extends TNumber, U extends TNumber> SparseMatMul sparseMatMul(Operand<T> a,
      Operand<U> b, SparseMatMul.Options... options) {
    return SparseMatMul.create(scope, a, b, options);
  }

  /**
   * Computes the max of elements across dimensions of a SparseTensor.
   *  <p>
   *  This Op takes a SparseTensor and is the sparse counterpart to
   *  `tf.reduce_max()`.  In particular, this Op also returns a dense `Tensor`
   *  instead of a sparse one.
   *  <p>
   *  Reduces `sp_input` along the dimensions given in `reduction_axes`.  Unless
   *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
   *  `reduction_axes`. If `keep_dims` is true, the reduced dimensions are retained
   *  with length 1.
   *  <p>
   *  If `reduction_axes` has no entries, all dimensions are reduced, and a tensor
   *  with a single element is returned.  Additionally, the axes can be negative,
   *  which are interpreted according to the indexing rules in Python.
   *
   * @param <T> data type for {@code output()} output
   * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-`K` vector containing the reduction axes.
   * @param options carries optional attributes values
   * @return a new instance of SparseReduceMax
   */
  public <T extends TNumber> SparseReduceMax<T> sparseReduceMax(Operand<TInt64> inputIndices,
      Operand<T> inputValues, Operand<TInt64> inputShape, Operand<TInt32> reductionAxes,
      SparseReduceMax.Options... options) {
    return SparseReduceMax.create(scope, inputIndices, inputValues, inputShape, reductionAxes, options);
  }

  /**
   * Computes the max of elements across dimensions of a SparseTensor.
   *  <p>
   *  This Op takes a SparseTensor and is the sparse counterpart to
   *  `tf.reduce_max()`.  In contrast to SparseReduceMax, this Op returns a
   *  SparseTensor.
   *  <p>
   *  Reduces `sp_input` along the dimensions given in `reduction_axes`.  Unless
   *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
   *  `reduction_axes`. If `keep_dims` is true, the reduced dimensions are retained
   *  with length 1.
   *  <p>
   *  If `reduction_axes` has no entries, all dimensions are reduced, and a tensor
   *  with a single element is returned.  Additionally, the axes can be negative,
   *  which are interpreted according to the indexing rules in Python.
   *
   * @param <T> data type for {@code outputValues()} output
   * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-`K` vector containing the reduction axes.
   * @param options carries optional attributes values
   * @return a new instance of SparseReduceMaxSparse
   */
  public <T extends TNumber> SparseReduceMaxSparse<T> sparseReduceMaxSparse(
      Operand<TInt64> inputIndices, Operand<T> inputValues, Operand<TInt64> inputShape,
      Operand<TInt32> reductionAxes, SparseReduceMaxSparse.Options... options) {
    return SparseReduceMaxSparse.create(scope, inputIndices, inputValues, inputShape, reductionAxes, options);
  }

  /**
   * Computes the sum of elements across dimensions of a SparseTensor.
   *  <p>
   *  This Op takes a SparseTensor and is the sparse counterpart to
   *  `tf.reduce_sum()`.  In particular, this Op also returns a dense `Tensor`
   *  instead of a sparse one.
   *  <p>
   *  Reduces `sp_input` along the dimensions given in `reduction_axes`.  Unless
   *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
   *  `reduction_axes`. If `keep_dims` is true, the reduced dimensions are retained
   *  with length 1.
   *  <p>
   *  If `reduction_axes` has no entries, all dimensions are reduced, and a tensor
   *  with a single element is returned.  Additionally, the axes can be negative,
   *  which are interpreted according to the indexing rules in Python.
   *
   * @param <T> data type for {@code output()} output
   * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-`K` vector containing the reduction axes.
   * @param options carries optional attributes values
   * @return a new instance of SparseReduceSum
   */
  public <T extends TType> SparseReduceSum<T> sparseReduceSum(Operand<TInt64> inputIndices,
      Operand<T> inputValues, Operand<TInt64> inputShape, Operand<TInt32> reductionAxes,
      SparseReduceSum.Options... options) {
    return SparseReduceSum.create(scope, inputIndices, inputValues, inputShape, reductionAxes, options);
  }

  /**
   * Computes the sum of elements across dimensions of a SparseTensor.
   *  <p>
   *  This Op takes a SparseTensor and is the sparse counterpart to
   *  `tf.reduce_sum()`.  In contrast to SparseReduceSum, this Op returns a
   *  SparseTensor.
   *  <p>
   *  Reduces `sp_input` along the dimensions given in `reduction_axes`.  Unless
   *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
   *  `reduction_axes`. If `keep_dims` is true, the reduced dimensions are retained
   *  with length 1.
   *  <p>
   *  If `reduction_axes` has no entries, all dimensions are reduced, and a tensor
   *  with a single element is returned.  Additionally, the axes can be negative,
   *  which are interpreted according to the indexing rules in Python.
   *
   * @param <T> data type for {@code outputValues()} output
   * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-`K` vector containing the reduction axes.
   * @param options carries optional attributes values
   * @return a new instance of SparseReduceSumSparse
   */
  public <T extends TType> SparseReduceSumSparse<T> sparseReduceSumSparse(
      Operand<TInt64> inputIndices, Operand<T> inputValues, Operand<TInt64> inputShape,
      Operand<TInt32> reductionAxes, SparseReduceSumSparse.Options... options) {
    return SparseReduceSumSparse.create(scope, inputIndices, inputValues, inputShape, reductionAxes, options);
  }

  /**
   * Reorders a SparseTensor into the canonical, row-major ordering.
   *  <p>
   *  Note that by convention, all sparse ops preserve the canonical ordering along
   *  increasing dimension number. The only time ordering can be violated is during
   *  manual manipulation of the indices and values vectors to add entries.
   *  <p>
   *  Reordering does not affect the shape of the SparseTensor.
   *  <p>
   *  If the tensor has rank `R` and `N` non-empty values, `input_indices` has
   *  shape `[N, R]`, input_values has length `N`, and input_shape has length `R`.
   *
   * @param <T> data type for {@code outputValues()} output
   * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @return a new instance of SparseReorder
   */
  public <T extends TType> SparseReorder<T> sparseReorder(Operand<TInt64> inputIndices,
      Operand<T> inputValues, Operand<TInt64> inputShape) {
    return SparseReorder.create(scope, inputIndices, inputValues, inputShape);
  }

  /**
   * Reshapes a SparseTensor to represent values in a new dense shape.
   *  <p>
   *  This operation has the same semantics as reshape on the represented dense
   *  tensor.  The `input_indices` are recomputed based on the requested `new_shape`.
   *  <p>
   *  If one component of `new_shape` is the special value -1, the size of that
   *  dimension is computed so that the total dense size remains constant.  At
   *  most one component of `new_shape` can be -1.  The number of dense elements
   *  implied by `new_shape` must be the same as the number of dense elements
   *  originally implied by `input_shape`.
   *  <p>
   *  Reshaping does not affect the order of values in the SparseTensor.
   *  <p>
   *  If the input tensor has rank `R_in` and `N` non-empty values, and `new_shape`
   *  has length `R_out`, then `input_indices` has shape `[N, R_in]`,
   *  `input_shape` has length `R_in`, `output_indices` has shape `[N, R_out]`, and
   *  `output_shape` has length `R_out`.
   *
   * @param inputIndices 2-D.  `N x R_in` matrix with the indices of non-empty values in a
   *  SparseTensor.
   * @param inputShape 1-D.  `R_in` vector with the input SparseTensor's dense shape.
   * @param newShape 1-D.  `R_out` vector with the requested new dense shape.
   * @return a new instance of SparseReshape
   */
  public SparseReshape sparseReshape(Operand<TInt64> inputIndices, Operand<TInt64> inputShape,
      Operand<TInt64> newShape) {
    return SparseReshape.create(scope, inputIndices, inputShape, newShape);
  }

  /**
   * Computes the mean along sparse segments of a tensor.
   *  <p>
   *  See `tf.sparse.segment_sum` for usage examples.
   *  <p>
   *  Like `SegmentMean`, but `segment_ids` can have rank less than `data`'s first
   *  dimension, selecting a subset of dimension 0, specified by `indices`.
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @return a new instance of SparseSegmentMean
   */
  public <T extends TNumber, U extends TNumber, V extends TNumber> SparseSegmentMean<T> sparseSegmentMean(
      Operand<T> data, Operand<U> indices, Operand<V> segmentIds) {
    return SparseSegmentMean.create(scope, data, indices, segmentIds);
  }

  /**
   * Computes gradients for SparseSegmentMean.
   *  <p>
   *  Returns tensor "output" with same shape as grad, except for dimension 0 whose
   *  value is output_dim0.
   *
   * @param <T> data type for {@code output()} output
   * @param grad gradient propagated to the SparseSegmentMean op.
   * @param indices indices passed to the corresponding SparseSegmentMean op.
   * @param segmentIds segment_ids passed to the corresponding SparseSegmentMean op.
   * @param outputDim0 dimension 0 of "data" passed to SparseSegmentMean op.
   * @return a new instance of SparseSegmentMeanGrad
   */
  public <T extends TNumber, U extends TNumber, V extends TNumber> SparseSegmentMeanGrad<T> sparseSegmentMeanGrad(
      Operand<T> grad, Operand<U> indices, Operand<V> segmentIds, Operand<TInt32> outputDim0) {
    return SparseSegmentMeanGrad.create(scope, grad, indices, segmentIds, outputDim0);
  }

  /**
   * Computes the mean along sparse segments of a tensor.
   *  <p>
   *  Like `SparseSegmentMean`, but allows missing ids in `segment_ids`. If an id is
   *  missing, the `output` tensor at that position will be zeroed.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
   *  for an explanation of segments.
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param numSegments Should equal the number of distinct segment IDs.
   * @return a new instance of SparseSegmentMeanWithNumSegments
   */
  public <T extends TNumber, U extends TNumber, V extends TNumber, W extends TNumber> SparseSegmentMeanWithNumSegments<T> sparseSegmentMeanWithNumSegments(
      Operand<T> data, Operand<U> indices, Operand<V> segmentIds, Operand<W> numSegments) {
    return SparseSegmentMeanWithNumSegments.create(scope, data, indices, segmentIds, numSegments);
  }

  /**
   * Computes the sum along sparse segments of a tensor divided by the sqrt of N.
   *  <p>
   *  N is the size of the segment being reduced.
   *  <p>
   *  See `tf.sparse.segment_sum` for usage examples.
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @return a new instance of SparseSegmentSqrtN
   */
  public <T extends TNumber, U extends TNumber, V extends TNumber> SparseSegmentSqrtN<T> sparseSegmentSqrtN(
      Operand<T> data, Operand<U> indices, Operand<V> segmentIds) {
    return SparseSegmentSqrtN.create(scope, data, indices, segmentIds);
  }

  /**
   * Computes gradients for SparseSegmentSqrtN.
   *  <p>
   *  Returns tensor "output" with same shape as grad, except for dimension 0 whose
   *  value is output_dim0.
   *
   * @param <T> data type for {@code output()} output
   * @param grad gradient propagated to the SparseSegmentSqrtN op.
   * @param indices indices passed to the corresponding SparseSegmentSqrtN op.
   * @param segmentIds segment_ids passed to the corresponding SparseSegmentSqrtN op.
   * @param outputDim0 dimension 0 of "data" passed to SparseSegmentSqrtN op.
   * @return a new instance of SparseSegmentSqrtNGrad
   */
  public <T extends TNumber, U extends TNumber, V extends TNumber> SparseSegmentSqrtNGrad<T> sparseSegmentSqrtNGrad(
      Operand<T> grad, Operand<U> indices, Operand<V> segmentIds, Operand<TInt32> outputDim0) {
    return SparseSegmentSqrtNGrad.create(scope, grad, indices, segmentIds, outputDim0);
  }

  /**
   * Computes the sum along sparse segments of a tensor divided by the sqrt of N.
   *  <p>
   *  N is the size of the segment being reduced.
   *  <p>
   *  Like `SparseSegmentSqrtN`, but allows missing ids in `segment_ids`. If an id is
   *  missing, the `output` tensor at that position will be zeroed.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
   *  for an explanation of segments.
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param numSegments Should equal the number of distinct segment IDs.
   * @return a new instance of SparseSegmentSqrtNWithNumSegments
   */
  public <T extends TNumber, U extends TNumber, V extends TNumber, W extends TNumber> SparseSegmentSqrtNWithNumSegments<T> sparseSegmentSqrtNWithNumSegments(
      Operand<T> data, Operand<U> indices, Operand<V> segmentIds, Operand<W> numSegments) {
    return SparseSegmentSqrtNWithNumSegments.create(scope, data, indices, segmentIds, numSegments);
  }

  /**
   * Computes the sum along sparse segments of a tensor.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
   *  for an explanation of segments.
   *  <p>
   *  Like `SegmentSum`, but `segment_ids` can have rank less than `data`'s first
   *  dimension, selecting a subset of dimension 0, specified by `indices`.
   *  <p>
   *  For example:
   *  <pre>{@code
   *  c = tf.constant([[1,2,3,4], [-1,-2,-3,-4], [5,6,7,8]])
   *
   *  # Select two rows, one segment.
   *  tf.sparse_segment_sum(c, tf.constant([0, 1]), tf.constant([0, 0]))
   *  # => [[0 0 0 0]]
   *
   *  # Select two rows, two segment.
   *  tf.sparse_segment_sum(c, tf.constant([0, 1]), tf.constant([0, 1]))
   *  # => [[ 1  2  3  4]
   *  #     [-1 -2 -3 -4]]
   *
   *  # Select all rows, two segments.
   *  tf.sparse_segment_sum(c, tf.constant([0, 1, 2]), tf.constant([0, 0, 1]))
   *  # => [[0 0 0 0]
   *  #     [5 6 7 8]]
   *
   *  # Which is equivalent to:
   *  tf.segment_sum(c, tf.constant([0, 0, 1]))
   *  }</pre>
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @return a new instance of SparseSegmentSum
   */
  public <T extends TNumber, U extends TNumber, V extends TNumber> SparseSegmentSum<T> sparseSegmentSum(
      Operand<T> data, Operand<U> indices, Operand<V> segmentIds) {
    return SparseSegmentSum.create(scope, data, indices, segmentIds);
  }

  /**
   * Computes the sum along sparse segments of a tensor.
   *  <p>
   *  Like `SparseSegmentSum`, but allows missing ids in `segment_ids`. If an id is
   *  missing, the `output` tensor at that position will be zeroed.
   *  <p>
   *  Read
   *  [the section on segmentation](https://tensorflow.org/api_docs/python/tf/sparse#Segmentation)
   *  for an explanation of segments.
   *  <p>
   *  For example:
   *  <pre>{@code
   *  c = tf.constant([[1,2,3,4], [-1,-2,-3,-4], [5,6,7,8]])
   *
   *  tf.sparse_segment_sum_with_num_segments(
   *      c, tf.constant([0, 1]), tf.constant([0, 0]), num_segments=3)
   *  # => [[0 0 0 0]
   *  #     [0 0 0 0]
   *  #     [0 0 0 0]]
   *
   *  tf.sparse_segment_sum_with_num_segments(c,
   *                                          tf.constant([0, 1]),
   *                                          tf.constant([0, 2],
   *                                          num_segments=4))
   *  # => [[ 1  2  3  4]
   *  #     [ 0  0  0  0]
   *  #     [-1 -2 -3 -4]
   *  #     [ 0  0  0  0]]
   *  }</pre>
   *
   * @param <T> data type for {@code output()} output
   * @param data
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param numSegments Should equal the number of distinct segment IDs.
   * @return a new instance of SparseSegmentSumWithNumSegments
   */
  public <T extends TNumber, U extends TNumber, V extends TNumber, W extends TNumber> SparseSegmentSumWithNumSegments<T> sparseSegmentSumWithNumSegments(
      Operand<T> data, Operand<U> indices, Operand<V> segmentIds, Operand<W> numSegments) {
    return SparseSegmentSumWithNumSegments.create(scope, data, indices, segmentIds, numSegments);
  }

  /**
   * Slice a `SparseTensor` based on the `start` and `size`.
   *  <p>
   *  For example, if the input is
   *  <p>
   *      input_tensor = shape = [2, 7]
   *      [    a   d e  ]
   *      [b c          ]
   *  <p>
   *  Graphically the output tensors are:
   *  <p>
   *      sparse_slice([0, 0], [2, 4]) = shape = [2, 4]
   *      [    a  ]
   *      [b c    ]
   *  <p>
   *      sparse_slice([0, 4], [2, 3]) = shape = [2, 3]
   *      [ d e  ]
   *      [      ]
   *
   * @param <T> data type for {@code outputValues()} output
   * @param indices 2-D tensor represents the indices of the sparse tensor.
   * @param values 1-D tensor represents the values of the sparse tensor.
   * @param shape 1-D. tensor represents the shape of the sparse tensor.
   * @param start 1-D. tensor represents the start of the slice.
   * @param size 1-D. tensor represents the size of the slice.
   *  output indices: A list of 1-D tensors represents the indices of the output
   *  sparse tensors.
   * @return a new instance of SparseSlice
   */
  public <T extends TType> SparseSlice<T> sparseSlice(Operand<TInt64> indices, Operand<T> values,
      Operand<TInt64> shape, Operand<TInt64> start, Operand<TInt64> size) {
    return SparseSlice.create(scope, indices, values, shape, start, size);
  }

  /**
   * The gradient operator for the SparseSlice op.
   *  <p>
   *  This op takes in the upstream gradient w.r.t. non-empty values of
   *  the sliced `SparseTensor`, and outputs the gradients w.r.t.
   *  the non-empty values of input `SparseTensor`.
   *
   * @param <T> data type for {@code valGrad()} output
   * @param backpropValGrad 1-D. The gradient with respect to
   *  the non-empty values of the sliced `SparseTensor`.
   * @param inputIndices 2-D.  The `indices` of the input `SparseTensor`.
   * @param inputStart 1-D. tensor represents the start of the slice.
   * @param outputIndices 2-D.  The `indices` of the sliced `SparseTensor`.
   * @return a new instance of SparseSliceGrad
   */
  public <T extends TType> SparseSliceGrad<T> sparseSliceGrad(Operand<T> backpropValGrad,
      Operand<TInt64> inputIndices, Operand<TInt64> inputStart, Operand<TInt64> outputIndices) {
    return SparseSliceGrad.create(scope, backpropValGrad, inputIndices, inputStart, outputIndices);
  }

  /**
   * Applies softmax to a batched N-D `SparseTensor`.
   *  <p>
   *  The inputs represent an N-D SparseTensor  with logical shape `[..., B, C]`
   *  (where `N >= 2`), and with indices sorted in the canonical lexicographic order.
   *  <p>
   *  This op is equivalent to applying the normal `tf.nn.softmax()` to each innermost
   *  logical submatrix with shape `[B, C]`, but with the catch that <i>the implicitly
   *  zero elements do not participate</i>.  Specifically, the algorithm is equivalent
   *  to the following:
   *  <p>
   *    (1) Applies `tf.nn.softmax()` to a densified view of each innermost submatrix
   *        with shape `[B, C]`, along the size-C dimension;
   *    (2) Masks out the original implicitly-zero locations;
   *    (3) Renormalizes the remaining elements.
   *  <p>
   *  Hence, the `SparseTensor` result has exactly the same non-zero indices and
   *  shape.
   *
   * @param <T> data type for {@code output()} output
   * @param spIndices 2-D.  `NNZ x R` matrix with the indices of non-empty values in a
   *  SparseTensor, in canonical ordering.
   * @param spValues 1-D.  `NNZ` non-empty values corresponding to `sp_indices`.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @return a new instance of SparseSoftmax
   */
  public <T extends TNumber> SparseSoftmax<T> sparseSoftmax(Operand<TInt64> spIndices,
      Operand<T> spValues, Operand<TInt64> spShape) {
    return SparseSoftmax.create(scope, spIndices, spValues, spShape);
  }

  /**
   * Returns the element-wise max of two SparseTensors.
   *  <p>
   *  Assumes the two SparseTensors have the same shape, i.e., no broadcasting.
   *
   * @param <T> data type for {@code outputValues()} output
   * @param aIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   *  SparseTensor, in the canonical lexicographic ordering.
   * @param aValues 1-D.  `N` non-empty values corresponding to `a_indices`.
   * @param aShape 1-D.  Shape of the input SparseTensor.
   * @param bIndices counterpart to `a_indices` for the other operand.
   * @param bValues counterpart to `a_values` for the other operand; must be of the same dtype.
   * @param bShape counterpart to `a_shape` for the other operand; the two shapes must be equal.
   * @return a new instance of SparseSparseMaximum
   */
  public <T extends TNumber> SparseSparseMaximum<T> sparseSparseMaximum(Operand<TInt64> aIndices,
      Operand<T> aValues, Operand<TInt64> aShape, Operand<TInt64> bIndices, Operand<T> bValues,
      Operand<TInt64> bShape) {
    return SparseSparseMaximum.create(scope, aIndices, aValues, aShape, bIndices, bValues, bShape);
  }

  /**
   * Returns the element-wise min of two SparseTensors.
   *  <p>
   *  Assumes the two SparseTensors have the same shape, i.e., no broadcasting.
   *
   * @param <T> data type for {@code outputValues()} output
   * @param aIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
   *  SparseTensor, in the canonical lexicographic ordering.
   * @param aValues 1-D.  `N` non-empty values corresponding to `a_indices`.
   * @param aShape 1-D.  Shape of the input SparseTensor.
   * @param bIndices counterpart to `a_indices` for the other operand.
   * @param bValues counterpart to `a_values` for the other operand; must be of the same dtype.
   * @param bShape counterpart to `a_shape` for the other operand; the two shapes must be equal.
   * @return a new instance of SparseSparseMinimum
   */
  public <T extends TType> SparseSparseMinimum<T> sparseSparseMinimum(Operand<TInt64> aIndices,
      Operand<T> aValues, Operand<TInt64> aShape, Operand<TInt64> bIndices, Operand<T> bValues,
      Operand<TInt64> bShape) {
    return SparseSparseMinimum.create(scope, aIndices, aValues, aShape, bIndices, bValues, bShape);
  }

  /**
   * Split a `SparseTensor` into `num_split` tensors along one dimension.
   *  <p>
   *  If the `shape[split_dim]` is not an integer multiple of `num_split`. Slices
   *  `[0 : shape[split_dim] % num_split]` gets one extra dimension.
   *  For example, if `split_dim = 1` and `num_split = 2` and the input is
   *  <p>
   *      input_tensor = shape = [2, 7]
   *      [    a   d e  ]
   *      [b c          ]
   *  <p>
   *  Graphically the output tensors are:
   *  <p>
   *      output_tensor[0] = shape = [2, 4]
   *      [    a  ]
   *      [b c    ]
   *  <p>
   *      output_tensor[1] = shape = [2, 3]
   *      [ d e  ]
   *      [      ]
   *
   * @param <T> data type for {@code outputValues()} output
   * @param splitDim 0-D.  The dimension along which to split.  Must be in the range
   *  `[0, rank(shape))`.
   * @param indices 2-D tensor represents the indices of the sparse tensor.
   * @param values 1-D tensor represents the values of the sparse tensor.
   * @param shape 1-D. tensor represents the shape of the sparse tensor.
   *  output indices: A list of 1-D tensors represents the indices of the output
   *  sparse tensors.
   * @param numSplit The number of ways to split.
   * @return a new instance of SparseSplit
   */
  public <T extends TType> SparseSplit<T> sparseSplit(Operand<TInt64> splitDim,
      Operand<TInt64> indices, Operand<T> values, Operand<TInt64> shape, Long numSplit) {
    return SparseSplit.create(scope, splitDim, indices, values, shape, numSplit);
  }

  /**
   * Adds up a `SparseTensor` and a dense `Tensor`, producing a dense `Tensor`.
   *  <p>
   *  This Op does not require `a_indices` be sorted in standard lexicographic order.
   *
   * @param <U> data type for {@code output()} output
   * @param aIndices 2-D.  The `indices` of the `SparseTensor`, with shape `[nnz, ndims]`.
   * @param aValues 1-D.  The `values` of the `SparseTensor`, with shape `[nnz]`.
   * @param aShape 1-D.  The `shape` of the `SparseTensor`, with shape `[ndims]`.
   * @param b `ndims`-D Tensor.  With shape `a_shape`.
   * @return a new instance of SparseTensorDenseAdd
   */
  public <U extends TType, T extends TNumber> SparseTensorDenseAdd<U> sparseTensorDenseAdd(
      Operand<T> aIndices, Operand<U> aValues, Operand<T> aShape, Operand<U> b) {
    return SparseTensorDenseAdd.create(scope, aIndices, aValues, aShape, b);
  }

  /**
   * Multiply SparseTensor (of rank 2) "A" by dense matrix "B".
   *  <p>
   *  No validity checking is performed on the indices of A.  However, the following
   *  input format is recommended for optimal behavior:
   *  <p>
   *  if adjoint_a == false:
   *    A should be sorted in lexicographically increasing order.  Use SparseReorder
   *    if you're not sure.
   *  if adjoint_a == true:
   *    A should be sorted in order of increasing dimension 1 (i.e., "column major"
   *    order instead of "row major" order).
   *
   * @param <U> data type for {@code product()} output
   * @param aIndices 2-D.  The `indices` of the `SparseTensor`, size `[nnz, 2]` Matrix.
   * @param aValues 1-D.  The `values` of the `SparseTensor`, size `[nnz]` Vector.
   * @param aShape 1-D.  The `shape` of the `SparseTensor`, size `[2]` Vector.
   * @param b 2-D.  A dense Matrix.
   * @param options carries optional attributes values
   * @return a new instance of SparseTensorDenseMatMul
   */
  public <U extends TType, T extends TNumber> SparseTensorDenseMatMul<U> sparseTensorDenseMatMul(
      Operand<T> aIndices, Operand<U> aValues, Operand<TInt64> aShape, Operand<U> b,
      SparseTensorDenseMatMul.Options... options) {
    return SparseTensorDenseMatMul.create(scope, aIndices, aValues, aShape, b, options);
  }

  /**
   * Converts a sparse representation into a dense tensor.
   *  <p>
   *  Builds an array `dense` with shape `output_shape` such that
   *  <pre>{@code
   *  # If sparse_indices is scalar
   *  dense[i] = (i == sparse_indices ? sparse_values : default_value)
   *
   *  # If sparse_indices is a vector, then for each i
   *  dense[sparse_indices[i]] = sparse_values[i]
   *
   *  # If sparse_indices is an n by d matrix, then for each i in [0, n)
   *  dense[sparse_indices[i][0], ..., sparse_indices[i][d-1]] = sparse_values[i]
   *  }</pre>
   *  All other values in `dense` are set to `default_value`.  If `sparse_values` is a
   *  scalar, all sparse indices are set to this single value.
   *  <p>
   *  Indices should be sorted in lexicographic order, and indices must not
   *  contain any repeats. If `validate_indices` is true, these properties
   *  are checked during execution.
   *
   * @param <U> data type for {@code dense()} output
   * @param sparseIndices 0-D, 1-D, or 2-D.  `sparse_indices[i]` contains the complete
   *  index where `sparse_values[i]` will be placed.
   * @param outputShape 1-D.  Shape of the dense output tensor.
   * @param sparseValues 1-D.  Values corresponding to each row of `sparse_indices`,
   *  or a scalar value to be used for all sparse indices.
   * @param defaultValue Scalar value to set for indices not specified in
   *  `sparse_indices`.
   * @param options carries optional attributes values
   * @return a new instance of SparseToDense
   */
  public <U extends TType, T extends TNumber> SparseToDense<U> sparseToDense(
      Operand<T> sparseIndices, Operand<T> outputShape, Operand<U> sparseValues,
      Operand<U> defaultValue, SparseToDense.Options... options) {
    return SparseToDense.create(scope, sparseIndices, outputShape, sparseValues, defaultValue, options);
  }

  /**
   * Applies set operation along last dimension of 2 `SparseTensor` inputs.
   *  <p>
   *  See SetOperationOp::SetOperationFromContext for values of `set_operation`.
   *  <p>
   *  If `validate_indices` is `True`, `sparse.SparseToSparseSetOperation` validates the
   *  order and range of `set1` and `set2` indices.
   *  <p>
   *  Input `set1` is a `SparseTensor` represented by `set1_indices`, `set1_values`,
   *  and `set1_shape`. For `set1` ranked `n`, 1st `n-1` dimensions must be the same
   *  as `set2`. Dimension `n` contains values in a set, duplicates are allowed but
   *  ignored.
   *  <p>
   *  Input `set2` is a `SparseTensor` represented by `set2_indices`, `set2_values`,
   *  and `set2_shape`. For `set2` ranked `n`, 1st `n-1` dimensions must be the same
   *  as `set1`. Dimension `n` contains values in a set, duplicates are allowed but
   *  ignored.
   *  <p>
   *  If `validate_indices` is `True`, this op validates the order and range of `set1`
   *  and `set2` indices.
   *  <p>
   *  Output `result` is a `SparseTensor` represented by `result_indices`,
   *  `result_values`, and `result_shape`. For `set1` and `set2` ranked `n`, this
   *  has rank `n` and the same 1st `n-1` dimensions as `set1` and `set2`. The `nth`
   *  dimension contains the result of `set_operation` applied to the corresponding
   *  `[0...n-1]` dimension of `set`.
   *
   * @param <T> data type for {@code resultValues()} output
   * @param set1Indices 2D `Tensor`, indices of a `SparseTensor`. Must be in row-major
   *  order.
   * @param set1Values 1D `Tensor`, values of a `SparseTensor`. Must be in row-major
   *  order.
   * @param set1Shape 1D `Tensor`, shape of a `SparseTensor`. `set1_shape[0...n-1]` must
   *  be the same as `set2_shape[0...n-1]`, `set1_shape[n]` is the
   *  max set size across `0...n-1` dimensions.
   * @param set2Indices 2D `Tensor`, indices of a `SparseTensor`. Must be in row-major
   *  order.
   * @param set2Values 1D `Tensor`, values of a `SparseTensor`. Must be in row-major
   *  order.
   * @param set2Shape 1D `Tensor`, shape of a `SparseTensor`. `set2_shape[0...n-1]` must
   *  be the same as `set1_shape[0...n-1]`, `set2_shape[n]` is the
   *  max set size across `0...n-1` dimensions.
   * @param setOperation
   * @param options carries optional attributes values
   * @return a new instance of SparseToSparseSetOperation
   */
  public <T extends TType> SparseToSparseSetOperation<T> sparseToSparseSetOperation(
      Operand<TInt64> set1Indices, Operand<T> set1Values, Operand<TInt64> set1Shape,
      Operand<TInt64> set2Indices, Operand<T> set2Values, Operand<TInt64> set2Shape,
      String setOperation, SparseToSparseSetOperation.Options... options) {
    return SparseToSparseSetOperation.create(scope, set1Indices, set1Values, set1Shape, set2Indices, set2Values, set2Shape, setOperation, options);
  }

  /**
   * Read `SparseTensors` from a `SparseTensorsMap` and concatenate them.
   *  <p>
   *  The input `sparse_handles` must be an `int64` matrix of shape `[N, 1]` where
   *  `N` is the minibatch size and the rows correspond to the output handles of
   *  `AddSparseToTensorsMap` or `AddManySparseToTensorsMap`.  The ranks of the
   *  original `SparseTensor` objects that went into the given input ops must all
   *  match.  When the final `SparseTensor` is created, it has rank one
   *  higher than the ranks of the incoming `SparseTensor` objects
   *  (they have been concatenated along a new row dimension on the left).
   *  <p>
   *  The output `SparseTensor` object's shape values for all dimensions but the
   *  first are the max across the input `SparseTensor` objects' shape values
   *  for the corresponding dimensions.  Its first shape value is `N`, the minibatch
   *  size.
   *  <p>
   *  The input `SparseTensor` objects' indices are assumed ordered in
   *  standard lexicographic order.  If this is not the case, after this
   *  step run `SparseReorder` to restore index ordering.
   *  <p>
   *  For example, if the handles represent an input, which is a `[2, 3]` matrix
   *  representing two original `SparseTensor` objects:
   *  <pre>{@code
   *      index = [ 0]
   *              [10]
   *              [20]
   *      values = [1, 2, 3]
   *      shape = [50]
   *  }</pre>
   *  and
   *  <pre>{@code
   *      index = [ 2]
   *              [10]
   *      values = [4, 5]
   *      shape = [30]
   *  }</pre>
   *  then the final `SparseTensor` will be:
   *  <pre>{@code
   *      index = [0  0]
   *              [0 10]
   *              [0 20]
   *              [1  2]
   *              [1 10]
   *      values = [1, 2, 3, 4, 5]
   *      shape = [2 50]
   *  }</pre>
   *
   * @param <T> data type for {@code sparseValues()} output
   * @param sparseHandles 1-D, The `N` serialized `SparseTensor` objects.
   *  Shape: `[N]`.
   * @param dtype The `dtype` of the `SparseTensor` objects stored in the
   *  `SparseTensorsMap`.
   * @param options carries optional attributes values
   * @return a new instance of TakeManySparseFromTensorsMap
   */
  public <T extends TType> TakeManySparseFromTensorsMap<T> takeManySparseFromTensorsMap(
      Operand<TInt64> sparseHandles, DataType<T> dtype,
      TakeManySparseFromTensorsMap.Options... options) {
    return TakeManySparseFromTensorsMap.create(scope, sparseHandles, dtype, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
