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
import org.tensorflow.Operand;
import org.tensorflow.op.ragged.RaggedBincount;
import org.tensorflow.op.ragged.RaggedCountSparseOutput;
import org.tensorflow.op.ragged.RaggedCross;
import org.tensorflow.op.ragged.RaggedFillEmptyRows;
import org.tensorflow.op.ragged.RaggedFillEmptyRowsGrad;
import org.tensorflow.op.ragged.RaggedGather;
import org.tensorflow.op.ragged.RaggedRange;
import org.tensorflow.op.ragged.RaggedTensorFromVariant;
import org.tensorflow.op.ragged.RaggedTensorToSparse;
import org.tensorflow.op.ragged.RaggedTensorToTensor;
import org.tensorflow.op.ragged.RaggedTensorToVariant;
import org.tensorflow.op.ragged.RaggedTensorToVariantGradient;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code ragged} operations as {@link Op Op}s
 *
 * @see Ops
 */
public final class RaggedOps {
  private final Scope scope;

  private final Ops ops;

  RaggedOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
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
   * @param splits 1D int64 {@code Tensor}.
   * @param values 2D int {@code Tensor}.
   * @param sizeOutput non-negative int scalar {@code Tensor}.
   * @param weights is an int32, int64, float32, or float64 {@code Tensor} with the same
   *  shape as {@code input}, or a length-0 {@code Tensor}, in which case it acts as all weights
   *  equal to 1.
   * @param options carries optional attribute values
   * @param <U> data type for {@code RaggedBincount} output and operands
   * @param <T> data type for {@code RaggedBincount} output and operands
   * @return a new instance of RaggedBincount
   */
  public <U extends TNumber, T extends TNumber> RaggedBincount<U> raggedBincount(
      Operand<TInt64> splits, Operand<T> values, Operand<T> sizeOutput, Operand<U> weights,
      RaggedBincount.Options... options) {
    return RaggedBincount.create(scope, splits, values, sizeOutput, weights, options);
  }

  /**
   * Performs sparse-output bin counting for a ragged tensor input.
   *  Counts the number of times each value occurs in the input.
   *
   * @param <U> data type for {@code output_values} output
   * @param splits Tensor containing the row splits of the ragged tensor to count.
   * @param values Tensor containing values of the sparse tensor to count.
   * @param weights A Tensor of the same shape as indices containing per-index weight values.
   *  May also be the empty tensor if no weights are used.
   * @param binaryOutput Whether to output the number of occurrences of each value or 1.
   * @param options carries optional attribute values
   * @param <U> data type for {@code RaggedCountSparseOutput} output and operands
   * @return a new instance of RaggedCountSparseOutput
   */
  public <U extends TNumber> RaggedCountSparseOutput<U> raggedCountSparseOutput(
      Operand<TInt64> splits, Operand<? extends TNumber> values, Operand<U> weights,
      Boolean binaryOutput, RaggedCountSparseOutput.Options... options) {
    return RaggedCountSparseOutput.create(scope, splits, values, weights, binaryOutput, options);
  }

