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

import java.nio.charset.Charset
import kotlin.Array
import kotlin.BooleanArray
import kotlin.Byte
import kotlin.ByteArray
import kotlin.Double
import kotlin.DoubleArray
import kotlin.Float
import kotlin.FloatArray
import kotlin.Int
import kotlin.IntArray
import kotlin.Long
import kotlin.LongArray
import kotlin.Unit
import org.tensorflow.DataType
import org.tensorflow.Operand
import org.tensorflow.Tensor
import org.tensorflow.ndarray.BooleanNdArray
import org.tensorflow.ndarray.ByteNdArray
import org.tensorflow.ndarray.DoubleNdArray
import org.tensorflow.ndarray.FloatNdArray
import org.tensorflow.ndarray.IntNdArray
import org.tensorflow.ndarray.LongNdArray
import org.tensorflow.ndarray.NdArray
import org.tensorflow.ndarray.Shape
import org.tensorflow.ndarray.buffer.BooleanDataBuffer
import org.tensorflow.ndarray.buffer.ByteDataBuffer
import org.tensorflow.ndarray.buffer.DataBuffer
import org.tensorflow.ndarray.buffer.DoubleDataBuffer
import org.tensorflow.ndarray.buffer.FloatDataBuffer
import org.tensorflow.ndarray.buffer.IntDataBuffer
import org.tensorflow.ndarray.buffer.LongDataBuffer
import org.tensorflow.op.Op
import org.tensorflow.op.Ops
import org.tensorflow.op.Scope
import org.tensorflow.op.core.Abort
import org.tensorflow.op.core.All
import org.tensorflow.op.core.Any
import org.tensorflow.op.core.AssertThat
import org.tensorflow.op.core.Assign
import org.tensorflow.op.core.AssignAdd
import org.tensorflow.op.core.AssignAddVariableOp
import org.tensorflow.op.core.AssignSub
import org.tensorflow.op.core.AssignSubVariableOp
import org.tensorflow.op.core.AssignVariableOp
import org.tensorflow.op.core.Barrier
import org.tensorflow.op.core.BarrierClose
import org.tensorflow.op.core.BarrierIncompleteSize
import org.tensorflow.op.core.BarrierInsertMany
import org.tensorflow.op.core.BarrierReadySize
import org.tensorflow.op.core.BarrierTakeMany
import org.tensorflow.op.core.Batch
import org.tensorflow.op.core.BatchToSpace
import org.tensorflow.op.core.BatchToSpaceNd
import org.tensorflow.op.core.Bitcast
import org.tensorflow.op.core.BroadcastDynamicShape
import org.tensorflow.op.core.BroadcastTo
import org.tensorflow.op.core.Bucketize
import org.tensorflow.op.core.ClipByValue
import org.tensorflow.op.core.Concat
import org.tensorflow.op.core.Constant
import org.tensorflow.op.core.ConsumeMutexLock
import org.tensorflow.op.core.ControlTrigger
import org.tensorflow.op.core.CountUpTo
import org.tensorflow.op.core.DeepCopy
import org.tensorflow.op.core.DeleteSessionTensor
import org.tensorflow.op.core.DestroyResourceOp
import org.tensorflow.op.core.DestroyTemporaryVariable
import org.tensorflow.op.core.DynamicPartition
import org.tensorflow.op.core.DynamicStitch
import org.tensorflow.op.core.EditDistance
import org.tensorflow.op.core.Empty
import org.tensorflow.op.core.EmptyTensorList
import org.tensorflow.op.core.EnsureShape
import org.tensorflow.op.core.ExpandDims
import org.tensorflow.op.core.ExtractVolumePatches
import org.tensorflow.op.core.Fill
import org.tensorflow.op.core.Fingerprint
import org.tensorflow.op.core.Gather
import org.tensorflow.op.core.GatherNd
import org.tensorflow.op.core.GetSessionHandle
import org.tensorflow.op.core.GetSessionTensor
import org.tensorflow.op.core.Gradients
import org.tensorflow.op.core.GuaranteeConst
import org.tensorflow.op.core.HashTable
import org.tensorflow.op.core.HistogramFixedWidth
import org.tensorflow.op.core.Identity
import org.tensorflow.op.core.IdentityN
import org.tensorflow.op.core.ImmutableConst
import org.tensorflow.op.core.Init
import org.tensorflow.op.core.InitializeTable
import org.tensorflow.op.core.InitializeTableFromTextFile
import org.tensorflow.op.core.InplaceAdd
import org.tensorflow.op.core.InplaceSub
import org.tensorflow.op.core.InplaceUpdate
import org.tensorflow.op.core.IsVariableInitialized
import org.tensorflow.op.core.LookupTableExport
import org.tensorflow.op.core.LookupTableFind
import org.tensorflow.op.core.LookupTableImport
import org.tensorflow.op.core.LookupTableInsert
import org.tensorflow.op.core.LookupTableSize
import org.tensorflow.op.core.LoopCond
import org.tensorflow.op.core.MapClear
import org.tensorflow.op.core.MapIncompleteSize
import org.tensorflow.op.core.MapPeek
import org.tensorflow.op.core.MapSize
import org.tensorflow.op.core.MapStage
import org.tensorflow.op.core.MapUnstage
import org.tensorflow.op.core.MapUnstageNoKey
import org.tensorflow.op.core.Max
import org.tensorflow.op.core.Merge
import org.tensorflow.op.core.Min
import org.tensorflow.op.core.MirrorPad
import org.tensorflow.op.core.MlirPassthroughOp
import org.tensorflow.op.core.MutableDenseHashTable
import org.tensorflow.op.core.MutableHashTable
import org.tensorflow.op.core.MutableHashTableOfTensors
import org.tensorflow.op.core.Mutex
import org.tensorflow.op.core.MutexLock
import org.tensorflow.op.core.NextIteration
import org.tensorflow.op.core.NoOp
import org.tensorflow.op.core.OneHot
import org.tensorflow.op.core.OnesLike
import org.tensorflow.op.core.OrderedMapClear
import org.tensorflow.op.core.OrderedMapIncompleteSize
import org.tensorflow.op.core.OrderedMapPeek
import org.tensorflow.op.core.OrderedMapSize
import org.tensorflow.op.core.OrderedMapStage
import org.tensorflow.op.core.OrderedMapUnstage
import org.tensorflow.op.core.OrderedMapUnstageNoKey
import org.tensorflow.op.core.Pad
import org.tensorflow.op.core.ParallelConcat
import org.tensorflow.op.core.ParallelDynamicStitch
import org.tensorflow.op.core.Placeholder
import org.tensorflow.op.core.PlaceholderWithDefault
import org.tensorflow.op.core.Print
import org.tensorflow.op.core.Prod
import org.tensorflow.op.core.QuantizedReshape
import org.tensorflow.op.core.Range
import org.tensorflow.op.core.Rank
import org.tensorflow.op.core.ReadVariableOp
import org.tensorflow.op.core.ReduceAll
import org.tensorflow.op.core.ReduceAny
import org.tensorflow.op.core.ReduceMax
import org.tensorflow.op.core.ReduceMin
import org.tensorflow.op.core.ReduceProd
import org.tensorflow.op.core.ReduceSum
import org.tensorflow.op.core.RefNextIteration
import org.tensorflow.op.core.RefSelect
import org.tensorflow.op.core.RefSwitch
import org.tensorflow.op.core.RemoteFusedGraphExecute
import org.tensorflow.op.core.Reshape
import org.tensorflow.op.core.ResourceCountUpTo
import org.tensorflow.op.core.ResourceGather
import org.tensorflow.op.core.ResourceGatherNd
import org.tensorflow.op.core.ResourceScatterAdd
import org.tensorflow.op.core.ResourceScatterDiv
import org.tensorflow.op.core.ResourceScatterMax
import org.tensorflow.op.core.ResourceScatterMin
import org.tensorflow.op.core.ResourceScatterMul
import org.tensorflow.op.core.ResourceScatterNdAdd
import org.tensorflow.op.core.ResourceScatterNdMax
import org.tensorflow.op.core.ResourceScatterNdMin
import org.tensorflow.op.core.ResourceScatterNdSub
import org.tensorflow.op.core.ResourceScatterNdUpdate
import org.tensorflow.op.core.ResourceScatterSub
import org.tensorflow.op.core.ResourceScatterUpdate
import org.tensorflow.op.core.ResourceStridedSliceAssign
import org.tensorflow.op.core.Reverse
import org.tensorflow.op.core.ReverseSequence
import org.tensorflow.op.core.Roll
import org.tensorflow.op.core.Rpc
import org.tensorflow.op.core.ScatterAdd
import org.tensorflow.op.core.ScatterDiv
import org.tensorflow.op.core.ScatterMax
import org.tensorflow.op.core.ScatterMin
import org.tensorflow.op.core.ScatterMul
import org.tensorflow.op.core.ScatterNd
import org.tensorflow.op.core.ScatterNdAdd
import org.tensorflow.op.core.ScatterNdNonAliasingAdd
import org.tensorflow.op.core.ScatterNdSub
import org.tensorflow.op.core.ScatterNdUpdate
import org.tensorflow.op.core.ScatterSub
import org.tensorflow.op.core.ScatterUpdate
import org.tensorflow.op.core.Select
import org.tensorflow.op.core.SetDiff1d
import org.tensorflow.op.core.SetSize
import org.tensorflow.op.core.ShapeN
import org.tensorflow.op.core.Size
import org.tensorflow.op.core.Skipgram
import org.tensorflow.op.core.Slice
import org.tensorflow.op.core.Snapshot
import org.tensorflow.op.core.SpaceToBatchNd
import org.tensorflow.op.core.Split
import org.tensorflow.op.core.SplitV
import org.tensorflow.op.core.Squeeze
import org.tensorflow.op.core.Stack
import org.tensorflow.op.core.Stage
import org.tensorflow.op.core.StageClear
import org.tensorflow.op.core.StagePeek
import org.tensorflow.op.core.StageSize
import org.tensorflow.op.core.StopGradient
import org.tensorflow.op.core.StridedSlice
import org.tensorflow.op.core.StridedSliceAssign
import org.tensorflow.op.core.StridedSliceGrad
import org.tensorflow.op.core.Sum
import org.tensorflow.op.core.SwitchCond
import org.tensorflow.op.core.TemporaryVariable
import org.tensorflow.op.core.TensorArray
import org.tensorflow.op.core.TensorArrayClose
import org.tensorflow.op.core.TensorArrayConcat
import org.tensorflow.op.core.TensorArrayGather
import org.tensorflow.op.core.TensorArrayGrad
import org.tensorflow.op.core.TensorArrayGradWithShape
import org.tensorflow.op.core.TensorArrayPack
import org.tensorflow.op.core.TensorArrayRead
import org.tensorflow.op.core.TensorArrayScatter
import org.tensorflow.op.core.TensorArraySize
import org.tensorflow.op.core.TensorArraySplit
import org.tensorflow.op.core.TensorArrayUnpack
import org.tensorflow.op.core.TensorArrayWrite
import org.tensorflow.op.core.TensorListConcat
import org.tensorflow.op.core.TensorListConcatLists
import org.tensorflow.op.core.TensorListElementShape
import org.tensorflow.op.core.TensorListFromTensor
import org.tensorflow.op.core.TensorListGather
import org.tensorflow.op.core.TensorListGetItem
import org.tensorflow.op.core.TensorListLength
import org.tensorflow.op.core.TensorListPopBack
import org.tensorflow.op.core.TensorListPushBack
import org.tensorflow.op.core.TensorListPushBackBatch
import org.tensorflow.op.core.TensorListReserve
import org.tensorflow.op.core.TensorListResize
import org.tensorflow.op.core.TensorListScatter
import org.tensorflow.op.core.TensorListScatterIntoExistingList
import org.tensorflow.op.core.TensorListSetItem
import org.tensorflow.op.core.TensorListSplit
import org.tensorflow.op.core.TensorListStack
import org.tensorflow.op.core.TensorScatterMax
import org.tensorflow.op.core.TensorScatterMin
import org.tensorflow.op.core.TensorScatterNdAdd
import org.tensorflow.op.core.TensorScatterNdMax
import org.tensorflow.op.core.TensorScatterNdMin
import org.tensorflow.op.core.TensorScatterNdSub
import org.tensorflow.op.core.TensorScatterNdUpdate
import org.tensorflow.op.core.TensorStridedSliceUpdate
import org.tensorflow.op.core.Tile
import org.tensorflow.op.core.Timestamp
import org.tensorflow.op.core.TryRpc
import org.tensorflow.op.core.Unbatch
import org.tensorflow.op.core.UnbatchGrad
import org.tensorflow.op.core.Unique
import org.tensorflow.op.core.UniqueWithCounts
import org.tensorflow.op.core.UnravelIndex
import org.tensorflow.op.core.Unstack
import org.tensorflow.op.core.Unstage
import org.tensorflow.op.core.VarHandleOp
import org.tensorflow.op.core.VarIsInitializedOp
import org.tensorflow.op.core.Variable
import org.tensorflow.op.core.VariableShape
import org.tensorflow.op.core.Where
import org.tensorflow.op.core.XlaSpmdFullToShardShape
import org.tensorflow.op.core.XlaSpmdShardToFullShape
import org.tensorflow.op.core.Zeros
import org.tensorflow.op.core.ZerosLike
import org.tensorflow.types.TBool
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TFloat64
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.TString
import org.tensorflow.types.TUint8
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public class KotlinOps(
	/**
	 * Returns the java counterpart of this API
	 */
	public val java: Ops
) {
	/**
	 * Returns the current {@link Scope scope} of this API
	 */
	public val scope: Scope = java.scope()

	/**
	 * Get the {@link Ops} object.
	 */
	public val ops: KotlinOps = this

	/**
	 * Get the {@link Ops} object.
	 */
	public val tf: KotlinOps = this

	public val nn: NnOps = NnOps(this)

	public val summary: SummaryOps = SummaryOps(this)

	public val image: ImageOps = ImageOps(this)

	public val ragged: RaggedOps = RaggedOps(this)

	public val `data`: DataOps = DataOps(this)

	public val shape: ShapeOps = ShapeOps(this)

	public val io: IoOps = IoOps(this)

	public val dtypes: DtypesOps = DtypesOps(this)

	public val xla: XlaOps = XlaOps(this)

	public val linalg: LinalgOps = LinalgOps(this)

	public val random: RandomOps = RandomOps(this)

	public val strings: StringsOps = StringsOps(this)

	public val sparse: SparseOps = SparseOps(this)

	public val bitwise: BitwiseOps = BitwiseOps(this)

	public val audio: AudioOps = AudioOps(this)

	public val math: MathOps = MathOps(this)

	public val signal: SignalOps = SignalOps(this)

	public val quantization: QuantizationOps = QuantizationOps(this)

	public val train: TrainOps = TrainOps(this)

	public fun abort(errorMsg: String? = null, exitWithoutError: Boolean? = null): Abort = java.abort(	
		*listOfNotNull(
			errorMsg?.let{ org.tensorflow.op.core.Abort.errorMsg(it) },
			exitWithoutError?.let{ org.tensorflow.op.core.Abort.exitWithoutError(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> all(
		input: Operand<TBool>,
		axis: Operand<T>,
		keepDims: Boolean? = null
	): All = java.all<T>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.core.All.keepDims(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> any(
		input: Operand<TBool>,
		axis: Operand<T>,
		keepDims: Boolean? = null
	): Any = java.any<T>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.core.Any.keepDims(it) }
		).toTypedArray()
		)

	public fun array(vararg `data`: Int): Constant<TInt32> = java.array(	
		*data
		)

	public fun array(vararg `data`: String): Constant<TString> = java.array(	
		*data
		)

	public fun array(vararg `data`: kotlin.Boolean): Constant<TBool> = java.array(	
		*data
		)

	public fun array(vararg `data`: Long): Constant<TInt64> = java.array(	
		*data
		)

	public fun array(vararg `data`: Float): Constant<TFloat32> = java.array(	
		*data
		)

	public fun array(vararg `data`: Double): Constant<TFloat64> = java.array(	
		*data
		)

	public fun array(vararg `data`: Byte): Constant<TUint8> = java.array(	
		*data
		)

	public fun array(charset: Charset, vararg `data`: String): Constant<TString> = java.array(	
		charset,
		*data
		)

	public fun assertThat(
		condition: Operand<TBool>,
		`data`: Iterable<Operand<*>>,
		summarize: Long? = null
	): AssertThat = java.assertThat(	
		condition,
		data,
		*listOfNotNull(
			summarize?.let{ org.tensorflow.op.core.AssertThat.summarize(it) }
		).toTypedArray()
		)

	public fun <T : TType> assign(
		ref: Operand<T>,
		value: Operand<T>,
		validateShape: Boolean? = null,
		useLocking: Boolean? = null
	): Assign<T> = java.assign<T>(	
		ref,
		value,
		*listOfNotNull(
			validateShape?.let{ org.tensorflow.op.core.Assign.validateShape(it) },
			useLocking?.let{ org.tensorflow.op.core.Assign.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> assignAdd(
		ref: Operand<T>,
		value: Operand<T>,
		useLocking: Boolean? = null
	): AssignAdd<T> = java.assignAdd<T>(	
		ref,
		value,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.AssignAdd.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> assignAddVariableOp(resource: Operand<*>, value: Operand<T>):
			AssignAddVariableOp = java.assignAddVariableOp<T>(	
		resource,
		value
		)

	public fun <T : TType> assignSub(
		ref: Operand<T>,
		value: Operand<T>,
		useLocking: Boolean? = null
	): AssignSub<T> = java.assignSub<T>(	
		ref,
		value,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.AssignSub.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> assignSubVariableOp(resource: Operand<*>, value: Operand<T>):
			AssignSubVariableOp = java.assignSubVariableOp<T>(	
		resource,
		value
		)

	public fun <T : TType> assignVariableOp(resource: Operand<*>, value: Operand<T>): AssignVariableOp
			= java.assignVariableOp<T>(	
		resource,
		value
		)

	public fun barrier(
		componentTypes: List<DataType<*>>,
		shapes: List<Shape>? = null,
		capacity: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): Barrier = java.barrier(	
		componentTypes,
		*listOfNotNull(
			shapes?.let{ org.tensorflow.op.core.Barrier.shapes(it) },
			capacity?.let{ org.tensorflow.op.core.Barrier.capacity(it) },
			container?.let{ org.tensorflow.op.core.Barrier.container(it) },
			sharedName?.let{ org.tensorflow.op.core.Barrier.sharedName(it) }
		).toTypedArray()
		)

	public fun barrierClose(handle: Operand<TString>, cancelPendingEnqueues: Boolean? = null):
			BarrierClose = java.barrierClose(	
		handle,
		*listOfNotNull(
			cancelPendingEnqueues?.let{ org.tensorflow.op.core.BarrierClose.cancelPendingEnqueues(it) }
		).toTypedArray()
		)

	public fun barrierIncompleteSize(handle: Operand<TString>): BarrierIncompleteSize =
			java.barrierIncompleteSize(	
		handle
		)

	public fun <T : TType> barrierInsertMany(
		handle: Operand<TString>,
		keys: Operand<TString>,
		values: Operand<T>,
		componentIndex: Long
	): BarrierInsertMany = java.barrierInsertMany<T>(	
		handle,
		keys,
		values,
		componentIndex
		)

	public fun barrierReadySize(handle: Operand<TString>): BarrierReadySize = java.barrierReadySize(	
		handle
		)

	public fun barrierTakeMany(
		handle: Operand<TString>,
		numElements: Operand<TInt32>,
		componentTypes: List<DataType<*>>,
		allowSmallBatch: Boolean? = null,
		waitForIncomplete: Boolean? = null,
		timeoutMs: Long? = null
	): BarrierTakeMany = java.barrierTakeMany(	
		handle,
		numElements,
		componentTypes,
		*listOfNotNull(
			allowSmallBatch?.let{ org.tensorflow.op.core.BarrierTakeMany.allowSmallBatch(it) },
			waitForIncomplete?.let{ org.tensorflow.op.core.BarrierTakeMany.waitForIncomplete(it) },
			timeoutMs?.let{ org.tensorflow.op.core.BarrierTakeMany.timeoutMs(it) }
		).toTypedArray()
		)

	public fun batch(
		inTensors: Iterable<Operand<*>>,
		numBatchThreads: Long,
		maxBatchSize: Long,
		batchTimeoutMicros: Long,
		gradTimeoutMicros: Long,
		maxEnqueuedBatches: Long? = null,
		allowedBatchSizes: List<Long>? = null,
		container: String? = null,
		sharedName: String? = null,
		batchingQueue: String? = null
	): Batch = java.batch(	
		inTensors,
		numBatchThreads,
		maxBatchSize,
		batchTimeoutMicros,
		gradTimeoutMicros,
		*listOfNotNull(
			maxEnqueuedBatches?.let{ org.tensorflow.op.core.Batch.maxEnqueuedBatches(it) },
			allowedBatchSizes?.let{ org.tensorflow.op.core.Batch.allowedBatchSizes(it) },
			container?.let{ org.tensorflow.op.core.Batch.container(it) },
			sharedName?.let{ org.tensorflow.op.core.Batch.sharedName(it) },
			batchingQueue?.let{ org.tensorflow.op.core.Batch.batchingQueue(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> batchToSpace(
		input: Operand<T>,
		crops: Operand<U>,
		blockSize: Long
	): BatchToSpace<T> = java.batchToSpace<T, U>(	
		input,
		crops,
		blockSize
		)

	public fun <T : TType, U : TNumber, V : TNumber> batchToSpaceNd(
		input: Operand<T>,
		blockShape: Operand<U>,
		crops: Operand<V>
	): BatchToSpaceNd<T> = java.batchToSpaceNd<T, U, V>(	
		input,
		blockShape,
		crops
		)

	public fun <U : TType, T : TType> bitcast(input: Operand<T>, type: DataType<U>): Bitcast<U> =
			java.bitcast<U, T>(	
		input,
		type
		)

	public fun <T : TNumber> broadcastDynamicShape(s0: Operand<T>, s1: Operand<T>):
			BroadcastDynamicShape<T> = java.broadcastDynamicShape<T>(	
		s0,
		s1
		)

	public fun <T : TType, U : TNumber> broadcastTo(input: Operand<T>, shape: Operand<U>):
			BroadcastTo<T> = java.broadcastTo<T, U>(	
		input,
		shape
		)

	public fun <T : TNumber> bucketize(input: Operand<T>, boundaries: List<Float>): Bucketize
			= java.bucketize<T>(	
		input,
		boundaries
		)

	public fun <T : TType> clipByValue(
		t: Operand<T>,
		clipValueMin: Operand<T>,
		clipValueMax: Operand<T>
	): ClipByValue<T> = java.clipByValue<T>(	
		t,
		clipValueMin,
		clipValueMax
		)

	public fun <T : TType, U : TNumber> concat(values: Iterable<Operand<T>>, axis: Operand<U>):
			Concat<T> = java.concat<T, U>(	
		values,
		axis
		)

	public fun constant(`data`: LongNdArray): Constant<TInt64> = java.constant(	
		data
		)

	public fun constant(`data`: IntArray): Constant<TInt32> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<IntArray>>): Constant<TInt32> = java.constant(	
		data
		)

	public fun constant(`data`: Double): Constant<TFloat64> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<Array<LongArray>>>>): Constant<TInt64> =
			java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<Array<BooleanArray>>>>): Constant<TBool> =
			java.constant(	
		data
		)

	public fun constant(`data`: IntNdArray): Constant<TInt32> = java.constant(	
		data
		)

	public fun constant(`data`: DoubleNdArray): Constant<TFloat64> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<IntArray>>>): Constant<TInt32> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<Array<Array<FloatArray>>>>>): Constant<TFloat32> =
			java.constant(	
		data
		)

	public fun constant(`data`: Byte): Constant<TUint8> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<BooleanArray>>): Constant<TBool> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<FloatArray>>>): Constant<TFloat32> = java.constant(	
		data
		)

	public fun constant(`data`: Array<LongArray>): Constant<TInt64> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<Array<ByteArray>>>>): Constant<TUint8> =
			java.constant(	
		data
		)

	public fun constant(`data`: BooleanNdArray): Constant<TBool> = java.constant(	
		data
		)

	public fun constant(`data`: Array<FloatArray>): Constant<TFloat32> = java.constant(	
		data
		)

	public fun constant(`data`: ByteNdArray): Constant<TUint8> = java.constant(	
		data
		)

	public fun constant(`data`: Array<ByteArray>): Constant<TUint8> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<Array<DoubleArray>>>>): Constant<TFloat64> =
			java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<FloatArray>>): Constant<TFloat32> = java.constant(	
		data
		)

	public fun constant(`data`: ByteArray): Constant<TUint8> = java.constant(	
		data
		)

	public fun constant(`data`: FloatArray): Constant<TFloat32> = java.constant(	
		data
		)

	public fun constant(`data`: Array<BooleanArray>): Constant<TBool> = java.constant(	
		data
		)

	public fun constant(`data`: NdArray<String>): Constant<TString> = java.constant(	
		data
		)

	public fun constant(`data`: String): Constant<TString> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<DoubleArray>>>): Constant<TFloat64> = java.constant(	
		data
		)

	public fun constant(`data`: Array<DoubleArray>): Constant<TFloat64> = java.constant(	
		data
		)

	public fun constant(`data`: Int): Constant<TInt32> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<ByteArray>>>): Constant<TUint8> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<Array<Array<IntArray>>>>>): Constant<TInt32> =
			java.constant(	
		data
		)

	public fun constant(`data`: Long): Constant<TInt64> = java.constant(	
		data
		)

	public fun constant(`data`: Float): Constant<TFloat32> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<Array<FloatArray>>>>): Constant<TFloat32> =
			java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<DoubleArray>>): Constant<TFloat64> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<Array<Array<LongArray>>>>>): Constant<TInt64> =
			java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<LongArray>>>): Constant<TInt64> = java.constant(	
		data
		)

	public fun constant(`data`: LongArray): Constant<TInt64> = java.constant(	
		data
		)

	public fun constant(`data`: BooleanArray): Constant<TBool> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<ByteArray>>): Constant<TUint8> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<Array<Array<ByteArray>>>>>): Constant<TUint8> =
			java.constant(	
		data
		)

	public fun constant(`data`: Array<IntArray>): Constant<TInt32> = java.constant(	
		data
		)

	public fun constant(`data`: FloatNdArray): Constant<TFloat32> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<Array<IntArray>>>>): Constant<TInt32> =
			java.constant(	
		data
		)

	public fun constant(`data`: DoubleArray): Constant<TFloat64> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<Array<Array<BooleanArray>>>>>): Constant<TBool> =
			java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<Array<Array<DoubleArray>>>>>): Constant<TFloat64> =
			java.constant(	
		data
		)

	public fun constant(`data`: kotlin.Boolean): Constant<TBool> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<Array<BooleanArray>>>): Constant<TBool> = java.constant(	
		data
		)

	public fun constant(`data`: Array<Array<LongArray>>): Constant<TInt64> = java.constant(	
		data
		)

	public fun constant(shape: Shape): Constant<TInt64> = java.constant(	
		shape
		)

	public fun <T : TType> constant(tensor: Tensor<T>): Constant<T> = java.constant<T>(	
		tensor
		)

	public fun constant(charset: Charset, `data`: Array<String>): Constant<TString> = java.constant(	
		charset,
		data
		)

	public fun constant(charset: Charset, `data`: String): Constant<TString> = java.constant(	
		charset,
		data
		)

	public fun constant(charset: Charset, `data`: NdArray<String>): Constant<TString> = java.constant(	
		charset,
		data
		)

	public fun constant(shape: Shape, `data`: FloatDataBuffer): Constant<TFloat32> = java.constant(	
		shape,
		data
		)

	public fun constant(shape: Shape, `data`: BooleanDataBuffer): Constant<TBool> = java.constant(	
		shape,
		data
		)

	public fun constant(shape: Shape, `data`: ByteDataBuffer): Constant<TUint8> = java.constant(	
		shape,
		data
		)

	public fun constant(shape: Shape, `data`: LongDataBuffer): Constant<TInt64> = java.constant(	
		shape,
		data
		)

	public fun constant(shape: Shape, `data`: DataBuffer<String>): Constant<TString> = java.constant(	
		shape,
		data
		)

	public fun constant(shape: Shape, `data`: DoubleDataBuffer): Constant<TFloat64> = java.constant(	
		shape,
		data
		)

	public fun constant(shape: Shape, `data`: IntDataBuffer): Constant<TInt32> = java.constant(	
		shape,
		data
		)

	public fun constant(
		charset: Charset,
		shape: Shape,
		`data`: DataBuffer<String>
	): Constant<TString> = java.constant(	
		charset,
		shape,
		data
		)

	public fun <T : TType> constant(
		type: DataType<T>,
		shape: Shape,
		`data`: ByteDataBuffer
	): Constant<T> = java.constant<T>(	
		type,
		shape,
		data
		)

	public fun consumeMutexLock(mutexLock: Operand<*>): ConsumeMutexLock = java.consumeMutexLock(	
		mutexLock
		)

	public fun controlTrigger(): ControlTrigger = java.controlTrigger(	
		
		)

	public fun <T : TNumber> countUpTo(ref: Operand<T>, limit: Long): CountUpTo<T> =
			java.countUpTo<T>(	
		ref,
		limit
		)

	public fun <T : TType> deepCopy(x: Operand<T>): DeepCopy<T> = java.deepCopy<T>(	
		x
		)

	public fun deleteSessionTensor(handle: Operand<TString>): DeleteSessionTensor =
			java.deleteSessionTensor(	
		handle
		)

	public fun destroyResourceOp(resource: Operand<*>, ignoreLookupError: Boolean? = null):
			DestroyResourceOp = java.destroyResourceOp(	
		resource,
		*listOfNotNull(
			ignoreLookupError?.let{ org.tensorflow.op.core.DestroyResourceOp.ignoreLookupError(it) }
		).toTypedArray()
		)

	public fun <T : TType> destroyTemporaryVariable(ref: Operand<T>, varName: String):
			DestroyTemporaryVariable<T> = java.destroyTemporaryVariable<T>(	
		ref,
		varName
		)

	public fun <T : TType> dynamicPartition(
		`data`: Operand<T>,
		partitions: Operand<TInt32>,
		numPartitions: Long
	): DynamicPartition<T> = java.dynamicPartition<T>(	
		data,
		partitions,
		numPartitions
		)

	public fun <T : TType> dynamicStitch(indices: Iterable<Operand<TInt32>>,
			`data`: Iterable<Operand<T>>): DynamicStitch<T> = java.dynamicStitch<T>(	
		indices,
		data
		)

	public fun <T : TType> editDistance(
		hypothesisIndices: Operand<TInt64>,
		hypothesisValues: Operand<T>,
		hypothesisShape: Operand<TInt64>,
		truthIndices: Operand<TInt64>,
		truthValues: Operand<T>,
		truthShape: Operand<TInt64>,
		normalize: Boolean? = null
	): EditDistance = java.editDistance<T>(	
		hypothesisIndices,
		hypothesisValues,
		hypothesisShape,
		truthIndices,
		truthValues,
		truthShape,
		*listOfNotNull(
			normalize?.let{ org.tensorflow.op.core.EditDistance.normalize(it) }
		).toTypedArray()
		)

	public fun <T : TType> empty(
		shape: Operand<TInt32>,
		dtype: DataType<T>,
		`init`: Boolean? = null
	): Empty<T> = java.empty<T>(	
		shape,
		dtype,
		*listOfNotNull(
			init?.let{ org.tensorflow.op.core.Empty.init(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TType> emptyTensorList(
		elementShape: Operand<T>,
		maxNumElements: Operand<TInt32>,
		elementDtype: DataType<U>
	): EmptyTensorList = java.emptyTensorList<T, U>(	
		elementShape,
		maxNumElements,
		elementDtype
		)

	public fun <T : TType> ensureShape(input: Operand<T>, shape: Shape): EnsureShape<T> =
			java.ensureShape<T>(	
		input,
		shape
		)

	public fun <T : TType, U : TNumber> expandDims(input: Operand<T>, axis: Operand<U>): ExpandDims<T>
			= java.expandDims<T, U>(	
		input,
		axis
		)

	public fun <T : TNumber> extractVolumePatches(
		input: Operand<T>,
		ksizes: List<Long>,
		strides: List<Long>,
		padding: String
	): ExtractVolumePatches<T> = java.extractVolumePatches<T>(	
		input,
		ksizes,
		strides,
		padding
		)

	public fun <U : TType, T : TNumber> fill(dims: Operand<T>, value: Operand<U>): Fill<U> =
			java.fill<U, T>(	
		dims,
		value
		)

	public fun <T : TType> fingerprint(`data`: Operand<T>, method: Operand<TString>): Fingerprint =
			java.fingerprint<T>(	
		data,
		method
		)

	public fun <T : TType, U : TNumber, V : TNumber> gather(
		params: Operand<T>,
		indices: Operand<U>,
		axis: Operand<V>,
		batchDims: Long? = null
	): Gather<T> = java.gather<T, U, V>(	
		params,
		indices,
		axis,
		*listOfNotNull(
			batchDims?.let{ org.tensorflow.op.core.Gather.batchDims(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> gatherNd(params: Operand<T>, indices: Operand<U>): GatherNd<T>
			= java.gatherNd<T, U>(	
		params,
		indices
		)

	public fun <T : TType> getSessionHandle(value: Operand<T>): GetSessionHandle =
			java.getSessionHandle<T>(	
		value
		)

	public fun <T : TType> getSessionTensor(handle: Operand<TString>, dtype: DataType<T>):
			GetSessionTensor<T> = java.getSessionTensor<T>(	
		handle,
		dtype
		)

	public fun gradients(
		y: Iterable<out Operand<*>>,
		x: Iterable<out Operand<*>>,
		dx: Iterable<out Operand<*>>? = null
	): Gradients = java.gradients(	
		y,
		x,
		*listOfNotNull(
			dx?.let{ org.tensorflow.op.core.Gradients.dx(it) }
		).toTypedArray()
		)

	public fun gradients(
		y: Operand<*>,
		x: Iterable<out Operand<*>>,
		dx: Iterable<out Operand<*>>? = null
	): Gradients = java.gradients(	
		y,
		x,
		*listOfNotNull(
			dx?.let{ org.tensorflow.op.core.Gradients.dx(it) }
		).toTypedArray()
		)

	public fun <T : TType> guaranteeConst(input: Operand<T>): GuaranteeConst<T> =
			java.guaranteeConst<T>(	
		input
		)

	public fun <T : TType, U : TType> hashTable(
		keyDtype: DataType<T>,
		valueDtype: DataType<U>,
		container: String? = null,
		sharedName: String? = null,
		useNodeNameSharing: Boolean? = null
	): HashTable = java.hashTable<T, U>(	
		keyDtype,
		valueDtype,
		*listOfNotNull(
			container?.let{ org.tensorflow.op.core.HashTable.container(it) },
			sharedName?.let{ org.tensorflow.op.core.HashTable.sharedName(it) },
			useNodeNameSharing?.let{ org.tensorflow.op.core.HashTable.useNodeNameSharing(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> histogramFixedWidth(
		values: Operand<T>,
		valueRange: Operand<T>,
		nbins: Operand<TInt32>
	): HistogramFixedWidth<TInt32> = java.histogramFixedWidth<T>(	
		values,
		valueRange,
		nbins
		)

	public fun <U : TNumber, T : TNumber> histogramFixedWidth(
		values: Operand<T>,
		valueRange: Operand<T>,
		nbins: Operand<TInt32>,
		dtype: DataType<U>
	): HistogramFixedWidth<U> = java.histogramFixedWidth<U, T>(	
		values,
		valueRange,
		nbins,
		dtype
		)

	public fun <T : TType> identity(input: Operand<T>): Identity<T> = java.identity<T>(	
		input
		)

	public fun identityN(input: Iterable<Operand<*>>): IdentityN = java.identityN(	
		input
		)

	public fun <T : TType> immutableConst(
		dtype: DataType<T>,
		shape: Shape,
		memoryRegionName: String
	): ImmutableConst<T> = java.immutableConst<T>(	
		dtype,
		shape,
		memoryRegionName
		)

	public fun `init`(): Init = java.init(	
		
		)

	public fun initAdd(initializer: Op): Unit = java.initAdd(	
		initializer
		)

	public fun <T : TType, U : TType> initializeTable(
		tableHandle: Operand<*>,
		keys: Operand<T>,
		values: Operand<U>
	): InitializeTable = java.initializeTable<T, U>(	
		tableHandle,
		keys,
		values
		)

	public fun initializeTableFromTextFile(
		tableHandle: Operand<*>,
		filename: Operand<TString>,
		keyIndex: Long,
		valueIndex: Long,
		vocabSize: Long? = null,
		delimiter: String? = null
	): InitializeTableFromTextFile = java.initializeTableFromTextFile(	
		tableHandle,
		filename,
		keyIndex,
		valueIndex,
		*listOfNotNull(
			vocabSize?.let{ org.tensorflow.op.core.InitializeTableFromTextFile.vocabSize(it) },
			delimiter?.let{ org.tensorflow.op.core.InitializeTableFromTextFile.delimiter(it) }
		).toTypedArray()
		)

	public fun <T : TType> inplaceAdd(
		x: Operand<T>,
		i: Operand<TInt32>,
		v: Operand<T>
	): InplaceAdd<T> = java.inplaceAdd<T>(	
		x,
		i,
		v
		)

	public fun <T : TType> inplaceSub(
		x: Operand<T>,
		i: Operand<TInt32>,
		v: Operand<T>
	): InplaceSub<T> = java.inplaceSub<T>(	
		x,
		i,
		v
		)

	public fun <T : TType> inplaceUpdate(
		x: Operand<T>,
		i: Operand<TInt32>,
		v: Operand<T>
	): InplaceUpdate<T> = java.inplaceUpdate<T>(	
		x,
		i,
		v
		)

	public fun <T : TType> isVariableInitialized(ref: Operand<T>): IsVariableInitialized =
			java.isVariableInitialized<T>(	
		ref
		)

	public fun <T : TType, U : TType> lookupTableExport(
		tableHandle: Operand<*>,
		Tkeys: DataType<T>,
		Tvalues: DataType<U>
	): LookupTableExport<T, U> = java.lookupTableExport<T, U>(	
		tableHandle,
		Tkeys,
		Tvalues
		)

	public fun <U : TType, T : TType> lookupTableFind(
		tableHandle: Operand<*>,
		keys: Operand<T>,
		defaultValue: Operand<U>
	): LookupTableFind<U> = java.lookupTableFind<U, T>(	
		tableHandle,
		keys,
		defaultValue
		)

	public fun <T : TType, U : TType> lookupTableImport(
		tableHandle: Operand<*>,
		keys: Operand<T>,
		values: Operand<U>
	): LookupTableImport = java.lookupTableImport<T, U>(	
		tableHandle,
		keys,
		values
		)

	public fun <T : TType, U : TType> lookupTableInsert(
		tableHandle: Operand<*>,
		keys: Operand<T>,
		values: Operand<U>
	): LookupTableInsert = java.lookupTableInsert<T, U>(	
		tableHandle,
		keys,
		values
		)

	public fun lookupTableSize(tableHandle: Operand<*>): LookupTableSize = java.lookupTableSize(	
		tableHandle
		)

	public fun loopCond(input: Operand<TBool>): LoopCond = java.loopCond(	
		input
		)

	public fun mapClear(
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): MapClear = java.mapClear(	
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.MapClear.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.MapClear.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.MapClear.container(it) },
			sharedName?.let{ org.tensorflow.op.core.MapClear.sharedName(it) }
		).toTypedArray()
		)

	public fun mapIncompleteSize(
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): MapIncompleteSize = java.mapIncompleteSize(	
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.MapIncompleteSize.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.MapIncompleteSize.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.MapIncompleteSize.container(it) },
			sharedName?.let{ org.tensorflow.op.core.MapIncompleteSize.sharedName(it) }
		).toTypedArray()
		)

	public fun mapPeek(
		key: Operand<TInt64>,
		indices: Operand<TInt32>,
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): MapPeek = java.mapPeek(	
		key,
		indices,
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.MapPeek.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.MapPeek.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.MapPeek.container(it) },
			sharedName?.let{ org.tensorflow.op.core.MapPeek.sharedName(it) }
		).toTypedArray()
		)

	public fun mapSize(
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): MapSize = java.mapSize(	
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.MapSize.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.MapSize.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.MapSize.container(it) },
			sharedName?.let{ org.tensorflow.op.core.MapSize.sharedName(it) }
		).toTypedArray()
		)

	public fun mapStage(
		key: Operand<TInt64>,
		indices: Operand<TInt32>,
		values: Iterable<Operand<*>>,
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): MapStage = java.mapStage(	
		key,
		indices,
		values,
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.MapStage.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.MapStage.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.MapStage.container(it) },
			sharedName?.let{ org.tensorflow.op.core.MapStage.sharedName(it) }
		).toTypedArray()
		)

	public fun mapUnstage(
		key: Operand<TInt64>,
		indices: Operand<TInt32>,
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): MapUnstage = java.mapUnstage(	
		key,
		indices,
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.MapUnstage.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.MapUnstage.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.MapUnstage.container(it) },
			sharedName?.let{ org.tensorflow.op.core.MapUnstage.sharedName(it) }
		).toTypedArray()
		)

	public fun mapUnstageNoKey(
		indices: Operand<TInt32>,
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): MapUnstageNoKey = java.mapUnstageNoKey(	
		indices,
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.MapUnstageNoKey.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.MapUnstageNoKey.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.MapUnstageNoKey.container(it) },
			sharedName?.let{ org.tensorflow.op.core.MapUnstageNoKey.sharedName(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> max(
		input: Operand<T>,
		axis: Operand<U>,
		keepDims: Boolean? = null
	): Max<T> = java.max<T, U>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.core.Max.keepDims(it) }
		).toTypedArray()
		)

	public fun <T : TType> merge(inputs: Iterable<Operand<T>>): Merge<T> = java.merge<T>(	
		inputs
		)

	public fun <T : TType, U : TNumber> min(
		input: Operand<T>,
		axis: Operand<U>,
		keepDims: Boolean? = null
	): Min<T> = java.min<T, U>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.core.Min.keepDims(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> mirrorPad(
		input: Operand<T>,
		paddings: Operand<U>,
		mode: String
	): MirrorPad<T> = java.mirrorPad<T, U>(	
		input,
		paddings,
		mode
		)

	public fun mlirPassthroughOp(
		inputs: Iterable<Operand<*>>,
		mlirModule: String,
		Toutputs: List<DataType<*>>
	): MlirPassthroughOp = java.mlirPassthroughOp(	
		inputs,
		mlirModule,
		Toutputs
		)

	public fun <T : TType, U : TType> mutableDenseHashTable(
		emptyKey: Operand<T>,
		deletedKey: Operand<T>,
		valueDtype: DataType<U>,
		container: String? = null,
		sharedName: String? = null,
		useNodeNameSharing: Boolean? = null,
		valueShape: Shape? = null,
		initialNumBuckets: Long? = null,
		maxLoadFactor: Float? = null
	): MutableDenseHashTable = java.mutableDenseHashTable<T, U>(	
		emptyKey,
		deletedKey,
		valueDtype,
		*listOfNotNull(
			container?.let{ org.tensorflow.op.core.MutableDenseHashTable.container(it) },
			sharedName?.let{ org.tensorflow.op.core.MutableDenseHashTable.sharedName(it) },
			useNodeNameSharing?.let{ org.tensorflow.op.core.MutableDenseHashTable.useNodeNameSharing(it) },
			valueShape?.let{ org.tensorflow.op.core.MutableDenseHashTable.valueShape(it) },
			initialNumBuckets?.let{ org.tensorflow.op.core.MutableDenseHashTable.initialNumBuckets(it) },
			maxLoadFactor?.let{ org.tensorflow.op.core.MutableDenseHashTable.maxLoadFactor(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TType> mutableHashTable(
		keyDtype: DataType<T>,
		valueDtype: DataType<U>,
		container: String? = null,
		sharedName: String? = null,
		useNodeNameSharing: Boolean? = null
	): MutableHashTable = java.mutableHashTable<T, U>(	
		keyDtype,
		valueDtype,
		*listOfNotNull(
			container?.let{ org.tensorflow.op.core.MutableHashTable.container(it) },
			sharedName?.let{ org.tensorflow.op.core.MutableHashTable.sharedName(it) },
			useNodeNameSharing?.let{ org.tensorflow.op.core.MutableHashTable.useNodeNameSharing(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TType> mutableHashTableOfTensors(
		keyDtype: DataType<T>,
		valueDtype: DataType<U>,
		container: String? = null,
		sharedName: String? = null,
		useNodeNameSharing: Boolean? = null,
		valueShape: Shape? = null
	): MutableHashTableOfTensors = java.mutableHashTableOfTensors<T, U>(	
		keyDtype,
		valueDtype,
		*listOfNotNull(
			container?.let{ org.tensorflow.op.core.MutableHashTableOfTensors.container(it) },
			sharedName?.let{ org.tensorflow.op.core.MutableHashTableOfTensors.sharedName(it) },
			useNodeNameSharing?.let{ org.tensorflow.op.core.MutableHashTableOfTensors.useNodeNameSharing(it)
			},
			valueShape?.let{ org.tensorflow.op.core.MutableHashTableOfTensors.valueShape(it) }
		).toTypedArray()
		)

	public fun mutex(container: String? = null, sharedName: String? = null): Mutex = java.mutex(	
		*listOfNotNull(
			container?.let{ org.tensorflow.op.core.Mutex.container(it) },
			sharedName?.let{ org.tensorflow.op.core.Mutex.sharedName(it) }
		).toTypedArray()
		)

	public fun mutexLock(mutex: Operand<*>): MutexLock = java.mutexLock(	
		mutex
		)

	public fun <T : TType> nextIteration(`data`: Operand<T>): NextIteration<T> =
			java.nextIteration<T>(	
		data
		)

	public fun noOp(): NoOp = java.noOp(	
		
		)

	public fun <U : TType, T : TNumber> oneHot(
		indices: Operand<T>,
		depth: Operand<TInt32>,
		onValue: Operand<U>,
		offValue: Operand<U>,
		axis: Long? = null
	): OneHot<U> = java.oneHot<U, T>(	
		indices,
		depth,
		onValue,
		offValue,
		*listOfNotNull(
			axis?.let{ org.tensorflow.op.core.OneHot.axis(it) }
		).toTypedArray()
		)

	public fun <T : TType> onesLike(x: Operand<T>): OnesLike<T> = java.onesLike<T>(	
		x
		)

	public fun orderedMapClear(
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): OrderedMapClear = java.orderedMapClear(	
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.OrderedMapClear.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.OrderedMapClear.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.OrderedMapClear.container(it) },
			sharedName?.let{ org.tensorflow.op.core.OrderedMapClear.sharedName(it) }
		).toTypedArray()
		)

	public fun orderedMapIncompleteSize(
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): OrderedMapIncompleteSize = java.orderedMapIncompleteSize(	
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.OrderedMapIncompleteSize.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.OrderedMapIncompleteSize.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.OrderedMapIncompleteSize.container(it) },
			sharedName?.let{ org.tensorflow.op.core.OrderedMapIncompleteSize.sharedName(it) }
		).toTypedArray()
		)

	public fun orderedMapPeek(
		key: Operand<TInt64>,
		indices: Operand<TInt32>,
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): OrderedMapPeek = java.orderedMapPeek(	
		key,
		indices,
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.OrderedMapPeek.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.OrderedMapPeek.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.OrderedMapPeek.container(it) },
			sharedName?.let{ org.tensorflow.op.core.OrderedMapPeek.sharedName(it) }
		).toTypedArray()
		)

	public fun orderedMapSize(
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): OrderedMapSize = java.orderedMapSize(	
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.OrderedMapSize.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.OrderedMapSize.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.OrderedMapSize.container(it) },
			sharedName?.let{ org.tensorflow.op.core.OrderedMapSize.sharedName(it) }
		).toTypedArray()
		)

	public fun orderedMapStage(
		key: Operand<TInt64>,
		indices: Operand<TInt32>,
		values: Iterable<Operand<*>>,
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): OrderedMapStage = java.orderedMapStage(	
		key,
		indices,
		values,
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.OrderedMapStage.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.OrderedMapStage.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.OrderedMapStage.container(it) },
			sharedName?.let{ org.tensorflow.op.core.OrderedMapStage.sharedName(it) }
		).toTypedArray()
		)

	public fun orderedMapUnstage(
		key: Operand<TInt64>,
		indices: Operand<TInt32>,
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): OrderedMapUnstage = java.orderedMapUnstage(	
		key,
		indices,
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.OrderedMapUnstage.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.OrderedMapUnstage.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.OrderedMapUnstage.container(it) },
			sharedName?.let{ org.tensorflow.op.core.OrderedMapUnstage.sharedName(it) }
		).toTypedArray()
		)

	public fun orderedMapUnstageNoKey(
		indices: Operand<TInt32>,
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): OrderedMapUnstageNoKey = java.orderedMapUnstageNoKey(	
		indices,
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.OrderedMapUnstageNoKey.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.OrderedMapUnstageNoKey.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.OrderedMapUnstageNoKey.container(it) },
			sharedName?.let{ org.tensorflow.op.core.OrderedMapUnstageNoKey.sharedName(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> pad(
		input: Operand<T>,
		paddings: Operand<U>,
		constantValues: Operand<T>
	): Pad<T> = java.pad<T, U>(	
		input,
		paddings,
		constantValues
		)

	public fun <T : TType> parallelConcat(values: Iterable<Operand<T>>, shape: Shape):
			ParallelConcat<T> = java.parallelConcat<T>(	
		values,
		shape
		)

	public fun <T : TType> parallelDynamicStitch(indices: Iterable<Operand<TInt32>>,
			`data`: Iterable<Operand<T>>): ParallelDynamicStitch<T> = java.parallelDynamicStitch<T>(	
		indices,
		data
		)

	public fun <T : TType> placeholder(dtype: DataType<T>, shape: Shape? = null): Placeholder<T> =
			java.placeholder<T>(	
		dtype,
		*listOfNotNull(
			shape?.let{ org.tensorflow.op.core.Placeholder.shape(it) }
		).toTypedArray()
		)

	public fun <T : TType> placeholderWithDefault(input: Operand<T>, shape: Shape):
			PlaceholderWithDefault<T> = java.placeholderWithDefault<T>(	
		input,
		shape
		)

	public fun print(
		input: Operand<TString>,
		outputStream: String? = null,
		end: String? = null
	): Print = java.print(	
		input,
		*listOfNotNull(
			outputStream?.let{ org.tensorflow.op.core.Print.outputStream(it) },
			end?.let{ org.tensorflow.op.core.Print.end(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> prod(
		input: Operand<T>,
		axis: Operand<U>,
		keepDims: Boolean? = null
	): Prod<T> = java.prod<T, U>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.core.Prod.keepDims(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> quantizedReshape(
		tensor: Operand<T>,
		shape: Operand<U>,
		inputMin: Operand<TFloat32>,
		inputMax: Operand<TFloat32>
	): QuantizedReshape<T> = java.quantizedReshape<T, U>(	
		tensor,
		shape,
		inputMin,
		inputMax
		)

	public fun <T : TNumber> range(
		start: Operand<T>,
		limit: Operand<T>,
		delta: Operand<T>
	): Range<T> = java.range<T>(	
		start,
		limit,
		delta
		)

	public fun <T : TType> rank(input: Operand<T>): Rank = java.rank<T>(	
		input
		)

	public fun <T : TType> readVariableOp(resource: Operand<*>, dtype: DataType<T>): ReadVariableOp<T>
			= java.readVariableOp<T>(	
		resource,
		dtype
		)

	public fun <T : TNumber> reduceAll(
		input: Operand<TBool>,
		axis: Operand<T>,
		keepDims: Boolean? = null
	): ReduceAll = java.reduceAll<T>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.core.ReduceAll.keepDims(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> reduceAny(
		input: Operand<TBool>,
		axis: Operand<T>,
		keepDims: Boolean? = null
	): ReduceAny = java.reduceAny<T>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.core.ReduceAny.keepDims(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> reduceMax(
		input: Operand<T>,
		axis: Operand<U>,
		keepDims: Boolean? = null
	): ReduceMax<T> = java.reduceMax<T, U>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.core.ReduceMax.keepDims(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> reduceMin(
		input: Operand<T>,
		axis: Operand<U>,
		keepDims: Boolean? = null
	): ReduceMin<T> = java.reduceMin<T, U>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.core.ReduceMin.keepDims(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> reduceProd(
		input: Operand<T>,
		axis: Operand<U>,
		keepDims: Boolean? = null
	): ReduceProd<T> = java.reduceProd<T, U>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.core.ReduceProd.keepDims(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> reduceSum(
		input: Operand<T>,
		axis: Operand<U>,
		keepDims: Boolean? = null
	): ReduceSum<T> = java.reduceSum<T, U>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.core.ReduceSum.keepDims(it) }
		).toTypedArray()
		)

	public fun <T : TType> refNextIteration(`data`: Operand<T>): RefNextIteration<T> =
			java.refNextIteration<T>(	
		data
		)

	public fun <T : TType> refSelect(index: Operand<TInt32>, inputs: Iterable<Operand<T>>):
			RefSelect<T> = java.refSelect<T>(	
		index,
		inputs
		)

	public fun <T : TType> refSwitch(`data`: Operand<T>, pred: Operand<TBool>): RefSwitch<T> =
			java.refSwitch<T>(	
		data,
		pred
		)

	public fun remoteFusedGraphExecute(
		inputs: Iterable<Operand<*>>,
		Toutputs: List<DataType<*>>,
		serializedRemoteFusedGraphExecuteInfo: String
	): RemoteFusedGraphExecute = java.remoteFusedGraphExecute(	
		inputs,
		Toutputs,
		serializedRemoteFusedGraphExecuteInfo
		)

	public fun <T : TType, U : TNumber> reshape(tensor: Operand<T>, shape: Operand<U>): Reshape<T> =
			java.reshape<T, U>(	
		tensor,
		shape
		)

	public fun <T : TNumber> resourceCountUpTo(
		resource: Operand<*>,
		limit: Long,
		T_: DataType<T>
	): ResourceCountUpTo<T> = java.resourceCountUpTo<T>(	
		resource,
		limit,
		T_
		)

	public fun <U : TType, T : TNumber> resourceGather(
		resource: Operand<*>,
		indices: Operand<T>,
		dtype: DataType<U>,
		batchDims: Long? = null,
		validateIndices: Boolean? = null
	): ResourceGather<U> = java.resourceGather<U, T>(	
		resource,
		indices,
		dtype,
		*listOfNotNull(
			batchDims?.let{ org.tensorflow.op.core.ResourceGather.batchDims(it) },
			validateIndices?.let{ org.tensorflow.op.core.ResourceGather.validateIndices(it) }
		).toTypedArray()
		)

	public fun <U : TType, T : TNumber> resourceGatherNd(
		resource: Operand<*>,
		indices: Operand<T>,
		dtype: DataType<U>
	): ResourceGatherNd<U> = java.resourceGatherNd<U, T>(	
		resource,
		indices,
		dtype
		)

	public fun <T : TNumber, U : TType> resourceScatterAdd(
		resource: Operand<*>,
		indices: Operand<T>,
		updates: Operand<U>
	): ResourceScatterAdd = java.resourceScatterAdd<T, U>(	
		resource,
		indices,
		updates
		)

	public fun <T : TNumber, U : TType> resourceScatterDiv(
		resource: Operand<*>,
		indices: Operand<T>,
		updates: Operand<U>
	): ResourceScatterDiv = java.resourceScatterDiv<T, U>(	
		resource,
		indices,
		updates
		)

	public fun <T : TNumber, U : TType> resourceScatterMax(
		resource: Operand<*>,
		indices: Operand<T>,
		updates: Operand<U>
	): ResourceScatterMax = java.resourceScatterMax<T, U>(	
		resource,
		indices,
		updates
		)

	public fun <T : TNumber, U : TType> resourceScatterMin(
		resource: Operand<*>,
		indices: Operand<T>,
		updates: Operand<U>
	): ResourceScatterMin = java.resourceScatterMin<T, U>(	
		resource,
		indices,
		updates
		)

	public fun <T : TNumber, U : TType> resourceScatterMul(
		resource: Operand<*>,
		indices: Operand<T>,
		updates: Operand<U>
	): ResourceScatterMul = java.resourceScatterMul<T, U>(	
		resource,
		indices,
		updates
		)

	public fun <T : TNumber, U : TType> resourceScatterNdAdd(
		ref: Operand<*>,
		indices: Operand<T>,
		updates: Operand<U>,
		useLocking: Boolean? = null
	): ResourceScatterNdAdd = java.resourceScatterNdAdd<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ResourceScatterNdAdd.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TType> resourceScatterNdMax(
		ref: Operand<*>,
		indices: Operand<T>,
		updates: Operand<U>,
		useLocking: Boolean? = null
	): ResourceScatterNdMax = java.resourceScatterNdMax<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ResourceScatterNdMax.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TType> resourceScatterNdMin(
		ref: Operand<*>,
		indices: Operand<T>,
		updates: Operand<U>,
		useLocking: Boolean? = null
	): ResourceScatterNdMin = java.resourceScatterNdMin<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ResourceScatterNdMin.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TType> resourceScatterNdSub(
		ref: Operand<*>,
		indices: Operand<T>,
		updates: Operand<U>,
		useLocking: Boolean? = null
	): ResourceScatterNdSub = java.resourceScatterNdSub<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ResourceScatterNdSub.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TType> resourceScatterNdUpdate(
		ref: Operand<*>,
		indices: Operand<T>,
		updates: Operand<U>,
		useLocking: Boolean? = null
	): ResourceScatterNdUpdate = java.resourceScatterNdUpdate<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ResourceScatterNdUpdate.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TType> resourceScatterSub(
		resource: Operand<*>,
		indices: Operand<T>,
		updates: Operand<U>
	): ResourceScatterSub = java.resourceScatterSub<T, U>(	
		resource,
		indices,
		updates
		)

	public fun <T : TNumber, U : TType> resourceScatterUpdate(
		resource: Operand<*>,
		indices: Operand<T>,
		updates: Operand<U>
	): ResourceScatterUpdate = java.resourceScatterUpdate<T, U>(	
		resource,
		indices,
		updates
		)

	public fun <T : TNumber, U : TType> resourceStridedSliceAssign(
		ref: Operand<*>,
		begin: Operand<T>,
		end: Operand<T>,
		strides: Operand<T>,
		value: Operand<U>,
		beginMask: Long? = null,
		endMask: Long? = null,
		ellipsisMask: Long? = null,
		newAxisMask: Long? = null,
		shrinkAxisMask: Long? = null
	): ResourceStridedSliceAssign = java.resourceStridedSliceAssign<T, U>(	
		ref,
		begin,
		end,
		strides,
		value,
		*listOfNotNull(
			beginMask?.let{ org.tensorflow.op.core.ResourceStridedSliceAssign.beginMask(it) },
			endMask?.let{ org.tensorflow.op.core.ResourceStridedSliceAssign.endMask(it) },
			ellipsisMask?.let{ org.tensorflow.op.core.ResourceStridedSliceAssign.ellipsisMask(it) },
			newAxisMask?.let{ org.tensorflow.op.core.ResourceStridedSliceAssign.newAxisMask(it) },
			shrinkAxisMask?.let{ org.tensorflow.op.core.ResourceStridedSliceAssign.shrinkAxisMask(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> reverse(tensor: Operand<T>, axis: Operand<U>): Reverse<T> =
			java.reverse<T, U>(	
		tensor,
		axis
		)

	public fun <T : TType, U : TNumber> reverseSequence(
		input: Operand<T>,
		seqLengths: Operand<U>,
		seqDim: Long,
		batchDim: Long? = null
	): ReverseSequence<T> = java.reverseSequence<T, U>(	
		input,
		seqLengths,
		seqDim,
		*listOfNotNull(
			batchDim?.let{ org.tensorflow.op.core.ReverseSequence.batchDim(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber, V : TNumber> roll(
		input: Operand<T>,
		shift: Operand<U>,
		axis: Operand<V>
	): Roll<T> = java.roll<T, U, V>(	
		input,
		shift,
		axis
		)

	public fun rpc(
		address: Operand<TString>,
		method: Operand<TString>,
		request: Operand<TString>,
		protocol: String? = null,
		failFast: Boolean? = null,
		timeoutInMs: Long? = null
	): Rpc = java.rpc(	
		address,
		method,
		request,
		*listOfNotNull(
			protocol?.let{ org.tensorflow.op.core.Rpc.protocol(it) },
			failFast?.let{ org.tensorflow.op.core.Rpc.failFast(it) },
			timeoutInMs?.let{ org.tensorflow.op.core.Rpc.timeoutInMs(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> scatterAdd(
		ref: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>,
		useLocking: Boolean? = null
	): ScatterAdd<T> = java.scatterAdd<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ScatterAdd.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> scatterDiv(
		ref: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>,
		useLocking: Boolean? = null
	): ScatterDiv<T> = java.scatterDiv<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ScatterDiv.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TNumber> scatterMax(
		ref: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>,
		useLocking: Boolean? = null
	): ScatterMax<T> = java.scatterMax<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ScatterMax.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TNumber> scatterMin(
		ref: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>,
		useLocking: Boolean? = null
	): ScatterMin<T> = java.scatterMin<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ScatterMin.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> scatterMul(
		ref: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>,
		useLocking: Boolean? = null
	): ScatterMul<T> = java.scatterMul<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ScatterMul.useLocking(it) }
		).toTypedArray()
		)

	public fun <U : TType, T : TNumber> scatterNd(
		indices: Operand<T>,
		updates: Operand<U>,
		shape: Operand<T>
	): ScatterNd<U> = java.scatterNd<U, T>(	
		indices,
		updates,
		shape
		)

	public fun <T : TType, U : TNumber> scatterNdAdd(
		ref: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>,
		useLocking: Boolean? = null
	): ScatterNdAdd<T> = java.scatterNdAdd<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ScatterNdAdd.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> scatterNdNonAliasingAdd(
		input: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>
	): ScatterNdNonAliasingAdd<T> = java.scatterNdNonAliasingAdd<T, U>(	
		input,
		indices,
		updates
		)

	public fun <T : TType, U : TNumber> scatterNdSub(
		ref: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>,
		useLocking: Boolean? = null
	): ScatterNdSub<T> = java.scatterNdSub<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ScatterNdSub.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> scatterNdUpdate(
		ref: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>,
		useLocking: Boolean? = null
	): ScatterNdUpdate<T> = java.scatterNdUpdate<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ScatterNdUpdate.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> scatterSub(
		ref: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>,
		useLocking: Boolean? = null
	): ScatterSub<T> = java.scatterSub<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ScatterSub.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> scatterUpdate(
		ref: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>,
		useLocking: Boolean? = null
	): ScatterUpdate<T> = java.scatterUpdate<T, U>(	
		ref,
		indices,
		updates,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.core.ScatterUpdate.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> select(
		condition: Operand<TBool>,
		t: Operand<T>,
		e: Operand<T>
	): Select<T> = java.select<T>(	
		condition,
		t,
		e
		)

	public fun <T : TType> setDiff1d(x: Operand<T>, y: Operand<T>): SetDiff1d<T, TInt32> =
			java.setDiff1d<T>(	
		x,
		y
		)

	public fun <T : TType, U : TNumber> setDiff1d(
		x: Operand<T>,
		y: Operand<T>,
		outIdx: DataType<U>
	): SetDiff1d<T, U> = java.setDiff1d<T, U>(	
		x,
		y,
		outIdx
		)

	public fun <T : TType> setSize(
		setIndices: Operand<TInt64>,
		setValues: Operand<T>,
		setShape: Operand<TInt64>,
		validateIndices: Boolean? = null
	): SetSize = java.setSize<T>(	
		setIndices,
		setValues,
		setShape,
		*listOfNotNull(
			validateIndices?.let{ org.tensorflow.op.core.SetSize.validateIndices(it) }
		).toTypedArray()
		)

	public fun <T : TType> shape(input: Operand<T>): org.tensorflow.op.core.Shape<TInt32> =
			java.shape<T>(	
		input
		)

	public fun <U : TNumber, T : TType> shape(input: Operand<T>, outType: DataType<U>):
			org.tensorflow.op.core.Shape<U> = java.shape<U, T>(	
		input,
		outType
		)

	public fun <T : TType> shapeN(input: Iterable<Operand<T>>): ShapeN<TInt32> = java.shapeN<T>(	
		input
		)

	public fun <U : TNumber, T : TType> shapeN(input: Iterable<Operand<T>>, outType: DataType<U>):
			ShapeN<U> = java.shapeN<U, T>(	
		input,
		outType
		)

	public fun <T : TType> size(input: Operand<T>): Size<TInt32> = java.size<T>(	
		input
		)

	public fun <U : TNumber, T : TType> size(input: Operand<T>, outType: DataType<U>): Size<U> =
			java.size<U, T>(	
		input,
		outType
		)

	public fun skipgram(
		filename: String,
		batchSize: Long,
		windowSize: Long? = null,
		minCount: Long? = null,
		subsample: Float? = null
	): Skipgram = java.skipgram(	
		filename,
		batchSize,
		*listOfNotNull(
			windowSize?.let{ org.tensorflow.op.core.Skipgram.windowSize(it) },
			minCount?.let{ org.tensorflow.op.core.Skipgram.minCount(it) },
			subsample?.let{ org.tensorflow.op.core.Skipgram.subsample(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> slice(
		input: Operand<T>,
		begin: Operand<U>,
		size: Operand<U>
	): Slice<T> = java.slice<T, U>(	
		input,
		begin,
		size
		)

	public fun <T : TType> snapshot(input: Operand<T>): Snapshot<T> = java.snapshot<T>(	
		input
		)

	public fun <T : TType, U : TNumber, V : TNumber> spaceToBatchNd(
		input: Operand<T>,
		blockShape: Operand<U>,
		paddings: Operand<V>
	): SpaceToBatchNd<T> = java.spaceToBatchNd<T, U, V>(	
		input,
		blockShape,
		paddings
		)

	public fun <T : TType> split(
		axis: Operand<TInt32>,
		value: Operand<T>,
		numSplit: Long
	): Split<T> = java.split<T>(	
		axis,
		value,
		numSplit
		)

	public fun <T : TType, U : TNumber> splitV(
		value: Operand<T>,
		sizeSplits: Operand<U>,
		axis: Operand<TInt32>,
		numSplit: Long
	): SplitV<T> = java.splitV<T, U>(	
		value,
		sizeSplits,
		axis,
		numSplit
		)

	public fun <T : TType> squeeze(input: Operand<T>, axis: List<Long>? = null): Squeeze<T> =
			java.squeeze<T>(	
		input,
		*listOfNotNull(
			axis?.let{ org.tensorflow.op.core.Squeeze.axis(it) }
		).toTypedArray()
		)

	public fun <T : TType> stack(values: Iterable<Operand<T>>, axis: Long? = null): Stack<T>
			= java.stack<T>(	
		values,
		*listOfNotNull(
			axis?.let{ org.tensorflow.op.core.Stack.axis(it) }
		).toTypedArray()
		)

	public fun stage(
		values: Iterable<Operand<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): Stage = java.stage(	
		values,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.Stage.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.Stage.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.Stage.container(it) },
			sharedName?.let{ org.tensorflow.op.core.Stage.sharedName(it) }
		).toTypedArray()
		)

	public fun stageClear(
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): StageClear = java.stageClear(	
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.StageClear.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.StageClear.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.StageClear.container(it) },
			sharedName?.let{ org.tensorflow.op.core.StageClear.sharedName(it) }
		).toTypedArray()
		)

	public fun stagePeek(
		index: Operand<TInt32>,
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): StagePeek = java.stagePeek(	
		index,
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.StagePeek.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.StagePeek.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.StagePeek.container(it) },
			sharedName?.let{ org.tensorflow.op.core.StagePeek.sharedName(it) }
		).toTypedArray()
		)

	public fun stageSize(
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): StageSize = java.stageSize(	
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.StageSize.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.StageSize.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.StageSize.container(it) },
			sharedName?.let{ org.tensorflow.op.core.StageSize.sharedName(it) }
		).toTypedArray()
		)

	public fun <T : TType> stopGradient(input: Operand<T>): StopGradient<T> = java.stopGradient<T>(	
		input
		)

	public fun <T : TType, U : TNumber> stridedSlice(
		input: Operand<T>,
		begin: Operand<U>,
		end: Operand<U>,
		strides: Operand<U>,
		beginMask: Long? = null,
		endMask: Long? = null,
		ellipsisMask: Long? = null,
		newAxisMask: Long? = null,
		shrinkAxisMask: Long? = null
	): StridedSlice<T> = java.stridedSlice<T, U>(	
		input,
		begin,
		end,
		strides,
		*listOfNotNull(
			beginMask?.let{ org.tensorflow.op.core.StridedSlice.beginMask(it) },
			endMask?.let{ org.tensorflow.op.core.StridedSlice.endMask(it) },
			ellipsisMask?.let{ org.tensorflow.op.core.StridedSlice.ellipsisMask(it) },
			newAxisMask?.let{ org.tensorflow.op.core.StridedSlice.newAxisMask(it) },
			shrinkAxisMask?.let{ org.tensorflow.op.core.StridedSlice.shrinkAxisMask(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> stridedSliceAssign(
		ref: Operand<T>,
		begin: Operand<U>,
		end: Operand<U>,
		strides: Operand<U>,
		value: Operand<T>,
		beginMask: Long? = null,
		endMask: Long? = null,
		ellipsisMask: Long? = null,
		newAxisMask: Long? = null,
		shrinkAxisMask: Long? = null
	): StridedSliceAssign<T> = java.stridedSliceAssign<T, U>(	
		ref,
		begin,
		end,
		strides,
		value,
		*listOfNotNull(
			beginMask?.let{ org.tensorflow.op.core.StridedSliceAssign.beginMask(it) },
			endMask?.let{ org.tensorflow.op.core.StridedSliceAssign.endMask(it) },
			ellipsisMask?.let{ org.tensorflow.op.core.StridedSliceAssign.ellipsisMask(it) },
			newAxisMask?.let{ org.tensorflow.op.core.StridedSliceAssign.newAxisMask(it) },
			shrinkAxisMask?.let{ org.tensorflow.op.core.StridedSliceAssign.shrinkAxisMask(it) }
		).toTypedArray()
		)

	public fun <U : TType, T : TNumber> stridedSliceGrad(
		shape: Operand<T>,
		begin: Operand<T>,
		end: Operand<T>,
		strides: Operand<T>,
		dy: Operand<U>,
		beginMask: Long? = null,
		endMask: Long? = null,
		ellipsisMask: Long? = null,
		newAxisMask: Long? = null,
		shrinkAxisMask: Long? = null
	): StridedSliceGrad<U> = java.stridedSliceGrad<U, T>(	
		shape,
		begin,
		end,
		strides,
		dy,
		*listOfNotNull(
			beginMask?.let{ org.tensorflow.op.core.StridedSliceGrad.beginMask(it) },
			endMask?.let{ org.tensorflow.op.core.StridedSliceGrad.endMask(it) },
			ellipsisMask?.let{ org.tensorflow.op.core.StridedSliceGrad.ellipsisMask(it) },
			newAxisMask?.let{ org.tensorflow.op.core.StridedSliceGrad.newAxisMask(it) },
			shrinkAxisMask?.let{ org.tensorflow.op.core.StridedSliceGrad.shrinkAxisMask(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> sum(
		input: Operand<T>,
		axis: Operand<U>,
		keepDims: Boolean? = null
	): Sum<T> = java.sum<T, U>(	
		input,
		axis,
		*listOfNotNull(
			keepDims?.let{ org.tensorflow.op.core.Sum.keepDims(it) }
		).toTypedArray()
		)

	public fun <T : TType> switchCond(`data`: Operand<T>, pred: Operand<TBool>): SwitchCond<T> =
			java.switchCond<T>(	
		data,
		pred
		)

	public fun <T : TType> temporaryVariable(
		shape: Shape,
		dtype: DataType<T>,
		varName: String? = null
	): TemporaryVariable<T> = java.temporaryVariable<T>(	
		shape,
		dtype,
		*listOfNotNull(
			varName?.let{ org.tensorflow.op.core.TemporaryVariable.varName(it) }
		).toTypedArray()
		)

	public fun <T : TType> tensorArray(
		size: Operand<TInt32>,
		dtype: DataType<T>,
		elementShape: Shape? = null,
		dynamicSize: Boolean? = null,
		clearAfterRead: Boolean? = null,
		identicalElementShapes: Boolean? = null,
		tensorArrayName: String? = null
	): TensorArray = java.tensorArray<T>(	
		size,
		dtype,
		*listOfNotNull(
			elementShape?.let{ org.tensorflow.op.core.TensorArray.elementShape(it) },
			dynamicSize?.let{ org.tensorflow.op.core.TensorArray.dynamicSize(it) },
			clearAfterRead?.let{ org.tensorflow.op.core.TensorArray.clearAfterRead(it) },
			identicalElementShapes?.let{ org.tensorflow.op.core.TensorArray.identicalElementShapes(it) },
			tensorArrayName?.let{ org.tensorflow.op.core.TensorArray.tensorArrayName(it) }
		).toTypedArray()
		)

	public fun tensorArrayClose(handle: Operand<*>): TensorArrayClose = java.tensorArrayClose(	
		handle
		)

	public fun <T : TType> tensorArrayConcat(
		handle: Operand<*>,
		flowIn: Operand<TFloat32>,
		dtype: DataType<T>,
		elementShapeExcept0: Shape? = null
	): TensorArrayConcat<T> = java.tensorArrayConcat<T>(	
		handle,
		flowIn,
		dtype,
		*listOfNotNull(
			elementShapeExcept0?.let{ org.tensorflow.op.core.TensorArrayConcat.elementShapeExcept0(it) }
		).toTypedArray()
		)

	public fun <T : TType> tensorArrayGather(
		handle: Operand<*>,
		indices: Operand<TInt32>,
		flowIn: Operand<TFloat32>,
		dtype: DataType<T>,
		elementShape: Shape? = null
	): TensorArrayGather<T> = java.tensorArrayGather<T>(	
		handle,
		indices,
		flowIn,
		dtype,
		*listOfNotNull(
			elementShape?.let{ org.tensorflow.op.core.TensorArrayGather.elementShape(it) }
		).toTypedArray()
		)

	public fun tensorArrayGrad(
		handle: Operand<*>,
		flowIn: Operand<TFloat32>,
		source: String
	): TensorArrayGrad = java.tensorArrayGrad(	
		handle,
		flowIn,
		source
		)

	public fun tensorArrayGradWithShape(
		handle: Operand<*>,
		flowIn: Operand<TFloat32>,
		shapeToPrepend: Operand<TInt32>,
		source: String
	): TensorArrayGradWithShape = java.tensorArrayGradWithShape(	
		handle,
		flowIn,
		shapeToPrepend,
		source
		)

	public fun <T : TType> tensorArrayPack(
		handle: Operand<TString>,
		flowIn: Operand<TFloat32>,
		dtype: DataType<T>,
		elementShape: Shape? = null
	): TensorArrayPack<T> = java.tensorArrayPack<T>(	
		handle,
		flowIn,
		dtype,
		*listOfNotNull(
			elementShape?.let{ org.tensorflow.op.core.TensorArrayPack.elementShape(it) }
		).toTypedArray()
		)

	public fun <T : TType> tensorArrayRead(
		handle: Operand<*>,
		index: Operand<TInt32>,
		flowIn: Operand<TFloat32>,
		dtype: DataType<T>
	): TensorArrayRead<T> = java.tensorArrayRead<T>(	
		handle,
		index,
		flowIn,
		dtype
		)

	public fun <T : TType> tensorArrayScatter(
		handle: Operand<*>,
		indices: Operand<TInt32>,
		value: Operand<T>,
		flowIn: Operand<TFloat32>
	): TensorArrayScatter = java.tensorArrayScatter<T>(	
		handle,
		indices,
		value,
		flowIn
		)

	public fun tensorArraySize(handle: Operand<*>, flowIn: Operand<TFloat32>): TensorArraySize =
			java.tensorArraySize(	
		handle,
		flowIn
		)

	public fun <T : TType> tensorArraySplit(
		handle: Operand<*>,
		value: Operand<T>,
		lengths: Operand<TInt64>,
		flowIn: Operand<TFloat32>
	): TensorArraySplit = java.tensorArraySplit<T>(	
		handle,
		value,
		lengths,
		flowIn
		)

	public fun <T : TType> tensorArrayUnpack(
		handle: Operand<TString>,
		value: Operand<T>,
		flowIn: Operand<TFloat32>
	): TensorArrayUnpack = java.tensorArrayUnpack<T>(	
		handle,
		value,
		flowIn
		)

	public fun <T : TType> tensorArrayWrite(
		handle: Operand<*>,
		index: Operand<TInt32>,
		value: Operand<T>,
		flowIn: Operand<TFloat32>
	): TensorArrayWrite = java.tensorArrayWrite<T>(	
		handle,
		index,
		value,
		flowIn
		)

	public fun <U : TType, T : TNumber> tensorListConcat(
		inputHandle: Operand<*>,
		elementShape: Operand<T>,
		leadingDims: Operand<TInt64>,
		elementDtype: DataType<U>
	): TensorListConcat<U> = java.tensorListConcat<U, T>(	
		inputHandle,
		elementShape,
		leadingDims,
		elementDtype
		)

	public fun <T : TType> tensorListConcatLists(
		inputA: Operand<*>,
		inputB: Operand<*>,
		elementDtype: DataType<T>
	): TensorListConcatLists = java.tensorListConcatLists<T>(	
		inputA,
		inputB,
		elementDtype
		)

	public fun <T : TNumber> tensorListElementShape(inputHandle: Operand<*>, shapeType: DataType<T>):
			TensorListElementShape<T> = java.tensorListElementShape<T>(	
		inputHandle,
		shapeType
		)

	public fun <T : TType, U : TNumber> tensorListFromTensor(tensor: Operand<T>,
			elementShape: Operand<U>): TensorListFromTensor = java.tensorListFromTensor<T, U>(	
		tensor,
		elementShape
		)

	public fun <T : TType> tensorListGather(
		inputHandle: Operand<*>,
		indices: Operand<TInt32>,
		elementShape: Operand<TInt32>,
		elementDtype: DataType<T>
	): TensorListGather<T> = java.tensorListGather<T>(	
		inputHandle,
		indices,
		elementShape,
		elementDtype
		)

	public fun <T : TType> tensorListGetItem(
		inputHandle: Operand<*>,
		index: Operand<TInt32>,
		elementShape: Operand<TInt32>,
		elementDtype: DataType<T>
	): TensorListGetItem<T> = java.tensorListGetItem<T>(	
		inputHandle,
		index,
		elementShape,
		elementDtype
		)

	public fun tensorListLength(inputHandle: Operand<*>): TensorListLength = java.tensorListLength(	
		inputHandle
		)

	public fun <T : TType> tensorListPopBack(
		inputHandle: Operand<*>,
		elementShape: Operand<TInt32>,
		elementDtype: DataType<T>
	): TensorListPopBack<T> = java.tensorListPopBack<T>(	
		inputHandle,
		elementShape,
		elementDtype
		)

	public fun <T : TType> tensorListPushBack(inputHandle: Operand<*>, tensor: Operand<T>):
			TensorListPushBack = java.tensorListPushBack<T>(	
		inputHandle,
		tensor
		)

	public fun <T : TType> tensorListPushBackBatch(inputHandles: Operand<*>, tensor: Operand<T>):
			TensorListPushBackBatch = java.tensorListPushBackBatch<T>(	
		inputHandles,
		tensor
		)

	public fun <T : TNumber, U : TType> tensorListReserve(
		elementShape: Operand<T>,
		numElements: Operand<TInt32>,
		elementDtype: DataType<U>
	): TensorListReserve = java.tensorListReserve<T, U>(	
		elementShape,
		numElements,
		elementDtype
		)

	public fun tensorListResize(inputHandle: Operand<*>, size: Operand<TInt32>): TensorListResize =
			java.tensorListResize(	
		inputHandle,
		size
		)

	public fun <T : TType, U : TNumber> tensorListScatter(
		tensor: Operand<T>,
		indices: Operand<TInt32>,
		elementShape: Operand<U>,
		numElements: Operand<TInt32>
	): TensorListScatter = java.tensorListScatter<T, U>(	
		tensor,
		indices,
		elementShape,
		numElements
		)

	public fun <T : TType> tensorListScatterIntoExistingList(
		inputHandle: Operand<*>,
		tensor: Operand<T>,
		indices: Operand<TInt32>
	): TensorListScatterIntoExistingList = java.tensorListScatterIntoExistingList<T>(	
		inputHandle,
		tensor,
		indices
		)

	public fun <T : TType> tensorListSetItem(
		inputHandle: Operand<*>,
		index: Operand<TInt32>,
		item: Operand<T>
	): TensorListSetItem = java.tensorListSetItem<T>(	
		inputHandle,
		index,
		item
		)

	public fun <T : TType, U : TNumber> tensorListSplit(
		tensor: Operand<T>,
		elementShape: Operand<U>,
		lengths: Operand<TInt64>
	): TensorListSplit = java.tensorListSplit<T, U>(	
		tensor,
		elementShape,
		lengths
		)

	public fun <T : TType> tensorListStack(
		inputHandle: Operand<*>,
		elementShape: Operand<TInt32>,
		elementDtype: DataType<T>,
		numElements: Long? = null
	): TensorListStack<T> = java.tensorListStack<T>(	
		inputHandle,
		elementShape,
		elementDtype,
		*listOfNotNull(
			numElements?.let{ org.tensorflow.op.core.TensorListStack.numElements(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> tensorScatterMax(
		tensor: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>
	): TensorScatterMax<T> = java.tensorScatterMax<T, U>(	
		tensor,
		indices,
		updates
		)

	public fun <T : TType, U : TNumber> tensorScatterMin(
		tensor: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>
	): TensorScatterMin<T> = java.tensorScatterMin<T, U>(	
		tensor,
		indices,
		updates
		)

	public fun <T : TType, U : TNumber> tensorScatterNdAdd(
		tensor: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>
	): TensorScatterNdAdd<T> = java.tensorScatterNdAdd<T, U>(	
		tensor,
		indices,
		updates
		)

	public fun <T : TType, U : TNumber> tensorScatterNdMax(
		tensor: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>
	): TensorScatterNdMax<T> = java.tensorScatterNdMax<T, U>(	
		tensor,
		indices,
		updates
		)

	public fun <T : TType, U : TNumber> tensorScatterNdMin(
		tensor: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>
	): TensorScatterNdMin<T> = java.tensorScatterNdMin<T, U>(	
		tensor,
		indices,
		updates
		)

	public fun <T : TType, U : TNumber> tensorScatterNdSub(
		tensor: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>
	): TensorScatterNdSub<T> = java.tensorScatterNdSub<T, U>(	
		tensor,
		indices,
		updates
		)

	public fun <T : TType, U : TNumber> tensorScatterNdUpdate(
		tensor: Operand<T>,
		indices: Operand<U>,
		updates: Operand<T>
	): TensorScatterNdUpdate<T> = java.tensorScatterNdUpdate<T, U>(	
		tensor,
		indices,
		updates
		)

	public fun <T : TType, U : TNumber> tensorStridedSliceUpdate(
		input: Operand<T>,
		begin: Operand<U>,
		end: Operand<U>,
		strides: Operand<U>,
		value: Operand<T>,
		beginMask: Long? = null,
		endMask: Long? = null,
		ellipsisMask: Long? = null,
		newAxisMask: Long? = null,
		shrinkAxisMask: Long? = null
	): TensorStridedSliceUpdate<T> = java.tensorStridedSliceUpdate<T, U>(	
		input,
		begin,
		end,
		strides,
		value,
		*listOfNotNull(
			beginMask?.let{ org.tensorflow.op.core.TensorStridedSliceUpdate.beginMask(it) },
			endMask?.let{ org.tensorflow.op.core.TensorStridedSliceUpdate.endMask(it) },
			ellipsisMask?.let{ org.tensorflow.op.core.TensorStridedSliceUpdate.ellipsisMask(it) },
			newAxisMask?.let{ org.tensorflow.op.core.TensorStridedSliceUpdate.newAxisMask(it) },
			shrinkAxisMask?.let{ org.tensorflow.op.core.TensorStridedSliceUpdate.shrinkAxisMask(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> tile(input: Operand<T>, multiples: Operand<U>): Tile<T> =
			java.tile<T, U>(	
		input,
		multiples
		)

	public fun timestamp(): Timestamp = java.timestamp(	
		
		)

	public fun tryRpc(
		address: Operand<TString>,
		method: Operand<TString>,
		request: Operand<TString>,
		protocol: String? = null,
		failFast: Boolean? = null,
		timeoutInMs: Long? = null
	): TryRpc = java.tryRpc(	
		address,
		method,
		request,
		*listOfNotNull(
			protocol?.let{ org.tensorflow.op.core.TryRpc.protocol(it) },
			failFast?.let{ org.tensorflow.op.core.TryRpc.failFast(it) },
			timeoutInMs?.let{ org.tensorflow.op.core.TryRpc.timeoutInMs(it) }
		).toTypedArray()
		)

	public fun <T : TType> unbatch(
		batchedTensor: Operand<T>,
		batchIndex: Operand<TInt64>,
		id: Operand<TInt64>,
		timeoutMicros: Long,
		container: String? = null,
		sharedName: String? = null
	): Unbatch<T> = java.unbatch<T>(	
		batchedTensor,
		batchIndex,
		id,
		timeoutMicros,
		*listOfNotNull(
			container?.let{ org.tensorflow.op.core.Unbatch.container(it) },
			sharedName?.let{ org.tensorflow.op.core.Unbatch.sharedName(it) }
		).toTypedArray()
		)

	public fun <T : TType> unbatchGrad(
		originalInput: Operand<T>,
		batchIndex: Operand<TInt64>,
		grad: Operand<T>,
		id: Operand<TInt64>,
		container: String? = null,
		sharedName: String? = null
	): UnbatchGrad<T> = java.unbatchGrad<T>(	
		originalInput,
		batchIndex,
		grad,
		id,
		*listOfNotNull(
			container?.let{ org.tensorflow.op.core.UnbatchGrad.container(it) },
			sharedName?.let{ org.tensorflow.op.core.UnbatchGrad.sharedName(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> unique(x: Operand<T>, axis: Operand<U>): Unique<T, TInt32> =
			java.unique<T, U>(	
		x,
		axis
		)

	public fun <T : TType, V : TNumber, U : TNumber> unique(
		x: Operand<T>,
		axis: Operand<U>,
		outIdx: DataType<V>
	): Unique<T, V> = java.unique<T, V, U>(	
		x,
		axis,
		outIdx
		)

	public fun <T : TType, U : TNumber> uniqueWithCounts(x: Operand<T>, axis: Operand<U>):
			UniqueWithCounts<T, TInt32> = java.uniqueWithCounts<T, U>(	
		x,
		axis
		)

	public fun <T : TType, V : TNumber, U : TNumber> uniqueWithCounts(
		x: Operand<T>,
		axis: Operand<U>,
		outIdx: DataType<V>
	): UniqueWithCounts<T, V> = java.uniqueWithCounts<T, V, U>(	
		x,
		axis,
		outIdx
		)

	public fun <T : TNumber> unravelIndex(indices: Operand<T>, dims: Operand<T>): UnravelIndex<T> =
			java.unravelIndex<T>(	
		indices,
		dims
		)

	public fun <T : TType> unstack(
		value: Operand<T>,
		num: Long,
		axis: Long? = null
	): Unstack<T> = java.unstack<T>(	
		value,
		num,
		*listOfNotNull(
			axis?.let{ org.tensorflow.op.core.Unstack.axis(it) }
		).toTypedArray()
		)

	public fun unstage(
		dtypes: List<DataType<*>>,
		capacity: Long? = null,
		memoryLimit: Long? = null,
		container: String? = null,
		sharedName: String? = null
	): Unstage = java.unstage(	
		dtypes,
		*listOfNotNull(
			capacity?.let{ org.tensorflow.op.core.Unstage.capacity(it) },
			memoryLimit?.let{ org.tensorflow.op.core.Unstage.memoryLimit(it) },
			container?.let{ org.tensorflow.op.core.Unstage.container(it) },
			sharedName?.let{ org.tensorflow.op.core.Unstage.sharedName(it) }
		).toTypedArray()
		)

	public fun <T : TType> varHandleOp(
		dtype: DataType<T>,
		shape: Shape,
		container: String? = null,
		sharedName: String? = null,
		allowedDevices: List<String>? = null
	): VarHandleOp = java.varHandleOp<T>(	
		dtype,
		shape,
		*listOfNotNull(
			container?.let{ org.tensorflow.op.core.VarHandleOp.container(it) },
			sharedName?.let{ org.tensorflow.op.core.VarHandleOp.sharedName(it) },
			allowedDevices?.let{ org.tensorflow.op.core.VarHandleOp.allowedDevices(it) }
		).toTypedArray()
		)

	public fun varIsInitializedOp(resource: Operand<*>): VarIsInitializedOp = java.varIsInitializedOp(	
		resource
		)

	public fun <T : TType> variable(
		`init`: Operand<T>,
		container: String? = null,
		sharedName: String? = null
	): Variable<T> = java.variable<T>(	
		init,
		*listOfNotNull(
			container?.let{ org.tensorflow.op.core.Variable.container(it) },
			sharedName?.let{ org.tensorflow.op.core.Variable.sharedName(it) }
		).toTypedArray()
		)

	public fun <T : TType> variable(
		shape: Shape,
		dtype: DataType<T>,
		container: String? = null,
		sharedName: String? = null
	): Variable<T> = java.variable<T>(	
		shape,
		dtype,
		*listOfNotNull(
			container?.let{ org.tensorflow.op.core.Variable.container(it) },
			sharedName?.let{ org.tensorflow.op.core.Variable.sharedName(it) }
		).toTypedArray()
		)

	public fun variableShape(input: Operand<*>): VariableShape<TInt32> = java.variableShape(	
		input
		)

	public fun <T : TNumber> variableShape(input: Operand<*>, outType: DataType<T>): VariableShape<T> =
			java.variableShape<T>(	
		input,
		outType
		)

	public fun <T : TType> `where`(condition: Operand<T>): Where = java.where<T>(	
		condition
		)

	public fun <T : TType> xlaSpmdFullToShardShape(input: Operand<T>, manualSharding: String):
			XlaSpmdFullToShardShape<T> = java.xlaSpmdFullToShardShape<T>(	
		input,
		manualSharding
		)

	public fun <T : TType> xlaSpmdShardToFullShape(
		input: Operand<T>,
		manualSharding: String,
		fullShape: Shape
	): XlaSpmdShardToFullShape<T> = java.xlaSpmdShardToFullShape<T>(	
		input,
		manualSharding,
		fullShape
		)

	public fun <T : TType, U : TNumber> zeros(dims: Operand<U>, type: DataType<T>): Zeros<T> =
			java.zeros<T, U>(	
		dims,
		type
		)

	public fun <T : TType> zerosLike(x: Operand<T>): ZerosLike<T> = java.zerosLike<T>(	
		x
		)
}
