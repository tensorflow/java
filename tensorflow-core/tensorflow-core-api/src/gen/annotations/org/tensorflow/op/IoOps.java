package org.tensorflow.op;

import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.nio.nd.Shape;
import org.tensorflow.op.io.DecodeBase64;
import org.tensorflow.op.io.DecodeCompressed;
import org.tensorflow.op.io.DecodeCsv;
import org.tensorflow.op.io.DecodeJsonExample;
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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;

/**
 * An API for building {@code io} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class IoOps {
  private final Scope scope;

  IoOps(Scope scope) {
    this.scope = scope;
  }

  /**
   * Builds an {@link QueueEnqueueMany} operation
   *
   * @param handle The handle to a queue.
   * @param components One or more tensors from which the enqueued tensors should
   * @param options carries optional attributes values
   * @return a new instance of QueueEnqueueMany
   * @see org.tensorflow.op.io.QueueEnqueueMany
   */
  public QueueEnqueueMany queueEnqueueMany(Operand<?> handle, Iterable<Operand<?>> components,
      QueueEnqueueMany.Options... options) {
    return QueueEnqueueMany.create(scope, handle, components, options);
  }

  /**
   * Builds an {@link ShardedFilename} operation
   *
   * @param basename 
   * @param shard 
   * @param numShards 
   * @return a new instance of ShardedFilename
   * @see org.tensorflow.op.io.ShardedFilename
   */
  public ShardedFilename shardedFilename(Operand<TString> basename, Operand<TInt32> shard,
      Operand<TInt32> numShards) {
    return ShardedFilename.create(scope, basename, shard, numShards);
  }

  /**
   * Builds an {@link FifoQueue} operation
   *
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attributes values
   * @return a new instance of FifoQueue
   * @see org.tensorflow.op.io.FifoQueue
   */
  public FifoQueue fifoQueue(List<DataType<?>> componentTypes, FifoQueue.Options... options) {
    return FifoQueue.create(scope, componentTypes, options);
  }

  /**
   * Builds an {@link ReaderReset} operation
   *
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderReset
   * @see org.tensorflow.op.io.ReaderReset
   */
  public ReaderReset readerReset(Operand<?> readerHandle) {
    return ReaderReset.create(scope, readerHandle);
  }

  /**
   * Builds an {@link ReaderReadUpTo} operation
   *
   * @param readerHandle Handle to a `Reader`.
   * @param queueHandle Handle to a `Queue`, with string work items.
   * @param numRecords number of records to read from `Reader`.
   * @return a new instance of ReaderReadUpTo
   * @see org.tensorflow.op.io.ReaderReadUpTo
   */
  public ReaderReadUpTo readerReadUpTo(Operand<?> readerHandle, Operand<?> queueHandle,
      Operand<TInt64> numRecords) {
    return ReaderReadUpTo.create(scope, readerHandle, queueHandle, numRecords);
  }

  /**
   * Builds an {@link IdentityReader} operation
   *
   * @param options carries optional attributes values
   * @return a new instance of IdentityReader
   * @see org.tensorflow.op.io.IdentityReader
   */
  public IdentityReader identityReader(IdentityReader.Options... options) {
    return IdentityReader.create(scope, options);
  }

  /**
   * Builds an {@link DecodeRaw} operation
   *
   * @param bytes All the elements must have the same length.
   * @param outType 
   * @param options carries optional attributes values
   * @return a new instance of DecodeRaw
   * @see org.tensorflow.op.io.DecodeRaw
   */
  public <T> DecodeRaw<T> decodeRaw(Operand<TString> bytes, DataType<T> outType,
      DecodeRaw.Options... options) {
    return DecodeRaw.create(scope, bytes, outType, options);
  }

  /**
   * Builds an {@link DecodeBase64} operation
   *
   * @param input Base64 strings to decode.
   * @return a new instance of DecodeBase64
   * @see org.tensorflow.op.io.DecodeBase64
   */
  public DecodeBase64 decodeBase64(Operand<TString> input) {
    return DecodeBase64.create(scope, input);
  }

  /**
   * Builds an {@link ParseSingleExample} operation
   *
   * @param serialized A vector containing a batch of binary serialized Example protos.
   * @param denseDefaults A list of Tensors (some may be empty), whose length matches
   * @param numSparse The number of sparse features to be parsed from the example. This
   * @param sparseKeys A list of `num_sparse` strings.
   * @param denseKeys The keys expected in the Examples' features associated with dense
   * @param sparseTypes A list of `num_sparse` types; the data types of data in each
   * @param denseShapes The shapes of data in each Feature given in dense_keys.
   * @return a new instance of ParseSingleExample
   * @see org.tensorflow.op.io.ParseSingleExample
   */
  public ParseSingleExample parseSingleExample(Operand<TString> serialized,
      Iterable<Operand<?>> denseDefaults, Long numSparse, List<String> sparseKeys,
      List<String> denseKeys, List<DataType<?>> sparseTypes, List<Shape> denseShapes) {
    return ParseSingleExample.create(scope, serialized, denseDefaults, numSparse, sparseKeys, denseKeys, sparseTypes, denseShapes);
  }

  /**
   * Builds an {@link MatchingFiles} operation
   *
   * @param pattern Shell wildcard pattern(s). Scalar or vector of type string.
   * @return a new instance of MatchingFiles
   * @see org.tensorflow.op.io.MatchingFiles
   */
  public MatchingFiles matchingFiles(Operand<TString> pattern) {
    return MatchingFiles.create(scope, pattern);
  }

  /**
   * Builds an {@link WholeFileReader} operation
   *
   * @param options carries optional attributes values
   * @return a new instance of WholeFileReader
   * @see org.tensorflow.op.io.WholeFileReader
   */
  public WholeFileReader wholeFileReader(WholeFileReader.Options... options) {
    return WholeFileReader.create(scope, options);
  }

  /**
   * Builds an {@link SerializeManySparse} operation
   *
   * @param sparseIndices 2-D.  The `indices` of the minibatch `SparseTensor`.
   * @param sparseValues 1-D.  The `values` of the minibatch `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the minibatch `SparseTensor`.
   * @return a new instance of SerializeManySparse
   * @see org.tensorflow.op.io.SerializeManySparse
   */
  public <T> SerializeManySparse<TString> serializeManySparse(Operand<TInt64> sparseIndices,
      Operand<T> sparseValues, Operand<TInt64> sparseShape) {
    return SerializeManySparse.create(scope, sparseIndices, sparseValues, sparseShape);
  }

  /**
   * Builds an {@link ReaderNumRecordsProduced} operation
   *
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderNumRecordsProduced
   * @see org.tensorflow.op.io.ReaderNumRecordsProduced
   */
  public ReaderNumRecordsProduced readerNumRecordsProduced(Operand<?> readerHandle) {
    return ReaderNumRecordsProduced.create(scope, readerHandle);
  }

  /**
   * Builds an {@link QueueEnqueue} operation
   *
   * @param handle The handle to a queue.
   * @param components One or more tensors from which the enqueued tensors should be taken.
   * @param options carries optional attributes values
   * @return a new instance of QueueEnqueue
   * @see org.tensorflow.op.io.QueueEnqueue
   */
  public QueueEnqueue queueEnqueue(Operand<?> handle, Iterable<Operand<?>> components,
      QueueEnqueue.Options... options) {
    return QueueEnqueue.create(scope, handle, components, options);
  }

  /**
   * Builds an {@link WriteFile} operation
   *
   * @param filename scalar. The name of the file to which we write the contents.
   * @param contents scalar. The content to be written to the output file.
   * @return a new instance of WriteFile
   * @see org.tensorflow.op.io.WriteFile
   */
  public WriteFile writeFile(Operand<TString> filename, Operand<TString> contents) {
    return WriteFile.create(scope, filename, contents);
  }

  /**
   * Builds an {@link PriorityQueue} operation
   *
   * @param componentTypes The type of each component in a value.
   * @param shapes The shape of each component in a value. The length of this attr must
   * @param options carries optional attributes values
   * @return a new instance of PriorityQueue
   * @see org.tensorflow.op.io.PriorityQueue
   */
  public PriorityQueue priorityQueue(List<DataType<?>> componentTypes, List<Shape> shapes,
      PriorityQueue.Options... options) {
    return PriorityQueue.create(scope, componentTypes, shapes, options);
  }

  /**
   * Builds an {@link ReaderSerializeState} operation
   *
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderSerializeState
   * @see org.tensorflow.op.io.ReaderSerializeState
   */
  public ReaderSerializeState readerSerializeState(Operand<?> readerHandle) {
    return ReaderSerializeState.create(scope, readerHandle);
  }

  /**
   * Builds an {@link QueueClose} operation
   *
   * @param handle The handle to a queue.
   * @param options carries optional attributes values
   * @return a new instance of QueueClose
   * @see org.tensorflow.op.io.QueueClose
   */
  public QueueClose queueClose(Operand<?> handle, QueueClose.Options... options) {
    return QueueClose.create(scope, handle, options);
  }

  /**
   * Builds an {@link SerializeManySparse} operation
   *
   * @param sparseIndices 2-D.  The `indices` of the minibatch `SparseTensor`.
   * @param sparseValues 1-D.  The `values` of the minibatch `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the minibatch `SparseTensor`.
   * @param outType The `dtype` to use for serialization; the supported types are `string`
   * @return a new instance of SerializeManySparse
   * @see org.tensorflow.op.io.SerializeManySparse
   */
  public <U, T> SerializeManySparse<U> serializeManySparse(Operand<TInt64> sparseIndices,
      Operand<T> sparseValues, Operand<TInt64> sparseShape, DataType<U> outType) {
    return SerializeManySparse.create(scope, sparseIndices, sparseValues, sparseShape, outType);
  }

  /**
   * Builds an {@link ReadFile} operation
   *
   * @param filename 
   * @return a new instance of ReadFile
   * @see org.tensorflow.op.io.ReadFile
   */
  public ReadFile readFile(Operand<TString> filename) {
    return ReadFile.create(scope, filename);
  }

  /**
   * Builds an {@link QueueDequeueUpTo} operation
   *
   * @param handle The handle to a queue.
   * @param n The number of tuples to dequeue.
   * @param componentTypes The type of each component in a tuple.
   * @param options carries optional attributes values
   * @return a new instance of QueueDequeueUpTo
   * @see org.tensorflow.op.io.QueueDequeueUpTo
   */
  public QueueDequeueUpTo queueDequeueUpTo(Operand<?> handle, Operand<TInt32> n,
      List<DataType<?>> componentTypes, QueueDequeueUpTo.Options... options) {
    return QueueDequeueUpTo.create(scope, handle, n, componentTypes, options);
  }

  /**
   * Builds an {@link ParseSingleSequenceExample} operation
   *
   * @param serialized A scalar containing a binary serialized SequenceExample proto.
   * @param featureListDenseMissingAssumedEmpty A vector listing the
   * @param contextSparseKeys A list of Ncontext_sparse string Tensors (scalars).
   * @param contextDenseKeys A list of Ncontext_dense string Tensors (scalars).
   * @param featureListSparseKeys A list of Nfeature_list_sparse string Tensors
   * @param featureListDenseKeys A list of Nfeature_list_dense string Tensors (scalars).
   * @param contextDenseDefaults A list of Ncontext_dense Tensors (some may be empty).
   * @param debugName A scalar containing the name of the serialized proto.
   * @param contextSparseTypes A list of Ncontext_sparse types; the data types of data in
   * @param featureListDenseTypes 
   * @param featureListSparseTypes A list of Nfeature_list_sparse types; the data types
   * @param options carries optional attributes values
   * @return a new instance of ParseSingleSequenceExample
   * @see org.tensorflow.op.io.ParseSingleSequenceExample
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
   * Builds an {@link ShardedFilespec} operation
   *
   * @param basename 
   * @param numShards 
   * @return a new instance of ShardedFilespec
   * @see org.tensorflow.op.io.ShardedFilespec
   */
  public ShardedFilespec shardedFilespec(Operand<TString> basename, Operand<TInt32> numShards) {
    return ShardedFilespec.create(scope, basename, numShards);
  }

  /**
   * Builds an {@link TfRecordReader} operation
   *
   * @param options carries optional attributes values
   * @return a new instance of TfRecordReader
   * @see org.tensorflow.op.io.TfRecordReader
   */
  public TfRecordReader tfRecordReader(TfRecordReader.Options... options) {
    return TfRecordReader.create(scope, options);
  }

  /**
   * Builds an {@link ParseTensor} operation
   *
   * @param serialized A scalar string containing a serialized TensorProto proto.
   * @param outType The type of the serialized tensor.  The provided type must match the
   * @return a new instance of ParseTensor
   * @see org.tensorflow.op.io.ParseTensor
   */
  public <T> ParseTensor<T> parseTensor(Operand<TString> serialized, DataType<T> outType) {
    return ParseTensor.create(scope, serialized, outType);
  }

  /**
   * Builds an {@link DecodeJsonExample} operation
   *
   * @param jsonExamples Each string is a JSON object serialized according to the JSON
   * @return a new instance of DecodeJsonExample
   * @see org.tensorflow.op.io.DecodeJsonExample
   */
  public DecodeJsonExample decodeJsonExample(Operand<TString> jsonExamples) {
    return DecodeJsonExample.create(scope, jsonExamples);
  }

  /**
   * Builds an {@link EncodeBase64} operation
   *
   * @param input Strings to be encoded.
   * @param options carries optional attributes values
   * @return a new instance of EncodeBase64
   * @see org.tensorflow.op.io.EncodeBase64
   */
  public EncodeBase64 encodeBase64(Operand<TString> input, EncodeBase64.Options... options) {
    return EncodeBase64.create(scope, input, options);
  }

  /**
   * Builds an {@link SerializeSparse} operation
   *
   * @param sparseIndices 2-D.  The `indices` of the `SparseTensor`.
   * @param sparseValues 1-D.  The `values` of the `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the `SparseTensor`.
   * @param outType The `dtype` to use for serialization; the supported types are `string`
   * @return a new instance of SerializeSparse
   * @see org.tensorflow.op.io.SerializeSparse
   */
  public <U, T> SerializeSparse<U> serializeSparse(Operand<TInt64> sparseIndices,
      Operand<T> sparseValues, Operand<TInt64> sparseShape, DataType<U> outType) {
    return SerializeSparse.create(scope, sparseIndices, sparseValues, sparseShape, outType);
  }

  /**
   * Builds an {@link DecodeCsv} operation
   *
   * @param records Each string is a record/row in the csv and all records should have
   * @param recordDefaults One tensor per column of the input record, with either a
   * @param options carries optional attributes values
   * @return a new instance of DecodeCsv
   * @see org.tensorflow.op.io.DecodeCsv
   */
  public DecodeCsv decodeCsv(Operand<TString> records, Iterable<Operand<?>> recordDefaults,
      DecodeCsv.Options... options) {
    return DecodeCsv.create(scope, records, recordDefaults, options);
  }

  /**
   * Builds an {@link QueueDequeueMany} operation
   *
   * @param handle The handle to a queue.
   * @param n The number of tuples to dequeue.
   * @param componentTypes The type of each component in a tuple.
   * @param options carries optional attributes values
   * @return a new instance of QueueDequeueMany
   * @see org.tensorflow.op.io.QueueDequeueMany
   */
  public QueueDequeueMany queueDequeueMany(Operand<?> handle, Operand<TInt32> n,
      List<DataType<?>> componentTypes, QueueDequeueMany.Options... options) {
    return QueueDequeueMany.create(scope, handle, n, componentTypes, options);
  }

  /**
   * Builds an {@link LmdbReader} operation
   *
   * @param options carries optional attributes values
   * @return a new instance of LmdbReader
   * @see org.tensorflow.op.io.LmdbReader
   */
  public LmdbReader lmdbReader(LmdbReader.Options... options) {
    return LmdbReader.create(scope, options);
  }

  /**
   * Builds an {@link ReaderNumWorkUnitsCompleted} operation
   *
   * @param readerHandle Handle to a Reader.
   * @return a new instance of ReaderNumWorkUnitsCompleted
   * @see org.tensorflow.op.io.ReaderNumWorkUnitsCompleted
   */
  public ReaderNumWorkUnitsCompleted readerNumWorkUnitsCompleted(Operand<?> readerHandle) {
    return ReaderNumWorkUnitsCompleted.create(scope, readerHandle);
  }

  /**
   * Builds an {@link ParseExample} operation
   *
   * @param serialized A vector containing a batch of binary serialized Example protos.
   * @param names A vector containing the names of the serialized protos.
   * @param sparseKeys A list of Nsparse string Tensors (scalars).
   * @param denseKeys A list of Ndense string Tensors (scalars).
   * @param denseDefaults A list of Ndense Tensors (some may be empty).
   * @param sparseTypes A list of Nsparse types; the data types of data in each Feature
   * @param denseShapes A list of Ndense shapes; the shapes of data in each Feature
   * @return a new instance of ParseExample
   * @see org.tensorflow.op.io.ParseExample
   */
  public ParseExample parseExample(Operand<TString> serialized, Operand<TString> names,
      Iterable<Operand<TString>> sparseKeys, Iterable<Operand<TString>> denseKeys,
      Iterable<Operand<?>> denseDefaults, List<DataType<?>> sparseTypes, List<Shape> denseShapes) {
    return ParseExample.create(scope, serialized, names, sparseKeys, denseKeys, denseDefaults, sparseTypes, denseShapes);
  }

  /**
   * Builds an {@link ReaderRead} operation
   *
   * @param readerHandle Handle to a Reader.
   * @param queueHandle Handle to a Queue, with string work items.
   * @return a new instance of ReaderRead
   * @see org.tensorflow.op.io.ReaderRead
   */
  public ReaderRead readerRead(Operand<?> readerHandle, Operand<?> queueHandle) {
    return ReaderRead.create(scope, readerHandle, queueHandle);
  }

  /**
   * Builds an {@link ParseSequenceExample} operation
   *
   * @param serialized A vector containing binary serialized SequenceExample protos.
   * @param debugName A vector containing the names of the serialized protos.
   * @param contextDenseDefaults A list of Ncontext_dense Tensors (some may be empty).
   * @param featureListDenseMissingAssumedEmpty A vector listing the
   * @param contextSparseKeys A list of Ncontext_sparse string Tensors (scalars).
   * @param contextDenseKeys A list of Ncontext_dense string Tensors (scalars).
   * @param featureListSparseKeys A list of Nfeature_list_sparse string Tensors
   * @param featureListDenseKeys A list of Nfeature_list_dense string Tensors (scalars).
   * @param contextSparseTypes A list of Ncontext_sparse types; the data types of data in
   * @param featureListDenseTypes 
   * @param featureListSparseTypes A list of Nfeature_list_sparse types; the data types
   * @param options carries optional attributes values
   * @return a new instance of ParseSequenceExample
   * @see org.tensorflow.op.io.ParseSequenceExample
   */
  public ParseSequenceExample parseSequenceExample(Operand<TString> serialized,
      Operand<TString> debugName, Iterable<Operand<?>> contextDenseDefaults,
      List<String> featureListDenseMissingAssumedEmpty, List<String> contextSparseKeys,
      List<String> contextDenseKeys, List<String> featureListSparseKeys,
      List<String> featureListDenseKeys, List<DataType<?>> contextSparseTypes,
      List<DataType<?>> featureListDenseTypes, List<DataType<?>> featureListSparseTypes,
      ParseSequenceExample.Options... options) {
    return ParseSequenceExample.create(scope, serialized, debugName, contextDenseDefaults, featureListDenseMissingAssumedEmpty, contextSparseKeys, contextDenseKeys, featureListSparseKeys, featureListDenseKeys, contextSparseTypes, featureListDenseTypes, featureListSparseTypes, options);
  }

  /**
   * Builds an {@link SerializeTensor} operation
   *
   * @param tensor A Tensor of type `T`.
   * @return a new instance of SerializeTensor
   * @see org.tensorflow.op.io.SerializeTensor
   */
  public <T> SerializeTensor serializeTensor(Operand<T> tensor) {
    return SerializeTensor.create(scope, tensor);
  }

  /**
   * Builds an {@link DecodeCompressed} operation
   *
   * @param bytes A Tensor of string which is compressed.
   * @param options carries optional attributes values
   * @return a new instance of DecodeCompressed
   * @see org.tensorflow.op.io.DecodeCompressed
   */
  public DecodeCompressed decodeCompressed(Operand<TString> bytes,
      DecodeCompressed.Options... options) {
    return DecodeCompressed.create(scope, bytes, options);
  }

  /**
   * Builds an {@link QueueIsClosed} operation
   *
   * @param handle The handle to a queue.
   * @return a new instance of QueueIsClosed
   * @see org.tensorflow.op.io.QueueIsClosed
   */
  public QueueIsClosed queueIsClosed(Operand<?> handle) {
    return QueueIsClosed.create(scope, handle);
  }

  /**
   * Builds an {@link DeserializeManySparse} operation
   *
   * @param serializedSparse 2-D, The `N` serialized `SparseTensor` objects.
   * @param dtype The `dtype` of the serialized `SparseTensor` objects.
   * @return a new instance of DeserializeManySparse
   * @see org.tensorflow.op.io.DeserializeManySparse
   */
  public <T> DeserializeManySparse<T> deserializeManySparse(Operand<TString> serializedSparse,
      DataType<T> dtype) {
    return DeserializeManySparse.create(scope, serializedSparse, dtype);
  }

  /**
   * Builds an {@link QueueSize} operation
   *
   * @param handle The handle to a queue.
   * @return a new instance of QueueSize
   * @see org.tensorflow.op.io.QueueSize
   */
  public QueueSize queueSize(Operand<?> handle) {
    return QueueSize.create(scope, handle);
  }

  /**
   * Builds an {@link RandomShuffleQueue} operation
   *
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attributes values
   * @return a new instance of RandomShuffleQueue
   * @see org.tensorflow.op.io.RandomShuffleQueue
   */
  public RandomShuffleQueue randomShuffleQueue(List<DataType<?>> componentTypes,
      RandomShuffleQueue.Options... options) {
    return RandomShuffleQueue.create(scope, componentTypes, options);
  }

  /**
   * Builds an {@link QueueDequeue} operation
   *
   * @param handle The handle to a queue.
   * @param componentTypes The type of each component in a tuple.
   * @param options carries optional attributes values
   * @return a new instance of QueueDequeue
   * @see org.tensorflow.op.io.QueueDequeue
   */
  public QueueDequeue queueDequeue(Operand<?> handle, List<DataType<?>> componentTypes,
      QueueDequeue.Options... options) {
    return QueueDequeue.create(scope, handle, componentTypes, options);
  }

  /**
   * Builds an {@link TextLineReader} operation
   *
   * @param options carries optional attributes values
   * @return a new instance of TextLineReader
   * @see org.tensorflow.op.io.TextLineReader
   */
  public TextLineReader textLineReader(TextLineReader.Options... options) {
    return TextLineReader.create(scope, options);
  }

  /**
   * Builds an {@link SerializeSparse} operation
   *
   * @param sparseIndices 2-D.  The `indices` of the `SparseTensor`.
   * @param sparseValues 1-D.  The `values` of the `SparseTensor`.
   * @param sparseShape 1-D.  The `shape` of the `SparseTensor`.
   * @return a new instance of SerializeSparse
   * @see org.tensorflow.op.io.SerializeSparse
   */
  public <T> SerializeSparse<TString> serializeSparse(Operand<TInt64> sparseIndices,
      Operand<T> sparseValues, Operand<TInt64> sparseShape) {
    return SerializeSparse.create(scope, sparseIndices, sparseValues, sparseShape);
  }

  /**
   * Builds an {@link FixedLengthRecordReader} operation
   *
   * @param recordBytes Number of bytes in the record.
   * @param options carries optional attributes values
   * @return a new instance of FixedLengthRecordReader
   * @see org.tensorflow.op.io.FixedLengthRecordReader
   */
  public FixedLengthRecordReader fixedLengthRecordReader(Long recordBytes,
      FixedLengthRecordReader.Options... options) {
    return FixedLengthRecordReader.create(scope, recordBytes, options);
  }

  /**
   * Builds an {@link ReaderRestoreState} operation
   *
   * @param readerHandle Handle to a Reader.
   * @param state Result of a ReaderSerializeState of a Reader with type
   * @return a new instance of ReaderRestoreState
   * @see org.tensorflow.op.io.ReaderRestoreState
   */
  public ReaderRestoreState readerRestoreState(Operand<?> readerHandle, Operand<TString> state) {
    return ReaderRestoreState.create(scope, readerHandle, state);
  }

  /**
   * Builds an {@link PaddingFifoQueue} operation
   *
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attributes values
   * @return a new instance of PaddingFifoQueue
   * @see org.tensorflow.op.io.PaddingFifoQueue
   */
  public PaddingFifoQueue paddingFifoQueue(List<DataType<?>> componentTypes,
      PaddingFifoQueue.Options... options) {
    return PaddingFifoQueue.create(scope, componentTypes, options);
  }
}