  /**
   * Generates a feature cross from a list of tensors, and returns it as a
   *  RaggedTensor.  See {@code tf.ragged.cross} for more details.
   *
   * @param <T> data type for {@code output_values} output
   * @param <U> data type for {@code output_row_splits} output
   * @param raggedValues The values tensor for each RaggedTensor input.
   * @param raggedRowSplits The row_splits tensor for each RaggedTensor input.
   * @param sparseIndices The indices tensor for each SparseTensor input.
   * @param sparseValues The values tensor for each SparseTensor input.
   * @param sparseShape The dense_shape tensor for each SparseTensor input.
   * @param denseInputs The tf.Tensor inputs.
   * @param inputOrder String specifying the tensor type for each input.  The {@code i}th character in
   *  this string specifies the type of the {@code i}th input, and is one of: 'R' (ragged),
   *  'D' (dense), or 'S' (sparse).  This attr is used to ensure that the crossed
   *  values are combined in the order of the inputs from the call to tf.ragged.cross.
   * @param hashedOutput The value of the hashedOutput attribute
   * @param numBuckets The value of the numBuckets attribute
   * @param hashKey The value of the hashKey attribute
   * @param outValuesType The value of the outValuesType attribute
   * @param outRowSplitsType The value of the outRowSplitsType attribute
   * @param <T> data type for {@code RaggedCross} output and operands
   * @param <U> data type for {@code RaggedCross} output and operands
   * @return a new instance of RaggedCross
   */
  public <T extends TType, U extends TNumber> RaggedCross<T, U> raggedCross(
      Iterable<Operand<?>> raggedValues, Iterable<Operand<?>> raggedRowSplits,
      Iterable<Operand<TInt64>> sparseIndices, Iterable<Operand<?>> sparseValues,
      Iterable<Operand<TInt64>> sparseShape, Iterable<Operand<?>> denseInputs, String inputOrder,
      Boolean hashedOutput, Long numBuckets, Long hashKey, Class<T> outValuesType,
      Class<U> outRowSplitsType) {
    return RaggedCross.create(scope, raggedValues, raggedRowSplits, sparseIndices, sparseValues, sparseShape, denseInputs, inputOrder, hashedOutput, numBuckets, hashKey, outValuesType, outRowSplitsType);
  }

  /**
   * The RaggedFillEmptyRows operation
   *
   * @param <T> data type for {@code output_values} output
   * @param valueRowids The valueRowids value
   * @param values The values value
   * @param nrows The nrows value
   * @param defaultValue The defaultValue value
   * @param <T> data type for {@code RaggedFillEmptyRows} output and operands
   * @return a new instance of RaggedFillEmptyRows
   */
  public <T extends TType> RaggedFillEmptyRows<T> raggedFillEmptyRows(Operand<TInt64> valueRowids,
      Operand<T> values, Operand<TInt64> nrows, Operand<T> defaultValue) {
    return RaggedFillEmptyRows.create(scope, valueRowids, values, nrows, defaultValue);
  }

  /**
   * The RaggedFillEmptyRowsGrad operation
   *
   * @param <T> data type for {@code d_values} output
   * @param reverseIndexMap The reverseIndexMap value
   * @param gradValues The gradValues value
   * @param <T> data type for {@code RaggedFillEmptyRowsGrad} output and operands
   * @return a new instance of RaggedFillEmptyRowsGrad
   */
  public <T extends TType> RaggedFillEmptyRowsGrad<T> raggedFillEmptyRowsGrad(
      Operand<TInt64> reverseIndexMap, Operand<T> gradValues) {
    return RaggedFillEmptyRowsGrad.create(scope, reverseIndexMap, gradValues);
  }

  /**
   * Gather ragged slices from {@code params} axis {@code 0} according to {@code indices}.
   *  Outputs a {@code RaggedTensor} output composed from {@code output_dense_values} and
   *  {@code output_nested_splits}, such that:
   *  <pre>
   *  output.shape = indices.shape + params.shape[1:]
   *  output.ragged_rank = indices.shape.ndims + params.ragged_rank
   *  output[i...j, d0...dn] = params[indices[i...j], d0...dn]
   *  </pre>
   *  <p>where
   *  <ul>
   *  <li>{@code params = ragged.from_nested_row_splits(params_dense_values, params_nested_splits)}
   *  provides the values that should be gathered.</li>
   *  <li>{@code indices} ia a dense tensor with dtype {@code int32} or {@code int64}, indicating which
   *  values should be gathered.</li>
   *  <li>{@code output = ragged.from_nested_row_splits(output_dense_values, output_nested_splits)}
   *  is the output tensor.</li>
   *  </ul>
   *  <p>(Note: This c++ op is used to implement the higher-level python
   *  {@code tf.ragged.gather} op, which also supports ragged indices.)
   *
   * @param <T> data type for {@code output_nested_splits} output
   * @param <U> data type for {@code output_dense_values} output
   * @param paramsNestedSplits The {@code nested_row_splits} tensors that define the row-partitioning for the
   *  {@code params} RaggedTensor input.
   * @param paramsDenseValues The {@code flat_values} for the {@code params} RaggedTensor. There was a terminology change
   *  at the python level from dense_values to flat_values, so dense_values is the
   *  deprecated name.
   * @param indices Indices in the outermost dimension of {@code params} of the values that should be
   *  gathered.
   * @param OUTPUTRAGGEDRANK The ragged rank of the output RaggedTensor. {@code output_nested_splits} will contain
   *  this number of {@code row_splits} tensors. This value should equal
   *  {@code indices.shape.ndims + params.ragged_rank - 1}.
   * @param <T> data type for {@code RaggedGather} output and operands
   * @param <U> data type for {@code RaggedGather} output and operands
   * @return a new instance of RaggedGather
   */
  public <T extends TNumber, U extends TType> RaggedGather<T, U> raggedGather(
      Iterable<Operand<T>> paramsNestedSplits, Operand<U> paramsDenseValues,
      Operand<? extends TNumber> indices, Long OUTPUTRAGGEDRANK) {
    return RaggedGather.create(scope, paramsNestedSplits, paramsDenseValues, indices, OUTPUTRAGGEDRANK);
  }

