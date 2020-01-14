package org.tensorflow.op;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.charset.Charset;
import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.EagerSession;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Operand;
import org.tensorflow.Tensor;
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
import org.tensorflow.op.core.BatchToSpace;
import org.tensorflow.op.core.BatchToSpaceNd;
import org.tensorflow.op.core.Bitcast;
import org.tensorflow.op.core.BroadcastDynamicShape;
import org.tensorflow.op.core.BroadcastTo;
import org.tensorflow.op.core.Bucketize;
import org.tensorflow.op.core.ClipByValue;
import org.tensorflow.op.core.Concat;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.ConsumeMutexLock;
import org.tensorflow.op.core.ControlTrigger;
import org.tensorflow.op.core.CountUpTo;
import org.tensorflow.op.core.DeepCopy;
import org.tensorflow.op.core.DeleteSessionTensor;
import org.tensorflow.op.core.DestroyResourceOp;
import org.tensorflow.op.core.DestroyTemporaryVariable;
import org.tensorflow.op.core.DynamicPartition;
import org.tensorflow.op.core.DynamicStitch;
import org.tensorflow.op.core.EditDistance;
import org.tensorflow.op.core.Empty;
import org.tensorflow.op.core.EmptyTensorList;
import org.tensorflow.op.core.EnsureShape;
import org.tensorflow.op.core.ExpandDims;
import org.tensorflow.op.core.ExtractVolumePatches;
import org.tensorflow.op.core.Fill;
import org.tensorflow.op.core.Fingerprint;
import org.tensorflow.op.core.Gather;
import org.tensorflow.op.core.GatherNd;
import org.tensorflow.op.core.GetSessionHandle;
import org.tensorflow.op.core.GetSessionTensor;
import org.tensorflow.op.core.Gradients;
import org.tensorflow.op.core.GuaranteeConst;
import org.tensorflow.op.core.HashTable;
import org.tensorflow.op.core.HistogramFixedWidth;
import org.tensorflow.op.core.Identity;
import org.tensorflow.op.core.IdentityN;
import org.tensorflow.op.core.ImmutableConst;
import org.tensorflow.op.core.InitializeTable;
import org.tensorflow.op.core.InitializeTableFromTextFile;
import org.tensorflow.op.core.InplaceAdd;
import org.tensorflow.op.core.InplaceSub;
import org.tensorflow.op.core.InplaceUpdate;
import org.tensorflow.op.core.IsVariableInitialized;
import org.tensorflow.op.core.LinSpace;
import org.tensorflow.op.core.LookupTableExport;
import org.tensorflow.op.core.LookupTableFind;
import org.tensorflow.op.core.LookupTableImport;
import org.tensorflow.op.core.LookupTableInsert;
import org.tensorflow.op.core.LookupTableSize;
import org.tensorflow.op.core.LoopCond;
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
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.PlaceholderWithDefault;
import org.tensorflow.op.core.Print;
import org.tensorflow.op.core.Prod;
import org.tensorflow.op.core.QuantizedConcat;
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
import org.tensorflow.op.core.RemoteFusedGraphExecute;
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
import org.tensorflow.op.core.ResourceScatterNdSub;
import org.tensorflow.op.core.ResourceScatterNdUpdate;
import org.tensorflow.op.core.ResourceScatterSub;
import org.tensorflow.op.core.ResourceScatterUpdate;
import org.tensorflow.op.core.ResourceStridedSliceAssign;
import org.tensorflow.op.core.Reverse;
import org.tensorflow.op.core.ReverseSequence;
import org.tensorflow.op.core.Roll;
import org.tensorflow.op.core.Rpc;
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
import org.tensorflow.op.core.StopGradient;
import org.tensorflow.op.core.StridedSlice;
import org.tensorflow.op.core.StridedSliceAssign;
import org.tensorflow.op.core.StridedSliceGrad;
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
import org.tensorflow.op.core.TensorScatterNdAdd;
import org.tensorflow.op.core.TensorScatterNdSub;
import org.tensorflow.op.core.TensorScatterNdUpdate;
import org.tensorflow.op.core.TensorStridedSliceUpdate;
import org.tensorflow.op.core.Tile;
import org.tensorflow.op.core.Timestamp;
import org.tensorflow.op.core.TryRpc;
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
import org.tensorflow.op.core.Zeros;
import org.tensorflow.op.core.ZerosLike;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TDouble;
import org.tensorflow.types.TFloat;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
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
 *   Ops ops = Ops.create(g);
 *   // Operations are typed classes with convenience
 *   // builders in Ops.
 *   Constant three = ops.constant(3);
 *   // Single-result operations implement the Operand
 *   // interface, so this works too.
 *   Operand four = ops.constant(4);
 *   // Most builders are found within a group, and accept
 *   // Operand types as operands
 *   Operand nine = ops.math.add(four, ops.constant(5));
 *   // Multi-result operations however offer methods to
 *   // select a particular result for use.
 *   Operand result = 
 *       ops.math.add(ops.unique(s, a).y(), b);
 *   // Optional attributes
 *   ops.linalg.matMul(a, b, MatMul.transposeA(true));
 *   // Naming operators
 *   ops.withName("foo").constant(5); // name "foo"
 *   // Names can exist in a hierarchy
 *   Ops sub = ops.withSubScope("sub");
 *   sub.withName("bar").constant(4); // "sub/bar"
 * }
 * }</pre>
 */
public final class Ops {
  public final NnOps nn;

  public final SummaryOps summary;

  public final ImageOps image;

  public final DataOps data;

  public final IoOps io;

  public final DtypesOps dtypes;

  public final LinalgOps linalg;

  public final RandomOps random;

  public final StringsOps strings;

  public final SparseOps sparse;

  public final BitwiseOps bitwise;

  public final MathOps math;

  public final AudioOps audio;

  public final SignalOps signal;

  public final TrainOps train;

  public final QuantizationOps quantization;

  private final Scope scope;

  private Ops(Scope scope) {
    this.scope = scope;
    nn = new NnOps(scope);
    summary = new SummaryOps(scope);
    image = new ImageOps(scope);
    data = new DataOps(scope);
    io = new IoOps(scope);
    dtypes = new DtypesOps(scope);
    linalg = new LinalgOps(scope);
    random = new RandomOps(scope);
    strings = new StringsOps(scope);
    sparse = new SparseOps(scope);
    bitwise = new BitwiseOps(scope);
    math = new MathOps(scope);
    audio = new AudioOps(scope);
    signal = new SignalOps(scope);
    train = new TrainOps(scope);
    quantization = new QuantizationOps(scope);
  }

  /**
   * Builds an {@link MutexLock} operation
   *
   * @param mutex The mutex resource to lock.
   * @return a new instance of MutexLock
   * @see org.tensorflow.op.core.MutexLock
   */
  public MutexLock mutexLock(Operand<?> mutex) {
    return MutexLock.create(scope, mutex);
  }

  /**
   * Builds an {@link BatchToSpace} operation
   *
   * @param input 4-D tensor with shape
   * @param crops 2-D tensor of non-negative integers with shape `[2, 2]`. It specifies
   * @param blockSize 
   * @return a new instance of BatchToSpace
   * @see org.tensorflow.op.core.BatchToSpace
   */
  public <T extends TType, U extends TNumber> BatchToSpace<T> batchToSpace(Operand<T> input,
      Operand<U> crops, Long blockSize) {
    return BatchToSpace.create(scope, input, crops, blockSize);
  }

  /**
   * Builds an {@link ZerosLike} operation
   *
   * @param x a tensor of type T.
   * @return a new instance of ZerosLike
   * @see org.tensorflow.op.core.ZerosLike
   */
  public <T extends TType> ZerosLike<T> zerosLike(Operand<T> x) {
    return ZerosLike.create(scope, x);
  }

  /**
   * Builds an {@link ReduceMax} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of ReduceMax
   * @see org.tensorflow.op.core.ReduceMax
   */
  public <T extends TType, U extends TNumber> ReduceMax<T> reduceMax(Operand<T> input,
      Operand<U> axis, ReduceMax.Options... options) {
    return ReduceMax.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link Unique} operation
   *
   * @param x A `Tensor`.
   * @param axis A `Tensor` of type `int32` (default: None). The axis of the Tensor to
   * @param outIdx 
   * @return a new instance of Unique
   * @see org.tensorflow.op.core.Unique
   */
  public <T extends TType, V extends TNumber, U extends TNumber> Unique<T, V> unique(Operand<T> x,
      Operand<U> axis, DataType<V> outIdx) {
    return Unique.create(scope, x, axis, outIdx);
  }

  /**
   * Builds an {@link AssignSub} operation
   *
   * @param ref Should be from a `Variable` node.
   * @param value The value to be subtracted to the variable.
   * @param options carries optional attributes values
   * @return a new instance of AssignSub
   * @see org.tensorflow.op.core.AssignSub
   */
  public <T extends TType> AssignSub<T> assignSub(Operand<T> ref, Operand<T> value,
      AssignSub.Options... options) {
    return AssignSub.create(scope, ref, value, options);
  }

  /**
   * Builds an {@link DynamicStitch} operation
   *
   * @param indices 
   * @param data 
   * @return a new instance of DynamicStitch
   * @see org.tensorflow.op.core.DynamicStitch
   */
  public <T extends TType> DynamicStitch<T> dynamicStitch(Iterable<Operand<TInt32>> indices,
      Iterable<Operand<T>> data) {
    return DynamicStitch.create(scope, indices, data);
  }

  /**
   * Builds an {@link SetSize} operation
   *
   * @param setIndices 2D `Tensor`, indices of a `SparseTensor`.
   * @param setValues 1D `Tensor`, values of a `SparseTensor`.
   * @param setShape 1D `Tensor`, shape of a `SparseTensor`.
   * @param options carries optional attributes values
   * @return a new instance of SetSize
   * @see org.tensorflow.op.core.SetSize
   */
  public <T extends TType> SetSize setSize(Operand<TInt64> setIndices, Operand<T> setValues,
      Operand<TInt64> setShape, SetSize.Options... options) {
    return SetSize.create(scope, setIndices, setValues, setShape, options);
  }

  /**
   * Builds an {@link TensorArraySize} operation
   *
   * @param handle The handle to a TensorArray (output of TensorArray or TensorArrayGrad).
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @return a new instance of TensorArraySize
   * @see org.tensorflow.op.core.TensorArraySize
   */
  public TensorArraySize tensorArraySize(Operand<?> handle, Operand<TFloat> flowIn) {
    return TensorArraySize.create(scope, handle, flowIn);
  }

  /**
   * Builds an {@link StopGradient} operation
   *
   * @param input 
   * @return a new instance of StopGradient
   * @see org.tensorflow.op.core.StopGradient
   */
  public <T extends TType> StopGradient<T> stopGradient(Operand<T> input) {
    return StopGradient.create(scope, input);
  }

  /**
   * Builds an {@link ReverseSequence} operation
   *
   * @param input The input to reverse.
   * @param seqLengths 1-D with length `input.dims(batch_dim)` and
   * @param seqDim The dimension which is partially reversed.
   * @param options carries optional attributes values
   * @return a new instance of ReverseSequence
   * @see org.tensorflow.op.core.ReverseSequence
   */
  public <T extends TType, U extends TNumber> ReverseSequence<T> reverseSequence(Operand<T> input,
      Operand<U> seqLengths, Long seqDim, ReverseSequence.Options... options) {
    return ReverseSequence.create(scope, input, seqLengths, seqDim, options);
  }

  /**
   * Builds an {@link AssignSubVariableOp} operation
   *
   * @param resource handle to the resource in which to store the variable.
   * @param value the value by which the variable will be incremented.
   * @return a new instance of AssignSubVariableOp
   * @see org.tensorflow.op.core.AssignSubVariableOp
   */
  public <T extends TType> AssignSubVariableOp assignSubVariableOp(Operand<?> resource,
      Operand<T> value) {
    return AssignSubVariableOp.create(scope, resource, value);
  }

  /**
   * Builds an {@link ClipByValue} operation
   *
   * @param t A `Tensor`.
   * @param clipValueMin A 0-D (scalar) `Tensor`, or a `Tensor` with the same shape
   * @param clipValueMax A 0-D (scalar) `Tensor`, or a `Tensor` with the same shape
   * @return a new instance of ClipByValue
   * @see org.tensorflow.op.core.ClipByValue
   */
  public <T extends TType> ClipByValue<T> clipByValue(Operand<T> t, Operand<T> clipValueMin,
      Operand<T> clipValueMax) {
    return ClipByValue.create(scope, t, clipValueMin, clipValueMax);
  }

  /**
   * Builds an {@link BroadcastTo} operation
   *
   * @param input A Tensor to broadcast.
   * @param shape An 1-D `int` Tensor. The shape of the desired output.
   * @return a new instance of BroadcastTo
   * @see org.tensorflow.op.core.BroadcastTo
   */
  public <T extends TType, U extends TNumber> BroadcastTo<T> broadcastTo(Operand<T> input,
      Operand<U> shape) {
    return BroadcastTo.create(scope, input, shape);
  }

  /**
   * Builds an {@link All} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of All
   * @see org.tensorflow.op.core.All
   */
  public <T extends TNumber> All all(Operand<TBool> input, Operand<T> axis,
      All.Options... options) {
    return All.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link TensorListConcat} operation
   *
   * @param inputHandle 
   * @param elementShape 
   * @param leadingDims 
   * @param elementDtype 
   * @return a new instance of TensorListConcat
   * @see org.tensorflow.op.core.TensorListConcat
   */
  public <U extends TType, T extends TNumber> TensorListConcat<U> tensorListConcat(
      Operand<?> inputHandle, Operand<T> elementShape, Operand<TInt64> leadingDims,
      DataType<U> elementDtype) {
    return TensorListConcat.create(scope, inputHandle, elementShape, leadingDims, elementDtype);
  }

  /**
   * Builds an {@link TensorArray} operation
   *
   * @param size The size of the array.
   * @param dtype The type of the elements on the tensor_array.
   * @param options carries optional attributes values
   * @return a new instance of TensorArray
   * @see org.tensorflow.op.core.TensorArray
   */
  public <T extends TType> TensorArray tensorArray(Operand<TInt32> size, DataType<T> dtype,
      TensorArray.Options... options) {
    return TensorArray.create(scope, size, dtype, options);
  }

