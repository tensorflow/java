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
import org.tensorflow.op.xla.BroadcastHelper
import org.tensorflow.op.xla.ClusterOutput
import org.tensorflow.op.xla.Conv
import org.tensorflow.op.xla.Dequantize
import org.tensorflow.op.xla.Dot
import org.tensorflow.op.xla.DynamicSlice
import org.tensorflow.op.xla.DynamicUpdateSlice
import org.tensorflow.op.xla.Einsum
import org.tensorflow.op.xla.Gather
import org.tensorflow.op.xla.KeyValueSort
import org.tensorflow.op.xla.Pad
import org.tensorflow.op.xla.Recv
import org.tensorflow.op.xla.ReplicaId
import org.tensorflow.op.xla.SelfAdjointEig
import org.tensorflow.op.xla.Send
import org.tensorflow.op.xla.Sharding
import org.tensorflow.op.xla.Sort
import org.tensorflow.op.xla.Svd
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building `xla` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class XlaOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.XlaOps = ops.java.xla

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Helper operator for performing XLA-style broadcasts
     *
     *  Broadcasts `lhs` and `rhs` to the same rank, by adding size 1 dimensions to
     *  whichever of `lhs` and `rhs` has the lower rank, using XLA's broadcasting rules
     *  for binary operators.
     *
     * @param T data type for ` lhsOutput()` output
     * @param lhs the LHS input tensor
     * @param rhs the RHS input tensor
     * @param broadcastDims an XLA-style broadcast dimension specification
     * @return a new instance of BroadcastHelper
     * @see org.tensorflow.op.XlaOps.broadcastHelper
     */
    public fun <T : TType, U : TNumber> broadcastHelper(
        lhs: Operand<T>,
        rhs: Operand<T>,
        broadcastDims: Operand<U>
    ): BroadcastHelper<T> = java.broadcastHelper<T, U>(
        lhs,
        rhs,
        broadcastDims
    )

    /**
     * Operator that connects the output of an XLA computation to other consumer graph nodes.
     *
     * @param T data type for ` outputs()` output
     * @param input
     * @return a new instance of ClusterOutput
     * @see org.tensorflow.op.XlaOps.clusterOutput
     */
    public fun <T : TType> clusterOutput(input: Operand<T>): ClusterOutput<T> =
        java.clusterOutput<T>(
            input
        )

    /**
     * Wraps the XLA ConvGeneralDilated operator, documented at
     *
     *   https://www.tensorflow.org/performance/xla/operation_semantics#conv_convolution
     *  .
     *
     * @param T data type for ` output()` output
     * @param lhs the input tensor
     * @param rhs the kernel tensor
     * @param windowStrides the inter-window strides
     * @param padding the padding to apply at the start and end of each input dimensions
     * @param lhsDilation dilation to apply between input elements
     * @param rhsDilation dilation to apply between kernel elements
     * @param featureGroupCount number of feature groups for grouped convolution.
     * @param dimensionNumbers a serialized xla::ConvolutionDimensionNumbers proto.
     * @param precisionConfig a serialized xla::PrecisionConfig proto.
     * @return a new instance of Conv
     * @see org.tensorflow.op.XlaOps.conv
     */
    public fun <T : TType, U : TNumber> conv(
        lhs: Operand<T>,
        rhs: Operand<T>,
        windowStrides: Operand<U>,
        padding: Operand<U>,
        lhsDilation: Operand<U>,
        rhsDilation: Operand<U>,
        featureGroupCount: Operand<U>,
        dimensionNumbers: String,
        precisionConfig: String
    ): Conv<T> = java.conv<T, U>(
        lhs,
        rhs,
        windowStrides,
        padding,
        lhsDilation,
        rhsDilation,
        featureGroupCount,
        dimensionNumbers,
        precisionConfig
    )

    /**
     * Takes the packed uint32 input and unpacks the input to uint8 to do
     *
     *  Dequantization on device.
     *
     * @param input Input tensors whose types is uint32, shape is &#91;d0, ..., dn].
     * @param minRange The minimum scalar value possibly produced for the input.
     * @param maxRange The maximum scalar value possibly produced for the input.
     * @param mode String to determine the dequantize mode in {"MIN_COMBINED", "MIN_FIRST",
     * "SCALED"}.
     * @param transposeOutput Boolean to determine if output is transposed. transpose_output
     *  is faster when input is large and rank of input is higher than 1.
     * @return a new instance of Dequantize
     * @see org.tensorflow.op.XlaOps.dequantize
     */
    public fun dequantize(
        input: Operand<*>,
        minRange: Float,
        maxRange: Float,
        mode: String,
        transposeOutput: Boolean
    ): Dequantize = java.dequantize(
        input,
        minRange,
        maxRange,
        mode,
        transposeOutput
    )

    /**
     * Wraps the XLA DotGeneral operator, documented at
     *
     *   https://www.tensorflow.org/performance/xla/operation_semantics#dotgeneral
     *  .
     *
     * @param T data type for ` output()` output
     * @param lhs the LHS tensor
     * @param rhs the RHS tensor
     * @param dimensionNumbers a serialized xla::DotDimensionNumbers proto.
     * @param precisionConfig a serialized xla::PrecisionConfig proto.
     * @return a new instance of Dot
     * @see org.tensorflow.op.XlaOps.dot
     */
    public fun <T : TType> dot(
        lhs: Operand<T>,
        rhs: Operand<T>,
        dimensionNumbers: String,
        precisionConfig: String
    ): Dot<T> = java.dot<T>(
        lhs,
        rhs,
        dimensionNumbers,
        precisionConfig
    )

    /**
     * Wraps the XLA DynamicSlice operator, documented at
     *
     *   https://www.tensorflow.org/performance/xla/operation_semantics#dynamicslice
     *  .
     *
     *  DynamicSlice extracts a sub-array from the input array at dynamic
     *  start_indices. The size of the slice in each dimension is passed in
     *  size_indices, which specify the end point of exclusive slice intervals in each
     *  dimension -- &#91;start, start + size). The shape of start_indices must have rank 1,
     *  with dimension size equal to the rank of operand.
     *
     * @param T data type for ` output()` output
     * @param input A `Tensor` of type T.
     * @param startIndices List of N integers containing the slice size for each
     *  dimension. Each value must be strictly greater than zero, and start + size
     *  must be less than or equal to the size of the dimension to avoid
     *  implementation defined behavior.
     * @param sizeIndices
     * @return a new instance of DynamicSlice
     * @see org.tensorflow.op.XlaOps.dynamicSlice
     */
    public fun <T : TType, U : TNumber> dynamicSlice(
        input: Operand<T>,
        startIndices: Operand<U>,
        sizeIndices: Operand<U>
    ): DynamicSlice<T> = java.dynamicSlice<T, U>(
        input,
        startIndices,
        sizeIndices
    )

    /**
     * Wraps the XLA DynamicUpdateSlice operator, documented at
     *
     *   https://www.tensorflow.org/performance/xla/operation_semantics#dynamicupdateslice
     *  .
     *
     *  XlaDynamicUpdateSlice generates a result which is the value of the `input`
     *  operand, with a slice update overwritten at `indices`. The shape of `update`
     *  determines the shape of the sub-array of the result which is updated. The shape
     *  of indices must be rank == 1, with dimension size equal to the rank of `input`.
     *
     *  Handling of out-of-bounds slice indices is implementation-defined.
     *
     * @param T data type for ` output()` output
     * @param input A `Tensor` of type T.
     * @param update A `Tensor` of type T. Same rank as `input`.
     * @param indices A vector of indices into `input`. Must have length equal to the rank of
     *  `input`.
     * @return a new instance of DynamicUpdateSlice
     * @see org.tensorflow.op.XlaOps.dynamicUpdateSlice
     */
    public fun <T : TType, U : TNumber> dynamicUpdateSlice(
        input: Operand<T>,
        update: Operand<T>,
        indices: Operand<U>
    ): DynamicUpdateSlice<T> = java.dynamicUpdateSlice<T, U>(
        input,
        update,
        indices
    )

    /**
     * An op which supports basic einsum op with 2 inputs and 1 output.
     *
     *  This op has better TPU performance since it doesn't have explicitly reshape and
     *  transpose operations as tf.einsum does.
     *
     * @param T data type for ` product()` output
     * @param a
     * @param b
     * @param equation
     * @return a new instance of Einsum
     * @see org.tensorflow.op.XlaOps.einsum
     */
    public fun <T : TType> einsum(
        a: Operand<T>,
        b: Operand<T>,
        equation: String
    ): Einsum<T> = java.einsum<T>(
        a,
        b,
        equation
    )

    /**
     * Wraps the XLA Gather operator documented at
     *
     *    https://www.tensorflow.org/xla/operation_semantics#gather
     *
     * @param T data type for ` output()` output
     * @param operand The array we're gathering from.
     * @param startIndices Array containing the starting indices of the slices we gather.
     * @param sliceSizes slice_sizes&#91;i] is the bounds for the slice on dimension i.
     * @param dimensionNumbers A serialized xla::GatherDimensionNumbers proto.
     * @param indicesAreSorted Boolean indicating if the indices are sorted.
     * @return a new instance of Gather
     * @see org.tensorflow.op.XlaOps.gather
     */
    public fun <T : TType, U : TNumber> gather(
        operand: Operand<T>,
        startIndices: Operand<U>,
        sliceSizes: Operand<U>,
        dimensionNumbers: String,
        indicesAreSorted: Boolean
    ): Gather<T> = java.gather<T, U>(
        operand,
        startIndices,
        sliceSizes,
        dimensionNumbers,
        indicesAreSorted
    )

    /**
     * Wraps the XLA Sort operator, documented at
     *
     *   https://www.tensorflow.org/performance/xla/operation_semantics#sort
     *  .
     *
     *  Sorts a tensor. Currently only sorts in ascending order are supported.
     *
     * @param T data type for ` sortedKeys()` output
     * @param U data type for ` sortedValues()` output
     * @param keys A `Tensor` of type K.
     * @param values A `Tensor` of type V.
     * @return a new instance of KeyValueSort
     * @see org.tensorflow.op.XlaOps.keyValueSort
     */
    public fun <T : TNumber, U : TType> keyValueSort(keys: Operand<T>, values: Operand<U>):
        KeyValueSort<T, U> = java.keyValueSort<T, U>(
            keys,
            values
        )

    /**
     * Wraps the XLA Pad operator, documented at
     *
     *   https://www.tensorflow.org/performance/xla/operation_semantics#pad
     *  .
     *
     * @param T data type for ` output()` output
     * @param input A `Tensor` of type T.
     * @param paddingValue A scalar `Tensor` of type T.
     * @param paddingLow the padding to apply at the start of each input dimensions
     * @param paddingHigh the padding to apply at the end of each input dimension.
     * @param paddingInterior the padding to apply between each input element.
     * @return a new instance of Pad
     * @see org.tensorflow.op.XlaOps.pad
     */
    public fun <T : TType, U : TNumber> pad(
        input: Operand<T>,
        paddingValue: Operand<T>,
        paddingLow: Operand<U>,
        paddingHigh: Operand<U>,
        paddingInterior: Operand<U>
    ): Pad<T> = java.pad<T, U>(
        input,
        paddingValue,
        paddingLow,
        paddingHigh,
        paddingInterior
    )

    /**
     * Receives the named tensor from another XLA computation. Wraps the XLA Recv
     *
     *  operator documented at
     *   https://www.tensorflow.org/performance/xla/operation_semantics#recv .
     *
     * @param T data type for ` tensor()` output
     * @param dtype The type of the tensor.
     * @param tensorName A string key that identifies the channel.
     * @param shape The shape of the tensor.
     * @return a new instance of Recv
     * @see org.tensorflow.op.XlaOps.recv
     */
    public fun <T : TType> recv(
        dtype: DataType<T>,
        tensorName: String,
        shape: Shape
    ): Recv<T> = java.recv<T>(
        dtype,
        tensorName,
        shape
    )

    /**
     * Replica ID.
     *
     * @return a new instance of ReplicaId
     * @see org.tensorflow.op.XlaOps.replicaId
     */
    public fun replicaId(): ReplicaId = java.replicaId()

    /**
     * Computes the eigen decomposition of a batch of self-adjoint matrices
     *
     *  (Note: Only real inputs are supported).
     *
     *  Computes the eigenvalues and eigenvectors of the innermost N-by-N matrices in
     *  tensor such that tensor&#91;...,:,:] * v&#91;..., :,i] = e&#91;..., i] * v&#91;...,:,i],
     * for
     *  i=0...N-1.
     *
     * @param T data type for ` w()` output
     * @param a the input tensor.
     * @param lower a boolean specifies whether the calculation is done with the lower
     *  triangular part or the upper triangular part.
     * @param maxIter maximum number of sweep update, i.e., the whole lower triangular
     *  part or upper triangular part based on parameter lower. Heuristically, it has
     *  been argued that approximately logN sweeps are needed in practice (Ref: Golub &
     *  van Loan "Matrix Computation").
     * @param epsilon the tolerance ratio.
     * @return a new instance of SelfAdjointEig
     * @see org.tensorflow.op.XlaOps.selfAdjointEig
     */
    public fun <T : TType> selfAdjointEig(
        a: Operand<T>,
        lower: Boolean,
        maxIter: Long,
        epsilon: Float
    ): SelfAdjointEig<T> = java.selfAdjointEig<T>(
        a,
        lower,
        maxIter,
        epsilon
    )

    /**
     * Sends the named tensor to another XLA computation. Wraps the XLA Send operator
     *
     *  documented at
     *   https://www.tensorflow.org/performance/xla/operation_semantics#send .
     *
     * @param tensor The tensor to send.
     * @param tensorName A string key that identifies the channel.
     * @return a new instance of Send
     * @see org.tensorflow.op.XlaOps.send
     */
    public fun <T : TType> send(tensor: Operand<T>, tensorName: String): Send = java.send<T>(
        tensor,
        tensorName
    )

    /**
     * An op which shards the input based on the given sharding attribute.
     *
     * @param T data type for ` output()` output
     * @param input
     * @return a new instance of Sharding
     * @see org.tensorflow.op.XlaOps.sharding
     */
    public fun <T : TType> sharding(input: Operand<T>): Sharding<T> = java.sharding<T>(
        input
    )

    /**
     * Wraps the XLA Sort operator, documented at
     *
     *   https://www.tensorflow.org/performance/xla/operation_semantics#sort
     *  .
     *
     *  Sorts a tensor. Currently only sorts in ascending order are supported.
     *
     * @param T data type for ` output()` output
     * @param input A `Tensor` of type T.
     * @return a new instance of Sort
     * @see org.tensorflow.op.XlaOps.sort
     */
    public fun <T : TType> sort(input: Operand<T>): Sort<T> = java.sort<T>(
        input
    )

    /**
     * Computes the eigen decomposition of a batch of self-adjoint matrices
     *
     *  (Note: Only real inputs are supported).
     *
     *  Computes the eigenvalues and eigenvectors of the innermost M-by-N matrices in
     *  tensor such that tensor&#91;...,:,:] = u&#91;..., :, :] * Diag(s&#91;..., :]) *
     * Transpose(v&#91;...,:,:]).
     *
     * @param T data type for ` s()` output
     * @param a the input tensor.
     * @param maxIter maximum number of sweep update, i.e., the whole lower triangular
     *  part or upper triangular part based on parameter lower. Heuristically, it has
     *  been argued that approximately log(min (M, N)) sweeps are needed in practice
     *  (Ref: Golub & van Loan "Matrix Computation").
     * @param epsilon the tolerance ratio.
     * @param precisionConfig a serialized xla::PrecisionConfig proto.
     * @return a new instance of Svd
     * @see org.tensorflow.op.XlaOps.svd
     */
    public fun <T : TType> svd(
        a: Operand<T>,
        maxIter: Long,
        epsilon: Float,
        precisionConfig: String
    ): Svd<T> = java.svd<T>(
        a,
        maxIter,
        epsilon,
        precisionConfig
    )
}