  /**
   * Returns a {@code RaggedTensor} containing the specified sequences of numbers.
   *  Returns a {@code RaggedTensor} {@code result} composed from {@code rt_dense_values} and
   *  {@code rt_nested_splits}, such that
   *  {@code result[i] = range(starts[i], limits[i], deltas[i])}.
   *  <pre>
   *  (rt_nested_splits, rt_dense_values) = ragged_range(
   *        starts=[2, 5, 8], limits=[3, 5, 12], deltas=1)
   *  result = tf.ragged.from_row_splits(rt_dense_values, rt_nested_splits)
   *  print(result)
   *  &lt;tf.RaggedTensor [[2], [], [8, 9, 10, 11]] &gt;
   *  </pre>
   *  <p>The input tensors {@code starts}, {@code limits}, and {@code deltas} may be scalars or vectors.
   *  The vector inputs must all have the same size.  Scalar inputs are broadcast
   *  to match the size of the vector inputs.
   *
   * @param <U> data type for {@code rt_nested_splits} output
   * @param <T> data type for {@code rt_dense_values} output
   * @param starts The starts of each range.
   * @param limits The limits of each range.
   * @param deltas The deltas of each range.
   * @param <T> data type for {@code RaggedRange} output and operands
   * @return a new instance of RaggedRange, with default output types
   */
  public <T extends TNumber> RaggedRange<TInt64, T> raggedRange(Operand<T> starts,
      Operand<T> limits, Operand<T> deltas) {
    return RaggedRange.create(scope, starts, limits, deltas);
  }

  /**
   * Returns a {@code RaggedTensor} containing the specified sequences of numbers.
   *  Returns a {@code RaggedTensor} {@code result} composed from {@code rt_dense_values} and
   *  {@code rt_nested_splits}, such that
   *  {@code result[i] = range(starts[i], limits[i], deltas[i])}.
   *  <pre>
   *  (rt_nested_splits, rt_dense_values) = ragged_range(
   *        starts=[2, 5, 8], limits=[3, 5, 12], deltas=1)
   *  result = tf.ragged.from_row_splits(rt_dense_values, rt_nested_splits)
   *  print(result)
   *  &lt;tf.RaggedTensor [[2], [], [8, 9, 10, 11]] &gt;
   *  </pre>
   *  <p>The input tensors {@code starts}, {@code limits}, and {@code deltas} may be scalars or vectors.
   *  The vector inputs must all have the same size.  Scalar inputs are broadcast
   *  to match the size of the vector inputs.
   *
   * @param <U> data type for {@code rt_nested_splits} output
   * @param <T> data type for {@code rt_dense_values} output
   * @param starts The starts of each range.
   * @param limits The limits of each range.
   * @param deltas The deltas of each range.
   * @param Tsplits The value of the Tsplits attribute
   * @param <U> data type for {@code RaggedRange} output and operands
   * @param <T> data type for {@code RaggedRange} output and operands
   * @return a new instance of RaggedRange
   */
  public <U extends TNumber, T extends TNumber> RaggedRange<U, T> raggedRange(Operand<T> starts,
      Operand<T> limits, Operand<T> deltas, Class<U> Tsplits) {
    return RaggedRange.create(scope, starts, limits, deltas, Tsplits);
  }