  /**
   * Builds an {@link ReduceProd} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of ReduceProd
   * @see org.tensorflow.op.core.ReduceProd
   */
  public <T extends TType, U extends TNumber> ReduceProd<T> reduceProd(Operand<T> input,
      Operand<U> axis, ReduceProd.Options... options) {
    return ReduceProd.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link StageClear} operation
   *
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of StageClear
   * @see org.tensorflow.op.core.StageClear
   */
  public StageClear stageClear(List<DataType<?>> dtypes, StageClear.Options... options) {
    return StageClear.create(scope, dtypes, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TFloat> constant(float[][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link AssignAddVariableOp} operation
   *
   * @param resource handle to the resource in which to store the variable.
   * @param value the value by which the variable will be incremented.
   * @return a new instance of AssignAddVariableOp
   * @see org.tensorflow.op.core.AssignAddVariableOp
   */
  public <T extends TType> AssignAddVariableOp assignAddVariableOp(Operand<?> resource,
      Operand<T> value) {
    return AssignAddVariableOp.create(scope, resource, value);
  }

  /**
   * Builds an {@link LinSpace} operation
   *
   * @param start 0-D tensor. First entry in the range.
   * @param stop 0-D tensor. Last entry in the range.
   * @param num 0-D tensor. Number of values to generate.
   * @return a new instance of LinSpace
   * @see org.tensorflow.op.core.LinSpace
   */
  public <T extends TNumber, U extends TNumber> LinSpace<T> linSpace(Operand<T> start,
      Operand<T> stop, Operand<U> num) {
    return LinSpace.create(scope, start, stop, num);
  }

  /**
   * Builds an {@link ReduceSum} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of ReduceSum
   * @see org.tensorflow.op.core.ReduceSum
   */
  public <T extends TType, U extends TNumber> ReduceSum<T> reduceSum(Operand<T> input,
      Operand<U> axis, ReduceSum.Options... options) {
    return ReduceSum.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link Reverse} operation
   *
   * @param tensor Up to 8-D.
   * @param axis 1-D. The indices of the dimensions to reverse. Must be in the range
   * @return a new instance of Reverse
   * @see org.tensorflow.op.core.Reverse
   */
  public <T extends TType, U extends TNumber> Reverse<T> reverse(Operand<T> tensor,
      Operand<U> axis) {
    return Reverse.create(scope, tensor, axis);
  }

  /**
   * Builds an {@link ResourceScatterNdSub} operation
   *
   * @param ref A resource handle. Must be from a VarHandleOp.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   * @param updates A Tensor. Must have the same type as ref. A tensor of
   * @param options carries optional attributes values
   * @return a new instance of ResourceScatterNdSub
   * @see org.tensorflow.op.core.ResourceScatterNdSub
   */
  public <T extends TNumber, U extends TType> ResourceScatterNdSub resourceScatterNdSub(
      Operand<?> ref, Operand<T> indices, Operand<U> updates,
      ResourceScatterNdSub.Options... options) {
    return ResourceScatterNdSub.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data The string to put into the new constant.
   * @return a string constant
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TString> constant(String data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link BarrierIncompleteSize} operation
   *
   * @param handle The handle to a barrier.
   * @return a new instance of BarrierIncompleteSize
   * @see org.tensorflow.op.core.BarrierIncompleteSize
   */
  public BarrierIncompleteSize barrierIncompleteSize(Operand<TString> handle) {
    return BarrierIncompleteSize.create(scope, handle);
  }

  /**
   * Builds an {@link ScatterAdd} operation
   *
   * @param ref Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to add to `ref`.
   * @param options carries optional attributes values
   * @return a new instance of ScatterAdd
   * @see org.tensorflow.op.core.ScatterAdd
   */
  public <T extends TType, U extends TNumber> ScatterAdd<T> scatterAdd(Operand<T> ref,
      Operand<U> indices, Operand<T> updates, ScatterAdd.Options... options) {
    return ScatterAdd.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt32> constant(int[] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link TensorArrayGather} operation
   *
   * @param handle The handle to a TensorArray.
   * @param indices The locations in the TensorArray from which to read tensor elements.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param dtype The type of the elem that is returned.
   * @param options carries optional attributes values
   * @return a new instance of TensorArrayGather
   * @see org.tensorflow.op.core.TensorArrayGather
   */
  public <T extends TType> TensorArrayGather<T> tensorArrayGather(Operand<?> handle,
      Operand<TInt32> indices, Operand<TFloat> flowIn, DataType<T> dtype,
      TensorArrayGather.Options... options) {
    return TensorArrayGather.create(scope, handle, indices, flowIn, dtype, options);
  }

  /**
   * Builds an {@link MutableHashTableOfTensors} operation
   *
   * @param keyDtype Type of the table keys.
   * @param valueDtype Type of the table values.
   * @param options carries optional attributes values
   * @return a new instance of MutableHashTableOfTensors
   * @see org.tensorflow.op.core.MutableHashTableOfTensors
   */
  public <T extends TType, U extends TType> MutableHashTableOfTensors mutableHashTableOfTensors(
      DataType<T> keyDtype, DataType<U> valueDtype, MutableHashTableOfTensors.Options... options) {
    return MutableHashTableOfTensors.create(scope, keyDtype, valueDtype, options);
  }

  /**
   * Builds an {@link ResourceScatterNdAdd} operation
   *
   * @param ref A resource handle. Must be from a VarHandleOp.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   * @param updates A Tensor. Must have the same type as ref. A tensor of
   * @param options carries optional attributes values
   * @return a new instance of ResourceScatterNdAdd
   * @see org.tensorflow.op.core.ResourceScatterNdAdd
   */
  public <T extends TNumber, U extends TType> ResourceScatterNdAdd resourceScatterNdAdd(
      Operand<?> ref, Operand<T> indices, Operand<U> updates,
      ResourceScatterNdAdd.Options... options) {
    return ResourceScatterNdAdd.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link InplaceSub} operation
   *
   * @param x A `Tensor` of type T.
   * @param i A vector. Indices into the left-most dimension of `x`.
   * @param v A `Tensor` of type T. Same dimension sizes as x except the first dimension, which must be the same as i's size.
   * @return a new instance of InplaceSub
   * @see org.tensorflow.op.core.InplaceSub
   */
  public <T extends TType> InplaceSub<T> inplaceSub(Operand<T> x, Operand<TInt32> i, Operand<T> v) {
    return InplaceSub.create(scope, x, i, v);
  }

  /**
   * Builds an {@link ScatterNdUpdate} operation
   *
   * @param ref A mutable Tensor. Should be from a Variable node.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated
   * @param options carries optional attributes values
   * @return a new instance of ScatterNdUpdate
   * @see org.tensorflow.op.core.ScatterNdUpdate
   */
  public <T extends TType, U extends TNumber> ScatterNdUpdate<T> scatterNdUpdate(Operand<T> ref,
      Operand<U> indices, Operand<T> updates, ScatterNdUpdate.Options... options) {
    return ScatterNdUpdate.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link OrderedMapPeek} operation
   *
   * @param key 
   * @param indices 
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of OrderedMapPeek
   * @see org.tensorflow.op.core.OrderedMapPeek
   */
  public OrderedMapPeek orderedMapPeek(Operand<TInt64> key, Operand<TInt32> indices,
      List<DataType<?>> dtypes, OrderedMapPeek.Options... options) {
    return OrderedMapPeek.create(scope, key, indices, dtypes, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a float constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TFloat> constant(long[] shape, FloatBuffer data) {
    return Constant.create(scope, shape, data);
  }

  /**
   * Builds an {@link LookupTableInsert} operation
   *
   * @param tableHandle Handle to the table.
   * @param keys Any shape.  Keys to look up.
   * @param values Values to associate with keys.
   * @return a new instance of LookupTableInsert
   * @see org.tensorflow.op.core.LookupTableInsert
   */
  public <T extends TType, U extends TType> LookupTableInsert lookupTableInsert(
      Operand<?> tableHandle, Operand<T> keys, Operand<U> values) {
    return LookupTableInsert.create(scope, tableHandle, keys, values);
  }

  /**
   * Builds an {@link TensorScatterNdAdd} operation
   *
   * @param tensor Tensor to copy/update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @return a new instance of TensorScatterNdAdd
   * @see org.tensorflow.op.core.TensorScatterNdAdd
   */
  public <T extends TType, U extends TNumber> TensorScatterNdAdd<T> tensorScatterNdAdd(
      Operand<T> tensor, Operand<U> indices, Operand<T> updates) {
    return TensorScatterNdAdd.create(scope, tensor, indices, updates);
  }

  /**
   * Builds an {@link ParallelConcat} operation
   *
   * @param values Tensors to be concatenated. All must have size 1 in the first dimension
   * @param shape the final shape of the result; should be equal to the shapes of any input
   * @return a new instance of ParallelConcat
   * @see org.tensorflow.op.core.ParallelConcat
   */
  public <T extends TType> ParallelConcat<T> parallelConcat(Iterable<Operand<T>> values,
      Shape shape) {
    return ParallelConcat.create(scope, values, shape);
  }

  /**
   * Builds an {@link AssignVariableOp} operation
   *
   * @param resource handle to the resource in which to store the variable.
   * @param value the value to set the new tensor to use.
   * @return a new instance of AssignVariableOp
   * @see org.tensorflow.op.core.AssignVariableOp
   */
  public <T extends TType> AssignVariableOp assignVariableOp(Operand<?> resource,
      Operand<T> value) {
    return AssignVariableOp.create(scope, resource, value);
  }

  /**
   * Builds an {@link TensorListPushBack} operation
   *
   * @param inputHandle 
   * @param tensor 
   * @return a new instance of TensorListPushBack
   * @see org.tensorflow.op.core.TensorListPushBack
   */
  public <T extends TType> TensorListPushBack tensorListPushBack(Operand<?> inputHandle,
      Operand<T> tensor) {
    return TensorListPushBack.create(scope, inputHandle, tensor);
  }

  /**
   * Builds an {@link QuantizedConcat} operation
   *
   * @param concatDim 0-D.  The dimension along which to concatenate.  Must be in the
   * @param values The `N` Tensors to concatenate. Their ranks and types must match,
   * @param inputMins The minimum scalar values for each of the input tensors.
   * @param inputMaxes The maximum scalar values for each of the input tensors.
   * @return a new instance of QuantizedConcat
   * @see org.tensorflow.op.core.QuantizedConcat
   */
  public <T extends TType> QuantizedConcat<T> quantizedConcat(Operand<TInt32> concatDim,
      Iterable<Operand<T>> values, Iterable<Operand<TFloat>> inputMins,
      Iterable<Operand<TFloat>> inputMaxes) {
    return QuantizedConcat.create(scope, concatDim, values, inputMins, inputMaxes);
  }

  /**
   * Builds an {@link RefSwitch} operation
   *
   * @param data The ref tensor to be forwarded to the appropriate output.
   * @param pred A scalar that specifies which output port will receive data.
   * @return a new instance of RefSwitch
   * @see org.tensorflow.op.core.RefSwitch
   */
  public <T extends TType> RefSwitch<T> refSwitch(Operand<T> data, Operand<TBool> pred) {
    return RefSwitch.create(scope, data, pred);
  }

  /**
   * Builds an {@link AssignAdd} operation
   *
   * @param ref Should be from a `Variable` node.
   * @param value The value to be added to the variable.
   * @param options carries optional attributes values
   * @return a new instance of AssignAdd
   * @see org.tensorflow.op.core.AssignAdd
   */
  public <T extends TType> AssignAdd<T> assignAdd(Operand<T> ref, Operand<T> value,
      AssignAdd.Options... options) {
    return AssignAdd.create(scope, ref, value, options);
  }

  /**
   * Builds an {@link GatherNd} operation
   *
   * @param params The tensor from which to gather values.
   * @param indices Index tensor.
   * @return a new instance of GatherNd
   * @see org.tensorflow.op.core.GatherNd
   */
  public <T extends TType, U extends TNumber> GatherNd<T> gatherNd(Operand<T> params,
      Operand<U> indices) {
    return GatherNd.create(scope, params, indices);
  }

  /**
   * Builds an {@link OrderedMapSize} operation
   *
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of OrderedMapSize
   * @see org.tensorflow.op.core.OrderedMapSize
   */
  public OrderedMapSize orderedMapSize(List<DataType<?>> dtypes,
      OrderedMapSize.Options... options) {
    return OrderedMapSize.create(scope, dtypes, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. String elements are
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TString> constant(byte[][][][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. String elements are
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TString> constant(byte[][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link IsVariableInitialized} operation
   *
   * @param ref Should be from a `Variable` node. May be uninitialized.
   * @return a new instance of IsVariableInitialized
   * @see org.tensorflow.op.core.IsVariableInitialized
   */
  public <T extends TType> IsVariableInitialized isVariableInitialized(Operand<T> ref) {
    return IsVariableInitialized.create(scope, ref);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a long constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt64> constant(long[] shape, LongBuffer data) {
    return Constant.create(scope, shape, data);
  }

  /**
   * Builds an {@link Gradients} operation
   *
   * @param y output of the function to derive
   * @param x inputs of the function for which partial derivatives are computed
   * @param options carries optional attributes values
   * @return a new instance of {@code Gradients}
   * @throws IllegalArgumentException if execution environment is not a graph
   * @see org.tensorflow.op.core.Gradients
   */
  public Gradients gradients(Operand<?> y, Iterable<? extends Operand<?>> x,
      Gradients.Options... options) {
    return Gradients.create(scope, y, x, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt32> constant(int[][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link MutableHashTable} operation
   *
   * @param keyDtype Type of the table keys.
   * @param valueDtype Type of the table values.
   * @param options carries optional attributes values
   * @return a new instance of MutableHashTable
   * @see org.tensorflow.op.core.MutableHashTable
   */
  public <T extends TType, U extends TType> MutableHashTable mutableHashTable(DataType<T> keyDtype,
      DataType<U> valueDtype, MutableHashTable.Options... options) {
    return MutableHashTable.create(scope, keyDtype, valueDtype, options);
  }

  /**
   * Builds an {@link Min} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of Min
   * @see org.tensorflow.op.core.Min
   */
  public <T extends TType, U extends TNumber> Min<T> min(Operand<T> input, Operand<U> axis,
      Min.Options... options) {
    return Min.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link Unstage} operation
   *
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of Unstage
   * @see org.tensorflow.op.core.Unstage
   */
  public Unstage unstage(List<DataType<?>> dtypes, Unstage.Options... options) {
    return Unstage.create(scope, dtypes, options);
  }

  /**
   * Builds an {@link Unique} operation
   *
   * @param x A `Tensor`.
   * @param axis A `Tensor` of type `int32` (default: None). The axis of the Tensor to
   * @return a new instance of Unique
   * @see org.tensorflow.op.core.Unique
   */
  public <T extends TType, U extends TNumber> Unique<T, TInt32> unique(Operand<T> x,
      Operand<U> axis) {
    return Unique.create(scope, x, axis);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TDouble> constant(double[][][][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TBool> constant(boolean[][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link TensorArrayWrite} operation
   *
   * @param handle The handle to a TensorArray.
   * @param index The position to write to inside the TensorArray.
   * @param value The tensor to write to the TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @return a new instance of TensorArrayWrite
   * @see org.tensorflow.op.core.TensorArrayWrite
   */
  public <T extends TType> TensorArrayWrite tensorArrayWrite(Operand<?> handle,
      Operand<TInt32> index, Operand<T> value, Operand<TFloat> flowIn) {
    return TensorArrayWrite.create(scope, handle, index, value, flowIn);
  }

  /**
   * Builds an {@link StridedSliceAssign} operation
   *
   * @param ref 
   * @param begin 
   * @param end 
   * @param strides 
   * @param value 
   * @param options carries optional attributes values
   * @return a new instance of StridedSliceAssign
   * @see org.tensorflow.op.core.StridedSliceAssign
   */
  public <T extends TType, U extends TNumber> StridedSliceAssign<T> stridedSliceAssign(
      Operand<T> ref, Operand<U> begin, Operand<U> end, Operand<U> strides, Operand<T> value,
      StridedSliceAssign.Options... options) {
    return StridedSliceAssign.create(scope, ref, begin, end, strides, value, options);
  }

  /**
   * Builds an {@link Unstack} operation
   *
   * @param value 1-D or higher, with `axis` dimension size equal to `num`.
   * @param num 
   * @param options carries optional attributes values
   * @return a new instance of Unstack
   * @see org.tensorflow.op.core.Unstack
   */
  public <T extends TType> Unstack<T> unstack(Operand<T> value, Long num,
      Unstack.Options... options) {
    return Unstack.create(scope, value, num, options);
  }

  /**
   * Builds an {@link ReduceAll} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of ReduceAll
   * @see org.tensorflow.op.core.ReduceAll
   */
  public <T extends TNumber> ReduceAll reduceAll(Operand<TBool> input, Operand<T> axis,
      ReduceAll.Options... options) {
    return ReduceAll.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TBool> constant(boolean[][][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Split} operation
   *
   * @param axis 0-D.  The dimension along which to split.  Must be in the range
   * @param value The tensor to split.
   * @param numSplit The number of ways to split.  Must evenly divide
   * @return a new instance of Split
   * @see org.tensorflow.op.core.Split
   */
  public <T extends TType> Split<T> split(Operand<TInt32> axis, Operand<T> value, Long numSplit) {
    return Split.create(scope, axis, value, numSplit);
  }

  /**
   * Builds an {@link ResourceGather} operation
   *
   * @param resource 
   * @param indices 
   * @param dtype 
   * @param options carries optional attributes values
   * @return a new instance of ResourceGather
   * @see org.tensorflow.op.core.ResourceGather
   */
  public <U extends TType, T extends TNumber> ResourceGather<U> resourceGather(Operand<?> resource,
      Operand<T> indices, DataType<U> dtype, ResourceGather.Options... options) {
    return ResourceGather.create(scope, resource, indices, dtype, options);
  }

  /**
   * Builds an {@link SwitchCond} operation
   *
   * @param data The tensor to be forwarded to the appropriate output.
   * @param pred A scalar that specifies which output port will receive data.
   * @return a new instance of SwitchCond
   * @see org.tensorflow.op.core.SwitchCond
   */
  public <T extends TType> SwitchCond<T> switchCond(Operand<T> data, Operand<TBool> pred) {
    return SwitchCond.create(scope, data, pred);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a double constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TDouble> constant(long[] shape, DoubleBuffer data) {
    return Constant.create(scope, shape, data);
  }

  /**
   * Builds an {@link TensorScatterNdUpdate} operation
   *
   * @param tensor Tensor to copy/update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @return a new instance of TensorScatterNdUpdate
   * @see org.tensorflow.op.core.TensorScatterNdUpdate
   */
  public <T extends TType, U extends TNumber> TensorScatterNdUpdate<T> tensorScatterNdUpdate(
      Operand<T> tensor, Operand<U> indices, Operand<T> updates) {
    return TensorScatterNdUpdate.create(scope, tensor, indices, updates);
  }

  /**
   * Builds an {@link BarrierReadySize} operation
   *
   * @param handle The handle to a barrier.
   * @return a new instance of BarrierReadySize
   * @see org.tensorflow.op.core.BarrierReadySize
   */
  public BarrierReadySize barrierReadySize(Operand<TString> handle) {
    return BarrierReadySize.create(scope, handle);
  }

  /**
   * Builds an {@link Rank} operation
   *
   * @param input 
   * @return a new instance of Rank
   * @see org.tensorflow.op.core.Rank
   */
  public <T extends TType> Rank rank(Operand<T> input) {
    return Rank.create(scope, input);
  }

  /**
   * Builds an {@link InitializeTable} operation
   *
   * @param tableHandle Handle to a table which will be initialized.
   * @param keys Keys of type Tkey.
   * @param values Values of type Tval.
   * @return a new instance of InitializeTable
   * @see org.tensorflow.op.core.InitializeTable
   */
  public <T extends TType, U extends TType> InitializeTable initializeTable(Operand<?> tableHandle,
      Operand<T> keys, Operand<U> values) {
    return InitializeTable.create(scope, tableHandle, keys, values);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data The value to put into the new constant.
   * @return a long constant
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt64> constant(long data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt64> constant(long[][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link SetDiff1d} operation
   *
   * @param x 1-D. Values to keep.
   * @param y 1-D. Values to remove.
   * @return a new instance of SetDiff1d
   * @see org.tensorflow.op.core.SetDiff1d
   */
  public <T extends TType> SetDiff1d<T, TInt32> setDiff1d(Operand<T> x, Operand<T> y) {
    return SetDiff1d.create(scope, x, y);
  }

  /**
   * Builds an {@link Any} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of Any
   * @see org.tensorflow.op.core.Any
   */
  public <T extends TNumber> Any any(Operand<TBool> input, Operand<T> axis,
      Any.Options... options) {
    return Any.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt32> constant(int[][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link TryRpc} operation
   *
   * @param address `0-D` or `1-D`.  The address (i.e. host_name:port) of the RPC server.
   * @param method `0-D` or `1-D`.  The method address on the RPC server.
   * @param request `0-D` or `1-D`.  Serialized proto strings: the rpc request argument.
   * @param options carries optional attributes values
   * @return a new instance of TryRpc
   * @see org.tensorflow.op.core.TryRpc
   */
  public TryRpc tryRpc(Operand<TString> address, Operand<TString> method, Operand<TString> request,
      TryRpc.Options... options) {
    return TryRpc.create(scope, address, method, request, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TFloat> constant(float[] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Empty} operation
   *
   * @param shape 1-D. Represents the shape of the output tensor.
   * @param dtype 
   * @param options carries optional attributes values
   * @return a new instance of Empty
   * @see org.tensorflow.op.core.Empty
   */
  public <T extends TType> Empty<T> empty(Operand<TInt32> shape, DataType<T> dtype,
      Empty.Options... options) {
    return Empty.create(scope, shape, dtype, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. String elements are
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TString> constant(byte[][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Timestamp} operation
   *
   * @return a new instance of Timestamp
   * @see org.tensorflow.op.core.Timestamp
   */
  public Timestamp timestamp() {
    return Timestamp.create(scope);
  }

  /**
   * Builds an {@link PlaceholderWithDefault} operation
   *
   * @param input The default value to produce when `output` is not fed.
   * @param shape The (possibly partial) shape of the tensor.
   * @return a new instance of PlaceholderWithDefault
   * @see org.tensorflow.op.core.PlaceholderWithDefault
   */
  public <T extends TType> PlaceholderWithDefault<T> placeholderWithDefault(Operand<T> input,
      Shape shape) {
    return PlaceholderWithDefault.create(scope, input, shape);
  }

  /**
   * Builds an {@link BatchToSpaceNd} operation
   *
   * @param input N-D with shape `input_shape = [batch] + spatial_shape + remaining_shape`,
   * @param blockShape 1-D with shape `[M]`, all values must be >= 1.
   * @param crops 2-D with shape `[M, 2]`, all values must be >= 0.
   * @return a new instance of BatchToSpaceNd
   * @see org.tensorflow.op.core.BatchToSpaceNd
   */
  public <T extends TType, U extends TNumber, V extends TNumber> BatchToSpaceNd<T> batchToSpaceNd(
      Operand<T> input, Operand<U> blockShape, Operand<V> crops) {
    return BatchToSpaceNd.create(scope, input, blockShape, crops);
  }

  /**
   * Builds an {@link TensorListGetItem} operation
   *
   * @param inputHandle 
   * @param index 
   * @param elementShape 
   * @param elementDtype 
   * @return a new instance of TensorListGetItem
   * @see org.tensorflow.op.core.TensorListGetItem
   */
  public <T extends TType> TensorListGetItem<T> tensorListGetItem(Operand<?> inputHandle,
      Operand<TInt32> index, Operand<TInt32> elementShape, DataType<T> elementDtype) {
    return TensorListGetItem.create(scope, inputHandle, index, elementShape, elementDtype);
  }

  /**
   * Builds an {@link NextIteration} operation
   *
   * @param data The tensor to be made available to the next iteration.
   * @return a new instance of NextIteration
   * @see org.tensorflow.op.core.NextIteration
   */
  public <T extends TType> NextIteration<T> nextIteration(Operand<T> data) {
    return NextIteration.create(scope, data);
  }

  /**
   * Builds an {@link ShapeN} operation
   *
   * @param input 
   * @return a new instance of ShapeN
   * @see org.tensorflow.op.core.ShapeN
   */
  public <T extends TType> ShapeN<TInt32> shapeN(Iterable<Operand<T>> input) {
    return ShapeN.create(scope, input);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TDouble> constant(double[][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link MutableDenseHashTable} operation
   *
   * @param emptyKey The key used to represent empty key buckets internally. Must not
   * @param deletedKey 
   * @param valueDtype Type of the table values.
   * @param options carries optional attributes values
   * @return a new instance of MutableDenseHashTable
   * @see org.tensorflow.op.core.MutableDenseHashTable
   */
  public <T extends TType, U extends TType> MutableDenseHashTable mutableDenseHashTable(
      Operand<T> emptyKey, Operand<T> deletedKey, DataType<U> valueDtype,
      MutableDenseHashTable.Options... options) {
    return MutableDenseHashTable.create(scope, emptyKey, deletedKey, valueDtype, options);
  }

  /**
   * Builds an {@link TensorListReserve} operation
   *
   * @param elementShape 
   * @param numElements 
   * @param elementDtype 
   * @return a new instance of TensorListReserve
   * @see org.tensorflow.op.core.TensorListReserve
   */
  public <T extends TNumber, U extends TType> TensorListReserve tensorListReserve(
      Operand<T> elementShape, Operand<TInt32> numElements, DataType<U> elementDtype) {
    return TensorListReserve.create(scope, elementShape, numElements, elementDtype);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TBool> constant(boolean[][][][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. String elements are
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TString> constant(byte[] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Batch} operation
   *
   * @param inTensors 
   * @param numBatchThreads 
   * @param maxBatchSize 
   * @param batchTimeoutMicros 
   * @param gradTimeoutMicros 
   * @param options carries optional attributes values
   * @return a new instance of Batch
   * @see org.tensorflow.op.core.Batch
   */
  public Batch batch(Iterable<Operand<?>> inTensors, Long numBatchThreads, Long maxBatchSize,
      Long batchTimeoutMicros, Long gradTimeoutMicros, Batch.Options... options) {
    return Batch.create(scope, inTensors, numBatchThreads, maxBatchSize, batchTimeoutMicros, gradTimeoutMicros, options);
  }

  /**
   * Builds an {@link ResourceCountUpTo} operation
   *
   * @param resource Should be from a scalar `Variable` node.
   * @param limit If incrementing ref would bring it above limit, instead generates an
   * @param T 
   * @return a new instance of ResourceCountUpTo
   * @see org.tensorflow.op.core.ResourceCountUpTo
   */
  public <T extends TNumber> ResourceCountUpTo<T> resourceCountUpTo(Operand<?> resource, Long limit,
      DataType<T> T) {
    return ResourceCountUpTo.create(scope, resource, limit, T);
  }

  /**
   * Builds an {@link UnravelIndex} operation
   *
   * @param indices An 0-D or 1-D `int` Tensor whose elements are indices into the
   * @param dims An 1-D `int` Tensor. The shape of the array to use for unraveling
   * @return a new instance of UnravelIndex
   * @see org.tensorflow.op.core.UnravelIndex
   */
  public <T extends TNumber> UnravelIndex<T> unravelIndex(Operand<T> indices, Operand<T> dims) {
    return UnravelIndex.create(scope, indices, dims);
  }

  /**
   * Builds an {@link Max} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of Max
   * @see org.tensorflow.op.core.Max
   */
  public <T extends TType, U extends TNumber> Max<T> max(Operand<T> input, Operand<U> axis,
      Max.Options... options) {
    return Max.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link LookupTableFind} operation
   *
   * @param tableHandle Handle to the table.
   * @param keys Any shape.  Keys to look up.
   * @param defaultValue 
   * @return a new instance of LookupTableFind
   * @see org.tensorflow.op.core.LookupTableFind
   */
  public <U extends TType, T extends TType> LookupTableFind<U> lookupTableFind(
      Operand<?> tableHandle, Operand<T> keys, Operand<U> defaultValue) {
    return LookupTableFind.create(scope, tableHandle, keys, defaultValue);
  }

  /**
   * Builds an {@link VariableShape} operation
   *
   * @param input 
   * @return a new instance of VariableShape
   * @see org.tensorflow.op.core.VariableShape
   */
  public VariableShape<TInt32> variableShape(Operand<?> input) {
    return VariableShape.create(scope, input);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TBool> constant(boolean[][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TBool> constant(boolean[] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Stage} operation
   *
   * @param values a list of tensors
   * @param options carries optional attributes values
   * @return a new instance of Stage
   * @see org.tensorflow.op.core.Stage
   */
  public Stage stage(Iterable<Operand<?>> values, Stage.Options... options) {
    return Stage.create(scope, values, options);
  }

  /**
   * Builds an {@link Fill} operation
   *
   * @param dims 1-D. Represents the shape of the output tensor.
   * @param value 0-D (scalar). Value to fill the returned tensor.
   * @return a new instance of Fill
   * @see org.tensorflow.op.core.Fill
   */
  public <U extends TType, T extends TNumber> Fill<U> fill(Operand<T> dims, Operand<U> value) {
    return Fill.create(scope, dims, value);
  }

  /**
   * Builds an {@link TensorArrayConcat} operation
   *
   * @param handle The handle to a TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param dtype The type of the elem that is returned.
   * @param options carries optional attributes values
   * @return a new instance of TensorArrayConcat
   * @see org.tensorflow.op.core.TensorArrayConcat
   */
  public <T extends TType> TensorArrayConcat<T> tensorArrayConcat(Operand<?> handle,
      Operand<TFloat> flowIn, DataType<T> dtype, TensorArrayConcat.Options... options) {
    return TensorArrayConcat.create(scope, handle, flowIn, dtype, options);
  }

  /**
   * Builds an {@link DeepCopy} operation
   *
   * @param x The source tensor of type `T`.
   * @return a new instance of DeepCopy
   * @see org.tensorflow.op.core.DeepCopy
   */
  public <T extends TType> DeepCopy<T> deepCopy(Operand<T> x) {
    return DeepCopy.create(scope, x);
  }

  /**
   * Builds an {@link ResourceScatterAdd} operation
   *
   * @param resource Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to add to `ref`.
   * @return a new instance of ResourceScatterAdd
   * @see org.tensorflow.op.core.ResourceScatterAdd
   */
  public <T extends TNumber, U extends TType> ResourceScatterAdd resourceScatterAdd(
      Operand<?> resource, Operand<T> indices, Operand<U> updates) {
    return ResourceScatterAdd.create(scope, resource, indices, updates);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data The value to put into the new constant. 
   * @return a float constant
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TFloat> constant(float data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link OnesLike} operation
   *
   * @param x a tensor of type T.
   * @return a new instance of OnesLike
   * @see org.tensorflow.op.core.OnesLike
   */
  public <T extends TType> OnesLike<T> onesLike(Operand<T> x) {
    return OnesLike.create(scope, x);
  }

  /**
   * Builds an {@link Bucketize} operation
   *
   * @param input Any shape of Tensor contains with int or float type.
   * @param boundaries A sorted list of floats gives the boundary of the buckets.
   * @return a new instance of Bucketize
   * @see org.tensorflow.op.core.Bucketize
   */
  public <T extends TNumber> Bucketize bucketize(Operand<T> input, List<Float> boundaries) {
    return Bucketize.create(scope, input, boundaries);
  }

  /**
   * Builds an {@link Slice} operation
   *
   * @param input 
   * @param begin begin[i] specifies the offset into the 'i'th dimension of
   * @param size size[i] specifies the number of elements of the 'i'th dimension
   * @return a new instance of Slice
   * @see org.tensorflow.op.core.Slice
   */
  public <T extends TType, U extends TNumber> Slice<T> slice(Operand<T> input, Operand<U> begin,
      Operand<U> size) {
    return Slice.create(scope, input, begin, size);
  }

  /**
   * Builds an {@link CountUpTo} operation
   *
   * @param ref Should be from a scalar `Variable` node.
   * @param limit If incrementing ref would bring it above limit, instead generates an
   * @return a new instance of CountUpTo
   * @see org.tensorflow.op.core.CountUpTo
   */
  public <T extends TNumber> CountUpTo<T> countUpTo(Operand<T> ref, Long limit) {
    return CountUpTo.create(scope, ref, limit);
  }

  /**
   * Builds an {@link ScatterMin} operation
   *
   * @param ref Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to reduce into `ref`.
   * @param options carries optional attributes values
   * @return a new instance of ScatterMin
   * @see org.tensorflow.op.core.ScatterMin
   */
  public <T extends TNumber, U extends TNumber> ScatterMin<T> scatterMin(Operand<T> ref,
      Operand<U> indices, Operand<T> updates, ScatterMin.Options... options) {
    return ScatterMin.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link MapPeek} operation
   *
   * @param key 
   * @param indices 
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of MapPeek
   * @see org.tensorflow.op.core.MapPeek
   */
  public MapPeek mapPeek(Operand<TInt64> key, Operand<TInt32> indices, List<DataType<?>> dtypes,
      MapPeek.Options... options) {
    return MapPeek.create(scope, key, indices, dtypes, options);
  }

  /**
   * Builds an {@link Concat} operation
   *
   * @param values List of `N` Tensors to concatenate. Their ranks and types must match,
   * @param axis 0-D.  The dimension along which to concatenate.  Must be in the
   * @return a new instance of Concat
   * @see org.tensorflow.op.core.Concat
   */
  public <T extends TType, U extends TNumber> Concat<T> concat(Iterable<Operand<T>> values,
      Operand<U> axis) {
    return Concat.create(scope, values, axis);
  }

  /**
   * Builds an {@link AssertThat} operation
   *
   * @param condition The condition to evaluate.
   * @param data The tensors to print out when condition is false.
   * @param options carries optional attributes values
   * @return a new instance of AssertThat
   * @see org.tensorflow.op.core.AssertThat
   */
  public AssertThat assertThat(Operand<TBool> condition, Iterable<Operand<?>> data,
      AssertThat.Options... options) {
    return AssertThat.create(scope, condition, data, options);
  }

  /**
   * Builds an {@link Stack} operation
   *
   * @param values Must be of same shape and type.
   * @param options carries optional attributes values
   * @return a new instance of Stack
   * @see org.tensorflow.op.core.Stack
   */
  public <T extends TType> Stack<T> stack(Iterable<Operand<T>> values, Stack.Options... options) {
    return Stack.create(scope, values, options);
  }

  /**
   * Builds an {@link ScatterMul} operation
   *
   * @param ref Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to multiply to `ref`.
   * @param options carries optional attributes values
   * @return a new instance of ScatterMul
   * @see org.tensorflow.op.core.ScatterMul
   */
  public <T extends TType, U extends TNumber> ScatterMul<T> scatterMul(Operand<T> ref,
      Operand<U> indices, Operand<T> updates, ScatterMul.Options... options) {
    return ScatterMul.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link Prod} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of Prod
   * @see org.tensorflow.op.core.Prod
   */
  public <T extends TType, U extends TNumber> Prod<T> prod(Operand<T> input, Operand<U> axis,
      Prod.Options... options) {
    return Prod.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link TensorListScatter} operation
   *
   * @param tensor 
   * @param indices 
   * @param elementShape 
   * @param numElements 
   * @return a new instance of TensorListScatter
   * @see org.tensorflow.op.core.TensorListScatter
   */
  public <T extends TType, U extends TNumber> TensorListScatter tensorListScatter(Operand<T> tensor,
      Operand<TInt32> indices, Operand<U> elementShape, Operand<TInt32> numElements) {
    return TensorListScatter.create(scope, tensor, indices, elementShape, numElements);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt64> constant(long[] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Snapshot} operation
   *
   * @param input 
   * @return a new instance of Snapshot
   * @see org.tensorflow.op.core.Snapshot
   */
  public <T extends TType> Snapshot<T> snapshot(Operand<T> input) {
    return Snapshot.create(scope, input);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TDouble> constant(double[] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Unbatch} operation
   *
   * @param batchedTensor 
   * @param batchIndex 
   * @param id 
   * @param timeoutMicros 
   * @param options carries optional attributes values
   * @return a new instance of Unbatch
   * @see org.tensorflow.op.core.Unbatch
   */
  public <T extends TType> Unbatch<T> unbatch(Operand<T> batchedTensor, Operand<TInt64> batchIndex,
      Operand<TInt64> id, Long timeoutMicros, Unbatch.Options... options) {
    return Unbatch.create(scope, batchedTensor, batchIndex, id, timeoutMicros, options);
  }

  /**
   * Builds an {@link TensorListConcatLists} operation
   *
   * @param inputA 
   * @param inputB 
   * @param elementDtype 
   * @return a new instance of TensorListConcatLists
   * @see org.tensorflow.op.core.TensorListConcatLists
   */
  public <T extends TType> TensorListConcatLists tensorListConcatLists(Operand<?> inputA,
      Operand<?> inputB, DataType<T> elementDtype) {
    return TensorListConcatLists.create(scope, inputA, inputB, elementDtype);
  }

  /**
   * Builds an {@link Merge} operation
   *
   * @param inputs The input tensors, exactly one of which will become available.
   * @return a new instance of Merge
   * @see org.tensorflow.op.core.Merge
   */
  public <T extends TType> Merge<T> merge(Iterable<Operand<T>> inputs) {
    return Merge.create(scope, inputs);
  }

  /**
   * Builds an {@link Gradients} operation
   *
   * @param y outputs of the function to derive
   * @param x inputs of the function for which partial derivatives are computed
   * @param options carries optional attributes values
   * @return a new instance of {@code Gradients}
   * @throws IllegalArgumentException if execution environment is not a graph
   * @see org.tensorflow.op.core.Gradients
   */
  public Gradients gradients(Iterable<? extends Operand<?>> y, Iterable<? extends Operand<?>> x,
      Gradients.Options... options) {
    return Gradients.create(scope, y, x, options);
  }

  /**
   * Builds an {@link NoOp} operation
   *
   * @return a new instance of NoOp
   * @see org.tensorflow.op.core.NoOp
   */
  public NoOp noOp() {
    return NoOp.create(scope);
  }

  /**
   * Builds an {@link Tile} operation
   *
   * @param input 1-D or higher.
   * @param multiples 1-D. Length must be the same as the number of dimensions in `input`
   * @return a new instance of Tile
   * @see org.tensorflow.op.core.Tile
   */
  public <T extends TType, U extends TNumber> Tile<T> tile(Operand<T> input, Operand<U> multiples) {
    return Tile.create(scope, input, multiples);
  }

  /**
   * Builds an {@link Select} operation
   *
   * @param condition 
   * @param t 
   * @param e 
   * @return a new instance of Select
   * @see org.tensorflow.op.core.Select
   */
  public <T extends TType> Select<T> select(Operand<TBool> condition, Operand<T> t, Operand<T> e) {
    return Select.create(scope, condition, t, e);
  }

  /**
   * Builds an {@link TensorListLength} operation
   *
   * @param inputHandle 
   * @return a new instance of TensorListLength
   * @see org.tensorflow.op.core.TensorListLength
   */
  public TensorListLength tensorListLength(Operand<?> inputHandle) {
    return TensorListLength.create(scope, inputHandle);
  }

  /**
   * Builds an {@link OrderedMapClear} operation
   *
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of OrderedMapClear
   * @see org.tensorflow.op.core.OrderedMapClear
   */
  public OrderedMapClear orderedMapClear(List<DataType<?>> dtypes,
      OrderedMapClear.Options... options) {
    return OrderedMapClear.create(scope, dtypes, options);
  }

  /**
   * Builds an {@link Skipgram} operation
   *
   * @param filename The corpus's text file name.
   * @param batchSize The size of produced batch.
   * @param options carries optional attributes values
   * @return a new instance of Skipgram
   * @see org.tensorflow.op.core.Skipgram
   */
  public Skipgram skipgram(String filename, Long batchSize, Skipgram.Options... options) {
    return Skipgram.create(scope, filename, batchSize, options);
  }

  /**
   * Builds an {@link TensorArrayClose} operation
   *
   * @param handle The handle to a TensorArray (output of TensorArray or TensorArrayGrad).
   * @return a new instance of TensorArrayClose
   * @see org.tensorflow.op.core.TensorArrayClose
   */
  public TensorArrayClose tensorArrayClose(Operand<?> handle) {
    return TensorArrayClose.create(scope, handle);
  }

  /**
   * Builds an {@link Size} operation
   *
   * @param input 
   * @param outType 
   * @return a new instance of Size
   * @see org.tensorflow.op.core.Size
   */
  public <U extends TNumber, T extends TType> Size<U> size(Operand<T> input, DataType<U> outType) {
    return Size.create(scope, input, outType);
  }

  /**
   * Builds an {@link TensorListStack} operation
   *
   * @param inputHandle 
   * @param elementShape 
   * @param elementDtype 
   * @param options carries optional attributes values
   * @return a new instance of TensorListStack
   * @see org.tensorflow.op.core.TensorListStack
   */
  public <T extends TType> TensorListStack<T> tensorListStack(Operand<?> inputHandle,
      Operand<TInt32> elementShape, DataType<T> elementDtype, TensorListStack.Options... options) {
    return TensorListStack.create(scope, inputHandle, elementShape, elementDtype, options);
  }

  /**
   * Builds an {@link EnsureShape} operation
   *
   * @param input A tensor, whose shape is to be validated.
   * @param shape The expected (possibly partially specified) shape of the input tensor.
   * @return a new instance of EnsureShape
   * @see org.tensorflow.op.core.EnsureShape
   */
  public <T extends TType> EnsureShape<T> ensureShape(Operand<T> input, Shape shape) {
    return EnsureShape.create(scope, input, shape);
  }

  /**
   * Builds an {@link Print} operation
   *
   * @param input The string scalar to print.
   * @param options carries optional attributes values
   * @return a new instance of Print
   * @see org.tensorflow.op.core.Print
   */
  public Print print(Operand<TString> input, Print.Options... options) {
    return Print.create(scope, input, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param object a Java object representing the constant.
   * @return a constant of type `type`
   * @see org.tensorflow.Tensor#create(Object) Tensor.create
   * @see org.tensorflow.op.core.Constant
   */
  public <T extends TType> Constant<T> constant(Object object, DataType<T> type) {
    return Constant.create(scope, object, type);
  }

  /**
   * Builds an {@link InitializeTableFromTextFile} operation
   *
   * @param tableHandle Handle to a table which will be initialized.
   * @param filename Filename of a vocabulary text file.
   * @param keyIndex Column index in a line to get the table `key` values from.
   * @param valueIndex Column index that represents information of a line to get the table
   * @param options carries optional attributes values
   * @return a new instance of InitializeTableFromTextFile
   * @see org.tensorflow.op.core.InitializeTableFromTextFile
   */
  public InitializeTableFromTextFile initializeTableFromTextFile(Operand<?> tableHandle,
      Operand<TString> filename, Long keyIndex, Long valueIndex,
      InitializeTableFromTextFile.Options... options) {
    return InitializeTableFromTextFile.create(scope, tableHandle, filename, keyIndex, valueIndex, options);
  }

  /**
   * Builds an {@link InplaceAdd} operation
   *
   * @param x A `Tensor` of type T.
   * @param i A vector. Indices into the left-most dimension of `x`.
   * @param v A `Tensor` of type T. Same dimension sizes as x except the first dimension, which must be the same as i's size.
   * @return a new instance of InplaceAdd
   * @see org.tensorflow.op.core.InplaceAdd
   */
  public <T extends TType> InplaceAdd<T> inplaceAdd(Operand<T> x, Operand<TInt32> i, Operand<T> v) {
    return InplaceAdd.create(scope, x, i, v);
  }

  /**
   * Builds an {@link Mutex} operation
   *
   * @param options carries optional attributes values
   * @return a new instance of Mutex
   * @see org.tensorflow.op.core.Mutex
   */
  public Mutex mutex(Mutex.Options... options) {
    return Mutex.create(scope, options);
  }

  /**
   * Builds an {@link ResourceScatterNdUpdate} operation
   *
   * @param ref A resource handle. Must be from a VarHandleOp.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated
   * @param options carries optional attributes values
   * @return a new instance of ResourceScatterNdUpdate
   * @see org.tensorflow.op.core.ResourceScatterNdUpdate
   */
  public <T extends TNumber, U extends TType> ResourceScatterNdUpdate resourceScatterNdUpdate(
      Operand<?> ref, Operand<T> indices, Operand<U> updates,
      ResourceScatterNdUpdate.Options... options) {
    return ResourceScatterNdUpdate.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link ShapeN} operation
   *
   * @param input 
   * @param outType 
   * @return a new instance of ShapeN
   * @see org.tensorflow.op.core.ShapeN
   */
  public <U extends TNumber, T extends TType> ShapeN<U> shapeN(Iterable<Operand<T>> input,
      DataType<U> outType) {
    return ShapeN.create(scope, input, outType);
  }

  /**
   * Builds an {@link RemoteFusedGraphExecute} operation
   *
   * @param inputs Arbitrary number of tensors with arbitrary data types
   * @param Toutputs 
   * @param serializedRemoteFusedGraphExecuteInfo Serialized protocol buffer
   * @return a new instance of RemoteFusedGraphExecute
   * @see org.tensorflow.op.core.RemoteFusedGraphExecute
   */
  public RemoteFusedGraphExecute remoteFusedGraphExecute(Iterable<Operand<?>> inputs,
      List<DataType<?>> Toutputs, String serializedRemoteFusedGraphExecuteInfo) {
    return RemoteFusedGraphExecute.create(scope, inputs, Toutputs, serializedRemoteFusedGraphExecuteInfo);
  }

  /**
   * Builds an {@link TensorStridedSliceUpdate} operation
   *
   * @param input 
   * @param begin 
   * @param end 
   * @param strides 
   * @param value 
   * @param options carries optional attributes values
   * @return a new instance of TensorStridedSliceUpdate
   * @see org.tensorflow.op.core.TensorStridedSliceUpdate
   */
  public <T extends TType, U extends TNumber> TensorStridedSliceUpdate<T> tensorStridedSliceUpdate(
      Operand<T> input, Operand<U> begin, Operand<U> end, Operand<U> strides, Operand<T> value,
      TensorStridedSliceUpdate.Options... options) {
    return TensorStridedSliceUpdate.create(scope, input, begin, end, strides, value, options);
  }

  /**
   * Builds an {@link ScatterMax} operation
   *
   * @param ref Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to reduce into `ref`.
   * @param options carries optional attributes values
   * @return a new instance of ScatterMax
   * @see org.tensorflow.op.core.ScatterMax
   */
  public <T extends TNumber, U extends TNumber> ScatterMax<T> scatterMax(Operand<T> ref,
      Operand<U> indices, Operand<T> updates, ScatterMax.Options... options) {
    return ScatterMax.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link TensorArrayGrad} operation
   *
   * @param handle The handle to the forward TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param source The gradient source string, used to decide which gradient TensorArray
   * @return a new instance of TensorArrayGrad
   * @see org.tensorflow.op.core.TensorArrayGrad
   */
  public TensorArrayGrad tensorArrayGrad(Operand<?> handle, Operand<TFloat> flowIn, String source) {
    return TensorArrayGrad.create(scope, handle, flowIn, source);
  }

  /**
   * Builds an {@link UniqueWithCounts} operation
   *
   * @param x A `Tensor`.
   * @param axis A `Tensor` of type `int32` (default: None). The axis of the Tensor to
   * @return a new instance of UniqueWithCounts
   * @see org.tensorflow.op.core.UniqueWithCounts
   */
  public <T extends TType, U extends TNumber> UniqueWithCounts<T, TInt32> uniqueWithCounts(
      Operand<T> x, Operand<U> axis) {
    return UniqueWithCounts.create(scope, x, axis);
  }

  /**
   * Builds an {@link ScatterNdAdd} operation
   *
   * @param ref A mutable Tensor. Should be from a Variable node.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated values
   * @param options carries optional attributes values
   * @return a new instance of ScatterNdAdd
   * @see org.tensorflow.op.core.ScatterNdAdd
   */
  public <T extends TType, U extends TNumber> ScatterNdAdd<T> scatterNdAdd(Operand<T> ref,
      Operand<U> indices, Operand<T> updates, ScatterNdAdd.Options... options) {
    return ScatterNdAdd.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link HistogramFixedWidth} operation
   *
   * @param values Numeric `Tensor`.
   * @param valueRange Shape [2] `Tensor` of same `dtype` as `values`.
   * @param nbins Scalar `int32 Tensor`.  Number of histogram bins.
   * @param dtype 
   * @return a new instance of HistogramFixedWidth
   * @see org.tensorflow.op.core.HistogramFixedWidth
   */
  public <U extends TNumber, T extends TNumber> HistogramFixedWidth<U> histogramFixedWidth(
      Operand<T> values, Operand<T> valueRange, Operand<TInt32> nbins, DataType<U> dtype) {
    return HistogramFixedWidth.create(scope, values, valueRange, nbins, dtype);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data The value to put into the new constant.
   * @return an integer constant
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt32> constant(int data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Size} operation
   *
   * @param input 
   * @return a new instance of Size
   * @see org.tensorflow.op.core.Size
   */
  public <T extends TType> Size<TInt32> size(Operand<T> input) {
    return Size.create(scope, input);
  }

  /**
   * Builds an {@link OrderedMapUnstageNoKey} operation
   *
   * @param indices 
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of OrderedMapUnstageNoKey
   * @see org.tensorflow.op.core.OrderedMapUnstageNoKey
   */
  public OrderedMapUnstageNoKey orderedMapUnstageNoKey(Operand<TInt32> indices,
      List<DataType<?>> dtypes, OrderedMapUnstageNoKey.Options... options) {
    return OrderedMapUnstageNoKey.create(scope, indices, dtypes, options);
  }

  /**
   * Builds an {@link BroadcastDynamicShape} operation
   *
   * @param s0 
   * @param s1 
   * @return a new instance of BroadcastDynamicShape
   * @see org.tensorflow.op.core.BroadcastDynamicShape
   */
  public <T extends TNumber> BroadcastDynamicShape<T> broadcastDynamicShape(Operand<T> s0,
      Operand<T> s1) {
    return BroadcastDynamicShape.create(scope, s0, s1);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. String elements are
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TString> constant(byte[][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link TensorListScatterIntoExistingList} operation
   *
   * @param inputHandle 
   * @param tensor 
   * @param indices 
   * @return a new instance of TensorListScatterIntoExistingList
   * @see org.tensorflow.op.core.TensorListScatterIntoExistingList
   */
  public <T extends TType> TensorListScatterIntoExistingList tensorListScatterIntoExistingList(
      Operand<?> inputHandle, Operand<T> tensor, Operand<TInt32> indices) {
    return TensorListScatterIntoExistingList.create(scope, inputHandle, tensor, indices);
  }

  /**
   * Builds an {@link Rpc} operation
   *
   * @param address `0-D` or `1-D`.  The address (i.e. host_name:port) of the RPC server.
   * @param method `0-D` or `1-D`.  The method address on the RPC server.
   * @param request `0-D` or `1-D`.  Serialized proto strings: the rpc request argument.
   * @param options carries optional attributes values
   * @return a new instance of Rpc
   * @see org.tensorflow.op.core.Rpc
   */
  public Rpc rpc(Operand<TString> address, Operand<TString> method, Operand<TString> request,
      Rpc.Options... options) {
    return Rpc.create(scope, address, method, request, options);
  }

  /**
   * Builds an {@link DestroyResourceOp} operation
   *
   * @param resource handle to the resource to delete.
   * @param options carries optional attributes values
   * @return a new instance of DestroyResourceOp
   * @see org.tensorflow.op.core.DestroyResourceOp
   */
  public DestroyResourceOp destroyResourceOp(Operand<?> resource,
      DestroyResourceOp.Options... options) {
    return DestroyResourceOp.create(scope, resource, options);
  }

  /**
   * Builds an {@link TensorArrayScatter} operation
   *
   * @param handle The handle to a TensorArray.
   * @param indices The locations at which to write the tensor elements.
   * @param value The concatenated tensor to write to the TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @return a new instance of TensorArrayScatter
   * @see org.tensorflow.op.core.TensorArrayScatter
   */
  public <T extends TType> TensorArrayScatter tensorArrayScatter(Operand<?> handle,
      Operand<TInt32> indices, Operand<T> value, Operand<TFloat> flowIn) {
    return TensorArrayScatter.create(scope, handle, indices, value, flowIn);
  }

  /**
   * Builds an {@link BarrierInsertMany} operation
   *
   * @param handle The handle to a barrier.
   * @param keys A one-dimensional tensor of keys, with length n.
   * @param values An any-dimensional tensor of values, which are associated with the
   * @param componentIndex The component of the barrier elements that is being assigned.
   * @return a new instance of BarrierInsertMany
   * @see org.tensorflow.op.core.BarrierInsertMany
   */
  public <T extends TType> BarrierInsertMany barrierInsertMany(Operand<TString> handle,
      Operand<TString> keys, Operand<T> values, Long componentIndex) {
    return BarrierInsertMany.create(scope, handle, keys, values, componentIndex);
  }

  /**
   * Builds an {@link QuantizedReshape} operation
   *
   * @param tensor 
   * @param shape Defines the shape of the output tensor.
   * @param inputMin The minimum value of the input.
   * @param inputMax The maximum value of the input.
   * @return a new instance of QuantizedReshape
   * @see org.tensorflow.op.core.QuantizedReshape
   */
  public <T extends TType, U extends TNumber> QuantizedReshape<T> quantizedReshape(
      Operand<T> tensor, Operand<U> shape, Operand<TFloat> inputMin, Operand<TFloat> inputMax) {
    return QuantizedReshape.create(scope, tensor, shape, inputMin, inputMax);
  }

  /**
   * Builds an {@link Placeholder} operation
   *
   * @param dtype The type of elements in the tensor.
   * @param options carries optional attributes values
   * @return a new instance of Placeholder
   * @see org.tensorflow.op.core.Placeholder
   */
  public <T extends TType> Placeholder<T> placeholder(DataType<T> dtype,
      Placeholder.Options... options) {
    return Placeholder.create(scope, dtype, options);
  }

  /**
   * Builds an {@link ConsumeMutexLock} operation
   *
   * @param mutexLock A tensor returned by `MutexLock`.
   * @return a new instance of ConsumeMutexLock
   * @see org.tensorflow.op.core.ConsumeMutexLock
   */
  public ConsumeMutexLock consumeMutexLock(Operand<?> mutexLock) {
    return ConsumeMutexLock.create(scope, mutexLock);
  }

  /**
   * Builds an {@link MirrorPad} operation
   *
   * @param input The input tensor to be padded.
   * @param paddings A two-column matrix specifying the padding sizes. The number of
   * @param mode Either `REFLECT` or `SYMMETRIC`. In reflect mode the padded regions
   * @return a new instance of MirrorPad
   * @see org.tensorflow.op.core.MirrorPad
   */
  public <T extends TType, U extends TNumber> MirrorPad<T> mirrorPad(Operand<T> input,
      Operand<U> paddings, String mode) {
    return MirrorPad.create(scope, input, paddings, mode);
  }

  /**
   * Builds an {@link ResourceScatterUpdate} operation
   *
   * @param resource Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to add to `ref`.
   * @return a new instance of ResourceScatterUpdate
   * @see org.tensorflow.op.core.ResourceScatterUpdate
   */
  public <T extends TNumber, U extends TType> ResourceScatterUpdate resourceScatterUpdate(
      Operand<?> resource, Operand<T> indices, Operand<U> updates) {
    return ResourceScatterUpdate.create(scope, resource, indices, updates);
  }

  /**
   * Builds an {@link MapSize} operation
   *
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of MapSize
   * @see org.tensorflow.op.core.MapSize
   */
  public MapSize mapSize(List<DataType<?>> dtypes, MapSize.Options... options) {
    return MapSize.create(scope, dtypes, options);
  }

  /**
   * Builds an {@link ResourceScatterSub} operation
   *
   * @param resource Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to add to `ref`.
   * @return a new instance of ResourceScatterSub
   * @see org.tensorflow.op.core.ResourceScatterSub
   */
  public <T extends TNumber, U extends TType> ResourceScatterSub resourceScatterSub(
      Operand<?> resource, Operand<T> indices, Operand<U> updates) {
    return ResourceScatterSub.create(scope, resource, indices, updates);
  }

  /**
   * Builds an {@link VarHandleOp} operation
   *
   * @param dtype the type of this variable. Must agree with the dtypes
   * @param shape The (possibly partially specified) shape of this variable.
   * @param options carries optional attributes values
   * @return a new instance of VarHandleOp
   * @see org.tensorflow.op.core.VarHandleOp
   */
  public <T extends TType> VarHandleOp varHandleOp(DataType<T> dtype, Shape shape,
      VarHandleOp.Options... options) {
    return VarHandleOp.create(scope, dtype, shape, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TDouble> constant(double[][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TFloat> constant(float[][][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Squeeze} operation
   *
   * @param input The `input` to squeeze.
   * @param options carries optional attributes values
   * @return a new instance of Squeeze
   * @see org.tensorflow.op.core.Squeeze
   */
  public <T extends TType> Squeeze<T> squeeze(Operand<T> input, Squeeze.Options... options) {
    return Squeeze.create(scope, input, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TDouble> constant(double[][][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link TensorListResize} operation
   *
   * @param inputHandle 
   * @param size 
   * @return a new instance of TensorListResize
   * @see org.tensorflow.op.core.TensorListResize
   */
  public TensorListResize tensorListResize(Operand<?> inputHandle, Operand<TInt32> size) {
    return TensorListResize.create(scope, inputHandle, size);
  }

  /**
   * Builds an {@link OrderedMapUnstage} operation
   *
   * @param key 
   * @param indices 
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of OrderedMapUnstage
   * @see org.tensorflow.op.core.OrderedMapUnstage
   */
  public OrderedMapUnstage orderedMapUnstage(Operand<TInt64> key, Operand<TInt32> indices,
      List<DataType<?>> dtypes, OrderedMapUnstage.Options... options) {
    return OrderedMapUnstage.create(scope, key, indices, dtypes, options);
  }

  /**
   * Builds an {@link Abort} operation
   *
   * @param options carries optional attributes values
   * @return a new instance of Abort
   * @see org.tensorflow.op.core.Abort
   */
  public Abort abort(Abort.Options... options) {
    return Abort.create(scope, options);
  }

  /**
   * Builds an {@link Where} operation
   *
   * @param condition 
   * @return a new instance of Where
   * @see org.tensorflow.op.core.Where
   */
  public <T extends TType> Where where(Operand<T> condition) {
    return Where.create(scope, condition);
  }

  /**
   * Builds an {@link Shape} operation
   *
   * @param input 
   * @return a new instance of Shape
   * @see org.tensorflow.op.core.Shape
   */
  public <T extends TType> org.tensorflow.op.core.Shape<TInt32> shape(Operand<T> input) {
    return org.tensorflow.op.core.Shape.create(scope, input);
  }

  /**
   * Builds an {@link TensorArrayPack} operation
   *
   * @param handle 
   * @param flowIn 
   * @param dtype 
   * @param options carries optional attributes values
   * @return a new instance of TensorArrayPack
   * @see org.tensorflow.op.core.TensorArrayPack
   */
  public <T extends TType> TensorArrayPack<T> tensorArrayPack(Operand<TString> handle,
      Operand<TFloat> flowIn, DataType<T> dtype, TensorArrayPack.Options... options) {
    return TensorArrayPack.create(scope, handle, flowIn, dtype, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt64> constant(long[][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. String elements are
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TString> constant(byte[][][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link VarIsInitializedOp} operation
   *
   * @param resource the input resource handle.
   * @return a new instance of VarIsInitializedOp
   * @see org.tensorflow.op.core.VarIsInitializedOp
   */
  public VarIsInitializedOp varIsInitializedOp(Operand<?> resource) {
    return VarIsInitializedOp.create(scope, resource);
  }

  /**
   * Builds an {@link OrderedMapIncompleteSize} operation
   *
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of OrderedMapIncompleteSize
   * @see org.tensorflow.op.core.OrderedMapIncompleteSize
   */
  public OrderedMapIncompleteSize orderedMapIncompleteSize(List<DataType<?>> dtypes,
      OrderedMapIncompleteSize.Options... options) {
    return OrderedMapIncompleteSize.create(scope, dtypes, options);
  }

  /**
   * Builds an {@link TensorListElementShape} operation
   *
   * @param inputHandle 
   * @param shapeType 
   * @return a new instance of TensorListElementShape
   * @see org.tensorflow.op.core.TensorListElementShape
   */
  public <T extends TNumber> TensorListElementShape<T> tensorListElementShape(
      Operand<?> inputHandle, DataType<T> shapeType) {
    return TensorListElementShape.create(scope, inputHandle, shapeType);
  }

  /**
   * Builds an {@link ResourceScatterMax} operation
   *
   * @param resource Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to add to `ref`.
   * @return a new instance of ResourceScatterMax
   * @see org.tensorflow.op.core.ResourceScatterMax
   */
  public <T extends TNumber, U extends TType> ResourceScatterMax resourceScatterMax(
      Operand<?> resource, Operand<T> indices, Operand<U> updates) {
    return ResourceScatterMax.create(scope, resource, indices, updates);
  }

  /**
   * Builds an {@link TensorArrayGradWithShape} operation
   *
   * @param handle The handle to the forward TensorArray.
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param shapeToPrepend An int32 vector representing a shape. Elements in the gradient accumulator will
   * @param source The gradient source string, used to decide which gradient TensorArray
   * @return a new instance of TensorArrayGradWithShape
   * @see org.tensorflow.op.core.TensorArrayGradWithShape
   */
  public TensorArrayGradWithShape tensorArrayGradWithShape(Operand<?> handle,
      Operand<TFloat> flowIn, Operand<TInt32> shapeToPrepend, String source) {
    return TensorArrayGradWithShape.create(scope, handle, flowIn, shapeToPrepend, source);
  }

  /**
   * Builds an {@link LookupTableExport} operation
   *
   * @param tableHandle Handle to the table.
   * @param Tkeys 
   * @param Tvalues 
   * @return a new instance of LookupTableExport
   * @see org.tensorflow.op.core.LookupTableExport
   */
  public <T extends TType, U extends TType> LookupTableExport<T, U> lookupTableExport(
      Operand<?> tableHandle, DataType<T> Tkeys, DataType<U> Tvalues) {
    return LookupTableExport.create(scope, tableHandle, Tkeys, Tvalues);
  }

  /**
   * Builds an {@link UniqueWithCounts} operation
   *
   * @param x A `Tensor`.
   * @param axis A `Tensor` of type `int32` (default: None). The axis of the Tensor to
   * @param outIdx 
   * @return a new instance of UniqueWithCounts
   * @see org.tensorflow.op.core.UniqueWithCounts
   */
  public <T extends TType, V extends TNumber, U extends TNumber> UniqueWithCounts<T, V> uniqueWithCounts(
      Operand<T> x, Operand<U> axis, DataType<V> outIdx) {
    return UniqueWithCounts.create(scope, x, axis, outIdx);
  }

  /**
   * Builds an {@link HashTable} operation
   *
   * @param keyDtype Type of the table keys.
   * @param valueDtype Type of the table values.
   * @param options carries optional attributes values
   * @return a new instance of HashTable
   * @see org.tensorflow.op.core.HashTable
   */
  public <T extends TType, U extends TType> HashTable hashTable(DataType<T> keyDtype,
      DataType<U> valueDtype, HashTable.Options... options) {
    return HashTable.create(scope, keyDtype, valueDtype, options);
  }

  /**
   * Builds an {@link Assign} operation
   *
   * @param ref Should be from a `Variable` node. May be uninitialized.
   * @param value The value to be assigned to the variable.
   * @param options carries optional attributes values
   * @return a new instance of Assign
   * @see org.tensorflow.op.core.Assign
   */
  public <T extends TType> Assign<T> assign(Operand<T> ref, Operand<T> value,
      Assign.Options... options) {
    return Assign.create(scope, ref, value, options);
  }

  /**
   * Builds an {@link Zeros} operation
   *
   * @param dims a 1-D operand that represents the shape of the output tensor
   * @param type the output tensor datatype
   * @return a constant tensor initialized with zeros
   * @throws IllegalArgumentException if the tensor type or shape cannot be initialized with zeros.
   * @see org.tensorflow.op.core.Zeros
   */
  public <T extends TType, U extends TNumber> Zeros<T> zeros(Operand<U> dims, DataType<T> type) {
    return Zeros.create(scope, dims, type);
  }

  /**
   * Builds an {@link OrderedMapStage} operation
   *
   * @param key int64
   * @param indices 
   * @param values a list of tensors
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of OrderedMapStage
   * @see org.tensorflow.op.core.OrderedMapStage
   */
  public OrderedMapStage orderedMapStage(Operand<TInt64> key, Operand<TInt32> indices,
      Iterable<Operand<?>> values, List<DataType<?>> dtypes, OrderedMapStage.Options... options) {
    return OrderedMapStage.create(scope, key, indices, values, dtypes, options);
  }

  /**
   * Builds an {@link ResourceScatterMin} operation
   *
   * @param resource Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to add to `ref`.
   * @return a new instance of ResourceScatterMin
   * @see org.tensorflow.op.core.ResourceScatterMin
   */
  public <T extends TNumber, U extends TType> ResourceScatterMin resourceScatterMin(
      Operand<?> resource, Operand<T> indices, Operand<U> updates) {
    return ResourceScatterMin.create(scope, resource, indices, updates);
  }

  /**
   * Builds an {@link MapStage} operation
   *
   * @param key int64
   * @param indices 
   * @param values a list of tensors
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of MapStage
   * @see org.tensorflow.op.core.MapStage
   */
  public MapStage mapStage(Operand<TInt64> key, Operand<TInt32> indices,
      Iterable<Operand<?>> values, List<DataType<?>> dtypes, MapStage.Options... options) {
    return MapStage.create(scope, key, indices, values, dtypes, options);
  }

  /**
   * Builds an {@link ResourceStridedSliceAssign} operation
   *
   * @param ref 
   * @param begin 
   * @param end 
   * @param strides 
   * @param value 
   * @param options carries optional attributes values
   * @return a new instance of ResourceStridedSliceAssign
   * @see org.tensorflow.op.core.ResourceStridedSliceAssign
   */
  public <T extends TNumber, U extends TType> ResourceStridedSliceAssign resourceStridedSliceAssign(
      Operand<?> ref, Operand<T> begin, Operand<T> end, Operand<T> strides, Operand<U> value,
      ResourceStridedSliceAssign.Options... options) {
    return ResourceStridedSliceAssign.create(scope, ref, begin, end, strides, value, options);
  }

  /**
   * Builds an {@link Fingerprint} operation
   *
   * @param data Must have rank 1 or higher.
   * @param method Fingerprint method used by this op. Currently available method is
   * @return a new instance of Fingerprint
   * @see org.tensorflow.op.core.Fingerprint
   */
  public <T extends TType> Fingerprint fingerprint(Operand<T> data, Operand<TString> method) {
    return Fingerprint.create(scope, data, method);
  }

  /**
   * Builds an {@link ExpandDims} operation
   *
   * @param input 
   * @param axis 0-D (scalar). Specifies the dimension index at which to
   * @return a new instance of ExpandDims
   * @see org.tensorflow.op.core.ExpandDims
   */
  public <T extends TType, U extends TNumber> ExpandDims<T> expandDims(Operand<T> input,
      Operand<U> axis) {
    return ExpandDims.create(scope, input, axis);
  }

  /**
   * Builds an {@link GuaranteeConst} operation
   *
   * @param input 
   * @return a new instance of GuaranteeConst
   * @see org.tensorflow.op.core.GuaranteeConst
   */
  public <T extends TType> GuaranteeConst<T> guaranteeConst(Operand<T> input) {
    return GuaranteeConst.create(scope, input);
  }

  /**
   * Builds an {@link Reshape} operation
   *
   * @param tensor 
   * @param shape Defines the shape of the output tensor.
   * @return a new instance of Reshape
   * @see org.tensorflow.op.core.Reshape
   */
  public <T extends TType, U extends TNumber> Reshape<T> reshape(Operand<T> tensor,
      Operand<U> shape) {
    return Reshape.create(scope, tensor, shape);
  }

  /**
   * Builds an {@link DynamicPartition} operation
   *
   * @param data 
   * @param partitions Any shape.  Indices in the range `[0, num_partitions)`.
   * @param numPartitions The number of partitions to output.
   * @return a new instance of DynamicPartition
   * @see org.tensorflow.op.core.DynamicPartition
   */
  public <T extends TType> DynamicPartition<T> dynamicPartition(Operand<T> data,
      Operand<TInt32> partitions, Long numPartitions) {
    return DynamicPartition.create(scope, data, partitions, numPartitions);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TFloat> constant(float[][][][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param tensor a Tensor holding the constant value
   * @return a constant of the same data type as `tensor`
   * @see org.tensorflow.op.core.Constant
   */
  public <T extends TType> Constant<T> constant(Tensor<T> tensor) {
    return Constant.create(scope, tensor);
  }

  /**
   * Builds an {@link LookupTableImport} operation
   *
   * @param tableHandle Handle to the table.
   * @param keys Any shape.  Keys to look up.
   * @param values Values to associate with keys.
   * @return a new instance of LookupTableImport
   * @see org.tensorflow.op.core.LookupTableImport
   */
  public <T extends TType, U extends TType> LookupTableImport lookupTableImport(
      Operand<?> tableHandle, Operand<T> keys, Operand<U> values) {
    return LookupTableImport.create(scope, tableHandle, keys, values);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt32> constant(int[][][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return an integer constant
   * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt32> constant(long[] shape, IntBuffer data) {
    return Constant.create(scope, shape, data);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt64> constant(long[][][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link SetDiff1d} operation
   *
   * @param x 1-D. Values to keep.
   * @param y 1-D. Values to remove.
   * @param outIdx 
   * @return a new instance of SetDiff1d
   * @see org.tensorflow.op.core.SetDiff1d
   */
  public <T extends TType, U extends TNumber> SetDiff1d<T, U> setDiff1d(Operand<T> x, Operand<T> y,
      DataType<U> outIdx) {
    return SetDiff1d.create(scope, x, y, outIdx);
  }

  /**
   * Builds an {@link HistogramFixedWidth} operation
   *
   * @param values Numeric `Tensor`.
   * @param valueRange Shape [2] `Tensor` of same `dtype` as `values`.
   * @param nbins Scalar `int32 Tensor`.  Number of histogram bins.
   * @return a new instance of HistogramFixedWidth
   * @see org.tensorflow.op.core.HistogramFixedWidth
   */
  public <T extends TNumber> HistogramFixedWidth<TInt32> histogramFixedWidth(Operand<T> values,
      Operand<T> valueRange, Operand<TInt32> nbins) {
    return HistogramFixedWidth.create(scope, values, valueRange, nbins);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TBool> constant(boolean[][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link MapUnstage} operation
   *
   * @param key 
   * @param indices 
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of MapUnstage
   * @see org.tensorflow.op.core.MapUnstage
   */
  public MapUnstage mapUnstage(Operand<TInt64> key, Operand<TInt32> indices,
      List<DataType<?>> dtypes, MapUnstage.Options... options) {
    return MapUnstage.create(scope, key, indices, dtypes, options);
  }

  /**
   * Builds an {@link VariableShape} operation
   *
   * @param input 
   * @param outType 
   * @return a new instance of VariableShape
   * @see org.tensorflow.op.core.VariableShape
   */
  public <T extends TNumber> VariableShape<T> variableShape(Operand<?> input, DataType<T> outType) {
    return VariableShape.create(scope, input, outType);
  }

  /**
   * Builds an {@link ScatterNd} operation
   *
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @param shape 1-D. The shape of the resulting tensor.
   * @return a new instance of ScatterNd
   * @see org.tensorflow.op.core.ScatterNd
   */
  public <U extends TType, T extends TNumber> ScatterNd<U> scatterNd(Operand<T> indices,
      Operand<U> updates, Operand<T> shape) {
    return ScatterNd.create(scope, indices, updates, shape);
  }

  /**
   * Builds an {@link ScatterSub} operation
   *
   * @param ref Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to subtract from `ref`.
   * @param options carries optional attributes values
   * @return a new instance of ScatterSub
   * @see org.tensorflow.op.core.ScatterSub
   */
  public <T extends TType, U extends TNumber> ScatterSub<T> scatterSub(Operand<T> ref,
      Operand<U> indices, Operand<T> updates, ScatterSub.Options... options) {
    return ScatterSub.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link BarrierClose} operation
   *
   * @param handle The handle to a barrier.
   * @param options carries optional attributes values
   * @return a new instance of BarrierClose
   * @see org.tensorflow.op.core.BarrierClose
   */
  public BarrierClose barrierClose(Operand<TString> handle, BarrierClose.Options... options) {
    return BarrierClose.create(scope, handle, options);
  }

  /**
   * Builds an {@link UnbatchGrad} operation
   *
   * @param originalInput 
   * @param batchIndex 
   * @param grad 
   * @param id 
   * @param options carries optional attributes values
   * @return a new instance of UnbatchGrad
   * @see org.tensorflow.op.core.UnbatchGrad
   */
  public <T extends TType> UnbatchGrad<T> unbatchGrad(Operand<T> originalInput,
      Operand<TInt64> batchIndex, Operand<T> grad, Operand<TInt64> id,
      UnbatchGrad.Options... options) {
    return UnbatchGrad.create(scope, originalInput, batchIndex, grad, id, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param type the tensor datatype.
   * @param shape the tensor shape.
   * @param data a buffer containing the tensor data.
   * @return a constant of type `type`
   * @throws IllegalArgumentException If the tensor datatype or shape is not compatible with the
   * @see org.tensorflow.op.core.Constant
   */
  public <T extends TType> Constant<T> constant(DataType<T> type, long[] shape, ByteBuffer data) {
    return Constant.create(scope, type, shape, data);
  }

  /**
   * Builds an {@link IdentityN} operation
   *
   * @param input 
   * @return a new instance of IdentityN
   * @see org.tensorflow.op.core.IdentityN
   */
  public IdentityN identityN(Iterable<Operand<?>> input) {
    return IdentityN.create(scope, input);
  }

  /**
   * Builds an {@link StridedSlice} operation
   *
   * @param input 
   * @param begin `begin[k]` specifies the offset into the `k`th range specification.
   * @param end `end[i]` is like `begin` with the exception that `end_mask` is
   * @param strides `strides[i]` specifies the increment in the `i`th specification
   * @param options carries optional attributes values
   * @return a new instance of StridedSlice
   * @see org.tensorflow.op.core.StridedSlice
   */
  public <T extends TType, U extends TNumber> StridedSlice<T> stridedSlice(Operand<T> input,
      Operand<U> begin, Operand<U> end, Operand<U> strides, StridedSlice.Options... options) {
    return StridedSlice.create(scope, input, begin, end, strides, options);
  }

  /**
   * Builds an {@link ReadVariableOp} operation
   *
   * @param resource handle to the resource in which to store the variable.
   * @param dtype the dtype of the value.
   * @return a new instance of ReadVariableOp
   * @see org.tensorflow.op.core.ReadVariableOp
   */
  public <T extends TType> ReadVariableOp<T> readVariableOp(Operand<?> resource,
      DataType<T> dtype) {
    return ReadVariableOp.create(scope, resource, dtype);
  }

  /**
   * Builds an {@link RefNextIteration} operation
   *
   * @param data The tensor to be made available to the next iteration.
   * @return a new instance of RefNextIteration
   * @see org.tensorflow.op.core.RefNextIteration
   */
  public <T extends TType> RefNextIteration<T> refNextIteration(Operand<T> data) {
    return RefNextIteration.create(scope, data);
  }

  /**
   * Builds an {@link RefSelect} operation
   *
   * @param index A scalar that determines the input that gets selected.
   * @param inputs A list of ref tensors, one of which will be forwarded to `output`.
   * @return a new instance of RefSelect
   * @see org.tensorflow.op.core.RefSelect
   */
  public <T extends TType> RefSelect<T> refSelect(Operand<TInt32> index,
      Iterable<Operand<T>> inputs) {
    return RefSelect.create(scope, index, inputs);
  }

  /**
   * Builds an {@link ScatterNdSub} operation
   *
   * @param ref A mutable Tensor. Should be from a Variable node.
   * @param indices A Tensor. Must be one of the following types: int32, int64.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated values
   * @param options carries optional attributes values
   * @return a new instance of ScatterNdSub
   * @see org.tensorflow.op.core.ScatterNdSub
   */
  public <T extends TType, U extends TNumber> ScatterNdSub<T> scatterNdSub(Operand<T> ref,
      Operand<U> indices, Operand<T> updates, ScatterNdSub.Options... options) {
    return ScatterNdSub.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link TemporaryVariable} operation
   *
   * @param shape The shape of the variable tensor.
   * @param dtype The type of elements in the variable tensor.
   * @param options carries optional attributes values
   * @return a new instance of TemporaryVariable
   * @see org.tensorflow.op.core.TemporaryVariable
   */
  public <T extends TType> TemporaryVariable<T> temporaryVariable(Shape shape, DataType<T> dtype,
      TemporaryVariable.Options... options) {
    return TemporaryVariable.create(scope, shape, dtype, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt32> constant(int[][][][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link GetSessionTensor} operation
   *
   * @param handle The handle for a tensor stored in the session state.
   * @param dtype The type of the output value.
   * @return a new instance of GetSessionTensor
   * @see org.tensorflow.op.core.GetSessionTensor
   */
  public <T extends TType> GetSessionTensor<T> getSessionTensor(Operand<TString> handle,
      DataType<T> dtype) {
    return GetSessionTensor.create(scope, handle, dtype);
  }

  /**
   * Builds an {@link StagePeek} operation
   *
   * @param index 
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of StagePeek
   * @see org.tensorflow.op.core.StagePeek
   */
  public StagePeek stagePeek(Operand<TInt32> index, List<DataType<?>> dtypes,
      StagePeek.Options... options) {
    return StagePeek.create(scope, index, dtypes, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data The value to put into the new constant.
   * @return a double constant
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TDouble> constant(double data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link ScatterUpdate} operation
   *
   * @param ref Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to store in `ref`.
   * @param options carries optional attributes values
   * @return a new instance of ScatterUpdate
   * @see org.tensorflow.op.core.ScatterUpdate
   */
  public <T extends TType, U extends TNumber> ScatterUpdate<T> scatterUpdate(Operand<T> ref,
      Operand<U> indices, Operand<T> updates, ScatterUpdate.Options... options) {
    return ScatterUpdate.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link Roll} operation
   *
   * @param input 
   * @param shift Dimension must be 0-D or 1-D. `shift[i]` specifies the number of places by which
   * @param axis Dimension must be 0-D or 1-D. `axis[i]` specifies the dimension that the shift
   * @return a new instance of Roll
   * @see org.tensorflow.op.core.Roll
   */
  public <T extends TType, U extends TNumber, V extends TNumber> Roll<T> roll(Operand<T> input,
      Operand<U> shift, Operand<V> axis) {
    return Roll.create(scope, input, shift, axis);
  }

  /**
   * Builds an {@link Bitcast} operation
   *
   * @param input 
   * @param type 
   * @return a new instance of Bitcast
   * @see org.tensorflow.op.core.Bitcast
   */
  public <U extends TType, T extends TType> Bitcast<U> bitcast(Operand<T> input, DataType<U> type) {
    return Bitcast.create(scope, input, type);
  }

  /**
   * Builds an {@link MapIncompleteSize} operation
   *
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of MapIncompleteSize
   * @see org.tensorflow.op.core.MapIncompleteSize
   */
  public MapIncompleteSize mapIncompleteSize(List<DataType<?>> dtypes,
      MapIncompleteSize.Options... options) {
    return MapIncompleteSize.create(scope, dtypes, options);
  }

  /**
   * Builds an {@link TensorListPopBack} operation
   *
   * @param inputHandle 
   * @param elementShape 
   * @param elementDtype 
   * @return a new instance of TensorListPopBack
   * @see org.tensorflow.op.core.TensorListPopBack
   */
  public <T extends TType> TensorListPopBack<T> tensorListPopBack(Operand<?> inputHandle,
      Operand<TInt32> elementShape, DataType<T> elementDtype) {
    return TensorListPopBack.create(scope, inputHandle, elementShape, elementDtype);
  }

  /**
   * Builds an {@link MapUnstageNoKey} operation
   *
   * @param indices 
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of MapUnstageNoKey
   * @see org.tensorflow.op.core.MapUnstageNoKey
   */
  public MapUnstageNoKey mapUnstageNoKey(Operand<TInt32> indices, List<DataType<?>> dtypes,
      MapUnstageNoKey.Options... options) {
    return MapUnstageNoKey.create(scope, indices, dtypes, options);
  }

  /**
   * Builds an {@link ReduceMin} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of ReduceMin
   * @see org.tensorflow.op.core.ReduceMin
   */
  public <T extends TType, U extends TNumber> ReduceMin<T> reduceMin(Operand<T> input,
      Operand<U> axis, ReduceMin.Options... options) {
    return ReduceMin.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link ExtractVolumePatches} operation
   *
   * @param input 5-D Tensor with shape `[batch, in_planes, in_rows, in_cols, depth]`.
   * @param ksizes The size of the sliding window for each dimension of `input`.
   * @param strides 1-D of length 5. How far the centers of two consecutive patches are in
   * @param padding The type of padding algorithm to use.
   * @return a new instance of ExtractVolumePatches
   * @see org.tensorflow.op.core.ExtractVolumePatches
   */
  public <T extends TNumber> ExtractVolumePatches<T> extractVolumePatches(Operand<T> input,
      List<Long> ksizes, List<Long> strides, String padding) {
    return ExtractVolumePatches.create(scope, input, ksizes, strides, padding);
  }

  /**
   * Builds an {@link Identity} operation
   *
   * @param input 
   * @return a new instance of Identity
   * @see org.tensorflow.op.core.Identity
   */
  public <T extends TType> Identity<T> identity(Operand<T> input) {
    return Identity.create(scope, input);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TFloat> constant(float[][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link ScatterDiv} operation
   *
   * @param ref Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of values that `ref` is divided by.
   * @param options carries optional attributes values
   * @return a new instance of ScatterDiv
   * @see org.tensorflow.op.core.ScatterDiv
   */
  public <T extends TType, U extends TNumber> ScatterDiv<T> scatterDiv(Operand<T> ref,
      Operand<U> indices, Operand<T> updates, ScatterDiv.Options... options) {
    return ScatterDiv.create(scope, ref, indices, updates, options);
  }

  /**
   * Builds an {@link ResourceScatterMul} operation
   *
   * @param resource Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to add to `ref`.
   * @return a new instance of ResourceScatterMul
   * @see org.tensorflow.op.core.ResourceScatterMul
   */
  public <T extends TNumber, U extends TType> ResourceScatterMul resourceScatterMul(
      Operand<?> resource, Operand<T> indices, Operand<U> updates) {
    return ResourceScatterMul.create(scope, resource, indices, updates);
  }

  /**
   * Builds an {@link TensorArrayRead} operation
   *
   * @param handle The handle to a TensorArray.
   * @param index 
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param dtype The type of the elem that is returned.
   * @return a new instance of TensorArrayRead
   * @see org.tensorflow.op.core.TensorArrayRead
   */
  public <T extends TType> TensorArrayRead<T> tensorArrayRead(Operand<?> handle,
      Operand<TInt32> index, Operand<TFloat> flowIn, DataType<T> dtype) {
    return TensorArrayRead.create(scope, handle, index, flowIn, dtype);
  }

  /**
   * Builds an {@link StridedSliceGrad} operation
   *
   * @param shape 
   * @param begin 
   * @param end 
   * @param strides 
   * @param dy 
   * @param options carries optional attributes values
   * @return a new instance of StridedSliceGrad
   * @see org.tensorflow.op.core.StridedSliceGrad
   */
  public <U extends TType, T extends TNumber> StridedSliceGrad<U> stridedSliceGrad(Operand<T> shape,
      Operand<T> begin, Operand<T> end, Operand<T> strides, Operand<U> dy,
      StridedSliceGrad.Options... options) {
    return StridedSliceGrad.create(scope, shape, begin, end, strides, dy, options);
  }

  /**
   * Builds an {@link SpaceToBatchNd} operation
   *
   * @param input N-D with shape `input_shape = [batch] + spatial_shape + remaining_shape`,
   * @param blockShape 1-D with shape `[M]`, all values must be >= 1.
   * @param paddings 2-D with shape `[M, 2]`, all values must be >= 0.
   * @return a new instance of SpaceToBatchNd
   * @see org.tensorflow.op.core.SpaceToBatchNd
   */
  public <T extends TType, U extends TNumber, V extends TNumber> SpaceToBatchNd<T> spaceToBatchNd(
      Operand<T> input, Operand<U> blockShape, Operand<V> paddings) {
    return SpaceToBatchNd.create(scope, input, blockShape, paddings);
  }

  /**
   * Builds an {@link TensorListGather} operation
   *
   * @param inputHandle 
   * @param indices 
   * @param elementShape 
   * @param elementDtype 
   * @return a new instance of TensorListGather
   * @see org.tensorflow.op.core.TensorListGather
   */
  public <T extends TType> TensorListGather<T> tensorListGather(Operand<?> inputHandle,
      Operand<TInt32> indices, Operand<TInt32> elementShape, DataType<T> elementDtype) {
    return TensorListGather.create(scope, inputHandle, indices, elementShape, elementDtype);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt32> constant(int[][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link EmptyTensorList} operation
   *
   * @param elementShape 
   * @param maxNumElements 
   * @param elementDtype 
   * @return a new instance of EmptyTensorList
   * @see org.tensorflow.op.core.EmptyTensorList
   */
  public <T extends TNumber, U extends TType> EmptyTensorList emptyTensorList(
      Operand<T> elementShape, Operand<TInt32> maxNumElements, DataType<U> elementDtype) {
    return EmptyTensorList.create(scope, elementShape, maxNumElements, elementDtype);
  }

  /**
   * Builds an {@link Gather} operation
   *
   * @param params The tensor from which to gather values. Must be at least rank
   * @param indices Index tensor. Must be in range `[0, params.shape[axis])`.
   * @param axis The axis in `params` to gather `indices` from. Defaults to the first
   * @param options carries optional attributes values
   * @return a new instance of Gather
   * @see org.tensorflow.op.core.Gather
   */
  public <T extends TType, U extends TNumber, V extends TNumber> Gather<T> gather(Operand<T> params,
      Operand<U> indices, Operand<V> axis, Gather.Options... options) {
    return Gather.create(scope, params, indices, axis, options);
  }

  /**
   * Builds an {@link Variable} operation
   *
   * @param shape The shape of the variable tensor.
   * @param dtype The type of elements in the variable tensor.
   * @param options carries optional attributes values
   * @return a new instance of Variable
   * @see org.tensorflow.op.core.Variable
   */
  public <T extends TType> Variable<T> variable(Shape shape, DataType<T> dtype,
      Variable.Options... options) {
    return Variable.create(scope, shape, dtype, options);
  }

  /**
   * Builds an {@link DeleteSessionTensor} operation
   *
   * @param handle The handle for a tensor stored in the session state.
   * @return a new instance of DeleteSessionTensor
   * @see org.tensorflow.op.core.DeleteSessionTensor
   */
  public DeleteSessionTensor deleteSessionTensor(Operand<TString> handle) {
    return DeleteSessionTensor.create(scope, handle);
  }

  /**
   * Builds an {@link TensorListPushBackBatch} operation
   *
   * @param inputHandles 
   * @param tensor 
   * @return a new instance of TensorListPushBackBatch
   * @see org.tensorflow.op.core.TensorListPushBackBatch
   */
  public <T extends TType> TensorListPushBackBatch tensorListPushBackBatch(Operand<?> inputHandles,
      Operand<T> tensor) {
    return TensorListPushBackBatch.create(scope, inputHandles, tensor);
  }

  /**
   * Builds an {@link TensorListSplit} operation
   *
   * @param tensor 
   * @param elementShape 
   * @param lengths 
   * @return a new instance of TensorListSplit
   * @see org.tensorflow.op.core.TensorListSplit
   */
  public <T extends TType, U extends TNumber> TensorListSplit tensorListSplit(Operand<T> tensor,
      Operand<U> elementShape, Operand<TInt64> lengths) {
    return TensorListSplit.create(scope, tensor, elementShape, lengths);
  }

  /**
   * Builds an {@link TensorListFromTensor} operation
   *
   * @param tensor 
   * @param elementShape 
   * @return a new instance of TensorListFromTensor
   * @see org.tensorflow.op.core.TensorListFromTensor
   */
  public <T extends TType, U extends TNumber> TensorListFromTensor tensorListFromTensor(
      Operand<T> tensor, Operand<U> elementShape) {
    return TensorListFromTensor.create(scope, tensor, elementShape);
  }

  /**
   * Builds an {@link TensorScatterNdSub} operation
   *
   * @param tensor Tensor to copy/update.
   * @param indices Index tensor.
   * @param updates Updates to scatter into output.
   * @return a new instance of TensorScatterNdSub
   * @see org.tensorflow.op.core.TensorScatterNdSub
   */
  public <T extends TType, U extends TNumber> TensorScatterNdSub<T> tensorScatterNdSub(
      Operand<T> tensor, Operand<U> indices, Operand<T> updates) {
    return TensorScatterNdSub.create(scope, tensor, indices, updates);
  }

  /**
   * Builds an {@link ImmutableConst} operation
   *
   * @param dtype Type of the returned tensor.
   * @param shape Shape of the returned tensor.
   * @param memoryRegionName Name of readonly memory region used by the tensor, see
   * @return a new instance of ImmutableConst
   * @see org.tensorflow.op.core.ImmutableConst
   */
  public <T extends TType> ImmutableConst<T> immutableConst(DataType<T> dtype, Shape shape,
      String memoryRegionName) {
    return ImmutableConst.create(scope, dtype, shape, memoryRegionName);
  }

  /**
   * Builds an {@link Pad} operation
   *
   * @param input 
   * @param paddings 
   * @param constantValues 
   * @return a new instance of Pad
   * @see org.tensorflow.op.core.Pad
   */
  public <T extends TType, U extends TNumber> Pad<T> pad(Operand<T> input, Operand<U> paddings,
      Operand<T> constantValues) {
    return Pad.create(scope, input, paddings, constantValues);
  }

  /**
   * Builds an {@link Range} operation
   *
   * @param start 0-D (scalar). First entry in the sequence.
   * @param limit 0-D (scalar). Upper limit of sequence, exclusive.
   * @param delta 0-D (scalar). Optional. Default is 1. Number that increments `start`.
   * @return a new instance of Range
   * @see org.tensorflow.op.core.Range
   */
  public <T extends TNumber> Range<T> range(Operand<T> start, Operand<T> limit, Operand<T> delta) {
    return Range.create(scope, start, limit, delta);
  }

  /**
   * Builds an {@link BarrierTakeMany} operation
   *
   * @param handle The handle to a barrier.
   * @param numElements A single-element tensor containing the number of elements to
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attributes values
   * @return a new instance of BarrierTakeMany
   * @see org.tensorflow.op.core.BarrierTakeMany
   */
  public BarrierTakeMany barrierTakeMany(Operand<TString> handle, Operand<TInt32> numElements,
      List<DataType<?>> componentTypes, BarrierTakeMany.Options... options) {
    return BarrierTakeMany.create(scope, handle, numElements, componentTypes, options);
  }

  /**
   * Builds an {@link MlirPassthroughOp} operation
   *
   * @param inputs 
   * @param mlirModule 
   * @param Toutputs 
   * @return a new instance of MlirPassthroughOp
   * @see org.tensorflow.op.core.MlirPassthroughOp
   */
  public MlirPassthroughOp mlirPassthroughOp(Iterable<Operand<?>> inputs, String mlirModule,
      List<DataType<?>> Toutputs) {
    return MlirPassthroughOp.create(scope, inputs, mlirModule, Toutputs);
  }

  /**
   * Builds an {@link Sum} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of Sum
   * @see org.tensorflow.op.core.Sum
   */
  public <T extends TType, U extends TNumber> Sum<T> sum(Operand<T> input, Operand<U> axis,
      Sum.Options... options) {
    return Sum.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link Shape} operation
   *
   * @param input 
   * @param outType 
   * @return a new instance of Shape
   * @see org.tensorflow.op.core.Shape
   */
  public <U extends TNumber, T extends TType> org.tensorflow.op.core.Shape<U> shape(
      Operand<T> input, DataType<U> outType) {
    return org.tensorflow.op.core.Shape.create(scope, input, outType);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TDouble> constant(double[][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link GetSessionHandle} operation
   *
   * @param value The tensor to be stored.
   * @return a new instance of GetSessionHandle
   * @see org.tensorflow.op.core.GetSessionHandle
   */
  public <T extends TType> GetSessionHandle getSessionHandle(Operand<T> value) {
    return GetSessionHandle.create(scope, value);
  }

  /**
   * Builds an {@link EditDistance} operation
   *
   * @param hypothesisIndices The indices of the hypothesis list SparseTensor.
   * @param hypothesisValues The values of the hypothesis list SparseTensor.
   * @param hypothesisShape The shape of the hypothesis list SparseTensor.
   * @param truthIndices The indices of the truth list SparseTensor.
   * @param truthValues The values of the truth list SparseTensor.
   * @param truthShape truth indices, vector.
   * @param options carries optional attributes values
   * @return a new instance of EditDistance
   * @see org.tensorflow.op.core.EditDistance
   */
  public <T extends TType> EditDistance editDistance(Operand<TInt64> hypothesisIndices,
      Operand<T> hypothesisValues, Operand<TInt64> hypothesisShape, Operand<TInt64> truthIndices,
      Operand<T> truthValues, Operand<TInt64> truthShape, EditDistance.Options... options) {
    return EditDistance.create(scope, hypothesisIndices, hypothesisValues, hypothesisShape, truthIndices, truthValues, truthShape, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TFloat> constant(float[][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link TensorArrayUnpack} operation
   *
   * @param handle 
   * @param value 
   * @param flowIn 
   * @return a new instance of TensorArrayUnpack
   * @see org.tensorflow.op.core.TensorArrayUnpack
   */
  public <T extends TType> TensorArrayUnpack tensorArrayUnpack(Operand<TString> handle,
      Operand<T> value, Operand<TFloat> flowIn) {
    return TensorArrayUnpack.create(scope, handle, value, flowIn);
  }

  /**
   * Builds an {@link MapClear} operation
   *
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of MapClear
   * @see org.tensorflow.op.core.MapClear
   */
  public MapClear mapClear(List<DataType<?>> dtypes, MapClear.Options... options) {
    return MapClear.create(scope, dtypes, options);
  }

  /**
   * Builds an {@link LookupTableSize} operation
   *
   * @param tableHandle Handle to the table.
   * @return a new instance of LookupTableSize
   * @see org.tensorflow.op.core.LookupTableSize
   */
  public LookupTableSize lookupTableSize(Operand<?> tableHandle) {
    return LookupTableSize.create(scope, tableHandle);
  }

  /**
   * Builds an {@link ReduceAny} operation
   *
   * @param input The tensor to reduce.
   * @param axis The dimensions to reduce. Must be in the range
   * @param options carries optional attributes values
   * @return a new instance of ReduceAny
   * @see org.tensorflow.op.core.ReduceAny
   */
  public <T extends TNumber> ReduceAny reduceAny(Operand<TBool> input, Operand<T> axis,
      ReduceAny.Options... options) {
    return ReduceAny.create(scope, input, axis, options);
  }

  /**
   * Builds an {@link ParallelDynamicStitch} operation
   *
   * @param indices 
   * @param data 
   * @return a new instance of ParallelDynamicStitch
   * @see org.tensorflow.op.core.ParallelDynamicStitch
   */
  public <T extends TType> ParallelDynamicStitch<T> parallelDynamicStitch(
      Iterable<Operand<TInt32>> indices, Iterable<Operand<T>> data) {
    return ParallelDynamicStitch.create(scope, indices, data);
  }

  /**
   * Builds an {@link ScatterNdNonAliasingAdd} operation
   *
   * @param input A Tensor.
   * @param indices A Tensor. Must be one of the following types: `int32`, `int64`.
   * @param updates A Tensor. Must have the same type as ref. A tensor of updated values
   * @return a new instance of ScatterNdNonAliasingAdd
   * @see org.tensorflow.op.core.ScatterNdNonAliasingAdd
   */
  public <T extends TType, U extends TNumber> ScatterNdNonAliasingAdd<T> scatterNdNonAliasingAdd(
      Operand<T> input, Operand<U> indices, Operand<T> updates) {
    return ScatterNdNonAliasingAdd.create(scope, input, indices, updates);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt64> constant(long[][][][][][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link SplitV} operation
   *
   * @param value The tensor to split.
   * @param sizeSplits list containing the sizes of each output tensor along the split
   * @param axis 0-D.  The dimension along which to split.  Must be in the range
   * @param numSplit 
   * @return a new instance of SplitV
   * @see org.tensorflow.op.core.SplitV
   */
  public <T extends TType, U extends TNumber> SplitV<T> splitV(Operand<T> value,
      Operand<U> sizeSplits, Operand<TInt32> axis, Long numSplit) {
    return SplitV.create(scope, value, sizeSplits, axis, numSplit);
  }

  /**
   * Builds an {@link DestroyTemporaryVariable} operation
   *
   * @param ref A reference to the temporary variable tensor.
   * @param varName Name of the temporary variable, usually the name of the matching
   * @return a new instance of DestroyTemporaryVariable
   * @see org.tensorflow.op.core.DestroyTemporaryVariable
   */
  public <T extends TType> DestroyTemporaryVariable<T> destroyTemporaryVariable(Operand<T> ref,
      String varName) {
    return DestroyTemporaryVariable.create(scope, ref, varName);
  }

  /**
   * Builds an {@link StageSize} operation
   *
   * @param dtypes 
   * @param options carries optional attributes values
   * @return a new instance of StageSize
   * @see org.tensorflow.op.core.StageSize
   */
  public StageSize stageSize(List<DataType<?>> dtypes, StageSize.Options... options) {
    return StageSize.create(scope, dtypes, options);
  }

  /**
   * Builds an {@link InplaceUpdate} operation
   *
   * @param x A tensor of type `T`.
   * @param i A vector. Indices into the left-most dimension of `x`.
   * @param v A `Tensor` of type T. Same dimension sizes as x except the first dimension, which must be the same as i's size.
   * @return a new instance of InplaceUpdate
   * @see org.tensorflow.op.core.InplaceUpdate
   */
  public <T extends TType> InplaceUpdate<T> inplaceUpdate(Operand<T> x, Operand<TInt32> i,
      Operand<T> v) {
    return InplaceUpdate.create(scope, x, i, v);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data An array containing the values to put into the new constant. The dimensions of the
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TInt64> constant(long[][] data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link LoopCond} operation
   *
   * @param input A boolean scalar, representing the branch predicate of the Switch op.
   * @return a new instance of LoopCond
   * @see org.tensorflow.op.core.LoopCond
   */
  public LoopCond loopCond(Operand<TBool> input) {
    return LoopCond.create(scope, input);
  }

  /**
   * Builds an {@link ResourceGatherNd} operation
   *
   * @param resource 
   * @param indices 
   * @param dtype 
   * @return a new instance of ResourceGatherNd
   * @see org.tensorflow.op.core.ResourceGatherNd
   */
  public <U extends TType, T extends TNumber> ResourceGatherNd<U> resourceGatherNd(
      Operand<?> resource, Operand<T> indices, DataType<U> dtype) {
    return ResourceGatherNd.create(scope, resource, indices, dtype);
  }

  /**
   * Builds an {@link TensorArraySplit} operation
   *
   * @param handle The handle to a TensorArray.
   * @param value The concatenated tensor to write to the TensorArray.
   * @param lengths The vector of lengths, how to split the rows of value into the
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @return a new instance of TensorArraySplit
   * @see org.tensorflow.op.core.TensorArraySplit
   */
  public <T extends TType> TensorArraySplit tensorArraySplit(Operand<?> handle, Operand<T> value,
      Operand<TInt64> lengths, Operand<TFloat> flowIn) {
    return TensorArraySplit.create(scope, handle, value, lengths, flowIn);
  }

  /**
   * Builds an {@link ResourceScatterDiv} operation
   *
   * @param resource Should be from a `Variable` node.
   * @param indices A tensor of indices into the first dimension of `ref`.
   * @param updates A tensor of updated values to add to `ref`.
   * @return a new instance of ResourceScatterDiv
   * @see org.tensorflow.op.core.ResourceScatterDiv
   */
  public <T extends TNumber, U extends TType> ResourceScatterDiv resourceScatterDiv(
      Operand<?> resource, Operand<T> indices, Operand<U> updates) {
    return ResourceScatterDiv.create(scope, resource, indices, updates);
  }

  /**
   * Builds an {@link Barrier} operation
   *
   * @param componentTypes The type of each component in a value.
   * @param options carries optional attributes values
   * @return a new instance of Barrier
   * @see org.tensorflow.op.core.Barrier
   */
  public Barrier barrier(List<DataType<?>> componentTypes, Barrier.Options... options) {
    return Barrier.create(scope, componentTypes, options);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param charset The encoding from String to bytes.
   * @param data The string to put into the new constant.
   * @return a string constant
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TString> constant(String data, Charset charset) {
    return Constant.create(scope, data, charset);
  }

  /**
   * Builds an {@link TensorListSetItem} operation
   *
   * @param inputHandle 
   * @param index 
   * @param item 
   * @return a new instance of TensorListSetItem
   * @see org.tensorflow.op.core.TensorListSetItem
   */
  public <T extends TType> TensorListSetItem tensorListSetItem(Operand<?> inputHandle,
      Operand<TInt32> index, Operand<T> item) {
    return TensorListSetItem.create(scope, inputHandle, index, item);
  }

  /**
   * Builds an {@link ControlTrigger} operation
   *
   * @return a new instance of ControlTrigger
   * @see org.tensorflow.op.core.ControlTrigger
   */
  public ControlTrigger controlTrigger() {
    return ControlTrigger.create(scope);
  }

  /**
   * Builds an {@link Constant} operation
   *
   * @param data The value to put into the new constant.
   * @return a boolean constant
   * @see org.tensorflow.op.core.Constant
   */
  public Constant<TBool> constant(boolean data) {
    return Constant.create(scope, data);
  }

  /**
   * Builds an {@link OneHot} operation
   *
   * @param indices A tensor of indices.
   * @param depth A scalar defining the depth of the one hot dimension.
   * @param onValue A scalar defining the value to fill in output when `indices[j] = i`.
   * @param offValue A scalar defining the value to fill in output when `indices[j] != i`.
   * @param options carries optional attributes values
   * @return a new instance of OneHot
   * @see org.tensorflow.op.core.OneHot
   */
  public <U extends TType, T extends TNumber> OneHot<U> oneHot(Operand<T> indices,
      Operand<TInt32> depth, Operand<U> onValue, Operand<U> offValue, OneHot.Options... options) {
    return OneHot.create(scope, indices, depth, onValue, offValue, options);
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
   * Returns an API that adds operations to the graph with the provided control dependencies.
   *
   * @see {@link Scope#withControlDependencies(Iterable<Operand<?>>)}
   */
  public Ops withControlDependencies(Iterable<Operand<?>> controls) {
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
    return new Ops(new Scope(env));
  }

  /**
   * Creates an API for building operations in the default eager execution environment
   *
   * <p>Invoking this method is equivalent to {@code Ops.create(EagerSession.getDefault())}.
   */
  public static Ops create() {
    return new Ops(new Scope(EagerSession.getDefault()));
  }
}
