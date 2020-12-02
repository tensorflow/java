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

import org.tensorflow.DataType
import org.tensorflow.Operand
import org.tensorflow.ndarray.Shape
import org.tensorflow.op.Scope
import org.tensorflow.op.xla.BroadcastHelper
import org.tensorflow.op.xla.ClusterOutput
import org.tensorflow.op.xla.Conv
import org.tensorflow.op.xla.Dequantize
import org.tensorflow.op.xla.Dot
import org.tensorflow.op.xla.DynamicSlice
import org.tensorflow.op.xla.DynamicUpdateSlice
import org.tensorflow.op.xla.Einsum
import org.tensorflow.op.xla.Gather
import org.tensorflow.op.xla.KeyValueSort
import org.tensorflow.op.xla.Pad
import org.tensorflow.op.xla.Recv
import org.tensorflow.op.xla.ReplicaId
import org.tensorflow.op.xla.SelfAdjointEig
import org.tensorflow.op.xla.Send
import org.tensorflow.op.xla.Sharding
import org.tensorflow.op.xla.Sort
import org.tensorflow.op.xla.Svd
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building {@code xla} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class XlaOps(
	/**
	 * Get the parent {@link KotlinOps} object.
	 */
	public val ops: KotlinOps
) {
	public val java: org.tensorflow.op.XlaOps = ops.java.xla

	/**
	 * Returns the current {@link Scope scope} of this API
	 */
	public val scope: Scope = ops.scope

	public fun <T : TType, U : TNumber> broadcastHelper(
		lhs: Operand<T>,
		rhs: Operand<T>,
		broadcastDims: Operand<U>
	): BroadcastHelper<T> = java.broadcastHelper<T, U>(	
		lhs,
		rhs,
		broadcastDims
		)

	public fun <T : TType> clusterOutput(input: Operand<T>): ClusterOutput<T> = java.clusterOutput<T>(	
		input
		)

	public fun <T : TType, U : TNumber> conv(
		lhs: Operand<T>,
		rhs: Operand<T>,
		windowStrides: Operand<U>,
		padding: Operand<U>,
		lhsDilation: Operand<U>,
		rhsDilation: Operand<U>,
		featureGroupCount: Operand<U>,
		dimensionNumbers: String,
		precisionConfig: String
	): Conv<T> = java.conv<T, U>(	
		lhs,
		rhs,
		windowStrides,
		padding,
		lhsDilation,
		rhsDilation,
		featureGroupCount,
		dimensionNumbers,
		precisionConfig
		)

	public fun dequantize(
		input: Operand<*>,
		minRange: Float,
		maxRange: Float,
		mode: String,
		transposeOutput: Boolean
	): Dequantize = java.dequantize(	
		input,
		minRange,
		maxRange,
		mode,
		transposeOutput
		)

	public fun <T : TType> dot(
		lhs: Operand<T>,
		rhs: Operand<T>,
		dimensionNumbers: String,
		precisionConfig: String
	): Dot<T> = java.dot<T>(	
		lhs,
		rhs,
		dimensionNumbers,
		precisionConfig
		)

	public fun <T : TType, U : TNumber> dynamicSlice(
		input: Operand<T>,
		startIndices: Operand<U>,
		sizeIndices: Operand<U>
	): DynamicSlice<T> = java.dynamicSlice<T, U>(	
		input,
		startIndices,
		sizeIndices
		)

	public fun <T : TType, U : TNumber> dynamicUpdateSlice(
		input: Operand<T>,
		update: Operand<T>,
		indices: Operand<U>
	): DynamicUpdateSlice<T> = java.dynamicUpdateSlice<T, U>(	
		input,
		update,
		indices
		)

	public fun <T : TType> einsum(
		a: Operand<T>,
		b: Operand<T>,
		equation: String
	): Einsum<T> = java.einsum<T>(	
		a,
		b,
		equation
		)

	public fun <T : TType, U : TNumber> gather(
		operand: Operand<T>,
		startIndices: Operand<U>,
		sliceSizes: Operand<U>,
		dimensionNumbers: String,
		indicesAreSorted: Boolean
	): Gather<T> = java.gather<T, U>(	
		operand,
		startIndices,
		sliceSizes,
		dimensionNumbers,
		indicesAreSorted
		)

	public fun <T : TNumber, U : TType> keyValueSort(keys: Operand<T>, values: Operand<U>):
			KeyValueSort<T, U> = java.keyValueSort<T, U>(	
		keys,
		values
		)

	public fun <T : TType, U : TNumber> pad(
		input: Operand<T>,
		paddingValue: Operand<T>,
		paddingLow: Operand<U>,
		paddingHigh: Operand<U>,
		paddingInterior: Operand<U>
	): Pad<T> = java.pad<T, U>(	
		input,
		paddingValue,
		paddingLow,
		paddingHigh,
		paddingInterior
		)

	public fun <T : TType> recv(
		dtype: DataType<T>,
		tensorName: String,
		shape: Shape
	): Recv<T> = java.recv<T>(	
		dtype,
		tensorName,
		shape
		)

	public fun replicaId(): ReplicaId = java.replicaId(	
		
		)

	public fun <T : TType> selfAdjointEig(
		a: Operand<T>,
		lower: Boolean,
		maxIter: Long,
		epsilon: Float
	): SelfAdjointEig<T> = java.selfAdjointEig<T>(	
		a,
		lower,
		maxIter,
		epsilon
		)

	public fun <T : TType> send(tensor: Operand<T>, tensorName: String): Send = java.send<T>(	
		tensor,
		tensorName
		)

	public fun <T : TType> sharding(input: Operand<T>): Sharding<T> = java.sharding<T>(	
		input
		)

	public fun <T : TType> sort(input: Operand<T>): Sort<T> = java.sort<T>(	
		input
		)

	public fun <T : TType> svd(
		a: Operand<T>,
		maxIter: Long,
		epsilon: Float,
		precisionConfig: String
	): Svd<T> = java.svd<T>(	
		a,
		maxIter,
		epsilon,
		precisionConfig
		)
}
