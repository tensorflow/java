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
import org.tensorflow.op.io.DecodeBase64
import org.tensorflow.op.io.DecodeCompressed
import org.tensorflow.op.io.DecodeCsv
import org.tensorflow.op.io.DecodeJsonExample
import org.tensorflow.op.io.DecodePaddedRaw
import org.tensorflow.op.io.DecodeRaw
import org.tensorflow.op.io.DeserializeManySparse
import org.tensorflow.op.io.EncodeBase64
import org.tensorflow.op.io.FifoQueue
import org.tensorflow.op.io.FixedLengthRecordReader
import org.tensorflow.op.io.IdentityReader
import org.tensorflow.op.io.LmdbReader
import org.tensorflow.op.io.MatchingFiles
import org.tensorflow.op.io.PaddingFifoQueue
import org.tensorflow.op.io.ParseExample
import org.tensorflow.op.io.ParseSequenceExample
import org.tensorflow.op.io.ParseSingleExample
import org.tensorflow.op.io.ParseSingleSequenceExample
import org.tensorflow.op.io.ParseTensor
import org.tensorflow.op.io.PriorityQueue
import org.tensorflow.op.io.QueueClose
import org.tensorflow.op.io.QueueDequeue
import org.tensorflow.op.io.QueueDequeueMany
import org.tensorflow.op.io.QueueDequeueUpTo
import org.tensorflow.op.io.QueueEnqueue
import org.tensorflow.op.io.QueueEnqueueMany
import org.tensorflow.op.io.QueueIsClosed
import org.tensorflow.op.io.QueueSize
import org.tensorflow.op.io.RandomShuffleQueue
import org.tensorflow.op.io.ReadFile
import org.tensorflow.op.io.ReaderNumRecordsProduced
import org.tensorflow.op.io.ReaderNumWorkUnitsCompleted
import org.tensorflow.op.io.ReaderRead
import org.tensorflow.op.io.ReaderReadUpTo
import org.tensorflow.op.io.ReaderReset
import org.tensorflow.op.io.ReaderRestoreState
import org.tensorflow.op.io.ReaderSerializeState
import org.tensorflow.op.io.SerializeManySparse
import org.tensorflow.op.io.SerializeSparse
import org.tensorflow.op.io.SerializeTensor
import org.tensorflow.op.io.ShardedFilename
import org.tensorflow.op.io.ShardedFilespec
import org.tensorflow.op.io.TextLineReader
import org.tensorflow.op.io.TfRecordReader
import org.tensorflow.op.io.WholeFileReader
import org.tensorflow.op.io.WriteFile
import org.tensorflow.types.TBool
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.TString
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building `io` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class IoOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.IoOps = ops.java.io

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Decode web-safe base64-encoded strings.
     *  
     *  Input may or may not have padding at the end. See EncodeBase64 for padding.
     *  Web-safe means that input must use - and _ instead of + and /.
     * 
     * @param input Base64 strings to decode.
     * @return a new instance of DecodeBase64
     * @see org.tensorflow.op.IoOps.decodeBase64
     */
    public fun decodeBase64(input: Operand<TString>): DecodeBase64 = java.decodeBase64(    
        input
        )

    /**
     * Decompress strings.
     *  
     *  This op decompresses each element of the `bytes` input `Tensor`, which
     *  is assumed to be compressed using the given `compression_type`.
     *  
     *  The `output` is a string `Tensor` of the same shape as `bytes`,
     *  each element containing the decompressed data from the corresponding
     *  element in `bytes`.
     * 
     * @param bytes A Tensor of string which is compressed.
     * @param options carries optional attributes values
     * @return a new instance of DecodeCompressed
     * @see org.tensorflow.op.IoOps.decodeCompressed
     * @param compressionType A scalar containing either (i) the empty string (no
     *  compression), (ii) "ZLIB", or (iii) "GZIP".
     */
    public fun decodeCompressed(bytes: Operand<TString>, compressionType: String? = null):
            DecodeCompressed = java.decodeCompressed(    
        bytes,
        *listOfNotNull(
            compressionType?.let{ org.tensorflow.op.io.DecodeCompressed.compressionType(it) }
        ).toTypedArray()
        )

    /**
     * Convert CSV records to tensors. Each column maps to one tensor.
     *  
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
     * @see org.tensorflow.op.IoOps.decodeCsv
     * @param fieldDelim char delimiter to separate fields in a record.
     * @param useQuoteDelim If false, treats double quotation marks as regular
     *  characters inside of the string fields (ignoring RFC 4180, Section 2,
     *  Bullet 5).
     * @param naValue Additional string to recognize as NA/NaN.
     * @param selectCols @param selectCols
     */
    public fun decodeCsv(
        records: Operand<TString>,
        recordDefaults: Iterable<Operand<*>>,
        fieldDelim: String? = null,
        useQuoteDelim: Boolean? = null,
        naValue: String? = null,
        selectCols: List<Long>? = null
    ): DecodeCsv = java.decodeCsv(    
        records,
        recordDefaults,
        *listOfNotNull(
            fieldDelim?.let{ org.tensorflow.op.io.DecodeCsv.fieldDelim(it) },
            useQuoteDelim?.let{ org.tensorflow.op.io.DecodeCsv.useQuoteDelim(it) },
            naValue?.let{ org.tensorflow.op.io.DecodeCsv.naValue(it) },
            selectCols?.let{ org.tensorflow.op.io.DecodeCsv.selectCols(it) }
        ).toTypedArray()
        )

    /**
     * Convert JSON-encoded Example records to binary protocol buffer strings.
     *  
     *  This op translates a tensor containing Example records, encoded using
     *  the &#91;standard JSON
     *  mapping](https://developers.google.com/protocol-buffers/docs/proto3#json),
     *  into a tensor containing the same records encoded as binary protocol
     *  buffers. The resulting tensor can then be fed to any of the other
     *  Example-parsing ops.
     * 
     * @param jsonExamples Each string is a JSON object serialized according to the JSON
     *  mapping of the Example proto.
     * @return a new instance of DecodeJsonExample
     * @see org.tensorflow.op.IoOps.decodeJsonExample
     */
    public fun decodeJsonExample(jsonExamples: Operand<TString>): DecodeJsonExample =
            java.decodeJsonExample(    
        jsonExamples
        )

    /**
     * Reinterpret the bytes of a string as a vector of numbers.
     * 
     * @param T data type for ` output()` output
     * @param inputBytes Tensor of string to be decoded.
     * @param fixedLength Length in bytes for each element of the decoded output. Must be a
     * multiple
     *  of the size of the output type.
     * @param outType
     * @param options carries optional attributes values
     * @return a new instance of DecodePaddedRaw
     * @see org.tensorflow.op.IoOps.decodePaddedRaw
     * @param littleEndian Whether the input `input_bytes` is in little-endian order. Ignored for
     *  `out_type` values that are stored in a single byte, like `uint8`
     */
    public fun <T : TNumber> decodePaddedRaw(
        inputBytes: Operand<TString>,
        fixedLength: Operand<TInt32>,
        outType: Class<T>,
        littleEndian: Boolean? = null
    ): DecodePaddedRaw<T> = java.decodePaddedRaw<T>(    
        inputBytes,
        fixedLength,
        outType,
        *listOfNotNull(
            littleEndian?.let{ org.tensorflow.op.io.DecodePaddedRaw.littleEndian(it) }
        ).toTypedArray()
        )

    /**
     * Reinterpret the bytes of a string as a vector of numbers.
     * 
     * @param T data type for ` output()` output
     * @param bytes All the elements must have the same length.
     * @param outType
     * @param options carries optional attributes values
     * @return a new instance of DecodeRaw
     * @see org.tensorflow.op.IoOps.decodeRaw
     * @param littleEndian Whether the input `bytes` are in little-endian order.
     *  Ignored for `out_type` values that are stored in a single byte like
     *  `uint8`.
     */
    public fun <T : TType> decodeRaw(
        bytes: Operand<TString>,
        outType: Class<T>,
        littleEndian: Boolean? = null
    ): DecodeRaw<T> = java.decodeRaw<T>(    
        bytes,
        outType,
        *listOfNotNull(
            littleEndian?.let{ org.tensorflow.op.io.DecodeRaw.littleEndian(it) }
        ).toTypedArray()
        )

    /**
     * Deserialize and concatenate `SparseTensors` from a serialized minibatch.
     *  
     *  The input `serialized_sparse` must be a string matrix of shape `&#91;N x 3]` where
     *  `N` is the minibatch size and the rows correspond to packed outputs of
     *  `SerializeSparse`.  The ranks of the original `SparseTensor` objects
     *  must all match.  When the final `SparseTensor` is created, it has rank one
     *  higher than the ranks of the incoming `SparseTensor` objects
     *  (they have been concatenated along a new row dimension).
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
     * @param T data type for ` sparseValues()` output
     * @param serializedSparse 2-D, The `N` serialized `SparseTensor` objects.
     *  Must have 3 columns.
     * @param dtype The `dtype` of the serialized `SparseTensor` objects.
     * @return a new instance of DeserializeManySparse
     * @see org.tensorflow.op.IoOps.deserializeManySparse
     */
    public fun <T : TType> deserializeManySparse(serializedSparse: Operand<TString>,
            dtype: Class<T>): DeserializeManySparse<T> = java.deserializeManySparse<T>(    
        serializedSparse,
        dtype
        )

    /**
     * Encode strings into web-safe base64 format.
     *  
     *  Refer to the following article for more information on base64 format:
     *  en.wikipedia.org/wiki/Base64. Base64 strings may have padding with '=' at the
     *  end so that the encoded has length multiple of 4. See Padding section of the
     *  link above.
     *  
     *  Web-safe means that the encoder uses - and _ instead of + and /.
     * 
     * @param input Strings to be encoded.
     * @param options carries optional attributes values
     * @return a new instance of EncodeBase64
     * @see org.tensorflow.op.IoOps.encodeBase64
     * @param pad Bool whether padding is applied at the ends.
     */
    public fun encodeBase64(input: Operand<TString>, pad: Boolean? = null): EncodeBase64 =
            java.encodeBase64(    
        input,
        *listOfNotNull(
            pad?.let{ org.tensorflow.op.io.EncodeBase64.pad(it) }
        ).toTypedArray()
        )

    /**
     * A queue that produces elements in first-in first-out order.
     * 
     * @param componentTypes The type of each component in a value.
     * @param options carries optional attributes values
     * @return a new instance of FifoQueue
     * @see org.tensorflow.op.IoOps.fifoQueue
     * @param shapes The shape of each component in a value. The length of this attr must
     *  be either 0 or the same as the length of component_types. If the length of
     *  this attr is 0, the shapes of queue elements are not constrained, and
     *  only one element may be dequeued at a time.
     * @param capacity The upper bound on the number of elements in this queue.
     *  Negative numbers mean no limit.
     * @param container If non-empty, this queue is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this queue will be shared under the given name
     *  across multiple sessions.
     */
    public fun fifoQueue(
        componentTypes: List<Class<out TType>>,
        shapes: List<Shape>? = null,
        capacity: Long? = null,
        container: String? = null,
        sharedName: String? = null
    ): FifoQueue = java.fifoQueue(    
        componentTypes,
        *listOfNotNull(
            shapes?.let{ org.tensorflow.op.io.FifoQueue.shapes(it) },
            capacity?.let{ org.tensorflow.op.io.FifoQueue.capacity(it) },
            container?.let{ org.tensorflow.op.io.FifoQueue.container(it) },
            sharedName?.let{ org.tensorflow.op.io.FifoQueue.sharedName(it) }
        ).toTypedArray()
        )

    /**
     * A Reader that outputs fixed-length records from a file.
     * 
     * @param recordBytes Number of bytes in the record.
     * @param options carries optional attributes values
     * @return a new instance of FixedLengthRecordReader
     * @see org.tensorflow.op.IoOps.fixedLengthRecordReader
     * @param headerBytes Number of bytes in the header, defaults to 0.
     * @param footerBytes Number of bytes in the footer, defaults to 0.
     * @param hopBytes Number of bytes to hop before each read. Default of 0 means using
     *  record_bytes.
     * @param container If non-empty, this reader is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this reader is named in the given bucket
     *  with this shared_name. Otherwise, the node name is used instead.
     * @param encoding The type of encoding for the file. Currently ZLIB and GZIP
     *  are supported. Defaults to none.
     */
    public fun fixedLengthRecordReader(
        recordBytes: Long,
        headerBytes: Long? = null,
        footerBytes: Long? = null,
        hopBytes: Long? = null,
        container: String? = null,
        sharedName: String? = null,
        encoding: String? = null
    ): FixedLengthRecordReader = java.fixedLengthRecordReader(    
        recordBytes,
        *listOfNotNull(
            headerBytes?.let{ org.tensorflow.op.io.FixedLengthRecordReader.headerBytes(it) },
            footerBytes?.let{ org.tensorflow.op.io.FixedLengthRecordReader.footerBytes(it) },
            hopBytes?.let{ org.tensorflow.op.io.FixedLengthRecordReader.hopBytes(it) },
            container?.let{ org.tensorflow.op.io.FixedLengthRecordReader.container(it) },
            sharedName?.let{ org.tensorflow.op.io.FixedLengthRecordReader.sharedName(it) },
            encoding?.let{ org.tensorflow.op.io.FixedLengthRecordReader.encoding(it) }
        ).toTypedArray()
        )

    /**
     * A Reader that outputs the queued work as both the key and value.
     *  
     *  To use, enqueue strings in a Queue.  ReaderRead will take the front
     *  work string and output (work, work).
     * 
     * @param options carries optional attributes values
     * @return a new instance of IdentityReader
     * @see org.tensorflow.op.IoOps.identityReader
     *
     * @param container If non-empty, this reader is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this reader is named in the given bucket
     *  with this shared_name. Otherwise, the node name is used instead.
     */
    public fun identityReader(container: String? = null, sharedName: String? = null): IdentityReader
            = java.identityReader(    
        *listOfNotNull(
            container?.let{ org.tensorflow.op.io.IdentityReader.container(it) },
            sharedName?.let{ org.tensorflow.op.io.IdentityReader.sharedName(it) }
        ).toTypedArray()
        )

    /**
     * A Reader that outputs the records from a LMDB file.
     * 
     * @param options carries optional attributes values
     * @return a new instance of LmdbReader
     * @see org.tensorflow.op.IoOps.lmdbReader
     *
     * @param container If non-empty, this reader is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this reader is named in the given bucket
     *  with this shared_name. Otherwise, the node name is used instead.
     */
    public fun lmdbReader(container: String? = null, sharedName: String? = null): LmdbReader =
            java.lmdbReader(    
        *listOfNotNull(
            container?.let{ org.tensorflow.op.io.LmdbReader.container(it) },
            sharedName?.let{ org.tensorflow.op.io.LmdbReader.sharedName(it) }
        ).toTypedArray()
        )

    /**
     * Returns the set of files matching one or more glob patterns.
     *  
     *  Note that this routine only supports wildcard characters in the
     *  basename portion of the pattern, not in the directory portion.
     *  Note also that the order of filenames returned is deterministic.
     * 
     * @param pattern Shell wildcard pattern(s). Scalar or vector of type string.
     * @return a new instance of MatchingFiles
     * @see org.tensorflow.op.IoOps.matchingFiles
     */
    public fun matchingFiles(pattern: Operand<TString>): MatchingFiles = java.matchingFiles(    
        pattern
        )

    /**
     * A queue that produces elements in first-in first-out order.
     *  
     *  Variable-size shapes are allowed by setting the corresponding shape dimensions
     *  to 0 in the shape attr.  In this case DequeueMany will pad up to the maximum
     *  size of any given element in the minibatch.  See below for details.
     * 
     * @param componentTypes The type of each component in a value.
     * @param options carries optional attributes values
     * @return a new instance of PaddingFifoQueue
     * @see org.tensorflow.op.IoOps.paddingFifoQueue
     * @param shapes The shape of each component in a value. The length of this attr must
     *  be either 0 or the same as the length of component_types.
     *  Shapes of fixed rank but variable size are allowed by setting
     *  any shape dimension to -1.  In this case, the inputs' shape may vary along
     *  the given dimension, and DequeueMany will pad the given dimension with
     *  zeros up to the maximum shape of all elements in the given batch.
     *  If the length of this attr is 0, different queue elements may have
     *  different ranks and shapes, but only one element may be dequeued at a time.
     * @param capacity The upper bound on the number of elements in this queue.
     *  Negative numbers mean no limit.
     * @param container If non-empty, this queue is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this queue will be shared under the given name
     *  across multiple sessions.
     */
    public fun paddingFifoQueue(
        componentTypes: List<Class<out TType>>,
        shapes: List<Shape>? = null,
        capacity: Long? = null,
        container: String? = null,
        sharedName: String? = null
    ): PaddingFifoQueue = java.paddingFifoQueue(    
        componentTypes,
        *listOfNotNull(
            shapes?.let{ org.tensorflow.op.io.PaddingFifoQueue.shapes(it) },
            capacity?.let{ org.tensorflow.op.io.PaddingFifoQueue.capacity(it) },
            container?.let{ org.tensorflow.op.io.PaddingFifoQueue.container(it) },
            sharedName?.let{ org.tensorflow.op.io.PaddingFifoQueue.sharedName(it) }
        ).toTypedArray()
        )

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
     * @param denseDefaults A list of Tensors (some may be empty).  Corresponds 1:1 with
     * `dense_keys`.
     *  dense_defaults&#91;j] provides default values
     *  when the example's feature_map lacks dense_key&#91;j].  If an empty Tensor is
     *  provided for dense_defaults&#91;j], then the Feature dense_keys&#91;j] is required.
     *  The input type is inferred from dense_defaults&#91;j], even when it's empty.
     *  If dense_defaults&#91;j] is not empty, and dense_shapes&#91;j] is fully defined,
     *  then the shape of dense_defaults&#91;j] must match that of dense_shapes&#91;j].
     *  If dense_shapes&#91;j] has an undefined major dimension (variable strides dense
     *  feature), dense_defaults&#91;j] must contain a single element:
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
     * @param raggedSplitTypes A list of `num_ragged` types; the data types of row_splits in each
     * Feature
     *  given in ragged_keys (where `num_ragged = sparse_keys.size()`).
     *  May be DT_INT32 or DT_INT64.
     * @param denseShapes A list of `num_dense` shapes; the shapes of data in each Feature
     *  given in dense_keys (where `num_dense = dense_keys.size()`).
     *  The number of elements in the Feature corresponding to dense_key&#91;j]
     *  must always equal dense_shapes&#91;j].NumEntries().
     *  If dense_shapes&#91;j] == (D0, D1, ..., DN) then the shape of output
     *  Tensor dense_values&#91;j] will be (|serialized|, D0, D1, ..., DN):
     *  The dense outputs are just the inputs row-stacked by batch.
     *  This works for dense_shapes&#91;j] = (-1, D1, ..., DN).  In this case
     *  the shape of the output Tensor dense_values&#91;j] will be
     *  (|serialized|, M, D1, .., DN), where M is the maximum number of blocks
     *  of elements of length D1 * .... * DN, across all minibatch entries
     *  in the input.  Any minibatch entry with less than M blocks of elements of
     *  length D1 * ... * DN will be padded with the corresponding default_value
     *  scalar element along the second dimension.
     * @return a new instance of ParseExample
     * @see org.tensorflow.op.IoOps.parseExample
     */
    public fun parseExample(
        serialized: Operand<TString>,
        names: Operand<TString>,
        sparseKeys: Operand<TString>,
        denseKeys: Operand<TString>,
        raggedKeys: Operand<TString>,
        denseDefaults: Iterable<Operand<*>>,
        numSparse: Long,
        sparseTypes: List<Class<out TType>>,
        raggedValueTypes: List<Class<out TType>>,
        raggedSplitTypes: List<Class<out TNumber>>,
        denseShapes: List<Shape>
    ): ParseExample = java.parseExample(    
        serialized,
        names,
        sparseKeys,
        denseKeys,
        raggedKeys,
        denseDefaults,
        numSparse,
        sparseTypes,
        raggedValueTypes,
        raggedSplitTypes,
        denseShapes
        )

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
     * @param contextSparseKeys The keys expected in the Examples' features associated with
     * context_sparse
     *  values.
     * @param contextDenseKeys The keys expected in the SequenceExamples' context features
     * associated with
     *  dense values.
     * @param contextRaggedKeys The keys expected in the Examples' features associated with
     * context_ragged
     *  values.
     * @param featureListSparseKeys The keys expected in the FeatureLists associated with sparse
     * values.
     * @param featureListDenseKeys The keys expected in the SequenceExamples' feature_lists
     * associated
     *  with lists of dense values.
     * @param featureListRaggedKeys The keys expected in the FeatureLists associated with ragged
     * values.
     * @param featureListDenseMissingAssumedEmpty A vector corresponding 1:1 with
     * feature_list_dense_keys, indicating which
     *  features may be missing from the SequenceExamples.  If the associated
     *  FeatureList is missing, it is treated as empty.
     * @param contextDenseDefaults A list of Ncontext_dense Tensors (some may be empty).
     *  context_dense_defaults&#91;j] provides default values
     *  when the SequenceExample's context map lacks context_dense_key&#91;j].
     *  If an empty Tensor is provided for context_dense_defaults&#91;j],
     *  then the Feature context_dense_keys&#91;j] is required.
     *  The input type is inferred from context_dense_defaults&#91;j], even when it's
     *  empty.  If context_dense_defaults&#91;j] is not empty, its shape must match
     *  context_dense_shapes&#91;j].
     * @param contextSparseTypes A list of Ncontext_sparse types; the data types of data in
     *  each context Feature given in context_sparse_keys.
     *  Currently the ParseSingleSequenceExample supports DT_FLOAT (FloatList),
     *  DT_INT64 (Int64List), and DT_STRING (BytesList).
     * @param contextRaggedValueTypes RaggedTensor.value dtypes for the ragged context features.
     * @param contextRaggedSplitTypes RaggedTensor.row_split dtypes for the ragged context
     * features.
     * @param featureListDenseTypes
     * @param featureListSparseTypes A list of Nfeature_list_sparse types; the data types
     *  of data in each FeatureList given in feature_list_sparse_keys.
     *  Currently the ParseSingleSequenceExample supports DT_FLOAT (FloatList),
     *  DT_INT64 (Int64List), and DT_STRING (BytesList).
     * @param featureListRaggedValueTypes RaggedTensor.value dtypes for the ragged FeatureList
     * features.
     * @param featureListRaggedSplitTypes RaggedTensor.row_split dtypes for the ragged FeatureList
     * features.
     * @param options carries optional attributes values
     * @return a new instance of ParseSequenceExample
     * @see org.tensorflow.op.IoOps.parseSequenceExample
     * @param NcontextSparse @param NcontextSparse
     * @param contextDenseShapes A list of Ncontext_dense shapes; the shapes of data in
     *  each context Feature given in context_dense_keys.
     *  The number of elements in the Feature corresponding to context_dense_key&#91;j]
     *  must always equal context_dense_shapes&#91;j].NumEntries().
     *  The shape of context_dense_values&#91;j] will match context_dense_shapes&#91;j].
     * @param NfeatureListSparse @param NfeatureListSparse
     * @param NfeatureListDense @param NfeatureListDense
     * @param featureListDenseShapes A list of Nfeature_list_dense shapes; the shapes of
     *  data in each FeatureList given in feature_list_dense_keys.
     *  The shape of each Feature in the FeatureList corresponding to
     *  feature_list_dense_key&#91;j] must always equal
     *  feature_list_dense_shapes&#91;j].NumEntries().
     */
    public fun parseSequenceExample(
        serialized: Operand<TString>,
        debugName: Operand<TString>,
        contextSparseKeys: Operand<TString>,
        contextDenseKeys: Operand<TString>,
        contextRaggedKeys: Operand<TString>,
        featureListSparseKeys: Operand<TString>,
        featureListDenseKeys: Operand<TString>,
        featureListRaggedKeys: Operand<TString>,
        featureListDenseMissingAssumedEmpty: Operand<TBool>,
        contextDenseDefaults: Iterable<Operand<*>>,
        contextSparseTypes: List<Class<out TType>>,
        contextRaggedValueTypes: List<Class<out TType>>,
        contextRaggedSplitTypes: List<Class<out TNumber>>,
        featureListDenseTypes: List<Class<out TType>>,
        featureListSparseTypes: List<Class<out TType>>,
        featureListRaggedValueTypes: List<Class<out TType>>,
        featureListRaggedSplitTypes: List<Class<out TNumber>>,
        NcontextSparse: Long? = null,
        contextDenseShapes: List<Shape>? = null,
        NfeatureListSparse: Long? = null,
        NfeatureListDense: Long? = null,
        featureListDenseShapes: List<Shape>? = null
    ): ParseSequenceExample = java.parseSequenceExample(    
        serialized,
        debugName,
        contextSparseKeys,
        contextDenseKeys,
        contextRaggedKeys,
        featureListSparseKeys,
        featureListDenseKeys,
        featureListRaggedKeys,
        featureListDenseMissingAssumedEmpty,
        contextDenseDefaults,
        contextSparseTypes,
        contextRaggedValueTypes,
        contextRaggedSplitTypes,
        featureListDenseTypes,
        featureListSparseTypes,
        featureListRaggedValueTypes,
        featureListRaggedSplitTypes,
        *listOfNotNull(
            NcontextSparse?.let{ org.tensorflow.op.io.ParseSequenceExample.NcontextSparse(it) },
            contextDenseShapes?.let{ org.tensorflow.op.io.ParseSequenceExample.contextDenseShapes(it) },
            NfeatureListSparse?.let{ org.tensorflow.op.io.ParseSequenceExample.NfeatureListSparse(it) },
            NfeatureListDense?.let{ org.tensorflow.op.io.ParseSequenceExample.NfeatureListDense(it) },
            featureListDenseShapes?.let{
            org.tensorflow.op.io.ParseSequenceExample.featureListDenseShapes(it) }
        ).toTypedArray()
        )

    /**
     * Transforms a tf.Example proto (as a string) into typed tensors.
     * 
     * @param serialized A vector containing a batch of binary serialized Example protos.
     * @param denseDefaults A list of Tensors (some may be empty), whose length matches
     *  the length of `dense_keys`. dense_defaults&#91;j] provides default values
     *  when the example's feature_map lacks dense_key&#91;j].  If an empty Tensor is
     *  provided for dense_defaults&#91;j], then the Feature dense_keys&#91;j] is required.
     *  The input type is inferred from dense_defaults&#91;j], even when it's empty.
     *  If dense_defaults&#91;j] is not empty, and dense_shapes&#91;j] is fully defined,
     *  then the shape of dense_defaults&#91;j] must match that of dense_shapes&#91;j].
     *  If dense_shapes&#91;j] has an undefined major dimension (variable strides dense
     *  feature), dense_defaults&#91;j] must contain a single element:
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
     *  number of elements in the Feature corresponding to dense_key&#91;j] must
     *  always equal dense_shapes&#91;j].NumEntries().  If dense_shapes&#91;j] ==
     *  (D0, D1, ..., DN) then the shape of output Tensor dense_values&#91;j]
     *  will be (D0, D1, ..., DN): In the case dense_shapes&#91;j] = (-1, D1,
     *  ..., DN), the shape of the output Tensor dense_values&#91;j] will be (M,
     *  D1, .., DN), where M is the number of blocks of elements of length
     *  D1 * .... * DN, in the input.
     * @return a new instance of ParseSingleExample
     * @see org.tensorflow.op.IoOps.parseSingleExample
     */
    public fun parseSingleExample(
        serialized: Operand<TString>,
        denseDefaults: Iterable<Operand<*>>,
        numSparse: Long,
        sparseKeys: List<String>,
        denseKeys: List<String>,
        sparseTypes: List<Class<out TType>>,
        denseShapes: List<Shape>
    ): ParseSingleExample = java.parseSingleExample(    
        serialized,
        denseDefaults,
        numSparse,
        sparseKeys,
        denseKeys,
        sparseTypes,
        denseShapes
        )

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
     *  context_dense_defaults&#91;j] provides default values
     *  when the SequenceExample's context map lacks context_dense_key&#91;j].
     *  If an empty Tensor is provided for context_dense_defaults&#91;j],
     *  then the Feature context_dense_keys&#91;j] is required.
     *  The input type is inferred from context_dense_defaults&#91;j], even when it's
     *  empty.  If context_dense_defaults&#91;j] is not empty, its shape must match
     *  context_dense_shapes&#91;j].
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
     * @see org.tensorflow.op.IoOps.parseSingleSequenceExample
     * @param contextDenseShapes A list of Ncontext_dense shapes; the shapes of data in
     *  each context Feature given in context_dense_keys.
     *  The number of elements in the Feature corresponding to context_dense_key&#91;j]
     *  must always equal context_dense_shapes&#91;j].NumEntries().
     *  The shape of context_dense_values&#91;j] will match context_dense_shapes&#91;j].
     * @param featureListDenseShapes A list of Nfeature_list_dense shapes; the shapes of
     *  data in each FeatureList given in feature_list_dense_keys.
     *  The shape of each Feature in the FeatureList corresponding to
     *  feature_list_dense_key&#91;j] must always equal
     *  feature_list_dense_shapes&#91;j].NumEntries().
     */
    public fun parseSingleSequenceExample(
        serialized: Operand<TString>,
        featureListDenseMissingAssumedEmpty: Operand<TString>,
        contextSparseKeys: Iterable<Operand<TString>>,
        contextDenseKeys: Iterable<Operand<TString>>,
        featureListSparseKeys: Iterable<Operand<TString>>,
        featureListDenseKeys: Iterable<Operand<TString>>,
        contextDenseDefaults: Iterable<Operand<*>>,
        debugName: Operand<TString>,
        contextSparseTypes: List<Class<out TType>>,
        featureListDenseTypes: List<Class<out TType>>,
        featureListSparseTypes: List<Class<out TType>>,
        contextDenseShapes: List<Shape>? = null,
        featureListDenseShapes: List<Shape>? = null
    ): ParseSingleSequenceExample = java.parseSingleSequenceExample(    
        serialized,
        featureListDenseMissingAssumedEmpty,
        contextSparseKeys,
        contextDenseKeys,
        featureListSparseKeys,
        featureListDenseKeys,
        contextDenseDefaults,
        debugName,
        contextSparseTypes,
        featureListDenseTypes,
        featureListSparseTypes,
        *listOfNotNull(
            contextDenseShapes?.let{
            org.tensorflow.op.io.ParseSingleSequenceExample.contextDenseShapes(it) },
            featureListDenseShapes?.let{
            org.tensorflow.op.io.ParseSingleSequenceExample.featureListDenseShapes(it) }
        ).toTypedArray()
        )

    /**
     * Transforms a serialized tensorflow.TensorProto proto into a Tensor.
     * 
     * @param T data type for ` output()` output
     * @param serialized A scalar string containing a serialized TensorProto proto.
     * @param outType The type of the serialized tensor.  The provided type must match the
     *  type of the serialized tensor and no implicit conversion will take place.
     * @return a new instance of ParseTensor
     * @see org.tensorflow.op.IoOps.parseTensor
     */
    public fun <T : TType> parseTensor(serialized: Operand<TString>, outType: Class<T>):
            ParseTensor<T> = java.parseTensor<T>(    
        serialized,
        outType
        )

    /**
     * A queue that produces elements sorted by the first component value.
     *  
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
     * @see org.tensorflow.op.IoOps.priorityQueue
     * @param capacity The upper bound on the number of elements in this queue.
     *  Negative numbers mean no limit.
     * @param container If non-empty, this queue is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this queue will be shared under the given name
     *  across multiple sessions.
     */
    public fun priorityQueue(
        componentTypes: List<Class<out TType>>,
        shapes: List<Shape>,
        capacity: Long? = null,
        container: String? = null,
        sharedName: String? = null
    ): PriorityQueue = java.priorityQueue(    
        componentTypes,
        shapes,
        *listOfNotNull(
            capacity?.let{ org.tensorflow.op.io.PriorityQueue.capacity(it) },
            container?.let{ org.tensorflow.op.io.PriorityQueue.container(it) },
            sharedName?.let{ org.tensorflow.op.io.PriorityQueue.sharedName(it) }
        ).toTypedArray()
        )

    /**
     * Closes the given queue.
     *  
     *  This operation signals that no more elements will be enqueued in the
     *  given queue. Subsequent Enqueue(Many) operations will fail.
     *  Subsequent Dequeue(Many) operations will continue to succeed if
     *  sufficient elements remain in the queue. Subsequent Dequeue(Many)
     *  operations that would block will fail immediately.
     * 
     * @param handle The handle to a queue.
     * @param options carries optional attributes values
     * @return a new instance of QueueClose
     * @see org.tensorflow.op.IoOps.queueClose
     * @param cancelPendingEnqueues If true, all pending enqueue requests that are
     *  blocked on the given queue will be canceled.
     */
    public fun queueClose(handle: Operand<*>, cancelPendingEnqueues: Boolean? = null): QueueClose =
            java.queueClose(    
        handle,
        *listOfNotNull(
            cancelPendingEnqueues?.let{ org.tensorflow.op.io.QueueClose.cancelPendingEnqueues(it) }
        ).toTypedArray()
        )

    /**
     * Dequeues a tuple of one or more tensors from the given queue.
     *  
     *  This operation has k outputs, where k is the number of components
     *  in the tuples stored in the given queue, and output i is the ith
     *  component of the dequeued tuple.
     *  
     *  N.B. If the queue is empty, this operation will block until an element
     *  has been dequeued (or 'timeout_ms' elapses, if specified).
     * 
     * @param handle The handle to a queue.
     * @param componentTypes The type of each component in a tuple.
     * @param options carries optional attributes values
     * @return a new instance of QueueDequeue
     * @see org.tensorflow.op.IoOps.queueDequeue
     * @param timeoutMs If the queue is empty, this operation will block for up to
     *  timeout_ms milliseconds.
     *  Note: This option is not supported yet.
     */
    public fun queueDequeue(
        handle: Operand<*>,
        componentTypes: List<Class<out TType>>,
        timeoutMs: Long? = null
    ): QueueDequeue = java.queueDequeue(    
        handle,
        componentTypes,
        *listOfNotNull(
            timeoutMs?.let{ org.tensorflow.op.io.QueueDequeue.timeoutMs(it) }
        ).toTypedArray()
        )

    /**
     * Dequeues `n` tuples of one or more tensors from the given queue.
     *  
     *  If the queue is closed and there are fewer than `n` elements, then an
     *  OutOfRange error is returned.
     *  
     *  This operation concatenates queue-element component tensors along the
     *  0th dimension to make a single component tensor.  All of the components
     *  in the dequeued tuple will have size `n` in the 0th dimension.
     *  
     *  This operation has `k` outputs, where `k` is the number of components in
     *  the tuples stored in the given queue, and output `i` is the ith
     *  component of the dequeued tuple.
     *  
     *  N.B. If the queue is empty, this operation will block until `n` elements
     *  have been dequeued (or 'timeout_ms' elapses, if specified).
     * 
     * @param handle The handle to a queue.
     * @param n The number of tuples to dequeue.
     * @param componentTypes The type of each component in a tuple.
     * @param options carries optional attributes values
     * @return a new instance of QueueDequeueMany
     * @see org.tensorflow.op.IoOps.queueDequeueMany
     * @param timeoutMs If the queue has fewer than n elements, this operation
     *  will block for up to timeout_ms milliseconds.
     *  Note: This option is not supported yet.
     */
    public fun queueDequeueMany(
        handle: Operand<*>,
        n: Operand<TInt32>,
        componentTypes: List<Class<out TType>>,
        timeoutMs: Long? = null
    ): QueueDequeueMany = java.queueDequeueMany(    
        handle,
        n,
        componentTypes,
        *listOfNotNull(
            timeoutMs?.let{ org.tensorflow.op.io.QueueDequeueMany.timeoutMs(it) }
        ).toTypedArray()
        )

    /**
     * Dequeues `n` tuples of one or more tensors from the given queue.
     *  
     *  This operation is not supported by all queues.  If a queue does not support
     *  DequeueUpTo, then an Unimplemented error is returned.
     *  
     *  If the queue is closed and there are more than 0 but less than `n`
     *  elements remaining, then instead of returning an OutOfRange error like
     *  QueueDequeueMany, less than `n` elements are returned immediately.  If
     *  the queue is closed and there are 0 elements left in the queue, then
     *  an OutOfRange error is returned just like in QueueDequeueMany.
     *  Otherwise the behavior is identical to QueueDequeueMany:
     *  
     *  This operation concatenates queue-element component tensors along the
     *  0th dimension to make a single component tensor.  All of the components
     *  in the dequeued tuple will have size n in the 0th dimension.
     *  
     *  This operation has `k` outputs, where `k` is the number of components in
     *  the tuples stored in the given queue, and output `i` is the ith
     *  component of the dequeued tuple.
     * 
     * @param handle The handle to a queue.
     * @param n The number of tuples to dequeue.
     * @param componentTypes The type of each component in a tuple.
     * @param options carries optional attributes values
     * @return a new instance of QueueDequeueUpTo
     * @see org.tensorflow.op.IoOps.queueDequeueUpTo
     * @param timeoutMs If the queue has fewer than n elements, this operation
     *  will block for up to timeout_ms milliseconds.
     *  Note: This option is not supported yet.
     */
    public fun queueDequeueUpTo(
        handle: Operand<*>,
        n: Operand<TInt32>,
        componentTypes: List<Class<out TType>>,
        timeoutMs: Long? = null
    ): QueueDequeueUpTo = java.queueDequeueUpTo(    
        handle,
        n,
        componentTypes,
        *listOfNotNull(
            timeoutMs?.let{ org.tensorflow.op.io.QueueDequeueUpTo.timeoutMs(it) }
        ).toTypedArray()
        )

    /**
     * Enqueues a tuple of one or more tensors in the given queue.
     *  
     *  The components input has k elements, which correspond to the components of
     *  tuples stored in the given queue.
     *  
     *  N.B. If the queue is full, this operation will block until the given
     *  element has been enqueued (or 'timeout_ms' elapses, if specified).
     * 
     * @param handle The handle to a queue.
     * @param components One or more tensors from which the enqueued tensors should be taken.
     * @param options carries optional attributes values
     * @return a new instance of QueueEnqueue
     * @see org.tensorflow.op.IoOps.queueEnqueue
     * @param timeoutMs If the queue is full, this operation will block for up to
     *  timeout_ms milliseconds.
     *  Note: This option is not supported yet.
     */
    public fun queueEnqueue(
        handle: Operand<*>,
        components: Iterable<Operand<*>>,
        timeoutMs: Long? = null
    ): QueueEnqueue = java.queueEnqueue(    
        handle,
        components,
        *listOfNotNull(
            timeoutMs?.let{ org.tensorflow.op.io.QueueEnqueue.timeoutMs(it) }
        ).toTypedArray()
        )

    /**
     * Enqueues zero or more tuples of one or more tensors in the given queue.
     *  
     *  This operation slices each component tensor along the 0th dimension to
     *  make multiple queue elements. All of the tuple components must have the
     *  same size in the 0th dimension.
     *  
     *  The components input has k elements, which correspond to the components of
     *  tuples stored in the given queue.
     *  
     *  N.B. If the queue is full, this operation will block until the given
     *  elements have been enqueued (or 'timeout_ms' elapses, if specified).
     * 
     * @param handle The handle to a queue.
     * @param components One or more tensors from which the enqueued tensors should
     *  be taken.
     * @param options carries optional attributes values
     * @return a new instance of QueueEnqueueMany
     * @see org.tensorflow.op.IoOps.queueEnqueueMany
     * @param timeoutMs If the queue is too full, this operation will block for up
     *  to timeout_ms milliseconds.
     *  Note: This option is not supported yet.
     */
    public fun queueEnqueueMany(
        handle: Operand<*>,
        components: Iterable<Operand<*>>,
        timeoutMs: Long? = null
    ): QueueEnqueueMany = java.queueEnqueueMany(    
        handle,
        components,
        *listOfNotNull(
            timeoutMs?.let{ org.tensorflow.op.io.QueueEnqueueMany.timeoutMs(it) }
        ).toTypedArray()
        )

    /**
     * Returns true if queue is closed.
     *  
     *  This operation returns true if the queue is closed and false if the queue
     *  is open.
     * 
     * @param handle The handle to a queue.
     * @return a new instance of QueueIsClosed
     * @see org.tensorflow.op.IoOps.queueIsClosed
     */
    public fun queueIsClosed(handle: Operand<*>): QueueIsClosed = java.queueIsClosed(    
        handle
        )

    /**
     * Computes the number of elements in the given queue.
     * 
     * @param handle The handle to a queue.
     * @return a new instance of QueueSize
     * @see org.tensorflow.op.IoOps.queueSize
     */
    public fun queueSize(handle: Operand<*>): QueueSize = java.queueSize(    
        handle
        )

    /**
     * A queue that randomizes the order of elements.
     * 
     * @param componentTypes The type of each component in a value.
     * @param options carries optional attributes values
     * @return a new instance of RandomShuffleQueue
     * @see org.tensorflow.op.IoOps.randomShuffleQueue
     * @param shapes The shape of each component in a value. The length of this attr must
     *  be either 0 or the same as the length of component_types. If the length of
     *  this attr is 0, the shapes of queue elements are not constrained, and
     *  only one element may be dequeued at a time.
     * @param capacity The upper bound on the number of elements in this queue.
     *  Negative numbers mean no limit.
     * @param minAfterDequeue Dequeue will block unless there would be this
     *  many elements after the dequeue or the queue is closed. This
     *  ensures a minimum level of mixing of elements.
     * @param seed If either seed or seed2 is set to be non-zero, the random number
     *  generator is seeded by the given seed.  Otherwise, a random seed is used.
     * @param seed2 A second seed to avoid seed collision.
     * @param container If non-empty, this queue is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this queue will be shared under the given name
     *  across multiple sessions.
     */
    public fun randomShuffleQueue(
        componentTypes: List<Class<out TType>>,
        shapes: List<Shape>? = null,
        capacity: Long? = null,
        minAfterDequeue: Long? = null,
        seed: Long? = null,
        seed2: Long? = null,
        container: String? = null,
        sharedName: String? = null
    ): RandomShuffleQueue = java.randomShuffleQueue(    
        componentTypes,
        *listOfNotNull(
            shapes?.let{ org.tensorflow.op.io.RandomShuffleQueue.shapes(it) },
            capacity?.let{ org.tensorflow.op.io.RandomShuffleQueue.capacity(it) },
            minAfterDequeue?.let{ org.tensorflow.op.io.RandomShuffleQueue.minAfterDequeue(it) },
            seed?.let{ org.tensorflow.op.io.RandomShuffleQueue.seed(it) },
            seed2?.let{ org.tensorflow.op.io.RandomShuffleQueue.seed2(it) },
            container?.let{ org.tensorflow.op.io.RandomShuffleQueue.container(it) },
            sharedName?.let{ org.tensorflow.op.io.RandomShuffleQueue.sharedName(it) }
        ).toTypedArray()
        )

    /**
     * Reads and outputs the entire contents of the input filename.
     * 
     * @param filename
     * @return a new instance of ReadFile
     * @see org.tensorflow.op.IoOps.readFile
     */
    public fun readFile(filename: Operand<TString>): ReadFile = java.readFile(    
        filename
        )

    /**
     * Returns the number of records this Reader has produced.
     *  
     *  This is the same as the number of ReaderRead executions that have
     *  succeeded.
     * 
     * @param readerHandle Handle to a Reader.
     * @return a new instance of ReaderNumRecordsProduced
     * @see org.tensorflow.op.IoOps.readerNumRecordsProduced
     */
    public fun readerNumRecordsProduced(readerHandle: Operand<*>): ReaderNumRecordsProduced =
            java.readerNumRecordsProduced(    
        readerHandle
        )

    /**
     * Returns the number of work units this Reader has finished processing.
     * 
     * @param readerHandle Handle to a Reader.
     * @return a new instance of ReaderNumWorkUnitsCompleted
     * @see org.tensorflow.op.IoOps.readerNumWorkUnitsCompleted
     */
    public fun readerNumWorkUnitsCompleted(readerHandle: Operand<*>): ReaderNumWorkUnitsCompleted =
            java.readerNumWorkUnitsCompleted(    
        readerHandle
        )

    /**
     * Returns the next record (key, value pair) produced by a Reader.
     *  
     *  Will dequeue from the input queue if necessary (e.g. when the
     *  Reader needs to start reading from a new file since it has finished
     *  with the previous file).
     * 
     * @param readerHandle Handle to a Reader.
     * @param queueHandle Handle to a Queue, with string work items.
     * @return a new instance of ReaderRead
     * @see org.tensorflow.op.IoOps.readerRead
     */
    public fun readerRead(readerHandle: Operand<*>, queueHandle: Operand<*>): ReaderRead =
            java.readerRead(    
        readerHandle,
        queueHandle
        )

    /**
     * Returns up to `num_records` (key, value) pairs produced by a Reader.
     *  
     *  Will dequeue from the input queue if necessary (e.g. when the
     *  Reader needs to start reading from a new file since it has finished
     *  with the previous file).
     *  It may return less than `num_records` even before the last batch.
     * 
     * @param readerHandle Handle to a `Reader`.
     * @param queueHandle Handle to a `Queue`, with string work items.
     * @param numRecords number of records to read from `Reader`.
     * @return a new instance of ReaderReadUpTo
     * @see org.tensorflow.op.IoOps.readerReadUpTo
     */
    public fun readerReadUpTo(
        readerHandle: Operand<*>,
        queueHandle: Operand<*>,
        numRecords: Operand<TInt64>
    ): ReaderReadUpTo = java.readerReadUpTo(    
        readerHandle,
        queueHandle,
        numRecords
        )

    /**
     * Restore a Reader to its initial clean state.
     * 
     * @param readerHandle Handle to a Reader.
     * @return a new instance of ReaderReset
     * @see org.tensorflow.op.IoOps.readerReset
     */
    public fun readerReset(readerHandle: Operand<*>): ReaderReset = java.readerReset(    
        readerHandle
        )

    /**
     * Restore a reader to a previously saved state.
     *  
     *  Not all Readers support being restored, so this can produce an
     *  Unimplemented error.
     * 
     * @param readerHandle Handle to a Reader.
     * @param state Result of a ReaderSerializeState of a Reader with type
     *  matching reader_handle.
     * @return a new instance of ReaderRestoreState
     * @see org.tensorflow.op.IoOps.readerRestoreState
     */
    public fun readerRestoreState(readerHandle: Operand<*>, state: Operand<TString>):
            ReaderRestoreState = java.readerRestoreState(    
        readerHandle,
        state
        )

    /**
     * Produce a string tensor that encodes the state of a Reader.
     *  
     *  Not all Readers support being serialized, so this can produce an
     *  Unimplemented error.
     * 
     * @param readerHandle Handle to a Reader.
     * @return a new instance of ReaderSerializeState
     * @see org.tensorflow.op.IoOps.readerSerializeState
     */
    public fun readerSerializeState(readerHandle: Operand<*>): ReaderSerializeState =
            java.readerSerializeState(    
        readerHandle
        )

    /**
     * Serialize an `N`-minibatch `SparseTensor` into an `&#91;N, 3]` `Tensor` object.
     *  
     *  The `SparseTensor` must have rank `R` greater than 1, and the first dimension
     *  is treated as the minibatch dimension.  Elements of the `SparseTensor`
     *  must be sorted in increasing order of this first dimension.  The serialized
     *  `SparseTensor` objects going into each row of `serialized_sparse` will have
     *  rank `R-1`.
     *  
     *  The minibatch size `N` is extracted from `sparse_shape&#91;0]`.
     * 
     * @param U data type for ` serializedSparse()` output
     * @param sparseIndices 2-D.  The `indices` of the minibatch `SparseTensor`.
     * @param sparseValues 1-D.  The `values` of the minibatch `SparseTensor`.
     * @param sparseShape 1-D.  The `shape` of the minibatch `SparseTensor`.
     * @return a new instance of SerializeManySparse
     * @see org.tensorflow.op.IoOps.serializeManySparse
     */
    public fun serializeManySparse(
        sparseIndices: Operand<TInt64>,
        sparseValues: Operand<out TType>,
        sparseShape: Operand<TInt64>
    ): SerializeManySparse<TString> = java.serializeManySparse(    
        sparseIndices,
        sparseValues,
        sparseShape
        )

    /**
     * Serialize an `N`-minibatch `SparseTensor` into an `&#91;N, 3]` `Tensor` object.
     *  
     *  The `SparseTensor` must have rank `R` greater than 1, and the first dimension
     *  is treated as the minibatch dimension.  Elements of the `SparseTensor`
     *  must be sorted in increasing order of this first dimension.  The serialized
     *  `SparseTensor` objects going into each row of `serialized_sparse` will have
     *  rank `R-1`.
     *  
     *  The minibatch size `N` is extracted from `sparse_shape&#91;0]`.
     * 
     * @param U data type for ` serializedSparse()` output
     * @param sparseIndices 2-D.  The `indices` of the minibatch `SparseTensor`.
     * @param sparseValues 1-D.  The `values` of the minibatch `SparseTensor`.
     * @param sparseShape 1-D.  The `shape` of the minibatch `SparseTensor`.
     * @param outType The `dtype` to use for serialization; the supported types are `string`
     *  (default) and `variant`.
     * @return a new instance of SerializeManySparse
     * @see org.tensorflow.op.IoOps.serializeManySparse
     */
    public fun <U : TType> serializeManySparse(
        sparseIndices: Operand<TInt64>,
        sparseValues: Operand<out TType>,
        sparseShape: Operand<TInt64>,
        outType: Class<U>
    ): SerializeManySparse<U> = java.serializeManySparse<U>(    
        sparseIndices,
        sparseValues,
        sparseShape,
        outType
        )

    /**
     * Serialize a `SparseTensor` into a `&#91;3]` `Tensor` object.
     * 
     * @param U data type for ` serializedSparse()` output
     * @param sparseIndices 2-D.  The `indices` of the `SparseTensor`.
     * @param sparseValues 1-D.  The `values` of the `SparseTensor`.
     * @param sparseShape 1-D.  The `shape` of the `SparseTensor`.
     * @return a new instance of SerializeSparse
     * @see org.tensorflow.op.IoOps.serializeSparse
     */
    public fun serializeSparse(
        sparseIndices: Operand<TInt64>,
        sparseValues: Operand<out TType>,
        sparseShape: Operand<TInt64>
    ): SerializeSparse<TString> = java.serializeSparse(    
        sparseIndices,
        sparseValues,
        sparseShape
        )

    /**
     * Serialize a `SparseTensor` into a `&#91;3]` `Tensor` object.
     * 
     * @param U data type for ` serializedSparse()` output
     * @param sparseIndices 2-D.  The `indices` of the `SparseTensor`.
     * @param sparseValues 1-D.  The `values` of the `SparseTensor`.
     * @param sparseShape 1-D.  The `shape` of the `SparseTensor`.
     * @param outType The `dtype` to use for serialization; the supported types are `string`
     *  (default) and `variant`.
     * @return a new instance of SerializeSparse
     * @see org.tensorflow.op.IoOps.serializeSparse
     */
    public fun <U : TType> serializeSparse(
        sparseIndices: Operand<TInt64>,
        sparseValues: Operand<out TType>,
        sparseShape: Operand<TInt64>,
        outType: Class<U>
    ): SerializeSparse<U> = java.serializeSparse<U>(    
        sparseIndices,
        sparseValues,
        sparseShape,
        outType
        )

    /**
     * Transforms a Tensor into a serialized TensorProto proto.
     * 
     * @param tensor A Tensor of type `T`.
     * @return a new instance of SerializeTensor
     * @see org.tensorflow.op.IoOps.serializeTensor
     */
    public fun serializeTensor(tensor: Operand<out TType>): SerializeTensor = java.serializeTensor(    
        tensor
        )

    /**
     * Generate a sharded filename. The filename is printf formatted as
     *  
     *     %s-%05d-of-%05d, basename, shard, num_shards.
     * 
     * @param basename
     * @param shard
     * @param numShards
     * @return a new instance of ShardedFilename
     * @see org.tensorflow.op.IoOps.shardedFilename
     */
    public fun shardedFilename(
        basename: Operand<TString>,
        shard: Operand<TInt32>,
        numShards: Operand<TInt32>
    ): ShardedFilename = java.shardedFilename(    
        basename,
        shard,
        numShards
        )

    /**
     * Generate a glob pattern matching all sharded file names.
     * 
     * @param basename
     * @param numShards
     * @return a new instance of ShardedFilespec
     * @see org.tensorflow.op.IoOps.shardedFilespec
     */
    public fun shardedFilespec(basename: Operand<TString>, numShards: Operand<TInt32>):
            ShardedFilespec = java.shardedFilespec(    
        basename,
        numShards
        )

    /**
     * A Reader that outputs the lines of a file delimited by '\n'.
     * 
     * @param options carries optional attributes values
     * @return a new instance of TextLineReader
     * @see org.tensorflow.op.IoOps.textLineReader
     *
     * @param skipHeaderLines Number of lines to skip from the beginning of every file.
     * @param container If non-empty, this reader is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this reader is named in the given bucket
     *  with this shared_name. Otherwise, the node name is used instead.
     */
    public fun textLineReader(
        skipHeaderLines: Long? = null,
        container: String? = null,
        sharedName: String? = null
    ): TextLineReader = java.textLineReader(    
        *listOfNotNull(
            skipHeaderLines?.let{ org.tensorflow.op.io.TextLineReader.skipHeaderLines(it) },
            container?.let{ org.tensorflow.op.io.TextLineReader.container(it) },
            sharedName?.let{ org.tensorflow.op.io.TextLineReader.sharedName(it) }
        ).toTypedArray()
        )

    /**
     * A Reader that outputs the records from a TensorFlow Records file.
     * 
     * @param options carries optional attributes values
     * @return a new instance of TfRecordReader
     * @see org.tensorflow.op.IoOps.tfRecordReader
     *
     * @param container If non-empty, this reader is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this reader is named in the given bucket
     *  with this shared_name. Otherwise, the node name is used instead.
     * @param compressionType @param compressionType
     */
    public fun tfRecordReader(
        container: String? = null,
        sharedName: String? = null,
        compressionType: String? = null
    ): TfRecordReader = java.tfRecordReader(    
        *listOfNotNull(
            container?.let{ org.tensorflow.op.io.TfRecordReader.container(it) },
            sharedName?.let{ org.tensorflow.op.io.TfRecordReader.sharedName(it) },
            compressionType?.let{ org.tensorflow.op.io.TfRecordReader.compressionType(it) }
        ).toTypedArray()
        )

    /**
     * A Reader that outputs the entire contents of a file as a value.
     *  
     *  To use, enqueue filenames in a Queue.  The output of ReaderRead will
     *  be a filename (key) and the contents of that file (value).
     * 
     * @param options carries optional attributes values
     * @return a new instance of WholeFileReader
     * @see org.tensorflow.op.IoOps.wholeFileReader
     *
     * @param container If non-empty, this reader is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this reader is named in the given bucket
     *  with this shared_name. Otherwise, the node name is used instead.
     */
    public fun wholeFileReader(container: String? = null, sharedName: String? = null):
            WholeFileReader = java.wholeFileReader(    
        *listOfNotNull(
            container?.let{ org.tensorflow.op.io.WholeFileReader.container(it) },
            sharedName?.let{ org.tensorflow.op.io.WholeFileReader.sharedName(it) }
        ).toTypedArray()
        )

    /**
     * Writes contents to the file at input filename. Creates file and recursively
     *  
     *  creates directory if not existing.
     * 
     * @param filename scalar. The name of the file to which we write the contents.
     * @param contents scalar. The content to be written to the output file.
     * @return a new instance of WriteFile
     * @see org.tensorflow.op.IoOps.writeFile
     */
    public fun writeFile(filename: Operand<TString>, contents: Operand<TString>): WriteFile =
            java.writeFile(    
        filename,
        contents
        )

    /**
     * Reinterpret the bytes of a string as a vector of numbers.
     * 
     * @param T data type for ` output()` output
     * @param inputBytes Tensor of string to be decoded.
     * @param fixedLength Length in bytes for each element of the decoded output. Must be a
     * multiple
     *  of the size of the output type.
     * @param outType
     * @param options carries optional attributes values
     * @return a new instance of DecodePaddedRaw
     * @see org.tensorflow.op.IoOps.decodePaddedRaw
     * @param littleEndian Whether the input `input_bytes` is in little-endian order. Ignored for
     *  `out_type` values that are stored in a single byte, like `uint8`
     */
    @JvmName("decodePaddedRawReified")
    public inline fun <reified T : TNumber> decodePaddedRaw(
        inputBytes: Operand<TString>,
        fixedLength: Operand<TInt32>,
        littleEndian: Boolean? = null
    ): DecodePaddedRaw<T> = decodePaddedRaw<T>(inputBytes, fixedLength, T::class.java, littleEndian)

    /**
     * Reinterpret the bytes of a string as a vector of numbers.
     * 
     * @param T data type for ` output()` output
     * @param bytes All the elements must have the same length.
     * @param outType
     * @param options carries optional attributes values
     * @return a new instance of DecodeRaw
     * @see org.tensorflow.op.IoOps.decodeRaw
     * @param littleEndian Whether the input `bytes` are in little-endian order.
     *  Ignored for `out_type` values that are stored in a single byte like
     *  `uint8`.
     */
    @JvmName("decodeRawReified")
    public inline fun <reified T : TType> decodeRaw(bytes: Operand<TString>, littleEndian: Boolean?
            = null): DecodeRaw<T> = decodeRaw<T>(bytes, T::class.java, littleEndian)

    /**
     * Deserialize and concatenate `SparseTensors` from a serialized minibatch.
     *  
     *  The input `serialized_sparse` must be a string matrix of shape `&#91;N x 3]` where
     *  `N` is the minibatch size and the rows correspond to packed outputs of
     *  `SerializeSparse`.  The ranks of the original `SparseTensor` objects
     *  must all match.  When the final `SparseTensor` is created, it has rank one
     *  higher than the ranks of the incoming `SparseTensor` objects
     *  (they have been concatenated along a new row dimension).
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
     * @param T data type for ` sparseValues()` output
     * @param serializedSparse 2-D, The `N` serialized `SparseTensor` objects.
     *  Must have 3 columns.
     * @param dtype The `dtype` of the serialized `SparseTensor` objects.
     * @return a new instance of DeserializeManySparse
     * @see org.tensorflow.op.IoOps.deserializeManySparse
     */
    @JvmName("deserializeManySparseReified")
    public inline fun <reified T : TType> deserializeManySparse(serializedSparse: Operand<TString>):
            DeserializeManySparse<T> = deserializeManySparse<T>(serializedSparse, T::class.java)

    /**
     * Transforms a serialized tensorflow.TensorProto proto into a Tensor.
     * 
     * @param T data type for ` output()` output
     * @param serialized A scalar string containing a serialized TensorProto proto.
     * @param outType The type of the serialized tensor.  The provided type must match the
     *  type of the serialized tensor and no implicit conversion will take place.
     * @return a new instance of ParseTensor
     * @see org.tensorflow.op.IoOps.parseTensor
     */
    @JvmName("parseTensorReified")
    public inline fun <reified T : TType> parseTensor(serialized: Operand<TString>): ParseTensor<T>
            = parseTensor<T>(serialized, T::class.java)

    /**
     * Serialize an `N`-minibatch `SparseTensor` into an `&#91;N, 3]` `Tensor` object.
     *  
     *  The `SparseTensor` must have rank `R` greater than 1, and the first dimension
     *  is treated as the minibatch dimension.  Elements of the `SparseTensor`
     *  must be sorted in increasing order of this first dimension.  The serialized
     *  `SparseTensor` objects going into each row of `serialized_sparse` will have
     *  rank `R-1`.
     *  
     *  The minibatch size `N` is extracted from `sparse_shape&#91;0]`.
     * 
     * @param U data type for ` serializedSparse()` output
     * @param sparseIndices 2-D.  The `indices` of the minibatch `SparseTensor`.
     * @param sparseValues 1-D.  The `values` of the minibatch `SparseTensor`.
     * @param sparseShape 1-D.  The `shape` of the minibatch `SparseTensor`.
     * @param outType The `dtype` to use for serialization; the supported types are `string`
     *  (default) and `variant`.
     * @return a new instance of SerializeManySparse
     * @see org.tensorflow.op.IoOps.serializeManySparse
     */
    @JvmName("serializeManySparseReified")
    public inline fun <reified U : TType> serializeManySparseTyped(
        sparseIndices: Operand<TInt64>,
        sparseValues: Operand<out TType>,
        sparseShape: Operand<TInt64>
    ): SerializeManySparse<U> = serializeManySparse<U>(sparseIndices, sparseValues, sparseShape,
            U::class.java)

    /**
     * Serialize a `SparseTensor` into a `&#91;3]` `Tensor` object.
     * 
     * @param U data type for ` serializedSparse()` output
     * @param sparseIndices 2-D.  The `indices` of the `SparseTensor`.
     * @param sparseValues 1-D.  The `values` of the `SparseTensor`.
     * @param sparseShape 1-D.  The `shape` of the `SparseTensor`.
     * @param outType The `dtype` to use for serialization; the supported types are `string`
     *  (default) and `variant`.
     * @return a new instance of SerializeSparse
     * @see org.tensorflow.op.IoOps.serializeSparse
     */
    @JvmName("serializeSparseReified")
    public inline fun <reified U : TType> serializeSparseTyped(
        sparseIndices: Operand<TInt64>,
        sparseValues: Operand<out TType>,
        sparseShape: Operand<TInt64>
    ): SerializeSparse<U> = serializeSparse<U>(sparseIndices, sparseValues, sparseShape,
            U::class.java)
}
