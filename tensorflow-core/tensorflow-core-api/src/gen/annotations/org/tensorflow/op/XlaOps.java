// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
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
import org.tensorflow.op.xla.AssignVariableConcatND;
import org.tensorflow.op.xla.ConcatND;
import org.tensorflow.op.xla.ReadVariableSplitND;
import org.tensorflow.op.xla.SplitND;
import org.tensorflow.op.xla.XlaHostCompute;
import org.tensorflow.op.xla.XlaRecvFromHost;
import org.tensorflow.op.xla.XlaSendToHost;
import org.tensorflow.op.xla.XlaSparseActivationsUnstack;
import org.tensorflow.op.xla.XlaSparseCoreAdagrad;
import org.tensorflow.op.xla.XlaSparseCoreAdagradMomentum;
import org.tensorflow.op.xla.XlaSparseCoreAdam;
import org.tensorflow.op.xla.XlaSparseCoreFtrl;
import org.tensorflow.op.xla.XlaSparseCoreSgd;
import org.tensorflow.op.xla.XlaSparseDenseMatmul;
import org.tensorflow.op.xla.XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradAndCsrInput;
import org.tensorflow.op.xla.XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput;
import org.tensorflow.op.xla.XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdamAndCsrInput;
import org.tensorflow.op.xla.XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput;
import org.tensorflow.op.xla.XlaSparseDenseMatmulCustomCombinerOnTcGradWithFtrlAndCsrInput;
import org.tensorflow.op.xla.XlaSparseDenseMatmulGradWithAdagradAndCsrInput;
import org.tensorflow.op.xla.XlaSparseDenseMatmulGradWithAdagradMomentumAndCsrInput;
import org.tensorflow.op.xla.XlaSparseDenseMatmulGradWithAdamAndCsrInput;
import org.tensorflow.op.xla.XlaSparseDenseMatmulGradWithFtrlAndCsrInput;
import org.tensorflow.op.xla.XlaSparseDenseMatmulGradWithSgdAndCsrInput;
import org.tensorflow.op.xla.XlaSparseDenseMatmulWithCsrInput;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code xla} operations as {@link Op Op}s
 *
 * @see Ops
 */
public final class XlaOps {
  private final Scope scope;

  private final Ops ops;

  XlaOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Concats input tensor across all dimensions.
   *  An op which merges slices the input tensor based on the given num_splits
   *  attribute, strips paddings optionally, and writes the merged tensor without
   *  paddings to the resource variable.
   *  <p>This op may be generated via the TPU bridge.
   *  <p>For example, with {@code input} tensor:
   *  <pre>
   *  [[0, 1],
   *   [4, 5]]
   *  [[2, 3],
   *   [6, 7]]
   *  [[8, 9],
   *   [12, 13]]
   *  [[10, 11],
   *   [14, 15]]
   *  </pre>
   *  <p>{@code num_splits}:
   *  <pre>
   *  [2, 2]
   *  </pre>
   *  <p>and {@code paddings}:
   *  <pre>
   *  [1, 1]
   *  </pre>
   *  <p>the expected {@code outputs} is:
   *  <pre>
   *  [[0, 1, 2],
   *   [4, 5, 6],
   *   [8, 9, 10]]
   *  </pre>
   *
   * @param resource Resource variable for concatenated input tensors across all dimensions.
   * @param inputs Input tensor slices in row-major order to merge across all dimensions. All
   *  inputs must have the same shape.
   * @param numConcats Number of ways to merge per dimension.
   * @param options carries optional attribute values
   * @return a new instance of AssignVariableConcatND
   */
  public AssignVariableConcatND assignVariableConcatND(Operand<? extends TType> resource,
      Iterable<Operand<? extends TType>> inputs, List<Long> numConcats,
      AssignVariableConcatND.Options... options) {
    return AssignVariableConcatND.create(scope, resource, inputs, numConcats, options);
  }

  /**
   * Concats input tensor across all dimensions.
   *  An op which merges slices the input tensor based on the given num_splits
   *  attribute, strips paddings optionally, and returns the merged tensor without
   *  paddings.
   *  <p>This op may be generated via the TPU bridge.
   *  <p>For example, with {@code input} tensor:
   *  <pre>
   *  [[0, 1],
   *   [4, 5]]
   *  [[2, 3],
   *   [6, 7]]
   *  [[8, 9],
   *   [12, 13]]
   *  [[10, 11],
   *   [14, 15]]
   *  </pre>
   *  <p>{@code num_splits}:
   *  <pre>
   *  [2, 2]
   *  </pre>
   *  <p>and {@code paddings}:
   *  <pre>
   *  [1, 1]
   *  </pre>
   *  <p>the expected {@code outputs} is:
   *  <pre>
   *  [[0, 1, 2],
   *   [4, 5, 6],
   *   [8, 9, 10]]
   *  </pre>
   *
   * @param inputs Input tensor slices in row-major order to merge across all dimensions. All
   *  inputs must have the same shape.
   * @param numConcats Number of ways to merge per dimension.
   * @param options carries optional attribute values
   * @param <T> data type for {@code XlaConcatND} output and operands
   * @return a new instance of ConcatND
   */
  public <T extends TType> ConcatND<T> concatND(Iterable<Operand<T>> inputs, List<Long> numConcats,
      ConcatND.Options... options) {
    return ConcatND.create(scope, inputs, numConcats, options);
  }

