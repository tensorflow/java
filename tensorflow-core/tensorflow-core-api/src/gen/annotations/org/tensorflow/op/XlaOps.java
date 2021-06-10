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
import org.tensorflow.op.xla.BroadcastHelper;
import org.tensorflow.op.xla.ClusterOutput;
import org.tensorflow.op.xla.Conv;
import org.tensorflow.op.xla.Dequantize;
import org.tensorflow.op.xla.Dot;
import org.tensorflow.op.xla.DynamicSlice;
import org.tensorflow.op.xla.DynamicUpdateSlice;
import org.tensorflow.op.xla.Einsum;
import org.tensorflow.op.xla.Gather;
import org.tensorflow.op.xla.KeyValueSort;
import org.tensorflow.op.xla.Pad;
import org.tensorflow.op.xla.Recv;
import org.tensorflow.op.xla.ReplicaId;
import org.tensorflow.op.xla.SelfAdjointEig;
import org.tensorflow.op.xla.Send;
import org.tensorflow.op.xla.Sharding;
import org.tensorflow.op.xla.Sort;
import org.tensorflow.op.xla.Svd;
import org.tensorflow.op.xla.XlaRecvFromHost;
import org.tensorflow.op.xla.XlaSendToHost;
import org.tensorflow.op.xla.XlaSetBound;
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
   * @param input the input value
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
   * @param <T> data type for {@code output} output
   * @param lhs the input tensor
   * @param rhs the kernel tensor
   * @param windowStrides the inter-window strides
   * @param padding the padding to apply at the start and end of each input dimensions
   * @param lhsDilation dilation to apply between input elements
   * @param rhsDilation dilation to apply between kernel elements
   * @param featureGroupCount number of feature groups for grouped convolution.
   * @param dimensionNumbers a serialized xla::ConvolutionDimensionNumbers proto.
   * @param precisionConfig a serialized xla::PrecisionConfig proto.
   * @param <T> data type for {@code XlaConv} output and operands
   * @param <U> data type for {@code XlaConv} output and operands
   * @return a new instance of Conv
   */
  public <T extends TType, U extends TNumber> Conv<T> conv(Operand<T> lhs, Operand<T> rhs,
      Operand<U> windowStrides, Operand<U> padding, Operand<U> lhsDilation, Operand<U> rhsDilation,
      Operand<U> featureGroupCount, String dimensionNumbers, String precisionConfig) {
    return Conv.create(scope, lhs, rhs, windowStrides, padding, lhsDilation, rhsDilation, featureGroupCount, dimensionNumbers, precisionConfig);
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
   * @param <T> data type for {@code output} output
   * @param lhs the LHS tensor
   * @param rhs the RHS tensor
   * @param dimensionNumbers a serialized xla::DotDimensionNumbers proto.
   * @param precisionConfig a serialized xla::PrecisionConfig proto.
   * @param <T> data type for {@code XlaDot} output and operands
   * @return a new instance of Dot
   */
  public <T extends TType> Dot<T> dot(Operand<T> lhs, Operand<T> rhs, String dimensionNumbers,
      String precisionConfig) {
    return Dot.create(scope, lhs, rhs, dimensionNumbers, precisionConfig);
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
   * @param sizeIndices the sizeIndices value
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
   * @param a the a value
   * @param b the b value
   * @param equation the value of the equation property
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
   * Replica ID.
   *
   * @return a new instance of ReplicaId
   */
  public ReplicaId replicaId() {
    return ReplicaId.create(scope);
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
   * An op which shards the input based on the given sharding attribute.
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
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
   * An op to receive a tensor from the host.
   *  output: the tensor that will be received from the host.
   *  Toutput: element type for output.
   *  shape: shape for output.
   *  key: A unique identifier for this region used to match up host transfers.
   *
   * @param <T> data type for {@code output} output
   * @param Toutput the value of the Toutput property
   * @param shape the value of the shape property
   * @param key the value of the key property
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
   * @param input the input value
   * @param key the value of the key property
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
   * @param input the input value
   * @param bound the bound value
   * @return a new instance of XlaSetBound
   */
  public XlaSetBound xlaSetBound(Operand<TInt32> input, Operand<TInt32> bound) {
    return XlaSetBound.create(scope, input, bound);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
