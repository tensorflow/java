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

import kotlin.jvm.JvmName
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
 * An API for building `sparse` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class SparseOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.SparseOps = ops.java.sparse

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Add an `N`-minibatch `SparseTensor` to a `SparseTensorsMap`, return `N` handles.
     *  
     *  A `SparseTensor` of rank `R` is represented by three tensors: `sparse_indices`,
     *  `sparse_values`, and `sparse_shape`, where
     *  ```
     *  sparse_indices.shape[1] == sparse_shape.shape[0] == R```
     * 
     *  An `N`-minibatch of `SparseTensor` objects is represented as a `SparseTensor`
     *  having a first `sparse_indices` column taking values between `&#91;0, N)`, where
     *  the minibatch size `N == sparse_shape&#91;0]`.
     *  
     *  The input `SparseTensor` must have rank `R` greater than 1, and the first
     *  dimension is treated as the minibatch dimension.  Elements of the `SparseTensor`
     *  must be sorted in increasing order of this first dimension.  The stored
     *  `SparseTensor` objects pointed to by each row of the output `sparse_handles`
     *  will have rank `R-1`.
     *  
     *  The `SparseTensor` values can then be read out as part of a minibatch by passing
     *  the given keys as vector elements to `TakeManySparseFromTensorsMap`.  To ensure
     *  the correct `SparseTensorsMap` is accessed, ensure that the same
     *  `container` and `shared_name` are passed to that Op.  If no `shared_name`
     *  is provided here, instead use the <i>name</i> of the Operation created by calling
     *  `sparse.AddManySparseToTensorsMap` as the `shared_name` passed to
     *  `TakeManySparseFromTensorsMap`.  Ensure the Operations are colocated.
     * 
     * @param sparseIndices 2-D.  The `indices` of the minibatch `SparseTensor`.
     *  `sparse_indices&#91;:, 0]` must be ordered values in `&#91;0, N)`.
     * @param sparseValues 1-D.  The `values` of the minibatch `SparseTensor`.
     * @param sparseShape 1-D.  The `shape` of the minibatch `SparseTensor`.
     *  The minibatch size `N == sparse_shape&#91;0]`.
     * @param options carries optional attributes values
     * @return a new instance of AddManySparseToTensorsMap
     * @see org.tensorflow.op.SparseOps.addManySparseToTensorsMap
     * @param container The container name for the `SparseTensorsMap` created by this op.
     * @param sharedName The shared name for the `SparseTensorsMap` created by this op.
     *  If blank, the new Operation's unique name is used.
     */
    public fun <T : TType> addManySparseToTensorsMap(
        sparseIndices: Operand<TInt64>,
        sparseValues: Operand<T>,
        sparseShape: Operand<TInt64>,
        container: String? = null,
        sharedName: String? = null
    ): AddManySparseToTensorsMap = java.addManySparseToTensorsMap<T>(    
        sparseIndices,
        sparseValues,
        sparseShape,
        *listOfNotNull(
            container?.let{ org.tensorflow.op.sparse.AddManySparseToTensorsMap.container(it) },
            sharedName?.let{ org.tensorflow.op.sparse.AddManySparseToTensorsMap.sharedName(it) }
        ).toTypedArray()
        )

    /**
     * Add a `SparseTensor` to a `SparseTensorsMap` return its handle.
     *  
     *  A `SparseTensor` is represented by three tensors: `sparse_indices`,
     *  `sparse_values`, and `sparse_shape`.
     *  
     *  This operator takes the given `SparseTensor` and adds it to a container
     *  object (a `SparseTensorsMap`).  A unique key within this container is generated
     *  in the form of an `int64`, and this is the value that is returned.
     *  
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
     * @see org.tensorflow.op.SparseOps.addSparseToTensorsMap
     * @param container The container name for the `SparseTensorsMap` created by this op.
     * @param sharedName The shared name for the `SparseTensorsMap` created by this op.
     *  If blank, the new Operation's unique name is used.
     */
    public fun <T : TType> addSparseToTensorsMap(
        sparseIndices: Operand<TInt64>,
        sparseValues: Operand<T>,
        sparseShape: Operand<TInt64>,
        container: String? = null,
        sharedName: String? = null
    ): AddSparseToTensorsMap = java.addSparseToTensorsMap<T>(    
        sparseIndices,
        sparseValues,
        sparseShape,
        *listOfNotNull(
            container?.let{ org.tensorflow.op.sparse.AddSparseToTensorsMap.container(it) },
            sharedName?.let{ org.tensorflow.op.sparse.AddSparseToTensorsMap.sharedName(it) }
        ).toTypedArray()
        )

    /**
     * Applies set operation along last dimension of 2 `Tensor` inputs.
     *  
     *  See SetOperationOp::SetOperationFromContext for values of `set_operation`.
     *  
     *  Output `result` is a `SparseTensor` represented by `result_indices`,
     *  `result_values`, and `result_shape`. For `set1` and `set2` ranked `n`, this
     *  has rank `n` and the same 1st `n-1` dimensions as `set1` and `set2`. The `nth`
     *  dimension contains the result of `set_operation` applied to the corresponding
     *  `&#91;0...n-1]` dimension of `set`.
     * 
     * @param T data type for ` resultValues()` output
     * @param set1 `Tensor` with rank `n`. 1st `n-1` dimensions must be the same as `set2`.
     *  Dimension `n` contains values in a set, duplicates are allowed but ignored.
     * @param set2 `Tensor` with rank `n`. 1st `n-1` dimensions must be the same as `set1`.
     *  Dimension `n` contains values in a set, duplicates are allowed but ignored.
     * @param setOperation
     * @param options carries optional attributes values
     * @return a new instance of DenseToDenseSetOperation
     * @see org.tensorflow.op.SparseOps.denseToDenseSetOperation
     * @param validateIndices @param validateIndices
     */
    public fun <T : TType> denseToDenseSetOperation(
        set1: Operand<T>,
        set2: Operand<T>,
        setOperation: String,
        validateIndices: Boolean? = null
    ): DenseToDenseSetOperation<T> = java.denseToDenseSetOperation<T>(    
        set1,
        set2,
        setOperation,
        *listOfNotNull(
            validateIndices?.let{ org.tensorflow.op.sparse.DenseToDenseSetOperation.validateIndices(it) }
        ).toTypedArray()
        )

    /**
     * Applies set operation along last dimension of `Tensor` and `SparseTensor`.
     *  
     *  See SetOperationOp::SetOperationFromContext for values of `set_operation`.
     *  
     *  Input `set2` is a `SparseTensor` represented by `set2_indices`, `set2_values`,
     *  and `set2_shape`. For `set2` ranked `n`, 1st `n-1` dimensions must be the same
     *  as `set1`. Dimension `n` contains values in a set, duplicates are allowed but
     *  ignored.
     *  
     *  If `validate_indices` is `True`, this op validates the order and range of `set2`
     *  indices.
     *  
     *  Output `result` is a `SparseTensor` represented by `result_indices`,
     *  `result_values`, and `result_shape`. For `set1` and `set2` ranked `n`, this
     *  has rank `n` and the same 1st `n-1` dimensions as `set1` and `set2`. The `nth`
     *  dimension contains the result of `set_operation` applied to the corresponding
     *  `&#91;0...n-1]` dimension of `set`.
     * 
     * @param T data type for ` resultValues()` output
     * @param set1 `Tensor` with rank `n`. 1st `n-1` dimensions must be the same as `set2`.
     *  Dimension `n` contains values in a set, duplicates are allowed but ignored.
     * @param set2Indices 2D `Tensor`, indices of a `SparseTensor`. Must be in row-major
     *  order.
     * @param set2Values 1D `Tensor`, values of a `SparseTensor`. Must be in row-major
     *  order.
     * @param set2Shape 1D `Tensor`, shape of a `SparseTensor`. `set2_shape&#91;0...n-1]` must
     *  be the same as the 1st `n-1` dimensions of `set1`, `result_shape&#91;n]` is the
     *  max set size across `n-1` dimensions.
     * @param setOperation
     * @param options carries optional attributes values
     * @return a new instance of DenseToSparseSetOperation
     * @see org.tensorflow.op.SparseOps.denseToSparseSetOperation
     * @param validateIndices @param validateIndices
     */
    public fun <T : TType> denseToSparseSetOperation(
        set1: Operand<T>,
        set2Indices: Operand<TInt64>,
        set2Values: Operand<T>,
        set2Shape: Operand<TInt64>,
        setOperation: String,
        validateIndices: Boolean? = null
    ): DenseToSparseSetOperation<T> = java.denseToSparseSetOperation<T>(    
        set1,
        set2Indices,
        set2Values,
        set2Shape,
        setOperation,
        *listOfNotNull(
            validateIndices?.let{ org.tensorflow.op.sparse.DenseToSparseSetOperation.validateIndices(it) }
        ).toTypedArray()
        )

    /**
     * Deserialize `SparseTensor` objects.
     *  
     *  The input `serialized_sparse` must have the shape `&#91;?, ?, ..., ?, 3]` where
     *  the last dimension stores serialized `SparseTensor` objects and the other N
     *  dimensions (N >= 0) correspond to a batch. The ranks of the original
     *  `SparseTensor` objects must all match. When the final `SparseTensor` is
     *  created, its rank is the rank of the incoming `SparseTensor` objects plus N;
     *  the sparse tensors have been concatenated along new dimensions, one for each
     *  batch.
     *  
     *  The output `SparseTensor` object's shape values for the original dimensions
     *  are the max across the input `SparseTensor` objects' shape values for the
     *  corresponding dimensions. The new dimensions match the size of the batch.
     *  
     *  The input `SparseTensor` objects' indices are assumed ordered in
     *  standard lexicographic order.  If this is not the case, after this
     *  step run `SparseReorder` to restore index ordering.
     *  
     *  For example, if the serialized input is a `&#91;2 x 3]` matrix representing two
     *  original `SparseTensor` objects:
     *  
     *      index = &#91; 0]
     *              &#91;10]
     *              &#91;20]
     *      values = &#91;1, 2, 3]
     *      shape = &#91;50]
     *  
     *  and
     *  
     *      index = &#91; 2]
     *              &#91;10]
     *      values = &#91;4, 5]
     *      shape = &#91;30]
     *  
     *  then the final deserialized `SparseTensor` will be:
     *  
     *      index = &#91;0  0]
     *              &#91;0 10]
     *              &#91;0 20]
     *              &#91;1  2]
     *              &#91;1 10]
     *      values = &#91;1, 2, 3, 4, 5]
     *      shape = &#91;2 50]
     * 
     * @param U data type for ` sparseValues()` output
     * @param serializedSparse The serialized `SparseTensor` objects. The last dimension
     *  must have 3 columns.
     * @param dtype The `dtype` of the serialized `SparseTensor` objects.
     * @return a new instance of DeserializeSparse
     * @see org.tensorflow.op.SparseOps.deserializeSparse
     */
    public fun <U : TType, T : TType> deserializeSparse(serializedSparse: Operand<T>,
            dtype: Class<U>): DeserializeSparse<U> = java.deserializeSparse<U, T>(    
        serializedSparse,
        dtype
        )

    /**
     * Applies a sparse gradient to a given accumulator.
     *  
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
     * @see org.tensorflow.op.SparseOps.sparseAccumulatorApplyGradient
     */
    public fun <T : TType> sparseAccumulatorApplyGradient(
        handle: Operand<TString>,
        localStep: Operand<TInt64>,
        gradientIndices: Operand<TInt64>,
        gradientValues: Operand<T>,
        gradientShape: Operand<TInt64>,
        hasKnownShape: Boolean
    ): SparseAccumulatorApplyGradient = java.sparseAccumulatorApplyGradient<T>(    
        handle,
        localStep,
        gradientIndices,
        gradientValues,
        gradientShape,
        hasKnownShape
        )

    /**
     * Extracts the average sparse gradient in a SparseConditionalAccumulator.
     *  
     *  The op will blocks until sufficient (i.e., more than num_required)
     *  gradients have been accumulated. If the accumulator has already
     *  aggregated more than num_required gradients, it will return its
     *  average of the accumulated gradients.  Also automatically increments
     *  the recorded global_step in the accumulator by 1, and resets the
     *  aggregate to 0.
     * 
     * @param T data type for ` values()` output
     * @param handle The handle to a SparseConditionalAccumulator.
     * @param numRequired Number of gradients required before we return an aggregate.
     * @param dtype The data type of accumulated gradients. Needs to correspond to the type
     *  of the accumulator.
     * @return a new instance of SparseAccumulatorTakeGradient
     * @see org.tensorflow.op.SparseOps.sparseAccumulatorTakeGradient
     */
    public fun <T : TType> sparseAccumulatorTakeGradient(
        handle: Operand<TString>,
        numRequired: Operand<TInt32>,
        dtype: Class<T>
    ): SparseAccumulatorTakeGradient<T> = java.sparseAccumulatorTakeGradient<T>(    
        handle,
        numRequired,
        dtype
        )

    /**
     * Adds two `SparseTensor` objects to produce another `SparseTensor`.
     *  
     *  The input `SparseTensor` objects' indices are assumed ordered in standard
     *  lexicographic order.  If this is not the case, before this step run
     *  `SparseReorder` to restore index ordering.
     *  
     *  By default, if two values sum to zero at some index, the output `SparseTensor`
     *  would still include that particular location in its index, storing a zero in the
     *  corresponding value slot.  To override this, callers can specify `thresh`,
     *  indicating that if the sum has a magnitude strictly smaller than `thresh`, its
     *  corresponding value and index would then not be included.  In particular,
     *  `thresh == 0` (default) means everything is kept and actual thresholding happens
     *  only for a positive value.
     *  
     *  In the following shapes, `nnz` is the count after taking `thresh` into account.
     * 
     * @param T data type for ` sumValues()` output
     * @param aIndices 2-D.  The `indices` of the first `SparseTensor`, size `&#91;nnz, ndims]`
     * Matrix.
     * @param aValues 1-D.  The `values` of the first `SparseTensor`, size `&#91;nnz]` Vector.
     * @param aShape 1-D.  The `shape` of the first `SparseTensor`, size `&#91;ndims]` Vector.
     * @param bIndices 2-D.  The `indices` of the second `SparseTensor`, size `&#91;nnz, ndims]`
     * Matrix.
     * @param bValues 1-D.  The `values` of the second `SparseTensor`, size `&#91;nnz]` Vector.
     * @param bShape 1-D.  The `shape` of the second `SparseTensor`, size `&#91;ndims]` Vector.
     * @param thresh 0-D.  The magnitude threshold that determines if an output value/index
     *  pair takes space.
     * @return a new instance of SparseAdd
     * @see org.tensorflow.op.SparseOps.sparseAdd
     */
    public fun <T : TType, U : TNumber> sparseAdd(
        aIndices: Operand<TInt64>,
        aValues: Operand<T>,
        aShape: Operand<TInt64>,
        bIndices: Operand<TInt64>,
        bValues: Operand<T>,
        bShape: Operand<TInt64>,
        thresh: Operand<U>
    ): SparseAdd<T> = java.sparseAdd<T, U>(    
        aIndices,
        aValues,
        aShape,
        bIndices,
        bValues,
        bShape,
        thresh
        )

    /**
     * The gradient operator for the SparseAdd op.
     *  
     *  The SparseAdd op calculates A + B, where A, B, and the sum are all represented
     *  as `SparseTensor` objects.  This op takes in the upstream gradient w.r.t.
     *  non-empty values of the sum, and outputs the gradients w.r.t. the non-empty
     *  values of A and B.
     * 
     * @param T data type for ` aValGrad()` output
     * @param backpropValGrad 1-D with shape `&#91;nnz(sum)]`.  The gradient with respect to
     *  the non-empty values of the sum.
     * @param aIndices 2-D.  The `indices` of the `SparseTensor` A, size `&#91;nnz(A), ndims]`.
     * @param bIndices 2-D.  The `indices` of the `SparseTensor` B, size `&#91;nnz(B), ndims]`.
     * @param sumIndices 2-D.  The `indices` of the sum `SparseTensor`, size
     *  `&#91;nnz(sum), ndims]`.
     * @return a new instance of SparseAddGrad
     * @see org.tensorflow.op.SparseOps.sparseAddGrad
     */
    public fun <T : TType> sparseAddGrad(
        backpropValGrad: Operand<T>,
        aIndices: Operand<TInt64>,
        bIndices: Operand<TInt64>,
        sumIndices: Operand<TInt64>
    ): SparseAddGrad<T> = java.sparseAddGrad<T>(    
        backpropValGrad,
        aIndices,
        bIndices,
        sumIndices
        )

    /**
     * Counts the number of occurrences of each value in an integer array.
     *  
     *  Outputs a vector with length `size` and the same dtype as `weights`. If
     *  `weights` are empty, then index `i` stores the number of times the value `i` is
     *  counted in `arr`. If `weights` are non-empty, then index `i` stores the sum of
     *  the value in `weights` at each index where the corresponding value in `arr` is
     *  `i`.
     *  
     *  Values in `arr` outside of the range &#91;0, size) are ignored.
     * 
     * @param U data type for ` output()` output
     * @param indices 2D int64 `Tensor`.
     * @param values 1D int `Tensor`.
     * @param denseShape 1D int64 `Tensor`.
     * @param size non-negative int scalar `Tensor`.
     * @param weights is an int32, int64, float32, or float64 `Tensor` with the same
     *  shape as `input`, or a length-0 `Tensor`, in which case it acts as all weights
     *  equal to 1.
     * @param options carries optional attributes values
     * @return a new instance of SparseBincount
     * @see org.tensorflow.op.SparseOps.sparseBincount
     * @param binaryOutput bool; Whether the kernel should count the appearance or number of
     * occurrences.
     */
    public fun <U : TNumber, T : TNumber> sparseBincount(
        indices: Operand<TInt64>,
        values: Operand<T>,
        denseShape: Operand<TInt64>,
        size: Operand<T>,
        weights: Operand<U>,
        binaryOutput: Boolean? = null
    ): SparseBincount<U> = java.sparseBincount<U, T>(    
        indices,
        values,
        denseShape,
        size,
        weights,
        *listOfNotNull(
            binaryOutput?.let{ org.tensorflow.op.sparse.SparseBincount.binaryOutput(it) }
        ).toTypedArray()
        )

    /**
     * Concatenates a list of `SparseTensor` along the specified dimension.
     *  
     *  Concatenation is with respect to the dense versions of these sparse tensors.
     *  It is assumed that each input is a `SparseTensor` whose elements are ordered
     *  along increasing dimension number.
     *  
     *  All inputs' shapes must match, except for the concat dimension.  The
     *  `indices`, `values`, and `shapes` lists must have the same length.
     *  
     *  The output shape is identical to the inputs', except along the concat
     *  dimension, where it is the sum of the inputs' sizes along that dimension.
     *  
     *  The output elements will be resorted to preserve the sort order along
     *  increasing dimension number.
     *  
     *  This op runs in `O(M log M)` time, where `M` is the total number of non-empty
     *  values across all inputs. This is due to the need for an internal sort in
     *  order to concatenate efficiently across an arbitrary dimension.
     *  
     *  For example, if `concat_dim = 1` and the inputs are
     *  
     *      sp_inputs&#91;0]: shape = &#91;2, 3]
     *      &#91;0, 2]: "a"
     *      &#91;1, 0]: "b"
     *      &#91;1, 1]: "c"
     *  
     *      sp_inputs&#91;1]: shape = &#91;2, 4]
     *      &#91;0, 1]: "d"
     *      &#91;0, 2]: "e"
     *  
     *  then the output will be
     *  
     *      shape = &#91;2, 7]
     *      &#91;0, 2]: "a"
     *      &#91;0, 4]: "d"
     *      &#91;0, 5]: "e"
     *      &#91;1, 0]: "b"
     *      &#91;1, 1]: "c"
     *  
     *  Graphically this is equivalent to doing
     *  
     *      &#91;    a] concat &#91;  d e  ] = &#91;    a   d e  ]
     *      &#91;b c  ]        &#91;       ]   &#91;b c          ]
     * 
     * @param T data type for ` outputValues()` output
     * @param indices 2-D.  Indices of each input `SparseTensor`.
     * @param values 1-D.  Non-empty values of each `SparseTensor`.
     * @param shapes 1-D.  Shapes of each `SparseTensor`.
     * @param concatDim Dimension to concatenate along. Must be in range &#91;-rank, rank),
     *  where rank is the number of dimensions in each input `SparseTensor`.
     * @return a new instance of SparseConcat
     * @see org.tensorflow.op.SparseOps.sparseConcat
     */
    public fun <T : TType> sparseConcat(
        indices: Iterable<Operand<TInt64>>,
        values: Iterable<Operand<T>>,
        shapes: Iterable<Operand<TInt64>>,
        concatDim: Long
    ): SparseConcat<T> = java.sparseConcat<T>(    
        indices,
        values,
        shapes,
        concatDim
        )

    /**
     * A conditional accumulator for aggregating sparse gradients.
     *  
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
     * @see org.tensorflow.op.SparseOps.sparseConditionalAccumulator
     * @param container If non-empty, this accumulator is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this accumulator will be shared under the given name
     *  across multiple sessions.
     * @param reductionType @param reductionType
     */
    public fun <T : TType> sparseConditionalAccumulator(
        dtype: Class<T>,
        shape: Shape,
        container: String? = null,
        sharedName: String? = null,
        reductionType: String? = null
    ): SparseConditionalAccumulator = java.sparseConditionalAccumulator<T>(    
        dtype,
        shape,
        *listOfNotNull(
            container?.let{ org.tensorflow.op.sparse.SparseConditionalAccumulator.container(it) },
            sharedName?.let{ org.tensorflow.op.sparse.SparseConditionalAccumulator.sharedName(it) },
            reductionType?.let{ org.tensorflow.op.sparse.SparseConditionalAccumulator.reductionType(it) }
        ).toTypedArray()
        )

    /**
     * Generates sparse cross from a list of sparse and dense tensors.
     *  
     *  The op takes two lists, one of 2D `SparseTensor` and one of 2D `Tensor`, each
     *  representing features of one feature column. It outputs a 2D `SparseTensor` with
     *  the batchwise crosses of these features.
     *  
     *  For example, if the inputs are
     *  
     *      inputs&#91;0]: SparseTensor with shape = &#91;2, 2]
     *      &#91;0, 0]: "a"
     *      &#91;1, 0]: "b"
     *      &#91;1, 1]: "c"
     *  
     *      inputs&#91;1]: SparseTensor with shape = &#91;2, 1]
     *      &#91;0, 0]: "d"
     *      &#91;1, 0]: "e"
     *  
     *      inputs&#91;2]: Tensor &#91;&#91;"f"], &#91;"g"]]
     *  
     *  then the output will be
     *  
     *      shape = &#91;2, 2]
     *      &#91;0, 0]: "a_X_d_X_f"
     *      &#91;1, 0]: "b_X_e_X_g"
     *      &#91;1, 1]: "c_X_e_X_g"
     *  
     *  if hashed_output=true then the output will be
     *  
     *      shape = &#91;2, 2]
     *      &#91;0, 0]: FingerprintCat64(
     *                  Fingerprint64("f"), FingerprintCat64(
     *                      Fingerprint64("d"), Fingerprint64("a")))
     *      &#91;1, 0]: FingerprintCat64(
     *                  Fingerprint64("g"), FingerprintCat64(
     *                      Fingerprint64("e"), Fingerprint64("b")))
     *      &#91;1, 1]: FingerprintCat64(
     *                  Fingerprint64("g"), FingerprintCat64(
     *                      Fingerprint64("e"), Fingerprint64("c")))
     * 
     * @param indices 2-D.  Indices of each input `SparseTensor`.
     * @param values 1-D.   values of each `SparseTensor`.
     * @param shapes 1-D.   Shapes of each `SparseTensor`.
     * @param denseInputs 2-D.    Columns represented by dense `Tensor`.
     * @param sep string used when joining a list of string inputs, can be used as separator later.
     * @return a new instance of SparseCross
     * @see org.tensorflow.op.SparseOps.sparseCross
     */
    public fun sparseCross(
        indices: Iterable<Operand<TInt64>>,
        values: Iterable<Operand<*>>,
        shapes: Iterable<Operand<TInt64>>,
        denseInputs: Iterable<Operand<*>>,
        sep: Operand<TString>
    ): SparseCross = java.sparseCross(    
        indices,
        values,
        shapes,
        denseInputs,
        sep
        )

    /**
     * Generates sparse cross from a list of sparse and dense tensors.
     *  
     *  The op takes two lists, one of 2D `SparseTensor` and one of 2D `Tensor`, each
     *  representing features of one feature column. It outputs a 2D `SparseTensor` with
     *  the batchwise crosses of these features.
     *  
     *  For example, if the inputs are
     *  
     *      inputs&#91;0]: SparseTensor with shape = &#91;2, 2]
     *      &#91;0, 0]: "a"
     *      &#91;1, 0]: "b"
     *      &#91;1, 1]: "c"
     *  
     *      inputs&#91;1]: SparseTensor with shape = &#91;2, 1]
     *      &#91;0, 0]: "d"
     *      &#91;1, 0]: "e"
     *  
     *      inputs&#91;2]: Tensor &#91;&#91;"f"], &#91;"g"]]
     *  
     *  then the output will be
     *  
     *      shape = &#91;2, 2]
     *      &#91;0, 0]: "a_X_d_X_f"
     *      &#91;1, 0]: "b_X_e_X_g"
     *      &#91;1, 1]: "c_X_e_X_g"
     *  
     *  if hashed_output=true then the output will be
     *  
     *      shape = &#91;2, 2]
     *      &#91;0, 0]: FingerprintCat64(
     *                  Fingerprint64("f"), FingerprintCat64(
     *                      Fingerprint64("d"), Fingerprint64("a")))
     *      &#91;1, 0]: FingerprintCat64(
     *                  Fingerprint64("g"), FingerprintCat64(
     *                      Fingerprint64("e"), Fingerprint64("b")))
     *      &#91;1, 1]: FingerprintCat64(
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
     * @see org.tensorflow.op.SparseOps.sparseCrossHashed
     */
    public fun sparseCrossHashed(
        indices: Iterable<Operand<TInt64>>,
        values: Iterable<Operand<*>>,
        shapes: Iterable<Operand<TInt64>>,
        denseInputs: Iterable<Operand<*>>,
        numBuckets: Operand<TInt64>,
        strongHash: Operand<TBool>,
        salt: Operand<TInt64>
    ): SparseCrossHashed = java.sparseCrossHashed(    
        indices,
        values,
        shapes,
        denseInputs,
        numBuckets,
        strongHash,
        salt
        )

    /**
     * Adds up a SparseTensor and a dense Tensor, using these special rules:
     *  
     *  (1) Broadcasts the dense side to have the same shape as the sparse side, if
     *      eligible;
     *  (2) Then, only the dense values pointed to by the indices of the SparseTensor
     *      participate in the cwise addition.
     *  
     *  By these rules, the result is a logical SparseTensor with exactly the same
     *  indices and shape, but possibly with different non-zero values.  The output of
     *  this Op is the resultant non-zero values.
     * 
     * @param T data type for ` output()` output
     * @param spIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
     *  SparseTensor, possibly not in canonical ordering.
     * @param spValues 1-D.  `N` non-empty values corresponding to `sp_indices`.
     * @param spShape 1-D.  Shape of the input SparseTensor.
     * @param dense `R`-D.  The dense Tensor operand.
     * @return a new instance of SparseDenseCwiseAdd
     * @see org.tensorflow.op.SparseOps.sparseDenseCwiseAdd
     */
    public fun <T : TType> sparseDenseCwiseAdd(
        spIndices: Operand<TInt64>,
        spValues: Operand<T>,
        spShape: Operand<TInt64>,
        dense: Operand<T>
    ): SparseDenseCwiseAdd<T> = java.sparseDenseCwiseAdd<T>(    
        spIndices,
        spValues,
        spShape,
        dense
        )

    /**
     * Component-wise divides a SparseTensor by a dense Tensor.
     *  
     *  <i>Limitation</i>: this Op only broadcasts the dense side to the sparse side, but not
     *  the other direction.
     * 
     * @param T data type for ` output()` output
     * @param spIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
     *  SparseTensor, possibly not in canonical ordering.
     * @param spValues 1-D.  `N` non-empty values corresponding to `sp_indices`.
     * @param spShape 1-D.  Shape of the input SparseTensor.
     * @param dense `R`-D.  The dense Tensor operand.
     * @return a new instance of SparseDenseCwiseDiv
     * @see org.tensorflow.op.SparseOps.sparseDenseCwiseDiv
     */
    public fun <T : TType> sparseDenseCwiseDiv(
        spIndices: Operand<TInt64>,
        spValues: Operand<T>,
        spShape: Operand<TInt64>,
        dense: Operand<T>
    ): SparseDenseCwiseDiv<T> = java.sparseDenseCwiseDiv<T>(    
        spIndices,
        spValues,
        spShape,
        dense
        )

    /**
     * Component-wise multiplies a SparseTensor by a dense Tensor.
     *  
     *  The output locations corresponding to the implicitly zero elements in the sparse
     *  tensor will be zero (i.e., will not take up storage space), regardless of the
     *  contents of the dense tensor (even if it's +/-INF and that INF<i>0 == NaN).
     *  
     *  </i>Limitation*: this Op only broadcasts the dense side to the sparse side, but not
     *  the other direction.
     * 
     * @param T data type for ` output()` output
     * @param spIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
     *  SparseTensor, possibly not in canonical ordering.
     * @param spValues 1-D.  `N` non-empty values corresponding to `sp_indices`.
     * @param spShape 1-D.  Shape of the input SparseTensor.
     * @param dense `R`-D.  The dense Tensor operand.
     * @return a new instance of SparseDenseCwiseMul
     * @see org.tensorflow.op.SparseOps.sparseDenseCwiseMul
     */
    public fun <T : TType> sparseDenseCwiseMul(
        spIndices: Operand<TInt64>,
        spValues: Operand<T>,
        spShape: Operand<TInt64>,
        dense: Operand<T>
    ): SparseDenseCwiseMul<T> = java.sparseDenseCwiseMul<T>(    
        spIndices,
        spValues,
        spShape,
        dense
        )

    /**
     * Fills empty rows in the input 2-D `SparseTensor` with a default value.
     *  
     *  The input `SparseTensor` is represented via the tuple of inputs
     *  (`indices`, `values`, `dense_shape`).  The output `SparseTensor` has the
     *  same `dense_shape` but with indices `output_indices` and values
     *  `output_values`.
     *  
     *  This op inserts a single entry for every row that doesn't have any values.
     *  The index is created as `&#91;row, 0, ..., 0]` and the inserted value
     *  is `default_value`.
     *  
     *  For example, suppose `sp_input` has shape `&#91;5, 6]` and non-empty values:
     *  
     *      &#91;0, 1]: a
     *      &#91;0, 3]: b
     *      &#91;2, 0]: c
     *      &#91;3, 1]: d
     *  
     *  Rows 1 and 4 are empty, so the output will be of shape `&#91;5, 6]` with values:
     *  
     *      &#91;0, 1]: a
     *      &#91;0, 3]: b
     *      &#91;1, 0]: default_value
     *      &#91;2, 0]: c
     *      &#91;3, 1]: d
     *      &#91;4, 0]: default_value
     *  
     *  The output `SparseTensor` will be in row-major order and will have the
     *  same shape as the input.
     *  
     *  This op also returns an indicator vector shaped `&#91;dense_shape&#91;0]]` such that
     *  
     *      empty_row_indicator&#91;i] = True iff row i was an empty row.
     *  
     *  And a reverse index map vector shaped `&#91;indices.shape&#91;0]]` that is used during
     *  backpropagation,
     *  
     *      reverse_index_map&#91;j] = out_j s.t. indices&#91;j, :] == output_indices&#91;out_j, :]
     * 
     * @param T data type for ` outputValues()` output
     * @param indices 2-D. the indices of the sparse tensor.
     * @param values 1-D. the values of the sparse tensor.
     * @param denseShape 1-D. the shape of the sparse tensor.
     * @param defaultValue 0-D. default value to insert into location `&#91;row, 0, ..., 0]`
     *    for rows missing from the input sparse tensor.
     *  output indices: 2-D. the indices of the filled sparse tensor.
     * @return a new instance of SparseFillEmptyRows
     * @see org.tensorflow.op.SparseOps.sparseFillEmptyRows
     */
    public fun <T : TType> sparseFillEmptyRows(
        indices: Operand<TInt64>,
        values: Operand<T>,
        denseShape: Operand<TInt64>,
        defaultValue: Operand<T>
    ): SparseFillEmptyRows<T> = java.sparseFillEmptyRows<T>(    
        indices,
        values,
        denseShape,
        defaultValue
        )

    /**
     * The gradient of SparseFillEmptyRows.
     *  
     *  Takes vectors reverse_index_map, shaped `&#91;N]`, and grad_values,
     *  shaped `&#91;N_full]`, where `N_full >= N` and copies data into either
     *  `d_values` or `d_default_value`.  Here `d_values` is shaped `&#91;N]` and
     *  `d_default_value` is a scalar.
     *  
     *    d_values&#91;j] = grad_values&#91;reverse_index_map&#91;j]]
     *    d_default_value = sum_{k : 0 .. N_full - 1} (
     *       grad_values&#91;k] * 1{k not in reverse_index_map})
     * 
     * @param T data type for ` dValues()` output
     * @param reverseIndexMap 1-D.  The reverse index map from SparseFillEmptyRows.
     * @param gradValues 1-D.  The gradients from backprop.
     * @return a new instance of SparseFillEmptyRowsGrad
     * @see org.tensorflow.op.SparseOps.sparseFillEmptyRowsGrad
     */
    public fun <T : TType> sparseFillEmptyRowsGrad(reverseIndexMap: Operand<TInt64>,
            gradValues: Operand<T>): SparseFillEmptyRowsGrad<T> = java.sparseFillEmptyRowsGrad<T>(    
        reverseIndexMap,
        gradValues
        )

    /**
     * Multiply matrix "a" by matrix "b".
     *  
     *  The inputs must be two-dimensional matrices and the inner dimension of "a" must
     *  match the outer dimension of "b". Both "a" and "b" must be `Tensor`s not
     *  `SparseTensor`s.  This op is optimized for the case where at least one of "a" or
     *  "b" is sparse, in the sense that they have a large proportion of zero values.
     *  The breakeven for using this versus a dense matrix multiply on one platform was
     *  30% zero values in the sparse matrix.
     *  
     *  The gradient computation of this operation will only take advantage of sparsity
     *  in the input gradient when that gradient comes from a Relu.
     * 
     * @param a
     * @param b
     * @param options carries optional attributes values
     * @return a new instance of SparseMatMul
     * @see org.tensorflow.op.SparseOps.sparseMatMul
     * @param transposeA @param transposeA
     * @param transposeB @param transposeB
     * @param aIsSparse @param aIsSparse
     * @param bIsSparse @param bIsSparse
     */
    public fun <T : TNumber, U : TNumber> sparseMatMul(
        a: Operand<T>,
        b: Operand<U>,
        transposeA: Boolean? = null,
        transposeB: Boolean? = null,
        aIsSparse: Boolean? = null,
        bIsSparse: Boolean? = null
    ): SparseMatMul = java.sparseMatMul<T, U>(    
        a,
        b,
        *listOfNotNull(
            transposeA?.let{ org.tensorflow.op.sparse.SparseMatMul.transposeA(it) },
            transposeB?.let{ org.tensorflow.op.sparse.SparseMatMul.transposeB(it) },
            aIsSparse?.let{ org.tensorflow.op.sparse.SparseMatMul.aIsSparse(it) },
            bIsSparse?.let{ org.tensorflow.op.sparse.SparseMatMul.bIsSparse(it) }
        ).toTypedArray()
        )

    /**
     * Computes the max of elements across dimensions of a SparseTensor.
     *  
     *  This Op takes a SparseTensor and is the sparse counterpart to
     *  `tf.reduce_max()`.  In particular, this Op also returns a dense `Tensor`
     *  instead of a sparse one.
     *  
     *  Reduces `sp_input` along the dimensions given in `reduction_axes`.  Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `reduction_axes`. If `keep_dims` is true, the reduced dimensions are retained
     *  with length 1.
     *  
     *  If `reduction_axes` has no entries, all dimensions are reduced, and a tensor
     *  with a single element is returned.  Additionally, the axes can be negative,
     *  which are interpreted according to the indexing rules in Python.
     * 
     * @param T data type for ` output()` output
     * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
     *  SparseTensor, possibly not in canonical ordering.
     * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
     * @param inputShape 1-D.  Shape of the input SparseTensor.
     * @param reductionAxes 1-D.  Length-`K` vector containing the reduction axes.
     * @param options carries optional attributes values
     * @return a new instance of SparseReduceMax
     * @see org.tensorflow.op.SparseOps.sparseReduceMax
     * @param keepDims If true, retain reduced dimensions with length 1.
     */
    public fun <T : TNumber> sparseReduceMax(
        inputIndices: Operand<TInt64>,
        inputValues: Operand<T>,
        inputShape: Operand<TInt64>,
        reductionAxes: Operand<TInt32>,
        keepDims: Boolean? = null
    ): SparseReduceMax<T> = java.sparseReduceMax<T>(    
        inputIndices,
        inputValues,
        inputShape,
        reductionAxes,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.sparse.SparseReduceMax.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Computes the max of elements across dimensions of a SparseTensor.
     *  
     *  This Op takes a SparseTensor and is the sparse counterpart to
     *  `tf.reduce_max()`.  In contrast to SparseReduceMax, this Op returns a
     *  SparseTensor.
     *  
     *  Reduces `sp_input` along the dimensions given in `reduction_axes`.  Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `reduction_axes`. If `keep_dims` is true, the reduced dimensions are retained
     *  with length 1.
     *  
     *  If `reduction_axes` has no entries, all dimensions are reduced, and a tensor
     *  with a single element is returned.  Additionally, the axes can be negative,
     *  which are interpreted according to the indexing rules in Python.
     * 
     * @param T data type for ` outputValues()` output
     * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
     *  SparseTensor, possibly not in canonical ordering.
     * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
     * @param inputShape 1-D.  Shape of the input SparseTensor.
     * @param reductionAxes 1-D.  Length-`K` vector containing the reduction axes.
     * @param options carries optional attributes values
     * @return a new instance of SparseReduceMaxSparse
     * @see org.tensorflow.op.SparseOps.sparseReduceMaxSparse
     * @param keepDims If true, retain reduced dimensions with length 1.
     */
    public fun <T : TNumber> sparseReduceMaxSparse(
        inputIndices: Operand<TInt64>,
        inputValues: Operand<T>,
        inputShape: Operand<TInt64>,
        reductionAxes: Operand<TInt32>,
        keepDims: Boolean? = null
    ): SparseReduceMaxSparse<T> = java.sparseReduceMaxSparse<T>(    
        inputIndices,
        inputValues,
        inputShape,
        reductionAxes,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.sparse.SparseReduceMaxSparse.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Computes the sum of elements across dimensions of a SparseTensor.
     *  
     *  This Op takes a SparseTensor and is the sparse counterpart to
     *  `tf.reduce_sum()`.  In particular, this Op also returns a dense `Tensor`
     *  instead of a sparse one.
     *  
     *  Reduces `sp_input` along the dimensions given in `reduction_axes`.  Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `reduction_axes`. If `keep_dims` is true, the reduced dimensions are retained
     *  with length 1.
     *  
     *  If `reduction_axes` has no entries, all dimensions are reduced, and a tensor
     *  with a single element is returned.  Additionally, the axes can be negative,
     *  which are interpreted according to the indexing rules in Python.
     * 
     * @param T data type for ` output()` output
     * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
     *  SparseTensor, possibly not in canonical ordering.
     * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
     * @param inputShape 1-D.  Shape of the input SparseTensor.
     * @param reductionAxes 1-D.  Length-`K` vector containing the reduction axes.
     * @param options carries optional attributes values
     * @return a new instance of SparseReduceSum
     * @see org.tensorflow.op.SparseOps.sparseReduceSum
     * @param keepDims If true, retain reduced dimensions with length 1.
     */
    public fun <T : TType> sparseReduceSum(
        inputIndices: Operand<TInt64>,
        inputValues: Operand<T>,
        inputShape: Operand<TInt64>,
        reductionAxes: Operand<TInt32>,
        keepDims: Boolean? = null
    ): SparseReduceSum<T> = java.sparseReduceSum<T>(    
        inputIndices,
        inputValues,
        inputShape,
        reductionAxes,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.sparse.SparseReduceSum.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Computes the sum of elements across dimensions of a SparseTensor.
     *  
     *  This Op takes a SparseTensor and is the sparse counterpart to
     *  `tf.reduce_sum()`.  In contrast to SparseReduceSum, this Op returns a
     *  SparseTensor.
     *  
     *  Reduces `sp_input` along the dimensions given in `reduction_axes`.  Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `reduction_axes`. If `keep_dims` is true, the reduced dimensions are retained
     *  with length 1.
     *  
     *  If `reduction_axes` has no entries, all dimensions are reduced, and a tensor
     *  with a single element is returned.  Additionally, the axes can be negative,
     *  which are interpreted according to the indexing rules in Python.
     * 
     * @param T data type for ` outputValues()` output
     * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
     *  SparseTensor, possibly not in canonical ordering.
     * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
     * @param inputShape 1-D.  Shape of the input SparseTensor.
     * @param reductionAxes 1-D.  Length-`K` vector containing the reduction axes.
     * @param options carries optional attributes values
     * @return a new instance of SparseReduceSumSparse
     * @see org.tensorflow.op.SparseOps.sparseReduceSumSparse
     * @param keepDims If true, retain reduced dimensions with length 1.
     */
    public fun <T : TType> sparseReduceSumSparse(
        inputIndices: Operand<TInt64>,
        inputValues: Operand<T>,
        inputShape: Operand<TInt64>,
        reductionAxes: Operand<TInt32>,
        keepDims: Boolean? = null
    ): SparseReduceSumSparse<T> = java.sparseReduceSumSparse<T>(    
        inputIndices,
        inputValues,
        inputShape,
        reductionAxes,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.sparse.SparseReduceSumSparse.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Reorders a SparseTensor into the canonical, row-major ordering.
     *  
     *  Note that by convention, all sparse ops preserve the canonical ordering along
     *  increasing dimension number. The only time ordering can be violated is during
     *  manual manipulation of the indices and values vectors to add entries.
     *  
     *  Reordering does not affect the shape of the SparseTensor.
     *  
     *  If the tensor has rank `R` and `N` non-empty values, `input_indices` has
     *  shape `&#91;N, R]`, input_values has length `N`, and input_shape has length `R`.
     * 
     * @param T data type for ` outputValues()` output
     * @param inputIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
     *  SparseTensor, possibly not in canonical ordering.
     * @param inputValues 1-D.  `N` non-empty values corresponding to `input_indices`.
     * @param inputShape 1-D.  Shape of the input SparseTensor.
     * @return a new instance of SparseReorder
     * @see org.tensorflow.op.SparseOps.sparseReorder
     */
    public fun <T : TType> sparseReorder(
        inputIndices: Operand<TInt64>,
        inputValues: Operand<T>,
        inputShape: Operand<TInt64>
    ): SparseReorder<T> = java.sparseReorder<T>(    
        inputIndices,
        inputValues,
        inputShape
        )

    /**
     * Reshapes a SparseTensor to represent values in a new dense shape.
     *  
     *  This operation has the same semantics as reshape on the represented dense
     *  tensor.  The `input_indices` are recomputed based on the requested `new_shape`.
     *  
     *  If one component of `new_shape` is the special value -1, the size of that
     *  dimension is computed so that the total dense size remains constant.  At
     *  most one component of `new_shape` can be -1.  The number of dense elements
     *  implied by `new_shape` must be the same as the number of dense elements
     *  originally implied by `input_shape`.
     *  
     *  Reshaping does not affect the order of values in the SparseTensor.
     *  
     *  If the input tensor has rank `R_in` and `N` non-empty values, and `new_shape`
     *  has length `R_out`, then `input_indices` has shape `&#91;N, R_in]`,
     *  `input_shape` has length `R_in`, `output_indices` has shape `&#91;N, R_out]`, and
     *  `output_shape` has length `R_out`.
     * 
     * @param inputIndices 2-D.  `N x R_in` matrix with the indices of non-empty values in a
     *  SparseTensor.
     * @param inputShape 1-D.  `R_in` vector with the input SparseTensor's dense shape.
     * @param newShape 1-D.  `R_out` vector with the requested new dense shape.
     * @return a new instance of SparseReshape
     * @see org.tensorflow.op.SparseOps.sparseReshape
     */
    public fun sparseReshape(
        inputIndices: Operand<TInt64>,
        inputShape: Operand<TInt64>,
        newShape: Operand<TInt64>
    ): SparseReshape = java.sparseReshape(    
        inputIndices,
        inputShape,
        newShape
        )

    /**
     * Computes the mean along sparse segments of a tensor.
     *  
     *  See `tf.sparse.segment_sum` for usage examples.
     *  
     *  Like `SegmentMean`, but `segment_ids` can have rank less than `data`'s first
     *  dimension, selecting a subset of dimension 0, specified by `indices`.
     * 
     * @param T data type for ` output()` output
     * @param data
     * @param indices A 1-D tensor. Has same rank as `segment_ids`.
     * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
     * @return a new instance of SparseSegmentMean
     * @see org.tensorflow.op.SparseOps.sparseSegmentMean
     */
    public fun <T : TNumber, U : TNumber, V : TNumber> sparseSegmentMean(
        `data`: Operand<T>,
        indices: Operand<U>,
        segmentIds: Operand<V>
    ): SparseSegmentMean<T> = java.sparseSegmentMean<T, U, V>(    
        data,
        indices,
        segmentIds
        )

    /**
     * Computes gradients for SparseSegmentMean.
     *  
     *  Returns tensor "output" with same shape as grad, except for dimension 0 whose
     *  value is output_dim0.
     * 
     * @param T data type for ` output()` output
     * @param grad gradient propagated to the SparseSegmentMean op.
     * @param indices indices passed to the corresponding SparseSegmentMean op.
     * @param segmentIds segment_ids passed to the corresponding SparseSegmentMean op.
     * @param outputDim0 dimension 0 of "data" passed to SparseSegmentMean op.
     * @return a new instance of SparseSegmentMeanGrad
     * @see org.tensorflow.op.SparseOps.sparseSegmentMeanGrad
     */
    public fun <T : TNumber, U : TNumber, V : TNumber> sparseSegmentMeanGrad(
        grad: Operand<T>,
        indices: Operand<U>,
        segmentIds: Operand<V>,
        outputDim0: Operand<TInt32>
    ): SparseSegmentMeanGrad<T> = java.sparseSegmentMeanGrad<T, U, V>(    
        grad,
        indices,
        segmentIds,
        outputDim0
        )

    /**
     * Computes the mean along sparse segments of a tensor.
     *  
     *  Like `SparseSegmentMean`, but allows missing ids in `segment_ids`. If an id is
     *  missing, the `output` tensor at that position will be zeroed.
     *  
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
     *  for an explanation of segments.
     * 
     * @param T data type for ` output()` output
     * @param data
     * @param indices A 1-D tensor. Has same rank as `segment_ids`.
     * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
     * @param numSegments Should equal the number of distinct segment IDs.
     * @return a new instance of SparseSegmentMeanWithNumSegments
     * @see org.tensorflow.op.SparseOps.sparseSegmentMeanWithNumSegments
     */
    public fun <T : TNumber, U : TNumber, V : TNumber, W : TNumber>
            sparseSegmentMeanWithNumSegments(
        `data`: Operand<T>,
        indices: Operand<U>,
        segmentIds: Operand<V>,
        numSegments: Operand<W>
    ): SparseSegmentMeanWithNumSegments<T> = java.sparseSegmentMeanWithNumSegments<T, U, V, W>(    
        data,
        indices,
        segmentIds,
        numSegments
        )

    /**
     * Computes the sum along sparse segments of a tensor divided by the sqrt of N.
     *  
     *  N is the size of the segment being reduced.
     *  
     *  See `tf.sparse.segment_sum` for usage examples.
     * 
     * @param T data type for ` output()` output
     * @param data
     * @param indices A 1-D tensor. Has same rank as `segment_ids`.
     * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
     * @return a new instance of SparseSegmentSqrtN
     * @see org.tensorflow.op.SparseOps.sparseSegmentSqrtN
     */
    public fun <T : TNumber, U : TNumber, V : TNumber> sparseSegmentSqrtN(
        `data`: Operand<T>,
        indices: Operand<U>,
        segmentIds: Operand<V>
    ): SparseSegmentSqrtN<T> = java.sparseSegmentSqrtN<T, U, V>(    
        data,
        indices,
        segmentIds
        )

    /**
     * Computes gradients for SparseSegmentSqrtN.
     *  
     *  Returns tensor "output" with same shape as grad, except for dimension 0 whose
     *  value is output_dim0.
     * 
     * @param T data type for ` output()` output
     * @param grad gradient propagated to the SparseSegmentSqrtN op.
     * @param indices indices passed to the corresponding SparseSegmentSqrtN op.
     * @param segmentIds segment_ids passed to the corresponding SparseSegmentSqrtN op.
     * @param outputDim0 dimension 0 of "data" passed to SparseSegmentSqrtN op.
     * @return a new instance of SparseSegmentSqrtNGrad
     * @see org.tensorflow.op.SparseOps.sparseSegmentSqrtNGrad
     */
    public fun <T : TNumber, U : TNumber, V : TNumber> sparseSegmentSqrtNGrad(
        grad: Operand<T>,
        indices: Operand<U>,
        segmentIds: Operand<V>,
        outputDim0: Operand<TInt32>
    ): SparseSegmentSqrtNGrad<T> = java.sparseSegmentSqrtNGrad<T, U, V>(    
        grad,
        indices,
        segmentIds,
        outputDim0
        )

    /**
     * Computes the sum along sparse segments of a tensor divided by the sqrt of N.
     *  
     *  N is the size of the segment being reduced.
     *  
     *  Like `SparseSegmentSqrtN`, but allows missing ids in `segment_ids`. If an id is
     *  missing, the `output` tensor at that position will be zeroed.
     *  
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
     *  for an explanation of segments.
     * 
     * @param T data type for ` output()` output
     * @param data
     * @param indices A 1-D tensor. Has same rank as `segment_ids`.
     * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
     * @param numSegments Should equal the number of distinct segment IDs.
     * @return a new instance of SparseSegmentSqrtNWithNumSegments
     * @see org.tensorflow.op.SparseOps.sparseSegmentSqrtNWithNumSegments
     */
    public fun <T : TNumber, U : TNumber, V : TNumber, W : TNumber>
            sparseSegmentSqrtNWithNumSegments(
        `data`: Operand<T>,
        indices: Operand<U>,
        segmentIds: Operand<V>,
        numSegments: Operand<W>
    ): SparseSegmentSqrtNWithNumSegments<T> = java.sparseSegmentSqrtNWithNumSegments<T, U, V, W>(    
        data,
        indices,
        segmentIds,
        numSegments
        )

    /**
     * Computes the sum along sparse segments of a tensor.
     *  
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
     *  for an explanation of segments.
     *  
     *  Like `SegmentSum`, but `segment_ids` can have rank less than `data`'s first
     *  dimension, selecting a subset of dimension 0, specified by `indices`.
     *  
     *  For example:
     *  ```
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
     *  ```
     * 
     * 
     * @param T data type for ` output()` output
     * @param data
     * @param indices A 1-D tensor. Has same rank as `segment_ids`.
     * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
     * @return a new instance of SparseSegmentSum
     * @see org.tensorflow.op.SparseOps.sparseSegmentSum
     */
    public fun <T : TNumber, U : TNumber, V : TNumber> sparseSegmentSum(
        `data`: Operand<T>,
        indices: Operand<U>,
        segmentIds: Operand<V>
    ): SparseSegmentSum<T> = java.sparseSegmentSum<T, U, V>(    
        data,
        indices,
        segmentIds
        )

    /**
     * Computes the sum along sparse segments of a tensor.
     *  
     *  Like `SparseSegmentSum`, but allows missing ids in `segment_ids`. If an id is
     *  missing, the `output` tensor at that position will be zeroed.
     *  
     *  Read
     *  &#91;the section on
     * segmentation](https://tensorflow.org/api_docs/python/tf/sparse#Segmentation)
     *  for an explanation of segments.
     *  
     *  For example:
     *  ```
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
     *  ```
     * 
     * 
     * @param T data type for ` output()` output
     * @param data
     * @param indices A 1-D tensor. Has same rank as `segment_ids`.
     * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
     * @param numSegments Should equal the number of distinct segment IDs.
     * @return a new instance of SparseSegmentSumWithNumSegments
     * @see org.tensorflow.op.SparseOps.sparseSegmentSumWithNumSegments
     */
    public fun <T : TNumber, U : TNumber, V : TNumber, W : TNumber> sparseSegmentSumWithNumSegments(
        `data`: Operand<T>,
        indices: Operand<U>,
        segmentIds: Operand<V>,
        numSegments: Operand<W>
    ): SparseSegmentSumWithNumSegments<T> = java.sparseSegmentSumWithNumSegments<T, U, V, W>(    
        data,
        indices,
        segmentIds,
        numSegments
        )

    /**
     * Slice a `SparseTensor` based on the `start` and `size`.
     *  
     *  For example, if the input is
     *  
     *      input_tensor = shape = &#91;2, 7]
     *      &#91;    a   d e  ]
     *      &#91;b c          ]
     *  
     *  Graphically the output tensors are:
     *  
     *      sparse_slice(&#91;0, 0], &#91;2, 4]) = shape = &#91;2, 4]
     *      &#91;    a  ]
     *      &#91;b c    ]
     *  
     *      sparse_slice(&#91;0, 4], &#91;2, 3]) = shape = &#91;2, 3]
     *      &#91; d e  ]
     *      &#91;      ]
     * 
     * @param T data type for ` outputValues()` output
     * @param indices 2-D tensor represents the indices of the sparse tensor.
     * @param values 1-D tensor represents the values of the sparse tensor.
     * @param shape 1-D. tensor represents the shape of the sparse tensor.
     * @param start 1-D. tensor represents the start of the slice.
     * @param size 1-D. tensor represents the size of the slice.
     *  output indices: A list of 1-D tensors represents the indices of the output
     *  sparse tensors.
     * @return a new instance of SparseSlice
     * @see org.tensorflow.op.SparseOps.sparseSlice
     */
    public fun <T : TType> sparseSlice(
        indices: Operand<TInt64>,
        values: Operand<T>,
        shape: Operand<TInt64>,
        start: Operand<TInt64>,
        size: Operand<TInt64>
    ): SparseSlice<T> = java.sparseSlice<T>(    
        indices,
        values,
        shape,
        start,
        size
        )

    /**
     * The gradient operator for the SparseSlice op.
     *  
     *  This op takes in the upstream gradient w.r.t. non-empty values of
     *  the sliced `SparseTensor`, and outputs the gradients w.r.t.
     *  the non-empty values of input `SparseTensor`.
     * 
     * @param T data type for ` valGrad()` output
     * @param backpropValGrad 1-D. The gradient with respect to
     *  the non-empty values of the sliced `SparseTensor`.
     * @param inputIndices 2-D.  The `indices` of the input `SparseTensor`.
     * @param inputStart 1-D. tensor represents the start of the slice.
     * @param outputIndices 2-D.  The `indices` of the sliced `SparseTensor`.
     * @return a new instance of SparseSliceGrad
     * @see org.tensorflow.op.SparseOps.sparseSliceGrad
     */
    public fun <T : TType> sparseSliceGrad(
        backpropValGrad: Operand<T>,
        inputIndices: Operand<TInt64>,
        inputStart: Operand<TInt64>,
        outputIndices: Operand<TInt64>
    ): SparseSliceGrad<T> = java.sparseSliceGrad<T>(    
        backpropValGrad,
        inputIndices,
        inputStart,
        outputIndices
        )

    /**
     * Applies softmax to a batched N-D `SparseTensor`.
     *  
     *  The inputs represent an N-D SparseTensor  with logical shape `&#91;..., B, C]`
     *  (where `N >= 2`), and with indices sorted in the canonical lexicographic order.
     *  
     *  This op is equivalent to applying the normal `tf.nn.softmax()` to each innermost
     *  logical submatrix with shape `&#91;B, C]`, but with the catch that <i>the implicitly
     *  zero elements do not participate</i>.  Specifically, the algorithm is equivalent
     *  to the following:
     *  
     *    (1) Applies `tf.nn.softmax()` to a densified view of each innermost submatrix
     *        with shape `&#91;B, C]`, along the size-C dimension;
     *    (2) Masks out the original implicitly-zero locations;
     *    (3) Renormalizes the remaining elements.
     *  
     *  Hence, the `SparseTensor` result has exactly the same non-zero indices and
     *  shape.
     * 
     * @param T data type for ` output()` output
     * @param spIndices 2-D.  `NNZ x R` matrix with the indices of non-empty values in a
     *  SparseTensor, in canonical ordering.
     * @param spValues 1-D.  `NNZ` non-empty values corresponding to `sp_indices`.
     * @param spShape 1-D.  Shape of the input SparseTensor.
     * @return a new instance of SparseSoftmax
     * @see org.tensorflow.op.SparseOps.sparseSoftmax
     */
    public fun <T : TNumber> sparseSoftmax(
        spIndices: Operand<TInt64>,
        spValues: Operand<T>,
        spShape: Operand<TInt64>
    ): SparseSoftmax<T> = java.sparseSoftmax<T>(    
        spIndices,
        spValues,
        spShape
        )

    /**
     * Returns the element-wise max of two SparseTensors.
     *  
     *  Assumes the two SparseTensors have the same shape, i.e., no broadcasting.
     * 
     * @param T data type for ` outputValues()` output
     * @param aIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
     *  SparseTensor, in the canonical lexicographic ordering.
     * @param aValues 1-D.  `N` non-empty values corresponding to `a_indices`.
     * @param aShape 1-D.  Shape of the input SparseTensor.
     * @param bIndices counterpart to `a_indices` for the other operand.
     * @param bValues counterpart to `a_values` for the other operand; must be of the same dtype.
     * @param bShape counterpart to `a_shape` for the other operand; the two shapes must be equal.
     * @return a new instance of SparseSparseMaximum
     * @see org.tensorflow.op.SparseOps.sparseSparseMaximum
     */
    public fun <T : TNumber> sparseSparseMaximum(
        aIndices: Operand<TInt64>,
        aValues: Operand<T>,
        aShape: Operand<TInt64>,
        bIndices: Operand<TInt64>,
        bValues: Operand<T>,
        bShape: Operand<TInt64>
    ): SparseSparseMaximum<T> = java.sparseSparseMaximum<T>(    
        aIndices,
        aValues,
        aShape,
        bIndices,
        bValues,
        bShape
        )

    /**
     * Returns the element-wise min of two SparseTensors.
     *  
     *  Assumes the two SparseTensors have the same shape, i.e., no broadcasting.
     * 
     * @param T data type for ` outputValues()` output
     * @param aIndices 2-D.  `N x R` matrix with the indices of non-empty values in a
     *  SparseTensor, in the canonical lexicographic ordering.
     * @param aValues 1-D.  `N` non-empty values corresponding to `a_indices`.
     * @param aShape 1-D.  Shape of the input SparseTensor.
     * @param bIndices counterpart to `a_indices` for the other operand.
     * @param bValues counterpart to `a_values` for the other operand; must be of the same dtype.
     * @param bShape counterpart to `a_shape` for the other operand; the two shapes must be equal.
     * @return a new instance of SparseSparseMinimum
     * @see org.tensorflow.op.SparseOps.sparseSparseMinimum
     */
    public fun <T : TType> sparseSparseMinimum(
        aIndices: Operand<TInt64>,
        aValues: Operand<T>,
        aShape: Operand<TInt64>,
        bIndices: Operand<TInt64>,
        bValues: Operand<T>,
        bShape: Operand<TInt64>
    ): SparseSparseMinimum<T> = java.sparseSparseMinimum<T>(    
        aIndices,
        aValues,
        aShape,
        bIndices,
        bValues,
        bShape
        )

    /**
     * Split a `SparseTensor` into `num_split` tensors along one dimension.
     *  
     *  If the `shape&#91;split_dim]` is not an integer multiple of `num_split`. Slices
     *  `&#91;0 : shape&#91;split_dim] % num_split]` gets one extra dimension.
     *  For example, if `split_dim = 1` and `num_split = 2` and the input is
     *  
     *      input_tensor = shape = &#91;2, 7]
     *      &#91;    a   d e  ]
     *      &#91;b c          ]
     *  
     *  Graphically the output tensors are:
     *  
     *      output_tensor&#91;0] = shape = &#91;2, 4]
     *      &#91;    a  ]
     *      &#91;b c    ]
     *  
     *      output_tensor&#91;1] = shape = &#91;2, 3]
     *      &#91; d e  ]
     *      &#91;      ]
     * 
     * @param T data type for ` outputValues()` output
     * @param splitDim 0-D.  The dimension along which to split.  Must be in the range
     *  `&#91;0, rank(shape))`.
     * @param indices 2-D tensor represents the indices of the sparse tensor.
     * @param values 1-D tensor represents the values of the sparse tensor.
     * @param shape 1-D. tensor represents the shape of the sparse tensor.
     *  output indices: A list of 1-D tensors represents the indices of the output
     *  sparse tensors.
     * @param numSplit The number of ways to split.
     * @return a new instance of SparseSplit
     * @see org.tensorflow.op.SparseOps.sparseSplit
     */
    public fun <T : TType> sparseSplit(
        splitDim: Operand<TInt64>,
        indices: Operand<TInt64>,
        values: Operand<T>,
        shape: Operand<TInt64>,
        numSplit: Long
    ): SparseSplit<T> = java.sparseSplit<T>(    
        splitDim,
        indices,
        values,
        shape,
        numSplit
        )

    /**
     * Adds up a `SparseTensor` and a dense `Tensor`, producing a dense `Tensor`.
     *  
     *  This Op does not require `a_indices` be sorted in standard lexicographic order.
     * 
     * @param U data type for ` output()` output
     * @param aIndices 2-D.  The `indices` of the `SparseTensor`, with shape `&#91;nnz, ndims]`.
     * @param aValues 1-D.  The `values` of the `SparseTensor`, with shape `&#91;nnz]`.
     * @param aShape 1-D.  The `shape` of the `SparseTensor`, with shape `&#91;ndims]`.
     * @param b `ndims`-D Tensor.  With shape `a_shape`.
     * @return a new instance of SparseTensorDenseAdd
     * @see org.tensorflow.op.SparseOps.sparseTensorDenseAdd
     */
    public fun <U : TType, T : TNumber> sparseTensorDenseAdd(
        aIndices: Operand<T>,
        aValues: Operand<U>,
        aShape: Operand<T>,
        b: Operand<U>
    ): SparseTensorDenseAdd<U> = java.sparseTensorDenseAdd<U, T>(    
        aIndices,
        aValues,
        aShape,
        b
        )

    /**
     * Multiply SparseTensor (of rank 2) "A" by dense matrix "B".
     *  
     *  No validity checking is performed on the indices of A.  However, the following
     *  input format is recommended for optimal behavior:
     *  
     *  if adjoint_a == false:
     *    A should be sorted in lexicographically increasing order.  Use SparseReorder
     *    if you're not sure.
     *  if adjoint_a == true:
     *    A should be sorted in order of increasing dimension 1 (i.e., "column major"
     *    order instead of "row major" order).
     * 
     * @param U data type for ` product()` output
     * @param aIndices 2-D.  The `indices` of the `SparseTensor`, size `&#91;nnz, 2]` Matrix.
     * @param aValues 1-D.  The `values` of the `SparseTensor`, size `&#91;nnz]` Vector.
     * @param aShape 1-D.  The `shape` of the `SparseTensor`, size `&#91;2]` Vector.
     * @param b 2-D.  A dense Matrix.
     * @param options carries optional attributes values
     * @return a new instance of SparseTensorDenseMatMul
     * @see org.tensorflow.op.SparseOps.sparseTensorDenseMatMul
     * @param adjointA Use the adjoint of A in the matrix multiply.  If A is complex, this
     *  is transpose(conj(A)).  Otherwise it's transpose(A).
     * @param adjointB Use the adjoint of B in the matrix multiply.  If B is complex, this
     *  is transpose(conj(B)).  Otherwise it's transpose(B).
     */
    public fun <U : TType, T : TNumber> sparseTensorDenseMatMul(
        aIndices: Operand<T>,
        aValues: Operand<U>,
        aShape: Operand<TInt64>,
        b: Operand<U>,
        adjointA: Boolean? = null,
        adjointB: Boolean? = null
    ): SparseTensorDenseMatMul<U> = java.sparseTensorDenseMatMul<U, T>(    
        aIndices,
        aValues,
        aShape,
        b,
        *listOfNotNull(
            adjointA?.let{ org.tensorflow.op.sparse.SparseTensorDenseMatMul.adjointA(it) },
            adjointB?.let{ org.tensorflow.op.sparse.SparseTensorDenseMatMul.adjointB(it) }
        ).toTypedArray()
        )

    /**
     * Converts a sparse representation into a dense tensor.
     *  
     *  Builds an array `dense` with shape `output_shape` such that
     *  ```
     *  # If sparse_indices is scalar
     *  dense[i] = (i == sparse_indices ? sparse_values : default_value)
     * 
     *  # If sparse_indices is a vector, then for each i
     *  dense[sparse_indices[i]] = sparse_values[i]
     * 
     *  # If sparse_indices is an n by d matrix, then for each i in [0, n)
     *  dense[sparse_indices[i][0], ..., sparse_indices[i][d-1]] = sparse_values[i]
     *  ```
     * 
     *  All other values in `dense` are set to `default_value`.  If `sparse_values` is a
     *  scalar, all sparse indices are set to this single value.
     *  
     *  Indices should be sorted in lexicographic order, and indices must not
     *  contain any repeats. If `validate_indices` is true, these properties
     *  are checked during execution.
     * 
     * @param U data type for ` dense()` output
     * @param sparseIndices 0-D, 1-D, or 2-D.  `sparse_indices&#91;i]` contains the complete
     *  index where `sparse_values&#91;i]` will be placed.
     * @param outputShape 1-D.  Shape of the dense output tensor.
     * @param sparseValues 1-D.  Values corresponding to each row of `sparse_indices`,
     *  or a scalar value to be used for all sparse indices.
     * @param defaultValue Scalar value to set for indices not specified in
     *  `sparse_indices`.
     * @param options carries optional attributes values
     * @return a new instance of SparseToDense
     * @see org.tensorflow.op.SparseOps.sparseToDense
     * @param validateIndices If true, indices are checked to make sure they are sorted in
     *  lexicographic order and that there are no repeats.
     */
    public fun <U : TType, T : TNumber> sparseToDense(
        sparseIndices: Operand<T>,
        outputShape: Operand<T>,
        sparseValues: Operand<U>,
        defaultValue: Operand<U>,
        validateIndices: Boolean? = null
    ): SparseToDense<U> = java.sparseToDense<U, T>(    
        sparseIndices,
        outputShape,
        sparseValues,
        defaultValue,
        *listOfNotNull(
            validateIndices?.let{ org.tensorflow.op.sparse.SparseToDense.validateIndices(it) }
        ).toTypedArray()
        )

    /**
     * Applies set operation along last dimension of 2 `SparseTensor` inputs.
     *  
     *  See SetOperationOp::SetOperationFromContext for values of `set_operation`.
     *  
     *  If `validate_indices` is `True`, `sparse.SparseToSparseSetOperation` validates the
     *  order and range of `set1` and `set2` indices.
     *  
     *  Input `set1` is a `SparseTensor` represented by `set1_indices`, `set1_values`,
     *  and `set1_shape`. For `set1` ranked `n`, 1st `n-1` dimensions must be the same
     *  as `set2`. Dimension `n` contains values in a set, duplicates are allowed but
     *  ignored.
     *  
     *  Input `set2` is a `SparseTensor` represented by `set2_indices`, `set2_values`,
     *  and `set2_shape`. For `set2` ranked `n`, 1st `n-1` dimensions must be the same
     *  as `set1`. Dimension `n` contains values in a set, duplicates are allowed but
     *  ignored.
     *  
     *  If `validate_indices` is `True`, this op validates the order and range of `set1`
     *  and `set2` indices.
     *  
     *  Output `result` is a `SparseTensor` represented by `result_indices`,
     *  `result_values`, and `result_shape`. For `set1` and `set2` ranked `n`, this
     *  has rank `n` and the same 1st `n-1` dimensions as `set1` and `set2`. The `nth`
     *  dimension contains the result of `set_operation` applied to the corresponding
     *  `&#91;0...n-1]` dimension of `set`.
     * 
     * @param T data type for ` resultValues()` output
     * @param set1Indices 2D `Tensor`, indices of a `SparseTensor`. Must be in row-major
     *  order.
     * @param set1Values 1D `Tensor`, values of a `SparseTensor`. Must be in row-major
     *  order.
     * @param set1Shape 1D `Tensor`, shape of a `SparseTensor`. `set1_shape&#91;0...n-1]` must
     *  be the same as `set2_shape&#91;0...n-1]`, `set1_shape&#91;n]` is the
     *  max set size across `0...n-1` dimensions.
     * @param set2Indices 2D `Tensor`, indices of a `SparseTensor`. Must be in row-major
     *  order.
     * @param set2Values 1D `Tensor`, values of a `SparseTensor`. Must be in row-major
     *  order.
     * @param set2Shape 1D `Tensor`, shape of a `SparseTensor`. `set2_shape&#91;0...n-1]` must
     *  be the same as `set1_shape&#91;0...n-1]`, `set2_shape&#91;n]` is the
     *  max set size across `0...n-1` dimensions.
     * @param setOperation
     * @param options carries optional attributes values
     * @return a new instance of SparseToSparseSetOperation
     * @see org.tensorflow.op.SparseOps.sparseToSparseSetOperation
     * @param validateIndices @param validateIndices
     */
    public fun <T : TType> sparseToSparseSetOperation(
        set1Indices: Operand<TInt64>,
        set1Values: Operand<T>,
        set1Shape: Operand<TInt64>,
        set2Indices: Operand<TInt64>,
        set2Values: Operand<T>,
        set2Shape: Operand<TInt64>,
        setOperation: String,
        validateIndices: Boolean? = null
    ): SparseToSparseSetOperation<T> = java.sparseToSparseSetOperation<T>(    
        set1Indices,
        set1Values,
        set1Shape,
        set2Indices,
        set2Values,
        set2Shape,
        setOperation,
        *listOfNotNull(
            validateIndices?.let{ org.tensorflow.op.sparse.SparseToSparseSetOperation.validateIndices(it)
            }
        ).toTypedArray()
        )

    /**
     * Read `SparseTensors` from a `SparseTensorsMap` and concatenate them.
     *  
     *  The input `sparse_handles` must be an `int64` matrix of shape `&#91;N, 1]` where
     *  `N` is the minibatch size and the rows correspond to the output handles of
     *  `AddSparseToTensorsMap` or `AddManySparseToTensorsMap`.  The ranks of the
     *  original `SparseTensor` objects that went into the given input ops must all
     *  match.  When the final `SparseTensor` is created, it has rank one
     *  higher than the ranks of the incoming `SparseTensor` objects
     *  (they have been concatenated along a new row dimension on the left).
     *  
     *  The output `SparseTensor` object's shape values for all dimensions but the
     *  first are the max across the input `SparseTensor` objects' shape values
     *  for the corresponding dimensions.  Its first shape value is `N`, the minibatch
     *  size.
     *  
     *  The input `SparseTensor` objects' indices are assumed ordered in
     *  standard lexicographic order.  If this is not the case, after this
     *  step run `SparseReorder` to restore index ordering.
     *  
     *  For example, if the handles represent an input, which is a `&#91;2, 3]` matrix
     *  representing two original `SparseTensor` objects:
     *  ```
     *      index = [ 0]
     *              [10]
     *              [20]
     *      values = [1, 2, 3]
     *      shape = [50]
     *  ```
     * 
     *  and
     *  ```
     *      index = [ 2]
     *              [10]
     *      values = [4, 5]
     *      shape = [30]
     *  ```
     * 
     *  then the final `SparseTensor` will be:
     *  ```
     *      index = [0  0]
     *              [0 10]
     *              [0 20]
     *              [1  2]
     *              [1 10]
     *      values = [1, 2, 3, 4, 5]
     *      shape = [2 50]
     *  ```
     * 
     * 
     * @param T data type for ` sparseValues()` output
     * @param sparseHandles 1-D, The `N` serialized `SparseTensor` objects.
     *  Shape: `&#91;N]`.
     * @param dtype The `dtype` of the `SparseTensor` objects stored in the
     *  `SparseTensorsMap`.
     * @param options carries optional attributes values
     * @return a new instance of TakeManySparseFromTensorsMap
     * @see org.tensorflow.op.SparseOps.takeManySparseFromTensorsMap
     * @param container The container name for the `SparseTensorsMap` read by this op.
     * @param sharedName The shared name for the `SparseTensorsMap` read by this op.
     *  It should not be blank; rather the `shared_name` or unique Operation name
     *  of the Op that created the original `SparseTensorsMap` should be used.
     */
    public fun <T : TType> takeManySparseFromTensorsMap(
        sparseHandles: Operand<TInt64>,
        dtype: Class<T>,
        container: String? = null,
        sharedName: String? = null
    ): TakeManySparseFromTensorsMap<T> = java.takeManySparseFromTensorsMap<T>(    
        sparseHandles,
        dtype,
        *listOfNotNull(
            container?.let{ org.tensorflow.op.sparse.TakeManySparseFromTensorsMap.container(it) },
            sharedName?.let{ org.tensorflow.op.sparse.TakeManySparseFromTensorsMap.sharedName(it) }
        ).toTypedArray()
        )

    /**
     * Deserialize `SparseTensor` objects.
     *  
     *  The input `serialized_sparse` must have the shape `&#91;?, ?, ..., ?, 3]` where
     *  the last dimension stores serialized `SparseTensor` objects and the other N
     *  dimensions (N >= 0) correspond to a batch. The ranks of the original
     *  `SparseTensor` objects must all match. When the final `SparseTensor` is
     *  created, its rank is the rank of the incoming `SparseTensor` objects plus N;
     *  the sparse tensors have been concatenated along new dimensions, one for each
     *  batch.
     *  
     *  The output `SparseTensor` object's shape values for the original dimensions
     *  are the max across the input `SparseTensor` objects' shape values for the
     *  corresponding dimensions. The new dimensions match the size of the batch.
     *  
     *  The input `SparseTensor` objects' indices are assumed ordered in
     *  standard lexicographic order.  If this is not the case, after this
     *  step run `SparseReorder` to restore index ordering.
     *  
     *  For example, if the serialized input is a `&#91;2 x 3]` matrix representing two
     *  original `SparseTensor` objects:
     *  
     *      index = &#91; 0]
     *              &#91;10]
     *              &#91;20]
     *      values = &#91;1, 2, 3]
     *      shape = &#91;50]
     *  
     *  and
     *  
     *      index = &#91; 2]
     *              &#91;10]
     *      values = &#91;4, 5]
     *      shape = &#91;30]
     *  
     *  then the final deserialized `SparseTensor` will be:
     *  
     *      index = &#91;0  0]
     *              &#91;0 10]
     *              &#91;0 20]
     *              &#91;1  2]
     *              &#91;1 10]
     *      values = &#91;1, 2, 3, 4, 5]
     *      shape = &#91;2 50]
     * 
     * @param U data type for ` sparseValues()` output
     * @param serializedSparse The serialized `SparseTensor` objects. The last dimension
     *  must have 3 columns.
     * @param dtype The `dtype` of the serialized `SparseTensor` objects.
     * @return a new instance of DeserializeSparse
     * @see org.tensorflow.op.SparseOps.deserializeSparse
     */
    @JvmName("deserializeSparseReified")
    public inline fun <reified U : TType, T : TType>
            deserializeSparse(serializedSparse: Operand<T>): DeserializeSparse<U> =
            deserializeSparse<U, T>(serializedSparse, U::class.java)

    /**
     * Extracts the average sparse gradient in a SparseConditionalAccumulator.
     *  
     *  The op will blocks until sufficient (i.e., more than num_required)
     *  gradients have been accumulated. If the accumulator has already
     *  aggregated more than num_required gradients, it will return its
     *  average of the accumulated gradients.  Also automatically increments
     *  the recorded global_step in the accumulator by 1, and resets the
     *  aggregate to 0.
     * 
     * @param T data type for ` values()` output
     * @param handle The handle to a SparseConditionalAccumulator.
     * @param numRequired Number of gradients required before we return an aggregate.
     * @param dtype The data type of accumulated gradients. Needs to correspond to the type
     *  of the accumulator.
     * @return a new instance of SparseAccumulatorTakeGradient
     * @see org.tensorflow.op.SparseOps.sparseAccumulatorTakeGradient
     */
    @JvmName("sparseAccumulatorTakeGradientReified")
    public inline fun <reified T : TType> sparseAccumulatorTakeGradient(handle: Operand<TString>,
            numRequired: Operand<TInt32>): SparseAccumulatorTakeGradient<T> =
            sparseAccumulatorTakeGradient<T>(handle, numRequired, T::class.java)

    /**
     * A conditional accumulator for aggregating sparse gradients.
     *  
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
     * @see org.tensorflow.op.SparseOps.sparseConditionalAccumulator
     * @param container If non-empty, this accumulator is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this accumulator will be shared under the given name
     *  across multiple sessions.
     * @param reductionType @param reductionType
     */
    @JvmName("sparseConditionalAccumulatorReified")
    public inline fun <reified T : TType> sparseConditionalAccumulator(
        shape: Shape,
        container: String? = null,
        sharedName: String? = null,
        reductionType: String? = null
    ): SparseConditionalAccumulator = sparseConditionalAccumulator<T>(T::class.java, shape,
            container, sharedName, reductionType)

    /**
     * Read `SparseTensors` from a `SparseTensorsMap` and concatenate them.
     *  
     *  The input `sparse_handles` must be an `int64` matrix of shape `&#91;N, 1]` where
     *  `N` is the minibatch size and the rows correspond to the output handles of
     *  `AddSparseToTensorsMap` or `AddManySparseToTensorsMap`.  The ranks of the
     *  original `SparseTensor` objects that went into the given input ops must all
     *  match.  When the final `SparseTensor` is created, it has rank one
     *  higher than the ranks of the incoming `SparseTensor` objects
     *  (they have been concatenated along a new row dimension on the left).
     *  
     *  The output `SparseTensor` object's shape values for all dimensions but the
     *  first are the max across the input `SparseTensor` objects' shape values
     *  for the corresponding dimensions.  Its first shape value is `N`, the minibatch
     *  size.
     *  
     *  The input `SparseTensor` objects' indices are assumed ordered in
     *  standard lexicographic order.  If this is not the case, after this
     *  step run `SparseReorder` to restore index ordering.
     *  
     *  For example, if the handles represent an input, which is a `&#91;2, 3]` matrix
     *  representing two original `SparseTensor` objects:
     *  ```
     *      index = [ 0]
     *              [10]
     *              [20]
     *      values = [1, 2, 3]
     *      shape = [50]
     *  ```
     * 
     *  and
     *  ```
     *      index = [ 2]
     *              [10]
     *      values = [4, 5]
     *      shape = [30]
     *  ```
     * 
     *  then the final `SparseTensor` will be:
     *  ```
     *      index = [0  0]
     *              [0 10]
     *              [0 20]
     *              [1  2]
     *              [1 10]
     *      values = [1, 2, 3, 4, 5]
     *      shape = [2 50]
     *  ```
     * 
     * 
     * @param T data type for ` sparseValues()` output
     * @param sparseHandles 1-D, The `N` serialized `SparseTensor` objects.
     *  Shape: `&#91;N]`.
     * @param dtype The `dtype` of the `SparseTensor` objects stored in the
     *  `SparseTensorsMap`.
     * @param options carries optional attributes values
     * @return a new instance of TakeManySparseFromTensorsMap
     * @see org.tensorflow.op.SparseOps.takeManySparseFromTensorsMap
     * @param container The container name for the `SparseTensorsMap` read by this op.
     * @param sharedName The shared name for the `SparseTensorsMap` read by this op.
     *  It should not be blank; rather the `shared_name` or unique Operation name
     *  of the Op that created the original `SparseTensorsMap` should be used.
     */
    @JvmName("takeManySparseFromTensorsMapReified")
    public inline fun <reified T : TType> takeManySparseFromTensorsMap(
        sparseHandles: Operand<TInt64>,
        container: String? = null,
        sharedName: String? = null
    ): TakeManySparseFromTensorsMap<T> = takeManySparseFromTensorsMap<T>(sparseHandles,
            T::class.java, container, sharedName)
}