  /**
   * Splits resource variable input tensor across all dimensions.
   *  An op which splits the resource variable input tensor based on the given
   *  num_splits attribute, pads slices optionally, and returned the slices. Slices
   *  are returned in row-major order.
   *  <p>This op may be generated via the TPU bridge.
   *  <p>For example, with {@code input} tensor:
   *  <pre>
   *  [[0, 1, 2],
   *   [3, 4, 5],
   *   [6, 7, 8]]
   *  </pre>
   *  <p>{@code num_splits}:
   *  <pre>
   *  [2, 2]
   *  </pre>
   *  <p>and {@code paddings}:
   *  <pre>
   *  [1, 1]
   *  </pre>
   *  <p>the expected {@code outputs} is:
   *  <pre>
   *  [[0, 1],
   *   [3, 4]]
   *  [[2, 0],
   *   [5, 0]]
   *  [[6, 7],
   *   [0, 0]]
   *  [[8, 0],
   *   [0, 0]]
   *  </pre>
   *
   * @param resource Resource variable of input tensor to split across all dimensions.
   * @param T The value of the T attribute
   * @param N The value of the N attribute
   * @param numSplits Number of ways to split per dimension. Shape dimensions must be evenly
   *  divisible.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ReadVariableXlaSplitND} output and operands
   * @return a new instance of ReadVariableSplitND
   */
  public <T extends TType> ReadVariableSplitND<T> readVariableSplitND(
      Operand<? extends TType> resource, Class<T> T, Long N, List<Long> numSplits,
      ReadVariableSplitND.Options... options) {
    return ReadVariableSplitND.create(scope, resource, T, N, numSplits, options);
  }

