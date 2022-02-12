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

import kotlin.Boolean
import kotlin.Float
import kotlin.Long
import kotlin.String
import kotlin.jvm.JvmName
import org.tensorflow.ConcreteFunction
import org.tensorflow.Operand
import org.tensorflow.ndarray.Shape
import org.tensorflow.op.Scope
import org.tensorflow.op.xla.AllReduce
import org.tensorflow.op.xla.BroadcastHelper
import org.tensorflow.op.xla.ClusterOutput
import org.tensorflow.op.xla.Conv
import org.tensorflow.op.xla.Dequantize
import org.tensorflow.op.xla.Dot
import org.tensorflow.op.xla.DynamicSlice
import org.tensorflow.op.xla.DynamicUpdateSlice
import org.tensorflow.op.xla.Einsum
import org.tensorflow.op.xla.Gather
import org.tensorflow.op.xla.If
import org.tensorflow.op.xla.KeyValueSort
import org.tensorflow.op.xla.Pad
import org.tensorflow.op.xla.Recv
import org.tensorflow.op.xla.Reduce
import org.tensorflow.op.xla.ReduceScatter
import org.tensorflow.op.xla.ReduceWindow
import org.tensorflow.op.xla.RemoveDynamicDimensionSize
import org.tensorflow.op.xla.ReplicaId
import org.tensorflow.op.xla.RngBitGenerator
import org.tensorflow.op.xla.Scatter
import org.tensorflow.op.xla.SelectAndScatter
import org.tensorflow.op.xla.SelfAdjointEig
import org.tensorflow.op.xla.Send
import org.tensorflow.op.xla.SetDynamicDimensionSize
import org.tensorflow.op.xla.Sharding
import org.tensorflow.op.xla.Sort
import org.tensorflow.op.xla.SpmdFullToShardShape
import org.tensorflow.op.xla.SpmdShardToFullShape
import org.tensorflow.op.xla.Svd
import org.tensorflow.op.xla.While
import org.tensorflow.op.xla.XlaHostCompute
import org.tensorflow.op.xla.XlaLaunch
import org.tensorflow.op.xla.XlaRecvFromHost
import org.tensorflow.op.xla.XlaSendToHost
import org.tensorflow.op.xla.XlaSetBound
import org.tensorflow.op.xla.XlaVariadicReduce
import org.tensorflow.op.xla.XlaVariadicSort
import org.tensorflow.types.TInt32
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
     * Wraps the XLA AllReduce operator
     *  documented at https://www.tensorflow.org/xla/operation_semantics#allreduce.
     *
     * @param <T> data type for `output` output
     * @param input Array or a non-empty tuple of arrays to reduce across replicas.
     * @param groupAssignment Groups between which the reductions are performed.
     * @param reduceOp Reduction computation.
     * @param <T> data type for `XlaAllReduce` output and operands
     * @return a new instance of AllReduce
     * @see org.tensorflow.op.XlaOps.allReduce
     */
    public fun <T : TNumber> allReduce(
        input: Operand<T>,
        groupAssignment: Operand<TInt32>,
        reduceOp: String
    ): AllReduce<T> = java.allReduce<T>(    
        input,
        groupAssignment,
        reduceOp
        )

    /**
     * Helper operator for performing XLA-style broadcasts
     *  Broadcasts `lhs` and `rhs` to the same rank, by adding size 1 dimensions to
     *  whichever of `lhs` and `rhs` has the lower rank, using XLA's broadcasting rules
     *  for binary operators.
     *
     * @param <T> data type for `lhs_output` output
     * @param lhs the LHS input tensor
     * @param rhs the RHS input tensor
     * @param broadcastDims an XLA-style broadcast dimension specification
     * @param <T> data type for `XlaBroadcastHelper` output and operands
     * @return a new instance of BroadcastHelper
     * @see org.tensorflow.op.XlaOps.broadcastHelper
     */
    public fun <T : TType> broadcastHelper(
        lhs: Operand<T>,
        rhs: Operand<T>,
        broadcastDims: Operand<out TNumber>
    ): BroadcastHelper<T> = java.broadcastHelper<T>(    
        lhs,
        rhs,
        broadcastDims
        )

    /**
     * Operator that connects the output of an XLA computation to other consumer graph nodes.
     *
     * @param <T> data type for `outputs` output
     * @param input The input value
     * @param <T> data type for `XlaClusterOutput` output and operands
     * @return a new instance of ClusterOutput
     * @see org.tensorflow.op.XlaOps.clusterOutput
     */
    public fun <T : TType> clusterOutput(input: Operand<T>): ClusterOutput<T> =
            java.clusterOutput<T>(    
        input
        )

    /**
     * Wraps the XLA ConvGeneralDilated operator, documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#conv_convolution
     *  .
     *
     * @param <W> data type for `output` output
     * @param lhs the input tensor
     * @param rhs the kernel tensor
     * @param windowStrides the inter-window strides
     * @param padding the padding to apply at the start and end of each input dimensions
     * @param lhsDilation dilation to apply between input elements
     * @param rhsDilation dilation to apply between kernel elements
     * @param featureGroupCount number of feature groups for grouped convolution.
     * @param dimensionNumbers a serialized xla::ConvolutionDimensionNumbers proto.
     * @param precisionConfig a serialized xla::PrecisionConfig proto.
     * @param preferredElementType The type of the tensor.
     * @param <W> data type for `XlaConvV2` output and operands
     * @param <V> data type for `XlaConvV2` output and operands
     * @return a new instance of Conv
     * @see org.tensorflow.op.XlaOps.conv
     */
    public fun <W : TType, V : TNumber> conv(
        lhs: Operand<out TType>,
        rhs: Operand<out TType>,
        windowStrides: Operand<V>,
        padding: Operand<V>,
        lhsDilation: Operand<V>,
        rhsDilation: Operand<V>,
        featureGroupCount: Operand<V>,
        dimensionNumbers: String,
        precisionConfig: String,
        preferredElementType: Class<W>
    ): Conv<W> = java.conv<W, V>(    
        lhs,
        rhs,
        windowStrides,
        padding,
        lhsDilation,
        rhsDilation,
        featureGroupCount,
        dimensionNumbers,
        precisionConfig,
        preferredElementType
        )

    /**
     * Takes the packed uint32 input and unpacks the input to uint8 to do
     *  Dequantization on device.
     *
     * @param input Input tensors whose types is uint32, shape is &#91;d0, ..., dn&#93;.
     * @param minRange The minimum scalar value possibly produced for the input.
     * @param maxRange The maximum scalar value possibly produced for the input.
     * @param mode String to determine the dequantize mode in {&quot;MIN_COMBINED&quot;,
     * &quot;MIN_FIRST&quot;, &quot;SCALED&quot;}.
     * @param transposeOutput Boolean to determine if output is transposed. transpose_output
     *  is faster when input is large and rank of input is higher than 1.
     * @return a new instance of Dequantize
     * @see org.tensorflow.op.XlaOps.dequantize
     */
    public fun dequantize(
        input: Operand<out TType>,
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
     *  https://www.tensorflow.org/performance/xla/operation_semantics#dotgeneral
     *  .
     *
     * @param <V> data type for `output` output
     * @param lhs the LHS tensor
     * @param rhs the RHS tensor
     * @param dimensionNumbers a serialized xla::DotDimensionNumbers proto.
     * @param precisionConfig a serialized xla::PrecisionConfig proto.
     * @param preferredElementType The type of the tensor.
     * @param <V> data type for `XlaDotV2` output and operands
     * @return a new instance of Dot
     * @see org.tensorflow.op.XlaOps.dot
     */
    public fun <V : TType> dot(
        lhs: Operand<out TType>,
        rhs: Operand<out TType>,
        dimensionNumbers: String,
        precisionConfig: String,
        preferredElementType: Class<V>
    ): Dot<V> = java.dot<V>(    
        lhs,
        rhs,
        dimensionNumbers,
        precisionConfig,
        preferredElementType
        )

    /**
     * Wraps the XLA DynamicSlice operator, documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#dynamicslice
     *  .
     *  
     * DynamicSlice extracts a sub-array from the input array at dynamic
     *  start_indices. The size of the slice in each dimension is passed in
     *  size_indices, which specify the end point of exclusive slice intervals in each
     *  dimension -- [start, start + size). The shape of start_indices must have rank 1,
     *  with dimension size equal to the rank of operand.
     *
     * @param <T> data type for `output` output
     * @param input A `Tensor` of type T.
     * @param startIndices List of N integers containing the slice size for each
     *  dimension. Each value must be strictly greater than zero, and start + size
     *  must be less than or equal to the size of the dimension to avoid
     *  implementation defined behavior.
     * @param sizeIndices The sizeIndices value
     * @param <T> data type for `XlaDynamicSlice` output and operands
     * @param <U> data type for `XlaDynamicSlice` output and operands
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
     *  https://www.tensorflow.org/performance/xla/operation_semantics#dynamicupdateslice
     *  .
     *  
     * XlaDynamicUpdateSlice generates a result which is the value of the `input`
     *  operand, with a slice update overwritten at `indices`. The shape of `update`
     *  determines the shape of the sub-array of the result which is updated. The shape
     *  of indices must be rank == 1, with dimension size equal to the rank of `input`.
     *  
     * Handling of out-of-bounds slice indices is implementation-defined.
     *
     * @param <T> data type for `output` output
     * @param input A `Tensor` of type T.
     * @param update A `Tensor` of type T. Same rank as `input`.
     * @param indices A vector of indices into `input`. Must have length equal to the rank of
     *  `input`.
     * @param <T> data type for `XlaDynamicUpdateSlice` output and operands
     * @return a new instance of DynamicUpdateSlice
     * @see org.tensorflow.op.XlaOps.dynamicUpdateSlice
     */
    public fun <T : TType> dynamicUpdateSlice(
        input: Operand<T>,
        update: Operand<T>,
        indices: Operand<out TNumber>
    ): DynamicUpdateSlice<T> = java.dynamicUpdateSlice<T>(    
        input,
        update,
        indices
        )

    /**
     * An op which supports basic einsum op with 2 inputs and 1 output.
     *  This op has better TPU performance since it doesn't have explicitly reshape and
     *  transpose operations as tf.einsum does.
     *
     * @param <T> data type for `product` output
     * @param a The a value
     * @param b The b value
     * @param equation The value of the equation attribute
     * @param <T> data type for `XlaEinsum` output and operands
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
     *  https://www.tensorflow.org/xla/operation_semantics#gather
     *
     * @param <T> data type for `output` output
     * @param operand The array we're gathering from.
     * @param startIndices Array containing the starting indices of the slices we gather.
     * @param sliceSizes slice_sizes[i] is the bounds for the slice on dimension i.
     * @param dimensionNumbers A serialized xla::GatherDimensionNumbers proto.
     * @param indicesAreSorted Boolean indicating if the indices are sorted.
     * @param <T> data type for `XlaGather` output and operands
     * @param <U> data type for `XlaGather` output and operands
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
     * output = cond ? then_branch(inputs) : else_branch(inputs).
     *
     * @param cond A boolean scalar.
     * @param inputs A list of input tensors.
     * @param thenBranch A function takes 'inputs' and returns a list of tensors,
     *  whose types are the same as what else_branch returns.
     * @param elseBranch A function takes 'inputs' and returns a list of tensors.
     *  whose types are the same as what then_branch returns.
     * @param Tout The value of the Tout attribute
     * @return a new instance of If
     * @see org.tensorflow.op.XlaOps.ifOp
     */
    public fun ifOp(
        cond: Operand<out TType>,
        inputs: Iterable<Operand<*>>,
        thenBranch: ConcreteFunction,
        elseBranch: ConcreteFunction,
        Tout: List<Class<out TType>>
    ): If = java.ifOp(    
        cond,
        inputs,
        thenBranch,
        elseBranch,
        Tout
        )

    /**
     * Wraps the XLA Sort operator, documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#sort
     *  .
     *  
     * Sorts a tensor. Currently only sorts in ascending order are supported.
     *
     * @param <T> data type for `sorted_keys` output
     * @param <U> data type for `sorted_values` output
     * @param keys A `Tensor` of type K.
     * @param values A `Tensor` of type V.
     * @param <T> data type for `XlaKeyValueSort` output and operands
     * @param <U> data type for `XlaKeyValueSort` output and operands
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
     *  https://www.tensorflow.org/performance/xla/operation_semantics#pad
     *  .
     *
     * @param <T> data type for `output` output
     * @param input A `Tensor` of type T.
     * @param paddingValue A scalar `Tensor` of type T.
     * @param paddingLow the padding to apply at the start of each input dimensions. Must
     *  be a compile-time constant 1D tensor of length equal to rank of input.
     * @param paddingHigh the padding to apply at the end of each input dimension. Must
     *  be a compile-time constant 1D tensor of length equal to rank of input.
     * @param paddingInterior the padding to apply between each input element. Must
     *  be a compile-time constant 1D tensor of length equal to rank of input,
     *  containing only non-negative values.
     * @param <T> data type for `XlaPad` output and operands
     * @param <U> data type for `XlaPad` output and operands
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
     *  operator documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#recv .
     *
     * @param <T> data type for `tensor` output
     * @param dtype The type of the tensor.
     * @param tensorName A string key that identifies the channel.
     * @param shape The shape of the tensor.
     * @param <T> data type for `XlaRecv` output and operands
     * @return a new instance of Recv
     * @see org.tensorflow.op.XlaOps.recv
     */
    public fun <T : TType> recv(
        dtype: Class<T>,
        tensorName: String,
        shape: Shape
    ): Recv<T> = java.recv<T>(    
        dtype,
        tensorName,
        shape
        )

    /**
     * Wraps the XLA Reduce operator, documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#reduce .
     *
     * @param <T> data type for `output` output
     * @param input the input tensor
     * @param initValue a scalar representing the initial value for the reduction
     * @param dimensionsToReduce dimension numbers over which to reduce
     * @param reducer a reducer function to apply
     * @param <T> data type for `XlaReduce` output and operands
     * @return a new instance of Reduce
     * @see org.tensorflow.op.XlaOps.reduce
     */
    public fun <T : TType> reduce(
        input: Operand<T>,
        initValue: Operand<T>,
        dimensionsToReduce: List<Long>,
        reducer: ConcreteFunction
    ): Reduce<T> = java.reduce<T>(    
        input,
        initValue,
        dimensionsToReduce,
        reducer
        )

    /**
     * Wraps the XLA ReduceScatter operator
     *  documented at https://www.tensorflow.org/xla/operation_semantics#reducescatter.
     *
     * @param <T> data type for `output` output
     * @param input Array or a non-empty tuple of arrays to reduce across replicas.
     * @param groupAssignment Groups between which the reductions are performed.
     * @param scatterDimension Dimension to scatter.
     * @param reduceOp Reduction computation.
     * @param <T> data type for `XlaReduceScatter` output and operands
     * @return a new instance of ReduceScatter
     * @see org.tensorflow.op.XlaOps.reduceScatter
     */
    public fun <T : TNumber> reduceScatter(
        input: Operand<T>,
        groupAssignment: Operand<TInt32>,
        scatterDimension: Operand<TInt32>,
        reduceOp: String
    ): ReduceScatter<T> = java.reduceScatter<T>(    
        input,
        groupAssignment,
        scatterDimension,
        reduceOp
        )

    /**
     * Wraps the XLA ReduceWindow operator, documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#reducewindow .
     *
     * @param <T> data type for `output` output
     * @param input the input tensor
     * @param initValue a scalar representing the initial value for the reduction
     * @param windowDimensions the shape of the window
     * @param windowStrides the inter-window strides
     * @param baseDilations The baseDilations value
     * @param windowDilations The windowDilations value
     * @param padding the padding to apply at the start and end of each input dimensions
     * @param computation a reducer function to apply
     * @param <T> data type for `XlaReduceWindow` output and operands
     * @param <U> data type for `XlaReduceWindow` output and operands
     * @return a new instance of ReduceWindow
     * @see org.tensorflow.op.XlaOps.reduceWindow
     */
    public fun <T : TType, U : TNumber> reduceWindow(
        input: Operand<T>,
        initValue: Operand<T>,
        windowDimensions: Operand<U>,
        windowStrides: Operand<U>,
        baseDilations: Operand<U>,
        windowDilations: Operand<U>,
        padding: Operand<U>,
        computation: ConcreteFunction
    ): ReduceWindow<T> = java.reduceWindow<T, U>(    
        input,
        initValue,
        windowDimensions,
        windowStrides,
        baseDilations,
        windowDilations,
        padding,
        computation
        )

    /**
     * Inverse of XlaSetDynamicDimensionSize.
     *  Make an xla bounded dynamic dimension into a static dimension. The bound of the
     *  size of dimension `dim_index` becomes the static dimension size.
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param dimIndex The dimIndex value
     * @param <T> data type for `XlaRemoveDynamicDimensionSize` output and operands
     * @return a new instance of RemoveDynamicDimensionSize
     * @see org.tensorflow.op.XlaOps.removeDynamicDimensionSize
     */
    public fun <T : TType> removeDynamicDimensionSize(input: Operand<T>, dimIndex: Operand<TInt32>):
            RemoveDynamicDimensionSize<T> = java.removeDynamicDimensionSize<T>(    
        input,
        dimIndex
        )

    /**
     * Replica ID.
     *
     * @return a new instance of ReplicaId
     * @see org.tensorflow.op.XlaOps.replicaId
     */
    public fun replicaId(): ReplicaId = java.replicaId(    
        
        )

    /**
     * Stateless PRNG bit generator.
     *  Wraps the XLA RngBitGenerator operator, documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#rngbitgenerator.
     *
     * @param <U> data type for `output` output
     * @param algorithm The PRNG algorithm to use, one of
     *  tf.random.Algorithm.{PHILOX, THREEFRY, AUTO_SELECT}.
     * @param initialState Initial state for the PRNG algorithm. For THREEFRY, it should be
     *  a u64[2] and for PHILOX a u64[3].
     * @param shape The output shape of the generated data.
     * @param dtype The type of the tensor.
     * @param <U> data type for `XlaRngBitGenerator` output and operands
     * @return a new instance of RngBitGenerator
     * @see org.tensorflow.op.XlaOps.rngBitGenerator
     */
    public fun <U : TNumber> rngBitGenerator(
        algorithm: Operand<TInt32>,
        initialState: Operand<out TType>,
        shape: Operand<out TNumber>,
        dtype: Class<U>
    ): RngBitGenerator<U> = java.rngBitGenerator<U>(    
        algorithm,
        initialState,
        shape,
        dtype
        )

    /**
     * Wraps the XLA Scatter operator documented at
     *  https://www.tensorflow.org/xla/operation_semantics#scatter.
     *
     * @param <T> data type for `output` output
     * @param operand Array to be scattered into.
     * @param scatterIndices Array containing the starting indices of the slices that must
     *  be scattered to.
     * @param updates Array containing the values that must be used for scattering.
     * @param updateComputation Computation to be used for combining the existing values in
     *  the input array and the updates during scatter.
     * @param dimensionNumbers A serialized xla::ScatterDimensionNumbers proto.
     * @param indicesAreSorted Boolean indicating if the indices are sorted.
     * @param <T> data type for `XlaScatter` output and operands
     * @return a new instance of Scatter
     * @see org.tensorflow.op.XlaOps.scatter
     */
    public fun <T : TType> scatter(
        operand: Operand<T>,
        scatterIndices: Operand<out TNumber>,
        updates: Operand<T>,
        updateComputation: ConcreteFunction,
        dimensionNumbers: String,
        indicesAreSorted: Boolean
    ): Scatter<T> = java.scatter<T>(    
        operand,
        scatterIndices,
        updates,
        updateComputation,
        dimensionNumbers,
        indicesAreSorted
        )

    /**
     * Wraps the XLA SelectAndScatter operator, documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#selectandscatter
     *  .
     *
     * @param <T> data type for `output` output
     * @param operand the input tensor
     * @param windowDimensions the shape of the window
     * @param windowStrides the inter-window strides
     * @param padding the padding to apply at the start and end of each input dimensions
     * @param source a tensor of values to scatter
     * @param initValue a scalar representing the initial value for the output tensor
     * @param select a selection function to apply
     * @param scatter a scatter function to apply
     * @param <T> data type for `XlaSelectAndScatter` output and operands
     * @param <U> data type for `XlaSelectAndScatter` output and operands
     * @return a new instance of SelectAndScatter
     * @see org.tensorflow.op.XlaOps.selectAndScatter
     */
    public fun <T : TType, U : TNumber> selectAndScatter(
        operand: Operand<T>,
        windowDimensions: Operand<U>,
        windowStrides: Operand<U>,
        padding: Operand<U>,
        source: Operand<T>,
        initValue: Operand<T>,
        select: ConcreteFunction,
        scatter: ConcreteFunction
    ): SelectAndScatter<T> = java.selectAndScatter<T, U>(    
        operand,
        windowDimensions,
        windowStrides,
        padding,
        source,
        initValue,
        select,
        scatter
        )

    /**
     * Computes the eigen decomposition of a batch of self-adjoint matrices
     *  (Note: Only real inputs are supported).
     *  
     * Computes the eigenvalues and eigenvectors of the innermost N-by-N matrices in
     *  tensor such that tensor&#91;...,:,:&#93; * v&#91;..., :,i&#93; = e&#91;..., i&#93; *
     * v&#91;...,:,i&#93;, for
     *  i=0...N-1.
     *
     * @param <T> data type for `w` output
     * @param a the input tensor.
     * @param lower a boolean specifies whether the calculation is done with the lower
     *  triangular part or the upper triangular part.
     * @param maxIter maximum number of sweep update, i.e., the whole lower triangular
     *  part or upper triangular part based on parameter lower. Heuristically, it has
     *  been argued that approximately logN sweeps are needed in practice (Ref: Golub &amp;
     *  van Loan &quot;Matrix Computation&quot;).
     * @param epsilon the tolerance ratio.
     * @param <T> data type for `XlaSelfAdjointEig` output and operands
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
     *  documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#send .
     *
     * @param tensor The tensor to send.
     * @param tensorName A string key that identifies the channel.
     * @return a new instance of Send
     * @see org.tensorflow.op.XlaOps.send
     */
    public fun send(tensor: Operand<out TType>, tensorName: String): Send = java.send(    
        tensor,
        tensorName
        )

    /**
     * Make a static dimension into a xla bounded dynamic dimension.
     *  ```
     * The current static dimension size will become the bound and the second
     *      operand becomes the dynamic size of the dimension.
     *  
     * ```
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param dimIndex The dimIndex value
     * @param sizeOutput The sizeOutput value
     * @param <T> data type for `XlaSetDynamicDimensionSize` output and operands
     * @return a new instance of SetDynamicDimensionSize
     * @see org.tensorflow.op.XlaOps.setDynamicDimensionSize
     */
    public fun <T : TType> setDynamicDimensionSize(
        input: Operand<T>,
        dimIndex: Operand<TInt32>,
        sizeOutput: Operand<TInt32>
    ): SetDynamicDimensionSize<T> = java.setDynamicDimensionSize<T>(    
        input,
        dimIndex,
        sizeOutput
        )

    /**
     * An op which shards the input based on the given sharding attribute. It can
     *  selectively annotate a subset of tensor dimensions by skipping unspecified_dims,
     *  and the sharding annotation should be replicated in those dims.
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param options carries optional attribute values
     * @param <T> data type for `XlaSharding` output and operands
     * @return a new instance of Sharding
     * @see org.tensorflow.op.XlaOps.sharding
     * @param sharding Sets the sharding option.
     *
     * @param sharding the sharding option
     * @return this Options instance.
     * @param unspecifiedDims Sets the unspecifiedDims option.
     *
     * @param unspecifiedDims the unspecifiedDims option
     * @return this Options instance.
     */
    public fun <T : TType> sharding(
        input: Operand<T>,
        sharding: String? = null,
        unspecifiedDims: List<Long>? = null
    ): Sharding<T> = java.sharding<T>(    
        input,
        *listOfNotNull(
            sharding?.let{ org.tensorflow.op.xla.Sharding.sharding(it) },
            unspecifiedDims?.let{ org.tensorflow.op.xla.Sharding.unspecifiedDims(it) }
        ).toTypedArray()
        )

    /**
     * Wraps the XLA Sort operator, documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#sort
     *  .
     *  
     * Sorts a tensor. Currently only sorts in ascending order are supported.
     *
     * @param <T> data type for `output` output
     * @param input A `Tensor` of type T.
     * @param <T> data type for `XlaSort` output and operands
     * @return a new instance of Sort
     * @see org.tensorflow.op.XlaOps.sort
     */
    public fun <T : TType> sort(input: Operand<T>): Sort<T> = java.sort<T>(    
        input
        )

    /**
     * An op used by XLA SPMD partitioner to switch from automatic partitioning to
     *  manual partitioning. It annotates the input (full-shape, to be automatically
     *  partitioned) with the same sharding used by manual partitioning, and outputs a
     *  shard-shaped tensor to be consumed by later manually-partitioned ops. If the
     *  shape is not evenly partitionable, the padding region will be masked with 0s.
     *  The conversion can happen partially in subgroups, by specifying the dim
     *  attribute, where only that dim will be converted.
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param manualSharding The value of the manualSharding attribute
     * @param options carries optional attribute values
     * @param <T> data type for `XlaSpmdFullToShardShape` output and operands
     * @return a new instance of SpmdFullToShardShape
     * @see org.tensorflow.op.XlaOps.spmdFullToShardShape
     * @param dim Sets the dim option.
     *
     * @param dim the dim option
     * @return this Options instance.
     * @param unspecifiedDims Sets the unspecifiedDims option.
     *
     * @param unspecifiedDims the unspecifiedDims option
     * @return this Options instance.
     */
    public fun <T : TType> spmdFullToShardShape(
        input: Operand<T>,
        manualSharding: String,
        dim: Long? = null,
        unspecifiedDims: List<Long>? = null
    ): SpmdFullToShardShape<T> = java.spmdFullToShardShape<T>(    
        input,
        manualSharding,
        *listOfNotNull(
            dim?.let{ org.tensorflow.op.xla.SpmdFullToShardShape.dim(it) },
            unspecifiedDims?.let{ org.tensorflow.op.xla.SpmdFullToShardShape.unspecifiedDims(it) }
        ).toTypedArray()
        )

    /**
     * An op used by XLA SPMD partitioner to switch from manual partitioning to
     *  automatic partitioning. It converts the shard-shaped, manually partitioned input
     *  into full-shaped tensor to be partitioned automatically with the same sharding
     *  used by manual partitioning. The conversion can happen partially in subgroups,
     *  by specifying the dim attribute, where only that dim will be converted.
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param manualSharding The value of the manualSharding attribute
     * @param fullShape The value of the fullShape attribute
     * @param options carries optional attribute values
     * @param <T> data type for `XlaSpmdShardToFullShape` output and operands
     * @return a new instance of SpmdShardToFullShape
     * @see org.tensorflow.op.XlaOps.spmdShardToFullShape
     * @param dim Sets the dim option.
     *
     * @param dim the dim option
     * @return this Options instance.
     * @param unspecifiedDims Sets the unspecifiedDims option.
     *
     * @param unspecifiedDims the unspecifiedDims option
     * @return this Options instance.
     */
    public fun <T : TType> spmdShardToFullShape(
        input: Operand<T>,
        manualSharding: String,
        fullShape: Shape,
        dim: Long? = null,
        unspecifiedDims: List<Long>? = null
    ): SpmdShardToFullShape<T> = java.spmdShardToFullShape<T>(    
        input,
        manualSharding,
        fullShape,
        *listOfNotNull(
            dim?.let{ org.tensorflow.op.xla.SpmdShardToFullShape.dim(it) },
            unspecifiedDims?.let{ org.tensorflow.op.xla.SpmdShardToFullShape.unspecifiedDims(it) }
        ).toTypedArray()
        )

    /**
     * Computes the eigen decomposition of a batch of self-adjoint matrices
     *  (Note: Only real inputs are supported).
     *  
     * Computes the eigenvalues and eigenvectors of the innermost M-by-N matrices in
     *  tensor such that tensor&#91;...,:,:&#93; = u&#91;..., :, :&#93; * Diag(s&#91;..., :&#93;) *
     * Transpose(v&#91;...,:,:&#93;).
     *
     * @param <T> data type for `s` output
     * @param a the input tensor.
     * @param maxIter maximum number of sweep update, i.e., the whole lower triangular
     *  part or upper triangular part based on parameter lower. Heuristically, it has
     *  been argued that approximately log(min (M, N)) sweeps are needed in practice
     *  (Ref: Golub &amp; van Loan &quot;Matrix Computation&quot;).
     * @param epsilon the tolerance ratio.
     * @param precisionConfig a serialized xla::PrecisionConfig proto.
     * @param <T> data type for `XlaSvd` output and operands
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

    /**
     * output = input; While (Cond(output)) { output = Body(output) }
     *
     * @param input A list of input tensors whose types are T.
     * @param cond A function takes 'input' and returns a tensor.  If the tensor is
     *  a scalar of non-boolean, the scalar is converted to a boolean
     *  according to the following rule: if the scalar is a numerical
     *  value, non-zero means True and zero means False; if the scalar is
     *  a string, non-empty means True and empty means False. If the
     *  tensor is not a scalar, non-emptiness means True and False
     *  otherwise.
     * @param body A function that takes a list of tensors and returns another
     *  list of tensors. Both lists have the same types as specified by T.
     * @return a new instance of While
     * @see org.tensorflow.op.XlaOps.whileOp
     */
    public fun whileOp(
        input: Iterable<Operand<*>>,
        cond: ConcreteFunction,
        body: ConcreteFunction
    ): While = java.whileOp(    
        input,
        cond,
        body
        )

    /**
     * A pseudo-op to represent host-side computation in an XLA program.
     *
     * @param inputs A list of tensors that will be sent to the host.
     * @param Toutputs The element types of each element in `outputs`.
     * @param ancestors A list of names of HostCompute computations that must be
     *  sequenced before this computation.
     * @param shapes If shape_inference_graph is empty, a list of the shapes of `outputs`.
     * @param shapeInferenceGraph If non-empty, a serialized GraphDef representing a graph
     *  that must be analyzed at compile time to determine the shapes of the outputs.
     * @param key A unique identifier for this region used to match up host transfers.
     * @param options carries optional attribute values
     * @return a new instance of XlaHostCompute
     * @see org.tensorflow.op.XlaOps.xlaHostCompute
     * @param sendKey Sets the sendKey option.
     *
     * @param sendKey the sendKey option
     * @return this Options instance.
     * @param recvKey Sets the recvKey option.
     *
     * @param recvKey the recvKey option
     * @return this Options instance.
     * @param costEstimateNs Sets the costEstimateNs option.
     *
     * @param costEstimateNs Estimated duration of the host computation in nanoseconds.
     * @return this Options instance.
     * @param tpuCore Sets the tpuCore option.
     *
     * @param tpuCore Default core to use for host to device transfers.
     * @return this Options instance.
     */
    public fun xlaHostCompute(
        inputs: Iterable<Operand<*>>,
        Toutputs: List<Class<out TType>>,
        ancestors: List<String>,
        shapes: List<Shape>,
        shapeInferenceGraph: ConcreteFunction,
        key: String,
        sendKey: String? = null,
        recvKey: String? = null,
        costEstimateNs: Long? = null,
        tpuCore: Long? = null
    ): XlaHostCompute = java.xlaHostCompute(    
        inputs,
        Toutputs,
        ancestors,
        shapes,
        shapeInferenceGraph,
        key,
        *listOfNotNull(
            sendKey?.let{ org.tensorflow.op.xla.XlaHostCompute.sendKey(it) },
            recvKey?.let{ org.tensorflow.op.xla.XlaHostCompute.recvKey(it) },
            costEstimateNs?.let{ org.tensorflow.op.xla.XlaHostCompute.costEstimateNs(it) },
            tpuCore?.let{ org.tensorflow.op.xla.XlaHostCompute.tpuCore(it) }
        ).toTypedArray()
        )

    /**
     * XLA Launch Op. For use by the XLA JIT only.
     *
     * @param constants The constants value
     * @param args The args value
     * @param resources The resources value
     * @param Tresults The value of the Tresults attribute
     * @param function The value of the function attribute
     * @return a new instance of XlaLaunch
     * @see org.tensorflow.op.XlaOps.xlaLaunch
     */
    public fun xlaLaunch(
        constants: Iterable<Operand<*>>,
        args: Iterable<Operand<*>>,
        resources: Iterable<Operand<out TType>>,
        Tresults: List<Class<out TType>>,
        function: ConcreteFunction
    ): XlaLaunch = java.xlaLaunch(    
        constants,
        args,
        resources,
        Tresults,
        function
        )

    /**
     * An op to receive a tensor from the host.
     *  output: the tensor that will be received from the host.
     *  Toutput: element type for output.
     *  shape: shape for output.
     *  key: A unique identifier for this region used to match up host transfers.
     *
     * @param <T> data type for `output` output
     * @param Toutput The value of the Toutput attribute
     * @param shape The value of the shape attribute
     * @param key The value of the key attribute
     * @param <T> data type for `XlaRecvFromHost` output and operands
     * @return a new instance of XlaRecvFromHost
     * @see org.tensorflow.op.XlaOps.xlaRecvFromHost
     */
    public fun <T : TType> xlaRecvFromHost(
        Toutput: Class<T>,
        shape: Shape,
        key: String
    ): XlaRecvFromHost<T> = java.xlaRecvFromHost<T>(    
        Toutput,
        shape,
        key
        )

    /**
     * An op to send a tensor to the host.
     *  input: the tensor that will be sent to the host.
     *  Tinput: element type for input.
     *  key: A unique identifier for this region used to match up host transfers.
     *
     * @param input The input value
     * @param key The value of the key attribute
     * @return a new instance of XlaSendToHost
     * @see org.tensorflow.op.XlaOps.xlaSendToHost
     */
    public fun xlaSendToHost(input: Operand<out TType>, key: String): XlaSendToHost =
            java.xlaSendToHost(    
        input,
        key
        )

    /**
     * Set a bound for the given input value as a hint to Xla compiler,
     *  ```
     * returns the same value.
     *  
     * ```
     *
     * @param input The input value
     * @param bound The bound value
     * @return a new instance of XlaSetBound
     * @see org.tensorflow.op.XlaOps.xlaSetBound
     */
    public fun xlaSetBound(input: Operand<TInt32>, bound: Operand<TInt32>): XlaSetBound =
            java.xlaSetBound(    
        input,
        bound
        )

    /**
     * Wraps the variadic XLA Reduce operator.
     *  Semantics are documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#variadic_reduce.
     *  
     * This is an expanded version of XlaVariadicReduce, with support for
     *  operands of different dtypes, and improved shape inference.
     *
     * @param inputs the input tensor(s)
     * @param initValues scalar initial value(s) for the reduction
     * @param dimensionsToReduce dimension numbers over which to reduce
     * @param reducer a reducer function to apply
     * @return a new instance of XlaVariadicReduce
     * @see org.tensorflow.op.XlaOps.xlaVariadicReduce
     */
    public fun xlaVariadicReduce(
        inputs: Iterable<Operand<*>>,
        initValues: Iterable<Operand<*>>,
        dimensionsToReduce: List<Long>,
        reducer: ConcreteFunction
    ): XlaVariadicReduce = java.xlaVariadicReduce(    
        inputs,
        initValues,
        dimensionsToReduce,
        reducer
        )

    /**
     * Wraps the XLA Sort operator, documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#sort
     *  .
     *  
     * Sorts one or more tensors, with support for custom comparator, dimension, and
     *  is_stable attributes.
     *
     * @param inputs A list of `Tensor` of identical shape but possibly different types.
     * @param dimension The dimension along which to sort. Must be a compile-time constant.
     * @param comparator A comparator function to apply to 2*N scalars and returning a
     *  boolean. N is the number of sort inputs. If you want to sort in ascending
     *  order then the comparator should perform a less-than comparison.
     * @param isStable Whether to use stable sort.
     * @return a new instance of XlaVariadicSort
     * @see org.tensorflow.op.XlaOps.xlaVariadicSort
     */
    public fun xlaVariadicSort(
        inputs: Iterable<Operand<*>>,
        dimension: Operand<TInt32>,
        comparator: ConcreteFunction,
        isStable: Boolean
    ): XlaVariadicSort = java.xlaVariadicSort(    
        inputs,
        dimension,
        comparator,
        isStable
        )

    /**
     * Wraps the XLA ConvGeneralDilated operator, documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#conv_convolution
     *  .
     *
     * @param <W> data type for `output` output
     * @param lhs the input tensor
     * @param rhs the kernel tensor
     * @param windowStrides the inter-window strides
     * @param padding the padding to apply at the start and end of each input dimensions
     * @param lhsDilation dilation to apply between input elements
     * @param rhsDilation dilation to apply between kernel elements
     * @param featureGroupCount number of feature groups for grouped convolution.
     * @param dimensionNumbers a serialized xla::ConvolutionDimensionNumbers proto.
     * @param precisionConfig a serialized xla::PrecisionConfig proto.
     * @param preferredElementType The type of the tensor.
     * @param <W> data type for `XlaConvV2` output and operands
     * @param <V> data type for `XlaConvV2` output and operands
     * @return a new instance of Conv
     * @see org.tensorflow.op.XlaOps.conv
     */
    @JvmName("convReified")
    public inline fun <reified W : TType, V : TNumber> conv(
        lhs: Operand<out TType>,
        rhs: Operand<out TType>,
        windowStrides: Operand<V>,
        padding: Operand<V>,
        lhsDilation: Operand<V>,
        rhsDilation: Operand<V>,
        featureGroupCount: Operand<V>,
        dimensionNumbers: String,
        precisionConfig: String
    ): Conv<W> = conv<W, V>(lhs, rhs, windowStrides, padding, lhsDilation, rhsDilation,
            featureGroupCount, dimensionNumbers, precisionConfig, W::class.java)

    /**
     * Wraps the XLA DotGeneral operator, documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#dotgeneral
     *  .
     *
     * @param <V> data type for `output` output
     * @param lhs the LHS tensor
     * @param rhs the RHS tensor
     * @param dimensionNumbers a serialized xla::DotDimensionNumbers proto.
     * @param precisionConfig a serialized xla::PrecisionConfig proto.
     * @param preferredElementType The type of the tensor.
     * @param <V> data type for `XlaDotV2` output and operands
     * @return a new instance of Dot
     * @see org.tensorflow.op.XlaOps.dot
     */
    @JvmName("dotReified")
    public inline fun <reified V : TType> dot(
        lhs: Operand<out TType>,
        rhs: Operand<out TType>,
        dimensionNumbers: String,
        precisionConfig: String
    ): Dot<V> = dot<V>(lhs, rhs, dimensionNumbers, precisionConfig, V::class.java)

    /**
     * Receives the named tensor from another XLA computation. Wraps the XLA Recv
     *  operator documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#recv .
     *
     * @param <T> data type for `tensor` output
     * @param dtype The type of the tensor.
     * @param tensorName A string key that identifies the channel.
     * @param shape The shape of the tensor.
     * @param <T> data type for `XlaRecv` output and operands
     * @return a new instance of Recv
     * @see org.tensorflow.op.XlaOps.recv
     */
    @JvmName("recvReified")
    public inline fun <reified T : TType> recv(tensorName: String, shape: Shape): Recv<T> =
            recv<T>(T::class.java, tensorName, shape)

    /**
     * Stateless PRNG bit generator.
     *  Wraps the XLA RngBitGenerator operator, documented at
     *  https://www.tensorflow.org/performance/xla/operation_semantics#rngbitgenerator.
     *
     * @param <U> data type for `output` output
     * @param algorithm The PRNG algorithm to use, one of
     *  tf.random.Algorithm.{PHILOX, THREEFRY, AUTO_SELECT}.
     * @param initialState Initial state for the PRNG algorithm. For THREEFRY, it should be
     *  a u64[2] and for PHILOX a u64[3].
     * @param shape The output shape of the generated data.
     * @param dtype The type of the tensor.
     * @param <U> data type for `XlaRngBitGenerator` output and operands
     * @return a new instance of RngBitGenerator
     * @see org.tensorflow.op.XlaOps.rngBitGenerator
     */
    @JvmName("rngBitGeneratorReified")
    public inline fun <reified U : TNumber> rngBitGenerator(
        algorithm: Operand<TInt32>,
        initialState: Operand<out TType>,
        shape: Operand<out TNumber>
    ): RngBitGenerator<U> = rngBitGenerator<U>(algorithm, initialState, shape, U::class.java)

    /**
     * An op to receive a tensor from the host.
     *  output: the tensor that will be received from the host.
     *  Toutput: element type for output.
     *  shape: shape for output.
     *  key: A unique identifier for this region used to match up host transfers.
     *
     * @param <T> data type for `output` output
     * @param Toutput The value of the Toutput attribute
     * @param shape The value of the shape attribute
     * @param key The value of the key attribute
     * @param <T> data type for `XlaRecvFromHost` output and operands
     * @return a new instance of XlaRecvFromHost
     * @see org.tensorflow.op.XlaOps.xlaRecvFromHost
     */
    @JvmName("xlaRecvFromHostReified")
    public inline fun <reified T : TType> xlaRecvFromHost(shape: Shape, key: String):
            XlaRecvFromHost<T> = xlaRecvFromHost<T>(T::class.java, shape, key)
}