  /**
   * Decodes a {@code variant} Tensor into a {@code RaggedTensor}.
   *  Decodes the given {@code variant} Tensor and returns a {@code RaggedTensor}. The input
   *  could be a scalar, meaning it encodes a single {@code RaggedTensor} with ragged_rank
   *  {@code output_ragged_rank}. It could also have an arbitrary rank, in which case each
   *  element is decoded into a {@code RaggedTensor} with ragged_rank {@code input_ragged_rank}
   *  and these are then stacked according to the input shape to output a single
   *  {@code RaggedTensor} with ragged_rank {@code output_ragged_rank}. Each {@code variant} element in
   *  the input Tensor is decoded by retrieving from the element a 1-D {@code variant}
   *  Tensor with {@code input_ragged_rank + 1} Tensors, corresponding to the splits and
   *  values of the decoded {@code RaggedTensor}. If {@code input_ragged_rank} is -1, then it is
   *  inferred as {@code output_ragged_rank} - {@code rank(encoded_ragged)}. See
   *  {@code RaggedTensorToVariant} for the corresponding encoding logic.
   *
   * @param <T> data type for {@code output_nested_splits} output
   * @param <U> data type for {@code output_dense_values} output
   * @param encodedRagged A {@code variant} Tensor containing encoded {@code RaggedTensor}s.
   * @param inputRaggedRank The ragged rank of each encoded {@code RaggedTensor} component in the input. If set to
   *  -1, this is inferred as {@code output_ragged_rank} - {@code rank(encoded_ragged)}
   * @param outputRaggedRank The expected ragged rank of the output {@code RaggedTensor}. The following must hold:
   *  {@code output_ragged_rank = rank(encoded_ragged) + input_ragged_rank}.
   * @param Tvalues The value of the Tvalues attribute
   * @param <U> data type for {@code RaggedTensorFromVariant} output and operands
   * @return a new instance of RaggedTensorFromVariant, with default output types
   */
  public <U extends TType> RaggedTensorFromVariant<TInt64, U> raggedTensorFromVariant(
      Operand<? extends TType> encodedRagged, Long inputRaggedRank, Long outputRaggedRank,
      Class<U> Tvalues) {
    return RaggedTensorFromVariant.create(scope, encodedRagged, inputRaggedRank, outputRaggedRank, Tvalues);
  }

  /**
   * Decodes a {@code variant} Tensor into a {@code RaggedTensor}.
   *  Decodes the given {@code variant} Tensor and returns a {@code RaggedTensor}. The input
   *  could be a scalar, meaning it encodes a single {@code RaggedTensor} with ragged_rank
   *  {@code output_ragged_rank}. It could also have an arbitrary rank, in which case each
   *  element is decoded into a {@code RaggedTensor} with ragged_rank {@code input_ragged_rank}
   *  and these are then stacked according to the input shape to output a single
   *  {@code RaggedTensor} with ragged_rank {@code output_ragged_rank}. Each {@code variant} element in
   *  the input Tensor is decoded by retrieving from the element a 1-D {@code variant}
   *  Tensor with {@code input_ragged_rank + 1} Tensors, corresponding to the splits and
   *  values of the decoded {@code RaggedTensor}. If {@code input_ragged_rank} is -1, then it is
   *  inferred as {@code output_ragged_rank} - {@code rank(encoded_ragged)}. See
   *  {@code RaggedTensorToVariant} for the corresponding encoding logic.
   *
   * @param <T> data type for {@code output_nested_splits} output
   * @param <U> data type for {@code output_dense_values} output
   * @param encodedRagged A {@code variant} Tensor containing encoded {@code RaggedTensor}s.
   * @param inputRaggedRank The ragged rank of each encoded {@code RaggedTensor} component in the input. If set to
   *  -1, this is inferred as {@code output_ragged_rank} - {@code rank(encoded_ragged)}
   * @param outputRaggedRank The expected ragged rank of the output {@code RaggedTensor}. The following must hold:
   *  {@code output_ragged_rank = rank(encoded_ragged) + input_ragged_rank}.
   * @param Tvalues The value of the Tvalues attribute
   * @param Tsplits The value of the Tsplits attribute
   * @param <T> data type for {@code RaggedTensorFromVariant} output and operands
   * @param <U> data type for {@code RaggedTensorFromVariant} output and operands
   * @return a new instance of RaggedTensorFromVariant
   */
  public <T extends TNumber, U extends TType> RaggedTensorFromVariant<T, U> raggedTensorFromVariant(
      Operand<? extends TType> encodedRagged, Long inputRaggedRank, Long outputRaggedRank,
      Class<U> Tvalues, Class<T> Tsplits) {
    return RaggedTensorFromVariant.create(scope, encodedRagged, inputRaggedRank, outputRaggedRank, Tvalues, Tsplits);
  }