  /**
   * Splits input tensor across all dimensions.
   *  An op which slices the input tensor based on the given num_splits attribute,
   *  pads slices optionally, and returned the slices. Slices are returned in
   *  row-major order.
   *  <p>This op may be generated via the TPU bridge.
   *  <p>For example, with {@code input} tensor:
   *  <pre>
   *  [[0, 1, 2],
   *   [3, 4, 5],
   *   [6, 7, 8]]
   *  </pre>
   *  <p>{@code num_splits}:
   *  <pre>
   *  [2, 2]
   *  </pre>
   *  <p>and {@code paddings}:
   *  <pre>
   *  [1, 1]
   *  </pre>
   *  <p>the expected {@code outputs} is:
   *  <pre>
   *  [[0, 1],
   *   [3, 4]]
   *  [[2, 0],
   *   [5, 0]]
   *  [[6, 7],
   *   [0, 0]]
   *  [[8, 0],
   *   [0, 0]]
   *  </pre>
   *
   * @param input Input tensor to split across all dimensions.
   * @param N The value of the N attribute
   * @param numSplits Number of ways to split per dimension. Shape dimensions must be evenly
   *  divisible.
   * @param options carries optional attribute values
   * @param <T> data type for {@code XlaSplitND} output and operands
   * @return a new instance of SplitND
   */
  public <T extends TType> SplitND<T> splitND(Operand<T> input, Long N, List<Long> numSplits,
      SplitND.Options... options) {
    return SplitND.create(scope, input, N, numSplits, options);
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
   * An op to receive a tensor from the host.
   *  output: the tensor that will be received from the host.
   *  Toutput: element type for output.
   *  shape: shape for output.
   *  key: A unique identifier for this region used to match up host transfers.
   *
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
   * The XlaSparseActivationsUnstack operation
   *
   * @param stackedActivations The stackedActivations value
   * @param numTables The value of the numTables attribute
   * @param sampleCounts The value of the sampleCounts attribute
   * @param features The value of the features attribute
   * @param interleaved The value of the interleaved attribute
   * @param dtype The value of the dtype attribute
   * @param <U> data type for {@code XlaSparseActivationsUnstack} output and operands
   * @return a new instance of XlaSparseActivationsUnstack
   */
  public <U extends TType> XlaSparseActivationsUnstack<U> xlaSparseActivationsUnstack(
      Operand<? extends TType> stackedActivations, Long numTables, List<Long> sampleCounts,
      List<Long> features, Boolean interleaved, Class<U> dtype) {
    return XlaSparseActivationsUnstack.create(scope, stackedActivations, numTables, sampleCounts, features, interleaved, dtype);
  }

  /**
   * The XlaSparseCoreAdagrad operation
   *
   * @param indices The indices value
   * @param gradient The gradient value
   * @param learningRate The learningRate value
   * @param accumulator The accumulator value
   * @param embeddingTable The embeddingTable value
   * @param featureWidth The value of the featureWidth attribute
   * @return a new instance of XlaSparseCoreAdagrad
   */
  public XlaSparseCoreAdagrad xlaSparseCoreAdagrad(Operand<TInt32> indices,
      Operand<TFloat32> gradient, Operand<TFloat32> learningRate, Operand<TFloat32> accumulator,
      Operand<TFloat32> embeddingTable, Long featureWidth) {
    return XlaSparseCoreAdagrad.create(scope, indices, gradient, learningRate, accumulator, embeddingTable, featureWidth);
  }

  /**
   * The XlaSparseCoreAdagradMomentum operation
   *
   * @param indices The indices value
   * @param gradient The gradient value
   * @param learningRate The learningRate value
   * @param beta1 The beta1 value
   * @param epsilon The epsilon value
   * @param accumulator The accumulator value
   * @param momentum The momentum value
   * @param embeddingTable The embeddingTable value
   * @param featureWidth The value of the featureWidth attribute
   * @param useNesterov The value of the useNesterov attribute
   * @param beta2 The value of the beta2 attribute
   * @param exponent The value of the exponent attribute
   * @return a new instance of XlaSparseCoreAdagradMomentum
   */
  public XlaSparseCoreAdagradMomentum xlaSparseCoreAdagradMomentum(Operand<TInt32> indices,
      Operand<TFloat32> gradient, Operand<TFloat32> learningRate, Operand<TFloat32> beta1,
      Operand<TFloat32> epsilon, Operand<TFloat32> accumulator, Operand<TFloat32> momentum,
      Operand<TFloat32> embeddingTable, Long featureWidth, Boolean useNesterov, Float beta2,
      Float exponent) {
    return XlaSparseCoreAdagradMomentum.create(scope, indices, gradient, learningRate, beta1, epsilon, accumulator, momentum, embeddingTable, featureWidth, useNesterov, beta2, exponent);
  }

  /**
   * The XlaSparseCoreAdam operation
   *
   * @param embeddingTable The embeddingTable value
   * @param indices The indices value
   * @param gradient The gradient value
   * @param learningRate The learningRate value
   * @param momentum The momentum value
   * @param velocity The velocity value
   * @param beta1 The beta1 value
   * @param beta2 The beta2 value
   * @param epsilon The epsilon value
   * @param featureWidth The value of the featureWidth attribute
   * @param useSumInsideSqrt The value of the useSumInsideSqrt attribute
   * @return a new instance of XlaSparseCoreAdam
   */
  public XlaSparseCoreAdam xlaSparseCoreAdam(Operand<TFloat32> embeddingTable,
      Operand<TInt32> indices, Operand<TFloat32> gradient, Operand<TFloat32> learningRate,
      Operand<TFloat32> momentum, Operand<TFloat32> velocity, Operand<TFloat32> beta1,
      Operand<TFloat32> beta2, Operand<TFloat32> epsilon, Long featureWidth,
      Boolean useSumInsideSqrt) {
    return XlaSparseCoreAdam.create(scope, embeddingTable, indices, gradient, learningRate, momentum, velocity, beta1, beta2, epsilon, featureWidth, useSumInsideSqrt);
  }

  /**
   * The XlaSparseCoreFtrl operation
   *
   * @param embeddingTable The embeddingTable value
   * @param accumulator The accumulator value
   * @param linear The linear value
   * @param learningRate The learningRate value
   * @param indices The indices value
   * @param gradient The gradient value
   * @param beta The beta value
   * @param learningRatePower The learningRatePower value
   * @param l2RegularizationStrength The l2RegularizationStrength value
   * @param featureWidth The value of the featureWidth attribute
   * @param multiplyLinearByLearningRate The value of the multiplyLinearByLearningRate attribute
   * @param l1RegularizationStrength The value of the l1RegularizationStrength attribute
   * @return a new instance of XlaSparseCoreFtrl
   */
  public XlaSparseCoreFtrl xlaSparseCoreFtrl(Operand<TFloat32> embeddingTable,
      Operand<TFloat32> accumulator, Operand<TFloat32> linear, Operand<TFloat32> learningRate,
      Operand<TInt32> indices, Operand<TFloat32> gradient, Operand<TFloat32> beta,
      Operand<TFloat32> learningRatePower, Operand<TFloat32> l2RegularizationStrength,
      Long featureWidth, Boolean multiplyLinearByLearningRate, Float l1RegularizationStrength) {
    return XlaSparseCoreFtrl.create(scope, embeddingTable, accumulator, linear, learningRate, indices, gradient, beta, learningRatePower, l2RegularizationStrength, featureWidth, multiplyLinearByLearningRate, l1RegularizationStrength);
  }

  /**
   * The XlaSparseCoreSgd operation
   *
   * @param indices The indices value
   * @param gradient The gradient value
   * @param learningRate The learningRate value
   * @param embeddingTable The embeddingTable value
   * @param featureWidth The value of the featureWidth attribute
   * @return a new instance of XlaSparseCoreSgd
   */
  public XlaSparseCoreSgd xlaSparseCoreSgd(Operand<TInt32> indices, Operand<TFloat32> gradient,
      Operand<TFloat32> learningRate, Operand<TFloat32> embeddingTable, Long featureWidth) {
    return XlaSparseCoreSgd.create(scope, indices, gradient, learningRate, embeddingTable, featureWidth);
  }

  /**
   * The XlaSparseDenseMatmul operation
   *
   * @param rowIds The rowIds value
   * @param colIds The colIds value
   * @param values The values value
   * @param offsets The offsets value
   * @param embeddingTable The embeddingTable value
   * @param maxIdsPerPartition The value of the maxIdsPerPartition attribute
   * @param maxUniqueIdsPerPartition The value of the maxUniqueIdsPerPartition attribute
   * @param inputSize The value of the inputSize attribute
   * @return a new instance of XlaSparseDenseMatmul
   */
  public XlaSparseDenseMatmul xlaSparseDenseMatmul(Operand<TInt32> rowIds,
      Operand<? extends TType> colIds, Operand<TFloat32> values, Operand<? extends TType> offsets,
      Operand<TFloat32> embeddingTable, Long maxIdsPerPartition, Long maxUniqueIdsPerPartition,
      Long inputSize) {
    return XlaSparseDenseMatmul.create(scope, rowIds, colIds, values, offsets, embeddingTable, maxIdsPerPartition, maxUniqueIdsPerPartition, inputSize);
  }

  /**
   * The XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradAndCsrInput operation
   *
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedPosIds The sortedPosIds value
   * @param sortedGains The sortedGains value
   * @param weights The weights value
   * @param preservedValencies The preservedValencies value
   * @param preservedVectors The preservedVectors value
   * @param preservedWeights The preservedWeights value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param combinerWeightsLearningRate The combinerWeightsLearningRate value
   * @param embeddingTable The embeddingTable value
   * @param accumulator The accumulator value
   * @param maxValency The value of the maxValency attribute
   * @param numWeights The value of the numWeights attribute
   * @param combinerTableVjpComputation The value of the combinerTableVjpComputation attribute
   * @param combinerWeightsVjpComputation The value of the combinerWeightsVjpComputation attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradAndCsrInput
   */
  public XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradAndCsrInput xlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradAndCsrInput(
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TInt32> sortedPosIds, Operand<TFloat32> sortedGains, Operand<TFloat32> weights,
      Operand<TInt32> preservedValencies, Operand<TFloat32> preservedVectors,
      Operand<TFloat32> preservedWeights, Operand<TFloat32> activationGradients,
      Operand<TFloat32> learningRate, Operand<TFloat32> combinerWeightsLearningRate,
      Operand<TFloat32> embeddingTable, Operand<TFloat32> accumulator, Long maxValency,
      Long numWeights, ConcreteFunction combinerTableVjpComputation,
      ConcreteFunction combinerWeightsVjpComputation, String tableName,
      XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradAndCsrInput.Options... options) {
    return XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradAndCsrInput.create(scope, rowPointers, sortedSampleIds, sortedTokenIds, sortedPosIds, sortedGains, weights, preservedValencies, preservedVectors, preservedWeights, activationGradients, learningRate, combinerWeightsLearningRate, embeddingTable, accumulator, maxValency, numWeights, combinerTableVjpComputation, combinerWeightsVjpComputation, tableName, options);
  }

  /**
   * The XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput operation
   *
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedPosIds The sortedPosIds value
   * @param sortedGains The sortedGains value
   * @param weights The weights value
   * @param preservedValencies The preservedValencies value
   * @param preservedVectors The preservedVectors value
   * @param preservedWeights The preservedWeights value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param combinerWeightsLearningRate The combinerWeightsLearningRate value
   * @param embeddingTable The embeddingTable value
   * @param accumulator The accumulator value
   * @param momenta The momenta value
   * @param useNesterov The value of the useNesterov attribute
   * @param exponent The value of the exponent attribute
   * @param beta1 The value of the beta1 attribute
   * @param beta2 The value of the beta2 attribute
   * @param epsilon The value of the epsilon attribute
   * @param maxValency The value of the maxValency attribute
   * @param numWeights The value of the numWeights attribute
   * @param combinerTableVjpComputation The value of the combinerTableVjpComputation attribute
   * @param combinerWeightsVjpComputation The value of the combinerWeightsVjpComputation attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput
   */
  public XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput xlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput(
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TInt32> sortedPosIds, Operand<TFloat32> sortedGains, Operand<TFloat32> weights,
      Operand<TInt32> preservedValencies, Operand<TFloat32> preservedVectors,
      Operand<TFloat32> preservedWeights, Operand<TFloat32> activationGradients,
      Operand<TFloat32> learningRate, Operand<TFloat32> combinerWeightsLearningRate,
      Operand<TFloat32> embeddingTable, Operand<TFloat32> accumulator, Operand<TFloat32> momenta,
      Boolean useNesterov, Float exponent, Float beta1, Float beta2, Float epsilon, Long maxValency,
      Long numWeights, ConcreteFunction combinerTableVjpComputation,
      ConcreteFunction combinerWeightsVjpComputation, String tableName,
      XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput.Options... options) {
    return XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdagradMomentumAndCsrInput.create(scope, rowPointers, sortedSampleIds, sortedTokenIds, sortedPosIds, sortedGains, weights, preservedValencies, preservedVectors, preservedWeights, activationGradients, learningRate, combinerWeightsLearningRate, embeddingTable, accumulator, momenta, useNesterov, exponent, beta1, beta2, epsilon, maxValency, numWeights, combinerTableVjpComputation, combinerWeightsVjpComputation, tableName, options);
  }

  /**
   * The XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdamAndCsrInput operation
   *
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedPosIds The sortedPosIds value
   * @param sortedGains The sortedGains value
   * @param weights The weights value
   * @param preservedValencies The preservedValencies value
   * @param preservedVectors The preservedVectors value
   * @param preservedWeights The preservedWeights value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param combinerWeightsLearningRate The combinerWeightsLearningRate value
   * @param embeddingTable The embeddingTable value
   * @param momenta The momenta value
   * @param velocity The velocity value
   * @param useSumInsideSqrt The value of the useSumInsideSqrt attribute
   * @param beta1 The value of the beta1 attribute
   * @param beta2 The value of the beta2 attribute
   * @param epsilon The value of the epsilon attribute
   * @param maxValency The value of the maxValency attribute
   * @param numWeights The value of the numWeights attribute
   * @param combinerTableVjpComputation The value of the combinerTableVjpComputation attribute
   * @param combinerWeightsVjpComputation The value of the combinerWeightsVjpComputation attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdamAndCsrInput
   */
  public XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdamAndCsrInput xlaSparseDenseMatmulCustomCombinerOnTcGradWithAdamAndCsrInput(
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TInt32> sortedPosIds, Operand<TFloat32> sortedGains, Operand<TFloat32> weights,
      Operand<TInt32> preservedValencies, Operand<TFloat32> preservedVectors,
      Operand<TFloat32> preservedWeights, Operand<TFloat32> activationGradients,
      Operand<TFloat32> learningRate, Operand<TFloat32> combinerWeightsLearningRate,
      Operand<TFloat32> embeddingTable, Operand<TFloat32> momenta, Operand<TFloat32> velocity,
      Boolean useSumInsideSqrt, Float beta1, Float beta2, Float epsilon, Long maxValency,
      Long numWeights, ConcreteFunction combinerTableVjpComputation,
      ConcreteFunction combinerWeightsVjpComputation, String tableName,
      XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdamAndCsrInput.Options... options) {
    return XlaSparseDenseMatmulCustomCombinerOnTcGradWithAdamAndCsrInput.create(scope, rowPointers, sortedSampleIds, sortedTokenIds, sortedPosIds, sortedGains, weights, preservedValencies, preservedVectors, preservedWeights, activationGradients, learningRate, combinerWeightsLearningRate, embeddingTable, momenta, velocity, useSumInsideSqrt, beta1, beta2, epsilon, maxValency, numWeights, combinerTableVjpComputation, combinerWeightsVjpComputation, tableName, options);
  }

  /**
   * The XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput operation
   *
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedPosIds The sortedPosIds value
   * @param sortedGains The sortedGains value
   * @param weights The weights value
   * @param preservedValencies The preservedValencies value
   * @param preservedVectors The preservedVectors value
   * @param preservedWeights The preservedWeights value
   * @param activationGradients The activationGradients value
   * @param tables The tables value
   * @param hyperparameters The hyperparameters value
   * @param combinerWeightsLearningRate The combinerWeightsLearningRate value
   * @param maxValency The value of the maxValency attribute
   * @param numWeights The value of the numWeights attribute
   * @param combinerTableVjpComputation The value of the combinerTableVjpComputation attribute
   * @param combinerWeightsVjpComputation The value of the combinerWeightsVjpComputation attribute
   * @param optimizerCustomComputation The value of the optimizerCustomComputation attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput
   */
  public XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput xlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput(
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TInt32> sortedPosIds, Operand<TFloat32> sortedGains, Operand<TFloat32> weights,
      Operand<TInt32> preservedValencies, Operand<TFloat32> preservedVectors,
      Operand<TFloat32> preservedWeights, Operand<TFloat32> activationGradients,
      Iterable<Operand<TFloat32>> tables, Iterable<Operand<TFloat32>> hyperparameters,
      Operand<TFloat32> combinerWeightsLearningRate, Long maxValency, Long numWeights,
      ConcreteFunction combinerTableVjpComputation, ConcreteFunction combinerWeightsVjpComputation,
      ConcreteFunction optimizerCustomComputation, String tableName,
      XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput.Options... options) {
    return XlaSparseDenseMatmulCustomCombinerOnTcGradWithCsrInput.create(scope, rowPointers, sortedSampleIds, sortedTokenIds, sortedPosIds, sortedGains, weights, preservedValencies, preservedVectors, preservedWeights, activationGradients, tables, hyperparameters, combinerWeightsLearningRate, maxValency, numWeights, combinerTableVjpComputation, combinerWeightsVjpComputation, optimizerCustomComputation, tableName, options);
  }

  /**
   * The XlaSparseDenseMatmulCustomCombinerOnTcGradWithFtrlAndCsrInput operation
   *
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedPosIds The sortedPosIds value
   * @param sortedGains The sortedGains value
   * @param weights The weights value
   * @param preservedValencies The preservedValencies value
   * @param preservedVectors The preservedVectors value
   * @param preservedWeights The preservedWeights value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param combinerWeightsLearningRate The combinerWeightsLearningRate value
   * @param embeddingTable The embeddingTable value
   * @param accumulator The accumulator value
   * @param linear The linear value
   * @param multiplyLinearByLearningRate The value of the multiplyLinearByLearningRate attribute
   * @param beta The value of the beta attribute
   * @param learningRatePower The value of the learningRatePower attribute
   * @param l1RegularizationStrength The value of the l1RegularizationStrength attribute
   * @param l2RegularizationStrength The value of the l2RegularizationStrength attribute
   * @param maxValency The value of the maxValency attribute
   * @param numWeights The value of the numWeights attribute
   * @param combinerTableVjpComputation The value of the combinerTableVjpComputation attribute
   * @param combinerWeightsVjpComputation The value of the combinerWeightsVjpComputation attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulCustomCombinerOnTcGradWithFtrlAndCsrInput
   */
  public XlaSparseDenseMatmulCustomCombinerOnTcGradWithFtrlAndCsrInput xlaSparseDenseMatmulCustomCombinerOnTcGradWithFtrlAndCsrInput(
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TInt32> sortedPosIds, Operand<TFloat32> sortedGains, Operand<TFloat32> weights,
      Operand<TInt32> preservedValencies, Operand<TFloat32> preservedVectors,
      Operand<TFloat32> preservedWeights, Operand<TFloat32> activationGradients,
      Operand<TFloat32> learningRate, Operand<TFloat32> combinerWeightsLearningRate,
      Operand<TFloat32> embeddingTable, Operand<TFloat32> accumulator, Operand<TFloat32> linear,
      Boolean multiplyLinearByLearningRate, Float beta, Float learningRatePower,
      Float l1RegularizationStrength, Float l2RegularizationStrength, Long maxValency,
      Long numWeights, ConcreteFunction combinerTableVjpComputation,
      ConcreteFunction combinerWeightsVjpComputation, String tableName,
      XlaSparseDenseMatmulCustomCombinerOnTcGradWithFtrlAndCsrInput.Options... options) {
    return XlaSparseDenseMatmulCustomCombinerOnTcGradWithFtrlAndCsrInput.create(scope, rowPointers, sortedSampleIds, sortedTokenIds, sortedPosIds, sortedGains, weights, preservedValencies, preservedVectors, preservedWeights, activationGradients, learningRate, combinerWeightsLearningRate, embeddingTable, accumulator, linear, multiplyLinearByLearningRate, beta, learningRatePower, l1RegularizationStrength, l2RegularizationStrength, maxValency, numWeights, combinerTableVjpComputation, combinerWeightsVjpComputation, tableName, options);
  }

  /**
   * The XlaSparseDenseMatmulGradWithAdagradAndCsrInput operation
   *
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedGains The sortedGains value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param embeddingTable The embeddingTable value
   * @param accumulator The accumulator value
   * @param numMinibatchesPerPhysicalSparseCore The numMinibatchesPerPhysicalSparseCore value
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulGradWithAdagradAndCsrInput
   */
  public XlaSparseDenseMatmulGradWithAdagradAndCsrInput xlaSparseDenseMatmulGradWithAdagradAndCsrInput(
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TFloat32> sortedGains, Operand<TFloat32> activationGradients,
      Operand<TFloat32> learningRate, Operand<TFloat32> embeddingTable,
      Operand<TFloat32> accumulator, Operand<TInt32> numMinibatchesPerPhysicalSparseCore,
      String tableName, XlaSparseDenseMatmulGradWithAdagradAndCsrInput.Options... options) {
    return XlaSparseDenseMatmulGradWithAdagradAndCsrInput.create(scope, rowPointers, sortedSampleIds, sortedTokenIds, sortedGains, activationGradients, learningRate, embeddingTable, accumulator, numMinibatchesPerPhysicalSparseCore, tableName, options);
  }

  /**
   * The XlaSparseDenseMatmulGradWithAdagradMomentumAndCsrInput operation
   *
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedGains The sortedGains value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param embeddingTable The embeddingTable value
   * @param accumulator The accumulator value
   * @param momenta The momenta value
   * @param numMinibatchesPerPhysicalSparseCore The numMinibatchesPerPhysicalSparseCore value
   * @param useNesterov The value of the useNesterov attribute
   * @param exponent The value of the exponent attribute
   * @param beta1 The value of the beta1 attribute
   * @param beta2 The value of the beta2 attribute
   * @param epsilon The value of the epsilon attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulGradWithAdagradMomentumAndCsrInput
   */
  public XlaSparseDenseMatmulGradWithAdagradMomentumAndCsrInput xlaSparseDenseMatmulGradWithAdagradMomentumAndCsrInput(
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TFloat32> sortedGains, Operand<TFloat32> activationGradients,
      Operand<TFloat32> learningRate, Operand<TFloat32> embeddingTable,
      Operand<TFloat32> accumulator, Operand<TFloat32> momenta,
      Operand<TInt32> numMinibatchesPerPhysicalSparseCore, Boolean useNesterov, Float exponent,
      Float beta1, Float beta2, Float epsilon, String tableName,
      XlaSparseDenseMatmulGradWithAdagradMomentumAndCsrInput.Options... options) {
    return XlaSparseDenseMatmulGradWithAdagradMomentumAndCsrInput.create(scope, rowPointers, sortedSampleIds, sortedTokenIds, sortedGains, activationGradients, learningRate, embeddingTable, accumulator, momenta, numMinibatchesPerPhysicalSparseCore, useNesterov, exponent, beta1, beta2, epsilon, tableName, options);
  }

  /**
   * The XlaSparseDenseMatmulGradWithAdamAndCsrInput operation
   *
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedGains The sortedGains value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param embeddingTable The embeddingTable value
   * @param momenta The momenta value
   * @param velocity The velocity value
   * @param numMinibatchesPerPhysicalSparseCore The numMinibatchesPerPhysicalSparseCore value
   * @param useSumInsideSqrt The value of the useSumInsideSqrt attribute
   * @param beta1 The value of the beta1 attribute
   * @param beta2 The value of the beta2 attribute
   * @param epsilon The value of the epsilon attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulGradWithAdamAndCsrInput
   */
  public XlaSparseDenseMatmulGradWithAdamAndCsrInput xlaSparseDenseMatmulGradWithAdamAndCsrInput(
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TFloat32> sortedGains, Operand<TFloat32> activationGradients,
      Operand<TFloat32> learningRate, Operand<TFloat32> embeddingTable, Operand<TFloat32> momenta,
      Operand<TFloat32> velocity, Operand<TInt32> numMinibatchesPerPhysicalSparseCore,
      Boolean useSumInsideSqrt, Float beta1, Float beta2, Float epsilon, String tableName,
      XlaSparseDenseMatmulGradWithAdamAndCsrInput.Options... options) {
    return XlaSparseDenseMatmulGradWithAdamAndCsrInput.create(scope, rowPointers, sortedSampleIds, sortedTokenIds, sortedGains, activationGradients, learningRate, embeddingTable, momenta, velocity, numMinibatchesPerPhysicalSparseCore, useSumInsideSqrt, beta1, beta2, epsilon, tableName, options);
  }

  /**
   * The XlaSparseDenseMatmulGradWithFtrlAndCsrInput operation
   *
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedGains The sortedGains value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param embeddingTable The embeddingTable value
   * @param accumulator The accumulator value
   * @param linear The linear value
   * @param numMinibatchesPerPhysicalSparseCore The numMinibatchesPerPhysicalSparseCore value
   * @param multiplyLinearByLearningRate The value of the multiplyLinearByLearningRate attribute
   * @param beta The value of the beta attribute
   * @param learningRatePower The value of the learningRatePower attribute
   * @param l1RegularizationStrength The value of the l1RegularizationStrength attribute
   * @param l2RegularizationStrength The value of the l2RegularizationStrength attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulGradWithFtrlAndCsrInput
   */
  public XlaSparseDenseMatmulGradWithFtrlAndCsrInput xlaSparseDenseMatmulGradWithFtrlAndCsrInput(
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TFloat32> sortedGains, Operand<TFloat32> activationGradients,
      Operand<TFloat32> learningRate, Operand<TFloat32> embeddingTable,
      Operand<TFloat32> accumulator, Operand<TFloat32> linear,
      Operand<TInt32> numMinibatchesPerPhysicalSparseCore, Boolean multiplyLinearByLearningRate,
      Float beta, Float learningRatePower, Float l1RegularizationStrength,
      Float l2RegularizationStrength, String tableName,
      XlaSparseDenseMatmulGradWithFtrlAndCsrInput.Options... options) {
    return XlaSparseDenseMatmulGradWithFtrlAndCsrInput.create(scope, rowPointers, sortedSampleIds, sortedTokenIds, sortedGains, activationGradients, learningRate, embeddingTable, accumulator, linear, numMinibatchesPerPhysicalSparseCore, multiplyLinearByLearningRate, beta, learningRatePower, l1RegularizationStrength, l2RegularizationStrength, tableName, options);
  }

  /**
   * The XlaSparseDenseMatmulGradWithSgdAndCsrInput operation
   *
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedGains The sortedGains value
   * @param activationGradients The activationGradients value
   * @param learningRate The learningRate value
   * @param embeddingTable The embeddingTable value
   * @param numMinibatchesPerPhysicalSparseCore The numMinibatchesPerPhysicalSparseCore value
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @return a new instance of XlaSparseDenseMatmulGradWithSgdAndCsrInput
   */
  public XlaSparseDenseMatmulGradWithSgdAndCsrInput xlaSparseDenseMatmulGradWithSgdAndCsrInput(
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TFloat32> sortedGains, Operand<TFloat32> activationGradients,
      Operand<TFloat32> learningRate, Operand<TFloat32> embeddingTable,
      Operand<TInt32> numMinibatchesPerPhysicalSparseCore, String tableName,
      XlaSparseDenseMatmulGradWithSgdAndCsrInput.Options... options) {
    return XlaSparseDenseMatmulGradWithSgdAndCsrInput.create(scope, rowPointers, sortedSampleIds, sortedTokenIds, sortedGains, activationGradients, learningRate, embeddingTable, numMinibatchesPerPhysicalSparseCore, tableName, options);
  }

  /**
   * The XlaSparseDenseMatmulWithCsrInput operation
   *
   * @param rowPointers The rowPointers value
   * @param sortedSampleIds The sortedSampleIds value
   * @param sortedTokenIds The sortedTokenIds value
   * @param sortedGains The sortedGains value
   * @param embeddingTable The embeddingTable value
   * @param numMinibatchesPerPhysicalSparseCore The numMinibatchesPerPhysicalSparseCore value
   * @param inputSize The value of the inputSize attribute
   * @param quantizationConfigLow The value of the quantizationConfigLow attribute
   * @param quantizationConfigHigh The value of the quantizationConfigHigh attribute
   * @param quantizationConfigNumBuckets The value of the quantizationConfigNumBuckets attribute
   * @param tableName The value of the tableName attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code XlaSparseDenseMatmulWithCsrInput} output and operands
   * @return a new instance of XlaSparseDenseMatmulWithCsrInput
   */
  public <T extends TNumber> XlaSparseDenseMatmulWithCsrInput<T> xlaSparseDenseMatmulWithCsrInput(
      Operand<TInt32> rowPointers, Operand<TInt32> sortedSampleIds, Operand<TInt32> sortedTokenIds,
      Operand<TFloat32> sortedGains, Operand<T> embeddingTable,
      Operand<TInt32> numMinibatchesPerPhysicalSparseCore, Long inputSize,
      Float quantizationConfigLow, Float quantizationConfigHigh, Long quantizationConfigNumBuckets,
      String tableName, XlaSparseDenseMatmulWithCsrInput.Options... options) {
    return XlaSparseDenseMatmulWithCsrInput.create(scope, rowPointers, sortedSampleIds, sortedTokenIds, sortedGains, embeddingTable, numMinibatchesPerPhysicalSparseCore, inputSize, quantizationConfigLow, quantizationConfigHigh, quantizationConfigNumBuckets, tableName, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
