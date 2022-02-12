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
import kotlin.Boolean
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
import kotlin.String
import kotlin.jvm.JvmName
import org.tensorflow.ConcreteFunction
import org.tensorflow.Operand
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
import org.tensorflow.ndarray.index.Index
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
import org.tensorflow.op.core.BatchFunction
import org.tensorflow.op.core.BatchToSpace
import org.tensorflow.op.core.BatchToSpaceNd
import org.tensorflow.op.core.Bitcast
import org.tensorflow.op.core.BroadcastDynamicShape
import org.tensorflow.op.core.BroadcastTo
import org.tensorflow.op.core.Bucketize
import org.tensorflow.op.core.Case
import org.tensorflow.op.core.ClipByValue
import org.tensorflow.op.core.Concat
import org.tensorflow.op.core.Constant
import org.tensorflow.op.core.ConsumeMutexLock
import org.tensorflow.op.core.ControlTrigger
import org.tensorflow.op.core.CountUpTo
import org.tensorflow.op.core.DecodeProto
import org.tensorflow.op.core.DeepCopy
import org.tensorflow.op.core.DeleteSessionTensor
import org.tensorflow.op.core.DestroyResourceOp
import org.tensorflow.op.core.DestroyTemporaryVariable
import org.tensorflow.op.core.DynamicPartition
import org.tensorflow.op.core.DynamicStitch
import org.tensorflow.op.core.EditDistance
import org.tensorflow.op.core.Empty
import org.tensorflow.op.core.EmptyTensorList
import org.tensorflow.op.core.EmptyTensorMap
import org.tensorflow.op.core.EncodeProto
import org.tensorflow.op.core.EnsureShape
import org.tensorflow.op.core.ExpandDims
import org.tensorflow.op.core.ExtractVolumePatches
import org.tensorflow.op.core.Fill
import org.tensorflow.op.core.Fingerprint
import org.tensorflow.op.core.For
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
import org.tensorflow.op.core.If
import org.tensorflow.op.core.ImmutableConst
import org.tensorflow.op.core.InitializeTable
import org.tensorflow.op.core.InitializeTableFromTextFile
import org.tensorflow.op.core.InplaceAdd
import org.tensorflow.op.core.InplaceSub
import org.tensorflow.op.core.InplaceUpdate
import org.tensorflow.op.core.IsVariableInitialized
import org.tensorflow.op.core.KthOrderStatistic
import org.tensorflow.op.core.LookupTableExport
import org.tensorflow.op.core.LookupTableFind
import org.tensorflow.op.core.LookupTableImport
import org.tensorflow.op.core.LookupTableInsert
import org.tensorflow.op.core.LookupTableSize
import org.tensorflow.op.core.LoopCond
import org.tensorflow.op.core.MakeUnique
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
import org.tensorflow.op.core.Ones
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
import org.tensorflow.op.core.PartitionedCall
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
import org.tensorflow.op.core.RemoteCall
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
import org.tensorflow.op.core.StatefulCase
import org.tensorflow.op.core.StatefulIf
import org.tensorflow.op.core.StatefulPartitionedCall
import org.tensorflow.op.core.StatefulWhile
import org.tensorflow.op.core.StatelessIf
import org.tensorflow.op.core.StatelessPartitionedCall
import org.tensorflow.op.core.StatelessWhile
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
import org.tensorflow.op.core.TensorMapErase
import org.tensorflow.op.core.TensorMapHasKey
import org.tensorflow.op.core.TensorMapInsert
import org.tensorflow.op.core.TensorMapLookup
import org.tensorflow.op.core.TensorMapSize
import org.tensorflow.op.core.TensorMapStackKeys
import org.tensorflow.op.core.TensorScatterNdAdd
import org.tensorflow.op.core.TensorScatterNdMax
import org.tensorflow.op.core.TensorScatterNdMin
import org.tensorflow.op.core.TensorScatterNdSub
import org.tensorflow.op.core.TensorScatterNdUpdate
import org.tensorflow.op.core.TensorStridedSliceUpdate
import org.tensorflow.op.core.Tile
import org.tensorflow.op.core.Timestamp
import org.tensorflow.op.core.TopKUnique
import org.tensorflow.op.core.TopKWithUnique
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
import org.tensorflow.op.core.While
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
 * An API for building operations as [Op][org.tensorflow.op.Op]s
 *
 * @see Ops
 */
public class KotlinOps(
    /**
     * Returns the java counterpart of this API
     */
    public override val java: Ops
) : OpsBase() {
    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = java.scope()

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

    public val tpu: TpuOps = TpuOps(this)

    public val audio: AudioOps = AudioOps(this)

    public val math: MathOps = MathOps(this)

    public val signal: SignalOps = SignalOps(this)

    public val quantization: QuantizationOps = QuantizationOps(this)

    public val train: TrainOps = TrainOps(this)

    /**
     * Raise a exception to abort the process when called.
     *  If exit_without_error is true, the process will exit normally,
     *  otherwise it will exit with a SIGABORT signal.
     *  
     * Returns nothing but an exception.
     *
     * @param options carries optional attribute values
     * @return a new instance of Abort
     * @see org.tensorflow.op.Ops.abort
     *
     * @param errorMsg Sets the errorMsg option.
     *
     * @param errorMsg A string which is the message associated with the exception.
     * @return this Options instance.
     * @param exitWithoutError Sets the exitWithoutError option.
     *
     * @param exitWithoutError the exitWithoutError option
     * @return this Options instance.
     */
    public fun abort(errorMsg: String? = null, exitWithoutError: Boolean? = null): Abort =
            java.abort(    
        *listOfNotNull(
            errorMsg?.let{ org.tensorflow.op.core.Abort.errorMsg(it) },
            exitWithoutError?.let{ org.tensorflow.op.core.Abort.exitWithoutError(it) }
        ).toTypedArray()
        )

    /**
     * Computes the &quot;logical and&quot; of elements across dimensions of a tensor.
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `[-rank(input), rank(input))`.
     * @param options carries optional attribute values
     * @return a new instance of All
     * @see org.tensorflow.op.Ops.all
     * @param keepDims Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public fun all(
        input: Operand<TBool>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): All = java.all(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.core.All.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Computes the &quot;logical or&quot; of elements across dimensions of a tensor.
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `[-rank(input), rank(input))`.
     * @param options carries optional attribute values
     * @return a new instance of Any
     * @see org.tensorflow.op.Ops.any
     * @param keepDims Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public fun any(
        input: Operand<TBool>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): Any = java.any(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.core.Any.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Creates a constant of `String` elements, using the default UTF-8 charset.
     *
     * @param data An array containing the values to put into the new constant.
     * @return the `String` constant
     * @see org.tensorflow.op.Ops.array
     */
    public fun array(vararg `data`: String): Constant<TString> = java.array(    
        *data
        )

    /**
     * Creates a constant of `int` elements.
     *
     * @param data An array containing the values to put into the new constant.
     * @return a float constant
     * @see org.tensorflow.op.Ops.array
     */
    public fun array(vararg `data`: Int): Constant<TInt32> = java.array(    
        *data
        )

    /**
     * Creates a constant of `double` elements.
     *
     * @param data An array containing the values to put into the new constant.
     * @return a double constant
     * @see org.tensorflow.op.Ops.array
     */
    public fun array(vararg `data`: Double): Constant<TFloat64> = java.array(    
        *data
        )

    /**
     * Creates a constant of `long` elements.
     *
     * @param data An array containing the values to put into the new constant.
     * @return a long constant
     * @see org.tensorflow.op.Ops.array
     */
    public fun array(vararg `data`: Long): Constant<TInt64> = java.array(    
        *data
        )

    /**
     * Creates a constant of `byte` elements.
     *
     * @param data An array containing the values to put into the new constant.
     * @return a byte constant
     * @see org.tensorflow.op.Ops.array
     */
    public fun array(vararg `data`: Byte): Constant<TUint8> = java.array(    
        *data
        )

    /**
     * Creates a constant of `boolean` elements.
     *
     * @param data An array containing the values to put into the new constant.
     * @return a boolean constant
     * @see org.tensorflow.op.Ops.array
     */
    public fun array(vararg `data`: Boolean): Constant<TBool> = java.array(    
        *data
        )

    /**
     * Creates a constant of `float` elements.
     *
     * @param data An array containing the values to put into the new constant.
     * @return a float constant
     * @see org.tensorflow.op.Ops.array
     */
    public fun array(vararg `data`: Float): Constant<TFloat32> = java.array(    
        *data
        )

    /**
     * Creates a constant of `String` elements, using the given charset.
     *
     * @param charset charset for encoding/decoding strings bytes.
     * @param data An array containing the values to put into the new constant. String elements are
     *      sequences of bytes from the last array dimension.
     * @return the `String` constant
     * @see org.tensorflow.op.Ops.array
     */
    public fun array(charset: Charset, vararg `data`: String): Constant<TString> = java.array(    
        charset,
        *data
        )

    /**
     * Asserts that the given condition is true.
     *  If `condition` evaluates to false, print the list of tensors in `data`.
     *  `summarize` determines how many entries of the tensors to print.
     *
     * @param condition The condition to evaluate.
     * @param data The tensors to print out when condition is false.
     * @param options carries optional attribute values
     * @return a new instance of AssertThat
     * @see org.tensorflow.op.Ops.assertThat
     * @param summarize Sets the summarize option.
     *
     * @param summarize Print this many entries of each tensor.
     * @return this Options instance.
     */
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

    /**
     * Update 'ref' by assigning 'value' to it.
     *  This operation outputs &quot;ref&quot; after the assignment is done.
     *  This makes it easier to chain operations that need to use the reset value.
     *
     * @param <T> data type for `output_ref` output
     * @param ref Should be from a `Variable` node. May be uninitialized.
     * @param value The value to be assigned to the variable.
     * @param options carries optional attribute values
     * @param <T> data type for `Assign` output and operands
     * @return a new instance of Assign
     * @see org.tensorflow.op.Ops.assign
     * @param validateShape Sets the validateShape option.
     *
     * @param validateShape If true, the operation will validate that the shape
     *  of 'value' matches the shape of the Tensor being assigned to.  If false,
     *  'ref' will take on the shape of 'value'.
     * @return this Options instance.
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking If True, the assignment will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
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

    /**
     * Update 'ref' by adding 'value' to it.
     *  This operation outputs &quot;ref&quot; after the update is done.
     *  This makes it easier to chain operations that need to use the reset value.
     *
     * @param <T> data type for `output_ref` output
     * @param ref Should be from a `Variable` node.
     * @param value The value to be added to the variable.
     * @param options carries optional attribute values
     * @param <T> data type for `AssignAdd` output and operands
     * @return a new instance of AssignAdd
     * @see org.tensorflow.op.Ops.assignAdd
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking If True, the addition will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
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

    /**
     * Adds a value to the current value of a variable.
     *  Any ReadVariableOp with a control dependency on this op is guaranteed to
     *  see the incremented value or a subsequent newer one.
     *
     * @param resource handle to the resource in which to store the variable.
     * @param value the value by which the variable will be incremented.
     * @return a new instance of AssignAddVariableOp
     * @see org.tensorflow.op.Ops.assignAddVariableOp
     */
    public fun assignAddVariableOp(resource: Operand<out TType>, value: Operand<out TType>):
            AssignAddVariableOp = java.assignAddVariableOp(    
        resource,
        value
        )

    /**
     * Update 'ref' by subtracting 'value' from it.
     *  This operation outputs &quot;ref&quot; after the update is done.
     *  This makes it easier to chain operations that need to use the reset value.
     *
     * @param <T> data type for `output_ref` output
     * @param ref Should be from a `Variable` node.
     * @param value The value to be subtracted to the variable.
     * @param options carries optional attribute values
     * @param <T> data type for `AssignSub` output and operands
     * @return a new instance of AssignSub
     * @see org.tensorflow.op.Ops.assignSub
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking If True, the subtraction will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
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

    /**
     * Subtracts a value from the current value of a variable.
     *  Any ReadVariableOp with a control dependency on this op is guaranteed to
     *  see the decremented value or a subsequent newer one.
     *
     * @param resource handle to the resource in which to store the variable.
     * @param value the value by which the variable will be incremented.
     * @return a new instance of AssignSubVariableOp
     * @see org.tensorflow.op.Ops.assignSubVariableOp
     */
    public fun assignSubVariableOp(resource: Operand<out TType>, value: Operand<out TType>):
            AssignSubVariableOp = java.assignSubVariableOp(    
        resource,
        value
        )

    /**
     * Assigns a new value to a variable.
     *  Any ReadVariableOp with a control dependency on this op is guaranteed to return
     *  this value or a subsequent newer value of the variable.
     *
     * @param resource handle to the resource in which to store the variable.
     * @param value the value to set the new tensor to use.
     * @return a new instance of AssignVariableOp
     * @see org.tensorflow.op.Ops.assignVariableOp
     */
    public fun assignVariableOp(resource: Operand<out TType>, value: Operand<out TType>):
            AssignVariableOp = java.assignVariableOp(    
        resource,
        value
        )

    /**
     * Defines a barrier that persists across different graph executions.
     *  A barrier represents a key-value map, where each key is a string, and
     *  each value is a tuple of tensors.
     *  
     * At runtime, the barrier contains 'complete' and 'incomplete'
     *  elements. A complete element has defined tensors for all components of
     *  its value tuple, and may be accessed using BarrierTakeMany. An
     *  incomplete element has some undefined components in its value tuple,
     *  and may be updated using BarrierInsertMany.
     *
     * @param componentTypes The type of each component in a value.
     * @param options carries optional attribute values
     * @return a new instance of Barrier
     * @see org.tensorflow.op.Ops.barrier
     * @param shapes Sets the shapes option.
     *
     * @param shapes The shape of each component in a value. Each shape must be 1 in the
     *  first dimension. The length of this attr must be the same as the length of
     *  component_types.
     * @return this Options instance.
     * @param capacity Sets the capacity option.
     *
     * @param capacity The capacity of the barrier.  The default capacity is MAX_INT32,
     *  which is the largest capacity of the underlying queue.
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container If non-empty, this barrier is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this barrier will be shared under the given name
     *  across multiple sessions.
     * @return this Options instance.
     */
    public fun barrier(
        componentTypes: List<Class<out TType>>,
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
     * @see org.tensorflow.op.Ops.barrierClose
     * @param cancelPendingEnqueues Sets the cancelPendingEnqueues option.
     *
     * @param cancelPendingEnqueues If true, all pending enqueue requests that are
     *  blocked on the barrier's queue will be canceled. InsertMany will fail, even
     *  if no new key is introduced.
     * @return this Options instance.
     */
    public fun barrierClose(handle: Operand<TString>, cancelPendingEnqueues: Boolean? = null):
            BarrierClose = java.barrierClose(    
        handle,
        *listOfNotNull(
            cancelPendingEnqueues?.let{ org.tensorflow.op.core.BarrierClose.cancelPendingEnqueues(it) }
        ).toTypedArray()
        )

    /**
     * Computes the number of incomplete elements in the given barrier.
     *
     * @param handle The handle to a barrier.
     * @return a new instance of BarrierIncompleteSize
     * @see org.tensorflow.op.Ops.barrierIncompleteSize
     */
    public fun barrierIncompleteSize(handle: Operand<TString>): BarrierIncompleteSize =
            java.barrierIncompleteSize(    
        handle
        )

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
     * @see org.tensorflow.op.Ops.barrierInsertMany
     */
    public fun barrierInsertMany(
        handle: Operand<TString>,
        keys: Operand<TString>,
        values: Operand<out TType>,
        componentIndex: Long
    ): BarrierInsertMany = java.barrierInsertMany(    
        handle,
        keys,
        values,
        componentIndex
        )

    /**
     * Computes the number of complete elements in the given barrier.
     *
     * @param handle The handle to a barrier.
     * @return a new instance of BarrierReadySize
     * @see org.tensorflow.op.Ops.barrierReadySize
     */
    public fun barrierReadySize(handle: Operand<TString>): BarrierReadySize =
            java.barrierReadySize(    
        handle
        )

    /**
     * Takes the given number of completed elements from a barrier.
     *  This operation concatenates completed-element component tensors along
     *  the 0th dimension to make a single component tensor.
     *  
     * Elements come out of the barrier when they are complete, and in the order
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
     * @see org.tensorflow.op.Ops.barrierTakeMany
     * @param allowSmallBatch Sets the allowSmallBatch option.
     *
     * @param allowSmallBatch Allow to return less than num_elements items if barrier is
     *  already closed.
     * @return this Options instance.
     * @param waitForIncomplete Sets the waitForIncomplete option.
     *
     * @param waitForIncomplete the waitForIncomplete option
     * @return this Options instance.
     * @param timeoutMs Sets the timeoutMs option.
     *
     * @param timeoutMs If the queue is empty, this operation will block for up to
     *  timeout_ms milliseconds.
     *  Note: This option is not supported yet.
     * @return this Options instance.
     */
    public fun barrierTakeMany(
        handle: Operand<TString>,
        numElements: Operand<TInt32>,
        componentTypes: List<Class<out TType>>,
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

    /**
     * Batches all input tensors nondeterministically.
     *  When many instances of this Op are being run concurrently with the same
     *  container/shared_name in the same device, some will output zero-shaped Tensors
     *  and others will output Tensors of size up to max_batch_size.
     *  
     * All Tensors in in_tensors are batched together (so, for example, labels and
     *  features should be batched with a single instance of this operation.
     *  
     * Each invocation of batch emits an `id` scalar which will be used to identify
     *  this particular invocation when doing unbatch or its gradient.
     *  
     * Each op which emits a non-empty batch will also emit a non-empty batch_index
     *  Tensor, which, is a &#91;K, 3&#93; matrix where each row contains the invocation's id,
     *  start, and length of elements of each set of Tensors present in batched_tensors.
     *  
     * Batched tensors are concatenated along the first dimension, and all tensors in
     *  in_tensors must have the first dimension of the same size.
     *  
     * in_tensors: The tensors to be batched.
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
     * @param inTensors The inTensors value
     * @param numBatchThreads The value of the numBatchThreads attribute
     * @param maxBatchSize The value of the maxBatchSize attribute
     * @param batchTimeoutMicros The value of the batchTimeoutMicros attribute
     * @param gradTimeoutMicros The value of the gradTimeoutMicros attribute
     * @param options carries optional attribute values
     * @return a new instance of Batch
     * @see org.tensorflow.op.Ops.batch
     * @param maxEnqueuedBatches Sets the maxEnqueuedBatches option.
     *
     * @param maxEnqueuedBatches the maxEnqueuedBatches option
     * @return this Options instance.
     * @param allowedBatchSizes Sets the allowedBatchSizes option.
     *
     * @param allowedBatchSizes the allowedBatchSizes option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     * @param batchingQueue Sets the batchingQueue option.
     *
     * @param batchingQueue the batchingQueue option
     * @return this Options instance.
     */
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

    /**
     * Batches all the inputs tensors to the computation done by the function.
     *  So, for example, in the following code
     *  ```
     * # This input will be captured.
     *  y = tf.placeholder_with_default(1.0, shape=[])
     *
     *  {@literal @
     * ```tf.Defun(tf.float32)
     *  def computation(a):
     *    return tf.matmul(a, a) + y
     *
     *  b = gen_batch_ops.batch_function(
     *          f=computation
     *          in_tensors=[a],
     *          captured_tensors=computation.captured_inputs,
     *          Tout=&#91;o.type for o in computation.definition.signature.output_arg&#93;,
     *          num_batch_threads=1,
     *          max_batch_size=10,
     *          batch_timeout_micros=100000,  # 100ms
     *          allowed_batch_sizes=&#91;3, 10&#93;,
     *          batching_queue=&quot;&quot;)
     *  }
     *  
     * If more than one session.run call is simultaneously trying to compute `b`
     *  the values of `a` will be gathered, non-deterministically concatenated
     *  along the first axis, and only one thread will run the computation.
     *  
     * Assumes that all arguments of the function are Tensors which will be batched
     *  along their first dimension.
     *  
     * Arguments that are captured, are not batched. The session.run call which does
     *  the concatenation, will use the values of the captured tensors available to it.
     *  Therefore, typical uses of captured tensors should involve values which remain
     *  unchanged across session.run calls. Inference is a good example of this.
     *  
     * SparseTensor is not supported. The return value of the decorated function
     *  must be a Tensor or a list/tuple of Tensors.
     *
     * @param inTensors The tensors to be batched.
     * @param capturedTensors The tensors which are captured in the function, and don't need
     *  to be batched.
     * @param f The value of the f attribute
     * @param numBatchThreads Number of scheduling threads for processing batches of work.
     *  Determines the number of batches processed in parallel.
     * @param maxBatchSize Batch sizes will never be bigger than this.
     * @param batchTimeoutMicros Maximum number of microseconds to wait before outputting
     *  an incomplete batch.
     * @param Tout the types of the output tensors.
     * @param options carries optional attribute values
     * @return a new instance of BatchFunction
     * @see org.tensorflow.op.Ops.batchFunction
     * @param maxEnqueuedBatches Sets the maxEnqueuedBatches option.
     *
     * @param maxEnqueuedBatches Maximum number of batches enqueued. Default: 10.
     * @return this Options instance.
     * @param allowedBatchSizes Sets the allowedBatchSizes option.
     *
     * @param allowedBatchSizes Optional list of allowed batch sizes. If left empty, does
     *  nothing. Otherwise, supplies a list of batch sizes, causing the op to pad
     *  batches up to one of those sizes. The entries must increase monotonically.
     *  If enable_large_batch_splitting is false (i.e., large-input-split is not
     *  enabled) the final entry must equal max_batch_size.
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container Controls the scope of sharing of this batch.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName Concurrently running instances of batch in the same device with the
     *  same container and shared_name will batch their elements together. If left
     *  empty, the op name will be used as the shared name.
     * @return this Options instance.
     * @param batchingQueue Sets the batchingQueue option.
     *
     * @param batchingQueue the batchingQueue option
     * @return this Options instance.
     * @param enableLargeBatchSplitting Sets the enableLargeBatchSplitting option.
     *
     * @param enableLargeBatchSplitting input with a large size (i.e., larger than the largest value
     * of
     *  `allowed_batch_sizes`) will be splitted into multiple batches with batch size.
     * @return this Options instance.
     */
    public fun batchFunction(
        inTensors: Iterable<Operand<*>>,
        capturedTensors: Iterable<Operand<*>>,
        f: ConcreteFunction,
        numBatchThreads: Long,
        maxBatchSize: Long,
        batchTimeoutMicros: Long,
        Tout: List<Class<out TType>>,
        maxEnqueuedBatches: Long? = null,
        allowedBatchSizes: List<Long>? = null,
        container: String? = null,
        sharedName: String? = null,
        batchingQueue: String? = null,
        enableLargeBatchSplitting: Boolean? = null
    ): BatchFunction = java.batchFunction(    
        inTensors,
        capturedTensors,
        f,
        numBatchThreads,
        maxBatchSize,
        batchTimeoutMicros,
        Tout,
        *listOfNotNull(
            maxEnqueuedBatches?.let{ org.tensorflow.op.core.BatchFunction.maxEnqueuedBatches(it) },
            allowedBatchSizes?.let{ org.tensorflow.op.core.BatchFunction.allowedBatchSizes(it) },
            container?.let{ org.tensorflow.op.core.BatchFunction.container(it) },
            sharedName?.let{ org.tensorflow.op.core.BatchFunction.sharedName(it) },
            batchingQueue?.let{ org.tensorflow.op.core.BatchFunction.batchingQueue(it) },
            enableLargeBatchSplitting?.let{
            org.tensorflow.op.core.BatchFunction.enableLargeBatchSplitting(it) }
        ).toTypedArray()
        )

    /**
     * BatchToSpace for 4-D tensors of type T.
     *  This is a legacy version of the more general BatchToSpaceND.
     *  
     * Rearranges (permutes) data from batch into blocks of spatial data, followed by
     *  cropping. This is the reverse transformation of SpaceToBatch. More specifically,
     *  this op outputs a copy of the input tensor where values from the `batch`
     *  dimension are moved in spatial blocks to the `height` and `width` dimensions,
     *  followed by cropping along the `height` and `width` dimensions.
     *
     * @param <T> data type for `output` output
     * @param input 4-D tensor with shape
     *  `&#91;batch*block_size*block_size, height_pad/block_size, width_pad/block_size, depth&#93;`.
     * Note that the batch size of the input tensor must be divisible by
     *  `block_size * block_size`.
     * @param crops 2-D tensor of non-negative integers with shape `&#91;2, 2&#93;`. It specifies
     *  how many elements to crop from the intermediate result across the spatial
     *  dimensions as follows:
     *  `
     * crops = [[crop_top, crop_bottom], [crop_left, crop_right]]
     *  
     * `
     * @param blockSize The value of the blockSize attribute
     * @param <T> data type for `BatchToSpace` output and operands
     * @return a new instance of BatchToSpace
     * @see org.tensorflow.op.Ops.batchToSpace
     */
    public fun <T : TType> batchToSpace(
        input: Operand<T>,
        crops: Operand<out TNumber>,
        blockSize: Long
    ): BatchToSpace<T> = java.batchToSpace<T>(    
        input,
        crops,
        blockSize
        )

    /**
     * BatchToSpace for N-D tensors of type T.
     *  This operation reshapes the &quot;batch&quot; dimension 0 into `M + 1` dimensions of shape
     *  `block_shape + &#91;batch&#93;`, interleaves these blocks back into the grid defined by
     *  the spatial dimensions `&#91;1, ..., M&#93;`, to obtain a result with the same rank as
     *  the input.  The spatial dimensions of this intermediate result are then
     *  optionally cropped according to `crops` to produce the output.  This is the
     *  reverse of SpaceToBatch.  See below for a precise description.
     *
     * @param <T> data type for `output` output
     * @param input N-D with shape `input_shape = &#91;batch&#93; + spatial_shape +
     * remaining_shape`,
     *  where spatial_shape has M dimensions.
     * @param blockShape 1-D with shape `[M]`, all values must be >= 1.
     * @param crops 2-D with shape `&#91;M, 2&#93;`, all values must be >= 0.
     *  `crops[i] = &#91;crop_start, crop_end&#93;` specifies the amount to crop from input
     *  dimension `i + 1`, which corresponds to spatial dimension `i`.  It is
     *  required that
     *  `crop_start[i] + crop_end[i] <= block_shape[i] * input_shape&#91;i + 1&#93;`.
     *  
     * This operation is equivalent to the following steps:
     *  <ol>
     *  <li>
     *  
     * Reshape `input` to `reshaped` of shape:
     *  &#91;block_shape[0&#93;, ..., block_shape&#91;M-1&#93;,
     *  batch / prod(block_shape),
     *  input_shape[1], ..., input_shape&#91;N-1&#93;]
     *  </li>
     *  <li>
     *  
     * Permute dimensions of `reshaped` to produce `permuted` of shape
     *  &#91;batch / prod(block_shape),
     *  
     * input_shape[1&#93;, block_shape[0],
     *  ...,
     *  input_shape[M], block_shape&#91;M-1&#93;,
     *  
     * input_shape&#91;M+1&#93;, ..., input_shape&#91;N-1&#93;]
     *  </li>
     *  <li>
     *  
     * Reshape `permuted` to produce `reshaped_permuted` of shape
     *  &#91;batch / prod(block_shape),
     *  
     * input_shape[1&#93; * block_shape[0],
     *  ...,
     *  input_shape[M] * block_shape&#91;M-1&#93;,
     *  
     * input_shape&#91;M+1&#93;,
     *  ...,
     *  input_shape&#91;N-1&#93;]
     *  </li>
     *  <li>
     *  
     * Crop the start and end of dimensions `&#91;1, ..., M&#93;` of
     *  `reshaped_permuted` according to `crops` to produce the output of shape:
     *  &#91;batch / prod(block_shape),
     *  
     * input_shape[1&#93; * block_shape[0] - crops&#91;0,0&#93; - crops&#91;0,1&#93;,
     *  ...,
     *  input_shape[M] * block_shape&#91;M-1&#93; - crops&#91;M-1,0&#93; - crops&#91;M-1,1&#93;,
     *  
     * input_shape&#91;M+1&#93;, ..., input_shape&#91;N-1&#93;]
     *  </li>
     *  </ol>
     *  
     * Some examples:
     *  
     * (1) For the following input of shape `&#91;4, 1, 1, 1&#93;`, `block_shape = &#91;2, 2&#93;`,
     * and
     *  `crops = &#91;[0, 0&#93;, &#91;0, 0&#93;]`:
     *  `
     * [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
     *  
     * `
     *  
     * The output tensor has shape `&#91;1, 2, 2, 1&#93;` and value:
     *  `
     * x = [[[[1], [2]], [[3], [4]]]]
     *  
     * `
     *  
     * (2) For the following input of shape `&#91;4, 1, 1, 3&#93;`, `block_shape = &#91;2, 2&#93;`,
     * and
     *  `crops = &#91;[0, 0&#93;, &#91;0, 0&#93;]`:
     *  `
     * [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
     *  
     * `
     *  
     * The output tensor has shape `&#91;1, 2, 2, 3&#93;` and value:
     *  `
     * x = [[[[1, 2, 3], [4, 5, 6]],
     *        [[7, 8, 9], [10, 11, 12]]]]
     *  
     * `
     *  
     * (3) For the following input of shape `&#91;4, 2, 2, 1&#93;`, `block_shape = &#91;2, 2&#93;`,
     * and
     *  `crops = &#91;[0, 0&#93;, &#91;0, 0&#93;]`:
     *  `
     * x = [[[[1], [3]], [[9], [11]]],
     *       [[[2], [4]], [[10], [12]]],
     *       [[[5], [7]], [[13], [15]]],
     *       [[[6], [8]], [[14], [16]]]]
     *  
     * `
     *  
     * The output tensor has shape `&#91;1, 4, 4, 1&#93;` and value:
     *  `
     * x = [[[[1],   [2],  [3],  [4]],
     *       [[5],   [6],  [7],  [8]],
     *       [[9],  [10], [11],  [12]],
     *       [[13], [14], [15],  [16]]]]
     *  
     * `
     *  
     * (4) For the following input of shape `&#91;8, 1, 3, 1&#93;`, `block_shape = &#91;2, 2&#93;`,
     * and
     *  `crops = &#91;[0, 0&#93;, &#91;2, 0&#93;]`:
     *  `
     * x = [[[[0], [1], [3]]], [[[0], [9], [11]]],
     *       [[[0], [2], [4]]], [[[0], [10], [12]]],
     *       [[[0], [5], [7]]], [[[0], [13], [15]]],
     *       [[[0], [6], [8]]], [[[0], [14], [16]]]]
     *  
     * `
     *  
     * The output tensor has shape `&#91;2, 2, 4, 1&#93;` and value:
     *  `
     * x = [[[[1],   [2],  [3],  [4]],
     *        [[5],   [6],  [7],  [8]]],
     *       [[[9],  [10], [11],  [12]],
     *        [[13], [14], [15],  [16]]]]
     *  
     * `
     * @param <T> data type for `BatchToSpaceND` output and operands
     * @return a new instance of BatchToSpaceNd
     * @see org.tensorflow.op.Ops.batchToSpaceNd
     */
    public fun <T : TType> batchToSpaceNd(
        input: Operand<T>,
        blockShape: Operand<out TNumber>,
        crops: Operand<out TNumber>
    ): BatchToSpaceNd<T> = java.batchToSpaceNd<T>(    
        input,
        blockShape,
        crops
        )

    /**
     * Bitcasts a tensor from one type to another without copying data.
     *  Given a tensor `input`, this operation returns a tensor that has the same buffer
     *  data as `input` with datatype `type`.
     *  
     * If the input datatype `T` is larger than the output datatype `type` then the
     *  shape changes from &#91;...&#93; to [..., sizeof(`T`)/sizeof(`type`)].
     *  
     * If `T` is smaller than `type`, the operator requires that the rightmost
     *  dimension be equal to sizeof(`type`)/sizeof(`T`). The shape then goes from
     *  [..., sizeof(`type`)/sizeof(`T`)] to &#91;...&#93;.
     *  
     * tf.bitcast() and tf.cast() work differently when real dtype is casted as a complex dtype
     *  (e.g. tf.complex64 or tf.complex128) as tf.cast() make imaginary part 0 while tf.bitcast()
     *  gives module error.
     *  For example,
     *  
     * Example 1:
     *  ```
     *
     * a = [1., 2., 3.]
     *  equality_bitcast = tf.bitcast(a, tf.complex128)
     *  Traceback (most recent call last):
     *  ...
     *  InvalidArgumentError: Cannot bitcast from 1 to 18 [Op:Bitcast]
     *  equality_cast = tf.cast(a, tf.complex128)
     *  print(equality_cast)
     *  tf.Tensor([1.+0.j 2.+0.j 3.+0.j], shape=(3,), dtype=complex128)
     * ```
     *  
     * Example 2:
     *  ```
     *
     * tf.bitcast(tf.constant(0xffffffff, dtype=tf.uint32), tf.uint8)
     *  <tf.Tensor: shape=(4,), dtype=uint8, numpy=array([255, 255, 255, 255], dtype=uint8)>
     * ```
     *  
     * Example 3:
     *  ```
     *
     * x = [1., 2., 3.]
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
     * ```
     *  
     * _NOTE_: Bitcast is implemented as a low-level cast, so machines with different
     *  endian orderings will give different results.
     *
     * @param <U> data type for `output` output
     * @param input The input value
     * @param type The value of the type attribute
     * @param <U> data type for `Bitcast` output and operands
     * @return a new instance of Bitcast
     * @see org.tensorflow.op.Ops.bitcast
     */
    public fun <U : TType> bitcast(input: Operand<out TType>, type: Class<U>): Bitcast<U> =
            java.bitcast<U>(    
        input,
        type
        )

    /**
     * Apply boolean mask to tensor.  Returns the flat array of each element corresponding to a
     * `true` in the mask.
     *  
     *
     *  Numpy equivalent is `tensor&#91;mask&#93;`.
     *  
     *
     *  In general, `0 < dim(mask) = K <= dim(tensor)`, and `mask`'s shape must match
     *  the first K dimensions of `tensor`'s shape.  We then have:
     *    `booleanMask(tensor, mask)&#91;i, j1,...,jd&#93; = tensor&#91;i1,...,iK,j1,...,jd&#93;`
     *  where `(i1,...,iK)` is the ith `true` entry of `mask` (row-major order).
     *  
     *
     *  The `axis` could be used with `mask` to indicate the axis to mask from (it's 0 by default).
     *  In that case, `axis + dim(mask) <= dim(tensor)` and `mask`'s shape must match
     *  the first `axis + dim(mask)` dimensions of `tensor`'s shape.
     *
     * @param tensor The tensor to mask.
     * @param mask The mask to apply.
     * @param options carries optional attributes values
     * @return The masked tensor.
     * @see org.tensorflow.op.Ops.booleanMask
     * @param axis 
     *
     * @param axis (Optional) The axis to mask from, or 0 if not set.
     */
    public fun <T : TType> booleanMask(
        tensor: Operand<T>,
        mask: Operand<TBool>,
        axis: Int? = null
    ): Operand<T> = java.booleanMask<T>(    
        tensor,
        mask,
        *listOfNotNull(
            axis?.let{ org.tensorflow.op.core.BooleanMask.axis(it) }
        ).toTypedArray()
        )

    /**
     * Updates a tensor at the masked values, and returns the updated tensor.  Does not mutate the
     * input tensors.  `updates` will be broadcasted by default
     *  
     *
     *  Numpy equivalent is `tensor&#91;mask&#93; = updates`.
     *  
     *
     *  In general, `0 < dim(mask) = K <= dim(tensor)`, and `mask`'s shape must match the first K
     * dimensions of
     *  `tensor`'s shape.  We then have: ```
     * booleanMask(tensor, mask)[i, j1,...,jd] =
     *  tensor[i1,...,iK,j1,...,jd]
     * ``` where `(i1,...,iK)` is the ith `true` entry of `mask` (row-major
     *  order).
     *  
     *
     *  The `axis` could be used with `mask` to indicate the axis to mask from (it's 0 by default).
     * In that
     *  case, `axis + dim(mask) <= dim(tensor)` and `mask`'s shape must match the first ```
     * axis +
     *  dim(mask)
     * ``` dimensions of `tensor`'s shape.
     *  
     *
     *  The shape of `updates` should be `&#91;n, t_1, t_2, ...&#93;` where `n` is the number of
     * true values in
     *  `mask` and `t_i` is the `i`th dimension of `tensor` after `axis` and `mask`.
     *  `updates` will be broadcasted to this shape by default, which can be disabled using
     * `options`.
     *
     * @param tensor The tensor to mask.
     * @param mask The mask to apply.
     * @param updates the new values
     * @param options carries optional attributes values
     * @return The masked tensor.
     * @see org.tensorflow.op.Ops.booleanMaskUpdate
     * @param axis 
     *
     * @param axis (Optional) The axis to mask from, or 0 if not set.
     * @param broadcast 
     *
     * @param broadcast (Optional) Whether to try broadcasting update.  True by default.
     */
    public fun <T : TType> booleanMaskUpdate(
        tensor: Operand<T>,
        mask: Operand<TBool>,
        updates: Operand<T>,
        axis: Int? = null,
        broadcast: Boolean? = null
    ): Operand<T> = java.booleanMaskUpdate<T>(    
        tensor,
        mask,
        updates,
        *listOfNotNull(
            axis?.let{ org.tensorflow.op.core.BooleanMaskUpdate.axis(it) },
            broadcast?.let{ org.tensorflow.op.core.BooleanMaskUpdate.broadcast(it) }
        ).toTypedArray()
        )

    /**
     * Return the shape of s0 op s1 with broadcast.
     *  Given `s0` and `s1`, tensors that represent shapes, compute `r0`, the
     *  broadcasted shape. `s0`, `s1` and `r0` are all integer vectors.
     *
     * @param <T> data type for `r0` output
     * @param s0 The s0 value
     * @param s1 The s1 value
     * @param <T> data type for `BroadcastArgs` output and operands
     * @return a new instance of BroadcastDynamicShape
     * @see org.tensorflow.op.Ops.broadcastDynamicShape
     */
    public fun <T : TNumber> broadcastDynamicShape(s0: Operand<T>, s1: Operand<T>):
            BroadcastDynamicShape<T> = java.broadcastDynamicShape<T>(    
        s0,
        s1
        )

    /**
     * Broadcast an array for a compatible shape.
     *  Broadcasting is the process of making arrays to have compatible shapes
     *  for arithmetic operations. Two shapes are compatible if for each
     *  dimension pair they are either equal or one of them is one. When trying
     *  to broadcast a Tensor to a shape, it starts with the trailing dimensions,
     *  and works its way forward.
     *  
     * For example,
     *  ```
     *
     * x = tf.constant([1, 2, 3])
     *  y = tf.broadcast_to(x, [3, 3])
     *  print(y)
     *  tf.Tensor(
     *  [[1 2 3]
     *  [1 2 3]
     *  [1 2 3]], shape=(3, 3), dtype=int32)
     * ```
     *  
     * In the above example, the input Tensor with the shape of `&#91;1, 3&#93;`
     *  is broadcasted to output Tensor with shape of `&#91;3, 3&#93;`.
     *  
     * When doing broadcasted operations such as multiplying a tensor
     *  by a scalar, broadcasting (usually) confers some time or space
     *  benefit, as the broadcasted tensor is never materialized.
     *  
     * However, `broadcast_to` does not carry with it any such benefits.
     *  The newly-created tensor takes the full memory of the broadcasted
     *  shape. (In a graph context, `broadcast_to` might be fused to
     *  subsequent operation and then be optimized away, however.)
     *
     * @param <T> data type for `output` output
     * @param input A Tensor to broadcast.
     * @param shape An 1-D `int` Tensor. The shape of the desired output.
     * @param <T> data type for `BroadcastTo` output and operands
     * @return a new instance of BroadcastTo
     * @see org.tensorflow.op.Ops.broadcastTo
     */
    public fun <T : TType> broadcastTo(input: Operand<T>, shape: Operand<out TNumber>):
            BroadcastTo<T> = java.broadcastTo<T>(    
        input,
        shape
        )

    /**
     * Bucketizes 'input' based on 'boundaries'.
     *  For example, if the inputs are
     *  boundaries = &#91;0, 10, 100&#93;
     *  input = &#91;[-5, 10000&#93;
     *  &#91;150,   10&#93;
     *  &#91;5,    100&#93;]
     *  
     * then the output will be
     *  output = &#91;[0, 3&#93;
     *  &#91;3, 2&#93;
     *  &#91;1, 3&#93;]
     *
     * @param input Any shape of Tensor contains with int or float type.
     * @param boundaries A sorted list of floats gives the boundary of the buckets.
     * @return a new instance of Bucketize
     * @see org.tensorflow.op.Ops.bucketize
     */
    public fun bucketize(input: Operand<out TNumber>, boundaries: List<Float>): Bucketize =
            java.bucketize(    
        input,
        boundaries
        )

    /**
     * Calls the function in an execution environment, adding its graph as a function if it isn't
     *  already present. Only works for functions with a single input and output.
     *
     * @param argument the argument to the call
     * @return the output of the function
     * @see ConcreteFunction.call
     * @see org.tensorflow.op.Ops.call
     */
    public fun call(function: ConcreteFunction, argument: Operand<*>): Operand<*> = java.call(    
        function,
        argument
        )

    /**
     * Calls the function in an execution environment, adding its graph as a function if it isn't
     *  already present. The inputs and outputs are keyed by the names set in the `Signature`.
     *
     * @param arguments the arguments to the call
     * @return the outputs of the function
     * @see ConcreteFunction.call
     * @see org.tensorflow.op.Ops.call
     */
    public fun call(function: ConcreteFunction, arguments: Map<String, Operand<*>>): Map<String,
            Operand<*>> = java.call(    
        function,
        arguments
        )

    /**
     * An n-way switch statement which calls a single branch function.
     *  ```
     * An n-way switch statement, implementing the following:
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
     *  
     * ```
     *  ```
     *  }
     *
     *  
     * Selects between [StatefulCase] and [StatelessCase] based on the statefulness of the function
     * arguments.
     *
     * @param branchIndex The branch selector, an int32 Tensor.
     * @param input A list of input tensors passed to the branch function.
     * @param Tout A list of output types.
     * @param branches `
     * A list of functions each of which takes 'inputs' and returns a list of
     *    tensors, whose types are the same as what every other branch returns.
     *  
     * `
     * @param options carries optional attribute values
     * @return a new instance of Case
     * @see org.tensorflow.op.Ops.caseOp
     * @param outputShapes Sets the outputShapes option.
     *
     * @param outputShapes the outputShapes option
     * @return this Options instance.
     */
    public fun caseOp(
        branchIndex: Operand<TInt32>,
        input: Iterable<Operand<*>>,
        Tout: List<Class<out TType>>,
        branches: List<ConcreteFunction>,
        outputShapes: List<Shape>? = null
    ): Case = java.caseOp(    
        branchIndex,
        input,
        Tout,
        branches,
        *listOfNotNull(
            outputShapes?.let{ org.tensorflow.op.core.Case.outputShapes(it) }
        ).toTypedArray()
        )

    /**
     * Clips tensor values to a specified min and max.
     *  Given a tensor `t`, this operation returns a tensor of the same type and
     *  shape as `t` with its values clipped to `clip_value_min` and `clip_value_max`.
     *  Any values less than `clip_value_min` are set to `clip_value_min`. Any values
     *  greater than `clip_value_max` are set to `clip_value_max`.
     *
     * @param <T> data type for `output` output
     * @param t A `Tensor`.
     * @param clipValueMin A 0-D (scalar) `Tensor`, or a `Tensor` with the same shape
     *  as `t`. The minimum value to clip by.
     * @param clipValueMax A 0-D (scalar) `Tensor`, or a `Tensor` with the same shape
     *  as `t`. The maximum value to clip by.
     * @param <T> data type for `ClipByValue` output and operands
     * @return a new instance of ClipByValue
     * @see org.tensorflow.op.Ops.clipByValue
     */
    public fun <T : TType> clipByValue(
        t: Operand<T>,
        clipValueMin: Operand<T>,
        clipValueMax: Operand<T>
    ): ClipByValue<T> = java.clipByValue<T>(    
        t,
        clipValueMin,
        clipValueMax
        )

    /**
     * Concatenates tensors along one dimension.
     *
     * @param <T> data type for `output` output
     * @param values List of `N` Tensors to concatenate. Their ranks and types must match,
     *  and their sizes must match in all dimensions except `concat_dim`.
     * @param axis 0-D.  The dimension along which to concatenate.  Must be in the
     *  range [-rank(values), rank(values)).
     * @param <T> data type for `ConcatV2` output and operands
     * @return a new instance of Concat
     * @see org.tensorflow.op.Ops.concat
     */
    public fun <T : TType> concat(values: Iterable<Operand<T>>, axis: Operand<out TNumber>):
            Concat<T> = java.concat<T>(    
        values,
        axis
        )

    /**
     * Creates a constant containing a single `int` element.
     *
     * @param data The value to put into the new constant.
     * @return an integer constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Int): Constant<TInt32> = java.constant(    
        data
        )

    /**
     * Creates a rank-3 constant of `double` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a double constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<DoubleArray>>): Constant<TFloat64> = java.constant(    
        data
        )

    /**
     * Creates a rank-5 constant of `byte` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a byte constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<Array<ByteArray>>>>): Constant<TUint8> =
            java.constant(    
        data
        )

    /**
     * Creates a constant of `String` elements that is a copy of a given n-dimensional array,
     *  using the default UTF-8 encoding.
     *
     * @param data an n-dimensional array of `String` elements.
     * @return a string constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: NdArray<String>): Constant<TString> = java.constant(    
        data
        )

    /**
     * Creates a rank-4 constant of `int` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return an integer constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<IntArray>>>): Constant<TInt32> = java.constant(    
        data
        )

    /**
     * Creates a constant containing a single `byte` element.
     *
     * @param data The value to put into the new constant.
     * @return a byte constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Byte): Constant<TUint8> = java.constant(    
        data
        )

    /**
     * Creates a rank-2 constant of `long` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a long constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<LongArray>): Constant<TInt64> = java.constant(    
        data
        )

    /**
     * Creates a rank-6 constant of `float` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a float constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<Array<Array<FloatArray>>>>>): Constant<TFloat32> =
            java.constant(    
        data
        )

    /**
     * Creates a rank-6 constant of `boolean` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a boolean constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<Array<Array<BooleanArray>>>>>): Constant<TBool> =
            java.constant(    
        data
        )

    /**
     * Creates a rank-4 constant of `boolean` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a boolean constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<BooleanArray>>>): Constant<TBool> =
            java.constant(    
        data
        )

    /**
     * Creates a rank-3 constant of `float` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a float constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<FloatArray>>): Constant<TFloat32> = java.constant(    
        data
        )

    /**
     * Creates a rank-5 constant of `float` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a float constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<Array<FloatArray>>>>): Constant<TFloat32> =
            java.constant(    
        data
        )

    /**
     * Creates a rank-5 constant of `long` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a long constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<Array<LongArray>>>>): Constant<TInt64> =
            java.constant(    
        data
        )

    /**
     * Creates a rank-1 constant of `int` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return an integer constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: IntArray): Constant<TInt32> = java.constant(    
        data
        )

    /**
     * Creates a rank-2 constant of `float` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a float constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<FloatArray>): Constant<TFloat32> = java.constant(    
        data
        )

    /**
     * Creates a rank-2 constant of `boolean` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a boolean constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<BooleanArray>): Constant<TBool> = java.constant(    
        data
        )

    /**
     * Creates a constant containing a single `double` element.
     *
     * @param data The value to put into the new constant.
     * @return a double constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Double): Constant<TFloat64> = java.constant(    
        data
        )

    /**
     * Creates a constant containing a single `boolean` element.
     *
     * @param data The value to put into the new constant.
     * @return a boolean constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Boolean): Constant<TBool> = java.constant(    
        data
        )

    /**
     * Creates a constant containing a single `long` element.
     *
     * @param data The value to put into the new constant.
     * @return a long constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Long): Constant<TInt64> = java.constant(    
        data
        )

    /**
     * Creates a `String` constant using the default, UTF-8 encoding.
     *
     * @param data The string to put into the new constant.
     * @return a string constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: String): Constant<TString> = java.constant(    
        data
        )

    /**
     * Creates a constant of `boolean` elements that is a copy of a given n-dimensional array.
     *
     * @param data an n-dimensional array of `boolean` elements.
     * @return a boolean constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: BooleanNdArray): Constant<TBool> = java.constant(    
        data
        )

    /**
     * Creates a rank-1 constant of `double` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a double constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: DoubleArray): Constant<TFloat64> = java.constant(    
        data
        )

    /**
     * Creates a constant of `long` elements that is a copy of a given n-dimensional array.
     *
     * @param data an n-dimensional array of `long` elements.
     * @return a long constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: LongNdArray): Constant<TInt64> = java.constant(    
        data
        )

    /**
     * Creates a rank-1 constant of `float` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a float constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: FloatArray): Constant<TFloat32> = java.constant(    
        data
        )

    /**
     * Creates a rank-3 constant of `long` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a long constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<LongArray>>): Constant<TInt64> = java.constant(    
        data
        )

    /**
     * Creates a rank-3 constant of `boolean` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a boolean constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<BooleanArray>>): Constant<TBool> = java.constant(    
        data
        )

    /**
     * Creates a rank-1 constant of `byte` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a byte constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: ByteArray): Constant<TUint8> = java.constant(    
        data
        )

    /**
     * Creates a rank-3 constant of `int` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return an integer constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<IntArray>>): Constant<TInt32> = java.constant(    
        data
        )

    /**
     * Creates a constant of `int` elements that is a copy of a given n-dimensional array.
     *
     * @param data an n-dimensional array of `int` elements.
     * @return an integer constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: IntNdArray): Constant<TInt32> = java.constant(    
        data
        )

    /**
     * Creates a rank-1 constant of `long` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a long constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: LongArray): Constant<TInt64> = java.constant(    
        data
        )

    /**
     * Creates a constant of `float` elements that is a copy of a given n-dimensional array.
     *
     * @param data an n-dimensional array of `float` elements.
     * @return a float constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: FloatNdArray): Constant<TFloat32> = java.constant(    
        data
        )

    /**
     * Creates a rank-5 constant of `int` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return an integer constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<Array<IntArray>>>>): Constant<TInt32> =
            java.constant(    
        data
        )

    /**
     * Creates a rank-5 constant of `double` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a double constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<Array<DoubleArray>>>>): Constant<TFloat64> =
            java.constant(    
        data
        )

    /**
     * Creates a rank-5 constant of `boolean` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a boolean constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<Array<BooleanArray>>>>): Constant<TBool> =
            java.constant(    
        data
        )

    /**
     * Creates a rank-6 constant of `int` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return an integer constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<Array<Array<IntArray>>>>>): Constant<TInt32> =
            java.constant(    
        data
        )

    /**
     * Creates a constant of `double` elements that is a copy of a given n-dimensional array.
     *
     * @param data an n-dimensional array of `double` elements.
     * @return a double constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: DoubleNdArray): Constant<TFloat64> = java.constant(    
        data
        )

    /**
     * Creates a rank-6 constant of `double` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a double constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<Array<Array<DoubleArray>>>>>): Constant<TFloat64>
            = java.constant(    
        data
        )

    /**
     * Creates a rank-6 constant of `long` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a long constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<Array<Array<LongArray>>>>>): Constant<TInt64> =
            java.constant(    
        data
        )

    /**
     * Creates a rank-2 constant of `int` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return an integer constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<IntArray>): Constant<TInt32> = java.constant(    
        data
        )

    /**
     * Creates a rank-1 constant of `boolean` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a boolean constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: BooleanArray): Constant<TBool> = java.constant(    
        data
        )

    /**
     * Creates a constant containing a single `float` element.
     *
     * @param data The value to put into the new constant.
     * @return a float constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Float): Constant<TFloat32> = java.constant(    
        data
        )

    /**
     * Creates a rank-4 constant of `byte` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a byte constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<ByteArray>>>): Constant<TUint8> = java.constant(    
        data
        )

    /**
     * Creates a rank-4 constant of `float` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a float constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<FloatArray>>>): Constant<TFloat32> =
            java.constant(    
        data
        )

    /**
     * Creates a constant of `byte` elements that is a copy of a given n-dimensional array.
     *
     * @param data an n-dimensional array of `byte` elements.
     * @return a byte constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: ByteNdArray): Constant<TUint8> = java.constant(    
        data
        )

    /**
     * Creates a rank-6 constant of `byte` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a byte constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<Array<Array<ByteArray>>>>>): Constant<TUint8> =
            java.constant(    
        data
        )

    /**
     * Creates a rank-4 constant of `long` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a long constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<LongArray>>>): Constant<TInt64> = java.constant(    
        data
        )

    /**
     * Creates a rank-2 constant of `byte` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a byte constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<ByteArray>): Constant<TUint8> = java.constant(    
        data
        )

    /**
     * Creates a rank-2 constant of `double` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a double constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<DoubleArray>): Constant<TFloat64> = java.constant(    
        data
        )

    /**
     * Creates a rank-3 constant of `byte` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a byte constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<ByteArray>>): Constant<TUint8> = java.constant(    
        data
        )

    /**
     * Creates a rank-4 constant of `double` elements.
     *
     * @param data An array containing the values to put into the new constant. The dimensions of
     * the
     *      new constant will match those of the array.
     * @return a double constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(`data`: Array<Array<Array<DoubleArray>>>): Constant<TFloat64> =
            java.constant(    
        data
        )

    /**
     * Creates a rank-1 constant of `long` elements representing the size of each dimensions of
     *  the given shape.
     *
     * @param shape a shape
     * @return a long constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(shape: Shape): Constant<TInt64> = java.constant(    
        shape
        )

    /**
     * Creates a constant of `String` elements that is a copy of a given n-dimensional array,
     *  using the given encoding.
     *
     * @param charset charset used to encode/decode string bytes.
     * @param data an n-dimensional array of `String` elements.
     * @return a string constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(charset: Charset, `data`: NdArray<String>): Constant<TString> =
            java.constant(    
        charset,
        data
        )

    /**
     * Creates a constant of `String` elements, using the given charset.
     *
     * @param charset charset for encoding/decoding strings bytes.
     * @param data An array containing the values to put into the new constant. String elements are
     *      sequences of bytes from the last array dimension.
     * @return the `String` constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(charset: Charset, `data`: Array<String>): Constant<TString> =
            java.constant(    
        charset,
        data
        )

    /**
     * Creates a `String` constant using a specified encoding.
     *
     * @param charset The encoding from String to bytes.
     * @param data The string to put into the new constant.
     * @return a string constant
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(charset: Charset, `data`: String): Constant<TString> = java.constant(    
        charset,
        data
        )

    /**
     * Create a [TBool] constant with data from the given buffer.
     *
     * @param shape the tensor shape.
     * @param data a buffer containing the tensor data.
     * @return an boolean constant
     * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(shape: Shape, `data`: BooleanDataBuffer): Constant<TBool> = java.constant(    
        shape,
        data
        )

    /**
     * Create a [TString] constant with data from the given buffer, using the default UTF-8
     *  encoding.
     *
     * @param shape the tensor shape.
     * @param data a buffer containing the tensor data.
     * @return a string constant
     * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(shape: Shape, `data`: DataBuffer<String>): Constant<TString> =
            java.constant(    
        shape,
        data
        )

    /**
     * Create a [TUint8] constant with data from the given buffer.
     *
     * @param shape the tensor shape.
     * @param data a buffer containing the tensor data.
     * @return a byte constant
     * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(shape: Shape, `data`: ByteDataBuffer): Constant<TUint8> = java.constant(    
        shape,
        data
        )

    /**
     * Create a [TInt32] constant with data from the given buffer.
     *
     * @param shape the tensor shape.
     * @param data a buffer containing the tensor data.
     * @return an integer constant
     * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(shape: Shape, `data`: IntDataBuffer): Constant<TInt32> = java.constant(    
        shape,
        data
        )

    /**
     * Create a [TInt64] constant with data from the given buffer.
     *
     * @param shape the tensor shape.
     * @param data a buffer containing the tensor data.
     * @return a long constant
     * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(shape: Shape, `data`: LongDataBuffer): Constant<TInt64> = java.constant(    
        shape,
        data
        )

    /**
     * Create a [TFloat64] constant with data from the given buffer.
     *
     * @param shape the tensor shape.
     * @param data a buffer containing the tensor data.
     * @return a double constant
     * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(shape: Shape, `data`: DoubleDataBuffer): Constant<TFloat64> =
            java.constant(    
        shape,
        data
        )

    /**
     * Create a [TFloat32] constant with data from the given buffer.
     *
     * @param shape the tensor shape.
     * @param data a buffer containing the tensor data.
     * @return a float constant
     * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(shape: Shape, `data`: FloatDataBuffer): Constant<TFloat32> = java.constant(    
        shape,
        data
        )

    /**
     * Creates a scalar of `type`, with the value of `number`. `number` may be
     *  truncated if it does not fit in the target type.
     *
     * @param type the type of tensor to create. Must be concrete (i.e. not
     * [org.tensorflow.types.family.TFloating])
     * @param number the value of the tensor
     * @return a constant of the passed type
     * @throws IllegalArgumentException if the type is abstract (i.e.
     * [org.tensorflow.types.family.TFloating]) or unknown.
     * @see org.tensorflow.op.Ops.constant
     */
    public fun <T : TNumber> constant(type: Class<T>, number: Number): Constant<T> =
            java.constant<T>(    
        type,
        number
        )

    /**
     * Create a [TString] constant with data from the given buffer, using the given encoding.
     *
     * @param charset charset used to encode/decode string bytes.
     * @param shape the tensor shape.
     * @param data a buffer containing the tensor data.
     * @return a string constant
     * @throws IllegalArgumentException If the tensor shape is not compatible with the buffer
     * @see org.tensorflow.op.Ops.constant
     */
    public fun constant(
        charset: Charset,
        shape: Shape,
        `data`: DataBuffer<String>
    ): Constant<TString> = java.constant(    
        charset,
        shape,
        data
        )

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
     * @see org.tensorflow.op.Ops.constant
     */
    public fun <T : TType> constant(
        type: Class<T>,
        shape: Shape,
        `data`: ByteDataBuffer
    ): Constant<T> = java.constant<T>(    
        type,
        shape,
        data
        )

    /**
     * Create a constant by making an immutable copy of `tensor`. `tensor` may be closed
     *  afterwards without issue.
     *
     *  
     * Note: this endpoint cannot be simply called `constant` since it will conflict with
     *  other endpoints accepting an NdArray in parameter {e.g. [FloatNdArray)][.tensorOf]}.
     *
     * @param tensor a Tensor holding the constant value
     * @return a constant of the same data type as `tensor`
     * @see org.tensorflow.op.Ops.constantOf
     */
    public fun <T : TType> constantOf(tensor: T): Constant<T> = java.constantOf<T>(    
        tensor
        )

    /**
     * Creates a scalar of the same type as `toMatch`, with the value of `number`. `number` may be
     * truncated if it does not fit in the target type.
     *
     * @param toMatch the operand providing the target type
     * @param number the value of the tensor
     * @return a constant with the same type as `toMatch`
     * @throws IllegalArgumentException if the type is unknown (which should be impossible).
     * @see Ops.constant
     * @see org.tensorflow.op.Ops.constantOfSameType
     */
    public fun <T : TNumber> constantOfSameType(toMatch: Operand<T>, number: Number): Constant<T> =
            java.constantOfSameType<T>(    
        toMatch,
        number
        )

    /**
     * This op consumes a lock created by `MutexLock`.
     *  This op exists to consume a tensor created by `MutexLock` (other than
     *  direct control dependencies).  It should be the only that consumes the tensor,
     *  and will raise an error if it is not.  Its only purpose is to keep the
     *  mutex lock tensor alive until it is consumed by this op.
     *  
     * **NOTE**: This operation must run on the same device as its input.  This may
     *  be enforced via the `colocate_with` mechanism.
     *
     * @param mutexLock A tensor returned by `MutexLock`.
     * @return a new instance of ConsumeMutexLock
     * @see org.tensorflow.op.Ops.consumeMutexLock
     */
    public fun consumeMutexLock(mutexLock: Operand<out TType>): ConsumeMutexLock =
            java.consumeMutexLock(    
        mutexLock
        )

    /**
     * Does nothing. Serves as a control trigger for scheduling.
     *  Only useful as a placeholder for control edges.
     *
     * @return a new instance of ControlTrigger
     * @see org.tensorflow.op.Ops.controlTrigger
     */
    public fun controlTrigger(): ControlTrigger = java.controlTrigger(    
        
        )

    /**
     * Increments 'ref' until it reaches 'limit'.
     *
     * @param <T> data type for `output` output
     * @param ref Should be from a scalar `Variable` node.
     * @param limit If incrementing ref would bring it above limit, instead generates an
     *  'OutOfRange' error.
     * @param <T> data type for `CountUpTo` output and operands
     * @return a new instance of CountUpTo
     * @see org.tensorflow.op.Ops.countUpTo
     */
    public fun <T : TNumber> countUpTo(ref: Operand<T>, limit: Long): CountUpTo<T> =
            java.countUpTo<T>(    
        ref,
        limit
        )

    /**
     * The op extracts fields from a serialized protocol buffers message into tensors.
     *  The `decode_proto` op extracts fields from a serialized protocol buffers
     *  message into tensors.  The fields in `field_names` are decoded and converted
     *  to the corresponding `output_types` if possible.
     *  
     * A `message_type` name must be provided to give context for the field names.
     *  The actual message descriptor can be looked up either in the linked-in
     *  descriptor pool or a filename provided by the caller using the
     *  `descriptor_source` attribute.
     *  
     * Each output tensor is a dense tensor. This means that it is padded to hold
     *  the largest number of repeated elements seen in the input minibatch. (The
     *  shape is also padded by one to prevent zero-sized dimensions). The actual
     *  repeat counts for each example in the minibatch can be found in the `sizes`
     *  output. In many cases the output of `decode_proto` is fed immediately into
     *  tf.squeeze if missing values are not a concern. When using tf.squeeze, always
     *  pass the squeeze dimension explicitly to avoid surprises.
     *  
     * For the most part, the mapping between Proto field types and TensorFlow dtypes
     *  is straightforward. However, there are a few special cases:
     *  <ul>
     *  <li>
     *  
     * A proto field that contains a submessage or group can only be converted
     *  to `DT_STRING` (the serialized submessage). This is to reduce the complexity
     *  of the API. The resulting string can be used as input to another instance of
     *  the decode_proto op.
     *  </li>
     *  <li>
     *  
     * TensorFlow lacks support for unsigned integers. The ops represent uint64
     *  types as a `DT_INT64` with the same twos-complement bit pattern (the obvious
     *  way). Unsigned int32 values can be represented exactly by specifying type
     *  `DT_INT64`, or using twos-complement if the caller specifies `DT_INT32` in
     *  the `output_types` attribute.
     *  </li>
     *  </ul>
     *  
     * Both binary and text proto serializations are supported, and can be
     *  chosen using the `format` attribute.
     *  
     * The `descriptor_source` attribute selects the source of protocol
     *  descriptors to consult when looking up `message_type`. This may be:
     *  <ul>
     *  <li>
     *  
     * An empty string  or &quot;local://&quot;, in which case protocol descriptors are
     *  created for C++ (not Python) proto definitions linked to the binary.
     *  </li>
     *  <li>
     *  
     * A file, in which case protocol descriptors are created from the file,
     *  which is expected to contain a `FileDescriptorSet` serialized as a string.
     *  NOTE: You can build a `descriptor_source` file using the `--descriptor_set_out`
     *  and `--include_imports` options to the protocol compiler `protoc`.
     *  </li>
     *  <li>
     *  
     * A &quot;bytes://<bytes>&quot;, in which protocol descriptors are created from `<bytes>`,
     *  which is expected to be a `FileDescriptorSet` serialized as a string.
     *  </li>
     *  </ul>
     *
     * @param bytes Tensor of serialized protos with shape `batch_shape`.
     * @param messageType Name of the proto message type to decode.
     * @param fieldNames List of strings containing proto field names. An extension field can be
     * decoded
     *  by using its full name, e.g. EXT_PACKAGE.EXT_FIELD_NAME.
     * @param outputTypes List of TF types to use for the respective field in field_names.
     * @param options carries optional attribute values
     * @return a new instance of DecodeProto
     * @see org.tensorflow.op.Ops.decodeProto
     * @param descriptorSource Sets the descriptorSource option.
     *
     * @param descriptorSource Either the special value `local://` or a path to a file containing
     *  a serialized `FileDescriptorSet`.
     * @return this Options instance.
     * @param messageFormat Sets the messageFormat option.
     *
     * @param messageFormat Either `binary` or `text`.
     * @return this Options instance.
     * @param sanitize Sets the sanitize option.
     *
     * @param sanitize Whether to sanitize the result or not.
     * @return this Options instance.
     */
    public fun decodeProto(
        bytes: Operand<TString>,
        messageType: String,
        fieldNames: List<String>,
        outputTypes: List<Class<out TType>>,
        descriptorSource: String? = null,
        messageFormat: String? = null,
        sanitize: Boolean? = null
    ): DecodeProto = java.decodeProto(    
        bytes,
        messageType,
        fieldNames,
        outputTypes,
        *listOfNotNull(
            descriptorSource?.let{ org.tensorflow.op.core.DecodeProto.descriptorSource(it) },
            messageFormat?.let{ org.tensorflow.op.core.DecodeProto.messageFormat(it) },
            sanitize?.let{ org.tensorflow.op.core.DecodeProto.sanitize(it) }
        ).toTypedArray()
        )

    /**
     * Makes a copy of `x`.
     *
     * @param <T> data type for `y` output
     * @param x The source tensor of type `T`.
     * @param <T> data type for `DeepCopy` output and operands
     * @return a new instance of DeepCopy
     * @see org.tensorflow.op.Ops.deepCopy
     */
    public fun <T : TType> deepCopy(x: Operand<T>): DeepCopy<T> = java.deepCopy<T>(    
        x
        )

    /**
     * Delete the tensor specified by its handle in the session.
     *
     * @param handle The handle for a tensor stored in the session state.
     * @return a new instance of DeleteSessionTensor
     * @see org.tensorflow.op.Ops.deleteSessionTensor
     */
    public fun deleteSessionTensor(handle: Operand<TString>): DeleteSessionTensor =
            java.deleteSessionTensor(    
        handle
        )

    /**
     * Deletes the resource specified by the handle.
     *  All subsequent operations using the resource will result in a NotFound
     *  error status.
     *
     * @param resource handle to the resource to delete.
     * @param options carries optional attribute values
     * @return a new instance of DestroyResourceOp
     * @see org.tensorflow.op.Ops.destroyResourceOp
     * @param ignoreLookupError Sets the ignoreLookupError option.
     *
     * @param ignoreLookupError whether to ignore the error when the resource
     *  doesn't exist.
     * @return this Options instance.
     */
    public fun destroyResourceOp(resource: Operand<out TType>, ignoreLookupError: Boolean? = null):
            DestroyResourceOp = java.destroyResourceOp(    
        resource,
        *listOfNotNull(
            ignoreLookupError?.let{ org.tensorflow.op.core.DestroyResourceOp.ignoreLookupError(it) }
        ).toTypedArray()
        )

    /**
     * Destroys the temporary variable and returns its final value.
     *  Sets output to the value of the Tensor pointed to by 'ref', then destroys
     *  the temporary variable called 'var_name'.
     *  All other uses of 'ref' _must_ have executed before this op.
     *  This is typically achieved by chaining the ref through each assign op, or by
     *  using control dependencies.
     *  
     * Outputs the final value of the tensor pointed to by 'ref'.
     *
     * @param <T> data type for `value` output
     * @param ref A reference to the temporary variable tensor.
     * @param varName Name of the temporary variable, usually the name of the matching
     *  'TemporaryVariable' op.
     * @param <T> data type for `DestroyTemporaryVariable` output and operands
     * @return a new instance of DestroyTemporaryVariable
     * @see org.tensorflow.op.Ops.destroyTemporaryVariable
     */
    public fun <T : TType> destroyTemporaryVariable(ref: Operand<T>, varName: String):
            DestroyTemporaryVariable<T> = java.destroyTemporaryVariable<T>(    
        ref,
        varName
        )

    /**
     * Partitions `data` into `num_partitions` tensors using indices from `partitions`.
     *  For each index tuple `js` of size `partitions.ndim`, the slice `data&#91;js, ...&#93;`
     *  becomes part of `outputs&#91;partitions[js&#93;]`.  The slices with `partitions&#91;js&#93;
     * = i`
     *  are placed in `outputs[i]` in lexicographic order of `js`, and the first
     *  dimension of `outputs[i]` is the number of entries in `partitions` equal to `i`.
     *  In detail,
     *  ```
     * outputs[i].shape = [sum(partitions == i)] + data.shape[partitions.ndim:]
     *
     *      outputs[i] = pack([data[js, ...] for js if partitions[js] == i])
     *  
     * ```
     *  
     * `data.shape` must start with `partitions.shape`.
     *  
     * For example:
     *  ```
     * # Scalar partitions.
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
     *  
     * ```
     *  
     * See `dynamic_stitch` for an example on how to merge partitions back.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/DynamicPartition.png" alt>
     *  </div>
     *
     * @param <T> data type for `outputs` output
     * @param data The data value
     * @param partitions Any shape.  Indices in the range `[0, num_partitions)`.
     * @param numPartitions The number of partitions to output.
     * @param <T> data type for `DynamicPartition` output and operands
     * @return a new instance of DynamicPartition
     * @see org.tensorflow.op.Ops.dynamicPartition
     */
    public fun <T : TType> dynamicPartition(
        `data`: Operand<T>,
        partitions: Operand<TInt32>,
        numPartitions: Long
    ): DynamicPartition<T> = java.dynamicPartition<T>(    
        data,
        partitions,
        numPartitions
        )

    /**
     * Interleave the values from the `data` tensors into a single tensor.
     *  Builds a merged tensor such that
     *  ```
     * merged[indices[m][i, ..., j], ...] = data[m][i, ..., j, ...]
     *  
     * ```
     *  
     * For example, if each `indices[m]` is scalar or vector, we have
     *  ```
     * # Scalar indices:
     *      merged[indices[m], ...] = data[m][...]
     *
     *      # Vector indices:
     *      merged[indices[m][i], ...] = data[m][i, ...]
     *  
     * ```
     *  
     * Each `data[i].shape` must start with the corresponding `indices[i].shape`,
     *  and the rest of `data[i].shape` must be constant w.r.t. `i`.  That is, we
     *  must have `data[i].shape = indices[i].shape + constant`.  In terms of this
     *  `constant`, the output shape is
     *  ```
     * merged.shape = [max(indices)] + constant
     *  
     * ```
     *  
     * Values are merged in order, so if an index appears in both `indices[m][i]` and
     *  `indices[n][j]` for `(m,i) < (n,j)` the slice `data[n][j]` will appear in the
     *  merged result. If you do not need this guarantee, ParallelDynamicStitch might
     *  perform better on some devices.
     *  
     * For example:
     *  ```
     * indices[0] = 6
     *      indices[1] = [4, 1]
     *      indices[2] = [[5, 2], [0, 3]]
     *      data[0] = [61, 62]
     *      data[1] = [[41, 42], [11, 12]]
     *      data[2] = [[[51, 52], [21, 22]], [[1, 2], [31, 32]]]
     *      merged = [[1, 2], [11, 12], [21, 22], [31, 32], [41, 42],
     *                [51, 52], [61, 62]]
     *  
     * ```
     *  
     * This method can be used to merge partitions created by `dynamic_partition`
     *  as illustrated on the following example:
     *  ```
     * # Apply function (increments x_i) on elements for which a certain condition
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
     *  
     * ```
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/DynamicStitch.png" alt>
     *  </div>
     *
     * @param <T> data type for `merged` output
     * @param indices The indices value
     * @param data The data value
     * @param <T> data type for `DynamicStitch` output and operands
     * @return a new instance of DynamicStitch
     * @see org.tensorflow.op.Ops.dynamicStitch
     */
    public fun <T : TType> dynamicStitch(indices: Iterable<Operand<TInt32>>,
            `data`: Iterable<Operand<T>>): DynamicStitch<T> = java.dynamicStitch<T>(    
        indices,
        data
        )

    /**
     * Computes the (possibly normalized) Levenshtein Edit Distance.
     *  The inputs are variable-length sequences provided by SparseTensors
     *  (hypothesis_indices, hypothesis_values, hypothesis_shape)
     *  and
     *  (truth_indices, truth_values, truth_shape).
     *  
     * The inputs are:
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
     * @param <T> data type for `EditDistance` output and operands
     * @return a new instance of EditDistance
     * @see org.tensorflow.op.Ops.editDistance
     * @param normalize Sets the normalize option.
     *
     * @param normalize boolean (if true, edit distances are normalized by length of truth).
     *  
     * The output is:
     * @return this Options instance.
     */
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

    /**
     * Creates a tensor with the given shape.
     *  
     * This operation creates a tensor of `shape` and `dtype`.
     *
     * @param <T> data type for `output` output
     * @param shape 1-D. Represents the shape of the output tensor.
     * @param dtype The value of the dtype attribute
     * @param options carries optional attribute values
     * @param <T> data type for `Empty` output and operands
     * @return a new instance of Empty
     * @see org.tensorflow.op.Ops.empty
     * @param init Sets the init option.
     *
     * @param init If True, initialize the returned tensor with the default value of dtype. 
     * Otherwise, the implementation is free not to initializethe tensor's content.
     * @return this Options instance.
     */
    public fun <T : TType> empty(
        shape: Operand<TInt32>,
        dtype: Class<T>,
        `init`: Boolean? = null
    ): Empty<T> = java.empty<T>(    
        shape,
        dtype,
        *listOfNotNull(
            init?.let{ org.tensorflow.op.core.Empty.init(it) }
        ).toTypedArray()
        )

    /**
     * Creates and returns an empty tensor list.
     *  All list elements must be tensors of dtype element_dtype and shape compatible
     *  with element_shape.
     *  
     * handle: an empty tensor list.
     *  element_dtype: the type of elements in the list.
     *  element_shape: a shape compatible with that of elements in the list.
     *
     * @param elementShape The elementShape value
     * @param maxNumElements The maxNumElements value
     * @param elementDtype The value of the elementDtype attribute
     * @param <U> data type for `EmptyTensorList` output and operands
     * @return a new instance of EmptyTensorList
     * @see org.tensorflow.op.Ops.emptyTensorList
     */
    public fun <U : TType> emptyTensorList(
        elementShape: Operand<out TNumber>,
        maxNumElements: Operand<TInt32>,
        elementDtype: Class<U>
    ): EmptyTensorList = java.emptyTensorList<U>(    
        elementShape,
        maxNumElements,
        elementDtype
        )

    /**
     * Creates and returns an empty tensor map.
     *  handle: an empty tensor map
     *
     * @return a new instance of EmptyTensorMap
     * @see org.tensorflow.op.Ops.emptyTensorMap
     */
    public fun emptyTensorMap(): EmptyTensorMap = java.emptyTensorMap(    
        
        )

    /**
     * The op serializes protobuf messages provided in the input tensors.
     *  The types of the tensors in `values` must match the schema for the fields
     *  specified in `field_names`. All the tensors in `values` must have a common
     *  shape prefix, _batch_shape_.
     *  
     * The `sizes` tensor specifies repeat counts for each field.  The repeat count
     *  (last dimension) of a each tensor in `values` must be greater than or equal
     *  to corresponding repeat count in `sizes`.
     *  
     * A `message_type` name must be provided to give context for the field names.
     *  The actual message descriptor can be looked up either in the linked-in
     *  descriptor pool or a filename provided by the caller using the
     *  `descriptor_source` attribute.
     *  
     * For the most part, the mapping between Proto field types and TensorFlow dtypes
     *  is straightforward. However, there are a few special cases:
     *  <ul>
     *  <li>
     *  
     * A proto field that contains a submessage or group can only be converted
     *  to `DT_STRING` (the serialized submessage). This is to reduce the complexity
     *  of the API. The resulting string can be used as input to another instance of
     *  the decode_proto op.
     *  </li>
     *  <li>
     *  
     * TensorFlow lacks support for unsigned integers. The ops represent uint64
     *  types as a `DT_INT64` with the same twos-complement bit pattern (the obvious
     *  way). Unsigned int32 values can be represented exactly by specifying type
     *  `DT_INT64`, or using twos-complement if the caller specifies `DT_INT32` in
     *  the `output_types` attribute.
     *  </li>
     *  </ul>
     *  
     * The `descriptor_source` attribute selects the source of protocol
     *  descriptors to consult when looking up `message_type`. This may be:
     *  <ul>
     *  <li>
     *  
     * An empty string  or &quot;local://&quot;, in which case protocol descriptors are
     *  created for C++ (not Python) proto definitions linked to the binary.
     *  </li>
     *  <li>
     *  
     * A file, in which case protocol descriptors are created from the file,
     *  which is expected to contain a `FileDescriptorSet` serialized as a string.
     *  NOTE: You can build a `descriptor_source` file using the `--descriptor_set_out`
     *  and `--include_imports` options to the protocol compiler `protoc`.
     *  </li>
     *  <li>
     *  
     * A &quot;bytes://<bytes>&quot;, in which protocol descriptors are created from `<bytes>`,
     *  which is expected to be a `FileDescriptorSet` serialized as a string.
     *  </li>
     *  </ul>
     *
     * @param sizes Tensor of int32 with shape `&#91;batch_shape, len(field_names)&#93;`.
     * @param values List of tensors containing values for the corresponding field.
     * @param fieldNames List of strings containing proto field names.
     * @param messageType Name of the proto message type to decode.
     * @param options carries optional attribute values
     * @return a new instance of EncodeProto
     * @see org.tensorflow.op.Ops.encodeProto
     * @param descriptorSource Sets the descriptorSource option.
     *
     * @param descriptorSource the descriptorSource option
     * @return this Options instance.
     */
    public fun encodeProto(
        sizes: Operand<TInt32>,
        values: Iterable<Operand<*>>,
        fieldNames: List<String>,
        messageType: String,
        descriptorSource: String? = null
    ): EncodeProto = java.encodeProto(    
        sizes,
        values,
        fieldNames,
        messageType,
        *listOfNotNull(
            descriptorSource?.let{ org.tensorflow.op.core.EncodeProto.descriptorSource(it) }
        ).toTypedArray()
        )

    /**
     * Ensures that the tensor's shape matches the expected shape.
     *  Raises an error if the input tensor's shape does not match the specified shape.
     *  Returns the input tensor otherwise.
     *
     * @param <T> data type for `output` output
     * @param input A tensor, whose shape is to be validated.
     * @param shape The expected (possibly partially specified) shape of the input tensor.
     * @param <T> data type for `EnsureShape` output and operands
     * @return a new instance of EnsureShape
     * @see org.tensorflow.op.Ops.ensureShape
     */
    public fun <T : TType> ensureShape(input: Operand<T>, shape: Shape): EnsureShape<T> =
            java.ensureShape<T>(    
        input,
        shape
        )

    /**
     * Inserts a dimension of 1 into a tensor's shape.
     *  Given a tensor `input`, this operation inserts a dimension of 1 at the
     *  dimension index `axis` of `input`'s shape. The dimension index `axis` starts at
     *  zero; if you specify a negative number for `axis` it is counted backward from
     *  the end.
     *  
     * This operation is useful if you want to add a batch dimension to a single
     *  element. For example, if you have a single image of shape `&#91;height, width,
     * channels&#93;`, you can make it a batch of 1 image with `expand_dims(image, 0)`,
     *  which will make the shape `&#91;1, height, width, channels&#93;`.
     *  
     * Other examples:
     *  ```
     * # 't' is a tensor of shape [2]
     *  shape(expand_dims(t, 0)) ==> [1, 2]
     *  shape(expand_dims(t, 1)) ==> [2, 1]
     *  shape(expand_dims(t, -1)) ==> [2, 1]
     *
     *  # 't2' is a tensor of shape [2, 3, 5]
     *  shape(expand_dims(t2, 0)) ==> [1, 2, 3, 5]
     *  shape(expand_dims(t2, 2)) ==> [2, 3, 1, 5]
     *  shape(expand_dims(t2, 3)) ==> [2, 3, 5, 1]
     *  
     * ```
     *  
     * This operation requires that:
     *  
     * `-1-input.dims() <= dim <= input.dims()`
     *  
     * This operation is related to `squeeze()`, which removes dimensions of
     *  size 1.
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param axis 0-D (scalar). Specifies the dimension index at which to
     *  expand the shape of `input`. Must be in the range
     *  `&#91;-rank(input) - 1, rank(input)&#93;`.
     * @param <T> data type for `ExpandDims` output and operands
     * @return a new instance of ExpandDims
     * @see org.tensorflow.op.Ops.expandDims
     */
    public fun <T : TType> expandDims(input: Operand<T>, axis: Operand<out TNumber>): ExpandDims<T>
            = java.expandDims<T>(    
        input,
        axis
        )

    /**
     * Extract `patches` from `input` and put them in the `"depth"` output dimension. 3D extension
     * of `extract_image_patches`.
     *
     * @param <T> data type for `patches` output
     * @param input 5-D Tensor with shape `&#91;batch, in_planes, in_rows, in_cols, depth&#93;`.
     * @param ksizes The size of the sliding window for each dimension of `input`.
     * @param strides 1-D of length 5. How far the centers of two consecutive patches are in
     *  `input`. Must be: `&#91;1, stride_planes, stride_rows, stride_cols, 1&#93;`.
     * @param padding The type of padding algorithm to use.
     *  
     * The size-related attributes are specified as follows:
     *  `
     * ksizes = [1, ksize_planes, ksize_rows, ksize_cols, 1]
     *  strides = [1, stride_planes, strides_rows, strides_cols, 1]
     *  
     * `
     * @param <T> data type for `ExtractVolumePatches` output and operands
     * @return a new instance of ExtractVolumePatches
     * @see org.tensorflow.op.Ops.extractVolumePatches
     */
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

    /**
     * Creates a tensor filled with a scalar value.
     *  This operation creates a tensor of shape `dims` and fills it with `value`.
     *  
     * For example:
     *  ```
     * # Output tensor has shape [2, 3].
     *  fill([2, 3], 9) ==> [[9, 9, 9]
     *                       [9, 9, 9]]
     *  
     * ```
     *  
     * `tf.fill` differs from `tf.constant` in a few ways:
     *  <ul>
     *  <li>`tf.fill` only supports scalar contents, whereas `tf.constant` supports
     *  Tensor values.</li>
     *  <li>`tf.fill` creates an Op in the computation graph that constructs the actual
     *  Tensor value at runtime. This is in contrast to `tf.constant` which embeds
     *  the entire Tensor into the graph with a `Const` node.</li>
     *  <li>Because `tf.fill` evaluates at graph runtime, it supports dynamic shapes
     *  based on other runtime Tensors, unlike `tf.constant`.</li>
     *  </ul>
     *
     * @param <U> data type for `output` output
     * @param dims 1-D. Represents the shape of the output tensor.
     * @param value 0-D (scalar). Value to fill the returned tensor.
     *  
     * `@`compatibility(numpy)
     *
     *  Equivalent to np.full
     *  
     * `@`end_compatibility
     * @param <U> data type for `Fill` output and operands
     * @return a new instance of Fill
     * @see org.tensorflow.op.Ops.fill
     */
    public fun <U : TType> fill(dims: Operand<out TNumber>, value: Operand<U>): Fill<U> =
            java.fill<U>(    
        dims,
        value
        )

    /**
     * Generates fingerprint values.
     *  Generates fingerprint values of `data`.
     *  
     * Fingerprint op considers the first dimension of `data` as the batch dimension,
     *  and `output[i]` contains the fingerprint value generated from contents in
     *  `data&#91;i, ...&#93;` for all `i`.
     *  
     * Fingerprint op writes fingerprint values as byte arrays. For example, the
     *  default method `farmhash64` generates a 64-bit fingerprint value at a time.
     *  This 8-byte value is written out as an `uint8` array of size 8, in little-endian
     *  order.
     *  
     * For example, suppose that `data` has data type `DT_INT32` and shape (2, 3, 4),
     *  and that the fingerprint method is `farmhash64`. In this case, the output shape
     *  is (2, 8), where 2 is the batch dimension size of `data`, and 8 is the size of
     *  each fingerprint value in bytes. `output&#91;0, :&#93;` is generated from 12 integers in
     *  `data&#91;0, :, :&#93;` and similarly `output&#91;1, :&#93;` is generated from other 12
     * integers
     *  in `data&#91;1, :, :&#93;`.
     *  
     * Note that this op fingerprints the raw underlying buffer, and it does not
     *  fingerprint Tensor's metadata such as data type and/or shape. For example, the
     *  fingerprint values are invariant under reshapes and bitcasts as long as the
     *  batch dimension remain the same:
     *  ```
     * Fingerprint(data) == Fingerprint(Reshape(data, ...))
     *  Fingerprint(data) == Fingerprint(Bitcast(data, ...))
     *  
     * ```
     *  
     * For string data, one should expect `Fingerprint(data) != Fingerprint(ReduceJoin(data))` in
     * general.
     *
     * @param data Must have rank 1 or higher.
     * @param method Fingerprint method used by this op. Currently available method is
     *  `farmhash::fingerprint64`.
     * @return a new instance of Fingerprint
     * @see org.tensorflow.op.Ops.fingerprint
     */
    public fun fingerprint(`data`: Operand<out TType>, method: Operand<TString>): Fingerprint =
            java.fingerprint(    
        data,
        method
        )

    /**
     * ```
     * output = input;
     *   for i in range(start, limit, delta)
     *     output = body(i, output);
     *  
     * ```
     *
     * @param start The lower bound. An int32
     * @param limit The upper bound. An int32
     * @param delta The increment. An int32
     * @param input A list of input tensors whose types are T.
     * @param body `
     * A function that takes a list of tensors (int32, T) and returns another
     *  list of tensors (T).
     *  
     * `
     * @return a new instance of For
     * @see org.tensorflow.op.Ops.forOp
     */
    public fun forOp(
        start: Operand<TInt32>,
        limit: Operand<TInt32>,
        delta: Operand<TInt32>,
        input: Iterable<Operand<*>>,
        body: ConcreteFunction
    ): For = java.forOp(    
        start,
        limit,
        delta,
        input,
        body
        )

    /**
     * Gather slices from `params` axis `axis` according to `indices`.
     *  `indices` must be an integer tensor of any dimension (usually 0-D or 1-D).
     *  Produces an output tensor with shape `params.shape&#91;:axis&#93; +
     * indices.shape&#91;batch_dims:&#93; + params.shape&#91;axis + 1:&#93;` where:
     *  ```
     * # Scalar indices (output is rank(params) - 1).
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
     *  
     * ```
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/Gather.png" alt>
     *  </div>
     *  
     * Note that on CPU, if an out of bound index is found, an error is returned.
     *  On GPU, if an out of bound index is found, a 0 is stored in the
     *  corresponding output value.
     *  
     * See also `tf.batch_gather` and `tf.gather_nd`.
     *
     * @param <T> data type for `output` output
     * @param params The tensor from which to gather values. Must be at least rank
     *  `axis + 1`.
     * @param indices Index tensor. Must be in range `&#91;0, params.shape[axis&#93;)`.
     * @param axis The axis in `params` to gather `indices` from. Defaults to the first
     *  dimension. Supports negative indexes.
     * @param options carries optional attribute values
     * @param <T> data type for `GatherV2` output and operands
     * @return a new instance of Gather
     * @see org.tensorflow.op.Ops.gather
     * @param batchDims Sets the batchDims option.
     *
     * @param batchDims the batchDims option
     * @return this Options instance.
     */
    public fun <T : TType> gather(
        params: Operand<T>,
        indices: Operand<out TNumber>,
        axis: Operand<out TNumber>,
        batchDims: Long? = null
    ): Gather<T> = java.gather<T>(    
        params,
        indices,
        axis,
        *listOfNotNull(
            batchDims?.let{ org.tensorflow.op.core.Gather.batchDims(it) }
        ).toTypedArray()
        )

    /**
     * Gather slices from `params` into a Tensor with shape specified by `indices`.
     *  `indices` is a K-dimensional integer tensor, best thought of as a
     *  (K-1)-dimensional tensor of indices into `params`, where each element defines a
     *  slice of `params`:
     *  `output[\`\(i_0, ..., i_{K-2`\\)`] = params&#91;indices[\`\(i_0, ..., i_{K-2}\\)`&#93;]
     *  }
     *  
     * Whereas in `tf.gather` `indices` defines slices into the `axis`
     *  dimension of `params`, in `tf.gather_nd`, `indices` defines slices into the
     *  first `N` dimensions of `params`, where `N = indices.shape&#91;-1&#93;`.
     *  
     * The last dimension of `indices` can be at most the rank of
     *  `params`:
     *  ```
     * indices.shape[-1] <= params.rank
     *  
     * ```
     *  
     * The last dimension of `indices` corresponds to elements
     *  (if `indices.shape&#91;-1&#93; == params.rank`) or slices
     *  (if `indices.shape&#91;-1&#93; < params.rank`) along dimension `indices.shape&#91;-1&#93;`
     *  of `params`.  The output tensor has shape
     *  ```
     * indices.shape[:-1] + params.shape[indices.shape[-1]:]
     *  
     * ```
     *  
     * Note that on CPU, if an out of bound index is found, an error is returned.
     *  On GPU, if an out of bound index is found, a 0 is stored in the
     *  corresponding output value.
     *  
     * Some examples below.
     *  
     * Simple indexing into a matrix:
     *  ```
     * indices = [[0, 0], [1, 1]]
     *      params = [['a', 'b'], ['c', 'd']]
     *      output = ['a', 'd']
     *  
     * ```
     *  
     * Slice indexing into a matrix:
     *  ```
     * indices = [[1], [0]]
     *      params = [['a', 'b'], ['c', 'd']]
     *      output = [['c', 'd'], ['a', 'b']]
     *  
     * ```
     *  
     * Indexing into a 3-tensor:
     *  ```
     * indices = [[1]]
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
     *  
     * ```
     *  
     * Batched indexing into a matrix:
     *  ```
     * indices = [[[0, 0]], [[0, 1]]]
     *      params = [['a', 'b'], ['c', 'd']]
     *      output = [['a'], ['b']]
     *  
     * ```
     *  
     * Batched slice indexing into a matrix:
     *  ```
     * indices = [[[1]], [[0]]]
     *      params = [['a', 'b'], ['c', 'd']]
     *      output = [[['c', 'd']], [['a', 'b']]]
     *  
     * ```
     *  
     * Batched indexing into a 3-tensor:
     *  ```
     * indices = [[[1]], [[0]]]
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
     *  
     * ```
     *  
     * See also `tf.gather` and `tf.batch_gather`.
     *
     * @param <T> data type for `output` output
     * @param params The tensor from which to gather values.
     * @param indices Index tensor.
     * @param <T> data type for `GatherNd` output and operands
     * @return a new instance of GatherNd
     * @see org.tensorflow.op.Ops.gatherNd
     */
    public fun <T : TType> gatherNd(params: Operand<T>, indices: Operand<out TNumber>): GatherNd<T>
            = java.gatherNd<T>(    
        params,
        indices
        )

    /**
     * Store the input tensor in the state of the current session.
     *
     * @param value The tensor to be stored.
     * @return a new instance of GetSessionHandle
     * @see org.tensorflow.op.Ops.getSessionHandle
     */
    public fun getSessionHandle(value: Operand<out TType>): GetSessionHandle =
            java.getSessionHandle(    
        value
        )

    /**
     * Get the value of the tensor specified by its handle.
     *
     * @param <T> data type for `value` output
     * @param handle The handle for a tensor stored in the session state.
     * @param dtype The type of the output value.
     * @param <T> data type for `GetSessionTensor` output and operands
     * @return a new instance of GetSessionTensor
     * @see org.tensorflow.op.Ops.getSessionTensor
     */
    public fun <T : TType> getSessionTensor(handle: Operand<TString>, dtype: Class<T>):
            GetSessionTensor<T> = java.getSessionTensor<T>(    
        handle,
        dtype
        )

    /**
     * Adds gradients computation ops to the graph according to scope.
     *
     * @param y outputs of the function to derive
     * @param x inputs of the function for which partial derivatives are computed
     * @param options carries optional attributes values
     * @return a new instance of `Gradients`
     * @throws IllegalArgumentException if execution environment is not a graph
     * @see org.tensorflow.op.Ops.gradients
     * @param dx 
     *
     * @param dx partial derivatives of some loss function `L` w.r.t. `y`
     * @return this option builder
     */
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

    /**
     * Adds operations to compute the partial derivatives of sum of `y`s w.r.t `x`s,
     *  i.e., `d(y_1 + y_2 + ...)/dx_1, d(y_1 + y_2 + ...)/dx_2...`
     *  
     *  
     *  If `Options.dx()` values are set, they are as the initial symbolic partial derivatives of
     * some loss 
     *  function `L` w.r.t. `y`. `Options.dx()` must have the size of `y`.
     *  
     *
     *  If `Options.dx()` is not set, the implementation will use dx of `OnesLike` for all
     *  shapes in `y`.
     *  
     *
     *  The partial derivatives are returned in output `dy`, with the size of `x`.
     *  
     *
     *  Example of usage:
     *  ```
     * {@code
     *  Gradients gradients = tf.gradients(loss, Arrays.asList(w, b));
     *  Constant<TFloat32> alpha = tf.constant(1.0f);
     *  tf.train.applyGradientDescent(w, alpha, gradients.<Float>dy(0));
     *  tf.train.applyGradientDescent(b, alpha, gradients.<Float>dy(1));
     *  
     * ```}
     *
     * @param y output of the function to derive
     * @param x inputs of the function for which partial derivatives are computed
     * @param options carries optional attributes values
     * @return a new instance of `Gradients`
     * @throws IllegalArgumentException if execution environment is not a graph
     * @see org.tensorflow.op.Ops.gradients
     * @param dx 
     *
     * @param dx partial derivatives of some loss function `L` w.r.t. `y`
     * @return this option builder
     */
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

    /**
     * Gives a guarantee to the TF runtime that the input tensor is a constant.
     *  The runtime is then free to make optimizations based on this.
     *  
     * Only accepts value typed tensors as inputs and rejects resource variable handles
     *  as input.
     *  
     * Returns the input tensor without modification.
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param <T> data type for `GuaranteeConst` output and operands
     * @return a new instance of GuaranteeConst
     * @see org.tensorflow.op.Ops.guaranteeConst
     */
    public fun <T : TType> guaranteeConst(input: Operand<T>): GuaranteeConst<T> =
            java.guaranteeConst<T>(    
        input
        )

    /**
     * Creates a non-initialized hash table.
     *  This op creates a hash table, specifying the type of its keys and values.
     *  Before using the table you will have to initialize it.  After initialization the
     *  table will be immutable.
     *
     * @param keyDtype Type of the table keys.
     * @param valueDtype Type of the table values.
     * @param options carries optional attribute values
     * @param <T> data type for `HashTableV2` output and operands
     * @param <U> data type for `HashTableV2` output and operands
     * @return a new instance of HashTable
     * @see org.tensorflow.op.Ops.hashTable
     * @param container Sets the container option.
     *
     * @param container If non-empty, this table is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this table is shared under the given name across
     *  multiple sessions.
     * @return this Options instance.
     * @param useNodeNameSharing Sets the useNodeNameSharing option.
     *
     * @param useNodeNameSharing If true and shared_name is empty, the table is shared
     *  using the node name.
     * @return this Options instance.
     */
    public fun <T : TType, U : TType> hashTable(
        keyDtype: Class<T>,
        valueDtype: Class<U>,
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

    /**
     * Return histogram of values.
     *  Given the tensor `values`, this operation returns a rank 1 histogram counting
     *  the number of entries in `values` that fall into every bin.  The bins are
     *  equal width and determined by the arguments `value_range` and `nbins`.
     *  ```
     * # Bins will be:  (-inf, 1), [1, 2), [2, 3), [3, 4), [4, inf)
     *  nbins = 5
     *  value_range = [0.0, 5.0]
     *  new_values = [-1.0, 0.0, 1.5, 2.0, 5.0, 15]
     *
     *  with tf.get_default_session() as sess:
     *    hist = tf.histogram_fixed_width(new_values, value_range, nbins=5)
     *    variables.global_variables_initializer().run()
     *    sess.run(hist) => [2, 1, 1, 0, 2]
     *  
     * ```
     *
     * @param <U> data type for `out` output
     * @param values Numeric `Tensor`.
     * @param valueRange Shape [2] `Tensor` of same `dtype` as `values`.
     *  values <= value_range[0] will be mapped to hist[0],
     *  values >= value_range[1] will be mapped to hist&#91;-1&#93;.
     * @param nbins Scalar `int32 Tensor`.  Number of histogram bins.
     * @param <T> data type for `HistogramFixedWidth` output and operands
     * @return a new instance of HistogramFixedWidth, with default output types
     * @see org.tensorflow.op.Ops.histogramFixedWidth
     */
    public fun <T : TNumber> histogramFixedWidth(
        values: Operand<T>,
        valueRange: Operand<T>,
        nbins: Operand<TInt32>
    ): HistogramFixedWidth<TInt32> = java.histogramFixedWidth<T>(    
        values,
        valueRange,
        nbins
        )

    /**
     * Return histogram of values.
     *  Given the tensor `values`, this operation returns a rank 1 histogram counting
     *  the number of entries in `values` that fall into every bin.  The bins are
     *  equal width and determined by the arguments `value_range` and `nbins`.
     *  ```
     * # Bins will be:  (-inf, 1), [1, 2), [2, 3), [3, 4), [4, inf)
     *  nbins = 5
     *  value_range = [0.0, 5.0]
     *  new_values = [-1.0, 0.0, 1.5, 2.0, 5.0, 15]
     *
     *  with tf.get_default_session() as sess:
     *    hist = tf.histogram_fixed_width(new_values, value_range, nbins=5)
     *    variables.global_variables_initializer().run()
     *    sess.run(hist) => [2, 1, 1, 0, 2]
     *  
     * ```
     *
     * @param <U> data type for `out` output
     * @param values Numeric `Tensor`.
     * @param valueRange Shape [2] `Tensor` of same `dtype` as `values`.
     *  values <= value_range[0] will be mapped to hist[0],
     *  values >= value_range[1] will be mapped to hist&#91;-1&#93;.
     * @param nbins Scalar `int32 Tensor`.  Number of histogram bins.
     * @param dtype The value of the dtype attribute
     * @param <U> data type for `HistogramFixedWidth` output and operands
     * @param <T> data type for `HistogramFixedWidth` output and operands
     * @return a new instance of HistogramFixedWidth
     * @see org.tensorflow.op.Ops.histogramFixedWidth
     */
    public fun <U : TNumber, T : TNumber> histogramFixedWidth(
        values: Operand<T>,
        valueRange: Operand<T>,
        nbins: Operand<TInt32>,
        dtype: Class<U>
    ): HistogramFixedWidth<U> = java.histogramFixedWidth<U, T>(    
        values,
        valueRange,
        nbins,
        dtype
        )

    /**
     * Return a tensor with the same shape and contents as the input tensor or value.
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param <T> data type for `Identity` output and operands
     * @return a new instance of Identity
     * @see org.tensorflow.op.Ops.identity
     */
    public fun <T : TType> identity(input: Operand<T>): Identity<T> = java.identity<T>(    
        input
        )

    /**
     * Returns a list of tensors with the same shapes and contents as the input
     *  tensors.
     *  
     * This op can be used to override the gradient for complicated functions. For
     *  example, suppose y = f(x) and we wish to apply a custom function g for backprop
     *  such that dx = g(dy). In Python,
     *  ```
     * with tf.get_default_graph().gradient_override_map(
     *      {'IdentityN': 'OverrideGradientWithG'
     * ```):
     *    y, _ = identity_n(&#91;f(x), x&#93;)
     *
     *  `@`tf.RegisterGradient('OverrideGradientWithG')
     *  def ApplyG(op, dy, _):
     *    return &#91;None, g(dy)&#93;  # Do not backprop to f(x).
     *  }
     *
     * @param input The input value
     * @return a new instance of IdentityN
     * @see org.tensorflow.op.Ops.identityN
     */
    public fun identityN(input: Iterable<Operand<*>>): IdentityN = java.identityN(    
        input
        )

    /**
     * output = cond ? then_branch(input) : else_branch(input)
     *
     *  
     * Selects between [StatefulIf] and [StatelessIf] based on the statefulness of the function
     * arguments.
     *
     * @param cond `
     * A Tensor. If the tensor is a scalar of non-boolean type, the
     *    scalar is converted to a boolean according to the
     *    following rule: if the scalar is a numerical value, non-zero means
     *    `True` and zero means False; if the scalar is a string, non-empty
     *    means `True` and empty means `False`. If the tensor is not a scalar,
     *    being empty means False and being non-empty means True.
     *  
     * `
     * @param input A list of input tensors.
     * @param Tout A list of output types.
     * @param thenBranch `
     * A function that takes 'inputs' and returns a list of tensors, whose
     *    types are the same as what else_branch returns.
     *  
     * `
     * @param elseBranch `
     * A function that takes 'inputs' and returns a list of tensors, whose
     *  types are the same as what then_branch returns.
     *  
     * `
     * @param options carries optional attribute values
     * @return a new instance of If
     * @see org.tensorflow.op.Ops.ifOp
     * @param outputShapes Sets the outputShapes option.
     *
     * @param outputShapes the outputShapes option
     * @return this Options instance.
     */
    public fun ifOp(
        cond: Operand<out TType>,
        input: Iterable<Operand<*>>,
        Tout: List<Class<out TType>>,
        thenBranch: ConcreteFunction,
        elseBranch: ConcreteFunction,
        outputShapes: List<Shape>? = null
    ): If = java.ifOp(    
        cond,
        input,
        Tout,
        thenBranch,
        elseBranch,
        *listOfNotNull(
            outputShapes?.let{ org.tensorflow.op.core.If.outputShapes(it) }
        ).toTypedArray()
        )

    /**
     * Returns immutable tensor from memory region.
     *  The current implementation memmaps the tensor from a file.
     *
     * @param <T> data type for `tensor` output
     * @param dtype Type of the returned tensor.
     * @param shape Shape of the returned tensor.
     * @param memoryRegionName Name of readonly memory region used by the tensor, see
     *  NewReadOnlyMemoryRegionFromFile in tensorflow::Env.
     * @param <T> data type for `ImmutableConst` output and operands
     * @return a new instance of ImmutableConst
     * @see org.tensorflow.op.Ops.immutableConst
     */
    public fun <T : TType> immutableConst(
        dtype: Class<T>,
        shape: Shape,
        memoryRegionName: String
    ): ImmutableConst<T> = java.immutableConst<T>(    
        dtype,
        shape,
        memoryRegionName
        )

    /**
     * Table initializer that takes two tensors for keys and values respectively.
     *
     * @param tableHandle Handle to a table which will be initialized.
     * @param keys Keys of type Tkey.
     * @param values Values of type Tval.
     * @return a new instance of InitializeTable
     * @see org.tensorflow.op.Ops.initializeTable
     */
    public fun initializeTable(
        tableHandle: Operand<out TType>,
        keys: Operand<out TType>,
        values: Operand<out TType>
    ): InitializeTable = java.initializeTable(    
        tableHandle,
        keys,
        values
        )

    /**
     * Initializes a table from a text file.
     *  It inserts one key-value pair into the table for each line of the file.
     *  The key and value is extracted from the whole line content, elements from the
     *  split line based on `delimiter` or the line number (starting from zero).
     *  Where to extract the key and value from a line is specified by `key_index` and
     *  `value_index`.
     *  <ul>
     *  <li>A value of -1 means use the line number(starting from zero), expects `int64`.</li>
     *  <li>A value of -2 means use the whole line content, expects `string`.</li>
     *  <li>A value >= 0 means use the index (starting at zero) of the split line based
     *  on `delimiter`.</li>
     *  </ul>
     *
     * @param tableHandle Handle to a table which will be initialized.
     * @param filename Filename of a vocabulary text file.
     * @param keyIndex Column index in a line to get the table `key` values from.
     * @param valueIndex Column index that represents information of a line to get the table
     *  `value` values from.
     * @param options carries optional attribute values
     * @return a new instance of InitializeTableFromTextFile
     * @see org.tensorflow.op.Ops.initializeTableFromTextFile
     * @param vocabSize Sets the vocabSize option.
     *
     * @param vocabSize Number of elements of the file, use -1 if unknown.
     * @return this Options instance.
     * @param delimiter Sets the delimiter option.
     *
     * @param delimiter Delimiter to separate fields in a line.
     * @return this Options instance.
     * @param offset Sets the offset option.
     *
     * @param offset the offset option
     * @return this Options instance.
     */
    public fun initializeTableFromTextFile(
        tableHandle: Operand<out TType>,
        filename: Operand<TString>,
        keyIndex: Long,
        valueIndex: Long,
        vocabSize: Long? = null,
        delimiter: String? = null,
        offset: Long? = null
    ): InitializeTableFromTextFile = java.initializeTableFromTextFile(    
        tableHandle,
        filename,
        keyIndex,
        valueIndex,
        *listOfNotNull(
            vocabSize?.let{ org.tensorflow.op.core.InitializeTableFromTextFile.vocabSize(it) },
            delimiter?.let{ org.tensorflow.op.core.InitializeTableFromTextFile.delimiter(it) },
            offset?.let{ org.tensorflow.op.core.InitializeTableFromTextFile.offset(it) }
        ).toTypedArray()
        )

    /**
     * Adds v into specified rows of x.
     *  ```
     * Computes y = x; y[i, :] += v; return y.
     *  
     * ```
     *
     * @param <T> data type for `y` output
     * @param x A `Tensor` of type T.
     * @param i A vector. Indices into the left-most dimension of `x`.
     * @param v A `Tensor` of type T. Same dimension sizes as x except the first dimension, which
     * must be the same as i's size.
     * @param <T> data type for `InplaceAdd` output and operands
     * @return a new instance of InplaceAdd
     * @see org.tensorflow.op.Ops.inplaceAdd
     */
    public fun <T : TType> inplaceAdd(
        x: Operand<T>,
        i: Operand<TInt32>,
        v: Operand<T>
    ): InplaceAdd<T> = java.inplaceAdd<T>(    
        x,
        i,
        v
        )

    /**
     * ```
     * Subtracts `v` into specified rows of `x`.
     *
     *  Computes y = x; y[i, :] -= v; return y.
     *  
     * ```
     *
     * @param <T> data type for `y` output
     * @param x A `Tensor` of type T.
     * @param i A vector. Indices into the left-most dimension of `x`.
     * @param v A `Tensor` of type T. Same dimension sizes as x except the first dimension, which
     * must be the same as i's size.
     * @param <T> data type for `InplaceSub` output and operands
     * @return a new instance of InplaceSub
     * @see org.tensorflow.op.Ops.inplaceSub
     */
    public fun <T : TType> inplaceSub(
        x: Operand<T>,
        i: Operand<TInt32>,
        v: Operand<T>
    ): InplaceSub<T> = java.inplaceSub<T>(    
        x,
        i,
        v
        )

    /**
     * Updates specified rows 'i' with values 'v'.
     *  Computes `x&#91;i, :&#93; = v; return x`.
     *  
     * Originally this function is mutative however for compilation we make this
     *  operation create / operate on a copy of `x`.
     *
     * @param <T> data type for `y` output
     * @param x A tensor of type `T`.
     * @param i A vector. Indices into the left-most dimension of `x`.
     * @param v A `Tensor` of type T. Same dimension sizes as x except the first dimension, which
     * must be the same as i's size.
     * @param <T> data type for `InplaceUpdate` output and operands
     * @return a new instance of InplaceUpdate
     * @see org.tensorflow.op.Ops.inplaceUpdate
     */
    public fun <T : TType> inplaceUpdate(
        x: Operand<T>,
        i: Operand<TInt32>,
        v: Operand<T>
    ): InplaceUpdate<T> = java.inplaceUpdate<T>(    
        x,
        i,
        v
        )

    /**
     * Checks whether a tensor has been initialized.
     *  Outputs boolean scalar indicating whether the tensor has been initialized.
     *
     * @param ref Should be from a `Variable` node. May be uninitialized.
     * @return a new instance of IsVariableInitialized
     * @see org.tensorflow.op.Ops.isVariableInitialized
     */
    public fun isVariableInitialized(ref: Operand<out TType>): IsVariableInitialized =
            java.isVariableInitialized(    
        ref
        )

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
     * @param input The input value
     * @param k The value of the k attribute
     * @return a new instance of KthOrderStatistic
     * @see org.tensorflow.op.Ops.kthOrderStatistic
     */
    public fun kthOrderStatistic(input: Operand<TFloat32>, k: Long): KthOrderStatistic =
            java.kthOrderStatistic(    
        input,
        k
        )

    /**
     * Outputs all keys and values in the table.
     *
     * @param <T> data type for `keys` output
     * @param <U> data type for `values` output
     * @param tableHandle Handle to the table.
     * @param Tkeys The value of the Tkeys attribute
     * @param Tvalues The value of the Tvalues attribute
     * @param <T> data type for `LookupTableExportV2` output and operands
     * @param <U> data type for `LookupTableExportV2` output and operands
     * @return a new instance of LookupTableExport
     * @see org.tensorflow.op.Ops.lookupTableExport
     */
    public fun <T : TType, U : TType> lookupTableExport(
        tableHandle: Operand<out TType>,
        Tkeys: Class<T>,
        Tvalues: Class<U>
    ): LookupTableExport<T, U> = java.lookupTableExport<T, U>(    
        tableHandle,
        Tkeys,
        Tvalues
        )

    /**
     * Looks up keys in a table, outputs the corresponding values.
     *  The tensor `keys` must of the same type as the keys of the table.
     *  The output `values` is of the type of the table values.
     *  
     * The scalar `default_value` is the value output for keys not present in the
     *  table. It must also be of the same type as the table values.
     *
     * @param <U> data type for `values` output
     * @param tableHandle Handle to the table.
     * @param keys Any shape.  Keys to look up.
     * @param defaultValue The defaultValue value
     * @param <U> data type for `LookupTableFindV2` output and operands
     * @return a new instance of LookupTableFind
     * @see org.tensorflow.op.Ops.lookupTableFind
     */
    public fun <U : TType> lookupTableFind(
        tableHandle: Operand<out TType>,
        keys: Operand<out TType>,
        defaultValue: Operand<U>
    ): LookupTableFind<U> = java.lookupTableFind<U>(    
        tableHandle,
        keys,
        defaultValue
        )

    /**
     * Replaces the contents of the table with the specified keys and values.
     *  The tensor `keys` must be of the same type as the keys of the table.
     *  The tensor `values` must be of the type of the table values.
     *
     * @param tableHandle Handle to the table.
     * @param keys Any shape.  Keys to look up.
     * @param values Values to associate with keys.
     * @return a new instance of LookupTableImport
     * @see org.tensorflow.op.Ops.lookupTableImport
     */
    public fun lookupTableImport(
        tableHandle: Operand<out TType>,
        keys: Operand<out TType>,
        values: Operand<out TType>
    ): LookupTableImport = java.lookupTableImport(    
        tableHandle,
        keys,
        values
        )

    /**
     * Updates the table to associates keys with values.
     *  The tensor `keys` must be of the same type as the keys of the table.
     *  The tensor `values` must be of the type of the table values.
     *
     * @param tableHandle Handle to the table.
     * @param keys Any shape.  Keys to look up.
     * @param values Values to associate with keys.
     * @return a new instance of LookupTableInsert
     * @see org.tensorflow.op.Ops.lookupTableInsert
     */
    public fun lookupTableInsert(
        tableHandle: Operand<out TType>,
        keys: Operand<out TType>,
        values: Operand<out TType>
    ): LookupTableInsert = java.lookupTableInsert(    
        tableHandle,
        keys,
        values
        )

    /**
     * Computes the number of elements in the given table.
     *
     * @param tableHandle Handle to the table.
     * @return a new instance of LookupTableSize
     * @see org.tensorflow.op.Ops.lookupTableSize
     */
    public fun lookupTableSize(tableHandle: Operand<out TType>): LookupTableSize =
            java.lookupTableSize(    
        tableHandle
        )

    /**
     * Forwards the input to the output.
     *  This operator represents the loop termination condition used by the
     *  &quot;pivot&quot; switches of a loop.
     *
     * @param input A boolean scalar, representing the branch predicate of the Switch op.
     * @return a new instance of LoopCond
     * @see org.tensorflow.op.Ops.loopCond
     */
    public fun loopCond(input: Operand<TBool>): LoopCond = java.loopCond(    
        input
        )

    /**
     * Make all elements in the non-Batch dimension unique, but &quot;close&quot; to
     *  their initial value. Never returns a sub-normal number. Never returns
     *  zero. The sign of each input element is always identical to the sign
     *  of the corresponding output element. Behavior for infinite elements is
     *  undefined. Behavior for subnormal elements is undefined.
     *
     * @param input The input value
     * @return a new instance of MakeUnique
     * @see org.tensorflow.op.Ops.makeUnique
     */
    public fun makeUnique(input: Operand<TFloat32>): MakeUnique = java.makeUnique(    
        input
        )

    /**
     * Op removes all elements in the underlying container.
     *
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of MapClear
     * @see org.tensorflow.op.Ops.mapClear
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun mapClear(
        dtypes: List<Class<out TType>>,
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

    /**
     * Op returns the number of incomplete elements in the underlying container.
     *
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of MapIncompleteSize
     * @see org.tensorflow.op.Ops.mapIncompleteSize
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun mapIncompleteSize(
        dtypes: List<Class<out TType>>,
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

    /**
     * Op peeks at the values at the specified key.  If the
     *  underlying container does not contain this key
     *  this op will block until it does.
     *
     * @param key The key value
     * @param indices The indices value
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of MapPeek
     * @see org.tensorflow.op.Ops.mapPeek
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun mapPeek(
        key: Operand<TInt64>,
        indices: Operand<TInt32>,
        dtypes: List<Class<out TType>>,
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

    /**
     * Op returns the number of elements in the underlying container.
     *
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of MapSize
     * @see org.tensorflow.op.Ops.mapSize
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun mapSize(
        dtypes: List<Class<out TType>>,
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

    /**
     * Stage (key, values) in the underlying container which behaves like a hashtable.
     *
     * @param key int64
     * @param indices The indices value
     * @param values a list of tensors
     *  dtypes A list of data types that inserted values should adhere to.
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of MapStage
     * @see org.tensorflow.op.Ops.mapStage
     * @param capacity Sets the capacity option.
     *
     * @param capacity Maximum number of elements in the Staging Area. If > 0, inserts
     *  on the container will block when the capacity is reached.
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container If non-empty, this queue is placed in the given container. Otherwise,
     *  a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName It is necessary to match this name to the matching Unstage Op.
     * @return this Options instance.
     */
    public fun mapStage(
        key: Operand<TInt64>,
        indices: Operand<TInt32>,
        values: Iterable<Operand<*>>,
        dtypes: List<Class<out TType>>,
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

    /**
     * Op removes and returns the values associated with the key
     *  from the underlying container.   If the underlying container
     *  does not contain this key, the op will block until it does.
     *
     * @param key The key value
     * @param indices The indices value
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of MapUnstage
     * @see org.tensorflow.op.Ops.mapUnstage
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun mapUnstage(
        key: Operand<TInt64>,
        indices: Operand<TInt32>,
        dtypes: List<Class<out TType>>,
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

    /**
     * Op removes and returns a random (key, value)
     *  from the underlying container.   If the underlying container
     *  does not contain elements, the op will block until it does.
     *
     * @param indices The indices value
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of MapUnstageNoKey
     * @see org.tensorflow.op.Ops.mapUnstageNoKey
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun mapUnstageNoKey(
        indices: Operand<TInt32>,
        dtypes: List<Class<out TType>>,
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

    /**
     * Computes the maximum of elements across dimensions of a tensor.
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param <T> data type for `output` output
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `[-rank(input), rank(input))`.
     * @param options carries optional attribute values
     * @param <T> data type for `Max` output and operands
     * @return a new instance of Max
     * @see org.tensorflow.op.Ops.max
     * @param keepDims Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public fun <T : TNumber> max(
        input: Operand<T>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): Max<T> = java.max<T>(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.core.Max.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Forwards the value of an available tensor from `inputs` to `output`.
     *  `Merge` waits for at least one of the tensors in `inputs` to become available.
     *  It is usually combined with `Switch` to implement branching.
     *  
     * `Merge` forwards the first tensor to become available to `output`, and sets
     *  `value_index` to its index in `inputs`.
     *
     * @param <T> data type for `output` output
     * @param inputs The input tensors, exactly one of which will become available.
     * @param <T> data type for `Merge` output and operands
     * @return a new instance of Merge
     * @see org.tensorflow.op.Ops.merge
     */
    public fun <T : TType> merge(inputs: Iterable<Operand<T>>): Merge<T> = java.merge<T>(    
        inputs
        )

    /**
     * Computes the minimum of elements across dimensions of a tensor.
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param <T> data type for `output` output
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `[-rank(input), rank(input))`.
     * @param options carries optional attribute values
     * @param <T> data type for `Min` output and operands
     * @return a new instance of Min
     * @see org.tensorflow.op.Ops.min
     * @param keepDims Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public fun <T : TNumber> min(
        input: Operand<T>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): Min<T> = java.min<T>(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.core.Min.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Pads a tensor with mirrored values.
     *  This operation pads a `input` with mirrored values according to the `paddings`
     *  you specify. `paddings` is an integer tensor with shape `&#91;n, 2&#93;`, where n is
     *  the rank of `input`. For each dimension D of `input`, `paddings&#91;D, 0&#93;` indicates
     *  how many values to add before the contents of `input` in that dimension, and
     *  `paddings&#91;D, 1&#93;` indicates how many values to add after the contents of `input`
     *  in that dimension. Both `paddings&#91;D, 0&#93;` and `paddings&#91;D, 1&#93;` must be no
     * greater
     *  than `input.dim_size(D)` (or `input.dim_size(D) - 1`) if `copy_border` is true
     *  (if false, respectively).
     *  
     * The padded size of each dimension D of the output is:
     *  
     * `paddings(D, 0) + input.dim_size(D) + paddings(D, 1)`
     *  
     * For example:
     *  ```
     * # 't' is [[1, 2, 3], [4, 5, 6]].
     *  # 'paddings' is [[1, 1]], [2, 2]].
     *  # 'mode' is SYMMETRIC.
     *  # rank of 't' is 2.
     *  pad(t, paddings) ==> [[2, 1, 1, 2, 3, 3, 2]
     *                        [2, 1, 1, 2, 3, 3, 2]
     *                        [5, 4, 4, 5, 6, 6, 5]
     *                        [5, 4, 4, 5, 6, 6, 5]]
     *  
     * ```
     *
     * @param <T> data type for `output` output
     * @param input The input tensor to be padded.
     * @param paddings A two-column matrix specifying the padding sizes. The number of
     *  rows must be the same as the rank of `input`.
     * @param mode Either `REFLECT` or `SYMMETRIC`. In reflect mode the padded regions
     *  do not include the borders, while in symmetric mode the padded regions
     *  do include the borders. For example, if `input` is `&#91;1, 2, 3&#93;` and `paddings`
     *  is `&#91;0, 2&#93;`, then the output is `&#91;1, 2, 3, 2, 1&#93;` in reflect mode, and
     *  it is `&#91;1, 2, 3, 3, 2&#93;` in symmetric mode.
     * @param <T> data type for `MirrorPad` output and operands
     * @return a new instance of MirrorPad
     * @see org.tensorflow.op.Ops.mirrorPad
     */
    public fun <T : TType> mirrorPad(
        input: Operand<T>,
        paddings: Operand<out TNumber>,
        mode: String
    ): MirrorPad<T> = java.mirrorPad<T>(    
        input,
        paddings,
        mode
        )

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
     *  ```
     * import tensorflow as tf
     *  from tensorflow.compiler.mlir.tensorflow.gen_mlir_passthrough_op import mlir_passthrough_op
     *
     *  mlir_module = '''python
     *  func {@literal @
     * ```main(%arg0 : tensor<10xf32>, %arg1 : tensor<10xf32>) -> tensor<10x10xf32> {
     *     %add = &quot;magic.op&quot;(%arg0, %arg1) : (tensor<10xf32>, tensor<10xf32>) ->
     * tensor<10x10xf32>
     *     return %ret : tensor<10x10xf32>
     *  }
     *  '''
     *
     *  `@`tf.function
     *  def foo(x, y):
     *    return mlir_passthrough_op(&#91;x, y&#93;, mlir_module, Toutputs=&#91;tf.float32&#93;)
     *
     *  graph_def = foo.get_concrete_function(tf.TensorSpec(&#91;10&#93;, tf.float32),
     * tf.TensorSpec(&#91;10&#93;, tf.float32)).graph.as_graph_def()
     *  }
     *
     * @param inputs The inputs value
     * @param mlirModule The value of the mlirModule attribute
     * @param Toutputs The value of the Toutputs attribute
     * @return a new instance of MlirPassthroughOp
     * @see org.tensorflow.op.Ops.mlirPassthroughOp
     */
    public fun mlirPassthroughOp(
        inputs: Iterable<Operand<*>>,
        mlirModule: String,
        Toutputs: List<Class<out TType>>
    ): MlirPassthroughOp = java.mlirPassthroughOp(    
        inputs,
        mlirModule,
        Toutputs
        )

    /**
     * Creates an empty hash table that uses tensors as the backing store.
     *  It uses &quot;open addressing&quot; with quadratic reprobing to resolve
     *  collisions.
     *  
     * This op creates a mutable hash table, specifying the type of its keys and
     *  values. Each value must be a scalar. Data can be inserted into the table using
     *  the insert operations. It does not support the initialization operation.
     *
     * @param emptyKey The key used to represent empty key buckets internally. Must not
     *  be used in insert or lookup operations.
     * @param deletedKey The deletedKey value
     * @param valueDtype Type of the table values.
     * @param options carries optional attribute values
     * @param <T> data type for `MutableDenseHashTableV2` output and operands
     * @param <U> data type for `MutableDenseHashTableV2` output and operands
     * @return a new instance of MutableDenseHashTable
     * @see org.tensorflow.op.Ops.mutableDenseHashTable
     * @param container Sets the container option.
     *
     * @param container If non-empty, this table is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this table is shared under the given name across
     *  multiple sessions.
     * @return this Options instance.
     * @param useNodeNameSharing Sets the useNodeNameSharing option.
     *
     * @param useNodeNameSharing the useNodeNameSharing option
     * @return this Options instance.
     * @param valueShape Sets the valueShape option.
     *
     * @param valueShape The shape of each value.
     * @return this Options instance.
     * @param initialNumBuckets Sets the initialNumBuckets option.
     *
     * @param initialNumBuckets The initial number of hash table buckets. Must be a power
     *  to 2.
     * @return this Options instance.
     * @param maxLoadFactor Sets the maxLoadFactor option.
     *
     * @param maxLoadFactor The maximum ratio between number of entries and number of
     *  buckets before growing the table. Must be between 0 and 1.
     * @return this Options instance.
     */
    public fun <T : TType, U : TType> mutableDenseHashTable(
        emptyKey: Operand<T>,
        deletedKey: Operand<T>,
        valueDtype: Class<U>,
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
            useNodeNameSharing?.let{ org.tensorflow.op.core.MutableDenseHashTable.useNodeNameSharing(it)
            },
            valueShape?.let{ org.tensorflow.op.core.MutableDenseHashTable.valueShape(it) },
            initialNumBuckets?.let{ org.tensorflow.op.core.MutableDenseHashTable.initialNumBuckets(it) },
            maxLoadFactor?.let{ org.tensorflow.op.core.MutableDenseHashTable.maxLoadFactor(it) }
        ).toTypedArray()
        )

    /**
     * Creates an empty hash table.
     *  This op creates a mutable hash table, specifying the type of its keys and
     *  values. Each value must be a scalar. Data can be inserted into the table using
     *  the insert operations. It does not support the initialization operation.
     *
     * @param keyDtype Type of the table keys.
     * @param valueDtype Type of the table values.
     * @param options carries optional attribute values
     * @param <T> data type for `MutableHashTableV2` output and operands
     * @param <U> data type for `MutableHashTableV2` output and operands
     * @return a new instance of MutableHashTable
     * @see org.tensorflow.op.Ops.mutableHashTable
     * @param container Sets the container option.
     *
     * @param container If non-empty, this table is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this table is shared under the given name across
     *  multiple sessions.
     * @return this Options instance.
     * @param useNodeNameSharing Sets the useNodeNameSharing option.
     *
     * @param useNodeNameSharing If true and shared_name is empty, the table is shared
     *  using the node name.
     * @return this Options instance.
     */
    public fun <T : TType, U : TType> mutableHashTable(
        keyDtype: Class<T>,
        valueDtype: Class<U>,
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

    /**
     * Creates an empty hash table.
     *  This op creates a mutable hash table, specifying the type of its keys and
     *  values. Each value must be a vector. Data can be inserted into the table using
     *  the insert operations. It does not support the initialization operation.
     *
     * @param keyDtype Type of the table keys.
     * @param valueDtype Type of the table values.
     * @param options carries optional attribute values
     * @param <T> data type for `MutableHashTableOfTensorsV2` output and operands
     * @param <U> data type for `MutableHashTableOfTensorsV2` output and operands
     * @return a new instance of MutableHashTableOfTensors
     * @see org.tensorflow.op.Ops.mutableHashTableOfTensors
     * @param container Sets the container option.
     *
     * @param container If non-empty, this table is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this table is shared under the given name across
     *  multiple sessions.
     * @return this Options instance.
     * @param useNodeNameSharing Sets the useNodeNameSharing option.
     *
     * @param useNodeNameSharing the useNodeNameSharing option
     * @return this Options instance.
     * @param valueShape Sets the valueShape option.
     *
     * @param valueShape the valueShape option
     * @return this Options instance.
     */
    public fun <T : TType, U : TType> mutableHashTableOfTensors(
        keyDtype: Class<T>,
        valueDtype: Class<U>,
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
            useNodeNameSharing?.let{
            org.tensorflow.op.core.MutableHashTableOfTensors.useNodeNameSharing(it) },
            valueShape?.let{ org.tensorflow.op.core.MutableHashTableOfTensors.valueShape(it) }
        ).toTypedArray()
        )

    /**
     * Creates a Mutex resource that can be locked by `MutexLock`.
     *
     * @param options carries optional attribute values
     * @return a new instance of Mutex
     * @see org.tensorflow.op.Ops.mutex
     *
     * @param container Sets the container option.
     *
     * @param container If non-empty, this variable is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this variable is named in the given bucket
     *  with this shared_name. Otherwise, the node name is used instead.
     * @return this Options instance.
     */
    public fun mutex(container: String? = null, sharedName: String? = null): Mutex = java.mutex(    
        *listOfNotNull(
            container?.let{ org.tensorflow.op.core.Mutex.container(it) },
            sharedName?.let{ org.tensorflow.op.core.Mutex.sharedName(it) }
        ).toTypedArray()
        )

    /**
     * Locks a mutex resource.  The output is the lock.  So long as the lock tensor
     *  is alive, any other request to use `MutexLock` with this mutex will wait.
     *  
     * This is particularly useful for creating a critical section when used in
     *  conjunction with `MutexLockIdentity`:
     *  ```
     * mutex = mutex_v2(
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
     *  
     * ```
     *  
     * While `fn` is running in the critical section, no other functions which wish to
     *  use this critical section may run.
     *  
     * Often the use case is that two executions of the same graph, in parallel,
     *  wish to run `fn`; and we wish to ensure that only one of them executes
     *  at a time.  This is especially important if `fn` modifies one or more
     *  variables at a time.
     *  
     * It is also useful if two separate functions must share a resource, but we
     *  wish to ensure the usage is exclusive.
     *
     * @param mutex The mutex resource to lock.
     * @return a new instance of MutexLock
     * @see org.tensorflow.op.Ops.mutexLock
     */
    public fun mutexLock(mutex: Operand<out TType>): MutexLock = java.mutexLock(    
        mutex
        )

    /**
     * Makes its input available to the next iteration.
     *
     * @param <T> data type for `output` output
     * @param data The tensor to be made available to the next iteration.
     * @param <T> data type for `NextIteration` output and operands
     * @return a new instance of NextIteration
     * @see org.tensorflow.op.Ops.nextIteration
     */
    public fun <T : TType> nextIteration(`data`: Operand<T>): NextIteration<T> =
            java.nextIteration<T>(    
        data
        )

    /**
     * Does nothing. Only useful as a placeholder for control edges.
     *
     * @return a new instance of NoOp
     * @see org.tensorflow.op.Ops.noOp
     */
    public fun noOp(): NoOp = java.noOp(    
        
        )

    /**
     * Returns a one-hot tensor.
     *  The locations represented by indices in `indices` take value `on_value`,
     *  while all other locations take value `off_value`.
     *  
     * If the input `indices` is rank `N`, the output will have rank `N+1`,
     *  The new axis is created at dimension `axis` (default: the new axis is
     *  appended at the end).
     *  
     * If `indices` is a scalar the output shape will be a vector of length `depth`.
     *  
     * If `indices` is a vector of length `features`, the output shape will be:
     *  ```
     * features x depth if axis == -1
     *    depth x features if axis == 0
     *  
     * ```
     *  
     * If `indices` is a matrix (batch) with shape `&#91;batch, features&#93;`,
     *  the output shape will be:
     *  ```
     * batch x features x depth if axis == -1
     *    batch x depth x features if axis == 1
     *    depth x batch x features if axis == 0
     *  
     * ```
     *  **Examples**
     *
     *  
     * Suppose that
     *  ```
     * indices = [0, 2, -1, 1]
     *    depth = 3
     *    on_value = 5.0
     *    off_value = 0.0
     *    axis = -1
     *  
     * ```
     *  
     * Then output is `&#91;4 x 3&#93;`:
     *  ```
     * output =
     *    [5.0 0.0 0.0]  // one_hot(0)
     *    [0.0 0.0 5.0]  // one_hot(2)
     *    [0.0 0.0 0.0]  // one_hot(-1)
     *    [0.0 5.0 0.0]  // one_hot(1)
     *  
     * ```
     *  
     * Suppose that
     *  ```
     * indices = [0, 2, -1, 1]
     *    depth = 3
     *    on_value = 0.0
     *    off_value = 3.0
     *    axis = 0
     *  
     * ```
     *  
     * Then output is `&#91;3 x 4&#93;`:
     *  ```
     * output =
     *    [0.0 3.0 3.0 3.0]
     *    [3.0 3.0 3.0 0.0]
     *    [3.0 3.0 3.0 3.0]
     *    [3.0 0.0 3.0 3.0]
     *  //  ^                one_hot(0)
     *  //      ^            one_hot(2)
     *  //          ^        one_hot(-1)
     *  //              ^    one_hot(1)
     *  
     * ```
     *  
     * Suppose that
     *  ```
     * indices = [[0, 2], [1, -1]]
     *    depth = 3
     *    on_value = 1.0
     *    off_value = 0.0
     *    axis = -1
     *  
     * ```
     *  
     * Then output is `&#91;2 x 2 x 3&#93;`:
     *  ```
     * output =
     *    [
     *      [1.0, 0.0, 0.0]  // one_hot(0)
     *      [0.0, 0.0, 1.0]  // one_hot(2)
     *    ][
     *      [0.0, 1.0, 0.0]  // one_hot(1)
     *      [0.0, 0.0, 0.0]  // one_hot(-1)
     *    ]
     *  
     * ```
     *
     * @param <U> data type for `output` output
     * @param indices A tensor of indices.
     * @param depth A scalar defining the depth of the one hot dimension.
     * @param onValue A scalar defining the value to fill in output when `indices[j] = i`.
     * @param offValue A scalar defining the value to fill in output when `indices[j] != i`.
     * @param options carries optional attribute values
     * @param <U> data type for `OneHot` output and operands
     * @return a new instance of OneHot
     * @see org.tensorflow.op.Ops.oneHot
     * @param axis Sets the axis option.
     *
     * @param axis The axis to fill (default: -1, a new inner-most axis).
     * @return this Options instance.
     */
    public fun <U : TType> oneHot(
        indices: Operand<out TNumber>,
        depth: Operand<TInt32>,
        onValue: Operand<U>,
        offValue: Operand<U>,
        axis: Long? = null
    ): OneHot<U> = java.oneHot<U>(    
        indices,
        depth,
        onValue,
        offValue,
        *listOfNotNull(
            axis?.let{ org.tensorflow.op.core.OneHot.axis(it) }
        ).toTypedArray()
        )

    /**
     * Creates a one valued tensor given its type and shape.
     *
     * @param dims a 1-D operand that represents the shape of the output tensor
     * @param type the output tensor type class. Can not be TString.
     * @return a constant tensor initialized with ones
     * @throws IllegalArgumentException if the tensor type or shape cannot be initialized with ones.
     * @see org.tensorflow.op.Ops.ones
     */
    public fun <T : TType> ones(dims: Operand<out TNumber>, type: Class<T>): Ones<T> =
            java.ones<T>(    
        dims,
        type
        )

    /**
     * Returns a tensor of ones with the same shape and type as x.
     *
     * @param <T> data type for `y` output
     * @param x a tensor of type T.
     * @param <T> data type for `OnesLike` output and operands
     * @return a new instance of OnesLike
     * @see org.tensorflow.op.Ops.onesLike
     */
    public fun <T : TType> onesLike(x: Operand<T>): OnesLike<T> = java.onesLike<T>(    
        x
        )

    /**
     * Op removes all elements in the underlying container.
     *
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of OrderedMapClear
     * @see org.tensorflow.op.Ops.orderedMapClear
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun orderedMapClear(
        dtypes: List<Class<out TType>>,
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

    /**
     * Op returns the number of incomplete elements in the underlying container.
     *
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of OrderedMapIncompleteSize
     * @see org.tensorflow.op.Ops.orderedMapIncompleteSize
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun orderedMapIncompleteSize(
        dtypes: List<Class<out TType>>,
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

    /**
     * Op peeks at the values at the specified key.  If the
     *  underlying container does not contain this key
     *  this op will block until it does.   This Op is optimized for
     *  performance.
     *
     * @param key The key value
     * @param indices The indices value
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of OrderedMapPeek
     * @see org.tensorflow.op.Ops.orderedMapPeek
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun orderedMapPeek(
        key: Operand<TInt64>,
        indices: Operand<TInt32>,
        dtypes: List<Class<out TType>>,
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

    /**
     * Op returns the number of elements in the underlying container.
     *
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of OrderedMapSize
     * @see org.tensorflow.op.Ops.orderedMapSize
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun orderedMapSize(
        dtypes: List<Class<out TType>>,
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

    /**
     * Stage (key, values) in the underlying container which behaves like a ordered
     *  associative container.   Elements are ordered by key.
     *
     * @param key int64
     * @param indices The indices value
     * @param values a list of tensors
     *  dtypes A list of data types that inserted values should adhere to.
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of OrderedMapStage
     * @see org.tensorflow.op.Ops.orderedMapStage
     * @param capacity Sets the capacity option.
     *
     * @param capacity Maximum number of elements in the Staging Area. If > 0, inserts
     *  on the container will block when the capacity is reached.
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container If non-empty, this queue is placed in the given container. Otherwise,
     *  a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName It is necessary to match this name to the matching Unstage Op.
     * @return this Options instance.
     */
    public fun orderedMapStage(
        key: Operand<TInt64>,
        indices: Operand<TInt32>,
        values: Iterable<Operand<*>>,
        dtypes: List<Class<out TType>>,
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

    /**
     * Op removes and returns the values associated with the key
     *  from the underlying container.   If the underlying container
     *  does not contain this key, the op will block until it does.
     *
     * @param key The key value
     * @param indices The indices value
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of OrderedMapUnstage
     * @see org.tensorflow.op.Ops.orderedMapUnstage
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun orderedMapUnstage(
        key: Operand<TInt64>,
        indices: Operand<TInt32>,
        dtypes: List<Class<out TType>>,
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

    /**
     * Op removes and returns the (key, value) element with the smallest
     *  key from the underlying container.   If the underlying container
     *  does not contain elements, the op will block until it does.
     *
     * @param indices The indices value
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of OrderedMapUnstageNoKey
     * @see org.tensorflow.op.Ops.orderedMapUnstageNoKey
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun orderedMapUnstageNoKey(
        indices: Operand<TInt32>,
        dtypes: List<Class<out TType>>,
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

    /**
     * Pads a tensor.
     *  This operation pads `input` according to the `paddings` and `constant_values`
     *  you specify. `paddings` is an integer tensor with shape `&#91;Dn, 2&#93;`, where n is
     *  the rank of `input`. For each dimension D of `input`, `paddings&#91;D, 0&#93;` indicates
     *  how many padding values to add before the contents of `input` in that dimension,
     *  and `paddings&#91;D, 1&#93;` indicates how many padding values to add after the contents
     *  of `input` in that dimension. `constant_values` is a scalar tensor of the same
     *  type as `input` that indicates the value to use for padding `input`.
     *  
     * The padded size of each dimension D of the output is:
     *  
     * `paddings(D, 0) + input.dim_size(D) + paddings(D, 1)`
     *  
     * For example:
     *  ```
     * # 't' is [[1, 1], [2, 2]]
     *  # 'paddings' is [[1, 1], [2, 2]]
     *  # 'constant_values' is 0
     *  # rank of 't' is 2
     *  pad(t, paddings) ==> [[0, 0, 0, 0, 0, 0]
     *                        [0, 0, 1, 1, 0, 0]
     *                        [0, 0, 2, 2, 0, 0]
     *                        [0, 0, 0, 0, 0, 0]]
     *  
     * ```
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param paddings The paddings value
     * @param constantValues The constantValues value
     * @param <T> data type for `PadV2` output and operands
     * @return a new instance of Pad
     * @see org.tensorflow.op.Ops.pad
     */
    public fun <T : TType> pad(
        input: Operand<T>,
        paddings: Operand<out TNumber>,
        constantValues: Operand<T>
    ): Pad<T> = java.pad<T>(    
        input,
        paddings,
        constantValues
        )

    /**
     * Concatenates a list of `N` tensors along the first dimension.
     *  The input tensors are all required to have size 1 in the first dimension.
     *  
     * For example:
     *  ```
     * # 'x' is [[1, 4]]
     *  # 'y' is [[2, 5]]
     *  # 'z' is [[3, 6]]
     *  parallel_concat([x, y, z]) => [[1, 4], [2, 5], [3, 6]]  # Pack along first dim.
     *  
     * ```
     *  
     * The difference between concat and parallel_concat is that concat requires all
     *  of the inputs be computed before the operation will begin but doesn't require
     *  that the input shapes be known during graph construction.  Parallel concat
     *  will copy pieces of the input into the output as they become available, in
     *  some situations this can provide a performance benefit.
     *
     * @param <T> data type for `output` output
     * @param values Tensors to be concatenated. All must have size 1 in the first dimension
     *  and same shape.
     * @param shape the final shape of the result; should be equal to the shapes of any input
     *  but with the number of input values in the first dimension.
     * @param <T> data type for `ParallelConcat` output and operands
     * @return a new instance of ParallelConcat
     * @see org.tensorflow.op.Ops.parallelConcat
     */
    public fun <T : TType> parallelConcat(values: Iterable<Operand<T>>, shape: Shape):
            ParallelConcat<T> = java.parallelConcat<T>(    
        values,
        shape
        )

    /**
     * Interleave the values from the `data` tensors into a single tensor.
     *  Builds a merged tensor such that
     *  ```
     * merged[indices[m][i, ..., j], ...] = data[m][i, ..., j, ...]
     *  
     * ```
     *  
     * For example, if each `indices[m]` is scalar or vector, we have
     *  ```
     * # Scalar indices:
     *      merged[indices[m], ...] = data[m][...]
     *
     *      # Vector indices:
     *      merged[indices[m][i], ...] = data[m][i, ...]
     *  
     * ```
     *  
     * Each `data[i].shape` must start with the corresponding `indices[i].shape`,
     *  and the rest of `data[i].shape` must be constant w.r.t. `i`.  That is, we
     *  must have `data[i].shape = indices[i].shape + constant`.  In terms of this
     *  `constant`, the output shape is
     *  ```
     * merged.shape = [max(indices)] + constant
     *  
     * ```
     *  
     * Values may be merged in parallel, so if an index appears in both `indices[m][i]`
     *  and `indices[n][j]`, the result may be invalid. This differs from the normal
     *  DynamicStitch operator that defines the behavior in that case.
     *  
     * For example:
     *  ```
     * indices[0] = 6
     *      indices[1] = [4, 1]
     *      indices[2] = [[5, 2], [0, 3]]
     *      data[0] = [61, 62]
     *      data[1] = [[41, 42], [11, 12]]
     *      data[2] = [[[51, 52], [21, 22]], [[1, 2], [31, 32]]]
     *      merged = [[1, 2], [11, 12], [21, 22], [31, 32], [41, 42],
     *                [51, 52], [61, 62]]
     *  
     * ```
     *  
     * This method can be used to merge partitions created by `dynamic_partition`
     *  as illustrated on the following example:
     *  ```
     * # Apply function (increments x_i) on elements for which a certain condition
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
     *  
     * ```
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/DynamicStitch.png" alt>
     *  </div>
     *
     * @param <T> data type for `merged` output
     * @param indices The indices value
     * @param data The data value
     * @param <T> data type for `ParallelDynamicStitch` output and operands
     * @return a new instance of ParallelDynamicStitch
     * @see org.tensorflow.op.Ops.parallelDynamicStitch
     */
    public fun <T : TType> parallelDynamicStitch(indices: Iterable<Operand<TInt32>>,
            `data`: Iterable<Operand<T>>): ParallelDynamicStitch<T> =
            java.parallelDynamicStitch<T>(    
        indices,
        data
        )

    /**
     * returns `f(inputs)`, where `f`'s body is placed and partitioned.
     *
     *  
     * Selects between [StatefulPartitionedCall] and [StatelessPartitionedCall] based on the
     * statefulness of the function arguments.
     *
     * @param args A list of input tensors.
     * @param Tout A list of output types.
     * @param f `
     * A function that takes 'args', a list of tensors, and returns 'output',
     *    another list of tensors. Input and output types are specified by 'Tin'
     *    and 'Tout'. The function body of f will be placed and partitioned across
     *    devices, setting this op apart from the regular Call op. This op is
     *    stateful.
     *  
     * `
     * @param options carries optional attribute values
     * @return a new instance of PartitionedCall
     * @see org.tensorflow.op.Ops.partitionedCall
     * @param config Sets the config option.
     *
     * @param config the config option
     * @return this Options instance.
     * @param configProto Sets the configProto option.
     *
     * @param configProto the configProto option
     * @return this Options instance.
     * @param executorType Sets the executorType option.
     *
     * @param executorType the executorType option
     * @return this Options instance.
     */
    public fun partitionedCall(
        args: Iterable<Operand<*>>,
        Tout: List<Class<out TType>>,
        f: ConcreteFunction,
        config: String? = null,
        configProto: String? = null,
        executorType: String? = null
    ): PartitionedCall = java.partitionedCall(    
        args,
        Tout,
        f,
        *listOfNotNull(
            config?.let{ org.tensorflow.op.core.PartitionedCall.config(it) },
            configProto?.let{ org.tensorflow.op.core.PartitionedCall.configProto(it) },
            executorType?.let{ org.tensorflow.op.core.PartitionedCall.executorType(it) }
        ).toTypedArray()
        )

    /**
     * A placeholder op for a value that will be fed into the computation.
     *  N.B. This operation will fail with an error if it is executed. It is
     *  intended as a way to represent a value that will always be fed, and to
     *  provide attrs that enable the fed value to be checked at runtime.
     *
     * @param <T> data type for `output` output
     * @param dtype The type of elements in the tensor.
     * @param options carries optional attribute values
     * @param <T> data type for `Placeholder` output and operands
     * @return a new instance of Placeholder
     * @see org.tensorflow.op.Ops.placeholder
     * @param shape Sets the shape option.
     *
     * @param shape (Optional) The shape of the tensor. If the shape has 0 dimensions, the
     *  shape is unconstrained.
     * @return this Options instance.
     */
    public fun <T : TType> placeholder(dtype: Class<T>, shape: Shape? = null): Placeholder<T> =
            java.placeholder<T>(    
        dtype,
        *listOfNotNull(
            shape?.let{ org.tensorflow.op.core.Placeholder.shape(it) }
        ).toTypedArray()
        )

    /**
     * A placeholder op that passes through `input` when its output is not fed.
     *
     * @param <T> data type for `output` output
     * @param input The default value to produce when `output` is not fed.
     * @param shape The (possibly partial) shape of the tensor.
     * @param <T> data type for `PlaceholderWithDefault` output and operands
     * @return a new instance of PlaceholderWithDefault
     * @see org.tensorflow.op.Ops.placeholderWithDefault
     */
    public fun <T : TType> placeholderWithDefault(input: Operand<T>, shape: Shape):
            PlaceholderWithDefault<T> = java.placeholderWithDefault<T>(    
        input,
        shape
        )

    /**
     * Prints a string scalar.
     *  Prints a string scalar to the desired output_stream.
     *
     * @param input The string scalar to print.
     * @param options carries optional attribute values
     * @return a new instance of Print
     * @see org.tensorflow.op.Ops.print
     * @param outputStream Sets the outputStream option.
     *
     * @param outputStream A string specifying the output stream or logging level to print to.
     * @return this Options instance.
     * @param end Sets the end option.
     *
     * @param end the end option
     * @return this Options instance.
     */
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

    /**
     * Computes the product of elements across dimensions of a tensor.
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param <T> data type for `output` output
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `[-rank(input), rank(input))`.
     * @param options carries optional attribute values
     * @param <T> data type for `Prod` output and operands
     * @return a new instance of Prod
     * @see org.tensorflow.op.Ops.prod
     * @param keepDims Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public fun <T : TType> prod(
        input: Operand<T>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): Prod<T> = java.prod<T>(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.core.Prod.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Reshapes a quantized tensor as per the Reshape op.
     *
     * @param <T> data type for `output` output
     * @param tensor The tensor value
     * @param shape Defines the shape of the output tensor.
     * @param inputMin The minimum value of the input.
     * @param inputMax The maximum value of the input.
     * @param <T> data type for `QuantizedReshape` output and operands
     * @return a new instance of QuantizedReshape
     * @see org.tensorflow.op.Ops.quantizedReshape
     */
    public fun <T : TType> quantizedReshape(
        tensor: Operand<T>,
        shape: Operand<out TNumber>,
        inputMin: Operand<TFloat32>,
        inputMax: Operand<TFloat32>
    ): QuantizedReshape<T> = java.quantizedReshape<T>(    
        tensor,
        shape,
        inputMin,
        inputMax
        )

    /**
     * Creates a sequence of numbers.
     *  This operation creates a sequence of numbers that begins at `start` and
     *  extends by increments of `delta` up to but not including `limit`.
     *  
     * For example:
     *  ```
     * # 'start' is 3
     *  # 'limit' is 18
     *  # 'delta' is 3
     *  tf.range(start, limit, delta) ==> [3, 6, 9, 12, 15]
     *  
     * ```
     *
     * @param <T> data type for `output` output
     * @param start 0-D (scalar). First entry in the sequence.
     * @param limit 0-D (scalar). Upper limit of sequence, exclusive.
     * @param delta 0-D (scalar). Optional. Default is 1. Number that increments `start`.
     * @param <T> data type for `Range` output and operands
     * @return a new instance of Range
     * @see org.tensorflow.op.Ops.range
     */
    public fun <T : TNumber> range(
        start: Operand<T>,
        limit: Operand<T>,
        delta: Operand<T>
    ): Range<T> = java.range<T>(    
        start,
        limit,
        delta
        )

    /**
     * Returns the rank of a tensor.
     *  This operation returns an integer representing the rank of `input`.
     *  
     * For example:
     *  ```
     * # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
     *  # shape of tensor 't' is [2, 2, 3]
     *  rank(t) ==> 3
     *  
     * ```
     *  
     * **Note**: The rank of a tensor is not the same as the rank of a matrix. The rank
     *  of a tensor is the number of indices required to uniquely select each element
     *  of the tensor. Rank is also known as &quot;order&quot;, &quot;degree&quot;, or
     * &quot;ndims.&quot;
     *
     * @param input The input value
     * @return a new instance of Rank
     * @see org.tensorflow.op.Ops.rank
     */
    public fun rank(input: Operand<out TType>): Rank = java.rank(    
        input
        )

    /**
     * Reads the value of a variable.
     *  The tensor returned by this operation is immutable.
     *  
     * The value returned by this operation is guaranteed to be influenced by all the
     *  writes on which this operation depends directly or indirectly, and to not be
     *  influenced by any of the writes which depend directly or indirectly on this
     *  operation.
     *
     * @param <T> data type for `value` output
     * @param resource handle to the resource in which to store the variable.
     * @param dtype the dtype of the value.
     * @param <T> data type for `ReadVariableOp` output and operands
     * @return a new instance of ReadVariableOp
     * @see org.tensorflow.op.Ops.readVariableOp
     */
    public fun <T : TType> readVariableOp(resource: Operand<out TType>, dtype: Class<T>):
            ReadVariableOp<T> = java.readVariableOp<T>(    
        resource,
        dtype
        )

    /**
     * Computes the &quot;logical and&quot; of elements across dimensions of a tensor.
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `[-rank(input), rank(input))`.
     * @param options carries optional attribute values
     * @return a new instance of ReduceAll
     * @see org.tensorflow.op.Ops.reduceAll
     * @param keepDims Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public fun reduceAll(
        input: Operand<TBool>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): ReduceAll = java.reduceAll(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.core.ReduceAll.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Computes the &quot;logical or&quot; of elements across dimensions of a tensor.
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `[-rank(input), rank(input))`.
     * @param options carries optional attribute values
     * @return a new instance of ReduceAny
     * @see org.tensorflow.op.Ops.reduceAny
     * @param keepDims Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public fun reduceAny(
        input: Operand<TBool>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): ReduceAny = java.reduceAny(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.core.ReduceAny.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Computes the maximum of elements across dimensions of a tensor.
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param <T> data type for `output` output
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `[-rank(input), rank(input))`.
     * @param options carries optional attribute values
     * @param <T> data type for `Max` output and operands
     * @return a new instance of ReduceMax
     * @see org.tensorflow.op.Ops.reduceMax
     * @param keepDims Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public fun <T : TNumber> reduceMax(
        input: Operand<T>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): ReduceMax<T> = java.reduceMax<T>(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.core.ReduceMax.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Computes the minimum of elements across dimensions of a tensor.
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param <T> data type for `output` output
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `[-rank(input), rank(input))`.
     * @param options carries optional attribute values
     * @param <T> data type for `Min` output and operands
     * @return a new instance of ReduceMin
     * @see org.tensorflow.op.Ops.reduceMin
     * @param keepDims Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public fun <T : TNumber> reduceMin(
        input: Operand<T>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): ReduceMin<T> = java.reduceMin<T>(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.core.ReduceMin.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Computes the product of elements across dimensions of a tensor.
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param <T> data type for `output` output
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `[-rank(input), rank(input))`.
     * @param options carries optional attribute values
     * @param <T> data type for `Prod` output and operands
     * @return a new instance of ReduceProd
     * @see org.tensorflow.op.Ops.reduceProd
     * @param keepDims Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public fun <T : TType> reduceProd(
        input: Operand<T>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): ReduceProd<T> = java.reduceProd<T>(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.core.ReduceProd.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Computes the sum of elements across dimensions of a tensor.
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param <T> data type for `output` output
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `[-rank(input), rank(input))`.
     * @param options carries optional attribute values
     * @param <T> data type for `Sum` output and operands
     * @return a new instance of ReduceSum
     * @see org.tensorflow.op.Ops.reduceSum
     * @param keepDims Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public fun <T : TType> reduceSum(
        input: Operand<T>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): ReduceSum<T> = java.reduceSum<T>(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.core.ReduceSum.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Makes its input available to the next iteration.
     *
     * @param <T> data type for `output` output
     * @param data The tensor to be made available to the next iteration.
     * @param <T> data type for `RefNextIteration` output and operands
     * @return a new instance of RefNextIteration
     * @see org.tensorflow.op.Ops.refNextIteration
     */
    public fun <T : TType> refNextIteration(`data`: Operand<T>): RefNextIteration<T> =
            java.refNextIteration<T>(    
        data
        )

    /**
     * Forwards the `index`th element of `inputs` to `output`.
     *
     * @param <T> data type for `output` output
     * @param index A scalar that determines the input that gets selected.
     * @param inputs A list of ref tensors, one of which will be forwarded to `output`.
     * @param <T> data type for `RefSelect` output and operands
     * @return a new instance of RefSelect
     * @see org.tensorflow.op.Ops.refSelect
     */
    public fun <T : TType> refSelect(index: Operand<TInt32>, inputs: Iterable<Operand<T>>):
            RefSelect<T> = java.refSelect<T>(    
        index,
        inputs
        )

    /**
     * Forwards the ref tensor `data` to the output port determined by `pred`.
     *  If `pred` is true, the `data` input is forwarded to `output_true`. Otherwise,
     *  the data goes to `output_false`.
     *  
     * See also `Switch` and `Merge`.
     *
     * @param <T> data type for `output_false` output
     * @param data The ref tensor to be forwarded to the appropriate output.
     * @param pred A scalar that specifies which output port will receive data.
     * @param <T> data type for `RefSwitch` output and operands
     * @return a new instance of RefSwitch
     * @see org.tensorflow.op.Ops.refSwitch
     */
    public fun <T : TType> refSwitch(`data`: Operand<T>, pred: Operand<TBool>): RefSwitch<T> =
            java.refSwitch<T>(    
        data,
        pred
        )

    /**
     * Runs function `f` on a remote device indicated by `target`.
     *
     * @param target A fully specified device name where we want to run the function.
     * @param args A list of arguments for the function.
     * @param Tout The type list for the return values.
     * @param f The function to run remotely.
     * @return a new instance of RemoteCall
     * @see org.tensorflow.op.Ops.remoteCall
     */
    public fun remoteCall(
        target: Operand<TString>,
        args: Iterable<Operand<*>>,
        Tout: List<Class<out TType>>,
        f: ConcreteFunction
    ): RemoteCall = java.remoteCall(    
        target,
        args,
        Tout,
        f
        )

    /**
     * Reshapes a tensor.
     *  Given `tensor`, this operation returns a tensor that has the same values
     *  as `tensor` with shape `shape`.
     *  
     * If one component of 1-D tensor `shape` is the special value -1, the size of that
     *  dimension is computed so that the total size remains constant.  In particular, a
     *  `shape` of `&#91;-1&#93;` flattens into 1-D.  At most one component of `shape` may be
     *  unknown.
     *  
     * The `shape` must be 1-D and the operation returns a tensor with shape
     *  `shape` filled with the values of `tensor`. In this case, the number of elements
     *  implied by `shape` must be the same as the number of elements in `tensor`.
     *  
     * It is an error if `shape` is not 1-D.
     *  
     * For example:
     *  ```
     * # tensor 't' is [1, 2, 3, 4, 5, 6, 7, 8, 9]
     *  # tensor 't' has shape [9]
     *  reshape(t, [3, 3]) ==> [[1, 2, 3],
     *                          [4, 5, 6],
     *                          [7, 8, 9]]
     *
     *  # tensor 't' is [[[1, 1], [2, 2]],
     *  #                [[3, 3], [4, 4]]]
     *  # tensor 't' has shape [2, 2, 2]
     *  reshape(t, [2, 4]) ==> [[1, 1, 2, 2],
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
     *  reshape(t, [-1]) ==> [1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6]
     *
     *  # -1 can also be used to infer the shape
     *
     *  # -1 is inferred to be 9:
     *  reshape(t, [2, -1]) ==> [[1, 1, 1, 2, 2, 2, 3, 3, 3],
     *                           [4, 4, 4, 5, 5, 5, 6, 6, 6]]
     *  # -1 is inferred to be 2:
     *  reshape(t, [-1, 9]) ==> [[1, 1, 1, 2, 2, 2, 3, 3, 3],
     *                           [4, 4, 4, 5, 5, 5, 6, 6, 6]]
     *  # -1 is inferred to be 3:
     *  reshape(t, [ 2, -1, 3]) ==> [[[1, 1, 1],
     *                                [2, 2, 2],
     *                                [3, 3, 3]],
     *                               [[4, 4, 4],
     *                                [5, 5, 5],
     *                                [6, 6, 6]]]
     *
     *  # tensor 't' is [7]
     *  # shape `[]` reshapes to a scalar
     *  reshape(t, []) ==> 7
     *  
     * ```
     *
     * @param <T> data type for `output` output
     * @param tensor The tensor value
     * @param shape Defines the shape of the output tensor.
     * @param <T> data type for `Reshape` output and operands
     * @return a new instance of Reshape
     * @see org.tensorflow.op.Ops.reshape
     */
    public fun <T : TType> reshape(tensor: Operand<T>, shape: Operand<out TNumber>): Reshape<T> =
            java.reshape<T>(    
        tensor,
        shape
        )

    /**
     * Increments variable pointed to by 'resource' until it reaches 'limit'.
     *
     * @param <T> data type for `output` output
     * @param resource Should be from a scalar `Variable` node.
     * @param limit If incrementing ref would bring it above limit, instead generates an
     *  'OutOfRange' error.
     * @param T The value of the T attribute
     * @param <T> data type for `ResourceCountUpTo` output and operands
     * @return a new instance of ResourceCountUpTo
     * @see org.tensorflow.op.Ops.resourceCountUpTo
     */
    public fun <T : TNumber> resourceCountUpTo(
        resource: Operand<out TType>,
        limit: Long,
        T_: Class<T>
    ): ResourceCountUpTo<T> = java.resourceCountUpTo<T>(    
        resource,
        limit,
        T_
        )

    /**
     * Gather slices from the variable pointed to by `resource` according to `indices`.
     *  `indices` must be an integer tensor of any dimension (usually 0-D or 1-D).
     *  Produces an output tensor with shape `indices.shape + params.shape&#91;1:&#93;` where:
     *  ```
     * # Scalar indices
     *      output[:, ..., :] = params[indices, :, ... :]
     *
     *      # Vector indices
     *      output[i, :, ..., :] = params[indices[i], :, ... :]
     *
     *      # Higher rank indices
     *      output[i, ..., j, :, ... :] = params[indices[i, ..., j], :, ..., :]
     *  
     * ```
     *
     * @param <U> data type for `output` output
     * @param resource The resource value
     * @param indices The indices value
     * @param dtype The value of the dtype attribute
     * @param options carries optional attribute values
     * @param <U> data type for `ResourceGather` output and operands
     * @return a new instance of ResourceGather
     * @see org.tensorflow.op.Ops.resourceGather
     * @param batchDims Sets the batchDims option.
     *
     * @param batchDims the batchDims option
     * @return this Options instance.
     * @param validateIndices Sets the validateIndices option.
     *
     * @param validateIndices the validateIndices option
     * @return this Options instance.
     */
    public fun <U : TType> resourceGather(
        resource: Operand<out TType>,
        indices: Operand<out TNumber>,
        dtype: Class<U>,
        batchDims: Long? = null,
        validateIndices: Boolean? = null
    ): ResourceGather<U> = java.resourceGather<U>(    
        resource,
        indices,
        dtype,
        *listOfNotNull(
            batchDims?.let{ org.tensorflow.op.core.ResourceGather.batchDims(it) },
            validateIndices?.let{ org.tensorflow.op.core.ResourceGather.validateIndices(it) }
        ).toTypedArray()
        )

    /**
     * The ResourceGatherNd operation
     *
     * @param <U> data type for `output` output
     * @param resource The resource value
     * @param indices The indices value
     * @param dtype The value of the dtype attribute
     * @param <U> data type for `ResourceGatherNd` output and operands
     * @return a new instance of ResourceGatherNd
     * @see org.tensorflow.op.Ops.resourceGatherNd
     */
    public fun <U : TType> resourceGatherNd(
        resource: Operand<out TType>,
        indices: Operand<out TNumber>,
        dtype: Class<U>
    ): ResourceGatherNd<U> = java.resourceGatherNd<U>(    
        resource,
        indices,
        dtype
        )

    /**
     * Adds sparse updates to the variable referenced by `resource`.
     *  This operation computes
     *  ```
     * # Scalar indices
     *  ref[indices, ...] += updates[...]
     *
     *  # Vector indices (for each i)
     *  ref[indices[i], ...] += updates[i, ...]
     *
     *  # High rank indices (for each i, ..., j)
     *  ref[indices[i, ..., j], ...] += updates[i, ..., j, ...]
     *  
     * ```
     *  
     * Duplicate entries are handled correctly: if multiple `indices` reference
     *  the same location, their contributions add.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
     *  </div>
     *
     * @param resource Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to add to `ref`.
     * @return a new instance of ResourceScatterAdd
     * @see org.tensorflow.op.Ops.resourceScatterAdd
     */
    public fun resourceScatterAdd(
        resource: Operand<out TType>,
        indices: Operand<out TNumber>,
        updates: Operand<out TType>
    ): ResourceScatterAdd = java.resourceScatterAdd(    
        resource,
        indices,
        updates
        )

    /**
     * Divides sparse updates into the variable referenced by `resource`.
     *  This operation computes
     *  ```
     * # Scalar indices
     *  ref[indices, ...] /= updates[...]
     *
     *  # Vector indices (for each i)
     *  ref[indices[i], ...] /= updates[i, ...]
     *
     *  # High rank indices (for each i, ..., j)
     *  ref[indices[i, ..., j], ...] /= updates[i, ..., j, ...]
     *  
     * ```
     *  
     * Duplicate entries are handled correctly: if multiple `indices` reference
     *  the same location, their contributions multiply.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
     *  </div>
     *
     * @param resource Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to add to `ref`.
     * @return a new instance of ResourceScatterDiv
     * @see org.tensorflow.op.Ops.resourceScatterDiv
     */
    public fun resourceScatterDiv(
        resource: Operand<out TType>,
        indices: Operand<out TNumber>,
        updates: Operand<out TType>
    ): ResourceScatterDiv = java.resourceScatterDiv(    
        resource,
        indices,
        updates
        )

    /**
     * Reduces sparse updates into the variable referenced by `resource` using the `max` operation.
     *  This operation computes
     *  ```
     * # Scalar indices
     *  ref[indices, ...] = max(ref[indices, ...], updates[...])
     *
     *  # Vector indices (for each i)
     *  ref[indices[i], ...] = max(ref[indices[i], ...], updates[i, ...])
     *
     *  # High rank indices (for each i, ..., j)
     *  ref[indices[i, ..., j], ...] = max(ref[indices[i, ..., j], ...], updates[i, ..., j, ...])
     *  
     * ```
     *  
     * Duplicate entries are handled correctly: if multiple `indices` reference
     *  the same location, their contributions are combined.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
     *  </div>
     *
     * @param resource Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to add to `ref`.
     * @return a new instance of ResourceScatterMax
     * @see org.tensorflow.op.Ops.resourceScatterMax
     */
    public fun resourceScatterMax(
        resource: Operand<out TType>,
        indices: Operand<out TNumber>,
        updates: Operand<out TType>
    ): ResourceScatterMax = java.resourceScatterMax(    
        resource,
        indices,
        updates
        )

    /**
     * Reduces sparse updates into the variable referenced by `resource` using the `min` operation.
     *  This operation computes
     *  ```
     * # Scalar indices
     *  ref[indices, ...] = min(ref[indices, ...], updates[...])
     *
     *  # Vector indices (for each i)
     *  ref[indices[i], ...] = min(ref[indices[i], ...], updates[i, ...])
     *
     *  # High rank indices (for each i, ..., j)
     *  ref[indices[i, ..., j], ...] = min(ref[indices[i, ..., j], ...], updates[i, ..., j, ...])
     *  
     * ```
     *  
     * Duplicate entries are handled correctly: if multiple `indices` reference
     *  the same location, their contributions are combined.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
     *  </div>
     *
     * @param resource Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to add to `ref`.
     * @return a new instance of ResourceScatterMin
     * @see org.tensorflow.op.Ops.resourceScatterMin
     */
    public fun resourceScatterMin(
        resource: Operand<out TType>,
        indices: Operand<out TNumber>,
        updates: Operand<out TType>
    ): ResourceScatterMin = java.resourceScatterMin(    
        resource,
        indices,
        updates
        )

    /**
     * Multiplies sparse updates into the variable referenced by `resource`.
     *  This operation computes
     *  ```
     * # Scalar indices
     *  ref[indices, ...] *= updates[...]
     *
     *  # Vector indices (for each i)
     *  ref[indices[i], ...] *= updates[i, ...]
     *
     *  # High rank indices (for each i, ..., j)
     *  ref[indices[i, ..., j], ...] *= updates[i, ..., j, ...]
     *  
     * ```
     *  
     * Duplicate entries are handled correctly: if multiple `indices` reference
     *  the same location, their contributions multiply.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
     *  </div>
     *
     * @param resource Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to add to `ref`.
     * @return a new instance of ResourceScatterMul
     * @see org.tensorflow.op.Ops.resourceScatterMul
     */
    public fun resourceScatterMul(
        resource: Operand<out TType>,
        indices: Operand<out TNumber>,
        updates: Operand<out TType>
    ): ResourceScatterMul = java.resourceScatterMul(    
        resource,
        indices,
        updates
        )

    /**
     * Applies sparse addition to individual values or slices in a Variable.
     *  `ref` is a `Tensor` with rank `P` and `indices` is a `Tensor` of rank `Q`.
     *  
     * `indices` must be integer tensor, containing indices into `ref`.
     *  It must be shape `[d_0, ..., d_{Q-2`, K]} where `0 < K <= P`.
     *  
     * The innermost dimension of `indices` (with length `K`) corresponds to
     *  indices into elements (if `K = P`) or slices (if `K < P`) along the `K`th
     *  dimension of `ref`.
     *  
     * `updates` is `Tensor` of rank `Q-1+P-K` with shape:
     *  `[d_0, ..., d_{Q-2`, ref.shape[K], ..., ref.shape&#91;P-1&#93;]
     *  }
     *  
     * For example, say we want to add 4 scattered elements to a rank-1 tensor to
     *  8 elements. In Python, that addition would look like this:
     *  ```
     * ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8], use_resource=True)
     *  indices = tf.constant([[4], [3], [1], [7]])
     *  updates = tf.constant([9, 10, 11, 12])
     *  add = tf.scatter_nd_add(ref, indices, updates)
     *  with tf.Session() as sess:
     *    print sess.run(add)
     *  
     * ```
     *  
     * The resulting update to ref would look like this:
     *  ```
     * [1, 13, 3, 14, 14, 6, 7, 20]
     *  
     * ```
     *  
     * See `tf.scatter_nd` for more details about how to make updates to
     *  slices.
     *
     * @param ref A resource handle. Must be from a VarHandleOp.
     * @param indices A Tensor. Must be one of the following types: int32, int64.
     *  A tensor of indices into ref.
     * @param updates A Tensor. Must have the same type as ref. A tensor of
     *  values to add to ref.
     * @param options carries optional attribute values
     * @return a new instance of ResourceScatterNdAdd
     * @see org.tensorflow.op.Ops.resourceScatterNdAdd
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking An optional bool. Defaults to True. If True, the assignment will
     *  be protected by a lock; otherwise the behavior is undefined,
     *  but may exhibit less contention.
     * @return this Options instance.
     */
    public fun resourceScatterNdAdd(
        ref: Operand<out TType>,
        indices: Operand<out TNumber>,
        updates: Operand<out TType>,
        useLocking: Boolean? = null
    ): ResourceScatterNdAdd = java.resourceScatterNdAdd(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ResourceScatterNdAdd.useLocking(it) }
        ).toTypedArray()
        )

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
     * @see org.tensorflow.op.Ops.resourceScatterNdMax
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking An optional bool. Defaults to True. If True, the assignment will
     *  be protected by a lock; otherwise the behavior is undefined,
     *  but may exhibit less contention.
     * @return this Options instance.
     */
    public fun resourceScatterNdMax(
        ref: Operand<out TType>,
        indices: Operand<out TNumber>,
        updates: Operand<out TType>,
        useLocking: Boolean? = null
    ): ResourceScatterNdMax = java.resourceScatterNdMax(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ResourceScatterNdMax.useLocking(it) }
        ).toTypedArray()
        )

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
     * @see org.tensorflow.op.Ops.resourceScatterNdMin
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking An optional bool. Defaults to True. If True, the assignment will
     *  be protected by a lock; otherwise the behavior is undefined,
     *  but may exhibit less contention.
     * @return this Options instance.
     */
    public fun resourceScatterNdMin(
        ref: Operand<out TType>,
        indices: Operand<out TNumber>,
        updates: Operand<out TType>,
        useLocking: Boolean? = null
    ): ResourceScatterNdMin = java.resourceScatterNdMin(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ResourceScatterNdMin.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * Applies sparse subtraction to individual values or slices in a Variable.
     *  `ref` is a `Tensor` with rank `P` and `indices` is a `Tensor` of rank `Q`.
     *  
     * `indices` must be integer tensor, containing indices into `ref`.
     *  It must be shape `[d_0, ..., d_{Q-2`, K]} where `0 < K <= P`.
     *  
     * The innermost dimension of `indices` (with length `K`) corresponds to
     *  indices into elements (if `K = P`) or slices (if `K < P`) along the `K`th
     *  dimension of `ref`.
     *  
     * `updates` is `Tensor` of rank `Q-1+P-K` with shape:
     *  `[d_0, ..., d_{Q-2`, ref.shape[K], ..., ref.shape&#91;P-1&#93;]
     *  }
     *  
     * For example, say we want to subtract 4 scattered elements from a rank-1 tensor
     *  with 8 elements. In Python, that subtraction would look like this:
     *  ```
     * ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8], use_resource=True)
     *  indices = tf.constant([[4], [3], [1], [7]])
     *  updates = tf.constant([9, 10, 11, 12])
     *  sub = tf.scatter_nd_sub(ref, indices, updates)
     *  with tf.Session() as sess:
     *    print sess.run(sub)
     *  
     * ```
     *  
     * The resulting update to ref would look like this:
     *  ```
     * [1, -9, 3, -6, -4, 6, 7, -4]
     *  
     * ```
     *  
     * See `tf.scatter_nd` for more details about how to make updates to
     *  slices.
     *
     * @param ref A resource handle. Must be from a VarHandleOp.
     * @param indices A Tensor. Must be one of the following types: int32, int64.
     *  A tensor of indices into ref.
     * @param updates A Tensor. Must have the same type as ref. A tensor of
     *  values to add to ref.
     * @param options carries optional attribute values
     * @return a new instance of ResourceScatterNdSub
     * @see org.tensorflow.op.Ops.resourceScatterNdSub
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking An optional bool. Defaults to True. If True, the assignment will
     *  be protected by a lock; otherwise the behavior is undefined,
     *  but may exhibit less contention.
     * @return this Options instance.
     */
    public fun resourceScatterNdSub(
        ref: Operand<out TType>,
        indices: Operand<out TNumber>,
        updates: Operand<out TType>,
        useLocking: Boolean? = null
    ): ResourceScatterNdSub = java.resourceScatterNdSub(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ResourceScatterNdSub.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * Applies sparse `updates` to individual values or slices within a given
     *  variable according to `indices`.
     *  
     * `ref` is a `Tensor` with rank `P` and `indices` is a `Tensor` of rank `Q`.
     *  
     * `indices` must be integer tensor, containing indices into `ref`.
     *  It must be shape `[d_0, ..., d_{Q-2`, K]} where `0 < K <= P`.
     *  
     * The innermost dimension of `indices` (with length `K`) corresponds to
     *  indices into elements (if `K = P`) or slices (if `K < P`) along the `K`th
     *  dimension of `ref`.
     *  
     * `updates` is `Tensor` of rank `Q-1+P-K` with shape:
     *  `[d_0, ..., d_{Q-2`, ref.shape[K], ..., ref.shape&#91;P-1&#93;].
     *  }
     *  
     * For example, say we want to update 4 scattered elements to a rank-1 tensor to
     *  8 elements. In Python, that update would look like this:
     *  ```
     * ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8])
     *      indices = tf.constant([[4], [3], [1] ,[7]])
     *      updates = tf.constant([9, 10, 11, 12])
     *      update = tf.scatter_nd_update(ref, indices, updates)
     *      with tf.Session() as sess:
     *        print sess.run(update)
     *  
     * ```
     *  
     * The resulting update to ref would look like this:
     *  ```
     * [1, 11, 3, 10, 9, 6, 7, 12]
     *  
     * ```
     *  
     * See `tf.scatter_nd` for more details about how to make updates to
     *  slices.
     *
     * @param ref A resource handle. Must be from a VarHandleOp.
     * @param indices A Tensor. Must be one of the following types: int32, int64.
     *  A tensor of indices into ref.
     * @param updates A Tensor. Must have the same type as ref. A tensor of updated
     *  values to add to ref.
     * @param options carries optional attribute values
     * @return a new instance of ResourceScatterNdUpdate
     * @see org.tensorflow.op.Ops.resourceScatterNdUpdate
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking An optional bool. Defaults to True. If True, the assignment will
     *  be protected by a lock; otherwise the behavior is undefined,
     *  but may exhibit less contention.
     * @return this Options instance.
     */
    public fun resourceScatterNdUpdate(
        ref: Operand<out TType>,
        indices: Operand<out TNumber>,
        updates: Operand<out TType>,
        useLocking: Boolean? = null
    ): ResourceScatterNdUpdate = java.resourceScatterNdUpdate(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ResourceScatterNdUpdate.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * Subtracts sparse updates from the variable referenced by `resource`.
     *  This operation computes
     *  ```
     * # Scalar indices
     *  ref[indices, ...] -= updates[...]
     *
     *  # Vector indices (for each i)
     *  ref[indices[i], ...] -= updates[i, ...]
     *
     *  # High rank indices (for each i, ..., j)
     *  ref[indices[i, ..., j], ...] -= updates[i, ..., j, ...]
     *  
     * ```
     *  
     * Duplicate entries are handled correctly: if multiple `indices` reference
     *  the same location, their contributions add.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src='https://www.tensorflow.org/images/ScatterAdd.png' alt>
     *  </div>
     *
     * @param resource Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to add to `ref`.
     * @return a new instance of ResourceScatterSub
     * @see org.tensorflow.op.Ops.resourceScatterSub
     */
    public fun resourceScatterSub(
        resource: Operand<out TType>,
        indices: Operand<out TNumber>,
        updates: Operand<out TType>
    ): ResourceScatterSub = java.resourceScatterSub(    
        resource,
        indices,
        updates
        )

    /**
     * Assigns sparse updates to the variable referenced by `resource`.
     *  This operation computes
     *  ```
     * # Scalar indices
     *  ref[indices, ...] = updates[...]
     *
     *  # Vector indices (for each i)
     *  ref[indices[i], ...] = updates[i, ...]
     *
     *  # High rank indices (for each i, ..., j)
     *  ref[indices[i, ..., j], ...] = updates[i, ..., j, ...]
     *  
     * ```
     *
     * @param resource Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to add to `ref`.
     * @return a new instance of ResourceScatterUpdate
     * @see org.tensorflow.op.Ops.resourceScatterUpdate
     */
    public fun resourceScatterUpdate(
        resource: Operand<out TType>,
        indices: Operand<out TNumber>,
        updates: Operand<out TType>
    ): ResourceScatterUpdate = java.resourceScatterUpdate(    
        resource,
        indices,
        updates
        )

    /**
     * Assign `value` to the sliced l-value reference of `ref`.
     *  The values of `value` are assigned to the positions in the variable
     *  `ref` that are selected by the slice parameters. The slice parameters
     *  `begin, `end`, `strides`, etc. work exactly as in `StridedSlice`.
     *  
     * NOTE this op currently does not support broadcasting and so `value`'s
     *  shape must be exactly the shape produced by the slice of `ref`.
     *
     * @param ref The ref value
     * @param begin The begin value
     * @param end The end value
     * @param strides The strides value
     * @param value The value value
     * @param options carries optional attribute values
     * @param <T> data type for `ResourceStridedSliceAssign` output and operands
     * @return a new instance of ResourceStridedSliceAssign
     * @see org.tensorflow.op.Ops.resourceStridedSliceAssign
     * @param beginMask Sets the beginMask option.
     *
     * @param beginMask the beginMask option
     * @return this Options instance.
     * @param endMask Sets the endMask option.
     *
     * @param endMask the endMask option
     * @return this Options instance.
     * @param ellipsisMask Sets the ellipsisMask option.
     *
     * @param ellipsisMask the ellipsisMask option
     * @return this Options instance.
     * @param newAxisMask Sets the newAxisMask option.
     *
     * @param newAxisMask the newAxisMask option
     * @return this Options instance.
     * @param shrinkAxisMask Sets the shrinkAxisMask option.
     *
     * @param shrinkAxisMask the shrinkAxisMask option
     * @return this Options instance.
     */
    public fun <T : TNumber> resourceStridedSliceAssign(
        ref: Operand<out TType>,
        begin: Operand<T>,
        end: Operand<T>,
        strides: Operand<T>,
        value: Operand<out TType>,
        beginMask: Long? = null,
        endMask: Long? = null,
        ellipsisMask: Long? = null,
        newAxisMask: Long? = null,
        shrinkAxisMask: Long? = null
    ): ResourceStridedSliceAssign = java.resourceStridedSliceAssign<T>(    
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

    /**
     * Reverses specific dimensions of a tensor.
     *  Given a `tensor`, and a `int32` tensor `axis` representing the set of
     *  dimensions of `tensor` to reverse. This operation reverses each dimension
     *  `i` for which there exists `j` s.t. `axis[j] == i`.
     *  
     * `tensor` can have up to 8 dimensions. The number of dimensions specified
     *  in `axis` may be 0 or more entries. If an index is specified more than
     *  once, a InvalidArgument error is raised.
     *  
     * For example:
     *  ```
     * # tensor 't' is [[[[ 0,  1,  2,  3],
     *  #                  [ 4,  5,  6,  7],
     *  #                  [ 8,  9, 10, 11]],
     *  #                 [[12, 13, 14, 15],
     *  #                  [16, 17, 18, 19],
     *  #                  [20, 21, 22, 23]]]]
     *  # tensor 't' shape is [1, 2, 3, 4]
     *
     *  # 'dims' is [3] or 'dims' is [-1]
     *  reverse(t, dims) ==> [[[[ 3,  2,  1,  0],
     *                          [ 7,  6,  5,  4],
     *                          [ 11, 10, 9, 8]],
     *                         [[15, 14, 13, 12],
     *                          [19, 18, 17, 16],
     *                          [23, 22, 21, 20]]]]
     *
     *  # 'dims' is '[1]' (or 'dims' is '[-3]')
     *  reverse(t, dims) ==> [[[[12, 13, 14, 15],
     *                          [16, 17, 18, 19],
     *                          [20, 21, 22, 23]
     *                         [[ 0,  1,  2,  3],
     *                          [ 4,  5,  6,  7],
     *                          [ 8,  9, 10, 11]]]]
     *
     *  # 'dims' is '[2]' (or 'dims' is '[-2]')
     *  reverse(t, dims) ==> [[[[8, 9, 10, 11],
     *                          [4, 5, 6, 7],
     *                          [0, 1, 2, 3]]
     *                         [[20, 21, 22, 23],
     *                          [16, 17, 18, 19],
     *                          [12, 13, 14, 15]]]]
     *  
     * ```
     *
     * @param <T> data type for `output` output
     * @param tensor Up to 8-D.
     * @param axis 1-D. The indices of the dimensions to reverse. Must be in the range
     *  `[-rank(tensor), rank(tensor))`.
     * @param <T> data type for `ReverseV2` output and operands
     * @return a new instance of Reverse
     * @see org.tensorflow.op.Ops.reverse
     */
    public fun <T : TType> reverse(tensor: Operand<T>, axis: Operand<out TNumber>): Reverse<T> =
            java.reverse<T>(    
        tensor,
        axis
        )

    /**
     * Reverses variable length slices.
     *  This op first slices `input` along the dimension `batch_dim`, and for each
     *  slice `i`, reverses the first `seq_lengths[i]` elements along
     *  the dimension `seq_dim`.
     *  
     * The elements of `seq_lengths` must obey `seq_lengths[i] <= input.dims&#91;seq_dim&#93;`,
     *  and `seq_lengths` must be a vector of length `input.dims&#91;batch_dim&#93;`.
     *  
     * The output slice `i` along dimension `batch_dim` is then given by input
     *  slice `i`, with the first `seq_lengths[i]` slices along dimension
     *  `seq_dim` reversed.
     *  
     * For example:
     *  ```
     * # Given this:
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
     *  
     * ```
     *  
     * In contrast, if:
     *  ```
     * # Given this:
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
     *  
     * ```
     *
     * @param <T> data type for `output` output
     * @param input The input to reverse.
     * @param seqLengths 1-D with length `input.dims(batch_dim)` and
     *  `max(seq_lengths) <= input.dims(seq_dim)`
     * @param seqDim The dimension which is partially reversed.
     * @param options carries optional attribute values
     * @param <T> data type for `ReverseSequence` output and operands
     * @return a new instance of ReverseSequence
     * @see org.tensorflow.op.Ops.reverseSequence
     * @param batchDim Sets the batchDim option.
     *
     * @param batchDim The dimension along which reversal is performed.
     * @return this Options instance.
     */
    public fun <T : TType> reverseSequence(
        input: Operand<T>,
        seqLengths: Operand<out TNumber>,
        seqDim: Long,
        batchDim: Long? = null
    ): ReverseSequence<T> = java.reverseSequence<T>(    
        input,
        seqLengths,
        seqDim,
        *listOfNotNull(
            batchDim?.let{ org.tensorflow.op.core.ReverseSequence.batchDim(it) }
        ).toTypedArray()
        )

    /**
     * Rolls the elements of a tensor along an axis.
     *  The elements are shifted positively (towards larger indices) by the offset of
     *  `shift` along the dimension of `axis`. Negative `shift` values will shift
     *  elements in the opposite direction. Elements that roll passed the last position
     *  will wrap around to the first and vice versa. Multiple shifts along multiple
     *  axes may be specified.
     *  
     * For example:
     *  ```
     * # 't' is [0, 1, 2, 3, 4]
     *  roll(t, shift=2, axis=0) ==> [3, 4, 0, 1, 2]
     *
     *  # shifting along multiple dimensions
     *  # 't' is [[0, 1, 2, 3, 4], [5, 6, 7, 8, 9]]
     *  roll(t, shift=[1, -2], axis=[0, 1]) ==> [[7, 8, 9, 5, 6], [2, 3, 4, 0, 1]]
     *
     *  # shifting along the same axis multiple times
     *  # 't' is [[0, 1, 2, 3, 4], [5, 6, 7, 8, 9]]
     *  roll(t, shift=[2, -3], axis=[1, 1]) ==> [[1, 2, 3, 4, 0], [6, 7, 8, 9, 5]]
     *  
     * ```
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param shift Dimension must be 0-D or 1-D. `shift[i]` specifies the number of places by which
     *  elements are shifted positively (towards larger indices) along the dimension
     *  specified by `axis[i]`. Negative shifts will roll the elements in the opposite
     *  direction.
     * @param axis Dimension must be 0-D or 1-D. `axis[i]` specifies the dimension that the shift
     *  `shift[i]` should occur. If the same axis is referenced more than once, the
     *  total shift for that axis will be the sum of all the shifts that belong to that
     *  axis.
     * @param <T> data type for `Roll` output and operands
     * @return a new instance of Roll
     * @see org.tensorflow.op.Ops.roll
     */
    public fun <T : TType> roll(
        input: Operand<T>,
        shift: Operand<out TNumber>,
        axis: Operand<out TNumber>
    ): Roll<T> = java.roll<T>(    
        input,
        shift,
        axis
        )

    /**
     * Adds sparse updates to a variable reference.
     *  This operation computes
     *  ```
     * # Scalar indices
     *  ref[indices, ...] += updates[...]
     *
     *  # Vector indices (for each i)
     *  ref[indices[i], ...] += updates[i, ...]
     *
     *  # High rank indices (for each i, ..., j)
     *  ref[indices[i, ..., j], ...] += updates[i, ..., j, ...]
     *  
     * ```
     *  
     * This operation outputs `ref` after the update is done.
     *  This makes it easier to chain operations that need to use the reset value.
     *  
     * Duplicate entries are handled correctly: if multiple `indices` reference
     *  the same location, their contributions add.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterAdd.png" alt>
     *  </div>
     *
     * @param <T> data type for `output_ref` output
     * @param ref Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to add to `ref`.
     * @param options carries optional attribute values
     * @param <T> data type for `ScatterAdd` output and operands
     * @return a new instance of ScatterAdd
     * @see org.tensorflow.op.Ops.scatterAdd
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking If True, the addition will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
    public fun <T : TType> scatterAdd(
        ref: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>,
        useLocking: Boolean? = null
    ): ScatterAdd<T> = java.scatterAdd<T>(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ScatterAdd.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * Divides a variable reference by sparse updates.
     *  This operation computes
     *  ```
     * # Scalar indices
     *      ref[indices, ...] /= updates[...]
     *
     *      # Vector indices (for each i)
     *      ref[indices[i], ...] /= updates[i, ...]
     *
     *      # High rank indices (for each i, ..., j)
     *      ref[indices[i, ..., j], ...] /= updates[i, ..., j, ...]
     *  
     * ```
     *  
     * This operation outputs `ref` after the update is done.
     *  This makes it easier to chain operations that need to use the reset value.
     *  
     * Duplicate entries are handled correctly: if multiple `indices` reference
     *  the same location, their contributions divide.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *
     * @param <T> data type for `output_ref` output
     * @param ref Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of values that `ref` is divided by.
     * @param options carries optional attribute values
     * @param <T> data type for `ScatterDiv` output and operands
     * @return a new instance of ScatterDiv
     * @see org.tensorflow.op.Ops.scatterDiv
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking If True, the operation will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
    public fun <T : TType> scatterDiv(
        ref: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>,
        useLocking: Boolean? = null
    ): ScatterDiv<T> = java.scatterDiv<T>(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ScatterDiv.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * Reduces sparse updates into a variable reference using the `max` operation.
     *  This operation computes
     *  ```
     * # Scalar indices
     *  ref[indices, ...] = max(ref[indices, ...], updates[...])
     *
     *  # Vector indices (for each i)
     *  ref[indices[i], ...] = max(ref[indices[i], ...], updates[i, ...])
     *
     *  # High rank indices (for each i, ..., j)
     *  ref[indices[i, ..., j], ...] = max(ref[indices[i, ..., j], ...], updates[i, ..., j, ...])
     *  
     * ```
     *  
     * This operation outputs `ref` after the update is done.
     *  This makes it easier to chain operations that need to use the reset value.
     *  
     * Duplicate entries are handled correctly: if multiple `indices` reference
     *  the same location, their contributions combine.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterAdd.png" alt>
     *  </div>
     *
     * @param <T> data type for `output_ref` output
     * @param ref Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to reduce into `ref`.
     * @param options carries optional attribute values
     * @param <T> data type for `ScatterMax` output and operands
     * @return a new instance of ScatterMax
     * @see org.tensorflow.op.Ops.scatterMax
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking If True, the update will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
    public fun <T : TNumber> scatterMax(
        ref: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>,
        useLocking: Boolean? = null
    ): ScatterMax<T> = java.scatterMax<T>(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ScatterMax.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * Reduces sparse updates into a variable reference using the `min` operation.
     *  This operation computes
     *  ```
     * # Scalar indices
     *  ref[indices, ...] = min(ref[indices, ...], updates[...])
     *
     *  # Vector indices (for each i)
     *  ref[indices[i], ...] = min(ref[indices[i], ...], updates[i, ...])
     *
     *  # High rank indices (for each i, ..., j)
     *  ref[indices[i, ..., j], ...] = min(ref[indices[i, ..., j], ...], updates[i, ..., j, ...])
     *  
     * ```
     *  
     * This operation outputs `ref` after the update is done.
     *  This makes it easier to chain operations that need to use the reset value.
     *  
     * Duplicate entries are handled correctly: if multiple `indices` reference
     *  the same location, their contributions combine.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterAdd.png" alt>
     *  </div>
     *
     * @param <T> data type for `output_ref` output
     * @param ref Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to reduce into `ref`.
     * @param options carries optional attribute values
     * @param <T> data type for `ScatterMin` output and operands
     * @return a new instance of ScatterMin
     * @see org.tensorflow.op.Ops.scatterMin
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking If True, the update will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
    public fun <T : TNumber> scatterMin(
        ref: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>,
        useLocking: Boolean? = null
    ): ScatterMin<T> = java.scatterMin<T>(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ScatterMin.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * Multiplies sparse updates into a variable reference.
     *  This operation computes
     *  ```
     * # Scalar indices
     *      ref[indices, ...] *= updates[...]
     *
     *      # Vector indices (for each i)
     *      ref[indices[i], ...] *= updates[i, ...]
     *
     *      # High rank indices (for each i, ..., j)
     *      ref[indices[i, ..., j], ...] *= updates[i, ..., j, ...]
     *  
     * ```
     *  
     * This operation outputs `ref` after the update is done.
     *  This makes it easier to chain operations that need to use the reset value.
     *  
     * Duplicate entries are handled correctly: if multiple `indices` reference
     *  the same location, their contributions multiply.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *
     * @param <T> data type for `output_ref` output
     * @param ref Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to multiply to `ref`.
     * @param options carries optional attribute values
     * @param <T> data type for `ScatterMul` output and operands
     * @return a new instance of ScatterMul
     * @see org.tensorflow.op.Ops.scatterMul
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking If True, the operation will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
    public fun <T : TType> scatterMul(
        ref: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>,
        useLocking: Boolean? = null
    ): ScatterMul<T> = java.scatterMul<T>(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ScatterMul.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * Scatters `updates` into a tensor of shape `shape` according to `indices`.
     *  Update the input tensor by scattering sparse `updates` according to individual values at the
     * specified `indices`.
     *  This op returns an `output` tensor with the `shape` you specify. This op is the
     *  inverse of the `tf.gather_nd` operator which extracts values or slices from a
     *  given tensor.
     *  
     * This operation is similar to `tf.tensor_scatter_add`, except that the tensor is
     *  zero-initialized. Calling `tf.scatter_nd(indices, values, shape)`
     *  is identical to calling
     *  `tf.tensor_scatter_add(tf.zeros(shape, values.dtype), indices, values)`.
     *  
     * If `indices` contains duplicates, the duplicate `values` are accumulated
     *  (summed).
     *  
     * **WARNING**: The order in which updates are applied is nondeterministic, so the
     *  output will be nondeterministic if `indices` contains duplicates;
     *  numbers summed in different order may yield different results because of some
     *  numerical approximation issues.
     *  
     * `indices` is an integer tensor of shape `shape`. The last dimension
     *  of `indices` can be at most the rank of `shape`:
     *  ```
     * indices.shape[-1] <= shape.rank
     *  
     * ```
     *  
     * The last dimension of `indices` corresponds to indices of elements
     *  (if `indices.shape&#91;-1&#93; = shape.rank`) or slices
     *  (if `indices.shape&#91;-1&#93; < shape.rank`) along dimension `indices.shape&#91;-1&#93;` of
     *  `shape`.
     *  
     * `updates` is a tensor with shape:
     *  ```
     * indices.shape[:-1] + shape[indices.shape[-1]:]
     *  
     * ```
     *  
     * The simplest form of the scatter op is to insert individual elements in
     *  a tensor by index. Consider an example where you want to insert 4 scattered
     *  elements in a rank-1 tensor with 8 elements.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterNd1.png" alt>
     *  </div>
     *  
     * In Python, this scatter operation would look like this:
     *  ```
     * indices = tf.constant([[4], [3], [1], [7]])
     *      updates = tf.constant([9, 10, 11, 12])
     *      shape = tf.constant([8])
     *      scatter = tf.scatter_nd(indices, updates, shape)
     *      print(scatter)
     *  
     * ```
     *  
     * The resulting tensor would look like this:
     *  ```
     * [0, 11, 0, 10, 9, 0, 0, 12]
     *  
     * ```
     *  
     * You can also insert entire slices of a higher rank tensor all at once. For
     *  example, you can insert two slices in the first dimension of a rank-3 tensor
     *  with two matrices of new values.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterNd2.png" alt>
     *  </div>
     *  
     * In Python, this scatter operation would look like this:
     *  ```
     * indices = tf.constant([[0], [2]])
     *      updates = tf.constant([[[5, 5, 5, 5], [6, 6, 6, 6],
     *                              [7, 7, 7, 7], [8, 8, 8, 8]],
     *                             [[5, 5, 5, 5], [6, 6, 6, 6],
     *                              [7, 7, 7, 7], [8, 8, 8, 8]]])
     *      shape = tf.constant([4, 4, 4])
     *      scatter = tf.scatter_nd(indices, updates, shape)
     *      print(scatter)
     *  
     * ```
     *  
     * The resulting tensor would look like this:
     *  ```
     * [[[5, 5, 5, 5], [6, 6, 6, 6], [7, 7, 7, 7], [8, 8, 8, 8]],
     *   [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]],
     *   [[5, 5, 5, 5], [6, 6, 6, 6], [7, 7, 7, 7], [8, 8, 8, 8]],
     *   [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]]
     *  
     * ```
     *  
     * Note that on CPU, if an out of bound index is found, an error is returned.
     *  On GPU, if an out of bound index is found, the index is ignored.
     *
     * @param <U> data type for `output` output
     * @param indices Tensor of indices.
     * @param updates Values to scatter into the output tensor.
     * @param shape 1-D. The shape of the output tensor.
     * @param <U> data type for `ScatterNd` output and operands
     * @param <T> data type for `ScatterNd` output and operands
     * @return a new instance of ScatterNd
     * @see org.tensorflow.op.Ops.scatterNd
     */
    public fun <U : TType, T : TNumber> scatterNd(
        indices: Operand<T>,
        updates: Operand<U>,
        shape: Operand<T>
    ): ScatterNd<U> = java.scatterNd<U, T>(    
        indices,
        updates,
        shape
        )

    /**
     * Applies sparse addition to individual values or slices in a Variable.
     *  `ref` is a `Tensor` with rank `P` and `indices` is a `Tensor` of rank `Q`.
     *  
     * `indices` must be integer tensor, containing indices into `ref`.
     *  It must be shape `[d_0, ..., d_{Q-2`, K]} where `0 < K <= P`.
     *  
     * The innermost dimension of `indices` (with length `K`) corresponds to
     *  indices into elements (if `K = P`) or slices (if `K < P`) along the `K`th
     *  dimension of `ref`.
     *  
     * `updates` is `Tensor` of rank `Q-1+P-K` with shape:
     *  `[d_0, ..., d_{Q-2`, ref.shape[K], ..., ref.shape&#91;P-1&#93;]
     *  }
     *  
     * For example, say we want to add 4 scattered elements to a rank-1 tensor to
     *  8 elements. In Python, that addition would look like this:
     *  ```
     * ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8])
     *  indices = tf.constant([[4], [3], [1], [7]])
     *  updates = tf.constant([9, 10, 11, 12])
     *  add = tf.scatter_nd_add(ref, indices, updates)
     *  with tf.Session() as sess:
     *    print sess.run(add)
     *  
     * ```
     *  
     * The resulting update to ref would look like this:
     *  ```
     * [1, 13, 3, 14, 14, 6, 7, 20]
     *  
     * ```
     *  
     * See `tf.scatter_nd` for more details about how to make updates to
     *  slices.
     *
     * @param <T> data type for `output_ref` output
     * @param ref A mutable Tensor. Should be from a Variable node.
     * @param indices A Tensor. Must be one of the following types: int32, int64.
     *  A tensor of indices into ref.
     * @param updates A Tensor. Must have the same type as ref. A tensor of updated values
     *  to add to ref.
     * @param options carries optional attribute values
     * @param <T> data type for `ScatterNdAdd` output and operands
     * @return a new instance of ScatterNdAdd
     * @see org.tensorflow.op.Ops.scatterNdAdd
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking An optional bool. Defaults to True. If True, the assignment will
     *  be protected by a lock; otherwise the behavior is undefined,
     *  but may exhibit less contention.
     * @return this Options instance.
     */
    public fun <T : TType> scatterNdAdd(
        ref: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>,
        useLocking: Boolean? = null
    ): ScatterNdAdd<T> = java.scatterNdAdd<T>(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ScatterNdAdd.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * Applies sparse addition to `input` using individual values or slices
     *  from `updates` according to indices `indices`.  The updates are non-aliasing:
     *  `input` is only modified in-place if no other operations will use it.
     *  Otherwise, a copy of `input` is made.  This operation has a gradient with
     *  respect to both `input` and `updates`.
     *  
     * `input` is a `Tensor` with rank `P` and `indices` is a `Tensor` of rank `Q`.
     *  
     * `indices` must be integer tensor, containing indices into `input`.
     *  It must be shape `\(&#91;d_0, ..., d_{Q-2}, K&#93;\)` where `0 < K <= P`.
     *  
     * The innermost dimension of `indices` (with length `K`) corresponds to
     *  indices into elements (if `K = P`) or `(P-K)`-dimensional slices
     *  (if `K < P`) along the `K`th dimension of `input`.
     *  
     * `updates` is `Tensor` of rank `Q-1+P-K` with shape:
     *  
     * $$&#91;d_0, ..., d_{Q-2}, input.shape[K&#93;, ..., input.shape&#91;P-1&#93;].$$
     *  
     * For example, say we want to add 4 scattered elements to a rank-1 tensor to 8
     *  elements. In Python, that addition would look like this:
     *  ```
     * input = tf.constant([1, 2, 3, 4, 5, 6, 7, 8])
     *  indices = tf.constant([[4], [3], [1], [7]])
     *  updates = tf.constant([9, 10, 11, 12])
     *  output = tf.scatter_nd_non_aliasing_add(input, indices, updates)
     *  with tf.Session() as sess:
     *    print(sess.run(output))
     *  
     * ```
     *  
     * The resulting value `output` would look like this:
     *  ```
     * [1, 13, 3, 14, 14, 6, 7, 20]
     *  
     * ```
     *  
     * See `tf.scatter_nd` for more details about how to make updates to slices.
     *
     * @param <T> data type for `output` output
     * @param input A Tensor.
     * @param indices A Tensor. Must be one of the following types: `int32`, `int64`.
     *  A tensor of indices into `input`.
     * @param updates A Tensor. Must have the same type as ref. A tensor of updated values
     *  to add to `input`.
     * @param <T> data type for `ScatterNdNonAliasingAdd` output and operands
     * @return a new instance of ScatterNdNonAliasingAdd
     * @see org.tensorflow.op.Ops.scatterNdNonAliasingAdd
     */
    public fun <T : TType> scatterNdNonAliasingAdd(
        input: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>
    ): ScatterNdNonAliasingAdd<T> = java.scatterNdNonAliasingAdd<T>(    
        input,
        indices,
        updates
        )

    /**
     * Applies sparse subtraction to individual values or slices in a Variable.
     *  within a given variable according to `indices`.
     *  
     * `ref` is a `Tensor` with rank `P` and `indices` is a `Tensor` of rank `Q`.
     *  
     * `indices` must be integer tensor, containing indices into `ref`.
     *  It must be shape `[d_0, ..., d_{Q-2`, K]} where `0 < K <= P`.
     *  
     * The innermost dimension of `indices` (with length `K`) corresponds to
     *  indices into elements (if `K = P`) or slices (if `K < P`) along the `K`th
     *  dimension of `ref`.
     *  
     * `updates` is `Tensor` of rank `Q-1+P-K` with shape:
     *  `[d_0, ..., d_{Q-2`, ref.shape[K], ..., ref.shape&#91;P-1&#93;]
     *  }
     *  
     * For example, say we want to subtract 4 scattered elements from a rank-1 tensor
     *  with 8 elements. In Python, that subtraction would look like this:
     *  ```
     * ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8])
     *  indices = tf.constant([[4], [3], [1], [7]])
     *  updates = tf.constant([9, 10, 11, 12])
     *  sub = tf.scatter_nd_sub(ref, indices, updates)
     *  with tf.Session() as sess:
     *    print sess.run(sub)
     *  
     * ```
     *  
     * The resulting update to ref would look like this:
     *  ```
     * [1, -9, 3, -6, -4, 6, 7, -4]
     *  
     * ```
     *  
     * See `tf.scatter_nd` for more details about how to make updates to
     *  slices.
     *
     * @param <T> data type for `output_ref` output
     * @param ref A mutable Tensor. Should be from a Variable node.
     * @param indices A Tensor. Must be one of the following types: int32, int64.
     *  A tensor of indices into ref.
     * @param updates A Tensor. Must have the same type as ref. A tensor of updated values
     *  to subtract from ref.
     * @param options carries optional attribute values
     * @param <T> data type for `ScatterNdSub` output and operands
     * @return a new instance of ScatterNdSub
     * @see org.tensorflow.op.Ops.scatterNdSub
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking An optional bool. Defaults to True. If True, the assignment will
     *  be protected by a lock; otherwise the behavior is undefined,
     *  but may exhibit less contention.
     * @return this Options instance.
     */
    public fun <T : TType> scatterNdSub(
        ref: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>,
        useLocking: Boolean? = null
    ): ScatterNdSub<T> = java.scatterNdSub<T>(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ScatterNdSub.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * Applies sparse `updates` to individual values or slices within a given
     *  variable according to `indices`.
     *  
     * `ref` is a `Tensor` with rank `P` and `indices` is a `Tensor` of rank `Q`.
     *  
     * `indices` must be integer tensor, containing indices into `ref`.
     *  It must be shape `\(&#91;d_0, ..., d_{Q-2}, K&#93;\)` where `0 < K <= P`.
     *  
     * The innermost dimension of `indices` (with length `K`) corresponds to
     *  indices into elements (if `K = P`) or slices (if `K < P`) along the `K`th
     *  dimension of `ref`.
     *  
     * `updates` is `Tensor` of rank `Q-1+P-K` with shape:
     *  
     * $$&#91;d_0, ..., d_{Q-2}, ref.shape[K&#93;, ..., ref.shape&#91;P-1&#93;].$$
     *  
     * For example, say we want to update 4 scattered elements to a rank-1 tensor to
     *  8 elements. In Python, that update would look like this:
     *  ```
     * ref = tf.Variable([1, 2, 3, 4, 5, 6, 7, 8])
     *      indices = tf.constant([[4], [3], [1] ,[7]])
     *      updates = tf.constant([9, 10, 11, 12])
     *      update = tf.scatter_nd_update(ref, indices, updates)
     *      with tf.Session() as sess:
     *        print sess.run(update)
     *  
     * ```
     *  
     * The resulting update to ref would look like this:
     *  ```
     * [1, 11, 3, 10, 9, 6, 7, 12]
     *  
     * ```
     *  
     * See `tf.scatter_nd` for more details about how to make updates to
     *  slices.
     *  
     * See also `tf.scatter_update` and `tf.batch_scatter_update`.
     *
     * @param <T> data type for `output_ref` output
     * @param ref A mutable Tensor. Should be from a Variable node.
     * @param indices A Tensor. Must be one of the following types: int32, int64.
     *  A tensor of indices into ref.
     * @param updates A Tensor. Must have the same type as ref. A tensor of updated
     *  values to add to ref.
     * @param options carries optional attribute values
     * @param <T> data type for `ScatterNdUpdate` output and operands
     * @return a new instance of ScatterNdUpdate
     * @see org.tensorflow.op.Ops.scatterNdUpdate
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking An optional bool. Defaults to True. If True, the assignment will
     *  be protected by a lock; otherwise the behavior is undefined,
     *  but may exhibit less contention.
     * @return this Options instance.
     */
    public fun <T : TType> scatterNdUpdate(
        ref: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>,
        useLocking: Boolean? = null
    ): ScatterNdUpdate<T> = java.scatterNdUpdate<T>(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ScatterNdUpdate.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * Subtracts sparse updates to a variable reference.
     *  ```
     * # Scalar indices
     *      ref[indices, ...] -= updates[...]
     *
     *      # Vector indices (for each i)
     *      ref[indices[i], ...] -= updates[i, ...]
     *
     *      # High rank indices (for each i, ..., j)
     *      ref[indices[i, ..., j], ...] -= updates[i, ..., j, ...]
     *  
     * ```
     *  This operation outputs `ref` after the update is done.
     *  This makes it easier to chain operations that need to use the reset value.
     *  
     * Duplicate entries are handled correctly: if multiple `indices` reference
     *  the same location, their (negated) contributions add.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterSub.png" alt>
     *  </div>
     *
     * @param <T> data type for `output_ref` output
     * @param ref Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to subtract from `ref`.
     * @param options carries optional attribute values
     * @param <T> data type for `ScatterSub` output and operands
     * @return a new instance of ScatterSub
     * @see org.tensorflow.op.Ops.scatterSub
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking If True, the subtraction will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
    public fun <T : TType> scatterSub(
        ref: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>,
        useLocking: Boolean? = null
    ): ScatterSub<T> = java.scatterSub<T>(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ScatterSub.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * Applies sparse updates to a variable reference.
     *  This operation computes
     *  ```
     * # Scalar indices
     *      ref[indices, ...] = updates[...]
     *
     *      # Vector indices (for each i)
     *      ref[indices[i], ...] = updates[i, ...]
     *
     *      # High rank indices (for each i, ..., j)
     *      ref[indices[i, ..., j], ...] = updates[i, ..., j, ...]
     *  
     * ```
     *  
     * This operation outputs `ref` after the update is done.
     *  This makes it easier to chain operations that need to use the reset value.
     *  
     * If values in `ref` is to be updated more than once, because there are
     *  duplicate entries in `indices`, the order at which the updates happen
     *  for each value is undefined.
     *  
     * Requires `updates.shape = indices.shape + ref.shape&#91;1:&#93;` or `updates.shape = []`.
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/ScatterUpdate.png" alt>
     *  </div>
     *  
     * See also `tf.batch_scatter_update` and `tf.scatter_nd_update`.
     *
     * @param <T> data type for `output_ref` output
     * @param ref Should be from a `Variable` node.
     * @param indices A tensor of indices into the first dimension of `ref`.
     * @param updates A tensor of updated values to store in `ref`.
     * @param options carries optional attribute values
     * @param <T> data type for `ScatterUpdate` output and operands
     * @return a new instance of ScatterUpdate
     * @see org.tensorflow.op.Ops.scatterUpdate
     * @param useLocking Sets the useLocking option.
     *
     * @param useLocking If True, the assignment will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
    public fun <T : TType> scatterUpdate(
        ref: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>,
        useLocking: Boolean? = null
    ): ScatterUpdate<T> = java.scatterUpdate<T>(    
        ref,
        indices,
        updates,
        *listOfNotNull(
            useLocking?.let{ org.tensorflow.op.core.ScatterUpdate.useLocking(it) }
        ).toTypedArray()
        )

    /**
     * The SelectV2 operation
     *
     * @param <T> data type for `output` output
     * @param condition The condition value
     * @param t The t value
     * @param e The e value
     * @param <T> data type for `SelectV2` output and operands
     * @return a new instance of Select
     * @see org.tensorflow.op.Ops.select
     */
    public fun <T : TType> select(
        condition: Operand<TBool>,
        t: Operand<T>,
        e: Operand<T>
    ): Select<T> = java.select<T>(    
        condition,
        t,
        e
        )

    /**
     * Computes the difference between two lists of numbers or strings.
     *  Given a list `x` and a list `y`, this operation returns a list `out` that
     *  represents all values that are in `x` but not in `y`. The returned list `out`
     *  is sorted in the same order that the numbers appear in `x` (duplicates are
     *  preserved). This operation also returns a list `idx` that represents the
     *  position of each `out` element in `x`. In other words:
     *  
     * `out[i] = x&#91;idx[i&#93;] for i in &#91;0, 1, ..., len(out) - 1&#93;`
     *  
     * For example, given this input:
     *  ```
     * x = [1, 2, 3, 4, 5, 6]
     *  y = [1, 3, 5]
     *  
     * ```
     *  
     * This operation would return:
     *  ```
     * out ==> [2, 4, 6]
     *  idx ==> [1, 3, 5]
     *  
     * ```
     *
     * @param <T> data type for `out` output
     * @param <U> data type for `idx` output
     * @param x 1-D. Values to keep.
     * @param y 1-D. Values to remove.
     * @param <T> data type for `ListDiff` output and operands
     * @return a new instance of SetDiff1d, with default output types
     * @see org.tensorflow.op.Ops.setDiff1d
     */
    public fun <T : TType> setDiff1d(x: Operand<T>, y: Operand<T>): SetDiff1d<T, TInt32> =
            java.setDiff1d<T>(    
        x,
        y
        )

    /**
     * Computes the difference between two lists of numbers or strings.
     *  Given a list `x` and a list `y`, this operation returns a list `out` that
     *  represents all values that are in `x` but not in `y`. The returned list `out`
     *  is sorted in the same order that the numbers appear in `x` (duplicates are
     *  preserved). This operation also returns a list `idx` that represents the
     *  position of each `out` element in `x`. In other words:
     *  
     * `out[i] = x&#91;idx[i&#93;] for i in &#91;0, 1, ..., len(out) - 1&#93;`
     *  
     * For example, given this input:
     *  ```
     * x = [1, 2, 3, 4, 5, 6]
     *  y = [1, 3, 5]
     *  
     * ```
     *  
     * This operation would return:
     *  ```
     * out ==> [2, 4, 6]
     *  idx ==> [1, 3, 5]
     *  
     * ```
     *
     * @param <T> data type for `out` output
     * @param <U> data type for `idx` output
     * @param x 1-D. Values to keep.
     * @param y 1-D. Values to remove.
     * @param outIdx The value of the outIdx attribute
     * @param <T> data type for `ListDiff` output and operands
     * @param <U> data type for `ListDiff` output and operands
     * @return a new instance of SetDiff1d
     * @see org.tensorflow.op.Ops.setDiff1d
     */
    public fun <T : TType, U : TNumber> setDiff1d(
        x: Operand<T>,
        y: Operand<T>,
        outIdx: Class<U>
    ): SetDiff1d<T, U> = java.setDiff1d<T, U>(    
        x,
        y,
        outIdx
        )

    /**
     * Number of unique elements along last dimension of input `set`.
     *  Input `set` is a `SparseTensor` represented by `set_indices`, `set_values`,
     *  and `set_shape`. The last dimension contains values in a set, duplicates are
     *  allowed but ignored.
     *  
     * If `validate_indices` is `True`, this op validates the order and range of `set`
     *  indices.
     *
     * @param setIndices 2D `Tensor`, indices of a `SparseTensor`.
     * @param setValues 1D `Tensor`, values of a `SparseTensor`.
     * @param setShape 1D `Tensor`, shape of a `SparseTensor`.
     * @param options carries optional attribute values
     * @return a new instance of SetSize
     * @see org.tensorflow.op.Ops.setSize
     * @param validateIndices Sets the validateIndices option.
     *
     * @param validateIndices the validateIndices option
     * @return this Options instance.
     */
    public fun setSize(
        setIndices: Operand<TInt64>,
        setValues: Operand<out TType>,
        setShape: Operand<TInt64>,
        validateIndices: Boolean? = null
    ): SetSize = java.setSize(    
        setIndices,
        setValues,
        setShape,
        *listOfNotNull(
            validateIndices?.let{ org.tensorflow.op.core.SetSize.validateIndices(it) }
        ).toTypedArray()
        )

    /**
     * Returns the shape of a tensor.
     *  This operation returns a 1-D integer tensor representing the shape of `input`.
     *  
     * For example:
     *  ```
     * # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
     *  shape(t) ==> [2, 2, 3]
     *  
     * ```
     *
     * @param <U> data type for `output` output
     * @param input The input value
     * @return a new instance of Shape, with default output types
     * @see org.tensorflow.op.Ops.shape
     */
    public fun shape(input: Operand<out TType>): org.tensorflow.op.core.Shape<TInt32> = java.shape(    
        input
        )

    /**
     * Returns the shape of a tensor.
     *  This operation returns a 1-D integer tensor representing the shape of `input`.
     *  
     * For example:
     *  ```
     * # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
     *  shape(t) ==> [2, 2, 3]
     *  
     * ```
     *
     * @param <U> data type for `output` output
     * @param input The input value
     * @param outType The value of the outType attribute
     * @param <U> data type for `Shape` output and operands
     * @return a new instance of Shape
     * @see org.tensorflow.op.Ops.shape
     */
    public fun <U : TNumber> shape(input: Operand<out TType>, outType: Class<U>):
            org.tensorflow.op.core.Shape<U> = java.shape<U>(    
        input,
        outType
        )

    /**
     * Returns shape of tensors.
     *  This operation returns N 1-D integer tensors representing shape of `input[i]s`.
     *
     * @param <U> data type for `output` output
     * @param input The input value
     * @return a new instance of ShapeN, with default output types
     * @see org.tensorflow.op.Ops.shapeN
     */
    public fun shapeN(input: Iterable<Operand<out TType>>): ShapeN<TInt32> = java.shapeN(    
        input
        )

    /**
     * Returns shape of tensors.
     *  This operation returns N 1-D integer tensors representing shape of `input[i]s`.
     *
     * @param <U> data type for `output` output
     * @param input The input value
     * @param outType The value of the outType attribute
     * @param <U> data type for `ShapeN` output and operands
     * @return a new instance of ShapeN
     * @see org.tensorflow.op.Ops.shapeN
     */
    public fun <U : TNumber> shapeN(input: Iterable<Operand<out TType>>, outType: Class<U>):
            ShapeN<U> = java.shapeN<U>(    
        input,
        outType
        )

    /**
     * Returns the size of a tensor.
     *  This operation returns an integer representing the number of elements in
     *  `input`.
     *  
     * For example:
     *  ```
     * # 't' is [[[1, 1,, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]]
     *  size(t) ==> 12
     *  
     * ```
     *
     * @param <U> data type for `output` output
     * @param input The input value
     * @return a new instance of Size, with default output types
     * @see org.tensorflow.op.Ops.size
     */
    public fun size(input: Operand<out TType>): Size<TInt32> = java.size(    
        input
        )

    /**
     * Returns the size of a tensor.
     *  This operation returns an integer representing the number of elements in
     *  `input`.
     *  
     * For example:
     *  ```
     * # 't' is [[[1, 1,, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]]
     *  size(t) ==> 12
     *  
     * ```
     *
     * @param <U> data type for `output` output
     * @param input The input value
     * @param outType The value of the outType attribute
     * @param <U> data type for `Size` output and operands
     * @return a new instance of Size
     * @see org.tensorflow.op.Ops.size
     */
    public fun <U : TNumber> size(input: Operand<out TType>, outType: Class<U>): Size<U> =
            java.size<U>(    
        input,
        outType
        )

    /**
     * Parses a text file and creates a batch of examples.
     *
     * @param filename The corpus's text file name.
     * @param batchSize The size of produced batch.
     * @param options carries optional attribute values
     * @return a new instance of Skipgram
     * @see org.tensorflow.op.Ops.skipgram
     * @param windowSize Sets the windowSize option.
     *
     * @param windowSize The number of words to predict to the left and right of the target.
     * @return this Options instance.
     * @param minCount Sets the minCount option.
     *
     * @param minCount The minimum number of word occurrences for it to be included in the
     *  vocabulary.
     * @return this Options instance.
     * @param subsample Sets the subsample option.
     *
     * @param subsample Threshold for word occurrence. Words that appear with higher
     *  frequency will be randomly down-sampled. Set to 0 to disable.
     * @return this Options instance.
     */
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

    /**
     * Return a slice from 'input'.
     *  The output tensor is a tensor with dimensions described by 'size'
     *  whose values are extracted from 'input' starting at the offsets in
     *  'begin'.
     *  
     * _Requirements_:
     *  0 <= begin[i] <= begin[i] + size[i] <= Di  for i in [0, n)
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param begin begin[i] specifies the offset into the 'i'th dimension of
     *  'input' to slice from.
     * @param sizeOutput size[i] specifies the number of elements of the 'i'th dimension
     *  of 'input' to slice. If size[i] is -1, all remaining elements in dimension
     *  i are included in the slice (i.e. this is equivalent to setting
     *  size[i] = input.dim_size(i) - begin[i]).
     * @param <T> data type for `Slice` output and operands
     * @param <U> data type for `Slice` output and operands
     * @return a new instance of Slice
     * @see org.tensorflow.op.Ops.slice
     */
    public fun <T : TType, U : TNumber> slice(
        input: Operand<T>,
        begin: Operand<U>,
        sizeOutput: Operand<U>
    ): Slice<T> = java.slice<T, U>(    
        input,
        begin,
        sizeOutput
        )

    /**
     * Returns a copy of the input tensor.
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param <T> data type for `Snapshot` output and operands
     * @return a new instance of Snapshot
     * @see org.tensorflow.op.Ops.snapshot
     */
    public fun <T : TType> snapshot(input: Operand<T>): Snapshot<T> = java.snapshot<T>(    
        input
        )

    /**
     * SpaceToBatch for N-D tensors of type T.
     *  This operation divides &quot;spatial&quot; dimensions `&#91;1, ..., M&#93;` of the input
     * into a
     *  grid of blocks of shape `block_shape`, and interleaves these blocks with the
     *  &quot;batch&quot; dimension (0) such that in the output, the spatial dimensions
     *  `&#91;1, ..., M&#93;` correspond to the position within the grid, and the batch
     *  dimension combines both the position within a spatial block and the original
     *  batch position.  Prior to division into blocks, the spatial dimensions of the
     *  input are optionally zero padded according to `paddings`. See below for a
     *  precise description.
     *  
     * This operation is equivalent to the following steps:
     *  <ol>
     *  <li>
     *  
     * Zero-pad the start and end of dimensions `&#91;1, ..., M&#93;` of the
     *  input according to `paddings` to produce `padded` of shape `padded_shape`.
     *  </li>
     *  <li>
     *  
     * Reshape `padded` to `reshaped_padded` of shape:
     *  
     * &#91;batch&#93; +
     *  &#91;padded_shape[1&#93; / block_shape[0],
     *  block_shape[0],
     *  ...,
     *  padded_shape[M] / block_shape&#91;M-1&#93;,
     *  block_shape&#91;M-1&#93;] +
     *  remaining_shape
     *  </li>
     *  <li>
     *  
     * Permute dimensions of `reshaped_padded` to produce
     *  `permuted_reshaped_padded` of shape:
     *  
     * block_shape +
     *  &#91;batch&#93; +
     *  &#91;padded_shape[1&#93; / block_shape[0],
     *  ...,
     *  padded_shape[M] / block_shape&#91;M-1&#93;] +
     *  remaining_shape
     *  </li>
     *  <li>
     *  
     * Reshape `permuted_reshaped_padded` to flatten `block_shape` into the batch
     *  dimension, producing an output tensor of shape:
     *  
     * &#91;batch * prod(block_shape)&#93; +
     *  &#91;padded_shape[1&#93; / block_shape[0],
     *  ...,
     *  padded_shape[M] / block_shape&#91;M-1&#93;] +
     *  remaining_shape
     *  </li>
     *  </ol>
     *  
     * Some examples:
     *  
     * (1) For the following input of shape `&#91;1, 2, 2, 1&#93;`, `block_shape = &#91;2, 2&#93;`,
     * and
     *  `paddings = &#91;[0, 0&#93;, &#91;0, 0&#93;]`:
     *  ```
     * x = [[[[1], [2]], [[3], [4]]]]
     *  
     * ```
     *  
     * The output tensor has shape `&#91;4, 1, 1, 1&#93;` and value:
     *  ```
     * [[[[1]]], [[[2]]], [[[3]]], [[[4]]]]
     *  
     * ```
     *  
     * (2) For the following input of shape `&#91;1, 2, 2, 3&#93;`, `block_shape = &#91;2, 2&#93;`,
     * and
     *  `paddings = &#91;[0, 0&#93;, &#91;0, 0&#93;]`:
     *  ```
     * x = [[[[1, 2, 3], [4, 5, 6]],
     *        [[7, 8, 9], [10, 11, 12]]]]
     *  
     * ```
     *  
     * The output tensor has shape `&#91;4, 1, 1, 3&#93;` and value:
     *  ```
     * [[[[1, 2, 3]]], [[[4, 5, 6]]], [[[7, 8, 9]]], [[[10, 11, 12]]]]
     *  
     * ```
     *  
     * (3) For the following input of shape `&#91;1, 4, 4, 1&#93;`, `block_shape = &#91;2, 2&#93;`,
     * and
     *  `paddings = &#91;[0, 0&#93;, &#91;0, 0&#93;]`:
     *  ```
     * x = [[[[1],   [2],  [3],  [4]],
     *        [[5],   [6],  [7],  [8]],
     *        [[9],  [10], [11],  [12]],
     *        [[13], [14], [15],  [16]]]]
     *  
     * ```
     *  
     * The output tensor has shape `&#91;4, 2, 2, 1&#93;` and value:
     *  ```
     * x = [[[[1], [3]], [[9], [11]]],
     *       [[[2], [4]], [[10], [12]]],
     *       [[[5], [7]], [[13], [15]]],
     *       [[[6], [8]], [[14], [16]]]]
     *  
     * ```
     *  
     * (4) For the following input of shape `&#91;2, 2, 4, 1&#93;`, block_shape = `&#91;2, 2&#93;`,
     * and
     *  paddings = `&#91;[0, 0&#93;, &#91;2, 0&#93;]`:
     *  ```
     * x = [[[[1],   [2],  [3],  [4]],
     *        [[5],   [6],  [7],  [8]]],
     *       [[[9],  [10], [11],  [12]],
     *        [[13], [14], [15],  [16]]]]
     *  
     * ```
     *  
     * The output tensor has shape `&#91;8, 1, 3, 1&#93;` and value:
     *  ```
     * x = [[[[0], [1], [3]]], [[[0], [9], [11]]],
     *       [[[0], [2], [4]]], [[[0], [10], [12]]],
     *       [[[0], [5], [7]]], [[[0], [13], [15]]],
     *       [[[0], [6], [8]]], [[[0], [14], [16]]]]
     *  
     * ```
     *  
     * Among others, this operation is useful for reducing atrous convolution into
     *  regular convolution.
     *
     * @param <T> data type for `output` output
     * @param input N-D with shape `input_shape = &#91;batch&#93; + spatial_shape +
     * remaining_shape`,
     *  where spatial_shape has `M` dimensions.
     * @param blockShape 1-D with shape `[M]`, all values must be >= 1.
     * @param paddings 2-D with shape `&#91;M, 2&#93;`, all values must be >= 0.
     *  `paddings[i] = &#91;pad_start, pad_end&#93;` specifies the padding for input dimension
     *  `i + 1`, which corresponds to spatial dimension `i`.  It is required that
     *  `block_shape[i]` divides `input_shape&#91;i + 1&#93; + pad_start + pad_end`.
     * @param <T> data type for `SpaceToBatchND` output and operands
     * @return a new instance of SpaceToBatchNd
     * @see org.tensorflow.op.Ops.spaceToBatchNd
     */
    public fun <T : TType> spaceToBatchNd(
        input: Operand<T>,
        blockShape: Operand<out TNumber>,
        paddings: Operand<out TNumber>
    ): SpaceToBatchNd<T> = java.spaceToBatchNd<T>(    
        input,
        blockShape,
        paddings
        )

    /**
     * Splits a tensor into `num_split` tensors along one dimension.
     *
     * @param <T> data type for `output` output
     * @param axis 0-D.  The dimension along which to split.  Must be in the range
     *  `[-rank(value), rank(value))`.
     * @param value The tensor to split.
     * @param numSplit The number of ways to split.  Must evenly divide
     *  `value.shape&#91;split_dim&#93;`.
     * @param <T> data type for `Split` output and operands
     * @return a new instance of Split
     * @see org.tensorflow.op.Ops.split
     */
    public fun <T : TType> split(
        axis: Operand<TInt32>,
        value: Operand<T>,
        numSplit: Long
    ): Split<T> = java.split<T>(    
        axis,
        value,
        numSplit
        )

    /**
     * Splits a tensor into `num_split` tensors along one dimension.
     *
     * @param <T> data type for `output` output
     * @param value The tensor to split.
     * @param sizeSplits list containing the sizes of each output tensor along the split
     *  dimension. Must sum to the dimension of value along split_dim.
     *  Can contain one -1 indicating that dimension is to be inferred.
     * @param axis 0-D.  The dimension along which to split.  Must be in the range
     *  `[-rank(value), rank(value))`.
     * @param numSplit The value of the numSplit attribute
     * @param <T> data type for `SplitV` output and operands
     * @return a new instance of SplitV
     * @see org.tensorflow.op.Ops.splitV
     */
    public fun <T : TType> splitV(
        value: Operand<T>,
        sizeSplits: Operand<out TNumber>,
        axis: Operand<TInt32>,
        numSplit: Long
    ): SplitV<T> = java.splitV<T>(    
        value,
        sizeSplits,
        axis,
        numSplit
        )

    /**
     * Removes dimensions of size 1 from the shape of a tensor.
     *  Given a tensor `input`, this operation returns a tensor of the same type with
     *  all dimensions of size 1 removed. If you don't want to remove all size 1
     *  dimensions, you can remove specific size 1 dimensions by specifying
     *  `axis`.
     *  
     * For example:
     *  ```
     * # 't' is a tensor of shape [1, 2, 1, 3, 1, 1]
     *  shape(squeeze(t)) ==> [2, 3]
     *  
     * ```
     *  
     * Or, to remove specific size 1 dimensions:
     *  ```
     * # 't' is a tensor of shape [1, 2, 1, 3, 1, 1]
     *  shape(squeeze(t, [2, 4])) ==> [1, 2, 3, 1]
     *  
     * ```
     *
     * @param <T> data type for `output` output
     * @param input The `input` to squeeze.
     * @param options carries optional attribute values
     * @param <T> data type for `Squeeze` output and operands
     * @return a new instance of Squeeze
     * @see org.tensorflow.op.Ops.squeeze
     * @param axis Sets the axis option.
     *
     * @param axis If specified, only squeezes the dimensions listed. The dimension
     *  index starts at 0. It is an error to squeeze a dimension that is not 1. Must
     *  be in the range `[-rank(input), rank(input))`.
     * @return this Options instance.
     */
    public fun <T : TType> squeeze(input: Operand<T>, axis: List<Long>? = null): Squeeze<T> =
            java.squeeze<T>(    
        input,
        *listOfNotNull(
            axis?.let{ org.tensorflow.op.core.Squeeze.axis(it) }
        ).toTypedArray()
        )

    /**
     * Packs a list of `N` rank-`R` tensors into one rank-`(R+1)` tensor.
     *  Packs the `N` tensors in `values` into a tensor with rank one higher than each
     *  tensor in `values`, by packing them along the `axis` dimension.
     *  Given a list of tensors of shape `(A, B, C)`;
     *  
     * if `axis == 0` then the `output` tensor will have the shape `(N, A, B, C)`.
     *  if `axis == 1` then the `output` tensor will have the shape `(A, N, B, C)`.
     *  Etc.
     *  
     * For example:
     *  ```
     * # 'x' is [1, 4]
     *  # 'y' is [2, 5]
     *  # 'z' is [3, 6]
     *  pack([x, y, z]) => [[1, 4], [2, 5], [3, 6]]  # Pack along first dim.
     *  pack([x, y, z], axis=1) => [[1, 2, 3], [4, 5, 6]]
     *  
     * ```
     *  
     * This is the opposite of `unpack`.
     *
     * @param <T> data type for `output` output
     * @param values Must be of same shape and type.
     * @param options carries optional attribute values
     * @param <T> data type for `Pack` output and operands
     * @return a new instance of Stack
     * @see org.tensorflow.op.Ops.stack
     * @param axis Sets the axis option.
     *
     * @param axis Dimension along which to pack.  Negative values wrap around, so the
     *  valid range is `[-(R+1), R+1)`.
     * @return this Options instance.
     */
    public fun <T : TType> stack(values: Iterable<Operand<T>>, axis: Long? = null): Stack<T> =
            java.stack<T>(    
        values,
        *listOfNotNull(
            axis?.let{ org.tensorflow.op.core.Stack.axis(it) }
        ).toTypedArray()
        )

    /**
     * Stage values similar to a lightweight Enqueue.
     *  The basic functionality of this Op is similar to a queue with many
     *  fewer capabilities and options.  This Op is optimized for performance.
     *
     * @param values a list of tensors
     *  dtypes A list of data types that inserted values should adhere to.
     * @param options carries optional attribute values
     * @return a new instance of Stage
     * @see org.tensorflow.op.Ops.stage
     * @param capacity Sets the capacity option.
     *
     * @param capacity Maximum number of elements in the Staging Area. If > 0, inserts
     *  on the container will block when the capacity is reached.
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit The maximum number of bytes allowed for Tensors in the Staging Area.
     *  If > 0, inserts will block until sufficient space is available.
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container If non-empty, this queue is placed in the given container. Otherwise,
     *  a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName It is necessary to match this name to the matching Unstage Op.
     * @return this Options instance.
     */
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

    /**
     * Op removes all elements in the underlying container.
     *
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of StageClear
     * @see org.tensorflow.op.Ops.stageClear
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun stageClear(
        dtypes: List<Class<out TType>>,
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

    /**
     * Op peeks at the values at the specified index.  If the
     *  underlying container does not contain sufficient elements
     *  this op will block until it does.   This Op is optimized for
     *  performance.
     *
     * @param index The index value
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of StagePeek
     * @see org.tensorflow.op.Ops.stagePeek
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun stagePeek(
        index: Operand<TInt32>,
        dtypes: List<Class<out TType>>,
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

    /**
     * Op returns the number of elements in the underlying container.
     *
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of StageSize
     * @see org.tensorflow.op.Ops.stageSize
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun stageSize(
        dtypes: List<Class<out TType>>,
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

    /**
     * An n-way switch statement which calls a single branch function.
     *  ```
     * An n-way switch statement, implementing the following:
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
     *  
     * ```
     *  ```
     *  }
     *
     * @param branchIndex The branch selector, an int32 Tensor.
     * @param input A list of input tensors passed to the branch function.
     * @param Tout A list of output types.
     * @param branches `
     * A list of functions each of which takes 'inputs' and returns a list of
     *    tensors, whose types are the same as what every other branch returns.
     *  
     * `
     * @param options carries optional attribute values
     * @return a new instance of StatefulCase
     * @see org.tensorflow.op.Ops.statefulCase
     * @param outputShapes Sets the outputShapes option.
     *
     * @param outputShapes the outputShapes option
     * @return this Options instance.
     */
    public fun statefulCase(
        branchIndex: Operand<TInt32>,
        input: Iterable<Operand<*>>,
        Tout: List<Class<out TType>>,
        branches: List<ConcreteFunction>,
        outputShapes: List<Shape>? = null
    ): StatefulCase = java.statefulCase(    
        branchIndex,
        input,
        Tout,
        branches,
        *listOfNotNull(
            outputShapes?.let{ org.tensorflow.op.core.Case.outputShapes(it) }
        ).toTypedArray()
        )

    /**
     * output = cond ? then_branch(input) : else_branch(input)
     *
     * @param cond `
     * A Tensor. If the tensor is a scalar of non-boolean type, the
     *    scalar is converted to a boolean according to the
     *    following rule: if the scalar is a numerical value, non-zero means
     *    `True` and zero means False; if the scalar is a string, non-empty
     *    means `True` and empty means `False`. If the tensor is not a scalar,
     *    being empty means False and being non-empty means True.
     *  
     * `
     * @param input A list of input tensors.
     * @param Tout A list of output types.
     * @param thenBranch `
     * A function that takes 'inputs' and returns a list of tensors, whose
     *    types are the same as what else_branch returns.
     *  
     * `
     * @param elseBranch `
     * A function that takes 'inputs' and returns a list of tensors, whose
     *  types are the same as what then_branch returns.
     *  
     * `
     * @param options carries optional attribute values
     * @return a new instance of StatefulIf
     * @see org.tensorflow.op.Ops.statefulIf
     * @param outputShapes Sets the outputShapes option.
     *
     * @param outputShapes the outputShapes option
     * @return this Options instance.
     */
    public fun statefulIf(
        cond: Operand<out TType>,
        input: Iterable<Operand<*>>,
        Tout: List<Class<out TType>>,
        thenBranch: ConcreteFunction,
        elseBranch: ConcreteFunction,
        outputShapes: List<Shape>? = null
    ): StatefulIf = java.statefulIf(    
        cond,
        input,
        Tout,
        thenBranch,
        elseBranch,
        *listOfNotNull(
            outputShapes?.let{ org.tensorflow.op.core.If.outputShapes(it) }
        ).toTypedArray()
        )

    /**
     * returns `f(inputs)`, where `f`'s body is placed and partitioned.
     *
     * @param args A list of input tensors.
     * @param Tout A list of output types.
     * @param f `
     * A function that takes 'args', a list of tensors, and returns 'output',
     *    another list of tensors. Input and output types are specified by 'Tin'
     *    and 'Tout'. The function body of f will be placed and partitioned across
     *    devices, setting this op apart from the regular Call op. This op is
     *    stateful.
     *  
     * `
     * @param options carries optional attribute values
     * @return a new instance of StatefulPartitionedCall
     * @see org.tensorflow.op.Ops.statefulPartitionedCall
     * @param config Sets the config option.
     *
     * @param config the config option
     * @return this Options instance.
     * @param configProto Sets the configProto option.
     *
     * @param configProto the configProto option
     * @return this Options instance.
     * @param executorType Sets the executorType option.
     *
     * @param executorType the executorType option
     * @return this Options instance.
     */
    public fun statefulPartitionedCall(
        args: Iterable<Operand<*>>,
        Tout: List<Class<out TType>>,
        f: ConcreteFunction,
        config: String? = null,
        configProto: String? = null,
        executorType: String? = null
    ): StatefulPartitionedCall = java.statefulPartitionedCall(    
        args,
        Tout,
        f,
        *listOfNotNull(
            config?.let{ org.tensorflow.op.core.PartitionedCall.config(it) },
            configProto?.let{ org.tensorflow.op.core.PartitionedCall.configProto(it) },
            executorType?.let{ org.tensorflow.op.core.PartitionedCall.executorType(it) }
        ).toTypedArray()
        )

    /**
     * output = input; While (Cond(output)) { output = Body(output) }
     *
     * @param input A list of input tensors whose types are T.
     * @param cond `
     * A function takes 'input' and returns a tensor.  If the tensor is
     *    a scalar of non-boolean, the scalar is converted to a boolean
     *    according to the following rule: if the scalar is a numerical
     *    value, non-zero means True and zero means False; if the scalar is
     *    a string, non-empty means True and empty means False. If the
     *    tensor is not a scalar, non-emptiness means True and False
     *    otherwise.
     *  
     * `
     * @param body `
     * A function that takes a list of tensors and returns another
     *    list of tensors. Both lists have the same types as specified
     *    by T.
     *  
     * `
     * @param options carries optional attribute values
     * @return a new instance of StatefulWhile
     * @see org.tensorflow.op.Ops.statefulWhile
     * @param outputShapes Sets the outputShapes option.
     *
     * @param outputShapes the outputShapes option
     * @return this Options instance.
     * @param parallelIterations Sets the parallelIterations option.
     *
     * @param parallelIterations the parallelIterations option
     * @return this Options instance.
     */
    public fun statefulWhile(
        input: Iterable<Operand<*>>,
        cond: ConcreteFunction,
        body: ConcreteFunction,
        outputShapes: List<Shape>? = null,
        parallelIterations: Long? = null
    ): StatefulWhile = java.statefulWhile(    
        input,
        cond,
        body,
        *listOfNotNull(
            outputShapes?.let{ org.tensorflow.op.core.While.outputShapes(it) },
            parallelIterations?.let{ org.tensorflow.op.core.While.parallelIterations(it) }
        ).toTypedArray()
        )

    /**
     * output = cond ? then_branch(input) : else_branch(input)
     *
     * @param cond `
     * A Tensor. If the tensor is a scalar of non-boolean type, the
     *    scalar is converted to a boolean according to the
     *    following rule: if the scalar is a numerical value, non-zero means
     *    `True` and zero means False; if the scalar is a string, non-empty
     *    means `True` and empty means `False`. If the tensor is not a scalar,
     *    being empty means False and being non-empty means True.
     *
     *    This should only be used when the if then/else body functions do not
     *    have stateful ops.
     *  
     * `
     * @param input A list of input tensors.
     * @param Tout A list of output types.
     * @param thenBranch `
     * A function that takes 'inputs' and returns a list of tensors, whose
     *    types are the same as what else_branch returns.
     *  
     * `
     * @param elseBranch `
     * A function that takes 'inputs' and returns a list of tensors, whose
     *  types are the same as what then_branch returns.
     *  
     * `
     * @param options carries optional attribute values
     * @return a new instance of StatelessIf
     * @see org.tensorflow.op.Ops.statelessIf
     * @param outputShapes Sets the outputShapes option.
     *
     * @param outputShapes the outputShapes option
     * @return this Options instance.
     */
    public fun statelessIf(
        cond: Operand<out TType>,
        input: Iterable<Operand<*>>,
        Tout: List<Class<out TType>>,
        thenBranch: ConcreteFunction,
        elseBranch: ConcreteFunction,
        outputShapes: List<Shape>? = null
    ): StatelessIf = java.statelessIf(    
        cond,
        input,
        Tout,
        thenBranch,
        elseBranch,
        *listOfNotNull(
            outputShapes?.let{ org.tensorflow.op.core.If.outputShapes(it) }
        ).toTypedArray()
        )

    /**
     * returns `f(inputs)`, where `f`'s body is placed and partitioned.
     *  Asynchronously executes a function, potentially across multiple devices but
     *  within a single process. The kernel places and partitions a given function's
     *  underlying graph, and executes each of the partitioned subgraphs as a function.
     *
     * @param args A list of input tensors.
     * @param Tout A list of output types.
     * @param f `
     * A function that takes 'args', a list of tensors, and returns 'output',
     *    another list of tensors. Input and output types are specified by 'Tin'
     *    and 'Tout'. The function body of f will be placed and partitioned across
     *    devices, setting this op apart from the regular Call op.
     *  
     * `
     * @param options carries optional attribute values
     * @return a new instance of StatelessPartitionedCall
     * @see org.tensorflow.op.Ops.statelessPartitionedCall
     * @param config Sets the config option.
     *
     * @param config the config option
     * @return this Options instance.
     * @param configProto Sets the configProto option.
     *
     * @param configProto the configProto option
     * @return this Options instance.
     * @param executorType Sets the executorType option.
     *
     * @param executorType the executorType option
     * @return this Options instance.
     */
    public fun statelessPartitionedCall(
        args: Iterable<Operand<*>>,
        Tout: List<Class<out TType>>,
        f: ConcreteFunction,
        config: String? = null,
        configProto: String? = null,
        executorType: String? = null
    ): StatelessPartitionedCall = java.statelessPartitionedCall(    
        args,
        Tout,
        f,
        *listOfNotNull(
            config?.let{ org.tensorflow.op.core.PartitionedCall.config(it) },
            configProto?.let{ org.tensorflow.op.core.PartitionedCall.configProto(it) },
            executorType?.let{ org.tensorflow.op.core.PartitionedCall.executorType(it) }
        ).toTypedArray()
        )

    /**
     * output = input; While (Cond(output)) { output = Body(output) }
     *
     * @param input A list of input tensors whose types are T.
     * @param cond `
     * A function takes 'input' and returns a tensor.  If the tensor is
     *    a scalar of non-boolean, the scalar is converted to a boolean
     *    according to the following rule: if the scalar is a numerical
     *    value, non-zero means True and zero means False; if the scalar is
     *    a string, non-empty means True and empty means False. If the
     *    tensor is not a scalar, non-emptiness means True and False
     *    otherwise.
     *
     *    This should only be used when the while condition and body functions
     *    do not have stateful ops.
     *  
     * `
     * @param body `
     * A function that takes a list of tensors and returns another
     *    list of tensors. Both lists have the same types as specified
     *    by T.
     *  
     * `
     * @param options carries optional attribute values
     * @return a new instance of StatelessWhile
     * @see org.tensorflow.op.Ops.statelessWhile
     * @param outputShapes Sets the outputShapes option.
     *
     * @param outputShapes the outputShapes option
     * @return this Options instance.
     * @param parallelIterations Sets the parallelIterations option.
     *
     * @param parallelIterations the parallelIterations option
     * @return this Options instance.
     */
    public fun statelessWhile(
        input: Iterable<Operand<*>>,
        cond: ConcreteFunction,
        body: ConcreteFunction,
        outputShapes: List<Shape>? = null,
        parallelIterations: Long? = null
    ): StatelessWhile = java.statelessWhile(    
        input,
        cond,
        body,
        *listOfNotNull(
            outputShapes?.let{ org.tensorflow.op.core.While.outputShapes(it) },
            parallelIterations?.let{ org.tensorflow.op.core.While.parallelIterations(it) }
        ).toTypedArray()
        )

    /**
     * Stops gradient computation.
     *  When executed in a graph, this op outputs its input tensor as-is.
     *  
     * When building ops to compute gradients, this op prevents the contribution of
     *  its inputs to be taken into account.  Normally, the gradient generator adds ops
     *  to a graph to compute the derivatives of a specified 'loss' by recursively
     *  finding out inputs that contributed to its computation.  If you insert this op
     *  in the graph it inputs are masked from the gradient generator.  They are not
     *  taken into account for computing gradients.
     *  
     * This is useful any time you want to compute a value with TensorFlow but need
     *  to pretend that the value was a constant. For example, the softmax function
     *  for a vector x can be written as
     *  ```
     * def softmax(x):
     *      numerator = tf.exp(x)
     *      denominator = tf.reduce_sum(numerator)
     *      return numerator / denominator
     *  
     * ```
     *  
     * This however is susceptible to overflow if the values in x are large. An
     *  alternative more stable way is to subtract the maximum of x from each of the
     *  values.
     *  ```
     * def stable_softmax(x):
     *      z = x - tf.reduce_max(x)
     *      numerator = tf.exp(z)
     *      denominator = tf.reduce_sum(numerator)
     *      return numerator / denominator
     *  
     * ```
     *  
     * However, when we backprop through the softmax to x, we dont want to backprop
     *  through the `tf.reduce_max(x)` (if the max values are not unique then the
     *  gradient could flow to the wrong input) calculation and treat that as a
     *  constant. Therefore, we should write this out as
     *  ```
     * def stable_softmax(x):
     *      z = x - tf.stop_gradient(tf.reduce_max(x))
     *      numerator = tf.exp(z)
     *      denominator = tf.reduce_sum(numerator)
     *      return numerator / denominator
     *  
     * ```
     *  
     * Some other examples include:
     *  <ul>
     *  <li>The _EM_ algorithm where the _M-step_ should not involve backpropagation
     *  through the output of the _E-step_.</li>
     *  <li>Contrastive divergence training of Boltzmann machines where, when
     *  differentiating the energy function, the training must not backpropagate
     *  through the graph that generated the samples from the model.</li>
     *  <li>Adversarial training, where no backprop should happen through the adversarial
     *  example generation process.</li>
     *  </ul>
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param <T> data type for `StopGradient` output and operands
     * @return a new instance of StopGradient
     * @see org.tensorflow.op.Ops.stopGradient
     */
    public fun <T : TType> stopGradient(input: Operand<T>): StopGradient<T> = java.stopGradient<T>(    
        input
        )

    /**
     * Return a strided slice from `input`.
     *  
     *
     *  The goal of this op is to produce a new tensor with a subset of the elements from the `n`
     * dimensional `input`
     *  tensor. The subset is chosen using a sequence of `m` sparse range specifications encoded
     * into the arguments of this
     *  function. Note, in some cases `m` could be equal to `n`, but this need not be the case. Each
     * range specification
     *  entry can be one of the following:
     *  
     *
     *  - An ellipsis (...) using [Indices.ellipsis]. Ellipses are used to imply zero or more
     * dimensions of
     *  full-dimension selection. For example, `stridedSlice(foo, Indices.ellipsis()` is the
     * identity slice.
     *  
     *
     *  - A new axis using [Indices.newAxis]. This is used to insert a new shape=1 dimension.
     *  For example, ``stridedSlice(foo, Indices.newAxis())` where `foo` is shape `(3, 4)` 
     *  produces a `(1, 3, 4)` tensor.
     *  
     *
     *  - A range `begin:end:stride` using [Long,][Indices.slice]  Index.slice()} or [Indices.all].
     * This is used to specify
     *  how much to choose from a given dimension. `stride` can be any integer but 0.  `begin` is an
     * integer which
     *  represents the index of the first value to select while `end` represents the index of the
     * last value to select 
     *  (exclusive). Begin and end can be null, in which case the index begins or ends at the
     * beginning or end of the dimension,
     *  respectively (reversed if stride is negative).  When both are null, `slice()` is the same as
     * `all()`.
     *  The number of values selected in each dimension is `end - begin` if `stride > 0` and
     * `begin - end`
     *  if `stride < 0`. `begin` and `end` can be negative where `-1` is the last element, `-2`
     *  is the second to last. For example, given a shape `(3,)` tensor `stridedSlice(foo,
     * Indices.all())`, the
     *  effective `begin` and `end` are `0` and `3`. Do not assume this is equivalent to
     *  `stridedSlice(foo, Indices.slice(0, -1))` which has an effective `begin` and `end` of `0`
     * and
     *  `2`. Another example is `stridedSlice(foo, Indices.slice(-2, null, -1))` which reverses the
     * first dimension
     *  of a tensor while dropping the last two (in the original order elements). For example ```
     * foo = [1,2,3,4];
     *  stridedSlice(foo, Indices.slice(-2, null, -1)
     * ``` is `&#91;4,3&#93;`.
     *  
     *
     *  - A single index using [Indices.at]. This is used to keep only elements that have a given
     * index. For
     *  example (`stridedSlice(foo, Indices.at(2))` on a shape `(5,6)` tensor produces a shape
     * `(6,)` tensor.
     *  The dimension can be kept with size one using [boolean)][Indices.at].
     *  
     *
     *  These semantics generally follow NumPy's indexing semantics, which can be found
     * here:[https://numpy.org/doc/stable/reference/arrays.indexing.html](https://numpy.org/doc/stable/reference/arrays.indexing.html)
     * 
     *
     *
     *  _Requirements_:
     *  `0 != strides[i] for i in [0, m)` Only one ellipsis.
     *
     * @param <T> data type for `output()` output
     * @param indices The indices to slice.  See [Indices].
     * @return a new instance of StridedSlice
     * @see Indices
     * @see org.tensorflow.op.Ops.stridedSlice
     */
    public fun <T : TType> stridedSlice(input: Operand<T>, vararg indices: Index): StridedSlice<T> =
            java.stridedSlice<T>(    
        input,
        *indices
        )

    /**
     * Return a strided slice from `input`.
     *  Note, most python users will want to use the Python `Tensor.__getitem__`
     *  or `Variable.__getitem__` rather than this op directly.
     *  
     * The goal of this op is to produce a new tensor with a subset of
     *  the elements from the `n` dimensional `input` tensor. The subset is chosen using
     *  a sequence of `m` sparse range specifications encoded into the arguments
     *  of this function. Note, in some cases
     *  `m` could be equal to `n`, but this need not be the case. Each
     *  range specification entry can be one of the following:
     *  <ul>
     *  <li>
     *  
     * An ellipsis (...). Ellipses are used to imply zero or more
     *  dimensions of full-dimension selection and are produced using
     *  `ellipsis_mask`. For example, `foo&#91;...&#93;` is the identity slice.
     *  </li>
     *  <li>
     *  
     * A new axis. This is used to insert a new shape=1 dimension and is
     *  produced using `new_axis_mask`. For example, `foo&#91;:, ...&#93;` where
     *  `foo` is shape `(3, 4)` produces a `(1, 3, 4)` tensor.
     *  </li>
     *  <li>
     *  
     * A range `begin:end:stride`. This is used to specify how much to choose from
     *  a given dimension. `stride` can be any integer but 0.  `begin` is an integer
     *  which represents the index of the first value to select while `end` represents
     *  the index of the last value to select. The number of values selected in each
     *  dimension is `end - begin` if `stride > 0` and `begin - end` if `stride < 0`.
     *  `begin` and `end` can be negative where `-1` is the last element, `-2` is
     *  the second to last. `begin_mask` controls whether to replace the explicitly
     *  given `begin` with an implicit effective value of `0` if `stride > 0` and
     *  `-1` if `stride < 0`. `end_mask` is analogous but produces the number
     *  required to create the largest open interval. For example, given a shape
     *  `(3,)` tensor `foo[:]`, the effective `begin` and `end` are `0` and `3`. Do
     *  not assume this is equivalent to `foo&#91;0:-1&#93;` which has an effective `begin`
     *  and `end` of `0` and `2`. Another example is `foo&#91;-2::-1&#93;` which reverses the
     *  first dimension of a tensor while dropping the last two (in the original
     *  order elements). For example `foo = &#91;1,2,3,4&#93;; foo&#91;-2::-1&#93;` is
     * `&#91;4,3&#93;`.
     *  </li>
     *  <li>
     *  
     * A single index. This is used to keep only elements that have a given
     *  index. For example (`foo&#91;2, :&#93;` on a shape `(5,6)` tensor produces a
     *  shape `(6,)` tensor. This is encoded in `begin` and `end` and
     *  `shrink_axis_mask`.
     *  </li>
     *  </ul>
     *  
     * Each conceptual range specification is encoded in the op's argument. This
     *  encoding is best understand by considering a non-trivial example. In
     *  particular,
     *  `foo&#91;1, 2:4, None, ..., :-3:-1, :&#93;` will be encoded as
     *  ```
     * begin = [1, 2, x, x, 0, x] # x denotes don't care (usually 0)
     *  end = [2, 4, x, x, -3, x]
     *  strides = [1, 1, x, x, -1, 1]
     *  begin_mask = 1<<4 | 1<<5 = 48
     *  end_mask = 1<<5 = 32
     *  ellipsis_mask = 1<<3 = 8
     *  new_axis_mask = 1<<2 = 4
     *  shrink_axis_mask = 1<<0 = 1
     *  
     * ```
     *  
     * In this case if `foo.shape` is (5, 5, 5, 5, 5, 5) the final shape of
     *  the slice becomes (2, 1, 5, 5, 2, 5).
     *  Let us walk step by step through each argument specification.
     *  <ol>
     *  <li>
     *  
     * The first argument in the example slice is turned into `begin = 1` and
     *  `end = begin + 1 = 2`. To disambiguate from the original spec `2:4` we
     *  also set the appropriate bit in `shrink_axis_mask`.
     *  </li>
     *  <li>
     *  
     * `2:4` is contributes 2, 4, 1 to begin, end, and stride. All masks have
     *  zero bits contributed.
     *  </li>
     *  <li>
     *  
     * None is a synonym for `tf.newaxis`. This means insert a dimension of size 1
     *  dimension in the final shape. Dummy values are contributed to begin,
     *  end and stride, while the new_axis_mask bit is set.
     *  </li>
     *  <li>
     *  
     * `...` grab the full ranges from as many dimensions as needed to
     *  fully specify a slice for every dimension of the input shape.
     *  </li>
     *  <li>
     *  
     * `:-3:-1` shows the use of negative indices. A negative index `i` associated
     *  with a dimension that has shape `s` is converted to a positive index
     *  `s + i`. So `-1` becomes `s-1` (i.e. the last element). This conversion
     *  is done internally so begin, end and strides receive x, -3, and -1.
     *  The appropriate begin_mask bit is set to indicate the start range is the
     *  full range (ignoring the x).
     *  </li>
     *  <li>
     *  
     * `:` indicates that the entire contents of the corresponding dimension
     *  is selected. This is equivalent to `::` or `0::1`. begin, end, and strides
     *  receive 0, 0, and 1, respectively. The appropriate bits in `begin_mask` and
     *  `end_mask` are also set.
     *  </li>
     *  </ol>
     *  
     * _Requirements_:
     *  `0 != strides[i] for i in [0, m)`
     *  `ellipsis_mask must be a power of two (only one ellipsis)`
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param begin `begin[k]` specifies the offset into the `k`th range specification.
     *  The exact dimension this corresponds to will be determined by context.
     *  Out-of-bounds values will be silently clamped. If the `k`th bit of
     *  `begin_mask` then `begin[k]` is ignored and the full range of the
     *  appropriate dimension is used instead. Negative values causes indexing
     *  to start from the highest element e.g. If `foo==&#91;1,2,3&#93;` then `foo&#91;-1&#93;==3`.
     * @param end `end[i]` is like `begin` with the exception that `end_mask` is
     *  used to determine full ranges.
     * @param strides `strides[i]` specifies the increment in the `i`th specification
     *  after extracting a given element. Negative indices will reverse
     *  the original order. Out or range values are
     *  clamped to `&#91;0,dim[i&#93;) if slice[i]>0` or `&#91;-1,dim[i&#93;-1] if slice[i] < 0`
     * @param options carries optional attribute values
     * @param <T> data type for `StridedSlice` output and operands
     * @param <U> data type for `StridedSlice` output and operands
     * @return a new instance of StridedSlice
     * @see org.tensorflow.op.Ops.stridedSlice
     * @param beginMask Sets the beginMask option.
     *
     * @param beginMask a bitmask where a bit i being 1 means to ignore the begin
     *  value and instead use the largest interval possible. At runtime
     *  begin[i] will be replaced with `[0, n-1)` if `stride[i] > 0` or
     *  `&#91;-1, n-1&#93;` if `stride[i] < 0`
     * @return this Options instance.
     * @param endMask Sets the endMask option.
     *
     * @param endMask analogous to `begin_mask`
     * @return this Options instance.
     * @param ellipsisMask Sets the ellipsisMask option.
     *
     * @param ellipsisMask a bitmask where bit `i` being 1 means the `i`th
     *  position is actually an ellipsis. One bit at most can be 1.
     *  If `ellipsis_mask == 0`, then an implicit ellipsis mask of `1 << (m+1)`
     *  is provided. This means that `foo&#91;3:5&#93; == foo&#91;3:5, ...&#93;`. An ellipsis
     *  implicitly creates as many range specifications as necessary to fully
     *  specify the sliced range for every dimension. For example for a 4-dimensional
     *  tensor `foo` the slice `foo&#91;2, ..., 5:8&#93;` implies `foo&#91;2, :, :, 5:8&#93;`.
     * @return this Options instance.
     * @param newAxisMask Sets the newAxisMask option.
     *
     * @param newAxisMask a bitmask where bit `i` being 1 means the `i`th
     *  specification creates a new shape 1 dimension. For example
     *  `foo&#91;:4, tf.newaxis, :2&#93;` would produce a shape `(4, 1, 2)` tensor.
     * @return this Options instance.
     * @param shrinkAxisMask Sets the shrinkAxisMask option.
     *
     * @param shrinkAxisMask a bitmask where bit `i` implies that the `i`th
     *  specification should shrink the dimensionality. begin and end
     *  must imply a slice of size 1 in the dimension. For example in
     *  python one might do `foo&#91;:, 3, :&#93;` which would result in
     *  `shrink_axis_mask` being 2.
     * @return this Options instance.
     */
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

    /**
     * Assign `value` to the sliced l-value reference of `ref`.
     *  
     *
     *  The values of `value` are assigned to the positions in the variable `ref` that are selected
     * by the slice
     *  parameters. The slice parameters `begin`, `end`, `strides`, etc. work exactly as in
     * `StridedSlice`.
     *  
     *
     *  NOTE this op currently does not support broadcasting and so `value`'s shape must be exactly
     * the shape produced by
     *  the slice of `ref`.
     *
     * @param <T> data type for `outputRef()` output
     * @param ref the tensor to assign to.
     * @param value the value to assign.
     * @param indices The indices to slice.  See [Indices].
     * @return a new instance of StridedSliceAssign
     * @see org.tensorflow.op.Ops.stridedSlice
     * @see org.tensorflow.op.Ops.stridedSliceAssign
     */
    public fun <T : TType> stridedSliceAssign(
        ref: Operand<T>,
        value: Operand<T>,
        vararg indices: Index
    ): StridedSliceAssign<T> = java.stridedSliceAssign<T>(    
        ref,
        value,
        *indices
        )

    /**
     * Assign `value` to the sliced l-value reference of `ref`.
     *  The values of `value` are assigned to the positions in the variable
     *  `ref` that are selected by the slice parameters. The slice parameters
     *  `begin`, `end`, `strides`, etc. work exactly as in `StridedSlice`.
     *  
     * NOTE this op currently does not support broadcasting and so `value`'s
     *  shape must be exactly the shape produced by the slice of `ref`.
     *
     * @param <T> data type for `output_ref` output
     * @param ref The ref value
     * @param begin The begin value
     * @param end The end value
     * @param strides The strides value
     * @param value The value value
     * @param options carries optional attribute values
     * @param <T> data type for `StridedSliceAssign` output and operands
     * @param <U> data type for `StridedSliceAssign` output and operands
     * @return a new instance of StridedSliceAssign
     * @see org.tensorflow.op.Ops.stridedSliceAssign
     * @param beginMask Sets the beginMask option.
     *
     * @param beginMask the beginMask option
     * @return this Options instance.
     * @param endMask Sets the endMask option.
     *
     * @param endMask the endMask option
     * @return this Options instance.
     * @param ellipsisMask Sets the ellipsisMask option.
     *
     * @param ellipsisMask the ellipsisMask option
     * @return this Options instance.
     * @param newAxisMask Sets the newAxisMask option.
     *
     * @param newAxisMask the newAxisMask option
     * @return this Options instance.
     * @param shrinkAxisMask Sets the shrinkAxisMask option.
     *
     * @param shrinkAxisMask the shrinkAxisMask option
     * @return this Options instance.
     */
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

    /**
     * Returns the gradient of `StridedSlice`.
     *  Since `StridedSlice` cuts out pieces of its `input` which is size
     *  `shape`, its gradient will have the same shape (which is passed here
     *  as `shape`). The gradient will be zero in any element that the slice
     *  does not select.
     *  
     * Arguments are the same as StridedSliceGrad with the exception that
     *  `dy` is the input gradient to be propagated and `shape` is the
     *  shape of `StridedSlice`'s `input`.
     *
     * @param <U> data type for `output` output
     * @param shape The shape value
     * @param begin The begin value
     * @param end The end value
     * @param strides The strides value
     * @param dy The dy value
     * @param options carries optional attribute values
     * @param <U> data type for `StridedSliceGrad` output and operands
     * @param <T> data type for `StridedSliceGrad` output and operands
     * @return a new instance of StridedSliceGrad
     * @see org.tensorflow.op.Ops.stridedSliceGrad
     * @param beginMask Sets the beginMask option.
     *
     * @param beginMask the beginMask option
     * @return this Options instance.
     * @param endMask Sets the endMask option.
     *
     * @param endMask the endMask option
     * @return this Options instance.
     * @param ellipsisMask Sets the ellipsisMask option.
     *
     * @param ellipsisMask the ellipsisMask option
     * @return this Options instance.
     * @param newAxisMask Sets the newAxisMask option.
     *
     * @param newAxisMask the newAxisMask option
     * @return this Options instance.
     * @param shrinkAxisMask Sets the shrinkAxisMask option.
     *
     * @param shrinkAxisMask the shrinkAxisMask option
     * @return this Options instance.
     */
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

    /**
     * Computes the sum of elements across dimensions of a tensor.
     *  Reduces `input` along the dimensions given in `axis`. Unless
     *  `keep_dims` is true, the rank of the tensor is reduced by 1 for each entry in
     *  `axis`. If `keep_dims` is true, the reduced dimensions are
     *  retained with length 1.
     *
     * @param <T> data type for `output` output
     * @param input The tensor to reduce.
     * @param axis The dimensions to reduce. Must be in the range
     *  `[-rank(input), rank(input))`.
     * @param options carries optional attribute values
     * @param <T> data type for `Sum` output and operands
     * @return a new instance of Sum
     * @see org.tensorflow.op.Ops.sum
     * @param keepDims Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public fun <T : TType> sum(
        input: Operand<T>,
        axis: Operand<out TNumber>,
        keepDims: Boolean? = null
    ): Sum<T> = java.sum<T>(    
        input,
        axis,
        *listOfNotNull(
            keepDims?.let{ org.tensorflow.op.core.Sum.keepDims(it) }
        ).toTypedArray()
        )

    /**
     * Forwards `data` to the output port determined by `pred`.
     *  If `pred` is true, the `data` input is forwarded to `output_true`. Otherwise,
     *  the data goes to `output_false`.
     *  
     * See also `RefSwitch` and `Merge`.
     *
     * @param <T> data type for `output_false` output
     * @param data The tensor to be forwarded to the appropriate output.
     * @param pred A scalar that specifies which output port will receive data.
     * @param <T> data type for `Switch` output and operands
     * @return a new instance of SwitchCond
     * @see org.tensorflow.op.Ops.switchCond
     */
    public fun <T : TType> switchCond(`data`: Operand<T>, pred: Operand<TBool>): SwitchCond<T> =
            java.switchCond<T>(    
        data,
        pred
        )

    /**
     * Returns a tensor that may be mutated, but only persists within a single step.
     *  This is an experimental op for internal use only and it is possible to use this
     *  op in unsafe ways.  DO NOT USE unless you fully understand the risks.
     *  
     * It is the caller's responsibility to ensure that 'ref' is eventually passed to a
     *  matching 'DestroyTemporaryVariable' op after all other uses have completed.
     *  
     * Outputs a ref to the tensor state so it may be read or modified.
     *  
     * E.g.
     *  var = state_ops._temporary_variable(&#91;1, 2&#93;, types.float_)
     *  var_name = var.op.name
     *  var = state_ops.assign(var, &#91;[4.0, 5.0&#93;])
     *  var = state_ops.assign_add(var, &#91;[6.0, 7.0&#93;])
     *  final = state_ops._destroy_temporary_variable(var, var_name=var_name)
     *
     * @param <T> data type for `ref` output
     * @param shape The shape of the variable tensor.
     * @param dtype The type of elements in the variable tensor.
     * @param options carries optional attribute values
     * @param <T> data type for `TemporaryVariable` output and operands
     * @return a new instance of TemporaryVariable
     * @see org.tensorflow.op.Ops.temporaryVariable
     * @param varName Sets the varName option.
     *
     * @param varName Overrides the name used for the temporary variable resource. Default
     *  value is the name of the 'TemporaryVariable' op (which is guaranteed unique).
     * @return this Options instance.
     */
    public fun <T : TType> temporaryVariable(
        shape: Shape,
        dtype: Class<T>,
        varName: String? = null
    ): TemporaryVariable<T> = java.temporaryVariable<T>(    
        shape,
        dtype,
        *listOfNotNull(
            varName?.let{ org.tensorflow.op.core.TemporaryVariable.varName(it) }
        ).toTypedArray()
        )

    /**
     * An array of Tensors of given size.
     *  Write data via Write and read via Read or Pack.
     *
     * @param sizeOutput The size of the array.
     * @param dtype The type of the elements on the tensor_array.
     * @param options carries optional attribute values
     * @param <T> data type for `TensorArrayV3` output and operands
     * @return a new instance of TensorArray
     * @see org.tensorflow.op.Ops.tensorArray
     * @param elementShape Sets the elementShape option.
     *
     * @param elementShape The expected shape of an element, if known. Used to
     *  validate the shapes of TensorArray elements. If this shape is not
     *  fully specified, gathering zero-size TensorArrays is an error.
     * @return this Options instance.
     * @param dynamicSize Sets the dynamicSize option.
     *
     * @param dynamicSize A boolean that determines whether writes to the TensorArray
     *  are allowed to grow the size.  By default, this is not allowed.
     * @return this Options instance.
     * @param clearAfterRead Sets the clearAfterRead option.
     *
     * @param clearAfterRead If true (default), Tensors in the TensorArray are cleared
     *  after being read.  This disables multiple read semantics but allows early
     *  release of memory.
     * @return this Options instance.
     * @param identicalElementShapes Sets the identicalElementShapes option.
     *
     * @param identicalElementShapes If true (default is false), then all
     *  elements in the TensorArray will be expected to have identical shapes.
     *  This allows certain behaviors, like dynamically checking for
     *  consistent shapes on write, and being able to fill in properly
     *  shaped zero tensors on stack -- even if the element_shape attribute
     *  is not fully defined.
     * @return this Options instance.
     * @param tensorArrayName Sets the tensorArrayName option.
     *
     * @param tensorArrayName Overrides the name used for the temporary tensor_array
     *  resource. Default value is the name of the 'TensorArray' op (which
     *  is guaranteed unique).
     * @return this Options instance.
     */
    public fun <T : TType> tensorArray(
        sizeOutput: Operand<TInt32>,
        dtype: Class<T>,
        elementShape: Shape? = null,
        dynamicSize: Boolean? = null,
        clearAfterRead: Boolean? = null,
        identicalElementShapes: Boolean? = null,
        tensorArrayName: String? = null
    ): TensorArray = java.tensorArray<T>(    
        sizeOutput,
        dtype,
        *listOfNotNull(
            elementShape?.let{ org.tensorflow.op.core.TensorArray.elementShape(it) },
            dynamicSize?.let{ org.tensorflow.op.core.TensorArray.dynamicSize(it) },
            clearAfterRead?.let{ org.tensorflow.op.core.TensorArray.clearAfterRead(it) },
            identicalElementShapes?.let{ org.tensorflow.op.core.TensorArray.identicalElementShapes(it) },
            tensorArrayName?.let{ org.tensorflow.op.core.TensorArray.tensorArrayName(it) }
        ).toTypedArray()
        )

    /**
     * Delete the TensorArray from its resource container.
     *  This enables the user to close and release the resource in the middle
     *  of a step/run.
     *
     * @param handle The handle to a TensorArray (output of TensorArray or TensorArrayGrad).
     * @return a new instance of TensorArrayClose
     * @see org.tensorflow.op.Ops.tensorArrayClose
     */
    public fun tensorArrayClose(handle: Operand<out TType>): TensorArrayClose =
            java.tensorArrayClose(    
        handle
        )

    /**
     * Concat the elements from the TensorArray into value `value`.
     *  Takes `T` elements of shapes
     *  ```
     * (n0 x d0 x d1 x ...), (n1 x d0 x d1 x ...), ..., (n(T-1) x d0 x d1 x ...)
     *  
     * ```
     *  
     * and concatenates them into a Tensor of shape:
     *  
     * `(n0 + n1 + ... + n(T-1) x d0 x d1 x ...)`
     *  
     * All elements must have the same shape (excepting the first dimension).
     *
     * @param <T> data type for `value` output
     * @param handle The handle to a TensorArray.
     * @param flowIn A float scalar that enforces proper chaining of operations.
     * @param dtype The type of the elem that is returned.
     * @param options carries optional attribute values
     * @param <T> data type for `TensorArrayConcatV3` output and operands
     * @return a new instance of TensorArrayConcat
     * @see org.tensorflow.op.Ops.tensorArrayConcat
     * @param elementShapeExcept0 Sets the elementShapeExcept0 option.
     *
     * @param elementShapeExcept0 The expected shape of an element, if known,
     *  excluding the first dimension. Used to validate the shapes of
     *  TensorArray elements. If this shape is not fully specified, concatenating
     *  zero-size TensorArrays is an error.
     * @return this Options instance.
     */
    public fun <T : TType> tensorArrayConcat(
        handle: Operand<out TType>,
        flowIn: Operand<TFloat32>,
        dtype: Class<T>,
        elementShapeExcept0: Shape? = null
    ): TensorArrayConcat<T> = java.tensorArrayConcat<T>(    
        handle,
        flowIn,
        dtype,
        *listOfNotNull(
            elementShapeExcept0?.let{ org.tensorflow.op.core.TensorArrayConcat.elementShapeExcept0(it) }
        ).toTypedArray()
        )

    /**
     * Gather specific elements from the TensorArray into output `value`.
     *  All elements selected by `indices` must have the same shape.
     *
     * @param <T> data type for `value` output
     * @param handle The handle to a TensorArray.
     * @param indices The locations in the TensorArray from which to read tensor elements.
     * @param flowIn A float scalar that enforces proper chaining of operations.
     * @param dtype The type of the elem that is returned.
     * @param options carries optional attribute values
     * @param <T> data type for `TensorArrayGatherV3` output and operands
     * @return a new instance of TensorArrayGather
     * @see org.tensorflow.op.Ops.tensorArrayGather
     * @param elementShape Sets the elementShape option.
     *
     * @param elementShape The expected shape of an element, if known. Used to
     *  validate the shapes of TensorArray elements. If this shape is not
     *  fully specified, gathering zero-size TensorArrays is an error.
     * @return this Options instance.
     */
    public fun <T : TType> tensorArrayGather(
        handle: Operand<out TType>,
        indices: Operand<TInt32>,
        flowIn: Operand<TFloat32>,
        dtype: Class<T>,
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

    /**
     * Creates a TensorArray for storing the gradients of values in the given handle.
     *  If the given TensorArray gradient already exists, returns a reference to it.
     *  
     * Locks the size of the original TensorArray by disabling its dynamic size flag.
     *  
     * **A note about the input flow_in:**
     *  
     * The handle flow_in forces the execution of the gradient lookup to occur
     *  only after certain other operations have occurred.  For example, when
     *  the forward TensorArray is dynamically sized, writes to this TensorArray
     *  may resize the object.  The gradient TensorArray is statically sized based
     *  on the size of the forward TensorArray when this operation executes.
     *  Furthermore, the size of the forward TensorArray is frozen by this call.
     *  As a result, the flow is used to ensure that the call to generate the gradient
     *  TensorArray only happens after all writes are executed.
     *  
     * In the case of dynamically sized TensorArrays, gradient computation should
     *  only be performed on read operations that have themselves been chained via
     *  flow to occur only after all writes have executed. That way the final size
     *  of the forward TensorArray is known when this operation is called.
     *  
     * **A note about the source attribute:**
     *  
     * TensorArray gradient calls use an accumulator TensorArray object.  If
     *  multiple gradients are calculated and run in the same session, the multiple
     *  gradient nodes may accidentally flow through the same accumulator TensorArray.
     *  This double counts and generally breaks the TensorArray gradient flow.
     *  
     * The solution is to identify which gradient call this particular
     *  TensorArray gradient is being called in.  This is performed by identifying
     *  a unique string (e.g. &quot;gradients&quot;, &quot;gradients_1&quot;, ...) from the input
     *  gradient Tensor's name.  This string is used as a suffix when creating
     *  the TensorArray gradient object here (the attribute `source`).
     *  
     * The attribute `source` is added as a suffix to the forward TensorArray's
     *  name when performing the creation / lookup, so that each separate gradient
     *  calculation gets its own TensorArray accumulator.
     *
     * @param handle The handle to the forward TensorArray.
     * @param flowIn A float scalar that enforces proper chaining of operations.
     * @param source The gradient source string, used to decide which gradient TensorArray
     *  to return.
     * @return a new instance of TensorArrayGrad
     * @see org.tensorflow.op.Ops.tensorArrayGrad
     */
    public fun tensorArrayGrad(
        handle: Operand<out TType>,
        flowIn: Operand<TFloat32>,
        source: String
    ): TensorArrayGrad = java.tensorArrayGrad(    
        handle,
        flowIn,
        source
        )

    /**
     * Creates a TensorArray for storing multiple gradients of values in the given handle.
     *  Similar to TensorArrayGradV3. However it creates an accumulator with an
     *  expanded shape compared to the input TensorArray whose gradient is being
     *  computed. This enables multiple gradients for the same TensorArray to be
     *  calculated using the same accumulator.
     *
     * @param handle The handle to the forward TensorArray.
     * @param flowIn A float scalar that enforces proper chaining of operations.
     * @param shapeToPrepend An int32 vector representing a shape. Elements in the gradient
     * accumulator will
     *  have shape which is this shape_to_prepend value concatenated with shape of the
     *  elements in the TensorArray corresponding to the input handle.
     * @param source The gradient source string, used to decide which gradient TensorArray
     *  to return.
     * @return a new instance of TensorArrayGradWithShape
     * @see org.tensorflow.op.Ops.tensorArrayGradWithShape
     */
    public fun tensorArrayGradWithShape(
        handle: Operand<out TType>,
        flowIn: Operand<TFloat32>,
        shapeToPrepend: Operand<TInt32>,
        source: String
    ): TensorArrayGradWithShape = java.tensorArrayGradWithShape(    
        handle,
        flowIn,
        shapeToPrepend,
        source
        )

    /**
     * The TensorArrayPack operation
     *
     * @param <T> data type for `value` output
     * @param handle The handle value
     * @param flowIn The flowIn value
     * @param dtype The value of the dtype attribute
     * @param options carries optional attribute values
     * @param <T> data type for `TensorArrayPack` output and operands
     * @return a new instance of TensorArrayPack
     * @see org.tensorflow.op.Ops.tensorArrayPack
     * @param elementShape Sets the elementShape option.
     *
     * @param elementShape the elementShape option
     * @return this Options instance.
     */
    public fun <T : TType> tensorArrayPack(
        handle: Operand<TString>,
        flowIn: Operand<TFloat32>,
        dtype: Class<T>,
        elementShape: Shape? = null
    ): TensorArrayPack<T> = java.tensorArrayPack<T>(    
        handle,
        flowIn,
        dtype,
        *listOfNotNull(
            elementShape?.let{ org.tensorflow.op.core.TensorArrayPack.elementShape(it) }
        ).toTypedArray()
        )

    /**
     * Read an element from the TensorArray into output `value`.
     *
     * @param <T> data type for `value` output
     * @param handle The handle to a TensorArray.
     * @param index The index value
     * @param flowIn A float scalar that enforces proper chaining of operations.
     * @param dtype The type of the elem that is returned.
     * @param <T> data type for `TensorArrayReadV3` output and operands
     * @return a new instance of TensorArrayRead
     * @see org.tensorflow.op.Ops.tensorArrayRead
     */
    public fun <T : TType> tensorArrayRead(
        handle: Operand<out TType>,
        index: Operand<TInt32>,
        flowIn: Operand<TFloat32>,
        dtype: Class<T>
    ): TensorArrayRead<T> = java.tensorArrayRead<T>(    
        handle,
        index,
        flowIn,
        dtype
        )

    /**
     * Scatter the data from the input value into specific TensorArray elements.
     *  `indices` must be a vector, its length must match the first dim of `value`.
     *
     * @param handle The handle to a TensorArray.
     * @param indices The locations at which to write the tensor elements.
     * @param value The concatenated tensor to write to the TensorArray.
     * @param flowIn A float scalar that enforces proper chaining of operations.
     * @return a new instance of TensorArrayScatter
     * @see org.tensorflow.op.Ops.tensorArrayScatter
     */
    public fun tensorArrayScatter(
        handle: Operand<out TType>,
        indices: Operand<TInt32>,
        value: Operand<out TType>,
        flowIn: Operand<TFloat32>
    ): TensorArrayScatter = java.tensorArrayScatter(    
        handle,
        indices,
        value,
        flowIn
        )

    /**
     * Get the current size of the TensorArray.
     *
     * @param handle The handle to a TensorArray (output of TensorArray or TensorArrayGrad).
     * @param flowIn A float scalar that enforces proper chaining of operations.
     * @return a new instance of TensorArraySize
     * @see org.tensorflow.op.Ops.tensorArraySize
     */
    public fun tensorArraySize(handle: Operand<out TType>, flowIn: Operand<TFloat32>):
            TensorArraySize = java.tensorArraySize(    
        handle,
        flowIn
        )

    /**
     * Split the data from the input value into TensorArray elements.
     *  Assuming that `lengths` takes on values
     *  
     * `(n0, n1, ..., n(T-1))`
     *  
     * and that `value` has shape
     *  
     * `(n0 + n1 + ... + n(T-1) x d0 x d1 x ...)`,
     *  
     * this splits values into a TensorArray with T tensors.
     *  
     * TensorArray index t will be the subtensor of values with starting position
     *  
     * `(n0 + n1 + ... + n(t-1), 0, 0, ...)`
     *  
     * and having size
     *  
     * `nt x d0 x d1 x ...`
     *
     * @param handle The handle to a TensorArray.
     * @param value The concatenated tensor to write to the TensorArray.
     * @param lengths The vector of lengths, how to split the rows of value into the
     *  TensorArray.
     * @param flowIn A float scalar that enforces proper chaining of operations.
     * @return a new instance of TensorArraySplit
     * @see org.tensorflow.op.Ops.tensorArraySplit
     */
    public fun tensorArraySplit(
        handle: Operand<out TType>,
        value: Operand<out TType>,
        lengths: Operand<TInt64>,
        flowIn: Operand<TFloat32>
    ): TensorArraySplit = java.tensorArraySplit(    
        handle,
        value,
        lengths,
        flowIn
        )

    /**
     * The TensorArrayUnpack operation
     *
     * @param handle The handle value
     * @param value The value value
     * @param flowIn The flowIn value
     * @return a new instance of TensorArrayUnpack
     * @see org.tensorflow.op.Ops.tensorArrayUnpack
     */
    public fun tensorArrayUnpack(
        handle: Operand<TString>,
        value: Operand<out TType>,
        flowIn: Operand<TFloat32>
    ): TensorArrayUnpack = java.tensorArrayUnpack(    
        handle,
        value,
        flowIn
        )

    /**
     * Push an element onto the tensor_array.
     *
     * @param handle The handle to a TensorArray.
     * @param index The position to write to inside the TensorArray.
     * @param value The tensor to write to the TensorArray.
     * @param flowIn A float scalar that enforces proper chaining of operations.
     * @return a new instance of TensorArrayWrite
     * @see org.tensorflow.op.Ops.tensorArrayWrite
     */
    public fun tensorArrayWrite(
        handle: Operand<out TType>,
        index: Operand<TInt32>,
        value: Operand<out TType>,
        flowIn: Operand<TFloat32>
    ): TensorArrayWrite = java.tensorArrayWrite(    
        handle,
        index,
        value,
        flowIn
        )

    /**
     * Concats all tensors in the list along the 0th dimension.
     *  Requires that all tensors have the same shape except the first dimension.
     *  
     * input_handle: The input list.
     *  element_shape: The shape of the uninitialized elements in the list. If the first
     *  dimension is not -1, it is assumed that all list elements have the same
     *  leading dim.
     *  leading_dims: The list of leading dims of uninitialized list elements. Used if
     *  the leading dim of input_handle.element_shape or the element_shape input arg
     *  is not already set.
     *  tensor: The concated result.
     *  lengths: Output tensor containing sizes of the 0th dimension of tensors in the list, used
     * for computing the gradient.
     *
     * @param <U> data type for `tensor` output
     * @param inputHandle The inputHandle value
     * @param elementShape The elementShape value
     * @param leadingDims The leadingDims value
     * @param elementDtype The value of the elementDtype attribute
     * @param <U> data type for `TensorListConcatV2` output and operands
     * @return a new instance of TensorListConcat
     * @see org.tensorflow.op.Ops.tensorListConcat
     */
    public fun <U : TType> tensorListConcat(
        inputHandle: Operand<out TType>,
        elementShape: Operand<out TNumber>,
        leadingDims: Operand<TInt64>,
        elementDtype: Class<U>
    ): TensorListConcat<U> = java.tensorListConcat<U>(    
        inputHandle,
        elementShape,
        leadingDims,
        elementDtype
        )

    /**
     * The TensorListConcatLists operation
     *
     * @param inputA The inputA value
     * @param inputB The inputB value
     * @param elementDtype The value of the elementDtype attribute
     * @param <T> data type for `TensorListConcatLists` output and operands
     * @return a new instance of TensorListConcatLists
     * @see org.tensorflow.op.Ops.tensorListConcatLists
     */
    public fun <T : TType> tensorListConcatLists(
        inputA: Operand<out TType>,
        inputB: Operand<out TType>,
        elementDtype: Class<T>
    ): TensorListConcatLists = java.tensorListConcatLists<T>(    
        inputA,
        inputB,
        elementDtype
        )

    /**
     * The shape of the elements of the given list, as a tensor.
     *  input_handle: the list
     *  element_shape: the shape of elements of the list
     *
     * @param <T> data type for `element_shape` output
     * @param inputHandle The inputHandle value
     * @param shapeType The value of the shapeType attribute
     * @param <T> data type for `TensorListElementShape` output and operands
     * @return a new instance of TensorListElementShape
     * @see org.tensorflow.op.Ops.tensorListElementShape
     */
    public fun <T : TNumber> tensorListElementShape(inputHandle: Operand<out TType>,
            shapeType: Class<T>): TensorListElementShape<T> = java.tensorListElementShape<T>(    
        inputHandle,
        shapeType
        )

    /**
     * Creates a TensorList which, when stacked, has the value of `tensor`.
     *  Each tensor in the result list corresponds to one row of the input tensor.
     *  
     * tensor: The input tensor.
     *  output_handle: The list.
     *
     * @param tensor The tensor value
     * @param elementShape The elementShape value
     * @return a new instance of TensorListFromTensor
     * @see org.tensorflow.op.Ops.tensorListFromTensor
     */
    public fun tensorListFromTensor(tensor: Operand<out TType>, elementShape: Operand<out TNumber>):
            TensorListFromTensor = java.tensorListFromTensor(    
        tensor,
        elementShape
        )

    /**
     * Creates a Tensor by indexing into the TensorList.
     *  Each row in the produced Tensor corresponds to the element in the TensorList
     *  specified by the given index (see `tf.gather`).
     *  
     * input_handle: The input tensor list.
     *  indices: The indices used to index into the list.
     *  values: The tensor.
     *
     * @param <T> data type for `values` output
     * @param inputHandle The inputHandle value
     * @param indices The indices value
     * @param elementShape The elementShape value
     * @param elementDtype The value of the elementDtype attribute
     * @param <T> data type for `TensorListGather` output and operands
     * @return a new instance of TensorListGather
     * @see org.tensorflow.op.Ops.tensorListGather
     */
    public fun <T : TType> tensorListGather(
        inputHandle: Operand<out TType>,
        indices: Operand<TInt32>,
        elementShape: Operand<TInt32>,
        elementDtype: Class<T>
    ): TensorListGather<T> = java.tensorListGather<T>(    
        inputHandle,
        indices,
        elementShape,
        elementDtype
        )

    /**
     * The TensorListGetItem operation
     *
     * @param <T> data type for `item` output
     * @param inputHandle The inputHandle value
     * @param index The index value
     * @param elementShape The elementShape value
     * @param elementDtype The value of the elementDtype attribute
     * @param <T> data type for `TensorListGetItem` output and operands
     * @return a new instance of TensorListGetItem
     * @see org.tensorflow.op.Ops.tensorListGetItem
     */
    public fun <T : TType> tensorListGetItem(
        inputHandle: Operand<out TType>,
        index: Operand<TInt32>,
        elementShape: Operand<TInt32>,
        elementDtype: Class<T>
    ): TensorListGetItem<T> = java.tensorListGetItem<T>(    
        inputHandle,
        index,
        elementShape,
        elementDtype
        )

    /**
     * Returns the number of tensors in the input tensor list.
     *  input_handle: the input list
     *  length: the number of tensors in the list
     *
     * @param inputHandle The inputHandle value
     * @return a new instance of TensorListLength
     * @see org.tensorflow.op.Ops.tensorListLength
     */
    public fun tensorListLength(inputHandle: Operand<out TType>): TensorListLength =
            java.tensorListLength(    
        inputHandle
        )

    /**
     * Returns the last element of the input list as well as a list with all but that element.
     *  Fails if the list is empty.
     *  
     * input_handle: the input list
     *  tensor: the withdrawn last element of the list
     *  element_dtype: the type of elements in the list
     *  element_shape: the shape of the output tensor
     *
     * @param <T> data type for `tensor` output
     * @param inputHandle The inputHandle value
     * @param elementShape The elementShape value
     * @param elementDtype The value of the elementDtype attribute
     * @param <T> data type for `TensorListPopBack` output and operands
     * @return a new instance of TensorListPopBack
     * @see org.tensorflow.op.Ops.tensorListPopBack
     */
    public fun <T : TType> tensorListPopBack(
        inputHandle: Operand<out TType>,
        elementShape: Operand<TInt32>,
        elementDtype: Class<T>
    ): TensorListPopBack<T> = java.tensorListPopBack<T>(    
        inputHandle,
        elementShape,
        elementDtype
        )

    /**
     * Returns a list which has the passed-in `Tensor` as last element and the other elements of the
     * given list in `input_handle`.
     *  tensor: The tensor to put on the list.
     *  input_handle: The old list.
     *  output_handle: A list with the elements of the old list followed by tensor.
     *  element_dtype: the type of elements in the list.
     *  element_shape: a shape compatible with that of elements in the list.
     *
     * @param inputHandle The inputHandle value
     * @param tensor The tensor value
     * @return a new instance of TensorListPushBack
     * @see org.tensorflow.op.Ops.tensorListPushBack
     */
    public fun tensorListPushBack(inputHandle: Operand<out TType>, tensor: Operand<out TType>):
            TensorListPushBack = java.tensorListPushBack(    
        inputHandle,
        tensor
        )

    /**
     * The TensorListPushBackBatch operation
     *
     * @param inputHandles The inputHandles value
     * @param tensor The tensor value
     * @return a new instance of TensorListPushBackBatch
     * @see org.tensorflow.op.Ops.tensorListPushBackBatch
     */
    public fun tensorListPushBackBatch(inputHandles: Operand<out TType>, tensor: Operand<out
            TType>): TensorListPushBackBatch = java.tensorListPushBackBatch(    
        inputHandles,
        tensor
        )

    /**
     * List of the given size with empty elements.
     *  element_shape: the shape of the future elements of the list
     *  num_elements: the number of elements to reserve
     *  handle: the output list
     *  element_dtype: the desired type of elements in the list.
     *
     * @param elementShape The elementShape value
     * @param numElements The numElements value
     * @param elementDtype The value of the elementDtype attribute
     * @param <U> data type for `TensorListReserve` output and operands
     * @return a new instance of TensorListReserve
     * @see org.tensorflow.op.Ops.tensorListReserve
     */
    public fun <U : TType> tensorListReserve(
        elementShape: Operand<out TNumber>,
        numElements: Operand<TInt32>,
        elementDtype: Class<U>
    ): TensorListReserve = java.tensorListReserve<U>(    
        elementShape,
        numElements,
        elementDtype
        )

    /**
     * Resizes the list.
     *  input_handle: the input list
     *  size: size of the output list
     *
     * @param inputHandle The inputHandle value
     * @param sizeOutput The sizeOutput value
     * @return a new instance of TensorListResize
     * @see org.tensorflow.op.Ops.tensorListResize
     */
    public fun tensorListResize(inputHandle: Operand<out TType>, sizeOutput: Operand<TInt32>):
            TensorListResize = java.tensorListResize(    
        inputHandle,
        sizeOutput
        )

    /**
     * Creates a TensorList by indexing into a Tensor.
     *  Each member of the TensorList corresponds to one row of the input tensor,
     *  specified by the given index (see `tf.gather`).
     *  
     * tensor: The input tensor.
     *  indices: The indices used to index into the list.
     *  element_shape: The shape of the elements in the list (can be less specified than
     *  the shape of the tensor).
     *  num_elements: The size of the output list. Must be large enough to accommodate
     *  the largest index in indices. If -1, the list is just large enough to include
     *  the largest index in indices.
     *  output_handle: The TensorList.
     *
     * @param tensor The tensor value
     * @param indices The indices value
     * @param elementShape The elementShape value
     * @param numElements The numElements value
     * @return a new instance of TensorListScatter
     * @see org.tensorflow.op.Ops.tensorListScatter
     */
    public fun tensorListScatter(
        tensor: Operand<out TType>,
        indices: Operand<TInt32>,
        elementShape: Operand<out TNumber>,
        numElements: Operand<TInt32>
    ): TensorListScatter = java.tensorListScatter(    
        tensor,
        indices,
        elementShape,
        numElements
        )

    /**
     * Scatters tensor at indices in an input list.
     *  Each member of the TensorList corresponds to one row of the input tensor,
     *  specified by the given index (see `tf.gather`).
     *  
     * input_handle: The list to scatter into.
     *  tensor: The input tensor.
     *  indices: The indices used to index into the list.
     *  output_handle: The TensorList.
     *
     * @param inputHandle The inputHandle value
     * @param tensor The tensor value
     * @param indices The indices value
     * @return a new instance of TensorListScatterIntoExistingList
     * @see org.tensorflow.op.Ops.tensorListScatterIntoExistingList
     */
    public fun tensorListScatterIntoExistingList(
        inputHandle: Operand<out TType>,
        tensor: Operand<out TType>,
        indices: Operand<TInt32>
    ): TensorListScatterIntoExistingList = java.tensorListScatterIntoExistingList(    
        inputHandle,
        tensor,
        indices
        )

    /**
     * The TensorListSetItem operation
     *
     * @param inputHandle The inputHandle value
     * @param index The index value
     * @param item The item value
     * @return a new instance of TensorListSetItem
     * @see org.tensorflow.op.Ops.tensorListSetItem
     */
    public fun tensorListSetItem(
        inputHandle: Operand<out TType>,
        index: Operand<TInt32>,
        item: Operand<out TType>
    ): TensorListSetItem = java.tensorListSetItem(    
        inputHandle,
        index,
        item
        )

    /**
     * Splits a tensor into a list.
     *  list[i] corresponds to lengths[i] tensors from the input tensor.
     *  The tensor must have rank at least 1 and contain exactly sum(lengths) elements.
     *  
     * tensor: The input tensor.
     *  element_shape: A shape compatible with that of elements in the tensor.
     *  lengths: Vector of sizes of the 0th dimension of tensors in the list.
     *  output_handle: The list.
     *
     * @param tensor The tensor value
     * @param elementShape The elementShape value
     * @param lengths The lengths value
     * @return a new instance of TensorListSplit
     * @see org.tensorflow.op.Ops.tensorListSplit
     */
    public fun tensorListSplit(
        tensor: Operand<out TType>,
        elementShape: Operand<out TNumber>,
        lengths: Operand<TInt64>
    ): TensorListSplit = java.tensorListSplit(    
        tensor,
        elementShape,
        lengths
        )

    /**
     * Stacks all tensors in the list.
     *  Requires that all tensors have the same shape.
     *  
     * input_handle: the input list
     *  tensor: the gathered result
     *  num_elements: optional. If not -1, the number of elements in the list.
     *
     * @param <T> data type for `tensor` output
     * @param inputHandle The inputHandle value
     * @param elementShape The elementShape value
     * @param elementDtype The value of the elementDtype attribute
     * @param options carries optional attribute values
     * @param <T> data type for `TensorListStack` output and operands
     * @return a new instance of TensorListStack
     * @see org.tensorflow.op.Ops.tensorListStack
     * @param numElements Sets the numElements option.
     *
     * @param numElements the numElements option
     * @return this Options instance.
     */
    public fun <T : TType> tensorListStack(
        inputHandle: Operand<out TType>,
        elementShape: Operand<TInt32>,
        elementDtype: Class<T>,
        numElements: Long? = null
    ): TensorListStack<T> = java.tensorListStack<T>(    
        inputHandle,
        elementShape,
        elementDtype,
        *listOfNotNull(
            numElements?.let{ org.tensorflow.op.core.TensorListStack.numElements(it) }
        ).toTypedArray()
        )

    /**
     * Returns a tensor map with item from given key erased.
     *  input_handle: the original map
     *  output_handle: the map with value from given key removed
     *  key: the key of the value to be erased
     *
     * @param inputHandle The inputHandle value
     * @param key The key value
     * @param valueDtype The value of the valueDtype attribute
     * @param <U> data type for `TensorMapErase` output and operands
     * @return a new instance of TensorMapErase
     * @see org.tensorflow.op.Ops.tensorMapErase
     */
    public fun <U : TType> tensorMapErase(
        inputHandle: Operand<out TType>,
        key: Operand<out TType>,
        valueDtype: Class<U>
    ): TensorMapErase = java.tensorMapErase<U>(    
        inputHandle,
        key,
        valueDtype
        )

    /**
     * Returns whether the given key exists in the map.
     *  input_handle: the input map
     *  key: the key to check
     *  has_key: whether the key is already in the map or not
     *
     * @param inputHandle The inputHandle value
     * @param key The key value
     * @return a new instance of TensorMapHasKey
     * @see org.tensorflow.op.Ops.tensorMapHasKey
     */
    public fun tensorMapHasKey(inputHandle: Operand<out TType>, key: Operand<out TType>):
            TensorMapHasKey = java.tensorMapHasKey(    
        inputHandle,
        key
        )

    /**
     * Returns a map that is the 'input_handle' with the given key-value pair inserted.
     *  input_handle: the original map
     *  output_handle: the map with key and value inserted
     *  key: the key to be inserted
     *  value: the value to be inserted
     *
     * @param inputHandle The inputHandle value
     * @param key The key value
     * @param value The value value
     * @return a new instance of TensorMapInsert
     * @see org.tensorflow.op.Ops.tensorMapInsert
     */
    public fun tensorMapInsert(
        inputHandle: Operand<out TType>,
        key: Operand<out TType>,
        value: Operand<out TType>
    ): TensorMapInsert = java.tensorMapInsert(    
        inputHandle,
        key,
        value
        )

    /**
     * Returns the value from a given key in a tensor map.
     *  input_handle: the input map
     *  key: the key to be looked up
     *  value: the value found from the given key
     *
     * @param <U> data type for `value` output
     * @param inputHandle The inputHandle value
     * @param key The key value
     * @param valueDtype The value of the valueDtype attribute
     * @param <U> data type for `TensorMapLookup` output and operands
     * @return a new instance of TensorMapLookup
     * @see org.tensorflow.op.Ops.tensorMapLookup
     */
    public fun <U : TType> tensorMapLookup(
        inputHandle: Operand<out TType>,
        key: Operand<out TType>,
        valueDtype: Class<U>
    ): TensorMapLookup<U> = java.tensorMapLookup<U>(    
        inputHandle,
        key,
        valueDtype
        )

    /**
     * Returns the number of tensors in the input tensor map.
     *  input_handle: the input map
     *  size: the number of tensors in the map
     *
     * @param inputHandle The inputHandle value
     * @return a new instance of TensorMapSize
     * @see org.tensorflow.op.Ops.tensorMapSize
     */
    public fun tensorMapSize(inputHandle: Operand<out TType>): TensorMapSize = java.tensorMapSize(    
        inputHandle
        )

    /**
     * Returns a Tensor stack of all keys in a tensor map.
     *  input_handle: the input map
     *  keys: the returned Tensor of all keys in the map
     *
     * @param <T> data type for `keys` output
     * @param inputHandle The inputHandle value
     * @param keyDtype The value of the keyDtype attribute
     * @param <T> data type for `TensorMapStackKeys` output and operands
     * @return a new instance of TensorMapStackKeys
     * @see org.tensorflow.op.Ops.tensorMapStackKeys
     */
    public fun <T : TType> tensorMapStackKeys(inputHandle: Operand<out TType>, keyDtype: Class<T>):
            TensorMapStackKeys<T> = java.tensorMapStackKeys<T>(    
        inputHandle,
        keyDtype
        )

    /**
     * Adds sparse `updates` to an existing tensor according to `indices`.
     *  This operation creates a new tensor by adding sparse `updates` to the passed
     *  in `tensor`.
     *  This operation is very similar to `tf.compat.v1.scatter_nd_add`, except that the updates
     *  are added onto an existing tensor (as opposed to a variable). If the memory
     *  for the existing tensor cannot be re-used, a copy is made and updated.
     *  
     * `indices` is an integer tensor containing indices into a new tensor of shape
     *  `tensor.shape`.  The last dimension of `indices` can be at most the rank of
     *  `tensor.shape`:
     *  ```
     * indices.shape[-1] <= tensor.shape.rank
     *  
     * ```
     *  
     * The last dimension of `indices` corresponds to indices into elements
     *  (if `indices.shape&#91;-1&#93; = tensor.shape.rank`) or slices
     *  (if `indices.shape&#91;-1&#93; < tensor.shape.rank`) along dimension
     *  `indices.shape&#91;-1&#93;` of `tensor.shape`.  `updates` is a tensor with shape
     *  ```
     * indices.shape[:-1] + tensor.shape[indices.shape[-1]:]
     *  
     * ```
     *  
     * The simplest form of tensor_scatter_add is to add individual elements to a
     *  tensor by index. For example, say we want to add 4 elements in a rank-1
     *  tensor with 8 elements.
     *  
     * In Python, this scatter add operation would look like this:
     *  ```
     * indices = tf.constant([[4], [3], [1], [7]])
     *      updates = tf.constant([9, 10, 11, 12])
     *      tensor = tf.ones([8], dtype=tf.int32)
     *      updated = tf.tensor_scatter_nd_add(tensor, indices, updates)
     *      print(updated)
     *  
     * ```
     *  
     * The resulting tensor would look like this:
     *  ```
     * [1, 12, 1, 11, 10, 1, 1, 13]
     *  
     * ```
     *  
     * We can also, insert entire slices of a higher rank tensor all at once. For
     *  example, if we wanted to insert two slices in the first dimension of a
     *  rank-3 tensor with two matrices of new values.
     *  
     * In Python, this scatter add operation would look like this:
     *  ```
     * indices = tf.constant([[0], [2]])
     *      updates = tf.constant([[[5, 5, 5, 5], [6, 6, 6, 6],
     *                              [7, 7, 7, 7], [8, 8, 8, 8]],
     *                             [[5, 5, 5, 5], [6, 6, 6, 6],
     *                              [7, 7, 7, 7], [8, 8, 8, 8]]])
     *      tensor = tf.ones([4, 4, 4],dtype=tf.int32)
     *      updated = tf.tensor_scatter_nd_add(tensor, indices, updates)
     *      print(updated)
     *  
     * ```
     *  
     * The resulting tensor would look like this:
     *  ```
     * [[[6, 6, 6, 6], [7, 7, 7, 7], [8, 8, 8, 8], [9, 9, 9, 9]],
     *   [[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]],
     *   [[6, 6, 6, 6], [7, 7, 7, 7], [8, 8, 8, 8], [9, 9, 9, 9]],
     *   [[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]]]
     *  
     * ```
     *  
     * Note that on CPU, if an out of bound index is found, an error is returned.
     *  On GPU, if an out of bound index is found, the index is ignored.
     *
     * @param <T> data type for `output` output
     * @param tensor Tensor to copy/update.
     * @param indices Index tensor.
     * @param updates Updates to scatter into output.
     * @param <T> data type for `TensorScatterAdd` output and operands
     * @return a new instance of TensorScatterNdAdd
     * @see org.tensorflow.op.Ops.tensorScatterNdAdd
     */
    public fun <T : TType> tensorScatterNdAdd(
        tensor: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>
    ): TensorScatterNdAdd<T> = java.tensorScatterNdAdd<T>(    
        tensor,
        indices,
        updates
        )

    /**
     * The TensorScatterMax operation
     *
     * @param <T> data type for `output` output
     * @param tensor Tensor to update.
     * @param indices Index tensor.
     * @param updates Updates to scatter into output.
     * @param <T> data type for `TensorScatterMax` output and operands
     * @return a new instance of TensorScatterNdMax
     * @see org.tensorflow.op.Ops.tensorScatterNdMax
     */
    public fun <T : TType> tensorScatterNdMax(
        tensor: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>
    ): TensorScatterNdMax<T> = java.tensorScatterNdMax<T>(    
        tensor,
        indices,
        updates
        )

    /**
     * The TensorScatterMin operation
     *
     * @param <T> data type for `output` output
     * @param tensor Tensor to update.
     * @param indices Index tensor.
     * @param updates Updates to scatter into output.
     * @param <T> data type for `TensorScatterMin` output and operands
     * @return a new instance of TensorScatterNdMin
     * @see org.tensorflow.op.Ops.tensorScatterNdMin
     */
    public fun <T : TType> tensorScatterNdMin(
        tensor: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>
    ): TensorScatterNdMin<T> = java.tensorScatterNdMin<T>(    
        tensor,
        indices,
        updates
        )

    /**
     * Subtracts sparse `updates` from an existing tensor according to `indices`.
     *  This operation creates a new tensor by subtracting sparse `updates` from the
     *  passed in `tensor`.
     *  This operation is very similar to `tf.scatter_nd_sub`, except that the updates
     *  are subtracted from an existing tensor (as opposed to a variable). If the memory
     *  for the existing tensor cannot be re-used, a copy is made and updated.
     *  
     * `indices` is an integer tensor containing indices into a new tensor of shape
     *  `shape`.  The last dimension of `indices` can be at most the rank of `shape`:
     *  ```
     * indices.shape[-1] <= shape.rank
     *  
     * ```
     *  
     * The last dimension of `indices` corresponds to indices into elements
     *  (if `indices.shape&#91;-1&#93; = shape.rank`) or slices
     *  (if `indices.shape&#91;-1&#93; < shape.rank`) along dimension `indices.shape&#91;-1&#93;` of
     *  `shape`.  `updates` is a tensor with shape
     *  ```
     * indices.shape[:-1] + shape[indices.shape[-1]:]
     *  
     * ```
     *  
     * The simplest form of tensor_scatter_sub is to subtract individual elements
     *  from a tensor by index. For example, say we want to insert 4 scattered elements
     *  in a rank-1 tensor with 8 elements.
     *  
     * In Python, this scatter subtract operation would look like this:
     *  ```
     * indices = tf.constant([[4], [3], [1], [7]])
     *      updates = tf.constant([9, 10, 11, 12])
     *      tensor = tf.ones([8], dtype=tf.int32)
     *      updated = tf.tensor_scatter_nd_sub(tensor, indices, updates)
     *      print(updated)
     *  
     * ```
     *  
     * The resulting tensor would look like this:
     *  ```
     * [1, -10, 1, -9, -8, 1, 1, -11]
     *  
     * ```
     *  
     * We can also, insert entire slices of a higher rank tensor all at once. For
     *  example, if we wanted to insert two slices in the first dimension of a
     *  rank-3 tensor with two matrices of new values.
     *  
     * In Python, this scatter add operation would look like this:
     *  ```
     * indices = tf.constant([[0], [2]])
     *      updates = tf.constant([[[5, 5, 5, 5], [6, 6, 6, 6],
     *                              [7, 7, 7, 7], [8, 8, 8, 8]],
     *                             [[5, 5, 5, 5], [6, 6, 6, 6],
     *                              [7, 7, 7, 7], [8, 8, 8, 8]]])
     *      tensor = tf.ones([4, 4, 4],dtype=tf.int32)
     *      updated = tf.tensor_scatter_nd_sub(tensor, indices, updates)
     *      print(updated)
     *  
     * ```
     *  
     * The resulting tensor would look like this:
     *  ```
     * [[[-4, -4, -4, -4], [-5, -5, -5, -5], [-6, -6, -6, -6], [-7, -7, -7, -7]],
     *   [[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]],
     *   [[-4, -4, -4, -4], [-5, -5, -5, -5], [-6, -6, -6, -6], [-7, -7, -7, -7]],
     *   [[1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1]]]
     *  
     * ```
     *  
     * Note that on CPU, if an out of bound index is found, an error is returned.
     *  On GPU, if an out of bound index is found, the index is ignored.
     *
     * @param <T> data type for `output` output
     * @param tensor Tensor to copy/update.
     * @param indices Index tensor.
     * @param updates Updates to scatter into output.
     * @param <T> data type for `TensorScatterSub` output and operands
     * @return a new instance of TensorScatterNdSub
     * @see org.tensorflow.op.Ops.tensorScatterNdSub
     */
    public fun <T : TType> tensorScatterNdSub(
        tensor: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>
    ): TensorScatterNdSub<T> = java.tensorScatterNdSub<T>(    
        tensor,
        indices,
        updates
        )

    /**
     * Scatter `updates` into an existing tensor according to `indices`.
     *  This operation creates a new tensor by applying sparse `updates` to the passed
     *  in `tensor`.
     *  This operation is very similar to `tf.scatter_nd`, except that the updates are
     *  scattered onto an existing tensor (as opposed to a zero-tensor). If the memory
     *  for the existing tensor cannot be re-used, a copy is made and updated.
     *  
     * If `indices` contains duplicates, then we pick the last update for the index.
     *  
     * If an out of bound index is found on CPU, an error is returned.
     *  
     * **WARNING**: There are some GPU specific semantics for this operation.
     *  <ul>
     *  <li>If an out of bound index is found, the index is ignored.</li>
     *  <li>The order in which updates are applied is nondeterministic, so the output
     *  will be nondeterministic if `indices` contains duplicates.</li>
     *  </ul>
     *  
     * `indices` is an integer tensor containing indices into a new tensor of shape
     *  `shape`.
     *  <ul>
     *  <li>`indices` must have at least 2 axes: `(num_updates, index_depth)`.</li>
     *  <li>The last axis of `indices` is how deep to index into `tensor` so  this index
     *  depth must be less than the rank of `tensor`: `indices.shape&#91;-1&#93; <=
     * tensor.ndim`</li>
     *  </ul>
     *  
     * if `indices.shape&#91;-1&#93; = tensor.rank` this Op indexes and updates scalar elements.
     *  if `indices.shape&#91;-1&#93; < tensor.rank` it indexes and updates slices of the input
     *  `tensor`.
     *  
     * Each `update` has a rank of `tensor.rank - indices.shape&#91;-1&#93;`.
     *  The overall shape of `updates` is:
     *  ```
     * indices.shape[:-1] + tensor.shape[indices.shape[-1]:]
     *  
     * ```
     *  
     * For usage examples see the python  tf.tensor_scatter_nd_update
     * [org.tensorflow.op.Ops.tensorScatterNdUpdate]  function
     *
     * @param <T> data type for `output` output
     * @param tensor Tensor to copy/update.
     * @param indices Index tensor.
     * @param updates Updates to scatter into output.
     * @param <T> data type for `TensorScatterUpdate` output and operands
     * @return a new instance of TensorScatterNdUpdate
     * @see org.tensorflow.op.Ops.tensorScatterNdUpdate
     */
    public fun <T : TType> tensorScatterNdUpdate(
        tensor: Operand<T>,
        indices: Operand<out TNumber>,
        updates: Operand<T>
    ): TensorScatterNdUpdate<T> = java.tensorScatterNdUpdate<T>(    
        tensor,
        indices,
        updates
        )

    /**
     * Assign `value` to the sliced l-value reference of `input`.
     *  The values of `value` are assigned to the positions in the tensor `input` that
     *  are selected by the slice parameters. The slice parameters `begin` `end`
     *  `strides` etc. work exactly as in `StridedSlice`.
     *  
     * NOTE this op currently does not support broadcasting and so `value`'s shape
     *  must be exactly the shape produced by the slice of `input`.
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param begin The begin value
     * @param end The end value
     * @param strides The strides value
     * @param value The value value
     * @param options carries optional attribute values
     * @param <T> data type for `TensorStridedSliceUpdate` output and operands
     * @param <U> data type for `TensorStridedSliceUpdate` output and operands
     * @return a new instance of TensorStridedSliceUpdate
     * @see org.tensorflow.op.Ops.tensorStridedSliceUpdate
     * @param beginMask Sets the beginMask option.
     *
     * @param beginMask the beginMask option
     * @return this Options instance.
     * @param endMask Sets the endMask option.
     *
     * @param endMask the endMask option
     * @return this Options instance.
     * @param ellipsisMask Sets the ellipsisMask option.
     *
     * @param ellipsisMask the ellipsisMask option
     * @return this Options instance.
     * @param newAxisMask Sets the newAxisMask option.
     *
     * @param newAxisMask the newAxisMask option
     * @return this Options instance.
     * @param shrinkAxisMask Sets the shrinkAxisMask option.
     *
     * @param shrinkAxisMask the shrinkAxisMask option
     * @return this Options instance.
     */
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

    /**
     * Constructs a tensor by tiling a given tensor.
     *  This operation creates a new tensor by replicating `input` `multiples` times.
     *  The output tensor's i'th dimension has `input.dims(i) * multiples[i]` elements,
     *  and the values of `input` are replicated `multiples[i]` times along the 'i'th
     *  dimension. For example, tiling `&#91;a b c d&#93;` by `[2]` produces
     *  `&#91;a b c d a b c d&#93;`.
     *  ```
     *
     * a = tf.constant([[1,2,3],[4,5,6]], tf.int32)
     *  b = tf.constant([1,2], tf.int32)
     *  tf.tile(a, b)
     *  <tf.Tensor: shape=(2, 6), dtype=int32, numpy=
     *  array([[1, 2, 3, 1, 2, 3],
     *  [4, 5, 6, 4, 5, 6]], dtype=int32)>
     *  c = tf.constant([2,1], tf.int32)
     *  tf.tile(a, c)
     *  <tf.Tensor: shape=(4, 3), dtype=int32, numpy=
     *  array([[1, 2, 3],
     *  [4, 5, 6],
     *  [1, 2, 3],
     *  [4, 5, 6]], dtype=int32)>
     *  d = tf.constant([2,2], tf.int32)
     *  tf.tile(a, d)
     *  <tf.Tensor: shape=(4, 6), dtype=int32, numpy=
     *  array([[1, 2, 3, 1, 2, 3],
     *  [4, 5, 6, 4, 5, 6],
     *  [1, 2, 3, 1, 2, 3],
     *  [4, 5, 6, 4, 5, 6]], dtype=int32)>
     * ```
     *
     * @param <T> data type for `output` output
     * @param input 1-D or higher.
     * @param multiples 1-D. Length must be the same as the number of dimensions in `input`
     * @param <T> data type for `Tile` output and operands
     * @return a new instance of Tile
     * @see org.tensorflow.op.Ops.tile
     */
    public fun <T : TType> tile(input: Operand<T>, multiples: Operand<out TNumber>): Tile<T> =
            java.tile<T>(    
        input,
        multiples
        )

    /**
     * Provides the time since epoch in seconds.
     *  Returns the timestamp as a `float64` for seconds since the Unix epoch.
     *  
     * Note: the timestamp is computed when the op is executed, not when it is added
     *  to the graph.
     *
     * @return a new instance of Timestamp
     * @see org.tensorflow.op.Ops.timestamp
     */
    public fun timestamp(): Timestamp = java.timestamp(    
        
        )

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
     * @param input The input value
     * @param k The value of the k attribute
     * @return a new instance of TopKUnique
     * @see org.tensorflow.op.Ops.topKUnique
     */
    public fun topKUnique(input: Operand<TFloat32>, k: Long): TopKUnique = java.topKUnique(    
        input,
        k
        )

    /**
     * Returns the TopK values in the array in sorted order.
     *  This is a combination of MakeUnique and TopKUnique. The returned top-K will
     *  have its lower bits replaced by iota, thus it will be close to the original
     *  value but not exactly the same. The running time is proportional to the product
     *  of K and the input size. NaNs are never returned. Subnormal numbers are flushed
     *  to zero.
     *
     * @param input The input value
     * @param k The value of the k attribute
     * @return a new instance of TopKWithUnique
     * @see org.tensorflow.op.Ops.topKWithUnique
     */
    public fun topKWithUnique(input: Operand<TFloat32>, k: Long): TopKWithUnique =
            java.topKWithUnique(    
        input,
        k
        )

    /**
     * Reverses the operation of Batch for a single output Tensor.
     *  An instance of Unbatch either receives an empty batched_tensor, in which case it
     *  asynchronously waits until the values become available from a concurrently
     *  running instance of Unbatch with the same container and shared_name, or receives
     *  a non-empty batched_tensor in which case it finalizes all other concurrently
     *  running instances and outputs its own element from the batch.
     *  
     * batched_tensor: The possibly transformed output of Batch. The size of the first
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
     * @param <T> data type for `unbatched_tensor` output
     * @param batchedTensor The batchedTensor value
     * @param batchIndex The batchIndex value
     * @param id The id value
     * @param timeoutMicros The value of the timeoutMicros attribute
     * @param options carries optional attribute values
     * @param <T> data type for `Unbatch` output and operands
     * @return a new instance of Unbatch
     * @see org.tensorflow.op.Ops.unbatch
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
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

    /**
     * Gradient of Unbatch.
     *  Acts like Batch but using the given batch_index index of batching things as they
     *  become available. This ensures that the gradients are propagated back in the
     *  same session which did the forward pass.
     *  
     * original_input: The input to the Unbatch operation this is the gradient of.
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
     * @param <T> data type for `batched_grad` output
     * @param originalInput The originalInput value
     * @param batchIndex The batchIndex value
     * @param grad The grad value
     * @param id The id value
     * @param options carries optional attribute values
     * @param <T> data type for `UnbatchGrad` output and operands
     * @return a new instance of UnbatchGrad
     * @see org.tensorflow.op.Ops.unbatchGrad
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
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

    /**
     * Finds unique elements along an axis of a tensor.
     *  This operation either returns a tensor `y` containing unique elements
     *  along the `axis` of a tensor. The returned unique elements is sorted
     *  in the same order as they occur along `axis` in `x`.
     *  This operation also returns a tensor `idx` that is the same size as
     *  the number of the elements in `x` along the `axis` dimension. It
     *  contains the index in the unique output `y`.
     *  In other words, for an `1-D` tensor `x` with `axis = None:
     *  
     * `y&#91;idx[i&#93;] = x[i] for i in &#91;0, 1,...,rank(x) - 1&#93;`
     *  
     * For example:
     *  ```
     * # tensor 'x' is [1, 1, 2, 4, 4, 4, 7, 8, 8]
     *  y, idx = unique(x)
     *  y ==> [1, 2, 4, 7, 8]
     *  idx ==> [0, 0, 1, 2, 2, 2, 3, 4, 4]
     *  
     * ```
     *  
     * For an `2-D` tensor `x` with `axis = 0`:
     *  ```
     * # tensor 'x' is [[1, 0, 0],
     *  #                [1, 0, 0],
     *  #                [2, 0, 0]]
     *  y, idx = unique(x, axis=0)
     *  y ==> [[1, 0, 0],
     *         [2, 0, 0]]
     *  idx ==> [0, 0, 1]
     *  
     * ```
     *  
     * For an `2-D` tensor `x` with `axis = 1`:
     *  ```
     * # tensor 'x' is [[1, 0, 0],
     *  #                [1, 0, 0],
     *  #                [2, 0, 0]]
     *  y, idx = unique(x, axis=1)
     *  y ==> [[1, 0],
     *         [1, 0],
     *         [2, 0]]
     *  idx ==> [0, 1, 1]
     *  
     * ```
     *
     * @param <T> data type for `y` output
     * @param <V> data type for `idx` output
     * @param x A `Tensor`.
     * @param axis A `Tensor` of type `int32` (default: None). The axis of the Tensor to
     *  find the unique elements.
     * @param <T> data type for `UniqueV2` output and operands
     * @return a new instance of Unique, with default output types
     * @see org.tensorflow.op.Ops.unique
     */
    public fun <T : TType> unique(x: Operand<T>, axis: Operand<out TNumber>): Unique<T, TInt32> =
            java.unique<T>(    
        x,
        axis
        )

    /**
     * Finds unique elements along an axis of a tensor.
     *  This operation either returns a tensor `y` containing unique elements
     *  along the `axis` of a tensor. The returned unique elements is sorted
     *  in the same order as they occur along `axis` in `x`.
     *  This operation also returns a tensor `idx` that is the same size as
     *  the number of the elements in `x` along the `axis` dimension. It
     *  contains the index in the unique output `y`.
     *  In other words, for an `1-D` tensor `x` with `axis = None:
     *  
     * `y&#91;idx[i&#93;] = x[i] for i in &#91;0, 1,...,rank(x) - 1&#93;`
     *  
     * For example:
     *  ```
     * # tensor 'x' is [1, 1, 2, 4, 4, 4, 7, 8, 8]
     *  y, idx = unique(x)
     *  y ==> [1, 2, 4, 7, 8]
     *  idx ==> [0, 0, 1, 2, 2, 2, 3, 4, 4]
     *  
     * ```
     *  
     * For an `2-D` tensor `x` with `axis = 0`:
     *  ```
     * # tensor 'x' is [[1, 0, 0],
     *  #                [1, 0, 0],
     *  #                [2, 0, 0]]
     *  y, idx = unique(x, axis=0)
     *  y ==> [[1, 0, 0],
     *         [2, 0, 0]]
     *  idx ==> [0, 0, 1]
     *  
     * ```
     *  
     * For an `2-D` tensor `x` with `axis = 1`:
     *  ```
     * # tensor 'x' is [[1, 0, 0],
     *  #                [1, 0, 0],
     *  #                [2, 0, 0]]
     *  y, idx = unique(x, axis=1)
     *  y ==> [[1, 0],
     *         [1, 0],
     *         [2, 0]]
     *  idx ==> [0, 1, 1]
     *  
     * ```
     *
     * @param <T> data type for `y` output
     * @param <V> data type for `idx` output
     * @param x A `Tensor`.
     * @param axis A `Tensor` of type `int32` (default: None). The axis of the Tensor to
     *  find the unique elements.
     * @param outIdx The value of the outIdx attribute
     * @param <T> data type for `UniqueV2` output and operands
     * @param <V> data type for `UniqueV2` output and operands
     * @return a new instance of Unique
     * @see org.tensorflow.op.Ops.unique
     */
    public fun <T : TType, V : TNumber> unique(
        x: Operand<T>,
        axis: Operand<out TNumber>,
        outIdx: Class<V>
    ): Unique<T, V> = java.unique<T, V>(    
        x,
        axis,
        outIdx
        )

    /**
     * Finds unique elements along an axis of a tensor.
     *  This operation either returns a tensor `y` containing unique elements
     *  along the `axis` of a tensor. The returned unique elements is sorted
     *  in the same order as they occur along `axis` in `x`.
     *  This operation also returns a tensor `idx` and a tensor `count`
     *  that are the same size as the number of the elements in `x` along the
     *  `axis` dimension. The `idx` contains the index in the unique output `y`
     *  and the `count` contains the count in the unique output `y`.
     *  In other words, for an `1-D` tensor `x` with `axis = None:
     *  
     * `y&#91;idx[i&#93;] = x[i] for i in &#91;0, 1,...,rank(x) - 1&#93;`
     *  
     * For example:
     *  ```
     * x = tf.constant([1, 1, 2, 4, 4, 4, 7, 8, 8])
     *  y, idx, count = UniqueWithCountsV2(x, axis = [0])
     *  y ==> [1, 2, 4, 7, 8]
     *  idx ==> [0, 0, 1, 2, 2, 2, 3, 4, 4]
     *  count ==> [2, 1, 3, 1, 2]
     *  
     * ```
     *  
     * For a `2-D` tensor `x` with `axis = 0`:
     *  ```
     * x = tf.constant([[1, 0, 0],
     *                  [1, 0, 0],
     *                  [2, 0, 0]])
     *  y, idx, count = UniqueWithCountsV2(x, axis=[0])
     *  y ==> [[1, 0, 0],
     *         [2, 0, 0]]
     *  idx ==> [0, 0, 1]
     *  count ==> [2, 1]
     *  
     * ```
     *  
     * For a `2-D` tensor `x` with `axis = 1`:
     *  ```
     * x = tf.constant([[1, 0, 0],
     *                  [1, 0, 0],
     *                  [2, 0, 0]])
     *  y, idx, count = UniqueWithCountsV2(x, axis=[1])
     *  y ==> [[1, 0],
     *         [1, 0],
     *         [2, 0]]
     *  idx ==> [0, 1, 1]
     *  count ==> [1, 2]
     *  
     * ```
     *
     * @param <T> data type for `y` output
     * @param <V> data type for `idx` output
     * @param x A `Tensor`.
     * @param axis A `Tensor` of type `int32` (default: None). The axis of the Tensor to
     *  find the unique elements.
     * @param <T> data type for `UniqueWithCountsV2` output and operands
     * @return a new instance of UniqueWithCounts, with default output types
     * @see org.tensorflow.op.Ops.uniqueWithCounts
     */
    public fun <T : TType> uniqueWithCounts(x: Operand<T>, axis: Operand<out TNumber>):
            UniqueWithCounts<T, TInt32> = java.uniqueWithCounts<T>(    
        x,
        axis
        )

    /**
     * Finds unique elements along an axis of a tensor.
     *  This operation either returns a tensor `y` containing unique elements
     *  along the `axis` of a tensor. The returned unique elements is sorted
     *  in the same order as they occur along `axis` in `x`.
     *  This operation also returns a tensor `idx` and a tensor `count`
     *  that are the same size as the number of the elements in `x` along the
     *  `axis` dimension. The `idx` contains the index in the unique output `y`
     *  and the `count` contains the count in the unique output `y`.
     *  In other words, for an `1-D` tensor `x` with `axis = None:
     *  
     * `y&#91;idx[i&#93;] = x[i] for i in &#91;0, 1,...,rank(x) - 1&#93;`
     *  
     * For example:
     *  ```
     * x = tf.constant([1, 1, 2, 4, 4, 4, 7, 8, 8])
     *  y, idx, count = UniqueWithCountsV2(x, axis = [0])
     *  y ==> [1, 2, 4, 7, 8]
     *  idx ==> [0, 0, 1, 2, 2, 2, 3, 4, 4]
     *  count ==> [2, 1, 3, 1, 2]
     *  
     * ```
     *  
     * For a `2-D` tensor `x` with `axis = 0`:
     *  ```
     * x = tf.constant([[1, 0, 0],
     *                  [1, 0, 0],
     *                  [2, 0, 0]])
     *  y, idx, count = UniqueWithCountsV2(x, axis=[0])
     *  y ==> [[1, 0, 0],
     *         [2, 0, 0]]
     *  idx ==> [0, 0, 1]
     *  count ==> [2, 1]
     *  
     * ```
     *  
     * For a `2-D` tensor `x` with `axis = 1`:
     *  ```
     * x = tf.constant([[1, 0, 0],
     *                  [1, 0, 0],
     *                  [2, 0, 0]])
     *  y, idx, count = UniqueWithCountsV2(x, axis=[1])
     *  y ==> [[1, 0],
     *         [1, 0],
     *         [2, 0]]
     *  idx ==> [0, 1, 1]
     *  count ==> [1, 2]
     *  
     * ```
     *
     * @param <T> data type for `y` output
     * @param <V> data type for `idx` output
     * @param x A `Tensor`.
     * @param axis A `Tensor` of type `int32` (default: None). The axis of the Tensor to
     *  find the unique elements.
     * @param outIdx The value of the outIdx attribute
     * @param <T> data type for `UniqueWithCountsV2` output and operands
     * @param <V> data type for `UniqueWithCountsV2` output and operands
     * @return a new instance of UniqueWithCounts
     * @see org.tensorflow.op.Ops.uniqueWithCounts
     */
    public fun <T : TType, V : TNumber> uniqueWithCounts(
        x: Operand<T>,
        axis: Operand<out TNumber>,
        outIdx: Class<V>
    ): UniqueWithCounts<T, V> = java.uniqueWithCounts<T, V>(    
        x,
        axis,
        outIdx
        )

    /**
     * Converts an array of flat indices into a tuple of coordinate arrays.
     *  Example:
     *  ```
     * y = tf.unravel_index(indices=[2, 5, 7], dims=[3, 3])
     *  # 'dims' represent a hypothetical (3, 3) tensor of indices:
     *  # [[0, 1, *2*],
     *  #  [3, 4, *5*],
     *  #  [6, *7*, 8]]
     *  # For each entry from 'indices', this operation returns
     *  # its coordinates (marked with '*'), such as
     *  # 2 ==> (0, 2)
     *  # 5 ==> (1, 2)
     *  # 7 ==> (2, 1)
     *  y ==> [[0, 1, 2], [2, 2, 1]]
     *  
     * ```
     *  
     * `@`compatibility(numpy)
     *
     *  Equivalent to np.unravel_index
     *  
     * `@`end_compatibility
     *
     * @param <T> data type for `output` output
     * @param indices An 0-D or 1-D `int` Tensor whose elements are indices into the
     *  flattened version of an array of dimensions dims.
     * @param dims An 1-D `int` Tensor. The shape of the array to use for unraveling
     *  indices.
     * @param <T> data type for `UnravelIndex` output and operands
     * @return a new instance of UnravelIndex
     * @see org.tensorflow.op.Ops.unravelIndex
     */
    public fun <T : TNumber> unravelIndex(indices: Operand<T>, dims: Operand<T>): UnravelIndex<T> =
            java.unravelIndex<T>(    
        indices,
        dims
        )

    /**
     * Unpacks a given dimension of a rank-`R` tensor into `num` rank-`(R-1)` tensors.
     *  Unpacks `num` tensors from `value` by chipping it along the `axis` dimension.
     *  For example, given a tensor of shape `(A, B, C, D)`;
     *  
     * If `axis == 0` then the i'th tensor in `output` is the slice `value&#91;i, :, :, :&#93;`
     *  and each tensor in `output` will have shape `(B, C, D)`. (Note that the
     *  dimension unpacked along is gone, unlike `split`).
     *  
     * If `axis == 1` then the i'th tensor in `output` is the slice `value&#91;:, i, :, :&#93;`
     *  and each tensor in `output` will have shape `(A, C, D)`.
     *  Etc.
     *  
     * This is the opposite of `pack`.
     *
     * @param <T> data type for `output` output
     * @param value 1-D or higher, with `axis` dimension size equal to `num`.
     * @param num The value of the num attribute
     * @param options carries optional attribute values
     * @param <T> data type for `Unpack` output and operands
     * @return a new instance of Unstack
     * @see org.tensorflow.op.Ops.unstack
     * @param axis Sets the axis option.
     *
     * @param axis Dimension along which to unpack.  Negative values wrap around, so the
     *  valid range is `[-R, R)`.
     * @return this Options instance.
     */
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

    /**
     * Op is similar to a lightweight Dequeue.
     *  The basic functionality is similar to dequeue with many fewer
     *  capabilities and options.  This Op is optimized for performance.
     *
     * @param dtypes The value of the dtypes attribute
     * @param options carries optional attribute values
     * @return a new instance of Unstage
     * @see org.tensorflow.op.Ops.unstage
     * @param capacity Sets the capacity option.
     *
     * @param capacity the capacity option
     * @return this Options instance.
     * @param memoryLimit Sets the memoryLimit option.
     *
     * @param memoryLimit the memoryLimit option
     * @return this Options instance.
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun unstage(
        dtypes: List<Class<out TType>>,
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

    /**
     * Creates a handle to a Variable resource.
     *
     * @param dtype the type of this variable. Must agree with the dtypes
     *  of all ops using this variable.
     * @param shape The (possibly partially specified) shape of this variable.
     * @param options carries optional attribute values
     * @param <T> data type for `VarHandleOp` output and operands
     * @return a new instance of VarHandleOp
     * @see org.tensorflow.op.Ops.varHandleOp
     * @param container Sets the container option.
     *
     * @param container the container this variable is placed in.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the name by which this variable is referred to.
     * @return this Options instance.
     * @param allowedDevices Sets the allowedDevices option.
     *
     * @param allowedDevices DEPRECATED. The allowed devices containing the resource variable. Set
     * when the
     *  output ResourceHandle represents a per-replica/partitioned resource variable.
     * @return this Options instance.
     */
    public fun <T : TType> varHandleOp(
        dtype: Class<T>,
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

    /**
     * Checks whether a resource handle-based variable has been initialized.
     *
     * @param resource the input resource handle.
     * @return a new instance of VarIsInitializedOp
     * @see org.tensorflow.op.Ops.varIsInitializedOp
     */
    public fun varIsInitializedOp(resource: Operand<out TType>): VarIsInitializedOp =
            java.varIsInitializedOp(    
        resource
        )

    /**
     * Factory method to create a new Variable with its initializer. Both the creation and
     * assignment
     *  are done in the init scope.
     *
     *  
     * Only supported on Graph sessions as the [org.tensorflow.op.core.Assign] op does not
     *  work in an EagerSession.
     *
     * @param init The op to use to initialise this variable.
     * @param options carries optional attributes values
     * @return a new instance of Variable
     * @see org.tensorflow.op.Ops.variable
     * @param container Sets the container option.
     *
     * @param container If non-empty, this variable is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this variable is named in the given bucket
     *  with this shared_name. Otherwise, the node name is used instead.
     * @return this Options instance.
     */
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

    /**
     * Holds state in the form of a tensor that persists across steps.
     *  Outputs a ref to the tensor state so it may be read or modified.
     *  TODO(zhifengc/mrry): Adds a pointer to a more detail document
     *  about sharing states in tensorflow.
     *
     * @param <T> data type for `ref` output
     * @param shape The shape of the variable tensor.
     * @param dtype The type of elements in the variable tensor.
     * @param options carries optional attribute values
     * @param <T> data type for `VariableV2` output and operands
     * @return a new instance of Variable
     * @see org.tensorflow.op.Ops.variable
     * @param container Sets the container option.
     *
     * @param container If non-empty, this variable is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this variable is named in the given bucket
     *  with this shared_name. Otherwise, the node name is used instead.
     * @return this Options instance.
     */
    public fun <T : TType> variable(
        shape: Shape,
        dtype: Class<T>,
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

    /**
     * Returns the shape of the variable pointed to by `resource`.
     *  This operation returns a 1-D integer tensor representing the shape of `input`.
     *  
     * For example:
     *  ```
     * # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
     *  shape(t) ==> [2, 2, 3]
     *  
     * ```
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @return a new instance of VariableShape, with default output types
     * @see org.tensorflow.op.Ops.variableShape
     */
    public fun variableShape(input: Operand<out TType>): VariableShape<TInt32> =
            java.variableShape(    
        input
        )

    /**
     * Returns the shape of the variable pointed to by `resource`.
     *  This operation returns a 1-D integer tensor representing the shape of `input`.
     *  
     * For example:
     *  ```
     * # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
     *  shape(t) ==> [2, 2, 3]
     *  
     * ```
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param outType The value of the outType attribute
     * @param <T> data type for `VariableShape` output and operands
     * @return a new instance of VariableShape
     * @see org.tensorflow.op.Ops.variableShape
     */
    public fun <T : TNumber> variableShape(input: Operand<out TType>, outType: Class<T>):
            VariableShape<T> = java.variableShape<T>(    
        input,
        outType
        )

    /**
     * Returns locations of nonzero / true values in a tensor.
     *  This operation returns the coordinates of true elements in `condition`. The
     *  coordinates are returned in a 2-D tensor where the first dimension (rows)
     *  represents the number of true elements, and the second dimension (columns)
     *  represents the coordinates of the true elements. Keep in mind, the shape of
     *  the output tensor can vary depending on how many true values there are in
     *  `condition`. Indices are output in row-major order.
     *  
     * For example:
     *  ```
     * # 'input' tensor is [[True, False]
     *  #                    [True, False]]
     *  # 'input' has two true values, so output has two coordinates.
     *  # 'input' has rank of 2, so coordinates have two indices.
     *  where(input) ==> [[0, 0],
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
     *  where(input) ==> [[0, 0, 0],
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
     *  where(input) ==> [[0, 0, 0],
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
     *  where(input) ==> [[0, 0, 0],
     *                    [0, 1, 0],
     *                    [1, 0, 1],
     *                    [1, 1, 1],
     *                    [2, 1, 1]]
     *  
     * ```
     *
     * @param condition The condition value
     * @return a new instance of Where
     * @see org.tensorflow.op.Ops.where
     */
    public fun `where`(condition: Operand<out TType>): Where = java.where(    
        condition
        )

    /**
     * output = input; While (Cond(output)) { output = Body(output) }
     *
     *  
     * Selects between [StatefulWhile] and [StatelessWhile] based on the statefulness of the
     * function arguments.
     *
     * @param input A list of input tensors whose types are T.
     * @param cond `
     * A function takes 'input' and returns a tensor.  If the tensor is
     *    a scalar of non-boolean, the scalar is converted to a boolean
     *    according to the following rule: if the scalar is a numerical
     *    value, non-zero means True and zero means False; if the scalar is
     *    a string, non-empty means True and empty means False. If the
     *    tensor is not a scalar, non-emptiness means True and False
     *    otherwise.
     *  
     * `
     * @param body `
     * A function that takes a list of tensors and returns another
     *    list of tensors. Both lists have the same types as specified
     *    by T.
     *  
     * `
     * @param options carries optional attribute values
     * @return a new instance of While
     * @see org.tensorflow.op.Ops.whileOp
     * @param outputShapes Sets the outputShapes option.
     *
     * @param outputShapes the outputShapes option
     * @return this Options instance.
     * @param parallelIterations Sets the parallelIterations option.
     *
     * @param parallelIterations the parallelIterations option
     * @return this Options instance.
     */
    public fun whileOp(
        input: Iterable<Operand<*>>,
        cond: ConcreteFunction,
        body: ConcreteFunction,
        outputShapes: List<Shape>? = null,
        parallelIterations: Long? = null
    ): While = java.whileOp(    
        input,
        cond,
        body,
        *listOfNotNull(
            outputShapes?.let{ org.tensorflow.op.core.While.outputShapes(it) },
            parallelIterations?.let{ org.tensorflow.op.core.While.parallelIterations(it) }
        ).toTypedArray()
        )

    /**
     * Creates a zeroed tensor given its type and shape.
     *
     * @param dims a 1-D operand that represents the shape of the output tensor
     * @param type the output tensor datatype
     * @return a constant tensor initialized with zeros
     * @throws IllegalArgumentException if the tensor type or shape cannot be initialized with
     * zeros.
     * @see org.tensorflow.op.Ops.zeros
     */
    public fun <T : TType> zeros(dims: Operand<out TNumber>, type: Class<T>): Zeros<T> =
            java.zeros<T>(    
        dims,
        type
        )

    /**
     * Returns a tensor of zeros with the same shape and type as x.
     *
     * @param <T> data type for `y` output
     * @param x a tensor of type T.
     * @param <T> data type for `ZerosLike` output and operands
     * @return a new instance of ZerosLike
     * @see org.tensorflow.op.Ops.zerosLike
     */
    public fun <T : TType> zerosLike(x: Operand<T>): ZerosLike<T> = java.zerosLike<T>(    
        x
        )

    /**
     * Bitcasts a tensor from one type to another without copying data.
     *  Given a tensor `input`, this operation returns a tensor that has the same buffer
     *  data as `input` with datatype `type`.
     *  
     * If the input datatype `T` is larger than the output datatype `type` then the
     *  shape changes from &#91;...&#93; to [..., sizeof(`T`)/sizeof(`type`)].
     *  
     * If `T` is smaller than `type`, the operator requires that the rightmost
     *  dimension be equal to sizeof(`type`)/sizeof(`T`). The shape then goes from
     *  [..., sizeof(`type`)/sizeof(`T`)] to &#91;...&#93;.
     *  
     * tf.bitcast() and tf.cast() work differently when real dtype is casted as a complex dtype
     *  (e.g. tf.complex64 or tf.complex128) as tf.cast() make imaginary part 0 while tf.bitcast()
     *  gives module error.
     *  For example,
     *  
     * Example 1:
     *  ```
     *
     * a = [1., 2., 3.]
     *  equality_bitcast = tf.bitcast(a, tf.complex128)
     *  Traceback (most recent call last):
     *  ...
     *  InvalidArgumentError: Cannot bitcast from 1 to 18 [Op:Bitcast]
     *  equality_cast = tf.cast(a, tf.complex128)
     *  print(equality_cast)
     *  tf.Tensor([1.+0.j 2.+0.j 3.+0.j], shape=(3,), dtype=complex128)
     * ```
     *  
     * Example 2:
     *  ```
     *
     * tf.bitcast(tf.constant(0xffffffff, dtype=tf.uint32), tf.uint8)
     *  <tf.Tensor: shape=(4,), dtype=uint8, numpy=array([255, 255, 255, 255], dtype=uint8)>
     * ```
     *  
     * Example 3:
     *  ```
     *
     * x = [1., 2., 3.]
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
     * ```
     *  
     * _NOTE_: Bitcast is implemented as a low-level cast, so machines with different
     *  endian orderings will give different results.
     *
     * @param <U> data type for `output` output
     * @param input The input value
     * @param type The value of the type attribute
     * @param <U> data type for `Bitcast` output and operands
     * @return a new instance of Bitcast
     * @see org.tensorflow.op.Ops.bitcast
     */
    @JvmName("bitcastReified")
    public inline fun <reified U : TType> bitcast(input: Operand<out TType>): Bitcast<U> =
            bitcast<U>(input, U::class.java)

    /**
     * Creates a scalar of `type`, with the value of `number`. `number` may be
     *  truncated if it does not fit in the target type.
     *
     * @param type the type of tensor to create. Must be concrete (i.e. not
     * [org.tensorflow.types.family.TFloating])
     * @param number the value of the tensor
     * @return a constant of the passed type
     * @throws IllegalArgumentException if the type is abstract (i.e.
     * [org.tensorflow.types.family.TFloating]) or unknown.
     * @see org.tensorflow.op.Ops.constant
     */
    @JvmName("constantReified")
    public inline fun <reified T : TNumber> constant(number: Number): Constant<T> =
            constant<T>(T::class.java, number)

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
     * @see org.tensorflow.op.Ops.constant
     */
    @JvmName("constantReified")
    public inline fun <reified T : TType> constantTyped(shape: Shape, `data`: ByteDataBuffer):
            Constant<T> = constant<T>(T::class.java, shape, data)

    /**
     * Creates a tensor with the given shape.
     *  
     * This operation creates a tensor of `shape` and `dtype`.
     *
     * @param <T> data type for `output` output
     * @param shape 1-D. Represents the shape of the output tensor.
     * @param dtype The value of the dtype attribute
     * @param options carries optional attribute values
     * @param <T> data type for `Empty` output and operands
     * @return a new instance of Empty
     * @see org.tensorflow.op.Ops.empty
     * @param init Sets the init option.
     *
     * @param init If True, initialize the returned tensor with the default value of dtype. 
     * Otherwise, the implementation is free not to initializethe tensor's content.
     * @return this Options instance.
     */
    @JvmName("emptyReified")
    public inline fun <reified T : TType> empty(shape: Operand<TInt32>, `init`: Boolean? = null):
            Empty<T> = empty<T>(shape, T::class.java, init)

    /**
     * Creates and returns an empty tensor list.
     *  All list elements must be tensors of dtype element_dtype and shape compatible
     *  with element_shape.
     *  
     * handle: an empty tensor list.
     *  element_dtype: the type of elements in the list.
     *  element_shape: a shape compatible with that of elements in the list.
     *
     * @param elementShape The elementShape value
     * @param maxNumElements The maxNumElements value
     * @param elementDtype The value of the elementDtype attribute
     * @param <U> data type for `EmptyTensorList` output and operands
     * @return a new instance of EmptyTensorList
     * @see org.tensorflow.op.Ops.emptyTensorList
     */
    @JvmName("emptyTensorListReified")
    public inline fun <reified U : TType> emptyTensorList(elementShape: Operand<out TNumber>,
            maxNumElements: Operand<TInt32>): EmptyTensorList = emptyTensorList<U>(elementShape,
            maxNumElements, U::class.java)

    /**
     * Get the value of the tensor specified by its handle.
     *
     * @param <T> data type for `value` output
     * @param handle The handle for a tensor stored in the session state.
     * @param dtype The type of the output value.
     * @param <T> data type for `GetSessionTensor` output and operands
     * @return a new instance of GetSessionTensor
     * @see org.tensorflow.op.Ops.getSessionTensor
     */
    @JvmName("getSessionTensorReified")
    public inline fun <reified T : TType> getSessionTensor(handle: Operand<TString>):
            GetSessionTensor<T> = getSessionTensor<T>(handle, T::class.java)

    /**
     * Creates a non-initialized hash table.
     *  This op creates a hash table, specifying the type of its keys and values.
     *  Before using the table you will have to initialize it.  After initialization the
     *  table will be immutable.
     *
     * @param keyDtype Type of the table keys.
     * @param valueDtype Type of the table values.
     * @param options carries optional attribute values
     * @param <T> data type for `HashTableV2` output and operands
     * @param <U> data type for `HashTableV2` output and operands
     * @return a new instance of HashTable
     * @see org.tensorflow.op.Ops.hashTable
     *
     * @param container Sets the container option.
     *
     * @param container If non-empty, this table is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this table is shared under the given name across
     *  multiple sessions.
     * @return this Options instance.
     * @param useNodeNameSharing Sets the useNodeNameSharing option.
     *
     * @param useNodeNameSharing If true and shared_name is empty, the table is shared
     *  using the node name.
     * @return this Options instance.
     */
    @JvmName("hashTableReified")
    public inline fun <reified T : TType, reified U : TType> hashTable(
        container: String? = null,
        sharedName: String? = null,
        useNodeNameSharing: Boolean? = null
    ): HashTable = hashTable<T, U>(T::class.java, U::class.java, container, sharedName,
            useNodeNameSharing)

    /**
     * Return histogram of values.
     *  Given the tensor `values`, this operation returns a rank 1 histogram counting
     *  the number of entries in `values` that fall into every bin.  The bins are
     *  equal width and determined by the arguments `value_range` and `nbins`.
     *  ```
     * # Bins will be:  (-inf, 1), [1, 2), [2, 3), [3, 4), [4, inf)
     *  nbins = 5
     *  value_range = [0.0, 5.0]
     *  new_values = [-1.0, 0.0, 1.5, 2.0, 5.0, 15]
     *
     *  with tf.get_default_session() as sess:
     *    hist = tf.histogram_fixed_width(new_values, value_range, nbins=5)
     *    variables.global_variables_initializer().run()
     *    sess.run(hist) => [2, 1, 1, 0, 2]
     *  
     * ```
     *
     * @param <U> data type for `out` output
     * @param values Numeric `Tensor`.
     * @param valueRange Shape [2] `Tensor` of same `dtype` as `values`.
     *  values <= value_range[0] will be mapped to hist[0],
     *  values >= value_range[1] will be mapped to hist&#91;-1&#93;.
     * @param nbins Scalar `int32 Tensor`.  Number of histogram bins.
     * @param dtype The value of the dtype attribute
     * @param <U> data type for `HistogramFixedWidth` output and operands
     * @param <T> data type for `HistogramFixedWidth` output and operands
     * @return a new instance of HistogramFixedWidth
     * @see org.tensorflow.op.Ops.histogramFixedWidth
     */
    @JvmName("histogramFixedWidthReified")
    public inline fun <reified U : TNumber, T : TNumber> histogramFixedWidthTyped(
        values: Operand<T>,
        valueRange: Operand<T>,
        nbins: Operand<TInt32>
    ): HistogramFixedWidth<U> = histogramFixedWidth<U, T>(values, valueRange, nbins, U::class.java)

    /**
     * Returns immutable tensor from memory region.
     *  The current implementation memmaps the tensor from a file.
     *
     * @param <T> data type for `tensor` output
     * @param dtype Type of the returned tensor.
     * @param shape Shape of the returned tensor.
     * @param memoryRegionName Name of readonly memory region used by the tensor, see
     *  NewReadOnlyMemoryRegionFromFile in tensorflow::Env.
     * @param <T> data type for `ImmutableConst` output and operands
     * @return a new instance of ImmutableConst
     * @see org.tensorflow.op.Ops.immutableConst
     */
    @JvmName("immutableConstReified")
    public inline fun <reified T : TType> immutableConst(shape: Shape, memoryRegionName: String):
            ImmutableConst<T> = immutableConst<T>(T::class.java, shape, memoryRegionName)

    /**
     * Outputs all keys and values in the table.
     *
     * @param <T> data type for `keys` output
     * @param <U> data type for `values` output
     * @param tableHandle Handle to the table.
     * @param Tkeys The value of the Tkeys attribute
     * @param Tvalues The value of the Tvalues attribute
     * @param <T> data type for `LookupTableExportV2` output and operands
     * @param <U> data type for `LookupTableExportV2` output and operands
     * @return a new instance of LookupTableExport
     * @see org.tensorflow.op.Ops.lookupTableExport
     */
    @JvmName("lookupTableExportReified")
    public inline fun <reified T : TType, reified U : TType>
            lookupTableExport(tableHandle: Operand<out TType>): LookupTableExport<T, U> =
            lookupTableExport<T, U>(tableHandle, T::class.java, U::class.java)

    /**
     * Creates an empty hash table that uses tensors as the backing store.
     *  It uses &quot;open addressing&quot; with quadratic reprobing to resolve
     *  collisions.
     *  
     * This op creates a mutable hash table, specifying the type of its keys and
     *  values. Each value must be a scalar. Data can be inserted into the table using
     *  the insert operations. It does not support the initialization operation.
     *
     * @param emptyKey The key used to represent empty key buckets internally. Must not
     *  be used in insert or lookup operations.
     * @param deletedKey The deletedKey value
     * @param valueDtype Type of the table values.
     * @param options carries optional attribute values
     * @param <T> data type for `MutableDenseHashTableV2` output and operands
     * @param <U> data type for `MutableDenseHashTableV2` output and operands
     * @return a new instance of MutableDenseHashTable
     * @see org.tensorflow.op.Ops.mutableDenseHashTable
     * @param container Sets the container option.
     *
     * @param container If non-empty, this table is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this table is shared under the given name across
     *  multiple sessions.
     * @return this Options instance.
     * @param useNodeNameSharing Sets the useNodeNameSharing option.
     *
     * @param useNodeNameSharing the useNodeNameSharing option
     * @return this Options instance.
     * @param valueShape Sets the valueShape option.
     *
     * @param valueShape The shape of each value.
     * @return this Options instance.
     * @param initialNumBuckets Sets the initialNumBuckets option.
     *
     * @param initialNumBuckets The initial number of hash table buckets. Must be a power
     *  to 2.
     * @return this Options instance.
     * @param maxLoadFactor Sets the maxLoadFactor option.
     *
     * @param maxLoadFactor The maximum ratio between number of entries and number of
     *  buckets before growing the table. Must be between 0 and 1.
     * @return this Options instance.
     */
    @JvmName("mutableDenseHashTableReified")
    public inline fun <T : TType, reified U : TType> mutableDenseHashTable(
        emptyKey: Operand<T>,
        deletedKey: Operand<T>,
        container: String? = null,
        sharedName: String? = null,
        useNodeNameSharing: Boolean? = null,
        valueShape: Shape? = null,
        initialNumBuckets: Long? = null,
        maxLoadFactor: Float? = null
    ): MutableDenseHashTable = mutableDenseHashTable<T, U>(emptyKey, deletedKey, U::class.java,
            container, sharedName, useNodeNameSharing, valueShape, initialNumBuckets, maxLoadFactor)

    /**
     * Creates an empty hash table.
     *  This op creates a mutable hash table, specifying the type of its keys and
     *  values. Each value must be a scalar. Data can be inserted into the table using
     *  the insert operations. It does not support the initialization operation.
     *
     * @param keyDtype Type of the table keys.
     * @param valueDtype Type of the table values.
     * @param options carries optional attribute values
     * @param <T> data type for `MutableHashTableV2` output and operands
     * @param <U> data type for `MutableHashTableV2` output and operands
     * @return a new instance of MutableHashTable
     * @see org.tensorflow.op.Ops.mutableHashTable
     *
     * @param container Sets the container option.
     *
     * @param container If non-empty, this table is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this table is shared under the given name across
     *  multiple sessions.
     * @return this Options instance.
     * @param useNodeNameSharing Sets the useNodeNameSharing option.
     *
     * @param useNodeNameSharing If true and shared_name is empty, the table is shared
     *  using the node name.
     * @return this Options instance.
     */
    @JvmName("mutableHashTableReified")
    public inline fun <reified T : TType, reified U : TType> mutableHashTable(
        container: String? = null,
        sharedName: String? = null,
        useNodeNameSharing: Boolean? = null
    ): MutableHashTable = mutableHashTable<T, U>(T::class.java, U::class.java, container,
            sharedName, useNodeNameSharing)

    /**
     * Creates an empty hash table.
     *  This op creates a mutable hash table, specifying the type of its keys and
     *  values. Each value must be a vector. Data can be inserted into the table using
     *  the insert operations. It does not support the initialization operation.
     *
     * @param keyDtype Type of the table keys.
     * @param valueDtype Type of the table values.
     * @param options carries optional attribute values
     * @param <T> data type for `MutableHashTableOfTensorsV2` output and operands
     * @param <U> data type for `MutableHashTableOfTensorsV2` output and operands
     * @return a new instance of MutableHashTableOfTensors
     * @see org.tensorflow.op.Ops.mutableHashTableOfTensors
     *
     * @param container Sets the container option.
     *
     * @param container If non-empty, this table is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this table is shared under the given name across
     *  multiple sessions.
     * @return this Options instance.
     * @param useNodeNameSharing Sets the useNodeNameSharing option.
     *
     * @param useNodeNameSharing the useNodeNameSharing option
     * @return this Options instance.
     * @param valueShape Sets the valueShape option.
     *
     * @param valueShape the valueShape option
     * @return this Options instance.
     */
    @JvmName("mutableHashTableOfTensorsReified")
    public inline fun <reified T : TType, reified U : TType> mutableHashTableOfTensors(
        container: String? = null,
        sharedName: String? = null,
        useNodeNameSharing: Boolean? = null,
        valueShape: Shape? = null
    ): MutableHashTableOfTensors = mutableHashTableOfTensors<T, U>(T::class.java, U::class.java,
            container, sharedName, useNodeNameSharing, valueShape)

    /**
     * Creates a one valued tensor given its type and shape.
     *
     * @param dims a 1-D operand that represents the shape of the output tensor
     * @param type the output tensor type class. Can not be TString.
     * @return a constant tensor initialized with ones
     * @throws IllegalArgumentException if the tensor type or shape cannot be initialized with ones.
     * @see org.tensorflow.op.Ops.ones
     */
    @JvmName("onesReified")
    public inline fun <reified T : TType> ones(dims: Operand<out TNumber>): Ones<T> = ones<T>(dims,
            T::class.java)

    /**
     * A placeholder op for a value that will be fed into the computation.
     *  N.B. This operation will fail with an error if it is executed. It is
     *  intended as a way to represent a value that will always be fed, and to
     *  provide attrs that enable the fed value to be checked at runtime.
     *
     * @param <T> data type for `output` output
     * @param dtype The type of elements in the tensor.
     * @param options carries optional attribute values
     * @param <T> data type for `Placeholder` output and operands
     * @return a new instance of Placeholder
     * @see org.tensorflow.op.Ops.placeholder
     *
     * @param shape Sets the shape option.
     *
     * @param shape (Optional) The shape of the tensor. If the shape has 0 dimensions, the
     *  shape is unconstrained.
     * @return this Options instance.
     */
    @JvmName("placeholderReified")
    public inline fun <reified T : TType> placeholder(shape: Shape? = null): Placeholder<T> =
            placeholder<T>(T::class.java, shape)

    /**
     * Reads the value of a variable.
     *  The tensor returned by this operation is immutable.
     *  
     * The value returned by this operation is guaranteed to be influenced by all the
     *  writes on which this operation depends directly or indirectly, and to not be
     *  influenced by any of the writes which depend directly or indirectly on this
     *  operation.
     *
     * @param <T> data type for `value` output
     * @param resource handle to the resource in which to store the variable.
     * @param dtype the dtype of the value.
     * @param <T> data type for `ReadVariableOp` output and operands
     * @return a new instance of ReadVariableOp
     * @see org.tensorflow.op.Ops.readVariableOp
     */
    @JvmName("readVariableOpReified")
    public inline fun <reified T : TType> readVariableOp(resource: Operand<out TType>):
            ReadVariableOp<T> = readVariableOp<T>(resource, T::class.java)

    /**
     * Increments variable pointed to by 'resource' until it reaches 'limit'.
     *
     * @param <T> data type for `output` output
     * @param resource Should be from a scalar `Variable` node.
     * @param limit If incrementing ref would bring it above limit, instead generates an
     *  'OutOfRange' error.
     * @param T The value of the T attribute
     * @param <T> data type for `ResourceCountUpTo` output and operands
     * @return a new instance of ResourceCountUpTo
     * @see org.tensorflow.op.Ops.resourceCountUpTo
     */
    @JvmName("resourceCountUpToReified")
    public inline fun <reified T : TNumber> resourceCountUpTo(resource: Operand<out TType>,
            limit: Long): ResourceCountUpTo<T> = resourceCountUpTo<T>(resource, limit,
            T::class.java)

    /**
     * Gather slices from the variable pointed to by `resource` according to `indices`.
     *  `indices` must be an integer tensor of any dimension (usually 0-D or 1-D).
     *  Produces an output tensor with shape `indices.shape + params.shape&#91;1:&#93;` where:
     *  ```
     * # Scalar indices
     *      output[:, ..., :] = params[indices, :, ... :]
     *
     *      # Vector indices
     *      output[i, :, ..., :] = params[indices[i], :, ... :]
     *
     *      # Higher rank indices
     *      output[i, ..., j, :, ... :] = params[indices[i, ..., j], :, ..., :]
     *  
     * ```
     *
     * @param <U> data type for `output` output
     * @param resource The resource value
     * @param indices The indices value
     * @param dtype The value of the dtype attribute
     * @param options carries optional attribute values
     * @param <U> data type for `ResourceGather` output and operands
     * @return a new instance of ResourceGather
     * @see org.tensorflow.op.Ops.resourceGather
     * @param batchDims Sets the batchDims option.
     *
     * @param batchDims the batchDims option
     * @return this Options instance.
     * @param validateIndices Sets the validateIndices option.
     *
     * @param validateIndices the validateIndices option
     * @return this Options instance.
     */
    @JvmName("resourceGatherReified")
    public inline fun <reified U : TType> resourceGather(
        resource: Operand<out TType>,
        indices: Operand<out TNumber>,
        batchDims: Long? = null,
        validateIndices: Boolean? = null
    ): ResourceGather<U> = resourceGather<U>(resource, indices, U::class.java, batchDims,
            validateIndices)

    /**
     * The ResourceGatherNd operation
     *
     * @param <U> data type for `output` output
     * @param resource The resource value
     * @param indices The indices value
     * @param dtype The value of the dtype attribute
     * @param <U> data type for `ResourceGatherNd` output and operands
     * @return a new instance of ResourceGatherNd
     * @see org.tensorflow.op.Ops.resourceGatherNd
     */
    @JvmName("resourceGatherNdReified")
    public inline fun <reified U : TType> resourceGatherNd(resource: Operand<out TType>,
            indices: Operand<out TNumber>): ResourceGatherNd<U> = resourceGatherNd<U>(resource,
            indices, U::class.java)

    /**
     * Computes the difference between two lists of numbers or strings.
     *  Given a list `x` and a list `y`, this operation returns a list `out` that
     *  represents all values that are in `x` but not in `y`. The returned list `out`
     *  is sorted in the same order that the numbers appear in `x` (duplicates are
     *  preserved). This operation also returns a list `idx` that represents the
     *  position of each `out` element in `x`. In other words:
     *  
     * `out[i] = x&#91;idx[i&#93;] for i in &#91;0, 1, ..., len(out) - 1&#93;`
     *  
     * For example, given this input:
     *  ```
     * x = [1, 2, 3, 4, 5, 6]
     *  y = [1, 3, 5]
     *  
     * ```
     *  
     * This operation would return:
     *  ```
     * out ==> [2, 4, 6]
     *  idx ==> [1, 3, 5]
     *  
     * ```
     *
     * @param <T> data type for `out` output
     * @param <U> data type for `idx` output
     * @param x 1-D. Values to keep.
     * @param y 1-D. Values to remove.
     * @param outIdx The value of the outIdx attribute
     * @param <T> data type for `ListDiff` output and operands
     * @param <U> data type for `ListDiff` output and operands
     * @return a new instance of SetDiff1d
     * @see org.tensorflow.op.Ops.setDiff1d
     */
    @JvmName("setDiff1dReified")
    public inline fun <T : TType, reified U : TNumber> setDiff1dTyped(x: Operand<T>, y: Operand<T>):
            SetDiff1d<T, U> = setDiff1d<T, U>(x, y, U::class.java)

    /**
     * Returns the shape of a tensor.
     *  This operation returns a 1-D integer tensor representing the shape of `input`.
     *  
     * For example:
     *  ```
     * # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
     *  shape(t) ==> [2, 2, 3]
     *  
     * ```
     *
     * @param <U> data type for `output` output
     * @param input The input value
     * @param outType The value of the outType attribute
     * @param <U> data type for `Shape` output and operands
     * @return a new instance of Shape
     * @see org.tensorflow.op.Ops.shape
     */
    @JvmName("shapeReified")
    public inline fun <reified U : TNumber> shapeTyped(input: Operand<out TType>):
            org.tensorflow.op.core.Shape<U> = shape<U>(input, U::class.java)

    /**
     * Returns shape of tensors.
     *  This operation returns N 1-D integer tensors representing shape of `input[i]s`.
     *
     * @param <U> data type for `output` output
     * @param input The input value
     * @param outType The value of the outType attribute
     * @param <U> data type for `ShapeN` output and operands
     * @return a new instance of ShapeN
     * @see org.tensorflow.op.Ops.shapeN
     */
    @JvmName("shapeNReified")
    public inline fun <reified U : TNumber> shapeNTyped(input: Iterable<Operand<out TType>>):
            ShapeN<U> = shapeN<U>(input, U::class.java)

    /**
     * Returns the size of a tensor.
     *  This operation returns an integer representing the number of elements in
     *  `input`.
     *  
     * For example:
     *  ```
     * # 't' is [[[1, 1,, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]]
     *  size(t) ==> 12
     *  
     * ```
     *
     * @param <U> data type for `output` output
     * @param input The input value
     * @param outType The value of the outType attribute
     * @param <U> data type for `Size` output and operands
     * @return a new instance of Size
     * @see org.tensorflow.op.Ops.size
     */
    @JvmName("sizeReified")
    public inline fun <reified U : TNumber> sizeTyped(input: Operand<out TType>): Size<U> =
            size<U>(input, U::class.java)

    /**
     * Returns a tensor that may be mutated, but only persists within a single step.
     *  This is an experimental op for internal use only and it is possible to use this
     *  op in unsafe ways.  DO NOT USE unless you fully understand the risks.
     *  
     * It is the caller's responsibility to ensure that 'ref' is eventually passed to a
     *  matching 'DestroyTemporaryVariable' op after all other uses have completed.
     *  
     * Outputs a ref to the tensor state so it may be read or modified.
     *  
     * E.g.
     *  var = state_ops._temporary_variable(&#91;1, 2&#93;, types.float_)
     *  var_name = var.op.name
     *  var = state_ops.assign(var, &#91;[4.0, 5.0&#93;])
     *  var = state_ops.assign_add(var, &#91;[6.0, 7.0&#93;])
     *  final = state_ops._destroy_temporary_variable(var, var_name=var_name)
     *
     * @param <T> data type for `ref` output
     * @param shape The shape of the variable tensor.
     * @param dtype The type of elements in the variable tensor.
     * @param options carries optional attribute values
     * @param <T> data type for `TemporaryVariable` output and operands
     * @return a new instance of TemporaryVariable
     * @see org.tensorflow.op.Ops.temporaryVariable
     * @param varName Sets the varName option.
     *
     * @param varName Overrides the name used for the temporary variable resource. Default
     *  value is the name of the 'TemporaryVariable' op (which is guaranteed unique).
     * @return this Options instance.
     */
    @JvmName("temporaryVariableReified")
    public inline fun <reified T : TType> temporaryVariable(shape: Shape, varName: String? = null):
            TemporaryVariable<T> = temporaryVariable<T>(shape, T::class.java, varName)

    /**
     * An array of Tensors of given size.
     *  Write data via Write and read via Read or Pack.
     *
     * @param sizeOutput The size of the array.
     * @param dtype The type of the elements on the tensor_array.
     * @param options carries optional attribute values
     * @param <T> data type for `TensorArrayV3` output and operands
     * @return a new instance of TensorArray
     * @see org.tensorflow.op.Ops.tensorArray
     * @param elementShape Sets the elementShape option.
     *
     * @param elementShape The expected shape of an element, if known. Used to
     *  validate the shapes of TensorArray elements. If this shape is not
     *  fully specified, gathering zero-size TensorArrays is an error.
     * @return this Options instance.
     * @param dynamicSize Sets the dynamicSize option.
     *
     * @param dynamicSize A boolean that determines whether writes to the TensorArray
     *  are allowed to grow the size.  By default, this is not allowed.
     * @return this Options instance.
     * @param clearAfterRead Sets the clearAfterRead option.
     *
     * @param clearAfterRead If true (default), Tensors in the TensorArray are cleared
     *  after being read.  This disables multiple read semantics but allows early
     *  release of memory.
     * @return this Options instance.
     * @param identicalElementShapes Sets the identicalElementShapes option.
     *
     * @param identicalElementShapes If true (default is false), then all
     *  elements in the TensorArray will be expected to have identical shapes.
     *  This allows certain behaviors, like dynamically checking for
     *  consistent shapes on write, and being able to fill in properly
     *  shaped zero tensors on stack -- even if the element_shape attribute
     *  is not fully defined.
     * @return this Options instance.
     * @param tensorArrayName Sets the tensorArrayName option.
     *
     * @param tensorArrayName Overrides the name used for the temporary tensor_array
     *  resource. Default value is the name of the 'TensorArray' op (which
     *  is guaranteed unique).
     * @return this Options instance.
     */
    @JvmName("tensorArrayReified")
    public inline fun <reified T : TType> tensorArray(
        sizeOutput: Operand<TInt32>,
        elementShape: Shape? = null,
        dynamicSize: Boolean? = null,
        clearAfterRead: Boolean? = null,
        identicalElementShapes: Boolean? = null,
        tensorArrayName: String? = null
    ): TensorArray = tensorArray<T>(sizeOutput, T::class.java, elementShape, dynamicSize,
            clearAfterRead, identicalElementShapes, tensorArrayName)

    /**
     * Concat the elements from the TensorArray into value `value`.
     *  Takes `T` elements of shapes
     *  ```
     * (n0 x d0 x d1 x ...), (n1 x d0 x d1 x ...), ..., (n(T-1) x d0 x d1 x ...)
     *  
     * ```
     *  
     * and concatenates them into a Tensor of shape:
     *  
     * `(n0 + n1 + ... + n(T-1) x d0 x d1 x ...)`
     *  
     * All elements must have the same shape (excepting the first dimension).
     *
     * @param <T> data type for `value` output
     * @param handle The handle to a TensorArray.
     * @param flowIn A float scalar that enforces proper chaining of operations.
     * @param dtype The type of the elem that is returned.
     * @param options carries optional attribute values
     * @param <T> data type for `TensorArrayConcatV3` output and operands
     * @return a new instance of TensorArrayConcat
     * @see org.tensorflow.op.Ops.tensorArrayConcat
     * @param elementShapeExcept0 Sets the elementShapeExcept0 option.
     *
     * @param elementShapeExcept0 The expected shape of an element, if known,
     *  excluding the first dimension. Used to validate the shapes of
     *  TensorArray elements. If this shape is not fully specified, concatenating
     *  zero-size TensorArrays is an error.
     * @return this Options instance.
     */
    @JvmName("tensorArrayConcatReified")
    public inline fun <reified T : TType> tensorArrayConcat(
        handle: Operand<out TType>,
        flowIn: Operand<TFloat32>,
        elementShapeExcept0: Shape? = null
    ): TensorArrayConcat<T> = tensorArrayConcat<T>(handle, flowIn, T::class.java,
            elementShapeExcept0)

    /**
     * Gather specific elements from the TensorArray into output `value`.
     *  All elements selected by `indices` must have the same shape.
     *
     * @param <T> data type for `value` output
     * @param handle The handle to a TensorArray.
     * @param indices The locations in the TensorArray from which to read tensor elements.
     * @param flowIn A float scalar that enforces proper chaining of operations.
     * @param dtype The type of the elem that is returned.
     * @param options carries optional attribute values
     * @param <T> data type for `TensorArrayGatherV3` output and operands
     * @return a new instance of TensorArrayGather
     * @see org.tensorflow.op.Ops.tensorArrayGather
     * @param elementShape Sets the elementShape option.
     *
     * @param elementShape The expected shape of an element, if known. Used to
     *  validate the shapes of TensorArray elements. If this shape is not
     *  fully specified, gathering zero-size TensorArrays is an error.
     * @return this Options instance.
     */
    @JvmName("tensorArrayGatherReified")
    public inline fun <reified T : TType> tensorArrayGather(
        handle: Operand<out TType>,
        indices: Operand<TInt32>,
        flowIn: Operand<TFloat32>,
        elementShape: Shape? = null
    ): TensorArrayGather<T> = tensorArrayGather<T>(handle, indices, flowIn, T::class.java,
            elementShape)

    /**
     * The TensorArrayPack operation
     *
     * @param <T> data type for `value` output
     * @param handle The handle value
     * @param flowIn The flowIn value
     * @param dtype The value of the dtype attribute
     * @param options carries optional attribute values
     * @param <T> data type for `TensorArrayPack` output and operands
     * @return a new instance of TensorArrayPack
     * @see org.tensorflow.op.Ops.tensorArrayPack
     * @param elementShape Sets the elementShape option.
     *
     * @param elementShape the elementShape option
     * @return this Options instance.
     */
    @JvmName("tensorArrayPackReified")
    public inline fun <reified T : TType> tensorArrayPack(
        handle: Operand<TString>,
        flowIn: Operand<TFloat32>,
        elementShape: Shape? = null
    ): TensorArrayPack<T> = tensorArrayPack<T>(handle, flowIn, T::class.java, elementShape)

    /**
     * Read an element from the TensorArray into output `value`.
     *
     * @param <T> data type for `value` output
     * @param handle The handle to a TensorArray.
     * @param index The index value
     * @param flowIn A float scalar that enforces proper chaining of operations.
     * @param dtype The type of the elem that is returned.
     * @param <T> data type for `TensorArrayReadV3` output and operands
     * @return a new instance of TensorArrayRead
     * @see org.tensorflow.op.Ops.tensorArrayRead
     */
    @JvmName("tensorArrayReadReified")
    public inline fun <reified T : TType> tensorArrayRead(
        handle: Operand<out TType>,
        index: Operand<TInt32>,
        flowIn: Operand<TFloat32>
    ): TensorArrayRead<T> = tensorArrayRead<T>(handle, index, flowIn, T::class.java)

    /**
     * Concats all tensors in the list along the 0th dimension.
     *  Requires that all tensors have the same shape except the first dimension.
     *  
     * input_handle: The input list.
     *  element_shape: The shape of the uninitialized elements in the list. If the first
     *  dimension is not -1, it is assumed that all list elements have the same
     *  leading dim.
     *  leading_dims: The list of leading dims of uninitialized list elements. Used if
     *  the leading dim of input_handle.element_shape or the element_shape input arg
     *  is not already set.
     *  tensor: The concated result.
     *  lengths: Output tensor containing sizes of the 0th dimension of tensors in the list, used
     * for computing the gradient.
     *
     * @param <U> data type for `tensor` output
     * @param inputHandle The inputHandle value
     * @param elementShape The elementShape value
     * @param leadingDims The leadingDims value
     * @param elementDtype The value of the elementDtype attribute
     * @param <U> data type for `TensorListConcatV2` output and operands
     * @return a new instance of TensorListConcat
     * @see org.tensorflow.op.Ops.tensorListConcat
     */
    @JvmName("tensorListConcatReified")
    public inline fun <reified U : TType> tensorListConcat(
        inputHandle: Operand<out TType>,
        elementShape: Operand<out TNumber>,
        leadingDims: Operand<TInt64>
    ): TensorListConcat<U> = tensorListConcat<U>(inputHandle, elementShape, leadingDims,
            U::class.java)

    /**
     * The TensorListConcatLists operation
     *
     * @param inputA The inputA value
     * @param inputB The inputB value
     * @param elementDtype The value of the elementDtype attribute
     * @param <T> data type for `TensorListConcatLists` output and operands
     * @return a new instance of TensorListConcatLists
     * @see org.tensorflow.op.Ops.tensorListConcatLists
     */
    @JvmName("tensorListConcatListsReified")
    public inline fun <reified T : TType> tensorListConcatLists(inputA: Operand<out TType>,
            inputB: Operand<out TType>): TensorListConcatLists = tensorListConcatLists<T>(inputA,
            inputB, T::class.java)

    /**
     * The shape of the elements of the given list, as a tensor.
     *  input_handle: the list
     *  element_shape: the shape of elements of the list
     *
     * @param <T> data type for `element_shape` output
     * @param inputHandle The inputHandle value
     * @param shapeType The value of the shapeType attribute
     * @param <T> data type for `TensorListElementShape` output and operands
     * @return a new instance of TensorListElementShape
     * @see org.tensorflow.op.Ops.tensorListElementShape
     */
    @JvmName("tensorListElementShapeReified")
    public inline fun <reified T : TNumber> tensorListElementShape(inputHandle: Operand<out TType>):
            TensorListElementShape<T> = tensorListElementShape<T>(inputHandle, T::class.java)

    /**
     * Creates a Tensor by indexing into the TensorList.
     *  Each row in the produced Tensor corresponds to the element in the TensorList
     *  specified by the given index (see `tf.gather`).
     *  
     * input_handle: The input tensor list.
     *  indices: The indices used to index into the list.
     *  values: The tensor.
     *
     * @param <T> data type for `values` output
     * @param inputHandle The inputHandle value
     * @param indices The indices value
     * @param elementShape The elementShape value
     * @param elementDtype The value of the elementDtype attribute
     * @param <T> data type for `TensorListGather` output and operands
     * @return a new instance of TensorListGather
     * @see org.tensorflow.op.Ops.tensorListGather
     */
    @JvmName("tensorListGatherReified")
    public inline fun <reified T : TType> tensorListGather(
        inputHandle: Operand<out TType>,
        indices: Operand<TInt32>,
        elementShape: Operand<TInt32>
    ): TensorListGather<T> = tensorListGather<T>(inputHandle, indices, elementShape, T::class.java)

    /**
     * The TensorListGetItem operation
     *
     * @param <T> data type for `item` output
     * @param inputHandle The inputHandle value
     * @param index The index value
     * @param elementShape The elementShape value
     * @param elementDtype The value of the elementDtype attribute
     * @param <T> data type for `TensorListGetItem` output and operands
     * @return a new instance of TensorListGetItem
     * @see org.tensorflow.op.Ops.tensorListGetItem
     */
    @JvmName("tensorListGetItemReified")
    public inline fun <reified T : TType> tensorListGetItem(
        inputHandle: Operand<out TType>,
        index: Operand<TInt32>,
        elementShape: Operand<TInt32>
    ): TensorListGetItem<T> = tensorListGetItem<T>(inputHandle, index, elementShape, T::class.java)

    /**
     * Returns the last element of the input list as well as a list with all but that element.
     *  Fails if the list is empty.
     *  
     * input_handle: the input list
     *  tensor: the withdrawn last element of the list
     *  element_dtype: the type of elements in the list
     *  element_shape: the shape of the output tensor
     *
     * @param <T> data type for `tensor` output
     * @param inputHandle The inputHandle value
     * @param elementShape The elementShape value
     * @param elementDtype The value of the elementDtype attribute
     * @param <T> data type for `TensorListPopBack` output and operands
     * @return a new instance of TensorListPopBack
     * @see org.tensorflow.op.Ops.tensorListPopBack
     */
    @JvmName("tensorListPopBackReified")
    public inline fun <reified T : TType> tensorListPopBack(inputHandle: Operand<out TType>,
            elementShape: Operand<TInt32>): TensorListPopBack<T> = tensorListPopBack<T>(inputHandle,
            elementShape, T::class.java)

    /**
     * List of the given size with empty elements.
     *  element_shape: the shape of the future elements of the list
     *  num_elements: the number of elements to reserve
     *  handle: the output list
     *  element_dtype: the desired type of elements in the list.
     *
     * @param elementShape The elementShape value
     * @param numElements The numElements value
     * @param elementDtype The value of the elementDtype attribute
     * @param <U> data type for `TensorListReserve` output and operands
     * @return a new instance of TensorListReserve
     * @see org.tensorflow.op.Ops.tensorListReserve
     */
    @JvmName("tensorListReserveReified")
    public inline fun <reified U : TType> tensorListReserve(elementShape: Operand<out TNumber>,
            numElements: Operand<TInt32>): TensorListReserve = tensorListReserve<U>(elementShape,
            numElements, U::class.java)

    /**
     * Stacks all tensors in the list.
     *  Requires that all tensors have the same shape.
     *  
     * input_handle: the input list
     *  tensor: the gathered result
     *  num_elements: optional. If not -1, the number of elements in the list.
     *
     * @param <T> data type for `tensor` output
     * @param inputHandle The inputHandle value
     * @param elementShape The elementShape value
     * @param elementDtype The value of the elementDtype attribute
     * @param options carries optional attribute values
     * @param <T> data type for `TensorListStack` output and operands
     * @return a new instance of TensorListStack
     * @see org.tensorflow.op.Ops.tensorListStack
     * @param numElements Sets the numElements option.
     *
     * @param numElements the numElements option
     * @return this Options instance.
     */
    @JvmName("tensorListStackReified")
    public inline fun <reified T : TType> tensorListStack(
        inputHandle: Operand<out TType>,
        elementShape: Operand<TInt32>,
        numElements: Long? = null
    ): TensorListStack<T> = tensorListStack<T>(inputHandle, elementShape, T::class.java,
            numElements)

    /**
     * Returns a tensor map with item from given key erased.
     *  input_handle: the original map
     *  output_handle: the map with value from given key removed
     *  key: the key of the value to be erased
     *
     * @param inputHandle The inputHandle value
     * @param key The key value
     * @param valueDtype The value of the valueDtype attribute
     * @param <U> data type for `TensorMapErase` output and operands
     * @return a new instance of TensorMapErase
     * @see org.tensorflow.op.Ops.tensorMapErase
     */
    @JvmName("tensorMapEraseReified")
    public inline fun <reified U : TType> tensorMapErase(inputHandle: Operand<out TType>,
            key: Operand<out TType>): TensorMapErase = tensorMapErase<U>(inputHandle, key,
            U::class.java)

    /**
     * Returns the value from a given key in a tensor map.
     *  input_handle: the input map
     *  key: the key to be looked up
     *  value: the value found from the given key
     *
     * @param <U> data type for `value` output
     * @param inputHandle The inputHandle value
     * @param key The key value
     * @param valueDtype The value of the valueDtype attribute
     * @param <U> data type for `TensorMapLookup` output and operands
     * @return a new instance of TensorMapLookup
     * @see org.tensorflow.op.Ops.tensorMapLookup
     */
    @JvmName("tensorMapLookupReified")
    public inline fun <reified U : TType> tensorMapLookup(inputHandle: Operand<out TType>,
            key: Operand<out TType>): TensorMapLookup<U> = tensorMapLookup<U>(inputHandle, key,
            U::class.java)

    /**
     * Returns a Tensor stack of all keys in a tensor map.
     *  input_handle: the input map
     *  keys: the returned Tensor of all keys in the map
     *
     * @param <T> data type for `keys` output
     * @param inputHandle The inputHandle value
     * @param keyDtype The value of the keyDtype attribute
     * @param <T> data type for `TensorMapStackKeys` output and operands
     * @return a new instance of TensorMapStackKeys
     * @see org.tensorflow.op.Ops.tensorMapStackKeys
     */
    @JvmName("tensorMapStackKeysReified")
    public inline fun <reified T : TType> tensorMapStackKeys(inputHandle: Operand<out TType>):
            TensorMapStackKeys<T> = tensorMapStackKeys<T>(inputHandle, T::class.java)

    /**
     * Finds unique elements along an axis of a tensor.
     *  This operation either returns a tensor `y` containing unique elements
     *  along the `axis` of a tensor. The returned unique elements is sorted
     *  in the same order as they occur along `axis` in `x`.
     *  This operation also returns a tensor `idx` that is the same size as
     *  the number of the elements in `x` along the `axis` dimension. It
     *  contains the index in the unique output `y`.
     *  In other words, for an `1-D` tensor `x` with `axis = None:
     *  
     * `y&#91;idx[i&#93;] = x[i] for i in &#91;0, 1,...,rank(x) - 1&#93;`
     *  
     * For example:
     *  ```
     * # tensor 'x' is [1, 1, 2, 4, 4, 4, 7, 8, 8]
     *  y, idx = unique(x)
     *  y ==> [1, 2, 4, 7, 8]
     *  idx ==> [0, 0, 1, 2, 2, 2, 3, 4, 4]
     *  
     * ```
     *  
     * For an `2-D` tensor `x` with `axis = 0`:
     *  ```
     * # tensor 'x' is [[1, 0, 0],
     *  #                [1, 0, 0],
     *  #                [2, 0, 0]]
     *  y, idx = unique(x, axis=0)
     *  y ==> [[1, 0, 0],
     *         [2, 0, 0]]
     *  idx ==> [0, 0, 1]
     *  
     * ```
     *  
     * For an `2-D` tensor `x` with `axis = 1`:
     *  ```
     * # tensor 'x' is [[1, 0, 0],
     *  #                [1, 0, 0],
     *  #                [2, 0, 0]]
     *  y, idx = unique(x, axis=1)
     *  y ==> [[1, 0],
     *         [1, 0],
     *         [2, 0]]
     *  idx ==> [0, 1, 1]
     *  
     * ```
     *
     * @param <T> data type for `y` output
     * @param <V> data type for `idx` output
     * @param x A `Tensor`.
     * @param axis A `Tensor` of type `int32` (default: None). The axis of the Tensor to
     *  find the unique elements.
     * @param outIdx The value of the outIdx attribute
     * @param <T> data type for `UniqueV2` output and operands
     * @param <V> data type for `UniqueV2` output and operands
     * @return a new instance of Unique
     * @see org.tensorflow.op.Ops.unique
     */
    @JvmName("uniqueReified")
    public inline fun <T : TType, reified V : TNumber> uniqueTyped(x: Operand<T>, axis: Operand<out
            TNumber>): Unique<T, V> = unique<T, V>(x, axis, V::class.java)

    /**
     * Finds unique elements along an axis of a tensor.
     *  This operation either returns a tensor `y` containing unique elements
     *  along the `axis` of a tensor. The returned unique elements is sorted
     *  in the same order as they occur along `axis` in `x`.
     *  This operation also returns a tensor `idx` and a tensor `count`
     *  that are the same size as the number of the elements in `x` along the
     *  `axis` dimension. The `idx` contains the index in the unique output `y`
     *  and the `count` contains the count in the unique output `y`.
     *  In other words, for an `1-D` tensor `x` with `axis = None:
     *  
     * `y&#91;idx[i&#93;] = x[i] for i in &#91;0, 1,...,rank(x) - 1&#93;`
     *  
     * For example:
     *  ```
     * x = tf.constant([1, 1, 2, 4, 4, 4, 7, 8, 8])
     *  y, idx, count = UniqueWithCountsV2(x, axis = [0])
     *  y ==> [1, 2, 4, 7, 8]
     *  idx ==> [0, 0, 1, 2, 2, 2, 3, 4, 4]
     *  count ==> [2, 1, 3, 1, 2]
     *  
     * ```
     *  
     * For a `2-D` tensor `x` with `axis = 0`:
     *  ```
     * x = tf.constant([[1, 0, 0],
     *                  [1, 0, 0],
     *                  [2, 0, 0]])
     *  y, idx, count = UniqueWithCountsV2(x, axis=[0])
     *  y ==> [[1, 0, 0],
     *         [2, 0, 0]]
     *  idx ==> [0, 0, 1]
     *  count ==> [2, 1]
     *  
     * ```
     *  
     * For a `2-D` tensor `x` with `axis = 1`:
     *  ```
     * x = tf.constant([[1, 0, 0],
     *                  [1, 0, 0],
     *                  [2, 0, 0]])
     *  y, idx, count = UniqueWithCountsV2(x, axis=[1])
     *  y ==> [[1, 0],
     *         [1, 0],
     *         [2, 0]]
     *  idx ==> [0, 1, 1]
     *  count ==> [1, 2]
     *  
     * ```
     *
     * @param <T> data type for `y` output
     * @param <V> data type for `idx` output
     * @param x A `Tensor`.
     * @param axis A `Tensor` of type `int32` (default: None). The axis of the Tensor to
     *  find the unique elements.
     * @param outIdx The value of the outIdx attribute
     * @param <T> data type for `UniqueWithCountsV2` output and operands
     * @param <V> data type for `UniqueWithCountsV2` output and operands
     * @return a new instance of UniqueWithCounts
     * @see org.tensorflow.op.Ops.uniqueWithCounts
     */
    @JvmName("uniqueWithCountsReified")
    public inline fun <T : TType, reified V : TNumber> uniqueWithCountsTyped(x: Operand<T>,
            axis: Operand<out TNumber>): UniqueWithCounts<T, V> = uniqueWithCounts<T, V>(x, axis,
            V::class.java)

    /**
     * Creates a handle to a Variable resource.
     *
     * @param dtype the type of this variable. Must agree with the dtypes
     *  of all ops using this variable.
     * @param shape The (possibly partially specified) shape of this variable.
     * @param options carries optional attribute values
     * @param <T> data type for `VarHandleOp` output and operands
     * @return a new instance of VarHandleOp
     * @see org.tensorflow.op.Ops.varHandleOp
     * @param container Sets the container option.
     *
     * @param container the container this variable is placed in.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the name by which this variable is referred to.
     * @return this Options instance.
     * @param allowedDevices Sets the allowedDevices option.
     *
     * @param allowedDevices DEPRECATED. The allowed devices containing the resource variable. Set
     * when the
     *  output ResourceHandle represents a per-replica/partitioned resource variable.
     * @return this Options instance.
     */
    @JvmName("varHandleOpReified")
    public inline fun <reified T : TType> varHandleOp(
        shape: Shape,
        container: String? = null,
        sharedName: String? = null,
        allowedDevices: List<String>? = null
    ): VarHandleOp = varHandleOp<T>(T::class.java, shape, container, sharedName, allowedDevices)

    /**
     * Holds state in the form of a tensor that persists across steps.
     *  Outputs a ref to the tensor state so it may be read or modified.
     *  TODO(zhifengc/mrry): Adds a pointer to a more detail document
     *  about sharing states in tensorflow.
     *
     * @param <T> data type for `ref` output
     * @param shape The shape of the variable tensor.
     * @param dtype The type of elements in the variable tensor.
     * @param options carries optional attribute values
     * @param <T> data type for `VariableV2` output and operands
     * @return a new instance of Variable
     * @see org.tensorflow.op.Ops.variable
     * @param container Sets the container option.
     *
     * @param container If non-empty, this variable is placed in the given container.
     *  Otherwise, a default container is used.
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName If non-empty, this variable is named in the given bucket
     *  with this shared_name. Otherwise, the node name is used instead.
     * @return this Options instance.
     */
    @JvmName("variableReified")
    public inline fun <reified T : TType> variable(
        shape: Shape,
        container: String? = null,
        sharedName: String? = null
    ): Variable<T> = variable<T>(shape, T::class.java, container, sharedName)

    /**
     * Returns the shape of the variable pointed to by `resource`.
     *  This operation returns a 1-D integer tensor representing the shape of `input`.
     *  
     * For example:
     *  ```
     * # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
     *  shape(t) ==> [2, 2, 3]
     *  
     * ```
     *
     * @param <T> data type for `output` output
     * @param input The input value
     * @param outType The value of the outType attribute
     * @param <T> data type for `VariableShape` output and operands
     * @return a new instance of VariableShape
     * @see org.tensorflow.op.Ops.variableShape
     */
    @JvmName("variableShapeReified")
    public inline fun <reified T : TNumber> variableShapeTyped(input: Operand<out TType>):
            VariableShape<T> = variableShape<T>(input, T::class.java)

    /**
     * Creates a zeroed tensor given its type and shape.
     *
     * @param dims a 1-D operand that represents the shape of the output tensor
     * @param type the output tensor datatype
     * @return a constant tensor initialized with zeros
     * @throws IllegalArgumentException if the tensor type or shape cannot be initialized with
     * zeros.
     * @see org.tensorflow.op.Ops.zeros
     */
    @JvmName("zerosReified")
    public inline fun <reified T : TType> zeros(dims: Operand<out TNumber>): Zeros<T> =
            zeros<T>(dims, T::class.java)
}