  /**
   * Converts a {@code RaggedTensor} into a {@code SparseTensor} with the same values.
   *  input=ragged.from_nested_row_splits(rt_dense_values, rt_nested_splits)
   *  output=SparseTensor(indices=sparse_indices, values=sparse_values,
   *  dense_shape=sparse_dense_shape)
   *
   * @param <U> data type for {@code sparse_values} output
   * @param rtNestedSplits The {@code row_splits} for the {@code RaggedTensor}.
   * @param rtDenseValues The {@code flat_values} for the {@code RaggedTensor}.
   * @param <U> data type for {@code RaggedTensorToSparse} output and operands
   * @return a new instance of RaggedTensorToSparse
   */
  public <U extends TType> RaggedTensorToSparse<U> raggedTensorToSparse(
      Iterable<Operand<? extends TNumber>> rtNestedSplits, Operand<U> rtDenseValues) {
    return RaggedTensorToSparse.create(scope, rtNestedSplits, rtDenseValues);
  }

  /**
   * Create a dense tensor from a ragged tensor, possibly altering its shape.
   *  The {@code ragged_to_dense} op creates a dense tensor from a list of row partition
   *  tensors, a value vector, and default values. If the shape is unspecified, the
   *  minimal shape required to contain all the elements in the ragged tensor (the
   *  natural shape) will be used. If some dimensions are left unspecified, then the
   *  size of the natural shape is used in that dimension.
   *  <p>The default_value will be broadcast to the output shape. After that, the values
   *  from the ragged tensor overwrite the default values. Note that the default_value
   *  must have less dimensions than the value.
   *  <p>The row partition tensors are in the order of the dimensions.
   *  At present, the types can be:
   *  <ul>
   *  <li>&quot;ROW_SPLITS&quot;: the row_splits tensor from the ragged tensor.</li>
   *  <li>&quot;VALUE_ROWIDS&quot;: the value_rowids tensor from the ragged tensor.</li>
   *  <li>&quot;FIRST_DIM_SIZE&quot;: if value_rowids is used for the first dimension, then it
   *  is preceded by &quot;FIRST_DIM_SIZE&quot;.</li>
   *  </ul>
   *
   * @param <U> data type for {@code result} output
   * @param shape The desired shape of the output tensor. If left unspecified (empty),
   *  the minimal shape required to contain all the elements in the ragged tensor
   *  (the natural shape) will be used. If some dimensions are left unspecified, then
   *  the size of the natural shape is used in that dimension.
   *  <p>Note that dense dimensions cannot be modified by the shape argument. Trying to
   *  change the size of a dense dimension will cause the op to fail.
   *  Examples:
   *  natural shape: [4, 5, 6]
   *  shape: -1
   *  output shape: [4, 5, 6]
   *  <p>natural shape: [4, 5, 6]
   *  shape: [3, -1, 2]
   *  output shape: [3, 5, 2]
   *  <p>natural shape: [4, 5, 6]
   *  shape: [3, 7, 2]
   *  output shape: [3, 7, 2]
   * @param values A 1D tensor representing the values of the ragged tensor.
   * @param defaultValue The default_value when the shape is larger than the ragged tensor. The
   *  default_value is broadcast until it is the shape of the output tensor, and
   *  then overwritten by values in the ragged tensor. The default value must be
   *  compatible with this broadcast operation, and must have fewer dimensions than
   *  the value tensor.
   * @param rowPartitionTensors The rowPartitionTensors value
   * @param rowPartitionTypes The types of the row partition tensors. At present, these can be:
   *  <ul>
   *  <li>&quot;ROW_SPLITS&quot;: the row_splits tensor from the ragged tensor.</li>
   *  <li>&quot;VALUE_ROWIDS&quot;: the value_rowids tensor from the ragged tensor.</li>
   *  <li>&quot;FIRST_DIM_SIZE&quot;: if value_rowids is used for the first dimension, then it
   *  is preceeded by &quot;FIRST_DIM_SIZE&quot;.
   *  The tensors are in the order of the dimensions.</li>
   *  </ul>
   * @param <U> data type for {@code RaggedTensorToTensor} output and operands
   * @return a new instance of RaggedTensorToTensor
   */
  public <U extends TType> RaggedTensorToTensor<U> raggedTensorToTensor(
      Operand<? extends TNumber> shape, Operand<U> values, Operand<U> defaultValue,
      Iterable<Operand<? extends TNumber>> rowPartitionTensors, List<String> rowPartitionTypes) {
    return RaggedTensorToTensor.create(scope, shape, values, defaultValue, rowPartitionTensors, rowPartitionTypes);
  }

