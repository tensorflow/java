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

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.DeviceSpec;
import org.tensorflow.EagerSession;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.BooleanNdArray;
import org.tensorflow.ndarray.ByteNdArray;
import org.tensorflow.ndarray.DoubleNdArray;
import org.tensorflow.ndarray.FloatNdArray;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.LongNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.index.Index;
import org.tensorflow.op.core.Abort;
import org.tensorflow.op.core.All;
import org.tensorflow.op.core.Any;
import org.tensorflow.op.core.AssertThat;
import org.tensorflow.op.core.Assign;
import org.tensorflow.op.core.AssignAdd;
import org.tensorflow.op.core.AssignAddVariableOp;
import org.tensorflow.op.core.AssignSub;
import org.tensorflow.op.core.AssignSubVariableOp;
import org.tensorflow.op.core.AssignVariableOp;
import org.tensorflow.op.core.Barrier;
import org.tensorflow.op.core.BarrierClose;
import org.tensorflow.op.core.BarrierIncompleteSize;
import org.tensorflow.op.core.BarrierInsertMany;
import org.tensorflow.op.core.BarrierReadySize;
import org.tensorflow.op.core.BarrierTakeMany;
import org.tensorflow.op.core.Batch;
import org.tensorflow.op.core.BatchFunction;
import org.tensorflow.op.core.BatchToSpace;
import org.tensorflow.op.core.BatchToSpaceNd;
import org.tensorflow.op.core.Bitcast;
import org.tensorflow.op.core.BooleanMask;
import org.tensorflow.op.core.BooleanMaskUpdate;
import org.tensorflow.op.core.BroadcastDynamicShape;
import org.tensorflow.op.core.BroadcastTo;
import org.tensorflow.op.core.Bucketize;
import org.tensorflow.op.core.Case;
import org.tensorflow.op.core.ClipByValue;
import org.tensorflow.op.core.Concat;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.ConsumeMutexLock;
import org.tensorflow.op.core.ControlTrigger;
import org.tensorflow.op.core.CountUpTo;
import org.tensorflow.op.core.DecodeProto;
import org.tensorflow.op.core.DeepCopy;
import org.tensorflow.op.core.DeleteSessionTensor;
import org.tensorflow.op.core.DestroyResourceOp;
import org.tensorflow.op.core.DestroyTemporaryVariable;
import org.tensorflow.op.core.DynamicPartition;
import org.tensorflow.op.core.DynamicStitch;
import org.tensorflow.op.core.EditDistance;
import org.tensorflow.op.core.Empty;
import org.tensorflow.op.core.EmptyTensorList;
import org.tensorflow.op.core.EmptyTensorMap;
import org.tensorflow.op.core.EncodeProto;
import org.tensorflow.op.core.EnsureShape;
import org.tensorflow.op.core.ExpandDims;
import org.tensorflow.op.core.ExtractVolumePatches;
import org.tensorflow.op.core.Fill;
import org.tensorflow.op.core.Fingerprint;
import org.tensorflow.op.core.For;
import org.tensorflow.op.core.Function;
import org.tensorflow.op.core.Gather;
import org.tensorflow.op.core.GatherNd;
import org.tensorflow.op.core.GetSessionHandle;
import org.tensorflow.op.core.GetSessionTensor;
import org.tensorflow.op.core.Gradients;
import org.tensorflow.op.core.GuaranteeConst;
import org.tensorflow.op.core.HashTable;
import org.tensorflow.op.core.Helpers;
import org.tensorflow.op.core.HistogramFixedWidth;
import org.tensorflow.op.core.Identity;
import org.tensorflow.op.core.IdentityN;
import org.tensorflow.op.core.If;
import org.tensorflow.op.core.ImmutableConst;
import org.tensorflow.op.core.Init;
import org.tensorflow.op.core.InitializeTable;
import org.tensorflow.op.core.InitializeTableFromTextFile;
import org.tensorflow.op.core.InplaceAdd;
import org.tensorflow.op.core.InplaceSub;
import org.tensorflow.op.core.InplaceUpdate;
import org.tensorflow.op.core.IsVariableInitialized;
import org.tensorflow.op.core.KthOrderStatistic;
import org.tensorflow.op.core.LookupTableExport;
import org.tensorflow.op.core.LookupTableFind;
import org.tensorflow.op.core.LookupTableImport;
import org.tensorflow.op.core.LookupTableInsert;
import org.tensorflow.op.core.LookupTableSize;
import org.tensorflow.op.core.LoopCond;
import org.tensorflow.op.core.MakeUnique;
import org.tensorflow.op.core.MapClear;
import org.tensorflow.op.core.MapIncompleteSize;
import org.tensorflow.op.core.MapPeek;
import org.tensorflow.op.core.MapSize;
import org.tensorflow.op.core.MapStage;
import org.tensorflow.op.core.MapUnstage;
import org.tensorflow.op.core.MapUnstageNoKey;
import org.tensorflow.op.core.Max;
import org.tensorflow.op.core.Merge;
import org.tensorflow.op.core.Min;
import org.tensorflow.op.core.MirrorPad;
import org.tensorflow.op.core.MlirPassthroughOp;
import org.tensorflow.op.core.MutableDenseHashTable;
import org.tensorflow.op.core.MutableHashTable;
import org.tensorflow.op.core.MutableHashTableOfTensors;
import org.tensorflow.op.core.Mutex;
import org.tensorflow.op.core.MutexLock;
import org.tensorflow.op.core.NextIteration;
import org.tensorflow.op.core.NoOp;
import org.tensorflow.op.core.OneHot;
import org.tensorflow.op.core.Ones;
import org.tensorflow.op.core.OnesLike;
import org.tensorflow.op.core.OrderedMapClear;
import org.tensorflow.op.core.OrderedMapIncompleteSize;
import org.tensorflow.op.core.OrderedMapPeek;
import org.tensorflow.op.core.OrderedMapSize;
import org.tensorflow.op.core.OrderedMapStage;
import org.tensorflow.op.core.OrderedMapUnstage;
import org.tensorflow.op.core.OrderedMapUnstageNoKey;
import org.tensorflow.op.core.Pad;
import org.tensorflow.op.core.ParallelConcat;
import org.tensorflow.op.core.ParallelDynamicStitch;
import org.tensorflow.op.core.PartitionedCall;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.PlaceholderWithDefault;
import org.tensorflow.op.core.Print;
import org.tensorflow.op.core.Prod;
import org.tensorflow.op.core.QuantizedReshape;
import org.tensorflow.op.core.Range;
import org.tensorflow.op.core.Rank;
import org.tensorflow.op.core.ReadVariableOp;
import org.tensorflow.op.core.ReduceAll;
import org.tensorflow.op.core.ReduceAny;
import org.tensorflow.op.core.ReduceMax;
import org.tensorflow.op.core.ReduceMin;
import org.tensorflow.op.core.ReduceProd;
import org.tensorflow.op.core.ReduceSum;
import org.tensorflow.op.core.RefNextIteration;
import org.tensorflow.op.core.RefSelect;
import org.tensorflow.op.core.RefSwitch;
import org.tensorflow.op.core.RemoteCall;
import org.tensorflow.op.core.Reshape;
import org.tensorflow.op.core.ResourceCountUpTo;
import org.tensorflow.op.core.ResourceGather;
import org.tensorflow.op.core.ResourceGatherNd;
import org.tensorflow.op.core.ResourceScatterAdd;
import org.tensorflow.op.core.ResourceScatterDiv;
import org.tensorflow.op.core.ResourceScatterMax;
import org.tensorflow.op.core.ResourceScatterMin;
import org.tensorflow.op.core.ResourceScatterMul;
import org.tensorflow.op.core.ResourceScatterNdAdd;
import org.tensorflow.op.core.ResourceScatterNdMax;
import org.tensorflow.op.core.ResourceScatterNdMin;
import org.tensorflow.op.core.ResourceScatterNdSub;
import org.tensorflow.op.core.ResourceScatterNdUpdate;
import org.tensorflow.op.core.ResourceScatterSub;
import org.tensorflow.op.core.ResourceScatterUpdate;
import org.tensorflow.op.core.ResourceStridedSliceAssign;
import org.tensorflow.op.core.Reverse;
import org.tensorflow.op.core.ReverseSequence;
import org.tensorflow.op.core.Roll;
import org.tensorflow.op.core.ScatterAdd;
import org.tensorflow.op.core.ScatterDiv;
import org.tensorflow.op.core.ScatterMax;
import org.tensorflow.op.core.ScatterMin;
import org.tensorflow.op.core.ScatterMul;
import org.tensorflow.op.core.ScatterNd;
import org.tensorflow.op.core.ScatterNdAdd;
import org.tensorflow.op.core.ScatterNdNonAliasingAdd;
import org.tensorflow.op.core.ScatterNdSub;
import org.tensorflow.op.core.ScatterNdUpdate;
import org.tensorflow.op.core.ScatterSub;
import org.tensorflow.op.core.ScatterUpdate;
import org.tensorflow.op.core.Select;
import org.tensorflow.op.core.SetDiff1d;
import org.tensorflow.op.core.SetSize;
import org.tensorflow.op.core.ShapeN;
import org.tensorflow.op.core.Size;
import org.tensorflow.op.core.Skipgram;
import org.tensorflow.op.core.Slice;
import org.tensorflow.op.core.Snapshot;
import org.tensorflow.op.core.SpaceToBatchNd;
import org.tensorflow.op.core.Split;
import org.tensorflow.op.core.SplitV;
import org.tensorflow.op.core.Squeeze;
import org.tensorflow.op.core.Stack;
import org.tensorflow.op.core.Stage;
import org.tensorflow.op.core.StageClear;
import org.tensorflow.op.core.StagePeek;
import org.tensorflow.op.core.StageSize;
import org.tensorflow.op.core.StatefulCase;
import org.tensorflow.op.core.StatefulIf;
import org.tensorflow.op.core.StatefulPartitionedCall;
import org.tensorflow.op.core.StatefulWhile;
import org.tensorflow.op.core.StatelessIf;
import org.tensorflow.op.core.StatelessPartitionedCall;
import org.tensorflow.op.core.StatelessWhile;
import org.tensorflow.op.core.StopGradient;
import org.tensorflow.op.core.StridedSlice;
import org.tensorflow.op.core.StridedSliceAssign;
import org.tensorflow.op.core.StridedSliceGrad;
import org.tensorflow.op.core.StridedSliceHelper;
import org.tensorflow.op.core.Sum;
import org.tensorflow.op.core.SwitchCond;
import org.tensorflow.op.core.TemporaryVariable;
import org.tensorflow.op.core.TensorArray;
import org.tensorflow.op.core.TensorArrayClose;
import org.tensorflow.op.core.TensorArrayConcat;
import org.tensorflow.op.core.TensorArrayGather;
import org.tensorflow.op.core.TensorArrayGrad;
import org.tensorflow.op.core.TensorArrayGradWithShape;
import org.tensorflow.op.core.TensorArrayPack;
import org.tensorflow.op.core.TensorArrayRead;
import org.tensorflow.op.core.TensorArrayScatter;
import org.tensorflow.op.core.TensorArraySize;
import org.tensorflow.op.core.TensorArraySplit;
import org.tensorflow.op.core.TensorArrayUnpack;
import org.tensorflow.op.core.TensorArrayWrite;
import org.tensorflow.op.core.TensorListConcat;
import org.tensorflow.op.core.TensorListConcatLists;
import org.tensorflow.op.core.TensorListElementShape;
import org.tensorflow.op.core.TensorListFromTensor;
import org.tensorflow.op.core.TensorListGather;
import org.tensorflow.op.core.TensorListGetItem;
import org.tensorflow.op.core.TensorListLength;
import org.tensorflow.op.core.TensorListPopBack;
import org.tensorflow.op.core.TensorListPushBack;
import org.tensorflow.op.core.TensorListPushBackBatch;
import org.tensorflow.op.core.TensorListReserve;
import org.tensorflow.op.core.TensorListResize;
import org.tensorflow.op.core.TensorListScatter;
import org.tensorflow.op.core.TensorListScatterIntoExistingList;
import org.tensorflow.op.core.TensorListSetItem;
import org.tensorflow.op.core.TensorListSplit;
import org.tensorflow.op.core.TensorListStack;
import org.tensorflow.op.core.TensorMapErase;
import org.tensorflow.op.core.TensorMapHasKey;
import org.tensorflow.op.core.TensorMapInsert;
import org.tensorflow.op.core.TensorMapLookup;
import org.tensorflow.op.core.TensorMapSize;
import org.tensorflow.op.core.TensorMapStackKeys;
import org.tensorflow.op.core.TensorScatterNdAdd;
import org.tensorflow.op.core.TensorScatterNdMax;
import org.tensorflow.op.core.TensorScatterNdMin;
import org.tensorflow.op.core.TensorScatterNdSub;
import org.tensorflow.op.core.TensorScatterNdUpdate;
import org.tensorflow.op.core.TensorStridedSliceUpdate;
import org.tensorflow.op.core.Tile;
import org.tensorflow.op.core.Timestamp;
import org.tensorflow.op.core.TopKUnique;
import org.tensorflow.op.core.TopKWithUnique;
import org.tensorflow.op.core.Unbatch;
import org.tensorflow.op.core.UnbatchGrad;
import org.tensorflow.op.core.Unique;
import org.tensorflow.op.core.UniqueWithCounts;
import org.tensorflow.op.core.UnravelIndex;
import org.tensorflow.op.core.Unstack;
import org.tensorflow.op.core.Unstage;
import org.tensorflow.op.core.VarHandleOp;
import org.tensorflow.op.core.VarIsInitializedOp;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.core.VariableShape;
import org.tensorflow.op.core.Where;
import org.tensorflow.op.core.While;
import org.tensorflow.op.core.Zeros;
import org.tensorflow.op.core.ZerosLike;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building operations as {@link Op Op}s
 * <p>
 * Any operation wrapper found in the classpath properly annotated as an{@link org.tensorflow.op.annotation.Operator @Operator} is exposed
 * by this API or one of its subgroup.
 * <p>Example usage:
 * <pre>{@code
 * try (Graph g = new Graph()) {
 *   Ops tf = Ops.create(g);
 *   // Operations are typed classes with convenience
 *   // builders in Ops.
 *   Constant<TInt32> three = tf.constant(3);
 *   // Single-result operations implement the Operand
 *   // interface, so this works too.
 *   Operand<TInt32> four = tf.constant(4);
 *   // Most builders are found within a group, and accept
 *   // Operand types as operands
 *   Operand<TInt32> nine = tf.math.add(four, tf.constant(5));
 *   // Multi-result operations however offer methods to
 *   // select a particular result for use.
 *   Operand<TInt32> result = 
 *       tf.math.add(tf.unique(s, a).y(), b);
 *   // Optional attributes
 *   tf.linalg.matMul(a, b, MatMul.transposeA(true));
 *   // Naming operators
 *   tf.withName("foo").constant(5); // name "foo"
 *   // Names can exist in a hierarchy
 *   Ops sub = tf.withSubScope("sub");
 *   sub.withName("bar").constant(4); // "sub/bar"
 * }
 * }</pre>
 */
public final class Ops {
  public final NnOps nn;

  public final SummaryOps summary;

  public final ImageOps image;

  public final RaggedOps ragged;

  public final DataOps data;

  public final ShapeOps shape;

  public final IoOps io;

  public final DtypesOps dtypes;

  public final XlaOps xla;

  public final LinalgOps linalg;

  public final RandomOps random;

  public final StringsOps strings;

  public final SparseOps sparse;

  public final TpuOps tpu;

  public final BitwiseOps bitwise;

  public final MathOps math;

  public final AudioOps audio;

  public final SignalOps signal;

  public final TrainOps train;

  public final QuantizationOps quantization;

  private final Scope scope;

  Ops(Scope scope) {
    this.scope = scope;
    nn = new NnOps(this);
    summary = new SummaryOps(this);
    image = new ImageOps(this);
    ragged = new RaggedOps(this);
    data = new DataOps(this);
    shape = new ShapeOps(this);
    io = new IoOps(this);
    dtypes = new DtypesOps(this);
    xla = new XlaOps(this);
    linalg = new LinalgOps(this);
    random = new RandomOps(this);
    strings = new StringsOps(this);
    sparse = new SparseOps(this);
    tpu = new TpuOps(this);
    bitwise = new BitwiseOps(this);
    math = new MathOps(this);
    audio = new AudioOps(this);
    signal = new SignalOps(this);
    train = new TrainOps(this);
    quantization = new QuantizationOps(this);
  }

  /**
   * Raise a exception to abort the process when called.
   *  If exit_without_error is true, the process will exit normally,
   *  otherwise it will exit with a SIGABORT signal.
   *  <p>Returns nothing but an exception.
   *
   * @param options carries optional attribute values
   * @return a new instance of Abort
   */
  public Abort abort(Abort.Options... options) {
    return Abort.create(scope, options);
  }

  /**
   * Computes the &quot;logical and&quot; of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @return a new instance of All
   */
  public All all(Operand<TBool> input, Operand<? extends TNumber> axis, All.Options... options) {
    return All.create(scope, input, axis, options);
  }

  /**
   * Computes the &quot;logical or&quot; of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @return a new instance of Any
   */
  public Any any(Operand<TBool> input, Operand<? extends TNumber> axis, Any.Options... options) {
    return Any.create(scope, input, axis, options);
  }

  /**
   * Creates a constant of {@code String} elements, using the default UTF-8 charset.
   *
   * @param data An array containing the values to put into the new constant.
   * @return the {@code String} constant
   */
  public Constant<TString> array(String... data) {
    return Constant.arrayOf(scope, data);
  }

  /**
   * Creates a constant of {@code int} elements.
   *
   * @param data An array containing the values to put into the new constant.
   * @return a float constant
   */
  public Constant<TInt32> array(int... data) {
    return Constant.arrayOf(scope, data);
  }

  /**
   * Creates a constant of {@code double} elements.
   *
   * @param data An array containing the values to put into the new constant.
   * @return a double constant
   */
  public Constant<TFloat64> array(double... data) {
    return Constant.arrayOf(scope, data);
  }

  /**
   * Creates a constant of {@code long} elements.
   *
   * @param data An array containing the values to put into the new constant.
   * @return a long constant
   */
  public Constant<TInt64> array(long... data) {
    return Constant.arrayOf(scope, data);
  }

  /**
   * Creates a constant of {@code byte} elements.
   *
   * @param data An array containing the values to put into the new constant.
   * @return a byte constant
   */
  public Constant<TUint8> array(byte... data) {
    return Constant.arrayOf(scope, data);
  }

  /**
   * Creates a constant of {@code boolean} elements.
   *
   * @param data An array containing the values to put into the new constant.
   * @return a boolean constant
   */
  public Constant<TBool> array(boolean... data) {
    return Constant.arrayOf(scope, data);
  }

  /**
   * Creates a constant of {@code float} elements.
   *
   * @param data An array containing the values to put into the new constant.
   * @return a float constant
   */
  public Constant<TFloat32> array(float... data) {
    return Constant.arrayOf(scope, data);
  }

  /**
   * Creates a constant of {@code String} elements, using the given charset.
   *
   * @param charset charset for encoding/decoding strings bytes.
   * @param data An array containing the values to put into the new constant. String elements are
   *      sequences of bytes from the last array dimension.
   * @return the {@code String} constant
   */
  public Constant<TString> array(Charset charset, String... data) {
    return Constant.arrayOf(scope, charset, data);
  }

  /**
   * Asserts that the given condition is true.
   *  If {@code condition} evaluates to false, print the list of tensors in {@code data}.
   *  {@code summarize} determines how many entries of the tensors to print.
   *
   * @param condition The condition to evaluate.
   * @param data The tensors to print out when condition is false.
   * @param options carries optional attribute values
   * @return a new instance of AssertThat
   */
  public AssertThat assertThat(Operand<TBool> condition, Iterable<Operand<?>> data,
      AssertThat.Options... options) {
    return AssertThat.create(scope, condition, data, options);
  }

  /**
   * Update 'ref' by assigning 'value' to it.
   *  This operation outputs &quot;ref&quot; after the assignment is done.
   *  This makes it easier to chain operations that need to use the reset value.
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref Should be from a {@code Variable} node. May be uninitialized.
   * @param value The value to be assigned to the variable.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Assign} output and operands
   * @return a new instance of Assign
   */
  public <T extends TType> Assign<T> assign(Operand<T> ref, Operand<T> value,
      Assign.Options... options) {
    return Assign.create(scope, ref, value, options);
  }

  /**
   * Update 'ref' by adding 'value' to it.
   *  This operation outputs &quot;ref&quot; after the update is done.
   *  This makes it easier to chain operations that need to use the reset value.
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref Should be from a {@code Variable} node.
   * @param value The value to be added to the variable.
   * @param options carries optional attribute values
   * @param <T> data type for {@code AssignAdd} output and operands
   * @return a new instance of AssignAdd
   */
  public <T extends TType> AssignAdd<T> assignAdd(Operand<T> ref, Operand<T> value,
      AssignAdd.Options... options) {
    return AssignAdd.create(scope, ref, value, options);
  }

  /**
   * Adds a value to the current value of a variable.
   *  Any ReadVariableOp with a control dependency on this op is guaranteed to
   *  see the incremented value or a subsequent newer one.
   *
   * @param resource handle to the resource in which to store the variable.
   * @param value the value by which the variable will be incremented.
   * @return a new instance of AssignAddVariableOp
   */
  public AssignAddVariableOp assignAddVariableOp(Operand<? extends TType> resource,
      Operand<? extends TType> value) {
    return AssignAddVariableOp.create(scope, resource, value);
  }

  /**
   * Update 'ref' by subtracting 'value' from it.
   *  This operation outputs &quot;ref&quot; after the update is done.
   *  This makes it easier to chain operations that need to use the reset value.
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref Should be from a {@code Variable} node.
   * @param value The value to be subtracted to the variable.
   * @param options carries optional attribute values
   * @param <T> data type for {@code AssignSub} output and operands
   * @return a new instance of AssignSub
   */
  public <T extends TType> AssignSub<T> assignSub(Operand<T> ref, Operand<T> value,
      AssignSub.Options... options) {
    return AssignSub.create(scope, ref, value, options);
  }

  /**
   * Subtracts a value from the current value of a variable.
   *  Any ReadVariableOp with a control dependency on this op is guaranteed to
   *  see the decremented value or a subsequent newer one.
   *
   * @param resource handle to the resource in which to store the variable.
   * @param value the value by which the variable will be incremented.
   * @return a new instance of AssignSubVariableOp
   */
  public AssignSubVariableOp assignSubVariableOp(Operand<? extends TType> resource,
      Operand<? extends TType> value) {
    return AssignSubVariableOp.create(scope, resource, value);
  }

  /**
   * Assigns a new value to a variable.
   *  Any ReadVariableOp with a control dependency on this op is guaranteed to return
   *  this value or a subsequent newer value of the variable.
   *
   * @param resource handle to the resource in which to store the variable.
   * @param value the value to set the new tensor to use.
   * @return a new instance of AssignVariableOp
   */
  public AssignVariableOp assignVariableOp(Operand<? extends TType> resource,
      Operand<? extends TType> value) {
    return AssignVariableOp.create(scope, resource, value);
  }

  /**
   * Defines a barrier that persists across different graph executions.
   *  A barrier represents a key-value map, where each key is a string, and
   *  each value is a tuple of tensors.
   *  <p>At runtime, the barrier contains 'complete' and 'incomplete'
   *  elements. A complete element has defined tensors for all components of
   *  its value tuple, and may be accessed using BarrierTakeMany. An
   *  incomplete element has some undefined components in its value tuple,
   *  and may be updated using BarrierInsertMany.
   *
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attribute values
   * @return a new instance of Barrier
   */
  public Barrier barrier(List<Class<? extends TType>> componentTypes, Barrier.Options... options) {
    return Barrier.create(scope, componentTypes, options);
  }

  /**
   * Closes the given barrier.
   *  This operation signals that no more new elements will be inserted in the
   *  given barrier. Subsequent InsertMany that try to introduce a new key will fail.
   *  Subsequent InsertMany operations that just add missing components to already
   *  existing elements will continue to succeed. Subsequent TakeMany operations will
   *  continue to succeed if sufficient completed elements remain in the barrier.
   *  Subsequent TakeMany operations that would block will fail immediately.
   *
   * @param handle The handle to a barrier.
   * @param options carries optional attribute values
   * @return a new instance of BarrierClose
   */
  public BarrierClose barrierClose(Operand<TString> handle, BarrierClose.Options... options) {
    return BarrierClose.create(scope, handle, options);
  }

  /**
   * Computes the number of incomplete elements in the given barrier.
   *
   * @param handle The handle to a barrier.
   * @return a new instance of BarrierIncompleteSize
   */
  public BarrierIncompleteSize barrierIncompleteSize(Operand<TString> handle) {
    return BarrierIncompleteSize.create(scope, handle);
  }

  /**
   * For each key, assigns the respective value to the specified component.
   *  If a key is not found in the barrier, this operation will create a new
   *  incomplete element. If a key is found in the barrier, and the element
   *  already has a value at component_index, this operation will fail with
   *  INVALID_ARGUMENT, and leave the barrier in an undefined state.
   *
   * @param handle The handle to a barrier.
   * @param keys A one-dimensional tensor of keys, with length n.
   * @param values An any-dimensional tensor of values, which are associated with the
   *  respective keys. The 0th dimension must have length n.
   * @param componentIndex The component of the barrier elements that is being assigned.
   * @return a new instance of BarrierInsertMany
   */
  public BarrierInsertMany barrierInsertMany(Operand<TString> handle, Operand<TString> keys,
      Operand<? extends TType> values, Long componentIndex) {
    return BarrierInsertMany.create(scope, handle, keys, values, componentIndex);
  }

  /**
   * Computes the number of complete elements in the given barrier.
   *
   * @param handle The handle to a barrier.
   * @return a new instance of BarrierReadySize
   */
  public BarrierReadySize barrierReadySize(Operand<TString> handle) {
    return BarrierReadySize.create(scope, handle);
  }

  /**
   * Takes the given number of completed elements from a barrier.
   *  This operation concatenates completed-element component tensors along
   *  the 0th dimension to make a single component tensor.
   *  <p>Elements come out of the barrier when they are complete, and in the order
   *  in which they were placed into the barrier.  The indices output provides
   *  information about the batch in which each element was originally inserted
   *  into the barrier.
   *
   * @param handle The handle to a barrier.
   * @param numElements A single-element tensor containing the number of elements to
   *  take.
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attribute values
   * @return a new instance of BarrierTakeMany
   */
  public BarrierTakeMany barrierTakeMany(Operand<TString> handle, Operand<TInt32> numElements,
      List<Class<? extends TType>> componentTypes, BarrierTakeMany.Options... options) {
    return BarrierTakeMany.create(scope, handle, numElements, componentTypes, options);
  }

  /**
   * Batches all input tensors nondeterministically.
   *  When many instances of this Op are being run concurrently with the same
   *  container/shared_name in the same device, some will output zero-shaped Tensors
   *  and others will output Tensors of size up to max_batch_size.
   *  <p>All Tensors in in_tensors are batched together (so, for example, labels and
   *  features should be batched with a single instance of this operation.
   *  <p>Each invocation of batch emits an {@code id} scalar which will be used to identify
   *  this particular invocation when doing unbatch or its gradient.
   *  <p>Each op which emits a non-empty batch will also emit a non-empty batch_index
   *  Tensor, which, is a [K, 3] matrix where each row contains the invocation's id,
   *  start, and length of elements of each set of Tensors present in batched_tensors.
   *  <p>Batched tensors are concatenated along the first dimension, and all tensors in
   *  in_tensors must have the first dimension of the same size.
   *  <p>in_tensors: The tensors to be batched.
   *  num_batch_threads: Number of scheduling threads for processing batches of work.
   *  Determines the number of batches processed in parallel.
   *  max_batch_size: Batch sizes will never be bigger than this.
   *  batch_timeout_micros: Maximum number of microseconds to wait before outputting
   *  an incomplete batch.
   *  allowed_batch_sizes: Optional list of allowed batch sizes. If left empty, does
   *  nothing. Otherwise, supplies a list of batch sizes, causing the op to pad
   *  batches up to one of those sizes. The entries must increase monotonically, and
   *  the final entry must equal max_batch_size.
   *  grad_timeout_micros: The timeout to use for the gradient. See Unbatch.
   *  batched_tensors: Either empty tensors or a batch of concatenated Tensors.
   *  batch_index: If out_tensors is non-empty, has information to invert it.
   *  container: Controls the scope of sharing of this batch.
   *  id: always contains a scalar with a unique ID for this invocation of Batch.
   *  shared_name: Concurrently running instances of batch in the same device with the
   *  same container and shared_name will batch their elements together. If left
   *  empty, the op name will be used as the shared name.
   *  T: the types of tensors to be batched.
   *
   * @param inTensors the inTensors value
   * @param numBatchThreads the value of the numBatchThreads property
   * @param maxBatchSize the value of the maxBatchSize property
   * @param batchTimeoutMicros the value of the batchTimeoutMicros property
   * @param gradTimeoutMicros the value of the gradTimeoutMicros property
   * @param options carries optional attribute values
   * @return a new instance of Batch
   */
  public Batch batch(Iterable<Operand<?>> inTensors, Long numBatchThreads, Long maxBatchSize,
      Long batchTimeoutMicros, Long gradTimeoutMicros, Batch.Options... options) {
    return Batch.create(scope, inTensors, numBatchThreads, maxBatchSize, batchTimeoutMicros, gradTimeoutMicros, options);
  }

  /**
   * Batches all the inputs tensors to the computation done by the function.
   *  So, for example, in the following code
   *  <pre>
   *
   *  # This input will be captured.
   *  y = tf.placeholder_with_default(1.0, shape=[])
   *
   *  {@literal @}tf.Defun(tf.float32)
   *  def computation(a):
   *    return tf.matmul(a, a) + y
   *
   *  b = gen_batch_ops.batch_function(
   *          f=computation
   *          in_tensors=[a],
   *          captured_tensors=computation.captured_inputs,
   *          Tout=[o.type for o in computation.definition.signature.output_arg],
   *          num_batch_threads=1,
   *          max_batch_size=10,
   *          batch_timeout_micros=100000,  # 100ms
   *          allowed_batch_sizes=[3, 10],
   *          batching_queue=&quot;&quot;)
   *  </pre>
   *  <p>If more than one session.run call is simultaneously trying to compute {@code b}
   *  the values of {@code a} will be gathered, non-deterministically concatenated
   *  along the first axis, and only one thread will run the computation.
   *  <p>Assumes that all arguments of the function are Tensors which will be batched
   *  along their first dimension.
   *  <p>Arguments that are captured, are not batched. The session.run call which does
   *  the concatenation, will use the values of the captured tensors available to it.
   *  Therefore, typical uses of captured tensors should involve values which remain
   *  unchanged across session.run calls. Inference is a good example of this.
   *  <p>SparseTensor is not supported. The return value of the decorated function
   *  must be a Tensor or a list/tuple of Tensors.
   *
   * @param inTensors The tensors to be batched.
   * @param capturedTensors The tensors which are captured in the function, and don't need
   *  to be batched.
   * @param f the value of the f property
   * @param numBatchThreads Number of scheduling threads for processing batches of work.
   *  Determines the number of batches processed in parallel.
   * @param maxBatchSize Batch sizes will never be bigger than this.
   * @param batchTimeoutMicros Maximum number of microseconds to wait before outputting
   *  an incomplete batch.
   * @param Tout the types of the output tensors.
   * @param options carries optional attribute values
   * @return a new instance of BatchFunction
   */
  public BatchFunction batchFunction(Iterable<Operand<?>> inTensors,
      Iterable<Operand<?>> capturedTensors, ConcreteFunction f, Long numBatchThreads,
      Long maxBatchSize, Long batchTimeoutMicros, List<Class<? extends TType>> Tout,
      BatchFunction.Options... options) {
    return BatchFunction.create(scope, inTensors, capturedTensors, f, numBatchThreads, maxBatchSize, batchTimeoutMicros, Tout, options);
  }

  /**
   * BatchToSpace for 4-D tensors of type T.
   *  This is a legacy version of the more general BatchToSpaceND.
   *  <p>Rearranges (permutes) data from batch into blocks of spatial data, followed by
   *  cropping. This is the reverse transformation of SpaceToBatch. More specifically,
   *  this op outputs a copy of the input tensor where values from the {@code batch}
   *  dimension are moved in spatial blocks to the {@code height} and {@code width} dimensions,
   *  followed by cropping along the {@code height} and {@code width} dimensions.
   *
   * @param <T> data type for {@code output} output
   * @param input 4-D tensor with shape
   *  {@code [batch*block_size*block_size, height_pad/block_size, width_pad/block_size, depth]}. Note that the batch size of the input tensor must be divisible by
   *  {@code block_size * block_size}.
   * @param crops 2-D tensor of non-negative integers with shape {@code [2, 2]}. It specifies
   *  how many elements to crop from the intermediate result across the spatial
   *  dimensions as follows:
   *  <pre>
   *  crops = [[crop_top, crop_bottom], [crop_left, crop_right]]
   *  </pre>
   * @param blockSize the value of the blockSize property
   * @param <T> data type for {@code BatchToSpace} output and operands
   * @return a new instance of BatchToSpace
   */
  public <T extends TType> BatchToSpace<T> batchToSpace(Operand<T> input,
      Operand<? extends TNumber> crops, Long blockSize) {
    return BatchToSpace.create(scope, input, crops, blockSize);
  }

  /**
   * BatchToSpace for N-D tensors of type T.
   *  This operation reshapes the &quot;batch&quot; dimension 0 into {@code M + 1} dimensions of shape
   *  {@code block_shape + [batch]}, interleaves these blocks back into the grid defined by
   *  the spatial dimensions {@code [1, ..., M]}, to obtain a result with the same rank as
   *  the input.  The spatial dimensions of this intermediate result are then
   *  optionally cropped according to {@code crops} to produce the output.  This is the
   *  reverse of SpaceToBatch.  See below for a precise description.
   *
   * @param <T> data type for {@code output} output
   * @param input N-D with shape {@code input_shape = [batch] + spatial_shape + remaining_shape},
   *  where spatial_shape has M dimensions.
   * @param blockShape 1-D with shape {@code [M]}, all values must be &gt;= 1.
   * @param crops 2-D with shape {@code [M, 2]}, all values must be &gt;= 0.
   *  {@code crops[i] = [crop_start, crop_end]} specifies the amount to crop from input
   *  dimension {@code i + 1}, which corresponds to spatial dimension {@code i}.  It is
   *  required that
   *  {@code crop_start[i] + crop_end[i] <= block_shape[i] * input_shape[i + 1]}.
   *  <p>This operation is equivalent to the following steps:
   *  <ol>
   *  <li>
   *  <p>Reshape {@code input} to {@code reshaped} of shape:
   *  [block_shape[0], ..., block_shape[M-1],
   *  batch / prod(block_shape),
   *  input_shape[1], ..., input_shape[N-1]]
   *  </li>
   *  <li>
   *  <p>Permute dimensions of {@code reshaped} to produce {@code permuted} of shape
   *  [batch / prod(block_shape),
   *  <p>input_shape[1], block_shape[0],
   *  ...,
   *  input_shape[M], block_shape[M-1],
   *  <p>input_shape[M+1], ..., input_shape[N-1]]
   *  </li>
   *  <li>
   *  <p>Reshape {@code permuted} to produce {@code reshaped_permuted} of shape
   *  [batch / prod(block_shape),
   *  <p>input_shape[1] * block_shape[0],
   *  ...,
   *  input_shape[M] * block_shape[M-1],
   *  <p>input_shape[M+1],
   *  ...,
   *  input_shape[N-1]]
   *  </li>
   *  <li>
   *  <p>Crop the start and end of dimensions {@code [1, ..., M]} of
   *  {@code reshaped_permuted} according to {@code crops} to produce the output of shape:
   *  [batch / prod(block_shape),
   *  <p>input_shape[1] * block_shape[0] - crops[0,0] - crops[0,1],
   *  ...,
   *  input_shape[M] * block_shape[M-1] - crops[M-1,0] - crops[M-1,1],
   *  <p>input_shape[M+1], ..., input_shape[N-1]]
   *  </li>
   *  </ol>
   *  <p>Some examples:
   *  <p>(1) For the following input of shape {@code [4, 1, 1, 1]}, {@code block_shape = [2, 2]}, and
   *  {@code crops = [[0, 0], [0, 0]]}:
   *  <pre>
   *  [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
   *  </pre>
   *  <p>The output tensor has shape {@code [1, 2, 2, 1]} and value:
   *  <pre>
   *  x = [[[[1], [2]], [[3], [4]]]]
   *  </pre>
   *  <p>(2) For the following input of shape {@code [4, 1, 1, 3]}, {@code block_shape = [2, 2]}, and
   *  {@code crops = [[0, 0], [0, 0]]}:
   *  <pre>
   *  [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
   *  </pre>
   *  <p>The output tensor has shape {@code [1, 2, 2, 3]} and value:
   *  <pre>
   *  x = [[[[1, 2, 3], [4, 5, 6]],
   *        [[7, 8, 9], [10, 11, 12]]]]
   *  </pre>
   *  <p>(3) For the following input of shape {@code [4, 2, 2, 1]}, {@code block_shape = [2, 2]}, and
   *  {@code crops = [[0, 0], [0, 0]]}:
   *  <pre>
   *  x = [[[[1], [3]], [[9], [11]]],
   *       [[[2], [4]], [[10], [12]]],
   *       [[[5], [7]], [[13], [15]]],
   *       [[[6], [8]], [[14], [16]]]]
   *  </pre>
   *  <p>The output tensor has shape {@code [1, 4, 4, 1]} and value:
   *  <pre>
   *  x = [[[[1],   [2],  [3],  [4]],
   *       [[5],   [6],  [7],  [8]],
   *       [[9],  [10], [11],  [12]],
   *       [[13], [14], [15],  [16]]]]
   *  </pre>
   *  <p>(4) For the following input of shape {@code [8, 1, 3, 1]}, {@code block_shape = [2, 2]}, and
   *  {@code crops = [[0, 0], [2, 0]]}:
   *  <pre>
   *  x = [[[[0], [1], [3]]], [[[0], [9], [11]]],
   *       [[[0], [2], [4]]], [[[0], [10], [12]]],
   *       [[[0], [5], [7]]], [[[0], [13], [15]]],
   *       [[[0], [6], [8]]], [[[0], [14], [16]]]]
   *  </pre>
   *  <p>The output tensor has shape {@code [2, 2, 4, 1]} and value:
   *  <pre>
   *  x = [[[[1],   [2],  [3],  [4]],
   *        [[5],   [6],  [7],  [8]]],
   *       [[[9],  [10], [11],  [12]],
   *        [[13], [14], [15],  [16]]]]
   *  </pre>
   * @param <T> data type for {@code BatchToSpaceND} output and operands
   * @return a new instance of BatchToSpaceNd
   */
  public <T extends TType> BatchToSpaceNd<T> batchToSpaceNd(Operand<T> input,
      Operand<? extends TNumber> blockShape, Operand<? extends TNumber> crops) {
    return BatchToSpaceNd.create(scope, input, blockShape, crops);
  }

  /**
   * Bitcasts a tensor from one type to another without copying data.
   *  Given a tensor {@code input}, this operation returns a tensor that has the same buffer
   *  data as {@code input} with datatype {@code type}.
   *  <p>If the input datatype {@code T} is larger than the output datatype {@code type} then the
   *  shape changes from [...] to [..., sizeof({@code T})/sizeof({@code type})].
   *  <p>If {@code T} is smaller than {@code type}, the operator requires that the rightmost
   *  dimension be equal to sizeof({@code type})/sizeof({@code T}). The shape then goes from
   *  [..., sizeof({@code type})/sizeof({@code T})] to [...].
   *  <p>tf.bitcast() and tf.cast() work differently when real dtype is casted as a complex dtype
   *  (e.g. tf.complex64 or tf.complex128) as tf.cast() make imaginary part 0 while tf.bitcast()
   *  gives module error.
   *  For example,
   *  <p>Example 1:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>a = [1., 2., 3.]
   *  equality_bitcast = tf.bitcast(a, tf.complex128)
   *  Traceback (most recent call last):
   *  ...
   *  InvalidArgumentError: Cannot bitcast from 1 to 18 [Op:Bitcast]
   *  equality_cast = tf.cast(a, tf.complex128)
   *  print(equality_cast)
   *  tf.Tensor([1.+0.j 2.+0.j 3.+0.j], shape=(3,), dtype=complex128)
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *  <p>Example 2:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>tf.bitcast(tf.constant(0xffffffff, dtype=tf.uint32), tf.uint8)
   *  &lt;tf.Tensor: shape=(4,), dtype=uint8, numpy=array([255, 255, 255, 255], dtype=uint8)&gt;
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *  <p>Example 3:
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>x = [1., 2., 3.]
   *  y = [0., 2., 3.]
   *  equality= tf.equal(x,y)
   *  equality_cast = tf.cast(equality,tf.float32)
   *  equality_bitcast = tf.bitcast(equality_cast,tf.uint8)
   *  print(equality)
   *  tf.Tensor([False True True], shape=(3,), dtype=bool)
   *  print(equality_cast)
   *  tf.Tensor([0. 1. 1.], shape=(3,), dtype=float32)
   *  print(equality_bitcast)
   *  tf.Tensor(
   *  [[  0   0   0   0]
   *  [  0   0 128  63]
   *  [  0   0 128  63]], shape=(3, 4), dtype=uint8)
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *  <p><em>NOTE</em>: Bitcast is implemented as a low-level cast, so machines with different
   *  endian orderings will give different results.
   *
   * @param <U> data type for {@code output} output
   * @param input the input value
   * @param type the value of the type property
   * @param <U> data type for {@code Bitcast} output and operands
   * @return a new instance of Bitcast
   */
  public <U extends TType> Bitcast<U> bitcast(Operand<? extends TType> input, Class<U> type) {
    return Bitcast.create(scope, input, type);
  }

  /**
   * Apply boolean mask to tensor.  Returns the flat array of each element corresponding to a {@code true} in the mask.
   *  <p>
   *  Numpy equivalent is {@code tensor[mask]}.
   *  <p>
   *  In general, {@code 0 < dim(mask) = K <= dim(tensor)}, and {@code mask}'s shape must match
   *  the first K dimensions of {@code tensor}'s shape.  We then have:
   *    {@code booleanMask(tensor, mask)[i, j1,...,jd] = tensor[i1,...,iK,j1,...,jd]}
   *  where {@code (i1,...,iK)} is the ith {@code true} entry of {@code mask} (row-major order).
   *  <p>
   *  The {@code axis} could be used with {@code mask} to indicate the axis to mask from (it's 0 by default).
   *  In that case, {@code axis + dim(mask) <= dim(tensor)} and {@code mask}'s shape must match
   *  the first {@code axis + dim(mask)} dimensions of {@code tensor}'s shape.
   *
   * @param tensor The tensor to mask.
   * @param mask The mask to apply.
   * @param options carries optional attributes values
   * @return The masked tensor.
   */
  public <T extends TType> Operand<T> booleanMask(Operand<T> tensor, Operand<TBool> mask,
      BooleanMask.Options... options) {
    return BooleanMask.create(scope, tensor, mask, options);
  }

  /**
   * Updates a tensor at the masked values, and returns the updated tensor.  Does not mutate the input tensors.  {@code
   *  updates} will be broadcasted by default
   *  <p>
   *  Numpy equivalent is `tensor[mask] = updates`.
   *  <p>
   *  In general, {@code 0 < dim(mask) = K <= dim(tensor)}, and {@code mask}'s shape must match the first K dimensions of
   *  {@code tensor}'s shape.  We then have: {@code booleanMask(tensor, mask)[i, j1,...,jd] =
   *  tensor[i1,...,iK,j1,...,jd]} where {@code (i1,...,iK)} is the ith {@code true} entry of {@code mask} (row-major
   *  order).
   *  <p>
   *  The {@code axis} could be used with {@code mask} to indicate the axis to mask from (it's 0 by default). In that
   *  case, {@code axis + dim(mask) <= dim(tensor)} and {@code mask}'s shape must match the first {@code axis +
   *  dim(mask)} dimensions of {@code tensor}'s shape.
   *  <p>
   *  The shape of {@code updates} should be {@code [n, t_1, t_2, ...]} where {@code n} is the number of true values in
   *  {@code mask} and {@code t_i} is the {@code i}th dimension of {@code tensor} after {@code axis} and {@code mask}.
   *  {@code updates} will be broadcasted to this shape by default, which can be disabled using {@code options}.
   *
   * @param tensor The tensor to mask.
   * @param mask The mask to apply.
   * @param updates the new values
   * @param options carries optional attributes values
   * @return The masked tensor.
   */
  public <T extends TType> Operand<T> booleanMaskUpdate(Operand<T> tensor, Operand<TBool> mask,
      Operand<T> updates, BooleanMaskUpdate.Options... options) {
    return BooleanMaskUpdate.create(scope, tensor, mask, updates, options);
  }

  /**
   * Return the shape of s0 op s1 with broadcast.
   *  Given {@code s0} and {@code s1}, tensors that represent shapes, compute {@code r0}, the
   *  broadcasted shape. {@code s0}, {@code s1} and {@code r0} are all integer vectors.
   *
   * @param <T> data type for {@code r0} output
   * @param s0 the s0 value
   * @param s1 the s1 value
   * @param <T> data type for {@code BroadcastArgs} output and operands
   * @return a new instance of BroadcastDynamicShape
   */
  public <T extends TNumber> BroadcastDynamicShape<T> broadcastDynamicShape(Operand<T> s0,
      Operand<T> s1) {
    return BroadcastDynamicShape.create(scope, s0, s1);
  }

  /**
   * Broadcast an array for a compatible shape.
   *  Broadcasting is the process of making arrays to have compatible shapes
   *  for arithmetic operations. Two shapes are compatible if for each
   *  dimension pair they are either equal or one of them is one. When trying
   *  to broadcast a Tensor to a shape, it starts with the trailing dimensions,
   *  and works its way forward.
   *  <p>For example,
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>x = tf.constant([1, 2, 3])
   *  y = tf.broadcast_to(x, [3, 3])
   *  print(y)
   *  tf.Tensor(
   *  [[1 2 3]
   *  [1 2 3]
   *  [1 2 3]], shape=(3, 3), dtype=int32)
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *  <p>In the above example, the input Tensor with the shape of {@code [1, 3]}
   *  is broadcasted to output Tensor with shape of {@code [3, 3]}.
   *  <p>When doing broadcasted operations such as multiplying a tensor
   *  by a scalar, broadcasting (usually) confers some time or space
   *  benefit, as the broadcasted tensor is never materialized.
   *  <p>However, {@code broadcast_to} does not carry with it any such benefits.
   *  The newly-created tensor takes the full memory of the broadcasted
   *  shape. (In a graph context, {@code broadcast_to} might be fused to
   *  subsequent operation and then be optimized away, however.)
   *
   * @param <T> data type for {@code output} output
   * @param input A Tensor to broadcast.
   * @param shape An 1-D {@code int} Tensor. The shape of the desired output.
   * @param <T> data type for {@code BroadcastTo} output and operands
   * @return a new instance of BroadcastTo
   */
  public <T extends TType> BroadcastTo<T> broadcastTo(Operand<T> input,
      Operand<? extends TNumber> shape) {
    return BroadcastTo.create(scope, input, shape);
  }

  /**
   * Bucketizes 'input' based on 'boundaries'.
   *  For example, if the inputs are
   *  boundaries = [0, 10, 100]
   *  input = [[-5, 10000]
   *  [150,   10]
   *  [5,    100]]
   *  <p>then the output will be
   *  output = [[0, 3]
   *  [3, 2]
   *  [1, 3]]
   *
   * @param input Any shape of Tensor contains with int or float type.
   * @param boundaries A sorted list of floats gives the boundary of the buckets.
   * @return a new instance of Bucketize
   */
  public Bucketize bucketize(Operand<? extends TNumber> input, List<Float> boundaries) {
    return Bucketize.create(scope, input, boundaries);
  }

  /**
   * Calls the function in an execution environment, adding its graph as a function if it isn't
   *  already present. Only works for functions with a single input and output.
   *
   * @param argument the argument to the call
   * @return the output of the function
   * @see ConcreteFunction#call(Ops, Operand)
   */
  public Operand<?> call(ConcreteFunction function, Operand<?> argument) {
    return Function.call(scope, function, argument);
  }

  /**
   * Calls the function in an execution environment, adding its graph as a function if it isn't
   *  already present. The inputs and outputs are keyed by the names set in the {@code Signature}.
   *
   * @param arguments the arguments to the call
   * @return the outputs of the function
   * @see ConcreteFunction#call(Ops, Map)
   */
  public Map<String, Operand<?>> call(ConcreteFunction function,
      Map<String, Operand<?>> arguments) {
    return Function.call(scope, function, arguments);
  }

  /**
   * An n-way switch statement which calls a single branch function.
   *  <pre>
   *  An n-way switch statement, implementing the following:
   *  ```
   *  switch (branch_index) {
   *    case 0:
   *      output = branches[0](input);
   *      break;
   *    case 1:
   *      output = branches[1](input);
   *      break;
   *    ...
   *    case [[nbranches-1]]:
   *    default:
   *      output = branches[nbranches-1](input);
   *      break;
   *  }
   *  ```
   *  </pre>
   *
   *  <p>Selects between {@link StatefulCase} and {@link StatelessCase} based on the statefulness of the function arguments.
   *
   * @param branchIndex The branch selector, an int32 Tensor.
   * @param input A list of input tensors passed to the branch function.
   * @param Tout A list of output types.
   * @param branches <pre>
   *    A list of functions each of which takes 'inputs' and returns a list of
   *    tensors, whose types are the same as what every other branch returns.
   *  </pre>
   * @param options carries optional attribute values
   * @return a new instance of Case
   */
  public Case caseOp(Operand<TInt32> branchIndex, Iterable<Operand<?>> input,
      List<Class<? extends TType>> Tout, List<ConcreteFunction> branches, Case.Options... options) {
    return Case.create(scope, branchIndex, input, Tout, branches, options);
  }

  /**
   * Clips tensor values to a specified min and max.
   *  Given a tensor {@code t}, this operation returns a tensor of the same type and
   *  shape as {@code t} with its values clipped to {@code clip_value_min} and {@code clip_value_max}.
   *  Any values less than {@code clip_value_min} are set to {@code clip_value_min}. Any values
   *  greater than {@code clip_value_max} are set to {@code clip_value_max}.
   *
   * @param <T> data type for {@code output} output
   * @param t A {@code Tensor}.
   * @param clipValueMin A 0-D (scalar) {@code Tensor}, or a {@code Tensor} with the same shape
   *  as {@code t}. The minimum value to clip by.
   * @param clipValueMax A 0-D (scalar) {@code Tensor}, or a {@code Tensor} with the same shape
   *  as {@code t}. The maximum value to clip by.
   * @param <T> data type for {@code ClipByValue} output and operands
   * @return a new instance of ClipByValue
   */
  public <T extends TType> ClipByValue<T> clipByValue(Operand<T> t, Operand<T> clipValueMin,
      Operand<T> clipValueMax) {
    return ClipByValue.create(scope, t, clipValueMin, clipValueMax);
  }

  /**
   * Concatenates tensors along one dimension.
   *
   * @param <T> data type for {@code output} output
   * @param values List of {@code N} Tensors to concatenate. Their ranks and types must match,
   *  and their sizes must match in all dimensions except {@code concat_dim}.
   * @param axis 0-D.  The dimension along which to concatenate.  Must be in the
   *  range [-rank(values), rank(values)).
   * @param <T> data type for {@code ConcatV2} output and operands
   * @return a new instance of Concat
   */
  public <T extends TType> Concat<T> concat(Iterable<Operand<T>> values,
      Operand<? extends TNumber> axis) {
    return Concat.create(scope, values, axis);
  }

  /**
   * Creates a constant containing a single {@code int} element.
   *
   * @param data The value to put into the new constant.
   * @return an integer constant
   */
  public Constant<TInt32> constant(int data) {
    return Constant.scalarOf(scope, data);
  }

  /**
   * Creates a rank-3 constant of {@code double} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a double constant
   */
  public Constant<TFloat64> constant(double[][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-5 constant of {@code byte} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a byte constant
   */
  public Constant<TUint8> constant(byte[][][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a constant of {@code String} elements that is a copy of a given n-dimensional array,
   *  using the default UTF-8 encoding.
   *
   * @param data an n-dimensional array of {@code String} elements.
   * @return a string constant
   */
  public Constant<TString> constant(NdArray<String> data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-4 constant of {@code int} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return an integer constant
   */
  public Constant<TInt32> constant(int[][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a constant containing a single {@code byte} element.
   *
   * @param data The value to put into the new constant.
   * @return a byte constant
   */
  public Constant<TUint8> constant(byte data) {
    return Constant.scalarOf(scope, data);
  }

  /**
   * Creates a rank-2 constant of {@code long} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a long constant
   */
  public Constant<TInt64> constant(long[][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-6 constant of {@code float} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a float constant
   */
  public Constant<TFloat32> constant(float[][][][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-6 constant of {@code boolean} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a boolean constant
   */
  public Constant<TBool> constant(boolean[][][][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-4 constant of {@code boolean} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a boolean constant
   */
  public Constant<TBool> constant(boolean[][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-3 constant of {@code float} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a float constant
   */
  public Constant<TFloat32> constant(float[][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-5 constant of {@code float} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a float constant
   */
  public Constant<TFloat32> constant(float[][][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-5 constant of {@code long} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a long constant
   */
  public Constant<TInt64> constant(long[][][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-1 constant of {@code int} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return an integer constant
   */
  public Constant<TInt32> constant(int[] data) {
    return Constant.vectorOf(scope, data);
  }

  /**
   * Creates a rank-2 constant of {@code float} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a float constant
   */
  public Constant<TFloat32> constant(float[][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-2 constant of {@code boolean} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a boolean constant
   */
  public Constant<TBool> constant(boolean[][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a constant containing a single {@code double} element.
   *
   * @param data The value to put into the new constant.
   * @return a double constant
   */
  public Constant<TFloat64> constant(double data) {
    return Constant.scalarOf(scope, data);
  }

  /**
   * Creates a constant containing a single {@code boolean} element.
   *
   * @param data The value to put into the new constant.
   * @return a boolean constant
   */
  public Constant<TBool> constant(boolean data) {
    return Constant.scalarOf(scope, data);
  }

  /**
   * Creates a constant containing a single {@code long} element.
   *
   * @param data The value to put into the new constant.
   * @return a long constant
   */
  public Constant<TInt64> constant(long data) {
    return Constant.scalarOf(scope, data);
  }

  /**
   * Creates a {@code String} constant using the default, UTF-8 encoding.
   *
   * @param data The string to put into the new constant.
   * @return a string constant
   */
  public Constant<TString> constant(String data) {
    return Constant.scalarOf(scope, data);
  }

  /**
   * Creates a constant of {@code boolean} elements that is a copy of a given n-dimensional array.
   *
   * @param data an n-dimensional array of {@code boolean} elements.
   * @return a boolean constant
   */
  public Constant<TBool> constant(BooleanNdArray data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-1 constant of {@code double} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a double constant
   */
  public Constant<TFloat64> constant(double[] data) {
    return Constant.vectorOf(scope, data);
  }

  /**
   * Creates a constant of {@code long} elements that is a copy of a given n-dimensional array.
   *
   * @param data an n-dimensional array of {@code long} elements.
   * @return a long constant
   */
  public Constant<TInt64> constant(LongNdArray data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-1 constant of {@code float} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a float constant
   */
  public Constant<TFloat32> constant(float[] data) {
    return Constant.vectorOf(scope, data);
  }

  /**
   * Creates a rank-3 constant of {@code long} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a long constant
   */
  public Constant<TInt64> constant(long[][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-3 constant of {@code boolean} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a boolean constant
   */
  public Constant<TBool> constant(boolean[][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-1 constant of {@code byte} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a byte constant
   */
  public Constant<TUint8> constant(byte[] data) {
    return Constant.vectorOf(scope, data);
  }

  /**
   * Creates a rank-3 constant of {@code int} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return an integer constant
   */
  public Constant<TInt32> constant(int[][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a constant of {@code int} elements that is a copy of a given n-dimensional array.
   *
   * @param data an n-dimensional array of {@code int} elements.
   * @return an integer constant
   */
  public Constant<TInt32> constant(IntNdArray data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-1 constant of {@code long} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a long constant
   */
  public Constant<TInt64> constant(long[] data) {
    return Constant.vectorOf(scope, data);
  }

  /**
   * Creates a constant of {@code float} elements that is a copy of a given n-dimensional array.
   *
   * @param data an n-dimensional array of {@code float} elements.
   * @return a float constant
   */
  public Constant<TFloat32> constant(FloatNdArray data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-5 constant of {@code int} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return an integer constant
   */
  public Constant<TInt32> constant(int[][][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-5 constant of {@code double} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a double constant
   */
  public Constant<TFloat64> constant(double[][][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-5 constant of {@code boolean} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a boolean constant
   */
  public Constant<TBool> constant(boolean[][][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-6 constant of {@code int} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return an integer constant
   */
  public Constant<TInt32> constant(int[][][][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a constant of {@code double} elements that is a copy of a given n-dimensional array.
   *
   * @param data an n-dimensional array of {@code double} elements.
   * @return a double constant
   */
  public Constant<TFloat64> constant(DoubleNdArray data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-6 constant of {@code double} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a double constant
   */
  public Constant<TFloat64> constant(double[][][][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-6 constant of {@code long} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a long constant
   */
  public Constant<TInt64> constant(long[][][][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-2 constant of {@code int} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return an integer constant
   */
  public Constant<TInt32> constant(int[][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-1 constant of {@code boolean} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a boolean constant
   */
  public Constant<TBool> constant(boolean[] data) {
    return Constant.vectorOf(scope, data);
  }

  /**
   * Creates a constant containing a single {@code float} element.
   *
   * @param data The value to put into the new constant.
   * @return a float constant
   */
  public Constant<TFloat32> constant(float data) {
    return Constant.scalarOf(scope, data);
  }

  /**
   * Creates a rank-4 constant of {@code byte} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a byte constant
   */
  public Constant<TUint8> constant(byte[][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-4 constant of {@code float} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a float constant
   */
  public Constant<TFloat32> constant(float[][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a constant of {@code byte} elements that is a copy of a given n-dimensional array.
   *
   * @param data an n-dimensional array of {@code byte} elements.
   * @return a byte constant
   */
  public Constant<TUint8> constant(ByteNdArray data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-6 constant of {@code byte} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a byte constant
   */
  public Constant<TUint8> constant(byte[][][][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-4 constant of {@code long} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a long constant
   */
  public Constant<TInt64> constant(long[][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-2 constant of {@code byte} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a byte constant
   */
  public Constant<TUint8> constant(byte[][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-2 constant of {@code double} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a double constant
   */
  public Constant<TFloat64> constant(double[][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-3 constant of {@code byte} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a byte constant
   */
  public Constant<TUint8> constant(byte[][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-4 constant of {@code double} elements.
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   *      new constant will match those of the array.
   * @return a double constant
   */
  public Constant<TFloat64> constant(double[][][][] data) {
    return Constant.tensorOf(scope, data);
  }

  /**
   * Creates a rank-1 constant of {@code long} elements representing the size of each dimensions of
   *  the given shape.
   *
   * @param shape a shape
   * @return a long constant
   */
  public Constant<TInt64> constant(Shape shape) {
    return Constant.tensorOf(scope, shape);
  }

  /**
   * Creates a constant of {@code String} elements that is a copy of a given n-dimensional array,
   *  using the given encoding.
   *
   * @param charset charset used to encode/decode string bytes.
   * @param data an n-dimensional array of {@code String} elements.
   * @return a string constant
   */
  public Constant<TString> constant(Charset charset, NdArray<String> data) {
    return Constant.tensorOf(scope, charset, data);
  }

  /**
   * Creates a constant of {@code String} elements, using the given charset.
   *
   * @param charset charset for encoding/decoding strings bytes.
   * @param data An array containing the values to put into the new constant. String elements are
   *      sequences of bytes from the last array dimension.
   * @return the {@code String} constant
   */
  public Constant<TString> constant(Charset charset, String[] data) {
    return Constant.vectorOf(scope, charset, data);
  }

  /**
   * Creates a {@code String} constant using a specified encoding.
   *
   * @param charset The encoding from String to bytes.
   * @param data The string to put into the new constant.
   * @return a string constant
   */
  public Constant<TString> constant(Charset charset, String data) {
    return Constant.scalarOf(scope, charset, data);
  }

  /**
   * Create a {@link TBool} constant with data from the given buffer.
   *
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return an boolean constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  public Constant<TBool> constant(Shape shape, BooleanDataBuffer data) {
    return Constant.tensorOf(scope, shape, data);
  }

  /**
   * Create a {@link TString} constant with data from the given buffer, using the default UTF-8
   *  encoding.
   *
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a string constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  public Constant<TString> constant(Shape shape, DataBuffer<String> data) {
    return Constant.tensorOf(scope, shape, data);
  }

  /**
   * Create a {@link TUint8} constant with data from the given buffer.
   *
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a byte constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  public Constant<TUint8> constant(Shape shape, ByteDataBuffer data) {
    return Constant.tensorOf(scope, shape, data);
  }

  /**
   * Create a {@link TInt32} constant with data from the given buffer.
   *
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return an integer constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  public Constant<TInt32> constant(Shape shape, IntDataBuffer data) {
    return Constant.tensorOf(scope, shape, data);
  }

  /**
   * Create a {@link TInt64} constant with data from the given buffer.
   *
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a long constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  public Constant<TInt64> constant(Shape shape, LongDataBuffer data) {
    return Constant.tensorOf(scope, shape, data);
  }

  /**
   * Create a {@link TFloat64} constant with data from the given buffer.
   *
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a double constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  public Constant<TFloat64> constant(Shape shape, DoubleDataBuffer data) {
    return Constant.tensorOf(scope, shape, data);
  }

  /**
   * Create a {@link TFloat32} constant with data from the given buffer.
   *
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a float constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  public Constant<TFloat32> constant(Shape shape, FloatDataBuffer data) {
    return Constant.tensorOf(scope, shape, data);
  }

  /**
   * Creates a scalar of {@code type}, with the value of {@code number}. {@code number} may be truncated if it does not
   *  fit in the target type.
   *
   * @param type the type of tensor to create.  Must be concrete (i.e. not {@link org.tensorflow.types.family.TFloating})
   * @param number the value of the tensor
   * @return a constant of the passed type
   * @throws IllegalArgumentException if the type is abstract (i.e. {@link org.tensorflow.types.family.TFloating}) or
   *  unknown.
   */
  public <T extends TNumber> Constant<T> constant(Class<T> type, Number number) {
    return Constant.tensorOf(scope, type, number);
  }

  /**
   * Create a {@link TString} constant with data from the given buffer, using the given encoding.
   *
   * @param charset charset used to encode/decode string bytes.
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a string constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   */
  public Constant<TString> constant(Charset charset, Shape shape, DataBuffer<String> data) {
    return Constant.tensorOf(scope, charset, shape, data);
  }

  /**
   * Create a constant with data from the given buffer.
   *
   * @param <T> the tensor type
   * @param type the tensor type class
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a constant of type `type`
   * @throws IllegalArgumentException If the tensor datatype or shape is not compatible with the
   *      buffer
   */
  public <T extends TType> Constant<T> constant(Class<T> type, Shape shape, ByteDataBuffer data) {
    return Constant.tensorOf(scope, type, shape, data);
  }

  /**
   * Create a constant by making an immutable copy of {@code tensor}. {@code tensor} may be closed afterwards without
   *  issue.
   *
   *  <p>Note: this endpoint cannot be simply called {@code constant} since it will conflict with
   *  other endpoints accepting an NdArray in parameter {e.g. {@link #tensorOf(Scope, FloatNdArray)}}.
   *
   * @param tensor a Tensor holding the constant value
   * @return a constant of the same data type as `tensor`
   */
  public <T extends TType> Constant<T> constantOf(T tensor) {
    return Constant.create(scope, tensor);
  }

  /**
   * Creates a scalar of the same type as {@code toMatch}, with the value of {@code number}. {@code number} may be
   *  truncated if it does not fit in the target type.
   *
   * @param toMatch the operand providing the target type
   * @param number the value of the tensor
   * @return a constant with the same type as {@code toMatch}
   * @throws IllegalArgumentException if the type is unknown (which should be impossible).
   * @see Ops#constant(Class, Number)
   */
  public <T extends TNumber> Constant<T> constantOfSameType(Operand<T> toMatch, Number number) {
    return Constant.tensorOfSameType(scope, toMatch, number);
  }

  /**
   * This op consumes a lock created by {@code MutexLock}.
   *  This op exists to consume a tensor created by {@code MutexLock} (other than
   *  direct control dependencies).  It should be the only that consumes the tensor,
   *  and will raise an error if it is not.  Its only purpose is to keep the
   *  mutex lock tensor alive until it is consumed by this op.
   *  <p><strong>NOTE</strong>: This operation must run on the same device as its input.  This may
   *  be enforced via the {@code colocate_with} mechanism.
   *
   * @param mutexLock A tensor returned by {@code MutexLock}.
   * @return a new instance of ConsumeMutexLock
   */
  public ConsumeMutexLock consumeMutexLock(Operand<? extends TType> mutexLock) {
    return ConsumeMutexLock.create(scope, mutexLock);
  }

  /**
   * Does nothing. Serves as a control trigger for scheduling.
   *  Only useful as a placeholder for control edges.
   *
   * @return a new instance of ControlTrigger
   */
  public ControlTrigger controlTrigger() {
    return ControlTrigger.create(scope);
  }

  /**
   * Increments 'ref' until it reaches 'limit'.
   *
   * @param <T> data type for {@code output} output
   * @param ref Should be from a scalar {@code Variable} node.
   * @param limit If incrementing ref would bring it above limit, instead generates an
   *  'OutOfRange' error.
   * @param <T> data type for {@code CountUpTo} output and operands
   * @return a new instance of CountUpTo
   */
  public <T extends TNumber> CountUpTo<T> countUpTo(Operand<T> ref, Long limit) {
    return CountUpTo.create(scope, ref, limit);
  }

  /**
   * The op extracts fields from a serialized protocol buffers message into tensors.
   *  The {@code decode_proto} op extracts fields from a serialized protocol buffers
   *  message into tensors.  The fields in {@code field_names} are decoded and converted
   *  to the corresponding {@code output_types} if possible.
   *  <p>A {@code message_type} name must be provided to give context for the field names.
   *  The actual message descriptor can be looked up either in the linked-in
   *  descriptor pool or a filename provided by the caller using the
   *  {@code descriptor_source} attribute.
   *  <p>Each output tensor is a dense tensor. This means that it is padded to hold
   *  the largest number of repeated elements seen in the input minibatch. (The
   *  shape is also padded by one to prevent zero-sized dimensions). The actual
   *  repeat counts for each example in the minibatch can be found in the {@code sizes}
   *  output. In many cases the output of {@code decode_proto} is fed immediately into
   *  tf.squeeze if missing values are not a concern. When using tf.squeeze, always
   *  pass the squeeze dimension explicitly to avoid surprises.
   *  <p>For the most part, the mapping between Proto field types and TensorFlow dtypes
   *  is straightforward. However, there are a few special cases:
   *  <ul>
   *  <li>
   *  <p>A proto field that contains a submessage or group can only be converted
   *  to {@code DT_STRING} (the serialized submessage). This is to reduce the complexity
   *  of the API. The resulting string can be used as input to another instance of
   *  the decode_proto op.
   *  </li>
   *  <li>
   *  <p>TensorFlow lacks support for unsigned integers. The ops represent uint64
   *  types as a {@code DT_INT64} with the same twos-complement bit pattern (the obvious
   *  way). Unsigned int32 values can be represented exactly by specifying type
   *  {@code DT_INT64}, or using twos-complement if the caller specifies {@code DT_INT32} in
   *  the {@code output_types} attribute.
   *  </li>
   *  </ul>
   *  <p>Both binary and text proto serializations are supported, and can be
   *  chosen using the {@code format} attribute.
   *  <p>The {@code descriptor_source} attribute selects the source of protocol
   *  descriptors to consult when looking up {@code message_type}. This may be:
   *  <ul>
   *  <li>
   *  <p>An empty string  or &quot;local://&quot;, in which case protocol descriptors are
   *  created for C++ (not Python) proto definitions linked to the binary.
   *  </li>
   *  <li>
   *  <p>A file, in which case protocol descriptors are created from the file,
   *  which is expected to contain a {@code FileDescriptorSet} serialized as a string.
   *  NOTE: You can build a {@code descriptor_source} file using the {@code --descriptor_set_out}
   *  and {@code --include_imports} options to the protocol compiler {@code protoc}.
   *  </li>
   *  <li>
   *  <p>A &quot;bytes://&lt;bytes&gt;&quot;, in which protocol descriptors are created from {@code <bytes>},
   *  which is expected to be a {@code FileDescriptorSet} serialized as a string.
   *  </li>
   *  </ul>
   *
   * @param bytes Tensor of serialized protos with shape {@code batch_shape}.
   * @param messageType Name of the proto message type to decode.
   * @param fieldNames List of strings containing proto field names. An extension field can be decoded
   *  by using its full name, e.g. EXT_PACKAGE.EXT_FIELD_NAME.
   * @param outputTypes List of TF types to use for the respective field in field_names.
   * @param options carries optional attribute values
   * @return a new instance of DecodeProto
   */
  public DecodeProto decodeProto(Operand<TString> bytes, String messageType,
      List<String> fieldNames, List<Class<? extends TType>> outputTypes,
      DecodeProto.Options... options) {
    return DecodeProto.create(scope, bytes, messageType, fieldNames, outputTypes, options);
  }

  /**
   * Makes a copy of {@code x}.
   *
   * @param <T> data type for {@code y} output
   * @param x The source tensor of type {@code T}.
   * @param <T> data type for {@code DeepCopy} output and operands
   * @return a new instance of DeepCopy
   */
  public <T extends TType> DeepCopy<T> deepCopy(Operand<T> x) {
    return DeepCopy.create(scope, x);
  }

  /**
   * Delete the tensor specified by its handle in the session.
   *
   * @param handle The handle for a tensor stored in the session state.
   * @return a new instance of DeleteSessionTensor
   */
  public DeleteSessionTensor deleteSessionTensor(Operand<TString> handle) {
    return DeleteSessionTensor.create(scope, handle);
  }

  /**
   * Deletes the resource specified by the handle.
   *  All subsequent operations using the resource will result in a NotFound
   *  error status.
   *
   * @param resource handle to the resource to delete.
   * @param options carries optional attribute values
   * @return a new instance of DestroyResourceOp
   */
  public DestroyResourceOp destroyResourceOp(Operand<? extends TType> resource,
      DestroyResourceOp.Options... options) {
    return DestroyResourceOp.create(scope, resource, options);
  }

  /**
   * Destroys the temporary variable and returns its final value.
   *  Sets output to the value of the Tensor pointed to by 'ref', then destroys
   *  the temporary variable called 'var_name'.
   *  All other uses of 'ref' <em>must</em> have executed before this op.
   *  This is typically achieved by chaining the ref through each assign op, or by
   *  using control dependencies.
   *  <p>Outputs the final value of the tensor pointed to by 'ref'.
   *
   * @param <T> data type for {@code value} output
   * @param ref A reference to the temporary variable tensor.
   * @param varName Name of the temporary variable, usually the name of the matching
   *  'TemporaryVariable' op.
   * @param <T> data type for {@code DestroyTemporaryVariable} output and operands
   * @return a new instance of DestroyTemporaryVariable
   */
  public <T extends TType> DestroyTemporaryVariable<T> destroyTemporaryVariable(Operand<T> ref,
      String varName) {
    return DestroyTemporaryVariable.create(scope, ref, varName);
  }

  /**
   * Partitions {@code data} into {@code num_partitions} tensors using indices from {@code partitions}.
   *  For each index tuple {@code js} of size {@code partitions.ndim}, the slice {@code data[js, ...]}
   *  becomes part of {@code outputs[partitions[js]]}.  The slices with {@code partitions[js] = i}
   *  are placed in {@code outputs[i]} in lexicographic order of {@code js}, and the first
   *  dimension of {@code outputs[i]} is the number of entries in {@code partitions} equal to {@code i}.
   *  In detail,
   *  <pre>
   *      outputs[i].shape = [sum(partitions == i)] + data.shape[partitions.ndim:]
   *
   *      outputs[i] = pack([data[js, ...] for js if partitions[js] == i])
   *  </pre>
   *  <p>{@code data.shape} must start with {@code partitions.shape}.
   *  <p>For example:
   *  <pre>
   *      # Scalar partitions.
   *      partitions = 1
   *      num_partitions = 2
   *      data = [10, 20]
   *      outputs[0] = []  # Empty with shape [0, 2]
   *      outputs[1] = [[10, 20]]
   *
   *      # Vector partitions.
   *      partitions = [0, 0, 1, 1, 0]
   *      num_partitions = 2
   *      data = [10, 20, 30, 40, 50]
   *      outputs[0] = [10, 20, 50]
   *      outputs[1] = [30, 40]
   *  </pre>
   *  <p>See {@code dynamic_stitch} for an example on how to merge partitions back.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/DynamicPartition.png" alt>
   *  </div>
   *
   * @param <T> data type for {@code outputs} output
   * @param data the data value
   * @param partitions Any shape.  Indices in the range {@code [0, num_partitions)}.
   * @param numPartitions The number of partitions to output.
   * @param <T> data type for {@code DynamicPartition} output and operands
   * @return a new instance of DynamicPartition
   */
  public <T extends TType> DynamicPartition<T> dynamicPartition(Operand<T> data,
      Operand<TInt32> partitions, Long numPartitions) {
    return DynamicPartition.create(scope, data, partitions, numPartitions);
  }

  /**
   * Interleave the values from the {@code data} tensors into a single tensor.
   *  Builds a merged tensor such that
   *  <pre>
   *      merged[indices[m][i, ..., j], ...] = data[m][i, ..., j, ...]
   *  </pre>
   *  <p>For example, if each {@code indices[m]} is scalar or vector, we have
   *  <pre>
   *      # Scalar indices:
   *      merged[indices[m], ...] = data[m][...]
   *
   *      # Vector indices:
   *      merged[indices[m][i], ...] = data[m][i, ...]
   *  </pre>
   *  <p>Each {@code data[i].shape} must start with the corresponding {@code indices[i].shape},
   *  and the rest of {@code data[i].shape} must be constant w.r.t. {@code i}.  That is, we
   *  must have {@code data[i].shape = indices[i].shape + constant}.  In terms of this
   *  {@code constant}, the output shape is
   *  <pre>
   *  merged.shape = [max(indices)] + constant
   *  </pre>
   *  <p>Values are merged in order, so if an index appears in both {@code indices[m][i]} and
   *  {@code indices[n][j]} for {@code (m,i) < (n,j)} the slice {@code data[n][j]} will appear in the
   *  merged result. If you do not need this guarantee, ParallelDynamicStitch might
   *  perform better on some devices.
   *  <p>For example:
   *  <pre>
   *      indices[0] = 6
   *      indices[1] = [4, 1]
   *      indices[2] = [[5, 2], [0, 3]]
   *      data[0] = [61, 62]
   *      data[1] = [[41, 42], [11, 12]]
   *      data[2] = [[[51, 52], [21, 22]], [[1, 2], [31, 32]]]
   *      merged = [[1, 2], [11, 12], [21, 22], [31, 32], [41, 42],
   *                [51, 52], [61, 62]]
   *  </pre>
   *  <p>This method can be used to merge partitions created by {@code dynamic_partition}
   *  as illustrated on the following example:
   *  <pre>
   *      # Apply function (increments x_i) on elements for which a certain condition
   *      # apply (x_i != -1 in this example).
   *      x=tf.constant([0.1, -1., 5.2, 4.3, -1., 7.4])
   *      condition_mask=tf.not_equal(x,tf.constant(-1.))
   *      partitioned_data = tf.dynamic_partition(
   *          x, tf.cast(condition_mask, tf.int32) , 2)
   *      partitioned_data[1] = partitioned_data[1] + 1.0
   *      condition_indices = tf.dynamic_partition(
   *          tf.range(tf.shape(x)[0]), tf.cast(condition_mask, tf.int32) , 2)
   *      x = tf.dynamic_stitch(condition_indices, partitioned_data)
   *      # Here x=[1.1, -1., 6.2, 5.3, -1, 8.4], the -1. values remain
   *      # unchanged.
   *  </pre>
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/DynamicStitch.png" alt>
   *  </div>
   *
   * @param <T> data type for {@code merged} output
   * @param indices the indices value
   * @param data the data value
   * @param <T> data type for {@code DynamicStitch} output and operands
   * @return a new instance of DynamicStitch
   */
  public <T extends TType> DynamicStitch<T> dynamicStitch(Iterable<Operand<TInt32>> indices,
      Iterable<Operand<T>> data) {
    return DynamicStitch.create(scope, indices, data);
  }

  /**
   * Computes the (possibly normalized) Levenshtein Edit Distance.
   *  The inputs are variable-length sequences provided by SparseTensors
   *  (hypothesis_indices, hypothesis_values, hypothesis_shape)
   *  and
   *  (truth_indices, truth_values, truth_shape).
   *  <p>The inputs are:
   *
   * @param hypothesisIndices The indices of the hypothesis list SparseTensor.
   *  This is an N x R int64 matrix.
   * @param hypothesisValues The values of the hypothesis list SparseTensor.
   *  This is an N-length vector.
   * @param hypothesisShape The shape of the hypothesis list SparseTensor.
   *  This is an R-length vector.
   * @param truthIndices The indices of the truth list SparseTensor.
   *  This is an M x R int64 matrix.
   * @param truthValues The values of the truth list SparseTensor.
   *  This is an M-length vector.
   * @param truthShape truth indices, vector.
   * @param options carries optional attribute values
   * @param <T> data type for {@code EditDistance} output and operands
   * @return a new instance of EditDistance
   */
  public <T extends TType> EditDistance editDistance(Operand<TInt64> hypothesisIndices,
      Operand<T> hypothesisValues, Operand<TInt64> hypothesisShape, Operand<TInt64> truthIndices,
      Operand<T> truthValues, Operand<TInt64> truthShape, EditDistance.Options... options) {
    return EditDistance.create(scope, hypothesisIndices, hypothesisValues, hypothesisShape, truthIndices, truthValues, truthShape, options);
  }

  /**
   * Creates a tensor with the given shape.
   *  <p>This operation creates a tensor of {@code shape} and {@code dtype}.
   *
   * @param <T> data type for {@code output} output
   * @param shape 1-D. Represents the shape of the output tensor.
   * @param dtype the value of the dtype property
   * @param options carries optional attribute values
   * @param <T> data type for {@code Empty} output and operands
   * @return a new instance of Empty
   */
  public <T extends TType> Empty<T> empty(Operand<TInt32> shape, Class<T> dtype,
      Empty.Options... options) {
    return Empty.create(scope, shape, dtype, options);
  }

  /**
   * Creates and returns an empty tensor list.
   *  All list elements must be tensors of dtype element_dtype and shape compatible
   *  with element_shape.
   *  <p>handle: an empty tensor list.
   *  element_dtype: the type of elements in the list.
   *  element_shape: a shape compatible with that of elements in the list.
   *
   * @param elementShape the elementShape value
   * @param maxNumElements the maxNumElements value
   * @param elementDtype the value of the elementDtype property
   * @param <U> data type for {@code EmptyTensorList} output and operands
   * @return a new instance of EmptyTensorList
   */
  public <U extends TType> EmptyTensorList emptyTensorList(Operand<? extends TNumber> elementShape,
      Operand<TInt32> maxNumElements, Class<U> elementDtype) {
    return EmptyTensorList.create(scope, elementShape, maxNumElements, elementDtype);
  }

  /**
   * Creates and returns an empty tensor map.
   *  handle: an empty tensor map
   *
   * @return a new instance of EmptyTensorMap
   */
  public EmptyTensorMap emptyTensorMap() {
    return EmptyTensorMap.create(scope);
  }

  /**
   * The op serializes protobuf messages provided in the input tensors.
   *  The types of the tensors in {@code values} must match the schema for the fields
   *  specified in {@code field_names}. All the tensors in {@code values} must have a common
   *  shape prefix, <em>batch_shape</em>.
   *  <p>The {@code sizes} tensor specifies repeat counts for each field.  The repeat count
   *  (last dimension) of a each tensor in {@code values} must be greater than or equal
   *  to corresponding repeat count in {@code sizes}.
   *  <p>A {@code message_type} name must be provided to give context for the field names.
   *  The actual message descriptor can be looked up either in the linked-in
   *  descriptor pool or a filename provided by the caller using the
   *  {@code descriptor_source} attribute.
   *  <p>For the most part, the mapping between Proto field types and TensorFlow dtypes
   *  is straightforward. However, there are a few special cases:
   *  <ul>
   *  <li>
   *  <p>A proto field that contains a submessage or group can only be converted
   *  to {@code DT_STRING} (the serialized submessage). This is to reduce the complexity
   *  of the API. The resulting string can be used as input to another instance of
   *  the decode_proto op.
   *  </li>
   *  <li>
   *  <p>TensorFlow lacks support for unsigned integers. The ops represent uint64
   *  types as a {@code DT_INT64} with the same twos-complement bit pattern (the obvious
   *  way). Unsigned int32 values can be represented exactly by specifying type
   *  {@code DT_INT64}, or using twos-complement if the caller specifies {@code DT_INT32} in
   *  the {@code output_types} attribute.
   *  </li>
   *  </ul>
   *  <p>The {@code descriptor_source} attribute selects the source of protocol
   *  descriptors to consult when looking up {@code message_type}. This may be:
   *  <ul>
   *  <li>
   *  <p>An empty string  or &quot;local://&quot;, in which case protocol descriptors are
   *  created for C++ (not Python) proto definitions linked to the binary.
   *  </li>
   *  <li>
   *  <p>A file, in which case protocol descriptors are created from the file,
   *  which is expected to contain a {@code FileDescriptorSet} serialized as a string.
   *  NOTE: You can build a {@code descriptor_source} file using the {@code --descriptor_set_out}
   *  and {@code --include_imports} options to the protocol compiler {@code protoc}.
   *  </li>
   *  <li>
   *  <p>A &quot;bytes://&lt;bytes&gt;&quot;, in which protocol descriptors are created from {@code <bytes>},
   *  which is expected to be a {@code FileDescriptorSet} serialized as a string.
   *  </li>
   *  </ul>
   *
   * @param sizes Tensor of int32 with shape {@code [batch_shape, len(field_names)]}.
   * @param values List of tensors containing values for the corresponding field.
   * @param fieldNames List of strings containing proto field names.
   * @param messageType Name of the proto message type to decode.
   * @param options carries optional attribute values
   * @return a new instance of EncodeProto
   */
  public EncodeProto encodeProto(Operand<TInt32> sizes, Iterable<Operand<?>> values,
      List<String> fieldNames, String messageType, EncodeProto.Options... options) {
    return EncodeProto.create(scope, sizes, values, fieldNames, messageType, options);
  }

  /**
   * Ensures that the tensor's shape matches the expected shape.
   *  Raises an error if the input tensor's shape does not match the specified shape.
   *  Returns the input tensor otherwise.
   *
   * @param <T> data type for {@code output} output
   * @param input A tensor, whose shape is to be validated.
   * @param shape The expected (possibly partially specified) shape of the input tensor.
   * @param <T> data type for {@code EnsureShape} output and operands
   * @return a new instance of EnsureShape
   */
  public <T extends TType> EnsureShape<T> ensureShape(Operand<T> input, Shape shape) {
    return EnsureShape.create(scope, input, shape);
  }

  /**
   * Inserts a dimension of 1 into a tensor's shape.
   *  Given a tensor {@code input}, this operation inserts a dimension of 1 at the
   *  dimension index {@code axis} of {@code input}'s shape. The dimension index {@code axis} starts at
   *  zero; if you specify a negative number for {@code axis} it is counted backward from
   *  the end.
   *  <p>This operation is useful if you want to add a batch dimension to a single
   *  element. For example, if you have a single image of shape {@code [height, width, channels]}, you can make it a batch of 1 image with {@code expand_dims(image, 0)},
   *  which will make the shape {@code [1, height, width, channels]}.
   *  <p>Other examples:
   *  <pre>
   *  # 't' is a tensor of shape [2]
   *  shape(expand_dims(t, 0)) ==&gt; [1, 2]
   *  shape(expand_dims(t, 1)) ==&gt; [2, 1]
   *  shape(expand_dims(t, -1)) ==&gt; [2, 1]
   *
   *  # 't2' is a tensor of shape [2, 3, 5]
   *  shape(expand_dims(t2, 0)) ==&gt; [1, 2, 3, 5]
   *  shape(expand_dims(t2, 2)) ==&gt; [2, 3, 1, 5]
   *  shape(expand_dims(t2, 3)) ==&gt; [2, 3, 5, 1]
   *  </pre>
   *  <p>This operation requires that:
   *  <p>{@code -1-input.dims() <= dim <= input.dims()}
   *  <p>This operation is related to {@code squeeze()}, which removes dimensions of
   *  size 1.
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param axis 0-D (scalar). Specifies the dimension index at which to
   *  expand the shape of {@code input}. Must be in the range
   *  {@code [-rank(input) - 1, rank(input)]}.
   * @param <T> data type for {@code ExpandDims} output and operands
   * @return a new instance of ExpandDims
   */
  public <T extends TType> ExpandDims<T> expandDims(Operand<T> input,
      Operand<? extends TNumber> axis) {
    return ExpandDims.create(scope, input, axis);
  }

  /**
   * Extract {@code patches} from {@code input} and put them in the {@code "depth"} output dimension. 3D extension of {@code extract_image_patches}.
   *
   * @param <T> data type for {@code patches} output
   * @param input 5-D Tensor with shape {@code [batch, in_planes, in_rows, in_cols, depth]}.
   * @param ksizes The size of the sliding window for each dimension of {@code input}.
   * @param strides 1-D of length 5. How far the centers of two consecutive patches are in
   *  {@code input}. Must be: {@code [1, stride_planes, stride_rows, stride_cols, 1]}.
   * @param padding The type of padding algorithm to use.
   *  <p>The size-related attributes are specified as follows:
   *  <pre>
   *  ksizes = [1, ksize_planes, ksize_rows, ksize_cols, 1]
   *  strides = [1, stride_planes, strides_rows, strides_cols, 1]
   *  </pre>
   * @param <T> data type for {@code ExtractVolumePatches} output and operands
   * @return a new instance of ExtractVolumePatches
   */
  public <T extends TNumber> ExtractVolumePatches<T> extractVolumePatches(Operand<T> input,
      List<Long> ksizes, List<Long> strides, String padding) {
    return ExtractVolumePatches.create(scope, input, ksizes, strides, padding);
  }

  /**
   * Creates a tensor filled with a scalar value.
   *  This operation creates a tensor of shape {@code dims} and fills it with {@code value}.
   *  <p>For example:
   *  <pre>
   *  # Output tensor has shape [2, 3].
   *  fill([2, 3], 9) ==&gt; [[9, 9, 9]
   *                       [9, 9, 9]]
   *  </pre>
   *  <p>{@code tf.fill} differs from {@code tf.constant} in a few ways:
   *  <ul>
   *  <li>{@code tf.fill} only supports scalar contents, whereas {@code tf.constant} supports
   *  Tensor values.</li>
   *  <li>{@code tf.fill} creates an Op in the computation graph that constructs the actual
   *  Tensor value at runtime. This is in contrast to {@code tf.constant} which embeds
   *  the entire Tensor into the graph with a {@code Const} node.</li>
   *  <li>Because {@code tf.fill} evaluates at graph runtime, it supports dynamic shapes
   *  based on other runtime Tensors, unlike {@code tf.constant}.</li>
   *  </ul>
   *
   * @param <U> data type for {@code output} output
   * @param dims 1-D. Represents the shape of the output tensor.
   * @param value 0-D (scalar). Value to fill the returned tensor.
   *  <p>{@literal @}compatibility(numpy)<br>
   *  Equivalent to np.full
   *  <br>{@literal @}end_compatibility
   * @param <U> data type for {@code Fill} output and operands
   * @return a new instance of Fill
   */
  public <U extends TType> Fill<U> fill(Operand<? extends TNumber> dims, Operand<U> value) {
    return Fill.create(scope, dims, value);
  }

  /**
   * Generates fingerprint values.
   *  Generates fingerprint values of {@code data}.
   *  <p>Fingerprint op considers the first dimension of {@code data} as the batch dimension,
   *  and {@code output[i]} contains the fingerprint value generated from contents in
   *  {@code data[i, ...]} for all {@code i}.
   *  <p>Fingerprint op writes fingerprint values as byte arrays. For example, the
   *  default method {@code farmhash64} generates a 64-bit fingerprint value at a time.
   *  This 8-byte value is written out as an {@code uint8} array of size 8, in little-endian
   *  order.
   *  <p>For example, suppose that {@code data} has data type {@code DT_INT32} and shape (2, 3, 4),
   *  and that the fingerprint method is {@code farmhash64}. In this case, the output shape
   *  is (2, 8), where 2 is the batch dimension size of {@code data}, and 8 is the size of
   *  each fingerprint value in bytes. {@code output[0, :]} is generated from 12 integers in
   *  {@code data[0, :, :]} and similarly {@code output[1, :]} is generated from other 12 integers
   *  in {@code data[1, :, :]}.
   *  <p>Note that this op fingerprints the raw underlying buffer, and it does not
   *  fingerprint Tensor's metadata such as data type and/or shape. For example, the
   *  fingerprint values are invariant under reshapes and bitcasts as long as the
   *  batch dimension remain the same:
   *  <pre>
   *  Fingerprint(data) == Fingerprint(Reshape(data, ...))
   *  Fingerprint(data) == Fingerprint(Bitcast(data, ...))
   *  </pre>
   *  <p>For string data, one should expect {@code Fingerprint(data) != Fingerprint(ReduceJoin(data))} in general.
   *
   * @param data Must have rank 1 or higher.
   * @param method Fingerprint method used by this op. Currently available method is
   *  {@code farmhash::fingerprint64}.
   * @return a new instance of Fingerprint
   */
  public Fingerprint fingerprint(Operand<? extends TType> data, Operand<TString> method) {
    return Fingerprint.create(scope, data, method);
  }

  /**
   * <pre>
   *   output = input;
   *   for i in range(start, limit, delta)
   *     output = body(i, output);
   *  </pre>
   *
   * @param start The lower bound. An int32
   * @param limit The upper bound. An int32
   * @param delta The increment. An int32
   * @param input A list of input tensors whose types are T.
   * @param body <pre>
   *  A function that takes a list of tensors (int32, T) and returns another
   *  list of tensors (T).
   *  </pre>
   * @return a new instance of For
   */
  public For forOp(Operand<TInt32> start, Operand<TInt32> limit, Operand<TInt32> delta,
      Iterable<Operand<?>> input, ConcreteFunction body) {
    return For.create(scope, start, limit, delta, input, body);
  }

  /**
   * Gather slices from {@code params} axis {@code axis} according to {@code indices}.
   *  {@code indices} must be an integer tensor of any dimension (usually 0-D or 1-D).
   *  Produces an output tensor with shape {@code params.shape[:axis] + indices.shape[batch_dims:] + params.shape[axis + 1:]} where:
   *  <pre>
   *      # Scalar indices (output is rank(params) - 1).
   *      output[a_0, ..., a_n, b_0, ..., b_n] =
   *        params[a_0, ..., a_n, indices, b_0, ..., b_n]
   *
   *      # Vector indices (output is rank(params)).
   *      output[a_0, ..., a_n, i, b_0, ..., b_n] =
   *        params[a_0, ..., a_n, indices[i], b_0, ..., b_n]
   *
   *      # Higher rank indices (output is rank(params) + rank(indices) - 1).
   *      output[a_0, ..., a_n, i, ..., j, b_0, ... b_n] =
   *        params[a_0, ..., a_n, indices[i, ..., j], b_0, ..., b_n]
   *  </pre>
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/Gather.png" alt>
   *  </div>
   *  <p>Note that on CPU, if an out of bound index is found, an error is returned.
   *  On GPU, if an out of bound index is found, a 0 is stored in the
   *  corresponding output value.
   *  <p>See also {@code tf.batch_gather} and {@code tf.gather_nd}.
   *
   * @param <T> data type for {@code output} output
   * @param params The tensor from which to gather values. Must be at least rank
   *  {@code axis + 1}.
   * @param indices Index tensor. Must be in range {@code [0, params.shape[axis])}.
   * @param axis The axis in {@code params} to gather {@code indices} from. Defaults to the first
   *  dimension. Supports negative indexes.
   * @param options carries optional attribute values
   * @param <T> data type for {@code GatherV2} output and operands
   * @return a new instance of Gather
   */
  public <T extends TType> Gather<T> gather(Operand<T> params, Operand<? extends TNumber> indices,
      Operand<? extends TNumber> axis, Gather.Options... options) {
    return Gather.create(scope, params, indices, axis, options);
  }

  /**
   * Gather slices from {@code params} into a Tensor with shape specified by {@code indices}.
   *  {@code indices} is a K-dimensional integer tensor, best thought of as a
   *  (K-1)-dimensional tensor of indices into {@code params}, where each element defines a
   *  slice of {@code params}:
   *  <pre>
   *  output[\\(i_0, ..., i_{K-2}\\)] = params[indices[\\(i_0, ..., i_{K-2}\\)]]
   *  </pre>
   *  <p>Whereas in {@code tf.gather} {@code indices} defines slices into the {@code axis}
   *  dimension of {@code params}, in {@code tf.gather_nd}, {@code indices} defines slices into the
   *  first {@code N} dimensions of {@code params}, where {@code N = indices.shape[-1]}.
   *  <p>The last dimension of {@code indices} can be at most the rank of
   *  {@code params}:
   *  <pre>
   *  indices.shape[-1] &lt;= params.rank
   *  </pre>
   *  <p>The last dimension of {@code indices} corresponds to elements
   *  (if {@code indices.shape[-1] == params.rank}) or slices
   *  (if {@code indices.shape[-1] < params.rank}) along dimension {@code indices.shape[-1]}
   *  of {@code params}.  The output tensor has shape
   *  <pre>
   *  indices.shape[:-1] + params.shape[indices.shape[-1]:]
   *  </pre>
   *  <p>Note that on CPU, if an out of bound index is found, an error is returned.
   *  On GPU, if an out of bound index is found, a 0 is stored in the
   *  corresponding output value.
   *  <p>Some examples below.
   *  <p>Simple indexing into a matrix:
   *  <pre>
   *      indices = [[0, 0], [1, 1]]
   *      params = [['a', 'b'], ['c', 'd']]
   *      output = ['a', 'd']
   *  </pre>
   *  <p>Slice indexing into a matrix:
   *  <pre>
   *      indices = [[1], [0]]
   *      params = [['a', 'b'], ['c', 'd']]
   *      output = [['c', 'd'], ['a', 'b']]
   *  </pre>
   *  <p>Indexing into a 3-tensor:
   *  <pre>
   *      indices = [[1]]
   *      params = [[['a0', 'b0'], ['c0', 'd0']],
   *                [['a1', 'b1'], ['c1', 'd1']]]
   *      output = [[['a1', 'b1'], ['c1', 'd1']]]
   *
   *
   *      indices = [[0, 1], [1, 0]]
   *      params = [[['a0', 'b0'], ['c0', 'd0']],
   *                [['a1', 'b1'], ['c1', 'd1']]]
   *      output = [['c0', 'd0'], ['a1', 'b1']]
   *
   *
   *      indices = [[0, 0, 1], [1, 0, 1]]
   *      params = [[['a0', 'b0'], ['c0', 'd0']],
   *                [['a1', 'b1'], ['c1', 'd1']]]
   *      output = ['b0', 'b1']
   *  </pre>
   *  <p>Batched indexing into a matrix:
   *  <pre>
   *      indices = [[[0, 0]], [[0, 1]]]
   *      params = [['a', 'b'], ['c', 'd']]
   *      output = [['a'], ['b']]
   *  </pre>
   *  <p>Batched slice indexing into a matrix:
   *  <pre>
   *      indices = [[[1]], [[0]]]
   *      params = [['a', 'b'], ['c', 'd']]
   *      output = [[['c', 'd']], [['a', 'b']]]
   *  </pre>
   *  <p>Batched indexing into a 3-tensor:
   *  <pre>
   *      indices = [[[1]], [[0]]]
   *      params = [[['a0', 'b0'], ['c0', 'd0']],
   *                [['a1', 'b1'], ['c1', 'd1']]]
   *      output = [[[['a1', 'b1'], ['c1', 'd1']]],
   *                [[['a0', 'b0'], ['c0', 'd0']]]]
   *
   *      indices = [[[0, 1], [1, 0]], [[0, 0], [1, 1]]]
   *      params = [[['a0', 'b0'], ['c0', 'd0']],
   *                [['a1', 'b1'], ['c1', 'd1']]]
   *      output = [[['c0', 'd0'], ['a1', 'b1']],
   *                [['a0', 'b0'], ['c1', 'd1']]]
   *
   *
   *      indices = [[[0, 0, 1], [1, 0, 1]], [[0, 1, 1], [1, 1, 0]]]
   *      params = [[['a0', 'b0'], ['c0', 'd0']],
   *                [['a1', 'b1'], ['c1', 'd1']]]
   *      output = [['b0', 'b1'], ['d0', 'c1']]
   *  </pre>
   *  <p>See also {@code tf.gather} and {@code tf.batch_gather}.
   *
   * @param <T> data type for {@code output} output
   * @param params The tensor from which to gather values.
   * @param indices Index tensor.
   * @param <T> data type for {@code GatherNd} output and operands
   * @return a new instance of GatherNd
   */
  public <T extends TType> GatherNd<T> gatherNd(Operand<T> params,
      Operand<? extends TNumber> indices) {
    return GatherNd.create(scope, params, indices);
  }

  /**
   * Store the input tensor in the state of the current session.
   *
   * @param value The tensor to be stored.
   * @return a new instance of GetSessionHandle
   */
  public GetSessionHandle getSessionHandle(Operand<? extends TType> value) {
    return GetSessionHandle.create(scope, value);
  }

  /**
   * Get the value of the tensor specified by its handle.
   *
   * @param <T> data type for {@code value} output
   * @param handle The handle for a tensor stored in the session state.
   * @param dtype The type of the output value.
   * @param <T> data type for {@code GetSessionTensor} output and operands
   * @return a new instance of GetSessionTensor
   */
  public <T extends TType> GetSessionTensor<T> getSessionTensor(Operand<TString> handle,
      Class<T> dtype) {
    return GetSessionTensor.create(scope, handle, dtype);
  }

  /**
   * Adds gradients computation ops to the graph according to scope.
   *
   * @param y outputs of the function to derive
   * @param x inputs of the function for which partial derivatives are computed
   * @param options carries optional attributes values
   * @return a new instance of {@code Gradients}
   * @throws IllegalArgumentException if execution environment is not a graph
   */
  public Gradients gradients(Iterable<? extends Operand<?>> y, Iterable<? extends Operand<?>> x,
      Gradients.Options... options) {
    return Gradients.create(scope, y, x, options);
  }

  /**
   * Adds operations to compute the partial derivatives of sum of {@code y}s w.r.t {@code x}s,
   *  i.e., {@code d(y_1 + y_2 + ...)/dx_1, d(y_1 + y_2 + ...)/dx_2...}
   *  <p> 
   *  If {@code Options.dx()} values are set, they are as the initial symbolic partial derivatives of some loss 
   *  function {@code L} w.r.t. {@code y}. {@code Options.dx()} must have the size of {@code y}.
   *  <p>
   *  If {@code Options.dx()} is not set, the implementation will use dx of {@code OnesLike} for all
   *  shapes in {@code y}.
   *  <p>
   *  The partial derivatives are returned in output {@code dy}, with the size of {@code x}.
   *  <p>
   *  Example of usage:
   *  <pre>{@code
   *  Gradients gradients = tf.gradients(loss, Arrays.asList(w, b));
   *  Constant<TFloat32> alpha = tf.constant(1.0f);
   *  tf.train.applyGradientDescent(w, alpha, gradients.<Float>dy(0));
   *  tf.train.applyGradientDescent(b, alpha, gradients.<Float>dy(1));
   *  }</pre>
   *
   * @param y output of the function to derive
   * @param x inputs of the function for which partial derivatives are computed
   * @param options carries optional attributes values
   * @return a new instance of {@code Gradients}
   * @throws IllegalArgumentException if execution environment is not a graph
   */
  public Gradients gradients(Operand<?> y, Iterable<? extends Operand<?>> x,
      Gradients.Options... options) {
    return Gradients.create(scope, y, x, options);
  }

  /**
   * Gives a guarantee to the TF runtime that the input tensor is a constant.
   *  The runtime is then free to make optimizations based on this.
   *  <p>Only accepts value typed tensors as inputs and rejects resource variable handles
   *  as input.
   *  <p>Returns the input tensor without modification.
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param <T> data type for {@code GuaranteeConst} output and operands
   * @return a new instance of GuaranteeConst
   */
  public <T extends TType> GuaranteeConst<T> guaranteeConst(Operand<T> input) {
    return GuaranteeConst.create(scope, input);
  }

  /**
   * Creates a non-initialized hash table.
   *  This op creates a hash table, specifying the type of its keys and values.
   *  Before using the table you will have to initialize it.  After initialization the
   *  table will be immutable.
   *
   * @param keyDtype Type of the table keys.
   * @param valueDtype Type of the table values.
   * @param options carries optional attribute values
   * @param <T> data type for {@code HashTableV2} output and operands
   * @param <U> data type for {@code HashTableV2} output and operands
   * @return a new instance of HashTable
   */
  public <T extends TType, U extends TType> HashTable hashTable(Class<T> keyDtype,
      Class<U> valueDtype, HashTable.Options... options) {
    return HashTable.create(scope, keyDtype, valueDtype, options);
  }

  /**
   * Return histogram of values.
   *  Given the tensor {@code values}, this operation returns a rank 1 histogram counting
   *  the number of entries in {@code values} that fall into every bin.  The bins are
   *  equal width and determined by the arguments {@code value_range} and {@code nbins}.
   *  <pre>
   *  # Bins will be:  (-inf, 1), [1, 2), [2, 3), [3, 4), [4, inf)
   *  nbins = 5
   *  value_range = [0.0, 5.0]
   *  new_values = [-1.0, 0.0, 1.5, 2.0, 5.0, 15]
   *
   *  with tf.get_default_session() as sess:
   *    hist = tf.histogram_fixed_width(new_values, value_range, nbins=5)
   *    variables.global_variables_initializer().run()
   *    sess.run(hist) =&gt; [2, 1, 1, 0, 2]
   *  </pre>
   *
   * @param <U> data type for {@code out} output
   * @param values Numeric {@code Tensor}.
   * @param valueRange Shape [2] {@code Tensor} of same {@code dtype} as {@code values}.
   *  values &lt;= value_range[0] will be mapped to hist[0],
   *  values &gt;= value_range[1] will be mapped to hist[-1].
   * @param nbins Scalar {@code int32 Tensor}.  Number of histogram bins.
   * @param <T> data type for {@code HistogramFixedWidth} output and operands
   * @return a new instance of HistogramFixedWidth, with default output types
   */
  public <T extends TNumber> HistogramFixedWidth<TInt32> histogramFixedWidth(Operand<T> values,
      Operand<T> valueRange, Operand<TInt32> nbins) {
    return HistogramFixedWidth.create(scope, values, valueRange, nbins);
  }

  /**
   * Return histogram of values.
   *  Given the tensor {@code values}, this operation returns a rank 1 histogram counting
   *  the number of entries in {@code values} that fall into every bin.  The bins are
   *  equal width and determined by the arguments {@code value_range} and {@code nbins}.
   *  <pre>
   *  # Bins will be:  (-inf, 1), [1, 2), [2, 3), [3, 4), [4, inf)
   *  nbins = 5
   *  value_range = [0.0, 5.0]
   *  new_values = [-1.0, 0.0, 1.5, 2.0, 5.0, 15]
   *
   *  with tf.get_default_session() as sess:
   *    hist = tf.histogram_fixed_width(new_values, value_range, nbins=5)
   *    variables.global_variables_initializer().run()
   *    sess.run(hist) =&gt; [2, 1, 1, 0, 2]
   *  </pre>
   *
   * @param <U> data type for {@code out} output
   * @param values Numeric {@code Tensor}.
   * @param valueRange Shape [2] {@code Tensor} of same {@code dtype} as {@code values}.
   *  values &lt;= value_range[0] will be mapped to hist[0],
   *  values &gt;= value_range[1] will be mapped to hist[-1].
   * @param nbins Scalar {@code int32 Tensor}.  Number of histogram bins.
   * @param dtype the value of the dtype property
   * @param <U> data type for {@code HistogramFixedWidth} output and operands
   * @param <T> data type for {@code HistogramFixedWidth} output and operands
   * @return a new instance of HistogramFixedWidth
   */
  public <U extends TNumber, T extends TNumber> HistogramFixedWidth<U> histogramFixedWidth(
      Operand<T> values, Operand<T> valueRange, Operand<TInt32> nbins, Class<U> dtype) {
    return HistogramFixedWidth.create(scope, values, valueRange, nbins, dtype);
  }

  /**
   * Return a tensor with the same shape and contents as the input tensor or value.
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param <T> data type for {@code Identity} output and operands
   * @return a new instance of Identity
   */
  public <T extends TType> Identity<T> identity(Operand<T> input) {
    return Identity.create(scope, input);
  }

  /**
   * Returns a list of tensors with the same shapes and contents as the input
   *  tensors.
   *  <p>This op can be used to override the gradient for complicated functions. For
   *  example, suppose y = f(x) and we wish to apply a custom function g for backprop
   *  such that dx = g(dy). In Python,
   *  <pre>
   *  with tf.get_default_graph().gradient_override_map(
   *      {'IdentityN': 'OverrideGradientWithG'}):
   *    y, _ = identity_n([f(x), x])
   *
   *  {@literal @}tf.RegisterGradient('OverrideGradientWithG')
   *  def ApplyG(op, dy, _):
   *    return [None, g(dy)]  # Do not backprop to f(x).
   *  </pre>
   *
   * @param input the input value
   * @return a new instance of IdentityN
   */
  public IdentityN identityN(Iterable<Operand<?>> input) {
    return IdentityN.create(scope, input);
  }

  /**
   * output = cond ? then_branch(input) : else_branch(input)
   *
   *  <p>Selects between {@link StatefulIf} and {@link StatelessIf} based on the statefulness of the function arguments.
   *
   * @param cond <pre>
   *    A Tensor. If the tensor is a scalar of non-boolean type, the
   *    scalar is converted to a boolean according to the
   *    following rule: if the scalar is a numerical value, non-zero means
   *    `True` and zero means False; if the scalar is a string, non-empty
   *    means `True` and empty means `False`. If the tensor is not a scalar,
   *    being empty means False and being non-empty means True.
   *  </pre>
   * @param input A list of input tensors.
   * @param Tout A list of output types.
   * @param thenBranch <pre>
   *    A function that takes 'inputs' and returns a list of tensors, whose
   *    types are the same as what else_branch returns.
   *  </pre>
   * @param elseBranch <pre>
   *  A function that takes 'inputs' and returns a list of tensors, whose
   *  types are the same as what then_branch returns.
   *  </pre>
   * @param options carries optional attribute values
   * @return a new instance of If
   */
  public If ifOp(Operand<? extends TType> cond, Iterable<Operand<?>> input,
      List<Class<? extends TType>> Tout, ConcreteFunction thenBranch, ConcreteFunction elseBranch,
      If.Options... options) {
    return If.create(scope, cond, input, Tout, thenBranch, elseBranch, options);
  }

  /**
   * Returns immutable tensor from memory region.
   *  The current implementation memmaps the tensor from a file.
   *
   * @param <T> data type for {@code tensor} output
   * @param dtype Type of the returned tensor.
   * @param shape Shape of the returned tensor.
   * @param memoryRegionName Name of readonly memory region used by the tensor, see
   *  NewReadOnlyMemoryRegionFromFile in tensorflow::Env.
   * @param <T> data type for {@code ImmutableConst} output and operands
   * @return a new instance of ImmutableConst
   */
  public <T extends TType> ImmutableConst<T> immutableConst(Class<T> dtype, Shape shape,
      String memoryRegionName) {
    return ImmutableConst.create(scope, dtype, shape, memoryRegionName);
  }

  /**
   * Factory method to create an operation executing all initializers of a graph.
   *
   *  <p>All initializers added to a graph via
   *  {@link org.tensorflow.op.core.Init#add(Scope, Op) tf.initAdd} are grouped together as a single
   *  unit of computation in the graph. This operation must then be added to any graph using one or
   *  more {@link Variable variables} and executed once before running the graph so the variable
   *  states are initialized properly.</p>
   *
   *  <p>When the graph is built by the same process that is running the session, the initializers
   *  can be invoked by executing this single endpoint. For example:</p>
   *  <pre>{@code
   *  try (Graph g = new Graph()) {
   *    Variable<TInt32> x = tf.variable(tf.constant(10));  // initAdd is called implicitly
   *    Variable<TInt32> y = tf.variable(tf.constant(20));  // idem
   *    Add<TInt32> z = tf.math.add(x, y);
   *
   *    try (Session s = new Session(g)) {
   *      s.run(tf.init());  // initialize all variables
   *
   *      try (TInt32 t = (TInt32)s.runner().fetch(z).run().get(0)) {
   *        assertEquals(30, t.data().getInt());
   *      }
   *    }
   *  }
   *  }</pre>
   *
   *  <p>When the graph is built by a separate process, the initializers can be invoked by running
   *  the init op by its name, which defaults to {@link org.tensorflow.op.core.Init#DEFAULT_NAME}.
   *  For example:</p>
   *  <pre>{@code
   *  // Building the model
   *  try (Graph g = new Graph()) {
   *    Variable<TInt32> x = tf.variable(tf.constant(10));  // initAdd is called implicitly
   *    Variable<TInt32> y = tf.variable(tf.constant(20));  // idem
   *    Add<TInt32> z = tf.withName("z").math.add(x, y);
   *
   *    tf.init();  // add variables initializers to the graph, as Init.DEFAULT_NAME
   *    // ...exporting graph as a saved model...
   *  }
   *
   *  ...
   *
   *  // Running the model
   *  try (SavedModelBundle model = SavedModelBundle.load("/path/to/model", "train")) {
   *    model.session().run(Init.DEFAULT_NAME);
   *
   *    try (TInt32 t = (TInt32)s.runner().fetch("z").run().get(0)) {
   *      assertEquals(30, t.data().getInt());
   *    }
   *  }
   *  }</pre>
   *
   * @return an op grouping all initializers added to the graph
   * @throws IllegalArgumentException if the execution environment in scope is not a graph
   */
  public Init init() {
    return Init.create(scope);
  }

  /**
   * Register an op as an initializer of the graph.
   *
   *  <p>Registered initializers are then grouped as a single unit of computation by adding
   *  and executing an {@link org.tensorflow.op.core.Init#create(Scope) init} operation from a graph
   *  session.  This is a no-op if executed in an eager session.
   *
   * @param initializer
   * @see org.tensorflow.op.core.Init#create(Scope) init
   */
  public void initAdd(Op initializer) {
    Init.add(scope, initializer);
  }

  /**
   * Table initializer that takes two tensors for keys and values respectively.
   *
   * @param tableHandle Handle to a table which will be initialized.
   * @param keys Keys of type Tkey.
   * @param values Values of type Tval.
   * @return a new instance of InitializeTable
   */
  public InitializeTable initializeTable(Operand<? extends TType> tableHandle,
      Operand<? extends TType> keys, Operand<? extends TType> values) {
    return InitializeTable.create(scope, tableHandle, keys, values);
  }

  /**
   * Initializes a table from a text file.
   *  It inserts one key-value pair into the table for each line of the file.
   *  The key and value is extracted from the whole line content, elements from the
   *  split line based on {@code delimiter} or the line number (starting from zero).
   *  Where to extract the key and value from a line is specified by {@code key_index} and
   *  {@code value_index}.
   *  <ul>
   *  <li>A value of -1 means use the line number(starting from zero), expects {@code int64}.</li>
   *  <li>A value of -2 means use the whole line content, expects {@code string}.</li>
   *  <li>A value &gt;= 0 means use the index (starting at zero) of the split line based
   *  on {@code delimiter}.</li>
   *  </ul>
   *
   * @param tableHandle Handle to a table which will be initialized.
   * @param filename Filename of a vocabulary text file.
   * @param keyIndex Column index in a line to get the table {@code key} values from.
   * @param valueIndex Column index that represents information of a line to get the table
   *  {@code value} values from.
   * @param options carries optional attribute values
   * @return a new instance of InitializeTableFromTextFile
   */
  public InitializeTableFromTextFile initializeTableFromTextFile(
      Operand<? extends TType> tableHandle, Operand<TString> filename, Long keyIndex,
      Long valueIndex, InitializeTableFromTextFile.Options... options) {
    return InitializeTableFromTextFile.create(scope, tableHandle, filename, keyIndex, valueIndex, options);
  }

  /**
   * Adds v into specified rows of x.
   *  <pre>
   *  Computes y = x; y[i, :] += v; return y.
   *  </pre>
   *
   * @param <T> data type for {@code y} output
   * @param x A {@code Tensor} of type T.
   * @param i A vector. Indices into the left-most dimension of {@code x}.
   * @param v A {@code Tensor} of type T. Same dimension sizes as x except the first dimension, which must be the same as i's size.
   * @param <T> data type for {@code InplaceAdd} output and operands
   * @return a new instance of InplaceAdd
   */
  public <T extends TType> InplaceAdd<T> inplaceAdd(Operand<T> x, Operand<TInt32> i, Operand<T> v) {
    return InplaceAdd.create(scope, x, i, v);
  }

  /**
   * <pre>
   *  Subtracts `v` into specified rows of `x`.
   *
   *  Computes y = x; y[i, :] -= v; return y.
   *  </pre>
   *
   * @param <T> data type for {@code y} output
   * @param x A {@code Tensor} of type T.
   * @param i A vector. Indices into the left-most dimension of {@code x}.
   * @param v A {@code Tensor} of type T. Same dimension sizes as x except the first dimension, which must be the same as i's size.
   * @param <T> data type for {@code InplaceSub} output and operands
   * @return a new instance of InplaceSub
   */
  public <T extends TType> InplaceSub<T> inplaceSub(Operand<T> x, Operand<TInt32> i, Operand<T> v) {
    return InplaceSub.create(scope, x, i, v);
  }

  /**
   * Updates specified rows 'i' with values 'v'.
   *  Computes {@code x[i, :] = v; return x}.
   *  <p>Originally this function is mutative however for compilation we make this
   *  operation create / operate on a copy of {@code x}.
   *
   * @param <T> data type for {@code y} output
   * @param x A tensor of type {@code T}.
   * @param i A vector. Indices into the left-most dimension of {@code x}.
   * @param v A {@code Tensor} of type T. Same dimension sizes as x except the first dimension, which must be the same as i's size.
   * @param <T> data type for {@code InplaceUpdate} output and operands
   * @return a new instance of InplaceUpdate
   */
  public <T extends TType> InplaceUpdate<T> inplaceUpdate(Operand<T> x, Operand<TInt32> i,
      Operand<T> v) {
    return InplaceUpdate.create(scope, x, i, v);
  }

  /**
   * Checks whether a tensor has been initialized.
   *  Outputs boolean scalar indicating whether the tensor has been initialized.
   *
   * @param ref Should be from a {@code Variable} node. May be uninitialized.
   * @return a new instance of IsVariableInitialized
   */
  public IsVariableInitialized isVariableInitialized(Operand<? extends TType> ref) {
    return IsVariableInitialized.create(scope, ref);
  }

  /**
   * Computes the Kth order statistic of a data set. The current
   *  implementation uses a binary search requiring exactly 32 passes over
   *  the input data. The running time is linear with respect to input
   *  size. The median-of-medians algorithm is probably faster, but is
   *  difficult to implement efficiently in XLA. The implementation imposes
   *  a total ordering on floats. The ordering is consistent with the usual
   *  partial order.  Positive NaNs are greater than positive
   *  infinity. Negative NaNs are less than negative infinity. NaNs with
   *  distinct payloads are treated as distinct. Subnormal numbers are
   *  preserved (not flushed to zero). Positive infinity is greater than all
   *  numbers. Negative infinity is less than all numbers. Positive is
   *  greater than negative zero. There are less than k values greater than
   *  the kth order statistic. There are at least k values greater than or
   *  equal to the Kth order statistic. The semantics are not the same as
   *  top_k_unique.
   *
   * @param input the input value
   * @param k the value of the k property
   * @return a new instance of KthOrderStatistic
   */
  public KthOrderStatistic kthOrderStatistic(Operand<TFloat32> input, Long k) {
    return KthOrderStatistic.create(scope, input, k);
  }

  /**
   * Outputs all keys and values in the table.
   *
   * @param <T> data type for {@code keys} output
   * @param <U> data type for {@code values} output
   * @param tableHandle Handle to the table.
   * @param Tkeys the value of the Tkeys property
   * @param Tvalues the value of the Tvalues property
   * @param <T> data type for {@code LookupTableExportV2} output and operands
   * @param <U> data type for {@code LookupTableExportV2} output and operands
   * @return a new instance of LookupTableExport
   */
  public <T extends TType, U extends TType> LookupTableExport<T, U> lookupTableExport(
      Operand<? extends TType> tableHandle, Class<T> Tkeys, Class<U> Tvalues) {
    return LookupTableExport.create(scope, tableHandle, Tkeys, Tvalues);
  }

  /**
   * Looks up keys in a table, outputs the corresponding values.
   *  The tensor {@code keys} must of the same type as the keys of the table.
   *  The output {@code values} is of the type of the table values.
   *  <p>The scalar {@code default_value} is the value output for keys not present in the
   *  table. It must also be of the same type as the table values.
   *
   * @param <U> data type for {@code values} output
   * @param tableHandle Handle to the table.
   * @param keys Any shape.  Keys to look up.
   * @param defaultValue the defaultValue value
   * @param <U> data type for {@code LookupTableFindV2} output and operands
   * @return a new instance of LookupTableFind
   */
  public <U extends TType> LookupTableFind<U> lookupTableFind(Operand<? extends TType> tableHandle,
      Operand<? extends TType> keys, Operand<U> defaultValue) {
    return LookupTableFind.create(scope, tableHandle, keys, defaultValue);
  }

  /**
   * Replaces the contents of the table with the specified keys and values.
   *  The tensor {@code keys} must be of the same type as the keys of the table.
   *  The tensor {@code values} must be of the type of the table values.
   *
   * @param tableHandle Handle to the table.
   * @param keys Any shape.  Keys to look up.
   * @param values Values to associate with keys.
   * @return a new instance of LookupTableImport
   */
  public LookupTableImport lookupTableImport(Operand<? extends TType> tableHandle,
      Operand<? extends TType> keys, Operand<? extends TType> values) {
    return LookupTableImport.create(scope, tableHandle, keys, values);
  }

  /**
   * Updates the table to associates keys with values.
   *  The tensor {@code keys} must be of the same type as the keys of the table.
   *  The tensor {@code values} must be of the type of the table values.
   *
   * @param tableHandle Handle to the table.
   * @param keys Any shape.  Keys to look up.
   * @param values Values to associate with keys.
   * @return a new instance of LookupTableInsert
   */
  public LookupTableInsert lookupTableInsert(Operand<? extends TType> tableHandle,
      Operand<? extends TType> keys, Operand<? extends TType> values) {
    return LookupTableInsert.create(scope, tableHandle, keys, values);
  }

  /**
   * Computes the number of elements in the given table.
   *
   * @param tableHandle Handle to the table.
   * @return a new instance of LookupTableSize
   */
  public LookupTableSize lookupTableSize(Operand<? extends TType> tableHandle) {
    return LookupTableSize.create(scope, tableHandle);
  }

  /**
   * Forwards the input to the output.
   *  This operator represents the loop termination condition used by the
   *  &quot;pivot&quot; switches of a loop.
   *
   * @param input A boolean scalar, representing the branch predicate of the Switch op.
   * @return a new instance of LoopCond
   */
  public LoopCond loopCond(Operand<TBool> input) {
    return LoopCond.create(scope, input);
  }

  /**
   * Make all elements in the non-Batch dimension unique, but &quot;close&quot; to
   *  their initial value. Never returns a sub-normal number. Never returns
   *  zero. The sign of each input element is always identical to the sign
   *  of the corresponding output element. Behavior for infinite elements is
   *  undefined. Behavior for subnormal elements is undefined.
   *
   * @param input the input value
   * @return a new instance of MakeUnique
   */
  public MakeUnique makeUnique(Operand<TFloat32> input) {
    return MakeUnique.create(scope, input);
  }

  /**
   * Op removes all elements in the underlying container.
   *
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of MapClear
   */
  public MapClear mapClear(List<Class<? extends TType>> dtypes, MapClear.Options... options) {
    return MapClear.create(scope, dtypes, options);
  }

  /**
   * Op returns the number of incomplete elements in the underlying container.
   *
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of MapIncompleteSize
   */
  public MapIncompleteSize mapIncompleteSize(List<Class<? extends TType>> dtypes,
      MapIncompleteSize.Options... options) {
    return MapIncompleteSize.create(scope, dtypes, options);
  }

  /**
   * Op peeks at the values at the specified key.  If the
   *  underlying container does not contain this key
   *  this op will block until it does.
   *
   * @param key the key value
   * @param indices the indices value
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of MapPeek
   */
  public MapPeek mapPeek(Operand<TInt64> key, Operand<TInt32> indices,
      List<Class<? extends TType>> dtypes, MapPeek.Options... options) {
    return MapPeek.create(scope, key, indices, dtypes, options);
  }

  /**
   * Op returns the number of elements in the underlying container.
   *
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of MapSize
   */
  public MapSize mapSize(List<Class<? extends TType>> dtypes, MapSize.Options... options) {
    return MapSize.create(scope, dtypes, options);
  }

  /**
   * Stage (key, values) in the underlying container which behaves like a hashtable.
   *
   * @param key int64
   * @param indices the indices value
   * @param values a list of tensors
   *  dtypes A list of data types that inserted values should adhere to.
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of MapStage
   */
  public MapStage mapStage(Operand<TInt64> key, Operand<TInt32> indices,
      Iterable<Operand<?>> values, List<Class<? extends TType>> dtypes,
      MapStage.Options... options) {
    return MapStage.create(scope, key, indices, values, dtypes, options);
  }

  /**
   * Op removes and returns the values associated with the key
   *  from the underlying container.   If the underlying container
   *  does not contain this key, the op will block until it does.
   *
   * @param key the key value
   * @param indices the indices value
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of MapUnstage
   */
  public MapUnstage mapUnstage(Operand<TInt64> key, Operand<TInt32> indices,
      List<Class<? extends TType>> dtypes, MapUnstage.Options... options) {
    return MapUnstage.create(scope, key, indices, dtypes, options);
  }

  /**
   * Op removes and returns a random (key, value)
   *  from the underlying container.   If the underlying container
   *  does not contain elements, the op will block until it does.
   *
   * @param indices the indices value
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of MapUnstageNoKey
   */
  public MapUnstageNoKey mapUnstageNoKey(Operand<TInt32> indices,
      List<Class<? extends TType>> dtypes, MapUnstageNoKey.Options... options) {
    return MapUnstageNoKey.create(scope, indices, dtypes, options);
  }

  /**
   * Computes the maximum of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param <T> data type for {@code output} output
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Max} output and operands
   * @return a new instance of Max
   */
  public <T extends TNumber> Max<T> max(Operand<T> input, Operand<? extends TNumber> axis,
      Max.Options... options) {
    return Max.create(scope, input, axis, options);
  }

  /**
   * Forwards the value of an available tensor from {@code inputs} to {@code output}.
   *  {@code Merge} waits for at least one of the tensors in {@code inputs} to become available.
   *  It is usually combined with {@code Switch} to implement branching.
   *  <p>{@code Merge} forwards the first tensor to become available to {@code output}, and sets
   *  {@code value_index} to its index in {@code inputs}.
   *
   * @param <T> data type for {@code output} output
   * @param inputs The input tensors, exactly one of which will become available.
   * @param <T> data type for {@code Merge} output and operands
   * @return a new instance of Merge
   */
  public <T extends TType> Merge<T> merge(Iterable<Operand<T>> inputs) {
    return Merge.create(scope, inputs);
  }

  /**
   * Computes the minimum of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param <T> data type for {@code output} output
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Min} output and operands
   * @return a new instance of Min
   */
  public <T extends TNumber> Min<T> min(Operand<T> input, Operand<? extends TNumber> axis,
      Min.Options... options) {
    return Min.create(scope, input, axis, options);
  }

  /**
   * Pads a tensor with mirrored values.
   *  This operation pads a {@code input} with mirrored values according to the {@code paddings}
   *  you specify. {@code paddings} is an integer tensor with shape {@code [n, 2]}, where n is
   *  the rank of {@code input}. For each dimension D of {@code input}, {@code paddings[D, 0]} indicates
   *  how many values to add before the contents of {@code input} in that dimension, and
   *  {@code paddings[D, 1]} indicates how many values to add after the contents of {@code input}
   *  in that dimension. Both {@code paddings[D, 0]} and {@code paddings[D, 1]} must be no greater
   *  than {@code input.dim_size(D)} (or {@code input.dim_size(D) - 1}) if {@code copy_border} is true
   *  (if false, respectively).
   *  <p>The padded size of each dimension D of the output is:
   *  <p>{@code paddings(D, 0) + input.dim_size(D) + paddings(D, 1)}
   *  <p>For example:
   *  <pre>
   *  # 't' is [[1, 2, 3], [4, 5, 6]].
   *  # 'paddings' is [[1, 1]], [2, 2]].
   *  # 'mode' is SYMMETRIC.
   *  # rank of 't' is 2.
   *  pad(t, paddings) ==&gt; [[2, 1, 1, 2, 3, 3, 2]
   *                        [2, 1, 1, 2, 3, 3, 2]
   *                        [5, 4, 4, 5, 6, 6, 5]
   *                        [5, 4, 4, 5, 6, 6, 5]]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param input The input tensor to be padded.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   *  rows must be the same as the rank of {@code input}.
   * @param mode Either {@code REFLECT} or {@code SYMMETRIC}. In reflect mode the padded regions
   *  do not include the borders, while in symmetric mode the padded regions
   *  do include the borders. For example, if {@code input} is {@code [1, 2, 3]} and {@code paddings}
   *  is {@code [0, 2]}, then the output is {@code [1, 2, 3, 2, 1]} in reflect mode, and
   *  it is {@code [1, 2, 3, 3, 2]} in symmetric mode.
   * @param <T> data type for {@code MirrorPad} output and operands
   * @return a new instance of MirrorPad
   */
  public <T extends TType> MirrorPad<T> mirrorPad(Operand<T> input,
      Operand<? extends TNumber> paddings, String mode) {
    return MirrorPad.create(scope, input, paddings, mode);
  }

  /**
   * Wraps an arbitrary MLIR computation expressed as a module with a main() function.
   *  This operation does not have an associated kernel and is not intended to be
   *  executed in a regular TensorFlow session. Instead it is intended to be used for
   *  testing or for special case where a user intends to pass custom MLIR computation
   *  through a TensorFlow graph with the intent of having custom tooling processing
   *  it downstream (when targeting a different environment, like TensorFlow lite for
   *  example).
   *  The MLIR module is expected to have a main() function that will be used as an
   *  entry point. The inputs to the operations will be passed as argument to the
   *  main() function and the returned values of the main function mapped to the
   *  outputs.
   *  Example usage:
   *  <pre>
   *  import tensorflow as tf
   *  from tensorflow.compiler.mlir.tensorflow.gen_mlir_passthrough_op import mlir_passthrough_op
   *
   *  mlir_module = '''python
   *  func {@literal @}main(%arg0 : tensor&lt;10xf32&gt;, %arg1 : tensor&lt;10xf32&gt;) -&gt; tensor&lt;10x10xf32&gt; {
   *     %add = &quot;magic.op&quot;(%arg0, %arg1) : (tensor&lt;10xf32&gt;, tensor&lt;10xf32&gt;) -&gt; tensor&lt;10x10xf32&gt;
   *     return %ret : tensor&lt;10x10xf32&gt;
   *  }
   *  '''
   *
   *  {@literal @}tf.function
   *  def foo(x, y):
   *    return mlir_passthrough_op([x, y], mlir_module, Toutputs=[tf.float32])
   *
   *  graph_def = foo.get_concrete_function(tf.TensorSpec([10], tf.float32), tf.TensorSpec([10], tf.float32)).graph.as_graph_def()
   *  </pre>
   *
   * @param inputs the inputs value
   * @param mlirModule the value of the mlirModule property
   * @param Toutputs the value of the Toutputs property
   * @return a new instance of MlirPassthroughOp
   */
  public MlirPassthroughOp mlirPassthroughOp(Iterable<Operand<?>> inputs, String mlirModule,
      List<Class<? extends TType>> Toutputs) {
    return MlirPassthroughOp.create(scope, inputs, mlirModule, Toutputs);
  }

  /**
   * Creates an empty hash table that uses tensors as the backing store.
   *  It uses &quot;open addressing&quot; with quadratic reprobing to resolve
   *  collisions.
   *  <p>This op creates a mutable hash table, specifying the type of its keys and
   *  values. Each value must be a scalar. Data can be inserted into the table using
   *  the insert operations. It does not support the initialization operation.
   *
   * @param emptyKey The key used to represent empty key buckets internally. Must not
   *  be used in insert or lookup operations.
   * @param deletedKey the deletedKey value
   * @param valueDtype Type of the table values.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MutableDenseHashTableV2} output and operands
   * @param <U> data type for {@code MutableDenseHashTableV2} output and operands
   * @return a new instance of MutableDenseHashTable
   */
  public <T extends TType, U extends TType> MutableDenseHashTable mutableDenseHashTable(
      Operand<T> emptyKey, Operand<T> deletedKey, Class<U> valueDtype,
      MutableDenseHashTable.Options... options) {
    return MutableDenseHashTable.create(scope, emptyKey, deletedKey, valueDtype, options);
  }

  /**
   * Creates an empty hash table.
   *  This op creates a mutable hash table, specifying the type of its keys and
   *  values. Each value must be a scalar. Data can be inserted into the table using
   *  the insert operations. It does not support the initialization operation.
   *
   * @param keyDtype Type of the table keys.
   * @param valueDtype Type of the table values.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MutableHashTableV2} output and operands
   * @param <U> data type for {@code MutableHashTableV2} output and operands
   * @return a new instance of MutableHashTable
   */
  public <T extends TType, U extends TType> MutableHashTable mutableHashTable(Class<T> keyDtype,
      Class<U> valueDtype, MutableHashTable.Options... options) {
    return MutableHashTable.create(scope, keyDtype, valueDtype, options);
  }

  /**
   * Creates an empty hash table.
   *  This op creates a mutable hash table, specifying the type of its keys and
   *  values. Each value must be a vector. Data can be inserted into the table using
   *  the insert operations. It does not support the initialization operation.
   *
   * @param keyDtype Type of the table keys.
   * @param valueDtype Type of the table values.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MutableHashTableOfTensorsV2} output and operands
   * @param <U> data type for {@code MutableHashTableOfTensorsV2} output and operands
   * @return a new instance of MutableHashTableOfTensors
   */
  public <T extends TType, U extends TType> MutableHashTableOfTensors mutableHashTableOfTensors(
      Class<T> keyDtype, Class<U> valueDtype, MutableHashTableOfTensors.Options... options) {
    return MutableHashTableOfTensors.create(scope, keyDtype, valueDtype, options);
  }

  /**
   * Creates a Mutex resource that can be locked by {@code MutexLock}.
   *
   * @param options carries optional attribute values
   * @return a new instance of Mutex
   */
  public Mutex mutex(Mutex.Options... options) {
    return Mutex.create(scope, options);
  }

  /**
   * Locks a mutex resource.  The output is the lock.  So long as the lock tensor
   *  is alive, any other request to use {@code MutexLock} with this mutex will wait.
   *  <p>This is particularly useful for creating a critical section when used in
   *  conjunction with {@code MutexLockIdentity}:
   *  <pre>
   *
   *  mutex = mutex_v2(
   *    shared_name=handle_name, container=container, name=name)
   *
   *  def execute_in_critical_section(fn, *args, **kwargs):
   *    lock = gen_resource_variable_ops.mutex_lock(mutex)
   *
   *    with ops.control_dependencies([lock]):
   *      r = fn(*args, **kwargs)
   *
   *    with ops.control_dependencies(nest.flatten(r)):
   *      with ops.colocate_with(mutex):
   *        ensure_lock_exists = mutex_lock_identity(lock)
   *
   *      # Make sure that if any element of r is accessed, all of
   *      # them are executed together.
   *      r = nest.map_structure(tf.identity, r)
   *
   *    with ops.control_dependencies([ensure_lock_exists]):
   *      return nest.map_structure(tf.identity, r)
   *  </pre>
   *  <p>While {@code fn} is running in the critical section, no other functions which wish to
   *  use this critical section may run.
   *  <p>Often the use case is that two executions of the same graph, in parallel,
   *  wish to run {@code fn}; and we wish to ensure that only one of them executes
   *  at a time.  This is especially important if {@code fn} modifies one or more
   *  variables at a time.
   *  <p>It is also useful if two separate functions must share a resource, but we
   *  wish to ensure the usage is exclusive.
   *
   * @param mutex The mutex resource to lock.
   * @return a new instance of MutexLock
   */
  public MutexLock mutexLock(Operand<? extends TType> mutex) {
    return MutexLock.create(scope, mutex);
  }

  /**
   * Makes its input available to the next iteration.
   *
   * @param <T> data type for {@code output} output
   * @param data The tensor to be made available to the next iteration.
   * @param <T> data type for {@code NextIteration} output and operands
   * @return a new instance of NextIteration
   */
  public <T extends TType> NextIteration<T> nextIteration(Operand<T> data) {
    return NextIteration.create(scope, data);
  }

  /**
   * Does nothing. Only useful as a placeholder for control edges.
   *
   * @return a new instance of NoOp
   */
  public NoOp noOp() {
    return NoOp.create(scope);
  }

  /**
   * Returns a one-hot tensor.
   *  The locations represented by indices in {@code indices} take value {@code on_value},
   *  while all other locations take value {@code off_value}.
   *  <p>If the input {@code indices} is rank {@code N}, the output will have rank {@code N+1},
   *  The new axis is created at dimension {@code axis} (default: the new axis is
   *  appended at the end).
   *  <p>If {@code indices} is a scalar the output shape will be a vector of length {@code depth}.
   *  <p>If {@code indices} is a vector of length {@code features}, the output shape will be:
   *  <pre>
   *    features x depth if axis == -1
   *    depth x features if axis == 0
   *  </pre>
   *  <p>If {@code indices} is a matrix (batch) with shape {@code [batch, features]},
   *  the output shape will be:
   *  <pre>
   *    batch x features x depth if axis == -1
   *    batch x depth x features if axis == 1
   *    depth x batch x features if axis == 0
   *  </pre>
   *  <strong>Examples</strong><br>
   *  <p>Suppose that
   *  <pre>
   *    indices = [0, 2, -1, 1]
   *    depth = 3
   *    on_value = 5.0
   *    off_value = 0.0
   *    axis = -1
   *  </pre>
   *  <p>Then output is {@code [4 x 3]}:
   *  <pre>
   *  output =
   *    [5.0 0.0 0.0]  // one_hot(0)
   *    [0.0 0.0 5.0]  // one_hot(2)
   *    [0.0 0.0 0.0]  // one_hot(-1)
   *    [0.0 5.0 0.0]  // one_hot(1)
   *  </pre>
   *  <p>Suppose that
   *  <pre>
   *    indices = [0, 2, -1, 1]
   *    depth = 3
   *    on_value = 0.0
   *    off_value = 3.0
   *    axis = 0
   *  </pre>
   *  <p>Then output is {@code [3 x 4]}:
   *  <pre>
   *  output =
   *    [0.0 3.0 3.0 3.0]
   *    [3.0 3.0 3.0 0.0]
   *    [3.0 3.0 3.0 3.0]
   *    [3.0 0.0 3.0 3.0]
   *  //  ^                one_hot(0)
   *  //      ^            one_hot(2)
   *  //          ^        one_hot(-1)
   *  //              ^    one_hot(1)
   *  </pre>
   *  <p>Suppose that
   *  <pre>
   *    indices = [[0, 2], [1, -1]]
   *    depth = 3
   *    on_value = 1.0
   *    off_value = 0.0
   *    axis = -1
   *  </pre>
   *  <p>Then output is {@code [2 x 2 x 3]}:
   *  <pre>
   *  output =
   *    [
   *      [1.0, 0.0, 0.0]  // one_hot(0)
   *      [0.0, 0.0, 1.0]  // one_hot(2)
   *    ][
   *      [0.0, 1.0, 0.0]  // one_hot(1)
   *      [0.0, 0.0, 0.0]  // one_hot(-1)
   *    ]
   *  </pre>
   *
   * @param <U> data type for {@code output} output
   * @param indices A tensor of indices.
   * @param depth A scalar defining the depth of the one hot dimension.
   * @param onValue A scalar defining the value to fill in output when {@code indices[j] = i}.
   * @param offValue A scalar defining the value to fill in output when {@code indices[j] != i}.
   * @param options carries optional attribute values
   * @param <U> data type for {@code OneHot} output and operands
   * @return a new instance of OneHot
   */
  public <U extends TType> OneHot<U> oneHot(Operand<? extends TNumber> indices,
      Operand<TInt32> depth, Operand<U> onValue, Operand<U> offValue, OneHot.Options... options) {
    return OneHot.create(scope, indices, depth, onValue, offValue, options);
  }

  /**
   * Creates a one valued tensor given its type and shape.
   *
   * @param dims a 1-D operand that represents the shape of the output tensor
   * @param type the output tensor type class. Can not be TString.
   * @return a constant tensor initialized with ones
   * @throws IllegalArgumentException if the tensor type or shape cannot be initialized with ones.
   */
  public <T extends TType> Ones<T> ones(Operand<? extends TNumber> dims, Class<T> type) {
    return Ones.create(scope, dims, type);
  }

  /**
   * Returns a tensor of ones with the same shape and type as x.
   *
   * @param <T> data type for {@code y} output
   * @param x a tensor of type T.
   * @param <T> data type for {@code OnesLike} output and operands
   * @return a new instance of OnesLike
   */
  public <T extends TType> OnesLike<T> onesLike(Operand<T> x) {
    return OnesLike.create(scope, x);
  }

  /**
   * Op removes all elements in the underlying container.
   *
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of OrderedMapClear
   */
  public OrderedMapClear orderedMapClear(List<Class<? extends TType>> dtypes,
      OrderedMapClear.Options... options) {
    return OrderedMapClear.create(scope, dtypes, options);
  }

  /**
   * Op returns the number of incomplete elements in the underlying container.
   *
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of OrderedMapIncompleteSize
   */
  public OrderedMapIncompleteSize orderedMapIncompleteSize(List<Class<? extends TType>> dtypes,
      OrderedMapIncompleteSize.Options... options) {
    return OrderedMapIncompleteSize.create(scope, dtypes, options);
  }

  /**
   * Op peeks at the values at the specified key.  If the
   *  underlying container does not contain this key
   *  this op will block until it does.   This Op is optimized for
   *  performance.
   *
   * @param key the key value
   * @param indices the indices value
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of OrderedMapPeek
   */
  public OrderedMapPeek orderedMapPeek(Operand<TInt64> key, Operand<TInt32> indices,
      List<Class<? extends TType>> dtypes, OrderedMapPeek.Options... options) {
    return OrderedMapPeek.create(scope, key, indices, dtypes, options);
  }

  /**
   * Op returns the number of elements in the underlying container.
   *
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of OrderedMapSize
   */
  public OrderedMapSize orderedMapSize(List<Class<? extends TType>> dtypes,
      OrderedMapSize.Options... options) {
    return OrderedMapSize.create(scope, dtypes, options);
  }

  /**
   * Stage (key, values) in the underlying container which behaves like a ordered
   *  associative container.   Elements are ordered by key.
   *
   * @param key int64
   * @param indices the indices value
   * @param values a list of tensors
   *  dtypes A list of data types that inserted values should adhere to.
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of OrderedMapStage
   */
  public OrderedMapStage orderedMapStage(Operand<TInt64> key, Operand<TInt32> indices,
      Iterable<Operand<?>> values, List<Class<? extends TType>> dtypes,
      OrderedMapStage.Options... options) {
    return OrderedMapStage.create(scope, key, indices, values, dtypes, options);
  }

  /**
   * Op removes and returns the values associated with the key
   *  from the underlying container.   If the underlying container
   *  does not contain this key, the op will block until it does.
   *
   * @param key the key value
   * @param indices the indices value
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of OrderedMapUnstage
   */
  public OrderedMapUnstage orderedMapUnstage(Operand<TInt64> key, Operand<TInt32> indices,
      List<Class<? extends TType>> dtypes, OrderedMapUnstage.Options... options) {
    return OrderedMapUnstage.create(scope, key, indices, dtypes, options);
  }

  /**
   * Op removes and returns the (key, value) element with the smallest
   *  key from the underlying container.   If the underlying container
   *  does not contain elements, the op will block until it does.
   *
   * @param indices the indices value
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of OrderedMapUnstageNoKey
   */
  public OrderedMapUnstageNoKey orderedMapUnstageNoKey(Operand<TInt32> indices,
      List<Class<? extends TType>> dtypes, OrderedMapUnstageNoKey.Options... options) {
    return OrderedMapUnstageNoKey.create(scope, indices, dtypes, options);
  }

  /**
   * Pads a tensor.
   *  This operation pads {@code input} according to the {@code paddings} and {@code constant_values}
   *  you specify. {@code paddings} is an integer tensor with shape {@code [Dn, 2]}, where n is
   *  the rank of {@code input}. For each dimension D of {@code input}, {@code paddings[D, 0]} indicates
   *  how many padding values to add before the contents of {@code input} in that dimension,
   *  and {@code paddings[D, 1]} indicates how many padding values to add after the contents
   *  of {@code input} in that dimension. {@code constant_values} is a scalar tensor of the same
   *  type as {@code input} that indicates the value to use for padding {@code input}.
   *  <p>The padded size of each dimension D of the output is:
   *  <p>{@code paddings(D, 0) + input.dim_size(D) + paddings(D, 1)}
   *  <p>For example:
   *  <pre>
   *  # 't' is [[1, 1], [2, 2]]
   *  # 'paddings' is [[1, 1], [2, 2]]
   *  # 'constant_values' is 0
   *  # rank of 't' is 2
   *  pad(t, paddings) ==&gt; [[0, 0, 0, 0, 0, 0]
   *                        [0, 0, 1, 1, 0, 0]
   *                        [0, 0, 2, 2, 0, 0]
   *                        [0, 0, 0, 0, 0, 0]]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param paddings the paddings value
   * @param constantValues the constantValues value
   * @param <T> data type for {@code PadV2} output and operands
   * @return a new instance of Pad
   */
  public <T extends TType> Pad<T> pad(Operand<T> input, Operand<? extends TNumber> paddings,
      Operand<T> constantValues) {
    return Pad.create(scope, input, paddings, constantValues);
  }

  /**
   * Concatenates a list of {@code N} tensors along the first dimension.
   *  The input tensors are all required to have size 1 in the first dimension.
   *  <p>For example:
   *  <pre>
   *  # 'x' is [[1, 4]]
   *  # 'y' is [[2, 5]]
   *  # 'z' is [[3, 6]]
   *  parallel_concat([x, y, z]) =&gt; [[1, 4], [2, 5], [3, 6]]  # Pack along first dim.
   *  </pre>
   *  <p>The difference between concat and parallel_concat is that concat requires all
   *  of the inputs be computed before the operation will begin but doesn't require
   *  that the input shapes be known during graph construction.  Parallel concat
   *  will copy pieces of the input into the output as they become available, in
   *  some situations this can provide a performance benefit.
   *
   * @param <T> data type for {@code output} output
   * @param values Tensors to be concatenated. All must have size 1 in the first dimension
   *  and same shape.
   * @param shape the final shape of the result; should be equal to the shapes of any input
   *  but with the number of input values in the first dimension.
   * @param <T> data type for {@code ParallelConcat} output and operands
   * @return a new instance of ParallelConcat
   */
  public <T extends TType> ParallelConcat<T> parallelConcat(Iterable<Operand<T>> values,
      Shape shape) {
    return ParallelConcat.create(scope, values, shape);
  }

  /**
   * Interleave the values from the {@code data} tensors into a single tensor.
   *  Builds a merged tensor such that
   *  <pre>
   *      merged[indices[m][i, ..., j], ...] = data[m][i, ..., j, ...]
   *  </pre>
   *  <p>For example, if each {@code indices[m]} is scalar or vector, we have
   *  <pre>
   *      # Scalar indices:
   *      merged[indices[m], ...] = data[m][...]
   *
   *      # Vector indices:
   *      merged[indices[m][i], ...] = data[m][i, ...]
   *  </pre>
   *  <p>Each {@code data[i].shape} must start with the corresponding {@code indices[i].shape},
   *  and the rest of {@code data[i].shape} must be constant w.r.t. {@code i}.  That is, we
   *  must have {@code data[i].shape = indices[i].shape + constant}.  In terms of this
   *  {@code constant}, the output shape is
   *  <pre>
   *  merged.shape = [max(indices)] + constant
   *  </pre>
   *  <p>Values may be merged in parallel, so if an index appears in both {@code indices[m][i]}
   *  and {@code indices[n][j]}, the result may be invalid. This differs from the normal
   *  DynamicStitch operator that defines the behavior in that case.
   *  <p>For example:
   *  <pre>
   *      indices[0] = 6
   *      indices[1] = [4, 1]
   *      indices[2] = [[5, 2], [0, 3]]
   *      data[0] = [61, 62]
   *      data[1] = [[41, 42], [11, 12]]
   *      data[2] = [[[51, 52], [21, 22]], [[1, 2], [31, 32]]]
   *      merged = [[1, 2], [11, 12], [21, 22], [31, 32], [41, 42],
   *                [51, 52], [61, 62]]
   *  </pre>
   *  <p>This method can be used to merge partitions created by {@code dynamic_partition}
   *  as illustrated on the following example:
   *  <pre>
   *      # Apply function (increments x_i) on elements for which a certain condition
   *      # apply (x_i != -1 in this example).
   *      x=tf.constant([0.1, -1., 5.2, 4.3, -1., 7.4])
   *      condition_mask=tf.not_equal(x,tf.constant(-1.))
   *      partitioned_data = tf.dynamic_partition(
   *          x, tf.cast(condition_mask, tf.int32) , 2)
   *      partitioned_data[1] = partitioned_data[1] + 1.0
   *      condition_indices = tf.dynamic_partition(
   *          tf.range(tf.shape(x)[0]), tf.cast(condition_mask, tf.int32) , 2)
   *      x = tf.dynamic_stitch(condition_indices, partitioned_data)
   *      # Here x=[1.1, -1., 6.2, 5.3, -1, 8.4], the -1. values remain
   *      # unchanged.
   *  </pre>
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/DynamicStitch.png" alt>
   *  </div>
   *
   * @param <T> data type for {@code merged} output
   * @param indices the indices value
   * @param data the data value
   * @param <T> data type for {@code ParallelDynamicStitch} output and operands
   * @return a new instance of ParallelDynamicStitch
   */
  public <T extends TType> ParallelDynamicStitch<T> parallelDynamicStitch(
      Iterable<Operand<TInt32>> indices, Iterable<Operand<T>> data) {
    return ParallelDynamicStitch.create(scope, indices, data);
  }

  /**
   * returns {@code f(inputs)}, where {@code f}'s body is placed and partitioned.
   *
   *  <p>Selects between {@link StatefulPartitionedCall} and {@link StatelessPartitionedCall} based on the statefulness of the function arguments.
   *
   * @param args A list of input tensors.
   * @param Tout A list of output types.
   * @param f <pre>
   *    A function that takes 'args', a list of tensors, and returns 'output',
   *    another list of tensors. Input and output types are specified by 'Tin'
   *    and 'Tout'. The function body of f will be placed and partitioned across
   *    devices, setting this op apart from the regular Call op. This op is
   *    stateful.
   *  </pre>
   * @param options carries optional attribute values
   * @return a new instance of PartitionedCall
   */
  public PartitionedCall partitionedCall(Iterable<Operand<?>> args,
      List<Class<? extends TType>> Tout, ConcreteFunction f, PartitionedCall.Options... options) {
    return PartitionedCall.create(scope, args, Tout, f, options);
  }

  /**
   * A placeholder op for a value that will be fed into the computation.
   *  N.B. This operation will fail with an error if it is executed. It is
   *  intended as a way to represent a value that will always be fed, and to
   *  provide attrs that enable the fed value to be checked at runtime.
   *
   * @param <T> data type for {@code output} output
   * @param dtype The type of elements in the tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Placeholder} output and operands
   * @return a new instance of Placeholder
   */
  public <T extends TType> Placeholder<T> placeholder(Class<T> dtype,
      Placeholder.Options... options) {
    return Placeholder.create(scope, dtype, options);
  }

  /**
   * A placeholder op that passes through {@code input} when its output is not fed.
   *
   * @param <T> data type for {@code output} output
   * @param input The default value to produce when {@code output} is not fed.
   * @param shape The (possibly partial) shape of the tensor.
   * @param <T> data type for {@code PlaceholderWithDefault} output and operands
   * @return a new instance of PlaceholderWithDefault
   */
  public <T extends TType> PlaceholderWithDefault<T> placeholderWithDefault(Operand<T> input,
      Shape shape) {
    return PlaceholderWithDefault.create(scope, input, shape);
  }

  /**
   * Prints a string scalar.
   *  Prints a string scalar to the desired output_stream.
   *
   * @param input The string scalar to print.
   * @param options carries optional attribute values
   * @return a new instance of Print
   */
  public Print print(Operand<TString> input, Print.Options... options) {
    return Print.create(scope, input, options);
  }

  /**
   * Computes the product of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param <T> data type for {@code output} output
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Prod} output and operands
   * @return a new instance of Prod
   */
  public <T extends TType> Prod<T> prod(Operand<T> input, Operand<? extends TNumber> axis,
      Prod.Options... options) {
    return Prod.create(scope, input, axis, options);
  }

  /**
   * Reshapes a quantized tensor as per the Reshape op.
   *
   * @param <T> data type for {@code output} output
   * @param tensor the tensor value
   * @param shape Defines the shape of the output tensor.
   * @param inputMin The minimum value of the input.
   * @param inputMax The maximum value of the input.
   * @param <T> data type for {@code QuantizedReshape} output and operands
   * @return a new instance of QuantizedReshape
   */
  public <T extends TType> QuantizedReshape<T> quantizedReshape(Operand<T> tensor,
      Operand<? extends TNumber> shape, Operand<TFloat32> inputMin, Operand<TFloat32> inputMax) {
    return QuantizedReshape.create(scope, tensor, shape, inputMin, inputMax);
  }

  /**
   * Creates a sequence of numbers.
   *  This operation creates a sequence of numbers that begins at {@code start} and
   *  extends by increments of {@code delta} up to but not including {@code limit}.
   *  <p>For example:
   *  <pre>
   *  # 'start' is 3
   *  # 'limit' is 18
   *  # 'delta' is 3
   *  tf.range(start, limit, delta) ==&gt; [3, 6, 9, 12, 15]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param start 0-D (scalar). First entry in the sequence.
   * @param limit 0-D (scalar). Upper limit of sequence, exclusive.
   * @param delta 0-D (scalar). Optional. Default is 1. Number that increments {@code start}.
   * @param <T> data type for {@code Range} output and operands
   * @return a new instance of Range
   */
  public <T extends TNumber> Range<T> range(Operand<T> start, Operand<T> limit, Operand<T> delta) {
    return Range.create(scope, start, limit, delta);
  }

  /**
   * Returns the rank of a tensor.
   *  This operation returns an integer representing the rank of {@code input}.
   *  <p>For example:
   *  <pre>
   *  # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
   *  # shape of tensor 't' is [2, 2, 3]
   *  rank(t) ==&gt; 3
   *  </pre>
   *  <p><strong>Note</strong>: The rank of a tensor is not the same as the rank of a matrix. The rank
   *  of a tensor is the number of indices required to uniquely select each element
   *  of the tensor. Rank is also known as &quot;order&quot;, &quot;degree&quot;, or &quot;ndims.&quot;
   *
   * @param input the input value
   * @return a new instance of Rank
   */
  public Rank rank(Operand<? extends TType> input) {
    return Rank.create(scope, input);
  }

  /**
   * Reads the value of a variable.
   *  The tensor returned by this operation is immutable.
   *  <p>The value returned by this operation is guaranteed to be influenced by all the
   *  writes on which this operation depends directly or indirectly, and to not be
   *  influenced by any of the writes which depend directly or indirectly on this
   *  operation.
   *
   * @param <T> data type for {@code value} output
   * @param resource handle to the resource in which to store the variable.
   * @param dtype the dtype of the value.
   * @param <T> data type for {@code ReadVariableOp} output and operands
   * @return a new instance of ReadVariableOp
   */
  public <T extends TType> ReadVariableOp<T> readVariableOp(Operand<? extends TType> resource,
      Class<T> dtype) {
    return ReadVariableOp.create(scope, resource, dtype);
  }

  /**
   * Computes the &quot;logical and&quot; of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @return a new instance of ReduceAll
   */
  public ReduceAll reduceAll(Operand<TBool> input, Operand<? extends TNumber> axis,
      ReduceAll.Options... options) {
    return ReduceAll.create(scope, input, axis, options);
  }

  /**
   * Computes the &quot;logical or&quot; of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @return a new instance of ReduceAny
   */
  public ReduceAny reduceAny(Operand<TBool> input, Operand<? extends TNumber> axis,
      ReduceAny.Options... options) {
    return ReduceAny.create(scope, input, axis, options);
  }

  /**
   * Computes the maximum of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param <T> data type for {@code output} output
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Max} output and operands
   * @return a new instance of ReduceMax
   */
  public <T extends TNumber> ReduceMax<T> reduceMax(Operand<T> input,
      Operand<? extends TNumber> axis, ReduceMax.Options... options) {
    return ReduceMax.create(scope, input, axis, options);
  }

  /**
   * Computes the minimum of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param <T> data type for {@code output} output
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Min} output and operands
   * @return a new instance of ReduceMin
   */
  public <T extends TNumber> ReduceMin<T> reduceMin(Operand<T> input,
      Operand<? extends TNumber> axis, ReduceMin.Options... options) {
    return ReduceMin.create(scope, input, axis, options);
  }

  /**
   * Computes the product of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param <T> data type for {@code output} output
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Prod} output and operands
   * @return a new instance of ReduceProd
   */
  public <T extends TType> ReduceProd<T> reduceProd(Operand<T> input,
      Operand<? extends TNumber> axis, ReduceProd.Options... options) {
    return ReduceProd.create(scope, input, axis, options);
  }

  /**
   * Computes the sum of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param <T> data type for {@code output} output
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Sum} output and operands
   * @return a new instance of ReduceSum
   */
  public <T extends TType> ReduceSum<T> reduceSum(Operand<T> input, Operand<? extends TNumber> axis,
      ReduceSum.Options... options) {
    return ReduceSum.create(scope, input, axis, options);
  }

  /**
   * Makes its input available to the next iteration.
   *
   * @param <T> data type for {@code output} output
   * @param data The tensor to be made available to the next iteration.
   * @param <T> data type for {@code RefNextIteration} output and operands
   * @return a new instance of RefNextIteration
   */
  public <T extends TType> RefNextIteration<T> refNextIteration(Operand<T> data) {
    return RefNextIteration.create(scope, data);
  }

  /**
   * Forwards the {@code index}th element of {@code inputs} to {@code output}.
   *
   * @param <T> data type for {@code output} output
   * @param index A scalar that determines the input that gets selected.
   * @param inputs A list of ref tensors, one of which will be forwarded to {@code output}.
   * @param <T> data type for {@code RefSelect} output and operands
   * @return a new instance of RefSelect
   */
  public <T extends TType> RefSelect<T> refSelect(Operand<TInt32> index,
      Iterable<Operand<T>> inputs) {
    return RefSelect.create(scope, index, inputs);
  }

  /**
   * Forwards the ref tensor {@code data} to the output port determined by {@code pred}.
   *  If {@code pred} is true, the {@code data} input is forwarded to {@code output_true}. Otherwise,
   *  the data goes to {@code output_false}.
   *  <p>See also {@code Switch} and {@code Merge}.
   *
   * @param <T> data type for {@code output_false} output
   * @param data The ref tensor to be forwarded to the appropriate output.
   * @param pred A scalar that specifies which output port will receive data.
   * @param <T> data type for {@code RefSwitch} output and operands
   * @return a new instance of RefSwitch
   */
  public <T extends TType> RefSwitch<T> refSwitch(Operand<T> data, Operand<TBool> pred) {
    return RefSwitch.create(scope, data, pred);
  }

  /**
   * Runs function {@code f} on a remote device indicated by {@code target}.
   *
   * @param target A fully specified device name where we want to run the function.
   * @param args A list of arguments for the function.
   * @param Tout The type list for the return values.
   * @param f The function to run remotely.
   * @return a new instance of RemoteCall
   */
  public RemoteCall remoteCall(Operand<TString> target, Iterable<Operand<?>> args,
      List<Class<? extends TType>> Tout, ConcreteFunction f) {
    return RemoteCall.create(scope, target, args, Tout, f);
  }

  /**
   * Reshapes a tensor.
   *  Given {@code tensor}, this operation returns a tensor that has the same values
   *  as {@code tensor} with shape {@code shape}.
   *  <p>If one component of 1-D tensor {@code shape} is the special value -1, the size of that
   *  dimension is computed so that the total size remains constant.  In particular, a
   *  {@code shape} of {@code [-1]} flattens into 1-D.  At most one component of {@code shape} may be
   *  unknown.
   *  <p>The {@code shape} must be 1-D and the operation returns a tensor with shape
   *  {@code shape} filled with the values of {@code tensor}. In this case, the number of elements
   *  implied by {@code shape} must be the same as the number of elements in {@code tensor}.
   *  <p>It is an error if {@code shape} is not 1-D.
   *  <p>For example:
   *  <pre>
   *  # tensor 't' is [1, 2, 3, 4, 5, 6, 7, 8, 9]
   *  # tensor 't' has shape [9]
   *  reshape(t, [3, 3]) ==&gt; [[1, 2, 3],
   *                          [4, 5, 6],
   *                          [7, 8, 9]]
   *
   *  # tensor 't' is [[[1, 1], [2, 2]],
   *  #                [[3, 3], [4, 4]]]
   *  # tensor 't' has shape [2, 2, 2]
   *  reshape(t, [2, 4]) ==&gt; [[1, 1, 2, 2],
   *                          [3, 3, 4, 4]]
   *
   *  # tensor 't' is [[[1, 1, 1],
   *  #                 [2, 2, 2]],
   *  #                [[3, 3, 3],
   *  #                 [4, 4, 4]],
   *  #                [[5, 5, 5],
   *  #                 [6, 6, 6]]]
   *  # tensor 't' has shape [3, 2, 3]
   *  # pass '[-1]' to flatten 't'
   *  reshape(t, [-1]) ==&gt; [1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6]
   *
   *  # -1 can also be used to infer the shape
   *
   *  # -1 is inferred to be 9:
   *  reshape(t, [2, -1]) ==&gt; [[1, 1, 1, 2, 2, 2, 3, 3, 3],
   *                           [4, 4, 4, 5, 5, 5, 6, 6, 6]]
   *  # -1 is inferred to be 2:
   *  reshape(t, [-1, 9]) ==&gt; [[1, 1, 1, 2, 2, 2, 3, 3, 3],
   *                           [4, 4, 4, 5, 5, 5, 6, 6, 6]]
   *  # -1 is inferred to be 3:
   *  reshape(t, [ 2, -1, 3]) ==&gt; [[[1, 1, 1],
   *                                [2, 2, 2],
   *                                [3, 3, 3]],
   *                               [[4, 4, 4],
   *                                [5, 5, 5],
   *                                [6, 6, 6]]]
   *
   *  # tensor 't' is [7]
   *  # shape `[]` reshapes to a scalar
   *  reshape(t, []) ==&gt; 7
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param tensor the tensor value
   * @param shape Defines the shape of the output tensor.
   * @param <T> data type for {@code Reshape} output and operands
   * @return a new instance of Reshape
   */
  public <T extends TType> Reshape<T> reshape(Operand<T> tensor, Operand<? extends TNumber> shape) {
    return Reshape.create(scope, tensor, shape);
  }

  /**
   * Increments variable pointed to by 'resource' until it reaches 'limit'.
   *
   * @param <T> data type for {@code output} output
   * @param resource Should be from a scalar {@code Variable} node.
   * @param limit If incrementing ref would bring it above limit, instead generates an
   *  'OutOfRange' error.
   * @param T the value of the T property
   * @param <T> data type for {@code ResourceCountUpTo} output and operands
   * @return a new instance of ResourceCountUpTo
   */
  public <T extends TNumber> ResourceCountUpTo<T> resourceCountUpTo(
      Operand<? extends TType> resource, Long limit, Class<T> T) {
    return ResourceCountUpTo.create(scope, resource, limit, T);
  }

  /**
   * Gather slices from the variable pointed to by {@code resource} according to {@code indices}.
   *  {@code indices} must be an integer tensor of any dimension (usually 0-D or 1-D).
   *  Produces an output tensor with shape {@code indices.shape + params.shape[1:]} where:
   *  <pre>
   *      # Scalar indices
   *      output[:, ..., :] = params[indices, :, ... :]
   *
   *      # Vector indices
   *      output[i, :, ..., :] = params[indices[i], :, ... :]
   *
   *      # Higher rank indices
   *      output[i, ..., j, :, ... :] = params[indices[i, ..., j], :, ..., :]
   *  </pre>
   *
   * @param <U> data type for {@code output} output
   * @param resource the resource value
   * @param indices the indices value
   * @param dtype the value of the dtype property
   * @param options carries optional attribute values
   * @param <U> data type for {@code ResourceGather} output and operands
   * @return a new instance of ResourceGather
   */
  public <U extends TType> ResourceGather<U> resourceGather(Operand<? extends TType> resource,
      Operand<? extends TNumber> indices, Class<U> dtype, ResourceGather.Options... options) {
    return ResourceGather.create(scope, resource, indices, dtype, options);
  }

  /**
   * The ResourceGatherNd operation
   *
   * @param <U> data type for {@code output} output
   * @param resource the resource value
   * @param indices the indices value
   * @param dtype the value of the dtype property
   * @param <U> data type for {@code ResourceGatherNd} output and operands
   * @return a new instance of ResourceGatherNd
   */
  public <U extends TType> ResourceGatherNd<U> resourceGatherNd(Operand<? extends TType> resource,
      Operand<? extends TNumber> indices, Class<U> dtype) {
    return ResourceGatherNd.create(scope, resource, indices, dtype);
  }

  /**
   * Adds sparse updates to the variable referenced by {@code resource}.
   *  This operation computes
   *  <pre>
   *  # Scalar indices
   *  ref[indices, ...] += updates[...]
   *
   *  # Vector indices (for each i)
   *  ref[indices[i], ...] += updates[i, ...]
   *
   *  # High rank indices (for each i, ..., j)
   *  ref[indices[i, ..., j], ...] += updates[i, ..., j, ...]
   *  </pre>
   *  <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
   *  the same location, their contributions add.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
   *  </div>
   *
   * @param resource Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to add to {@code ref}.
   * @return a new instance of ResourceScatterAdd
   */
  public ResourceScatterAdd resourceScatterAdd(Operand<? extends TType> resource,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates) {
    return ResourceScatterAdd.create(scope, resource, indices, updates);
  }

  /**
   * Divides sparse updates into the variable referenced by {@code resource}.
   *  This operation computes
   *  <pre>
   *  # Scalar indices
   *  ref[indices, ...] /= updates[...]
   *
   *  # Vector indices (for each i)
   *  ref[indices[i], ...] /= updates[i, ...]
   *
   *  # High rank indices (for each i, ..., j)
   *  ref[indices[i, ..., j], ...] /= updates[i, ..., j, ...]
   *  </pre>
   *  <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
   *  the same location, their contributions multiply.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
   *  </div>
   *
   * @param resource Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to add to {@code ref}.
   * @return a new instance of ResourceScatterDiv
   */
  public ResourceScatterDiv resourceScatterDiv(Operand<? extends TType> resource,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates) {
    return ResourceScatterDiv.create(scope, resource, indices, updates);
  }

  /**
   * Reduces sparse updates into the variable referenced by {@code resource} using the {@code max} operation.
   *  This operation computes
   *  <pre>
   *  # Scalar indices
   *  ref[indices, ...] = max(ref[indices, ...], updates[...])
   *
   *  # Vector indices (for each i)
   *  ref[indices[i], ...] = max(ref[indices[i], ...], updates[i, ...])
   *
   *  # High rank indices (for each i, ..., j)
   *  ref[indices[i, ..., j], ...] = max(ref[indices[i, ..., j], ...], updates[i, ..., j, ...])
   *  </pre>
   *  <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
   *  the same location, their contributions are combined.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
   *  </div>
   *
   * @param resource Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to add to {@code ref}.
   * @return a new instance of ResourceScatterMax
   */
  public ResourceScatterMax resourceScatterMax(Operand<? extends TType> resource,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates) {
    return ResourceScatterMax.create(scope, resource, indices, updates);
  }

  /**
   * Reduces sparse updates into the variable referenced by {@code resource} using the {@code min} operation.
   *  This operation computes
   *  <pre>
   *  # Scalar indices
   *  ref[indices, ...] = min(ref[indices, ...], updates[...])
   *
   *  # Vector indices (for each i)
   *  ref[indices[i], ...] = min(ref[indices[i], ...], updates[i, ...])
   *
   *  # High rank indices (for each i, ..., j)
   *  ref[indices[i, ..., j], ...] = min(ref[indices[i, ..., j], ...], updates[i, ..., j, ...])
   *  </pre>
   *  <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
   *  the same location, their contributions are combined.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
   *  </div>
   *
   * @param resource Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to add to {@code ref}.
   * @return a new instance of ResourceScatterMin
   */
  public ResourceScatterMin resourceScatterMin(Operand<? extends TType> resource,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates) {
    return ResourceScatterMin.create(scope, resource, indices, updates);
  }

  /**
   * Multiplies sparse updates into the variable referenced by {@code resource}.
   *  This operation computes
   *  <pre>
   *  # Scalar indices
   *  ref[indices, ...] *= updates[...]
   *
   *  # Vector indices (for each i)
   *  ref[indices[i], ...] *= updates[i, ...]
   *
   *  # High rank indices (for each i, ..., j)
   *  ref[indices[i, ..., j], ...] *= updates[i, ..., j, ...]
   *  </pre>
   *  <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
   *  the same location, their contributions multiply.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
   *  </div>
   *
   * @param resource Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to add to {@code ref}.
   * @return a new instance of ResourceScatterMul
   */
  public ResourceScatterMul resourceScatterMul(Operand<? extends TType> resource,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates) {
    return ResourceScatterMul.create(scope, resource, indices, updates);
  }

  /**
   * Applies sparse addition to individual values or slices in a Variable.
   *  {@code ref} is a {@code Tensor} with rank {@code P} and {@code indices} is a {@code Tensor} of rank {@code Q}.
   *  <p>{@code indices} must be integer tensor, containing indices into {@code ref}.
   *  It must be shape {@code [d_0, ..., d_{Q-2}, K]} where {@code 0 < K <= P}.
   *  <p>The innermost dimension of {@code indices} (with length {@code K}) corresponds to
   *  indices into elements (if {@code K = P}) or slices (if {@code K < P}) along the {@code K}th
   *  dimension of {@code ref}.
   *  <p>{@code updates} is {@code Tensor} of rank {@code Q-1+P-K} with shape:
   *  <pre>
   *  [d_0, ..., d_{Q-2}, ref.shape[K], ..., ref.shape[P-1]]
   *  </pre>
   *  <p>For example, say we want to add 4 scattered elements to a rank-1 tensor to
   *  8 elements. In Python, that addition would look like this:
   *  <pre>
   *  ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8], use_resource=True)
   *  indices = tf.constant([[4], [3], [1], [7]])
   *  updates = tf.constant([9, 10, 11, 12])
   *  add = tf.scatter_nd_add(ref, indices, updates)
   *  with tf.Session() as sess:
   *    print sess.run(add)
   *  </pre>
   *  <p>The resulting update to ref would look like this:
   *  <pre>
   *  [1, 13, 3, 14, 14, 6, 7, 20]
   *  </pre>
   *  <p>See {@code tf.scatter_nd} for more details about how to make updates to
   *  slices.
   *
   * @param ref A resource handle. Must be from a VarHandleOp.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   *  A tensor of indices into ref.
   * @param updates A Tensor. Must have the same type as ref. A tensor of
   *  values to add to ref.
   * @param options carries optional attribute values
   * @return a new instance of ResourceScatterNdAdd
   */
  public ResourceScatterNdAdd resourceScatterNdAdd(Operand<? extends TType> ref,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates,
      ResourceScatterNdAdd.Options... options) {
    return ResourceScatterNdAdd.create(scope, ref, indices, updates, options);
  }

  /**
   * The ResourceScatterNdMax operation
   *
   * @param ref A resource handle. Must be from a VarHandleOp.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   *  A tensor of indices into ref.
   * @param updates A Tensor. Must have the same type as ref. A tensor of
   *  values whose element wise max is taken with ref
   * @param options carries optional attribute values
   * @return a new instance of ResourceScatterNdMax
   */
  public ResourceScatterNdMax resourceScatterNdMax(Operand<? extends TType> ref,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates,
      ResourceScatterNdMax.Options... options) {
    return ResourceScatterNdMax.create(scope, ref, indices, updates, options);
  }

  /**
   * The ResourceScatterNdMin operation
   *
   * @param ref A resource handle. Must be from a VarHandleOp.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   *  A tensor of indices into ref.
   * @param updates A Tensor. Must have the same type as ref. A tensor of
   *  values whose element wise min is taken with ref.
   * @param options carries optional attribute values
   * @return a new instance of ResourceScatterNdMin
   */
  public ResourceScatterNdMin resourceScatterNdMin(Operand<? extends TType> ref,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates,
      ResourceScatterNdMin.Options... options) {
    return ResourceScatterNdMin.create(scope, ref, indices, updates, options);
  }

  /**
   * Applies sparse subtraction to individual values or slices in a Variable.
   *  {@code ref} is a {@code Tensor} with rank {@code P} and {@code indices} is a {@code Tensor} of rank {@code Q}.
   *  <p>{@code indices} must be integer tensor, containing indices into {@code ref}.
   *  It must be shape {@code [d_0, ..., d_{Q-2}, K]} where {@code 0 < K <= P}.
   *  <p>The innermost dimension of {@code indices} (with length {@code K}) corresponds to
   *  indices into elements (if {@code K = P}) or slices (if {@code K < P}) along the {@code K}th
   *  dimension of {@code ref}.
   *  <p>{@code updates} is {@code Tensor} of rank {@code Q-1+P-K} with shape:
   *  <pre>
   *  [d_0, ..., d_{Q-2}, ref.shape[K], ..., ref.shape[P-1]]
   *  </pre>
   *  <p>For example, say we want to subtract 4 scattered elements from a rank-1 tensor
   *  with 8 elements. In Python, that subtraction would look like this:
   *  <pre>
   *  ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8], use_resource=True)
   *  indices = tf.constant([[4], [3], [1], [7]])
   *  updates = tf.constant([9, 10, 11, 12])
   *  sub = tf.scatter_nd_sub(ref, indices, updates)
   *  with tf.Session() as sess:
   *    print sess.run(sub)
   *  </pre>
   *  <p>The resulting update to ref would look like this:
   *  <pre>
   *  [1, -9, 3, -6, -4, 6, 7, -4]
   *  </pre>
   *  <p>See {@code tf.scatter_nd} for more details about how to make updates to
   *  slices.
   *
   * @param ref A resource handle. Must be from a VarHandleOp.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   *  A tensor of indices into ref.
   * @param updates A Tensor. Must have the same type as ref. A tensor of
   *  values to add to ref.
   * @param options carries optional attribute values
   * @return a new instance of ResourceScatterNdSub
   */
  public ResourceScatterNdSub resourceScatterNdSub(Operand<? extends TType> ref,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates,
      ResourceScatterNdSub.Options... options) {
    return ResourceScatterNdSub.create(scope, ref, indices, updates, options);
  }

  /**
   * Applies sparse {@code updates} to individual values or slices within a given
   *  variable according to {@code indices}.
   *  <p>{@code ref} is a {@code Tensor} with rank {@code P} and {@code indices} is a {@code Tensor} of rank {@code Q}.
   *  <p>{@code indices} must be integer tensor, containing indices into {@code ref}.
   *  It must be shape {@code [d_0, ..., d_{Q-2}, K]} where {@code 0 < K <= P}.
   *  <p>The innermost dimension of {@code indices} (with length {@code K}) corresponds to
   *  indices into elements (if {@code K = P}) or slices (if {@code K < P}) along the {@code K}th
   *  dimension of {@code ref}.
   *  <p>{@code updates} is {@code Tensor} of rank {@code Q-1+P-K} with shape:
   *  <pre>
   *  [d_0, ..., d_{Q-2}, ref.shape[K], ..., ref.shape[P-1]].
   *  </pre>
   *  <p>For example, say we want to update 4 scattered elements to a rank-1 tensor to
   *  8 elements. In Python, that update would look like this:
   *  <pre>
   *      ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8])
   *      indices = tf.constant([[4], [3], [1] ,[7]])
   *      updates = tf.constant([9, 10, 11, 12])
   *      update = tf.scatter_nd_update(ref, indices, updates)
   *      with tf.Session() as sess:
   *        print sess.run(update)
   *  </pre>
   *  <p>The resulting update to ref would look like this:
   *  <pre>
   *  [1, 11, 3, 10, 9, 6, 7, 12]
   *  </pre>
   *  <p>See {@code tf.scatter_nd} for more details about how to make updates to
   *  slices.
   *
   * @param ref A resource handle. Must be from a VarHandleOp.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   *  A tensor of indices into ref.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated
   *  values to add to ref.
   * @param options carries optional attribute values
   * @return a new instance of ResourceScatterNdUpdate
   */
  public ResourceScatterNdUpdate resourceScatterNdUpdate(Operand<? extends TType> ref,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates,
      ResourceScatterNdUpdate.Options... options) {
    return ResourceScatterNdUpdate.create(scope, ref, indices, updates, options);
  }

  /**
   * Subtracts sparse updates from the variable referenced by {@code resource}.
   *  This operation computes
   *  <pre>
   *  # Scalar indices
   *  ref[indices, ...] -= updates[...]
   *
   *  # Vector indices (for each i)
   *  ref[indices[i], ...] -= updates[i, ...]
   *
   *  # High rank indices (for each i, ..., j)
   *  ref[indices[i, ..., j], ...] -= updates[i, ..., j, ...]
   *  </pre>
   *  <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
   *  the same location, their contributions add.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
   *  </div>
   *
   * @param resource Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to add to {@code ref}.
   * @return a new instance of ResourceScatterSub
   */
  public ResourceScatterSub resourceScatterSub(Operand<? extends TType> resource,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates) {
    return ResourceScatterSub.create(scope, resource, indices, updates);
  }

  /**
   * Assigns sparse updates to the variable referenced by {@code resource}.
   *  This operation computes
   *  <pre>
   *  # Scalar indices
   *  ref[indices, ...] = updates[...]
   *
   *  # Vector indices (for each i)
   *  ref[indices[i], ...] = updates[i, ...]
   *
   *  # High rank indices (for each i, ..., j)
   *  ref[indices[i, ..., j], ...] = updates[i, ..., j, ...]
   *  </pre>
   *
   * @param resource Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to add to {@code ref}.
   * @return a new instance of ResourceScatterUpdate
   */
  public ResourceScatterUpdate resourceScatterUpdate(Operand<? extends TType> resource,
      Operand<? extends TNumber> indices, Operand<? extends TType> updates) {
    return ResourceScatterUpdate.create(scope, resource, indices, updates);
  }

  /**
   * Assign {@code value} to the sliced l-value reference of {@code ref}.
   *  The values of {@code value} are assigned to the positions in the variable
   *  {@code ref} that are selected by the slice parameters. The slice parameters
   *  {@code begin, }end{@code , }strides{@code , etc. work exactly as in }StridedSlice`.
   *  <p>NOTE this op currently does not support broadcasting and so {@code value}'s
   *  shape must be exactly the shape produced by the slice of {@code ref}.
   *
   * @param ref the ref value
   * @param begin the begin value
   * @param end the end value
   * @param strides the strides value
   * @param value the value value
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceStridedSliceAssign} output and operands
   * @return a new instance of ResourceStridedSliceAssign
   */
  public <T extends TNumber> ResourceStridedSliceAssign resourceStridedSliceAssign(
      Operand<? extends TType> ref, Operand<T> begin, Operand<T> end, Operand<T> strides,
      Operand<? extends TType> value, ResourceStridedSliceAssign.Options... options) {
    return ResourceStridedSliceAssign.create(scope, ref, begin, end, strides, value, options);
  }

  /**
   * Reverses specific dimensions of a tensor.
   *  NOTE {@code tf.reverse} has now changed behavior in preparation for 1.0.
   *  {@code tf.reverse_v2} is currently an alias that will be deprecated before TF 1.0.
   *  <p>Given a {@code tensor}, and a {@code int32} tensor {@code axis} representing the set of
   *  dimensions of {@code tensor} to reverse. This operation reverses each dimension
   *  {@code i} for which there exists {@code j} s.t. {@code axis[j] == i}.
   *  <p>{@code tensor} can have up to 8 dimensions. The number of dimensions specified
   *  in {@code axis} may be 0 or more entries. If an index is specified more than
   *  once, a InvalidArgument error is raised.
   *  <p>For example:
   *  <pre>
   *  # tensor 't' is [[[[ 0,  1,  2,  3],
   *  #                  [ 4,  5,  6,  7],
   *  #                  [ 8,  9, 10, 11]],
   *  #                 [[12, 13, 14, 15],
   *  #                  [16, 17, 18, 19],
   *  #                  [20, 21, 22, 23]]]]
   *  # tensor 't' shape is [1, 2, 3, 4]
   *
   *  # 'dims' is [3] or 'dims' is [-1]
   *  reverse(t, dims) ==&gt; [[[[ 3,  2,  1,  0],
   *                          [ 7,  6,  5,  4],
   *                          [ 11, 10, 9, 8]],
   *                         [[15, 14, 13, 12],
   *                          [19, 18, 17, 16],
   *                          [23, 22, 21, 20]]]]
   *
   *  # 'dims' is '[1]' (or 'dims' is '[-3]')
   *  reverse(t, dims) ==&gt; [[[[12, 13, 14, 15],
   *                          [16, 17, 18, 19],
   *                          [20, 21, 22, 23]
   *                         [[ 0,  1,  2,  3],
   *                          [ 4,  5,  6,  7],
   *                          [ 8,  9, 10, 11]]]]
   *
   *  # 'dims' is '[2]' (or 'dims' is '[-2]')
   *  reverse(t, dims) ==&gt; [[[[8, 9, 10, 11],
   *                          [4, 5, 6, 7],
   *                          [0, 1, 2, 3]]
   *                         [[20, 21, 22, 23],
   *                          [16, 17, 18, 19],
   *                          [12, 13, 14, 15]]]]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param tensor Up to 8-D.
   * @param axis 1-D. The indices of the dimensions to reverse. Must be in the range
   *  {@code [-rank(tensor), rank(tensor))}.
   * @param <T> data type for {@code ReverseV2} output and operands
   * @return a new instance of Reverse
   */
  public <T extends TType> Reverse<T> reverse(Operand<T> tensor, Operand<? extends TNumber> axis) {
    return Reverse.create(scope, tensor, axis);
  }

  /**
   * Reverses variable length slices.
   *  This op first slices {@code input} along the dimension {@code batch_dim}, and for each
   *  slice {@code i}, reverses the first {@code seq_lengths[i]} elements along
   *  the dimension {@code seq_dim}.
   *  <p>The elements of {@code seq_lengths} must obey {@code seq_lengths[i] <= input.dims[seq_dim]},
   *  and {@code seq_lengths} must be a vector of length {@code input.dims[batch_dim]}.
   *  <p>The output slice {@code i} along dimension {@code batch_dim} is then given by input
   *  slice {@code i}, with the first {@code seq_lengths[i]} slices along dimension
   *  {@code seq_dim} reversed.
   *  <p>For example:
   *  <pre>
   *  # Given this:
   *  batch_dim = 0
   *  seq_dim = 1
   *  input.dims = (4, 8, ...)
   *  seq_lengths = [7, 2, 3, 5]
   *
   *  # then slices of input are reversed on seq_dim, but only up to seq_lengths:
   *  output[0, 0:7, :, ...] = input[0, 7:0:-1, :, ...]
   *  output[1, 0:2, :, ...] = input[1, 2:0:-1, :, ...]
   *  output[2, 0:3, :, ...] = input[2, 3:0:-1, :, ...]
   *  output[3, 0:5, :, ...] = input[3, 5:0:-1, :, ...]
   *
   *  # while entries past seq_lens are copied through:
   *  output[0, 7:, :, ...] = input[0, 7:, :, ...]
   *  output[1, 2:, :, ...] = input[1, 2:, :, ...]
   *  output[2, 3:, :, ...] = input[2, 3:, :, ...]
   *  output[3, 2:, :, ...] = input[3, 2:, :, ...]
   *  </pre>
   *  <p>In contrast, if:
   *  <pre>
   *  # Given this:
   *  batch_dim = 2
   *  seq_dim = 0
   *  input.dims = (8, ?, 4, ...)
   *  seq_lengths = [7, 2, 3, 5]
   *
   *  # then slices of input are reversed on seq_dim, but only up to seq_lengths:
   *  output[0:7, :, 0, :, ...] = input[7:0:-1, :, 0, :, ...]
   *  output[0:2, :, 1, :, ...] = input[2:0:-1, :, 1, :, ...]
   *  output[0:3, :, 2, :, ...] = input[3:0:-1, :, 2, :, ...]
   *  output[0:5, :, 3, :, ...] = input[5:0:-1, :, 3, :, ...]
   *
   *  # while entries past seq_lens are copied through:
   *  output[7:, :, 0, :, ...] = input[7:, :, 0, :, ...]
   *  output[2:, :, 1, :, ...] = input[2:, :, 1, :, ...]
   *  output[3:, :, 2, :, ...] = input[3:, :, 2, :, ...]
   *  output[2:, :, 3, :, ...] = input[2:, :, 3, :, ...]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param input The input to reverse.
   * @param seqLengths 1-D with length {@code input.dims(batch_dim)} and
   *  {@code max(seq_lengths) <= input.dims(seq_dim)}
   * @param seqDim The dimension which is partially reversed.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ReverseSequence} output and operands
   * @return a new instance of ReverseSequence
   */
  public <T extends TType> ReverseSequence<T> reverseSequence(Operand<T> input,
      Operand<? extends TNumber> seqLengths, Long seqDim, ReverseSequence.Options... options) {
    return ReverseSequence.create(scope, input, seqLengths, seqDim, options);
  }

  /**
   * Rolls the elements of a tensor along an axis.
   *  The elements are shifted positively (towards larger indices) by the offset of
   *  {@code shift} along the dimension of {@code axis}. Negative {@code shift} values will shift
   *  elements in the opposite direction. Elements that roll passed the last position
   *  will wrap around to the first and vice versa. Multiple shifts along multiple
   *  axes may be specified.
   *  <p>For example:
   *  <pre>
   *  # 't' is [0, 1, 2, 3, 4]
   *  roll(t, shift=2, axis=0) ==&gt; [3, 4, 0, 1, 2]
   *
   *  # shifting along multiple dimensions
   *  # 't' is [[0, 1, 2, 3, 4], [5, 6, 7, 8, 9]]
   *  roll(t, shift=[1, -2], axis=[0, 1]) ==&gt; [[7, 8, 9, 5, 6], [2, 3, 4, 0, 1]]
   *
   *  # shifting along the same axis multiple times
   *  # 't' is [[0, 1, 2, 3, 4], [5, 6, 7, 8, 9]]
   *  roll(t, shift=[2, -3], axis=[1, 1]) ==&gt; [[1, 2, 3, 4, 0], [6, 7, 8, 9, 5]]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param shift Dimension must be 0-D or 1-D. {@code shift[i]} specifies the number of places by which
   *  elements are shifted positively (towards larger indices) along the dimension
   *  specified by {@code axis[i]}. Negative shifts will roll the elements in the opposite
   *  direction.
   * @param axis Dimension must be 0-D or 1-D. {@code axis[i]} specifies the dimension that the shift
   *  {@code shift[i]} should occur. If the same axis is referenced more than once, the
   *  total shift for that axis will be the sum of all the shifts that belong to that
   *  axis.
   * @param <T> data type for {@code Roll} output and operands
   * @return a new instance of Roll
   */
  public <T extends TType> Roll<T> roll(Operand<T> input, Operand<? extends TNumber> shift,
      Operand<? extends TNumber> axis) {
    return Roll.create(scope, input, shift, axis);
  }

  /**
   * Adds sparse updates to a variable reference.
   *  This operation computes
   *  <pre>
   *  # Scalar indices
   *  ref[indices, ...] += updates[...]
   *
   *  # Vector indices (for each i)
   *  ref[indices[i], ...] += updates[i, ...]
   *
   *  # High rank indices (for each i, ..., j)
   *  ref[indices[i, ..., j], ...] += updates[i, ..., j, ...]
   *  </pre>
   *  <p>This operation outputs {@code ref} after the update is done.
   *  This makes it easier to chain operations that need to use the reset value.
   *  <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
   *  the same location, their contributions add.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterAdd.png" alt>
   *  </div>
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to add to {@code ref}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ScatterAdd} output and operands
   * @return a new instance of ScatterAdd
   */
  public <T extends TType> ScatterAdd<T> scatterAdd(Operand<T> ref,
      Operand<? extends TNumber> indices, Operand<T> updates, ScatterAdd.Options... options) {
    return ScatterAdd.create(scope, ref, indices, updates, options);
  }

  /**
   * Divides a variable reference by sparse updates.
   *  This operation computes
   *  <pre>
   *      # Scalar indices
   *      ref[indices, ...] /= updates[...]
   *
   *      # Vector indices (for each i)
   *      ref[indices[i], ...] /= updates[i, ...]
   *
   *      # High rank indices (for each i, ..., j)
   *      ref[indices[i, ..., j], ...] /= updates[i, ..., j, ...]
   *  </pre>
   *  <p>This operation outputs {@code ref} after the update is done.
   *  This makes it easier to chain operations that need to use the reset value.
   *  <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
   *  the same location, their contributions divide.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of values that {@code ref} is divided by.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ScatterDiv} output and operands
   * @return a new instance of ScatterDiv
   */
  public <T extends TType> ScatterDiv<T> scatterDiv(Operand<T> ref,
      Operand<? extends TNumber> indices, Operand<T> updates, ScatterDiv.Options... options) {
    return ScatterDiv.create(scope, ref, indices, updates, options);
  }

  /**
   * Reduces sparse updates into a variable reference using the {@code max} operation.
   *  This operation computes
   *  <pre>
   *  # Scalar indices
   *  ref[indices, ...] = max(ref[indices, ...], updates[...])
   *
   *  # Vector indices (for each i)
   *  ref[indices[i], ...] = max(ref[indices[i], ...], updates[i, ...])
   *
   *  # High rank indices (for each i, ..., j)
   *  ref[indices[i, ..., j], ...] = max(ref[indices[i, ..., j], ...], updates[i, ..., j, ...])
   *  </pre>
   *  <p>This operation outputs {@code ref} after the update is done.
   *  This makes it easier to chain operations that need to use the reset value.
   *  <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
   *  the same location, their contributions combine.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterAdd.png" alt>
   *  </div>
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to reduce into {@code ref}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ScatterMax} output and operands
   * @return a new instance of ScatterMax
   */
  public <T extends TNumber> ScatterMax<T> scatterMax(Operand<T> ref,
      Operand<? extends TNumber> indices, Operand<T> updates, ScatterMax.Options... options) {
    return ScatterMax.create(scope, ref, indices, updates, options);
  }

  /**
   * Reduces sparse updates into a variable reference using the {@code min} operation.
   *  This operation computes
   *  <pre>
   *  # Scalar indices
   *  ref[indices, ...] = min(ref[indices, ...], updates[...])
   *
   *  # Vector indices (for each i)
   *  ref[indices[i], ...] = min(ref[indices[i], ...], updates[i, ...])
   *
   *  # High rank indices (for each i, ..., j)
   *  ref[indices[i, ..., j], ...] = min(ref[indices[i, ..., j], ...], updates[i, ..., j, ...])
   *  </pre>
   *  <p>This operation outputs {@code ref} after the update is done.
   *  This makes it easier to chain operations that need to use the reset value.
   *  <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
   *  the same location, their contributions combine.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterAdd.png" alt>
   *  </div>
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to reduce into {@code ref}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ScatterMin} output and operands
   * @return a new instance of ScatterMin
   */
  public <T extends TNumber> ScatterMin<T> scatterMin(Operand<T> ref,
      Operand<? extends TNumber> indices, Operand<T> updates, ScatterMin.Options... options) {
    return ScatterMin.create(scope, ref, indices, updates, options);
  }

  /**
   * Multiplies sparse updates into a variable reference.
   *  This operation computes
   *  <pre>
   *      # Scalar indices
   *      ref[indices, ...] *= updates[...]
   *
   *      # Vector indices (for each i)
   *      ref[indices[i], ...] *= updates[i, ...]
   *
   *      # High rank indices (for each i, ..., j)
   *      ref[indices[i, ..., j], ...] *= updates[i, ..., j, ...]
   *  </pre>
   *  <p>This operation outputs {@code ref} after the update is done.
   *  This makes it easier to chain operations that need to use the reset value.
   *  <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
   *  the same location, their contributions multiply.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to multiply to {@code ref}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ScatterMul} output and operands
   * @return a new instance of ScatterMul
   */
  public <T extends TType> ScatterMul<T> scatterMul(Operand<T> ref,
      Operand<? extends TNumber> indices, Operand<T> updates, ScatterMul.Options... options) {
    return ScatterMul.create(scope, ref, indices, updates, options);
  }

  /**
   * Scatter {@code updates} into a new tensor according to {@code indices}.
   *  Creates a new tensor by applying sparse {@code updates} to individual values or
   *  slices within a tensor (initially zero for numeric, empty for string) of
   *  the given {@code shape} according to indices.  This operator is the inverse of the
   *  {@code tf.gather_nd} operator which extracts values or slices from a given tensor.
   *  <p>This operation is similar to tensor_scatter_add, except that the tensor is
   *  zero-initialized. Calling {@code tf.scatter_nd(indices, values, shape)} is identical
   *  to {@code tensor_scatter_add(tf.zeros(shape, values.dtype), indices, values)}
   *  <p>If {@code indices} contains duplicates, then their updates are accumulated (summed).
   *  <p><strong>WARNING</strong>: The order in which updates are applied is nondeterministic, so the
   *  output will be nondeterministic if {@code indices} contains duplicates -- because
   *  of some numerical approximation issues, numbers summed in different order
   *  may yield different results.
   *  <p>{@code indices} is an integer tensor containing indices into a new tensor of shape
   *  {@code shape}.  The last dimension of {@code indices} can be at most the rank of {@code shape}:
   *  <pre>
   *  indices.shape[-1] &lt;= shape.rank
   *  </pre>
   *  <p>The last dimension of {@code indices} corresponds to indices into elements
   *  (if {@code indices.shape[-1] = shape.rank}) or slices
   *  (if {@code indices.shape[-1] < shape.rank}) along dimension {@code indices.shape[-1]} of
   *  {@code shape}.  {@code updates} is a tensor with shape
   *  <pre>
   *  indices.shape[:-1] + shape[indices.shape[-1]:]
   *  </pre>
   *  <p>The simplest form of scatter is to insert individual elements in a tensor by
   *  index. For example, say we want to insert 4 scattered elements in a rank-1
   *  tensor with 8 elements.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterNd1.png" alt>
   *  </div>
   *  <p>In Python, this scatter operation would look like this:
   *  <pre>
   *      indices = tf.constant([[4], [3], [1], [7]])
   *      updates = tf.constant([9, 10, 11, 12])
   *      shape = tf.constant([8])
   *      scatter = tf.scatter_nd(indices, updates, shape)
   *      print(scatter)
   *  </pre>
   *  <p>The resulting tensor would look like this:
   *  <pre>
   *  [0, 11, 0, 10, 9, 0, 0, 12]
   *  </pre>
   *  <p>We can also, insert entire slices of a higher rank tensor all at once. For
   *  example, if we wanted to insert two slices in the first dimension of a
   *  rank-3 tensor with two matrices of new values.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterNd2.png" alt>
   *  </div>
   *  <p>In Python, this scatter operation would look like this:
   *  <pre>
   *      indices = tf.constant([[0], [2]])
   *      updates = tf.constant([[[5, 5, 5, 5], [6, 6, 6, 6],
   *                              [7, 7, 7, 7], [8, 8, 8, 8]],
   *                             [[5, 5, 5, 5], [6, 6, 6, 6],
   *                              [7, 7, 7, 7], [8, 8, 8, 8]]])
   *      shape = tf.constant([4, 4, 4])
   *      scatter = tf.scatter_nd(indices, updates, shape)
   *      print(scatter)
   *  </pre>
   *  <p>The resulting tensor would look like this:
   *  <pre>
   *  [[[5, 5, 5, 5], [6, 6, 6, 6], [7, 7, 7, 7], [8, 8, 8, 8]],
   *   [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]],
   *   [[5, 5, 5, 5], [6, 6, 6, 6], [7, 7, 7, 7], [8, 8, 8, 8]],
   *   [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]]
   *  </pre>
   *  <p>Note that on CPU, if an out of bound index is found, an error is returned.
   *  On GPU, if an out of bound index is found, the index is ignored.
   *
   * @param <U> data type for {@code output} output
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @param shape 1-D. The shape of the resulting tensor.
   * @param <U> data type for {@code ScatterNd} output and operands
   * @param <T> data type for {@code ScatterNd} output and operands
   * @return a new instance of ScatterNd
   */
  public <U extends TType, T extends TNumber> ScatterNd<U> scatterNd(Operand<T> indices,
      Operand<U> updates, Operand<T> shape) {
    return ScatterNd.create(scope, indices, updates, shape);
  }

  /**
   * Applies sparse addition to individual values or slices in a Variable.
   *  {@code ref} is a {@code Tensor} with rank {@code P} and {@code indices} is a {@code Tensor} of rank {@code Q}.
   *  <p>{@code indices} must be integer tensor, containing indices into {@code ref}.
   *  It must be shape {@code [d_0, ..., d_{Q-2}, K]} where {@code 0 < K <= P}.
   *  <p>The innermost dimension of {@code indices} (with length {@code K}) corresponds to
   *  indices into elements (if {@code K = P}) or slices (if {@code K < P}) along the {@code K}th
   *  dimension of {@code ref}.
   *  <p>{@code updates} is {@code Tensor} of rank {@code Q-1+P-K} with shape:
   *  <pre>
   *  [d_0, ..., d_{Q-2}, ref.shape[K], ..., ref.shape[P-1]]
   *  </pre>
   *  <p>For example, say we want to add 4 scattered elements to a rank-1 tensor to
   *  8 elements. In Python, that addition would look like this:
   *  <pre>
   *  ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8])
   *  indices = tf.constant([[4], [3], [1], [7]])
   *  updates = tf.constant([9, 10, 11, 12])
   *  add = tf.scatter_nd_add(ref, indices, updates)
   *  with tf.Session() as sess:
   *    print sess.run(add)
   *  </pre>
   *  <p>The resulting update to ref would look like this:
   *  <pre>
   *  [1, 13, 3, 14, 14, 6, 7, 20]
   *  </pre>
   *  <p>See {@code tf.scatter_nd} for more details about how to make updates to
   *  slices.
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref A mutable Tensor. Should be from a Variable node.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   *  A tensor of indices into ref.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated values
   *  to add to ref.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ScatterNdAdd} output and operands
   * @return a new instance of ScatterNdAdd
   */
  public <T extends TType> ScatterNdAdd<T> scatterNdAdd(Operand<T> ref,
      Operand<? extends TNumber> indices, Operand<T> updates, ScatterNdAdd.Options... options) {
    return ScatterNdAdd.create(scope, ref, indices, updates, options);
  }

  /**
   * Applies sparse addition to {@code input} using individual values or slices
   *  from {@code updates} according to indices {@code indices}.  The updates are non-aliasing:
   *  {@code input} is only modified in-place if no other operations will use it.
   *  Otherwise, a copy of {@code input} is made.  This operation has a gradient with
   *  respect to both {@code input} and {@code updates}.
   *  <p>{@code input} is a {@code Tensor} with rank {@code P} and {@code indices} is a {@code Tensor} of rank {@code Q}.
   *  <p>{@code indices} must be integer tensor, containing indices into {@code input}.
   *  It must be shape \([d_0, ..., d_{Q-2}, K]\) where {@code 0 < K <= P}.
   *  <p>The innermost dimension of {@code indices} (with length {@code K}) corresponds to
   *  indices into elements (if {@code K = P}) or {@code (P-K)}-dimensional slices
   *  (if {@code K < P}) along the {@code K}th dimension of {@code input}.
   *  <p>{@code updates} is {@code Tensor} of rank {@code Q-1+P-K} with shape:
   *  <p>$$[d_0, ..., d_{Q-2}, input.shape[K], ..., input.shape[P-1]].$$
   *  <p>For example, say we want to add 4 scattered elements to a rank-1 tensor to 8
   *  elements. In Python, that addition would look like this:
   *  <pre>
   *  input = tf.constant([1, 2, 3, 4, 5, 6, 7, 8])
   *  indices = tf.constant([[4], [3], [1], [7]])
   *  updates = tf.constant([9, 10, 11, 12])
   *  output = tf.scatter_nd_non_aliasing_add(input, indices, updates)
   *  with tf.Session() as sess:
   *    print(sess.run(output))
   *  </pre>
   *  <p>The resulting value {@code output} would look like this:
   *  <pre>
   *  [1, 13, 3, 14, 14, 6, 7, 20]
   *  </pre>
   *  <p>See {@code tf.scatter_nd} for more details about how to make updates to slices.
   *
   * @param <T> data type for {@code output} output
   * @param input A Tensor.
   * @param indices A Tensor. Must be one of the following types: {@code int32}, {@code int64}.
   *  A tensor of indices into {@code input}.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated values
   *  to add to {@code input}.
   * @param <T> data type for {@code ScatterNdNonAliasingAdd} output and operands
   * @return a new instance of ScatterNdNonAliasingAdd
   */
  public <T extends TType> ScatterNdNonAliasingAdd<T> scatterNdNonAliasingAdd(Operand<T> input,
      Operand<? extends TNumber> indices, Operand<T> updates) {
    return ScatterNdNonAliasingAdd.create(scope, input, indices, updates);
  }

  /**
   * Applies sparse subtraction to individual values or slices in a Variable.
   *  within a given variable according to {@code indices}.
   *  <p>{@code ref} is a {@code Tensor} with rank {@code P} and {@code indices} is a {@code Tensor} of rank {@code Q}.
   *  <p>{@code indices} must be integer tensor, containing indices into {@code ref}.
   *  It must be shape {@code [d_0, ..., d_{Q-2}, K]} where {@code 0 < K <= P}.
   *  <p>The innermost dimension of {@code indices} (with length {@code K}) corresponds to
   *  indices into elements (if {@code K = P}) or slices (if {@code K < P}) along the {@code K}th
   *  dimension of {@code ref}.
   *  <p>{@code updates} is {@code Tensor} of rank {@code Q-1+P-K} with shape:
   *  <pre>
   *  [d_0, ..., d_{Q-2}, ref.shape[K], ..., ref.shape[P-1]]
   *  </pre>
   *  <p>For example, say we want to subtract 4 scattered elements from a rank-1 tensor
   *  with 8 elements. In Python, that subtraction would look like this:
   *  <pre>
   *  ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8])
   *  indices = tf.constant([[4], [3], [1], [7]])
   *  updates = tf.constant([9, 10, 11, 12])
   *  sub = tf.scatter_nd_sub(ref, indices, updates)
   *  with tf.Session() as sess:
   *    print sess.run(sub)
   *  </pre>
   *  <p>The resulting update to ref would look like this:
   *  <pre>
   *  [1, -9, 3, -6, -4, 6, 7, -4]
   *  </pre>
   *  <p>See {@code tf.scatter_nd} for more details about how to make updates to
   *  slices.
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref A mutable Tensor. Should be from a Variable node.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   *  A tensor of indices into ref.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated values
   *  to subtract from ref.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ScatterNdSub} output and operands
   * @return a new instance of ScatterNdSub
   */
  public <T extends TType> ScatterNdSub<T> scatterNdSub(Operand<T> ref,
      Operand<? extends TNumber> indices, Operand<T> updates, ScatterNdSub.Options... options) {
    return ScatterNdSub.create(scope, ref, indices, updates, options);
  }

  /**
   * Applies sparse {@code updates} to individual values or slices within a given
   *  variable according to {@code indices}.
   *  <p>{@code ref} is a {@code Tensor} with rank {@code P} and {@code indices} is a {@code Tensor} of rank {@code Q}.
   *  <p>{@code indices} must be integer tensor, containing indices into {@code ref}.
   *  It must be shape \([d_0, ..., d_{Q-2}, K]\) where {@code 0 < K <= P}.
   *  <p>The innermost dimension of {@code indices} (with length {@code K}) corresponds to
   *  indices into elements (if {@code K = P}) or slices (if {@code K < P}) along the {@code K}th
   *  dimension of {@code ref}.
   *  <p>{@code updates} is {@code Tensor} of rank {@code Q-1+P-K} with shape:
   *  <p>$$[d_0, ..., d_{Q-2}, ref.shape[K], ..., ref.shape[P-1]].$$
   *  <p>For example, say we want to update 4 scattered elements to a rank-1 tensor to
   *  8 elements. In Python, that update would look like this:
   *  <pre>
   *      ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8])
   *      indices = tf.constant([[4], [3], [1] ,[7]])
   *      updates = tf.constant([9, 10, 11, 12])
   *      update = tf.scatter_nd_update(ref, indices, updates)
   *      with tf.Session() as sess:
   *        print sess.run(update)
   *  </pre>
   *  <p>The resulting update to ref would look like this:
   *  <pre>
   *  [1, 11, 3, 10, 9, 6, 7, 12]
   *  </pre>
   *  <p>See {@code tf.scatter_nd} for more details about how to make updates to
   *  slices.
   *  <p>See also {@code tf.scatter_update} and {@code tf.batch_scatter_update}.
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref A mutable Tensor. Should be from a Variable node.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   *  A tensor of indices into ref.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated
   *  values to add to ref.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ScatterNdUpdate} output and operands
   * @return a new instance of ScatterNdUpdate
   */
  public <T extends TType> ScatterNdUpdate<T> scatterNdUpdate(Operand<T> ref,
      Operand<? extends TNumber> indices, Operand<T> updates, ScatterNdUpdate.Options... options) {
    return ScatterNdUpdate.create(scope, ref, indices, updates, options);
  }

  /**
   * Subtracts sparse updates to a variable reference.
   *  <pre>
   *      # Scalar indices
   *      ref[indices, ...] -= updates[...]
   *
   *      # Vector indices (for each i)
   *      ref[indices[i], ...] -= updates[i, ...]
   *
   *      # High rank indices (for each i, ..., j)
   *      ref[indices[i, ..., j], ...] -= updates[i, ..., j, ...]
   *  </pre>
   *  This operation outputs {@code ref} after the update is done.
   *  This makes it easier to chain operations that need to use the reset value.
   *  <p>Duplicate entries are handled correctly: if multiple {@code indices} reference
   *  the same location, their (negated) contributions add.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterSub.png" alt>
   *  </div>
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to subtract from {@code ref}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ScatterSub} output and operands
   * @return a new instance of ScatterSub
   */
  public <T extends TType> ScatterSub<T> scatterSub(Operand<T> ref,
      Operand<? extends TNumber> indices, Operand<T> updates, ScatterSub.Options... options) {
    return ScatterSub.create(scope, ref, indices, updates, options);
  }

  /**
   * Applies sparse updates to a variable reference.
   *  This operation computes
   *  <pre>
   *      # Scalar indices
   *      ref[indices, ...] = updates[...]
   *
   *      # Vector indices (for each i)
   *      ref[indices[i], ...] = updates[i, ...]
   *
   *      # High rank indices (for each i, ..., j)
   *      ref[indices[i, ..., j], ...] = updates[i, ..., j, ...]
   *  </pre>
   *  <p>This operation outputs {@code ref} after the update is done.
   *  This makes it easier to chain operations that need to use the reset value.
   *  <p>If values in {@code ref} is to be updated more than once, because there are
   *  duplicate entries in {@code indices}, the order at which the updates happen
   *  for each value is undefined.
   *  <p>Requires {@code updates.shape = indices.shape + ref.shape[1:]} or {@code updates.shape = []}.
   *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
   *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterUpdate.png" alt>
   *  </div>
   *  <p>See also {@code tf.batch_scatter_update} and {@code tf.scatter_nd_update}.
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref Should be from a {@code Variable} node.
   * @param indices A tensor of indices into the first dimension of {@code ref}.
   * @param updates A tensor of updated values to store in {@code ref}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ScatterUpdate} output and operands
   * @return a new instance of ScatterUpdate
   */
  public <T extends TType> ScatterUpdate<T> scatterUpdate(Operand<T> ref,
      Operand<? extends TNumber> indices, Operand<T> updates, ScatterUpdate.Options... options) {
    return ScatterUpdate.create(scope, ref, indices, updates, options);
  }

  /**
   * The SelectV2 operation
   *
   * @param <T> data type for {@code output} output
   * @param condition the condition value
   * @param t the t value
   * @param e the e value
   * @param <T> data type for {@code SelectV2} output and operands
   * @return a new instance of Select
   */
  public <T extends TType> Select<T> select(Operand<TBool> condition, Operand<T> t, Operand<T> e) {
    return Select.create(scope, condition, t, e);
  }

  /**
   * Computes the difference between two lists of numbers or strings.
   *  Given a list {@code x} and a list {@code y}, this operation returns a list {@code out} that
   *  represents all values that are in {@code x} but not in {@code y}. The returned list {@code out}
   *  is sorted in the same order that the numbers appear in {@code x} (duplicates are
   *  preserved). This operation also returns a list {@code idx} that represents the
   *  position of each {@code out} element in {@code x}. In other words:
   *  <p>{@code out[i] = x[idx[i]] for i in [0, 1, ..., len(out) - 1]}
   *  <p>For example, given this input:
   *  <pre>
   *  x = [1, 2, 3, 4, 5, 6]
   *  y = [1, 3, 5]
   *  </pre>
   *  <p>This operation would return:
   *  <pre>
   *  out ==&gt; [2, 4, 6]
   *  idx ==&gt; [1, 3, 5]
   *  </pre>
   *
   * @param <T> data type for {@code out} output
   * @param <U> data type for {@code idx} output
   * @param x 1-D. Values to keep.
   * @param y 1-D. Values to remove.
   * @param <T> data type for {@code ListDiff} output and operands
   * @return a new instance of SetDiff1d, with default output types
   */
  public <T extends TType> SetDiff1d<T, TInt32> setDiff1d(Operand<T> x, Operand<T> y) {
    return SetDiff1d.create(scope, x, y);
  }

  /**
   * Computes the difference between two lists of numbers or strings.
   *  Given a list {@code x} and a list {@code y}, this operation returns a list {@code out} that
   *  represents all values that are in {@code x} but not in {@code y}. The returned list {@code out}
   *  is sorted in the same order that the numbers appear in {@code x} (duplicates are
   *  preserved). This operation also returns a list {@code idx} that represents the
   *  position of each {@code out} element in {@code x}. In other words:
   *  <p>{@code out[i] = x[idx[i]] for i in [0, 1, ..., len(out) - 1]}
   *  <p>For example, given this input:
   *  <pre>
   *  x = [1, 2, 3, 4, 5, 6]
   *  y = [1, 3, 5]
   *  </pre>
   *  <p>This operation would return:
   *  <pre>
   *  out ==&gt; [2, 4, 6]
   *  idx ==&gt; [1, 3, 5]
   *  </pre>
   *
   * @param <T> data type for {@code out} output
   * @param <U> data type for {@code idx} output
   * @param x 1-D. Values to keep.
   * @param y 1-D. Values to remove.
   * @param outIdx the value of the outIdx property
   * @param <T> data type for {@code ListDiff} output and operands
   * @param <U> data type for {@code ListDiff} output and operands
   * @return a new instance of SetDiff1d
   */
  public <T extends TType, U extends TNumber> SetDiff1d<T, U> setDiff1d(Operand<T> x, Operand<T> y,
      Class<U> outIdx) {
    return SetDiff1d.create(scope, x, y, outIdx);
  }

  /**
   * Number of unique elements along last dimension of input {@code set}.
   *  Input {@code set} is a {@code SparseTensor} represented by {@code set_indices}, {@code set_values},
   *  and {@code set_shape}. The last dimension contains values in a set, duplicates are
   *  allowed but ignored.
   *  <p>If {@code validate_indices} is {@code True}, this op validates the order and range of {@code set}
   *  indices.
   *
   * @param setIndices 2D {@code Tensor}, indices of a {@code SparseTensor}.
   * @param setValues 1D {@code Tensor}, values of a {@code SparseTensor}.
   * @param setShape 1D {@code Tensor}, shape of a {@code SparseTensor}.
   * @param options carries optional attribute values
   * @return a new instance of SetSize
   */
  public SetSize setSize(Operand<TInt64> setIndices, Operand<? extends TType> setValues,
      Operand<TInt64> setShape, SetSize.Options... options) {
    return SetSize.create(scope, setIndices, setValues, setShape, options);
  }

  /**
   * Returns the shape of a tensor.
   *  This operation returns a 1-D integer tensor representing the shape of {@code input}.
   *  <p>For example:
   *  <pre>
   *  # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
   *  shape(t) ==&gt; [2, 2, 3]
   *  </pre>
   *
   * @param <U> data type for {@code output} output
   * @param input the input value
   * @return a new instance of Shape, with default output types
   */
  public org.tensorflow.op.core.Shape<TInt32> shape(Operand<? extends TType> input) {
    return org.tensorflow.op.core.Shape.create(scope, input);
  }

  /**
   * Returns the shape of a tensor.
   *  This operation returns a 1-D integer tensor representing the shape of {@code input}.
   *  <p>For example:
   *  <pre>
   *  # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
   *  shape(t) ==&gt; [2, 2, 3]
   *  </pre>
   *
   * @param <U> data type for {@code output} output
   * @param input the input value
   * @param outType the value of the outType property
   * @param <U> data type for {@code Shape} output and operands
   * @return a new instance of Shape
   */
  public <U extends TNumber> org.tensorflow.op.core.Shape<U> shape(Operand<? extends TType> input,
      Class<U> outType) {
    return org.tensorflow.op.core.Shape.create(scope, input, outType);
  }

  /**
   * Returns shape of tensors.
   *  This operation returns N 1-D integer tensors representing shape of {@code input[i]s}.
   *
   * @param <U> data type for {@code output} output
   * @param input the input value
   * @return a new instance of ShapeN, with default output types
   */
  public ShapeN<TInt32> shapeN(Iterable<Operand<? extends TType>> input) {
    return ShapeN.create(scope, input);
  }

  /**
   * Returns shape of tensors.
   *  This operation returns N 1-D integer tensors representing shape of {@code input[i]s}.
   *
   * @param <U> data type for {@code output} output
   * @param input the input value
   * @param outType the value of the outType property
   * @param <U> data type for {@code ShapeN} output and operands
   * @return a new instance of ShapeN
   */
  public <U extends TNumber> ShapeN<U> shapeN(Iterable<Operand<? extends TType>> input,
      Class<U> outType) {
    return ShapeN.create(scope, input, outType);
  }

  /**
   * Returns the size of a tensor.
   *  This operation returns an integer representing the number of elements in
   *  {@code input}.
   *  <p>For example:
   *  <pre>
   *  # 't' is [[[1, 1,, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]]
   *  size(t) ==&gt; 12
   *  </pre>
   *
   * @param <U> data type for {@code output} output
   * @param input the input value
   * @return a new instance of Size, with default output types
   */
  public Size<TInt32> size(Operand<? extends TType> input) {
    return Size.create(scope, input);
  }

  /**
   * Returns the size of a tensor.
   *  This operation returns an integer representing the number of elements in
   *  {@code input}.
   *  <p>For example:
   *  <pre>
   *  # 't' is [[[1, 1,, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]]
   *  size(t) ==&gt; 12
   *  </pre>
   *
   * @param <U> data type for {@code output} output
   * @param input the input value
   * @param outType the value of the outType property
   * @param <U> data type for {@code Size} output and operands
   * @return a new instance of Size
   */
  public <U extends TNumber> Size<U> size(Operand<? extends TType> input, Class<U> outType) {
    return Size.create(scope, input, outType);
  }

  /**
   * Parses a text file and creates a batch of examples.
   *
   * @param filename The corpus's text file name.
   * @param batchSize The size of produced batch.
   * @param options carries optional attribute values
   * @return a new instance of Skipgram
   */
  public Skipgram skipgram(String filename, Long batchSize, Skipgram.Options... options) {
    return Skipgram.create(scope, filename, batchSize, options);
  }

  /**
   * Return a slice from 'input'.
   *  The output tensor is a tensor with dimensions described by 'size'
   *  whose values are extracted from 'input' starting at the offsets in
   *  'begin'.
   *  <p><em>Requirements</em>:
   *  0 &lt;= begin[i] &lt;= begin[i] + size[i] &lt;= Di  for i in [0, n)
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param begin begin[i] specifies the offset into the 'i'th dimension of
   *  'input' to slice from.
   * @param sizeOutput size[i] specifies the number of elements of the 'i'th dimension
   *  of 'input' to slice. If size[i] is -1, all remaining elements in dimension
   *  i are included in the slice (i.e. this is equivalent to setting
   *  size[i] = input.dim_size(i) - begin[i]).
   * @param <T> data type for {@code Slice} output and operands
   * @param <U> data type for {@code Slice} output and operands
   * @return a new instance of Slice
   */
  public <T extends TType, U extends TNumber> Slice<T> slice(Operand<T> input, Operand<U> begin,
      Operand<U> sizeOutput) {
    return Slice.create(scope, input, begin, sizeOutput);
  }

  /**
   * Returns a copy of the input tensor.
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param <T> data type for {@code Snapshot} output and operands
   * @return a new instance of Snapshot
   */
  public <T extends TType> Snapshot<T> snapshot(Operand<T> input) {
    return Snapshot.create(scope, input);
  }

  /**
   * SpaceToBatch for N-D tensors of type T.
   *  This operation divides &quot;spatial&quot; dimensions {@code [1, ..., M]} of the input into a
   *  grid of blocks of shape {@code block_shape}, and interleaves these blocks with the
   *  &quot;batch&quot; dimension (0) such that in the output, the spatial dimensions
   *  {@code [1, ..., M]} correspond to the position within the grid, and the batch
   *  dimension combines both the position within a spatial block and the original
   *  batch position.  Prior to division into blocks, the spatial dimensions of the
   *  input are optionally zero padded according to {@code paddings}.  See below for a
   *  precise description.
   *
   * @param <T> data type for {@code output} output
   * @param input N-D with shape {@code input_shape = [batch] + spatial_shape + remaining_shape},
   *  where spatial_shape has {@code M} dimensions.
   * @param blockShape 1-D with shape {@code [M]}, all values must be &gt;= 1.
   * @param paddings 2-D with shape {@code [M, 2]}, all values must be &gt;= 0.
   *  {@code paddings[i] = [pad_start, pad_end]} specifies the padding for input dimension
   *  {@code i + 1}, which corresponds to spatial dimension {@code i}.  It is required that
   *  {@code block_shape[i]} divides {@code input_shape[i + 1] + pad_start + pad_end}.
   *  <p>This operation is equivalent to the following steps:
   *  <ol>
   *  <li>
   *  <p>Zero-pad the start and end of dimensions {@code [1, ..., M]} of the
   *  input according to {@code paddings} to produce {@code padded} of shape {@code padded_shape}.
   *  </li>
   *  <li>
   *  <p>Reshape {@code padded} to {@code reshaped_padded} of shape:
   *  <p>[batch] +
   *  [padded_shape[1] / block_shape[0],
   *  block_shape[0],
   *  ...,
   *  padded_shape[M] / block_shape[M-1],
   *  block_shape[M-1]] +
   *  remaining_shape
   *  </li>
   *  <li>
   *  <p>Permute dimensions of {@code reshaped_padded} to produce
   *  {@code permuted_reshaped_padded} of shape:
   *  <p>block_shape +
   *  [batch] +
   *  [padded_shape[1] / block_shape[0],
   *  ...,
   *  padded_shape[M] / block_shape[M-1]] +
   *  remaining_shape
   *  </li>
   *  <li>
   *  <p>Reshape {@code permuted_reshaped_padded} to flatten {@code block_shape} into the batch
   *  dimension, producing an output tensor of shape:
   *  <p>[batch * prod(block_shape)] +
   *  [padded_shape[1] / block_shape[0],
   *  ...,
   *  padded_shape[M] / block_shape[M-1]] +
   *  remaining_shape
   *  </li>
   *  </ol>
   *  <p>Some examples:
   *  <p>(1) For the following input of shape {@code [1, 2, 2, 1]}, {@code block_shape = [2, 2]}, and
   *  {@code paddings = [[0, 0], [0, 0]]}:
   *  <pre>
   *  x = [[[[1], [2]], [[3], [4]]]]
   *  </pre>
   *  <p>The output tensor has shape {@code [4, 1, 1, 1]} and value:
   *  <pre>
   *  [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
   *  </pre>
   *  <p>(2) For the following input of shape {@code [1, 2, 2, 3]}, {@code block_shape = [2, 2]}, and
   *  {@code paddings = [[0, 0], [0, 0]]}:
   *  <pre>
   *  x = [[[[1, 2, 3], [4, 5, 6]],
   *        [[7, 8, 9], [10, 11, 12]]]]
   *  </pre>
   *  <p>The output tensor has shape {@code [4, 1, 1, 3]} and value:
   *  <pre>
   *  [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
   *  </pre>
   *  <p>(3) For the following input of shape {@code [1, 4, 4, 1]}, {@code block_shape = [2, 2]}, and
   *  {@code paddings = [[0, 0], [0, 0]]}:
   *  <pre>
   *  x = [[[[1],   [2],  [3],  [4]],
   *        [[5],   [6],  [7],  [8]],
   *        [[9],  [10], [11],  [12]],
   *        [[13], [14], [15],  [16]]]]
   *  </pre>
   *  <p>The output tensor has shape {@code [4, 2, 2, 1]} and value:
   *  <pre>
   *  x = [[[[1], [3]], [[9], [11]]],
   *       [[[2], [4]], [[10], [12]]],
   *       [[[5], [7]], [[13], [15]]],
   *       [[[6], [8]], [[14], [16]]]]
   *  </pre>
   *  <p>(4) For the following input of shape {@code [2, 2, 4, 1]}, block_shape = {@code [2, 2]}, and
   *  paddings = {@code [[0, 0], [2, 0]]}:
   *  <pre>
   *  x = [[[[1],   [2],  [3],  [4]],
   *        [[5],   [6],  [7],  [8]]],
   *       [[[9],  [10], [11],  [12]],
   *        [[13], [14], [15],  [16]]]]
   *  </pre>
   *  <p>The output tensor has shape {@code [8, 1, 3, 1]} and value:
   *  <pre>
   *  x = [[[[0], [1], [3]]], [[[0], [9], [11]]],
   *       [[[0], [2], [4]]], [[[0], [10], [12]]],
   *       [[[0], [5], [7]]], [[[0], [13], [15]]],
   *       [[[0], [6], [8]]], [[[0], [14], [16]]]]
   *  </pre>
   *  <p>Among others, this operation is useful for reducing atrous convolution into
   *  regular convolution.
   * @param <T> data type for {@code SpaceToBatchND} output and operands
   * @return a new instance of SpaceToBatchNd
   */
  public <T extends TType> SpaceToBatchNd<T> spaceToBatchNd(Operand<T> input,
      Operand<? extends TNumber> blockShape, Operand<? extends TNumber> paddings) {
    return SpaceToBatchNd.create(scope, input, blockShape, paddings);
  }

  /**
   * Splits a tensor into {@code num_split} tensors along one dimension.
   *
   * @param <T> data type for {@code output} output
   * @param axis 0-D.  The dimension along which to split.  Must be in the range
   *  {@code [-rank(value), rank(value))}.
   * @param value The tensor to split.
   * @param numSplit The number of ways to split.  Must evenly divide
   *  {@code value.shape[split_dim]}.
   * @param <T> data type for {@code Split} output and operands
   * @return a new instance of Split
   */
  public <T extends TType> Split<T> split(Operand<TInt32> axis, Operand<T> value, Long numSplit) {
    return Split.create(scope, axis, value, numSplit);
  }

  /**
   * Splits a tensor into {@code num_split} tensors along one dimension.
   *
   * @param <T> data type for {@code output} output
   * @param value The tensor to split.
   * @param sizeSplits list containing the sizes of each output tensor along the split
   *  dimension. Must sum to the dimension of value along split_dim.
   *  Can contain one -1 indicating that dimension is to be inferred.
   * @param axis 0-D.  The dimension along which to split.  Must be in the range
   *  {@code [-rank(value), rank(value))}.
   * @param numSplit the value of the numSplit property
   * @param <T> data type for {@code SplitV} output and operands
   * @return a new instance of SplitV
   */
  public <T extends TType> SplitV<T> splitV(Operand<T> value, Operand<? extends TNumber> sizeSplits,
      Operand<TInt32> axis, Long numSplit) {
    return SplitV.create(scope, value, sizeSplits, axis, numSplit);
  }

  /**
   * Removes dimensions of size 1 from the shape of a tensor.
   *  Given a tensor {@code input}, this operation returns a tensor of the same type with
   *  all dimensions of size 1 removed. If you don't want to remove all size 1
   *  dimensions, you can remove specific size 1 dimensions by specifying
   *  {@code axis}.
   *  <p>For example:
   *  <pre>
   *  # 't' is a tensor of shape [1, 2, 1, 3, 1, 1]
   *  shape(squeeze(t)) ==&gt; [2, 3]
   *  </pre>
   *  <p>Or, to remove specific size 1 dimensions:
   *  <pre>
   *  # 't' is a tensor of shape [1, 2, 1, 3, 1, 1]
   *  shape(squeeze(t, [2, 4])) ==&gt; [1, 2, 3, 1]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param input The {@code input} to squeeze.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Squeeze} output and operands
   * @return a new instance of Squeeze
   */
  public <T extends TType> Squeeze<T> squeeze(Operand<T> input, Squeeze.Options... options) {
    return Squeeze.create(scope, input, options);
  }

  /**
   * Packs a list of {@code N} rank-{@code R} tensors into one rank-{@code (R+1)} tensor.
   *  Packs the {@code N} tensors in {@code values} into a tensor with rank one higher than each
   *  tensor in {@code values}, by packing them along the {@code axis} dimension.
   *  Given a list of tensors of shape {@code (A, B, C)};
   *  <p>if {@code axis == 0} then the {@code output} tensor will have the shape {@code (N, A, B, C)}.
   *  if {@code axis == 1} then the {@code output} tensor will have the shape {@code (A, N, B, C)}.
   *  Etc.
   *  <p>For example:
   *  <pre>
   *  # 'x' is [1, 4]
   *  # 'y' is [2, 5]
   *  # 'z' is [3, 6]
   *  pack([x, y, z]) =&gt; [[1, 4], [2, 5], [3, 6]]  # Pack along first dim.
   *  pack([x, y, z], axis=1) =&gt; [[1, 2, 3], [4, 5, 6]]
   *  </pre>
   *  <p>This is the opposite of {@code unpack}.
   *
   * @param <T> data type for {@code output} output
   * @param values Must be of same shape and type.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Pack} output and operands
   * @return a new instance of Stack
   */
  public <T extends TType> Stack<T> stack(Iterable<Operand<T>> values, Stack.Options... options) {
    return Stack.create(scope, values, options);
  }

  /**
   * Stage values similar to a lightweight Enqueue.
   *  The basic functionality of this Op is similar to a queue with many
   *  fewer capabilities and options.  This Op is optimized for performance.
   *
   * @param values a list of tensors
   *  dtypes A list of data types that inserted values should adhere to.
   * @param options carries optional attribute values
   * @return a new instance of Stage
   */
  public Stage stage(Iterable<Operand<?>> values, Stage.Options... options) {
    return Stage.create(scope, values, options);
  }

  /**
   * Op removes all elements in the underlying container.
   *
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of StageClear
   */
  public StageClear stageClear(List<Class<? extends TType>> dtypes, StageClear.Options... options) {
    return StageClear.create(scope, dtypes, options);
  }

  /**
   * Op peeks at the values at the specified index.  If the
   *  underlying container does not contain sufficient elements
   *  this op will block until it does.   This Op is optimized for
   *  performance.
   *
   * @param index the index value
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of StagePeek
   */
  public StagePeek stagePeek(Operand<TInt32> index, List<Class<? extends TType>> dtypes,
      StagePeek.Options... options) {
    return StagePeek.create(scope, index, dtypes, options);
  }

  /**
   * Op returns the number of elements in the underlying container.
   *
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of StageSize
   */
  public StageSize stageSize(List<Class<? extends TType>> dtypes, StageSize.Options... options) {
    return StageSize.create(scope, dtypes, options);
  }

  /**
   * An n-way switch statement which calls a single branch function.
   *  <pre>
   *  An n-way switch statement, implementing the following:
   *  ```
   *  switch (branch_index) {
   *    case 0:
   *      output = branches[0](input);
   *      break;
   *    case 1:
   *      output = branches[1](input);
   *      break;
   *    ...
   *    case [[nbranches-1]]:
   *    default:
   *      output = branches[nbranches-1](input);
   *      break;
   *  }
   *  ```
   *  </pre>
   *
   * @param branchIndex The branch selector, an int32 Tensor.
   * @param input A list of input tensors passed to the branch function.
   * @param Tout A list of output types.
   * @param branches <pre>
   *    A list of functions each of which takes 'inputs' and returns a list of
   *    tensors, whose types are the same as what every other branch returns.
   *  </pre>
   * @param options carries optional attribute values
   * @return a new instance of StatefulCase
   */
  public StatefulCase statefulCase(Operand<TInt32> branchIndex, Iterable<Operand<?>> input,
      List<Class<? extends TType>> Tout, List<ConcreteFunction> branches, Case.Options... options) {
    return StatefulCase.create(scope, branchIndex, input, Tout, branches, options);
  }

  /**
   * output = cond ? then_branch(input) : else_branch(input)
   *
   * @param cond <pre>
   *    A Tensor. If the tensor is a scalar of non-boolean type, the
   *    scalar is converted to a boolean according to the
   *    following rule: if the scalar is a numerical value, non-zero means
   *    `True` and zero means False; if the scalar is a string, non-empty
   *    means `True` and empty means `False`. If the tensor is not a scalar,
   *    being empty means False and being non-empty means True.
   *  </pre>
   * @param input A list of input tensors.
   * @param Tout A list of output types.
   * @param thenBranch <pre>
   *    A function that takes 'inputs' and returns a list of tensors, whose
   *    types are the same as what else_branch returns.
   *  </pre>
   * @param elseBranch <pre>
   *  A function that takes 'inputs' and returns a list of tensors, whose
   *  types are the same as what then_branch returns.
   *  </pre>
   * @param options carries optional attribute values
   * @return a new instance of StatefulIf
   */
  public StatefulIf statefulIf(Operand<? extends TType> cond, Iterable<Operand<?>> input,
      List<Class<? extends TType>> Tout, ConcreteFunction thenBranch, ConcreteFunction elseBranch,
      If.Options... options) {
    return StatefulIf.create(scope, cond, input, Tout, thenBranch, elseBranch, options);
  }

  /**
   * returns {@code f(inputs)}, where {@code f}'s body is placed and partitioned.
   *
   * @param args A list of input tensors.
   * @param Tout A list of output types.
   * @param f <pre>
   *    A function that takes 'args', a list of tensors, and returns 'output',
   *    another list of tensors. Input and output types are specified by 'Tin'
   *    and 'Tout'. The function body of f will be placed and partitioned across
   *    devices, setting this op apart from the regular Call op. This op is
   *    stateful.
   *  </pre>
   * @param options carries optional attribute values
   * @return a new instance of StatefulPartitionedCall
   */
  public StatefulPartitionedCall statefulPartitionedCall(Iterable<Operand<?>> args,
      List<Class<? extends TType>> Tout, ConcreteFunction f, PartitionedCall.Options... options) {
    return StatefulPartitionedCall.create(scope, args, Tout, f, options);
  }

  /**
   * output = input; While (Cond(output)) { output = Body(output) }
   *
   * @param input A list of input tensors whose types are T.
   * @param cond <pre>
   *    A function takes 'input' and returns a tensor.  If the tensor is
   *    a scalar of non-boolean, the scalar is converted to a boolean
   *    according to the following rule: if the scalar is a numerical
   *    value, non-zero means True and zero means False; if the scalar is
   *    a string, non-empty means True and empty means False. If the
   *    tensor is not a scalar, non-emptiness means True and False
   *    otherwise.
   *  </pre>
   * @param body <pre>
   *    A function that takes a list of tensors and returns another
   *    list of tensors. Both lists have the same types as specified
   *    by T.
   *  </pre>
   * @param options carries optional attribute values
   * @return a new instance of StatefulWhile
   */
  public StatefulWhile statefulWhile(Iterable<Operand<?>> input, ConcreteFunction cond,
      ConcreteFunction body, While.Options... options) {
    return StatefulWhile.create(scope, input, cond, body, options);
  }

  /**
   * output = cond ? then_branch(input) : else_branch(input)
   *
   * @param cond <pre>
   *    A Tensor. If the tensor is a scalar of non-boolean type, the
   *    scalar is converted to a boolean according to the
   *    following rule: if the scalar is a numerical value, non-zero means
   *    `True` and zero means False; if the scalar is a string, non-empty
   *    means `True` and empty means `False`. If the tensor is not a scalar,
   *    being empty means False and being non-empty means True.
   *
   *    This should only be used when the if then/else body functions do not
   *    have stateful ops.
   *  </pre>
   * @param input A list of input tensors.
   * @param Tout A list of output types.
   * @param thenBranch <pre>
   *    A function that takes 'inputs' and returns a list of tensors, whose
   *    types are the same as what else_branch returns.
   *  </pre>
   * @param elseBranch <pre>
   *  A function that takes 'inputs' and returns a list of tensors, whose
   *  types are the same as what then_branch returns.
   *  </pre>
   * @param options carries optional attribute values
   * @return a new instance of StatelessIf
   */
  public StatelessIf statelessIf(Operand<? extends TType> cond, Iterable<Operand<?>> input,
      List<Class<? extends TType>> Tout, ConcreteFunction thenBranch, ConcreteFunction elseBranch,
      If.Options... options) {
    return StatelessIf.create(scope, cond, input, Tout, thenBranch, elseBranch, options);
  }

  /**
   * returns {@code f(inputs)}, where {@code f}'s body is placed and partitioned.
   *
   * @param args A list of input tensors.
   * @param Tout A list of output types.
   * @param f <pre>
   *    A function that takes 'args', a list of tensors, and returns 'output',
   *    another list of tensors. Input and output types are specified by 'Tin'
   *    and 'Tout'. The function body of f will be placed and partitioned across
   *    devices, setting this op apart from the regular Call op.
   *  </pre>
   * @param options carries optional attribute values
   * @return a new instance of StatelessPartitionedCall
   */
  public StatelessPartitionedCall statelessPartitionedCall(Iterable<Operand<?>> args,
      List<Class<? extends TType>> Tout, ConcreteFunction f, PartitionedCall.Options... options) {
    return StatelessPartitionedCall.create(scope, args, Tout, f, options);
  }

  /**
   * output = input; While (Cond(output)) { output = Body(output) }
   *
   * @param input A list of input tensors whose types are T.
   * @param cond <pre>
   *    A function takes 'input' and returns a tensor.  If the tensor is
   *    a scalar of non-boolean, the scalar is converted to a boolean
   *    according to the following rule: if the scalar is a numerical
   *    value, non-zero means True and zero means False; if the scalar is
   *    a string, non-empty means True and empty means False. If the
   *    tensor is not a scalar, non-emptiness means True and False
   *    otherwise.
   *
   *    This should only be used when the while condition and body functions
   *    do not have stateful ops.
   *  </pre>
   * @param body <pre>
   *    A function that takes a list of tensors and returns another
   *    list of tensors. Both lists have the same types as specified
   *    by T.
   *  </pre>
   * @param options carries optional attribute values
   * @return a new instance of StatelessWhile
   */
  public StatelessWhile statelessWhile(Iterable<Operand<?>> input, ConcreteFunction cond,
      ConcreteFunction body, While.Options... options) {
    return StatelessWhile.create(scope, input, cond, body, options);
  }

  /**
   * Stops gradient computation.
   *  When executed in a graph, this op outputs its input tensor as-is.
   *  <p>When building ops to compute gradients, this op prevents the contribution of
   *  its inputs to be taken into account.  Normally, the gradient generator adds ops
   *  to a graph to compute the derivatives of a specified 'loss' by recursively
   *  finding out inputs that contributed to its computation.  If you insert this op
   *  in the graph it inputs are masked from the gradient generator.  They are not
   *  taken into account for computing gradients.
   *  <p>This is useful any time you want to compute a value with TensorFlow but need
   *  to pretend that the value was a constant. For example, the softmax function
   *  for a vector x can be written as
   *  <pre>
   *
   *    def softmax(x):
   *      numerator = tf.exp(x)
   *      denominator = tf.reduce_sum(numerator)
   *      return numerator / denominator
   *  </pre>
   *  <p>This however is susceptible to overflow if the values in x are large. An
   *  alternative more stable way is to subtract the maximum of x from each of the
   *  values.
   *  <pre>
   *
   *    def stable_softmax(x):
   *      z = x - tf.reduce_max(x)
   *      numerator = tf.exp(z)
   *      denominator = tf.reduce_sum(numerator)
   *      return numerator / denominator
   *  </pre>
   *  <p>However, when we backprop through the softmax to x, we dont want to backprop
   *  through the {@code tf.reduce_max(x)} (if the max values are not unique then the
   *  gradient could flow to the wrong input) calculation and treat that as a
   *  constant. Therefore, we should write this out as
   *  <pre>
   *
   *    def stable_softmax(x):
   *      z = x - tf.stop_gradient(tf.reduce_max(x))
   *      numerator = tf.exp(z)
   *      denominator = tf.reduce_sum(numerator)
   *      return numerator / denominator
   *  </pre>
   *  <p>Some other examples include:
   *  <ul>
   *  <li>The <em>EM</em> algorithm where the <em>M-step</em> should not involve backpropagation
   *  through the output of the <em>E-step</em>.</li>
   *  <li>Contrastive divergence training of Boltzmann machines where, when
   *  differentiating the energy function, the training must not backpropagate
   *  through the graph that generated the samples from the model.</li>
   *  <li>Adversarial training, where no backprop should happen through the adversarial
   *  example generation process.</li>
   *  </ul>
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param <T> data type for {@code StopGradient} output and operands
   * @return a new instance of StopGradient
   */
  public <T extends TType> StopGradient<T> stopGradient(Operand<T> input) {
    return StopGradient.create(scope, input);
  }

  /**
   * Return a strided slice from `input`.
   *  <p>
   *  The goal of this op is to produce a new tensor with a subset of the elements from the `n` dimensional `input`
   *  tensor. The subset is chosen using a sequence of `m` sparse range specifications encoded into the arguments of this
   *  function. Note, in some cases `m` could be equal to `n`, but this need not be the case. Each range specification
   *  entry can be one of the following:
   *  <p>
   *  - An ellipsis (...) using {@link Indices#ellipsis()}. Ellipses are used to imply zero or more dimensions of
   *  full-dimension selection. For example, {@code stridedSlice(foo, Indices.ellipsis()} is the identity slice.
   *  <p>
   *  - A new axis using {@link Indices#newAxis()}. This is used to insert a new shape=1 dimension.
   *  For example, `{@code stridedSlice(foo, Indices.newAxis())} where {@code foo} is shape {@code (3, 4)} 
   *  produces a {@code (1, 3, 4)} tensor.
   *  <p>
   *  - A range {@code begin:end:stride} using {@link Indices#slice(Long, Long, long)}  Index.slice()} or {@link Indices#all()}. This is used to specify
   *  how much to choose from a given dimension. {@code stride} can be any integer but 0.  {@code begin} is an integer which
   *  represents the index of the first value to select while {@code end} represents the index of the last value to select 
   *  (exclusive). Begin and end can be null, in which case the index begins or ends at the beginning or end of the dimension,
   *  respectively (reversed if stride is negative).  When both are null, {@code slice()} is the same as {@code all()}.
   *  The number of values selected in each dimension is {@code end - begin} if {@code stride > 0} and {@code begin - end}
   *  if {@code stride < 0}. {@code begin} and {@code end} can be negative where {@code -1} is the last element, {@code -2}
   *  is the second to last. For example, given a shape {@code (3,)} tensor {@code stridedSlice(foo, Indices.all())}, the
   *  effective {@code begin} and {@code end} are {@code 0} and {@code 3}. Do not assume this is equivalent to
   *  {@code stridedSlice(foo, Indices.slice(0, -1))} which has an effective {@code begin} and {@code end} of {@code 0} and
   *  {@code 2}. Another example is {@code stridedSlice(foo, Indices.slice(-2, null, -1))} which reverses the first dimension
   *  of a tensor while dropping the last two (in the original order elements). For example {@code foo = [1,2,3,4];
   *  stridedSlice(foo, Indices.slice(-2, null, -1)} is {@code [4,3]}.
   *  <p>
   *  - A single index using {@link Indices#at(long)}. This is used to keep only elements that have a given index. For
   *  example ({@code stridedSlice(foo, Indices.at(2))} on a shape {@code (5,6)} tensor produces a shape {@code (6,)} tensor.
   *  The dimension can be kept with size one using {@link Indices#at(long, boolean)}.
   *  <p>
   *  These semantics generally follow NumPy's indexing semantics, which can be found here:
   *  <a href="https://numpy.org/doc/stable/reference/arrays.indexing.html">https://numpy.org/doc/stable/reference/arrays.indexing.html</a>
   *  <p>
   *
   *  <i>Requirements</i>:
   *  `0 != strides[i] for i in [0, m)` Only one ellipsis.
   *
   * @param <T> data type for {@code output()} output
   * @param indices The indices to slice.  See {@link Indices}.
   * @return a new instance of StridedSlice
   * @see Indices
   */
  public <T extends TType> StridedSlice<T> stridedSlice(Operand<T> input, Index... indices) {
    return StridedSliceHelper.stridedSlice(scope, input, indices);
  }

  /**
   * Return a strided slice from {@code input}.
   *  Note, most python users will want to use the Python {@code Tensor.__getitem__}
   *  or {@code Variable.__getitem__} rather than this op directly.
   *  <p>The goal of this op is to produce a new tensor with a subset of
   *  the elements from the {@code n} dimensional {@code input} tensor. The subset is chosen using
   *  a sequence of {@code m} sparse range specifications encoded into the arguments
   *  of this function. Note, in some cases
   *  {@code m} could be equal to {@code n}, but this need not be the case. Each
   *  range specification entry can be one of the following:
   *  <ul>
   *  <li>
   *  <p>An ellipsis (...). Ellipses are used to imply zero or more
   *  dimensions of full-dimension selection and are produced using
   *  {@code ellipsis_mask}. For example, {@code foo[...]} is the identity slice.
   *  </li>
   *  <li>
   *  <p>A new axis. This is used to insert a new shape=1 dimension and is
   *  produced using {@code new_axis_mask}. For example, {@code foo[:, ...]} where
   *  {@code foo} is shape {@code (3, 4)} produces a {@code (1, 3, 4)} tensor.
   *  </li>
   *  <li>
   *  <p>A range {@code begin:end:stride}. This is used to specify how much to choose from
   *  a given dimension. {@code stride} can be any integer but 0.  {@code begin} is an integer
   *  which represents the index of the first value to select while {@code end} represents
   *  the index of the last value to select. The number of values selected in each
   *  dimension is {@code end - begin} if {@code stride > 0} and {@code begin - end} if {@code stride < 0}.
   *  {@code begin} and {@code end} can be negative where {@code -1} is the last element, {@code -2} is
   *  the second to last. {@code begin_mask} controls whether to replace the explicitly
   *  given {@code begin} with an implicit effective value of {@code 0} if {@code stride > 0} and
   *  {@code -1} if {@code stride < 0}. {@code end_mask} is analogous but produces the number
   *  required to create the largest open interval. For example, given a shape
   *  {@code (3,)} tensor {@code foo[:]}, the effective {@code begin} and {@code end} are {@code 0} and {@code 3}. Do
   *  not assume this is equivalent to {@code foo[0:-1]} which has an effective {@code begin}
   *  and {@code end} of {@code 0} and {@code 2}. Another example is {@code foo[-2::-1]} which reverses the
   *  first dimension of a tensor while dropping the last two (in the original
   *  order elements). For example {@code foo = [1,2,3,4]; foo[-2::-1]} is {@code [4,3]}.
   *  </li>
   *  <li>
   *  <p>A single index. This is used to keep only elements that have a given
   *  index. For example ({@code foo[2, :]} on a shape {@code (5,6)} tensor produces a
   *  shape {@code (6,)} tensor. This is encoded in {@code begin} and {@code end} and
   *  {@code shrink_axis_mask}.
   *  </li>
   *  </ul>
   *  <p>Each conceptual range specification is encoded in the op's argument. This
   *  encoding is best understand by considering a non-trivial example. In
   *  particular,
   *  {@code foo[1, 2:4, None, ..., :-3:-1, :]} will be encoded as
   *  <pre>
   *  begin = [1, 2, x, x, 0, x] # x denotes don't care (usually 0)
   *  end = [2, 4, x, x, -3, x]
   *  strides = [1, 1, x, x, -1, 1]
   *  begin_mask = 1&lt;&lt;4 | 1&lt;&lt;5 = 48
   *  end_mask = 1&lt;&lt;5 = 32
   *  ellipsis_mask = 1&lt;&lt;3 = 8
   *  new_axis_mask = 1&lt;&lt;2 = 4
   *  shrink_axis_mask = 1&lt;&lt;0 = 1
   *  </pre>
   *  <p>In this case if {@code foo.shape} is (5, 5, 5, 5, 5, 5) the final shape of
   *  the slice becomes (2, 1, 5, 5, 2, 5).
   *  Let us walk step by step through each argument specification.
   *  <ol>
   *  <li>
   *  <p>The first argument in the example slice is turned into {@code begin = 1} and
   *  {@code end = begin + 1 = 2}. To disambiguate from the original spec {@code 2:4} we
   *  also set the appropriate bit in {@code shrink_axis_mask}.
   *  </li>
   *  <li>
   *  <p>{@code 2:4} is contributes 2, 4, 1 to begin, end, and stride. All masks have
   *  zero bits contributed.
   *  </li>
   *  <li>
   *  <p>None is a synonym for {@code tf.newaxis}. This means insert a dimension of size 1
   *  dimension in the final shape. Dummy values are contributed to begin,
   *  end and stride, while the new_axis_mask bit is set.
   *  </li>
   *  <li>
   *  <p>{@code ...} grab the full ranges from as many dimensions as needed to
   *  fully specify a slice for every dimension of the input shape.
   *  </li>
   *  <li>
   *  <p>{@code :-3:-1} shows the use of negative indices. A negative index {@code i} associated
   *  with a dimension that has shape {@code s} is converted to a positive index
   *  {@code s + i}. So {@code -1} becomes {@code s-1} (i.e. the last element). This conversion
   *  is done internally so begin, end and strides receive x, -3, and -1.
   *  The appropriate begin_mask bit is set to indicate the start range is the
   *  full range (ignoring the x).
   *  </li>
   *  <li>
   *  <p>{@code :} indicates that the entire contents of the corresponding dimension
   *  is selected. This is equivalent to {@code ::} or {@code 0::1}. begin, end, and strides
   *  receive 0, 0, and 1, respectively. The appropriate bits in {@code begin_mask} and
   *  {@code end_mask} are also set.
   *  </li>
   *  </ol>
   *  <p><em>Requirements</em>:
   *  {@code 0 != strides[i] for i in [0, m)}
   *  {@code ellipsis_mask must be a power of two (only one ellipsis)}
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param begin {@code begin[k]} specifies the offset into the {@code k}th range specification.
   *  The exact dimension this corresponds to will be determined by context.
   *  Out-of-bounds values will be silently clamped. If the {@code k}th bit of
   *  {@code begin_mask} then {@code begin[k]} is ignored and the full range of the
   *  appropriate dimension is used instead. Negative values causes indexing
   *  to start from the highest element e.g. If {@code foo==[1,2,3]} then {@code foo[-1]==3}.
   * @param end {@code end[i]} is like {@code begin} with the exception that {@code end_mask} is
   *  used to determine full ranges.
   * @param strides {@code strides[i]} specifies the increment in the {@code i}th specification
   *  after extracting a given element. Negative indices will reverse
   *  the original order. Out or range values are
   *  clamped to {@code [0,dim[i]) if slice[i]>0} or {@code [-1,dim[i]-1] if slice[i] < 0}
   * @param options carries optional attribute values
   * @param <T> data type for {@code StridedSlice} output and operands
   * @param <U> data type for {@code StridedSlice} output and operands
   * @return a new instance of StridedSlice
   */
  public <T extends TType, U extends TNumber> StridedSlice<T> stridedSlice(Operand<T> input,
      Operand<U> begin, Operand<U> end, Operand<U> strides, StridedSlice.Options... options) {
    return StridedSlice.create(scope, input, begin, end, strides, options);
  }

  /**
   * Assign `value` to the sliced l-value reference of `ref`.
   *  <p>
   *  The values of `value` are assigned to the positions in the variable `ref` that are selected by the slice
   *  parameters. The slice parameters `begin`, `end`, `strides`, etc. work exactly as in `StridedSlice`.
   *  <p>
   *  NOTE this op currently does not support broadcasting and so `value`'s shape must be exactly the shape produced by
   *  the slice of `ref`.
   *
   * @param <T> data type for {@code outputRef()} output
   * @param ref the tensor to assign to.
   * @param value the value to assign.
   * @param indices The indices to slice.  See {@link Indices}.
   * @return a new instance of StridedSliceAssign
   * @see org.tensorflow.op.Ops#stridedSlice(Operand, Index...)
   */
  public <T extends TType> StridedSliceAssign<T> stridedSliceAssign(Operand<T> ref,
      Operand<T> value, Index... indices) {
    return StridedSliceHelper.stridedSliceAssign(scope, ref, value, indices);
  }

  /**
   * Assign {@code value} to the sliced l-value reference of {@code ref}.
   *  The values of {@code value} are assigned to the positions in the variable
   *  {@code ref} that are selected by the slice parameters. The slice parameters
   *  {@code begin}, {@code end}, {@code strides}, etc. work exactly as in {@code StridedSlice}.
   *  <p>NOTE this op currently does not support broadcasting and so {@code value}'s
   *  shape must be exactly the shape produced by the slice of {@code ref}.
   *
   * @param <T> data type for {@code output_ref} output
   * @param ref the ref value
   * @param begin the begin value
   * @param end the end value
   * @param strides the strides value
   * @param value the value value
   * @param options carries optional attribute values
   * @param <T> data type for {@code StridedSliceAssign} output and operands
   * @param <U> data type for {@code StridedSliceAssign} output and operands
   * @return a new instance of StridedSliceAssign
   */
  public <T extends TType, U extends TNumber> StridedSliceAssign<T> stridedSliceAssign(
      Operand<T> ref, Operand<U> begin, Operand<U> end, Operand<U> strides, Operand<T> value,
      StridedSliceAssign.Options... options) {
    return StridedSliceAssign.create(scope, ref, begin, end, strides, value, options);
  }

  /**
   * Returns the gradient of {@code StridedSlice}.
   *  Since {@code StridedSlice} cuts out pieces of its {@code input} which is size
   *  {@code shape}, its gradient will have the same shape (which is passed here
   *  as {@code shape}). The gradient will be zero in any element that the slice
   *  does not select.
   *  <p>Arguments are the same as StridedSliceGrad with the exception that
   *  {@code dy} is the input gradient to be propagated and {@code shape} is the
   *  shape of {@code StridedSlice}'s {@code input}.
   *
   * @param <U> data type for {@code output} output
   * @param shape the shape value
   * @param begin the begin value
   * @param end the end value
   * @param strides the strides value
   * @param dy the dy value
   * @param options carries optional attribute values
   * @param <U> data type for {@code StridedSliceGrad} output and operands
   * @param <T> data type for {@code StridedSliceGrad} output and operands
   * @return a new instance of StridedSliceGrad
   */
  public <U extends TType, T extends TNumber> StridedSliceGrad<U> stridedSliceGrad(Operand<T> shape,
      Operand<T> begin, Operand<T> end, Operand<T> strides, Operand<U> dy,
      StridedSliceGrad.Options... options) {
    return StridedSliceGrad.create(scope, shape, begin, end, strides, dy, options);
  }

  /**
   * Computes the sum of elements across dimensions of a tensor.
   *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
   *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
   *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
   *  retained with length 1.
   *
   * @param <T> data type for {@code output} output
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   *  {@code [-rank(input), rank(input))}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Sum} output and operands
   * @return a new instance of Sum
   */
  public <T extends TType> Sum<T> sum(Operand<T> input, Operand<? extends TNumber> axis,
      Sum.Options... options) {
    return Sum.create(scope, input, axis, options);
  }

  /**
   * Forwards {@code data} to the output port determined by {@code pred}.
   *  If {@code pred} is true, the {@code data} input is forwarded to {@code output_true}. Otherwise,
   *  the data goes to {@code output_false}.
   *  <p>See also {@code RefSwitch} and {@code Merge}.
   *
   * @param <T> data type for {@code output_false} output
   * @param data The tensor to be forwarded to the appropriate output.
   * @param pred A scalar that specifies which output port will receive data.
   * @param <T> data type for {@code Switch} output and operands
   * @return a new instance of SwitchCond
   */
  public <T extends TType> SwitchCond<T> switchCond(Operand<T> data, Operand<TBool> pred) {
    return SwitchCond.create(scope, data, pred);
  }

  /**
   * Returns a tensor that may be mutated, but only persists within a single step.
   *  This is an experimental op for internal use only and it is possible to use this
   *  op in unsafe ways.  DO NOT USE unless you fully understand the risks.
   *  <p>It is the caller's responsibility to ensure that 'ref' is eventually passed to a
   *  matching 'DestroyTemporaryVariable' op after all other uses have completed.
   *  <p>Outputs a ref to the tensor state so it may be read or modified.
   *  <p>E.g.
   *  var = state_ops.<em>temporary_variable([1, 2], types.float</em>)
   *  var_name = var.op.name
   *  var = state_ops.assign(var, [[4.0, 5.0]])
   *  var = state_ops.assign_add(var, [[6.0, 7.0]])
   *  final = state_ops._destroy_temporary_variable(var, var_name=var_name)
   *
   * @param <T> data type for {@code ref} output
   * @param shape The shape of the variable tensor.
   * @param dtype The type of elements in the variable tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TemporaryVariable} output and operands
   * @return a new instance of TemporaryVariable
   */
  public <T extends TType> TemporaryVariable<T> temporaryVariable(Shape shape, Class<T> dtype,
      TemporaryVariable.Options... options) {
    return TemporaryVariable.create(scope, shape, dtype, options);
  }

  /**
   * An array of Tensors of given size.
   *  Write data via Write and read via Read or Pack.
   *
   * @param sizeOutput The size of the array.
   * @param dtype The type of the elements on the tensor_array.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TensorArrayV3} output and operands
   * @return a new instance of TensorArray
   */
  public <T extends TType> TensorArray tensorArray(Operand<TInt32> sizeOutput, Class<T> dtype,
      TensorArray.Options... options) {
    return TensorArray.create(scope, sizeOutput, dtype, options);
  }

  /**
   * Delete the TensorArray from its resource container.
   *  This enables the user to close and release the resource in the middle
   *  of a step/run.
   *
   * @param handle The handle to a TensorArray (output of TensorArray or TensorArrayGrad).
   * @return a new instance of TensorArrayClose
   */
  public TensorArrayClose tensorArrayClose(Operand<? extends TType> handle) {
    return TensorArrayClose.create(scope, handle);
  }

  /**
   * Concat the elements from the TensorArray into value {@code value}.
   *  Takes {@code T} elements of shapes
   *  <pre>
   *  (n0 x d0 x d1 x ...), (n1 x d0 x d1 x ...), ..., (n(T-1) x d0 x d1 x ...)
   *  </pre>
   *  <p>and concatenates them into a Tensor of shape:
   *  <p>{@code (n0 + n1 + ... + n(T-1) x d0 x d1 x ...)}
   *  <p>All elements must have the same shape (excepting the first dimension).
   *
   * @param <T> data type for {@code value} output
   * @param handle The handle to a TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param dtype The type of the elem that is returned.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TensorArrayConcatV3} output and operands
   * @return a new instance of TensorArrayConcat
   */
  public <T extends TType> TensorArrayConcat<T> tensorArrayConcat(Operand<? extends TType> handle,
      Operand<TFloat32> flowIn, Class<T> dtype, TensorArrayConcat.Options... options) {
    return TensorArrayConcat.create(scope, handle, flowIn, dtype, options);
  }

  /**
   * Gather specific elements from the TensorArray into output {@code value}.
   *  All elements selected by {@code indices} must have the same shape.
   *
   * @param <T> data type for {@code value} output
   * @param handle The handle to a TensorArray.
   * @param indices The locations in the TensorArray from which to read tensor elements.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param dtype The type of the elem that is returned.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TensorArrayGatherV3} output and operands
   * @return a new instance of TensorArrayGather
   */
  public <T extends TType> TensorArrayGather<T> tensorArrayGather(Operand<? extends TType> handle,
      Operand<TInt32> indices, Operand<TFloat32> flowIn, Class<T> dtype,
      TensorArrayGather.Options... options) {
    return TensorArrayGather.create(scope, handle, indices, flowIn, dtype, options);
  }

  /**
   * Creates a TensorArray for storing the gradients of values in the given handle.
   *  If the given TensorArray gradient already exists, returns a reference to it.
   *  <p>Locks the size of the original TensorArray by disabling its dynamic size flag.
   *  <p><strong>A note about the input flow_in:</strong>
   *  <p>The handle flow_in forces the execution of the gradient lookup to occur
   *  only after certain other operations have occurred.  For example, when
   *  the forward TensorArray is dynamically sized, writes to this TensorArray
   *  may resize the object.  The gradient TensorArray is statically sized based
   *  on the size of the forward TensorArray when this operation executes.
   *  Furthermore, the size of the forward TensorArray is frozen by this call.
   *  As a result, the flow is used to ensure that the call to generate the gradient
   *  TensorArray only happens after all writes are executed.
   *  <p>In the case of dynamically sized TensorArrays, gradient computation should
   *  only be performed on read operations that have themselves been chained via
   *  flow to occur only after all writes have executed. That way the final size
   *  of the forward TensorArray is known when this operation is called.
   *  <p><strong>A note about the source attribute:</strong>
   *  <p>TensorArray gradient calls use an accumulator TensorArray object.  If
   *  multiple gradients are calculated and run in the same session, the multiple
   *  gradient nodes may accidentally flow through the same accumulator TensorArray.
   *  This double counts and generally breaks the TensorArray gradient flow.
   *  <p>The solution is to identify which gradient call this particular
   *  TensorArray gradient is being called in.  This is performed by identifying
   *  a unique string (e.g. &quot;gradients&quot;, &quot;gradients_1&quot;, ...) from the input
   *  gradient Tensor's name.  This string is used as a suffix when creating
   *  the TensorArray gradient object here (the attribute {@code source}).
   *  <p>The attribute {@code source} is added as a suffix to the forward TensorArray's
   *  name when performing the creation / lookup, so that each separate gradient
   *  calculation gets its own TensorArray accumulator.
   *
   * @param handle The handle to the forward TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param source The gradient source string, used to decide which gradient TensorArray
   *  to return.
   * @return a new instance of TensorArrayGrad
   */
  public TensorArrayGrad tensorArrayGrad(Operand<? extends TType> handle, Operand<TFloat32> flowIn,
      String source) {
    return TensorArrayGrad.create(scope, handle, flowIn, source);
  }

  /**
   * Creates a TensorArray for storing multiple gradients of values in the given handle.
   *  Similar to TensorArrayGradV3. However it creates an accumulator with an
   *  expanded shape compared to the input TensorArray whose gradient is being
   *  computed. This enables multiple gradients for the same TensorArray to be
   *  calculated using the same accumulator.
   *
   * @param handle The handle to the forward TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param shapeToPrepend An int32 vector representing a shape. Elements in the gradient accumulator will
   *  have shape which is this shape_to_prepend value concatenated with shape of the
   *  elements in the TensorArray corresponding to the input handle.
   * @param source The gradient source string, used to decide which gradient TensorArray
   *  to return.
   * @return a new instance of TensorArrayGradWithShape
   */
  public TensorArrayGradWithShape tensorArrayGradWithShape(Operand<? extends TType> handle,
      Operand<TFloat32> flowIn, Operand<TInt32> shapeToPrepend, String source) {
    return TensorArrayGradWithShape.create(scope, handle, flowIn, shapeToPrepend, source);
  }

  /**
   * The TensorArrayPack operation
   *
   * @param <T> data type for {@code value} output
   * @param handle the handle value
   * @param flowIn the flowIn value
   * @param dtype the value of the dtype property
   * @param options carries optional attribute values
   * @param <T> data type for {@code TensorArrayPack} output and operands
   * @return a new instance of TensorArrayPack
   */
  public <T extends TType> TensorArrayPack<T> tensorArrayPack(Operand<TString> handle,
      Operand<TFloat32> flowIn, Class<T> dtype, TensorArrayPack.Options... options) {
    return TensorArrayPack.create(scope, handle, flowIn, dtype, options);
  }

  /**
   * Read an element from the TensorArray into output {@code value}.
   *
   * @param <T> data type for {@code value} output
   * @param handle The handle to a TensorArray.
   * @param index the index value
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param dtype The type of the elem that is returned.
   * @param <T> data type for {@code TensorArrayReadV3} output and operands
   * @return a new instance of TensorArrayRead
   */
  public <T extends TType> TensorArrayRead<T> tensorArrayRead(Operand<? extends TType> handle,
      Operand<TInt32> index, Operand<TFloat32> flowIn, Class<T> dtype) {
    return TensorArrayRead.create(scope, handle, index, flowIn, dtype);
  }

  /**
   * Scatter the data from the input value into specific TensorArray elements.
   *  {@code indices} must be a vector, its length must match the first dim of {@code value}.
   *
   * @param handle The handle to a TensorArray.
   * @param indices The locations at which to write the tensor elements.
   * @param value The concatenated tensor to write to the TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @return a new instance of TensorArrayScatter
   */
  public TensorArrayScatter tensorArrayScatter(Operand<? extends TType> handle,
      Operand<TInt32> indices, Operand<? extends TType> value, Operand<TFloat32> flowIn) {
    return TensorArrayScatter.create(scope, handle, indices, value, flowIn);
  }

  /**
   * Get the current size of the TensorArray.
   *
   * @param handle The handle to a TensorArray (output of TensorArray or TensorArrayGrad).
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @return a new instance of TensorArraySize
   */
  public TensorArraySize tensorArraySize(Operand<? extends TType> handle,
      Operand<TFloat32> flowIn) {
    return TensorArraySize.create(scope, handle, flowIn);
  }

  /**
   * Split the data from the input value into TensorArray elements.
   *  Assuming that {@code lengths} takes on values
   *  <p>{@code (n0, n1, ..., n(T-1))}
   *  <p>and that {@code value} has shape
   *  <p>{@code (n0 + n1 + ... + n(T-1) x d0 x d1 x ...)},
   *  <p>this splits values into a TensorArray with T tensors.
   *  <p>TensorArray index t will be the subtensor of values with starting position
   *  <p>{@code (n0 + n1 + ... + n(t-1), 0, 0, ...)}
   *  <p>and having size
   *  <p>{@code nt x d0 x d1 x ...}
   *
   * @param handle The handle to a TensorArray.
   * @param value The concatenated tensor to write to the TensorArray.
   * @param lengths The vector of lengths, how to split the rows of value into the
   *  TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @return a new instance of TensorArraySplit
   */
  public TensorArraySplit tensorArraySplit(Operand<? extends TType> handle,
      Operand<? extends TType> value, Operand<TInt64> lengths, Operand<TFloat32> flowIn) {
    return TensorArraySplit.create(scope, handle, value, lengths, flowIn);
  }

  /**
   * The TensorArrayUnpack operation
   *
   * @param handle the handle value
   * @param value the value value
   * @param flowIn the flowIn value
   * @return a new instance of TensorArrayUnpack
   */
  public TensorArrayUnpack tensorArrayUnpack(Operand<TString> handle,
      Operand<? extends TType> value, Operand<TFloat32> flowIn) {
    return TensorArrayUnpack.create(scope, handle, value, flowIn);
  }

  /**
   * Push an element onto the tensor_array.
   *
   * @param handle The handle to a TensorArray.
   * @param index The position to write to inside the TensorArray.
   * @param value The tensor to write to the TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @return a new instance of TensorArrayWrite
   */
  public TensorArrayWrite tensorArrayWrite(Operand<? extends TType> handle, Operand<TInt32> index,
      Operand<? extends TType> value, Operand<TFloat32> flowIn) {
    return TensorArrayWrite.create(scope, handle, index, value, flowIn);
  }

  /**
   * Concats all tensors in the list along the 0th dimension.
   *  Requires that all tensors have the same shape except the first dimension.
   *  <p>input_handle: The input list.
   *  element_shape: The shape of the uninitialized elements in the list. If the first
   *  dimension is not -1, it is assumed that all list elements have the same
   *  leading dim.
   *  leading_dims: The list of leading dims of uninitialized list elements. Used if
   *  the leading dim of input_handle.element_shape or the element_shape input arg
   *  is not already set.
   *  tensor: The concated result.
   *  lengths: Output tensor containing sizes of the 0th dimension of tensors in the list, used for computing the gradient.
   *
   * @param <U> data type for {@code tensor} output
   * @param inputHandle the inputHandle value
   * @param elementShape the elementShape value
   * @param leadingDims the leadingDims value
   * @param elementDtype the value of the elementDtype property
   * @param <U> data type for {@code TensorListConcatV2} output and operands
   * @return a new instance of TensorListConcat
   */
  public <U extends TType> TensorListConcat<U> tensorListConcat(
      Operand<? extends TType> inputHandle, Operand<? extends TNumber> elementShape,
      Operand<TInt64> leadingDims, Class<U> elementDtype) {
    return TensorListConcat.create(scope, inputHandle, elementShape, leadingDims, elementDtype);
  }

  /**
   * The TensorListConcatLists operation
   *
   * @param inputA the inputA value
   * @param inputB the inputB value
   * @param elementDtype the value of the elementDtype property
   * @param <T> data type for {@code TensorListConcatLists} output and operands
   * @return a new instance of TensorListConcatLists
   */
  public <T extends TType> TensorListConcatLists tensorListConcatLists(
      Operand<? extends TType> inputA, Operand<? extends TType> inputB, Class<T> elementDtype) {
    return TensorListConcatLists.create(scope, inputA, inputB, elementDtype);
  }

  /**
   * The shape of the elements of the given list, as a tensor.
   *  input_handle: the list
   *  element_shape: the shape of elements of the list
   *
   * @param <T> data type for {@code element_shape} output
   * @param inputHandle the inputHandle value
   * @param shapeType the value of the shapeType property
   * @param <T> data type for {@code TensorListElementShape} output and operands
   * @return a new instance of TensorListElementShape
   */
  public <T extends TNumber> TensorListElementShape<T> tensorListElementShape(
      Operand<? extends TType> inputHandle, Class<T> shapeType) {
    return TensorListElementShape.create(scope, inputHandle, shapeType);
  }

  /**
   * Creates a TensorList which, when stacked, has the value of {@code tensor}.
   *  Each tensor in the result list corresponds to one row of the input tensor.
   *  <p>tensor: The input tensor.
   *  output_handle: The list.
   *
   * @param tensor the tensor value
   * @param elementShape the elementShape value
   * @return a new instance of TensorListFromTensor
   */
  public TensorListFromTensor tensorListFromTensor(Operand<? extends TType> tensor,
      Operand<? extends TNumber> elementShape) {
    return TensorListFromTensor.create(scope, tensor, elementShape);
  }

  /**
   * Creates a Tensor by indexing into the TensorList.
   *  Each row in the produced Tensor corresponds to the element in the TensorList
   *  specified by the given index (see {@code tf.gather}).
   *  <p>input_handle: The input tensor list.
   *  indices: The indices used to index into the list.
   *  values: The tensor.
   *
   * @param <T> data type for {@code values} output
   * @param inputHandle the inputHandle value
   * @param indices the indices value
   * @param elementShape the elementShape value
   * @param elementDtype the value of the elementDtype property
   * @param <T> data type for {@code TensorListGather} output and operands
   * @return a new instance of TensorListGather
   */
  public <T extends TType> TensorListGather<T> tensorListGather(
      Operand<? extends TType> inputHandle, Operand<TInt32> indices, Operand<TInt32> elementShape,
      Class<T> elementDtype) {
    return TensorListGather.create(scope, inputHandle, indices, elementShape, elementDtype);
  }

  /**
   * The TensorListGetItem operation
   *
   * @param <T> data type for {@code item} output
   * @param inputHandle the inputHandle value
   * @param index the index value
   * @param elementShape the elementShape value
   * @param elementDtype the value of the elementDtype property
   * @param <T> data type for {@code TensorListGetItem} output and operands
   * @return a new instance of TensorListGetItem
   */
  public <T extends TType> TensorListGetItem<T> tensorListGetItem(
      Operand<? extends TType> inputHandle, Operand<TInt32> index, Operand<TInt32> elementShape,
      Class<T> elementDtype) {
    return TensorListGetItem.create(scope, inputHandle, index, elementShape, elementDtype);
  }

  /**
   * Returns the number of tensors in the input tensor list.
   *  input_handle: the input list
   *  length: the number of tensors in the list
   *
   * @param inputHandle the inputHandle value
   * @return a new instance of TensorListLength
   */
  public TensorListLength tensorListLength(Operand<? extends TType> inputHandle) {
    return TensorListLength.create(scope, inputHandle);
  }

  /**
   * Returns the last element of the input list as well as a list with all but that element.
   *  Fails if the list is empty.
   *  <p>input_handle: the input list
   *  tensor: the withdrawn last element of the list
   *  element_dtype: the type of elements in the list
   *  element_shape: the shape of the output tensor
   *
   * @param <T> data type for {@code tensor} output
   * @param inputHandle the inputHandle value
   * @param elementShape the elementShape value
   * @param elementDtype the value of the elementDtype property
   * @param <T> data type for {@code TensorListPopBack} output and operands
   * @return a new instance of TensorListPopBack
   */
  public <T extends TType> TensorListPopBack<T> tensorListPopBack(
      Operand<? extends TType> inputHandle, Operand<TInt32> elementShape, Class<T> elementDtype) {
    return TensorListPopBack.create(scope, inputHandle, elementShape, elementDtype);
  }

  /**
   * Returns a list which has the passed-in {@code Tensor} as last element and the other elements of the given list in {@code input_handle}.
   *  tensor: The tensor to put on the list.
   *  input_handle: The old list.
   *  output_handle: A list with the elements of the old list followed by tensor.
   *  element_dtype: the type of elements in the list.
   *  element_shape: a shape compatible with that of elements in the list.
   *
   * @param inputHandle the inputHandle value
   * @param tensor the tensor value
   * @return a new instance of TensorListPushBack
   */
  public TensorListPushBack tensorListPushBack(Operand<? extends TType> inputHandle,
      Operand<? extends TType> tensor) {
    return TensorListPushBack.create(scope, inputHandle, tensor);
  }

  /**
   * The TensorListPushBackBatch operation
   *
   * @param inputHandles the inputHandles value
   * @param tensor the tensor value
   * @return a new instance of TensorListPushBackBatch
   */
  public TensorListPushBackBatch tensorListPushBackBatch(Operand<? extends TType> inputHandles,
      Operand<? extends TType> tensor) {
    return TensorListPushBackBatch.create(scope, inputHandles, tensor);
  }

  /**
   * List of the given size with empty elements.
   *  element_shape: the shape of the future elements of the list
   *  num_elements: the number of elements to reserve
   *  handle: the output list
   *  element_dtype: the desired type of elements in the list.
   *
   * @param elementShape the elementShape value
   * @param numElements the numElements value
   * @param elementDtype the value of the elementDtype property
   * @param <U> data type for {@code TensorListReserve} output and operands
   * @return a new instance of TensorListReserve
   */
  public <U extends TType> TensorListReserve tensorListReserve(
      Operand<? extends TNumber> elementShape, Operand<TInt32> numElements, Class<U> elementDtype) {
    return TensorListReserve.create(scope, elementShape, numElements, elementDtype);
  }

  /**
   * Resizes the list.
   *  input_handle: the input list
   *  size: size of the output list
   *
   * @param inputHandle the inputHandle value
   * @param sizeOutput the sizeOutput value
   * @return a new instance of TensorListResize
   */
  public TensorListResize tensorListResize(Operand<? extends TType> inputHandle,
      Operand<TInt32> sizeOutput) {
    return TensorListResize.create(scope, inputHandle, sizeOutput);
  }

  /**
   * Creates a TensorList by indexing into a Tensor.
   *  Each member of the TensorList corresponds to one row of the input tensor,
   *  specified by the given index (see {@code tf.gather}).
   *  <p>tensor: The input tensor.
   *  indices: The indices used to index into the list.
   *  element_shape: The shape of the elements in the list (can be less specified than
   *  the shape of the tensor).
   *  num_elements: The size of the output list. Must be large enough to accommodate
   *  the largest index in indices. If -1, the list is just large enough to include
   *  the largest index in indices.
   *  output_handle: The TensorList.
   *
   * @param tensor the tensor value
   * @param indices the indices value
   * @param elementShape the elementShape value
   * @param numElements the numElements value
   * @return a new instance of TensorListScatter
   */
  public TensorListScatter tensorListScatter(Operand<? extends TType> tensor,
      Operand<TInt32> indices, Operand<? extends TNumber> elementShape,
      Operand<TInt32> numElements) {
    return TensorListScatter.create(scope, tensor, indices, elementShape, numElements);
  }

  /**
   * Scatters tensor at indices in an input list.
   *  Each member of the TensorList corresponds to one row of the input tensor,
   *  specified by the given index (see {@code tf.gather}).
   *  <p>input_handle: The list to scatter into.
   *  tensor: The input tensor.
   *  indices: The indices used to index into the list.
   *  output_handle: The TensorList.
   *
   * @param inputHandle the inputHandle value
   * @param tensor the tensor value
   * @param indices the indices value
   * @return a new instance of TensorListScatterIntoExistingList
   */
  public TensorListScatterIntoExistingList tensorListScatterIntoExistingList(
      Operand<? extends TType> inputHandle, Operand<? extends TType> tensor,
      Operand<TInt32> indices) {
    return TensorListScatterIntoExistingList.create(scope, inputHandle, tensor, indices);
  }

  /**
   * The TensorListSetItem operation
   *
   * @param inputHandle the inputHandle value
   * @param index the index value
   * @param item the item value
   * @return a new instance of TensorListSetItem
   */
  public TensorListSetItem tensorListSetItem(Operand<? extends TType> inputHandle,
      Operand<TInt32> index, Operand<? extends TType> item) {
    return TensorListSetItem.create(scope, inputHandle, index, item);
  }

  /**
   * Splits a tensor into a list.
   *  list[i] corresponds to lengths[i] tensors from the input tensor.
   *  The tensor must have rank at least 1 and contain exactly sum(lengths) elements.
   *  <p>tensor: The input tensor.
   *  element_shape: A shape compatible with that of elements in the tensor.
   *  lengths: Vector of sizes of the 0th dimension of tensors in the list.
   *  output_handle: The list.
   *
   * @param tensor the tensor value
   * @param elementShape the elementShape value
   * @param lengths the lengths value
   * @return a new instance of TensorListSplit
   */
  public TensorListSplit tensorListSplit(Operand<? extends TType> tensor,
      Operand<? extends TNumber> elementShape, Operand<TInt64> lengths) {
    return TensorListSplit.create(scope, tensor, elementShape, lengths);
  }

  /**
   * Stacks all tensors in the list.
   *  Requires that all tensors have the same shape.
   *  <p>input_handle: the input list
   *  tensor: the gathered result
   *  num_elements: optional. If not -1, the number of elements in the list.
   *
   * @param <T> data type for {@code tensor} output
   * @param inputHandle the inputHandle value
   * @param elementShape the elementShape value
   * @param elementDtype the value of the elementDtype property
   * @param options carries optional attribute values
   * @param <T> data type for {@code TensorListStack} output and operands
   * @return a new instance of TensorListStack
   */
  public <T extends TType> TensorListStack<T> tensorListStack(Operand<? extends TType> inputHandle,
      Operand<TInt32> elementShape, Class<T> elementDtype, TensorListStack.Options... options) {
    return TensorListStack.create(scope, inputHandle, elementShape, elementDtype, options);
  }

  /**
   * Returns a tensor map with item from given key erased.
   *  input_handle: the original map
   *  output_handle: the map with value from given key removed
   *  key: the key of the value to be erased
   *
   * @param inputHandle the inputHandle value
   * @param key the key value
   * @param valueDtype the value of the valueDtype property
   * @param <U> data type for {@code TensorMapErase} output and operands
   * @return a new instance of TensorMapErase
   */
  public <U extends TType> TensorMapErase tensorMapErase(Operand<? extends TType> inputHandle,
      Operand<? extends TType> key, Class<U> valueDtype) {
    return TensorMapErase.create(scope, inputHandle, key, valueDtype);
  }

  /**
   * Returns whether the given key exists in the map.
   *  input_handle: the input map
   *  key: the key to check
   *  has_key: whether the key is already in the map or not
   *
   * @param inputHandle the inputHandle value
   * @param key the key value
   * @return a new instance of TensorMapHasKey
   */
  public TensorMapHasKey tensorMapHasKey(Operand<? extends TType> inputHandle,
      Operand<? extends TType> key) {
    return TensorMapHasKey.create(scope, inputHandle, key);
  }

  /**
   * Returns a map that is the 'input_handle' with the given key-value pair inserted.
   *  input_handle: the original map
   *  output_handle: the map with key and value inserted
   *  key: the key to be inserted
   *  value: the value to be inserted
   *
   * @param inputHandle the inputHandle value
   * @param key the key value
   * @param value the value value
   * @return a new instance of TensorMapInsert
   */
  public TensorMapInsert tensorMapInsert(Operand<? extends TType> inputHandle,
      Operand<? extends TType> key, Operand<? extends TType> value) {
    return TensorMapInsert.create(scope, inputHandle, key, value);
  }

  /**
   * Returns the value from a given key in a tensor map.
   *  input_handle: the input map
   *  key: the key to be looked up
   *  value: the value found from the given key
   *
   * @param <U> data type for {@code value} output
   * @param inputHandle the inputHandle value
   * @param key the key value
   * @param valueDtype the value of the valueDtype property
   * @param <U> data type for {@code TensorMapLookup} output and operands
   * @return a new instance of TensorMapLookup
   */
  public <U extends TType> TensorMapLookup<U> tensorMapLookup(Operand<? extends TType> inputHandle,
      Operand<? extends TType> key, Class<U> valueDtype) {
    return TensorMapLookup.create(scope, inputHandle, key, valueDtype);
  }

  /**
   * Returns the number of tensors in the input tensor map.
   *  input_handle: the input map
   *  size: the number of tensors in the map
   *
   * @param inputHandle the inputHandle value
   * @return a new instance of TensorMapSize
   */
  public TensorMapSize tensorMapSize(Operand<? extends TType> inputHandle) {
    return TensorMapSize.create(scope, inputHandle);
  }

  /**
   * Returns a Tensor stack of all keys in a tensor map.
   *  input_handle: the input map
   *  keys: the returned Tensor of all keys in the map
   *
   * @param <T> data type for {@code keys} output
   * @param inputHandle the inputHandle value
   * @param keyDtype the value of the keyDtype property
   * @param <T> data type for {@code TensorMapStackKeys} output and operands
   * @return a new instance of TensorMapStackKeys
   */
  public <T extends TType> TensorMapStackKeys<T> tensorMapStackKeys(
      Operand<? extends TType> inputHandle, Class<T> keyDtype) {
    return TensorMapStackKeys.create(scope, inputHandle, keyDtype);
  }

  /**
   * Adds sparse {@code updates} to an existing tensor according to {@code indices}.
   *  This operation creates a new tensor by adding sparse {@code updates} to the passed
   *  in {@code tensor}.
   *  This operation is very similar to {@code tf.scatter_nd_add}, except that the updates
   *  are added onto an existing tensor (as opposed to a variable). If the memory
   *  for the existing tensor cannot be re-used, a copy is made and updated.
   *  <p>{@code indices} is an integer tensor containing indices into a new tensor of shape
   *  {@code tensor.shape}.  The last dimension of {@code indices} can be at most the rank of
   *  {@code tensor.shape}:
   *  <pre>
   *  indices.shape[-1] &lt;= tensor.shape.rank
   *  </pre>
   *  <p>The last dimension of {@code indices} corresponds to indices into elements
   *  (if {@code indices.shape[-1] = tensor.shape.rank}) or slices
   *  (if {@code indices.shape[-1] < tensor.shape.rank}) along dimension
   *  {@code indices.shape[-1]} of {@code tensor.shape}.  {@code updates} is a tensor with shape
   *  <pre>
   *  indices.shape[:-1] + tensor.shape[indices.shape[-1]:]
   *  </pre>
   *  <p>The simplest form of tensor_scatter_add is to add individual elements to a
   *  tensor by index. For example, say we want to add 4 elements in a rank-1
   *  tensor with 8 elements.
   *  <p>In Python, this scatter add operation would look like this:
   *  <pre>
   *      indices = tf.constant([[4], [3], [1], [7]])
   *      updates = tf.constant([9, 10, 11, 12])
   *      tensor = tf.ones([8], dtype=tf.int32)
   *      updated = tf.tensor_scatter_nd_add(tensor, indices, updates)
   *      print(updated)
   *  </pre>
   *  <p>The resulting tensor would look like this:
   *  <pre>
   *  [1, 12, 1, 11, 10, 1, 1, 13]
   *  </pre>
   *  <p>We can also, insert entire slices of a higher rank tensor all at once. For
   *  example, if we wanted to insert two slices in the first dimension of a
   *  rank-3 tensor with two matrices of new values.
   *  <p>In Python, this scatter add operation would look like this:
   *  <pre>
   *      indices = tf.constant([[0], [2]])
   *      updates = tf.constant([[[5, 5, 5, 5], [6, 6, 6, 6],
   *                              [7, 7, 7, 7], [8, 8, 8, 8]],
   *                             [[5, 5, 5, 5], [6, 6, 6, 6],
   *                              [7, 7, 7, 7], [8, 8, 8, 8]]])
   *      tensor = tf.ones([4, 4, 4],dtype=tf.int32)
   *      updated = tf.tensor_scatter_nd_add(tensor, indices, updates)
   *      print(updated)
   *  </pre>
   *  <p>The resulting tensor would look like this:
   *  <pre>
   *  [[[6, 6, 6, 6], [7, 7, 7, 7], [8, 8, 8, 8], [9, 9, 9, 9]],
   *   [[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]],
   *   [[6, 6, 6, 6], [7, 7, 7, 7], [8, 8, 8, 8], [9, 9, 9, 9]],
   *   [[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]]]
   *  </pre>
   *  <p>Note that on CPU, if an out of bound index is found, an error is returned.
   *  On GPU, if an out of bound index is found, the index is ignored.
   *
   * @param <T> data type for {@code output} output
   * @param tensor Tensor to copy/update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @param <T> data type for {@code TensorScatterAdd} output and operands
   * @return a new instance of TensorScatterNdAdd
   */
  public <T extends TType> TensorScatterNdAdd<T> tensorScatterNdAdd(Operand<T> tensor,
      Operand<? extends TNumber> indices, Operand<T> updates) {
    return TensorScatterNdAdd.create(scope, tensor, indices, updates);
  }

  /**
   * The TensorScatterMax operation
   *
   * @param <T> data type for {@code output} output
   * @param tensor Tensor to update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @param <T> data type for {@code TensorScatterMax} output and operands
   * @return a new instance of TensorScatterNdMax
   */
  public <T extends TType> TensorScatterNdMax<T> tensorScatterNdMax(Operand<T> tensor,
      Operand<? extends TNumber> indices, Operand<T> updates) {
    return TensorScatterNdMax.create(scope, tensor, indices, updates);
  }

  /**
   * The TensorScatterMin operation
   *
   * @param <T> data type for {@code output} output
   * @param tensor Tensor to update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @param <T> data type for {@code TensorScatterMin} output and operands
   * @return a new instance of TensorScatterNdMin
   */
  public <T extends TType> TensorScatterNdMin<T> tensorScatterNdMin(Operand<T> tensor,
      Operand<? extends TNumber> indices, Operand<T> updates) {
    return TensorScatterNdMin.create(scope, tensor, indices, updates);
  }

  /**
   * Subtracts sparse {@code updates} from an existing tensor according to {@code indices}.
   *  This operation creates a new tensor by subtracting sparse {@code updates} from the
   *  passed in {@code tensor}.
   *  This operation is very similar to {@code tf.scatter_nd_sub}, except that the updates
   *  are subtracted from an existing tensor (as opposed to a variable). If the memory
   *  for the existing tensor cannot be re-used, a copy is made and updated.
   *  <p>{@code indices} is an integer tensor containing indices into a new tensor of shape
   *  {@code shape}.  The last dimension of {@code indices} can be at most the rank of {@code shape}:
   *  <pre>
   *  indices.shape[-1] &lt;= shape.rank
   *  </pre>
   *  <p>The last dimension of {@code indices} corresponds to indices into elements
   *  (if {@code indices.shape[-1] = shape.rank}) or slices
   *  (if {@code indices.shape[-1] < shape.rank}) along dimension {@code indices.shape[-1]} of
   *  {@code shape}.  {@code updates} is a tensor with shape
   *  <pre>
   *  indices.shape[:-1] + shape[indices.shape[-1]:]
   *  </pre>
   *  <p>The simplest form of tensor_scatter_sub is to subtract individual elements
   *  from a tensor by index. For example, say we want to insert 4 scattered elements
   *  in a rank-1 tensor with 8 elements.
   *  <p>In Python, this scatter subtract operation would look like this:
   *  <pre>
   *      indices = tf.constant([[4], [3], [1], [7]])
   *      updates = tf.constant([9, 10, 11, 12])
   *      tensor = tf.ones([8], dtype=tf.int32)
   *      updated = tf.tensor_scatter_nd_sub(tensor, indices, updates)
   *      print(updated)
   *  </pre>
   *  <p>The resulting tensor would look like this:
   *  <pre>
   *  [1, -10, 1, -9, -8, 1, 1, -11]
   *  </pre>
   *  <p>We can also, insert entire slices of a higher rank tensor all at once. For
   *  example, if we wanted to insert two slices in the first dimension of a
   *  rank-3 tensor with two matrices of new values.
   *  <p>In Python, this scatter add operation would look like this:
   *  <pre>
   *      indices = tf.constant([[0], [2]])
   *      updates = tf.constant([[[5, 5, 5, 5], [6, 6, 6, 6],
   *                              [7, 7, 7, 7], [8, 8, 8, 8]],
   *                             [[5, 5, 5, 5], [6, 6, 6, 6],
   *                              [7, 7, 7, 7], [8, 8, 8, 8]]])
   *      tensor = tf.ones([4, 4, 4],dtype=tf.int32)
   *      updated = tf.tensor_scatter_nd_sub(tensor, indices, updates)
   *      print(updated)
   *  </pre>
   *  <p>The resulting tensor would look like this:
   *  <pre>
   *  [[[-4, -4, -4, -4], [-5, -5, -5, -5], [-6, -6, -6, -6], [-7, -7, -7, -7]],
   *   [[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]],
   *   [[-4, -4, -4, -4], [-5, -5, -5, -5], [-6, -6, -6, -6], [-7, -7, -7, -7]],
   *   [[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]]]
   *  </pre>
   *  <p>Note that on CPU, if an out of bound index is found, an error is returned.
   *  On GPU, if an out of bound index is found, the index is ignored.
   *
   * @param <T> data type for {@code output} output
   * @param tensor Tensor to copy/update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @param <T> data type for {@code TensorScatterSub} output and operands
   * @return a new instance of TensorScatterNdSub
   */
  public <T extends TType> TensorScatterNdSub<T> tensorScatterNdSub(Operand<T> tensor,
      Operand<? extends TNumber> indices, Operand<T> updates) {
    return TensorScatterNdSub.create(scope, tensor, indices, updates);
  }

  /**
   * Scatter {@code updates} into an existing tensor according to {@code indices}.
   *  This operation creates a new tensor by applying sparse {@code updates} to the passed
   *  in {@code tensor}.
   *  This operation is very similar to {@code tf.scatter_nd}, except that the updates are
   *  scattered onto an existing tensor (as opposed to a zero-tensor). If the memory
   *  for the existing tensor cannot be re-used, a copy is made and updated.
   *  <p>If {@code indices} contains duplicates, then we pick the last update for the index.
   *  <p>If an out of bound index is found on CPU, an error is returned.
   *  <p><strong>WARNING</strong>: There are some GPU specific semantics for this operation.
   *  <ul>
   *  <li>If an out of bound index is found, the index is ignored.</li>
   *  <li>The order in which updates are applied is nondeterministic, so the output
   *  will be nondeterministic if {@code indices} contains duplicates.</li>
   *  </ul>
   *  <p>{@code indices} is an integer tensor containing indices into a new tensor of shape
   *  {@code shape}.
   *  <ul>
   *  <li>{@code indices} must have at least 2 axes: {@code (num_updates, index_depth)}.</li>
   *  <li>The last axis of {@code indices} is how deep to index into {@code tensor} so  this index
   *  depth must be less than the rank of {@code tensor}: {@code indices.shape[-1] <= tensor.ndim}</li>
   *  </ul>
   *  <p>if {@code indices.shape[-1] = tensor.rank} this Op indexes and updates scalar elements.
   *  if {@code indices.shape[-1] < tensor.rank} it indexes and updates slices of the input
   *  {@code tensor}.
   *  <p>Each {@code update} has a rank of {@code tensor.rank - indices.shape[-1]}.
   *  The overall shape of {@code updates} is:
   *  <pre>
   *  indices.shape[:-1] + tensor.shape[indices.shape[-1]:]
   *  </pre>
   *  <p>For usage examples see the python  tf.tensor_scatter_nd_update {@link org.tensorflow.op.Ops#tensorScatterNdUpdate}  function
   *
   * @param <T> data type for {@code output} output
   * @param tensor Tensor to copy/update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @param <T> data type for {@code TensorScatterUpdate} output and operands
   * @return a new instance of TensorScatterNdUpdate
   */
  public <T extends TType> TensorScatterNdUpdate<T> tensorScatterNdUpdate(Operand<T> tensor,
      Operand<? extends TNumber> indices, Operand<T> updates) {
    return TensorScatterNdUpdate.create(scope, tensor, indices, updates);
  }

  /**
   * Assign {@code value} to the sliced l-value reference of {@code input}.
   *  The values of {@code value} are assigned to the positions in the tensor {@code input} that
   *  are selected by the slice parameters. The slice parameters {@code begin} {@code end}
   *  {@code strides} etc. work exactly as in {@code StridedSlice}.
   *  <p>NOTE this op currently does not support broadcasting and so {@code value}'s shape
   *  must be exactly the shape produced by the slice of {@code input}.
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param begin the begin value
   * @param end the end value
   * @param strides the strides value
   * @param value the value value
   * @param options carries optional attribute values
   * @param <T> data type for {@code TensorStridedSliceUpdate} output and operands
   * @param <U> data type for {@code TensorStridedSliceUpdate} output and operands
   * @return a new instance of TensorStridedSliceUpdate
   */
  public <T extends TType, U extends TNumber> TensorStridedSliceUpdate<T> tensorStridedSliceUpdate(
      Operand<T> input, Operand<U> begin, Operand<U> end, Operand<U> strides, Operand<T> value,
      TensorStridedSliceUpdate.Options... options) {
    return TensorStridedSliceUpdate.create(scope, input, begin, end, strides, value, options);
  }

  /**
   * Constructs a tensor by tiling a given tensor.
   *  This operation creates a new tensor by replicating {@code input} {@code multiples} times.
   *  The output tensor's i'th dimension has {@code input.dims(i) * multiples[i]} elements,
   *  and the values of {@code input} are replicated {@code multiples[i]} times along the 'i'th
   *  dimension. For example, tiling {@code [a b c d]} by {@code [2]} produces
   *  {@code [a b c d a b c d]}.
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>a = tf.constant([[1,2,3],[4,5,6]], tf.int32)
   *  b = tf.constant([1,2], tf.int32)
   *  tf.tile(a, b)
   *  &lt;tf.Tensor: shape=(2, 6), dtype=int32, numpy=
   *  array([[1, 2, 3, 1, 2, 3],
   *  [4, 5, 6, 4, 5, 6]], dtype=int32)&gt;
   *  c = tf.constant([2,1], tf.int32)
   *  tf.tile(a, c)
   *  &lt;tf.Tensor: shape=(4, 3), dtype=int32, numpy=
   *  array([[1, 2, 3],
   *  [4, 5, 6],
   *  [1, 2, 3],
   *  [4, 5, 6]], dtype=int32)&gt;
   *  d = tf.constant([2,2], tf.int32)
   *  tf.tile(a, d)
   *  &lt;tf.Tensor: shape=(4, 6), dtype=int32, numpy=
   *  array([[1, 2, 3, 1, 2, 3],
   *  [4, 5, 6, 4, 5, 6],
   *  [1, 2, 3, 1, 2, 3],
   *  [4, 5, 6, 4, 5, 6]], dtype=int32)&gt;
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param <T> data type for {@code output} output
   * @param input 1-D or higher.
   * @param multiples 1-D. Length must be the same as the number of dimensions in {@code input}
   * @param <T> data type for {@code Tile} output and operands
   * @return a new instance of Tile
   */
  public <T extends TType> Tile<T> tile(Operand<T> input, Operand<? extends TNumber> multiples) {
    return Tile.create(scope, input, multiples);
  }

  /**
   * Provides the time since epoch in seconds.
   *  Returns the timestamp as a {@code float64} for seconds since the Unix epoch.
   *  <p>Note: the timestamp is computed when the op is executed, not when it is added
   *  to the graph.
   *
   * @return a new instance of Timestamp
   */
  public Timestamp timestamp() {
    return Timestamp.create(scope);
  }

  /**
   * Returns the TopK unique values in the array in sorted order.
   *  The running time is proportional to the product of K and the input
   *  size. Sorting the whole array is more efficient for sufficiently large
   *  values of K. The median-of-medians algorithm is probably faster, but
   *  difficult to implement efficiently in XLA. If there are fewer than K
   *  unique numbers (not NANs), the results are padded with negative
   *  infinity. NaNs are never returned. Subnormal numbers are flushed to
   *  zero. If an element appears at multiple indices, the highest index is
   *  returned. If a TopK element never appears in the input due to padding
   *  values, the indices are padded with negative one. If a padding value
   *  appears in the input and padding is needed, the highest index of the
   *  padding value will be returned. The semantics are not the same as
   *  kth_order_statistic.
   *
   * @param input the input value
   * @param k the value of the k property
   * @return a new instance of TopKUnique
   */
  public TopKUnique topKUnique(Operand<TFloat32> input, Long k) {
    return TopKUnique.create(scope, input, k);
  }

  /**
   * Returns the TopK values in the array in sorted order.
   *  This is a combination of MakeUnique and TopKUnique. The returned top-K will
   *  have its lower bits replaced by iota, thus it will be close to the original
   *  value but not exactly the same. The running time is proportional to the product
   *  of K and the input size. NaNs are never returned. Subnormal numbers are flushed
   *  to zero.
   *
   * @param input the input value
   * @param k the value of the k property
   * @return a new instance of TopKWithUnique
   */
  public TopKWithUnique topKWithUnique(Operand<TFloat32> input, Long k) {
    return TopKWithUnique.create(scope, input, k);
  }

  /**
   * Reverses the operation of Batch for a single output Tensor.
   *  An instance of Unbatch either receives an empty batched_tensor, in which case it
   *  asynchronously waits until the values become available from a concurrently
   *  running instance of Unbatch with the same container and shared_name, or receives
   *  a non-empty batched_tensor in which case it finalizes all other concurrently
   *  running instances and outputs its own element from the batch.
   *  <p>batched_tensor: The possibly transformed output of Batch. The size of the first
   *  dimension should remain unchanged by the transformations for the operation to
   *  work.
   *  batch_index: The matching batch_index obtained from Batch.
   *  id: The id scalar emitted by Batch.
   *  unbatched_tensor: The Tensor corresponding to this execution.
   *  timeout_micros: Maximum amount of time (in microseconds) to wait to receive the
   *  batched input tensor associated with a given invocation of the op.
   *  container: Container to control resource sharing.
   *  shared_name: Instances of Unbatch with the same container and shared_name are
   *  assumed to possibly belong to the same batch. If left empty, the op name will
   *  be used as the shared name.
   *
   * @param <T> data type for {@code unbatched_tensor} output
   * @param batchedTensor the batchedTensor value
   * @param batchIndex the batchIndex value
   * @param id the id value
   * @param timeoutMicros the value of the timeoutMicros property
   * @param options carries optional attribute values
   * @param <T> data type for {@code Unbatch} output and operands
   * @return a new instance of Unbatch
   */
  public <T extends TType> Unbatch<T> unbatch(Operand<T> batchedTensor, Operand<TInt64> batchIndex,
      Operand<TInt64> id, Long timeoutMicros, Unbatch.Options... options) {
    return Unbatch.create(scope, batchedTensor, batchIndex, id, timeoutMicros, options);
  }

  /**
   * Gradient of Unbatch.
   *  Acts like Batch but using the given batch_index index of batching things as they
   *  become available. This ensures that the gradients are propagated back in the
   *  same session which did the forward pass.
   *  <p>original_input: The input to the Unbatch operation this is the gradient of.
   *  batch_index: The batch_index given to the Unbatch operation this is the gradient
   *  of.
   *  grad: The downstream gradient.
   *  id: The id scalar emitted by Batch.
   *  batched_grad: The return value, either an empty tensor or the batched gradient.
   *  container: Container to control resource sharing.
   *  shared_name: Instances of UnbatchGrad with the same container and shared_name
   *  are assumed to possibly belong to the same batch. If left empty, the op name
   *  will be used as the shared name.
   *
   * @param <T> data type for {@code batched_grad} output
   * @param originalInput the originalInput value
   * @param batchIndex the batchIndex value
   * @param grad the grad value
   * @param id the id value
   * @param options carries optional attribute values
   * @param <T> data type for {@code UnbatchGrad} output and operands
   * @return a new instance of UnbatchGrad
   */
  public <T extends TType> UnbatchGrad<T> unbatchGrad(Operand<T> originalInput,
      Operand<TInt64> batchIndex, Operand<T> grad, Operand<TInt64> id,
      UnbatchGrad.Options... options) {
    return UnbatchGrad.create(scope, originalInput, batchIndex, grad, id, options);
  }

  /**
   * Finds unique elements along an axis of a tensor.
   *  This operation either returns a tensor {@code y} containing unique elements
   *  along the {@code axis} of a tensor. The returned unique elements is sorted
   *  in the same order as they occur along {@code axis} in {@code x}.
   *  This operation also returns a tensor {@code idx} that is the same size as
   *  the number of the elements in {@code x} along the {@code axis} dimension. It
   *  contains the index in the unique output {@code y}.
   *  In other words, for an {@code 1-D} tensor {@code x} with `axis = None:
   *  <p>{@code y[idx[i]] = x[i] for i in [0, 1,...,rank(x) - 1]}
   *  <p>For example:
   *  <pre>
   *  # tensor 'x' is [1, 1, 2, 4, 4, 4, 7, 8, 8]
   *  y, idx = unique(x)
   *  y ==&gt; [1, 2, 4, 7, 8]
   *  idx ==&gt; [0, 0, 1, 2, 2, 2, 3, 4, 4]
   *  </pre>
   *  <p>For an {@code 2-D} tensor {@code x} with {@code axis = 0}:
   *  <pre>
   *  # tensor 'x' is [[1, 0, 0],
   *  #                [1, 0, 0],
   *  #                [2, 0, 0]]
   *  y, idx = unique(x, axis=0)
   *  y ==&gt; [[1, 0, 0],
   *         [2, 0, 0]]
   *  idx ==&gt; [0, 0, 1]
   *  </pre>
   *  <p>For an {@code 2-D} tensor {@code x} with {@code axis = 1}:
   *  <pre>
   *  # tensor 'x' is [[1, 0, 0],
   *  #                [1, 0, 0],
   *  #                [2, 0, 0]]
   *  y, idx = unique(x, axis=1)
   *  y ==&gt; [[1, 0],
   *         [1, 0],
   *         [2, 0]]
   *  idx ==&gt; [0, 1, 1]
   *  </pre>
   *
   * @param <T> data type for {@code y} output
   * @param <V> data type for {@code idx} output
   * @param x A {@code Tensor}.
   * @param axis A {@code Tensor} of type {@code int32} (default: None). The axis of the Tensor to
   *  find the unique elements.
   * @param <T> data type for {@code UniqueV2} output and operands
   * @return a new instance of Unique, with default output types
   */
  public <T extends TType> Unique<T, TInt32> unique(Operand<T> x, Operand<? extends TNumber> axis) {
    return Unique.create(scope, x, axis);
  }

  /**
   * Finds unique elements along an axis of a tensor.
   *  This operation either returns a tensor {@code y} containing unique elements
   *  along the {@code axis} of a tensor. The returned unique elements is sorted
   *  in the same order as they occur along {@code axis} in {@code x}.
   *  This operation also returns a tensor {@code idx} that is the same size as
   *  the number of the elements in {@code x} along the {@code axis} dimension. It
   *  contains the index in the unique output {@code y}.
   *  In other words, for an {@code 1-D} tensor {@code x} with `axis = None:
   *  <p>{@code y[idx[i]] = x[i] for i in [0, 1,...,rank(x) - 1]}
   *  <p>For example:
   *  <pre>
   *  # tensor 'x' is [1, 1, 2, 4, 4, 4, 7, 8, 8]
   *  y, idx = unique(x)
   *  y ==&gt; [1, 2, 4, 7, 8]
   *  idx ==&gt; [0, 0, 1, 2, 2, 2, 3, 4, 4]
   *  </pre>
   *  <p>For an {@code 2-D} tensor {@code x} with {@code axis = 0}:
   *  <pre>
   *  # tensor 'x' is [[1, 0, 0],
   *  #                [1, 0, 0],
   *  #                [2, 0, 0]]
   *  y, idx = unique(x, axis=0)
   *  y ==&gt; [[1, 0, 0],
   *         [2, 0, 0]]
   *  idx ==&gt; [0, 0, 1]
   *  </pre>
   *  <p>For an {@code 2-D} tensor {@code x} with {@code axis = 1}:
   *  <pre>
   *  # tensor 'x' is [[1, 0, 0],
   *  #                [1, 0, 0],
   *  #                [2, 0, 0]]
   *  y, idx = unique(x, axis=1)
   *  y ==&gt; [[1, 0],
   *         [1, 0],
   *         [2, 0]]
   *  idx ==&gt; [0, 1, 1]
   *  </pre>
   *
   * @param <T> data type for {@code y} output
   * @param <V> data type for {@code idx} output
   * @param x A {@code Tensor}.
   * @param axis A {@code Tensor} of type {@code int32} (default: None). The axis of the Tensor to
   *  find the unique elements.
   * @param outIdx the value of the outIdx property
   * @param <T> data type for {@code UniqueV2} output and operands
   * @param <V> data type for {@code UniqueV2} output and operands
   * @return a new instance of Unique
   */
  public <T extends TType, V extends TNumber> Unique<T, V> unique(Operand<T> x,
      Operand<? extends TNumber> axis, Class<V> outIdx) {
    return Unique.create(scope, x, axis, outIdx);
  }

  /**
   * Finds unique elements along an axis of a tensor.
   *  This operation either returns a tensor {@code y} containing unique elements
   *  along the {@code axis} of a tensor. The returned unique elements is sorted
   *  in the same order as they occur along {@code axis} in {@code x}.
   *  This operation also returns a tensor {@code idx} and a tensor {@code count}
   *  that are the same size as the number of the elements in {@code x} along the
   *  {@code axis} dimension. The {@code idx} contains the index in the unique output {@code y}
   *  and the {@code count} contains the count in the unique output {@code y}.
   *  In other words, for an {@code 1-D} tensor {@code x} with `axis = None:
   *  <p>{@code y[idx[i]] = x[i] for i in [0, 1,...,rank(x) - 1]}
   *  <p>For example:
   *  <pre>
   *  x = tf.constant([1, 1, 2, 4, 4, 4, 7, 8, 8])
   *  y, idx, count = UniqueWithCountsV2(x, axis = [0])
   *  y ==&gt; [1, 2, 4, 7, 8]
   *  idx ==&gt; [0, 0, 1, 2, 2, 2, 3, 4, 4]
   *  count ==&gt; [2, 1, 3, 1, 2]
   *  </pre>
   *  <p>For a {@code 2-D} tensor {@code x} with {@code axis = 0}:
   *  <pre>
   *  x = tf.constant([[1, 0, 0],
   *                  [1, 0, 0],
   *                  [2, 0, 0]])
   *  y, idx, count = UniqueWithCountsV2(x, axis=[0])
   *  y ==&gt; [[1, 0, 0],
   *         [2, 0, 0]]
   *  idx ==&gt; [0, 0, 1]
   *  count ==&gt; [2, 1]
   *  </pre>
   *  <p>For a {@code 2-D} tensor {@code x} with {@code axis = 1}:
   *  <pre>
   *  x = tf.constant([[1, 0, 0],
   *                  [1, 0, 0],
   *                  [2, 0, 0]])
   *  y, idx, count = UniqueWithCountsV2(x, axis=[1])
   *  y ==&gt; [[1, 0],
   *         [1, 0],
   *         [2, 0]]
   *  idx ==&gt; [0, 1, 1]
   *  count ==&gt; [1, 2]
   *  </pre>
   *
   * @param <T> data type for {@code y} output
   * @param <V> data type for {@code idx} output
   * @param x A {@code Tensor}.
   * @param axis A {@code Tensor} of type {@code int32} (default: None). The axis of the Tensor to
   *  find the unique elements.
   * @param <T> data type for {@code UniqueWithCountsV2} output and operands
   * @return a new instance of UniqueWithCounts, with default output types
   */
  public <T extends TType> UniqueWithCounts<T, TInt32> uniqueWithCounts(Operand<T> x,
      Operand<? extends TNumber> axis) {
    return UniqueWithCounts.create(scope, x, axis);
  }

  /**
   * Finds unique elements along an axis of a tensor.
   *  This operation either returns a tensor {@code y} containing unique elements
   *  along the {@code axis} of a tensor. The returned unique elements is sorted
   *  in the same order as they occur along {@code axis} in {@code x}.
   *  This operation also returns a tensor {@code idx} and a tensor {@code count}
   *  that are the same size as the number of the elements in {@code x} along the
   *  {@code axis} dimension. The {@code idx} contains the index in the unique output {@code y}
   *  and the {@code count} contains the count in the unique output {@code y}.
   *  In other words, for an {@code 1-D} tensor {@code x} with `axis = None:
   *  <p>{@code y[idx[i]] = x[i] for i in [0, 1,...,rank(x) - 1]}
   *  <p>For example:
   *  <pre>
   *  x = tf.constant([1, 1, 2, 4, 4, 4, 7, 8, 8])
   *  y, idx, count = UniqueWithCountsV2(x, axis = [0])
   *  y ==&gt; [1, 2, 4, 7, 8]
   *  idx ==&gt; [0, 0, 1, 2, 2, 2, 3, 4, 4]
   *  count ==&gt; [2, 1, 3, 1, 2]
   *  </pre>
   *  <p>For a {@code 2-D} tensor {@code x} with {@code axis = 0}:
   *  <pre>
   *  x = tf.constant([[1, 0, 0],
   *                  [1, 0, 0],
   *                  [2, 0, 0]])
   *  y, idx, count = UniqueWithCountsV2(x, axis=[0])
   *  y ==&gt; [[1, 0, 0],
   *         [2, 0, 0]]
   *  idx ==&gt; [0, 0, 1]
   *  count ==&gt; [2, 1]
   *  </pre>
   *  <p>For a {@code 2-D} tensor {@code x} with {@code axis = 1}:
   *  <pre>
   *  x = tf.constant([[1, 0, 0],
   *                  [1, 0, 0],
   *                  [2, 0, 0]])
   *  y, idx, count = UniqueWithCountsV2(x, axis=[1])
   *  y ==&gt; [[1, 0],
   *         [1, 0],
   *         [2, 0]]
   *  idx ==&gt; [0, 1, 1]
   *  count ==&gt; [1, 2]
   *  </pre>
   *
   * @param <T> data type for {@code y} output
   * @param <V> data type for {@code idx} output
   * @param x A {@code Tensor}.
   * @param axis A {@code Tensor} of type {@code int32} (default: None). The axis of the Tensor to
   *  find the unique elements.
   * @param outIdx the value of the outIdx property
   * @param <T> data type for {@code UniqueWithCountsV2} output and operands
   * @param <V> data type for {@code UniqueWithCountsV2} output and operands
   * @return a new instance of UniqueWithCounts
   */
  public <T extends TType, V extends TNumber> UniqueWithCounts<T, V> uniqueWithCounts(Operand<T> x,
      Operand<? extends TNumber> axis, Class<V> outIdx) {
    return UniqueWithCounts.create(scope, x, axis, outIdx);
  }

  /**
   * Converts an array of flat indices into a tuple of coordinate arrays.
   *  Example:
   *  <pre>
   *  y = tf.unravel_index(indices=[2, 5, 7], dims=[3, 3])
   *  # 'dims' represent a hypothetical (3, 3) tensor of indices:
   *  # [[0, 1, *2*],
   *  #  [3, 4, *5*],
   *  #  [6, *7*, 8]]
   *  # For each entry from 'indices', this operation returns
   *  # its coordinates (marked with '*'), such as
   *  # 2 ==&gt; (0, 2)
   *  # 5 ==&gt; (1, 2)
   *  # 7 ==&gt; (2, 1)
   *  y ==&gt; [[0, 1, 2], [2, 2, 1]]
   *  </pre>
   *  <p>{@literal @}compatibility(numpy)<br>
   *  Equivalent to np.unravel_index
   *  <br>{@literal @}end_compatibility
   *
   * @param <T> data type for {@code output} output
   * @param indices An 0-D or 1-D {@code int} Tensor whose elements are indices into the
   *  flattened version of an array of dimensions dims.
   * @param dims An 1-D {@code int} Tensor. The shape of the array to use for unraveling
   *  indices.
   * @param <T> data type for {@code UnravelIndex} output and operands
   * @return a new instance of UnravelIndex
   */
  public <T extends TNumber> UnravelIndex<T> unravelIndex(Operand<T> indices, Operand<T> dims) {
    return UnravelIndex.create(scope, indices, dims);
  }

  /**
   * Unpacks a given dimension of a rank-{@code R} tensor into {@code num} rank-{@code (R-1)} tensors.
   *  Unpacks {@code num} tensors from {@code value} by chipping it along the {@code axis} dimension.
   *  For example, given a tensor of shape {@code (A, B, C, D)};
   *  <p>If {@code axis == 0} then the i'th tensor in {@code output} is the slice {@code value[i, :, :, :]}
   *  and each tensor in {@code output} will have shape {@code (B, C, D)}. (Note that the
   *  dimension unpacked along is gone, unlike {@code split}).
   *  <p>If {@code axis == 1} then the i'th tensor in {@code output} is the slice {@code value[:, i, :, :]}
   *  and each tensor in {@code output} will have shape {@code (A, C, D)}.
   *  Etc.
   *  <p>This is the opposite of {@code pack}.
   *
   * @param <T> data type for {@code output} output
   * @param value 1-D or higher, with {@code axis} dimension size equal to {@code num}.
   * @param num the value of the num property
   * @param options carries optional attribute values
   * @param <T> data type for {@code Unpack} output and operands
   * @return a new instance of Unstack
   */
  public <T extends TType> Unstack<T> unstack(Operand<T> value, Long num,
      Unstack.Options... options) {
    return Unstack.create(scope, value, num, options);
  }

  /**
   * Op is similar to a lightweight Dequeue.
   *  The basic functionality is similar to dequeue with many fewer
   *  capabilities and options.  This Op is optimized for performance.
   *
   * @param dtypes the value of the dtypes property
   * @param options carries optional attribute values
   * @return a new instance of Unstage
   */
  public Unstage unstage(List<Class<? extends TType>> dtypes, Unstage.Options... options) {
    return Unstage.create(scope, dtypes, options);
  }

  /**
   * Creates a handle to a Variable resource.
   *
   * @param dtype the type of this variable. Must agree with the dtypes
   *  of all ops using this variable.
   * @param shape The (possibly partially specified) shape of this variable.
   * @param options carries optional attribute values
   * @param <T> data type for {@code VarHandleOp} output and operands
   * @return a new instance of VarHandleOp
   */
  public <T extends TType> VarHandleOp varHandleOp(Class<T> dtype, Shape shape,
      VarHandleOp.Options... options) {
    return VarHandleOp.create(scope, dtype, shape, options);
  }

  /**
   * Checks whether a resource handle-based variable has been initialized.
   *
   * @param resource the input resource handle.
   * @return a new instance of VarIsInitializedOp
   */
  public VarIsInitializedOp varIsInitializedOp(Operand<? extends TType> resource) {
    return VarIsInitializedOp.create(scope, resource);
  }

  /**
   * Factory method to create a new Variable with it's initializer.
   *  <p>
   *  Only supported on Graph sessions as the {@link org.tensorflow.op.core.Assign} op
   *  does not work in an EagerSession.
   *
   * @param init The op to use to initialise this variable.
   * @param options carries optional attributes values
   * @return a new instance of Variable
   */
  public <T extends TType> Variable<T> variable(Operand<T> init, Variable.Options... options) {
    return Helpers.createVariableWithInit(scope, init, options);
  }

  /**
   * Holds state in the form of a tensor that persists across steps.
   *  Outputs a ref to the tensor state so it may be read or modified.
   *  TODO(zhifengc/mrry): Adds a pointer to a more detail document
   *  about sharing states in tensorflow.
   *
   * @param <T> data type for {@code ref} output
   * @param shape The shape of the variable tensor.
   * @param dtype The type of elements in the variable tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code VariableV2} output and operands
   * @return a new instance of Variable
   */
  public <T extends TType> Variable<T> variable(Shape shape, Class<T> dtype,
      Variable.Options... options) {
    return Variable.create(scope, shape, dtype, options);
  }

  /**
   * Returns the shape of the variable pointed to by {@code resource}.
   *  This operation returns a 1-D integer tensor representing the shape of {@code input}.
   *  <p>For example:
   *  <pre>
   *  # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
   *  shape(t) ==&gt; [2, 2, 3]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @return a new instance of VariableShape, with default output types
   */
  public VariableShape<TInt32> variableShape(Operand<? extends TType> input) {
    return VariableShape.create(scope, input);
  }

  /**
   * Returns the shape of the variable pointed to by {@code resource}.
   *  This operation returns a 1-D integer tensor representing the shape of {@code input}.
   *  <p>For example:
   *  <pre>
   *  # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
   *  shape(t) ==&gt; [2, 2, 3]
   *  </pre>
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param outType the value of the outType property
   * @param <T> data type for {@code VariableShape} output and operands
   * @return a new instance of VariableShape
   */
  public <T extends TNumber> VariableShape<T> variableShape(Operand<? extends TType> input,
      Class<T> outType) {
    return VariableShape.create(scope, input, outType);
  }

  /**
   * Returns locations of nonzero / true values in a tensor.
   *  This operation returns the coordinates of true elements in {@code condition}. The
   *  coordinates are returned in a 2-D tensor where the first dimension (rows)
   *  represents the number of true elements, and the second dimension (columns)
   *  represents the coordinates of the true elements. Keep in mind, the shape of
   *  the output tensor can vary depending on how many true values there are in
   *  {@code condition}. Indices are output in row-major order.
   *  <p>For example:
   *  <pre>
   *  # 'input' tensor is [[True, False]
   *  #                    [True, False]]
   *  # 'input' has two true values, so output has two coordinates.
   *  # 'input' has rank of 2, so coordinates have two indices.
   *  where(input) ==&gt; [[0, 0],
   *                    [1, 0]]
   *
   *  # `condition` tensor is [[[True, False]
   *  #                     [True, False]]
   *  #                    [[False, True]
   *  #                     [False, True]]
   *  #                    [[False, False]
   *  #                     [False, True]]]
   *  # 'input' has 5 true values, so output has 5 coordinates.
   *  # 'input' has rank of 3, so coordinates have three indices.
   *  where(input) ==&gt; [[0, 0, 0],
   *                    [0, 1, 0],
   *                    [1, 0, 1],
   *                    [1, 1, 1],
   *                    [2, 1, 1]]
   *
   *  # `condition` tensor is [[[1.5,  0.0]
   *  #                     [-0.5, 0.0]]
   *  #                    [[0.0,  0.25]
   *  #                     [0.0,  0.75]]
   *  #                    [[0.0,  0.0]
   *  #                     [0.0,  0.01]]]
   *  # 'input' has 5 nonzero values, so output has 5 coordinates.
   *  # 'input' has rank of 3, so coordinates have three indices.
   *  where(input) ==&gt; [[0, 0, 0],
   *                    [0, 1, 0],
   *                    [1, 0, 1],
   *                    [1, 1, 1],
   *                    [2, 1, 1]]
   *
   *  # `condition` tensor is [[[1.5 + 0.0j, 0.0  + 0.0j]
   *  #                     [0.0 + 0.5j, 0.0  + 0.0j]]
   *  #                    [[0.0 + 0.0j, 0.25 + 1.5j]
   *  #                     [0.0 + 0.0j, 0.75 + 0.0j]]
   *  #                    [[0.0 + 0.0j, 0.0  + 0.0j]
   *  #                     [0.0 + 0.0j, 0.01 + 0.0j]]]
   *  # 'input' has 5 nonzero magnitude values, so output has 5 coordinates.
   *  # 'input' has rank of 3, so coordinates have three indices.
   *  where(input) ==&gt; [[0, 0, 0],
   *                    [0, 1, 0],
   *                    [1, 0, 1],
   *                    [1, 1, 1],
   *                    [2, 1, 1]]
   *  </pre>
   *
   * @param condition the condition value
   * @return a new instance of Where
   */
  public Where where(Operand<? extends TType> condition) {
    return Where.create(scope, condition);
  }

  /**
   * output = input; While (Cond(output)) { output = Body(output) }
   *
   *  <p>Selects between {@link StatefulWhile} and {@link StatelessWhile} based on the statefulness of the function arguments.
   *
   * @param input A list of input tensors whose types are T.
   * @param cond <pre>
   *    A function takes 'input' and returns a tensor.  If the tensor is
   *    a scalar of non-boolean, the scalar is converted to a boolean
   *    according to the following rule: if the scalar is a numerical
   *    value, non-zero means True and zero means False; if the scalar is
   *    a string, non-empty means True and empty means False. If the
   *    tensor is not a scalar, non-emptiness means True and False
   *    otherwise.
   *  </pre>
   * @param body <pre>
   *    A function that takes a list of tensors and returns another
   *    list of tensors. Both lists have the same types as specified
   *    by T.
   *  </pre>
   * @param options carries optional attribute values
   * @return a new instance of While
   */
  public While whileOp(Iterable<Operand<?>> input, ConcreteFunction cond, ConcreteFunction body,
      While.Options... options) {
    return While.create(scope, input, cond, body, options);
  }

  /**
   * Creates a zeroed tensor given its type and shape.
   *
   * @param dims a 1-D operand that represents the shape of the output tensor
   * @param type the output tensor datatype
   * @return a constant tensor initialized with zeros
   * @throws IllegalArgumentException if the tensor type or shape cannot be initialized with zeros.
   */
  public <T extends TType> Zeros<T> zeros(Operand<? extends TNumber> dims, Class<T> type) {
    return Zeros.create(scope, dims, type);
  }

  /**
   * Returns a tensor of zeros with the same shape and type as x.
   *
   * @param <T> data type for {@code y} output
   * @param x a tensor of type T.
   * @param <T> data type for {@code ZerosLike} output and operands
   * @return a new instance of ZerosLike
   */
  public <T extends TType> ZerosLike<T> zerosLike(Operand<T> x) {
    return ZerosLike.create(scope, x);
  }

  /**
   * Returns an API that builds operations with the provided name prefix.
   *
   * @see {@link Scope#withSubScope(String)}
   */
  public Ops withSubScope(String childScopeName) {
    return new Ops(scope.withSubScope(childScopeName));
  }

  /**
   * Returns an API that uses the provided name for an op.
   *
   * @see {@link Scope#withName(String)}
   */
  public Ops withName(String opName) {
    return new Ops(scope.withName(opName));
  }

  /**
   * Returns an API that places the created operations on the device(s) matching the provided spec.
   *
   * @see {@link Scope#withDevice(DeviceSpec)}
   */
  public Ops withDevice(DeviceSpec deviceSpec) {
    return new Ops(scope.withDevice(deviceSpec));
  }

  /**
   * Returns an API that adds operations to the graph with the provided control dependencies.
   *
   * @see {@link Scope#withControlDependencies(Iterable<Op<?>>)}
   */
  public Ops withControlDependencies(Iterable<Op> controls) {
    return new Ops(scope.withControlDependencies(controls));
  }

  /**
   * Returns the current {@link Scope scope} of this API
   */
  public final Scope scope() {
    return scope;
  }

  /**
   * Creates an API for building operations in the provided execution environment
   */
  public static Ops create(ExecutionEnvironment env) {
    return new Ops(env.baseScope());
  }

  /**
   * Creates an API for building operations in the default eager execution environment
   *
   * <p>Invoking this method is equivalent to {@code Ops.create(EagerSession.getDefault())}.
   */
  public static Ops create() {
    return create(EagerSession.getDefault());
  }
}
