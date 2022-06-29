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
import org.tensorflow.op.sparse.SparseSegmentSumGrad;
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
   * Add an {@code N}-minibatch {@code SparseTensor} to a {@code SparseTensorsMap}, return {@code N} handles.
   *  A {@code SparseTensor} of rank {@code R} is represented by three tensors: {@code sparse_indices},
   *  {@code sparse_values}, and {@code sparse_shape}, where
   *  <p>{@code sparse_indices.shape[1] == sparse_shape.shape[0] == R}
   *  <p>An {@code N}-minibatch of {@code SparseTensor} objects is represented as a {@code SparseTensor}
   *  having a first {@code sparse_indices} column taking values between {@code [0, N)}, where
   *  the minibatch size {@code N == sparse_shape[0]}.
   *  <p>The input {@code SparseTensor} must have rank {@code R} greater than 1, and the first
   *  dimension is treated as the minibatch dimension.  Elements of the {@code SparseTensor}
   *  must be sorted in increasing order of this first dimension.  The stored
   *  {@code SparseTensor} objects pointed to by each row of the output {@code sparse_handles}
   *  will have rank {@code R-1}.
   *  <p>The {@code SparseTensor} values can then be read out as part of a minibatch by passing
   *  the given keys as vector elements to {@code TakeManySparseFromTensorsMap}.  To ensure
   *  the correct {@code SparseTensorsMap} is accessed, ensure that the same
   *  {@code container} and {@code shared_name} are passed to that Op.  If no {@code shared_name}
   *  is provided here, instead use the <em>name</em> of the Operation created by calling
   *  {@code sparse.AddManySparseToTensorsMap} as the {@code shared_name} passed to
   *  {@code TakeManySparseFromTensorsMap}.  Ensure the Operations are colocated.
   *
   * @param sparseIndices 2-D.  The {@code indices} of the minibatch {@code SparseTensor}.
   *  {@code sparse_indices[:, 0]} must be ordered values in {@code [0, N)}.
   * @param sparseValues 1-D.  The {@code values} of the minibatch {@code SparseTensor}.
   * @param sparseShape 1-D.  The {@code shape} of the minibatch {@code SparseTensor}.
   *  The minibatch size {@code N == sparse_shape[0]}.
   * @param options carries optional attribute values
   * @return a new instance of AddManySparseToTensorsMap
   */
  public AddManySparseToTensorsMap addManySparseToTensorsMap(Operand<TInt64> sparseIndices,
      Operand<? extends TType> sparseValues, Operand<TInt64> sparseShape,
      AddManySparseToTensorsMap.Options... options) {
    return AddManySparseToTensorsMap.create(scope, sparseIndices, sparseValues, sparseShape, options);
  }

  /**
   * Add a {@code SparseTensor} to a {@code SparseTensorsMap} return its handle.
   *  A {@code SparseTensor} is represented by three tensors: {@code sparse_indices},
   *  {@code sparse_values}, and {@code sparse_shape}.
   *  <p>This operator takes the given {@code SparseTensor} and adds it to a container
   *  object (a {@code SparseTensorsMap}).  A unique key within this container is generated
   *  in the form of an {@code int64}, and this is the value that is returned.
   *  <p>The {@code SparseTensor} can then be read out as part of a minibatch by passing
   *  the key as a vector element to {@code TakeManySparseFromTensorsMap}.  To ensure
   *  the correct {@code SparseTensorsMap} is accessed, ensure that the same
   *  {@code container} and {@code shared_name} are passed to that Op.  If no {@code shared_name}
   *  is provided here, instead use the <em>name</em> of the Operation created by calling
   *  {@code sparse.AddSparseToTensorsMap} as the {@code shared_name} passed to
   *  {@code TakeManySparseFromTensorsMap}.  Ensure the Operations are colocated.
   *
   * @param sparseIndices 2-D.  The {@code indices} of the {@code SparseTensor}.
   * @param sparseValues 1-D.  The {@code values} of the {@code SparseTensor}.
   * @param sparseShape 1-D.  The {@code shape} of the {@code SparseTensor}.
   * @param options carries optional attribute values
   * @return a new instance of AddSparseToTensorsMap
   */
  public AddSparseToTensorsMap addSparseToTensorsMap(Operand<TInt64> sparseIndices,
      Operand<? extends TType> sparseValues, Operand<TInt64> sparseShape,
      AddSparseToTensorsMap.Options... options) {
    return AddSparseToTensorsMap.create(scope, sparseIndices, sparseValues, sparseShape, options);
  }

  /**
   * Applies set operation along last dimension of 2 {@code Tensor} inputs.
   *  See SetOperationOp::SetOperationFromContext for values of {@code set_operation}.
   *  <p>Output {@code result} is a {@code SparseTensor} represented by {@code result_indices},
   *  {@code result_values}, and {@code result_shape}. For {@code set1} and {@code set2} ranked {@code n}, this
   *  has rank {@code n} and the same 1st {@code n-1} dimensions as {@code set1} and {@code set2}. The {@code nth}
   *  dimension contains the result of {@code set_operation} applied to the corresponding
   *  {@code [0...n-1]} dimension of {@code set}.
   *
   * @param <T> data type for {@code result_values} output
   * @param set1 {@code Tensor} with rank {@code n}. 1st {@code n-1} dimensions must be the same as {@code set2}.
   *  Dimension {@code n} contains values in a set, duplicates are allowed but ignored.
   * @param set2 {@code Tensor} with rank {@code n}. 1st {@code n-1} dimensions must be the same as {@code set1}.
   *  Dimension {@code n} contains values in a set, duplicates are allowed but ignored.
   * @param setOperation The value of the setOperation attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code DenseToDenseSetOperation} output and operands
   * @return a new instance of DenseToDenseSetOperation
   */
  public <T extends TType> DenseToDenseSetOperation<T> denseToDenseSetOperation(Operand<T> set1,
      Operand<T> set2, String setOperation, DenseToDenseSetOperation.Options... options) {
    return DenseToDenseSetOperation.create(scope, set1, set2, setOperation, options);
  }

  /**
   * Applies set operation along last dimension of {@code Tensor} and {@code SparseTensor}.
   *  See SetOperationOp::SetOperationFromContext for values of {@code set_operation}.
   *  <p>Input {@code set2} is a {@code SparseTensor} represented by {@code set2_indices}, {@code set2_values},
   *  and {@code set2_shape}. For {@code set2} ranked {@code n}, 1st {@code n-1} dimensions must be the same
   *  as {@code set1}. Dimension {@code n} contains values in a set, duplicates are allowed but
   *  ignored.
   *  <p>If {@code validate_indices} is {@code True}, this op validates the order and range of {@code set2}
   *  indices.
   *  <p>Output {@code result} is a {@code SparseTensor} represented by {@code result_indices},
   *  {@code result_values}, and {@code result_shape}. For {@code set1} and {@code set2} ranked {@code n}, this
   *  has rank {@code n} and the same 1st {@code n-1} dimensions as {@code set1} and {@code set2}. The {@code nth}
   *  dimension contains the result of {@code set_operation} applied to the corresponding
   *  {@code [0...n-1]} dimension of {@code set}.
   *
   * @param <T> data type for {@code result_values} output
   * @param set1 {@code Tensor} with rank {@code n}. 1st {@code n-1} dimensions must be the same as {@code set2}.
   *  Dimension {@code n} contains values in a set, duplicates are allowed but ignored.
   * @param set2Indices 2D {@code Tensor}, indices of a {@code SparseTensor}. Must be in row-major
   *  order.
   * @param set2Values 1D {@code Tensor}, values of a {@code SparseTensor}. Must be in row-major
   *  order.
   * @param set2Shape 1D {@code Tensor}, shape of a {@code SparseTensor}. {@code set2_shape[0...n-1]} must
   *  be the same as the 1st {@code n-1} dimensions of {@code set1}, {@code result_shape[n]} is the
   *  max set size across {@code n-1} dimensions.
   * @param setOperation The value of the setOperation attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code DenseToSparseSetOperation} output and operands
   * @return a new instance of DenseToSparseSetOperation
   */
  public <T extends TType> DenseToSparseSetOperation<T> denseToSparseSetOperation(Operand<T> set1,
      Operand<TInt64> set2Indices, Operand<T> set2Values, Operand<TInt64> set2Shape,
      String setOperation, DenseToSparseSetOperation.Options... options) {
    return DenseToSparseSetOperation.create(scope, set1, set2Indices, set2Values, set2Shape, setOperation, options);
  }

  /**
   * Deserialize {@code SparseTensor} objects.
   *  The input {@code serialized_sparse} must have the shape {@code [?, ?, ..., ?, 3]} where
   *  the last dimension stores serialized {@code SparseTensor} objects and the other N
   *  dimensions (N &gt;= 0) correspond to a batch. The ranks of the original
   *  {@code SparseTensor} objects must all match. When the final {@code SparseTensor} is
   *  created, its rank is the rank of the incoming {@code SparseTensor} objects plus N;
   *  the sparse tensors have been concatenated along new dimensions, one for each
   *  batch.
   *  <p>The output {@code SparseTensor} object's shape values for the original dimensions
   *  are the max across the input {@code SparseTensor} objects' shape values for the
   *  corresponding dimensions. The new dimensions match the size of the batch.
   *  <p>The input {@code SparseTensor} objects' indices are assumed ordered in
   *  standard lexicographic order.  If this is not the case, after this
   *  step run {@code SparseReorder} to restore index ordering.
   *  <p>For example, if the serialized input is a {@code [2 x 3]} matrix representing two
   *  original {@code SparseTensor} objects:
   *  <pre>
   *  index = [ 0]
   *          [10]
   *          [20]
   *  values = [1, 2, 3]
   *  shape = [50]
   *  </pre>
   *  <p>and
   *  <pre>
   *  index = [ 2]
   *          [10]
   *  values = [4, 5]
   *  shape = [30]
   *  </pre>
   *  <p>then the final deserialized {@code SparseTensor} will be:
   *  <pre>
   *  index = [0  0]
   *          [0 10]
   *          [0 20]
   *          [1  2]
   *          [1 10]
   *  values = [1, 2, 3, 4, 5]
   *  shape = [2 50]
   *  </pre>
   *
   * @param <U> data type for {@code sparse_values} output
   * @param serializedSparse The serialized {@code SparseTensor} objects. The last dimension
   *  must have 3 columns.
   * @param dtype The {@code dtype} of the serialized {@code SparseTensor} objects.
   * @param <U> data type for {@code DeserializeSparse} output and operands
   * @return a new instance of DeserializeSparse
   */
  public <U extends TType> DeserializeSparse<U> deserializeSparse(
      Operand<? extends TType> serializedSparse, Class<U> dtype) {
    return DeserializeSparse.create(scope, serializedSparse, dtype);
  }

  /**
   * Applies a sparse gradient to a given accumulator.
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
  public SparseAccumulatorApplyGradient sparseAccumulatorApplyGradient(Operand<TString> handle,
      Operand<TInt64> localStep, Operand<TInt64> gradientIndices,
      Operand<? extends TType> gradientValues, Operand<TInt64> gradientShape,
      Boolean hasKnownShape) {
    return SparseAccumulatorApplyGradient.create(scope, handle, localStep, gradientIndices, gradientValues, gradientShape, hasKnownShape);
  }

  /**
   * Extracts the average sparse gradient in a SparseConditionalAccumulator.
   *  The op will blocks until sufficient (i.e., more than num_required)
   *  gradients have been accumulated. If the accumulator has already
   *  aggregated more than num_required gradients, it will return its
   *  average of the accumulated gradients.  Also automatically increments
   *  the recorded global_step in the accumulator by 1, and resets the
   *  aggregate to 0.
   *
   * @param <T> data type for {@code values} output
   * @param handle The handle to a SparseConditionalAccumulator.
   * @param numRequired Number of gradients required before we return an aggregate.
   * @param dtype The data type of accumulated gradients. Needs to correspond to the type
   *  of the accumulator.
   * @param <T> data type for {@code SparseAccumulatorTakeGradient} output and operands
   * @return a new instance of SparseAccumulatorTakeGradient
   */
  public <T extends TType> SparseAccumulatorTakeGradient<T> sparseAccumulatorTakeGradient(
      Operand<TString> handle, Operand<TInt32> numRequired, Class<T> dtype) {
    return SparseAccumulatorTakeGradient.create(scope, handle, numRequired, dtype);
  }

  /**
   * Adds two {@code SparseTensor} objects to produce another {@code SparseTensor}.
   *  The input {@code SparseTensor} objects' indices are assumed ordered in standard
   *  lexicographic order.  If this is not the case, before this step run
   *  {@code SparseReorder} to restore index ordering.
   *  <p>By default, if two values sum to zero at some index, the output {@code SparseTensor}
   *  would still include that particular location in its index, storing a zero in the
   *  corresponding value slot.  To override this, callers can specify {@code thresh},
   *  indicating that if the sum has a magnitude strictly smaller than {@code thresh}, its
   *  corresponding value and index would then not be included.  In particular,
   *  {@code thresh == 0} (default) means everything is kept and actual thresholding happens
   *  only for a positive value.
   *  <p>In the following shapes, {@code nnz} is the count after taking {@code thresh} into account.
   *
   * @param <T> data type for {@code sum_values} output
   * @param aIndices 2-D.  The {@code indices} of the first {@code SparseTensor}, size {@code [nnz, ndims]} Matrix.
   * @param aValues 1-D.  The {@code values} of the first {@code SparseTensor}, size {@code [nnz]} Vector.
   * @param aShape 1-D.  The {@code shape} of the first {@code SparseTensor}, size {@code [ndims]} Vector.
   * @param bIndices 2-D.  The {@code indices} of the second {@code SparseTensor}, size {@code [nnz, ndims]} Matrix.
   * @param bValues 1-D.  The {@code values} of the second {@code SparseTensor}, size {@code [nnz]} Vector.
   * @param bShape 1-D.  The {@code shape} of the second {@code SparseTensor}, size {@code [ndims]} Vector.
   * @param thresh 0-D.  The magnitude threshold that determines if an output value/index
   *  pair takes space.
   * @param <T> data type for {@code SparseAdd} output and operands
   * @return a new instance of SparseAdd
   */
  public <T extends TType> SparseAdd<T> sparseAdd(Operand<TInt64> aIndices, Operand<T> aValues,
      Operand<TInt64> aShape, Operand<TInt64> bIndices, Operand<T> bValues, Operand<TInt64> bShape,
      Operand<? extends TNumber> thresh) {
    return SparseAdd.create(scope, aIndices, aValues, aShape, bIndices, bValues, bShape, thresh);
  }

  /**
   * The gradient operator for the SparseAdd op.
   *  The SparseAdd op calculates A + B, where A, B, and the sum are all represented
   *  as {@code SparseTensor} objects.  This op takes in the upstream gradient w.r.t.
   *  non-empty values of the sum, and outputs the gradients w.r.t. the non-empty
   *  values of A and B.
   *
   * @param <T> data type for {@code a_val_grad} output
   * @param backpropValGrad 1-D with shape {@code [nnz(sum)]}.  The gradient with respect to
   *  the non-empty values of the sum.
   * @param aIndices 2-D.  The {@code indices} of the {@code SparseTensor} A, size {@code [nnz(A), ndims]}.
   * @param bIndices 2-D.  The {@code indices} of the {@code SparseTensor} B, size {@code [nnz(B), ndims]}.
   * @param sumIndices 2-D.  The {@code indices} of the sum {@code SparseTensor}, size
   *  {@code [nnz(sum), ndims]}.
   * @param <T> data type for {@code SparseAddGrad} output and operands
   * @return a new instance of SparseAddGrad
   */
  public <T extends TType> SparseAddGrad<T> sparseAddGrad(Operand<T> backpropValGrad,
      Operand<TInt64> aIndices, Operand<TInt64> bIndices, Operand<TInt64> sumIndices) {
    return SparseAddGrad.create(scope, backpropValGrad, aIndices, bIndices, sumIndices);
  }

  /**
   * Counts the number of occurrences of each value in an integer array.
   *  Outputs a vector with length {@code size} and the same dtype as {@code weights}. If
   *  {@code weights} are empty, then index {@code i} stores the number of times the value {@code i} is
   *  counted in {@code arr}. If {@code weights} are non-empty, then index {@code i} stores the sum of
   *  the value in {@code weights} at each index where the corresponding value in {@code arr} is
   *  {@code i}.
   *  <p>Values in {@code arr} outside of the range [0, size) are ignored.
   *
   * @param <U> data type for {@code output} output
   * @param indices 2D int64 {@code Tensor}.
   * @param values 1D int {@code Tensor}.
   * @param denseShape 1D int64 {@code Tensor}.
   * @param sizeOutput non-negative int scalar {@code Tensor}.
   * @param weights is an int32, int64, float32, or float64 {@code Tensor} with the same
   *  shape as {@code input}, or a length-0 {@code Tensor}, in which case it acts as all weights
   *  equal to 1.
   * @param options carries optional attribute values
   * @param <U> data type for {@code SparseBincount} output and operands
   * @param <T> data type for {@code SparseBincount} output and operands
   * @return a new instance of SparseBincount
   */
  public <U extends TNumber, T extends TNumber> SparseBincount<U> sparseBincount(
      Operand<TInt64> indices, Operand<T> values, Operand<TInt64> denseShape, Operand<T> sizeOutput,
      Operand<U> weights, SparseBincount.Options... options) {
    return SparseBincount.create(scope, indices, values, denseShape, sizeOutput, weights, options);
  }

  /**
   * Concatenates a list of {@code SparseTensor} along the specified dimension.
   *  Concatenation is with respect to the dense versions of these sparse tensors.
   *  It is assumed that each input is a {@code SparseTensor} whose elements are ordered
   *  along increasing dimension number.
   *  <p>All inputs' shapes must match, except for the concat dimension.  The
   *  {@code indices}, {@code values}, and {@code shapes} lists must have the same length.
   *  <p>The output shape is identical to the inputs', except along the concat
   *  dimension, where it is the sum of the inputs' sizes along that dimension.
   *  <p>The output elements will be resorted to preserve the sort order along
   *  increasing dimension number.
   *  <p>This op runs in {@code O(M log M)} time, where {@code M} is the total number of non-empty
   *  values across all inputs. This is due to the need for an internal sort in
   *  order to concatenate efficiently across an arbitrary dimension.
   *  <p>For example, if {@code concat_dim = 1} and the inputs are
   *  <pre>
   *  sp_inputs[0]: shape = [2, 3]
   *  [0, 2]: &quot;a&quot;
   *  [1, 0]: &quot;b&quot;
   *  [1, 1]: &quot;c&quot;
   *
   *  sp_inputs[1]: shape = [2, 4]
   *  [0, 1]: &quot;d&quot;
   *  [0, 2]: &quot;e&quot;
   *  </pre>
   *  <p>then the output will be
   *  <pre>
   *  shape = [2, 7]
   *  [0, 2]: &quot;a&quot;
   *  [0, 4]: &quot;d&quot;
   *  [0, 5]: &quot;e&quot;
   *  [1, 0]: &quot;b&quot;
   *  [1, 1]: &quot;c&quot;
   *  </pre>
   *  <p>Graphically this is equivalent to doing
   *  <pre>
   *  [    a] concat [  d e  ] = [    a   d e  ]
   *  [b c  ]        [       ]   [b c          ]
   *  </pre>
   *
   * @param <T> data type for {@code output_values} output
   * @param indices 2-D.  Indices of each input {@code SparseTensor}.
   * @param values 1-D.  Non-empty values of each {@code SparseTensor}.
   * @param shapes 1-D.  Shapes of each {@code SparseTensor}.
   * @param concatDim Dimension to concatenate along. Must be in range [-rank, rank),
   *  where rank is the number of dimensions in each input {@code SparseTensor}.
   * @param <T> data type for {@code SparseConcat} output and operands
   * @return a new instance of SparseConcat
   */
  public <T extends TType> SparseConcat<T> sparseConcat(Iterable<Operand<TInt64>> indices,
      Iterable<Operand<T>> values, Iterable<Operand<TInt64>> shapes, Long concatDim) {
    return SparseConcat.create(scope, indices, values, shapes, concatDim);
  }

  /**
   * A conditional accumulator for aggregating sparse gradients.
   *  The accumulator accepts gradients marked with local_step greater or
   *  equal to the most recent global_step known to the accumulator. The
   *  average can be extracted from the accumulator, provided sufficient
   *  gradients have been accumulated. Extracting the average automatically
   *  resets the aggregate to 0, and increments the global_step recorded by
   *  the accumulator.
   *
   * @param dtype The type of the value being accumulated.
   * @param shape The shape of the values.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseConditionalAccumulator} output and operands
   * @return a new instance of SparseConditionalAccumulator
   */
  public <T extends TType> SparseConditionalAccumulator sparseConditionalAccumulator(Class<T> dtype,
      Shape shape, SparseConditionalAccumulator.Options... options) {
    return SparseConditionalAccumulator.create(scope, dtype, shape, options);
  }

  /**
   * Generates sparse cross from a list of sparse and dense tensors.
   *  The op takes two lists, one of 2D {@code SparseTensor} and one of 2D {@code Tensor}, each
   *  representing features of one feature column. It outputs a 2D {@code SparseTensor} with
   *  the batchwise crosses of these features.
   *  <p>For example, if the inputs are
   *  <pre>
   *  inputs[0]: SparseTensor with shape = [2, 2]
   *  [0, 0]: &quot;a&quot;
   *  [1, 0]: &quot;b&quot;
   *  [1, 1]: &quot;c&quot;
   *
   *  inputs[1]: SparseTensor with shape = [2, 1]
   *  [0, 0]: &quot;d&quot;
   *  [1, 0]: &quot;e&quot;
   *
   *  inputs[2]: Tensor [[&quot;f&quot;], [&quot;g&quot;]]
   *  </pre>
   *  <p>then the output will be
   *  <pre>
   *  shape = [2, 2]
   *  [0, 0]: &quot;a_X_d_X_f&quot;
   *  [1, 0]: &quot;b_X_e_X_g&quot;
   *  [1, 1]: &quot;c_X_e_X_g&quot;
   *  </pre>
   *  <p>if hashed_output=true then the output will be
   *  <pre>
   *  shape = [2, 2]
   *  [0, 0]: FingerprintCat64(
   *              Fingerprint64(&quot;f&quot;), FingerprintCat64(
   *                  Fingerprint64(&quot;d&quot;), Fingerprint64(&quot;a&quot;)))
   *  [1, 0]: FingerprintCat64(
   *              Fingerprint64(&quot;g&quot;), FingerprintCat64(
   *                  Fingerprint64(&quot;e&quot;), Fingerprint64(&quot;b&quot;)))
   *  [1, 1]: FingerprintCat64(
   *              Fingerprint64(&quot;g&quot;), FingerprintCat64(
   *                  Fingerprint64(&quot;e&quot;), Fingerprint64(&quot;c&quot;)))
   *  </pre>
   *
   * @param indices 2-D.  Indices of each input {@code SparseTensor}.
   * @param values 1-D.   values of each {@code SparseTensor}.
   * @param shapes 1-D.   Shapes of each {@code SparseTensor}.
   * @param denseInputs 2-D.    Columns represented by dense {@code Tensor}.
   * @param sep string used when joining a list of string inputs, can be used as separator later.
   * @return a new instance of SparseCross
   */
  public SparseCross sparseCross(Iterable<Operand<TInt64>> indices, Iterable<Operand<?>> values,
      Iterable<Operand<TInt64>> shapes, Iterable<Operand<?>> denseInputs, Operand<TString> sep) {
    return SparseCross.create(scope, indices, values, shapes, denseInputs, sep);
  }

  /**
   * Generates sparse cross from a list of sparse and dense tensors.
   *  The op takes two lists, one of 2D {@code SparseTensor} and one of 2D {@code Tensor}, each
   *  representing features of one feature column. It outputs a 2D {@code SparseTensor} with
   *  the batchwise crosses of these features.
   *  <p>For example, if the inputs are
   *  <pre>
   *  inputs[0]: SparseTensor with shape = [2, 2]
   *  [0, 0]: &quot;a&quot;
   *  [1, 0]: &quot;b&quot;
   *  [1, 1]: &quot;c&quot;
   *
   *  inputs[1]: SparseTensor with shape = [2, 1]
   *  [0, 0]: &quot;d&quot;
   *  [1, 0]: &quot;e&quot;
   *
   *  inputs[2]: Tensor [[&quot;f&quot;], [&quot;g&quot;]]
   *  </pre>
   *  <p>then the output will be
   *  <pre>
   *  shape = [2, 2]
   *  [0, 0]: &quot;a_X_d_X_f&quot;
   *  [1, 0]: &quot;b_X_e_X_g&quot;
   *  [1, 1]: &quot;c_X_e_X_g&quot;
   *  </pre>
   *  <p>if hashed_output=true then the output will be
   *  <pre>
   *  shape = [2, 2]
   *  [0, 0]: FingerprintCat64(
   *              Fingerprint64(&quot;f&quot;), FingerprintCat64(
   *                  Fingerprint64(&quot;d&quot;), Fingerprint64(&quot;a&quot;)))
   *  [1, 0]: FingerprintCat64(
   *              Fingerprint64(&quot;g&quot;), FingerprintCat64(
   *                  Fingerprint64(&quot;e&quot;), Fingerprint64(&quot;b&quot;)))
   *  [1, 1]: FingerprintCat64(
   *              Fingerprint64(&quot;g&quot;), FingerprintCat64(
   *                  Fingerprint64(&quot;e&quot;), Fingerprint64(&quot;c&quot;)))
   *  </pre>
   *
   * @param indices 2-D.  Indices of each input {@code SparseTensor}.
   * @param values 1-D.   values of each {@code SparseTensor}.
   * @param shapes 1-D.   Shapes of each {@code SparseTensor}.
   * @param denseInputs 2-D.    Columns represented by dense {@code Tensor}.
   * @param numBuckets It is used if hashed_output is true.
   *  output = hashed_value%num_buckets if num_buckets &gt; 0 else hashed_value.
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
   *  (1) Broadcasts the dense side to have the same shape as the sparse side, if
   *  eligible;
   *  (2) Then, only the dense values pointed to by the indices of the SparseTensor
   *  participate in the cwise addition.
   *  <p>By these rules, the result is a logical SparseTensor with exactly the same
   *  indices and shape, but possibly with different non-zero values.  The output of
   *  this Op is the resultant non-zero values.
   *
   * @param <T> data type for {@code output} output
   * @param spIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param spValues 1-D.  {@code N} non-empty values corresponding to {@code sp_indices}.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param dense {@code R}-D.  The dense Tensor operand.
   * @param <T> data type for {@code SparseDenseCwiseAdd} output and operands
   * @return a new instance of SparseDenseCwiseAdd
   */
  public <T extends TType> SparseDenseCwiseAdd<T> sparseDenseCwiseAdd(Operand<TInt64> spIndices,
      Operand<T> spValues, Operand<TInt64> spShape, Operand<T> dense) {
    return SparseDenseCwiseAdd.create(scope, spIndices, spValues, spShape, dense);
  }

  /**
   * Component-wise divides a SparseTensor by a dense Tensor.
   *  <em>Limitation</em>: this Op only broadcasts the dense side to the sparse side, but not
   *  the other direction.
   *
   * @param <T> data type for {@code output} output
   * @param spIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param spValues 1-D.  {@code N} non-empty values corresponding to {@code sp_indices}.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param dense {@code R}-D.  The dense Tensor operand.
   * @param <T> data type for {@code SparseDenseCwiseDiv} output and operands
   * @return a new instance of SparseDenseCwiseDiv
   */
  public <T extends TType> SparseDenseCwiseDiv<T> sparseDenseCwiseDiv(Operand<TInt64> spIndices,
      Operand<T> spValues, Operand<TInt64> spShape, Operand<T> dense) {
    return SparseDenseCwiseDiv.create(scope, spIndices, spValues, spShape, dense);
  }

  /**
   * Component-wise multiplies a SparseTensor by a dense Tensor.
   *  The output locations corresponding to the implicitly zero elements in the sparse
   *  tensor will be zero (i.e., will not take up storage space), regardless of the
   *  contents of the dense tensor (even if it's +/-INF and that INF*0 == NaN).
   *  <p><em>Limitation</em>: this Op only broadcasts the dense side to the sparse side, but not
   *  the other direction.
   *
   * @param <T> data type for {@code output} output
   * @param spIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param spValues 1-D.  {@code N} non-empty values corresponding to {@code sp_indices}.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param dense {@code R}-D.  The dense Tensor operand.
   * @param <T> data type for {@code SparseDenseCwiseMul} output and operands
   * @return a new instance of SparseDenseCwiseMul
   */
  public <T extends TType> SparseDenseCwiseMul<T> sparseDenseCwiseMul(Operand<TInt64> spIndices,
      Operand<T> spValues, Operand<TInt64> spShape, Operand<T> dense) {
    return SparseDenseCwiseMul.create(scope, spIndices, spValues, spShape, dense);
  }

  /**
   * Fills empty rows in the input 2-D {@code SparseTensor} with a default value.
   *  The input {@code SparseTensor} is represented via the tuple of inputs
   *  ({@code indices}, {@code values}, {@code dense_shape}).  The output {@code SparseTensor} has the
   *  same {@code dense_shape} but with indices {@code output_indices} and values
   *  {@code output_values}.
   *  <p>This op inserts a single entry for every row that doesn't have any values.
   *  The index is created as {@code [row, 0, ..., 0]} and the inserted value
   *  is {@code default_value}.
   *  <p>For example, suppose {@code sp_input} has shape {@code [5, 6]} and non-empty values:
   *  <pre>
   *  [0, 1]: a
   *  [0, 3]: b
   *  [2, 0]: c
   *  [3, 1]: d
   *  </pre>
   *  <p>Rows 1 and 4 are empty, so the output will be of shape {@code [5, 6]} with values:
   *  <pre>
   *  [0, 1]: a
   *  [0, 3]: b
   *  [1, 0]: default_value
   *  [2, 0]: c
   *  [3, 1]: d
   *  [4, 0]: default_value
   *  </pre>
   *  <p>The output {@code SparseTensor} will be in row-major order and will have the
   *  same shape as the input.
   *  <p>This op also returns an indicator vector shaped {@code [dense_shape[0]]} such that
   *  <pre>
   *  empty_row_indicator[i] = True iff row i was an empty row.
   *  </pre>
   *  <p>And a reverse index map vector shaped {@code [indices.shape[0]]} that is used during
   *  backpropagation,
   *  <pre>
   *  reverse_index_map[j] = out_j s.t. indices[j, :] == output_indices[out_j, :]
   *  </pre>
   *
   * @param <T> data type for {@code output_values} output
   * @param indices 2-D. the indices of the sparse tensor.
   * @param values 1-D. the values of the sparse tensor.
   * @param denseShape 1-D. the shape of the sparse tensor.
   * @param defaultValue 0-D. default value to insert into location {@code [row, 0, ..., 0]}
   *  for rows missing from the input sparse tensor.
   *  output indices: 2-D. the indices of the filled sparse tensor.
   * @param <T> data type for {@code SparseFillEmptyRows} output and operands
   * @return a new instance of SparseFillEmptyRows
   */
  public <T extends TType> SparseFillEmptyRows<T> sparseFillEmptyRows(Operand<TInt64> indices,
      Operand<T> values, Operand<TInt64> denseShape, Operand<T> defaultValue) {
    return SparseFillEmptyRows.create(scope, indices, values, denseShape, defaultValue);
  }

  /**
   * The gradient of SparseFillEmptyRows.
   *  Takes vectors reverse_index_map, shaped {@code [N]}, and grad_values,
   *  shaped {@code [N_full]}, where {@code N_full >= N} and copies data into either
   *  {@code d_values} or {@code d_default_value}.  Here {@code d_values} is shaped {@code [N]} and
   *  {@code d_default_value} is a scalar.
   *  <p>d_values[j] = grad_values[reverse_index_map[j]]
   *  d_default_value = sum_{k : 0 .. N_full - 1} (
   *  grad_values[k] * 1{k not in reverse_index_map})
   *
   * @param <T> data type for {@code d_values} output
   * @param reverseIndexMap 1-D.  The reverse index map from SparseFillEmptyRows.
   * @param gradValues 1-D.  The gradients from backprop.
   * @param <T> data type for {@code SparseFillEmptyRowsGrad} output and operands
   * @return a new instance of SparseFillEmptyRowsGrad
   */
  public <T extends TType> SparseFillEmptyRowsGrad<T> sparseFillEmptyRowsGrad(
      Operand<TInt64> reverseIndexMap, Operand<T> gradValues) {
    return SparseFillEmptyRowsGrad.create(scope, reverseIndexMap, gradValues);
  }

  /**
   * Multiply matrix &quot;a&quot; by matrix &quot;b&quot;.
   *  The inputs must be two-dimensional matrices and the inner dimension of &quot;a&quot; must
   *  match the outer dimension of &quot;b&quot;. Both &quot;a&quot; and &quot;b&quot; must be {@code Tensor}s not
   *  {@code SparseTensor}s.  This op is optimized for the case where at least one of &quot;a&quot; or
   *  &quot;b&quot; is sparse, in the sense that they have a large proportion of zero values.
   *  The breakeven for using this versus a dense matrix multiply on one platform was
   *  30% zero values in the sparse matrix.
   *  <p>The gradient computation of this operation will only take advantage of sparsity
   *  in the input gradient when that gradient comes from a Relu.
   *
   * @param a The a value
   * @param b The b value
   * @param options carries optional attribute values
   * @return a new instance of SparseMatMul
   */
  public SparseMatMul sparseMatMul(Operand<? extends TNumber> a, Operand<? extends TNumber> b,
      SparseMatMul.Options... options) {
    return SparseMatMul.create(scope, a, b, options);
  }

  /**
   * Computes the max of elements across dimensions of a SparseTensor.
   *  This Op takes a SparseTensor and is the sparse counterpart to
   *  {@code tf.reduce_max()}.  In particular, this Op also returns a dense {@code Tensor}
   *  instead of a sparse one.
   *  <p>Reduces {@code sp_input} along the dimensions given in {@code reduction_axes}.  Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code reduction_axes}. If {@code keep_dims} is true, the reduced dimensions are retained
   *  with length 1.
   *  <p>If {@code reduction_axes} has no entries, all dimensions are reduced, and a tensor
   *  with a single element is returned.  Additionally, the axes can be negative,
   *  which are interpreted according to the indexing rules in Python.
   *
   * @param <T> data type for {@code output} output
   * @param inputIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  {@code N} non-empty values corresponding to {@code input_indices}.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-{@code K} vector containing the reduction axes.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseReduceMax} output and operands
   * @return a new instance of SparseReduceMax
   */
  public <T extends TNumber> SparseReduceMax<T> sparseReduceMax(Operand<TInt64> inputIndices,
      Operand<T> inputValues, Operand<TInt64> inputShape, Operand<TInt32> reductionAxes,
      SparseReduceMax.Options... options) {
    return SparseReduceMax.create(scope, inputIndices, inputValues, inputShape, reductionAxes, options);
  }

  /**
   * Computes the max of elements across dimensions of a SparseTensor.
   *  This Op takes a SparseTensor and is the sparse counterpart to
   *  {@code tf.reduce_max()}.  In contrast to SparseReduceMax, this Op returns a
   *  SparseTensor.
   *  <p>Reduces {@code sp_input} along the dimensions given in {@code reduction_axes}.  Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code reduction_axes}. If {@code keep_dims} is true, the reduced dimensions are retained
   *  with length 1.
   *  <p>If {@code reduction_axes} has no entries, all dimensions are reduced, and a tensor
   *  with a single element is returned.  Additionally, the axes can be negative,
   *  which are interpreted according to the indexing rules in Python.
   *
   * @param <T> data type for {@code output_values} output
   * @param inputIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  {@code N} non-empty values corresponding to {@code input_indices}.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-{@code K} vector containing the reduction axes.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseReduceMaxSparse} output and operands
   * @return a new instance of SparseReduceMaxSparse
   */
  public <T extends TNumber> SparseReduceMaxSparse<T> sparseReduceMaxSparse(
      Operand<TInt64> inputIndices, Operand<T> inputValues, Operand<TInt64> inputShape,
      Operand<TInt32> reductionAxes, SparseReduceMaxSparse.Options... options) {
    return SparseReduceMaxSparse.create(scope, inputIndices, inputValues, inputShape, reductionAxes, options);
  }

  /**
   * Computes the sum of elements across dimensions of a SparseTensor.
   *  This Op takes a SparseTensor and is the sparse counterpart to
   *  {@code tf.reduce_sum()}.  In particular, this Op also returns a dense {@code Tensor}
   *  instead of a sparse one.
   *  <p>Reduces {@code sp_input} along the dimensions given in {@code reduction_axes}.  Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code reduction_axes}. If {@code keep_dims} is true, the reduced dimensions are retained
   *  with length 1.
   *  <p>If {@code reduction_axes} has no entries, all dimensions are reduced, and a tensor
   *  with a single element is returned.  Additionally, the axes can be negative,
   *  which are interpreted according to the indexing rules in Python.
   *
   * @param <T> data type for {@code output} output
   * @param inputIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  {@code N} non-empty values corresponding to {@code input_indices}.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-{@code K} vector containing the reduction axes.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseReduceSum} output and operands
   * @return a new instance of SparseReduceSum
   */
  public <T extends TType> SparseReduceSum<T> sparseReduceSum(Operand<TInt64> inputIndices,
      Operand<T> inputValues, Operand<TInt64> inputShape, Operand<TInt32> reductionAxes,
      SparseReduceSum.Options... options) {
    return SparseReduceSum.create(scope, inputIndices, inputValues, inputShape, reductionAxes, options);
  }

  /**
   * Computes the sum of elements across dimensions of a SparseTensor.
   *  This Op takes a SparseTensor and is the sparse counterpart to
   *  {@code tf.reduce_sum()}.  In contrast to SparseReduceSum, this Op returns a
   *  SparseTensor.
   *  <p>Reduces {@code sp_input} along the dimensions given in {@code reduction_axes}.  Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code reduction_axes}. If {@code keep_dims} is true, the reduced dimensions are retained
   *  with length 1.
   *  <p>If {@code reduction_axes} has no entries, all dimensions are reduced, and a tensor
   *  with a single element is returned.  Additionally, the axes can be negative,
   *  which are interpreted according to the indexing rules in Python.
   *
   * @param <T> data type for {@code output_values} output
   * @param inputIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  {@code N} non-empty values corresponding to {@code input_indices}.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-{@code K} vector containing the reduction axes.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseReduceSumSparse} output and operands
   * @return a new instance of SparseReduceSumSparse
   */
  public <T extends TType> SparseReduceSumSparse<T> sparseReduceSumSparse(
      Operand<TInt64> inputIndices, Operand<T> inputValues, Operand<TInt64> inputShape,
      Operand<TInt32> reductionAxes, SparseReduceSumSparse.Options... options) {
    return SparseReduceSumSparse.create(scope, inputIndices, inputValues, inputShape, reductionAxes, options);
  }

  /**
   * Reorders a SparseTensor into the canonical, row-major ordering.
   *  Note that by convention, all sparse ops preserve the canonical ordering along
   *  increasing dimension number. The only time ordering can be violated is during
   *  manual manipulation of the indices and values vectors to add entries.
   *  <p>Reordering does not affect the shape of the SparseTensor.
   *  <p>If the tensor has rank {@code R} and {@code N} non-empty values, {@code input_indices} has
   *  shape {@code [N, R]}, input_values has length {@code N}, and input_shape has length {@code R}.
   *
   * @param <T> data type for {@code output_values} output
   * @param inputIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   *  SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  {@code N} non-empty values corresponding to {@code input_indices}.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param <T> data type for {@code SparseReorder} output and operands
   * @return a new instance of SparseReorder
   */
  public <T extends TType> SparseReorder<T> sparseReorder(Operand<TInt64> inputIndices,
      Operand<T> inputValues, Operand<TInt64> inputShape) {
    return SparseReorder.create(scope, inputIndices, inputValues, inputShape);
  }

  /**
   * Reshapes a SparseTensor to represent values in a new dense shape.
   *  This operation has the same semantics as reshape on the represented dense
   *  tensor.  The {@code input_indices} are recomputed based on the requested {@code new_shape}.
   *  <p>If one component of {@code new_shape} is the special value -1, the size of that
   *  dimension is computed so that the total dense size remains constant.  At
   *  most one component of {@code new_shape} can be -1.  The number of dense elements
   *  implied by {@code new_shape} must be the same as the number of dense elements
   *  originally implied by {@code input_shape}.
   *  <p>Reshaping does not affect the order of values in the SparseTensor.
   *  <p>If the input tensor has rank {@code R_in} and {@code N} non-empty values, and {@code new_shape}
   *  has length {@code R_out}, then {@code input_indices} has shape {@code [N, R_in]},
   *  {@code input_shape} has length {@code R_in}, {@code output_indices} has shape {@code [N, R_out]}, and
   *  {@code output_shape} has length {@code R_out}.
   *
   * @param inputIndices 2-D.  {@code N x R_in} matrix with the indices of non-empty values in a
   *  SparseTensor.
   * @param inputShape 1-D.  {@code R_in} vector with the input SparseTensor's dense shape.
   * @param newShape 1-D.  {@code R_out} vector with the requested new dense shape.
   * @return a new instance of SparseReshape
   */
  public SparseReshape sparseReshape(Operand<TInt64> inputIndices, Operand<TInt64> inputShape,
      Operand<TInt64> newShape) {
    return SparseReshape.create(scope, inputIndices, inputShape, newShape);
  }

  /**
   * Computes the mean along sparse segments of a tensor.
   *  See {@code tf.sparse.segment_sum} for usage examples.
   *  <p>Like {@code SegmentMean}, but {@code segment_ids} can have rank less than {@code data}'s first
   *  dimension, selecting a subset of dimension 0, specified by {@code indices}.
   *
   * @param <T> data type for {@code output} output
   * @param data The data value
   * @param indices A 1-D tensor. Has same rank as {@code segment_ids}.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param <T> data type for {@code SparseSegmentMean} output and operands
   * @return a new instance of SparseSegmentMean
   */
  public <T extends TNumber> SparseSegmentMean<T> sparseSegmentMean(Operand<T> data,
      Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds) {
    return SparseSegmentMean.create(scope, data, indices, segmentIds);
  }

  /**
   * Computes gradients for SparseSegmentMean.
   *  Returns tensor &quot;output&quot; with same shape as grad, except for dimension 0 whose
   *  value is output_dim0.
   *
   * @param <T> data type for {@code output} output
   * @param grad gradient propagated to the SparseSegmentMean op.
   * @param indices indices passed to the corresponding SparseSegmentMean op.
   * @param segmentIds segment_ids passed to the corresponding SparseSegmentMean op.
   * @param outputDim0 dimension 0 of &quot;data&quot; passed to SparseSegmentMean op.
   * @param <T> data type for {@code SparseSegmentMeanGrad} output and operands
   * @return a new instance of SparseSegmentMeanGrad
   */
  public <T extends TNumber> SparseSegmentMeanGrad<T> sparseSegmentMeanGrad(Operand<T> grad,
      Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds,
      Operand<TInt32> outputDim0) {
    return SparseSegmentMeanGrad.create(scope, grad, indices, segmentIds, outputDim0);
  }

  /**
   * Computes the mean along sparse segments of a tensor.
   *  Like {@code SparseSegmentMean}, but allows missing ids in {@code segment_ids}. If an id is
   *  missing, the {@code output} tensor at that position will be zeroed.
   *  <p>Read
   *   <a href="https://tensorflow.org/api_docs/python/tf/math#Segmentation">the section on segmentation</a> 
   *  for an explanation of segments.
   *
   * @param <T> data type for {@code output} output
   * @param data The data value
   * @param indices A 1-D tensor. Has same rank as {@code segment_ids}.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param numSegments Should equal the number of distinct segment IDs.
   * @param <T> data type for {@code SparseSegmentMeanWithNumSegments} output and operands
   * @return a new instance of SparseSegmentMeanWithNumSegments
   */
  public <T extends TNumber> SparseSegmentMeanWithNumSegments<T> sparseSegmentMeanWithNumSegments(
      Operand<T> data, Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds,
      Operand<? extends TNumber> numSegments) {
    return SparseSegmentMeanWithNumSegments.create(scope, data, indices, segmentIds, numSegments);
  }

  /**
   * Computes the sum along sparse segments of a tensor divided by the sqrt of N.
   *  N is the size of the segment being reduced.
   *  <p>See {@code tf.sparse.segment_sum} for usage examples.
   *
   * @param <T> data type for {@code output} output
   * @param data The data value
   * @param indices A 1-D tensor. Has same rank as {@code segment_ids}.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param <T> data type for {@code SparseSegmentSqrtN} output and operands
   * @return a new instance of SparseSegmentSqrtN
   */
  public <T extends TNumber> SparseSegmentSqrtN<T> sparseSegmentSqrtN(Operand<T> data,
      Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds) {
    return SparseSegmentSqrtN.create(scope, data, indices, segmentIds);
  }

  /**
   * Computes gradients for SparseSegmentSqrtN.
   *  Returns tensor &quot;output&quot; with same shape as grad, except for dimension 0 whose
   *  value is output_dim0.
   *
   * @param <T> data type for {@code output} output
   * @param grad gradient propagated to the SparseSegmentSqrtN op.
   * @param indices indices passed to the corresponding SparseSegmentSqrtN op.
   * @param segmentIds segment_ids passed to the corresponding SparseSegmentSqrtN op.
   * @param outputDim0 dimension 0 of &quot;data&quot; passed to SparseSegmentSqrtN op.
   * @param <T> data type for {@code SparseSegmentSqrtNGrad} output and operands
   * @return a new instance of SparseSegmentSqrtNGrad
   */
  public <T extends TNumber> SparseSegmentSqrtNGrad<T> sparseSegmentSqrtNGrad(Operand<T> grad,
      Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds,
      Operand<TInt32> outputDim0) {
    return SparseSegmentSqrtNGrad.create(scope, grad, indices, segmentIds, outputDim0);
  }

  /**
   * Computes the sum along sparse segments of a tensor divided by the sqrt of N.
   *  N is the size of the segment being reduced.
   *  <p>Like {@code SparseSegmentSqrtN}, but allows missing ids in {@code segment_ids}. If an id is
   *  missing, the {@code output} tensor at that position will be zeroed.
   *  <p>Read
   *   <a href="https://tensorflow.org/api_docs/python/tf/math#Segmentation">the section on segmentation</a> 
   *  for an explanation of segments.
   *
   * @param <T> data type for {@code output} output
   * @param data The data value
   * @param indices A 1-D tensor. Has same rank as {@code segment_ids}.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param numSegments Should equal the number of distinct segment IDs.
   * @param <T> data type for {@code SparseSegmentSqrtNWithNumSegments} output and operands
   * @return a new instance of SparseSegmentSqrtNWithNumSegments
   */
  public <T extends TNumber> SparseSegmentSqrtNWithNumSegments<T> sparseSegmentSqrtNWithNumSegments(
      Operand<T> data, Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds,
      Operand<? extends TNumber> numSegments) {
    return SparseSegmentSqrtNWithNumSegments.create(scope, data, indices, segmentIds, numSegments);
  }

  /**
   * Computes the sum along sparse segments of a tensor.
   *  Read
   *   <a href="https://tensorflow.org/api_docs/python/tf/math#Segmentation">the section on segmentation</a> 
   *  for an explanation of segments.
   *  <p>Like {@code SegmentSum}, but {@code segment_ids} can have rank less than {@code data}'s first
   *  dimension, selecting a subset of dimension 0, specified by {@code indices}.
   *  <p>For example:
   *  <pre>
   *  c = tf.constant([[1,2,3,4], [-1,-2,-3,-4], [5,6,7,8]])
   *
   *  # Select two rows, one segment.
   *  tf.sparse_segment_sum(c, tf.constant([0, 1]), tf.constant([0, 0]))
   *  # =&gt; [[0 0 0 0]]
   *
   *  # Select two rows, two segment.
   *  tf.sparse_segment_sum(c, tf.constant([0, 1]), tf.constant([0, 1]))
   *  # =&gt; [[ 1  2  3  4]
   *  #     [-1 -2 -3 -4]]
   *
   *  # Select all rows, two segments.
   *  tf.sparse_segment_sum(c, tf.constant([0, 1, 2]), tf.constant([0, 0, 1]))
   *  # =&gt; [[0 0 0 0]
   *  #     [5 6 7 8]]
   *
   *  # Which is equivalent to:
   *  tf.segment_sum(c, tf.constant([0, 0, 1]))
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param data The data value
   * @param indices A 1-D tensor. Has same rank as {@code segment_ids}.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param <T> data type for {@code SparseSegmentSum} output and operands
   * @return a new instance of SparseSegmentSum
   */
  public <T extends TNumber> SparseSegmentSum<T> sparseSegmentSum(Operand<T> data,
      Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds) {
    return SparseSegmentSum.create(scope, data, indices, segmentIds);
  }

  /**
   * Computes gradients for SparseSegmentSum.
   *  Returns tensor &quot;output&quot; with same shape as grad, except for dimension 0 whose
   *  value is output_dim0.
   *
   * @param <T> data type for {@code output} output
   * @param grad gradient propagated to the SparseSegmentSum op.
   * @param indices indices passed to the corresponding SparseSegmentSum op.
   * @param segmentIds segment_ids passed to the corresponding SparseSegmentSum op.
   * @param outputDim0 dimension 0 of &quot;data&quot; passed to SparseSegmentSum op.
   * @param <T> data type for {@code SparseSegmentSumGrad} output and operands
   * @return a new instance of SparseSegmentSumGrad
   */
  public <T extends TNumber> SparseSegmentSumGrad<T> sparseSegmentSumGrad(Operand<T> grad,
      Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds,
      Operand<TInt32> outputDim0) {
    return SparseSegmentSumGrad.create(scope, grad, indices, segmentIds, outputDim0);
  }

  /**
   * Computes the sum along sparse segments of a tensor.
   *  Like {@code SparseSegmentSum}, but allows missing ids in {@code segment_ids}. If an id is
   *  missing, the {@code output} tensor at that position will be zeroed.
   *  <p>Read
   *   <a href="https://tensorflow.org/api_docs/python/tf/sparse#Segmentation">the section on segmentation</a> 
   *  for an explanation of segments.
   *  <p>For example:
   *  <pre>
   *  c = tf.constant([[1,2,3,4], [-1,-2,-3,-4], [5,6,7,8]])
   *
   *  tf.sparse_segment_sum_with_num_segments(
   *      c, tf.constant([0, 1]), tf.constant([0, 0]), num_segments=3)
   *  # =&gt; [[0 0 0 0]
   *  #     [0 0 0 0]
   *  #     [0 0 0 0]]
   *
   *  tf.sparse_segment_sum_with_num_segments(c,
   *                                          tf.constant([0, 1]),
   *                                          tf.constant([0, 2],
   *                                          num_segments=4))
   *  # =&gt; [[ 1  2  3  4]
   *  #     [ 0  0  0  0]
   *  #     [-1 -2 -3 -4]
   *  #     [ 0  0  0  0]]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param data The data value
   * @param indices A 1-D tensor. Has same rank as {@code segment_ids}.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param numSegments Should equal the number of distinct segment IDs.
   * @param <T> data type for {@code SparseSegmentSumWithNumSegments} output and operands
   * @return a new instance of SparseSegmentSumWithNumSegments
   */
  public <T extends TNumber> SparseSegmentSumWithNumSegments<T> sparseSegmentSumWithNumSegments(
      Operand<T> data, Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds,
      Operand<? extends TNumber> numSegments) {
    return SparseSegmentSumWithNumSegments.create(scope, data, indices, segmentIds, numSegments);
  }

  /**
   * Slice a {@code SparseTensor} based on the {@code start} and {@code size}.
   *  For example, if the input is
   *  <pre>
   *  input_tensor = shape = [2, 7]
   *  [    a   d e  ]
   *  [b c          ]
   *  </pre>
   *  <p>Graphically the output tensors are:
   *  <pre>
   *  sparse_slice([0, 0], [2, 4]) = shape = [2, 4]
   *  [    a  ]
   *  [b c    ]
   *
   *  sparse_slice([0, 4], [2, 3]) = shape = [2, 3]
   *  [ d e  ]
   *  [      ]
   *  </pre>
   *
   * @param <T> data type for {@code output_values} output
   * @param indices 2-D tensor represents the indices of the sparse tensor.
   * @param values 1-D tensor represents the values of the sparse tensor.
   * @param shape 1-D. tensor represents the shape of the sparse tensor.
   * @param start 1-D. tensor represents the start of the slice.
   * @param sizeOutput 1-D. tensor represents the size of the slice.
   *  output indices: A list of 1-D tensors represents the indices of the output
   *  sparse tensors.
   * @param <T> data type for {@code SparseSlice} output and operands
   * @return a new instance of SparseSlice
   */
  public <T extends TType> SparseSlice<T> sparseSlice(Operand<TInt64> indices, Operand<T> values,
      Operand<TInt64> shape, Operand<TInt64> start, Operand<TInt64> sizeOutput) {
    return SparseSlice.create(scope, indices, values, shape, start, sizeOutput);
  }

  /**
   * The gradient operator for the SparseSlice op.
   *  This op takes in the upstream gradient w.r.t. non-empty values of
   *  the sliced {@code SparseTensor}, and outputs the gradients w.r.t.
   *  the non-empty values of input {@code SparseTensor}.
   *
   * @param <T> data type for {@code val_grad} output
   * @param backpropValGrad 1-D. The gradient with respect to
   *  the non-empty values of the sliced {@code SparseTensor}.
   * @param inputIndices 2-D.  The {@code indices} of the input {@code SparseTensor}.
   * @param inputStart 1-D. tensor represents the start of the slice.
   * @param outputIndices 2-D.  The {@code indices} of the sliced {@code SparseTensor}.
   * @param <T> data type for {@code SparseSliceGrad} output and operands
   * @return a new instance of SparseSliceGrad
   */
  public <T extends TType> SparseSliceGrad<T> sparseSliceGrad(Operand<T> backpropValGrad,
      Operand<TInt64> inputIndices, Operand<TInt64> inputStart, Operand<TInt64> outputIndices) {
    return SparseSliceGrad.create(scope, backpropValGrad, inputIndices, inputStart, outputIndices);
  }

  /**
   * Applies softmax to a batched N-D {@code SparseTensor}.
   *  The inputs represent an N-D SparseTensor  with logical shape {@code [..., B, C]}
   *  (where {@code N >= 2}), and with indices sorted in the canonical lexicographic order.
   *  <p>This op is equivalent to applying the normal {@code tf.nn.softmax()} to each innermost
   *  logical submatrix with shape {@code [B, C]}, but with the catch that <em>the implicitly
   *  zero elements do not participate</em>.  Specifically, the algorithm is equivalent
   *  to the following:
   *  <p>(1) Applies {@code tf.nn.softmax()} to a densified view of each innermost submatrix
   *  with shape {@code [B, C]}, along the size-C dimension;
   *  (2) Masks out the original implicitly-zero locations;
   *  (3) Renormalizes the remaining elements.
   *  <p>Hence, the {@code SparseTensor} result has exactly the same non-zero indices and
   *  shape.
   *
   * @param <T> data type for {@code output} output
   * @param spIndices 2-D.  {@code NNZ x R} matrix with the indices of non-empty values in a
   *  SparseTensor, in canonical ordering.
   * @param spValues 1-D.  {@code NNZ} non-empty values corresponding to {@code sp_indices}.
   * @param spShape 1-D.  Shape of the input SparseTensor.
   * @param <T> data type for {@code SparseSoftmax} output and operands
   * @return a new instance of SparseSoftmax
   */
  public <T extends TNumber> SparseSoftmax<T> sparseSoftmax(Operand<TInt64> spIndices,
      Operand<T> spValues, Operand<TInt64> spShape) {
    return SparseSoftmax.create(scope, spIndices, spValues, spShape);
  }

  /**
   * Returns the element-wise max of two SparseTensors.
   *  Assumes the two SparseTensors have the same shape, i.e., no broadcasting.
   *
   * @param <T> data type for {@code output_values} output
   * @param aIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   *  SparseTensor, in the canonical lexicographic ordering.
   * @param aValues 1-D.  {@code N} non-empty values corresponding to {@code a_indices}.
   * @param aShape 1-D.  Shape of the input SparseTensor.
   * @param bIndices counterpart to {@code a_indices} for the other operand.
   * @param bValues counterpart to {@code a_values} for the other operand; must be of the same dtype.
   * @param bShape counterpart to {@code a_shape} for the other operand; the two shapes must be equal.
   * @param <T> data type for {@code SparseSparseMaximum} output and operands
   * @return a new instance of SparseSparseMaximum
   */
  public <T extends TNumber> SparseSparseMaximum<T> sparseSparseMaximum(Operand<TInt64> aIndices,
      Operand<T> aValues, Operand<TInt64> aShape, Operand<TInt64> bIndices, Operand<T> bValues,
      Operand<TInt64> bShape) {
    return SparseSparseMaximum.create(scope, aIndices, aValues, aShape, bIndices, bValues, bShape);
  }

  /**
   * Returns the element-wise min of two SparseTensors.
   *  Assumes the two SparseTensors have the same shape, i.e., no broadcasting.
   *
   * @param <T> data type for {@code output_values} output
   * @param aIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   *  SparseTensor, in the canonical lexicographic ordering.
   * @param aValues 1-D.  {@code N} non-empty values corresponding to {@code a_indices}.
   * @param aShape 1-D.  Shape of the input SparseTensor.
   * @param bIndices counterpart to {@code a_indices} for the other operand.
   * @param bValues counterpart to {@code a_values} for the other operand; must be of the same dtype.
   * @param bShape counterpart to {@code a_shape} for the other operand; the two shapes must be equal.
   * @param <T> data type for {@code SparseSparseMinimum} output and operands
   * @return a new instance of SparseSparseMinimum
   */
  public <T extends TType> SparseSparseMinimum<T> sparseSparseMinimum(Operand<TInt64> aIndices,
      Operand<T> aValues, Operand<TInt64> aShape, Operand<TInt64> bIndices, Operand<T> bValues,
      Operand<TInt64> bShape) {
    return SparseSparseMinimum.create(scope, aIndices, aValues, aShape, bIndices, bValues, bShape);
  }

  /**
   * Split a {@code SparseTensor} into {@code num_split} tensors along one dimension.
   *  If the {@code shape[split_dim]} is not an integer multiple of {@code num_split}. Slices
   *  {@code [0 : shape[split_dim] % num_split]} gets one extra dimension.
   *  For example, if {@code split_dim = 1} and {@code num_split = 2} and the input is
   *  <pre>
   *  input_tensor = shape = [2, 7]
   *  [    a   d e  ]
   *  [b c          ]
   *  </pre>
   *  <p>Graphically the output tensors are:
   *  <pre>
   *  output_tensor[0] = shape = [2, 4]
   *  [    a  ]
   *  [b c    ]
   *
   *  output_tensor[1] = shape = [2, 3]
   *  [ d e  ]
   *  [      ]
   *  </pre>
   *
   * @param <T> data type for {@code output_values} output
   * @param splitDim 0-D.  The dimension along which to split.  Must be in the range
   *  {@code [0, rank(shape))}.
   * @param indices 2-D tensor represents the indices of the sparse tensor.
   * @param values 1-D tensor represents the values of the sparse tensor.
   * @param shape 1-D. tensor represents the shape of the sparse tensor.
   *  output indices: A list of 1-D tensors represents the indices of the output
   *  sparse tensors.
   * @param numSplit The number of ways to split.
   * @param <T> data type for {@code SparseSplit} output and operands
   * @return a new instance of SparseSplit
   */
  public <T extends TType> SparseSplit<T> sparseSplit(Operand<TInt64> splitDim,
      Operand<TInt64> indices, Operand<T> values, Operand<TInt64> shape, Long numSplit) {
    return SparseSplit.create(scope, splitDim, indices, values, shape, numSplit);
  }

  /**
   * Adds up a {@code SparseTensor} and a dense {@code Tensor}, producing a dense {@code Tensor}.
   *  This Op does not require {@code a_indices} be sorted in standard lexicographic order.
   *
   * @param <U> data type for {@code output} output
   * @param aIndices 2-D.  The {@code indices} of the {@code SparseTensor}, with shape {@code [nnz, ndims]}.
   * @param aValues 1-D.  The {@code values} of the {@code SparseTensor}, with shape {@code [nnz]}.
   * @param aShape 1-D.  The {@code shape} of the {@code SparseTensor}, with shape {@code [ndims]}.
   * @param b {@code ndims}-D Tensor.  With shape {@code a_shape}.
   * @param <U> data type for {@code SparseTensorDenseAdd} output and operands
   * @param <T> data type for {@code SparseTensorDenseAdd} output and operands
   * @return a new instance of SparseTensorDenseAdd
   */
  public <U extends TType, T extends TNumber> SparseTensorDenseAdd<U> sparseTensorDenseAdd(
      Operand<T> aIndices, Operand<U> aValues, Operand<T> aShape, Operand<U> b) {
    return SparseTensorDenseAdd.create(scope, aIndices, aValues, aShape, b);
  }

  /**
   * Multiply SparseTensor (of rank 2) &quot;A&quot; by dense matrix &quot;B&quot;.
   *  No validity checking is performed on the indices of A.  However, the following
   *  input format is recommended for optimal behavior:
   *  <p>if adjoint_a == false:
   *  A should be sorted in lexicographically increasing order.  Use SparseReorder
   *  if you're not sure.
   *  if adjoint_a == true:
   *  A should be sorted in order of increasing dimension 1 (i.e., &quot;column major&quot;
   *  order instead of &quot;row major&quot; order).
   *
   * @param <U> data type for {@code product} output
   * @param aIndices 2-D.  The {@code indices} of the {@code SparseTensor}, size {@code [nnz, 2]} Matrix.
   * @param aValues 1-D.  The {@code values} of the {@code SparseTensor}, size {@code [nnz]} Vector.
   * @param aShape 1-D.  The {@code shape} of the {@code SparseTensor}, size {@code [2]} Vector.
   * @param b 2-D.  A dense Matrix.
   * @param options carries optional attribute values
   * @param <U> data type for {@code SparseTensorDenseMatMul} output and operands
   * @return a new instance of SparseTensorDenseMatMul
   */
  public <U extends TType> SparseTensorDenseMatMul<U> sparseTensorDenseMatMul(
      Operand<? extends TNumber> aIndices, Operand<U> aValues, Operand<TInt64> aShape, Operand<U> b,
      SparseTensorDenseMatMul.Options... options) {
    return SparseTensorDenseMatMul.create(scope, aIndices, aValues, aShape, b, options);
  }

  /**
   * Converts a sparse representation into a dense tensor.
   *  Builds an array {@code dense} with shape {@code output_shape} such that
   *  <pre>
   *  # If sparse_indices is scalar
   *  dense[i] = (i == sparse_indices ? sparse_values : default_value)
   *
   *  # If sparse_indices is a vector, then for each i
   *  dense[sparse_indices[i]] = sparse_values[i]
   *
   *  # If sparse_indices is an n by d matrix, then for each i in [0, n)
   *  dense[sparse_indices[i][0], ..., sparse_indices[i][d-1]] = sparse_values[i]
   *  </pre>
   *  <p>All other values in {@code dense} are set to {@code default_value}.  If {@code sparse_values} is a
   *  scalar, all sparse indices are set to this single value.
   *  <p>Indices should be sorted in lexicographic order, and indices must not
   *  contain any repeats. If {@code validate_indices} is true, these properties
   *  are checked during execution.
   *
   * @param <U> data type for {@code dense} output
   * @param sparseIndices 0-D, 1-D, or 2-D.  {@code sparse_indices[i]} contains the complete
   *  index where {@code sparse_values[i]} will be placed.
   * @param outputShape 1-D.  Shape of the dense output tensor.
   * @param sparseValues 1-D.  Values corresponding to each row of {@code sparse_indices},
   *  or a scalar value to be used for all sparse indices.
   * @param defaultValue Scalar value to set for indices not specified in
   *  {@code sparse_indices}.
   * @param options carries optional attribute values
   * @param <U> data type for {@code SparseToDense} output and operands
   * @param <T> data type for {@code SparseToDense} output and operands
   * @return a new instance of SparseToDense
   */
  public <U extends TType, T extends TNumber> SparseToDense<U> sparseToDense(
      Operand<T> sparseIndices, Operand<T> outputShape, Operand<U> sparseValues,
      Operand<U> defaultValue, SparseToDense.Options... options) {
    return SparseToDense.create(scope, sparseIndices, outputShape, sparseValues, defaultValue, options);
  }

  /**
   * Applies set operation along last dimension of 2 {@code SparseTensor} inputs.
   *  See SetOperationOp::SetOperationFromContext for values of {@code set_operation}.
   *  <p>If {@code validate_indices} is {@code True}, {@code sparse.SparseToSparseSetOperation} validates the
   *  order and range of {@code set1} and {@code set2} indices.
   *  <p>Input {@code set1} is a {@code SparseTensor} represented by {@code set1_indices}, {@code set1_values},
   *  and {@code set1_shape}. For {@code set1} ranked {@code n}, 1st {@code n-1} dimensions must be the same
   *  as {@code set2}. Dimension {@code n} contains values in a set, duplicates are allowed but
   *  ignored.
   *  <p>Input {@code set2} is a {@code SparseTensor} represented by {@code set2_indices}, {@code set2_values},
   *  and {@code set2_shape}. For {@code set2} ranked {@code n}, 1st {@code n-1} dimensions must be the same
   *  as {@code set1}. Dimension {@code n} contains values in a set, duplicates are allowed but
   *  ignored.
   *  <p>If {@code validate_indices} is {@code True}, this op validates the order and range of {@code set1}
   *  and {@code set2} indices.
   *  <p>Output {@code result} is a {@code SparseTensor} represented by {@code result_indices},
   *  {@code result_values}, and {@code result_shape}. For {@code set1} and {@code set2} ranked {@code n}, this
   *  has rank {@code n} and the same 1st {@code n-1} dimensions as {@code set1} and {@code set2}. The {@code nth}
   *  dimension contains the result of {@code set_operation} applied to the corresponding
   *  {@code [0...n-1]} dimension of {@code set}.
   *
   * @param <T> data type for {@code result_values} output
   * @param set1Indices 2D {@code Tensor}, indices of a {@code SparseTensor}. Must be in row-major
   *  order.
   * @param set1Values 1D {@code Tensor}, values of a {@code SparseTensor}. Must be in row-major
   *  order.
   * @param set1Shape 1D {@code Tensor}, shape of a {@code SparseTensor}. {@code set1_shape[0...n-1]} must
   *  be the same as {@code set2_shape[0...n-1]}, {@code set1_shape[n]} is the
   *  max set size across {@code 0...n-1} dimensions.
   * @param set2Indices 2D {@code Tensor}, indices of a {@code SparseTensor}. Must be in row-major
   *  order.
   * @param set2Values 1D {@code Tensor}, values of a {@code SparseTensor}. Must be in row-major
   *  order.
   * @param set2Shape 1D {@code Tensor}, shape of a {@code SparseTensor}. {@code set2_shape[0...n-1]} must
   *  be the same as {@code set1_shape[0...n-1]}, {@code set2_shape[n]} is the
   *  max set size across {@code 0...n-1} dimensions.
   * @param setOperation The value of the setOperation attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseToSparseSetOperation} output and operands
   * @return a new instance of SparseToSparseSetOperation
   */
  public <T extends TType> SparseToSparseSetOperation<T> sparseToSparseSetOperation(
      Operand<TInt64> set1Indices, Operand<T> set1Values, Operand<TInt64> set1Shape,
      Operand<TInt64> set2Indices, Operand<T> set2Values, Operand<TInt64> set2Shape,
      String setOperation, SparseToSparseSetOperation.Options... options) {
    return SparseToSparseSetOperation.create(scope, set1Indices, set1Values, set1Shape, set2Indices, set2Values, set2Shape, setOperation, options);
  }

  /**
   * Read {@code SparseTensors} from a {@code SparseTensorsMap} and concatenate them.
   *  The input {@code sparse_handles} must be an {@code int64} matrix of shape {@code [N, 1]} where
   *  {@code N} is the minibatch size and the rows correspond to the output handles of
   *  {@code AddSparseToTensorsMap} or {@code AddManySparseToTensorsMap}.  The ranks of the
   *  original {@code SparseTensor} objects that went into the given input ops must all
   *  match.  When the final {@code SparseTensor} is created, it has rank one
   *  higher than the ranks of the incoming {@code SparseTensor} objects
   *  (they have been concatenated along a new row dimension on the left).
   *  <p>The output {@code SparseTensor} object's shape values for all dimensions but the
   *  first are the max across the input {@code SparseTensor} objects' shape values
   *  for the corresponding dimensions.  Its first shape value is {@code N}, the minibatch
   *  size.
   *  <p>The input {@code SparseTensor} objects' indices are assumed ordered in
   *  standard lexicographic order.  If this is not the case, after this
   *  step run {@code SparseReorder} to restore index ordering.
   *  <p>For example, if the handles represent an input, which is a {@code [2, 3]} matrix
   *  representing two original {@code SparseTensor} objects:
   *  <pre>
   *      index = [ 0]
   *              [10]
   *              [20]
   *      values = [1, 2, 3]
   *      shape = [50]
   *  </pre>
   *  <p>and
   *  <pre>
   *      index = [ 2]
   *              [10]
   *      values = [4, 5]
   *      shape = [30]
   *  </pre>
   *  <p>then the final {@code SparseTensor} will be:
   *  <pre>
   *      index = [0  0]
   *              [0 10]
   *              [0 20]
   *              [1  2]
   *              [1 10]
   *      values = [1, 2, 3, 4, 5]
   *      shape = [2 50]
   *  </pre>
   *
   * @param <T> data type for {@code sparse_values} output
   * @param sparseHandles 1-D, The {@code N} serialized {@code SparseTensor} objects.
   *  Shape: {@code [N]}.
   * @param dtype The {@code dtype} of the {@code SparseTensor} objects stored in the
   *  {@code SparseTensorsMap}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TakeManySparseFromTensorsMap} output and operands
   * @return a new instance of TakeManySparseFromTensorsMap
   */
  public <T extends TType> TakeManySparseFromTensorsMap<T> takeManySparseFromTensorsMap(
      Operand<TInt64> sparseHandles, Class<T> dtype,
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
