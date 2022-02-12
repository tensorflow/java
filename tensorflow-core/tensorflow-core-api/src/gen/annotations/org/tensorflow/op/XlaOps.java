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

import java.util.List;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.xla.AllReduce;
import org.tensorflow.op.xla.BroadcastHelper;
import org.tensorflow.op.xla.ClusterOutput;
import org.tensorflow.op.xla.Conv;
import org.tensorflow.op.xla.Dequantize;
import org.tensorflow.op.xla.Dot;
import org.tensorflow.op.xla.DynamicSlice;
import org.tensorflow.op.xla.DynamicUpdateSlice;
import org.tensorflow.op.xla.Einsum;
import org.tensorflow.op.xla.Gather;
import org.tensorflow.op.xla.If;
import org.tensorflow.op.xla.KeyValueSort;
import org.tensorflow.op.xla.Pad;
import org.tensorflow.op.xla.Recv;
import org.tensorflow.op.xla.Reduce;
import org.tensorflow.op.xla.ReduceScatter;
import org.tensorflow.op.xla.ReduceWindow;
import org.tensorflow.op.xla.RemoveDynamicDimensionSize;
import org.tensorflow.op.xla.ReplicaId;
import org.tensorflow.op.xla.RngBitGenerator;
import org.tensorflow.op.xla.Scatter;
import org.tensorflow.op.xla.SelectAndScatter;
import org.tensorflow.op.xla.SelfAdjointEig;
import org.tensorflow.op.xla.Send;
import org.tensorflow.op.xla.SetDynamicDimensionSize;
import org.tensorflow.op.xla.Sharding;
import org.tensorflow.op.xla.Sort;
import org.tensorflow.op.xla.SpmdFullToShardShape;
import org.tensorflow.op.xla.SpmdShardToFullShape;
import org.tensorflow.op.xla.Svd;
import org.tensorflow.op.xla.While;
import org.tensorflow.op.xla.XlaHostCompute;
import org.tensorflow.op.xla.XlaLaunch;
import org.tensorflow.op.xla.XlaRecvFromHost;
import org.tensorflow.op.xla.XlaSendToHost;
import org.tensorflow.op.xla.XlaSetBound;
import org.tensorflow.op.xla.XlaVariadicReduce;
import org.tensorflow.op.xla.XlaVariadicSort;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code xla} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class XlaOps {
  private final Scope scope;

  private final Ops ops;

  XlaOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Wraps the XLA AllReduce operator
   *  documented at https://www.tensorflow.org/xla/operation_semantics#allreduce.
   *
   * @param <T> data type for {@code output} output
   * @param input Array or a non-empty tuple of arrays to reduce across replicas.
   * @param groupAssignment Groups between which the reductions are performed.
   * @param reduceOp Reduction computation.
   * @param mode group mode.
   *  CrossReplica: group_assignment contains replica_id. Each group contains the
   *  replicas for the current partition.
   *  CrossReplicaAndPartition: group_assignment contains replica_id. Each group
   *  contains the replicas for all partitions.
   * @param <T> data type for {@code XlaAllReduce} output and operands
   * @return a new instance of AllReduce
   */
  public <T extends TNumber> AllReduce<T> allReduce(Operand<T> input,
      Operand<TInt32> groupAssignment, String reduceOp, String mode) {
    return AllReduce.create(scope, input, groupAssignment, reduceOp, mode);
  }

  /**
   * Helper operator for performing XLA-style broadcasts
   *  Broadcasts {@code lhs} and {@code rhs} to the same rank, by adding size 1 dimensions to
   *  whichever of {@code lhs} and {@code rhs} has the lower rank, using XLA's broadcasting rules
   *  for binary operators.
   *
   * @param <T> data type for {@code lhs_output} output
   * @param lhs the LHS input tensor
   * @param rhs the RHS input tensor
   * @param broadcastDims an XLA-style broadcast dimension specification
   * @param <T> data type for {@code XlaBroadcastHelper} output and operands
   * @return a new instance of BroadcastHelper
   */
  public <T extends TType> BroadcastHelper<T> broadcastHelper(Operand<T> lhs, Operand<T> rhs,
      Operand<? extends TNumber> broadcastDims) {
    return BroadcastHelper.create(scope, lhs, rhs, broadcastDims);
  }

  /**
   * Operator that connects the output of an XLA computation to other consumer graph nodes.
   *
   * @param <T> data type for {@code outputs} output
   * @param input The input value
   * @param <T> data type for {@code XlaClusterOutput} output and operands
   * @return a new instance of ClusterOutput
   */
  public <T extends TType> ClusterOutput<T> clusterOutput(Operand<T> input) {
    return ClusterOutput.create(scope, input);
  }

  /**
   * Wraps the XLA ConvGeneralDilated operator, documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#conv_convolution
   *  .
   *
   * @param <W> data type for {@code output} output
   * @param lhs input tensor
   * @param rhs kernel tensor
   * @param windowStrides inter-window strides
   * @param padding padding to apply at the start and end of each input dimensions
   * @param lhsDilation dilation to apply between input elements
   * @param rhsDilation dilation to apply between kernel elements
   * @param featureGroupCount number of feature groups for grouped convolution.
   * @param dimensionNumbers serialized xla::ConvolutionDimensionNumbers proto.
   * @param precisionConfig serialized xla::PrecisionConfig proto.
   * @param preferredElementType type of the tensor.
   * @param options carries optional attribute values
   * @param <W> data type for {@code XlaConvV2} output and operands
   * @param <V> data type for {@code XlaConvV2} output and operands
   * @return a new instance of Conv
   */
  public <W extends TType, V extends TNumber> Conv<W> conv(Operand<? extends TType> lhs,
      Operand<? extends TType> rhs, Operand<V> windowStrides, Operand<V> padding,
      Operand<V> lhsDilation, Operand<V> rhsDilation, Operand<V> featureGroupCount,
      String dimensionNumbers, String precisionConfig, Class<W> preferredElementType,
      Conv.Options... options) {
    return Conv.create(scope, lhs, rhs, windowStrides, padding, lhsDilation, rhsDilation, featureGroupCount, dimensionNumbers, precisionConfig, preferredElementType, options);
  }

  /**
   * Takes the packed uint32 input and unpacks the input to uint8 to do
   *  Dequantization on device.
   *
   * @param input Input tensors whose types is uint32, shape is [d0, ..., dn].
   * @param minRange The minimum scalar value possibly produced for the input.
   * @param maxRange The maximum scalar value possibly produced for the input.
   * @param mode String to determine the dequantize mode in {&quot;MIN_COMBINED&quot;, &quot;MIN_FIRST&quot;, &quot;SCALED&quot;}.
   * @param transposeOutput Boolean to determine if output is transposed. transpose_output
   *  is faster when input is large and rank of input is higher than 1.
   * @return a new instance of Dequantize
   */
  public Dequantize dequantize(Operand<? extends TType> input, Float minRange, Float maxRange,
      String mode, Boolean transposeOutput) {
    return Dequantize.create(scope, input, minRange, maxRange, mode, transposeOutput);
  }

  /**
   * Wraps the XLA DotGeneral operator, documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#dotgeneral
   *  .
   *
   * @param <V> data type for {@code output} output
   * @param lhs the LHS tensor
   * @param rhs the RHS tensor
   * @param dimensionNumbers a serialized xla::DotDimensionNumbers proto.
   * @param precisionConfig a serialized xla::PrecisionConfig proto.
   * @param preferredElementType The type of the tensor.
   * @param <V> data type for {@code XlaDotV2} output and operands
   * @return a new instance of Dot
   */
  public <V extends TType> Dot<V> dot(Operand<? extends TType> lhs, Operand<? extends TType> rhs,
      String dimensionNumbers, String precisionConfig, Class<V> preferredElementType) {
    return Dot.create(scope, lhs, rhs, dimensionNumbers, precisionConfig, preferredElementType);
  }

  /**
   * Wraps the XLA DynamicSlice operator, documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#dynamicslice
   *  .
   *  <p>DynamicSlice extracts a sub-array from the input array at dynamic
   *  start_indices. The size of the slice in each dimension is passed in
   *  size_indices, which specify the end point of exclusive slice intervals in each
   *  dimension -- [start, start + size). The shape of start_indices must have rank 1,
   *  with dimension size equal to the rank of operand.
   *
   * @param <T> data type for {@code output} output
   * @param input A {@code Tensor} of type T.
   * @param startIndices List of N integers containing the slice size for each
   *  dimension. Each value must be strictly greater than zero, and start + size
   *  must be less than or equal to the size of the dimension to avoid
   *  implementation defined behavior.
   * @param sizeIndices The sizeIndices value
   * @param <T> data type for {@code XlaDynamicSlice} output and operands
   * @param <U> data type for {@code XlaDynamicSlice} output and operands
   * @return a new instance of DynamicSlice
   */
  public <T extends TType, U extends TNumber> DynamicSlice<T> dynamicSlice(Operand<T> input,
      Operand<U> startIndices, Operand<U> sizeIndices) {
    return DynamicSlice.create(scope, input, startIndices, sizeIndices);
  }

  /**
   * Wraps the XLA DynamicUpdateSlice operator, documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#dynamicupdateslice
   *  .
   *  <p>XlaDynamicUpdateSlice generates a result which is the value of the {@code input}
   *  operand, with a slice update overwritten at {@code indices}. The shape of {@code update}
   *  determines the shape of the sub-array of the result which is updated. The shape
   *  of indices must be rank == 1, with dimension size equal to the rank of {@code input}.
   *  <p>Handling of out-of-bounds slice indices is implementation-defined.
   *
   * @param <T> data type for {@code output} output
   * @param input A {@code Tensor} of type T.
   * @param update A {@code Tensor} of type T. Same rank as {@code input}.
   * @param indices A vector of indices into {@code input}. Must have length equal to the rank of
   *  {@code input}.
   * @param <T> data type for {@code XlaDynamicUpdateSlice} output and operands
   * @return a new instance of DynamicUpdateSlice
   */
  public <T extends TType> DynamicUpdateSlice<T> dynamicUpdateSlice(Operand<T> input,
      Operand<T> update, Operand<? extends TNumber> indices) {
    return DynamicUpdateSlice.create(scope, input, update, indices);
  }

  /**
   * An op which supports basic einsum op with 2 inputs and 1 output.
   *  This op has better TPU performance since it doesn't have explicitly reshape and
   *  transpose operations as tf.einsum does.
   *
   * @param <T> data type for {@code product} output
   * @param a The a value
   * @param b The b value
   * @param equation The value of the equation attribute
   * @param <T> data type for {@code XlaEinsum} output and operands
   * @return a new instance of Einsum
   */
  public <T extends TType> Einsum<T> einsum(Operand<T> a, Operand<T> b, String equation) {
    return Einsum.create(scope, a, b, equation);
  }

  /**
   * Wraps the XLA Gather operator documented at
   *  https://www.tensorflow.org/xla/operation_semantics#gather
   *
   * @param <T> data type for {@code output} output
   * @param operand The array we're gathering from.
   * @param startIndices Array containing the starting indices of the slices we gather.
   * @param sliceSizes slice_sizes[i] is the bounds for the slice on dimension i.
   * @param dimensionNumbers A serialized xla::GatherDimensionNumbers proto.
   * @param indicesAreSorted Boolean indicating if the indices are sorted.
   * @param <T> data type for {@code XlaGather} output and operands
   * @param <U> data type for {@code XlaGather} output and operands
   * @return a new instance of Gather
   */
  public <T extends TType, U extends TNumber> Gather<T> gather(Operand<T> operand,
      Operand<U> startIndices, Operand<U> sliceSizes, String dimensionNumbers,
      Boolean indicesAreSorted) {
    return Gather.create(scope, operand, startIndices, sliceSizes, dimensionNumbers, indicesAreSorted);
  }

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
   */
  public If ifOp(Operand<? extends TType> cond, Iterable<Operand<?>> inputs,
      ConcreteFunction thenBranch, ConcreteFunction elseBranch, List<Class<? extends TType>> Tout) {
    return If.create(scope, cond, inputs, thenBranch, elseBranch, Tout);
  }

  /**
   * Wraps the XLA Sort operator, documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#sort
   *  .
   *  <p>Sorts a tensor. Currently only sorts in ascending order are supported.
   *
   * @param <T> data type for {@code sorted_keys} output
   * @param <U> data type for {@code sorted_values} output
   * @param keys A {@code Tensor} of type K.
   * @param values A {@code Tensor} of type V.
   * @param <T> data type for {@code XlaKeyValueSort} output and operands
   * @param <U> data type for {@code XlaKeyValueSort} output and operands
   * @return a new instance of KeyValueSort
   */
  public <T extends TNumber, U extends TType> KeyValueSort<T, U> keyValueSort(Operand<T> keys,
      Operand<U> values) {
    return KeyValueSort.create(scope, keys, values);
  }

  /**
   * Wraps the XLA Pad operator, documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#pad
   *  .
   *
   * @param <T> data type for {@code output} output
   * @param input A {@code Tensor} of type T.
   * @param paddingValue A scalar {@code Tensor} of type T.
   * @param paddingLow the padding to apply at the start of each input dimensions. Must
   *  be a compile-time constant 1D tensor of length equal to rank of input.
   * @param paddingHigh the padding to apply at the end of each input dimension. Must
   *  be a compile-time constant 1D tensor of length equal to rank of input.
   * @param paddingInterior the padding to apply between each input element. Must
   *  be a compile-time constant 1D tensor of length equal to rank of input,
   *  containing only non-negative values.
   * @param <T> data type for {@code XlaPad} output and operands
   * @param <U> data type for {@code XlaPad} output and operands
   * @return a new instance of Pad
   */
  public <T extends TType, U extends TNumber> Pad<T> pad(Operand<T> input, Operand<T> paddingValue,
      Operand<U> paddingLow, Operand<U> paddingHigh, Operand<U> paddingInterior) {
    return Pad.create(scope, input, paddingValue, paddingLow, paddingHigh, paddingInterior);
  }

  /**
   * Receives the named tensor from another XLA computation. Wraps the XLA Recv
   *  operator documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#recv .
   *
   * @param <T> data type for {@code tensor} output
   * @param dtype The type of the tensor.
   * @param tensorName A string key that identifies the channel.
   * @param shape The shape of the tensor.
   * @param <T> data type for {@code XlaRecv} output and operands
   * @return a new instance of Recv
   */
  public <T extends TType> Recv<T> recv(Class<T> dtype, String tensorName, Shape shape) {
    return Recv.create(scope, dtype, tensorName, shape);
  }

  /**
   * Wraps the XLA Reduce operator, documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#reduce .
   *
   * @param <T> data type for {@code output} output
   * @param input the input tensor
   * @param initValue a scalar representing the initial value for the reduction
   * @param dimensionsToReduce dimension numbers over which to reduce
   * @param reducer a reducer function to apply
   * @param <T> data type for {@code XlaReduce} output and operands
   * @return a new instance of Reduce
   */
  public <T extends TType> Reduce<T> reduce(Operand<T> input, Operand<T> initValue,
      List<Long> dimensionsToReduce, ConcreteFunction reducer) {
    return Reduce.create(scope, input, initValue, dimensionsToReduce, reducer);
  }

  /**
   * Wraps the XLA ReduceScatter operator
   *  documented at https://www.tensorflow.org/xla/operation_semantics#reducescatter.
   *
   * @param <T> data type for {@code output} output
   * @param input Array or a non-empty tuple of arrays to reduce across replicas.
   * @param groupAssignment Groups between which the reductions are performed.
   * @param scatterDimension Dimension to scatter.
   * @param reduceOp Reduction computation.
   * @param <T> data type for {@code XlaReduceScatter} output and operands
   * @return a new instance of ReduceScatter
   */
  public <T extends TNumber> ReduceScatter<T> reduceScatter(Operand<T> input,
      Operand<TInt32> groupAssignment, Operand<TInt32> scatterDimension, String reduceOp) {
    return ReduceScatter.create(scope, input, groupAssignment, scatterDimension, reduceOp);
  }

  /**
   * Wraps the XLA ReduceWindow operator, documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#reducewindow .
   *
   * @param <T> data type for {@code output} output
   * @param input the input tensor
   * @param initValue a scalar representing the initial value for the reduction
   * @param windowDimensions the shape of the window
   * @param windowStrides the inter-window strides
   * @param baseDilations The baseDilations value
   * @param windowDilations The windowDilations value
   * @param padding the padding to apply at the start and end of each input dimensions
   * @param computation a reducer function to apply
   * @param <T> data type for {@code XlaReduceWindow} output and operands
   * @param <U> data type for {@code XlaReduceWindow} output and operands
   * @return a new instance of ReduceWindow
   */
  public <T extends TType, U extends TNumber> ReduceWindow<T> reduceWindow(Operand<T> input,
      Operand<T> initValue, Operand<U> windowDimensions, Operand<U> windowStrides,
      Operand<U> baseDilations, Operand<U> windowDilations, Operand<U> padding,
      ConcreteFunction computation) {
    return ReduceWindow.create(scope, input, initValue, windowDimensions, windowStrides, baseDilations, windowDilations, padding, computation);
  }

  /**
   * Inverse of XlaSetDynamicDimensionSize.
   *  Make an xla bounded dynamic dimension into a static dimension. The bound of the
   *  size of dimension {@code dim_index} becomes the static dimension size.
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param dimIndex The dimIndex value
   * @param <T> data type for {@code XlaRemoveDynamicDimensionSize} output and operands
   * @return a new instance of RemoveDynamicDimensionSize
   */
  public <T extends TType> RemoveDynamicDimensionSize<T> removeDynamicDimensionSize(
      Operand<T> input, Operand<TInt32> dimIndex) {
    return RemoveDynamicDimensionSize.create(scope, input, dimIndex);
  }

  /**
   * Replica ID.
   *
   * @return a new instance of ReplicaId
   */
  public ReplicaId replicaId() {
    return ReplicaId.create(scope);
  }

  /**
   * Stateless PRNG bit generator.
   *  Wraps the XLA RngBitGenerator operator, documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#rngbitgenerator.
   *
   * @param <U> data type for {@code output} output
   * @param algorithm The PRNG algorithm to use, one of
   *  tf.random.Algorithm.{PHILOX, THREEFRY, AUTO_SELECT}.
   * @param initialState Initial state for the PRNG algorithm. For THREEFRY, it should be
   *  a u64[2] and for PHILOX a u64[3].
   * @param shape The output shape of the generated data.
   * @param dtype The type of the tensor.
   * @param <U> data type for {@code XlaRngBitGenerator} output and operands
   * @return a new instance of RngBitGenerator
   */
  public <U extends TNumber> RngBitGenerator<U> rngBitGenerator(Operand<TInt32> algorithm,
      Operand<? extends TType> initialState, Operand<? extends TNumber> shape, Class<U> dtype) {
    return RngBitGenerator.create(scope, algorithm, initialState, shape, dtype);
  }

  /**
   * Wraps the XLA Scatter operator documented at
   *  https://www.tensorflow.org/xla/operation_semantics#scatter.
   *
   * @param <T> data type for {@code output} output
   * @param operand Array to be scattered into.
   * @param scatterIndices Array containing the starting indices of the slices that must
   *  be scattered to.
   * @param updates Array containing the values that must be used for scattering.
   * @param updateComputation Computation to be used for combining the existing values in
   *  the input array and the updates during scatter.
   * @param dimensionNumbers A serialized xla::ScatterDimensionNumbers proto.
   * @param indicesAreSorted Boolean indicating if the indices are sorted.
   * @param <T> data type for {@code XlaScatter} output and operands
   * @return a new instance of Scatter
   */
  public <T extends TType> Scatter<T> scatter(Operand<T> operand,
      Operand<? extends TNumber> scatterIndices, Operand<T> updates,
      ConcreteFunction updateComputation, String dimensionNumbers, Boolean indicesAreSorted) {
    return Scatter.create(scope, operand, scatterIndices, updates, updateComputation, dimensionNumbers, indicesAreSorted);
  }

  /**
   * Wraps the XLA SelectAndScatter operator, documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#selectandscatter
   *  .
   *
   * @param <T> data type for {@code output} output
   * @param operand the input tensor
   * @param windowDimensions the shape of the window
   * @param windowStrides the inter-window strides
   * @param padding the padding to apply at the start and end of each input dimensions
   * @param source a tensor of values to scatter
   * @param initValue a scalar representing the initial value for the output tensor
   * @param select a selection function to apply
   * @param scatter a scatter function to apply
   * @param <T> data type for {@code XlaSelectAndScatter} output and operands
   * @param <U> data type for {@code XlaSelectAndScatter} output and operands
   * @return a new instance of SelectAndScatter
   */
  public <T extends TType, U extends TNumber> SelectAndScatter<T> selectAndScatter(
      Operand<T> operand, Operand<U> windowDimensions, Operand<U> windowStrides, Operand<U> padding,
      Operand<T> source, Operand<T> initValue, ConcreteFunction select, ConcreteFunction scatter) {
    return SelectAndScatter.create(scope, operand, windowDimensions, windowStrides, padding, source, initValue, select, scatter);
  }

  /**
   * Computes the eigen decomposition of a batch of self-adjoint matrices
   *  (Note: Only real inputs are supported).
   *  <p>Computes the eigenvalues and eigenvectors of the innermost N-by-N matrices in
   *  tensor such that tensor[...,:,:] * v[..., :,i] = e[..., i] * v[...,:,i], for
   *  i=0...N-1.
   *
   * @param <T> data type for {@code w} output
   * @param a the input tensor.
   * @param lower a boolean specifies whether the calculation is done with the lower
   *  triangular part or the upper triangular part.
   * @param maxIter maximum number of sweep update, i.e., the whole lower triangular
   *  part or upper triangular part based on parameter lower. Heuristically, it has
   *  been argued that approximately logN sweeps are needed in practice (Ref: Golub &amp;
   *  van Loan &quot;Matrix Computation&quot;).
   * @param epsilon the tolerance ratio.
   * @param <T> data type for {@code XlaSelfAdjointEig} output and operands
   * @return a new instance of SelfAdjointEig
   */
  public <T extends TType> SelfAdjointEig<T> selfAdjointEig(Operand<T> a, Boolean lower,
      Long maxIter, Float epsilon) {
    return SelfAdjointEig.create(scope, a, lower, maxIter, epsilon);
  }

  /**
   * Sends the named tensor to another XLA computation. Wraps the XLA Send operator
   *  documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#send .
   *
   * @param tensor The tensor to send.
   * @param tensorName A string key that identifies the channel.
   * @return a new instance of Send
   */
  public Send send(Operand<? extends TType> tensor, String tensorName) {
    return Send.create(scope, tensor, tensorName);
  }

  /**
   * Make a static dimension into a xla bounded dynamic dimension.
   *  <pre>
   *      The current static dimension size will become the bound and the second
   *      operand becomes the dynamic size of the dimension.
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param dimIndex The dimIndex value
   * @param sizeOutput The sizeOutput value
   * @param <T> data type for {@code XlaSetDynamicDimensionSize} output and operands
   * @return a new instance of SetDynamicDimensionSize
   */
  public <T extends TType> SetDynamicDimensionSize<T> setDynamicDimensionSize(Operand<T> input,
      Operand<TInt32> dimIndex, Operand<TInt32> sizeOutput) {
    return SetDynamicDimensionSize.create(scope, input, dimIndex, sizeOutput);
  }

  /**
   * An op which shards the input based on the given sharding attribute. It can
   *  selectively annotate a subset of tensor dimensions by skipping unspecified_dims,
   *  and the sharding annotation should be replicated in those dims.
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param options carries optional attribute values
   * @param <T> data type for {@code XlaSharding} output and operands
   * @return a new instance of Sharding
   */
  public <T extends TType> Sharding<T> sharding(Operand<T> input, Sharding.Options... options) {
    return Sharding.create(scope, input, options);
  }

  /**
   * Wraps the XLA Sort operator, documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#sort
   *  .
   *  <p>Sorts a tensor. Currently only sorts in ascending order are supported.
   *
   * @param <T> data type for {@code output} output
   * @param input A {@code Tensor} of type T.
   * @param <T> data type for {@code XlaSort} output and operands
   * @return a new instance of Sort
   */
  public <T extends TType> Sort<T> sort(Operand<T> input) {
    return Sort.create(scope, input);
  }

  /**
   * An op used by XLA SPMD partitioner to switch from automatic partitioning to
   *  manual partitioning. It annotates the input (full-shape, to be automatically
   *  partitioned) with the same sharding used by manual partitioning, and outputs a
   *  shard-shaped tensor to be consumed by later manually-partitioned ops. If the
   *  shape is not evenly partitionable, the padding region will be masked with 0s.
   *  The conversion can happen partially in subgroups, by specifying the dim
   *  attribute, where only that dim will be converted.
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param manualSharding The value of the manualSharding attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code XlaSpmdFullToShardShape} output and operands
   * @return a new instance of SpmdFullToShardShape
   */
  public <T extends TType> SpmdFullToShardShape<T> spmdFullToShardShape(Operand<T> input,
      String manualSharding, SpmdFullToShardShape.Options... options) {
    return SpmdFullToShardShape.create(scope, input, manualSharding, options);
  }

  /**
   * An op used by XLA SPMD partitioner to switch from manual partitioning to
   *  automatic partitioning. It converts the shard-shaped, manually partitioned input
   *  into full-shaped tensor to be partitioned automatically with the same sharding
   *  used by manual partitioning. The conversion can happen partially in subgroups,
   *  by specifying the dim attribute, where only that dim will be converted.
   *
   * @param <T> data type for {@code output} output
   * @param input The input value
   * @param manualSharding The value of the manualSharding attribute
   * @param fullShape The value of the fullShape attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code XlaSpmdShardToFullShape} output and operands
   * @return a new instance of SpmdShardToFullShape
   */
  public <T extends TType> SpmdShardToFullShape<T> spmdShardToFullShape(Operand<T> input,
      String manualSharding, Shape fullShape, SpmdShardToFullShape.Options... options) {
    return SpmdShardToFullShape.create(scope, input, manualSharding, fullShape, options);
  }

  /**
   * Computes the eigen decomposition of a batch of self-adjoint matrices
   *  (Note: Only real inputs are supported).
   *  <p>Computes the eigenvalues and eigenvectors of the innermost M-by-N matrices in
   *  tensor such that tensor[...,:,:] = u[..., :, :] * Diag(s[..., :]) * Transpose(v[...,:,:]).
   *
   * @param <T> data type for {@code s} output
   * @param a the input tensor.
   * @param maxIter maximum number of sweep update, i.e., the whole lower triangular
   *  part or upper triangular part based on parameter lower. Heuristically, it has
   *  been argued that approximately log(min (M, N)) sweeps are needed in practice
   *  (Ref: Golub &amp; van Loan &quot;Matrix Computation&quot;).
   * @param epsilon the tolerance ratio.
   * @param precisionConfig a serialized xla::PrecisionConfig proto.
   * @param <T> data type for {@code XlaSvd} output and operands
   * @return a new instance of Svd
   */
  public <T extends TType> Svd<T> svd(Operand<T> a, Long maxIter, Float epsilon,
      String precisionConfig) {
    return Svd.create(scope, a, maxIter, epsilon, precisionConfig);
  }

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
   */
  public While whileOp(Iterable<Operand<?>> input, ConcreteFunction cond, ConcreteFunction body) {
    return While.create(scope, input, cond, body);
  }

  /**
   * A pseudo-op to represent host-side computation in an XLA program.
   *
   * @param inputs A list of tensors that will be sent to the host.
   * @param Toutputs The element types of each element in {@code outputs}.
   * @param ancestors A list of names of HostCompute computations that must be
   *  sequenced before this computation.
   * @param shapes If shape_inference_graph is empty, a list of the shapes of {@code outputs}.
   * @param shapeInferenceGraph If non-empty, a serialized GraphDef representing a graph
   *  that must be analyzed at compile time to determine the shapes of the outputs.
   * @param key A unique identifier for this region used to match up host transfers.
   * @param options carries optional attribute values
   * @return a new instance of XlaHostCompute
   */
  public XlaHostCompute xlaHostCompute(Iterable<Operand<?>> inputs,
      List<Class<? extends TType>> Toutputs, List<String> ancestors, List<Shape> shapes,
      ConcreteFunction shapeInferenceGraph, String key, XlaHostCompute.Options... options) {
    return XlaHostCompute.create(scope, inputs, Toutputs, ancestors, shapes, shapeInferenceGraph, key, options);
  }

  /**
   * XLA Launch Op. For use by the XLA JIT only.
   *
   * @param constants The constants value
   * @param args The args value
   * @param resources The resources value
   * @param Tresults The value of the Tresults attribute
   * @param function The value of the function attribute
   * @return a new instance of XlaLaunch
   */
  public XlaLaunch xlaLaunch(Iterable<Operand<?>> constants, Iterable<Operand<?>> args,
      Iterable<Operand<? extends TType>> resources, List<Class<? extends TType>> Tresults,
      ConcreteFunction function) {
    return XlaLaunch.create(scope, constants, args, resources, Tresults, function);
  }

  /**
   * An op to receive a tensor from the host.
   *  output: the tensor that will be received from the host.
   *  Toutput: element type for output.
   *  shape: shape for output.
   *  key: A unique identifier for this region used to match up host transfers.
   *
   * @param <T> data type for {@code output} output
   * @param Toutput The value of the Toutput attribute
   * @param shape The value of the shape attribute
   * @param key The value of the key attribute
   * @param <T> data type for {@code XlaRecvFromHost} output and operands
   * @return a new instance of XlaRecvFromHost
   */
  public <T extends TType> XlaRecvFromHost<T> xlaRecvFromHost(Class<T> Toutput, Shape shape,
      String key) {
    return XlaRecvFromHost.create(scope, Toutput, shape, key);
  }

  /**
   * An op to send a tensor to the host.
   *  input: the tensor that will be sent to the host.
   *  Tinput: element type for input.
   *  key: A unique identifier for this region used to match up host transfers.
   *
   * @param input The input value
   * @param key The value of the key attribute
   * @return a new instance of XlaSendToHost
   */
  public XlaSendToHost xlaSendToHost(Operand<? extends TType> input, String key) {
    return XlaSendToHost.create(scope, input, key);
  }

  /**
   * Set a bound for the given input value as a hint to Xla compiler,
   *  <pre>
   *      returns the same value.
   *  </pre>
   *
   * @param input The input value
   * @param bound The bound value
   * @return a new instance of XlaSetBound
   */
  public XlaSetBound xlaSetBound(Operand<TInt32> input, Operand<TInt32> bound) {
    return XlaSetBound.create(scope, input, bound);
  }

  /**
   * Wraps the variadic XLA Reduce operator.
   *  Semantics are documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#variadic_reduce.
   *  <p>This is an expanded version of XlaVariadicReduce, with support for
   *  operands of different dtypes, and improved shape inference.
   *
   * @param inputs the input tensor(s)
   * @param initValues scalar initial value(s) for the reduction
   * @param dimensionsToReduce dimension numbers over which to reduce
   * @param reducer a reducer function to apply
   * @return a new instance of XlaVariadicReduce
   */
  public XlaVariadicReduce xlaVariadicReduce(Iterable<Operand<?>> inputs,
      Iterable<Operand<?>> initValues, List<Long> dimensionsToReduce, ConcreteFunction reducer) {
    return XlaVariadicReduce.create(scope, inputs, initValues, dimensionsToReduce, reducer);
  }

  /**
   * Wraps the XLA Sort operator, documented at
   *  https://www.tensorflow.org/performance/xla/operation_semantics#sort
   *  .
   *  <p>Sorts one or more tensors, with support for custom comparator, dimension, and
   *  is_stable attributes.
   *
   * @param inputs A list of {@code Tensor} of identical shape but possibly different types.
   * @param dimension The dimension along which to sort. Must be a compile-time constant.
   * @param comparator A comparator function to apply to 2*N scalars and returning a
   *  boolean. N is the number of sort inputs. If you want to sort in ascending
   *  order then the comparator should perform a less-than comparison.
   * @param isStable Whether to use stable sort.
   * @return a new instance of XlaVariadicSort
   */
  public XlaVariadicSort xlaVariadicSort(Iterable<Operand<?>> inputs, Operand<TInt32> dimension,
      ConcreteFunction comparator, Boolean isStable) {
    return XlaVariadicSort.create(scope, inputs, dimension, comparator, isStable);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
