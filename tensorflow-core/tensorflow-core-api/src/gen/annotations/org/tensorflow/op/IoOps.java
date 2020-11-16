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
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.io.DecodeBase64;
import org.tensorflow.op.io.DecodeCompressed;
import org.tensorflow.op.io.DecodeCsv;
import org.tensorflow.op.io.DecodeJsonExample;
import org.tensorflow.op.io.DecodePaddedRaw;
import org.tensorflow.op.io.DecodeRaw;
import org.tensorflow.op.io.DeserializeManySparse;
import org.tensorflow.op.io.EncodeBase64;
import org.tensorflow.op.io.FifoQueue;
import org.tensorflow.op.io.FixedLengthRecordReader;
import org.tensorflow.op.io.IdentityReader;
import org.tensorflow.op.io.LmdbReader;
import org.tensorflow.op.io.MatchingFiles;
import org.tensorflow.op.io.PaddingFifoQueue;
import org.tensorflow.op.io.ParseExample;
import org.tensorflow.op.io.ParseSequenceExample;
import org.tensorflow.op.io.ParseSingleExample;
import org.tensorflow.op.io.ParseSingleSequenceExample;
import org.tensorflow.op.io.ParseTensor;
import org.tensorflow.op.io.PriorityQueue;
import org.tensorflow.op.io.QueueClose;
import org.tensorflow.op.io.QueueDequeue;
import org.tensorflow.op.io.QueueDequeueMany;
import org.tensorflow.op.io.QueueDequeueUpTo;
import org.tensorflow.op.io.QueueEnqueue;
import org.tensorflow.op.io.QueueEnqueueMany;
import org.tensorflow.op.io.QueueIsClosed;
import org.tensorflow.op.io.QueueSize;
import org.tensorflow.op.io.RandomShuffleQueue;
import org.tensorflow.op.io.ReadFile;
import org.tensorflow.op.io.ReaderNumRecordsProduced;
import org.tensorflow.op.io.ReaderNumWorkUnitsCompleted;
import org.tensorflow.op.io.ReaderRead;
import org.tensorflow.op.io.ReaderReadUpTo;
import org.tensorflow.op.io.ReaderReset;
import org.tensorflow.op.io.ReaderRestoreState;
import org.tensorflow.op.io.ReaderSerializeState;
import org.tensorflow.op.io.SerializeManySparse;
import org.tensorflow.op.io.SerializeSparse;
import org.tensorflow.op.io.SerializeTensor;
import org.tensorflow.op.io.ShardedFilename;
import org.tensorflow.op.io.ShardedFilespec;
import org.tensorflow.op.io.TextLineReader;
import org.tensorflow.op.io.TfRecordReader;
import org.tensorflow.op.io.WholeFileReader;
import org.tensorflow.op.io.WriteFile;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code io} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class IoOps {
  private final Scope scope;

  private final Ops ops;

  IoOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Decode web-safe base64-encoded strings.
   *  <p>
   *  Input may or may not have padding at the end. See EncodeBase64 for padding.
   *  Web-safe means that input must use - and _ instead of + and /.
   *
   * @param input Base64 strings to decode.
   * @return a new instance of DecodeBase64
   */
  public DecodeBase64 decodeBase64(Operand<TString> input) {
    return DecodeBase64.create(scope, input);
  }

  /**
   * Decompress strings.
   *  <p>
   *  This op decompresses each element of the `bytes` input `Tensor`, which
   *  is assumed to be compressed using the given `compression_type`.
   *  <p>
   *  The `output` is a string `Tensor` of the same shape as `bytes`,
   *  each element containing the decompressed data from the corresponding
   *  element in `bytes`.
   *
   * @param bytes A Tensor of string which is compressed.
   * @param options carries optional attributes values
   * @return a new instance of DecodeCompressed
   */
  public DecodeCompressed decodeCompressed(Operand<TString> bytes,
      DecodeCompressed.Options... options) {
    return DecodeCompressed.create(scope, bytes, options);
  }

  /**
   * Convert CSV records to tensors. Each column maps to one tensor.
   *  <p>
   *  RFC 4180 format is expected for the CSV records.
   *  (https://tools.ietf.org/html/rfc4180)
   *  Note that we allow leading and trailing spaces with int or float field.
   *
   * @param records Each string is a record/row in the csv and all records should have
   *  the same format.
   * @param recordDefaults One tensor per column of the input record, with either a
   *  scalar default value for that column or an empty vector if the column is
   *  required.
   * @param options carries optional attributes values
   * @return a new instance of DecodeCsv
   */
  public DecodeCsv decodeCsv(Operand<TString> records, Iterable<Operand<?>> recordDefaults,
      DecodeCsv.Options... options) {
    return DecodeCsv.create(scope, records, recordDefaults, options);
  }

  /**
   * Convert JSON-encoded Example records to binary protocol buffer strings.
   *  <p>
   *  This op translates a tensor containing Example records, encoded using
   *  the [standard JSON
   *  mapping](https://developers.google.com/protocol-buffers/docs/proto3#json),
   *  into a tensor containing the same records encoded as binary protocol
   *  buffers. The resulting tensor can then be fed to any of the other
   *  Example-parsing ops.
   *
   * @param jsonExamples Each string is a JSON object serialized according to the JSON
   *  mapping of the Example proto.
   * @return a new instance of DecodeJsonExample
   */
  public DecodeJsonExample decodeJsonExample(Operand<TString> jsonExamples) {
    return DecodeJsonExample.create(scope, jsonExamples);
  }

  /**
   * Reinterpret the bytes of a string as a vector of numbers.
   *
   * @param <T> data type for {@code output()} output
   * @param inputBytes Tensor of string to be decoded.
   * @param fixedLength Length in bytes for each element of the decoded output. Must be a multiple
   *  of the size of the output type.
   * @param outType
   * @param options carries optional attributes values
   * @return a new instance of DecodePaddedRaw
   */
  public <T extends TNumber> DecodePaddedRaw<T> decodePaddedRaw(Operand<TString> inputBytes,
      Operand<TInt32> fixedLength, DataType<T> outType, DecodePaddedRaw.Options... options) {
    return DecodePaddedRaw.create(scope, inputBytes, fixedLength, outType, options);
  }

  /**
   * Reinterpret the bytes of a string as a vector of numbers.
   *
   * @param <T> data type for {@code output()} output
   * @param bytes All the elements must have the same length.
   * @param outType
   * @param options carries optional attributes values
   * @return a new instance of DecodeRaw
   */
  public <T extends TType> DecodeRaw<T> decodeRaw(Operand<TString> bytes, DataType<T> outType,
      DecodeRaw.Options... options) {
    return DecodeRaw.create(scope, bytes, outType, options);
  }

  /**
   * Deserialize and concatenate `SparseTensors` from a serialized minibatch.
   *  <p>
   *  The input `serialized_sparse` must be a string matrix of shape `[N x 3]` where
   *  `N` is the minibatch size and the rows correspond to packed outputs of
   *  `SerializeSparse`.  The ranks of the original `SparseTensor` objects
   *  must all match.  When the final `SparseTensor` is created, it has rank one
   *  higher than the ranks of the incoming `SparseTensor` objects
   *  (they have been concatenated along a new row dimension).
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
   * @param <T> data type for {@code sparseValues()} output
   * @param serializedSparse 2-D, The `N` serialized `SparseTensor` objects.
   *  Must have 3 columns.
   * @param dtype The `dtype` of the serialized `SparseTensor` objects.
   * @return a new instance of DeserializeManySparse
   */
  public <T extends TType> DeserializeManySparse<T> deserializeManySparse(
      Operand<TString> serializedSparse, DataType<T> dtype) {
    return DeserializeManySparse.create(scope, serializedSparse, dtype);
  }

  /**
   * Encode strings into web-safe base64 format.
   *  <p>
   *  Refer to the following article for more information on base64 format:
   *  en.wikipedia.org/wiki/Base64. Base64 strings may have padding with '=' at the
   *  end so that the encoded has length multiple of 4. See Padding section of the
   *  link above.
   *  <p>
   *  Web-safe means that the encoder uses - and _ instead of + and /.
   *
   * @param input Strings to be encoded.
   * @param options carries optional attributes values
   * @return a new instance of EncodeBase64
   */
  public EncodeBase64 encodeBase64(Operand<TString> input, EncodeBase64.Options... options) {
    return EncodeBase64.create(scope, input, options);
  }

  /**
   * A queue that produces elements in first-in first-out order.
   *
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attributes values
   * @return a new instance of FifoQueue
   */
  public FifoQueue fifoQueue(List<DataType<?>> componentTypes, FifoQueue.Options... options) {
    return FifoQueue.create(scope, componentTypes, options);
  }

  /**
   * A Reader that outputs fixed-length records from a file.
   *
   * @param recordBytes Number of bytes in the record.
   * @param options carries optional attributes values
   * @return a new instance of FixedLengthRecordReader
   */
  public FixedLengthRecordReader fixedLengthRecordReader(Long recordBytes,
      FixedLengthRecordReader.Options... options) {
    return FixedLengthRecordReader.create(scope, recordBytes, options);
  }

  /**
   * A Reader that outputs the queued work as both the key and value.
   *  <p>
   *  To use, enqueue strings in a Queue.  ReaderRead will take the front
   *  work string and output (work, work).
   *
   * @param options carries optional attributes values
   * @return a new instance of IdentityReader
   */
  public IdentityReader identityReader(IdentityReader.Options... options) {
    return IdentityReader.create(scope, options);
  }

  /**
   * A Reader that outputs the records from a LMDB file.
   *
   * @param options carries optional attributes values
   * @return a new instance of LmdbReader
   */
  public LmdbReader lmdbReader(LmdbReader.Options... options) {
    return LmdbReader.create(scope, options);
  }

  /**
   * Returns the set of files matching one or more glob patterns.
   *  <p>
   *  Note that this routine only supports wildcard characters in the
   *  basename portion of the pattern, not in the directory portion.
   *  Note also that the order of filenames returned is deterministic.
   *
   * @param pattern Shell wildcard pattern(s). Scalar or vector of type string.
   * @return a new instance of MatchingFiles
   */
  public MatchingFiles matchingFiles(Operand<TString> pattern) {
    return MatchingFiles.create(scope, pattern);
  }

  /**
   * A queue that produces elements in first-in first-out order.
   *  <p>
   *  Variable-size shapes are allowed by setting the corresponding shape dimensions
   *  to 0 in the shape attr.  In this case DequeueMany will pad up to the maximum
   *  size of any given element in the minibatch.  See below for details.
   *
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attributes values
   * @return a new instance of PaddingFifoQueue
   */
  public PaddingFifoQueue paddingFifoQueue(List<DataType<?>> componentTypes,
      PaddingFifoQueue.Options... options) {
    return PaddingFifoQueue.create(scope, componentTypes, options);
  }

  /**
   * Transforms a vector of tf.Example protos (as strings) into typed tensors.
   *
   * @param serialized A scalar or vector containing binary serialized Example protos.
   * @param names A tensor containing the names of the serialized protos.
   *  Corresponds 1:1 with the `serialized` tensor.
   *  May contain, for example, table key (descriptive) names for the
   *  corresponding serialized protos.  These are purely useful for debugging
   *  purposes, and the presence of values here has no effect on the output.
   *  May also be an empty vector if no names are available.
   *  If non-empty, this tensor must have the same shape as "serialized".
   * @param sparseKeys Vector of strings.
   *  The keys expected in the Examples' features associated with sparse values.
   * @param denseKeys Vector of strings.
   *  The keys expected in the Examples' features associated with dense values.
   * @param raggedKeys Vector of strings.
   *  The keys expected in the Examples' features associated with ragged values.
   * @param denseDefaults A list of Tensors (some may be empty).  Corresponds 1:1 with `dense_keys`.
   *  dense_defaults[j] provides default values
   *  when the example's feature_map lacks dense_key[j].  If an empty Tensor is
   *  provided for dense_defaults[j], then the Feature dense_keys[j] is required.
   *  The input type is inferred from dense_defaults[j], even when it's empty.
   *  If dense_defaults[j] is not empty, and dense_shapes[j] is fully defined,
   *  then the shape of dense_defaults[j] must match that of dense_shapes[j].
   *  If dense_shapes[j] has an undefined major dimension (variable strides dense
   *  feature), dense_defaults[j] must contain a single element:
   *  the padding element.
   * @param numSparse The number of sparse keys.
   * @param sparseTypes A list of `num_sparse` types; the data types of data in each Feature
   *  given in sparse_keys.
   *  Currently the ParseExample supports DT_FLOAT (FloatList),
   *  DT_INT64 (Int64List), and DT_STRING (BytesList).
   * @param raggedValueTypes A list of `num_ragged` types; the data types of data in each Feature
   *  given in ragged_keys (where `num_ragged = sparse_keys.size()`).
   *  Currently the ParseExample supports DT_FLOAT (FloatList),
   *  DT_INT64 (Int64List), and DT_STRING (BytesList).
   * @param raggedSplitTypes A list of `num_ragged` types; the data types of row_splits in each Feature
   *  given in ragged_keys (where `num_ragged = sparse_keys.size()`).
   *  May be DT_INT32 or DT_INT64.
   * @param denseShapes A list of `num_dense` shapes; the shapes of data in each Feature
   *  given in dense_keys (where `num_dense = dense_keys.size()`).
   *  The number of elements in the Feature corresponding to dense_key[j]
   *  must always equal dense_shapes[j].NumEntries().
   *  If dense_shapes[j] == (D0, D1, ..., DN) then the shape of output
   *  Tensor dense_values[j] will be (|serialized|, D0, D1, ..., DN):
   *  The dense outputs are just the inputs row-stacked by batch.
   *  This works for dense_shapes[j] = (-1, D1, ..., DN).  In this case
   *  the shape of the output Tensor dense_values[j] will be
   *  (|serialized|, M, D1, .., DN), where M is the maximum number of blocks
   *  of elements of length D1 * .... * DN, across all minibatch entries
   *  in the input.  Any minibatch entry with less than M blocks of elements of
   *  length D1 * ... * DN will be padded with the corresponding default_value
   *  scalar element along the second dimension.
   * @return a new instance of ParseExample
   */
  public ParseExample parseExample(Operand<TString> serialized, Operand<TString> names,
      Operand<TString> sparseKeys, Operand<TString> denseKeys, Operand<TString> raggedKeys,
      Iterable<Operand<?>> denseDefaults, Long numSparse, List<DataType<?>> sparseTypes,
      List<DataType<?>> raggedValueTypes, List<DataType<?>> raggedSplitTypes,
      List<Shape> denseShapes) {
    return ParseExample.create(scope, serialized, names, sparseKeys, denseKeys, raggedKeys, denseDefaults, numSparse, sparseTypes, raggedValueTypes, raggedSplitTypes, denseShapes);
  }

  /**
   * Transforms a vector of tf.io.SequenceExample protos (as strings) into
   *  typed tensors.
   *
   * @param serialized A scalar or vector containing binary serialized SequenceExample protos.
   * @param debugName A scalar or vector containing the names of the serialized protos.
   *  May contain, for example, table key (descriptive) name for the
   *  corresponding serialized proto.  This is purely useful for debugging
   *  purposes, and the presence of values here has no effect on the output.
   *  May also be an empty vector if no name is available.
   * @param contextSparseKeys The keys expected in the Examples' features associated with context_sparse
   *  values.
   * @param contextDenseKeys The keys expected in the SequenceExamples' context features associated with
   *  dense values.
   * @param contextRaggedKeys The keys expected in the Examples' features associated with context_ragged
   *  values.
   * @param featureListSparseKeys The keys expected in the FeatureLists associated with sparse values.
   * @param featureListDenseKeys The keys expected in the SequenceExamples' feature_lists associated
   *  with lists of dense values.
   * @param featureListRaggedKeys The keys expected in the FeatureLists associated with ragged values.
   * @param featureListDenseMissingAssumedEmpty A vector corresponding 1:1 with feature_list_dense_keys, indicating which
   *  features may be missing from the SequenceExamples.  If the associated
   *  FeatureList is missing, it is treated as empty.
   * @param contextDenseDefaults A list of Ncontext_dense Tensors (some may be empty).
   *  context_dense_defaults[j] provides default values
   *  when the SequenceExample's context map lacks context_dense_key[j].
   *  If an empty Tensor is provided for context_dense_defaults[j],
   *  then the Feature context_dense_keys[j] is required.
   *  The input type is inferred from context_dense_defaults[j], even when it's
   *  empty.  If context_dense_defaults[j] is not empty, its shape must match
   *  context_dense_shapes[j].
   * @param contextSparseTypes A list of Ncontext_sparse types; the data types of data in
   *  each context Feature given in context_sparse_keys.
   *  Currently the ParseSingleSequenceExample supports DT_FLOAT (FloatList),
   *  DT_INT64 (Int64List), and DT_STRING (BytesList).
   * @param contextRaggedValueTypes RaggedTensor.value dtypes for the ragged context features.
   * @param contextRaggedSplitTypes RaggedTensor.row_split dtypes for the ragged context features.
   * @param featureListDenseTypes
   * @param featureListSparseTypes A list of Nfeature_list_sparse types; the data types
   *  of data in each FeatureList given in feature_list_sparse_keys.
   *  Currently the ParseSingleSequenceExample supports DT_FLOAT (FloatList),
   *  DT_INT64 (Int64List), and DT_STRING (BytesList).
   * @param featureListRaggedValueTypes RaggedTensor.value dtypes for the ragged FeatureList features.
   * @param featureListRaggedSplitTypes RaggedTensor.row_split dtypes for the ragged FeatureList features.
   * @param options carries optional attributes values
   * @return a new instance of ParseSequenceExample
   */
  public ParseSequenceExample parseSequenceExample(Operand<TString> serialized,
      Operand<TString> debugName, Operand<TString> contextSparseKeys,
      Operand<TString> contextDenseKeys, Operand<TString> contextRaggedKeys,
      Operand<TString> featureListSparseKeys, Operand<TString> featureListDenseKeys,
      Operand<TString> featureListRaggedKeys, Operand<TBool> featureListDenseMissingAssumedEmpty,
      Iterable<Operand<?>> contextDenseDefaults, List<DataType<?>> contextSparseTypes,
      List<DataType<?>> contextRaggedValueTypes, List<DataType<?>> contextRaggedSplitTypes,
      List<DataType<?>> featureListDenseTypes, List<DataType<?>> featureListSparseTypes,
      List<DataType<?>> featureListRaggedValueTypes, List<DataType<?>> featureListRaggedSplitTypes,
      ParseSequenceExample.Options... options) {
    return ParseSequenceExample.create(scope, serialized, debugName, contextSparseKeys, contextDenseKeys, contextRaggedKeys, featureListSparseKeys, featureListDenseKeys, featureListRaggedKeys, featureListDenseMissingAssumedEmpty, contextDenseDefaults, contextSparseTypes, contextRaggedValueTypes, contextRaggedSplitTypes, featureListDenseTypes, featureListSparseTypes, featureListRaggedValueTypes, featureListRaggedSplitTypes, options);
  }

  /**
   * Transforms a tf.Example proto (as a string) into typed tensors.
   *
   * @param serialized A vector containing a batch of binary serialized Example protos.
   * @param denseDefaults A list of Tensors (some may be empty), whose length matches
   *  the length of `dense_keys`. dense_defaults[j] provides default values
   *  when the example's feature_map lacks dense_key[j].  If an empty Tensor is
   *  provided for dense_defaults[j], then the Feature dense_keys[j] is required.
   *  The input type is inferred from dense_defaults[j], even when it's empty.
   *  If dense_defaults[j] is not empty, and dense_shapes[j] is fully defined,
   *  then the shape of dense_defaults[j] must match that of dense_shapes[j].
   *  If dense_shapes[j] has an undefined major dimension (variable strides dense
   *  feature), dense_defaults[j] must contain a single element:
   *  the padding element.
   * @param numSparse The number of sparse features to be parsed from the example. This
   *  must match the lengths of `sparse_keys` and `sparse_types`.
   * @param sparseKeys A list of `num_sparse` strings.
   *  The keys expected in the Examples' features associated with sparse values.
   * @param denseKeys The keys expected in the Examples' features associated with dense
   *  values.
   * @param sparseTypes A list of `num_sparse` types; the data types of data in each
   *  Feature given in sparse_keys.
   *  Currently the ParseSingleExample op supports DT_FLOAT (FloatList),
   *  DT_INT64 (Int64List), and DT_STRING (BytesList).
   * @param denseShapes The shapes of data in each Feature given in dense_keys.
   *  The length of this list must match the length of `dense_keys`.  The
   *  number of elements in the Feature corresponding to dense_key[j] must
   *  always equal dense_shapes[j].NumEntries().  If dense_shapes[j] ==
   *  (D0, D1, ..., DN) then the shape of output Tensor dense_values[j]
   *  will be (D0, D1, ..., DN): In the case dense_shapes[j] = (-1, D1,
   *  ..., DN), the shape of the output Tensor dense_values[j] will be (M,
   *  D1, .., DN), where M is the number of blocks of elements of length
   *  D1 * .... * DN, in the input.
   * @return a new instance of ParseSingleExample
   */
  public ParseSingleExample parseSingleExample(Operand<TString> serialized,
      Iterable<Operand<?>> denseDefaults, Long numSparse, List<String> sparseKeys,
      List<String> denseKeys, List<DataType<?>> sparseTypes, List<Shape> denseShapes) {
    return ParseSingleExample.create(scope, serialized, denseDefaults, numSparse, sparseKeys, denseKeys, sparseTypes, denseShapes);
  }

  /**
   * Transforms a scalar brain.SequenceExample proto (as strings) into typed tensors.
   *
   * @param serialized A scalar containing a binary serialized SequenceExample proto.
   * @param featureListDenseMissingAssumedEmpty A vector listing the
   *  FeatureList keys which may be missing from the SequenceExample.  If the
   *  associated FeatureList is missing, it is treated as empty.  By default,
   *  any FeatureList not listed in this vector must exist in the SequenceExample.
   * @param contextSparseKeys A list of Ncontext_sparse string Tensors (scalars).
   *  The keys expected in the Examples' features associated with context_sparse
   *  values.
   * @param contextDenseKeys A list of Ncontext_dense string Tensors (scalars).
   *  The keys expected in the SequenceExamples' context features associated with
   *  dense values.
   * @param featureListSparseKeys A list of Nfeature_list_sparse string Tensors
   *  (scalars).  The keys expected in the FeatureLists associated with sparse
   *  values.
   * @param featureListDenseKeys A list of Nfeature_list_dense string Tensors (scalars).
   *  The keys expected in the SequenceExamples' feature_lists associated
   *  with lists of dense values.
   * @param contextDenseDefaults A list of Ncontext_dense Tensors (some may be empty).
   *  context_dense_defaults[j] provides default values
   *  when the SequenceExample's context map lacks context_dense_key[j].
   *  If an empty Tensor is provided for context_dense_defaults[j],
   *  then the Feature context_dense_keys[j] is required.
   *  The input type is inferred from context_dense_defaults[j], even when it's
   *  empty.  If context_dense_defaults[j] is not empty, its shape must match
   *  context_dense_shapes[j].
   * @param debugName A scalar containing the name of the serialized proto.
   *  May contain, for example, table key (descriptive) name for the
   *  corresponding serialized proto.  This is purely useful for debugging
   *  purposes, and the presence of values here has no effect on the output.
   *  May also be an empty scalar if no name is available.
   * @param contextSparseTypes A list of Ncontext_sparse types; the data types of data in
   *  each context Feature given in context_sparse_keys.
   *  Currently the ParseSingleSequenceExample supports DT_FLOAT (FloatList),
   *  DT_INT64 (Int64List), and DT_STRING (BytesList).
   * @param featureListDenseTypes
   * @param featureListSparseTypes A list of Nfeature_list_sparse types; the data types
   *  of data in each FeatureList given in feature_list_sparse_keys.
   *  Currently the ParseSingleSequenceExample supports DT_FLOAT (FloatList),
   *  DT_INT64 (Int64List), and DT_STRING (BytesList).
   * @param options carries optional attributes values
   * @return a new instance of ParseSingleSequenceExample
   */
  public ParseSingleSequenceExample parseSingleSequenceExample(Operand<TString> serialized,
      Operand<TString> featureListDenseMissingAssumedEmpty,
      Iterable<Operand<TString>> contextSparseKeys, Iterable<Operand<TString>> contextDenseKeys,
      Iterable<Operand<TString>> featureListSparseKeys,
      Iterable<Operand<TString>> featureListDenseKeys, Iterable<Operand<?>> contextDenseDefaults,
      Operand<TString> debugName, List<DataType<?>> contextSparseTypes,
      List<DataType<?>> featureListDenseTypes, List<DataType<?>> featureListSparseTypes,
      ParseSingleSequenceExample.Options... options) {
    return ParseSingleSequenceExample.create(scope, serialized, featureListDenseMissingAssumedEmpty, contextSparseKeys, contextDenseKeys, featureListSparseKeys, featureListDenseKeys, contextDenseDefaults, debugName, contextSparseTypes, featureListDenseTypes, featureListSparseTypes, options);
  }

  /**
   * Transforms a serialized tensorflow.TensorProto proto into a Tensor.
   *
   * @param <T> data type for {@code output()} output
   * @param serialized A scalar string containing a serialized TensorProto proto.
   * @param outType The type of the serialized tensor.  The provided type must match the
   *  type of the serialized tensor and no implicit conversion will take place.
   * @return a new instance of ParseTensor
   */
  public <T extends TType> ParseTensor<T> parseTensor(Operand<TString> serialized,
      DataType<T> outType) {
    return ParseTensor.create(scope, serialized, outType);
  }

  /**
   * A queue that produces elements sorted by the first component value.
   *  <p>
   *  Note that the PriorityQueue requires the first component of any element
   *  to be a scalar int64, in addition to the other elements declared by
   *  component_types.  Therefore calls to Enqueue and EnqueueMany (resp. Dequeue
   *  and DequeueMany) on a PriorityQueue will all require (resp. output) one extra
   *  entry in their input (resp. output) lists.
   *
   * @param componentTypes The type of each component in a value.
   * @param shapes The shape of each component in a value. The length of this attr must
   *  be either 0 or the same as the length of component_types. If the length of
   *  this attr is 0, the shapes of queue elements are not constrained, and
   *  only one element may be dequeued at a time.
   * @param options carries optional attributes values
   * @return a new instance of PriorityQueue
   */
  public PriorityQueue priorityQueue(List<DataType<?>> componentTypes, List<Shape> shapes,
      PriorityQueue.Options... options) {
    return PriorityQueue.create(scope, componentTypes, shapes, options);
  }

  /**
   * Closes the given queue.
   *  <p>
   *  This operation signals that no more elements will be enqueued in the
   *  given queue. Subsequent Enqueue(Many) operations will fail.
   *  Subsequent Dequeue(Many) operations will continue to succeed if
   *  sufficient elements remain in the queue. Subsequent Dequeue(Many)
   *  operations that would block will fail immediately.
   *
   * @param handle The handle to a queue.
   * @param options carries optional attributes values
   * @return a new instance of QueueClose
   */
  public QueueClose queueClose(Operand<?> handle, QueueClose.Options... options) {
    return QueueClose.create(scope, handle, options);
  }

  /**
   * Dequeues a tuple of one or more tensors from the given queue.
   *  <p>
   *  This operation has k outputs, where k is the number of components
   *  in the tuples stored in the given queue, and output i is the ith
   *  component of the dequeued tuple.
   *  <p>
   *  N.B. If the queue is empty, this operation will block until an element
   *  has been dequeued (or 'timeout_ms' elapses, if specified).
   *
   * @param handle The handle to a queue.
   * @param componentTypes The type of each component in a tuple.
   * @param options carries optional attributes values
   * @return a new instance of QueueDequeue
   */
  public QueueDequeue queueDequeue(Operand<?> handle, List<DataType<?>> componentTypes,
      QueueDequeue.Options... options) {
    return QueueDequeue.create(scope, handle, componentTypes, options);
  }

  /**
   * Dequeues `n` tuples of one or more tensors from the given queue.
   *  <p>
   *  If the queue is closed and there are fewer than `n` elements, then an
   *  OutOfRange error is returned.
   *  <p>
   *  This operation concatenates queue-element component tensors along the
   *  0th dimension to make a single component tensor.  All of the components
   *  in the dequeued tuple will have size `n` in the 0th dimension.
   *  <p>
   *  This operation has `k` outputs, where `k` is the number of components in
   *  the tuples stored in the given queue, and output `i` is the ith
   *  component of the dequeued tuple.
   *  <p>
   *  N.B. If the queue is empty, this operation will block until `n` elements
   *  have been dequeued (or 'timeout_ms' elapses, if specified).
   *
   * @param handle The handle to a queue.
   * @param n The number of tuples to dequeue.
   * @param componentTypes The type of each component in a tuple.
   * @param options carries optional attributes values
   * @return a new instance of QueueDequeueMany
   */
  public QueueDequeueMany queueDequeueMany(Operand<?> handle, Operand<TInt32> n,
      List<DataType<?>> componentTypes, QueueDequeueMany.Options... options) {
    return QueueDequeueMany.create(scope, handle, n, componentTypes, options);
  }

  /**
   * Dequeues `n` tuples of one or more tensors from the given queue.
   *  <p>
   *  This operation is not supported by all queues.  If a queue does not support
   *  DequeueUpTo, then an Unimplemented error is returned.
   *  <p>
   *  If the queue is closed and there are more than 0 but less than `n`
   *  elements remaining, then instead of returning an OutOfRange error like
   *  QueueDequeueMany, less than `n` elements are returned immediately.  If
   *  the queue is closed and there are 0 elements left in the queue, then
   *  an OutOfRange error is returned just like in QueueDequeueMany.
   *  Otherwise the behavior is identical to QueueDequeueMany:
   *  <p>
   *  This operation concatenates queue-element component tensors along the
   *  0th dimension to make a single component tensor.  All of the components
   *  in the dequeued tuple will have size n in the 0th dimension.
   *  <p>
   *  This operation has `k` outputs, where `k` is the number of components in
   *  the tuples stored in the given queue, and output `i` is the ith
   *  component of the dequeued tuple.
   *
   * @param handle The handle to a queue.
   * @param n The number of tuples to dequeue.
   * @param componentTypes The type of each component in a tuple.
   * @param options carries optional attributes values
   * @return a new instance of QueueDequeueUpTo
   */
  public QueueDequeueUpTo queueDequeueUpTo(Operand<?> handle, Operand<TInt32> n,
      List<DataType<?>> componentTypes, QueueDequeueUpTo.Options... options) {
    return QueueDequeueUpTo.create(scope, handle, n, componentTypes, options);
  }

  /**
   * Enqueues a tuple of one or more tensors in the given queue.
   *  <p>
   *  The components input has k elements, which correspond to the components of
   *  tuples stored in the given queue.
   *  <p>
   *  N.B. If the queue is full, this operation will block until the given
   *  element has been enqueued (or 'timeout_ms' elapses, if specified).
   *
   * @param handle The handle to a queue.
   * @param components One or more tensors from which the enqueued tensors should be taken.
   * @param options carries optional attributes values
   * @return a new instance of QueueEnqueue
   */
  public QueueEnqueue queueEnqueue(Operand<?> handle, Iterable<Operand<?>> components,
      QueueEnqueue.Options... options) {
    return QueueEnqueue.create(scope, handle, components, options);
  }

  /**
   * Enqueues zero or more tuples of one or more tensors in the given queue.
   *  <p>
   *  This operation slices each component tensor along the 0th dimension to
   *  make multiple queue elements. All of the tuple components must have the
   *  same size in the 0th dimension.
   *  <p>
   *  The components input has k elements, which correspond to the components of
   *  tuples stored in the given queue.
   *  <p>
   *  N.B. If the queue is full, this operation will block until the given
   *  elements have been enqueued (or 'timeout_ms' elapses, if specified).
   *
   * @param handle The handle to a queue.
   * @param components One or more tensors from which the enqueued tensors should
   *  be taken.
   * @param options carries optional attributes values
   * @return a new instance of QueueEnqueueMany
   */
  public QueueEnqueueMany queueEnqueueMany(Operand<?> handle, Iterable<Operand<?>> components,
      QueueEnqueueMany.Options... options) {
    return QueueEnqueueMany.create(scope, handle, components, options);
  }

  /**
   * Returns true if queue is closed.
   *  <p>
   *  This operation returns true if the queue is closed and false if the queue
   *  is open.
   *
   * @param handle The handle to a queue.
   * @return a new instance of QueueIsClosed
   */
  public QueueIsClosed queueIsClosed(Operand<?> handle) {
    return QueueIsClosed.create(scope, handle);
  }

  /**
   * Computes the number of elements in the given queue.
   *
   * @param handle The handle to a queue.
   * @return a new instance of QueueSize
   */
  public QueueSize queueSize(Operand<?> handle) {
    return QueueSize.create(scope, handle);
  }

  /**
   * A queue that randomizes the order of elements.
   *
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attributes values
   * @return a new instance of RandomShuffleQueue
   */
  public RandomShuffleQueue randomShuffleQueue(List<DataType<?>> componentTypes,
      RandomShuffleQueue.Options... options) {
    return RandomShuffleQueue.create(scope, componentTypes, options);
  }

  /**
   * Reads and outputs the entire contents of the input filename.
   *
   * @param filename
   * @return a new instance of ReadFile
   */
  public ReadFile readFile(Operand<TString> filename) {
    return ReadFile.create(scope, filename);
  }

  /**
   * Returns the number of records this Reader has produced.
   *  <p>
   *  This is the same as the number of ReaderRead executions that have
   *  succeeded.
   *
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderNumRecordsProduced
   */
  public ReaderNumRecordsProduced readerNumRecordsProduced(Operand<?> readerHandle) {
    return ReaderNumRecordsProduced.create(scope, readerHandle);
  }

  /**
   * Returns the number of work units this Reader has finished processing.
   *
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderNumWorkUnitsCompleted
   */
  public ReaderNumWorkUnitsCompleted readerNumWorkUnitsCompleted(Operand<?> readerHandle) {
    return ReaderNumWorkUnitsCompleted.create(scope, readerHandle);
  }

  /**
   * Returns the next record (key, value pair) produced by a Reader.
   *  <p>
   *  Will dequeue from the input queue if necessary (e.g. when the
   *  Reader needs to start reading from a new file since it has finished
   *  with the previous file).
   *
   * @param readerHandle Handle to a Reader.
   * @param queueHandle Handle to a Queue, with string work items.
   * @return a new instance of ReaderRead
   */
  public ReaderRead readerRead(Operand<?> readerHandle, Operand<?> queueHandle) {
    return ReaderRead.create(scope, readerHandle, queueHandle);
  }

  /**
   * Returns up to `num_records` (key, value) pairs produced by a Reader.
   *  <p>
   *  Will dequeue from the input queue if necessary (e.g. when the
   *  Reader needs to start reading from a new file since it has finished
   *  with the previous file).
   *  It may return less than `num_records` even before the last batch.
   *
   * @param readerHandle Handle to a `Reader`.
   * @param queueHandle Handle to a `Queue`, with string work items.
   * @param numRecords number of records to read from `Reader`.
   * @return a new instance of ReaderReadUpTo
   */
  public ReaderReadUpTo readerReadUpTo(Operand<?> readerHandle, Operand<?> queueHandle,
      Operand<TInt64> numRecords) {
    return ReaderReadUpTo.create(scope, readerHandle, queueHandle, numRecords);
  }

  /**
   * Restore a Reader to its initial clean state.
   *
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderReset
   */
  public ReaderReset readerReset(Operand<?> readerHandle) {
    return ReaderReset.create(scope, readerHandle);
  }

  /**
   * Restore a reader to a previously saved state.
   *  <p>
   *  Not all Readers support being restored, so this can produce an
   *  Unimplemented error.
   *
   * @param readerHandle Handle to a Reader.
   * @param state Result of a ReaderSerializeState of a Reader with type
   *  matching reader_handle.
   * @return a new instance of ReaderRestoreState
   */
  public ReaderRestoreState readerRestoreState(Operand<?> readerHandle, Operand<TString> state) {
    return ReaderRestoreState.create(scope, readerHandle, state);
  }

  /**
   * Produce a string tensor that encodes the state of a Reader.
   *  <p>
   *  Not all Readers support being serialized, so this can produce an
   *  Unimplemented error.
   *
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderSerializeState
   */
  public ReaderSerializeState readerSerializeState(Operand<?> readerHandle) {
    return ReaderSerializeState.create(scope, readerHandle);
  }

  /**
   * Serialize an `N`-minibatch `SparseTensor` into an `[N, 3]` `Tensor` object.
   *  <p>
   *  The `SparseTensor` must have rank `R` greater than 1, and the first dimension
   *  is treated as the minibatch dimension.  Elements of the `SparseTensor`
   *  must be sorted in increasing order of this first dimension.  The serialized
   *  `SparseTensor` objects going into each row of `serialized_sparse` will have
   *  rank `R-1`.
   *  <p>
   *  The minibatch size `N` is extracted from `sparse_shape[0]`.
   *
   * @param <U> data type for {@code serializedSparse()} output
   * @param sparseIndices 2-D.  The `indices` of the minibatch `SparseTensor`.
   * @param sparseValues 1-D.  The `values` of the minibatch `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the minibatch `SparseTensor`.
   * @return a new instance of SerializeManySparse
   */
  public <T extends TType> SerializeManySparse<TString> serializeManySparse(
      Operand<TInt64> sparseIndices, Operand<T> sparseValues, Operand<TInt64> sparseShape) {
    return SerializeManySparse.create(scope, sparseIndices, sparseValues, sparseShape);
  }

  /**
   * Serialize an `N`-minibatch `SparseTensor` into an `[N, 3]` `Tensor` object.
   *  <p>
   *  The `SparseTensor` must have rank `R` greater than 1, and the first dimension
   *  is treated as the minibatch dimension.  Elements of the `SparseTensor`
   *  must be sorted in increasing order of this first dimension.  The serialized
   *  `SparseTensor` objects going into each row of `serialized_sparse` will have
   *  rank `R-1`.
   *  <p>
   *  The minibatch size `N` is extracted from `sparse_shape[0]`.
   *
   * @param <U> data type for {@code serializedSparse()} output
   * @param sparseIndices 2-D.  The `indices` of the minibatch `SparseTensor`.
   * @param sparseValues 1-D.  The `values` of the minibatch `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the minibatch `SparseTensor`.
   * @param outType The `dtype` to use for serialization; the supported types are `string`
   *  (default) and `variant`.
   * @return a new instance of SerializeManySparse
   */
  public <U extends TType, T extends TType> SerializeManySparse<U> serializeManySparse(
      Operand<TInt64> sparseIndices, Operand<T> sparseValues, Operand<TInt64> sparseShape,
      DataType<U> outType) {
    return SerializeManySparse.create(scope, sparseIndices, sparseValues, sparseShape, outType);
  }

  /**
   * Serialize a `SparseTensor` into a `[3]` `Tensor` object.
   *
   * @param <U> data type for {@code serializedSparse()} output
   * @param sparseIndices 2-D.  The `indices` of the `SparseTensor`.
   * @param sparseValues 1-D.  The `values` of the `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the `SparseTensor`.
   * @return a new instance of SerializeSparse
   */
  public <T extends TType> SerializeSparse<TString> serializeSparse(Operand<TInt64> sparseIndices,
      Operand<T> sparseValues, Operand<TInt64> sparseShape) {
    return SerializeSparse.create(scope, sparseIndices, sparseValues, sparseShape);
  }

  /**
   * Serialize a `SparseTensor` into a `[3]` `Tensor` object.
   *
   * @param <U> data type for {@code serializedSparse()} output
   * @param sparseIndices 2-D.  The `indices` of the `SparseTensor`.
   * @param sparseValues 1-D.  The `values` of the `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the `SparseTensor`.
   * @param outType The `dtype` to use for serialization; the supported types are `string`
   *  (default) and `variant`.
   * @return a new instance of SerializeSparse
   */
  public <U extends TType, T extends TType> SerializeSparse<U> serializeSparse(
      Operand<TInt64> sparseIndices, Operand<T> sparseValues, Operand<TInt64> sparseShape,
      DataType<U> outType) {
    return SerializeSparse.create(scope, sparseIndices, sparseValues, sparseShape, outType);
  }

  /**
   * Transforms a Tensor into a serialized TensorProto proto.
   *
   * @param tensor A Tensor of type `T`.
   * @return a new instance of SerializeTensor
   */
  public <T extends TType> SerializeTensor serializeTensor(Operand<T> tensor) {
    return SerializeTensor.create(scope, tensor);
  }

  /**
   * Generate a sharded filename. The filename is printf formatted as
   *  <p>
   *     %s-%05d-of-%05d, basename, shard, num_shards.
   *
   * @param basename
   * @param shard
   * @param numShards
   * @return a new instance of ShardedFilename
   */
  public ShardedFilename shardedFilename(Operand<TString> basename, Operand<TInt32> shard,
      Operand<TInt32> numShards) {
    return ShardedFilename.create(scope, basename, shard, numShards);
  }

  /**
   * Generate a glob pattern matching all sharded file names.
   *
   * @param basename
   * @param numShards
   * @return a new instance of ShardedFilespec
   */
  public ShardedFilespec shardedFilespec(Operand<TString> basename, Operand<TInt32> numShards) {
    return ShardedFilespec.create(scope, basename, numShards);
  }

  /**
   * A Reader that outputs the lines of a file delimited by '\n'.
   *
   * @param options carries optional attributes values
   * @return a new instance of TextLineReader
   */
  public TextLineReader textLineReader(TextLineReader.Options... options) {
    return TextLineReader.create(scope, options);
  }

  /**
   * A Reader that outputs the records from a TensorFlow Records file.
   *
   * @param options carries optional attributes values
   * @return a new instance of TfRecordReader
   */
  public TfRecordReader tfRecordReader(TfRecordReader.Options... options) {
    return TfRecordReader.create(scope, options);
  }

  /**
   * A Reader that outputs the entire contents of a file as a value.
   *  <p>
   *  To use, enqueue filenames in a Queue.  The output of ReaderRead will
   *  be a filename (key) and the contents of that file (value).
   *
   * @param options carries optional attributes values
   * @return a new instance of WholeFileReader
   */
  public WholeFileReader wholeFileReader(WholeFileReader.Options... options) {
    return WholeFileReader.create(scope, options);
  }

  /**
   * Writes contents to the file at input filename. Creates file and recursively
   *  <p>
   *  creates directory if not existing.
   *
   * @param filename scalar. The name of the file to which we write the contents.
   * @param contents scalar. The content to be written to the output file.
   * @return a new instance of WriteFile
   */
  public WriteFile writeFile(Operand<TString> filename, Operand<TString> contents) {
    return WriteFile.create(scope, filename, contents);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