  /**
   * Encodes a {@code RaggedTensor} into a {@code variant} Tensor.
   *  Encodes the given {@code RaggedTensor} and returns a {@code variant} Tensor. If
   *  {@code batched_input} is True, then input {@code RaggedTensor} is unbatched along the
   *  zero-th dimension, each component {@code RaggedTensor} is encoded into a scalar
   *  {@code variant} Tensor, and these are stacked to return a 1-D {@code variant} Tensor.
   *  If {@code batched_input} is False, then the input {@code RaggedTensor} is encoded as is and
   *  a scalar {@code variant} Tensor is returned. A {@code RaggedTensor} is encoded by first
   *  creating a 1-D {@code variant} Tensor with {@code ragged_rank + 1} elements, containing the
   *  splits and values Tensors of the {@code RaggedTensor}. Then the 1-D {@code variant} Tensor
   *  is wrapped in a scalar {@code variant} Tensor. See {@code RaggedTensorFromVariant} for the
   *  corresponding decoding logic.
   *
   * @param rtNestedSplits A list of one or more Tensors representing the splits of the input
   *  {@code RaggedTensor}.
   * @param rtDenseValues A Tensor representing the values of the input {@code RaggedTensor}.
   * @param batchedInput A {@code bool} denoting whether the input is a batched {@code RaggedTensor}.
   * @return a new instance of RaggedTensorToVariant
   */
  public RaggedTensorToVariant raggedTensorToVariant(
      Iterable<Operand<? extends TNumber>> rtNestedSplits, Operand<? extends TType> rtDenseValues,
      Boolean batchedInput) {
    return RaggedTensorToVariant.create(scope, rtNestedSplits, rtDenseValues, batchedInput);
  }

  /**
   * Helper used to compute the gradient for {@code RaggedTensorToVariant}.
   *  Computes the gradient for the dense_values input to the RaggedTensorToVariant
   *  op, given the variant-encoded ragged gradients of the outputs, along with
   *  the outer row-splits and the shape of the dense-values that were provided as
   *  inputs to the RaggedTensorToVariant op.
   *
   * @param <U> data type for {@code dense_values_grad} output
   * @param encodedRaggedGrad A {@code variant} Tensor containing encoded {@code RaggedTensor} gradients.
   * @param rowSplits Outermost row-splits that were used as input to the RaggedTensorToVariant op.
   * @param denseValuesShape Shape of the dense_values that was used as an input to the
   *  RaggedTensorToVariant op.
   * @param Tvalues The value of the Tvalues attribute
   * @param <U> data type for {@code RaggedTensorToVariantGradient} output and operands
   * @return a new instance of RaggedTensorToVariantGradient
   */
  public <U extends TType> RaggedTensorToVariantGradient<U> raggedTensorToVariantGradient(
      Operand<? extends TType> encodedRaggedGrad, Operand<? extends TNumber> rowSplits,
      Operand<TInt32> denseValuesShape, Class<U> Tvalues) {
    return RaggedTensorToVariantGradient.create(scope, encodedRaggedGrad, rowSplits, denseValuesShape, Tvalues);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
